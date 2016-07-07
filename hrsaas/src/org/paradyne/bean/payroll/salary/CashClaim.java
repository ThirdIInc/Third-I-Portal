/**
 * 
 */
package org.paradyne.bean.payroll.salary;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class CashClaim extends BeanBase {
	
	private String empId ="";
	private String empName ="";
	private String empCenter ="";
	private String empRank ="";
	private String empToken = "";
	private String claimDate = "";
	private String particulars = "";
	private String status="";
	private String statusFlag="false";
	private String claimStatus="";
	
	private  ArrayList<Object> att=null;
	private String claimId = "";
	private String creditId="";
	private String onHoldAmt="";
	private String hproof="";
	private String creditName ="";
	private String entitleAmt ="";
	private String claimAmt="";
	private String balanceAmt="";
	private String totalAmt = "";
	private String isDataAvailable ="false";
	private String viewCashClaim="true";
	private String isSaveButton = "false";
	private String IsApprove="false";
	
	
	private String vchName;
	private String vchCode;
	private String vchAmt;
	private String vchProof;
	private String totalVchAmt;
	
	private String applForDivision;
	private String applForDivisionCode;
	
	private  ArrayList<Object> voucherList=null;
	
	public ArrayList<Object> getVoucherList() {
		return voucherList;
	}


	public void setVoucherList(ArrayList<Object> voucherList) {
		this.voucherList = voucherList;
	}


	public String getIsApprove() {
		return IsApprove;
	}


	public void setIsApprove(String isApprove) {
		IsApprove = isApprove;
	}


	public String getIsSaveButton() {
		return isSaveButton;
	}


	public void setIsSaveButton(String isSaveButton) {
		this.isSaveButton = isSaveButton;
	}


	public String getViewCashClaim() {
		return viewCashClaim;
	}


	public void setViewCashClaim(String viewCashClaim) {
		this.viewCashClaim = viewCashClaim;
	}


	public String getIsDataAvailable() {
		return isDataAvailable;
	}


	public void setIsDataAvailable(String isDataAvailable) {
		this.isDataAvailable = isDataAvailable;
	}


	public ArrayList<Object> getAtt() {
		return att;
	}


	public void setAtt(ArrayList<Object> att) {
		this.att = att;
	}


	public String getCreditId() {
		return creditId;
	}


	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}


	public String getCreditName() {
		return creditName;
	}


	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}


	public String getEntitleAmt() {
		return entitleAmt;
	}


	public void setEntitleAmt(String entitleAmt) {
		this.entitleAmt = entitleAmt;
	}


	public String getClaimAmt() {
		return claimAmt;
	}


	public void setClaimAmt(String claimAmt) {
		this.claimAmt = claimAmt;
	}


	public String getBalanceAmt() {
		return balanceAmt;
	}


	public void setBalanceAmt(String balanceAmt) {
		this.balanceAmt = balanceAmt;
	}


	public String getClaimDate() {
		return claimDate;
	}


	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}


	public String getParticulars() {
		return particulars;
	}


	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}


	public String getEmpToken() {
		return empToken;
	}


	public void setEmpToken(String empToken) {
		this.empToken = empToken;
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
	public String getEmpCenter() {
		return empCenter;
	}
	public void setEmpCenter(String empCenter) {
		this.empCenter = empCenter;
	}
	public String getEmpRank() {
		return empRank;
	}
	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}


	public String getClaimId() {
		return claimId;
	}


	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}


	public String getTotalAmt() {
		return totalAmt;
	}


	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}


	public String getOnHoldAmt() {
		return onHoldAmt;
	}


	public void setOnHoldAmt(String onHoldAmt) {
		this.onHoldAmt = onHoldAmt;
	}


	public String getHproof() {
		return hproof;
	}


	public void setHproof(String hproof) {
		this.hproof = hproof;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getStatusFlag() {
		return statusFlag;
	}


	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}


	public String getClaimStatus() {
		return claimStatus;
	}


	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}


	public String getVchName() {
		return vchName;
	}


	public void setVchName(String vchName) {
		this.vchName = vchName;
	}


	public String getVchCode() {
		return vchCode;
	}


	public void setVchCode(String vchCode) {
		this.vchCode = vchCode;
	}


	public String getVchAmt() {
		return vchAmt;
	}


	public void setVchAmt(String vchAmt) {
		this.vchAmt = vchAmt;
	}


	public String getVchProof() {
		return vchProof;
	}


	public void setVchProof(String vchProof) {
		this.vchProof = vchProof;
	}


	public String getTotalVchAmt() {
		return totalVchAmt;
	}


	public void setTotalVchAmt(String totalVchAmt) {
		this.totalVchAmt = totalVchAmt;
	}


	public String getApplForDivisionCode() {
		return applForDivisionCode;
	}


	public void setApplForDivisionCode(String applForDivisionCode) {
		this.applForDivisionCode = applForDivisionCode;
	}


	public String getApplForDivision() {
		return applForDivision;
	}


	public void setApplForDivision(String applForDivision) {
		this.applForDivision = applForDivision;
	}
		

}
