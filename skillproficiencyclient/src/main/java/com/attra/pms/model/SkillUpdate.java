package com.attra.pms.model;

public class SkillUpdate {
	
	private String emailId;
	
	private String skillId;
	
	private String areaId;
	
	private String isPrimary;

	private int experienceYear;
	
	private String streamId;
	
	private int experienceMonth;
	
	private int selfAssesment;

	private String comments;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	public int getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(int experienceYear) {
		this.experienceYear = experienceYear;
	}

	public String getStreamId() {
		return streamId;
	}

	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}

	public int getExperienceMonth() {
		return experienceMonth;
	}

	public void setExperienceMonth(int experienceMonth) {
		this.experienceMonth = experienceMonth;
	}

	public int getSelfAssesment() {
		return selfAssesment;
	}

	public void setSelfAssesment(int selfAssesment) {
		this.selfAssesment = selfAssesment;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
