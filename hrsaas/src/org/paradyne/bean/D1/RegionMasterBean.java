package org.paradyne.bean.D1;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class RegionMasterBean extends BeanBase {

	
	public String regionName="";
   public String regionCode="";

   public String hdeleteCode="";
   
   private String myPage  = "" ;
	private String modeLength  = "" ;
	private String totalNoOfRecords  = "" ;
   private ArrayList regionList=null;	
   
   
   /**
 * @return the myPage
 */
public String getMyPage() {
	return myPage;
}

/**
 * @param myPage the myPage to set
 */
public void setMyPage(String myPage) {
	this.myPage = myPage;
}

/**
 * @return the modeLength
 */
public String getModeLength() {
	return modeLength;
}

/**
 * @param modeLength the modeLength to set
 */
public void setModeLength(String modeLength) {
	this.modeLength = modeLength;
}

/**
 * @return the totalNoOfRecords
 */
public String getTotalNoOfRecords() {
	return totalNoOfRecords;
}

/**
 * @param totalNoOfRecords the totalNoOfRecords to set
 */
public void setTotalNoOfRecords(String totalNoOfRecords) {
	this.totalNoOfRecords = totalNoOfRecords;
}

/**
 * @return the regionCode
 */
public String getRegionCode() {
	return regionCode;
}

/**
 * @param regionCode the regionCode to set
 */
public void setRegionCode(String regionCode) {
	this.regionCode = regionCode;
}

	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/**
	 * @return the regionList
	 */
	public ArrayList getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(ArrayList regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}

	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
}
