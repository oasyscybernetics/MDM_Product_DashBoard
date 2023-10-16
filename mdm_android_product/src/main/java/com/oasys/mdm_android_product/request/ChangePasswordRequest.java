package com.oasys.mdm_android_product.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {

	private String userName;
	private String oldPassword;
	private String newPassword;
//	private String confirmPassword;
	
}
