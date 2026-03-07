import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function AttendeeManage() {
    const [attendees, setAttendees] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate=useNavigate();
    const fetchAttendee = async () => {
        setLoading(true);
        try {
            const response = await axios.get(`http://localhost:8080/api/attendee`);
            setAttendees(response.data.data || response.data);
        } catch (error) {
            alert("Failed to load details");
        } finally {
            setLoading(false);
        }
    };

    const deleteAttendee = async (id) => {
        if (window.confirm("Are you sure you want to delete this attendee?")) {
            try {
                await axios.delete(`http://localhost:8080/api/attendee/${id}`);
                alert("Deleted successfully!");
                fetchAttendee();
            } catch (error) {
                if (error.response?.status === 404) {
                    alert("Attendee does not exist");
                } else {
                    alert("Failed to delete");
                }
            }
        }
    };

    useEffect(() => {
        fetchAttendee();
    }, []);

    if (loading) return <div style={styles.loading}>Loading attendees...</div>;

    return (
        <div style={styles.container}>
            <h1 style={styles.title}>👥 Attendee Management</h1>
            <button style={styles.homeBtn} onClick={()=>navigate(-1)}>Home</button>
            
            <div style={styles.tableContainer}>
                <table style={styles.table}>
                    <thead>
                        <tr>
                            <th style={styles.th}>ID</th>
                            <th style={styles.th}>Name</th>
                            <th style={styles.th}>Contact</th>
                            <th style={styles.th}>Email</th>
                            <th style={styles.th}>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {attendees.map(attendee => (
                            <tr key={attendee.id} style={styles.tr}>
                                <td style={styles.td}>{attendee.id}</td>
                                <td style={styles.td}>{attendee.name}</td>
                                <td style={styles.td}>{attendee.contact}</td>
                                <td style={styles.td}>{attendee.email}</td>
                                <td style={styles.td}>
                                    <button 
                                        onClick={() => deleteAttendee(attendee.id)}
                                        style={styles.deleteBtn}
                                    >
                                        🗑️ Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            {attendees.length === 0 && !loading && (
                <div style={styles.emptyState}>
                    No attendees found
                </div>
            )}
        </div>
    );
}

const styles = {
    container: {
        padding: '2rem',
        maxWidth: '1200px',
        margin: '0 auto',
        fontFamily: 'system-ui, -apple-system, sans-serif'
    },
    title: {
        fontSize: '2rem',
        color: '#1a202c',
        marginBottom: '2rem',
        textAlign: 'center'
    },
    tableContainer: {
        background: 'white',
        borderRadius: '12px',
        boxShadow: '0 10px 25px rgba(0,0,0,0.1)',
        overflow: 'hidden',
        border: '1px solid #e2e8f0'
    },
    table: {
        width: '100%',
        borderCollapse: 'collapse'
    },
    th: {
        background: '#4a5568',
        color: 'white',
        padding: '1.2rem 1rem',
        textAlign: 'left',
        fontWeight: '600',
        fontSize: '0.95rem'
    },
    tr: {
        borderBottom: '1px solid #e2e8f0',
        transition: 'background-color 0.2s'
    },
    trHover: {
        backgroundColor: '#f7fafc'
    },
    td: {
        padding: '1rem',
        fontSize: '0.9rem'
    },
    deleteBtn: {
        background: '#f56565',
        color: 'white',
        border: 'none',
        padding: '0.5rem 1rem',
        borderRadius: '6px',
        cursor: 'pointer',
        fontSize: '0.85rem',
        fontWeight: '500',
        transition: 'all 0.2s',
        boxShadow: '0 2px 4px rgba(245, 101, 101, 0.3)'
    },
    loading: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '400px',
        fontSize: '1.2rem',
        color: '#4a5568'
    },
    emptyState: {
        textAlign: 'center',
        padding: '4rem 2rem',
        color: '#a0aec0',
        fontSize: '1.1rem'
    },
    homeBtn: {
        background: 'linear-gradient(135deg, #696c6b 0%, #404c48 100%)',
        color: 'white',
        border: 'none',
        padding: '1rem 2rem',
        borderRadius: '12px',
        cursor: 'pointer',
        fontSize: '1rem',
        fontWeight: '600',
        boxShadow: '0 8px 25px rgba(83, 87, 85, 0.4)',
        transition: 'all 0.3s ease',
        display: 'inline-flex',
        alignItems: 'center',
        gap: '0.5rem'
    }

};

export default AttendeeManage;
