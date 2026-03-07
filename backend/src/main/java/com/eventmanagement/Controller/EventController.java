package com.eventmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.*;
import com.eventmanagement.Service.Events_Service;


@RequestMapping("/api/events")
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class EventController {
	

	@Autowired
	private Events_Service service;
	
	///i)add Events
		@PostMapping
		public ResponseEntity<ResponseStructure<Events>> saveEventDetails( @RequestBody Events event){
		  return service.saveEvent(event);
		}

 //ii)fetch all Events details
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Events>>> fetchallEventsDetails() {
			return service.findallEvents();
		}
		
//iii)fetch  Event details by Id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Events>> fetchEventDetailsById(@PathVariable int id) {
			return service.findEventDetailsById(id);
		}
		
//iv)update Event details
		@PutMapping
		public ResponseEntity<ResponseStructure<Events>> updateEventDetails(@RequestBody Events event){
			return service.updateEvent(event);
		}
		
//V)delete Event by using id
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteEventDetails(@PathVariable int id){
			return service.deleteEvent(id);
		}
		
//vi)get Attendees by Event id
		@GetMapping("/attendee/{eventId}")
		public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeesByeventId( @PathVariable int eventId) {
			return service.getAttendeesByeventId(eventId);
		}
		
//vi)get Attendees by Organizer id
		@GetMapping("/organizer/{oId}")
		public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeesByOrganizerId( @PathVariable int oId) {
			return service.getAttendeesByOrganizerId(oId);
		}
		
}
