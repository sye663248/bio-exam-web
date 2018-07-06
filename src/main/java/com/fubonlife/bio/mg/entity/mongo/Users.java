package com.fubonlife.bio.mg.entity.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class Users /*extends BaseEntity*/ {

	@Id
	private String userId;
	
	private String account;
	
	private String password;
	
	private String key;
	
//	@DBRef
//	@Transient
//	private List<Systems> systems;
//	public List<Systems> getSystems() {
//		return systems;
//	}
//	public void setSystems(List<Systems> systems) {
//		this.systems = systems;
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
//	list.contains(user)
	@Override
	public boolean equals(Object obj) {   
        if (obj instanceof Users) {   
            Users u = (Users) obj;   
            return this.account.equals(u.getAccount());   
        }   
        return super.equals(obj);  
	}



	
}
