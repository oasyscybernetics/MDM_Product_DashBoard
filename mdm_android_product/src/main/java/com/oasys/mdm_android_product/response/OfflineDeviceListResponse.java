package com.oasys.mdm_android_product.response;

import lombok.Data;

@Data
public class OfflineDeviceListResponse {	

	private String deviceId;
	private String deviceModel;
	private String deviceInternalStorage;
	private String deviceExternalStorage;
	private String deviceBatteryLevel;
	private String deviceManufacturer;
	private String deviceStorageUsed;
	private String deviceTemprature;
	private String deviceOSVersion;
	private String batteryPlugged;
	private String signalStrength;
	private String internetConnection;
	private String deviceLatitude;
	private String deviceLongitude;
	private String deviceSim1IMEI;
	private String deviceSim2IMEI;
	private String deviceSim1Number;
	private String deviceSim2Number;
	private Integer deviceSysAppCount;
	private Integer deviceUserAppCount;
	private String createdDateNew;
//	private Long minuteDiff;
	private String status;

}
