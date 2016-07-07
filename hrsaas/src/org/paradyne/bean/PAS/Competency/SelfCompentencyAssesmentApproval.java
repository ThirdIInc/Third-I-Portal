package org.paradyne.bean.PAS.Competency;

import java.util.List;
import java.util.TreeMap;
import org.paradyne.lib.BeanBase;

public class SelfCompentencyAssesmentApproval extends BeanBase
{
  private String source = "";

  private String approverdevPlanComment = "";

  private String myPage = "";

  private String myPendingPage = "";

  private String myProcessedPage = "";

  private String listType = "";

  private String competencyMasterCode = "";
  private boolean pendingRecordListFlag;
  private List<SelfCompentencyAssesmentApproval> pendingRecordList;
  private String competencyIDItr = "";

  private String competencyAssesmentIDItr = "";

  private String competencyNameItr = "";

  private String competencyAssesmentEmployeeIDItr = "";

  private String competencyAssesmentEmployeeNameItr = "";

  private String competencyAssesmentDepartmentItr = "";

  private String competencyAssesmentDesignationItr = "";

  private String competencyAssesmentReviewDateItr = "";
  private boolean pendingEmptyFlag;
  private boolean processedRecordListFlag;
  private List<SelfCompentencyAssesmentApproval> processedRecordList;
  private boolean processedEmptyFlag;
  private String competencyAssesmentReviewCompletedDateItr = "";

  private String competencyAssesmentLevelItr = "";
  private boolean reAssessedRecordListFlag;
  private List<SelfCompentencyAssesmentApproval> reAssessedRecordList;
  private boolean reAssessedEmptyFlag;
  private boolean afterFinalizedFlag;
  private String MappedCategory = "";

  private String allRatingCommentsCompetencyIDItr = "";

  private String allRatingCommentsCompetencyNameItr = "";

  private String allRatingCommentsProficiencyLevelItr = "";

  private String allRatingCommentsEmpRatingItr = "";

  private String allRatingCommentsSelfApproverCommentsItr = "";

  private String allRatingCommentsSelfApproverRatingItr = "";
  private List<SelfCompentencyAssesmentApproval> allRatingAndCommentsIterator;
  private boolean allRatingCommentsPageFlag;
  private boolean processedListPageFlag;
  private boolean nonProcessListPageFlag;
  private boolean pendingEmpRatingCommentsPreviousApproverRatingCommentsFlag;
  private boolean processedEmpRatingCommentsPreviousApproverRatingCommentsFlag;
  private String approverCompetencyKey = "";

  private String approverCompetencyID = "";

  private String approverCompetencyTitle = "";

  private String approverCompetencyDescription = "";

  private String approverCompetencyIndicator = "";
  private boolean detailPageFlag;
  private String approverSelfComments = "";

  private String approverSelfRating = "";

  private String approverCompetencyAssesmentCode = "";

  private String approverCompetencyEmployeeCode = "";

  private String competencyAssesmentLevel = "";
  private boolean previousApproverRatingCommentsPageFlag;
  private List<SelfCompentencyAssesmentApproval> previousApproverRatingCommentsIterator;
  private String previousApproverName = "";
  private TreeMap<String, String> competencyTitleMap;
  private String competencyTitleDropDown = "";
  private boolean signOffForFinalizedRecordFlag;
  private String employeeSelfComments = "";

  private String employeeSelfRating = "";
  private boolean finalPageFlag;
  private boolean finalPageSignOffFlag;
  private String empId = "";

  private String empToken = "";

  private String empName = "";

  private String empBranch = "";

  private String empDepartment = "";

  private String empDesignation = "";

  private String empReportingTo = "";

  private String devPlanCode = "";
  private String devPlanName = "";
  private String devTargetDate = "";
  private String devRequired = "";

  public String getDevRequired()
  {
    return this.devRequired;
  }

  public void setDevRequired(String devRequired)
  {
    this.devRequired = devRequired;
  }

  public String getDevPlanCode()
  {
    return this.devPlanCode;
  }

  public void setDevPlanCode(String devPlanCode)
  {
    this.devPlanCode = devPlanCode;
  }

  public String getDevPlanName()
  {
    return this.devPlanName;
  }

  public void setDevPlanName(String devPlanName)
  {
    this.devPlanName = devPlanName;
  }

  public String getDevTargetDate()
  {
    return this.devTargetDate;
  }

  public void setDevTargetDate(String devTargetDate)
  {
    this.devTargetDate = devTargetDate;
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

  public String getAllRatingCommentsCompetencyIDItr()
  {
    return this.allRatingCommentsCompetencyIDItr;
  }

  public void setAllRatingCommentsCompetencyIDItr(String allRatingCommentsCompetencyIDItr)
  {
    this.allRatingCommentsCompetencyIDItr = allRatingCommentsCompetencyIDItr;
  }

  public String getAllRatingCommentsCompetencyNameItr()
  {
    return this.allRatingCommentsCompetencyNameItr;
  }

  public void setAllRatingCommentsCompetencyNameItr(String allRatingCommentsCompetencyNameItr)
  {
    this.allRatingCommentsCompetencyNameItr = allRatingCommentsCompetencyNameItr;
  }

  public String getAllRatingCommentsProficiencyLevelItr()
  {
    return this.allRatingCommentsProficiencyLevelItr;
  }

  public void setAllRatingCommentsProficiencyLevelItr(String allRatingCommentsProficiencyLevelItr)
  {
    this.allRatingCommentsProficiencyLevelItr = allRatingCommentsProficiencyLevelItr;
  }

  public String getAllRatingCommentsSelfApproverCommentsItr()
  {
    return this.allRatingCommentsSelfApproverCommentsItr;
  }

  public void setAllRatingCommentsSelfApproverCommentsItr(String allRatingCommentsSelfApproverCommentsItr)
  {
    this.allRatingCommentsSelfApproverCommentsItr = allRatingCommentsSelfApproverCommentsItr;
  }

  public String getAllRatingCommentsSelfApproverRatingItr()
  {
    return this.allRatingCommentsSelfApproverRatingItr;
  }

  public void setAllRatingCommentsSelfApproverRatingItr(String allRatingCommentsSelfApproverRatingItr)
  {
    this.allRatingCommentsSelfApproverRatingItr = allRatingCommentsSelfApproverRatingItr;
  }

  public String getListType()
  {
    return this.listType;
  }

  public void setListType(String listType)
  {
    this.listType = listType;
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

  public String getCompetencyNameItr()
  {
    return this.competencyNameItr;
  }

  public void setCompetencyNameItr(String competencyNameItr)
  {
    this.competencyNameItr = competencyNameItr;
  }

  public String getCompetencyAssesmentEmployeeIDItr()
  {
    return this.competencyAssesmentEmployeeIDItr;
  }

  public void setCompetencyAssesmentEmployeeIDItr(String competencyAssesmentEmployeeIDItr)
  {
    this.competencyAssesmentEmployeeIDItr = competencyAssesmentEmployeeIDItr;
  }

  public String getCompetencyAssesmentEmployeeNameItr()
  {
    return this.competencyAssesmentEmployeeNameItr;
  }

  public void setCompetencyAssesmentEmployeeNameItr(String competencyAssesmentEmployeeNameItr)
  {
    this.competencyAssesmentEmployeeNameItr = competencyAssesmentEmployeeNameItr;
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

  public String getCompetencyAssesmentReviewDateItr()
  {
    return this.competencyAssesmentReviewDateItr;
  }

  public void setCompetencyAssesmentReviewDateItr(String competencyAssesmentReviewDateItr)
  {
    this.competencyAssesmentReviewDateItr = competencyAssesmentReviewDateItr;
  }

  public boolean isPendingRecordListFlag()
  {
    return this.pendingRecordListFlag;
  }

  public void setPendingRecordListFlag(boolean pendingRecordListFlag)
  {
    this.pendingRecordListFlag = pendingRecordListFlag;
  }

  public List<SelfCompentencyAssesmentApproval> getPendingRecordList()
  {
    return this.pendingRecordList;
  }

  public void setPendingRecordList(List<SelfCompentencyAssesmentApproval> pendingRecordList)
  {
    this.pendingRecordList = pendingRecordList;
  }

  public boolean isPendingEmptyFlag()
  {
    return this.pendingEmptyFlag;
  }

  public void setPendingEmptyFlag(boolean pendingEmptyFlag)
  {
    this.pendingEmptyFlag = pendingEmptyFlag;
  }

  public boolean isProcessedRecordListFlag()
  {
    return this.processedRecordListFlag;
  }

  public void setProcessedRecordListFlag(boolean processedRecordListFlag)
  {
    this.processedRecordListFlag = processedRecordListFlag;
  }

  public List<SelfCompentencyAssesmentApproval> getProcessedRecordList()
  {
    return this.processedRecordList;
  }

  public void setProcessedRecordList(List<SelfCompentencyAssesmentApproval> processedRecordList)
  {
    this.processedRecordList = processedRecordList;
  }

  public boolean isProcessedEmptyFlag()
  {
    return this.processedEmptyFlag;
  }

  public void setProcessedEmptyFlag(boolean processedEmptyFlag)
  {
    this.processedEmptyFlag = processedEmptyFlag;
  }

  public String getCompetencyAssesmentReviewCompletedDateItr()
  {
    return this.competencyAssesmentReviewCompletedDateItr;
  }

  public void setCompetencyAssesmentReviewCompletedDateItr(String competencyAssesmentReviewCompletedDateItr)
  {
    this.competencyAssesmentReviewCompletedDateItr = competencyAssesmentReviewCompletedDateItr;
  }

  public List<SelfCompentencyAssesmentApproval> getAllRatingAndCommentsIterator()
  {
    return this.allRatingAndCommentsIterator;
  }

  public void setAllRatingAndCommentsIterator(List<SelfCompentencyAssesmentApproval> allRatingAndCommentsIterator)
  {
    this.allRatingAndCommentsIterator = allRatingAndCommentsIterator;
  }

  public String getAllRatingCommentsEmpRatingItr()
  {
    return this.allRatingCommentsEmpRatingItr;
  }

  public void setAllRatingCommentsEmpRatingItr(String allRatingCommentsEmpRatingItr)
  {
    this.allRatingCommentsEmpRatingItr = allRatingCommentsEmpRatingItr;
  }

  public boolean isAllRatingCommentsPageFlag()
  {
    return this.allRatingCommentsPageFlag;
  }

  public void setAllRatingCommentsPageFlag(boolean allRatingCommentsPageFlag)
  {
    this.allRatingCommentsPageFlag = allRatingCommentsPageFlag;
  }

  public boolean isProcessedListPageFlag()
  {
    return this.processedListPageFlag;
  }

  public void setProcessedListPageFlag(boolean processedListPageFlag)
  {
    this.processedListPageFlag = processedListPageFlag;
  }

  public boolean isNonProcessListPageFlag()
  {
    return this.nonProcessListPageFlag;
  }

  public void setNonProcessListPageFlag(boolean nonProcessListPageFlag)
  {
    this.nonProcessListPageFlag = nonProcessListPageFlag;
  }

  public String getApproverCompetencyKey()
  {
    return this.approverCompetencyKey;
  }

  public void setApproverCompetencyKey(String approverCompetencyKey)
  {
    this.approverCompetencyKey = approverCompetencyKey;
  }

  public String getApproverCompetencyID()
  {
    return this.approverCompetencyID;
  }

  public void setApproverCompetencyID(String approverCompetencyID)
  {
    this.approverCompetencyID = approverCompetencyID;
  }

  public String getApproverCompetencyTitle()
  {
    return this.approverCompetencyTitle;
  }

  public void setApproverCompetencyTitle(String approverCompetencyTitle)
  {
    this.approverCompetencyTitle = approverCompetencyTitle;
  }

  public String getApproverCompetencyDescription()
  {
    return this.approverCompetencyDescription;
  }

  public void setApproverCompetencyDescription(String approverCompetencyDescription)
  {
    this.approverCompetencyDescription = approverCompetencyDescription;
  }

  public String getApproverCompetencyIndicator()
  {
    return this.approverCompetencyIndicator;
  }

  public void setApproverCompetencyIndicator(String approverCompetencyIndicator)
  {
    this.approverCompetencyIndicator = approverCompetencyIndicator;
  }

  public boolean isDetailPageFlag()
  {
    return this.detailPageFlag;
  }

  public void setDetailPageFlag(boolean detailPageFlag)
  {
    this.detailPageFlag = detailPageFlag;
  }

  public String getApproverSelfComments()
  {
    return this.approverSelfComments;
  }

  public void setApproverSelfComments(String approverSelfComments)
  {
    this.approverSelfComments = approverSelfComments;
  }

  public String getApproverSelfRating()
  {
    return this.approverSelfRating;
  }

  public void setApproverSelfRating(String approverSelfRating)
  {
    this.approverSelfRating = approverSelfRating;
  }

  public String getApproverCompetencyAssesmentCode()
  {
    return this.approverCompetencyAssesmentCode;
  }

  public void setApproverCompetencyAssesmentCode(String approverCompetencyAssesmentCode)
  {
    this.approverCompetencyAssesmentCode = approverCompetencyAssesmentCode;
  }

  public String getApproverCompetencyEmployeeCode()
  {
    return this.approverCompetencyEmployeeCode;
  }

  public void setApproverCompetencyEmployeeCode(String approverCompetencyEmployeeCode)
  {
    this.approverCompetencyEmployeeCode = approverCompetencyEmployeeCode;
  }

  public String getEmployeeSelfComments()
  {
    return this.employeeSelfComments;
  }

  public void setEmployeeSelfComments(String employeeSelfComments)
  {
    this.employeeSelfComments = employeeSelfComments;
  }

  public String getEmployeeSelfRating()
  {
    return this.employeeSelfRating;
  }

  public void setEmployeeSelfRating(String employeeSelfRating)
  {
    this.employeeSelfRating = employeeSelfRating;
  }

  public boolean isFinalPageFlag()
  {
    return this.finalPageFlag;
  }

  public void setFinalPageFlag(boolean finalPageFlag)
  {
    this.finalPageFlag = finalPageFlag;
  }

  public String getCompetencyAssesmentLevelItr()
  {
    return this.competencyAssesmentLevelItr;
  }

  public void setCompetencyAssesmentLevelItr(String competencyAssesmentLevelItr)
  {
    this.competencyAssesmentLevelItr = competencyAssesmentLevelItr;
  }

  public String getCompetencyAssesmentLevel()
  {
    return this.competencyAssesmentLevel;
  }

  public void setCompetencyAssesmentLevel(String competencyAssesmentLevel)
  {
    this.competencyAssesmentLevel = competencyAssesmentLevel;
  }

  public String getCompetencyMasterCode()
  {
    return this.competencyMasterCode;
  }

  public void setCompetencyMasterCode(String competencyMasterCode)
  {
    this.competencyMasterCode = competencyMasterCode;
  }

  public boolean isPreviousApproverRatingCommentsPageFlag()
  {
    return this.previousApproverRatingCommentsPageFlag;
  }

  public void setPreviousApproverRatingCommentsPageFlag(boolean previousApproverRatingCommentsPageFlag)
  {
    this.previousApproverRatingCommentsPageFlag = previousApproverRatingCommentsPageFlag;
  }

  public List<SelfCompentencyAssesmentApproval> getPreviousApproverRatingCommentsIterator()
  {
    return this.previousApproverRatingCommentsIterator;
  }

  public void setPreviousApproverRatingCommentsIterator(List<SelfCompentencyAssesmentApproval> previousApproverRatingCommentsIterator)
  {
    this.previousApproverRatingCommentsIterator = previousApproverRatingCommentsIterator;
  }

  public String getPreviousApproverName()
  {
    return this.previousApproverName;
  }

  public void setPreviousApproverName(String previousApproverName)
  {
    this.previousApproverName = previousApproverName;
  }

  public TreeMap<String, String> getCompetencyTitleMap()
  {
    return this.competencyTitleMap;
  }

  public void setCompetencyTitleMap(TreeMap<String, String> competencyTitleMap)
  {
    this.competencyTitleMap = competencyTitleMap;
  }

  public String getCompetencyTitleDropDown()
  {
    return this.competencyTitleDropDown;
  }

  public void setCompetencyTitleDropDown(String competencyTitleDropDown)
  {
    this.competencyTitleDropDown = competencyTitleDropDown;
  }

  public boolean isPendingEmpRatingCommentsPreviousApproverRatingCommentsFlag()
  {
    return this.pendingEmpRatingCommentsPreviousApproverRatingCommentsFlag;
  }

  public void setPendingEmpRatingCommentsPreviousApproverRatingCommentsFlag(boolean pendingEmpRatingCommentsPreviousApproverRatingCommentsFlag)
  {
    this.pendingEmpRatingCommentsPreviousApproverRatingCommentsFlag = pendingEmpRatingCommentsPreviousApproverRatingCommentsFlag;
  }

  public boolean isProcessedEmpRatingCommentsPreviousApproverRatingCommentsFlag()
  {
    return this.processedEmpRatingCommentsPreviousApproverRatingCommentsFlag;
  }

  public void setProcessedEmpRatingCommentsPreviousApproverRatingCommentsFlag(boolean processedEmpRatingCommentsPreviousApproverRatingCommentsFlag)
  {
    this.processedEmpRatingCommentsPreviousApproverRatingCommentsFlag = processedEmpRatingCommentsPreviousApproverRatingCommentsFlag;
  }

  public boolean isFinalPageSignOffFlag()
  {
    return this.finalPageSignOffFlag;
  }

  public void setFinalPageSignOffFlag(boolean finalPageSignOffFlag)
  {
    this.finalPageSignOffFlag = finalPageSignOffFlag;
  }

  public boolean isReAssessedRecordListFlag()
  {
    return this.reAssessedRecordListFlag;
  }

  public void setReAssessedRecordListFlag(boolean reAssessedRecordListFlag)
  {
    this.reAssessedRecordListFlag = reAssessedRecordListFlag;
  }

  public boolean isReAssessedEmptyFlag()
  {
    return this.reAssessedEmptyFlag;
  }

  public void setReAssessedEmptyFlag(boolean reAssessedEmptyFlag)
  {
    this.reAssessedEmptyFlag = reAssessedEmptyFlag;
  }

  public void setReAssessedRecordList(List<SelfCompentencyAssesmentApproval> reAssessedRecordList)
  {
    this.reAssessedRecordList = reAssessedRecordList;
  }

  public String getMyPage()
  {
    return this.myPage;
  }

  public void setMyPage(String myPage)
  {
    this.myPage = myPage;
  }

  public List<SelfCompentencyAssesmentApproval> getReAssessedRecordList()
  {
    return this.reAssessedRecordList;
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

  public boolean isAfterFinalizedFlag()
  {
    return this.afterFinalizedFlag;
  }

  public void setAfterFinalizedFlag(boolean afterFinalizedFlag)
  {
    this.afterFinalizedFlag = afterFinalizedFlag;
  }

  public boolean isSignOffForFinalizedRecordFlag()
  {
    return this.signOffForFinalizedRecordFlag;
  }

  public void setSignOffForFinalizedRecordFlag(boolean signOffForFinalizedRecordFlag)
  {
    this.signOffForFinalizedRecordFlag = signOffForFinalizedRecordFlag;
  }

  public String getMappedCategory()
  {
    return this.MappedCategory;
  }

  public void setMappedCategory(String mappedCategory)
  {
    this.MappedCategory = mappedCategory;
  }

  public String getSource()
  {
    return this.source;
  }

  public void setSource(String source)
  {
    this.source = source;
  }
  public String getApproverdevPlanComment() {
    return this.approverdevPlanComment;
  }
  public void setApproverdevPlanComment(String approverdevPlanComment) {
    this.approverdevPlanComment = approverdevPlanComment;
  }
}