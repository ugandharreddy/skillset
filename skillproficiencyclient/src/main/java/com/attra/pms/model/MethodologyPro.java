package com.attra.pms.model;

public class MethodologyPro {
	
	
	private String methodologyDesc;
	private Integer methodologySelfRating;
	private Integer methodologyExperience;
	private String empComment;
	
	

	public String getEmpComment() {
		return empComment;
	}

	public void setEmpComment(String empComment) {
		this.empComment = empComment;
	}

	/**
	 * @return the methodologyDesc
	 */
	public String getMethodologyDesc() {
		return methodologyDesc;
	}

	/**
	 * @param methodologyDesc the methodologyDesc to set
	 */
	public void setMethodologyDesc(String methodologyDesc) {
		this.methodologyDesc = methodologyDesc;
	}

	public Integer getMethodologySelfRating() {
		return methodologySelfRating;
	}

	public void setMethodologySelfRating(Integer methodologySelfRating) {
		this.methodologySelfRating = methodologySelfRating;
	}

	public Integer getMethodologyExperience() {
		return methodologyExperience;
	}

	public void setMethodologyExperience(Integer methodologyExperience) {
		this.methodologyExperience = methodologyExperience;
	}
	
	
	
	

}
