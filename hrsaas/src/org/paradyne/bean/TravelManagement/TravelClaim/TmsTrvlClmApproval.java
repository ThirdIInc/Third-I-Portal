package org.paradyne.bean.TravelManagement.TravelClaim;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class TmsTrvlClmApproval extends BeanBase {
	
	private String currencyEmployeeAdvance = "";
	private String totalCurrencyExpense = "";
	private String currencyExpenseAmt = "";
	//for claim approved application View
	private String source="";
	private String searchempId="";
	private String searchempName="";
	private String searchtravelId="";
	private String searchemptoken="";
	private String hidenstatus="";
	
	//Added by Prashant 
	
	private String lodgeExpenditureAmount  = "";
	private String travelExpenditureAmount  = "";
	private String claimExpenditureAmount  = "";
	
	private String followUpCommentsItt  = "";
	private String responsibleEmpIdItt  = "";
	private String responsibleEmpTokenItt  = "";
	private String responsibleEmpItt  = "";
	private String targetDateItt  = "";
	private ArrayList followUpActionList =null;
	
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
	private String travelApplicationId  = "";
	
	private String tourComments  = "";
	private String tourReportFile  = "";
	private String achievementComments  = "";
	private String followUpComments  = "";
	private String targetDate  = "";
	
	//Added by vishwambhar
	
	private String hiddenApplicationCode="";
	private String level= "";
	private String checkApproveRejectStatus="";
	private boolean revokeFlag = false;
	private ArrayList approverCommentList =null ; 
	private String budgetExpenditure = "";
	private String approverCommentsFlag ="false";
	private String approverListFlag ="false";
	
	private String appSrNo  = "";
	private String prevApproverID  = "";
	private String prevApproverName  = "";
	private String prevApproverDate  = "";
	private String prevApproverStatus  = "";
	private String prevApproverComment  = "";
	
	private String showComments ="false";
	
	private String clmAppFlag="";
	private String clmAppCmts="";
	private String clmApplStatus="";
	private String checkStatus = "";
	
	//for closed claim applications view purpose
	
	private String totDisbAmt="";
	
	//for pay details
	ArrayList payDtls = new ArrayList();
	private String payDate="";
	private String payMode="";
	private String payAmt="";
	private String payCmt="";
	private String totPayAmt="";
	private String balPayAmt="";
	// for paging
	private String hdPage = "";
	private String fromTotRec = "";
	private String toTotRec = "";

	// for new paging

	private String myPage = "";
	private String status = "";
	private boolean pageFlag = false;

	// control flags
	private boolean noData = false;
	private String pen = "";
	private String Apprvd = "";
	private String Retrned = "";
	// for iterator
	ArrayList travelClmList = new ArrayList();

	private String travelId = "";
	private String trvlEmpId = "";
	private String empRguestName = "";
	private String trvlRqstName = "";
	private String trvlDate = "";
	private String clmAppDate = "";
	private String initrId = "";
	private String initrName = "";
	private String expType = "";
	private String apprvrLevel = "";
	private String trvlClmId = "";
	private String trvlAppId = "";
	private String trvlAppCode = "";

	// for navigation purpose

	private String tmsClmAppId = "";
	private String tmsTrvlId = "";
	private String tmsTrvlCode = "";
	private String tmsExpType = "";
	private String tmsApprvrLevel = "";

	// Employee Details in view

	private String empCode = "";
	private String empName = "";
	private String trvlAppFor = "";
	private String trvlAppDate = "";
	private String trvlStartDate = "";
	private String trvlEndDate = "";
	private String empBranch = "";
	private String empDept = "";
	private String empGrade = "";
	private String empDesgn = "";
	private String clmApplDate = "";
	private String clmStatus = "";

    private String clmTrvlRqstName="";
	private String clmPurpose = "";
	private String clmAdvance = "";
	private String clmTrvlType = "";

	// for expense details

	ArrayList expDtls = new ArrayList();
	private String expDtlId = "";
	private String expExpAppId = "";
	private String expDate = "";
	private String expName = "";
	private String expElgblAmt = "";
	private String expExpAmt = "";
	private String expParticlrs = "";
	private String expIsProof = "";
	private String expProofPath = "";

	private String totElgblAmt = "";
	private String totExpAmt = "";
	private String apprvdAmt = "";
	private String clmApprCmts = "";
	private String navStatus = "";
	private String statusSave = "";
	
	private String proofName="";

	private String gradId = "";

	// for viewing travel policy

	private String appDate = "";
	private String startDate = "";
	private String endDate = "";

	
	//for showing booking details
	
	private String bDtlAppId="";
	private String bDtlAppCode="";
	private String bDtlInitrId="";
	private String bDtlEmpId="";
	private String bDtlAppDate="";
	
	//for comments
	
	private String cmtlableName="";
	private String comments="";
	ArrayList cmtsList = new ArrayList();
	
	//added ganesh
	private String projectId = "";
	private String project ="";
	private String customerId ="";
	private String customerName ="";
	
	ArrayList expProofPathList = null;
	
	
	private String actualExpenditure = "";
	private String isPolicyViolated = "";
	
	public String getActualExpenditure() {
		return actualExpenditure;
	}

	public void setActualExpenditure(String actualExpenditure) {
		this.actualExpenditure = actualExpenditure;
	}

	public ArrayList getExpProofPathList() {
		return expProofPathList;
	}

	public void setExpProofPathList(ArrayList expProofPathList) {
		this.expProofPathList = expProofPathList;
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

	public String getApprvdAmt() {
		return apprvdAmt;
	}

	public void setApprvdAmt(String apprvdAmt) {
		this.apprvdAmt = apprvdAmt;
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

	public String getExpDtlId() {
		return expDtlId;
	}

	public ArrayList getExpDtls() {
		return expDtls;
	}

	public void setExpDtls(ArrayList expDtls) {
		this.expDtls = expDtls;
	}

	public void setExpDtlId(String expDtlId) {
		this.expDtlId = expDtlId;
	}

	public String getExpExpAppId() {
		return expExpAppId;
	}

	public void setExpExpAppId(String expExpAppId) {
		this.expExpAppId = expExpAppId;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getExpName() {
		return expName;
	}

	public void setExpName(String expName) {
		this.expName = expName;
	}

	public String getExpElgblAmt() {
		return expElgblAmt;
	}

	public void setExpElgblAmt(String expElgblAmt) {
		this.expElgblAmt = expElgblAmt;
	}

	public String getExpExpAmt() {
		return expExpAmt;
	}

	public void setExpExpAmt(String expExpAmt) {
		this.expExpAmt = expExpAmt;
	}

	public String getExpParticlrs() {
		return expParticlrs;
	}

	public void setExpParticlrs(String expParticlrs) {
		this.expParticlrs = expParticlrs;
	}

	public String getExpIsProof() {
		return expIsProof;
	}

	public void setExpIsProof(String expIsProof) {
		this.expIsProof = expIsProof;
	}

	public String getExpProofPath() {
		return expProofPath;
	}

	public void setExpProofPath(String expProofPath) {
		this.expProofPath = expProofPath;
	}

	public String getHdPage() {
		return hdPage;
	}

	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}

	public String getFromTotRec() {
		return fromTotRec;
	}

	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}

	public String getToTotRec() {
		return toTotRec;
	}

	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}

	public boolean isNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}

	public String getPen() {
		return pen;
	}

	public void setPen(String pen) {
		this.pen = pen;
	}

	public String getApprvd() {
		return Apprvd;
	}

	public void setApprvd(String apprvd) {
		Apprvd = apprvd;
	}

	public String getRetrned() {
		return Retrned;
	}

	public void setRetrned(String retrned) {
		Retrned = retrned;
	}

	public ArrayList getTravelClmList() {
		return travelClmList;
	}

	public void setTravelClmList(ArrayList travelClmList) {
		this.travelClmList = travelClmList;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	public String getTrvlEmpId() {
		return trvlEmpId;
	}

	public void setTrvlEmpId(String trvlEmpId) {
		this.trvlEmpId = trvlEmpId;
	}

	public String getEmpRguestName() {
		return empRguestName;
	}

	public void setEmpRguestName(String empRguestName) {
		this.empRguestName = empRguestName;
	}

	public String getTrvlRqstName() {
		return trvlRqstName;
	}

	public void setTrvlRqstName(String trvlRqstName) {
		this.trvlRqstName = trvlRqstName;
	}

	public String getTrvlDate() {
		return trvlDate;
	}

	public void setTrvlDate(String trvlDate) {
		this.trvlDate = trvlDate;
	}

	public String getClmAppDate() {
		return clmAppDate;
	}

	public void setClmAppDate(String clmAppDate) {
		this.clmAppDate = clmAppDate;
	}

	public String getInitrId() {
		return initrId;
	}

	public void setInitrId(String initrId) {
		this.initrId = initrId;
	}

	public String getInitrName() {
		return initrName;
	}

	public void setInitrName(String initrName) {
		this.initrName = initrName;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getApprvrLevel() {
		return apprvrLevel;
	}

	public void setApprvrLevel(String apprvrLevel) {
		this.apprvrLevel = apprvrLevel;
	}

	public String getTrvlClmId() {
		return trvlClmId;
	}

	public void setTrvlClmId(String trvlClmId) {
		this.trvlClmId = trvlClmId;
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

	public String getTmsClmAppId() {
		return tmsClmAppId;
	}

	public void setTmsClmAppId(String tmsClmAppId) {
		this.tmsClmAppId = tmsClmAppId;
	}

	public String getTmsTrvlId() {
		return tmsTrvlId;
	}

	public void setTmsTrvlId(String tmsTrvlId) {
		this.tmsTrvlId = tmsTrvlId;
	}

	public String getTmsTrvlCode() {
		return tmsTrvlCode;
	}

	public void setTmsTrvlCode(String tmsTrvlCode) {
		this.tmsTrvlCode = tmsTrvlCode;
	}

	public String getTmsExpType() {
		return tmsExpType;
	}

	public void setTmsExpType(String tmsExpType) {
		this.tmsExpType = tmsExpType;
	}

	public String getTmsApprvrLevel() {
		return tmsApprvrLevel;
	}

	public void setTmsApprvrLevel(String tmsApprvrLevel) {
		this.tmsApprvrLevel = tmsApprvrLevel;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTrvlStartDate() {
		return trvlStartDate;
	}

	public void setTrvlStartDate(String trvlStartDate) {
		this.trvlStartDate = trvlStartDate;
	}

	public String getTrvlEndDate() {
		return trvlEndDate;
	}

	public void setTrvlEndDate(String trvlEndDate) {
		this.trvlEndDate = trvlEndDate;
	}

	public String getEmpBranch() {
		return empBranch;
	}

	public void setEmpBranch(String empBranch) {
		this.empBranch = empBranch;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getEmpGrade() {
		return empGrade;
	}

	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}

	public String getEmpDesgn() {
		return empDesgn;
	}

	public void setEmpDesgn(String empDesgn) {
		this.empDesgn = empDesgn;
	}

	public String getClmApplDate() {
		return clmApplDate;
	}

	public void setClmApplDate(String clmApplDate) {
		this.clmApplDate = clmApplDate;
	}

	public String getClmStatus() {
		return clmStatus;
	}

	public void setClmStatus(String clmStatus) {
		this.clmStatus = clmStatus;
	}

	public String getTrvlAppDate() {
		return trvlAppDate;
	}

	public void setTrvlAppDate(String trvlAppDate) {
		this.trvlAppDate = trvlAppDate;
	}

	public String getTrvlAppFor() {
		return trvlAppFor;
	}

	public void setTrvlAppFor(String trvlAppFor) {
		this.trvlAppFor = trvlAppFor;
	}

	public String getClmPurpose() {
		return clmPurpose;
	}

	public void setClmPurpose(String clmPurpose) {
		this.clmPurpose = clmPurpose;
	}

	public String getClmAdvance() {
		return clmAdvance;
	}

	public void setClmAdvance(String clmAdvance) {
		this.clmAdvance = clmAdvance;
	}

	public String getClmTrvlType() {
		return clmTrvlType;
	}

	public void setClmTrvlType(String clmTrvlType) {
		this.clmTrvlType = clmTrvlType;
	}

	public String getStatusSave() {
		return statusSave;
	}

	public void setStatusSave(String statusSave) {
		this.statusSave = statusSave;
	}

	public String getClmApprCmts() {
		return clmApprCmts;
	}

	public void setClmApprCmts(String clmApprCmts) {
		this.clmApprCmts = clmApprCmts;
	}

	public String getNavStatus() {
		return navStatus;
	}

	public void setNavStatus(String navStatus) {
		this.navStatus = navStatus;
	}

	public String getGradId() {
		return gradId;
	}

	public void setGradId(String gradId) {
		this.gradId = gradId;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

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
	 * @param dtlAppId the bDtlAppId to set
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
	 * @param dtlAppCode the bDtlAppCode to set
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
	 * @param dtlInitrId the bDtlInitrId to set
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
	 * @param dtlEmpId the bDtlEmpId to set
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
	 * @return the cmtlableName
	 */
	public String getCmtlableName() {
		return cmtlableName;
	}

	/**
	 * @param cmtlableName the cmtlableName to set
	 */
	public void setCmtlableName(String cmtlableName) {
		this.cmtlableName = cmtlableName;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the cmtsList
	 */
	public ArrayList getCmtsList() {
		return cmtsList;
	}

	/**
	 * @param cmtsList the cmtsList to set
	 */
	public void setCmtsList(ArrayList cmtsList) {
		this.cmtsList = cmtsList;
	}

	/**
	 * @return the clmAppFlag
	 */
	public String getClmAppFlag() {
		return clmAppFlag;
	}

	/**
	 * @param clmAppFlag the clmAppFlag to set
	 */
	public void setClmAppFlag(String clmAppFlag) {
		this.clmAppFlag = clmAppFlag;
	}

	/**
	 * @return the clmAppCmts
	 */
	public String getClmAppCmts() {
		return clmAppCmts;
	}

	/**
	 * @param clmAppCmts the clmAppCmts to set
	 */
	public void setClmAppCmts(String clmAppCmts) {
		this.clmAppCmts = clmAppCmts;
	}

	/**
	 * @return the clmTrvlRqstName
	 */
	public String getClmTrvlRqstName() {
		return clmTrvlRqstName;
	}

	/**
	 * @param clmTrvlRqstName the clmTrvlRqstName to set
	 */
	public void setClmTrvlRqstName(String clmTrvlRqstName) {
		this.clmTrvlRqstName = clmTrvlRqstName;
	}

	/**
	 * @return the clmApplStatus
	 */
	public String getClmApplStatus() {
		return clmApplStatus;
	}

	/**
	 * @param clmApplStatus the clmApplStatus to set
	 */
	public void setClmApplStatus(String clmApplStatus) {
		this.clmApplStatus = clmApplStatus;
	}

	/**
	 * @return the totDisbAmt
	 */
	public String getTotDisbAmt() {
		return totDisbAmt;
	}

	/**
	 * @param totDisbAmt the totDisbAmt to set
	 */
	public void setTotDisbAmt(String totDisbAmt) {
		this.totDisbAmt = totDisbAmt;
	}

	/**
	 * @return the payDtls
	 */
	public ArrayList getPayDtls() {
		return payDtls;
	}

	/**
	 * @param payDtls the payDtls to set
	 */
	public void setPayDtls(ArrayList payDtls) {
		this.payDtls = payDtls;
	}

	/**
	 * @return the payDate
	 */
	public String getPayDate() {
		return payDate;
	}

	/**
	 * @param payDate the payDate to set
	 */
	public void setPayDate(String payDate) {
		this.payDate = payDate;
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
	 * @return the payAmt
	 */
	public String getPayAmt() {
		return payAmt;
	}

	/**
	 * @param payAmt the payAmt to set
	 */
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}

	/**
	 * @return the payCmt
	 */
	public String getPayCmt() {
		return payCmt;
	}

	/**
	 * @param payCmt the payCmt to set
	 */
	public void setPayCmt(String payCmt) {
		this.payCmt = payCmt;
	}

	/**
	 * @return the totPayAmt
	 */
	public String getTotPayAmt() {
		return totPayAmt;
	}

	/**
	 * @param totPayAmt the totPayAmt to set
	 */
	public void setTotPayAmt(String totPayAmt) {
		this.totPayAmt = totPayAmt;
	}

	/**
	 * @return the balPayAmt
	 */
	public String getBalPayAmt() {
		return balPayAmt;
	}

	/**
	 * @param balPayAmt the balPayAmt to set
	 */
	public void setBalPayAmt(String balPayAmt) {
		this.balPayAmt = balPayAmt;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getProofName() {
		return proofName;
	}

	public void setProofName(String proofName) {
		this.proofName = proofName;
	}

	public String getShowComments() {
		return showComments;
	}

	public void setShowComments(String showComments) {
		this.showComments = showComments;
	}

	public String getHiddenApplicationCode() {
		return hiddenApplicationCode;
	}

	public void setHiddenApplicationCode(String hiddenApplicationCode) {
		this.hiddenApplicationCode = hiddenApplicationCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCheckApproveRejectStatus() {
		return checkApproveRejectStatus;
	}

	public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
		this.checkApproveRejectStatus = checkApproveRejectStatus;
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

	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}

	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	public boolean isRevokeFlag() {
		return revokeFlag;
	}

	public void setRevokeFlag(boolean revokeFlag) {
		this.revokeFlag = revokeFlag;
	}

	public String getBudgetExpenditure() {
		return budgetExpenditure;
	}

	public void setBudgetExpenditure(String budgetExpenditure) {
		this.budgetExpenditure = budgetExpenditure;
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

	public boolean isShowHotelRatingFlag() {
		return showHotelRatingFlag;
	}

	public void setShowHotelRatingFlag(boolean showHotelRatingFlag) {
		this.showHotelRatingFlag = showHotelRatingFlag;
	}

	public ArrayList getHotelNameList() {
		return hotelNameList;
	}

	public void setHotelNameList(ArrayList hotelNameList) {
		this.hotelNameList = hotelNameList;
	}

	public ArrayList getHotelRatingParameterList() {
		return hotelRatingParameterList;
	}

	public void setHotelRatingParameterList(ArrayList hotelRatingParameterList) {
		this.hotelRatingParameterList = hotelRatingParameterList;
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

	public String getTravelApplicationId() {
		return travelApplicationId;
	}

	public void setTravelApplicationId(String travelApplicationId) {
		this.travelApplicationId = travelApplicationId;
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

	public String getLodgeExpenditureAmount() {
		return lodgeExpenditureAmount;
	}

	public void setLodgeExpenditureAmount(String lodgeExpenditureAmount) {
		this.lodgeExpenditureAmount = lodgeExpenditureAmount;
	}

	public String getTravelExpenditureAmount() {
		return travelExpenditureAmount;
	}

	public void setTravelExpenditureAmount(String travelExpenditureAmount) {
		this.travelExpenditureAmount = travelExpenditureAmount;
	}

	public String getClaimExpenditureAmount() {
		return claimExpenditureAmount;
	}

	public void setClaimExpenditureAmount(String claimExpenditureAmount) {
		this.claimExpenditureAmount = claimExpenditureAmount;
	}

	public String getIsPolicyViolated() {
		return isPolicyViolated;
	}

	public void setIsPolicyViolated(String isPolicyViolated) {
		this.isPolicyViolated = isPolicyViolated;
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

	public String getSearchtravelId() {
		return searchtravelId;
	}

	public void setSearchtravelId(String searchtravelId) {
		this.searchtravelId = searchtravelId;
	}

	public String getSearchemptoken() {
		return searchemptoken;
	}

	public void setSearchemptoken(String searchemptoken) {
		this.searchemptoken = searchemptoken;
	}

	public String getHidenstatus() {
		return hidenstatus;
	}

	public void setHidenstatus(String hidenstatus) {
		this.hidenstatus = hidenstatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCurrencyExpenseAmt() {
		return currencyExpenseAmt;
	}

	public void setCurrencyExpenseAmt(String currencyExpenseAmt) {
		this.currencyExpenseAmt = currencyExpenseAmt;
	}

	public String getTotalCurrencyExpense() {
		return totalCurrencyExpense;
	}

	public void setTotalCurrencyExpense(String totalCurrencyExpense) {
		this.totalCurrencyExpense = totalCurrencyExpense;
	}

	public String getCurrencyEmployeeAdvance() {
		return currencyEmployeeAdvance;
	}

	public void setCurrencyEmployeeAdvance(String currencyEmployeeAdvance) {
		this.currencyEmployeeAdvance = currencyEmployeeAdvance;
	}


}
