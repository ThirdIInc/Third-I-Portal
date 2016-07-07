package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ApprFormDiscpMeasures extends BeanBase {
	
	private String mode;
	
	//common bean variables for navigation
	private String sourceFormType="";
	private String source="";
	private String goalFinalizeFlag="false";
	private String compFinalizeFlag="false";
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
	private String commentFlag="false";
	private String sectionId;	
	private String discpId;
	private String discpIdComment;
	private String discpAction;
	private String discpAuth;
	private String discpDate;
	private String discpComments;
	private String removeDiscpCode;
	private String flag;
	
	private ArrayList discpList;

	
	
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

	public String getDiscpIdComment() {
		return discpIdComment;
	}

	public void setDiscpIdComment(String discpIdComment) {
		this.discpIdComment = discpIdComment;
	}

	public String getRemoveDiscpCode() {
		return removeDiscpCode;
	}

	public void setRemoveDiscpCode(String removeDiscpCode) {
		this.removeDiscpCode = removeDiscpCode;
	}

	public String getDiscpId() {
		return discpId;
	}

	public void setDiscpId(String discpId) {
		this.discpId = discpId;
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

	public String getDiscpAction() {
		return discpAction;
	}

	public void setDiscpAction(String discpAction) {
		this.discpAction = discpAction;
	}

	public String getDiscpAuth() {
		return discpAuth;
	}

	public void setDiscpAuth(String discpAuth) {
		this.discpAuth = discpAuth;
	}

	public String getDiscpDate() {
		return discpDate;
	}

	public void setDiscpDate(String discpDate) {
		this.discpDate = discpDate;
	}

	public String getDiscpComments() {
		return discpComments;
	}

	public void setDiscpComments(String discpComments) {
		this.discpComments = discpComments;
	}



	public ArrayList getDiscpList() {
		return discpList;
	}

	public void setDiscpList(ArrayList discpList) {
		this.discpList = discpList;
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
