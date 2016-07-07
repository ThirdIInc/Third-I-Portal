package org.paradyne.bean.Loan;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LoanClosure extends BeanBase {

	private String loanClosureCode;
	private String loanAppCode;
	private String empCode;
	private String empToken;
	private String empName;
	private String BranchName;
	private String deptName;
	private String sanctionAmount;
	private String interestRate;
	private String interestType;
	private String amountPaid;
	private String balanceAmount;
	private String closureDate;
	private String amtPaidByEmp;
	private String date;
	
	private String installmentFlag = "false";
	private String monthYear;
	private String principalAmt;
	private String interestAmt;
	private String installmentAmt;
	private String paidFlag = "false";
	private String isPaid;
	private String balancePrincipalAmt;
	private String totalPrincipalAmt;
	private String totalInterestAmt;
	private String totalInstallmenteAmt;
	private String hiddenInterestType;
	private String isPaidFlag;
	
	private String flatRateFlag="false";
	private String emiAmount;
	private String noOfInstallmentOther;
	private String noOfInstallmentsReduceInt;
	private String hiddenCalType;
	private String monthlyPrincAmount;
	private ArrayList<Object> installmentList;
	
	public ArrayList<Object> getInstallmentList() {
		return installmentList;
	}
	public void setInstallmentList(ArrayList<Object> installmentList) {
		this.installmentList = installmentList;
	}
	public String getLoanAppCode() {
		return loanAppCode;
	}
	public void setLoanAppCode(String loanAppCode) {
		this.loanAppCode = loanAppCode;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getBranchName() {
		return BranchName;
	}
	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSanctionAmount() {
		return sanctionAmount;
	}
	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public String getInterestType() {
		return interestType;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	public String getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getClosureDate() {
		return closureDate;
	}
	public void setClosureDate(String closureDate) {
		this.closureDate = closureDate;
	}
	public String getAmtPaidByEmp() {
		return amtPaidByEmp;
	}
	public void setAmtPaidByEmp(String amtPaidByEmp) {
		this.amtPaidByEmp = amtPaidByEmp;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLoanClosureCode() {
		return loanClosureCode;
	}
	public void setLoanClosureCode(String loanClosureCode) {
		this.loanClosureCode = loanClosureCode;
	}
	
	public String getNoOfInstallmentOther() {
		return noOfInstallmentOther;
	}
	public void setNoOfInstallmentOther(String noOfInstallmentOther) {
		this.noOfInstallmentOther = noOfInstallmentOther;
	}
	public String getNoOfInstallmentsReduceInt() {
		return noOfInstallmentsReduceInt;
	}
	public void setNoOfInstallmentsReduceInt(String noOfInstallmentsReduceInt) {
		this.noOfInstallmentsReduceInt = noOfInstallmentsReduceInt;
	}
	public String getInstallmentFlag() {
		return installmentFlag;
	}
	public void setInstallmentFlag(String installmentFlag) {
		this.installmentFlag = installmentFlag;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public String getPrincipalAmt() {
		return principalAmt;
	}
	public void setPrincipalAmt(String principalAmt) {
		this.principalAmt = principalAmt;
	}
	public String getInterestAmt() {
		return interestAmt;
	}
	public void setInterestAmt(String interestAmt) {
		this.interestAmt = interestAmt;
	}
	public String getInstallmentAmt() {
		return installmentAmt;
	}
	public void setInstallmentAmt(String installmentAmt) {
		this.installmentAmt = installmentAmt;
	}
	public String getPaidFlag() {
		return paidFlag;
	}
	public void setPaidFlag(String paidFlag) {
		this.paidFlag = paidFlag;
	}
	public String getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}
	public String getBalancePrincipalAmt() {
		return balancePrincipalAmt;
	}
	public void setBalancePrincipalAmt(String balancePrincipalAmt) {
		this.balancePrincipalAmt = balancePrincipalAmt;
	}
	public String getTotalPrincipalAmt() {
		return totalPrincipalAmt;
	}
	public void setTotalPrincipalAmt(String totalPrincipalAmt) {
		this.totalPrincipalAmt = totalPrincipalAmt;
	}
	public String getTotalInterestAmt() {
		return totalInterestAmt;
	}
	public void setTotalInterestAmt(String totalInterestAmt) {
		this.totalInterestAmt = totalInterestAmt;
	}
	public String getTotalInstallmenteAmt() {
		return totalInstallmenteAmt;
	}
	public void setTotalInstallmenteAmt(String totalInstallmenteAmt) {
		this.totalInstallmenteAmt = totalInstallmenteAmt;
	}
	public String getHiddenInterestType() {
		return hiddenInterestType;
	}
	public void setHiddenInterestType(String hiddenInterestType) {
		this.hiddenInterestType = hiddenInterestType;
	}
	public String getIsPaidFlag() {
		return isPaidFlag;
	}
	public void setIsPaidFlag(String isPaidFlag) {
		this.isPaidFlag = isPaidFlag;
	}
	public String getFlatRateFlag() {
		return flatRateFlag;
	}
	public void setFlatRateFlag(String flatRateFlag) {
		this.flatRateFlag = flatRateFlag;
	}
	public String getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(String emiAmount) {
		this.emiAmount = emiAmount;
	}
	public String getHiddenCalType() {
		return hiddenCalType;
	}
	public void setHiddenCalType(String hiddenCalType) {
		this.hiddenCalType = hiddenCalType;
	}
	public String getMonthlyPrincAmount() {
		return monthlyPrincAmount;
	}
	public void setMonthlyPrincAmount(String monthlyPrincAmount) {
		this.monthlyPrincAmount = monthlyPrincAmount;
	}
}
