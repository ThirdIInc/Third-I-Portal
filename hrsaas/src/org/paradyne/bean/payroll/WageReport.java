/**
 * 
 */
package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class WageReport extends BeanBase {
	private String empID;
	private String empTokenNo ;
	private String empName ;
	private String designation ;
	private String branch ;
	private String fromYear;
	private String toYear;
	
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpTokenNo() {
		return empTokenNo;
	}
	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	

}
