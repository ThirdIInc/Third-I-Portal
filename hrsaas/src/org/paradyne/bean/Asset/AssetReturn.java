package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AssetReturn extends BeanBase {
	private String empId;
	private String empTokenNo;
	
	private String empName;
	private ArrayList<Object> retList;
	
	private String assetTypeCodeItt="";
	private String assetSubTypeCodeItt ="";
	private String rowNum;
	private String category;
	private String subType;
	private String inventory;
	private String appCode;
	private String hDate;
	private String masterCode;
	private String status;
	private String showFlag="false";
	private String warehouseCode;
	private String returnStatus;
	private String hiddenReturnStatus;
	private String chk = "false";
	private String disb;
	private String vendorName="";
	private String vendorId="";
	
	
	private String employeeOrVendorType="";
	private String employeeOrVendorCode="";
	
	private String hiddenAutoCodeItt ="";

	public String getHiddenAutoCodeItt() {
		return hiddenAutoCodeItt;
	}
	public void setHiddenAutoCodeItt(String hiddenAutoCodeItt) {
		this.hiddenAutoCodeItt = hiddenAutoCodeItt;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpTokenNo() {
		return empTokenNo;
	}
	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}
	public ArrayList<Object> getRetList() {
		return retList;
	}
	public void setRetList(ArrayList<Object> retList) {
		this.retList = retList;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	/*public boolean isListFlag() {
		return listFlag;
	}
	public void setListFlag(boolean listFlag) {
		this.listFlag = listFlag;
	}*/
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getHDate() {
		return hDate;
	}
	public void setHDate(String date) {
		hDate = date;
	}
	public String getDisb() {
		return disb;
	}
	public void setDisb(String disb) {
		this.disb = disb;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getMasterCode() {
		return masterCode;
	}
	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getHiddenReturnStatus() {
		return hiddenReturnStatus;
	}
	public void setHiddenReturnStatus(String hiddenReturnStatus) {
		this.hiddenReturnStatus = hiddenReturnStatus;
	}
	public String getEmployeeOrVendorType() {
		return employeeOrVendorType;
	}
	public void setEmployeeOrVendorType(String employeeOrVendorType) {
		this.employeeOrVendorType = employeeOrVendorType;
	}
	public String getEmployeeOrVendorCode() {
		return employeeOrVendorCode;
	}
	public void setEmployeeOrVendorCode(String employeeOrVendorCode) {
		this.employeeOrVendorCode = employeeOrVendorCode;
	}
	public String getAssetTypeCodeItt() {
		return assetTypeCodeItt;
	}
	public void setAssetTypeCodeItt(String assetTypeCodeItt) {
		this.assetTypeCodeItt = assetTypeCodeItt;
	}
	public String getAssetSubTypeCodeItt() {
		return assetSubTypeCodeItt;
	}
	public void setAssetSubTypeCodeItt(String assetSubTypeCodeItt) {
		this.assetSubTypeCodeItt = assetSubTypeCodeItt;
	}
	
	
	
	

}
