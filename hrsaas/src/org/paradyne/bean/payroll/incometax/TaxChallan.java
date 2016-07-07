package org.paradyne.bean.payroll.incometax;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Venkatesh
 *
 */
public class TaxChallan extends BeanBase {
	
	private String challanID;
	private String divName;
	private String divId;
	private String month; 
	private String year;
	private String tax;
	private String surcharge;
	private String eduCess;
	private String totalTax;
	private String chequeNo;
	private String chequeDate;
	private String bank;
	private String bsrCode;
	private String micr;
	private String challanNo;
	private String challanDate;
	private String ackNo;
	private String chaFlag="false";
	//Added on Date 13-10-2008
	private ArrayList challanList = null;
	private String empToken="";
	private String empName="";
	private String challanTax="";
	private String challanSurcharge="";
	private String challanEduCess="";
	private String challanCode="";
	private String empId="";
	private String challanTotTax="";
	private String vchrNo="";
	private String goFlag="true";
	private String listFlag="false";
	private String intAmt="";
	private String othrAmt="";
	private String bookEntry="";
	private String payDate="";
	private String deductDate="";
	private String depDate="";
	private String challanFlag="false";
	private String chqFlag="false";
	private String saveFlag="false";
	private String countFlag="false";
	private String onHold  = "";
	private String surchargePercen="";
	private String eduCessPercen="";
	private String rebateLimit="";
	private String empTaxIncome="";
	private String paymentDate="";
	
	private String challanListCode="";
	private String challanListMonth="";
	private String challanListYear="";
	private String challanListDivName="";
	private String challanListDivId="";
	private String totalListRecords="";
	private String challanListMonthId="";
	private String searchEmpId = "";
	private String searchEmpToken = "";
	private String searchEmpName ="";
	private String includeSalary="";
	private String includeArrears="";
	private String includeSettlement = "";
	private String includeAllowance = "";
	private String includeBonus = "";
	private String includeOverTime = "";
	private String includeLeaveEncashment = "";
	
	private String noData="false";
	private String myPage;
	
	private String addEmpToken = "";
	private String addEmpName = "";
	private String addEmpId = "";
	private String enterTotalTax = "";
	private String addedTds = "";
	private String addedSurcharge = "";
	private String addedEduCess = "";
	private String f9AddEmpFlag = "false";
	private String showFlag = "false";
	private String tempTotalTax = "0";
	private String tempTax = "0";
	private String tempSur = "0";
	private String tempEdu = "0";
	private String searchForEmpToken = "";
	private String searchForEmpName = "";
	private String searchForEmpId = "";
	private String listRowNum = "";
	private String listNoData = "false";
	private String monthName = "";
	private String onHoldName = "";
	private String listTotalTax = "";
	private String dummyField = "";
	private String reportType = "";
	
	ArrayList iteratorlist;
	
	private String hidListEmpId="";
	private String hidListTdsIncome = "";
	private ArrayList<Object> valuesList = new ArrayList<Object>();
	
	/*Variables for Bonus allowance form*/
	private String applicationCode = "";
	private String applicationType = "";
	private String backAction = "";
	
	
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getChallanListCode() {
		return challanListCode;
	}
	public void setChallanListCode(String challanListCode) {
		this.challanListCode = challanListCode;
	}
	public String getChallanListMonth() {
		return challanListMonth;
	}
	public void setChallanListMonth(String challanListMonth) {
		this.challanListMonth = challanListMonth;
	}
	public String getChallanListYear() {
		return challanListYear;
	}
	public void setChallanListYear(String challanListYear) {
		this.challanListYear = challanListYear;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getEmpTaxIncome() {
		return empTaxIncome;
	}
	public void setEmpTaxIncome(String empTaxIncome) {
		this.empTaxIncome = empTaxIncome;
	}
	public String getSurchargePercen() {
		return surchargePercen;
	}
	public void setSurchargePercen(String surchargePercen) {
		this.surchargePercen = surchargePercen;
	}
	public String getEduCessPercen() {
		return eduCessPercen;
	}
	public void setEduCessPercen(String eduCessPercen) {
		this.eduCessPercen = eduCessPercen;
	}
	public String getRebateLimit() {
		return rebateLimit;
	}
	public void setRebateLimit(String rebateLimit) {
		this.rebateLimit = rebateLimit;
	}
	public String getIntAmt() {
		return intAmt;
	}
	public void setIntAmt(String intAmt) {
		this.intAmt = intAmt;
	}
	public String getOthrAmt() {
		return othrAmt;
	}
	public void setOthrAmt(String othrAmt) {
		this.othrAmt = othrAmt;
	}
	public String getBookEntry() {
		return bookEntry;
	}
	public void setBookEntry(String bookEntry) {
		this.bookEntry = bookEntry;
	}
	public String getVchrNo() {
		return vchrNo;
	}
	public void setVchrNo(String vchrNo) {
		this.vchrNo = vchrNo;
	}
	public String getGoFlag() {
		return goFlag;
	}
	public void setGoFlag(String goFlag) {
		this.goFlag = goFlag;
	}
	public String getChallanTotTax() {
		return challanTotTax;
	}
	public void setChallanTotTax(String challanTotTax) {
		this.challanTotTax = challanTotTax;
	}
	/**
	 * @return the chaFlag
	 */
	public String getChaFlag() {
		return chaFlag;
	}
	/**
	 * @param chaFlag the chaFlag to set
	 */
	public void setChaFlag(String chaFlag) {
		this.chaFlag = chaFlag;
	}
	
	/**
	 * @return the challanID
	 */
	public String getChallanID() {
		return challanID;
	}
	/**
	 * @param challanID the challanID to set
	 */
	public void setChallanID(String challanID) {
		this.challanID = challanID;
	}
	
	
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
	 * @return the tax
	 */
	public String getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}
	/**
	 * @return the surcharge
	 */
	public String getSurcharge() {
		return surcharge;
	}
	/**
	 * @param surcharge the surcharge to set
	 */
	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}
	/**
	 * @return the eduCess
	 */
	public String getEduCess() {
		return eduCess;
	}
	/**
	 * @param eduCess the eduCess to set
	 */
	public void setEduCess(String eduCess) {
		this.eduCess = eduCess;
	}
	/**
	 * @return the totalTax
	 */
	public String getTotalTax() {
		return totalTax;
	}
	/**
	 * @param totalTax the totalTax to set
	 */
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	/**
	 * @return the chequeNo
	 */
	public String getChequeNo() {
		return chequeNo;
	}
	/**
	 * @param chequeNo the chequeNo to set
	 */
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
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
	 * @return the micr
	 */
	public String getMicr() {
		return micr;
	}
	/**
	 * @param micr the micr to set
	 */
	public void setMicr(String micr) {
		this.micr = micr;
	}
	/**
	 * @return the challanNo
	 */
	public String getChallanNo() {
		return challanNo;
	}
	/**
	 * @param challanNo the challanNo to set
	 */
	public void setChallanNo(String challanNo) {
		this.challanNo = challanNo;
	}
	/**
	 * @return the challanDate
	 */
	public String getChallanDate() {
		return challanDate;
	}
	/**
	 * @param challanDate the challanDate to set
	 */
	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
	}
	/**
	 * @return the empTypeId
	 */
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getBsrCode() {
		return bsrCode;
	}
	public void setBsrCode(String bsrCode) {
		this.bsrCode = bsrCode;
	}
	public String getAckNo() {
		return ackNo;
	}
	public void setAckNo(String ackNo) {
		this.ackNo = ackNo;
	}
	public ArrayList getChallanList() {
		return challanList;
	}
	public void setChallanList(ArrayList challanList) {
		this.challanList = challanList;
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
	public String getChallanTax() {
		return challanTax;
	}
	public void setChallanTax(String challanTax) {
		this.challanTax = challanTax;
	}
	public String getChallanSurcharge() {
		return challanSurcharge;
	}
	public void setChallanSurcharge(String challanSurcharge) {
		this.challanSurcharge = challanSurcharge;
	}
	public String getChallanEduCess() {
		return challanEduCess;
	}
	public void setChallanEduCess(String challanEduCess) {
		this.challanEduCess = challanEduCess;
	}
	public String getChallanCode() {
		return challanCode;
	}
	public void setChallanCode(String challanCode) {
		this.challanCode = challanCode;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getListFlag() {
		return listFlag;
	}
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getDeductDate() {
		return deductDate;
	}
	public void setDeductDate(String deductDate) {
		this.deductDate = deductDate;
	}
	public String getDepDate() {
		return depDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public String getChallanFlag() {
		return challanFlag;
	}
	public void setChallanFlag(String challanFlag) {
		this.challanFlag = challanFlag;
	}
	public String getChqFlag() {
		return chqFlag;
	}
	public void setChqFlag(String chqFlag) {
		this.chqFlag = chqFlag;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getCountFlag() {
		return countFlag;
	}
	public void setCountFlag(String countFlag) {
		this.countFlag = countFlag;
	}
	public String getOnHold() {
		return onHold;
	}
	public void setOnHold(String onHold) {
		this.onHold = onHold;
	}
	public String getChallanListDivName() {
		return challanListDivName;
	}
	public void setChallanListDivName(String challanListDivName) {
		this.challanListDivName = challanListDivName;
	}
	public String getChallanListDivId() {
		return challanListDivId;
	}
	public void setChallanListDivId(String challanListDivId) {
		this.challanListDivId = challanListDivId;
	}
	public String getTotalListRecords() {
		return totalListRecords;
	}
	public void setTotalListRecords(String totalListRecords) {
		this.totalListRecords = totalListRecords;
	}
	public String getChallanListMonthId() {
		return challanListMonthId;
	}
	public void setChallanListMonthId(String challanListMonthId) {
		this.challanListMonthId = challanListMonthId;
	}
	public String getSearchEmpId() {
		return searchEmpId;
	}
	public void setSearchEmpId(String searchEmpId) {
		this.searchEmpId = searchEmpId;
	}
	public String getSearchEmpToken() {
		return searchEmpToken;
	}
	public void setSearchEmpToken(String searchEmpToken) {
		this.searchEmpToken = searchEmpToken;
	}
	public String getSearchEmpName() {
		return searchEmpName;
	}
	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}
	public String getIncludeSalary() {
		return includeSalary;
	}
	public void setIncludeSalary(String includeSalary) {
		this.includeSalary = includeSalary;
	}
	public String getIncludeSettlement() {
		return includeSettlement;
	}
	public void setIncludeSettlement(String includeSettlement) {
		this.includeSettlement = includeSettlement;
	}
	public String getIncludeAllowance() {
		return includeAllowance;
	}
	public void setIncludeAllowance(String includeAllowance) {
		this.includeAllowance = includeAllowance;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getAddEmpToken() {
		return addEmpToken;
	}
	public void setAddEmpToken(String addEmpToken) {
		this.addEmpToken = addEmpToken;
	}
	public String getAddEmpName() {
		return addEmpName;
	}
	public void setAddEmpName(String addEmpName) {
		this.addEmpName = addEmpName;
	}
	public String getAddEmpId() {
		return addEmpId;
	}
	public void setAddEmpId(String addEmpId) {
		this.addEmpId = addEmpId;
	}
	public String getEnterTotalTax() {
		return enterTotalTax;
	}
	public void setEnterTotalTax(String enterTotalTax) {
		this.enterTotalTax = enterTotalTax;
	}
	public String getAddedTds() {
		return addedTds;
	}
	public void setAddedTds(String addedTds) {
		this.addedTds = addedTds;
	}
	public String getAddedSurcharge() {
		return addedSurcharge;
	}
	public void setAddedSurcharge(String addedSurcharge) {
		this.addedSurcharge = addedSurcharge;
	}
	public String getAddedEduCess() {
		return addedEduCess;
	}
	public void setAddedEduCess(String addedEduCess) {
		this.addedEduCess = addedEduCess;
	}
	public ArrayList<Object> getValuesList() {
		return valuesList;
	}
	public void setValuesList(ArrayList<Object> valuesList) {
		this.valuesList = valuesList;
	}
	public String getHidListEmpId() {
		return hidListEmpId;
	}
	public void setHidListEmpId(String hidListEmpId) {
		this.hidListEmpId = hidListEmpId;
	}
	public String getHidListTdsIncome() {
		return hidListTdsIncome;
	}
	public void setHidListTdsIncome(String hidListTdsIncome) {
		this.hidListTdsIncome = hidListTdsIncome;
	}
	public String getF9AddEmpFlag() {
		return f9AddEmpFlag;
	}
	public void setF9AddEmpFlag(String addEmpFlag) {
		f9AddEmpFlag = addEmpFlag;
	}
	public String getTempTotalTax() {
		return tempTotalTax;
	}
	public void setTempTotalTax(String tempTotalTax) {
		this.tempTotalTax = tempTotalTax;
	}
	public String getTempTax() {
		return tempTax;
	}
	public void setTempTax(String tempTax) {
		this.tempTax = tempTax;
	}
	public String getTempSur() {
		return tempSur;
	}
	public void setTempSur(String tempSur) {
		this.tempSur = tempSur;
	}
	public String getTempEdu() {
		return tempEdu;
	}
	public void setTempEdu(String tempEdu) {
		this.tempEdu = tempEdu;
	}
	public String getSearchForEmpToken() {
		return searchForEmpToken;
	}
	public void setSearchForEmpToken(String searchForEmpToken) {
		this.searchForEmpToken = searchForEmpToken;
	}
	public String getSearchForEmpName() {
		return searchForEmpName;
	}
	public void setSearchForEmpName(String searchForEmpName) {
		this.searchForEmpName = searchForEmpName;
	}
	public String getSearchForEmpId() {
		return searchForEmpId;
	}
	public void setSearchForEmpId(String searchForEmpId) {
		this.searchForEmpId = searchForEmpId;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getListRowNum() {
		return listRowNum;
	}
	public void setListRowNum(String listRowNum) {
		this.listRowNum = listRowNum;
	}
	public String getListNoData() {
		return listNoData;
	}
	public void setListNoData(String listNoData) {
		this.listNoData = listNoData;
	}
	public String getOnHoldName() {
		return onHoldName;
	}
	public void setOnHoldName(String onHoldName) {
		this.onHoldName = onHoldName;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getListTotalTax() {
		return listTotalTax;
	}
	public void setListTotalTax(String listTotalTax) {
		this.listTotalTax = listTotalTax;
	}
	public String getDummyField() {
		return dummyField;
	}
	public void setDummyField(String dummyField) {
		this.dummyField = dummyField;
	}
	public String getIncludeArrears() {
		return includeArrears;
	}
	public void setIncludeArrears(String includeArrears) {
		this.includeArrears = includeArrears;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getIncludeBonus() {
		return includeBonus;
	}
	public void setIncludeBonus(String includeBonus) {
		this.includeBonus = includeBonus;
	}
	public String getIncludeOverTime() {
		return includeOverTime;
	}
	public void setIncludeOverTime(String includeOverTime) {
		this.includeOverTime = includeOverTime;
	}
	public String getIncludeLeaveEncashment() {
		return includeLeaveEncashment;
	}
	public void setIncludeLeaveEncashment(String includeLeaveEncashment) {
		this.includeLeaveEncashment = includeLeaveEncashment;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getBackAction() {
		return backAction;
	}
	public void setBackAction(String backAction) {
		this.backAction = backAction;
	}
}
