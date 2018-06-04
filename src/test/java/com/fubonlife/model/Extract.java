package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Extract {
	
	@JsonProperty("reqid")
	String reqid;

	@JsonProperty("time_used")
	Integer timeUsed;
	
	

}
