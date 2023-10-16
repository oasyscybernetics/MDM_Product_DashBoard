package com.oasys.mdm_android_product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "device_info")
@Data
public class DeviceInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "device_id", nullable = false)
	private String deviceId;

	@Column(name = "device_model")
	private String deviceModel;

	@Column(name = "device_internal_storage")
	private String deviceInternalStorage;

	@Column(name = "device_external_storage")
	private String deviceExternalStorage;

	@Column(name = "device_user_app_count")
	private Integer deviceUserAppCount;

	@Column(name = "device_sys_app_count")
	private Integer deviceSysAppCount;

	@Column(name = "device_battery_level")
	private String deviceBatteryLevel;

	@Column(name = "device_temprature")
	private String deviceTemperature;

	@Column(name = "device_ram")
	private String deviceRAM;

	@Column(name = "device_storage_used")
	private String deviceStorageUsed;

	@Column(name = "device_manufacturer")
	private String deviceManufacturer;

	@Column(name = "device_os_version", columnDefinition = "varchar(200)")
	private String deviceOSVersion;

	@Column(name = "device_ip")
	private String deviceIP;

	@Column(name = "device_mac")
	private String deviceMAC;

	@Column(name = "device_lat")
	private String deviceLatitude;

	@Column(name = "device_long")
	private String deviceLongitude;

	@Column(name = "created_dt", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private java.sql.Timestamp createdDate;

	@Column(name = "device_Sim1_IMEI")
	private String deviceSim1IMEI;

	@Column(name = "device_sno")
	private String deviceSerialNumber;

	@Column(name = "device_sim1_no")
	private String deviceSim1Number;

	@Column(name = "battery_technology")
	private String batteryTechnology;

	@Column(name = "battery_health")
	private String batteryHealth;

	@Column(name = "battery_plugged")
	private String batteryPlugged;

	@Column(name = "internet_connection")
	private String internetConnection;

	@Column(name = "signal_strength")
	private String signalStrength;

	@Column(name = "device_Sim2_IMEI")
	private String deviceSim2IMEI;

	@Column(name = "device_sim2_no")
	private String deviceSim2Number;

	@Column(name = "battery_voltage")
	private Integer batteryVoltage;

	@Column(name = "device_serial_no")
	private String deviceSerialNo;

}
