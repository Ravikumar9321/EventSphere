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
	private Organize_Repository repository;

	public Organizer saveOrganizer(Organizer o) {
		return repository.save(o);
	}

	public List<Organizer> findallOrganizer() {
		return repository.findAll();
	}

	public Optional<Organizer> findOrganizerById(int id) {
		return repository.findById(id);
	}

	public void deleteOrganizer(Organizer organizer) {
		repository.delete(organizer);
	}

	public Organizer getOrganizerDetailsByEventId(int eventId) {
		return repository.getOrganizerByEventId(eventId);
	}

}
