package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ApprFormCareerProgression extends BeanBase {
	
	private String mode;
	private String source="";
	private String sourceFormType="";
	private String visibliltyFlagNew="false";
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
	
	private String visibilityFlag;
	private String sectionId;	
	
	private String commentFlag="false";
	private String questionCode;
	private String questionDesc;
	private String charLimit;
	private String commentsFlag;
	private String quesComment;
	private String flag;
	
	private String careerId;
	private String careerIdComment;
	private String careerComment;
	private ArrayList careerList;

	private String showCommentFlag="false";
	
	private String isCommentsApp="false";
	
	 
	
	private String isAddButtonPress="false";
	
	/*
	 * FOR PREVIOUS COMMENTS
	 */
	private String phaseNamePrev="";
	private String appraiserNamePrev="";
	private String commentsPrev="";
	private ArrayList careerListPrev;
	private ArrayList commentsList;
	private String commentsHead;
	private ArrayList commentsDataList;
	private ArrayList commentsDataSubList;
	
	//private String appraiserNamePrev="";
	
	public ArrayList getCommentsDataList() {
		return commentsDataList;
	}

	public void setCommentsDataList(ArrayList commentsDataList) {
		this.commentsDataList = commentsDataList;
	}

	public ArrayList getCommentsDataSubList() {
		return commentsDataSubList;
	}

	public void setCommentsDataSubList(ArrayList commentsDataSubList) {
		this.commentsDataSubList = commentsDataSubList;
	}

	public ArrayList getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(ArrayList commentsList) {
		this.commentsList = commentsList;
	}

	public String getCommentsHead() {
		return commentsHead;
	}

	public void setCommentsHead(String commentsHead) {
		this.commentsHead = commentsHead;
	}

	public String getPhaseNamePrev() {
		return phaseNamePrev;
	}

	public void setPhaseNamePrev(String phaseNamePrev) {
		this.phaseNamePrev = phaseNamePrev;
	}

	public String getAppraiserNamePrev() {
		return appraiserNamePrev;
	}

	public void setAppraiserNamePrev(String appraiserNamePrev) {
		this.appraiserNamePrev = appraiserNamePrev;
	}

	public String getCommentsPrev() {
		return commentsPrev;
	}

	public void setCommentsPrev(String commentsPrev) {
		this.commentsPrev = commentsPrev;
	}

	public ArrayList getCareerListPrev() {
		return careerListPrev;
	}

	public void setCareerListPrev(ArrayList careerListPrev) {
		this.careerListPrev = careerListPrev;
	}

	public String getIsAddButtonPress() {
		return isAddButtonPress;
	}

	public void setIsAddButtonPress(String isAddButtonPress) {
		this.isAddButtonPress = isAddButtonPress;
	}

	public String getShowCommentFlag() {
		return showCommentFlag;
	}

	public void setShowCommentFlag(String showCommentFlag) {
		this.showCommentFlag = showCommentFlag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getCharLimit() {
		return charLimit;
	}

	public void setCharLimit(String charLimit) {
		this.charLimit = charLimit;
	}

	public String getCommentsFlag() {
		return commentsFlag;
	}

	public void setCommentsFlag(String commentsFlag) {
		this.commentsFlag = commentsFlag;
	}

	public String getQuesComment() {
		return quesComment;
	}

	public void setQuesComment(String quesComment) {
		this.quesComment = quesComment;
	}

	public String getCareerId() {
		return careerId;
	}

	public void setCareerId(String careerId) {
		this.careerId = careerId;
	}

	public String getCareerIdComment() {
		return careerIdComment;
	}

	public void setCareerIdComment(String careerIdComment) {
		this.careerIdComment = careerIdComment;
	}

	public String getCareerComment() {
		return careerComment;
	}

	public void setCareerComment(String careerComment) {
		this.careerComment = careerComment;
	}

	public ArrayList getCareerList() {
		return careerList;
	}

	public void setCareerList(ArrayList careerList) {
		this.careerList = careerList;
	}

	public String getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(String isSelf) {
		this.isSelf = isSelf;
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


	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public String getPhaseSequence() {
		return phaseSequence;
	}

	public void setPhaseSequence(String phaseSequence) {
		this.phaseSequence = phaseSequence;
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

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getVisibilityFlag() {
		return visibilityFlag;
	}

	public void setVisibilityFlag(String visibilityFlag) {
		this.visibilityFlag = visibilityFlag;
	}

	
	public String getCommentFlag() {
		return commentFlag;
	}

	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
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

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getSectionList() {
		return sectionList;
	}

	public void setSectionList(String sectionList) {
		this.sectionList = sectionList;
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

	public String getIsCommentsApp() {
		return isCommentsApp;
	}

	public void setIsCommentsApp(String isCommentsApp) {
		this.isCommentsApp = isCommentsApp;
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

	public String getVisibliltyFlagNew() {
		return visibliltyFlagNew;
	}

	public void setVisibliltyFlagNew(String visibliltyFlagNew) {
		this.visibliltyFlagNew = visibliltyFlagNew;
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
