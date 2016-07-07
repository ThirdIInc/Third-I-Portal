package org.paradyne.bean.TravelManagement.TravelProcess;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain
 * 
 */
public class TravelMonitoring extends BeanBase {

	private String buttonLabel = "";
	private String buttonAction = "";
	private String initiator = "";
	private String applicant = "";
	private String journeyDate = "";
	private String applicationDate = "";
	private String appToken;
	private int applicationId;
	private int empApplId;
	private int iniEmpId;
	private String empId;
	private int srNo;
	private String travelReqId;
	private String tvlStatus;
	private String accStatus;
	private String locStatus;
	private String userType;
	private ArrayList main;
	private ArrayList options;
	private ArrayList path;
	private String source;
	private String dest;
	private String travelDate;
	private String travelTime;
	private String travelModeClass;
	private String busTrnFlgNo;
	private String ticketNumber;
	private String cost;
	private String monitorId;
	private String trvlDtlId;
	private String selFlag;
	private String jourCode;
	private String fromCity;
	private String toCity;
	private String policyId;
	private String gradeName;
	private String policyName;
	private String aplComments;
	private String aprComments;
	private String schComments;
	private boolean readOnlyFlag = true;
	private LinkedMap modeClassMap;
	private String tourStartDate;
	private String lodgeCode;
	private String lodgeDtlId;
	private String accomFromDate;
	private String accomToDate;
	private String accomFromTime;
	private String accomToTime;
	private String hotelTypeCode;
	private String hotelTypeName;
	private String roomTypeCode;
	private String roomTypeName;
	private String accomAddress;
	private String accomBookingAmt;
	private String cityCode;
	private String gradeId;
	private String tmsTrvlId;
	private String tmsTrvlIndiId;
	private String tmsChkTypeFlg;
	private String deskFlag = "";
	private String viewFlag = "false";
	private String violateFlag = "N";
	private String rejFlag = "N";
	private String remarks;
	private String rowId;
	private File myFile;
	private String contentType;

	// Start booking

	// FOR VIEW JSP
	private boolean selfTrvlFlag = false;
	private boolean guestTrvlFlag = false;

	// FOR EMPLOYEE iNFORMATION

	private String empToken = "";
	private String empName = "";
	private String brnchName = "";
	private String deptName = "";
	private String desgn = "";
	private String applDate = "";
	private String statusView = "";
	private String grade = "";
	private String age = "";
	private String contactNo = "";

	// For guest Information

	private String empTokenG = "";
	private String empNameG = "";
	private String guestName = "";
	private String applDateG = "";
	private String statusViewG = "";
	private String ageG = "";
	private String contactNoG = "";

	// for Tour Details
	private boolean tourFlag = true;
	private String trvlAppFor = "";
	private String trvlReqName = "";
	private String trvlPurpose = "";
	private String trvlAccom = "";
	private String trvlArrngmt = "";
	private String trvlLocCon = "";
	private String trvlType = "";
	private String tourStrtDate = "";
	private String tourEndDate = "";
	private String travelId = "";

	// for journey details
	private boolean jourDtlFlag = false;
	ArrayList travelJourDtl = new ArrayList();
	private String JournDtlId = "";
	private String jourFrm = "";
	private String jourTo = "";
	private String JourModeCls = "";
	private String jourDate = "";
	private String jourTime = "";
	private String jourNo = "";
	private String ticketNo = "";
	private String jourCost = "";
	private String jourClsId = "";

	// for local Conveyance details
	private boolean locConDtlFlag = false;
	ArrayList travelLocConDtl = new ArrayList();
	private String locConId = "";
	private String locConDtlId = "";
	private String locConCity = "";
	private String locConSource = "";
	private String locConDate = "";
	private String locConMode = "";
	private String locConFrmTime = "";
	private String locConToTime = "";
	private String locConCost = "";

	// for lodge details
	private boolean lodgDtlFlag = false;
	ArrayList travelLodgDtl = new ArrayList();
	private String lodgDtlId = "";
	private String lodgId = "";
	private String lodgHotel = "";
	private String lodgRoom = "";
	private String lodgCity = "";
	private String lodgPreLoc = "";
	private String lodgFrmDate = "";
	private String lodgFrmTime = "";
	private String lodgToDate = "";
	private String lodgToTime = "";
	private String lodgBookAmt = "";

	// for advance details
	private String trvlAdvAmt = "";
	private String trvlPrefPayMode = "";
	private String trvlExpSettleDate = "";

	// for identification details
	private String idProof = "";
	private String idNumber = "";
	private String idCmts = "";

	// End of Start booking

	private String locConvCode = "";
	private String locDtlId = "";
	private String locConVehNo = "";
	private String locConConPer = "";
	private String locConConNo = "";
	private String locConPicPer = "";
	private String locConPicPlace = "";
	private String locConToriffCost = "";
	private String locConStatus = "";
	private String currentPage = "";
	private String jourDetails = "";
	private String accDetails = "";
	private String locDetails = "";
	private String savedFlag = "false";
	private String onHoldFlag = "false";
	private String jourCancelFlag = "";
	private String lodgCancelFlag = "";
	private String locCancelFlag = "";

	// Travel Application - cancellation details screen
	private String cancelComm = "";

	private String cancelChargeTvl;
	private String refundAmtTvl;
	private String commentsTvl;
	private String cancelChargeAcc;
	private String refundAmtAcc;
	private String commentsAcc;
	private String cancelChargeLoc;
	private String refundAmtLoc;
	private String commentsLoc;
	private String uploadFileTvl;
	private String uploadFileAcc;
	private String uploadFileLoc;

	private ArrayList<Object> monitorList = new ArrayList<Object>();

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getIniEmpId() {
		return iniEmpId;
	}

	public void setIniEmpId(int iniEmpId) {
		this.iniEmpId = iniEmpId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getButtonLabel() {
		return buttonLabel;
	}

	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}

	public String getButtonAction() {
		return buttonAction;
	}

	public void setButtonAction(String buttonAction) {
		this.buttonAction = buttonAction;
	}

	public int getEmpApplId() {
		return empApplId;
	}

	public void setEmpApplId(int empApplId) {
		this.empApplId = empApplId;
	}

	public String getTvlStatus() {
		return tvlStatus;
	}

	public void setTvlStatus(String tvlStatus) {
		this.tvlStatus = tvlStatus;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public String getLocStatus() {
		return locStatus;
	}

	public void setLocStatus(String locStatus) {
		this.locStatus = locStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public ArrayList getMain() {
		return main;
	}

	public void setMain(ArrayList main) {
		this.main = main;
	}

	public ArrayList getOptions() {
		return options;
	}

	public void setOptions(ArrayList options) {
		this.options = options;
	}

	public ArrayList getPath() {
		return path;
	}

	public void setPath(ArrayList path) {
		this.path = path;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public String getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	public String getTravelModeClass() {
		return travelModeClass;
	}

	public void setTravelModeClass(String travelModeClass) {
		this.travelModeClass = travelModeClass;
	}

	public String getBusTrnFlgNo() {
		return busTrnFlgNo;
	}

	public void setBusTrnFlgNo(String busTrnFlgNo) {
		this.busTrnFlgNo = busTrnFlgNo;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public String getTrvlDtlId() {
		return trvlDtlId;
	}

	public void setTrvlDtlId(String trvlDtlId) {
		this.trvlDtlId = trvlDtlId;
	}

	public String getJourCode() {
		return jourCode;
	}

	public void setJourCode(String jourCode) {
		this.jourCode = jourCode;
	}

	public String getSelFlag() {
		return selFlag;
	}

	public void setSelFlag(String selFlag) {
		this.selFlag = selFlag;
	}

	public String getAplComments() {
		return aplComments;
	}

	public void setAplComments(String aplComments) {
		this.aplComments = aplComments;
	}

	public String getSchComments() {
		return schComments;
	}

	public void setSchComments(String schComments) {
		this.schComments = schComments;
	}

	public boolean isReadOnlyFlag() {
		return readOnlyFlag;
	}

	public void setReadOnlyFlag(boolean readOnlyFlag) {
		this.readOnlyFlag = readOnlyFlag;
	}

	public LinkedMap getModeClassMap() {
		return modeClassMap;
	}

	public void setModeClassMap(LinkedMap modeClassMap) {
		this.modeClassMap = modeClassMap;
	}

	public String getTourStartDate() {
		return tourStartDate;
	}

	public void setTourStartDate(String tourStartDate) {
		this.tourStartDate = tourStartDate;
	}

	public String getLodgeCode() {
		return lodgeCode;
	}

	public void setLodgeCode(String lodgeCode) {
		this.lodgeCode = lodgeCode;
	}

	public String getLodgeDtlId() {
		return lodgeDtlId;
	}

	public void setLodgeDtlId(String lodgeDtlId) {
		this.lodgeDtlId = lodgeDtlId;
	}

	public String getAccomFromDate() {
		return accomFromDate;
	}

	public void setAccomFromDate(String accomFromDate) {
		this.accomFromDate = accomFromDate;
	}

	public String getAccomToDate() {
		return accomToDate;
	}

	public void setAccomToDate(String accomToDate) {
		this.accomToDate = accomToDate;
	}

	public String getAccomFromTime() {
		return accomFromTime;
	}

	public void setAccomFromTime(String accomFromTime) {
		this.accomFromTime = accomFromTime;
	}

	public String getAccomToTime() {
		return accomToTime;
	}

	public void setAccomToTime(String accomToTime) {
		this.accomToTime = accomToTime;
	}

	public String getHotelTypeCode() {
		return hotelTypeCode;
	}

	public void setHotelTypeCode(String hotelTypeCode) {
		this.hotelTypeCode = hotelTypeCode;
	}

	public String getHotelTypeName() {
		return hotelTypeName;
	}

	public void setHotelTypeName(String hotelTypeName) {
		this.hotelTypeName = hotelTypeName;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getAccomAddress() {
		return accomAddress;
	}

	public void setAccomAddress(String accomAddress) {
		this.accomAddress = accomAddress;
	}

	public String getAccomBookingAmt() {
		return accomBookingAmt;
	}

	public void setAccomBookingAmt(String accomBookingAmt) {
		this.accomBookingAmt = accomBookingAmt;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public ArrayList<Object> getMonitorList() {
		return monitorList;
	}

	public void setMonitorList(ArrayList<Object> monitorList) {
		this.monitorList = monitorList;
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

	public String getBrnchName() {
		return brnchName;
	}

	public void setBrnchName(String brnchName) {
		this.brnchName = brnchName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesgn() {
		return desgn;
	}

	public void setDesgn(String desgn) {
		this.desgn = desgn;
	}

	public String getApplDate() {
		return applDate;
	}

	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	public String getStatusView() {
		return statusView;
	}

	public void setStatusView(String statusView) {
		this.statusView = statusView;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public boolean isTourFlag() {
		return tourFlag;
	}

	public void setTourFlag(boolean tourFlag) {
		this.tourFlag = tourFlag;
	}

	public String getTrvlAppFor() {
		return trvlAppFor;
	}

	public void setTrvlAppFor(String trvlAppFor) {
		this.trvlAppFor = trvlAppFor;
	}

	public String getTrvlReqName() {
		return trvlReqName;
	}

	public void setTrvlReqName(String trvlReqName) {
		this.trvlReqName = trvlReqName;
	}

	public String getTrvlPurpose() {
		return trvlPurpose;
	}

	public void setTrvlPurpose(String trvlPurpose) {
		this.trvlPurpose = trvlPurpose;
	}

	public String getTrvlAccom() {
		return trvlAccom;
	}

	public void setTrvlAccom(String trvlAccom) {
		this.trvlAccom = trvlAccom;
	}

	public String getTrvlArrngmt() {
		return trvlArrngmt;
	}

	public void setTrvlArrngmt(String trvlArrngmt) {
		this.trvlArrngmt = trvlArrngmt;
	}

	public String getTrvlLocCon() {
		return trvlLocCon;
	}

	public void setTrvlLocCon(String trvlLocCon) {
		this.trvlLocCon = trvlLocCon;
	}

	public String getTrvlType() {
		return trvlType;
	}

	public void setTrvlType(String trvlType) {
		this.trvlType = trvlType;
	}

	public String getTourStrtDate() {
		return tourStrtDate;
	}

	public void setTourStrtDate(String tourStrtDate) {
		this.tourStrtDate = tourStrtDate;
	}

	public String getTourEndDate() {
		return tourEndDate;
	}

	public void setTourEndDate(String tourEndDate) {
		this.tourEndDate = tourEndDate;
	}

	public boolean isJourDtlFlag() {
		return jourDtlFlag;
	}

	public void setJourDtlFlag(boolean jourDtlFlag) {
		this.jourDtlFlag = jourDtlFlag;
	}

	public ArrayList getTravelJourDtl() {
		return travelJourDtl;
	}

	public void setTravelJourDtl(ArrayList travelJourDtl) {
		this.travelJourDtl = travelJourDtl;
	}

	public String getJournDtlId() {
		return JournDtlId;
	}

	public void setJournDtlId(String journDtlId) {
		JournDtlId = journDtlId;
	}

	public String getJourFrm() {
		return jourFrm;
	}

	public void setJourFrm(String jourFrm) {
		this.jourFrm = jourFrm;
	}

	public String getJourTo() {
		return jourTo;
	}

	public void setJourTo(String jourTo) {
		this.jourTo = jourTo;
	}

	public String getJourModeCls() {
		return JourModeCls;
	}

	public void setJourModeCls(String jourModeCls) {
		JourModeCls = jourModeCls;
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

	public String getJourNo() {
		return jourNo;
	}

	public void setJourNo(String jourNo) {
		this.jourNo = jourNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getJourCost() {
		return jourCost;
	}

	public void setJourCost(String jourCost) {
		this.jourCost = jourCost;
	}

	public boolean isLocConDtlFlag() {
		return locConDtlFlag;
	}

	public void setLocConDtlFlag(boolean locConDtlFlag) {
		this.locConDtlFlag = locConDtlFlag;
	}

	public ArrayList getTravelLocConDtl() {
		return travelLocConDtl;
	}

	public void setTravelLocConDtl(ArrayList travelLocConDtl) {
		this.travelLocConDtl = travelLocConDtl;
	}

	public String getLocConId() {
		return locConId;
	}

	public void setLocConId(String locConId) {
		this.locConId = locConId;
	}

	public String getLocConCity() {
		return locConCity;
	}

	public void setLocConCity(String locConCity) {
		this.locConCity = locConCity;
	}

	public String getLocConSource() {
		return locConSource;
	}

	public void setLocConSource(String locConSource) {
		this.locConSource = locConSource;
	}

	public String getLocConFrmTime() {
		return locConFrmTime;
	}

	public void setLocConFrmTime(String locConFrmTime) {
		this.locConFrmTime = locConFrmTime;
	}

	public String getLocConDate() {
		return locConDate;
	}

	public void setLocConDate(String locConDate) {
		this.locConDate = locConDate;
	}

	public String getLocConMode() {
		return locConMode;
	}

	public void setLocConMode(String locConMode) {
		this.locConMode = locConMode;
	}

	public String getLocConToTime() {
		return locConToTime;
	}

	public void setLocConToTime(String locConToTime) {
		this.locConToTime = locConToTime;
	}

	public String getLocConCost() {
		return locConCost;
	}

	public void setLocConCost(String locConCost) {
		this.locConCost = locConCost;
	}

	public boolean isLodgDtlFlag() {
		return lodgDtlFlag;
	}

	public void setLodgDtlFlag(boolean lodgDtlFlag) {
		this.lodgDtlFlag = lodgDtlFlag;
	}

	public ArrayList getTravelLodgDtl() {
		return travelLodgDtl;
	}

	public void setTravelLodgDtl(ArrayList travelLodgDtl) {
		this.travelLodgDtl = travelLodgDtl;
	}

	public String getLodgId() {
		return lodgId;
	}

	public void setLodgId(String lodgId) {
		this.lodgId = lodgId;
	}

	public String getLodgHotel() {
		return lodgHotel;
	}

	public void setLodgHotel(String lodgHotel) {
		this.lodgHotel = lodgHotel;
	}

	public String getLodgRoom() {
		return lodgRoom;
	}

	public void setLodgRoom(String lodgRoom) {
		this.lodgRoom = lodgRoom;
	}

	public String getLodgCity() {
		return lodgCity;
	}

	public void setLodgCity(String lodgCity) {
		this.lodgCity = lodgCity;
	}

	public String getLodgPreLoc() {
		return lodgPreLoc;
	}

	public void setLodgPreLoc(String lodgPreLoc) {
		this.lodgPreLoc = lodgPreLoc;
	}

	public String getLodgFrmDate() {
		return lodgFrmDate;
	}

	public void setLodgFrmDate(String lodgFrmDate) {
		this.lodgFrmDate = lodgFrmDate;
	}

	public String getLodgFrmTime() {
		return lodgFrmTime;
	}

	public void setLodgFrmTime(String lodgFrmTime) {
		this.lodgFrmTime = lodgFrmTime;
	}

	public String getLodgToDate() {
		return lodgToDate;
	}

	public void setLodgToDate(String lodgToDate) {
		this.lodgToDate = lodgToDate;
	}

	public String getLodgToTime() {
		return lodgToTime;
	}

	public void setLodgToTime(String lodgToTime) {
		this.lodgToTime = lodgToTime;
	}

	public String getLodgBookAmt() {
		return lodgBookAmt;
	}

	public void setLodgBookAmt(String lodgBookAmt) {
		this.lodgBookAmt = lodgBookAmt;
	}

	public String getTrvlAdvAmt() {
		return trvlAdvAmt;
	}

	public void setTrvlAdvAmt(String trvlAdvAmt) {
		this.trvlAdvAmt = trvlAdvAmt;
	}

	public String getTrvlPrefPayMode() {
		return trvlPrefPayMode;
	}

	public void setTrvlPrefPayMode(String trvlPrefPayMode) {
		this.trvlPrefPayMode = trvlPrefPayMode;
	}

	public String getTrvlExpSettleDate() {
		return trvlExpSettleDate;
	}

	public void setTrvlExpSettleDate(String trvlExpSettleDate) {
		this.trvlExpSettleDate = trvlExpSettleDate;
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

	public String getIdCmts() {
		return idCmts;
	}

	public void setIdCmts(String idCmts) {
		this.idCmts = idCmts;
	}

	public String getEmpTokenG() {
		return empTokenG;
	}

	public void setEmpTokenG(String empTokenG) {
		this.empTokenG = empTokenG;
	}

	public String getEmpNameG() {
		return empNameG;
	}

	public void setEmpNameG(String empNameG) {
		this.empNameG = empNameG;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getApplDateG() {
		return applDateG;
	}

	public void setApplDateG(String applDateG) {
		this.applDateG = applDateG;
	}

	public String getStatusViewG() {
		return statusViewG;
	}

	public void setStatusViewG(String statusViewG) {
		this.statusViewG = statusViewG;
	}

	public String getAgeG() {
		return ageG;
	}

	public void setAgeG(String ageG) {
		this.ageG = ageG;
	}

	public String getContactNoG() {
		return contactNoG;
	}

	public void setContactNoG(String contactNoG) {
		this.contactNoG = contactNoG;
	}

	public boolean isSelfTrvlFlag() {
		return selfTrvlFlag;
	}

	public void setSelfTrvlFlag(boolean selfTrvlFlag) {
		this.selfTrvlFlag = selfTrvlFlag;
	}

	public boolean isGuestTrvlFlag() {
		return guestTrvlFlag;
	}

	public void setGuestTrvlFlag(boolean guestTrvlFlag) {
		this.guestTrvlFlag = guestTrvlFlag;
	}

	public String getJourClsId() {
		return jourClsId;
	}

	public void setJourClsId(String jourClsId) {
		this.jourClsId = jourClsId;
	}

	public String getLodgDtlId() {
		return lodgDtlId;
	}

	public void setLodgDtlId(String lodgDtlId) {
		this.lodgDtlId = lodgDtlId;
	}

	public String getLocConDtlId() {
		return locConDtlId;
	}

	public void setLocConDtlId(String locConDtlId) {
		this.locConDtlId = locConDtlId;
	}

	public String getLocConvCode() {
		return locConvCode;
	}

	public void setLocConvCode(String locConvCode) {
		this.locConvCode = locConvCode;
	}

	public String getLocDtlId() {
		return locDtlId;
	}

	public void setLocDtlId(String locDtlId) {
		this.locDtlId = locDtlId;
	}

	public String getLocConVehNo() {
		return locConVehNo;
	}

	public void setLocConVehNo(String locConVehNo) {
		this.locConVehNo = locConVehNo;
	}

	public String getLocConConPer() {
		return locConConPer;
	}

	public void setLocConConPer(String locConConPer) {
		this.locConConPer = locConConPer;
	}

	public String getLocConConNo() {
		return locConConNo;
	}

	public void setLocConConNo(String locConConNo) {
		this.locConConNo = locConConNo;
	}

	public String getLocConPicPer() {
		return locConPicPer;
	}

	public void setLocConPicPer(String locConPicPer) {
		this.locConPicPer = locConPicPer;
	}

	public String getLocConPicPlace() {
		return locConPicPlace;
	}

	public void setLocConPicPlace(String locConPicPlace) {
		this.locConPicPlace = locConPicPlace;
	}

	public String getLocConToriffCost() {
		return locConToriffCost;
	}

	public void setLocConToriffCost(String locConToriffCost) {
		this.locConToriffCost = locConToriffCost;
	}

	public String getLocConStatus() {
		return locConStatus;
	}

	public void setLocConStatus(String locConStatus) {
		this.locConStatus = locConStatus;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getTravelReqId() {
		return travelReqId;
	}

	public void setTravelReqId(String travelReqId) {
		this.travelReqId = travelReqId;
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

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	public String getViolateFlag() {
		return violateFlag;
	}

	public void setViolateFlag(String violateFlag) {
		this.violateFlag = violateFlag;
	}

	public String getRejFlag() {
		return rejFlag;
	}

	public void setRejFlag(String rejFlag) {
		this.rejFlag = rejFlag;
	}

	public String getAprComments() {
		return aprComments;
	}

	public void setAprComments(String aprComments) {
		this.aprComments = aprComments;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getJourDetails() {
		return jourDetails;
	}

	public void setJourDetails(String jourDetails) {
		this.jourDetails = jourDetails;
	}

	public String getAccDetails() {
		return accDetails;
	}

	public void setAccDetails(String accDetails) {
		this.accDetails = accDetails;
	}

	public String getLocDetails() {
		return locDetails;
	}

	public void setLocDetails(String locDetails) {
		this.locDetails = locDetails;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getSavedFlag() {
		return savedFlag;
	}

	public void setSavedFlag(String savedFlag) {
		this.savedFlag = savedFlag;
	}

	public String getOnHoldFlag() {
		return onHoldFlag;
	}

	public void setOnHoldFlag(String onHoldFlag) {
		this.onHoldFlag = onHoldFlag;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getJourCancelFlag() {
		return jourCancelFlag;
	}

	public void setJourCancelFlag(String jourCancelFlag) {
		this.jourCancelFlag = jourCancelFlag;
	}

	public String getLodgCancelFlag() {
		return lodgCancelFlag;
	}

	public void setLodgCancelFlag(String lodgCancelFlag) {
		this.lodgCancelFlag = lodgCancelFlag;
	}

	public String getLocCancelFlag() {
		return locCancelFlag;
	}

	public void setLocCancelFlag(String locCancelFlag) {
		this.locCancelFlag = locCancelFlag;
	}

	public String getCancelChargeTvl() {
		return cancelChargeTvl;
	}

	public void setCancelChargeTvl(String cancelChargeTvl) {
		this.cancelChargeTvl = cancelChargeTvl;
	}

	public String getRefundAmtTvl() {
		return refundAmtTvl;
	}

	public void setRefundAmtTvl(String refundAmtTvl) {
		this.refundAmtTvl = refundAmtTvl;
	}

	public String getCommentsTvl() {
		return commentsTvl;
	}

	public void setCommentsTvl(String commentsTvl) {
		this.commentsTvl = commentsTvl;
	}

	public String getCancelChargeAcc() {
		return cancelChargeAcc;
	}

	public void setCancelChargeAcc(String cancelChargeAcc) {
		this.cancelChargeAcc = cancelChargeAcc;
	}

	public String getRefundAmtAcc() {
		return refundAmtAcc;
	}

	public void setRefundAmtAcc(String refundAmtAcc) {
		this.refundAmtAcc = refundAmtAcc;
	}

	public String getCommentsAcc() {
		return commentsAcc;
	}

	public void setCommentsAcc(String commentsAcc) {
		this.commentsAcc = commentsAcc;
	}

	public String getCancelChargeLoc() {
		return cancelChargeLoc;
	}

	public void setCancelChargeLoc(String cancelChargeLoc) {
		this.cancelChargeLoc = cancelChargeLoc;
	}

	public String getRefundAmtLoc() {
		return refundAmtLoc;
	}

	public void setRefundAmtLoc(String refundAmtLoc) {
		this.refundAmtLoc = refundAmtLoc;
	}

	public String getCommentsLoc() {
		return commentsLoc;
	}

	public void setCommentsLoc(String commentsLoc) {
		this.commentsLoc = commentsLoc;
	}

	public String getCancelComm() {
		return cancelComm;
	}

	public void setCancelComm(String cancelComm) {
		this.cancelComm = cancelComm;
	}

	public String getUploadFileTvl() {
		return uploadFileTvl;
	}

	public void setUploadFileTvl(String uploadFileTvl) {
		this.uploadFileTvl = uploadFileTvl;
	}

	public String getUploadFileAcc() {
		return uploadFileAcc;
	}

	public void setUploadFileAcc(String uploadFileAcc) {
		this.uploadFileAcc = uploadFileAcc;
	}

	public String getUploadFileLoc() {
		return uploadFileLoc;
	}

	public void setUploadFileLoc(String uploadFileLoc) {
		this.uploadFileLoc = uploadFileLoc;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}
}
