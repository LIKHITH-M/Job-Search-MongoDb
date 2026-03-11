# 📋 JobSearch MongoDB — React & Spring Boot Application

## Overview
This is a Full-Stack Job Search Application showcasing seamless integration between a Java Spring Boot backend and a React frontend, powered by **MongoDB Atlas** as the cloud database provider. Instead of a traditional local database, this project connects to a live MongoDB Atlas cluster, utilizing Spring Data MongoDB to securely store, retrieve, and map dynamic BSON documents representing job postings, required technologies, and experience profiles directly to our React UI.

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
