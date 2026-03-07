package com.eventmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import com.eventmanagement.Dao.Organize_DAO;
import com.eventmanagement.Dao.Venue_DAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Events;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.Exception.*;

import jakarta.persistence.NoResultException;


@Service
public class Venue_Service {
	
	@Autowired
	private Venue_DAO vdao;
//i)add Venue
	public ResponseEntity<ResponseStructure<Venue>> saveVenue(Venue o) {
		ResponseStructure<Venue> rs=new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Venue Details Saved");
		rs.setData(vdao.saveVenue(o));
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	//ii)
	public ResponseEntity<ResponseStructure<List<Venue>>> findallVenue() {
		ResponseStructure<List<Venue>> rs=new ResponseStructure<>();
	     List<Venue> ls=vdao.findallVenue();
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
	     Optional<Venue> org =vdao.findVenueById(id);
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

		 Optional<Venue> opt=vdao.findVenueById(venue.getId());
       if  (opt.isPresent()) {
       	  rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Venue details are updated");
	    	 rs.setData(vdao.saveVenue(venue));
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
         }
         else
       	  throw new IdNotFoundException("Invalid ,We cannot insert new Id ");
	}
//	v)
	public ResponseEntity<ResponseStructure<String>> deleteVenue(int id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
	    Optional<Venue> opt=vdao.findVenueById(id);
	    if(opt.isPresent()) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Venue details deleted");
     	    	 vdao.deleteVenue(opt.get());
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid ,Id is not Found");
	}
	
//vi)
	public ResponseEntity<ResponseStructure<List<Events>>> getEventsDetailsByVenueId(int venueId) {
		ResponseStructure<List<Events>> rs=new ResponseStructure<>();
		          Optional<Venue> opt=vdao.findVenueById(venueId);
		          if(opt.isEmpty())
		          throw new IdNotFoundException("Venue Id Not Found");
		          
	     List<Events> ls=vdao.getEventsDetailsByVenueId(venueId);
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
   
        
      List<Venue> ls=vdao.getVenueDetailsBylocation(location);
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
