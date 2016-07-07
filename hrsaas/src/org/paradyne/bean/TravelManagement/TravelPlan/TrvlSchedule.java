package org.paradyne.bean.TravelManagement.TravelPlan;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * 
 * @author krishna
 * 
 */

public class TrvlSchedule extends BeanBase {

	// for saving or finalize

	private String saveStatusFlag = "U";

	// control flags
	private boolean noData = false;

	private String pen = "";
	private String schTr = "";
	private String schAppr = "";
	private String schReg = "";
	private String schCan = "";
	private String schCanceled = "";

	// for paging
	private String hdPage = "";
	private String fromTotRec = "";
	private String toTotRec = "";

	// control flags for iterator

	private boolean penFlag = false;
	private boolean trvlFlag = false;
	private boolean apprvdFlag = false;
	private boolean regctedFlag = false;
	private boolean cancelFlag = false;
	private boolean canceledFlag = false;

	// control flags for showing iterators
	private boolean jourBkFlg = false;
	private boolean localConFlg = false;
	private boolean lodgFlg = false;

	// for iterator
	ArrayList trvlSchList = new ArrayList();

	// variable for passing to next form
	private String trvAppId = "";
	private String appID = "";

	private String expEmpToken = "";
	private String stat = "";
	private String status = "";
	private String comment = "";
	private String rowId = "";

	private String schAdvAmt = "";
	private String reqAdvAmt = "";
	private String schPayMode = "";
	private String schComment = "";

	// for iterator

	private String empName = "";
	private String trvlReqName = "";
	private String trvlDate = "";
	private String locType = "";
	private String trvlArgmt = "";
	private String trvlAccom = "";
	private String trvlAdv = "";
	private String EmployeeId = "";
	// for showing Travel Policy
	private String travelAppId = "";
	private String trAppId = "";
	private String empGrade="";
	private String grade="";
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

	// for cancel form

	private String trvCancAmt = "0";
	private String locCancAmt = "0";
	private String lodgCancAmt = "0";

	private String totTrvCanCost = "0";
	private String totTariffCanCost = "0";
	private String totLodgCanCost = "0";

	private String travelCost = "0";
	private String localCost = "0";
	private String lodgingCost = "0";

	private String travelCancelAmt = "0";
	private String localCancelAmt = "0";
	private String lodgeCancelAmt = "0";

	private String trvlRefund = "0";
	private String localRefund = "0";
	private String lodgeRefund = "0";

	private String totalSchedCost = "0";
	private String totalRefundCost = "0";

	private String trvApprCmts = "";
	private String canCmts = "";
	
	
	
	//for Travel Mode ,Hotel and Room Type validations
	private String policyId="";
	private String validTrMode="";
	private String validHotelType="";
	private String validRoomType="";
	
	// for alter scheduler
	private String altSchEmpId="";
	private String forwrdFlag="true";
	private String pendingFlag="false";
	
	
	
	//flag for rescheduling
	private boolean chkRejFlg=false;

	/**
	 * @return the canCmts
	 */
	public String getCanCmts() {
		return canCmts;
	}

	/**
	 * @param canCmts
	 *            the canCmts to set
	 */
	public void setCanCmts(String canCmts) {
		this.canCmts = canCmts;
	}

	/**
	 * @return the trvApprCmts
	 */
	public String getTrvApprCmts() {
		return trvApprCmts;
	}

	/**
	 * @param trvApprCmts
	 *            the trvApprCmts to set
	 */
	public void setTrvApprCmts(String trvApprCmts) {
		this.trvApprCmts = trvApprCmts;
	}

	/**
	 * @return the totalSchedCost
	 */
	public String getTotalSchedCost() {
		return totalSchedCost;
	}

	/**
	 * @param totalSchedCost
	 *            the totalSchedCost to set
	 */
	public void setTotalSchedCost(String totalSchedCost) {
		this.totalSchedCost = totalSchedCost;
	}

	/**
	 * @return the totalRefundCost
	 */
	public String getTotalRefundCost() {
		return totalRefundCost;
	}

	/**
	 * @param totalRefundCost
	 *            the totalRefundCost to set
	 */
	public void setTotalRefundCost(String totalRefundCost) {
		this.totalRefundCost = totalRefundCost;
	}

	public String getEmployeeId() {
		return EmployeeId;
	}

	/**
	 * @return the trvCancAmt
	 */
	public String getTrvCancAmt() {
		return trvCancAmt;
	}

	/**
	 * @param trvCancAmt
	 *            the trvCancAmt to set
	 */
	public void setTrvCancAmt(String trvCancAmt) {
		this.trvCancAmt = trvCancAmt;
	}

	/**
	 * @return the locCancAmt
	 */
	public String getLocCancAmt() {
		return locCancAmt;
	}

	/**
	 * @param locCancAmt
	 *            the locCancAmt to set
	 */
	public void setLocCancAmt(String locCancAmt) {
		this.locCancAmt = locCancAmt;
	}

	/**
	 * @return the lodgCancAmt
	 */
	public String getLodgCancAmt() {
		return lodgCancAmt;
	}

	/**
	 * @param lodgCancAmt
	 *            the lodgCancAmt to set
	 */
	public void setLodgCancAmt(String lodgCancAmt) {
		this.lodgCancAmt = lodgCancAmt;
	}

	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}

	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	public String getTrvlArgmt() {
		return trvlArgmt;
	}

	public void setTrvlArgmt(String trvlArgmt) {
		this.trvlArgmt = trvlArgmt;
	}

	public String getTrvlAccom() {
		return trvlAccom;
	}

	public void setTrvlAccom(String trvlAccom) {
		this.trvlAccom = trvlAccom;
	}

	public String getTrvlAdv() {
		return trvlAdv;
	}

	public void setTrvlAdv(String trvlAdv) {
		this.trvlAdv = trvlAdv;
	}

	public String getTrvAppId() {
		return trvAppId;
	}

	public void setTrvAppId(String trvAppId) {
		this.trvAppId = trvAppId;
	}

	/**
	 * @return the trvlSchList
	 */
	public ArrayList getTrvlSchList() {
		return trvlSchList;
	}

	/**
	 * @param trvlSchList
	 *            the trvlSchList to set
	 */
	public void setTrvlSchList(ArrayList trvlSchList) {
		this.trvlSchList = trvlSchList;
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

	/**
	 * @return the expEmpToken
	 */
	public String getExpEmpToken() {
		return expEmpToken;
	}

	/**
	 * @param expEmpToken
	 *            the expEmpToken to set
	 */
	public void setExpEmpToken(String expEmpToken) {
		this.expEmpToken = expEmpToken;
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
	 * @return the trvlReqName
	 */
	public String getTrvlReqName() {
		return trvlReqName;
	}

	/**
	 * @param trvlReqName
	 *            the trvlReqName to set
	 */
	public void setTrvlReqName(String trvlReqName) {
		this.trvlReqName = trvlReqName;
	}

	/**
	 * @return the trvlDate
	 */
	public String getTrvlDate() {
		return trvlDate;
	}

	/**
	 * @param trvlDate
	 *            the trvlDate to set
	 */
	public void setTrvlDate(String trvlDate) {
		this.trvlDate = trvlDate;
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
	 * @return the penFlag
	 */
	public boolean isPenFlag() {
		return penFlag;
	}

	/**
	 * @param penFlag
	 *            the penFlag to set
	 */
	public void setPenFlag(boolean penFlag) {
		this.penFlag = penFlag;
	}

	/**
	 * @return the apprvdFlag
	 */

	/**
	 * @return the regctedFlag
	 */
	public boolean isRegctedFlag() {
		return regctedFlag;
	}

	/**
	 * @param regctedFlag
	 *            the regctedFlag to set
	 */
	public void setRegctedFlag(boolean regctedFlag) {
		this.regctedFlag = regctedFlag;
	}

	/**
	 * @return the apprvdFlag
	 */
	public boolean isApprvdFlag() {
		return apprvdFlag;
	}

	/**
	 * @param apprvdFlag
	 *            the apprvdFlag to set
	 */
	public void setApprvdFlag(boolean apprvdFlag) {
		this.apprvdFlag = apprvdFlag;
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

	public String getSchTr() {
		return schTr;
	}

	public void setSchTr(String schTr) {
		this.schTr = schTr;
	}

	public String getSchAppr() {
		return schAppr;
	}

	public void setSchAppr(String schAppr) {
		this.schAppr = schAppr;
	}

	public String getSchReg() {
		return schReg;
	}

	public void setSchReg(String schReg) {
		this.schReg = schReg;
	}

	public String getSchCan() {
		return schCan;
	}

	public void setSchCan(String schCan) {
		this.schCan = schCan;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
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
	 * @return the trvlFlag
	 */
	public boolean isTrvlFlag() {
		return trvlFlag;
	}

	/**
	 * @param trvlFlag
	 *            the trvlFlag to set
	 */
	public void setTrvlFlag(boolean trvlFlag) {
		this.trvlFlag = trvlFlag;
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
	 * @return the saveStatusFlag
	 */
	public String getSaveStatusFlag() {
		return saveStatusFlag;
	}

	/**
	 * @param saveStatusFlag
	 *            the saveStatusFlag to set
	 */
	public void setSaveStatusFlag(String saveStatusFlag) {
		this.saveStatusFlag = saveStatusFlag;
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
	 * @return the cancelFlag
	 */
	public boolean isCancelFlag() {
		return cancelFlag;
	}

	/**
	 * @param cancelFlag
	 *            the cancelFlag to set
	 */
	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	/**
	 * @return the totTrvCanCost
	 */
	public String getTotTrvCanCost() {
		return totTrvCanCost;
	}

	/**
	 * @param totTrvCanCost
	 *            the totTrvCanCost to set
	 */
	public void setTotTrvCanCost(String totTrvCanCost) {
		this.totTrvCanCost = totTrvCanCost;
	}

	/**
	 * @return the totTariffCanCost
	 */
	public String getTotTariffCanCost() {
		return totTariffCanCost;
	}

	/**
	 * @param totTariffCanCost
	 *            the totTariffCanCost to set
	 */
	public void setTotTariffCanCost(String totTariffCanCost) {
		this.totTariffCanCost = totTariffCanCost;
	}

	/**
	 * @return the totLodgCanCost
	 */
	public String getTotLodgCanCost() {
		return totLodgCanCost;
	}

	/**
	 * @param totLodgCanCost
	 *            the totLodgCanCost to set
	 */
	public void setTotLodgCanCost(String totLodgCanCost) {
		this.totLodgCanCost = totLodgCanCost;
	}

	/**
	 * @return the travelCancelAmt
	 */
	public String getTravelCancelAmt() {
		return travelCancelAmt;
	}

	/**
	 * @param travelCancelAmt
	 *            the travelCancelAmt to set
	 */
	public void setTravelCancelAmt(String travelCancelAmt) {
		this.travelCancelAmt = travelCancelAmt;
	}

	/**
	 * @return the localCancelAmt
	 */
	public String getLocalCancelAmt() {
		return localCancelAmt;
	}

	/**
	 * @param localCancelAmt
	 *            the localCancelAmt to set
	 */
	public void setLocalCancelAmt(String localCancelAmt) {
		this.localCancelAmt = localCancelAmt;
	}

	/**
	 * @return the lodgeCancelAmt
	 */
	public String getLodgeCancelAmt() {
		return lodgeCancelAmt;
	}

	/**
	 * @param lodgeCancelAmt
	 *            the lodgeCancelAmt to set
	 */
	public void setLodgeCancelAmt(String lodgeCancelAmt) {
		this.lodgeCancelAmt = lodgeCancelAmt;
	}

	/**
	 * @return the trvlRefund
	 */
	public String getTrvlRefund() {
		return trvlRefund;
	}

	/**
	 * @param trvlRefund
	 *            the trvlRefund to set
	 */
	public void setTrvlRefund(String trvlRefund) {
		this.trvlRefund = trvlRefund;
	}

	/**
	 * @return the localRefund
	 */
	public String getLocalRefund() {
		return localRefund;
	}

	/**
	 * @param localRefund
	 *            the localRefund to set
	 */
	public void setLocalRefund(String localRefund) {
		this.localRefund = localRefund;
	}

	/**
	 * @return the lodgeRefund
	 */
	public String getLodgeRefund() {
		return lodgeRefund;
	}

	/**
	 * @param lodgeRefund
	 *            the lodgeRefund to set
	 */
	public void setLodgeRefund(String lodgeRefund) {
		this.lodgeRefund = lodgeRefund;
	}

	/**
	 * @return the schCanceled
	 */
	public String getSchCanceled() {
		return schCanceled;
	}

	/**
	 * @param schCanceled
	 *            the schCanceled to set
	 */
	public void setSchCanceled(String schCanceled) {
		this.schCanceled = schCanceled;
	}

	/**
	 * @return the canceledFlag
	 */
	public boolean isCanceledFlag() {
		return canceledFlag;
	}

	/**
	 * @param canceledFlag
	 *            the canceledFlag to set
	 */
	public void setCanceledFlag(boolean canceledFlag) {
		this.canceledFlag = canceledFlag;
	}

	/**
	 * @return the travelCost
	 */
	public String getTravelCost() {
		return travelCost;
	}

	/**
	 * @param travelCost
	 *            the travelCost to set
	 */
	public void setTravelCost(String travelCost) {
		this.travelCost = travelCost;
	}

	/**
	 * @return the localCost
	 */
	public String getLocalCost() {
		return localCost;
	}

	/**
	 * @param localCost
	 *            the localCost to set
	 */
	public void setLocalCost(String localCost) {
		this.localCost = localCost;
	}

	/**
	 * @return the lodgingCost
	 */
	public String getLodgingCost() {
		return lodgingCost;
	}

	/**
	 * @param lodgingCost
	 *            the lodgingCost to set
	 */
	public void setLodgingCost(String lodgingCost) {
		this.lodgingCost = lodgingCost;
	}

	/**
	 * @return the reqAdvAmt
	 */
	public String getReqAdvAmt() {
		return reqAdvAmt;
	}

	/**
	 * @param reqAdvAmt
	 *            the reqAdvAmt to set
	 */
	public void setReqAdvAmt(String reqAdvAmt) {
		this.reqAdvAmt = reqAdvAmt;
	}

	/**
	 * @return the trAppId
	 */
	public String getTrAppId() {
		return trAppId;
	}

	/**
	 * @param trAppId
	 *            the trAppId to set
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

	/**
	 * @return the chkRejFlg
	 */
	public boolean isChkRejFlg() {
		return chkRejFlg;
	}

	/**
	 * @param chkRejFlg the chkRejFlg to set
	 */
	public void setChkRejFlg(boolean chkRejFlg) {
		this.chkRejFlg = chkRejFlg;
	}

	/**
	 * @return the empGrade
	 */
	public String getEmpGrade() {
		return empGrade;
	}

	/**
	 * @param empGrade the empGrade to set
	 */
	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAltSchEmpId() {
		return altSchEmpId;
	}

	public void setAltSchEmpId(String altSchEmpId) {
		this.altSchEmpId = altSchEmpId;
	}

	public String getForwrdFlag() {
		return forwrdFlag;
	}

	public void setForwrdFlag(String forwrdFlag) {
		this.forwrdFlag = forwrdFlag;
	}

	public String getPendingFlag() {
		return pendingFlag;
	}

	public void setPendingFlag(String pendingFlag) {
		this.pendingFlag = pendingFlag;
	}

}
