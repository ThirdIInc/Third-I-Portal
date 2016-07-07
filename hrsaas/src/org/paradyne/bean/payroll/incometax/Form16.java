package org.paradyne.bean.payroll.incometax;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Venkatesh
 *
 */
public class Form16 extends BeanBase {
	
	

	private String empID="";
	private String empName="";
	private String rank="";
	private String center="";
	private String fromYear="";
	private String toYear="";
	private String invAmount="";
	private String invName="";
	private String paraId="";
	private String empTokenNo="";
	private String invId="";
	private String payBillNo="";
	private String creditAmt="";
	private String excemptions="";
	private String rebate="";
	private String otherInc="";
	private String taxIncome="";
	private String totalTax="";
	private String educCess="";
	private String surcharge="";
	private String netTax="";
	private String taxPaid="";
	private String taxPerMon="";
	private String chkFlag="";
	private String salary="";
	private String invChapter="";
	private String chkEdit="";
	private String lop="";
	
	private String signAuthtoken="";
	private String signAuthName="";
	private String signAuthEmpId="";
	private String signAuthEmpDesg="";
	private String signAuthEmpFather="";
	
	
	
	ArrayList empInvList=null;
	ArrayList invList=null;
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
	 * @return the chkEdit
	 */
	public String getChkEdit() {
		return chkEdit;
	}
	/**
	 * @param chkEdit the chkEdit to set
	 */
	public void setChkEdit(String chkEdit) {
		this.chkEdit = chkEdit;
	}
	/**
	 * @return the chkFlag
	 */
	public String getChkFlag() {
		return chkFlag;
	}
	/**
	 * @param chkFlag the chkFlag to set
	 */
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}
	/**
	 * @return the creditAmt
	 */
	public String getCreditAmt() {
		return creditAmt;
	}
	/**
	 * @param creditAmt the creditAmt to set
	 */
	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}
	
	/**
	 * @return the educCess
	 */
	public String getEducCess() {
		return educCess;
	}
	/**
	 * @param educCess the educCess to set
	 */
	public void setEducCess(String educCess) {
		this.educCess = educCess;
	}
	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}
	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	/**
	 * @return the empInvList
	 */
	public ArrayList getEmpInvList() {
		return empInvList;
	}
	/**
	 * @param empInvList the empInvList to set
	 */
	public void setEmpInvList(ArrayList empInvList) {
		this.empInvList = empInvList;
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
	 * @return the empTokenNo
	 */
	public String getEmpTokenNo() {
		return empTokenNo;
	}
	/**
	 * @param empTokenNo the empTokenNo to set
	 */
	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}
	/**
	 * @return the excemptions
	 */
	public String getExcemptions() {
		return excemptions;
	}
	/**
	 * @param excemptions the excemptions to set
	 */
	public void setExcemptions(String excemptions) {
		this.excemptions = excemptions;
	}
	/**
	 * @return the fromYear
	 */
	public String getFromYear() {
		return fromYear;
	}
	/**
	 * @param fromYear the fromYear to set
	 */
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	/**
	 * @return the invAmount
	 */
	public String getInvAmount() {
		return invAmount;
	}
	/**
	 * @param invAmount the invAmount to set
	 */
	public void setInvAmount(String invAmount) {
		this.invAmount = invAmount;
	}
	/**
	 * @return the invChapter
	 */
	public String getInvChapter() {
		return invChapter;
	}
	/**
	 * @param invChapter the invChapter to set
	 */
	public void setInvChapter(String invChapter) {
		this.invChapter = invChapter;
	}
	/**
	 * @return the invId
	 */
	public String getInvId() {
		return invId;
	}
	/**
	 * @param invId the invId to set
	 */
	public void setInvId(String invId) {
		this.invId = invId;
	}
	/**
	 * @return the invList
	 */
	public ArrayList getInvList() {
		return invList;
	}
	/**
	 * @param invList the invList to set
	 */
	public void setInvList(ArrayList invList) {
		this.invList = invList;
	}
	/**
	 * @return the invName
	 */
	public String getInvName() {
		return invName;
	}
	/**
	 * @param invName the invName to set
	 */
	public void setInvName(String invName) {
		this.invName = invName;
	}
	/**
	 * @return the lop
	 */
	public String getLop() {
		return lop;
	}
	/**
	 * @param lop the lop to set
	 */
	public void setLop(String lop) {
		this.lop = lop;
	}
	/**
	 * @return the netTax
	 */
	public String getNetTax() {
		return netTax;
	}
	/**
	 * @param netTax the netTax to set
	 */
	public void setNetTax(String netTax) {
		this.netTax = netTax;
	}
	/**
	 * @return the otherInc
	 */
	public String getOtherInc() {
		return otherInc;
	}
	/**
	 * @param otherInc the otherInc to set
	 */
	public void setOtherInc(String otherInc) {
		this.otherInc = otherInc;
	}
	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}
	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
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
	 * @return the rebate
	 */
	public String getRebate() {
		return rebate;
	}
	/**
	 * @param rebate the rebate to set
	 */
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	/**
	 * @return the surcharge
	 */
	public String getSurcharge() {
		return surcharge;
	}
	/**
	 * @param surcharge the surcharge to set
	 */
	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}
	/**
	 * @return the taxIncome
	 */
	public String getTaxIncome() {
		return taxIncome;
	}
	/**
	 * @param taxIncome the taxIncome to set
	 */
	public void setTaxIncome(String taxIncome) {
		this.taxIncome = taxIncome;
	}
	/**
	 * @return the taxPaid
	 */
	public String getTaxPaid() {
		return taxPaid;
	}
	/**
	 * @param taxPaid the taxPaid to set
	 */
	public void setTaxPaid(String taxPaid) {
		this.taxPaid = taxPaid;
	}
	/**
	 * @return the taxPerMon
	 */
	public String getTaxPerMon() {
		return taxPerMon;
	}
	/**
	 * @param taxPerMon the taxPerMon to set
	 */
	public void setTaxPerMon(String taxPerMon) {
		this.taxPerMon = taxPerMon;
	}
	/**
	 * @return the totalTax
	 */
	public String getTotalTax() {
		return totalTax;
	}
	/**
	 * @param totalTax the totalTax to set
	 */
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	/**
	 * @return the toYear
	 */
	public String getToYear() {
		return toYear;
	}
	/**
	 * @param toYear the toYear to set
	 */
	public void setToYear(String toYear) {
		this.toYear = toYear;
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
	public String getSignAuthName() {
		return signAuthName;
	}
	public void setSignAuthName(String signAuthName) {
		this.signAuthName = signAuthName;
	}
	public String getSignAuthEmpId() {
		return signAuthEmpId;
	}
	public void setSignAuthEmpId(String signAuthEmpId) {
		this.signAuthEmpId = signAuthEmpId;
	}
	public String getSignAuthEmpDesg() {
		return signAuthEmpDesg;
	}
	public void setSignAuthEmpDesg(String signAuthEmpDesg) {
		this.signAuthEmpDesg = signAuthEmpDesg;
	}
	public String getSignAuthEmpFather() {
		return signAuthEmpFather;
	}
	public void setSignAuthEmpFather(String signAuthEmpFather) {
		this.signAuthEmpFather = signAuthEmpFather;
	}
	public String getSignAuthtoken() {
		return signAuthtoken;
	}
	public void setSignAuthtoken(String signAuthtoken) {
		this.signAuthtoken = signAuthtoken;
	}
}