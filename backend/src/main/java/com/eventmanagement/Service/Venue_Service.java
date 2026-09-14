package com.eventmanagement.Service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import com.eventmanagement.Dao.Venue_DAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.*;
import com.eventmanagement.Exception.*;



@Service
public class Venue_Service {
	
	@Autowired
	private Venue_DAO venuedao;
//i)add Venue
	public ResponseEntity<ResponseStructure<Venue>> saveVenue(Venue o) {
		ResponseStructure<Venue> rs=new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Venue Details Saved");
		rs.setData(venuedao.saveVenue(o));
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	//ii)
	public ResponseEntity<ResponseStructure<List<Venue>>> findallVenue() {
		ResponseStructure<List<Venue>> rs=new ResponseStructure<>();
	     List<Venue> ls=venuedao.findallVenue();
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Venue details are retrieved");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new NoRecordFoundException("No Details Found");
	}
	//iii)
	public ResponseEntity<ResponseStructure<Venue>> findVenueDetailsById(int id) {
		ResponseStructure<Venue> rs=new ResponseStructure<>();
	     Optional<Venue> org =venuedao.findVenueById(id);
	     if(org.isPresent()) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Venue details are retrieved by  Id");
	    	 rs.setData(org.get());
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("Invalid,Id not Found");
	}
	
	//iv)
	public ResponseEntity<ResponseStructure<Venue>> updateVenue(Venue venue) {
		ResponseStructure<Venue> rs=new ResponseStructure<>();
		 if(venue.getId()==null) {
			 throw new IdNotFoundException("Venue Id Not Found");
		 }

		 Optional<Venue> opt=venuedao.findVenueById(venue.getId());
       if  (opt.isPresent()) {
       	  rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Venue details are updated");
	    	 rs.setData(venuedao.saveVenue(venue));
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
         }
         else
       	  throw new IdNotFoundException("Invalid ,We cannot insert new Id ");
	}
//	v)
	public ResponseEntity<ResponseStructure<String>> deleteVenue(int id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
	    Optional<Venue> opt=venuedao.findVenueById(id);
	    if(opt.isPresent()) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Venue details deleted");
     	    	 venuedao.deleteVenue(opt.get());
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid ,Id is not Found");
	}
	
//vi)
	public ResponseEntity<ResponseStructure<List<Events>>> getEventsDetailsByVenueId(int venueId) {
		ResponseStructure<List<Events>> rs=new ResponseStructure<>();
		          Optional<Venue> opt=venuedao.findVenueById(venueId);
		          if(opt.isEmpty())
		          throw new IdNotFoundException("Venue Id Not Found");
		          
	     List<Events> ls=venuedao.getEventsDetailsByVenueId(venueId);
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Events details are retrieved by venue Id");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     
	     else
	    	 throw new NoRecordFoundException("Venue Details not Found");
	}
	
//vii)
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenueDetailsBylocation(String location) {
		ResponseStructure<List<Venue>> rs=new ResponseStructure<>();
   
        
      List<Venue> ls=venuedao.getVenueDetailsBylocation(location);
      if(ls.size()>0) {
	      rs.setStatusCode(HttpStatus.OK.value());
	      rs.setMessage("Venue details are retrieved by location");
	      rs.setData(ls);
	      return new ResponseEntity<>(rs,HttpStatus.OK);
          }
       else
	       throw new NoRecordFoundException("Venue Not Found in that location");
	   }

}
