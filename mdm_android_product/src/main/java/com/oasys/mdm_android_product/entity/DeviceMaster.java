package com.oasys.mdm_android_product.entity;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "device_master")
@Data
public class DeviceMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "device_id", nullable = false)
	private String deviceId;

	@Column(name = "device_name", nullable = false)
	private String deviceName;

	@Column(name = "remotesession_id")
	private String remoteSessionId;

	@Column(name = "remote_pwd")
	private String remotePwd;

	@Column(name = "launcher_pwd", columnDefinition = "int default 890654")
	private int launcherPwd;

	@Column(name = "ssid")
	private String ssid;

	@Column(name = "wifipwd")
	private String wifiPwd;

//	@Column(name = "device_status")
//	private Integer deviceStatus;

	@CreationTimestamp
	@Column(name = "device_createdat", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private java.sql.Timestamp deviceCreatedAt;

	@Column(name = "device_shopcode")
	private String deviceShopCode;

	@Column(name = "settings_pwd")
	private String settingsPwd;

	@Column(name = "branch", nullable = false)
	private String branch;

	@Column(name = "mobile_number")
	private String mobileNumber; 

}
