package com.oasys.mdm_android_product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import com.oasys.mdm_android_product.entity.DeviceMaster;
import com.oasys.mdm_android_product.repository.DeviceMasterRepository;
import com.oasys.mdm_android_product.request.RemoteControlRequest;

@Service
public class RemoteControlService {

	@Autowired
	private DeviceMasterRepository deviceMasterRepository;

	public String processRemoteControl(RemoteControlRequest request) {
		try (ZContext context = new ZContext()) {
			ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
			publisher.bind("tcp://*:5555");
			System.out.println("Publisher bound to port 5555 - Device-ID: " + request.getDevice_id());

			System.out.println("Sending a multipart message RemoteControl");
			int i = 0;
			while (i < 2) {

				publisher.sendMore(request.getDevice_id());
				publisher.send(("RemoteControl," + request.getRemote_url() + "," + request.getRemote_pwd()),
						ZMQ.SNDMORE);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				i++;
			}

			DeviceMaster deviceMaster = deviceMasterRepository.findByDeviceId(request.getDevice_id());
			if (deviceMaster != null) {
				return "RemoteControl: " + deviceMaster.toString();
			}

			return "RemoteControl not found";
		}
	}
}