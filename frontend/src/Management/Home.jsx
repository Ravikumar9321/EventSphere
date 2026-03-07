import React, { useState } from "react";
import { Link } from "react-router-dom";

function Home() {
  const [hoveredCard, setHoveredCard] = useState(null);

  const cards = [
    { emoji: "👥", title: "Attendees", path: "/attendee", desc: "Manage attendee details" },
    { emoji: "🎉", title: "Events", desc: "Create upcoming events",path:"/eventManage" },
  { emoji: "📒", title: "Registration_Manager",path:"/registers", desc: "manage Registrations" },
    { emoji: "👨‍💼", title: "Organizers", desc: "View organizers",path:"/orgManage" },
    { emoji: "📍", title: "Venues", desc: "Browse venues" ,path:"/venueManage"},
    { emoji: "📝", title: "Registration", desc: "Attendee registration", path: "/register" }
  ];

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>EventSphere</h1>
      <p style={styles.subtitle}>
        Complete event platform built with React & Spring Boot
      </p>

      <div style={styles.cardsGrid}>
        {cards.map((card, index) => (
          <div
            key={index}
            style={{
              ...styles.card,
              ...(hoveredCard === index ? styles.cardHovered : {}),
            }}
            onMouseEnter={() => setHoveredCard(index)}
            onMouseLeave={() => setHoveredCard(null)}
          >
            {card.path ? (
              <Link to={card.path} style={styles.link}>
                <span style={styles.emoji}>{card.emoji}</span>
                <h3 style={styles.cardTitle}>{card.title}</h3>
                <p style={styles.cardText}>{card.desc}</p>
              </Link>
            ) : (
            
              <>
                <span style={styles.emoji}>{card.emoji}</span>
                <h3 style={styles.cardTitle}>{card.title}</h3>
                <p style={styles.cardText}>{card.desc}</p>
              </>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

const styles = {
  container: {
    minHeight: "100vh",
    background: "linear-gradient(135deg, #ff6b6b 0%, #4ecdc4 50%, #45b7d1 100%)",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    padding: "2rem",
    fontFamily: "system-ui, -apple-system, sans-serif",
  },
  link: {
    textDecoration: "none",
    color: "inherit",
    display: "block",
    height: "100%",
    width: "100%",
  },
  title: {
    fontSize: "3rem",
    fontWeight: "800",
    margin: "0 0 1.5rem 0",
    textAlign: "center",
    color: "white",
  },
  subtitle: {
    fontSize: "1.3rem",
    color: "#f8fafc",
    textAlign: "center",
    marginBottom: "3rem",
    maxWidth: "500px",
  },
  cardsGrid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
    gap: "1.5rem",
    maxWidth: "1000px",
    width: "100%",
  },
  card: {
    background: "rgba(255, 255, 255, 0.2)",
    borderRadius: "16px",
    padding: "1.8rem 1.5rem",
    textAlign: "center",
    color: "white",
    transition: "all 0.3s ease",
    border: "1px solid rgba(255, 255, 255, 0.4)",
    boxShadow: "0 8px 25px rgba(0, 0, 0, 0.15)",
    cursor: "pointer",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
  },
  cardHovered: {
    transform: "translateY(-8px)",
    boxShadow: "0 15px 35px rgba(0, 0, 0, 0.25)",
    background: "rgba(255, 255, 255, 0.3)",
  },
  emoji: {
    fontSize: "3rem",
    display: "block",
    marginBottom: "1rem",
  },
  cardTitle: {
    fontSize: "1.4rem",
    fontWeight: "700",
    margin: "0 0 0.5rem 0",
    color: "white",
  },
  cardText: {
    fontSize: "0.95rem",
    lineHeight: "1.5",
    color: "#f1f5f9",
    margin: 0,
  }
};

export default Home;
