/**
 * 
 */
package org.paradyne.bean.TravelManagement.TravelPlan;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0417
 *
 */
public class TravelApplication extends BeanBase {
	
 
	private String empToken ="";
	private String empName ="";
	private String branchName ="";
	private String department ="";
	private String designation="";
	private String empId="";
	private String empGrade="";
	private String empGradeId="";
	private String empDob="";
	private String empAge="";
	private String contactNumber="";    
	private String appStatus="";
	private String hidStatus=""; 
	private String disAppStatus="";  
	private String appDate="";
	private String  autoComp="";
	ArrayList cityList = new ArrayList();
	private String cityName="";
	private String  cityId=""; 
	private String hidAcc="checked";
	private String trAppId;
	private String foodType="";
	private String foodTypeId=""; 
	private String policyId=""; 
	private String travelPolicyViewFlag="true"; 
	private String trackFlag="false"; 
	
	// tour details 
	private String appFor ="";
	private String guestName ="";
	private String accommodationReq ="";
	private String trArrg ="";
	private String localConReq="";
	private String tourLocType=""; 
	private String  tourLocTypeId="";
	private String tourStartDate ="";
	private String tourEndDate=""; 
	private String travelRequest="";
	private String travelPurposeId="";
	private String travelPurpose="";
	
	private String level="";
	private String levelApp="";
	private String approver="";
	private String alterApprover="";
	   
	// advance details     
	private String advanceAmt ="";
	private String payMode ="";
	private String settleDate ="";
	
	// identy details          
	private String idProof ="";
	private String idProofNumber ="";
	private String appComments ="";
	
	
	// for travel Mode
	private String jourFromPlace ="";
	private String jourToPlace ="";
	private String trModeClass ="";
	private String jourDate ="";
	private String jourTime =""; 
	private String  trSrNo="";
	private String  trNoAddRow="false";
	private String  settleNumOfDay="";
	private String  settleFlag ="";
	ArrayList trModeClassList = new ArrayList();
	LinkedHashMap modeMap = new LinkedHashMap();
	
	// for local Convence
	private String localCity ="";
	private String localSource ="";
	private String localFromDate ="";
	private String localFromTime ="";
	private String localToDate =""; 
	private String localToTime ="";
	private String  localNoAddRow="false";    
	ArrayList localConList = new ArrayList();
	
	// for lodging  
	private String hotelType ="";             
	private String lodgCity ="";
	private String roomType ="";
	private String lodgLocation ="";
	private String lodgFromdate ="";
	private String lodgFromtime =""; 
	private String lodgTodate ="";
	private String lodgTotime ="";
	private String lodgNoAddRow="false";    
	
	ArrayList lodgList = new ArrayList();
	LinkedHashMap hotelMap = new LinkedHashMap();
	LinkedHashMap roomMap = new LinkedHashMap();
	private String rowId ="";
	
	//========= for travel policy==========
	//for Expense
	private String exCategory=""; 
	private String expCatUnit=""; 
	private String expValue=""; 
	private ArrayList expList=null;
	
	//========= FOR TRAVEL==========
	private String travelMode=""; 
	private String travelClass=""; 
	private ArrayList travelModeList=null;   
	private String travelModeLength=""; 
	
	//for Hotel type
	private String hotelTypePol=""; 
	private String roomTypePol="";  
	private ArrayList hotelTypeList=null; 
	private String guidLines="";  
	
	
	private String delteArrFlag="false";   
	
	// flag for radio button
	private String guestAppFlag="false";   
	private String accomFlag="false"; 
	private String trArFlag="false"; 
	private String localFlag="false"; 
	private String  hidAppForRadio=""; 
	private String  hidAccommodationReqFlag="";
	private String  hidtrArrgFlag="";
	private String  hidLocalConReq=""; 
	
	 
	//for flag 
	private String loadFlag="true";
	private String addNewFlag="false";
	private String editFlag="false";
	private String saveFlag="false";
	private String hidFlag="false"; 
	
	//for tarck Change 
	private String trackTrModeFlag="false";
	private String trackLocalFlag="false";
	private String trackLodgFlag="false"; 
	private String disPayMode="";
	private String disIdProof="";
	
	// for travel policy check
	private String validTrMode="";
	private String validHotelType="";
	private String validRoomType="";
	
	
	// for Approver Comments
	private String apprEmpName="";
	private String apprEmpToken="";
	private String apprDate="";
	private String apprComments="";
	private String commentFlag="false";
	private String approverPanelFlag="false";
	
	ArrayList apprList = new ArrayList();
	
	private String itEmpId="";
	private String itEmpName="";
	private String itRequestName="";
	private String itAppDate="";
	private String itStatus=""; 
	private String itHidStatus="";
	private String itTrAppId="";
	private String fromTotRec="";
	private String toTotRec="";
	private String hdPage=""; 
	ArrayList statusList = new ArrayList();
	private String travelPolInvalidFlag="false";
	private String noData=""; 
	private String serialNo=""; 
	
	
	// for Approver Comments
	
	 private String reqNew="";
	 private String pen="";
	 private String app="";
	 private String rej ="";
	 private String apprflag=""; 
	 private String trAppCanStatus="true";  
	 private String reqFlag="true";  
	 private String buttonFlag="";  
	 
	 
	public String getReqFlag() {
		return reqFlag;
	}
	public void setReqFlag(String reqFlag) {
		this.reqFlag = reqFlag;
	}
	public String getTrAppCanStatus() {
		return trAppCanStatus;
	}
	public void setTrAppCanStatus(String trAppCanStatus) {
		this.trAppCanStatus = trAppCanStatus;
	}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
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
	public String getItEmpId() {
		return itEmpId;
	}
	public void setItEmpId(String itEmpId) {
		this.itEmpId = itEmpId;
	}
	public String getItEmpName() {
		return itEmpName;
	}
	public void setItEmpName(String itEmpName) {
		this.itEmpName = itEmpName;
	}
	public String getItRequestName() {
		return itRequestName;
	}
	public void setItRequestName(String itRequestName) {
		this.itRequestName = itRequestName;
	}
	public String getItAppDate() {
		return itAppDate;
	}
	public void setItAppDate(String itAppDate) {
		this.itAppDate = itAppDate;
	}
	public String getItStatus() {
		return itStatus;
	}
	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}
	public String getItHidStatus() {
		return itHidStatus;
	}
	public void setItHidStatus(String itHidStatus) {
		this.itHidStatus = itHidStatus;
	}
	public String getItTrAppId() {
		return itTrAppId;
	}
	public void setItTrAppId(String itTrAppId) {
		this.itTrAppId = itTrAppId;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public ArrayList getApprList() {
		return apprList;
	}
	public void setApprList(ArrayList apprList) {
		this.apprList = apprList;
	}
	public String getApprEmpName() {
		return apprEmpName;
	}
	public void setApprEmpName(String apprEmpName) {
		this.apprEmpName = apprEmpName;
	}
	public String getApprEmpToken() {
		return apprEmpToken;
	}
	public void setApprEmpToken(String apprEmpToken) {
		this.apprEmpToken = apprEmpToken;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprComments() {
		return apprComments;
	}
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}
	public String getDisPayMode() {
		return disPayMode;
	}
	public void setDisPayMode(String disPayMode) {
		this.disPayMode = disPayMode;
	}
	public String getDelteArrFlag() {
		return delteArrFlag;
	}
	public void setDelteArrFlag(String delteArrFlag) {
		this.delteArrFlag = delteArrFlag;
	}
	public LinkedHashMap getModeMap() {
		return modeMap;
	}
	public void setModeMap(LinkedHashMap modeMap) {
		this.modeMap = modeMap;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public void setCityList(ArrayList cityList) {
		this.cityList = cityList;
	}
	public String getAutoComp() {
		return autoComp;
	}
	public void setAutoComp(String autoComp) {
		this.autoComp = autoComp;
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpGrade() {
		return empGrade;
	}
	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}
	public String getEmpGradeId() {
		return empGradeId;
	}
	public void setEmpGradeId(String empGradeId) {
		this.empGradeId = empGradeId;
	}
	public String getEmpDob() {
		return empDob;
	}
	public void setEmpDob(String empDob) {
		this.empDob = empDob;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public ArrayList getCityList() {
		return cityList;
	}
	public String getJourFromPlace() {
		return jourFromPlace;
	}
	public void setJourFromPlace(String jourFromPlace) {
		this.jourFromPlace = jourFromPlace;
	}
	public String getJourToPlace() {
		return jourToPlace;
	}
	public void setJourToPlace(String jourToPlace) {
		this.jourToPlace = jourToPlace;
	}
	public String getTrModeClass() {
		return trModeClass;
	}
	public void setTrModeClass(String trModeClass) {
		this.trModeClass = trModeClass;
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
	public ArrayList getTrModeClassList() {
		return trModeClassList;
	}
	public void setTrModeClassList(ArrayList trModeClassList) {
		this.trModeClassList = trModeClassList;
	}
	public String getTrSrNo() {
		return trSrNo;
	}
	public void setTrSrNo(String trSrNo) {
		this.trSrNo = trSrNo;
	}
	public String getTrNoAddRow() {
		return trNoAddRow;
	}
	public void setTrNoAddRow(String trNoAddRow) {
		this.trNoAddRow = trNoAddRow;
	}
	public String getLocalCity() {
		return localCity;
	}
	public void setLocalCity(String localCity) {
		this.localCity = localCity;
	}
	public String getLocalSource() {
		return localSource;
	}
	public void setLocalSource(String localSource) {
		this.localSource = localSource;
	}
	public String getLocalFromDate() {
		return localFromDate;
	}
	public void setLocalFromDate(String localFromDate) {
		this.localFromDate = localFromDate;
	}
	public String getLocalFromTime() {
		return localFromTime;
	}
	public void setLocalFromTime(String localFromTime) {
		this.localFromTime = localFromTime;
	}
	public String getLocalToDate() {
		return localToDate;
	}
	public void setLocalToDate(String localToDate) {
		this.localToDate = localToDate;
	}
	public String getLocalToTime() {
		return localToTime;
	}
	public void setLocalToTime(String localToTime) {
		this.localToTime = localToTime;
	}
	public ArrayList getLocalConList() {
		return localConList;
	}
	public void setLocalConList(ArrayList localConList) {
		this.localConList = localConList;
	}
	public String getLocalNoAddRow() {
		return localNoAddRow;
	}
	public void setLocalNoAddRow(String localNoAddRow) {
		this.localNoAddRow = localNoAddRow;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public String getLodgCity() {
		return lodgCity;
	}
	public void setLodgCity(String lodgCity) {
		this.lodgCity = lodgCity;
	}
	public String getLodgLocation() {
		return lodgLocation;
	}
	public void setLodgLocation(String lodgLocation) {
		this.lodgLocation = lodgLocation;
	}
	public String getLodgFromdate() {
		return lodgFromdate;
	}
	public void setLodgFromdate(String lodgFromdate) {
		this.lodgFromdate = lodgFromdate;
	}
	public String getLodgFromtime() {
		return lodgFromtime;
	}
	public void setLodgFromtime(String lodgFromtime) {
		this.lodgFromtime = lodgFromtime;
	}
	public String getLodgTodate() {
		return lodgTodate;
	}
	public void setLodgTodate(String lodgTodate) {
		this.lodgTodate = lodgTodate;
	}
	public String getLodgTotime() {
		return lodgTotime;
	}
	public void setLodgTotime(String lodgTotime) {
		this.lodgTotime = lodgTotime;
	}
	public ArrayList getLodgList() {
		return lodgList;
	}
	public void setLodgList(ArrayList lodgList) {
		this.lodgList = lodgList;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public LinkedHashMap getHotelMap() {
		return hotelMap;
	}
	public void setHotelMap(LinkedHashMap hotelMap) {
		this.hotelMap = hotelMap;
	}
	public LinkedHashMap getRoomMap() {
		return roomMap;
	}
	public void setRoomMap(LinkedHashMap roomMap) {
		this.roomMap = roomMap;
	}
	public String getLodgNoAddRow() {
		return lodgNoAddRow;
	}
	public void setLodgNoAddRow(String lodgNoAddRow) {
		this.lodgNoAddRow = lodgNoAddRow;
	}
	public String getAppFor() {
		return appFor;
	}
	public void setAppFor(String appFor) {
		this.appFor = appFor;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getAccommodationReq() {
		return accommodationReq;
	}
	public void setAccommodationReq(String accommodationReq) {
		this.accommodationReq = accommodationReq;
	}
	public String getTrArrg() {
		return trArrg;
	}
	public void setTrArrg(String trArrg) {
		this.trArrg = trArrg;
	}
	public String getLocalConReq() {
		return localConReq;
	}
	public void setLocalConReq(String localConReq) {
		this.localConReq = localConReq;
	}
	public String getTourLocType() {
		return tourLocType;
	}
	public void setTourLocType(String tourLocType) {
		this.tourLocType = tourLocType;
	}
	public String getTourStartDate() {
		return tourStartDate;
	}
	public void setTourStartDate(String tourStartDate) {
		this.tourStartDate = tourStartDate;
	}
	public String getTourEndDate() {
		return tourEndDate;
	}
	public void setTourEndDate(String tourEndDate) {
		this.tourEndDate = tourEndDate;
	}
	public String getAdvanceAmt() {
		return advanceAmt;
	}
	public void setAdvanceAmt(String advanceAmt) {
		this.advanceAmt = advanceAmt;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public String getIdProofNumber() {
		return idProofNumber;
	}
	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}
	public String getAppComments() {
		return appComments;
	}
	public void setAppComments(String appComments) {
		this.appComments = appComments;
	}
	public String getHidAcc() {
		return hidAcc;
	}
	public void setHidAcc(String hidAcc) {
		this.hidAcc = hidAcc;
	}
	public String getTravelRequest() {
		return travelRequest;
	}
	public void setTravelRequest(String travelRequest) {
		this.travelRequest = travelRequest;
	}
	public String getTravelPurposeId() {
		return travelPurposeId;
	}
	public void setTravelPurposeId(String travelPurposeId) {
		this.travelPurposeId = travelPurposeId;
	}
	public String getTravelPurpose() {
		return travelPurpose;
	}
	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}
	public String getTourLocTypeId() {
		return tourLocTypeId;
	}
	public void setTourLocTypeId(String tourLocTypeId) {
		this.tourLocTypeId = tourLocTypeId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getAlterApprover() {
		return alterApprover;
	}
	public void setAlterApprover(String alterApprover) {
		this.alterApprover = alterApprover;
	}
	public String getTrAppId() {
		return trAppId;
	}
	public void setTrAppId(String trAppId) {
		this.trAppId = trAppId;
	}
	public String getExCategory() {
		return exCategory;
	}
	public void setExCategory(String exCategory) {
		this.exCategory = exCategory;
	}
	public String getExpCatUnit() {
		return expCatUnit;
	}
	public void setExpCatUnit(String expCatUnit) {
		this.expCatUnit = expCatUnit;
	}
	public String getExpValue() {
		return expValue;
	}
	public void setExpValue(String expValue) {
		this.expValue = expValue;
	}
	public ArrayList getExpList() {
		return expList;
	}
	public void setExpList(ArrayList expList) {
		this.expList = expList;
	}
	public String getTravelMode() {
		return travelMode;
	}
	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}
	public String getTravelClass() {
		return travelClass;
	}
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	public ArrayList getTravelModeList() {
		return travelModeList;
	}
	public void setTravelModeList(ArrayList travelModeList) {
		this.travelModeList = travelModeList;
	}
	public String getHotelTypePol() {
		return hotelTypePol;
	}
	public void setHotelTypePol(String hotelTypePol) {
		this.hotelTypePol = hotelTypePol;
	}
	public String getRoomTypePol() {
		return roomTypePol;
	}
	public void setRoomTypePol(String roomTypePol) {
		this.roomTypePol = roomTypePol;
	}
	public ArrayList getHotelTypeList() {
		return hotelTypeList;
	}
	public void setHotelTypeList(ArrayList hotelTypeList) {
		this.hotelTypeList = hotelTypeList;
	}
	public String getGuidLines() {
		return guidLines;
	}
	public void setGuidLines(String guidLines) {
		this.guidLines = guidLines;
	}
	public String getGuestAppFlag() {
		return guestAppFlag;
	}
	public void setGuestAppFlag(String guestAppFlag) {
		this.guestAppFlag = guestAppFlag;
	}
 
	public String getAccomFlag() {
		return accomFlag;
	}
	public void setAccomFlag(String accomFlag) {
		this.accomFlag = accomFlag;
	}
	public String getTrArFlag() {
		return trArFlag;
	}
	public void setTrArFlag(String trArFlag) {
		this.trArFlag = trArFlag;
	}
	public String getLocalFlag() {
		return localFlag;
	}
	public void setLocalFlag(String localFlag) {
		this.localFlag = localFlag;
	}
	public String getLoadFlag() {
		return loadFlag;
	}
	public void setLoadFlag(String loadFlag) {
		this.loadFlag = loadFlag;
	}
	public String getAddNewFlag() {
		return addNewFlag;
	}
	public void setAddNewFlag(String addNewFlag) {
		this.addNewFlag = addNewFlag;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getHidFlag() {
		return hidFlag;
	}
	public void setHidFlag(String hidFlag) {
		this.hidFlag = hidFlag;
	}
	public String getHidAppForRadio() {
		return hidAppForRadio;
	}
	public void setHidAppForRadio(String hidAppForRadio) {
		this.hidAppForRadio = hidAppForRadio;
	}
	public String getHidAccommodationReqFlag() {
		return hidAccommodationReqFlag;
	}
	public void setHidAccommodationReqFlag(String hidAccommodationReqFlag) {
		this.hidAccommodationReqFlag = hidAccommodationReqFlag;
	}
	public String getHidtrArrgFlag() {
		return hidtrArrgFlag;
	}
	public void setHidtrArrgFlag(String hidtrArrgFlag) {
		this.hidtrArrgFlag = hidtrArrgFlag;
	}
	public String getHidLocalConReq() {
		return hidLocalConReq;
	}
	public void setHidLocalConReq(String hidLocalConReq) {
		this.hidLocalConReq = hidLocalConReq;
	}
	public String getHidStatus() {
		return hidStatus;
	}
	public void setHidStatus(String hidStatus) {
		this.hidStatus = hidStatus;
	}
	public String getLevelApp() {
		return levelApp;
	}
	public void setLevelApp(String levelApp) {
		this.levelApp = levelApp;
	}
	public String getTrackTrModeFlag() {
		return trackTrModeFlag;
	}
	public void setTrackTrModeFlag(String trackTrModeFlag) {
		this.trackTrModeFlag = trackTrModeFlag;
	}
	public String getTrackLocalFlag() {
		return trackLocalFlag;
	}
	public void setTrackLocalFlag(String trackLocalFlag) {
		this.trackLocalFlag = trackLocalFlag;
	}
	public String getTrackLodgFlag() {
		return trackLodgFlag;
	}
	public void setTrackLodgFlag(String trackLodgFlag) {
		this.trackLodgFlag = trackLodgFlag;
	}
	public String getDisAppStatus() {
		return disAppStatus;
	}
	public void setDisAppStatus(String disAppStatus) {
		this.disAppStatus = disAppStatus;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getDisIdProof() {
		return disIdProof;
	}
	public void setDisIdProof(String disIdProof) {
		this.disIdProof = disIdProof;
	}
	public String getSettleNumOfDay() {
		return settleNumOfDay;
	}
	public void setSettleNumOfDay(String settleNumOfDay) {
		this.settleNumOfDay = settleNumOfDay;
	}
	public String getSettleFlag() {
		return settleFlag;
	}
	public void setSettleFlag(String settleFlag) {
		this.settleFlag = settleFlag;
	}
	public String getValidTrMode() {
		return validTrMode;
	}
	public void setValidTrMode(String validTrMode) {
		this.validTrMode = validTrMode;
	}
	public String getValidHotelType() {
		return validHotelType;
	}
	public void setValidHotelType(String validHotelType) {
		this.validHotelType = validHotelType;
	}
	public String getValidRoomType() {
		return validRoomType;
	}
	public void setValidRoomType(String validRoomType) {
		this.validRoomType = validRoomType;
	}
	public String getApproverPanelFlag() {
		return approverPanelFlag;
	}
	public void setApproverPanelFlag(String approverPanelFlag) {
		this.approverPanelFlag = approverPanelFlag;
	}
	public ArrayList getStatusList() {
		return statusList;
	}
	public void setStatusList(ArrayList statusList) {
		this.statusList = statusList;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getRej() {
		return rej;
	}
	public void setRej(String rej) {
		this.rej = rej;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(String foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public String getReqNew() {
		return reqNew;
	}
	public void setReqNew(String reqNew) {
		this.reqNew = reqNew;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getTravelPolInvalidFlag() {
		return travelPolInvalidFlag;
	}
	public void setTravelPolInvalidFlag(String travelPolInvalidFlag) {
		this.travelPolInvalidFlag = travelPolInvalidFlag;
	}
	public String getTravelModeLength() {
		return travelModeLength;
	}
	public void setTravelModeLength(String travelModeLength) {
		this.travelModeLength = travelModeLength;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getTravelPolicyViewFlag() {
		return travelPolicyViewFlag;
	}
	public void setTravelPolicyViewFlag(String travelPolicyViewFlag) {
		this.travelPolicyViewFlag = travelPolicyViewFlag;
	}
	public String getTrackFlag() {
		return trackFlag;
	}
	public void setTrackFlag(String trackFlag) {
		this.trackFlag = trackFlag;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getButtonFlag() {
		return buttonFlag;
	}
	public void setButtonFlag(String buttonFlag) {
		this.buttonFlag = buttonFlag;
	} 

}
