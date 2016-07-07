/**
 * 
 */
package org.paradyne.bean.reimbursement;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class ReimbProcess extends BeanBase {

	private String listType="pending";
	private String pendingLength="false";
	private String myPage="";
	private String myPageLocked="";
	private String lockedLength="false";
	private String divisionNameList="";
	private String processCodeList="";
	private String processCode="";
	private String status="";
	private String reimbHeadList="";
	private String reimbHeadCodeList="";
	private String processDateList="";
	private String processFromList="";
	private String processToList="";
	private String processTypeList="";
	private String statusList="";
	
	private String reimbHeadName="";
	private String reimbHeadCode="";
	private String divisionName="";
	private String divisionCode="";
	private String reimbPeriod="";
	
	private String fromDate="";
	private String toDate="";
	private String month="";
	private String year="";
	private String quarter="";
	private String quarterYear="";
	private String halfYear="";
	private String halfMonth="";
	private String annYear="";
	
	private String empNameList="";
	private String empCodeList="";
	private String empTokenList="";
	private String empReimbAmountList="";
	private String empProcessLength="false";
	
	ArrayList processList=null;
	ArrayList empProcessList=null;
	ArrayList lockedList=null;
	
	public ArrayList getProcessList() {
		return processList;
	}
	public void setProcessList(ArrayList processList) {
		this.processList = processList;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getPendingLength() {
		return pendingLength;
	}
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getDivisionNameList() {
		return divisionNameList;
	}
	public void setDivisionNameList(String divisionNameList) {
		this.divisionNameList = divisionNameList;
	}
	public String getProcessCodeList() {
		return processCodeList;
	}
	public void setProcessCodeList(String processCodeList) {
		this.processCodeList = processCodeList;
	}
	public String getReimbHeadList() {
		return reimbHeadList;
	}
	public void setReimbHeadList(String reimbHeadList) {
		this.reimbHeadList = reimbHeadList;
	}
	public String getReimbHeadCodeList() {
		return reimbHeadCodeList;
	}
	public void setReimbHeadCodeList(String reimbHeadCodeList) {
		this.reimbHeadCodeList = reimbHeadCodeList;
	}
	public String getProcessDateList() {
		return processDateList;
	}
	public void setProcessDateList(String processDateList) {
		this.processDateList = processDateList;
	}
	public String getProcessFromList() {
		return processFromList;
	}
	public void setProcessFromList(String processFromList) {
		this.processFromList = processFromList;
	}
	public String getProcessToList() {
		return processToList;
	}
	public void setProcessToList(String processToList) {
		this.processToList = processToList;
	}
	public String getProcessTypeList() {
		return processTypeList;
	}
	public void setProcessTypeList(String processTypeList) {
		this.processTypeList = processTypeList;
	}
	public String getReimbHeadName() {
		return reimbHeadName;
	}
	public void setReimbHeadName(String reimbHeadName) {
		this.reimbHeadName = reimbHeadName;
	}
	public String getReimbHeadCode() {
		return reimbHeadCode;
	}
	public void setReimbHeadCode(String reimbHeadCode) {
		this.reimbHeadCode = reimbHeadCode;
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
	public String getReimbPeriod() {
		return reimbPeriod;
	}
	public void setReimbPeriod(String reimbPeriod) {
		this.reimbPeriod = reimbPeriod;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getQuarterYear() {
		return quarterYear;
	}
	public void setQuarterYear(String quarterYear) {
		this.quarterYear = quarterYear;
	}
	public String getHalfYear() {
		return halfYear;
	}
	public void setHalfYear(String halfYear) {
		this.halfYear = halfYear;
	}
	public String getHalfMonth() {
		return halfMonth;
	}
	public void setHalfMonth(String halfMonth) {
		this.halfMonth = halfMonth;
	}
	public String getAnnYear() {
		return annYear;
	}
	public void setAnnYear(String annYear) {
		this.annYear = annYear;
	}
	public String getProcessCode() {
		return processCode;
	}
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	public String getEmpNameList() {
		return empNameList;
	}
	public void setEmpNameList(String empNameList) {
		this.empNameList = empNameList;
	}
	public String getEmpCodeList() {
		return empCodeList;
	}
	public void setEmpCodeList(String empCodeList) {
		this.empCodeList = empCodeList;
	}
	public String getEmpTokenList() {
		return empTokenList;
	}
	public void setEmpTokenList(String empTokenList) {
		this.empTokenList = empTokenList;
	}
	public String getEmpReimbAmountList() {
		return empReimbAmountList;
	}
	public void setEmpReimbAmountList(String empReimbAmountList) {
		this.empReimbAmountList = empReimbAmountList;
	}
	public ArrayList getEmpProcessList() {
		return empProcessList;
	}
	public void setEmpProcessList(ArrayList empProcessList) {
		this.empProcessList = empProcessList;
	}
	public String getEmpProcessLength() {
		return empProcessLength;
	}
	public void setEmpProcessLength(String empProcessLength) {
		this.empProcessLength = empProcessLength;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMyPageLocked() {
		return myPageLocked;
	}
	public void setMyPageLocked(String myPageLocked) {
		this.myPageLocked = myPageLocked;
	}
	public String getLockedLength() {
		return lockedLength;
	}
	public void setLockedLength(String lockedLength) {
		this.lockedLength = lockedLength;
	}
	public ArrayList getLockedList() {
		return lockedList;
	}
	public void setLockedList(ArrayList lockedList) {
		this.lockedList = lockedList;
	}
	public String getStatusList() {
		return statusList;
	}
	public void setStatusList(String statusList) {
		this.statusList = statusList;
	}
}
