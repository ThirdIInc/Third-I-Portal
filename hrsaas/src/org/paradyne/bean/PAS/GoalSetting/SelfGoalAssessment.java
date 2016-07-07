package org.paradyne.bean.PAS.GoalSetting;

import java.util.ArrayList;
import java.util.TreeMap;
import org.paradyne.lib.BeanBase;

/**@modified by prajakta.bhandare
 * @date 15 May 2013
 *
 */
public class SelfGoalAssessment extends BeanBase
{
  TreeMap tmap;
  private ArrayList Approverlst = null;
  private ArrayList reassessmentList;
  private String revieweWeight = "";
  private String ratingrangedesc = "";
  private String approverToken = "";
  private String approverName = "";
  private String acceptFlag = "";
  private String employeecomment = "";
  private String reviewercomment = "";
  private String hiddenApproverId = "";
  private String esclationworkflowFlag = "false";
  private String rejectFlag = "";
  private String hiddenacceptflag = "";
  private String hiddenempid = "";
  private String hiddenlevel = "";
  private String hiddenassessmentId = "";
  public String confgoalId;
  public String goalId;
  public String goalName;
  public String goalAssessmentId;
  public String scheduledAssessmentDate;
  public String scheduledAssessmentDateList;
  public String completedAssessmentDate;
  public String empId;
  public String empName;
  public String empToken;
  public String empBrn;
  public String empDesg;
  public String empDept;
  public String empReportingTo;
  public String empDoj;
  public String listType;
  public String reportingType = "";
  public String assessLevel;
  public String individualGoalId;
  public String goalDescription;
  public String goalAchDate;
  public String goalDtlStatus;
  public String goalRating;
  public String comment;
  public String isRecordSaved;
  private ArrayList dataList;
  private ArrayList processedList;
  private ArrayList goalList;
  private String myPage;
  private String show;
  private String sysDate;
  private String noData = "false";
  private String isGoalCategory = "false";
  private String isGoalAchievedFlag = "false";
  private String goalCategoryCodeList = "";
  private String goalCategoryNameList = "";
  private String goalAchievedList = "";

  private ArrayList managerRatingList = null;
  private String managerRating;
  private String managerComments;
  private String managerName;
  private String appComments = "";
  private String appWeightage = "";
  private String signOffStatus = "";
  private String source = "";
  
  //Added by Prajakta B
  private String appCommentsAbbr="";
  private String  goalDescriptionAbbr="";
  //Ends Added by Prajakta B

  /**
 * @return appCommentsAbbr
 */
public String getAppCommentsAbbr() {
	return appCommentsAbbr;
}

/**
 * @param appCommentsAbbr
 * the appCommentsAbbr to set
 */
public void setAppCommentsAbbr(String appCommentsAbbr) {
	this.appCommentsAbbr = appCommentsAbbr;
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

public String getSource() {
    return this.source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getAppComments() {
    return this.appComments;
  }

  public void setAppComments(String appComments) {
    this.appComments = appComments;
  }

  public String getAppWeightage() {
    return this.appWeightage;
  }

  public void setAppWeightage(String appWeightage) {
    this.appWeightage = appWeightage;
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

  public String getGoalAssessmentId() {
    return this.goalAssessmentId;
  }

  public void setGoalAssessmentId(String goalAssessmentId) {
    this.goalAssessmentId = goalAssessmentId;
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

  public String getListType() {
    return this.listType;
  }

  public void setListType(String listType) {
    this.listType = listType;
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

  public String getIsRecordSaved() {
    return this.isRecordSaved;
  }

  public void setIsRecordSaved(String isRecordSaved) {
    this.isRecordSaved = isRecordSaved;
  }

  public ArrayList getDataList() {
    return this.dataList;
  }

  public void setDataList(ArrayList dataList) {
    this.dataList = dataList;
  }

  public ArrayList getProcessedList() {
    return this.processedList;
  }

  public void setProcessedList(ArrayList processedList) {
    this.processedList = processedList;
  }

  public ArrayList getGoalList() {
    return this.goalList;
  }

  public void setGoalList(ArrayList goalList) {
    this.goalList = goalList;
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

  public String getGoalDtlStatus() {
    return this.goalDtlStatus;
  }

  public void setGoalDtlStatus(String goalDtlStatus) {
    this.goalDtlStatus = goalDtlStatus;
  }

  public String getScheduledAssessmentDateList() {
    return this.scheduledAssessmentDateList;
  }

  public void setScheduledAssessmentDateList(String scheduledAssessmentDateList) {
    this.scheduledAssessmentDateList = scheduledAssessmentDateList;
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

  public String getManagerName() {
    return this.managerName;
  }

  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }

  public String getSysDate() {
    return this.sysDate;
  }

  public void setSysDate(String sysDate) {
    this.sysDate = sysDate;
  }

  public String getSignOffStatus()
  {
    return this.signOffStatus;
  }

  public void setSignOffStatus(String signOffStatus)
  {
    this.signOffStatus = signOffStatus;
  }

  public TreeMap getTmap() {
    return this.tmap;
  }

  public void setTmap(TreeMap tmap) {
    this.tmap = tmap;
  }

  public String getConfgoalId() {
    return this.confgoalId;
  }

  public void setConfgoalId(String confgoalId) {
    this.confgoalId = confgoalId;
  }

  public ArrayList getApproverlst() {
    return this.Approverlst;
  }

  public void setApproverlst(ArrayList approverlst) {
    this.Approverlst = approverlst;
  }

  public String getApproverToken() {
    return this.approverToken;
  }

  public void setApproverToken(String approverToken) {
    this.approverToken = approverToken;
  }

  public String getApproverName() {
    return this.approverName;
  }

  public void setApproverName(String approverName) {
    this.approverName = approverName;
  }

  public String getAcceptFlag() {
    return this.acceptFlag;
  }

  public void setAcceptFlag(String acceptFlag) {
    this.acceptFlag = acceptFlag;
  }

  public String getEmployeecomment() {
    return this.employeecomment;
  }

  public void setEmployeecomment(String employeecomment) {
    this.employeecomment = employeecomment;
  }

  public String getReviewercomment() {
    return this.reviewercomment;
  }

  public void setReviewercomment(String reviewercomment) {
    this.reviewercomment = reviewercomment;
  }

  public String getHiddenApproverId() {
    return this.hiddenApproverId;
  }

  public void setHiddenApproverId(String hiddenApproverId) {
    this.hiddenApproverId = hiddenApproverId;
  }

  public String getEsclationworkflowFlag() {
    return this.esclationworkflowFlag;
  }

  public void setEsclationworkflowFlag(String esclationworkflowFlag) {
    this.esclationworkflowFlag = esclationworkflowFlag;
  }

  public String getRejectFlag() {
    return this.rejectFlag;
  }

  public void setRejectFlag(String rejectFlag) {
    this.rejectFlag = rejectFlag;
  }

  public String getHiddenacceptflag() {
    return this.hiddenacceptflag;
  }

  public void setHiddenacceptflag(String hiddenacceptflag) {
    this.hiddenacceptflag = hiddenacceptflag;
  }

  public String getHiddenempid() {
    return this.hiddenempid;
  }

  public void setHiddenempid(String hiddenempid) {
    this.hiddenempid = hiddenempid;
  }

  public String getHiddenlevel() {
    return this.hiddenlevel;
  }

  public void setHiddenlevel(String hiddenlevel) {
    this.hiddenlevel = hiddenlevel;
  }

  public String getHiddenassessmentId() {
    return this.hiddenassessmentId;
  }

  public void setHiddenassessmentId(String hiddenassessmentId) {
    this.hiddenassessmentId = hiddenassessmentId;
  }

  public ArrayList getReassessmentList() {
    return this.reassessmentList;
  }

  public void setReassessmentList(ArrayList reassessmentList) {
    this.reassessmentList = reassessmentList;
  }

  public String getRatingrangedesc() {
    return this.ratingrangedesc;
  }

  public void setRatingrangedesc(String ratingrangedesc) {
    this.ratingrangedesc = ratingrangedesc;
  }

  public String getReportingType() {
    return this.reportingType;
  }

  public void setReportingType(String reportingType) {
    this.reportingType = reportingType;
  }

  public String getAssessLevel() {
    return this.assessLevel;
  }

  public void setAssessLevel(String assessLevel) {
    this.assessLevel = assessLevel;
  }

  public String getRevieweWeight() {
    return this.revieweWeight;
  }

  public void setRevieweWeight(String revieweWeight) {
    this.revieweWeight = revieweWeight;
  }
}