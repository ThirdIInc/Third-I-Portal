package org.paradyne.bean.PAS.Competency;

import java.util.List;
import java.util.TreeMap;
import org.paradyne.lib.BeanBase;

public class SelfCompentencyAssesment extends BeanBase
{
  private String source = "";

  private String competencyIDItr = "";

  private String myPendingPage = "";

  private String myProcessedPage = "";

  private String competencyAssementIDItr = "";

  private String competencyNameItr = "";

  private String competencyFromDate = "";

  private String competencyToDate = "";

  private String competencyReviewDateItr = "";

  private String listType = "";
  private List<SelfCompentencyAssesment> pendingRecordList;
  private boolean pendingRecordListFlag;
  private List<SelfCompentencyAssesment> processedRecordList;
  private boolean processedRecordListFlag;
  private boolean processListEmptyFlag;
  private boolean pendingListEmptyFlag;
  private boolean firstDetailPageFlag;
  private boolean secondDetailPageFlag;
  private TreeMap<String, String> competencyTitleValues;
  private String competencyTitleDropDown = "";

  private String mappedCategory = "";
  private boolean approverRatingCommentsPageFlag;
  private List<SelfCompentencyAssesment> approverRatingCommentsIterator;
  private String approverCompetencyNameItr = "";

  private String approverProficiencyLevelItr = "";

  private String approverRatingItr = "";

  private String approverCommentsItr = "";

  private String approverNameItr = "";
  private boolean processedPageApproverRatingCommentsFlag;
  private List<SelfCompentencyAssesment> reviewEmployeeIterator;
  private String reviewApproverIDItr = "";

  private String reviewApproverTokenItr = "";

  private String reviewApproverNameItr = "";

  private String reviewAgreeItr = "";

  private String reviewDisAgreeItr = "";

  private String reviewCommentsItr = "";

  private String reviewAgreeDisAgreeFlag = "";
  private boolean signOffWorkflowFlag;
  private boolean signOffAndEscalationWorkflowFlag;
  private boolean allReadyProcessedAgreeDisAgreeFlag;
  private boolean signOffEscalationWorkFlowONFLAG;
  private boolean afterReAssesedFlag;
  private String competencyID = "";

  private String competencyAssementID = "";

  private String competencyEmpDesignation = "";

  private String competencyName = "";

  private String hiddenCompetencyName = "";

  private String competencyDescription = "";

  private String competencyIndicator = "";

  private String selfRating = "";

  private String selfComments = "";

  private String competencyKey = "1";

  private String competencyIDFinalizeItr = "";
  private boolean backToListPageFlag;
  private boolean finalizeSaveAndPreviousPageFlag;
  private String masterCompetencyCode = "";
  private List<SelfCompentencyAssesment> allRatingAndCommentsIterator;
  private String competencyNameFinalizeItr = "";

  private String proficiencyLevelFinalizeItr = "";

  private String ratingFinalizeItr = "";

  private String commentsFinalizeItr = "";
  private boolean finalizedFlag;
  private boolean finalPageFlag;
  private boolean nonFinalPageFlag = true;

  private String empId = "";

  private String empToken = "";

  private String empName = "";

  private String empBranch = "";

  private String empDepartment = "";

  private String empDesignation = "";

  private String empReportingTo = "";

  public String getCompetencyName()
  {
    return this.competencyName;
  }

  public void setCompetencyName(String competencyName)
  {
    this.competencyName = competencyName;
  }

  public String getCompetencyDescription()
  {
    return this.competencyDescription;
  }

  public void setCompetencyDescription(String competencyDescription)
  {
    this.competencyDescription = competencyDescription;
  }

  public String getCompetencyIndicator()
  {
    return this.competencyIndicator;
  }

  public void setCompetencyIndicator(String competencyIndicator)
  {
    this.competencyIndicator = competencyIndicator;
  }

  public String getCompetencyIDItr()
  {
    return this.competencyIDItr;
  }

  public void setCompetencyIDItr(String competencyIDItr)
  {
    this.competencyIDItr = competencyIDItr;
  }

  public String getCompetencyAssementIDItr()
  {
    return this.competencyAssementIDItr;
  }

  public void setCompetencyAssementIDItr(String competencyAssementIDItr)
  {
    this.competencyAssementIDItr = competencyAssementIDItr;
  }

  public String getCompetencyNameItr()
  {
    return this.competencyNameItr;
  }

  public void setCompetencyNameItr(String competencyNameItr)
  {
    this.competencyNameItr = competencyNameItr;
  }

  public String getCompetencyFromDate()
  {
    return this.competencyFromDate;
  }

  public void setCompetencyFromDate(String competencyFromDate)
  {
    this.competencyFromDate = competencyFromDate;
  }

  public String getCompetencyToDate()
  {
    return this.competencyToDate;
  }

  public void setCompetencyToDate(String competencyToDate)
  {
    this.competencyToDate = competencyToDate;
  }

  public List<SelfCompentencyAssesment> getPendingRecordList()
  {
    return this.pendingRecordList;
  }

  public void setPendingRecordList(List<SelfCompentencyAssesment> pendingRecordList)
  {
    this.pendingRecordList = pendingRecordList;
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

  public String getCompetencyEmpDesignation()
  {
    return this.competencyEmpDesignation;
  }

  public void setCompetencyEmpDesignation(String competencyEmpDesignation)
  {
    this.competencyEmpDesignation = competencyEmpDesignation;
  }

  public String getSelfRating()
  {
    return this.selfRating;
  }

  public void setSelfRating(String selfRating)
  {
    this.selfRating = selfRating;
  }

  public String getSelfComments()
  {
    return this.selfComments;
  }

  public void setSelfComments(String selfComments)
  {
    this.selfComments = selfComments;
  }

  public String getCompetencyKey()
  {
    return this.competencyKey;
  }

  public void setCompetencyKey(String competencyKey)
  {
    this.competencyKey = competencyKey;
  }

  public String getHiddenCompetencyName()
  {
    return this.hiddenCompetencyName;
  }

  public void setHiddenCompetencyName(String hiddenCompetencyName)
  {
    this.hiddenCompetencyName = hiddenCompetencyName;
  }

  public List<SelfCompentencyAssesment> getAllRatingAndCommentsIterator()
  {
    return this.allRatingAndCommentsIterator;
  }

  public void setAllRatingAndCommentsIterator(List<SelfCompentencyAssesment> allRatingAndCommentsIterator)
  {
    this.allRatingAndCommentsIterator = allRatingAndCommentsIterator;
  }

  public String getCompetencyNameFinalizeItr()
  {
    return this.competencyNameFinalizeItr;
  }

  public void setCompetencyNameFinalizeItr(String competencyNameFinalizeItr)
  {
    this.competencyNameFinalizeItr = competencyNameFinalizeItr;
  }

  public String getProficiencyLevelFinalizeItr()
  {
    return this.proficiencyLevelFinalizeItr;
  }

  public void setProficiencyLevelFinalizeItr(String proficiencyLevelFinalizeItr)
  {
    this.proficiencyLevelFinalizeItr = proficiencyLevelFinalizeItr;
  }

  public String getRatingFinalizeItr()
  {
    return this.ratingFinalizeItr;
  }

  public void setRatingFinalizeItr(String ratingFinalizeItr)
  {
    this.ratingFinalizeItr = ratingFinalizeItr;
  }

  public String getCommentsFinalizeItr()
  {
    return this.commentsFinalizeItr;
  }

  public void setCommentsFinalizeItr(String commentsFinalizeItr)
  {
    this.commentsFinalizeItr = commentsFinalizeItr;
  }

  public boolean isFinalizedFlag()
  {
    return this.finalizedFlag;
  }

  public void setFinalizedFlag(boolean finalizedFlag)
  {
    this.finalizedFlag = finalizedFlag;
  }

  public boolean isFinalPageFlag()
  {
    return this.finalPageFlag;
  }

  public void setFinalPageFlag(boolean finalPageFlag)
  {
    this.finalPageFlag = finalPageFlag;
  }

  public boolean isNonFinalPageFlag()
  {
    return this.nonFinalPageFlag;
  }

  public void setNonFinalPageFlag(boolean nonFinalPageFlag)
  {
    this.nonFinalPageFlag = nonFinalPageFlag;
  }

  public String getCompetencyIDFinalizeItr()
  {
    return this.competencyIDFinalizeItr;
  }

  public void setCompetencyIDFinalizeItr(String competencyIDFinalizeItr)
  {
    this.competencyIDFinalizeItr = competencyIDFinalizeItr;
  }

  public String getListType()
  {
    return this.listType;
  }

  public void setListType(String listType)
  {
    this.listType = listType;
  }

  public List<SelfCompentencyAssesment> getProcessedRecordList()
  {
    return this.processedRecordList;
  }

  public void setProcessedRecordList(List<SelfCompentencyAssesment> processedRecordList)
  {
    this.processedRecordList = processedRecordList;
  }

  public boolean isPendingRecordListFlag()
  {
    return this.pendingRecordListFlag;
  }

  public void setPendingRecordListFlag(boolean pendingRecordListFlag)
  {
    this.pendingRecordListFlag = pendingRecordListFlag;
  }

  public boolean isProcessedRecordListFlag()
  {
    return this.processedRecordListFlag;
  }

  public void setProcessedRecordListFlag(boolean processedRecordListFlag)
  {
    this.processedRecordListFlag = processedRecordListFlag;
  }

  public boolean isProcessListEmptyFlag()
  {
    return this.processListEmptyFlag;
  }

  public void setProcessListEmptyFlag(boolean processListEmptyFlag)
  {
    this.processListEmptyFlag = processListEmptyFlag;
  }

  public boolean isPendingListEmptyFlag()
  {
    return this.pendingListEmptyFlag;
  }

  public void setPendingListEmptyFlag(boolean pendingListEmptyFlag)
  {
    this.pendingListEmptyFlag = pendingListEmptyFlag;
  }

  public boolean isBackToListPageFlag()
  {
    return this.backToListPageFlag;
  }

  public void setBackToListPageFlag(boolean backToListPageFlag)
  {
    this.backToListPageFlag = backToListPageFlag;
  }

  public boolean isFinalizeSaveAndPreviousPageFlag()
  {
    return this.finalizeSaveAndPreviousPageFlag;
  }

  public void setFinalizeSaveAndPreviousPageFlag(boolean finalizeSaveAndPreviousPageFlag)
  {
    this.finalizeSaveAndPreviousPageFlag = finalizeSaveAndPreviousPageFlag;
  }

  public String getMasterCompetencyCode()
  {
    return this.masterCompetencyCode;
  }

  public void setMasterCompetencyCode(String masterCompetencyCode)
  {
    this.masterCompetencyCode = masterCompetencyCode;
  }

  public String getCompetencyReviewDateItr()
  {
    return this.competencyReviewDateItr;
  }

  public void setCompetencyReviewDateItr(String competencyReviewDateItr)
  {
    this.competencyReviewDateItr = competencyReviewDateItr;
  }

  public boolean isFirstDetailPageFlag()
  {
    return this.firstDetailPageFlag;
  }

  public void setFirstDetailPageFlag(boolean firstDetailPageFlag)
  {
    this.firstDetailPageFlag = firstDetailPageFlag;
  }

  public boolean isSecondDetailPageFlag()
  {
    return this.secondDetailPageFlag;
  }

  public void setSecondDetailPageFlag(boolean secondDetailPageFlag)
  {
    this.secondDetailPageFlag = secondDetailPageFlag;
  }

  public boolean isApproverRatingCommentsPageFlag()
  {
    return this.approverRatingCommentsPageFlag;
  }

  public void setApproverRatingCommentsPageFlag(boolean approverRatingCommentsPageFlag)
  {
    this.approverRatingCommentsPageFlag = approverRatingCommentsPageFlag;
  }

  public List<SelfCompentencyAssesment> getApproverRatingCommentsIterator()
  {
    return this.approverRatingCommentsIterator;
  }

  public void setApproverRatingCommentsIterator(List<SelfCompentencyAssesment> approverRatingCommentsIterator)
  {
    this.approverRatingCommentsIterator = approverRatingCommentsIterator;
  }

  public String getApproverCompetencyNameItr()
  {
    return this.approverCompetencyNameItr;
  }

  public void setApproverCompetencyNameItr(String approverCompetencyNameItr)
  {
    this.approverCompetencyNameItr = approverCompetencyNameItr;
  }

  public String getApproverProficiencyLevelItr()
  {
    return this.approverProficiencyLevelItr;
  }

  public void setApproverProficiencyLevelItr(String approverProficiencyLevelItr)
  {
    this.approverProficiencyLevelItr = approverProficiencyLevelItr;
  }

  public String getApproverRatingItr()
  {
    return this.approverRatingItr;
  }

  public void setApproverRatingItr(String approverRatingItr)
  {
    this.approverRatingItr = approverRatingItr;
  }

  public String getApproverCommentsItr()
  {
    return this.approverCommentsItr;
  }

  public void setApproverCommentsItr(String approverCommentsItr)
  {
    this.approverCommentsItr = approverCommentsItr;
  }

  public String getApproverNameItr()
  {
    return this.approverNameItr;
  }

  public void setApproverNameItr(String approverNameItr)
  {
    this.approverNameItr = approverNameItr;
  }

  public TreeMap<String, String> getCompetencyTitleValues()
  {
    return this.competencyTitleValues;
  }

  public void setCompetencyTitleValues(TreeMap<String, String> competencyTitleValues)
  {
    this.competencyTitleValues = competencyTitleValues;
  }

  public String getCompetencyTitleDropDown()
  {
    return this.competencyTitleDropDown;
  }

  public void setCompetencyTitleDropDown(String competencyTitleDropDown)
  {
    this.competencyTitleDropDown = competencyTitleDropDown;
  }

  public boolean isProcessedPageApproverRatingCommentsFlag()
  {
    return this.processedPageApproverRatingCommentsFlag;
  }

  public void setProcessedPageApproverRatingCommentsFlag(boolean processedPageApproverRatingCommentsFlag)
  {
    this.processedPageApproverRatingCommentsFlag = processedPageApproverRatingCommentsFlag;
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

  public List<SelfCompentencyAssesment> getReviewEmployeeIterator()
  {
    return this.reviewEmployeeIterator;
  }

  public void setReviewEmployeeIterator(List<SelfCompentencyAssesment> reviewEmployeeIterator)
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

  public String getReviewCommentsItr()
  {
    return this.reviewCommentsItr;
  }

  public void setReviewCommentsItr(String reviewCommentsItr)
  {
    this.reviewCommentsItr = reviewCommentsItr;
  }

  public String getReviewAgreeDisAgreeFlag()
  {
    return this.reviewAgreeDisAgreeFlag;
  }

  public void setReviewAgreeDisAgreeFlag(String reviewAgreeDisAgreeFlag)
  {
    this.reviewAgreeDisAgreeFlag = reviewAgreeDisAgreeFlag;
  }

  public boolean isSignOffWorkflowFlag()
  {
    return this.signOffWorkflowFlag;
  }

  public void setSignOffWorkflowFlag(boolean signOffWorkflowFlag)
  {
    this.signOffWorkflowFlag = signOffWorkflowFlag;
  }

  public boolean isSignOffAndEscalationWorkflowFlag()
  {
    return this.signOffAndEscalationWorkflowFlag;
  }

  public void setSignOffAndEscalationWorkflowFlag(boolean signOffAndEscalationWorkflowFlag)
  {
    this.signOffAndEscalationWorkflowFlag = signOffAndEscalationWorkflowFlag;
  }

  public boolean isAllReadyProcessedAgreeDisAgreeFlag()
  {
    return this.allReadyProcessedAgreeDisAgreeFlag;
  }

  public void setAllReadyProcessedAgreeDisAgreeFlag(boolean allReadyProcessedAgreeDisAgreeFlag)
  {
    this.allReadyProcessedAgreeDisAgreeFlag = allReadyProcessedAgreeDisAgreeFlag;
  }

  public boolean isSignOffEscalationWorkFlowONFLAG()
  {
    return this.signOffEscalationWorkFlowONFLAG;
  }

  public void setSignOffEscalationWorkFlowONFLAG(boolean signOffEscalationWorkFlowONFLAG)
  {
    this.signOffEscalationWorkFlowONFLAG = signOffEscalationWorkFlowONFLAG;
  }

  public boolean isAfterReAssesedFlag()
  {
    return this.afterReAssesedFlag;
  }

  public void setAfterReAssesedFlag(boolean afterReAssesedFlag)
  {
    this.afterReAssesedFlag = afterReAssesedFlag;
  }

  public String getMyPendingPage()
  {
    return this.myPendingPage;
  }

  public void setMyPendingPage(String myPendingPage)
  {
    this.myPendingPage = myPendingPage;
  }

  public String getMyProcessedPage()
  {
    return this.myProcessedPage;
  }

  public void setMyProcessedPage(String myProcessedPage)
  {
    this.myProcessedPage = myProcessedPage;
  }

  public String getMappedCategory()
  {
    return this.mappedCategory;
  }

  public void setMappedCategory(String mappedCategory)
  {
    this.mappedCategory = mappedCategory;
  }

  public String getSource()
  {
    return this.source;
  }

  public void setSource(String source)
  {
    this.source = source;
  }
}