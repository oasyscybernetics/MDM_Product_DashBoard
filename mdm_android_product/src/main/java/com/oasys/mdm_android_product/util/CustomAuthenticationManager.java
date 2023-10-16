package com.oasys.mdm_android_product.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.oasys.mdm_android_product.entity.Login;
import com.oasys.mdm_android_product.repository.LoginRepository;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Login user = loginRepository.findFirstByUsername(authentication.getName());
		if (user.getUsername() != null || user.getPassword() != null) {
			if (passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
				List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//                for (Role role : user.get().getRoleSet()) {
//                    grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
//                }
				return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
						authentication.getCredentials(), grantedAuthorityList);
			} else {
				throw new BadCredentialsException("Wrong Password");
			}
		} else {
			throw new BadCredentialsException("Wrong UserName");
		}
	}
}