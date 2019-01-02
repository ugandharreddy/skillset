package com.attra.pms.model;

import java.util.List;

public class EmployeeExportdata {
	
	private String employeeId;
	private int totaExp;
	private String areaOfWork;
	private String project;
	private List<TechProficiency> techProficiency;
	private List<DomainProficiency> domainProficiency;
	private List<ToolProficiency> toolProficiency;
	private List<MethodologyPro> methodology;
	private List<AccrediationPro> accrediation;
	
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the totaExp
	 */
	
	/**
	 * @return the areaOfWork
	 */
	public String getAreaOfWork() {
		return areaOfWork;
	}
	/**
	 * @return the totaExp
	 */
	public int getTotaExp() {
		return totaExp;
	}
	/**
	 * @param totaExp the totaExp to set
	 */
	public void setTotaExp(int totaExp) {
		this.totaExp = totaExp;
	}
	/**
	 * @param areaOfWork the areaOfWork to set
	 */
	public void setAreaOfWork(String areaOfWork) {
		this.areaOfWork = areaOfWork;
	}
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * @return the techProficiency
	 */
	public List<TechProficiency> getTechProficiency() {
		return techProficiency;
	}
	/**
	 * @param techProficiency the techProficiency to set
	 */
	public void setTechProficiency(List<TechProficiency> techProficiency) {
		this.techProficiency = techProficiency;
	}
	/**
	 * @return the domainProficiency
	 */
	public List<DomainProficiency> getDomainProficiency() {
		return domainProficiency;
	}
	/**
	 * @param domainProficiency the domainProficiency to set
	 */
	public void setDomainProficiency(List<DomainProficiency> domainProficiency) {
		this.domainProficiency = domainProficiency;
	}
	/**
	 * @return the toolProficiency
	 */
	public List<ToolProficiency> getToolProficiency() {
		return toolProficiency;
	}
	/**
	 * @param toolProficiency the toolProficiency to set
	 */
	public void setToolProficiency(List<ToolProficiency> toolProficiency) {
		this.toolProficiency = toolProficiency;
	}
	/**
	 * @return the methodology
	 */
	public List<MethodologyPro> getMethodology() {
		return methodology;
	}
	/**
	 * @param methodology the methodology to set
	 */
	public void setMethodology(List<MethodologyPro> methodology) {
		this.methodology = methodology;
	}
	/**
	 * @return the accrediation
	 */
	public List<AccrediationPro> getAccrediation() {
		return accrediation;
	}
	/**
	 * @param accrediation the accrediation to set
	 */
	public void setAccrediation(List<AccrediationPro> accrediation) {
		this.accrediation = accrediation;
	}
	
	
}
