package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fubonlife.model.Version.Threshold;

public class NInit {
	@JsonProperty("result")
	String result;
	@JsonProperty("error")
	String error;
	
	
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
		
	
}
