import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function EventManage(){
    const navigate = useNavigate();
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(true);
   
    const fetchEvents = async () => {
        try {
            setLoading(true);
            const response = await axios.get(`http://localhost:8080/api/events`);
            setEvents(response.data.data || response.data);
        } catch (error) {
            alert("Failed to load Events");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchEvents();
    }, []);

    const handleBook = (event) => {
        navigate(`/eventReg/${event.id}`);
    };

    const handleHome = () => {
        navigate("/"); 
    };

    if (loading) {
        return <div style={styles.loadingContainer}>
            <div style={styles.loadingSpinner}></div>
            <h2>Loading Events...</h2>
        </div>;
    }

    return (
        <div style={styles.container}>
            <div style={styles.header}>
                <button onClick={handleHome} style={styles.homeBtn}>
                    🏠 Home
                </button>
                <h1 style={styles.title}>Event Management</h1>
            </div>


            <div style={styles.tableContainer}>
                {events.length === 0 ? (
                    <div style={styles.emptyState}>
                        <div style={styles.emptyIcon}>📅</div>
                        <h3>No Events Found</h3>
                        <p>No events available at the moment</p>
                    </div>
                ) : (
                    <table style={styles.table}>
                        <thead>
                            <tr style={styles.tableHeadRow}>
                                {["ID", "Name", "Date", "Description", "Time", "Organizer", "Venue", "Action"].map((header) => (
                                    <th key={header} style={styles.tableHeader}>{header}</th>
                                ))}
                            </tr>
                        </thead>
                        <tbody>
                            {events.map((event) => (
                                <tr key={event.id} style={styles.tableRow}>
                                    <td style={styles.tableCell}>{event.id}</td>
                                    <td style={styles.tableCell}>{event.name}</td>
                                    <td style={styles.tableCellDate}>{formatDate(event.date)}</td>
                                    <td style={styles.tableCellDesc}>{event.description}</td>
                                    <td style={styles.tableCell}>{event.time}</td>
                                    <td style={styles.tableCell}>{event.organizer?.id || 'N/A'}</td>
                                    <td style={styles.tableCell}>{event.venue?.id || 'N/A'}</td>
                                    <td style={styles.tableCellAction}>
                                        <button 
                                            onClick={() => handleBook(event)}
                                            style={styles.bookBtn}
                                        >
                                            🎫 Book
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
}


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
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        padding: '2rem',
        fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif'
    },
    header: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginBottom: '2rem',
        flexWrap: 'wrap',
        gap: '1rem'
    },
    homeBtn: {
        background: 'linear-gradient(135deg, #10b981 0%, #059669 100%)',
        color: 'white',
        border: 'none',
        padding: '0.75rem 1.5rem',
        borderRadius: '12px',
        cursor: 'pointer',
        fontSize: '1rem',
        fontWeight: '600',
        boxShadow: '0 4px 15px rgba(16, 185, 129, 0.4)',
        transition: 'all 0.3s ease'
    },
    title: {
        color: 'white',
        fontSize: '2.5rem',
        fontWeight: '700',
        margin: 0,
        background: 'linear-gradient(135deg, #ffffff 0%, #e2e8f0 100%)',
        WebkitBackgroundClip: 'text',
        WebkitTextFillColor: 'transparent',
        backgroundClip: 'text'
    },
    tableContainer: {
        background: 'rgba(255, 255, 255, 0.95)',
        backdropFilter: 'blur(20px)',
        borderRadius: '20px',
        padding: '2rem',
        boxShadow: '0 20px 40px rgba(0,0,0,0.1)',
        maxWidth: '1400px',
        margin: '0 auto'
    },
    table: {
        width: '100%',
        borderCollapse: 'collapse',
        borderRadius: '12px',
        overflow: 'hidden',
        background: 'white',
        boxShadow: '0 10px 30px rgba(0,0,0,0.1)'
    },
    tableHeadRow: {
        background: 'linear-gradient(135deg, #3b82f6 0%, #1d4ed8 100%)',
        color: 'white'
    },
    tableHeader: {
        padding: '1.2rem 1rem',
        textAlign: 'left',
        fontWeight: '600',
        fontSize: '0.95rem',
        textTransform: 'uppercase',
        letterSpacing: '0.05em'
    },
    tableRow: {
        transition: 'all 0.2s ease',
        borderBottom: '1px solid #f1f5f9'
    },
    tableRowHover: {
        background: '#f8fafc'
    },
    tableCell: {
        padding: '1rem',
        fontSize: '0.95rem',
        color: '#475569'
    },
    tableCellDate: {
        padding: '1rem',
        fontSize: '0.95rem',
        color: '#059669',
        fontWeight: '500'
    },
    tableCellDesc: {
        padding: '1rem',
        maxWidth: '250px',
        color: '#334155'
    },
    tableCellAction: {
        padding: '1rem',
        textAlign: 'center'
    },
    bookBtn: {
        background: 'linear-gradient(135deg, #f59e0b 0%, #d97706 100%)',
        color: 'white',
        border: 'none',
        padding: '0.6rem 1.5rem',
        borderRadius: '8px',
        cursor: 'pointer',
        fontSize: '0.9rem',
        fontWeight: '600',
        boxShadow: '0 4px 15px rgba(245, 158, 11, 0.4)',
        transition: 'all 0.3s ease',
        hover: {
            transform: 'translateY(-2px)',
            boxShadow: '0 6px 20px rgba(245, 158, 11, 0.5)'
        }
    },
    loadingContainer: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        height: '60vh',
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
        animation: 'spin 1s linear infinite',
        marginBottom: '1rem'
    },
    emptyState: {
        textAlign: 'center',
        padding: '4rem 2rem',
        color: '#64748b'
    },
    emptyIcon: {
        fontSize: '4rem',
        marginBottom: '1rem',
        opacity: '0.5'
    }
};

export default EventManage;
