package com.fubonlife.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fubonlife.model.Version.Threshold;

public class VersionN {
	@JsonProperty("result")
	String result;
	@JsonProperty("version")
	String version;
	@JsonProperty("threshold")
	Threshold threshold;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Threshold getThreshold() {
		return threshold;
	}
	public void setThreshold(Threshold threshold) {
		this.threshold = threshold;
	}
	
	
	// Threshold (Inner class)
		public class Threshold {
			
			@JsonProperty("1e-3")
			Double le3;

			@JsonProperty("1e-4")
			Double le4;

			@JsonProperty("1e-5")
			Double le5;

			@JsonProperty("1e-6")
			Double le6;

			public Double getLe3() {
				return le3;
			}

			public void setLe3(Double le3) {
				this.le3 = le3;
			}

			public Double getLe4() {
				return le4;
			}

			public void setLe4(Double le4) {
				this.le4 = le4;
			}

			public Double getLe5() {
				return le5;
			}

			public void setLe5(Double le5) {
				this.le5 = le5;
			}

			public Double getLe6() {
				return le6;
			}

			public void setLe6(Double le6) {
				this.le6 = le6;
			}
		}
	
}
