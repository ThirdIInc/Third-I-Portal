package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

public class OpenVacReport extends BeanBase{

	private String frmDate;
	private String toDate;
	private String reqname;
	private String reqCode;
	private String recruiterName;
	private String recruiterCode;
	private String recruiter;
	private String  reportType; 
	private String reqname1;
	private String  hiringManagerId=""; 
	private String  hiringManagerName="";  
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
	private String  totalVacAdvCom="";
	private String  asgnVacAdvCom="";
	private String  openVacAdvCom="";
	private String  closeVacAdvCom="";
	private String  requirdeByDateCom="";  
	private String  totalVacAdvTxt="";
	private String  asgnVacAdvTxt="";
	private String  openVacAdvTxt="";
	private String  closeVacAdvTxt="";
	private String  requirdeByFromDateTxt=""; 
	private String  requirdeByToDateTxt=""; 
	
	
	// for view on jsp 
	private String  ivReqCode="";
	private String  ivPostion="";
	private String  ivReqDate="";
	private String  ivHiringManager="";
	private String  ivTotalVac="";  
	private String  ivRecruiterName="";
	private String  ivRequiredDate="";
	private String  ivAssgnVac="";
	private String  ivOpenVac="";
	private String  ivCloseVac="";   
	private String  ivClosedDate="";
	private String  ivOverDueDay="";  
	private String  ivApprovarName="";
	// for check box  
	private String  reqCodeChk="";
	private String  postionChk="";
	private String  dateChk="";
	private String  hiringMangerChk="";
	private String  approvarChk="";
	private String  totalVacChk="";  
	private String  recruiterChk="";
	private String  requiredDateChk="";
	private String  vacAssignChk="";
	private String  openVacChk="";
	private String  closedvacChk="";    
	private String  closedDateChk="";
	private String  overDueDayChk="";   
	
	private String  reqCodeChkFlag="false";
	private String  postionChkFlag="false";
	private String  dateChkFlag="false";
	private String  hiringMangerChkFlag="false";
	private String  totalVacChkFlag="false";
	private String  recruiterChkFlag="false";
	private String  requiredDateChkFlag="false";
	private String  vacAssignChkFlag="false";
	private String  openVacChkFlag="false";
	private String  closedvacChkFlag="false"; 
	private String  closedDateChkFlag="false"; 
	private String  overDueDayChkFlag="false"; 
	private String approvarChkFlag="false";
	
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
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	public String getRecruiterCode() {
		return recruiterCode;
	}
	public void setRecruiterCode(String recruiterCode) {
		this.recruiterCode = recruiterCode;
	}
	public String getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
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
	public String getHiringManagerId() {
		return hiringManagerId;
	}
	public void setHiringManagerId(String hiringManagerId) {
		this.hiringManagerId = hiringManagerId;
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
	public String getTotalVacAdvCom() {
		return totalVacAdvCom;
	}
	public void setTotalVacAdvCom(String totalVacAdvCom) {
		this.totalVacAdvCom = totalVacAdvCom;
	}
	public String getAsgnVacAdvCom() {
		return asgnVacAdvCom;
	}
	public void setAsgnVacAdvCom(String asgnVacAdvCom) {
		this.asgnVacAdvCom = asgnVacAdvCom;
	}
	public String getOpenVacAdvCom() {
		return openVacAdvCom;
	}
	public void setOpenVacAdvCom(String openVacAdvCom) {
		this.openVacAdvCom = openVacAdvCom;
	}
	public String getCloseVacAdvCom() {
		return closeVacAdvCom;
	}
	public void setCloseVacAdvCom(String closeVacAdvCom) {
		this.closeVacAdvCom = closeVacAdvCom;
	}
 
 
	public String getRequirdeByDateCom() {
		return requirdeByDateCom;
	}
	public void setRequirdeByDateCom(String requirdeByDateCom) {
		this.requirdeByDateCom = requirdeByDateCom;
	}
	public String getTotalVacAdvTxt() {
		return totalVacAdvTxt;
	}
	public void setTotalVacAdvTxt(String totalVacAdvTxt) {
		this.totalVacAdvTxt = totalVacAdvTxt;
	}
	public String getAsgnVacAdvTxt() {
		return asgnVacAdvTxt;
	}
	public void setAsgnVacAdvTxt(String asgnVacAdvTxt) {
		this.asgnVacAdvTxt = asgnVacAdvTxt;
	}
	public String getOpenVacAdvTxt() {
		return openVacAdvTxt;
	}
	public void setOpenVacAdvTxt(String openVacAdvTxt) {
		this.openVacAdvTxt = openVacAdvTxt;
	}
	public String getCloseVacAdvTxt() {
		return closeVacAdvTxt;
	}
	public void setCloseVacAdvTxt(String closeVacAdvTxt) {
		this.closeVacAdvTxt = closeVacAdvTxt;
	}
 
	public String getRequirdeByFromDateTxt() {
		return requirdeByFromDateTxt;
	}
	public void setRequirdeByFromDateTxt(String requirdeByFromDateTxt) {
		this.requirdeByFromDateTxt = requirdeByFromDateTxt;
	}
	
	public String getRequirdeByToDateTxt() {
		return requirdeByToDateTxt;
	}
	public void setRequirdeByToDateTxt(String requirdeByToDateTxt) {
		this.requirdeByToDateTxt = requirdeByToDateTxt;
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
	public String getIvReqDate() {
		return ivReqDate;
	}
	public void setIvReqDate(String ivReqDate) {
		this.ivReqDate = ivReqDate;
	}
	public String getIvHiringManager() {
		return ivHiringManager;
	}
	public void setIvHiringManager(String ivHiringManager) {
		this.ivHiringManager = ivHiringManager;
	}
	public String getIvTotalVac() {
		return ivTotalVac;
	}
	public void setIvTotalVac(String ivTotalVac) {
		this.ivTotalVac = ivTotalVac;
	}
	public String getIvRecruiterName() {
		return ivRecruiterName;
	}
	public void setIvRecruiterName(String ivRecruiterName) {
		this.ivRecruiterName = ivRecruiterName;
	}
	public String getIvRequiredDate() {
		return ivRequiredDate;
	}
	public void setIvRequiredDate(String ivRequiredDate) {
		this.ivRequiredDate = ivRequiredDate;
	}
	public String getIvAssgnVac() {
		return ivAssgnVac;
	}
	public void setIvAssgnVac(String ivAssgnVac) {
		this.ivAssgnVac = ivAssgnVac;
	}
	public String getIvOpenVac() {
		return ivOpenVac;
	}
	public void setIvOpenVac(String ivOpenVac) {
		this.ivOpenVac = ivOpenVac;
	}
	public String getIvCloseVac() {
		return ivCloseVac;
	}
	public void setIvCloseVac(String ivCloseVac) {
		this.ivCloseVac = ivCloseVac;
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
	public String getDateChk() {
		return dateChk;
	}
	public void setDateChk(String dateChk) {
		this.dateChk = dateChk;
	}
	public String getHiringMangerChk() {
		return hiringMangerChk;
	}
	public void setHiringMangerChk(String hiringMangerChk) {
		this.hiringMangerChk = hiringMangerChk;
	}
	public String getTotalVacChk() {
		return totalVacChk;
	}
	public void setTotalVacChk(String totalVacChk) {
		this.totalVacChk = totalVacChk;
	}
	public String getRecruiterChk() {
		return recruiterChk;
	}
	public void setRecruiterChk(String recruiterChk) {
		this.recruiterChk = recruiterChk;
	}
	public String vacAssignChk() {
		return requiredDateChk;
	}
	public void setRequiredDateChk(String requiredDateChk) {
		this.requiredDateChk = requiredDateChk;
	}
	public String getVacAssignChk() {
		return vacAssignChk;
	}
	public void setVacAssignChk(String vacAssignChk) {
		this.vacAssignChk = vacAssignChk;
	}
	public String getOpenVacChk() {
		return openVacChk;
	}
	public void setOpenVacChk(String openVacChk) {
		this.openVacChk = openVacChk;
	}
	public String getClosedvacChk() {
		return closedvacChk;
	}
	public void setClosedvacChk(String closedvacChk) {
		this.closedvacChk = closedvacChk;
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
	public String getDateChkFlag() {
		return dateChkFlag;
	}
	public void setDateChkFlag(String dateChkFlag) {
		this.dateChkFlag = dateChkFlag;
	}
	public String getHiringMangerChkFlag() {
		return hiringMangerChkFlag;
	}
	public void setHiringMangerChkFlag(String hiringMangerChkFlag) {
		this.hiringMangerChkFlag = hiringMangerChkFlag;
	}
	public String getTotalVacChkFlag() {
		return totalVacChkFlag;
	}
	public void setTotalVacChkFlag(String totalVacChkFlag) {
		this.totalVacChkFlag = totalVacChkFlag;
	}
	public String getRecruiterChkFlag() {
		return recruiterChkFlag;
	}
	public void setRecruiterChkFlag(String recruiterChkFlag) {
		this.recruiterChkFlag = recruiterChkFlag;
	}
	public String getRequiredDateChkFlag() {
		return requiredDateChkFlag;
	}
	public void setRequiredDateChkFlag(String requiredDateChkFlag) {
		this.requiredDateChkFlag = requiredDateChkFlag;
	}
	public String getVacAssignChkFlag() {
		return vacAssignChkFlag;
	}
	public void setVacAssignChkFlag(String vacAssignChkFlag) {
		this.vacAssignChkFlag = vacAssignChkFlag;
	}
	public String getOpenVacChkFlag() {
		return openVacChkFlag;
	}
	public void setOpenVacChkFlag(String openVacChkFlag) {
		this.openVacChkFlag = openVacChkFlag;
	}
	public String getClosedvacChkFlag() {
		return closedvacChkFlag;
	}
	public void setClosedvacChkFlag(String closedvacChkFlag) {
		this.closedvacChkFlag = closedvacChkFlag;
	}
	public String getRequiredDateChk() {
		return requiredDateChk;
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
	public String getHiringManagerName() {
		return hiringManagerName;
	}
	public void setHiringManagerName(String hiringManagerName) {
		this.hiringManagerName = hiringManagerName;
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
	public String getClosedDateChk() {
		return closedDateChk;
	}
	public void setClosedDateChk(String closedDateChk) {
		this.closedDateChk = closedDateChk;
	}
	public String getOverDueDayChk() {
		return overDueDayChk;
	}
	public void setOverDueDayChk(String overDueDayChk) {
		this.overDueDayChk = overDueDayChk;
	}
	public String getClosedDateChkFlag() {
		return closedDateChkFlag;
	}
	public void setClosedDateChkFlag(String closedDateChkFlag) {
		this.closedDateChkFlag = closedDateChkFlag;
	}
	public String getOverDueDayChkFlag() {
		return overDueDayChkFlag;
	}
	public void setOverDueDayChkFlag(String overDueDayChkFlag) {
		this.overDueDayChkFlag = overDueDayChkFlag;
	}
	public String getIvClosedDate() {
		return ivClosedDate;
	}
	public void setIvClosedDate(String ivClosedDate) {
		this.ivClosedDate = ivClosedDate;
	}
	public String getIvOverDueDay() {
		return ivOverDueDay;
	}
	public void setIvOverDueDay(String ivOverDueDay) {
		this.ivOverDueDay = ivOverDueDay;
	}
	public String getHidSelectedReqName() {
		return hidSelectedReqName;
	}
	public void setHidSelectedReqName(String hidSelectedReqName) {
		this.hidSelectedReqName = hidSelectedReqName;
	}
	public String getApprovarChk() {
		return approvarChk;
	}
	public void setApprovarChk(String approvarChk) {
		this.approvarChk = approvarChk;
	}
	public String getApprovarChkFlag() {
		return approvarChkFlag;
	}
	public void setApprovarChkFlag(String approvarChkFlag) {
		this.approvarChkFlag = approvarChkFlag;
	}
	public String getIvApprovarName() {
		return ivApprovarName;
	}
	public void setIvApprovarName(String ivApprovarName) {
		this.ivApprovarName = ivApprovarName;
	} 
	
	
}
