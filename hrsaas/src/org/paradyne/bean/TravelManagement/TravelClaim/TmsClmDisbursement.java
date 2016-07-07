package org.paradyne.bean.TravelManagement.TravelClaim;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class TmsClmDisbursement extends BeanBase {
	
	private String currencyEmployeeAdvance = "";
	private String totalCurrencyExpense = "";
	private String currencyExpenseAmt = ""; 
	
	//Added by vishwambhar
	private String creditCodeItt ="";
	private String creditNameItt ="";
	
	private String creditName ="";
	private String creditCode ="";
	
	private String source="";
	private String divisionName = "";
	private String divisionCode = "";
	private String listType = "";
	private String myClaimPage = "";
	private boolean noClaimData=false  ;
 
 
	private String bankIFSCCode = "";
	private String bankBranch = "";
	private String bankBSRCode = "";
	
	private String searchempId="";
	private String searchempName="";
	private String travelId="";
	private String searchemptoken="";
	
	// for combo box ADDED BY REEBA
	TreeMap payModeMap;
	private String month="";
	private String year = "";
	private String monthClaim="";
	private String yearClaim = "";
	private String empCode = "";
	
	private String recoveryMonth="";
	private String recoveryYear = "";
	private String recoveryDebit = "";
	private String recoveryDebitCode = "";
	
	private String showRevokeStatus="false";
	
	// for paging
	private String hdPage = "";
	private String fromTotRec = "";
	private String toTotRec = "";
	private String zeroflag="false";
	// for new paging

	private String myPage = "";
	private String status = "";
	private boolean pageFlag = false;
	private String rowId="";

	// control flags
	private boolean noData = false;
	private String pen = "";
	private String clsd = "";
	private String trvlId="";
	private String claimtrvlId="";

	// for iterator
	ArrayList clmDisbrList = new ArrayList();

	private String itEmpName = "";
	private String itclmType = "";
	private String itAppDate = "";
	private String itClmAmt = "";
	private String itClmAppId="";
	private String itClmStatus="";
	private String tranferFlag="false";
	private String chequeFlag="false";
	private String disburseCode="";
	private String paraId="";
	private String trvlAppId="";
	private String trvlAppCode="";
	private String appId="";
	private String appCode="";
	// for navigation purpose

	private String tmsClmAppId = "";
	private String tmsClmStatus = "";

	// Employee Details in view

	private String empName = "";
	private String trvlAppFor = "";
	private String trvlAppDate = "";
	private String trvlStartDate = "";
	private String trvlEndDate = "";
	private String empBranch = "";
	private String empDept = "";
	private String empGrade = "";
	private String empDesgn = "";
	private String clmAppDate = "";
	private String gradId = "";
	private String clmTrvlType="";
	
	private String apprvdAmt="";
	private String advAmt="";
	private String expDisbDate="";
	// for viewing travel policy

	private String appDate = "";
	private String startDate = "";
	private String endDate = "";

	// for showing booking details

	private String bDtlAppId = "";
	private String bDtlAppCode = "";
	private String bDtlInitrId = "";
	private String bDtlEmpId = "";
	private String bDtlAppDate = "";
	
	
  //for payment details
	
	private String disbBalId="";
	private String disbBalDate="";
	private String disbBalPayMode="";
	private String disbBalAmt="";
	private String disbBalCmt="";
	private String chqNo="";
	private String chqDate="";
	private String bankName="";
	private String accountNo="";
	private String bankId="";
	private String payFlag="N";
	private String payChk="";
	private String checkAll="";
	private String disburseDate="";
	private String disburseAccount="";
	private String disburseMode="";
	private String disburseChqDate="";
	private String disburseChqNo="";
	private String bankCode="";
	private String disburseBankName="";
	private String disburseAmount="";
	private String disbursementComment="";
	private String paymentDet="";
	private String comment="";
	private String totalAmount="";
	private String balanceAmount="";
	ArrayList paymentList = new ArrayList();
	private String totDisbrAmt="";
	private String disburseStatus="";
	private String otherAdv="";
	private String disbursementId="";
	private String disburseFlag="true";
	private String descCnt1="";
	private String editButton="false";
	private String currentStatus="";
	
	private ArrayList approverCommentList =null ; 
	ArrayList expDtls = new ArrayList();
	private String totElgblAmt = "";
	private String totExpAmt = "";
	private String expProofPath = "";
	ArrayList expProofPathList =null;
	
	ArrayList cmtsList = new ArrayList();
	private String clmAppCmts="";
	private String applicantId = "";
	
	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getClmAppCmts() {
		return clmAppCmts;
	}

	public void setClmAppCmts(String clmAppCmts) {
		this.clmAppCmts = clmAppCmts;
	}

	public ArrayList getCmtsList() {
		return cmtsList;
	}

	public void setCmtsList(ArrayList cmtsList) {
		this.cmtsList = cmtsList;
	}

	public ArrayList getExpProofPathList() {
		return expProofPathList;
	}

	public void setExpProofPathList(ArrayList expProofPathList) {
		this.expProofPathList = expProofPathList;
	}

	public String getExpProofPath() {
		return expProofPath;
	}

	public void setExpProofPath(String expProofPath) {
		this.expProofPath = expProofPath;
	}

	public ArrayList getExpDtls() {
		return expDtls;
	}

	public void setExpDtls(ArrayList expDtls) {
		this.expDtls = expDtls;
	}

	public String getTotElgblAmt() {
		return totElgblAmt;
	}

	public void setTotElgblAmt(String totElgblAmt) {
		this.totElgblAmt = totElgblAmt;
	}

	public String getTotExpAmt() {
		return totExpAmt;
	}

	public void setTotExpAmt(String totExpAmt) {
		this.totExpAmt = totExpAmt;
	}

	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}

	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	public String getEditButton() {
		return editButton;
	}

	public void setEditButton(String editButton) {
		this.editButton = editButton;
	}

	public String getDisbursementId() {
		return disbursementId;
	}

	public void setDisbursementId(String disbursementId) {
		this.disbursementId = disbursementId;
	}

	public String getOtherAdv() {
		return otherAdv;
	}

	public void setOtherAdv(String otherAdv) {
		this.otherAdv = otherAdv;
	}

	public String getDisburseStatus() {
		return disburseStatus;
	}

	public void setDisburseStatus(String disburseStatus) {
		this.disburseStatus = disburseStatus;
	}

	public String getTotDisbrAmt() {
		return totDisbrAmt;
	}

	public void setTotDisbrAmt(String totDisbrAmt) {
		this.totDisbrAmt = totDisbrAmt;
	}

	/**
	 * @return the hdPage
	 */
	public String getHdPage() {
		return hdPage;
	}

	/**
	 * @param hdPage
	 *            the hdPage to set
	 */
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}

	/**
	 * @return the fromTotRec
	 */
	public String getFromTotRec() {
		return fromTotRec;
	}

	/**
	 * @param fromTotRec
	 *            the fromTotRec to set
	 */
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}

	/**
	 * @return the toTotRec
	 */
	public String getToTotRec() {
		return toTotRec;
	}

	/**
	 * @param toTotRec
	 *            the toTotRec to set
	 */
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage
	 *            the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the pageFlag
	 */
	public boolean isPageFlag() {
		return pageFlag;
	}

	/**
	 * @param pageFlag
	 *            the pageFlag to set
	 */
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}

	/**
	 * @return the noData
	 */
	public boolean isNoData() {
		return noData;
	}

	/**
	 * @param noData
	 *            the noData to set
	 */
	public void setNoData(boolean noData) {
		this.noData = noData;
	}

	/**
	 * @return the pen
	 */
	public String getPen() {
		return pen;
	}

	/**
	 * @param pen
	 *            the pen to set
	 */
	public void setPen(String pen) {
		this.pen = pen;
	}

	/**
	 * @return the clsd
	 */
	public String getClsd() {
		return clsd;
	}

	/**
	 * @param clsd
	 *            the clsd to set
	 */
	public void setClsd(String clsd) {
		this.clsd = clsd;
	}

	/**
	 * @return the clmDisbrList
	 */
	public ArrayList getClmDisbrList() {
		return clmDisbrList;
	}

	/**
	 * @param clmDisbrList
	 *            the clmDisbrList to set
	 */
	public void setClmDisbrList(ArrayList clmDisbrList) {
		this.clmDisbrList = clmDisbrList;
	}

	/**
	 * @return the itEmpName
	 */
	public String getItEmpName() {
		return itEmpName;
	}

	/**
	 * @param itEmpName
	 *            the itEmpName to set
	 */
	public void setItEmpName(String itEmpName) {
		this.itEmpName = itEmpName;
	}

	/**
	 * @return the itclmType
	 */
	public String getItclmType() {
		return itclmType;
	}

	/**
	 * @param itclmType
	 *            the itclmType to set
	 */
	public void setItclmType(String itclmType) {
		this.itclmType = itclmType;
	}

	/**
	 * @return the itAppDate
	 */
	public String getItAppDate() {
		return itAppDate;
	}

	/**
	 * @param itAppDate
	 *            the itAppDate to set
	 */
	public void setItAppDate(String itAppDate) {
		this.itAppDate = itAppDate;
	}

	/**
	 * @return the itClmAmt
	 */
	public String getItClmAmt() {
		return itClmAmt;
	}

	/**
	 * @param itClmAmt
	 *            the itClmAmt to set
	 */
	public void setItClmAmt(String itClmAmt) {
		this.itClmAmt = itClmAmt;
	}

		/**
	 * @return the tmsClmAppId
	 */
	public String getTmsClmAppId() {
		return tmsClmAppId;
	}

	/**
	 * @param tmsClmAppId
	 *            the tmsClmAppId to set
	 */
	public void setTmsClmAppId(String tmsClmAppId) {
		this.tmsClmAppId = tmsClmAppId;
	}

	/**
	 * @return the tmsClmStatus
	 */
	public String getTmsClmStatus() {
		return tmsClmStatus;
	}

	/**
	 * @param tmsClmStatus
	 *            the tmsClmStatus to set
	 */
	public void setTmsClmStatus(String tmsClmStatus) {
		this.tmsClmStatus = tmsClmStatus;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the trvlAppFor
	 */
	public String getTrvlAppFor() {
		return trvlAppFor;
	}

	/**
	 * @param trvlAppFor
	 *            the trvlAppFor to set
	 */
	public void setTrvlAppFor(String trvlAppFor) {
		this.trvlAppFor = trvlAppFor;
	}

	/**
	 * @return the trvlAppDate
	 */
	public String getTrvlAppDate() {
		return trvlAppDate;
	}

	/**
	 * @param trvlAppDate
	 *            the trvlAppDate to set
	 */
	public void setTrvlAppDate(String trvlAppDate) {
		this.trvlAppDate = trvlAppDate;
	}

	/**
	 * @return the trvlStartDate
	 */
	public String getTrvlStartDate() {
		return trvlStartDate;
	}

	/**
	 * @param trvlStartDate
	 *            the trvlStartDate to set
	 */
	public void setTrvlStartDate(String trvlStartDate) {
		this.trvlStartDate = trvlStartDate;
	}

	/**
	 * @return the trvlEndDate
	 */
	public String getTrvlEndDate() {
		return trvlEndDate;
	}

	/**
	 * @param trvlEndDate
	 *            the trvlEndDate to set
	 */
	public void setTrvlEndDate(String trvlEndDate) {
		this.trvlEndDate = trvlEndDate;
	}

	/**
	 * @return the empBranch
	 */
	public String getEmpBranch() {
		return empBranch;
	}

	/**
	 * @param empBranch
	 *            the empBranch to set
	 */
	public void setEmpBranch(String empBranch) {
		this.empBranch = empBranch;
	}

	/**
	 * @return the empDept
	 */
	public String getEmpDept() {
		return empDept;
	}

	/**
	 * @param empDept
	 *            the empDept to set
	 */
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	/**
	 * @return the empGrade
	 */
	public String getEmpGrade() {
		return empGrade;
	}

	/**
	 * @param empGrade
	 *            the empGrade to set
	 */
	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}

	/**
	 * @return the empDesgn
	 */
	public String getEmpDesgn() {
		return empDesgn;
	}

	/**
	 * @param empDesgn
	 *            the empDesgn to set
	 */
	public void setEmpDesgn(String empDesgn) {
		this.empDesgn = empDesgn;
	}

	
	/**
	 * @return the gradId
	 */
	public String getGradId() {
		return gradId;
	}

	/**
	 * @param gradId
	 *            the gradId to set
	 */
	public void setGradId(String gradId) {
		this.gradId = gradId;
	}

	/**
	 * @return the appDate
	 */
	public String getAppDate() {
		return appDate;
	}

	/**
	 * @param appDate
	 *            the appDate to set
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the bDtlAppId
	 */
	public String getBDtlAppId() {
		return bDtlAppId;
	}

	/**
	 * @param dtlAppId
	 *            the bDtlAppId to set
	 */
	public void setBDtlAppId(String dtlAppId) {
		bDtlAppId = dtlAppId;
	}

	/**
	 * @return the bDtlAppCode
	 */
	public String getBDtlAppCode() {
		return bDtlAppCode;
	}

	/**
	 * @param dtlAppCode
	 *            the bDtlAppCode to set
	 */
	public void setBDtlAppCode(String dtlAppCode) {
		bDtlAppCode = dtlAppCode;
	}

	/**
	 * @return the bDtlInitrId
	 */
	public String getBDtlInitrId() {
		return bDtlInitrId;
	}

	/**
	 * @param dtlInitrId
	 *            the bDtlInitrId to set
	 */
	public void setBDtlInitrId(String dtlInitrId) {
		bDtlInitrId = dtlInitrId;
	}

	/**
	 * @return the bDtlEmpId
	 */
	public String getBDtlEmpId() {
		return bDtlEmpId;
	}

	/**
	 * @param dtlEmpId
	 *            the bDtlEmpId to set
	 */
	public void setBDtlEmpId(String dtlEmpId) {
		bDtlEmpId = dtlEmpId;
	}

	/**
	 * @return the bDtlAppDate
	 */
	public String getBDtlAppDate() {
		return bDtlAppDate;
	}

	/**
	 * @param dtlAppDate the bDtlAppDate to set
	 */
	public void setBDtlAppDate(String dtlAppDate) {
		bDtlAppDate = dtlAppDate;
	}

	/**
	 * @return the itClmAppId
	 */
	public String getItClmAppId() {
		return itClmAppId;
	}

	/**
	 * @param itClmAppId the itClmAppId to set
	 */
	public void setItClmAppId(String itClmAppId) {
		this.itClmAppId = itClmAppId;
	}

	/**
	 * @return the itClmStatus
	 */
	public String getItClmStatus() {
		return itClmStatus;
	}

	/**
	 * @param itClmStatus the itClmStatus to set
	 */
	public void setItClmStatus(String itClmStatus) {
		this.itClmStatus = itClmStatus;
	}

	/**
	 * @return the clmAppDate
	 */
	public String getClmAppDate() {
		return clmAppDate;
	}

	/**
	 * @param clmAppDate the clmAppDate to set
	 */
	public void setClmAppDate(String clmAppDate) {
		this.clmAppDate = clmAppDate;
	}

	/**
	 * @return the clmTrvlType
	 */
	public String getClmTrvlType() {
		return clmTrvlType;
	}

	/**
	 * @param clmTrvlType the clmTrvlType to set
	 */
	public void setClmTrvlType(String clmTrvlType) {
		this.clmTrvlType = clmTrvlType;
	}

	/**
	 * @return the apprvdAmt
	 */
	public String getApprvdAmt() {
		return apprvdAmt;
	}

	/**
	 * @param apprvdAmt the apprvdAmt to set
	 */
	public void setApprvdAmt(String apprvdAmt) {
		this.apprvdAmt = apprvdAmt;
	}

	/**
	 * @return the advAmt
	 */
	public String getAdvAmt() {
		return advAmt;
	}

	/**
	 * @param advAmt the advAmt to set
	 */
	public void setAdvAmt(String advAmt) {
		this.advAmt = advAmt;
	}

	/**
	 * @return the disbBalId
	 */
	public String getDisbBalId() {
		return disbBalId;
	}

	/**
	 * @param disbBalId the disbBalId to set
	 */
	public void setDisbBalId(String disbBalId) {
		this.disbBalId = disbBalId;
	}

	/**
	 * @return the disbBalDate
	 */
	public String getDisbBalDate() {
		return disbBalDate;
	}

	/**
	 * @param disbBalDate the disbBalDate to set
	 */
	public void setDisbBalDate(String disbBalDate) {
		this.disbBalDate = disbBalDate;
	}

	/**
	 * @return the disbBalPayMode
	 */
	public String getDisbBalPayMode() {
		return disbBalPayMode;
	}

	/**
	 * @param disbBalPayMode the disbBalPayMode to set
	 */
	public void setDisbBalPayMode(String disbBalPayMode) {
		this.disbBalPayMode = disbBalPayMode;
	}

	/**
	 * @return the disbBalAmt
	 */
	public String getDisbBalAmt() {
		return disbBalAmt;
	}

	/**
	 * @param disbBalAmt the disbBalAmt to set
	 */
	public void setDisbBalAmt(String disbBalAmt) {
		this.disbBalAmt = disbBalAmt;
	}

	/**
	 * @return the disbBalCmt
	 */
	public String getDisbBalCmt() {
		return disbBalCmt;
	}

	/**
	 * @param disbBalCmt the disbBalCmt to set
	 */
	public void setDisbBalCmt(String disbBalCmt) {
		this.disbBalCmt = disbBalCmt;
	}

	/**
	 * @return the paymentList
	 */
	public ArrayList getPaymentList() {
		return paymentList;
	}

	/**
	 * @param paymentList the paymentList to set
	 */
	public void setPaymentList(ArrayList paymentList) {
		this.paymentList = paymentList;
	}

	public String getChqNo() {
		return chqNo;
	}

	public void setChqNo(String chqNo) {
		this.chqNo = chqNo;
	}

	public String getChqDate() {
		return chqDate;
	}

	public void setChqDate(String chqDate) {
		this.chqDate = chqDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getPayChk() {
		return payChk;
	}

	public void setPayChk(String payChk) {
		this.payChk = payChk;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getCheckAll() {
		return checkAll;
	}

	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}

	public String getTranferFlag() {
		return tranferFlag;
	}

	public void setTranferFlag(String tranferFlag) {
		this.tranferFlag = tranferFlag;
	}

	public String getChequeFlag() {
		return chequeFlag;
	}

	public void setChequeFlag(String chequeFlag) {
		this.chequeFlag = chequeFlag;
	}

	public String getDisburseDate() {
		return disburseDate;
	}

	public void setDisburseDate(String disburseDate) {
		this.disburseDate = disburseDate;
	}

	public String getDisburseAccount() {
		return disburseAccount;
	}

	public void setDisburseAccount(String disburseAccount) {
		this.disburseAccount = disburseAccount;
	}

	public String getDisburseMode() {
		return disburseMode;
	}

	public void setDisburseMode(String disburseMode) {
		this.disburseMode = disburseMode;
	}

	public String getDisburseChqDate() {
		return disburseChqDate;
	}

	public void setDisburseChqDate(String disburseChqDate) {
		this.disburseChqDate = disburseChqDate;
	}

	public String getDisburseChqNo() {
		return disburseChqNo;
	}

	public void setDisburseChqNo(String disburseChqNo) {
		this.disburseChqNo = disburseChqNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getDisburseBankName() {
		return disburseBankName;
	}

	public void setDisburseBankName(String disburseBankName) {
		this.disburseBankName = disburseBankName;
	}

	public String getDisburseAmount() {
		return disburseAmount;
	}

	public void setDisburseAmount(String disburseAmount) {
		this.disburseAmount = disburseAmount;
	}

	public String getDisbursementComment() {
		return disbursementComment;
	}

	public void setDisbursementComment(String disbursementComment) {
		this.disbursementComment = disbursementComment;
	}

	public String getPaymentDet() {
		return paymentDet;
	}

	public void setPaymentDet(String paymentDet) {
		this.paymentDet = paymentDet;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDisburseCode() {
		return disburseCode;
	}

	public void setDisburseCode(String disburseCode) {
		this.disburseCode = disburseCode;
	}

	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getDisburseFlag() {
		return disburseFlag;
	}

	public void setDisburseFlag(String disburseFlag) {
		this.disburseFlag = disburseFlag;
	}

	public String getDescCnt1() {
		return descCnt1;
	}

	public void setDescCnt1(String descCnt1) {
		this.descCnt1 = descCnt1;
	}

	public String getTrvlAppId() {
		return trvlAppId;
	}

	public void setTrvlAppId(String trvlAppId) {
		this.trvlAppId = trvlAppId;
	}

	public String getTrvlAppCode() {
		return trvlAppCode;
	}

	public void setTrvlAppCode(String trvlAppCode) {
		this.trvlAppCode = trvlAppCode;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @return the trvlId
	 */
	public String getTrvlId() {
		return trvlId;
	}

	/**
	 * @param trvlId the trvlId to set
	 */
	public void setTrvlId(String trvlId) {
		this.trvlId = trvlId;
	}

	/**
	 * @return the claimtrvlId
	 */
	public String getClaimtrvlId() {
		return claimtrvlId;
	}

	/**
	 * @param claimtrvlId the claimtrvlId to set
	 */
	public void setClaimtrvlId(String claimtrvlId) {
		this.claimtrvlId = claimtrvlId;
	}

	/**
	 * @return the zeroflag
	 */
	public String getZeroflag() {
		return zeroflag;
	}

	/**
	 * @param zeroflag the zeroflag to set
	 */
	public void setZeroflag(String zeroflag) {
		this.zeroflag = zeroflag;
	}

	/**
	 * @return the currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus the currentStatus to set
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public TreeMap getPayModeMap() {
		return payModeMap;
	}

	public void setPayModeMap(TreeMap payModeMap) {
		this.payModeMap = payModeMap;
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

	public String getMonthClaim() {
		return monthClaim;
	}

	public void setMonthClaim(String monthClaim) {
		this.monthClaim = monthClaim;
	}

	public String getYearClaim() {
		return yearClaim;
	}

	public void setYearClaim(String yearClaim) {
		this.yearClaim = yearClaim;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getRecoveryMonth() {
		return recoveryMonth;
	}

	public void setRecoveryMonth(String recoveryMonth) {
		this.recoveryMonth = recoveryMonth;
	}

	public String getRecoveryYear() {
		return recoveryYear;
	}

	public void setRecoveryYear(String recoveryYear) {
		this.recoveryYear = recoveryYear;
	}

	public String getRecoveryDebit() {
		return recoveryDebit;
	}

	public void setRecoveryDebit(String recoveryDebit) {
		this.recoveryDebit = recoveryDebit;
	}

	public String getRecoveryDebitCode() {
		return recoveryDebitCode;
	}

	public void setRecoveryDebitCode(String recoveryDebitCode) {
		this.recoveryDebitCode = recoveryDebitCode;
	}

	/**
	 * @return the showRevokeStatus
	 */
	public String getShowRevokeStatus() {
		return showRevokeStatus;
	}

	/**
	 * @param showRevokeStatus the showRevokeStatus to set
	 */
	public void setShowRevokeStatus(String showRevokeStatus) {
		this.showRevokeStatus = showRevokeStatus;
	}

	public String getBankIFSCCode() {
		return bankIFSCCode;
	}

	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankBSRCode() {
		return bankBSRCode;
	}

	public void setBankBSRCode(String bankBSRCode) {
		this.bankBSRCode = bankBSRCode;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getMyClaimPage() {
		return myClaimPage;
	}

	public void setMyClaimPage(String myClaimPage) {
		this.myClaimPage = myClaimPage;
	}

 

	public void setNoClaimData(boolean noClaimData) {
		this.noClaimData = noClaimData;
	}

	public boolean isNoClaimData() {
		return noClaimData;
	}

	public String getSearchempId() {
		return searchempId;
	}

	public void setSearchempId(String searchempId) {
		this.searchempId = searchempId;
	}

	public String getSearchempName() {
		return searchempName;
	}

	public void setSearchempName(String searchempName) {
		this.searchempName = searchempName;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	public String getSearchemptoken() {
		return searchemptoken;
	}

	public void setSearchemptoken(String searchemptoken) {
		this.searchemptoken = searchemptoken;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreditCodeItt() {
		return creditCodeItt;
	}

	public void setCreditCodeItt(String creditCodeItt) {
		this.creditCodeItt = creditCodeItt;
	}

	public String getCreditNameItt() {
		return creditNameItt;
	}

	public void setCreditNameItt(String creditNameItt) {
		this.creditNameItt = creditNameItt;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getCurrencyEmployeeAdvance() {
		return currencyEmployeeAdvance;
	}

	public void setCurrencyEmployeeAdvance(String currencyEmployeeAdvance) {
		this.currencyEmployeeAdvance = currencyEmployeeAdvance;
	}

	public String getTotalCurrencyExpense() {
		return totalCurrencyExpense;
	}

	public void setTotalCurrencyExpense(String totalCurrencyExpense) {
		this.totalCurrencyExpense = totalCurrencyExpense;
	}

	public String getCurrencyExpenseAmt() {
		return currencyExpenseAmt;
	}

	public void setCurrencyExpenseAmt(String currencyExpenseAmt) {
		this.currencyExpenseAmt = currencyExpenseAmt;
	}

	public String getExpDisbDate() {
		return expDisbDate;
	}

	public void setExpDisbDate(String expDisbDate) {
		this.expDisbDate = expDisbDate;
	}

}
