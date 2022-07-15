package com.foxacons.base.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ContentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contentId;
	
    private String location;
	private String carouselImg;
	private String carouselDes;

	private String liveProjectImg;
	private String liveProjectDes;

	private String doneProjectImg;
	private String doneProjectDes;

	private String serviceProvideImg;
	private String serviceProvideDes;

	private String Name;
	private String email;
	private long phoneNumber;
	private String Description;

}
