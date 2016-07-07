/**
 * @author Mangesh Jadhav
 */
package org.paradyne.bean.reimbursement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class ReimbAdminApproval extends BeanBase {
	private String listType="pending";
	private String empName="";
	private String empId="";
	private String empNameList="";
	private String applRefNoList="";
	private String applRefNo="";
	private String applId="";
	
	private String applIdList="";
	private String claimDate="";
	private String expenseDate="";
	private String claimDateList="";
	private String reimbHead="";
	private String reimbHeadList="";
	private String reimbHeadCodeList="";
	private String claimAmt="";
	private String claimAmtHead="";
	private String approvedAmt="";
	private String billAmt="";
	private String approvedBillAmt="";
	private String proof="";
	private String proofHidden="";
	private String proofRefNo="";
	private String proofRefNoHidden="";
	private String level;
	private String applStatus;
	private String claimParticular;
	private String pendingLength="false";
	private String processedLength="false";
	private String approverListFlag="false";
	private String approverComments="";
	private String designation;
	private String branch;
	private String grade;
	private String division;
	private String myPage;
	private String myPageProcessed;
	private String totalClaimAmt;
	private String totalApprovedAmt;
	private String totalBillAmt;
	private String totalApprovedBillAmt;
	
	private String approverNameList="";
	private String approverCommentsList="";
	private String approverDateList="";
	
	private ArrayList pendingList=null; 
	private ArrayList processedList=null;
	private ArrayList expenseList=null;
	private ArrayList proofList=null;
	private ArrayList approverCommentList=null;
	
	private String reimburseBalance="";
	private String billsCarriedForward="";
	private ArrayList preApprovedList = null;
	private ArrayList preApprovedProofList = null;
	private String preApprovedListFlag ="false";
	private String prevProofRefNo ="";
	private String prevProofFileName ="";
	private String prevProofAmt="";
	
	private String applIdSearch="";
	private String empIdSearch="";
	private String empTokenSearch="";
	private String empNameSearch="";
	private String reimbHeadSearch="";
	private String reimbHeadNameSearch="";
	private String claimDateSearch="";
	

	public String getApplIdSearch() {
		return applIdSearch;
	}
	public void setApplIdSearch(String applIdSearch) {
		this.applIdSearch = applIdSearch;
	}
	
	public String getEmpIdSearch() {
		return empIdSearch;
	}
	public void setEmpIdSearch(String empIdSearch) {
		this.empIdSearch = empIdSearch;
	}
	public String getReimbHeadSearch() {
		return reimbHeadSearch;
	}
	public void setReimbHeadSearch(String reimbHeadSearch) {
		this.reimbHeadSearch = reimbHeadSearch;
	}
	public String getReimbHeadNameSearch() {
		return reimbHeadNameSearch;
	}
	public void setReimbHeadNameSearch(String reimbHeadNameSearch) {
		this.reimbHeadNameSearch = reimbHeadNameSearch;
	}
	public String getClaimDateSearch() {
		return claimDateSearch;
	}
	public void setClaimDateSearch(String claimDateSearch) {
		this.claimDateSearch = claimDateSearch;
	}
	public ArrayList getPreApprovedList() {
		return preApprovedList;
	}
	public void setPreApprovedList(ArrayList preApprovedList) {
		this.preApprovedList = preApprovedList;
	}
	public ArrayList getPreApprovedProofList() {
		return preApprovedProofList;
	}
	public void setPreApprovedProofList(ArrayList preApprovedProofList) {
		this.preApprovedProofList = preApprovedProofList;
	}
	public String getPreApprovedListFlag() {
		return preApprovedListFlag;
	}
	public void setPreApprovedListFlag(String preApprovedListFlag) {
		this.preApprovedListFlag = preApprovedListFlag;
	}
	public String getPrevProofRefNo() {
		return prevProofRefNo;
	}
	public void setPrevProofRefNo(String prevProofRefNo) {
		this.prevProofRefNo = prevProofRefNo;
	}
	public String getPrevProofFileName() {
		return prevProofFileName;
	}
	public void setPrevProofFileName(String prevProofFileName) {
		this.prevProofFileName = prevProofFileName;
	}
	public String getPrevProofAmt() {
		return prevProofAmt;
	}
	public void setPrevProofAmt(String prevProofAmt) {
		this.prevProofAmt = prevProofAmt;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getApplRefNo() {
		return applRefNo;
	}
	public void setApplRefNo(String applRefNo) {
		this.applRefNo = applRefNo;
	}
	public String getApplId() {
		return applId;
	}
	public void setApplId(String applId) {
		this.applId = applId;
	}
	public String getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}
	public String getReimbHead() {
		return reimbHead;
	}
	public void setReimbHead(String reimbHead) {
		this.reimbHead = reimbHead;
	}
	public String getClaimAmt() {
		return claimAmt;
	}
	public void setClaimAmt(String claimAmt) {
		this.claimAmt = claimAmt;
	}
	public String getPendingLength() {
		return pendingLength;
	}
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public ArrayList getProcessedList() {
		return processedList;
	}
	public void setProcessedList(ArrayList processedList) {
		this.processedList = processedList;
	}
	public String getApplStatus() {
		return applStatus;
	}
	public void setApplStatus(String applStatus) {
		this.applStatus = applStatus;
	}
	public String getProcessedLength() {
		return processedLength;
	}
	public void setProcessedLength(String processedLength) {
		this.processedLength = processedLength;
	}
	public String getApplIdList() {
		return applIdList;
	}
	public void setApplIdList(String applIdList) {
		this.applIdList = applIdList;
	}
	public String getApprovedAmt() {
		return approvedAmt;
	}
	public void setApprovedAmt(String approvedAmt) {
		this.approvedAmt = approvedAmt;
	}
	public String getClaimDateList() {
		return claimDateList;
	}
	public void setClaimDateList(String claimDateList) {
		this.claimDateList = claimDateList;
	}
	public String getClaimAmtHead() {
		return claimAmtHead;
	}
	public void setClaimAmtHead(String claimAmtHead) {
		this.claimAmtHead = claimAmtHead;
	}
	public String getApplRefNoList() {
		return applRefNoList;
	}
	public void setApplRefNoList(String applRefNoList) {
		this.applRefNoList = applRefNoList;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getEmpNameList() {
		return empNameList;
	}
	public void setEmpNameList(String empNameList) {
		this.empNameList = empNameList;
	}
	public String getClaimParticular() {
		return claimParticular;
	}
	public void setClaimParticular(String claimParticular) {
		this.claimParticular = claimParticular;
	}
	public ArrayList getExpenseList() {
		return expenseList;
	}
	public void setExpenseList(ArrayList expenseList) {
		this.expenseList = expenseList;
	}
	public ArrayList getProofList() {
		return proofList;
	}
	public void setProofList(ArrayList proofList) {
		this.proofList = proofList;
	}
	public String getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getProof() {
		return proof;
	}
	public void setProof(String proof) {
		this.proof = proof;
	}
	public String getProofRefNo() {
		return proofRefNo;
	}
	public void setProofRefNo(String proofRefNo) {
		this.proofRefNo = proofRefNo;
	}
	public String getTotalClaimAmt() {
		return totalClaimAmt;
	}
	public void setTotalClaimAmt(String totalClaimAmt) {
		this.totalClaimAmt = totalClaimAmt;
	}
	public String getTotalApprovedAmt() {
		return totalApprovedAmt;
	}
	public void setTotalApprovedAmt(String totalApprovedAmt) {
		this.totalApprovedAmt = totalApprovedAmt;
	}
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public String getApproverListFlag() {
		return approverListFlag;
	}
	public void setApproverListFlag(String approverListFlag) {
		this.approverListFlag = approverListFlag;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public String getApproverNameList() {
		return approverNameList;
	}
	public void setApproverNameList(String approverNameList) {
		this.approverNameList = approverNameList;
	}
	public String getApproverCommentsList() {
		return approverCommentsList;
	}
	public void setApproverCommentsList(String approverCommentsList) {
		this.approverCommentsList = approverCommentsList;
	}
	public String getApproverDateList() {
		return approverDateList;
	}
	public void setApproverDateList(String approverDateList) {
		this.approverDateList = approverDateList;
	}
	public String getProofHidden() {
		return proofHidden;
	}
	public void setProofHidden(String proofHidden) {
		this.proofHidden = proofHidden;
	}
	public String getProofRefNoHidden() {
		return proofRefNoHidden;
	}
	public void setProofRefNoHidden(String proofRefNoHidden) {
		this.proofRefNoHidden = proofRefNoHidden;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getReimbHeadList() {
		return reimbHeadList;
	}
	public void setReimbHeadList(String reimbHeadList) {
		this.reimbHeadList = reimbHeadList;
	}
	public String getMyPageProcessed() {
		return myPageProcessed;
	}
	public void setMyPageProcessed(String myPageProcessed) {
		this.myPageProcessed = myPageProcessed;
	}
	public String getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}
	public String getApprovedBillAmt() {
		return approvedBillAmt;
	}
	public void setApprovedBillAmt(String approvedBillAmt) {
		this.approvedBillAmt = approvedBillAmt;
	}
	public String getTotalApprovedBillAmt() {
		return totalApprovedBillAmt;
	}
	public void setTotalApprovedBillAmt(String totalApprovedBillAmt) {
		this.totalApprovedBillAmt = totalApprovedBillAmt;
	}
	public String getTotalBillAmt() {
		return totalBillAmt;
	}
	public void setTotalBillAmt(String totalBillAmt) {
		this.totalBillAmt = totalBillAmt;
	}
	public String getReimburseBalance() {
		return reimburseBalance;
	}
	public void setReimburseBalance(String reimburseBalance) {
		this.reimburseBalance = reimburseBalance;
	}
	public String getBillsCarriedForward() {
		return billsCarriedForward;
	}
	public void setBillsCarriedForward(String billsCarriedForward) {
		this.billsCarriedForward = billsCarriedForward;
	}
	public String getEmpTokenSearch() {
		return empTokenSearch;
	}
	public void setEmpTokenSearch(String empTokenSearch) {
		this.empTokenSearch = empTokenSearch;
	}
	public String getEmpNameSearch() {
		return empNameSearch;
	}
	public void setEmpNameSearch(String empNameSearch) {
		this.empNameSearch = empNameSearch;
	}
	public String getReimbHeadCodeList() {
		return reimbHeadCodeList;
	}
	public void setReimbHeadCodeList(String reimbHeadCodeList) {
		this.reimbHeadCodeList = reimbHeadCodeList;
	}
}
