/**
 * 
 */
package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author mangeshj
 *
 */
public class PurchaseOrder extends BeanBase {
	
	
	private String source ="";
	
	
	private String myPageRejected="";
private String approverCommentsFlag="false";
	private String shippingCost="";
	private String purchaseNoItt="";
	private String priceAdjust="";
	private String additionalTaxRate="";
	private String additionalTaxAmount="";
	private String salesTaxAmount="";
	private String salesTax="";
	private String totalallAmount="";
	private String totalnetAmount="";
	private String discount="";
	private String caldiscount="";
	private String createdDate="";
	private String costcenter="";
	private String remarks="";
	private String terms="";
	private String costCenterId="";
	private String subcostcenter="";
	private String subcostCenterId="";
	private String vendorAddress="";
	private String vendorContact="";
	private String shippingAddress="";
	private String billingAddress="";
	private String keepInfoFlag="true";
	private String checkRemove="";
	private String poName="";
	private String requiredBy="";
	private String insertFlag="true";
	private String referenceId="";
	private int applicationLevel=0;
	private String operationType="";
	 
	
	
	private String appcomment="";
	private String hiddenPurchaseCode="";
	
	private String showeditflag="true";
	
	private String purchaseCode;
	private String empCode;
	private String empToken;
	private String empName;
	private String orderDate;
	private String approverCode ;
	
	private String employeeId;
	private String employeeName;
	private String employeeToken;
	private ArrayList keepInformedList;
	private String serialNo;
	private String keepInformedEmpId;
	private String keepInformedEmpName;
	private ArrayList approverList;
	private String srNoIterator;
	
	private String vendorName;
	private String vendorCode;
	private String cityName;
	private String subTypeCode;
	private String subTypeName;
	private String price;
	private String quantity;
	private String itemIterator;
	private String priceIterator;
	private String itemCodeIterator;
	private String vendorCodeIterator;
	private String quantityIterator;
	private String unitIterator;
	private String totalIterator;
	private String vendorNameIterator;
	private String cityIterator;
	private String totalPrice;
	private String totalAmount;
	private String comments;
	private String status;
	private String hiddenStatus;
	private String commentFlag;
	private String IsApprove="false";
	private String approverName ;
	private String approvedDate ;
	private String approveStatus ;
	private String approverComment ;
	private ArrayList apprList;
	private String paraId;
	private String unit="Unit";
	private String today;
	private String purchaseDtlCode;
	private String hiddenDtlCode;
	private String purchaseOrderNo;
	ArrayList orderList;
	
	private ArrayList draftList;
	private ArrayList submittedList;
	private ArrayList returnedList;
	private ArrayList approvedList;
	private ArrayList rejectedList;
	private ArrayList assignedList;
	private String listLengthAssigned="false";
	private String listLengthRejected="false";
	private String listLengthApproved="false";
	private String listType="";
	private String code;
	
	/*
	 * 
	 * for inward
	 * 
	 */
	
	private String inwardFlag="false";
	private String noData="false";
	private String noDataForCancel;
	ArrayList cancelList;
	
	ArrayList iteratorlist;
	private String hdeleteCode;
	private String isActive="";
	 private String totalRecords="";
	 private String recordsLength="false";
	 private String myPage;
	 
	 private String ittrpurchasecode="";
	 private String ittrpurchaseempid="";
	 private String ittrpurchaseempname="";
	 private String ittrpurchaseorderdate="";
	 private String ittrpurchasestatus="";
	 private String ittrpurchaseempcode="";
	 private String ittrpurchasevendorcode="";
	 private String ittrpurchasevendorname="";
	 private String ittrpurchaselocation="";
	
	
	
	public String getIttrpurchaseempcode() {
		return ittrpurchaseempcode;
	}
	public void setIttrpurchaseempcode(String ittrpurchaseempcode) {
		this.ittrpurchaseempcode = ittrpurchaseempcode;
	}
	public String getIttrpurchasevendorcode() {
		return ittrpurchasevendorcode;
	}
	public void setIttrpurchasevendorcode(String ittrpurchasevendorcode) {
		this.ittrpurchasevendorcode = ittrpurchasevendorcode;
	}
	public String getIttrpurchasevendorname() {
		return ittrpurchasevendorname;
	}
	public void setIttrpurchasevendorname(String ittrpurchasevendorname) {
		this.ittrpurchasevendorname = ittrpurchasevendorname;
	}
	public String getIttrpurchaselocation() {
		return ittrpurchaselocation;
	}
	public void setIttrpurchaselocation(String ittrpurchaselocation) {
		this.ittrpurchaselocation = ittrpurchaselocation;
	}
	public String getIttrpurchasecode() {
		return ittrpurchasecode;
	}
	public void setIttrpurchasecode(String ittrpurchasecode) {
		this.ittrpurchasecode = ittrpurchasecode;
	}
	public String getIttrpurchaseempid() {
		return ittrpurchaseempid;
	}
	public void setIttrpurchaseempid(String ittrpurchaseempid) {
		this.ittrpurchaseempid = ittrpurchaseempid;
	}
	public String getIttrpurchaseempname() {
		return ittrpurchaseempname;
	}
	public void setIttrpurchaseempname(String ittrpurchaseempname) {
		this.ittrpurchaseempname = ittrpurchaseempname;
	}
	public String getIttrpurchaseorderdate() {
		return ittrpurchaseorderdate;
	}
	public void setIttrpurchaseorderdate(String ittrpurchaseorderdate) {
		this.ittrpurchaseorderdate = ittrpurchaseorderdate;
	}
	public String getIttrpurchasestatus() {
		return ittrpurchasestatus;
	}
	public void setIttrpurchasestatus(String ittrpurchasestatus) {
		this.ittrpurchasestatus = ittrpurchasestatus;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getRecordsLength() {
		return recordsLength;
	}
	public void setRecordsLength(String recordsLength) {
		this.recordsLength = recordsLength;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getNoDataForCancel() {
		return noDataForCancel;
	}
	public void setNoDataForCancel(String noDataForCancel) {
		this.noDataForCancel = noDataForCancel;
	}
	public ArrayList getCancelList() {
		return cancelList;
	}
	public void setCancelList(ArrayList cancelList) {
		this.cancelList = cancelList;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public ArrayList getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList orderList) {
		this.orderList = orderList;
	}
	public String getUnitIterator() {
		return unitIterator;
	}
	public void setUnitIterator(String unitIterator) {
		this.unitIterator = unitIterator;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getSubTypeCode() {
		return subTypeCode;
	}
	public void setSubTypeCode(String subTypeCode) {
		this.subTypeCode = subTypeCode;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getItemIterator() {
		return itemIterator;
	}
	public void setItemIterator(String itemIterator) {
		this.itemIterator = itemIterator;
	}
	public String getPriceIterator() {
		return priceIterator;
	}
	public void setPriceIterator(String priceIterator) {
		this.priceIterator = priceIterator;
	}
	public String getItemCodeIterator() {
		return itemCodeIterator;
	}
	public void setItemCodeIterator(String itemCodeIterator) {
		this.itemCodeIterator = itemCodeIterator;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getVendorCodeIterator() {
		return vendorCodeIterator;
	}
	public void setVendorCodeIterator(String vendorCodeIterator) {
		this.vendorCodeIterator = vendorCodeIterator;
	}
	
	public String getVendorNameIterator() {
		return vendorNameIterator;
	}
	public void setVendorNameIterator(String vendorNameIterator) {
		this.vendorNameIterator = vendorNameIterator;
	}
	public String getQuantityIterator() {
		return quantityIterator;
	}
	public void setQuantityIterator(String quantityIterator) {
		this.quantityIterator = quantityIterator;
	}
	public String getTotalIterator() {
		return totalIterator;
	}
	public void setTotalIterator(String totalIterator) {
		this.totalIterator = totalIterator;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityIterator() {
		return cityIterator;
	}
	public void setCityIterator(String cityIterator) {
		this.cityIterator = cityIterator;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getIsApprove() {
		return IsApprove;
	}
	public void setIsApprove(String isApprove) {
		IsApprove = isApprove;
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
	public String getInwardFlag() {
		return inwardFlag;
	}
	public void setInwardFlag(String inwardFlag) {
		this.inwardFlag = inwardFlag;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getApproverCode() {
		return approverCode;
	}
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	public String getPurchaseDtlCode() {
		return purchaseDtlCode;
	}
	public void setPurchaseDtlCode(String purchaseDtlCode) {
		this.purchaseDtlCode = purchaseDtlCode;
	}
	public String getHiddenDtlCode() {
		return hiddenDtlCode;
	}
	public void setHiddenDtlCode(String hiddenDtlCode) {
		this.hiddenDtlCode = hiddenDtlCode;
	}
	public String getHiddenStatus() {
		return hiddenStatus;
	}
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}
	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}
	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}
	public String getApproverCommentsFlag() {
		return approverCommentsFlag;
	}
	public void setApproverCommentsFlag(String approverCommentsFlag) {
		this.approverCommentsFlag = approverCommentsFlag;
	}
	public String getAppcomment() {
		return appcomment;
	}
	public void setAppcomment(String appcomment) {
		this.appcomment = appcomment;
	}
	public String getHiddenPurchaseCode() {
		return hiddenPurchaseCode;
	}
	public void setHiddenPurchaseCode(String hiddenPurchaseCode) {
		this.hiddenPurchaseCode = hiddenPurchaseCode;
	}
	public String getShoweditflag() {
		return showeditflag;
	}
	public void setShoweditflag(String showeditflag) {
		this.showeditflag = showeditflag;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArrayList getApprovedList() {
		return approvedList;
	}
	public void setApprovedList(ArrayList approvedList) {
		this.approvedList = approvedList;
	}
	public String getListLengthApproved() {
		return listLengthApproved;
	}
	public void setListLengthApproved(String listLengthApproved) {
		this.listLengthApproved = listLengthApproved;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public ArrayList getRejectedList() {
		return rejectedList;
	}
	public void setRejectedList(ArrayList rejectedList) {
		this.rejectedList = rejectedList;
	}
	public String getListLengthRejected() {
		return listLengthRejected;
	}
	public void setListLengthRejected(String listLengthRejected) {
		this.listLengthRejected = listLengthRejected;
	}
	public ArrayList getAssignedList() {
		return assignedList;
	}
	public void setAssignedList(ArrayList assignedList) {
		this.assignedList = assignedList;
	}
	public String getListLengthAssigned() {
		return listLengthAssigned;
	}
	public void setListLengthAssigned(String listLengthAssigned) {
		this.listLengthAssigned = listLengthAssigned;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public ArrayList getKeepInformedList() {
		return keepInformedList;
	}
	public void setKeepInformedList(ArrayList keepInformedList) {
		this.keepInformedList = keepInformedList;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	public ArrayList getApproverList() {
		return approverList;
	}
	public void setApproverList(ArrayList approverList) {
		this.approverList = approverList;
	}
	public String getSrNoIterator() {
		return srNoIterator;
	}
	public void setSrNoIterator(String srNoIterator) {
		this.srNoIterator = srNoIterator;
	}
	public String getPriceAdjust() {
		return priceAdjust;
	}
	public void setPriceAdjust(String priceAdjust) {
		this.priceAdjust = priceAdjust;
	}
	public String getAdditionalTaxRate() {
		return additionalTaxRate;
	}
	public void setAdditionalTaxRate(String additionalTaxRate) {
		this.additionalTaxRate = additionalTaxRate;
	}
	public String getAdditionalTaxAmount() {
		return additionalTaxAmount;
	}
	public void setAdditionalTaxAmount(String additionalTaxAmount) {
		this.additionalTaxAmount = additionalTaxAmount;
	}
	public String getSalesTaxAmount() {
		return salesTaxAmount;
	}
	public void setSalesTaxAmount(String salesTaxAmount) {
		this.salesTaxAmount = salesTaxAmount;
	}
	public String getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(String salesTax) {
		this.salesTax = salesTax;
	}
	public String getTotalallAmount() {
		return totalallAmount;
	}
	public void setTotalallAmount(String totalallAmount) {
		this.totalallAmount = totalallAmount;
	}
	public String getTotalnetAmount() {
		return totalnetAmount;
	}
	public void setTotalnetAmount(String totalnetAmount) {
		this.totalnetAmount = totalnetAmount;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getCaldiscount() {
		return caldiscount;
	}
	public void setCaldiscount(String caldiscount) {
		this.caldiscount = caldiscount;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCostcenter() {
		return costcenter;
	}
	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public String getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(String costCenterId) {
		this.costCenterId = costCenterId;
	}
	public String getSubcostcenter() {
		return subcostcenter;
	}
	public void setSubcostcenter(String subcostcenter) {
		this.subcostcenter = subcostcenter;
	}
	public String getSubcostCenterId() {
		return subcostCenterId;
	}
	public void setSubcostCenterId(String subcostCenterId) {
		this.subcostCenterId = subcostCenterId;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public String getVendorContact() {
		return vendorContact;
	}
	public void setVendorContact(String vendorContact) {
		this.vendorContact = vendorContact;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getKeepInfoFlag() {
		return keepInfoFlag;
	}
	public void setKeepInfoFlag(String keepInfoFlag) {
		this.keepInfoFlag = keepInfoFlag;
	}
	public String getCheckRemove() {
		return checkRemove;
	}
	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}
	public String getPoName() {
		return poName;
	}
	public void setPoName(String poName) {
		this.poName = poName;
	}
	public String getRequiredBy() {
		return requiredBy;
	}
	public void setRequiredBy(String requiredBy) {
		this.requiredBy = requiredBy;
	}
	public String getInsertFlag() {
		return insertFlag;
	}
	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}
	public String getPurchaseNoItt() {
		return purchaseNoItt;
	}
	public void setPurchaseNoItt(String purchaseNoItt) {
		this.purchaseNoItt = purchaseNoItt;
	}
	public String getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(String shippingCost) {
		this.shippingCost = shippingCost;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public int getApplicationLevel() {
		return applicationLevel;
	}
	public void setApplicationLevel(int applicationLevel) {
		this.applicationLevel = applicationLevel;
	}
 
	public String getMyPageRejected() {
		return myPageRejected;
	}
	public void setMyPageRejected(String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
 
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
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
 
	
	
}
