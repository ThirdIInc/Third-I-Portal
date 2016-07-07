package org.paradyne.bean.reimbursement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ReimbManagerApproval extends BeanBase {
	
	private String myPage;
	private String listType;
	private String claimId="";
	private String applicationId="";
	private String applicationDate="";
	private String employeeToken="";
	private String approverId="";
	private String approverName="";
	private String employeeId="";
	private String employeeName="";
	private String employeeDesignation="";
	private String employeeBranch="";
	private String employeeGrade="";
	private String claimDate="";
	private String employeeDivision="";
	private String reimbHead="";
	private String claimParticulars="";
	private String head="";
	private String claimAmount="";
	private String status="";
	private String decodedStatus="";
	private String approverComments="";
	private String level="";
	private String creditCode="";
	
	private String claimIdItt="";
	private String applicationIdItt="";
	private String applicationDateItt="";
	private String employeeNameItt="";
	private String headItt="";
	private String headCodeItt="";
	private String claimAmountItt="";
	private String statusItt="";
	private String decodedStatusItt="";
	private String expenseBillAmountItt="";
	
	ArrayList reimbursementIteratorlist;
	
	private String prevApproverNameItt;
	private String prevApproverDateItt;
	private String prevApproverCommentItt;
	ArrayList approverCommentList;
	
	private String expenseDateItt;
	private String expenseClaimAmountItt;
	private String expenseProofItt;
	private String proofReferenceIdItt;
	private String proofDocItt;
	private String claimAmountTotal;
	private String billAmountTotal="";
	
	ArrayList listProof;
	ArrayList expenseList;
	
	private String reimburseBalance="";
	private String billsCarriedForward="";
	private ArrayList preApprovedList = null;
	private ArrayList preApprovedProofList = null;
	private String preApprovedListFlag ="false";
	private String prevProofRefNo ="";
	private String prevProofFileName ="";
	private String prevProofAmt="";
	
	private boolean approverCommentListFlag = false ; 
	private boolean commentListFlag = false ; 

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDecodedStatus() {
		return decodedStatus;
	}

	public void setDecodedStatus(String decodedStatus) {
		this.decodedStatus = decodedStatus;
	}

	public String getApplicationIdItt() {
		return applicationIdItt;
	}

	public void setApplicationIdItt(String applicationIdItt) {
		this.applicationIdItt = applicationIdItt;
	}

	public String getApplicationDateItt() {
		return applicationDateItt;
	}

	public void setApplicationDateItt(String applicationDateItt) {
		this.applicationDateItt = applicationDateItt;
	}

	public String getEmployeeNameItt() {
		return employeeNameItt;
	}

	public void setEmployeeNameItt(String employeeNameItt) {
		this.employeeNameItt = employeeNameItt;
	}

	public String getHeadItt() {
		return headItt;
	}

	public void setHeadItt(String headItt) {
		this.headItt = headItt;
	}

	public String getClaimAmountItt() {
		return claimAmountItt;
	}

	public void setClaimAmountItt(String claimAmountItt) {
		this.claimAmountItt = claimAmountItt;
	}

	public String getStatusItt() {
		return statusItt;
	}

	public void setStatusItt(String statusItt) {
		this.statusItt = statusItt;
	}

	public String getDecodedStatusItt() {
		return decodedStatusItt;
	}

	public void setDecodedStatusItt(String decodedStatusItt) {
		this.decodedStatusItt = decodedStatusItt;
	}

	public ArrayList getReimbursementIteratorlist() {
		return reimbursementIteratorlist;
	}

	public void setReimbursementIteratorlist(ArrayList reimbursementIteratorlist) {
		this.reimbursementIteratorlist = reimbursementIteratorlist;
	}

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getClaimIdItt() {
		return claimIdItt;
	}

	public void setClaimIdItt(String claimIdItt) {
		this.claimIdItt = claimIdItt;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeeBranch() {
		return employeeBranch;
	}

	public void setEmployeeBranch(String employeeBranch) {
		this.employeeBranch = employeeBranch;
	}

	public String getEmployeeGrade() {
		return employeeGrade;
	}

	public void setEmployeeGrade(String employeeGrade) {
		this.employeeGrade = employeeGrade;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimParticulars() {
		return claimParticulars;
	}

	public void setClaimParticulars(String claimParticulars) {
		this.claimParticulars = claimParticulars;
	}

	public String getEmployeeDivision() {
		return employeeDivision;
	}

	public void setEmployeeDivision(String employeeDivision) {
		this.employeeDivision = employeeDivision;
	}

	public String getReimbHead() {
		return reimbHead;
	}

	public void setReimbHead(String reimbHead) {
		this.reimbHead = reimbHead;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}

	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	public String getPrevApproverNameItt() {
		return prevApproverNameItt;
	}

	public void setPrevApproverNameItt(String prevApproverNameItt) {
		this.prevApproverNameItt = prevApproverNameItt;
	}

	public String getPrevApproverDateItt() {
		return prevApproverDateItt;
	}

	public void setPrevApproverDateItt(String prevApproverDateItt) {
		this.prevApproverDateItt = prevApproverDateItt;
	}

	public String getPrevApproverCommentItt() {
		return prevApproverCommentItt;
	}

	public void setPrevApproverCommentItt(String prevApproverCommentItt) {
		this.prevApproverCommentItt = prevApproverCommentItt;
	}

	public boolean isApproverCommentListFlag() {
		return approverCommentListFlag;
	}

	public void setApproverCommentListFlag(boolean approverCommentListFlag) {
		this.approverCommentListFlag = approverCommentListFlag;
	}

	public String getExpenseDateItt() {
		return expenseDateItt;
	}

	public void setExpenseDateItt(String expenseDateItt) {
		this.expenseDateItt = expenseDateItt;
	}

	public String getExpenseClaimAmountItt() {
		return expenseClaimAmountItt;
	}

	public void setExpenseClaimAmountItt(String expenseClaimAmountItt) {
		this.expenseClaimAmountItt = expenseClaimAmountItt;
	}

	public String getExpenseProofItt() {
		return expenseProofItt;
	}

	public void setExpenseProofItt(String expenseProofItt) {
		this.expenseProofItt = expenseProofItt;
	}

	public ArrayList getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(ArrayList expenseList) {
		this.expenseList = expenseList;
	}

	public String getProofReferenceIdItt() {
		return proofReferenceIdItt;
	}

	public void setProofReferenceIdItt(String proofReferenceIdItt) {
		this.proofReferenceIdItt = proofReferenceIdItt;
	}

	public String getProofDocItt() {
		return proofDocItt;
	}

	public void setProofDocItt(String proofDocItt) {
		this.proofDocItt = proofDocItt;
	}

	public ArrayList getListProof() {
		return listProof;
	}

	public void setListProof(ArrayList listProof) {
		this.listProof = listProof;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public boolean isCommentListFlag() {
		return commentListFlag;
	}

	public void setCommentListFlag(boolean commentListFlag) {
		this.commentListFlag = commentListFlag;
	}

	public String getClaimAmountTotal() {
		return claimAmountTotal;
	}

	public void setClaimAmountTotal(String claimAmountTotal) {
		this.claimAmountTotal = claimAmountTotal;
	}

	public String getExpenseBillAmountItt() {
		return expenseBillAmountItt;
	}

	public void setExpenseBillAmountItt(String expenseBillAmountItt) {
		this.expenseBillAmountItt = expenseBillAmountItt;
	}

	public String getBillAmountTotal() {
		return billAmountTotal;
	}

	public void setBillAmountTotal(String billAmountTotal) {
		this.billAmountTotal = billAmountTotal;
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

	public String getHeadCodeItt() {
		return headCodeItt;
	}

	public void setHeadCodeItt(String headCodeItt) {
		this.headCodeItt = headCodeItt;
	}

}
