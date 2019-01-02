package com.attra.pms.model;

import java.io.Serializable;

public class ProficiencyDomain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int dExpYears;
	private int dExpMonths;
	private int mgrRating;
	private int finalRating;
	private String empComments;
	
	private String mgrComment;
	private String dName;
	private String isPrimary;
	private String sGroup;
	private String pLCheck;
	private int areaId;
	private String areaName;

	private String mgrCommentToPL;
	private String plCommentToSME;
	
	private String domainName;
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	private int proficiencyId;
	private String domain;
	private String domainCategory;
	private char isPrime;
	private int experience;
	private int empRating;
	private String empComment;
	public int getdExpYears() {
		return dExpYears;
	}
	public void setdExpYears(int dExpYears) {
		this.dExpYears = dExpYears;
	}
	public int getdExpMonths() {
		return dExpMonths;
	}
	public void setdExpMonths(int dExpMonths) {
		this.dExpMonths = dExpMonths;
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
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getsGroup() {
		return sGroup;
	}
	public void setsGroup(String sGroup) {
		this.sGroup = sGroup;
	}
	public String getpLCheck() {
		return pLCheck;
	}
	public void setpLCheck(String pLCheck) {
		this.pLCheck = pLCheck;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getMgrCommentToPL() {
		return mgrCommentToPL;
	}
	public void setMgrCommentToPL(String mgrCommentToPL) {
		this.mgrCommentToPL = mgrCommentToPL;
	}
	public String getPlCommentToSME() {
		return plCommentToSME;
	}
	public void setPlCommentToSME(String plCommentToSME) {
		this.plCommentToSME = plCommentToSME;
	}
	public int getProficiencyId() {
		return proficiencyId;
	}
	public void setProficiencyId(int proficiencyId) {
		this.proficiencyId = proficiencyId;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDomainCategory() {
		return domainCategory;
	}
	public void setDomainCategory(String domainCategory) {
		this.domainCategory = domainCategory;
	}
	public char getIsPrime() {
		return isPrime;
	}
	public void setIsPrime(char isPrime) {
		this.isPrime = isPrime;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getEmpRating() {
		return empRating;
	}
	public void setEmpRating(int empRating) {
		this.empRating = empRating;
	}
	public String getEmpComment() {
		return empComment;
	}
	public void setEmpComment(String empComment) {
		this.empComment = empComment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
