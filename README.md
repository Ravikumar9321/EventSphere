# EventSphere

🛠️ Tech Stack
Backend:
├── Java 8+ (Streams, Lambdas, Collections)
├── Spring Boot & Spring MVC
├── Hibernate/JPA ORM
├── PostgreSQL Database
├── Maven Build Tool
└── Postman API Testing

Frontend:
├── React • HTML5 • CSS3 • JavaScript
├── React Router • Axios
└── VS Code

Backend (Production-Ready)
Full CRUD for Attendee, Event, Registration, Venue, Organizer entities

Complex relationships: One-to-Many, Many-to-One with proper @JoinColumn

RESTful APIs with proper HTTP status codes & JSON handling (@JsonIgnore)

@CreationTimestamp for registration & event tracking

Unique attendee management with contact/email tracking

Frontend (React Dashboard)
Responsive event listings & registration forms

React Router navigation: Events → Registration → Dashboard

Axios integration with Spring Boot REST APIs

Real-time data fetching with useState/useEffect hooks

Mobile-friendly HTML/CSS responsive design

🗄️ Database Schema & Relationships
Entities & Key Fields:

Attendee:

id (PK), name, email, contact

registration (One-to-Many)

Event:

id (PK), name, time (@CreationTimestamp), date, description

venue (Many-to-One), organizer (Many-to-One)

registrations (One-to-Many)

Organizer:

id (PK), name, email, organizer

events (One-to-Many)

Registration:

id (PK), date (@CreationTimestamp)

event (Many-to-One), attendee (Many-to-One)

Venue:

id (PK), name, location, capacity

events (One-to-Many)

🚀 Quick Start
Backend Setup
# Clone Backend
git clone https://github.com/Ravikumar9321/EventSphere
cd event-app

# Eclipse: → Right Project → Run
# APIs available: http://localhost:8080
# Create React App
npx create-react-app event-frontend
cd event-app

# Install Dependencies
npm install axios react-router-dom

# Start Frontend
npm start
# Runs on: http://localhost:3000

 database setup
spring.datasource.url=jdbc:postgresql://localhost:5432/event_management_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

📋 API Endpoints
| Method | Endpoint                         | Description                       | Frontend Integration    |
| ------ | -------------------------------- | --------------------------------- | ----------------------- |
| POST   | /api/venues                      | Create venue w/ capacity          | Venue Management Page   |
| POST   | /api/organizers                  | Create organizer                  | Organizer Dashboard     |
| POST   | /api/events                      | Create event w/ venue & organizer | Event Creation Form     |
| GET    | /api/events/venue/{id}           | Get events by venue               | Venue Events List       |
| POST   | /api/attendees                   | Create attendee                   | Attendee Registration   |
| POST   | /api/registrations               | Register attendee for event       | React Registration Form |
| GET    | /api/registrations/attendee/{id} | Get attendee registrations        | Attendee Dashboard      |

🧪 Testing
Backend: 100% CRUD coverage via Postman collections
Frontend: Manual testing + React Developer Tools
Integration: Axios API calls verified across all endpoints
Relationships: Tested @JsonIgnore serialization & @CreationTimestamp

📁 Project Structure
Backend: src/main/java/com/eventManagement/Entity/
├── Attendee.java      (name, email, contact, registration)
├── Events.java        (name, time, date, venue, organizer)
├── Organizer.java     (name, email, organizer, events)
├── Registration.java  (date, event, attendee)
└── Venue.java         (name, location, capacity, events)

Frontend: eventsphere-frontend/src/
├── components/
│   ├── EventList.js
│   ├── RegistrationForm.js
│   └── Dashboard.js
├── App.js (React Router)
└── index.js (Axios setup)

🔮 Future Enhancements
JWT Authentication (Admin/Attendee roles)
Event capacity validation & seat booking
Payment integration (Razorpay/Stripe)
Advanced React features 
Docker containerization
CI/CD pipeline setup
