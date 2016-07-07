/**
 * 
 */
package org.paradyne.bean.payroll.salary;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author REEBA JOSEPH
 * 22 NOVEMBER 2010
 *
 */
public class LTCProcess extends BeanBase {
	private String ltcCodeIt = "";
	private String monthIt = "";
	private String monthCodeIt = "";
	private String yearIt = "";
	private String divisionIt = "";
	private String divisionCodeIt = "";
	private String statusIt = "";
	
	private boolean recordsAvailable = false;
	ArrayList<Object> processedLtcList = null;
	private int totalRecords;
	private String myPage = "";
	
	private String month = "";
	private String year = "";
	private String monthCode = "";
	private String divisionId = "";
	private String divisionName = "";
	private String status = "";
	private String ltcCode = "";
	
	private boolean editFlag = false;
	private String branchViewId = "";
	private String branchViewName = "";
	private String departmentViewId = "";
	private String departmentViewName = "";
	private String employeeTypeViewId = "";
	private String employeeTypeViewName = "";
	private String payBillViewId = "";
	private String payBillViewName = "";
	private String empViewId = "";
	private String empViewName = "";
	private String empStatusView = "";
	private String empTokenView = "";
	
	private String empId;
	private String empToken;
	private String empName;
	private String attnDays;
	private String trvlDays;
	private String ltcDays;
	private String ltcAmount;
	
	private String flag="false";
	private String myGridPage;
	private String noData="false"; 
	private ArrayList<Object> empList;
	
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
	public boolean isEditFlag() {
		return editFlag;
	}
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public boolean isRecordsAvailable() {
		return recordsAvailable;
	}
	public void setRecordsAvailable(boolean recordsAvailable) {
		this.recordsAvailable = recordsAvailable;
	}
	public ArrayList<Object> getProcessedLtcList() {
		return processedLtcList;
	}
	public void setProcessedLtcList(ArrayList<Object> processedLtcList) {
		this.processedLtcList = processedLtcList;
	}
	public String getLtcCodeIt() {
		return ltcCodeIt;
	}
	public void setLtcCodeIt(String ltcCodeIt) {
		this.ltcCodeIt = ltcCodeIt;
	}
	public String getMonthIt() {
		return monthIt;
	}
	public void setMonthIt(String monthIt) {
		this.monthIt = monthIt;
	}
	public String getMonthCodeIt() {
		return monthCodeIt;
	}
	public void setMonthCodeIt(String monthCodeIt) {
		this.monthCodeIt = monthCodeIt;
	}
	public String getYearIt() {
		return yearIt;
	}
	public void setYearIt(String yearIt) {
		this.yearIt = yearIt;
	}
	public String getDivisionIt() {
		return divisionIt;
	}
	public void setDivisionIt(String divisionIt) {
		this.divisionIt = divisionIt;
	}
	public String getDivisionCodeIt() {
		return divisionCodeIt;
	}
	public void setDivisionCodeIt(String divisionCodeIt) {
		this.divisionCodeIt = divisionCodeIt;
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
	public String getMonthCode() {
		return monthCode;
	}
	public void setMonthCode(String monthCode) {
		this.monthCode = monthCode;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLtcCode() {
		return ltcCode;
	}
	public void setLtcCode(String ltcCode) {
		this.ltcCode = ltcCode;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAttnDays() {
		return attnDays;
	}
	public void setAttnDays(String attnDays) {
		this.attnDays = attnDays;
	}
	public String getTrvlDays() {
		return trvlDays;
	}
	public void setTrvlDays(String trvlDays) {
		this.trvlDays = trvlDays;
	}
	public String getLtcDays() {
		return ltcDays;
	}
	public void setLtcDays(String ltcDays) {
		this.ltcDays = ltcDays;
	}
	public String getLtcAmount() {
		return ltcAmount;
	}
	public void setLtcAmount(String ltcAmount) {
		this.ltcAmount = ltcAmount;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMyGridPage() {
		return myGridPage;
	}
	public void setMyGridPage(String myGridPage) {
		this.myGridPage = myGridPage;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList<Object> getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList<Object> empList) {
		this.empList = empList;
	}
	public String getStatusIt() {
		return statusIt;
	}
	public void setStatusIt(String statusIt) {
		this.statusIt = statusIt;
	}
}
