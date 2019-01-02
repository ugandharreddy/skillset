package com.attra.pms.model;

public class TechProficiency {
	
	
	private String techAreadesc;
	private String technologyDesc;
	private String techSkillDesc;
	private Character isTechnologyPrimary;
	private Integer technologyProf;
	private Integer technologySelfRating;
	private Integer technologyExperience;
	private String empComment;
	private String mngerComment;
	
	
	
	public String getEmpComment() {
		return empComment;
	}
	public void setEmpComment(String empComment) {
		this.empComment = empComment;
	}
	public String getMngerComment() {
		return mngerComment;
	}
	public void setMngerComment(String mngerComment) {
		this.mngerComment = mngerComment;
	}
	public Integer getTechnologySelfRating() {
		return technologySelfRating;
	}
	public void setTechnologySelfRating(Integer technologySelfRating) {
		this.technologySelfRating = technologySelfRating;
	}
	public Integer getTechnologyExperience() {
		return technologyExperience;
	}
	public void setTechnologyExperience(Integer technologyExperience) {
		this.technologyExperience = technologyExperience;
	}
	
	/**
	 * @return the techAreadesc
	 */
	public String getTechAreadesc() {
		return techAreadesc;
	}
	/**
	 * @param techAreadesc the techAreadesc to set
	 */
	public void setTechAreadesc(String techAreadesc) {
		this.techAreadesc = techAreadesc;
	}
	/**
	 * @return the technologyDesc
	 */
	public String getTechnologyDesc() {
		return technologyDesc;
	}
	/**
	 * @param technologyDesc the technologyDesc to set
	 */
	public void setTechnologyDesc(String technologyDesc) {
		this.technologyDesc = technologyDesc;
	}
	/**
	 * @return the techSkillDesc
	 */
	public String getTechSkillDesc() {
		return techSkillDesc;
	}
	/**
	 * @param techSkillDesc the techSkillDesc to set
	 */
	public void setTechSkillDesc(String techSkillDesc) {
		this.techSkillDesc = techSkillDesc;
	}
	/**
	 * @return the isTechnologyPrimary
	 */

	/**
	 * @return the technologyProf
	 */
	public Integer getTechnologyProf() {
		return technologyProf;
	}
	/**
	 * @return the isTechnologyPrimary
	 */
	public Character getIsTechnologyPrimary() {
		return isTechnologyPrimary;
	}
	/**
	 * @param isTechnologyPrimary the isTechnologyPrimary to set
	 */
	public void setIsTechnologyPrimary(Character isTechnologyPrimary) {
		this.isTechnologyPrimary = isTechnologyPrimary;
	}
	/**
	 * @param technologyProf the technologyProf to set
	 */
	public void setTechnologyProf(Integer technologyProf) {
		this.technologyProf = technologyProf;
	}
	
	

}
