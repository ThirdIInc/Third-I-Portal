package org.paradyne.bean.attendance.monthlyAttendance;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class UploadMonthlyAttnStatistics extends BeanBase {
	
	/**
	 * FOR STATISTICS
	 */
	
	private String ittBranchName;
	private String ittBranchCode;
	private String ittUploadedEmployee;
	private String ittLeaveApplication;
	private String ittResignedEmployee;
	private String ittOnHoldEmployee;
	private String totaluploadeFlag;
	private String totalresigneFlag;
	private String totalleaveFlag;
	private String branchNameattn="";
	public String getBranchNameattn() {
		return branchNameattn;
	}
	public void setBranchNameattn(String branchNameattn) {
		this.branchNameattn = branchNameattn;
	}
	private String divisionCode="";
	private String branchCode="";
	private String fromdate="";
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	private String todate="";
	
	
	
	public String getTotalleaveFlag() {
		return totalleaveFlag;
	}
	public void setTotalleaveFlag(String totalleaveFlag) {
		this.totalleaveFlag = totalleaveFlag;
	}
	public String getTotaluploadeFlag() {
		return totaluploadeFlag;
	}
	public void setTotaluploadeFlag(String totaluploadeFlag) {
		this.totaluploadeFlag = totaluploadeFlag;
	}
	public String getTotalresigneFlag() {
		return totalresigneFlag;
	}
	public void setTotalresigneFlag(String totalresigneFlag) {
		this.totalresigneFlag = totalresigneFlag;
	}
	private String ittTotalEmployee;
	private String ittTotalNoEmployee;
	
	
	public String getIttTotalNoEmployee() {
		return ittTotalNoEmployee;
	}
	public void setIttTotalNoEmployee(String ittTotalNoEmployee) {
		this.ittTotalNoEmployee = ittTotalNoEmployee;
	}
	private String ittNotUplEmployee;
	public String getIttTotalEmployee() {
		return ittTotalEmployee;
	}
	public void setIttTotalEmployee(String ittTotalEmployee) {
		this.ittTotalEmployee = ittTotalEmployee;
	}
	public String getIttNotUplEmployee() {
		return ittNotUplEmployee;
	}
	public void setIttNotUplEmployee(String ittNotUplEmployee) {
		this.ittNotUplEmployee = ittNotUplEmployee;
	}
	public String gethBranchCode() {
		return hBranchCode;
	}
	public void sethBranchCode(String hBranchCode) {
		this.hBranchCode = hBranchCode;
	}
	public String gethEmpType() {
		return hEmpType;
	}
	public void sethEmpType(String hEmpType) {
		this.hEmpType = hEmpType;
	}
	//private String ittBranchName;
	private String ittTotalUploadedEmployee;
	private String ittTotalLeaveApplication;
	private String ittTotalResignedEmployee;
	private String ittTotalOnHoldEmployee;
	
	private String hBranchCode="";
	private String hEmpType="";
	private String removeEmpCode="";
	private String removeEmpToken="";
	private String removeEmpName="";
	private String onHoldEmpCode="";
	private String onHoldEmpToken="";
	private String onHoldEmpName="";	
	private String onHoldAddEmpCode="";
	private String onHoldAddEmpToken="";
	private String onHoldAddEmpName="";	
	private ArrayList statisticsList=null; 
	
	
	private String onholdFlag="";
	private String resigneFlag="";
	private String leaveFlag="";
	private String uploadeFlag="";
	public String getResigneFlag() {
		return resigneFlag;
	}
	public void setResigneFlag(String resigneFlag) {
		this.resigneFlag = resigneFlag;
	}
	public String getLeaveFlag() {
		return leaveFlag;
	}
	public void setLeaveFlag(String leaveFlag) {
		this.leaveFlag = leaveFlag;
	}
	public String getUploadeFlag() {
		return uploadeFlag;
	}
	public void setUploadeFlag(String uploadeFlag) {
		this.uploadeFlag = uploadeFlag;
	}
	public String getOnholdFlag() {
		return onholdFlag;
	}
	public void setOnholdFlag(String onholdFlag) {
		this.onholdFlag = onholdFlag;
	}
	private String uploadFileName = "";
	private boolean branchFlag = false;
	private boolean departmentFlag = false;
	private boolean payBillFlag = false;
	private boolean employeeTypeFlag = false;
	private boolean divisionFlag = false;
	private String recoveryFlag = "false";
	private String month="0";
	private String year="0";
	private String branchId="";
	private String branchName="";
	private String departmentId="";
	private String departmentName="";
	private String payBillId="";
	private String payBilName="";
	private String employeeTypeId="";
	private String employeeTypeName="";
	private String divisionId="";
	private String divisionName="";
	private String employeeName="";
	private String empSerachId="";
	
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public boolean isBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}
	public boolean isDepartmentFlag() {
		return departmentFlag;
	}
	public void setDepartmentFlag(boolean departmentFlag) {
		this.departmentFlag = departmentFlag;
	}
	public boolean isPayBillFlag() {
		return payBillFlag;
	}
	public void setPayBillFlag(boolean payBillFlag) {
		this.payBillFlag = payBillFlag;
	}
	public boolean isEmployeeTypeFlag() {
		return employeeTypeFlag;
	}
	public void setEmployeeTypeFlag(boolean employeeTypeFlag) {
		this.employeeTypeFlag = employeeTypeFlag;
	}
	public boolean isDivisionFlag() {
		return divisionFlag;
	}
	public void setDivisionFlag(boolean divisionFlag) {
		this.divisionFlag = divisionFlag;
	}
	public String getRecoveryFlag() {
		return recoveryFlag;
	}
	public void setRecoveryFlag(String recoveryFlag) {
		this.recoveryFlag = recoveryFlag;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPayBillId() {
		return payBillId;
	}
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	public String getPayBilName() {
		return payBilName;
	}
	public void setPayBilName(String payBilName) {
		this.payBilName = payBilName;
	}
	public String getEmployeeTypeId() {
		return employeeTypeId;
	}
	public void setEmployeeTypeId(String employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmpSerachId() {
		return empSerachId;
	}
	public void setEmpSerachId(String empSerachId) {
		this.empSerachId = empSerachId;
	}
	public String getIttBranchName() {
		return ittBranchName;
	}
	public void setIttBranchName(String ittBranchName) {
		this.ittBranchName = ittBranchName;
	}
	public String getIttUploadedEmployee() {
		return ittUploadedEmployee;
	}
	public void setIttUploadedEmployee(String ittUploadedEmployee) {
		this.ittUploadedEmployee = ittUploadedEmployee;
	}
	public String getIttLeaveApplication() {
		return ittLeaveApplication;
	}
	public void setIttLeaveApplication(String ittLeaveApplication) {
		this.ittLeaveApplication = ittLeaveApplication;
	}
	public String getIttResignedEmployee() {
		return ittResignedEmployee;
	}
	public void setIttResignedEmployee(String ittResignedEmployee) {
		this.ittResignedEmployee = ittResignedEmployee;
	}
	public String getIttOnHoldEmployee() {
		return ittOnHoldEmployee;
	}
	public void setIttOnHoldEmployee(String ittOnHoldEmployee) {
		this.ittOnHoldEmployee = ittOnHoldEmployee;
	}
	public ArrayList getStatisticsList() {
		return statisticsList;
	}
	public void setStatisticsList(ArrayList statisticsList) {
		this.statisticsList = statisticsList;
	}
	public String getIttTotalUploadedEmployee() {
		return ittTotalUploadedEmployee;
	}
	public void setIttTotalUploadedEmployee(String ittTotalUploadedEmployee) {
		this.ittTotalUploadedEmployee = ittTotalUploadedEmployee;
	}
	public String getIttTotalLeaveApplication() {
		return ittTotalLeaveApplication;
	}
	public void setIttTotalLeaveApplication(String ittTotalLeaveApplication) {
		this.ittTotalLeaveApplication = ittTotalLeaveApplication;
	}
	public String getIttTotalResignedEmployee() {
		return ittTotalResignedEmployee;
	}
	public void setIttTotalResignedEmployee(String ittTotalResignedEmployee) {
		this.ittTotalResignedEmployee = ittTotalResignedEmployee;
	}
	public String getIttTotalOnHoldEmployee() {
		return ittTotalOnHoldEmployee;
	}
	public void setIttTotalOnHoldEmployee(String ittTotalOnHoldEmployee) {
		this.ittTotalOnHoldEmployee = ittTotalOnHoldEmployee;
	}
	public String getIttBranchCode() {
		return ittBranchCode;
	}
	public void setIttBranchCode(String ittBranchCode) {
		this.ittBranchCode = ittBranchCode;
	}
	public String getHBranchCode() {
		return hBranchCode;
	}
	public void setHBranchCode(String branchCode) {
		hBranchCode = branchCode;
	}
	public String getHEmpType() {
		return hEmpType;
	}
	public void setHEmpType(String empType) {
		hEmpType = empType;
	}
	public String getRemoveEmpCode() {
		return removeEmpCode;
	}
	public void setRemoveEmpCode(String removeEmpCode) {
		this.removeEmpCode = removeEmpCode;
	}
	public String getRemoveEmpToken() {
		return removeEmpToken;
	}
	public void setRemoveEmpToken(String removeEmpToken) {
		this.removeEmpToken = removeEmpToken;
	}
	public String getRemoveEmpName() {
		return removeEmpName;
	}
	public void setRemoveEmpName(String removeEmpName) {
		this.removeEmpName = removeEmpName;
	}
	public String getOnHoldEmpCode() {
		return onHoldEmpCode;
	}
	public void setOnHoldEmpCode(String onHoldEmpCode) {
		this.onHoldEmpCode = onHoldEmpCode;
	}
	public String getOnHoldEmpToken() {
		return onHoldEmpToken;
	}
	public void setOnHoldEmpToken(String onHoldEmpToken) {
		this.onHoldEmpToken = onHoldEmpToken;
	}
	public String getOnHoldEmpName() {
		return onHoldEmpName;
	}
	public void setOnHoldEmpName(String onHoldEmpName) {
		this.onHoldEmpName = onHoldEmpName;
	}
	public String getOnHoldAddEmpCode() {
		return onHoldAddEmpCode;
	}
	public void setOnHoldAddEmpCode(String onHoldAddEmpCode) {
		this.onHoldAddEmpCode = onHoldAddEmpCode;
	}
	public String getOnHoldAddEmpToken() {
		return onHoldAddEmpToken;
	}
	public void setOnHoldAddEmpToken(String onHoldAddEmpToken) {
		this.onHoldAddEmpToken = onHoldAddEmpToken;
	}
	public String getOnHoldAddEmpName() {
		return onHoldAddEmpName;
	}
	public void setOnHoldAddEmpName(String onHoldAddEmpName) {
		this.onHoldAddEmpName = onHoldAddEmpName;
	}
}
