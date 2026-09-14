import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";


function Organizermanage(){
    const [organizers, setOrganizers] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    const fetchOrganizer = async () => {
        try {
            setLoading(true);
            const response = await api.get(`http://localhost:8080/api/organizers`);
            setOrganizers(response.data.data || response.data);
        } catch (error) {
            console.error("Fetch error:", error);
        } finally {
            setLoading(false);
        }
    };

    const deleteOrganizer = async (id) => {
        try {
            if (window.confirm("Are you sure you want to delete this organizer?")) {
                setLoading(true);
                await api.delete(`http://localhost:8080/api/organizers/${id}`);
                alert("✅ Organizer deleted successfully!");
                fetchOrganizer(); 
            }
        } catch (error) {
            alert("❌ Failed to delete organizer");
            console.error("Delete error:", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchOrganizer();
    }, []);

    if (loading) {
        return (
            <div style={Styles.loading}>
                <h1>⏳ Loading Organizers...</h1>
            </div>
        );
    }

    return (
        <div style={Styles.page}>
            <div style={Styles.container}>
                <div style={Styles.header}>
                    <h1 style={Styles.title}>👥 Organizer Management</h1>
                    <p style={{margin: '0', opacity: '0.9'}}>Manage all organizers here</p>
                </div>

                
                <div style={Styles.tableContainer}>
                    <button 
                        style={Styles.addButton}
                        onClick={() => navigate("/orgRegister")}
                    >
                        ➕ Add New Organizer
                    </button>
                    <button style={Styles.addButton} onClick={()=>navigate(-1)}>Back Home</button>

                    
                    {organizers.length === 0 ? (
                        <div style={Styles.empty}>
                            <h3>📭 No organizers found</h3>
                            <p>Click "Add New Organizer" to get started</p>
                        </div>
                    ) : (
                
                        <table style={Styles.table}>
                            <thead>
                                <tr>
                                    <th style={Styles.th}>ID</th>
                                    <th style={Styles.th}>Name</th>
                                    <th style={Styles.th}>Email</th>
                                    <th style={Styles.th}>Organization</th>
                                    <th style={Styles.th}>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {organizers.map((org) => (
                                    <tr key={org.id || org.email}>
                                        <td style={Styles.td}><strong>{org.id}</strong></td>
                                        <td style={Styles.td}>{org.name}</td>
                                        <td style={Styles.td}>{org.email}</td>
                                        <td style={Styles.td}>{org.organizer}</td>
                                        <td style={Styles.td}>
                                            <button 
                                                style={Styles.deleteButton}
                                                onClick={() => deleteOrganizer(org.id)}
                                            >
                                                🗑️ Delete
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    )}
                </div>
            </div>
        </div>
    );
}

const Styles = {
  page: {
    minHeight: '100vh',
    backgroundColor: '#f8f9fa',
    padding: '30px 20px'
  },
  container: {
    maxWidth: '1100px',
    margin: '0 auto',
    background: 'white',
    borderRadius: '12px',
    boxShadow: '0 4px 20px rgba(0,0,0,0.08)',
    overflow: 'hidden',
    border: '1px solid #e9ecef'
  },
  header: {
    backgroundColor: '#3498db',
    color: 'white',
    padding: '25px',
    textAlign: 'center'
  },
  title: {
    margin: '0',
    fontSize: '26px',
    fontWeight: 'bold'
  },
  addButton: {
    backgroundColor: '#28a745',
    color: 'white',
    padding: '12px 24px',
    border: 'none',
    borderRadius: '8px',
    fontSize: '16px',
    fontWeight: '600',
    cursor: 'pointer',
    margin: '25px',
    display: 'block'
  },
  tableContainer: {
    padding: '25px'
  },
  table: {
    width: '100%',
    borderCollapse: 'collapse',
    fontSize: '15px'
  },
  th: {
    backgroundColor: '#f8f9fa',
    padding: '15px',
    textAlign: 'left',
    borderBottom: '2px solid #dee2e6',
    fontWeight: 'bold',
    color: '#495057'
  },
  td: {
    padding: '15px',
    borderBottom: '1px solid #dee2e6',
    color: '#6c757d'
  },
  deleteButton: {
    backgroundColor: '#dc3545',
    color: 'white',
    padding: '8px 16px',
    border: 'none',
    borderRadius: '6px',
    cursor: 'pointer',
    fontSize: '14px',
    fontWeight: '500'
  },
  loading: {
    textAlign: 'center',
    padding: '60px 20px',
    color: '#6c757d',
    fontSize: '18px'
  },
  empty: {
    textAlign: 'center',
    padding: '60px 25px',
    color: '#6c757d',
    fontSize: '18px'
  }
};

export default Organizermanage;
