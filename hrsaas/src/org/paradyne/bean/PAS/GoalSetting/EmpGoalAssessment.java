package org.paradyne.bean.PAS.GoalSetting;

import java.util.ArrayList;
import java.util.TreeMap;
import org.paradyne.lib.BeanBase;

public class EmpGoalAssessment extends BeanBase
{
  private String confgoalId;
  private ArrayList reassessmentList;
  private String goalCategoryWeightage = "";
  private String hiddengoaldtlid;
  private String hiddenassessmentType = "";
  private String ratingrangedesc = "";
  private String hiddenlevel;
  private String reassessmentlevel;
  TreeMap tmap;
  private String isSignOffRequired = "";
  public String goalId;
  public String goalName;
  public String assessLevel;
  private String assessLevelList;
  public String goalAssessmentId;
  public String reportingType = "";
  public String reportingTypeList = "";
  public String goalRatingAccess;
  public String goalCommentsAccess;
  public String scheduledAssessmentDate;
  public String completedAssessmentDate;
  private String applicantComments = "";
  private String weightage = "";
  private String selfRating = "";
  private String selfComments = "";
  public String goalDtlStatus;
  public String empId;
  public String empName;
  public String empToken;
  public String empBrn;
  public String empDesg;
  public String empDept;
  public String empReportingTo;
  public String empDoj;
  private String isGoalCategory = "false";
  private String isGoalAchievedFlag = "false";
  private String goalAchievedList = "";
  private String goalCategoryCodeList = "";
  private String goalCategoryNameList = "";
  private String finalRating;
  public String listType;
  public String individualGoalId;
  public String goalDescription;
  public String goalAchDate;
  public String goalRating;
  public String comment;
  public String isRecordSaved;
  private String managerName;
  private ArrayList dataList;
  private ArrayList processedList;
  private ArrayList goalList;
  private String myPage;
  private String show;
  private String divScoreDet = "false";

  private String noData = "false";

  private ArrayList managerRatingList = null;
  private String managerRating;
  private String managerComments;
  private String accessorFlag = "false";
  private String source = "";

  //Added by Prajakta B
  private String goalCategoryNameListAbbr="";
  private String goalDescriptionAbbr="";
  private String selfCommentsAbbr="";
  private String applicantCommentsAbbr="";
  private String goalAchievedListAbbr="";
  private String managerCommentsAbbr="";
  private String commentAbbr="";
  
  /**
 * @return commentAbbr
 */
public String getCommentAbbr() {
	return commentAbbr;
}
/**
 * @param commentAbbr
 * the commentAbbr to set
 */
public void setCommentAbbr(String commentAbbr) {
	this.commentAbbr = commentAbbr;
}
//Ends added by Prajakta B
  public String getSource() {
    return this.source;
  }
  public void setSource(String source) {
    this.source = source;
  }
  public ArrayList getDataList() {
    return this.dataList;
  }
  public void setDataList(ArrayList dataList) {
    this.dataList = dataList;
  }
  public String getMyPage() {
    return this.myPage;
  }
  public void setMyPage(String myPage) {
    this.myPage = myPage;
  }
  public String getShow() {
    return this.show;
  }
  public void setShow(String show) {
    this.show = show;
  }
  public String getNoData() {
    return this.noData;
  }
  public void setNoData(String noData) {
    this.noData = noData;
  }
  public String getGoalId() {
    return this.goalId;
  }
  public void setGoalId(String goalId) {
    this.goalId = goalId;
  }
  public String getGoalName() {
    return this.goalName;
  }
  public void setGoalName(String goalName) {
    this.goalName = goalName;
  }
  public String getEmpId() {
    return this.empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return this.empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getEmpDesg() {
    return this.empDesg;
  }
  public void setEmpDesg(String empDesg) {
    this.empDesg = empDesg;
  }
  public String getEmpDept() {
    return this.empDept;
  }
  public void setEmpDept(String empDept) {
    this.empDept = empDept;
  }
  public String getListType() {
    return this.listType;
  }
  public void setListType(String listType) {
    this.listType = listType;
  }
  public String getScheduledAssessmentDate() {
    return this.scheduledAssessmentDate;
  }
  public void setScheduledAssessmentDate(String scheduledAssessmentDate) {
    this.scheduledAssessmentDate = scheduledAssessmentDate;
  }
  public String getCompletedAssessmentDate() {
    return this.completedAssessmentDate;
  }
  public void setCompletedAssessmentDate(String completedAssessmentDate) {
    this.completedAssessmentDate = completedAssessmentDate;
  }
  public ArrayList getProcessedList() {
    return this.processedList;
  }
  public void setProcessedList(ArrayList processedList) {
    this.processedList = processedList;
  }
  public String getIndividualGoalId() {
    return this.individualGoalId;
  }
  public void setIndividualGoalId(String individualGoalId) {
    this.individualGoalId = individualGoalId;
  }
  public String getGoalDescription() {
    return this.goalDescription;
  }
  public void setGoalDescription(String goalDescription) {
    this.goalDescription = goalDescription;
  }
  public String getGoalAchDate() {
    return this.goalAchDate;
  }
  public void setGoalAchDate(String goalAchDate) {
    this.goalAchDate = goalAchDate;
  }
  public String getGoalRating() {
    return this.goalRating;
  }
  public void setGoalRating(String goalRating) {
    this.goalRating = goalRating;
  }
  public String getComment() {
    return this.comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
  public ArrayList getGoalList() {
    return this.goalList;
  }
  public void setGoalList(ArrayList goalList) {
    this.goalList = goalList;
  }
  public String getEmpToken() {
    return this.empToken;
  }
  public void setEmpToken(String empToken) {
    this.empToken = empToken;
  }
  public String getEmpBrn() {
    return this.empBrn;
  }
  public void setEmpBrn(String empBrn) {
    this.empBrn = empBrn;
  }
  public String getEmpReportingTo() {
    return this.empReportingTo;
  }
  public void setEmpReportingTo(String empReportingTo) {
    this.empReportingTo = empReportingTo;
  }
  public String getEmpDoj() {
    return this.empDoj;
  }
  public void setEmpDoj(String empDoj) {
    this.empDoj = empDoj;
  }
  public String getGoalAssessmentId() {
    return this.goalAssessmentId;
  }
  public void setGoalAssessmentId(String goalAssessmentId) {
    this.goalAssessmentId = goalAssessmentId;
  }
  public String getIsRecordSaved() {
    return this.isRecordSaved;
  }
  public void setIsRecordSaved(String isRecordSaved) {
    this.isRecordSaved = isRecordSaved;
  }
  public String getSelfRating() {
    return this.selfRating;
  }
  public void setSelfRating(String selfRating) {
    this.selfRating = selfRating;
  }
  public String getSelfComments() {
    return this.selfComments;
  }
  public void setSelfComments(String selfComments) {
    this.selfComments = selfComments;
  }
  public String getApplicantComments() {
    return this.applicantComments;
  }
  public void setApplicantComments(String applicantComments) {
    this.applicantComments = applicantComments;
  }
  public String getWeightage() {
    return this.weightage;
  }
  public void setWeightage(String weightage) {
    this.weightage = weightage;
  }
  public String getAssessLevel() {
    return this.assessLevel;
  }
  public void setAssessLevel(String assessLevel) {
    this.assessLevel = assessLevel;
  }
  public String getAssessLevelList() {
    return this.assessLevelList;
  }
  public void setAssessLevelList(String assessLevelList) {
    this.assessLevelList = assessLevelList;
  }
  public String getGoalRatingAccess() {
    return this.goalRatingAccess;
  }
  public void setGoalRatingAccess(String goalRatingAccess) {
    this.goalRatingAccess = goalRatingAccess;
  }
  public String getGoalCommentsAccess() {
    return this.goalCommentsAccess;
  }
  public void setGoalCommentsAccess(String goalCommentsAccess) {
    this.goalCommentsAccess = goalCommentsAccess;
  }
  public String getReportingType() {
    return this.reportingType;
  }
  public void setReportingType(String reportingType) {
    this.reportingType = reportingType;
  }
  public String getReportingTypeList() {
    return this.reportingTypeList;
  }
  public void setReportingTypeList(String reportingTypeList) {
    this.reportingTypeList = reportingTypeList;
  }
  public String getGoalDtlStatus() {
    return this.goalDtlStatus;
  }
  public void setGoalDtlStatus(String goalDtlStatus) {
    this.goalDtlStatus = goalDtlStatus;
  }
  public String getIsGoalCategory() {
    return this.isGoalCategory;
  }
  public void setIsGoalCategory(String isGoalCategory) {
    this.isGoalCategory = isGoalCategory;
  }
  public String getGoalCategoryCodeList() {
    return this.goalCategoryCodeList;
  }
  public void setGoalCategoryCodeList(String goalCategoryCodeList) {
    this.goalCategoryCodeList = goalCategoryCodeList;
  }
  public String getGoalCategoryNameList() {
    return this.goalCategoryNameList;
  }
  public void setGoalCategoryNameList(String goalCategoryNameList) {
    this.goalCategoryNameList = goalCategoryNameList;
  }
  public String getIsGoalAchievedFlag() {
    return this.isGoalAchievedFlag;
  }
  public void setIsGoalAchievedFlag(String isGoalAchievedFlag) {
    this.isGoalAchievedFlag = isGoalAchievedFlag;
  }
  public String getGoalAchievedList() {
    return this.goalAchievedList;
  }
  public void setGoalAchievedList(String goalAchievedList) {
    this.goalAchievedList = goalAchievedList;
  }
  public ArrayList getManagerRatingList() {
    return this.managerRatingList;
  }
  public void setManagerRatingList(ArrayList managerRatingList) {
    this.managerRatingList = managerRatingList;
  }
  public String getManagerRating() {
    return this.managerRating;
  }
  public void setManagerRating(String managerRating) {
    this.managerRating = managerRating;
  }
  public String getManagerComments() {
    return this.managerComments;
  }
  public void setManagerComments(String managerComments) {
    this.managerComments = managerComments;
  }
  public String getAccessorFlag() {
    return this.accessorFlag;
  }
  public void setAccessorFlag(String accessorFlag) {
    this.accessorFlag = accessorFlag;
  }
  public String getManagerName() {
    return this.managerName;
  }
  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }
  public String getIsSignOffRequired() {
    return this.isSignOffRequired;
  }
  public void setIsSignOffRequired(String isSignOffRequired) {
    this.isSignOffRequired = isSignOffRequired;
  }
  public String getConfgoalId() {
    return this.confgoalId;
  }
  public void setConfgoalId(String confgoalId) {
    this.confgoalId = confgoalId;
  }
  public TreeMap getTmap() {
    return this.tmap;
  }
  public void setTmap(TreeMap tmap) {
    this.tmap = tmap;
  }
  public ArrayList getReassessmentList() {
    return this.reassessmentList;
  }
  public void setReassessmentList(ArrayList reassessmentList) {
    this.reassessmentList = reassessmentList;
  }
  public String getHiddenassessmentType() {
    return this.hiddenassessmentType;
  }
  public void setHiddenassessmentType(String hiddenassessmentType) {
    this.hiddenassessmentType = hiddenassessmentType;
  }
  public String getHiddenlevel() {
    return this.hiddenlevel;
  }
  public void setHiddenlevel(String hiddenlevel) {
    this.hiddenlevel = hiddenlevel;
  }
  public String getReassessmentlevel() {
    return this.reassessmentlevel;
  }
  public void setReassessmentlevel(String reassessmentlevel) {
    this.reassessmentlevel = reassessmentlevel;
  }
  public String getRatingrangedesc() {
    return this.ratingrangedesc;
  }
  public void setRatingrangedesc(String ratingrangedesc) {
    this.ratingrangedesc = ratingrangedesc;
  }
  public String getHiddengoaldtlid() {
    return this.hiddengoaldtlid;
  }
  public void setHiddengoaldtlid(String hiddengoaldtlid) {
    this.hiddengoaldtlid = hiddengoaldtlid;
  }
  public String getGoalCategoryWeightage() {
    return this.goalCategoryWeightage;
  }
  public void setGoalCategoryWeightage(String goalCategoryWeightage) {
    this.goalCategoryWeightage = goalCategoryWeightage;
  }
  public String getDivScoreDet() {
    return this.divScoreDet;
  }
  public void setDivScoreDet(String divScoreDet) {
    this.divScoreDet = divScoreDet;
  }
  public String getFinalRating() {
    return this.finalRating;
  }
  public void setFinalRating(String finalRating) {
    this.finalRating = finalRating;
  }
/**
 * @return goalCategoryNameListAbbr
 */
public String getGoalCategoryNameListAbbr() {
	return goalCategoryNameListAbbr;
}
/**
 * @param goalCategoryNameListAbbr
 * the goalCategoryNameListAbbr to set
 */
public void setGoalCategoryNameListAbbr(String goalCategoryNameListAbbr) {
	this.goalCategoryNameListAbbr = goalCategoryNameListAbbr;
}
/**
 * @return goalDescriptionAbbr
 */
public String getGoalDescriptionAbbr() {
	return goalDescriptionAbbr;
}
/**
 * @param goalDescriptionAbbr
 * the goalDescriptionAbbr to set
 */
public void setGoalDescriptionAbbr(String goalDescriptionAbbr) {
	this.goalDescriptionAbbr = goalDescriptionAbbr;
}
/**
 * @return selfCommentsAbbr
 */
public String getSelfCommentsAbbr() {
	return selfCommentsAbbr;
}
/**
 * @param selfCommentsAbbr
 * the selfCommentsAbbr to set
 */
public void setSelfCommentsAbbr(String selfCommentsAbbr) {
	this.selfCommentsAbbr = selfCommentsAbbr;
}
/**
 * @return applicantCommentsAbbr
 */
public String getApplicantCommentsAbbr() {
	return applicantCommentsAbbr;
}
/**
 * @param applicantCommentsAbbr
 * the applicantCommentsAbbr to set
 */
public void setApplicantCommentsAbbr(String applicantCommentsAbbr) {
	this.applicantCommentsAbbr = applicantCommentsAbbr;
}
/**
 * @return goalAchievedListAbbr
 */ 
public String getGoalAchievedListAbbr() {
	return goalAchievedListAbbr;
}
/**
 * @param goalAchievedListAbbr
 * the goalAchievedListAbbr to set
 */
public void setGoalAchievedListAbbr(String goalAchievedListAbbr) {
	this.goalAchievedListAbbr = goalAchievedListAbbr;
}
/**
 * @return managerCommentsAbbr
 */
public String getManagerCommentsAbbr() {
	return managerCommentsAbbr;
}
/**
 * @param managerCommentsAbbr
 * the managerCommentsAbbr to set
 */
public void setManagerCommentsAbbr(String managerCommentsAbbr) {
	this.managerCommentsAbbr = managerCommentsAbbr;
}
}