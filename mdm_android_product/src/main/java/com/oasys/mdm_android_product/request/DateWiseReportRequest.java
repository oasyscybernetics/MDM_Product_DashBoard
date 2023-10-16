package com.oasys.mdm_android_product.request;

import lombok.Data;

@Data
public class DateWiseReportRequest {

	private String deviceId;
	private String startDate;
	private String endDate;

}
