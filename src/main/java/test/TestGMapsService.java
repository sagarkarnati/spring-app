package test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.path.json.JsonPath.from;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.bson.Document;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixElementStatus;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestGMapsService
{
	// Replace the API key below with a valid API key.
	private static final String API_KEY = "AIzaSyCLE1p-guk1G6sQx9xJ34mFjQfi1n_rRS4";
	private static final GeoApiContext CONTEXT = new GeoApiContext().setApiKey(API_KEY);

	public static void main(String[] args)
	{
		try 
		{
			MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017");
			try (MongoClient mongoClient = new MongoClient(connectionString)) 
			{
				MongoDatabase database = mongoClient.getDatabase("GEO_TEMP");

				Path file = Paths.get("C:\\Users\\sagarkarnati\\git\\spring-app\\src\\main\\resources\\employee.csv");
				BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());
				String line = null;
				Set<String> places = new HashSet<String>();
				int i = 0;
				while ((line = reader.readLine()) != null) 
				{
					if(i++ == 0)
						continue;

					String[] split = line.split("\\|");
					places.add(split[3]);
				}
				populateDistanceMatrix(database, places);
				populateGeoInformation(database, places);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private static void populateGeoInformation(MongoDatabase database, Set<String> places)
	{
		MongoCollection<Document> collection = database.getCollection("GEO_INFROMATION");
		for (String src : places) 
		{
			String json = get("https://maps.googleapis.com/maps/api/geocode/json?address="+src+"&key="+API_KEY).asString();
			if("OK".equals(from(json).getString("status")))
			{
				collection.insertOne(Document.parse(json));
			}
		}
	}

	private static void populateDistanceMatrix(MongoDatabase database, Set<String> places) throws Exception
	{
		MongoCollection<Document> collection = database.getCollection("DISTANCE_MATRIX");
		for (String src : places) 
		{
			for (String dest : places) 
			{
				if (!dest.equals(src)) 
				{
					Document dbObject = new Document();

					String[] srcArr = { src };
					String[] destArr = { dest };
					
					if(isNew(database, src, dest))
					{
						DistanceMatrixApiRequest results = DistanceMatrixApi.getDistanceMatrix(CONTEXT, srcArr, destArr);
						DistanceMatrix matrix = results.await();
						for (int i = 0; i < srcArr.length; i++) 
						{
							dbObject.append("SOURCE", src).append("DESTINATION", dest);
							dbObject.append("SOURCE_DETAIL", matrix.originAddresses[i]).append("DESTINATION_DETAIL", matrix.destinationAddresses[i]);
							for (DistanceMatrixElement element : matrix.rows[i].elements) 
							{	
								if(element.status.equals(DistanceMatrixElementStatus.OK))
								{
									dbObject.append("DISTANCE",element.distance.inMeters).append("DURATION",element.duration.inSeconds).append("STATUS", "SUCCESS");
								}else
								{
									dbObject.append("STATUS", "FAILED");
								}
							}
						}
						collection.insertOne(dbObject);
					}
				}
			}
		}
	}

	private static boolean isNew(MongoDatabase database,String src,String dest)
	{
		MongoCollection<Document> collection = database.getCollection("DISTANCE_MATRIX");
		Document dbObject = new Document();
		dbObject.append("SOURCE", src);
		dbObject.append("DESTINATION", dest);
		return (collection.count(dbObject) == 0);
	}
}