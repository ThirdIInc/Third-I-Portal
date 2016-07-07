package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class InterviewAnalysis extends BeanBase{

	private String statusHd="false";
	private String scoreHd="false";		
	private String ratingHd="false";
	private String intRndTypeHd="false";
	private String intrviewerHd="false";
	private String CandidateHd="false";	
	private String fromTotRec="";
	private String toTotRec="";
	private String exportAll_bot="";
	private String exportAll="";
	private ArrayList interviewList=null;
	private String CandidateFlag="false";
	private String intRndTypeFlag="false";
	private String intrviewerFlag="false";
	private String scoreFlag="false";
	private String ratingFlag="false";
	private String statusFlag="false";
	private String makeOfferFlag="false";
	private String nextRoundFlag="false";
	private String percentageFlag="false";
	private String intStatusFlag="false";
	private String settingSearch="";
	private String candName="";
	private String candCode="";
	private String intrvCode="";	
	private String interviewerName	="";
	private String noData="";
	HashMap hashMap = new HashMap();
	private String settingCode ="";
	private String reqCode="";
	private String userEmpId ="";
	private String totalPage1="";
	private String intStatus="";
	private String interviewstatus="";
	private String intRound="";
	private String interviewRound="";
	private String aId="";
	private String divId="";
	private String sortByInt=""; 
	private String sortRadioInt ="";
	private String RadioOneInt ="";
	private String thenBYInt ="";
	private String thenRadioInt="";
	private String RadioTwoInt="";
	private String secondByInt="";
	private String thanRadioInt="";
	private String RadioThreeInt="";
	private String intRoundCheck="Y";
	private String statusCheck="Y";
	private String ratingCheck="Y";
	private String scoreCheck="Y";
	
	private String interviewerCheck="Y";
	private String candidateCheck="Y";
	private String suitablityCheck="Y";
	private String qualificationCheck="Y";	
	private String technicalCheck="Y";	
	private String communicationCheck="Y";
	private String mangmentCheck="Y";
	private String personalityCheck="Y";
	private String relevantCheck="Y";
	private String makeOffferCheck="Y";
	private String nextRoundCheck="Y";
	private String learningCheck="Y";
	private String intStatusCheck="Y";
	private String schdType="";
	private String type="";
	private String genRep="";
	private String onScrn=""; 
	private String seltype="";
	private String myPage="";
	private String hdPage="";
	private String candidateLength="";
	private String  statusInt="";
	private String settingName="";
	private String candidateName="";
	private String interviewer="";
	private String rating="";
	private String score="";
	private String status="";
	private Boolean myChkFlag=false;
	private Boolean buttonFlag=false;
	private String pageNoField="";
	private String backFlag="false";
	private String modeLength="false";
	
	private String qualificationHd="false";
	private String technicalHd		="false";
	private String communicationHd="false"; 
	private String managmentHd	="false";
	private String personalityHd="false";
	private String learningHd="false";
	private String relevantHd="false";
	private String percentageHd="false";
	private String suitablityHd="false";
	private String quailificationFlag="false";
	private String percentageCheck="false";
	private String Percent="";
	private String	technicalFlag="false";
	private String	communicationFlag="false";
	private String	managmentFlag="false";
	private String personalityFlag="false";
	private String learningFlag="false";
	private String relevantFlag="false";
	private String sutablityFlag="false";
	private String qualification="";
	private String technical="";
	private String negotiableFlag="false";
	private String percantageFlag="false";
	private String communication="";
	private String managment="";
	private String personality="";
	private String learning="";
	private String relevant="";
	private String suitable="";
	private String makeOfferHd="false";
	private String nextRoundHd="false";
	private String evalCode="";
	private String evalScore="";
	private String  forwardNext="";
	private String makeOff="";
	private String pecrcentageHd="false";
	private String percentage="";
	private String nextRound="";
	private String makeOffer="";
	
	private String NextR="";
	private String makeOffe="";
	private String Suitab="";
	private String Relev="";
	private String Learn="";
	private String Persn="";
	private String Mgm="";
	private String CMM="";
	private String TC="";
	private String QF="";
	public String getQualificationCheck() {
		return qualificationCheck;
	}

	public void setQualificationCheck(String qualificationCheck) {
		this.qualificationCheck = qualificationCheck;
	}

	public String getTechnicalCheck() {
		return technicalCheck;
	}

	public void setTechnicalCheck(String technicalCheck) {
		this.technicalCheck = technicalCheck;
	}

	public String getCommunicationCheck() {
		return communicationCheck;
	}

	public void setCommunicationCheck(String communicationCheck) {
		this.communicationCheck = communicationCheck;
	}

	public String getMangmentCheck() {
		return mangmentCheck;
	}

	public void setMangmentCheck(String mangmentCheck) {
		this.mangmentCheck = mangmentCheck;
	}

	public String getPersonalityCheck() {
		return personalityCheck;
	}

	public void setPersonalityCheck(String personalityCheck) {
		this.personalityCheck = personalityCheck;
	}

	public String getRelevantCheck() {
		return relevantCheck;
	}

	public void setRelevantCheck(String relevantCheck) {
		this.relevantCheck = relevantCheck;
	}

	public String getMakeOffferCheck() {
		return makeOffferCheck;
	}

	public void setMakeOffferCheck(String makeOffferCheck) {
		this.makeOffferCheck = makeOffferCheck;
	}

	public String getNextRoundCheck() {
		return nextRoundCheck;
	}

	public void setNextRoundCheck(String nextRoundCheck) {
		this.nextRoundCheck = nextRoundCheck;
	}

	public String getNextR() {
		return NextR;
	}

	public void setNextR(String nextR) {
		NextR = nextR;
	}

	public String getMakeOffe() {
		return makeOffe;
	}

	public void setMakeOffe(String makeOffe) {
		this.makeOffe = makeOffe;
	}

	public String getSuitab() {
		return Suitab;
	}

	public void setSuitab(String suitab) {
		Suitab = suitab;
	}

	public String getRelev() {
		return Relev;
	}

	public void setRelev(String relev) {
		Relev = relev;
	}

	public String getLearn() {
		return Learn;
	}

	public void setLearn(String learn) {
		Learn = learn;
	}

	public String getPersn() {
		return Persn;
	}

	public void setPersn(String persn) {
		Persn = persn;
	}

	public String getMgm() {
		return Mgm;
	}

	public void setMgm(String mgm) {
		Mgm = mgm;
	}

	public String getCMM() {
		return CMM;
	}

	public void setCMM(String cmm) {
		CMM = cmm;
	}

	public String getTC() {
		return TC;
	}

	public void setTC(String tc) {
		TC = tc;
	}

	public String getQF() {
		return QF;
	}

	public void setQF(String qf) {
		QF = qf;
	}

	public String getMakeOffer() {
		return makeOffer;
	}

	public void setMakeOffer(String makeOffer) {
		this.makeOffer = makeOffer;
	}

	public String getNextRound() {
		return nextRound;
	}

	public void setNextRound(String nextRound) {
		this.nextRound = nextRound;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getTechnical() {
		return technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public String getManagment() {
		return managment;
	}

	public void setManagment(String managment) {
		this.managment = managment;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getLearning() {
		return learning;
	}

	public void setLearning(String learning) {
		this.learning = learning;
	}

	public String getRelevant() {
		return relevant;
	}

	public void setRelevant(String relevant) {
		this.relevant = relevant;
	}

	public String getSuitable() {
		return suitable;
	}

	public void setSuitable(String suitable) {
		this.suitable = suitable;
	}

	public String getQuailificationFlag() {
		return quailificationFlag;
	}

	public void setQuailificationFlag(String quailificationFlag) {
		this.quailificationFlag = quailificationFlag;
	}

	public String getTechnicalFlag() {
		return technicalFlag;
	}

	public void setTechnicalFlag(String technicalFlag) {
		this.technicalFlag = technicalFlag;
	}

	public String getCommunicationFlag() {
		return communicationFlag;
	}

	public void setCommunicationFlag(String communicationFlag) {
		this.communicationFlag = communicationFlag;
	}

	public String getManagmentFlag() {
		return managmentFlag;
	}

	public void setManagmentFlag(String managmentFlag) {
		this.managmentFlag = managmentFlag;
	}

	public String getPersonalityFlag() {
		return personalityFlag;
	}

	public void setPersonalityFlag(String personalityFlag) {
		this.personalityFlag = personalityFlag;
	}

	public String getLearningFlag() {
		return learningFlag;
	}

	public void setLearningFlag(String learningFlag) {
		this.learningFlag = learningFlag;
	}

	public String getRelevantFlag() {
		return relevantFlag;
	}

	public void setRelevantFlag(String relevantFlag) {
		this.relevantFlag = relevantFlag;
	}

	public String getSutablityFlag() {
		return sutablityFlag;
	}

	public void setSutablityFlag(String sutablityFlag) {
		this.sutablityFlag = sutablityFlag;
	}

	public Boolean getButtonFlag() {
		return buttonFlag;
	}

	public void setButtonFlag(Boolean buttonFlag) {
		this.buttonFlag = buttonFlag;
	}

	public Boolean getMyChkFlag() {
		return myChkFlag;
	}

	public void setMyChkFlag(Boolean myChkFlag) {
		this.myChkFlag = myChkFlag;
	}

	public String getStatusHd() {
		return statusHd;
	}

	public void setStatusHd(String statusHd) {
		this.statusHd = statusHd;
	}

	public String getScoreHd() {
		return scoreHd;
	}

	public void setScoreHd(String scoreHd) {
		this.scoreHd = scoreHd;
	}

	public String getRatingHd() {
		return ratingHd;
	}

	public void setRatingHd(String ratingHd) {
		this.ratingHd = ratingHd;
	}

	public String getIntRndTypeHd() {
		return intRndTypeHd;
	}

	public void setIntRndTypeHd(String intRndTypeHd) {
		this.intRndTypeHd = intRndTypeHd;
	}

	public String getIntrviewerHd() {
		return intrviewerHd;
	}

	public void setIntrviewerHd(String intrviewerHd) {
		this.intrviewerHd = intrviewerHd;
	}

	public String getCandidateHd() {
		return CandidateHd;
	}

	public void setCandidateHd(String candidateHd) {
		CandidateHd = candidateHd;
	}

	public ArrayList getInterviewList() {
		return interviewList;
	}

	public void setInterviewList(ArrayList interviewList) {
		this.interviewList = interviewList;
	}

	public String getCandidateFlag() {
		return CandidateFlag;
	}

	public void setCandidateFlag(String candidateFlag) {
		CandidateFlag = candidateFlag;
	}

	public String getIntRndTypeFlag() {
		return intRndTypeFlag;
	}

	public void setIntRndTypeFlag(String intRndTypeFlag) {
		this.intRndTypeFlag = intRndTypeFlag;
	}

	public String getIntrviewerFlag() {
		return intrviewerFlag;
	}

	public void setIntrviewerFlag(String intrviewerFlag) {
		this.intrviewerFlag = intrviewerFlag;
	}

	public String getScoreFlag() {
		return scoreFlag;
	}

	public void setScoreFlag(String scoreFlag) {
		this.scoreFlag = scoreFlag;
	}

	public String getRatingFlag() {
		return ratingFlag;
	}

	public void setRatingFlag(String ratingFlag) {
		this.ratingFlag = ratingFlag;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getSettingSearch() {
		return settingSearch;
	}

	public void setSettingSearch(String settingSearch) {
		this.settingSearch = settingSearch;
	}

	public String getCandName() {
		return candName;
	}

	public void setCandName(String candName) {
		this.candName = candName;
	}

	public String getCandCode() {
		return candCode;
	}

	public void setCandCode(String candCode) {
		this.candCode = candCode;
	}

	public String getIntrvCode() {
		return intrvCode;
	}

	public void setIntrvCode(String intrvCode) {
		this.intrvCode = intrvCode;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(String intStatus) {
		this.intStatus = intStatus;
	}

	public String getInterviewstatus() {
		return interviewstatus;
	}

	public void setInterviewstatus(String interviewstatus) {
		this.interviewstatus = interviewstatus;
	}

	public String getIntRound() {
		return intRound;
	}

	public void setIntRound(String intRound) {
		this.intRound = intRound;
	}

	public String getInterviewRound() {
		return interviewRound;
	}

	public void setInterviewRound(String interviewRound) {
		this.interviewRound = interviewRound;
	}

	public String getSortByInt() {
		return sortByInt;
	}

	public void setSortByInt(String sortByInt) {
		this.sortByInt = sortByInt;
	}

	public String getSortRadioInt() {
		return sortRadioInt;
	}

	public void setSortRadioInt(String sortRadioInt) {
		this.sortRadioInt = sortRadioInt;
	}

	public String getRadioOneInt() {
		return RadioOneInt;
	}

	public void setRadioOneInt(String radioOneInt) {
		RadioOneInt = radioOneInt;
	}

	public String getThenBYInt() {
		return thenBYInt;
	}

	public void setThenBYInt(String thenBYInt) {
		this.thenBYInt = thenBYInt;
	}

	public String getThenRadioInt() {
		return thenRadioInt;
	}

	public void setThenRadioInt(String thenRadioInt) {
		this.thenRadioInt = thenRadioInt;
	}

	public String getRadioTwoInt() {
		return RadioTwoInt;
	}

	public void setRadioTwoInt(String radioTwoInt) {
		RadioTwoInt = radioTwoInt;
	}

	public String getSecondByInt() {
		return secondByInt;
	}

	public void setSecondByInt(String secondByInt) {
		this.secondByInt = secondByInt;
	}

	public String getThanRadioInt() {
		return thanRadioInt;
	}

	public void setThanRadioInt(String thanRadioInt) {
		this.thanRadioInt = thanRadioInt;
	}

	public String getRadioThreeInt() {
		return RadioThreeInt;
	}

	public void setRadioThreeInt(String radioThreeInt) {
		RadioThreeInt = radioThreeInt;
	}

	public String getIntRoundCheck() {
		return intRoundCheck;
	}

	public void setIntRoundCheck(String intRoundCheck) {
		this.intRoundCheck = intRoundCheck;
	}

	public String getStatusCheck() {
		return statusCheck;
	}

	public void setStatusCheck(String statusCheck) {
		this.statusCheck = statusCheck;
	}

	public String getRatingCheck() {
		return ratingCheck;
	}

	public void setRatingCheck(String ratingCheck) {
		this.ratingCheck = ratingCheck;
	}

	public String getScoreCheck() {
		return scoreCheck;
	}

	public void setScoreCheck(String scoreCheck) {
		this.scoreCheck = scoreCheck;
	}

	public String getInterviewerCheck() {
		return interviewerCheck;
	}

	public void setInterviewerCheck(String interviewerCheck) {
		this.interviewerCheck = interviewerCheck;
	}

	public String getCandidateCheck() {
		return candidateCheck;
	}

	public void setCandidateCheck(String candidateCheck) {
		this.candidateCheck = candidateCheck;
	}

	public String getSchdType() {
		return schdType;
	}

	public void setSchdType(String schdType) {
		this.schdType = schdType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenRep() {
		return genRep;
	}

	public void setGenRep(String genRep) {
		this.genRep = genRep;
	}

	public String getOnScrn() {
		return onScrn;
	}

	public void setOnScrn(String onScrn) {
		this.onScrn = onScrn;
	}

	public String getSeltype() {
		return seltype;
	}

	public void setSeltype(String seltype) {
		this.seltype = seltype;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getHdPage() {
		return hdPage;
	}

	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}

	public String getCandidateLength() {
		return candidateLength;
	}

	public void setCandidateLength(String candidateLength) {
		this.candidateLength = candidateLength;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFromTotRec() {
		return fromTotRec;
	}

	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}

	public String getToTotRec() {
		return toTotRec;
	}

	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
	}

	public String getStatusInt() {
		return statusInt;
	}

	public void setStatusInt(String statusInt) {
		this.statusInt = statusInt;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public HashMap getHashMap() {
		return hashMap;
	}

	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
	}

	public String getSettingCode() {
		return settingCode;
	}

	public void setSettingCode(String settingCode) {
		this.settingCode = settingCode;
	}

	public String getUserEmpId() {
		return userEmpId;
	}

	public void setUserEmpId(String userEmpId) {
		this.userEmpId = userEmpId;
	}

	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getPageNoField() {
		return pageNoField;
	}

	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}

	public String getTotalPage1() {
		return totalPage1;
	}

	public void setTotalPage1(String totalPage1) {
		this.totalPage1 = totalPage1;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
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

	public String getQualificationHd() {
		return qualificationHd;
	}

	public void setQualificationHd(String qualificationHd) {
		this.qualificationHd = qualificationHd;
	}

	public String getTechnicalHd() {
		return technicalHd;
	}

	public void setTechnicalHd(String technicalHd) {
		this.technicalHd = technicalHd;
	}

	

	public String getManagmentHd() {
		return managmentHd;
	}

	public void setManagmentHd(String managmentHd) {
		this.managmentHd = managmentHd;
	}

	public String getPersonalityHd() {
		return personalityHd;
	}

	public void setPersonalityHd(String personalityHd) {
		this.personalityHd = personalityHd;
	}

	public String getLearningHd() {
		return learningHd;
	}

	public void setLearningHd(String learningHd) {
		this.learningHd = learningHd;
	}

	public String getRelevantHd() {
		return relevantHd;
	}

	public void setRelevantHd(String relevantHd) {
		this.relevantHd = relevantHd;
	}

	public String getSuitablityHd() {
		return suitablityHd;
	}

	public void setSuitablityHd(String suitablityHd) {
		this.suitablityHd = suitablityHd;
	}

	public String getNegotiableFlag() {
		return negotiableFlag;
	}

	public void setNegotiableFlag(String negotiableFlag) {
		this.negotiableFlag = negotiableFlag;
	}

	public String getCommunicationHd() {
		return communicationHd;
	}

	public void setCommunicationHd(String communicationHd) {
		this.communicationHd = communicationHd;
	}

	public String getMakeOfferHd() {
		return makeOfferHd;
	}

	public void setMakeOfferHd(String makeOfferHd) {
		this.makeOfferHd = makeOfferHd;
	}

	public String getNextRoundHd() {
		return nextRoundHd;
	}

	public void setNextRoundHd(String nextRoundHd) {
		this.nextRoundHd = nextRoundHd;
	}

	public String getForwardNext() {
		return forwardNext;
	}

	public void setForwardNext(String forwardNext) {
		this.forwardNext = forwardNext;
	}

	public String getMakeOff() {
		return makeOff;
	}

	public void setMakeOff(String makeOff) {
		this.makeOff = makeOff;
	}

	public String getPecrcentageHd() {
		return pecrcentageHd;
	}

	public void setPecrcentageHd(String pecrcentageHd) {
		this.pecrcentageHd = pecrcentageHd;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getEvalCode() {
		return evalCode;
	}

	public void setEvalCode(String evalCode) {
		this.evalCode = evalCode;
	}

	public String getEvalScore() {
		return evalScore;
	}

	public void setEvalScore(String evalScore) {
		this.evalScore = evalScore;
	}

	public String getLearningCheck() {
		return learningCheck;
	}

	public void setLearningCheck(String learningCheck) {
		this.learningCheck = learningCheck;
	}

	public String getMakeOfferFlag() {
		return makeOfferFlag;
	}

	public void setMakeOfferFlag(String makeOfferFlag) {
		this.makeOfferFlag = makeOfferFlag;
	}

	public String getNextRoundFlag() {
		return nextRoundFlag;
	}

	public void setNextRoundFlag(String nextRoundFlag) {
		this.nextRoundFlag = nextRoundFlag;
	}

	public String getSuitablityCheck() {
		return suitablityCheck;
	}

	public void setSuitablityCheck(String suitablityCheck) {
		this.suitablityCheck = suitablityCheck;
	}

	public String getPercantageFlag() {
		return percantageFlag;
	}

	public void setPercantageFlag(String percantageFlag) {
		this.percantageFlag = percantageFlag;
	}

	public String getPercentageHd() {
		return percentageHd;
	}

	public void setPercentageHd(String percentageHd) {
		this.percentageHd = percentageHd;
	}

	public String getPercentageCheck() {
		return percentageCheck;
	}

	public void setPercentageCheck(String percentageCheck) {
		this.percentageCheck = percentageCheck;
	}

	public String getPercent() {
		return Percent;
	}

	public void setPercent(String percent) {
		Percent = percent;
	}

	public String getPercentageFlag() {
		return percentageFlag;
	}

	public void setPercentageFlag(String percentageFlag) {
		this.percentageFlag = percentageFlag;
	}

	public String getExportAll_bot() {
		return exportAll_bot;
	}

	public void setExportAll_bot(String exportAll_bot) {
		this.exportAll_bot = exportAll_bot;
	}

	public String getExportAll() {
		return exportAll;
	}

	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}

	public String getIntStatusFlag() {
		return intStatusFlag;
	}

	public void setIntStatusFlag(String intStatusFlag) {
		this.intStatusFlag = intStatusFlag;
	}

	public String getIntStatusCheck() {
		return intStatusCheck;
	}

	public void setIntStatusCheck(String intStatusCheck) {
		this.intStatusCheck = intStatusCheck;
	}
	
	
}
