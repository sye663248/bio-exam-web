package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NList {
	@JsonProperty("result")
	String result;
	@JsonProperty("error")
	String error;
	@JsonProperty("groups")
	String[] groups;
	
	
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
	
	
	public String[] getGroups() {
		return groups;
	}
	public void setGroups(String[] groups) {
		this.groups = groups;
	}
	
	
	
}
