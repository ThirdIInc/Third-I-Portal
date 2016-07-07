package org.struts.action.recruitment;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.Recruitment.SchdInterviewList;
import org.paradyne.model.recruitment.SchdInterviewListModel;
import org.struts.lib.ParaActionSupport;

public class SchdInterviewListAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(SchdInterviewListAction.class);
  SchdInterviewList schdIntList;

  public SchdInterviewList getSchdIntList()
  {
    return this.schdIntList;
  }

  public void setSchdIntList(SchdInterviewList schdIntList) {
    this.schdIntList = schdIntList;
  }

  public void prepare_local() throws Exception {
    this.schdIntList = new SchdInterviewList();
    this.schdIntList.setMenuCode(780);
  }

  public Object getModel()
  {
    return this.schdIntList;
  }

  public void prepare_withLoginProfileDetails()
    throws Exception
  {
  }

  public String input()
  {
    SchdInterviewListModel model = new SchdInterviewListModel();
    model.initiate(this.context, this.session);

    String status = "N";
    String stat = "Scheduled Interviews";
    this.request.setAttribute("stat", stat);

    String msg = getMessage("reqs.code");
    String msg1 = getMessage("cand.name");
    String msg2 = getMessage("interVType");
    String msg3 = getMessage("intDate");
    String msg4 = getMessage("Interviewer");
    model.getSchdInterviewList(this.schdIntList, status, msg, msg1, msg2, msg3, msg4, this.request);
    model.terminate();
    return "success";
  }

  public String interviewStatus()
  {
    SchdInterviewListModel model = new SchdInterviewListModel();
    model.initiate(this.context, this.session);

    this.schdIntList.setCheckFilterFlag("true");
    String req = getMessage("reqs.code");
    String cand = getMessage("cand.name");
    String stat1 = getMessage("intstatus");
    String offer = getMessage("moffer");
    String round = getMessage("nxtround");
    model.getintStatusList(this.schdIntList, "Y", req, cand, stat1, offer, round, this.request);
    this.schdIntList.setChkSerch("true");

    if (this.schdIntList.getSearchFilterFlag().equals("true"))
      this.schdIntList.setSearchFilterFlag("true");
    else
      this.schdIntList.setSearchFilterFlag("false");
    model.terminate();
    return "intStatusList";
  }

  public String toOfferDetails()
    throws Exception
  {
    this.request.setAttribute("status", "Y");
    return "toOfferDetails";
  }

  public String scheduledInterviews()
  {
    SchdInterviewListModel model = new SchdInterviewListModel();
    model.initiate(this.context, this.session);
    String status = "N";
    String stat = "";
    if (this.schdIntList.getCancelStatus().equals("true")) {
      stat = "Canceled Interviews";
    }
    else {
      stat = "Scheduled Interviews";
    }

    this.schdIntList.setCheckFilterFlag("true");
    String msg = getMessage("reqs.code");
    String msg1 = getMessage("cand.name");
    String msg2 = getMessage("interVType");
    String msg3 = getMessage("intDate");
    String msg4 = getMessage("Interviewer");
    this.request.setAttribute("stat", stat);

    model.getSchdInterviewList(this.schdIntList, status, msg, msg1, msg2, msg3, msg4, this.request);
    this.schdIntList.setChkSerch("true");

    if (this.schdIntList.getSearchFlag().equals("true"))
      this.schdIntList.setSearchFlag("true");
    else {
      this.schdIntList.setSearchFlag("false");
    }
    model.terminate();
    return "success";
  }

  public String candidatesearch() throws Exception
  {
    SchdInterviewListModel model = new SchdInterviewListModel();
    model.initiate(this.context, this.session);
    String status = "Y";
    String stat = "Interview Status";
    this.request.setAttribute("stat", stat);

    String msg = getMessage("reqs.code");
    String msg1 = getMessage("cand.name");
    String msg2 = getMessage("intstatus");
    String msg3 = getMessage("moffer");
    String msg4 = getMessage("nxtround");
    this.request.setAttribute("stat", stat);

    model.terminate();
    return "success";
  }

  public String clearFilter()
    throws Exception
  {
    SchdInterviewListModel model = new SchdInterviewListModel();
    model.initiate(this.context, this.session);
    this.schdIntList.setRequisitionId("");
    this.schdIntList.setDuprequisitionId("");
    this.schdIntList.setRequisitionName("");
    this.schdIntList.setCandCode1("");
    this.schdIntList.setCandidateName1("");
    this.schdIntList.setIntRound("");
    this.schdIntList.setIntervDate("");
    this.schdIntList.setIntervName("");
    this.schdIntList.setStats("");
    this.schdIntList.setMakeOfferLetter("");
    this.schdIntList.setIntnextRound("");
    this.schdIntList.setChkSerch("");
    String req = getMessage("reqs.code");
    String cand = getMessage("cand.name");
    String stat1 = getMessage("intstatus");
    String offer = getMessage("moffer");
    String round = getMessage("nxtround");
    model.getintStatusList(this.schdIntList, "Y", req, cand, stat1, offer, round, this.request);

    if (this.schdIntList.getSearchFlag().equals("true"))
      this.schdIntList.setSearchFlag("true");
    else
      this.schdIntList.setSearchFlag("false");
    this.schdIntList.setShowFilterValue(true);
    model.terminate();
    return "success";
  }

  public String filterClear()
    throws Exception
  {
    SchdInterviewListModel model = new SchdInterviewListModel();
    model.initiate(this.context, this.session);
    String status = "N";
    String stat = "Scheduled Interviews";
    this.request.setAttribute("stat", stat);

    this.schdIntList.setDuprequisitionId("");
    this.schdIntList.setDupreqName("");
    this.schdIntList.setCandCode1("");
    this.schdIntList.setCandidateName1("");
    this.schdIntList.setIntRound("");
    this.schdIntList.setIntervDate("");
    this.schdIntList.setIntervName("");
    this.schdIntList.setIntervId("");
    this.schdIntList.setStats("");

    this.schdIntList.setMakeOfferLetter("");
    this.schdIntList.setIntnextRound("");
    this.schdIntList.setChkSerch("");
    String msg = getMessage("reqs.code");
    String msg1 = getMessage("cand.name");
    String msg2 = getMessage("interVType");
    String msg3 = getMessage("intDate");
    String msg4 = getMessage("Interviewer");
    model.getSchdInterviewList(this.schdIntList, status, msg, msg1, msg2, msg3, msg4, this.request);
    if (this.schdIntList.getSearchFlag().equals("true"))
      this.schdIntList.setSearchFlag("true");
    else
      this.schdIntList.setSearchFlag("false");
    this.schdIntList.setShowFilterValue(true);
    model.terminate();
    return "success";
  }

  public String reset() throws Exception {
    SchdInterviewListModel model = new SchdInterviewListModel();
    model.initiate(this.context, this.session);
    this.schdIntList.setRequisitionId("");
    this.schdIntList.setDuprequisitionId("");
    this.schdIntList.setRequisitionName("");
    this.schdIntList.setCandCode1("");
    this.schdIntList.setCandidateName1("");
    this.schdIntList.setIntRound("");
    this.schdIntList.setIntervDate("");
    this.schdIntList.setIntervName("");
    this.schdIntList.setStats("");
    this.schdIntList.setMakeOfferLetter("");
    this.schdIntList.setIntnextRound("");

    String msg = getMessage("reqs.code");
    String msg1 = getMessage("cand.name");
    String msg2 = getMessage("interVType");
    String msg3 = getMessage("intDate");
    String msg4 = getMessage("Interviewer");

    model.terminate();
    return "success";
  }

  public String cancelReq()
  {
    SchdInterviewListModel model = new SchdInterviewListModel();
    model.initiate(this.context, this.session);
    boolean result = model.callCancelReq(this.schdIntList);
    if (result)
      addActionMessage("Interview Schedule cancelled successfully.");
    else {
      addActionMessage("Interview Schedule can't cancel.");
    }
    model.terminate();
    return input();
  }

  public String f9Requisition()
    throws Exception
  {
    String query = " SELECT  REQS_NAME,rank_name,to_char(REQS_DATE,'dd-mm-yyyy'),HRMS_REC_REQS_HDR.REQS_CODE  FROM HRMS_REC_REQS_HDR   inner join hrms_rank on(hrms_rank.RANK_ID = hrms_rec_reqs_hdr.REQS_POSITION)  WHERE REQS_APPROVAL_STATUS IN ('A','Q')  ORDER BY REQS_CODE ";

    String[] headers = { getMessage("reqs.code"), getMessage("position"), getMessage("reqs.date") };

    String[] headerWidth = { "20", "20", "10" };

    String[] fieldNames = { "nameOfReq", "fakeRankName", "fakeRankName", "reqNameId" };

    int[] columnIndex = { 0, 1, 2, 3 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9dupRequisition()
    throws Exception
  {
    if (this.schdIntList.getSearchFlag().equals("true"))
      this.schdIntList.setSearchFlag("true");
    else {
      this.schdIntList.setSearchFlag("false");
    }

    String query = " SELECT  REQS_NAME,rank_name,to_char(REQS_DATE,'dd-mm-yyyy'),HRMS_REC_REQS_HDR.REQS_CODE  FROM HRMS_REC_REQS_HDR   inner join hrms_rank on(hrms_rank.RANK_ID = hrms_rec_reqs_hdr.REQS_POSITION)  WHERE REQS_APPROVAL_STATUS IN ('A','Q')  ORDER BY REQS_CODE ";

    String[] headers = { getMessage("reqs.code"), getMessage("position"), getMessage("reqs.date") };

    String[] headerWidth = { "20", "20", "10" };

    String[] fieldNames = { "DupreqName", "fakeRankName", "fakeRankName", "DuprequisitionId" };

    int[] columnIndex = { 0, 1, 2, 3 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }

  public String f9CandidateAction()
  {
    if (this.schdIntList.getSearchFlag().equals("true"))
      this.schdIntList.setSearchFlag("true");
    else {
      this.schdIntList.setSearchFlag("false");
    }

    String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ,CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY CAND_CODE";

    String[] headers = { getMessage("cand.name") };

    String[] headerWidth = { "30", "60" };

    String[] fieldNames = { "candidateName1", "candCode1" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9CandidateNameAction()
  {
    String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME ,CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY CAND_CODE";

    String[] headers = { getMessage("cand.name") };

    String[] headerWidth = { "30", "60" };

    String[] fieldNames = { "nameOfcandidate", "codeOfCandidate" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

    return "f9page";
  }

  public String f9Interviewer()
    throws Exception
  {
    if (this.schdIntList.getSearchFlag().equals("true"))
      this.schdIntList.setSearchFlag("true");
    else {
      this.schdIntList.setSearchFlag("false");
    }

    String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC ORDER BY EMP_ID";

    String[] headers = { getMessage("Interviewer") };

    String[] headerWidth = { "20" };

    String[] fieldNames = { "intervName", "intervId" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
      submitFlag, submitToMethod);
    return "f9page";
  }
}