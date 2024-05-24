package com.scm.Service;

import java.util.List;

import com.scm.payloads.ApiResponse;
import com.scm.payloads.LoginDto;
import com.scm.payloads.UserDto;


public interface UserService {

	
UserDto registerNewUser(UserDto user);
	
	
	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto userdto, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
	
	//UserDto login(String email, String password);
	
	 ApiResponse login(LoginDto loginDto);
}
