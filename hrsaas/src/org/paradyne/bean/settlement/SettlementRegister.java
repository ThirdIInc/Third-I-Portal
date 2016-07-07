package org.paradyne.bean.settlement;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.bean.payroll.NonIndustrialSalary;
import org.paradyne.lib.BeanBase;

public class SettlementRegister extends BeanBase implements Serializable{		
	
	
	public String fromMonth="";
	public String fromYear="";
	public String month="";
	public String year="";
	public String toMonth="";
	public String toYear="";
	public String payBillNo="";
	public String creditCode="";
	public String creditName="";
	public String creditAmount="";
	public String debitCode="";
	public String debitName="";
	public String debitAmount="";
	public String empId="";
	public String empName="";
	public String empToken="";
	public String payName="";
	public String attCode="";
	public String typeCode="";
	public String typeName="";
	public String deptCode="";
	public String deptName="";
	public String branchCode=""; 
	public String branchName="";
	public String radioChk="";
	public String report="";
	public String onHold=""; 
	public String desgCode="";
	public String desgName="";
	public String divCode="";
	public String divName="";
	public String checkFlag="N";
	public String checkBrn="N";
	public String checkDept="N";
	public String checkDob="N";
	public String checkBank="N";
	public String checkEmpType="N";
	public String checkAccount="N";
	public String checkPan="N";
	public String checkDoj="N";
	public String checkDesg="N";
	public String checkGender="N";
	public String chkBrnDept="N";
	public String checkHold="N";
	public String checkGrade="N";
	public String chkBrnOrder="N";
	public String chkDeptOrder="N";
	public String chkDesgOrder="N";
	
	//ADDED BY REEBA BEGINS
	public String chkConsSummary="N";
	public String checkEmployerPF="N";
	public String checkEmployerESIC="N";
	//ADDED BY REEBA ENDS
	
	public String rowNumber;
	public String columnNumber;
	
	public ArrayList<NonIndustrialSalary> creditHeader=null;
	public ArrayList<NonIndustrialSalary> debitHeader=null;
	
	public String ledgerCode;
	public String arrLedgerCode;
	
	public Object[][] totalSalCre;
	public Object[][] totalSalDeb;
	public Object[][] debitHead;
	public Object[][] creditHead;
	
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
	
	

	
	
	
	

}
