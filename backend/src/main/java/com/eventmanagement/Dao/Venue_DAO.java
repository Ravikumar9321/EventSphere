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
	private Venue_Repository or;

	public Venue saveVenue(Venue venue) {
		return or.save(venue);
	}

	public List<Venue> findallVenue() {
		return or.findAll();
	}

	public Optional<Venue> findVenueById(int id) {
		return or.findById(id);
	}

	public void deleteVenue(Venue Venue) {
		// TODO Auto-generated method stub
		or.delete(Venue);
	}

	public List<Events> getEventsDetailsByVenueId(int venueId) {
		// TODO Auto-generated method stub
		return or.getEventsByVenueId(venueId);
	}

	public List<Venue> getVenueDetailsBylocation(String location) {
		// TODO Auto-generated method stub
		return or.getEventsByLocation(location);
	}

}
