package org.paradyne.model.D1;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.NewHireRehireApprover;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.Utility;
import org.paradyne.model.settings.UserCreationModel;

public class NewHireRehireApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(NewHireRehireApprovalModel.class);

	// Pending list Begins (Manager approver)
	public void getPendingList(NewHireRehireApprover hrdsoftApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();

			// For Pending application Begins
			String selQuery = " SELECT TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ,"
					+ " HIRE_STATUS,	HIRE_REHIRE_ID , DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE "
					+ " 		WHERE HIRE_STATUS = 'P' "// AND HIRE_APPROVER
					// = "+userId
					+ " ORDER BY HIRE_REHIRE_ID DESC ";
			pendingListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(hrdsoftApprBean
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
				hrdsoftApprBean.setMyPage("1");

			if (pendingListData != null && pendingListData.length > 0) {
				hrdsoftApprBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					NewHireRehireApprover beanItt = new NewHireRehireApprover();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingListData[i][1])));

					beanItt.setApplicationDate(checkNull(String
							.valueOf(pendingListData[i][2])));
					beanItt.setHireStatus(checkNull(String
							.valueOf(pendingListData[i][3])));

					beanItt.setHireRehireApprovalId(checkNull(String
							.valueOf(pendingListData[i][4])));
					beanItt.setActionReasonItr(checkNull(String
							.valueOf(pendingListData[i][5])));

					pendingDataList.add(beanItt);
				}
				hrdsoftApprBean.setPendingIteratorList(pendingDataList);
			}
			// For Pending application Ends

			// pending cancellation application Begins
			String pendingCancellationQuery = " SELECT TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ,"
											+ " HIRE_STATUS, HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE "
											+ " WHERE HIRE_STATUS = 'C' AND HIRE_APPROVER = " + userId 
											+ " ORDER BY HIRE_REHIRE_ID DESC ";
			pendingCancellationListData = getSqlModel().getSingleResult(
					pendingCancellationQuery);

			String[] pagependingCancellationIndex = Utility.doPaging(
					hrdsoftApprBean.getMyPagePendingCancel(),
					pendingCancellationListData.length, 20);
			if (pageIndexDrafted == null) {
				pagependingCancellationIndex[0] = "0";
				pagependingCancellationIndex[1] = "20";
				pagependingCancellationIndex[2] = "1";
				pagependingCancellationIndex[3] = "1";
				pagependingCancellationIndex[4] = "";
			}

			request.setAttribute("totalPendingCancellationPage", Integer
					.parseInt(String.valueOf(pagependingCancellationIndex[2])));
			request.setAttribute("pendingCancellationPageNo", Integer
					.parseInt(String.valueOf(pagependingCancellationIndex[3])));
			if (pagependingCancellationIndex[4].equals("1"))
				hrdsoftApprBean.setMyPagePendingCancel("1");

			if (pendingCancellationListData != null
					&& pendingCancellationListData.length > 0) {
				hrdsoftApprBean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer
						.parseInt(pagependingCancellationIndex[1]); i++) {
					NewHireRehireApprover beanItt = new NewHireRehireApprover();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(pendingListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(pendingListData[i][1])));

					beanItt.setApplicationDate(checkNull(String
							.valueOf(pendingListData[i][2])));
					beanItt.setHireStatus(checkNull(String
							.valueOf(pendingListData[i][3])));

					beanItt.setHireRehireApprovalId(checkNull(String
							.valueOf(pendingListData[i][4])));
					
					beanItt.setActionReasonItr(checkNull(String
							.valueOf(pendingListData[i][5])));
					pendingCancellationDataList.add(beanItt);
				}
				hrdsoftApprBean
						.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// For pending cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pending List Ends (For Manager Approver)

	// Approved application List Begins (For Manager Approver)
	public void getApprovedList(NewHireRehireApprover hrdsoftApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();

			String selQuery = "SELECT TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ,"
							+ " HIRE_STATUS, HIRE_REHIRE_ID,DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE "
							+ " WHERE HIRE_STATUS = 'A' "// AND HIRE_APPROVER
					// = "+userId
					+ " ORDER BY HIRE_REHIRE_ID DESC ";
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(hrdsoftApprBean
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
				hrdsoftApprBean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				hrdsoftApprBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					NewHireRehireApprover beanItt = new NewHireRehireApprover();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(approvedListData[i][1])));

					beanItt.setApplicationDate(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setHireStatus(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setHireRehireApprovalId(checkNull(String
							.valueOf(approvedListData[i][4])));
					beanItt.setActionReasonItr(checkNull(String
							.valueOf(approvedListData[i][5])));

					approvedList.add(beanItt);
				}
				hrdsoftApprBean.setApproveredIteratorList(approvedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Approved application List Ends (For Manager Approver)

	// Rejected List Begins (For Manager Approver)
	public void getRejectedList(NewHireRehireApprover hrdsoftApprBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();

			String selQuery = "SELECT TRACKING_NUMBER,EMP_FIRST_NAME ||'  '|| EMP_MIDDLE_NAME ||'  '|| EMP_LAST_NAME , TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') ,"
							+ " HIRE_STATUS, HIRE_REHIRE_ID, DECODE(ACTION_REASON,'H','Hire ','R','ReHire','A','Acquisition') FROM HRMS_D1_NEW_HIRE_REHIRE "
							+ "	WHERE HIRE_STATUS = 'R' "// AND HIRE_APPROVER
					// 	= "+userId
					+ " ORDER BY HIRE_REHIRE_ID DESC ";
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(hrdsoftApprBean
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
				hrdsoftApprBean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				hrdsoftApprBean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					NewHireRehireApprover beanItt = new NewHireRehireApprover();
					beanItt.setTrackingNo(checkNull(String
							.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeName(checkNull(String
							.valueOf(rejectedListData[i][1])));

					beanItt.setApplicationDate(checkNull(String
							.valueOf(rejectedListData[i][2])));
					beanItt.setHireStatus(checkNull(String
							.valueOf(rejectedListData[i][3])));
					beanItt.setHireRehireApprovalId(checkNull(String
							.valueOf(rejectedListData[i][4])));
					
					beanItt.setActionReasonItr(checkNull(String
							.valueOf(rejectedListData[i][5])));
					rejectedList.add(beanItt);
				}
				hrdsoftApprBean.setRejectedIteratorList(rejectedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Rejected List Ends (For Manager Approver)

	public Object[][] getApproverCommentList(String applicationId) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, HWSW_COMMENTS, "
				+ " TO_CHAR(HWSW_APPROVED_DATE, 'DD-MM-YYYY') AS HWSW_APPROVED_DATE, "
				+ " DECODE(HWSW_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
				+ " FROM HRMS_D1_HW_SW_DATA_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_HW_SW_DATA_PATH.HWSW_APPROVER) "
				+ " WHERE HWSW_APPLICATION_ID = "
				+ applicationId
				+ " ORDER BY HWSW_PATH_ID DESC";
		return getSqlModel().getSingleResult(apprCommentListSql);
	}

	public String approve(String applicationId, String approverComments,
			String userId, String status, String nextApprover, int level) {
		String statusToUpdate = "A";
		String updateApproversql = "";

		try {
			if (status.equals("C")) {
				statusToUpdate = "X";
			} else {
				if (isUserAMagaer(applicationId, userId)) {
					statusToUpdate = "P";

					if (nextApprover.equals(userId)) {
						statusToUpdate = "F";
					}
					updateApproversql = ", HWSW_APPOVER_ID = " + nextApprover;
				}
			}

			String updateApproverCommentsSql = " UPDATE HRMS_D1_HARDWARE_SOFTWARE_REQ SET HWSW_STATUS = '"
					+ statusToUpdate
					+ "', "
					+ " HWSW_LEVEL = "
					+ level
					+ updateApproversql
					+ " WHERE HWSW_REQ_ID = "
					+ applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			insertApproverComments(applicationId, userId, approverComments,
					statusToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusToUpdate;
	}

	public String reject(String applicationId, String approverComments,
			String userId) {
		String message = "";
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_NEW_HIRE_REHIRE SET HIRE_STATUS = 'R' "
					+ " WHERE HIRE_REHIRE_ID = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(applicationId, userId, approverComments, "R");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public String sendBack(String applicationId, String approverComments,
			String userId, String nextApprover) {
		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_NEW_HIRE_REHIRE SET HIRE_STATUS = 'B',  "
					+ " HIRE_APPROVER = "
					+ nextApprover
					+ " WHERE HIRE_REHIRE_ID = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);
			insertApproverComments(applicationId, userId, approverComments, "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "B";
	}

	private void insertApproverComments(String applicationId, String userId,
			String approverComments, String statusToUpdate) {
		System.out.println("statusToUpdate ========== " + statusToUpdate);

		try {
			String insertSql = " INSERT INTO HRMS_D1_NEW_HIRE_DATA_PATH (HIRE_PATH_ID, HIRE_APPLICATION_ID, HIRE_APPROVER, HIRE_COMMENTS, HIRE_STATUS) "
					+ " VALUES ((SELECT NVL(MAX(HIRE_PATH_ID), 0) + 1 FROM HRMS_D1_NEW_HIRE_DATA_PATH), ?, ?, ?, ?) ";
			Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = userId;
			insertObj[0][2] = approverComments;
			insertObj[0][3] = statusToUpdate;
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Added by Nilesh For Rehire case to insert  path data .

	private void insertApproverCommentsRehire(String applicationId,
			String userId, String approverComments) {
		System.out.println("statusToUpdate in rehire case ========== ");

		try {
			String insertSql = " INSERT INTO HRMS_D1_NEW_HIRE_DATA_PATH (HIRE_PATH_ID, HIRE_APPLICATION_ID, HIRE_APPROVER, HIRE_COMMENTS, HIRE_STATUS) "
					+ " VALUES ((SELECT NVL(MAX(HIRE_PATH_ID), 0) + 1 FROM HRMS_D1_NEW_HIRE_DATA_PATH), ?, ?, ?, ?) ";
			Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = userId;
			insertObj[0][2] = approverComments;
			insertObj[0][3] = "A";
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isUserAMagaer(String applicationID, String userId) {
		try {
			String mgrApproverSql = " SELECT * FROM HRMS_D1_HARDWARE_SOFTWARE_REQ WHERE HWSW_REQ_ID = "
					+ applicationID + " AND HWSW_APPOVER_ID = " + userId;
			Object[][] mgrApproverObj = getSqlModel().getSingleResult(
					mgrApproverSql);
			if (mgrApproverObj != null && mgrApproverObj.length > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void viewApplicationFunction(NewHireRehireApprover bean,
			String applicationID, String typeOfList) {
		try {
			String editQuery = "SELECT HIRE_REHIRE_ID, EMP_FIRST_NAME, EMP_MIDDLE_NAME, EMP_LAST_NAME, "
					+ " EMP_SOCIAL_SECRTY_NUMBER, EMP_SOCIAL_INSURANCE_NUMBER, EMP_ADDRESS,  "
					+ " EMP_CITY, EMP_STATE, EMP_COUNTRY, EMP_PIN_CODE,  "
					+ " EMP_HOME_PHONE, EMP_REQ_NUMBER, EMP_SEX, EMP_MARITAL_STATUS,  "
					+ " EMP_EDUCATION,QUA_NAME, TO_CHAR(EMP_BIRTH_DATE,'DD-MM-YYYY'), EMP_REFFERAL_SOURCE,REFERRAL_SOURCE_NAME,  "
					+ " EMP_ETHNIC_GRP, ETHINICITY AS ETHNIC_NAME , TO_CHAR(HIRE_DATE,'DD-MM-YYYY'), ACTION_REASON, JOB_CODE, JOB_TITLE,"
					+ " TO_CHAR(ACQUISITION_DATE,'DD-MM-YYYY'), ACQUIRED_COMPANY,TO_CHAR(PREACQUSITION_DATE,'DD-MM-YYYY'), PHY_WRK_LOCATION,  "
					+ " HOME_SHIFT_CODE, HOME_SHIFT.SHIFT_NAME AS HOME_SHIFT_NAME ,  "
					+ " HOME_REG_TEMP, HOME_FLSA_STATUS, CUSTOMER_NAME, CUST_PHY_ADDRESS,  "
					+ " CUST_CITY, CUST_STATE, CUST_PIN_CODE, CUST_SHIFT_CODE,"
					+ "CUST_SHIFT.SHIFT_NAME AS CUST_SHIFT_NAME , CUST_REG_TEMP,  "
					+ " CUST_FLSA_STATUS,  "
					+ " HRMS_D1_NEW_HIRE_REHIRE.SALARY_PLAN, PAY_GROUP, GRADE,CADRE_NAME, WEEKLY_HOURS, BIWEEKLY_SALARY, ANNUAL_SALARY, DEPT_CODE,  "
					+ " EXECUTIVE_CODE,RANK_NAME, OFFICE_CITY, OFFICE_STATE, "
					+ "HIRE_APPROVER,OFFC1.EMP_TOKEN AS APPROVER_TOKEN,   "
					+ " OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME,   "
					+ "  TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') , HIRE_STATUS,HIRE_LEVEL,TRACKING_NUMBER ,HIRE_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(HIRE_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') "
					+ " , NVL(HRMS_DEPT.DEPT_NAME||' - '||DEPT_ABBR, ' '),EXE_EMP_CODE, EXECUTIVE.EMP_TOKEN, EXECUTIVE.EMP_FNAME || ' ' || EXECUTIVE.EMP_MNAME || ' ' || EXECUTIVE.EMP_LNAME AS ENAME,HIRE_TOKEN, CENTER_NAME, SHIFT_NAME, EMP_RANK.RANK_NAME AS EMP_RANK , DIV_NAME, ADD_EMAIL, FULL_PART_TIME, CUST_FULL_PART_TIME,TYPE_NAME,ACTION_REASON ,HIRE_EMAIL_ADD  from HRMS_D1_NEW_HIRE_REHIRE "
					+ " LEFT JOIN HRMS_SHIFT HOME_SHIFT ON(HOME_SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.HOME_SHIFT_CODE) "
					// + " LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE =
					// HRMS_D1_NEW_HIRE_REHIRE.EMP_CITY) "
					+ " LEFT JOIN HRMS_SHIFT SHIFT ON(SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.CUST_SHIFT_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.HIRE_APPROVER) "
					+ " LEFT JOIN HRMS_QUA ON (HRMS_QUA.QUA_ID = HRMS_D1_NEW_HIRE_REHIRE.EMP_EDUCATION) "
					+ " LEFT JOIN HRMS_D1_REFERRAL on (HRMS_D1_REFERRAL.REFERRAL_ID =HRMS_D1_NEW_HIRE_REHIRE.EMP_REFFERAL_SOURCE) "
					// + " LEFT JOIN HRMS_LOCATION CUST_CITY
					// ON(CUST_CITY.LOCATION_CODE =
					// HRMS_D1_NEW_HIRE_REHIRE.CUST_CITY) "
					// + " LEFT JOIN HRMS_LOCATION DECISION_CITY
					// ON(DECISION_CITY.LOCATION_CODE =
					// HRMS_D1_NEW_HIRE_REHIRE.DECISION_CITY) "
					// + " LEFT JOIN HRMS_SHIFT DECISION_SHIFT
					// ON(DECISION_SHIFT.SHIFT_ID =
					// HRMS_D1_NEW_HIRE_REHIRE.DECISION_SHIFT_CODE) "
					+ " LEFT JOIN HRMS_SHIFT CUST_SHIFT ON(CUST_SHIFT.SHIFT_ID = HRMS_D1_NEW_HIRE_REHIRE.CUST_SHIFT_CODE) "
					+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID =HRMS_D1_NEW_HIRE_REHIRE.GRADE) "
					// + " LEFT JOIN HRMS_LOCATION OFFICE_CITY
					// ON(OFFICE_CITY.LOCATION_CODE =
					// HRMS_D1_NEW_HIRE_REHIRE.OFFICE_CITY) "
					+ " LEFT JOIN HRMS_D1_ETHIC ON (HRMS_D1_ETHIC.ETHIC_CODE = HRMS_D1_NEW_HIRE_REHIRE.EMP_ETHNIC_GRP) "
					// + " LEFT JOIN HRMS_D1_HIRE_ZIP ON
					// (HRMS_D1_HIRE_ZIP.ZIP_ID =
					// HRMS_D1_NEW_HIRE_REHIRE.CUST_PIN_CODE) "
					+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_D1_NEW_HIRE_REHIRE.EXECUTIVE_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.HIRE_INITIATOR)  "
					+ " LEFT JOIN HRMS_EMP_OFFC EXECUTIVE ON(EXECUTIVE.EMP_ID = HRMS_D1_NEW_HIRE_REHIRE.EXE_EMP_CODE)  "
					+ " LEFT JOIN HRMS_DEPT  ON(HRMS_DEPT.DEPT_ID=HRMS_D1_NEW_HIRE_REHIRE.DEPT_CODE )"

					+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_CENTER) "
					+ " LEFT JOIN HRMS_SHIFT  ON(HRMS_SHIFT.SHIFT_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_SHIFT) "
					+ " LEFT JOIN HRMS_RANK EMP_RANK ON(EMP_RANK.RANK_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_RANK) "
					+ " LEFT JOIN HRMS_DIVISION  ON(HRMS_DIVISION.DIV_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_DIV) "
					+ " left JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_D1_NEW_HIRE_REHIRE.EMP_TYPE)  "
					+ "  WHERE HIRE_REHIRE_ID = " + applicationID;
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				bean.setHireRehireApprovalId(checkNull(String
						.valueOf(editObj[0][0])));
				bean.setEmpFirstName(checkNull(String.valueOf(editObj[0][1])));
				bean.setEmpMiddleName(checkNull(String.valueOf(editObj[0][2])));
				bean.setEmpLastName(checkNull(String.valueOf(editObj[0][3])));
				bean.setSocialSecurityNumber(checkNull(String
						.valueOf(editObj[0][4])));
				bean.setSocialInsuranceNumber(checkNull(String
						.valueOf(editObj[0][5])));
				bean
						.setEmpHomeAddress(checkNull(String
								.valueOf(editObj[0][6])));
				// bean.setCityId(checkNull(String.valueOf(editObj[0][7])));
				bean.setCityName(checkNull(String.valueOf(editObj[0][7])));

				bean.setStateName(checkNull(String.valueOf(editObj[0][8])));
				bean.setCountry(checkNull(String.valueOf(editObj[0][9])));
				bean.setZip(checkNull(String.valueOf(editObj[0][10])));
				bean.setHomePhoneNumber(checkNull(String
						.valueOf(editObj[0][11])));
				bean.setReqNumber(checkNull(String.valueOf(editObj[0][12])));
				bean.setSex(checkNull(String.valueOf(editObj[0][13])));
				bean
						.setMaritalStatus(checkNull(String
								.valueOf(editObj[0][14])));
				bean.setQualCode(checkNull(String.valueOf(editObj[0][15])));
				bean.setQualifyName(checkNull(String.valueOf(editObj[0][16])));
				bean.setBirthDate(checkNull(String.valueOf(editObj[0][17])));
				bean.setMediumCode(checkNull(String.valueOf(editObj[0][18])));
				bean.setMediumName(checkNull(String.valueOf(editObj[0][19])));
				bean.setCastCode(checkNull(String.valueOf(editObj[0][20])));
				bean.setCastName(checkNull(String.valueOf(editObj[0][21])));
				bean.setHireDate(checkNull(String.valueOf(editObj[0][22])));
				bean.setActionReason(checkNull(String.valueOf(editObj[0][23])));

				bean.setJobCode(checkNull(String.valueOf(editObj[0][24])));
				bean.setJobTitle(checkNull(String.valueOf(editObj[0][25])));
				bean.setAcquisitionDate(checkNull(String
						.valueOf(editObj[0][26])));
				bean.setAcquiredCompany(checkNull(String
						.valueOf(editObj[0][27])));
				bean.setPreacqusitionDate(checkNull(String
						.valueOf(editObj[0][28])));
				// bean.setUserProfile(checkNull(String.valueOf(editObj[0][30])));

				if (String.valueOf(editObj[0][29]).equals("H")) {
					bean.setUserProfile("Ho");
					bean.setUserProfileRadioOptionValue("Ho");
				} else if (String.valueOf(editObj[0][29]).equals("T")) {
					bean.setUserProfile("Tr");
					bean.setUserProfileRadioOptionValue("Tr");
				} else if (String.valueOf(editObj[0][29]).equals("C")) {
					bean.setUserProfile("Cu");
					bean.setUserProfileRadioOptionValue("Cu");
				} else if (String.valueOf(editObj[0][29]).equals("D")) {
					bean.setUserProfile("De");
					bean.setUserProfileRadioOptionValue("De");
				} else if (String.valueOf(editObj[0][29]).equals("V")) {
					bean.setUserProfile("Va");
					bean.setUserProfileRadioOptionValue("Va");
				} else {
					bean.setUserProfile("");
					bean.setUserProfileRadioOptionValue("");
				}
				bean.setShiftCode(checkNull(String.valueOf(editObj[0][30])));
				bean.setShiftType(checkNull(String.valueOf(editObj[0][31])));
				bean.setRegTemp(checkNull(String.valueOf(editObj[0][32])));
				bean.setFlsaStatus(checkNull(String.valueOf(editObj[0][33])));
				bean.setCustomerName(checkNull(String.valueOf(editObj[0][34])));
				bean.setPhysicalAddress(checkNull(String
						.valueOf(editObj[0][35])));
				// bean.setCustCityId(checkNull(String.valueOf(editObj[0][37])));
				bean.setCustCityName(checkNull(String.valueOf(editObj[0][36])));
				bean
						.setCustStateName(checkNull(String
								.valueOf(editObj[0][37])));
				bean.setCustZipCode(checkNull(String.valueOf(editObj[0][38])));
				// bean.setCustZipCode(checkNull(String.valueOf(editObj[0][41])));
				bean
						.setCustShiftCode(checkNull(String
								.valueOf(editObj[0][39])));
				bean
						.setCustShiftType(checkNull(String
								.valueOf(editObj[0][40])));
				bean.setCustRegTemp(checkNull(String.valueOf(editObj[0][41])));
				bean
						.setCustflsaStatus(checkNull(String
								.valueOf(editObj[0][42])));
				bean.setSalaryPlan(checkNull(String.valueOf(editObj[0][43])));
				System.out.println("##################" + editObj[0][43]);
				bean.setPayGroup(checkNull(String.valueOf(editObj[0][44])));
				bean.setCadreCode(checkNull(String.valueOf(editObj[0][45])));
				bean.setCadreName(checkNull(String.valueOf(editObj[0][46])));
				bean.setWeeklyHours(checkNull(String.valueOf(editObj[0][47])));
				bean
						.setBiweeklySalary(checkNull(String
								.valueOf(editObj[0][48])));
				bean.setAnnualSalary(checkNull(String.valueOf(editObj[0][49])));
				bean.setDeptCode(checkNull(String.valueOf(editObj[0][50])));
				bean
						.setExecutiveCode(checkNull(String
								.valueOf(editObj[0][51])));
				bean
						.setExecutiveName(checkNull(String
								.valueOf(editObj[0][52])));
				// bean.setOfficeCityId(checkNull(String.valueOf(editObj[0][55])));
				bean
						.setOfficeCityName(checkNull(String
								.valueOf(editObj[0][53])));
				bean.setOfficeStateName(checkNull(String
						.valueOf(editObj[0][54])));
				bean.setApproverCode(checkNull(String.valueOf(editObj[0][55])));
				bean
						.setApproverToken(checkNull(String
								.valueOf(editObj[0][56])));
				bean.setApproverName(checkNull(String.valueOf(editObj[0][57])));
				bean.setApplicationDate(checkNull(String
						.valueOf(editObj[0][58])));
				bean.setStatus(checkNull(String.valueOf(editObj[0][59])));
				bean.setTrackingNo(checkNull(String.valueOf(editObj[0][61])));
				bean
						.setInitiatorCode(checkNull(String
								.valueOf(editObj[0][62])));
				bean
						.setInitiatorName(checkNull(String
								.valueOf(editObj[0][63])));
				bean
						.setInitiatorDate(checkNull(String
								.valueOf(editObj[0][64])));
				bean.setDeptName(checkNull(String.valueOf(editObj[0][65])));

				bean.setExeEmployeeCode(checkNull(String
						.valueOf(editObj[0][66])));
				bean.setExeEmployeeToken(checkNull(String
						.valueOf(editObj[0][67])));
				bean.setExeEmployeeName(checkNull(String
						.valueOf(editObj[0][68])));

				bean.setEmpToken(checkNull(String.valueOf(editObj[0][69])));

				bean.setCenterName(checkNull(String.valueOf(editObj[0][70])));
				bean
						.setShiftTypeAppr(checkNull(String
								.valueOf(editObj[0][71])));

				bean.setRankName(checkNull(String.valueOf(editObj[0][72])));
				bean.setDivName(checkNull(String.valueOf(editObj[0][73])));
				bean.setEmailId(checkNull(String.valueOf(editObj[0][74])));

				bean.setFulltimeParttime(checkNull(String
						.valueOf(editObj[0][75])));
				bean.setCustfulltimeParttime(checkNull(String
						.valueOf(editObj[0][76])));
				bean.setEmpTypeName(checkNull(String.valueOf(editObj[0][77])));
				bean.setActionType(checkNull(String.valueOf(editObj[0][78])));

				if (bean.getActionType().equals("null")
						|| bean.getActionType().equals("")
						|| bean.getActionType().equals("H")
						|| bean.getActionType().equals("A")) {
					System.out.println("heloooooooooooooo");
					bean.setDisplayFlag("true");

				} else {
					System.out.println("hi111111111111111111");
					bean.setDisplayFlag("false");
					bean
							.setActionType(checkNull(String
									.valueOf(editObj[0][78])));
				}

				bean.setEmailAddress(checkNull(String.valueOf(editObj[0][79])));
				getApproverCommentList(bean, applicationID);

				for (int i = 0; i < editObj.length; i++) {
					for (int j = 0; j < editObj[i].length; j++) {
						logger.info("editObj[" + i + "][" + j + "]  "
								+ editObj[i][j]);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getApproverCommentList(NewHireRehireApprover bean,
			String requestID) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, HIRE_COMMENTS, "
				+ " TO_CHAR(HIRE_APPROVED_DATE, 'DD-MM-YYYY') AS HIRE_APPROVED_DATE, "
				+ " DECODE(HIRE_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
				+ " FROM HRMS_D1_NEW_HIRE_DATA_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_NEW_HIRE_DATA_PATH.HIRE_APPROVER) "
				+ " WHERE HIRE_APPLICATION_ID = "
				+ requestID
				+ " ORDER BY HIRE_APPLICATION_ID DESC";

		Object[][] apprCommentListObj = getSqlModel().getSingleResult(
				apprCommentListSql);
		ArrayList approverList = new ArrayList<Object>(
				apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			bean.setApproverCommentsFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				NewHireRehireApprover innerBean = new NewHireRehireApprover();
				innerBean.setApprName(checkNull(String
						.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(checkNull(String
						.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(checkNull(String
						.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(checkNull(String
						.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}

	public boolean isITDeptApprover(String userId) {
		String ITApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'H' AND APP_SETTINGS_EMP_ID = "
				+ userId;
		Object[][] ITApproverObj = getSqlModel().getSingleResult(ITApproverSql);

		if (ITApproverObj != null && ITApproverObj.length > 0) {
			return true;
		}
		return false;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	// / added ganesh new

	public boolean approveApplicationFunction(NewHireRehireApprover bean,
			String status, String actionType) {
		String applicationId = bean.getHireRehireApprovalId();
		System.out.println("applicationId  ======" + applicationId);
		String approverComments = bean.getApproverComments();
		String approverID = bean.getUserEmpId();
		System.out.println("actionType  ======" + actionType);
		boolean result = false;
		try {
			/*
			 * if(reqApproverBean.getStatus().equals("P")) { status = "A"; }else {
			 * status = "X"; }
			 */

			String changeStatusQuery = "UPDATE HRMS_D1_NEW_HIRE_REHIRE SET HIRE_STATUS = '"
					+ status + "'" + " WHERE HIRE_REHIRE_ID = " + applicationId;
			result = getSqlModel().singleExecute(changeStatusQuery);
			if (actionType.equals("R")) {

				System.out
						.println("hello friends:::::::::::::::::::::::::::::::::::::::::::::::::::::");
				insertApproverCommentsRehire(applicationId, approverID,
						approverComments);

			} else {
				insertApproverComments(applicationId, approverID,
						approverComments, status);
			}

			if (status.equals("A")) {
				insertHireRehireData(bean, applicationId);
				insertHireData(bean, applicationId);
				createUserName(bean);
			}

			if (actionType.equals("R")) {
				updateEmpData(bean, applicationId);

				String code = bean.getNewHiredEmployeeCode();
				String changeRehireStatusQuery = "UPDATE HRMS_D1_NEW_HIRE_REHIRE SET HIRE_STATUS = 'A' ,HIRE_EMP_ID = "
						+ code + " WHERE HIRE_REHIRE_ID = " + applicationId;
				getSqlModel().singleExecute(changeRehireStatusQuery);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void createUserName(NewHireRehireApprover bean) {
		String empId = bean.getEmpId();
		String query = "SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ') ,NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') "
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_LOGIN ON (HRMS_LOGIN.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID ="
				+ empId
				+ " AND HRMS_EMP_OFFC.EMP_STATUS='S' ";
		Object[][] data = getSqlModel().getSingleResult(query);
		String userName = "";
		if (data != null && data.length > 0) {
			userName = String.valueOf(data[0][1]).trim() + "."
					+ String.valueOf(data[0][2]).trim();
		}

		userName = checkUserName(userName);

		String password = "pass@123";
		int passWord = 8;
		String passQuery = "SELECT NVL(PWD_MIN_LENGTH,'8') FROM HRMS_SETTINGS_PWD";
		Object[][] passObj = getSqlModel().getSingleResult(passQuery);
		if (passObj != null && passObj.length > 0) {
			passWord = Integer.parseInt(String.valueOf(passObj[0][0]));
		}
		UserCreationModel passModel = new UserCreationModel();
		passModel.initiate(context, session);
		password = passModel.getRandomPassword(passWord);
		try {
			password = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(password); // encrypted
			// password
		} catch (Exception e) {
			// TODO: handle exception
		}
		String insertQuery = "INSERT INTO HRMS_LOGIN(LOGIN_CODE,EMP_ID,PROFILE_CODE,LOGIN_NAME,LOGIN_PASSWORD,LOCK_FLAG,LOGIN_CREATE_DATE,LOGIN_PWD_CHANGE_DATE,EMP_ACTIVE) "
				+ " VALUES((SELECT NVL(MAX(LOGIN_CODE),0)+1 FROM HRMS_LOGIN),?,68,?,?,'N',SYSDATE,SYSDATE,'Y') ";

		logger.info("insert 1======" + userName);
		Object[][] finalObj = new Object[1][3];
		finalObj[0][0] = empId;// emp_id
		finalObj[0][1] = String.valueOf(userName);// username
		finalObj[0][2] = password;// password
		getSqlModel().singleExecute(insertQuery, finalObj);

	}

	private String checkUserName(String value) {
		String result = value;
		String selQuery = "SELECT LOGIN_NAME FROM HRMS_LOGIN WHERE LOWER(LOGIN_NAME)=LOWER('"
				+ result + "')";
		Object[][] obj = getSqlModel().getSingleResult(selQuery);
		if (obj != null && obj.length > 0) {
			result = result + "1";
			result = checkUserName(result);

		} else {
			return result;
		}
		return result;

	}

	// INSERT DATA IN HRMS_EMP_OFFC
	public boolean insertHireRehireData(NewHireRehireApprover bean,
			String applicationId) {

		boolean result = false;
		try {
			Object addObj[][] = new Object[1][15];
			addObj[0][0] = bean.getEmpToken();
			addObj[0][1] = bean.getEmpFirstName();
			addObj[0][2] = bean.getEmpMiddleName();
			addObj[0][3] = bean.getEmpLastName();

			if (bean.getDeptCode() != null && !bean.getDeptCode().equals("")) {
				addObj[0][4] = bean.getDeptCode().trim();
			} else {
				addObj[0][4] = 0;
			}

			if (bean.getDivCode() != null && !bean.getDivCode().equals("")) {
				addObj[0][5] = bean.getDivCode().trim();
			} else {
				addObj[0][5] = 0;
			}
			if (bean.getCenterCode() != null
					&& !bean.getCenterCode().equals("")) {
				addObj[0][6] = bean.getCenterCode().trim();
			} else {
				addObj[0][6] = 0;
			}
			if (bean.getRankCode() != null && !bean.getRankCode().equals("")) {
				addObj[0][7] = bean.getRankCode().trim();
			} else {
				addObj[0][7] = 0;
			}
			if (bean.getShiftCodeAppr() != null
					&& !bean.getShiftCodeAppr().equals("")) {
				addObj[0][8] = bean.getShiftCodeAppr().trim();
			} else {
				addObj[0][8] = 0;
			}
			addObj[0][9] = bean.getHireDate();
			addObj[0][10] = String.valueOf("S");
			addObj[0][11] = bean.getEmpTypeCode();
			addObj[0][12] = bean.getSex();
			addObj[0][13] = bean.getExeEmployeeCode();
			addObj[0][14] = bean.getBirthDate();

			String empIdQuery = "SELECT MAX(EMP_ID)+1 FROM HRMS_EMP_OFFC";
			Object[][] empData = getSqlModel().getSingleResult(empIdQuery);
			bean.setEmpId(String.valueOf(empData[0][0]));

			String insertSql = "INSERT INTO HRMS_EMP_OFFC(EMP_ID,EMP_TOKEN,EMP_FNAME, EMP_MNAME, EMP_LNAME, "
					+ "EMP_DEPT,  EMP_DIV,EMP_CENTER, EMP_RANK,EMP_SHIFT,EMP_REGULAR_DATE,EMP_STATUS,EMP_TYPE,EMP_GENDER,EMP_REPORTING_OFFICER,EMP_DOB) "
					+ "values("
					+ bean.getEmpId()
					+ ",?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'))";

			result = getSqlModel().singleExecute(insertSql, addObj);

			/*
			 * for(int i = 0; i < addObj.length; i++) { for(int j = 0; j <
			 * addObj[i].length; j++) { logger.info("updateObj[" + i + "][" + j + "] " +
			 * addObj[i][j]); } }
			 */

			Object[][] currentAddress = new Object[1][9];
			currentAddress[0][0] = bean.getEmpId();// Emp Id
			currentAddress[0][1] = String.valueOf("P");// Address Type

			currentAddress[0][2] = "";// City Id
			currentAddress[0][3] = bean.getStateName();// State
			currentAddress[0][4] = bean.getCountry();// Country
			currentAddress[0][5] = bean.getZip();// Pin Code
			currentAddress[0][6] = bean.getHomePhoneNumber();// HomePhoneNumber
			currentAddress[0][7] = bean.getEmailId();// EmailId
			currentAddress[0][8] = bean.getEmpHomeAddress();// empHomeAddress

			String currAddress = "INSERT INTO HRMS_EMP_ADDRESS(EMP_ID,ADD_TYPE,ADD_CITY,ADD_STATE,ADD_CNTRY,ADD_PINCODE,ADD_PH1,ADD_EMAIL,ADD_1) VALUES (?,?,?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(currAddress, currentAddress);

			/*
			 * for(int i = 0; i < currentAddress.length; i++) { for(int j = 0; j <
			 * currentAddress[i].length; j++) { logger.info("currentAddress[" +
			 * i + "][" + j + "] " + currentAddress[i][j]); } }
			 */

			Object[][] maritialStatus = new Object[1][2];
			maritialStatus[0][0] = bean.getEmpId();// Emp Id
			maritialStatus[0][1] = bean.getMaritalStatus();// MaritalStatus

			String maritialStatusQuery = "INSERT INTO HRMS_EMP_PERS(EMP_ID,EMP_MARITAL_STATUS) VALUES (?,?)";
			getSqlModel().singleExecute(maritialStatusQuery, maritialStatus);

			/*
			 * for(int i = 0; i < maritialStatus.length; i++) { for(int j = 0; j <
			 * maritialStatus[i].length; j++) { logger.info("maritialStatus[" +
			 * i + "][" + j + "] " + maritialStatus[i][j]); } }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// INSERT DATA IN HRMS_D1_NEW_HIRE_REHIRE
	public boolean insertHireData(NewHireRehireApprover bean,
			String applicationId) {

		boolean result = false;
		try {
			Object addObj[][] = new Object[1][8];
			addObj[0][0] = bean.getEmpToken();

			if (bean.getDivCode() != null && !bean.getDivCode().equals("")) {
				addObj[0][1] = bean.getDivCode().trim();
			} else {
				addObj[0][1] = 0;
			}
			if (bean.getCenterCode() != null
					&& !bean.getCenterCode().equals("")) {
				addObj[0][2] = bean.getCenterCode().trim();
			} else {
				addObj[0][2] = 0;
			}
			if (bean.getRankCode() != null && !bean.getRankCode().equals("")) {
				addObj[0][3] = bean.getRankCode().trim();
			} else {
				addObj[0][3] = 0;
			}
			if (bean.getShiftCodeAppr() != null
					&& !bean.getShiftCodeAppr().equals("")) {
				addObj[0][4] = bean.getShiftCodeAppr().trim();
			} else {
				addObj[0][4] = 0;
			}
			addObj[0][5] = bean.getEmailId();
			addObj[0][6] = bean.getEmpId();
			addObj[0][7] = bean.getEmpTypeCode();

			String insertSql = "UPDATE HRMS_D1_NEW_HIRE_REHIRE SET  HIRE_TOKEN = ? , "
					+ " EMP_DIV =  ? ,EMP_CENTER = ?, EMP_RANK = ?,EMP_SHIFT = ?,ADD_EMAIL = ? ,HIRE_EMP_ID=? ,EMP_TYPE=? "
					+ " WHERE HIRE_REHIRE_ID= " + applicationId;

			result = getSqlModel().singleExecute(insertSql, addObj);

			/*
			 * for(int i = 0; i < addObj.length; i++) { for(int j = 0; j <
			 * addObj[i].length; j++) { logger.info("updateObj[" + i + "][" + j + "] " +
			 * addObj[i][j]); } }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Added by Nilesh

	public boolean updateEmpData(NewHireRehireApprover bean,
			String applicationId) {

		boolean result = false;
		try {
			Object addObj[][] = new Object[1][9];
			addObj[0][0] = bean.getNewHiredEmployeeToken();

			if (bean.getDivCode() != null && !bean.getDivCode().equals("")) {
				addObj[0][1] = bean.getDivCode().trim();
			} else {
				addObj[0][1] = 0;
			}
			if (bean.getCenterCode() != null
					&& !bean.getCenterCode().equals("")) {
				addObj[0][2] = bean.getCenterCode().trim();
			} else {
				addObj[0][2] = 0;
			}
			if (bean.getRankCode() != null && !bean.getRankCode().equals("")) {
				addObj[0][3] = bean.getRankCode().trim();
			} else {
				addObj[0][3] = 0;
			}
			if (bean.getShiftCodeAppr() != null
					&& !bean.getShiftCodeAppr().equals("")) {
				addObj[0][4] = bean.getShiftCodeAppr().trim();
			} else {
				addObj[0][4] = 0;
			}

			addObj[0][5] = bean.getNewHiredEmployeeCode();
			addObj[0][6] = bean.getEmpTypeCode();
			addObj[0][7] = bean.getExeEmployeeCode();
			addObj[0][8] = bean.getDeptCode();

			String updateSql = "UPDATE hrms_emp_offc SET  EMP_TOKEN = ? , "
					+ " EMP_DIV =  ? ,EMP_CENTER = ?, EMP_RANK = ?,EMP_SHIFT = ?, emp_id=? ,EMP_TYPE=? , EMP_REPORTING_OFFICER =? ,EMP_DEPT=?, EMP_STATUS='S' "
					+ " WHERE emp_id = " + bean.getNewHiredEmployeeCode();

			result = getSqlModel().singleExecute(updateSql, addObj);

			// To insert email id and emp id into hrms_emp_address table

			Object[][] data = new Object[1][2];
			data[0][0] = bean.getNewHiredEmployeeCode().trim();
			data[0][1] = bean.getEmailId().trim();

			String empCode = String.valueOf(data[0][0]);
			System.out.println("empCode------ " + empCode);
			System.out.println("data[0][0]-------" + data[0][0]);
			System.out.println("data[0][1]-------" + data[0][1]);

			String conditionQuery = "select EMP_ID from hrms_emp_address where EMP_ID like ("
					+ empCode + ")";
			Object[][] conditionData = getSqlModel().getSingleResult(
					conditionQuery);
			System.out
					.println("conditionData.length here we get ======================="
							+ conditionData.length);
			if (conditionData.length == 0) {
				System.out.println("inside if auery===================");
				String emailInsertQuery = "INSERT INTO hrms_emp_address (EMP_ID ,ADD_EMAIL,ADD_TYPE) values (?,?,'P')";
				getSqlModel().singleExecute(emailInsertQuery, data);
			} else {
				System.out
						.println("inside update query --------------------------"
								+ empCode);
				String updateEmailQuery = "UPDATE HRMS_EMP_ADDRESS SET ADD_EMAIL = '"
						+ bean.getEmailId() + "' WHERE emp_id = " + empCode;
				getSqlModel().getSingleResult(updateEmailQuery);

			}

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					logger.info("data[" + i + "][" + j + "]  " + data[i][j]);
				}
			}

			for (int i = 0; i < addObj.length; i++) {
				for (int j = 0; j < addObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  "
							+ addObj[i][j]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkTokenExit(String empToken) {
		String check = "SELECt EMP_TOKEN FROm HRMS_EMP_OFFC where EMP_TOKEN ='"
				+ empToken + "' ";
		Object[][] obj = getSqlModel().getSingleResult(check);
		if (obj != null && obj.length > 0) {
			return true;
		}
		return false;
	}

	public void getEmpValues(NewHireRehireApprover bean) {

		String query = "select EMP_DIV,DIV_NAME,EMP_CENTER,CENTER_NAME,EMP_RANK,RANK_NAME,EMP_SHIFT,SHIFT_NAME,EMP_TYPE ,TYPE_NAME, "
				+ " EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME),EMP_ID from  hrms_emp_offc "
				+ " left join hrms_division on(hrms_division.DIV_ID = hrms_emp_offc.EMP_DIV)		 "
				+ " left join hrms_center on(hrms_center.CENTER_ID = hrms_emp_offc.EMP_CENTER)"
				+ " left join hrms_rank on(hrms_rank.RANK_ID = hrms_emp_offc.EMP_RANK) "
				+ " left join hrms_shift on(hrms_shift.SHIFT_ID = hrms_emp_offc.EMP_SHIFT)"
				+ " left join hrms_emp_type on(hrms_emp_type.TYPE_ID = hrms_emp_offc.EMP_TYPE) "
				+ " where EMP_STATUS IN ('N','R','E','A') AND  hrms_emp_offc.EMP_ID ="
				+ bean.getNewHiredEmployeeCode();

		Object[][] setData = getSqlModel().getSingleResult(query);

		if (setData != null && setData.length > 0) {
			bean.setDivCode(checkNull(String.valueOf(setData[0][0])));
			bean.setDivName(checkNull(String.valueOf(setData[0][1])));

			bean.setCenterCode(checkNull(String.valueOf(setData[0][2])));
			bean.setCenterName(checkNull(String.valueOf(setData[0][3])));

			bean.setRankCode(checkNull(String.valueOf(setData[0][4])));
			bean.setRankName(checkNull(String.valueOf(setData[0][5])));

			bean.setShiftCodeAppr(checkNull(String.valueOf(setData[0][6])));
			bean.setShiftTypeAppr(checkNull(String.valueOf(setData[0][7])));

			bean.setEmpTypeCode(checkNull(String.valueOf(setData[0][8])));
			bean.setEmpTypeName(checkNull(String.valueOf(setData[0][9])));

			bean.setNewHiredEmployeeToken(checkNull(String
					.valueOf(setData[0][10])));
			bean.setNewHiredEmployeeName(checkNull(String
					.valueOf(setData[0][11])));
			bean.setNewHiredEmployeeCode(checkNull(String
					.valueOf(setData[0][12])));

			for (int i = 0; i < setData.length; i++) {
				System.out.println(" getEmpValues are::::::" + setData[0][i]);
			}
		}
	}

	public void setEmailId(NewHireRehireApprover bean) {

		String mailQuery = " select ADD_EMAIL from   hrms_emp_address where EMP_ID ="
				+ bean.getNewHiredEmployeeCode();
		Object[][] mailData = getSqlModel().getSingleResult(mailQuery);
		if (mailData != null && mailData.length > 0) {
			System.out.println("mailData[0][0] ======  " + mailData[0][0]);
			bean.setEmailId(checkNull(String.valueOf(mailData[0][0])));

		}
	}

	public void viewOfficialDetails(NewHireRehireApprover bean, String code) {

		String officeQuery = " select EMP_DIV,DIV_NAME,EMP_CENTER,CENTER_NAME,EMP_RANK,RANK_NAME,EMP_SHIFT,SHIFT_NAME,EMP_TYPE ,TYPE_NAME, "
				+ "  EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME),EMP_ID , hrms_emp_address.ADD_EMAIL from  hrms_emp_offc "
				+ "   left join hrms_division on(hrms_division.DIV_ID = hrms_emp_offc.EMP_DIV) "
				+ "  left join hrms_center on(hrms_center.CENTER_ID = hrms_emp_offc.EMP_CENTER) "
				+ "  left join hrms_rank on(hrms_rank.RANK_ID = hrms_emp_offc.EMP_RANK) "
				+ "  left join hrms_shift on(hrms_shift.SHIFT_ID = hrms_emp_offc.EMP_SHIFT) "
				+ "  left join hrms_emp_type on(hrms_emp_type.TYPE_ID = hrms_emp_offc.EMP_TYPE) "
				+ "  left join hrms_emp_address on(hrms_emp_address.EMP_ID = hrms_emp_offc.EMP_ID)"
				+ "  where EMP_STATUS = 'S' AND  hrms_emp_offc.EMP_ID = "
				+ code;
		Object[][] offcData = getSqlModel().getSingleResult(officeQuery);
		if (offcData != null && offcData.length > 0) {
			bean.setDivCode(checkNull(String.valueOf(offcData[0][0])));
			bean.setDivName(checkNull(String.valueOf(offcData[0][1])));

			bean.setCenterCode(checkNull(String.valueOf(offcData[0][2])));
			bean.setCenterName(checkNull(String.valueOf(offcData[0][3])));

			bean.setRankCode(checkNull(String.valueOf(offcData[0][4])));
			bean.setRankName(checkNull(String.valueOf(offcData[0][5])));

			bean.setShiftCodeAppr(checkNull(String.valueOf(offcData[0][6])));
			bean.setShiftTypeAppr(checkNull(String.valueOf(offcData[0][7])));

			bean.setEmpTypeCode(checkNull(String.valueOf(offcData[0][8])));
			bean.setEmpTypeName(checkNull(String.valueOf(offcData[0][9])));
			bean.setEmpToken(checkNull(String.valueOf(offcData[0][10])));
			bean.setNewHiredEmployeeName(checkNull(String
					.valueOf(offcData[0][11])));
			bean.setNewHiredEmployeeCode(checkNull(String
					.valueOf(offcData[0][12])));
			bean.setEmailId(checkNull(String.valueOf(offcData[0][13])));

		}

	}

}
