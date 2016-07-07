 package org.paradyne.bean.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class Regularization extends BeanBase {

	
	private String reasonItt ="";
	private String addQuesPageNoField="";
	private String actAddQuesPage="";
	private String viewApplication="";
	private String pageSwipe="";
	private String pageLate="";
	private String pageRedressal="";
	private String pagePT="";
	private String source="";
	/**
	 * PERSONAL TIME
	 */
	String regularizationApplCode="";
	String secondApproverCode="";
	String personalTimeFlag="false";
	String personalTime="";
	String ptDate="";
	String ptFromTime="";
	String ptToTime="";
	String ptDateItt="";
	String ptFromTimeItt="";
	String ptToTimeItt="";
	String hiddenEdit="";
	String personalTimeHH_MI="";
	String availablePT="";
	String differencePT="";
	String personalTimeCode="";
	String approverFlag="false";
	String keepHidden="";
	String ptType="";
	String hiddenDate="";
	String dataBaseFromTime="";
	String dataBaseToTime="";
	String swipeType="";
	String shiftStartTime="";
	String shiftEndTime="";
	private ArrayList  ptList=null;
	//private ptList=null;
	/**
	 * ACTION NAMES
	 */
	private String rejectActionName="";
	private String approveActionName="";
	private String sendBackActionName="";
	private String backActionName="";
	private String sendBackFlag="false";
	/**
	 * Main regularization list 
	 */
	String listType="";//pending,approved,reject
	String applicationType="";//rr,sr,lr
	String swipeAppCode="";
	String lateAppCode="";
	String redressalAppCode="";
	String applEmpCode="";
	String applicationCode="";
	String status="";
	private String listFlag="false";
	private String viewApplFlag="false";
	//private String month="";
	private String month_old = "";
	private String year="";
	private String applyFor="";
	private String applyForitt="";
	private String policyCode="";
	private String applyForName="";
	private String shiftCode="";
	private String actionName="";
	
	private ArrayList viewApproverList=null;
	private String approverNameView="";
	private String apprverComments="";
	
	/**
	 * REGULARIZATION LIST
	 */
	private String leaveCode="";
	private String leaveType="";
	private String date="";
	private String shiftTime="";
	private String inTime="";
	private String lateHrs="";
	private String lateCheckBox="";
	private ArrayList list=null;
	private String totalLateHrs="";
	private String status1="";
	private String status2="";
	private String earlyHrs="";
	private String totalHrs="";
	
	private String sLeaveType="";
	private String sLeaveCode="";
	private String sBalance="";
	private ArrayList bList=null;
	private String totalBalance="";
	/**
	 * REGULARIZATION APPLICATION
	 * @return
	 */
	private String empCode="";
	private String empName="";
	private String empToken="";
	private String empGender="";
	private String empBranch="";
	private String empDesg="";
	private String approverDate="";
	private String applicationDate="";
	private String approverName="";
	private ArrayList approverList=null;
	private String keepInform="";
	private ArrayList informList=null;
	private ArrayList regList=null;
	private String lateHrsDeduct="";
	private String adjustLeave="";
	private String reason="";
	/**
	 * FLAGS
	 * @return
	 */
	private String lateFlag="false";
	private String swipeFlag="false";
	private String redressalFlag="false";
	private String apprSrNo="";
	private String condTrueFlag="false";
	private String showButtonFlag="false";
	private String countValue="";
	private String commentsFlag="false";
	/**
	 * SWIPE CODING
	 * @return
	 */
	private ArrayList swipeList=null;
	private String fromTime="";
	private String toTime="";
	private String totalDiff="";	
	private String informCode="";
	private String informToken="";
	private String informName="";
	private String keepInformCode="";
	private String approverCode="";
	
	/**
	 * LATE REGULARIZATION
	 * @return
	 */
	private String sClosingbalance="";
	private String totalLateHrsInMin="";
	private String totalBalanceInMin="";
	private String difference="";
	private String lateHrsDeductFrom="";
	private String lateHrsDeductFromCode="";
	private String remainingBalance="";

	/**
	 * REDRESSAL APPICATION
	 * @return
	 */
	private String rFromDate="";
	private String rToDate="";
	private String rPenaltyDays="";
	private String rLeaveCode="";
	private String rrdressalDays="";
	private String redressalAdjDays="";
	private String redressalAdjStatus="";
	
	private boolean hideApproverCommentsSectionFlag = true;
	/**
	 * APPROVER FILED
	 * @return
	 */
	private String approverComents="";
	
	public String getApproverComents() {
		return approverComents;
	}
	public void setApproverComents(String approverComents) {
		this.approverComents = approverComents;
	}
	public String getRrdressalDays() {
		return rrdressalDays;
	}
	public void setRrdressalDays(String rrdressalDays) {
		this.rrdressalDays = rrdressalDays;
	}
	public String getRLeaveCode() {
		return rLeaveCode;
	}
	public void setRLeaveCode(String leaveCode) {
		rLeaveCode = leaveCode;
	}
	public String getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(String remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	public String getLateHrsDeductFrom() {
		return lateHrsDeductFrom;
	}
	public void setLateHrsDeductFrom(String lateHrsDeductFrom) {
		this.lateHrsDeductFrom = lateHrsDeductFrom;
	}
	public String getTotalBalanceInMin() {
		return totalBalanceInMin;
	}
	public void setTotalBalanceInMin(String totalBalanceInMin) {
		this.totalBalanceInMin = totalBalanceInMin;
	}
	public String getTotalLateHrsInMin() {
		return totalLateHrsInMin;
	}
	public void setTotalLateHrsInMin(String totalLateHrsInMin) {
		this.totalLateHrsInMin = totalLateHrsInMin;
	}
	public String getSClosingbalance() {
		return sClosingbalance;
	}
	public void setSClosingbalance(String closingbalance) {
		sClosingbalance = closingbalance;
	}
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	public String getKeepInformCode() {
		return keepInformCode;
	}
	public void setKeepInformCode(String keepInformCode) {
		this.keepInformCode = keepInformCode;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpBranch() {
		return empBranch;
	}
	public void setEmpBranch(String empBranch) {
		this.empBranch = empBranch;
	}
	public String getEmpDesg() {
		return empDesg;
	}
	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}
	public String getApproverDate() {
		return approverDate;
	}
	public void setApproverDate(String approverDate) {
		this.approverDate = approverDate;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public ArrayList getApproverList() {
		return approverList;
	}
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}
	public String getKeepInform() {
		return keepInform;
	}
	public void setKeepInform(String keepInform) {
		this.keepInform = keepInform;
	}
	public ArrayList getInformList() {
		return informList;
	}
	public void setInformList(ArrayList informList) {
		this.informList = informList;
	}
	public String getLateHrsDeduct() {
		return lateHrsDeduct;
	}
	public void setLateHrsDeduct(String lateHrsDeduct) {
		this.lateHrsDeduct = lateHrsDeduct;
	}
	public String getAdjustLeave() {
		return adjustLeave;
	}
	public void setAdjustLeave(String adjustLeave) {
		this.adjustLeave = adjustLeave;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getMonth_old() {
		return month_old;
	}
	public void setMonth_old(String month_old) {
		this.month_old = month_old;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getApplyFor() {
		return applyFor;
	}
	public void setApplyFor(String applyFor) {
		this.applyFor = applyFor;
	}
	public String getLeaveCode() {
		return leaveCode;
	}
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getShiftTime() {
		return shiftTime;
	}
	public void setShiftTime(String shiftTime) {
		this.shiftTime = shiftTime;
	}
	
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getLateHrs() {
		return lateHrs;
	}
	public void setLateHrs(String lateHrs) {
		this.lateHrs = lateHrs;
	}
	public String getLateCheckBox() {
		return lateCheckBox;
	}
	public void setLateCheckBox(String lateCheckBox) {
		this.lateCheckBox = lateCheckBox;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public ArrayList getRegList() {
		return regList;
	}
	public void setRegList(ArrayList regList) {
		this.regList = regList;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getListFlag() {
		return listFlag;
	}
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	public String getPolicyCode() {
		return policyCode;
	}
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public String getLateFlag() {
		return lateFlag;
	}
	public void setLateFlag(String lateFlag) {
		this.lateFlag = lateFlag;
	}
	public String getSwipeFlag() {
		return swipeFlag;
	}
	public void setSwipeFlag(String swipeFlag) {
		this.swipeFlag = swipeFlag;
	}
	public String getRedressalFlag() {
		return redressalFlag;
	}
	public void setRedressalFlag(String redressalFlag) {
		this.redressalFlag = redressalFlag;
	}
	public ArrayList getSwipeList() {
		return swipeList;
	}
	public void setSwipeList(ArrayList swipeList) {
		this.swipeList = swipeList;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getTotalDiff() {
		return totalDiff;
	}
	public void setTotalDiff(String totalDiff) {
		this.totalDiff = totalDiff;
	}
	public String getApprSrNo() {
		return apprSrNo;
	}
	public void setApprSrNo(String apprSrNo) {
		this.apprSrNo = apprSrNo;
	}
	public String getInformCode() {
		return informCode;
	}
	public void setInformCode(String informCode) {
		this.informCode = informCode;
	}
	public String getInformToken() {
		return informToken;
	}
	public void setInformToken(String informToken) {
		this.informToken = informToken;
	}
	public String getInformName() {
		return informName;
	}
	public void setInformName(String informName) {
		this.informName = informName;
	}
	public String getApplyForName() {
		return applyForName;
	}
	public void setApplyForName(String applyForName) {
		this.applyForName = applyForName;
	}
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String getTotalLateHrs() {
		return totalLateHrs;
	}
	public void setTotalLateHrs(String totalLateHrs) {
		this.totalLateHrs = totalLateHrs;
	}
	public String getSLeaveType() {
		return sLeaveType;
	}
	public void setSLeaveType(String leaveType) {
		sLeaveType = leaveType;
	}
	public String getSLeaveCode() {
		return sLeaveCode;
	}
	public void setSLeaveCode(String leaveCode) {
		sLeaveCode = leaveCode;
	}
	public String getSBalance() {
		return sBalance;
	}
	public void setSBalance(String balance) {
		sBalance = balance;
	}
	public ArrayList getBList() {
		return bList;
	}
	public void setBList(ArrayList list) {
		bList = list;
	}
	public String getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public String getStatus2() {
		return status2;
	}
	public void setStatus2(String status2) {
		this.status2 = status2;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
	public String getCondTrueFlag() {
		return condTrueFlag;
	}
	public void setCondTrueFlag(String condTrueFlag) {
		this.condTrueFlag = condTrueFlag;
	}
	public String getCountValue() {
		return countValue;
	}
	public void setCountValue(String countValue) {
		this.countValue = countValue;
	}
	public String getLateHrsDeductFromCode() {
		return lateHrsDeductFromCode;
	}
	public void setLateHrsDeductFromCode(String lateHrsDeductFromCode) {
		this.lateHrsDeductFromCode = lateHrsDeductFromCode;
	}
	public String getRFromDate() {
		return rFromDate;
	}
	public void setRFromDate(String fromDate) {
		rFromDate = fromDate;
	}
	public String getRToDate() {
		return rToDate;
	}
	public void setRToDate(String toDate) {
		rToDate = toDate;
	}
	public String getRPenaltyDays() {
		return rPenaltyDays;
	}
	public void setRPenaltyDays(String penaltyDays) {
		rPenaltyDays = penaltyDays;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getSwipeAppCode() {
		return swipeAppCode;
	}
	public void redressalAppCode(String swipeAppCode) {
		this.swipeAppCode = swipeAppCode;
	}
	public String getLateAppCode() {
		return lateAppCode;
	}
	public void setLateAppCode(String lateAppCode) {
		this.lateAppCode = lateAppCode;
	}
	public String getRedressalAppCode() {
		return redressalAppCode;
	}
	public void setRedressalAppCode(String redressalAppCode) {
		this.redressalAppCode = redressalAppCode;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public void setSwipeAppCode(String swipeAppCode) {
		this.swipeAppCode = swipeAppCode;
	}
	public String getViewApplFlag() {
		return viewApplFlag;
	}
	public void setViewApplFlag(String viewApplFlag) {
		this.viewApplFlag = viewApplFlag;
	}
	public String getApplEmpCode() {
		return applEmpCode;
	}
	public void setApplEmpCode(String applEmpCode) {
		this.applEmpCode = applEmpCode;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getApplyForitt() {
		return applyForitt;
	}
	public void setApplyForitt(String applyForitt) {
		this.applyForitt = applyForitt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRejectActionName() {
		return rejectActionName;
	}
	public void setRejectActionName(String rejectActionName) {
		this.rejectActionName = rejectActionName;
	}
	public String getApproveActionName() {
		return approveActionName;
	}
	public void setApproveActionName(String approveActionName) {
		this.approveActionName = approveActionName;
	}
	public String getSendBackActionName() {
		return sendBackActionName;
	}
	public void setSendBackActionName(String sendBackActionName) {
		this.sendBackActionName = sendBackActionName;
	}
	public ArrayList getViewApproverList() {
		return viewApproverList;
	}
	public void setViewApproverList(ArrayList viewApproverList) {
		this.viewApproverList = viewApproverList;
	}
	public String getApproverNameView() {
		return approverNameView;
	}
	public void setApproverNameView(String approverNameView) {
		this.approverNameView = approverNameView;
	}
	public String getApprverComments() {
		return apprverComments;
	}
	public void setApprverComments(String apprverComments) {
		this.apprverComments = apprverComments;
	}
	public String getShowButtonFlag() {
		return showButtonFlag;
	}
	public void setShowButtonFlag(String showButtonFlag) {
		this.showButtonFlag = showButtonFlag;
	}
	public String getRedressalAdjDays() {
		return redressalAdjDays;
	}
	public void setRedressalAdjDays(String redressalAdjDays) {
		this.redressalAdjDays = redressalAdjDays;
	}
	public String getRedressalAdjStatus() {
		return redressalAdjStatus;
	}
	public void setRedressalAdjStatus(String redressalAdjStatus) {
		this.redressalAdjStatus = redressalAdjStatus;
	}
	public String getCommentsFlag() {
		return commentsFlag;
	}
	public void setCommentsFlag(String commentsFlag) {
		this.commentsFlag = commentsFlag;
	}
	public String getPersonalTimeFlag() {
		return personalTimeFlag;
	}
	public void setPersonalTimeFlag(String personalTimeFlag) {
		this.personalTimeFlag = personalTimeFlag;
	}
	public String getPersonalTime() {
		return personalTime;
	}
	public void setPersonalTime(String personalTime) {
		this.personalTime = personalTime;
	}
	public String getPtDate() {
		return ptDate;
	}
	public void setPtDate(String ptDate) {
		this.ptDate = ptDate;
	}
	public String getPtFromTime() {
		return ptFromTime;
	}
	public void setPtFromTime(String ptFromTime) {
		this.ptFromTime = ptFromTime;
	}
	public String getPtToTime() {
		return ptToTime;
	}
	public void setPtToTime(String ptToTime) {
		this.ptToTime = ptToTime;
	}
	public String getPtDateItt() {
		return ptDateItt;
	}
	public void setPtDateItt(String ptDateItt) {
		this.ptDateItt = ptDateItt;
	}
	public String getPtFromTimeItt() {
		return ptFromTimeItt;
	}
	public void setPtFromTimeItt(String ptFromTimeItt) {
		this.ptFromTimeItt = ptFromTimeItt;
	}
	public String getPtToTimeItt() {
		return ptToTimeItt;
	}
	public void setPtToTimeItt(String ptToTimeItt) {
		this.ptToTimeItt = ptToTimeItt;
	}
	public ArrayList getPtList() {
		return ptList;
	}
	public void setPtList(ArrayList ptList) {
		this.ptList = ptList;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getPersonalTimeHH_MI() {
		return personalTimeHH_MI;
	}
	public void setPersonalTimeHH_MI(String personalTimeHH_MI) {
		this.personalTimeHH_MI = personalTimeHH_MI;
	}
	public String getAvailablePT() {
		return availablePT;
	}
	public void setAvailablePT(String availablePT) {
		this.availablePT = availablePT;
	}
	public String getDifferencePT() {
		return differencePT;
	}
	public void setDifferencePT(String differencePT) {
		this.differencePT = differencePT;
	}
	public String getPersonalTimeCode() {
		return personalTimeCode;
	}
	public void setPersonalTimeCode(String personalTimeCode) {
		this.personalTimeCode = personalTimeCode;
	}
	public String getApproverFlag() {
		return approverFlag;
	}
	public void setApproverFlag(String approverFlag) {
		this.approverFlag = approverFlag;
	}
	public String getBackActionName() {
		return backActionName;
	}
	public void setBackActionName(String backActionName) {
		this.backActionName = backActionName;
	}
	public String getPtType() {
		return ptType;
	}
	public void setPtType(String ptType) {
		this.ptType = ptType;
	}
	public String getKeepHidden() {
		return keepHidden;
	}
	public void setKeepHidden(String keepHidden) {
		this.keepHidden = keepHidden;
	}
	public String getHiddenDate() {
		return hiddenDate;
	}
	public void setHiddenDate(String hiddenDate) {
		this.hiddenDate = hiddenDate;
	}
	public String getDataBaseFromTime() {
		return dataBaseFromTime;
	}
	public void setDataBaseFromTime(String dataBaseFromTime) {
		this.dataBaseFromTime = dataBaseFromTime;
	}
	public String getDataBaseToTime() {
		return dataBaseToTime;
	}
	public void setDataBaseToTime(String dataBaseToTime) {
		this.dataBaseToTime = dataBaseToTime;
	}
	public String getSwipeType() {
		return swipeType;
	}
	public void setSwipeType(String swipeType) {
		this.swipeType = swipeType;
	}
	public String getRegularizationApplCode() {
		return regularizationApplCode;
	}
	public void setRegularizationApplCode(String regularizationApplCode) {
		this.regularizationApplCode = regularizationApplCode;
	}
	public String getSecondApproverCode() {
		return secondApproverCode;
	}
	public void setSecondApproverCode(String secondApproverCode) {
		this.secondApproverCode = secondApproverCode;
	}
	public String getAddQuesPageNoField() {
		return addQuesPageNoField;
	}
	public void setAddQuesPageNoField(String addQuesPageNoField) {
		this.addQuesPageNoField = addQuesPageNoField;
	}
	public String getActAddQuesPage() {
		return actAddQuesPage;
	}
	public void setActAddQuesPage(String actAddQuesPage) {
		this.actAddQuesPage = actAddQuesPage;
	}
	public String getPageSwipe() {
		return pageSwipe;
	}
	public void setPageSwipe(String pageSwipe) {
		this.pageSwipe = pageSwipe;
	}
	public String getPageLate() {
		return pageLate;
	}
	public void setPageLate(String pageLate) {
		this.pageLate = pageLate;
	}
	public String getPageRedressal() {
		return pageRedressal;
	}
	public void setPageRedressal(String pageRedressal) {
		this.pageRedressal = pageRedressal;
	}
	public String getPagePT() {
		return pagePT;
	}
	public void setPagePT(String pagePT) {
		this.pagePT = pagePT;
	}
	public String getEarlyHrs() {
		return earlyHrs;
	}
	public void setEarlyHrs(String earlyHrs) {
		this.earlyHrs = earlyHrs;
	}
	public String getTotalHrs() {
		return totalHrs;
	}
	public void setTotalHrs(String totalHrs) {
		this.totalHrs = totalHrs;
	}
	public String getSendBackFlag() {
		return sendBackFlag;
	}
	public void setSendBackFlag(String sendBackFlag) {
		this.sendBackFlag = sendBackFlag;
	}
	public String getShiftStartTime() {
		return shiftStartTime;
	}
	public void setShiftStartTime(String shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}
	public String getShiftEndTime() {
		return shiftEndTime;
	}
	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}
	public String getViewApplication() {
		return viewApplication;
	}
	public void setViewApplication(String viewApplication) {
		this.viewApplication = viewApplication;
	}
	public boolean isHideApproverCommentsSectionFlag() {
		return hideApproverCommentsSectionFlag;
	}
	public void setHideApproverCommentsSectionFlag(
			boolean hideApproverCommentsSectionFlag) {
		this.hideApproverCommentsSectionFlag = hideApproverCommentsSectionFlag;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getReasonItt() {
		return reasonItt;
	}
	public void setReasonItt(String reasonItt) {
		this.reasonItt = reasonItt;
	}
	
	
}
