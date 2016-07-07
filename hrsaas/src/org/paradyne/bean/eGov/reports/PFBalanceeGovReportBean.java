package org.paradyne.bean.eGov.reports;

import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class PFBalanceeGovReportBean extends BeanBase {
	
	private HashMap pfSubJanToMarch=null;
	private HashMap pfSubAprToDec=null;
	private HashMap pfRefundJanToMarch=null;
	private HashMap pfRefundAprToDec=null;
	private HashMap pfLoanJanToMarch=null;
	private HashMap pfLoanAprToDec=null;
	private HashMap pfOpeningBal=null;
	private String divName;
	private String divCode;
	private String branchName;
	private String branchCode;
	private String deptName;
	private String sysDate;
	private String deptCode;
	private String reportType;
	
	private String payBill = " ";
	private String paybillId = " ";
	/**
	 * @return the pfSubJanToMarch
	 */
	public HashMap getPfSubJanToMarch() {
		return pfSubJanToMarch;
	}
	/**
	 * @param pfSubJanToMarch the pfSubJanToMarch to set
	 */
	public void setPfSubJanToMarch(HashMap pfSubJanToMarch) {
		this.pfSubJanToMarch = pfSubJanToMarch;
	}
	/**
	 * @return the pfSubAprToDec
	 */
	public HashMap getPfSubAprToDec() {
		return pfSubAprToDec;
	}
	/**
	 * @param pfSubAprToDec the pfSubAprToDec to set
	 */
	public void setPfSubAprToDec(HashMap pfSubAprToDec) {
		this.pfSubAprToDec = pfSubAprToDec;
	}
	/**
	 * @return the pfRefundJanToMarch
	 */
	public HashMap getPfRefundJanToMarch() {
		return pfRefundJanToMarch;
	}
	/**
	 * @param pfRefundJanToMarch the pfRefundJanToMarch to set
	 */
	public void setPfRefundJanToMarch(HashMap pfRefundJanToMarch) {
		this.pfRefundJanToMarch = pfRefundJanToMarch;
	}
	/**
	 * @return the pfRefundAprToDec
	 */
	public HashMap getPfRefundAprToDec() {
		return pfRefundAprToDec;
	}
	/**
	 * @param pfRefundAprToDec the pfRefundAprToDec to set
	 */
	public void setPfRefundAprToDec(HashMap pfRefundAprToDec) {
		this.pfRefundAprToDec = pfRefundAprToDec;
	}
	/**
	 * @return the pfLoanJanToMarch
	 */
	public HashMap getPfLoanJanToMarch() {
		return pfLoanJanToMarch;
	}
	/**
	 * @param pfLoanJanToMarch the pfLoanJanToMarch to set
	 */
	public void setPfLoanJanToMarch(HashMap pfLoanJanToMarch) {
		this.pfLoanJanToMarch = pfLoanJanToMarch;
	}
	/**
	 * @return the pfLoanAprToDec
	 */
	public HashMap getPfLoanAprToDec() {
		return pfLoanAprToDec;
	}
	/**
	 * @param pfLoanAprToDec the pfLoanAprToDec to set
	 */
	public void setPfLoanAprToDec(HashMap pfLoanAprToDec) {
		this.pfLoanAprToDec = pfLoanAprToDec;
	}
	/**
	 * @return the pfOpeningBal
	 */
	public HashMap getPfOpeningBal() {
		return pfOpeningBal;
	}
	/**
	 * @param pfOpeningBal the pfOpeningBal to set
	 */
	public void setPfOpeningBal(HashMap pfOpeningBal) {
		this.pfOpeningBal = pfOpeningBal;
	}
	/**
	 * @return the divName
	 */
	public String getDivName() {
		return divName;
	}
	/**
	 * @param divName the divName to set
	 */
	public void setDivName(String divName) {
		this.divName = divName;
	}
	/**
	 * @return the divCode
	 */
	public String getDivCode() {
		return divCode;
	}
	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the sysDate
	 */
	public String getSysDate() {
		return sysDate;
	}
	/**
	 * @param sysDate the sysDate to set
	 */
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the payBill
	 */
	public String getPayBill() {
		return payBill;
	}
	/**
	 * @param payBill the payBill to set
	 */
	public void setPayBill(String payBill) {
		this.payBill = payBill;
	}
	/**
	 * @return the paybillId
	 */
	public String getPaybillId() {
		return paybillId;
	}
	/**
	 * @param paybillId the paybillId to set
	 */
	public void setPaybillId(String paybillId) {
		this.paybillId = paybillId;
	}

}
