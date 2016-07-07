package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.PersonalDataChangeApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class PersonalDataChangeApprovalModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeApprovalModel.class);

	public String approve(String applicationId, String approverComments, String userId, String status, String nextApprover, int level) {
		String statusToUpdate = "A";
		String updateApproversql = "";

		if(status.equals("C")) {
			statusToUpdate = "X";
		} else {
			/*if(isUserAMagaer(applicationId, userId)) {
				statusToUpdate = "P";
				
				if(nextApprover.equals(userId)) {
					statusToUpdate = "F";
				}
			
				updateApproversql = ", PERS_APPROVER = " + nextApprover;
			}*/

			if(status.equals("Pending")){
							statusToUpdate = "F";
						}
		}
		
		String updateApproverCommentsSql = " UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET PERS_STATUS = '" + statusToUpdate + "', "
			+ " PERS_LEVEL = " + level + updateApproversql + " WHERE PERS_CHANGE_ID = " + applicationId;
		getSqlModel().singleExecute(updateApproverCommentsSql);

		insertApproverComments(applicationId, userId, approverComments, statusToUpdate);
		if(statusToUpdate.equals("A")){
			updatePersonalData(applicationId);
		}
		return statusToUpdate;
	}

	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public List getApprovedApplicationList(String pageForApprovedApp, HttpServletRequest request) {

		List approvedAppList = null;

		try {
			String approvedAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
				+ " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID WHERE PERS_STATUS = 'A' " + " ORDER BY PERS_EFFECTIVE_DATE ";
			Object[][] approvedAppObj = getSqlModel().getSingleResult(approvedAppSql);

			if(approvedAppObj != null && approvedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForApprovedApp, approvedAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				approvedAppList = new ArrayList(approvedAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					PersonalDataChangeApproval bean = new PersonalDataChangeApproval();
					bean.setPersDataChangeApproverId(String.valueOf(approvedAppObj[i][0]));
					bean.setEmpToken(String.valueOf(approvedAppObj[i][1]));
					bean.setEmpName(String.valueOf(approvedAppObj[i][2]));
					bean.setApplicationDate(String.valueOf(approvedAppObj[i][3]));
					approvedAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}

		return approvedAppList;
	}

	public List getApprovedApplicationList(String userId, String pageForApprovedApp, HttpServletRequest request) {

		List approvedAppList = null;

		try {
			String approvedAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS IN ('F', 'A') AND PERS_APPROVER = " + userId + " ORDER BY PERS_EFFECTIVE_DATE ";
			Object[][] approvedAppObj = getSqlModel().getSingleResult(approvedAppSql);

			if(approvedAppObj != null && approvedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForApprovedApp, approvedAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				approvedAppList = new ArrayList(approvedAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					PersonalDataChangeApproval bean = new PersonalDataChangeApproval();
					bean.setPersDataChangeApproverId(String.valueOf(approvedAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(approvedAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(approvedAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(approvedAppObj[i][3])));
					approvedAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}

		return approvedAppList;
	}

	public Object[][] getApproverCommentList(String persDataChangeId) {
		String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, PERS_COMMENTS, "
			+ " TO_CHAR(PERS_DATE, 'DD-MM-YYYY') AS PERS_DATE, "
			+ " DECODE(PERS_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS "
			+ " FROM HRMS_D1_PERS_DATA_PATH PATH " + " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.PERS_APPROVER) "
			+ " WHERE PERS_CHANGE_ID = " + persDataChangeId + " ORDER BY PERS_PATH_ID DESC";
		return getSqlModel().getSingleResult(apprCommentListSql);
	}

	public List getForwardedApplicationList(String pageForForwardedApp, HttpServletRequest request) {

		List forwardedAppList = null;

		try {
			String pendingAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'F' ORDER BY PERS_EFFECTIVE_DATE ";
			Object[][] pendingAppObj = getSqlModel().getSingleResult(pendingAppSql);

			if(pendingAppObj != null && pendingAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForForwardedApp, pendingAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				forwardedAppList = new ArrayList(pendingAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					PersonalDataChangeApproval bean = new PersonalDataChangeApproval();
					bean.setPersDataChangeApproverId(String.valueOf(pendingAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(pendingAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(pendingAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(pendingAppObj[i][3])));
					forwardedAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}

		return forwardedAppList;
	}

	public List getPendingApplicationList(String userId, String pageForPendingApp, HttpServletRequest request) {

		List pendingAppList = null;

		try {
			String pendingAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'P' AND PERS_APPROVER = " + userId + " ORDER BY PERS_EFFECTIVE_DATE ";
			Object[][] pendingAppObj = getSqlModel().getSingleResult(pendingAppSql);

			if(pendingAppObj != null && pendingAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingApp, pendingAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingAppList = new ArrayList(pendingAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					PersonalDataChangeApproval bean = new PersonalDataChangeApproval();
					bean.setPersDataChangeApproverId(String.valueOf(pendingAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(pendingAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(pendingAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(pendingAppObj[i][3])));
					pendingAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}

		return pendingAppList;
	}

	public List getPendingCancellationApplicationList(String pageForPendingCancelApp, HttpServletRequest request) {
		List pendingCancelAppList = null;

		try {
			String pendingCancelAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
				+ " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID WHERE PERS_STATUS = 'C' " + " ORDER BY PERS_EFFECTIVE_DATE ";
			Object[][] pendingCancelAppObj = getSqlModel().getSingleResult(pendingCancelAppSql);

			if(pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingCancelApp, pendingCancelAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingCancelAppList = new ArrayList(pendingCancelAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					PersonalDataChangeApproval bean = new PersonalDataChangeApproval();
					bean.setPersDataChangeApproverId(String.valueOf(pendingCancelAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(pendingCancelAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(pendingCancelAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(pendingCancelAppObj[i][3])));
					pendingCancelAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}

		return pendingCancelAppList;
	}

	public List getPendingCancellationApplicationList(String userId, String pageForPendingCancelApp, HttpServletRequest request) {
		List pendingCancelAppList = null;

		try {
			String pendingCancelAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'C' AND PERS_APPROVER = " + userId + " ORDER BY PERS_EFFECTIVE_DATE ";
			Object[][] pendingCancelAppObj = getSqlModel().getSingleResult(pendingCancelAppSql);

			if(pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingCancelApp, pendingCancelAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingCancelAppList = new ArrayList(pendingCancelAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					PersonalDataChangeApproval bean = new PersonalDataChangeApproval();
					bean.setPersDataChangeApproverId(String.valueOf(pendingCancelAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(pendingCancelAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(pendingCancelAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(pendingCancelAppObj[i][3])));
					pendingCancelAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}

		return pendingCancelAppList;
	}

	public Object[][] getPersDataChangeDetails(String persDataChangeId) {
		String getDetailsSql = " SELECT PERS_APPROVER_COMMENTS, PERS_APPROVER, PERS_STATUS FROM HRMS_D1_PERSONAL_DATA_CHANGE "
			+ " WHERE PERS_CHANGE_ID = " + persDataChangeId;
		return getSqlModel().getSingleResult(getDetailsSql);
	}

	public List getRejectedApplicationList(String pageForRejectedApp, HttpServletRequest request) {

		List rejectedAppList = null;

		try {
			String rejectedAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, "
				+ " TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
				+ " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID WHERE PERS_STATUS = 'R' " + " ORDER BY PERS_EFFECTIVE_DATE ";
			Object[][] rejectedAppObj = getSqlModel().getSingleResult(rejectedAppSql);

			if(rejectedAppObj != null && rejectedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForRejectedApp, rejectedAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				rejectedAppList = new ArrayList(rejectedAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					PersonalDataChangeApproval bean = new PersonalDataChangeApproval();
					bean.setPersDataChangeApproverId(String.valueOf(rejectedAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(rejectedAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(rejectedAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(rejectedAppObj[i][3])));
					rejectedAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}

		return rejectedAppList;
	}

	public List getRejectedApplicationList(String userId, String pageForRejectedApp, HttpServletRequest request) {

		List rejectedAppList = null;

		try {
			String rejectedAppSql = " SELECT PERS_CHANGE_ID, HRMS_EMP_OFFC.EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME  "
				+ " AS EMP_NAME, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
				+ " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE PERS_STATUS = 'R' AND PERS_APPROVER = " + userId
				+ " ORDER BY PERS_EFFECTIVE_DATE ";
			Object[][] rejectedAppObj = getSqlModel().getSingleResult(rejectedAppSql);

			if(rejectedAppObj != null && rejectedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForRejectedApp, rejectedAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				rejectedAppList = new ArrayList(rejectedAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					PersonalDataChangeApproval bean = new PersonalDataChangeApproval();
					bean.setPersDataChangeApproverId(String.valueOf(rejectedAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(rejectedAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(rejectedAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(rejectedAppObj[i][3])));
					rejectedAppList.add(bean);
				}
			}
		} catch(Exception e) {
			logger.error(e);
		}

		return rejectedAppList;
	}

	private void insertApproverComments(String applicationId, String userId, String approverComments, String statusToUpdate) {
		String insertSql = " INSERT INTO HRMS_D1_PERS_DATA_PATH (PERS_PATH_ID, PERS_CHANGE_ID, PERS_APPROVER, PERS_COMMENTS, PERS_STATUS) "
			+ " VALUES ((SELECT NVL(MAX(PERS_PATH_ID), 0) + 1 FROM HRMS_D1_PERS_DATA_PATH), ?, ?, ?, ?) ";

		Object[][] insertObj = new Object[1][4];
		insertObj[0][0] = applicationId;
		insertObj[0][1] = userId;
		insertObj[0][2] = approverComments;
		insertObj[0][3] = statusToUpdate;

		getSqlModel().singleExecute(insertSql, insertObj);
	}

	public boolean isUserAHRApprover(String userId) {
		String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'H' AND APP_SETTINGS_EMP_ID = " + userId;
		Object[][] hrApproverObj = getSqlModel().getSingleResult(hrApproverSql);

		if(hrApproverObj != null && hrApproverObj.length > 0) { return true; }

		return false;
	}

	public boolean isUserAMagaer(String persDataChangeId, String userId) {
		String mgrApproverSql = " SELECT * FROM HRMS_D1_PERSONAL_DATA_CHANGE WHERE PERS_CHANGE_ID = " + persDataChangeId + " AND PERS_APPROVER = "
			+ userId;
		Object[][] mgrApproverObj = getSqlModel().getSingleResult(mgrApproverSql);

		if(mgrApproverObj != null && mgrApproverObj.length > 0) { return true; }

		return false;
	}

	public String reject(String persDataChangeId, String approverComments, String userId) {
		String message = "";

		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET PERS_STATUS = 'R' " 
			+" WHERE PERS_CHANGE_ID = " + persDataChangeId;
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(persDataChangeId, userId, approverComments, "R");
		} catch(Exception e) {
			logger.error(e);
		}

		return message;
	}

	public String sendBack(String persDataChangeId, String approverComments, String userId, String nextApprover) {
		String updateApproverCommentsSql = " UPDATE HRMS_D1_PERSONAL_DATA_CHANGE SET PERS_STATUS = 'B', PERS_LEVEL = 1 "
		+ " WHERE PERS_CHANGE_ID = " + persDataChangeId;
		getSqlModel().singleExecute(updateApproverCommentsSql);

		insertApproverComments(persDataChangeId, userId, approverComments, "B");
		
		return "B";
	}

	private void updatePersonalData(String persDataChangeId) {
		System.out.println("IN EMP OFFICE---- GANESH-----------" + persDataChangeId);
		String personalDataSql = " SELECT PERS_EMP_ID, PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, PERS_STATE, "
			+ " PERS_COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, PERS_WORK_PHONE , PERS_EMAIL_ID , PERS_STREET_ADDRESS,PERS_EMP_DEPT  FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
			+ " WHERE PDC.PERS_CHANGE_ID = " + persDataChangeId;
		Object[][] personalDataObj = getSqlModel().getSingleResult(personalDataSql);

		String empId = String.valueOf(personalDataObj[0][0]);
		String firstName = checkNull(String.valueOf(personalDataObj[0][1]));
		String middleName = checkNull(String.valueOf(personalDataObj[0][2]));
		String lastName = checkNull(String.valueOf(personalDataObj[0][3]));
		String maritalStatus = checkNull(String.valueOf(personalDataObj[0][4]));
		String city = checkNull(String.valueOf(personalDataObj[0][5]));
		String state = checkNull(String.valueOf(personalDataObj[0][6]));
		String country = checkNull(String.valueOf(personalDataObj[0][7]));
		String pinCode = checkNull(String.valueOf(personalDataObj[0][8]));
		String homePhone = checkNull(String.valueOf(personalDataObj[0][9]));
		String workPhone = checkNull(String.valueOf(personalDataObj[0][10]));
		String emailAddress = checkNull(String.valueOf(personalDataObj[0][11]));
		String streetAddress = checkNull(String.valueOf(personalDataObj[0][12]));
		String department = checkNull(String.valueOf(personalDataObj[0][13]));

		boolean offcDtlsToModify = false;
		String updateOffcialDetailsSql = " UPDATE HRMS_EMP_OFFC SET ";

		if(!firstName.equals("")) {
			updateOffcialDetailsSql += " EMP_FNAME = '" + firstName + "'";
			offcDtlsToModify = true;
		}

		if(!middleName.equals("")) {
			if(offcDtlsToModify) {
				updateOffcialDetailsSql += ", EMP_MNAME = '" + middleName + "'";
			} else {
				updateOffcialDetailsSql += " EMP_MNAME = '" + middleName + "'";
			}

			offcDtlsToModify = true;
		}

		if(!lastName.equals("")) {
			if(offcDtlsToModify) {
				updateOffcialDetailsSql += ", EMP_LNAME = '" + lastName + "'";
			} else {
				updateOffcialDetailsSql += " EMP_LNAME = '" + lastName + "'";
			}

			offcDtlsToModify = true;
		}
		
		if(!department.equals("")) {
			if(offcDtlsToModify) {
				updateOffcialDetailsSql += ", EMP_DEPT = '" + department + "'";
			} else {
				updateOffcialDetailsSql += " EMP_DEPT = '" + department + "'";
			}

			offcDtlsToModify = true;
		}
		
		
		

		if(offcDtlsToModify) {
			updateOffcialDetailsSql += " WHERE EMP_ID = " + empId;
			System.out.println("updateOffcialDetailsSql--" + updateOffcialDetailsSql);
			getSqlModel().singleExecute(updateOffcialDetailsSql);
		}

		if(!maritalStatus.equals("")) {
			String updatePersonalDetailsSql = " UPDATE HRMS_EMP_PERS SET EMP_MARITAL_STATUS = '" + maritalStatus + "' WHERE EMP_ID = " + empId;
			System.out.println("updatePersonalDetailsSql--" + updatePersonalDetailsSql);
			getSqlModel().singleExecute(updatePersonalDetailsSql);
		}

		boolean addrDtlToModify = false;
		String updateAddressDetailsSql = " UPDATE HRMS_EMP_ADDRESS SET ";

	/*	if(!city.equals("")) {
			updateAddressDetailsSql += " ADD_CITY = ''";
			addrDtlToModify = true;
		}*/

		if(!state.equals("")) {
			if(addrDtlToModify) {
				updateAddressDetailsSql += ", ADD_STATE = '" + state + "'";
			} else {
				updateAddressDetailsSql += " ADD_STATE = '" + state + "'";
			}
			addrDtlToModify = true;
		}

		if(!country.equals("")) {
			if(addrDtlToModify) {
				updateAddressDetailsSql += ", ADD_CNTRY = '" + country + "'";
			} else {
				updateAddressDetailsSql += " ADD_CNTRY = '" + country + "'";
			}
			addrDtlToModify = true;
		}

		if(!pinCode.equals("")) {
			if(addrDtlToModify) {
				updateAddressDetailsSql += ", ADD_PINCODE = '" + pinCode+ "'";
			} else {
				updateAddressDetailsSql += " ADD_PINCODE = '" + pinCode+ "'";
			}
			addrDtlToModify = true;
		}

		if(!workPhone.equals("")) {
			if(addrDtlToModify) {
				updateAddressDetailsSql += ", ADD_PH1 = '" + workPhone + "'";
			} else {
				updateAddressDetailsSql += " ADD_PH1 = '" + workPhone + "'";
			}
			addrDtlToModify = true;
		}

		if(!homePhone.equals("")) {
			if(addrDtlToModify) {
				updateAddressDetailsSql += ", ADD_PH2 = '" + homePhone + "'";
			} else {
				updateAddressDetailsSql += " ADD_PH2 = '" + homePhone + "'";
			}
			addrDtlToModify = true;
		}
		
		if(!emailAddress.equals("")) {
			if(addrDtlToModify) {
				updateAddressDetailsSql += ", ADD_EMAIL = '" + emailAddress + "'";
			} else {
				updateAddressDetailsSql += " ADD_EMAIL = '" + emailAddress + "'";
			}
			addrDtlToModify = true;
		}
		if(!streetAddress.equals("")) {
			if(addrDtlToModify) {
				updateAddressDetailsSql += ", ADD_1 = '" + streetAddress + "'";
			} else {
				updateAddressDetailsSql += " ADD_1 = '" + streetAddress + "'";
			}
			addrDtlToModify = true;
		}
		
		if(addrDtlToModify) {
			updateAddressDetailsSql += " WHERE ADD_TYPE = 'P' AND EMP_ID = " + empId;
			System.out.println("updateAddressDetailsSql--" + updateAddressDetailsSql);
			getSqlModel().singleExecute(updateAddressDetailsSql);
		}
	}
	// new added

	
	// Pending list Begins (Manager approver) 
	public void getPendingList(PersonalDataChangeApproval bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			

			// For Pending application Begins
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'P' AND HWSW_APPOVER_ID = "+userId
					+" ORDER BY HWSW_REQ_ID DESC ";*/
			
			String selQuery = " SELECT PERS_CHANGE_ID, TRACKING_NUMBER, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'P' AND PERS_APPROVER = " + userId + " ORDER BY PERS_CHANGE_ID DESC";
			
			
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
					PersonalDataChangeApproval bean1 = new PersonalDataChangeApproval();
					bean1.setPersDataChangeApproverId(String.valueOf(pendingListData[i][0]));
					bean1.setTrackingNo(checkNull(String.valueOf(pendingListData[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(pendingListData[i][2])));
					bean1.setApplicationDate(checkNull(String.valueOf(pendingListData[i][3])));
					pendingDataList.add(bean1);
				}
				bean.setPendingIteratorList(pendingDataList);
			}
			// For Pending application Ends

			// pending cancellation application Begins
			/*String pendingCancellationQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'C' AND HWSW_APPOVER_ID = "+userId
					+" ORDER BY HWSW_REQ_ID DESC";*/
			
			String pendingCancellationQuery = " SELECT PERS_CHANGE_ID, TRACKING_NUMBER, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'C' AND PERS_APPROVER = " + userId + " ORDER BY PERS_CHANGE_ID ";
			
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
					PersonalDataChangeApproval bean1 = new PersonalDataChangeApproval();
					bean1.setPersDataChangeApproverId(String.valueOf(pendingListData[i][0]));
					bean1.setTrackingNo(checkNull(String.valueOf(pendingListData[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(pendingListData[i][2])));
					bean1.setApplicationDate(checkNull(String.valueOf(pendingListData[i][3])));
					pendingCancellationDataList.add(bean1);
				}
				bean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// For pending cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPendingList(PersonalDataChangeApproval bean,
			HttpServletRequest request) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			
			// For Forwarded application Begins
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'F' ORDER BY HWSW_REQ_ID DESC ";*/
			
			String selQuery = " SELECT PERS_CHANGE_ID, TRACKING_NUMBER, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'F' OR (PERS_STATUS='P' AND PERS_APPROVER = "+bean.getUserEmpId() +") ORDER BY PERS_CHANGE_ID DESC";
			
			
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
					PersonalDataChangeApproval bean1 = new PersonalDataChangeApproval();
					bean1.setPersDataChangeApproverId(String.valueOf(pendingListData[i][0]));
					bean1.setTrackingNo(checkNull(String.valueOf(pendingListData[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(pendingListData[i][2])));
					bean1.setApplicationDate(checkNull(String.valueOf(pendingListData[i][3])));
					pendingDataList.add(bean1);
				}
				bean.setPendingIteratorList(pendingDataList);
			}
			// For Pending application Ends 

			// pending cancellation application Begins
			/*String pendingCancellationQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'C' ORDER BY HWSW_REQ_ID DESC";*/
			
			String pendingCancellationQuery = " SELECT PERS_CHANGE_ID, TRACKING_NUMBER, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'C'  ORDER BY PERS_CHANGE_ID ";
			
			
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
					PersonalDataChangeApproval bean1 = new PersonalDataChangeApproval();
					bean1.setPersDataChangeApproverId(String.valueOf(pendingListData[i][0]));
					bean1.setTrackingNo(checkNull(String.valueOf(pendingListData[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(pendingListData[i][2])));
					bean1.setApplicationDate(checkNull(String.valueOf(pendingListData[i][3])));
					pendingCancellationDataList.add(bean1);
				}
				bean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// For pending cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
		
	public void viewApplicationFunction(PersonalDataChangeApproval bean,
			String applicationID, String typeOfList) {
		try {
			String query = " SELECT PERS_CHANGE_ID, PERS_EMP_ID, OFFC.EMP_TOKEN, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ENAME, "
				+ " DEPTNO.DEPT_NUMBER, PERS_DEPT_CODE,DEPT.DEPT_NAME, PERS_EMP_PNONE_NUMBER, PERS_FIRST_NAME, PERS_MIDDLE_NAME, PERS_LAST_NAME, PERS_MARITAL_STATUS, PERS_CITY, "
				+ " PERS_STATE, PERS_COUNTRY, PERS_PIN_CODE, PERS_HOME_PHONE, "
				+ " PERS_WORK_PHONE, TO_CHAR(PERS_EFFECTIVE_DATE, 'DD-MM-YYYY') AS EFFECTIVE_DATE, PERS_EMGCY_NAME, REL.RELATION_CODE AS REL_CODE, "
				+ " REL.RELATION_NAME AS REL_NAME, PDC.PERS_EMGCY_REL_HOME_PHONE AS REL_HOME_PHONE, PDC.PERS_EMGCY_REL_WORK_PHONE AS REL_WORK_PHONE, "
				+ " DECODE(PDC.PERS_EMGCY_REL_SAME_ADDR,'Y','Yes ','N','No') AS REL_SAME_ADDR, PDC.PERS_EMGCY_REL_ADDR, DECODE(PDC.PERS_STATUS,'D','Draft','P','Pending','B','Sent Back','A','Approved','R','Rejected',	'N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected'), PDC.PERS_APPROVER, "
				+ " OFFC1.EMP_TOKEN AS APPROVER_TOKEN,  OFFC1.EMP_FNAME || ' ' || OFFC1.EMP_MNAME || ' ' || OFFC1.EMP_LNAME AS APPROVER_NAME, "
				+ " PERS_LEVEL,PERS_INITIATOR,INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME, TO_CHAR(PERS_INITIATOR_DATE,'DD-MM-YYYY HH24:MI') ,TO_CHAR(APPLICATION_DATE,'DD-MM-YYYY') "
				+ " , PERS_STREET_ADDRESS,PERS_EMAIL_ID,PERS_EMP_DEPT,PESR.DEPT_NAME FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = PDC.PERS_EMP_ID) "
				+ " LEFT JOIN HRMS_DEPT DEPT ON(DEPT.DEPT_ID = OFFC.EMP_DEPT) "
				+ " LEFT JOIN HRMS_D1_DEPARTMENT DEPTNO ON(DEPTNO.DEPT_ID = OFFC.EMP_DEPT_NO) "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ADDR ON (ADDR.EMP_ID = OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = PDC.PERS_APPROVER)  "
				+ " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = PDC.PERS_INITIATOR)  "
				+" LEFT JOIN HRMS_DEPT  ON(HRMS_DEPT.DEPT_ID=PDC.PERS_DEPT_CODE )"
				+" 	LEFT JOIN HRMS_DEPT PESR  ON(PESR.DEPT_ID=PDC.PERS_EMP_DEPT ) "
				+ " LEFT JOIN HRMS_RELATION REL ON(REL.RELATION_CODE = PDC.PERS_EMGCY_REL) WHERE PERS_CHANGE_ID = " + applicationID;

			Object[][] data = getSqlModel().getSingleResult(query);

			try {
				bean.setPersDataChangeApproverId(checkNull(String.valueOf(data[0][0])));
				bean.setEmployeeCode(checkNull(String.valueOf(data[0][1])));
				bean.setEmployeeToken(checkNull(String.valueOf(data[0][2])));
				bean.setEmployeeName(checkNull(String.valueOf(data[0][3])));
				bean.setDeptNumber(checkNull(String.valueOf(data[0][4])));
				bean.setDeptCode(checkNull(String.valueOf(data[0][5])));
				bean.setDeptName(checkNull(String.valueOf(data[0][6])));
				
				bean.setWorkPhone(checkNull(String.valueOf(data[0][7])));
				bean.setFirstName(checkNull(String.valueOf(data[0][8])));
				bean.setInitialName(checkNull(String.valueOf(data[0][9])));
				bean.setLastName(checkNull(String.valueOf(data[0][10])));
				bean.setMaritalStatus(checkNull(String.valueOf(data[0][11])));
				//bean.setCityId(checkNull(String.valueOf(data[0][11])));
				bean.setCityName(checkNull(String.valueOf(data[0][12])));
				bean.setStateName(checkNull(String.valueOf(data[0][13])));
				bean.setCountry(checkNull(String.valueOf(data[0][14])));
				bean.setZip(checkNull(String.valueOf(data[0][15])));
				bean.setHomePhoneNumber(checkNull(String.valueOf(data[0][16])));
				bean.setWorkPhoneNumber(checkNull(String.valueOf(data[0][17])));
				bean.setMoveDate(checkNull(String.valueOf(data[0][18])));
				bean.setEmergencyName(checkNull(String.valueOf(data[0][19])));
				bean.setRelationCode(checkNull(String.valueOf(data[0][20])));
				bean.setRelationName(checkNull(String.valueOf(data[0][21])));
				bean.setHomePhoneNumberEmergency(checkNull(String.valueOf(data[0][22])));
				bean.setWorkPhoneNumberEmergency(checkNull(String.valueOf(data[0][23])));
				bean.setSameAddressType(checkNull(String.valueOf(data[0][24])));
				bean.setRelationAddress(checkNull(String.valueOf(data[0][25])));
			//	System.out.println("status : "+String.valueOf(data[0][26]));
				bean.setApplnStatus(checkNull(String.valueOf(data[0][26])));
				bean.setApproverCode(checkNull(String.valueOf(data[0][27])));
				bean.setApproverToken(checkNull(String.valueOf(data[0][28])));
				bean.setSelectapproverName(checkNull(String.valueOf(data[0][29])));
				bean.setLevel(checkNull(String.valueOf(data[0][30])));
				
				bean.setInitiatorCode(checkNull(String.valueOf(data[0][31])));
				bean.setInitiatorName(checkNull(String.valueOf(data[0][32])));
				bean.setInitiatorDate(checkNull(String.valueOf(data[0][33])));
				bean.setApplicationDate(checkNull(String.valueOf(data[0][34])));
				bean.setStreetAddress(checkNull(String.valueOf(data[0][35])));
				bean.setEmailAddress(checkNull(String.valueOf(data[0][36])));
				bean.setEmpDeptCode(checkNull(String.valueOf(data[0][37])));
				bean.setEmpDeptName(checkNull(String.valueOf(data[0][38])));
				
			} catch(Exception e) {
				e.printStackTrace();
			}

		} catch(Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}
	}
	
	// Rejected List Begins (FOR IT APPROVER == Final Approver)
	public void getRejectedList(PersonalDataChangeApproval bean,
			HttpServletRequest request) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'R' ORDER BY HWSW_REQ_ID DESC";*/
			
			String selQuery =" SELECT PERS_CHANGE_ID, TRACKING_NUMBER, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'R'  ORDER BY PERS_EFFECTIVE_DATE DESC";
			
			
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(bean
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
				bean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				bean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					PersonalDataChangeApproval bean1 = new PersonalDataChangeApproval();
					bean1.setPersDataChangeApproverId(String.valueOf(rejectedListData[i][0]));
					bean1.setTrackingNo(checkNull(String.valueOf(rejectedListData[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(rejectedListData[i][2])));
					bean1.setApplicationDate(checkNull(String.valueOf(rejectedListData[i][3])));
					rejectedList.add(bean1);
				}
				bean.setRejectedIteratorList(rejectedList);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Rejected List Ends (FOR IT APPROVER == Final Approver)
	
	
	// Rejected List Begins (For Manager Approver)
	public void getRejectedList(PersonalDataChangeApproval bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'R' AND HWSW_APPOVER_ID = "+userId
					+" ORDER BY HWSW_REQ_ID DESC";*/
			
			String selQuery = " SELECT PERS_CHANGE_ID, TRACKING_NUMBER, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'R' AND PERS_APPROVER = " + userId + " ORDER BY PERS_CHANGE_ID DESC";
			
			
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(bean
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
				bean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				bean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					PersonalDataChangeApproval bean1 = new PersonalDataChangeApproval();
					bean1.setPersDataChangeApproverId(String.valueOf(rejectedListData[i][0]));
					bean1.setTrackingNo(checkNull(String.valueOf(rejectedListData[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(rejectedListData[i][2])));
					bean1.setApplicationDate(checkNull(String.valueOf(rejectedListData[i][3])));
					rejectedList.add(bean1);
				}
				bean.setRejectedIteratorList(rejectedList);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Rejected List Ends (For Manager Approver)
	
	// Approved application List Begins (FOR IT APPROVER == Final Approver)
	public void getApprovedList(PersonalDataChangeApproval bean,
			HttpServletRequest request) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS = 'A' ORDER BY HWSW_REQ_ID DESC";*/
			
			String selQuery = " SELECT PERS_CHANGE_ID, TRACKING_NUMBER, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS = 'A'  ORDER BY PERS_CHANGE_ID DESC";
			
			
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(bean
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
				bean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					PersonalDataChangeApproval bean1 = new PersonalDataChangeApproval();
					bean1.setPersDataChangeApproverId(String.valueOf(approvedListData[i][0]));
					bean1.setTrackingNo(checkNull(String.valueOf(approvedListData[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(approvedListData[i][2])));
					bean1.setApplicationDate(checkNull(String.valueOf(approvedListData[i][3])));
					approvedList.add(bean1);
				}
				bean.setApproveredIteratorList(approvedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Approved application List Ends (FOR IT APPROVER == Final Approver)
	
	
	
	
	// Approved application List Begins (For Manager Approver)
	public void getApprovedList(PersonalDataChangeApproval bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			
			/*String selQuery = " SELECT HWSW_REQ_ID, EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, TO_CHAR(HWSW_REQUIRED_DATE,'DD-MM-YYYY'), "
					+ " HWSW_STATUS FROM HRMS_D1_HARDWARE_SOFTWARE_REQ "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =  HRMS_D1_HARDWARE_SOFTWARE_REQ.HWSW_REQUESTER_ID) "
					+ " WHERE HWSW_STATUS IN ('F', 'A') AND HWSW_APPOVER_ID = "+userId
					+" ORDER BY HWSW_REQ_ID DESC";*/
			
			String selQuery = " SELECT PERS_CHANGE_ID, TRACKING_NUMBER, "
				+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(APPLICATION_DATE, 'DD-MM-YYYY') "
				+ " FROM HRMS_D1_PERSONAL_DATA_CHANGE PDC " + " INNER JOIN HRMS_EMP_OFFC ON PDC.PERS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " WHERE PERS_STATUS IN ('F','A') AND PERS_APPROVER = " + userId + " ORDER BY PERS_CHANGE_ID DESC ";
			
			
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(bean
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
				bean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					PersonalDataChangeApproval bean1 = new PersonalDataChangeApproval();
					bean1.setPersDataChangeApproverId(String.valueOf(approvedListData[i][0]));
					bean1.setTrackingNo(checkNull(String.valueOf(approvedListData[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(approvedListData[i][2])));
					bean1.setApplicationDate(checkNull(String.valueOf(approvedListData[i][3])));
					approvedList.add(bean1);
				}
				bean.setApproveredIteratorList(approvedList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Approved application List Ends (For Manager Approver)
}