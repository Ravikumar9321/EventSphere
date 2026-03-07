package com.eventmanagement.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.eventmanagement.Entity.Organizer;
import com.eventmanagement.Repository.Organize_Repository;

@Repository
public class Organize_DAO {
	
	@Autowired
	private Organize_Repository or;

	public Organizer saveOrganizer(Organizer o) {
		return or.save(o);
	}

	public List<Organizer> findallOrganizer() {
		return or.findAll();
	}

	public Optional<Organizer> findOrganizerById(int id) {
		return or.findById(id);
	}

	public void deleteOrganizer(Organizer organizer) {
		// TODO Auto-generated method stub
		or.delete(organizer);
	}

	public Organizer getOrganizerDetailsByEventId(int eventId) {
		// TODO Auto-generated method stub
		return or.getOrganizerByEventId(eventId);
	}

}
