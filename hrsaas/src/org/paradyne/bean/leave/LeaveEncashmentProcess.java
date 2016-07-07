 package org.paradyne.bean.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LeaveEncashmentProcess extends BeanBase {
	private String divCode ="";
	private String divName ="";
	private String deptCode ="";
	private String deptName ="";
	private String branchCode ="";
	private String branchName ="";
	private String empTypeCode ="";
	private String empTypeName ="";
	private String levCode ="";
	private String levType ="";
	private String salaryCheck ="";
	private String salarymonth ="";
	private String salaryyear ="";
	private String empToken ="";
	private String employeeId ="";
	private String empName ="";
	private String leaveName ="";
	private String leaveCode ="";
	private String availableBal ="";
	private String noOfencashLeave ="";
	private ArrayList  list = null ;
	private String greaterEncashBal="";
	private String encashFormula="";
	private String encashAmount= "";
	private String amtPerDay="";
	private String processCode="";
	private String processingDate="";
	private String employeeCode ="";
	private String employeeName ="";
	private String employeeToken ="";
	private String lockFlag="";
	private String  showFlag="false";
	private String listFlag="false";
	private String myPage;
	private String modeLength="false";
	private String noData="false";
	private String processCodeItt ="";
	private String divisionItt ="";
	private String processDateItt ="";
	private String statusItt ="";
	private String totalRecords ="";
	private ArrayList tableList =null;
	private String hiddencode="";
	private String confChk ="";
	private String hdeleteCode ="";
	private String  srNo="";
	private String addFlag="true";
	private String hiddenEdit ="";
	private String hiddenEncashDays ="";
	private String isAddFlag = "false";
	private String addFlagItt="N";
	private String  imageFlag="true";
	private String currentBal ="";
	private String  creditCode ="";
	private String  creditType ="";
	
	private String payBillNo = "";
	private String payBillName = "";
	private String cadreCode = "";
	private String cadreName = "";
	private String costCenterCode = "";
	private String costCenterName = "";
	private String deductIncomeTax = "";
	
	private String tds = "";
	private String netAmount = "";
	private String monthView = "";
	private String totalEncashAmt = "";
	private String headcount = "";
	private String salmonthyear = "";
	private String empGender = "";
	
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	public String getCurrentBal() {
		return currentBal;
	}
	public void setCurrentBal(String currentBal) {
		this.currentBal = currentBal;
	}
	public String getAddFlagItt() {
		return addFlagItt;
	}
	public void setAddFlagItt(String addFlagItt) {
		this.addFlagItt = addFlagItt;
	}
	public String getIsAddFlag() {
		return isAddFlag;
	}
	public void setIsAddFlag(String isAddFlag) {
		this.isAddFlag = isAddFlag;
	}
	public String getHiddenEncashDays() {
		return hiddenEncashDays;
	}
	public void setHiddenEncashDays(String hiddenEncashDays) {
		this.hiddenEncashDays = hiddenEncashDays;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getProcessCodeItt() {
		return processCodeItt;
	}
	public void setProcessCodeItt(String processCodeItt) {
		this.processCodeItt = processCodeItt;
	}
	public String getDivisionItt() {
		return divisionItt;
	}
	public void setDivisionItt(String divisionItt) {
		this.divisionItt = divisionItt;
	}
	public String getProcessDateItt() {
		return processDateItt;
	}
	public void setProcessDateItt(String processDateItt) {
		this.processDateItt = processDateItt;
	}
	public String getStatusItt() {
		return statusItt;
	}
	public void setStatusItt(String statusItt) {
		this.statusItt = statusItt;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public ArrayList getTableList() {
		return tableList;
	}
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getProcessingDate() {
		return processingDate;
	}
	public void setProcessingDate(String processingDate) {
		this.processingDate = processingDate;
	}
	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getAmtPerDay() {
		return amtPerDay;
	}
	public void setAmtPerDay(String amtPerDay) {
		this.amtPerDay = amtPerDay;
	}
	public String getEncashAmount() {
		return encashAmount;
	}
	public void setEncashAmount(String encashAmount) {
		this.encashAmount = encashAmount;
	}
	public String getEncashFormula() {
		return encashFormula;
	}
	public void setEncashFormula(String encashFormula) {
		this.encashFormula = encashFormula;
	}
	public String getGreaterEncashBal() {
		return greaterEncashBal;
	}
	public void setGreaterEncashBal(String greaterEncashBal) {
		this.greaterEncashBal = greaterEncashBal;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getLeaveCode() {
		return leaveCode;
	}
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}
	 
	public String getAvailableBal() {
		return availableBal;
	}
	public void setAvailableBal(String availableBal) {
		this.availableBal = availableBal;
	}
	public String getNoOfencashLeave() {
		return noOfencashLeave;
	}
	public void setNoOfencashLeave(String noOfencashLeave) {
		this.noOfencashLeave = noOfencashLeave;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
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
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	 
	public String getLevCode() {
		return levCode;
	}
	public void setLevCode(String levCode) {
		this.levCode = levCode;
	}
	public String getLevType() {
		return levType;
	}
	public void setLevType(String levType) {
		this.levType = levType;
	}
	public String getSalaryCheck() {
		return salaryCheck;
	}
	public void setSalaryCheck(String salaryCheck) {
		this.salaryCheck = salaryCheck;
	}
	public String getSalarymonth() {
		return salarymonth;
	}
	public void setSalarymonth(String salarymonth) {
		this.salarymonth = salarymonth;
	}
	public String getSalaryyear() {
		return salaryyear;
	}
	public void setSalaryyear(String salaryyear) {
		this.salaryyear = salaryyear;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getListFlag() {
		return listFlag;
	}
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getImageFlag() {
		return imageFlag;
	}
	public void setImageFlag(String imageFlag) {
		this.imageFlag = imageFlag;
	}
	/**
	 * @return the payBillNo
	 */
	public String getPayBillNo() {
		return payBillNo;
	}
	/**
	 * @param payBillNo the payBillNo to set
	 */
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}
	/**
	 * @param payBillName the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	/**
	 * @return the cadreCode
	 */
	public String getCadreCode() {
		return cadreCode;
	}
	/**
	 * @param cadreCode the cadreCode to set
	 */
	public void setCadreCode(String cadreCode) {
		this.cadreCode = cadreCode;
	}
	/**
	 * @return the cadreName
	 */
	public String getCadreName() {
		return cadreName;
	}
	/**
	 * @param cadreName the cadreName to set
	 */
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	/**
	 * @return the costCenterCode
	 */
	public String getCostCenterCode() {
		return costCenterCode;
	}
	/**
	 * @param costCenterCode the costCenterCode to set
	 */
	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}
	/**
	 * @return the costCenterName
	 */
	public String getCostCenterName() {
		return costCenterName;
	}
	/**
	 * @param costCenterName the costCenterName to set
	 */
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	/**
	 * @return the deductIncomeTax
	 */
	public String getDeductIncomeTax() {
		return deductIncomeTax;
	}
	/**
	 * @param deductIncomeTax the deductIncomeTax to set
	 */
	public void setDeductIncomeTax(String deductIncomeTax) {
		this.deductIncomeTax = deductIncomeTax;
	}
	/**
	 * @return the tds
	 */
	public String getTds() {
		return tds;
	}
	/**
	 * @param tds the tds to set
	 */
	public void setTds(String tds) {
		this.tds = tds;
	}
	/**
	 * @return the netAmount
	 */
	public String getNetAmount() {
		return netAmount;
	}
	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	/**
	 * @return the monthView
	 */
	public String getMonthView() {
		return monthView;
	}
	/**
	 * @param monthView the monthView to set
	 */
	public void setMonthView(String monthView) {
		this.monthView = monthView;
	}
	/**
	 * @return the totalEncashAmt
	 */
	public String getTotalEncashAmt() {
		return totalEncashAmt;
	}
	/**
	 * @param totalEncashAmt the totalEncashAmt to set
	 */
	public void setTotalEncashAmt(String totalEncashAmt) {
		this.totalEncashAmt = totalEncashAmt;
	}
	/**
	 * @return the headcount
	 */
	public String getHeadcount() {
		return headcount;
	}
	/**
	 * @param headcount the headcount to set
	 */
	public void setHeadcount(String headcount) {
		this.headcount = headcount;
	}
	/**
	 * @return the salmonthyear
	 */
	public String getSalmonthyear() {
		return salmonthyear;
	}
	/**
	 * @param salmonthyear the salmonthyear to set
	 */
	public void setSalmonthyear(String salmonthyear) {
		this.salmonthyear = salmonthyear;
	}
	/**
	 * @return the empGender
	 */
	public String getEmpGender() {
		return empGender;
	}
	/**
	 * @param empGender the empGender to set
	 */
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	
}
