package com.oasys.mdm_android_product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.repository.DeviceInfoRepository;
import com.oasys.mdm_android_product.request.DeviceInfoRequest;
import com.oasys.mdm_android_product.response.MDMResponse;

import jakarta.transaction.Transactional;

@Service
public class ReportService {

	@Autowired
	private DeviceInfoRepository deviceInfoRepository;

	// used to update device_info table
	@Transactional
	public MDMResponse deviceHeartBeat(DeviceInfoRequest deviceInfoRequest) {
		MDMResponse response = new MDMResponse();
		try {
			deviceInfoRepository.deviceHeartBeat(deviceInfoRequest.getDeviceId(), deviceInfoRequest.getDeviceLat(),
					deviceInfoRequest.getDeviceLong());

			response.setMsg("Successfully Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
