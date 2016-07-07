package org.paradyne.bean.PAS.Competency;

import java.util.List;
import org.paradyne.lib.BeanBase;

public class ReviewerCompetencyAssessment extends BeanBase
{
  private String source = "";

  private String listType = "";

  private String myPage = "";

  private String myProcessedPage = "";
  private boolean pendingListLengthFlag;
  private List<ReviewerCompetencyAssessment> pendingIteratorList;
  private String competencyIDItr = "";

  private String competencyAssesmentIDItr = "";

  private String competencyAssesmentEmployeeIDItr = "";

  private String competencyAssesmentLevelItr = "";

  private String competencyNameItr = "";

  private String competencyAssesmentEmployeeNameItr = "";

  private String competencyAssesmentReviewDateItr = "";

  private String competencyAssesmentDepartmentItr = "";

  private String competencyAssesmentDesignationItr = "";
  private boolean processedListLengthFlag;
  private List<ReviewerCompetencyAssessment> processedIteratorList;
  private String competencyID = "";

  private String competencyAssementID = "";

  private String competencyKey = "";

  private String competencyEmployeeCode = "";

  private String competencyAssesmentLevel = "";

  private String masterCompetencyCode = "";
  private List<ReviewerCompetencyAssessment> reviewEmployeeIterator;
  private String reviewApproverIDItr = "";

  private String reviewApproverTokenItr = "";

  private String reviewApproverNameItr = "";

  private String reviewAgreeItr = "";

  private String reviewDisAgreeItr = "";

  private String reviewEmployeeCommentsItr = "";

  private String reviewReviewerCommentsItr = "";

  private String reviewAgreeDisAgreeFlag = "";

  private String empId = "";

  private String empToken = "";

  private String empName = "";

  private String empBranch = "";

  private String empDepartment = "";

  private String empDesignation = "";

  private String empReportingTo = "";

  public String getListType()
  {
    return this.listType;
  }

  public void setListType(String listType)
  {
    this.listType = listType;
  }

  public boolean isPendingListLengthFlag()
  {
    return this.pendingListLengthFlag;
  }

  public void setPendingListLengthFlag(boolean pendingListLengthFlag)
  {
    this.pendingListLengthFlag = pendingListLengthFlag;
  }

  public List<ReviewerCompetencyAssessment> getPendingIteratorList()
  {
    return this.pendingIteratorList;
  }

  public void setPendingIteratorList(List<ReviewerCompetencyAssessment> pendingIteratorList)
  {
    this.pendingIteratorList = pendingIteratorList;
  }

  public String getCompetencyIDItr()
  {
    return this.competencyIDItr;
  }

  public void setCompetencyIDItr(String competencyIDItr)
  {
    this.competencyIDItr = competencyIDItr;
  }

  public String getCompetencyAssesmentIDItr()
  {
    return this.competencyAssesmentIDItr;
  }

  public void setCompetencyAssesmentIDItr(String competencyAssesmentIDItr)
  {
    this.competencyAssesmentIDItr = competencyAssesmentIDItr;
  }

  public String getCompetencyAssesmentEmployeeIDItr()
  {
    return this.competencyAssesmentEmployeeIDItr;
  }

  public void setCompetencyAssesmentEmployeeIDItr(String competencyAssesmentEmployeeIDItr)
  {
    this.competencyAssesmentEmployeeIDItr = competencyAssesmentEmployeeIDItr;
  }

  public String getCompetencyAssesmentLevelItr()
  {
    return this.competencyAssesmentLevelItr;
  }

  public void setCompetencyAssesmentLevelItr(String competencyAssesmentLevelItr)
  {
    this.competencyAssesmentLevelItr = competencyAssesmentLevelItr;
  }

  public String getCompetencyNameItr()
  {
    return this.competencyNameItr;
  }

  public void setCompetencyNameItr(String competencyNameItr)
  {
    this.competencyNameItr = competencyNameItr;
  }

  public String getCompetencyAssesmentEmployeeNameItr()
  {
    return this.competencyAssesmentEmployeeNameItr;
  }

  public void setCompetencyAssesmentEmployeeNameItr(String competencyAssesmentEmployeeNameItr)
  {
    this.competencyAssesmentEmployeeNameItr = competencyAssesmentEmployeeNameItr;
  }

  public String getCompetencyAssesmentReviewDateItr()
  {
    return this.competencyAssesmentReviewDateItr;
  }

  public void setCompetencyAssesmentReviewDateItr(String competencyAssesmentReviewDateItr)
  {
    this.competencyAssesmentReviewDateItr = competencyAssesmentReviewDateItr;
  }

  public String getCompetencyAssesmentDepartmentItr()
  {
    return this.competencyAssesmentDepartmentItr;
  }

  public void setCompetencyAssesmentDepartmentItr(String competencyAssesmentDepartmentItr)
  {
    this.competencyAssesmentDepartmentItr = competencyAssesmentDepartmentItr;
  }

  public String getCompetencyAssesmentDesignationItr()
  {
    return this.competencyAssesmentDesignationItr;
  }

  public void setCompetencyAssesmentDesignationItr(String competencyAssesmentDesignationItr)
  {
    this.competencyAssesmentDesignationItr = competencyAssesmentDesignationItr;
  }

  public boolean isProcessedListLengthFlag()
  {
    return this.processedListLengthFlag;
  }

  public void setProcessedListLengthFlag(boolean processedListLengthFlag)
  {
    this.processedListLengthFlag = processedListLengthFlag;
  }

  public List<ReviewerCompetencyAssessment> getProcessedIteratorList()
  {
    return this.processedIteratorList;
  }

  public void setProcessedIteratorList(List<ReviewerCompetencyAssessment> processedIteratorList)
  {
    this.processedIteratorList = processedIteratorList;
  }

  public String getMyPage()
  {
    return this.myPage;
  }

  public void setMyPage(String myPage)
  {
    this.myPage = myPage;
  }

  public String getMyProcessedPage()
  {
    return this.myProcessedPage;
  }

  public void setMyProcessedPage(String myProcessedPage)
  {
    this.myProcessedPage = myProcessedPage;
  }

  public String getCompetencyID()
  {
    return this.competencyID;
  }

  public void setCompetencyID(String competencyID)
  {
    this.competencyID = competencyID;
  }

  public String getCompetencyAssementID()
  {
    return this.competencyAssementID;
  }

  public void setCompetencyAssementID(String competencyAssementID)
  {
    this.competencyAssementID = competencyAssementID;
  }

  public String getCompetencyKey()
  {
    return this.competencyKey;
  }

  public void setCompetencyKey(String competencyKey)
  {
    this.competencyKey = competencyKey;
  }

  public String getMasterCompetencyCode()
  {
    return this.masterCompetencyCode;
  }

  public void setMasterCompetencyCode(String masterCompetencyCode)
  {
    this.masterCompetencyCode = masterCompetencyCode;
  }

  public List<ReviewerCompetencyAssessment> getReviewEmployeeIterator()
  {
    return this.reviewEmployeeIterator;
  }

  public void setReviewEmployeeIterator(List<ReviewerCompetencyAssessment> reviewEmployeeIterator)
  {
    this.reviewEmployeeIterator = reviewEmployeeIterator;
  }

  public String getReviewApproverIDItr()
  {
    return this.reviewApproverIDItr;
  }

  public void setReviewApproverIDItr(String reviewApproverIDItr)
  {
    this.reviewApproverIDItr = reviewApproverIDItr;
  }

  public String getReviewApproverTokenItr()
  {
    return this.reviewApproverTokenItr;
  }

  public void setReviewApproverTokenItr(String reviewApproverTokenItr)
  {
    this.reviewApproverTokenItr = reviewApproverTokenItr;
  }

  public String getReviewApproverNameItr()
  {
    return this.reviewApproverNameItr;
  }

  public void setReviewApproverNameItr(String reviewApproverNameItr)
  {
    this.reviewApproverNameItr = reviewApproverNameItr;
  }

  public String getReviewAgreeItr()
  {
    return this.reviewAgreeItr;
  }

  public void setReviewAgreeItr(String reviewAgreeItr)
  {
    this.reviewAgreeItr = reviewAgreeItr;
  }

  public String getReviewDisAgreeItr()
  {
    return this.reviewDisAgreeItr;
  }

  public void setReviewDisAgreeItr(String reviewDisAgreeItr)
  {
    this.reviewDisAgreeItr = reviewDisAgreeItr;
  }

  public String getReviewEmployeeCommentsItr()
  {
    return this.reviewEmployeeCommentsItr;
  }

  public void setReviewEmployeeCommentsItr(String reviewCommentsItr)
  {
    this.reviewEmployeeCommentsItr = reviewCommentsItr;
  }

  public String getReviewAgreeDisAgreeFlag()
  {
    return this.reviewAgreeDisAgreeFlag;
  }

  public void setReviewAgreeDisAgreeFlag(String reviewAgreeDisAgreeFlag)
  {
    this.reviewAgreeDisAgreeFlag = reviewAgreeDisAgreeFlag;
  }

  public String getEmpId()
  {
    return this.empId;
  }

  public void setEmpId(String empId)
  {
    this.empId = empId;
  }

  public String getEmpToken()
  {
    return this.empToken;
  }

  public void setEmpToken(String empToken)
  {
    this.empToken = empToken;
  }

  public String getEmpName()
  {
    return this.empName;
  }

  public void setEmpName(String empName)
  {
    this.empName = empName;
  }

  public String getEmpBranch()
  {
    return this.empBranch;
  }

  public void setEmpBranch(String empBranch)
  {
    this.empBranch = empBranch;
  }

  public String getEmpDepartment()
  {
    return this.empDepartment;
  }

  public void setEmpDepartment(String empDepartment)
  {
    this.empDepartment = empDepartment;
  }

  public String getEmpDesignation()
  {
    return this.empDesignation;
  }

  public void setEmpDesignation(String empDesignation)
  {
    this.empDesignation = empDesignation;
  }

  public String getEmpReportingTo()
  {
    return this.empReportingTo;
  }

  public void setEmpReportingTo(String empReportingTo)
  {
    this.empReportingTo = empReportingTo;
  }

  public String getCompetencyEmployeeCode()
  {
    return this.competencyEmployeeCode;
  }

  public void setCompetencyEmployeeCode(String competencyEmployeeCode)
  {
    this.competencyEmployeeCode = competencyEmployeeCode;
  }

  public String getCompetencyAssesmentLevel()
  {
    return this.competencyAssesmentLevel;
  }

  public void setCompetencyAssesmentLevel(String competencyAssesmentLevel)
  {
    this.competencyAssesmentLevel = competencyAssesmentLevel;
  }

  public String getReviewReviewerCommentsItr()
  {
    return this.reviewReviewerCommentsItr;
  }

  public void setReviewReviewerCommentsItr(String reviewReviewerCommentsItr)
  {
    this.reviewReviewerCommentsItr = reviewReviewerCommentsItr;
  }
  public String getSource() {
    return this.source;
  }
  public void setSource(String source) {
    this.source = source;
  }
}