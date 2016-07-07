package org.paradyne.bean.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class LoanApplication extends BeanBase {

	private String source ="";
	private String loanApplCode = "";
	private String empToken;
	private String empName;
	private String empCode = "";
	private String branchCode;
	private String branchName;
	private String deptCode;
	private String deptName;
	private String desgCode;
	private String desgName;
	private String confirmationDate;
	private String gradeCode;
	private String grade;
	private String loanCode;
	private String loanType;
	private String applicationdate;
	private String loanAmount;
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
	private String level;
	
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
	private String myPage = "";
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
	private String backButtonFlag = "";
	public String trackingNo="";
	public String divCode="";
	public String divName="";
	private ArrayList approverList;
	private String hiddenCalfrmId = "";
	
	boolean approverCommentFlag  = false;
	
	private String apprName = "";
	private String apprComments = "";
	private String apprDate = "";
	private String apprStatus = "";
	
	private ArrayList listComment;
	private String apprAmount = "";
	
	
	public String getApprAmount() {
		return apprAmount;
	}
	public void setApprAmount(String apprAmount) {
		this.apprAmount = apprAmount;
	}
	public boolean isApproverCommentFlag() {
		return approverCommentFlag;
	}
	public void setApproverCommentFlag(boolean approverCommentFlag) {
		this.approverCommentFlag = approverCommentFlag;
	}
	
	
	
	public ArrayList getListComment() {
		return listComment;
	}
	public void setListComment(ArrayList listComment) {
		this.listComment = listComment;
	}
	public String getApprName() {
		return apprName;
	}
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprStatus() {
		return apprStatus;
	}
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}
	public ArrayList getApproverList() {
		return approverList;
	}
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getApprLoanAmount() {
		return apprLoanAmount;
	}
	public void setApprLoanAmount(String apprLoanAmount) {
		this.apprLoanAmount = apprLoanAmount;
	}
	public String getBackButtonFlag() {
		return backButtonFlag;
	}
	public void setBackButtonFlag(String backButtonFlag) {
		this.backButtonFlag = backButtonFlag;
	}
	public boolean isApproverCommentsFlag() {
		return approverCommentsFlag;
	}
	public void setApproverCommentsFlag(boolean approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
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
	public String getInitiatorCode() {
		return initiatorCode;
	}
	public void setInitiatorCode(String initiatorCode) {
		this.initiatorCode = initiatorCode;
	}
	public String getHiddenChechfrmId() {
		return hiddenChechfrmId;
	}
	public void setHiddenChechfrmId(String hiddenChechfrmId) {
		this.hiddenChechfrmId = hiddenChechfrmId;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
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
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public ArrayList<Object> getInstallmentList() {
		return installmentList;
	}
	public void setInstallmentList(ArrayList<Object> installmentList) {
		this.installmentList = installmentList;
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
	public HashMap getLoanTypeHashmap() {
		return loanTypeHashmap;
	}
	public void setLoanTypeHashmap(HashMap loanTypeHashmap) {
		this.loanTypeHashmap = loanTypeHashmap;
	}
	public ArrayList getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList commentList) {
		this.commentList = commentList;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	public String getAppCommentFlag() {
		return appCommentFlag;
	}
	public void setAppCommentFlag(String appCommentFlag) {
		this.appCommentFlag = appCommentFlag;
	}
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}
	public String getHiddenApplicationStatus() {
		return hiddenApplicationStatus;
	}
	public void setHiddenApplicationStatus(String hiddenApplicationStatus) {
		this.hiddenApplicationStatus = hiddenApplicationStatus;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getLoanApplCode() {
		return loanApplCode;
	}
	public void setLoanApplCode(String loanApplCode) {
		this.loanApplCode = loanApplCode;
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
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
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
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
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
	/**
	 * @return the approverCode
	 */
	public String getApproverCode() {
		return approverCode;
	}
	/**
	 * @param approverCode the approverCode to set
	 */
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	/**
	 * @return the grossSalary
	 */
	public String getGrossSalary() {
		return grossSalary;
	}
	/**
	 * @param grossSalary the grossSalary to set
	 */
	public void setGrossSalary(String grossSalary) {
		this.grossSalary = grossSalary;
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
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
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
	public HashMap getPurposeList() {
		return purposeList;
	}
	public void setPurposeList(HashMap purposeList) {
		this.purposeList = purposeList;
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
	public String getLoanPurposeValue() {
		return loanPurposeValue;
	}
	public void setLoanPurposeValue(String loanPurposeValue) {
		this.loanPurposeValue = loanPurposeValue;
	}
	public String getHiddenCalType() {
		return hiddenCalType;
	}
	public void setHiddenCalType(String hiddenCalType) {
		this.hiddenCalType = hiddenCalType;
	}
	public String getApplicantComment() {
		return applicantComment;
	}
	public void setApplicantComment(String applicantComment) {
		this.applicantComment = applicantComment;
	}
	public String getLoanAllowedLimit() {
		return loanAllowedLimit;
	}
	public void setLoanAllowedLimit(String loanAllowedLimit) {
		this.loanAllowedLimit = loanAllowedLimit;
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
	public String getHiddenCalfrmId() {
		return hiddenCalfrmId;
	}
	public void setHiddenCalfrmId(String hiddenCalfrmId) {
		this.hiddenCalfrmId = hiddenCalfrmId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

}
