package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Verify {

	@JsonProperty("reqid")
	String reqid;

	@JsonProperty("time_used")
	Integer timeUsed;
	
	@JsonProperty("confidence")
	Double confidence;

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	public Integer getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(Integer timeUsed) {
		this.timeUsed = timeUsed;
	}

	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}
	
	

	
}
