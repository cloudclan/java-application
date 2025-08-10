# 🏥 Healthcare Management System

A modern, secure healthcare management application built with Spring Boot that allows users to track and manage their health records through a REST API and web interface.

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [Database Configuration](#-database-configuration)
- [Running the Application](#-running-the-application)
- [API Documentation](#-api-documentation)
- [Web Interface](#-web-interface)
- [Security Features](#-security-features)
- [Project Structure](#-project-structure)
- [Testing](#-testing)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### 🔐 Authentication & Authorization
- **JWT-based authentication** for secure API access
- **User registration and login** with encrypted passwords
- **Stateless session management**
- **Role-based access control**

### 📊 Health Record Management
- **Create health records** with comprehensive medical data
- **View personal health history** with filtering and search
- **Update and delete records** with proper authorization
- **Track multiple health metrics**:
  - Weight measurements
  - Blood pressure readings
  - Exercise duration
  - Personal notes and observations

### 🌐 Web Interface
- **Modern, responsive UI** built with HTML5, CSS3, and JavaScript
- **Real-time data updates** without page refresh
- **Mobile-friendly design** for on-the-go access
- **Intuitive user experience** with clear navigation

## 🛠 Technology Stack

### Backend
- **Java 17** - Modern Java features and performance
- **Spring Boot 3.5.4** - Rapid application development framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database abstraction layer
- **Hibernate** - Object-relational mapping
- **MySQL** - Relational database
- **JWT** - JSON Web Tokens for stateless authentication
- **Maven** - Dependency management and build tool

### Frontend
- **HTML5** - Semantic markup
- **CSS3** - Modern styling with gradients and animations
- **JavaScript (ES6+)** - Dynamic functionality and API integration
- **Fetch API** - Modern HTTP client for API calls

### Development Tools
- **Lombok** - Reduces boilerplate code
- **Spring Boot DevTools** - Development convenience
- **H2 Console** - Database management (optional)

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6** or higher
- **MySQL 8.0** or higher
- **Git** (for cloning the repository)

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd health-care
```

### 2. Database Setup
Create a MySQL database named `healthcare`:
```sql
CREATE DATABASE healthcare;
```

### 3. Configure Database Connection
Update `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/healthcare
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Build the Project
```bash
./mvnw clean install
```

## 🗄 Database Configuration

The application uses MySQL with the following configuration:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/healthcare
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Database Schema
The application automatically creates the following tables:
- **`customer`** - User accounts and authentication
- **`heath_care`** - Health records and medical data

## ▶ Running the Application

### Using Maven Wrapper
```bash
./mvnw spring-boot:run
```

### Using Maven
```bash
mvn spring-boot:run
```

### Using JAR File
```bash
./mvnw clean package
java -jar target/health-care-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "password": "secure_password123"
}
```

**Response:**
```json
{
  "id": 1,
  "username": "john_doe",
  "password": "$2a$10$...",
  "records": null
}
```

#### Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "secure_password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huX2RvZSIsImV4cCI6MTc1NDI1NDQyNCwiaWF0IjoxNzU0MjE4NDI0fQ..."
}
```

### Health Records Endpoints

All health endpoints require authentication via JWT token in the Authorization header:
```http
Authorization: Bearer <your-jwt-token>
```

#### Get All Health Records
```http
GET /api/health
Authorization: Bearer <jwt-token>
```

**Response:**
```json
[
  {
    "id": 1,
    "diagnosis": "120/80",
    "notes": "FEELING GOOD, GOOD SLEEP",
    "userName": "john_doe"
  }
]
```

#### Create Health Record
```http
POST /api/health
Content-Type: application/json
Authorization: Bearer <jwt-token>

{
  "date": "2024-01-15",
  "weight": 70.5,
  "bloodPressure": "120/80",
  "exerciseMinutes": 30,
  "notes": ["feeling good", "good sleep"]
}
```

**Response:**
```json
{
  "message": "Record created successfully"
}
```

#### Delete Health Record
```http
DELETE /api/health/{id}
Authorization: Bearer <jwt-token>
```

**Response:** `204 No Content`

## 🌐 Web Interface

### Accessing the UI
1. Start the application
2. Open your browser and navigate to `http://localhost:8080`
3. You'll see the login/register page

### Features
- **User Registration**: Create new accounts
- **User Login**: Authenticate with existing credentials
- **Health Dashboard**: View and manage health records
- **Add Records**: Create new health entries
- **Delete Records**: Remove unwanted records
- **Responsive Design**: Works on desktop and mobile

### UI Screenshots
- Login/Register page with modern gradient design
- Health dashboard with form and records list
- Mobile-responsive layout

## 🔒 Security Features

### JWT Authentication
- **Stateless authentication** using JSON Web Tokens
- **Token expiration** (10 hours by default)
- **Secure token validation** with signature verification

### Password Security
- **BCrypt encryption** for password hashing
- **Salt generation** for additional security
- **Secure password storage** in database

### API Security
- **CORS configuration** for cross-origin requests
- **CSRF protection** (disabled for API endpoints)
- **Input validation** and sanitization
- **Authorization headers** required for protected endpoints

## 📁 Project Structure

```
health-care/
├── src/
│   ├── main/
│   │   ├── java/com/project/health_care/
│   │   │   ├── Config/
│   │   │   │   ├── JwtConfig.java          # JWT filter configuration
│   │   │   │   └── WebConfig.java          # Security configuration
│   │   │   ├── Controller/
│   │   │   │   ├── HealthCareController.java # Health records API
│   │   │   │   └── UserController.java     # Authentication API
│   │   │   ├── Models/
│   │   │   │   ├── Customer.java           # User entity
│   │   │   │   └── HeathCare.java          # Health record entity
│   │   │   ├── Repository/
│   │   │   │   ├── HealthCareRepository.java # Health data access
│   │   │   │   └── UserRepository.java     # User data access
│   │   │   ├── Service/
│   │   │   │   ├── HealthCareService.java  # Health business logic
│   │   │   │   └── UserService.java        # User business logic
│   │   │   ├── Utils/
│   │   │   │   ├── CustomUserDetailsService.java # Spring Security integration
│   │   │   │   └── JwtUtil.java            # JWT utilities
│   │   │   └── HealthCareApplication.java  # Main application class
│   │   └── resources/
│   │       ├── application.properties      # Configuration
│   │       └── static/
│   │           └── index.html              # Web interface
│   └── test/                               # Test files
├── pom.xml                                 # Maven configuration
└── README.md                               # This file
```

## 🧪 Testing

### Running Tests
```bash
./mvnw test
```

### API Testing with curl

#### Register a new user
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "password123"}'
```

#### Login and get token
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "password123"}'
```

#### Create health record
```bash
curl -X POST http://localhost:8080/api/health \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your-jwt-token>" \
  -d '{
    "date": "2024-01-15",
    "weight": 70.5,
    "bloodPressure": "120/80",
    "exerciseMinutes": 30,
    "notes": ["feeling good", "good sleep"]
  }'
```

#### Get all health records
```bash
curl -X GET http://localhost:8080/api/health \
  -H "Authorization: Bearer <your-jwt-token>"
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Add unit tests for new features
- Update documentation for API changes
- Ensure security best practices

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

If you encounter any issues or have questions:

1. Check the [Issues](../../issues) page for existing solutions
2. Create a new issue with detailed information
3. Include error logs and steps to reproduce

## 🔮 Future Enhancements

- [ ] Health analytics and trends
- [ ] Export health data to PDF/CSV
- [ ] Integration with health devices
- [ ] Push notifications for health reminders
- [ ] Multi-language support
- [ ] Advanced reporting and charts
- [ ] Family member health tracking
- [ ] Integration with external health APIs

---

**Built with ❤️ using Spring Boot and modern web technologies** 