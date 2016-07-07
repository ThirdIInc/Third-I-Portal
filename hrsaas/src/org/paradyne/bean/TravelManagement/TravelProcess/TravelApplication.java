package org.paradyne.bean.TravelManagement.TravelProcess;

import java.util.ArrayList;

public class TravelApplication extends TravelMonitoring {

	private String source ="";
	private String joiningDate="";
	private String travelPolicyCurrency = "";
	private String defaultCurrency = "";
	private String currencyEmployeeAdvance = "";
	
	private String counterVal ="";
	private String isSelfFlag = "false" ; 
	private String isEmployeeFlag = "false" ; 
	private String isGuestFlag = "false" ; 
	private String draftOrSend = "" ; 
	private String addedEmployee = "" ; 
	private boolean showRevokeStatus=false;
	private ArrayList approverCommentList =null ; 
	private String empBand ="";
	private String employeeBandFromList="";
	
	private String policyCode ="";
	private boolean approverListFlag = false ; 

	private String appSrNo  = "";
	private String prevApproverID  = "";
	private String prevApproverName  = "";
	private String prevApproverDate  = "";
	private String prevApproverStatus  = "";
	private String prevApproverComment  = "";
	
	private boolean appRejButtonFlag =false;
	private String approverCommentsFlag="false";
	private boolean backFlag =false;
	private boolean viewDtlFlag =false ;
	private String travelNameItt = "";
	private String level = "";
	 
	private String checkReportingStructure ="";
	private String keepInformToCode ="";
	private String keepInformToName ="";
	private String checkApproveRejectStatus ="";
	
	private String informCode="";
	private String informToken="";
	private String informName="";
	private String keepInformCode="";
	private ArrayList informList=null;
	private String keepHidden="";
	private String keepInform="";
 
	private ArrayList keepInformedList = null ;
	
	private String empId;
	private String appEmpId;
	private String empName;
	private String trvlReqName;// TRAVEL REQUEST NAME
	private String trvlDate;// TRAVEL DATE
	private String appStatus;// APPLICATION STATUS
	private String appId = "";
	private String appCode = "";
	private String advAppCode;
	private String idenAppCode;
	private String trvlId;

	// EMPLOYEE INFORMATION
	private String appFor;
	private String appDate;
	private String hAppFor;
	private String initToken;
	private String initId="";
	private String initGradeId;
	private String initName;
	private String empToken;
	private String branchId;
	private String branch;
	private String deptId;
	private String dept;
	private String desgId;
	private String desg;
	private String grad;
	private String gradId;
	private String age;
	private String contact;
	private String delApp;

	// GUEST INFORMATION
	private String guestName;
	private String guestAge;
	private String guestContact;
	private String delGuest;

	// TOUR DETAILS
	private String tourId;
	private String startDate;
	private String endDate;
	private String purposeId;
	private String purpose;
	private String trvlTypeId;
	private String trvlType;

	private String trRadio;
	private String accomRadio;
	private String locRadio;

	// flags-radio buttons
	private String accom;
	private String localConv;

	// a.Journey details
	private String jourId;
	private String frmPlace;
	private String toPlace;
	private String jourModeId;
	private String jourMode;
	private String jourDate;
	private String jourTime;
	private String jourStatus;

	// b.Accommodation
	private String lodgId;
	private String lodgTypeId;
	private String lodgType;
	private String roomTypeId;
	private String roomType;
	private String cityId;
	private String city;
	private String prfrdLoc;
	private String frmDate;
	private String frmTime;
	private String toDate;
	private String toTime;
	private String delAccom;
	private String lodgStatus;

	// c.Local Conveyance
	private String locId;
	private String locCity;
	private String locFrmDate;
	private String locToDate;
	private String locFromTime;
	private String locToTime;
	private String trvlDtls;
	private String medium;
	private String delLoc;
	private String locStatus;

	// ADVANCE AND IDENTITY DETAILS
	private String advAmount;

	// //////Policy
	private String policy;
	private String abbr;
	private String effctDate;
	private String policyDesc;
	private String policyStatus;
	private String expCategory;
	private String lodgExpCategory;
	private String unit;
	private String maxEntAmount;
	private String noLimit;
	private String mapTravel;
	private String mapLodg;
	private String sttlmntDays;
	private String policyGud;
	private String listType;
	private String totalAmount;

	private String classId;
	private String journeyId;
	private String journeyName;
	private String className;

	private String roomId;
	private String hotelId;
	private String hotel;
	private String room;
	private String proofReq;

	private String status;

	private String policyViolation;
	private String approverId;

	private String apprComment;
	private String applComm;
	private String withComm;
	private String cancelComm;
	private String cancelDate;
			
	private String policyAdvanceAllowed ="";
	private String maxDaysTravelClaim = "";
	private String payModeForAdvanceClaimCheque ="";
	private String payModeForAdvanceClaimCash = "";
	private String payModeForAdvanceClaimTransfer ="";
	private String payModeForAdvanceClaimInSalary = "";
	private String amountWithBill="";
	private String amountWithOutBill="";
	private String cityGrade="";
	private String minNumberOfAdvanceDaysToApplyForTravel="";
	private String travelTypeSelf="";
	private String travelTypeTeam="";
	private String travelTypeGuest="";

	ArrayList expList;
	ArrayList trvlModeList;
	ArrayList hotelTypeList;

	// /OPEN/SUBMIT/RETURNED/APPROVED/REJECTED list

	ArrayList empList;
	ArrayList guestList;

	ArrayList jourList;
	ArrayList accomList;
	ArrayList locList;

	ArrayList advList;
	ArrayList identityList;
	ArrayList commentList;
	// Added by Prashant Starts
	
	private String initiatorDateOfBirth;
	private String employeeDateOfBirth;
	private String violationReason;
	private String violationStatus;
	private String violationFlag;
	private String travellerGradeId;
	private String initiatorGradeId;
	private String saveFlag;
	private String myPage;
	private String projectId;
	private String project;
	private String otherProject;
	private String customerId;
	private String customerName;
	private String otherCustomerName;
	private String travellerToken;
	private String travelTypeId;
	private String travelTypeName;
	private String applicantComments;
	private String journeyRadio;
	private String accomodationRadio;
	private String localConvRadio;
	private boolean policyViolated=false;
	private boolean datePolicyViolated=false;
	private String datePolicyViolationMsg="";
	private String policyViolationMsg="";
	private String approverComments  ="";
	private String approverViolationComments  ="";
	
	
	// journey variables
	
	private String journeyFromPlace;
	private String journeyToPlace;
	private String journeyModeId;
	private String journeyMode;
	private String journeyDate;
	private String journeyTime;
	
	//lodging details
	
	private String accomodationId;
	private String accomodationHotelTypeId;
	private String accomodationHotelType;
	private String accomodationRoomTypeId;
	private String accomodationRoomType;
	private String accomodationCityId;
	private String accomodationCity;
	private String accomodationPrefLocation;
	private String accomodationFromDate;
	private String accomodationFromTime;
	private String accomodationToDate;
	private String accomodationToTime;
	private String accomodationFlag;
	
	//local conveyance details
	
	
	private String localConveyanceCode;
	private String localConveyanceCity;
	private String localConveyanceTravelDetail;
	private String localConveyanceToDate;
	private String localConveyanceFromDate;
	private String localConveyanceFromTime;
	private String localConveyanceToTime;
	private String localConveyanceTravelMedium;
	private String localConveyanceFlag;
	


	/*** Iterator variables to display list of all applications starts*/
	private String travelApplicationIdItt;
	private String travelApplicationCodeItt;
	private String travelApplicationForFlagItt;
	private String employeeOrGuestNameItt;
	private String travelRequestNameItt;
	private String initiatorNameItt;
	private String initiatorIdItt;
	private String applicationEmpCodeItt;
	private String travleApplicationForItt;
	private String travleDateItt;
	private String applicationTravelIdItt;
	private String travleApplicationStatusItt;
	private String travleApplicationStatusNameItt;//Decoded name
	ArrayList travelApplicationIteratorlist;
	
	/*** Iterator variables to display list of all applications ends*/
	
	/*** Iterator variables to display list of all travellers begins*/
	
	private String employeeTravellerIdFromList;
	private String employeeTypeFromList;
	private String employeeNameFromList;
	private String employeeAgeFromList;
	private String employeeDateOfBirthFromList;
	private String employeeContactFromList;
	private String employeeAdvanceFromList;
	
	
	ArrayList travellerList;
	
	/*** Iterator variables to display list of all travellers ends*/
	
	/*** Iterator variables to display list of journey begins*/
	
	private String journeyFromPlaceItt;	
	private String journeyToPlaceItt;
	private String journeyModeIdItt;	
	private String journeyModeItt;	
	private String journeyDateItt;
	private String journeyTimeItt;	
	private String jourCodeItt;
	
	ArrayList journeyList;
	
	
	/*** Iterator variables to display list of journey ends*/
	
	
	ArrayList accomodationList;
	
	/*** Iterator variables to display list of accomodation ends*/
	
	/*** Iterator variables to display list of local conveyance begins*/
	
	ArrayList localConveyanceList;

	/*** Iterator variables to display list of local conveyance ends*/
	
	
	
	private String alternateApprover  ="";
	private String empTokenNo  ="";
	private String employeeName =""; 
	private String employeeAge ="";
	private String employeeContact ="";
	
	private String hiddenApplicationCode ="";
	
	
	private String appRejFlag="false";
	
	// Added by Prashant Ends
	 private String approvedAdvanceAmount ="";
	 private String approximateCostOfTour ="";

	public String getApproximateCostOfTour() {
		return approximateCostOfTour;
	}

	public void setApproximateCostOfTour(String approximateCostOfTour) {
		this.approximateCostOfTour = approximateCostOfTour;
	}

	public String getApprovedAdvanceAmount() {
		return approvedAdvanceAmount;
	}

	public void setApprovedAdvanceAmount(String approvedAdvanceAmount) {
		this.approvedAdvanceAmount = approvedAdvanceAmount;
	}

	public String getAppRejFlag() {
		return appRejFlag;
	}

	public void setAppRejFlag(String appRejFlag) {
		this.appRejFlag = appRejFlag;
	}

	public String getHiddenApplicationCode() {
		return hiddenApplicationCode;
	}

	public void setHiddenApplicationCode(String hiddenApplicationCode) {
		this.hiddenApplicationCode = hiddenApplicationCode;
	}

	public String getEmpTokenNo() {
		return empTokenNo;
	}

	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(String employeeAge) {
		this.employeeAge = employeeAge;
	}

	

	/*** Iterator variables to display list of all Under Process applications ends*/
	
	// Added by Prashant ends


	public String getApprComment() {
		return apprComment;
	}

	public void setApprComment(String apprComment) {
		this.apprComment = apprComment;
	}

	public ArrayList getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList commentList) {
		this.commentList = commentList;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getEffctDate() {
		return effctDate;
	}

	public void setEffctDate(String effctDate) {
		this.effctDate = effctDate;
	}

	public String getPolicyDesc() {
		return policyDesc;
	}

	public void setPolicyDesc(String policyDesc) {
		this.policyDesc = policyDesc;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public String getExpCategory() {
		return expCategory;
	}

	public void setExpCategory(String expCategory) {
		this.expCategory = expCategory;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMaxEntAmount() {
		return maxEntAmount;
	}

	public void setMaxEntAmount(String maxEntAmount) {
		this.maxEntAmount = maxEntAmount;
	}

	public String getNoLimit() {
		return noLimit;
	}

	public void setNoLimit(String noLimit) {
		this.noLimit = noLimit;
	}

	public String getMapTravel() {
		return mapTravel;
	}

	public void setMapTravel(String mapTravel) {
		this.mapTravel = mapTravel;
	}

	public String getMapLodg() {
		return mapLodg;
	}

	public void setMapLodg(String mapLodg) {
		this.mapLodg = mapLodg;
	}

	public String getSttlmntDays() {
		return sttlmntDays;
	}

	public void setSttlmntDays(String sttlmntDays) {
		this.sttlmntDays = sttlmntDays;
	}

	public String getPolicyGud() {
		return policyGud;
	}

	public void setPolicyGud(String policyGud) {
		this.policyGud = policyGud;
	}

	public ArrayList getTrvlModeList() {
		return trvlModeList;
	}

	public void setTrvlModeList(ArrayList trvlModeList) {
		this.trvlModeList = trvlModeList;
	}

	public ArrayList getHotelTypeList() {
		return hotelTypeList;
	}

	public void setHotelTypeList(ArrayList hotelTypeList) {
		this.hotelTypeList = hotelTypeList;
	}

	public ArrayList getAdvList() {
		return advList;
	}

	public void setAdvList(ArrayList advList) {
		this.advList = advList;
	}

	public ArrayList getIdentityList() {
		return identityList;
	}

	public void setIdentityList(ArrayList identityList) {
		this.identityList = identityList;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getLocCity() {
		return locCity;
	}

	public void setLocCity(String locCity) {
		this.locCity = locCity;
	}

	public String getLocFrmDate() {
		return locFrmDate;
	}

	public void setLocFrmDate(String locFrmDate) {
		this.locFrmDate = locFrmDate;
	}

	public String getLocToDate() {
		return locToDate;
	}

	public void setLocToDate(String locToDate) {
		this.locToDate = locToDate;
	}

	public String getLocFromTime() {
		return locFromTime;
	}

	public void setLocFromTime(String locFromTime) {
		this.locFromTime = locFromTime;
	}

	public String getLocToTime() {
		return locToTime;
	}

	public void setLocToTime(String locToTime) {
		this.locToTime = locToTime;
	}

	public ArrayList getJourList() {
		return jourList;
	}

	public void setJourList(ArrayList jourList) {
		this.jourList = jourList;
	}

	public ArrayList getAccomList() {
		return accomList;
	}

	public void setAccomList(ArrayList accomList) {
		this.accomList = accomList;
	}

	public ArrayList getLocList() {
		return locList;
	}

	public void setLocList(ArrayList locList) {
		this.locList = locList;
	}

	public String getHAppFor() {
		return hAppFor;
	}

	public void setHAppFor(String appFor) {
		hAppFor = appFor;
	}

	public ArrayList getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}

	public ArrayList getGuestList() {
		return guestList;
	}

	public void setGuestList(ArrayList guestList) {
		this.guestList = guestList;
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

	public String getTrvlDate() {
		return trvlDate;
	}

	public void setTrvlDate(String trvlDate) {
		this.trvlDate = trvlDate;
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

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
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

	public String getInitName() {
		return initName;
	}

	public void setInitName(String initName) {
		this.initName = initName;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
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

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getGradId() {
		return gradId;
	}

	public void setGradId(String gradId) {
		this.gradId = gradId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestAge() {
		return guestAge;
	}

	public void setGuestAge(String guestAge) {
		this.guestAge = guestAge;
	}

	public String getGuestContact() {
		return guestContact;
	}

	public void setGuestContact(String guestContact) {
		this.guestContact = guestContact;
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

	public String getPurposeId() {
		return purposeId;
	}

	public void setPurposeId(String purposeId) {
		this.purposeId = purposeId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
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

	public String getAccom() {
		return accom;
	}

	public void setAccom(String accom) {
		this.accom = accom;
	}

	public String getLocalConv() {
		return localConv;
	}

	public void setLocalConv(String localConv) {
		this.localConv = localConv;
	}

	public String getFrmPlace() {
		return frmPlace;
	}

	public void setFrmPlace(String frmPlace) {
		this.frmPlace = frmPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public String getJourModeId() {
		return jourModeId;
	}

	public void setJourModeId(String jourModeId) {
		this.jourModeId = jourModeId;
	}

	public String getJourMode() {
		return jourMode;
	}

	public void setJourMode(String jourMode) {
		this.jourMode = jourMode;
	}

	public String getJourDate() {
		return jourDate;
	}

	public void setJourDate(String jourDate) {
		this.jourDate = jourDate;
	}

	public String getJourTime() {
		return jourTime;
	}

	public void setJourTime(String jourTime) {
		this.jourTime = jourTime;
	}

	public String getLodgTypeId() {
		return lodgTypeId;
	}

	public void setLodgTypeId(String lodgTypeId) {
		this.lodgTypeId = lodgTypeId;
	}

	public String getLodgType() {
		return lodgType;
	}

	public void setLodgType(String lodgType) {
		this.lodgType = lodgType;
	}

	public String getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPrfrdLoc() {
		return prfrdLoc;
	}

	public void setPrfrdLoc(String prfrdLoc) {
		this.prfrdLoc = prfrdLoc;
	}

	public String getFrmDate() {
		return frmDate;
	}

	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	public String getFrmTime() {
		return frmTime;
	}

	public void setFrmTime(String frmTime) {
		this.frmTime = frmTime;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getDelAccom() {
		return delAccom;
	}

	public void setDelAccom(String delAccom) {
		this.delAccom = delAccom;
	}

	public String getTrvlDtls() {
		return trvlDtls;
	}

	public void setTrvlDtls(String trvlDtls) {
		this.trvlDtls = trvlDtls;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getDelLoc() {
		return delLoc;
	}

	public void setDelLoc(String delLoc) {
		this.delLoc = delLoc;
	}

	public String getAdvAmount() {
		return advAmount;
	}

	public void setAdvAmount(String advAmount) {
		this.advAmount = advAmount;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getJourId() {
		return jourId;
	}

	public void setJourId(String jourId) {
		this.jourId = jourId;
	}

	public String getLodgId() {
		return lodgId;
	}

	public void setLodgId(String lodgId) {
		this.lodgId = lodgId;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getDelApp() {
		return delApp;
	}

	public void setDelApp(String delApp) {
		this.delApp = delApp;
	}

	public String getDelGuest() {
		return delGuest;
	}

	public void setDelGuest(String delGuest) {
		this.delGuest = delGuest;
	}

	public String getTrRadio() {
		return trRadio;
	}

	public void setTrRadio(String trRadio) {
		this.trRadio = trRadio;
	}

	public String getAccomRadio() {
		return accomRadio;
	}

	public void setAccomRadio(String accomRadio) {
		this.accomRadio = accomRadio;
	}

	public String getLocRadio() {
		return locRadio;
	}

	public void setLocRadio(String locRadio) {
		this.locRadio = locRadio;
	}

	public String getTourId() {
		return tourId;
	}

	public void setTourId(String tourId) {
		this.tourId = tourId;
	}

	public String getAdvAppCode() {
		return advAppCode;
	}

	public void setAdvAppCode(String advAppCode) {
		this.advAppCode = advAppCode;
	}

	public String getIdenAppCode() {
		return idenAppCode;
	}

	public void setIdenAppCode(String idenAppCode) {
		this.idenAppCode = idenAppCode;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getAppEmpId() {
		return appEmpId;
	}

	public void setAppEmpId(String appEmpId) {
		this.appEmpId = appEmpId;
	}

	public ArrayList getExpList() {
		return expList;
	}

	public void setExpList(ArrayList expList) {
		this.expList = expList;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(String journeyId) {
		this.journeyId = journeyId;
	}

	public String getJourneyName() {
		return journeyName;
	}

	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getLodgExpCategory() {
		return lodgExpCategory;
	}

	public void setLodgExpCategory(String lodgExpCategory) {
		this.lodgExpCategory = lodgExpCategory;
	}

	public String getPolicyViolation() {
		return policyViolation;
	}

	public void setPolicyViolation(String policyViolation) {
		this.policyViolation = policyViolation;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInitGradeId() {
		return initGradeId;
	}

	public void setInitGradeId(String initGradeId) {
		this.initGradeId = initGradeId;
	}


	public String getTrvlId() {
		return trvlId;
	}

	public void setTrvlId(String trvlId) {
		this.trvlId = trvlId;
	}

	public String getApplComm() {
		return applComm;
	}

	public void setApplComm(String applComm) {
		this.applComm = applComm;
	}

	public String getWithComm() {
		return withComm;
	}

	public void setWithComm(String withComm) {
		this.withComm = withComm;
	}

	public String getJourStatus() {
		return jourStatus;
	}

	public void setJourStatus(String jourStatus) {
		this.jourStatus = jourStatus;
	}

	public String getLodgStatus() {
		return lodgStatus;
	}

	public void setLodgStatus(String lodgStatus) {
		this.lodgStatus = lodgStatus;
	}

	public String getLocStatus() {
		return locStatus;
	}

	public void setLocStatus(String locStatus) {
		this.locStatus = locStatus;
	}

	public String getCancelComm() {
		return cancelComm;
	}

	public void setCancelComm(String cancelComm) {
		this.cancelComm = cancelComm;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	/**
	 * @return the proofReq
	 */
	public String getProofReq() {
		return proofReq;
	}

	/**
	 * @param proofReq
	 *            the proofReq to set
	 */
	public void setProofReq(String proofReq) {
		this.proofReq = proofReq;
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

	public String getKeepInformCode() {
		return keepInformCode;
	}

	public void setKeepInformCode(String keepInformCode) {
		this.keepInformCode = keepInformCode;
	}

	public ArrayList getInformList() {
		return informList;
	}

	public void setInformList(ArrayList informList) {
		this.informList = informList;
	}

	public String getKeepHidden() {
		return keepHidden;
	}

	public void setKeepHidden(String keepHidden) {
		this.keepHidden = keepHidden;
	}

	public String getKeepInform() {
		return keepInform;
	}

	public void setKeepInform(String keepInform) {
		this.keepInform = keepInform;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getEmployeeOrGuestNameItt() {
		return employeeOrGuestNameItt;
	}

	public void setEmployeeOrGuestNameItt(String employeeOrGuestNameItt) {
		this.employeeOrGuestNameItt = employeeOrGuestNameItt;
	}

	public String getTravelRequestNameItt() {
		return travelRequestNameItt;
	}

	public void setTravelRequestNameItt(String travelRequestNameItt) {
		this.travelRequestNameItt = travelRequestNameItt;
	}

	public String getInitiatorNameItt() {
		return initiatorNameItt;
	}

	public void setInitiatorNameItt(String initiatorNameItt) {
		this.initiatorNameItt = initiatorNameItt;
	}

	public String getTravleApplicationForItt() {
		return travleApplicationForItt;
	}

	public void setTravleApplicationForItt(String travleApplicationForItt) {
		this.travleApplicationForItt = travleApplicationForItt;
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

	public String getTravelApplicationIdItt() {
		return travelApplicationIdItt;
	}

	public void setTravelApplicationIdItt(String travelApplicationIdItt) {
		this.travelApplicationIdItt = travelApplicationIdItt;
	}

	public ArrayList getTravelApplicationIteratorlist() {
		return travelApplicationIteratorlist;
	}

	public void setTravelApplicationIteratorlist(
			ArrayList travelApplicationIteratorlist) {
		this.travelApplicationIteratorlist = travelApplicationIteratorlist;
	}

	public String getTravelApplicationCodeItt() {
		return travelApplicationCodeItt;
	}

	public void setTravelApplicationCodeItt(String travelApplicationCodeItt) {
		this.travelApplicationCodeItt = travelApplicationCodeItt;
	}

	public String getTravelApplicationForFlagItt() {
		return travelApplicationForFlagItt;
	}

	public void setTravelApplicationForFlagItt(String travelApplicationForFlagItt) {
		this.travelApplicationForFlagItt = travelApplicationForFlagItt;
	}

	public String getApplicationEmpCodeItt() {
		return applicationEmpCodeItt;
	}

	public void setApplicationEmpCodeItt(String applicationEmpCodeItt) {
		this.applicationEmpCodeItt = applicationEmpCodeItt;
	}

	public String getApplicationTravelIdItt() {
		return applicationTravelIdItt;
	}

	public void setApplicationTravelIdItt(String applicationTravelIdItt) {
		this.applicationTravelIdItt = applicationTravelIdItt;
	}



	public String getTravleApplicationStatusNameItt() {
		return travleApplicationStatusNameItt;
	}

	public void setTravleApplicationStatusNameItt(
			String travleApplicationStatusNameItt) {
		this.travleApplicationStatusNameItt = travleApplicationStatusNameItt;
	}

	public String getEmployeeContact() {
		return employeeContact;
	}

	public void setEmployeeContact(String employeeContact) {
		this.employeeContact = employeeContact;
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

	public String getAlternateApprover() {
		return alternateApprover;
	}

	public void setAlternateApprover(String alternateApprover) {
		this.alternateApprover = alternateApprover;
	}

	public String getJourneyFromPlace() {
		return journeyFromPlace;
	}

	public void setJourneyFromPlace(String journeyFromPlace) {
		this.journeyFromPlace = journeyFromPlace;
	}

	public String getJourneyToPlace() {
		return journeyToPlace;
	}

	public void setJourneyToPlace(String journeyToPlace) {
		this.journeyToPlace = journeyToPlace;
	}

	public String getJourneyModeId() {
		return journeyModeId;
	}

	public void setJourneyModeId(String journeyModeId) {
		this.journeyModeId = journeyModeId;
	}

	public String getJourneyMode() {
		return journeyMode;
	}

	public void setJourneyMode(String journeyMode) {
		this.journeyMode = journeyMode;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(String journeyTime) {
		this.journeyTime = journeyTime;
	}

	public String getAccomodationHotelTypeId() {
		return accomodationHotelTypeId;
	}

	public void setAccomodationHotelTypeId(String accomodationHotelTypeId) {
		this.accomodationHotelTypeId = accomodationHotelTypeId;
	}

	public String getAccomodationHotelType() {
		return accomodationHotelType;
	}

	public void setAccomodationHotelType(String accomodationHotelType) {
		this.accomodationHotelType = accomodationHotelType;
	}

	public String getAccomodationRoomTypeId() {
		return accomodationRoomTypeId;
	}

	public void setAccomodationRoomTypeId(String accomodationRoomTypeId) {
		this.accomodationRoomTypeId = accomodationRoomTypeId;
	}

	public String getAccomodationRoomType() {
		return accomodationRoomType;
	}

	public void setAccomodationRoomType(String accomodationRoomType) {
		this.accomodationRoomType = accomodationRoomType;
	}

	public String getAccomodationCityId() {
		return accomodationCityId;
	}

	public void setAccomodationCityId(String accomodationCityId) {
		this.accomodationCityId = accomodationCityId;
	}

	public String getAccomodationCity() {
		return accomodationCity;
	}

	public void setAccomodationCity(String accomodationCity) {
		this.accomodationCity = accomodationCity;
	}

	public String getAccomodationPrefLocation() {
		return accomodationPrefLocation;
	}

	public void setAccomodationPrefLocation(String accomodationPrefLocation) {
		this.accomodationPrefLocation = accomodationPrefLocation;
	}

	public String getAccomodationFromDate() {
		return accomodationFromDate;
	}

	public void setAccomodationFromDate(String accomodationFromDate) {
		this.accomodationFromDate = accomodationFromDate;
	}

	public String getAccomodationFromTime() {
		return accomodationFromTime;
	}

	public void setAccomodationFromTime(String accomodationFromTime) {
		this.accomodationFromTime = accomodationFromTime;
	}

	public String getAccomodationToDate() {
		return accomodationToDate;
	}

	public void setAccomodationToDate(String accomodationToDate) {
		this.accomodationToDate = accomodationToDate;
	}

	public String getAccomodationToTime() {
		return accomodationToTime;
	}

	public void setAccomodationToTime(String accomodationToTime) {
		this.accomodationToTime = accomodationToTime;
	}

	public String getAccomodationId() {
		return accomodationId;
	}

	public void setAccomodationId(String accomodationId) {
		this.accomodationId = accomodationId;
	}

	public String getLocalConveyanceCode() {
		return localConveyanceCode;
	}

	public void setLocalConveyanceCode(String localConveyanceCode) {
		this.localConveyanceCode = localConveyanceCode;
	}

	public String getLocalConveyanceCity() {
		return localConveyanceCity;
	}

	public void setLocalConveyanceCity(String localConveyanceCity) {
		this.localConveyanceCity = localConveyanceCity;
	}

	public String getLocalConveyanceTravelDetail() {
		return localConveyanceTravelDetail;
	}

	public void setLocalConveyanceTravelDetail(String localConveyanceTravelDetail) {
		this.localConveyanceTravelDetail = localConveyanceTravelDetail;
	}

	public String getLocalConveyanceToDate() {
		return localConveyanceToDate;
	}

	public void setLocalConveyanceToDate(String localConveyanceToDate) {
		this.localConveyanceToDate = localConveyanceToDate;
	}

	public String getLocalConveyanceFromDate() {
		return localConveyanceFromDate;
	}

	public void setLocalConveyanceFromDate(String localConveyanceFromDate) {
		this.localConveyanceFromDate = localConveyanceFromDate;
	}

	public String getLocalConveyanceFromTime() {
		return localConveyanceFromTime;
	}

	public void setLocalConveyanceFromTime(String localConveyanceFromTime) {
		this.localConveyanceFromTime = localConveyanceFromTime;
	}

	public String getLocalConveyanceToTime() {
		return localConveyanceToTime;
	}

	public void setLocalConveyanceToTime(String localConveyanceToTime) {
		this.localConveyanceToTime = localConveyanceToTime;
	}

	public String getLocalConveyanceTravelMedium() {
		return localConveyanceTravelMedium;
	}

	public void setLocalConveyanceTravelMedium(String localConveyanceTravelMedium) {
		this.localConveyanceTravelMedium = localConveyanceTravelMedium;
	}

	public String getTravellerToken() {
		return travellerToken;
	}

	public void setTravellerToken(String travellerToken) {
		this.travellerToken = travellerToken;
	}

	public String getAccomodationFlag() {
		return accomodationFlag;
	}

	public void setAccomodationFlag(String accomodationFlag) {
		this.accomodationFlag = accomodationFlag;
	}

	public String getLocalConveyanceFlag() {
		return localConveyanceFlag;
	}

	public void setLocalConveyanceFlag(String localConveyanceFlag) {
		this.localConveyanceFlag = localConveyanceFlag;
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

	public String getJourneyFromPlaceItt() {
		return journeyFromPlaceItt;
	}

	public void setJourneyFromPlaceItt(String journeyFromPlaceItt) {
		this.journeyFromPlaceItt = journeyFromPlaceItt;
	}

	public String getJourneyToPlaceItt() {
		return journeyToPlaceItt;
	}

	public void setJourneyToPlaceItt(String journeyToPlaceItt) {
		this.journeyToPlaceItt = journeyToPlaceItt;
	}

	public String getJourneyModeIdItt() {
		return journeyModeIdItt;
	}

	public void setJourneyModeIdItt(String journeyModeIdItt) {
		this.journeyModeIdItt = journeyModeIdItt;
	}

	public String getJourneyDateItt() {
		return journeyDateItt;
	}

	public void setJourneyDateItt(String journeyDateItt) {
		this.journeyDateItt = journeyDateItt;
	}

	public String getJourneyTimeItt() {
		return journeyTimeItt;
	}

	public void setJourneyTimeItt(String journeyTimeItt) {
		this.journeyTimeItt = journeyTimeItt;
	}

	public String getJourCodeItt() {
		return jourCodeItt;
	}

	public void setJourCodeItt(String jourCodeItt) {
		this.jourCodeItt = jourCodeItt;
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

	public String getJourneyModeItt() {
		return journeyModeItt;
	}

	public void setJourneyModeItt(String journeyModeItt) {
		this.journeyModeItt = journeyModeItt;
	}

	public String getOtherProject() {
		return otherProject;
	}

	public void setOtherProject(String otherProject) {
		this.otherProject = otherProject;
	}

	public String getOtherCustomerName() {
		return otherCustomerName;
	}

	public void setOtherCustomerName(String otherCustomerName) {
		this.otherCustomerName = otherCustomerName;
	}

	public String getApplicantComments() {
		return applicantComments;
	}

	public void setApplicantComments(String applicantComments) {
		this.applicantComments = applicantComments;
	}

	public String getTravelTypeId() {
		return travelTypeId;
	}

	public void setTravelTypeId(String travelTypeId) {
		this.travelTypeId = travelTypeId;
	}

	public String getTravelTypeName() {
		return travelTypeName;
	}

	public void setTravelTypeName(String travelTypeName) {
		this.travelTypeName = travelTypeName;
	}

	public String getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	public String getInitiatorIdItt() {
		return initiatorIdItt;
	}

	public void setInitiatorIdItt(String initiatorIdItt) {
		this.initiatorIdItt = initiatorIdItt;
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

	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}

	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}

	public String getKeepInformToCode() {
		return keepInformToCode;
	}

	public void setKeepInformToCode(String keepInformToCode) {
		this.keepInformToCode = keepInformToCode;
	}

	public String getKeepInformToName() {
		return keepInformToName;
	}

	public void setKeepInformToName(String keepInformToName) {
		this.keepInformToName = keepInformToName;
	}

	public String getCheckReportingStructure() {
		return checkReportingStructure;
	}

	public void setCheckReportingStructure(String checkReportingStructure) {
		this.checkReportingStructure = checkReportingStructure;
	}

	public String getCheckApproveRejectStatus() {
		return checkApproveRejectStatus;
	}

	public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
		this.checkApproveRejectStatus = checkApproveRejectStatus;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isViewDtlFlag() {
		return viewDtlFlag;
	}

	public void setViewDtlFlag(boolean viewDtlFlag) {
		this.viewDtlFlag = viewDtlFlag;
	}

	public boolean isAppRejButtonFlag() {
		return appRejButtonFlag;
	}

	public void setAppRejButtonFlag(boolean appRejButtonFlag) {
		this.appRejButtonFlag = appRejButtonFlag;
	}

	public String getPolicyAdvanceAllowed() {
		return policyAdvanceAllowed;
	}

	public void setPolicyAdvanceAllowed(String policyAdvanceAllowed) {
		this.policyAdvanceAllowed = policyAdvanceAllowed;
	}

	public String getMaxDaysTravelClaim() {
		return maxDaysTravelClaim;
	}

	public void setMaxDaysTravelClaim(String maxDaysTravelClaim) {
		this.maxDaysTravelClaim = maxDaysTravelClaim;
	}

	public String getPayModeForAdvanceClaimCheque() {
		return payModeForAdvanceClaimCheque;
	}

	public void setPayModeForAdvanceClaimCheque(String payModeForAdvanceClaimCheque) {
		this.payModeForAdvanceClaimCheque = payModeForAdvanceClaimCheque;
	}

	public String getPayModeForAdvanceClaimCash() {
		return payModeForAdvanceClaimCash;
	}

	public void setPayModeForAdvanceClaimCash(String payModeForAdvanceClaimCash) {
		this.payModeForAdvanceClaimCash = payModeForAdvanceClaimCash;
	}

	public String getPayModeForAdvanceClaimTransfer() {
		return payModeForAdvanceClaimTransfer;
	}

	public void setPayModeForAdvanceClaimTransfer(
			String payModeForAdvanceClaimTransfer) {
		this.payModeForAdvanceClaimTransfer = payModeForAdvanceClaimTransfer;
	}

	public String getPayModeForAdvanceClaimInSalary() {
		return payModeForAdvanceClaimInSalary;
	}

	public void setPayModeForAdvanceClaimInSalary(
			String payModeForAdvanceClaimInSalary) {
		this.payModeForAdvanceClaimInSalary = payModeForAdvanceClaimInSalary;
	}

	public String getAmountWithBill() {
		return amountWithBill;
	}

	public void setAmountWithBill(String amountWithBill) {
		this.amountWithBill = amountWithBill;
	}

	public String getAmountWithOutBill() {
		return amountWithOutBill;
	}

	public void setAmountWithOutBill(String amountWithOutBill) {
		this.amountWithOutBill = amountWithOutBill;
	}

	public String getTravelNameItt() {
		return travelNameItt;
	}

	public void setTravelNameItt(String travelNameItt) {
		this.travelNameItt = travelNameItt;
	}

	public boolean isBackFlag() {
		return backFlag;
	}

	public void setBackFlag(boolean backFlag) {
		this.backFlag = backFlag;
	}

	public String getTravellerGradeId() {
		return travellerGradeId;
	}

	public void setTravellerGradeId(String travellerGradeId) {
		this.travellerGradeId = travellerGradeId;
	}

	public boolean isPolicyViolated() {
		return policyViolated;
	}

	public void setPolicyViolated(boolean policyViolated) {
		this.policyViolated = policyViolated;
	}

	public String getPolicyViolationMsg() {
		return policyViolationMsg;
	}

	public void setPolicyViolationMsg(String policyViolationMsg) {
		this.policyViolationMsg = policyViolationMsg;
	}

	public String getViolationReason() {
		return violationReason;
	}

	public void setViolationReason(String violationReason) {
		this.violationReason = violationReason;
	}

	public String getCityGrade() {
		return cityGrade;
	}

	public void setCityGrade(String cityGrade) {
		this.cityGrade = cityGrade;
	}

	public String getInitiatorGradeId() {
		return initiatorGradeId;
	}

	public void setInitiatorGradeId(String initiatorGradeId) {
		this.initiatorGradeId = initiatorGradeId;
	}

	public String getViolationStatus() {
		return violationStatus;
	}

	public void setViolationStatus(String violationStatus) {
		this.violationStatus = violationStatus;
	}

	public String getViolationFlag() {
		return violationFlag;
	}

	public void setViolationFlag(String violationFlag) {
		this.violationFlag = violationFlag;
	}

	public String getApproverViolationComments() {
		return approverViolationComments;
	}

	public void setApproverViolationComments(String approverViolationComments) {
		this.approverViolationComments = approverViolationComments;
	}

	public String getApproverCommentsFlag() {
		return approverCommentsFlag;
	}

	public void setApproverCommentsFlag(String approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}

	public String getMinNumberOfAdvanceDaysToApplyForTravel() {
		return minNumberOfAdvanceDaysToApplyForTravel;
	}

	public void setMinNumberOfAdvanceDaysToApplyForTravel(
			String minNumberOfAdvanceDaysToApplyForTravel) {
		this.minNumberOfAdvanceDaysToApplyForTravel = minNumberOfAdvanceDaysToApplyForTravel;
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

	public boolean isApproverListFlag() {
		return approverListFlag;
	}

	public void setApproverListFlag(boolean approverListFlag) {
		this.approverListFlag = approverListFlag;
	}

	public String getInitiatorDateOfBirth() {
		return initiatorDateOfBirth;
	}

	public void setInitiatorDateOfBirth(String initiatorDateOfBirth) {
		this.initiatorDateOfBirth = initiatorDateOfBirth;
	}

	public String getEmployeeDateOfBirth() {
		return employeeDateOfBirth;
	}

	public void setEmployeeDateOfBirth(String employeeDateOfBirth) {
		this.employeeDateOfBirth = employeeDateOfBirth;
	}

	public String getEmployeeDateOfBirthFromList() {
		return employeeDateOfBirthFromList;
	}

	public void setEmployeeDateOfBirthFromList(String employeeDateOfBirthFromList) {
		this.employeeDateOfBirthFromList = employeeDateOfBirthFromList;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public String getTravelTypeSelf() {
		return travelTypeSelf;
	}

	public void setTravelTypeSelf(String travelTypeSelf) {
		this.travelTypeSelf = travelTypeSelf;
	}

	public String getTravelTypeTeam() {
		return travelTypeTeam;
	}

	public void setTravelTypeTeam(String travelTypeTeam) {
		this.travelTypeTeam = travelTypeTeam;
	}

	public String getTravelTypeGuest() {
		return travelTypeGuest;
	}

	public void setTravelTypeGuest(String travelTypeGuest) {
		this.travelTypeGuest = travelTypeGuest;
	}

	public String getIsSelfFlag() {
		return isSelfFlag;
	}

	public void setIsSelfFlag(String isSelfFlag) {
		this.isSelfFlag = isSelfFlag;
	}

	public String getIsEmployeeFlag() {
		return isEmployeeFlag;
	}

	public void setIsEmployeeFlag(String isEmployeeFlag) {
		this.isEmployeeFlag = isEmployeeFlag;
	}

	public String getIsGuestFlag() {
		return isGuestFlag;
	}

	public void setIsGuestFlag(String isGuestFlag) {
		this.isGuestFlag = isGuestFlag;
	}

	public String getCounterVal() {
		return counterVal;
	}

	public void setCounterVal(String counterVal) {
		this.counterVal = counterVal;
	}

	public String getDraftOrSend() {
		return draftOrSend;
	}

	public void setDraftOrSend(String draftOrSend) {
		this.draftOrSend = draftOrSend;
	}

	public String getAddedEmployee() {
		return addedEmployee;
	}

	public void setAddedEmployee(String addedEmployee) {
		this.addedEmployee = addedEmployee;
	}

	public boolean isShowRevokeStatus() {
		return showRevokeStatus;
	}

	public void setShowRevokeStatus(boolean showRevokeStatus) {
		this.showRevokeStatus = showRevokeStatus;
	}

	public String getDatePolicyViolationMsg() {
		return datePolicyViolationMsg;
	}

	public void setDatePolicyViolationMsg(String datePolicyViolationMsg) {
		this.datePolicyViolationMsg = datePolicyViolationMsg;
	}

	public boolean isDatePolicyViolated() {
		return datePolicyViolated;
	}

	public void setDatePolicyViolated(boolean datePolicyViolated) {
		this.datePolicyViolated = datePolicyViolated;
	}

	public String getEmployeeBandFromList() {
		return employeeBandFromList;
	}

	public void setEmployeeBandFromList(String employeeBandFromList) {
		this.employeeBandFromList = employeeBandFromList;
	}

	public String getEmpBand() {
		return empBand;
	}

	public void setEmpBand(String empBand) {
		this.empBand = empBand;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTravelPolicyCurrency() {
		return travelPolicyCurrency;
	}

	public void setTravelPolicyCurrency(String travelPolicyCurrency) {
		this.travelPolicyCurrency = travelPolicyCurrency;
	}

	public String getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	public String getCurrencyEmployeeAdvance() {
		return currencyEmployeeAdvance;
	}

	public void setCurrencyEmployeeAdvance(String currencyEmployeeAdvance) {
		this.currencyEmployeeAdvance = currencyEmployeeAdvance;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	 
 
}
