package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AssetDirectAssignment extends BeanBase {
	private String assetcategorycode = "";
	private String assetcategory = "";
	private String assetsubtypecode = "";
	private String assetsubtype = "";
	private String employeecode = "";
	private String employeename= "";
	private String vendorid = "";
	private String vendorname = "";
	private String assignDate = "";
	private String assetassignmentid="";
	private String usertypeRadioOptionValue="";
	private String inventoryname="";
	private String inventorycode="";
	private String assignquantity="";
	private String availablequantity="";
	private String usertype="";
	private String assetcode="";
	private String employeeToken="";
	
	private String ittremployeename="";
	private String ittremployeecode="";
	private String ittrvendorname="";
	private String ittrinventory="";
	private String ittrassetcategory="";
	private String ittrassetsubtype="";
	private String ittrassignquantity="";
	private String ittrassigndate="";
	private String ittrassetownby="";
	private String ittrvendorid="";
	
	private String assetautoid="";
	
	private ArrayList assetassignmentList=null;
	
	

	private String show;
	
	
	
	
	public String getAssetassignmentid() {
		return assetassignmentid;
	}
	public void setAssetassignmentid(String assetassignmentid) {
		this.assetassignmentid = assetassignmentid;
	}
	public String getAssetcategorycode() {
		return assetcategorycode;
	}
	public void setAssetcategorycode(String assetcategorycode) {
		this.assetcategorycode = assetcategorycode;
	}
	public String getAssetcategory() {
		return assetcategory;
	}
	public void setAssetcategory(String assetcategory) {
		this.assetcategory = assetcategory;
	}
	public String getAssetsubtypecode() {
		return assetsubtypecode;
	}
	public void setAssetsubtypecode(String assetsubtypecode) {
		this.assetsubtypecode = assetsubtypecode;
	}
	public String getAssetsubtype() {
		return assetsubtype;
	}
	public void setAssetsubtype(String assetsubtype) {
		this.assetsubtype = assetsubtype;
	}
	public String getEmployeecode() {
		return employeecode;
	}
	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getVendorid() {
		return vendorid;
	}
	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	public String getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	
	public String getUsertypeRadioOptionValue() {
		return usertypeRadioOptionValue;
	}
	public void setUsertypeRadioOptionValue(String usertypeRadioOptionValue) {
		this.usertypeRadioOptionValue = usertypeRadioOptionValue;
	}
	public String getInventoryname() {
		return inventoryname;
	}
	public void setInventoryname(String inventoryname) {
		this.inventoryname = inventoryname;
	}
	public String getInventorycode() {
		return inventorycode;
	}
	public void setInventorycode(String inventorycode) {
		this.inventorycode = inventorycode;
	}
	public String getAssignquantity() {
		return assignquantity;
	}
	public void setAssignquantity(String assignquantity) {
		this.assignquantity = assignquantity;
	}
	public String getAvailablequantity() {
		return availablequantity;
	}
	public void setAvailablequantity(String availablequantity) {
		this.availablequantity = availablequantity;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getAssetcode() {
		return assetcode;
	}
	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getIttremployeename() {
		return ittremployeename;
	}
	public void setIttremployeename(String ittremployeename) {
		this.ittremployeename = ittremployeename;
	}
	public String getIttremployeecode() {
		return ittremployeecode;
	}
	public void setIttremployeecode(String ittremployeecode) {
		this.ittremployeecode = ittremployeecode;
	}
	public String getIttrvendorname() {
		return ittrvendorname;
	}
	public void setIttrvendorname(String ittrvendorname) {
		this.ittrvendorname = ittrvendorname;
	}
	public String getIttrinventory() {
		return ittrinventory;
	}
	public void setIttrinventory(String ittrinventory) {
		this.ittrinventory = ittrinventory;
	}
	public String getIttrassetcategory() {
		return ittrassetcategory;
	}
	public void setIttrassetcategory(String ittrassetcategory) {
		this.ittrassetcategory = ittrassetcategory;
	}
	public String getIttrassetsubtype() {
		return ittrassetsubtype;
	}
	public void setIttrassetsubtype(String ittrassetsubtype) {
		this.ittrassetsubtype = ittrassetsubtype;
	}
	public String getIttrassignquantity() {
		return ittrassignquantity;
	}
	public void setIttrassignquantity(String ittrassignquantity) {
		this.ittrassignquantity = ittrassignquantity;
	}
	public String getIttrassigndate() {
		return ittrassigndate;
	}
	public void setIttrassigndate(String ittrassigndate) {
		this.ittrassigndate = ittrassigndate;
	}
	public ArrayList getAssetassignmentList() {
		return assetassignmentList;
	}
	public void setAssetassignmentList(ArrayList assetassignmentList) {
		this.assetassignmentList = assetassignmentList;
	}
	public String getIttrassetownby() {
		return ittrassetownby;
	}
	public void setIttrassetownby(String ittrassetownby) {
		this.ittrassetownby = ittrassetownby;
	}
	public String getIttrvendorid() {
		return ittrvendorid;
	}
	public void setIttrvendorid(String ittrvendorid) {
		this.ittrvendorid = ittrvendorid;
	}
	public String getAssetautoid() {
		return assetautoid;
	}
	public void setAssetautoid(String assetautoid) {
		this.assetautoid = assetautoid;
	}

}
