package com.eventmanagement.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.*;
import com.eventmanagement.Service.Venue_Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RequestMapping("/api/venues")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Venue",description = "Venue related API's")
public class VenueController {
	

	@Autowired
	private Venue_Service service;
	// i) Add Venue
	@Operation(summary = "Add new venue", description = "Creates a new venue record")
	@PostMapping
	public ResponseEntity<ResponseStructure<Venue>> saveVenueDetails(@RequestBody Venue o) {
	    return service.saveVenue(o);
	}

	// ii) Fetch all Venues
	@Operation(summary = "Fetch all venues", description = "Returns list of all venues")
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Venue>>> fetchallVenueDetails() {
	    return service.findallVenue();
	}

	// iii) Fetch Venue by ID
	@Operation(summary = "Fetch venue by ID", description = "Returns venue details for given ID")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Venue>> fetchVenueDetailsById(@PathVariable int id) {
	    return service.findVenueDetailsById(id);
	}

	// iv) Update Venue
	@Operation(summary = "Update venue", description = "Updates venue details")
	@PutMapping
	public ResponseEntity<ResponseStructure<Venue>> updateVenueDetails(@RequestBody Venue venue) {
	    return service.updateVenue(venue);
	}

	// v) Delete Venue
	@Operation(summary = "Delete venue", description = "Deletes venue by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteVenueDetails(@PathVariable int id) {
	    return service.deleteVenue(id);
	}

	// vi) Get Events by Venue ID
	@Operation(summary = "Get events by venue ID", description = "Fetches events linked to a specific venue")
	@GetMapping("/event/{venueId}")
	public ResponseEntity<ResponseStructure<List<Events>>> getEventsDetailsByVenueId(@PathVariable int venueId) {
	    return service.getEventsDetailsByVenueId(venueId);
	}

	// vii) Get Venue by Location
	@Operation(summary = "Get venues by location", description = "Fetches venues based on location")
	@GetMapping("/location/{location}")
	public ResponseEntity<ResponseStructure<List<Venue>>> getEventsDetailsBylocation(@PathVariable String location) {
	    return service.getVenueDetailsBylocation(location);
	}

}
