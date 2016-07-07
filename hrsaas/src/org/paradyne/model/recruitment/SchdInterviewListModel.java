package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.Recruitment.SchdInterviewList;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

public class SchdInterviewListModel extends ModelBase
{
  static Logger logger = Logger.getLogger(SchdInterviewListModel.class);

  public void getSchdInterviewList(SchdInterviewList schdIntList, String intConductStatus, String msg, String msg1, String msg2, String msg3, String msg4, HttpServletRequest request)
  {
    int count = 0;
    String concatStr = "";
    Object[][] schdIntData = (Object[][])null;
    ArrayList tableList = new ArrayList();
    String query = "";
    try {
      if (schdIntList.getCancelStatus().equals("true")) {
        query = "SELECT RESUME_REQS_CODE, REQS_NAME, RESUME_CAND_CODE, CAND_FIRST_NAME||' '||CAND_LAST_NAME,  INT_ROUND_TYPE, TO_CHAR(INT_DATE,'DD-MM-YYYY'),INT_TIME, INT_VENUE_DET, INT_VIEWER_EMPID,  A1.EMP_FNAME||' '||A1.EMP_LNAME,INT_COMMENTS,INT_CODE,INT_DTL_CODE, NVL(ROUND_TYPE,'') FROM HRMS_REC_RESUME_BANK  INNER JOIN HRMS_REC_SCHINT_DTL ON (HRMS_REC_SCHINT_DTL.INT_CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE and INT_CONDUCT_STATUS = 'C'  AND HRMS_REC_SCHINT_DTL.INT_REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)  INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE)  INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)  INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)   LEFT JOIN HRMS_REC_ROUND_TYPE ON (HRMS_REC_ROUND_TYPE.ROUND_CODE = HRMS_REC_SCHINT_DTL.INT_ROUND_TYPE)  WHERE (RESUME_SHEDULE_STATUS = 'I' OR RESUME_SHEDULE_STATUS = 'F') AND RESUME_REC_EMPID = " + 
          schdIntList.getUserEmpId() + " ";
      }
      else {
        query = "SELECT RESUME_REQS_CODE, REQS_NAME, RESUME_CAND_CODE, CAND_FIRST_NAME||' '||CAND_LAST_NAME,  INT_ROUND_TYPE, TO_CHAR(INT_DATE,'DD-MM-YYYY'),INT_TIME, INT_VENUE_DET, INT_VIEWER_EMPID,  A1.EMP_FNAME||' '||A1.EMP_LNAME,INT_COMMENTS,INT_CODE,INT_DTL_CODE, NVL(ROUND_TYPE,'') FROM HRMS_REC_RESUME_BANK  INNER JOIN HRMS_REC_SCHINT_DTL ON (HRMS_REC_SCHINT_DTL.INT_CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE and INT_CONDUCT_STATUS = '" + 
          intConductStatus + 
          "' AND HRMS_REC_SCHINT_DTL.INT_REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE) " + 
          " INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) " + 
          " INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID) " + 
          " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE) " + 
          " LEFT JOIN HRMS_REC_ROUND_TYPE ON (HRMS_REC_ROUND_TYPE.ROUND_CODE = HRMS_REC_SCHINT_DTL.INT_ROUND_TYPE) " + 
          " WHERE (RESUME_SHEDULE_STATUS = 'I' OR RESUME_SHEDULE_STATUS = 'F') AND RESUME_REC_EMPID = " + schdIntList.getUserEmpId() + " ";
      }
      if (schdIntList.getCheckFilterFlag().equals("true")) {
        if (!schdIntList.getDuprequisitionId().equals("")) {
          query = query + "AND  HRMS_REC_REQS_HDR.REQS_CODE =" + schdIntList.getDuprequisitionId();
          concatStr = concatStr + msg + " :" + schdIntList.getDupreqName() + ",";
        }

        if (!schdIntList.getCandCode1().equals("")) {
          query = query + "AND   RESUME_CAND_CODE=" + schdIntList.getCandCode1();
          concatStr = concatStr + msg1 + " :" + schdIntList.getCandidateName1() + ",";
        }

        if (!schdIntList.getIntRound().equals("")) {
          query = query + "AND INT_ROUND_TYPE='" + schdIntList.getIntRound() + "'";
          concatStr = concatStr + msg2 + " :" + schdIntList.getIntRound() + ",";
        }
        if ((!schdIntList.getIntervDate().trim().equals("")) && (!schdIntList.getIntervDate().trim().equals("null")))
        {
          query = query + "AND INT_DATE =TO_DATE('" + schdIntList.getIntervDate() + "'" + ",'DD-MM-YYYY')";
          concatStr = concatStr + msg3 + " :" + schdIntList.getIntervDate() + ",";
        }

        if (!schdIntList.getIntervId().equals("")) {
          query = query + " AND HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID=" + schdIntList.getIntervId();
          concatStr = concatStr + msg4 + " :" + schdIntList.getIntervName() + ",";
        }
      }
      else {
        schdIntList.setCheckFilterFlag("false");
      }
      query = query + " ORDER BY INT_DATE DESC ";
      schdIntData = getSqlModel().getSingleResult(query);
      if ((schdIntData != null) && (schdIntData.length > 0))
      {
        schdIntList.setModeLength("true");
      }
      else {
        schdIntList.setModeLength("false");
      }

    }
    catch (Exception e)
    {
      logger.error("exception in schdIntData query", e);
    }

    String[] pageIndex = Utility.doPaging(schdIntList.getMyPage(), schdIntData.length, 20);
    if (pageIndex == null) {
      pageIndex[0] = "0";
      pageIndex[1] = "20";
      pageIndex[2] = "1";
      pageIndex[3] = "1";
      pageIndex[4] = "";
    }
    logger.info("my page in action" + schdIntList.getMyPage());
    request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
    request.setAttribute("PageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
    if (pageIndex[4].equals("1")) {
      schdIntList.setMyPage("1");
    }
    if (schdIntData == null) {
      schdIntList.setNoData("true");
      schdIntList.setList(null);
      schdIntList.setTotalRecords(String.valueOf(schdIntData.length));
    }
    else if (schdIntData.length == 0) {
      schdIntList.setNoData("true");
      schdIntList.setList(null);
      schdIntList.setTotalRecords(String.valueOf("0"));
    }
    else
    {
      try
      {
        for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++)
        {
          SchdInterviewList bean = new SchdInterviewList();

          bean.setRequisitionCode(String.valueOf(schdIntData[i][0]));
          bean.setRequisitionName(String.valueOf(schdIntData[i][1]));
          bean.setCandidateCode(String.valueOf(schdIntData[i][2]));
          bean.setCandidateName(String.valueOf(schdIntData[i][3]));

          if ((String.valueOf(schdIntData[i][4]).equals("")) || (String.valueOf(schdIntData[i][4]).equals("null")) || 
            (String.valueOf(schdIntData[i][4]).equals(null))) {
            bean.setIntRoundType("");
          }
          else {
            bean.setIntRoundType(String.valueOf(schdIntData[i][4]));
          }

          if ((String.valueOf(schdIntData[i][5]).equals("")) || (String.valueOf(schdIntData[i][5]).equals("null")) || 
            (String.valueOf(schdIntData[i][5]).equals(null))) {
            bean.setIntDate("");
          }
          else {
            bean.setIntDate(String.valueOf(schdIntData[i][5]));
          }
          if ((String.valueOf(schdIntData[i][6]).equals("")) || (String.valueOf(schdIntData[i][6]).equals("null")) || 
            (String.valueOf(schdIntData[i][6]).equals(null))) {
            bean.setIntTime("");
          }
          else {
            bean.setIntTime(String.valueOf(schdIntData[i][6]));
          }
          if ((String.valueOf(schdIntData[i][7]).equals("")) || (String.valueOf(schdIntData[i][7]).equals("null")) || 
            (String.valueOf(schdIntData[i][7]).equals(null))) {
            bean.setVenue("");
          }
          else {
            bean.setVenue(String.valueOf(schdIntData[i][7]));
          }

          bean.setInterviewerCode(String.valueOf(schdIntData[i][8]));
          bean.setInterviewer(String.valueOf(schdIntData[i][9]));

          if ((String.valueOf(schdIntData[i][10]).equals("")) || (String.valueOf(schdIntData[i][10]).equals("null")) || 
            (String.valueOf(schdIntData[i][10]).equals(null))) {
            bean.setComments("");
          }
          else {
            bean.setComments(String.valueOf(schdIntData[i][10]));
          }
          bean.setInterviewCode(String.valueOf(schdIntData[i][11]));
          bean.setInterviewDtlCode(String.valueOf(schdIntData[i][12]));
          bean.setIntRoundTypeName(String.valueOf(schdIntData[i][13]));
          tableList.add(bean);
          schdIntList.setTotalRecords(String.valueOf(schdIntData.length));
        }
      }
      catch (Exception e) {
        logger.error("exception in schdIntData for loop", e);
      }
      schdIntList.setList(tableList);
      schdIntList.setNoData("false");
    }

    try
    {
      String[] dispArr = concatStr.split(",");
      request.setAttribute("dispArr", dispArr);
    }
    catch (Exception localException1)
    {
    }
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void getintStatusList(SchdInterviewList schdIntList, String intConductStatus, String req, String cand, String stat1, String offer, String round, HttpServletRequest request)
  {
    int count = 0;
    String concatStr = "";

    Object[][] intStatusData = (Object[][])null;
    ArrayList tableList = new ArrayList();
    try
    {
      String intStatQuery = "SELECT STATUS_REQS_CODE,REQS_NAME,STATUS_CAND_CODE,CAND_FIRST_NAME||' '||CAND_LAST_NAME,  DECODE(EVAL_INT_STATUS,'S','SELECTED','R','REJECTED','O','ONHOLD'),DECODE(EVAL_MAKE_OFFER,'N','No','Y','Yes'),  DECODE(EVAL_FWD_NEXTROU,'N','No','Y','Yes'),EVAL_INT_EMPID,EVAL.EMP_FNAME||' '||EVAL.EMP_LNAME,EVAL_COMMENTS,  INT_VIEWER_EMPID,INTVIEWER.EMP_FNAME||' '||INTVIEWER.EMP_LNAME,STATUS_INTDTLCODE,STATUS_INTCODE  FROM HRMS_REC_INTSTATUS  INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_INTSTATUS.STATUS_REQS_CODE)  INNER JOIN HRMS_REC_CANDEVAL ON HRMS_REC_CANDEVAL.EVAL_CODE = HRMS_REC_INTSTATUS.STATUS_EVACODE   INNER JOIN HRMS_REC_SCHINT_DTL ON HRMS_REC_SCHINT_DTL.INT_DTL_CODE = HRMS_REC_CANDEVAL.EVAL_INT_DTL_CODE  LEFT JOIN HRMS_EMP_OFFC EVAL ON (EVAL.EMP_ID = HRMS_REC_CANDEVAL.EVAL_INT_EMPID)  LEFT JOIN HRMS_EMP_OFFC INTVIEWER ON (INTVIEWER.EMP_ID = HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID)  LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_INTSTATUS.STATUS_CAND_CODE)    LEFT JOIN HRMS_REC_SCHINT ON (HRMS_REC_SCHINT.INT_CODE= HRMS_REC_INTSTATUS.STATUS_INTCODE)   WHERE STATUS_STAGE IN('M','F','R','O')  AND INT_REC_EMPID = " + 
        schdIntList.getUserEmpId() + 
        " AND STATUS_CAND_CODE NOT IN (SELECT OFFER_CAND_CODE FROM HRMS_REC_OFFER WHERE OFFER_STATUS !='D')";

      if (schdIntList.getCheckFilterFlag().equals("true"))
      {
        if (!schdIntList.getReqNameId().equals("")) {
          intStatQuery = intStatQuery + " AND  STATUS_REQS_CODE =" + schdIntList.getReqNameId();
          concatStr = concatStr + req + " :" + schdIntList.getNameOfReq() + ",";
        }

        if (!schdIntList.getCodeOfCandidate().equals("")) {
          intStatQuery = intStatQuery + " AND  STATUS_CAND_CODE=" + schdIntList.getCodeOfCandidate();
          concatStr = concatStr + cand + " :" + schdIntList.getNameOfcandidate() + ",";
        }

        if (!schdIntList.getStats().equals("")) {
          intStatQuery = intStatQuery + " AND EVAL_INT_STATUS='" + schdIntList.getStats() + "'";
          if (schdIntList.getStats().equals("S")) {
            concatStr = concatStr + stat1 + " : SELECTED,";
          }
          else if (schdIntList.getStats().equals("R")) {
            concatStr = concatStr + stat1 + " : REJECTED,";
          }
          else if (schdIntList.getStats().equals("O")) {
            concatStr = concatStr + stat1 + " : ONHOLD,";
          }
        }
        if (!schdIntList.getMakeOfferLetter().equals("")) {
          intStatQuery = intStatQuery + " AND EVAL_MAKE_OFFER='" + schdIntList.getMakeOfferLetter() + "'";

          if (schdIntList.getMakeOfferLetter().equals("N")) {
            concatStr = concatStr + offer + " : No,";
          }
          else if (schdIntList.getMakeOfferLetter().equals("Y")) {
            concatStr = concatStr + offer + " : Yes,";
          }
        }

        if (!schdIntList.getIntnextRound().equals("")) {
          intStatQuery = intStatQuery + " AND EVAL_FWD_NEXTROU='" + schdIntList.getIntnextRound() + "'";
          if (schdIntList.getIntnextRound().equals("N")) {
            concatStr = concatStr + round + " : No,";
          }
          else if (schdIntList.getIntnextRound().equals("Y")) {
            concatStr = concatStr + round + " : Yes,";
          }
        }
      }
      else
      {
        schdIntList.setCheckFilterFlag("false");
      }

      intStatusData = getSqlModel().getSingleResult(intStatQuery);

      if ((intStatusData != null) && (intStatusData.length > 0))
      {
        schdIntList.setModeLength("true");
      }
      else {
        schdIntList.setModeLength("false");
      }
    }
    catch (Exception e)
    {
      logger.error("exception in intStatQuery  ", e);
    }
    String[] pageIndex = Utility.doPaging(schdIntList.getMyPage(), intStatusData.length, 20);
    if (pageIndex == null) {
      pageIndex[0] = "0";
      pageIndex[1] = "20";
      pageIndex[2] = "1";
      pageIndex[3] = "1";
      pageIndex[4] = "";
    }

    request.setAttribute("totalPage", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[2]))));
    request.setAttribute("PageNo", Integer.valueOf(Integer.parseInt(String.valueOf(pageIndex[3]))));
    if (pageIndex[4].equals("1")) {
      schdIntList.setMyPage("1");
    }
    if (intStatusData == null) {
      schdIntList.setNoData("true");
      schdIntList.setList(null);
      schdIntList.setTotalRecords(String.valueOf(intStatusData.length));
    }
    else if (intStatusData.length == 0) {
      schdIntList.setNoData("true");
      schdIntList.setList(null);
      schdIntList.setTotalRecords(String.valueOf(intStatusData.length));
    }
    else
    {
      try {
        for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++) {
          Object[][] offerData = (Object[][])null;
          String offerCode = "";
          try {
            String query = "SELECT OFFER_CODE FROM HRMS_REC_OFFER WHERE OFFER_REQS_CODE=" + 
              intStatusData[i][0] + " " + " AND OFFER_CAND_CODE=" + intStatusData[i][2];

            offerData = getSqlModel().getSingleResult(query);
          } catch (Exception e) {
            logger.error("exception in offer data", e);
          }
          if (offerData != null)
          {
            if (offerData.length != 0)
            {
              offerCode = String.valueOf(offerData[0][0]);
            }
          }
          SchdInterviewList bean = new SchdInterviewList();

          bean.setOfferCode(offerCode);
          bean.setReqCodeOffer(String.valueOf(intStatusData[i][0]));

          bean.setRadioOffer("");
          bean.setButtonName("int");

          bean.setRequisitionCode(String.valueOf(intStatusData[i][0]));
          bean.setRequisitionName(String.valueOf(intStatusData[i][1]));
          bean.setCandidateCode(String.valueOf(intStatusData[i][2]));
          bean.setCandidateName(String.valueOf(intStatusData[i][3]));
          if ((String.valueOf(intStatusData[i][4]).equals("")) || (String.valueOf(intStatusData[i][4]).equals("null")))
            bean.setIntStatus("");
          else {
            bean.setIntStatus(String.valueOf(intStatusData[i][4]));
          }
          bean.setMakeOffer(String.valueOf(intStatusData[i][5]));
          bean.setNextRound(String.valueOf(intStatusData[i][6]));
          if ((String.valueOf(intStatusData[i][7]).equals("")) || (String.valueOf(intStatusData[i][7]).equals("null")) || 
            (String.valueOf(intStatusData[i][7]).equals(null))) {
            bean.setInterviewerCode("");
          }
          else {
            bean.setInterviewerCode(String.valueOf(intStatusData[i][7]));
          }
          if ((String.valueOf(intStatusData[i][8]).equals("")) || (String.valueOf(intStatusData[i][8]).equals("null")) || 
            (String.valueOf(intStatusData[i][7]).equals(null))) {
            bean.setInterviewer("");
          }
          else {
            bean.setInterviewer(String.valueOf(intStatusData[i][8]));
          }

          bean.setComments(checkNull(String.valueOf(intStatusData[i][9])));
          bean.setInterviewDtlCode(String.valueOf(intStatusData[i][12]));
          bean.setInterviewCode(String.valueOf(intStatusData[i][13]));
          tableList.add(bean);
          schdIntList.setTotalRecords(String.valueOf(intStatusData.length));
        }
      }
      catch (Exception e) {
        logger.error("exception in schdIntData for loop", e);
      }
      schdIntList.setList(tableList);

      schdIntList.setRequisitionId("");
      schdIntList.setCandidateName1("");
      schdIntList.setIntRound("");
      schdIntList.setIntervDate("");
      schdIntList.setIntervName("");
      schdIntList.setNoData("false");
      schdIntList.setTotalRecords(String.valueOf(intStatusData.length));
    }

    try
    {
      String[] dispArr = concatStr.split(",");
      request.setAttribute("dispArr", dispArr);
    }
    catch (Exception localException1)
    {
    }
  }

  public void getRecordForOffer(SchdInterviewList schdIntList, HttpServletRequest request)
  {
    String[] radio = request.getParameterValues("radioButton");
    Object[][] offerData = (Object[][])null;
    String offerCode = "";
    logger.info("radio   ==>:" + radio);

    String[] reqCode = request.getParameterValues("requisitionCode");
    String[] candCode = request.getParameterValues("candidateCode");

    String requisitionCode = reqCode[(Integer.parseInt(radio[0]) - 1)];
    String candidateCode = candCode[(Integer.parseInt(radio[0]) - 1)];
    try
    {
      String query = "SELECT OFFER_CODE FROM HRMS_REC_OFFER WHERE OFFER_REQS_CODE=" + 
        requisitionCode + 
        " " + 
        " AND OFFER_CAND_CODE=" + 
        candidateCode;
      offerData = getSqlModel().getSingleResult(query);
    } catch (Exception e) {
      logger.error("exception in offer data", e);
    }
    if (offerData != null)
    {
      if (offerData.length != 0)
      {
        offerCode = String.valueOf(offerData[0][0]);
      }
    }
    schdIntList.setOfferCode(offerCode);
    schdIntList.setReqCodeOffer(requisitionCode);
    schdIntList.setStatus("Y");
    schdIntList.setRadioOffer("Y");
    schdIntList.setButtonName("int");
  }

  public boolean callCancelReq(SchdInterviewList bean)
  {
    String sql = " UPDATE HRMS_REC_SCHINT_DTL SET INT_CONDUCT_STATUS ='C' WHERE INT_DTL_CODE= " + bean.getCancelDtlCode();
    return getSqlModel().singleExecute(sql);
  }
}