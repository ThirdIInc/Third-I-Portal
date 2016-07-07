package org.paradyne.bean.Loan;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LoanMaster extends BeanBase {

	private String loanTypeCode;
	private String loanTypeName;

	private String Debitcode;
	private String Debitname;
	private String myPage;
	private String hiddencode;
	private String show;
	private String hdeleteCode;

	private String totalRecords;
	private String listLength = "false";
	
	// Added by Prashant Shinde
	
	private String loanLimitAmount;
	
	private String adminToken ;
	private String adminName;
	private String adminCode ;
	private String accountToken ;
	private String accountName;
	private String accountCode ;
	private String accApprovalReq ;
	private String admApprovalReq ;
	private String divCode = "";
	private String divName = "";
	private String interestType = "";
	private String interestRate = "";
	private String taxable = "";
	private String otherLoanTerms = "";
	private String stdIntRateSBI = "";
    
	private String report="";
	ArrayList iteratorlist;

	public String getLoanTypeCode() {
		return loanTypeCode;
	}

	public void setLoanTypeCode(String loanTypeCode) {
		this.loanTypeCode = loanTypeCode;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public String getDebitcode() {
		return Debitcode;
	}

	public void setDebitcode(String debitcode) {
		Debitcode = debitcode;
	}

	public String getDebitname() {
		return Debitname;
	}

	public void setDebitname(String debitname) {
		Debitname = debitname;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public ArrayList getIteratorlist() {
		return iteratorlist;
	}

	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getListLength() {
		return listLength;
	}

	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	public String getLoanLimitAmount() {
		return loanLimitAmount;
	}

	public void setLoanLimitAmount(String loanLimitAmount) {
		this.loanLimitAmount = loanLimitAmount;
	}

	public String getAdminToken() {
		return adminToken;
	}

	public void setAdminToken(String adminToken) {
		this.adminToken = adminToken;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccApprovalReq() {
		return accApprovalReq;
	}

	public void setAccApprovalReq(String accApprovalReq) {
		this.accApprovalReq = accApprovalReq;
	}

	public String getAdmApprovalReq() {
		return admApprovalReq;
	}

	public void setAdmApprovalReq(String admApprovalReq) {
		this.admApprovalReq = admApprovalReq;
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

	public String getInterestType() {
		return interestType;
	}

	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getTaxable() {
		return taxable;
	}

	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	public String getOtherLoanTerms() {
		return otherLoanTerms;
	}

	public void setOtherLoanTerms(String otherLoanTerms) {
		this.otherLoanTerms = otherLoanTerms;
	}

	public String getStdIntRateSBI() {
		return stdIntRateSBI;
	}

	public void setStdIntRateSBI(String stdIntRateSBI) {
		this.stdIntRateSBI = stdIntRateSBI;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

}
