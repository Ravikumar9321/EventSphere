package com.eventmanagement.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Service.Registration_Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RequestMapping("/api/register")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Registration",description = "Register related API's")

public class RegistrationController {
	

	@Autowired
	private Registration_Service service;
	// i) Add Registration
	@Operation(summary = "Add new registration", description = "Creates a new registration for an attendee in an event")
	@PostMapping("{eventId}/{attendeeId}")
	public ResponseEntity<ResponseStructure<Registration>> saveRegistrationDetails(
	        @RequestBody Registration register,
	        @PathVariable int eventId,
	        @PathVariable int attendeeId) {
	    return service.saveRegistration(register, eventId, attendeeId);
	}

	// ii) Fetch all Registrations
	@Operation(summary = "Fetch all registrations", description = "Returns list of all registrations")
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Registration>>> fetchallRegistrationDetails() {
	    return service.findallRegistrations();
	}

	// iii) Fetch Registration by ID
	@Operation(summary = "Fetch registration by ID", description = "Returns registration details for given ID")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Registration>> fetchRegistrationDetailsById(@PathVariable int id) {
	    return service.findRegistrationDetailsById(id);
	}

	// iv) Update Registration
	@Operation(summary = "Update registration", description = "Updates registration details")
	@PutMapping
	public ResponseEntity<ResponseStructure<Registration>> updateRegistrationDetails(@RequestBody Registration registration) {
	    return service.updateRegistration(registration);
	}

	// v) Delete Registration
	@Operation(summary = "Delete registration", description = "Deletes registration by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteRegistrationDetails(@PathVariable int id) {
	    return service.deleteRegistration(id);
	}

	// vi) Get Registrations by Event ID
	@Operation(summary = "Get registrations by event ID", description = "Fetches registrations linked to a specific event")
	@GetMapping("/event/{eventId}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationDetailsByEventId(@PathVariable int eventId) {
	    return service.getRegistrationDetailsByEventId(eventId);
	}

	// vii) Get Registration by Attendee ID
	@Operation(summary = "Get registration by attendee ID", description = "Fetches registration details linked to a specific attendee")
	@GetMapping("/attendee/{attendeeId}")
	public ResponseEntity<ResponseStructure<Registration>> getRegistrationDetailsByAttendeeId(@PathVariable int attendeeId) {
	    return service.getRegistrationDetailsByAttendeeId(attendeeId);
	}
}
