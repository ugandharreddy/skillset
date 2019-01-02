package com.attra.pms.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class SMEList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String smeEmailId;
	private String empId;
	private String eName;
	private String smeName;

	public String getSmeName() {
		return smeName;
	}
	public void setSmeName(String smeName) {
		this.smeName = smeName;
	}
	public String getSmeEmailId() {
		return smeEmailId;
	}
	public void setSmeEmailId(String smeEmailId) {
		this.smeEmailId = smeEmailId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
		
	}
	
}
