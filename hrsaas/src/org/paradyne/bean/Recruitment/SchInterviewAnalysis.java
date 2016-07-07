package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

public class SchInterviewAnalysis extends BeanBase{

	private String frmDate;
	private String toDate;
	private String reqname;
	private String reqCode; 
	private String recruiter;
	private String  reportType; 
	private String reqname1; 
	private String  selectedReq=""; 
	private String  position="";
	private String  positionId=""; 
	private String dateFilter="";
	private String intDateFilter="";//for interview Date Filter
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
	private String aId=""; 
	private String divId=""; 
	private String backFlag="false"; 
	private String exportAll="";
	private String settingNameDup=""; 
	private String hiringMgrName="";
	private String hiringMgrCode=""; 
	private String hidSelectedReqName=""; 
	private String intFrmDate="";//for interview From date
	private String intToDate="";//for interview To Date
	 
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
	private String  radioHiringMgr="";
	private String  radioPosition="";
	private String radioStatus =""; 
	
	
	
	// for Advance Filter  
	private String  schStatusCom="";
	private String  interviewerCode="";
	private String  candidateCode="";
	private String  candidateName="";
	private String  interviewerName="";
	
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
 
	// for check box  
	private String  candiCodeChk="";
	private String  interviewTimeChk="";
	private String  recruiterChk="";
	private String  roundTypeChk="";
	private String  interviewVenueChk="";   
	private String  schStatusChk="";
	private String  interviewDateChk="";
	 private String  interviewerChk="";  
	private String  contactNumChk ="";
	private String  emailIdChk="";
	private String interviewStatusChk="";//for interview status check
	private String interviewStatus="";//for interview Status 
	private String schDateChk="";//for interview schedule date check
	private String schDate="";//for interview schedule date
	private String  candiCodeChkFlag="false";
	private String  contactNumChkFlag="false";
	private String  emailIdChkFlag="false";
	private String  interviewTimeChkFlag="false";
	private String  recruiterChkFlag="false";
	private String  roundTypeChkFlag="false";
	private String  interviewVenueChkFlag="false";
	private String  schStatusChkFlag="false";
	private String  interviewDateChkFlag="false";
	private String  interviewerChkFlag="false";
	private String  interviewStatusChkFlag="false";//for interview Status check flag
	private String schDateChkFlag="false";//for interview date check flag
	
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
	private String  noDataCandi="false";
	private String  noDataReq="false";  
	
	
	// for view the requistion information
	private String  vReqName="";
	private String  vReqDate="";
	private String  vBranch="";
	private String  vDepartment="";
	private String  vDivision="";   
	private String  vPosition="";
	private String  vHiringMgr ="";
	private String  vAppStatus="";
	private String  vReqStatus="";  
	private String  vReqCode=""; 
	private String  myPageCandi="";
	ArrayList candidateList = new ArrayList();
	
	// for iterator 
	 
	private String  ivCandidateName="";
	private String  ivEmailId="";
	private String  ivContactNum="";
	private String  ivRoundType="";
	private String  ivInterviewDate="";   
	private String  ivInterviewTime="";
	private String  ivVenue ="";
	private String  ivInterviewver="";
	private String  ivRecruiter="";   
	private String  ivSchStatus="";   
	private String candidateFlag="false"; 
	private String screenFlag="false"; 
	
	// for SUMMARY
	private String  sReqName="";
	private String  sSchedule="";
	private String  sReschedule="";
	private String  sConducted="";
	private String  sCancel="";
	private String  noDataSummFlag="false"; 
	private String  dataSummLength=""; 
	ArrayList summaryList = new ArrayList(); 
	
	
	public ArrayList getSummaryList() {
		return summaryList;
	}
	public void setSummaryList(ArrayList summaryList) {
		this.summaryList = summaryList;
	}
	public ArrayList getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(ArrayList candidateList) {
		this.candidateList = candidateList;
	}
	public String getVReqName() {
		return vReqName;
	}
	public void setVReqName(String reqName) {
		vReqName = reqName;
	}
	public String getVReqDate() {
		return vReqDate;
	}
	public void setVReqDate(String reqDate) {
		vReqDate = reqDate;
	}
	public String getVBranch() {
		return vBranch;
	}
	public void setVBranch(String branch) {
		vBranch = branch;
	}
	public String getVDepartment() {
		return vDepartment;
	}
	public void setVDepartment(String department) {
		vDepartment = department;
	}
	public String getVDivision() {
		return vDivision;
	}
	public void setVDivision(String division) {
		vDivision = division;
	}
	public String getVPosition() {
		return vPosition;
	}
	public void setVPosition(String position) {
		vPosition = position;
	}
	public String getVHiringMgr() {
		return vHiringMgr;
	}
	public void setVHiringMgr(String hiringMgr) {
		vHiringMgr = hiringMgr;
	}
	public String getVAppStatus() {
		return vAppStatus;
	}
	public void setVAppStatus(String appStatus) {
		vAppStatus = appStatus;
	}
	public String getVReqStatus() {
		return vReqStatus;
	}
	public void setVReqStatus(String reqStatus) {
		vReqStatus = reqStatus;
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
 
 
 
	public String getRecruiterChkFlag() {
		return recruiterChkFlag;
	}
	public void setRecruiterChkFlag(String recruiterChkFlag) {
		this.recruiterChkFlag = recruiterChkFlag;
	}
	 
	 
	public String getCandiCodeChk() {
		return candiCodeChk;
	}
	public void setCandiCodeChk(String candiCodeChk) {
		this.candiCodeChk = candiCodeChk;
	}
	public String getInterviewTimeChk() {
		return interviewTimeChk;
	}
	public void setInterviewTimeChk(String interviewTimeChk) {
		this.interviewTimeChk = interviewTimeChk;
	}
	public String getRecruiterChk() {
		return recruiterChk;
	}
	public void setRecruiterChk(String recruiterChk) {
		this.recruiterChk = recruiterChk;
	}
	public String getRoundTypeChk() {
		return roundTypeChk;
	}
	public void setRoundTypeChk(String roundTypeChk) {
		this.roundTypeChk = roundTypeChk;
	}
	public String getInterviewVenueChk() {
		return interviewVenueChk;
	}
	public void setInterviewVenueChk(String interviewVenueChk) {
		this.interviewVenueChk = interviewVenueChk;
	}
	public String getSchStatusChk() {
		return schStatusChk;
	}
	public void setSchStatusChk(String schStatusChk) {
		this.schStatusChk = schStatusChk;
	}
	public String getInterviewDateChk() {
		return interviewDateChk;
	}
	public void setInterviewDateChk(String interviewDateChk) {
		this.interviewDateChk = interviewDateChk;
	}
	 public String getInterviewerChk() {
		return interviewerChk;
	}
	public void setInterviewerChk(String interviewerChk) {
		this.interviewerChk = interviewerChk;
	} 
	public String getCandiCodeChkFlag() {
		return candiCodeChkFlag;
	}
	public void setCandiCodeChkFlag(String candiCodeChkFlag) {
		this.candiCodeChkFlag = candiCodeChkFlag;
	}
	public String getInterviewTimeChkFlag() {
		return interviewTimeChkFlag;
	}
	public void setInterviewTimeChkFlag(String interviewTimeChkFlag) {
		this.interviewTimeChkFlag = interviewTimeChkFlag;
	}
	public String getRoundTypeChkFlag() {
		return roundTypeChkFlag;
	}
	public void setRoundTypeChkFlag(String roundTypeChkFlag) {
		this.roundTypeChkFlag = roundTypeChkFlag;
	}
	public String getInterviewVenueChkFlag() {
		return interviewVenueChkFlag;
	}
	public void setInterviewVenueChkFlag(String interviewVenueChkFlag) {
		this.interviewVenueChkFlag = interviewVenueChkFlag;
	}
	public String getSchStatusChkFlag() {
		return schStatusChkFlag;
	}
	public void setSchStatusChkFlag(String schStatusChkFlag) {
		this.schStatusChkFlag = schStatusChkFlag;
	}
	public String getInterviewDateChkFlag() {
		return interviewDateChkFlag;
	}
	public void setInterviewDateChkFlag(String interviewDateChkFlag) {
		this.interviewDateChkFlag = interviewDateChkFlag;
	}
	public String getInterviewerChkFlag() {
		return interviewerChkFlag;
	}
	public void setInterviewerChkFlag(String interviewerChkFlag) {
		this.interviewerChkFlag = interviewerChkFlag;
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
	 
	public String getRadioHiringMgr() {
		return radioHiringMgr;
	}
	public void setRadioHiringMgr(String radioHiringMgr) {
		this.radioHiringMgr = radioHiringMgr;
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
	public String getHiringMgrName() {
		return hiringMgrName;
	}
	public void setHiringMgrName(String hiringMgrName) {
		this.hiringMgrName = hiringMgrName;
	}
	public String getHiringMgrCode() {
		return hiringMgrCode;
	}
	public void setHiringMgrCode(String hiringMgrCode) {
		this.hiringMgrCode = hiringMgrCode;
	}
	public String getContactNumChk() {
		return contactNumChk;
	}
	public void setContactNumChk(String contactNumChk) {
		this.contactNumChk = contactNumChk;
	}
	public String getEmailIdChk() {
		return emailIdChk;
	}
	public void setEmailIdChk(String emailIdChk) {
		this.emailIdChk = emailIdChk;
	}
	public String getContactNumChkFlag() {
		return contactNumChkFlag;
	}
	public void setContactNumChkFlag(String contactNumChkFlag) {
		this.contactNumChkFlag = contactNumChkFlag;
	}
	public String getEmailIdChkFlag() {
		return emailIdChkFlag;
	}
	public void setEmailIdChkFlag(String emailIdChkFlag) {
		this.emailIdChkFlag = emailIdChkFlag;
	}
	public String getMyPageCandi() {
		return myPageCandi;
	}
	public void setMyPageCandi(String myPageCandi) {
		this.myPageCandi = myPageCandi;
	}
	public String getIvCandidateName() {
		return ivCandidateName;
	}
	public void setIvCandidateName(String ivCandidateName) {
		this.ivCandidateName = ivCandidateName;
	}
	public String getIvEmailId() {
		return ivEmailId;
	}
	public void setIvEmailId(String ivEmailId) {
		this.ivEmailId = ivEmailId;
	}
	public String getIvContactNum() {
		return ivContactNum;
	}
	public void setIvContactNum(String ivContactNum) {
		this.ivContactNum = ivContactNum;
	}
	public String getIvRoundType() {
		return ivRoundType;
	}
	public void setIvRoundType(String ivRoundType) {
		this.ivRoundType = ivRoundType;
	}
	public String getIvInterviewDate() {
		return ivInterviewDate;
	}
	public void setIvInterviewDate(String ivInterviewDate) {
		this.ivInterviewDate = ivInterviewDate;
	}
	public String getIvInterviewTime() {
		return ivInterviewTime;
	}
	public void setIvInterviewTime(String ivInterviewTime) {
		this.ivInterviewTime = ivInterviewTime;
	}
	public String getIvVenue() {
		return ivVenue;
	}
	public void setIvVenue(String ivVenue) {
		this.ivVenue = ivVenue;
	}
	public String getIvInterviewver() {
		return ivInterviewver;
	}
	public void setIvInterviewver(String ivInterviewver) {
		this.ivInterviewver = ivInterviewver;
	}
	public String getIvRecruiter() {
		return ivRecruiter;
	}
	public void setIvRecruiter(String ivRecruiter) {
		this.ivRecruiter = ivRecruiter;
	}
	public String getIvSchStatus() {
		return ivSchStatus;
	}
	public void setIvSchStatus(String ivSchStatus) {
		this.ivSchStatus = ivSchStatus;
	}
	public String getNoDataCandi() {
		return noDataCandi;
	}
	public void setNoDataCandi(String noDataCandi) {
		this.noDataCandi = noDataCandi;
	}
	public String getVReqCode() {
		return vReqCode;
	}
	public void setVReqCode(String reqCode) {
		vReqCode = reqCode;
	}
	public String getCandidateFlag() {
		return candidateFlag;
	}
	public void setCandidateFlag(String candidateFlag) {
		this.candidateFlag = candidateFlag;
	}
	public String getScreenFlag() {
		return screenFlag;
	}
	public void setScreenFlag(String screenFlag) {
		this.screenFlag = screenFlag;
	}
	public String getSchStatusCom() {
		return schStatusCom;
	}
	public void setSchStatusCom(String schStatusCom) {
		this.schStatusCom = schStatusCom;
	}
	public String getInterviewerCode() {
		return interviewerCode;
	}
	public void setInterviewerCode(String interviewerCode) {
		this.interviewerCode = interviewerCode;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getInterviewerName() {
		return interviewerName;
	}
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}
	public String getSReqName() {
		return sReqName;
	}
	public void setSReqName(String reqName) {
		sReqName = reqName;
	}
	public String getSSchedule() {
		return sSchedule;
	}
	public void setSSchedule(String schedule) {
		sSchedule = schedule;
	}
	public String getSReschedule() {
		return sReschedule;
	}
	public void setSReschedule(String reschedule) {
		sReschedule = reschedule;
	}
	public String getSConducted() {
		return sConducted;
	}
	public void setSConducted(String conducted) {
		sConducted = conducted;
	}
	public String getSCancel() {
		return sCancel;
	}
	public void setSCancel(String cancel) {
		sCancel = cancel;
	}
	public String getNoDataSummFlag() {
		return noDataSummFlag;
	}
	public void setNoDataSummFlag(String noDataSummFlag) {
		this.noDataSummFlag = noDataSummFlag;
	}
	public String getDataSummLength() {
		return dataSummLength;
	}
	public void setDataSummLength(String dataSummLength) {
		this.dataSummLength = dataSummLength;
	}
	public String getHidSelectedReqName() {
		return hidSelectedReqName;
	}
	public void setHidSelectedReqName(String hidSelectedReqName) {
		this.hidSelectedReqName = hidSelectedReqName;
	}
	/**
	 * @return interviewStatusChk
	 */
	public String getInterviewStatusChk() {
		return interviewStatusChk;
	}
	/**
	 * @param interviewStatusChk
	 * the interviewStatusChk to set
	 */
	public void setInterviewStatusChk(String interviewStatusChk) {
		this.interviewStatusChk = interviewStatusChk;
	}
	/**
	 * @return intDateFilter
	 */
	public String getIntDateFilter() {
		return intDateFilter;
	}
	/**
	 * @param intDateFilter
	 * the intDateFilter to set
	 */
	public void setIntDateFilter(String intDateFilter) {
		this.intDateFilter = intDateFilter;
	}
	/**
	 * @return interviewStatusChkFlag
	 */
	public String getInterviewStatusChkFlag() {
		return interviewStatusChkFlag;
	}
	/**
	 * @param interviewStatusChkFlag
	 * the interviewStatusChkFlag to set
	 */
	public void setInterviewStatusChkFlag(String interviewStatusChkFlag) {
		this.interviewStatusChkFlag = interviewStatusChkFlag;
	}
	/**
	 * @return interviewStatus
	 */
	public String getInterviewStatus() {
		return interviewStatus;
	}
	/**
	 * @param interviewStatus
	 * the interviewStatus to set
	 */
	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}
	/**
	 * @return intFrmDate
	 */
	public String getIntFrmDate() {
		return intFrmDate;
	}
	/**
	 * @param intFrmDate
	 * the intFrmDate to set
	 */
	public void setIntFrmDate(String intFrmDate) {
		this.intFrmDate = intFrmDate;
	}
	/**
	 * @return intToDate
	 */
	public String getIntToDate() {
		return intToDate;
	}
	/**
	 * @param intToDate
	 * the intToDate to set
	 */
	public void setIntToDate(String intToDate) {
		this.intToDate = intToDate;
	}
	public String getSchDateChk() {
		return schDateChk;
	}
	public void setSchDateChk(String schDateChk) {
		this.schDateChk = schDateChk;
	}
	public String getSchDate() {
		return schDate;
	}
	public void setSchDate(String schDate) {
		this.schDate = schDate;
	}
	public String getSchDateChkFlag() {
		return schDateChkFlag;
	}
	public void setSchDateChkFlag(String schDateChkFlag) {
		this.schDateChkFlag = schDateChkFlag;
	}
	 
	
	
}
