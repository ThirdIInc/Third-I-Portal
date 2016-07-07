package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/*
 * Author:Pradeep and Venkatesh and Prakash
 */
public class SalaryRegister  extends BeanBase implements Serializable {		
	
	private String costcentername="";
	private String costcenterid="";
	private String subcostcentername="";
	private String subcostcenterid="";
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
	private String attCode="";
	private String typeCode="";
	private String typeName="";
	private String deptCode="";
	private String deptName="";
	private String branchCode=""; 
	private String branchName="";
	private String radioChk="";
	private String report="";
	private String onHold=""; 
	private String desgCode="";
	private String desgName="";
	private String divCode="";
	private String divisionAbbrevation="";
	private String divName="";
	private String empGradeId="";
	private String empGradeName="";
	private String checkFlag="N";
	private String checkBrn="N";
	private String checkDept="N";
	private String checkDob="N";
	private String checkBank="N";
	private String checkEmpType="N";
	private String checkAccount="N";
	private String checkPan="N";
	private String checkDoj="N";
	private String checkDesg="N";
	private String checkGender="N";
	private String chkBrnDept="N";
	private String checkHold="N";
	private String checkGrade="N";
	private String chkBrnOrder="N";
	private String chkDeptOrder="N";
	private String chkDesgOrder="N";
	private String paybillCheck="N";
	
	//ADDED BY REEBA BEGINS
	private String chkConsSummary="N";
	private String checkEmployerPF="N";
	private String checkEmployerESIC="N";
	//ADDED BY REEBA ENDS
	
	private String checkCostCenter="N";
	private String checkSubCostCenter="N";
	
	private String rowNumber;
	private String columnNumber;
	
	private ArrayList<NonIndustrialSalary> creditHeader=null;
	private ArrayList<NonIndustrialSalary> debitHeader=null;
	
	private String ledgerCode;
	private String arrLedgerCode;
	
	private Object[][] totalSalCre;
	private Object[][] totalSalDeb;
	private Object[][] debitHead;
	private Object[][] creditHead;
	//Added By Ganesh BEGINS
	private String checkEmpGrade= "N";
	//Added By Ganesh Ends
	private String paybillId = "";
	private String paybillName = "";
	public String linkSource="";
	public String monthView="";
	public String getLinkSource() {
		return linkSource;
	}
	public void setLinkSource(String linkSource) {
		this.linkSource = linkSource;
	}
	public String getMonthView() {
		return monthView;
	}
	public void setMonthView(String monthView) {
		this.monthView = monthView;
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
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
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
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
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
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
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
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public ArrayList<NonIndustrialSalary> getCreditHeader() {
		return creditHeader;
	}
	public void setCreditHeader(ArrayList<NonIndustrialSalary> creditHeader) {
		this.creditHeader = creditHeader;
	}
	public ArrayList<NonIndustrialSalary> getDebitHeader() {
		return debitHeader;
	}
	public void setDebitHeader(ArrayList<NonIndustrialSalary> debitHeader) {
		this.debitHeader = debitHeader;
	}
	public String getAttCode() {
		return attCode;
	}
	public void setAttCode(String attCode) {
		this.attCode = attCode;
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
	public String getRadioChk() {
		return radioChk;
	}
	public void setRadioChk(String radioChk) {
		this.radioChk = radioChk;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
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
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getCheckBrn() {
		return checkBrn;
	}
	public void setCheckBrn(String checkBrn) {
		this.checkBrn = checkBrn;
	}
	public String getCheckDept() {
		return checkDept;
	}
	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}
	public String getCheckDob() {
		return checkDob;
	}
	public void setCheckDob(String checkDob) {
		this.checkDob = checkDob;
	}
	public String getCheckBank() {
		return checkBank;
	}
	public void setCheckBank(String checkBank) {
		this.checkBank = checkBank;
	}
	public String getCheckEmpType() {
		return checkEmpType;
	}
	public void setCheckEmpType(String checkEmpType) {
		this.checkEmpType = checkEmpType;
	}
	public String getCheckAccount() {
		return checkAccount;
	}
	public void setCheckAccount(String checkAccount) {
		this.checkAccount = checkAccount;
	}
	public String getCheckPan() {
		return checkPan;
	}
	public void setCheckPan(String checkPan) {
		this.checkPan = checkPan;
	}
	public String getCheckDoj() {
		return checkDoj;
	}
	public void setCheckDoj(String checkDoj) {
		this.checkDoj = checkDoj;
	}
	public String getCheckDesg() {
		return checkDesg;
	}
	public void setCheckDesg(String checkDesg) {
		this.checkDesg = checkDesg;
	}
	public String getCheckGender() {
		return checkGender;
	}
	public void setCheckGender(String checkGender) {
		this.checkGender = checkGender;
	}
	public String getChkBrnDept() {
		return chkBrnDept;
	}
	public void setChkBrnDept(String chkBrnDept) {
		this.chkBrnDept = chkBrnDept;
	}
	public String getCheckHold() {
		return checkHold;
	}
	public void setCheckHold(String checkHold) {
		this.checkHold = checkHold;
	}
	public String getCheckGrade() {
		return checkGrade;
	}
	public void setCheckGrade(String checkGrade) {
		this.checkGrade = checkGrade;
	}
	public String getChkBrnOrder() {
		return chkBrnOrder;
	}
	public void setChkBrnOrder(String chkBrnOrder) {
		this.chkBrnOrder = chkBrnOrder;
	}
	public String getChkDeptOrder() {
		return chkDeptOrder;
	}
	public void setChkDeptOrder(String chkDeptOrder) {
		this.chkDeptOrder = chkDeptOrder;
	}
	public String getChkDesgOrder() {
		return chkDesgOrder;
	}
	public void setChkDesgOrder(String chkDesgOrder) {
		this.chkDesgOrder = chkDesgOrder;
	}
	public String getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(String columnNumber) {
		this.columnNumber = columnNumber;
	}
	public Object[][] getDebitHead() {
		return debitHead;
	}
	public void setDebitHead(Object[][] debitHead) {
		this.debitHead = debitHead;
	}
	public Object[][] getCreditHead() {
		return creditHead;
	}
	public void setCreditHead(Object[][] creditHead) {
		this.creditHead = creditHead;
	}
	public Object[][] getTotalSalCre() {
		return totalSalCre;
	}
	public void setTotalSalCre(Object[][] totalSalCre) {
		this.totalSalCre = totalSalCre;
	}
	public Object[][] getTotalSalDeb() {
		return totalSalDeb;
	}
	public void setTotalSalDeb(Object[][] totalSalDeb) {
		this.totalSalDeb = totalSalDeb;
	}
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	public String getArrLedgerCode() {
		return arrLedgerCode;
	}
	public void setArrLedgerCode(String arrLedgerCode) {
		this.arrLedgerCode = arrLedgerCode;
	}
	public String getChkConsSummary() {
		return chkConsSummary;
	}
	public void setChkConsSummary(String chkConsSummary) {
		this.chkConsSummary = chkConsSummary;
	}
	public String getCheckEmployerPF() {
		return checkEmployerPF;
	}
	public void setCheckEmployerPF(String checkEmployerPF) {
		this.checkEmployerPF = checkEmployerPF;
	}
	public String getCheckEmployerESIC() {
		return checkEmployerESIC;
	}
	public void setCheckEmployerESIC(String checkEmployerESIC) {
		this.checkEmployerESIC = checkEmployerESIC;
	}
	public String getCheckEmpGrade() {
		return checkEmpGrade;
	}
	public void setCheckEmpGrade(String checkEmpGrade) {
		this.checkEmpGrade = checkEmpGrade;
	}
	public String getPaybillId() {
		return paybillId;
	}
	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}
	public String getPaybillName() {
		return paybillName;
	}
	public void setPaybillName(String paybillName) {
		this.paybillName = paybillName;
	}
	public String getPaybillCheck() {
		return paybillCheck;
	}
	public void setPaybillCheck(String paybillCheck) {
		this.paybillCheck = paybillCheck;
	}
	public String getDivisionAbbrevation() {
		return divisionAbbrevation;
	}
	public void setDivisionAbbrevation(String divisionAbbrevation) {
		this.divisionAbbrevation = divisionAbbrevation;
	}
	public String getCostcentername() {
		return costcentername;
	}
	public void setCostcentername(String costcentername) {
		this.costcentername = costcentername;
	}
	public String getCostcenterid() {
		return costcenterid;
	}
	public void setCostcenterid(String costcenterid) {
		this.costcenterid = costcenterid;
	}
	public String getSubcostcentername() {
		return subcostcentername;
	}
	public void setSubcostcentername(String subcostcentername) {
		this.subcostcentername = subcostcentername;
	}
	public String getSubcostcenterid() {
		return subcostcenterid;
	}
	public void setSubcostcenterid(String subcostcenterid) {
		this.subcostcenterid = subcostcenterid;
	}
	public String getCheckCostCenter() {
		return checkCostCenter;
	}
	public void setCheckCostCenter(String checkCostCenter) {
		this.checkCostCenter = checkCostCenter;
	}
	public String getCheckSubCostCenter() {
		return checkSubCostCenter;
	}
	public void setCheckSubCostCenter(String checkSubCostCenter) {
		this.checkSubCostCenter = checkSubCostCenter;
	}
	public String getEmpGradeId() {
		return empGradeId;
	}
	public void setEmpGradeId(String empGradeId) {
		this.empGradeId = empGradeId;
	}
	public String getEmpGradeName() {
		return empGradeName;
	}
	public void setEmpGradeName(String empGradeName) {
		this.empGradeName = empGradeName;
	}

}
