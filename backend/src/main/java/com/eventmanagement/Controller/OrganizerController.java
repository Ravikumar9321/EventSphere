package com.eventmanagement.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.Service.Organize_Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RequestMapping("/api/organizers")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Organizer",description = "Organize related API's")

public class OrganizerController {
	

	@Autowired
	private Organize_Service service;
	// i) Add Organizer
	@Operation(summary = "Add new organizer", description = "Creates a new organizer record")
	@PostMapping
	public ResponseEntity<ResponseStructure<Organizer>> saveOrganizerDetails(@RequestBody Organizer o) {
	    return service.saveOrganizer(o);
	}

	// ii) Fetch all Organizers
	@Operation(summary = "Fetch all organizers", description = "Returns list of all organizers")
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Organizer>>> fetchallOrganizerDetails() {
	    return service.findallOrganizer();
	}

	// iii) Fetch Organizer by ID
	@Operation(summary = "Fetch organizer by ID", description = "Returns organizer details for given ID")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Organizer>> fetchOrganizerDetailsById(@PathVariable int id) {
	    return service.findOrganizerDetailsById(id);
	}

	// iv) Update Organizer
	@Operation(summary = "Update organizer", description = "Updates organizer details")
	@PutMapping
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganizerDetails(@RequestBody Organizer o) {
	    return service.updateOrganizer(o);
	}

	// v) Delete Organizer
	@Operation(summary = "Delete organizer", description = "Deletes organizer by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteOrganizerDetails(@PathVariable int id) {
	    return service.deleteOrganizer(id);
	}

	// vi) Fetch Organizer by Event ID
	@Operation(summary = "Fetch organizer by event ID", description = "Returns organizer details linked to a specific event")
	@GetMapping("/event/{eventId}")
	public ResponseEntity<ResponseStructure<Organizer>> fetchOrganizerDetailsByEventId(@PathVariable int eventId) {
	    return service.fetchOrganizerDetailsByEventId(eventId);
	}

}
