package com.eventmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Events;

public interface Event_Repository extends JpaRepository<Events, Integer> {
	
// get attendee by event id
	@Query("select r.attendees from Registration r where r.event.id=?1")
	List<Attendee> getAttendeeByEventId(int id);
	//get Attendee by organizer id
	@Query("select r.attendees from Registration r where r.event.organizer.id=?1")
	List<Attendee> getAttendeeByOrganizerId(int id);
	
}
