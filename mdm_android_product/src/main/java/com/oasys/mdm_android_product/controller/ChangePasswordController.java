package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.ChangePasswordRequest;
import com.oasys.mdm_android_product.response.DeviceMasterResponse;
import com.oasys.mdm_android_product.services.UserService;

@RestController
@RequestMapping("/mdm")
public class ChangePasswordController {

	@Autowired
	private UserService userService;

// This method is used if we need to change the password.
	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
		// Validate the request and delegate to the service
		DeviceMasterResponse response = userService.changePassword(request.getUserName(), request.getOldPassword(),
				request.getNewPassword());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}