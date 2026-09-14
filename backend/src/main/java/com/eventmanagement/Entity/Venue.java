package com.eventmanagement.Entity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Venue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String location;
	private int capacity;
	
      @JsonIgnore
	  @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
	    private List<Events> events;

	

}
