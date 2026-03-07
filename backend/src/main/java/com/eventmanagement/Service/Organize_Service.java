package com.eventmanagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.eventmanagement.Dao.Event_DAO;
import com.eventmanagement.Dao.Organize_DAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Events;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.Exception.*;


@Service
public class Organize_Service {
	
	@Autowired
	private Organize_DAO odao;
	@Autowired
	private Event_DAO edao;
//i)add organizer
	public ResponseEntity<ResponseStructure<Organizer>> saveOrganizer(Organizer o) {
		ResponseStructure<Organizer> rs=new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Organizer Details Saved");
		rs.setData(odao.saveOrganizer(o));
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	//ii)
	public ResponseEntity<ResponseStructure<List<Organizer>>> findallOrganizer() {
		ResponseStructure<List<Organizer>> rs=new ResponseStructure<>();
	     List<Organizer> ls=odao.findallOrganizer();
	     if(ls.size()>0) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Organizer details are retrieved");
	    	 rs.setData(ls);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new NoRecordFoundException("No Details Found");
	}
	//iii)
	public ResponseEntity<ResponseStructure<Organizer>> findOrganizerDetailsById(int id) {
		ResponseStructure<Organizer> rs=new ResponseStructure<>();
	     Optional<Organizer> org =odao.findOrganizerById(id);
	     if(org.isPresent()) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Organizer details are retrieved by  Id");
	    	 rs.setData(org.get());
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("Invalid,Id not Found");
	}
	
	//iv)
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(Organizer o) {
		ResponseStructure<Organizer> rs=new ResponseStructure<>();
		 if(o.getId()==null) {
			 throw new IdNotFoundException("Organizer Id Not Found");
		 }

		 Optional<Organizer> opt=odao.findOrganizerById(o.getId());
       if  (opt.isPresent()) {
       	  rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Organizer details are updated");
	    	 rs.setData(odao.saveOrganizer(o));
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
         }
         else
       	  throw new IdNotFoundException("Invalid ,We cannot insert new Id ");
	}
//	v)
	public ResponseEntity<ResponseStructure<String>> deleteOrganizer(int id) {
		ResponseStructure<String> rs=new ResponseStructure<>();
	    Optional<Organizer> opt=odao.findOrganizerById(id);
	    if(opt.isPresent()) {
	    	  rs.setStatusCode(HttpStatus.OK.value());
     	    	 rs.setMessage("Organizer details deleted");
     	    	 odao.deleteOrganizer(opt.get());
     	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	    }
	    else
      	  throw new IdNotFoundException("Invalid ,Id is not Found");
	}
	
	//vi)
	public ResponseEntity<ResponseStructure<Organizer>> fetchOrganizerDetailsByEventId(int eventId) {
		ResponseStructure<Organizer> rs=new ResponseStructure<>();
		
	     Organizer organize=odao.getOrganizerDetailsByEventId(eventId);
	     if(organize!=null) {
	    	 rs.setStatusCode(HttpStatus.OK.value());
	    	 rs.setMessage("Organizer details are retrieved by event Id");
	    	 rs.setData(organize);
	    	 return new ResponseEntity<>(rs,HttpStatus.OK);
	     }
	     else
	    	 throw new IdNotFoundException("Invalid Event Id Not Found");
	}

}
