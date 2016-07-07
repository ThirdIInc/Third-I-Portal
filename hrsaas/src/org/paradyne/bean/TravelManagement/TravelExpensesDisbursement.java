package org.paradyne.bean.TravelManagement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelExpensesDisbursement extends BeanBase {

	private String trvlExpId = "";
	
	private String expEmpId = "";
	private String expEmpToken = "";	
	private String empName = "";
	private String trvlReqName = "";
	private String expAppDate = "";
	private String expAdvAmt = "";
	private String expExpnsAmt = "";

	private String stat = "";
	//private String noData = "";
	private boolean noData=false;

	private String pen = "";
	private String bal = "";
	private String full = "";
	// control flags for iterator

	private boolean penFlag = false;
	private boolean balFlag = false;
	private boolean fullFlag = false;

	// field to pass data to second form

	private String travelExpense = "";

	// for iterator
	ArrayList travelDtlList = new ArrayList();
	// for paging
	private String hdPage = "";
	private String fromTotRec = "";
	private String toTotRec = "";

	// Expense Payment Details ====Employee Information

	
	private String empId = "";
	private String empToken = "";
	private String employeeName = "";
	private String brnchName = "";
	private String deptName = "";
	private String desgName = "";
	private String applDate = "";
	private String statusFld = "";
	private String grdName = "";

	private String ApprExpAmt = "0";
	private String lessAdvAmt = "0";
	private String lessAnyAmt = "0";
	private String totDisbAmt = "0";
	private String entDisbAmt = "0";
	private String balAmt = "0";
	private String prefModPay = "";
	private String payDate = "";
	private String modPayment = "";
	private String bank = "";

	private String checkNo = "";
	private String checkDate = "";
	private String expMon = "";
	private String expYr = "";
	private String comment = "";

	//for navigation
	
	private String navStatus="";
	//for second form
	private String expDisbId="";
	private String travelAppId="";
	private String AppId="";
	private String expAppId="";
	
	
	
	//for Balance form
	private String balId="";
	private String balApprAmt="0";
	private String balLessAdvAmt="0";
	private String balLessAnyAmt="0";
	private String balPiadAmt="0";
	private String balTotDisbAmt="0";
	private String balEntDisbAmt="0";
	private String balanceAmt="0";
	private String balPrefModPay="";
	private String balPayDate="";
	private String balModPayment="";
	private String balBank="";
	private String balCheckNo="";
	private String balCheckDate="";	
	private String balMon="";
	private String balYr="";
	private String balComment="";
	
	
	//for full payment details
	
	private String fullAppExpAmt="";
	private String fullAdvAmt="";
	private String fullAnyDedAmt="";
//---iterator 
	
	private String fullPaidAmt="";
	private String fullPaidDate="";
	private String fullPaidMode="";
	ArrayList trvlList =null;
	
	
	
	
	/**
	 * @return the apprExpAmt
	 */
	public String getApprExpAmt() {
		return ApprExpAmt;
	}

	/**
	 * @param apprExpAmt
	 *            the apprExpAmt to set
	 */
	public void setApprExpAmt(String apprExpAmt) {
		ApprExpAmt = apprExpAmt;
	}

	/**
	 * @return the lessAdvAmt
	 */
	public String getLessAdvAmt() {
		return lessAdvAmt;
	}

	/**
	 * @param lessAdvAmt
	 *            the lessAdvAmt to set
	 */
	public void setLessAdvAmt(String lessAdvAmt) {
		this.lessAdvAmt = lessAdvAmt;
	}

	/**
	 * @return the lessAnyAmt
	 */
	public String getLessAnyAmt() {
		return lessAnyAmt;
	}

	/**
	 * @param lessAnyAmt
	 *            the lessAnyAmt to set
	 */
	public void setLessAnyAmt(String lessAnyAmt) {
		this.lessAnyAmt = lessAnyAmt;
	}

	/**
	 * @return the totDisbAmt
	 */
	public String getTotDisbAmt() {
		return totDisbAmt;
	}

	/**
	 * @param totDisbAmt
	 *            the totDisbAmt to set
	 */
	public void setTotDisbAmt(String totDisbAmt) {
		this.totDisbAmt = totDisbAmt;
	}

	/**
	 * @return the entDisbAmt
	 */
	public String getEntDisbAmt() {
		return entDisbAmt;
	}

	/**
	 * @param entDisbAmt
	 *            the entDisbAmt to set
	 */
	public void setEntDisbAmt(String entDisbAmt) {
		this.entDisbAmt = entDisbAmt;
	}

	/**
	 * @return the balAmt
	 */
	public String getBalAmt() {
		return balAmt;
	}

	/**
	 * @param balAmt
	 *            the balAmt to set
	 */
	public void setBalAmt(String balAmt) {
		this.balAmt = balAmt;
	}

	/**
	 * @return the prefModPay
	 */
	public String getPrefModPay() {
		return prefModPay;
	}

	/**
	 * @param prefModPay
	 *            the prefModPay to set
	 */
	public void setPrefModPay(String prefModPay) {
		this.prefModPay = prefModPay;
	}

	/**
	 * @return the payDate
	 */
	public String getPayDate() {
		return payDate;
	}

	/**
	 * @param payDate
	 *            the payDate to set
	 */
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	/**
	 * @return the modPayment
	 */
	public String getModPayment() {
		return modPayment;
	}

	/**
	 * @param modPayment
	 *            the modPayment to set
	 */
	public void setModPayment(String modPayment) {
		this.modPayment = modPayment;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the checkNo
	 */
	public String getCheckNo() {
		return checkNo;
	}

	/**
	 * @param checkNo
	 *            the checkNo to set
	 */
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	/**
	 * @return the checkDate
	 */
	public String getCheckDate() {
		return checkDate;
	}

	/**
	 * @param checkDate
	 *            the checkDate to set
	 */
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * @return the expMon
	 */
	public String getExpMon() {
		return expMon;
	}

	/**
	 * @param expMon
	 *            the expMon to set
	 */
	public void setExpMon(String expMon) {
		this.expMon = expMon;
	}

	/**
	 * @return the expYr
	 */
	public String getExpYr() {
		return expYr;
	}

	/**
	 * @param expYr
	 *            the expYr to set
	 */
	public void setExpYr(String expYr) {
		this.expYr = expYr;
	}

	public String getStatusFld() {
		return statusFld;
	}

	public void setStatusFld(String statusFld) {
		this.statusFld = statusFld;
	}

	public String getGrdName() {
		return grdName;
	}

	public void setGrdName(String grdName) {
		this.grdName = grdName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getApplDate() {
		return applDate;
	}

	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	public String getExpEmpId() {
		return expEmpId;
	}

	public void setExpEmpId(String expEmpId) {
		this.expEmpId = expEmpId;
	}

	/**
	 * @return the trvlExpId
	 */
	public String getTrvlExpId() {
		return trvlExpId;
	}

	/**
	 * @param trvlExpId
	 *            the trvlExpId to set
	 */
	public void setTrvlExpId(String trvlExpId) {
		this.trvlExpId = trvlExpId;
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

	public String getExpAdvAmt() {
		return expAdvAmt;
	}

	public void setExpAdvAmt(String expAdvAmt) {
		this.expAdvAmt = expAdvAmt;
	}

	public String getExpExpnsAmt() {
		return expExpnsAmt;
	}

	public void setExpExpnsAmt(String expExpnsAmt) {
		this.expExpnsAmt = expExpnsAmt;
	}

	/**
	 * @return the travelDtlList
	 */
	public ArrayList getTravelDtlList() {
		return travelDtlList;
	}

	/**
	 * @param travelDtlList
	 *            the travelDtlList to set
	 */
	public void setTravelDtlList(ArrayList travelDtlList) {
		this.travelDtlList = travelDtlList;
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
	 * @param stat the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
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
	 * @return the expAppDate
	 */
	public String getExpAppDate() {
		return expAppDate;
	}

	/**
	 * @param expAppDate
	 *            the expAppDate to set
	 */
	public void setExpAppDate(String expAppDate) {
		this.expAppDate = expAppDate;
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
	 * @return the bal
	 */
	public String getBal() {
		return bal;
	}

	/**
	 * @param bal
	 *            the bal to set
	 */
	public void setBal(String bal) {
		this.bal = bal;
	}

	/**
	 * @return the full
	 */
	public String getFull() {
		return full;
	}

	/**
	 * @param full
	 *            the full to set
	 */
	public void setFull(String full) {
		this.full = full;
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
	 * @return the balFlag
	 */
	public boolean isBalFlag() {
		return balFlag;
	}

	/**
	 * @param balFlag
	 *            the balFlag to set
	 */
	public void setBalFlag(boolean balFlag) {
		this.balFlag = balFlag;
	}

	/**
	 * @return the fullFlag
	 */
	public boolean isFullFlag() {
		return fullFlag;
	}

	/**
	 * @param fullFlag
	 *            the fullFlag to set
	 */
	public void setFullFlag(boolean fullFlag) {
		this.fullFlag = fullFlag;
	}

	public String getTravelExpense() {
		return travelExpense;
	}

	public void setTravelExpense(String travelExpense) {
		this.travelExpense = travelExpense;
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

	public String getNavStatus() {
		return navStatus;
	}

	public void setNavStatus(String navStatus) {
		this.navStatus = navStatus;
	}

	public String getExpDisbId() {
		return expDisbId;
	}

	public void setExpDisbId(String expDisbId) {
		this.expDisbId = expDisbId;
	}

	public String getExpAppId() {
		return expAppId;
	}

	public void setExpAppId(String expAppId) {
		this.expAppId = expAppId;
	}

	public String getTravelAppId() {
		return travelAppId;
	}

	public void setTravelAppId(String travelAppId) {
		this.travelAppId = travelAppId;
	}

	public String getAppId() {
		return AppId;
	}

	public void setAppId(String appId) {
		AppId = appId;
	}

	public String getBalId() {
		return balId;
	}

	public void setBalId(String balId) {
		this.balId = balId;
	}

	public String getBalApprAmt() {
		return balApprAmt;
	}

	public void setBalApprAmt(String balApprAmt) {
		this.balApprAmt = balApprAmt;
	}

	public String getBalLessAdvAmt() {
		return balLessAdvAmt;
	}

	public void setBalLessAdvAmt(String balLessAdvAmt) {
		this.balLessAdvAmt = balLessAdvAmt;
	}

	public String getBalLessAnyAmt() {
		return balLessAnyAmt;
	}

	public void setBalLessAnyAmt(String balLessAnyAmt) {
		this.balLessAnyAmt = balLessAnyAmt;
	}

	public String getBalTotDisbAmt() {
		return balTotDisbAmt;
	}

	public void setBalTotDisbAmt(String balTotDisbAmt) {
		this.balTotDisbAmt = balTotDisbAmt;
	}

	public String getBalEntDisbAmt() {
		return balEntDisbAmt;
	}

	public void setBalEntDisbAmt(String balEntDisbAmt) {
		this.balEntDisbAmt = balEntDisbAmt;
	}

	public String getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(String balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public String getBalPrefModPay() {
		return balPrefModPay;
	}

	public void setBalPrefModPay(String balPrefModPay) {
		this.balPrefModPay = balPrefModPay;
	}

	public String getBalPayDate() {
		return balPayDate;
	}

	public void setBalPayDate(String balPayDate) {
		this.balPayDate = balPayDate;
	}

	public String getBalModPayment() {
		return balModPayment;
	}

	public void setBalModPayment(String balModPayment) {
		this.balModPayment = balModPayment;
	}

	public String getBalBank() {
		return balBank;
	}

	public void setBalBank(String balBank) {
		this.balBank = balBank;
	}

	public String getBalCheckNo() {
		return balCheckNo;
	}

	public void setBalCheckNo(String balCheckNo) {
		this.balCheckNo = balCheckNo;
	}

	public String getBalMon() {
		return balMon;
	}

	public void setBalMon(String balMon) {
		this.balMon = balMon;
	}

	public String getBalYr() {
		return balYr;
	}

	public void setBalYr(String balYr) {
		this.balYr = balYr;
	}

	public String getBalPiadAmt() {
		return balPiadAmt;
	}

	public void setBalPiadAmt(String balPiadAmt) {
		this.balPiadAmt = balPiadAmt;
	}

	public String getBalCheckDate() {
		return balCheckDate;
	}

	public void setBalCheckDate(String balCheckDate) {
		this.balCheckDate = balCheckDate;
	}

	public String getBalComment() {
		return balComment;
	}

	public void setBalComment(String balComment) {
		this.balComment = balComment;
	}

	public String getFullAppExpAmt() {
		return fullAppExpAmt;
	}

	public void setFullAppExpAmt(String fullAppExpAmt) {
		this.fullAppExpAmt = fullAppExpAmt;
	}

	public String getFullAdvAmt() {
		return fullAdvAmt;
	}

	public void setFullAdvAmt(String fullAdvAmt) {
		this.fullAdvAmt = fullAdvAmt;
	}

	public String getFullAnyDedAmt() {
		return fullAnyDedAmt;
	}

	public void setFullAnyDedAmt(String fullAnyDedAmt) {
		this.fullAnyDedAmt = fullAnyDedAmt;
	}

	public String getFullPaidAmt() {
		return fullPaidAmt;
	}

	public void setFullPaidAmt(String fullPaidAmt) {
		this.fullPaidAmt = fullPaidAmt;
	}

	public String getFullPaidDate() {
		return fullPaidDate;
	}

	public void setFullPaidDate(String fullPaidDate) {
		this.fullPaidDate = fullPaidDate;
	}

	public String getFullPaidMode() {
		return fullPaidMode;
	}

	public void setFullPaidMode(String fullPaidMode) {
		this.fullPaidMode = fullPaidMode;
	}

	public ArrayList getTrvlList() {
		return trvlList;
	}

	public void setTrvlList(ArrayList trvlList) {
		this.trvlList = trvlList;
	}

	public boolean isNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}

}
