package com.eventmanagement.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.eventmanagement.Dao.Attendee_DAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.*;
import com.eventmanagement.Exception.*;

@Service
public class Attendee_Service {

	@Autowired
	private Attendee_DAO attendeedao;

//i)add Attendee
	public ResponseEntity<ResponseStructure<Attendee>> saveAttendee(Attendee attendee) {
		ResponseStructure<Attendee> rs = new ResponseStructure<>();

		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Attendee Details Saved");

		rs.setData(attendeedao.saveAttendee(attendee));
		return new ResponseEntity<>(rs, HttpStatus.CREATED);
	}

	// ii)
	public ResponseEntity<ResponseStructure<List<Attendee>>> findallAttendees() {
		ResponseStructure<List<Attendee>> rs = new ResponseStructure<>();
		List<Attendee> ls = attendeedao.findallAttendee();
		if (ls.size() > 0) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Attendee details are retrieved");
			rs.setData(ls);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new NoRecordFoundException("No Details Found");
	}

	// iii)
	public ResponseEntity<ResponseStructure<Attendee>> findAttendeeDetailsById(int id) {
		ResponseStructure<Attendee> rs = new ResponseStructure<>();
		Optional<Attendee> org = attendeedao.findAttendeeById(id);
		if (org.isPresent()) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Attendee details are retrieved by  Id");
			rs.setData(org.get());
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Invalid,Id not Found");
	}

	// iv)
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(Attendee Attendee) {
		ResponseStructure<Attendee> rs = new ResponseStructure<>();
		if (Attendee.getId() == null) {
			throw new IdNotFoundException("Attendee Id Not Found");
		}

		Optional<Attendee> opt = attendeedao.findAttendeeById(Attendee.getId());
		if (opt.isPresent()) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Attendee details are updated");
			rs.setData(attendeedao.saveAttendee(Attendee));
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Invalid ,We cannot insert new Id ");
	}

//	v)
	public ResponseEntity<ResponseStructure<String>> deleteAttendee(Integer id) {
		ResponseStructure<String> rs = new ResponseStructure<>();
		Optional<Attendee> opt = attendeedao.findAttendeeById(id);
		if (opt.isPresent()) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Attendee details deleted");
			attendeedao.deleteAttendee(opt.get());
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Invalid ,Id is not Found");
	}

	// vi)
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationsbyattendeeId(int attendeeId) {
		ResponseStructure<List<Registration>> rs = new ResponseStructure<>();
		List<Registration> ls = attendeedao.getRegistrationsbyattendeeId(attendeeId);

		if (ls.size() > 0) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Registration details are retrieved by attendee Id");
			rs.setData(ls);
			return new ResponseEntity<>(rs, HttpStatus.OK);
		} else
			throw new IdNotFoundException("attendee Id Not Found");
	}

//	vii)
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeesbycontact(String contact) {
	    ResponseStructure<Attendee> rs = new ResponseStructure<>();
	    Optional<Attendee> attendeeOpt = attendeedao.findByContact(contact);

	    if (attendeeOpt.isEmpty()) {
	        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
	        rs.setMessage(" No attendee found with this phone number");
	        rs.setData(null);
	        return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
	    }

	    rs.setStatusCode(HttpStatus.OK.value());
	    rs.setMessage("✅ Attendee details retrieved successfully");
	    rs.setData(attendeeOpt.get());
	    return new ResponseEntity<>(rs, HttpStatus.OK);
	}


}
