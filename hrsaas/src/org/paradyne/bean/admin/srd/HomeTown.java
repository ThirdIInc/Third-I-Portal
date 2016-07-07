package org.paradyne.bean.admin.srd;
import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep K
 */
public class HomeTown extends BeanBase {
	
	String empId;
	String empName;
	String repType;
	String empToken;
	
	public HomeTown() { }
	public HomeTown(String empId, String empName, String repType,String empToken) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.repType = repType;
		this.empToken=empToken;
	}
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the repType
	 */
	public String getRepType() {
		return repType;
	}
	/**
	 * @param repType the repType to set
	 */
	public void setRepType(String repType) {
		this.repType = repType;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	
	
	
	

}

