/**
 * 
 */
package org.paradyne.bean.Asset;

import org.paradyne.lib.BeanBase;

/**
 * @author mangeshj
 *
 */
public class AssetReport extends BeanBase {
	private String warehouseCode;         
	private String warehouseName;
	private String branchName;
	private String branchCode;
	private String subTypeCode;
	private String subTypeName;
	private String vendorCode;
	private String vendorName;
	private String returnType;
	private String availability;
	private String toDate;
	private String fromDate;
	private String today;
	private String reportType;
	private String returnFlag="true";
	private String categoryCode="";
	private String categoryName;
	
	//Added by Ayyappa
	private String leaFromDate;
	private String leaToDate;
	private String insFromDate;
	private String insToDate;
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getReturnFlag() {
		return returnFlag;
	}
	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getLeaFromDate() {
		return leaFromDate;
	}
	public void setLeaFromDate(String leaFromDate) {
		this.leaFromDate = leaFromDate;
	}
	public String getLeaToDate() {
		return leaToDate;
	}
	public void setLeaToDate(String leaToDate) {
		this.leaToDate = leaToDate;
	}
	public String getInsFromDate() {
		return insFromDate;
	}
	public void setInsFromDate(String insFromDate) {
		this.insFromDate = insFromDate;
	}
	public String getInsToDate() {
		return insToDate;
	}
	public void setInsToDate(String insToDate) {
		this.insToDate = insToDate;
	}
	
}
