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

import com.foxacons.base.entity.ContentEntity;
import com.foxacons.base.jwt.JwtUtil;
import com.foxacons.base.model.Request;
import com.foxacons.base.model.Response;
import com.foxacons.base.service.AdminImpl;
import com.foxacons.base.service.ContentService;

@RestController
public class FoxaconController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AdminImpl adminImpl;

	@Autowired
	private UserDetailsService userDetailsService;


	@Autowired
	private ContentService contentService;

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

	// content Controller...........

	@PostMapping("/addContent")
	public ResponseEntity<Boolean> addContent(@RequestBody ContentEntity contentEntity) {
		boolean newContent = contentService.addContent(contentEntity);
		return new ResponseEntity<>(newContent, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteContent")
	public ResponseEntity<?> deleteContent(@PathVariable int contentId) {
		if (contentService.deleteContent(contentId)) {
			return new ResponseEntity<>("Product deleted with id no :" + contentId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("somthing went wrong " + 400, HttpStatus.BAD_GATEWAY);
		}
	}

	@PutMapping("/updatedContent")
	public ResponseEntity<?> updateContent(@RequestBody ContentEntity contentEntity) {
		if (contentService.updateCarousel(contentEntity)) {
			return new ResponseEntity<>(contentEntity, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Product Not Updated " + 400, HttpStatus.BAD_GATEWAY);
		}
	}

	@GetMapping("/getAllContent")
	public ResponseEntity<?> getAllContent() {
		List<ContentEntity> contentEntities = contentService.getAllContent();
		if (contentEntities != null) {
			return new ResponseEntity<>(contentEntities, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("List Not Found " + 400, HttpStatus.BAD_GATEWAY);
		}

	}

}
