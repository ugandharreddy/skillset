package com.attra.pms.model;

import java.util.List;

public class ListResponseTask {
	
	List<SmeMyTaskView> list=null;
	String message=null;
	
	public List<SmeMyTaskView> getList() {
		return list;
	}
	public void setList(List<SmeMyTaskView> list) {
		this.list = list;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
