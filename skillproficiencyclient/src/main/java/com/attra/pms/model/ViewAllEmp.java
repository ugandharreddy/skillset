package com.attra.pms.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class ViewAllEmp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String empId;
	private String emailId;
	private String empName;
	private String areaOfWork;
	private String projectName;
	private String primaryTech;
	private String technology;
	private String primaryDomain;
	private String domainName;
	private String methodlogy;
	private String toolName;
	private String accreditation;
	private int expInyear;
	private int expInmonth;
	
	
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAreaOfWork() {
		return areaOfWork;
	}
	public void setAreaOfWork(String areaOfWork) {
		this.areaOfWork = areaOfWork;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPrimaryTech() {
		return primaryTech;
	}
	public void setPrimaryTech(String primaryTech) {
		this.primaryTech = primaryTech;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public String getPrimaryDomain() {
		return primaryDomain;
	}
	public void setPrimaryDomain(String primaryDomain) {
		this.primaryDomain = primaryDomain;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getMethodlogy() {
		return methodlogy;
	}
	public void setMethodlogy(String methodlogy) {
		this.methodlogy = methodlogy;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public String getAccreditation() {
		return accreditation;
	}
	public void setAccreditation(String accreditation) {
		this.accreditation = accreditation;
	}
	public int getExpInyear() {
		return expInyear;
	}
	public void setExpInyear(int expInyear) {
		this.expInyear = expInyear;
	}
	public int getExpInmonth() {
		return expInmonth;
	}
	public void setExpInmonth(int expInmonth) {
		this.expInmonth = expInmonth;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	}
