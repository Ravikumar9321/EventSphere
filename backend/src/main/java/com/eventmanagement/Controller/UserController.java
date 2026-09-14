package com.eventmanagement.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.UserInfo;
import com.eventmanagement.Service.UserService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/user")
@Hidden
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<UserInfo>>> getAllUser() {
		return service.getAllUser();
	}

}