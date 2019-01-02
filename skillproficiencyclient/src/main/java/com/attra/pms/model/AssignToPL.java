package com.attra.pms.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class AssignToPL implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String empID;
	private String skill_Type;
	private String skill_Name;
	private String streamName;
	private String comments;

	


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getEmpID() {
		return empID;
	}


	public void setEmpID(String empID) {
		this.empID = empID;
	}


	public String getSkill_Type() {
		return skill_Type;
	}


	public void setSkill_Type(String skill_Type) {
		this.skill_Type = skill_Type;
	}


	public String getSkill_Name() {
		return skill_Name;
	}


	public void setSkill_Name(String skill_Name) {
		this.skill_Name = skill_Name;
	}


	public String getStreamName() {
		return streamName;
	}


	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
