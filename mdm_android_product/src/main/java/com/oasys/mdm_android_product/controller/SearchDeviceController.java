package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.SearchDeviceRequest;
import com.oasys.mdm_android_product.response.DeviceMasterResponse;
import com.oasys.mdm_android_product.services.SearchDeviceService;

@RestController
@RequestMapping("/mdm")
public class SearchDeviceController {

	@Autowired
	private SearchDeviceService searchDeviceService;

	@PostMapping("/searchDeviceByDate")
	public ResponseEntity<?> searchDeviceByPreviousDate(@RequestBody SearchDeviceRequest searchDeviceRequest)
			throws Exception {
		DeviceMasterResponse response = null;
		response = searchDeviceService.searchDeviceByPreviousDate(searchDeviceRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
