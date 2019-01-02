package com.attra.pms.model;

import java.io.Serializable;


public class newSubDomainUpdate implements Serializable {

	private String empEmailId;
	private int subDomainId;
	private String isPrimery;
	private int experiency;
	private int selfAsses;
	private String empComments;
	public String getEmpEmailId() {
		return empEmailId;
	}
	public void setEmpEmailId(String empEmailId) {
		this.empEmailId = empEmailId;
	}
	public int getSubDomainId() {
		return subDomainId;
	}
	public void setSubDomainId(int subDomainId) {
		this.subDomainId = subDomainId;
	}
	public String getIsPrimery() {
		return isPrimery;
	}
	public void setIsPrimery(String isPrimery) {
		this.isPrimery = isPrimery;
	}
	public int getExperiency() {
		return experiency;
	}
	public void setExperiency(int experiency) {
		this.experiency = experiency;
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

}
