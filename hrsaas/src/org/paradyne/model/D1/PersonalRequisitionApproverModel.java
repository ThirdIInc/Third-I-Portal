package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.PersonalRequisitionApprover;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class PersonalRequisitionApproverModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PersonalRequisitionApproverModel.class);

	
	
	
	// Pending list Begins (Manager approver) 
	public void getPendingList(PersonalRequisitionApprover bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			

			// For Pending application Begins
					
			String selQuery =  "SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION , "
			+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
			+ " WHERE PERS_REQ_STATUS IN ('F','P')" + " AND NEXT_APPR_CODE =" +userId 
		+ "	ORDER BY HRMS_D1_PERSONAL_REQUISITION.PERSONAL_REQ_ID DESC ";
			
			
			pendingListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean
					.getMyPage(), pendingListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				bean.setMyPage("1");

			if (pendingListData != null && pendingListData.length > 0) {
				bean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					PersonalRequisitionApprover bean1 = new PersonalRequisitionApprover();
					bean1.setTrackingNo(checkNull(String
							.valueOf(pendingListData[i][0])));
					bean1.setPositionTitle(checkNull(String
							.valueOf(pendingListData[i][1])));
					bean1.setRequisition(checkNull(String
							.valueOf(pendingListData[i][2])));
					
					bean1.setApplicationDate(checkNull(String
							.valueOf(pendingListData[i][3])));

					bean1.setPersonalRequisitionApprID(checkNull(String
							.valueOf(pendingListData[i][5])));
					bean1.setPositionRequiredDateItr(checkNull(String
							.valueOf(pendingListData[i][6])));
					pendingDataList.add(bean1);
				}
				bean.setPendingIteratorList(pendingDataList);
			}
			// For Pending application Ends

			// pending cancellation application Begins
					
			String pendingCancellationQuery = "SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION , "
				+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
				+ "  WHERE PERS_REQ_STATUS = 'C'" +
						" AND NEXT_APPR_CODE =" +userId 
			+ "	ORDER BY HRMS_D1_PERSONAL_REQUISITION.PERSONAL_REQ_ID DESC ";
			
			pendingCancellationListData = getSqlModel().getSingleResult(pendingCancellationQuery);

			String[] pagependingCancellationIndex = Utility.doPaging(bean
					.getMyPagePendingCancel(), pendingCancellationListData.length, 20);
			if (pageIndexDrafted == null) {
				pagependingCancellationIndex[0] = "0";
				pagependingCancellationIndex[1] = "20";
				pagependingCancellationIndex[2] = "1";
				pagependingCancellationIndex[3] = "1";
				pagependingCancellationIndex[4] = "";
			}

			request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String
					.valueOf(pagependingCancellationIndex[2])));
			request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String
					.valueOf(pagependingCancellationIndex[3])));
			if (pagependingCancellationIndex[4].equals("1"))
				bean.setMyPagePendingCancel("1");

			if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
				bean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer
						.parseInt(pagependingCancellationIndex[1]); i++) {
					PersonalRequisitionApprover bean1 = new PersonalRequisitionApprover();
					bean1.setTrackingNo(checkNull(String
							.valueOf(pendingCancellationListData[i][0])));
					
					bean1.setPositionTitle(checkNull(String
							.valueOf(pendingCancellationListData[i][1])));
					bean1.setRequisition(checkNull(String
							.valueOf(pendingCancellationListData[i][2])));
					
					bean1.setApplicationDate(checkNull(String
							.valueOf(pendingCancellationListData[i][3])));

					bean1.setPersonalRequisitionApprID(checkNull(String
							.valueOf(pendingCancellationListData[i][5])));
					bean1.setPositionRequiredDateItr(checkNull(String
							.valueOf(pendingCancellationListData[i][6])));
					pendingCancellationDataList.add(bean1);
				}
				bean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// For pending cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getApproverCommentList(PersonalRequisitionApprover bean,
			String requestID) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, PERS_COMMENTS, "
				+ " TO_CHAR(PERS_APPROVED_DATE, 'DD-MM-YYYY') AS PERS_APPROVED_DATE, "
				+ " DECODE(PERS_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'S', 'Authorized SignOff') AS STATUS "
				+ " FROM HRMS_D1_PERSREQ_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_PERSREQ_PATH.PERS_APPROVER_CODE) "
				+ " WHERE PERS_REQ_ID = "
				+ requestID
				+ " ORDER BY PERS_PATH_ID DESC";

		Object[][] apprCommentListObj = getSqlModel().getSingleResult(
				apprCommentListSql);
		ArrayList approverList = new ArrayList<Object>(
				apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			bean.setApproverCommentsFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				PersonalRequisitionApprover innerBean = new PersonalRequisitionApprover();
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
	

	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	

	public void editApplicationFunction(PersonalRequisitionApprover reqbean,
			String requestID) {
		try {
			String editQuery = "SELECT PERSONAL_REQ_ID, POSITION_TITLE, REQUISITION, BAND_CODE,CADRE_NAME, "
					+ " MAX_SALARY, TO_CHAR(POSITION_REQ_DATE,'DD-MM-YYYY'), DEPT_CODE, EXECUTIVE_CODE,RANK_NAME, WORK_LOCATION, "
					+ " 	HIRING_MGNR_ID,OFFC.EMP_TOKEN AS HR_TOKEN,"
					+ "	OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS HR_NAME, "
					+ "	HRMS_EMP_ADDRESS.ADD_PH1,ADD_FAX, ADD_EMAIL, IS_JOB_EXIST, IS_JOB_DESC_EXIST,"
					+ "	IS_HEAD_COUNT, IS_REPLACEMENT,REQ_REPLACING, TO_CHAR(TERMINATION_DATE,'DD-MM-YYYY'), "
					+ " 	POSITION_TYPE, TEMPORARY_TYPE, AGENCY_NAME, CONTR_NAME, CONTR_PHONE_NUMBER, CONTR_EMAIL_ID, "
					+ " 	MAX_BILL_RATE, IS_OT_REQ, NO_OT_PER_WEEK, ASSIGN_DURATION, REASON_FOR_TEMP, VARIABLE_RATE, "
					+ " 	RATE_TYPE, VAR_ASIGN_DURATION, REASON_FOR_VARIABLE, IS_BUDGET, STD_HOUR_WEEK, FULL_PART_TIME, "
					+ " 	EDUCATION_REQ, EXPER_REQ, NO_POSITION, JUSTIFICATION, NEXT_APPR_CODE,OFFC1.EMP_TOKEN AS APPROVER_TOKEN, "
					+ " 	OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,"
					+ " 	FORWARDED_TO, PERS_REQ_STATUS, TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') "
					+ " 	FROM HRMS_D1_PERSONAL_REQUISITION  "
					+ " 	LEFT JOIN HRMS_CADRE ON ( HRMS_CADRE.CADRE_ID = HRMS_D1_PERSONAL_REQUISITION.BAND_CODE) "
					+ " 	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_D1_PERSONAL_REQUISITION.EXECUTIVE_CODE)"
					+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.NEXT_APPR_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.HIRING_MGNR_ID) "
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
				reqbean.setExecutiveCode(checkNull(String
						.valueOf(editObj[0][8])));

				reqbean.setExecutiveName(checkNull(String
						.valueOf(editObj[0][9])));
				reqbean.setWorkLocation(checkNull(String
						.valueOf(editObj[0][10])));
				reqbean.setHiringManagerCode(checkNull(String
						.valueOf(editObj[0][11])));

				reqbean.setHiringManagerToken(checkNull(String
						.valueOf(editObj[0][12])));
				reqbean.setHiringManagerName(checkNull(String
						.valueOf(editObj[0][13])));
				reqbean.setHiringManagerPhoneNumber(checkNull(String
						.valueOf(editObj[0][14])));

				reqbean.setHiringManagerFaxNumber(checkNull(String
						.valueOf(editObj[0][15])));

				reqbean.setHiringManagerEmailAddress(checkNull(String
						.valueOf(editObj[0][16])));

				reqbean.setApprovedExistingJob(checkNull(String
						.valueOf(editObj[0][17])));
				reqbean
						.setNewJobExist(checkNull(String
								.valueOf(editObj[0][18])));
				reqbean.setHeadCount(checkNull(String.valueOf(editObj[0][19])));
				reqbean.setReplacementType(checkNull(String
						.valueOf(editObj[0][20])));
				reqbean.setReqReplacing(checkNull(String
						.valueOf(editObj[0][21])));
				reqbean.setTerminationDate(checkNull(String
						.valueOf(editObj[0][22])));
				// reqbean.setPositionType(checkNull(String.valueOf(editObj[0][18])));

				if (String.valueOf(editObj[0][23]).equals("R")) {
					reqbean.setPositionType("Re");
					reqbean.setUserProfileRadioOptionValue("Re");
				} else if (String.valueOf(editObj[0][23]).equals("T")) {
					reqbean.setPositionType("Te");
					reqbean.setUserProfileRadioOptionValue("Te");
				} else if (String.valueOf(editObj[0][23]).equals("V")) {
					reqbean.setPositionType("Va");
					reqbean.setUserProfileRadioOptionValue("Va");
				} else {
					reqbean.setPositionType("");
					reqbean.setUserProfileRadioOptionValue("");
				}

				reqbean.setTemporaryType(checkNull(String
						.valueOf(editObj[0][24])));
				reqbean
						.setAgencyName(checkNull(String.valueOf(editObj[0][25])));
				reqbean.setContractorName(checkNull(String
						.valueOf(editObj[0][26])));
				reqbean.setContractorPhoneNumber(checkNull(String
						.valueOf(editObj[0][27])));
				reqbean.setContractorEmailAddress(checkNull(String
						.valueOf(editObj[0][28])));
				reqbean.setMaximumBillRate(checkNull(String
						.valueOf(editObj[0][29])));

				reqbean.setOvertimeRequired(checkNull(String
						.valueOf(editObj[0][30])));
				reqbean.setNumberOfOvertime(checkNull(String
						.valueOf(editObj[0][31])));
				reqbean.setDurationOfAssignment(checkNull(String
						.valueOf(editObj[0][32])));
				reqbean.setReasonForTemporaryNeed(checkNull(String
						.valueOf(editObj[0][33])));
				reqbean.setVariableWorkfroceRate(checkNull(String
						.valueOf(editObj[0][34])));
				// reqbean.setUserProfile(checkNull(String.valueOf(editObj[0][30])));

				if (String.valueOf(editObj[0][35]).equals("Pc")) {
					reqbean.setRateType("Pc");
					reqbean.setRateTypeRadioOptionValue("Pc");
				} else if (String.valueOf(editObj[0][35]).equals("Pp")) {
					reqbean.setRateType("Pp");
					reqbean.setRateTypeRadioOptionValue("Pp");
				} else if (String.valueOf(editObj[0][35]).equals("Ph")) {
					reqbean.setRateType("Ph");
					reqbean.setRateTypeRadioOptionValue("Ph");
				} else {
					reqbean.setRateType("");
					reqbean.setRateTypeRadioOptionValue("");
				}

				reqbean.setDurationOfVariableAssignment(checkNull(String
						.valueOf(editObj[0][36])));
				reqbean.setReasonForVariableWorkforceNeed(checkNull(String
						.valueOf(editObj[0][37])));
				reqbean.setBudget(checkNull(String.valueOf(editObj[0][38])));
				reqbean.setStdHourPerWeek(checkNull(String
						.valueOf(editObj[0][39])));
				reqbean.setFulltimeParttime(checkNull(String
						.valueOf(editObj[0][40])));
				reqbean.setEducationRequirements(checkNull(String
						.valueOf(editObj[0][41])));
				reqbean.setExperienceRequirement(checkNull(String
						.valueOf(editObj[0][42])));
				reqbean.setEssentialPositionRequirements(checkNull(String
						.valueOf(editObj[0][43])));
				reqbean.setReason(checkNull(String.valueOf(editObj[0][44])));
				reqbean.setApproverCode(checkNull(String
						.valueOf(editObj[0][45])));
				reqbean.setApproverToken(checkNull(String
						.valueOf(editObj[0][46])));
				reqbean.setApproverName(checkNull(String
						.valueOf(editObj[0][47])));
				reqbean.setForwardedForApprovar(checkNull(String
						.valueOf(editObj[0][48])));
				reqbean.setPersReqStatus(checkNull(String
						.valueOf(editObj[0][49])));

				reqbean.setApplicationDate(checkNull(String
						.valueOf(editObj[0][50])));
				 getApproverCommentList(reqbean, requestID);

				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void viewApplicationFunction(PersonalRequisitionApprover reqbean,
			String requestID , String typeOfList) {
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
					+ " 	EDUCATION_REQ, EXPER_REQ, NO_POSITION, JUSTIFICATION, "
					+ " 	FORWARDED_TO, PERS_REQ_STATUS, TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ,TRACKING_NUMBER"
					+ " 	,PERS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(PERS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI'), NVL(HRMS_DEPT.DEPT_NAME, ' ') FROM HRMS_D1_PERSONAL_REQUISITION  "
					+ " 	LEFT JOIN HRMS_CADRE ON ( HRMS_CADRE.CADRE_ID = HRMS_D1_PERSONAL_REQUISITION.BAND_CODE) "
				//	+ " 	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_D1_PERSONAL_REQUISITION.EXECUTIVE_CODE)"
					+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.NEXT_APPR_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.HIRING_MGNR_ID) "
					+ "	LEFT JOIN HRMS_EMP_ADDRESS ON (OFFC.EMP_ID = HRMS_EMP_ADDRESS.EMP_ID)"
					+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.PERS_INITIATOR)  "
					+ " 	LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_PERSONAL_REQUISITION.REQ_REPLACING)"
					+" LEFT JOIN HRMS_DEPT  ON(HRMS_DEPT.DEPT_ID=HRMS_D1_PERSONAL_REQUISITION.DEPT_CODE )"
					+ "  WHERE PERSONAL_REQ_ID = " + requestID;
			Object[][] viewObj = getSqlModel().getSingleResult(editQuery);
			if (viewObj != null && viewObj.length > 0) {
				reqbean.setRequestID(checkNull(String.valueOf(viewObj[0][0])));
				reqbean.setPositionTitleName(checkNull(String
						.valueOf(viewObj[0][1])));
				reqbean
						.setRequisition(checkNull(String.valueOf(viewObj[0][2])));
				reqbean.setBandId(checkNull(String.valueOf(viewObj[0][3])));
				reqbean.setBandName(checkNull(String.valueOf(viewObj[0][4])));
				reqbean.setMaxSalary(checkNull(String.valueOf(viewObj[0][5])));
				reqbean
						.setPositionDate(checkNull(String
								.valueOf(viewObj[0][6])));
				reqbean.setDeptCode(checkNull(String.valueOf(viewObj[0][7])));
				reqbean.setExecutiveName(checkNull(String
						.valueOf(viewObj[0][8])));

				/*reqbean.setExecutiveName(checkNull(String
						.valueOf(viewObj[0][9])));*/
				reqbean.setWorkLocation(checkNull(String
						.valueOf(viewObj[0][9])));
				reqbean.setHiringManagerCode(checkNull(String
						.valueOf(viewObj[0][10])));

				reqbean.setHiringManagerToken(checkNull(String
						.valueOf(viewObj[0][11])));
				reqbean.setHiringManagerName(checkNull(String
						.valueOf(viewObj[0][12])));
				reqbean.setHiringManagerPhoneNumber(checkNull(String
						.valueOf(viewObj[0][13])));

				reqbean.setHiringManagerFaxNumber(checkNull(String
						.valueOf(viewObj[0][14])));

				reqbean.setHiringManagerEmailAddress(checkNull(String
						.valueOf(viewObj[0][15])));

				reqbean.setApprovedExistingJob(checkNull(String
						.valueOf(viewObj[0][16])));
				reqbean
						.setNewJobExist(checkNull(String
								.valueOf(viewObj[0][17])));
				reqbean.setHeadCount(checkNull(String.valueOf(viewObj[0][18])));
				reqbean.setReplacementType(checkNull(String
						.valueOf(viewObj[0][19])));
				reqbean.setReqReplacingCode(checkNull(String
						.valueOf(viewObj[0][20])));
				
				reqbean.setReqReplacingToken(checkNull(String
						.valueOf(viewObj[0][21])));
				reqbean.setReqReplacingName(checkNull(String
						.valueOf(viewObj[0][22])));
				
				
				reqbean.setTerminationDate(checkNull(String
						.valueOf(viewObj[0][23])));
				// reqbean.setPositionType(checkNull(String.valueOf(viewObj[0][18])));

				if (String.valueOf(viewObj[0][24]).equals("R")) {
					reqbean.setPositionType("Re");
					reqbean.setUserProfileRadioOptionValue("Re");
				} else if (String.valueOf(viewObj[0][24]).equals("T")) {
					reqbean.setPositionType("Te");
					reqbean.setUserProfileRadioOptionValue("Te");
				} else if (String.valueOf(viewObj[0][24]).equals("V")) {
					reqbean.setPositionType("Va");
					reqbean.setUserProfileRadioOptionValue("Va");
				} else {
					reqbean.setPositionType("");
					reqbean.setUserProfileRadioOptionValue("");
				}

				reqbean.setTemporaryType(checkNull(String
						.valueOf(viewObj[0][25])));
				reqbean
						.setAgencyName(checkNull(String.valueOf(viewObj[0][26])));
				reqbean.setContractorName(checkNull(String
						.valueOf(viewObj[0][27])));
				reqbean.setContractorPhoneNumber(checkNull(String
						.valueOf(viewObj[0][28])));
				reqbean.setContractorEmailAddress(checkNull(String
						.valueOf(viewObj[0][29])));
				reqbean.setMaximumBillRate(checkNull(String
						.valueOf(viewObj[0][30])));

				reqbean.setOvertimeRequired(checkNull(String
						.valueOf(viewObj[0][31])));
				reqbean.setNumberOfOvertime(checkNull(String
						.valueOf(viewObj[0][32])));
				reqbean.setDurationOfAssignment(checkNull(String
						.valueOf(viewObj[0][33])));
				reqbean.setReasonForTemporaryNeed(checkNull(String
						.valueOf(viewObj[0][34])));
				reqbean.setVariableWorkfroceRate(checkNull(String
						.valueOf(viewObj[0][35])));
				// reqbean.setUserProfile(checkNull(String.valueOf(viewObj[0][30])));

				if (String.valueOf(viewObj[0][36]).equals("Pc")) {
					reqbean.setRateType("Pc");
					reqbean.setRateTypeRadioOptionValue("Pc");
				} else if (String.valueOf(viewObj[0][36]).equals("Pp")) {
					reqbean.setRateType("Pp");
					reqbean.setRateTypeRadioOptionValue("Pp");
				} else if (String.valueOf(viewObj[0][36]).equals("Ph")) {
					reqbean.setRateType("Ph");
					reqbean.setRateTypeRadioOptionValue("Ph");
				} else {
					reqbean.setRateType("");
					reqbean.setRateTypeRadioOptionValue("");
				}

				reqbean.setDurationOfVariableAssignment(checkNull(String
						.valueOf(viewObj[0][37])));
				reqbean.setReasonForVariableWorkforceNeed(checkNull(String
						.valueOf(viewObj[0][38])));
				reqbean.setBudget(checkNull(String.valueOf(viewObj[0][39])));
				reqbean.setStdHourPerWeek(checkNull(String
						.valueOf(viewObj[0][40])));
				reqbean.setFulltimeParttime(checkNull(String
						.valueOf(viewObj[0][41])));
				reqbean.setEducationRequirements(checkNull(String
						.valueOf(viewObj[0][42])));
				reqbean.setExperienceRequirement(checkNull(String
						.valueOf(viewObj[0][43])));
				reqbean.setEssentialPositionRequirements(checkNull(String
						.valueOf(viewObj[0][44])));
				reqbean.setReason(checkNull(String.valueOf(viewObj[0][45])));
				/*reqbean.setApproverCode(checkNull(String
						.valueOf(viewObj[0][45])));
				reqbean.setApproverToken(checkNull(String
						.valueOf(viewObj[0][46])));
				reqbean.setApproverName(checkNull(String
						.valueOf(viewObj[0][47])));*/
				reqbean.setForwardedNameType(checkNull(String
						.valueOf(viewObj[0][46])));
				reqbean.setPersReqStatus(checkNull(String
						.valueOf(viewObj[0][47])));

				reqbean.setApplicationDate(checkNull(String
						.valueOf(viewObj[0][48])));
				reqbean.setTrackingNo(checkNull(String
						.valueOf(viewObj[0][49])));
				reqbean.setInitiatorCode(checkNull(String.valueOf(viewObj[0][50])));
				reqbean.setInitiatorName(checkNull(String.valueOf(viewObj[0][51])));
				reqbean.setInitiatorDate(checkNull(String.valueOf(viewObj[0][52])));
				reqbean.setDeptName(checkNull(String.valueOf(viewObj[0][53])));
				getApproverCommentList(reqbean, requestID);

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	/*public boolean sendForApprovalFunction(PersonalRequisitionApprover reqbean) {
		boolean result = false;
		if (reqbean.getRequestID().equals("")) {
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
	}*/

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

	

	public void getApprovedList(PersonalRequisitionApprover reqbean,
			HttpServletRequest request, String userId) {

		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();

			Object[][] approvedCancellationListData = null;
			ArrayList approvedCancellationList = new ArrayList();

			// Approved application Begins
			String selQuery = "SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION  , "
				+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
				+ "INNER JOIN HRMS_D1_PERSREQ_PATH on (HRMS_D1_PERSREQ_PATH.PERS_REQ_ID = HRMS_D1_PERSONAL_REQUISITION.PERSONAL_REQ_ID AND PERS_APPROVER_CODE="+userId+")"
				+ " WHERE PERS_REQ_STATUS IN( 'A' , 'F','S') "											
			+ "	ORDER BY HRMS_D1_PERSONAL_REQUISITION.APPLICATION_DATE DESC ";
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
					PersonalRequisitionApprover bean1 = new PersonalRequisitionApprover();
					bean1.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					
					bean1.setPositionTitle(checkNull(String
							.valueOf(approvedListData[i][1])));
					bean1.setRequisition(checkNull(String
							.valueOf(approvedListData[i][2])));
					
					bean1.setApplicationDate(checkNull(String
							.valueOf(approvedListData[i][3])));

					bean1.setPersonalRequisitionApprID(checkNull(String
							.valueOf(approvedListData[i][5])));
					bean1.setPositionRequiredDateItr(checkNull(String
							.valueOf(approvedListData[i][6])));
					approvedList.add(bean1);
				}
				reqbean.setApprovedVoucherIteratorList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins
			String approvedcancellationQuery = "SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION , "
				+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
				+ " LEFT JOIN HRMS_CADRE ON ( HRMS_CADRE.CADRE_ID = HRMS_D1_PERSONAL_REQUISITION.BAND_CODE) WHERE PERS_REQ_STATUS = 'X'" +
						" AND NEXT_APPR_CODE =" +userId 
			+ "	ORDER BY HRMS_D1_PERSONAL_REQUISITION.APPLICATION_DATE DESC ";
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
					PersonalRequisitionApprover bean1 = new PersonalRequisitionApprover();
					bean1.setTrackingNo(checkNull(String
							.valueOf(approvedCancellationListData[i][0])));
					bean1.setPositionTitle(checkNull(String
							.valueOf(approvedCancellationListData[i][1])));
					bean1.setRequisition(checkNull(String
							.valueOf(approvedCancellationListData[i][2])));
					
					bean1.setApplicationDate(checkNull(String
							.valueOf(approvedCancellationListData[i][3])));

					bean1.setPersonalRequisitionApprID(checkNull(String
							.valueOf(approvedCancellationListData[i][5])));
					bean1.setPositionRequiredDateItr(checkNull(String
							.valueOf(approvedCancellationListData[i][6])));
					approvedCancellationList.add(bean1);
				}
				reqbean
						.setApprovedCancellationVoucherIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
// Pending List Ends (For Manager Approver)
	


	public void getRejectedList(PersonalRequisitionApprover reqbean,
			HttpServletRequest request, String userId) {

		Object[][] rejectedListData = null;
		ArrayList rejectedList = new ArrayList();

		Object[][] rejectedCancellationListData = null;
		ArrayList rejectedCancellationList = new ArrayList();

		// Rejected application Begins
		String selQuery ="SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION ,  "
			+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
			+ " LEFT JOIN HRMS_CADRE ON ( HRMS_CADRE.CADRE_ID = HRMS_D1_PERSONAL_REQUISITION.BAND_CODE) WHERE PERS_REQ_STATUS ='R' "
			+" AND NEXT_APPR_CODE =" + userId 
			+ "	ORDER BY HRMS_D1_PERSONAL_REQUISITION.APPLICATION_DATE DESC ";
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
				PersonalRequisitionApprover bean1 = new PersonalRequisitionApprover();
				bean1.setTrackingNo(checkNull(String
						.valueOf(rejectedListData[i][0])));
				bean1.setPositionTitle(checkNull(String
						.valueOf(rejectedListData[i][1])));
				bean1.setRequisition(checkNull(String
						.valueOf(rejectedListData[i][2])));
				
				bean1.setApplicationDate(checkNull(String
						.valueOf(rejectedListData[i][3])));

				bean1.setPersonalRequisitionApprID(checkNull(String
						.valueOf(rejectedListData[i][5])));
				bean1.setPositionRequiredDateItr(checkNull(String
						.valueOf(rejectedListData[i][6])));
				rejectedList.add(bean1);
			}
			reqbean.setRejectedVoucherIteratorList(rejectedList);
		}
		// Rejected application Ends

		// Rejected cancellation application Begins
		String approvedcancellationQuery = "SELECT TRACKING_NUMBER,POSITION_TITLE ,REQUISITION  , "
			+ "TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , PERS_REQ_STATUS , PERSONAL_REQ_ID, TO_CHAR(POSITION_REQ_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_REQUISITION "
			+ " LEFT JOIN HRMS_CADRE ON ( HRMS_CADRE.CADRE_ID = HRMS_D1_PERSONAL_REQUISITION.BAND_CODE) WHERE PERS_REQ_STATUS ='Z' "+
					" AND NEXT_APPR_CODE =" +userId 
		+ "	ORDER BY HRMS_D1_PERSONAL_REQUISITION.APPLICATION_DATE DESC ";
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
				PersonalRequisitionApprover bean1 = new PersonalRequisitionApprover();
				bean1.setTrackingNo(checkNull(String
						.valueOf(rejectedCancellationListData[i][0])));
				bean1.setPositionTitle(checkNull(String
						.valueOf(rejectedCancellationListData[i][1])));
				bean1.setRequisition(checkNull(String
						.valueOf(rejectedCancellationListData[i][2])));
				
				bean1.setApplicationDate(checkNull(String
						.valueOf(rejectedCancellationListData[i][3])));

				bean1.setPersonalRequisitionApprID(checkNull(String
						.valueOf(rejectedCancellationListData[i][5])));
				bean1.setPositionRequiredDateItr(checkNull(String
						.valueOf(rejectedCancellationListData[i][6])));
				rejectedCancellationList.add(bean1);
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
	
	public boolean isUserADirectorApprover(String userId) {
		String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'D' AND APP_SETTINGS_EMP_ID = "
				+ userId;
		Object[][] hrApproverObj = getSqlModel().getSingleResult(hrApproverSql);

		if (hrApproverObj != null && hrApproverObj.length > 0) {
			return true;
		}

		return false;
	}

	public void getEmployeeDetails(String empId, PersonalRequisitionApprover bean) {
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

			bean.setHiringManagerToken(String.valueOf(data[0][0]));// employee
			// token

			bean.setHiringManagerName(checkNull(String.valueOf(data[0][1])));// employee
			// name
			/*
			 * bean.setDeptNumber(checkNull(String.valueOf(data[0][2])));// Dept
			 * Number bean.setAreaType(checkNull(String.valueOf(data[0][3])));//
			 * department
			 */
			bean.setHiringManagerPhoneNumber(checkNull(String
					.valueOf(data[0][4])));// work phone
			bean
					.setHiringManagerFaxNumber(checkNull(String
							.valueOf(data[0][5])));
			bean.setHiringManagerEmailAddress(checkNull(String
					.valueOf(data[0][6])));
			bean.setHiringManagerCode(checkNull(String.valueOf(data[0][7])));// employee
			// id

			// end of for loop
		} catch (Exception e) {
			logger.info("Exception in getEmployeeDetails------------" + e);
		}

	}

	public boolean approve(String applicationId, String userId, String nextAppr, String Comments,String status, String forwardedTo) {
		boolean flag = false;
		/**
		 * CHECK DEFOULT MANAGER
		 */
		String finalApprove = status;
		if (status.equals("F")) {
			status = "F";
		}
		if (nextAppr.equals("")) {
			nextAppr = userId;
		}
		if(finalApprove.equals("F")){
			String persReq = " UPDATE HRMS_D1_PERSONAL_REQUISITION  SET PERS_REQ_STATUS='"
				+ status + "',NEXT_APPR_CODE=" + nextAppr
				+ " , FORWARDED_TO ='"+forwardedTo+"'  WHERE 	PERSONAL_REQ_ID  =" + applicationId;
		flag = getSqlModel().singleExecute(persReq);
		}
		else {
			String persReq = " UPDATE HRMS_D1_PERSONAL_REQUISITION  SET PERS_REQ_STATUS='"
				+ status + "' ,NEXT_APPR_CODE=" + nextAppr
				+ " WHERE 	PERSONAL_REQ_ID  =" + applicationId;
		flag = getSqlModel().singleExecute(persReq);
		}
	

		if (flag) {
			// INSERT INTO PATH
			String qqq = "INSERT INTO HRMS_D1_PERSREQ_PATH(PERS_REQ_ID,PERS_APPROVER_CODE,PERS_COMMENTS,PERS_APPROVED_DATE,PERS_PATH_ID,PERS_STATUS) "
					+ "	VALUES(?,?,?,SYSDATE,(SELECT NVL(MAX(PERS_PATH_ID),0)+1 FROM HRMS_D1_PERSREQ_PATH),?)";
			Object[][] obj = new Object[1][4];
			obj[0][0] = applicationId;
			obj[0][1] = userId;
			obj[0][2] = Comments;
			obj[0][3] = status;
			flag = getSqlModel().singleExecute(qqq, obj);
		}

		return flag;
	}

	public String rejectSendBack(String applicationId, String approverComments, String userId, String status) {
		String message = "";
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_PERSONAL_REQUISITION SET PERS_REQ_STATUS ='"
				+ status + "' " 
			+" WHERE PERSONAL_REQ_ID = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);

			String qqq = "INSERT INTO HRMS_D1_PERSREQ_PATH(PERS_REQ_ID,PERS_APPROVER_CODE,PERS_COMMENTS,PERS_APPROVED_DATE,PERS_PATH_ID,PERS_STATUS) "
				+ "	VALUES(?,?,?,SYSDATE,(SELECT NVL(MAX(PERS_PATH_ID),0)+1 FROM HRMS_D1_PERSREQ_PATH),?)";
		Object[][] obj = new Object[1][4];
		obj[0][0] = applicationId;
		obj[0][1] = userId;
		obj[0][2] = approverComments;
		obj[0][3] = status;
		 getSqlModel().singleExecute(qqq, obj);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}