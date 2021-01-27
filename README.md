# Player Market
## Overview

This project is a CRUD rest api application to do operations on football players and teams.

## Technology Stack

- Spring Boot
- Hibernate/JPA
- Docker
- My-SQL
- Swagger
- SpringFox
- Maven
- Junit
- Mockito
- MapStruct

## Starting the application
First make sure you have installed Maven and docker in your environment.

### To start application follow these steps:
- I created Docker-compose.yml file to run Mysql server in docker container. To start mysql server run this command
- **docker-compose up -d**
- When Mysql server starts to run we can start our spring boot application with this command:
- **mvn spring-boot:run** 
- When it is successfully compile and run go to this swagger interface to see Api endpoints
- http://localhost:8080/swagger-ui.html
- You can see all the endpoints from this link and test it.
- If the example data is not loaded in the beginning, please check scripts folder and run init.sql by manually
- If you are using windows home and running your docker engine on VM you should change data source url in application.properties to spring.datasource.url=jdbc:mysql://192.168.99.100:3306/test?useSSL=false
