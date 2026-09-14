import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api/api";

function EventReg(){
    const { id } = useParams();
    const navigate = useNavigate();
   
    const [event, setEvent] = useState(null);
    const [venue, setVenue] = useState(null);
    const [organizer, setOrganizer] = useState(null);
    const [phoneNumber, setPhoneNumber] = useState('');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchRegisterDetails = async () => {
            setLoading(true);
            try {
                const eventRes = await api.get(`http://localhost:8080/api/events/${id}`);
                const eventData = eventRes.data.data || eventRes.data;
                
                setEvent(eventData);

                if (eventData?.venue?.id) {
                    const venueRes = await api.get(`http://localhost:8080/api/venues/${eventData.venue.id}`);
                    setVenue(venueRes.data.data || venueRes.data);
                }

                if (eventData?.organizer?.id) {
                    const orgRes = await api.get(`http://localhost:8080/api/organizers/${eventData.organizer.id}`);
                    setOrganizer(orgRes.data.data || orgRes.data);
                }
            } catch (error) {
                console.error("Failed to load details:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchRegisterDetails();
    }, [id]);
const handlePhoneSearch = async () => {
  if (!phoneNumber.trim() || !event?.id) {
    alert("Please wait for event to load or enter valid phone number");
    return; 
  }

  try {
    const response = await api.get(`http://localhost:8080/api/attendee/contact/${phoneNumber}`);
    const attendeeData = response.data.data;

    if (!attendeeData) {
      alert(response.data.message || "No attendee found with this phone number");
      return;
    }

    // ✅ Proceed with registration
    await api.post(`http://localhost:8080/api/register/${event.id}/${attendeeData.id}`, {
      eventId: event.id,
      attendeeId: attendeeData.id
    });
    alert("✅ Event Registration successful!");
    setPhoneNumber('');
  } catch (error) {
     const errorMsg = error.response?.data?.message || "Phone number not registered";
  alert(`❌ ${errorMsg}`);

    if (window.confirm("Do you want to register now?")) {
      navigate("/register");
    } else {
      alert("Booking cancelled");
      navigate(-1);
    }
  }
};

    if (loading) return <div style={styles.loadingContainer}>
        <div style={styles.loadingSpinner}></div>
        <div>Loading registration details...</div>
    </div>;
    
    if (!event) return <div style={styles.errorContainer}>
        <div style={styles.errorIcon}>⚠️</div>
        <h2>Event Not Found</h2>
        <button onClick={() => navigate(-1)} style={styles.errorBtn}>← Back</button>
    </div>;

    return (
        <div style={styles.container}>
            <div style={styles.header}>
                <button onClick={() => navigate(-1)} style={styles.backButton}>
                    ← Back to Events
                </button>
            </div>

            <div style={styles.contentGrid}>
                <div style={styles.primaryCard}>
                    <div style={styles.cardHeader}>
                        <div style={styles.cardIcon}>📱</div>
                    </div>
                    <div style={styles.searchSection}>
                        <div style={styles.inputGroup}>
                            <input 
                                type="tel"  
                                placeholder="Enter Registered Phone Number"
                                value={phoneNumber}
                                onChange={(e) => setPhoneNumber(e.target.value)}
                                style={styles.phoneInput}
                            />
                            <button 
                                onClick={handlePhoneSearch}
                                disabled={!phoneNumber.trim() || !event?.id} 
                                style={
                                    styles.searchBtn}
                            >
                                🎫 Book
                            </button>
                        </div>
                    </div>
                </div>

                
                {event && (
                    <div style={styles.infoCard}>
                        <div style={styles.cardHeader}>
                            <div style={styles.cardIcon}>🎉</div>
                            <h3 style={styles.cardTitle}>Event Details</h3>
                        </div>
                        <div style={styles.cardContent}>
                            <div style={styles.detailGrid}>
                                <DetailItem label="ID" value={event.id} />
                                <DetailItem label="Name" value={event.name} />
                                <DetailItem label="Date" value={formatDate(event.date)} />
                                <DetailItem label="Time" value={event.time} />
                                <DetailItem label="Description" value={event.description} />
                            </div>
                        </div>
                    </div>
                )}

        
                {venue && (
                    <div style={styles.infoCard}>
                        <div style={styles.cardHeader}>
                            <div style={styles.cardIcon}>📍</div>
                            <h3 style={styles.cardTitle}>Venue Details</h3>
                        </div>
                        <div style={styles.cardContent}>
                            <div style={styles.detailGrid}>
                                <DetailItem label="ID" value={venue.id} />
                                <DetailItem label="Name" value={venue.name} />
                                <DetailItem label="Location" value={venue.location} />
                                <DetailItem label="Capacity" value={venue.capacity} />
                            </div>
                        </div>
                    </div>
                )}

        
                {organizer && (
                    <div style={styles.infoCard}>
                        <div style={styles.cardHeader}>
                            <div style={styles.cardIcon}>👨‍💼</div>
                            <h3 style={styles.cardTitle}>Organizer Details</h3>
                        </div>
                        <div style={styles.cardContent}>
                            <div style={styles.detailGrid}>
                                <DetailItem label="ID" value={organizer.id} />
                                <DetailItem label="Name" value={organizer.name} />
                                <DetailItem label="Company" value={organizer.organizer} />
                                <DetailItem label="Email" value={organizer.email} />
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

const DetailItem = ({ label, value }) => (
    <div style={styles.detailItem}>
        <span style={styles.detailLabel}>{label}:</span>
        <span style={styles.detailValue}>{value || 'N/A'}</span>
    </div>
);

const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    try {
        return new Date(dateString).toLocaleDateString('en-IN', {
            day: '2-digit',
            month: 'short',
            year: 'numeric'
        });
    } catch {
        return dateString;
    }
};
const styles = {
    container: {
        padding: '2.5rem',
        maxWidth: '1200px',
        margin: '0 auto',
        fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif',
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        minHeight: '100vh'
    },
    header: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginBottom: '3rem',
        flexWrap: 'wrap',
        gap: '1rem'
    },
    backButton: {
        background: 'linear-gradient(135deg, #ef4444 0%, #dc2626 100%)',
        color: 'white',
        border: 'none',
        padding: '1rem 2rem',
        borderRadius: '12px',
        cursor: 'pointer',
        fontSize: '1rem',
        fontWeight: '600',
        boxShadow: '0 8px 25px rgba(239, 68, 68, 0.4)',
        transition: 'all 0.3s ease',
        display: 'inline-flex',
        alignItems: 'center',
        gap: '0.5rem'
    },
    pageTitle: {
        fontSize: '2.5rem',
        fontWeight: '700',
        background: 'linear-gradient(135deg, #ffffff 0%, #f8fafc 100%)',
        WebkitBackgroundClip: 'text',
        WebkitTextFillColor: 'transparent',
        backgroundClip: 'text',
        margin: 0
    },
    contentGrid: {
        display: 'grid',
        gap: '2rem',
        gridTemplateColumns: 'repeat(auto-fit, minmax(500px, 1fr))'
    },
    primaryCard: {
        gridColumn: '1 / -1',
        background: 'rgba(255, 255, 255, 0.95)',
        backdropFilter: 'blur(20px)',
        borderRadius: '24px',
        boxShadow: '0 25px 50px rgba(0,0,0,0.2)',
        border: '1px solid rgba(255, 255, 255, 0.2)',
        overflow: 'hidden'
    },
    infoCard: {
        background: 'rgba(255, 255, 255, 0.9)',
        backdropFilter: 'blur(15px)',
        borderRadius: '20px',
        boxShadow: '0 15px 35px rgba(0,0,0,0.15)',
        border: '1px solid rgba(255, 255, 255, 0.25)',
        overflow: 'hidden',
        transition: 'all 0.3s ease'
    },
    cardHeader: {
        display: 'flex',
        alignItems: 'center',
        gap: '1rem',
        padding: '1.5rem 2rem',
        background: 'linear-gradient(135deg, rgba(255,255,255,0.1) 0%, rgba(255,255,255,0.05) 100%)'
    },
    cardIcon: {
        fontSize: '2rem',
        padding: '0.5rem',
        background: 'rgba(255, 255, 255, 0.2)',
        borderRadius: '12px'
    },
    cardTitle: {
        margin: 0,
        fontSize: '1.4rem',
        fontWeight: '600',
        color: '#1e293b'
    },
    searchSection: {
        padding: '2rem'
    },
    inputGroup: {
        display: 'flex',
        gap: '1rem',
        marginBottom: '2rem',
        flexWrap: 'wrap'
    },
    phoneInput: {
        flex: 1,
        minWidth: '300px',
        padding: '1rem 1.5rem',
        border: '2px solid rgba(255,255,255,0.3)',
        borderRadius: '12px',
        fontSize: '1rem',
        background: 'rgba(255,255,255,0.9)',
        backdropFilter: 'blur(10px)',
        transition: 'all 0.3s ease'
    },
    searchBtn: {
        background: 'linear-gradient(135deg, #3b82f6 0%, #1d4ed8 100%)',
        color: 'white',
        border: 'none',
        padding: '1rem 2rem',
        borderRadius: '12px',
        cursor: 'pointer',
        fontSize: '1rem',
        fontWeight: '600',
        boxShadow: '0 8px 25px rgba(59, 130, 246, 0.4)',
        transition: 'all 0.3s ease'
    },
    searchResultCard: {
        background: 'linear-gradient(135deg, rgba(34, 197, 94, 0.1) 0%, rgba(16, 185, 129, 0.05) 100%)',
        border: '2px solid rgba(34, 197, 94, 0.3)',
        borderRadius: '16px',
        padding: '1.5rem',
        marginTop: '1rem'
    },
    resultHeader: {
        display: 'flex',
        alignItems: 'center',
        gap: '0.75rem',
        marginBottom: '1rem'
    },
    successIcon: {
        fontSize: '1.5rem',
        background: 'rgba(34, 197, 94, 0.2)',
        padding: '0.5rem',
        borderRadius: '50%'
    },
    resultTitle: {
        fontSize: '1.2rem',
        fontWeight: '600',
        color: '#059669'
    },
    resultDetails: {
        marginBottom: '1.5rem'
    },
    detailRow: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        padding: '0.75rem 0',
        borderBottom: '1px solid rgba(0,0,0,0.05)'
    },
    label: {
        fontWeight: '600',
        color: '#475569',
        fontSize: '0.95rem'
    },
    value: {
        fontWeight: '500',
        color: '#1e293b',
        fontSize: '1rem',
        textAlign: 'right'
    },
    detailGrid: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))',
        gap: '1rem'
    },
    detailItem: {
        display: 'flex',
        flexDirection: 'column',
        gap: '0.25rem'
    },
    detailLabel: {
        fontSize: '0.85rem',
        fontWeight: '500',
        color: '#64748b',
        textTransform: 'uppercase',
        letterSpacing: '0.05em'
    },
    detailValue: {
        fontSize: '1rem',
        fontWeight: '500',
        color: '#1e293b',
        wordBreak: 'break-word'
    },
    cardContent: {
        padding: '0 2rem 2rem'
    },
    loadingContainer: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        height: '60vh',
        gap: '1.5rem',
        color: '#64748b',
        background: 'rgba(255, 255, 255, 0.9)',
        borderRadius: '20px',
        padding: '3rem',
        maxWidth: '500px',
        margin: '2rem auto'
    },
    loadingSpinner: {
        width: '50px',
        height: '50px',
        border: '4px solid rgba(102, 126, 234, 0.2)',
        borderTop: '4px solid #667eea',
        borderRadius: '50%',
        animation: 'spin 1s linear infinite'
    },
    errorContainer: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        height: '60vh',
        textAlign: 'center',
        color: '#64748b',
        background: 'rgba(255, 255, 255, 0.9)',
        borderRadius: '20px',
        padding: '3rem',
        maxWidth: '500px',
        margin: '2rem auto'
    },
    errorIcon: {
        fontSize: '4rem',
        marginBottom: '1rem',
        opacity: '0.5'
    },
    errorBtn: {
        background: 'linear-gradient(135deg, #6b7280 0%, #4b5563 100%)',
        color: 'white',
        border: 'none',
        padding: '1rem 2rem',
        borderRadius: '12px',
        cursor: 'pointer',
        fontSize: '1rem',
        fontWeight: '600',
        marginTop: '1.5rem'
    }
};

export default EventReg;
