package com.vidya.api.repos;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = { "classpath:spring-base-test.xml", "classpath:spring-fongo-test.xml" })
public class UserRepositoryITTest extends AbstractTestNGSpringContextTests
{
	/*    @Autowired
    private UserRepository userRepository;

    public UserRepositoryITTest()
    {
	RestAssured.baseURI = "http://localhost:8888/vidya-api";
    }

    @Test(groups = { "integration" })
    public void test_getUsers() throws Exception
    {
	get("/users").then().assertThat().statusCode(200);
    }

    @Test(groups = { "integration" })
    public void test_postUsers() throws Exception
    {
	String testBody = "{\"username\": \"username3\",\"password\": \"b79aca2251469e626132a96ade2e5582d50b588e\",\"roles\": [{\"privileges\": [\"ROLE_USER\",\"ROLE_ADMIN\"]}],\"expires\": 123456789}";

	with().body(testBody).post("/users").then().assertThat().statusCode(201);
    }

    @Test(groups = { "integration" })
    public void test_putUsers() throws Exception
    {
	String userString = get("/users").getBody().jsonPath().getString("_embedded.users[0]");
	String id = from(userString).getString("id");

	Object object = JSON.parse(userString);
	BasicDBObject bObject = (BasicDBObject)object;
	bObject.put("username", "username_put");

	with().body(bObject.toString()).put("/users/"+id).then().assertThat().statusCode(204);
    }

    @Test(groups = { "integration" })
    public void test_deleteUsers() throws Exception
    {
	String id = get("/users").getBody().jsonPath().getString("_embedded.users[0].id");

	delete("/users/"+id).then().assertThat().statusCode(200);
    }

    private String getUser()
    {
	return get("/users").getBody().jsonPath().getString("_embedded.users[0]._id");
    }
	 */
}