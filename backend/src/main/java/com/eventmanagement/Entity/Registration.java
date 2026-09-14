package com.eventmanagement.Entity;

import java.time.LocalDate;


import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

	
	
}
