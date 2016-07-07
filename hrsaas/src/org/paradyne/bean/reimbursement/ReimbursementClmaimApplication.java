package org.paradyne.bean.reimbursement;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class ReimbursementClmaimApplication extends BeanBase {
	

	private String source ="";

	private String sysdate="";
	private String onHoldBalance="0";
	private ArrayList approverCommentList =null;
	private String appSrNo =""; 
	private String prevApproverID =""; 
	private String prevApproverName =""; 
	private String prevApproverDate =""; 
	private String prevApproverStatus =""; 
	private String prevApproverComment =""; 
	private String prevAppCommentListFlag="false";
	private String creditNameItt="";
	private String historyCreditCode="";
	private String referenceId="";
	private String reimbursBalance="";
	private String billsCarriedForward="";
	private String billsCarriedForwardList="";
	private String minBillAmount="";
	private String billAmount="";
	private String billAmountItt="";
	private String reimCodeItt ="";
	private String showFlag = "true";
	private String totalAmt = "";
	private ArrayList draftList =null;
	private String EmpIdItt ="";
	private String applicationCodeItt =""; 
	private String applicationDateItt =""; 
	private String claimAmtItt =""; 
	private String statusItt =""; 
	private String hiddenApplicationCode ="";
	private String statusNameItt="";
	private String checkReportingStructure= "";
	private String userUploadFileName ="";
	private String proofFileName="";
	private String uploadDocPath = "";
	private String hiddenCreditCode ="";
	private String comments="";
	private String checkEdit="";
	private String creditCode ="";
	private String creditType ="";
	private String balance ="";
	private ArrayList balanceList =null;
	private String empCode="";
	private String levstatus="";
	private String  checkRemoveUpload ="";
	
	private String reimbursementCrediCode ="";
	private String reimbursementCrediName ="";
	private String expenseDate ="";
	private String claimAmount ="";
	private String applStatus;
	
	private String expenseDateItt="";
	private String dataPath ="";
	
	private String proofSrNo ="";
	private String proofName ="";
	private ArrayList proofList =null;
	
	private String empId ="";
	private String empToken ="";
	private String empName ="";
	private String designation ="";
	private String branch ="";
	private String grade ="";
	private String claimDate ="";
	private String division ="";
	private String divisionCode ="";
	
	private String uploadFileName="";
	
	private ArrayList  list  =null;
	private ArrayList  ittUploadList  =null;
	private String uploadDoc ="";
	 
	private String srNo="";
	private String reimbursementCrediCodeItt ="";
	private String reimbursementCrediNameItt ="";
	private String claimAmountItt ="";
	
	private String applicationId =""; 
	private String reimbursementHead ="";
	private String claimHistoryDate ="";
	private String claimHistoryAmount ="";
	private String claimHistoryStatus ="";
	private String claimHistoryApprovedAmount ="";
	

	private ArrayList claimHistory = null;
	
	private String listType="pending";
	private String empNameList="";
	private String applRefNoList="";
	private String applRefNo="";
	private String applId="";
	
	private String applIdList="";
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
	private String claimParticular;
	private String pendingLength="false";
	private String processedLength="false";
	private String approverListFlag="false";
	private String approverComments="";
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
	private String reimburseBalance="";
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
	
	// for manager bean start
	private String claimId="";
	private String applicationDate="";
	private String employeeToken="";
	private String approverId="";
	private String approverName="";
	private String employeeId="";
	private String employeeName="";
	private String employeeDesignation="";
	private String employeeBranch="";
	private String employeeGrade="";
	private String employeeDivision="";
	private String claimParticulars="";
	private String head="";
	private String status="";
	private String decodedStatus="";
	private String claimIdItt="";
	private String applicationIdItt="";
	private String employeeNameItt="";
	private String headItt="";
	private String headCodeItt="";
	private String decodedStatusItt="";
	private String expenseBillAmountItt="";
	ArrayList reimbursementIteratorlist;
	private String prevApproverNameItt;
	private String prevApproverDateItt;
	private String prevApproverCommentItt;
	private String expenseClaimAmountItt;
	private String expenseProofItt;
	private String proofReferenceIdItt;
	private String proofDocItt;
	private String claimAmountTotal;
	private String billAmountTotal="";
	ArrayList listProof;
	private boolean approverCommentListFlag = false ; 
	private boolean commentListFlag = false ; 
	private String adminStatus="";
	// for manager bean end
	//for Admin Accountant start
	
	private String closedLength="false";
	private String myPageClosed="";
	private String claimAmtList="";
	private String statusList="";
	private String disbursementCode="";
	private String disbIdList="";
	private String disbReferenceId="";
	private String accountantComments="";
	private ArrayList disburseList=null;
	private ArrayList closedList=null;
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
	//for Admin Accountant end
	
	/**
	 * @return the claimId
	 */
	public String getClaimId() {
		return claimId;
	}
	/**
	 * @param claimId the claimId to set
	 */
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return applicationDate;
	}
	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return employeeToken;
	}
	/**
	 * @param employeeToken the employeeToken to set
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	/**
	 * @return the approverId
	 */
	public String getApproverId() {
		return approverId;
	}
	/**
	 * @param approverId the approverId to set
	 */
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return approverName;
	}
	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the employeeDesignation
	 */
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}
	/**
	 * @param employeeDesignation the employeeDesignation to set
	 */
	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}
	/**
	 * @return the employeeBranch
	 */
	public String getEmployeeBranch() {
		return employeeBranch;
	}
	/**
	 * @param employeeBranch the employeeBranch to set
	 */
	public void setEmployeeBranch(String employeeBranch) {
		this.employeeBranch = employeeBranch;
	}
	/**
	 * @return the employeeGrade
	 */
	public String getEmployeeGrade() {
		return employeeGrade;
	}
	/**
	 * @param employeeGrade the employeeGrade to set
	 */
	public void setEmployeeGrade(String employeeGrade) {
		this.employeeGrade = employeeGrade;
	}
	/**
	 * @return the employeeDivision
	 */
	public String getEmployeeDivision() {
		return employeeDivision;
	}
	/**
	 * @param employeeDivision the employeeDivision to set
	 */
	public void setEmployeeDivision(String employeeDivision) {
		this.employeeDivision = employeeDivision;
	}
	/**
	 * @return the claimParticulars
	 */
	public String getClaimParticulars() {
		return claimParticulars;
	}
	/**
	 * @param claimParticulars the claimParticulars to set
	 */
	public void setClaimParticulars(String claimParticulars) {
		this.claimParticulars = claimParticulars;
	}
	/**
	 * @return the head
	 */
	public String getHead() {
		return head;
	}
	/**
	 * @param head the head to set
	 */
	public void setHead(String head) {
		this.head = head;
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
	/**
	 * @return the decodedStatus
	 */
	public String getDecodedStatus() {
		return decodedStatus;
	}
	/**
	 * @param decodedStatus the decodedStatus to set
	 */
	public void setDecodedStatus(String decodedStatus) {
		this.decodedStatus = decodedStatus;
	}
	/**
	 * @return the claimIdItt
	 */
	public String getClaimIdItt() {
		return claimIdItt;
	}
	/**
	 * @param claimIdItt the claimIdItt to set
	 */
	public void setClaimIdItt(String claimIdItt) {
		this.claimIdItt = claimIdItt;
	}
	/**
	 * @return the applicationIdItt
	 */
	public String getApplicationIdItt() {
		return applicationIdItt;
	}
	/**
	 * @param applicationIdItt the applicationIdItt to set
	 */
	public void setApplicationIdItt(String applicationIdItt) {
		this.applicationIdItt = applicationIdItt;
	}
	/**
	 * @return the employeeNameItt
	 */
	public String getEmployeeNameItt() {
		return employeeNameItt;
	}
	/**
	 * @param employeeNameItt the employeeNameItt to set
	 */
	public void setEmployeeNameItt(String employeeNameItt) {
		this.employeeNameItt = employeeNameItt;
	}
	/**
	 * @return the headItt
	 */
	public String getHeadItt() {
		return headItt;
	}
	/**
	 * @param headItt the headItt to set
	 */
	public void setHeadItt(String headItt) {
		this.headItt = headItt;
	}
	/**
	 * @return the headCodeItt
	 */
	public String getHeadCodeItt() {
		return headCodeItt;
	}
	/**
	 * @param headCodeItt the headCodeItt to set
	 */
	public void setHeadCodeItt(String headCodeItt) {
		this.headCodeItt = headCodeItt;
	}
	/**
	 * @return the decodedStatusItt
	 */
	public String getDecodedStatusItt() {
		return decodedStatusItt;
	}
	/**
	 * @param decodedStatusItt the decodedStatusItt to set
	 */
	public void setDecodedStatusItt(String decodedStatusItt) {
		this.decodedStatusItt = decodedStatusItt;
	}
	/**
	 * @return the expenseBillAmountItt
	 */
	public String getExpenseBillAmountItt() {
		return expenseBillAmountItt;
	}
	/**
	 * @param expenseBillAmountItt the expenseBillAmountItt to set
	 */
	public void setExpenseBillAmountItt(String expenseBillAmountItt) {
		this.expenseBillAmountItt = expenseBillAmountItt;
	}
	/**
	 * @return the reimbursementIteratorlist
	 */
	public ArrayList getReimbursementIteratorlist() {
		return reimbursementIteratorlist;
	}
	/**
	 * @param reimbursementIteratorlist the reimbursementIteratorlist to set
	 */
	public void setReimbursementIteratorlist(ArrayList reimbursementIteratorlist) {
		this.reimbursementIteratorlist = reimbursementIteratorlist;
	}
	/**
	 * @return the prevApproverNameItt
	 */
	public String getPrevApproverNameItt() {
		return prevApproverNameItt;
	}
	/**
	 * @param prevApproverNameItt the prevApproverNameItt to set
	 */
	public void setPrevApproverNameItt(String prevApproverNameItt) {
		this.prevApproverNameItt = prevApproverNameItt;
	}
	/**
	 * @return the prevApproverDateItt
	 */
	public String getPrevApproverDateItt() {
		return prevApproverDateItt;
	}
	/**
	 * @param prevApproverDateItt the prevApproverDateItt to set
	 */
	public void setPrevApproverDateItt(String prevApproverDateItt) {
		this.prevApproverDateItt = prevApproverDateItt;
	}
	/**
	 * @return the prevApproverCommentItt
	 */
	public String getPrevApproverCommentItt() {
		return prevApproverCommentItt;
	}
	/**
	 * @param prevApproverCommentItt the prevApproverCommentItt to set
	 */
	public void setPrevApproverCommentItt(String prevApproverCommentItt) {
		this.prevApproverCommentItt = prevApproverCommentItt;
	}
	/**
	 * @return the expenseClaimAmountItt
	 */
	public String getExpenseClaimAmountItt() {
		return expenseClaimAmountItt;
	}
	/**
	 * @param expenseClaimAmountItt the expenseClaimAmountItt to set
	 */
	public void setExpenseClaimAmountItt(String expenseClaimAmountItt) {
		this.expenseClaimAmountItt = expenseClaimAmountItt;
	}
	/**
	 * @return the expenseProofItt
	 */
	public String getExpenseProofItt() {
		return expenseProofItt;
	}
	/**
	 * @param expenseProofItt the expenseProofItt to set
	 */
	public void setExpenseProofItt(String expenseProofItt) {
		this.expenseProofItt = expenseProofItt;
	}
	/**
	 * @return the proofReferenceIdItt
	 */
	public String getProofReferenceIdItt() {
		return proofReferenceIdItt;
	}
	/**
	 * @param proofReferenceIdItt the proofReferenceIdItt to set
	 */
	public void setProofReferenceIdItt(String proofReferenceIdItt) {
		this.proofReferenceIdItt = proofReferenceIdItt;
	}
	/**
	 * @return the proofDocItt
	 */
	public String getProofDocItt() {
		return proofDocItt;
	}
	/**
	 * @param proofDocItt the proofDocItt to set
	 */
	public void setProofDocItt(String proofDocItt) {
		this.proofDocItt = proofDocItt;
	}
	/**
	 * @return the claimAmountTotal
	 */
	public String getClaimAmountTotal() {
		return claimAmountTotal;
	}
	/**
	 * @param claimAmountTotal the claimAmountTotal to set
	 */
	public void setClaimAmountTotal(String claimAmountTotal) {
		this.claimAmountTotal = claimAmountTotal;
	}
	/**
	 * @return the billAmountTotal
	 */
	public String getBillAmountTotal() {
		return billAmountTotal;
	}
	/**
	 * @param billAmountTotal the billAmountTotal to set
	 */
	public void setBillAmountTotal(String billAmountTotal) {
		this.billAmountTotal = billAmountTotal;
	}
	/**
	 * @return the listProof
	 */
	public ArrayList getListProof() {
		return listProof;
	}
	/**
	 * @param listProof the listProof to set
	 */
	public void setListProof(ArrayList listProof) {
		this.listProof = listProof;
	}
	/**
	 * @return the approverCommentListFlag
	 */
	public boolean isApproverCommentListFlag() {
		return approverCommentListFlag;
	}
	/**
	 * @param approverCommentListFlag the approverCommentListFlag to set
	 */
	public void setApproverCommentListFlag(boolean approverCommentListFlag) {
		this.approverCommentListFlag = approverCommentListFlag;
	}
	/**
	 * @return the commentListFlag
	 */
	public boolean isCommentListFlag() {
		return commentListFlag;
	}
	/**
	 * @param commentListFlag the commentListFlag to set
	 */
	public void setCommentListFlag(boolean commentListFlag) {
		this.commentListFlag = commentListFlag;
	}
	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}
	/**
	 * @return the empNameList
	 */
	public String getEmpNameList() {
		return empNameList;
	}
	/**
	 * @param empNameList the empNameList to set
	 */
	public void setEmpNameList(String empNameList) {
		this.empNameList = empNameList;
	}
	/**
	 * @return the applRefNoList
	 */
	public String getApplRefNoList() {
		return applRefNoList;
	}
	/**
	 * @param applRefNoList the applRefNoList to set
	 */
	public void setApplRefNoList(String applRefNoList) {
		this.applRefNoList = applRefNoList;
	}
	/**
	 * @return the applRefNo
	 */
	public String getApplRefNo() {
		return applRefNo;
	}
	/**
	 * @param applRefNo the applRefNo to set
	 */
	public void setApplRefNo(String applRefNo) {
		this.applRefNo = applRefNo;
	}
	/**
	 * @return the applId
	 */
	public String getApplId() {
		return applId;
	}
	/**
	 * @param applId the applId to set
	 */
	public void setApplId(String applId) {
		this.applId = applId;
	}
	/**
	 * @return the applIdList
	 */
	public String getApplIdList() {
		return applIdList;
	}
	/**
	 * @param applIdList the applIdList to set
	 */
	public void setApplIdList(String applIdList) {
		this.applIdList = applIdList;
	}
	/**
	 * @return the claimDateList
	 */
	public String getClaimDateList() {
		return claimDateList;
	}
	/**
	 * @param claimDateList the claimDateList to set
	 */
	public void setClaimDateList(String claimDateList) {
		this.claimDateList = claimDateList;
	}
	/**
	 * @return the reimbHead
	 */
	public String getReimbHead() {
		return reimbHead;
	}
	/**
	 * @param reimbHead the reimbHead to set
	 */
	public void setReimbHead(String reimbHead) {
		this.reimbHead = reimbHead;
	}
	/**
	 * @return the reimbHeadList
	 */
	public String getReimbHeadList() {
		return reimbHeadList;
	}
	/**
	 * @param reimbHeadList the reimbHeadList to set
	 */
	public void setReimbHeadList(String reimbHeadList) {
		this.reimbHeadList = reimbHeadList;
	}
	/**
	 * @return the reimbHeadCodeList
	 */
	public String getReimbHeadCodeList() {
		return reimbHeadCodeList;
	}
	/**
	 * @param reimbHeadCodeList the reimbHeadCodeList to set
	 */
	public void setReimbHeadCodeList(String reimbHeadCodeList) {
		this.reimbHeadCodeList = reimbHeadCodeList;
	}
	/**
	 * @return the claimAmt
	 */
	public String getClaimAmt() {
		return claimAmt;
	}
	/**
	 * @param claimAmt the claimAmt to set
	 */
	public void setClaimAmt(String claimAmt) {
		this.claimAmt = claimAmt;
	}
	/**
	 * @return the claimAmtHead
	 */
	public String getClaimAmtHead() {
		return claimAmtHead;
	}
	/**
	 * @param claimAmtHead the claimAmtHead to set
	 */
	public void setClaimAmtHead(String claimAmtHead) {
		this.claimAmtHead = claimAmtHead;
	}
	/**
	 * @return the approvedAmt
	 */
	public String getApprovedAmt() {
		return approvedAmt;
	}
	/**
	 * @param approvedAmt the approvedAmt to set
	 */
	public void setApprovedAmt(String approvedAmt) {
		this.approvedAmt = approvedAmt;
	}
	/**
	 * @return the billAmt
	 */
	public String getBillAmt() {
		return billAmt;
	}
	/**
	 * @param billAmt the billAmt to set
	 */
	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}
	/**
	 * @return the approvedBillAmt
	 */
	public String getApprovedBillAmt() {
		return approvedBillAmt;
	}
	/**
	 * @param approvedBillAmt the approvedBillAmt to set
	 */
	public void setApprovedBillAmt(String approvedBillAmt) {
		this.approvedBillAmt = approvedBillAmt;
	}
	/**
	 * @return the proof
	 */
	public String getProof() {
		return proof;
	}
	/**
	 * @param proof the proof to set
	 */
	public void setProof(String proof) {
		this.proof = proof;
	}
	/**
	 * @return the proofHidden
	 */
	public String getProofHidden() {
		return proofHidden;
	}
	/**
	 * @param proofHidden the proofHidden to set
	 */
	public void setProofHidden(String proofHidden) {
		this.proofHidden = proofHidden;
	}
	/**
	 * @return the proofRefNo
	 */
	public String getProofRefNo() {
		return proofRefNo;
	}
	/**
	 * @param proofRefNo the proofRefNo to set
	 */
	public void setProofRefNo(String proofRefNo) {
		this.proofRefNo = proofRefNo;
	}
	/**
	 * @return the proofRefNoHidden
	 */
	public String getProofRefNoHidden() {
		return proofRefNoHidden;
	}
	/**
	 * @param proofRefNoHidden the proofRefNoHidden to set
	 */
	public void setProofRefNoHidden(String proofRefNoHidden) {
		this.proofRefNoHidden = proofRefNoHidden;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the claimParticular
	 */
	public String getClaimParticular() {
		return claimParticular;
	}
	/**
	 * @param claimParticular the claimParticular to set
	 */
	public void setClaimParticular(String claimParticular) {
		this.claimParticular = claimParticular;
	}
	/**
	 * @return the pendingLength
	 */
	public String getPendingLength() {
		return pendingLength;
	}
	/**
	 * @param pendingLength the pendingLength to set
	 */
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	/**
	 * @return the processedLength
	 */
	public String getProcessedLength() {
		return processedLength;
	}
	/**
	 * @param processedLength the processedLength to set
	 */
	public void setProcessedLength(String processedLength) {
		this.processedLength = processedLength;
	}
	/**
	 * @return the approverListFlag
	 */
	public String getApproverListFlag() {
		return approverListFlag;
	}
	/**
	 * @param approverListFlag the approverListFlag to set
	 */
	public void setApproverListFlag(String approverListFlag) {
		this.approverListFlag = approverListFlag;
	}
	/**
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return approverComments;
	}
	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the myPageProcessed
	 */
	public String getMyPageProcessed() {
		return myPageProcessed;
	}
	/**
	 * @param myPageProcessed the myPageProcessed to set
	 */
	public void setMyPageProcessed(String myPageProcessed) {
		this.myPageProcessed = myPageProcessed;
	}
	/**
	 * @return the totalClaimAmt
	 */
	public String getTotalClaimAmt() {
		return totalClaimAmt;
	}
	/**
	 * @param totalClaimAmt the totalClaimAmt to set
	 */
	public void setTotalClaimAmt(String totalClaimAmt) {
		this.totalClaimAmt = totalClaimAmt;
	}
	/**
	 * @return the totalApprovedAmt
	 */
	public String getTotalApprovedAmt() {
		return totalApprovedAmt;
	}
	/**
	 * @param totalApprovedAmt the totalApprovedAmt to set
	 */
	public void setTotalApprovedAmt(String totalApprovedAmt) {
		this.totalApprovedAmt = totalApprovedAmt;
	}
	/**
	 * @return the totalBillAmt
	 */
	public String getTotalBillAmt() {
		return totalBillAmt;
	}
	/**
	 * @param totalBillAmt the totalBillAmt to set
	 */
	public void setTotalBillAmt(String totalBillAmt) {
		this.totalBillAmt = totalBillAmt;
	}
	/**
	 * @return the totalApprovedBillAmt
	 */
	public String getTotalApprovedBillAmt() {
		return totalApprovedBillAmt;
	}
	/**
	 * @param totalApprovedBillAmt the totalApprovedBillAmt to set
	 */
	public void setTotalApprovedBillAmt(String totalApprovedBillAmt) {
		this.totalApprovedBillAmt = totalApprovedBillAmt;
	}
	/**
	 * @return the approverNameList
	 */
	public String getApproverNameList() {
		return approverNameList;
	}
	/**
	 * @param approverNameList the approverNameList to set
	 */
	public void setApproverNameList(String approverNameList) {
		this.approverNameList = approverNameList;
	}
	/**
	 * @return the approverCommentsList
	 */
	public String getApproverCommentsList() {
		return approverCommentsList;
	}
	/**
	 * @param approverCommentsList the approverCommentsList to set
	 */
	public void setApproverCommentsList(String approverCommentsList) {
		this.approverCommentsList = approverCommentsList;
	}
	/**
	 * @return the approverDateList
	 */
	public String getApproverDateList() {
		return approverDateList;
	}
	/**
	 * @param approverDateList the approverDateList to set
	 */
	public void setApproverDateList(String approverDateList) {
		this.approverDateList = approverDateList;
	}
	/**
	 * @return the pendingList
	 */
	public ArrayList getPendingList() {
		return pendingList;
	}
	/**
	 * @param pendingList the pendingList to set
	 */
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	/**
	 * @return the processedList
	 */
	public ArrayList getProcessedList() {
		return processedList;
	}
	/**
	 * @param processedList the processedList to set
	 */
	public void setProcessedList(ArrayList processedList) {
		this.processedList = processedList;
	}
	/**
	 * @return the expenseList
	 */
	public ArrayList getExpenseList() {
		return expenseList;
	}
	/**
	 * @param expenseList the expenseList to set
	 */
	public void setExpenseList(ArrayList expenseList) {
		this.expenseList = expenseList;
	}
	/**
	 * @return the reimburseBalance
	 */
	public String getReimburseBalance() {
		return reimburseBalance;
	}
	/**
	 * @param reimburseBalance the reimburseBalance to set
	 */
	public void setReimburseBalance(String reimburseBalance) {
		this.reimburseBalance = reimburseBalance;
	}
	/**
	 * @return the applIdSearch
	 */
	public String getApplIdSearch() {
		return applIdSearch;
	}
	/**
	 * @param applIdSearch the applIdSearch to set
	 */
	public void setApplIdSearch(String applIdSearch) {
		this.applIdSearch = applIdSearch;
	}
	/**
	 * @return the empIdSearch
	 */
	public String getEmpIdSearch() {
		return empIdSearch;
	}
	/**
	 * @param empIdSearch the empIdSearch to set
	 */
	public void setEmpIdSearch(String empIdSearch) {
		this.empIdSearch = empIdSearch;
	}
	/**
	 * @return the empTokenSearch
	 */
	public String getEmpTokenSearch() {
		return empTokenSearch;
	}
	/**
	 * @param empTokenSearch the empTokenSearch to set
	 */
	public void setEmpTokenSearch(String empTokenSearch) {
		this.empTokenSearch = empTokenSearch;
	}
	/**
	 * @return the empNameSearch
	 */
	public String getEmpNameSearch() {
		return empNameSearch;
	}
	/**
	 * @param empNameSearch the empNameSearch to set
	 */
	public void setEmpNameSearch(String empNameSearch) {
		this.empNameSearch = empNameSearch;
	}
	/**
	 * @return the reimbHeadSearch
	 */
	public String getReimbHeadSearch() {
		return reimbHeadSearch;
	}
	/**
	 * @param reimbHeadSearch the reimbHeadSearch to set
	 */
	public void setReimbHeadSearch(String reimbHeadSearch) {
		this.reimbHeadSearch = reimbHeadSearch;
	}
	/**
	 * @return the reimbHeadNameSearch
	 */
	public String getReimbHeadNameSearch() {
		return reimbHeadNameSearch;
	}
	/**
	 * @param reimbHeadNameSearch the reimbHeadNameSearch to set
	 */
	public void setReimbHeadNameSearch(String reimbHeadNameSearch) {
		this.reimbHeadNameSearch = reimbHeadNameSearch;
	}
	/**
	 * @return the claimDateSearch
	 */
	public String getClaimDateSearch() {
		return claimDateSearch;
	}
	/**
	 * @param claimDateSearch the claimDateSearch to set
	 */
	public void setClaimDateSearch(String claimDateSearch) {
		this.claimDateSearch = claimDateSearch;
	}
	public String getPrevProofAmt() {
		return prevProofAmt;
	}
	public void setPrevProofAmt(String prevProofAmt) {
		this.prevProofAmt = prevProofAmt;
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
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getReimbursementHead() {
		return reimbursementHead;
	}
	public void setReimbursementHead(String reimbursementHead) {
		this.reimbursementHead = reimbursementHead;
	}
	public String getClaimHistoryDate() {
		return claimHistoryDate;
	}
	public void setClaimHistoryDate(String claimHistoryDate) {
		this.claimHistoryDate = claimHistoryDate;
	}
	public String getClaimHistoryAmount() {
		return claimHistoryAmount;
	}
	public void setClaimHistoryAmount(String claimHistoryAmount) {
		this.claimHistoryAmount = claimHistoryAmount;
	}
	public String getClaimHistoryStatus() {
		return claimHistoryStatus;
	}
	public void setClaimHistoryStatus(String claimHistoryStatus) {
		this.claimHistoryStatus = claimHistoryStatus;
	}
	public ArrayList getClaimHistory() {
		return claimHistory;
	}
	public void setClaimHistory(ArrayList claimHistory) {
		this.claimHistory = claimHistory;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
	public String getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public ArrayList getBalanceList() {
		return balanceList;
	}
	public void setBalanceList(ArrayList balanceList) {
		this.balanceList = balanceList;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getReimbursementCrediCode() {
		return reimbursementCrediCode;
	}
	public void setReimbursementCrediCode(String reimbursementCrediCode) {
		this.reimbursementCrediCode = reimbursementCrediCode;
	}
	public String getReimbursementCrediName() {
		return reimbursementCrediName;
	}
	public void setReimbursementCrediName(String reimbursementCrediName) {
		this.reimbursementCrediName = reimbursementCrediName;
	}
	public String getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getProofSrNo() {
		return proofSrNo;
	}
	public void setProofSrNo(String proofSrNo) {
		this.proofSrNo = proofSrNo;
	}
	public String getProofName() {
		return proofName;
	}
	public void setProofName(String proofName) {
		this.proofName = proofName;
	}
	public ArrayList getProofList() {
		return proofList;
	}
	public void setProofList(ArrayList proofList) {
		this.proofList = proofList;
	}
	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}
	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public ArrayList getIttUploadList() {
		return ittUploadList;
	}
	public void setIttUploadList(ArrayList ittUploadList) {
		this.ittUploadList = ittUploadList;
	}
	public String getUploadDoc() {
		return uploadDoc;
	}
	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}
	public String getReimbursementCrediCodeItt() {
		return reimbursementCrediCodeItt;
	}
	public void setReimbursementCrediCodeItt(String reimbursementCrediCodeItt) {
		this.reimbursementCrediCodeItt = reimbursementCrediCodeItt;
	}
	public String getReimbursementCrediNameItt() {
		return reimbursementCrediNameItt;
	}
	public void setReimbursementCrediNameItt(String reimbursementCrediNameItt) {
		this.reimbursementCrediNameItt = reimbursementCrediNameItt;
	}
	public String getClaimAmountItt() {
		return claimAmountItt;
	}
	public void setClaimAmountItt(String claimAmountItt) {
		this.claimAmountItt = claimAmountItt;
	}
	public String getExpenseDateItt() {
		return expenseDateItt;
	}
	public void setExpenseDateItt(String expenseDateItt) {
		this.expenseDateItt = expenseDateItt;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getCheckEdit() {
		return checkEdit;
	}
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getHiddenCreditCode() {
		return hiddenCreditCode;
	}
	public void setHiddenCreditCode(String hiddenCreditCode) {
		this.hiddenCreditCode = hiddenCreditCode;
	}
	public String getUploadDocPath() {
		return uploadDocPath;
	}
	public void setUploadDocPath(String uploadDocPath) {
		this.uploadDocPath = uploadDocPath;
	}
	public String getProofFileName() {
		return proofFileName;
	}
	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}
	public String getUserUploadFileName() {
		return userUploadFileName;
	}
	public void setUserUploadFileName(String userUploadFileName) {
		this.userUploadFileName = userUploadFileName;
	}
	public String getEmpIdItt() {
		return EmpIdItt;
	}
	public void setEmpIdItt(String empIdItt) {
		EmpIdItt = empIdItt;
	}
	public String getApplicationCodeItt() {
		return applicationCodeItt;
	}
	public void setApplicationCodeItt(String applicationCodeItt) {
		this.applicationCodeItt = applicationCodeItt;
	}
	public String getApplicationDateItt() {
		return applicationDateItt;
	}
	public void setApplicationDateItt(String applicationDateItt) {
		this.applicationDateItt = applicationDateItt;
	}
	public String getClaimAmtItt() {
		return claimAmtItt;
	}
	public void setClaimAmtItt(String claimAmtItt) {
		this.claimAmtItt = claimAmtItt;
	}
	public String getStatusItt() {
		return statusItt;
	}
	public void setStatusItt(String statusItt) {
		this.statusItt = statusItt;
	}
	public ArrayList getDraftList() {
		return draftList;
	}
	public void setDraftList(ArrayList draftList) {
		this.draftList = draftList;
	}
	public String getStatusNameItt() {
		return statusNameItt;
	}
	public void setStatusNameItt(String statusNameItt) {
		this.statusNameItt = statusNameItt;
	}
	public String getHiddenApplicationCode() {
		return hiddenApplicationCode;
	}
	public void setHiddenApplicationCode(String hiddenApplicationCode) {
		this.hiddenApplicationCode = hiddenApplicationCode;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getReimCodeItt() {
		return reimCodeItt;
	}
	public void setReimCodeItt(String reimCodeItt) {
		this.reimCodeItt = reimCodeItt;
	}
	public String getCheckReportingStructure() {
		return checkReportingStructure;
	}
	public void setCheckReportingStructure(String checkReportingStructure) {
		this.checkReportingStructure = checkReportingStructure;
	}
	public String getReimbursBalance() {
		return reimbursBalance;
	}
	public void setReimbursBalance(String reimbursBalance) {
		this.reimbursBalance = reimbursBalance;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getLevstatus() {
		return levstatus;
	}
	public void setLevstatus(String levstatus) {
		this.levstatus = levstatus;
	}
	public String getHistoryCreditCode() {
		return historyCreditCode;
	}
	public void setHistoryCreditCode(String historyCreditCode) {
		this.historyCreditCode = historyCreditCode;
	}
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public String getAppSrNo() {
		return appSrNo;
	}
	public void setAppSrNo(String appSrNo) {
		this.appSrNo = appSrNo;
	}
	public String getPrevApproverID() {
		return prevApproverID;
	}
	public void setPrevApproverID(String prevApproverID) {
		this.prevApproverID = prevApproverID;
	}
	public String getPrevApproverName() {
		return prevApproverName;
	}
	public void setPrevApproverName(String prevApproverName) {
		this.prevApproverName = prevApproverName;
	}
	public String getPrevApproverDate() {
		return prevApproverDate;
	}
	public void setPrevApproverDate(String prevApproverDate) {
		this.prevApproverDate = prevApproverDate;
	}
	public String getPrevApproverStatus() {
		return prevApproverStatus;
	}
	public void setPrevApproverStatus(String prevApproverStatus) {
		this.prevApproverStatus = prevApproverStatus;
	}
	public String getPrevApproverComment() {
		return prevApproverComment;
	}
	public void setPrevApproverComment(String prevApproverComment) {
		this.prevApproverComment = prevApproverComment;
	}
	public String getPrevAppCommentListFlag() {
		return prevAppCommentListFlag;
	}
	public void setPrevAppCommentListFlag(String prevAppCommentListFlag) {
		this.prevAppCommentListFlag = prevAppCommentListFlag;
	}
	public String getApplStatus() {
		return applStatus;
	}
	public void setApplStatus(String applStatus) {
		this.applStatus = applStatus;
	}
	public String getCreditNameItt() {
		return creditNameItt;
	}
	public void setCreditNameItt(String creditNameItt) {
		this.creditNameItt = creditNameItt;
	}
	public String getOnHoldBalance() {
		return onHoldBalance;
	}
	public void setOnHoldBalance(String onHoldBalance) {
		this.onHoldBalance = onHoldBalance;
	}
	public String getSysdate() {
		return sysdate;
	}
	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}
	public String getBillsCarriedForward() {
		return billsCarriedForward;
	}
	public void setBillsCarriedForward(String billsCarriedForward) {
		this.billsCarriedForward = billsCarriedForward;
	}
	public String getMinBillAmount() {
		return minBillAmount;
	}
	public void setMinBillAmount(String minBillAmount) {
		this.minBillAmount = minBillAmount;
	}
	public String getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}
	public String getBillsCarriedForwardList() {
		return billsCarriedForwardList;
	}
	public void setBillsCarriedForwardList(String billsCarriedForwardList) {
		this.billsCarriedForwardList = billsCarriedForwardList;
	}
	public String getBillAmountItt() {
		return billAmountItt;
	}
	public void setBillAmountItt(String billAmountItt) {
		this.billAmountItt = billAmountItt;
	}
	public String getClaimHistoryApprovedAmount() {
		return claimHistoryApprovedAmount;
	}
	public void setClaimHistoryApprovedAmount(String claimHistoryApprovedAmount) {
		this.claimHistoryApprovedAmount = claimHistoryApprovedAmount;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the adminStatus
	 */
	public String getAdminStatus() {
		return adminStatus;
	}
	/**
	 * @param adminStatus the adminStatus to set
	 */
	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}
	/**
	 * @return the closedLength
	 */
	public String getClosedLength() {
		return closedLength;
	}
	/**
	 * @param closedLength the closedLength to set
	 */
	public void setClosedLength(String closedLength) {
		this.closedLength = closedLength;
	}
	/**
	 * @return the myPageClosed
	 */
	public String getMyPageClosed() {
		return myPageClosed;
	}
	/**
	 * @param myPageClosed the myPageClosed to set
	 */
	public void setMyPageClosed(String myPageClosed) {
		this.myPageClosed = myPageClosed;
	}
	/**
	 * @return the claimAmtList
	 */
	public String getClaimAmtList() {
		return claimAmtList;
	}
	/**
	 * @param claimAmtList the claimAmtList to set
	 */
	public void setClaimAmtList(String claimAmtList) {
		this.claimAmtList = claimAmtList;
	}
	/**
	 * @return the statusList
	 */
	public String getStatusList() {
		return statusList;
	}
	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(String statusList) {
		this.statusList = statusList;
	}
	/**
	 * @return the disbursementCode
	 */
	public String getDisbursementCode() {
		return disbursementCode;
	}
	/**
	 * @param disbursementCode the disbursementCode to set
	 */
	public void setDisbursementCode(String disbursementCode) {
		this.disbursementCode = disbursementCode;
	}
	/**
	 * @return the disbIdList
	 */
	public String getDisbIdList() {
		return disbIdList;
	}
	/**
	 * @param disbIdList the disbIdList to set
	 */
	public void setDisbIdList(String disbIdList) {
		this.disbIdList = disbIdList;
	}
	/**
	 * @return the disbReferenceId
	 */
	public String getDisbReferenceId() {
		return disbReferenceId;
	}
	/**
	 * @param disbReferenceId the disbReferenceId to set
	 */
	public void setDisbReferenceId(String disbReferenceId) {
		this.disbReferenceId = disbReferenceId;
	}
	/**
	 * @return the accountantComments
	 */
	public String getAccountantComments() {
		return accountantComments;
	}
	/**
	 * @param accountantComments the accountantComments to set
	 */
	public void setAccountantComments(String accountantComments) {
		this.accountantComments = accountantComments;
	}
	/**
	 * @return the disburseList
	 */
	public ArrayList getDisburseList() {
		return disburseList;
	}
	/**
	 * @param disburseList the disburseList to set
	 */
	public void setDisburseList(ArrayList disburseList) {
		this.disburseList = disburseList;
	}
	/**
	 * @return the closedList
	 */
	public ArrayList getClosedList() {
		return closedList;
	}
	/**
	 * @param closedList the closedList to set
	 */
	public void setClosedList(ArrayList closedList) {
		this.closedList = closedList;
	}
	/**
	 * @return the disbAmount
	 */
	public String getDisbAmount() {
		return disbAmount;
	}
	/**
	 * @param disbAmount the disbAmount to set
	 */
	public void setDisbAmount(String disbAmount) {
		this.disbAmount = disbAmount;
	}
	/**
	 * @return the disbDate
	 */
	public String getDisbDate() {
		return disbDate;
	}
	/**
	 * @param disbDate the disbDate to set
	 */
	public void setDisbDate(String disbDate) {
		this.disbDate = disbDate;
	}
	/**
	 * @return the payMode
	 */
	public String getPayMode() {
		return payMode;
	}
	/**
	 * @param payMode the payMode to set
	 */
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	/**
	 * @return the chequeDate
	 */
	public String getChequeDate() {
		return chequeDate;
	}
	/**
	 * @param chequeDate the chequeDate to set
	 */
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}
	/**
	 * @return the chequeNo
	 */
	public String getChequeNo() {
		return chequeNo;
	}
	/**
	 * @param chequeNo the chequeNo to set
	 */
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the payModeHashmap
	 */
	public HashMap<String, String> getPayModeHashmap() {
		return payModeHashmap;
	}
	/**
	 * @param payModeHashmap the payModeHashmap to set
	 */
	public void setPayModeHashmap(HashMap<String, String> payModeHashmap) {
		this.payModeHashmap = payModeHashmap;
	}
	
	 
}
