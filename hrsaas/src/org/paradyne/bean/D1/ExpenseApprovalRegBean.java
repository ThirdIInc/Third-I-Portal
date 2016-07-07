package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ExpenseApprovalRegBean extends BeanBase {
	
	public String completedBy="";
	public String completedOn="";
	
	public String checkData="";
	public String fileheaderName="";
	public String buttonName="";
	public String dataPath="";
	public ArrayList listComment=null;
	private String  ittApproverName="";
	private String  ittComments="";
	public String ittStatus="";
	public String comments="";
	public String headerName="";
	
	private String myPage= "";
	private String myPage1= "";
	private String myPage2= "";
	private String flag="";
	private String status="";
	private String hiddenValue="";
	private String expenseApproverCode="";
	//private ArrayList approverList=null;
	private String ittEmployeeToken= "";
	private String ittExpenseApproverCode= "";
	private String ittEmployee= "";
	private String ittApplicationDate="";
	private String approverName="";
	private String apprSrNo="";
	private String approverCode="";
	
	
	private String employeeCode= "";
	private String employeeToken= "";
	private String employeeName= "";
	
	private ArrayList listDraft=null;
	private ArrayList listReject=null;
	private ArrayList listApprove=null;
	private ArrayList listResubmit=null;
	private ArrayList listInProgress=null;
	private ArrayList listSendBack=null;
	private ArrayList approverList=null;
	
	
	private String totalExpenseDollarAmt="";
	private String attachFile="";
	private String telephoneNo="";
	private String zipCode="";
	private String state="";
	private String city="";
	private String address="";
	private String uploadFileName="";
	private String businessPurpose="";
	private String changeApproverCode="";
	private String changeApproverName="";
	private String changeApproverToken="";
	private String preApprovedPolicy="";
	private String preApprovedPolicyComments="";
	
	
	private String nextApproverToken="";
	private String nextApproverName="";
	private String nextApproverCode="";
	
	private String businessPurposeItr="";
	
	public String getPreApprovedPolicy() {
		return preApprovedPolicy;
	}
	public void setPreApprovedPolicy(String preApprovedPolicy) {
		this.preApprovedPolicy = preApprovedPolicy;
	}
	public String getPreApprovedPolicyComments() {
		return preApprovedPolicyComments;
	}
	public void setPreApprovedPolicyComments(String preApprovedPolicyComments) {
		this.preApprovedPolicyComments = preApprovedPolicyComments;
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
	public String getExpenseApproverCode() {
		return expenseApproverCode;
	}
	public void setExpenseApproverCode(String expenseApproverCode) {
		this.expenseApproverCode = expenseApproverCode;
	}
	public String getIttEmployeeToken() {
		return ittEmployeeToken;
	}
	public void setIttEmployeeToken(String ittEmployeeToken) {
		this.ittEmployeeToken = ittEmployeeToken;
	}
	public String getIttExpenseApproverCode() {
		return ittExpenseApproverCode;
	}
	public void setIttExpenseApproverCode(String ittExpenseApproverCode) {
		this.ittExpenseApproverCode = ittExpenseApproverCode;
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
	public String getTotalExpenseDollarAmt() {
		return totalExpenseDollarAmt;
	}
	public void setTotalExpenseDollarAmt(String totalExpenseDollarAmt) {
		this.totalExpenseDollarAmt = totalExpenseDollarAmt;
	}
	public String getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public ArrayList getApproverList() {
		return approverList;
	}
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
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
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getApprSrNo() {
		return apprSrNo;
	}
	public void setApprSrNo(String apprSrNo) {
		this.apprSrNo = apprSrNo;
	}
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	public String getBusinessPurpose() {
		return businessPurpose;
	}
	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}
	public String getChangeApproverCode() {
		return changeApproverCode;
	}
	public void setChangeApproverCode(String changeApproverCode) {
		this.changeApproverCode = changeApproverCode;
	}
	public String getChangeApproverName() {
		return changeApproverName;
	}
	public void setChangeApproverName(String changeApproverName) {
		this.changeApproverName = changeApproverName;
	}
	public String getChangeApproverToken() {
		return changeApproverToken;
	}
	public void setChangeApproverToken(String changeApproverToken) {
		this.changeApproverToken = changeApproverToken;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNextApproverToken() {
		return nextApproverToken;
	}
	public void setNextApproverToken(String nextApproverToken) {
		this.nextApproverToken = nextApproverToken;
	}
	public String getNextApproverName() {
		return nextApproverName;
	}
	public void setNextApproverName(String nextApproverName) {
		this.nextApproverName = nextApproverName;
	}
	public String getNextApproverCode() {
		return nextApproverCode;
	}
	public void setNextApproverCode(String nextApproverCode) {
		this.nextApproverCode = nextApproverCode;
	}
	public String getFileheaderName() {
		return fileheaderName;
	}
	public void setFileheaderName(String fileheaderName) {
		this.fileheaderName = fileheaderName;
	}
	public String getCheckData() {
		return checkData;
	}
	public void setCheckData(String checkData) {
		this.checkData = checkData;
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
	/**
	 * @return the businessPurposeItr
	 */
	public String getBusinessPurposeItr() {
		return this.businessPurposeItr;
	}
	/**
	 * @param businessPurposeItr the businessPurposeItr to set
	 */
	public void setBusinessPurposeItr(String businessPurposeItr) {
		this.businessPurposeItr = businessPurposeItr;
	}
	
}
