package com.attra.pms.model;

public class ToolProficiency {
	
	private String toolDesc;
	private String toolTypeDesc;
	private Integer toolSelfRating;
	private Integer toolExperience;
    private String empComment;
	
	
	public String getEmpComment() {
		return empComment;
	}
	public void setEmpComment(String empComment) {
		this.empComment = empComment;
	}
	/**
	 * @return the toolDesc
	 */
	public String getToolDesc() {
		return toolDesc;
	}
	/**
	 * @param toolDesc the toolDesc to set
	 */
	public void setToolDesc(String toolDesc) {
		this.toolDesc = toolDesc;
	}
	/**
	 * @return the toolTypeDesc
	 */
	public String getToolTypeDesc() {
		return toolTypeDesc;
	}
	public Integer getToolSelfRating() {
		return toolSelfRating;
	}
	public void setToolSelfRating(Integer toolSelfRating) {
		this.toolSelfRating = toolSelfRating;
	}
	public Integer getToolExperience() {
		return toolExperience;
	}
	public void setToolExperience(Integer toolExperience) {
		this.toolExperience = toolExperience;
	}
	/**
	 * @param toolTypeDesc the toolTypeDesc to set
	 */
	public void setToolTypeDesc(String toolTypeDesc) {
		this.toolTypeDesc = toolTypeDesc;
	}
	
	

}
