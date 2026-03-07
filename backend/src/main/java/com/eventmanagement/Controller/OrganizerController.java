package com.eventmanagement.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.Service.Organize_Service;


@RequestMapping("/api/organizers")
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class OrganizerController {
	

	@Autowired
	private Organize_Service service;
	
	///i)add Organizer
		@PostMapping
		public ResponseEntity<ResponseStructure<Organizer>> saveOrganizerDetails( @RequestBody Organizer o){
		  return service.saveOrganizer(o);
		}

 //ii)fetch all Organizer details
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Organizer>>> fetchallOrganizerDetails() {
			return service.findallOrganizer();
		}
		
		//iii)fetch  Organizer details by Id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Organizer>> fetchOrganizerDetailsById(@PathVariable int id) {
			return service.findOrganizerDetailsById(id);
		}
		
		//iv)update Organizer details
		@PutMapping
		public ResponseEntity<ResponseStructure<Organizer>> updateOrganizerDetails(@RequestBody Organizer o){
			return service.updateOrganizer(o);
		}
		
		//V)delete Organizer by using id
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteOrganizerDetails(@PathVariable int id){
			return service.deleteOrganizer(id);
		}
//vi)fetch  Organizer details by EventId
				@GetMapping("/event/{eventId}")
				public ResponseEntity<ResponseStructure<Organizer>> fetchOrganizerDetailsByEventId(@PathVariable int eventId) {
					return service.fetchOrganizerDetailsByEventId(eventId);
				}
		
}
