package com.attra.pms.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class ProjectList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int projectID;
	private String projectName;
	private String managerMailID;
	
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getManagerMailID() {
		return managerMailID;
	}
	public void setManagerMailID(String managerMailID) {
		this.managerMailID = managerMailID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
