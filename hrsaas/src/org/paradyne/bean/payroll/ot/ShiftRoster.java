package org.paradyne.bean.payroll.ot;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

/**Created on 22 Feb 2012.
 * @author aa1385
 */

public class ShiftRoster extends BeanBase {
	/**
	 * fromDate.
	 */
	private String fromDate = "";
	/**
	 * toDate.
	 */
	private String toDate = "";
	/**
	 * managerID.
	 */
	private String managerID = "";
	/**
	 * managerName.
	 */
	private String managerName = "";
	/**
	 * managerToken.
	 */
	private String managerToken = "";
	/**
	 * employeeID.
	 */
	private String employeeID = "";
	/**
	 * employeeName.
	 */
	private String employeeName = "";
	/**
	 * employeeToken.
	 */
	private String employeeToken = "";
	/**
	 * shiftID.
	 */
	private String shiftID = "";
	/**
	 * shiftName.
	 */
	private String shiftName = "";
	/**
	 * fromShiftDate.
	 */
	private String fromShiftDate = "";
	/**
	 * toShiftDate.
	 */
	private String toShiftDate = "";
	/**
	 * shiftRosterID.
	 */
	private String shiftRosterID = "";
	/**
	 * myPage.
	 */
	private String myPage = "";
	/**
	 * modeLength.
	 */
	private String modeLength = "";
	/**
	 * totalRecords.
	 */
	private String totalRecords = "";
	/**
	 * iteratorlist.
	 */
	ArrayList iteratorlist;
	/**
	 * ittShiftRosterID.
	 */
	private String ittShiftRosterID = "";
	/**
	 * ittEmployeeID.
	 */
	private String ittEmployeeID = "";
	/**
	 * ittEmployeeName.
	 */
	private String ittEmployeeName = "";
	/**
	 * ittShiftID.
	 */
	private String ittShiftID = "";
	/**
	 * ittShiftName
	 */
	private String ittShiftName = "";
	/**
	 * ittFromShiftDate.
	 */
	private String ittFromShiftDate = "";
	/**
	 * ittEmployeeToken.
	 */
	private String ittEmployeeToken = "";
	/**
	 * ittShiftDate.
	 */
	private String ittShiftDate = "";
	/**
	 * rptType.
	 */
	private String rptType = "";
	/**
	 * dateList.
	 */
	ArrayList dateList;
	/**
	 * dayName.
	 */
	private String dayName = "";
	/**
	 * ittDateList.
	 */
	ArrayList ittDateList;
	/**
	 * itshiftName.
	 */
	private String itshiftName = "";
	/**
	 * @return the fromDate
	 */
	public final String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public final void setFromDate(final String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public final String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public final void setToDate(final String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the managerID
	 */
	public final String getManagerID() {
		return managerID;
	}
	/**
	 * @param managerID the managerID to set
	 */
	public final void setManagerID(final String managerID) {
		this.managerID = managerID;
	}
	/**
	 * @return the managerName
	 */
	public final String getManagerName() {
		return managerName;
	}
	/**
	 * @param managerName the managerName to set
	 */
	public final void setManagerName(final String managerName) {
		this.managerName = managerName;
	}
	/**
	 * @return the managerToken
	 */
	public final String getManagerToken() {
		return managerToken;
	}
	/**
	 * @param managerToken the managerToken to set
	 */
	public final void setManagerToken(final String managerToken) {
		this.managerToken = managerToken;
	}
	/**
	 * @return the employeeID
	 */
	public final String getEmployeeID() {
		return employeeID;
	}
	/**
	 * @param employeeID the employeeID to set
	 */
	public final void setEmployeeID(final String employeeID) {
		this.employeeID = employeeID;
	}
	/**
	 * @return the employeeName
	 */
	public final String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public final void setEmployeeName(final String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the employeeToken
	 */
	public final String getEmployeeToken() {
		return employeeToken;
	}
	/**
	 * @param employeeToken the employeeToken to set
	 */
	public final void setEmployeeToken(final String employeeToken) {
		this.employeeToken = employeeToken;
	}
	/**
	 * @return the shiftID
	 */
	public final String getShiftID() {
		return shiftID;
	}
	/**
	 * @param shiftID the shiftID to set
	 */
	public final void setShiftID(final String shiftID) {
		this.shiftID = shiftID;
	}
	/**
	 * @return the shiftName
	 */
	public final String getShiftName() {
		return shiftName;
	}
	/**
	 * @param shiftName the shiftName to set
	 */
	public final void setShiftName(final String shiftName) {
		this.shiftName = shiftName;
	}
	/**
	 * @return the fromShiftDate
	 */
	public final String getFromShiftDate() {
		return fromShiftDate;
	}
	/**
	 * @param fromShiftDate the fromShiftDate to set
	 */
	public final void setFromShiftDate(final String fromShiftDate) {
		this.fromShiftDate = fromShiftDate;
	}
	/**
	 * @return the toShiftDate
	 */
	public final String getToShiftDate() {
		return toShiftDate;
	}
	/**
	 * @param toShiftDate the toShiftDate to set
	 */
	public final void setToShiftDate(final String toShiftDate) {
		this.toShiftDate = toShiftDate;
	}
	/**
	 * @return the shiftRosterID
	 */
	public final String getShiftRosterID() {
		return shiftRosterID;
	}
	/**
	 * @param shiftRosterID the shiftRosterID to set
	 */
	public final void setShiftRosterID(final String shiftRosterID) {
		this.shiftRosterID = shiftRosterID;
	}
	/**
	 * @return the myPage
	 */
	public final String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public final void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the modeLength
	 */
	public final String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public final void setModeLength(final String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalRecords
	 */
	public final String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public final void setTotalRecords(final String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the iteratorlist
	 */
	public final ArrayList getIteratorlist() {
		return iteratorlist;
	}
	/**
	 * @param iteratorlist the iteratorlist to set
	 */
	public final void setIteratorlist(final ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	/**
	 * @return the ittShiftRosterID
	 */
	public final String getIttShiftRosterID() {
		return ittShiftRosterID;
	}
	/**
	 * @param ittShiftRosterID the ittShiftRosterID to set
	 */
	public final void setIttShiftRosterID(final String ittShiftRosterID) {
		this.ittShiftRosterID = ittShiftRosterID;
	}
	/**
	 * @return the ittEmployeeID
	 */
	public final String getIttEmployeeID() {
		return ittEmployeeID;
	}
	/**
	 * @param ittEmployeeID the ittEmployeeID to set
	 */
	public final void setIttEmployeeID(final String ittEmployeeID) {
		this.ittEmployeeID = ittEmployeeID;
	}
	/**
	 * @return the ittEmployeeName
	 */
	public final String getIttEmployeeName() {
		return ittEmployeeName;
	}
	/**
	 * @param ittEmployeeName the ittEmployeeName to set
	 */
	public final void setIttEmployeeName(final String ittEmployeeName) {
		this.ittEmployeeName = ittEmployeeName;
	}
	/**
	 * @return the ittShiftID
	 */
	public final String getIttShiftID() {
		return ittShiftID;
	}
	/**
	 * @param ittShiftID the ittShiftID to set
	 */
	public final void setIttShiftID(final String ittShiftID) {
		this.ittShiftID = ittShiftID;
	}
	/**
	 * @return the ittShiftName
	 */
	public final String getIttShiftName() {
		return ittShiftName;
	}
	/**
	 * @param ittShiftName the ittShiftName to set
	 */
	public final void setIttShiftName(final String ittShiftName) {
		this.ittShiftName = ittShiftName;
	}
	/**
	 * @return the ittFromShiftDate
	 */
	public final String getIttFromShiftDate() {
		return ittFromShiftDate;
	}
	/**
	 * @param ittFromShiftDate the ittFromShiftDate to set
	 */
	public final void setIttFromShiftDate(final String ittFromShiftDate) {
		this.ittFromShiftDate = ittFromShiftDate;
	}
	/**
	 * @return the ittEmployeeToken
	 */
	public final String getIttEmployeeToken() {
		return ittEmployeeToken;
	}
	/**
	 * @param ittEmployeeToken the ittEmployeeToken to set
	 */
	public final void setIttEmployeeToken(final String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}
	/**
	 * @return the ittShiftDate
	 */
	public final String getIttShiftDate() {
		return ittShiftDate;
	}
	/**
	 * @param ittShiftDate the ittShiftDate to set
	 */
	public final void setIttShiftDate(final String ittShiftDate) {
		this.ittShiftDate = ittShiftDate;
	}
	/**
	 * @return the rptType
	 */
	public final String getRptType() {
		return rptType;
	}
	/**
	 * @param rptType the rptType to set
	 */
	public final void setRptType(final String rptType) {
		this.rptType = rptType;
	}
	/**
	 * @return the dateList
	 */
	public final ArrayList getDateList() {
		return dateList;
	}
	/**
	 * @param dateList the dateList to set
	 */
	public final void setDateList(final ArrayList dateList) {
		this.dateList = dateList;
	}
	/**
	 * @return the dayName
	 */
	public final String getDayName() {
		return dayName;
	}
	/**
	 * @param dayName the dayName to set
	 */
	public final void setDayName(final String dayName) {
		this.dayName = dayName;
	}
	/**
	 * @return the ittDateList
	 */
	public final ArrayList getIttDateList() {
		return ittDateList;
	}
	/**
	 * @param ittDateList the ittDateList to set
	 */
	public final void setIttDateList(final ArrayList ittDateList) {
		this.ittDateList = ittDateList;
	}
	/**
	 * @return the itshiftName
	 */
	public final String getItshiftName() {
		return itshiftName;
	}
	/**
	 * @param itshiftName the itshiftName to set
	 */
	public final void setItshiftName(final String itshiftName) {
		this.itshiftName = itshiftName;
	}
}
