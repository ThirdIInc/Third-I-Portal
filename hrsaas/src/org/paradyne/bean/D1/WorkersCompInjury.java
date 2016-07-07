package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class WorkersCompInjury extends BeanBase {
	
	public String trackingNo="";
	public String completedBy="";
	public String completedOn="";
	public String myPage="";
	public String flagResubmit="";
	public String myPage1="";
	public String myPage2="";
	public String f9Flage="";
	public String flagSendBack="";
	private String reportingOfficer="";
	public String comments="";
	private String flag="";
	private String empId="";
	private String empToken="";
	private String empName="";
	private String empHomeState="";
	private String empSSN="";
	private String socialInsNo="";
	private String companyWork="";
	private String  department="";
	private String  	executive="";
	private String  region="";
	private String  managerName="";
	private String  managerPhone="";
	private String  maritalStatus="";
	private String  noOfDependancy="";
	private String  hrsWorkedDate="";
	private String  doi="";
	private String  toi="";
	private String  dor="";
	private String  tor="";
	private String  normalWorkingHrsFrom="";
	private String  dke="";
	private String  injuryReportedName="";
	private String  title="";
	private String  incidentResult="";
	private String  probableLengthDisability="";
	private String  lostWorkDayDate="";
	private String  injuredReturnWork="";
	private String  dateReturned="";
	private String  reasonToDoubt="";
	private String  typeOfTretment="";
	private String  nameAddressPhy="";
	private String  partOfBodyAffected="";
	private String  describeInjury="";
	private String  addressPhone="";
	private String  addressAccident="";
	private String  normalWorkingHrsTo="";
	
	private String  ittEmployeeId="";
	private String  ittApplicationDate="";
	private String  ittEmployee="";
	public String ittEmployeeToken="";
	
	private String  ittWorkersCode="";
	public String workersCode="";
	
	public ArrayList listDraft=null;
	public ArrayList listInProgress=null;
	public ArrayList listApprove=null;
	public ArrayList listApproveCancel=null;
	public ArrayList listReject=null;
	
	public ArrayList listSendBack=null;
	public ArrayList listResubmit=null;
	
	public ArrayList listComment=null;
	private String  ittApproverName="";
	private String  ittComments="";
	public String ittStatus="";
	
	
	
	public ArrayList getListComment() {
		return listComment;
	}
	public void setListComment(ArrayList listComment) {
		this.listComment = listComment;
	}
	public String getIttApproverName() {
		return ittApproverName;
	}
	public void setIttApproverName(String ittApproverName) {
		this.ittApproverName = ittApproverName;
	}
	public String getIttComments() {
		return ittComments;
	}
	public void setIttComments(String ittComments) {
		this.ittComments = ittComments;
	}
	public String getIttStatus() {
		return ittStatus;
	}
	public void setIttStatus(String ittStatus) {
		this.ittStatus = ittStatus;
	}
	public String getIttWorkersCode() {
		return ittWorkersCode;
	}
	public void setIttWorkersCode(String ittWorkersCode) {
		this.ittWorkersCode = ittWorkersCode;
	}
	public String getWorkersCode() {
		return workersCode;
	}
	public void setWorkersCode(String workersCode) {
		this.workersCode = workersCode;
	}
	public String getIttEmployeeId() {
		return ittEmployeeId;
	}
	public void setIttEmployeeId(String ittEmployeeId) {
		this.ittEmployeeId = ittEmployeeId;
	}
	public String getIttApplicationDate() {
		return ittApplicationDate;
	}
	public void setIttApplicationDate(String ittApplicationDate) {
		this.ittApplicationDate = ittApplicationDate;
	}
	public String getIttEmployee() {
		return ittEmployee;
	}
	public void setIttEmployee(String ittEmployee) {
		this.ittEmployee = ittEmployee;
	}
	 
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpHomeState() {
		return empHomeState;
	}
	public void setEmpHomeState(String empHomeState) {
		this.empHomeState = empHomeState;
	}
	public String getSocialInsNo() {
		return socialInsNo;
	}
	public void setSocialInsNo(String socialInsNo) {
		this.socialInsNo = socialInsNo;
	}
	public String getCompanyWork() {
		return companyWork;
	}
	public void setCompanyWork(String companyWork) {
		this.companyWork = companyWork;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getExecutive() {
		return executive;
	}
	public void setExecutive(String executive) {
		this.executive = executive;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNoOfDependancy() {
		return noOfDependancy;
	}
	public void setNoOfDependancy(String noOfDependancy) {
		this.noOfDependancy = noOfDependancy;
	}
	public String getHrsWorkedDate() {
		return hrsWorkedDate;
	}
	public void setHrsWorkedDate(String hrsWorkedDate) {
		this.hrsWorkedDate = hrsWorkedDate;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getToi() {
		return toi;
	}
	public void setToi(String toi) {
		this.toi = toi;
	}
	public String getNormalWorkingHrsFrom() {
		return normalWorkingHrsFrom;
	}
	public void setNormalWorkingHrsFrom(String normalWorkingHrsFrom) {
		this.normalWorkingHrsFrom = normalWorkingHrsFrom;
	}
	public String getDke() {
		return dke;
	}
	public void setDke(String dke) {
		this.dke = dke;
	}
	public String getInjuryReportedName() {
		return injuryReportedName;
	}
	public void setInjuryReportedName(String injuryReportedName) {
		this.injuryReportedName = injuryReportedName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIncidentResult() {
		return incidentResult;
	}
	public void setIncidentResult(String incidentResult) {
		this.incidentResult = incidentResult;
	}
	public String getProbableLengthDisability() {
		return probableLengthDisability;
	}
	public void setProbableLengthDisability(String probableLengthDisability) {
		this.probableLengthDisability = probableLengthDisability;
	}
	public String getLostWorkDayDate() {
		return lostWorkDayDate;
	}
	public void setLostWorkDayDate(String lostWorkDayDate) {
		this.lostWorkDayDate = lostWorkDayDate;
	}
	public String getInjuredReturnWork() {
		return injuredReturnWork;
	}
	public void setInjuredReturnWork(String injuredReturnWork) {
		this.injuredReturnWork = injuredReturnWork;
	}
	public String getDateReturned() {
		return dateReturned;
	}
	public void setDateReturned(String dateReturned) {
		this.dateReturned = dateReturned;
	}
	public String getReasonToDoubt() {
		return reasonToDoubt;
	}
	public void setReasonToDoubt(String reasonToDoubt) {
		this.reasonToDoubt = reasonToDoubt;
	}
	public String getTypeOfTretment() {
		return typeOfTretment;
	}
	public void setTypeOfTretment(String typeOfTretment) {
		this.typeOfTretment = typeOfTretment;
	}
	public String getNameAddressPhy() {
		return nameAddressPhy;
	}
	public void setNameAddressPhy(String nameAddressPhy) {
		this.nameAddressPhy = nameAddressPhy;
	}
	public String getPartOfBodyAffected() {
		return partOfBodyAffected;
	}
	public void setPartOfBodyAffected(String partOfBodyAffected) {
		this.partOfBodyAffected = partOfBodyAffected;
	}
	public String getDescribeInjury() {
		return describeInjury;
	}
	public void setDescribeInjury(String describeInjury) {
		this.describeInjury = describeInjury;
	}
	public String getAddressPhone() {
		return addressPhone;
	}
	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}
	public String getAddressAccident() {
		return addressAccident;
	}
	public void setAddressAccident(String addressAccident) {
		this.addressAccident = addressAccident;
	}
	public String getNormalWorkingHrsTo() {
		return normalWorkingHrsTo;
	}
	public void setNormalWorkingHrsTo(String normalWorkingHrsTo) {
		this.normalWorkingHrsTo = normalWorkingHrsTo;
	}
	public String getEmpSSN() {
		return empSSN;
	}
	public void setEmpSSN(String empSSN) {
		this.empSSN = empSSN;
	}
	public String getDor() {
		return dor;
	}
	public void setDor(String dor) {
		this.dor = dor;
	}
	public String getTor() {
		return tor;
	}
	public void setTor(String tor) {
		this.tor = tor;
	}
	public String getIttEmployeeToken() {
		return ittEmployeeToken;
	}
	public void setIttEmployeeToken(String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}
	public ArrayList getListDraft() {
		return listDraft;
	}
	public void setListDraft(ArrayList listDraft) {
		this.listDraft = listDraft;
	}
	public ArrayList getListInProgress() {
		return listInProgress;
	}
	public void setListInProgress(ArrayList listInProgress) {
		this.listInProgress = listInProgress;
	}
	public ArrayList getListApprove() {
		return listApprove;
	}
	public void setListApprove(ArrayList listApprove) {
		this.listApprove = listApprove;
	}
	public ArrayList getListApproveCancel() {
		return listApproveCancel;
	}
	public void setListApproveCancel(ArrayList listApproveCancel) {
		this.listApproveCancel = listApproveCancel;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public ArrayList getListReject() {
		return listReject;
	}
	public void setListReject(ArrayList listReject) {
		this.listReject = listReject;
	}
	public ArrayList getListSendBack() {
		return listSendBack;
	}
	public void setListSendBack(ArrayList listSendBack) {
		this.listSendBack = listSendBack;
	}
	public String getReportingOfficer() {
		return reportingOfficer;
	}
	public void setReportingOfficer(String reportingOfficer) {
		this.reportingOfficer = reportingOfficer;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getFlagSendBack() {
		return flagSendBack;
	}
	public void setFlagSendBack(String flagSendBack) {
		this.flagSendBack = flagSendBack;
	}
	public String getF9Flage() {
		return f9Flage;
	}
	public void setF9Flage(String flage) {
		f9Flage = flage;
	}
	public String getMyPage1() {
		return myPage1;
	}
	public void setMyPage1(String myPage1) {
		this.myPage1 = myPage1;
	}
	public String getMyPage2() {
		return myPage2;
	}
	public void setMyPage2(String myPage2) {
		this.myPage2 = myPage2;
	}
	public ArrayList getListResubmit() {
		return listResubmit;
	}
	public void setListResubmit(ArrayList listResubmit) {
		this.listResubmit = listResubmit;
	}
	public String getFlagResubmit() {
		return flagResubmit;
	}
	public void setFlagResubmit(String flagResubmit) {
		this.flagResubmit = flagResubmit;
	}
	public String getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}
	public String getCompletedOn() {
		return completedOn;
	}
	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
}
