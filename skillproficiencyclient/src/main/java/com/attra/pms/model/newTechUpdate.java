package com.attra.pms.model;

import java.io.Serializable;


public class newTechUpdate implements Serializable {


	private String empEmailId;

	private String isPrimery;
	private int experience;
	private int selfAsses;
	public String getEmpEmailId() {
		return empEmailId;
	}
	public void setEmpEmailId(String empEmailId) {
		this.empEmailId = empEmailId;
	}
	public String getIsPrimery() {
		return isPrimery;
	}
	public void setIsPrimery(String isPrimery) {
		this.isPrimery = isPrimery;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getSelfAsses() {
		return selfAsses;
	}
	public void setSelfAsses(int selfAsses) {
		this.selfAsses = selfAsses;
	}
	public String getEmpComments() {
		return empComments;
	}
	public void setEmpComments(String empComments) {
		this.empComments = empComments;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	private String empComments;
	private int skillId;
	


}
