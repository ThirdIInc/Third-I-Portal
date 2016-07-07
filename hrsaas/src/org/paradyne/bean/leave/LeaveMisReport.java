package org.paradyne.bean.leave;

/**
 * @author Hemant

 */
import java.util.ArrayList;


import org.paradyne.lib.BeanBase;

public class LeaveMisReport extends BeanBase {
	
	private String empId="";
	private String token="";
	
	private String fromDate="";
	private String toDate="";
	private String levType="";
	private String leaveBal="";
	
	
	private String AbsFromDate="";
	private String AbsToDate="";	
	
	private String LeaveAppFromDate="";
	private String LeaveAppToDate="";
	
	
	private String empName="";
	private String levCode="";
	private String leaveId="";
	private String divCode="";
	
	private String desgCode="";
	private String deptCode="";
	private String divName ="";
	private String desgName="";
	private String deptName="";
	private String reportType="";
	private String center="";
	private String centerNo="";
	private String centerName="";
	private String payBillNo="";
	private String category="";
	private String rank="";
	private String month="";
	private String year="";
	private String report = "";
	
	String typeCode="";
	private String empType="";
	
	private String dropDowntype="";
	
	private String serviceStatus="";
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	/**
	 * @return the dropDowntype
	 */
	public String getDropDowntype() {
		return dropDowntype;
	}
	/**
	 * @param dropDowntype the dropDowntype to set
	 */
	public void setDropDowntype(String dropDowntype) {
		this.dropDowntype = dropDowntype;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public LeaveMisReport(){
		
	}
	public String getPayBillNo() {
		return payBillNo;
	}
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterNo() {
		return centerNo;
	}
	public void setCenterNo(String centerNo) {
		this.centerNo = centerNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getLeaveBal() {
		return leaveBal;
	}
	public void setLeaveBal(String leaveBal) {
		this.leaveBal = leaveBal;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	public String getLevCode() {
		return levCode;
	}
	public void setLevCode(String levCode) {
		this.levCode = levCode;
	}
	public String getLevType() {
		return levType;
	}
	public void setLevType(String levType) {
		this.levType = levType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAbsFromDate() {
		return AbsFromDate;
	}
	public void setAbsFromDate(String absFromDate) {
		AbsFromDate = absFromDate;
	}
	public String getAbsToDate() {
		return AbsToDate;
	}
	public void setAbsToDate(String absToDate) {
		AbsToDate = absToDate;
	}
	public String getLeaveAppFromDate() {
		return LeaveAppFromDate;
	}
	public void setLeaveAppFromDate(String leaveAppFromDate) {
		LeaveAppFromDate = leaveAppFromDate;
	}
	public String getLeaveAppToDate() {
		return LeaveAppToDate;
	}
	public void setLeaveAppToDate(String leaveAppToDate) {
		LeaveAppToDate = leaveAppToDate;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getDesgCode() {
		return desgCode;
	}
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
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
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
	
	
}
