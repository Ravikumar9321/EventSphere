import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../api/api";

function RegisterManage() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [register, setRegister] = useState(null);
    const [attendee, setAttendee] = useState(null);
    const [event, setEvent] = useState(null);
    const [venue, setVenue] = useState(null);
    const [organizer, setOrganizer] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchRegisterDetails = async () => {
            setLoading(true);
            try {
                const registerRes = await api.get(`http://localhost:8080/api/register/${id}`);
                const registerData = registerRes.data.data || registerRes.data;
                setRegister(registerData);

                if (registerData.attendees?.id) {
                    const attRes = await api.get(`http://localhost:8080/api/attendee/${registerData.attendees.id}`);
                    setAttendee(attRes.data.data || attRes.data);
                }

                if (registerData.event.id) {
                    const eventRes = await api.get(`http://localhost:8080/api/events/${registerData.event.id}`);
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
                }
            } catch (error) {
                alert(error);
            } finally {
                setLoading(false);
            }
        };

        fetchRegisterDetails();
    }, [id]);

    if (loading) return <div style={styles.loadingContainer}>
        <div style={styles.loadingSpinner}></div>
        <div>Loading registration details...</div>
    </div>;
    
    if (!register) return <div style={styles.errorContainer}>
        <div style={styles.errorIcon}>⚠️</div>
        <h2>Registration Not Found</h2>
        <p>The registration you're looking for doesn't exist.</p>
        <button onClick={() => navigate(-1)} style={styles.errorBtn}>← Back to List</button>
    </div>;

    return (
        <div style={styles.container}>
            <div style={styles.header}>
                <button onClick={() => navigate(-1)} style={styles.backButton}>
                    ← Back 
                </button>
            </div>

            <div style={styles.contentGrid}>
                <div style={styles.primaryCard}>
                    <div style={styles.cardHeader}>
                        <div style={styles.cardIcon}>📄</div>
                        <h2 style={styles.cardTitle}>Registration Details</h2>
                    </div>
                    <div style={styles.cardContent}>
                        <div style={styles.detailRow}>
                            <span style={styles.label}>Registration ID:</span>
                            <span style={styles.value}>{register.id}</span>
                        </div>
                        <div style={styles.detailRow}>
                            <span style={styles.label}>Date:</span>
                            <span style={styles.formattedDate}>{formatDate(register.date)}</span>
                        </div>
                    </div>
                </div>

                {/* Attendee Card */}
                {attendee && (
                    <div style={styles.infoCard}>
                        <div style={styles.cardHeader}>
                            <div style={styles.cardIcon}>👥</div>
                            <h3 style={styles.cardTitle}>Attendee Details</h3>
                        </div>
                        <div style={styles.cardContent}>
                            <div style={styles.detailGrid}>
                                <DetailItem label="ID" value={attendee.id} />
                                <DetailItem label="Name" value={attendee.name} />
                                <DetailItem label="Email" value={attendee.email} />
                                <DetailItem label="Contact" value={attendee.contact} />
                            </div>
                        </div>
                    </div>
                )}

                {/* Event Card */}
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

                {/* Organizer Card */}
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
    cardContent: {
        padding: '0 2rem 2rem'
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
    detailRow: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        padding: '1rem 0',
        borderBottom: '1px solid rgba(0,0,0,0.05)'
    },
    label: {
        fontWeight: '600',
        color: '#475569',
        fontSize: '1rem'
    },
    value: {
        fontWeight: '500',
        color: '#1e293b',
        fontSize: '1.1rem'
    },
    formattedDate: {
        fontWeight: '600',
        color: '#059669',
        fontSize: '1.1rem'
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

export default RegisterManage;
