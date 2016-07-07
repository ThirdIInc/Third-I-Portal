/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0517
 *
 */
public class InterviewEvaluationBean extends BeanBase {
	
	private String reqname = "";
	private String reqCode = "";
	private String reqDate = "";
	private String reqPosition = "";
	private String interviewerName = "";
	private String interviewerToken = "";
	private String interviewerId = "";
	private String candidateName = "";
	private String candidateCode = "";
	private String position = "";
	private String positionId = "";
	private String  reportType;
	private String frmDate = "";
	private String toDate = "";
	private String interviewDate = "";
	
	
	private String  settingName="";
	private String checkedCount="0"; 
	private String exportAll="";
	private String aId=""; 
	private String divId=""; 
	private String backFlag="false"; 
	private String hidSelectedReqName=""; 
	
	//for paging
	private String myPage="";
	private String  noData="false";
	
	//for requistion date on filter tab
	private String dateFilter="";
	
	
	//for multiple select requisition
	ArrayList dispList = new ArrayList();
	private String  noDataReq="false";
	private String editReqFlag="false"; 
	private String  selectedReq="";
	private String selectedReqName="";
	private String selectedReqFlag="";
	private String dataLength="";
	private String  itReqName="";
	private String  itPosition="";
	private String  itReqDate="";
	private String  itStatus="";
	private String  itReqCode="";
	
	// for sorting Filter  
	private String  sortBy="";
	private String  sortByOrder="";
	private String  thenBy1="";
	private String  thenByOrder1="";
	private String  thenByOrder2=""; 
	private String  thenBy2="";
	private String  reqStatus="";
	
	
	
	
	
	//for common
	private String common = "";
	private String radioStatus = "";
	
	//for filter option
	private String radioReq = "";
	private String radioInterviewer = "";
	private String radioCandidate = "";
	private String radioPosition = "";
	
	// for radio button 
	private String  hidReportView="checked";
	private String  hidReportRadio="";
	private String  sortByAsc="checked"; 
	private String  sortByDsc=""; 
	private String  thenByOrder1Asc="checked"; 	
	private String  thenByOrder1Dsc=""; 
	private String  thenByOrder2Asc="checked"; 	
	private String  thenByOrder2Dsc="";
	
	// for check box  
	private String  reqCodeChk="";
	private String  interviewerChk="";
	private String  candidateNameChk="";
	private String  postionChk="";
	private String  statusChk = "";
	private String  qualificationDtlChk = "";
	private String  technicalSkillChk = "";
	private String  communicationLevelChk = "";
	private String  managementSkillChk = "";
	private String  personalityChk = "";
	private String  learningSkillChk = "";
	private String  relevantExpChk = "";
	private String  suitabilityAbiltiyChk = "";
	private String  roundOfIntChk = "";
	private String evalScoreChk = "";
	private String evalPercantageChk = "";
	
	private String  reqCodeChkFlag="";
	private String  interviewerChkFlag="";
	private String  candidateNameChkFlag="";
	private String  postionChkFlag="";
	private String  statusChkFlag = "";
	private String  qualificationDtlChkFlag = "";
	private String  technicalSkillChkFlag = "";
	private String  communicationLevelChkFlag = "";
	private String  managementSkillChkFlag = "";
	private String  personalityChkFlag = "";
	private String  learningSkillChkFlag = "";
	private String  relevantExpChkFlag = "";
	private String  suitabilityAbiltiyChkFlag = "";
	private String  roundOfIntChkFlag = "";
	private String 	evalScoreChkFlag = "";
	private String  evalPercentageChkFlag = "";
	
	// for view on jsp 
	private String ivReqCode = "";
	private String ivReqName = "";
	private String ivPosition = "";
	private String ivInterviewer = "";
	private String ivCandidateName = "";
	private String ivRoundOfInterview = "";
	private String ivQualification = "";
	private String ivTechSkill = "";
	private String ivCommunication = "";
	private String ivManagement = "";
	private String ivPersonality = "";
	private String ivLearning = "";
	private String ivRelevant = "";
	private String ivSuitability = "";
	private String ivEvalPercentage = "";
	private String ivEvalScore = "";
	private String ivStatus = "";
	
	//for save
	private String  searchSetting=""; 	
	private String settingNameDup="";
	private String recruiterChk = "";
	private String recruiterChkFlag = "";
	private String ivRecruiter = "";
	
	LinkedHashMap map = new LinkedHashMap();
	ArrayList openViewList = new ArrayList();
	
	public ArrayList getOpenViewList() {
		return openViewList;
	}
	public void setOpenViewList(ArrayList openViewList) {
		this.openViewList = openViewList;
	}
	public LinkedHashMap getMap() {
		return map;
	}
	public void setMap(LinkedHashMap map) {
		this.map = map;
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
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getInterviewerName() {
		return interviewerName;
	}
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}
	public String getInterviewerId() {
		return interviewerId;
	}
	public void setInterviewerId(String interviewerId) {
		this.interviewerId = interviewerId;
	}
	public String getRadioReq() {
		return radioReq;
	}
	public void setRadioReq(String radioReq) {
		this.radioReq = radioReq;
	}
	public String getRadioInterviewer() {
		return radioInterviewer;
	}
	public void setRadioInterviewer(String radioInterviewer) {
		this.radioInterviewer = radioInterviewer;
	}
	public String getRadioStatus() {
		return radioStatus;
	}
	public void setRadioStatus(String radioStatus) {
		this.radioStatus = radioStatus;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getRadioCandidate() {
		return radioCandidate;
	}
	public void setRadioCandidate(String radioCandidate) {
		this.radioCandidate = radioCandidate;
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
	public String getRadioPosition() {
		return radioPosition;
	}
	public void setRadioPosition(String radioPosition) {
		this.radioPosition = radioPosition;
	}
	public String getDateFilter() {
		return dateFilter;
	}
	public void setDateFilter(String dateFilter) {
		this.dateFilter = dateFilter;
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
	public String getSelectedReqName() {
		return selectedReqName;
	}
	public void setSelectedReqName(String selectedReqName) {
		this.selectedReqName = selectedReqName;
	}
	public ArrayList getDispList() {
		return dispList;
	}
	public void setDispList(ArrayList dispList) {
		this.dispList = dispList;
	}
	public String getNoDataReq() {
		return noDataReq;
	}
	public void setNoDataReq(String noDataReq) {
		this.noDataReq = noDataReq;
	}
	public String getEditReqFlag() {
		return editReqFlag;
	}
	public void setEditReqFlag(String editReqFlag) {
		this.editReqFlag = editReqFlag;
	}
	public String getSelectedReq() {
		return selectedReq;
	}
	public void setSelectedReq(String selectedReq) {
		this.selectedReq = selectedReq;
	}
	public String getSelectedReqFlag() {
		return selectedReqFlag;
	}
	public void setSelectedReqFlag(String selectedReqFlag) {
		this.selectedReqFlag = selectedReqFlag;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
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
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getHidReportView() {
		return hidReportView;
	}
	public void setHidReportView(String hidReportView) {
		this.hidReportView = hidReportView;
	}
	public String getHidReportRadio() {
		return hidReportRadio;
	}
	public void setHidReportRadio(String hidReportRadio) {
		this.hidReportRadio = hidReportRadio;
	}
	public String getSettingName() {
		return settingName;
	}
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}
	public String getReqCodeChk() {
		return reqCodeChk;
	}
	public void setReqCodeChk(String reqCodeChk) {
		this.reqCodeChk = reqCodeChk;
	}
	public String getInterviewerChk() {
		return interviewerChk;
	}
	public void setInterviewerChk(String interviewerChk) {
		this.interviewerChk = interviewerChk;
	}
	public String getCandidateNameChk() {
		return candidateNameChk;
	}
	public void setCandidateNameChk(String candidateNameChk) {
		this.candidateNameChk = candidateNameChk;
	}
	public String getPostionChk() {
		return postionChk;
	}
	public void setPostionChk(String postionChk) {
		this.postionChk = postionChk;
	}
	public String getStatusChk() {
		return statusChk;
	}
	public void setStatusChk(String statusChk) {
		this.statusChk = statusChk;
	}
	public String getQualificationDtlChk() {
		return qualificationDtlChk;
	}
	public void setQualificationDtlChk(String qualificationDtlChk) {
		this.qualificationDtlChk = qualificationDtlChk;
	}
	public String getTechnicalSkillChk() {
		return technicalSkillChk;
	}
	public void setTechnicalSkillChk(String technicalSkillChk) {
		this.technicalSkillChk = technicalSkillChk;
	}
	public String getCommunicationLevelChk() {
		return communicationLevelChk;
	}
	public void setCommunicationLevelChk(String communicationLevelChk) {
		this.communicationLevelChk = communicationLevelChk;
	}
	public String getManagementSkillChk() {
		return managementSkillChk;
	}
	public void setManagementSkillChk(String managementSkillChk) {
		this.managementSkillChk = managementSkillChk;
	}
	public String getPersonalityChk() {
		return personalityChk;
	}
	public void setPersonalityChk(String personalityChk) {
		this.personalityChk = personalityChk;
	}
	public String getLearningSkillChk() {
		return learningSkillChk;
	}
	public void setLearningSkillChk(String learningSkillChk) {
		this.learningSkillChk = learningSkillChk;
	}
	public String getRelevantExpChk() {
		return relevantExpChk;
	}
	public void setRelevantExpChk(String relevantExpChk) {
		this.relevantExpChk = relevantExpChk;
	}
	public String getSuitabilityAbiltiyChk() {
		return suitabilityAbiltiyChk;
	}
	public void setSuitabilityAbiltiyChk(String suitabilityAbiltiyChk) {
		this.suitabilityAbiltiyChk = suitabilityAbiltiyChk;
	}
	public String getRoundOfIntChk() {
		return roundOfIntChk;
	}
	public void setRoundOfIntChk(String roundOfIntChk) {
		this.roundOfIntChk = roundOfIntChk;
	}
	public String getReqCodeChkFlag() {
		return reqCodeChkFlag;
	}
	public void setReqCodeChkFlag(String reqCodeChkFlag) {
		this.reqCodeChkFlag = reqCodeChkFlag;
	}
	public String getInterviewerChkFlag() {
		return interviewerChkFlag;
	}
	public void setInterviewerChkFlag(String interviewerChkFlag) {
		this.interviewerChkFlag = interviewerChkFlag;
	}
	public String getCandidateNameChkFlag() {
		return candidateNameChkFlag;
	}
	public void setCandidateNameChkFlag(String candidateNameChkFlag) {
		this.candidateNameChkFlag = candidateNameChkFlag;
	}
	public String getPostionChkFlag() {
		return postionChkFlag;
	}
	public void setPostionChkFlag(String postionChkFlag) {
		this.postionChkFlag = postionChkFlag;
	}
	public String getStatusChkFlag() {
		return statusChkFlag;
	}
	public void setStatusChkFlag(String statusChkFlag) {
		this.statusChkFlag = statusChkFlag;
	}
	public String getQualificationDtlChkFlag() {
		return qualificationDtlChkFlag;
	}
	public void setQualificationDtlChkFlag(String qualificationDtlChkFlag) {
		this.qualificationDtlChkFlag = qualificationDtlChkFlag;
	}
	public String getTechnicalSkillChkFlag() {
		return technicalSkillChkFlag;
	}
	public void setTechnicalSkillChkFlag(String technicalSkillChkFlag) {
		this.technicalSkillChkFlag = technicalSkillChkFlag;
	}
	public String getCommunicationLevelChkFlag() {
		return communicationLevelChkFlag;
	}
	public void setCommunicationLevelChkFlag(String communicationLevelChkFlag) {
		this.communicationLevelChkFlag = communicationLevelChkFlag;
	}
	public String getManagementSkillChkFlag() {
		return managementSkillChkFlag;
	}
	public void setManagementSkillChkFlag(String managementSkillChkFlag) {
		this.managementSkillChkFlag = managementSkillChkFlag;
	}
	public String getPersonalityChkFlag() {
		return personalityChkFlag;
	}
	public void setPersonalityChkFlag(String personalityChkFlag) {
		this.personalityChkFlag = personalityChkFlag;
	}
	public String getLearningSkillChkFlag() {
		return learningSkillChkFlag;
	}
	public void setLearningSkillChkFlag(String learningSkillChkFlag) {
		this.learningSkillChkFlag = learningSkillChkFlag;
	}
	public String getRelevantExpChkFlag() {
		return relevantExpChkFlag;
	}
	public void setRelevantExpChkFlag(String relevantExpChkFlag) {
		this.relevantExpChkFlag = relevantExpChkFlag;
	}
	public String getSuitabilityAbiltiyChkFlag() {
		return suitabilityAbiltiyChkFlag;
	}
	public void setSuitabilityAbiltiyChkFlag(String suitabilityAbiltiyChkFlag) {
		this.suitabilityAbiltiyChkFlag = suitabilityAbiltiyChkFlag;
	}
	public String getRoundOfIntChkFlag() {
		return roundOfIntChkFlag;
	}
	public void setRoundOfIntChkFlag(String roundOfIntChkFlag) {
		this.roundOfIntChkFlag = roundOfIntChkFlag;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getEvalScoreChk() {
		return evalScoreChk;
	}
	public void setEvalScoreChk(String evalScoreChk) {
		this.evalScoreChk = evalScoreChk;
	}
	public String getEvalPercantageChk() {
		return evalPercantageChk;
	}
	public void setEvalPercantageChk(String evalPercantageChk) {
		this.evalPercantageChk = evalPercantageChk;
	}
	public String getEvalScoreChkFlag() {
		return evalScoreChkFlag;
	}
	public void setEvalScoreChkFlag(String evalScoreChkFlag) {
		this.evalScoreChkFlag = evalScoreChkFlag;
	}
	public String getEvalPercentageChkFlag() {
		return evalPercentageChkFlag;
	}
	public void setEvalPercentageChkFlag(String evalPercentageChkFlag) {
		this.evalPercentageChkFlag = evalPercentageChkFlag;
	}
	public String getIvReqCode() {
		return ivReqCode;
	}
	public void setIvReqCode(String ivReqCode) {
		this.ivReqCode = ivReqCode;
	}
	public String getIvPosition() {
		return ivPosition;
	}
	public void setIvPosition(String ivPosition) {
		this.ivPosition = ivPosition;
	}
	public String getIvInterviewer() {
		return ivInterviewer;
	}
	public void setIvInterviewer(String ivInterviewer) {
		this.ivInterviewer = ivInterviewer;
	}
	public String getIvCandidateName() {
		return ivCandidateName;
	}
	public void setIvCandidateName(String ivCandidateName) {
		this.ivCandidateName = ivCandidateName;
	}
	public String getIvRoundOfInterview() {
		return ivRoundOfInterview;
	}
	public void setIvRoundOfInterview(String ivRoundOfInterview) {
		this.ivRoundOfInterview = ivRoundOfInterview;
	}
	public String getIvQualification() {
		return ivQualification;
	}
	public void setIvQualification(String ivQualification) {
		this.ivQualification = ivQualification;
	}
	public String getIvTechSkill() {
		return ivTechSkill;
	}
	public void setIvTechSkill(String ivTechSkill) {
		this.ivTechSkill = ivTechSkill;
	}
	public String getIvCommunication() {
		return ivCommunication;
	}
	public void setIvCommunication(String ivCommunication) {
		this.ivCommunication = ivCommunication;
	}
	public String getIvManagement() {
		return ivManagement;
	}
	public void setIvManagement(String ivManagement) {
		this.ivManagement = ivManagement;
	}
	public String getIvPersonality() {
		return ivPersonality;
	}
	public void setIvPersonality(String ivPersonality) {
		this.ivPersonality = ivPersonality;
	}
	public String getIvLearning() {
		return ivLearning;
	}
	public void setIvLearning(String ivLearning) {
		this.ivLearning = ivLearning;
	}
	public String getIvRelevant() {
		return ivRelevant;
	}
	public void setIvRelevant(String ivRelevant) {
		this.ivRelevant = ivRelevant;
	}
	public String getIvSuitability() {
		return ivSuitability;
	}
	public void setIvSuitability(String ivSuitability) {
		this.ivSuitability = ivSuitability;
	}
	public String getIvEvalPercentage() {
		return ivEvalPercentage;
	}
	public void setIvEvalPercentage(String ivEvalPercentage) {
		this.ivEvalPercentage = ivEvalPercentage;
	}
	public String getIvEvalScore() {
		return ivEvalScore;
	}
	public void setIvEvalScore(String ivEvalScore) {
		this.ivEvalScore = ivEvalScore;
	}
	public String getIvStatus() {
		return ivStatus;
	}
	public void setIvStatus(String ivStatus) {
		this.ivStatus = ivStatus;
	}
	public String getIvReqName() {
		return ivReqName;
	}
	public void setIvReqName(String ivReqName) {
		this.ivReqName = ivReqName;
	}
	public String getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(String checkedCount) {
		this.checkedCount = checkedCount;
	}
	public String getExportAll() {
		return exportAll;
	}
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
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
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
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
	public String getSearchSetting() {
		return searchSetting;
	}
	public void setSearchSetting(String searchSetting) {
		this.searchSetting = searchSetting;
	}
	public String getSettingNameDup() {
		return settingNameDup;
	}
	public void setSettingNameDup(String settingNameDup) {
		this.settingNameDup = settingNameDup;
	}
	public String getHidSelectedReqName() {
		return hidSelectedReqName;
	}
	public void setHidSelectedReqName(String hidSelectedReqName) {
		this.hidSelectedReqName = hidSelectedReqName;
	}
	public String getRecruiterChk() {
		return recruiterChk;
	}
	public void setRecruiterChk(String recruiterChk) {
		this.recruiterChk = recruiterChk;
	}
	public String getRecruiterChkFlag() {
		return recruiterChkFlag;
	}
	public void setRecruiterChkFlag(String recruiterChkFlag) {
		this.recruiterChkFlag = recruiterChkFlag;
	}
	public String getIvRecruiter() {
		return ivRecruiter;
	}
	public void setIvRecruiter(String ivRecruiter) {
		this.ivRecruiter = ivRecruiter;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getReqPosition() {
		return reqPosition;
	}
	public void setReqPosition(String reqPosition) {
		this.reqPosition = reqPosition;
	}
	public String getInterviewerToken() {
		return interviewerToken;
	}
	public void setInterviewerToken(String interviewerToken) {
		this.interviewerToken = interviewerToken;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	
	
}
