package com.attra.pms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sessionId;
	
	private List<String> roleList= new ArrayList<String>();
	
	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	
	
}
