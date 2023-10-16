package com.oasys.mdm_android_product.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import com.oasys.mdm_android_product.repository.DeviceInfoRepository;
import com.oasys.mdm_android_product.request.DeviceInfoZMQRequest;
import com.oasys.mdm_android_product.response.DeviceInfoZMQResponse;
import com.oasys.mdm_android_product.response.MDMResponse;

@Service
public class DeviceInfoZMQService {

	@Autowired
	DeviceInfoRepository deviceInfoRepository;

	public MDMResponse publishDeviceInfo(DeviceInfoZMQRequest request) {
		MDMResponse response = null;
		try (ZContext context = new ZContext()) {
			ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
			publisher.bind("tcp://*:5555");
			System.out.println("Publisher bound to port 5555 - Device-ID: " + request.getDeviceId());

			System.out.println("Sending a multipart message DeviceInfo");
			int i = 0;
			while (i < 2) {
				publisher.sendMore(request.getDeviceId());
				publisher.send("DeviceInfo!", 0);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				i++;
			}

			try {
				List<Object[]> result = deviceInfoRepository.findByZMQDeviceId(request.getDeviceId());
				List<DeviceInfoZMQResponse> responses = new ArrayList<DeviceInfoZMQResponse>();
				for (Object[] obj : result) {
					DeviceInfoZMQResponse deviceInfoZMQResponse = new DeviceInfoZMQResponse();
					deviceInfoZMQResponse.setDeviceId((obj[0]).toString());
					deviceInfoZMQResponse.setDeviceModel((obj[1]).toString());
					deviceInfoZMQResponse.setDeviceInternalStorage((obj[2]).toString());
					deviceInfoZMQResponse.setDeviceExternalStorage((obj[3]).toString());
					deviceInfoZMQResponse.setDeviceBatteryLevel((obj[4]).toString());
					deviceInfoZMQResponse.setDeviceManufacturer((obj[5]).toString());
					deviceInfoZMQResponse.setDeviceOSVersion((obj[6]).toString());
					deviceInfoZMQResponse.setDeviceLatitude((obj[7]).toString());
					deviceInfoZMQResponse.setDeviceLongitude((obj[8]).toString());
					deviceInfoZMQResponse.setDeviceSim1IMEI((obj[9]).toString());
					deviceInfoZMQResponse.setDeviceSim2IMEI((obj[10]).toString());
					deviceInfoZMQResponse.setDeviceSim1Number((obj[11]).toString());
					deviceInfoZMQResponse.setDeviceSim2Number((obj[12]).toString());
					deviceInfoZMQResponse.setInternetConnection((obj[13]).toString());
					deviceInfoZMQResponse.setSignalStrength((obj[14]).toString());
					deviceInfoZMQResponse.setBatteryTechnology((obj[15]).toString());
					deviceInfoZMQResponse.setBatteryPlugged((obj[16]).toString());
					deviceInfoZMQResponse.setCreatedDate((obj[17]).toString());
					deviceInfoZMQResponse.setMinuteDiff(((Long) obj[18]).longValue());

					responses.add(deviceInfoZMQResponse);
				}
				if (responses != null) {
					response = new MDMResponse();
					response.setMsg(responses);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return response;
		}
	}
}
