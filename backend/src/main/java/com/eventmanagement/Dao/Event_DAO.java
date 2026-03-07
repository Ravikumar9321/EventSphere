package com.eventmanagement.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Events;
import com.eventmanagement.Repository.Event_Repository;

@Repository
public class Event_DAO {
	@Autowired
	private Event_Repository er;

	public Events saveEvent(Events event) {
		return er.save(event);
	}

	public List<Events> findallEvents() {
		return er.findAll();
	}

	public Optional<Events> findEventById(int id) {
		return er.findById(id);
	}

	public void deleteEvent(Events event) {
		// TODO Auto-generated method stub
		er.delete(event);
	}

	public List<Attendee> getAttendeeByEventId(int eventId) {
		// TODO Auto-generated method stub
		return er.getAttendeeByEventId(eventId);
	}

	public List<Attendee> getAttendeesByOrganizerId(int oId) {
		// TODO Auto-generated method stub
		return er.getAttendeeByOrganizerId(oId);
	}

}
