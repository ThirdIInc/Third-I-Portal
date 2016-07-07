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
public class RateList extends BeanBase {

	private String vendorName;
	private String vendorCode;
	private String subTypeCode;
	private String subTypeName;
	private String price;
	private String itemIterator;
	private String priceIterator;
	private String itemCodeIterator;
	private String rateCode;
	private String rateCodeIterator;
	private String unitIterator;
	private String paraId;
	private String unit="Unit";
	private String cityName;
	private String cityNameItereator;
	private String count="";
	
	ArrayList iteratorlist;
	private String hdeleteCode;
	private String isActive="";
	 private String totalRecords="";
	 private String recordsLength="false";
	 private String myPage;
	 
	 private String iteratorvendorcode="";
	 private String iteratorratecode="";
	 private String iteratorvendorname="";
	 private String iteratorcityname="";
	
	ArrayList rateList;
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
	public ArrayList getRateList() {
		return rateList;
	}
	public void setRateList(ArrayList rateList) {
		this.rateList = rateList;
	}
	public String getItemCodeIterator() {
		return itemCodeIterator;
	}
	public void setItemCodeIterator(String itemCodeIterator) {
		this.itemCodeIterator = itemCodeIterator;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public String getRateCodeIterator() {
		return rateCodeIterator;
	}
	public void setRateCodeIterator(String rateCodeIterator) {
		this.rateCodeIterator = rateCodeIterator;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitIterator() {
		return unitIterator;
	}
	public void setUnitIterator(String unitIterator) {
		this.unitIterator = unitIterator;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityNameItereator() {
		return cityNameItereator;
	}
	public void setCityNameItereator(String cityNameItereator) {
		this.cityNameItereator = cityNameItereator;
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
	public String getIteratorvendorcode() {
		return iteratorvendorcode;
	}
	public void setIteratorvendorcode(String iteratorvendorcode) {
		this.iteratorvendorcode = iteratorvendorcode;
	}
	public String getIteratorratecode() {
		return iteratorratecode;
	}
	public void setIteratorratecode(String iteratorratecode) {
		this.iteratorratecode = iteratorratecode;
	}
	public String getIteratorvendorname() {
		return iteratorvendorname;
	}
	public void setIteratorvendorname(String iteratorvendorname) {
		this.iteratorvendorname = iteratorvendorname;
	}
	public String getIteratorcityname() {
		return iteratorcityname;
	}
	public void setIteratorcityname(String iteratorcityname) {
		this.iteratorcityname = iteratorcityname;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
