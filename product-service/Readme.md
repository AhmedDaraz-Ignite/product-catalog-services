## Summary

Simple product management application, exposing REST API to perform CRUD operations

### Project used technologies
 - Java 8
 - MySQL DB
 - Spring Boot
 - Swagger (API documentation)
 - Spring security
 - Spring Mvc web for Rest API



### How to run

Project is basen on spring boot, and it has 2 main profiles (dev/prod)
It is recommended to run with dev profile as it has some data useful for testing purposes.

#### Current configuration files are under config folder in project root folder, however you can still change this location and point to your own folder:
```
-Dspring.config.location=<YOUR_CONFIG_FOLDER>
```
#### Configure DB  ```application-dev.properties/application-prod.properties```

 - spring.datasource.url=<jdbc:mysql://<YOUR_HOST>:<YOUR_PORT>/product_catalog?createDatabaseIfNotExist=true>
 - spring.datasource.username=<YOUR_DB_USER> 
 - spring.datasource.password=<YOUR_DB_PASSWORD>

#### build the project with maven:

```
mvn clean install
```

#### To run with dev profile:

```
java -jar -Dspring.config.location=./config/ -Dspring.profiles.active=dev target/product-service-0.0.1-SNAPSHOT.jar
```

#### To access swagger UI for API documentation:

```
http://localhost:8080/swagger-ui.html
```

#### Sample test requests are added in Postman file
 - install Postman 
 - import the following API environment file <ProductManagement APIs.postman_environment.json>
 - import the following API file <ProductManagement APIs.postman_collection.json>
 