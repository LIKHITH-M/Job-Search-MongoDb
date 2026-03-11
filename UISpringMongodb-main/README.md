# 📋 Quiz Application — Microservices Architecture

## Overview
This is a Quiz Application built using Java Spring Boot with a microservices architecture. The application is split into 4 independent services that communicate with each other to create quizzes, serve questions, and calculate scores. It demonstrates how requests are routed and handled between microservices using Eureka Service Discovery, Spring Cloud Gateway, and OpenFeign for inter-service communication.

## 🏗️ Architecture & Request Flow
The diagram below illustrates the high-level architecture and the specific request flow for creating a quiz. The Quiz Service depends on the Question Service to fetch questions via OpenFeign.

**Quiz Application Architecture and Request Flow:**
1. Client sends a request to the API Gateway.
2. API Gateway uses Eureka to discover the target service and routes the request.
3. Quiz Service handles quiz-related requests and internally calls the Question Service to fetch/manage questions.
4. Each service maintains its own PostgreSQL database (`quizdb` and `questiondb`) to ensure data isolation.

## 📦 Services Breakdown

### 1. 🛰️ Service Registry (Port 8761)
- **Role:** Eureka Server — the central hub for service discovery.
- **Registration:** All other services register themselves here on startup.
- **Configuration:** Set with `register-with-eureka=false` and `fetch-registry=false` as it is the server itself.

### 2. ⚡ API Gateway (Port 8765)
- **Role:** Single entry point for all client requests.
- **Routing:** Uses Spring Cloud Gateway to automatically route requests based on the service name in the URL (e.g., `/question-service/...`).

### 3. ❓ Question Service (Port 8083)
- **Role:** Manages the question bank.
- **Database:** `questiondb` (PostgreSQL).
- **Endpoints:**
  - `GET /question/allQuestions` — Get all questions.
  - `GET /question/category/{category}` — Get questions by category.
  - `POST /question/add` — Add a new question.
  - `POST /question/getScore` — Calculate score from submitted responses.
- **Logic:** Uses a native SQL query with `ORDER BY RANDOM() LIMIT :numQ` to pick random questions.

### 4. 📝 Quiz Service (Port 8090)
- **Role:** Manages quiz creation, question retrieval, and scoring.
- **Database:** `quizdb` (PostgreSQL).
- **Endpoints:**
  - `POST /quiz/create` — Create a quiz (category, number of questions, title).
  - `GET /quiz/get/{id}` — Get quiz questions by quiz ID.
  - `POST /quiz/submit/{id}` — Submit answers and get score.
- **Communication:** Annotated with `@FeignClient(name = "QUESTION-SERVICE")` to call Question Service endpoints.

## 🛠️ Tech Stack
| Technology | Purpose |
| :--- | :--- |
| **Java 17+** | Programming language |
| **Spring Boot** | Application framework |
| **Spring Cloud Eureka** | Service discovery & registration |
| **Spring Cloud Gateway** | API Gateway / request routing |
| **Spring Cloud OpenFeign** | Declarative inter-service REST calls |
| **Spring Data JPA** | Database access (ORM) |
| **PostgreSQL** | Relational database (2 databases) |
| **Lombok** | Boilerplate code reduction (`@Data`) |
| **Maven** | Build & dependency management |

---

## 🚀 How to Run the Application

Follow these steps to safely start the entire microservice architecture:

### Prerequisites:
1. Make sure you have **Java 17** (or above) installed.
2. Make sure you have **PostgreSQL** installed and running locally on port `5432`.
3. Create two empty databases in PostgreSQL: `questiondb` and `quizdb`.

### Step-by-Step Execution:
> **⚠️ IMPORTANT:** The services **must** be started in the following specific order. Wait for each service to fully start up (check the console logs) before moving to the next one!

**1. Start the Eureka Service Registry:**
Open a terminal, navigate to the `serviceregistry` folder and run:
```bash
./mvnw spring-boot:run
```
Wait until you see it running on `http://localhost:8761`.

**2. Start the API Gateway:**
Open a new terminal, navigate to the `apigateway` folder and run:
```bash
./mvnw spring-boot:run
```
Wait until it connects to Eureka.

**3. Start the Question Service:**
Open a new terminal, navigate to the `question-service` folder and run:
```bash
./mvnw spring-boot:run
```

**4. Start the Quiz Service:**
Open a new terminal, navigate to the `quiz-service` folder and run:
```bash
./mvnw spring-boot:run
```

### Verification:
Once everything is started, you can go to your browser and open **`http://localhost:8761`**. You should see the Eureka dashboard with all 3 clients (API Gateway, Question Service, Quiz Service) successfully registered and available!

---

## 🔮 Future Improvements & Roadmap

To make the application more robust and user-friendly, the following features and technical improvements are planned:

### Frontend Enhancements
- **Dynamic Filtering:** Add a filter button/sidebar to easily sort and filter job postings/questions by exact categories, required experience, and specific tech stacks.
- **Pagination:** Implement pagination on the frontend to handle large lists of data instead of fetching all records at once.
- **Search Optimization:** Improve the current text-based search to use a modern datagrid with debounce.

### Backend Improvements
- **Remove Hardcoded Values:** Eliminate hardcoded constants inside services (such as fixed ascending sorts or hardcoded SQL `LIMIT` parameters) and convert them to dynamic `application.properties` configurations or query parameters.
- **Improved Logging & Tracing:** Integrate Spring Cloud Sleuth and Zipkin for distributed tracing to monitor exactly how requests flow through the Gateway, Quiz Service, and Question Service.
- **Error Handling:** Implement Global Exception Handling in all microservices using `@ControllerAdvice` to provide standardized error responses to the frontend.
- **Containerization:** Create Dockerfiles and a `docker-compose.yml` to spin up Eureka, the API Gateway, Microservices, and PostgreSQL databases with a single `docker-compose up` command.
