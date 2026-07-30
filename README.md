# 🏠 Home Pocket — Backend

> REST API for Home Pocket, a shared household management app.

---

## 📋 About

This repository contains the backend for **Home Pocket**, responsible for all business logic, authentication, data persistence, and real-time communication between household members.

---

## 🛠️ Tech Stack

| Technology            | Usage |
|-----------------------|---|
| Java 21               | Main language |
| Spring Boot 3         | Main framework |
| Spring Security + JWT | Authentication and authorization |
| Spring Data JPA       | Data persistence |
| PostgreSQL            | Relational database |
| WebSocket (STOMP)     | Real-time communication |
| Docker                | Containerization |

---

## 🏗️ Project Structure

```text
src/main/java/com/homepocket/
├── auth/           # Authentication, JWT, and user registration
├── house/          # House and members module
├── market/         # Grocery list module
├── event/          # Events module
├── finance/        # Finance module
└── shared/         # Exceptions, DTOs, and common utilities
```

---

## 🚀 How to Run

### Prerequisites
- Java 17+
- Docker and Docker Compose

### Steps

```bash
# Clone the repository
git clone https://github.com/your-username/home-pocket-backend.git
cd home-pocket-backend

# Start the database
docker-compose up -d

# Run the application
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## 🔐 Authentication

The API uses **JWT Bearer Token**. After logging in, include the token in the header of all protected requests:

```text
Authorization: Bearer <your-token>
```

---

## 📡 Endpoints

### Auth
```text
POST   /api/auth/register         → User registration
POST   /api/auth/login            → Login and token generation
```

### Houses
```text
POST   /api/houses                → Create house
POST   /api/houses/{id}/invite    → Invite member
GET    /api/houses/{id}/members   → List members
```

### Grocery List
```text
GET    /api/houses/{id}/market         → List items
POST   /api/houses/{id}/market         → Add item
PATCH  /api/market/{itemId}            → Update status or assignee
DELETE /api/market/{itemId}            → Remove item
```

### Events
```text
GET    /api/houses/{id}/events    → List events
POST   /api/houses/{id}/events    → Create event
PATCH  /api/events/{eventId}      → Update event
DELETE /api/events/{eventId}      → Remove event
```

### Finance
```text
GET    /api/houses/{id}/finances  → List expenses
POST   /api/houses/{id}/finances  → Register expense
DELETE /api/finances/{id}         → Remove record
```

---

## 📌 Roadmap

- [ ] JWT Authentication
- [ ] Houses and invitations module
- [ ] Real-time grocery list (WebSocket)
- [ ] Events module
- [ ] Finance module (phase 1)
- [ ] Push notifications
- [ ] Expense splitting between household members

---

## 👤 Author

Developed by **Joao Ehlers** as a portfolio project.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/joão-ehlers-37037511jx)
[![GitHub](https://img.shields.io/badge/GitHub-black?style=flat&logo=github)](https://github.com/joao-ehlers)

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.