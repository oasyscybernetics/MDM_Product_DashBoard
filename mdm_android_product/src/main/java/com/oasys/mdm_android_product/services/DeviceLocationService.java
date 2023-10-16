package com.oasys.mdm_android_product.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.repository.DeviceInfoRepository;
import com.oasys.mdm_android_product.request.DeviceLocationRequest;
import com.oasys.mdm_android_product.response.DeviceLocationResponse;
import com.oasys.mdm_android_product.response.GenericResponse;

@Service
public class DeviceLocationService {

	@Autowired
	private DeviceInfoRepository deviceInfoRepository;

	// used to fetch all device location
	public GenericResponse deviceLocation(DeviceLocationRequest deviceLocationRequest) {
		GenericResponse response = null;
		List<Object[]> deviceInfo = deviceInfoRepository.deviceLocation(deviceLocationRequest.getDeviceId());
		List<DeviceLocationResponse> deviceLocationResponses = new ArrayList<DeviceLocationResponse>();
		try {
			for (Object[] obj : deviceInfo) {
				DeviceLocationResponse deviceLocationResponse = new DeviceLocationResponse();
				deviceLocationResponse.setDeviceId(((String) obj[0]));
				deviceLocationResponse.setDeviceLatitude(((String) obj[1]));
				deviceLocationResponse.setDeviceLongitude(((String) obj[2]));
				deviceLocationResponse.setCreatedDate(((String) obj[3]));

				deviceLocationResponses.add(deviceLocationResponse);
			}
			if (deviceLocationResponses != null) {
				response = new GenericResponse();
				response.setData(deviceLocationResponses);
				response.setUserDisplayMesg("Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}