package com.eventmanagement.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.Events;
import com.eventmanagement.Entity.Venue;
import com.eventmanagement.Repository.Venue_Repository;

@Repository
public class Venue_DAO {
	@Autowired
	private Venue_Repository repository;

	public Venue saveVenue(Venue venue) {
		return repository.save(venue);
	}

	public List<Venue> findallVenue() {
		return repository.findAll();
	}

	public Optional<Venue> findVenueById(int id) {
		return repository.findById(id);
	}

	public void deleteVenue(Venue Venue) {
		repository.delete(Venue);
	}

	public List<Events> getEventsDetailsByVenueId(int venueId) {
		return repository.getEventsByVenueId(venueId);
	}

	public List<Venue> getVenueDetailsBylocation(String location) {
		return repository.getEventsByLocation(location);
	}

}
