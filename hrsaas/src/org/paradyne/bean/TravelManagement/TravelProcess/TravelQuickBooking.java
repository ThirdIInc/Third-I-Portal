package org.paradyne.bean.TravelManagement.TravelProcess;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelQuickBooking extends BeanBase {

	// for starting list
	private String defaultCurrency = "";
	private String defaultCurrencyFlag="false";
	//BEGINS -- Journey Details Currency
	private String currencyCost = "";
	private String currencyActualCost = "";
	private String currencyCancelAmountJourney = "";
	//ENDS -- Journey Details Currency
	
	//BEGINS -- Local Conveyance Details
	private String currencyLocalConveyanceTariffCost = "";
	private String currencyCancelAmountLocalConv = "";
	//BEGINS -- Local Conveyance Details
	
	//BEGINS -- Lodging Details
	private String currencyBookingAmount = "";
	private String currencyCorporateRate = "";
	private String currencyCancelAmountAccom = "";
	//ENDS -- Lodging Details
	private String cancelAmountJourney ="";
	private String cancelAmountAccom ="";
	private String cancelAmountLocalConv = "";
	private String srNo = "";
	private String checkSearch="";
	private String  travelId = "";
	private String  initiatorId = "";
	private String  initiaterName = "";
	private String  status = "";
	private String  source = "";
	private String  destination = "";
	private String adminComments="";
	private String pendingData ="false";
	private String completedData ="false";
	private String travelStartDateFilter="";
	private String ittStatus = "";
	private boolean revokeFlag= false;
	private String dateOfBirth ="";
	private String ittTravelSrcDestini = "";
	private String  existingAssignedEmployee ="";
	private String showFlag="false";
	private boolean assignSchedulerFlag = false;
	
	private String toEamilAddress ="";
	
	private String ccMailId ="";
	
	private String ittAssignedEmployee ="";
	
	private String hiddenEmpCode = "";
	
	private String ittEmployeeToken =""; 
	private String ittEmployee =""; 
	private String ittEmployeeCode ="";
	
	private ArrayList subSchdlrList =null;
	
	private String authManagementId ="";
	
	private String hiddenGradeCode ="";

	private String violateFlag = "N";
	
	private String policyViolationMsg = "";

	private String violationFlag = "N";
	private String bookingPageFlag = "false";

	private String lodgeDtlFlag = "false";
	private String localConvFlag = "false";

	private String initiatorCode = "";

	private ArrayList travelBookingList = null;
	private String IttapplicationDate = "";
	private String ittTravelApplicationCode = "";
	private String ittTravelInitiator = "";
	private String ittTravelRequestName = "";
	private String ittTravelStartDate = "";

	private String modeLength = "false";
	private String myPage = "";
	private String listFlag = "false";
	private String totalRecords = "";
	private String noData = "false";

	private String ittTravelId = "";

	private String TravelIdCompleted = "";

	private String initiatorName = "";
	private String applicationDate = "";
	private String travelStartDate = "";
	private String travelEndDate = "";
	private String travelRequestName = "";
	private String travelPurpose = "";
	private String travelProject = "";
	private String travelOtherProject = "";
	private String travelCustomer = "";
	private String travelOtherCustomer = "";
	private String travelType = "";
	private String applicationStatus = "";
	private String travelPurposeId = "";

	private String hiddenApplicationCode = "";

	// fields for journey dtl
	private String journeyFromPlace = "";
	private String journeyToPlace = "";
	private String journeyModeId = "";
	private String journeyDate = "";
	private String journeyTime = "";
	private String busTrainNo = "";
	private String ticketNo = "";
	private String cost = "";
	private String journeydetails = "";
	private String journeyFileUpload = "";
	private ArrayList journeyList = null;
	private String listType = "";
	private String jourMode = "";

	private String myPageCompleted = "";
	private String totalRecordsCompleted = "";
	private String totalPageRecordFlag = "false";
	private String totalPageCompletedFlag = "false";

	private String ittTravelCodeCompleted = "";
	private String ittTravelInitiatorCompleted = "";
	private String ittTravelRequestNameCompleted = "";
	private String IttapplicationDateCompleted = "";
	private String ittTravelStartDateCompleted = "";
	private ArrayList travelBookingCompleteList = null;

	// fields for employee information
	private String currencyEmployeeAdvance = "";
	private String employeeNameFromList = "";
	private String employeeAgeFromList = "";
	private String employeeContactFromList = "";
	private String employeeAdvanceFromList = "";
	private ArrayList travellerList = null;

	private String ittTravelStatus = "";
	private String ittTravelStatusCompleted = "";

	private String buttonShowFlag = "false";

	// fields for Lodging Details

	private String accomodationRoomTypeId = "";
	private String accomodationHotelTypeId = "";
	private String accomodationHotelType = "";
	private String accomodationRoomType = "";
	private String accomodationCity = "";
	private String accomodationPrefLocation = "";
	private String accomodationFromDate = "";
	private String accomodationToDate = "";
	private String accomodationFromTime = "";
	private String accomodationToTime = "";
	private String bookingAmount = "";
	private String bookingDetails = "";
	private String bookingFileUpload = "";
	private ArrayList accomodationList = null;
	private String actualCost ="";

	// fields for local conveyance

	private String localConveyanceCity = "";
	private String localConveyanceTravelDetail = "";
	private String localConveyanceTravelMedium = "";
	private String localConveyanceFromDate = "";
	private String localConveyanceFromTime = "";
	private String localConveyanceToDate = "";
	private String localConveyanceToTime = "";
	private String localConveyanceTariffCost = "";
	private String localConveyanceDetails = "";
	private String localConveyanceFileUpload = "";
	private ArrayList localConveyanceList = null;

	private String saveFlag = "false";

	private String journeyAgency = "";
	private String journeyAgencyId = "";
	private String journeyMedium = "";
	private String journeyMediumId = "";
	private String accomodationHotelName = "";
	private String accomodationHotelNameId = "";
	
	private String statusFlag = "";
	
	
	private String noOfDays = "";
	private String corporateRate = "";
	
	private String employeeGrade = "";
	private String employeeDesignation = "";

	public String getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getCorporateRate() {
		return corporateRate;
	}

	public void setCorporateRate(String corporateRate) {
		this.corporateRate = corporateRate;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getJourneyAgency() {
		return journeyAgency;
	}

	public void setJourneyAgency(String journeyAgency) {
		this.journeyAgency = journeyAgency;
	}

	public String getJourneyAgencyId() {
		return journeyAgencyId;
	}

	public void setJourneyAgencyId(String journeyAgencyId) {
		this.journeyAgencyId = journeyAgencyId;
	}

	public String getJourneyMedium() {
		return journeyMedium;
	}

	public void setJourneyMedium(String journeyMedium) {
		this.journeyMedium = journeyMedium;
	}

	public String getJourneyMediumId() {
		return journeyMediumId;
	}

	public void setJourneyMediumId(String journeyMediumId) {
		this.journeyMediumId = journeyMediumId;
	}

	public String getAccomodationHotelName() {
		return accomodationHotelName;
	}

	public void setAccomodationHotelName(String accomodationHotelName) {
		this.accomodationHotelName = accomodationHotelName;
	}

	public String getAccomodationHotelNameId() {
		return accomodationHotelNameId;
	}

	public void setAccomodationHotelNameId(String accomodationHotelNameId) {
		this.accomodationHotelNameId = accomodationHotelNameId;
	}

	public String getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	public ArrayList getJourneyList() {
		return journeyList;
	}

	public void setJourneyList(ArrayList journeyList) {
		this.journeyList = journeyList;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getTravelStartDate() {
		return travelStartDate;
	}

	public void setTravelStartDate(String travelStartDate) {
		this.travelStartDate = travelStartDate;
	}

	public String getTravelRequestName() {
		return travelRequestName;
	}

	public void setTravelRequestName(String travelRequestName) {
		this.travelRequestName = travelRequestName;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}

	public String getTravelProject() {
		return travelProject;
	}

	public void setTravelProject(String travelProject) {
		this.travelProject = travelProject;
	}

	public String getTravelOtherProject() {
		return travelOtherProject;
	}

	public void setTravelOtherProject(String travelOtherProject) {
		this.travelOtherProject = travelOtherProject;
	}

	public String getTravelCustomer() {
		return travelCustomer;
	}

	public void setTravelCustomer(String travelCustomer) {
		this.travelCustomer = travelCustomer;
	}

	public String getTravelOtherCustomer() {
		return travelOtherCustomer;
	}

	public void setTravelOtherCustomer(String travelOtherCustomer) {
		this.travelOtherCustomer = travelOtherCustomer;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getHiddenApplicationCode() {
		return hiddenApplicationCode;
	}

	public void setHiddenApplicationCode(String hiddenApplicationCode) {
		this.hiddenApplicationCode = hiddenApplicationCode;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public ArrayList getTravelBookingList() {
		return travelBookingList;
	}

	public void setTravelBookingList(ArrayList travelBookingList) {
		this.travelBookingList = travelBookingList;
	}

	public String getIttapplicationDate() {
		return IttapplicationDate;
	}

	public void setIttapplicationDate(String ittapplicationDate) {
		IttapplicationDate = ittapplicationDate;
	}

	public String getIttTravelApplicationCode() {
		return ittTravelApplicationCode;
	}

	public void setIttTravelApplicationCode(String ittTravelApplicationCode) {
		this.ittTravelApplicationCode = ittTravelApplicationCode;
	}

	public String getIttTravelInitiator() {
		return ittTravelInitiator;
	}

	public void setIttTravelInitiator(String ittTravelInitiator) {
		this.ittTravelInitiator = ittTravelInitiator;
	}

	public String getIttTravelRequestName() {
		return ittTravelRequestName;
	}

	public void setIttTravelRequestName(String ittTravelRequestName) {
		this.ittTravelRequestName = ittTravelRequestName;
	}

	public String getIttTravelStartDate() {
		return ittTravelStartDate;
	}

	public void setIttTravelStartDate(String ittTravelStartDate) {
		this.ittTravelStartDate = ittTravelStartDate;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getTravelEndDate() {
		return travelEndDate;
	}

	public void setTravelEndDate(String travelEndDate) {
		this.travelEndDate = travelEndDate;
	}

	public String getTravelPurposeId() {
		return travelPurposeId;
	}

	public void setTravelPurposeId(String travelPurposeId) {
		this.travelPurposeId = travelPurposeId;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getListFlag() {
		return listFlag;
	}

	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
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

	public String getBusTrainNo() {
		return busTrainNo;
	}

	public void setBusTrainNo(String busTrainNo) {
		this.busTrainNo = busTrainNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getJourneydetails() {
		return journeydetails;
	}

	public void setJourneydetails(String journeydetails) {
		this.journeydetails = journeydetails;
	}

	public String getJourneyFileUpload() {
		return journeyFileUpload;
	}

	public void setJourneyFileUpload(String journeyFileUpload) {
		this.journeyFileUpload = journeyFileUpload;
	}

	public String getAccomodationRoomTypeId() {
		return accomodationRoomTypeId;
	}

	public void setAccomodationRoomTypeId(String accomodationRoomTypeId) {
		this.accomodationRoomTypeId = accomodationRoomTypeId;
	}

	public String getAccomodationHotelTypeId() {
		return accomodationHotelTypeId;
	}

	public void setAccomodationHotelTypeId(String accomodationHotelTypeId) {
		this.accomodationHotelTypeId = accomodationHotelTypeId;
	}

	public String getAccomodationRoomType() {
		return accomodationRoomType;
	}

	public void setAccomodationRoomType(String accomodationRoomType) {
		this.accomodationRoomType = accomodationRoomType;
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

	public String getAccomodationToDate() {
		return accomodationToDate;
	}

	public void setAccomodationToDate(String accomodationToDate) {
		this.accomodationToDate = accomodationToDate;
	}

	public String getAccomodationFromTime() {
		return accomodationFromTime;
	}

	public void setAccomodationFromTime(String accomodationFromTime) {
		this.accomodationFromTime = accomodationFromTime;
	}

	public String getAccomodationToTime() {
		return accomodationToTime;
	}

	public void setAccomodationToTime(String accomodationToTime) {
		this.accomodationToTime = accomodationToTime;
	}

	public String getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(String bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

	public String getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(String bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public String getBookingFileUpload() {
		return bookingFileUpload;
	}

	public void setBookingFileUpload(String bookingFileUpload) {
		this.bookingFileUpload = bookingFileUpload;
	}

	public ArrayList getAccomodationList() {
		return accomodationList;
	}

	public void setAccomodationList(ArrayList accomodationList) {
		this.accomodationList = accomodationList;
	}

	public String getAccomodationHotelType() {
		return accomodationHotelType;
	}

	public void setAccomodationHotelType(String accomodationHotelType) {
		this.accomodationHotelType = accomodationHotelType;
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

	public void setLocalConveyanceTravelDetail(
			String localConveyanceTravelDetail) {
		this.localConveyanceTravelDetail = localConveyanceTravelDetail;
	}

	public String getLocalConveyanceTravelMedium() {
		return localConveyanceTravelMedium;
	}

	public void setLocalConveyanceTravelMedium(
			String localConveyanceTravelMedium) {
		this.localConveyanceTravelMedium = localConveyanceTravelMedium;
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

	public String getLocalConveyanceToDate() {
		return localConveyanceToDate;
	}

	public void setLocalConveyanceToDate(String localConveyanceToDate) {
		this.localConveyanceToDate = localConveyanceToDate;
	}

	public String getLocalConveyanceToTime() {
		return localConveyanceToTime;
	}

	public void setLocalConveyanceToTime(String localConveyanceToTime) {
		this.localConveyanceToTime = localConveyanceToTime;
	}

	public String getLocalConveyanceTariffCost() {
		return localConveyanceTariffCost;
	}

	public void setLocalConveyanceTariffCost(String localConveyanceTariffCost) {
		this.localConveyanceTariffCost = localConveyanceTariffCost;
	}

	public String getLocalConveyanceDetails() {
		return localConveyanceDetails;
	}

	public void setLocalConveyanceDetails(String localConveyanceDetails) {
		this.localConveyanceDetails = localConveyanceDetails;
	}

	public String getLocalConveyanceFileUpload() {
		return localConveyanceFileUpload;
	}

	public void setLocalConveyanceFileUpload(String localConveyanceFileUpload) {
		this.localConveyanceFileUpload = localConveyanceFileUpload;
	}

	public ArrayList getLocalConveyanceList() {
		return localConveyanceList;
	}

	public void setLocalConveyanceList(ArrayList localConveyanceList) {
		this.localConveyanceList = localConveyanceList;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getMyPageCompleted() {
		return myPageCompleted;
	}

	public void setMyPageCompleted(String myPageCompleted) {
		this.myPageCompleted = myPageCompleted;
	}

	public String getIttTravelCodeCompleted() {
		return ittTravelCodeCompleted;
	}

	public void setIttTravelCodeCompleted(String ittTravelCodeCompleted) {
		this.ittTravelCodeCompleted = ittTravelCodeCompleted;
	}

	public String getIttTravelInitiatorCompleted() {
		return ittTravelInitiatorCompleted;
	}

	public void setIttTravelInitiatorCompleted(
			String ittTravelInitiatorCompleted) {
		this.ittTravelInitiatorCompleted = ittTravelInitiatorCompleted;
	}

	public String getIttTravelRequestNameCompleted() {
		return ittTravelRequestNameCompleted;
	}

	public void setIttTravelRequestNameCompleted(
			String ittTravelRequestNameCompleted) {
		this.ittTravelRequestNameCompleted = ittTravelRequestNameCompleted;
	}

	public String getIttapplicationDateCompleted() {
		return IttapplicationDateCompleted;
	}

	public void setIttapplicationDateCompleted(
			String ittapplicationDateCompleted) {
		IttapplicationDateCompleted = ittapplicationDateCompleted;
	}

	public String getIttTravelStartDateCompleted() {
		return ittTravelStartDateCompleted;
	}

	public void setIttTravelStartDateCompleted(
			String ittTravelStartDateCompleted) {
		this.ittTravelStartDateCompleted = ittTravelStartDateCompleted;
	}

	public ArrayList getTravelBookingCompleteList() {
		return travelBookingCompleteList;
	}

	public void setTravelBookingCompleteList(ArrayList travelBookingCompleteList) {
		this.travelBookingCompleteList = travelBookingCompleteList;
	}

	public String getTotalRecordsCompleted() {
		return totalRecordsCompleted;
	}

	public void setTotalRecordsCompleted(String totalRecordsCompleted) {
		this.totalRecordsCompleted = totalRecordsCompleted;
	}

	public String getTotalPageRecordFlag() {
		return totalPageRecordFlag;
	}

	public void setTotalPageRecordFlag(String totalPageRecordFlag) {
		this.totalPageRecordFlag = totalPageRecordFlag;
	}

	public String getTotalPageCompletedFlag() {
		return totalPageCompletedFlag;
	}

	public void setTotalPageCompletedFlag(String totalPageCompletedFlag) {
		this.totalPageCompletedFlag = totalPageCompletedFlag;
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

	public String getIttTravelStatus() {
		return ittTravelStatus;
	}

	public void setIttTravelStatus(String ittTravelStatus) {
		this.ittTravelStatus = ittTravelStatus;
	}

	public String getIttTravelStatusCompleted() {
		return ittTravelStatusCompleted;
	}

	public void setIttTravelStatusCompleted(String ittTravelStatusCompleted) {
		this.ittTravelStatusCompleted = ittTravelStatusCompleted;
	}

	public String getButtonShowFlag() {
		return buttonShowFlag;
	}

	public void setButtonShowFlag(String buttonShowFlag) {
		this.buttonShowFlag = buttonShowFlag;
	}

	public String getJourMode() {
		return jourMode;
	}

	public void setJourMode(String jourMode) {
		this.jourMode = jourMode;
	}

	public String getIttTravelId() {
		return ittTravelId;
	}

	public void setIttTravelId(String ittTravelId) {
		this.ittTravelId = ittTravelId;
	}

	public String getTravelIdCompleted() {
		return TravelIdCompleted;
	}

	public void setTravelIdCompleted(String travelIdCompleted) {
		TravelIdCompleted = travelIdCompleted;
	}

	public String getLodgeDtlFlag() {
		return lodgeDtlFlag;
	}

	public void setLodgeDtlFlag(String lodgeDtlFlag) {
		this.lodgeDtlFlag = lodgeDtlFlag;
	}

	public String getLocalConvFlag() {
		return localConvFlag;
	}

	public void setLocalConvFlag(String localConvFlag) {
		this.localConvFlag = localConvFlag;
	}

	public String getInitiatorCode() {
		return initiatorCode;
	}

	public void setInitiatorCode(String initiatorCode) {
		this.initiatorCode = initiatorCode;
	}

	public String getBookingPageFlag() {
		return bookingPageFlag;
	}

	public void setBookingPageFlag(String bookingPageFlag) {
		this.bookingPageFlag = bookingPageFlag;
	}

	public String getViolateFlag() {
		return violateFlag;
	}

	public void setViolateFlag(String violateFlag) {
		this.violateFlag = violateFlag;
	}

	public String getViolationFlag() {
		return violationFlag;
	}

	public void setViolationFlag(String violationFlag) {
		this.violationFlag = violationFlag;
	}

	public String getPolicyViolationMsg() {
		return policyViolationMsg;
	}

	public void setPolicyViolationMsg(String policyViolationMsg) {
		this.policyViolationMsg = policyViolationMsg;
	}

	public String getHiddenGradeCode() {
		return hiddenGradeCode;
	}

	public void setHiddenGradeCode(String hiddenGradeCode) {
		this.hiddenGradeCode = hiddenGradeCode;
	}

	public String getAuthManagementId() {
		return authManagementId;
	}

	public void setAuthManagementId(String authManagementId) {
		this.authManagementId = authManagementId;
	}

	public String getIttEmployeeToken() {
		return ittEmployeeToken;
	}

	public void setIttEmployeeToken(String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}

	public String getIttEmployee() {
		return ittEmployee;
	}

	public void setIttEmployee(String ittEmployee) {
		this.ittEmployee = ittEmployee;
	}

	public String getIttEmployeeCode() {
		return ittEmployeeCode;
	}

	public void setIttEmployeeCode(String ittEmployeeCode) {
		this.ittEmployeeCode = ittEmployeeCode;
	}

	public ArrayList getSubSchdlrList() {
		return subSchdlrList;
	}

	public void setSubSchdlrList(ArrayList subSchdlrList) {
		this.subSchdlrList = subSchdlrList;
	}

	public String getHiddenEmpCode() {
		return hiddenEmpCode;
	}

	public void setHiddenEmpCode(String hiddenEmpCode) {
		this.hiddenEmpCode = hiddenEmpCode;
	}

	public String getIttAssignedEmployee() {
		return ittAssignedEmployee;
	}

	public void setIttAssignedEmployee(String ittAssignedEmployee) {
		this.ittAssignedEmployee = ittAssignedEmployee;
	}

	public String getToEamilAddress() {
		return toEamilAddress;
	}

	public void setToEamilAddress(String toEamilAddress) {
		this.toEamilAddress = toEamilAddress;
	}

	public String getCcMailId() {
		return ccMailId;
	}

	public void setCcMailId(String ccMailId) {
		this.ccMailId = ccMailId;
	}

	public boolean isAssignSchedulerFlag() {
		return assignSchedulerFlag;
	}

	public void setAssignSchedulerFlag(boolean assignSchedulerFlag) {
		this.assignSchedulerFlag = assignSchedulerFlag;
	}

	public String getExistingAssignedEmployee() {
		return existingAssignedEmployee;
	}

	public void setExistingAssignedEmployee(String existingAssignedEmployee) {
		this.existingAssignedEmployee = existingAssignedEmployee;
	}

	public String getIttStatus() {
		return ittStatus;
	}

	public void setIttStatus(String ittStatus) {
		this.ittStatus = ittStatus;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getActualCost() {
		return actualCost;
	}

	public void setActualCost(String actualCost) {
		this.actualCost = actualCost;
	}

	public String getPendingData() {
		return pendingData;
	}

	public void setPendingData(String pendingData) {
		this.pendingData = pendingData;
	}

	public String getCompletedData() {
		return completedData;
	}

	public void setCompletedData(String completedData) {
		this.completedData = completedData;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}
 
	public String getInitiaterName() {
		return initiaterName;
	}

	public void setInitiaterName(String initiaterName) {
		this.initiaterName = initiaterName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(String initiatorId) {
		this.initiatorId = initiatorId;
	}

	public String getCheckSearch() {
		return checkSearch;
	}

	public void setCheckSearch(String checkSearch) {
		this.checkSearch = checkSearch;
	}

	public String getAdminComments() {
		return adminComments;
	}

	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}

	public String getTravelStartDateFilter() {
		return travelStartDateFilter;
	}

	public void setTravelStartDateFilter(String travelStartDateFilter) {
		this.travelStartDateFilter = travelStartDateFilter;
	}

	public boolean isRevokeFlag() {
		return revokeFlag;
	}

	public void setRevokeFlag(boolean revokeFlag) {
		this.revokeFlag = revokeFlag;
	}

	public String getIttTravelSrcDestini() {
		return ittTravelSrcDestini;
	}

	public void setIttTravelSrcDestini(String ittTravelSrcDestini) {
		this.ittTravelSrcDestini = ittTravelSrcDestini;
	}

	public String getCancelAmountJourney() {
		return cancelAmountJourney;
	}

	public void setCancelAmountJourney(String cancelAmountJourney) {
		this.cancelAmountJourney = cancelAmountJourney;
	}

	public String getCancelAmountAccom() {
		return cancelAmountAccom;
	}

	public void setCancelAmountAccom(String cancelAmountAccom) {
		this.cancelAmountAccom = cancelAmountAccom;
	}

	public String getCancelAmountLocalConv() {
		return cancelAmountLocalConv;
	}

	public void setCancelAmountLocalConv(String cancelAmountLocalConv) {
		this.cancelAmountLocalConv = cancelAmountLocalConv;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getEmployeeGrade() {
		return employeeGrade;
	}

	public void setEmployeeGrade(String employeeGrade) {
		this.employeeGrade = employeeGrade;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	public String getCurrencyCost() {
		return currencyCost;
	}

	public void setCurrencyCost(String currencyCost) {
		this.currencyCost = currencyCost;
	}

	public String getCurrencyActualCost() {
		return currencyActualCost;
	}

	public void setCurrencyActualCost(String currencyActualCost) {
		this.currencyActualCost = currencyActualCost;
	}

	public String getCurrencyCancelAmountJourney() {
		return currencyCancelAmountJourney;
	}

	public void setCurrencyCancelAmountJourney(String currencyCancelAmountJourney) {
		this.currencyCancelAmountJourney = currencyCancelAmountJourney;
	}

	public String getCurrencyEmployeeAdvance() {
		return currencyEmployeeAdvance;
	}

	public void setCurrencyEmployeeAdvance(String currencyEmployeeAdvance) {
		this.currencyEmployeeAdvance = currencyEmployeeAdvance;
	}

	public String getCurrencyLocalConveyanceTariffCost() {
		return currencyLocalConveyanceTariffCost;
	}

	public void setCurrencyLocalConveyanceTariffCost(
			String currencyLocalConveyanceTariffCost) {
		this.currencyLocalConveyanceTariffCost = currencyLocalConveyanceTariffCost;
	}

	public String getCurrencyCancelAmountLocalConv() {
		return currencyCancelAmountLocalConv;
	}

	public void setCurrencyCancelAmountLocalConv(
			String currencyCancelAmountLocalConv) {
		this.currencyCancelAmountLocalConv = currencyCancelAmountLocalConv;
	}

	public String getCurrencyCorporateRate() {
		return currencyCorporateRate;
	}

	public void setCurrencyCorporateRate(String currencyCorporateRate) {
		this.currencyCorporateRate = currencyCorporateRate;
	}

	public String getCurrencyCancelAmountAccom() {
		return currencyCancelAmountAccom;
	}

	public void setCurrencyCancelAmountAccom(String currencyCancelAmountAccom) {
		this.currencyCancelAmountAccom = currencyCancelAmountAccom;
	}

	public String getCurrencyBookingAmount() {
		return currencyBookingAmount;
	}

	public void setCurrencyBookingAmount(String currencyBookingAmount) {
		this.currencyBookingAmount = currencyBookingAmount;
	}

	public String getDefaultCurrencyFlag() {
		return defaultCurrencyFlag;
	}

	public void setDefaultCurrencyFlag(String defaultCurrencyFlag) {
		this.defaultCurrencyFlag = defaultCurrencyFlag;
	}
}