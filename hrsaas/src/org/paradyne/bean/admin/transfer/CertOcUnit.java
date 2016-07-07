package org.paradyne.bean.admin.transfer;


import org.paradyne.lib.BeanBase;
/*
 * Pradeep K Sahoo
 * 
 */
public class CertOcUnit extends BeanBase{
	
	
	String empId;
	String empName;
	String repType;
	String empToken;
	String appDate;
	
	public CertOcUnit(){ }
	public CertOcUnit(String appDate,String empId, String empName, String repType,String empToken) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.repType = repType;
		this.empToken=empToken;
		this.appDate=appDate;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
	
	
	
	

}


