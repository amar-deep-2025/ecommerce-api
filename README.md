# 🛒 Ecommerce API - Spring Boot Backend

A production-style Ecommerce Backend REST API** built using Java, Spring Boot, Spring Security, JWT, and MySQL**.

This project demonstrates a **phase-based development approach**, where each feature is implemented step-by-step like a real-world backend system.

---

# 🚀 Features

- User Registration & Login
- JWT Authentication
- Role-Based Authorization
- Product Management APIs
- Cart System
- Order Management
- Payment Flow
- Global Exception Handling
- Logging with SLF4J
- Swagger API Documentation
- Clean Layered Architecture

---

# 🏗️ Project Architecture

Controller → Service → Repository → Database

**Controller Layer**  
Handles HTTP requests and API endpoints.

**Service Layer**  
Contains business logic.

**Repository Layer**  
Handles database operations using Spring Data JPA.

**DTO Layer**  
Transfers request and response objects.

---

# 🧑‍💻 Tech Stack

| Technology | Usage |
|-----------|------|
Java | Backend language |
Spring Boot | Application framework |
Spring Security | Authentication & Authorization |
JWT | Token based authentication |
MySQL | Database |
Spring Data JPA | ORM |
Maven | Dependency management |
Swagger | API documentation |
Lombok | Boilerplate code reduction |
SLF4J | Logging |

---

# 📂 Project Structure


src
└── main
└── java
└── com.amar.fullstack.ecommerce_api
├── controller
├── services
├── repository
├── security
├── config
├── dto
├── exception
└── model


---

# 🔐 Authentication Flow

User Login  
↓  
Spring Security Authentication  
↓  
JWT Token Generated  
↓  
Token used for protected APIs  

---

# 📡 API Endpoints

## Authentication

POST /api/auth/register  
POST /api/auth/login  

---

## Products

POST /api/products  
GET /api/products  
PUT /api/products/{id}  
DELETE /api/products/{id}

Example Delete API Response


Product successfully deleted with id: 1


---

# 🧾 Example Request JSON

Create Product

```json
{
  "name": "Laptop",
  "description": "Gaming Laptop",
  "price": 75000,
  "stock": 10
}
🧱 Development Phases

Phase 1 → Core User CRUD
Phase 2 → Validation
Phase 3 → Security
Phase 4 → Role Based Security
Phase 5 → Product APIs
Phase 6 → Cart System
Phase 7 → Logging & Swagger
Phase 8 → Order Management
Phase 9 → Payment Flow

📌 Future Improvements

Email Notifications

Payment Gateway Integration

Microservices Architecture

Docker Deployment

CI/CD Pipeline

👨‍💻 Author

Amar Deep
Java Backend Developer

LinkedIn
https://www.linkedin.com/in/amar-maurya-backend/

GitHub
https://github.com/amar-deep-2025

⭐ Support
If you like this project, please give it a ⭐ on GitHub.
