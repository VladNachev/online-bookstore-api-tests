[![CI](https://github.com/VladNachev/online-bookstore-api-tests/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/VladNachev/online-bookstore-api-tests/actions/workflows/ci.yml)

[![Allure Report](https://img.shields.io/badge/Allure_Report-View_Report-1f6feb?logo=allure&logoColor=white)](https://vladnachev.github.io/online-bookstore-api-tests/)

# Online Bookstore API Test Automation Framework

API automation for the public [FakeRestAPI Bookstore API](https://fakerestapi.azurewebsites.net/index.html).

The framework validates the `Books` and `Authors` API resources using Java, TestNG, RestAssured, JSON Schema validation, and Allure reporting. It also uses Apache Maven as the build tool and includes Maven Wrapper support to allow the framework to be executed without requiring a locally installed Maven version.

## Tech Stack
- Java
- Maven
- Maven Wrapper
- TestNG
- RestAssured
- Allure Report
- JSON Schema Validator
- GitHub Actions

## Covered API Areas

### Books

- `GET /api/v1/Books`
- `GET /api/v1/Books/{id}`
- `POST /api/v1/Books`
- `PUT /api/v1/Books/{id}`
- `DELETE /api/v1/Books/{id}`

### Authors

- `GET /api/v1/Authors`
- `GET /api/v1/Authors/{id}`
- `GET /api/v1/Authors/authors/books/{idBook}`
- `POST /api/v1/Authors`
- `PUT /api/v1/Authors/{id}`
- `DELETE /api/v1/Authors/{id}`

## Project Structure

```text
src/test/java/bookstore
|-- clients      # API client classes built on top of RestAssured
|-- config       # Base URL and endpoint constants
|-- dto          # Request and response DTOs
|-- testdata     # Test data factories, invalid payloads, and DataProviders
|-- tests        # TestNG test classes grouped by resource
`-- utils        # Reusable response validation helpers

src/test/resources
|-- schemas      # JSON schemas used for response validation
`-- testng.xml   # TestNG suite configuration
```

## Prerequisites
Before running the tests locally, make sure you have:

- Java 17+ installed
- Maven available, or use the included Maven Wrapper
- Internet access, because the tests run against the public FakeRestAPI service

## Setup, configure and run the framework
### Clone repository
```bash
git clone https://github.com/VladNachev/online-bookstore-api-tests
```
You can import the project into your preferred IDE (like Intellij for examples) as a Maven project and run the tests directly from the TestNG test classes, or run the tests from the command line using Maven or Maven Wrapper.

## How to Run Tests Locally
I recommend using the Maven Wrapper in order to avoid any Maven version incompatibilities issues. 

From the project root, depending on your terminal and OS, run the following commands:
- For Windows PowerShell:

```powershell
.\mvnw.cmd clean test
```

- For Windows Command Prompt:
```cmd
mvnw.cmd clean test
```

- For Git Bash terminal or Unix-based terminals (Linux, macOS):
```bash
./mvnw clean test
```

### Generate Allure Report Locally

After running the tests, generate and open the Allure report with the command that matches your terminal and operating system:

- For Windows PowerShell:
```powershell
.\mvnw.cmd allure:serve
```

- For Windows Command Prompt:
```cmd
mvnw.cmd allure:serve
```
- For Git Bash terminal or Unix-based terminals (Linux, macOS):
```bash
./mvnw allure:serve
```

The allure:serve command generates the report, starts a temporary local server, and opens the report in your default browser.

The generated report is located at:

```bash
target/site/allure-maven-plugin
```

## Running Tests With Docker

This project includes Docker support, so the test suite can be executed inside a container.

### Build the Docker Image

From the project root, run:

```bash
docker build -t bookstore-api-tests .
```

### Run Tests Inside the Container
```bash 
docker run --rm bookstore-api-tests
```

### Keep Test Results on the Host Machine when using Docker
By default, the container is removed after the test run. To keep the generated target folder on your machine, mount it as a volume.

- Windows PowerShell
```powershell
docker run --rm -v ${PWD}/target:/app/target bookstore-api-tests
```

- Windows Command Prompt
```cmd
docker run --rm -v "%cd%/target:/app/target" bookstore-api-tests
```

- Linux / macOS / Git Bash
```bash
docker run --rm -v "$(pwd)/target:/app/target" bookstore-api-tests
```

The test results and Allure result files will be available in:
```text
target/
```

Note: Docker support is optional. The tests can still be run directly with Maven Wrapper or through the GitHub Actions CI pipeline.

## CI and Allure Reports on GitHub Pages

This project uses GitHub Actions to run the API test suite automatically on every push and pull request to the `master` branch.

The CI workflow performs the following steps:

- Checks out the repository
- Sets up Java 17
- Runs the Maven TestNG test suite
- Generates an Allure report
- Uploads the Allure report as a downloadable GitHub Actions artifact
- Publishes the latest Allure report to GitHub Pages

The latest Allure report is automatically published to GitHub Pages after each successful push workflow run on the `master` branch.

[View Latest Allure Report](https://vladnachev.github.io/online-bookstore-api-tests/)

The report includes:

- test execution summary
- passed/failed test status
- test grouping by feature and suite
- TestNG data provider parameters
- request and response attachments from RestAssured
- assertion failure details when a test fails

## Downloading the Allure Report Artifact

Each workflow run also stores the generated Allure report as a downloadable artifact.

To download it:

1. Open the repository on GitHub.
2. Go to the **Actions** tab.
3. Select the latest workflow run.
4. Scroll down to the **Artifacts** section.
5. Download the `allure-report` artifact.
6. Extract the downloaded `.zip` file.
7. Open the report through a local web server, for example:

```bash
cd allure-report
python -m http.server 8080
```

And then open `http://localhost:8080` in your web browser. The browser will automatically load the Allure report index.html from that folder.

### Notes About FakeRestAPI Behavior

FakeRestAPI is a public demo API. Some responses may contain dynamic or non-persistent data.

Because of that, the tests avoid relying on unstable response content where appropriate. For example:

- existing resources are validated by status code, schema, ID, and required/populated fields
- POST and PUT responses are validated against the request payload when the API echoes submitted data
- some negative scenarios are documented when the API returns behavior that differs from typical REST expectations

This keeps the test suite stable while still validating meaningful API behavior.

### Test Design Highlights

The framework uses:

- client classes to isolate API request logic
- DTOs for typed request and response bodies
- reusable validation helpers
- TestNG DataProviders for edge and negative scenarios
- JSON schemas for response contract validation
- Allure annotations for readable test reports
- GitHub Actions for continuous test execution

### Run a Specific Test Suite
The Maven Surefire plugin is configured to use:

``` bash
src/test/resources/testng.xml
```
To adjust which tests are executed, update the TestNG suite file.