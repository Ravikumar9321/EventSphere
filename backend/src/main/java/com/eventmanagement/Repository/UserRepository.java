package com.eventmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanagement.Entity.UserInfo;


public interface UserRepository extends JpaRepository<UserInfo, Integer> {
	Optional<UserInfo> findByEmail(String email);

}
