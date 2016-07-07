/**
 * 
 */
package org.paradyne.bean.leave;

import org.paradyne.lib.BeanBase;

/**
 * @author diptip
 * 
 */
public class LeaveApplicationMis extends BeanBase {

	/**
	 * 
	 */
	private String empId = "";
	private String empName = "";

	private String token = "";

	private String frmDate = "";
	private String toDate = "";
	private String appFromDate = "";
	private String appToDate = "";
	private String reportType = "";
	private String divCode = "";
	private String desgCode = "";
	private String deptCode = "";
	private String divsion = "";
	private String desgName = "";
	private String deptName = "";

	private String center = "";
	private String centerNo = "";
	private String centerName = "";

	private String rank = "";
	private String status = "";

	private String empStatus="";
	private String reportTypeV2="";
	private String report="";
	private String today="";
	
	
	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFrmDate() {
		return frmDate;
	}

	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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

	public String getDivsion() {
		return divsion;
	}

	public void setDivsion(String divsion) {
		this.divsion = divsion;
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

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getCenterNo() {
		return centerNo;
	}

	public void setCenterNo(String centerNo) {
		this.centerNo = centerNo;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getAppFromDate() {
		return appFromDate;
	}

	public void setAppFromDate(String appFromDate) {
		this.appFromDate = appFromDate;
	}

	public String getAppToDate() {
		return appToDate;
	}

	public void setAppToDate(String appToDate) {
		this.appToDate = appToDate;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportTypeV2() {
		return reportTypeV2;
	}

	public void setReportTypeV2(String reportTypeV2) {
		this.reportTypeV2 = reportTypeV2;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

}
