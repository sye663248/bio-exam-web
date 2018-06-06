package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NGetSize {
	@JsonProperty("result")
	String result;
	@JsonProperty("error")
	String error;
	@JsonProperty("size")
	int size;
	
	
	
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
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
