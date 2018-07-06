package com.fubonlife.bio.exam.util;

public class WebRestResponse {
	
	private Boolean success;
	private int status;
	private String message;
	private Object data;
	private long total;
	
	// 成功1
	public static WebRestResponse success(Object data){
		WebRestResponse response = new WebRestResponse();
		response.success = Boolean.TRUE;
		response.status = 0;
		response.data = data;
		return response;
	}
	
	// 成功2
	public static WebRestResponse success(Object data, long total){
		WebRestResponse response = new WebRestResponse();
		response.success = Boolean.TRUE;
		response.status = 0;
		response.data = data;
		response.total = total;
		return response;
	}
	
	// 失敗1
	public static WebRestResponse failure(String message){
		WebRestResponse response = new WebRestResponse();
		response.success = Boolean.FALSE;
		response.status = -1;
		response.message = message;
		return response;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	

}
