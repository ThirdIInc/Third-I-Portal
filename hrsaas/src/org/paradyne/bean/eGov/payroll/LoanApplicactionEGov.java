package org.paradyne.bean.eGov.payroll;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * 
 * @author Anantha lakshmi
 *
 */

public class LoanApplicactionEGov extends BeanBase {
	
	
	String token="";
	String empName="";
	String empId="";
	String loanAmount="";
	String pendingLoanAmount="";
	String emiAmount="";
	String loanDate="";
	String isRefundable="";
	String loanAmt1="";
	String loanAmt="";
	String emiAmt1="";
	String emiAmount1="";
	String paraId="";
	String paraLoanAmt="";
	String paraEmiAmt="";
	String srNo="";
	String loanDateIt="";
	String loanAmt1It="";
	String isRefundableIt="";
	String emiAmount1It="";
	ArrayList list;
	String hiddenEdit="";
	String loanID="";
	String loanIDList="";
	private ArrayList<LoanApplicactionEGov> creditHeader=null;
	private ArrayList<LoanApplicactionEGov> debitHeader=null;
	ArrayList iteratorList;

	public ArrayList getIteratorList() {
		return iteratorList;
	}
	public void setIteratorList(ArrayList iteratorList) {
		this.iteratorList = iteratorList;
	}
	
	public ArrayList<LoanApplicactionEGov> getCreditHeader() {
		return creditHeader;
	}
	public void setCreditHeader(ArrayList<LoanApplicactionEGov> creditHeader) {
		this.creditHeader = creditHeader;
	}
	public ArrayList<LoanApplicactionEGov> getDebitHeader() {
		return debitHeader;
	}
	public void setDebitHeader(ArrayList<LoanApplicactionEGov> debitHeader) {
		this.debitHeader = debitHeader;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getPendingLoanAmount() {
		return pendingLoanAmount;
	}
	public void setPendingLoanAmount(String pendingLoanAmount) {
		this.pendingLoanAmount = pendingLoanAmount;
	}
	public String getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(String emiAmount) {
		this.emiAmount = emiAmount;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public String getIsRefundable() {
		return isRefundable;
	}
	public void setIsRefundable(String isRefundable) {
		this.isRefundable = isRefundable;
	}
	public String getLoanAmt1() {
		return loanAmt1;
	}
	public void setLoanAmt1(String loanAmt1) {
		this.loanAmt1 = loanAmt1;
	}
	public String getEmiAmount1() {
		return emiAmount1;
	}
	public void setEmiAmount1(String emiAmount1) {
		this.emiAmount1 = emiAmount1;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getEmiAmt1() {
		return emiAmt1;
	}
	public void setEmiAmt1(String emiAmt1) {
		this.emiAmt1 = emiAmt1;
	}
	public String getLoanDateIt() {
		return loanDateIt;
	}
	public void setLoanDateIt(String loanDateIt) {
		this.loanDateIt = loanDateIt;
	}
	public String getLoanAmt1It() {
		return loanAmt1It;
	}
	public void setLoanAmt1It(String loanAmt1It) {
		this.loanAmt1It = loanAmt1It;
	}
	public String getIsRefundableIt() {
		return isRefundableIt;
	}
	public void setIsRefundableIt(String isRefundableIt) {
		this.isRefundableIt = isRefundableIt;
	}
	public String getEmiAmount1It() {
		return emiAmount1It;
	}
	public void setEmiAmount1It(String emiAmount1It) {
		this.emiAmount1It = emiAmount1It;
	}
	public String getLoanID() {
		return loanID;
	}
	public void setLoanID(String loanID) {
		this.loanID = loanID;
	}
	public String getLoanIDList() {
		return loanIDList;
	}
	public void setLoanIDList(String loanIDList) {
		this.loanIDList = loanIDList;
	}
	public String getParaLoanAmt() {
		return paraLoanAmt;
	}
	public void setParaLoanAmt(String paraLoanAmt) {
		this.paraLoanAmt = paraLoanAmt;
	}
	public String getParaEmiAmt() {
		return paraEmiAmt;
	}
	public void setParaEmiAmt(String paraEmiAmt) {
		this.paraEmiAmt = paraEmiAmt;
	}

}
