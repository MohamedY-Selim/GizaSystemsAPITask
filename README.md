# GizaSystemsAPITask

Automated API tests for **Swagger Petstore** using **Rest-Assured**, **TestNG**, **Java 24**, and **Allure**. CI/CD is configured with **GitHub Actions** to run tests on every push and publish an interactive Allure report to **GitHub Pages**.

---

## Project Overview

This project covers the required API scenarios:

* **Create a new Pet** (`POST /pet`) and validate response mapping.
* **Get Pet by Id** (`GET /pet/{petId}`) and extract name.
* **Find Pets by status** (`GET /pet/findByStatus?status=...`) and collect names.
* Full request/response logging with colorized console output for visibility.
* Allure reporting for steps, attachments, and execution timeline.

> The suite is built for maintainability: clear API layer, endpoint enum, POJOs with Jackson + Lombok, utility generators (Faker), and a thin base test class.

---

## Tech Stack

* **Language:** Java 24
* **Build:** Maven
* **Test Runner:** TestNG
* **HTTP Client:** Rest-Assured
* **Reporting:** Allure
* **Data Gen:** JavaFaker
* **CI:** GitHub Actions → Allure on GitHub Pages

---

## Project Structure

```
GizaSystemsAPITask
├── .github/workflows/ci.yml           # CI: run tests, print summary, publish Allure
├── src
│   ├── main/java/io/swagger/petstore
│   │   ├── api/                       # PetApi (request methods)
│   │   ├── base/                      # BaseApi, BaseTest (common init + logging helpers)
│   │   ├── config/                    # EndPoint enum
│   │   ├── factory/                   # ApiFactory (RequestSpecification builder)
│   │   ├── objects/                   # POJOs: Pet, Category, Tag
│   │   └── utils/                     # ConfigUtils, PropertiesUtils, PetUtils
│   ├── main/resources/properties/
│   │   └── testing.properties         # api_base_url, api_base_path
│   └── test/java/io/swagger/petstore/testcases/
│       └── PetApiTest.java            # TestNG scenarios
├── pom.xml                            # Dependencies + plugins
└── README.md                          # This file
```

---

## Setup

### Prerequisites

* **Java 24** (Temurin recommended)
* **Maven 3.9+**
* (Optional) **Allure CLI** for local report viewing

### Clone

```bash
git clone https://github.com/MohamedY-Selim/GizaSystemsAPITask.git
cd GizaSystemsAPITask
```

### Configuration

`src/main/resources/properties/testing.properties`:

```
api_base_url=https://petstore.swagger.io
api_base_path=/v2
```

> Keep `api_base_path=/v2` (the test code already prefixes endpoints correctly).

---

## Run Tests Locally

Run all tests:

```bash
mvn clean test -Dallure.results.directory=target/allure-results
```

Run a single class:

```bash
mvn -Dtest=io.swagger.petstore.testcases.PetApiTest test
```

### View Allure Report Locally

```bash
# If you have Allure CLI
allure serve target/allure-results
```

---

## CI/CD (GitHub Actions)

Pipeline does the following on every push/PR to `main`:

1. Sets up Java 24 & caches Maven dependencies.
2. Runs `mvn test` (keeps job green to publish reports).
3. Prints a **Test Summary** in the job logs (totals + test names).
4. Generates Allure report and publishes it to **GitHub Pages**.

**Live Report:**

* 📊 [https://mohamedy-selim.github.io/GizaSystemsAPITask](https://mohamedy-selim.github.io/GizaSystemsAPITask)

> If `gh-pages` is not visible, ensure GitHub Pages is enabled for the repo (branch: `gh-pages`, `/` root).

---