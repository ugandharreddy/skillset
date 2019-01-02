package com.attra.pms.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class EmployeeInSkill implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String skillName;
	private int numberOfEmp;
	
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public int getNumberOfEmp() {
		return numberOfEmp;
	}
	public void setNumberOfEmp(int numberOfEmp) {
		this.numberOfEmp = numberOfEmp;
	}
	
	

}
