package com.foxacons.base.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foxacons.base.entity.Admin;
import com.foxacons.base.entity.MyAdminDetails;
import com.foxacons.base.repos.AdminRepository;

@Service
public class AdminImpl  implements UserDetailsService{
	
    @Autowired
	private AdminRepository adminRepository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Admin admin = adminRepository.findByAdminEmail(username);
	  if(admin!=null) {
		  return new MyAdminDetails(admin);
	  }throw new UsernameNotFoundException("user not found");
		  
	  
	}
	
	public Admin addData(Admin admin) {
		return adminRepository.save(admin);
	}

}

