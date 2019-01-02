package com.attra.pms.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ProficiencyCoreSkill implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String areaOfWork;
	private String stream;
	private String technology;
	private int expYears;
	private int expMonths;
	private int empRating;
	private int mgrRating;
	private int finalRating;
	private String empComments;
	private String mgrComment;


	

	public String getAreaOfWork() {
		return areaOfWork;
	}
	public void setAreaOfWork(String areaOfWork) {
		this.areaOfWork = areaOfWork;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public int getExpYears() {
		return expYears;
	}
	public void setExpYears(int expYears) {
		this.expYears = expYears;
	}
	public int getExpMonths() {
		return expMonths;
	}
	public void setExpMonths(int expMonths) {
		this.expMonths = expMonths;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
