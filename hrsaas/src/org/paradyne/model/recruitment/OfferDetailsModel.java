package org.paradyne.model.recruitment;

import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.lsmp.djep.xjep.XJep;
import org.nfunk.jep.Node;
import org.paradyne.bean.Recruitment.OfferDetails;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;

public class OfferDetailsModel extends ModelBase
{
  static Logger logger = Logger.getLogger(OfferDetailsModel.class);

  public void getOfferDetails(OfferDetails bean, String status)
  {
    try
    {
      Object[][] reqsDetails = getSqlModel().getSingleResult(getQuery(1), 
        new Object[] { bean.getRequisitionCode() });
      Object[][] candDetails = getSqlModel().getSingleResult(getQuery(2), 
        new Object[] { bean.getOfferCode() });

      if ((candDetails != null) && (candDetails.length != 0)) {
        bean.setCandidateCode(
          checkNull(String.valueOf(candDetails[0][0])).trim());
        bean.setCandidateName(
          checkNull(String.valueOf(candDetails[0][1])).trim());
        bean
          .setJoiningDate(checkNull(
          String.valueOf(candDetails[0][2])));

        if (status.equals("Y"))
          bean.setOfferDate(
            checkNull(String.valueOf(candDetails[0][3])));
        else if (status.equals("N")) {
          bean.setOfferDate(
            checkNull(String.valueOf(candDetails[0][4])));
        }
        bean
          .setCurrentCtc(checkNull(
          String.valueOf(candDetails[0][5])));
        bean.setNegotiatedCtc(
          checkNull(String.valueOf(candDetails[0][6])));

        if (bean.isViewOfferFlag())
          bean.setOfferStatus(
            checkNull(String.valueOf(candDetails[0][30])).trim());
        else {
          bean.setOfferStatus(
            checkNull(String.valueOf(candDetails[0][7])).trim());
        }

        bean.setJobDescription(
          checkNull(String.valueOf(candDetails[0][8])));
        bean.setRolesResponsibility(
          checkNull(String.valueOf(candDetails[0][9])));

        bean.setEmpTypeCode(
          checkNull(String.valueOf(candDetails[0][10])));
        bean.setEmpType(checkNull(String.valueOf(candDetails[0][11])));

        bean.setHiringMgrCode(
          checkNull(String.valueOf(candDetails[0][12])));
        bean.setHiringMgr(
          checkNull(String.valueOf(candDetails[0][13])).trim());
        bean.setExpJoiningDate(
          checkNull(String.valueOf(candDetails[0][14])));
        bean.setReportingToCode(
          checkNull(String.valueOf(candDetails[0][15])));
        bean.setReportingTo(
          checkNull(String.valueOf(candDetails[0][16])).trim());
        bean.setTestRequirements(
          checkNull(String.valueOf(candDetails[0][17])));
        bean
          .setGradeCode(checkNull(
          String.valueOf(candDetails[0][18])));
        bean.setGrade(checkNull(String.valueOf(candDetails[0][19])));
        bean.setTemplateCode(
          checkNull(String.valueOf(candDetails[0][20])));
        if (bean.isViewOfferFlag()) {
          if (String.valueOf(candDetails[0][21]).equals("true"))
            bean.setProbation("Yes");
          else
            bean.setProbation("No");
        }
        else {
          bean.setProbation(
            checkNull(String.valueOf(candDetails[0][21])));
        }

        bean.setMonths(checkNull(String.valueOf(candDetails[0][22])));
        bean.setAuthorityCode(
          checkNull(String.valueOf(candDetails[0][23])).trim());
        bean.setSigningAuthority(
          checkNull(String.valueOf(candDetails[0][24])).trim());
        bean.setDesignation(
          checkNull(String.valueOf(candDetails[0][25])));
        bean.setRemarks(checkNull(String.valueOf(candDetails[0][26])));
        bean.setCandConstraints(
          checkNull(String.valueOf(candDetails[0][27])));
        if (bean.isViewOfferFlag()) {
          if (String.valueOf(candDetails[0][28]).equals("true"))
            bean.setBackgroundCheck("Yes");
          else
            bean.setBackgroundCheck("No");
        }
        else {
          bean.setBackgroundCheck(
            checkNull(String.valueOf(candDetails[0][28])));
        }

        bean
          .setOfferedCtc(checkNull(
          String.valueOf(candDetails[0][29])));
        bean.setTemplate(checkNull(String.valueOf(candDetails[0][31])));
        bean.setDivisionCode(
          checkNull(String.valueOf(candDetails[0][32])));
        bean
          .setBranchCode(checkNull(
          String.valueOf(candDetails[0][33])));
        bean.setDeptCode(checkNull(String.valueOf(candDetails[0][34])));

        bean.setPositionCode(
          checkNull(String.valueOf(candDetails[0][35])));
        bean.setPosition(checkNull(String.valueOf(candDetails[0][36])));
        bean.setDivision(checkNull(String.valueOf(candDetails[0][37])));
        bean.setBranch(checkNull(String.valueOf(candDetails[0][38])));
        bean
          .setDepartment(checkNull(
          String.valueOf(candDetails[0][39])));
        bean.setReportingToAdminCode(
          checkNull(String.valueOf(candDetails[0][40])));

        bean.setReportingToAdmin(
          checkNull(String.valueOf(candDetails[0][41])));

        bean.setAnnextureFileName(
          checkNull(String.valueOf(candDetails[0][42])));

        bean.setCandidateEmailID(checkNull(String.valueOf(candDetails[0][43])));

        if (checkNull(String.valueOf(candDetails[0][44])).trim().equals(""))
          bean.setShowCandidateCommentsFlag(false);
        else {
          bean.setShowCandidateCommentsFlag(true);
        }

        bean.setCandidateComments(checkNull(String.valueOf(candDetails[0][44])));
        bean.setOfferLetterRegCode(checkNull(String.valueOf(candDetails[0][45])));
        System.out.println("bean.getOfferCode() >>>>>>>>>>>>" + bean.getOfferCode());

        Object[][] recruiterData = (Object[][])null;
        String recQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,RESUME_REC_EMPID  FROM HRMS_REC_RESUME_BANK  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_RESUME_BANK.RESUME_REC_EMPID)  WHERE RESUME_REQS_CODE = " + 
          bean.getRequisitionCode() + 
          " AND RESUME_CAND_CODE = " + 
          bean.getCandidateCode();

        recruiterData = getSqlModel().getSingleResult(recQuery);

        if ((recruiterData != null) && (recruiterData.length > 0)) {
          bean.setRecruiterToken(String.valueOf(recruiterData[0][0]));
          bean.setRecruiterName(String.valueOf(recruiterData[0][1]));
          bean.setRecruiterId(String.valueOf(recruiterData[0][2]));
        }

      }

      if ((reqsDetails != null) && (reqsDetails.length != 0)) {
        bean.setRequisitionName(checkNull(String.valueOf(reqsDetails[0][0])).trim());
        bean.setPosition(checkNull(String.valueOf(reqsDetails[0][1])));
        bean.setDivision(checkNull(String.valueOf(reqsDetails[0][2])));
        bean.setBranch(checkNull(String.valueOf(reqsDetails[0][3])));
        bean.setDepartment(checkNull(String.valueOf(reqsDetails[0][4])));
        bean.setPositionCode(checkNull(String.valueOf(reqsDetails[0][5])));
        bean.setDivisionCode(checkNull(String.valueOf(reqsDetails[0][6])));
        bean.setBranchCode(checkNull(String.valueOf(reqsDetails[0][7])));
        bean.setDeptCode(checkNull(String.valueOf(reqsDetails[0][8])));
        bean.setReqStatus(checkNull(String.valueOf(reqsDetails[0][9])));
        
      }
    }
    catch (Exception e) {
      logger.error("exception in getOfferDetails method", e);
    }
  }

  public void joiningChecklist(OfferDetails bean, HttpServletRequest request)
  {
    try
    {
      ArrayList checkList = new ArrayList();
      String query = "SELECT CHECK_CODE,CHECK_NAME FROM HRMS_CHECKLIST WHERE CHECK_TYPE = 'J' AND CHECK_STATUS='A' ORDER BY UPPER(CHECK_NAME)";
      Object[][] checkListData = getSqlModel().getSingleResult(query);
      if ((checkListData != null) && (checkListData.length != 0)) {
        for (int i = 0; i < checkListData.length; i++) {
          OfferDetails bean1 = new OfferDetails();
          bean1.setChecklistCode(String.valueOf(checkListData[i][0]));

          bean1.setChecklistName(String.valueOf(checkListData[i][1]));

          checkList.add(bean1);
        }
        bean.setTestDataList(checkList);
        String str = request.getParameter("id");
        String str1 = request.getParameter("id1");
        bean.setTestRequirements(str);
        bean.setTestReqCode(str1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void saveKeepInformedList(String[] empCode, String offerCode) {
    try {
      String empId = "";
      if ((empCode != null) && (empCode.length > 0)) {
        for (int i = 0; i < empCode.length; i++) {
          if (i < empCode.length - 1)
            empId = empId + empCode[i] + ",";
          else {
            empId = empId + empCode[i];
          }
        }
      }
      String updateQuery = "  UPDATE HRMS_REC_OFFER SET OFFER_KEEP_INFORMED=?  WHERE OFFER_CODE=?  ";
      Object[][] updateObj = new Object[1][2];
      updateObj[0][0] = empId;
      updateObj[0][1] = offerCode;

      getSqlModel().singleExecute(updateQuery, updateObj);
    } catch (Exception e) {
      logger.error("Exception in saveKeepInformedList----------" + e);
    }
  }

  public String save(OfferDetails bean, String[] serialNo, String[] empCode, String[] empName, String offerStatus, String hiddenRequisitionCode)
  {
    String result = "";
    try
    {
      if (!bean.getOfferCode().equals("")) {
        if (bean.getOfferStatus().equals("OA")||bean.getOfferStatus().equals("D") || bean.getOfferStatus().equals("OR") || bean.getOfferStatus().equals("S")|| bean.getOfferStatus().equals("I") ||  bean.getOfferStatus().equals("C")) {
          result = updateOfferDetails(bean, offerStatus, hiddenRequisitionCode);
        } /*else {
          String checkVacancyStatsDataQuery = "SELECT VAC_OFFER_CODE, VAC_OFFER_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = " + hiddenRequisitionCode + " AND VAC_OFFER_CODE = " + bean.getOfferCode();
          Object[][] checkVacancyStatsDataObj = getSqlModel().getSingleResult(checkVacancyStatsDataQuery);
          if ((checkVacancyStatsDataObj != null) && (checkVacancyStatsDataObj.length > 0)) {
            result = updateOfferDetails(bean, offerStatus, hiddenRequisitionCode);
          } else {
            String vacancyDataQuery = "SELECT VAC_OFFER_CODE, VAC_OFFER_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = " + hiddenRequisitionCode + " AND VAC_STATUS = 'O' AND VAC_OFFER_GIVEN='N' AND VAC_OFFER_CODE IS NULL AND VAC_APPOINT_GIVEN='N' AND VAC_APPOINT_CODE IS NULL ";
            Object[][] vacancyDataObj = getSqlModel().getSingleResult(vacancyDataQuery);
            if ((vacancyDataObj != null) && (vacancyDataObj.length > 0))
              result = updateOfferDetails(bean, offerStatus, hiddenRequisitionCode);
            else
              result = "alreadyProcessed";
          }
        }*/
      }
      else {
        String Searchcandidate = "SELECT OFFER_CODE FROM HRMS_REC_OFFER WHERE OFFER_REQS_CODE=" + 
          bean.getRequisitionCode() + 
          " AND OFFER_CAND_CODE=" + 
          bean.getCandidateCode();
        Object[][] noofRecords = getSqlModel().getSingleResult(
          Searchcandidate);
        if ((noofRecords != null) && (noofRecords.length > 0)) {
          if (noofRecords.length == 1)
          {
            String offercode = String.valueOf(noofRecords[0][0]);
            if (offercode != null) {
              result = updateExistingRecord(bean, offercode, 
                empCode);
            }

          }
          else
          {
            String delQuery = "DELETE FROM HRMS_REC_OFFER WHERE OFFER_REQS_CODE=" + 
              bean.getRequisitionCode() + 
              " AND OFFER_CAND_CODE=" + 
              bean.getCandidateCode();
            boolean result1 = getSqlModel().singleExecute(delQuery);
            if (result1)
            {
              result = insertOfferDetails(bean, empCode, offerStatus, hiddenRequisitionCode);
            }
          }
        }
        else {
          result = insertOfferDetails(bean, empCode, offerStatus, hiddenRequisitionCode);
        }

      }

      if (bean.getOfferStatus().equals("D"))
        bean.setOfferStatus("Due");
      else if (bean.getOfferStatus().equals("I")) 
        bean.setOfferStatus("Issued");
      else if (bean.getOfferStatus().equals("S"))
        bean.setOfferStatus("Send For Approval");
      else if (bean.getOfferStatus().equals("OA"))
        bean.setOfferStatus("Accepted");
      else if (bean.getOfferStatus().equals("S"))
        bean.setOfferStatus("Rejected");
      else if (bean.getOfferStatus().equals("C")) {
        bean.setOfferStatus("Canceled");
      }

      if (bean.getProbation().equals("true"))
        bean.setProbation("Yes");
      else {
        bean.setProbation("No");
      }
      if (bean.getBackgroundCheck().equals("true"))
        bean.setBackgroundCheck("Yes");
      else {
        bean.setBackgroundCheck("No");
      }
      getKeepInformedSavedRecord(bean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private void updateVacancyStatistics(String requisitionCode, String status, String offerCode)
  {
    try {
      if (status.trim().equals("OfferAccepted")) {
        String updateVacancyOfferstatusQuery = " UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'Y', HRMS_REC_VACANCIES_STATS.VAC_OFFER_STATUS_DATE = SYSDATE,  HRMS_REC_VACANCIES_STATS.VAC_OFFER_DATE = SYSDATE, HRMS_REC_VACANCIES_STATS.VAC_OFFER_CODE = " + 
          offerCode + 
          " WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = " + requisitionCode + " AND ROWNUM=1 AND HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'N'";
        getSqlModel().singleExecute(updateVacancyOfferstatusQuery);
      } else if (status.trim().equals("OfferRejectCancel")) {
        Object[][] checkData = getSqlModel().getSingleResult(" SELECT VAC_CODE, VAC_REQS_CODE, VAC_OFFER_GIVEN, VAC_OFFER_CODE FROM HRMS_REC_VACANCIES_STATS  WHERE VAC_REQS_CODE = " + 
          requisitionCode + " AND ROWNUM=1 AND VAC_OFFER_GIVEN = 'N' AND VAC_OFFER_CODE IS NOT NULL");

        String updateVacancyOfferstatusQuery = " UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'N', HRMS_REC_VACANCIES_STATS.VAC_OFFER_STATUS_DATE = SYSDATE,  HRMS_REC_VACANCIES_STATS.VAC_OFFER_DATE = NULL, HRMS_REC_VACANCIES_STATS.VAC_OFFER_CODE = NULL  WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = " + 
          requisitionCode + " AND ROWNUM=1 ";
        if (checkData == null)
          updateVacancyOfferstatusQuery = updateVacancyOfferstatusQuery + " AND HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'Y'";
        else {
          updateVacancyOfferstatusQuery = updateVacancyOfferstatusQuery + " AND HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN = 'N'";
        }

        getSqlModel().singleExecute(updateVacancyOfferstatusQuery);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String updateExistingRecord(OfferDetails bean, String offercode, String[] empCode)
  {
    String flag = "";
    try {
      Object[][] offerDetails = new Object[1][26];

      offerDetails[0][0] = bean.getOfferStatus();
      offerDetails[0][1] = bean.getJobDescription();
      offerDetails[0][2] = bean.getRolesResponsibility();
      offerDetails[0][3] = bean.getEmpTypeCode();
      offerDetails[0][4] = bean.getHiringMgrCode();
      offerDetails[0][5] = bean.getExpJoiningDate();
      offerDetails[0][6] = bean.getReportingToCode();
      offerDetails[0][7] = bean.getTestRequirements();
      offerDetails[0][8] = bean.getGradeCode();
      offerDetails[0][9] = bean.getTemplateCode();

      if (bean.getProbation().equals("true"))
        offerDetails[0][10] = "Y";
      else {
        offerDetails[0][10] = "N";
      }

      offerDetails[0][11] = bean.getMonths();
      offerDetails[0][12] = bean.getAuthorityCode();
      offerDetails[0][13] = bean.getRemarks();
      offerDetails[0][14] = bean.getCandConstraints();

      if (bean.getOfferStatus().equals("S")) {
        offerDetails[0][15] = "P";
      } else {
        String appQuery = "SELECT NVL(OFFER_APPR_STATUS, ' ') FROM HRMS_REC_OFFER WHERE OFFER_CODE=" + 
          String.valueOf(offercode);

        Object[][] appStatus = getSqlModel().getSingleResult(appQuery);
        if (appStatus != null) {
          if ((!appStatus[0][0].equals("null")) && 
            (appStatus.length > 0)) {
            offerDetails[0][15] = String.valueOf(appStatus[0][0]);
          }
          else
            offerDetails[0][15] = "";
        }
        else offerDetails[0][15] = "";
      }
      if (bean.getBackgroundCheck().equals("true"))
        offerDetails[0][16] = "Y";
      else {
        offerDetails[0][16] = "N";
      }

      offerDetails[0][17] = bean.getJoiningDate();
      offerDetails[0][18] = bean.getCurrentCtc();
      offerDetails[0][19] = bean.getNegotiatedCtc();
      offerDetails[0][20] = bean.getOfferedCtc();
      offerDetails[0][21] = bean.getOfferDate();

      offerDetails[0][22] = bean.getDivisionCode();
      offerDetails[0][23] = bean.getBranchCode();
      offerDetails[0][24] = bean.getDeptCode();
      offerDetails[0][25] = bean.getPositionCode();
     // offerDetails[0][26] = String.valueOf(offercode);

      String query = getQuery(4);

      if ((bean.getOfferStatus().equals("OA")) || 
        (bean.getOfferStatus().equals("S")) || 
        (bean.getOfferStatus().equals("C"))) {
        query = query + ", OFFER_ACCEPT_DATE = TO_DATE(TO_CHAR(SYSDATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') ";
      }
      query = query + " WHERE OFFER_CODE =" + String.valueOf(offercode);

      boolean result = getSqlModel().singleExecute(query, offerDetails);
      bean.setOfferCode(offercode);
      if (result) {
        flag = "saved";
        saveKeepInformedList(empCode, bean.getOfferCode());
      } else {
        flag = "notSaved";
      }
    } catch (Exception e) {
      logger.error("exception in updateExistingRecord method", e);
    }

    return flag;
  }

  /**
	 * @Method insertOfferDetails
	 * @Purpose to insert the offer details in HRMS_REC_OFFER
	 * @param bean
	 * 
	 * insert
	 * @param hiddenRequisitionCode 
	 * @param offerStatus 
	 * @param request 
	 */
	//public String insertOfferDetails(OfferDetails bean, String[] empCode, String offerStatus, String hiddenPublishCode, String hiddenRequisitionCode) {
	public String insertOfferDetails(OfferDetails bean, String[] empCode, String offerStatus, String hiddenRequisitionCode) {
		String flag = "";
		try {
			Object[][] offerDetails = new Object[1][32];
			offerDetails[0][0] = bean.getRequisitionCode().trim(); // requisition
			// code
			offerDetails[0][1] = bean.getCandidateCode().trim(); // candidate
			// code
			offerDetails[0][2] = bean.getOfferStatus().trim(); // offer status
			offerDetails[0][3] = bean.getJobDescription().trim(); // job
			// description
			// code
			offerDetails[0][4] = bean.getRolesResponsibility().trim(); // roles
			// n
			// responsibility
			offerDetails[0][5] = bean.getEmpTypeCode().trim(); // employee type
			// code
			offerDetails[0][6] = bean.getHiringMgrCode().trim(); // hiring
			// manager
			// code
			offerDetails[0][7] = bean.getExpJoiningDate().trim(); // expected
			// joining
			// date
			offerDetails[0][8] = bean.getReportingToCode().trim(); // reporting
			// to code
			offerDetails[0][9] = bean.getTestRequirements().trim(); // joining
			// formality
			offerDetails[0][10] = bean.getGradeCode().trim(); // grade code
			offerDetails[0][11] = bean.getTemplateCode().trim(); // template code

			if (bean.getProbation().equals("true")) {
				offerDetails[0][12] = "Y";
			} else {
				offerDetails[0][12] = "N";
			}
			// offerDetails [0][12] = bean.getProbation(); //probation check box
			offerDetails[0][13] = bean.getMonths().trim(); // probation months
			offerDetails[0][14] = bean.getAuthorityCode().trim(); // signing authority code
			offerDetails[0][15] = bean.getRemarks().trim(); // remarks
			offerDetails[0][16] = bean.getCandConstraints().trim(); // candidate
			// constraints

			if (bean.getOfferStatus().equals("S")) {
				offerDetails[0][17] = "P"; // approval status
			} else {
				offerDetails[0][17] = ""; // approval status
			}

			if (bean.getBackgroundCheck().equals("true"))
				offerDetails[0][18] = "Y";
			else
				offerDetails[0][18] = "N";

			// offerDetails [0][18] = bean.getBackgroundCheck(); //background check box
			offerDetails[0][19] = bean.getJoiningDate().trim(); // joining date
			offerDetails[0][20] = bean.getCurrentCtc(); // current CTC
			offerDetails[0][21] = bean.getNegotiatedCtc().trim(); // negotiated CTC
			offerDetails[0][22] = bean.getOfferedCtc().trim(); // offered CTC
			offerDetails[0][23] = bean.getOfferDate().trim(); // offer date
			// offerDetails[0][24] = bean.getEmailtemplateCode().trim(); //
			// emailtemplate code
			offerDetails[0][24] = bean.getDivisionCode();
			offerDetails[0][25] = bean.getBranchCode();
			offerDetails[0][26] = bean.getDeptCode();
			offerDetails[0][27] = bean.getPositionCode();
			offerDetails[0][28] = bean.getReportingToAdminCode();
			offerDetails[0][29] = bean.getKeepInformedEmpId();// keep informed id
			offerDetails[0][30] = bean.getAnnextureFileName();// Annexure attached
			
			
			//############################ BEGIN ###############################
			Object[][] chkIfPublishData = null;
			String checkIfPublished = "SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR " + 
									  "WHERE PUB_REQS_CODE = " + bean.getRequisitionCode() + " AND PUB_STATUS = 'P'";
			chkIfPublishData = getSqlModel().getSingleResult(checkIfPublished);
			if (chkIfPublishData != null && chkIfPublishData.length > 0) {

			} else {
				Object[][] reqVacDtlCode = null;
				String vacDtlQuery = "SELECT VACAN_DTL_CODE,VACAN_NUMBERS FROM HRMS_REC_REQS_VACDTL "
									+ " WHERE REQS_CODE = "	+ bean.getRequisitionCode();
				reqVacDtlCode = getSqlModel().getSingleResult(vacDtlQuery);
				if (reqVacDtlCode != null && reqVacDtlCode.length > 0) {
					// BEGINS -- Insert Records into HRMS_REC_VACANCIES_STATS
					Object[][] pubMaxCode = null;
					String pubCode = "SELECT NVL(MAX(PUB_CODE),0)+1 FROM HRMS_REC_VACPUB_HDR";
					pubMaxCode = getSqlModel().getSingleResult(pubCode);
					 
					/*Object[][] vacancyObj = null;
					String insertVacancyDtlQuery = "";
					String maxCodeQuery = "SELECT NVL(MAX(VAC_CODE),0)+1 FROM HRMS_REC_VACANCIES_STATS";
					Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(maxCodeQuery);
					int count=0;
					if(maxCodeQueryObj!=null && maxCodeQueryObj.length>0) {
						count =Integer.parseInt(String.valueOf(maxCodeQueryObj[0][0]));
					}
					vacancyObj =new Object[Integer.parseInt(String.valueOf(reqVacDtlCode[0][1]))][6];
					for (int i = 0; i < vacancyObj.length; i++) {
						vacancyObj[i][0] = count++; 
						vacancyObj[i][1] = bean.getRequisitionCode();
						vacancyObj[i][2] = checkNull(String.valueOf("O"));
						vacancyObj[i][3] = checkNull(String.valueOf("N"));
						vacancyObj[i][4] = checkNull(String.valueOf("N"));
						vacancyObj[i][5] = checkNull(String.valueOf(pubMaxCode[0][0]));
						
						insertVacancyDtlQuery = "INSERT INTO HRMS_REC_VACANCIES_STATS (VAC_CODE, VAC_REQS_CODE, VAC_STATUS, VAC_OFFER_GIVEN, VAC_APPOINT_GIVEN, VAC_PUBLISH_CODE) "
													+ " VALUES(?,?,?,?,?,?)";
					}
					getSqlModel().singleExecute(insertVacancyDtlQuery, vacancyObj);*/
					// ENDS -- Insert Records into HRMS_REC_VACANCIES_STATS
					
					for (int i = 0; i < reqVacDtlCode.length; i++) {
						String insertVacHdr = "INSERT INTO HRMS_REC_VACPUB_HDR (PUB_CODE,PUB_REQS_CODE,PUB_STATUS, PUB_DATE, "
								+ " PUB_VACAN_DTLCODE, PUB_EMPID,PUB_TO_CONS, PUB_TO_REF, PUB_TO_WEB,PUB_REC_TYPE_INT, PUB_REC_TYPE_EXT, PUB_TO_CANDJOB) VALUES("
								+ checkNull(String.valueOf(pubMaxCode[0][0])) + "," 
								+ bean.getRequisitionCode()
								+ ",'P',TO_DATE(SYSDATE,'DD-MM-YYYY'),"
								+ reqVacDtlCode[i][0]
								+ ","
								+ bean.getUserEmpId()
								+ ",'Y','Y','Y','Y','Y','Y')";

						boolean resultHdrInsert = getSqlModel().singleExecute(
								insertVacHdr);

						if (resultHdrInsert) {
							String updReqVacDtlQuey = "UPDATE HRMS_REC_REQS_VACDTL SET VACAN_STATUS='P' WHERE VACAN_DTL_CODE = "
								+ reqVacDtlCode[i][0] + "";
							getSqlModel().singleExecute(updReqVacDtlQuey);
									
							String insertVacDtl = "INSERT INTO HRMS_REC_VACPUB_RECDTL (PUB_DTL_CODE, PUB_CODE, PUB_REC_EMPID, PUB_ASG_VAC, PUB_VAC_STATUS) "
									+ " VALUES((SELECT NVL(MAX(PUB_DTL_CODE),0)+1 FROM HRMS_REC_VACPUB_RECDTL),"
									+ " (SELECT MAX(PUB_CODE) FROM HRMS_REC_VACPUB_HDR),"
									+ bean.getRecruiterId()
									+ ","
									+ reqVacDtlCode[i][1] + ",'O')";
							getSqlModel().singleExecute(insertVacDtl);
						} // end of if
					} // end of loop
				} // end of if
			}
			
			
			/*String vacancyStatsDataQuery = "SELECT VAC_OFFER_CODE, VAC_OFFER_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = "+hiddenRequisitionCode +" AND VAC_STATUS = 'O' AND VAC_OFFER_GIVEN='N' AND VAC_OFFER_CODE IS NULL";
			Object[][] vacancyStatsDataObj = getSqlModel().getSingleResult(vacancyStatsDataQuery);
			if(vacancyStatsDataObj !=null && vacancyStatsDataObj.length>0){
				Object[][] checkResumeData = null;
				String checkResumeBank = "SELECT RESUME_REQS_CODE,RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK "
						+ " WHERE RESUME_REQS_CODE ="
						+ bean.getRequisitionCode()
						+ " AND RESUME_CAND_CODE =" + bean.getCandidateCode() + " ";
				checkResumeData = getSqlModel().getSingleResult(checkResumeBank);
				if (checkResumeData != null && checkResumeData.length > 0) {

				} else {*/
					String insertResumeBank = "INSERT INTO HRMS_REC_RESUME_BANK (RESUME_CODE, RESUME_REQS_CODE, RESUME_CAND_CODE, RESUME_REC_EMPID, "
							+ " RESUME_APPR_EMPID, RESUME_STATUS) VALUES((SELECT NVL(MAX(RESUME_CODE),0)+1 FROM HRMS_REC_RESUME_BANK),"
							+ " "
							+ bean.getRequisitionCode()
							+ ","
							+ bean.getCandidateCode()
							+ ",'"
							+ bean.getRecruiterId()
							+ "', " + " " + bean.getUserEmpId() + ",'D')";
					getSqlModel().singleExecute(insertResumeBank);
			/*	}
			}*/
			//############################ ENDS ###############################
			
		
			if (bean.getOfferStatus().equals("D")) {
				/** BEGINS - IF OFFER STATUS IS -- DUE  THEN ALLOWED TO PUT ANY NUMBER OF NEW OFFER*/	
				final String regNumberQuery = "SELECT MAX(OFFER_CODE)+1 ||'/'|| TO_CHAR(SYSDATE,'dd-mm-yyyy'), MAX(OFFER_CODE)+1, "   
											 +" TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_REC_OFFER ";
				final Object[][] regNumberObj = this.getSqlModel().getSingleResult(regNumberQuery);
				if (regNumberObj != null && regNumberObj.length > 0) {
					try {
						final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
						model.initiate(context, session);
						final String autoIncrementAppointmentCode = model.generateApplicationCode(String.valueOf(regNumberObj[0][1]), "RMS-AppointmentLetter", bean.getUserEmpId(), String.valueOf(regNumberObj[0][2]));
						bean.setOfferLetterRegCode(this.checkNull(autoIncrementAppointmentCode));
						model.terminate();
					} catch (final Exception e) {
						e.printStackTrace();
					}
				}

				offerDetails[0][31] = bean.getOfferLetterRegCode();
				boolean result = getSqlModel().singleExecute(getQuery(3), offerDetails);
				if (result) {
					String query = "SELECT MAX(OFFER_CODE) FROM HRMS_REC_OFFER";
					Object[][] offerCode = getSqlModel().getSingleResult(query);

					if (offerCode != null && offerCode.length != 0) {
						bean.setOfferCode(String.valueOf(offerCode[0][0]));
					}
					saveKeepInformedList(empCode, bean.getOfferCode());
					flag = "saved";
				} else {
					flag = "notSaved";
				}	
				/** ENDS - IF OFFER STATUS IS -- DUE  THEN ALLOWED TO PUT ANY NUMBER OF NEW OFFER*/	
			} else {
				//Check Whether all the requisitions related vacancies are Processed Or not --- START
				/*String checkVacancyStatsDataQuery = "SELECT VAC_OFFER_CODE, VAC_OFFER_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = "+hiddenRequisitionCode +" AND VAC_STATUS = 'O' AND VAC_OFFER_GIVEN='N' AND VAC_OFFER_CODE IS NULL AND VAC_APPOINT_GIVEN='N' AND VAC_APPOINT_CODE IS NULL ";
				Object[][] checkVacancyStatsDataObj = getSqlModel().getSingleResult(checkVacancyStatsDataQuery);*/
				
				//BEGINS - Check for current requisition is Quick Requisition or NOT.
				/*String quickRequisitionQuery = "SELECT REQS_APPROVAL_STATUS FROM HRMS_REC_REQS_HDR WHERE REQS_CODE ="+bean.getRequisitionCode();
				Object[][] quickRequisitionObj = getSqlModel().getSingleResult(quickRequisitionQuery);
				String requisitionType = "";
				if (quickRequisitionObj != null && quickRequisitionObj.length > 0 ) {
					requisitionType = Utility.checkNull(String.valueOf(quickRequisitionObj[0][0]));
				}*/
				//ENDS - Check for current requisition is Quick Requisition or NOT.
				
				/**
				 * Below we are checking whether selected requisition is Quick OR not.
				 * If it is Quick Requisition then no need to check against No. Vacancies.
				 */
				//if( (requisitionType.trim()!="" && requisitionType.trim().equals("Q")) || (vacancyStatsDataObj != null && vacancyStatsDataObj.length>0)) {
				/*if (checkVacancyStatsDataObj != null && checkVacancyStatsDataObj.length>0) {*/
					final String regNumberQuery = "SELECT MAX(OFFER_CODE)+1 ||'/'|| TO_CHAR(SYSDATE,'dd-mm-yyyy'), MAX(OFFER_CODE)+1, "   
						+" TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_REC_OFFER ";
					final Object[][] regNumberObj = this.getSqlModel().getSingleResult(regNumberQuery);
					if (regNumberObj != null && regNumberObj.length > 0) {
						try {
							final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
							model.initiate(context, session);
							final String autoIncrementAppointmentCode = model.generateApplicationCode(String.valueOf(regNumberObj[0][1]), "RMS-AppointmentLetter", bean.getUserEmpId(), String.valueOf(regNumberObj[0][2]));
							bean.setOfferLetterRegCode(this.checkNull(autoIncrementAppointmentCode));
							model.terminate();
						} catch (final Exception e) {
							e.printStackTrace();
						}
					}

					offerDetails[0][31] = bean.getOfferLetterRegCode();
					
					boolean result = getSqlModel().singleExecute(getQuery(3), offerDetails);
					if (result) {
						String query = "SELECT MAX(OFFER_CODE) FROM HRMS_REC_OFFER";
						Object[][] offerCode = getSqlModel().getSingleResult(query);

						if (offerCode != null && offerCode.length != 0) {
							bean.setOfferCode(String.valueOf(offerCode[0][0]));
						}
						saveKeepInformedList(empCode, bean.getOfferCode());
							
						
						if(bean.getOfferStatus().equals("OA")){
							insertAppointmentDetails(bean);
							//updateVacancyStatistics(hiddenRequisitionCode, "OfferAccepted", bean.getOfferCode());
						}else if(bean.getOfferStatus().equals("OR") || bean.getOfferStatus().equals("C")){
							///updateVacancyStatistics(hiddenRequisitionCode, "OfferRejectCancel", bean.getOfferCode());
						} else if(bean.getOfferStatus().equals("I") || bean.getOfferStatus().equals("S")){
							
							/*String updateVacancyOfferstatusQuery = "UPDATE HRMS_REC_VACANCIES_STATS SET VAC_OFFER_DATE = SYSDATE, VAC_OFFER_CODE = "+bean.getOfferCode()+" WHERE VAC_REQS_CODE = "+hiddenRequisitionCode
																  +" AND ROWNUM=1 AND VAC_OFFER_GIVEN = 'N' AND VAC_OFFER_CODE IS NULL";
							getSqlModel().singleExecute(updateVacancyOfferstatusQuery);*/
						}
						flag = "saved";
					} else {
						flag = "notSaved";
					}	
				/*}
				//Check Whether all the requisitions related vacancies are Processed Or not --- END
				else {
					flag = "alreadyProcessed";
				}*/
			}
		} catch (Exception e) {
			logger.error("exception in insertOfferDetails method", e);
		}
		return flag;
	}

  public String updateOfferDetails(OfferDetails bean, String offerStatus, String hiddenRequisitionCode)
  {
    String flag = "";
    try {
      String getOfferDetailsQuery = "SELECT OFFER_CODE,OFFER_STATUS FROM HRMS_REC_OFFER WHERE OFFER_REQS_CODE=" + bean.getRequisitionCode() + " AND OFFER_CAND_CODE=" + bean.getCandidateCode();
      Object[][] getOfferDetailsObj = getSqlModel().getSingleResult(getOfferDetailsQuery);
      if ((getOfferDetailsObj != null) && (getOfferDetailsObj.length > 0)) {
      
            insertAppointmentDetails(bean);
            ///updateVacancyStatistics(hiddenRequisitionCode, "OfferAccepted", String.valueOf(getOfferDetailsObj[0][0]).trim());
         

      }

      Object[][] offerDetails = new Object[1][30];

      offerDetails[0][0] = bean.getOfferStatus();
      offerDetails[0][1] = bean.getJobDescription();
      offerDetails[0][2] = bean.getRolesResponsibility();
      offerDetails[0][3] = bean.getEmpTypeCode();
      offerDetails[0][4] = bean.getHiringMgrCode();
      offerDetails[0][5] = bean.getExpJoiningDate();
      offerDetails[0][6] = bean.getReportingToCode();
      offerDetails[0][7] = bean.getTestRequirements();
      offerDetails[0][8] = bean.getGradeCode();
      offerDetails[0][9] = bean.getTemplateCode();

      if (bean.getProbation().equals("true"))
        offerDetails[0][10] = "Y";
      else {
        offerDetails[0][10] = "N";
      }

      offerDetails[0][11] = bean.getMonths();
      offerDetails[0][12] = bean.getAuthorityCode();
      offerDetails[0][13] = bean.getRemarks();
      offerDetails[0][14] = bean.getCandConstraints();

      if (bean.getOfferStatus().equals("S")) {
        offerDetails[0][15] = "P";
      } else {
        String appQuery = "SELECT nvl(OFFER_APPR_STATUS, ' ') FROM HRMS_REC_OFFER WHERE OFFER_CODE=" + 
          bean.getOfferCode();
        Object[][] appStatus = getSqlModel().getSingleResult(appQuery);
        if (appStatus != null) {
          if ((!appStatus[0][0].equals("null")) && 
            (appStatus.length > 0)) {
            offerDetails[0][15] = String.valueOf(appStatus[0][0]);
          }
          else
            offerDetails[0][15] = "";
        }
        else offerDetails[0][15] = "";
      }
      logger.info("offerDetails [0][15] ====" + offerDetails[0][15]);
      if (bean.getBackgroundCheck().equals("true"))
        offerDetails[0][16] = "Y";
      else {
        offerDetails[0][16] = "N";
      }

      offerDetails[0][17] = bean.getJoiningDate();
      offerDetails[0][18] = bean.getCurrentCtc();
      offerDetails[0][19] = bean.getNegotiatedCtc();
      offerDetails[0][20] = bean.getOfferedCtc();
      offerDetails[0][21] = bean.getOfferDate();

      offerDetails[0][22] = bean.getDivisionCode();
      offerDetails[0][23] = bean.getBranchCode();
      offerDetails[0][24] = bean.getDeptCode();
      offerDetails[0][25] = bean.getPositionCode();
      offerDetails[0][26] = bean.getReportingToAdminCode();
      offerDetails[0][27] = bean.getKeepInformedEmpId();

      offerDetails[0][28] = bean.getAnnextureFileName();

      offerDetails[0][29] = bean.getOfferCode();

      String query = getQuery(4);

      if ((bean.getOfferStatus().equals("OA")) || 
        (bean.getOfferStatus().equals("S")) || 
        (bean.getOfferStatus().equals("C"))) {
        query = query + ", OFFER_ACCEPT_DATE = TO_DATE(TO_CHAR(SYSDATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') ";
      }
      query = query + " ,  OFFER_ADMIN_REPORTING_TO= ?, OFFER_KEEP_INFORMED=?, OFFER_ATTACHED_ANNEXURE=? WHERE OFFER_CODE = ?";

      boolean result = getSqlModel().singleExecute(query, offerDetails);
      if (result)
        flag = "updated";
      else {
        flag = "notUpdated";
      }

      Object[][] chkIfPublishData = (Object[][])null;
      String checkIfPublished = "SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR WHERE PUB_REQS_CODE = " + 
        bean.getRequisitionCode() + " AND PUB_STATUS = 'P'";
      chkIfPublishData = getSqlModel().getSingleResult(checkIfPublished);
      if ((chkIfPublishData == null) || (chkIfPublishData.length <= 0))
      {
        Object[][] reqVacDtlCode = (Object[][])null;
        String vacDtlQuery = "SELECT VACAN_DTL_CODE,VACAN_NUMBERS FROM HRMS_REC_REQS_VACDTL  WHERE REQS_CODE = " + 
          bean.getRequisitionCode();
        reqVacDtlCode = getSqlModel().getSingleResult(vacDtlQuery);
        if ((reqVacDtlCode != null) && (reqVacDtlCode.length > 0))
        {
          Object[][] pubMaxCode = (Object[][])null;
          String pubCode = "SELECT NVL(MAX(PUB_CODE),0)+1 FROM HRMS_REC_VACPUB_HDR";
          pubMaxCode = getSqlModel().getSingleResult(pubCode);

         /* Object[][] vacancyObj = (Object[][])null;
          String insertVacancyDtlQuery = "";
          String maxCodeQuery = "SELECT NVL(MAX(VAC_CODE),0)+1 FROM HRMS_REC_VACANCIES_STATS";
          Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(maxCodeQuery);
          int count = 0;
          if ((maxCodeQueryObj != null) && (maxCodeQueryObj.length > 0)) {
            count = Integer.parseInt(String.valueOf(maxCodeQueryObj[0][0]));
          }
          vacancyObj = new Object[Integer.parseInt(String.valueOf(reqVacDtlCode[0][1]))][6];
          for (int i = 0; i < vacancyObj.length; i++) {
            vacancyObj[i][0] = Integer.valueOf(count++);
            vacancyObj[i][1] = bean.getRequisitionCode();
            vacancyObj[i][2] = checkNull(String.valueOf("O"));
            vacancyObj[i][3] = checkNull(String.valueOf("N"));
            vacancyObj[i][4] = checkNull(String.valueOf("N"));
            vacancyObj[i][5] = checkNull(String.valueOf(pubMaxCode[0][0]));

            insertVacancyDtlQuery = "INSERT INTO HRMS_REC_VACANCIES_STATS (VAC_CODE, VAC_REQS_CODE, VAC_STATUS, VAC_OFFER_GIVEN, VAC_APPOINT_GIVEN, VAC_PUBLISH_CODE)  VALUES(?,?,?,?,?,?)";
          }

          getSqlModel().singleExecute(insertVacancyDtlQuery, vacancyObj);*/

          for (int i = 0; i < reqVacDtlCode.length; i++) {
            String insertVacHdr = "INSERT INTO HRMS_REC_VACPUB_HDR (PUB_CODE,PUB_REQS_CODE,PUB_STATUS, PUB_DATE,  PUB_VACAN_DTLCODE, PUB_EMPID,PUB_TO_CONS, PUB_TO_REF, PUB_TO_WEB,PUB_REC_TYPE_INT, PUB_REC_TYPE_EXT, PUB_TO_CANDJOB) VALUES(" + 
              checkNull(String.valueOf(pubMaxCode[0][0])) + "," + 
              bean.getRequisitionCode() + 
              ",'P',TO_DATE(SYSDATE,'DD-MM-YYYY')," + 
              reqVacDtlCode[i][0] + 
              "," + 
              bean.getUserEmpId() + 
              ",'Y','Y','Y','Y','Y','Y')";

            boolean resultHdrInsert = getSqlModel().singleExecute(
              insertVacHdr);

            if (resultHdrInsert) {
              String updReqVacDtlQuey = "UPDATE HRMS_REC_REQS_VACDTL SET VACAN_STATUS='P' WHERE VACAN_DTL_CODE = " + 
                reqVacDtlCode[i][0];
              getSqlModel().singleExecute(updReqVacDtlQuey);

              String insertVacDtl = "INSERT INTO HRMS_REC_VACPUB_RECDTL (PUB_DTL_CODE, PUB_CODE, PUB_REC_EMPID, PUB_ASG_VAC, PUB_VAC_STATUS)  VALUES((SELECT NVL(MAX(PUB_DTL_CODE),0)+1 FROM HRMS_REC_VACPUB_RECDTL), (SELECT MAX(PUB_CODE) FROM HRMS_REC_VACPUB_HDR)," + 
                bean.getRecruiterId() + 
                "," + 
                reqVacDtlCode[i][1] + ",'O')";
              getSqlModel().singleExecute(insertVacDtl);
            }
          }
        }
      }

    /*  String vacancyStatsDataQuery = "SELECT VAC_OFFER_CODE, VAC_OFFER_GIVEN FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE = " + hiddenRequisitionCode + " AND VAC_STATUS = 'O' AND VAC_OFFER_GIVEN='N' AND VAC_OFFER_CODE IS NULL";
      Object[][] vacancyStatsDataObj = getSqlModel().getSingleResult(vacancyStatsDataQuery);
      if ((vacancyStatsDataObj != null) && (vacancyStatsDataObj.length > 0)) {*/
        Object[][] checkResumeData = (Object[][])null;
        String checkResumeBank = "SELECT RESUME_REQS_CODE,RESUME_CAND_CODE FROM HRMS_REC_RESUME_BANK  WHERE RESUME_REQS_CODE =" + 
          bean.getRequisitionCode() + 
          " AND RESUME_CAND_CODE =" + bean.getCandidateCode() + " ";
        checkResumeData = getSqlModel().getSingleResult(checkResumeBank);
        if ((checkResumeData == null) || (checkResumeData.length <= 0))
        {
          String insertResumeBank = "INSERT INTO HRMS_REC_RESUME_BANK (RESUME_CODE, RESUME_REQS_CODE, RESUME_CAND_CODE, RESUME_REC_EMPID,  RESUME_APPR_EMPID, RESUME_STATUS) VALUES((SELECT NVL(MAX(RESUME_CODE),0)+1 FROM HRMS_REC_RESUME_BANK), " + 
            bean.getRequisitionCode() + 
            "," + 
            bean.getCandidateCode() + 
            ",'" + 
            bean.getRecruiterId() + 
            "', " + " " + bean.getUserEmpId() + ",'D')";
          getSqlModel().singleExecute(insertResumeBank);
        }
      //}
    }
    catch (Exception e) {
      logger.error("exception in updateOfferDetails method", e);
    }

    return flag;
  }

  public boolean insertAppointmentDetailsFromOnline(String offercode) {
    boolean result = false;
    try
    {
      String sqlQuery = "SELECT OFFER_REQS_CODE, OFFER_CAND_CODE, OFFER_JD, OFFER_ROLES_RESP,  OFFER_EMP_TYPE, OFFER_HIRE_MANAGER, TO_CHAR(OFFER_EXP_JOINDATE,'DD-MM-YYYY'),  OFFER_REPORT_TO, OFFER_JOIN_FORM, OFFER_GRADE, OFFER_TEMPLATE, OFFER_PROBATION,  OFFER_PROB_MONTHS, OFFER_SIGN_AUTH, OFFER_REMARKS, OFFER_CONSTRAINTS, OFFER_APPR_STATUS,  OFFER_BG_REQ, TO_CHAR(OFFER_JOIN_DATE,'DD-MM-YYYY'), OFFER_CURR_CTC, OFFER_NEG_CTC,  OFFER_OFFERED_CTC, TO_CHAR(OFFER_DATE,'DD-MM-YYYY') , OFFER_DIVISION, OFFER_BRANCH, OFFER_DEPT,  OFFER_POSITION, OFFER_ADMIN_REPORTING_TO FROM HRMS_REC_OFFER  WHERE OFFER_CODE = " + 
        offercode;

      Object[][] dataObj = getSqlModel().getSingleResult(sqlQuery);

      Object[][] appointDetails = new Object[1][29];

      if ((dataObj != null) && (dataObj.length > 0))
      {
        appointDetails[0][0] = checkNull(String.valueOf(dataObj[0][0]));
        appointDetails[0][1] = checkNull(String.valueOf(dataObj[0][1]));
        appointDetails[0][2] = "D";
        appointDetails[0][3] = checkNull(String.valueOf(dataObj[0][2]));
        appointDetails[0][4] = checkNull(String.valueOf(dataObj[0][3]));
        appointDetails[0][5] = checkNull(String.valueOf(dataObj[0][4]));
        appointDetails[0][6] = checkNull(String.valueOf(dataObj[0][5]));
        appointDetails[0][7] = checkNull(String.valueOf(dataObj[0][6]));
        appointDetails[0][8] = checkNull(String.valueOf(dataObj[0][7]));
        appointDetails[0][9] = checkNull(String.valueOf(dataObj[0][8]));
        appointDetails[0][10] = checkNull(String.valueOf(dataObj[0][9]));
        appointDetails[0][11] = checkNull(String.valueOf(dataObj[0][10]));
        appointDetails[0][12] = checkNull(String.valueOf(dataObj[0][11]));
        appointDetails[0][13] = checkNull(String.valueOf(dataObj[0][12]));
        appointDetails[0][14] = checkNull(String.valueOf(dataObj[0][13]));
        appointDetails[0][15] = checkNull(String.valueOf(dataObj[0][14]));
        appointDetails[0][16] = checkNull(String.valueOf(dataObj[0][15]));
        if (checkNull(String.valueOf(dataObj[0][16])).equals("S"))
          appointDetails[0][17] = "NOT KNOWN";
        else {
          appointDetails[0][17] = "";
        }
        appointDetails[0][18] = checkNull(String.valueOf(dataObj[0][17]));
        appointDetails[0][19] = checkNull(String.valueOf(dataObj[0][18]));
        appointDetails[0][20] = checkNull(String.valueOf(dataObj[0][19]));
        appointDetails[0][21] = checkNull(String.valueOf(dataObj[0][20]));
        appointDetails[0][22] = checkNull(String.valueOf(dataObj[0][21]));
        appointDetails[0][23] = checkNull(String.valueOf(dataObj[0][22]));
        appointDetails[0][24] = checkNull(String.valueOf(dataObj[0][23]));
        appointDetails[0][25] = checkNull(String.valueOf(dataObj[0][24]));
        appointDetails[0][26] = checkNull(String.valueOf(dataObj[0][25]));
        appointDetails[0][27] = checkNull(String.valueOf(dataObj[0][26]));
        appointDetails[0][28] = checkNull(String.valueOf(dataObj[0][27]));
      }

      result = getSqlModel().singleExecute(getQuery(5), appointDetails);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return result;
  }

  public boolean insertAppointmentDetails(OfferDetails bean)
  {
    boolean result = false;
    try {
      Object[][] appointDetails = new Object[1][29];

      appointDetails[0][0] = bean.getRequisitionCode().trim();

      appointDetails[0][1] = bean.getCandidateCode().trim();

      appointDetails[0][2] = "D";
      appointDetails[0][3] = bean.getJobDescription().trim();

      appointDetails[0][4] = bean.getRolesResponsibility().trim();

      appointDetails[0][5] = bean.getEmpTypeCode().trim();

      appointDetails[0][6] = bean.getHiringMgrCode().trim();

      appointDetails[0][7] = bean.getExpJoiningDate().trim();

      appointDetails[0][8] = bean.getReportingToCode().trim();

      appointDetails[0][9] = bean.getTestRequirements().trim();

      appointDetails[0][10] = bean.getGradeCode().trim();

      if (bean.getProbation().equals("true"))
        appointDetails[0][12] = "Y";
      else {
        appointDetails[0][12] = "N";
      }

      appointDetails[0][13] = bean.getMonths().trim();

      appointDetails[0][14] = bean.getAuthorityCode().trim();

      appointDetails[0][15] = bean.getRemarks().trim();
      appointDetails[0][16] = bean.getCandConstraints().trim();

      if (bean.getOfferStatus().equals("S"))
        appointDetails[0][17] = "P";
      else {
        appointDetails[0][17] = "";
      }

      if (bean.getBackgroundCheck().equals("true"))
        appointDetails[0][18] = "Y";
      else {
        appointDetails[0][18] = "N";
      }

      appointDetails[0][19] = bean.getJoiningDate().trim();

      appointDetails[0][20] = bean.getCurrentCtc();
      appointDetails[0][21] = bean.getNegotiatedCtc().trim();

      appointDetails[0][22] = bean.getOfferedCtc().trim();

      appointDetails[0][23] = bean.getOfferDate().trim();
      appointDetails[0][24] = bean.getDivisionCode();
      appointDetails[0][25] = bean.getBranchCode();
      appointDetails[0][26] = bean.getDeptCode();
      appointDetails[0][27] = bean.getPositionCode();
      appointDetails[0][28] = bean.getReportingToAdminCode();

      result = getSqlModel().singleExecute(getQuery(5), appointDetails);
    }
    catch (Exception e)
    {
      logger.error("exception in insertAppointmentDetails method ", e);
    }

    return result;
  }

  public String checkNull(String result)
  {
    if ((result == null) || (result.equals("null"))) {
      return "";
    }

    return result;
  }

  public void setSysDate(OfferDetails bean)
  {
    try
    {
      String dateQuery = "SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM DUAL";
      Object[][] sysDate = getSqlModel().getSingleResult(dateQuery);
      bean.setOfferDate(String.valueOf(sysDate[0][0]));
      Object[][] sighAuthData = (Object[][])null;

      String signingAuthFrmConf = "SELECT CONF_SIGN_AUTH,EMP_FNAME||' '||EMP_LNAME,RANK_NAME  FROM HRMS_REC_CONF  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REC_CONF.CONF_SIGN_AUTH) INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)";

      sighAuthData = getSqlModel().getSingleResult(signingAuthFrmConf);

      if ((sighAuthData != null) && (sighAuthData.length > 0)) {
        bean.setAuthorityCode(String.valueOf(sighAuthData[0][0]));
        bean.setSigningAuthority(String.valueOf(sighAuthData[0][1]));
        bean.setDesignation(String.valueOf(sighAuthData[0][2]));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void viewDetails(OfferDetails offerdetails, String tempcode) {
		try {
			String query = "SELECT NVL(TEMPLATE_NAME,'') FROM HRMS_LETTERTEMPLATE_HDR WHERE TEMPLATE_ID="
					+ String.valueOf(tempcode);
			Object[][] templateName = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

  public void returnFromPostResume(OfferDetails offerDetails, String candCode, Object[] formValues)
  {
    try
    {
      offerDetails.setRequisitionCode(String.valueOf(formValues[1]));
      offerDetails.setRequisitionName(String.valueOf(formValues[2]));
      offerDetails.setPosition(String.valueOf(formValues[3]));
      offerDetails.setHiringManager(String.valueOf(formValues[4]));
      offerDetails.setDivision(String.valueOf(formValues[5]));
      offerDetails.setBranch(String.valueOf(formValues[6]));
      offerDetails.setDepartment(String.valueOf(formValues[7]));

      offerDetails.setJoiningDate(String.valueOf(formValues[8]));
      offerDetails.setOfferDate(String.valueOf(formValues[9]));
      offerDetails.setCurrentCtc(String.valueOf(formValues[10]));
      offerDetails.setNegotiatedCtc(String.valueOf(formValues[11]));

      offerDetails.setJobCode(String.valueOf(formValues[12]));
      offerDetails.setJobDescription(String.valueOf(formValues[13]));
      offerDetails.setRolesResponsibility(String.valueOf(formValues[14]));
      offerDetails.setEmpType(String.valueOf(formValues[15]));
      offerDetails.setEmpTypeCode(String.valueOf(formValues[16]));
      offerDetails.setHiringMgr(String.valueOf(formValues[17]));
      offerDetails.setHiringMgrCode(String.valueOf(formValues[18]));
      offerDetails.setExpJoiningDate(String.valueOf(formValues[19]));
      offerDetails.setReportingTo(String.valueOf(formValues[20]));
      offerDetails.setReportingToCode(String.valueOf(formValues[21]));
      offerDetails.setTestReqCode(String.valueOf(formValues[22]));
      offerDetails.setTestRequirements(String.valueOf(formValues[23]));
      offerDetails.setGrade(String.valueOf(formValues[24]));
      offerDetails.setGradeCode(String.valueOf(formValues[25]));
      offerDetails.setTemplate(String.valueOf(formValues[26]));
      offerDetails.setTemplateCode(String.valueOf(formValues[27]));
      offerDetails.setProbation(String.valueOf(formValues[28]));
      offerDetails.setSigningAuthority(String.valueOf(formValues[29]));
      offerDetails.setAuthorityCode(String.valueOf(formValues[30]));
      offerDetails.setBackgroundCheck(String.valueOf(formValues[31]));
      offerDetails.setDesignation(String.valueOf(formValues[32]));
      offerDetails.setOfferStatus(String.valueOf(formValues[33]));
      offerDetails.setOfferedCtc(String.valueOf(formValues[34]));
      offerDetails.setRemarks(String.valueOf(formValues[35]));
      offerDetails.setCandConstraints(String.valueOf(formValues[36]));
      offerDetails.setCandidateCode(String.valueOf(formValues[37]));
      offerDetails.setCandidateName(String.valueOf(formValues[38]));
      offerDetails.setMonths(String.valueOf(formValues[39]));

      offerDetails.setPositionCode(String.valueOf(formValues[40]));
      offerDetails.setDivisionCode(String.valueOf(formValues[41]));
      offerDetails.setDeptCode(String.valueOf(formValues[42]));
      offerDetails.setBranchCode(String.valueOf(formValues[43]));
      offerDetails.setRecruiterId(String.valueOf(formValues[44]));
      offerDetails.setRecruiterName(String.valueOf(formValues[45]));

      if ((candCode != null) && (!candCode.equals(""))) {
        String candQry = "SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME FROM HRMS_REC_CAND_DATABANK WHERE CAND_CODE = " + 
          candCode;

        Object[][] candObj = getSqlModel().getSingleResult(candQry);

        if ((candObj != null) && (candObj.length != 0)) {
          offerDetails.setCandidateName(
            String.valueOf(candObj[0][0]).trim());
          offerDetails.setCandidateCode(candCode);
        }
      }
    } catch (Exception e) {
      logger.error("exception in returnFromPostResume method", e);
    }
  }

  public void showGrade(OfferDetails offerDetails, String id, HttpServletRequest request)
  {
    try {
      Object[][] amt = (Object[][])null;
      ArrayList tableList = new ArrayList();
      String sqlQuery = "SELECT distinct HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME, NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,'0'),NVL(CREDIT_PERIODICITY,' ')  FROM HRMS_CREDIT_HEAD   left JOIN HRMS_SALGRADE_DTL ON (HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE  and HRMS_SALGRADE_DTL.SALGRADE_CODE=" + 
        offerDetails.getSalgrdId() + 
        " ) " + 
        " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
      amt = getSqlModel().getSingleResult(sqlQuery);
      if ((amt != null) && (amt.length > 0)) {
        for (int i = 0; i < amt.length; i++) {
          OfferDetails bean = new OfferDetails();
          bean.setSalCode(String.valueOf(amt[i][0]));
          bean.setSalHead(String.valueOf(amt[i][1]));
          bean.setNewAmt(Utility.twoDecimals(
            String.valueOf(amt[i][2])));
          if (String.valueOf(amt[i][3]).equals("M"))
            bean.setSalPerdiocity("Monthly");
          if (String.valueOf(amt[i][3]).equals("Q"))
            bean.setSalPerdiocity("Quarterly");
          if (String.valueOf(amt[i][3]).equals("H"))
            bean.setSalPerdiocity("Half Yearly");
          if (String.valueOf(amt[i][3]).equals("A"))
            bean.setSalPerdiocity("Annually");
          tableList.add(bean);
        }
        offerDetails.setSalList(tableList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void saveSalaryStruc(OfferDetails offerDetails, HttpServletRequest request)
  {
    try {
      String type = "";
      String typeCode = "";
      String delete = "DELETE FROM HRMS_REC_OFFER_SALARY WHERE OFFER_REQS_CODE = " + 
        offerDetails.getRequisitionCode() + 
        " AND OFFER_CAND_CODE = " + 
        offerDetails.getCandidateCode();
      getSqlModel().singleExecute(delete);
      if (!offerDetails.getGrdFlag().equals("false")) {
        type = "G";
        typeCode = offerDetails.getSalgrdId();
      } else if (!offerDetails.getFrmFlag().equals("false")) {
        type = "F";
        typeCode = offerDetails.getFrmId();
      } else {
        type = " ";
        typeCode = "0";
      }
      String[] creditCode = request.getParameterValues("salCode");
      String[] creditAmt = request.getParameterValues("newAmt");
      for (int i = 0; i < creditCode.length; i++) {
        String insert = "INSERT INTO HRMS_REC_OFFER_SALARY (OFFER_REQS_CODE,OFFER_CAND_CODE,OFFER_CREDIT_CODE,  OFFER_CREDIT_AMOUNT,OFFER_TYPE,OFFER_TYPE_CODE)  VALUES(" + 
          offerDetails.getRequisitionCode() + 
          "," + 
          offerDetails.getCandidateCode() + 
          "," + 
          " " + 
          creditCode[i] + 
          "," + 
          creditAmt[i] + 
          ",'" + 
          type + 
          "'," + typeCode + ")";
        getSqlModel().singleExecute(insert);
      }
      viewSalaryStructure(offerDetails);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void saveSalaryStrucDbt(OfferDetails offerDetails, HttpServletRequest request)
  {
    boolean delQueryResult = false;

    String delete = "DELETE FROM HRMS_REC_OFFER_SALARY_DBT WHERE OFFER_REQS_DBT_CODE = " + 
      offerDetails.getRequisitionCode() + 
      " AND OFFER_CAND_DBT_CODE = " + 
      offerDetails.getCandidateCode();
    delQueryResult = getSqlModel().singleExecute(delete);
    if (delQueryResult) {
      String[] debitCode = request.getParameterValues("dbtCode");
      String[] debitAmt = request.getParameterValues("newAmtDbt");
      if ((debitCode != null) && (debitCode.length > 0)) {
        for (int i = 0; i < debitCode.length; i++) {
          String insert = "INSERT INTO HRMS_REC_OFFER_SALARY_DBT (OFFER_REQS_DBT_CODE,OFFER_CAND_DBT_CODE,OFFER_DEBIT_CODE,  OFFER_DEBIT_AMOUNT,OFFER_DEBIT_TYPE)  VALUES(" + 
            offerDetails.getRequisitionCode() + 
            "," + 
            offerDetails.getCandidateCode() + 
            "," + 
            " " + 
            debitCode[i] + "," + debitAmt[i] + ",'')";
          getSqlModel().singleExecute(insert);
        }
      }
    }
    viewSalaryStructureDebit(offerDetails);
  }

  public void viewSalaryStructure(OfferDetails offerDetails) {
    Object[][] data = (Object[][])null;
    ArrayList tableList = new ArrayList();
    logger.info("in viewSalarySturcture   ");
    try {
      String query = "SELECT OFFER_CREDIT_CODE,CREDIT_NAME,CREDIT_PERIODICITY,OFFER_CREDIT_AMOUNT,OFFER_TYPE,  OFFER_TYPE_CODE,SALGRADE_TYPE,FORMULA_NAME  FROM HRMS_REC_OFFER_SALARY  LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_REC_OFFER_SALARY.OFFER_CREDIT_CODE)  LEFT JOIN HRMS_SALGRADE_HDR ON (HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_REC_OFFER_SALARY.OFFER_TYPE_CODE)  LEFT JOIN HRMS_FORMULABUILDER_HDR ON (HRMS_FORMULABUILDER_HDR.FORMULA_ID = HRMS_REC_OFFER_SALARY.OFFER_TYPE_CODE)  WHERE OFFER_REQS_CODE = " + 
        offerDetails.getRequisitionCode() + 
        " AND  OFFER_CAND_CODE = " + 
        offerDetails.getCandidateCode() + " ORDER BY CREDIT_CODE";
      data = getSqlModel().getSingleResult(query);
    } catch (Exception e) {
      logger.error("exception in viewSalaryStructure", e);
    }
    if (data == null) {
      viewInitially(offerDetails);
    }
    else if (data.length == 0) {
      viewInitially(offerDetails);
    }
    else {
      try {
        for (int i = 0; i < data.length; i++) {
          if (String.valueOf(data[0][4]).equals("G")) {
            offerDetails.setGrdFlag("true");
            offerDetails.setSalgrdId(String.valueOf(data[0][5]));
            offerDetails.setSalgrdName(String.valueOf(data[0][6]));
            offerDetails.setFrmId("");
            offerDetails.setFrmName("");
          } else if (String.valueOf(data[0][4]).equals("F")) {
            offerDetails.setFrmFlag("true");
            offerDetails.setFrmId(String.valueOf(data[0][5]));
            offerDetails.setFrmName(String.valueOf(data[0][7]));
            offerDetails.setSalgrdId("");
            offerDetails.setSalgrdName("");
          } else {
            logger.info("no type");
            offerDetails.setFrmId("");
            offerDetails.setSalgrdId("");
            offerDetails.setSalgrdName("");
            offerDetails.setFrmName("");
            offerDetails.setFrmFlag("false");
            offerDetails.setGrdFlag("false");
          }
          OfferDetails bean = new OfferDetails();
          bean.setSalCode(String.valueOf(data[i][0]));
          bean.setSalHead(String.valueOf(data[i][1]));
          bean.setNewAmt(Utility.twoDecimals(
            String.valueOf(data[i][3])));
          if (String.valueOf(data[i][2]).equals("M"))
            bean.setSalPerdiocity("Monthly");
          if (String.valueOf(data[i][2]).equals("Q"))
            bean.setSalPerdiocity("Quarterly");
          if (String.valueOf(data[i][2]).equals("H"))
            bean.setSalPerdiocity("Half Yearly");
          if (String.valueOf(data[i][2]).equals("A"))
            bean.setSalPerdiocity("Annually");
          tableList.add(bean);
        }
      }
      catch (Exception e) {
        logger.error("exception in viewSalaryStructure for loop", e);
      }
      offerDetails.setSalList(tableList);
    }
  }

  public void viewSalaryStructureDebit(OfferDetails offerDetails)
  {
    try
    {
      Object[][] data = (Object[][])null;
      ArrayList tableList = new ArrayList();
      String query = "SELECT OFFER_DEBIT_CODE,DEBIT_NAME,DEBIT_PERIODICITY,OFFER_DEBIT_AMOUNT,OFFER_DEBIT_TYPE,   OFFER_DEBIT_TYPE_CODE  FROM HRMS_REC_OFFER_SALARY_DBT  LEFT JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_REC_OFFER_SALARY_DBT.OFFER_DEBIT_CODE)  WHERE OFFER_REQS_DBT_CODE = " + 
        offerDetails.getRequisitionCode() + 
        " AND OFFER_CAND_DBT_CODE = " + 
        offerDetails.getCandidateCode() + " ORDER BY DEBIT_CODE";
      data = getSqlModel().getSingleResult(query);
      if ((data != null) && (data.length > 0)) {
        for (int i = 0; i < data.length; i++) {
          OfferDetails bean = new OfferDetails();
          bean.setDbtCode(String.valueOf(data[i][0]));
          bean.setDbtHead(String.valueOf(data[i][1]));
          bean.setNewAmtDbt(Utility.twoDecimals(
            String.valueOf(data[i][3])));
          if (String.valueOf(data[i][2]).equals("M"))
            bean.setDbtPerdiocity("Monthly");
          if (String.valueOf(data[i][2]).equals("Q"))
            bean.setDbtPerdiocity("Quarterly");
          if (String.valueOf(data[i][2]).equals("H"))
            bean.setDbtPerdiocity("Half Yearly");
          if (String.valueOf(data[i][2]).equals("A"))
            bean.setDbtPerdiocity("Annually");
          tableList.add(bean);
        }
        offerDetails.setDbtList(tableList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void editSalaryStruc(OfferDetails offerDetails, HttpServletRequest request)
  {
    try {
      ArrayList tableList = new ArrayList();
      String[] creditCode = request.getParameterValues("salCode");
      String[] creditAmt = request.getParameterValues("newAmt");
      String[] periodicity = request.getParameterValues("salPerdiocity");
      String[] creditName = request.getParameterValues("salHead");
      for (int i = 0; i < creditCode.length; i++) {
        OfferDetails bean = new OfferDetails();
        bean.setSalCode(creditCode[i]);
        bean.setSalHead(creditName[i]);
        bean.setNewAmt(creditAmt[i]);
        bean.setSalPerdiocity(periodicity[i]);
        tableList.add(bean);
      }
      offerDetails.setSalList(tableList);
      offerDetails.setViewFlagStruc("true");
      if ((!offerDetails.getSalgrdId().equals("")) && 
        (!offerDetails.getSalgrdId().equals(null)) && 
        (!offerDetails.getSalgrdId().equals("null"))) {
        offerDetails.setGrdFlag("true");
      } else if ((!offerDetails.getFrmId().equals("")) && 
        (!offerDetails.getFrmId().equals(null)) && 
        (!offerDetails.getFrmId().equals("null"))) {
        offerDetails.setFrmFlag("true");
      } else {
        offerDetails.setGrdFlag("false");
        offerDetails.setFrmFlag("false");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void editSalaryStrucDbt(OfferDetails offerDetails, HttpServletRequest request)
  {
    try
    {
      ArrayList tableList = new ArrayList();
      String[] debitCode = request.getParameterValues("dbtCode");
      String[] debitAmt = request.getParameterValues("newAmtDbt");
      String[] periodicity = request.getParameterValues("dbtPerdiocity");
      String[] debitName = request.getParameterValues("dbtHead");
      if ((debitCode != null) && (debitCode.length > 0)) {
        for (int i = 0; i < debitCode.length; i++) {
          OfferDetails bean = new OfferDetails();
          bean.setDbtCode(debitCode[i]);
          bean.setDbtHead(debitName[i]);
          bean.setNewAmtDbt(debitAmt[i]);
          bean.setDbtPerdiocity(periodicity[i]);
          tableList.add(bean);
        }
      }
      offerDetails.setDbtList(tableList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void viewInitially(OfferDetails offerDetails) {
    try {
      Object[][] data = (Object[][])null;
      ArrayList tableList = new ArrayList();
      String query = "SELECT CREDIT_CODE,CREDIT_NAME,CREDIT_PERIODICITY FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE";
      data = getSqlModel().getSingleResult(query);
      if ((data != null) && (data.length > 0)) {
        for (int i = 0; i < data.length; i++) {
          OfferDetails bean = new OfferDetails();
          bean.setSalCode(String.valueOf(data[i][0]));
          bean.setSalHead(String.valueOf(data[i][1]));
          bean.setNewAmt("0");
          if (String.valueOf(data[i][2]).equals("M"))
            bean.setSalPerdiocity("Monthly");
          if (String.valueOf(data[i][2]).equals("Q"))
            bean.setSalPerdiocity("Quarterly");
          if (String.valueOf(data[i][2]).equals("H"))
            bean.setSalPerdiocity("Half Yearly");
          if (String.valueOf(data[i][2]).equals("A"))
            bean.setSalPerdiocity("Annually");
          tableList.add(bean);
        }
        offerDetails.setSalList(tableList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void viewInitiallyDebit(OfferDetails offerDetails)
  {
    try
    {
      Object[][] data = (Object[][])null;
      ArrayList tableList = new ArrayList();
      String query = " SELECT DEBIT_CODE,DEBIT_NAME,DEBIT_PERIODICITY FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";
      data = getSqlModel().getSingleResult(query);
      if ((data != null) && (data.length > 0)) {
        for (int i = 0; i < data.length; i++) {
          OfferDetails bean = new OfferDetails();
          bean.setDbtCode(String.valueOf(data[i][0]));
          bean.setDbtHead(String.valueOf(data[i][1]));
          bean.setNewAmtDbt("0");
          if (String.valueOf(data[i][2]).equals("M"))
            bean.setDbtPerdiocity("Monthly");
          if (String.valueOf(data[i][2]).equals("Q"))
            bean.setDbtPerdiocity("Quarterly");
          if (String.valueOf(data[i][2]).equals("H"))
            bean.setDbtPerdiocity("Half Yearly");
          if (String.valueOf(data[i][2]).equals("A"))
            bean.setDbtPerdiocity("Annually");
          tableList.add(bean);
        }
        offerDetails.setDbtList(tableList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

//Method for calculating credit amount according to selected formula ...
	public void showFormula(Object[] sCode, Object[] sHead, Object[] snewAmt,
			Object[] salPer, OfferDetails offerDetail) {
		try {
			Object[][] tableData = new Object[sCode.length][4];
			for (int i1 = 0; i1 < tableData.length; i1++) {

				// loop for taking original values from jsp...

				tableData[i1][0] = String.valueOf(sCode[i1]);
				tableData[i1][1] = String.valueOf(sHead[i1]);
				// tableData[i1][2] =
				// Utility.twoDecimals(Double.valueOf(String.valueOf(scurAmt[i1])));
				tableData[i1][2] = Utility.twoDecimals(Double.valueOf(String
						.valueOf(snewAmt[i1])));

				tableData[i1][3] = String.valueOf(salPer[i1]);
			}

			// Query for retrieving formula information...

			String frmQue = " SELECT SAL_CODE, SAL_TYPE,SAL_FORMULA FROM HRMS_FORMULABUILDER_HDR "
					+ " INNER JOIN HRMS_FORMULABUILDER_DTL ON(HRMS_FORMULABUILDER_DTL.FORMULA_ID = HRMS_FORMULABUILDER_HDR.FORMULA_ID) "
					+ " WHERE HRMS_FORMULABUILDER_HDR.FORMULA_ID = "
					+ offerDetail.getFrmId() + " ORDER BY SAL_CODE";

			Object[][] frmObj = getSqlModel().getSingleResult(frmQue);

			if (offerDetail.getGrsAmt().length() > 0) {
				double CTC = Double.parseDouble(offerDetail.getGrsAmt());

				for (int j1 = 0; j1 < tableData.length; j1++) {
					tableData[j1][2] = "0.00";
				}

				ArrayList diffData = new ArrayList();
				ArrayList formData = new ArrayList();

				for (int j = 0; j < tableData.length; j++) {

					/*
					 * Loop for checking length of salary codes This loop
					 * calculates new salary amount according to salary code .
					 */

					for (int i = 0; i < frmObj.length; i++) {

						/*
						 * Loop for checking formula type according to formula
						 * code.
						 */

						String exec = "";
						if (sCode[j].equals(String.valueOf(frmObj[i][0]))) {

							/*
							 * If loop for checking equal salary code It
							 * calculates new amount if salary code is equal to
							 * salary code of formula
							 */
							String sal_type = String.valueOf(frmObj[i][1]);
							String sal_formula = String.valueOf(frmObj[i][2]);

							if (sal_type.trim().equals("Fi")) {

								/*
								 * if loop for calculating new amount when
								 * salary type is fixed
								 */

								tableData[j][2] = sal_formula;
							} else if (sal_type.trim().equals("Fr")) {

								/*
								 * if loop for calculating new amount when
								 * salary type is formula
								 */

								logger.info("In formula");
								// formData.add(String.valueOf(tableData[j][0]));

								try {
									exec = executeFormula(sal_formula, CTC,
											tableData, formData);
									logger.info("exec===" + exec);
								} catch (Exception e) {
									e.printStackTrace();
								}
								tableData[j][2] = Utility.twoDecimals(Math
										.round(Double.parseDouble(String
												.valueOf(exec))));
								// logger.info("tableData[j][3]==="+tableData[j][3]);
							} else if (sal_type.trim().equals("Df")) {

								/*
								 * if loop for calculating new amount when
								 * salary type is difference
								 */

								diffData.add(String.valueOf(tableData[j][0]));
								/*
								 * double sum =
								 * Double.parseDouble(String.valueOf(tableData[j][3]))*0.12*12;
								 * logger.info("sum========="+sum); for (int k =
								 * 0; k < tableData.length; k++) {
								 * logger.info("SalCode======="+tableData[k][0]);
								 * logger.info("Period======="+tableData[k][4]);
								 * logger.info("Amt======="+tableData[k][3]);
								 * if(String.valueOf(tableData[k][4]).equals("A")) {
								 * sum +=
								 * Double.parseDouble(String.valueOf(tableData[k][3])); }
								 * else
								 * if(String.valueOf(tableData[k][4]).equals("Q")) {
								 * sum +=
								 * Double.parseDouble(String.valueOf(tableData[k][3]))*
								 * 4; } else
								 * if(String.valueOf(tableData[k][4]).equals("H")) {
								 * sum +=
								 * Double.parseDouble(String.valueOf(tableData[k][3])) *
								 * 2; } else
								 * if(String.valueOf(tableData[k][4]).equals("M")) {
								 * sum +=
								 * Double.parseDouble(String.valueOf(tableData[k][3])) *
								 * 12; } } String cal = "0"; if(CTC >= sum) {
								 * cal = ""+Math.round(((CTC-sum)/12)); } else {
								 * cal = ""+Math.round(((sum - CTC)/12)); }
								 */
								tableData[j][2] = "0.00";
							} // end of else if
						} // end of if loop
					} // end of i loop
				} // end of j loop

				// calDifference(diffData, tableData, CTC);
				// calculates salary amount when formula type is difference...
				double sum = 0.00;

				/*
				 * Query for cheking pf type of employee if pf type is yes then
				 * calculates pf of employee type pf is not applicable for
				 * consultant type
				 */
				/*
				 * String pfQue=" SELECT TYPE_PF FROM HRMS_EMP_TYPE" +" INNER
				 * JOIN HRMS_EMP_OFFC ON(HRMS_EMP_TYPE.TYPE_ID =
				 * HRMS_EMP_OFFC.EMP_TYPE)" +" WHERE HRMS_EMP_OFFC.EMP_ID
				 * ="+offerDetail.getEmpCode(); Object[][] pfObj =
				 * getSqlModel().getSingleResult(pfQue);
				 */

				/*
				 * if(String.valueOf(pfObj[0][0]).equals("Y")) { sum +=
				 * (Double.parseDouble(String.valueOf(tableData[0][3])) * 0.12 *
				 * 12); logger.info("pf==========" +sum); }
				 */

				for (int k = 0; k < tableData.length; k++) {

					/*
					 * calculates difference according to periodicity
					 */

					try {

						logger.info("pppppppppppp======="
								+ String.valueOf(tableData[k][3]));
						if (String.valueOf(tableData[k][3]).equals("Annually")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][2])));
						} else if (String.valueOf(tableData[k][3]).equals(
								"Quarterly")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][2])) * 4);
						} else if (String.valueOf(tableData[k][3]).equals(
								"Half Yearly")) {
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][2])) * 2);
						} else if (String.valueOf(tableData[k][3]).equals(
								"Monthly")) {
							logger.info("String.valueOf(tableData[k][3]====)"
									+ String.valueOf(tableData[k][3]));
							sum += (Double.parseDouble(String
									.valueOf(tableData[k][2])) * 12);
						}
						logger.info("SalCode==" + tableData[k][0]);
						logger.info("SalAmt==" + tableData[k][2]);
						logger.info("sum==========" + sum);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				double diffCal = 0.0;
				double cal = 0.00;

				// diffCal = CTC -
				if (CTC >= sum) {
					cal = ((CTC - sum) / 12);
					logger.info("cal======" + cal);
				}
				if (CTC <= sum) {
					cal = ((sum - CTC) / 12);
					logger.info("cal======" + cal);
				}
				for (int j = 0; j < tableData.length; j++) {

					for (int i = 0; i < diffData.size(); i++) {
						if (String.valueOf(tableData[j][0]).equals(
								diffData.get(i))) {
							logger.info("cnt==============" + i);
							tableData[j][2] = Utility.twoDecimals(Math
									.round(cal));
						} // end of if loop
					} // end of i loop
				} // end of j loop

			} // end outer if
			ArrayList<Object> list = new ArrayList<Object>();

			for (int i = 0; i < tableData.length; i++) {
				/* loop for setting new calculated values using formula */
				OfferDetails bean = new OfferDetails();
				bean.setSalCode(String.valueOf(tableData[i][0]));
				bean.setSalHead(String.valueOf(tableData[i][1]));
				// promotion1.setCurAmt(Utility.twoDecimals(Double.parseDouble(String.valueOf(tableData[i][2]))));
				bean.setNewAmt(Utility.twoDecimals(Double.parseDouble(String
						.valueOf(tableData[i][2]))));
				bean.setSalPerdiocity(String.valueOf(tableData[i][3]));
				list.add(bean);
			} // end of i loop
			offerDetail.setSalList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// Method for executing formula when formula type is formula(fr).......
	public String executeFormula(String sal_formula, double CTC,
			Object[][] tableData, ArrayList frmData) {
		try {
			String str[] = sal_formula.split("#");
			String frml = "";
			for (int i = 0; i < str.length; i++) {
				/*
				 * Loop for checking string length which is split by #
				 */
				if (str[i].equals("CTC")) {
					logger.info("Inside CTC");
					frml += "" + CTC;
				} else {
					/*
					 * Loop for salary codes
					 */
					String flag = "false", strValue = "";
					int cnt = 1;

					if (str[i].equals("") || str[i].equals("null")) {
						strValue = str[i];
					} else if (str[i].length() == 1) {
						strValue = str[i];
					} else if (str[i].contains("C")) {
						strValue = str[i].substring(1, str[i].length());
					} else {
						strValue = str[i];
					}
					for (int z = 0; z < tableData.length; z++) {
						if (String.valueOf(strValue).trim().equals("" + cnt))// *12)/100
																				// //
																				// (1*12)/100
						{
							for (int j = 0; j < tableData.length; j++) {
								if (String.valueOf(tableData[j][0]).trim()
										.equals("" + cnt)) {
									frml += Utility.twoDecimals(Double
											.parseDouble(String
													.valueOf(tableData[j][2])));
									flag = "true";
								}
							}
						}
						cnt++;
					}
					if (flag.equals("false")) {
						frml += "" + strValue;// (1000*12)/100
					}
				}
			}
			return expressionEvaluate(frml);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

  public String expressionEvaluate(String formula)
  {
    try
    {
      XJep j = new XJep();
      try
      {
        Node node = j.parse(formula);
        Node processed = j.preprocess(node);
        Object value = j.evaluate(processed);

        return Utility.twoDecimals(String.valueOf(value));
      } catch (Exception localException1) {
      }
    } catch (Exception e) {
      return "0";
    }
    return "0";
  }

  public void getRequsitionDetails(OfferDetails offerDetails, String code) {
    try {
      String query = "SELECT REQS_CODE,REQS_NAME,RANK_NAME,DIV_NAME,CENTER_NAME,DEPT_NAME, REQS_JOB_DESCRIPTION,REQS_ROLE_RESPON,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME from HRMS_REC_REQS_HDR LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION  LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION  LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT  LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH LEFT JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER WHERE REQS_CODE= " + 
        code;
      Object[][] data = getSqlModel().getSingleResult(query);
      if ((data != null) && (data.length > 0)) {
        offerDetails.setRequisitionCode(
          String.valueOf(data[0][0]).trim());
        offerDetails.setRequisitionName(
          String.valueOf(data[0][1]).trim());
        offerDetails.setPosition(String.valueOf(data[0][2]).trim());
        offerDetails.setDivision(String.valueOf(data[0][3]).trim());
        offerDetails.setBranch(String.valueOf(data[0][4]).trim());
        offerDetails.setDepartment(String.valueOf(data[0][5]).trim());
        offerDetails.setJobDescription(
          String.valueOf(data[0][6]).trim());
        offerDetails.setRolesResponsibility(
          String.valueOf(data[0][7]).trim());
        offerDetails.setHiringMgr(String.valueOf(data[0][8]).trim());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getTotalAmt(String candCode, String reqCode, String operationType, boolean esicFlag)
  {
    String totalAmt = "0";
    try {
      String totalCreditQry = " SELECT ROUND(NVL(SUM(OFFER_CREDIT_AMOUNT),0)) FROM HRMS_REC_OFFER_SALARY  LEFT JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=OFFER_CREDIT_CODE) WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE= " + 
        candCode + 
        " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE=" + 
        reqCode + " AND CREDIT_PERIODICITY='M'";
      Object[][] totCredit = getSqlModel()
        .getSingleResult(totalCreditQry);
      String totalDebitQry = "";
      if (esicFlag)
        totalDebitQry = "SELECT DISTINCT ROUND(OFFER_CREDIT_AMOUNT*.12) + ROUND(CASE WHEN SUM(OFFER_CREDIT_AMOUNT) <10000 THEN ((OFFER_CREDIT_AMOUNT/0.40)*0.0175) ELSE 0 END),  (ROUND(OFFER_CREDIT_AMOUNT*.12) + ROUND(CASE WHEN SUM(OFFER_CREDIT_AMOUNT) <10000 THEN ((OFFER_CREDIT_AMOUNT/0.40)*0.0475) ELSE 0 END)) FROM HRMS_REC_OFFER_SALARY  WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE=" + 
          candCode + 
          " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE=" + 
          reqCode + 
          " AND OFFER_CREDIT_CODE=1 GROUP BY OFFER_CREDIT_AMOUNT";
      else {
        totalDebitQry = "SELECT DISTINCT ROUND(OFFER_CREDIT_AMOUNT*.12)   FROM HRMS_REC_OFFER_SALARY  WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE=" + 
          candCode + 
          " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE=" + 
          reqCode + 
          " AND OFFER_CREDIT_CODE=1 GROUP BY OFFER_CREDIT_AMOUNT";
      }
      Object[][] totDebit = getSqlModel().getSingleResult(totalDebitQry);
      if (operationType.equals("takeHome")) {
        if ((totDebit != null) && (totDebit.length > 0))
          totalAmt = String.valueOf(Double.parseDouble(
            String.valueOf(totCredit[0][0])) - 
            Double.parseDouble(String.valueOf(totDebit[0][0])));
        else
          totalAmt = String.valueOf(Double.parseDouble(String.valueOf(totCredit[0][0])));
      }
      else
      {
        if (esicFlag) {
          if ((totDebit != null) && (totDebit.length > 0))
            totalAmt = String.valueOf(Double.parseDouble(String.valueOf(totCredit[0][0])) + 
              Double.parseDouble(String.valueOf(totDebit[0][1])));
          else {
            totalAmt = String.valueOf(Double.parseDouble(String.valueOf(totCredit[0][0])));
          }
        }

        if (operationType.equals("ctcPerYear"))
          totalAmt = String.valueOf(Math.round(
            Double.parseDouble(totalAmt) * 12.0D));
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return totalAmt;
  }

  public void displayIteratorValueForKeepInformed(String[] srNo, String[] empCode, String[] empName, OfferDetails offerDetails)
  {
    try
    {
      ArrayList list = new ArrayList();
      if (srNo != null) {
        for (int i = 0; i < srNo.length; i++) {
          OfferDetails bean = new OfferDetails();
          bean.setKeepInformedEmpId(empCode[i]);
          bean.setKeepInformedEmpName(empName[i]);
          bean.setSerialNo(srNo[i]);
          list.add(bean);
        }
        offerDetails.setKeepInformedList(list);
      }
    }
    catch (Exception e) {
      logger
        .error("Exception in displayIteratorValueForKeepInformed----------" + 
        e);
    }
  }

  public void setKeepInformed(String[] srNo, String[] empCode, String[] empName, OfferDetails offerDetails)
  {
    try {
      OfferDetails bean = new OfferDetails();
      bean.setKeepInformedEmpId(offerDetails.getEmployeeId());
      bean.setKeepInformedEmpName(offerDetails.getEmployeeName());
      ArrayList list = displayNewValueForKeepInformed(srNo, 
        empCode, empName, offerDetails);
      offerDetails.setSerialNo(String.valueOf(list.size() + 1));
      list.add(bean);
      offerDetails.setKeepInformedList(list);
    } catch (Exception e) {
      logger.error("Exception in setKeepInformed----------" + e);
    }
  }

  private ArrayList<OfferDetails> displayNewValueForKeepInformed(String[] srNo, String[] empCode, String[] empName, OfferDetails offerDetails)
  {
    ArrayList list = null;
    try {
      list = new ArrayList();
      if (srNo != null)
        for (int i = 0; i < srNo.length; i++) {
          OfferDetails bean = new OfferDetails();
          bean.setKeepInformedEmpId(empCode[i]);
          bean.setKeepInformedEmpName(empName[i]);
          bean.setSerialNo(srNo[i]);
          list.add(bean);
        }
    }
    catch (Exception e) {
      logger
        .error("Exception in displayNewValueForKeepInformed----------" + 
        e);
    }
    return list;
  }

  public void removeKeepInformedData(String[] serialNo, String[] empCode, String[] empName, OfferDetails offerDetails)
  {
    try {
      ArrayList tableList = new ArrayList();
      if (serialNo != null) {
        for (int i = 0; i < serialNo.length; i++) {
          OfferDetails bean = new OfferDetails();
          bean.setSrNo(String.valueOf(i + 1));
          bean.setKeepInformedEmpId(empCode[i]);
          bean.setKeepInformedEmpName(empName[i]);
          tableList.add(bean);
        }

        tableList.remove(
          Integer.parseInt(offerDetails.getCheckRemove()) - 1);
      }

      offerDetails.setKeepInformedList(tableList);
    } catch (Exception e) {
      logger.error("Exception in removeKeepInformedData------" + e);
    }
  }

  public void getKeepInformedSavedRecord(OfferDetails offerDetails)
  {
    try {
      String selectQuery = " SELECT OFFER_KEEP_INFORMED FROM  HRMS_REC_OFFER  WHERE OFFER_CODE=" + 
        offerDetails.getOfferCode();

      Object[][] selectDataObj = getSqlModel().getSingleResult(
        selectQuery);
      String str = "";
      String query = "";
      if ((selectDataObj != null) && (selectDataObj.length > 0)) {
        str = String.valueOf(selectDataObj[0][0]);

        if (str.length() > 0) {
          query = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID  FROM HRMS_EMP_OFFC   WHERE  EMP_ID IN(" + 
            str + ")";
        }
        Object[][] result = getSqlModel().getSingleResult(query);

        ArrayList leaveList = new ArrayList();
        if (result != null)
        {
          for (int i = 0; i < result.length; i++) {
            OfferDetails bean = new OfferDetails();
            bean.setKeepInformedEmpId(String.valueOf(result[i][1]));
            bean.setKeepInformedEmpName(
              String.valueOf(result[i][0]));
            bean.setSerialNo(String.valueOf(i + 1));
            leaveList.add(bean);
          }
          offerDetails.setKeepInformedList(leaveList);
        }
      }
    } catch (Exception e) {
      logger.error("Exception in getKeepInformedSavedRecord----------" + 
        e);
    }
  }

  public String onlineAcceptRejectOffer(HttpServletRequest request, String candCode, String offerCode, String status, String requisitionCode, String remarks)
  {
    String result = "";
    String res = "";
    try {
      System.out.println("candCode : " + candCode);
      System.out.println("offerCode : " + offerCode);
      System.out.println("Status : " + status);
      System.out.println("remarks : " + remarks);

      System.out.println("requisitionCode >>" + requisitionCode);

      String query = " SELECT OFFER_CAND_CODE, trim(OFFER_STATUS) FROM HRMS_REC_OFFER WHERE OFFER_CODE=" + 
        offerCode;
      Object[][] candidateIdObj = getSqlModel().getSingleResult(query);
      if ((candidateIdObj != null) && (candidateIdObj.length > 0))
        if ((String.valueOf(candidateIdObj[0][0]).equals(candCode)) && 
          (String.valueOf(candidateIdObj[0][1]).equals("I")))
        {
          res = updateOfferStatus(request, offerCode, status, remarks, requisitionCode);
          if (res.equals("accepted")) {
            result = "Offer accepted successfully.";
            sendMailToRecruiterRegardingOfferStatus(request, candCode, offerCode);
          } else if (res.equals("rejected")) {
            result = "Offer rejected successfully.";
            sendMailToRecruiterRegardingOfferStatus(request, candCode, offerCode);
          } else {
            result = "Error Occured.";
          }
        } else {
          result = "Offer request has already been processed.";
        }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return result;
  }

  private void sendMailToRecruiterRegardingOfferStatus(HttpServletRequest request, String candCode, String offerCode)
  {
    try {
      String recruitHeadQuery = "SELECT CASE WHEN TRIM(HRMS_REC_RESUME_BANK.RESUME_REC_EMPID) IS  NULL  THEN TRIM(HRMS_REC_RESUME_BANK.RESUME_APPR_EMPID)  ELSE  TRIM(HRMS_REC_RESUME_BANK.RESUME_REC_EMPID) END AS RECRUITER  FROM HRMS_REC_RESUME_BANK  WHERE RESUME_REQS_CODE = (SELECT OFFER_REQS_CODE FROM HRMS_REC_OFFER WHERE OFFER_CODE = " + 
        offerCode + " ) AND RESUME_CAND_CODE = " + candCode;
      Object[][] recruitHeadObj = getSqlModel().getSingleResult(
        recruitHeadQuery);
      if ((recruitHeadObj != null) && (recruitHeadObj.length > 0)) {
        EmailTemplateBody template = new EmailTemplateBody();
        template.initiate(this.context, this.session);
        template
          .setEmailTemplate("RMS-OFFER MAIL TO RECRUITER REGARDING OFFER STATUS");
        template.getTemplateQueries();

        EmailTemplateQuery templateQuery1 = template
          .getTemplateQuery(1);
        templateQuery1.setParameter(1, candCode);

        EmailTemplateQuery templateQuery2 = template
          .getTemplateQuery(2);
        templateQuery2.setParameter(1, 
          checkNull(String.valueOf(recruitHeadObj[0][0])));

        EmailTemplateQuery templateQuery3 = template
          .getTemplateQuery(3);
        templateQuery3.setParameter(1, offerCode);

        EmailTemplateQuery templateQuery4 = template
          .getTemplateQuery(4);
        templateQuery4.setParameter(1, offerCode);

        EmailTemplateQuery templateQuery5 = template
          .getTemplateQuery(5);
        templateQuery5.setParameter(1, offerCode);

        EmailTemplateQuery templateQuery6 = template
          .getTemplateQuery(6);
        templateQuery6.setParameter(1, offerCode);
        templateQuery6.setParameter(2, candCode);

        template.configMailAlert();
        template.sendApplicationMail();
        template.clearParameters();
        template.terminate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String updateOfferStatus(HttpServletRequest request, String offerCode, String status, String remarks, String requisitionCode)
  {
    String stringToReturn = "";
    boolean result = false;
    try {
      String getOfferDetailsQuery = "SELECT OFFER_CODE,OFFER_STATUS FROM HRMS_REC_OFFER WHERE OFFER_CODE=" + offerCode;
      Object[][] getOfferDetailsObj = getSqlModel().getSingleResult(getOfferDetailsQuery);
      if ((getOfferDetailsObj != null) && (getOfferDetailsObj.length > 0)) {
        if (status.trim().equals("OA"))
          updateVacancyStatistics(requisitionCode, "OfferAccepted", offerCode);
        else {
          updateVacancyStatistics(requisitionCode, "OfferRejectCancel", offerCode);
        }
      }

      String updateQuery = "UPDATE HRMS_REC_OFFER SET OFFER_STATUS = '" + status + "' " + 
        ",OFFER_ACCEPT_DATE = SYSDATE, OFFER_CAND_COMMENT = '" + remarks + "' WHERE OFFER_CODE = " + offerCode;
      result = getSqlModel().singleExecute(updateQuery);
      if (result)
        if (status.equals("OA")) {
          insertAppointmentDetailsFromOnline(offerCode);
          stringToReturn = "accepted";
        } else {
          stringToReturn = "rejected";
        }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return stringToReturn;
  }
}