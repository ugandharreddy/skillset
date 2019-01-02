package com.attra.pms.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ProficiencyTechnology implements Serializable {
	
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
	private static final long serialVersionUID = 1L;
	private String isPrimary;
	private String areaOfWork;
	private String stream;
	private String skill;
	private String technology;
	private int expYears;
	private String pLCheck;
	private int expMonths;
	private int empRating;
	private int mgrRating;
	private int finalRating;
	private String empComments;
	private String mgrComment;
	private String mgrCommentToPL;
	private String plCommentToSME;
	private String plComment;
		
	public String getPlComment() {
		return plComment;
	}
	public void setPlComment(String plComment) {
		this.plComment = plComment;
	}
	public String getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
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
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
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
	public String getpLCheck() {
		return pLCheck;
	}
	public void setpLCheck(String pLCheck) {
		this.pLCheck = pLCheck;
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
	public int getProficiencyId() {
		return proficiencyId;
	}
	public void setProficiencyId(int proficiencyId) {
		this.proficiencyId = proficiencyId;
	}
	
	public String getAowName() {
		return aowName;
	}
	public void setAowName(String aowName) {
		this.aowName = aowName;
	}
	public String getTechname() {
		return techname;
	}
	public void setTechname(String techname) {
		this.techname = techname;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public char getIsPrime() {
		return isPrime;
	}
	public void setIsPrime(char isPrime) {
		this.isPrime = isPrime;
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
	private int proficiencyId;
	private String aowName;
	private String techname;
	private String skillName;
	private int experience;
	private char isPrime;
	private String empComment;
	
	
	
}
