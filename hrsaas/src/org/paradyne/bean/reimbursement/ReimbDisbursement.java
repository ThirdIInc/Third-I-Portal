/**
 * 
 */
package org.paradyne.bean.reimbursement;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0554
 *
 */
public class ReimbDisbursement extends BeanBase {

		private String listType="pending";
		private String closedLength="false";
		private String pendingLength="false";
		private String approverListFlag="false";
		private String myPageClosed="";
		private String applRefNoList="";
		private String applIdList="";		
		private String empNameList="";
		private String claimDateList=""; 
		private String claimAmtList="";
		private String myPage="";
		private String statusList="";
		
		
		private String applIdSearch="";
		private String empIdSearch="";
		private String empTokenSearch="";
		private String empNameSearch="";
		private String reimbHeadSearch="";
		private String reimbHeadNameSearch="";
		private String claimDateSearch="";
		
		
		private String empName="";
		private String empId="";
		private String applRefNo="";
		private String applId="";
		private String disbursementCode="";
		private String disbIdList="";
		private String disbReferenceId="";
		
		private String claimDate="";
		private String expenseDate="";
		private String reimbHead="";
		private String reimbHeadList="";
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
		private String accountantComments="";
		private String designation;
		private String branch;
		private String grade;
		private String division;
		private ArrayList disburseList=null;
		private ArrayList closedList=null;
		private String totalClaimAmt;
		private String totalApprovedAmt;
		private String totalBillAmt;
		private String totalApprovedBillAmt;
		
		private String approverNameList="";
		private String approverCommentsList="";
		private String approverDateList="";
		
		
		private String disbAmount="";
		private String disbDate="";
		private String payMode="";
		private String chequeDate="";
		private String chequeNo="";
		private String month="";
		private String year="";
		 private String accountNo="";
		 private String bank="";
		 private String bankName="";
		
		private HashMap<String, String> payModeHashmap=null;
		
		
		private ArrayList pendingList=null; 
		private ArrayList processedList=null;
		private ArrayList expenseList=null;
		private ArrayList proofList=null;
		private ArrayList approverCommentList=null;
		private String source ="";
		private boolean alertFlag = false ; 
		private String empIdList="";
		/**
		 * @return the source
		 */
		public String getSource() {
			return source;
		}
		/**
		 * @param source the source to set
		 */
		public void setSource(String source) {
			this.source = source;
		}
		public String getListType() {
			return listType;
		}
		public void setListType(String listType) {
			this.listType = listType;
		}
		public String getClosedLength() {
			return closedLength;
		}
		public void setClosedLength(String closedLength) {
			this.closedLength = closedLength;
		}
		public String getApplRefNoList() {
			return applRefNoList;
		}
		public void setApplRefNoList(String applRefNoList) {
			this.applRefNoList = applRefNoList;
		}
		public String getApplIdList() {
			return applIdList;
		}
		public void setApplIdList(String applIdList) {
			this.applIdList = applIdList;
		}
		public String getEmpNameList() {
			return empNameList;
		}
		public void setEmpNameList(String empNameList) {
			this.empNameList = empNameList;
		}
		public String getClaimDateList() {
			return claimDateList;
		}
		public void setClaimDateList(String claimDateList) {
			this.claimDateList = claimDateList;
		}
		public String getClaimAmtList() {
			return claimAmtList;
		}
		public void setClaimAmtList(String claimAmtList) {
			this.claimAmtList = claimAmtList;
		}
		public String getMyPage() {
			return myPage;
		}
		public void setMyPage(String myPage) {
			this.myPage = myPage;
		}
		public String getStatusList() {
			return statusList;
		}
		public void setStatusList(String statusList) {
			this.statusList = statusList;
		}
		public ArrayList getDisburseList() {
			return disburseList;
		}
		public void setDisburseList(ArrayList disburseList) {
			this.disburseList = disburseList;
		}
		public ArrayList getClosedList() {
			return closedList;
		}
		public void setClosedList(ArrayList closedList) {
			this.closedList = closedList;
		}
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
		public String getPendingLength() {
			return pendingLength;
		}
		public void setPendingLength(String pendingLength) {
			this.pendingLength = pendingLength;
		}
		public String getMyPageClosed() {
			return myPageClosed;
		}
		public void setMyPageClosed(String myPageClosed) {
			this.myPageClosed = myPageClosed;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
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
		public String getExpenseDate() {
			return expenseDate;
		}
		public void setExpenseDate(String expenseDate) {
			this.expenseDate = expenseDate;
		}
		public String getReimbHead() {
			return reimbHead;
		}
		public void setReimbHead(String reimbHead) {
			this.reimbHead = reimbHead;
		}
		public String getReimbHeadList() {
			return reimbHeadList;
		}
		public void setReimbHeadList(String reimbHeadList) {
			this.reimbHeadList = reimbHeadList;
		}
		public String getClaimAmt() {
			return claimAmt;
		}
		public void setClaimAmt(String claimAmt) {
			this.claimAmt = claimAmt;
		}
		public String getClaimAmtHead() {
			return claimAmtHead;
		}
		public void setClaimAmtHead(String claimAmtHead) {
			this.claimAmtHead = claimAmtHead;
		}
		public String getApprovedAmt() {
			return approvedAmt;
		}
		public void setApprovedAmt(String approvedAmt) {
			this.approvedAmt = approvedAmt;
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
		public String getProof() {
			return proof;
		}
		public void setProof(String proof) {
			this.proof = proof;
		}
		public String getProofHidden() {
			return proofHidden;
		}
		public void setProofHidden(String proofHidden) {
			this.proofHidden = proofHidden;
		}
		public String getProofRefNo() {
			return proofRefNo;
		}
		public void setProofRefNo(String proofRefNo) {
			this.proofRefNo = proofRefNo;
		}
		public String getProofRefNoHidden() {
			return proofRefNoHidden;
		}
		public void setProofRefNoHidden(String proofRefNoHidden) {
			this.proofRefNoHidden = proofRefNoHidden;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public String getApplStatus() {
			return applStatus;
		}
		public void setApplStatus(String applStatus) {
			this.applStatus = applStatus;
		}
		public String getClaimParticular() {
			return claimParticular;
		}
		public void setClaimParticular(String claimParticular) {
			this.claimParticular = claimParticular;
		}
		public String getAccountantComments() {
			return accountantComments;
		}
		public void setAccountantComments(String accountantComments) {
			this.accountantComments = accountantComments;
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
		public ArrayList getPendingList() {
			return pendingList;
		}
		public void setPendingList(ArrayList pendingList) {
			this.pendingList = pendingList;
		}
		public ArrayList getProcessedList() {
			return processedList;
		}
		public void setProcessedList(ArrayList processedList) {
			this.processedList = processedList;
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
		public ArrayList getApproverCommentList() {
			return approverCommentList;
		}
		public void setApproverCommentList(ArrayList approverCommentList) {
			this.approverCommentList = approverCommentList;
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
		public String getTotalBillAmt() {
			return totalBillAmt;
		}
		public void setTotalBillAmt(String totalBillAmt) {
			this.totalBillAmt = totalBillAmt;
		}
		public String getTotalApprovedBillAmt() {
			return totalApprovedBillAmt;
		}
		public void setTotalApprovedBillAmt(String totalApprovedBillAmt) {
			this.totalApprovedBillAmt = totalApprovedBillAmt;
		}
		public String getApproverListFlag() {
			return approverListFlag;
		}
		public void setApproverListFlag(String approverListFlag) {
			this.approverListFlag = approverListFlag;
		}
		public String getDisbAmount() {
			return disbAmount;
		}
		public void setDisbAmount(String disbAmount) {
			this.disbAmount = disbAmount;
		}
		public String getDisbDate() {
			return disbDate;
		}
		public void setDisbDate(String disbDate) {
			this.disbDate = disbDate;
		}
		public String getPayMode() {
			return payMode;
		}
		public void setPayMode(String payMode) {
			this.payMode = payMode;
		}
		public HashMap<String, String> getPayModeHashmap() {
			return payModeHashmap;
		}
		public void setPayModeHashmap(HashMap<String, String> payModeHashmap) {
			this.payModeHashmap = payModeHashmap;
		}
		public String getChequeDate() {
			return chequeDate;
		}
		public void setChequeDate(String chequeDate) {
			this.chequeDate = chequeDate;
		}
		public String getChequeNo() {
			return chequeNo;
		}
		public void setChequeNo(String chequeNo) {
			this.chequeNo = chequeNo;
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
		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		public String getBank() {
			return bank;
		}
		public void setBank(String bank) {
			this.bank = bank;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public String getDisbursementCode() {
			return disbursementCode;
		}
		public void setDisbursementCode(String disbursementCode) {
			this.disbursementCode = disbursementCode;
		}
		public String getDisbReferenceId() {
			return disbReferenceId;
		}
		public void setDisbReferenceId(String disbReferenceId) {
			this.disbReferenceId = disbReferenceId;
		}
		public String getDisbIdList() {
			return disbIdList;
		}
		public void setDisbIdList(String disbIdList) {
			this.disbIdList = disbIdList;
		}
		/**
		 * @return the alertFlag
		 */
		public boolean isAlertFlag() {
			return alertFlag;
		}
		/**
		 * @param alertFlag the alertFlag to set
		 */
		public void setAlertFlag(boolean alertFlag) {
			this.alertFlag = alertFlag;
		}
		/**
		 * @return the empIdList
		 */
		public String getEmpIdList() {
			return empIdList;
		}
		/**
		 * @param empIdList the empIdList to set
		 */
		public void setEmpIdList(String empIdList) {
			this.empIdList = empIdList;
		}
}
