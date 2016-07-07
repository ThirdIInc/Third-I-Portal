/**
 * 
 */
package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author riteshr
 *
 */
public class LeaveHistory extends BeanBase implements Serializable {
	
	public String leaveId="";
	public String empId="";
	public String empName="";
	public String tokenNo="";
	public String rank="";
	public String center="";
	public String leaveType="";
	public String leaveTypeId="";
	public String leaveDays="";
	public String fromDate="";
	public String toDate="";
	public String paracode="";
	private ArrayList leaveList ;
	/**
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}
	/**
	 * @param center the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
	}
	
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
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
	 * @return the leaveDays
	 */
	public String getLeaveDays() {
		return leaveDays;
	}
	/**
	 * @param leaveDays the leaveDays to set
	 */
	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
	}
	/**
	 * @return the leaveId
	 */
	public String getLeaveId() {
		return leaveId;
	}
	/**
	 * @param leaveId the leaveId to set
	 */
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	/**
	 * @return the leaveList
	 */
	public ArrayList getLeaveList() {
		return leaveList;
	}
	/**
	 * @param leaveList the leaveList to set
	 */
	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}
	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}
	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	/**
	 * @return the leaveTypeId
	 */
	public String getLeaveTypeId() {
		return leaveTypeId;
	}
	/**
	 * @param leaveTypeId the leaveTypeId to set
	 */
	public void setLeaveTypeId(String leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}
	/**
	 * @param paracode the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
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
	 * @return the tokenNo
	 */
	public String getTokenNo() {
		return tokenNo;
	}
	/**
	 * @param tokenNo the tokenNo to set
	 */
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

}
