package com.oasys.mdm_android_product.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {

	private String token;
	private String password;
	
}
