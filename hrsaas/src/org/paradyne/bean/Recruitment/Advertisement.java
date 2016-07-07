package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class Advertisement extends BeanBase {

	private String reqName =""; 
	private String postionName =""; 
	private String noOfVaccany =""; 
	private String reqCode ="";
	private String postionId =""; 
	private String advertiseCode =""; 
	private boolean editFlag =false;
	private boolean searchFlag =false;
	private String myPage="";
	
	//for details iterator
	private String advertiseMode =""; 
	private String advertiseModeName =""; 
	private String advertiseStartDate =""; 
	private String advertiseEndDate ="";
	private String advertiseDetails =""; 
	private String advertiseCost =""; 
	private String advertiseResponse ="";
	private String advertiseModeText="";
	
	//for main  iterator
	private String itReqName =""; 
	private String itPosition =""; 
	private String itVaccany =""; 
	private String itCost ="";
	private String itResponse =""; 
	private String itReqCode =""; 
	private String itPositionId ="";
	private String itAdvertiseCode =""; 
	private String show="";
	private String totalRecords=""; 
	private String  noData ="false";
	ArrayList advertiseList = new ArrayList();  
	ArrayList advertiseMainList = new ArrayList();
 
	
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getPostionName() {
		return postionName;
	}
	public void setPostionName(String postionName) {
		this.postionName = postionName;
	}
	public String getNoOfVaccany() {
		return noOfVaccany;
	}
	public void setNoOfVaccany(String noOfVaccany) {
		this.noOfVaccany = noOfVaccany;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	public String getPostionId() {
		return postionId;
	}
	public void setPostionId(String postionId) {
		this.postionId = postionId;
	}
	public String getAdvertiseMode() {
		return advertiseMode;
	}
	public void setAdvertiseMode(String advertiseMode) {
		this.advertiseMode = advertiseMode;
	}
	public String getAdvertiseModeName() {
		return advertiseModeName;
	}
	public void setAdvertiseModeName(String advertiseModeName) {
		this.advertiseModeName = advertiseModeName;
	}
	public String getAdvertiseStartDate() {
		return advertiseStartDate;
	}
	public void setAdvertiseStartDate(String advertiseStartDate) {
		this.advertiseStartDate = advertiseStartDate;
	}
	public String getAdvertiseEndDate() {
		return advertiseEndDate;
	}
	public void setAdvertiseEndDate(String advertiseEndDate) {
		this.advertiseEndDate = advertiseEndDate;
	}
	public String getAdvertiseDetails() {
		return advertiseDetails;
	}
	public void setAdvertiseDetails(String advertiseDetails) {
		this.advertiseDetails = advertiseDetails;
	}
	public String getAdvertiseCost() {
		return advertiseCost;
	}
	public void setAdvertiseCost(String advertiseCost) {
		this.advertiseCost = advertiseCost;
	}
	public String getAdvertiseResponse() {
		return advertiseResponse;
	}
	public void setAdvertiseResponse(String advertiseResponse) {
		this.advertiseResponse = advertiseResponse;
	}
	public ArrayList getAdvertiseList() {
		return advertiseList;
	}
	public void setAdvertiseList(ArrayList advertiseList) {
		this.advertiseList = advertiseList;
	}
	public String getItReqName() {
		return itReqName;
	}
	public void setItReqName(String itReqName) {
		this.itReqName = itReqName;
	}
	public String getItPosition() {
		return itPosition;
	}
	public void setItPosition(String itPosition) {
		this.itPosition = itPosition;
	}
	public String getItVaccany() {
		return itVaccany;
	}
	public void setItVaccany(String itVaccany) {
		this.itVaccany = itVaccany;
	}
	public String getItCost() {
		return itCost;
	}
	public void setItCost(String itCost) {
		this.itCost = itCost;
	}
	public String getItResponse() {
		return itResponse;
	}
	public void setItResponse(String itResponse) {
		this.itResponse = itResponse;
	}
	public String getItReqCode() {
		return itReqCode;
	}
	public void setItReqCode(String itReqCode) {
		this.itReqCode = itReqCode;
	}
	public String getItPositionId() {
		return itPositionId;
	}
	public void setItPositionId(String itPositionId) {
		this.itPositionId = itPositionId;
	}
	public String getItAdvertiseCode() {
		return itAdvertiseCode;
	}
	public void setItAdvertiseCode(String itAdvertiseCode) {
		this.itAdvertiseCode = itAdvertiseCode;
	}
	public ArrayList getAdvertiseMainList() {
		return advertiseMainList;
	}
	public void setAdvertiseMainList(ArrayList advertiseMainList) {
		this.advertiseMainList = advertiseMainList;
	}
	public String getAdvertiseCode() {
		return advertiseCode;
	}
	public void setAdvertiseCode(String advertiseCode) {
		this.advertiseCode = advertiseCode;
	}
	public boolean isEditFlag() {
		return editFlag;
	}
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	public String getAdvertiseModeText() {
		return advertiseModeText;
	}
	public void setAdvertiseModeText(String advertiseModeText) {
		this.advertiseModeText = advertiseModeText;
	}
	public boolean isSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(boolean searchFlag) {
		this.searchFlag = searchFlag;
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
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
 
}
