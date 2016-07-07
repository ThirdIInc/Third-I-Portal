package org.paradyne.bean.attendance.monthlyAttendance;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class MonthlyAttnProcessStatistics extends BeanBase {
	private boolean branchFlag = false;
	private boolean departmentFlag = false;
	private boolean payBillFlag = false;
	private boolean employeeTypeFlag = false;
	private boolean divisionFlag = false;
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
	private String myPage = "";
	private String listLength = "false";
	private String hBranchCode="";
	private String hEmpType="";
	private String payBillName="";
	
	private String ittOnholdEmployee="";
	private String onHoldFlag="";
	
	
	private String removeEmpCode="";
	private String removeEmpToken = "";
	private String removeEmpName = "false";
	private String onHoldAddEmpCode="";
	private String onHoldAddEmpToken="";
	private String onHoldAddEmpName="";
	private String onHoldEmpCode="";
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
	public String getOnHoldEmpCode() {
		return onHoldEmpCode;
	}
	public void setOnHoldEmpCode(String onHoldEmpCode) {
		this.onHoldEmpCode = onHoldEmpCode;
	}
	public String getOnHoldEmpName() {
		return onHoldEmpName;
	}
	public void setOnHoldEmpName(String onHoldEmpName) {
		this.onHoldEmpName = onHoldEmpName;
	}
	public String getOnHoldEmpToken() {
		return onHoldEmpToken;
	}
	public void setOnHoldEmpToken(String onHoldEmpToken) {
		this.onHoldEmpToken = onHoldEmpToken;
	}
	private String onHoldEmpName="";
	private String onHoldEmpToken="";
	
	
	
	
	public String getIttOnholdEmployee() {
		return ittOnholdEmployee;
	}
	public void setIttOnholdEmployee(String ittOnholdEmployee) {
		this.ittOnholdEmployee = ittOnholdEmployee;
	}
	public String getOnHoldFlag() {
		return onHoldFlag;
	}
	public void setOnHoldFlag(String onHoldFlag) {
		this.onHoldFlag = onHoldFlag;
	}
	public String getTotalOnHoldEmployee() {
		return totalOnHoldEmployee;
	}
	public void setTotalOnHoldEmployee(String totalOnHoldEmployee) {
		this.totalOnHoldEmployee = totalOnHoldEmployee;
	}
	public String getTotalOnHoldFlag() {
		return totalOnHoldFlag;
	}
	public void setTotalOnHoldFlag(String totalOnHoldFlag) {
		this.totalOnHoldFlag = totalOnHoldFlag;
	}
	private String totalOnHoldEmployee="";
	private String totalOnHoldFlag="";
	
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}	

	public String getHEmpType() {
		return hEmpType;
	}
	public void setHEmpType(String empType) {
		hEmpType = empType;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	//ArrayList iteratorList;
	private String totalRecords = "";	
	private String ittMonth="";
	private String ittYear="";
	private String ittBranchId="";
	private String ittBranchName="";
	private String ittDepartmentId="";
	private String ittDepartmentName="";
	private String ittPayBillId="";
	private String ittPayBilName="";
	private String ittEmployeeTypeId="";
	private String ittEmployeeTypeName="";
	private String ittDivisionId="";
	private String ittDivisionName="";
	private String ittStatus="";
	private String ittMonthCode="";
	private String ittLedgerCode="";
	private String ledgerCode="";
	
	/**
	 * FOR STATISTICS
	 * @return
	 */
	private ArrayList statisticsList=null; 
	private String ittBranchNameS;
	private String ittBranchCodeS;
	private String ittTotalEmployee;
	private String ittUploadedEmployee;
	private String ittProcessedEmployee;
	private String ittUnProcessedEmployee;
	private String ittNotIncludedEmployee;
	private String ittTotalNoEmployee="";
	private String totalUploadedEmployee="";
	private String ittResignEmployee="";
	
	private String resignFlag="";
	private String notIncludeFlag="";
	private String processFlag="";
	private String notProcessFlag="";
	private String totalresignFlag="";
	private String totalnotIncludeFlag="";
	private String totalprocessFlag="";
	private String totalnotProcessFlag="";
	private String totaluploadFlag="";
	//private String totalFlag="";
	
	
	
	
	public String getResignFlag() {
		return resignFlag;
	}
	public String getTotalresignFlag() {
		return totalresignFlag;
	}
	public void setTotalresignFlag(String totalresignFlag) {
		this.totalresignFlag = totalresignFlag;
	}
	public String getTotalnotIncludeFlag() {
		return totalnotIncludeFlag;
	}
	public void setTotalnotIncludeFlag(String totalnotIncludeFlag) {
		this.totalnotIncludeFlag = totalnotIncludeFlag;
	}
	public String getTotalprocessFlag() {
		return totalprocessFlag;
	}
	public void setTotalprocessFlag(String totalprocessFlag) {
		this.totalprocessFlag = totalprocessFlag;
	}
	public String getTotalnotProcessFlag() {
		return totalnotProcessFlag;
	}
	public void setTotalnotProcessFlag(String totalnotProcessFlag) {
		this.totalnotProcessFlag = totalnotProcessFlag;
	}
	public String getTotaluploadFlag() {
		return totaluploadFlag;
	}
	public void setTotaluploadFlag(String totaluploadFlag) {
		this.totaluploadFlag = totaluploadFlag;
	}
	public void setResignFlag(String resignFlag) {
		this.resignFlag = resignFlag;
	}
	public String getNotIncludeFlag() {
		return notIncludeFlag;
	}
	public void setNotIncludeFlag(String notIncludeFlag) {
		this.notIncludeFlag = notIncludeFlag;
	}
	public String getProcessFlag() {
		return processFlag;
	}
	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}
	public String getNotProcessFlag() {
		return notProcessFlag;
	}
	public void setNotProcessFlag(String notProcessFlag) {
		this.notProcessFlag = notProcessFlag;
	}
	public String getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public String getTotalFlag() {
		return totalFlag;
	}
	public void setTotalFlag(String totalFlag) {
		this.totalFlag = totalFlag;
	}
	private String uploadFlag="";
	private String totalFlag="";
	
	
	public String getIttResignEmployee() {
		return ittResignEmployee;
	}
	public void setIttResignEmployee(String ittResignEmployee) {
		this.ittResignEmployee = ittResignEmployee;
	}
	public String getTotalResignEmployee() {
		return totalResignEmployee;
	}
	public void setTotalResignEmployee(String totalResignEmployee) {
		this.totalResignEmployee = totalResignEmployee;
	}
	private String totalResignEmployee="";
	
	
	public String getIttNotIncludeEmployee() {
		return ittNotIncludeEmployee;
	}
	public void setIttNotIncludeEmployee(String ittNotIncludeEmployee) {
		this.ittNotIncludeEmployee = ittNotIncludeEmployee;
	}
	public String getTotalNotIncludeEmployee() {
		return totalNotIncludeEmployee;
	}
	public void setTotalNotIncludeEmployee(String totalNotIncludeEmployee) {
		this.totalNotIncludeEmployee = totalNotIncludeEmployee;
	}
	private String totalProcessedEmployee="";
	private String totalUnProcessedEmployee="";
	private String ittNotIncludeEmployee="";
	private String totalNotIncludeEmployee="";
	//private String ittUnProcessedEmployee"";
	/*private String ittLeaveApplication;
	private String ittResignedEmployee;
	private String ittOnHoldEmployee;
	private String ittTotalEmployee;
	private String ittTotalNoEmployee;*/
	
	
	public String getTotalUnProcessedEmployee() {
		return totalUnProcessedEmployee;
	}
	public void setTotalUnProcessedEmployee(String totalUnProcessedEmployee) {
		this.totalUnProcessedEmployee = totalUnProcessedEmployee;
	}
	public String getTotalProcessedEmployee() {
		return totalProcessedEmployee;
	}
	public void setTotalProcessedEmployee(String totalProcessedEmployee) {
		this.totalProcessedEmployee = totalProcessedEmployee;
	}
	public String getTotalUploadedEmployee() {
		return totalUploadedEmployee;
	}
	public void setTotalUploadedEmployee(String totalUploadedEmployee) {
		this.totalUploadedEmployee = totalUploadedEmployee;
	}
	public String getIttTotalNoEmployee() {
		return ittTotalNoEmployee;
	}
	public void setIttTotalNoEmployee(String ittTotalNoEmployee) {
		this.ittTotalNoEmployee = ittTotalNoEmployee;
	}
	public ArrayList getStatisticsList() {
		return statisticsList;
	}
	public void setStatisticsList(ArrayList statisticsList) {
		this.statisticsList = statisticsList;
	}
	public String getIttBranchNameS() {
		return ittBranchNameS;
	}
	public void setIttBranchNameS(String ittBranchNameS) {
		this.ittBranchNameS = ittBranchNameS;
	}
	public String getIttBranchCodeS() {
		return ittBranchCodeS;
	}
	public void setIttBranchCodeS(String ittBranchCodeS) {
		this.ittBranchCodeS = ittBranchCodeS;
	}
	public String getIttTotalEmployee() {
		return ittTotalEmployee;
	}
	public void setIttTotalEmployee(String ittTotalEmployee) {
		this.ittTotalEmployee = ittTotalEmployee;
	}
	public String getIttUploadedEmployee() {
		return ittUploadedEmployee;
	}
	public void setIttUploadedEmployee(String ittUploadedEmployee) {
		this.ittUploadedEmployee = ittUploadedEmployee;
	}
	public String getIttProcessedEmployee() {
		return ittProcessedEmployee;
	}
	public void setIttProcessedEmployee(String ittProcessedEmployee) {
		this.ittProcessedEmployee = ittProcessedEmployee;
	}
	public String getIttUnProcessedEmployee() {
		return ittUnProcessedEmployee;
	}
	public void setIttUnProcessedEmployee(String ittUnProcessedEmployee) {
		this.ittUnProcessedEmployee = ittUnProcessedEmployee;
	}
	public String getIttNotIncludedEmployee() {
		return ittNotIncludedEmployee;
	}
	public void setIttNotIncludedEmployee(String ittNotIncludedEmployee) {
		this.ittNotIncludedEmployee = ittNotIncludedEmployee;
	}
	public String getIttMonthName() {
		return ittMonthName;
	}
	public void setIttMonthName(String ittMonthName) {
		this.ittMonthName = ittMonthName;
	}
	private String ledgerStatus="";
	private String ittMonthName="";
	
	
	public String getLedgerStatus() {
		return ledgerStatus;
	}
	public void setLedgerStatus(String ledgerStatus) {
		this.ledgerStatus = ledgerStatus;
	}
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	public String getIttLedgerCode() {
		return ittLedgerCode;
	}
	public void setIttLedgerCode(String ittLedgerCode) {
		this.ittLedgerCode = ittLedgerCode;
	}
	public String getIttMonthCode() {
		return ittMonthCode;
	}
	public void setIttMonthCode(String ittMonthCode) {
		this.ittMonthCode = ittMonthCode;
	}
	public String getIttStatus() {
		return ittStatus;
	}
	public void setIttStatus(String ittStatus) {
		this.ittStatus = ittStatus;
	}
	private ArrayList arrayList=null;
	
	
	public ArrayList getArrayList() {
		return arrayList;
	}
	public void setArrayList(ArrayList arrayList) {
		this.arrayList = arrayList;
	}
	public String getIttMonth() {
		return ittMonth;
	}
	public void setIttMonth(String ittMonth) {
		this.ittMonth = ittMonth;
	}
	public String getIttYear() {
		return ittYear;
	}
	public void setIttYear(String ittYear) {
		this.ittYear = ittYear;
	}
	public String getIttBranchId() {
		return ittBranchId;
	}
	public void setIttBranchId(String ittBranchId) {
		this.ittBranchId = ittBranchId;
	}
	public String getIttBranchName() {
		return ittBranchName;
	}
	public void setIttBranchName(String ittBranchName) {
		this.ittBranchName = ittBranchName;
	}
	public String getIttDepartmentId() {
		return ittDepartmentId;
	}
	public void setIttDepartmentId(String ittDepartmentId) {
		this.ittDepartmentId = ittDepartmentId;
	}
	public String getIttDepartmentName() {
		return ittDepartmentName;
	}
	public void setIttDepartmentName(String ittDepartmentName) {
		this.ittDepartmentName = ittDepartmentName;
	}
	public String getIttPayBillId() {
		return ittPayBillId;
	}
	public void setIttPayBillId(String ittPayBillId) {
		this.ittPayBillId = ittPayBillId;
	}
	public String getIttPayBilName() {
		return ittPayBilName;
	}
	public void setIttPayBilName(String ittPayBilName) {
		this.ittPayBilName = ittPayBilName;
	}
	public String getIttEmployeeTypeId() {
		return ittEmployeeTypeId;
	}
	public void setIttEmployeeTypeId(String ittEmployeeTypeId) {
		this.ittEmployeeTypeId = ittEmployeeTypeId;
	}
	public String getIttEmployeeTypeName() {
		return ittEmployeeTypeName;
	}
	public void setIttEmployeeTypeName(String ittEmployeeTypeName) {
		this.ittEmployeeTypeName = ittEmployeeTypeName;
	}
	public String getIttDivisionId() {
		return ittDivisionId;
	}
	public void setIttDivisionId(String ittDivisionId) {
		this.ittDivisionId = ittDivisionId;
	}
	public String getIttDivisionName() {
		return ittDivisionName;
	}
	public void setIttDivisionName(String ittDivisionName) {
		this.ittDivisionName = ittDivisionName;
	}
	/**
	 * @return the branchFlag
	 */
	public boolean isBranchFlag() {
		return branchFlag;
	}
	/**
	 * @param branchFlag the branchFlag to set
	 */
	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}
	/**
	 * @return the departmentFlag
	 */
	public boolean isDepartmentFlag() {
		return departmentFlag;
	}
	/**
	 * @param departmentFlag the departmentFlag to set
	 */
	public void setDepartmentFlag(boolean departmentFlag) {
		this.departmentFlag = departmentFlag;
	}
	/**
	 * @return the payBillFlag
	 */
	public boolean isPayBillFlag() {
		return payBillFlag;
	}
	/**
	 * @param payBillFlag the payBillFlag to set
	 */
	public void setPayBillFlag(boolean payBillFlag) {
		this.payBillFlag = payBillFlag;
	}
	/**
	 * @return the employeeTypeFlag
	 */
	public boolean isEmployeeTypeFlag() {
		return employeeTypeFlag;
	}
	/**
	 * @param employeeTypeFlag the employeeTypeFlag to set
	 */
	public void setEmployeeTypeFlag(boolean employeeTypeFlag) {
		this.employeeTypeFlag = employeeTypeFlag;
	}
	/**
	 * @return the divisionFlag
	 */
	public boolean isDivisionFlag() {
		return divisionFlag;
	}
	/**
	 * @param divisionFlag the divisionFlag to set
	 */
	public void setDivisionFlag(boolean divisionFlag) {
		this.divisionFlag = divisionFlag;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the payBillId
	 */
	public String getPayBillId() {
		return payBillId;
	}
	/**
	 * @param payBillId the payBillId to set
	 */
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}
	/**
	 * @return the payBilName
	 */
	public String getPayBilName() {
		return payBilName;
	}
	/**
	 * @param payBilName the payBilName to set
	 */
	public void setPayBilName(String payBilName) {
		this.payBilName = payBilName;
	}
	/**
	 * @return the employeeTypeId
	 */
	public String getEmployeeTypeId() {
		return employeeTypeId;
	}
	/**
	 * @param employeeTypeId the employeeTypeId to set
	 */
	public void setEmployeeTypeId(String employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}
	/**
	 * @return the employeeTypeName
	 */
	public String getEmployeeTypeName() {
		return employeeTypeName;
	}
	/**
	 * @param employeeTypeName the employeeTypeName to set
	 */
	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	/**
	 * @return the divisionId
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @param divisionId the divisionId to set
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/** 
	 * @return String  Branch Code
	 */
	public String getHBranchCode() {
		return hBranchCode;
	}
	/**
	 * @param branchCode to Set Branch Code
	 */
	public void setHBranchCode(String branchCode) {
		hBranchCode = branchCode;
	}
}
