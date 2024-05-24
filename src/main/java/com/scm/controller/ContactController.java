package com.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.Service.ContactService;
import com.scm.Service.UserService;
import com.scm.payloads.ApiResponse;
import com.scm.payloads.ContactDto;
import com.scm.payloads.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class ContactController {


	@Autowired
	private ContactService contactService;


	// POST-create user
	@PostMapping("/user/{userId}/contacts")
	public ResponseEntity<ContactDto> createContact(@Valid @RequestBody ContactDto contactDto,@PathVariable Integer userId) {
		ContactDto createContactDto = this.contactService.createContact(contactDto,userId);
		return new ResponseEntity<ContactDto>(createContactDto, HttpStatus.CREATED);
	}
	

	
	
	
	// PUT- update contact

		@PutMapping("/{contactId}")
		public ResponseEntity<ContactDto> updateUser(@Valid @RequestBody ContactDto contactDto, @PathVariable("contactId") Integer cid) {
			ContactDto updatedContact = this.contactService.updateContact(contactDto, cid);
			return ResponseEntity.ok(updatedContact);
		}
		
		// GET - contacts
		@GetMapping("/")
		public ResponseEntity<List<ContactDto>> getAllContacts() {
			return ResponseEntity.ok(this.contactService.getAllContacts());
		}
	
		//getall contact by user
		
		@GetMapping("/user/{userId}/contacts")
		public ResponseEntity<List<ContactDto>> getContactsByUser(@PathVariable Integer userId) {

			List<ContactDto> contacts = this.contactService.getContactsByUser(userId);
			return new ResponseEntity<List<ContactDto>>(contacts, HttpStatus.OK);

		}
		// delete post
		@DeleteMapping("/contacts/{contactId}")
		public ApiResponse deleteContact(@PathVariable Integer contactId) {
			this.contactService.deletContact(contactId);
			return new ApiResponse("contact is successfully deleted !!", true, null, contactId);
		}
}
