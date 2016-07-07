/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class InductionSchedule extends BeanBase {
	
	private String employeeName;
	private String employeeCode;
	private String empNewToken;
	private String empNewName;
	private String empNewId;
	private String divisionName;
	private String divisionCode;
	private String branchName;
	private String branchCode;
	private String deptName;
	private String deptCode;
	private String dateOfJoining;
	private String checkBox;
	private String hresumeChk;
	private String ownerFlag ="true";
	
	private String inductionName;
	private String cntPersonId;
	private String cntPersonToken;
	private String contactPerson;
	private String inductionFrom;
	private String inductionTo;
	private String inducDesc;
	private String inducVenue;
	private String actDate;
	private String actDetails;
	private String actStartTime;
	private String actEndTime;
	
	private String ownerButton;
	private String ownerType;
	private String ownerNameInt;
	private String ownerTokenInt;
	private String ownerIdInt;
	private String ownerNameExt;
	private String ownerTokenExt;
	private String ownerIdExt;
	private String ownerIntOrExt;
	private String designationInt;
	private String designationExt; 
	
	private String designation;
	private String chkVenue;
	private String checkBoxVenue;
	private String actVenue;
	private String chkEmpList;
	private String checkBoxEmpList;
	private String empName;
	private String empCode;
	private String divName;
	private String divCode;
	private String brName;
	private String brCode;
	private String deptListName;
	private String deptListCode;
	private String dtOfJoinList;
	
	private String actListDate;
	private String actListStartTime;
	private String actListEndTime;
	private String actListDetails;
	private String actListOwner;
	private String rowId;
	private String inductionCode;
	
	private String inducListName;
	private String inducFrmDt;
	private String inducToDt;
	private String feedbackStatus;
	private String myPage;
	private String totalRecords="";
	private String modeLength=""; 
	private ArrayList inductionList=null;
	
	private ArrayList activityList=null;
	
	private ArrayList participantList=null;
	
	private ArrayList employeeList=null;
	
	private String actVenueDetails = "";
	private String givenFeedbackStatus = "";
	private String actListOwnerIDIerator = "";
	

	public String getActListOwnerIDIerator() {
		return actListOwnerIDIerator;
	}

	public void setActListOwnerIDIerator(String actListOwnerIDIerator) {
		this.actListOwnerIDIerator = actListOwnerIDIerator;
	}

	public String getGivenFeedbackStatus() {
		return givenFeedbackStatus;
	}

	public void setGivenFeedbackStatus(String givenFeedbackStatus) {
		this.givenFeedbackStatus = givenFeedbackStatus;
	}

	public String getActVenueDetails() {
		return actVenueDetails;
	}

	public void setActVenueDetails(String actVenueDetails) {
		this.actVenueDetails = actVenueDetails;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public ArrayList getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList employeeList) {
		this.employeeList = employeeList;
	}

	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	

	public String getHresumeChk() {
		return hresumeChk;
	}

	public void setHresumeChk(String hresumeChk) {
		this.hresumeChk = hresumeChk;
	}

	public String getInductionName() {
		return inductionName;
	}

	public void setInductionName(String inductionName) {
		this.inductionName = inductionName;
	}

	public String getCntPersonId() {
		return cntPersonId;
	}

	public void setCntPersonId(String cntPersonId) {
		this.cntPersonId = cntPersonId;
	}

	public String getCntPersonToken() {
		return cntPersonToken;
	}

	public void setCntPersonToken(String cntPersonToken) {
		this.cntPersonToken = cntPersonToken;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getInductionFrom() {
		return inductionFrom;
	}

	public void setInductionFrom(String inductionFrom) {
		this.inductionFrom = inductionFrom;
	}

	public String getInductionTo() {
		return inductionTo;
	}

	public void setInductionTo(String inductionTo) {
		this.inductionTo = inductionTo;
	}

	public String getInducDesc() {
		return inducDesc;
	}

	public void setInducDesc(String inducDesc) {
		this.inducDesc = inducDesc;
	}

	public String getInducVenue() {
		return inducVenue;
	}

	public void setInducVenue(String inducVenue) {
		this.inducVenue = inducVenue;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}

	public String getActDetails() {
		return actDetails;
	}

	public void setActDetails(String actDetails) {
		this.actDetails = actDetails;
	}

	public String getActStartTime() {
		return actStartTime;
	}

	public void setActStartTime(String actStartTime) {
		this.actStartTime = actStartTime;
	}

	public String getActEndTime() {
		return actEndTime;
	}

	public void setActEndTime(String actEndTime) {
		this.actEndTime = actEndTime;
	}

	public String getOwnerButton() {
		return ownerButton;
	}

	public void setOwnerButton(String ownerButton) {
		this.ownerButton = ownerButton;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getChkVenue() {
		return chkVenue;
	}

	public void setChkVenue(String chkVenue) {
		this.chkVenue = chkVenue;
	}

	public String getCheckBoxVenue() {
		return checkBoxVenue;
	}

	public void setCheckBoxVenue(String checkBoxVenue) {
		this.checkBoxVenue = checkBoxVenue;
	}

	public String getActVenue() {
		return actVenue;
	}

	public void setActVenue(String actVenue) {
		this.actVenue = actVenue;
	}

	public String getChkEmpList() {
		return chkEmpList;
	}

	public void setChkEmpList(String chkEmpList) {
		this.chkEmpList = chkEmpList;
	}

	public String getCheckBoxEmpList() {
		return checkBoxEmpList;
	}

	public void setCheckBoxEmpList(String checkBoxEmpList) {
		this.checkBoxEmpList = checkBoxEmpList;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
	}

	public String getBrCode() {
		return brCode;
	}

	public void setBrCode(String brCode) {
		this.brCode = brCode;
	}

	public String getDeptListName() {
		return deptListName;
	}

	public void setDeptListName(String deptListName) {
		this.deptListName = deptListName;
	}

	public String getDeptListCode() {
		return deptListCode;
	}

	public void setDeptListCode(String deptListCode) {
		this.deptListCode = deptListCode;
	}

	public ArrayList getParticipantList() {
		return participantList;
	}

	public void setParticipantList(ArrayList participantList) {
		this.participantList = participantList;
	}

	public String getDtOfJoinList() {
		return dtOfJoinList;
	}

	public void setDtOfJoinList(String dtOfJoinList) {
		this.dtOfJoinList = dtOfJoinList;
	}

	public String getOwnerNameInt() {
		return ownerNameInt;
	}

	public void setOwnerNameInt(String ownerNameInt) {
		this.ownerNameInt = ownerNameInt;
	}

	public String getOwnerTokenInt() {
		return ownerTokenInt;
	}

	public void setOwnerTokenInt(String ownerTokenInt) {
		this.ownerTokenInt = ownerTokenInt;
	}

	public String getOwnerIdInt() {
		return ownerIdInt;
	}

	public void setOwnerIdInt(String ownerIdInt) {
		this.ownerIdInt = ownerIdInt;
	}

	public String getOwnerNameExt() {
		return ownerNameExt;
	}

	public void setOwnerNameExt(String ownerNameExt) {
		this.ownerNameExt = ownerNameExt;
	}

	public String getOwnerTokenExt() {
		return ownerTokenExt;
	}

	public void setOwnerTokenExt(String ownerTokenExt) {
		this.ownerTokenExt = ownerTokenExt;
	}

	public String getOwnerIdExt() {
		return ownerIdExt;
	}

	public void setOwnerIdExt(String ownerIdExt) {
		this.ownerIdExt = ownerIdExt;
	}

	public String getOwnerFlag() {
		return ownerFlag;
	}

	public void setOwnerFlag(String ownerFlag) {
		this.ownerFlag = ownerFlag;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerIntOrExt() {
		return ownerIntOrExt;
	}

	public void setOwnerIntOrExt(String ownerIntOrExt) {
		this.ownerIntOrExt = ownerIntOrExt;
	}

	public String getActListDate() {
		return actListDate;
	}

	public void setActListDate(String actListDate) {
		this.actListDate = actListDate;
	}

	public String getActListStartTime() {
		return actListStartTime;
	}

	public void setActListStartTime(String actListStartTime) {
		this.actListStartTime = actListStartTime;
	}

	public String getActListEndTime() {
		return actListEndTime;
	}

	public void setActListEndTime(String actListEndTime) {
		this.actListEndTime = actListEndTime;
	}

	public String getActListDetails() {
		return actListDetails;
	}

	public void setActListDetails(String actListDetails) {
		this.actListDetails = actListDetails;
	}

	public String getActListOwner() {
		return actListOwner;
	}

	public void setActListOwner(String actListOwner) {
		this.actListOwner = actListOwner;
	}

	public ArrayList getActivityList() {
		return activityList;
	}

	public void setActivityList(ArrayList activityList) {
		this.activityList = activityList;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getDesignationExt() {
		return designationExt;
	}

	public void setDesignationExt(String designationExt) {
		this.designationExt = designationExt;
	}

	public String getDesignationInt() {
		return designationInt;
	}

	public void setDesignationInt(String designationInt) {
		this.designationInt = designationInt;
	}

	public String getInductionCode() {
		return inductionCode;
	}

	public void setInductionCode(String inductionCode) {
		this.inductionCode = inductionCode;
	}

	public String getEmpNewToken() {
		return empNewToken;
	}

	public void setEmpNewToken(String empNewToken) {
		this.empNewToken = empNewToken;
	}

	public String getEmpNewName() {
		return empNewName;
	}

	public void setEmpNewName(String empNewName) {
		this.empNewName = empNewName;
	}

	public String getEmpNewId() {
		return empNewId;
	}

	public void setEmpNewId(String empNewId) {
		this.empNewId = empNewId;
	}

	public String getInducListName() {
		return inducListName;
	}

	public void setInducListName(String inducListName) {
		this.inducListName = inducListName;
	}

	public String getInducFrmDt() {
		return inducFrmDt;
	}

	public void setInducFrmDt(String inducFrmDt) {
		this.inducFrmDt = inducFrmDt;
	}

	public String getInducToDt() {
		return inducToDt;
	}

	public void setInducToDt(String inducToDt) {
		this.inducToDt = inducToDt;
	}

	public ArrayList getInductionList() {
		return inductionList;
	}

	public void setInductionList(ArrayList inductionList) {
		this.inductionList = inductionList;
	}

	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	
	

}
