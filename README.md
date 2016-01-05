Build Status : <img src="https://travis-ci.org/sagarkarnati/spring-app.svg?branch=master">

## Synopsis


## Code Example


## Motivation


## Installation


## API Reference


## Tests


## Contributors


## License

## Available Features
*   Spring seed project setup
*   Spring Data with MongoDB
*   Spring REST Repositories
*   Test NG framework support
*   InMemory UniTests with fongo.
*   Travis CI Integration.
*   Exception Handler in REST Services
*   Hibernate Validation framework for domain objects.
*   MongoDB level validations
*   Env Specific Logging
	*   Add the below parameter to container runtime arguments
	 	-Dlog4j.configuration=log4j-<env>.properties	 
*   Spring security
	*   Project setup and configuration.
		*   Resource level security configuration.
		*   Method level security configuration using AOP.
	*   Authentication using Mongo DB.
	*   Application specific roles instead of spring default roles.
	*   Securing the communication with Token based authentication.
*   Create an unique identifier to trace to all the logs.

## InProgress
*   Saving files GridFS Support
*   Full text search support with mongodb
*   Specifying index in the domain model classes
*   Integration testing support
	*	Adding server.xml entries and memory params to the maven integration testing suit   

## TODO
*   Auditing entities in Spring Data MongoDB
*   http://metrics.dropwizard.io/ integration
*   Spring security
	*   Using this with angular JS Application running in a different domain.
*   Full text search support with elastic search.
*   HTTP Request and Response Logging. 
*   PUB-SUB model for mongodb
*   Spring Profiles for Continuous Integration.
*   Code quality tools like findBugs,PMD.
*   Code Coverage Tool like ECL-EMMA
*   Swagger Api Tool Integration.
*   Adding Spring Security OAuth 1.0
*   Adding Spring Security OAuth 2.0
*   Release management.
*   Togglz integration.
*   YAML for configurations.
*   SPEL Usage.
*   LogStash.
*   Log4j 2 support with asyc logging to LogStash.
*   Artificial Intelligence
*   Machine Learning
*   Create a docker file to setup the environment for quickly running this app.
*   Unit Testing spring mvc components with Rest Assured
	https://github.com/jayway/rest-assured/wiki/Usage#spring-mock-mvc-module
*   JSON Schema
	http://json-schema.org/examples.html
*   Disable running Integration Tests by default
		