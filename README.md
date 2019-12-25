## Code Challenge by Saul Montoya

### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes

#### Prerequisites

Requires to have installed maven for the execution of the commands

#### Installing

For the execution of the microservice must download the branch master and execute the following command from the root directory of the project

###### mvn spring-boot:run

#### Running the tests

To run the unit tests you must be located in the root directory of the project and execute the following command

###### mvn test

#### Published methods
Method:GET

endpoint: http://localhost:8080/api/petitions?fromDate=201912010000&toDate=201912310000

endpoint: http://localhost:8080/api/petitions/1
