package com.attra.pms.model;

public class DomainProficiency {
	
	
	private String domainDesc;
	private String subDomainDesc;
	private Character isDPrimary;
	private Integer domainProficiency;
	private Integer domainSelfRating;
	private Integer domainExperience;
	private String empComment;
	private String reviewerComment;
	
	
	public String getEmpComment() {
		return empComment;
	}
	public void setEmpComment(String empComment) {
		this.empComment = empComment;
	}
	public String getReviewerComment() {
		return reviewerComment;
	}
	public void setReviewerComment(String reviewerComment) {
		this.reviewerComment = reviewerComment;
	}
	
	
	public Integer getDomainSelfRating() {
		return domainSelfRating;
	}
	public void setDomainSelfRating(Integer domainSelfRating) {
		this.domainSelfRating = domainSelfRating;
	}
	public Integer getDomainExperience() {
		return domainExperience;
	}
	public void setDomainExperience(Integer domainExperience) {
		this.domainExperience = domainExperience;
	}
	/**
	 * @return the domainDesc
	 */
	public String getDomainDesc() {
		return domainDesc;
	}
	/**
	 * @param domainDesc the domainDesc to set
	 */
	public void setDomainDesc(String domainDesc) {
		this.domainDesc = domainDesc;
	}
	/**
	 * @return the subDomainDesc
	 */
	public String getSubDomainDesc() {
		return subDomainDesc;
	}
	/**
	 * @param subDomainDesc the subDomainDesc to set
	 */
	public void setSubDomainDesc(String subDomainDesc) {
		this.subDomainDesc = subDomainDesc;
	}
	/**
	 * @return the isDPrimary
	 */
	
	/**
	 * @return the domainProficiency
	 */
	public Integer getDomainProficiency() {
		return domainProficiency;
	}
	/**
	 * @return the isDPrimary
	 */
	public Character getIsDPrimary() {
		return isDPrimary;
	}
	/**
	 * @param isDPrimary the isDPrimary to set
	 */
	public void setIsDPrimary(Character isDPrimary) {
		this.isDPrimary = isDPrimary;
	}
	/**
	 * @param domainProficiency the domainProficiency to set
	 */
	public void setDomainProficiency(Integer domainProficiency) {
		this.domainProficiency = domainProficiency;
	}
	
	

}
