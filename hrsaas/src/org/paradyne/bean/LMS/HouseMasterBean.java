package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class HouseMasterBean extends BeanBase {

	/** 
	 * for House list
	 */
	
	private String ittHouseId="";
	private String ittSrN0="";
	private String ittHouseNo="";
	private String ittColonyName="";
	private String ittHouseAddress="";
	ArrayList HouseMasterItt=null ;
	/*
	 * for House Master 
	 * 
	 */
	private String houseNo="";
	private String houseId="";
	private String houseAddress="";
	private String colonyId="";
	private String colonyName="";
	private String NoFamily="";
	private String NoIndividual="";
	private String myPage="";
	public String getIttHouseId() {
		return ittHouseId;
	}
	public void setIttHouseId(String ittHouseId) {
		this.ittHouseId = ittHouseId;
	}
	public String getIttSrN0() {
		return ittSrN0;
	}
	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}
	public String getIttHouseNo() {
		return ittHouseNo;
	}
	public void setIttHouseNo(String ittHouseNo) {
		this.ittHouseNo = ittHouseNo;
	}
	public String getIttHouseAddress() {
		return ittHouseAddress;
	}
	public void setIttHouseAddress(String ittHouseAddress) {
		this.ittHouseAddress = ittHouseAddress;
	}
	public ArrayList getHouseMasterItt() {
		return HouseMasterItt;
	}
	public void setHouseMasterItt(ArrayList houseMasterItt) {
		HouseMasterItt = houseMasterItt;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public String getColonyId() {
		return colonyId;
	}
	public void setColonyId(String colonyId) {
		this.colonyId = colonyId;
	}
	public String getNoFamily() {
		return NoFamily;
	}
	public void setNoFamily(String noFamily) {
		NoFamily = noFamily;
	}
	public String getNoIndividual() {
		return NoIndividual;
	}
	public void setNoIndividual(String noIndividual) {
		NoIndividual = noIndividual;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getIttColonyName() {
		return ittColonyName;
	}
	public void setIttColonyName(String ittColonyName) {
		this.ittColonyName = ittColonyName;
	}
	public String getColonyName() {
		return colonyName;
	}
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
}
