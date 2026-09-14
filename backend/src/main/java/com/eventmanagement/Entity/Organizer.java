
package com.eventmanagement.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Organizer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String  organizer;
	
	@JsonIgnore
	 @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
	    private List<Events> events;

	

}
