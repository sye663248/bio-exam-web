package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceRect {

	@JsonProperty("left")
	Integer left;
	
	@JsonProperty("top")
	Integer top;
	
	@JsonProperty("right")
	Integer right;
	
	@JsonProperty("bottom")
	Integer bottom;

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getRight() {
		return right;
	}

	public void setRight(Integer right) {
		this.right = right;
	}

	public Integer getBottom() {
		return bottom;
	}

	public void setBottom(Integer bottom) {
		this.bottom = bottom;
	}
	
	
	
	
}
