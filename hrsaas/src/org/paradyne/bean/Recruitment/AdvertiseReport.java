package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

public class AdvertiseReport extends BeanBase{

	private String frmDate;
	private String toDate;
	private String reqname;
	private String reqCode;
 
	private String  reportType; 
	private String reqname1; 
	private String  selectedReq=""; 
	private String  position="";
	private String  positionId=""; 
	private String dateFilter="";
	private String  itReqName="";
	private String  itPosition="";
	private String  itReqDate="";
	private String  itStatus=""; 
	private String  itReqCode="";
	private String myPage="";
	private String show="";
	private String dataLength="";
	private String selectedReqName="";
	private String selectedReqFlag=""; 
	private String editReqFlag="false"; 
	private String checkedCount="0"; 
	private String modeOfAdvertise=""; 
	private String aId=""; 
	private String divId=""; 
	private String backFlag="false"; 
	private String hidSelectedReqName="";
	
	
	ArrayList dispList = new ArrayList();
	
	// for sorting Filter  
	private String sortBy="";
	private String  sortByOrder="";
	private String  thenBy1="";
	private String  thenByOrder1="";
	private String  thenByOrder2=""; 
	private String  thenBy2="";
	private String reqStatus="";
	private String  settingName="";
	
	// for Advance Filter 
	private String  numOfVacAdvCom="";
	private String  numOfVacAdvTxt="";
	private String  onlineRespAdvCom="";
	private String  onlineRespAdvTxt="";
	private String  costAdvCom="";  
	private String  costAdvTxt=""; 
	
	// for view on jsp 
	private String  ivReqCode="";
	private String  ivPostion="";
	private String  ivNoVac="";
	private String  ivModeOfAdv="";
	private String  ivAgencyName="";  
	private String  ivAdvDate="";
	private String  ivAdvCost="";
	private String  ivOnlineResp="";
	 
	
	// for check box     	  	 
	private String  reqCodeChk="";
	private String  numOfVacChk="";
	private String  modeOfAdvChk="";
	private String  agencuNameChk="";
	private String  advDateChk="";  
	private String  advCostChk="";
	private String  onlineResChk=""; 
	private String  positionChk=""; 
	
	
	private String  reqCodeChkFlag="false";
	private String  numOfVacChkFlag="false";
	private String  modeOfAdvChkFlag="false";
	private String  agencuNameChkFlag="false";
	private String  advDateChkFlag="false";
	private String  advCostChkFlag="false";
	private String  onlineResChkFlag="false"; 
	private String  positionChkFlag="false"; 
	
	private String  searchSetting=""; 	
	ArrayList advertiseViewList = new ArrayList();
	LinkedHashMap map = new LinkedHashMap();
	
	// for radio button 
	private String  hidReportView="checked"; 	
	private String  hidReportRadio=""; 
	private String  sortByAsc="checked"; 	
	private String  sortByDsc=""; 
	private String  thenByOrder1Asc="checked"; 	
	private String  thenByOrder1Dsc=""; 
	private String  thenByOrder2Asc="checked"; 	
	private String  thenByOrder2Dsc="";
	private String  noData="false";
	private String  noDataReq="false";  
	private String  exportAll="";  
	private String  settingNameDup="";     
	
	private String  radioStatus="";
	private String  radioReq="";  
	private String  radioPosition="";   
	  
	public String getSettingNameDup() {
		return settingNameDup;
	}
	public void setSettingNameDup(String settingNameDup) {
		this.settingNameDup = settingNameDup;
	}
	public String getNoDataReq() {
		return noDataReq;
	}
	public void setNoDataReq(String noDataReq) {
		this.noDataReq = noDataReq;
	}
	public String getHidReportView() {
		return hidReportView;
	}
	public void setHidReportView(String hidReportView) {
		this.hidReportView = hidReportView;
	}
 
	public ArrayList getDispList() {
		return dispList;
	}
	public void setDispList(ArrayList dispList) {
		this.dispList = dispList;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getReqname() {
		return reqname;
	}
	public void setReqname(String reqname) {
		this.reqname = reqname;
	}
	public String getReqCode() {
		return reqCode;
	}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
	 
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
 
	public String getReqname1() {
		return reqname1;
	}
	public void setReqname1(String reqname1) {
		this.reqname1 = reqname1;
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
	public String getItReqDate() {
		return itReqDate;
	}
	public void setItReqDate(String itReqDate) {
		this.itReqDate = itReqDate;
	}
	public String getItStatus() {
		return itStatus;
	}
	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}
	public String getItReqCode() {
		return itReqCode;
	}
	public void setItReqCode(String itReqCode) {
		this.itReqCode = itReqCode;
	}
	public String getSelectedReq() {
		return selectedReq;
	}
	public void setSelectedReq(String selectedReq) {
		this.selectedReq = selectedReq;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getDateFilter() {
		return dateFilter;
	}
	public void setDateFilter(String dateFilter) {
		this.dateFilter = dateFilter;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortByOrder() {
		return sortByOrder;
	}
	public void setSortByOrder(String sortByOrder) {
		this.sortByOrder = sortByOrder;
	}
	public String getThenBy1() {
		return thenBy1;
	}
	public void setThenBy1(String thenBy1) {
		this.thenBy1 = thenBy1;
	}
	public String getThenByOrder1() {
		return thenByOrder1;
	}
	public void setThenByOrder1(String thenByOrder1) {
		this.thenByOrder1 = thenByOrder1;
	}
	public String getThenByOrder2() {
		return thenByOrder2;
	}
	public void setThenByOrder2(String thenByOrder2) {
		this.thenByOrder2 = thenByOrder2;
	}
	public String getThenBy2() {
		return thenBy2;
	}
	public void setThenBy2(String thenBy2) {
		this.thenBy2 = thenBy2;
	}
	 
	public String getNumOfVacAdvCom() {
		return numOfVacAdvCom;
	}
	public void setNumOfVacAdvCom(String numOfVacAdvCom) {
		this.numOfVacAdvCom = numOfVacAdvCom;
	}
	public String getNumOfVacAdvTxt() {
		return numOfVacAdvTxt;
	}
	public void setNumOfVacAdvTxt(String numOfVacAdvTxt) {
		this.numOfVacAdvTxt = numOfVacAdvTxt;
	}
	public String getOnlineRespAdvCom() {
		return onlineRespAdvCom;
	}
	public void setOnlineRespAdvCom(String onlineRespAdvCom) {
		this.onlineRespAdvCom = onlineRespAdvCom;
	}
	public String getOnlineRespAdvTxt() {
		return onlineRespAdvTxt;
	}
	public void setOnlineRespAdvTxt(String onlineRespAdvTxt) {
		this.onlineRespAdvTxt = onlineRespAdvTxt;
	}
	public String getCostAdvCom() {
		return costAdvCom;
	}
	public void setCostAdvCom(String costAdvCom) {
		this.costAdvCom = costAdvCom;
	}
	public String getCostAdvTxt() {
		return costAdvTxt;
	}
	public void setCostAdvTxt(String costAdvTxt) {
		this.costAdvTxt = costAdvTxt;
	}
	public String getIvReqCode() {
		return ivReqCode;
	}
	public void setIvReqCode(String ivReqCode) {
		this.ivReqCode = ivReqCode;
	}
	public String getIvPostion() {
		return ivPostion;
	}
	public void setIvPostion(String ivPostion) {
		this.ivPostion = ivPostion;
	}  
	public String getIvNoVac() {
		return ivNoVac;
	}
	public void setIvNoVac(String ivNoVac) {
		this.ivNoVac = ivNoVac;
	}
	public String getIvModeOfAdv() {
		return ivModeOfAdv;
	}
	public void setIvModeOfAdv(String ivModeOfAdv) {
		this.ivModeOfAdv = ivModeOfAdv;
	}
	public String getIvAgencyName() {
		return ivAgencyName;
	}
	public void setIvAgencyName(String ivAgencyName) {
		this.ivAgencyName = ivAgencyName;
	}
	public String getIvAdvDate() {
		return ivAdvDate;
	}
	public void setIvAdvDate(String ivAdvDate) {
		this.ivAdvDate = ivAdvDate;
	}
	public String getIvAdvCost() {
		return ivAdvCost;
	}
	public void setIvAdvCost(String ivAdvCost) {
		this.ivAdvCost = ivAdvCost;
	}
	public String getIvOnlineResp() {
		return ivOnlineResp;
	}
	public void setIvOnlineResp(String ivOnlineResp) {
		this.ivOnlineResp = ivOnlineResp;
	}
	public ArrayList getAdvertiseViewList() {
		return advertiseViewList;
	}
	public void setAdvertiseViewList(ArrayList advertiseViewList) {
		this.advertiseViewList = advertiseViewList;
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
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getSelectedReqName() {
		return selectedReqName;
	}
	public void setSelectedReqName(String selectedReqName) {
		this.selectedReqName = selectedReqName;
	}

	public String getSelectedReqFlag() {
		return selectedReqFlag;
	}
	public void setSelectedReqFlag(String selectedReqFlag) {
		this.selectedReqFlag = selectedReqFlag;
	}
	public String getEditReqFlag() {
		return editReqFlag;
	}
	public void setEditReqFlag(String editReqFlag) {
		this.editReqFlag = editReqFlag;
	}
	public String getReqCodeChk() {
		return reqCodeChk;
	}
	public void setReqCodeChk(String reqCodeChk) {
		this.reqCodeChk = reqCodeChk;
	} 
	 
	public String getNumOfVacChk() {
		return numOfVacChk;
	}
	public void setNumOfVacChk(String numOfVacChk) {
		this.numOfVacChk = numOfVacChk;
	}
	public String getModeOfAdvChk() {
		return modeOfAdvChk;
	}
	public void setModeOfAdvChk(String modeOfAdvChk) {
		this.modeOfAdvChk = modeOfAdvChk;
	}
	public String getAgencuNameChk() {
		return agencuNameChk;
	}
	public void setAgencuNameChk(String agencuNameChk) {
		this.agencuNameChk = agencuNameChk;
	}
	public String getAdvDateChk() {
		return advDateChk;
	}
	public void setAdvDateChk(String advDateChk) {
		this.advDateChk = advDateChk;
	}
	public String getAdvCostChk() {
		return advCostChk;
	}
	public void setAdvCostChk(String advCostChk) {
		this.advCostChk = advCostChk;
	}
	 
	public String getReqCodeChkFlag() {
		return reqCodeChkFlag;
	}
	public void setReqCodeChkFlag(String reqCodeChkFlag) {
		this.reqCodeChkFlag = reqCodeChkFlag;
	}
	public String getNumOfVacChkFlag() {
		return numOfVacChkFlag;
	}
	public void setNumOfVacChkFlag(String numOfVacChkFlag) {
		this.numOfVacChkFlag = numOfVacChkFlag;
	}
	public String getModeOfAdvChkFlag() {
		return modeOfAdvChkFlag;
	}
	public void setModeOfAdvChkFlag(String modeOfAdvChkFlag) {
		this.modeOfAdvChkFlag = modeOfAdvChkFlag;
	}
	public String getAgencuNameChkFlag() {
		return agencuNameChkFlag;
	}
	public void setAgencuNameChkFlag(String agencuNameChkFlag) {
		this.agencuNameChkFlag = agencuNameChkFlag;
	}
	public String getAdvDateChkFlag() {
		return advDateChkFlag;
	}
	public void setAdvDateChkFlag(String advDateChkFlag) {
		this.advDateChkFlag = advDateChkFlag;
	}
	public String getAdvCostChkFlag() {
		return advCostChkFlag;
	}
	public void setAdvCostChkFlag(String advCostChkFlag) {
		this.advCostChkFlag = advCostChkFlag;
	}
	public String getOnlineResChkFlag() {
		return onlineResChkFlag;
	}
	public void setOnlineResChkFlag(String onlineResChkFlag) {
		this.onlineResChkFlag = onlineResChkFlag;
	}
	public String getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(String checkedCount) {
		this.checkedCount = checkedCount;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getSearchSetting() {
		return searchSetting;
	}
	public void setSearchSetting(String searchSetting) {
		this.searchSetting = searchSetting;
	}
	 
	public String getHidReportRadio() {
		return hidReportRadio;
	}
	public void setHidReportRadio(String hidReportRadio) {
		this.hidReportRadio = hidReportRadio;
	}
	public String getSortByAsc() {
		return sortByAsc;
	}
	public void setSortByAsc(String sortByAsc) {
		this.sortByAsc = sortByAsc;
	}
	public String getSortByDsc() {
		return sortByDsc;
	}
	public void setSortByDsc(String sortByDsc) {
		this.sortByDsc = sortByDsc;
	}
	public String getThenByOrder1Asc() {
		return thenByOrder1Asc;
	}
	public void setThenByOrder1Asc(String thenByOrder1Asc) {
		this.thenByOrder1Asc = thenByOrder1Asc;
	}
	public String getThenByOrder1Dsc() {
		return thenByOrder1Dsc;
	}
	public void setThenByOrder1Dsc(String thenByOrder1Dsc) {
		this.thenByOrder1Dsc = thenByOrder1Dsc;
	}
	public String getThenByOrder2Asc() {
		return thenByOrder2Asc;
	}
	public void setThenByOrder2Asc(String thenByOrder2Asc) {
		this.thenByOrder2Asc = thenByOrder2Asc;
	}
	public String getThenByOrder2Dsc() {
		return thenByOrder2Dsc;
	}
	public void setThenByOrder2Dsc(String thenByOrder2Dsc) {
		this.thenByOrder2Dsc = thenByOrder2Dsc;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getModeOfAdvertise() {
		return modeOfAdvertise;
	}
	public void setModeOfAdvertise(String modeOfAdvertise) {
		this.modeOfAdvertise = modeOfAdvertise;
	}
	public String getPositionChk() {
		return positionChk;
	}
	public void setPositionChk(String positionChk) {
		this.positionChk = positionChk;
	}
	public String getPositionChkFlag() {
		return positionChkFlag;
	}
	public void setPositionChkFlag(String positionChkFlag) {
		this.positionChkFlag = positionChkFlag;
	}
	public String getOnlineResChk() {
		return onlineResChk;
	}
	public void setOnlineResChk(String onlineResChk) {
		this.onlineResChk = onlineResChk;
	}
	public String getAId() {
		return aId;
	}
	public void setAId(String id) {
		aId = id;
	}
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getExportAll() {
		return exportAll;
	}
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}
	public LinkedHashMap getMap() {
		return map;
	}
	public void setMap(LinkedHashMap map) {
		this.map = map;
	}
	public String getRadioStatus() {
		return radioStatus;
	}
	public void setRadioStatus(String radioStatus) {
		this.radioStatus = radioStatus;
	}
	public String getRadioReq() {
		return radioReq;
	}
	public void setRadioReq(String radioReq) {
		this.radioReq = radioReq;
	}
	public String getRadioPosition() {
		return radioPosition;
	}
	public void setRadioPosition(String radioPosition) {
		this.radioPosition = radioPosition;
	}
	public String getHidSelectedReqName() {
		return hidSelectedReqName;
	}
	public void setHidSelectedReqName(String hidSelectedReqName) {
		this.hidSelectedReqName = hidSelectedReqName;
	} 
	
	
}
