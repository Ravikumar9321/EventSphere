package com.eventmanagement.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Repository.Attendee_Repository;

@Repository
public class Attendee_DAO {
	@Autowired
	private Attendee_Repository repository;

	public Attendee saveAttendee(Attendee attendee) {
		return repository.save(attendee);
	}

	public List<Attendee> findallAttendee() {
		return repository.findAll();
	}

	public Optional<Attendee> findAttendeeById(Integer id) {
		return repository.findById(id);
	}

	public void deleteAttendee(Attendee attendee) {
		repository.delete(attendee);
	}

	public List<Registration> getRegistrationsbyattendeeId(int attendeeId) {
		return repository.getRegistrationsbyattendeeId(attendeeId);
	}

	

	public Optional<Attendee> findByContact(String contact) {
		return repository.findByContact(contact);
	}

}
