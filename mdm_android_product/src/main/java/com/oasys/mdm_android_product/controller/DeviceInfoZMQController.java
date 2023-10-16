package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.DeviceInfoZMQRequest;
import com.oasys.mdm_android_product.response.MDMResponse;
import com.oasys.mdm_android_product.services.DeviceInfoZMQService;

@RestController
@RequestMapping("/mdm")
public class DeviceInfoZMQController {

	@Autowired
	DeviceInfoZMQService deviceInfoZMQService;

	@PostMapping("/DeviceInfoZMQ")
	public ResponseEntity<?> deviceInfoZMQ(@RequestBody DeviceInfoZMQRequest request) throws InterruptedException {
		MDMResponse resp = null;
		resp = deviceInfoZMQService.publishDeviceInfo(request);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
