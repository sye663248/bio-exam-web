package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NBatchAdd {
	@JsonProperty("result")
	String result;
	@JsonProperty("error")
	String error;
	@JsonProperty("fail_index")
	Integer[] fail_index;
	@JsonProperty("fail_reason")
	Integer[] fail_reason;
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	public Integer[] getFail_index() {
		return fail_index;
	}
	public void setFail_index(Integer[] fail_index) {
		this.fail_index = fail_index;
	}
	public Integer[] getFail_reason() {
		return fail_reason;
	}
	public void setFail_reason(Integer[] fail_reason) {
		this.fail_reason = fail_reason;
	}
	
	
	
}
