# maintenance-app project

This project comprises an API to manage maintenance messages, with two different levels of access: TECHNICIAN and 
MANAGER.

The framework used is Quarkus, please visit its website for general reference: https://quarkus.io/ .

## Running the application

Before building the application, please, run;
```shell script
docker-compose up
```
on the root of the project, in order to build the container for postgres. 

You can run the application with:
```shell script
mvn package quarkus:dev
```


## Swagger 
In case you want to use Swagger to perform some manual API testing: http://localhost:8080/q/swagger-ui/#/ (available 
only when the app is running). 



