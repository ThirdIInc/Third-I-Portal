/**
 * 
 */
package org.paradyne.bean.helpdesk;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class HelpDeskProcess extends BeanBase {
	// Added by Prashant
	
	private String reqEmpContactNo = "";
	private String reqEmpEmailId = "";
	private String reqEmpExtensionNo = "";
	//For dropdown box
	TreeMap statusMap;
	
	//filter variables
	private String filterById = "";
	private String filterByReqToken = "";
	private String filterByEmp = "";
	private String filterByEmpId = "";
	private String filterByEmpToken = "";
	private String filterByStatus= "-1";
	private String filterByCat = "";
	private String filterByCatId = "";
	private String filterByDeptId = "";
	private String filterByDept = "";
	private String filterByDate = "";
	private String checkSearch = "";
	
	private String myPage="";
	private String totalRecords="";
	private String myPageClosed="";
	private String closedLength="false";
	
	private String pendingLength="false";
	private String listType="pending";
	
	private String empNameList="";
	private String requestIdList="";
	private String requestDateList="";
	private String empCodeList="";
	private String empIdList="";
	private String applDeptNameList="";
	private String requestTokenList="";
	private String requestToken="";
	private String subjectList="";
	private String applDeptCodeList="";
	private String statusList="";
	private String escalatedFlagList="";
	private String escalatedFlag="";
	private String escalatedFromNameList="";
	private String escalatedFromName="";
	private String escalatedTimeList="";
	private String pendingSinceList="";
	private String escalatedTime="";
	private String categoryIdList="";
	private String categoryList="";
	
	private String empName="";
	private String empCode="";
	private String empId="";
	private String empNameFor="";
	private String empCodeFor="";
	private String empIdFor="";
	private String branchName="";
	private String deptName="";
	private String desgName="";
	private String applDeptName="";
	private String reqType="";
	private String reqTypeCode="";
	private String reqSubTypeCode="";
	private String applDeptCode="";
	private String status="";
	private String requestId="";
	private String requestDate="";
	private String requestDateTime="";
	private String appliedFor="";
	private String requestType="";
	private String requestTypeCode="";
	private String subject="";
	private String assetFlag="false";
	private String assetType="";
	private String assetTypeCode="";
	private String assetSubType="";
	private String assetSubTypeCode="";
	private String reqDesc="";
	private String reqQuantity="";
	private String fileName="";
	private String fileFlag="false";
	private String clientName="";
	private String slaStatus="";
	private String slaDuration="";
	private String slaActualDuration="";
	
	private String logFromEmp="";
	private String logToEmp="";
	private String logStatus="";
	private String logDate="";
	private String logComments="";
	private String logLength="false";
	
	private String assignToId="";
	private String assignToName="";
	private String assignToCode="";
	private String processStatus="";
	private String processComments="";
	
	/*
	 * variables for Asset assignment
	 */
	private String empWarehouseCode;
	private String assetCode="";
	private String asstHdType="";
	private String subTypeCodeIterator;
	private String assetSubTypeIterator;
	private String assetRequiredIterator;
	private String returnFlagIterator;
	private String empWarehouse;
	private String hiddenSubTypeCode="";
	private String hiddenCategoryCode="";
	private String rowId;
	private String tableLength="";
	private String src="";
	
	ArrayList pendingList=null;
	ArrayList closedList=null;
	ArrayList fileList=null;
	ArrayList slaList=null;
	ArrayList logList=null;
	ArrayList assignList=null;
	//Added By Anantha lakshmi
	private String deptCode="";
	
	public String getEmpNameList() {
		return empNameList;
	}
	public void setEmpNameList(String empNameList) {
		this.empNameList = empNameList;
	}
	public String getEmpIdList() {
		return empIdList;
	}
	public void setEmpIdList(String empIdList) {
		this.empIdList = empIdList;
	}
	public String getApplDeptNameList() {
		return applDeptNameList;
	}
	public void setApplDeptNameList(String applDeptNameList) {
		this.applDeptNameList = applDeptNameList;
	}
	public String getApplDeptCodeList() {
		return applDeptCodeList;
	}
	public void setApplDeptCodeList(String applDeptCodeList) {
		this.applDeptCodeList = applDeptCodeList;
	}
	public String getStatusList() {
		return statusList;
	}
	public void setStatusList(String statusList) {
		this.statusList = statusList;
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
	public String getMyPageClosed() {
		return myPageClosed;
	}
	public void setMyPageClosed(String myPageClosed) {
		this.myPageClosed = myPageClosed;
	}
	public String getClosedLength() {
		return closedLength;
	}
	public void setClosedLength(String closedLength) {
		this.closedLength = closedLength;
	}
	public String getPendingLength() {
		return pendingLength;
	}
	public void setPendingLength(String pendingLength) {
		this.pendingLength = pendingLength;
	}
	public ArrayList getPendingList() {
		return pendingList;
	}
	public void setPendingList(ArrayList pendingList) {
		this.pendingList = pendingList;
	}
	public String getEmpCodeList() {
		return empCodeList;
	}
	public void setEmpCodeList(String empCodeList) {
		this.empCodeList = empCodeList;
	}
	public String getRequestIdList() {
		return requestIdList;
	}
	public void setRequestIdList(String requestIdList) {
		this.requestIdList = requestIdList;
	}
	public String getRequestDateList() {
		return requestDateList;
	}
	public void setRequestDateList(String requestDateList) {
		this.requestDateList = requestDateList;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getApplDeptName() {
		return applDeptName;
	}
	public void setApplDeptName(String applDeptName) {
		this.applDeptName = applDeptName;
	}
	public String getApplDeptCode() {
		return applDeptCode;
	}
	public void setApplDeptCode(String applDeptCode) {
		this.applDeptCode = applDeptCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getEmpNameFor() {
		return empNameFor;
	}
	public void setEmpNameFor(String empNameFor) {
		this.empNameFor = empNameFor;
	}
	public String getEmpCodeFor() {
		return empCodeFor;
	}
	public void setEmpCodeFor(String empCodeFor) {
		this.empCodeFor = empCodeFor;
	}
	public String getEmpIdFor() {
		return empIdFor;
	}
	public void setEmpIdFor(String empIdFor) {
		this.empIdFor = empIdFor;
	}
	public String getAppliedFor() {
		return appliedFor;
	}
	public void setAppliedFor(String appliedFor) {
		this.appliedFor = appliedFor;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getReqTypeCode() {
		return reqTypeCode;
	}
	public void setReqTypeCode(String reqTypeCode) {
		this.reqTypeCode = reqTypeCode;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestTypeCode() {
		return requestTypeCode;
	}
	public void setRequestTypeCode(String requestTypeCode) {
		this.requestTypeCode = requestTypeCode;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetTypeCode() {
		return assetTypeCode;
	}
	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}
	public String getAssetSubType() {
		return assetSubType;
	}
	public void setAssetSubType(String assetSubType) {
		this.assetSubType = assetSubType;
	}
	public String getAssetSubTypeCode() {
		return assetSubTypeCode;
	}
	public void setAssetSubTypeCode(String assetSubTypeCode) {
		this.assetSubTypeCode = assetSubTypeCode;
	}
	public String getReqDesc() {
		return reqDesc;
	}
	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	public String getReqQuantity() {
		return reqQuantity;
	}
	public void setReqQuantity(String reqQuantity) {
		this.reqQuantity = reqQuantity;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ArrayList getFileList() {
		return fileList;
	}
	public void setFileList(ArrayList fileList) {
		this.fileList = fileList;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getSlaStatus() {
		return slaStatus;
	}
	public void setSlaStatus(String slaStatus) {
		this.slaStatus = slaStatus;
	}
	public String getSlaDuration() {
		return slaDuration;
	}
	public void setSlaDuration(String slaDuration) {
		this.slaDuration = slaDuration;
	}
	public String getSlaActualDuration() {
		return slaActualDuration;
	}
	public void setSlaActualDuration(String slaActualDuration) {
		this.slaActualDuration = slaActualDuration;
	}
	public ArrayList getSlaList() {
		return slaList;
	}
	public void setSlaList(ArrayList slaList) {
		this.slaList = slaList;
	}
	public String getReqSubTypeCode() {
		return reqSubTypeCode;
	}
	public void setReqSubTypeCode(String reqSubTypeCode) {
		this.reqSubTypeCode = reqSubTypeCode;
	}
	public String getAssetFlag() {
		return assetFlag;
	}
	public void setAssetFlag(String assetFlag) {
		this.assetFlag = assetFlag;
	}
	public String getLogFromEmp() {
		return logFromEmp;
	}
	public void setLogFromEmp(String logFromEmp) {
		this.logFromEmp = logFromEmp;
	}
	public String getLogToEmp() {
		return logToEmp;
	}
	public void setLogToEmp(String logToEmp) {
		this.logToEmp = logToEmp;
	}
	public String getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getLogComments() {
		return logComments;
	}
	public void setLogComments(String logComments) {
		this.logComments = logComments;
	}
	public String getLogLength() {
		return logLength;
	}
	public void setLogLength(String logLength) {
		this.logLength = logLength;
	}
	public ArrayList getLogList() {
		return logList;
	}
	public void setLogList(ArrayList logList) {
		this.logList = logList;
	}
	public String getAssignToId() {
		return assignToId;
	}
	public void setAssignToId(String assignToId) {
		this.assignToId = assignToId;
	}
	public String getAssignToName() {
		return assignToName;
	}
	public void setAssignToName(String assignToName) {
		this.assignToName = assignToName;
	}
	public String getAssignToCode() {
		return assignToCode;
	}
	public void setAssignToCode(String assignToCode) {
		this.assignToCode = assignToCode;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getProcessComments() {
		return processComments;
	}
	public void setProcessComments(String processComments) {
		this.processComments = processComments;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getAsstHdType() {
		return asstHdType;
	}
	public void setAsstHdType(String asstHdType) {
		this.asstHdType = asstHdType;
	}
	public String getSubTypeCodeIterator() {
		return subTypeCodeIterator;
	}
	public void setSubTypeCodeIterator(String subTypeCodeIterator) {
		this.subTypeCodeIterator = subTypeCodeIterator;
	}
	public String getAssetSubTypeIterator() {
		return assetSubTypeIterator;
	}
	public void setAssetSubTypeIterator(String assetSubTypeIterator) {
		this.assetSubTypeIterator = assetSubTypeIterator;
	}
	public String getAssetRequiredIterator() {
		return assetRequiredIterator;
	}
	public void setAssetRequiredIterator(String assetRequiredIterator) {
		this.assetRequiredIterator = assetRequiredIterator;
	}
	public String getReturnFlagIterator() {
		return returnFlagIterator;
	}
	public void setReturnFlagIterator(String returnFlagIterator) {
		this.returnFlagIterator = returnFlagIterator;
	}
	public String getEmpWarehouse() {
		return empWarehouse;
	}
	public void setEmpWarehouse(String empWarehouse) {
		this.empWarehouse = empWarehouse;
	}
	public ArrayList getAssignList() {
		return assignList;
	}
	public void setAssignList(ArrayList assignList) {
		this.assignList = assignList;
	}
	public String getHiddenSubTypeCode() {
		return hiddenSubTypeCode;
	}
	public void setHiddenSubTypeCode(String hiddenSubTypeCode) {
		this.hiddenSubTypeCode = hiddenSubTypeCode;
	}
	public String getHiddenCategoryCode() {
		return hiddenCategoryCode;
	}
	public void setHiddenCategoryCode(String hiddenCategoryCode) {
		this.hiddenCategoryCode = hiddenCategoryCode;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getEmpWarehouseCode() {
		return empWarehouseCode;
	}
	public void setEmpWarehouseCode(String empWarehouseCode) {
		this.empWarehouseCode = empWarehouseCode;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public ArrayList getClosedList() {
		return closedList;
	}
	public void setClosedList(ArrayList closedList) {
		this.closedList = closedList;
	}
	public String getRequestTokenList() {
		return requestTokenList;
	}
	public void setRequestTokenList(String requestTokenList) {
		this.requestTokenList = requestTokenList;
	}
	public String getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(String subjectList) {
		this.subjectList = subjectList;
	}
	public String getRequestToken() {
		return requestToken;
	}
	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}
	public String getEscalatedFlagList() {
		return escalatedFlagList;
	}
	public void setEscalatedFlagList(String escalatedFlagList) {
		this.escalatedFlagList = escalatedFlagList;
	}
	public String getEscalatedFlag() {
		return escalatedFlag;
	}
	public void setEscalatedFlag(String escalatedFlag) {
		this.escalatedFlag = escalatedFlag;
	}
	public String getEscalatedFromNameList() {
		return escalatedFromNameList;
	}
	public void setEscalatedFromNameList(String escalatedFromNameList) {
		this.escalatedFromNameList = escalatedFromNameList;
	}
	public String getEscalatedFromName() {
		return escalatedFromName;
	}
	public void setEscalatedFromName(String escalatedFromName) {
		this.escalatedFromName = escalatedFromName;
	}
	public String getEscalatedTimeList() {
		return escalatedTimeList;
	}
	public void setEscalatedTimeList(String escalatedTimeList) {
		this.escalatedTimeList = escalatedTimeList;
	}
	public String getEscalatedTime() {
		return escalatedTime;
	}
	public void setEscalatedTime(String escalatedTime) {
		this.escalatedTime = escalatedTime;
	}
	public String getFileFlag() {
		return fileFlag;
	}
	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}
	public String getReqEmpContactNo() {
		return reqEmpContactNo;
	}
	public void setReqEmpContactNo(String reqEmpContactNo) {
		this.reqEmpContactNo = reqEmpContactNo;
	}
	public String getReqEmpEmailId() {
		return reqEmpEmailId;
	}
	public void setReqEmpEmailId(String reqEmpEmailId) {
		this.reqEmpEmailId = reqEmpEmailId;
	}
	public String getReqEmpExtensionNo() {
		return reqEmpExtensionNo;
	}
	public void setReqEmpExtensionNo(String reqEmpExtensionNo) {
		this.reqEmpExtensionNo = reqEmpExtensionNo;
	}
	public String getPendingSinceList() {
		return pendingSinceList;
	}
	public void setPendingSinceList(String pendingSinceList) {
		this.pendingSinceList = pendingSinceList;
	}
	public String getFilterById() {
		return filterById;
	}
	public void setFilterById(String filterById) {
		this.filterById = filterById;
	}
	public String getFilterByEmp() {
		return filterByEmp;
	}
	public void setFilterByEmp(String filterByEmp) {
		this.filterByEmp = filterByEmp;
	}
	public String getFilterByEmpId() {
		return filterByEmpId;
	}
	public void setFilterByEmpId(String filterByEmpId) {
		this.filterByEmpId = filterByEmpId;
	}
	public String getFilterByStatus() {
		return filterByStatus;
	}
	public void setFilterByStatus(String filterByStatus) {
		this.filterByStatus = filterByStatus;
	}
	public String getFilterByCat() {
		return filterByCat;
	}
	public void setFilterByCat(String filterByCat) {
		this.filterByCat = filterByCat;
	}
	public String getFilterByCatId() {
		return filterByCatId;
	}
	public void setFilterByCatId(String filterByCatId) {
		this.filterByCatId = filterByCatId;
	}
	public String getFilterByDeptId() {
		return filterByDeptId;
	}
	public void setFilterByDeptId(String filterByDeptId) {
		this.filterByDeptId = filterByDeptId;
	}
	public String getFilterByDept() {
		return filterByDept;
	}
	public void setFilterByDept(String filterByDept) {
		this.filterByDept = filterByDept;
	}
	public String getFilterByDate() {
		return filterByDate;
	}
	public void setFilterByDate(String filterByDate) {
		this.filterByDate = filterByDate;
	}
	public String getFilterByEmpToken() {
		return filterByEmpToken;
	}
	public void setFilterByEmpToken(String filterByEmpToken) {
		this.filterByEmpToken = filterByEmpToken;
	}
	public String getFilterByReqToken() {
		return filterByReqToken;
	}
	public void setFilterByReqToken(String filterByReqToken) {
		this.filterByReqToken = filterByReqToken;
	}
	public String getCategoryIdList() {
		return categoryIdList;
	}
	public void setCategoryIdList(String categoryIdList) {
		this.categoryIdList = categoryIdList;
	}
	public String getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(String categoryList) {
		this.categoryList = categoryList;
	}
	public String getCheckSearch() {
		return checkSearch;
	}
	public void setCheckSearch(String checkSearch) {
		this.checkSearch = checkSearch;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public TreeMap getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(TreeMap statusMap) {
		this.statusMap = statusMap;
	}
}
