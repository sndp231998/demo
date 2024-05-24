package com.scm.payloads;

import com.scm.entities.User;

import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
public class LoginDto {
	 private String email;
	    private String password;
	    @ManyToOne
		private User user;
}
