package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.PersonalRequisition;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

public class PersonalRequisitionModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PersonalRequisitionModel.class);

	public void getPendingList(PersonalRequisition bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] draftListData = null;
			ArrayList draftvoucherList = new ArrayList();

			Object[][] inProcessListData = null;
			ArrayList inProcessVoucherList = new ArrayList();

			Object[][] sentBackListData = null;
			ArrayList sentBackVoucherList = new ArrayList();

			// For drafted application Begins
			String selQuery = " SELECT TRACKING_NUMBER, POSITION_TITLE ,REQUISITION , "
					+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
					+ " WHERE PERS_REQ_STATUS = 'D' AND PERS_INITIATOR =" + userId+" ORDER BY PERSONAL_REQ_ID DESC ";
			draftListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(),
					draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				bean.setMyPage("1");

			if (draftListData != null && draftListData.length > 0) {
				bean.setDraftVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					PersonalRequisition beanItt = new PersonalRequisition();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(draftListData[i][0])));
					
					beanItt.setPositionTitle(checkNull(String
							.valueOf(draftListData[i][1])));
					beanItt.setRequisition(checkNull(String
							.valueOf(draftListData[i][2])));
					beanItt.setApplicationDate(checkNull(String
							.valueOf(draftListData[i][3])));

					beanItt.setPersonalRequisitionID(checkNull(String
							.valueOf(draftListData[i][5])));
					beanItt.setPositionRequiredDateItr(checkNull(String
							.valueOf(draftListData[i][6])));
					draftvoucherList.add(beanItt);
				}
				bean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends

			// For in-Process application Begins
			String inProcessQuery = "  SELECT TRACKING_NUMBER, POSITION_TITLE ,REQUISITION ,  "
					+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
					+ " WHERE PERS_REQ_STATUS IN ('P','F') AND PERS_INITIATOR =" +userId +" ORDER BY PERSONAL_REQ_ID DESC ";
			inProcessListData = getSqlModel().getSingleResult(inProcessQuery);

			String[] pageIndexInProcess = Utility.doPaging(bean
					.getMyPageInProcess(), inProcessListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = "1";
				pageIndexInProcess[3] = "1";
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String
					.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String
					.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals("1"))
				bean.setMyPageInProcess("1");

			if (inProcessListData != null && inProcessListData.length > 0) {
				bean.setInProcessVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer
						.parseInt(pageIndexInProcess[1]); i++) {
					PersonalRequisition beanItt = new PersonalRequisition();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(inProcessListData[i][0])));
					
					beanItt.setPositionTitle(checkNull(String
							.valueOf(inProcessListData[i][1])));
					beanItt.setRequisition(checkNull(String
							.valueOf(inProcessListData[i][2])));
					
					beanItt.setApplicationDate(checkNull(String
							.valueOf(inProcessListData[i][3])));

					beanItt.setPersonalRequisitionID(checkNull(String
							.valueOf(inProcessListData[i][5])));
					beanItt.setPositionRequiredDateItr(checkNull(String
							.valueOf(inProcessListData[i][6])));
					inProcessVoucherList.add(beanItt);
				}
				bean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends

			// Sent-Back application Begins
			String sentBackQuery = "  SELECT TRACKING_NUMBER, POSITION_TITLE ,REQUISITION , "
					+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
					+ " WHERE PERS_REQ_STATUS = 'B' AND PERS_INITIATOR =" +userId +"  ORDER BY PERSONAL_REQ_ID DESC ";
			sentBackListData = getSqlModel().getSingleResult(sentBackQuery);

			String[] pageIndexSentBack = Utility.doPaging(bean
					.getMyPageSentBack(), sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = "0";
				pageIndexSentBack[1] = "20";
				pageIndexSentBack[2] = "1";
				pageIndexSentBack[3] = "1";
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String
					.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String
					.valueOf(pageIndexSentBack[3])));
			if (pageIndexSentBack[4].equals("1"))
				bean.setMyPageSentBack("1");

			if (sentBackListData != null && sentBackListData.length > 0) {
				bean.setSentBackVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer
						.parseInt(pageIndexSentBack[1]); i++) {
					PersonalRequisition beanItt = new PersonalRequisition();
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(sentBackListData[i][0])));
					beanItt.setPositionTitle(checkNull(String
							.valueOf(sentBackListData[i][1])));
					beanItt.setRequisition(checkNull(String
							.valueOf(sentBackListData[i][2])));
				
					beanItt.setApplicationDate(checkNull(String
							.valueOf(sentBackListData[i][3])));

					beanItt.setPersonalRequisitionID(checkNull(String
							.valueOf(sentBackListData[i][5])));
					beanItt.setPositionRequiredDateItr(checkNull(String
							.valueOf(sentBackListData[i][6])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getApproverCommentList(PersonalRequisition bean,
			String requestID) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, PERS_COMMENTS, "
				+ " TO_CHAR(PERS_APPROVED_DATE, 'DD-MM-YYYY') AS PERS_APPROVED_DATE, "
				+ " DECODE(PERS_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Authorized SignOff') AS STATUS "
				+ " FROM HRMS_D1_PERSREQ_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_PERSREQ_PATH.PERS_APPROVER_CODE) "
				+ " WHERE PERS_REQ_ID = "
				+ requestID
				+ " ORDER BY PERS_REQ_ID DESC";

		Object[][] apprCommentListObj = getSqlModel().getSingleResult(
				apprCommentListSql);
		ArrayList approverList = new ArrayList<Object>(
				apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			bean.setApproverCommentsFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				PersonalRequisition innerBean = new PersonalRequisition();
				innerBean.setApprName(checkNull(String.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(checkNull(String
						.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(checkNull(String.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(checkNull(String
						.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}

	public void getSystemDate(PersonalRequisition reqbean) {
		try {
			String systemDateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] systemDateObject = getSqlModel().getSingleResult(
					systemDateQuery);
			if (systemDateObject != null && systemDateObject.length > 0) {
				// reqbean.setApplicationDate(checkNull(String.valueOf(systemDateObject[0][0])));
			}

			String fromNameQuery = "SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ reqbean.getUserEmpId();
			Object[][] fromNameObject = getSqlModel().getSingleResult(
					fromNameQuery);
			if (fromNameObject != null && fromNameObject.length > 0) {
				/*
				 * reqbean.setEmployeeID(checkNull(String.valueOf(fromNameObject[0][0])));
				 * reqbean.setEmployeeToken(checkNull(String.valueOf(fromNameObject[0][1])));
				 * reqbean.setFromName(checkNull(String.valueOf(fromNameObject[0][2])));
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean draftFunction(PersonalRequisition reqbean) {
		boolean result = false;
		System.out.println("Status : " + reqbean.getPersReqStatus());
		try {
			Object addObj[][] = new Object[1][45];
			addObj[0][0] = reqbean.getPositionTitleName();
			addObj[0][1] = reqbean.getRequisition();
			addObj[0][2] = reqbean.getBandId();
			addObj[0][3] = reqbean.getMaxSalary();
			addObj[0][4] = reqbean.getPositionDate();
			addObj[0][5] = reqbean.getDeptCode();
			addObj[0][6] = reqbean.getExecutiveName();
			addObj[0][7] = reqbean.getWorkLocation();
			addObj[0][8] = reqbean.getHiringManagerCode();

			addObj[0][9] = reqbean.getApprovedExistingJob();

			addObj[0][10] = reqbean.getNewJobExist();
			addObj[0][11] = reqbean.getHeadCount();
			addObj[0][12] = reqbean.getReplacementType();
			addObj[0][13] = reqbean.getReqReplacingCode();
			addObj[0][14] = reqbean.getTerminationDate();

			if (reqbean.getPositionType().equals("Re")) {
				addObj[0][15] = "R";
			} else if (reqbean.getPositionType().equals("Te")) {
				addObj[0][15] = "T";
			} else if (reqbean.getPositionType().equals("Va")) {
				addObj[0][15] = "V";
			}

			else {
				addObj[0][15] = "";
			}

			addObj[0][16] = reqbean.getTemporaryType();
			addObj[0][17] = reqbean.getAgencyName();
			addObj[0][18] = reqbean.getContractorName();
			addObj[0][19] = reqbean.getContractorPhoneNumber();

			addObj[0][20] = reqbean.getContractorEmailAddress();
			addObj[0][21] = reqbean.getMaximumBillRate();
			addObj[0][22] = reqbean.getOvertimeRequired();
			addObj[0][23] = reqbean.getNumberOfOvertime();
			addObj[0][24] = reqbean.getDurationOfAssignment();
			addObj[0][25] = reqbean.getReasonForTemporaryNeed();
			addObj[0][26] = reqbean.getVariableWorkfroceRate();

			System.out.println("reqbean.getRateType()========"
					+ reqbean.getRateType());
			if (reqbean.getRateType().equals("Pc")) {
				addObj[0][27] = "Pc";
			} else if (reqbean.getRateType().equals("Pp")) {
				addObj[0][27] = "Pp";
			} else if (reqbean.getRateType().equals("Ph")) {
				addObj[0][27] = "Ph";
			}

			else {
				addObj[0][27] = "";
			}

			addObj[0][28] = reqbean.getDurationOfVariableAssignment();
			addObj[0][29] = reqbean.getReasonForVariableWorkforceNeed();
			addObj[0][30] = reqbean.getBudget();
			addObj[0][31] = reqbean.getStdHourPerWeek();
			addObj[0][32] = reqbean.getFulltimeParttime();
			addObj[0][33] = reqbean.getEducationRequirements();
			addObj[0][34] = reqbean.getExperienceRequirement();
			addObj[0][35] = reqbean.getEssentialPositionRequirements();
			addObj[0][36] = reqbean.getReason();

			addObj[0][37] = reqbean.getApproverCode();
			addObj[0][38] = reqbean.getForwardedForApprovar();

			addObj[0][39] = reqbean.getPersReqStatus();
			
			String autoCode = "";
			String selQuery = "SELECT NVL(MAX(PERSONAL_REQ_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(PERSONAL_REQ_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_PERSONAL_REQUISITION	";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			if (reqbean.getRequestID().equals("")) {			
				autoCode = String.valueOf(selData[0][0]);
				System.out.println("autoCode==="+ autoCode);
				reqbean.setRequestID(autoCode);			
				/**
					 * SET TRACKING NO
					 */String qq="SELECT NVL(MAX(PERSONAL_REQ_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(PERSONAL_REQ_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_PERSONAL_REQUISITION	";
						Object[][]obj=getSqlModel().getSingleResult(qq);
						if(obj !=null && obj.length>0){			
						try {
							ApplCodeTemplateModel model = new ApplCodeTemplateModel();
							model.initiate(context, session);
							String applnCode = model.generateApplicationCode(String.valueOf(obj[0][1]), "D1-PERSONAL", reqbean.getHiringManagerCode(),String.valueOf(obj[0][2]));
							reqbean.setTrackingNo(checkNull(applnCode));
							System.out.println("applnCode%%%%%" + applnCode);
							model.terminate();
						} catch (Exception e) {
							// TODO: handle exception
						}
						}			
			}
			
			addObj[0][40] = reqbean.getTrackingNo();
			System.out.println("reqbean.getTrackingNo()%%%%%%%%%%%%%" + reqbean.getTrackingNo());
			addObj[0][41] = reqbean.getInitiatorCode();
			addObj[0][42] = reqbean.getHiringManagerPhoneNumber();
			addObj[0][43] = reqbean.getHiringManagerFaxNumber();
			addObj[0][44] = reqbean.getHiringManagerEmailAddress();
			
			String insertQuery = "INSERT INTO HRMS_D1_PERSONAL_REQUISITION"
					+ "(PERSONAL_REQ_ID, POSITION_TITLE, REQUISITION, BAND_CODE, MAX_SALARY, POSITION_REQ_DATE , "
					+ " DEPT_CODE, EXECUTIVE_CODE, WORK_LOCATION, HIRING_MGNR_ID, IS_JOB_EXIST, IS_JOB_DESC_EXIST, IS_HEAD_COUNT "
					+ " ,IS_REPLACEMENT , REQ_REPLACING, TERMINATION_DATE ,  "
					+ " POSITION_TYPE, TEMPORARY_TYPE, AGENCY_NAME, CONTR_NAME, CONTR_PHONE_NUMBER, CONTR_EMAIL_ID, "
					+ "MAX_BILL_RATE, IS_OT_REQ, NO_OT_PER_WEEK, ASSIGN_DURATION, REASON_FOR_TEMP, VARIABLE_RATE, "
					+ "RATE_TYPE, VAR_ASIGN_DURATION, REASON_FOR_VARIABLE, IS_BUDGET, STD_HOUR_WEEK, FULL_PART_TIME, "
					+ "EDUCATION_REQ, EXPER_REQ, NO_POSITION, JUSTIFICATION, NEXT_APPR_CODE, FORWARDED_TO, PERS_REQ_STATUS ,TRACKING_NUMBER,PERS_INITIATOR, PERS_INITIATOR_DATE,HIRING_MGNR_PHONE_NUMBER, HIRING_MGNR_FAX_NUMBER, HIRING_MGNR_EMAIL_ID)"

					+ " VALUES((SELECT NVL(MAX(PERSONAL_REQ_ID),0)+1 FROM HRMS_D1_PERSONAL_REQUISITION),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?)";
			/* ,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; */

			result = getSqlModel().singleExecute(insertQuery, addObj);
			for (int i = 0; i < addObj.length; i++) {
				for (int j = 0; j < addObj[i].length; j++) {
					logger.info("insertObj[" + i + "][" + j + "]  "
							+ addObj[i][j]);
				}
			}

			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(PERSONAL_REQ_ID),0) FROM HRMS_D1_PERSONAL_REQUISITION ";
				Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					reqbean.setRequestID(String.valueOf(data[0][0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateRecords(PersonalRequisition reqbean) {

		boolean result = false;
		System.out.println("Status : " + reqbean.getPersReqStatus());
		try {
			Object updateObj[][] = new Object[1][43];
			updateObj[0][0] = reqbean.getPositionTitleName();
			updateObj[0][1] = reqbean.getRequisition();
			updateObj[0][2] = reqbean.getBandId();
			updateObj[0][3] = reqbean.getMaxSalary();
			updateObj[0][4] = reqbean.getPositionDate();
			updateObj[0][5] = reqbean.getDeptCode();
			updateObj[0][6] = reqbean.getExecutiveName();
			updateObj[0][7] = reqbean.getWorkLocation();
			updateObj[0][8] = reqbean.getHiringManagerCode();

			updateObj[0][9] = reqbean.getApprovedExistingJob();

			updateObj[0][10] = reqbean.getNewJobExist();
			updateObj[0][11] = reqbean.getHeadCount();
			updateObj[0][12] = reqbean.getReplacementType();
			updateObj[0][13] = reqbean.getReqReplacingCode();
			updateObj[0][14] = reqbean.getTerminationDate();

			System.out
					.println("reqbean.getUserProfileRadioOptionValue()========"
							+ reqbean.getPositionType());
			if (reqbean.getPositionType().equals("Re")) {
				updateObj[0][15] = "R";
			} else if (reqbean.getPositionType().equals("Te")) {
				updateObj[0][15] = "T";
			} else if (reqbean.getPositionType().equals("Va")) {
				updateObj[0][15] = "V";
			}

			else {
				updateObj[0][15] = "";
			}

			updateObj[0][16] = reqbean.getTemporaryType();
			updateObj[0][17] = reqbean.getAgencyName();
			updateObj[0][18] = reqbean.getContractorName();
			updateObj[0][19] = reqbean.getContractorPhoneNumber();

			updateObj[0][20] = reqbean.getContractorEmailAddress();
			updateObj[0][21] = reqbean.getMaximumBillRate();
			updateObj[0][22] = reqbean.getOvertimeRequired();
			updateObj[0][23] = reqbean.getNumberOfOvertime();
			updateObj[0][24] = reqbean.getDurationOfAssignment();
			updateObj[0][25] = reqbean.getReasonForTemporaryNeed();
			updateObj[0][26] = reqbean.getVariableWorkfroceRate();

			System.out.println("reqbean.getRateType()========"
					+ reqbean.getRateType());
			if (reqbean.getRateType().equals("Pc")) {
				updateObj[0][27] = "Pc";
			} else if (reqbean.getRateType().equals("Pp")) {
				updateObj[0][27] = "Pp";
			} else if (reqbean.getRateType().equals("Ph")) {
				updateObj[0][27] = "Ph";
			}

			else {
				updateObj[0][27] = "";
			}
			updateObj[0][28] = reqbean.getDurationOfVariableAssignment();
			updateObj[0][29] = reqbean.getReasonForVariableWorkforceNeed();
			updateObj[0][30] = reqbean.getBudget();
			updateObj[0][31] = reqbean.getStdHourPerWeek();
			updateObj[0][32] = reqbean.getFulltimeParttime();
			updateObj[0][33] = reqbean.getEducationRequirements();
			updateObj[0][34] = reqbean.getExperienceRequirement();
			updateObj[0][35] = reqbean.getEssentialPositionRequirements();
			updateObj[0][36] = reqbean.getReason();

			updateObj[0][37] = reqbean.getApproverCode();
			updateObj[0][38] = reqbean.getForwardedForApprovar();

			updateObj[0][39] = reqbean.getPersReqStatus();
			//updateObj[0][40] = reqbean.getRequestID();
			updateObj[0][40] = reqbean.getHiringManagerPhoneNumber();
			updateObj[0][41] = reqbean.getHiringManagerFaxNumber();
			updateObj[0][42] = reqbean.getHiringManagerEmailAddress();

			String insertQuery = "UPDATE HRMS_D1_PERSONAL_REQUISITION SET "
					+ "  POSITION_TITLE = ? , REQUISITION = ? , BAND_CODE = ? , MAX_SALARY = ? , POSITION_REQ_DATE  = to_date(?,'DD-MM-YYYY') , "
					+ " DEPT_CODE = ? , EXECUTIVE_CODE = ? , WORK_LOCATION = ? , HIRING_MGNR_ID = ? , IS_JOB_EXIST = ? , IS_JOB_DESC_EXIST = ? , IS_HEAD_COUNT = ?  "
					+ " ,IS_REPLACEMENT = ?  , REQ_REPLACING = ? , TERMINATION_DATE  = to_date(?,'DD-MM-YYYY') ,  "
					+ " POSITION_TYPE = ? , TEMPORARY_TYPE = ? , AGENCY_NAME = ? , CONTR_NAME = ? , CONTR_PHONE_NUMBER = ? , CONTR_EMAIL_ID = ? , "
					+ "MAX_BILL_RATE = ? , IS_OT_REQ = ? , NO_OT_PER_WEEK = ? , ASSIGN_DURATION = ? , REASON_FOR_TEMP = ? , VARIABLE_RATE = ? , "
					+ "RATE_TYPE = ? , VAR_ASIGN_DURATION = ? , REASON_FOR_VARIABLE = ? , IS_BUDGET = ? , STD_HOUR_WEEK = ? , FULL_PART_TIME = ? , "
					+ "EDUCATION_REQ = ? , EXPER_REQ = ? , NO_POSITION = ? , JUSTIFICATION = ? , NEXT_APPR_CODE = ? , FORWARDED_TO = ? , PERS_REQ_STATUS = ? ,HIRING_MGNR_PHONE_NUMBER = ? , HIRING_MGNR_FAX_NUMBER = ? , HIRING_MGNR_EMAIL_ID = ?    WHERE PERSONAL_REQ_ID= " + reqbean.getRequestID() ;

			result = getSqlModel().singleExecute(insertQuery, updateObj);

			/*for (int i = 0; i < updateObj.length; i++) {
				for (int j = 0; j < updateObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  "
							+ updateObj[i][j]);
				}
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void editApplicationFunction(PersonalRequisition reqbean,
			String requestID) {
		try {
			String editQuery = "SELECT PERSONAL_REQ_ID, POSITION_TITLE, REQUISITION, BAND_CODE,CADRE_NAME, "
					+ " MAX_SALARY, TO_CHAR(POSITION_REQ_DATE,'DD-MM-YYYY'), DEPT_CODE, EXECUTIVE_CODE, WORK_LOCATION, "
					+ " 	HIRING_MGNR_ID,OFFC.EMP_TOKEN AS HR_TOKEN,"
					+ "	OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS HR_NAME, "
					+ "	HIRING_MGNR_PHONE_NUMBER,HIRING_MGNR_FAX_NUMBER, HIRING_MGNR_EMAIL_ID, IS_JOB_EXIST, IS_JOB_DESC_EXIST,"
					+ "	IS_HEAD_COUNT, IS_REPLACEMENT,REQ_REPLACING,OFFC2.EMP_TOKEN AS REPLACING_TOKEN,OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS REPLACING_NAME, TO_CHAR(TERMINATION_DATE,'DD-MM-YYYY'), "
					+ " 	POSITION_TYPE, TEMPORARY_TYPE, AGENCY_NAME, CONTR_NAME, CONTR_PHONE_NUMBER, CONTR_EMAIL_ID, "
					+ " 	MAX_BILL_RATE, IS_OT_REQ, NO_OT_PER_WEEK, ASSIGN_DURATION, REASON_FOR_TEMP, VARIABLE_RATE, "
					+ " 	RATE_TYPE, VAR_ASIGN_DURATION, REASON_FOR_VARIABLE, IS_BUDGET, STD_HOUR_WEEK, FULL_PART_TIME, "
					+ " 	EDUCATION_REQ, EXPER_REQ, NO_POSITION, JUSTIFICATION, NEXT_APPR_CODE,OFFC1.EMP_TOKEN AS APPROVER_TOKEN, "
					+ " 	OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,"
					+ " 	FORWARDED_TO, PERS_REQ_STATUS, TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY'),TRACKING_NUMBER "
					+ " 	, PERS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(PERS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') , NVL(HRMS_DEPT.DEPT_NAME, ' ')  FROM HRMS_D1_PERSONAL_REQUISITION  "
					+ " 	LEFT JOIN HRMS_CADRE ON ( HRMS_CADRE.CADRE_ID = HRMS_D1_PERSONAL_REQUISITION.BAND_CODE) "
				//	+ " 	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_D1_PERSONAL_REQUISITION.EXECUTIVE_CODE)"
					+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.NEXT_APPR_CODE)"
					+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.REQ_REPLACING)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.HIRING_MGNR_ID) "
					+ "	LEFT JOIN HRMS_EMP_ADDRESS ON (OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID)"
					+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.PERS_INITIATOR)  "
					+" LEFT JOIN HRMS_DEPT  ON(HRMS_DEPT.DEPT_ID=HRMS_D1_PERSONAL_REQUISITION.DEPT_CODE )"
					+ "  WHERE PERSONAL_REQ_ID = " + requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				reqbean.setRequestID(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setPositionTitleName(checkNull(String
						.valueOf(editObj[0][1])));
				reqbean
						.setRequisition(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setBandId(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setBandName(checkNull(String.valueOf(editObj[0][4])));
				reqbean.setMaxSalary(checkNull(String.valueOf(editObj[0][5])));
				reqbean
						.setPositionDate(checkNull(String
								.valueOf(editObj[0][6])));
				reqbean.setDeptCode(checkNull(String.valueOf(editObj[0][7])));
				reqbean.setExecutiveName(checkNull(String
						.valueOf(editObj[0][8])));

				/*reqbean.setExecutiveName(checkNull(String
						.valueOf(editObj[0][9])));*/
				reqbean.setWorkLocation(checkNull(String
						.valueOf(editObj[0][9])));
				reqbean.setHiringManagerCode(checkNull(String
						.valueOf(editObj[0][10])));

				reqbean.setHiringManagerToken(checkNull(String
						.valueOf(editObj[0][11])));
				reqbean.setHiringManagerName(checkNull(String
						.valueOf(editObj[0][12])));
				reqbean.setHiringManagerPhoneNumber(checkNull(String
						.valueOf(editObj[0][13])));

				reqbean.setHiringManagerFaxNumber(checkNull(String
						.valueOf(editObj[0][14])));

				reqbean.setHiringManagerEmailAddress(checkNull(String
						.valueOf(editObj[0][15])));

				reqbean.setApprovedExistingJob(checkNull(String
						.valueOf(editObj[0][16])));
				reqbean
						.setNewJobExist(checkNull(String
								.valueOf(editObj[0][17])));
				reqbean.setHeadCount(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setReplacementType(checkNull(String
						.valueOf(editObj[0][19])));
				reqbean.setReqReplacingCode(checkNull(String
						.valueOf(editObj[0][20])));
				
				reqbean.setReqReplacingToken(checkNull(String
						.valueOf(editObj[0][21])));
				reqbean.setReqReplacingName(checkNull(String
						.valueOf(editObj[0][22])));
				
				
				reqbean.setTerminationDate(checkNull(String
						.valueOf(editObj[0][23])));
				// reqbean.setPositionType(checkNull(String.valueOf(editObj[0][18])));

				if (String.valueOf(editObj[0][24]).equals("R")) {
					reqbean.setPositionType("Re");
					reqbean.setUserProfileRadioOptionValue("Re");
				} else if (String.valueOf(editObj[0][24]).equals("T")) {
					reqbean.setPositionType("Te");
					reqbean.setUserProfileRadioOptionValue("Te");
				} else if (String.valueOf(editObj[0][24]).equals("V")) {
					reqbean.setPositionType("Va");
					reqbean.setUserProfileRadioOptionValue("Va");
				} else {
					reqbean.setPositionType("");
					reqbean.setUserProfileRadioOptionValue("");
				}

				reqbean.setTemporaryType(checkNull(String
						.valueOf(editObj[0][25])));
				reqbean
						.setAgencyName(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setContractorName(checkNull(String
						.valueOf(editObj[0][27])));
				reqbean.setContractorPhoneNumber(checkNull(String
						.valueOf(editObj[0][28])));
				reqbean.setContractorEmailAddress(checkNull(String
						.valueOf(editObj[0][29])));
				reqbean.setMaximumBillRate(checkNull(String
						.valueOf(editObj[0][30])));

				reqbean.setOvertimeRequired(checkNull(String
						.valueOf(editObj[0][31])));
				reqbean.setNumberOfOvertime(checkNull(String
						.valueOf(editObj[0][32])));
				reqbean.setDurationOfAssignment(checkNull(String
						.valueOf(editObj[0][33])));
				reqbean.setReasonForTemporaryNeed(checkNull(String
						.valueOf(editObj[0][34])));
				reqbean.setVariableWorkfroceRate(checkNull(String
						.valueOf(editObj[0][35])));
				// reqbean.setUserProfile(checkNull(String.valueOf(editObj[0][30])));

				if (String.valueOf(editObj[0][36]).equals("Pc")) {
					reqbean.setRateType("Pc");
					reqbean.setRateTypeRadioOptionValue("Pc");
				} else if (String.valueOf(editObj[0][36]).equals("Pp")) {
					reqbean.setRateType("Pp");
					reqbean.setRateTypeRadioOptionValue("Pp");
				} else if (String.valueOf(editObj[0][36]).equals("Ph")) {
					reqbean.setRateType("Ph");
					reqbean.setRateTypeRadioOptionValue("Ph");
				} else {
					reqbean.setRateType("");
					reqbean.setRateTypeRadioOptionValue("");
				}

				reqbean.setDurationOfVariableAssignment(checkNull(String
						.valueOf(editObj[0][37])));
				reqbean.setReasonForVariableWorkforceNeed(checkNull(String
						.valueOf(editObj[0][38])));
				reqbean.setBudget(checkNull(String.valueOf(editObj[0][39])));
				reqbean.setStdHourPerWeek(checkNull(String
						.valueOf(editObj[0][40])));
				reqbean.setFulltimeParttime(checkNull(String
						.valueOf(editObj[0][41])));
				reqbean.setEducationRequirements(checkNull(String
						.valueOf(editObj[0][42])));
				reqbean.setExperienceRequirement(checkNull(String
						.valueOf(editObj[0][43])));
				reqbean.setEssentialPositionRequirements(checkNull(String
						.valueOf(editObj[0][44])));
				reqbean.setReason(checkNull(String.valueOf(editObj[0][45])));
				reqbean.setApproverCode(checkNull(String
						.valueOf(editObj[0][46])));
				reqbean.setApproverToken(checkNull(String
						.valueOf(editObj[0][47])));
				reqbean.setApproverName(checkNull(String
						.valueOf(editObj[0][48])));
				reqbean.setForwardedForApprovar(checkNull(String
						.valueOf(editObj[0][49])));
				reqbean.setPersReqStatus(checkNull(String
						.valueOf(editObj[0][50])));

				reqbean.setApplicationDate(checkNull(String
						.valueOf(editObj[0][51])));
				reqbean.setTrackingNo(checkNull(String
						.valueOf(editObj[0][52])));
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][53])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][54])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][55])));
				reqbean.setDeptName(checkNull(String.valueOf(editObj[0][56])));
				
				/*reqbean.setHiringManagerPhoneNumber(checkNull(String.valueOf(editObj[0][58])));
				reqbean.setHiringManagerFaxNumber(checkNull(String.valueOf(editObj[0][59])));
				reqbean.setHiringManagerEmailAddress(checkNull(String.valueOf(editObj[0][60])));*/
				
				getApproverCommentList(reqbean, requestID);

				/*for (int i = 0; i < editObj.length; i++) {
					for (int j = 0; j < editObj[i].length; j++) {
						logger.info("editObj[" + i + "][" + j + "]  "
								+ editObj[i][j]);
					}
				}*/
				
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void viewApplicationFunction(PersonalRequisition reqbean,
			String requestID) {
		try {
			String editQuery = "SELECT PERSONAL_REQ_ID, POSITION_TITLE, REQUISITION, BAND_CODE,CADRE_NAME, "
					+ " MAX_SALARY, TO_CHAR(POSITION_REQ_DATE,'DD-MM-YYYY'), DEPT_CODE, EXECUTIVE_CODE, WORK_LOCATION, "
					+ " 	HIRING_MGNR_ID,OFFC.EMP_TOKEN AS HR_TOKEN,"
					+ "	OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS HR_NAME, "
					+ "	HIRING_MGNR_PHONE_NUMBER, HIRING_MGNR_FAX_NUMBER, HIRING_MGNR_EMAIL_ID, IS_JOB_EXIST, IS_JOB_DESC_EXIST,"
					+ "	IS_HEAD_COUNT, IS_REPLACEMENT,REQ_REPLACING,OFFC2.EMP_TOKEN AS REPLACING_TOKEN,OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS REPLACING_NAME, TO_CHAR(TERMINATION_DATE,'DD-MM-YYYY'), "
					+ " 	POSITION_TYPE, TEMPORARY_TYPE, AGENCY_NAME, CONTR_NAME, CONTR_PHONE_NUMBER, CONTR_EMAIL_ID, "
					+ " 	MAX_BILL_RATE, IS_OT_REQ, NO_OT_PER_WEEK, ASSIGN_DURATION, REASON_FOR_TEMP, VARIABLE_RATE, "
					+ " 	RATE_TYPE, VAR_ASIGN_DURATION, REASON_FOR_VARIABLE, IS_BUDGET, STD_HOUR_WEEK, FULL_PART_TIME, "
					+ " 	EDUCATION_REQ, EXPER_REQ, NO_POSITION, JUSTIFICATION, NEXT_APPR_CODE,OFFC1.EMP_TOKEN AS APPROVER_TOKEN, "
					+ " 	OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,"
					+ " 	FORWARDED_TO, PERS_REQ_STATUS, TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') "
					+ " 	,PERS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(PERS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI'), NVL(HRMS_DEPT.DEPT_NAME, ' ') FROM HRMS_D1_PERSONAL_REQUISITION  "
					+ " 	LEFT JOIN HRMS_CADRE ON ( HRMS_CADRE.CADRE_ID = HRMS_D1_PERSONAL_REQUISITION.BAND_CODE) "
				//	+ " 	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_D1_PERSONAL_REQUISITION.EXECUTIVE_CODE)"
					+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.NEXT_APPR_CODE)"
					+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.REQ_REPLACING)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.HIRING_MGNR_ID) "
					+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.PERS_INITIATOR)  "
					+" LEFT JOIN HRMS_DEPT  ON(HRMS_DEPT.DEPT_ID=HRMS_D1_PERSONAL_REQUISITION.DEPT_CODE )"
					+ "	LEFT JOIN HRMS_EMP_ADDRESS ON (OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID)"
					+ "  WHERE PERSONAL_REQ_ID = " + requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				reqbean.setRequestID(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setPositionTitleName(checkNull(String
						.valueOf(editObj[0][1])));
				reqbean
						.setRequisition(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setBandId(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setBandName(checkNull(String.valueOf(editObj[0][4])));
				reqbean.setMaxSalary(checkNull(String.valueOf(editObj[0][5])));
				reqbean
						.setPositionDate(checkNull(String
								.valueOf(editObj[0][6])));
				reqbean.setDeptCode(checkNull(String.valueOf(editObj[0][7])));
				reqbean.setExecutiveName(checkNull(String
						.valueOf(editObj[0][8])));

				/*reqbean.setExecutiveName(checkNull(String
						.valueOf(editObj[0][9])));*/
				reqbean.setWorkLocation(checkNull(String
						.valueOf(editObj[0][9])));
				reqbean.setHiringManagerCode(checkNull(String
						.valueOf(editObj[0][10])));

				reqbean.setHiringManagerToken(checkNull(String
						.valueOf(editObj[0][11])));
				reqbean.setHiringManagerName(checkNull(String
						.valueOf(editObj[0][12])));
				reqbean.setHiringManagerPhoneNumber(checkNull(String
						.valueOf(editObj[0][13])));

				reqbean.setHiringManagerFaxNumber(checkNull(String
						.valueOf(editObj[0][14])));

				reqbean.setHiringManagerEmailAddress(checkNull(String
						.valueOf(editObj[0][15])));

				reqbean.setApprovedExistingJob(checkNull(String
						.valueOf(editObj[0][16])));
				reqbean
						.setNewJobExist(checkNull(String
								.valueOf(editObj[0][17])));
				reqbean.setHeadCount(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setReplacementType(checkNull(String
						.valueOf(editObj[0][19])));
				reqbean.setReqReplacingCode(checkNull(String
						.valueOf(editObj[0][20])));
				reqbean.setReqReplacingToken(checkNull(String
						.valueOf(editObj[0][21])));
				reqbean.setReqReplacingName(checkNull(String
						.valueOf(editObj[0][22])));
				
				reqbean.setTerminationDate(checkNull(String
						.valueOf(editObj[0][23])));
				// reqbean.setPositionType(checkNull(String.valueOf(editObj[0][18])));

				if (String.valueOf(editObj[0][24]).equals("R")) {
					reqbean.setPositionType("Re");
					reqbean.setUserProfileRadioOptionValue("Re");
				} else if (String.valueOf(editObj[0][24]).equals("T")) {
					reqbean.setPositionType("Te");
					reqbean.setUserProfileRadioOptionValue("Te");
				} else if (String.valueOf(editObj[0][24]).equals("V")) {
					reqbean.setPositionType("Va");
					reqbean.setUserProfileRadioOptionValue("Va");
				} else {
					reqbean.setPositionType("");
					reqbean.setUserProfileRadioOptionValue("");
				}

				reqbean.setTemporaryType(checkNull(String
						.valueOf(editObj[0][25])));
				reqbean
						.setAgencyName(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setContractorName(checkNull(String
						.valueOf(editObj[0][27])));
				reqbean.setContractorPhoneNumber(checkNull(String
						.valueOf(editObj[0][28])));
				reqbean.setContractorEmailAddress(checkNull(String
						.valueOf(editObj[0][29])));
				reqbean.setMaximumBillRate(checkNull(String
						.valueOf(editObj[0][30])));

				reqbean.setOvertimeRequired(checkNull(String
						.valueOf(editObj[0][31])));
				reqbean.setNumberOfOvertime(checkNull(String
						.valueOf(editObj[0][32])));
				reqbean.setDurationOfAssignment(checkNull(String
						.valueOf(editObj[0][33])));
				reqbean.setReasonForTemporaryNeed(checkNull(String
						.valueOf(editObj[0][34])));
				reqbean.setVariableWorkfroceRate(checkNull(String
						.valueOf(editObj[0][35])));
				// reqbean.setUserProfile(checkNull(String.valueOf(editObj[0][30])));

				if (String.valueOf(editObj[0][36]).equals("Pc")) {
					reqbean.setRateType("Pc");
					reqbean.setRateTypeRadioOptionValue("Pc");
				} else if (String.valueOf(editObj[0][36]).equals("Pp")) {
					reqbean.setRateType("Pp");
					reqbean.setRateTypeRadioOptionValue("Pp");
				} else if (String.valueOf(editObj[0][36]).equals("Ph")) {
					reqbean.setRateType("Ph");
					reqbean.setRateTypeRadioOptionValue("Ph");
				} else {
					reqbean.setRateType("");
					reqbean.setRateTypeRadioOptionValue("");
				}

				reqbean.setDurationOfVariableAssignment(checkNull(String
						.valueOf(editObj[0][37])));
				reqbean.setReasonForVariableWorkforceNeed(checkNull(String
						.valueOf(editObj[0][38])));
				reqbean.setBudget(checkNull(String.valueOf(editObj[0][39])));
				reqbean.setStdHourPerWeek(checkNull(String
						.valueOf(editObj[0][40])));
				reqbean.setFulltimeParttime(checkNull(String
						.valueOf(editObj[0][41])));
				reqbean.setEducationRequirements(checkNull(String
						.valueOf(editObj[0][42])));
				reqbean.setExperienceRequirement(checkNull(String
						.valueOf(editObj[0][43])));
				reqbean.setEssentialPositionRequirements(checkNull(String
						.valueOf(editObj[0][44])));
				reqbean.setReason(checkNull(String.valueOf(editObj[0][45])));
				reqbean.setApproverCode(checkNull(String
						.valueOf(editObj[0][46])));
				reqbean.setApproverToken(checkNull(String
						.valueOf(editObj[0][47])));
				reqbean.setApproverName(checkNull(String
						.valueOf(editObj[0][48])));
				reqbean.setForwardedForApprovar(checkNull(String
						.valueOf(editObj[0][49])));
				reqbean.setPersReqStatus(checkNull(String
						.valueOf(editObj[0][50])));

				reqbean.setApplicationDate(checkNull(String
						.valueOf(editObj[0][51])));
				//reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][51])));
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][52])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][53])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][54])));
				reqbean.setDeptName(checkNull(String.valueOf(editObj[0][55])));
				
				/*reqbean.setHiringManagerPhoneNumber(checkNull(String.valueOf(editObj[0][57])));
				reqbean.setHiringManagerFaxNumber(checkNull(String.valueOf(editObj[0][58])));
				reqbean.setHiringManagerEmailAddress(checkNull(String.valueOf(editObj[0][59])));*/
				
				getApproverCommentList(reqbean, requestID);

				/*for (int i = 0; i < editObj.length; i++) {
					for (int j = 0; j < editObj[i].length; j++) {
						logger.info("editObj[" + i + "][" + j + "]  "
								+ editObj[i][j]);
					}
				}*/
				
				/*String qq = "SELECT NVL((PERSONAL_REQ_ID),0),(NVL((PERSONAL_REQ_ID),0))||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY')		 FROM HRMS_D1_PERSONAL_REQUISITION	WHERE PERSONAL_REQ_ID = " + requestID;
				Object[][] obj = getSqlModel().getSingleResult(qq);
				if (obj != null && obj.length > 0) {
					reqbean.setFileheaderName(checkNull(String.valueOf(obj[0][1])));
				}*/

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean delRecord(PersonalRequisition bean) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getRequestID();
		} catch (Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_D1_PERSONAL_REQUISITION WHERE PERSONAL_REQ_ID=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}

	public boolean sendForApprovalFunction(PersonalRequisition reqbean, HttpServletRequest request) {
		boolean result = false;
		
		if (reqbean.getRequestID().equals("")) {
			System.out.println("reqbean.getRequestID()========" + reqbean.getRequestID());
			result = draftFunction(reqbean);
		} else {
			result = updateRecords(reqbean);
			try {
				String changeStatusQuery = "UPDATE HRMS_D1_PERSONAL_REQUISITION SET PERS_REQ_STATUS = 'P' WHERE PERSONAL_REQ_ID = "
						+ reqbean.getRequestID();
				result = getSqlModel().singleExecute(changeStatusQuery);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(
				currentUserSql);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}

	/*
	 * public boolean cancelFunction(PersonalRequisition reqbean, String status) {
	 * boolean result = false; try { String changeStatusQuery = "UPDATE
	 * HRMS_D1_NEW_HIRE_REHIRE SET HIRE_STATUS = '"+status+"'" +" WHERE
	 * HIRE_REHIRE_ID = "+reqbean.getHireRehireId(); result =
	 * getSqlModel().singleExecute(changeStatusQuery); } catch (Exception e) {
	 * e.printStackTrace(); } return result; }
	 */

	public void getApprovedList(PersonalRequisition reqbean,
			HttpServletRequest request, String userId) {

		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();

			Object[][] approvedCancellationListData = null;
			ArrayList approvedCancellationList = new ArrayList();

			// Approved application Begins
			String selQuery = "  SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION , "
					+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
					+ " WHERE PERS_REQ_STATUS IN ('A','S') ORDER BY PERSONAL_REQ_ID DESC ";
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(reqbean
					.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				reqbean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				reqbean.setApprovedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					PersonalRequisition beanItt = new PersonalRequisition();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					
					beanItt.setPositionTitle(checkNull(String
							.valueOf(approvedListData[i][1])));
					beanItt.setRequisition(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setApplicationDate(checkNull(String
							.valueOf(approvedListData[i][3])));

					beanItt.setPersonalRequisitionID(checkNull(String
							.valueOf(approvedListData[i][5])));
					beanItt.setPositionRequiredDateItr(checkNull(String
							.valueOf(approvedListData[i][6])));
					approvedList.add(beanItt);
				}
				reqbean.setApprovedVoucherIteratorList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins
			String approvedcancellationQuery = "  SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION , "
					+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
					+ " WHERE PERS_REQ_STATUS = 'X' ORDER BY PERSONAL_REQ_ID DESC ";
			approvedCancellationListData = getSqlModel().getSingleResult(
					approvedcancellationQuery);

			String[] pageIndexApprovedCancel = Utility.doPaging(reqbean
					.getMyPageApprovedCancel(),
					approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = "0";
				pageIndexApprovedCancel[1] = "20";
				pageIndexApprovedCancel[2] = "1";
				pageIndexApprovedCancel[3] = "1";
				pageIndexApprovedCancel[4] = "";
			}

			request.setAttribute("totalApprovedCancellationPage", Integer
					.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer
					.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if (pageIndexApprovedCancel[4].equals("1"))
				reqbean.setMyPageApprovedCancel("1");

			if (approvedCancellationListData != null
					&& approvedCancellationListData.length > 0) {
				reqbean.setApprovedCancellationVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer
						.parseInt(pageIndexApprovedCancel[1]); i++) {
					PersonalRequisition beanItt = new PersonalRequisition();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedCancellationListData[i][0])));
					
					beanItt.setPositionTitle(checkNull(String
							.valueOf(approvedCancellationListData[i][1])));
					beanItt.setRequisition(checkNull(String
							.valueOf(approvedCancellationListData[i][2])));
					beanItt.setApplicationDate(checkNull(String
							.valueOf(approvedCancellationListData[i][3])));

					beanItt.setPersonalRequisitionID(checkNull(String
							.valueOf(approvedCancellationListData[i][5])));
					beanItt.setPositionRequiredDateItr(checkNull(String
							.valueOf(approvedCancellationListData[i][6])));
					approvedCancellationList.add(beanItt);
				}
				reqbean
						.setApprovedCancellationVoucherIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getCancelledList(PersonalRequisition reqbean,
			HttpServletRequest request, String userId) {

		try {
			Object[][] cancelledListData = null;
			ArrayList cancelledList = new ArrayList();

			// Cancelled application Begins
			String selQuery = " SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION , "
					+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
					+ "  WHERE PERS_REQ_STATUS = 'C' ORDER BY PERSONAL_REQ_ID DESC ";
			cancelledListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexCancel = Utility.doPaging(reqbean
					.getMyPageCancel(), cancelledListData.length, 20);
			if (pageIndexCancel == null) {
				pageIndexCancel[0] = "0";
				pageIndexCancel[1] = "20";
				pageIndexCancel[2] = "1";
				pageIndexCancel[3] = "1";
				pageIndexCancel[4] = "";
			}

			request.setAttribute("totalCancelPage", Integer.parseInt(String
					.valueOf(pageIndexCancel[2])));
			request.setAttribute("cancelPageNo", Integer.parseInt(String
					.valueOf(pageIndexCancel[3])));
			if (pageIndexCancel[4].equals("1"))
				reqbean.setMyPageCancel("1");

			if (cancelledListData != null && cancelledListData.length > 0) {
				reqbean.setCancelledVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexCancel[0]); i < Integer
						.parseInt(pageIndexCancel[1]); i++) {
					PersonalRequisition beanItt = new PersonalRequisition();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(cancelledListData[i][0])));
					
					beanItt.setPositionTitle(checkNull(String
							.valueOf(cancelledListData[i][1])));
					beanItt.setRequisition(checkNull(String
							.valueOf(cancelledListData[i][2])));
					beanItt.setApplicationDate(checkNull(String
							.valueOf(cancelledListData[i][3])));

					beanItt.setPersonalRequisitionID(checkNull(String
							.valueOf(cancelledListData[i][5])));
					beanItt.setPositionRequiredDateItr(checkNull(String
							.valueOf(cancelledListData[i][6])));
					cancelledList.add(beanItt);
				}
				reqbean.setCancelledVoucherIteratorList(cancelledList);
			}
			// Cancelled application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRejectedList(PersonalRequisition reqbean,
			HttpServletRequest request, String userId) {

		Object[][] rejectedListData = null;
		ArrayList rejectedList = new ArrayList();

		Object[][] rejectedCancellationListData = null;
		ArrayList rejectedCancellationList = new ArrayList();

		// Rejected application Begins
		String selQuery = "  SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION , "
				+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
				+ " WHERE PERS_REQ_STATUS = 'R' ORDER BY PERSONAL_REQ_ID DESC ";
		rejectedListData = getSqlModel().getSingleResult(selQuery);

		String[] pageIndexRejected = Utility.doPaging(reqbean
				.getMyPageRejected(), rejectedListData.length, 20);
		if (pageIndexRejected == null) {
			pageIndexRejected[0] = "0";
			pageIndexRejected[1] = "20";
			pageIndexRejected[2] = "1";
			pageIndexRejected[3] = "1";
			pageIndexRejected[4] = "";
		}

		request.setAttribute("totalRejectedPage", Integer.parseInt(String
				.valueOf(pageIndexRejected[2])));
		request.setAttribute("rejectedPageNo", Integer.parseInt(String
				.valueOf(pageIndexRejected[3])));
		if (pageIndexRejected[4].equals("1"))
			reqbean.setMyPageRejected("1");

		if (rejectedListData != null && rejectedListData.length > 0) {
			reqbean.setRejectedVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
					.parseInt(pageIndexRejected[1]); i++) {
				PersonalRequisition beanItt = new PersonalRequisition();
				beanItt.setTrackingNo(checkNull(String
						.valueOf(rejectedListData[i][0])));
				
				beanItt.setPositionTitle(checkNull(String
						.valueOf(rejectedListData[i][1])));
				beanItt.setRequisition(checkNull(String
						.valueOf(rejectedListData[i][2])));
				beanItt.setApplicationDate(checkNull(String
						.valueOf(rejectedListData[i][3])));

				beanItt.setPersonalRequisitionID(checkNull(String
						.valueOf(rejectedListData[i][5])));
				beanItt.setPositionRequiredDateItr(checkNull(String
						.valueOf(rejectedListData[i][6])));
				rejectedList.add(beanItt);
			}
			reqbean.setRejectedVoucherIteratorList(rejectedList);
		}
		// Rejected application Ends

		// Rejected cancellation application Begins
		String approvedcancellationQuery = "  SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION , "
				+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
				+ " WHERE PERS_REQ_STATUS = 'Z' ORDER BY PERSONAL_REQ_ID DESC ";
		rejectedCancellationListData = getSqlModel().getSingleResult(
				approvedcancellationQuery);

		String[] pageIndexRejectedCancellation = Utility.doPaging(reqbean
				.getMyPageCancelRejected(),
				rejectedCancellationListData.length, 20);
		if (pageIndexRejectedCancellation == null) {
			pageIndexRejectedCancellation[0] = "0";
			pageIndexRejectedCancellation[1] = "20";
			pageIndexRejectedCancellation[2] = "1";
			pageIndexRejectedCancellation[3] = "1";
			pageIndexRejectedCancellation[4] = "";
		}

		request.setAttribute("totalRejectedCancellationPage", Integer
				.parseInt(String.valueOf(pageIndexRejectedCancellation[2])));
		request.setAttribute("rejectedCancellationPageNo", Integer
				.parseInt(String.valueOf(pageIndexRejectedCancellation[3])));
		if (pageIndexRejectedCancellation[4].equals("1"))
			reqbean.setMyPageCancelRejected("1");

		if (rejectedCancellationListData != null
				&& rejectedCancellationListData.length > 0) {
			reqbean.setRejectedCancelVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer
					.parseInt(pageIndexRejectedCancellation[1]); i++) {
				PersonalRequisition beanItt = new PersonalRequisition();
				beanItt.setTrackingNo(checkNull(String
						.valueOf(rejectedCancellationListData[i][0])));
				
				beanItt.setPositionTitle(checkNull(String
						.valueOf(rejectedCancellationListData[i][1])));
				beanItt.setRequisition(checkNull(String
						.valueOf(rejectedCancellationListData[i][2])));
				beanItt.setApplicationDate(checkNull(String
						.valueOf(rejectedCancellationListData[i][3])));

				beanItt.setPersonalRequisitionID(checkNull(String
						.valueOf(rejectedCancellationListData[i][5])));
				beanItt.setPositionRequiredDateItr(checkNull(String
						.valueOf(rejectedCancellationListData[i][6])));
				rejectedCancellationList.add(beanItt);
			}
			reqbean
					.setRejectedCancelVoucherIteratorList(rejectedCancellationList);
		}
		// Rejected cancellation application Ends
	}

	public boolean isUserAHRApprover(String userId) {
		String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'H' AND APP_SETTINGS_EMP_ID = "
				+ userId;
		Object[][] hrApproverObj = getSqlModel().getSingleResult(hrApproverSql);

		if (hrApproverObj != null && hrApproverObj.length > 0) {
			return true;
		}

		return false;
	}
	
	public void getEmployeeDetails(String empId, PersonalRequisition bean) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	HRMS_D1_DEPARTMENT.DEPT_NUMBER, NVL(HRMS_DEPT.DEPT_NAME, ' '),HRMS_EMP_ADDRESS.ADD_PH1,ADD_FAX, ADD_EMAIL, HRMS_EMP_OFFC.EMP_ID"
					+ "	FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_DEPT ON (EMP_DEPT = HRMS_DEPT.DEPT_ID) "
					+ " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID) "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID=?";

			Object[][] data = getSqlModel().getSingleResult(query, beanObj);
			if (data != null && data.length > 0) {
				bean.setHiringManagerToken(String.valueOf(data[0][0]));// employee
				// token

				bean
						.setHiringManagerName(checkNull(String
								.valueOf(data[0][1])));// employee
				// name
				/*
				 * bean.setDeptNumber(checkNull(String.valueOf(data[0][2])));//
				 * Dept Number
				 * bean.setAreaType(checkNull(String.valueOf(data[0][3])));//
				 * department
				 */
				bean.setHiringManagerPhoneNumber(checkNull(String
						.valueOf(data[0][4])));// work phone
				bean.setHiringManagerFaxNumber(checkNull(String
						.valueOf(data[0][5])));
				bean.setHiringManagerEmailAddress(checkNull(String
						.valueOf(data[0][6])));
				bean
						.setHiringManagerCode(checkNull(String
								.valueOf(data[0][7])));// employee
				// id

			}// end of for loop

			String dateQuery="SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual "	;
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if(dateObj!=null && dateObj.length >0){
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}
			
			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
			+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
			+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID =" + empId;
			
			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if(initiatorObj!=null && initiatorObj.length >0){
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
		} catch (Exception e) {
			logger.info("Exception in getEmployeeDetails------------" + e);
		}

	}

	public void getSysDate(PersonalRequisition bean) {
		String sysDateSql = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object data[][] = getSqlModel().getSingleResult(sysDateSql);

		if (data != null && data.length > 0) {
			bean.setApplicationDate(String.valueOf(data[0][0]));// Set sys_date as
			// application date
		}
		
	}

	public boolean cancelFunction(PersonalRequisition bean, String status, String apprCode, String forwardedTo) {
		boolean result = false;			
		try {
			String changeStatusQuery = "UPDATE HRMS_D1_PERSONAL_REQUISITION SET PERS_REQ_STATUS = '"+status+"' , FORWARDED_TO = '"+forwardedTo+"', NEXT_APPR_CODE = '"+apprCode+"' "
										+" WHERE PERSONAL_REQ_ID = " + bean.getRequestID();
			
			result =  getSqlModel().singleExecute(changeStatusQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void viewApprovedFunction(PersonalRequisition reqbean, String requestID) {
		try {
			String editQuery = "SELECT PERSONAL_REQ_ID, POSITION_TITLE, REQUISITION, BAND_CODE,CADRE_NAME, "
					+ " MAX_SALARY, TO_CHAR(POSITION_REQ_DATE,'DD-MM-YYYY'), DEPT_CODE, EXECUTIVE_CODE, WORK_LOCATION, "
					+ " 	HIRING_MGNR_ID,OFFC.EMP_TOKEN AS HR_TOKEN,"
					+ "	OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS HR_NAME, "
					+ "	HIRING_MGNR_PHONE_NUMBER, HIRING_MGNR_FAX_NUMBER, HIRING_MGNR_EMAIL_ID, IS_JOB_EXIST, IS_JOB_DESC_EXIST,"
					+ "	IS_HEAD_COUNT, IS_REPLACEMENT,REQ_REPLACING,OFFC2.EMP_TOKEN AS REPLACING_TOKEN,OFFC2.EMP_FNAME || ' ' || OFFC2.EMP_MNAME || ' ' || OFFC2.EMP_LNAME AS REPLACING_NAME, TO_CHAR(TERMINATION_DATE,'DD-MM-YYYY'), "
					+ " 	POSITION_TYPE, TEMPORARY_TYPE, AGENCY_NAME, CONTR_NAME, CONTR_PHONE_NUMBER, CONTR_EMAIL_ID, "
					+ " 	MAX_BILL_RATE, IS_OT_REQ, NO_OT_PER_WEEK, ASSIGN_DURATION, REASON_FOR_TEMP, VARIABLE_RATE, "
					+ " 	RATE_TYPE, VAR_ASIGN_DURATION, REASON_FOR_VARIABLE, IS_BUDGET, STD_HOUR_WEEK, FULL_PART_TIME, "
					+ " 	EDUCATION_REQ, EXPER_REQ, NO_POSITION, JUSTIFICATION, PERS_REQ_STATUS, TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') "
					+ " 	,PERS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(PERS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') , NVL(HRMS_DEPT.DEPT_NAME, ' ') FROM HRMS_D1_PERSONAL_REQUISITION  "
					+ " 	LEFT JOIN HRMS_CADRE ON ( HRMS_CADRE.CADRE_ID = HRMS_D1_PERSONAL_REQUISITION.BAND_CODE) "
				//	+ " 	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_D1_PERSONAL_REQUISITION.EXECUTIVE_CODE)"
				//	+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.NEXT_APPR_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.HIRING_MGNR_ID) "
					+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.REQ_REPLACING)"
					+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.PERS_INITIATOR)  "
					+" LEFT JOIN HRMS_DEPT  ON(HRMS_DEPT.DEPT_ID=HRMS_D1_PERSONAL_REQUISITION.DEPT_CODE )"
					+ "	LEFT JOIN HRMS_EMP_ADDRESS ON (OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID)"
					+ "  WHERE PERSONAL_REQ_ID = " + requestID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				reqbean.setRequestID(checkNull(String.valueOf(editObj[0][0])));
				reqbean.setPositionTitleName(checkNull(String
						.valueOf(editObj[0][1])));
				reqbean
						.setRequisition(checkNull(String.valueOf(editObj[0][2])));
				reqbean.setBandId(checkNull(String.valueOf(editObj[0][3])));
				reqbean.setBandName(checkNull(String.valueOf(editObj[0][4])));
				reqbean.setMaxSalary(checkNull(String.valueOf(editObj[0][5])));
				reqbean
						.setPositionDate(checkNull(String
								.valueOf(editObj[0][6])));
				reqbean.setDeptCode(checkNull(String.valueOf(editObj[0][7])));
				reqbean.setExecutiveName(checkNull(String
						.valueOf(editObj[0][8])));

				/*reqbean.setExecutiveName(checkNull(String
						.valueOf(editObj[0][9])));*/
				reqbean.setWorkLocation(checkNull(String
						.valueOf(editObj[0][9])));
				reqbean.setHiringManagerCode(checkNull(String
						.valueOf(editObj[0][10])));

				reqbean.setHiringManagerToken(checkNull(String
						.valueOf(editObj[0][11])));
				reqbean.setHiringManagerName(checkNull(String
						.valueOf(editObj[0][12])));
				reqbean.setHiringManagerPhoneNumber(checkNull(String
						.valueOf(editObj[0][13])));

				reqbean.setHiringManagerFaxNumber(checkNull(String
						.valueOf(editObj[0][14])));

				reqbean.setHiringManagerEmailAddress(checkNull(String
						.valueOf(editObj[0][15])));

				reqbean.setApprovedExistingJob(checkNull(String
						.valueOf(editObj[0][16])));
				reqbean
						.setNewJobExist(checkNull(String
								.valueOf(editObj[0][18])));
				reqbean.setHeadCount(checkNull(String.valueOf(editObj[0][18])));
				reqbean.setReplacementType(checkNull(String
						.valueOf(editObj[0][19])));
				reqbean.setReqReplacingCode(checkNull(String
						.valueOf(editObj[0][20])));
				
				reqbean.setReqReplacingToken(checkNull(String
						.valueOf(editObj[0][21])));
				reqbean.setReqReplacingName(checkNull(String
						.valueOf(editObj[0][22])));
				
				
				reqbean.setTerminationDate(checkNull(String
						.valueOf(editObj[0][23])));
				// reqbean.setPositionType(checkNull(String.valueOf(editObj[0][18])));

				if (String.valueOf(editObj[0][24]).equals("R")) {
					reqbean.setPositionType("Re");
					reqbean.setUserProfileRadioOptionValue("Re");
				} else if (String.valueOf(editObj[0][24]).equals("T")) {
					reqbean.setPositionType("Te");
					reqbean.setUserProfileRadioOptionValue("Te");
				} else if (String.valueOf(editObj[0][24]).equals("V")) {
					reqbean.setPositionType("Va");
					reqbean.setUserProfileRadioOptionValue("Va");
				} else {
					reqbean.setPositionType("");
					reqbean.setUserProfileRadioOptionValue("");
				}

				reqbean.setTemporaryType(checkNull(String
						.valueOf(editObj[0][25])));
				reqbean
						.setAgencyName(checkNull(String.valueOf(editObj[0][26])));
				reqbean.setContractorName(checkNull(String
						.valueOf(editObj[0][27])));
				reqbean.setContractorPhoneNumber(checkNull(String
						.valueOf(editObj[0][28])));
				reqbean.setContractorEmailAddress(checkNull(String
						.valueOf(editObj[0][29])));
				reqbean.setMaximumBillRate(checkNull(String
						.valueOf(editObj[0][30])));

				reqbean.setOvertimeRequired(checkNull(String
						.valueOf(editObj[0][31])));
				reqbean.setNumberOfOvertime(checkNull(String
						.valueOf(editObj[0][32])));
				reqbean.setDurationOfAssignment(checkNull(String
						.valueOf(editObj[0][33])));
				reqbean.setReasonForTemporaryNeed(checkNull(String
						.valueOf(editObj[0][34])));
				reqbean.setVariableWorkfroceRate(checkNull(String
						.valueOf(editObj[0][35])));
				// reqbean.setUserProfile(checkNull(String.valueOf(editObj[0][30])));

				if (String.valueOf(editObj[0][36]).equals("Pc")) {
					reqbean.setRateType("Pc");
					reqbean.setRateTypeRadioOptionValue("Pc");
				} else if (String.valueOf(editObj[0][36]).equals("Pp")) {
					reqbean.setRateType("Pp");
					reqbean.setRateTypeRadioOptionValue("Pp");
				} else if (String.valueOf(editObj[0][36]).equals("Ph")) {
					reqbean.setRateType("Ph");
					reqbean.setRateTypeRadioOptionValue("Ph");
				} else {
					reqbean.setRateType("");
					reqbean.setRateTypeRadioOptionValue("");
				}

				reqbean.setDurationOfVariableAssignment(checkNull(String
						.valueOf(editObj[0][37])));
				reqbean.setReasonForVariableWorkforceNeed(checkNull(String
						.valueOf(editObj[0][38])));
				reqbean.setBudget(checkNull(String.valueOf(editObj[0][39])));
				reqbean.setStdHourPerWeek(checkNull(String
						.valueOf(editObj[0][40])));
				reqbean.setFulltimeParttime(checkNull(String
						.valueOf(editObj[0][41])));
				reqbean.setEducationRequirements(checkNull(String
						.valueOf(editObj[0][42])));
				reqbean.setExperienceRequirement(checkNull(String
						.valueOf(editObj[0][43])));
				reqbean.setEssentialPositionRequirements(checkNull(String
						.valueOf(editObj[0][44])));
				reqbean.setReason(checkNull(String.valueOf(editObj[0][45])));
				/*reqbean.setApproverCode(checkNull(String
						.valueOf(editObj[0][45])));
				reqbean.setApproverToken(checkNull(String
						.valueOf(editObj[0][46])));
				reqbean.setApproverName(checkNull(String
						.valueOf(editObj[0][47])));
				reqbean.setForwardedForApprovar(checkNull(String
						.valueOf(editObj[0][48])));*/
				reqbean.setPersReqStatus(checkNull(String
						.valueOf(editObj[0][46])));

				reqbean.setApplicationDate(checkNull(String
						.valueOf(editObj[0][47])));
				//reqbean.setTrackingNo(checkNull(String.valueOf(editObj[0][51])));
				reqbean.setInitiatorCode(checkNull(String.valueOf(editObj[0][48])));
				reqbean.setInitiatorName(checkNull(String.valueOf(editObj[0][49])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(editObj[0][50])));
				reqbean.setDeptName(checkNull(String.valueOf(editObj[0][51])));
				
				/*reqbean.setHiringManagerPhoneNumber(checkNull(String.valueOf(editObj[0][53])));
				reqbean.setHiringManagerFaxNumber(checkNull(String.valueOf(editObj[0][54])));
				reqbean.setHiringManagerEmailAddress(checkNull(String.valueOf(editObj[0][55])));*/
				
				getApproverCommentList(reqbean, requestID);

				/*for (int i = 0; i < editObj.length; i++) {
					for (int j = 0; j < editObj[i].length; j++) {
						logger.info("editObj[" + i + "][" + j + "]  "
								+ editObj[i][j]);
					}
				}*/
				
				/*String qq = "SELECT NVL((PERSONAL_REQ_ID),0),(NVL((PERSONAL_REQ_ID),0))||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY')		 FROM HRMS_D1_PERSONAL_REQUISITION	WHERE PERSONAL_REQ_ID = " + requestID;
				Object[][] obj = getSqlModel().getSingleResult(qq);
				if (obj != null && obj.length > 0) {
					reqbean.setFileheaderName(checkNull(String.valueOf(obj[0][1])));
				}*/

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
