package org.paradyne.bean.leave;
import org.paradyne.lib.BeanBase;

/*
 * Pradeep K Sahoo
 * Dt:25-06-2007
 * 
 */
public class leaveBalanceReport extends BeanBase{
	
	
	String empId;
	String empName;
	String repType;
	String empToken;
	
	
	public leaveBalanceReport(){ }
	public leaveBalanceReport(String empId, String empName, String repType,String empToken) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.repType = repType;
		this.empToken=empToken;
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
	

}







