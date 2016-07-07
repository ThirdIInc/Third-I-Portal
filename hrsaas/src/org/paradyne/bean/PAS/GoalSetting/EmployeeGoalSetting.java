package org.paradyne.bean.PAS.GoalSetting;

import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.lib.BeanBase;

public class EmployeeGoalSetting extends BeanBase
{
  private String source = "";
  private String categoryWeightage = "";
  private String goalCategoryWeightageList = "";
  private String isApprovalGoalClick = "false";
  private String goalWeightage = "";
  private String goalWeightageList = "";
  private String totalWeightage = "0";
  private String listType = "pending";
  private String submitGoalNo;
  private String returnedGoalNo;
  private String approvedGoalNo;
  private String empTokenList;
  private String goalStatus;
  private String level;
  private String reportingType = "";
  private String empNameList;
  private String draftGoalNo;
  private String revisedGoalNo;
  private String empCodeList;
  private String goalFromDateList;
  private String goalToDateList;
  private String goalPeriodList;
  private String empCode;
  private String empToken;
  private String empName;
  private String goalPeriod;
  private String goalFromDate;
  private String goalToDate;
  private String goalDesc;
  private String goalDTLStatus;
  private String goalDTLStatusList;
  private String goalDTLidList;
  private String goalPeriodId;
  private String empGoalId;
  private String goalAchieveDate;
  private String goalDescList;
  private String goalAchieveDateList;
  private String goalCategoryCodeList = "";
  private String goalCategoryNameList = "";
  private String noDraftData = "false";
  private String approveLength = "false";
  private String approvalStatus;
  private String approverComment;
  private String hiddenStatus;
  private String myPage;
  private String isApprovalForm = "false";
  private String isGoalCategory = "false";
  private String goalCategoryCode = "";
  private HashMap<String, String> categoryMap = null;
  private ArrayList apprList;
  private String approverName;
  private String approvedDate;
  private String approveStatus;
  private String approverCommentList;
  private String commentFlag = "false";
  private ArrayList draftList;
  private ArrayList approvedList;
  private ArrayList submitList;
  private ArrayList returnList;
  private ArrayList revisedList;
  private ArrayList empGoalList;
  private String paraId;
  private String goalComments = "";
  private String goalCommentsList = "";
  private String competency;
  private String flag = "";

  public String getCompetency() { return this.competency; }

  public void setCompetency(String competency)
  {
    this.competency = competency;
  }

  public String getGoalComments() {
    return this.goalComments;
  }

  public void setGoalComments(String goalComments) {
    this.goalComments = goalComments;
  }

  public String getParaId() {
    return this.paraId;
  }

  public void setParaId(String paraId) {
    this.paraId = paraId;
  }

  public ArrayList getDraftList() {
    return this.draftList;
  }

  public void setDraftList(ArrayList draftList) {
    this.draftList = draftList;
  }

  public String getListType() {
    return this.listType;
  }

  public void setListType(String listType) {
    this.listType = listType;
  }

  public String getSubmitGoalNo() {
    return this.submitGoalNo;
  }

  public void setSubmitGoalNo(String submitGoalNo) {
    this.submitGoalNo = submitGoalNo;
  }

  public String getEmpTokenList() {
    return this.empTokenList;
  }

  public void setEmpTokenList(String empTokenList) {
    this.empTokenList = empTokenList;
  }

  public String getGoalStatus() {
    return this.goalStatus;
  }

  public void setGoalStatus(String goalStatus) {
    this.goalStatus = goalStatus;
  }

  public String getEmpNameList() {
    return this.empNameList;
  }

  public void setEmpNameList(String empNameList) {
    this.empNameList = empNameList;
  }

  public String getDraftGoalNo() {
    return this.draftGoalNo;
  }

  public void setDraftGoalNo(String draftGoalNo) {
    this.draftGoalNo = draftGoalNo;
  }

  public String getEmpCodeList() {
    return this.empCodeList;
  }

  public void setEmpCodeList(String empCodeList) {
    this.empCodeList = empCodeList;
  }

  public ArrayList getSubmitList() {
    return this.submitList;
  }

  public void setSubmitList(ArrayList submitList) {
    this.submitList = submitList;
  }

  public ArrayList getReturnList() {
    return this.returnList;
  }

  public void setReturnList(ArrayList returnList) {
    this.returnList = returnList;
  }

  public String getNoDraftData() {
    return this.noDraftData;
  }

  public void setNoDraftData(String noDraftData) {
    this.noDraftData = noDraftData;
  }

  public String getGoalFromDate() {
    return this.goalFromDate;
  }

  public void setGoalFromDate(String goalFromDate) {
    this.goalFromDate = goalFromDate;
  }

  public String getGoalToDate() {
    return this.goalToDate;
  }

  public void setGoalToDate(String goalToDate) {
    this.goalToDate = goalToDate;
  }

  public String getGoalPeriodList() {
    return this.goalPeriodList;
  }

  public void setGoalPeriodList(String goalPeriodList) {
    this.goalPeriodList = goalPeriodList;
  }

  public String getGoalFromDateList() {
    return this.goalFromDateList;
  }

  public void setGoalFromDateList(String goalFromDateList) {
    this.goalFromDateList = goalFromDateList;
  }

  public String getGoalToDateList() {
    return this.goalToDateList;
  }

  public void setGoalToDateList(String goalToDateList) {
    this.goalToDateList = goalToDateList;
  }

  public String getEmpCode() {
    return this.empCode;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getEmpToken() {
    return this.empToken;
  }

  public void setEmpToken(String empToken) {
    this.empToken = empToken;
  }

  public String getEmpName() {
    return this.empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getGoalPeriod() {
    return this.goalPeriod;
  }

  public void setGoalPeriod(String goalPeriod) {
    this.goalPeriod = goalPeriod;
  }

  public String getGoalDesc() {
    return this.goalDesc;
  }

  public void setGoalDesc(String goalDesc) {
    this.goalDesc = goalDesc;
  }

  public String getGoalAchieveDate() {
    return this.goalAchieveDate;
  }

  public void setGoalAchieveDate(String goalAchieveDate) {
    this.goalAchieveDate = goalAchieveDate;
  }

  public String getReturnedGoalNo() {
    return this.returnedGoalNo;
  }

  public void setReturnedGoalNo(String returnedGoalNo) {
    this.returnedGoalNo = returnedGoalNo;
  }

  public String getApprovedGoalNo() {
    return this.approvedGoalNo;
  }

  public void setApprovedGoalNo(String approvedGoalNo) {
    this.approvedGoalNo = approvedGoalNo;
  }

  public String getApproveLength() {
    return this.approveLength;
  }

  public void setApproveLength(String approveLength) {
    this.approveLength = approveLength;
  }

  public ArrayList getApprovedList() {
    return this.approvedList;
  }

  public void setApprovedList(ArrayList approvedList) {
    this.approvedList = approvedList;
  }

  public String getGoalPeriodId() {
    return this.goalPeriodId;
  }

  public void setGoalPeriodId(String goalPeriodId) {
    this.goalPeriodId = goalPeriodId;
  }

  public String getGoalDescList() {
    return this.goalDescList;
  }

  public void setGoalDescList(String goalDescList) {
    this.goalDescList = goalDescList;
  }

  public String getGoalAchieveDateList() {
    return this.goalAchieveDateList;
  }

  public void setGoalAchieveDateList(String goalAchieveDateList) {
    this.goalAchieveDateList = goalAchieveDateList;
  }

  public ArrayList getEmpGoalList() {
    return this.empGoalList;
  }

  public void setEmpGoalList(ArrayList empGoalList) {
    this.empGoalList = empGoalList;
  }

  public String getEmpGoalId() {
    return this.empGoalId;
  }

  public void setEmpGoalId(String empGoalId) {
    this.empGoalId = empGoalId;
  }

  public String getApprovalStatus() {
    return this.approvalStatus;
  }

  public void setApprovalStatus(String approvalStatus) {
    this.approvalStatus = approvalStatus;
  }

  public String getHiddenStatus() {
    return this.hiddenStatus;
  }

  public void setHiddenStatus(String hiddenStatus) {
    this.hiddenStatus = hiddenStatus;
  }

  public String getMyPage() {
    return this.myPage;
  }

  public void setMyPage(String myPage) {
    this.myPage = myPage;
  }

  public ArrayList getApprList() {
    return this.apprList;
  }

  public void setApprList(ArrayList apprList) {
    this.apprList = apprList;
  }

  public String getApproverName() {
    return this.approverName;
  }

  public void setApproverName(String approverName) {
    this.approverName = approverName;
  }

  public String getApprovedDate() {
    return this.approvedDate;
  }

  public void setApprovedDate(String approvedDate) {
    this.approvedDate = approvedDate;
  }

  public String getApproveStatus() {
    return this.approveStatus;
  }

  public void setApproveStatus(String approveStatus) {
    this.approveStatus = approveStatus;
  }

  public String getApproverComment() {
    return this.approverComment;
  }

  public void setApproverComment(String approverComment) {
    this.approverComment = approverComment;
  }

  public String getCommentFlag() {
    return this.commentFlag;
  }

  public void setCommentFlag(String commentFlag) {
    this.commentFlag = commentFlag;
  }

  public String getIsApprovalForm() {
    return this.isApprovalForm;
  }

  public void setIsApprovalForm(String isApprovalForm) {
    this.isApprovalForm = isApprovalForm;
  }

  public String getGoalWeightage() {
    return this.goalWeightage;
  }

  public void setGoalWeightage(String goalWeightage) {
    this.goalWeightage = goalWeightage;
  }

  public String getGoalWeightageList() {
    return this.goalWeightageList;
  }

  public void setGoalWeightageList(String goalWeightageList) {
    this.goalWeightageList = goalWeightageList;
  }

  public String getGoalCommentsList() {
    return this.goalCommentsList;
  }

  public void setGoalCommentsList(String goalCommentsList) {
    this.goalCommentsList = goalCommentsList;
  }

  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getReportingType() {
    return this.reportingType;
  }

  public void setReportingType(String reportingType) {
    this.reportingType = reportingType;
  }

  public String getRevisedGoalNo() {
    return this.revisedGoalNo;
  }

  public void setRevisedGoalNo(String revisedGoalNo) {
    this.revisedGoalNo = revisedGoalNo;
  }

  public ArrayList getRevisedList() {
    return this.revisedList;
  }

  public void setRevisedList(ArrayList revisedList) {
    this.revisedList = revisedList;
  }

  public String getGoalDTLStatus() {
    return this.goalDTLStatus;
  }

  public void setGoalDTLStatus(String goalDTLStatus) {
    this.goalDTLStatus = goalDTLStatus;
  }

  public String getGoalDTLStatusList() {
    return this.goalDTLStatusList;
  }

  public void setGoalDTLStatusList(String goalDTLStatusList) {
    this.goalDTLStatusList = goalDTLStatusList;
  }

  public String getGoalDTLidList() {
    return this.goalDTLidList;
  }

  public void setGoalDTLidList(String goalDTLidList) {
    this.goalDTLidList = goalDTLidList;
  }

  public String getTotalWeightage() {
    return this.totalWeightage;
  }

  public void setTotalWeightage(String totalWeightage) {
    this.totalWeightage = totalWeightage;
  }

  public String getApproverCommentList() {
    return this.approverCommentList;
  }

  public void setApproverCommentList(String approverCommentList) {
    this.approverCommentList = approverCommentList;
  }

  public String getIsGoalCategory() {
    return this.isGoalCategory;
  }

  public void setIsGoalCategory(String isGoalCategory) {
    this.isGoalCategory = isGoalCategory;
  }

  public HashMap<String, String> getCategoryMap() {
    return this.categoryMap;
  }

  public void setCategoryMap(HashMap<String, String> categoryMap) {
    this.categoryMap = categoryMap;
  }

  public String getGoalCategoryCodeList() {
    return this.goalCategoryCodeList;
  }

  public void setGoalCategoryCodeList(String goalCategoryCodeList) {
    this.goalCategoryCodeList = goalCategoryCodeList;
  }

  public String getGoalCategoryCode() {
    return this.goalCategoryCode;
  }

  public void setGoalCategoryCode(String goalCategoryCode) {
    this.goalCategoryCode = goalCategoryCode;
  }

  public String getGoalCategoryNameList() {
    return this.goalCategoryNameList;
  }

  public void setGoalCategoryNameList(String goalCategoryNameList) {
    this.goalCategoryNameList = goalCategoryNameList;
  }

  public String getIsApprovalGoalClick() {
    return this.isApprovalGoalClick;
  }

  public void setIsApprovalGoalClick(String isApprovalGoalClick) {
    this.isApprovalGoalClick = isApprovalGoalClick;
  }

  public String getSource() {
    return this.source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getCategoryWeightage() {
    return this.categoryWeightage;
  }

  public void setCategoryWeightage(String categoryWeightage) {
    this.categoryWeightage = categoryWeightage;
  }

  public String getGoalCategoryWeightageList() {
    return this.goalCategoryWeightageList;
  }

  public void setGoalCategoryWeightageList(String goalCategoryWeightageList) {
    this.goalCategoryWeightageList = goalCategoryWeightageList;
  }

  public String getFlag() {
    return this.flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }
}