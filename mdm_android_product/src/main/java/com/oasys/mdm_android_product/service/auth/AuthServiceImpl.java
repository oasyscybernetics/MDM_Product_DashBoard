package com.oasys.mdm_android_product.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.entity.Login;
import com.oasys.mdm_android_product.repository.LoginRepository;
import com.oasys.mdm_android_product.response.RegisterDTO;
import com.oasys.mdm_android_product.response.UserDTO;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDTO createUser(RegisterDTO registerDTO) {
		Login login = new Login();
		login.setUsername(registerDTO.getUsername());
		login.setPassword(new BCryptPasswordEncoder().encode(registerDTO.getPassword()));
		login.setEmail(registerDTO.getEmail());

		Login createdUser = loginRepository.save(login);

		UserDTO userDTO = new UserDTO();

		if (createdUser.getId() > 0) {
			userDTO.setId(createdUser.getId());
			userDTO.setUsername(createdUser.getUsername());
			userDTO.setMessage("successfully registered");
		} else {
			userDTO.setMessage("fail to register");
		}
		return userDTO;
	}

}
