package com.foxacons.base.repos;

import org.springframework.data.repository.CrudRepository;

import com.foxacons.base.entity.ContactForm;

public interface ContactRepository  extends CrudRepository<ContactForm, Integer>{

}
