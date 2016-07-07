/**
 * 
 */
package org.paradyne.bean.attendance;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 *
 */
public class DailyMusterReport extends BeanBase {
	private String month;
	private String year;
	private String divCode = "";
	private String divName = "";
	private String brnCode = "";
	private String brnName = "";
	private String reportType;
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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
}
