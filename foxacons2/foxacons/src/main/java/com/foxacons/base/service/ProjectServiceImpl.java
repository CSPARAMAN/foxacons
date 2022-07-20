package com.foxacons.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxacons.base.entity.ProjectEntity;
import com.foxacons.base.repos.ProjectRepository;
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<ProjectEntity> getAllProject() {
                  List<ProjectEntity> projectEntities=(List<ProjectEntity>) projectRepository.findAll();
                  if(projectEntities.size()>0) {
                	  return projectEntities;
                  }
		return null;
	}

	@Override
	public boolean addProject(ProjectEntity projectEntity) {
	if(projectEntity!=null) {
		ProjectEntity projectEntity2=projectRepository.save(projectEntity);
		return true;
		
	}
		return false;
	}

	@Override
	public boolean deleteProject(int id) {
		ProjectEntity projectEntity3=projectRepository.findById(id);
		if(projectEntity3!=null) {
			projectRepository.delete(projectEntity3);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProject(ProjectEntity projectEntity) {
		ProjectEntity projectEntity4=projectRepository.findById(projectEntity.getId());
		if(projectEntity4!=null) {
			projectRepository.save(projectEntity);
			return true;
		}
		return false;
	}
	
	

}
