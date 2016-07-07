/**
 * 
 */
package org.paradyne.bean.Asset;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author mangeshj
 *
 */
public class AssetSubTypes extends BeanBase {
	
	
	private String hiddenEdit ="";

	private String assetCategoryCode;
	private String assetsubTypeCode ;
	private String assetCategoryName ;
	private String assetSubTypeName ;
	private String returnType ;
	private String unit ;
	private String leadTime ;
	private String isActive="";
	private String hiddenassetCategoryCode="";
	
	private String categoryIterator;
	private String hdeleteCode ;
	private String subTypeNameIterator ;
	private String subTypeCodeIterator ;
	private String returnTypeIterator ;
	
	
	private String show;
	private String hiddencode;
	private String myPage;
	private String checkid;
	private String reOrderLevel;
	private String invType;
	private String hiddenInvType;
	HashMap hashmap;
	ArrayList iteratorlist; 
	
	private String totalRecords="";
	private String listLength="false";
	
	
	private String propertyNameItt ="";

	private String  propertyUnitItt ="";
	
	private ArrayList propertyList =null ;
	
	private String  properUnit ="";
	private String  propertyName ="";
	private String propertyUnitNameItt ="";
	private String safetystock="";
	private String tableLength="";
	private String paraId;
	
	public String getSafetystock() {
		return safetystock;
	}
	public void setSafetystock(String safetystock) {
		this.safetystock = safetystock;
	}
	public String getPropertyUnitNameItt() {
		return propertyUnitNameItt;
	}
	public void setPropertyUnitNameItt(String propertyUnitNameItt) {
		this.propertyUnitNameItt = propertyUnitNameItt;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public ArrayList getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}
	public String getPropertyNameItt() {
		return propertyNameItt;
	}
	public void setPropertyNameItt(String propertyNameItt) {
		this.propertyNameItt = propertyNameItt;
	}
	public String getPropertyUnitItt() {
		return propertyUnitItt;
	}
	public void setPropertyUnitItt(String propertyUnitItt) {
		this.propertyUnitItt = propertyUnitItt;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	public String getAssetCategoryCode() {
		return assetCategoryCode;
	}
	public void setAssetCategoryCode(String assetCategoryCode) {
		this.assetCategoryCode = assetCategoryCode;
	}
	public String getAssetsubTypeCode() {
		return assetsubTypeCode;
	}
	public void setAssetsubTypeCode(String assetsubTypeCode) {
		this.assetsubTypeCode = assetsubTypeCode;
	}
	public String getAssetCategoryName() {
		return assetCategoryName;
	}
	public void setAssetCategoryName(String assetCategoryName) {
		this.assetCategoryName = assetCategoryName;
	}
	public String getAssetSubTypeName() {
		return assetSubTypeName;
	}
	public void setAssetSubTypeName(String assetSubTypeName) {
		this.assetSubTypeName = assetSubTypeName;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getCategoryIterator() {
		return categoryIterator;
	}
	public void setCategoryIterator(String categoryIterator) {
		this.categoryIterator = categoryIterator;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getSubTypeNameIterator() {
		return subTypeNameIterator;
	}
	public void setSubTypeNameIterator(String subTypeNameIterator) {
		this.subTypeNameIterator = subTypeNameIterator;
	}
	public String getSubTypeCodeIterator() {
		return subTypeCodeIterator;
	}
	public void setSubTypeCodeIterator(String subTypeCodeIterator) {
		this.subTypeCodeIterator = subTypeCodeIterator;
	}
	public String getReturnTypeIterator() {
		return returnTypeIterator;
	}
	public void setReturnTypeIterator(String returnTypeIterator) {
		this.returnTypeIterator = returnTypeIterator;
	}
	public HashMap getHashmap() {
		return hashmap;
	}
	public void setHashmap(HashMap hashmap) {
		this.hashmap = hashmap;
	}
	public String getReOrderLevel() {
		return reOrderLevel;
	}
	public void setReOrderLevel(String reOrderLevel) {
		this.reOrderLevel = reOrderLevel;
	}
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	public String getHiddenInvType() {
		return hiddenInvType;
	}
	public void setHiddenInvType(String hiddenInvType) {
		this.hiddenInvType = hiddenInvType;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getProperUnit() {
		return properUnit;
	}
	public void setProperUnit(String properUnit) {
		this.properUnit = properUnit;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getHiddenassetCategoryCode() {
		return hiddenassetCategoryCode;
	}
	public void setHiddenassetCategoryCode(String hiddenassetCategoryCode) {
		this.hiddenassetCategoryCode = hiddenassetCategoryCode;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
}
