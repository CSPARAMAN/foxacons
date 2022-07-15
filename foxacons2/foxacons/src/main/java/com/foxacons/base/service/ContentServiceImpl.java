 package com.foxacons.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxacons.base.entity.ContentEntity;
import com.foxacons.base.repos.ContentRepository;


@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private ContentRepository contentRepository;

	@Override
	public List<ContentEntity> getAllContent() {
		List<ContentEntity> details = (List<ContentEntity>) contentRepository.findAll();
		if (details.size() <= 0) {
			return null;
		} else {
			return details;
		}               
		
	}

	@Override
	public boolean addContent(ContentEntity contentEntity) {
		if (contentEntity != null) {
			ContentEntity content = contentRepository.save(contentEntity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteContent(int contentId) {
		ContentEntity details = contentRepository.findByContentId(contentId);
		if (details!= null) {
			contentRepository.delete(details);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateCarousel(ContentEntity contentEntity) {
		boolean isUpdate = false;

		ContentEntity details = contentRepository.findByContentId(contentEntity.getContentId());
		
		if (details != null) {
			contentRepository.save(contentEntity);

			return isUpdate=true;
		}
		return isUpdate;
	}

}
