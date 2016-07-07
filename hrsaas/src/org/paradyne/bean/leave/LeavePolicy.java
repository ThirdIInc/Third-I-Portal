/**
 * 
 */
package org.paradyne.bean.leave;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;
/**
 * @author lakkichand and Pradeep
 *
 */
public class LeavePolicy extends BeanBase{
	private String empID="";
	private String typeID="";
	private String empType="";
	private String leaveCode="";
	private String leaveName="";
	private String creditInterval="";
	private String quantumEntitled="";
	private String maxLeaveBal="";
	private String encashable="";
	private String effOnServPeriod="";
	private String carryForword="";
	private String leaveRecovery="";
	private String applicable="";
	private String maxbalance="";
	private String entitle="";
	private String chkFlag="";
	private boolean chkFlag1;
	private boolean chkHoliday;
	private boolean weekOff;
	private boolean leaveCons;
	private String maxLimit="";
	private String encashFormula="";	
	private String chkFlag2="";
	private String holiDay="";
	private String conse="";
	private String weekoff="";
	private boolean zeroBal;
	private boolean advLeave;
	private String minBalance="";
	private String hLeaveCode="";
	private String frmId="";
	private String hiddenfrmId="";
	private String levid="";
	private String levname="";
	private String flag="false";
	private String maxLevsInMonth="";
	private String divisionName="";
	private String divisionCode="";
	private String  policyCode="";
	private String  policyName="";
	private String leaveApplicableto="";
	private String leaveCreditType="";
	
	
	private String hiddenMonthfrmId ="";
	ArrayList leaveList=null;
	ArrayList arr=null;
	
	/**21 sep 2009
	 * modified by shashikant
	 * Redesign leave policy
	 * @return
	 */
	
	private String unAuthorizedLeaveDay="";
	private String unAuthorizedLeaveStatus="";
	
	private String fromMonth="";
	private String penaltyStatus="";
	private String toMonth="";
	private String hiddenLeaveCode="";
	private String hiddenPenelty="";
	
	private String unAuthorizedDay="";
	private String absentDays="";
	private String unAuthorizedStatus="";
	private ArrayList unplaneArray=null;
	private TreeMap leaveMap=null;
	private String unPlaneLeave="";
	private String applyInAdvance="";
	private String forLeaves="";
	private String toLeaves="";
	private String appliedInAdvance="";
	private String penaltyForUnPlane="";
	private String addRuleField="";
	private String unplaneFlag="";
	private String resetFlag="";
	private String unPlaneHidCode="";
	private String hideenY="";
	private String leaveBalEnableStatus="";
	private String leaveBalanceNotAvail="";
	private String f9Flag="false";
	
	//FOR LEAVE LIST
	private String leaveCodeHid = "";
	private String leaveAbbrHid = "";
	
	private String leaveCodeHidNext = "";
	private String leaveAbbrHidNext = "";
	private String srNo;
	
	private String leaveCodev;
	private String leaveNamev;
	private String leaveAbbrv;
	private String check;
	private ArrayList leaveDataList;
	private String leaveTypeFlag = "false";
	private String checkFlag = "false";
	private String lateMarksLeave="";
	private String lateMarksLeaveCode="";
	
	
	private String joiningDateFlag="";
	private String confirmationDateFlag="";
	
	
	
	
	
/*
 * Added by manish sakpal
 */	
	private String intervalmonthname="";
	private String carryforwardmonth="";
	private String maxLeaveApply = "";
	
	
	
	
	public String getMaxLeaveApply() {
		return maxLeaveApply;
	}
	public void setMaxLeaveApply(String maxLeaveApply) {
		this.maxLeaveApply = maxLeaveApply;
	}
	public String getIntervalmonthname() {
		return intervalmonthname;
	}
	public void setIntervalmonthname(String intervalmonthname) {
		this.intervalmonthname = intervalmonthname;
	}
	public String getJoiningDateFlag() {
		return joiningDateFlag;
	}
	public void setJoiningDateFlag(String joiningDateFlag) {
		this.joiningDateFlag = joiningDateFlag;
	}
	public String getConfirmationDateFlag() {
		return confirmationDateFlag;
	}
	public void setConfirmationDateFlag(String confirmationDateFlag) {
		this.confirmationDateFlag = confirmationDateFlag;
	}
	public String getF9Flag() {
		return f9Flag;
	}
	public void setF9Flag(String flag) {
		f9Flag = flag;
	}
	public String getLeaveBalEnableStatus() {
		return leaveBalEnableStatus;
	}
	public void setLeaveBalEnableStatus(String leaveBalEnableStatus) {
		this.leaveBalEnableStatus = leaveBalEnableStatus;
	}
	public String getLeaveBalanceNotAvail() {
		return leaveBalanceNotAvail;
	}
	public void setLeaveBalanceNotAvail(String leaveBalanceNotAvail) {
		this.leaveBalanceNotAvail = leaveBalanceNotAvail;
	}
	public String getHideenY() {
		return hideenY;
	}
	public void setHideenY(String hideenY) {
		this.hideenY = hideenY;
	}
	public String getUnPlaneHidCode() {
		return unPlaneHidCode;
	}
	public void setUnPlaneHidCode(String unPlaneHidCode) {
		this.unPlaneHidCode = unPlaneHidCode;
	}
	public String getUnplaneFlag() {
		return unplaneFlag;
	}
	public void setUnplaneFlag(String unplaneFlag) {
		this.unplaneFlag = unplaneFlag;
	}
	public String getAddRuleField() {
		return addRuleField;
	}
	public void setAddRuleField(String addRuleField) {
		this.addRuleField = addRuleField;
	}
	public String getForLeaves() {
		return forLeaves;
	}
	public void setForLeaves(String forLeaves) {
		this.forLeaves = forLeaves;
	}
	public String getToLeaves() {
		return toLeaves;
	}
	public void setToLeaves(String toLeaves) {
		this.toLeaves = toLeaves;
	}
	public String getAppliedInAdvance() {
		return appliedInAdvance;
	}
	public void setAppliedInAdvance(String appliedInAdvance) {
		this.appliedInAdvance = appliedInAdvance;
	}
	public String getPenaltyForUnPlane() {
		return penaltyForUnPlane;
	}
	public void setPenaltyForUnPlane(String penaltyForUnPlane) {
		this.penaltyForUnPlane = penaltyForUnPlane;
	}
	public String getApplyInAdvance() {
		return applyInAdvance;
	}
	public void setApplyInAdvance(String applyInAdvance) {
		this.applyInAdvance = applyInAdvance;
	}
	public String getUnPlaneLeave() {
		return unPlaneLeave;
	}
	public void setUnPlaneLeave(String unPlaneLeave) {
		this.unPlaneLeave = unPlaneLeave;
	}
	public String getHiddenPenelty() {
		return hiddenPenelty;
	}
	public void setHiddenPenelty(String hiddenPenelty) {
		this.hiddenPenelty = hiddenPenelty;
	}
	public String getHiddenLeaveCode() {
		return hiddenLeaveCode;
	}
	public void setHiddenLeaveCode(String hiddenLeaveCode) {
		this.hiddenLeaveCode = hiddenLeaveCode;
	}
	public String getLeaveCreditType() {
		return leaveCreditType;
	}
	public void setLeaveCreditType(String leaveCreditType) {
		this.leaveCreditType = leaveCreditType;
	}
	public String getLeaveApplicableto() {
		return leaveApplicableto;
	}
	public void setLeaveApplicableto(String leaveApplicableto) {
		this.leaveApplicableto = leaveApplicableto;
	}
	public String getPolicyCode() {
		return policyCode;
	}
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getMaxLevsInMonth() {
		return maxLevsInMonth;
	}
	public void setMaxLevsInMonth(String maxLevsInMonth) {
		this.maxLevsInMonth = maxLevsInMonth;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMinBalance() {
		return minBalance;
	}
	public void setMinBalance(String minBalance) {
		this.minBalance = minBalance;
	}
	/**
	 * @return the carryForword
	 */
	public String getCarryForword() {
		return carryForword;
	}
	/**
	 * @param carryForword the carryForword to set
	 */
	public void setCarryForword(String carryForword) {
		this.carryForword = carryForword;
	}
	/**
	 * @return the creditInterval
	 */
	public String getCreditInterval() {
		return creditInterval;
	}
	/**
	 * @param creditInterval the creditInterval to set
	 */
	public void setCreditInterval(String creditInterval) {
		this.creditInterval = creditInterval;
	}
	/**
	 * @return the effOnServPeriod
	 */
	public String getEffOnServPeriod() {
		return effOnServPeriod;
	}
	/**
	 * @param effOnServPeriod the effOnServPeriod to set
	 */
	public void setEffOnServPeriod(String effOnServPeriod) {
		this.effOnServPeriod = effOnServPeriod;
	}
	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}
	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	/**
	 * @return the empType
	 */
	public String getEmpType() {
		return empType;
	}
	/**
	 * @param empType the empType to set
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	/**
	 * @return the encashable
	 */
	public String getEncashable() {
		return encashable;
	}
	/**
	 * @param encashable the encashable to set
	 */
	public void setEncashable(String encashable) {
		this.encashable = encashable;
	}
	/**
	 * @return the leaveCode
	 */
	public String getLeaveCode() {
		return leaveCode;
	}
	/**
	 * @param leaveCode the leaveCode to set
	 */
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}
	/**
	 * @return the leaveName
	 */
	public String getLeaveName() {
		return leaveName;
	}
	/**
	 * @param leaveName the leaveName to set
	 */
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	/**
	 * @return the leaveRecovery
	 */
	public String getLeaveRecovery() {
		return leaveRecovery;
	}
	/**
	 * @param leaveRecovery the leaveRecovery to set
	 */
	public void setLeaveRecovery(String leaveRecovery) {
		this.leaveRecovery = leaveRecovery;
	}
	/**
	 * @return the maxLeaveBal
	 */
	public String getMaxLeaveBal() {
		return maxLeaveBal;
	}
	/**
	 * @param maxLeaveBal the maxLeaveBal to set
	 */
	public void setMaxLeaveBal(String maxLeaveBal) {
		this.maxLeaveBal = maxLeaveBal;
	}
	/**
	 * @return the quantumEntitled
	 */
	public String getQuantumEntitled() {
		return quantumEntitled;
	}
	/**
	 * @param quantumEntitled the quantumEntitled to set
	 */
	public void setQuantumEntitled(String quantumEntitled) {
		this.quantumEntitled = quantumEntitled;
	}
	/**
	 * @return the leaveList
	 */
	public ArrayList getLeaveList() {
		return leaveList;
	}
	/**
	 * @param leaveList the leaveList to set
	 */
	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}
	/**
	 * @return the typeID
	 */
	public String getTypeID() {
		return typeID;
	}
	/**
	 * @param typeID the typeID to set
	 */
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	public String getEntitle() {
		return entitle;
	}

	public void setEntitle(String entitle) {
		this.entitle = entitle;
	}

	public String getApplicable() {
		return applicable;
	}

	public void setApplicable(String applicable) {
		this.applicable = applicable;
	}

	public String getMaxbalance() {
		return maxbalance;
	}

	public void setMaxbalance(String maxbalance) {
		this.maxbalance = maxbalance;
	}

	public String getChkFlag() {
		return chkFlag;
	}

	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}

		/*public String getChakFlag1() {
		return chkFlag1;
	}

	public void setChkFlag1(String chkFlag1) {
		this.chkFlag1 = chkFlag1;
	}*/

	public String getChkFlag2() {
		return chkFlag2;
	}

	public void setChkFlag2(String chkFlag2) {
		this.chkFlag2 = chkFlag2;
	}

	public boolean isChkFlag1() {
		return chkFlag1;
	}

	public void setChkFlag1(boolean chkFlag1) {
		this.chkFlag1 = chkFlag1;
	}

	public boolean isChkHoliday() {
		return chkHoliday;
	}

	public void setChkHoliday(boolean chkHoliday) {
		this.chkHoliday = chkHoliday;
	}

	public String getEncashFormula() {
		return encashFormula;
	}

	public void setEncashFormula(String encashFormula) {
		this.encashFormula = encashFormula;
	}

	public boolean isLeaveCons() {
		return leaveCons;
	}

	public void setLeaveCons(boolean leaveCons) {
		this.leaveCons = leaveCons;
	}

	public String getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}

	public boolean isWeekOff() {
		return weekOff;
	}

	public void setWeekOff(boolean weekOff) {
		this.weekOff = weekOff;
	}

	public String getHoliDay() {
		return holiDay;
	}

	public void setHoliDay(String holiDay) {
		this.holiDay = holiDay;
	}

	public String getConse() {
		return conse;
	}

	public void setConse(String conse) {
		this.conse = conse;
	}

	public String getWeekoff() {
		return weekoff;
	}

	public void setWeekoff(String weekoff) {
		this.weekoff = weekoff;
	}
	public boolean isAdvLeave() {
		return advLeave;
	}
	public void setAdvLeave(boolean advLeave) {
		this.advLeave = advLeave;
	}
	public boolean isZeroBal() {
		return zeroBal;
	}
	public void setZeroBal(boolean zeroBal) {
		this.zeroBal = zeroBal;
	}
	public String getLevid() {
		return levid;
	}
	public void setLevid(String levid) {
		this.levid = levid;
	}
	public String getLevname() {
		return levname;
	}
	public void setLevname(String levname) {
		this.levname = levname;
	}
	public ArrayList getArr() {
		return arr;
	}
	public void setArr(ArrayList arr) {
		this.arr = arr;
	}
	public String getFrmId() {
		return frmId;
	}
	public void setFrmId(String frmId) {
		this.frmId = frmId;
	}
	public String getHiddenfrmId() {
		return hiddenfrmId;
	}
	public void setHiddenfrmId(String hiddenfrmId) {
		this.hiddenfrmId = hiddenfrmId;
	}
	public String getHLeaveCode() {
		return hLeaveCode;
	}
	public void setHLeaveCode(String leaveCode) {
		hLeaveCode = leaveCode;
	}
	public String getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	public String getPenaltyStatus() {
		return penaltyStatus;
	}
	public void setPenaltyStatus(String penaltyStatus) {
		this.penaltyStatus = penaltyStatus;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public String getUnAuthorizedDay() {
		return unAuthorizedDay;
	}
	public void setUnAuthorizedDay(String unAuthorizedDay) {
		this.unAuthorizedDay = unAuthorizedDay;
	}
	public String getAbsentDays() {
		return absentDays;
	}
	public void setAbsentDays(String absentDays) {
		this.absentDays = absentDays;
	}
	public String getUnAuthorizedStatus() {
		return unAuthorizedStatus;
	}
	public void setUnAuthorizedStatus(String unAuthorizedStatus) {
		this.unAuthorizedStatus = unAuthorizedStatus;
	}
	public ArrayList getUnplaneArray() {
		return unplaneArray;
	}
	public void setUnplaneArray(ArrayList unplaneArray) {
		this.unplaneArray = unplaneArray;
	}
	public TreeMap getLeaveMap() {
		return leaveMap;
	}
	public void setLeaveMap(TreeMap leaveMap) {
		this.leaveMap = leaveMap;
	}
	public String getResetFlag() {
		return resetFlag;
	}
	public void setResetFlag(String resetFlag) {
		this.resetFlag = resetFlag;
	}
	public String getUnAuthorizedLeaveDay() {
		return unAuthorizedLeaveDay;
	}
	public void setUnAuthorizedLeaveDay(String unAuthorizedLeaveDay) {
		this.unAuthorizedLeaveDay = unAuthorizedLeaveDay;
	}
	public String getUnAuthorizedLeaveStatus() {
		return unAuthorizedLeaveStatus;
	}
	public void setUnAuthorizedLeaveStatus(String unAuthorizedLeaveStatus) {
		this.unAuthorizedLeaveStatus = unAuthorizedLeaveStatus;
	}
	public String getLeaveCodeHid() {
		return leaveCodeHid;
	}
	public void setLeaveCodeHid(String leaveCodeHid) {
		this.leaveCodeHid = leaveCodeHid;
	}
	public String getLeaveAbbrHid() {
		return leaveAbbrHid;
	}
	public void setLeaveAbbrHid(String leaveAbbrHid) {
		this.leaveAbbrHid = leaveAbbrHid;
	}
	public String getLeaveCodeHidNext() {
		return leaveCodeHidNext;
	}
	public void setLeaveCodeHidNext(String leaveCodeHidNext) {
		this.leaveCodeHidNext = leaveCodeHidNext;
	}
	public String getLeaveAbbrHidNext() {
		return leaveAbbrHidNext;
	}
	public void setLeaveAbbrHidNext(String leaveAbbrHidNext) {
		this.leaveAbbrHidNext = leaveAbbrHidNext;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getLeaveCodev() {
		return leaveCodev;
	}
	public void setLeaveCodev(String leaveCodev) {
		this.leaveCodev = leaveCodev;
	}
	public String getLeaveNamev() {
		return leaveNamev;
	}
	public void setLeaveNamev(String leaveNamev) {
		this.leaveNamev = leaveNamev;
	}
	public String getLeaveAbbrv() {
		return leaveAbbrv;
	}
	public void setLeaveAbbrv(String leaveAbbrv) {
		this.leaveAbbrv = leaveAbbrv;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public ArrayList getLeaveDataList() {
		return leaveDataList;
	}
	public void setLeaveDataList(ArrayList leaveDataList) {
		this.leaveDataList = leaveDataList;
	}
	public String getLeaveTypeFlag() {
		return leaveTypeFlag;
	}
	public void setLeaveTypeFlag(String leaveTypeFlag) {
		this.leaveTypeFlag = leaveTypeFlag;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getLateMarksLeave() {
		return lateMarksLeave;
	}
	public void setLateMarksLeave(String lateMarksLeave) {
		this.lateMarksLeave = lateMarksLeave;
	}
	public String getLateMarksLeaveCode() {
		return lateMarksLeaveCode;
	}
	public void setLateMarksLeaveCode(String lateMarksLeaveCode) {
		this.lateMarksLeaveCode = lateMarksLeaveCode;
	}
	public String getHiddenMonthfrmId() {
		return hiddenMonthfrmId;
	}
	public void setHiddenMonthfrmId(String hiddenMonthfrmId) {
		this.hiddenMonthfrmId = hiddenMonthfrmId;
	}
	public String getCarryforwardmonth() {
		return carryforwardmonth;
	}
	public void setCarryforwardmonth(String carryforwardmonth) {
		this.carryforwardmonth = carryforwardmonth;
	}
}
