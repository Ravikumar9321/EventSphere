import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";


function VenueForm() {
  const [loading, setLoading] = useState(false);
  const [venue, setVenue] = useState({
    name: "",
    capacity: "",
    location: ""
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setVenue({
      ...venue,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      await api.post("http://localhost:8080/api/venues", venue);
      alert("✅ Venue added successfully!");
      navigate(-1);
    } catch (error) {
      alert("❌ Failed to add venue");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={Styles.page}>
      <div style={Styles.card}>
        <h1 style={Styles.title}>➕ Add New Venue</h1>
        <p style={Styles.subtitle}>
          Fill venue details for your event management system
        </p>
        
        <form 
          onSubmit={handleSubmit} 
          style={Styles.form}
          className={loading ? 'loading' : ''}
        >
          <div>
            <label style={Styles.label}>
              📍 <strong>Venue Name</strong>
            </label>
            <input 
              name="name"
              value={venue.name}
              onChange={handleChange}
              placeholder="e.g. Grand Ballroom"
              style={Styles.input}
              required
              disabled={loading}
            />
          </div>

          <div>
            <label style={Styles.label}>
              👥 <strong>Capacity</strong>
            </label>
            <input 
              name="capacity"
              type="number"
              min="1"
              value={venue.capacity}
              onChange={handleChange}
              placeholder="e.g. 250"
              style={Styles.input}
              required
              disabled={loading}
            />
          </div>

      
          <div>
            <label style={Styles.label}>
              🗺️ <strong>Location</strong>
            </label>
            <input 
              name="location"
              value={venue.location}
              onChange={handleChange}
              placeholder="e.g. Trichy Downtown"
              style={Styles.input}
              required
              disabled={loading}
            />
          </div>

          
          <div style={Styles.buttonGroup}>
            <button 
              type="submit" 
              disabled={loading}
              style={Styles.submitBtn}
            >
              {loading ? "⏳ Adding..." : "✅ Add Venue"}
            </button>
            
            <button 
              type="button"
              onClick={() => navigate(-1)}
              disabled={loading}
              style={Styles.cancelBtn}
            >
              ❌ Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
const Styles = {
  page: {
    minHeight: '100vh',
    background: 'linear-gradient(135deg, #f1f5f9 0%, #e2e8f0 100%)',
    padding: '20px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    fontFamily: '-apple-system, BlinkMacSystemFont, sans-serif'
  },
  card: {
    background: 'white',
    padding: '40px',
    borderRadius: '16px',
    boxShadow: '0 10px 40px rgba(0,0,0,0.1)',
    width: '100%',
    maxWidth: '480px',
    border: '1px solid #e2e8f0'
  },
  title: {
    fontSize: '32px',
    fontWeight: '700',
    color: '#1e293b',
    textAlign: 'center',
    marginBottom: '8px',
    background: 'linear-gradient(135deg, #3b82f6, #8b5cf6)',
    WebkitBackgroundClip: 'text',
    WebkitTextFillColor: 'transparent',
    backgroundClip: 'text'
  },
  subtitle: {
    textAlign: 'center',
    color: '#64748b',
    marginBottom: '30px',
    fontSize: '16px'
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '24px'
  },
  label: {
    fontWeight: '600',
    color: '#334155',
    marginBottom: '10px',
    fontSize: '15px',
    display: 'flex',
    alignItems: 'center',
    gap: '8px'
  },
  input: {
    width: '100%',
    padding: '14px 18px',
    border: '2px solid #e2e8f0',
    borderRadius: '12px',
    fontSize: '16px',
    backgroundColor: '#fcfcfc',
    boxSizing: 'border-box'
  },
  buttonGroup: {
    display: 'flex',
    gap: '16px',
    marginTop: '8px'
  },
  submitBtn: {
    background: 'linear-gradient(135deg, #3b82f6 0%, #1d4ed8 100%)',
    color: 'white',
    flex: 1,
    padding: '14px 28px',
    borderRadius: '12px',
    fontSize: '16px',
    fontWeight: '600',
    border: 'none',
    cursor: 'pointer'
  },
  cancelBtn: {
    background: 'white',
    color: '#3b82f6',
    flex: 1,
    padding: '14px 28px',
    borderRadius: '12px',
    fontSize: '16px',
    fontWeight: '600',
    border: '2px solid #3b82f6',
    cursor: 'pointer'
  }
};

export default VenueForm;
