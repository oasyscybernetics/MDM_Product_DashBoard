package com.oasys.mdm_android_product.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.repository.DeviceInfoHistoryRepository;
import com.oasys.mdm_android_product.repository.DeviceInfoRepository;
import com.oasys.mdm_android_product.request.SearchDeviceRequest;
import com.oasys.mdm_android_product.response.DeviceMasterResponse;
import com.oasys.mdm_android_product.response.OfflineDeviceResponse;

@Service
public class SearchDeviceService {

	@Autowired
	private DeviceInfoRepository deviceInfoRepository;

	@Autowired
	private DeviceInfoHistoryRepository deviceInfoHistoryRepository;
	
	public DeviceMasterResponse searchDeviceByPreviousDate(SearchDeviceRequest searchDeviceRequest) {
		DeviceMasterResponse response = null;
		List<OfflineDeviceResponse> deviceOfflineListResponses = new ArrayList<OfflineDeviceResponse>();
		try {
			if(searchDeviceRequest.getDate().equals(LocalDate.now())) {
			List<Object[]> resultList = deviceInfoRepository.searchDeviceByCurrentDate(searchDeviceRequest.getDate().toString());
			for (Object[] obj : resultList) {
				OfflineDeviceResponse deviceOfflineListResponse = new OfflineDeviceResponse();
				deviceOfflineListResponse.setDeviceId(obj[0].toString());
				deviceOfflineListResponse.setDeviceInternalStorage(obj[1].toString());
				deviceOfflineListResponse.setDeviceBatteryLevel(obj[2].toString());
				deviceOfflineListResponse.setDeviceExternalStorage(obj[3].toString());
				deviceOfflineListResponse.setDeviceLatitude(obj[4].toString());
				deviceOfflineListResponse.setDeviceLongitude(obj[5].toString());
				deviceOfflineListResponse.setCreatedDateNew(obj[6].toString());
				deviceOfflineListResponse.setDeviceStorageUsed((String)obj[7]);
				deviceOfflineListResponse.setDeviceUserAppCount((Integer)obj[8]);
				deviceOfflineListResponse.setDeviceSysAppCount((Integer)obj[9]);
				deviceOfflineListResponse.setDeviceTemprature(obj[10].toString());	

				deviceOfflineListResponses.add(deviceOfflineListResponse);
			}
			if (deviceOfflineListResponses != null) {
				response = new DeviceMasterResponse();
				response.setMsg(deviceOfflineListResponses);

			}
			}
			else {
				List<Object[]> resultList = deviceInfoHistoryRepository.searchDeviceByPreviousDate(searchDeviceRequest.getDate().toString());
				for (Object[] obj : resultList) {
					OfflineDeviceResponse deviceOfflineListResponse = new OfflineDeviceResponse();
					deviceOfflineListResponse.setDeviceId(obj[0].toString());
					deviceOfflineListResponse.setDeviceInternalStorage(obj[1].toString());
					deviceOfflineListResponse.setDeviceBatteryLevel(obj[2].toString()); 
					deviceOfflineListResponse.setDeviceExternalStorage(obj[3].toString());
					deviceOfflineListResponse.setDeviceLatitude(obj[4].toString());
					deviceOfflineListResponse.setDeviceLongitude(obj[5].toString());
					deviceOfflineListResponse.setCreatedDateNew(obj[6].toString()); 
					deviceOfflineListResponse.setDeviceStorageUsed((String)obj[7]);
					deviceOfflineListResponse.setDeviceUserAppCount((Integer)obj[8]);
					deviceOfflineListResponse.setDeviceSysAppCount((Integer)obj[9]);
					deviceOfflineListResponse.setDeviceTemprature(obj[10].toString());
					
					deviceOfflineListResponses.add(deviceOfflineListResponse);
				}
				if (deviceOfflineListResponses != null) {
					response = new DeviceMasterResponse();
					response.setMsg(deviceOfflineListResponses);
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
