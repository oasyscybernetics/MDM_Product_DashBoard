package com.oasys.mdm_android_product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.oasys.mdm_android_product.entity.DeviceInfo;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Integer> {

	@Query(value = "SELECT di.device_id,di.device_model,di.device_internal_storage,di.device_battery_level,di.device_manufacturer,di.device_os_version,di.battery_plugged,di.signal_strength,di.internet_connection,di.device_lat,di.device_long,di.device_Sim1_IMEI,di.device_Sim2_IMEI,di.device_sim1_no,di.device_sim2_no,DATE_FORMAT(di.created_dt,'%d-%m-%Y %T') AS created_dt_new,(SELECT IF ((TIME_TO_SEC((TIMEDIFF(NOW(),di.created_dt)))) > 660, 'Offline', 'Online')) AS 'Status' FROM device_master dm LEFT JOIN device_info di ON dm.device_id =di.device_id WHERE TIME_TO_SEC((TIMEDIFF(NOW(),di.created_dt))) > 660 ORDER BY di.created_dt DESC", nativeQuery = true)
	List<Object[]> getOfflineDeviceList();

	@Query(value = "SELECT di.device_id,di.device_model,di.device_internal_storage,di.device_battery_level,di.device_manufacturer,di.device_os_version,di.device_lat,di.device_long,di.battery_plugged,di.signal_strength,di.internet_connection,di.device_Sim1_IMEI,di.device_Sim2_IMEI,di.device_sim1_no,di.device_sim2_no,DATE_FORMAT(di.created_dt,'%d-%m-%Y %T') AS created_dt_new,(SELECT IF ((TIME_TO_SEC((TIMEDIFF(NOW(),di.created_dt)))) < 660, 'Online', 'Offline')) AS 'Status' FROM device_master dm LEFT JOIN device_info di ON dm.device_id =di.device_id WHERE TIME_TO_SEC((TIMEDIFF(NOW(),di.created_dt))) < 660 ORDER BY di.created_dt DESC ", nativeQuery = true)
	List<Object[]> getOnlineDeviceList();

//	@Query(value = "SELECT di.device_id,di.device_model,di.device_internal_storage,di.device_battery_level,di.device_manufacturer,di.device_os_version,di.battery_plugged,di.signal_strength,di.internet_connection,di.device_lat,di.device_long,di.device_Sim1_IMEI,di.device_Sim2_IMEI,di.device_sim1_no,di.device_sim2_no,DATE_FORMAT(di.created_dt,'%d-%m-%Y %T') AS created_dt_new,(SELECT IF ((TIME_TO_SEC((TIMEDIFF(NOW(),di.created_dt)))) < 660, 'Online', 'Offline')) AS 'Status' FROM device_info di LEFT JOIN device_master dm ON dm.device_id =di.device_id WHERE di.device_model LIKE '%TEG%' AND TIME_TO_SEC((TIMEDIFF(NOW(),di.created_dt))) < 660 OR TIME_TO_SEC((TIMEDIFF(NOW(),di.created_dt))) > 660 limit 15 ", nativeQuery = true)
//	List<Object[]> deviceList();

	@Query(value = "SELECT di.device_id,di.device_model,di.device_internal_storage,di.device_battery_level,di.device_manufacturer,di.device_os_version,di.battery_plugged,di.signal_strength,di.internet_connection,di.device_lat,di.device_long,di.device_Sim1_IMEI,di.device_Sim2_IMEI,di.device_sim1_no,di.device_sim2_no,DATE_FORMAT(di.created_dt,'%d-%m-%Y %T') AS created_dt_new,(SELECT IF ((TIME_TO_SEC((TIMEDIFF(NOW(),di.created_dt)))) > 660, 'Offline', 'Online')) AS 'Status' FROM device_info di LEFT JOIN device_master dm ON dm.device_id =di.device_id ", nativeQuery = true)
	List<Object[]> deviceList();

	@Query(value = "SELECT di.device_id,di.device_internal_storage,di.device_battery_level,di.device_external_storage,di.device_lat,di.device_long,DATE_FORMAT(di.created_dt,'%d-%m-%Y %T') AS created_dt,di.device_storage_used,di.device_user_app_count,di.device_sys_app_count,di.device_temprature FROM tbl_device_info_history di WHERE DATE(di.created_dt) = :date ", nativeQuery = true)
	List<Object[]> searchDeviceByCurrentDate(String date);
	
	@Query(value = "SELECT di.device_id,di.device_model,di.device_internal_storage,di.device_external_storage,di.device_battery_level,di.device_manufacturer,di.device_os_version,di.device_lat,di.device_long,di.device_Sim1_IMEI,di.device_Sim2_IMEI,di.device_sim1_no,di.device_sim2_no,di.signal_strength,di.internet_connection, di.battery_plugged,di.battery_technology,DATE_FORMAT(di.created_dt,'%Y-%m-%d %T') AS created_dt, TIMESTAMPDIFF(MINUTE,di.created_dt,CURRENT_TIMESTAMP)AS minute_diff FROM device_master dm LEFT JOIN device_info di ON di.device_id=dm.device_id WHERE di.device_id= :deviceId", nativeQuery = true)
	List<Object[]> findByZMQDeviceId(String deviceId);

	@Query(value = "SELECT di.device_id,di.device_lat,di.device_long,DATE_FORMAT(di.created_dt,'%Y-%m-%d %T') as created_dt, TIMESTAMPDIFF(MINUTE,di.created_dt,CURRENT_TIMESTAMP)as minute_diff  FROM device_master dm LEFT JOIN device_info di ON di.device_id=dm.device_id  WHERE di.device_id= :deviceId", nativeQuery = true)
	List<Object[]> deviceLocation(String deviceId);

	@Modifying
	@Query(value = "UPDATE device_info SET device_lat = :deviceLat, device_long = :deviceLong WHERE device_id = :deviceId", nativeQuery = true)
	void deviceHeartBeat(String deviceId, String deviceLat, String deviceLong);

//	@Query(value = "SELECT di.device_id,di.device_internal_storage,di.device_battery_level,di.device_external_storage,di.device_lat,di.device_long,DATE_FORMAT(di.created_dt,'%d-%m-%Y %T') AS created_dt,di.device_storage_used,di.device_user_app_count,di.device_sys_app_count,di.device_temprature FROM tbl_device_info_history di WHERE di.created_dt = :startDate ", nativeQuery = true)
////	List<Object[]> searchDeviceByCurrentDate(String date);
//	List<Object[]> searchDeviceByCurrentDate(Date startDate);

}
