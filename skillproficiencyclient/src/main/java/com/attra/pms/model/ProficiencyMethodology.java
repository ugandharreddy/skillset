package com.attra.pms.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ProficiencyMethodology implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int mExpYears;
	private int mExpMonths;
	private int empRating;
	private int mgrRating;
	private int finalRating;
	private String comments;
	private String mgrComment;
	private String mName;
	
	public int getmExpYears() {
		return mExpYears;
	}
	public void setmExpYears(int mExpYears) {
		this.mExpYears = mExpYears;
	}
	public int getmExpMonths() {
		return mExpMonths;
	}
	public void setmExpMonths(int mExpMonths) {
		this.mExpMonths = mExpMonths;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getMgrComment() {
		return mgrComment;
	}
	public void setMgrComment(String mgrComment) {
		this.mgrComment = mgrComment;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
