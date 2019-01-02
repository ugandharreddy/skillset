package com.attra.pms.model;

public class newToolandMethodologyUpdate {
	
	private String empEmailId;
	private int toolId;
	//private String toolName;
	private int experience;
	public String getEmpEmailId() {
		return empEmailId;
	}
	public void setEmpEmailId(String empEmailId) {
		this.empEmailId = empEmailId;
	}
	public int getToolId() {
		return toolId;
	}
	public void setToolId(int toolId) {
		this.toolId = toolId;
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
	public int getMethodologyId() {
		return methodologyId;
	}
	public void setMethodologyId(int methodologyId) {
		this.methodologyId = methodologyId;
	}
	private int selfAsses;
	private String empComments;
	private int methodologyId;
	
	
}
