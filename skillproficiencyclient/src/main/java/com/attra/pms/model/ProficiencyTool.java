package com.attra.pms.model;

import java.io.Serializable;

public class ProficiencyTool implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int tExpYears;
	private int tExpMonths;
	private int empRating;
	private int mgrRating;
	private int finalRating;
	private String empComments;
	private String mgrComment;
	private String toolName;
	private String toolType;
	
	public String getToolType() {
		return toolType;
	}
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
	public int gettExpYears() {
		return tExpYears;
	}
	public void settExpYears(int tExpYears) {
		this.tExpYears = tExpYears;
	}
	public int gettExpMonths() {
		return tExpMonths;
	}
	public void settExpMonths(int tExpMonths) {
		this.tExpMonths = tExpMonths;
	}
	public int getEmpRating() {
		return empRating;
	}
	public void setEmpRating(int empRating) {
		this.empRating = empRating;
	}
	public int getMgrRating() {
		return mgrRating;
	}
	public void setMgrRating(int mgrRating) {
		this.mgrRating = mgrRating;
	}
	public int getFinalRating() {
		return finalRating;
	}
	public void setFinalRating(int finalRating) {
		this.finalRating = finalRating;
	}
	public String getEmpComments() {
		return empComments;
	}
	public void setEmpComments(String empComments) {
		this.empComments = empComments;
	}
	public String getMgrComment() {
		return mgrComment;
	}
	public void setMgrComment(String mgrComment) {
		this.mgrComment = mgrComment;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
