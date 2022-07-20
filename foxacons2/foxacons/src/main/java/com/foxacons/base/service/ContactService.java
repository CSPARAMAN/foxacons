package com.foxacons.base.service;

import java.util.List;

import com.foxacons.base.entity.ContactForm;

public interface ContactService  {
	
	public List<ContactForm> getAllContactForm();
	public boolean addContactForm();

}
