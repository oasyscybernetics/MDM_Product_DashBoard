package com.oasys.mdm_android_product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oasys.mdm_android_product.entity.DeviceInfoHistory;

public interface DeviceInfoHistoryRepository extends JpaRepository<DeviceInfoHistory, Integer> {

	@Query(value = "SELECT di.device_id,di.device_internal_storage,di.device_battery_level,di.device_external_storage,di.device_lat,di.device_long,DATE_FORMAT(di.created_dt,'%d-%m-%Y %T') AS created_dt,di.device_storage_used,di.device_user_app_count,di.device_sys_app_count,di.device_temprature FROM tbl_device_info_history di WHERE DATE(di.created_dt) = :date AND di.id IN (SELECT MAX(dh.id) FROM tbl_device_info_history dh WHERE DATE(dh.created_dt) = :date GROUP BY dh.device_id)", nativeQuery = true)
	List<Object[]> searchDeviceByPreviousDate(String date);

	DeviceInfoHistory findByDeviceId(String deviceId);

//	@Query(value = "SELECT DISTINCT di.device_id, DATE_FORMAT(di.created_dt, '%Y-%m-%d') AS created_date FROM tbl_device_info_history di WHERE DATE(di.created_dt) BETWEEN :startDate AND :endDate AND di.device_id =:deviceId ", nativeQuery = true)
	@Query(value = "SELECT DISTINCT DATE_FORMAT(di.created_dt, '%Y-%m-%d') AS created_date FROM tbl_device_info_history di WHERE DATE(di.created_dt) BETWEEN :startDate AND :endDate AND di.device_id =:deviceId ", nativeQuery = true)
	List<Object> dateWiseReport(java.util.Date startDate, java.util.Date endDate, String deviceId);

}
