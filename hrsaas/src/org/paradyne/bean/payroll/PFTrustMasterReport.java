package org.paradyne.bean.payroll;

import org.paradyne.lib.BeanBase;

public class PFTrustMasterReport extends BeanBase {
	private String fromMonth 	= "";
	private String fromYear 	= "";
	private String toMonth 		= "";
	private String toYear 		= "";
	private String sDivName 	= "";
	private String sDivId 		= "";
	private String sBrchName 	= "";
	private String sBrchId 		= "";
	private String sDeptName 	= "";
	private String sDeptId 		= "";
	private String reportType = "";
	/**
	 * @return
	 */
	public String getFromMonth() {
		return fromMonth;
	}
	/**
	 * @param fromMonth
	 */
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	/**
	 * @return
	 */
	public String getFromYear() {
		return fromYear;
	}
	/**
	 * @param fromYear
	 */
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	/**
	 * @return
	 */
	public String getToMonth() {
		return toMonth;
	}
	/**
	 * @param toMonth
	 */
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	/**
	 * @return
	 */
	public String getToYear() {
		return toYear;
	}
	/**
	 * @param toYear
	 */
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	/**
	 * @return
	 */
	public String getSDivName() {
		return sDivName;
	}
	/**
	 * @param divName
	 */
	public void setSDivName(String divName) {
		sDivName = divName;
	}
	/**
	 * @return
	 */
	public String getSDivId() {
		return sDivId;
	}
	/**
	 * @param divId
	 */
	public void setSDivId(String divId) {
		sDivId = divId;
	}
	/**
	 * @return
	 */
	public String getSBrchName() {
		return sBrchName;
	}
	/**
	 * @param brchName
	 */
	public void setSBrchName(String brchName) {
		sBrchName = brchName;
	}
	/**
	 * @return
	 */
	public String getSBrchId() {
		return sBrchId;
	}
	/**
	 * @param brchId
	 */
	public void setSBrchId(String brchId) {
		sBrchId = brchId;
	}
	/**
	 * @return
	 */
	public String getSDeptName() {
		return sDeptName;
	}
	/**
	 * @param deptName
	 */
	public void setSDeptName(String deptName) {
		sDeptName = deptName;
	}
	/**
	 * @return
	 */
	public String getSDeptId() {
		return sDeptId;
	}
	/**
	 * @param deptId
	 */
	public void setSDeptId(String deptId) {
		sDeptId = deptId;
	}
	/**
	 * @return
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}
