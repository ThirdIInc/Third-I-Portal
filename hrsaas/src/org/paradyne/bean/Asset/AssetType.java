package org.paradyne.bean.Asset;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class AssetType extends BeanBase {
	private String assetCode;
	private String assetCodeIterator;
	private String assetname;
	private String type;
	private String unit;
	private String typeIterator;
	ArrayList tableList1;
	ArrayList iteratorlist; 
	
	private String totalRecords="";
	private String show;
	private String hiddencode;
	private String myPage;
	private String checkid;
	private String isActive;
	private String hdeleteCode;

	private String listLength="false";

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	
	
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public ArrayList getTableList1() {
		return tableList1;
	}
	public void setTableList1(ArrayList tableList1) {
		this.tableList1 = tableList1;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
	public String getAssetCodeIterator() {
		return assetCodeIterator;
	}
	public void setAssetCodeIterator(String assetCodeIterator) {
		this.assetCodeIterator = assetCodeIterator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTypeIterator() {
		return typeIterator;
	}

	public void setTypeIterator(String typeIterator) {
		this.typeIterator = typeIterator;
	}

	public String getListLength() {
		return listLength;
	}

	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	

}
