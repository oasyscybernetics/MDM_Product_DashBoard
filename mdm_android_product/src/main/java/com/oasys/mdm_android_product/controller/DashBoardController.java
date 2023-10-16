package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.DeviceListRequest;
import com.oasys.mdm_android_product.response.ClientResponse;
import com.oasys.mdm_android_product.services.DashBoardService;

@RestController
@RequestMapping("/mdm/registeredDevice")
public class DashBoardController {

	@Autowired
	private DashBoardService deviceRegisteredService;

	@PostMapping("/deviceDetails")
	public ResponseEntity<?> deviceList(@RequestBody DeviceListRequest deviceListReq) throws Exception {
		ClientResponse response = null;
		response = deviceRegisteredService.deviceList(deviceListReq);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
