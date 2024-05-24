package com.scm.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.entities.User;


public interface UserRepository extends JpaRepository<User,Integer> {

//	User findByEmail(String email);
//	
	 User findByPassword(String password);

	    User findByEmail(String email);
	
}
