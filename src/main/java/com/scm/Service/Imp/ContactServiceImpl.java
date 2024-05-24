package com.scm.Service.Imp;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.exceptions.ResourceNotFoundException;
import com.scm.payloads.ContactDto;

import com.scm.repository.ContactRepository;
import com.scm.repository.UserRepository;

import com.scm.Service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    private UserRepository userRepo;
	
    
    
    
    

    
  //main cascade
	
  	public Contact dtoToCotact(ContactDto contactDto) {
  		Contact contact = this.modelMapper.map(contactDto, Contact.class);

  		return contact;
  	}
  	
  	

	public ContactDto contactToDto(Contact contact) {
		ContactDto contactDto = this.modelMapper.map(contact, ContactDto.class);
		return contactDto;
	}

	@Override
	public ContactDto createContact(ContactDto Contactdto, Integer userId) {
		
		   User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "Id", userId));

		   Contact contact = this.modelMapper.map(Contactdto, Contact.class);
	      
	        contact.setUser(user);
	      
	      Contact newContact = this.contactRepo.save(contact);

	        return this.modelMapper.map(newContact, ContactDto.class);
	}

	@Override
	public ContactDto updateContact(ContactDto contact, Integer contactId) {

		   Contact contacts = this.contactRepo.findById(contactId)
	                .orElseThrow(() -> new ResourceNotFoundException("Contact ", "contact id", contactId));
           
		   
		   contacts.setName(contacts.getName());
		  
	       contacts.setEmail(contacts.getEmail());
	       contacts.setDescription(contacts.getDescription());
	       contacts.setWork(contacts.getWork());
	       contacts.setPhone(contacts.getPhone());
	      
	      
	      Contact updatecontact = this.contactRepo.save(contacts);

	        return this.modelMapper.map(updatecontact, ContactDto.class);
	}

	@Override
	public ContactDto getContactById(Integer contactId) {
		Contact contact = this.contactRepo.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact", " Id ", contactId));

		return this.contactToDto(contact);
	}

	@Override
	public List<ContactDto> getAllContacts() {
		List<Contact> contacts = this.contactRepo.findAll();
		List<ContactDto> contactDtos = contacts.stream().map(contact -> this.contactToDto(contact)).collect(Collectors.toList());

		return contactDtos;
	}

	@Override
	public void deletContact(Integer ContactId) {
		Contact contact = this.contactRepo.findById(ContactId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", ContactId));
		this.contactRepo.delete(contact);
		
	}

//Get all Contact

	@Override
	public List<ContactDto> getContactsByUser(Integer userId) {
		 User user = this.userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
	        List<Contact> contacts = this.contactRepo.findByUser(user);

	        List<ContactDto> contactDtos = contacts.stream().map((contact) -> this.modelMapper.map(contact, ContactDto.class))
	                .collect(Collectors.toList());

	        return contactDtos;
	}


//search
	@Override
	public List<ContactDto> searchContacts(String keyword) {
		 List<Contact> contacts = this.contactRepo.searchByTitle("%" + keyword + "%");
	        List<ContactDto> contactDtos = contacts.stream().map((contact) -> this.modelMapper.map(contact, ContactDto.class)).collect(Collectors.toList());
	        return contactDtos;
	}




}







//contact.setFirstname(contact.getFirstname());
//contact.setSecondName(contact.getSecondName());
//contact.setEmail(contact.getEmail());
//contact.setDescription(contact.getDescription());
//contact.setWork(contact.getWork());
//contact.setPhone(contact.getPhone());
