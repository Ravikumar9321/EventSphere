package com.eventmanagement.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.*;

import com.eventmanagement.Entity.Registration;

public interface Registration_Repository extends JpaRepository<Registration, Integer> {
	

	@Query("select e.registrations from Events e where e.id=?1")
	List<Registration> getRegistrationsByEventId(int id);
	
	@Query("select a.registration from Attendee a where a.id=?1")
	Registration getRegistrationsByAttendeeId(int id);
}
