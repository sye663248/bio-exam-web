package com.fubonlife.bio.mg.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="fields")
public class Fields {

	@Id
	private String fieldId;
	private String data;
	private String formId;
	
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public Object getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	
}
