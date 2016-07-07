package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LoanInstallment extends BeanBase implements Serializable {
	
	public String installCode="";
	public String loanEmpId="";
	public String empName="";
	public String tokenNo="";
	public String loanCode="";
	public String loanType="";
	public String loanStartDate="";
	public String loanRecoveryType="";
	public String interestRate="";
	public String totalPrincipalAmount="";
	public String totalInterestAmount="";
	public String monthInstallAmount="";
	public String interestBalanceAmount="";
	public String principleBalanceAmount="";
	public String totalInstallNo="";
	public String balanceInstallNo="";
	public String penaltyInterestAmount="";
	public String paracode ="";
	public ArrayList installList = null;
	public String  rank ="";
	public String  center="";
	public String  centerId="";
	public String payBilGrp="";
	public String status="";
	
	/**
	 * @return the balanceInstallNo
	 */
	public String getBalanceInstallNo() {
		return balanceInstallNo;
	}
	/**
	 * @param balanceInstallNo the balanceInstallNo to set
	 */
	public void setBalanceInstallNo(String balanceInstallNo) {
		this.balanceInstallNo = balanceInstallNo;
	}
	/**
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}
	/**
	 * @param center the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the installCode
	 */
	public String getInstallCode() {
		return installCode;
	}
	/**
	 * @param installCode the installCode to set
	 */
	public void setInstallCode(String installCode) {
		this.installCode = installCode;
	}
	/**
	 * @return the installList
	 */
	public ArrayList getInstallList() {
		return installList;
	}
	/**
	 * @param installList the installList to set
	 */
	public void setInstallList(ArrayList installList) {
		this.installList = installList;
	}
	/**
	 * @return the interestBalanceAmount
	 */
	public String getInterestBalanceAmount() {
		return interestBalanceAmount;
	}
	/**
	 * @param interestBalanceAmount the interestBalanceAmount to set
	 */
	public void setInterestBalanceAmount(String interestBalanceAmount) {
		this.interestBalanceAmount = interestBalanceAmount;
	}
	/**
	 * @return the interestRate
	 */
	public String getInterestRate() {
		return interestRate;
	}
	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	/**
	 * @return the loanCode
	 */
	public String getLoanCode() {
		return loanCode;
	}
	/**
	 * @param loanCode the loanCode to set
	 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	/**
	 * @return the loanEmpId
	 */
	public String getLoanEmpId() {
		return loanEmpId;
	}
	/**
	 * @param loanEmpId the loanEmpId to set
	 */
	public void setLoanEmpId(String loanEmpId) {
		this.loanEmpId = loanEmpId;
	}
	/**
	 * @return the loanRecoveryType
	 */
	public String getLoanRecoveryType() {
		return loanRecoveryType;
	}
	/**
	 * @param loanRecoveryType the loanRecoveryType to set
	 */
	public void setLoanRecoveryType(String loanRecoveryType) {
		this.loanRecoveryType = loanRecoveryType;
	}
	/**
	 * @return the loanStartDate
	 */
	public String getLoanStartDate() {
		return loanStartDate;
	}
	/**
	 * @param loanStartDate the loanStartDate to set
	 */
	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}
	/**
	 * @return the loanType
	 */
	public String getLoanType() {
		return loanType;
	}
	/**
	 * @param loanType the loanType to set
	 */
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	/**
	 * @return the monthInstallAmount
	 */
	public String getMonthInstallAmount() {
		return monthInstallAmount;
	}
	/**
	 * @param monthInstallAmount the monthInstallAmount to set
	 */
	public void setMonthInstallAmount(String monthInstallAmount) {
		this.monthInstallAmount = monthInstallAmount;
	}
	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}
	/**
	 * @param paracode the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}
	/**
	 * @return the penaltyInterestAmount
	 */
	public String getPenaltyInterestAmount() {
		return penaltyInterestAmount;
	}
	/**
	 * @param penaltyInterestAmount the penaltyInterestAmount to set
	 */
	public void setPenaltyInterestAmount(String penaltyInterestAmount) {
		this.penaltyInterestAmount = penaltyInterestAmount;
	}
	/**
	 * @return the principleBalanceAmount
	 */
	public String getPrincipleBalanceAmount() {
		return principleBalanceAmount;
	}
	/**
	 * @param principleBalanceAmount the principleBalanceAmount to set
	 */
	public void setPrincipleBalanceAmount(String principleBalanceAmount) {
		this.principleBalanceAmount = principleBalanceAmount;
	}
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * @return the tokenNo
	 */
	public String getTokenNo() {
		return tokenNo;
	}
	/**
	 * @param tokenNo the tokenNo to set
	 */
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	/**
	 * @return the totalInstallNo
	 */
	public String getTotalInstallNo() {
		return totalInstallNo;
	}
	/**
	 * @param totalInstallNo the totalInstallNo to set
	 */
	public void setTotalInstallNo(String totalInstallNo) {
		this.totalInstallNo = totalInstallNo;
	}
	/**
	 * @return the totalInterestAmount
	 */
	public String getTotalInterestAmount() {
		return totalInterestAmount;
	}
	/**
	 * @param totalInterestAmount the totalInterestAmount to set
	 */
	public void setTotalInterestAmount(String totalInterestAmount) {
		this.totalInterestAmount = totalInterestAmount;
	}
	/**
	 * @return the totalPrincipalAmount
	 */
	public String getTotalPrincipalAmount() {
		return totalPrincipalAmount;
	}
	/**
	 * @param totalPrincipalAmount the totalPrincipalAmount to set
	 */
	public void setTotalPrincipalAmount(String totalPrincipalAmount) {
		this.totalPrincipalAmount = totalPrincipalAmount;
	}
	/**
	 * @return the centerId
	 */
	public String getCenterId() {
		return centerId;
	}
	/**
	 * @param centerId the centerId to set
	 */
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	/**
	 * @return the payBilGrp
	 */
	public String getPayBilGrp() {
		return payBilGrp;
	}
	/**
	 * @param payBilGrp the payBilGrp to set
	 */
	public void setPayBilGrp(String payBilGrp) {
		this.payBilGrp = payBilGrp;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
