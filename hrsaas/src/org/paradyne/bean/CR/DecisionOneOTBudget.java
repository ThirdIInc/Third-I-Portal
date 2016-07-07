package org.paradyne.bean.CR;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;


/**
 * @Date July 19, 2013 
 */
public class DecisionOneOTBudget  extends BeanBase{
	
	private String otMonth = "";
	private String otYear = "";
	private boolean viewOTDtlFlag = false;
	private String totalBudget="";
	private String NumberOfWorkingDays="";
	private String yearMonth="";//for getting year and month from jsp in combination of year and month
	private String regionID="";
	private String regionIDItt="";
	private String regionName="";
	private String otPersonType="";
	private String ReportingID="";
	private String PersonID="";
	private String budgetPer="";
	private String budgetAmt="";
	private String budgetDaily="";
	private String budgetWeekly="";
	private String myPage;
	private boolean listLength=false;
	private ArrayList otBudgetList=null;
	private String autoid="";
	private String PersonName="";
	private String forMonthYear="";
	private String previousData="";
	private String previousMonthYear="";
	private String ReportingName="";
	
	
	
	
	
	/**
	 * @return the previousMonthYear
	 */
	public String getPreviousMonthYear() {
		return previousMonthYear;
	}
	/**
	 * @param previousMonthYear the previousMonthYear to set
	 */
	public void setPreviousMonthYear(String previousMonthYear) {
		this.previousMonthYear = previousMonthYear;
	}
	/**
	 * @return the previousData
	 */
	public String getPreviousData() {
		return previousData;
	}
	/**
	 * @param previousData the previousData to set
	 */
	public void setPreviousData(String previousData) {
		this.previousData = previousData;
	}
	/**
	 * @return the forMonthYear
	 */
	public String getForMonthYear() {
		return forMonthYear;
	}
	/**
	 * @param forMonthYear the forMonthYear to set
	 */
	public void setForMonthYear(String forMonthYear) {
		this.forMonthYear = forMonthYear;
	}
	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return PersonName;
	}
	/**
	 * @param personName the personName to set
	 */
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	/**
	 * @return the autoid
	 */
	public String getAutoid() {
		return autoid;
	}
	/**
	 * @param autoid the autoid to set
	 */
	public void setAutoid(String autoid) {
		this.autoid = autoid;
	}
	/**
	 * @return the otBudgetList
	 */
	public ArrayList getOtBudgetList() {
		return otBudgetList;
	}
	/**
	 * @param otBudgetList the otBudgetList to set
	 */
	public void setOtBudgetList(ArrayList otBudgetList) {
		this.otBudgetList = otBudgetList;
	}
	/**
	 * @return the listLength
	 */
	public boolean isListLength() {
		return listLength;
	}
	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(boolean listLength) {
		this.listLength = listLength;
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
	 * @return the budgetAmt
	 */
	public String getBudgetAmt() {
		return budgetAmt;
	}
	/**
	 * @param budgetAmt the budgetAmt to set
	 */
	public void setBudgetAmt(String budgetAmt) {
		this.budgetAmt = budgetAmt;
	}
	/**
	 * @return the budgetDaily
	 */
	public String getBudgetDaily() {
		return budgetDaily;
	}
	/**
	 * @param budgetDaily the budgetDaily to set
	 */
	public void setBudgetDaily(String budgetDaily) {
		this.budgetDaily = budgetDaily;
	}
	/**
	 * @return the budgetWeekly
	 */
	public String getBudgetWeekly() {
		return budgetWeekly;
	}
	/**
	 * @param budgetWeekly the budgetWeekly to set
	 */
	public void setBudgetWeekly(String budgetWeekly) {
		this.budgetWeekly = budgetWeekly;
	}
	/**
	 * @return the budgetPer
	 */
	public String getBudgetPer() {
		return budgetPer;
	}
	/**
	 * @param budgetPer the budgetPer to set
	 */
	public void setBudgetPer(String budgetPer) {
		this.budgetPer = budgetPer;
	}
	/**
	 * @return the personID
	 */
	public String getPersonID() {
		return PersonID;
	}
	/**
	 * @param personID the personID to set
	 */
	public void setPersonID(String personID) {
		PersonID = personID;
	}
	/**
	 * @return the reportingID
	 */
	public String getReportingID() {
		return ReportingID;
	}
	/**
	 * @param reportingID the reportingID to set
	 */
	public void setReportingID(String reportingID) {
		ReportingID = reportingID;
	}
	/**
	 * @return the otMonth
	 */
	public String getOtMonth() {
		return otMonth;
	}
	/**
	 * @param otMonth the otMonth to set
	 */
	public void setOtMonth(String otMonth) {
		this.otMonth = otMonth;
	}
	/**
	 * @return the otYear
	 */
	public String getOtYear() {
		return otYear;
	}
	/**
	 * @param otYear the otYear to set
	 */
	public void setOtYear(String otYear) {
		this.otYear = otYear;
	}
	/**
	 * @return the viewOTDtlFlag
	 */
	public boolean isViewOTDtlFlag() {
		return viewOTDtlFlag;
	}
	/**
	 * @param viewOTDtlFlag the viewOTDtlFlag to set
	 */
	public void setViewOTDtlFlag(boolean viewOTDtlFlag) {
		this.viewOTDtlFlag = viewOTDtlFlag;
	}
	/**
	 * @return the totalBudget
	 */
	public String getTotalBudget() {
		return totalBudget;
	}
	/**
	 * @param totalBudget the totalBudget to set
	 */
	public void setTotalBudget(String totalBudget) {
		this.totalBudget = totalBudget;
	}
	/**
	 * @return the numberOfWorkingDays
	 */
	public String getNumberOfWorkingDays() {
		return NumberOfWorkingDays;
	}
	/**
	 * @param numberOfWorkingDays the numberOfWorkingDays to set
	 */
	public void setNumberOfWorkingDays(String numberOfWorkingDays) {
		NumberOfWorkingDays = numberOfWorkingDays;
	}
	/**
	 * @return the yearMonth
	 */
	public String getYearMonth() {
		return yearMonth;
	}
	/**
	 * @param yearMonth the yearMonth to set
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	/**
	 * @return the regionID
	 */
	public String getRegionID() {
		return regionID;
	}
	/**
	 * @param regionID the regionID to set
	 */
	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}
	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * @return the otPersonType
	 */
	public String getOtPersonType() {
		return otPersonType;
	}
	/**
	 * @param otPersonType the otPersonType to set
	 */
	public void setOtPersonType(String otPersonType) {
		this.otPersonType = otPersonType;
	}
	/**
	 * @return the regionIDItt
	 */
	public String getRegionIDItt() {
		return regionIDItt;
	}
	/**
	 * @param regionIDItt the regionIDItt to set
	 */
	public void setRegionIDItt(String regionIDItt) {
		this.regionIDItt = regionIDItt;
	}
	/**
	 * @return the reportingName
	 */
	public String getReportingName() {
		return ReportingName;
	}
	/**
	 * @param reportingName the reportingName to set
	 */
	public void setReportingName(String reportingName) {
		ReportingName = reportingName;
	}
	
	
	
	

	
	

	
}
