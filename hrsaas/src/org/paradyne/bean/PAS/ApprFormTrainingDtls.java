package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ApprFormTrainingDtls extends BeanBase {
	
	private String mode;
	//veriable for projected score
	private String sourceFormType="";
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
	
	//common bean variables for navigation
	private String apprId;
	private String apprCode;
	private String apprStartDate;
	private String apprEndDate;
	private String apprValidTillDate;
	private String detailFLag="false";//to display for approval check
	private String phaseForwardFlag="false";//phase forwarded so display in readonly
	
	private String phaseCode;
	private String phaseName;
	private String phaseSequence;
	private String phaseStartDate;
	private String phaseEndDate;
	private String phaseLockFlag;
	private String quesWtDisplayFlag;
	
	private String empId;
	private String empName;
	private String empDesgName;
	private String templateCode;
	private String sectionCode;
	private String sectionList;
	private String nextExist;
	private String previousExist;
	
	private String isSelf;
	
	private String sectionId;
	private String visibilityFlag;
	private String ratingFlag;
	private String commentFlag="false";
	private String questionCode;
	private String questionDesc;
	private String charLimit;
	private String commentsFlag;
	private String quesComment;
	
	private String trngCode;
	private String trngDesc;
	private String trngDuration;
	private String trngFrom;
	private String trngTo;
	private String trngSponsor;
	private String trngComments;
	private String removeTrngCode;
	private String flag;
	
	private ArrayList trainingList;
	private ArrayList questionList;
	
	
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(String isSelf) {
		this.isSelf = isSelf;
	}
	public String getRemoveTrngCode() {
		return removeTrngCode;
	}
	public void setRemoveTrngCode(String removeTrngCode) {
		this.removeTrngCode = removeTrngCode;
	}
	public String getPhaseSequence() {
		return phaseSequence;
	}
	public void setPhaseSequence(String phaseSequence) {
		this.phaseSequence = phaseSequence;
	}
	public String getTrngCode() {
		return trngCode;
	}
	public void setTrngCode(String trngCode) {
		this.trngCode = trngCode;
	}
	public String getTrngDesc() {
		return trngDesc;
	}
	public void setTrngDesc(String trngDesc) {
		this.trngDesc = trngDesc;
	}
	public String getTrngDuration() {
		return trngDuration;
	}
	public void setTrngDuration(String trngDuration) {
		this.trngDuration = trngDuration;
	}
	public String getTrngFrom() {
		return trngFrom;
	}
	public void setTrngFrom(String trngFrom) {
		this.trngFrom = trngFrom;
	}
	public String getTrngTo() {
		return trngTo;
	}
	public void setTrngTo(String trngTo) {
		this.trngTo = trngTo;
	}
	public String getTrngSponsor() {
		return trngSponsor;
	}
	public void setTrngSponsor(String trngSponsor) {
		this.trngSponsor = trngSponsor;
	}
	public String getTrngComments() {
		return trngComments;
	}
	public void setTrngComments(String trngComments) {
		this.trngComments = trngComments;
	}
	public ArrayList getTrainingList() {
		return trainingList;
	}
	public void setTrainingList(ArrayList trainingList) {
		this.trainingList = trainingList;
	}
	public String getCommentsFlag() {
		return commentsFlag;
	}
	public void setCommentsFlag(String commentsFlag) {
		this.commentsFlag = commentsFlag;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCharLimit() {
		return charLimit;
	}
	public void setCharLimit(String charLimit) {
		this.charLimit = charLimit;
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
	
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	public String getApprValidTillDate() {
		return apprValidTillDate;
	}
	public void setApprValidTillDate(String apprValidTillDate) {
		this.apprValidTillDate = apprValidTillDate;
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
	public String getQuesComment() {
		return quesComment;
	}
	public void setQuesComment(String quesComment) {
		this.quesComment = quesComment;
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
	public String getSourceFormType() {
		return sourceFormType;
	}
	public void setSourceFormType(String sourceFormType) {
		this.sourceFormType = sourceFormType;
	}
	
	

}
