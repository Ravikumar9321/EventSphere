package com.eventmanagement.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.eventmanagement.Dao.UserDAO;
import com.eventmanagement.Dto.ResponseStructure;
import com.eventmanagement.Entity.UserInfo;
import com.eventmanagement.Exception.NoRecordFoundException;



@Service
public class UserService {

	@Autowired
	private UserDAO userdao;

	public ResponseEntity<ResponseStructure<List<UserInfo>>> getAllUser() {
		ResponseStructure<List<UserInfo>> response = new ResponseStructure<List<UserInfo>>();
		List<UserInfo> list = userdao.getAllUser();
		if (list.size() >= 1) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Retrieved ");
			response.setData(list);
		} else
			throw new NoRecordFoundException("User details not found");

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	public ResponseEntity<ResponseStructure<UserInfo>> createuser(UserInfo user) {
		ResponseStructure<UserInfo> response = new ResponseStructure<>();
		UserInfo user1 = userdao.createuser(user);

		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Created Successfully ");
		response.setData(user1);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
