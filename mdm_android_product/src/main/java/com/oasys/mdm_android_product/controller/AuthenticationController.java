package com.oasys.mdm_android_product.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.jwt.UserDetailsServiceImpl;
import com.oasys.mdm_android_product.response.AuthenticationDTO;
import com.oasys.mdm_android_product.response.AuthenticationResponse;
import com.oasys.mdm_android_product.response.CustomUserDetails;
import com.oasys.mdm_android_product.util.JWTUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController implements AuthenticationManager {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// used to fetch Login details
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO,
			HttpServletResponse response)
			throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
		// Validate username and password are not null or empty
		String username = authenticationDTO.getUsername();
		String password = authenticationDTO.getPassword();

		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			return ResponseEntity.badRequest().body("massage : Username and password cannot be null or empty.");
		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
					authenticationDTO.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password!");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getUsername());

		CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;

		final String jwt = jwtUtil.generateToken(userDetails.getUsername());

		AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwt, customUserDetails.getLoginId(),
				customUserDetails.getMessage());

		return ResponseEntity.ok(authenticationResponse);

	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
