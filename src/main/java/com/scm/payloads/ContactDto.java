package com.scm.payloads;



import com.scm.entities.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ContactDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int contactId;
	
	private String name;

	
	private String work;
	
	private String email;
	
	private String phone;
	
	private String description;
	
	@ManyToOne
	private User user;
}
