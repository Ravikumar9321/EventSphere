package com.eventmanagement.Dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Entity.UserInfo;
import com.eventmanagement.Repository.UserRepository;



@Repository
public class UserDAO {

	@Autowired
	private UserRepository repository;
	
	public List<UserInfo> getAllUser() {
		return repository.findAll();

	}

	public UserInfo createuser(UserInfo user) {
		return repository.save(user);
	}
}
