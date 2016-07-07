package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class TravelRatingBean extends BeanBase {
	
	private String ratingParameter="";
	private String ratingType="";


	private String onLoadFlag="false";
	private String flagShow="false";

	private String hiddencode  = "" ;

	
	private String myPage  = "" ;
	private String modeLength  = "" ;
	private String totalNoOfRecords  = "" ;
	
	private String ratingId="";
	
	private String hdeleteCode = "";
	private ArrayList ratingdataList = null ;
	
	public String getRatingParameter() {
		return ratingParameter;
	}
	public void setRatingParameter(String ratingParameter) {
		this.ratingParameter = ratingParameter;
	}
	public String getRatingType() {
		return ratingType;
	}
	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}
	public String getOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(String onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	public String getFlagShow() {
		return flagShow;
	}
	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
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
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	public String getRatingId() {
		return ratingId;
	}
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public ArrayList getRatingdataList() {
		return ratingdataList;
	}
	public void setRatingdataList(ArrayList ratingdataList) {
		this.ratingdataList = ratingdataList;
	}
	
	
}
