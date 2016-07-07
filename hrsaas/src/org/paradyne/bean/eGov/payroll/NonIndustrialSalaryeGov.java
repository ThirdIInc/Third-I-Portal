package org.paradyne.bean.eGov.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class NonIndustrialSalaryeGov extends BeanBase implements Serializable {			
	
	private String typeCode="";
	private String typeName="";
	private String month="";
	private String year="";
	private String payBillNo="";
	private String creditCode="";
	private String creditName="";
	private String creditAmount="";
	private String debitCode="";
	private String debitName="";
	private String debitAmount="";
	private String empId="";
	private String empName="";
	private String empToken="";
	private String payName="";
	private String emptypeFlag="";
	private String paybillFlag="";
	private String branchFlag="";
	private String departmentFlag="";
	private String divisionFlag="";
	private String payBillName="";
	private String frmDate="";
	private String toDate="";
	
	
	private String branchCode=""; 
	private String branchName="";
	private String deptCode="";
	private String deptName="";
	private String divisionCode="";
	private String divisionName="";
	private String chkProFlag=""; 
	private String fromReCal="";
	private String ledgerStatus="";
	private String empSearchToken="";
	private String empSearchId="";
	private String empSearchName="";
	private String empSearchFlag="";
	private String searchFlagRes = ""; 
	private String recordsPerPage="";
	private String joinDaysFlag="";
	private String vpfFlag="";
		
	private ArrayList<NonIndustrialSalaryeGov> creditHeader=null;
	private ArrayList<NonIndustrialSalaryeGov> debitHeader=null;
	private ArrayList<ArrayList> empInfo=null;
	private Object[][]  credit=null;
	private String creditLength="";
	private Object[][] ptaxLoc=null;
	
	private String attCode="";
	private String salDays="";
	private String status="";
	private Object[][] grossCredit=null;
	private String hdPage;
	private String hdProcess;
	private String fromTotRec="";
	private String toTotRec="";
	private String cashCode="";
	private String recoveryFlag="N";
	private String profHandiFLag="N";
	private String incomeTaxFlag="N";
	
	private String lwfFlag="N";
	private String lwfDebitCode="";
	private String lwfCreditCode="";
	
	private String creditRound="";
	private String totalCreditRound="";
	private String totalDebitRound="";
	private String netPayRound="";
		
	
	public String getLwfCreditCode() {
		return lwfCreditCode;
	}
	public void setLwfCreditCode(String lwfCreditCode) {
		this.lwfCreditCode = lwfCreditCode;
	}
	public String getLwfDebitCode() {
		return lwfDebitCode;
	}
	public void setLwfDebitCode(String lwfDebitCode) {
		this.lwfDebitCode = lwfDebitCode;
	}
	public String getIncomeTaxFlag() {
		return incomeTaxFlag;
	}
	public void setIncomeTaxFlag(String incomeTaxFlag) {
		this.incomeTaxFlag = incomeTaxFlag;
	}
	public String getCashCode() {
		return cashCode;
	}
	public void setCashCode(String cashCode) {
		this.cashCode = cashCode;
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
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}
	public String getHdProcess() {
		return hdProcess;
	}
	public void setHdProcess(String hdProcess) {
		this.hdProcess = hdProcess;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public Object[][] getGrossCredit() {
		return grossCredit;
	}
	public void setGrossCredit(Object[][] grossCredit) {
		this.grossCredit = grossCredit;
	}
	public String getSalDays() {
		return salDays;
	}
	public void setSalDays(String salDays) {
		this.salDays = salDays;
	}
	
	public Object[][] getPtaxLoc() {
		return ptaxLoc;
	}
	public void setPtaxLoc(Object[][] ptaxLoc) {
		this.ptaxLoc = ptaxLoc;
	}
	public ArrayList<ArrayList> getEmpInfo() {
		return empInfo;
	}
	public void setEmpInfo(ArrayList<ArrayList> empInfo) {
		this.empInfo = empInfo;
	}
	public String getCreditLength() {
		return creditLength;
	}
	public void setCreditLength(String creditLength) {
		this.creditLength = creditLength;
	}
	public Object[][] getCredit() {
		return credit;
	}
	public void setCredit(Object[][] credit) {
		this.credit = credit;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public ArrayList<NonIndustrialSalaryeGov> getDebitHeader() {
		return debitHeader;
	}
	public void setDebitHeader(ArrayList<NonIndustrialSalaryeGov> debitHeader) {
		this.debitHeader = debitHeader;
	}
	public ArrayList<NonIndustrialSalaryeGov> getCreditHeader() {
		return creditHeader;
	}
	public void setCreditHeader(ArrayList<NonIndustrialSalaryeGov> creditHeader) {
		this.creditHeader = creditHeader;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getDebitCode() {
		return debitCode;
	}
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
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
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	
	public String getAttCode() {
		return attCode;
	}
	public void setAttCode(String attCode) {
		this.attCode = attCode;
	}
	public String getEmptypeFlag() {
		return emptypeFlag;
	}
	public void setEmptypeFlag(String emptypeFlag) {
		this.emptypeFlag = emptypeFlag;
	}
	public String getPaybillFlag() {
		return paybillFlag;
	}
	public void setPaybillFlag(String paybillFlag) {
		this.paybillFlag = paybillFlag;
	}
	public String getBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
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
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayBillName() {
		return payBillName;
	}
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	public String getChkProFlag() {
		return chkProFlag;
	}
	public void setChkProFlag(String chkProFlag) {
		this.chkProFlag = chkProFlag;
	}
	public String getFromReCal() {
		return fromReCal;
	}
	public void setFromReCal(String fromReCal) {
		this.fromReCal = fromReCal;
	}
	
	public String getLedgerStatus() {
		return ledgerStatus;
	}
	public void setLedgerStatus(String ledgerStatus) {
		this.ledgerStatus = ledgerStatus;
	}
	public String getEmpSearchToken() {
		return empSearchToken;
	}
	public void setEmpSearchToken(String empSearchToken) {
		this.empSearchToken = empSearchToken;
	}
	public String getEmpSearchId() {
		return empSearchId;
	}
	public void setEmpSearchId(String empSearchId) {
		this.empSearchId = empSearchId;
	}
	public String getEmpSearchName() {
		return empSearchName;
	}
	public void setEmpSearchName(String empSearchName) {
		this.empSearchName = empSearchName;
	}
	public String getEmpSearchFlag() {
		return empSearchFlag;
	}
	public void setEmpSearchFlag(String empSearchFlag) {
		this.empSearchFlag = empSearchFlag;
	}
	public String getSearchFlagRes() {
		return searchFlagRes;
	}
	public void setSearchFlagRes(String searchFlagRes) {
		this.searchFlagRes = searchFlagRes;
	}
	public String getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(String recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public String getJoinDaysFlag() {
		return joinDaysFlag;
	}
	public void setJoinDaysFlag(String joinDaysFlag) {
		this.joinDaysFlag = joinDaysFlag;
	}
	public String getRecoveryFlag() {
		return recoveryFlag;
	}
	public void setRecoveryFlag(String recoveryFlag) {
		this.recoveryFlag = recoveryFlag;
	}
	public String getProfHandiFLag() {
		return profHandiFLag;
	}
	public void setProfHandiFLag(String profHandiFLag) {
		this.profHandiFLag = profHandiFLag;
	}
	public String getLwfFlag() {
		return lwfFlag;
	}
	public void setLwfFlag(String lwfFlag) {
		this.lwfFlag = lwfFlag;
	}
	public String getVpfFlag() {
		return vpfFlag;
	}
	public void setVpfFlag(String vpfFlag) {
		this.vpfFlag = vpfFlag;
	}
	public String getCreditRound() {
		return creditRound;
	}
	public void setCreditRound(String creditRound) {
		this.creditRound = creditRound;
	}
	public String getTotalCreditRound() {
		return totalCreditRound;
	}
	public void setTotalCreditRound(String totalCreditRound) {
		this.totalCreditRound = totalCreditRound;
	}
	public String getTotalDebitRound() {
		return totalDebitRound;
	}
	public void setTotalDebitRound(String totalDebitRound) {
		this.totalDebitRound = totalDebitRound;
	}
	public String getNetPayRound() {
		return netPayRound;
	}
	public void setNetPayRound(String netPayRound) {
		this.netPayRound = netPayRound;
	}
	
}
