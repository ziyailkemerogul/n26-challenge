# Task 3: Test Automation Framework for Swagger Petstore API

This project provides a test automation framework for performing CRUD (Create, Read, Update, Delete) operations on the **PET** endpoint of the [Swagger Petstore API](https://petstore3.swagger.io/). It includes a Dockerized setup and a TestNG test execution report.

## Project Overview
This test automation framework validates CRUD operations on the PET endpoint.
- Tests for creating, reading, updating, and deleting pets, with positive and negative scenarios.
- A TestNG test execution report in `target/surefire-reports/`.

## Tech Stack
- Java
- Maven
- TestNG
- REST Assured
- Docker
- TestNG Reports

### Why This Tech Stack?
Java and Maven for test automation. TestNG for better reporting and flexibility. REST Assured for API testing and Docker for portability. TestNG reports provide clear test results.

## Setup Instructions
### Prerequisites
- Java 11+
- Maven
- Docker

### Steps
1. **Verify Java and Maven**:
- java -version
- mvn -version

2. **Install and Verify Docker**:
- docker --version

## How to Run Tests
### Run Tests on Local
1. Navigate to the project directory: cd /task3-api-automation
2. Run tests: mvn clean install
3. View the report:
- Open `target/surefire-reports/index.html` in a browser.

### Run Tests in Docker
1. Navigate to the project directory: cd /task3-api-automation
2. Build the Docker image: docker build -t task3-api-automation .
3. Run the tests: docker run --rm task3-api-automation
4. Access the report: docker run --rm -v $(pwd)/target:/host_target task3-api-automation /bin/bash -c "mvn test && cp -r /app/target/surefire-reports /host_target/"
- Open `target/surefire-reports/index.html` in a browser.

## Approach and Design Decisions
### Approach
The framework focuses on the PET endpoint of the Swagger Petstore API for its clear CRUD operations.
- **Modular Design**: Separates API interactions, models, and tests for maintainability.
- **Test Organization**: Groups tests by CRUD operation (Create, Read, Update, Delete).
- **Error Handling**: Includes negative tests for error scenarios.
- **Retry Mechanism**: Handles API deletion delays in `PetDeleteTests` with retries.
- **Dockerization**: Ensures consistent execution across environments.
- **Reporting**: Uses TestNG for detailed HTML reports.

### Design Decisions
- **REST Assured**: Simplifies API testing with readable syntax.
- **TestNG**: Provides testing features reporting.
- **Retry Mechanism**: Ensures reliable deletion tests with unique pet IDs to avoid interference.
- **Docker**: Guarantees consistency across environments.

## Future Improvements
- Use Allure or ExtentReports for better reporting.
- Enable parallel test execution in TestNG.
