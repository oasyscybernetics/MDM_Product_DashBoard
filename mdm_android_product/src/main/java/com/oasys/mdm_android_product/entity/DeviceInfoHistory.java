package com.oasys.mdm_android_product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_device_info_history")
@Data
public class DeviceInfoHistory {


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

		@Column(name = "device_id", nullable = false)
		private String deviceId;

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

		@Column(name = "device_storage_used")
		private String deviceStorageUsed;

		@Column(name = "device_lat")
		private String deviceLatitude;

		@Column(name = "device_long")
		private String deviceLongitude;

		@Column(name = "created_dt", nullable = false)
		private java.sql.Timestamp createdDate;

	}
