package com.oasys.mdm_android_product.request;

import lombok.Data;

@Data
public class RemoteControlRequest {

	private String device_id;
	private String remote_url;
	private String remote_pwd;

}
