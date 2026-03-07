import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function ARegistration() {
    const navigate = useNavigate();
    const [attendee, setAttendee] = useState({
        name: "",
        email: "",
        contact: "" 
    });

    const handleChange = (e) => {
        setAttendee({ ...attendee, [e.target.name]: e.target.value });
    };

    const handleSubmit = async () => {
        try {
            await axios.post(`http://localhost:8080/api/attendee`, attendee);
            alert("Registration Done");
        } catch (error) {
            alert("Registration Failed");
        }
    };

    return (
        <div style={styles.container}>
            <button 
                style={styles.backButton}
                onClick={() => navigate("/")}
            >
                ← Back to Home
            </button>
            <div style={styles.registerSection}>
                <h2 style={styles.registerTitle}>Attendee Registration</h2>
                <form style={styles.form} onSubmit={handleSubmit}>
                    <input 
                        
                        name="name"
                        value={attendee.name}
                        placeholder="Full Name *"
                        style={styles.input} 
                        onChange={handleChange}
                        required
                    />
                    <input 
                
                        name="email"
                        value={attendee.email}
                        placeholder="Email *" 
                        onChange={handleChange}
                        style={styles.input} 
                        required 
                    />
                    <input 
                        
                        name="contact"
                        value={attendee.contact}
                        placeholder="Contact Number *" 
                        style={styles.input}
                        onChange={handleChange} 
                        required
                    />
                    <button type="submit" style={styles.button}>
                        Submit Registration
                    </button>
                </form>
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
    registerSection: {
        padding: "3rem 2.5rem",
        background: "rgba(255, 255, 255, 0.95)",
        borderRadius: "20px",
        maxWidth: "450px",
        width: "100%",
        textAlign: "center",
        boxShadow: "0 20px 60px rgba(0, 0, 0, 0.15)",
        marginTop: "2rem",
    },
    registerTitle: {
        fontSize: "1.8rem",
        color: "#1e293b",
        marginBottom: "1.5rem",
    },
    form: {
        display: "flex",
        flexDirection: "column",
        gap: "1.2rem",
    },
    input: {
        padding: "1rem 1.2rem",
        border: "1px solid #e2e8f0",
        borderRadius: "12px",
        fontSize: "1rem",
        transition: "border-color 0.3s ease",
    },
    button: {
        padding: "1rem 1.5rem",
        background: "linear-gradient(135deg, #ff6b6b 0%, #4ecdc4 100%)",
        color: "white",
        border: "none",
        borderRadius: "12px",
        fontSize: "1.1rem",
        fontWeight: "600",
        cursor: "pointer",
        transition: "all 0.3s ease",
    },
    backButton: {
        alignSelf: "flex-start",
        padding: "0.8rem 1.5rem",
        background: "rgba(255,255,255,0.2)",
        color: "white",
        border: "none",
        borderRadius: "10px",
        cursor: "pointer",
        fontSize: "1rem",
        marginBottom: "2rem",
    }
};

export default ARegistration;
