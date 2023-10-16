package com.oasys.mdm_android_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oasys.mdm_android_product.entity.DeviceMaster;

public interface DeviceMasterRepository extends JpaRepository<DeviceMaster, Integer> {

	DeviceMaster findByDeviceId(String device_id);

}
