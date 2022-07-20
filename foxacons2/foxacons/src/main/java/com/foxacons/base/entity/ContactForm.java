package com.foxacons.base.entity;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class ContactForm {
   private int id;
   private int phoneNumber;
   private String email;
   private String subject;
   private String description;
   
}
