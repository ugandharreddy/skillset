package com.attra.pms.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;


	@Component
	public class EmployeesInTeam implements Serializable {
		private static final long serialVersionUID = 1L;
		private String empName;
		private String technologyName;
		private String techPrimaryCheck;
		private int tExpYears;
		private int tExpMonths;
		private String domain;
		private String domainPrimaryCheck;
		private int dExpYears;
		private int dExpMonths;
		private String profilePic;
		private String emailID;
		private String empID;
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getTechnologyName() {
			return technologyName;
		}
		public void setTechnologyName(String technologyName) {
			this.technologyName = technologyName;
		}
		public String getTechPrimaryCheck() {
			return techPrimaryCheck;
		}
		public void setTechPrimaryCheck(String techPrimaryCheck) {
			this.techPrimaryCheck = techPrimaryCheck;
		}
		public int gettExpYears() {
			return tExpYears;
		}
		public void settExpYears(int tExpYears) {
			this.tExpYears = tExpYears;
		}
		public int gettExpMonths() {
			return tExpMonths;
		}
		public void settExpMonths(int tExpMonths) {
			this.tExpMonths = tExpMonths;
		}
		public String getDomain() {
			return domain;
		}
		public void setDomain(String domain) {
			this.domain = domain;
		}
		public String getDomainPrimaryCheck() {
			return domainPrimaryCheck;
		}
		public void setDomainPrimaryCheck(String domainPrimaryCheck) {
			this.domainPrimaryCheck = domainPrimaryCheck;
		}
		public int getdExpYears() {
			return dExpYears;
		}
		public void setdExpYears(int dExpYears) {
			this.dExpYears = dExpYears;
		}
		public int getdExpMonths() {
			return dExpMonths;
		}
		public void setdExpMonths(int dExpMonths) {
			this.dExpMonths = dExpMonths;
		}
		public String getProfilePic() {
			return profilePic;
		}
		public void setProfilePic(String profilePic) {
			this.profilePic = profilePic;
		}
		public String getEmailID() {
			return emailID;
		}
		public void setEmailID(String emailID) {
			this.emailID = emailID;
		}
		public String getEmpID() {
			return empID;
		}
		public void setEmpID(String empID) {
			this.empID = empID;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		
}




