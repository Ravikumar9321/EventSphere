package com.eventmanagement.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank(message = "Email is required") @Email(message = "Email must be valid") String email,
		String password) {

}
