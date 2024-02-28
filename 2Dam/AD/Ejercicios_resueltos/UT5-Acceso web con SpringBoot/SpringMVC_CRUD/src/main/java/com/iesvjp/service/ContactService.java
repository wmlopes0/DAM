package com.iesvjp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iesvjp.model.Contact;
import com.iesvjp.repository.IContactRepository;

@Service("contactService")
public class ContactService implements IContactService {

	@Autowired
	@Qualifier("contactRepository")
	private IContactRepository contactRepository;

	@Override
	public Contact addContact(Contact contactModel) {
		Contact contact = contactRepository.save(contactModel);
		return contact;
	}

	@Override
	public List<Contact> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		return contacts;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if (contact != null) {
			contactRepository.delete(contact);
		}
	}
}
