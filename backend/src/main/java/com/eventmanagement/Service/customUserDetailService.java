package com.eventmanagement.Service;


import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.eventmanagement.Entity.UserInfo;
import com.eventmanagement.Exception.NoRecordFoundException;
import com.eventmanagement.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class customUserDetailService  implements UserDetailsService{

	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo user = repository.findByEmail(email).orElseThrow(() -> new NoRecordFoundException("User does not Exist"));

		return User.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities("USER")
				.build();
	}

}
