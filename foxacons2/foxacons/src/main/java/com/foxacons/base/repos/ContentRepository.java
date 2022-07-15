package com.foxacons.base.repos;

import org.springframework.data.repository.CrudRepository;

import com.foxacons.base.entity.ContentEntity;

public interface ContentRepository extends CrudRepository<ContentEntity, Integer> {
	public ContentEntity findByContentId(int contentId);

}
