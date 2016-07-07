/**
 * Bhushan Apr 1, 2008
 */

package org.paradyne.bean.attendance;

import org.paradyne.lib.BeanBase;

public class DailyAttendanceReport extends BeanBase {
	private String fromDate;
	private String toDate;
	private String divCode = "";
	private String divName = "";
	private String typeCode = "";
	private String typeName = "";
	private String payBillNo = "";
	private String payBillName = "";
	private String brnCode = "";
	private String brnName = "";
	private String deptCode = "";
	private String deptName = "";
	private String reportType;
	private String empCode;
	private String empName;
	private String empToken;
	private String srchStatus1;
	private String srchStatus2;

	private String report = "";
	
	
	
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the divCode
	 */
	public String getDivCode() {
		return divCode;
	}
	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	/**
	 * @return the divName
	 */
	public String getDivName() {
		return divName;
	}
	/**
	 * @param divName the divName to set
	 */
	public void setDivName(String divName) {
		this.divName = divName;
	}
	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the payBillNo
	 */
	public String getPayBillNo() {
		return payBillNo;
	}
	/**
	 * @param payBillNo the payBillNo to set
	 */
	public void setPayBillNo(String payBillNo) {
		this.payBillNo = payBillNo;
	}
	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}
	/**
	 * @param payBillName the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}
	/**
	 * @return the brnCode
	 */
	public String getBrnCode() {
		return brnCode;
	}
	/**
	 * @param brnCode the brnCode to set
	 */
	public void setBrnCode(String brnCode) {
		this.brnCode = brnCode;
	}
	/**
	 * @return the brnName
	 */
	public String getBrnName() {
		return brnName;
	}
	/**
	 * @param brnName the brnName to set
	 */
	public void setBrnName(String brnName) {
		this.brnName = brnName;
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
	 * @return the srchStatus1
	 */
	public String getSrchStatus1() {
		return srchStatus1;
	}
	/**
	 * @param srchStatus1 the srchStatus1 to set
	 */
	public void setSrchStatus1(String srchStatus1) {
		this.srchStatus1 = srchStatus1;
	}
	/**
	 * @return the srchStatus2
	 */
	public String getSrchStatus2() {
		return srchStatus2;
	}
	/**
	 * @param srchStatus2 the srchStatus2 to set
	 */
	public void setSrchStatus2(String srchStatus2) {
		this.srchStatus2 = srchStatus2;
	}
	/**
	 * @return the report
	 */
	public String getReport() {
		return report;
	}
	/**
	 * @param report the report to set
	 */
	public void setReport(String report) {
		this.report = report;
	}
}