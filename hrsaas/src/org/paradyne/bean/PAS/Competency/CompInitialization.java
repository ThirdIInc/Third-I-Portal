package org.paradyne.bean.PAS.Competency;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class CompInitialization extends BeanBase
{
  private String reviewWeightage = "";
  private String totalreviewWeight = "";
  private String categoryWeightage = "";
  private String catWeightage = "";
  private String totalCatWeightage = "";
  private String isConfRating = "";
  private String hiddenConfRating = "";
  private String ratingrangeupto = "";
  private String ratingrangedescrp = "";
  private String srNo;
  private String compId;
  private String compName;
  private String compfromDate;
  private String comptoDate;
  private String compPublishStatus;
  private String hiddencode = "";

  private String compNameItt = "";
  private String compfromDateItt = "";
  private String comptoDateItt = "";
  private String reviewDate;
  private String paraId;
  private String myPage;
  private String compStatusItt;
  private String appraisalName = "";
  private String appraisalCode = "";
  private String reportingType = "";
  private String isCategoryReq = "";
  private String isAchievedFlagReq = "";
  private String isSignOffRequired = "";
  private String compIdItt = "";
  ArrayList reviewDateList;
  ArrayList iteratorlist;
  private String isEscalation = "";
  private String escalationMailId = "";
  private String categoryIt;
  private String tableLength;
  ArrayList list;
  private String category1;
  private String subcode;
  private String hiddenEdit;
  private String confChkop;
  private String hdeleteOp;
  private String compWeightage = "";
  private String appWeightage = "";
  private String selfAsstWeightage = "";
  private String managerAsstWeightage = "";
  private String multipleManagerRating = "";
  private String multipleManagerRatingRadio = "";
  private String finalRating = "";
  private String avgRating = "";
  private boolean calcRatingFlag = false;
  private String report = "";

  private String reportType = "";

  public String getReportType() {
    return this.reportType;
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

  public ArrayList getReviewDateList()
  {
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

  public String getCategoryIt() {
    return this.categoryIt;
  }
  public void setCategoryIt(String categoryIt) {
    this.categoryIt = categoryIt;
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
  public String getCompId() {
    return this.compId;
  }
  public void setCompId(String compId) {
    this.compId = compId;
  }
  public String getCompName() {
    return this.compName;
  }
  public void setCompName(String compName) {
    this.compName = compName;
  }
  public String getCompfromDate() {
    return this.compfromDate;
  }
  public void setCompfromDate(String compfromDate) {
    this.compfromDate = compfromDate;
  }
  public String getComptoDate() {
    return this.comptoDate;
  }
  public void setComptoDate(String comptoDate) {
    this.comptoDate = comptoDate;
  }
  public String getCompPublishStatus() {
    return this.compPublishStatus;
  }
  public void setCompPublishStatus(String compPublishStatus) {
    this.compPublishStatus = compPublishStatus;
  }
  public String getCompNameItt() {
    return this.compNameItt;
  }
  public void setCompNameItt(String compNameItt) {
    this.compNameItt = compNameItt;
  }
  public String getCompfromDateItt() {
    return this.compfromDateItt;
  }
  public void setCompfromDateItt(String compfromDateItt) {
    this.compfromDateItt = compfromDateItt;
  }
  public String getComptoDateItt() {
    return this.comptoDateItt;
  }
  public void setComptoDateItt(String comptoDateItt) {
    this.comptoDateItt = comptoDateItt;
  }
  public String getCompStatusItt() {
    return this.compStatusItt;
  }
  public void setCompStatusItt(String compStatusItt) {
    this.compStatusItt = compStatusItt;
  }
  public String getCompIdItt() {
    return this.compIdItt;
  }
  public void setCompIdItt(String compIdItt) {
    this.compIdItt = compIdItt;
  }
  public String getCompWeightage() {
    return this.compWeightage;
  }
  public void setCompWeightage(String compWeightage) {
    this.compWeightage = compWeightage;
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
  public String getReport() {
    return this.report;
  }
  public void setReport(String report) {
    this.report = report;
  }
  public String getIsConfRating() {
    return this.isConfRating;
  }
  public void setIsConfRating(String isConfRating) {
    this.isConfRating = isConfRating;
  }
  public String getHiddenConfRating() {
    return this.hiddenConfRating;
  }
  public void setHiddenConfRating(String hiddenConfRating) {
    this.hiddenConfRating = hiddenConfRating;
  }
}