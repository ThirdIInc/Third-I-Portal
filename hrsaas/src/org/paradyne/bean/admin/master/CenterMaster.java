/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;
public class CenterMaster extends BeanBase{
	private String modeLength="false";
	private String totalRecords="";
	private String centerID;
	private String centerName;
	private String centerAbbr;
	private String locId;
	private String locName;
	private String add1;
	private String add2;
	private String add3;
	private String city;
	private String pin;
	private String tel;
	private String fax;
	private	String isActive;
	private String esiZone;
	private String ptZone;
	private String pfZone;
	ArrayList  centerList;
	private String myPage;
	private String show;
	private String  hiddencode;
	private String address;
	private String hdeleteCode;
	TreeMap map;
	private String zone="";
	private String centerType="";
		
	public TreeMap getMap() {
		return map;
	}
	public void setMap(TreeMap map) {
		this.map = map;
	}
	public String getCenterID() {
		return centerID;
	}
	public void setCenterID(String centerID) {
		this.centerID = centerID;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getAdd3() {
		return add3;
	}
	public void setAdd3(String add3) {
		this.add3 = add3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEsiZone() {
		return esiZone;
	}
	public void setEsiZone(String esiZone) {
		this.esiZone = esiZone;
	}
	public String getPtZone() {
		return ptZone;
	}
	public void setPtZone(String ptZone) {
		this.ptZone = ptZone;
	}
	public ArrayList getCenterList() {
		return centerList;
	}
	public void setCenterList(ArrayList centerList) {
		this.centerList = centerList;
	}

	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getPfZone() {
		return pfZone;
	}
	public void setPfZone(String pfZone) {
		this.pfZone = pfZone;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getCenterAbbr() {
		return centerAbbr;
	}
	public void setCenterAbbr(String centerAbbr) {
		this.centerAbbr = centerAbbr;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getCenterType() {
		return centerType;
	}
	public void setCenterType(String centerType) {
		this.centerType = centerType;
	}
	
}
