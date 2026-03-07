package com.eventmanagement.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.Registration;
import com.eventmanagement.Repository.Registration_Repository;

@Repository
public class Registration_DAO {
	@Autowired
	private Registration_Repository rr;

	public Registration saveRegistration(Registration registration) {
		return rr.save(registration);
	}

	public List<Registration> findallRegistration() {
		return rr.findAll();
	}

	public Optional<Registration> findRegistrationById(int id) {
		return rr.findById(id);
	}

	public void deleteRegistration(Registration registration) {
		// TODO Auto-generated method stub
		rr.delete(registration);
	}

	public List<Registration> getRegistrationsByEventId(int eventId) {
		// TODO Auto-generated method stub
		return rr.getRegistrationsByEventId(eventId);
	}

	public Registration getRegistrationDetailsByAttendeeId(int attendeeId) {
		// TODO Auto-generated method stub
		return rr.getRegistrationsByAttendeeId(attendeeId);
	}

}
