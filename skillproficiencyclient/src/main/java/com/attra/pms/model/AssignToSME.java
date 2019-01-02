package com.attra.pms.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class AssignToSME implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int smeId;
	private int empID;
	private String proficiencyType;
	private String proficiencyName;
	private String plComment;
	


	public String getPlComment() {
		return plComment;
	}




	public void setPlComment(String plComment) {
		this.plComment = plComment;
	}




	public int getSmeId() {
		return smeId;
	}




	public void setSmeId(int smeId) {
		this.smeId = smeId;
	}




	public int getEmpID() {
		return empID;
	}




	public void setEmpID(int empID) {
		this.empID = empID;
	}




	public String getProficiencyType() {
		return proficiencyType;
	}




	public void setProficiencyType(String proficiencyType) {
		this.proficiencyType = proficiencyType;
	}




	public String getProficiencyName() {
		return proficiencyName;
	}




	public void setProficiencyName(String proficiencyName) {
		this.proficiencyName = proficiencyName;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
