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
public class AssetAssignment extends BeanBase {

	private String empToken;
	
	private String hiddenappCode="";

	private String assetCode;
	private String empID ;
	private String empName ;
	private String hiddenAssetCode ;
	private String noData;
	private String listLength;
	private String availability;
	private String category;
	private String categoryCode;
	private String invCode;
	private String subType;
	private String subTypeCode;
	private String quantityRequired;
	private String sourceWarehouse;
	private String sourceWarehouseName;
	private String tableList;
	private String reqCode;
	private String rowId;
	private String hiddenCategoryCode;
	private String hiddenSubTypeCode;
	private String pendingFlag="";
	private String destWarehouse;
	private String hiddenReqCode;
	
	private String selectedEmpCode;
	private String selectedEmpName;
	private String selectedEmpId;
	
	 private String totalRecords="";
	 private String recordsLength="false";
	 private String myPage;
	 private String showFlag="false";
	 private String source="";
	
	private ArrayList<Object> recordList;
	
	public ArrayList<Object> getRecordList() {
		return recordList;
	}
	public void setRecordList(ArrayList<Object> recordList) {
		this.recordList = recordList;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getHiddenAssetCode() {
		return hiddenAssetCode;
	}
	public void setHiddenAssetCode(String hiddenAssetCode) {
		this.hiddenAssetCode = hiddenAssetCode;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getInvCode() {
		return invCode;
	}
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getPendingFlag() {
		return pendingFlag;
	}
	public void setPendingFlag(String pendingFlag) {
		this.pendingFlag = pendingFlag;
	}
	public String getQuantityRequired() {
		return quantityRequired;
	}
	public void setQuantityRequired(String quantityRequired) {
		this.quantityRequired = quantityRequired;
	}
	public String getTableList() {
		return tableList;
	}
	public void setTableList(String tableList) {
		this.tableList = tableList;
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
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getSubTypeCode() {
		return subTypeCode;
	}
	public void setSubTypeCode(String subTypeCode) {
		this.subTypeCode = subTypeCode;
	}
	public String getSourceWarehouse() {
		return sourceWarehouse;
	}
	public void setSourceWarehouse(String sourceWarehouse) {
		this.sourceWarehouse = sourceWarehouse;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public String getSourceWarehouseName() {
		return sourceWarehouseName;
	}
	public void setSourceWarehouseName(String sourceWarehouseName) {
		this.sourceWarehouseName = sourceWarehouseName;
	}
	public String getDestWarehouse() {
		return destWarehouse;
	}
	public void setDestWarehouse(String destWarehouse) {
		this.destWarehouse = destWarehouse;
	}
	public String getHiddenReqCode() {
		return hiddenReqCode;
	}
	public void setHiddenReqCode(String hiddenReqCode) {
		this.hiddenReqCode = hiddenReqCode;
	}
	public String getSelectedEmpCode() {
		return selectedEmpCode;
	}
	public void setSelectedEmpCode(String selectedEmpCode) {
		this.selectedEmpCode = selectedEmpCode;
	}
	public String getSelectedEmpName() {
		return selectedEmpName;
	}
	public void setSelectedEmpName(String selectedEmpName) {
		this.selectedEmpName = selectedEmpName;
	}
	public String getSelectedEmpId() {
		return selectedEmpId;
	}
	public void setSelectedEmpId(String selectedEmpId) {
		this.selectedEmpId = selectedEmpId;
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
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getHiddenappCode() {
		return hiddenappCode;
	}
	public void setHiddenappCode(String hiddenappCode) {
		this.hiddenappCode = hiddenappCode;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
}
