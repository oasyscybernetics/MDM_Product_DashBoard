package com.oasys.mdm_android_product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.entity.Login;
import com.oasys.mdm_android_product.repository.LoginRepository;
import com.oasys.mdm_android_product.response.DeviceMasterResponse;

@Service
public class UserService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public DeviceMasterResponse changePassword(String username, String currentPassword, String newPassword) {
		DeviceMasterResponse response = null;
		// Retrieve the user from the database
		Login user = loginRepository.findFirstByUsername(username);

		if (user == null) {
			response = new DeviceMasterResponse();
			response.setMsg("Cannot find the User");
			return response;
		}

		// Verify that the current password matches the stored password
		if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
			response = new DeviceMasterResponse();
			response.setMsg("Incorrect Password");
			return response;
		}

		String hashedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(hashedPassword);
		loginRepository.save(user);
		response = new DeviceMasterResponse();
		response.setMsg("Password changed successfully");
		return response;
	}

}