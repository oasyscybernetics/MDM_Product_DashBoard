package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.response.RegisterDTO;
import com.oasys.mdm_android_product.response.UserDTO;
import com.oasys.mdm_android_product.service.auth.AuthService;

@RestController
public class RegisterController {

	@Autowired
	private AuthService authService;


	@PostMapping("/register")
	public ResponseEntity<?> signupUser(@RequestBody RegisterDTO registerDTO) {
		UserDTO createdUser = authService.createUser(registerDTO);
		if (createdUser == null) {
			return new ResponseEntity<>("User not created!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

}
