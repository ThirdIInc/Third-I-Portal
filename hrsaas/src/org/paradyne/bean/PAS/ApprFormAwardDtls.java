package org.paradyne.bean.PAS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class ApprFormAwardDtls extends BeanBase {
	
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
	private String awdCode;
	private String awdCodeComment;
	private String awdDesc;
	private String awdDate;
	private String awdIssAuth;
	private String awdComments;
	private String removeAwdCode;
	private String removeNomCode;
	private String sectionId;
	private String awdId;
	private String award;
	private String reason;
	private String hAward;
	private String hReason;
	private String flag;
	
	private String nominateFlag="false";
	private String reasonFlag="false";
	
	private ArrayList awardList;
	private ArrayList nomList;
		
	
	
	public String getRemoveNomCode() {
		return removeNomCode;
	}

	public void setRemoveNomCode(String removeNomCode) {
		this.removeNomCode = removeNomCode;
	}

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

	public String getNominateFlag() {
		return nominateFlag;
	}

	public void setNominateFlag(String nominateFlag) {
		this.nominateFlag = nominateFlag;
	}

	public String getReasonFlag() {
		return reasonFlag;
	}

	public void setReasonFlag(String reasonFlag) {
		this.reasonFlag = reasonFlag;
	}

	public String getHAward() {
		return hAward;
	}

	public void setHAward(String award) {
		hAward = award;
	}

	public String getHReason() {
		return hReason;
	}

	public void setHReason(String reason) {
		hReason = reason;
	}

	public String getAwdId() {
		return awdId;
	}

	public void setAwdId(String awdId) {
		this.awdId = awdId;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ArrayList getNomList() {
		return nomList;
	}

	public void setNomList(ArrayList nomList) {
		this.nomList = nomList;
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

	public String getCommentFlag() {
		return commentFlag;
	}

	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}

	public String getAwdCode() {
		return awdCode;
	}

	public void setAwdCode(String awdCode) {
		this.awdCode = awdCode;
	}

	public String getAwdDesc() {
		return awdDesc;
	}

	public void setAwdDesc(String awdDesc) {
		this.awdDesc = awdDesc;
	}

	public String getAwdDate() {
		return awdDate;
	}

	public void setAwdDate(String awdDate) {
		this.awdDate = awdDate;
	}

	public String getAwdIssAuth() {
		return awdIssAuth;
	}

	public void setAwdIssAuth(String awdIssAuth) {
		this.awdIssAuth = awdIssAuth;
	}

	public String getAwdComments() {
		return awdComments;
	}

	public void setAwdComments(String awdComments) {
		this.awdComments = awdComments;
	}

	public String getRemoveAwdCode() {
		return removeAwdCode;
	}

	public void setRemoveAwdCode(String removeAwdCode) {
		this.removeAwdCode = removeAwdCode;
	}

	public ArrayList getAwardList() {
		return awardList;
	}

	public void setAwardList(ArrayList awardList) {
		this.awardList = awardList;
	}

	public String getAwdCodeComment() {
		return awdCodeComment;
	}

	public void setAwdCodeComment(String awdCodeComment) {
		this.awdCodeComment = awdCodeComment;
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
