package com.iesvjp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesvjp.model.Contact;

@Repository("contactRepository")
public interface IContactRepository extends JpaRepository<Contact, Integer> {
	public Contact findById(int id);
}
