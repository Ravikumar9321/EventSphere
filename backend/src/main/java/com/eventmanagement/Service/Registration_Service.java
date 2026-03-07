package com.eventmanagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.eventmanagement.Dao.Attendee_DAO;
import com.eventmanagement.Dao.Event_DAO;
import com.eventmanagement.Dao.Organize_DAO;
import com.eventmanagement.Dao.Registration_DAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Events;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Exception.*;


@Service
public class Registration_Service {
	
	@Autowired
	private Registration_DAO rdao;
	@Autowired
	private Event_DAO edao;
	@Autowired
	private Attendee_DAO adao;
	
//i)add Registration
	public ResponseEntity<ResponseStructure<Registration>> saveRegistration(Registration regisitration, int eventId,int attendeeId) {
		ResponseStructure<Registration> rs=new ResponseStructure<>();
		       Optional<Events> optevent=  edao.findEventById((eventId));
		       if(optevent.isEmpty())
		    	   throw new IdNotFoundException("Invalid, eventId Not Found");
		       Optional<Attendee> optattendee=adao.findAttendeeById(attendeeId);
		       if(optattendee.isEmpty())
		    	   throw new IdNotFoundException("Invalid, attendee Not Found");
	     regisitration.setAttendees(optattendee.get());
	     regisitration.setEvent(optevent.get());
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Registration Details Saved");
		rs.setData(rdao.saveRegistration(regisitration));
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	//ii)
	public ResponseEntity<ResponseStructure<List<Registration>>> findallRegistrations() {
		ResponseStructure<List<Registration>> rs=new ResponseStructure<>();
	     List<Registration> ls=rdao.findallRegistration();
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Registration details are retrieved");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new NoRecordFoundException("No Details Found");
	}
	//iii)
	public ResponseEntity<ResponseStructure<Registration>> findRegistrationDetailsById(int id) {
		ResponseStructure<Registration> rs=new ResponseStructure<>();
	     Optional<Registration> org =rdao.findRegistrationById(id);
	     if(org.isPresent()) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Registration details are retrieved by  Id");
	    	 rs.setData(org.get());
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("Invalid,Id not Found");
	}
	
	//iv)
	public ResponseEntity<ResponseStructure<Registration>> updateRegistration(Registration registration) {
		ResponseStructure<Registration> rs=new ResponseStructure<>();
		 if(registration.getId()==null) {
			 throw new IdNotFoundException("Registration Id Not Found");
		 }
         
		 Optional<Registration> optregister=rdao.findRegistrationById(registration.getId());
		 Optional<Events> optevent=edao.findEventById(registration.getEvent().getId());

		 if(optevent.isEmpty()) {
       	  throw new IdNotFoundException("Event id not found");
         }
       if  (optregister.isPresent()) {
       	  rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Registration details are updated");
	    	 rs.setData(rdao.saveRegistration(registration));
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
         }
         else
       	  throw new NoRecordFoundException("Invalid , Registraton details not found");
	}
//	v)
	public ResponseEntity<ResponseStructure<String>> deleteRegistration(int id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
	    Optional<Registration> opt=rdao.findRegistrationById(id);
	    if(opt.isPresent()) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Registration details cancelled");
     	    	 rdao.deleteRegistration(opt.get());
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid ,Id is not Found");
	}
//vi)
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationDetailsByEventId(int eventId) 
	{
		ResponseStructure<List<Registration>> rs=new ResponseStructure<>();
		Optional<Events> e=edao.findEventById(eventId);
		if(e.isEmpty())
			throw new NoRecordFoundException("Event is Not Found");
		
	   List<Registration> ls=rdao.getRegistrationsByEventId(eventId);
	    if(ls.size()>0) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Registration details retrieved by Event Id");
     	    	 rs.setData(ls);
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new NoRecordFoundException("Event is Not  Registered ");
		
	}
	
	//Vii)
	public ResponseEntity<ResponseStructure<Registration>> getRegistrationDetailsByAttendeeId(int attendeeId) {
		ResponseStructure<Registration> rs=new ResponseStructure<>();
	    Registration opt=rdao.getRegistrationDetailsByAttendeeId(attendeeId);
	    if(opt!=null) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Registration details retrieved by attendee Id");
     	    	 rs.setData(opt);
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid , attendee Id is not Found");
	}

}
