package com.eventmanagement.Service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.eventmanagement.Dao.*;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.*;
import com.eventmanagement.Exception.*;


@Service
public class Registration_Service {
	
	@Autowired
	private Registration_DAO registerdao;
	@Autowired
	private Event_DAO eventdao;
	@Autowired
	private Attendee_DAO attendeedao;
	
//i)add Registration
	public ResponseEntity<ResponseStructure<Registration>> saveRegistration(Registration regisitration, int eventId,int attendeeId) {
		ResponseStructure<Registration> rs=new ResponseStructure<>();
		       Optional<Events> optevent=  eventdao.findEventById((eventId));
		       if(optevent.isEmpty())
		    	   throw new IdNotFoundException("Invalid, eventId Not Found");
		       Optional<Attendee> optattendee=attendeedao.findAttendeeById(attendeeId);
		       if(optattendee.isEmpty())
		    	   throw new IdNotFoundException("Invalid, attendee Not Found");
	     regisitration.setAttendees(optattendee.get());
	     regisitration.setEvent(optevent.get());
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Registration Details Saved");
		rs.setData(registerdao.saveRegistration(regisitration));
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	//ii)
	public ResponseEntity<ResponseStructure<List<Registration>>> findallRegistrations() {
		ResponseStructure<List<Registration>> rs=new ResponseStructure<>();
	     List<Registration> ls=registerdao.findallRegistration();
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
	     Optional<Registration> org =registerdao.findRegistrationById(id);
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
         
		 Optional<Registration> optregister=registerdao.findRegistrationById(registration.getId());
		 Optional<Events> optevent=eventdao.findEventById(registration.getEvent().getId());

		 if(optevent.isEmpty()) {
       	  throw new IdNotFoundException("Event id not found");
         }
       if  (optregister.isPresent()) {
       	  rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Registration details are updated");
	    	 rs.setData(registerdao.saveRegistration(registration));
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
         }
         else
       	  throw new NoRecordFoundException("Invalid , Registraton details not found");
	}
//	v)
	public ResponseEntity<ResponseStructure<String>> deleteRegistration(int id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
	    Optional<Registration> opt=registerdao.findRegistrationById(id);
	    if(opt.isPresent()) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Registration details cancelled");
     	    	 registerdao.deleteRegistration(opt.get());
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid ,Id is not Found");
	}
//vi)
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationDetailsByEventId(int eventId) 
	{
		ResponseStructure<List<Registration>> rs=new ResponseStructure<>();
		Optional<Events> e=eventdao.findEventById(eventId);
		if(e.isEmpty())
			throw new NoRecordFoundException("Event is Not Found");
		
	   List<Registration> ls=registerdao.getRegistrationsByEventId(eventId);
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
	    Registration opt=registerdao.getRegistrationDetailsByAttendeeId(attendeeId);
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
