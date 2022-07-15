package com.foxacons.base.repos;


import org.springframework.data.repository.CrudRepository;

import com.foxacons.base.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    
	public Admin findByAdminEmail(String adminEmail); 
}
