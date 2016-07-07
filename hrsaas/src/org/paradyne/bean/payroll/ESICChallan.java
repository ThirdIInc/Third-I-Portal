/**
 * 
 */
package org.paradyne.bean.payroll;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.bean.payroll.bonus.BonusAllowance;
import org.paradyne.lib.BeanBase;

/**
 * @author Dipti
 *
 */
public class ESICChallan extends BeanBase {
	
	/*List page variables*/
	private String myPage="";
	private String esicCodeItt="";
	private String divisionItt="";
	private String monthItt="";
	private String yearItt="";
	private String earningTypeItt="";
	private ArrayList<ESICChallan> challanList;
	private String branchItt="";
	
	/*Data page variables*/
	private String paymentMode="";
	private String paymentDate="";
	private String chequeNo="";
	private String payAmt="";
	private String month="";
	private String year="";
	private String division="";
	private String divCode="";
	private String esicCode="";
	private String amt="";
	private String challan="";
	private String onHold="";
	private String earningType="";
	private String holdId="";
	private String salaryCheck="";
	private String arrearsCheck="";
	private String settlementCheck="";
	private String employeeContribution="";
	private String employerContribution="";
	private String totalWages="";
	private String challanAmount="";
	private String noOfEmployees="";
	private String bankId="";
	private String bankName="";
	private String reportType="";
	private String brnId = "";
	private String brnName = "";
	
	
	
	/**
	 * @return the brnId
	 */
	public String getBrnId() {
		return brnId;
	}
	/**
	 * @param brnId the brnId to set
	 */
	public void setBrnId(String brnId) {
		this.brnId = brnId;
	}
	/**
	 * @return the brnName
	 */
	public String getBrnName() {
		return brnName;
	}
	/**
	 * @param brnName the brnName to set
	 */
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getSalaryCheck() {
		return salaryCheck;
	}
	public void setSalaryCheck(String salaryCheck) {
		this.salaryCheck = salaryCheck;
	}
	public String getArrearsCheck() {
		return arrearsCheck;
	}
	public void setArrearsCheck(String arrearsCheck) {
		this.arrearsCheck = arrearsCheck;
	}
	public String getSettlementCheck() {
		return settlementCheck;
	}
	public void setSettlementCheck(String settlementCheck) {
		this.settlementCheck = settlementCheck;
	}
	public String getEmployeeContribution() {
		return employeeContribution;
	}
	public void setEmployeeContribution(String employeeContribution) {
		this.employeeContribution = employeeContribution;
	}
	public String getEmployerContribution() {
		return employerContribution;
	}
	public void setEmployerContribution(String employerContribution) {
		this.employerContribution = employerContribution;
	}
	public String getTotalWages() {
		return totalWages;
	}
	public void setTotalWages(String totalWages) {
		this.totalWages = totalWages;
	}
	public String getNoOfEmployees() {
		return noOfEmployees;
	}
	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getHoldId() {
		return holdId;
	}
	public void setHoldId(String holdId) {
		this.holdId = holdId;
	}
	public String getEsicCode() {
		return esicCode;
	}
	public void setEsicCode(String esicCode) {
		this.esicCode = esicCode;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
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
	public String getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getChallan() {
		return challan;
	}
	public void setChallan(String challan) {
		this.challan = challan;
	}
	
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
	}
	public String getEarningType() {
		return earningType;
	}
	public void setEarningType(String earningType) {
		this.earningType = earningType;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getChallanAmount() {
		return challanAmount;
	}
	public void setChallanAmount(String challanAmount) {
		this.challanAmount = challanAmount;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getEsicCodeItt() {
		return esicCodeItt;
	}
	public void setEsicCodeItt(String esicCodeItt) {
		this.esicCodeItt = esicCodeItt;
	}
	public String getDivisionItt() {
		return divisionItt;
	}
	public void setDivisionItt(String divisionItt) {
		this.divisionItt = divisionItt;
	}
	public String getMonthItt() {
		return monthItt;
	}
	public void setMonthItt(String monthItt) {
		this.monthItt = monthItt;
	}
	public String getYearItt() {
		return yearItt;
	}
	public void setYearItt(String yearItt) {
		this.yearItt = yearItt;
	}
	public String getEarningTypeItt() {
		return earningTypeItt;
	}
	public void setEarningTypeItt(String earningTypeItt) {
		this.earningTypeItt = earningTypeItt;
	}
	public ArrayList<ESICChallan> getChallanList() {
		return challanList;
	}
	public void setChallanList(ArrayList<ESICChallan> challanList) {
		this.challanList = challanList;
	}
	/**
	 * @return the branchItt
	 */
	public String getBranchItt() {
		return branchItt;
	}
	/**
	 * @param branchItt the branchItt to set
	 */
	public void setBranchItt(String branchItt) {
		this.branchItt = branchItt;
	}
}
