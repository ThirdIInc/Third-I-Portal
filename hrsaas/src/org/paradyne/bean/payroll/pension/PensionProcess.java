/**
 * 
 */
package org.paradyne.bean.payroll.pension;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0418 Prakash
 * Date 30-09-2009
 * 
 */
public class PensionProcess extends BeanBase {

	private String month;
	private String year;
	private String divCode;
	private String divName;
	
	private String status;
	private String ledgerCode;
	
	private String empId;
	private String empCode;
	private String empName;
	private String penType;
	private String penArrear;
	private String penAmount;
	private String commAmount;
	private String recAmount;
	private String miscAmount;
	private String netAmount;
	private String monthView;

	private String flag="false";
	
	private String myPage;
	private String noData="false"; 
	
	
	//ADDED BY REEBA
	private String listMyPage ;
	private String hiddencode;
	private String show;
	private String totalRecords;
	private String listLength="false";
	private String hdeleteCode;
	ArrayList iteratorlist;
	
	private String listPensionCode = "";
	private String listMonth = "";
	private String listMonthCode = "";
	private String listYear = "";
	private String listDivName = "";
	private String listDivId = "";
	private String listPensionStatus = "";
	
	private boolean filterFlag = false;
	private String branchViewId="";
	private String branchViewName="";
	private String departmentViewId="";
	private String departmentViewName="";
	private String employeeTypeViewId="";
	private String employeeTypeViewName="";
	private String payBillViewId="";
	private String payBillViewName="";
	private String empViewId;
	private String empViewName;
	private String empStatusView;
	private String empTokenView;
	
	private ArrayList empList;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPenType() {
		return penType;
	}
	public void setPenType(String penType) {
		this.penType = penType;
	}
	public String getPenArrear() {
		return penArrear;
	}
	public void setPenArrear(String penArrear) {
		this.penArrear = penArrear;
	}
	public String getPenAmount() {
		return penAmount;
	}
	public void setPenAmount(String penAmount) {
		this.penAmount = penAmount;
	}
	public String getCommAmount() {
		return commAmount;
	}
	public void setCommAmount(String commAmount) {
		this.commAmount = commAmount;
	}
	public String getRecAmount() {
		return recAmount;
	}
	public void setRecAmount(String recAmount) {
		this.recAmount = recAmount;
	}
	public String getMiscAmount() {
		return miscAmount;
	}
	public void setMiscAmount(String miscAmount) {
		this.miscAmount = miscAmount;
	}
	public String getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
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
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public ArrayList getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getMonthView() {
		return monthView;
	}
	public void setMonthView(String monthView) {
		this.monthView = monthView;
	}
	public String getListMyPage() {
		return listMyPage;
	}
	public void setListMyPage(String listMyPage) {
		this.listMyPage = listMyPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
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
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getListPensionCode() {
		return listPensionCode;
	}
	public void setListPensionCode(String listPensionCode) {
		this.listPensionCode = listPensionCode;
	}
	public String getListMonth() {
		return listMonth;
	}
	public void setListMonth(String listMonth) {
		this.listMonth = listMonth;
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
	public String getListPensionStatus() {
		return listPensionStatus;
	}
	public void setListPensionStatus(String listPensionStatus) {
		this.listPensionStatus = listPensionStatus;
	}
	public boolean isFilterFlag() {
		return filterFlag;
	}
	public void setFilterFlag(boolean filterFlag) {
		this.filterFlag = filterFlag;
	}
	public String getBranchViewId() {
		return branchViewId;
	}
	public void setBranchViewId(String branchViewId) {
		this.branchViewId = branchViewId;
	}
	public String getBranchViewName() {
		return branchViewName;
	}
	public void setBranchViewName(String branchViewName) {
		this.branchViewName = branchViewName;
	}
	public String getDepartmentViewId() {
		return departmentViewId;
	}
	public void setDepartmentViewId(String departmentViewId) {
		this.departmentViewId = departmentViewId;
	}
	public String getDepartmentViewName() {
		return departmentViewName;
	}
	public void setDepartmentViewName(String departmentViewName) {
		this.departmentViewName = departmentViewName;
	}
	public String getEmployeeTypeViewId() {
		return employeeTypeViewId;
	}
	public void setEmployeeTypeViewId(String employeeTypeViewId) {
		this.employeeTypeViewId = employeeTypeViewId;
	}
	public String getEmployeeTypeViewName() {
		return employeeTypeViewName;
	}
	public void setEmployeeTypeViewName(String employeeTypeViewName) {
		this.employeeTypeViewName = employeeTypeViewName;
	}
	public String getPayBillViewId() {
		return payBillViewId;
	}
	public void setPayBillViewId(String payBillViewId) {
		this.payBillViewId = payBillViewId;
	}
	public String getPayBillViewName() {
		return payBillViewName;
	}
	public void setPayBillViewName(String payBillViewName) {
		this.payBillViewName = payBillViewName;
	}
	public String getEmpViewId() {
		return empViewId;
	}
	public void setEmpViewId(String empViewId) {
		this.empViewId = empViewId;
	}
	public String getEmpViewName() {
		return empViewName;
	}
	public void setEmpViewName(String empViewName) {
		this.empViewName = empViewName;
	}
	public String getEmpStatusView() {
		return empStatusView;
	}
	public void setEmpStatusView(String empStatusView) {
		this.empStatusView = empStatusView;
	}
	public String getEmpTokenView() {
		return empTokenView;
	}
	public void setEmpTokenView(String empTokenView) {
		this.empTokenView = empTokenView;
	}
	
	
}
