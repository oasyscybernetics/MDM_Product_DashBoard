package com.oasys.mdm_android_product.response;

import lombok.Data;

@Data
public class DeviceResponse {

	private String deviceId;
	private String deviceLat;
	private String deviceLong;
	private String deviceSim1IMEI;
	private String deviceSim2IMEI;
	private String deviceSim1Number;
	private String deviceSim2Number;
	private String createdDtNew;
	private String createdTime;
	private String status;

}
