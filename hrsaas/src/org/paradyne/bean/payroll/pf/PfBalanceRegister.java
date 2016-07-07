/**
 * 
 */
package org.paradyne.bean.payroll.pf;

import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0554
 *
 */
public class PfBalanceRegister extends BeanBase{
	
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
	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public HashMap getPfSubJanToMarch() {
		return pfSubJanToMarch;
	}
	public void setPfSubJanToMarch(HashMap pfSubJanToMarch) {
		this.pfSubJanToMarch = pfSubJanToMarch;
	}
	public HashMap getPfSubAprToDec() {
		return pfSubAprToDec;
	}
	public void setPfSubAprToDec(HashMap pfSubAprToDec) {
		this.pfSubAprToDec = pfSubAprToDec;
	}
	public HashMap getPfRefundJanToMarch() {
		return pfRefundJanToMarch;
	}
	public void setPfRefundJanToMarch(HashMap pfRefundJanToMarch) {
		this.pfRefundJanToMarch = pfRefundJanToMarch;
	}
	public HashMap getPfRefundAprToDec() {
		return pfRefundAprToDec;
	}
	public void setPfRefundAprToDec(HashMap pfRefundAprToDec) {
		this.pfRefundAprToDec = pfRefundAprToDec;
	}
	public HashMap getPfLoanJanToMarch() {
		return pfLoanJanToMarch;
	}
	public void setPfLoanJanToMarch(HashMap pfLoanJanToMarch) {
		this.pfLoanJanToMarch = pfLoanJanToMarch;
	}
	public HashMap getPfLoanAprToDec() {
		return pfLoanAprToDec;
	}
	public void setPfLoanAprToDec(HashMap pfLoanAprToDec) {
		this.pfLoanAprToDec = pfLoanAprToDec;
	}
	public HashMap getPfOpeningBal() {
		return pfOpeningBal;
	}
	public void setPfOpeningBal(HashMap pfOpeningBal) {
		this.pfOpeningBal = pfOpeningBal;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

}
