package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.DeviceInfoRequest;
import com.oasys.mdm_android_product.response.MDMResponse;
import com.oasys.mdm_android_product.services.ReportService;

@RestController
@RequestMapping("/mdm/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	// used to update device_info table
	@PostMapping(value = "/deviceHeartBeat")
	public ResponseEntity<?> deviceHeartBeat(@RequestBody DeviceInfoRequest deviceInfoRequest) {
		MDMResponse response = reportService.deviceHeartBeat(deviceInfoRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}