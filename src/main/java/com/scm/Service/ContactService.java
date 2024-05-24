package com.scm.Service;

import java.util.List;

import com.scm.payloads.ContactDto;

public interface ContactService {


	//create
	ContactDto createContact(ContactDto contact, Integer userId);
	
	

	
//update

	ContactDto updateContact (ContactDto contact, Integer contactId);
	
	//get by id
	ContactDto getContactById(Integer contactId);

	//get all contact
	List<ContactDto> getAllContacts();
	
	//delete

	void deletContact(Integer contactId);
	
	
	
	//get all contacts by user
		List<ContactDto> getContactsByUser(Integer userId);
		
		//search contacts
		List<ContactDto> searchContacts(String keyword);
}
