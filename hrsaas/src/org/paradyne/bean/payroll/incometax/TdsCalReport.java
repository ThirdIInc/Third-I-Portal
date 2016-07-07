package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

public class TdsCalReport extends BeanBase {
	
	private String fromYear="";
	private String toYear="";
	private String empID="";
	private String payBillNo="";
	private String branchFlag="";
	private String paybillFlag="";
	private String departmentFlag="";
	private String divisionFlag="";
	private String emptypeFlag="";
	private String typeName="";
	private String branchName="";
	private String deptName="";
	private String divisionName="";
	private String branchCode=""; 
	private String deptCode="";
	private String divisionCode="";
	private String typeCode="";

	public String getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}

	public String getPaybillFlag() {
		return paybillFlag;
	}

	public void setPaybillFlag(String paybillFlag) {
		this.paybillFlag = paybillFlag;
	}

	public String getDepartmentFlag() {
		return departmentFlag;
	}

	public void setDepartmentFlag(String departmentFlag) {
		this.departmentFlag = departmentFlag;
	}

	public String getDivisionFlag() {
		return divisionFlag;
	}

	public void setDivisionFlag(String divisionFlag) {
		this.divisionFlag = divisionFlag;
	}

	public String getEmptypeFlag() {
		return emptypeFlag;
	}

	public void setEmptypeFlag(String emptypeFlag) {
		this.emptypeFlag = emptypeFlag;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	public String getPayBillNo() {
		return payBillNo;
	}

	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

}
