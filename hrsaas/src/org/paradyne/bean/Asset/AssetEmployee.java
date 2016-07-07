package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AssetEmployee extends BeanBase {
	
	
	private String referenceId="";
	private String messageStr ="";
	private String myPageRejected="";
	private String srNoIterator ="";
	private ArrayList approverList =null ;
	private String myPageAssigned  ="";
	private String approverCommentsFlag="false";
	private String assetId="";
	private String source="";
	private int applicationLevel=0;
	private String appcomment="";
	private String hiddenappCode="";
	
	private String employeeToken="";
	private String employeeName="";
	private String employeeId="";
	private String keepInformedEmpId="";
	private String keepInformedEmpName="";
	private String serialNo="";
	private ArrayList keepInformedList;
	private String checkRemove="";
	private String keepInfoFlag="true";
	
	private String empName;
	private String empCode;
	private String approverCode;
	private String srNo;
	private String invCode;
	private String asstHdType;
	private String invCode1;
	private String asstHdType1;
	private String code;
	private ArrayList list;
	private String  asstCode;
	private String assignDate;
	private String returnDate;
	private String comments;
	private String asstCode1;
	private String assignDate1;
	private String returnDate1;
	private String select;
	private String assetCode;
	private String checkFlag;
	private String chkFlag;
	private String chkCode="false";
	private String empToken="";
	private String tableLength="";
	private String assetSubType;
	private String subTypeCode;
	private String assetAvailable;
	private String assetRequired;
	private String assetInvType ;
	private String paraId;
	private String appl_No;
	
	private String assetSubTypeIterator;
	private String subTypeCodeIterator;
	private String assetRequiredIterator;
	private String status;
	private String hiddenStatus;
	private String isSentBack="false";
	private String isApprove="false";
	private String isAssign="false";
	private String partialAssign="false";
	private String partialAssignIt="false";
	private String assetInvTypeIterator="false";
	private String commentFlag="false";
	private String approverName ;
	private String approvedDate ;
	private String approveStatus ;
	private String approverComment ;
	private String assetUnit;
	private String assetUnitIterator;
	private String assignCommentsFlag="false";
	private ArrayList apprList;
	
	/*
	 * 
	 * used for asset assignment
	 * 
	 */
	private String hiddenCategoryCode;
	private String hiddenSubTypeCode;
	private ArrayList assignList;
	private ArrayList otherWarehouseList;
	private String rowId;
	private String quantityAssigned;
	private String returnFlagIterator;
	private String applCode;
	private String empWarehouse;
	private String empWarehouseCode;
	private String assignComments;
	private String assetSubTypeIteratorAssigned;
	private String subTypeCodeIteratorAssigned;
	private String assetCodeAssigned;
	private String asstHdTypeAssigned;
	private String assetAssignedIterator;
	private String assetUnassignedIterator;
	private String assetUnitIteratorAssigned;
	private ArrayList assignedAssetList;
	private ArrayList unassignedAssetList;
	//private String quantityAvailable;
	/*
	 * 
	 *used for Asset request form 
	 *
	 */
	
	private String reqCategory;      
	private String reqCategoryCode ;
	private String reqSubType;
	private String ReqSubTypeCode;
	private String warehouseCode;
	private String warehouseName;
	private String reqQuantityAvailable;
	private String reqQuantityRequired;
	   
	private String inventoryCodeReq;
	private String masterCodeReq;
	private String availableReq;
	private String requiredReq;
	private String noData="false";
	private ArrayList warehouseList;
	private String assetAssignDate="";
	
	/*
	 * used for AssetEmployeeList
	 */
	
	private String listType;//to show pending, approved, rejected 
	private String listLengthApproved="false";
	private String listLengthRejected="false";
	private String listLengthAssigned="false";
	
	private ArrayList draftList;
	private ArrayList submittedList;
	private ArrayList returnedList;
	private ArrayList approvedList;
	private ArrayList rejectedList;
	private ArrayList assignedList;
	
	private String branch;
	private String dept;
	private String desig;
	
	private String hiddencode;
	private String hdeleteCode;
	private String myPage;
	private String totalRecords;
	
	public String getReqCategory() {
		return reqCategory;
	}

	public void setReqCategory(String reqCategory) {
		this.reqCategory = reqCategory;
	}

	public String getReqCategoryCode() {
		return reqCategoryCode;
	}

	public void setReqCategoryCode(String reqCategoryCode) {
		this.reqCategoryCode = reqCategoryCode;
	}

	public String getReqSubType() {
		return reqSubType;
	}

	public void setReqSubType(String reqSubType) {
		this.reqSubType = reqSubType;
	}

	public String getReqSubTypeCode() {
		return ReqSubTypeCode;
	}

	public void setReqSubTypeCode(String reqSubTypeCode) {
		ReqSubTypeCode = reqSubTypeCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getReqQuantityAvailable() {
		return reqQuantityAvailable;
	}

	public void setReqQuantityAvailable(String reqQuantityAvailable) {
		this.reqQuantityAvailable = reqQuantityAvailable;
	}

	public String getReqQuantityRequired() {
		return reqQuantityRequired;
	}

	public void setReqQuantityRequired(String reqQuantityRequired) {
		this.reqQuantityRequired = reqQuantityRequired;
	}

	public String getApplCode() {
		return applCode;
	}

	public void setApplCode(String applCode) {
		this.applCode = applCode;
	}

	

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getHiddenCategoryCode() {
		return hiddenCategoryCode;
	}

	public void setHiddenCategoryCode(String hiddenCategoryCode) {
		this.hiddenCategoryCode = hiddenCategoryCode;
	}

	public String getHiddenSubTypeCode() {
		return hiddenSubTypeCode;
	}

	public void setHiddenSubTypeCode(String hiddenSubTypeCode) {
		this.hiddenSubTypeCode = hiddenSubTypeCode;
	}

	public ArrayList getAssignList() {
		return assignList;
	}

	public void setAssignList(ArrayList assignList) {
		this.assignList = assignList;
	}

	public String getCommentFlag() {
		return commentFlag;
	}

	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssetSubTypeIterator() {
		return assetSubTypeIterator;
	}

	public void setAssetSubTypeIterator(String assetSubTypeIterator) {
		this.assetSubTypeIterator = assetSubTypeIterator;
	}

	public String getSubTypeCodeIterator() {
		return subTypeCodeIterator;
	}

	public void setSubTypeCodeIterator(String subTypeCodeIterator) {
		this.subTypeCodeIterator = subTypeCodeIterator;
	}

	public String getAssetRequiredIterator() {
		return assetRequiredIterator;
	}

	public void setAssetRequiredIterator(String assetRequiredIterator) {
		this.assetRequiredIterator = assetRequiredIterator;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getChkCode() {
		return chkCode;
	}

	public void setChkCode(String chkCode) {
		this.chkCode = chkCode;
	}

	public String getChkFlag() {
		return chkFlag;
	}

	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getAsstCode() {
		return asstCode;
	}

	public void setAsstCode(String asstCode) {
		this.asstCode = asstCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
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

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getInvCode() {
		return invCode;
	}

	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	public String getAsstHdType() {
		return asstHdType;
	}

	public void setAsstHdType(String asstHdType) {
		this.asstHdType = asstHdType;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getInvCode1() {
		return invCode1;
	}

	public void setInvCode1(String invCode1) {
		this.invCode1 = invCode1;
	}

	public String getAsstHdType1() {
		return asstHdType1;
	}

	public void setAsstHdType1(String asstHdType1) {
		this.asstHdType1 = asstHdType1;
	}

	public String getAsstCode1() {
		return asstCode1;
	}

	public void setAsstCode1(String asstCode1) {
		this.asstCode1 = asstCode1;
	}

	public String getAssignDate1() {
		return assignDate1;
	}

	public void setAssignDate1(String assignDate1) {
		this.assignDate1 = assignDate1;
	}

	public String getReturnDate1() {
		return returnDate1;
	}

	public void setReturnDate1(String returnDate1) {
		this.returnDate1 = returnDate1;
	}

	public String getTableLength() {
		return tableLength;
	}

	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}

	public String getAssetSubType() {
		return assetSubType;
	}

	public void setAssetSubType(String assetSubType) {
		this.assetSubType = assetSubType;
	}

	public String getAssetAvailable() {
		return assetAvailable;
	}

	public void setAssetAvailable(String assetAvailable) {
		this.assetAvailable = assetAvailable;
	}

	public String getAssetRequired() {
		return assetRequired;
	}

	public void setAssetRequired(String assetRequired) {
		this.assetRequired = assetRequired;
	}

	public String getSubTypeCode() {
		return subTypeCode;
	}

	public void setSubTypeCode(String subTypeCode) {
		this.subTypeCode = subTypeCode;
	}

	

	public String getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproverComment() {
		return approverComment;
	}

	public void setApproverComment(String approverComment) {
		this.approverComment = approverComment;
	}

	public ArrayList getApprList() {
		return apprList;
	}

	public void setApprList(ArrayList apprList) {
		this.apprList = apprList;
	}

	public String getAssetUnit() {
		return assetUnit;
	}

	public void setAssetUnit(String assetUnit) {
		this.assetUnit = assetUnit;
	}

	public String getQuantityAssigned() {
		return quantityAssigned;
	}

	public void setQuantityAssigned(String quantityAssigned) {
		this.quantityAssigned = quantityAssigned;
	}

	
	public String getReturnFlagIterator() {
		return returnFlagIterator;
	}

	public void setReturnFlagIterator(String returnFlagIterator) {
		this.returnFlagIterator = returnFlagIterator;
	}

	public ArrayList getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(ArrayList warehouseList) {
		this.warehouseList = warehouseList;
	}

	public String getInventoryCodeReq() {
		return inventoryCodeReq;
	}

	public void setInventoryCodeReq(String inventoryCodeReq) {
		this.inventoryCodeReq = inventoryCodeReq;
	}

	public String getMasterCodeReq() {
		return masterCodeReq;
	}

	public void setMasterCodeReq(String masterCodeReq) {
		this.masterCodeReq = masterCodeReq;
	}

	public String getAvailableReq() {
		return availableReq;
	}

	public void setAvailableReq(String availableReq) {
		this.availableReq = availableReq;
	}

	public String getRequiredReq() {
		return requiredReq;
	}

	public void setRequiredReq(String requiredReq) {
		this.requiredReq = requiredReq;
	}

	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getApproverCode() {
		return approverCode;
	}

	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}

	public String getEmpWarehouse() {
		return empWarehouse;
	}

	public void setEmpWarehouse(String empWarehouse) {
		this.empWarehouse = empWarehouse;
	}

	public String getEmpWarehouseCode() {
		return empWarehouseCode;
	}

	public void setEmpWarehouseCode(String empWarehouseCode) {
		this.empWarehouseCode = empWarehouseCode;
	}

	public ArrayList getOtherWarehouseList() {
		return otherWarehouseList;
	}

	public void setOtherWarehouseList(ArrayList otherWarehouseList) {
		this.otherWarehouseList = otherWarehouseList;
	}

	public String getAssetInvType() {
		return assetInvType;
	}

	public void setAssetInvType(String assetInvType) {
		this.assetInvType = assetInvType;
	}

	public String getAssignCommentsFlag() {
		return assignCommentsFlag;
	}

	public void setAssignCommentsFlag(String assignCommentsFlag) {
		this.assignCommentsFlag = assignCommentsFlag;
	}

	public String getAssignComments() {
		return assignComments;
	}

	public void setAssignComments(String assignComments) {
		this.assignComments = assignComments;
	}

	public String getAssetUnitIterator() {
		return assetUnitIterator;
	}

	public void setAssetUnitIterator(String assetUnitIterator) {
		this.assetUnitIterator = assetUnitIterator;
	}

	public String getIsAssign() {
		return isAssign;
	}

	public void setIsAssign(String isAssign) {
		this.isAssign = isAssign;
	}

	public String getPartialAssign() {
		return partialAssign;
	}

	public void setPartialAssign(String partialAssign) {
		this.partialAssign = partialAssign;
	}

	public String getHiddenStatus() {
		return hiddenStatus;
	}

	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	public String getPartialAssignIt() {
		return partialAssignIt;
	}

	public void setPartialAssignIt(String partialAssignIt) {
		this.partialAssignIt = partialAssignIt;
	}

	public String getAssetAssignedIterator() {
		return assetAssignedIterator;
	}

	public void setAssetAssignedIterator(String assetAssignedIterator) {
		this.assetAssignedIterator = assetAssignedIterator;
	}

	public String getAssetUnassignedIterator() {
		return assetUnassignedIterator;
	}

	public void setAssetUnassignedIterator(String assetUnassignedIterator) {
		this.assetUnassignedIterator = assetUnassignedIterator;
	}

	public ArrayList getAssignedAssetList() {
		return assignedAssetList;
	}

	public void setAssignedAssetList(ArrayList assignedAssetList) {
		this.assignedAssetList = assignedAssetList;
	}

	public ArrayList getUnassignedAssetList() {
		return unassignedAssetList;
	}

	public void setUnassignedAssetList(ArrayList unassignedAssetList) {
		this.unassignedAssetList = unassignedAssetList;
	}

	public String getAssetSubTypeIteratorAssigned() {
		return assetSubTypeIteratorAssigned;
	}

	public void setAssetSubTypeIteratorAssigned(String assetSubTypeIteratorAssigned) {
		this.assetSubTypeIteratorAssigned = assetSubTypeIteratorAssigned;
	}

	public String getSubTypeCodeIteratorAssigned() {
		return subTypeCodeIteratorAssigned;
	}

	public void setSubTypeCodeIteratorAssigned(String subTypeCodeIteratorAssigned) {
		this.subTypeCodeIteratorAssigned = subTypeCodeIteratorAssigned;
	}

	public String getAssetCodeAssigned() {
		return assetCodeAssigned;
	}

	public void setAssetCodeAssigned(String assetCodeAssigned) {
		this.assetCodeAssigned = assetCodeAssigned;
	}

	public String getAsstHdTypeAssigned() {
		return asstHdTypeAssigned;
	}

	public void setAsstHdTypeAssigned(String asstHdTypeAssigned) {
		this.asstHdTypeAssigned = asstHdTypeAssigned;
	}

	public String getAssetUnitIteratorAssigned() {
		return assetUnitIteratorAssigned;
	}

	public void setAssetUnitIteratorAssigned(String assetUnitIteratorAssigned) {
		this.assetUnitIteratorAssigned = assetUnitIteratorAssigned;
	}

	public String getAssetInvTypeIterator() {
		return assetInvTypeIterator;
	}

	public void setAssetInvTypeIterator(String assetInvTypeIterator) {
		this.assetInvTypeIterator = assetInvTypeIterator;
	}

	public String getAppl_No() {
		return appl_No;
	}

	public void setAppl_No(String appl_No) {
		this.appl_No = appl_No;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getListLengthApproved() {
		return listLengthApproved;
	}

	public void setListLengthApproved(String listLengthApproved) {
		this.listLengthApproved = listLengthApproved;
	}

	public String getListLengthRejected() {
		return listLengthRejected;
	}

	public void setListLengthRejected(String listLengthRejected) {
		this.listLengthRejected = listLengthRejected;
	}

	public ArrayList getDraftList() {
		return draftList;
	}

	public void setDraftList(ArrayList draftList) {
		this.draftList = draftList;
	}

	public ArrayList getSubmittedList() {
		return submittedList;
	}

	public void setSubmittedList(ArrayList submittedList) {
		this.submittedList = submittedList;
	}

	public ArrayList getReturnedList() {
		return returnedList;
	}

	public void setReturnedList(ArrayList returnedList) {
		this.returnedList = returnedList;
	}

	public ArrayList getApprovedList() {
		return approvedList;
	}

	public void setApprovedList(ArrayList approvedList) {
		this.approvedList = approvedList;
	}

	public ArrayList getRejectedList() {
		return rejectedList;
	}

	public void setRejectedList(ArrayList rejectedList) {
		this.rejectedList = rejectedList;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getIsSentBack() {
		return isSentBack;
	}

	public void setIsSentBack(String isSentBack) {
		this.isSentBack = isSentBack;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public String getListLengthAssigned() {
		return listLengthAssigned;
	}

	public void setListLengthAssigned(String listLengthAssigned) {
		this.listLengthAssigned = listLengthAssigned;
	}

	public ArrayList getAssignedList() {
		return assignedList;
	}

	public void setAssignedList(ArrayList assignedList) {
		this.assignedList = assignedList;
	}

	public String getAssetAssignDate() {
		return assetAssignDate;
	}

	public void setAssetAssignDate(String assetAssignDate) {
		this.assetAssignDate = assetAssignDate;
	}

	public String getMessageStr() {
		return messageStr;
	}

	public void setMessageStr(String messageStr) {
		this.messageStr = messageStr;
	}

	public String getAppcomment() {
		return appcomment;
	}

	public void setAppcomment(String appcomment) {
		this.appcomment = appcomment;
	}

	public String getApproverCommentsFlag() {
		return approverCommentsFlag;
	}

	public void setApproverCommentsFlag(String approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}

	public String getHiddenappCode() {
		return hiddenappCode;
	}

	public void setHiddenappCode(String hiddenappCode) {
		this.hiddenappCode = hiddenappCode;
	}

	public int getApplicationLevel() {
		return applicationLevel;
	}

	public void setApplicationLevel(int applicationLevel) {
		this.applicationLevel = applicationLevel;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getKeepInformedEmpId() {
		return keepInformedEmpId;
	}

	public void setKeepInformedEmpId(String keepInformedEmpId) {
		this.keepInformedEmpId = keepInformedEmpId;
	}

	public String getKeepInformedEmpName() {
		return keepInformedEmpName;
	}

	public void setKeepInformedEmpName(String keepInformedEmpName) {
		this.keepInformedEmpName = keepInformedEmpName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}

	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}

	public String getCheckRemove() {
		return checkRemove;
	}

	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}

	public String getKeepInfoFlag() {
		return keepInfoFlag;
	}

	public void setKeepInfoFlag(String keepInfoFlag) {
		this.keepInfoFlag = keepInfoFlag;
	}

	public String getSrNoIterator() {
		return srNoIterator;
	}

	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}

	public ArrayList getApproverList() {
		return approverList;
	}

	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}

	public String getMyPageRejected() {
		return myPageRejected;
	}

	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}

	public String getMyPageAssigned() {
		return myPageAssigned;
	}

	public void setMyPageAssigned(String myPageAssigned) {
		this.myPageAssigned = myPageAssigned;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	

}
