
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function OrganizeForm(){
    const [loading, setLoading] = useState(false);
    const navigate=useNavigate();
    const [organizer, setOrganizer] = useState({
        name: "",
        email: "",
        organizer: ""
    });

    const handleInput = (e) => {
        setOrganizer({...organizer, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        try {
            setLoading(true);
            await axios.post(`http://localhost:8080/api/organizers`, organizer);
            alert("✅ Organizer Info added successfully");
        
            setOrganizer({
                name: "",
                email: "",
                organizer: ""
            });
            navigate(-1);
            
        } catch (error) {
            alert("❌ Failed to register");
        } finally {
            setLoading(false);
        }
    };

    if(loading){
        return (
            <div style={Styles.page}>
                <div style={Styles.card}>
                    <h1 style={{textAlign: 'center', color: '#6c757d'}}>
                        ⏳ Adding Organizer...
                    </h1>
                </div>
            </div>
        );
    }
    
    return(
        <div style={Styles.page}>

            <div style={Styles.card}>
                <button onClick={()=>{navigate(-1)}}  style={Styles.button}>Go Back</button>
                <h1 style={Styles.title}>👥 Add Organizer</h1>
                <p style={Styles.subtitle}>Fill organizer details below</p>
                
                <form onSubmit={handleSubmit} style={Styles.form}>
                    
                    <div>
                        <label style={Styles.label}>👤 Organizer Name</label>
                        <input
                            name="name"
                            value={organizer.name}
                            onChange={handleInput} 
                            placeholder="e.g. Alice Johnson"
                            style={Styles.input}
                            disabled={loading}
                            required
                        />
                    </div>
                    
                    
                    <div>
                        <label style={Styles.label}>📧 Email</label>
                        <input 
                            name="email"
                            value={organizer.email}
                            onChange={handleInput}
                            placeholder="e.g. alice@gmail.com"
                            style={Styles.input}
                            disabled={loading}
                            required  
                        />
                    </div>
                    
                
                    <div>
                        <label style={Styles.label}>🏢 Company/Organization</label>
                        <input 
                        
                            name="organizer"
                            value={organizer.organizer}
                            onChange={handleInput}
                            placeholder="e.g. GrandGroom Events"
                            style={Styles.input}
                            disabled={loading}
                            required 
                        />
                    </div>
                    
                    
                    <button 
                        type="submit" 
                        disabled={loading}
                        style={{
                            ...Styles.button,
                            ...Styles.submitBtn,
                            ...(loading ? Styles.submitBtnDisabled : Styles.submitBtnHover)
                        }}
                    >
                        {loading ? "⏳ Adding..." : "✅ Add Organizer"}
                    </button>
                </form>
            </div>
        </div>
    );
}

const Styles = {
  page: {
    minHeight: '100vh',
    backgroundColor: '#f8f9fa',
    padding: '30px 20px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center'
  },
  card: {
    background: 'white',
    padding: '40px',
    borderRadius: '12px',
    boxShadow: '0 4px 20px rgba(0,0,0,0.08)',
    width: '100%',
    maxWidth: '450px',
    border: '1px solid #e9ecef'
  },
  title: {
    fontSize: '28px',
    fontWeight: 'bold',
    color: '#2c3e50',
    textAlign: 'center',
    marginBottom: '10px'
  },
  subtitle: {
    textAlign: 'center',
    color: '#7f8c8d',
    marginBottom: '30px',
    fontSize: '16px'
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '20px'
  },
  label: {
    fontWeight: '600',
    color: '#34495e',
    marginBottom: '8px',
    fontSize: '15px'
  },
  input: {
    width: '100%',
    padding: '12px 16px',
    border: '2px solid #dee2e6',
    borderRadius: '8px',
    fontSize: '16px',
    backgroundColor: '#fff',
    transition: 'all 0.2s'
  },
  inputFocus: {
    borderColor: '#3498db',
    boxShadow: '0 0 0 3px rgba(52, 152, 219, 0.1)'
  },
  button: {
    padding: '14px',
    borderRadius: '8px',
    fontSize: '16px',
    fontWeight: '600',
    border: 'none',
    cursor: 'pointer',
    transition: 'all 0.2s'
  },
  submitBtn: {
    backgroundColor: '#28a745',
    color: 'white'
  },
  submitBtnHover: {
    backgroundColor: '#218838'
  },
  submitBtnDisabled: {
    backgroundColor: '#6c757d',
    cursor: 'not-allowed'
  }
};

export default OrganizeForm;
