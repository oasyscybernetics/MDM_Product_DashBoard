package com.oasys.mdm_android_product.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.repository.DeviceInfoRepository;
import com.oasys.mdm_android_product.request.DeviceListRequest;
import com.oasys.mdm_android_product.response.ClientResponse;
import com.oasys.mdm_android_product.response.OfflineDeviceListResponse;

@Service
public class DashBoardService {

	@Autowired
	private DeviceInfoRepository deviceInfoRepository;

	public ClientResponse deviceList(DeviceListRequest deviceListReq) {
		ClientResponse res = new ClientResponse();
		List<OfflineDeviceListResponse> deviceOfflineListResponses = new ArrayList<OfflineDeviceListResponse>();
		try {
			List<Object[]> resultList = deviceInfoRepository.deviceList();
			for (Object[] obj : resultList) {
				OfflineDeviceListResponse deviceOfflineListResponse = new OfflineDeviceListResponse();
				deviceOfflineListResponse.setDeviceId((String) obj[0]);
				deviceOfflineListResponse.setDeviceModel((String) obj[1]);
				deviceOfflineListResponse.setDeviceInternalStorage((String) obj[2]);
				deviceOfflineListResponse.setDeviceBatteryLevel((String) obj[3]);
				deviceOfflineListResponse.setDeviceManufacturer((String) obj[4]);
				deviceOfflineListResponse.setDeviceOSVersion((String) obj[5]);
				deviceOfflineListResponse.setBatteryPlugged((String) obj[6]);
				deviceOfflineListResponse.setSignalStrength((String) obj[7]);
				deviceOfflineListResponse.setInternetConnection((String) obj[8]);
				deviceOfflineListResponse.setDeviceLatitude((String) obj[9]);
				deviceOfflineListResponse.setDeviceLongitude((String) obj[10]);
				deviceOfflineListResponse.setDeviceSim1IMEI((String) obj[11]);
				deviceOfflineListResponse.setDeviceSim2IMEI((String) obj[12]);
				deviceOfflineListResponse.setDeviceSim1Number((String) obj[13]);
				deviceOfflineListResponse.setDeviceSim2Number((String) obj[14]);
				deviceOfflineListResponse.setCreatedDateNew((String) obj[15]);
				deviceOfflineListResponse.setStatus((String) obj[16]);

				deviceOfflineListResponses.add(deviceOfflineListResponse);

			}

			List<OfflineDeviceListResponse> overAllList = deviceOfflineListResponses.stream()
					.collect(Collectors.toList());

			List<OfflineDeviceListResponse> onlineList = deviceOfflineListResponses.stream()
					.filter(deviceList -> deviceList.getStatus().equalsIgnoreCase("online"))
					.collect(Collectors.toList());

			List<OfflineDeviceListResponse> offlineList = deviceOfflineListResponses.stream()
					.filter(deviceList -> deviceList.getStatus().equalsIgnoreCase("offline"))
					.collect(Collectors.toList());

			if (deviceListReq.getStatus().equalsIgnoreCase("online")) {
				res.setMsg(onlineList);
				res.setCount(onlineList.size());
			} else if (deviceListReq.getStatus().equalsIgnoreCase("offline")) {
				res.setMsg(offlineList);
				res.setCount(offlineList.size());
			} else if (deviceListReq.getStatus().equalsIgnoreCase("all")) {
				res.setMsg(overAllList);
				res.setCount(overAllList.size());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

}
