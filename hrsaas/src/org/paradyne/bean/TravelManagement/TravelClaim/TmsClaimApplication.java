package org.paradyne.bean.TravelManagement.TravelClaim;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0651
 * 
 */
public class TmsClaimApplication extends BeanBase {
	
	// added by Prashant
	private String hiddenProofCnt="";
	private String defaultCurrencyFlag="false";
	private String totalCurrencyExpense = "";
	private String currencyEmployeeAdvance = "";
	private String currencyExpenseAmt = "";
	private String currencyExpenseAmtItr = "";
	
	private String source="";
	private String rowId ;
	private String followUpCommentsItt  = "";
	private String responsibleEmpIdItt  = "";
	private String responsibleEmpTokenItt  = "";
	private String responsibleEmpItt  = "";
	private String targetDateItt  = "";
	private ArrayList followUpActionList =null;
	
	private String hiddenStatus="";
	private ArrayList travelRatingParameterList =null;
	private String deskRatingIdItt  = "";
	private String deskRatingNameItt  = "";
	private String deskRatingItt  = "";
	private boolean showHotelRatingFlag= false; 
	private ArrayList hotelNameList =null;
	private ArrayList hotelRatingParameterList =null;
	private String hotelNameItt  = "";
	private String hotelIdItt  = "";
	private String hotelRatingIdItt  = "";
	private String hotelRatingNameItt  = "";
	private String hotelRatingItt  = "";
	private String sourceDestination  = "";
	
	private String tourComments  = "";
	private String tourReportFile  = "";
	private String achievementComments  = "";
	private String followUpComments  = "";
	private String targetDate  = "";
	private boolean showFlag= true; 
	
	private ArrayList approverCommentList =null ; 
	
	private String approverCommentsFlag ="false";
	private String approverListFlag ="false";
	
	private String appSrNo  = "";
	private String prevApproverID  = "";
	private String prevApproverName  = "";
	private String prevApproverDate  = "";
	private String prevApproverStatus  = "";
	private String prevApproverComment  = "";
	
	private String tmsExpType = "";
	
	private String showBookingDetailsFlag ="false";
	
	private String  maxClaimDays = "";
	private String maximumClaimDueDays = "";
	 

	// for showing booking details
	
	private String itteratorProofNameForSave="";

	private String bDtlAppId = "";
	private String bDtlAppCode = "";
	private String bDtlInitrId = "";
	private String bDtlEmpId = "";
	private String bDtlAppDate = "";
	private String bDtlForFlag = "";
	private String policyViolationText="";
	private String policyViolationTextIt="";
	
	private String tmsTrvlId;
	private String tmsTrvlIndiId;
	private String tmsChkTypeFlg;
	private String deskFlag;

	private String expTabLength = "false";
	private String appDate = "";
	private String claimApplnCode = "";
	private String listType = "";
	private String trvlId = "";
	private String appId = "";
	private String appCode = "";
	private String appStatus = "";
	private String appFor = "";
	private String initId = "";
	private String empId = "";
	private String empName = "";
	private String trvlReqName = "";
	private String initName = "";
	private String appEndDate = "";
	private String claimDueDays = "";
	private String schExpAppId = "";
	private String schFlag = "false";
	// Paging variables scheduled
	private String myPageDraft;
	private String myPageSubmit;
	private String myPageReturn;
	private String myPageApproved;
	private String myPageClose;
	private String myPageRejected;
	private String myPageSchlTrvlList;
	private String myPageCancel;
	private String myPagePendingCancel;

	private String totalPageScheduled = "";
	private String PageNoScheduled = "";
	private String rowScheduled = "";

	// Paging variables draft
	private String totalPageDraft = "";
	private String PageNoDraft = "";
	private String rowDraft = "";
	// draft data

	private String draftExpAppId = "";
	private String appCombinedIdDraft = "";
	private String appIdDraft = "";
	private String appCodeDraft = "";
	private String appStatusDraft = "";
	private String appForDraft = "";
	private String initIdDraft = "";
	private String empIdDraft = "";
	private String trvlIdDraft = "";
	private String appEndDateDraft = "";
	private String initNameDraft = "";
	private String empNameDraft = "";
	private String trvlReqNameDraft = "";
	private ArrayList draftAppList;
	private ArrayList blockedAppList;

	// Paging variables Submit
	private String totalPageSubmit = "";
	private String PageNoSubmit = "";
	private String rowSubmit = "";
	// submit data
	private String submitExpAppId = "";
	private String appIdSubmit = "";
	private String appCodeSubmit = "";
	private String appStatusSubmit = "";
	private String appForSubmit = "";
	private String initIdSubmit = "";
	private String empIdSubmit = "";
	private String trvlIdSubmit = "";
	private String appEndDateSubmit = "";
	private String initNameSubmit = "";
	private String empNameSubmit = "";
	private String trvlReqNameSubmit = "";
	ArrayList submitAppList;

	// Paging variables return
	private String totalPageReturn = "";
	private String PageNoReturn = "";
	private String rowReturn = "";
	// return data
	private String returnExpAppId = "";
	private String appIdReturn = "";
	private String appCodeReturn = "";
	private String appStatusReturn = "";
	private String appForReturn = "";
	private String initIdReturn = "";
	private String empIdReturn = "";
	private String trvlIdReturn = "";
	private String appEndDateReturn = "";
	private String initNameReturn = "";
	private String empNameReturn = "";
	private String trvlReqNameReturn = "";
	ArrayList returnAppList;

	private String applnId = "";
	private String applnCode = "";
	private String applnStatus = "";
	private String applnFor = "";
	private String applnInitId = "";
	private String applnEmpId = "";

	// for employee information
	private String employeeToken = "";
	private String employeeId = "";
	private String employeeName = "";
	private String trvlStartDate = "";
	private String branchId = "";
	private String branch = "";
	private String trvlEndDate = "";
	private String deptId = "";
	private String dept = "";
	private String gradId = "";
	private String grad = "";
	private String desgId = "";
	private String desg = "";
	// Travel Request name
	private String trvlReqNameAddNew = "";
	private String travelPurpose = "";
	private String travelPurposeId = "";
	private String advanceAmtTaken = "";
	private String travelType = "";
	private String travelTypeId = "";
	// Expense details
	private String expenseDate = "";
	private String eligibleAmt = "";
	private String expenseType = "";
	private String expenseTypeId = "";
	private String expenseAmt = "";
	private String particulars = "";
	private String proofRequired = "";

	private String uploadLocFileName = "";
	// Expense dtl list
	ArrayList expenseDtlList;
	private String expenseDateIt = "";
	private String expenseTypeIt = "";
	private String expenseTypeIdIt = "";
	private String particularsIt = "";
	private String eligibleAmtIt = "";
	private String expenseAmtIt = "";
	//private String proofIt = "";
	private String proofRequiredIt = "";
	private String srNo = "";
	private String comment = "";
	private String totEligibleAmount = "";
	private String totExpAmount = "";
	private String expAppDateDraft = "";
	private String requiredAdvanceAmt = "";
	private String claimApplnCodeDraft = "";
	
//Added by manish	
	private String expValuewithBill="";
	private String expValuewithoutBill="";

	private String addNewFlag = "false";
	private String draftFlag = "false";
	private String submitFlag = "false";
	private String returnFlag = "false";
	private String approvedFlag = "false";
	private String closedFlag = "false";

	// added by krishna

	private String expDtlId = "";
	private String expItId = "";
	private String delExp = "";
	private String totElgAmt = "";
	private String totExpAmt = "";

	// Paging variables Approved
	private String totalPageApprove = "";
	private String PageNoApprove = "";
	private String rowApprove = "";
	// approve data
	private String approveExpAppId = "";
	private String appIdApprove = "";
	private String appCodeApprove = "";
	private String appStatusApprove = "";
	private String appForApprove = "";
	private String initIdApprove = "";
	private String empIdApprove = "";
	private String trvlIdApprove = "";
	private String appEndDateApprove = "";
	private String initNameApprove = "";
	private String empNameApprove = "";
	private String trvlReqNameApprove = "";
	ArrayList approveAppList;

	// Paging variables Closed
	private String totalPageClose = "";
	private String PageNoClose = "";
	private String rowClose = "";
	// Close data
	private String closeExpAppId = "";
	private String appIdClose = "";
	private String appCodeClose = "";
	private String appStatusClose = "";
	private String appForClose = "";
	private String initIdClose = "";
	private String empIdClose = "";
	private String trvlIdClose = "";
	private String appEndDateClose = "";
	private String initNameClose = "";
	private String empNameClose = "";
	private String trvlReqNameClose = "";
	ArrayList closeAppList;

	private String uploadFlag = "false";

	private boolean cityGradeFlag = false;

	
	
	ArrayList proofList;
	private String proofSrNo="";
	private String proofName="";
	private String proofFileName="";
	private String checkRemoveUpload="";
	private String dataPath="";
	
	private String ittproofName="";
	
	private String amountWithBill ="";
	private String amountWithoutBill="";
	
	
	private String eligibleAmtWithBillIt="";
	private String eligibleAmtWithoutBillIt="";
	
	
	private String totElgAmtWithBill="";
	private String totElgAmtWithoutBill="";
	
	ArrayList ittUploadList;
	
	private String status ="";
	
	private boolean eligibleAmountFlag = false;
	
	private String AtActual="";
	
	private String projectId = "";
	private String project ="";
	private String customerId ="";
	private String customerName ="";
	
	private String cityGrade ="";
	TreeMap tmap = null;
	String pendingStatus="";
	
	private boolean claimDueDaysFlag = false; 
	
	private String applicationCode ="";
	/*
	 * ri //DraftDtlView Data private String employeeNameDraft=""; private
	 * String trvlStartDateDraft=""; private String branchDraft=""; private
	 * String trvlEndDateDraft=""; private String deptDraft=""; private String
	 * gradIdDraft=""; private String gradDraft=""; private String desgDraft="";
	 * private String requiredAdvanceAmountDraft=""; private String
	 * expAppDateDraft=""; private String trvlReqNameDraftView=""; private
	 * String travelPurposeDraft=""; private String travelTypeDraft=""; private
	 * String advanceAmtTakenDraft=""; private String commentDraft=""; private
	 * String totExpenseAmtDraft=""; private String totEligibleAmtDraft="";
	 * private String expenseDateDraftIt=""; private String
	 * expenseTypeDraftIt=""; private String particularsDraftIt=""; private
	 * String eligibleAmtDraftIt=""; private String expenseAmtDraftIt="";
	 * private String proofDradftIt="";
	 */

	/*
	 * private String expenseDateDraft=""; private String eligibleAmtDraft="";
	 * private String expenseTypeDraft=""; private String expenseTypeIdDraft="";
	 * private String expenseAmtDraft=""; private String proofDradftIt="";
	 */

	ArrayList expenseDtlViewList;

	ArrayList scheduledAppList = new ArrayList();

	//Added by Ayyappa
	private String removeData = "";

	public String getRemoveData() {
		return removeData;
	}

	public void setRemoveData(String removeData) {
		this.removeData = removeData;
	}

	public String getClaimApplnCode() {
		return claimApplnCode;
	}

	public void setClaimApplnCode(String claimApplnCode) {
		this.claimApplnCode = claimApplnCode;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
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

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getAppFor() {
		return appFor;
	}

	public void setAppFor(String appFor) {
		this.appFor = appFor;
	}

	public String getInitId() {
		return initId;
	}

	public void setInitId(String initId) {
		this.initId = initId;
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

	public String getTrvlReqName() {
		return trvlReqName;
	}

	public void setTrvlReqName(String trvlReqName) {
		this.trvlReqName = trvlReqName;
	}

	public String getInitName() {
		return initName;
	}

	public void setInitName(String initName) {
		this.initName = initName;
	}

	public String getAppEndDate() {
		return appEndDate;
	}

	public void setAppEndDate(String appEndDate) {
		this.appEndDate = appEndDate;
	}

	public String getClaimDueDays() {
		return claimDueDays;
	}

	public void setClaimDueDays(String claimDueDays) {
		this.claimDueDays = claimDueDays;
	}

	public ArrayList getScheduledAppList() {
		return scheduledAppList;
	}

	public void setScheduledAppList(ArrayList scheduledAppList) {
		this.scheduledAppList = scheduledAppList;
	}

	public String getTrvlId() {
		return trvlId;
	}

	public void setTrvlId(String trvlId) {
		this.trvlId = trvlId;
	}

	public String getTotalPageScheduled() {
		return totalPageScheduled;
	}

	public void setTotalPageScheduled(String totalPageScheduled) {
		this.totalPageScheduled = totalPageScheduled;
	}

	public String getPageNoScheduled() {
		return PageNoScheduled;
	}

	public void setPageNoScheduled(String pageNoScheduled) {
		PageNoScheduled = pageNoScheduled;
	}

	public String getRowScheduled() {
		return rowScheduled;
	}

	public void setRowScheduled(String rowScheduled) {
		this.rowScheduled = rowScheduled;
	}

	public String getMyPageDraft() {
		return myPageDraft;
	}

	public void setMyPageDraft(String myPageDraft) {
		this.myPageDraft = myPageDraft;
	}

	public String getMyPageSubmit() {
		return myPageSubmit;
	}

	public void setMyPageSubmit(String myPageSubmit) {
		this.myPageSubmit = myPageSubmit;
	}

	public String getMyPageReturn() {
		return myPageReturn;
	}

	public void setMyPageReturn(String myPageReturn) {
		this.myPageReturn = myPageReturn;
	}

	public String getMyPageApproved() {
		return myPageApproved;
	}

	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	public String getMyPageRejected() {
		return myPageRejected;
	}

	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	public String getMyPageSchlTrvlList() {
		return myPageSchlTrvlList;
	}

	public void setMyPageSchlTrvlList(String myPageSchlTrvlList) {
		this.myPageSchlTrvlList = myPageSchlTrvlList;
	}

	public String getMyPageCancel() {
		return myPageCancel;
	}

	public void setMyPageCancel(String myPageCancel) {
		this.myPageCancel = myPageCancel;
	}

	public String getMyPagePendingCancel() {
		return myPagePendingCancel;
	}

	public void setMyPagePendingCancel(String myPagePendingCancel) {
		this.myPagePendingCancel = myPagePendingCancel;
	}

	public String getApplnId() {
		return applnId;
	}

	public void setApplnId(String applnId) {
		this.applnId = applnId;
	}

	public String getApplnCode() {
		return applnCode;
	}

	public void setApplnCode(String applnCode) {
		this.applnCode = applnCode;
	}

	public String getApplnStatus() {
		return applnStatus;
	}

	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}

	public String getApplnFor() {
		return applnFor;
	}

	public void setApplnFor(String applnFor) {
		this.applnFor = applnFor;
	}

	public String getApplnInitId() {
		return applnInitId;
	}

	public void setApplnInitId(String applnInitId) {
		this.applnInitId = applnInitId;
	}

	public String getApplnEmpId() {
		return applnEmpId;
	}

	public void setApplnEmpId(String applnEmpId) {
		this.applnEmpId = applnEmpId;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getTrvlStartDate() {
		return trvlStartDate;
	}

	public void setTrvlStartDate(String trvlStartDate) {
		this.trvlStartDate = trvlStartDate;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTrvlEndDate() {
		return trvlEndDate;
	}

	public void setTrvlEndDate(String trvlEndDate) {
		this.trvlEndDate = trvlEndDate;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getGradId() {
		return gradId;
	}

	public void setGradId(String gradId) {
		this.gradId = gradId;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDesgId() {
		return desgId;
	}

	public void setDesgId(String desgId) {
		this.desgId = desgId;
	}

	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}

	public String getTrvlReqNameAddNew() {
		return trvlReqNameAddNew;
	}

	public void setTrvlReqNameAddNew(String trvlReqNameAddNew) {
		this.trvlReqNameAddNew = trvlReqNameAddNew;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}

	public String getTravelPurposeId() {
		return travelPurposeId;
	}

	public void setTravelPurposeId(String travelPurposeId) {
		this.travelPurposeId = travelPurposeId;
	}

	public String getAdvanceAmtTaken() {
		return advanceAmtTaken;
	}

	public void setAdvanceAmtTaken(String advanceAmtTaken) {
		this.advanceAmtTaken = advanceAmtTaken;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getTravelTypeId() {
		return travelTypeId;
	}

	public void setTravelTypeId(String travelTypeId) {
		this.travelTypeId = travelTypeId;
	}

	public String getUploadLocFileName() {
		return uploadLocFileName;
	}

	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}

	public ArrayList getExpenseDtlList() {
		return expenseDtlList;
	}

	public void setExpenseDtlList(ArrayList expenseDtlList) {
		this.expenseDtlList = expenseDtlList;
	}

	public String getExpenseDateIt() {
		return expenseDateIt;
	}

	public void setExpenseDateIt(String expenseDateIt) {
		this.expenseDateIt = expenseDateIt;
	}

	public String getExpenseTypeIt() {
		return expenseTypeIt;
	}

	public void setExpenseTypeIt(String expenseTypeIt) {
		this.expenseTypeIt = expenseTypeIt;
	}

	public String getExpenseTypeIdIt() {
		return expenseTypeIdIt;
	}

	public void setExpenseTypeIdIt(String expenseTypeIdIt) {
		this.expenseTypeIdIt = expenseTypeIdIt;
	}

	public String getParticularsIt() {
		return particularsIt;
	}

	public void setParticularsIt(String particularsIt) {
		this.particularsIt = particularsIt;
	}

	public String getEligibleAmtIt() {
		return eligibleAmtIt;
	}

	public void setEligibleAmtIt(String eligibleAmtIt) {
		this.eligibleAmtIt = eligibleAmtIt;
	}

	public String getExpenseAmtIt() {
		return expenseAmtIt;
	}

	public void setExpenseAmtIt(String expenseAmtIt) {
		this.expenseAmtIt = expenseAmtIt;
	}

	 

	public String getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getEligibleAmt() {
		return eligibleAmt;
	}

	public void setEligibleAmt(String eligibleAmt) {
		this.eligibleAmt = eligibleAmt;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getExpenseTypeId() {
		return expenseTypeId;
	}

	public void setExpenseTypeId(String expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}

	public String getExpenseAmt() {
		return expenseAmt;
	}

	public void setExpenseAmt(String expenseAmt) {
		this.expenseAmt = expenseAmt;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getProofRequired() {
		return proofRequired;
	}

	public void setProofRequired(String proofRequired) {
		this.proofRequired = proofRequired;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTotEligibleAmount() {
		return totEligibleAmount;
	}

	public void setTotEligibleAmount(String totEligibleAmount) {
		this.totEligibleAmount = totEligibleAmount;
	}

	public String getTotExpAmount() {
		return totExpAmount;
	}

	public void setTotExpAmount(String totExpAmount) {
		this.totExpAmount = totExpAmount;
	}

	public String getProofRequiredIt() {
		return proofRequiredIt;
	}

	public void setProofRequiredIt(String proofRequiredIt) {
		this.proofRequiredIt = proofRequiredIt;
	}

	public String getTotalPageDraft() {
		return totalPageDraft;
	}

	public void setTotalPageDraft(String totalPageDraft) {
		this.totalPageDraft = totalPageDraft;
	}

	public String getPageNoDraft() {
		return PageNoDraft;
	}

	public void setPageNoDraft(String pageNoDraft) {
		PageNoDraft = pageNoDraft;
	}

	public String getRowDraft() {
		return rowDraft;
	}

	public void setRowDraft(String rowDraft) {
		this.rowDraft = rowDraft;
	}

	public String getAppIdDraft() {
		return appIdDraft;
	}

	public void setAppIdDraft(String appIdDraft) {
		this.appIdDraft = appIdDraft;
	}

	public String getAppCodeDraft() {
		return appCodeDraft;
	}

	public void setAppCodeDraft(String appCodeDraft) {
		this.appCodeDraft = appCodeDraft;
	}

	public String getAppStatusDraft() {
		return appStatusDraft;
	}

	public void setAppStatusDraft(String appStatusDraft) {
		this.appStatusDraft = appStatusDraft;
	}

	public String getAppForDraft() {
		return appForDraft;
	}

	public void setAppForDraft(String appForDraft) {
		this.appForDraft = appForDraft;
	}

	public String getInitIdDraft() {
		return initIdDraft;
	}

	public void setInitIdDraft(String initIdDraft) {
		this.initIdDraft = initIdDraft;
	}

	public String getEmpIdDraft() {
		return empIdDraft;
	}

	public void setEmpIdDraft(String empIdDraft) {
		this.empIdDraft = empIdDraft;
	}

	public String getTrvlIdDraft() {
		return trvlIdDraft;
	}

	public void setTrvlIdDraft(String trvlIdDraft) {
		this.trvlIdDraft = trvlIdDraft;
	}

	public String getAppEndDateDraft() {
		return appEndDateDraft;
	}

	public void setAppEndDateDraft(String appEndDateDraft) {
		this.appEndDateDraft = appEndDateDraft;
	}

	public String getInitNameDraft() {
		return initNameDraft;
	}

	public void setInitNameDraft(String initNameDraft) {
		this.initNameDraft = initNameDraft;
	}

	public String getEmpNameDraft() {
		return empNameDraft;
	}

	public void setEmpNameDraft(String empNameDraft) {
		this.empNameDraft = empNameDraft;
	}

	public ArrayList getDraftAppList() {
		return draftAppList;
	}

	public void setDraftAppList(ArrayList draftAppList) {
		this.draftAppList = draftAppList;
	}

	public String getAppCombinedIdDraft() {
		return appCombinedIdDraft;
	}

	public void setAppCombinedIdDraft(String appCombinedIdDraft) {
		this.appCombinedIdDraft = appCombinedIdDraft;
	}

	public String getTrvlReqNameDraft() {
		return trvlReqNameDraft;
	}

	public void setTrvlReqNameDraft(String trvlReqNameDraft) {
		this.trvlReqNameDraft = trvlReqNameDraft;
	}

	public String getTotalPageSubmit() {
		return totalPageSubmit;
	}

	public void setTotalPageSubmit(String totalPageSubmit) {
		this.totalPageSubmit = totalPageSubmit;
	}

	public String getPageNoSubmit() {
		return PageNoSubmit;
	}

	public void setPageNoSubmit(String pageNoSubmit) {
		PageNoSubmit = pageNoSubmit;
	}

	public String getRowSubmit() {
		return rowSubmit;
	}

	public void setRowSubmit(String rowSubmit) {
		this.rowSubmit = rowSubmit;
	}

	public String getAppIdSubmit() {
		return appIdSubmit;
	}

	public void setAppIdSubmit(String appIdSubmit) {
		this.appIdSubmit = appIdSubmit;
	}

	public String getAppCodeSubmit() {
		return appCodeSubmit;
	}

	public void setAppCodeSubmit(String appCodeSubmit) {
		this.appCodeSubmit = appCodeSubmit;
	}

	public String getAppStatusSubmit() {
		return appStatusSubmit;
	}

	public void setAppStatusSubmit(String appStatusSubmit) {
		this.appStatusSubmit = appStatusSubmit;
	}

	public String getAppForSubmit() {
		return appForSubmit;
	}

	public void setAppForSubmit(String appForSubmit) {
		this.appForSubmit = appForSubmit;
	}

	public String getInitIdSubmit() {
		return initIdSubmit;
	}

	public void setInitIdSubmit(String initIdSubmit) {
		this.initIdSubmit = initIdSubmit;
	}

	public String getEmpIdSubmit() {
		return empIdSubmit;
	}

	public void setEmpIdSubmit(String empIdSubmit) {
		this.empIdSubmit = empIdSubmit;
	}

	public String getTrvlIdSubmit() {
		return trvlIdSubmit;
	}

	public void setTrvlIdSubmit(String trvlIdSubmit) {
		this.trvlIdSubmit = trvlIdSubmit;
	}

	public String getAppEndDateSubmit() {
		return appEndDateSubmit;
	}

	public void setAppEndDateSubmit(String appEndDateSubmit) {
		this.appEndDateSubmit = appEndDateSubmit;
	}

	public String getInitNameSubmit() {
		return initNameSubmit;
	}

	public void setInitNameSubmit(String initNameSubmit) {
		this.initNameSubmit = initNameSubmit;
	}

	public String getEmpNameSubmit() {
		return empNameSubmit;
	}

	public void setEmpNameSubmit(String empNameSubmit) {
		this.empNameSubmit = empNameSubmit;
	}

	public String getTrvlReqNameSubmit() {
		return trvlReqNameSubmit;
	}

	public void setTrvlReqNameSubmit(String trvlReqNameSubmit) {
		this.trvlReqNameSubmit = trvlReqNameSubmit;
	}

	public ArrayList getSubmitAppList() {
		return submitAppList;
	}

	public void setSubmitAppList(ArrayList submitAppList) {
		this.submitAppList = submitAppList;
	}

	public String getTotalPageReturn() {
		return totalPageReturn;
	}

	public void setTotalPageReturn(String totalPageReturn) {
		this.totalPageReturn = totalPageReturn;
	}

	public String getPageNoReturn() {
		return PageNoReturn;
	}

	public void setPageNoReturn(String pageNoReturn) {
		PageNoReturn = pageNoReturn;
	}

	public String getRowReturn() {
		return rowReturn;
	}

	public void setRowReturn(String rowReturn) {
		this.rowReturn = rowReturn;
	}

	public String getAppIdReturn() {
		return appIdReturn;
	}

	public void setAppIdReturn(String appIdReturn) {
		this.appIdReturn = appIdReturn;
	}

	public String getAppCodeReturn() {
		return appCodeReturn;
	}

	public void setAppCodeReturn(String appCodeReturn) {
		this.appCodeReturn = appCodeReturn;
	}

	public String getAppStatusReturn() {
		return appStatusReturn;
	}

	public void setAppStatusReturn(String appStatusReturn) {
		this.appStatusReturn = appStatusReturn;
	}

	public String getAppForReturn() {
		return appForReturn;
	}

	public void setAppForReturn(String appForReturn) {
		this.appForReturn = appForReturn;
	}

	public String getInitIdReturn() {
		return initIdReturn;
	}

	public void setInitIdReturn(String initIdReturn) {
		this.initIdReturn = initIdReturn;
	}

	public String getEmpIdReturn() {
		return empIdReturn;
	}

	public void setEmpIdReturn(String empIdReturn) {
		this.empIdReturn = empIdReturn;
	}

	public String getTrvlIdReturn() {
		return trvlIdReturn;
	}

	public void setTrvlIdReturn(String trvlIdReturn) {
		this.trvlIdReturn = trvlIdReturn;
	}

	public String getAppEndDateReturn() {
		return appEndDateReturn;
	}

	public void setAppEndDateReturn(String appEndDateReturn) {
		this.appEndDateReturn = appEndDateReturn;
	}

	public String getInitNameReturn() {
		return initNameReturn;
	}

	public void setInitNameReturn(String initNameReturn) {
		this.initNameReturn = initNameReturn;
	}

	public String getEmpNameReturn() {
		return empNameReturn;
	}

	public void setEmpNameReturn(String empNameReturn) {
		this.empNameReturn = empNameReturn;
	}

	public String getTrvlReqNameReturn() {
		return trvlReqNameReturn;
	}

	public void setTrvlReqNameReturn(String trvlReqNameReturn) {
		this.trvlReqNameReturn = trvlReqNameReturn;
	}

	public ArrayList getReturnAppList() {
		return returnAppList;
	}

	public void setReturnAppList(ArrayList returnAppList) {
		this.returnAppList = returnAppList;
	}

	public String getDraftExpAppId() {
		return draftExpAppId;
	}

	public void setDraftExpAppId(String draftExpAppId) {
		this.draftExpAppId = draftExpAppId;
	}

	public String getSubmitExpAppId() {
		return submitExpAppId;
	}

	public void setSubmitExpAppId(String submitExpAppId) {
		this.submitExpAppId = submitExpAppId;
	}

	public String getReturnExpAppId() {
		return returnExpAppId;
	}

	public void setReturnExpAppId(String returnExpAppId) {
		this.returnExpAppId = returnExpAppId;
	}

	public ArrayList getExpenseDtlViewList() {
		return expenseDtlViewList;
	}

	public void setExpenseDtlViewList(ArrayList expenseDtlViewList) {
		this.expenseDtlViewList = expenseDtlViewList;
	}

	public String getAddNewFlag() {
		return addNewFlag;
	}

	public void setAddNewFlag(String addNewFlag) {
		this.addNewFlag = addNewFlag;
	}

	public String getDraftFlag() {
		return draftFlag;
	}

	public void setDraftFlag(String draftFlag) {
		this.draftFlag = draftFlag;
	}

	public String getExpAppDateDraft() {
		return expAppDateDraft;
	}

	public void setExpAppDateDraft(String expAppDateDraft) {
		this.expAppDateDraft = expAppDateDraft;
	}

	public String getRequiredAdvanceAmt() {
		return requiredAdvanceAmt;
	}

	public void setRequiredAdvanceAmt(String requiredAdvanceAmt) {
		this.requiredAdvanceAmt = requiredAdvanceAmt;
	}

	public String getClaimApplnCodeDraft() {
		return claimApplnCodeDraft;
	}

	public void setClaimApplnCodeDraft(String claimApplnCodeDraft) {
		this.claimApplnCodeDraft = claimApplnCodeDraft;
	}

	public String getExpDtlId() {
		return expDtlId;
	}

	public void setExpDtlId(String expDtlId) {
		this.expDtlId = expDtlId;
	}

	public String getExpItId() {
		return expItId;
	}

	public void setExpItId(String expItId) {
		this.expItId = expItId;
	}

	public String getDelExp() {
		return delExp;
	}

	public void setDelExp(String delExp) {
		this.delExp = delExp;
	}

	public String getTotalPageApprove() {
		return totalPageApprove;
	}

	public void setTotalPageApprove(String totalPageApprove) {
		this.totalPageApprove = totalPageApprove;
	}

	public String getPageNoApprove() {
		return PageNoApprove;
	}

	public void setPageNoApprove(String pageNoApprove) {
		PageNoApprove = pageNoApprove;
	}

	public String getRowApprove() {
		return rowApprove;
	}

	public void setRowApprove(String rowApprove) {
		this.rowApprove = rowApprove;
	}

	public String getApproveExpAppId() {
		return approveExpAppId;
	}

	public void setApproveExpAppId(String approveExpAppId) {
		this.approveExpAppId = approveExpAppId;
	}

	public String getAppIdApprove() {
		return appIdApprove;
	}

	public void setAppIdApprove(String appIdApprove) {
		this.appIdApprove = appIdApprove;
	}

	public String getAppCodeApprove() {
		return appCodeApprove;
	}

	public void setAppCodeApprove(String appCodeApprove) {
		this.appCodeApprove = appCodeApprove;
	}

	public String getAppStatusApprove() {
		return appStatusApprove;
	}

	public void setAppStatusApprove(String appStatusApprove) {
		this.appStatusApprove = appStatusApprove;
	}

	public String getAppForApprove() {
		return appForApprove;
	}

	public void setAppForApprove(String appForApprove) {
		this.appForApprove = appForApprove;
	}

	public String getInitIdApprove() {
		return initIdApprove;
	}

	public void setInitIdApprove(String initIdApprove) {
		this.initIdApprove = initIdApprove;
	}

	public String getEmpIdApprove() {
		return empIdApprove;
	}

	public void setEmpIdApprove(String empIdApprove) {
		this.empIdApprove = empIdApprove;
	}

	public String getTrvlIdApprove() {
		return trvlIdApprove;
	}

	public void setTrvlIdApprove(String trvlIdApprove) {
		this.trvlIdApprove = trvlIdApprove;
	}

	public String getAppEndDateApprove() {
		return appEndDateApprove;
	}

	public void setAppEndDateApprove(String appEndDateApprove) {
		this.appEndDateApprove = appEndDateApprove;
	}

	public String getInitNameApprove() {
		return initNameApprove;
	}

	public void setInitNameApprove(String initNameApprove) {
		this.initNameApprove = initNameApprove;
	}

	public String getEmpNameApprove() {
		return empNameApprove;
	}

	public void setEmpNameApprove(String empNameApprove) {
		this.empNameApprove = empNameApprove;
	}

	public String getTrvlReqNameApprove() {
		return trvlReqNameApprove;
	}

	public void setTrvlReqNameApprove(String trvlReqNameApprove) {
		this.trvlReqNameApprove = trvlReqNameApprove;
	}

	public ArrayList getApproveAppList() {
		return approveAppList;
	}

	public void setApproveAppList(ArrayList approveAppList) {
		this.approveAppList = approveAppList;
	}

	public String getTotalPageClose() {
		return totalPageClose;
	}

	public void setTotalPageClose(String totalPageClose) {
		this.totalPageClose = totalPageClose;
	}

	public String getPageNoClose() {
		return PageNoClose;
	}

	public void setPageNoClose(String pageNoClose) {
		PageNoClose = pageNoClose;
	}

	public String getRowClose() {
		return rowClose;
	}

	public void setRowClose(String rowClose) {
		this.rowClose = rowClose;
	}

	public String getCloseExpAppId() {
		return closeExpAppId;
	}

	public void setCloseExpAppId(String closeExpAppId) {
		this.closeExpAppId = closeExpAppId;
	}

	public String getAppIdClose() {
		return appIdClose;
	}

	public void setAppIdClose(String appIdClose) {
		this.appIdClose = appIdClose;
	}

	public String getAppCodeClose() {
		return appCodeClose;
	}

	public void setAppCodeClose(String appCodeClose) {
		this.appCodeClose = appCodeClose;
	}

	public String getAppStatusClose() {
		return appStatusClose;
	}

	public void setAppStatusClose(String appStatusClose) {
		this.appStatusClose = appStatusClose;
	}

	public String getAppForClose() {
		return appForClose;
	}

	public void setAppForClose(String appForClose) {
		this.appForClose = appForClose;
	}

	public String getInitIdClose() {
		return initIdClose;
	}

	public void setInitIdClose(String initIdClose) {
		this.initIdClose = initIdClose;
	}

	public String getEmpIdClose() {
		return empIdClose;
	}

	public void setEmpIdClose(String empIdClose) {
		this.empIdClose = empIdClose;
	}

	public String getTrvlIdClose() {
		return trvlIdClose;
	}

	public void setTrvlIdClose(String trvlIdClose) {
		this.trvlIdClose = trvlIdClose;
	}

	public String getAppEndDateClose() {
		return appEndDateClose;
	}

	public void setAppEndDateClose(String appEndDateClose) {
		this.appEndDateClose = appEndDateClose;
	}

	public String getInitNameClose() {
		return initNameClose;
	}

	public void setInitNameClose(String initNameClose) {
		this.initNameClose = initNameClose;
	}

	public String getEmpNameClose() {
		return empNameClose;
	}

	public void setEmpNameClose(String empNameClose) {
		this.empNameClose = empNameClose;
	}

	public String getTrvlReqNameClose() {
		return trvlReqNameClose;
	}

	public void setTrvlReqNameClose(String trvlReqNameClose) {
		this.trvlReqNameClose = trvlReqNameClose;
	}

	public ArrayList getCloseAppList() {
		return closeAppList;
	}

	public void setCloseAppList(ArrayList closeAppList) {
		this.closeAppList = closeAppList;
	}

	public String getMyPageClose() {
		return myPageClose;
	}

	public void setMyPageClose(String myPageClose) {
		this.myPageClose = myPageClose;
	}

	public String getSchExpAppId() {
		return schExpAppId;
	}

	public void setSchExpAppId(String schExpAppId) {
		this.schExpAppId = schExpAppId;
	}

	public String getSchFlag() {
		return schFlag;
	}

	public void setSchFlag(String schFlag) {
		this.schFlag = schFlag;
	}

	/**
	 * @return the totElgAmt
	 */
	public String getTotElgAmt() {
		return totElgAmt;
	}

	/**
	 * @param totElgAmt
	 *            the totElgAmt to set
	 */
	public void setTotElgAmt(String totElgAmt) {
		this.totElgAmt = totElgAmt;
	}

	/**
	 * @return the totExpAmt
	 */
	public String getTotExpAmt() {
		return totExpAmt;
	}

	/**
	 * @param totExpAmt
	 *            the totExpAmt to set
	 */
	public void setTotExpAmt(String totExpAmt) {
		this.totExpAmt = totExpAmt;
	}

	public String getExpTabLength() {
		return expTabLength;
	}

	public void setExpTabLength(String expTabLength) {
		this.expTabLength = expTabLength;
	}

	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	public String getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}

	public String getReturnFlag() {
		return returnFlag;
	}

	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
	}

	public String getApprovedFlag() {
		return approvedFlag;
	}

	public void setApprovedFlag(String approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	public String getClosedFlag() {
		return closedFlag;
	}

	public void setClosedFlag(String closedFlag) {
		this.closedFlag = closedFlag;
	}

	/**
	 * @return the tmsExpType
	 */
	public String getTmsExpType() {
		return tmsExpType;
	}

	/**
	 * @param tmsExpType
	 *            the tmsExpType to set
	 */
	public void setTmsExpType(String tmsExpType) {
		this.tmsExpType = tmsExpType;
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
	 * @param dtlAppDate
	 *            the bDtlAppDate to set
	 */
	public void setBDtlAppDate(String dtlAppDate) {
		bDtlAppDate = dtlAppDate;
	}

	public String getBDtlForFlag() {
		return bDtlForFlag;
	}

	public void setBDtlForFlag(String dtlForFlag) {
		bDtlForFlag = dtlForFlag;
	}

	public String getTmsTrvlId() {
		return tmsTrvlId;
	}

	public void setTmsTrvlId(String tmsTrvlId) {
		this.tmsTrvlId = tmsTrvlId;
	}

	public String getTmsTrvlIndiId() {
		return tmsTrvlIndiId;
	}

	public void setTmsTrvlIndiId(String tmsTrvlIndiId) {
		this.tmsTrvlIndiId = tmsTrvlIndiId;
	}

	public String getTmsChkTypeFlg() {
		return tmsChkTypeFlg;
	}

	public void setTmsChkTypeFlg(String tmsChkTypeFlg) {
		this.tmsChkTypeFlg = tmsChkTypeFlg;
	}

	public String getDeskFlag() {
		return deskFlag;
	}

	public void setDeskFlag(String deskFlag) {
		this.deskFlag = deskFlag;
	}

	public TreeMap getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}

	public boolean isCityGradeFlag() {
		return cityGradeFlag;
	}

	public void setCityGradeFlag(boolean cityGradeFlag) {
		this.cityGradeFlag = cityGradeFlag;
	}

	public String getExpValuewithBill() {
		return expValuewithBill;
	}

	public void setExpValuewithBill(String expValuewithBill) {
		this.expValuewithBill = expValuewithBill;
	}

	public String getExpValuewithoutBill() {
		return expValuewithoutBill;
	}

	public void setExpValuewithoutBill(String expValuewithoutBill) {
		this.expValuewithoutBill = expValuewithoutBill;
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

	public String getProofFileName() {
		return proofFileName;
	}

	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
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

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getAmountWithoutBill() {
		return amountWithoutBill;
	}

	public void setAmountWithoutBill(String amountWithoutBill) {
		this.amountWithoutBill = amountWithoutBill;
	}

	public String getAmountWithBill() {
		return amountWithBill;
	}

	public void setAmountWithBill(String amountWithBill) {
		this.amountWithBill = amountWithBill;
	}

	public String getEligibleAmtWithBillIt() {
		return eligibleAmtWithBillIt;
	}

	public void setEligibleAmtWithBillIt(String eligibleAmtWithBillIt) {
		this.eligibleAmtWithBillIt = eligibleAmtWithBillIt;
	}

	public String getEligibleAmtWithoutBillIt() {
		return eligibleAmtWithoutBillIt;
	}

	public void setEligibleAmtWithoutBillIt(String eligibleAmtWithoutBillIt) {
		this.eligibleAmtWithoutBillIt = eligibleAmtWithoutBillIt;
	}

	public String getTotElgAmtWithBill() {
		return totElgAmtWithBill;
	}

	public void setTotElgAmtWithBill(String totElgAmtWithBill) {
		this.totElgAmtWithBill = totElgAmtWithBill;
	}

	public String getTotElgAmtWithoutBill() {
		return totElgAmtWithoutBill;
	}

	public void setTotElgAmtWithoutBill(String totElgAmtWithoutBill) {
		this.totElgAmtWithoutBill = totElgAmtWithoutBill;
	}

	public ArrayList getIttUploadList() {
		return ittUploadList;
	}

	public void setIttUploadList(ArrayList ittUploadList) {
		this.ittUploadList = ittUploadList;
	}

	public String getIttproofName() {
		return ittproofName;
	}

	public void setIttproofName(String ittproofName) {
		this.ittproofName = ittproofName;
	}

	public boolean isEligibleAmountFlag() {
		return eligibleAmountFlag;
	}

	public void setEligibleAmountFlag(boolean eligibleAmountFlag) {
		this.eligibleAmountFlag = eligibleAmountFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAtActual() {
		return AtActual;
	}

	public void setAtActual(String atActual) {
		AtActual = atActual;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getItteratorProofNameForSave() {
		return itteratorProofNameForSave;
	}

	public void setItteratorProofNameForSave(String itteratorProofNameForSave) {
		this.itteratorProofNameForSave = itteratorProofNameForSave;
	}

	public String getCityGrade() {
		return cityGrade;
	}

	public void setCityGrade(String cityGrade) {
		this.cityGrade = cityGrade;
	}

	public boolean isClaimDueDaysFlag() {
		return claimDueDaysFlag;
	}

	public void setClaimDueDaysFlag(boolean claimDueDaysFlag) {
		this.claimDueDaysFlag = claimDueDaysFlag;
	}

	public String getPendingStatus() {
		return pendingStatus;
	}

	public void setPendingStatus(String pendingStatus) {
		this.pendingStatus = pendingStatus;
	}

	public String getPolicyViolationText() {
		return policyViolationText;
	}

	public void setPolicyViolationText(String policyViolationText) {
		this.policyViolationText = policyViolationText;
	}

	public String getPolicyViolationTextIt() {
		return policyViolationTextIt;
	}

	public void setPolicyViolationTextIt(String policyViolationTextIt) {
		this.policyViolationTextIt = policyViolationTextIt;
	}

	public ArrayList getBlockedAppList() {
		return blockedAppList;
	}

	public void setBlockedAppList(ArrayList blockedAppList) {
		this.blockedAppList = blockedAppList;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getMaximumClaimDueDays() {
		return maximumClaimDueDays;
	}

	public void setMaximumClaimDueDays(String maximumClaimDueDays) {
		this.maximumClaimDueDays = maximumClaimDueDays;
	}

	public String getMaxClaimDays() {
		return maxClaimDays;
	}

	public void setMaxClaimDays(String maxClaimDays) {
		this.maxClaimDays = maxClaimDays;
	}

	public String getShowBookingDetailsFlag() {
		return showBookingDetailsFlag;
	}

	public void setShowBookingDetailsFlag(String showBookingDetailsFlag) {
		this.showBookingDetailsFlag = showBookingDetailsFlag;
	}

	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}

	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	public String getApproverCommentsFlag() {
		return approverCommentsFlag;
	}

	public void setApproverCommentsFlag(String approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}

	public String getApproverListFlag() {
		return approverListFlag;
	}

	public void setApproverListFlag(String approverListFlag) {
		this.approverListFlag = approverListFlag;
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

	public ArrayList getTravelRatingParameterList() {
		return travelRatingParameterList;
	}

	public void setTravelRatingParameterList(ArrayList travelRatingParameterList) {
		this.travelRatingParameterList = travelRatingParameterList;
	}

	public String getDeskRatingIdItt() {
		return deskRatingIdItt;
	}

	public void setDeskRatingIdItt(String deskRatingIdItt) {
		this.deskRatingIdItt = deskRatingIdItt;
	}

	public String getDeskRatingNameItt() {
		return deskRatingNameItt;
	}

	public void setDeskRatingNameItt(String deskRatingNameItt) {
		this.deskRatingNameItt = deskRatingNameItt;
	}

	public String getDeskRatingItt() {
		return deskRatingItt;
	}

	public void setDeskRatingItt(String deskRatingItt) {
		this.deskRatingItt = deskRatingItt;
	}

	public ArrayList getHotelRatingParameterList() {
		return hotelRatingParameterList;
	}

	public void setHotelRatingParameterList(ArrayList hotelRatingParameterList) {
		this.hotelRatingParameterList = hotelRatingParameterList;
	}

	public String getHotelRatingIdItt() {
		return hotelRatingIdItt;
	}

	public void setHotelRatingIdItt(String hotelRatingIdItt) {
		this.hotelRatingIdItt = hotelRatingIdItt;
	}

	public String getHotelRatingNameItt() {
		return hotelRatingNameItt;
	}

	public void setHotelRatingNameItt(String hotelRatingNameItt) {
		this.hotelRatingNameItt = hotelRatingNameItt;
	}

	public String getHotelRatingItt() {
		return hotelRatingItt;
	}

	public void setHotelRatingItt(String hotelRatingItt) {
		this.hotelRatingItt = hotelRatingItt;
	}

	public String getTourComments() {
		return tourComments;
	}

	public void setTourComments(String tourComments) {
		this.tourComments = tourComments;
	}

	public String getTourReportFile() {
		return tourReportFile;
	}

	public void setTourReportFile(String tourReportFile) {
		this.tourReportFile = tourReportFile;
	}

	public String getAchievementComments() {
		return achievementComments;
	}

	public void setAchievementComments(String achievementComments) {
		this.achievementComments = achievementComments;
	}

	public String getFollowUpComments() {
		return followUpComments;
	}

	public void setFollowUpComments(String followUpComments) {
		this.followUpComments = followUpComments;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isShowFlag() {
		return showFlag;
	}

	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
	}

	public String getHiddenStatus() {
		return hiddenStatus;
	}

	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	public ArrayList getHotelNameList() {
		return hotelNameList;
	}

	public void setHotelNameList(ArrayList hotelNameList) {
		this.hotelNameList = hotelNameList;
	}

	public String getHotelNameItt() {
		return hotelNameItt;
	}

	public void setHotelNameItt(String hotelNameItt) {
		this.hotelNameItt = hotelNameItt;
	}

	public String getHotelIdItt() {
		return hotelIdItt;
	}

	public void setHotelIdItt(String hotelIdItt) {
		this.hotelIdItt = hotelIdItt;
	}

	public String getSourceDestination() {
		return sourceDestination;
	}

	public void setSourceDestination(String sourceDestination) {
		this.sourceDestination = sourceDestination;
	}

	public boolean isShowHotelRatingFlag() {
		return showHotelRatingFlag;
	}

	public void setShowHotelRatingFlag(boolean showHotelRatingFlag) {
		this.showHotelRatingFlag = showHotelRatingFlag;
	}

	public String getFollowUpCommentsItt() {
		return followUpCommentsItt;
	}

	public void setFollowUpCommentsItt(String followUpCommentsItt) {
		this.followUpCommentsItt = followUpCommentsItt;
	}

	public String getResponsibleEmpIdItt() {
		return responsibleEmpIdItt;
	}

	public void setResponsibleEmpIdItt(String responsibleEmpIdItt) {
		this.responsibleEmpIdItt = responsibleEmpIdItt;
	}

	public String getResponsibleEmpTokenItt() {
		return responsibleEmpTokenItt;
	}

	public void setResponsibleEmpTokenItt(String responsibleEmpTokenItt) {
		this.responsibleEmpTokenItt = responsibleEmpTokenItt;
	}

	public String getResponsibleEmpItt() {
		return responsibleEmpItt;
	}

	public void setResponsibleEmpItt(String responsibleEmpItt) {
		this.responsibleEmpItt = responsibleEmpItt;
	}

	public String getTargetDateItt() {
		return targetDateItt;
	}

	public void setTargetDateItt(String targetDateItt) {
		this.targetDateItt = targetDateItt;
	}

	public ArrayList getFollowUpActionList() {
		return followUpActionList;
	}

	public void setFollowUpActionList(ArrayList followUpActionList) {
		this.followUpActionList = followUpActionList;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCurrencyEmployeeAdvance() {
		return currencyEmployeeAdvance;
	}

	public void setCurrencyEmployeeAdvance(String currencyEmployeeAdvance) {
		this.currencyEmployeeAdvance = currencyEmployeeAdvance;
	}

	public String getCurrencyExpenseAmt() {
		return currencyExpenseAmt;
	}

	public void setCurrencyExpenseAmt(String currencyExpenseAmt) {
		this.currencyExpenseAmt = currencyExpenseAmt;
	}

	public String getCurrencyExpenseAmtItr() {
		return currencyExpenseAmtItr;
	}

	public void setCurrencyExpenseAmtItr(String currencyExpenseAmtItr) {
		this.currencyExpenseAmtItr = currencyExpenseAmtItr;
	}

	public String getTotalCurrencyExpense() {
		return totalCurrencyExpense;
	}

	public void setTotalCurrencyExpense(String totalCurrencyExpense) {
		this.totalCurrencyExpense = totalCurrencyExpense;
	}

	public String getDefaultCurrencyFlag() {
		return defaultCurrencyFlag;
	}

	public void setDefaultCurrencyFlag(String defaultCurrencyFlag) {
		this.defaultCurrencyFlag = defaultCurrencyFlag;
	}

	public String getHiddenProofCnt() {
		return hiddenProofCnt;
	}

	public void setHiddenProofCnt(String hiddenProofCnt) {
		this.hiddenProofCnt = hiddenProofCnt;
	}
	
}
