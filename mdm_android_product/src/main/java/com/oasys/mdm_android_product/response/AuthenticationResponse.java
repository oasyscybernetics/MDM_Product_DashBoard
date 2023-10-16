package com.oasys.mdm_android_product.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //created the contructor for authentication response
public class AuthenticationResponse {
	
	/*
	 * //created the contructor for authentication response public
	 * AuthenticationResponse(String jwt) {
	 * 
	 * }
	 */

	private String jwtToken;
	private String district_id;
	private String msg;

}
