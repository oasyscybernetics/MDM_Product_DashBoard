package com.oasys.mdm_android_product.request;

import lombok.Data;

@Data
public class EmailRequest {
	
	private String to;
	private String subject;
	private String body;
	private String email;
	

}
