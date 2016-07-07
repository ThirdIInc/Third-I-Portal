package org.paradyne.bean.helpdesk;

import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class HelpDeskMisReport extends BeanBase {

	private String employeeCode;
	private String employeeToken;
	private String employeeName;
	private String fromDate;
	private String toDate;
	private String status;
	private String report;
	//For dropdown box
	TreeMap statusMap;
	/**
	 * Added by Nilesh Dhandare on 6th Jan 2011.
	 */
	
	private String teamMemberCode = "";
	private String teamMemberToken = "";
	private String teamMemberName = "";
	private String deptCode = "";
	private String deptName = "";
	private String catagoryCode = "";
	private String catagoryName = "";
	private String reportType  = "";
	private String centerName  = "";
	private String centerId  = "";
							
						
	
	
	
	
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the teamMemberCode
	 */
	public String getTeamMemberCode() {
		return teamMemberCode;
	}
	/**
	 * @param teamMemberCode the teamMemberCode to set
	 */
	public void setTeamMemberCode(String teamMemberCode) {
		this.teamMemberCode = teamMemberCode;
	}
	/**
	 * @return the teamMemberToken
	 */
	public String getTeamMemberToken() {
		return teamMemberToken;
	}
	/**
	 * @param teamMemberToken the teamMemberToken to set
	 */
	public void setTeamMemberToken(String teamMemberToken) {
		this.teamMemberToken = teamMemberToken;
	}
	/**
	 * @return the teamMemberName
	 */
	public String getTeamMemberName() {
		return teamMemberName;
	}
	/**
	 * @param teamMemberName the teamMemberName to set
	 */
	public void setTeamMemberName(String teamMemberName) {
		this.teamMemberName = teamMemberName;
	}
	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the catagoryCode
	 */
	public String getCatagoryCode() {
		return catagoryCode;
	}
	/**
	 * @param catagoryCode the catagoryCode to set
	 */
	public void setCatagoryCode(String catagoryCode) {
		this.catagoryCode = catagoryCode;
	}
	/**
	 * @return the catagoryName
	 */
	public String getCatagoryName() {
		return catagoryName;
	}
	/**
	 * @param catagoryName the catagoryName to set
	 */
	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public TreeMap getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(TreeMap statusMap) {
		this.statusMap = statusMap;
	}
}
