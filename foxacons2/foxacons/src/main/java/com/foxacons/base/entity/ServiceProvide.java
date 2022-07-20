package com.foxacons.base.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ServiceProvide {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contentId;

	private String carouselImg;
	private String carouselDes;

}
