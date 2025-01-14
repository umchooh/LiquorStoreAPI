# Liquor Store API

## Description

LiquorStoreAPI is a Spring Boot-based application that provides a RESTful API for customer registration. The API allows customers to register via HTTP requests and retrieve a list of registered customers. This project demonstrates the creation of a simple transactional web application using Spring Boot, including the ability to manage customer data through endpoints for registration and listing.

## Getting Started
### Prerequisites
Ensure you have the following installed:
* Java 21 (as specified in the pom.xml file)
* Maven

### Dependencies

This project uses the following dependencies:
* Spring Boot Starter Data JPA
* Spring Boot Starter Web
* Spring Boot DevTools
* H2 Database
* Spring Boot Starter Test
* Lombok
* JUnit 5 (JUnit Jupiter)
* JUnit 5 API
* JUnit 5 Parameters



## Steps to Run the Project

* Clone the repository:
```
git clone https://github.com/umchooh/LiquorStoreAPI.git
```
* Navigate to the project directory:
```
cd LiquorStoreAPI
```
* Build the project using Maven:
```
mvn clean install
```
* Run the Spring Boot application:
```
mvn spring-boot:run
```
* Access the API at http://localhost:8080.


### Running the Tests
* Run unit and integration tests with Maven:
```
mvn test
```

## API Enpoints

#### Customer Registration API
### POST	/customers/register
To register customer.

### GET	/customers/list
Get the list of customers.

### DELETE	/customers/remove/{id}
Remove customer by cusotmer id.
