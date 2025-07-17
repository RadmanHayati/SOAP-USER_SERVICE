**SOAP-Based Microservices Architecture Documentation**

**Author**: Radman Hayati\
**Technology Stack**: Java 17, Spring Boot 3, PostgreSQL, Docker, SOAP, Swagger, MapStruct, Testcontainers

---

### 📌 Project Overview

This project demonstrates a modular architecture with two Spring Boot microservices that communicate using the SOAP protocol. The services encapsulate independent responsibilities and are built using modern development practices and containerized infrastructure. This setup is designed to simulate a real-world service communication layer in a microservices environment.

> **🔀 Note:** The project is currently maintained on the `**staging**` branch.

---

### Microservices Description

1. **User Service** (Port: 8080)

   - Manages user creation and retrieval.
   - Exposes RESTful endpoints for user interaction.
   - Provides SOAP web service consumed by other services.
   - URL: [http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)

2. **Profile Service** (Port: 8081)

   - Manages user profile creation and retrieval.
   - Communicates with User Service using SOAP to fetch user details.
   - URL: [http://localhost:8081/api/swagger-ui/index.html](http://localhost:8081/api/swagger-ui/index.html)

---

### Technologies Used

- **Java 17**: Modern LTS version with strong support for records, sealed classes, and enhanced pattern matching.
- **Spring Boot 3**: Latest generation of the Spring ecosystem, providing simplified auto-configuration.
- **PostgreSQL**: Relational database system used for data persistence.
- **SOAP**: Inter-service communication is implemented via SOAP web services.
- **Swagger/OpenAPI**: API documentation and testing via integrated UI.
- **MapStruct**: Used for DTO and entity mapping to maintain clean separation of data layers.
- **Docker**: Services and databases are containerized for consistent local development.
- **Testcontainers**: Provides robust integration testing environments with ephemeral PostgreSQL instances.

---

### Running the Project Locally

#### Prerequisites

- Java 17+
- Maven
- Docker (and Docker Compose)

#### Steps

1. **Clone Repositories**

   - User Service: [https://github.com/RadmanHayati/SOAP-USER\_SERVICE](https://github.com/RadmanHayati/SOAP-USER_SERVICE)
   - Profile Service: [https://github.com/RadmanHayati/SOAP-PROFILE\_SERVICE](https://github.com/RadmanHayati/SOAP-PROFILE_SERVICE)

2. **Start PostgreSQL Containers** Each repository includes a `docker-compose.yml` file. Navigate to each and run:

   ```bash
   docker-compose up -d
   ```

3. **Build the Services**

   ```bash
   mvn clean install
   ```

   Run this in both `SOAP-USER_SERVICE` and `SOAP-PROFILE_SERVICE` directories.

4. **Run the Applications** Start each service using:

   ```bash
   ./mvnw spring-boot:run
   ```

   Alternatively, run JAR files from the `target` directory after build.

5. **Access API Documentation**

   - User Service: [http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)
   - Profile Service: [http://localhost:8081/api/swagger-ui/index.html](http://localhost:8081/api/swagger-ui/index.html)

---

### Default Users

Upon startup, the User Service is preloaded with the following users:

| ID | Name  | Email                                          |
| -- | ----- | ---------------------------------------------- |
| 1  | User1 | [user1@example.com](mailto\:user1@example.com) |
| 2  | User2 | [user2@example.com](mailto\:user2@example.com) |
| 3  | User3 | [user3@example.com](mailto\:user3@example.com) |

These can be used to test profile creation in the Profile Service.

---

### Inter-Service Communication

The Profile Service does not directly manage user data. Instead, it delegates identity validation and user data fetching to the User Service via SOAP. This allows for:

- Data encapsulation
- Service independence
- Robust contract-based communication

---

### API Usage Workflow

1. **Create a User** using User Service (POST `/api/users`).
2. **Create a Profile** for the user via Profile Service (POST `/api/profiles`).
   - This triggers a SOAP request to User Service to fetch user details.
   - Fetched data is stored and used in profile records.
3. **Retrieve Profile** using GET `/api/profiles/{userId}`.
4. Additional users can be created dynamically via Swagger UI.

---

### Unified Response Format & Error Management

All services follow a consistent response model containing a `result` object:

```json
{
  "result": {
    "title": "SUCCESS",
    "status": 200,
    "message": "Operation completed successfully",
    "level": "INFO"
  },
  "data": { ... }
}
```

Error handling is centralized and standardized. Common HTTP error codes are mapped to clear messages and returned with levels like `WARNING`, `BLOCKER`, etc., depending on severity. This ensures frontend systems can reliably interpret system responses.

---

### Summary

This project illustrates a scalable and clean microservices architecture using modern tools and practices. The use of SOAP for service communication, Docker for environment consistency, and uniform error handling demonstrates a mature and maintainable system suitable for enterprise-level applications.

---

**Sincerely Radman Hayati**
<div align="center"> <table> <tr> <td align="center"> <strong>Microservices</strong><br/> <img src="https://github.com/user-attachments/assets/07025255-1bf2-49c4-be37-faafc4279d38" alt="User Service Swagger UI" width="800"/> </td> </tr> <tr> <td align="center"> <strong>Swagger UI</strong><br/> <img src="https://github.com/user-attachments/assets/13801651-7eb2-48e8-b9e4-63ca8cd43280" alt="Profile Service Swagger UI" width="800"/> </td> </tr> <tr> <td align="center"> <strong>WSDL XSD Files</strong><br/> <img src="https://github.com/user-attachments/assets/831425dd-71f2-4d01-9ae7-ac1a1c1bb8b5" alt="Container logs" width="800"/> </td> </tr> </table> </div>
