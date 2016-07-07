package org.paradyne.bean.leave;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author radha
 * 
 */
public class LeaveBalance extends BeanBase implements Serializable {

	String empId = "";
	String empName = "";
	String opBal = "";
	String clBal = "";
	String onholdBal = "";
	String opBalHrs = "";
	String clBalHrs = "";
	String onholdBalHrs = "";
	String leaveCode = "";
	String leaveName = "";
	String deptName = "";
	String centerName = "";
	String rankName = "";
	String empToken = "";
	ArrayList leaveList = null;
	ArrayList leveList = null;
	String uploadFileName="";
	String profileEmpName="";
	private String isNotGeneralUser="false";
	private boolean hourFlag = false;
    private boolean editFlag = false;
    String noData="";
	/**
	 * @return the hourFlag
	 */
	public boolean isHourFlag() {
		return hourFlag;
	}

	/**
	 * @param hourFlag the hourFlag to set
	 */
	public void setHourFlag(boolean hourFlag) {
		this.hourFlag = hourFlag;
	}

	public LeaveBalance() {

	}

	/**
	 * Constructor for LeaveBalance
	 * @param empId
	 * @param empName
	 * @param opBal
	 * @param clBal
	 * @param leaveCode
	 * @param leaveName
	 * @param deptName
	 * @param centerName
	 */
	public LeaveBalance(String empId, String empName, String opBal,
			String clBal, String leaveCode, String leaveName, String deptName,
			String centerName) {
		this.empId = empId;
		this.empName = empName;
		this.opBal = opBal;
		this.clBal = clBal;
		this.leaveCode = leaveCode;
		this.leaveName = leaveName;
		this.deptName = deptName;
		this.centerName = centerName;

	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName
	 *            the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/**
	 * @return the clBal
	 */
	public String getClBal() {
		return clBal;
	}

	/**
	 * @param clBal
	 *            the clBal to set
	 */
	public void setClBal(String clBal) {
		this.clBal = clBal;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
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
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the leaveCode
	 */
	public String getLeaveCode() {
		return leaveCode;
	}

	/**
	 * @param leaveCode
	 *            the leaveCode to set
	 */
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	/**
	 * @return the leaveName
	 */
	public String getLeaveName() {
		return leaveName;
	}

	/**
	 * @param leaveName
	 *            the leaveName to set
	 */
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	/**
	 * @return the list
	 */

	/**
	 * @return the opBal
	 */
	public String getOpBal() {
		return opBal;
	}

	/**
	 * @param opBal
	 *            the opBal to set
	 */
	public void setOpBal(String opBal) {
		this.opBal = opBal;
	}

	/**
	 * @return the leaveList
	 */
	public ArrayList getLeaveList() {
		return leaveList;
	}

	/**
	 * @param leaveList
	 *            the leaveList to set
	 */
	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}

	/**
	 * @return the onholdBal
	 */
	public String getOnholdBal() {
		return onholdBal;
	}

	/**
	 * @param onholdBal the onholdBal to set
	 */
	public void setOnholdBal(String onholdBal) {
		this.onholdBal = onholdBal;
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
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the leveList
	 */
	public ArrayList getLeveList() {
		return leveList;
	}

	/**
	 * @param leveList the leveList to set
	 */
	public void setLeveList(ArrayList leveList) {
		this.leveList = leveList;
	}

	/**
	 * @return the opBalHrs
	 */
	public String getOpBalHrs() {
		return opBalHrs;
	}

	/**
	 * @param opBalHrs the opBalHrs to set
	 */
	public void setOpBalHrs(String opBalHrs) {
		this.opBalHrs = opBalHrs;
	}

	/**
	 * @return the clBalHrs
	 */
	public String getClBalHrs() {
		return clBalHrs;
	}

	/**
	 * @param clBalHrs the clBalHrs to set
	 */
	public void setClBalHrs(String clBalHrs) {
		this.clBalHrs = clBalHrs;
	}

	/**
	 * @return the onholdBalHrs
	 */
	public String getOnholdBalHrs() {
		return onholdBalHrs;
	}

	/**
	 * @param onholdBalHrs the onholdBalHrs to set
	 */
	public void setOnholdBalHrs(String onholdBalHrs) {
		this.onholdBalHrs = onholdBalHrs;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getProfileEmpName() {
		return profileEmpName;
	}

	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

}