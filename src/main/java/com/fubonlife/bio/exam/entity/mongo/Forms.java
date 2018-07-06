package com.fubonlife.bio.exam.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="forms")
public class Forms {

	@Id
	private String formId;
	private String formName;
	private String systemId;
	@DBRef
	private Systems system;
	public Systems getSystem() {
		return system;
	}
	public void setSystem(Systems system) {
		this.system = system;
	}
	
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
	
}
