package com.scm.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
	
	List<Contact> findByUser(User user);
	
	@Query("select p from Contact p where p.name like :key")
	List<Contact> searchByTitle(@Param("key") String name);
}
