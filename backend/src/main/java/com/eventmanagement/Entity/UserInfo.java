package com.eventmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	

}
