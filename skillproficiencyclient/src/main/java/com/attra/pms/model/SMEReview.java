package com.attra.pms.model;

public class SMEReview {
	private int mgrRating;
	private String mgrComment;
	private String empName;
	private String empId;
	private String empRating;
	private int eExpYears;
	private int eExpMonths;
	private String proficiencyName;
	public int getMgrRating() {
		return mgrRating;
	}
	public void setMgrRating(int mgrRating) {
		this.mgrRating = mgrRating;
	}
	public String getMgrComment() {
		return mgrComment;
	}
	public void setMgrComment(String mgrComment) {
		this.mgrComment = mgrComment;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpRating() {
		return empRating;
	}
	public void setEmpRating(String empRating) {
		this.empRating = empRating;
	}
	public int geteExpYears() {
		return eExpYears;
	}
	public void seteExpYears(int eExpYears) {
		this.eExpYears = eExpYears;
	}
	public int geteExpMonths() {
		return eExpMonths;
	}
	public void seteExpMonths(int eExpMonths) {
		this.eExpMonths = eExpMonths;
	}
	public String getProficiencyName() {
		return proficiencyName;
	}
	public void setProficiencyName(String proficiencyName) {
		this.proficiencyName = proficiencyName;
	}

	
	
	
}

