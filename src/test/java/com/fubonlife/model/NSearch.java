package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NSearch {
	@JsonProperty("result")
	String result;
	@JsonProperty("error")
	String error;
	@JsonProperty("ids")
	Integer[] ids;
	@JsonProperty("scores")
	Double[] scores;
	@JsonProperty("tags")
	String[] tags;
	
	
	
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
	
	
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public Double[] getScores() {
		return scores;
	}
	public void setScores(Double[] scores) {
		this.scores = scores;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	
}
