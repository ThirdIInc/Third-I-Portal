package org.paradyne.bean.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LeaveCancelApproval extends BeanBase {
	
	private String empId ="";

	private String tokenNo="";

	private String empName="";

	private String leaveapplicationDate="";

	private String status="";
	
	private String leaveCode="";
	
	private String leaveAppNo="";
	
	private String empCode="";
	private String level="";
	
	private String myPage;
	private String show;
	private String selectname;
	
	private String noData="false";
	private String listLength="0";
	
	ArrayList cancelList =null;

	public ArrayList getCancelList() {
		return cancelList;
	}

	public void setCancelList(ArrayList cancelList) {
		this.cancelList = cancelList;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
 	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getLeaveAppNo() {
		return leaveAppNo;
	}

	public void setLeaveAppNo(String leaveAppNo) {
		this.leaveAppNo = leaveAppNo;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @return the selectname
	 */
	public String getSelectname() {
		return selectname;
	}

	/**
	 * @param selectname the selectname to set
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return the listLength
	 */
	public String getListLength() {
		return listLength;
	}

	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	public String getLeaveapplicationDate() {
		return leaveapplicationDate;
	}

	public void setLeaveapplicationDate(String leaveapplicationDate) {
		this.leaveapplicationDate = leaveapplicationDate;
	}

}
