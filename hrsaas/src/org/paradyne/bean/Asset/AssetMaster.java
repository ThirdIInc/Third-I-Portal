package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AssetMaster extends BeanBase {
	
	private String code;
	private String invCode;
	private String purchaseDate;
	private String assetname;
	private String assetCode;
	private String price;
	private String status;
	private String description;
	private String quant;
	private String subTypeCode;
	private String assigned;
	private String available;
	private String subTypeName;
	private String vendorName;
	private String vendorCode;
	private String assetUnit="unit";
	private String warehouseCode;
	private String warehouseName;
	private String invType;
	private String quantityWareHouse ;
	private String invPrefix ;
	private String fromInv;
	private String toInv;
	private String inventoryCodeIterator;
	private String warehouseNameIterator;
	private String warehouseCodeIterator;
	private String quantityIterator;
	private String availableIterator;
	private String listLength;
	
	private String tableLength;
	private String assignFlagIterator;
	private String itemizedFlag="false";
	private String commonFlag="false";
	private String paraId;
	private String updateApp ;
	
	private String purchaseFlag="false";
	private String hiddenPurchaseCode;
	
	private String totalRecords="";
	private String makeList="false";
	private String myPage;
	private String hdeleteCode;
	private String hiddencode;
	ArrayList iteratorList;
	ArrayList tableList;
	ArrayList propertyHeadList;
	ArrayList propertyListDtl;
	private String propertyName;
	private String propertyCode;
	private String propertyNameDtl="";
	
	private String propertyUnit="";
	
	private String isLeased = "N";
	private String isInsured = "N";
	private String leaseDate = "";
	private String insureDate = "";
	
	private String inventoryCodeItt ="";
	ArrayList propertyList =null ;
	private String tabDisplay="false";
	private String propertyDataFlag="false";
	
	
	public String getTabDisplay() {
		return tabDisplay;
	}
	public void setTabDisplay(String tabDisplay) {
		this.tabDisplay = tabDisplay;
	}
	public ArrayList getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}
	public String getInventoryCodeItt() {
		return inventoryCodeItt;
	}
	public void setInventoryCodeItt(String inventoryCodeItt) {
		this.inventoryCodeItt = inventoryCodeItt;
	}
	public String getIsLeased() {
		return isLeased;
	}
	public void setIsLeased(String isLeased) {
		this.isLeased = isLeased;
	}
	public String getIsInsured() {
		return isInsured;
	}
	public void setIsInsured(String isInsured) {
		this.isInsured = isInsured;
	}
	public String getLeaseDate() {
		return leaseDate;
	}
	public void setLeaseDate(String leaseDate) {
		this.leaseDate = leaseDate;
	}
	public String getInsureDate() {
		return insureDate;
	}
	public void setInsureDate(String insureDate) {
		this.insureDate = insureDate;
	}
	public String getQuantityWareHouse() {
		return quantityWareHouse;
	}
	public void setQuantityWareHouse(String quantityWareHouse) {
		this.quantityWareHouse = quantityWareHouse;
	}
	public String getInvPrefix() {
		return invPrefix;
	}
	public void setInvPrefix(String invPrefix) {
		this.invPrefix = invPrefix;
	}
	public ArrayList getTableList() {
		return tableList;
	}
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the invCode
	 */
	public String getInvCode() {
		return invCode;
	}
	/**
	 * @param invCode the invCode to set
	 */
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}
	/**
	 * @return the purchaseDate
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**	
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getQuant() {
		return quant;
	}
	public void setQuant(String quant) {
		this.quant = quant;
	}
	public String getSubTypeCode() {
		return subTypeCode;
	}
	public void setSubTypeCode(String subTypeCode) {
		this.subTypeCode = subTypeCode;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
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
	public String getAssetUnit() {
		return assetUnit;
	}
	public void setAssetUnit(String assetUnit) {
		this.assetUnit = assetUnit;
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
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	public String getItemizedFlag() {
		return itemizedFlag;
	}
	public void setItemizedFlag(String itemizedFlag) {
		this.itemizedFlag = itemizedFlag;
	}
	public String getInventoryCodeIterator() {
		return inventoryCodeIterator;
	}
	public void setInventoryCodeIterator(String inventoryCodeIterator) {
		this.inventoryCodeIterator = inventoryCodeIterator;
	}
	public String getWarehouseNameIterator() {
		return warehouseNameIterator;
	}
	public void setWarehouseNameIterator(String warehouseNameIterator) {
		this.warehouseNameIterator = warehouseNameIterator;
	}
	public String getWarehouseCodeIterator() {
		return warehouseCodeIterator;
	}
	public void setWarehouseCodeIterator(String warehouseCodeIterator) {
		this.warehouseCodeIterator = warehouseCodeIterator;
	}
	public String getFromInv() {
		return fromInv;
	}
	public void setFromInv(String fromInv) {
		this.fromInv = fromInv;
	}
	public String getToInv() {
		return toInv;
	}
	public void setToInv(String toInv) {
		this.toInv = toInv;
	}
	public String getCommonFlag() {
		return commonFlag;
	}
	public void setCommonFlag(String commonFlag) {
		this.commonFlag = commonFlag;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getAssignFlagIterator() {
		return assignFlagIterator;
	}
	public void setAssignFlagIterator(String assignFlagIterator) {
		this.assignFlagIterator = assignFlagIterator;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getQuantityIterator() {
		return quantityIterator;
	}
	public void setQuantityIterator(String quantityIterator) {
		this.quantityIterator = quantityIterator;
	}
	public String getAvailableIterator() {
		return availableIterator;
	}
	public void setAvailableIterator(String availableIterator) {
		this.availableIterator = availableIterator;
	}
	public String getUpdateApp() {
		return updateApp;
	}
	public void setUpdateApp(String updateApp) {
		this.updateApp = updateApp;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getPurchaseFlag() {
		return purchaseFlag;
	}
	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}
	public String getHiddenPurchaseCode() {
		return hiddenPurchaseCode;
	}
	public void setHiddenPurchaseCode(String hiddenPurchaseCode) {
		this.hiddenPurchaseCode = hiddenPurchaseCode;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getMakeList() {
		return makeList;
	}
	public void setMakeList(String makeList) {
		this.makeList = makeList;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public ArrayList getIteratorList() {
		return iteratorList;
	}
	public void setIteratorList(ArrayList iteratorList) {
		this.iteratorList = iteratorList;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getPropertyDataFlag() {
		return propertyDataFlag;
	}
	public void setPropertyDataFlag(String propertyDataFlag) {
		this.propertyDataFlag = propertyDataFlag;
	}
	public ArrayList getPropertyHeadList() {
		return propertyHeadList;
	}
	public void setPropertyHeadList(ArrayList propertyHeadList) {
		this.propertyHeadList = propertyHeadList;
	}
	public ArrayList getPropertyListDtl() {
		return propertyListDtl;
	}
	public void setPropertyListDtl(ArrayList propertyListDtl) {
		this.propertyListDtl = propertyListDtl;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyCode() {
		return propertyCode;
	}
	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}
	public String getPropertyNameDtl() {
		return propertyNameDtl;
	}
	public void setPropertyNameDtl(String propertyNameDtl) {
		this.propertyNameDtl = propertyNameDtl;
	}
	public String getPropertyUnit() {
		return propertyUnit;
	}
	public void setPropertyUnit(String propertyUnit) {
		this.propertyUnit = propertyUnit;
	}
	
}
