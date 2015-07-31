//package com.vidya.api.repos;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.github.fakemongo.Fongo;
//import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
//import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
//import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
//import com.mongodb.Mongo;
//import com.vidya.api.repository.EmployeeRepository;
//
//@ContextConfiguration
//public class PersonRepositoryTest extends AbstractTestNGSpringContextTests
//{
//
//	public MongoDbRule mongoDbRule;
//	
//	@BeforeClass
//	public void setup()
//	{
//		mongoDbRule = newMongoDbRule().defaultSpringMongoDb("demo-test");
//	}
//	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("demo-test");
//
//	/**
//	 *
//	 * nosql-unit requirement
//	 *
//	 */
//	@Autowired
//	private ApplicationContext applicationContext;
//
//	@Autowired
//	private EmployeeRepository personRepository;
//
//	/**
//	 * Expected results are in "one-person.json" file
//	 */
////	@Test
////	@ShouldMatchDataSet(location = "/two-person.json")
////	public void testInsertPersonWithNameJohnathanAndRandomAge()
////	{
////		this.personRepository.insertPersonWithNameJohnathan(35);
////		this.personRepository.insertPersonWithNameJohnathan(67);
////	}
//
//	/**
//	 * Insert data from "five-person.json" and test countAllPersons method
//	 */
//	@Test
//	@UsingDataSet(locations = { "/five-person.json" }, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
//	public void testCountAllPersons()
//	{
//		long total = this.personRepository.countAllPersons();
//
//		assertThat(total).isEqualTo(5);
//	}
//
//	/**
//	 * Insert data from "five-person.json" and test countUnderAge method
//	 */
//	@Test
//	@UsingDataSet(locations = { "/five-person.json" }, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
//	public void testCountUnderAge()
//	{
//		long total = this.personRepository.countUnderAge();
//
//		assertThat(total).isEqualTo(3);
//	}
//
//	@Configuration
//	@EnableMongoRepositories
//	@ComponentScan(basePackageClasses = { EmployeeRepository.class })
//	@PropertySource("classpath:application.properties")
//	static class PersonRepositoryTestConfiguration extends AbstractMongoConfiguration
//	{
//		@Override
//		protected String getDatabaseName()
//		{
//			return "demo-test";
//		}
//
//		@Override
//		public Mongo mongo()
//		{
//			return new Fongo("mongo-test").getMongo();
//		}
//
//		@Override
//		protected String getMappingBasePackage()
//		{
//			return "com.johnathanmarksmith.mongodb.example.domain";
//		}
//	}
//}