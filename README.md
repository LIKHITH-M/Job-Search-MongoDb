# 📋 JobSearch MongoDB — React & Spring Boot Application


## Overview
This project is a **full-stack Job Search application** designed to demonstrate the integration of a **modern Java backend, a dynamic React frontend, and a cloud-based NoSQL database**. The application enables users to **create, browse, and search job postings** that contain information such as job profile, required technologies, experience level, and detailed descriptions.

The backend is built using **Spring Boot**, which exposes REST APIs responsible for handling business logic and database communication. Instead of relying on a traditional relational database, the application uses **MongoDB** hosted on **MongoDB Atlas**. This allows the system to store flexible **BSON documents** representing job postings, which can contain arrays (such as technology stacks) and descriptive fields without requiring rigid schemas.

Each job document is mapped from MongoDB to Java **POJO classes** using Spring Data MongoDB, allowing the backend to easily perform CRUD operations and expose structured JSON responses to the frontend.

On the frontend side, **React** provides an interactive UI where users can view job listings, create new postings, and perform searches. Communication between the frontend and backend is handled through **Axios**, which sends HTTP requests and receives JSON responses that are dynamically rendered in the interface.

A key feature of this project is the implementation of **MongoDB Atlas Search using aggregation pipelines**. Instead of simple database queries, the backend leverages **aggregation-based search** to perform advanced full-text searches across multiple fields such as job descriptions, profiles, and technology stacks. This enables the application to return **relevant job results based on keywords**, sort them by experience level, and limit the number of results returned to the user. By integrating Atlas Search with aggregation stages such as `$search`, `$sort`, and `$limit`, the system demonstrates how modern search capabilities can be built directly on top of MongoDB without requiring an external search engine.

Overall, the project illustrates a **modern full-stack architecture**, where a decoupled React frontend communicates with a Spring Boot REST API, which in turn interacts with a scalable MongoDB Atlas cluster. It highlights how cloud databases, RESTful services, and aggregation-based search can be combined to build responsive and scalable web applications for managing dynamic datasets like job listings.

## 🏗️ Architecture & Request Flow
1. Client sends a request to the React Frontend.
2. The Frontend routes API requests to the Spring Boot REST API safely.
3. The Spring Boot backend uses `Spring Data MongoDB` to fetch and store job profiles, descriptions, required technologies, and experience levels against a MongoDB cluster.
4. Data is seamlessly mapped from MongoDB `BSON` documents to Java objects and served as JSON payloads to React.

## 📦 Tech Stack
| Technology | Purpose |
| :--- | :--- |
| **Java 21+** | Main backend programming language |
| **Spring Boot** | Backend application framework |
| **React** | Frontend UI interface |
| **MongoDB** | NoSQL Document Database |
| **Axios** | HTTP requests from React to Spring Boot |
| **Maven** | Build & dependency management |

---

## 🚀 How to Run the Application

Follow these steps to safely start both frontend and backend servers.

### Prerequisites:
1. Make sure you have **Java 17/21** or above installed.
2. Make sure you have **Node.js** installed.
3. Have a MongoDB cluster (e.g., MongoDB Atlas) ready to connect.

### Step-by-Step Execution:

**1. Configure the Database & Ports:**
Navigate to `SpringMongoDB/src/main/resources/application.properties` and replace the placeholders to match your MongoDB credentials and preferred backend localhost port.
*Note: Make sure to also update the identical backend port placeholder inside the React frontend files (`Feed.js` and `Create.js`) so they can successfully communicate!*

**2. Start the Spring Boot Backend:**
Open a terminal, navigate to the `SpringMongoDB` folder and run:
```bash
./mvnw spring-boot:run
```

**3. Start the React Frontend:**
Open a new terminal, navigate to the `UISpringMongodb-main` folder and run:
```bash
npm install
npm start
```

### Verification:
Go to your browser and open **`http://localhost:3000`** to view the app!

---

## 🔮 Future Improvements & Roadmap

To make the application more robust and user-friendly, the following features and technical improvements are planned:

### Frontend Enhancements
- **Dynamic Filtering:** Add a filter button/sidebar to easily sort and filter job postings by exact categories, required experience, and specific tech stacks.
- **Pagination:** Implement pagination on the frontend to handle large lists of data instead of fetching all records at once.
- **Search Optimization:** Improve the current text-based search to use a modern datagrid with debounce.

### Backend Improvements
- **Remove Hardcoded Values:** Eliminate hardcoded constants inside controllers (such as fixed ascending sorts or hardcoded SQL/MongoDB constraints) and convert them to dynamic `application.properties` configurations or generic query parameters.
- **Improved Logging & Tracing:** Integrate standardized logging tools to monitor exactly how requests flow through the application.
- **Error Handling:** Implement Global Exception Handling in the Spring Boot backend using `@ControllerAdvice` to provide clean, standardized JSON error responses back to the frontend.
