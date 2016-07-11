package org.paradyne.bean.PAS;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class ApprFormSection extends BeanBase {
	
	private String phaseOrder="";
	
	private String source="";
	private String goalFinalizeFlag="false";
	private String compFinalizeFlag="false";
	private String goalMapFlag="false";
	private String compMapFlag="false";
	private String previewFlag="false";
	private String apprWeightage="";
	private String apprScore="";
	private String goalWeightage="";
	private String goalScore="";
	private String compWeightage="";
	private String compScore="";
	private String totalScore="";
	private String apprId;
	private String apprCode;
	private String apprStartDate;
	private String apprEndDate;
	private String apprValidTillDate;
	private String detailFLag="false";//to display for approval check
	private String phaseForwardFlag="false";//phase forwarded so display in readonly
	
	private String phaseCode;
	private String phaseName;
	private String phaseStartDate;
	private String phaseEndDate;
	private String phaseLockFlag;
	private String quesWtDisplayFlag;
	
	private String empId;
	private String empName;
	private String empDesgName;
	private String templateCode;
	
	private String sectionCode;
	private String sectionName;
	private String visibilityFlag;
	private String ratingFlag;
	private String commentFlag;
	private String questionCode;
	private String questionDesc;
	private String quesWeight;
	private String quesType;
	private String fractionRating;
	private String quesRating;
	private String quesMandatory;
	private String quesLimit;
	private String quesComment;
	private HashMap hashmap;
	private String ratingNote;
	private String quesAvg;
	private String totalWeightage;
	private String totalAvg;
	private String ratingType;//perc or scale
	
	
	private String sectionList;
	private String nextExist="true";
	private String previousExist="false";
	private String empDataExist="false";
	private String maxWeightage;
	private ArrayList questionList;
	private ArrayList previousPhaseDataList;
	
	/*Preview Purpose*/
	private String ittrPhaseName="";
	private String ittrSectionName="";
	private String ittrQuesDesc="";
	private String ittrQuesWt="";
	private String ittrQuesRating="";
	private String ittrActual="";
	private String ittrComment="";
	private ArrayList apprRatingList=null;
	
	private String ittrTrnAttndDesc="";
	private String ittrTrnAttndFrom="";
	private String ittrTrnAttndTo="";
	private String ittrTrnAttndDuration="";
	private String ittrTrnAttndSponser="";
	private ArrayList appTrnAttndList=null;
	
	private String ittrTrnPhase="";
	private String ittrTrnAttendDesc="";
	private String ittrTrnAttndComment="";
	private ArrayList apprTrnAttndCommList=null;
	
	
	private String ittrTrnRecomndPhase="";
	private String ittrTrnRecomndQusDesc="";
	private String ittrTrnRecomndComment="";
	private ArrayList apprTrnRecomndList=null;
	
	private String ittrAwdAchvdDesc="";
	private String ittrAwdAchvDate="";
	private String ittrAwdAchvIssueAuth="";
	private ArrayList apprAwdAchvList=null;
	
	private String ittrAwdAchvdCommPhase="";
	private String ittrAwdAchvdCommDesc="";
	private String ittrAwdAchvdComment="";
	private ArrayList apprAwdAchvdCommList=null;
	
	private String ittrAwdNominPhase="";
	private String ittrAwdNominAwardName="";
	private String ittrAwdNominAwardReason="";
	private ArrayList apprAwdNomnList=null;
	
	private String ittrDispAction="";
	private String ittrDispActDate="";
	private String ittrDispActIsssueAuth="";
	private ArrayList apprDispActList=null;
	
	private String ittrDispActPhase="";
	private String ittrDispActCommDesc="";
	private String ittrDispActComment="";
	private ArrayList apprDispActCommentList=null;
	
	
	
	
	private String prvPhaseName;
	private String prvPhaseApprName;;
	private String prvPhaseWeightage;
	private String prvPhaseRating;
	private String prvPhaseActual;
	private String prvPhaseComments;
	private ArrayList subQuestionList;
	
	private String quesDescNV;;
	private String quesWeightNV;;
	private String quesRatingNV;
	private String quesActualNV;
	private String commentsNV;
	private ArrayList questionListNV;
	private ArrayList subQuestionListNV;
	
	private String prvPhaseNameNV;
	private String prvPhaseApprNameNV;;
	private String prvPhaseWeightageNV;
	private String prvPhaseRatingNV;
	private String prvPhaseActualNV;
	private String prvPhaseCommentsNV;
	private String flagNV="false";
	private String flag="false";
	
	private String ratingMonthString="";
	private String monthRating="";
	private ArrayList monthRatingList=null;
	private boolean showMonthRating=false;
	private String avgMonthRating;
	private String rating70;
	private boolean parametersAvailable=false;
	
	
	public boolean isParametersAvailable() {
		return parametersAvailable;
	}
	public void setParametersAvailable(boolean parametersAvailable) {
		this.parametersAvailable = parametersAvailable;
	}
	public String getAvgMonthRating() {
		return avgMonthRating;
	}
	public void setAvgMonthRating(String avgMonthRating) {
		this.avgMonthRating = avgMonthRating;
	}
	public boolean isShowMonthRating() {
		return showMonthRating;
	}
	public void setShowMonthRating(boolean showMonthRating) {
		this.showMonthRating = showMonthRating;
	}
	public String getFlagNV() {
		return flagNV;
	}
	public void setFlagNV(String flagNV) {
		this.flagNV = flagNV;
	}
	public String getPrvPhaseNameNV() {
		return prvPhaseNameNV;
	}
	public void setPrvPhaseNameNV(String prvPhaseNameNV) {
		this.prvPhaseNameNV = prvPhaseNameNV;
	}
	public String getPrvPhaseApprNameNV() {
		return prvPhaseApprNameNV;
	}
	public void setPrvPhaseApprNameNV(String prvPhaseApprNameNV) {
		this.prvPhaseApprNameNV = prvPhaseApprNameNV;
	}
	public String getPrvPhaseWeightageNV() {
		return prvPhaseWeightageNV;
	}
	public void setPrvPhaseWeightageNV(String prvPhaseWeightageNV) {
		this.prvPhaseWeightageNV = prvPhaseWeightageNV;
	}
	public String getPrvPhaseRatingNV() {
		return prvPhaseRatingNV;
	}
	public void setPrvPhaseRatingNV(String prvPhaseRatingNV) {
		this.prvPhaseRatingNV = prvPhaseRatingNV;
	}
	public String getPrvPhaseActualNV() {
		return prvPhaseActualNV;
	}
	public void setPrvPhaseActualNV(String prvPhaseActualNV) {
		this.prvPhaseActualNV = prvPhaseActualNV;
	}
	public String getPrvPhaseCommentsNV() {
		return prvPhaseCommentsNV;
	}
	public void setPrvPhaseCommentsNV(String prvPhaseCommentsNV) {
		this.prvPhaseCommentsNV = prvPhaseCommentsNV;
	}
	public ArrayList getPreviousPhaseDataList() {
		return previousPhaseDataList;
	}
	public void setPreviousPhaseDataList(ArrayList previousPhaseDataList) {
		this.previousPhaseDataList = previousPhaseDataList;
	}
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getApprStartDate() {
		return apprStartDate;
	}
	public void setApprStartDate(String apprStartDate) {
		this.apprStartDate = apprStartDate;
	}
	public String getApprEndDate() {
		return apprEndDate;
	}
	public void setApprEndDate(String apprEndDate) {
		this.apprEndDate = apprEndDate;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getPhaseStartDate() {
		return phaseStartDate;
	}
	public void setPhaseStartDate(String phaseStartDate) {
		this.phaseStartDate = phaseStartDate;
	}
	public String getPhaseEndDate() {
		return phaseEndDate;
	}
	public void setPhaseEndDate(String phaseEndDate) {
		this.phaseEndDate = phaseEndDate;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDesgName() {
		return empDesgName;
	}
	public void setEmpDesgName(String empDesgName) {
		this.empDesgName = empDesgName;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	
	public ArrayList getQuestionList() {
		return questionList;
	}
	public void setQuestionList(ArrayList questionList) {
		this.questionList = questionList;
	}
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public String getQuesRating() {
		return quesRating;
	}
	public void setQuesRating(String quesRating) {
		this.quesRating = quesRating;
	}
	public String getQuesComment() {
		return quesComment;
	}
	public void setQuesComment(String quesComment) {
		this.quesComment = quesComment;
	}
	public String getQuesWeight() {
		return quesWeight;
	}
	public void setQuesWeight(String quesWeight) {
		this.quesWeight = quesWeight;
	}
	public String getQuesType() {
		return quesType;
	}
	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}
	public String getVisibilityFlag() {
		return visibilityFlag;
	}
	public void setVisibilityFlag(String visibilityFlag) {
		this.visibilityFlag = visibilityFlag;
	}
	public String getRatingFlag() {
		return ratingFlag;
	}
	public void setRatingFlag(String ratingFlag) {
		this.ratingFlag = ratingFlag;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getSectionList() {
		return sectionList;
	}
	public void setSectionList(String sectionList) {
		this.sectionList = sectionList;
	}
	public String getNextExist() {
		return nextExist;
	}
	public void setNextExist(String nextExist) {
		this.nextExist = nextExist;
	}
	public String getPreviousExist() {
		return previousExist;
	}
	public void setPreviousExist(String previousExist) {
		this.previousExist = previousExist;
	}
	public String getEmpDataExist() {
		return empDataExist;
	}
	public void setEmpDataExist(String empDataExist) {
		this.empDataExist = empDataExist;
	}
	public String getMaxWeightage() {
		return maxWeightage;
	}
	public void setMaxWeightage(String maxWeightage) {
		this.maxWeightage = maxWeightage;
	}
	public String getDetailFLag() {
		return detailFLag;
	}
	public void setDetailFLag(String detailFLag) {
		this.detailFLag = detailFLag;
	}
	public String getPhaseForwardFlag() {
		return phaseForwardFlag;
	}
	public void setPhaseForwardFlag(String phaseForwardFlag) {
		this.phaseForwardFlag = phaseForwardFlag;
	}
	public String getApprValidTillDate() {
		return apprValidTillDate;
	}
	public void setApprValidTillDate(String apprValidTillDate) {
		this.apprValidTillDate = apprValidTillDate;
	}
	public HashMap getHashmap() {
		return hashmap;
	}
	public void setHashmap(HashMap hashmap) {
		this.hashmap = hashmap;
	}
	public String getQuesMandatory() {
		return quesMandatory;
	}
	public void setQuesMandatory(String quesMandatory) {
		this.quesMandatory = quesMandatory;
	}
	public String getQuesLimit() {
		return quesLimit;
	}
	public void setQuesLimit(String quesLimit) {
		this.quesLimit = quesLimit;
	}
	public String getRatingNote() {
		return ratingNote;
	}
	public void setRatingNote(String ratingNote) {
		this.ratingNote = ratingNote;
	}
	public String getQuesAvg() {
		return quesAvg;
	}
	public void setQuesAvg(String quesAvg) {
		this.quesAvg = quesAvg;
	}
	public String getTotalWeightage() {
		return totalWeightage;
	}
	public void setTotalWeightage(String totalWeightage) {
		this.totalWeightage = totalWeightage;
	}
	public String getTotalAvg() {
		return totalAvg;
	}
	public void setTotalAvg(String totalAvg) {
		this.totalAvg = totalAvg;
	}
	public String getRatingType() {
		return ratingType;
	}
	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}
	public String getPhaseLockFlag() {
		return phaseLockFlag;
	}
	public void setPhaseLockFlag(String phaseLockFlag) {
		this.phaseLockFlag = phaseLockFlag;
	}
	public String getQuesWtDisplayFlag() {
		return quesWtDisplayFlag;
	}
	public void setQuesWtDisplayFlag(String quesWtDisplayFlag) {
		this.quesWtDisplayFlag = quesWtDisplayFlag;
	}
	public String getFractionRating() {
		return fractionRating;
	}
	public void setFractionRating(String fractionRating) {
		this.fractionRating = fractionRating;
	}
	public String getPrvPhaseName() {
		return prvPhaseName;
	}
	public void setPrvPhaseName(String prvPhaseName) {
		this.prvPhaseName = prvPhaseName;
	}
	public String getPrvPhaseApprName() {
		return prvPhaseApprName;
	}
	public void setPrvPhaseApprName(String prvPhaseApprName) {
		this.prvPhaseApprName = prvPhaseApprName;
	}
	public String getPrvPhaseWeightage() {
		return prvPhaseWeightage;
	}
	public void setPrvPhaseWeightage(String prvPhaseWeightage) {
		this.prvPhaseWeightage = prvPhaseWeightage;
	}
	public String getPrvPhaseRating() {
		return prvPhaseRating;
	}
	public void setPrvPhaseRating(String prvPhaseRating) {
		this.prvPhaseRating = prvPhaseRating;
	}
	public String getPrvPhaseActual() {
		return prvPhaseActual;
	}
	public void setPrvPhaseActual(String prvPhaseActual) {
		this.prvPhaseActual = prvPhaseActual;
	}
	public String getPrvPhaseComments() {
		return prvPhaseComments;
	}
	public void setPrvPhaseComments(String prvPhaseComments) {
		this.prvPhaseComments = prvPhaseComments;
	}
	public ArrayList getSubQuestionList() {
		return subQuestionList;
	}
	public void setSubQuestionList(ArrayList subQuestionList) {
		this.subQuestionList = subQuestionList;
	}
	public String getQuesDescNV() {
		return quesDescNV;
	}
	public void setQuesDescNV(String quesDescNV) {
		this.quesDescNV = quesDescNV;
	}
	public String getQuesWeightNV() {
		return quesWeightNV;
	}
	public void setQuesWeightNV(String quesWeightNV) {
		this.quesWeightNV = quesWeightNV;
	}
	public String getQuesRatingNV() {
		return quesRatingNV;
	}
	public void setQuesRatingNV(String quesRatingNV) {
		this.quesRatingNV = quesRatingNV;
	}
	public String getQuesActualNV() {
		return quesActualNV;
	}
	public void setQuesActualNV(String quesActualNV) {
		this.quesActualNV = quesActualNV;
	}
	public String getCommentsNV() {
		return commentsNV;
	}
	public void setCommentsNV(String commentsNV) {
		this.commentsNV = commentsNV;
	}
	public ArrayList getQuestionListNV() {
		return questionListNV;
	}
	public void setQuestionListNV(ArrayList questionListNV) {
		this.questionListNV = questionListNV;
	}
	public ArrayList getSubQuestionListNV() {
		return subQuestionListNV;
	}
	public void setSubQuestionListNV(ArrayList subQuestionListNV) {
		this.subQuestionListNV = subQuestionListNV;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRatingMonthString() {
		return ratingMonthString;
	}
	public void setRatingMonthString(String ratingMonthString) {
		this.ratingMonthString = ratingMonthString;
	}
	public String getMonthRating() {
		return monthRating;
	}
	public void setMonthRating(String monthRating) {
		this.monthRating = monthRating;
	}
	public ArrayList getMonthRatingList() {
		return monthRatingList;
	}
	public void setMonthRatingList(ArrayList monthRatingList) {
		this.monthRatingList = monthRatingList;
	}
	public String getRating70() {
		return rating70;
	}
	public void setRating70(String rating70) {
		this.rating70 = rating70;
	}
	public String getIttrPhaseName() {
		return ittrPhaseName;
	}
	public void setIttrPhaseName(String ittrPhaseName) {
		this.ittrPhaseName = ittrPhaseName;
	}
	public String getIttrSectionName() {
		return ittrSectionName;
	}
	public void setIttrSectionName(String ittrSectionName) {
		this.ittrSectionName = ittrSectionName;
	}
	public String getIttrQuesDesc() {
		return ittrQuesDesc;
	}
	public void setIttrQuesDesc(String ittrQuesDesc) {
		this.ittrQuesDesc = ittrQuesDesc;
	}
	public String getIttrQuesWt() {
		return ittrQuesWt;
	}
	public void setIttrQuesWt(String ittrQuesWt) {
		this.ittrQuesWt = ittrQuesWt;
	}
	public String getIttrQuesRating() {
		return ittrQuesRating;
	}
	public void setIttrQuesRating(String ittrQuesRating) {
		this.ittrQuesRating = ittrQuesRating;
	}
	public String getIttrActual() {
		return ittrActual;
	}
	public void setIttrActual(String ittrActual) {
		this.ittrActual = ittrActual;
	}
	public String getIttrComment() {
		return ittrComment;
	}
	public void setIttrComment(String ittrComment) {
		this.ittrComment = ittrComment;
	}
	public ArrayList getApprRatingList() {
		return apprRatingList;
	}
	public void setApprRatingList(ArrayList apprRatingList) {
		this.apprRatingList = apprRatingList;
	}
	public String getIttrTrnAttndDesc() {
		return ittrTrnAttndDesc;
	}
	public void setIttrTrnAttndDesc(String ittrTrnAttndDesc) {
		this.ittrTrnAttndDesc = ittrTrnAttndDesc;
	}
	public String getIttrTrnAttndFrom() {
		return ittrTrnAttndFrom;
	}
	public void setIttrTrnAttndFrom(String ittrTrnAttndFrom) {
		this.ittrTrnAttndFrom = ittrTrnAttndFrom;
	}
	public String getIttrTrnAttndTo() {
		return ittrTrnAttndTo;
	}
	public void setIttrTrnAttndTo(String ittrTrnAttndTo) {
		this.ittrTrnAttndTo = ittrTrnAttndTo;
	}
	public String getIttrTrnAttndDuration() {
		return ittrTrnAttndDuration;
	}
	public void setIttrTrnAttndDuration(String ittrTrnAttndDuration) {
		this.ittrTrnAttndDuration = ittrTrnAttndDuration;
	}
	public String getIttrTrnAttndSponser() {
		return ittrTrnAttndSponser;
	}
	public void setIttrTrnAttndSponser(String ittrTrnAttndSponser) {
		this.ittrTrnAttndSponser = ittrTrnAttndSponser;
	}
	public ArrayList getAppTrnAttndList() {
		return appTrnAttndList;
	}
	public void setAppTrnAttndList(ArrayList appTrnAttndList) {
		this.appTrnAttndList = appTrnAttndList;
	}
	public String getIttrTrnPhase() {
		return ittrTrnPhase;
	}
	public void setIttrTrnPhase(String ittrTrnPhase) {
		this.ittrTrnPhase = ittrTrnPhase;
	}
	public String getIttrTrnAttendDesc() {
		return ittrTrnAttendDesc;
	}
	public void setIttrTrnAttendDesc(String ittrTrnAttendDesc) {
		this.ittrTrnAttendDesc = ittrTrnAttendDesc;
	}
	public String getIttrTrnAttndComment() {
		return ittrTrnAttndComment;
	}
	public void setIttrTrnAttndComment(String ittrTrnAttndComment) {
		this.ittrTrnAttndComment = ittrTrnAttndComment;
	}
	public ArrayList getApprTrnAttndCommList() {
		return apprTrnAttndCommList;
	}
	public void setApprTrnAttndCommList(ArrayList apprTrnAttndCommList) {
		this.apprTrnAttndCommList = apprTrnAttndCommList;
	}
	public String getIttrTrnRecomndPhase() {
		return ittrTrnRecomndPhase;
	}
	public void setIttrTrnRecomndPhase(String ittrTrnRecomndPhase) {
		this.ittrTrnRecomndPhase = ittrTrnRecomndPhase;
	}
	public String getIttrTrnRecomndQusDesc() {
		return ittrTrnRecomndQusDesc;
	}
	public void setIttrTrnRecomndQusDesc(String ittrTrnRecomndQusDesc) {
		this.ittrTrnRecomndQusDesc = ittrTrnRecomndQusDesc;
	}
	public String getIttrTrnRecomndComment() {
		return ittrTrnRecomndComment;
	}
	public void setIttrTrnRecomndComment(String ittrTrnRecomndComment) {
		this.ittrTrnRecomndComment = ittrTrnRecomndComment;
	}
	public ArrayList getApprTrnRecomndList() {
		return apprTrnRecomndList;
	}
	public void setApprTrnRecomndList(ArrayList apprTrnRecomndList) {
		this.apprTrnRecomndList = apprTrnRecomndList;
	}
	public String getIttrAwdAchvdDesc() {
		return ittrAwdAchvdDesc;
	}
	public void setIttrAwdAchvdDesc(String ittrAwdAchvdDesc) {
		this.ittrAwdAchvdDesc = ittrAwdAchvdDesc;
	}
	public String getIttrAwdAchvDate() {
		return ittrAwdAchvDate;
	}
	public void setIttrAwdAchvDate(String ittrAwdAchvDate) {
		this.ittrAwdAchvDate = ittrAwdAchvDate;
	}
	public String getIttrAwdAchvIssueAuth() {
		return ittrAwdAchvIssueAuth;
	}
	public void setIttrAwdAchvIssueAuth(String ittrAwdAchvIssueAuth) {
		this.ittrAwdAchvIssueAuth = ittrAwdAchvIssueAuth;
	}
	public ArrayList getApprAwdAchvList() {
		return apprAwdAchvList;
	}
	public void setApprAwdAchvList(ArrayList apprAwdAchvList) {
		this.apprAwdAchvList = apprAwdAchvList;
	}
	public String getIttrAwdAchvdCommPhase() {
		return ittrAwdAchvdCommPhase;
	}
	public void setIttrAwdAchvdCommPhase(String ittrAwdAchvdCommPhase) {
		this.ittrAwdAchvdCommPhase = ittrAwdAchvdCommPhase;
	}
	public String getIttrAwdAchvdCommDesc() {
		return ittrAwdAchvdCommDesc;
	}
	public void setIttrAwdAchvdCommDesc(String ittrAwdAchvdCommDesc) {
		this.ittrAwdAchvdCommDesc = ittrAwdAchvdCommDesc;
	}
	public String getIttrAwdAchvdComment() {
		return ittrAwdAchvdComment;
	}
	public void setIttrAwdAchvdComment(String ittrAwdAchvdComment) {
		this.ittrAwdAchvdComment = ittrAwdAchvdComment;
	}
	public ArrayList getApprAwdAchvdCommList() {
		return apprAwdAchvdCommList;
	}
	public void setApprAwdAchvdCommList(ArrayList apprAwdAchvdCommList) {
		this.apprAwdAchvdCommList = apprAwdAchvdCommList;
	}
	public String getIttrAwdNominPhase() {
		return ittrAwdNominPhase;
	}
	public void setIttrAwdNominPhase(String ittrAwdNominPhase) {
		this.ittrAwdNominPhase = ittrAwdNominPhase;
	}
	public String getIttrAwdNominAwardName() {
		return ittrAwdNominAwardName;
	}
	public void setIttrAwdNominAwardName(String ittrAwdNominAwardName) {
		this.ittrAwdNominAwardName = ittrAwdNominAwardName;
	}
	public String getIttrAwdNominAwardReason() {
		return ittrAwdNominAwardReason;
	}
	public void setIttrAwdNominAwardReason(String ittrAwdNominAwardReason) {
		this.ittrAwdNominAwardReason = ittrAwdNominAwardReason;
	}
	public ArrayList getApprAwdNomnList() {
		return apprAwdNomnList;
	}
	public void setApprAwdNomnList(ArrayList apprAwdNomnList) {
		this.apprAwdNomnList = apprAwdNomnList;
	}
	public String getIttrDispAction() {
		return ittrDispAction;
	}
	public void setIttrDispAction(String ittrDispAction) {
		this.ittrDispAction = ittrDispAction;
	}
	public String getIttrDispActDate() {
		return ittrDispActDate;
	}
	public void setIttrDispActDate(String ittrDispActDate) {
		this.ittrDispActDate = ittrDispActDate;
	}
	public String getIttrDispActIsssueAuth() {
		return ittrDispActIsssueAuth;
	}
	public void setIttrDispActIsssueAuth(String ittrDispActIsssueAuth) {
		this.ittrDispActIsssueAuth = ittrDispActIsssueAuth;
	}
	public ArrayList getApprDispActList() {
		return apprDispActList;
	}
	public void setApprDispActList(ArrayList apprDispActList) {
		this.apprDispActList = apprDispActList;
	}
	public String getIttrDispActPhase() {
		return ittrDispActPhase;
	}
	public void setIttrDispActPhase(String ittrDispActPhase) {
		this.ittrDispActPhase = ittrDispActPhase;
	}
	public String getIttrDispActCommDesc() {
		return ittrDispActCommDesc;
	}
	public void setIttrDispActCommDesc(String ittrDispActCommDesc) {
		this.ittrDispActCommDesc = ittrDispActCommDesc;
	}
	public String getIttrDispActComment() {
		return ittrDispActComment;
	}
	public void setIttrDispActComment(String ittrDispActComment) {
		this.ittrDispActComment = ittrDispActComment;
	}
	public ArrayList getApprDispActCommentList() {
		return apprDispActCommentList;
	}
	public void setApprDispActCommentList(ArrayList apprDispActCommentList) {
		this.apprDispActCommentList = apprDispActCommentList;
	}
	public String getPreviewFlag() {
		return previewFlag;
	}
	public void setPreviewFlag(String previewFlag) {
		this.previewFlag = previewFlag;
	}
	public String getApprWeightage() {
		return apprWeightage;
	}
	public void setApprWeightage(String apprWeightage) {
		this.apprWeightage = apprWeightage;
	}
	public String getApprScore() {
		return apprScore;
	}
	public void setApprScore(String apprScore) {
		this.apprScore = apprScore;
	}
	public String getGoalWeightage() {
		return goalWeightage;
	}
	public void setGoalWeightage(String goalWeightage) {
		this.goalWeightage = goalWeightage;
	}
	public String getGoalScore() {
		return goalScore;
	}
	public void setGoalScore(String goalScore) {
		this.goalScore = goalScore;
	}
	public String getCompWeightage() {
		return compWeightage;
	}
	public void setCompWeightage(String compWeightage) {
		this.compWeightage = compWeightage;
	}
	public String getCompScore() {
		return compScore;
	}
	public void setCompScore(String compScore) {
		this.compScore = compScore;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getGoalMapFlag() {
		return goalMapFlag;
	}
	public void setGoalMapFlag(String goalMapFlag) {
		this.goalMapFlag = goalMapFlag;
	}
	public String getCompMapFlag() {
		return compMapFlag;
	}
	public void setCompMapFlag(String compMapFlag) {
		this.compMapFlag = compMapFlag;
	}
	public String getGoalFinalizeFlag() {
		return goalFinalizeFlag;
	}
	public void setGoalFinalizeFlag(String goalFinalizeFlag) {
		this.goalFinalizeFlag = goalFinalizeFlag;
	}
	public String getCompFinalizeFlag() {
		return compFinalizeFlag;
	}
	public void setCompFinalizeFlag(String compFinalizeFlag) {
		this.compFinalizeFlag = compFinalizeFlag;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	

}
