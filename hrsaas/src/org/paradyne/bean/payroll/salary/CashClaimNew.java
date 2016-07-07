/**
 * 
 */
package org.paradyne.bean.payroll.salary;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author REEBA JOSEPH
 *
 */
public class CashClaimNew extends BeanBase {
	private String empIdIt ="";
	private String empNameIt ="";
	private String empTokenIt = "";
	private String claimDateIt = "";
	private String claimAppIdIt = "";
	private String claimStatusIt = "";
	ArrayList<Object> cashClaimList = null;
	ArrayList<Object> claimSubmitList = null;
	ArrayList<Object> claimApprovedList = null;
	ArrayList<Object> claimRejectedList = null;
	private String listType = "";
	private String appClaimAppIdIt = "";
	private String appEmpIdIt = "";
	private String rejClaimAppIdIt = "";
	private String rejEmpIdIt = "";
	private String subClaimAppIdIt = "";
	private String subEmpIdIt = "";
	private String myPage = "";
	private String myPageRejected = "";
	private String approveLength="false";
	private String rejectedLength="false";
	private String draftStatus = "";
	private String pendingStatus = "";
	private String approvedStatus = "";
	private String rejectedStatus = "";
		
	private String dataPath = "";
	private String empId ="";
	private String empName ="";
	private String empCenter ="";
	private String empRank ="";
	private String empToken = "";
	private String claimDate = "";
	private String particulars = "";
	private String claimStatus="";
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
	ArrayList creditList = null;
	
	//Added by Prashant
	private String proofFileName="";
	private String totAmt="";
	private String creditCode="";
	private String creditType="";
	private String onHoldAmount="";
	private String balanceAmount="";
	ArrayList<Object> uploadProofList = null;
	private ArrayList balanceList = null;
	
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public ArrayList<Object> getCashClaimList() {
		return cashClaimList;
	}
	public void setCashClaimList(ArrayList<Object> cashClaimList) {
		this.cashClaimList = cashClaimList;
	}
	public String getEmpIdIt() {
		return empIdIt;
	}
	public void setEmpIdIt(String empIdIt) {
		this.empIdIt = empIdIt;
	}
	public String getEmpNameIt() {
		return empNameIt;
	}
	public void setEmpNameIt(String empNameIt) {
		this.empNameIt = empNameIt;
	}
	public String getEmpTokenIt() {
		return empTokenIt;
	}
	public void setEmpTokenIt(String empTokenIt) {
		this.empTokenIt = empTokenIt;
	}
	public String getClaimDateIt() {
		return claimDateIt;
	}
	public void setClaimDateIt(String claimDateIt) {
		this.claimDateIt = claimDateIt;
	}
	public String getClaimAppIdIt() {
		return claimAppIdIt;
	}
	public void setClaimAppIdIt(String claimAppIdIt) {
		this.claimAppIdIt = claimAppIdIt;
	}
	public String getClaimStatusIt() {
		return claimStatusIt;
	}
	public void setClaimStatusIt(String claimStatusIt) {
		this.claimStatusIt = claimStatusIt;
	}
	public ArrayList<Object> getClaimApprovedList() {
		return claimApprovedList;
	}
	public void setClaimApprovedList(ArrayList<Object> claimApprovedList) {
		this.claimApprovedList = claimApprovedList;
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
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
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
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
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
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getIsDataAvailable() {
		return isDataAvailable;
	}
	public void setIsDataAvailable(String isDataAvailable) {
		this.isDataAvailable = isDataAvailable;
	}
	public ArrayList<Object> getCreditList() {
		return creditList;
	}
	public void setCreditList(ArrayList<Object> creditList) {
		this.creditList = creditList;
	}
	public ArrayList<Object> getClaimSubmitList() {
		return claimSubmitList;
	}
	public void setClaimSubmitList(ArrayList<Object> claimSubmitList) {
		this.claimSubmitList = claimSubmitList;
	}
	public ArrayList<Object> getClaimRejectedList() {
		return claimRejectedList;
	}
	public void setClaimRejectedList(ArrayList<Object> claimRejectedList) {
		this.claimRejectedList = claimRejectedList;
	}
	public String getAppClaimAppIdIt() {
		return appClaimAppIdIt;
	}
	public void setAppClaimAppIdIt(String appClaimAppIdIt) {
		this.appClaimAppIdIt = appClaimAppIdIt;
	}
	public String getAppEmpIdIt() {
		return appEmpIdIt;
	}
	public void setAppEmpIdIt(String appEmpIdIt) {
		this.appEmpIdIt = appEmpIdIt;
	}
	public String getRejClaimAppIdIt() {
		return rejClaimAppIdIt;
	}
	public void setRejClaimAppIdIt(String rejClaimAppIdIt) {
		this.rejClaimAppIdIt = rejClaimAppIdIt;
	}
	public String getRejEmpIdIt() {
		return rejEmpIdIt;
	}
	public void setRejEmpIdIt(String rejEmpIdIt) {
		this.rejEmpIdIt = rejEmpIdIt;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getMyPageRejected() {
		return myPageRejected;
	}
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	public String getSubClaimAppIdIt() {
		return subClaimAppIdIt;
	}
	public void setSubClaimAppIdIt(String subClaimAppIdIt) {
		this.subClaimAppIdIt = subClaimAppIdIt;
	}
	public String getSubEmpIdIt() {
		return subEmpIdIt;
	}
	public void setSubEmpIdIt(String subEmpIdIt) {
		this.subEmpIdIt = subEmpIdIt;
	}
	public String getApproveLength() {
		return approveLength;
	}
	public void setApproveLength(String approveLength) {
		this.approveLength = approveLength;
	}
	public String getRejectedLength() {
		return rejectedLength;
	}
	public void setRejectedLength(String rejectedLength) {
		this.rejectedLength = rejectedLength;
	}
	public String getDraftStatus() {
		return draftStatus;
	}
	public void setDraftStatus(String draftStatus) {
		this.draftStatus = draftStatus;
	}
	public String getPendingStatus() {
		return pendingStatus;
	}
	public void setPendingStatus(String pendingStatus) {
		this.pendingStatus = pendingStatus;
	}
	public String getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	public String getRejectedStatus() {
		return rejectedStatus;
	}
	public void setRejectedStatus(String rejectedStatus) {
		this.rejectedStatus = rejectedStatus;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getProofFileName() {
		return proofFileName;
	}
	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}
	public ArrayList<Object> getUploadProofList() {
		return uploadProofList;
	}
	public void setUploadProofList(ArrayList<Object> uploadProofList) {
		this.uploadProofList = uploadProofList;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	public ArrayList getBalanceList() {
		return balanceList;
	}
	public void setBalanceList(ArrayList balanceList) {
		this.balanceList = balanceList;
	}
	public String getOnHoldAmount() {
		return onHoldAmount;
	}
	public void setOnHoldAmount(String onHoldAmount) {
		this.onHoldAmount = onHoldAmount;
	}
	public String getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
}
