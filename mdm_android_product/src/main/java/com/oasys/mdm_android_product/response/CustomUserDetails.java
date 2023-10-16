package com.oasys.mdm_android_product.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class CustomUserDetails extends User {

	private static final long serialVersionUID = 26399796230321368L;
	private String loginId;
	private String message;

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
}
