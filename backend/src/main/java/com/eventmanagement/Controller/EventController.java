package com.eventmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.*;
import com.eventmanagement.Service.Events_Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RequestMapping("/api/events")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Events",description = "Events related API's")
public class EventController {
	

	@Autowired
	private Events_Service service;
	
	// i) Add Event
	@Operation(summary = "Add new event", description = "Creates a new event record")
	@PostMapping
	public ResponseEntity<ResponseStructure<Events>> saveEventDetails(@RequestBody Events event) {
	    return service.saveEvent(event);
	}

	// ii) Fetch all Events
	@Operation(summary = "Fetch all events", description = "Returns list of all events")
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Events>>> fetchallEventsDetails() {
	    return service.findallEvents();
	}

	// iii) Fetch Event by ID
	@Operation(summary = "Fetch event by ID", description = "Returns event details for given ID")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Events>> fetchEventDetailsById(@PathVariable int id) {
	    return service.findEventDetailsById(id);
	}

	// iv) Update Event
	@Operation(summary = "Update event", description = "Updates event details")
	@PutMapping
	public ResponseEntity<ResponseStructure<Events>> updateEventDetails(@RequestBody Events event) {
	    return service.updateEvent(event);
	}

	// v) Delete Event
	@Operation(summary = "Delete event", description = "Deletes event by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteEventDetails(@PathVariable int id) {
	    return service.deleteEvent(id);
	}

	// vi) Get Attendees by Event ID
	@Operation(summary = "Get attendees by event ID", description = "Fetches attendees linked to an event")
	@GetMapping("/attendee/{eventId}")
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeesByeventId(@PathVariable int eventId) {
	    return service.getAttendeesByeventId(eventId);
	}

	// vii) Get Attendees by Organizer ID
	@Operation(summary = "Get attendees by organizer ID", description = "Fetches attendees linked to an organizer")
	@GetMapping("/organizer/{oId}")
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeesByOrganizerId(@PathVariable int oId) {
	    return service.getAttendeesByOrganizerId(oId);
	}

		
}
