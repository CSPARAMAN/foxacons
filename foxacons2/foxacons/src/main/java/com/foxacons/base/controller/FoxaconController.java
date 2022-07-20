package com.foxacons.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foxacons.base.entity.ContactForm;
import com.foxacons.base.entity.ProjectEntity;
import com.foxacons.base.jwt.JwtUtil;
import com.foxacons.base.model.Request;
import com.foxacons.base.model.Response;
import com.foxacons.base.repos.ProjectRepository;
import com.foxacons.base.service.AdminImpl;
import com.foxacons.base.service.ContactService;
import com.foxacons.base.service.ProjectService;

@RestController
public class FoxaconController{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AdminImpl adminImpl;

	@Autowired
	private UserDetailsService userDetailsService;

	

	// contact...
	@Autowired
	private ContactService contactService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectRepository projectRepository;

	// Admin .......................

	@PostMapping("/authentication")
	public Response authentication(@RequestBody Request request) throws Exception {
		Response response = new Response();

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getAdminEmail(), request.getAdminPassword()));
		} catch (AuthenticationException e) {
			throw new Exception("invaild id and password", e);
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getAdminEmail());
		String jwt = jwtUtil.generateToken(userDetails);
		response.setMsg("admin logged");
		response.setToken(jwt);

		return response;
	}


	// Contact Controller

	@GetMapping("/getAllContact")
	public ResponseEntity<?> getAllContact() {
		List<ContactForm> contactForms = contactService.getAllContactForm();
		if (contactForms != null) {
			return new ResponseEntity<>(contactForms, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("List Not Found " + 400, HttpStatus.BAD_GATEWAY);
		}
	}

	@PostMapping("/addContact")
	public ResponseEntity<Boolean> addContact(@RequestBody ContactForm contactForm) {
		boolean value = contactService.addContactForm(contactForm);
		if (value) {
			return new ResponseEntity<>(value, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Project Controller......

	@GetMapping("/GetAllProject")
	public ResponseEntity<?> getAllProject() {
		List<ProjectEntity> projectEntities2 = projectService.getAllProject();
		if (projectEntities2 != null) {
			return new ResponseEntity<>(projectEntities2, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("List Not Found " + 400, HttpStatus.BAD_GATEWAY);
		}
	}

	@PostMapping("/addProject")
	public ResponseEntity<Boolean> addProject(@RequestBody ProjectEntity projectEntity) {
		boolean value = projectService.addProject(projectEntity);
		if (value) {
			return new ResponseEntity<>(value, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateProject")
	public ResponseEntity<Boolean> updateProject(@RequestBody ProjectEntity projectEntity) {
		boolean value = projectService.updateProject(projectEntity);
		if (value) {
			return new ResponseEntity<>(value, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteProject")
	public ResponseEntity<Boolean> deleteProject(@PathVariable int id) {
		boolean value = projectService.deleteProject(id);
		if (value) {
			return new ResponseEntity<>(value, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
@GetMapping("/findById{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		ProjectEntity value = projectRepository.findById(id);
		if (value!=null) {
			return new ResponseEntity<>(value, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
