/**
 * 
 */
package org.paradyne.bean.Loan;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author tarunc
 *
 */
public class LoanApproval extends BeanBase {

	private String loanAppStatus;
	private String apprFlag;
	private String statusIterator;
	private String noData;
	private ArrayList list;
	private String loanApplCode;
	private String empCode;
	private String empToken;
	private String empName;
	private String appliedDate;
	private String level;
	private String status;
	private String loanAmount;
	private String loanCode;
	private String empId;
	private String comment;
	private String approver;
	private String hiddenStatus;
	
	private String backButtonFlag = "";
	private String adminCode = "";
	private String accountantCode = "";
	private String forwardApprFlag = "";

	private String flag="";
	public String headerName="";
	private ArrayList listDraft=null;
	private ArrayList listReject=null;
	private ArrayList listApprove=null;
	private String myPage= "";
	private String myPage1= "";
	private String myPage2= "";
	public String buttonName="";
	
	private String ittEmployeeToken= "";
	private String ittloanApplCode= "";
	private String ittEmployee= "";
	private String ittApplicationDate="";
	
	
	
	private String branchCode;
	private String branchName;
	private String deptCode;
	private String deptName;
	private String desgCode;
	private String desgName;
	private String confirmationDate;
	private String gradeCode;
	private String grade;

	private String loanType;
	private String applicationdate;

	private String recommendedByCode = "";
	private String recommendedByToken;
	private String recommendedByName;
	private String firstGuarantorCode = "";
	private String firstGuarantorToken;
	private String firstGuarantorName;
	private String secondGuarantorCode = "";
	private String secondGuarantorToken;
	private String secondGuarantorName;
	private String applicationStatus;
	private String hiddenApplicationStatus;
	private String approverCode;
	private String grossSalary;
	private String year;
	private String loanPurpose;
	private String loanPurposeValue;
	private String minSanctionLimit ;
	private String maxSanctionLimit;
	private String minSanctionAmt;
	private String maxSanctionAmt;
	private String loanEligibility;
	private String pfBalance;
	private String loanSubType;
	private String hiddenLoanSubType;
	private String pfLoanCode;
	
	
	private String isApprove;
	private String appCommentFlag;
	private String approverName;
	private String approvedDate;
	private String approvedStatus;
	private String approverComment;
	
	
	HashMap loanTypeHashmap;
	HashMap purposeList;
	private ArrayList commentList;
	
	
	/*  parameters for  calculate EMI */
	
	private String sanctionAmount;
	//private String sanctionDate;
	private String paymentDate;
	private String interestRate;
	private String interestType;
	private String installmentNumber;
	private String installmentNumberFlat;
	private String monthlyPrincAmount;
	private String emiAmount;
	private String startingDate;
	private String view="false";
	private String monthYear;
	private String principalAmt ;
	private String interestAmt;
	private String installmentAmt;
	private String installmentFlag="false";
	private String hiddenCalType="";
	
	private String totalPrincipalAmt;
	private String totalInstallmenteAmt;
	private String totalInterestAmt;
	private String interestTypeHidden;
	private String balancePrincipalAmt;
	private  String isPaid;
	private String paidFlag="false";
	private String CheckFlag;
	private String installmentPaidFlag="false" ;
	private String noOfPaidInstallments;
	private ArrayList<Object>  installmentList ;
	
	//Added by Prashant Shinde
	private String applicantComment;
	
	private String listType = "";
	
	ArrayList draftLoanApplIteratorList = null;
	boolean draftLoanApplListLength = false;
	private String hiddenCode = "";
	private String hiddenChechfrmId = "";
	private String initiatorCode ="";
	private String loanAllowedLimit = "";
	private String expectedEmi = "";
	private String tenure = "";
	private String initiatorDate = "";
	private String initiatorToken = "";
	private String initiatorName = "";
	
	private String myPageInProcess = "";
	ArrayList inProcessLoanApplIteratorList = null;
	private boolean inProcessLoanApplListLength = false;
	
	boolean approverCommentsFlag = false;
	private String apprLoanAmount = "";
	
	public String trackingNo="";
	public String divCode="";
	public String divName="";
	
	public ArrayList listComment=null;
	private String  ittApproverName="";
	private String  ittComments="";
	public String ittStatus="";
	public String ittApprAmount="";
	
	private String searchEmpCode = "";
	private String  searchEmpName = "";
	private String  searchEmpToken = "";
	
	private ArrayList approverList;
	boolean hrFlag = false;
	private String hiddenLoginfrmId = "";
	private String expEmpValue = "";
	
	private String pendingLength="false";
	ArrayList pendingIteratorList = null;
	private boolean pendingListLength = false;
	
	private String myPageApproved = "";
	ArrayList approvedIteratorList = null;
	private boolean approvedListLength = false;
	
	private String source="";
	private String myPageRejected = "";
	ArrayList rejectedIteratorList = null;
	private boolean rejectedListLength = false;
	/**
	 * @return the rejectedIteratorList
	 */
	public ArrayList getRejectedIteratorList() {
		return rejectedIteratorList;
	}

	/**
	 * @param rejectedIteratorList the rejectedIteratorList to set
	 */
	public void setRejectedIteratorList(ArrayList rejectedIteratorList) {
		this.rejectedIteratorList = rejectedIteratorList;
	}

	/**
	 * @return the rejectedListLength
	 */
	public boolean isRejectedListLength() {
		return rejectedListLength;
	}

	/**
	 * @param rejectedListLength the rejectedListLength to set
	 */
	public void setRejectedListLength(boolean rejectedListLength) {
		this.rejectedListLength = rejectedListLength;
	}

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

	public String getExpEmpValue() {
		return expEmpValue;
	}

	public void setExpEmpValue(String expEmpValue) {
		this.expEmpValue = expEmpValue;
	}

	public String getHiddenLoginfrmId() {
		return hiddenLoginfrmId;
	}

	public void setHiddenLoginfrmId(String hiddenLoginfrmId) {
		this.hiddenLoginfrmId = hiddenLoginfrmId;
	}

	public boolean isHrFlag() {
		return hrFlag;
	}

	public void setHrFlag(boolean hrFlag) {
		this.hrFlag = hrFlag;
	}

	public ArrayList getApproverList() {
		return approverList;
	}

	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	public String getSearchEmpCode() {
		return searchEmpCode;
	}

	public void setSearchEmpCode(String searchEmpCode) {
		this.searchEmpCode = searchEmpCode;
	}

	public String getSearchEmpName() {
		return searchEmpName;
	}

	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}

	public String getSearchEmpToken() {
		return searchEmpToken;
	}

	public void setSearchEmpToken(String searchEmpToken) {
		this.searchEmpToken = searchEmpToken;
	}

	public ArrayList getListComment() {
		return listComment;
	}

	public void setListComment(ArrayList listComment) {
		this.listComment = listComment;
	}

	public String getIttApproverName() {
		return ittApproverName;
	}

	public void setIttApproverName(String ittApproverName) {
		this.ittApproverName = ittApproverName;
	}

	public String getIttComments() {
		return ittComments;
	}

	public void setIttComments(String ittComments) {
		this.ittComments = ittComments;
	}

	public String getIttStatus() {
		return ittStatus;
	}

	public void setIttStatus(String ittStatus) {
		this.ittStatus = ittStatus;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getMyPage1() {
		return myPage1;
	}

	public void setMyPage1(String myPage1) {
		this.myPage1 = myPage1;
	}

	public String getMyPage2() {
		return myPage2;
	}

	public void setMyPage2(String myPage2) {
		this.myPage2 = myPage2;
	}

	public ArrayList getListDraft() {
		return listDraft;
	}

	public void setListDraft(ArrayList listDraft) {
		this.listDraft = listDraft;
	}

	public ArrayList getListReject() {
		return listReject;
	}

	public void setListReject(ArrayList listReject) {
		this.listReject = listReject;
	}

	public ArrayList getListApprove() {
		return listApprove;
	}

	public void setListApprove(ArrayList listApprove) {
		this.listApprove = listApprove;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getForwardApprFlag() {
		return forwardApprFlag;
	}

	public void setForwardApprFlag(String forwardApprFlag) {
		this.forwardApprFlag = forwardApprFlag;
	}

	public String getHiddenStatus() {
		return hiddenStatus;
	}

	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApprFlag() {
		return apprFlag;
	}

	public void setApprFlag(String apprFlag) {
		this.apprFlag = apprFlag;
	}

	public String getStatusIterator() {
		return statusIterator;
	}

	public void setStatusIterator(String statusIterator) {
		this.statusIterator = statusIterator;
	}

	public String getLoanAppStatus() {
		return loanAppStatus;
	}

	public void setLoanAppStatus(String loanAppStatus) {
		this.loanAppStatus = loanAppStatus;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public String getLoanApplCode() {
		return loanApplCode;
	}

	public void setLoanApplCode(String loanApplCode) {
		this.loanApplCode = loanApplCode;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBackButtonFlag() {
		return backButtonFlag;
	}

	public void setBackButtonFlag(String backButtonFlag) {
		this.backButtonFlag = backButtonFlag;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getAccountantCode() {
		return accountantCode;
	}

	public void setAccountantCode(String accountantCode) {
		this.accountantCode = accountantCode;
	}

	public String getIttEmployeeToken() {
		return ittEmployeeToken;
	}

	public void setIttEmployeeToken(String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}

	public String getIttloanApplCode() {
		return ittloanApplCode;
	}

	public void setIttloanApplCode(String ittloanApplCode) {
		this.ittloanApplCode = ittloanApplCode;
	}

	public String getIttEmployee() {
		return ittEmployee;
	}

	public void setIttEmployee(String ittEmployee) {
		this.ittEmployee = ittEmployee;
	}

	public String getIttApplicationDate() {
		return ittApplicationDate;
	}

	public void setIttApplicationDate(String ittApplicationDate) {
		this.ittApplicationDate = ittApplicationDate;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesgCode() {
		return desgCode;
	}

	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getApplicationdate() {
		return applicationdate;
	}

	public void setApplicationdate(String applicationdate) {
		this.applicationdate = applicationdate;
	}

	public String getRecommendedByCode() {
		return recommendedByCode;
	}

	public void setRecommendedByCode(String recommendedByCode) {
		this.recommendedByCode = recommendedByCode;
	}

	public String getRecommendedByToken() {
		return recommendedByToken;
	}

	public void setRecommendedByToken(String recommendedByToken) {
		this.recommendedByToken = recommendedByToken;
	}

	public String getRecommendedByName() {
		return recommendedByName;
	}

	public void setRecommendedByName(String recommendedByName) {
		this.recommendedByName = recommendedByName;
	}

	public String getFirstGuarantorCode() {
		return firstGuarantorCode;
	}

	public void setFirstGuarantorCode(String firstGuarantorCode) {
		this.firstGuarantorCode = firstGuarantorCode;
	}

	public String getFirstGuarantorToken() {
		return firstGuarantorToken;
	}

	public void setFirstGuarantorToken(String firstGuarantorToken) {
		this.firstGuarantorToken = firstGuarantorToken;
	}

	public String getFirstGuarantorName() {
		return firstGuarantorName;
	}

	public void setFirstGuarantorName(String firstGuarantorName) {
		this.firstGuarantorName = firstGuarantorName;
	}

	public String getSecondGuarantorCode() {
		return secondGuarantorCode;
	}

	public void setSecondGuarantorCode(String secondGuarantorCode) {
		this.secondGuarantorCode = secondGuarantorCode;
	}

	public String getSecondGuarantorToken() {
		return secondGuarantorToken;
	}

	public void setSecondGuarantorToken(String secondGuarantorToken) {
		this.secondGuarantorToken = secondGuarantorToken;
	}

	public String getSecondGuarantorName() {
		return secondGuarantorName;
	}

	public void setSecondGuarantorName(String secondGuarantorName) {
		this.secondGuarantorName = secondGuarantorName;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getHiddenApplicationStatus() {
		return hiddenApplicationStatus;
	}

	public void setHiddenApplicationStatus(String hiddenApplicationStatus) {
		this.hiddenApplicationStatus = hiddenApplicationStatus;
	}

	public String getApproverCode() {
		return approverCode;
	}

	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}

	public String getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(String grossSalary) {
		this.grossSalary = grossSalary;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getLoanPurposeValue() {
		return loanPurposeValue;
	}

	public void setLoanPurposeValue(String loanPurposeValue) {
		this.loanPurposeValue = loanPurposeValue;
	}

	public String getMinSanctionLimit() {
		return minSanctionLimit;
	}

	public void setMinSanctionLimit(String minSanctionLimit) {
		this.minSanctionLimit = minSanctionLimit;
	}

	public String getMaxSanctionLimit() {
		return maxSanctionLimit;
	}

	public void setMaxSanctionLimit(String maxSanctionLimit) {
		this.maxSanctionLimit = maxSanctionLimit;
	}

	public String getMinSanctionAmt() {
		return minSanctionAmt;
	}

	public void setMinSanctionAmt(String minSanctionAmt) {
		this.minSanctionAmt = minSanctionAmt;
	}

	public String getMaxSanctionAmt() {
		return maxSanctionAmt;
	}

	public void setMaxSanctionAmt(String maxSanctionAmt) {
		this.maxSanctionAmt = maxSanctionAmt;
	}

	public String getLoanEligibility() {
		return loanEligibility;
	}

	public void setLoanEligibility(String loanEligibility) {
		this.loanEligibility = loanEligibility;
	}

	public String getPfBalance() {
		return pfBalance;
	}

	public void setPfBalance(String pfBalance) {
		this.pfBalance = pfBalance;
	}

	public String getLoanSubType() {
		return loanSubType;
	}

	public void setLoanSubType(String loanSubType) {
		this.loanSubType = loanSubType;
	}

	public String getHiddenLoanSubType() {
		return hiddenLoanSubType;
	}

	public void setHiddenLoanSubType(String hiddenLoanSubType) {
		this.hiddenLoanSubType = hiddenLoanSubType;
	}

	public String getPfLoanCode() {
		return pfLoanCode;
	}

	public void setPfLoanCode(String pfLoanCode) {
		this.pfLoanCode = pfLoanCode;
	}

	public String getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	public String getAppCommentFlag() {
		return appCommentFlag;
	}

	public void setAppCommentFlag(String appCommentFlag) {
		this.appCommentFlag = appCommentFlag;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getApproverComment() {
		return approverComment;
	}

	public void setApproverComment(String approverComment) {
		this.approverComment = approverComment;
	}

	public HashMap getLoanTypeHashmap() {
		return loanTypeHashmap;
	}

	public void setLoanTypeHashmap(HashMap loanTypeHashmap) {
		this.loanTypeHashmap = loanTypeHashmap;
	}

	public HashMap getPurposeList() {
		return purposeList;
	}

	public void setPurposeList(HashMap purposeList) {
		this.purposeList = purposeList;
	}

	public ArrayList getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList commentList) {
		this.commentList = commentList;
	}

	public String getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getInterestType() {
		return interestType;
	}

	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}

	public String getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(String installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

	public String getInstallmentNumberFlat() {
		return installmentNumberFlat;
	}

	public void setInstallmentNumberFlat(String installmentNumberFlat) {
		this.installmentNumberFlat = installmentNumberFlat;
	}

	public String getMonthlyPrincAmount() {
		return monthlyPrincAmount;
	}

	public void setMonthlyPrincAmount(String monthlyPrincAmount) {
		this.monthlyPrincAmount = monthlyPrincAmount;
	}

	public String getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(String emiAmount) {
		this.emiAmount = emiAmount;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public String getPrincipalAmt() {
		return principalAmt;
	}

	public void setPrincipalAmt(String principalAmt) {
		this.principalAmt = principalAmt;
	}

	public String getInterestAmt() {
		return interestAmt;
	}

	public void setInterestAmt(String interestAmt) {
		this.interestAmt = interestAmt;
	}

	public String getInstallmentAmt() {
		return installmentAmt;
	}

	public void setInstallmentAmt(String installmentAmt) {
		this.installmentAmt = installmentAmt;
	}

	public String getInstallmentFlag() {
		return installmentFlag;
	}

	public void setInstallmentFlag(String installmentFlag) {
		this.installmentFlag = installmentFlag;
	}

	public String getHiddenCalType() {
		return hiddenCalType;
	}

	public void setHiddenCalType(String hiddenCalType) {
		this.hiddenCalType = hiddenCalType;
	}

	public String getTotalPrincipalAmt() {
		return totalPrincipalAmt;
	}

	public void setTotalPrincipalAmt(String totalPrincipalAmt) {
		this.totalPrincipalAmt = totalPrincipalAmt;
	}

	public String getTotalInstallmenteAmt() {
		return totalInstallmenteAmt;
	}

	public void setTotalInstallmenteAmt(String totalInstallmenteAmt) {
		this.totalInstallmenteAmt = totalInstallmenteAmt;
	}

	public String getTotalInterestAmt() {
		return totalInterestAmt;
	}

	public void setTotalInterestAmt(String totalInterestAmt) {
		this.totalInterestAmt = totalInterestAmt;
	}

	public String getInterestTypeHidden() {
		return interestTypeHidden;
	}

	public void setInterestTypeHidden(String interestTypeHidden) {
		this.interestTypeHidden = interestTypeHidden;
	}

	public String getBalancePrincipalAmt() {
		return balancePrincipalAmt;
	}

	public void setBalancePrincipalAmt(String balancePrincipalAmt) {
		this.balancePrincipalAmt = balancePrincipalAmt;
	}

	public String getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}

	public String getPaidFlag() {
		return paidFlag;
	}

	public void setPaidFlag(String paidFlag) {
		this.paidFlag = paidFlag;
	}

	public String getCheckFlag() {
		return CheckFlag;
	}

	public void setCheckFlag(String checkFlag) {
		CheckFlag = checkFlag;
	}

	public String getInstallmentPaidFlag() {
		return installmentPaidFlag;
	}

	public void setInstallmentPaidFlag(String installmentPaidFlag) {
		this.installmentPaidFlag = installmentPaidFlag;
	}

	public String getNoOfPaidInstallments() {
		return noOfPaidInstallments;
	}

	public void setNoOfPaidInstallments(String noOfPaidInstallments) {
		this.noOfPaidInstallments = noOfPaidInstallments;
	}

	public ArrayList<Object> getInstallmentList() {
		return installmentList;
	}

	public void setInstallmentList(ArrayList<Object> installmentList) {
		this.installmentList = installmentList;
	}

	public String getApplicantComment() {
		return applicantComment;
	}

	public void setApplicantComment(String applicantComment) {
		this.applicantComment = applicantComment;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public ArrayList getDraftLoanApplIteratorList() {
		return draftLoanApplIteratorList;
	}

	public void setDraftLoanApplIteratorList(ArrayList draftLoanApplIteratorList) {
		this.draftLoanApplIteratorList = draftLoanApplIteratorList;
	}

	public boolean isDraftLoanApplListLength() {
		return draftLoanApplListLength;
	}

	public void setDraftLoanApplListLength(boolean draftLoanApplListLength) {
		this.draftLoanApplListLength = draftLoanApplListLength;
	}

	public String getHiddenCode() {
		return hiddenCode;
	}

	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}

	public String getHiddenChechfrmId() {
		return hiddenChechfrmId;
	}

	public void setHiddenChechfrmId(String hiddenChechfrmId) {
		this.hiddenChechfrmId = hiddenChechfrmId;
	}

	public String getInitiatorCode() {
		return initiatorCode;
	}

	public void setInitiatorCode(String initiatorCode) {
		this.initiatorCode = initiatorCode;
	}

	public String getLoanAllowedLimit() {
		return loanAllowedLimit;
	}

	public void setLoanAllowedLimit(String loanAllowedLimit) {
		this.loanAllowedLimit = loanAllowedLimit;
	}

	public String getExpectedEmi() {
		return expectedEmi;
	}

	public void setExpectedEmi(String expectedEmi) {
		this.expectedEmi = expectedEmi;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getInitiatorDate() {
		return initiatorDate;
	}

	public void setInitiatorDate(String initiatorDate) {
		this.initiatorDate = initiatorDate;
	}

	public String getInitiatorToken() {
		return initiatorToken;
	}

	public void setInitiatorToken(String initiatorToken) {
		this.initiatorToken = initiatorToken;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public String getMyPageInProcess() {
		return myPageInProcess;
	}

	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}

	public ArrayList getInProcessLoanApplIteratorList() {
		return inProcessLoanApplIteratorList;
	}

	public void setInProcessLoanApplIteratorList(
			ArrayList inProcessLoanApplIteratorList) {
		this.inProcessLoanApplIteratorList = inProcessLoanApplIteratorList;
	}

	public boolean isInProcessLoanApplListLength() {
		return inProcessLoanApplListLength;
	}

	public void setInProcessLoanApplListLength(boolean inProcessLoanApplListLength) {
		this.inProcessLoanApplListLength = inProcessLoanApplListLength;
	}

	public boolean isApproverCommentsFlag() {
		return approverCommentsFlag;
	}

	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}

	public String getApprLoanAmount() {
		return apprLoanAmount;
	}

	public void setApprLoanAmount(String apprLoanAmount) {
		this.apprLoanAmount = apprLoanAmount;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getIttApprAmount() {
		return ittApprAmount;
	}

	public void setIttApprAmount(String ittApprAmount) {
		this.ittApprAmount = ittApprAmount;
	}

	/**
	 * @return the pendingIteratorList
	 */
	public ArrayList getPendingIteratorList() {
		return pendingIteratorList;
	}

	/**
	 * @param pendingIteratorList the pendingIteratorList to set
	 */
	public void setPendingIteratorList(ArrayList pendingIteratorList) {
		this.pendingIteratorList = pendingIteratorList;
	}

	/**
	 * @return the pendingListLength
	 */
	public boolean isPendingListLength() {
		return pendingListLength;
	}

	/**
	 * @param pendingListLength the pendingListLength to set
	 */
	public void setPendingListLength(boolean pendingListLength) {
		this.pendingListLength = pendingListLength;
	}

	/**
	 * @return the myPageApproved
	 */
	public String getMyPageApproved() {
		return myPageApproved;
	}

	/**
	 * @param myPageApproved the myPageApproved to set
	 */
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	/**
	 * @return the approvedIteratorList
	 */
	public ArrayList getApprovedIteratorList() {
		return approvedIteratorList;
	}

	/**
	 * @param approvedIteratorList the approvedIteratorList to set
	 */
	public void setApprovedIteratorList(ArrayList approvedIteratorList) {
		this.approvedIteratorList = approvedIteratorList;
	}

	/**
	 * @return the approvedListLength
	 */
	public boolean isApprovedListLength() {
		return approvedListLength;
	}

	/**
	 * @param approvedListLength the approvedListLength to set
	 */
	public void setApprovedListLength(boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}

	/**
	 * @return the myPageRejected
	 */
	public String getMyPageRejected() {
		return myPageRejected;
	}

	/**
	 * @param myPageRejected the myPageRejected to set
	 */
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
}
