package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;


import org.paradyne.lib.BeanBase;
/**Created By
 * Nilesh Dhandare
 * **/



public class CityGrade extends BeanBase {


	private String gradeName="";
	private String gradeCities="";


	private String onLoadFlag="false";
	private String flagShow="false";

	private String hiddencode  = "" ;

	
	private String myPage  = "" ;
	private String modeLength  = "" ;
	private String totalNoOfRecords  = "" ;
	
	private String gradeId="";
	
	private String hdeleteCode = "";
	private ArrayList gradeList = null ;
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeCities() {
		return gradeCities;
	}
	public void setGradeCities(String gradeCities) {
		this.gradeCities = gradeCities;
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
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public ArrayList getGradeList() {
		return gradeList;
	}
	public void setGradeList(ArrayList gradeList) {
		this.gradeList = gradeList;
	}
	
	
	
	


}
