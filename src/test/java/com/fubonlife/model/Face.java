package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Face {

	@JsonProperty("Confidence")
	Double confidence;
	
	@JsonProperty("FaceRect")
	FaceRect faceRect;


}
