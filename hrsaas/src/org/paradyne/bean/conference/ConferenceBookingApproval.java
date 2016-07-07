package org.paradyne.bean.conference;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ConferenceBookingApproval extends BeanBase {
	
	private String empID;
	private String empToken;
	private String approver;	
	private String status;
	//private String ammount;
	private String conferenceID;
	private String apprflag = "false";
	private String confAppStatus;
	private String level;
	private String noData = "false";	
	private String empName;
	private String appliedDate;
	private String remark;
	private String hiddenStatus;
	private String comment;
	private String statusIterator;
	private String isConfRoomAvailable="true";
	private boolean approveFlag =false;
	private String cancelAppFlag="false";
	private String confFromTime="";
	private String confToTime="";
	
	private ArrayList<ConferenceBookingApproval> list;

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConferenceID() {
		return conferenceID;
	}

	public void setConferenceID(String conferenceID) {
		this.conferenceID = conferenceID;
	}

	public String getApprflag() {
		return apprflag;
	}

	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}

	public String getConfAppStatus() {
		return confAppStatus;
	}

	public void setConfAppStatus(String confAppStatus) {
		this.confAppStatus = confAppStatus;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHiddenStatus() {
		return hiddenStatus;
	}

	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatusIterator() {
		return statusIterator;
	}

	public void setStatusIterator(String statusIterator) {
		this.statusIterator = statusIterator;
	}

	public ArrayList<ConferenceBookingApproval> getList() {
		return list;
	}

	public void setList(ArrayList<ConferenceBookingApproval> list) {
		this.list = list;
	}

	public boolean isApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(boolean approveFlag) {
		this.approveFlag = approveFlag;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getIsConfRoomAvailable() {
		return isConfRoomAvailable;
	}

	public void setIsConfRoomAvailable(String isConfRoomAvailable) {
		this.isConfRoomAvailable = isConfRoomAvailable;
	}

	public String getCancelAppFlag() {
		return cancelAppFlag;
	}

	public void setCancelAppFlag(String cancelAppFlag) {
		this.cancelAppFlag = cancelAppFlag;
	}

	public String getConfFromTime() {
		return confFromTime;
	}

	public void setConfFromTime(String confFromTime) {
		this.confFromTime = confFromTime;
	}

	public String getConfToTime() {
		return confToTime;
	}

	public void setConfToTime(String confToTime) {
		this.confToTime = confToTime;
	}
}
