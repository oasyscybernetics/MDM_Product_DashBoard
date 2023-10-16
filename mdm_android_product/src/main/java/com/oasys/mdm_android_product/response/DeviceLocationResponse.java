package com.oasys.mdm_android_product.response;

import lombok.Data;

@Data
public class DeviceLocationResponse {

	private String deviceId;
	private String deviceLatitude;
	private String deviceLongitude;
	private String createdDate;

}
