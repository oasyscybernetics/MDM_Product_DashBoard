package com.oasys.mdm_android_product.response;

import lombok.Data;

@Data
public class DeviceInfoZMQResponse {

	private String deviceId;
	private String deviceModel;
	private String deviceInternalStorage;
	private String deviceExternalStorage;
	private String deviceBatteryLevel;
	private String deviceManufacturer;
	private String deviceOSVersion;
	private String deviceLatitude;
	private String deviceLongitude;
	private String deviceSim1IMEI;
	private String deviceSim1Number;
	private String deviceSim2IMEI;
	private String deviceSim2Number;
	private String internetConnection;
	private String signalStrength;
	private String batteryTechnology;
	private String batteryPlugged;
	private String createdDate;
	private Long minuteDiff;

}
