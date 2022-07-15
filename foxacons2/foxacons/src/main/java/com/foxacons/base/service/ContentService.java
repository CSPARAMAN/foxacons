package com.foxacons.base.service;

import java.util.List;

import com.foxacons.base.entity.ContentEntity;

public interface ContentService {

	public List<ContentEntity> getAllContent();
	
    public boolean addContent(ContentEntity contentEntity);
    
    public boolean deleteContent(int contentId);
    
    public boolean updateCarousel(ContentEntity contentEntity);
}
