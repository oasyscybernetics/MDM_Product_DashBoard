package com.oasys.mdm_android_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasys.mdm_android_product.request.DateWiseReportRequest;
import com.oasys.mdm_android_product.response.MDMResponse;
import com.oasys.mdm_android_product.services.DateWiseReportService;

@RestController
@RequestMapping("/mdm/dateWiseReport")
public class DateWiseReportController {

	@Autowired
	DateWiseReportService dateWiseReport;

	@PostMapping("/dateWiseReport")
	public ResponseEntity<?> dateWiseReport(@RequestBody DateWiseReportRequest dateWiseReportRequest) throws Exception {
		MDMResponse response = null;
		response = dateWiseReport.dateWiseReport(dateWiseReportRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
