package org.paradyne.bean.settlement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
/**
 * 
 * @author Reeba Joseph
 * Date: 02-July-2009
 *
 */

public class SettlementDetails extends BeanBase {
	
	// investment verification view flag
	private boolean settlementFlag = false;
	
	private String decodedStatus="";
	private String pending="false";
	private String inProcess="false";
	private String finalized="false";
	
	private String myPage;
	private String noData="false";
	private String noDtlData="false";
	private String modeLength="false";
	private String finalFlag="false";
	private String reCalFlag="false";
	private String empTokenItt = "";
	private String empNameItt = "";
	private String empCodeItt = "";
	private String resignDateItt = "";
	private String separDateItt = "";
	private String resignCodeItt = "";
	private String settDateItt = "";
	private String settCodeItt = "";
	private String noticePeriodItt = "";
	private String noticeStatusItt = "";
	private String statusItt = "";
	private String status = "";
	private String noticeDate = "";
	private String joinDate = "";
	private String totalRecords = "";
	
	private ArrayList tableList=null;
	
	private String resignCode = "";
	private String settCode = "";
	private String settDtlCode = "";
	private String empToken = "";
	private String empName = "";
	private String empCode = "";
	private String branch;
	private String dept;
	private String desgn;
	private String resignDate;
	private String settDate;
	private String sepDate;
	private String noticePeriod = "";
	private String noticePeriodStatus = "";
	private String noticeStatus = "";
	private String modePayment = "";
	private String bank = "";
	private String bankMicrId = "";
	private String chequeno = "";
	private String chequeDate = "";
	private String preparedby;
	private String prepbyCode = "";
	private String prepbyToken = "";
	private String accCheck;
	private String accChkCode = "";
	private String accChkToken = "";
	private String checkedby;
	private String chkbyCode = "";
	private String chkbyToken = "";
	private String handedOver;
	private String handOverCode = "";
	private String handOverToken = "";
	private String permSettle = "";
	private String processFlag = "";
	private String isSettled = "";
	private String eligibleMth = "";
	private String comments = "";
	private String gratuity;
	private String serviceTenure;
	private String gratuityAvgSalary;
	private String reimburse;
	private String deduct;
	private String taxValue;
	private String lockFlag = "";
	private String salaryAmt = "";
	
	private String ohMonth = "";
	private String ohYear = "";
	private String ohMaxDays="";
	private String calDays = "";
	private String calType = "";
	private String payByMnth = "";
	private String payByYr = "";
	private String calPayByDays = "";
	private String calPayByType = "";
	private ArrayList typeList=null;
	private String listCode="";
	private String payListCode="";
	private String settleAmt;
	private String leaveLength;
	private String payMonth = "";
	private String calMonth = "";
	private String maxDays = "";

	// variables for days .............

	private String creditCode;
	private String creditHead;
	private String debitCode;
	private String debitHead;
	private String ledgerCode;
	private String month;
	private String year;

	// variables for short period days .............

	private String month1;
	private String year1;
	private String typeCheck;
	private String daysCheck;
	private String netOnHold;

	ArrayList<Object> monthList = null;
	ArrayList<Object> creditList = null;
	ArrayList<Object> debitList = null;
	ArrayList<Object> daysList = null;

	private Object[] grossCredit = null;
	
	//Objects for Leave details
	private String leaveName;
	private String leaveId;
	private String clBal;
	private String totalAmt;
	private String total;
	private String totalLeaveDays;
	private String encashAmt;
	private String isFlag="false";
	
	private boolean onholdFlag = false;
	private boolean payFlag = true;
	
	private String loanFlag="false";
	private String totalLoanAmt="";
	private String loanAmtCh="";
	
	private ArrayList<SettlementDetails> creditHeader = null;
	private ArrayList<SettlementDetails> debitHeader = null;
	//Added by Ganesh start
	private String reimburseComments = "";
	private String deductComments = "";
	//Added by Ganesh end
	
	//Updated Anantha lakshmi
	private String cadreName="";
	private String dateOfJoin="";
	
	
	/**
	 * @return cadreName
	 */
	public String getCadreName() {
		return cadreName;
	}
	
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	/**
	 * @return the creditHeader
	 */
	public ArrayList<SettlementDetails> getCreditHeader() {
		return creditHeader;
	}
	/**
	 * @param creditHeader the creditHeader to set
	 */
	public void setCreditHeader(ArrayList<SettlementDetails> creditHeader) {
		this.creditHeader = creditHeader;
	}
	/**
	 * @return the debitHeader
	 */
	public ArrayList<SettlementDetails> getDebitHeader() {
		return debitHeader;
	}
	/**
	 * @param debitHeader the debitHeader to set
	 */
	public void setDebitHeader(ArrayList<SettlementDetails> debitHeader) {
		this.debitHeader = debitHeader;
	}
	/**
	 * @return the onholdFlag
	 */
	public boolean isOnholdFlag() {
		return onholdFlag;
	}
	/**
	 * @param onholdFlag the onholdFlag to set
	 */
	public void setOnholdFlag(boolean onholdFlag) {
		this.onholdFlag = onholdFlag;
	}
	/**
	 * @return the resignCode
	 */
	public String getResignCode() {
		return resignCode;
	}
	/**
	 * @param resignCode the resignCode to set
	 */
	public void setResignCode(String resignCode) {
		this.resignCode = resignCode;
	}
	/**
	 * @return the settCode
	 */
	public String getSettCode() {
		return settCode;
	}
	/**
	 * @param settCode the settCode to set
	 */
	public void setSettCode(String settCode) {
		this.settCode = settCode;
	}
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}
	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * @return the desgn
	 */
	public String getDesgn() {
		return desgn;
	}
	/**
	 * @param desgn the desgn to set
	 */
	public void setDesgn(String desgn) {
		this.desgn = desgn;
	}
	/**
	 * @return the resignDate
	 */
	public String getResignDate() {
		return resignDate;
	}
	/**
	 * @param resignDate the resignDate to set
	 */
	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}
	/**
	 * @return the settDate
	 */
	public String getSettDate() {
		return settDate;
	}
	/**
	 * @param settDate the settDate to set
	 */
	public void setSettDate(String settDate) {
		this.settDate = settDate;
	}
	/**
	 * @return the sepDate
	 */
	public String getSepDate() {
		return sepDate;
	}
	/**
	 * @param sepDate the sepDate to set
	 */
	public void setSepDate(String sepDate) {
		this.sepDate = sepDate;
	}
	/**
	 * @return the noticePeriod
	 */
	public String getNoticePeriod() {
		return noticePeriod;
	}
	/**
	 * @param noticePeriod the noticePeriod to set
	 */
	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	/**
	 * @return the noticePeriodStatus
	 */
	public String getNoticePeriodStatus() {
		return noticePeriodStatus;
	}
	/**
	 * @param noticePeriodStatus the noticePeriodStatus to set
	 */
	public void setNoticePeriodStatus(String noticePeriodStatus) {
		this.noticePeriodStatus = noticePeriodStatus;
	}
	/**
	 * @return the noticeStatus
	 */
	public String getNoticeStatus() {
		return noticeStatus;
	}
	/**
	 * @param noticeStatus the noticeStatus to set
	 */
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	/**
	 * @return the modePayment
	 */
	public String getModePayment() {
		return modePayment;
	}
	/**
	 * @param modePayment the modePayment to set
	 */
	public void setModePayment(String modePayment) {
		this.modePayment = modePayment;
	}
	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * @return the bankMicrId
	 */
	public String getBankMicrId() {
		return bankMicrId;
	}
	/**
	 * @param bankMicrId the bankMicrId to set
	 */
	public void setBankMicrId(String bankMicrId) {
		this.bankMicrId = bankMicrId;
	}
	/**
	 * @return the chequeno
	 */
	public String getChequeno() {
		return chequeno;
	}
	/**
	 * @param chequeno the chequeno to set
	 */
	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}
	/**
	 * @return the chequeDate
	 */
	public String getChequeDate() {
		return chequeDate;
	}
	/**
	 * @param chequeDate the chequeDate to set
	 */
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}
	/**
	 * @return the preparedby
	 */
	public String getPreparedby() {
		return preparedby;
	}
	/**
	 * @param preparedby the preparedby to set
	 */
	public void setPreparedby(String preparedby) {
		this.preparedby = preparedby;
	}
	/**
	 * @return the prepbyCode
	 */
	public String getPrepbyCode() {
		return prepbyCode;
	}
	/**
	 * @param prepbyCode the prepbyCode to set
	 */
	public void setPrepbyCode(String prepbyCode) {
		this.prepbyCode = prepbyCode;
	}
	/**
	 * @return the prepbyToken
	 */
	public String getPrepbyToken() {
		return prepbyToken;
	}
	/**
	 * @param prepbyToken the prepbyToken to set
	 */
	public void setPrepbyToken(String prepbyToken) {
		this.prepbyToken = prepbyToken;
	}
	/**
	 * @return the accCheck
	 */
	public String getAccCheck() {
		return accCheck;
	}
	/**
	 * @param accCheck the accCheck to set
	 */
	public void setAccCheck(String accCheck) {
		this.accCheck = accCheck;
	}
	/**
	 * @return the accChkCode
	 */
	public String getAccChkCode() {
		return accChkCode;
	}
	/**
	 * @param accChkCode the accChkCode to set
	 */
	public void setAccChkCode(String accChkCode) {
		this.accChkCode = accChkCode;
	}
	/**
	 * @return the accChkToken
	 */
	public String getAccChkToken() {
		return accChkToken;
	}
	/**
	 * @param accChkToken the accChkToken to set
	 */
	public void setAccChkToken(String accChkToken) {
		this.accChkToken = accChkToken;
	}
	/**
	 * @return the checkedby
	 */
	public String getCheckedby() {
		return checkedby;
	}
	/**
	 * @param checkedby the checkedby to set
	 */
	public void setCheckedby(String checkedby) {
		this.checkedby = checkedby;
	}
	/**
	 * @return the chkbyCode
	 */
	public String getChkbyCode() {
		return chkbyCode;
	}
	/**
	 * @param chkbyCode the chkbyCode to set
	 */
	public void setChkbyCode(String chkbyCode) {
		this.chkbyCode = chkbyCode;
	}
	/**
	 * @return the chkbyToken
	 */
	public String getChkbyToken() {
		return chkbyToken;
	}
	/**
	 * @param chkbyToken the chkbyToken to set
	 */
	public void setChkbyToken(String chkbyToken) {
		this.chkbyToken = chkbyToken;
	}
	/**
	 * @return the handedOver
	 */
	public String getHandedOver() {
		return handedOver;
	}
	/**
	 * @param handedOver the handedOver to set
	 */
	public void setHandedOver(String handedOver) {
		this.handedOver = handedOver;
	}
	/**
	 * @return the handOverCode
	 */
	public String getHandOverCode() {
		return handOverCode;
	}
	/**
	 * @param handOverCode the handOverCode to set
	 */
	public void setHandOverCode(String handOverCode) {
		this.handOverCode = handOverCode;
	}
	/**
	 * @return the handOverToken
	 */
	public String getHandOverToken() {
		return handOverToken;
	}
	/**
	 * @param handOverToken the handOverToken to set
	 */
	public void setHandOverToken(String handOverToken) {
		this.handOverToken = handOverToken;
	}
	/**
	 * @return the permSettle
	 */
	public String getPermSettle() {
		return permSettle;
	}
	/**
	 * @param permSettle the permSettle to set
	 */
	public void setPermSettle(String permSettle) {
		this.permSettle = permSettle;
	}
	/**
	 * @return the processFlag
	 */
	public String getProcessFlag() {
		return processFlag;
	}
	/**
	 * @param processFlag the processFlag to set
	 */
	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}
	/**
	 * @return the isSettled
	 */
	public String getIsSettled() {
		return isSettled;
	}
	/**
	 * @param isSettled the isSettled to set
	 */
	public void setIsSettled(String isSettled) {
		this.isSettled = isSettled;
	}
	/**
	 * @return the eligibleMth
	 */
	public String getEligibleMth() {
		return eligibleMth;
	}
	/**
	 * @param eligibleMth the eligibleMth to set
	 */
	public void setEligibleMth(String eligibleMth) {
		this.eligibleMth = eligibleMth;
	}
	/**
	 * @return the calDays
	 */
	public String getCalDays() {
		return calDays;
	}
	/**
	 * @param calDays the calDays to set
	 */
	public void setCalDays(String calDays) {
		this.calDays = calDays;
	}
	/**
	 * @return the calType
	 */
	public String getCalType() {
		return calType;
	}
	/**
	 * @param calType the calType to set
	 */
	public void setCalType(String calType) {
		this.calType = calType;
	}
	/**
	 * @return the typeList
	 */
	public ArrayList getTypeList() {
		return typeList;
	}
	/**
	 * @param typeList the typeList to set
	 */
	public void setTypeList(ArrayList typeList) {
		this.typeList = typeList;
	}
	/**
	 * @return the calPayByDays
	 */
	public String getCalPayByDays() {
		return calPayByDays;
	}
	/**
	 * @param calPayByDays the calPayByDays to set
	 */
	public void setCalPayByDays(String calPayByDays) {
		this.calPayByDays = calPayByDays;
	}
	/**
	 * @return the calPayByType
	 */
	public String getCalPayByType() {
		return calPayByType;
	}
	/**
	 * @param calPayByType the calPayByType to set
	 */
	public void setCalPayByType(String calPayByType) {
		this.calPayByType = calPayByType;
	}
	/**
	 * @return the ledgerCode
	 */
	public String getLedgerCode() {
		return ledgerCode;
	}
	/**
	 * @param ledgerCode the ledgerCode to set
	 */
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the monthList
	 */
	public ArrayList<Object> getMonthList() {
		return monthList;
	}
	/**
	 * @param monthList the monthList to set
	 */
	public void setMonthList(ArrayList<Object> monthList) {
		this.monthList = monthList;
	}
	/**
	 * @return the creditList
	 */
	public ArrayList<Object> getCreditList() {
		return creditList;
	}
	/**
	 * @param creditList the creditList to set
	 */
	public void setCreditList(ArrayList<Object> creditList) {
		this.creditList = creditList;
	}
	/**
	 * @return the debitList
	 */
	public ArrayList<Object> getDebitList() {
		return debitList;
	}
	/**
	 * @param debitList the debitList to set
	 */
	public void setDebitList(ArrayList<Object> debitList) {
		this.debitList = debitList;
	}
	/**
	 * @return the daysList
	 */
	public ArrayList<Object> getDaysList() {
		return daysList;
	}
	/**
	 * @param daysList the daysList to set
	 */
	public void setDaysList(ArrayList<Object> daysList) {
		this.daysList = daysList;
	}
	/**
	 * @return the grossCredit
	 */
	public Object[] getGrossCredit() {
		return grossCredit;
	}
	/**
	 * @param grossCredit the grossCredit to set
	 */
	public void setGrossCredit(Object[] grossCredit) {
		this.grossCredit = grossCredit;
	}
	/**
	 * @return the listCode
	 */
	public String getListCode() {
		return listCode;
	}
	/**
	 * @param listCode the listCode to set
	 */
	public void setListCode(String listCode) {
		this.listCode = listCode;
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
	 * @return the leaveId
	 */
	public String getLeaveId() {
		return leaveId;
	}
	/**
	 * @param leaveId the leaveId to set
	 */
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	/**
	 * @return the clBal
	 */
	public String getClBal() {
		return clBal;
	}
	/**
	 * @param clBal the clBal to set
	 */
	public void setClBal(String clBal) {
		this.clBal = clBal;
	}
	/**
	 * @return the totalAmt
	 */
	public String getTotalAmt() {
		return totalAmt;
	}
	/**
	 * @param totalAmt the totalAmt to set
	 */
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return the isFlag
	 */
	public String getIsFlag() {
		return isFlag;
	}
	/**
	 * @param isFlag the isFlag to set
	 */
	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	/**
	 * @return the month1
	 */
	public String getMonth1() {
		return month1;
	}
	/**
	 * @param month1 the month1 to set
	 */
	public void setMonth1(String month1) {
		this.month1 = month1;
	}
	/**
	 * @return the year1
	 */
	public String getYear1() {
		return year1;
	}
	/**
	 * @param year1 the year1 to set
	 */
	public void setYear1(String year1) {
		this.year1 = year1;
	}
	/**
	 * @return the typeCheck
	 */
	public String getTypeCheck() {
		return typeCheck;
	}
	/**
	 * @param typeCheck the typeCheck to set
	 */
	public void setTypeCheck(String typeCheck) {
		this.typeCheck = typeCheck;
	}
	/**
	 * @return the daysCheck
	 */
	public String getDaysCheck() {
		return daysCheck;
	}
	/**
	 * @param daysCheck the daysCheck to set
	 */
	public void setDaysCheck(String daysCheck) {
		this.daysCheck = daysCheck;
	}
	/**
	 * @return the netOnHold
	 */
	public String getNetOnHold() {
		return netOnHold;
	}
	/**
	 * @param netOnHold the netOnHold to set
	 */
	public void setNetOnHold(String netOnHold) {
		this.netOnHold = netOnHold;
	}
	/**
	 * @return the settleAmt
	 */
	public String getSettleAmt() {
		return settleAmt;
	}
	/**
	 * @param settleAmt the settleAmt to set
	 */
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}
	/**
	 * @return the payListCode
	 */
	public String getPayListCode() {
		return payListCode;
	}
	/**
	 * @param payListCode the payListCode to set
	 */
	public void setPayListCode(String payListCode) {
		this.payListCode = payListCode;
	}
	/**
	 * @return the leaveLength
	 */
	public String getLeaveLength() {
		return leaveLength;
	}
	/**
	 * @param leaveLength the leaveLength to set
	 */
	public void setLeaveLength(String leaveLength) {
		this.leaveLength = leaveLength;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the gratuity
	 */
	public String getGratuity() {
		return gratuity;
	}
	/**
	 * @param gratuity the gratuity to set
	 */
	public void setGratuity(String gratuity) {
		this.gratuity = gratuity;
	}
	/**
	 * @return the reimburse
	 */
	public String getReimburse() {
		return reimburse;
	}
	/**
	 * @param reimburse the reimburse to set
	 */
	public void setReimburse(String reimburse) {
		this.reimburse = reimburse;
	}
	/**
	 * @return the deduct
	 */
	public String getDeduct() {
		return deduct;
	}
	/**
	 * @param deduct the deduct to set
	 */
	public void setDeduct(String deduct) {
		this.deduct = deduct;
	}
	/**
	 * @return the creditCode
	 */
	public String getCreditCode() {
		return creditCode;
	}
	/**
	 * @param creditCode the creditCode to set
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	/**
	 * @return the creditHead
	 */
	public String getCreditHead() {
		return creditHead;
	}
	/**
	 * @param creditHead the creditHead to set
	 */
	public void setCreditHead(String creditHead) {
		this.creditHead = creditHead;
	}
	/**
	 * @return the debitCode
	 */
	public String getDebitCode() {
		return debitCode;
	}
	/**
	 * @param debitCode the debitCode to set
	 */
	public void setDebitCode(String debitCode) {
		this.debitCode = debitCode;
	}
	/**
	 * @return the debitHead
	 */
	public String getDebitHead() {
		return debitHead;
	}
	/**
	 * @param debitHead the debitHead to set
	 */
	public void setDebitHead(String debitHead) {
		this.debitHead = debitHead;
	}
	/**
	 * @return the lockFlag
	 */
	public String getLockFlag() {
		return lockFlag;
	}
	/**
	 * @param lockFlag the lockFlag to set
	 */
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	/**
	 * @return the totalLeaveDays
	 */
	public String getTotalLeaveDays() {
		return totalLeaveDays;
	}
	/**
	 * @param totalLeaveDays the totalLeaveDays to set
	 */
	public void setTotalLeaveDays(String totalLeaveDays) {
		this.totalLeaveDays = totalLeaveDays;
	}
	/**
	 * @return the payMonth
	 */
	public String getPayMonth() {
		return payMonth;
	}
	/**
	 * @param payMonth the payMonth to set
	 */
	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}
	/**
	 * @return the calMonth
	 */
	public String getCalMonth() {
		return calMonth;
	}
	/**
	 * @param calMonth the calMonth to set
	 */
	public void setCalMonth(String calMonth) {
		this.calMonth = calMonth;
	}
	/**
	 * @return the settDtlCode
	 */
	public String getSettDtlCode() {
		return settDtlCode;
	}
	/**
	 * @param settDtlCode the settDtlCode to set
	 */
	public void setSettDtlCode(String settDtlCode) {
		this.settDtlCode = settDtlCode;
	}
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
	}
	public String getInProcess() {
		return inProcess;
	}
	public void setInProcess(String inProcess) {
		this.inProcess = inProcess;
	}
	public String getFinalized() {
		return finalized;
	}
	public void setFinalized(String finalized) {
		this.finalized = finalized;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}
	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}
	/**
	 * @return the tableList
	 */
	public ArrayList getTableList() {
		return tableList;
	}
	/**
	 * @param tableList the tableList to set
	 */
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
	}
	/**
	 * @return the empTokenItt
	 */
	public String getEmpTokenItt() {
		return empTokenItt;
	}
	/**
	 * @param empTokenItt the empTokenItt to set
	 */
	public void setEmpTokenItt(String empTokenItt) {
		this.empTokenItt = empTokenItt;
	}
	/**
	 * @return the empNameItt
	 */
	public String getEmpNameItt() {
		return empNameItt;
	}
	/**
	 * @param empNameItt the empNameItt to set
	 */
	public void setEmpNameItt(String empNameItt) {
		this.empNameItt = empNameItt;
	}
	/**
	 * @return the empCodeItt
	 */
	public String getEmpCodeItt() {
		return empCodeItt;
	}
	/**
	 * @param empCodeItt the empCodeItt to set
	 */
	public void setEmpCodeItt(String empCodeItt) {
		this.empCodeItt = empCodeItt;
	}
	/**
	 * @return the resignDateItt
	 */
	public String getResignDateItt() {
		return resignDateItt;
	}
	/**
	 * @param resignDateItt the resignDateItt to set
	 */
	public void setResignDateItt(String resignDateItt) {
		this.resignDateItt = resignDateItt;
	}
	/**
	 * @return the resignCodeItt
	 */
	public String getResignCodeItt() {
		return resignCodeItt;
	}
	/**
	 * @param resignCodeItt the resignCodeItt to set
	 */
	public void setResignCodeItt(String resignCodeItt) {
		this.resignCodeItt = resignCodeItt;
	}
	/**
	 * @return the settCodeItt
	 */
	public String getSettCodeItt() {
		return settCodeItt;
	}
	/**
	 * @param settCodeItt the settCodeItt to set
	 */
	public void setSettCodeItt(String settCodeItt) {
		this.settCodeItt = settCodeItt;
	}
	/**
	 * @return the noticePeriodItt
	 */
	public String getNoticePeriodItt() {
		return noticePeriodItt;
	}
	/**
	 * @param noticePeriodItt the noticePeriodItt to set
	 */
	public void setNoticePeriodItt(String noticePeriodItt) {
		this.noticePeriodItt = noticePeriodItt;
	}
	/**
	 * @return the separDateItt
	 */
	public String getSeparDateItt() {
		return separDateItt;
	}
	/**
	 * @param separDateItt the separDateItt to set
	 */
	public void setSeparDateItt(String separDateItt) {
		this.separDateItt = separDateItt;
	}
	/**
	 * @return the statusItt
	 */
	public String getStatusItt() {
		return statusItt;
	}
	/**
	 * @param statusItt the statusItt to set
	 */
	public void setStatusItt(String statusItt) {
		this.statusItt = statusItt;
	}
	/**
	 * @return the settDateItt
	 */
	public String getSettDateItt() {
		return settDateItt;
	}
	/**
	 * @param settDateItt the settDateItt to set
	 */
	public void setSettDateItt(String settDateItt) {
		this.settDateItt = settDateItt;
	}
	/**
	 * @return the payByMnth
	 */
	public String getPayByMnth() {
		return payByMnth;
	}
	/**
	 * @param payByMnth the payByMnth to set
	 */
	public void setPayByMnth(String payByMnth) {
		this.payByMnth = payByMnth;
	}
	/**
	 * @return the payByYr
	 */
	public String getPayByYr() {
		return payByYr;
	}
	/**
	 * @param payByYr the payByYr to set
	 */
	public void setPayByYr(String payByYr) {
		this.payByYr = payByYr;
	}
	/**
	 * @return the ohMonth
	 */
	public String getOhMonth() {
		return ohMonth;
	}
	/**
	 * @param ohMonth the ohMonth to set
	 */
	public void setOhMonth(String ohMonth) {
		this.ohMonth = ohMonth;
	}
	/**
	 * @return the ohYear
	 */
	public String getOhYear() {
		return ohYear;
	}
	/**
	 * @param ohYear the ohYear to set
	 */
	public void setOhYear(String ohYear) {
		this.ohYear = ohYear;
	}
	/**
	 * @return the maxDays
	 */
	public String getMaxDays() {
		return maxDays;
	}
	/**
	 * @param maxDays the maxDays to set
	 */
	public void setMaxDays(String maxDays) {
		this.maxDays = maxDays;
	}
	/**
	 * @return the taxValue
	 */
	public String getTaxValue() {
		return taxValue;
	}
	/**
	 * @param taxValue the taxValue to set
	 */
	public void setTaxValue(String taxValue) {
		this.taxValue = taxValue;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the salaryAmt
	 */
	public String getSalaryAmt() {
		return salaryAmt;
	}
	/**
	 * @param salaryAmt the salaryAmt to set
	 */
	public void setSalaryAmt(String salaryAmt) {
		this.salaryAmt = salaryAmt;
	}
	/**
	 * @return the noticeStatusItt
	 */
	public String getNoticeStatusItt() {
		return noticeStatusItt;
	}
	/**
	 * @param noticeStatusItt the noticeStatusItt to set
	 */
	public void setNoticeStatusItt(String noticeStatusItt) {
		this.noticeStatusItt = noticeStatusItt;
	}
	/**
	 * @return the ohMaxDays
	 */
	public String getOhMaxDays() {
		return ohMaxDays;
	}
	/**
	 * @param ohMaxDays the ohMaxDays to set
	 */
	public void setOhMaxDays(String ohMaxDays) {
		this.ohMaxDays = ohMaxDays;
	}
	/**
	 * @return the payFlag
	 */
	public boolean isPayFlag() {
		return payFlag;
	}
	/**
	 * @param payFlag the payFlag to set
	 */
	public void setPayFlag(boolean payFlag) {
		this.payFlag = payFlag;
	}
	/**
	 * @return the noDtlData
	 */
	public String getNoDtlData() {
		return noDtlData;
	}
	/**
	 * @param noDtlData the noDtlData to set
	 */
	public void setNoDtlData(String noDtlData) {
		this.noDtlData = noDtlData;
	}
	/**
	 * @return the finalFlag
	 */
	public String getFinalFlag() {
		return finalFlag;
	}
	/**
	 * @param finalFlag the finalFlag to set
	 */
	public void setFinalFlag(String finalFlag) {
		this.finalFlag = finalFlag;
	}
	/**
	 * @return the noticeDate
	 */
	public String getNoticeDate() {
		return noticeDate;
	}
	/**
	 * @param noticeDate the noticeDate to set
	 */
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	/**
	 * @return the joinDate
	 */
	public String getJoinDate() {
		return joinDate;
	}
	/**
	 * @param joinDate the joinDate to set
	 */
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the loanFlag
	 */
	public String getLoanFlag() {
		return loanFlag;
	}
	/**
	 * @param loanFlag the loanFlag to set
	 */
	public void setLoanFlag(String loanFlag) {
		this.loanFlag = loanFlag;
	}
	/**
	 * @return the totalLoanAmt
	 */
	public String getTotalLoanAmt() {
		return totalLoanAmt;
	}
	/**
	 * @param totalLoanAmt the totalLoanAmt to set
	 */
	public void setTotalLoanAmt(String totalLoanAmt) {
		this.totalLoanAmt = totalLoanAmt;
	}
	/**
	 * @return the reCalFlag
	 */
	public String getReCalFlag() {
		return reCalFlag;
	}
	/**
	 * @param reCalFlag the reCalFlag to set
	 */
	public void setReCalFlag(String reCalFlag) {
		this.reCalFlag = reCalFlag;
	}
	/**
	 * @return the loanAmtCh
	 */
	public String getLoanAmtCh() {
		return loanAmtCh;
	}
	/**
	 * @param loanAmtCh the loanAmtCh to set
	 */
	public void setLoanAmtCh(String loanAmtCh) {
		this.loanAmtCh = loanAmtCh;
	}
	/**
	 * @return the encashAmt
	 */
	public String getEncashAmt() {
		return encashAmt;
	}
	/**
	 * @param encashAmt the encashAmt to set
	 */
	public void setEncashAmt(String encashAmt) {
		this.encashAmt = encashAmt;
	}
	public String getServiceTenure() {
		return serviceTenure;
	}
	public void setServiceTenure(String serviceTenure) {
		this.serviceTenure = serviceTenure;
	}
	public String getGratuityAvgSalary() {
		return gratuityAvgSalary;
	}
	public void setGratuityAvgSalary(String gratuityAvgSalary) {
		this.gratuityAvgSalary = gratuityAvgSalary;
	}
	/**
	 * @return
	 */
	public String getDecodedStatus() {
		return decodedStatus;
	}
	/**
	 * @param decodedStatus
	 */
	public void setDecodedStatus(String decodedStatus) {
		this.decodedStatus = decodedStatus;
	}
	public String getReimburseComments() {
		return reimburseComments;
	}
	public void setReimburseComments(String reimburseComments) {
		this.reimburseComments = reimburseComments;
	}
	public String getDeductComments() {
		return deductComments;
	}
	public void setDeductComments(String deductComments) {
		this.deductComments = deductComments;
	}
	public String getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public boolean isSettlementFlag() {
		return settlementFlag;
	}

	public void setSettlementFlag(boolean settlementFlag) {
		this.settlementFlag = settlementFlag;
	}
	
	
}