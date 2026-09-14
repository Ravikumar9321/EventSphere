package com.eventmanagement.Entity;


import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Events {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;

	@CreationTimestamp
	private LocalTime time;
	private String date;
	private String description;
	
	 @ManyToOne
	    @JoinColumn(name = "venue_id")
	    private Venue venue;
	 
	 @ManyToOne
	    @JoinColumn(name = "organizer_id")
	    private Organizer organizer;
	 
	 @JsonIgnore
	 @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	    private List<Registration> registrations;

	
}
