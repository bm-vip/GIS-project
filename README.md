# Electric vehicle charging station management system
## Installation
You have 2 ways for installation:\
Before running the application, the following commands must be executed in the terminal from the project root directory:
1. prerequisites for using Docker (recommended) 
   1. Install Docker
   2. run the following command:
```
docker-compose up
```
2. Prerequisites for using current OS
   1. Install Java 17
   2. Install Postgres sql database
   3. Maven 3
   4. run the following command:
```
mvn clean install
java -jar target/electric-vehicle-charging-station-management-1.0.0-SNAPSHOT.jar
```
## Kubernetes configuration
For API scalability, kubernetes should be installed and the following commands must be executed for deploying the images:
```
kubectl apply -f k8s-deployment-app.yaml
kubectl apply -f k8s-deployment-db.yaml
kubectl get pods | grep electric
kubectl get pods | grep postgres
```
Note: If any container of related images has already been started by Docker Compos, must be stopped that containers to prevent the port from being occupied with the following commands:
```
docker stop electric-vehicle-charging-station-management_app_1
docker stop postgresql-container
```
Finally, for checking application interface, should port forward pods on current OS with the following commands:
```
kubectl port-forward electric-vehicle-charging-station-management 2022:2022&
kubectl port-forward postgres-db 5432:5432&
```
After finishing installation click on the [Link](http://localhost:2022) to display login form with `admin` userName and `12345` password by `ADMIN_ROLE`.<br/> Also, you can register a new user on login form by `USER_ROLE`.<br/> After login there are 4 web forms to consuming APIs.<br/>In addition, the swagger-UI [Link](http://localhost:2022/swagger-ui.html) exists to check all APIs.
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