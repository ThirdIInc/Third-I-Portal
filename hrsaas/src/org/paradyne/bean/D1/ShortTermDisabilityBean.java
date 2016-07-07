package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ShortTermDisabilityBean extends BeanBase {

	
	public String completedBy="";
	public String completedOn="";
	public String trackingNo="";
	
	
	public String buttonName="";
	public String apptype="";
	public String appStatus="";
	public ArrayList listComment=null;
	private String  ittApproverName="";
	private String  ittComments="";
	public String ittStatus="";
	public String headerName="";
	
	private String myPage= "";
	private String myPage1= "";
	private String myPage2= "";
	private String flag="";
	private String hiddenValue="";
	private String status="";
	private String ittshortTermCode="";
	//private ArrayList approverList=null;
	private String ittEmployeeToken= "";
	private String ittnonInventoryCode= "";
	private String ittEmployee= "";
	private String ittApplicationDate="";
	//
	private String employeeCode= "";
	private String employeeToken= "";
	private String employeeName= "";
	
	
	private ArrayList listDraft=null;
	private ArrayList listReject=null;
	private ArrayList listApprove=null;
	private ArrayList listResubmit=null;
	private ArrayList listInProgress=null;
	private ArrayList listSendBack=null;
	
	
	private String employeeDeptNo= "";
	private String executive= "";
	private String shortTermCode= "";
	private String stdEffectiveDate="";
	private String stdEligibleDate="";
	private String actionSTO="";
	private String regionSTO="";
	private String workRelated="";
	private String workRelatedHidden="";
	private String didEmployeereturnHidden="";
	
	private String didEmployeereturn="";
	private String dateEmpReturn="";
	private String actionRFD="";
	private String regionRFD="";
	private String noOfWorkingHrs="";
	private String daysOfAbsence="";
	
	
	public String getStdEffectiveDate() {
		return stdEffectiveDate;
	}
	public void setStdEffectiveDate(String stdEffectiveDate) {
		this.stdEffectiveDate = stdEffectiveDate;
	}
	public String getStdEligibleDate() {
		return stdEligibleDate;
	}
	public void setStdEligibleDate(String stdEligibleDate) {
		this.stdEligibleDate = stdEligibleDate;
	}
	public String getActionSTO() {
		return actionSTO;
	}
	public void setActionSTO(String actionSTO) {
		this.actionSTO = actionSTO;
	}
	public String getRegionSTO() {
		return regionSTO;
	}
	public void setRegionSTO(String regionSTO) {
		this.regionSTO = regionSTO;
	}
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
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
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getHiddenValue() {
		return hiddenValue;
	}
	public void setHiddenValue(String hiddenValue) {
		this.hiddenValue = hiddenValue;
	}
	public String getIttEmployeeToken() {
		return ittEmployeeToken;
	}
	public void setIttEmployeeToken(String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}
	public String getIttnonInventoryCode() {
		return ittnonInventoryCode;
	}
	public void setIttnonInventoryCode(String ittnonInventoryCode) {
		this.ittnonInventoryCode = ittnonInventoryCode;
	}
	public String getIttEmployee() {
		return ittEmployee;
	}
	public void setIttEmployee(String ittEmployee) {
		this.ittEmployee = ittEmployee;
	}
	public String getIttApplicationDate() {
		return ittApplicationDate;
	}
	public void setIttApplicationDate(String ittApplicationDate) {
		this.ittApplicationDate = ittApplicationDate;
	}
	public ArrayList getListDraft() {
		return listDraft;
	}
	public void setListDraft(ArrayList listDraft) {
		this.listDraft = listDraft;
	}
	public ArrayList getListReject() {
		return listReject;
	}
	public void setListReject(ArrayList listReject) {
		this.listReject = listReject;
	}
	public ArrayList getListApprove() {
		return listApprove;
	}
	public void setListApprove(ArrayList listApprove) {
		this.listApprove = listApprove;
	}
	public ArrayList getListResubmit() {
		return listResubmit;
	}
	public void setListResubmit(ArrayList listResubmit) {
		this.listResubmit = listResubmit;
	}
	public ArrayList getListInProgress() {
		return listInProgress;
	}
	public void setListInProgress(ArrayList listInProgress) {
		this.listInProgress = listInProgress;
	}
	public ArrayList getListSendBack() {
		return listSendBack;
	}
	public void setListSendBack(ArrayList listSendBack) {
		this.listSendBack = listSendBack;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeDeptNo() {
		return employeeDeptNo;
	}
	public void setEmployeeDeptNo(String employeeDeptNo) {
		this.employeeDeptNo = employeeDeptNo;
	}
	public String getExecutive() {
		return executive;
	}
	public void setExecutive(String executive) {
		this.executive = executive;
	}
	public String getShortTermCode() {
		return shortTermCode;
	}
	public void setShortTermCode(String shortTermCode) {
		this.shortTermCode = shortTermCode;
	}
	public String getWorkRelated() {
		return workRelated;
	}
	public void setWorkRelated(String workRelated) {
		this.workRelated = workRelated;
	}
	public String getDidEmployeereturn() {
		return didEmployeereturn;
	}
	public void setDidEmployeereturn(String didEmployeereturn) {
		this.didEmployeereturn = didEmployeereturn;
	}
	public String getDateEmpReturn() {
		return dateEmpReturn;
	}
	public void setDateEmpReturn(String dateEmpReturn) {
		this.dateEmpReturn = dateEmpReturn;
	}
	public String getActionRFD() {
		return actionRFD;
	}
	public void setActionRFD(String actionRFD) {
		this.actionRFD = actionRFD;
	}
	public String getRegionRFD() {
		return regionRFD;
	}
	public void setRegionRFD(String regionRFD) {
		this.regionRFD = regionRFD;
	}
	public String getNoOfWorkingHrs() {
		return noOfWorkingHrs;
	}
	public void setNoOfWorkingHrs(String noOfWorkingHrs) {
		this.noOfWorkingHrs = noOfWorkingHrs;
	}
	public String getDaysOfAbsence() {
		return daysOfAbsence;
	}
	public void setDaysOfAbsence(String daysOfAbsence) {
		this.daysOfAbsence = daysOfAbsence;
	}
	public String getWorkRelatedHidden() {
		return workRelatedHidden;
	}
	public void setWorkRelatedHidden(String workRelatedHidden) {
		this.workRelatedHidden = workRelatedHidden;
	}
	public String getDidEmployeereturnHidden() {
		return didEmployeereturnHidden;
	}
	public void setDidEmployeereturnHidden(String didEmployeereturnHidden) {
		this.didEmployeereturnHidden = didEmployeereturnHidden;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIttshortTermCode() {
		return ittshortTermCode;
	}
	public void setIttshortTermCode(String ittshortTermCode) {
		this.ittshortTermCode = ittshortTermCode;
	}
	public String getApptype() {
		return apptype;
	}
	public void setApptype(String apptype) {
		this.apptype = apptype;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
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
