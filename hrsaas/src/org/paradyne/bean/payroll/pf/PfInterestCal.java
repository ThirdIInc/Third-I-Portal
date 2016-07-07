/**
 * 
 */
package org.paradyne.bean.payroll.pf;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Mangesh Jadhav
 *
 */
public class PfInterestCal extends BeanBase {
	
	private String empToken;
	private String empName;
	private String empId;
	private String empStatus;
	private String empPfNo;
	private String divName;
	private String branchName;
	private String deptName;
	private String desgName;
	private String fromMonth;
	private String fromYear;
	private String toMonth;
	private String toYear;
	private String openingBalance;
	
	ArrayList monthList=null;
	private String listPfSub;
	private String listMonthName;
	private String listPfRefund;
	private String listPfMPCont;
	private String listPfLoan;
	private String listPfRepay;
	private String listPfProg;
	
	private String totPfSub;
	private String totMonthName;
	private String totPfRefund;
	private String totPfMPCont;
	private String totPfLoan;
	private String totPfRepay;
	private String totPfProg;
	private String totPfProgActual;
	private String intRate;
	private String intAmount;
	private String totDeposite;
	private String totWithdraw;
	private String closingBalance;
	private String calcFlag="false";
	private String filterFlag="false";
	private String calcType;
	
	/*
	 * 
	 */
	private String applDivisionCode;
	private String applDivisionName;
	private String applBranchCode;
	private String applBranchName;
	private String applDeptCode;
	private String applDeptName;
	private String applDesgCode;
	private String applDesgName;
	private String applETypeCode;
	private String applETypeName;
	private String applGradeCode;
	private String applGradeName;
	private String applEmpCode;
	private String applEmpName;
	
	/*
	 * 
	 */
	private String empTokenList;
	private String empNameList ;
	private String empCodeList ;
	private String opnBalanceList ;
	private String depositeAmtList;
	private String withdrawAmtList;
	private String intAmtList;
	private String clsBalanceList;
	ArrayList empList=null;
	
	private String myPage;
	
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getApplDivisionCode() {
		return applDivisionCode;
	}
	public void setApplDivisionCode(String applDivisionCode) {
		this.applDivisionCode = applDivisionCode;
	}
	public String getApplDivisionName() {
		return applDivisionName;
	}
	public void setApplDivisionName(String applDivisionName) {
		this.applDivisionName = applDivisionName;
	}
	public String getApplBranchCode() {
		return applBranchCode;
	}
	public void setApplBranchCode(String applBranchCode) {
		this.applBranchCode = applBranchCode;
	}
	public String getApplBranchName() {
		return applBranchName;
	}
	public void setApplBranchName(String applBranchName) {
		this.applBranchName = applBranchName;
	}
	public String getApplDeptCode() {
		return applDeptCode;
	}
	public void setApplDeptCode(String applDeptCode) {
		this.applDeptCode = applDeptCode;
	}
	public String getApplDeptName() {
		return applDeptName;
	}
	public void setApplDeptName(String applDeptName) {
		this.applDeptName = applDeptName;
	}
	public String getApplDesgCode() {
		return applDesgCode;
	}
	public void setApplDesgCode(String applDesgCode) {
		this.applDesgCode = applDesgCode;
	}
	public String getApplDesgName() {
		return applDesgName;
	}
	public void setApplDesgName(String applDesgName) {
		this.applDesgName = applDesgName;
	}
	public String getApplETypeCode() {
		return applETypeCode;
	}
	public void setApplETypeCode(String applETypeCode) {
		this.applETypeCode = applETypeCode;
	}
	public String getApplETypeName() {
		return applETypeName;
	}
	public void setApplETypeName(String applETypeName) {
		this.applETypeName = applETypeName;
	}
	public String getApplGradeCode() {
		return applGradeCode;
	}
	public void setApplGradeCode(String applGradeCode) {
		this.applGradeCode = applGradeCode;
	}
	public String getApplGradeName() {
		return applGradeName;
	}
	public void setApplGradeName(String applGradeName) {
		this.applGradeName = applGradeName;
	}
	public String getApplEmpCode() {
		return applEmpCode;
	}
	public void setApplEmpCode(String applEmpCode) {
		this.applEmpCode = applEmpCode;
	}
	public String getApplEmpName() {
		return applEmpName;
	}
	public void setApplEmpName(String applEmpName) {
		this.applEmpName = applEmpName;
	}
	public String getListPfProg() {
		return listPfProg;
	}
	public void setListPfProg(String listPfProg) {
		this.listPfProg = listPfProg;
	}
	public ArrayList getMonthList() {
		return monthList;
	}
	public void setMonthList(ArrayList monthList) {
		this.monthList = monthList;
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
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
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getEmpPfNo() {
		return empPfNo;
	}
	public void setEmpPfNo(String empPfNo) {
		this.empPfNo = empPfNo;
	}
	public String getListPfSub() {
		return listPfSub;
	}
	public void setListPfSub(String listPfSub) {
		this.listPfSub = listPfSub;
	}
	public String getListMonthName() {
		return listMonthName;
	}
	public void setListMonthName(String listMonthName) {
		this.listMonthName = listMonthName;
	}
	public String getListPfRefund() {
		return listPfRefund;
	}
	public void setListPfRefund(String listPfRefund) {
		this.listPfRefund = listPfRefund;
	}
	public String getListPfMPCont() {
		return listPfMPCont;
	}
	public void setListPfMPCont(String listPfMPCont) {
		this.listPfMPCont = listPfMPCont;
	}
	public String getListPfLoan() {
		return listPfLoan;
	}
	public void setListPfLoan(String listPfLoan) {
		this.listPfLoan = listPfLoan;
	}
	public String getListPfRepay() {
		return listPfRepay;
	}
	public void setListPfRepay(String listPfRepay) {
		this.listPfRepay = listPfRepay;
	}
	public String getTotPfSub() {
		return totPfSub;
	}
	public void setTotPfSub(String totPfSub) {
		this.totPfSub = totPfSub;
	}
	public String getTotMonthName() {
		return totMonthName;
	}
	public void setTotMonthName(String totMonthName) {
		this.totMonthName = totMonthName;
	}
	public String getTotPfRefund() {
		return totPfRefund;
	}
	public void setTotPfRefund(String totPfRefund) {
		this.totPfRefund = totPfRefund;
	}
	public String getTotPfMPCont() {
		return totPfMPCont;
	}
	public void setTotPfMPCont(String totPfMPCont) {
		this.totPfMPCont = totPfMPCont;
	}
	public String getTotPfLoan() {
		return totPfLoan;
	}
	public void setTotPfLoan(String totPfLoan) {
		this.totPfLoan = totPfLoan;
	}
	public String getTotPfRepay() {
		return totPfRepay;
	}
	public void setTotPfRepay(String totPfRepay) {
		this.totPfRepay = totPfRepay;
	}
	public String getTotPfProg() {
		return totPfProg;
	}
	public void setTotPfProg(String totPfProg) {
		this.totPfProg = totPfProg;
	}
	public String getCalcFlag() {
		return calcFlag;
	}
	public void setCalcFlag(String calcFlag) {
		this.calcFlag = calcFlag;
	}
	public String getTotPfProgActual() {
		return totPfProgActual;
	}
	public void setTotPfProgActual(String totPfProgActual) {
		this.totPfProgActual = totPfProgActual;
	}
	public String getIntRate() {
		return intRate;
	}
	public void setIntRate(String intRate) {
		this.intRate = intRate;
	}
	public String getTotDeposite() {
		return totDeposite;
	}
	public void setTotDeposite(String totDeposite) {
		this.totDeposite = totDeposite;
	}
	public String getTotWithdraw() {
		return totWithdraw;
	}
	public void setTotWithdraw(String totWithdraw) {
		this.totWithdraw = totWithdraw;
	}
	public String getIntAmount() {
		return intAmount;
	}
	public void setIntAmount(String intAmount) {
		this.intAmount = intAmount;
	}
	public String getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(String closingBalance) {
		this.closingBalance = closingBalance;
	}
	public String getEmpTokenList() {
		return empTokenList;
	}
	public void setEmpTokenList(String empTokenList) {
		this.empTokenList = empTokenList;
	}
	public String getEmpNameList() {
		return empNameList;
	}
	public void setEmpNameList(String empNameList) {
		this.empNameList = empNameList;
	}
	public String getEmpCodeList() {
		return empCodeList;
	}
	public void setEmpCodeList(String empCodeList) {
		this.empCodeList = empCodeList;
	}
	public String getOpnBalanceList() {
		return opnBalanceList;
	}
	public void setOpnBalanceList(String opnBalanceList) {
		this.opnBalanceList = opnBalanceList;
	}
	public String getDepositeAmtList() {
		return depositeAmtList;
	}
	public void setDepositeAmtList(String depositeAmtList) {
		this.depositeAmtList = depositeAmtList;
	}
	public String getWithdrawAmtList() {
		return withdrawAmtList;
	}
	public void setWithdrawAmtList(String withdrawAmtList) {
		this.withdrawAmtList = withdrawAmtList;
	}
	public String getIntAmtList() {
		return intAmtList;
	}
	public void setIntAmtList(String intAmtList) {
		this.intAmtList = intAmtList;
	}
	public String getClsBalanceList() {
		return clsBalanceList;
	}
	public void setClsBalanceList(String clsBalanceList) {
		this.clsBalanceList = clsBalanceList;
	}
	public ArrayList getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}
	public String getFilterFlag() {
		return filterFlag;
	}
	public void setFilterFlag(String filterFlag) {
		this.filterFlag = filterFlag;
	}
	public String getCalcType() {
		return calcType;
	}
	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}
	
	
	
}
