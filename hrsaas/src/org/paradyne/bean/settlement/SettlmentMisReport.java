/**
 * 
 */
package org.paradyne.bean.settlement;

import org.paradyne.lib.BeanBase;

/**
 * @author ritac
 * 
 */
public class SettlmentMisReport extends BeanBase {
/* @param fields for settlement Mis report
 * @returns String
 */
	private String settId = "";
	private String frmDate = "";
	private String toDate = "";
	private String settBranch = "";
	private String settBranCode = "";
	private String settDept = "";
	private String settDeptCode = "";
	private String settDesg = "";
	private String settDesgCode = "";
	private String settDiv = "";
	private String settDivCode = "";
	private String empCode = "";
	private String empToken = "";
	private String empName = "";
	private String rptType = "";

	public String getSettId() {
		return settId;
	}

	public void setSettId(String settId) {
		this.settId = settId;
	}

	public String getFrmDate() {
		return frmDate;
	}

	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getSettBranch() {
		return settBranch;
	}

	public void setSettBranch(String settBranch) {
		this.settBranch = settBranch;
	}

	public String getSettBranCode() {
		return settBranCode;
	}

	public void setSettBranCode(String settBranCode) {
		this.settBranCode = settBranCode;
	}

	public String getSettDept() {
		return settDept;
	}

	public void setSettDept(String settDept) {
		this.settDept = settDept;
	}

	public String getSettDeptCode() {
		return settDeptCode;
	}

	public void setSettDeptCode(String settDeptCode) {
		this.settDeptCode = settDeptCode;
	}

	public String getSettDesg() {
		return settDesg;
	}

	public void setSettDesg(String settDesg) {
		this.settDesg = settDesg;
	}

	public String getSettDesgCode() {
		return settDesgCode;
	}

	public void setSettDesgCode(String settDesgCode) {
		this.settDesgCode = settDesgCode;
	}

	public String getSettDiv() {
		return settDiv;
	}

	public void setSettDiv(String settDiv) {
		this.settDiv = settDiv;
	}

	public String getSettDivCode() {
		return settDivCode;
	}

	public void setSettDivCode(String settDivCode) {
		this.settDivCode = settDivCode;
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

	public String getRptType() {
		return rptType;
	}

	public void setRptType(String rptType) {
		this.rptType = rptType;
	}

}
