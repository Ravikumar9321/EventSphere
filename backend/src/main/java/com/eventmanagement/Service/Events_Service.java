package com.eventmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.eventmanagement.Dao.Event_DAO;
import com.eventmanagement.Dao.Organize_DAO;
import com.eventmanagement.Dao.Venue_DAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Events;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.Exception.*;


@Service
public class Events_Service {
	
	@Autowired
	private Event_DAO eventdao;
	@Autowired
	private Organize_DAO organizerdao;
	@Autowired
	private Venue_DAO venuedao;
//i)add Events
	public ResponseEntity<ResponseStructure<Events>> saveEvent(Events event) {
		ResponseStructure<Events> rs=new ResponseStructure<>();
		  Optional<Organizer> optorganizer= organizerdao.findOrganizerById(event.getOrganizer().getId());
		  Optional<Venue> optvenue= venuedao.findVenueById(event.getVenue().getId());

		  if(optorganizer.isPresent()) {
			  event.setOrganizer(optorganizer.get());
		  }
		  else
			  throw new IdNotFoundException("Invalid,organizer id not found");
		  if(optvenue.isPresent()) {
			  event.setVenue(optvenue.get());
		  }
		  else
			  throw new IdNotFoundException("Invalid,venue id not found");
		 
		
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Event Details Saved");
		rs.setData(eventdao.saveEvent(event));
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	//ii)
	public ResponseEntity<ResponseStructure<List<Events>>> findallEvents() {
		ResponseStructure<List<Events>> rs=new ResponseStructure<>();
	     List<Events> ls=eventdao.findallEvents();
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Events details are retrieved");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new NoRecordFoundException("No Details Found");
	}
	//iii)
	public ResponseEntity<ResponseStructure<Events>> findEventDetailsById(int id) {
		ResponseStructure<Events> rs=new ResponseStructure<>();
	     Optional<Events> org =eventdao.findEventById(id);
	     if(org.isPresent()) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Event details are retrieved by  Id");
	    	 rs.setData(org.get());
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("Invalid,Id not Found");
	}
	
	//iv)
	public ResponseEntity<ResponseStructure<Events>> updateEvent(Events event) {
		ResponseStructure<Events> rs=new ResponseStructure<>();
		 if(event.getId()==null) {
			 throw new IdNotFoundException("Events Id Not Found");
		 }

		 Optional<Events> opt=eventdao.findEventById(event.getId());
       if  (opt.isPresent()) {
       	  rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Events details are updated");
	    	 event.setTime(event.getTime());
	    	 rs.setData(eventdao.saveEvent(event));
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
         }
         else
       	  throw new IdNotFoundException("Invalid ,Event Id ");
	}
//	v)
	public ResponseEntity<ResponseStructure<String>> deleteEvent(int id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
	    Optional<Events> opt=eventdao.findEventById(id);
	    if(opt.isPresent()) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Events details deleted");
     	    	 eventdao.deleteEvent(opt.get());
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid ,Id is not Found");
	}
//vi)	
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeesByeventId(int eventId) {
		ResponseStructure<List<Attendee>> rs=new ResponseStructure<>();
	Optional<Events> opt=eventdao.findEventById(eventId);
	if(opt.isEmpty())
		throw new IdNotFoundException("Event Id Not Found");
	
	     List<Attendee> ls=eventdao.getAttendeeByEventId(eventId);
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Attendee details are retrieved by event Id");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new NoRecordFoundException(" Attendee is not registered any Events ");
	}
	
//vii)
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeesByOrganizerId(int oId) {
		ResponseStructure<List<Attendee>> rs=new ResponseStructure<>();
		Optional<Organizer> opt=organizerdao.findOrganizerById(oId);
		if(opt.isEmpty())
			throw new IdNotFoundException("Organizer Id Not Found");
		
	     List<Attendee> ls=eventdao.getAttendeeByOrganizerId(oId);
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Attendees details are retrieved by organizer Id");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new NoRecordFoundException("Attendee is not registered any Events  ");

	}

}
