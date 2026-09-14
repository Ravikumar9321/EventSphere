import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"; 
import api from "../api/api";

function RegisterDetails() {
    const [registers, setRegisters] = useState([]);
    const [loading, setLoading] = useState(true); 
    const navigate = useNavigate();

    const fetchRegister = async () => {
        setLoading(true);
        try {
            const response = await api.get(`http://localhost:8080/api/register`);
            setRegisters(response.data.data || response.data);
        } catch (error) {
            console.error("Failed to load:", error);
        } finally {
            setLoading(false); 
        }
    };

    useEffect(() => {
        fetchRegister();
    }, []);

    const handleRegistration = (register) => {
        navigate(`/register/${register.id}`);
    };

    const handleHome = () => {
        navigate('/home');
    };

    if (loading) return <div style={styles.loadingContainer}>
        <div style={styles.loadingSpinner}></div>
        <div>Loading registrations...</div>
    </div>;

    return (
        <div style={styles.container}>
            <div style={styles.header}>
                <div style={styles.headerLeft}>
                    <h1 style={styles.title}>📋 Registration Management</h1>
                    <p style={styles.subtitle}>{registers.length} total registrations</p>
                </div>
                <button 
                    onClick={handleHome}
                    style={styles.homeBtn}
                >
                    🏠 Home
                </button>
            </div>

            <div style={styles.tableWrapper}>
                <table style={styles.table}>
                    <thead>
                        <tr style={styles.tableHeadRow}>
                            <th style={styles.colId}>ID</th>
                            <th style={styles.colDate}>Date</th>
                            <th style={styles.colAttendee}>Attendee ID</th>
                            <th style={styles.colEvent}>Event ID</th>
                            <th style={styles.colActions}>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {registers.map(register => (
                            <tr 
                                key={register.id} 
                                style={styles.tableRow}
                                onMouseEnter={(e) => e.currentTarget.style.backgroundColor = '#f8f9ff'}
                                onMouseLeave={(e) => e.currentTarget.style.backgroundColor = 'transparent'}
                            >
                                <td style={styles.tableCellId}>
                                    <div style={styles.cellContent}>
                                        <span style={styles.idBadge}>{register.id}</span>
                                    </div>
                                </td>
                                <td style={styles.tableCellDate}>
                                    <div style={styles.dateCell}>{formatDate(register.date)}</div>
                                </td>
                                <td style={styles.tableCellCenter}>
                                    <span style={styles.attendeeId}>
                                        {register.attendees?.id || register.attendee?.id || 'N/A'}
                                    </span>
                                </td>
                                <td style={styles.tableCellCenter}>
                                    <span style={styles.eventId}>{register.event?.id || 'N/A'}</span>
                                </td>
                                <td style={styles.tableCellCenter}>
                                    <button 
                                        onClick={() => handleRegistration(register)}
                                        style={styles.actionBtn}
                                    >
                                         View Details
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            {registers.length === 0 && !loading && (
                <div style={styles.emptyState}>
                    <div style={styles.emptyIcon}>📭</div>
                    <h3>No registrations found</h3>
                    <p>Check back later for new registrations</p>
                </div>
            )}
        </div>
    );
}


const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    try {
        return new Date(dateString).toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'short',
            day: 'numeric'
        });
    } catch {
        return dateString;
    }
};

const styles = {
    container: {
        padding: '2.5rem',
        maxWidth: '1400px',
        margin: '0 auto',
        fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif',
        background: 'linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)',
        minHeight: '100vh'
    },
    header: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginBottom: '2.5rem',
        flexWrap: 'wrap',
        gap: '1rem'
    },
    headerLeft: {
        display: 'flex',
        flexDirection: 'column',
        gap: '0.5rem'
    },
    title: {
        fontSize: '2.5rem',
        fontWeight: '700',
        color: '#1a202c',
        margin: 0,
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        WebkitBackgroundClip: 'text',
        WebkitTextFillColor: 'transparent',
        backgroundClip: 'text'
    },
    subtitle: {
        color: '#64748b',
        fontSize: '1.1rem',
        margin: 0
    },
    homeBtn: {
        background: 'linear-gradient(135deg, #10b981 0%, #059669 100%)',
        color: 'white',
        border: 'none',
        padding: '1rem 2rem',
        borderRadius: '12px',
        cursor: 'pointer',
        fontSize: '1rem',
        fontWeight: '600',
        boxShadow: '0 8px 25px rgba(16, 185, 129, 0.4)',
        transition: 'all 0.3s ease',
        display: 'inline-flex',
        alignItems: 'center',
        gap: '0.5rem'
    },
    tableWrapper: {
        background: 'white',
        borderRadius: '20px',
        boxShadow: '0 20px 60px rgba(0,0,0,0.15)',
        overflow: 'auto',
        border: '1px solid #e2e8f0',
        maxHeight: '600px'
    },
    table: {
        width: '100%',
        borderCollapse: 'collapse',
        tableLayout: 'fixed'  
    },
    tableHeadRow: {
        background: 'linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%)'
    },
    
    colId: {
        padding: '1.25rem 1rem',
        color: 'white',
        fontWeight: '600',
        fontSize: '0.95rem',
        textTransform: 'uppercase',
        letterSpacing: '0.05em',
        textAlign: 'left',
        width: '60px'
    },
    colDate: {
        padding: '1.25rem 1rem',
        color: 'white',
        fontWeight: '600',
        fontSize: '0.95rem',
        textTransform: 'uppercase',
        letterSpacing: '0.05em',
        textAlign: 'left',
        width: '140px'
    },
    colAttendee: {
        padding: '1.25rem 1rem',
        color: 'white',
        fontWeight: '600',
        fontSize: '0.95rem',
        textTransform: 'uppercase',
        letterSpacing: '0.05em',
        textAlign: 'center',
        width: '120px'
    },
    colEvent: {
        padding: '1.25rem 1rem',
        color: 'white',
        fontWeight: '600',
        fontSize: '0.95rem',
        textTransform: 'uppercase',
        letterSpacing: '0.05em',
        textAlign: 'center',
        width: '120px'
    },
    colActions: {
        padding: '1.25rem 1rem',
        color: 'white',
        fontWeight: '600',
        fontSize: '0.95rem',
        textTransform: 'uppercase',
        letterSpacing: '0.05em',
        textAlign: 'center',
        width: '160px'
    },
    tableRow: {
        transition: 'all 0.2s ease',
        cursor: 'pointer',
        borderBottom: '1px solid #f1f5f9'
    },
    tableCellId: {
        padding: '1.25rem 1rem',
        verticalAlign: 'middle',
        textAlign: 'left'
    },
    tableCellDate: {
        padding: '1.25rem 1rem',
        verticalAlign: 'middle',
        textAlign: 'left'
    },
    tableCellCenter: {
        padding: '1.25rem 1rem',
        verticalAlign: 'middle',
        textAlign: 'center'
    },
    cellContent: {
        display: 'flex',
        alignItems: 'center',
        gap: '0.75rem'
    },
    idBadge: {
        background: 'linear-gradient(135deg, #10b981, #059669)',
        color: 'white',
        padding: '0.4rem 0.8rem',
        borderRadius: '20px',
        fontSize: '0.85rem',
        fontWeight: '600'
    },
    dateCell: {
        fontWeight: '500',
        color: '#475569'
    },
    attendeeId: {
        background: '#dbeafe',
        color: '#1e40af',
        padding: '0.3rem 0.7rem',
        borderRadius: '8px',
        fontSize: '0.85rem',
        fontWeight: '500'
    },
    eventId: {
        background: '#fef3c7',
        color: '#92400e',
        padding: '0.3rem 0.7rem',
        borderRadius: '8px',
        fontSize: '0.85rem',
        fontWeight: '500'
    },
    actionBtn: {
        background: 'linear-gradient(135deg, #3b82f6 0%, #1d4ed8 100%)',
        color: 'white',
        border: 'none',
        padding: '0.75rem 1.5rem',
        borderRadius: '10px',
        cursor: 'pointer',
        fontSize: '0.9rem',
        fontWeight: '600',
        boxShadow: '0 4px 15px rgba(59, 130, 246, 0.4)',
        transition: 'all 0.3s ease',
        display: 'inline-flex',
        alignItems: 'center',
        gap: '0.5rem',
        width: '70%'  
    },
    loadingContainer: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        height: '50vh',
        gap: '1rem',
        color: '#64748b'
    },
    loadingSpinner: {
        width: '40px',
        height: '40px',
        border: '4px solid #e2e8f0',
        borderTop: '4px solid #3b82f6',
        borderRadius: '50%',
        animation: 'spin 1s linear infinite'
    },
    emptyState: {
        textAlign: 'center',
        padding: '6rem 2rem',
        color: '#64748b'
    },
    emptyIcon: {
        fontSize: '4rem',
        marginBottom: '1rem',
        opacity: '0.5'
    }
};

export default RegisterDetails;
