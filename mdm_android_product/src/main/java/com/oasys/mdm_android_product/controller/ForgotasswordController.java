package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.ForgotPasswordRequest;
import com.oasys.mdm_android_product.request.ResetPasswordRequest;
import com.oasys.mdm_android_product.response.DeviceMasterResponse;
import com.oasys.mdm_android_product.response.ForgotPasswordResponse;
import com.oasys.mdm_android_product.services.ForgotPasswordService;

@RestController
public class ForgotasswordController {
	@Autowired
	private ForgotPasswordService service;

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
		ForgotPasswordResponse response = service.createPasswordResetToken(forgotPasswordRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
		DeviceMasterResponse response = service.resetPassword(resetPasswordRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
