package org.paradyne.bean.TravelManagement.TravelProcess;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TmsTrvlApproval extends BeanBase {
	private String currencyEmployeeAdvance = "";
	private String searchempId="";
	private String searchempName="";
	private String travelId="";
	private String searchemptoken="";
	private String hidenstatus="";
	private String source ="";
	private String empBand ="";
	private String myPage ="";
	private String initName ="";
	private String appDate="";
	private String appStatus="";
	private String startDate="";
	private String endDate="";
	private String trvlReqName="";
	private String purpose="";
	private String purposeId="";
	private String project="";
	private String projectId="";
	private String otherProject="";
	private String customerId="";
	private String customerName="";
	private String otherCustomerName="";
	private String trvlTypeId="";
	private String trvlType="";
	private String applicantComments="";
	private String initToken="";
	private String initId="";
	private String applComm="";
	private String violationReason="";
	private String violationStatus="";
	private String policyViolationMsg="";
	private String initiatorGradeId="";
	private String violationFlag="";
	private String showRevokeStatus="false";
	
	
	private String listType=""; 
	/*** Iterator variables to display list of all applications starts*/
	private String travelApplicationIdItt="";
	private String initiatorNameItt="";
	private String travelRequestNameItt="";
	private String travleDateItt="";
	private String travleApplicationStatusItt="";
	private String travleApplicationStatusNameItt="";//Decoded name
	ArrayList travelApplicationIteratorlist;
	private String initiatorIdItt = "";
	private String travelIdItt = "";
	
	private boolean  appRejButtonFlag =false;
	private String approverCommentsFlag="false";
	private boolean keepInformedFlag =false;
	private boolean viewDtlFlag =false ;
	private String appRejFlag="false";
	private boolean approverListFlag = false ; 
	
	private String hiddenApplicationCode ="";
	
	private String journeyRadio="";
	private String accomodationRadio="";
	private String localConvRadio="";
	
	private String approverComments  ="";
	
	private String employeeTravellerIdFromList="";
	private String employeeTypeFromList="";
	private String employeeNameFromList="";
	private String employeeAgeFromList="";
	private String employeeBandFromList="";
	private String employeeDateOfBirthFromList="";
	private String employeeContactFromList="";
	private String employeeAdvanceFromList="";
	private String travellerGradeId="";
	
	
	ArrayList travellerList;
	ArrayList journeyList;
	ArrayList accomodationList;
	ArrayList localConveyanceList;
	private ArrayList keepInformedList = null;
	private ArrayList approverCommentList =null ;
	
	private String checkApproveRejectStatus ="";
	private String level = "";
	
	
	private String approximateCostOfTour="";
	private String approvedAdvanceAmount="";
	private String travelApplicationCodeItt="";
	
	
	public String getTravelApplicationCodeItt() {
		return travelApplicationCodeItt;
	}
	public void setTravelApplicationCodeItt(String travelApplicationCodeItt) {
		this.travelApplicationCodeItt = travelApplicationCodeItt;
	}
	public String getApproximateCostOfTour() {
		return approximateCostOfTour;
	}
	public void setApproximateCostOfTour(String approximateCostOfTour) {
		this.approximateCostOfTour = approximateCostOfTour;
	}
	public String getCheckApproveRejectStatus() {
		return checkApproveRejectStatus;
	}
	public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
		this.checkApproveRejectStatus = checkApproveRejectStatus;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	public ArrayList getJourneyList() {
		return journeyList;
	}
	public void setJourneyList(ArrayList journeyList) {
		this.journeyList = journeyList;
	}
	public ArrayList getAccomodationList() {
		return accomodationList;
	}
	public void setAccomodationList(ArrayList accomodationList) {
		this.accomodationList = accomodationList;
	}
	public ArrayList getLocalConveyanceList() {
		return localConveyanceList;
	}
	public void setLocalConveyanceList(ArrayList localConveyanceList) {
		this.localConveyanceList = localConveyanceList;
	}
	public String getEmployeeTravellerIdFromList() {
		return employeeTravellerIdFromList;
	}
	public void setEmployeeTravellerIdFromList(String employeeTravellerIdFromList) {
		this.employeeTravellerIdFromList = employeeTravellerIdFromList;
	}
	public String getEmployeeTypeFromList() {
		return employeeTypeFromList;
	}
	public void setEmployeeTypeFromList(String employeeTypeFromList) {
		this.employeeTypeFromList = employeeTypeFromList;
	}
	public String getEmployeeNameFromList() {
		return employeeNameFromList;
	}
	public void setEmployeeNameFromList(String employeeNameFromList) {
		this.employeeNameFromList = employeeNameFromList;
	}
	public String getEmployeeAgeFromList() {
		return employeeAgeFromList;
	}
	public void setEmployeeAgeFromList(String employeeAgeFromList) {
		this.employeeAgeFromList = employeeAgeFromList;
	}
	public String getEmployeeDateOfBirthFromList() {
		return employeeDateOfBirthFromList;
	}
	public void setEmployeeDateOfBirthFromList(String employeeDateOfBirthFromList) {
		this.employeeDateOfBirthFromList = employeeDateOfBirthFromList;
	}
	public String getEmployeeContactFromList() {
		return employeeContactFromList;
	}
	public void setEmployeeContactFromList(String employeeContactFromList) {
		this.employeeContactFromList = employeeContactFromList;
	}
	public String getEmployeeAdvanceFromList() {
		return employeeAdvanceFromList;
	}
	public void setEmployeeAdvanceFromList(String employeeAdvanceFromList) {
		this.employeeAdvanceFromList = employeeAdvanceFromList;
	}
	public ArrayList getTravellerList() {
		return travellerList;
	}
	public void setTravellerList(ArrayList travellerList) {
		this.travellerList = travellerList;
	}
	public String getJourneyRadio() {
		return journeyRadio;
	}
	public void setJourneyRadio(String journeyRadio) {
		this.journeyRadio = journeyRadio;
	}
	public String getAccomodationRadio() {
		return accomodationRadio;
	}
	public void setAccomodationRadio(String accomodationRadio) {
		this.accomodationRadio = accomodationRadio;
	}
	public String getLocalConvRadio() {
		return localConvRadio;
	}
	public void setLocalConvRadio(String localConvRadio) {
		this.localConvRadio = localConvRadio;
	}
	public String getHiddenApplicationCode() {
		return hiddenApplicationCode;
	}
	public void setHiddenApplicationCode(String hiddenApplicationCode) {
		this.hiddenApplicationCode = hiddenApplicationCode;
	}
	public String getTravelIdItt() {
		return travelIdItt;
	}
	public void setTravelIdItt(String travelIdItt) {
		this.travelIdItt = travelIdItt;
	}
	public String getTravelApplicationIdItt() {
		return travelApplicationIdItt;
	}
	public void setTravelApplicationIdItt(String travelApplicationIdItt) {
		this.travelApplicationIdItt = travelApplicationIdItt;
	}
	public String getInitiatorNameItt() {
		return initiatorNameItt;
	}
	public void setInitiatorNameItt(String initiatorNameItt) {
		this.initiatorNameItt = initiatorNameItt;
	}
	public String getTravelRequestNameItt() {
		return travelRequestNameItt;
	}
	public void setTravelRequestNameItt(String travelRequestNameItt) {
		this.travelRequestNameItt = travelRequestNameItt;
	}
	public String getTravleDateItt() {
		return travleDateItt;
	}
	public void setTravleDateItt(String travleDateItt) {
		this.travleDateItt = travleDateItt;
	}
	public String getTravleApplicationStatusItt() {
		return travleApplicationStatusItt;
	}
	public void setTravleApplicationStatusItt(String travleApplicationStatusItt) {
		this.travleApplicationStatusItt = travleApplicationStatusItt;
	}
	public String getTravleApplicationStatusNameItt() {
		return travleApplicationStatusNameItt;
	}
	public void setTravleApplicationStatusNameItt(
			String travleApplicationStatusNameItt) {
		this.travleApplicationStatusNameItt = travleApplicationStatusNameItt;
	}
	public ArrayList getTravelApplicationIteratorlist() {
		return travelApplicationIteratorlist;
	}
	public void setTravelApplicationIteratorlist(
			ArrayList travelApplicationIteratorlist) {
		this.travelApplicationIteratorlist = travelApplicationIteratorlist;
	}
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
	public String getInitiatorIdItt() {
		return initiatorIdItt;
	}
	public void setInitiatorIdItt(String initiatorIdItt) {
		this.initiatorIdItt = initiatorIdItt;
	}
	public boolean isAppRejButtonFlag() {
		return appRejButtonFlag;
	}
	public void setAppRejButtonFlag(boolean appRejButtonFlag) {
		this.appRejButtonFlag = appRejButtonFlag;
	}
	public String getApproverCommentsFlag() {
		return approverCommentsFlag;
	}
	public void setApproverCommentsFlag(String approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}
	public boolean isViewDtlFlag() {
		return viewDtlFlag;
	}
	public void setViewDtlFlag(boolean viewDtlFlag) {
		this.viewDtlFlag = viewDtlFlag;
	}
	public String getAppRejFlag() {
		return appRejFlag;
	}
	public void setAppRejFlag(String appRejFlag) {
		this.appRejFlag = appRejFlag;
	}
	public boolean isApproverListFlag() {
		return approverListFlag;
	}
	public void setApproverListFlag(boolean approverListFlag) {
		this.approverListFlag = approverListFlag;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	public String getInitName() {
		return initName;
	}
	public void setInitName(String initName) {
		this.initName = initName;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
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
	public String getTrvlReqName() {
		return trvlReqName;
	}
	public void setTrvlReqName(String trvlReqName) {
		this.trvlReqName = trvlReqName;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPurposeId() {
		return purposeId;
	}
	public void setPurposeId(String purposeId) {
		this.purposeId = purposeId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getOtherProject() {
		return otherProject;
	}
	public void setOtherProject(String otherProject) {
		this.otherProject = otherProject;
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
	public String getOtherCustomerName() {
		return otherCustomerName;
	}
	public void setOtherCustomerName(String otherCustomerName) {
		this.otherCustomerName = otherCustomerName;
	}
	public String getTrvlTypeId() {
		return trvlTypeId;
	}
	public void setTrvlTypeId(String trvlTypeId) {
		this.trvlTypeId = trvlTypeId;
	}
	public String getTrvlType() {
		return trvlType;
	}
	public void setTrvlType(String trvlType) {
		this.trvlType = trvlType;
	}
	public String getApplicantComments() {
		return applicantComments;
	}
	public void setApplicantComments(String applicantComments) {
		this.applicantComments = applicantComments;
	}
	public String getInitToken() {
		return initToken;
	}
	public void setInitToken(String initToken) {
		this.initToken = initToken;
	}
	public String getInitId() {
		return initId;
	}
	public void setInitId(String initId) {
		this.initId = initId;
	}
	public String getApplComm() {
		return applComm;
	}
	public void setApplComm(String applComm) {
		this.applComm = applComm;
	}
	public String getViolationReason() {
		return violationReason;
	}
	public void setViolationReason(String violationReason) {
		this.violationReason = violationReason;
	}
	public String getViolationStatus() {
		return violationStatus;
	}
	public void setViolationStatus(String violationStatus) {
		this.violationStatus = violationStatus;
	}
	public String getPolicyViolationMsg() {
		return policyViolationMsg;
	}
	public void setPolicyViolationMsg(String policyViolationMsg) {
		this.policyViolationMsg = policyViolationMsg;
	}
	public String getInitiatorGradeId() {
		return initiatorGradeId;
	}
	public void setInitiatorGradeId(String initiatorGradeId) {
		this.initiatorGradeId = initiatorGradeId;
	}
	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}
	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	public String getViolationFlag() {
		return violationFlag;
	}
	public void setViolationFlag(String violationFlag) {
		this.violationFlag = violationFlag;
	}
	public boolean isKeepInformedFlag() {
		return keepInformedFlag;
	}
	public void setKeepInformedFlag(boolean keepInformedFlag) {
		this.keepInformedFlag = keepInformedFlag;
	}
	public String getApprovedAdvanceAmount() {
		return approvedAdvanceAmount;
	}
	public void setApprovedAdvanceAmount(String approvedAdvanceAmount) {
		this.approvedAdvanceAmount = approvedAdvanceAmount;
	}
	public String getShowRevokeStatus() {
		return showRevokeStatus;
	}
	public void setShowRevokeStatus(String showRevokeStatus) {
		this.showRevokeStatus = showRevokeStatus;
	}
	/**
	 * @return
	 */
	public String getEmpBand() {
		return empBand;
	}
	/**
	 * @param empBand
	 */
	public void setEmpBand(String empBand) {
		this.empBand = empBand;
	}
	/**
	 * @return
	 */
	public String getEmployeeBandFromList() {
		return employeeBandFromList;
	}
	/**
	 * @param employeeBandFromList
	 */
	public void setEmployeeBandFromList(String employeeBandFromList) {
		this.employeeBandFromList = employeeBandFromList;
	}
	/**
	 * @return
	 */
	public String getTravellerGradeId() {
		return travellerGradeId;
	}
	/**
	 * @param travellerGradeId
	 */
	public void setTravellerGradeId(String travellerGradeId) {
		this.travellerGradeId = travellerGradeId;
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
	public String getCurrencyEmployeeAdvance() {
		return currencyEmployeeAdvance;
	}
	public void setCurrencyEmployeeAdvance(String currencyEmployeeAdvance) {
		this.currencyEmployeeAdvance = currencyEmployeeAdvance;
	}
	
	
	/*** Iterator variables to display list of all applications ends*/
	

}
