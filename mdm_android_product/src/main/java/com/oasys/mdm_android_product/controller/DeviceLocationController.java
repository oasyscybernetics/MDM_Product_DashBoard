package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.DeviceLocationRequest;
import com.oasys.mdm_android_product.response.GenericResponse;
import com.oasys.mdm_android_product.services.DeviceLocationService;

@RestController
@RequestMapping("/mdm/device_location")
public class DeviceLocationController {

	@Autowired
	DeviceLocationService deviceLocationService;

	// used to fetch all device location
	@PostMapping(value = "/deviceLocation")
	public ResponseEntity<?> deviceLocation(@RequestBody DeviceLocationRequest deviceLocationRequest) throws Exception {
		GenericResponse resp = null;
		resp = deviceLocationService.deviceLocation(deviceLocationRequest);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
