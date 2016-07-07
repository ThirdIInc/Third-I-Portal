package org.paradyne.bean.TravelManagement.TravelProcess;

import java.util.ArrayList;

import javax.management.loading.PrivateClassLoader;
import javax.security.auth.PrivateCredentialPermission;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0651
 *
 */
public class TmsTrvlDesk extends BeanBase {
	//for iterator
	private String hidDeskCode="";
	private String deskId;
	private String empId;
	private String deskTrvlAppId;//extra field for uniqueness
	private String travelAppId="";
	private String trvlinitrId=""; 
	private String trvlEmpId=""; 
	private String applDate=""; 
	private String travelIndAppId="";
	private String checkStatus="";
	private String chkTypeFlag="";
	private String trvlEmpName=""; 
	private String trvlReqName=""; 
	private String trvlInitrName=""; 
	private String journeyDate=""; 
	private String SchAssgnMnt="";
	private String deskStatus="";
	private String chkAsgnmt="";
	private String hidChkAsgnmt="N";
	private String penTabLen="";
	private String hidTrvlEmpId="";	
	private String dupDeskId="";
	private String hiddenEdit="";
	private String colorId="";
	private String colorFlag="false";
	private String chkAsgnmtState="";
	private String holdStatus="";
	private String selfCheck = "";
	private String empGrade="";
	
	

	private String chktrvlAsigned="";	
	private String chkConvAsigned="";
	private String chkLodgeAsigned="";
	private String hidChktrvlAsigned="";
	private String hidChkLodgeAsigned="";
	private String hidChkConvAsigned="";		 	  
	
	// control flags
	private boolean noData = false;
	private String pen = "";
	private String assigned="";
	private String booked="";
	private String rejected="";
	private String penFlg = "";
	private String assignedFlg="";
	private String bookedFlg="";
	private String rejectedFlg="";
	private String submited="";
	private String submitFlg="";
	private String  hidRow="0";
	private String canceled="false";
	private String cancelFlag="false";
	private boolean applyFilterFlag=false;
	private String searchFlag="false";
	private String chkSerch="";
	private boolean ShowFilterValue=false;
	private String pendingCancel="false";
	private String pendingCancelFlg="false";

	
	//COMMON VARIABLES FOR VIEWING APPLICATIONS FROM DESK
	private String tmsTrvlId = "";
	private String tmsTrvlIndiId = "";
	private String tmsChkTypeFlg = "";
	private String singleApplication;
	private String isApprover;
	
	// for paging
	private String hdPage = "";
	private String fromTotRec = "";
	private String toTotRec = "";
	private String pageFlg="true";
	
	
	
	// for iterator

	ArrayList travelList = new ArrayList();
	ArrayList schedlrList = new ArrayList();

	// FOR VIEW JSP
	private boolean selfTrvlFlag = false;
	private boolean guestTrvlFlag = false;
	
	
	//for scheduler list
	private String schEmpId = "";
	private String schEmpToken = "";
	private String schEmpName = "";
	private String trvlRadio = "";
	private String loclConvRadio = "";
	private String lodgeRadio= "";
	private String hidLodgeRadio= "";
	private String hidLoclConvRadio= "";
	private String hidTrvlRadio= "";
	private String schViewFlg="false";
	private String hidSchCode= "";
	
	//for child window iterator
	ArrayList assignedCntList = new ArrayList();
	private String schEmpTokenCnt="";
	private String schEmpIdCnt= "";
	private String schEmpNameCnt= "";
	private String trvlCnt= "";
	private String localConvCnt= "";
	private String lodgeCnt= "";
	    
	  //for view of Assign button
	private String viewAssignFlg= "false";
	
	
	//for check box settings
	private String myHidden="0";
	
	//To check authorities is defined or not
	private String onLoadFlg="false"; 
	private String onLoadSubSchFlg="false"; 
	private String editFlg="false"; 
	
	
	// for new paging

	private String myPage = "";
	private String status = "";
	private boolean pageFlag = false;
	
//flags use full for Travel view
	
	private String deskFlag="";
	
	
	
	//changes for TMS-III
	
	private String trvlAsignedTo="";
	private String convAsignedTo="";
	private String lodgeAsignedTo="";
	private String trvlAsignedToId="";
	private String convAsignedToId="";
	private String lodgeAsignedToId="";
	private String trvlAsignedToEdit="";
	private String convAsignedToEdit="";
	private String lodgeAsignedToEdit="";
	
	
	//for filter
	private String filterEmpName="";
	private String filterEmpId="";
	private String filterInitName="";
	private String filterInitId="";
	private String filterApplDate="";
	private String filterStartDate="";
	private String filterTrvlId="";
	private String filterTrvlReq="";
	private String filterTrvlAsignedToId="";
	private String filterTrvlAsignedTo="";
	private String filterAccomAsignedTo="";
	private String filterAccomAsignedToId="";
	private String filterConvAsignedToId="";
	private String filterConvAsignedTo="";
	private String filterEmpToken="";
	private String filterInitToken="";
	private String filterTrvlAsignedToToken="";
	private String filterAccomAsignedToToken="";
	private String filterConvAsignedToToken="";
	
	
//for counting the applications
	
	private String pendCnt="0";
	private String assgnedCnt="0";
	private String bookedCnt="0";
	private String cancelCnt="0";
	private String submitCnt="0";
	private String rejectCnt="0";
	private String pendcancelCnt="0";
	
	
	
	//start booking
	
	

	// FOR EMPLOYEE iNFORMATION

	private String empToken = "";
	private String employeeName = "";
	private String brnchName = "";
	private String deptName = "";
	private String desgn = "";
	private String applDateSubmit = "";
	private String statusView = "";
	private String grade = "";
	private String gradId = "";
	private String age = "";
	private String contactNo = "";
	private String empIdSubmit = "";
	private String othersName = "";
	private String initIdSubmit = "";

	// For guest Information

	private String empTokenG = "";
	private String employeeNameG = "";
	private String guestName = "";
	private String applDateG = "";
	private String statusViewG = "";
	private String ageG = "";
	private String contactNoG = "";

	

	// for Tour Details
	private boolean tourFlag = true;
	private String trvlAppFor = "";
	private String trvlReqNameBook = "";
	private String trvlPurpose = "";
	private String trvlAccom = "";
	private String trvlArrngmt = "";
	private String trvlLocCon = "";
	private String trvlType = "";
	private String tourStrtDate = "";
	private String tourEndDate = "";
	private String travelReqId = "";
	
	
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
	private String jourDetails = "";
	private String JournDtlCode = "";

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
	private String medium = "";
	private String locConFrmDate = "";
	private String locConToDate = "";
	private String locDetails = "";
	private String locConCode = "";

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
	private String accDetails = "";
	private String lodgCode = "";
	private String hotelTypeId = "";
	 
	// for showing red colour
	private String violateFlag = "N";
	private String violateFlagRm = "N";
	
	// for advance details
	private String trvlAdvAmt = "";
	private String trvlPrefPayMode = "";
	private String trvlExpSettleDate = "";

	// for identification details
	private String idProof = "";
	private String idNumber = "";
	private String idCmts = "";
	
	//End of Start booking
	
	
	
	// fields for showing Travel Policy
	private String appDate = "";
	private String startDate = "";
	private String endDate = "";

	// Travel Policy Violation string
	private String polViolationStr = "";
	private String startDt = "";
	private String endDt = "";
	private String GradeId = "";
	private String tpViolationStr = "";
	
	//For uploading
	private String uploadFileName="";
	private String uploadLodgeFileName="";
	private String uploadLocFileName="";
	
	
	//for checking self or company
	private String tourTrvSts ="";
	private String tourConvSts ="";
	private String tourAccomSts="";
	private boolean bookedDtlFlg=false;
	private String trvlSchdlrCmts="";
	
	
	public String getTrvlSchdlrCmts() {
		return trvlSchdlrCmts;
	}
	public void setTrvlSchdlrCmts(String trvlSchdlrCmts) {
		this.trvlSchdlrCmts = trvlSchdlrCmts;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getFilterEmpToken() {
		return filterEmpToken;
	}
	public void setFilterEmpToken(String filterEmpToken) {
		this.filterEmpToken = filterEmpToken;
	}
	public String getFilterTrvlAsignedToToken() {
		return filterTrvlAsignedToToken;
	}
	public void setFilterTrvlAsignedToToken(String filterTrvlAsignedToToken) {
		this.filterTrvlAsignedToToken = filterTrvlAsignedToToken;
	}
	public String getFilterAccomAsignedToToken() {
		return filterAccomAsignedToToken;
	}
	public void setFilterAccomAsignedToToken(String filterAccomAsignedToToken) {
		this.filterAccomAsignedToToken = filterAccomAsignedToToken;
	}
	public String getFilterConvAsignedToToken() {
		return filterConvAsignedToToken;
	}
	public void setFilterConvAsignedToToken(String filterConvAsignedToToken) {
		this.filterConvAsignedToToken = filterConvAsignedToToken;
	}
	public String getDeskFlag() {
		return deskFlag;
	}
	public void setDeskFlag(String deskFlag) {
		this.deskFlag = deskFlag;
	}
	public String getSchViewFlg() {
		return schViewFlg;
	}
	public void setSchViewFlg(String schViewFlg) {
		this.schViewFlg = schViewFlg;
	}
	public String getSchEmpId() {
		return schEmpId;
	}
	public void setSchEmpId(String schEmpId) {
		this.schEmpId = schEmpId;
	}
	public String getSchEmpName() {
		return schEmpName;
	}
	public void setSchEmpName(String schEmpName) {
		this.schEmpName = schEmpName;
	}
	public String getTrvlRadio() {
		return trvlRadio;
	}
	public void setTrvlRadio(String trvlRadio) {
		this.trvlRadio = trvlRadio;
	}
	public String getLoclConvRadio() {
		return loclConvRadio;
	}
	public void setLoclConvRadio(String loclConvRadio) {
		this.loclConvRadio = loclConvRadio;
	}
	public String getLodgeRadio() {
		return lodgeRadio;
	}
	public void setLodgeRadio(String lodgeRadio) {
		this.lodgeRadio = lodgeRadio;
	}
	public ArrayList getTravelList() {
		return travelList;
	}
	public void setTravelList(ArrayList travelList) {
		this.travelList = travelList;
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
	public String getTravelAppId() {
		return travelAppId;
	}
	public void setTravelAppId(String travelAppId) {
		this.travelAppId = travelAppId;
	}
	public String getTrvlinitrId() {
		return trvlinitrId;
	}
	public void setTrvlinitrId(String trvlinitrId) {
		this.trvlinitrId = trvlinitrId;
	}
	public String getTrvlEmpId() {
		return trvlEmpId;
	}
	public void setTrvlEmpId(String trvlEmpId) {
		this.trvlEmpId = trvlEmpId;
	}
	public String getApplDate() {
		return applDate;
	}
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}
	public String getTravelIndAppId() {
		return travelIndAppId;
	}
	public void setTravelIndAppId(String travelIndAppId) {
		this.travelIndAppId = travelIndAppId;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getChkTypeFlag() {
		return chkTypeFlag;
	}
	public void setChkTypeFlag(String chkTypeFlag) {
		this.chkTypeFlag = chkTypeFlag;
	}
	public String getTrvlEmpName() {
		return trvlEmpName;
	}
	public void setTrvlEmpName(String trvlEmpName) {
		this.trvlEmpName = trvlEmpName;
	}
	public String getTrvlInitrName() {
		return trvlInitrName;
	}
	public void setTrvlInitrName(String trvlInitrName) {
		this.trvlInitrName = trvlInitrName;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getSchAssgnMnt() {
		return SchAssgnMnt;
	}
	public void setSchAssgnMnt(String schAssgnMnt) {
		SchAssgnMnt = schAssgnMnt;
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
	public String getDeskId() {
		return deskId;
	}
	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}
	public String getDeskStatus() {
		return deskStatus;
	}
	public void setDeskStatus(String deskStatus) {
		this.deskStatus = deskStatus;
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
	public String getChkAsgnmt() {
		return chkAsgnmt;
	}
	public void setChkAsgnmt(String chkAsgnmt) {
		this.chkAsgnmt = chkAsgnmt;
	}
	public String getHidChkAsgnmt() {
		return hidChkAsgnmt;
	}
	public void setHidChkAsgnmt(String hidChkAsgnmt) {
		this.hidChkAsgnmt = hidChkAsgnmt;
	}
	public ArrayList getSchedlrList() {
		return schedlrList;
	}
	public void setSchedlrList(ArrayList schedlrList) {
		this.schedlrList = schedlrList;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getBooked() {
		return booked;
	}
	public void setBooked(String booked) {
		this.booked = booked;
	}
	public String getRejected() {
		return rejected;
	}
	public void setRejected(String rejected) {
		this.rejected = rejected;
	}
	public String getPenFlg() {
		return penFlg;
	}
	public void setPenFlg(String penFlg) {
		this.penFlg = penFlg;
	}
	public String getAssignedFlg() {
		return assignedFlg;
	}
	public void setAssignedFlg(String assignedFlg) {
		this.assignedFlg = assignedFlg;
	}
	public String getBookedFlg() {
		return bookedFlg;
	}
	public void setBookedFlg(String bookedFlg) {
		this.bookedFlg = bookedFlg;
	}
	public String getRejectedFlg() {
		return rejectedFlg;
	}
	public void setRejectedFlg(String rejectedFlg) {
		this.rejectedFlg = rejectedFlg;
	}
	public String getHidLodgeRadio() {
		return hidLodgeRadio;
	}
	public void setHidLodgeRadio(String hidLodgeRadio) {
		this.hidLodgeRadio = hidLodgeRadio;
	}
	public String getHidLoclConvRadio() {
		return hidLoclConvRadio;
	}
	public void setHidLoclConvRadio(String hidLoclConvRadio) {
		this.hidLoclConvRadio = hidLoclConvRadio;
	}
	public String getHidTrvlRadio() {
		return hidTrvlRadio;
	}
	public void setHidTrvlRadio(String hidTrvlRadio) {
		this.hidTrvlRadio = hidTrvlRadio;
	}
	public String getPenTabLen() {
		return penTabLen;
	}
	public void setPenTabLen(String penTabLen) {
		this.penTabLen = penTabLen;
	}
	public String getHidSchCode() {
		return hidSchCode;
	}
	public void setHidSchCode(String hidSchCode) {
		this.hidSchCode = hidSchCode;
	}
	public String getHidDeskCode() {
		return hidDeskCode;
	}
	public void setHidDeskCode(String hidDeskCode) {
		this.hidDeskCode = hidDeskCode;
	}
	
	public String getHidChktrvlAsigned() {
		return hidChktrvlAsigned;
	}
	public void setHidChktrvlAsigned(String hidChktrvlAsigned) {
		this.hidChktrvlAsigned = hidChktrvlAsigned;
	}
	public String getChktrvlAsigned() {
		return chktrvlAsigned;
	}
	public void setChktrvlAsigned(String chktrvlAsigned) {
		this.chktrvlAsigned = chktrvlAsigned;
	}
	public String getChkConvAsigned() {
		return chkConvAsigned;
	}
	public void setChkConvAsigned(String chkConvAsigned) {
		this.chkConvAsigned = chkConvAsigned;
	}
	public String getChkLodgeAsigned() {
		return chkLodgeAsigned;
	}
	public void setChkLodgeAsigned(String chkLodgeAsigned) {
		this.chkLodgeAsigned = chkLodgeAsigned;
	}
	public String getHidChkLodgeAsigned() {
		return hidChkLodgeAsigned;
	}
	public void setHidChkLodgeAsigned(String hidChkLodgeAsigned) {
		this.hidChkLodgeAsigned = hidChkLodgeAsigned;
	}
	public String getHidChkConvAsigned() {
		return hidChkConvAsigned;
	}
	public void setHidChkConvAsigned(String hidChkConvAsigned) {
		this.hidChkConvAsigned = hidChkConvAsigned;
	}
	public ArrayList getAssignedCntList() {
		return assignedCntList;
	}
	public void setAssignedCntList(ArrayList assignedCntList) {
		this.assignedCntList = assignedCntList;
	}
	public String getSchEmpIdCnt() {
		return schEmpIdCnt;
	}
	public void setSchEmpIdCnt(String schEmpIdCnt) {
		this.schEmpIdCnt = schEmpIdCnt;
	}
	public String getSchEmpNameCnt() {
		return schEmpNameCnt;
	}
	public void setSchEmpNameCnt(String schEmpNameCnt) {
		this.schEmpNameCnt = schEmpNameCnt;
	}
	public String getTrvlCnt() {
		return trvlCnt;
	}
	public void setTrvlCnt(String trvlCnt) {
		this.trvlCnt = trvlCnt;
	}
	public String getLocalConvCnt() {
		return localConvCnt;
	}
	public void setLocalConvCnt(String localConvCnt) {
		this.localConvCnt = localConvCnt;
	}
	public String getLodgeCnt() {
		return lodgeCnt;
	}
	public void setLodgeCnt(String lodgeCnt) {
		this.lodgeCnt = lodgeCnt;
	}
	public String getHidTrvlEmpId() {
		return hidTrvlEmpId;
	}
	public void setHidTrvlEmpId(String hidTrvlEmpId) {
		this.hidTrvlEmpId = hidTrvlEmpId;
	}
	public String getViewAssignFlg() {
		return viewAssignFlg;
	}
	public void setViewAssignFlg(String viewAssignFlg) {
		this.viewAssignFlg = viewAssignFlg;
	}
	public String getDupDeskId() {
		return dupDeskId;
	}
	public void setDupDeskId(String dupDeskId) {
		this.dupDeskId = dupDeskId;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getSchEmpTokenCnt() {
		return schEmpTokenCnt;
	}
	public void setSchEmpTokenCnt(String schEmpTokenCnt) {
		this.schEmpTokenCnt = schEmpTokenCnt;
	}
	public String getSchEmpToken() {
		return schEmpToken;
	}
	public void setSchEmpToken(String schEmpToken) {
		this.schEmpToken = schEmpToken;
	}
	public String getMyHidden() {
		return myHidden;
	}
	public void setMyHidden(String myHidden) {
		this.myHidden = myHidden;
	}
	public String getOnLoadFlg() {
		return onLoadFlg;
	}
	public void setOnLoadFlg(String onLoadFlg) {
		this.onLoadFlg = onLoadFlg;
	}
	public String getEditFlg() {
		return editFlg;
	}
	public void setEditFlg(String editFlg) {
		this.editFlg = editFlg;
	}
	public String getPageFlg() {
		return pageFlg;
	}
	public void setPageFlg(String pageFlg) {
		this.pageFlg = pageFlg;
	}
	public String getSingleApplication() {
		return singleApplication;
	}
	public void setSingleApplication(String singleApplication) {
		this.singleApplication = singleApplication;
	}
	public String getIsApprover() {
		return isApprover;
	}
	public void setIsApprover(String isApprover) {
		this.isApprover = isApprover;
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
	
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getColorFlag() {
		return colorFlag;
	}
	public void setColorFlag(String colorFlag) {
		this.colorFlag = colorFlag;
	}
	public String getDeskTrvlAppId() {
		return deskTrvlAppId;
	}
	public void setDeskTrvlAppId(String deskTrvlAppId) {
		this.deskTrvlAppId = deskTrvlAppId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getChkAsgnmtState() {
		return chkAsgnmtState;
	}
	public void setChkAsgnmtState(String chkAsgnmtState) {
		this.chkAsgnmtState = chkAsgnmtState;
	}
	public String getHidRow() {
		return hidRow;
	}
	public void setHidRow(String hidRow) {
		this.hidRow = hidRow;
	}
	public String getTrvlAsignedTo() {
		return trvlAsignedTo;
	}
	public void setTrvlAsignedTo(String trvlAsignedTo) {
		this.trvlAsignedTo = trvlAsignedTo;
	}
	public String getConvAsignedTo() {
		return convAsignedTo;
	}
	public void setConvAsignedTo(String convAsignedTo) {
		this.convAsignedTo = convAsignedTo;
	}
	public String getLodgeAsignedTo() {
		return lodgeAsignedTo;
	}
	public void setLodgeAsignedTo(String lodgeAsignedTo) {
		this.lodgeAsignedTo = lodgeAsignedTo;
	}
	public String getFilterEmpName() {
		return filterEmpName;
	}
	public void setFilterEmpName(String filterEmpName) {
		this.filterEmpName = filterEmpName;
	}
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterInitName() {
		return filterInitName;
	}
	public void setFilterInitName(String filterInitName) {
		this.filterInitName = filterInitName;
	}
	public String getFilterInitId() {
		return filterInitId;
	}
	public void setFilterInitId(String filterInitId) {
		this.filterInitId = filterInitId;
	}
	public String getFilterApplDate() {
		return filterApplDate;
	}
	public void setFilterApplDate(String filterApplDate) {
		this.filterApplDate = filterApplDate;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterTrvlId() {
		return filterTrvlId;
	}
	public void setFilterTrvlId(String filterTrvlId) {
		this.filterTrvlId = filterTrvlId;
	}
	public String getFilterTrvlReq() {
		return filterTrvlReq;
	}
	public void setFilterTrvlReq(String filterTrvlReq) {
		this.filterTrvlReq = filterTrvlReq;
	}
	public String getFilterTrvlAsignedToId() {
		return filterTrvlAsignedToId;
	}
	public void setFilterTrvlAsignedToId(String filterTrvlAsignedToId) {
		this.filterTrvlAsignedToId = filterTrvlAsignedToId;
	}
	public String getFilterAccomAsignedTo() {
		return filterAccomAsignedTo;
	}
	public void setFilterAccomAsignedTo(String filterAccomAsignedTo) {
		this.filterAccomAsignedTo = filterAccomAsignedTo;
	}
	public String getFilterAccomAsignedToId() {
		return filterAccomAsignedToId;
	}
	public void setFilterAccomAsignedToId(String filterAccomAsignedToId) {
		this.filterAccomAsignedToId = filterAccomAsignedToId;
	}
	public String getFilterConvAsignedToId() {
		return filterConvAsignedToId;
	}
	public void setFilterConvAsignedToId(String filterConvAsignedToId) {
		this.filterConvAsignedToId = filterConvAsignedToId;
	}
	public String getFilterConvAsignedTo() {
		return filterConvAsignedTo;
	}
	public void setFilterConvAsignedTo(String filterConvAsignedTo) {
		this.filterConvAsignedTo = filterConvAsignedTo;
	}
	public String getFilterInitToken() {
		return filterInitToken;
	}
	public void setFilterInitToken(String filterInitToken) {
		this.filterInitToken = filterInitToken;
	}
	public String getFilterTrvlAsignedTo() {
		return filterTrvlAsignedTo;
	}
	public void setFilterTrvlAsignedTo(String filterTrvlAsignedTo) {
		this.filterTrvlAsignedTo = filterTrvlAsignedTo;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public boolean isApplyFilterFlag() {
		return applyFilterFlag;
	}
	public void setApplyFilterFlag(boolean applyFilterFlag) {
		this.applyFilterFlag = applyFilterFlag;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getChkSerch() {
		return chkSerch;
	}
	public void setChkSerch(String chkSerch) {
		this.chkSerch = chkSerch;
	}
	public boolean isShowFilterValue() {
		return ShowFilterValue;
	}
	public void setShowFilterValue(boolean showFilterValue) {
		ShowFilterValue = showFilterValue;
	}
	public String getPendCnt() {
		return pendCnt;
	}
	public void setPendCnt(String pendCnt) {
		this.pendCnt = pendCnt;
	}
	public String getAssgnedCnt() {
		return assgnedCnt;
	}
	public void setAssgnedCnt(String assgnedCnt) {
		this.assgnedCnt = assgnedCnt;
	}
	public String getBookedCnt() {
		return bookedCnt;
	}
	public void setBookedCnt(String bookedCnt) {
		this.bookedCnt = bookedCnt;
	}
	public String getCancelCnt() {
		return cancelCnt;
	}
	public void setCancelCnt(String cancelCnt) {
		this.cancelCnt = cancelCnt;
	}
	public String getSubmited() {
		return submited;
	}
	public void setSubmited(String submited) {
		this.submited = submited;
	}
	public String getSubmitFlg() {
		return submitFlg;
	}
	public void setSubmitFlg(String submitFlg) {
		this.submitFlg = submitFlg;
	}
	public String getSubmitCnt() {
		return submitCnt;
	}
	public void setSubmitCnt(String submitCnt) {
		this.submitCnt = submitCnt;
	}
	public String getTrvlReqName() {
		return trvlReqName;
	}
	public void setTrvlReqName(String trvlReqName) {
		this.trvlReqName = trvlReqName;
	}
	public String getHoldStatus() {
		return holdStatus;
	}
	public void setHoldStatus(String holdStatus) {
		this.holdStatus = holdStatus;
	}
	public String getOnLoadSubSchFlg() {
		return onLoadSubSchFlg;
	}
	public void setOnLoadSubSchFlg(String onLoadSubSchFlg) {
		this.onLoadSubSchFlg = onLoadSubSchFlg;
	}
	public String getRejectCnt() {
		return rejectCnt;
	}
	public void setRejectCnt(String rejectCnt) {
		this.rejectCnt = rejectCnt;
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
	public String getTrvlReqNameBook() {
		return trvlReqNameBook;
	}
	public void setTrvlReqNameBook(String trvlReqNameBook) {
		this.trvlReqNameBook = trvlReqNameBook;
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
	public String getJourClsId() {
		return jourClsId;
	}
	public void setJourClsId(String jourClsId) {
		this.jourClsId = jourClsId;
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
	public String getLocConDtlId() {
		return locConDtlId;
	}
	public void setLocConDtlId(String locConDtlId) {
		this.locConDtlId = locConDtlId;
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
	public String getLocConFrmTime() {
		return locConFrmTime;
	}
	public void setLocConFrmTime(String locConFrmTime) {
		this.locConFrmTime = locConFrmTime;
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
	public String getLodgDtlId() {
		return lodgDtlId;
	}
	public void setLodgDtlId(String lodgDtlId) {
		this.lodgDtlId = lodgDtlId;
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
	public String getPendcancelCnt() {
		return pendcancelCnt;
	}
	public void setPendcancelCnt(String pendcancelCnt) {
		this.pendcancelCnt = pendcancelCnt;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getOthersName() {
		return othersName;
	}
	public void setOthersName(String othersName) {
		this.othersName = othersName;
	}
	public String getEmpTokenG() {
		return empTokenG;
	}
	public void setEmpTokenG(String empTokenG) {
		this.empTokenG = empTokenG;
	}
	public String getEmployeeNameG() {
		return employeeNameG;
	}
	public void setEmployeeNameG(String employeeNameG) {
		this.employeeNameG = employeeNameG;
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
	public String getSelfCheck() {
		return selfCheck;
	}
	public void setSelfCheck(String selfCheck) {
		this.selfCheck = selfCheck;
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
	public String getPolViolationStr() {
		return polViolationStr;
	}
	public void setPolViolationStr(String polViolationStr) {
		this.polViolationStr = polViolationStr;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getGradeId() {
		return GradeId;
	}
	public void setGradeId(String gradeId) {
		GradeId = gradeId;
	}
	public String getTpViolationStr() {
		return tpViolationStr;
	}
	public void setTpViolationStr(String tpViolationStr) {
		this.tpViolationStr = tpViolationStr;
	}
	public String getApplDateSubmit() {
		return applDateSubmit;
	}
	public void setApplDateSubmit(String applDateSubmit) {
		this.applDateSubmit = applDateSubmit;
	}
	public String getEmpIdSubmit() {
		return empIdSubmit;
	}
	public void setEmpIdSubmit(String empIdSubmit) {
		this.empIdSubmit = empIdSubmit;
	}
	public String getTravelReqId() {
		return travelReqId;
	}
	public void setTravelReqId(String travelReqId) {
		this.travelReqId = travelReqId;
	}
	public String getViolateFlag() {
		return violateFlag;
	}
	public void setViolateFlag(String violateFlag) {
		this.violateFlag = violateFlag;
	}
	public String getViolateFlagRm() {
		return violateFlagRm;
	}
	public void setViolateFlagRm(String violateFlagRm) {
		this.violateFlagRm = violateFlagRm;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getLocConFrmDate() {
		return locConFrmDate;
	}
	public void setLocConFrmDate(String locConFrmDate) {
		this.locConFrmDate = locConFrmDate;
	}
	public String getLocConToDate() {
		return locConToDate;
	}
	public void setLocConToDate(String locConToDate) {
		this.locConToDate = locConToDate;
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
	public String getJournDtlCode() {
		return JournDtlCode;
	}
	public void setJournDtlCode(String journDtlCode) {
		JournDtlCode = journDtlCode;
	}
	public String getLodgCode() {
		return lodgCode;
	}
	public void setLodgCode(String lodgCode) {
		this.lodgCode = lodgCode;
	}
	public String getLocConCode() {
		return locConCode;
	}
	public void setLocConCode(String locConCode) {
		this.locConCode = locConCode;
	}
	public String getHotelTypeId() {
		return hotelTypeId;
	}
	public void setHotelTypeId(String hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
	}
	public String getPendingCancel() {
		return pendingCancel;
	}
	public void setPendingCancel(String pendingCancel) {
		this.pendingCancel = pendingCancel;
	}
	public String getPendingCancelFlg() {
		return pendingCancelFlg;
	}
	public void setPendingCancelFlg(String pendingCancelFlg) {
		this.pendingCancelFlg = pendingCancelFlg;
	}
	public String getCanceled() {
		return canceled;
	}
	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}
	public String getTrvlAsignedToEdit() {
		return trvlAsignedToEdit;
	}
	public void setTrvlAsignedToEdit(String trvlAsignedToEdit) {
		this.trvlAsignedToEdit = trvlAsignedToEdit;
	}
	public String getConvAsignedToEdit() {
		return convAsignedToEdit;
	}
	public void setConvAsignedToEdit(String convAsignedToEdit) {
		this.convAsignedToEdit = convAsignedToEdit;
	}
	public String getLodgeAsignedToEdit() {
		return lodgeAsignedToEdit;
	}
	public void setLodgeAsignedToEdit(String lodgeAsignedToEdit) {
		this.lodgeAsignedToEdit = lodgeAsignedToEdit;
	}
	public String getTrvlAsignedToId() {
		return trvlAsignedToId;
	}
	public void setTrvlAsignedToId(String trvlAsignedToId) {
		this.trvlAsignedToId = trvlAsignedToId;
	}
	public String getConvAsignedToId() {
		return convAsignedToId;
	}
	public void setConvAsignedToId(String convAsignedToId) {
		this.convAsignedToId = convAsignedToId;
	}
	public String getLodgeAsignedToId() {
		return lodgeAsignedToId;
	}
	public void setLodgeAsignedToId(String lodgeAsignedToId) {
		this.lodgeAsignedToId = lodgeAsignedToId;
	}
	public String getEmpGrade() {
		return empGrade;
	}
	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}
	public String getUploadLodgeFileName() {
		return uploadLodgeFileName;
	}
	public void setUploadLodgeFileName(String uploadLodgeFileName) {
		this.uploadLodgeFileName = uploadLodgeFileName;
	}
	public String getUploadLocFileName() {
		return uploadLocFileName;
	}
	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}
	public String getTourTrvSts() {
		return tourTrvSts;
	}
	public void setTourTrvSts(String tourTrvSts) {
		this.tourTrvSts = tourTrvSts;
	}
	public String getTourConvSts() {
		return tourConvSts;
	}
	public void setTourConvSts(String tourConvSts) {
		this.tourConvSts = tourConvSts;
	}
	public String getTourAccomSts() {
		return tourAccomSts;
	}
	public void setTourAccomSts(String tourAccomSts) {
		this.tourAccomSts = tourAccomSts;
	}
	public boolean isBookedDtlFlg() {
		return bookedDtlFlg;
	}
	public void setBookedDtlFlg(boolean bookedDtlFlg) {
		this.bookedDtlFlg = bookedDtlFlg;
	}
	public String getInitIdSubmit() {
		return initIdSubmit;
	}
	public void setInitIdSubmit(String initIdSubmit) {
		this.initIdSubmit = initIdSubmit;
	}
	
	
	

}
