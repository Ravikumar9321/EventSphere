import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";

function VenueManage() {
  const [venues, setVenues] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  const fetchVenue = async () => {
    try {
      setLoading(true);
      const response = await api.get("http://localhost:8080/api/venues");
      setVenues(response.data.data || response.data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const deleteVenue = async (id) => {
    if (window.confirm("Are you sure you want to delete this venue?")) {
      try {
        setLoading(true);
        await api.delete(`http://localhost:8080/api/venues/${id}`);
        alert("Venue deleted successfully!");
        fetchVenue(); 
      } catch (error) {
        alert("Error deleting venue!");
        console.error(error);
      } finally {
        setLoading(false);
      }
    }
  };

  useEffect(() => {
    fetchVenue();
  }, []);

  if (loading) {
    return (
      <div style={Styles.loading}>
        <h2>Loading venues...</h2>
      </div>
    );
  }

  return (
    <div style={Styles.page}>
      <div style={Styles.container}>
        <div style={Styles.header}>
          <h1 style={Styles.title}>Venue List</h1>
          <p>Manage your venues here</p>
        </div>

        
        <div style={Styles.tableContainer}>
          <button 
            style={Styles.addButton}
            onClick={() => navigate("/vRegister")}
          >
            + Add New Venue
          </button>
           <button 
            style={Styles.addButton}
            onClick={() => navigate(-1)}
          >
            ⬅️Back
          </button>

          {venues.length === 0 ? (
            <div style={Styles.empty}>
              <h3>No venues found</h3>
              <p>Add your first venue using the button above</p>
            </div>
          ) : (
            <table style={Styles.table}>
              <thead>
                <tr>
                  <th style={Styles.th}>ID</th>
                  <th style={Styles.th}>Name</th>
                  <th style={Styles.th}>Location</th>
                  <th style={Styles.th}>Capacity</th>
                  <th style={Styles.th}>Action</th>
                </tr>
              </thead>
              <tbody>
                {venues.map((venue) => (
                  <tr key={venue.id}>
                    <td style={Styles.td}>{venue.id}</td>
                    <td style={Styles.td}>{venue.name}</td>
                    <td style={Styles.td}>{venue.location}</td>
                    <td style={Styles.td}>{venue.capacity}</td>
                    <td style={Styles.td}>
                      <button 
                        style={Styles.deleteButton}
                        onClick={() => deleteVenue(venue.id)}
                      >
                        Delete
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
    padding: '20px',
    backgroundColor: '#f5f5f5',
    minHeight: '100vh'
  },
  container: {
    maxWidth: '1000px',
    margin: '0 auto',
    background: 'white',
    borderRadius: '10px',
    boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
    overflow: 'hidden'
  },
  header: {
    backgroundColor: '#4a90e2',
    color: 'white',
    padding: '20px',
    textAlign: 'center'
  },
  title: {
    margin: '0',
    fontSize: '24px',
    fontWeight: 'bold'
  },
  addButton: {
    backgroundColor: '#28a745',
    color: 'white',
    padding: '12px 24px',
    border: 'none',
    borderRadius: '6px',
    fontSize: '16px',
    cursor: 'pointer',
    margin: '20px'
  },
  tableContainer: {
    padding: '20px'
  },
  table: {
    width: '100%',
    borderCollapse: 'collapse'
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
    borderBottom: '1px solid #dee2e6'
  },
  deleteButton: {
    backgroundColor: '#dc3545',
    color: 'white',
    padding: '8px 16px',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    fontSize: '14px'
  },
  loading: {
    textAlign: 'center',
    padding: '50px',
    fontSize: '18px',
    color: '#6c757d'
  },
  empty: {
    textAlign: 'center',
    padding: '50px',
    color: '#6c757d',
    fontSize: '18px'
  }
};


export default VenueManage;
