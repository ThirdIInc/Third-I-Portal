package org.paradyne.bean.PAS.GoalSetting;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class GoalInitialization extends BeanBase
{
  private String ratingrangeupto = "";

  private String ratingrangedescrp = "";

  private String reviewWeightage = "";

  private String totalreviewWeight = "";

  private String categoryWeightage = "";

  private String catWeightage = "";

  private String totalCatWeightage = "";
  private String srNo;
  private String goalId;
  private String goalName;
  private String goalfromDate;
  private String goaltoDate;
  private String goalPublishStatus;
  private String hiddencode = "";

  private String report = "";

  private String goalNameItt = "";

  private String goalfromDateItt = "";

  private String goaltoDateItt = "";
  private String reviewDate;
  private String paraId;
  private String myPage;
  private String goalStatusItt;
  private String appraisalName = "";

  private String appraisalCode = "";

  private String reportingType = "";

  private String isCategoryReq = "";

  private String isAchievedFlagReq = "";

  private String isSignOffRequired = "";

  private String goalIdItt = "";

  private String deleteKey = "";

  private String gStatus = "";

  private String goalToDateEmp = "";

  private String goalFromDateEmp = "";

  private String goalStatusEmp = "";

  private String reportingTypeEmp = "";

  private String goalPublishStatusEmp = "";

  private String goalNameEmp = "";

  private String empIdTxt = "";

  private String eligibleForGoal = "";
  ArrayList reviewDateList;
  ArrayList iteratorlist;
  private String isEscalation = "";

  private String escalationMailId = "";
  private String category;
  private String tableLength;
  ArrayList list;
  private String category1;
  private String subcode;
  private String hiddenEdit;
  private String confChkop;
  private String hdeleteOp;
  private String goalWeightage = "";

  private String appWeightage = "";

  private String selfAsstWeightage = "";

  private String managerAsstWeightage = "";

  private String multipleManagerRating = "";

  private String multipleManagerRatingRadio = "";

  private String finalRating = "";

  private String avgRating = "";

  private boolean calcRatingFlag = false;
  private boolean eligibleEmployeeReportFlag=false;
  private String empToken;
  private String empName;
  private String empBranch;
  private String empDepartment;
  private String empDesignation;
  private String empGrade;
  private String datOfJoining;
  ArrayList empList;
  private String divCode;
  private String branchCode;
  private String deptCode;
  private String desgCode;
  private String gradeCode;
  private String empId;
  private String listEmp = "";
  private String empTypeCode;
  private String dupData;
  private String criteriaflag = "";

  private String reportType = "";
  private boolean editFlag = false;
  private String delKey = "";

  public String getReportType() {
    return this.reportType;
  }
  public boolean isEditFlag() {
    return this.editFlag;
  }
  public void setEditFlag(boolean editFlag) {
    this.editFlag = editFlag;
  }
  public void setReportType(String reportType) {
    this.reportType = reportType;
  }
  public boolean isCalcRatingFlag() {
    return this.calcRatingFlag;
  }
  public void setCalcRatingFlag(boolean calcRatingFlag) {
    this.calcRatingFlag = calcRatingFlag;
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
  public String getGoalfromDate() {
    return this.goalfromDate;
  }
  public void setGoalfromDate(String goalfromDate) {
    this.goalfromDate = goalfromDate;
  }
  public String getGoaltoDate() {
    return this.goaltoDate;
  }
  public void setGoaltoDate(String goaltoDate) {
    this.goaltoDate = goaltoDate;
  }
  public String getGoalPublishStatus() {
    return this.goalPublishStatus;
  }
  public void setGoalPublishStatus(String goalPublishStatus) {
    this.goalPublishStatus = goalPublishStatus;
  }

  public ArrayList getReviewDateList() {
    return this.reviewDateList;
  }
  public void setReviewDateList(ArrayList reviewDateList) {
    this.reviewDateList = reviewDateList;
  }
  public String getSrNo() {
    return this.srNo;
  }
  public void setSrNo(String srNo) {
    this.srNo = srNo;
  }
  public String getHiddencode() {
    return this.hiddencode;
  }
  public void setHiddencode(String hiddencode) {
    this.hiddencode = hiddencode;
  }
  public String getGoalNameItt() {
    return this.goalNameItt;
  }
  public void setGoalNameItt(String goalNameItt) {
    this.goalNameItt = goalNameItt;
  }
  public String getGoalfromDateItt() {
    return this.goalfromDateItt;
  }
  public void setGoalfromDateItt(String goalfromDateItt) {
    this.goalfromDateItt = goalfromDateItt;
  }
  public String getGoaltoDateItt() {
    return this.goaltoDateItt;
  }
  public void setGoaltoDateItt(String goaltoDateItt) {
    this.goaltoDateItt = goaltoDateItt;
  }
  public String getGoalIdItt() {
    return this.goalIdItt;
  }
  public void setGoalIdItt(String goalIdItt) {
    this.goalIdItt = goalIdItt;
  }
  public ArrayList getIteratorlist() {
    return this.iteratorlist;
  }
  public void setIteratorlist(ArrayList iteratorlist) {
    this.iteratorlist = iteratorlist;
  }
  public String getReviewDate() {
    return this.reviewDate;
  }
  public void setReviewDate(String reviewDate) {
    this.reviewDate = reviewDate;
  }
  public String getParaId() {
    return this.paraId;
  }
  public void setParaId(String paraId) {
    this.paraId = paraId;
  }
  public String getGoalStatusItt() {
    return this.goalStatusItt;
  }
  public void setGoalStatusItt(String goalStatusItt) {
    this.goalStatusItt = goalStatusItt;
  }
  public String getMyPage() {
    return this.myPage;
  }
  public void setMyPage(String myPage) {
    this.myPage = myPage;
  }
  public String getAppraisalName() {
    return this.appraisalName;
  }
  public void setAppraisalName(String appraisalName) {
    this.appraisalName = appraisalName;
  }
  public String getAppraisalCode() {
    return this.appraisalCode;
  }
  public void setAppraisalCode(String appraisalCode) {
    this.appraisalCode = appraisalCode;
  }
  public String getReportingType() {
    return this.reportingType;
  }
  public void setReportingType(String reportingType) {
    this.reportingType = reportingType;
  }
  public String getCategory() {
    return this.category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getTableLength() {
    return this.tableLength;
  }
  public void setTableLength(String tableLength) {
    this.tableLength = tableLength;
  }
  public ArrayList getList() {
    return this.list;
  }
  public void setList(ArrayList list) {
    this.list = list;
  }
  public String getCategory1() {
    return this.category1;
  }
  public void setCategory1(String category1) {
    this.category1 = category1;
  }
  public String getSubcode() {
    return this.subcode;
  }
  public void setSubcode(String subcode) {
    this.subcode = subcode;
  }
  public String getHiddenEdit() {
    return this.hiddenEdit;
  }
  public void setHiddenEdit(String hiddenEdit) {
    this.hiddenEdit = hiddenEdit;
  }
  public String getConfChkop() {
    return this.confChkop;
  }
  public void setConfChkop(String confChkop) {
    this.confChkop = confChkop;
  }
  public String getHdeleteOp() {
    return this.hdeleteOp;
  }
  public void setHdeleteOp(String hdeleteOp) {
    this.hdeleteOp = hdeleteOp;
  }
  public String getIsCategoryReq() {
    return this.isCategoryReq;
  }
  public void setIsCategoryReq(String isCategoryReq) {
    this.isCategoryReq = isCategoryReq;
  }
  public String getIsAchievedFlagReq() {
    return this.isAchievedFlagReq;
  }
  public void setIsAchievedFlagReq(String isAchievedFlagReq) {
    this.isAchievedFlagReq = isAchievedFlagReq;
  }
  public String getIsSignOffRequired() {
    return this.isSignOffRequired;
  }
  public void setIsSignOffRequired(String isSignOffRequired) {
    this.isSignOffRequired = isSignOffRequired;
  }
  public String getGoalWeightage() {
    return this.goalWeightage;
  }
  public void setGoalWeightage(String goalWeightage) {
    this.goalWeightage = goalWeightage;
  }
  public String getAppWeightage() {
    return this.appWeightage;
  }
  public void setAppWeightage(String appWeightage) {
    this.appWeightage = appWeightage;
  }
  public String getSelfAsstWeightage() {
    return this.selfAsstWeightage;
  }
  public void setSelfAsstWeightage(String selfAsstWeightage) {
    this.selfAsstWeightage = selfAsstWeightage;
  }
  public String getManagerAsstWeightage() {
    return this.managerAsstWeightage;
  }
  public void setManagerAsstWeightage(String managerAsstWeightage) {
    this.managerAsstWeightage = managerAsstWeightage;
  }
  public String getFinalRating() {
    return this.finalRating;
  }
  public void setFinalRating(String finalRating) {
    this.finalRating = finalRating;
  }
  public String getAvgRating() {
    return this.avgRating;
  }
  public void setAvgRating(String avgRating) {
    this.avgRating = avgRating;
  }
  public String getMultipleManagerRating() {
    return this.multipleManagerRating;
  }
  public void setMultipleManagerRating(String multipleManagerRating) {
    this.multipleManagerRating = multipleManagerRating;
  }
  public String getMultipleManagerRatingRadio() {
    return this.multipleManagerRatingRadio;
  }
  public void setMultipleManagerRatingRadio(String multipleManagerRatingRadio) {
    this.multipleManagerRatingRadio = multipleManagerRatingRadio;
  }
  public String getRatingrangeupto() {
    return this.ratingrangeupto;
  }
  public void setRatingrangeupto(String ratingrangeupto) {
    this.ratingrangeupto = ratingrangeupto;
  }
  public String getIsEscalation() {
    return this.isEscalation;
  }
  public void setIsEscalation(String isEscalation) {
    this.isEscalation = isEscalation;
  }
  public String getEscalationMailId() {
    return this.escalationMailId;
  }
  public void setEscalationMailId(String escalationMailId) {
    this.escalationMailId = escalationMailId;
  }
  public String getRatingrangedescrp() {
    return this.ratingrangedescrp;
  }
  public void setRatingrangedescrp(String ratingrangedescrp) {
    this.ratingrangedescrp = ratingrangedescrp;
  }

  public String getReport() {
    return this.report;
  }
  public void setReport(String report) {
    this.report = report;
  }

  public String getReviewWeightage() {
    return this.reviewWeightage;
  }
  public void setReviewWeightage(String reviewWeightage) {
    this.reviewWeightage = reviewWeightage;
  }
  public String getTotalreviewWeight() {
    return this.totalreviewWeight;
  }
  public void setTotalreviewWeight(String totalreviewWeight) {
    this.totalreviewWeight = totalreviewWeight;
  }
  public String getCategoryWeightage() {
    return this.categoryWeightage;
  }
  public void setCategoryWeightage(String categoryWeightage) {
    this.categoryWeightage = categoryWeightage;
  }
  public String getCatWeightage() {
    return this.catWeightage;
  }
  public void setCatWeightage(String catWeightage) {
    this.catWeightage = catWeightage;
  }
  public String getTotalCatWeightage() {
    return this.totalCatWeightage;
  }
  public void setTotalCatWeightage(String totalCatWeightage) {
    this.totalCatWeightage = totalCatWeightage;
  }

  public ArrayList getEmpList() {
    return this.empList;
  }
  public void setEmpList(ArrayList empList) {
    this.empList = empList;
  }
  public String getDatOfJoining() {
    return this.datOfJoining;
  }
  public void setDatOfJoining(String datOfJoining) {
    this.datOfJoining = datOfJoining;
  }
  public String getListEmp() {
    return this.listEmp;
  }
  public void setListEmp(String listEmp) {
    this.listEmp = listEmp;
  }
  public String getCriteriaflag() {
    return this.criteriaflag;
  }
  public void setCriteriaflag(String criteriaflag) {
    this.criteriaflag = criteriaflag;
  }
  public String getDivCode() {
    return this.divCode;
  }
  public void setDivCode(String divCode) {
    this.divCode = divCode;
  }
  public String getBranchCode() {
    return this.branchCode;
  }
  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }
  public String getDeptCode() {
    return this.deptCode;
  }
  public void setDeptCode(String deptCode) {
    this.deptCode = deptCode;
  }
  public String getDesgCode() {
    return this.desgCode;
  }
  public void setDesgCode(String desgCode) {
    this.desgCode = desgCode;
  }
  public String getGradeCode() {
    return this.gradeCode;
  }
  public void setGradeCode(String gradeCode) {
    this.gradeCode = gradeCode;
  }
  public String getEmpId() {
    return this.empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
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
  public String getEmpBranch() {
    return this.empBranch;
  }
  public void setEmpBranch(String empBranch) {
    this.empBranch = empBranch;
  }
  public String getEmpDepartment() {
    return this.empDepartment;
  }
  public void setEmpDepartment(String empDepartment) {
    this.empDepartment = empDepartment;
  }
  public String getEmpDesignation() {
    return this.empDesignation;
  }
  public void setEmpDesignation(String empDesignation) {
    this.empDesignation = empDesignation;
  }
  public String getEmpGrade() {
    return this.empGrade;
  }
  public void setEmpGrade(String empGrade) {
    this.empGrade = empGrade;
  }
  public String getEmpTypeCode() {
    return this.empTypeCode;
  }
  public void setEmpTypeCode(String empTypeCode) {
    this.empTypeCode = empTypeCode;
  }
  public String getDeleteKey() {
    return this.deleteKey;
  }
  public void setDeleteKey(String deleteKey) {
    this.deleteKey = deleteKey;
  }
  public String getGStatus() {
    return this.gStatus;
  }
  public void setGStatus(String status) {
    this.gStatus = status;
  }
  public String getEmpIdTxt() {
    return this.empIdTxt;
  }
  public void setEmpIdTxt(String empIdTxt) {
    this.empIdTxt = empIdTxt;
  }
  public String getGoalToDateEmp() {
    return this.goalToDateEmp;
  }
  public void setGoalToDateEmp(String goalToDateEmp) {
    this.goalToDateEmp = goalToDateEmp;
  }
  public String getGoalFromDateEmp() {
    return this.goalFromDateEmp;
  }
  public void setGoalFromDateEmp(String goalFromDateEmp) {
    this.goalFromDateEmp = goalFromDateEmp;
  }
  public String getGoalStatusEmp() {
    return this.goalStatusEmp;
  }
  public void setGoalStatusEmp(String goalStatusEmp) {
    this.goalStatusEmp = goalStatusEmp;
  }
  public String getReportingTypeEmp() {
    return this.reportingTypeEmp;
  }
  public void setReportingTypeEmp(String reportingTypeEmp) {
    this.reportingTypeEmp = reportingTypeEmp;
  }
  public String getGoalPublishStatusEmp() {
    return this.goalPublishStatusEmp;
  }
  public void setGoalPublishStatusEmp(String goalPublishStatusEmp) {
    this.goalPublishStatusEmp = goalPublishStatusEmp;
  }
  public String getGoalNameEmp() {
    return this.goalNameEmp;
  }
  public void setGoalNameEmp(String goalNameEmp) {
    this.goalNameEmp = goalNameEmp;
  }
  public String getEligibleForGoal() {
    return this.eligibleForGoal;
  }
  public void setEligibleForGoal(String eligibleForGoal) {
    this.eligibleForGoal = eligibleForGoal;
  }
  public String getDupData() {
    return this.dupData;
  }
  public void setDupData(String dupData) {
    this.dupData = dupData;
  }
  public String getDelKey() {
    return this.delKey;
  }
  public void setDelKey(String delKey) {
    this.delKey = delKey;
  }
/**
 * @return the eligibleEmployeeReportFlag
 */
public boolean isEligibleEmployeeReportFlag() {
	return eligibleEmployeeReportFlag;
}
/**
 * @param eligibleEmployeeReportFlag the eligibleEmployeeReportFlag to set
 */
public void setEligibleEmployeeReportFlag(boolean eligibleEmployeeReportFlag) {
	this.eligibleEmployeeReportFlag = eligibleEmployeeReportFlag;
}
}