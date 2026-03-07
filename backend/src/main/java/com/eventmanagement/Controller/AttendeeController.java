package com.eventmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Service.Attendee_Service;


@RequestMapping("/api/attendee")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AttendeeController {
	

	@Autowired
	private Attendee_Service service;
	
	///i)add Attendee
		@PostMapping
		public ResponseEntity<ResponseStructure<Attendee>> saveAttendeeDetails( @RequestBody Attendee attendee){
		  return service.saveAttendee(attendee);
		}

 //ii)fetch all Attendee details
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Attendee>>> fetchallAttendeeDetails() {
			return service.findallAttendees();
		}
		
//iii)fetch  Attendee details by Id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Attendee>> fetchAttendeeDetailsById(@PathVariable int id) {
			return service.findAttendeeDetailsById(id);
		}
		
//iv)update Attendee details
		@PutMapping
		public ResponseEntity<ResponseStructure<Attendee>> updateAttendeeDetails(@RequestBody Attendee attendee){
			return service.updateAttendee(attendee);
		}
		
//V)delete Attendee by using id
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteAttendeeDetails(@PathVariable Integer id){
			return service.deleteAttendee(id);
		}
		
//vi)get Registrations by attendee id
		@GetMapping("/register/{attendeeId}")
		public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationsbyattendeeId( @PathVariable int attendeeId) {
			return service.getRegistrationsbyattendeeId(attendeeId);
		}
		
		
//vii)get Attendee by contact
		@GetMapping("/contact/{contact}")
		public ResponseEntity<ResponseStructure<Attendee>> getAttendeesbycontact( @PathVariable long contact) {
			return service.getAttendeesbycontact(contact);
		}
		
}
