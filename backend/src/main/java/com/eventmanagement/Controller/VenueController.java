package com.eventmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Events;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.Service.Organize_Service;
import com.eventmanagement.Service.Venue_Service;


@RequestMapping("/api/venues")
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class VenueController {
	

	@Autowired
	private Venue_Service service;
	
	///i)add Venue
		@PostMapping
		public ResponseEntity<ResponseStructure<Venue>> saveVenueDetails( @RequestBody Venue o){
		  return service.saveVenue(o);
		}

 //ii)fetch all Venue details
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Venue>>> fetchallVenueDetails() {
			return service.findallVenue();
		}
		
//iii)fetch  Venue details by Id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Venue>> fetchVenueDetailsById(@PathVariable int id) {
			return service.findVenueDetailsById(id);
		}
		
//iv)update Venue details
		@PutMapping
		public ResponseEntity<ResponseStructure<Venue>> updateVenueDetails(@RequestBody Venue venue){
			return service.updateVenue(venue);
		}
		
//V)delete Venue by using id
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteVenueDetails(@PathVariable int id){
			return service.deleteVenue(id);
		}
//vi)get Events details by venue id
				@GetMapping("/event/{venueId}")
				public ResponseEntity<ResponseStructure<List<Events>>> getEventsDetailsByVenueId(@PathVariable int venueId) {
					return service.getEventsDetailsByVenueId(venueId);
				}
				
///vi)get Venue details by location 
				@GetMapping("/location/{location}")
				public ResponseEntity<ResponseStructure<List<Venue>>> getEventsDetailsBylocation(@PathVariable String location) {
					return service.getVenueDetailsBylocation(location);
				}
		
}
