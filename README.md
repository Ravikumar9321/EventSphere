# 📦 EventSphere – Event Management System

 production‑ready full‑stack application for managing Organizers, Venues, Events, Attendees, and Registrations, built with Spring Boot + React.js, featuring secure JWT authentication, Swagger API documentation, and PostgreSQL database integration.

---

## 🚀 Tech Stack

### Backend
- Java 21  
- Spring Boot 3.5.5  
- Spring Security (JWT Authentication)  
- Spring Data JPA / Hibernate  
- PostgreSQL  
- Swagger/OpenAPI 3.0  
- Maven  

### Frontend
- React.js  
- Axios (with interceptors for JWT authentication)  
- React Router  
- useState & useEffect  
- Responsive CSS  

### Tools

- Git & GitHub  
- Eclipse IDE  

---

## 🏗️ Architecture
**Frontend (React)** ➝ **REST API (Spring Boot)** ➝ **PostgreSQL Database**

Backend follows a clean layered architecture:
- **Controller Layer** – Handles HTTP requests  
- **Service Layer** – Business logic  
- **Repository Layer** – Database interaction  

---

## ✨ Features
-👥 Attendee Management – Register and track attendees with unique email/contact.
-🎟️ Event Management – Create, update, and manage events with venue & organizer mapping.
-🏢 Venue Management – Add and assign venues with capacity tracking.
-👔 Organizer Management – Manage event organizers and their assigned events.
-📋 Registration Management – Track attendee registrations for events.
-🔒 Secure Authentication – JWT login & protected routes.
-📘 API Documentation – Swagger UI with JWT integration.
-🧪 Testing – CRUD coverage via Postman collections.
---

## 🗄️ Database Design
**Entities:**
- Attendee → id, name, email, contact, registrations (One‑to‑Many)
-Event → id, name, date, time (@CreationTimestamp), description, venue (Many‑to‑One), organizer (Many‑to‑One), -registrations (One‑to‑Many)
-Organizer → id, name, email, events (One‑to‑Many)
-Registration → id, date (@CreationTimestamp), event (Many‑to‑One), attendee (Many‑to‑One)
-Venue → id, name, location, capacity, events (One‑to‑Many)
---

## 📁 Project Structure
| Path | Description |
|------|-------------|
| `backend/` | Spring Boot API |
| `entity/` | Attendee, Event, Organizer, Registration, Venue entities |
| `controller/` | REST Controllers |
| `service/` | Business Logic |
| `repository/` | JPA Repositories |
| `frontend/` | React Application |
| `management/` | EventList, RegistrationForm, Dashboard |
| `doc/` | Screenshots & Documentation |
| `README.md` | This file |

---

## 🔗 REST API Endpoints

### Event APIs
| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| GET    | `/api/events`      | Get all events  |
| POST   | `/api/events`      | Create event    |

### Attendee APIs
| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| GET    | `/api/attendee`    | Get all attendees |
| POST   | `/api/attendee`    | Add new attendee  |

### Registration APIs
| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| GET    | `/api/register`      | Get all registrations     |
| POST   | `/api/register`      | Register attendee for event |

---

## 🧪 How to Run Locally

### 1️⃣ Configure PostgreSQL
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/eventsphereDB
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
server.port=8080


 ###  2️⃣ Backend Setup (Eclipse)

-->   Import the backend project into Eclipse IDE
-->   Right‑click the project → Run As → Spring Boot App
-->    Backend runs on: http://localhost:8080

###  3️⃣ Frontend Setup
-->    cd frontend
-->   npm install
-->   npm start