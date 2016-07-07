package org.paradyne.bean.TravelManagement.TravelPlan;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * 
 * @author Krishna
 * 
 */
public class TravelScheduleApprover extends BeanBase {
	// for paging
	private String hdPage = "";
	private String fromTotRec = "";
	private String toTotRec = "";

	// control flags
	private boolean noData = false;
	private String pen = "";
	private String apprved = "";
	private String regcted = "";

	// for iterator

	ArrayList travelSchApprList = new ArrayList();

	// variable for passing to next form
	private String trvAppId = "";

	private String travelEmpId = "";
	private String level = "";

	private String empName = "";
	private String trRequestName = "";
	private String appDate = "";
	private String checkStatus = "";
	private String comment = "";

	// for combobox

	private String apprStatus = "";
	private String apprflag = "";

	// ***************declare variables same as Schedule details for view
	// purpose**************

	// control flags for showing iterators
	private boolean jourBkFlg = false;
	private boolean localConFlg = false;
	private boolean lodgFlg = false;

	private String appID = "";
	private String status = "";
	private String rowId = "";

	private String schReqAmount = "";
	private String schAdvAmt = "";
	private String schPayMode = "";
	private String schComment = "";
	private String schApprComment = "";

	// for trvSchDtl form
	private String empId = "";
	private String empToken = "";
	private String employeeName = "";
	private String brnchName = "";
	private String deptName = "";
	private String desgName = "";
	private String applDate = "";
	private String grdName = "";
	private String dob = "";
	private String mobile = "";
	private String trvlRequest="";
	private String tourStrtDate="";
	private String tourEndDate="";
	private String tourPurpose="";

	private String JourneyId = "";

	// for Booking Details Iterator
	ArrayList travelSchList = new ArrayList();

	private String sourceAndDest = "";
	private String travelDate = "";
	private String travelTime = "";
	private String travelMdAndCls = "";
	private String trvlVehNo = "";
	private String ticktNo = "";
	private String ticktCost = "";
	private String uploadFileName = "";

	private String appJournDtlFrom = "";
	private String appJournDtlTo = "";
	private String appJournDtlId = "";

	// for Local Conveyance Details Iterator
	ArrayList localConvDtls = new ArrayList();
	private String locConCity = "";

	private String locConSource = "";
	private String locConDate = "";
	private String locConTrvlMod = "";
	private String locConTrvlModNum = "";
	private String locConTrvlConPer = "";
	private String locConTrvlConNum = "";
	private String locConTrvlPcPer = "";
	private String locConTrvlPcTime = "";
	private String locConTrvlPcPlace = "";
	private String locConTrvlPcToriffCst = "";

	private String locConCityId = "";
	private String locConTrvlModId = "";
	private String locConId = "";

	// for lodging Details Iterator

	ArrayList lodgingDtls = new ArrayList();
	private String lodgFrmDate = "";
	private String lodgChkInTm = "";
	private String lodgToDate = "";
	private String lodgChkOutTm = "";
	private String lodgAddrs = "";
	private String lodgBokAmt = "";
	private String lodgBilAmt = "";
	private String lodgeDtlId = "";

	// to view
	private String lodgCity = "";
	private String lodgHotel = "";
	private String lodgRoom = "";
	private String lodgUploadFileName = "";

	// for trvSchDtl form

	private String totTrvCost = "0";
	private String totTariffCost = "0";
	private String totLodgCost = "0";
	// for approve or reject methods
	private String decFlag = "";
	private String savingStatus = "";

	// for call Back in jsp
	private String stat = "";
	// for showing Travel Policy
	private String travelAppId = "";
	private String trAppId = "";
	
	//for validations of Travel Mode,hotel and Room Type
	
	private String policyId = "";
	private String validTrMode = "";
	private String validHotelType = "";
	private String validRoomType = "";
	private String gradeId = "";
	private String forwardFlag = "true";
	private String schAppComment = "";
	private String altSchEmpId = "";
	
	
	

	public String getAltSchEmpId() {
		return altSchEmpId;
	}

	public void setAltSchEmpId(String altSchEmpId) {
		this.altSchEmpId = altSchEmpId;
	}

	public String getSchAppComment() {
		return schAppComment;
	}

	public void setSchAppComment(String schAppComment) {
		this.schAppComment = schAppComment;
	}

	/**
	 * @return the hdPage
	 
	 */
	public String getHdPage() {
		return hdPage;
	}

	/**
	 * @param hdPage
	 *            the hdPage to set
	 */
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}

	/**
	 * @return the fromTotRec
	 */
	public String getFromTotRec() {
		return fromTotRec;
	}

	/**
	 * @param fromTotRec
	 *            the fromTotRec to set
	 */
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}

	/**
	 * @return the toTotRec
	 */
	public String getToTotRec() {
		return toTotRec;
	}

	/**
	 * @param toTotRec
	 *            the toTotRec to set
	 */
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * @return the pen
	 */
	public String getPen() {
		return pen;
	}

	/**
	 * @param pen
	 *            the pen to set
	 */
	public void setPen(String pen) {
		this.pen = pen;
	}

	/**
	 * @return the apprved
	 */
	public String getApprved() {
		return apprved;
	}

	/**
	 * @param apprved
	 *            the apprved to set
	 */
	public void setApprved(String apprved) {
		this.apprved = apprved;
	}

	/**
	 * @return the regcted
	 */
	public String getRegcted() {
		return regcted;
	}

	/**
	 * @param regcted
	 *            the regcted to set
	 */
	public void setRegcted(String regcted) {
		this.regcted = regcted;
	}

	/**
	 * @return the travelSchApprList
	 */
	public ArrayList getTravelSchApprList() {
		return travelSchApprList;
	}

	/**
	 * @param travelSchApprList
	 *            the travelSchApprList to set
	 */
	public void setTravelSchApprList(ArrayList travelSchApprList) {
		this.travelSchApprList = travelSchApprList;
	}

	/**
	 * @return the trvAppId
	 */
	public String getTrvAppId() {
		return trvAppId;
	}

	/**
	 * @param trvAppId
	 *            the trvAppId to set
	 */
	public void setTrvAppId(String trvAppId) {
		this.trvAppId = trvAppId;
	}

	/**
	 * @return the travelAppId
	 */
	public String getTravelAppId() {
		return travelAppId;
	}

	/**
	 * @param travelAppId
	 *            the travelAppId to set
	 */
	public void setTravelAppId(String travelAppId) {
		this.travelAppId = travelAppId;
	}

	/**
	 * @return the travelEmpId
	 */
	public String getTravelEmpId() {
		return travelEmpId;
	}

	/**
	 * @param travelEmpId
	 *            the travelEmpId to set
	 */
	public void setTravelEmpId(String travelEmpId) {
		this.travelEmpId = travelEmpId;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the trRequestName
	 */
	public String getTrRequestName() {
		return trRequestName;
	}

	/**
	 * @param trRequestName
	 *            the trRequestName to set
	 */
	public void setTrRequestName(String trRequestName) {
		this.trRequestName = trRequestName;
	}

	/**
	 * @return the appDate
	 */
	public String getAppDate() {
		return appDate;
	}

	/**
	 * @param appDate
	 *            the appDate to set
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	/**
	 * @return the checkStatus
	 */
	public String getCheckStatus() {
		return checkStatus;
	}

	/**
	 * @param checkStatus
	 *            the checkStatus to set
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * @return the noData
	 */
	public boolean isNoData() {
		return noData;
	}

	/**
	 * @param noData
	 *            the noData to set
	 */
	public void setNoData(boolean noData) {
		this.noData = noData;
	}

	/**
	 * @return the apprStatus
	 */
	public String getApprStatus() {
		return apprStatus;
	}

	/**
	 * @param apprStatus
	 *            the apprStatus to set
	 */
	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}

	/**
	 * @return the apprflag
	 */
	public String getApprflag() {
		return apprflag;
	}

	/**
	 * @param apprflag
	 *            the apprflag to set
	 */
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the jourBkFlg
	 */
	public boolean isJourBkFlg() {
		return jourBkFlg;
	}

	/**
	 * @param jourBkFlg
	 *            the jourBkFlg to set
	 */
	public void setJourBkFlg(boolean jourBkFlg) {
		this.jourBkFlg = jourBkFlg;
	}

	/**
	 * @return the localConFlg
	 */
	public boolean isLocalConFlg() {
		return localConFlg;
	}

	/**
	 * @param localConFlg
	 *            the localConFlg to set
	 */
	public void setLocalConFlg(boolean localConFlg) {
		this.localConFlg = localConFlg;
	}

	/**
	 * @return the lodgFlg
	 */
	public boolean isLodgFlg() {
		return lodgFlg;
	}

	/**
	 * @param lodgFlg
	 *            the lodgFlg to set
	 */
	public void setLodgFlg(boolean lodgFlg) {
		this.lodgFlg = lodgFlg;
	}

	/**
	 * @return the appID
	 */
	public String getAppID() {
		return appID;
	}

	/**
	 * @param appID
	 *            the appID to set
	 */
	public void setAppID(String appID) {
		this.appID = appID;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the rowId
	 */
	public String getRowId() {
		return rowId;
	}

	/**
	 * @param rowId
	 *            the rowId to set
	 */
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	/**
	 * @return the schAdvAmt
	 */
	public String getSchAdvAmt() {
		return schAdvAmt;
	}

	/**
	 * @param schAdvAmt
	 *            the schAdvAmt to set
	 */
	public void setSchAdvAmt(String schAdvAmt) {
		this.schAdvAmt = schAdvAmt;
	}

	/**
	 * @return the schPayMode
	 */
	public String getSchPayMode() {
		return schPayMode;
	}

	/**
	 * @param schPayMode
	 *            the schPayMode to set
	 */
	public void setSchPayMode(String schPayMode) {
		this.schPayMode = schPayMode;
	}

	/**
	 * @return the schComment
	 */
	public String getSchComment() {
		return schComment;
	}

	/**
	 * @param schComment
	 *            the schComment to set
	 */
	public void setSchComment(String schComment) {
		this.schComment = schComment;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName
	 *            the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the brnchName
	 */
	public String getBrnchName() {
		return brnchName;
	}

	/**
	 * @param brnchName
	 *            the brnchName to set
	 */
	public void setBrnchName(String brnchName) {
		this.brnchName = brnchName;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the desgName
	 */
	public String getDesgName() {
		return desgName;
	}

	/**
	 * @param desgName
	 *            the desgName to set
	 */
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	/**
	 * @return the applDate
	 */
	public String getApplDate() {
		return applDate;
	}

	/**
	 * @param applDate
	 *            the applDate to set
	 */
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	/**
	 * @return the grdName
	 */
	public String getGrdName() {
		return grdName;
	}

	/**
	 * @param grdName
	 *            the grdName to set
	 */
	public void setGrdName(String grdName) {
		this.grdName = grdName;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the journeyId
	 */
	public String getJourneyId() {
		return JourneyId;
	}

	/**
	 * @param journeyId
	 *            the journeyId to set
	 */
	public void setJourneyId(String journeyId) {
		JourneyId = journeyId;
	}

	/**
	 * @return the travelSchList
	 */
	public ArrayList getTravelSchList() {
		return travelSchList;
	}

	/**
	 * @param travelSchList
	 *            the travelSchList to set
	 */
	public void setTravelSchList(ArrayList travelSchList) {
		this.travelSchList = travelSchList;
	}

	/**
	 * @return the sourceAndDest
	 */
	public String getSourceAndDest() {
		return sourceAndDest;
	}

	/**
	 * @param sourceAndDest
	 *            the sourceAndDest to set
	 */
	public void setSourceAndDest(String sourceAndDest) {
		this.sourceAndDest = sourceAndDest;
	}

	/**
	 * @return the travelDate
	 */
	public String getTravelDate() {
		return travelDate;
	}

	/**
	 * @param travelDate
	 *            the travelDate to set
	 */
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	/**
	 * @return the travelTime
	 */
	public String getTravelTime() {
		return travelTime;
	}

	/**
	 * @param travelTime
	 *            the travelTime to set
	 */
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	/**
	 * @return the travelMdAndCls
	 */
	public String getTravelMdAndCls() {
		return travelMdAndCls;
	}

	/**
	 * @param travelMdAndCls
	 *            the travelMdAndCls to set
	 */
	public void setTravelMdAndCls(String travelMdAndCls) {
		this.travelMdAndCls = travelMdAndCls;
	}

	/**
	 * @return the trvlVehNo
	 */
	public String getTrvlVehNo() {
		return trvlVehNo;
	}

	/**
	 * @param trvlVehNo
	 *            the trvlVehNo to set
	 */
	public void setTrvlVehNo(String trvlVehNo) {
		this.trvlVehNo = trvlVehNo;
	}

	/**
	 * @return the ticktNo
	 */
	public String getTicktNo() {
		return ticktNo;
	}

	/**
	 * @param ticktNo
	 *            the ticktNo to set
	 */
	public void setTicktNo(String ticktNo) {
		this.ticktNo = ticktNo;
	}

	/**
	 * @return the ticktCost
	 */
	public String getTicktCost() {
		return ticktCost;
	}

	/**
	 * @param ticktCost
	 *            the ticktCost to set
	 */
	public void setTicktCost(String ticktCost) {
		this.ticktCost = ticktCost;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the appJournDtlFrom
	 */
	public String getAppJournDtlFrom() {
		return appJournDtlFrom;
	}

	/**
	 * @param appJournDtlFrom
	 *            the appJournDtlFrom to set
	 */
	public void setAppJournDtlFrom(String appJournDtlFrom) {
		this.appJournDtlFrom = appJournDtlFrom;
	}

	/**
	 * @return the appJournDtlTo
	 */
	public String getAppJournDtlTo() {
		return appJournDtlTo;
	}

	/**
	 * @param appJournDtlTo
	 *            the appJournDtlTo to set
	 */
	public void setAppJournDtlTo(String appJournDtlTo) {
		this.appJournDtlTo = appJournDtlTo;
	}

	/**
	 * @return the appJournDtlId
	 */
	public String getAppJournDtlId() {
		return appJournDtlId;
	}

	/**
	 * @param appJournDtlId
	 *            the appJournDtlId to set
	 */
	public void setAppJournDtlId(String appJournDtlId) {
		this.appJournDtlId = appJournDtlId;
	}

	/**
	 * @return the localConvDtls
	 */
	public ArrayList getLocalConvDtls() {
		return localConvDtls;
	}

	/**
	 * @param localConvDtls
	 *            the localConvDtls to set
	 */
	public void setLocalConvDtls(ArrayList localConvDtls) {
		this.localConvDtls = localConvDtls;
	}

	/**
	 * @return the locConCity
	 */
	public String getLocConCity() {
		return locConCity;
	}

	/**
	 * @param locConCity
	 *            the locConCity to set
	 */
	public void setLocConCity(String locConCity) {
		this.locConCity = locConCity;
	}

	/**
	 * @return the locConSource
	 */
	public String getLocConSource() {
		return locConSource;
	}

	/**
	 * @param locConSource
	 *            the locConSource to set
	 */
	public void setLocConSource(String locConSource) {
		this.locConSource = locConSource;
	}

	/**
	 * @return the locConDate
	 */
	public String getLocConDate() {
		return locConDate;
	}

	/**
	 * @param locConDate
	 *            the locConDate to set
	 */
	public void setLocConDate(String locConDate) {
		this.locConDate = locConDate;
	}

	/**
	 * @return the locConTrvlMod
	 */
	public String getLocConTrvlMod() {
		return locConTrvlMod;
	}

	/**
	 * @param locConTrvlMod
	 *            the locConTrvlMod to set
	 */
	public void setLocConTrvlMod(String locConTrvlMod) {
		this.locConTrvlMod = locConTrvlMod;
	}

	/**
	 * @return the locConTrvlModNum
	 */
	public String getLocConTrvlModNum() {
		return locConTrvlModNum;
	}

	/**
	 * @param locConTrvlModNum
	 *            the locConTrvlModNum to set
	 */
	public void setLocConTrvlModNum(String locConTrvlModNum) {
		this.locConTrvlModNum = locConTrvlModNum;
	}

	/**
	 * @return the locConTrvlConPer
	 */
	public String getLocConTrvlConPer() {
		return locConTrvlConPer;
	}

	/**
	 * @param locConTrvlConPer
	 *            the locConTrvlConPer to set
	 */
	public void setLocConTrvlConPer(String locConTrvlConPer) {
		this.locConTrvlConPer = locConTrvlConPer;
	}

	/**
	 * @return the locConTrvlConNum
	 */
	public String getLocConTrvlConNum() {
		return locConTrvlConNum;
	}

	/**
	 * @param locConTrvlConNum
	 *            the locConTrvlConNum to set
	 */
	public void setLocConTrvlConNum(String locConTrvlConNum) {
		this.locConTrvlConNum = locConTrvlConNum;
	}

	/**
	 * @return the locConTrvlPcPer
	 */
	public String getLocConTrvlPcPer() {
		return locConTrvlPcPer;
	}

	/**
	 * @param locConTrvlPcPer
	 *            the locConTrvlPcPer to set
	 */
	public void setLocConTrvlPcPer(String locConTrvlPcPer) {
		this.locConTrvlPcPer = locConTrvlPcPer;
	}

	/**
	 * @return the locConTrvlPcTime
	 */
	public String getLocConTrvlPcTime() {
		return locConTrvlPcTime;
	}

	/**
	 * @param locConTrvlPcTime
	 *            the locConTrvlPcTime to set
	 */
	public void setLocConTrvlPcTime(String locConTrvlPcTime) {
		this.locConTrvlPcTime = locConTrvlPcTime;
	}

	/**
	 * @return the locConTrvlPcPlace
	 */
	public String getLocConTrvlPcPlace() {
		return locConTrvlPcPlace;
	}

	/**
	 * @param locConTrvlPcPlace
	 *            the locConTrvlPcPlace to set
	 */
	public void setLocConTrvlPcPlace(String locConTrvlPcPlace) {
		this.locConTrvlPcPlace = locConTrvlPcPlace;
	}

	/**
	 * @return the locConTrvlPcToriffCst
	 */
	public String getLocConTrvlPcToriffCst() {
		return locConTrvlPcToriffCst;
	}

	/**
	 * @param locConTrvlPcToriffCst
	 *            the locConTrvlPcToriffCst to set
	 */
	public void setLocConTrvlPcToriffCst(String locConTrvlPcToriffCst) {
		this.locConTrvlPcToriffCst = locConTrvlPcToriffCst;
	}

	/**
	 * @return the locConCityId
	 */
	public String getLocConCityId() {
		return locConCityId;
	}

	/**
	 * @param locConCityId
	 *            the locConCityId to set
	 */
	public void setLocConCityId(String locConCityId) {
		this.locConCityId = locConCityId;
	}

	/**
	 * @return the locConTrvlModId
	 */
	public String getLocConTrvlModId() {
		return locConTrvlModId;
	}

	/**
	 * @param locConTrvlModId
	 *            the locConTrvlModId to set
	 */
	public void setLocConTrvlModId(String locConTrvlModId) {
		this.locConTrvlModId = locConTrvlModId;
	}

	/**
	 * @return the locConId
	 */
	public String getLocConId() {
		return locConId;
	}

	/**
	 * @param locConId
	 *            the locConId to set
	 */
	public void setLocConId(String locConId) {
		this.locConId = locConId;
	}

	/**
	 * @return the lodgingDtls
	 */
	public ArrayList getLodgingDtls() {
		return lodgingDtls;
	}

	/**
	 * @param lodgingDtls
	 *            the lodgingDtls to set
	 */
	public void setLodgingDtls(ArrayList lodgingDtls) {
		this.lodgingDtls = lodgingDtls;
	}

	/**
	 * @return the lodgFrmDate
	 */
	public String getLodgFrmDate() {
		return lodgFrmDate;
	}

	/**
	 * @param lodgFrmDate
	 *            the lodgFrmDate to set
	 */
	public void setLodgFrmDate(String lodgFrmDate) {
		this.lodgFrmDate = lodgFrmDate;
	}

	/**
	 * @return the lodgChkInTm
	 */
	public String getLodgChkInTm() {
		return lodgChkInTm;
	}

	/**
	 * @param lodgChkInTm
	 *            the lodgChkInTm to set
	 */
	public void setLodgChkInTm(String lodgChkInTm) {
		this.lodgChkInTm = lodgChkInTm;
	}

	/**
	 * @return the lodgToDate
	 */
	public String getLodgToDate() {
		return lodgToDate;
	}

	/**
	 * @param lodgToDate
	 *            the lodgToDate to set
	 */
	public void setLodgToDate(String lodgToDate) {
		this.lodgToDate = lodgToDate;
	}

	/**
	 * @return the lodgChkOutTm
	 */
	public String getLodgChkOutTm() {
		return lodgChkOutTm;
	}

	/**
	 * @param lodgChkOutTm
	 *            the lodgChkOutTm to set
	 */
	public void setLodgChkOutTm(String lodgChkOutTm) {
		this.lodgChkOutTm = lodgChkOutTm;
	}

	/**
	 * @return the lodgAddrs
	 */
	public String getLodgAddrs() {
		return lodgAddrs;
	}

	/**
	 * @param lodgAddrs
	 *            the lodgAddrs to set
	 */
	public void setLodgAddrs(String lodgAddrs) {
		this.lodgAddrs = lodgAddrs;
	}

	/**
	 * @return the lodgBokAmt
	 */
	public String getLodgBokAmt() {
		return lodgBokAmt;
	}

	/**
	 * @param lodgBokAmt
	 *            the lodgBokAmt to set
	 */
	public void setLodgBokAmt(String lodgBokAmt) {
		this.lodgBokAmt = lodgBokAmt;
	}

	/**
	 * @return the lodgBilAmt
	 */
	public String getLodgBilAmt() {
		return lodgBilAmt;
	}

	/**
	 * @param lodgBilAmt
	 *            the lodgBilAmt to set
	 */
	public void setLodgBilAmt(String lodgBilAmt) {
		this.lodgBilAmt = lodgBilAmt;
	}

	/**
	 * @return the lodgeDtlId
	 */
	public String getLodgeDtlId() {
		return lodgeDtlId;
	}

	/**
	 * @param lodgeDtlId
	 *            the lodgeDtlId to set
	 */
	public void setLodgeDtlId(String lodgeDtlId) {
		this.lodgeDtlId = lodgeDtlId;
	}

	/**
	 * @return the lodgCity
	 */
	public String getLodgCity() {
		return lodgCity;
	}

	/**
	 * @param lodgCity
	 *            the lodgCity to set
	 */
	public void setLodgCity(String lodgCity) {
		this.lodgCity = lodgCity;
	}

	/**
	 * @return the lodgHotel
	 */
	public String getLodgHotel() {
		return lodgHotel;
	}

	/**
	 * @param lodgHotel
	 *            the lodgHotel to set
	 */
	public void setLodgHotel(String lodgHotel) {
		this.lodgHotel = lodgHotel;
	}

	/**
	 * @return the lodgRoom
	 */
	public String getLodgRoom() {
		return lodgRoom;
	}

	/**
	 * @param lodgRoom
	 *            the lodgRoom to set
	 */
	public void setLodgRoom(String lodgRoom) {
		this.lodgRoom = lodgRoom;
	}

	/**
	 * @return the lodgUploadFileName
	 */
	public String getLodgUploadFileName() {
		return lodgUploadFileName;
	}

	/**
	 * @param lodgUploadFileName
	 *            the lodgUploadFileName to set
	 */
	public void setLodgUploadFileName(String lodgUploadFileName) {
		this.lodgUploadFileName = lodgUploadFileName;
	}

	/**
	 * @return the totTrvCost
	 */
	public String getTotTrvCost() {
		return totTrvCost;
	}

	/**
	 * @param totTrvCost
	 *            the totTrvCost to set
	 */
	public void setTotTrvCost(String totTrvCost) {
		this.totTrvCost = totTrvCost;
	}

	/**
	 * @return the totTariffCost
	 */
	public String getTotTariffCost() {
		return totTariffCost;
	}

	/**
	 * @param totTariffCost
	 *            the totTariffCost to set
	 */
	public void setTotTariffCost(String totTariffCost) {
		this.totTariffCost = totTariffCost;
	}

	/**
	 * @return the totLodgCost
	 */
	public String getTotLodgCost() {
		return totLodgCost;
	}

	/**
	 * @param totLodgCost
	 *            the totLodgCost to set
	 */
	public void setTotLodgCost(String totLodgCost) {
		this.totLodgCost = totLodgCost;
	}

	/**
	 * @return the decFlag
	 */
	public String getDecFlag() {
		return decFlag;
	}

	/**
	 * @param decFlag
	 *            the decFlag to set
	 */
	public void setDecFlag(String decFlag) {
		this.decFlag = decFlag;
	}

	/**
	 * @return the savingStatus
	 */
	public String getSavingStatus() {
		return savingStatus;
	}

	/**
	 * @param savingStatus
	 *            the savingStatus to set
	 */
	public void setSavingStatus(String savingStatus) {
		this.savingStatus = savingStatus;
	}

	/**
	 * @return the schApprComment
	 */
	public String getSchApprComment() {
		return schApprComment;
	}

	/**
	 * @param schApprComment
	 *            the schApprComment to set
	 */
	public void setSchApprComment(String schApprComment) {
		this.schApprComment = schApprComment;
	}

	/**
	 * @return the schReqAmount
	 */
	public String getSchReqAmount() {
		return schReqAmount;
	}

	/**
	 * @param schReqAmount
	 *            the schReqAmount to set
	 */
	public void setSchReqAmount(String schReqAmount) {
		this.schReqAmount = schReqAmount;
	}

	/**
	 * @return the trAppId
	 */
	public String getTrAppId() {
		return trAppId;
	}

	/**
	 * @param trAppId the trAppId to set
	 */
	public void setTrAppId(String trAppId) {
		this.trAppId = trAppId;
	}

	/**
	 * @return the policyId
	 */
	public String getPolicyId() {
		return policyId;
	}

	/**
	 * @param policyId the policyId to set
	 */
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	/**
	 * @return the validTrMode
	 */
	public String getValidTrMode() {
		return validTrMode;
	}

	/**
	 * @param validTrMode the validTrMode to set
	 */
	public void setValidTrMode(String validTrMode) {
		this.validTrMode = validTrMode;
	}

	/**
	 * @return the validHotelType
	 */
	public String getValidHotelType() {
		return validHotelType;
	}

	/**
	 * @param validHotelType the validHotelType to set
	 */
	public void setValidHotelType(String validHotelType) {
		this.validHotelType = validHotelType;
	}

	/**
	 * @return the validRoomType
	 */
	public String getValidRoomType() {
		return validRoomType;
	}

	/**
	 * @param validRoomType the validRoomType to set
	 */
	public void setValidRoomType(String validRoomType) {
		this.validRoomType = validRoomType;
	}

	/**
	 * @return the gradeId
	 */
	public String getGradeId() {
		return gradeId;
	}

	/**
	 * @param gradeId the gradeId to set
	 */
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * @return the trvlRequest
	 */
	public String getTrvlRequest() {
		return trvlRequest;
	}

	/**
	 * @param trvlRequest the trvlRequest to set
	 */
	public void setTrvlRequest(String trvlRequest) {
		this.trvlRequest = trvlRequest;
	}

	/**
	 * @return the tourStrtDate
	 */
	public String getTourStrtDate() {
		return tourStrtDate;
	}

	/**
	 * @param tourStrtDate the tourStrtDate to set
	 */
	public void setTourStrtDate(String tourStrtDate) {
		this.tourStrtDate = tourStrtDate;
	}

	/**
	 * @return the tourEndDate
	 */
	public String getTourEndDate() {
		return tourEndDate;
	}

	/**
	 * @param tourEndDate the tourEndDate to set
	 */
	public void setTourEndDate(String tourEndDate) {
		this.tourEndDate = tourEndDate;
	}

	/**
	 * @return the tourPurpose
	 */
	public String getTourPurpose() {
		return tourPurpose;
	}

	/**
	 * @param tourPurpose the tourPurpose to set
	 */
	public void setTourPurpose(String tourPurpose) {
		this.tourPurpose = tourPurpose;
	}

	public String getForwardFlag() {
		return forwardFlag;
	}

	public void setForwardFlag(String forwardFlag) {
		this.forwardFlag = forwardFlag;
	}

}