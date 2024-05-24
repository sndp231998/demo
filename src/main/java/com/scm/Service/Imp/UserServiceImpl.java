package com.scm.Service.Imp;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.modelmapper.ModelMapper;

import com.scm.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.payloads.ApiResponse;
import com.scm.payloads.LoginDto;
import com.scm.payloads.UserDto;

import com.scm.repository.UserRepository;

import com.scm.Service.UserService;
import com.scm.exceptions.AuthenticationFailedException;
import com.scm.exceptions.ResourceNotFoundException;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	


	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
 user.setName(userdto.getName());
 user.setEmail(userdto.getEmail());
 user.setPassword(userdto.getPassword());
 user.setAbout(userdto.getAbout());
 
		
	  User updateuser=this.userRepo.save(user);  
	  UserDto userDto1=this.userToDto(updateuser);
	  return userDto1;
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		
	}
	
	//main cascade
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	

	@Override
	public UserDto registerNewUser(UserDto userDto) {
	
		User users = this.modelMapper.map(userDto, User.class);

	//......
		
		User newUser = this.userRepo.save(users);

		return this.modelMapper.map(newUser, UserDto.class);
	}

	
	@Override
	public ApiResponse login(LoginDto loginDto) {
		 User user = userRepo.findByEmail(loginDto.getEmail());
		 
	        if (user == null) {
	            throw new RuntimeException((loginDto.getPassword()));
	            }else
	                if (user.getPassword().equals(loginDto.getPassword())) {
	                	  int userId = user.getId();
	                	  String name=user.getName();
	                	 
	                	  System.out.print(name);
	                	return new ApiResponse("Login Success ",true, name, userId);
	        }else
	        throw new RuntimeException("Password mismatch.");

	        }

//	@Override
//	public UserDto login(String email, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

}
