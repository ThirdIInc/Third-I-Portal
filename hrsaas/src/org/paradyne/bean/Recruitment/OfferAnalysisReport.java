package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

public class OfferAnalysisReport extends BeanBase{

	private String frmDate;
	private String toDate;
	private String reqname;
	private String reqCode;
	private String offerDate="";
	
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
	private String  itRecriuterName="";
	private String myPage="";
	private String show="";
	private String dataLength="";
	private String selectedReqName="";
	private String selectedReqFlag=""; 
	private String editReqFlag="false"; 
	private String checkedCount="0"; 
	private String aId=""; 
	private String divId=""; 
	private String backFlag="false"; 
	private String exportAll="";
	private String settingNameDup="";
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
	private String  radioReq=""; 
	private String  radioRecr="";
	private String  radioHirMng="";
	private String  radioPosition="";
	private String radioStatus =""; 
	
	// for Advance Filter 
	private String  noVacAdvCom="";
	private String  offerDueAdvCom=""; 
	private String  offerIssueAdvCom="";
	private String  offerAcceptAdvCom="";
	private String  offerRejectAdvCom="";
	private String  offerCancelAdvCom="";  
	private String  noVacAdvTxt="";
	private String  offerDueAdvTxt=""; 
	private String  offerIssueAdvTxt="";
	private String  offerAcceptAdvTxt="";
	private String  offerRejectAdvTxt="";
	private String  offerCancelAdvTxt=""; 
	
	
	// for view on jsp 
	private String  ivReqCode="";
	private String  ivPostion=""; 
	private String  ivReqDate="";
	private String  ivNoVac="";
	private String  ivOfferDue="";
	private String  ivOfferIssue="";  
	private String  ivOfferAccept="";
	private String  ivOfferReject="";
	private String  ivOfferCancel="";
	private String  ivOfferDate="";
	 
	
	// for check box  
	private String  reqCodeChk="";
	private String  postionChk="";  
	private String  offerAccptedChk="";
	private String  offerRejectedChk="";
	private String  novacChk="";  
	private String  offerCancelChk="";
	private String  offerIssueChk=""; 
	private String  offerDueChk="";
	private String  reqDateChk="";
	
	
	private String  reqCodeChkFlag="false";
	private String  postionChkFlag="false"; 
	private String  offerAccptedChkFlag="false";
	private String  offerRejectedChkFlag="false";
	private String  novacChkFlag="false";
	private String  offerCancelChkFlag="false";
	private String  offerIssueChkFlag="false";
	private String  offerDueChkFlag="false";
	private String  reqDateChkFlag="false";
	 
	private String  searchSetting=""; 	
	ArrayList openViewList = new ArrayList();
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
 
	 
	public String getNoVacAdvCom() {
		return noVacAdvCom;
	}
	public void setNoVacAdvCom(String noVacAdvCom) {
		this.noVacAdvCom = noVacAdvCom;
	}
	public String getOfferIssueAdvCom() {
		return offerIssueAdvCom;
	}
	public void setOfferIssueAdvCom(String offerIssueAdvCom) {
		this.offerIssueAdvCom = offerIssueAdvCom;
	}
	public String getOfferAcceptAdvCom() {
		return offerAcceptAdvCom;
	}
	public void setOfferAcceptAdvCom(String offerAcceptAdvCom) {
		this.offerAcceptAdvCom = offerAcceptAdvCom;
	}
	public String getOfferRejectAdvCom() {
		return offerRejectAdvCom;
	}
	public void setOfferRejectAdvCom(String offerRejectAdvCom) {
		this.offerRejectAdvCom = offerRejectAdvCom;
	}
	public String getOfferCancelAdvCom() {
		return offerCancelAdvCom;
	}
	public void setOfferCancelAdvCom(String offerCancelAdvCom) {
		this.offerCancelAdvCom = offerCancelAdvCom;
	}
	public String getNoVacAdvTxt() {
		return noVacAdvTxt;
	}
	public void setNoVacAdvTxt(String noVacAdvTxt) {
		this.noVacAdvTxt = noVacAdvTxt;
	}
	public String getOfferIssueAdvTxt() {
		return offerIssueAdvTxt;
	}
	public void setOfferIssueAdvTxt(String offerIssueAdvTxt) {
		this.offerIssueAdvTxt = offerIssueAdvTxt;
	}
	public String getOfferAcceptAdvTxt() {
		return offerAcceptAdvTxt;
	}
	public void setOfferAcceptAdvTxt(String offerAcceptAdvTxt) {
		this.offerAcceptAdvTxt = offerAcceptAdvTxt;
	}
	public String getOfferRejectAdvTxt() {
		return offerRejectAdvTxt;
	}
	public void setOfferRejectAdvTxt(String offerRejectAdvTxt) {
		this.offerRejectAdvTxt = offerRejectAdvTxt;
	}
	public String getOfferCancelAdvTxt() {
		return offerCancelAdvTxt;
	}
	public void setOfferCancelAdvTxt(String offerCancelAdvTxt) {
		this.offerCancelAdvTxt = offerCancelAdvTxt;
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
	 
	 
	public ArrayList getOpenViewList() {
		return openViewList;
	}
	public void setOpenViewList(ArrayList openViewList) {
		this.openViewList = openViewList;
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
	public String getPostionChk() {
		return postionChk;
	}
	public void setPostionChk(String postionChk) {
		this.postionChk = postionChk;
	}
 
 
	public String getReqCodeChkFlag() {
		return reqCodeChkFlag;
	}
	public void setReqCodeChkFlag(String reqCodeChkFlag) {
		this.reqCodeChkFlag = reqCodeChkFlag;
	}
	public String getPostionChkFlag() {
		return postionChkFlag;
	}
	public void setPostionChkFlag(String postionChkFlag) {
		this.postionChkFlag = postionChkFlag;
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
	public String getSettingNameDup() {
		return settingNameDup;
	}
	public void setSettingNameDup(String settingNameDup) {
		this.settingNameDup = settingNameDup;
	}
	public String getItRecriuterName() {
		return itRecriuterName;
	}
	public void setItRecriuterName(String itRecriuterName) {
		this.itRecriuterName = itRecriuterName;
	}
	public LinkedHashMap getMap() {
		return map;
	}
	public void setMap(LinkedHashMap map) {
		this.map = map;
	}
 
 
	public String getRadioReq() {
		return radioReq;
	}
	public void setRadioReq(String radioReq) {
		this.radioReq = radioReq;
	}
	public String getRadioRecr() {
		return radioRecr;
	}
	public void setRadioRecr(String radioRecr) {
		this.radioRecr = radioRecr;
	}
	public String getRadioHirMng() {
		return radioHirMng;
	}
	public void setRadioHirMng(String radioHirMng) {
		this.radioHirMng = radioHirMng;
	}
	public String getRadioPosition() {
		return radioPosition;
	}
	public void setRadioPosition(String radioPosition) {
		this.radioPosition = radioPosition;
	}
	public String getRadioStatus() {
		return radioStatus;
	}
	public void setRadioStatus(String radioStatus) {
		this.radioStatus = radioStatus;
	}
	 
 
	public String getIvNoVac() {
		return ivNoVac;
	}
	public void setIvNoVac(String ivNoVac) {
		this.ivNoVac = ivNoVac;
	}
	public String getIvOfferDue() {
		return ivOfferDue;
	}
	public void setIvOfferDue(String ivOfferDue) {
		this.ivOfferDue = ivOfferDue;
	}
	public String getIvOfferIssue() {
		return ivOfferIssue;
	}
	public void setIvOfferIssue(String ivOfferIssue) {
		this.ivOfferIssue = ivOfferIssue;
	}
	public String getIvOfferAccept() {
		return ivOfferAccept;
	}
	public void setIvOfferAccept(String ivOfferAccept) {
		this.ivOfferAccept = ivOfferAccept;
	}
	public String getIvOfferReject() {
		return ivOfferReject;
	}
	public void setIvOfferReject(String ivOfferReject) {
		this.ivOfferReject = ivOfferReject;
	}
	public String getIvOfferCancel() {
		return ivOfferCancel;
	}
	public void setIvOfferCancel(String ivOfferCancel) {
		this.ivOfferCancel = ivOfferCancel;
	}
	public String getOfferAccptedChk() {
		return offerAccptedChk;
	}
	public void setOfferAccptedChk(String offerAccptedChk) {
		this.offerAccptedChk = offerAccptedChk;
	}
	public String getOfferRejectedChk() {
		return offerRejectedChk;
	}
	public void setOfferRejectedChk(String offerRejectedChk) {
		this.offerRejectedChk = offerRejectedChk;
	}
	public String getNovacChk() {
		return novacChk;
	}
	public void setNovacChk(String novacChk) {
		this.novacChk = novacChk;
	}
	public String getOfferCancelChk() {
		return offerCancelChk;
	}
	public void setOfferCancelChk(String offerCancelChk) {
		this.offerCancelChk = offerCancelChk;
	}
	public String getOfferIssueChk() {
		return offerIssueChk;
	}
	public void setOfferIssueChk(String offerIssueChk) {
		this.offerIssueChk = offerIssueChk;
	}
	public String getOfferAccptedChkFlag() {
		return offerAccptedChkFlag;
	}
	public void setOfferAccptedChkFlag(String offerAccptedChkFlag) {
		this.offerAccptedChkFlag = offerAccptedChkFlag;
	}
	public String getOfferRejectedChkFlag() {
		return offerRejectedChkFlag;
	}
	public void setOfferRejectedChkFlag(String offerRejectedChkFlag) {
		this.offerRejectedChkFlag = offerRejectedChkFlag;
	}
	public String getNovacChkFlag() {
		return novacChkFlag;
	}
	public void setNovacChkFlag(String novacChkFlag) {
		this.novacChkFlag = novacChkFlag;
	}
	public String getOfferCancelChkFlag() {
		return offerCancelChkFlag;
	}
	public void setOfferCancelChkFlag(String offerCancelChkFlag) {
		this.offerCancelChkFlag = offerCancelChkFlag;
	}
	public String getOfferIssueChkFlag() {
		return offerIssueChkFlag;
	}
	public void setOfferIssueChkFlag(String offerIssueChkFlag) {
		this.offerIssueChkFlag = offerIssueChkFlag;
	}
	public String getOfferDueAdvCom() {
		return offerDueAdvCom;
	}
	public void setOfferDueAdvCom(String offerDueAdvCom) {
		this.offerDueAdvCom = offerDueAdvCom;
	}
	public String getOfferDueAdvTxt() {
		return offerDueAdvTxt;
	}
	public void setOfferDueAdvTxt(String offerDueAdvTxt) {
		this.offerDueAdvTxt = offerDueAdvTxt;
	}
	public String getOfferDueChk() {
		return offerDueChk;
	}
	public void setOfferDueChk(String offerDueChk) {
		this.offerDueChk = offerDueChk;
	}
	public String getOfferDueChkFlag() {
		return offerDueChkFlag;
	}
	public void setOfferDueChkFlag(String offerDueChkFlag) {
		this.offerDueChkFlag = offerDueChkFlag;
	}
	public String getIvReqDate() {
		return ivReqDate;
	}
	public void setIvReqDate(String ivReqDate) {
		this.ivReqDate = ivReqDate;
	}
	public String getReqDateChk() {
		return reqDateChk;
	}
	public void setReqDateChk(String reqDateChk) {
		this.reqDateChk = reqDateChk;
	}
	public String getReqDateChkFlag() {
		return reqDateChkFlag;
	}
	public void setReqDateChkFlag(String reqDateChkFlag) {
		this.reqDateChkFlag = reqDateChkFlag;
	}
	public String getHidSelectedReqName() {
		return hidSelectedReqName;
	}
	public void setHidSelectedReqName(String hidSelectedReqName) {
		this.hidSelectedReqName = hidSelectedReqName;
	}
	public String getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}
	public String getIvOfferDate() {
		return ivOfferDate;
	}
	public void setIvOfferDate(String ivOfferDate) {
		this.ivOfferDate = ivOfferDate;
	} 
	
	
}
