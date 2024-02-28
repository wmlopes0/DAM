package com.iesvjp.service;

import java.util.List;

import com.iesvjp.model.Contact;

public interface IContactService {
	
	public Contact addContact(Contact contactModel);
	public List<Contact> listAllContacts();
	public Contact findContactById(int id);
	public void removeContact(int id);
}