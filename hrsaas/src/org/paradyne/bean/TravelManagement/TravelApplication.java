 package org.paradyne.bean.TravelManagement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelApplication extends BeanBase {
	//for Application
	private String empToken ="";
	private String empName="";
	private String travelAppId="";
	private String checkEdit="";
	private String empId="";
	private String branchName="";
	private String dept=""; 
	private String desg="";
	private String appDate="";
	private String status="";
	private String fromPlace=""; 
	private String toPlace="";
	private String journeyName="";
	private String journeyClassName="";
	private String journeyDate="";
	private String journeyTime=""; 
	private String fromPlaceId="";
	private String toPlaceId="";
	private String journeyClassId="";
	private String journeyId=""; 
	private String applicantComment=""; 
	private String hidStatus=""; 
	private String trHidSchStatus="";
	private String journeyModeRefNumber="";
	private String gradeCode="";
	private String gradeName="";
	private String expenseFlag="";
	private String gradeJourneyId="";
	private String gradeClassId="";
	private String modeName="";
	private String empAge="";
	private String contactNo=""; 
	private String idProof="";
	private String idNumber="";
	ArrayList modelist = new ArrayList();
	ArrayList appModeList = new ArrayList();  // for display the applicable mode list
	private String appModeName="";
	private String appClassName="";
	private String appSerial="";
	private String appDispFlag="true";  
	private String alertSchMode="";   // for checking the schedular mode
	private String alertSchFlag="false"; 
	private String alertTrOtherCost="";  // for checking the Travel Cost
	private String alertHotelOtherCost="";   
	private String alertHotelBillCost=""; 
	private String trOtherExp="";  // for display the gradewise cost
	private String lodgOtherAllow="";   
	private String lodgAllowPerDay=""; 
	private String outPocketAllow="";   
	private String foodAllow="";
	private String cssClassTYpe="";
	
	//for Iterator
	ArrayList journeyList = new ArrayList();
	private String itFromPlaceName ="";
	private String itToPlaceName="";
	private String itJourneyName="";
	private String itJourneyClassName="";
	private String itFromPlaceId="";
	private String itToPlaceId="";
	private String itJourneyId="";
	private String itJourneyClassId="";
	private String itJourneyDate="";
	private String itJourneyTime="";
	private String serialNo="";
	private String noDataFlag ="false";
	private String itJourneyModeRefNumber="";
	
	
	//bean for schedule
	
	private String travelAppFlag="true"; 
	private String schJournyId="";  
	private String  statusSch=""; 
	private String schModeNumber="";
	private String schTicketNumber ="";
	private String schJourneyDate ="";
	private String schJourneyTime ="";
	private String schJourneyCost =""; 
	private String schViewJourneyMode ="";
	private String schViewJourneyClass ="";
	private String schViewFlag ="false";
	private String schExtraAmt ="";
	private String schJourneyComment ="";
	private String totalJCost ="";
	private String  schSaveFlag ="true";
	private String scheduleFinalFlag=""; 
	private String travelOtherCost ="";
	private String  hotelPerDayCost ="";
	private String hotelOtherCost=""; 
	ArrayList hotelList = new ArrayList(); 
	
	// bean for hotel
	private String htFromPlaceName=""; 
	private String htToPlaceName="";  
	private String  hotelName=""; 
	private String hotelAddress="";
	private String chkInDate ="";
	private String chkInTime =""; 
	private String  chkOutDate=""; 
	private String chkOutTime="";
	private String hotelBill ="";
	private String hotelTotalBill ="";
	private String extraHotelBill="";
	private String hotelComment="";
	private String totalTravelCost ="";
	
	// used for cancellation
	private String journeyDtlId;
	private String cancelJourneyAmt;
	private String hotelDtlId;
	private String hotelCancelAmt;
	private String totalJCancel;
	private String hotelTotalCanceled;
	private String totalCanceled;	
	private String cancelStatus="";
	private String confStatus="";
	
	 
	ArrayList schJourneyList = new ArrayList();
	
	public String getSchJournyId() {
		return schJournyId;
	}
	public void setSchJournyId(String schJournyId) {
		this.schJournyId = schJournyId;
	}
	public String getNoDataFlag() {
		return noDataFlag;
	}
	public void setNoDataFlag(String noDataFlag) {
		this.noDataFlag = noDataFlag;
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
	public String getTravelAppId() {
		return travelAppId;
	}
	public void setTravelAppId(String travelAppId) {
		this.travelAppId = travelAppId;
	}
	public String getCheckEdit() {
		return checkEdit;
	}
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
 
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getFromPlaceId() {
		return fromPlaceId;
	}
	public void setFromPlaceId(String fromPlaceId) {
		this.fromPlaceId = fromPlaceId;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public String getJourneyName() {
		return journeyName;
	}
	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}
	public String getJourneyClassName() {
		return journeyClassName;
	}
	public void setJourneyClassName(String journeyClassName) {
		this.journeyClassName = journeyClassName;
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
	public String getToPlaceId() {
		return toPlaceId;
	}
	public void setToPlaceId(String toPlaceId) {
		this.toPlaceId = toPlaceId;
	}
	public String getJourneyClassId() {
		return journeyClassId;
	}
	public void setJourneyClassId(String journeyClassId) {
		this.journeyClassId = journeyClassId;
	}
	public String getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(String journeyId) {
		this.journeyId = journeyId;
	}
	public String getItFromPlaceName() {
		return itFromPlaceName;
	}
	public void setItFromPlaceName(String itFromPlaceName) {
		this.itFromPlaceName = itFromPlaceName;
	}
	public String getItToPlaceName() {
		return itToPlaceName;
	}
	public void setItToPlaceName(String itToPlaceName) {
		this.itToPlaceName = itToPlaceName;
	}
	 
	public String getItJourneyName() {
		return itJourneyName;
	}
	public void setItJourneyName(String itJourneyName) {
		this.itJourneyName = itJourneyName;
	}
	public String getItJourneyId() {
		return itJourneyId;
	}
	public void setItJourneyId(String itJourneyId) {
		this.itJourneyId = itJourneyId;
	}
	public String getItJourneyClassName() {
		return itJourneyClassName;
	}
	public void setItJourneyClassName(String itJourneyClassName) {
		this.itJourneyClassName = itJourneyClassName;
	}
	public String getItFromPlaceId() {
		return itFromPlaceId;
	}
	public void setItFromPlaceId(String itFromPlaceId) {
		this.itFromPlaceId = itFromPlaceId;
	}
	public String getItToPlaceId() {
		return itToPlaceId;
	}
	public void setItToPlaceId(String itToPlaceId) {
		this.itToPlaceId = itToPlaceId;
	}
	 
	public String getItJourneyClassId() {
		return itJourneyClassId;
	}
	public void setItJourneyClassId(String itJourneyClassId) {
		this.itJourneyClassId = itJourneyClassId;
	}
	public String getItJourneyDate() {
		return itJourneyDate;
	}
	public void setItJourneyDate(String itJourneyDate) {
		this.itJourneyDate = itJourneyDate;
	}
	public String getItJourneyTime() {
		return itJourneyTime;
	}
	public void setItJourneyTime(String itJourneyTime) {
		this.itJourneyTime = itJourneyTime;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public ArrayList getJourneyList() {
		return journeyList;
	}
	public void setJourneyList(ArrayList journeyList) {
		this.journeyList = journeyList;
	}
	public String getTravelAppFlag() {
		return travelAppFlag;
	}
	public void setTravelAppFlag(String travelAppFlag) {
		this.travelAppFlag = travelAppFlag;
	}
	public ArrayList getSchJourneyList() {
		return schJourneyList;
	}
	public void setSchJourneyList(ArrayList schJourneyList) {
		this.schJourneyList = schJourneyList;
	}
	public String getStatusSch() {
		return statusSch;
	}
	public void setStatusSch(String statusSch) {
		this.statusSch = statusSch;
	}
	public String getApplicantComment() {
		return applicantComment;
	}
	public void setApplicantComment(String applicantComment) {
		this.applicantComment = applicantComment;
	}
	 
	public String getSchModeNumber() {
		return schModeNumber;
	}
	public void setSchModeNumber(String schModeNumber) {
		this.schModeNumber = schModeNumber;
	}
	public String getSchTicketNumber() {
		return schTicketNumber;
	}
	public void setSchTicketNumber(String schTicketNumber) {
		this.schTicketNumber = schTicketNumber;
	}
	public String getSchJourneyDate() {
		return schJourneyDate;
	}
	public void setSchJourneyDate(String schJourneyDate) {
		this.schJourneyDate = schJourneyDate;
	}
	public String getSchJourneyTime() {
		return schJourneyTime;
	}
	public void setSchJourneyTime(String schJourneyTime) {
		this.schJourneyTime = schJourneyTime;
	}
	public String getSchJourneyCost() {
		return schJourneyCost;
	}
	public void setSchJourneyCost(String schJourneyCost) {
		this.schJourneyCost = schJourneyCost;
	}
	public String getSchViewJourneyMode() {
		return schViewJourneyMode;
	}
	public void setSchViewJourneyMode(String schViewJourneyMode) {
		this.schViewJourneyMode = schViewJourneyMode;
	}
	public String getSchViewJourneyClass() {
		return schViewJourneyClass;
	}
	public void setSchViewJourneyClass(String schViewJourneyClass) {
		this.schViewJourneyClass = schViewJourneyClass;
	}
	public String getSchViewFlag() {
		return schViewFlag;
	}
	public void setSchViewFlag(String schViewFlag) {
		this.schViewFlag = schViewFlag;
	}
	public String getSchExtraAmt() {
		return schExtraAmt;
	}
	public void setSchExtraAmt(String schExtraAmt) {
		this.schExtraAmt = schExtraAmt;
	}
	public String getSchJourneyComment() {
		return schJourneyComment;
	}
	public void setSchJourneyComment(String schJourneyComment) {
		this.schJourneyComment = schJourneyComment;
	}
	public String getHtFromPlaceName() {
		return htFromPlaceName;
	}
	public void setHtFromPlaceName(String htFromPlaceName) {
		this.htFromPlaceName = htFromPlaceName;
	}
	public String getHtToPlaceName() {
		return htToPlaceName;
	}
	public void setHtToPlaceName(String htToPlaceName) {
		this.htToPlaceName = htToPlaceName;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getChkInDate() {
		return chkInDate;
	}
	public void setChkInDate(String chkInDate) {
		this.chkInDate = chkInDate;
	}
	public String getChkInTime() {
		return chkInTime;
	}
	public void setChkInTime(String chkInTime) {
		this.chkInTime = chkInTime;
	}
	public String getChkOutDate() {
		return chkOutDate;
	}
	public void setChkOutDate(String chkOutDate) {
		this.chkOutDate = chkOutDate;
	}
	public String getChkOutTime() {
		return chkOutTime;
	}
	public void setChkOutTime(String chkOutTime) {
		this.chkOutTime = chkOutTime;
	}
	public String getHotelBill() {
		return hotelBill;
	}
	public void setHotelBill(String hotelBill) {
		this.hotelBill = hotelBill;
	}
	public String getTotalJCost() {
		return totalJCost;
	}
	public void setTotalJCost(String totalJCost) {
		this.totalJCost = totalJCost;
	}
	public String getHotelTotalBill() {
		return hotelTotalBill;
	}
	public void setHotelTotalBill(String hotelTotalBill) {
		this.hotelTotalBill = hotelTotalBill;
	}
	public String getTotalTravelCost() {
		return totalTravelCost;
	}
	public void setTotalTravelCost(String totalTravelCost) {
		this.totalTravelCost = totalTravelCost;
	}
	public ArrayList getHotelList() {
		return hotelList;
	}
	public void setHotelList(ArrayList hotelList) {
		this.hotelList = hotelList;
	}
	public String getSchSaveFlag() {
		return schSaveFlag;
	}
	public void setSchSaveFlag(String schSaveFlag) {
		this.schSaveFlag = schSaveFlag;
	}
	public String getExtraHotelBill() {
		return extraHotelBill;
	}
	public void setExtraHotelBill(String extraHotelBill) {
		this.extraHotelBill = extraHotelBill;
	}
	public String getHotelComment() {
		return hotelComment;
	}
	public void setHotelComment(String hotelComment) {
		this.hotelComment = hotelComment;
	}
	public String getHidStatus() {
		return hidStatus;
	}
	public void setHidStatus(String hidStatus) {
		this.hidStatus = hidStatus;
	}
	public String getScheduleFinalFlag() {
		return scheduleFinalFlag;
	}
	public void setScheduleFinalFlag(String scheduleFinalFlag) {
		this.scheduleFinalFlag = scheduleFinalFlag;
	}
	public String getJourneyDtlId() {
		return journeyDtlId;
	}
	public void setJourneyDtlId(String journeyDtlId) {
		this.journeyDtlId = journeyDtlId;
	}
	public String getCancelJourneyAmt() {
		return cancelJourneyAmt;
	}
	public void setCancelJourneyAmt(String cancelJourneyAmt) {
		this.cancelJourneyAmt = cancelJourneyAmt;
	}
	public String getHotelDtlId() {
		return hotelDtlId;
	}
	public void setHotelDtlId(String hotelDtlId) {
		this.hotelDtlId = hotelDtlId;
	}
	public String getHotelCancelAmt() {
		return hotelCancelAmt;
	}
	public void setHotelCancelAmt(String hotelCancelAmt) {
		this.hotelCancelAmt = hotelCancelAmt;
	}
	public String getTotalJCancel() {
		return totalJCancel;
	}
	public void setTotalJCancel(String totalJCancel) {
		this.totalJCancel = totalJCancel;
	}
	public String getHotelTotalCanceled() {
		return hotelTotalCanceled;
	}
	public void setHotelTotalCanceled(String hotelTotalCanceled) {
		this.hotelTotalCanceled = hotelTotalCanceled;
	}
	public String getTotalCanceled() {
		return totalCanceled;
	}
	public void setTotalCanceled(String totalCanceled) {
		this.totalCanceled = totalCanceled;
	}
	public String getTrHidSchStatus() {
		return trHidSchStatus;
	}
	public void setTrHidSchStatus(String trHidSchStatus) {
		this.trHidSchStatus = trHidSchStatus;
	}
	public String getJourneyModeRefNumber() {
		return journeyModeRefNumber;
	}
	public void setJourneyModeRefNumber(String journeyModeRefNumber) {
		this.journeyModeRefNumber = journeyModeRefNumber;
	}
	public String getItJourneyModeRefNumber() {
		return itJourneyModeRefNumber;
	}
	public void setItJourneyModeRefNumber(String itJourneyModeRefNumber) {
		this.itJourneyModeRefNumber = itJourneyModeRefNumber;
	}
	public String getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public String getConfStatus() {
		return confStatus;
	}
	public void setConfStatus(String confStatus) {
		this.confStatus = confStatus;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getExpenseFlag() {
		return expenseFlag;
	}
	public void setExpenseFlag(String expenseFlag) {
		this.expenseFlag = expenseFlag;
	}
	public String getGradeJourneyId() {
		return gradeJourneyId;
	}
	public void setGradeJourneyId(String gradeJourneyId) {
		this.gradeJourneyId = gradeJourneyId;
	}
	public String getGradeClassId() {
		return gradeClassId;
	}
	public void setGradeClassId(String gradeClassId) {
		this.gradeClassId = gradeClassId;
	}
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public ArrayList getModelist() {
		return modelist;
	}
	public void setModelist(ArrayList modelist) {
		this.modelist = modelist;
	}
	public String getTravelOtherCost() {
		return travelOtherCost;
	}
	public void setTravelOtherCost(String travelOtherCost) {
		this.travelOtherCost = travelOtherCost;
	}
	public String getHotelPerDayCost() {
		return hotelPerDayCost;
	}
	public void setHotelPerDayCost(String hotelPerDayCost) {
		this.hotelPerDayCost = hotelPerDayCost;
	}
	public String getHotelOtherCost() {
		return hotelOtherCost;
	}
	public void setHotelOtherCost(String hotelOtherCost) {
		this.hotelOtherCost = hotelOtherCost;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	} 
	public String getAppModeName() {
		return appModeName;
	}
	public void setAppModeName(String appModeName) {
		this.appModeName = appModeName;
	}
	public String getAppClassName() {
		return appClassName;
	}
	public void setAppClassName(String appClassName) {
		this.appClassName = appClassName;
	}
	public ArrayList getAppModeList() {
		return appModeList;
	}
	public void setAppModeList(ArrayList appModeList) {
		this.appModeList = appModeList;
	}
	public String getAppSerial() {
		return appSerial;
	}
	public void setAppSerial(String appSerial) {
		this.appSerial = appSerial;
	}
	public String getAppDispFlag() {
		return appDispFlag;
	}
	public void setAppDispFlag(String appDispFlag) {
		this.appDispFlag = appDispFlag;
	}
	public String getAlertSchMode() {
		return alertSchMode;
	}
	public void setAlertSchMode(String alertSchMode) {
		this.alertSchMode = alertSchMode;
	}
	public String getAlertSchFlag() {
		return alertSchFlag;
	}
	public void setAlertSchFlag(String alertSchFlag) {
		this.alertSchFlag = alertSchFlag;
	}
	public String getAlertTrOtherCost() {
		return alertTrOtherCost;
	}
	public void setAlertTrOtherCost(String alertTrOtherCost) {
		this.alertTrOtherCost = alertTrOtherCost;
	}
	public String getAlertHotelOtherCost() {
		return alertHotelOtherCost;
	}
	public void setAlertHotelOtherCost(String alertHotelOtherCost) {
		this.alertHotelOtherCost = alertHotelOtherCost;
	}
	public String getAlertHotelBillCost() {
		return alertHotelBillCost;
	}
	public void setAlertHotelBillCost(String alertHotelBillCost) {
		this.alertHotelBillCost = alertHotelBillCost;
	}
	public String getTrOtherExp() {
		return trOtherExp;
	}
	public void setTrOtherExp(String trOtherExp) {
		this.trOtherExp = trOtherExp;
	}
	public String getLodgOtherAllow() {
		return lodgOtherAllow;
	}
	public void setLodgOtherAllow(String lodgOtherAllow) {
		this.lodgOtherAllow = lodgOtherAllow;
	}
	public String getLodgAllowPerDay() {
		return lodgAllowPerDay;
	}
	public void setLodgAllowPerDay(String lodgAllowPerDay) {
		this.lodgAllowPerDay = lodgAllowPerDay;
	}
	public String getOutPocketAllow() {
		return outPocketAllow;
	}
	public void setOutPocketAllow(String outPocketAllow) {
		this.outPocketAllow = outPocketAllow;
	}
	public String getFoodAllow() {
		return foodAllow;
	}
	public void setFoodAllow(String foodAllow) {
		this.foodAllow = foodAllow;
	}
	public String getCssClassTYpe() {
		return cssClassTYpe;
	}
	public void setCssClassTYpe(String cssClassTYpe) {
		this.cssClassTYpe = cssClassTYpe;
	}
	 
 
  
}
