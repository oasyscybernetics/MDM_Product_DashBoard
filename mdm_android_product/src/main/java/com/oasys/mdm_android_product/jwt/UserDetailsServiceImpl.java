package com.oasys.mdm_android_product.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.entity.Login;
import com.oasys.mdm_android_product.repository.LoginRepository;
import com.oasys.mdm_android_product.response.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/*
	 * @Autowired private UserRepository userRepository;
	 */

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Write Logic to get the user from the DB(jwt)
		// User user = userRepository.findFirstByEmail(email);
		Login login = loginRepository.findFirstByUsername(username);
		if (login == null) {
			throw new UsernameNotFoundException("Username not found", null);
		}
		CustomUserDetails userDetails = new CustomUserDetails(login.getUsername(), login.getPassword(),
				new ArrayList<>());
		userDetails.setLoginId(login.getId().toString());
		userDetails.setMessage(login.getUsername());

		return userDetails;
		// return new
		// org.springframework.security.core.userdetails.User(login.getUsername(),login.getPassword(),
		// new ArrayList<>());
	}

}
