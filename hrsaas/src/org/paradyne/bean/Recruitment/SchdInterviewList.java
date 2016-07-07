package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class SchdInterviewList extends BeanBase
{
  private String requisitionCode = "";
  private String requisitionName = "";
  private String candidateCode = "";
  private String candidateName = "";
  private String intRoundType = "";
  private String intDate;
  private String intTime;
  private String venue;
  private String interviewerCode = "";
  private String interviewer = "";
  private String comments;
  private String listLength = "0";
  private String noData = "true";
  private String radioButton;
  private String interviewCode;
  private String interviewDtlCode;
  private String intStatus = "";
  private String makeOffer = "";
  private String nextRound = "";
  private String nextInterviewer;
  private String offerCode;
  private String buttonName;
  private String status;
  private String radioOffer;
  private String reqCodeOffer;
  private String myPage = "";
  private String show = "";
  private String requisitionId = "";
  private String candCode1 = "";
  private String DuprequisitionId = "";
  private String candidateName1 = "";
  private String intRound = "";
  private String intervId = "";
  private String intervDate = "";
  private String intervName = "";
  private String modeLength = "";
  private String totalRecords = "";
  private String stats = "";
  private String makeOfferLetter = "";
  private String intnextRound = "";
  private ArrayList list = null;

  private String viewFilterFlag = "";
  private String filterFlag = "";
  private boolean clearFlag = false;
  private String ChkSerch = "";
  private boolean enableFilterValue = false;
  private boolean showFilterValue = false;
  private boolean statusFlag = false;
  private boolean makeOfferFlag = false;
  private boolean nextRFlag = false;
  private String hideFilterFlag = "";
  private String appliedFilterFlag = "";
  private String checkFilterFlag = "false";
  private String DupreqName;
  private String nameOfreq = "";
  private String nameOfReq = "";
  private String reqNameId = "";
  private String codeOfCandidate = "";
  private String nameOfcandidate = "";
  private String searchFilterFlag = "false";

  private String searchFlag = "false";
  private String checkStatusList = "false";
  private String cancelDtlCode = "";
  private String hidReschFlag = "false";
  private String fakeRankName = "";
  private String cancelStatus = "false";
  private String intRoundTypeName = "";

  public String getIntRoundTypeName() {
    return this.intRoundTypeName;
  }

  public void setIntRoundTypeName(String intRoundTypeName) {
    this.intRoundTypeName = intRoundTypeName;
  }

  public String getCancelStatus() {
    return this.cancelStatus;
  }

  public void setCancelStatus(String cancelStatus) {
    this.cancelStatus = cancelStatus;
  }

  public String getFakeRankName() {
    return this.fakeRankName;
  }

  public void setFakeRankName(String fakeRankName) {
    this.fakeRankName = fakeRankName;
  }

  public String getCheckStatusList() {
    return this.checkStatusList;
  }

  public void setCheckStatusList(String checkStatusList) {
    this.checkStatusList = checkStatusList;
  }

  public String getDuprequisitionId() {
    return this.DuprequisitionId;
  }

  public void setDuprequisitionId(String duprequisitionId) {
    this.DuprequisitionId = duprequisitionId;
  }

  public boolean isClearFlag() {
    return this.clearFlag;
  }

  public void setClearFlag(boolean clearFlag) {
    this.clearFlag = clearFlag;
  }

  public String getChkSerch() {
    return this.ChkSerch;
  }

  public void setChkSerch(String chkSerch) {
    this.ChkSerch = chkSerch;
  }

  public boolean isEnableFilterValue() {
    return this.enableFilterValue;
  }

  public void setEnableFilterValue(boolean enableFilterValue) {
    this.enableFilterValue = enableFilterValue;
  }

  public boolean isShowFilterValue() {
    return this.showFilterValue;
  }

  public void setShowFilterValue(boolean showFilterValue) {
    this.showFilterValue = showFilterValue;
  }

  public boolean isStatusFlag() {
    return this.statusFlag;
  }

  public void setStatusFlag(boolean statusFlag) {
    this.statusFlag = statusFlag;
  }

  public boolean isMakeOfferFlag() {
    return this.makeOfferFlag;
  }

  public void setMakeOfferFlag(boolean makeOfferFlag) {
    this.makeOfferFlag = makeOfferFlag;
  }

  public boolean isNextRFlag() {
    return this.nextRFlag;
  }

  public void setNextRFlag(boolean nextRFlag) {
    this.nextRFlag = nextRFlag;
  }

  public String getHideFilterFlag() {
    return this.hideFilterFlag;
  }

  public void setHideFilterFlag(String hideFilterFlag) {
    this.hideFilterFlag = hideFilterFlag;
  }

  public String getAppliedFilterFlag() {
    return this.appliedFilterFlag;
  }

  public void setAppliedFilterFlag(String appliedFilterFlag) {
    this.appliedFilterFlag = appliedFilterFlag;
  }

  public String getCheckFilterFlag() {
    return this.checkFilterFlag;
  }

  public void setCheckFilterFlag(String checkFilterFlag) {
    this.checkFilterFlag = checkFilterFlag;
  }

  public String getDupreqName() {
    return this.DupreqName;
  }

  public void setDupreqName(String dupreqName) {
    this.DupreqName = dupreqName;
  }

  public String getSearchFlag() {
    return this.searchFlag;
  }

  public void setSearchFlag(String searchFlag) {
    this.searchFlag = searchFlag;
  }

  public String getRequisitionCode() {
    return this.requisitionCode;
  }

  public void setRequisitionCode(String requisitionCode) {
    this.requisitionCode = requisitionCode;
  }

  public String getRequisitionName() {
    return this.requisitionName;
  }

  public void setRequisitionName(String requisitionName) {
    this.requisitionName = requisitionName;
  }

  public String getCandidateCode() {
    return this.candidateCode;
  }

  public void setCandidateCode(String candidateCode) {
    this.candidateCode = candidateCode;
  }

  public String getCandidateName() {
    return this.candidateName;
  }

  public void setCandidateName(String candidateName) {
    this.candidateName = candidateName;
  }

  public String getIntRoundType() {
    return this.intRoundType;
  }

  public void setIntRoundType(String intRoundType) {
    this.intRoundType = intRoundType;
  }

  public String getIntDate() {
    return this.intDate;
  }

  public void setIntDate(String intDate) {
    this.intDate = intDate;
  }

  public String getIntTime() {
    return this.intTime;
  }

  public void setIntTime(String intTime) {
    this.intTime = intTime;
  }

  public String getVenue() {
    return this.venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String getInterviewer() {
    return this.interviewer;
  }

  public void setInterviewer(String interviewer) {
    this.interviewer = interviewer;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getListLength() {
    return this.listLength;
  }

  public void setListLength(String listLength) {
    this.listLength = listLength;
  }

  public ArrayList getList() {
    return this.list;
  }

  public void setList(ArrayList list) {
    this.list = list;
  }

  public String getInterviewerCode() {
    return this.interviewerCode;
  }

  public void setInterviewerCode(String interviewerCode) {
    this.interviewerCode = interviewerCode;
  }

  public String getNoData() {
    return this.noData;
  }

  public void setNoData(String noData) {
    this.noData = noData;
  }

  public String getRadioButton() {
    return this.radioButton;
  }

  public void setRadioButton(String radioButton) {
    this.radioButton = radioButton;
  }

  public String getInterviewCode() {
    return this.interviewCode;
  }

  public void setInterviewCode(String interviewCode) {
    this.interviewCode = interviewCode;
  }

  public String getInterviewDtlCode() {
    return this.interviewDtlCode;
  }

  public void setInterviewDtlCode(String interviewDtlCode) {
    this.interviewDtlCode = interviewDtlCode;
  }

  public String getIntStatus() {
    return this.intStatus;
  }

  public void setIntStatus(String intStatus) {
    this.intStatus = intStatus;
  }

  public String getMakeOffer() {
    return this.makeOffer;
  }

  public void setMakeOffer(String makeOffer) {
    this.makeOffer = makeOffer;
  }

  public String getNextRound() {
    return this.nextRound;
  }

  public void setNextRound(String nextRound) {
    this.nextRound = nextRound;
  }

  public String getNextInterviewer() {
    return this.nextInterviewer;
  }

  public void setNextInterviewer(String nextInterviewer) {
    this.nextInterviewer = nextInterviewer;
  }

  public String getOfferCode()
  {
    return this.offerCode;
  }

  public void setOfferCode(String offerCode)
  {
    this.offerCode = offerCode;
  }

  public String getButtonName()
  {
    return this.buttonName;
  }

  public void setButtonName(String buttonName)
  {
    this.buttonName = buttonName;
  }

  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getRadioOffer()
  {
    return this.radioOffer;
  }

  public void setRadioOffer(String radioOffer)
  {
    this.radioOffer = radioOffer;
  }

  public String getReqCodeOffer()
  {
    return this.reqCodeOffer;
  }

  public void setReqCodeOffer(String reqCodeOffer)
  {
    this.reqCodeOffer = reqCodeOffer;
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

  public String getRequisitionId() {
    return this.requisitionId;
  }

  public void setRequisitionId(String requisitionId) {
    this.requisitionId = requisitionId;
  }

  public String getCandCode1() {
    return this.candCode1;
  }

  public void setCandCode1(String candCode1) {
    this.candCode1 = candCode1;
  }

  public String getCandidateName1() {
    return this.candidateName1;
  }

  public void setCandidateName1(String candidateName1) {
    this.candidateName1 = candidateName1;
  }

  public String getIntRound() {
    return this.intRound;
  }

  public void setIntRound(String intRound) {
    this.intRound = intRound;
  }

  public String getIntervId() {
    return this.intervId;
  }

  public void setIntervId(String intervId) {
    this.intervId = intervId;
  }

  public String getIntervDate() {
    return this.intervDate;
  }

  public void setIntervDate(String intervDate) {
    this.intervDate = intervDate;
  }

  public String getIntervName() {
    return this.intervName;
  }

  public void setIntervName(String intervName) {
    this.intervName = intervName;
  }

  public String getModeLength() {
    return this.modeLength;
  }

  public void setModeLength(String modeLength) {
    this.modeLength = modeLength;
  }

  public String getTotalRecords() {
    return this.totalRecords;
  }

  public void setTotalRecords(String totalRecords) {
    this.totalRecords = totalRecords;
  }

  public String getStats() {
    return this.stats;
  }

  public void setStats(String stats) {
    this.stats = stats;
  }

  public String getMakeOfferLetter() {
    return this.makeOfferLetter;
  }

  public void setMakeOfferLetter(String makeOfferLetter) {
    this.makeOfferLetter = makeOfferLetter;
  }

  public String getIntnextRound() {
    return this.intnextRound;
  }

  public void setIntnextRound(String intnextRound) {
    this.intnextRound = intnextRound;
  }

  public String getViewFilterFlag() {
    return this.viewFilterFlag;
  }

  public void setViewFilterFlag(String viewFilterFlag) {
    this.viewFilterFlag = viewFilterFlag;
  }

  public String getFilterFlag() {
    return this.filterFlag;
  }

  public void setFilterFlag(String filterFlag) {
    this.filterFlag = filterFlag;
  }

  public String getNameOfreq() {
    return this.nameOfreq;
  }

  public void setNameOfreq(String nameOfreq) {
    this.nameOfreq = nameOfreq;
  }

  public String getReqNameId() {
    return this.reqNameId;
  }

  public void setReqNameId(String reqNameId) {
    this.reqNameId = reqNameId;
  }

  public String getSearchFilterFlag() {
    return this.searchFilterFlag;
  }

  public void setSearchFilterFlag(String searchFilterFlag) {
    this.searchFilterFlag = searchFilterFlag;
  }

  public String getCodeOfCandidate() {
    return this.codeOfCandidate;
  }

  public void setCodeOfCandidate(String codeOfCandidate) {
    this.codeOfCandidate = codeOfCandidate;
  }

  public String getNameOfcandidate() {
    return this.nameOfcandidate;
  }

  public void setNameOfcandidate(String nameOfcandidate) {
    this.nameOfcandidate = nameOfcandidate;
  }

  public String getNameOfReq() {
    return this.nameOfReq;
  }

  public void setNameOfReq(String nameOfReq) {
    this.nameOfReq = nameOfReq;
  }

  public String getCancelDtlCode() {
    return this.cancelDtlCode;
  }

  public void setCancelDtlCode(String cancelDtlCode) {
    this.cancelDtlCode = cancelDtlCode;
  }

  public String getHidReschFlag() {
    return this.hidReschFlag;
  }

  public void setHidReschFlag(String hidReschFlag) {
    this.hidReschFlag = hidReschFlag;
  }
}