package com.eventmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.eventmanagement.Dao.Attendee_DAO;
import com.eventmanagement.Dao.Organize_DAO;
import com.eventmanagement.Dao.Registration_DAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Exception.*;


@Service
public class Attendee_Service {
	
	@Autowired
	private Attendee_DAO adao;
	@Autowired
	private  Registration_DAO rdao;
//i)add Attendee
	public ResponseEntity<ResponseStructure<Attendee>> saveAttendee(Attendee attendee) {
		ResponseStructure<Attendee> rs=new ResponseStructure<>();
		     
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Attendee Details Saved");
		
		rs.setData(adao.saveAttendee(attendee));
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	//ii)
	public ResponseEntity<ResponseStructure<List<Attendee>>> findallAttendees() {
		ResponseStructure<List<Attendee>> rs=new ResponseStructure<>();
	     List<Attendee> ls=adao.findallAttendee();
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Attendee details are retrieved");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new NoRecordFoundException("No Details Found");
	}
	//iii)
	public ResponseEntity<ResponseStructure<Attendee>> findAttendeeDetailsById(int id) {
		ResponseStructure<Attendee> rs=new ResponseStructure<>();
	     Optional<Attendee> org =adao.findAttendeeById(id);
	     if(org.isPresent()) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Attendee details are retrieved by  Id");
	    	 rs.setData(org.get());
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("Invalid,Id not Found");
	}
	
	//iv)
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(Attendee Attendee) {
		ResponseStructure<Attendee> rs=new ResponseStructure<>();
		 if(Attendee.getId()==null) {
			 throw new IdNotFoundException("Attendee Id Not Found");
		 }

		 Optional<Attendee> opt=adao.findAttendeeById(Attendee.getId());
       if  (opt.isPresent()) {
       	  rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Attendee details are updated");
	    	 rs.setData(adao.saveAttendee(Attendee));
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
         }
         else
       	  throw new IdNotFoundException("Invalid ,We cannot insert new Id ");
	}
//	v)
	public ResponseEntity<ResponseStructure<String>> deleteAttendee(Integer id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
	    Optional<Attendee> opt=adao.findAttendeeById(id);
	    if(opt.isPresent()) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Attendee details deleted");
     	    	 adao.deleteAttendee(opt.get());
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid ,Id is not Found");
	}
	//vi)
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationsbyattendeeId(int attendeeId) {
		ResponseStructure<List<Registration>> rs=new ResponseStructure<>();
	     List<Registration> ls=adao.getRegistrationsbyattendeeId(attendeeId);
	    
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Registration details are retrieved by attendee Id");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("attendee Id Not Found");
	}
	
//	vii)
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeesbycontact(long contact) {
		ResponseStructure<Attendee> rs=new ResponseStructure<>();
	     Attendee attendee =adao.getRegistrationsbyattendeeId(contact);
	     if(attendee!=null) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Attendee details are retrieved by  contact");
	    	 rs.setData(attendee);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new NoRecordFoundException("Attendee Details not Found");
	}

}
