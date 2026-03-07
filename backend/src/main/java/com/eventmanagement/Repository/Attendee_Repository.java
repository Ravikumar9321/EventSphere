package com.eventmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.Entity.Attendee;
import com.eventmanagement.Entity.Registration;

public interface Attendee_Repository extends JpaRepository<Attendee, Integer> {
	

	@Query("select a.registration from Attendee a where a.id=?1")
	 List<Registration> getRegistrationsbyattendeeId(Integer id);
	
	@Query("select a from Attendee a where a.contact=?1")
	 	Attendee getAttendeeByContact(long contact);
}
