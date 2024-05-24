package com.scm.payloads;

import java.util.ArrayList;
import java.util.List;

import com.scm.entities.Contact;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	
	private String name;


	private String email;

	private String password;

	private String about;
	
	
//	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user" , fetch = FetchType.LAZY )
//	private List<Contact> contacts = new ArrayList<>();
}
