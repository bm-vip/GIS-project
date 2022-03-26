# Electric vehicle charging station management system
## Installation
Before running the application, the following commands must be executed in the terminal from the project root directory:
* prerequisites for using Docker 
   1. Install Docker
   2. Install Maven 3
```
mvn clean install
docker-compose up
```
* Prerequisites for using current OS
   1. Install Java 17
   2. Install Postgres sql database
   3. Maven 3
```
mvn clean install
java -jar electric-vehicle-charging-station-management-1.0.0-SNAPSHOT.jar
```
After finishing installation click on the [Link](http://localhost:2022/swagger-ui.html).

## Introduction
The task is to implement Rest-API for the electric vehicle charging station management
system.
* You should use java 14 or above.
* You must have your postgreSQL schema in your implementation
* Unit test and integration test should be practical and show your experience
* We need docker file and docker compose for all of application’s dependencies
* Pay attention to the scalability of the API.
* One charging company can own one or more other charging companies.
* Readme file with all setup steps is mandatory
* **You will get extra 100 points if you add all of k8s related files(secret,deployment... it is optional though).**

Hence, the parent company should have the access to all the child company's stations
hierarchically. For example, we got 3 companies A, B and C accordingly with 10,5 and 2
stations. Company B belongs to A and company C belongs to B. Then we can say that
company A has 17, company B has 7 and company C has 2 stations in total.

### The database schema for start point:
* `Station (id, name, latitude, longitude, company_id)`
* `Company (id, parent_company_id, name)`

You should make a git repository and commit as frequently as you can.

* **Task 1: Api should support CRUD for stations and companies.**
* **Task 2: Implement endpoint which gets all stations.**
  * Within the radius of n kilometers from a point (latitude, longitude) ordered by distance.
  * Including all the children stations in the tree, for the given company_id.
* **Task 3: Write a simple, not fancy interface that will consume your API programmatically.**