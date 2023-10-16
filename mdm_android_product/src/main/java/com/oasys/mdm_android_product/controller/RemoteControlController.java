package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.RemoteControlRequest;
import com.oasys.mdm_android_product.services.RemoteControlService;

@RestController
public class RemoteControlController {

	@Autowired
	private RemoteControlService remoteControlService;

	@PostMapping("/RemoteControlZMQ")
	public String remoteControlZMQ(@RequestBody RemoteControlRequest request) {
		return remoteControlService.processRemoteControl(request);
	}
}