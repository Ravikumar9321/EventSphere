package com.eventmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.Entity.Events;
import com.eventmanagement.Entity.Venue;

public interface Venue_Repository extends JpaRepository<Venue, Integer> {
	
	//get events by venue
	@Query("select v.events from Venue v where v.id=?1")
	List<Events> getEventsByVenueId(int id);
	
	//get Venue by Location
	@Query("select v from Venue v where v.location=?1")
	List<Venue> getEventsByLocation(String loc);
	

}
