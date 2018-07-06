package com.fubonlife.bio.exam.entity.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fields")
public class Fields {
/////////////data vs fieldType fieldLabel
	@Id
	private String fieldId;
	private List<Object> fieldItems; //ExamItemList basic regexp(special) relation 
	private String fieldType;
	private String fieldLabel;
	private String formId;
	@DBRef
	private Forms form;
	public Forms getForm() {
		return form;
	}
	public void setForm(Forms form) {
		this.form = form;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public List<Object> getFieldItems() {
		return fieldItems;
	}

	public void setFieldItems(List<Object> fieldItems) {
		this.fieldItems = fieldItems;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

}
