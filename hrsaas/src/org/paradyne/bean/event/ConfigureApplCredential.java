package org.paradyne.bean.event;

import org.paradyne.lib.BeanBase;

public class ConfigureApplCredential extends BeanBase {
	
	private String empCode ="";
	private String empToken ="";
	private String empName ="";
	private String applicationCode ="";
	private String userName ="";
	private String userPassword ="";
	private String hiddenAutoCode ="";
	public String getHiddenAutoCode() {
		return hiddenAutoCode;
	}
	public void setHiddenAutoCode(String hiddenAutoCode) {
		this.hiddenAutoCode = hiddenAutoCode;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
