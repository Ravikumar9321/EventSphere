package com.eventmanagement.Dao;

import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Events;
import com.eventmanagement.Repository.Event_Repository;

@Repository
public class Event_DAO {
	@Autowired
	private Event_Repository repository;

	public Events saveEvent(Events event) {
		return repository.save(event);
	}

	public List<Events> findallEvents() {
		return repository.findAll();
	}

	public Optional<Events> findEventById(int id) {
		return repository.findById(id);
	}

	public void deleteEvent(Events event) {
		repository.delete(event);
	}

	public List<Attendee> getAttendeeByEventId(int eventId) {
		return repository.getAttendeeByEventId(eventId);
	}

	public List<Attendee> getAttendeeByOrganizerId(int oId) {
		return repository.getAttendeeByOrganizerId(oId);
	}

}
