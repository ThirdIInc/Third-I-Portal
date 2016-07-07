/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author Pankaj_Jain
 *
 */
public class ConductInterview extends BeanBase {
 private String jobDesc;
 private String rolesResponsibility;
 private String requisitionCode;
 private String requisitionName;
 private String positionCode;
 private String position;
 private String hiringManagerCode;
 private String division;
 private String divisionCode;
 private String department;
 private String deptCode;
 private String branch;
 private String branchCode;
 private String candName;
 private String candCode;
 private String intRound;
 private String intTime;
 private String intDate;
 private String qlfDts;
 private String techSkills;
 private String commLevel;
 private String mgmtSkills;
 private String personality;
 private String lrngSkills;
 private String rlvntExp;
 private String sutAbility;
 private String evalScore;
 private String percentage;
 private String currentCTC;
 private String readyReloc;
 private String negoCTC;
 private String intrStatus;
 private String exptdJoinDate;
 private String empType;
 private String empTypeCode;
 private String constraints;
 private String comments;
 private String checkFlag = "false";
 private String selectInter;
 private String selectInterId = "";
 private String makeOffer;
 private String fwdNxtRnd;
 private String nxtRoundNo = "";
 private String intCode;
 private String intDtlCode;
 private String bckToIntrFlag="false";
 private String addNewFlag="false";
 private String hiringManager;
 
 private String recruiterId;
 private String recruiterToken;
 private String recruiterName;
 
 private String weakness ="";
 private String strength ="";
 
 TreeMap<String, String> tmap =null ;
 private String candidateEvaluationCode = "";
 private String interviewDetailCode = "";
 
 private String onHoldInterviewFlag = "false";
 private String onHoldCalculation = "intialData";
 
 
 private String groupId ="";
 private String groupDesc ="";
 private String groupAbbr ="";
 private String groupName ="";
 private boolean afterGroupSelectionFlag;
 private boolean parameterMappedOrNotFlag;
 
public String getOnHoldCalculation() {
	return onHoldCalculation;
}
public void setOnHoldCalculation(String onHoldCalculation) {
	this.onHoldCalculation = onHoldCalculation;
}
public String getOnHoldInterviewFlag() {
	return onHoldInterviewFlag;
}
public void setOnHoldInterviewFlag(String onHoldInterviewFlag) {
	this.onHoldInterviewFlag = onHoldInterviewFlag;
}
public String getCandidateEvaluationCode() {
	return candidateEvaluationCode;
}
public void setCandidateEvaluationCode(String candidateEvaluationCode) {
	this.candidateEvaluationCode = candidateEvaluationCode;
}
public String getInterviewDetailCode() {
	return interviewDetailCode;
}
public void setInterviewDetailCode(String interviewDetailCode) {
	this.interviewDetailCode = interviewDetailCode;
}
public String getWeakness() {
	return weakness;
}
public void setWeakness(String weakness) {
	this.weakness = weakness;
}
public String getStrength() {
	return strength;
}
public void setStrength(String strength) {
	this.strength = strength;
}
public String getRecruiterId() {
	return recruiterId;
}
public void setRecruiterId(String recruiterId) {
	this.recruiterId = recruiterId;
}
public String getRecruiterToken() {
	return recruiterToken;
}
public void setRecruiterToken(String recruiterToken) {
	this.recruiterToken = recruiterToken;
}
public String getRecruiterName() {
	return recruiterName;
}
public void setRecruiterName(String recruiterName) {
	this.recruiterName = recruiterName;
}
public String getHiringManager() {
	return hiringManager;
}
public void setHiringManager(String hiringManager) {
	this.hiringManager = hiringManager;
}
public String getBckToIntrFlag() {
	return bckToIntrFlag;
}
public void setBckToIntrFlag(String bckToIntrFlag) {
	this.bckToIntrFlag = bckToIntrFlag;
}
public String getIntCode() {
	return intCode;
}
public void setIntCode(String intCode) {
	this.intCode = intCode;
}
public String getIntDtlCode() {
	return intDtlCode;
}
public void setIntDtlCode(String intDtlCode) {
	this.intDtlCode = intDtlCode;
}
public String getMakeOffer() {
	return makeOffer;
}
public void setMakeOffer(String makeOffer) {
	this.makeOffer = makeOffer;
}
public String getFwdNxtRnd() {
	return fwdNxtRnd;
}
public void setFwdNxtRnd(String fwdNxtRnd) {
	this.fwdNxtRnd = fwdNxtRnd;
}
public String getNxtRoundNo() {
	return nxtRoundNo;
}
public void setNxtRoundNo(String nxtRoundNo) {
	this.nxtRoundNo = nxtRoundNo;
}
public String getSelectInter() {
	return selectInter;
}
public void setSelectInter(String selectInter) {
	this.selectInter = selectInter;
}
public String getSelectInterId() {
	return selectInterId;
}
public void setSelectInterId(String selectInterId) {
	this.selectInterId = selectInterId;
}
public String getCheckFlag() {
	return checkFlag;
}
public void setCheckFlag(String checkFlag) {
	this.checkFlag = checkFlag;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}

public String getDivision() {
	return division;
}
public void setDivision(String division) {
	this.division = division;
}

public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}

public String getBranch() {
	return branch;
}
public void setBranch(String branch) {
	this.branch = branch;
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
public String getIntRound() {
	return intRound;
}
public void setIntRound(String intRound) {
	this.intRound = intRound;
}
public String getIntTime() {
	return intTime;
}
public void setIntTime(String intTime) {
	this.intTime = intTime;
}
public String getIntDate() {
	return intDate;
}
public void setIntDate(String intDate) {
	this.intDate = intDate;
}
public String getQlfDts() {
	return qlfDts;
}
public void setQlfDts(String qlfDts) {
	this.qlfDts = qlfDts;
}
public String getTechSkills() {
	return techSkills;
}
public void setTechSkills(String techSkills) {
	this.techSkills = techSkills;
}
public String getCommLevel() {
	return commLevel;
}
public void setCommLevel(String commLevel) {
	this.commLevel = commLevel;
}
public String getMgmtSkills() {
	return mgmtSkills;
}
public void setMgmtSkills(String mgmtSkills) {
	this.mgmtSkills = mgmtSkills;
}
public String getPersonality() {
	return personality;
}
public void setPersonality(String personality) {
	this.personality = personality;
}
public String getLrngSkills() {
	return lrngSkills;
}
public void setLrngSkills(String lrngSkills) {
	this.lrngSkills = lrngSkills;
}
public String getRlvntExp() {
	return rlvntExp;
}
public void setRlvntExp(String rlvntExp) {
	this.rlvntExp = rlvntExp;
}
public String getSutAbility() {
	return sutAbility;
}
public void setSutAbility(String sutAbility) {
	this.sutAbility = sutAbility;
}
public String getEvalScore() {
	return evalScore;
}
public void setEvalScore(String evalScore) {
	this.evalScore = evalScore;
}
public String getPercentage() {
	return percentage;
}
public void setPercentage(String percentage) {
	this.percentage = percentage;
}
public String getCurrentCTC() {
	return currentCTC;
}
public void setCurrentCTC(String currentCTC) {
	this.currentCTC = currentCTC;
}
public String getReadyReloc() {
	return readyReloc;
}
public void setReadyReloc(String readyReloc) {
	this.readyReloc = readyReloc;
}
public String getNegoCTC() {
	return negoCTC;
}
public void setNegoCTC(String negoCTC) {
	this.negoCTC = negoCTC;
}
public String getIntrStatus() {
	return intrStatus;
}
public void setIntrStatus(String intrStatus) {
	this.intrStatus = intrStatus;
}
public String getExptdJoinDate() {
	return exptdJoinDate;
}
public void setExptdJoinDate(String exptdJoinDate) {
	this.exptdJoinDate = exptdJoinDate;
}
public String getEmpType() {
	return empType;
}
public void setEmpType(String empType) {
	this.empType = empType;
}
public String getConstraints() {
	return constraints;
}
public void setConstraints(String constraints) {
	this.constraints = constraints;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}
public String getEmpTypeCode() {
	return empTypeCode;
}
public void setEmpTypeCode(String empTypeCode) {
	this.empTypeCode = empTypeCode;
}
public String getRequisitionCode() {
	return requisitionCode;
}
public void setRequisitionCode(String requisitionCode) {
	this.requisitionCode = requisitionCode;
}
public String getRequisitionName() {
	return requisitionName;
}
public void setRequisitionName(String requisitionName) {
	this.requisitionName = requisitionName;
}
public String getAddNewFlag() {
	return addNewFlag;
}
public void setAddNewFlag(String addNewFlag) {
	this.addNewFlag = addNewFlag;
}
/**
 * @return the jobDesc
 */
public String getJobDesc() {
	return jobDesc;
}
/**
 * @param jobDesc the jobDesc to set
 */
public void setJobDesc(String jobDesc) {
	this.jobDesc = jobDesc;
}
/**
 * @return the rolesResponsibility
 */
public String getRolesResponsibility() {
	return rolesResponsibility;
}
/**
 * @param rolesResponsibility the rolesResponsibility to set
 */
public void setRolesResponsibility(String rolesResponsibility) {
	this.rolesResponsibility = rolesResponsibility;
}
/**
 * @return the hiringManagerCode
 */
public String getHiringManagerCode() {
	return hiringManagerCode;
}
/**
 * @param hiringManagerCode the hiringManagerCode to set
 */
public void setHiringManagerCode(String hiringManagerCode) {
	this.hiringManagerCode = hiringManagerCode;
}
public String getPositionCode() {
	return positionCode;
}
public void setPositionCode(String positionCode) {
	this.positionCode = positionCode;
}
public String getDivisionCode() {
	return divisionCode;
}
public void setDivisionCode(String divisionCode) {
	this.divisionCode = divisionCode;
}
public String getDeptCode() {
	return deptCode;
}
public void setDeptCode(String deptCode) {
	this.deptCode = deptCode;
}
public String getBranchCode() {
	return branchCode;
}
public void setBranchCode(String branchCode) {
	this.branchCode = branchCode;
}
public TreeMap<String, String> getTmap() {
	return tmap;
}
public void setTmap(TreeMap<String, String> tmap) {
	this.tmap = tmap;
}
/**
 * @return the groupId
 */
public final String getGroupId() {
	return this.groupId;
}
/**
 * @param groupId the groupId to set
 */
public final void setGroupId(String groupId) {
	this.groupId = groupId;
}
/**
 * @return the groupDesc
 */
public final String getGroupDesc() {
	return this.groupDesc;
}
/**
 * @param groupDesc the groupDesc to set
 */
public final void setGroupDesc(String groupDesc) {
	this.groupDesc = groupDesc;
}
/**
 * @return the groupAbbr
 */
public final String getGroupAbbr() {
	return this.groupAbbr;
}
/**
 * @param groupAbbr the groupAbbr to set
 */
public final void setGroupAbbr(String groupAbbr) {
	this.groupAbbr = groupAbbr;
}
/**
 * @return the groupName
 */
public final String getGroupName() {
	return this.groupName;
}
/**
 * @param groupName the groupName to set
 */
public final void setGroupName(String groupName) {
	this.groupName = groupName;
}
/**
 * @return the parameterMappedOrNotFlag
 */
public final boolean isParameterMappedOrNotFlag() {
	return this.parameterMappedOrNotFlag;
}
/**
 * @param parameterMappedOrNotFlag the parameterMappedOrNotFlag to set
 */
public final void setParameterMappedOrNotFlag(boolean parameterMappedOrNotFlag) {
	this.parameterMappedOrNotFlag = parameterMappedOrNotFlag;
}
/**
 * @return the afterGroupSelectionFlag
 */
public final boolean isAfterGroupSelectionFlag() {
	return this.afterGroupSelectionFlag;
}
/**
 * @param afterGroupSelectionFlag the afterGroupSelectionFlag to set
 */
public final void setAfterGroupSelectionFlag(boolean afterGroupSelectionFlag) {
	this.afterGroupSelectionFlag = afterGroupSelectionFlag;
}
  
}
