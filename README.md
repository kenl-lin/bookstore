# BookStore Backend System  

## 1.Description

#### 1.1 Project Overview


This project is a simple book management system that provides basic functions such as book categories, book addition, and shopping cart management. It is built and developed using Java 17 and spring boot 3.3.x.

#### 1.2 Why Chose Spring Boot ?

chose **Spring Boot** because it simplifies the development of production-grade Spring applications by offering:

- **Rapid Development:** Auto-configuration and starter dependencies reduce boilerplate code.
- **Community & Documentation:** Backed by a large community and extensive documentation, making it easy to find solutions.

---

#### 1.3 Project Layout
```text
bookstore/
├── src/
│   ├── main/
│   │   ├── java: Java source code directory
│   │   │   └── com/ken/bootstore/
│   │   │   	└── bean: Instance objects, request response objects, and related enumerations
│   │   │   	└── common: Common management ：such as exceptions
│   │   │   	└── constant: constant control
│   │   │   	└── repository: repository layer
│   │   │   	└── rest: api layer
│   │   │   	└── service: service layer
│   │   │   	└── utils: project util
│   │   └── resources/
│   │       └── ddl : database DDL scripts
│   │       └── dml: database DML scripts
│   │       └── application.yml
│   └── test:  unit test source code
│   │   │   └── com/ken/bootstore/
│   │   │   	└── rest:  rest unit test class
│   │   │   	└── service:  service unit test class
├── pom.xml: Maven related dependencies
└── README.md: Project description and
```
---

### 1.4 Unit Tests Description


The project has tests for both the rest and service layers to ensure code quality and functional reliability.The main tests verified the response status code, parameter legitimacy, data boundary values, exception prompts and other logical processing


---

## 2.Usage

### 2.1 Project download

You can download it via the current link. github link [Github](https://github.com/kenl-lin/bookstore)

git command
```shell
git clone https://github.com/kenl-lin/bookstore.git
```

### 2.2 How to start  project

Running from Source Code: You can start the project by launching **BookstoreApplication**.

---

### 2.3 Use API Documentation

When the system is successfully started, you can access it through the  [Swagger web UI](http://localhost:8080/swagger-ui.html)

### 2.4 How to compile mvn

run the maven command:

```shell
mvn clean package -Dmaven.test.skip=true
```

### 2.5 How to run unit tests

- run the maven command:

```shell
mvn clean test
```

- Execute through code: Go into the test/src class and execute the method

