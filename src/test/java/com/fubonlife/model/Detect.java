package com.fubonlife.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Detect {
	
	@JsonProperty("reqid")
	String reqid;

	@JsonProperty("time_used")
	Integer timeUsed;
	
	@JsonProperty("faces")
	List faces;

}
