package com.attra.pms.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class UserDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String empID;
	private String empName;
	private String mgrName;
	private String roleType;
	private String areaOfWork;
	private String projectName;
	private String emailID;
	private String mgrId;
	private int roleID;
	private int techId;
	private int projectId;
	private String designation;
	private String doj;
	private int attraExpYears;
	private int attraExpMonths;
	private int totalExpYears;
	private int totalExpMonths;
	private String status;
	private String profilePic;
	private String resume;
	private String contactNumber;
	private String technology;
	private String techPrimaryCheck;
	private int technologyRating;
    private String methodology;
    private int methodRating;
	private String domain;
	private String domainPrimaryCheck;
	private int domainRating;
	private String toolName;
	private int toolRating;
	
	

	
	public String getTechPrimaryCheck() {
		return techPrimaryCheck;
	}
	public void setTechPrimaryCheck(String techPrimaryCheck) {
		this.techPrimaryCheck = techPrimaryCheck;
	}
	public String getDomainPrimaryCheck() {
		return domainPrimaryCheck;
	}
	public void setDomainPrimaryCheck(String domainPrimaryCheck) {
		this.domainPrimaryCheck = domainPrimaryCheck;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getMgrName() {
		return mgrName;
	}
	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
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
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getMgrId() {
		return mgrId;
	}
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public int getTechId() {
		return techId;
	}
	public void setTechId(int techId) {
		this.techId = techId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public int getAttraExpYears() {
		return attraExpYears;
	}
	public void setAttraExpYears(int attraExpYears) {
		this.attraExpYears = attraExpYears;
	}
	public int getAttraExpMonths() {
		return attraExpMonths;
	}
	public void setAttraExpMonths(int attraExpMonths) {
		this.attraExpMonths = attraExpMonths;
	}
	public int getTotalExpYears() {
		return totalExpYears;
	}
	public void setTotalExpYears(int totalExpYears) {
		this.totalExpYears = totalExpYears;
	}
	public int getTotalExpMonths() {
		return totalExpMonths;
	}
	public void setTotalExpMonths(int totalExpMonths) {
		this.totalExpMonths = totalExpMonths;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public int getTechnologyRating() {
		return technologyRating;
	}
	public void setTechnologyRating(int technologyRating) {
		this.technologyRating = technologyRating;
	}
	public String getMethodology() {
		return methodology;
	}
	public void setMethodology(String methodology) {
		this.methodology = methodology;
	}
	public int getMethodRating() {
		return methodRating;
	}
	public void setMethodRating(int methodRating) {
		this.methodRating = methodRating;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getDomainRating() {
		return domainRating;
	}
	public void setDomainRating(int domainRating) {
		this.domainRating = domainRating;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public int getToolRating() {
		return toolRating;
	}
	public void setToolRating(int toolRating) {
		this.toolRating = toolRating;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
