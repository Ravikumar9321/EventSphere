package com.eventmanagement.Entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@CreationTimestamp
	private LocalDate date;
	
	 @ManyToOne
	    @JoinColumn(name = "event_id")
	    private Events event;
	 
		
         @ManyToOne
	    @JoinColumn(name = "attendee_id")
	    private Attendee attendees;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}

	public Attendee getAttendees() {
		return attendees;
	}

	public void setAttendees(Attendee attendees) {
		this.attendees = attendees;
	}

	
	
	
}
