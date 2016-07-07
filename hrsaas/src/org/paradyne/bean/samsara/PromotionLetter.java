/**
 * 
 */
package org.paradyne.bean.samsara;

import org.paradyne.lib.BeanBase;

/**
 * @author Lakkichand_Golegaonkar
 * @date 22nd August 2010
 */
public class PromotionLetter extends BeanBase {

	private String effDate="";
	private String promCode="";
	private String empCode="";
	private String empToken="";
	private String empName="";
	private String branch="";
	private String dept="";
	private String desg="";
	private String divCode="";
	private String divName="";
	
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getPromCode() {
		return promCode;
	}
	public void setPromCode(String promCode) {
		this.promCode = promCode;
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
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
}
