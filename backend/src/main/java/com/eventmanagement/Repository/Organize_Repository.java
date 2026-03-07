package com.eventmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.Entity.Organizer;

public interface Organize_Repository extends JpaRepository<Organizer, Integer> {
	
	//get Organizer by Event Id
  @Query("select e.organizer from Events e where e.id=?1")
   Organizer getOrganizerByEventId(int eventId);
	
}
