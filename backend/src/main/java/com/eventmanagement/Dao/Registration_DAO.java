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
	private Registration_Repository repository;

	public Registration saveRegistration(Registration registration) {
		return repository.save(registration);
	}

	public List<Registration> findallRegistration() {
		return repository.findAll();
	}

	public Optional<Registration> findRegistrationById(int id) {
		return repository.findById(id);
	}

	public void deleteRegistration(Registration registration) {
		repository.delete(registration);
	}

	public List<Registration> getRegistrationsByEventId(int eventId) {
		return repository.getRegistrationsByEventId(eventId);
	}

	public Registration getRegistrationDetailsByAttendeeId(int attendeeId) {
		return repository.getRegistrationsByAttendeeId(attendeeId);
	}

}
