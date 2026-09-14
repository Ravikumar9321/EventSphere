package com.eventmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Service.Attendee_Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RequestMapping("/api/attendee")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Attendee",description = "Attendee related API's")
public class AttendeeController {
	

	@Autowired
	private Attendee_Service service;
	// i) Add Attendee
	@Operation(summary = "Add new attendee", description = "Creates a new attendee record")
	@PostMapping
	public ResponseEntity<ResponseStructure<Attendee>> saveAttendeeDetails(@Valid  @RequestBody Attendee attendee) {
	    return service.saveAttendee(attendee);
	}

	// ii) Fetch all Attendees
	@Operation(summary = "Fetch all attendees", description = "Returns list of all attendees")
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Attendee>>> fetchallAttendeeDetails() {
	    return service.findallAttendees();
	}

	// iii) Fetch Attendee by Id
	@Operation(summary = "Fetch attendee by ID", description = "Returns attendee details for given ID")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Attendee>> fetchAttendeeDetailsById(@PathVariable int id) {
	    return service.findAttendeeDetailsById(id);
	}

	// iv) Update Attendee
	@Operation(summary = "Update attendee", description = "Updates attendee details")
	@PutMapping
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendeeDetails(@RequestBody Attendee attendee) {
	    return service.updateAttendee(attendee);
	}

	// v) Delete Attendee
	@Operation(summary = "Delete attendee", description = "Deletes attendee by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAttendeeDetails(@PathVariable Integer id) {
	    return service.deleteAttendee(id);
	}

	// vi) Get Registrations by Attendee ID
	@Operation(summary = "Get registrations by attendee ID", description = "Fetches registrations linked to an attendee")
	@GetMapping("/register/{attendeeId}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationsbyattendeeId(@PathVariable int attendeeId) {
	    return service.getRegistrationsbyattendeeId(attendeeId);
	}

	// vii) Get Attendee by Contact
	@Operation(summary = "Get attendee by contact", description = "Fetches attendee details using contact number")
	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<Attendee>> getAttendeesbycontact(@PathVariable String contact) {
	    return service.getAttendeesbycontact(contact);
	}

}
