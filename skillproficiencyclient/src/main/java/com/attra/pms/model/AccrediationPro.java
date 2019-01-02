package com.attra.pms.model;

public class AccrediationPro {
	
	private String accrediationDesc;
	private String validFrom;
	private String empComment;	
	

	public String getEmpComment() {
		return empComment;
	}

	public void setEmpComment(String empComment) {
		this.empComment = empComment;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return the accrediationDesc
	 */
	public String getAccrediationDesc() {
		return accrediationDesc;
	}

	/**
	 * @param accrediationDesc the accrediationDesc to set
	 */
	public void setAccrediationDesc(String accrediationDesc) {
		this.accrediationDesc = accrediationDesc;
	}
	
	
	

}
