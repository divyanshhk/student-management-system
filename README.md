# 🎓 Student Management System
 
A full-stack **REST API backend** built with **Spring Boot** that manages student data with secure authentication, role-based access, and clean layered architecture.
 
---
 
## 🚀 Tech Stack
 
| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.5.7 |
| Security | Spring Security + JWT |
| Database | PostgreSQL |
| ORM | Spring Data JPA (Hibernate) |
| Validation | Spring Boot Validation |
| Mapping | ModelMapper |
| API Docs | Swagger UI (SpringDoc OpenAPI) |
| Build Tool | Maven |
| Container | Docker |
 
---
 
## ✨ Features
 
- 🔐 **JWT Authentication** — Secure login with token-based auth
- 👥 **Student CRUD** — Create, Read, Update, Delete student records
- ✅ **Input Validation** — Request validation using `@Valid` annotations
- 📄 **Pagination & Sorting** — Efficient data retrieval for large datasets
- 🗂️ **DTO Pattern** — Clean separation between API layer and database layer using ModelMapper
- 📘 **Swagger UI** — Interactive API documentation at `/swagger-ui.html`
- 🐳 **Dockerized** — Ready to run in any environment with Docker
---
 
## 🏗️ Project Architecture
 
```
src/
└── main/
    └── java/
        └── com/divyansh/studentmanagementsystem/
            ├── controller/       # REST endpoints — handles HTTP requests/responses
            ├── service/          # Business logic layer
            ├── repository/       # Database access layer (JPA Repositories)
            ├── entity/           # Database table mappings (@Entity classes)
            ├── dto/              # Data Transfer Objects for API communication
            ├── security/         # JWT filter, Security config
            └── exception/        # Global exception handling
```
 
### 🔄 Data Flow
 
```
HTTP Request
    ↓
JWT Filter (validates token)
    ↓
Controller (@RestController)
    ↓
Service (@Service) — business logic
    ↓
Repository (@Repository) — JPA query
    ↓
PostgreSQL Database
    ↑
Entity → ModelMapper → DTO → JSON Response
```
 
---
 
## ⚙️ Getting Started
 
### Prerequisites
 
- Java 17+
- PostgreSQL running locally
- Maven 3.x
### 1. Clone the repository
 
```bash
git clone https://github.com/divyanshhk/student-management-system.git
cd student-management-system
```
 
### 2. Configure the database
 
Open `src/main/resources/application.properties` and update:
 
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/student_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
 
### 3. Run the application
 
```bash
./mvnw spring-boot:run
```
 
The server starts at `http://localhost:8080`
 
---
 
## 🐳 Running with Docker
 
```bash
# Build the Docker image
docker build -t student-management-system .
 
# Run the container
docker run -p 8080:8080 student-management-system
```
 
---
 
## 📘 API Documentation
 
Once the app is running, visit:
 
```
http://localhost:8080/swagger-ui.html
```
 
### Key Endpoints
 
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register a new user | ❌ |
| POST | `/api/auth/login` | Login and get JWT token | ❌ |
| GET | `/api/students` | Get all students (paginated) | ✅ |
| GET | `/api/students/{id}` | Get student by ID | ✅ |
| POST | `/api/students` | Add a new student | ✅ |
| PUT | `/api/students/{id}` | Update student details | ✅ |
| DELETE | `/api/students/{id}` | Delete a student | ✅ |
 
### How to Authenticate
 
1. Call `POST /api/auth/login` with valid credentials
2. Copy the `token` from the response
3. In Swagger UI, click **Authorize** and paste: `Bearer <your_token>`
4. All protected endpoints are now accessible
---
 
## 🧠 What I Learned Building This
 
- How Spring Security filter chain works with JWT
- Difference between Entity and DTO and why separation matters
- How `@Transactional` ensures data consistency
- Pagination using `Pageable` in Spring Data JPA
- How Docker containerizes a Spring Boot app
---
 
## 🔧 Known Improvements (Planned)
 
- [ ] Add unit tests with JUnit & Mockito
- [ ] Add role-based authorization (ADMIN / STUDENT roles)
- [ ] Connect a frontend (HTML/CSS/JS)
- [ ] Deploy to cloud (Railway / Render)
---
 
## 👨‍💻 Author
 
**Divyansh**
- GitHub: [@divyanshhk](https://github.com/divyanshhk)
---
 
> 💡 *This project was built as part of my backend development learning journey with Java and Spring Boot.*
