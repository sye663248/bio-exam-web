package com.fubonlife.bio.exam.entity.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="menus")
public class Menu {
	
	/** 序號 */
	@Id
	private String menuId;
	
	private String text;
	
	private Integer order;
	
	private String note;
	
	private String view;
	
	private String parentId;
	
	private Boolean leaf;
	
	@Transient
	List<Menu> data;
	
	
	
	

	public List<Menu> getData() {
		return data;
	}

	public void setData(List<Menu> data) {
		this.data = data;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	

}
