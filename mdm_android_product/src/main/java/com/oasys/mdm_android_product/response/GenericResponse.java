package com.oasys.mdm_android_product.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class GenericResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userDisplayMesg;
	private Object Data;

	public String getUserDisplayMesg() {
		return userDisplayMesg;
	}

	public void setUserDisplayMesg(String userDisplayMesg) {
		this.userDisplayMesg = userDisplayMesg;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static GenericResponse getSuccessfulResponse(Object obj) throws Exception {
		GenericResponse GenericResponse = new GenericResponse();
		GenericResponse.setData(obj);
		GenericResponse.setUserDisplayMesg("Success");
		return GenericResponse;
	}

	public static GenericResponse getFailureResponse(String errorCode, String errorMsg) throws Exception {
		GenericResponse GenericResponse = new GenericResponse();
		GenericResponse.setData(null);
		GenericResponse.setUserDisplayMesg(errorMsg);
		return GenericResponse;
	}

	public static GenericResponse setNoRecordFoundResponse(String status, String errorCode, String errorMsg)
			throws Exception {
		GenericResponse GenericResponse = new GenericResponse();
		GenericResponse.setData(null);
		GenericResponse.setUserDisplayMesg(errorMsg);
		return GenericResponse;
	}

}
