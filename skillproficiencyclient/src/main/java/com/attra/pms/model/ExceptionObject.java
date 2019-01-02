package com.attra.pms.model;

public class ExceptionObject {
	
	private Class<?> classname;
	private String methodName;
	private String emailId;
	private Exception e;
	
	
	
	public Class<?> getClassname() {
		return classname;
	}
	public void setClassname(Class<?> classname) {
		this.classname = classname;
	}
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
			

}
