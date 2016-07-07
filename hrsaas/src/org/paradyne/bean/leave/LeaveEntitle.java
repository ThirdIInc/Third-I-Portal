/**
 * 
 */
package org.paradyne.bean.leave;

import org.paradyne.lib.BeanBase;

/**
 * @author lakkichand
 *
 *
 */
public class LeaveEntitle extends BeanBase {

	private String empTypeCode = "";
	private String empTypeName = "";
	private String month = "";
	private String year = "";
	private String entitleDate="";
	
	private String divisionCode="";
	private String divisionName ="";
	
	public String getEntitleDate() {
		return entitleDate;
	}
	public void setEntitleDate(String entitleDate) {
		this.entitleDate = entitleDate;
	}
	/**
	 * @return the empType
	 */
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	/**
	 * @param empType the empType to set
	 */
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
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
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
}
