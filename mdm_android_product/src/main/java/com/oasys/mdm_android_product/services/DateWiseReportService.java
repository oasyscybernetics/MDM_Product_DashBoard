package com.oasys.mdm_android_product.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.mdm_android_product.repository.DeviceInfoHistoryRepository;
import com.oasys.mdm_android_product.request.DateWiseReportRequest;
import com.oasys.mdm_android_product.response.DateWiseReportResponse;
import com.oasys.mdm_android_product.response.MDMResponse;

@Service
public class DateWiseReportService {

	@Autowired
	DeviceInfoHistoryRepository deviceInfoHistoryRepository;

	public MDMResponse dateWiseReport(DateWiseReportRequest dateWiseReportRequest) throws Exception {
		List<DateWiseReportResponse> dateWiseReportResponses = new ArrayList<DateWiseReportResponse>();
		MDMResponse response = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = dateFormat.parse(dateWiseReportRequest.getStartDate());
			Date endDate = dateFormat.parse(dateWiseReportRequest.getEndDate());

			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);

			List<Object> deviceAnalyticsList = deviceInfoHistoryRepository.dateWiseReport(startDate, endDate,
					dateWiseReportRequest.getDeviceId());

//			for (Object obj : deviceAnalyticsList) {
				for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE,1), date = start.getTime()) {
//			for (Date date = start.getTime(); start.after(end); start.add(Calendar.DATE,1), date = start.getTime()) {
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					String date1 = format1.format(date); 

//					if (dates.equals(date1.toString())) {
					if (deviceAnalyticsList.contains(date1)) {
						DateWiseReportResponse dateWiseReportResponse = new DateWiseReportResponse();
//						dateWiseReportResponse.setDeviceId(deviceID);
						dateWiseReportResponse.setDeviceId(dateWiseReportRequest.getDeviceId());
						dateWiseReportResponse.setDate(date1.toString());
						dateWiseReportResponse.setStatus("online");
						dateWiseReportResponses.add(dateWiseReportResponse);
					}
					else {
						DateWiseReportResponse dateWiseReportResponse = new DateWiseReportResponse();
//						dateWiseReportResponse.setDeviceId(deviceID);
						dateWiseReportResponse.setDeviceId(dateWiseReportRequest.getDeviceId());
						dateWiseReportResponse.setDate(date1.toString());
						dateWiseReportResponse.setStatus("offline");
						dateWiseReportResponses.add(dateWiseReportResponse);
					}
					
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dateWiseReportResponses != null) {
			response = new MDMResponse();
			response.setMsg(dateWiseReportResponses);
		}
		return response;
	}
	
}