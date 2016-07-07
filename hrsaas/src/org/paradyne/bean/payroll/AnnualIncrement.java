/**
 * 
 */
package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class AnnualIncrement extends BeanBase {
	private String month="";
	private String monthView="";
	private String year="";
	private String divisionId="";
	private String divisionName="";
	private String branchId="";
	private String branchName="";
	private String departmentId="";
	private String departmentName="";
	private String incrementCode="";
	private String incrementStatus="";
	private String myPage = "";
	private String totalRecords = "";
	private String listLength = "false";
	private boolean showFlag=false;
	
	private String listIncrementCode = "";
	private String listMonthName = "";
	private String listMonthCode = "";
	private String listYear = "";
	private String listDeptName = "";
	private String listDeptId = "";
	private String listBranchName = "";
	private String listBranchId = "";
	private String listDivName = "";
	private String listDivId = "";
	private String listIncrementStatus = "";
	
	private String creditCode;
	private String creditName;
	
	ArrayList iteratorList;
	ArrayList creditHeader;
	private String hdPage;
	private String fromTotRec;
	private String toTotRec;
	
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public ArrayList getIteratorList() {
		return iteratorList;
	}
	public void setIteratorList(ArrayList iteratorList) {
		this.iteratorList = iteratorList;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonthView() {
		return monthView;
	}
	public void setMonthView(String monthView) {
		this.monthView = monthView;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getIncrementCode() {
		return incrementCode;
	}
	public void setIncrementCode(String incrementCode) {
		this.incrementCode = incrementCode;
	}
	public String getIncrementStatus() {
		return incrementStatus;
	}
	public void setIncrementStatus(String incrementStatus) {
		this.incrementStatus = incrementStatus;
	}
	public String getListIncrementCode() {
		return listIncrementCode;
	}
	public void setListIncrementCode(String listIncrementCode) {
		this.listIncrementCode = listIncrementCode;
	}
	public String getListMonthName() {
		return listMonthName;
	}
	public void setListMonthName(String listMonthName) {
		this.listMonthName = listMonthName;
	}
	public String getListMonthCode() {
		return listMonthCode;
	}
	public void setListMonthCode(String listMonthCode) {
		this.listMonthCode = listMonthCode;
	}
	public String getListYear() {
		return listYear;
	}
	public void setListYear(String listYear) {
		this.listYear = listYear;
	}
	public String getListDeptName() {
		return listDeptName;
	}
	public void setListDeptName(String listDeptName) {
		this.listDeptName = listDeptName;
	}
	public String getListDeptId() {
		return listDeptId;
	}
	public void setListDeptId(String listDeptId) {
		this.listDeptId = listDeptId;
	}
	public String getListBranchName() {
		return listBranchName;
	}
	public void setListBranchName(String listBranchName) {
		this.listBranchName = listBranchName;
	}
	public String getListBranchId() {
		return listBranchId;
	}
	public void setListBranchId(String listBranchId) {
		this.listBranchId = listBranchId;
	}
	public String getListDivName() {
		return listDivName;
	}
	public void setListDivName(String listDivName) {
		this.listDivName = listDivName;
	}
	public String getListDivId() {
		return listDivId;
	}
	public void setListDivId(String listDivId) {
		this.listDivId = listDivId;
	}
	public String getListIncrementStatus() {
		return listIncrementStatus;
	}
	public void setListIncrementStatus(String listIncrementStatus) {
		this.listIncrementStatus = listIncrementStatus;
	}
	public boolean isShowFlag() {
		return showFlag;
	}
	public void setShowFlag(boolean showFlag) {
		this.showFlag = showFlag;
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
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	public ArrayList getCreditHeader() {
		return creditHeader;
	}
	public void setCreditHeader(ArrayList creditHeader) {
		this.creditHeader = creditHeader;
	}
}
