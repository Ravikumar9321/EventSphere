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
	private Attendee_Repository ar;

	public Attendee saveAttendee(Attendee attendee) {
		return ar.save(attendee);
	}

	public List<Attendee> findallAttendee() {
		return ar.findAll();
	}

	public Optional<Attendee> findAttendeeById(Integer id) {
		return ar.findById(id);
	}

	public void deleteAttendee(Attendee attendee) {
		// TODO Auto-generated method stub
		ar.delete(attendee);
	}

	public List<Registration> getRegistrationsbyattendeeId(int attendeeId) {
		// TODO Auto-generated method stub
		return ar.getRegistrationsbyattendeeId(attendeeId);
	}

	public Attendee getRegistrationsbyattendeeId(long contact) {
		// TODO Auto-generated method stub
		return ar.getAttendeeByContact(contact);
	}

}
