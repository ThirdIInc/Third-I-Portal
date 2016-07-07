/**
 * 
 */
package org.paradyne.bean.extraWorkBenefits;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 * 
 */
public class ExtraWorkApplication extends BeanBase {

	// VARIABLES FOR FIELDS
	
	private String source ="";
	private String compId = "";
	private String status = "";
	private String empId = "";
	private String empName = "";
	private String branchName = "";
	private String dept = "";
	private String desg = "";
	private String appDate = "";
	private String prName = "";
	private String prDate = "";
	private String startTime = "";
	private String endTime = "";
	private String type = "";
	private String cmonth = "";
	private String cyear = "";
	private String level;

	// VARIABLES FOR ADD LIST
	private String hprojName = "";
	private String hsType = "";
	private String hsTime = "";
	private String heTime = "";
	private String hmonth = "";
	private String hyear = "";
	private String hDate = "";
	private String hDay = "";
	private String serialNo = "";

	private String hidMon;
	private String empToken;
	ArrayList compList = null;
	private String checkEdit;
	
	private String iteratorFlag = "false";
	private String comments;
	private String hiddenStatus = "";

	// VARIABLES FOR DRAFT LIST
	private ArrayList draftList;
	private String draftEmpId = "";
	private String draftExtWorkAppNo = "";

	// VARIABLES FOR SUBMIT LIST
	private ArrayList submitList;
	private String submitEmpId = "";
	private String submitExtWorkAppNo = "";

	// VARIABLES FOR RETURN LIST
	private ArrayList returnList;
	private String returnEmpId = "";
	private String returnExtWorkAppNo = "";

	// VARIABLES FOR APPROVED LIST approved

	private ArrayList approvedList;
	private String approvedEmpId = "";
	private String approvedExtWorkAppNo = "";

	// VARIABLES FOR REJECTED LIST rejected

	private ArrayList rejectedList;
	private String rejectedEmpId = "";
	private String rejectedExtWorkAppNo = "";

	// VARIABLES FOR LIST
	private String extEmpToken = "";
	private String extEmpName = "";
	private String extAppDate = "";
	private String listType = "";
	private String extAppStatus = "";

	// VARIABLES FOR KEEP INFORMED TO
	private String employeeName = "";
	private String employeeId = "";
	private String employeeToken = "";
	private ArrayList keepInformedList = null;
	private String keepInformedSerialNo = "";
	private String keepInformedEmpName = "";
	private String keepInformedEmpId = "";
	private String checkRemove = "";
	private String approvalFlag = "true";
	private String isExtTypeApp="true";

	// VARIABLES FOR APPROVER LIST
	private ArrayList approverList;
	private String approverName = "";
	private String srNoIterator = "";

	// VARIABLES FOR APPROVER & PREV APPR COMMENTS
	private String approverComments = "";
	private ArrayList approverCommentList;
	private String appSrNo = "";
	private String prevApproverName = "";
	private String prevApproverDate = "";
	private String prevApproverComment = "";
	private String prevApproverID = "";
	private String prevAppCommentFlag = "false";
	private String prevAppCommentListFlag = "";
	private String prevApproverStatus="";
	
	//VARIABLES FOR PAGING
	private String myPage="";
	private String totalRecords="";
	private String myPageApproved="";
	private String myPageRejected="";
	private String approveLength="false";
	private String rejectedLength="false";
	
	private String brnHDayFlag = "false";
	private String workFlowFlag = "false";
	
	//VARIABLES FOR APPROVAL
	private String isApprovalClick = "false";
	private String isExtApp = "true";
	private String isApprove = "false";
	
	private String apprName = "";
	private String apprDate = "";
	private String apprComments = "";
	ArrayList approveList = null;
	private String approverDetails = "false";
	private String appRejSendBackFlag="false";
	private String checkApproveRejectStatus="";
	private String apprCommentsCheck = "false";
	private boolean flagForward = true; // To remove Add and Clear button from JSP

	/**
	 * @return the checkApproveRejectStatus
	 */
	public String getCheckApproveRejectStatus() {
		return checkApproveRejectStatus;
	}

	/**
	 * @param checkApproveRejectStatus the checkApproveRejectStatus to set
	 */
	public void setCheckApproveRejectStatus(String checkApproveRejectStatus) {
		this.checkApproveRejectStatus = checkApproveRejectStatus;
	}

	/**
	 * @return the appRejSendBackFlag
	 */
	public String getAppRejSendBackFlag() {
		return appRejSendBackFlag;
	}

	/**
	 * @param appRejSendBackFlag the appRejSendBackFlag to set
	 */
	public void setAppRejSendBackFlag(String appRejSendBackFlag) {
		this.appRejSendBackFlag = appRejSendBackFlag;
	}

	/**
	 * @return the approverDetails
	 */
	public String getApproverDetails() {
		return approverDetails;
	}

	/**
	 * @param approverDetails the approverDetails to set
	 */
	public void setApproverDetails(String approverDetails) {
		this.approverDetails = approverDetails;
	}

	/**
	 * @return the isApprove
	 */
	public String getIsApprove() {
		return isApprove;
	}

	/**
	 * @param isApprove the isApprove to set
	 */
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	/**
	 * @return the isApprovalClick
	 */
	public String getIsApprovalClick() {
		return isApprovalClick;
	}

	/**
	 * @param isApprovalClick the isApprovalClick to set
	 */
	public void setIsApprovalClick(String isApprovalClick) {
		this.isApprovalClick = isApprovalClick;
	}

	/**
	 * @return the brnHDayFlag
	 */
	public String getBrnHDayFlag() {
		return brnHDayFlag;
	}

	/**
	 * @param brnHDayFlag the brnHDayFlag to set
	 */
	public void setBrnHDayFlag(String brnHDayFlag) {
		this.brnHDayFlag = brnHDayFlag;
	}

	/**
	 * @return the prevApproverStatus
	 */
	public String getPrevApproverStatus() {
		return prevApproverStatus;
	}

	/**
	 * @param prevApproverStatus the prevApproverStatus to set
	 */
	public void setPrevApproverStatus(String prevApproverStatus) {
		this.prevApproverStatus = prevApproverStatus;
	}

	/**
	 * @return the compId
	 */
	public String getCompId() {
		return compId;
	}

	/**
	 * @param compId
	 *            the compId to set
	 */
	public void setCompId(String compId) {
		this.compId = compId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName
	 *            the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept
	 *            the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * @return the desg
	 */
	public String getDesg() {
		return desg;
	}

	/**
	 * @param desg
	 *            the desg to set
	 */
	public void setDesg(String desg) {
		this.desg = desg;
	}

	/**
	 * @return the appDate
	 */
	public String getAppDate() {
		return appDate;
	}

	/**
	 * @param appDate
	 *            the appDate to set
	 */
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	/**
	 * @return the prName
	 */
	public String getPrName() {
		return prName;
	}

	/**
	 * @param prName
	 *            the prName to set
	 */
	public void setPrName(String prName) {
		this.prName = prName;
	}

	/**
	 * @return the prDate
	 */
	public String getPrDate() {
		return prDate;
	}

	/**
	 * @param prDate
	 *            the prDate to set
	 */
	public void setPrDate(String prDate) {
		this.prDate = prDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the cmonth
	 */
	public String getCmonth() {
		return cmonth;
	}

	/**
	 * @param cmonth
	 *            the cmonth to set
	 */
	public void setCmonth(String cmonth) {
		this.cmonth = cmonth;
	}

	/**
	 * @return the cyear
	 */
	public String getCyear() {
		return cyear;
	}

	/**
	 * @param cyear
	 *            the cyear to set
	 */
	public void setCyear(String cyear) {
		this.cyear = cyear;
	}

	/**
	 * @return the hprojName
	 */
	public String getHprojName() {
		return hprojName;
	}

	/**
	 * @param hprojName
	 *            the hprojName to set
	 */
	public void setHprojName(String hprojName) {
		this.hprojName = hprojName;
	}

	/**
	 * @return the hsTime
	 */
	public String getHsTime() {
		return hsTime;
	}

	/**
	 * @param hsTime
	 *            the hsTime to set
	 */
	public void setHsTime(String hsTime) {
		this.hsTime = hsTime;
	}

	/**
	 * @return the heTime
	 */
	public String getHeTime() {
		return heTime;
	}

	/**
	 * @param heTime
	 *            the heTime to set
	 */
	public void setHeTime(String heTime) {
		this.heTime = heTime;
	}

	/**
	 * @return the hmonth
	 */
	public String getHmonth() {
		return hmonth;
	}

	/**
	 * @param hmonth
	 *            the hmonth to set
	 */
	public void setHmonth(String hmonth) {
		this.hmonth = hmonth;
	}

	/**
	 * @return the hyear
	 */
	public String getHyear() {
		return hyear;
	}

	/**
	 * @param hyear
	 *            the hyear to set
	 */
	public void setHyear(String hyear) {
		this.hyear = hyear;
	}

	/**
	 * @return the hDate
	 */
	public String getHDate() {
		return hDate;
	}

	/**
	 * @param date
	 *            the hDate to set
	 */
	public void setHDate(String date) {
		hDate = date;
	}

	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo
	 *            the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return the hidMon
	 */
	public String getHidMon() {
		return hidMon;
	}

	/**
	 * @param hidMon
	 *            the hidMon to set
	 */
	public void setHidMon(String hidMon) {
		this.hidMon = hidMon;
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
	 * @return the compList
	 */
	public ArrayList getCompList() {
		return compList;
	}

	/**
	 * @param compList
	 *            the compList to set
	 */
	public void setCompList(ArrayList compList) {
		this.compList = compList;
	}

	/**
	 * @return the checkEdit
	 */
	public String getCheckEdit() {
		return checkEdit;
	}

	/**
	 * @param checkEdit
	 *            the checkEdit to set
	 */
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
	}

	/**
	 * @return the apprName
	 */
	public String getApprName() {
		return apprName;
	}

	/**
	 * @param apprName
	 *            the apprName to set
	 */
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	/**
	 * @return the apprDate
	 */
	public String getApprDate() {
		return apprDate;
	}

	/**
	 * @param apprDate
	 *            the apprDate to set
	 */
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	/**
	 * @return the apprComments
	 */
	public String getApprComments() {
		return apprComments;
	}

	/**
	 * @param apprComments
	 *            the apprComments to set
	 */
	public void setApprComments(String apprComments) {
		this.apprComments = apprComments;
	}

	/**
	 * @return the approveList
	 */
	public ArrayList getApproveList() {
		return approveList;
	}

	/**
	 * @param approveList
	 *            the approveList to set
	 */
	public void setApproveList(ArrayList approveList) {
		this.approveList = approveList;
	}

	/**
	 * @return the iteratorFlag
	 */
	public String getIteratorFlag() {
		return iteratorFlag;
	}

	/**
	 * @param iteratorFlag
	 *            the iteratorFlag to set
	 */
	public void setIteratorFlag(String iteratorFlag) {
		this.iteratorFlag = iteratorFlag;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the draftList
	 */
	public ArrayList getDraftList() {
		return draftList;
	}

	/**
	 * @param draftList
	 *            the draftList to set
	 */
	public void setDraftList(ArrayList draftList) {
		this.draftList = draftList;
	}

	/**
	 * @return the draftEmpId
	 */
	public String getDraftEmpId() {
		return draftEmpId;
	}

	/**
	 * @param draftEmpId
	 *            the draftEmpId to set
	 */
	public void setDraftEmpId(String draftEmpId) {
		this.draftEmpId = draftEmpId;
	}

	/**
	 * @return the draftExtWorkAppNo
	 */
	public String getDraftExtWorkAppNo() {
		return draftExtWorkAppNo;
	}

	/**
	 * @param draftExtWorkAppNo
	 *            the draftExtWorkAppNo to set
	 */
	public void setDraftExtWorkAppNo(String draftExtWorkAppNo) {
		this.draftExtWorkAppNo = draftExtWorkAppNo;
	}

	/**
	 * @return the submitList
	 */
	public ArrayList getSubmitList() {
		return submitList;
	}

	/**
	 * @param submitList
	 *            the submitList to set
	 */
	public void setSubmitList(ArrayList submitList) {
		this.submitList = submitList;
	}

	/**
	 * @return the submitEmpId
	 */
	public String getSubmitEmpId() {
		return submitEmpId;
	}

	/**
	 * @param submitEmpId
	 *            the submitEmpId to set
	 */
	public void setSubmitEmpId(String submitEmpId) {
		this.submitEmpId = submitEmpId;
	}

	/**
	 * @return the submitExtWorkAppNo
	 */
	public String getSubmitExtWorkAppNo() {
		return submitExtWorkAppNo;
	}

	/**
	 * @param submitExtWorkAppNo
	 *            the submitExtWorkAppNo to set
	 */
	public void setSubmitExtWorkAppNo(String submitExtWorkAppNo) {
		this.submitExtWorkAppNo = submitExtWorkAppNo;
	}

	/**
	 * @return the returnList
	 */
	public ArrayList getReturnList() {
		return returnList;
	}

	/**
	 * @param returnList
	 *            the returnList to set
	 */
	public void setReturnList(ArrayList returnList) {
		this.returnList = returnList;
	}

	/**
	 * @return the returnEmpId
	 */
	public String getReturnEmpId() {
		return returnEmpId;
	}

	/**
	 * @param returnEmpId
	 *            the returnEmpId to set
	 */
	public void setReturnEmpId(String returnEmpId) {
		this.returnEmpId = returnEmpId;
	}

	/**
	 * @return the returnExtWorkAppNo
	 */
	public String getReturnExtWorkAppNo() {
		return returnExtWorkAppNo;
	}

	/**
	 * @param returnExtWorkAppNo
	 *            the returnExtWorkAppNo to set
	 */
	public void setReturnExtWorkAppNo(String returnExtWorkAppNo) {
		this.returnExtWorkAppNo = returnExtWorkAppNo;
	}

	/**
	 * @return the approvedList
	 */
	public ArrayList getApprovedList() {
		return approvedList;
	}

	/**
	 * @param approvedList
	 *            the approvedList to set
	 */
	public void setApprovedList(ArrayList approvedList) {
		this.approvedList = approvedList;
	}

	/**
	 * @return the approvedEmpId
	 */
	public String getApprovedEmpId() {
		return approvedEmpId;
	}

	/**
	 * @param approvedEmpId
	 *            the approvedEmpId to set
	 */
	public void setApprovedEmpId(String approvedEmpId) {
		this.approvedEmpId = approvedEmpId;
	}

	/**
	 * @return the approvedExtWorkAppNo
	 */
	public String getApprovedExtWorkAppNo() {
		return approvedExtWorkAppNo;
	}

	/**
	 * @param approvedExtWorkAppNo
	 *            the approvedExtWorkAppNo to set
	 */
	public void setApprovedExtWorkAppNo(String approvedExtWorkAppNo) {
		this.approvedExtWorkAppNo = approvedExtWorkAppNo;
	}

	/**
	 * @return the rejectedList
	 */
	public ArrayList getRejectedList() {
		return rejectedList;
	}

	/**
	 * @param rejectedList
	 *            the rejectedList to set
	 */
	public void setRejectedList(ArrayList rejectedList) {
		this.rejectedList = rejectedList;
	}

	/**
	 * @return the rejectedEmpId
	 */
	public String getRejectedEmpId() {
		return rejectedEmpId;
	}

	/**
	 * @param rejectedEmpId
	 *            the rejectedEmpId to set
	 */
	public void setRejectedEmpId(String rejectedEmpId) {
		this.rejectedEmpId = rejectedEmpId;
	}

	/**
	 * @return the rejectedExtWorkAppNo
	 */
	public String getRejectedExtWorkAppNo() {
		return rejectedExtWorkAppNo;
	}

	/**
	 * @param rejectedExtWorkAppNo
	 *            the rejectedExtWorkAppNo to set
	 */
	public void setRejectedExtWorkAppNo(String rejectedExtWorkAppNo) {
		this.rejectedExtWorkAppNo = rejectedExtWorkAppNo;
	}

	/**
	 * @return the extEmpToken
	 */
	public String getExtEmpToken() {
		return extEmpToken;
	}

	/**
	 * @param extEmpToken
	 *            the extEmpToken to set
	 */
	public void setExtEmpToken(String extEmpToken) {
		this.extEmpToken = extEmpToken;
	}

	/**
	 * @return the extEmpName
	 */
	public String getExtEmpName() {
		return extEmpName;
	}

	/**
	 * @param extEmpName
	 *            the extEmpName to set
	 */
	public void setExtEmpName(String extEmpName) {
		this.extEmpName = extEmpName;
	}

	/**
	 * @return the extAppDate
	 */
	public String getExtAppDate() {
		return extAppDate;
	}

	/**
	 * @param extAppDate
	 *            the extAppDate to set
	 */
	public void setExtAppDate(String extAppDate) {
		this.extAppDate = extAppDate;
	}

	/**
	 * @return the listType
	 */
	public String getListType() {
		return listType;
	}

	/**
	 * @param listType
	 *            the listType to set
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}

	/**
	 * @return the extAppStatus
	 */
	public String getExtAppStatus() {
		return extAppStatus;
	}

	/**
	 * @param extAppStatus
	 *            the extAppStatus to set
	 */
	public void setExtAppStatus(String extAppStatus) {
		this.extAppStatus = extAppStatus;
	}

	/**
	 * @return the hiddenStatus
	 */
	public String getHiddenStatus() {
		return hiddenStatus;
	}

	/**
	 * @param hiddenStatus
	 *            the hiddenStatus to set
	 */
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the hsType
	 */
	public String getHsType() {
		return hsType;
	}

	/**
	 * @param hsType
	 *            the hsType to set
	 */
	public void setHsType(String hsType) {
		this.hsType = hsType;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName
	 *            the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return employeeToken;
	}

	/**
	 * @param employeeToken
	 *            the employeeToken to set
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	/**
	 * @return the keepInformedList
	 */
	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}

	/**
	 * @param keepInformedList
	 *            the keepInformedList to set
	 */
	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}

	/**
	 * @return the keepInformedSerialNo
	 */
	public String getKeepInformedSerialNo() {
		return keepInformedSerialNo;
	}

	/**
	 * @param keepInformedSerialNo
	 *            the keepInformedSerialNo to set
	 */
	public void setKeepInformedSerialNo(String keepInformedSerialNo) {
		this.keepInformedSerialNo = keepInformedSerialNo;
	}

	/**
	 * @return the keepInformedEmpName
	 */
	public String getKeepInformedEmpName() {
		return keepInformedEmpName;
	}

	/**
	 * @param keepInformedEmpName
	 *            the keepInformedEmpName to set
	 */
	public void setKeepInformedEmpName(String keepInformedEmpName) {
		this.keepInformedEmpName = keepInformedEmpName;
	}

	/**
	 * @return the keepInformedEmpId
	 */
	public String getKeepInformedEmpId() {
		return keepInformedEmpId;
	}

	/**
	 * @param keepInformedEmpId
	 *            the keepInformedEmpId to set
	 */
	public void setKeepInformedEmpId(String keepInformedEmpId) {
		this.keepInformedEmpId = keepInformedEmpId;
	}

	/**
	 * @return the checkRemove
	 */
	public String getCheckRemove() {
		return checkRemove;
	}

	/**
	 * @param checkRemove
	 *            the checkRemove to set
	 */
	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}

	/**
	 * @return the approverList
	 */
	public ArrayList getApproverList() {
		return approverList;
	}

	/**
	 * @param approverList
	 *            the approverList to set
	 */
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return approverName;
	}

	/**
	 * @param approverName
	 *            the approverName to set
	 */
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	/**
	 * @return the srNoIterator
	 */
	public String getSrNoIterator() {
		return srNoIterator;
	}

	/**
	 * @param srNoIterator
	 *            the srNoIterator to set
	 */
	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}

	/**
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return approverComments;
	}

	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	/**
	 * @return the approverCommentList
	 */
	public ArrayList getApproverCommentList() {
		return approverCommentList;
	}

	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(ArrayList approverCommentList) {
		this.approverCommentList = approverCommentList;
	}

	/**
	 * @return the appSrNo
	 */
	public String getAppSrNo() {
		return appSrNo;
	}

	/**
	 * @param appSrNo the appSrNo to set
	 */
	public void setAppSrNo(String appSrNo) {
		this.appSrNo = appSrNo;
	}

	/**
	 * @return the prevApproverName
	 */
	public String getPrevApproverName() {
		return prevApproverName;
	}

	/**
	 * @param prevApproverName the prevApproverName to set
	 */
	public void setPrevApproverName(String prevApproverName) {
		this.prevApproverName = prevApproverName;
	}

	/**
	 * @return the prevApproverDate
	 */
	public String getPrevApproverDate() {
		return prevApproverDate;
	}

	/**
	 * @param prevApproverDate the prevApproverDate to set
	 */
	public void setPrevApproverDate(String prevApproverDate) {
		this.prevApproverDate = prevApproverDate;
	}

	/**
	 * @return the prevApproverComment
	 */
	public String getPrevApproverComment() {
		return prevApproverComment;
	}

	/**
	 * @param prevApproverComment the prevApproverComment to set
	 */
	public void setPrevApproverComment(String prevApproverComment) {
		this.prevApproverComment = prevApproverComment;
	}

	/**
	 * @return the prevApproverID
	 */
	public String getPrevApproverID() {
		return prevApproverID;
	}

	/**
	 * @param prevApproverID the prevApproverID to set
	 */
	public void setPrevApproverID(String prevApproverID) {
		this.prevApproverID = prevApproverID;
	}

	/**
	 * @return the prevAppCommentFlag
	 */
	public String getPrevAppCommentFlag() {
		return prevAppCommentFlag;
	}

	/**
	 * @param prevAppCommentFlag the prevAppCommentFlag to set
	 */
	public void setPrevAppCommentFlag(String prevAppCommentFlag) {
		this.prevAppCommentFlag = prevAppCommentFlag;
	}

	/**
	 * @return the prevAppCommentListFlag
	 */
	public String getPrevAppCommentListFlag() {
		return prevAppCommentListFlag;
	}

	/**
	 * @param prevAppCommentListFlag the prevAppCommentListFlag to set
	 */
	public void setPrevAppCommentListFlag(String prevAppCommentListFlag) {
		this.prevAppCommentListFlag = prevAppCommentListFlag;
	}

	/**
	 * @return the isExtTypeApp
	 */
	public String getIsExtTypeApp() {
		return isExtTypeApp;
	}

	/**
	 * @param isExtTypeApp the isExtTypeApp to set
	 */
	public void setIsExtTypeApp(String isExtTypeApp) {
		this.isExtTypeApp = isExtTypeApp;
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

	/**
	 * @return the approvalFlag
	 */
	public String getApprovalFlag() {
		return approvalFlag;
	}

	/**
	 * @param approvalFlag the approvalFlag to set
	 */
	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
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
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the myPageApproved
	 */
	public String getMyPageApproved() {
		return myPageApproved;
	}

	/**
	 * @param myPageApproved the myPageApproved to set
	 */
	public void setMyPageApproved(String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}

	/**
	 * @return the myPageRejected
	 */
	public String getMyPageRejected() {
		return myPageRejected;
	}

	/**
	 * @param myPageRejected the myPageRejected to set
	 */
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	/**
	 * @return the approveLength
	 */
	public String getApproveLength() {
		return approveLength;
	}

	/**
	 * @param approveLength the approveLength to set
	 */
	public void setApproveLength(String approveLength) {
		this.approveLength = approveLength;
	}

	/**
	 * @return the rejectedLength
	 */
	public String getRejectedLength() {
		return rejectedLength;
	}

	/**
	 * @param rejectedLength the rejectedLength to set
	 */
	public void setRejectedLength(String rejectedLength) {
		this.rejectedLength = rejectedLength;
	}

	/**
	 * @return the isExtApp
	 */
	public String getIsExtApp() {
		return isExtApp;
	}

	/**
	 * @param isExtApp the isExtApp to set
	 */
	public void setIsExtApp(String isExtApp) {
		this.isExtApp = isExtApp;
	}

	/**
	 * @return the apprCommentsCheck
	 */
	public String getApprCommentsCheck() {
		return apprCommentsCheck;
	}

	/**
	 * @param apprCommentsCheck the apprCommentsCheck to set
	 */
	public void setApprCommentsCheck(String apprCommentsCheck) {
		this.apprCommentsCheck = apprCommentsCheck;
	}

	/**
	 * @return the hDay
	 */
	public String getHDay() {
		return hDay;
	}

	/**
	 * @param day the hDay to set
	 */
	public void setHDay(String day) {
		hDay = day;
	}

	public String getWorkFlowFlag() {
		return workFlowFlag;
	}

	public void setWorkFlowFlag(String workFlowFlag) {
		this.workFlowFlag = workFlowFlag;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	public boolean isFlagForward() {
		return flagForward;
	}

	public void setFlagForward(boolean flagForward) {
		this.flagForward = flagForward;
	}
}
