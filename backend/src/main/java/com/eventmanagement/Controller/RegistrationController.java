package com.eventmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dao.Registration_DAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Service.Organize_Service;
import com.eventmanagement.Service.Registration_Service;


@RequestMapping("/api/register")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
	

	@Autowired
	private Registration_Service service;
	
	///i)add Registration
		@PostMapping("{eventId}/{attendeeId}")
		public ResponseEntity<ResponseStructure<Registration>> saveRegistrationDetails( @RequestBody Registration register,@PathVariable int eventId,@PathVariable int attendeeId){
		  return service.saveRegistration(register,eventId,attendeeId);
		}

 //ii)fetch all Registration details
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Registration>>> fetchallRegistrationDetails() {
			return service.findallRegistrations();
		}
		
		//iii)fetch  Registration details by Id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Registration>> fetchRegistrationDetailsById(@PathVariable int id) {
			return service.findRegistrationDetailsById(id);
		}
		
		//iv)update Registration details
		@PutMapping
		public ResponseEntity<ResponseStructure<Registration>> updateRegistrationDetails(@RequestBody Registration registration){
			return service.updateRegistration(registration);
		}
		
		//V)delete Registration by using id
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteRegistrationDetails(@PathVariable int id){
			return service.deleteRegistration(id);
		}
		
//vi)get Registrations details by Event Id
		@GetMapping("/event/{eventId}")
		public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationDetailsByEventId(@PathVariable int eventId) {
			return service.getRegistrationDetailsByEventId(eventId);
		}
//vii)get Registration by Attendee Id
		@GetMapping("/attendee/{attendeeId}")
		public ResponseEntity<ResponseStructure<Registration>> getRegistrationDetailsByAttendeeId(@PathVariable int attendeeId) {
			return service.getRegistrationDetailsByAttendeeId(attendeeId);
		}
}
