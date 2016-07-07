/**
 * 
 */
package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.CDRomApprovalBean;
import org.paradyne.bean.D1.TravelApplicationAppBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1439
 * 
 */
public class TravelApplicationAppModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PersonalDataChangeApprovalModel.class);

	public boolean isUserAHRApprover(String userId) {
		String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE IN ('H','R') AND APP_SETTINGS_EMP_ID = "
				+ userId;
		Object[][] hrApproverObj = getSqlModel().getSingleResult(hrApproverSql);

		if (hrApproverObj != null && hrApproverObj.length > 0) {
			return true;
		}

		return false;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public List getForwardedApplicationList(String pageForForwardedApp,
			HttpServletRequest request) {

		List forwardedAppList = null;

		try {

			String pendingAppSql = " SELECT TRAVEL_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "  EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(TRAVEL_APPLICATION_DATE, 'DD-MM-YYYY') "
					+ " FROM HRMS_D1_TRAVEL_REQUEST travel  "
					+ " INNER JOIN HRMS_EMP_OFFC ON travel.TRAVEL_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " WHERE TRAVEL_APPROVER_STATUS = 'F' ORDER BY travel.TRAVEL_ID DESC ";

			Object[][] pendingAppObj = getSqlModel().getSingleResult(
					pendingAppSql);

			if (pendingAppObj != null && pendingAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForForwardedApp,
						pendingAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingApp", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingApp", Integer
						.parseInt(String.valueOf(pageIndex[3])));

				forwardedAppList = new ArrayList(pendingAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					TravelApplicationAppBean bean = new TravelApplicationAppBean();
					bean.setTravelId(String.valueOf(pendingAppObj[i][0]));
					bean.setEmpToken(checkNull(String
							.valueOf(pendingAppObj[i][1])));
					bean.setEmpName(checkNull(String
							.valueOf(pendingAppObj[i][2])));
					bean.setApplicationDate(checkNull(String
							.valueOf(pendingAppObj[i][3])));
					forwardedAppList.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return forwardedAppList;
	}

	public List getPendingCancellationApplicationList(
			String pageForPendingCancelApp, HttpServletRequest request) {
		List pendingCancelAppList = null;

		try {

			String pendingCancelAppSql = " SELECT TRAVEL_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "  EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(TRAVEL_APPLICATION_DATE, 'DD-MM-YYYY') "
					+ " FROM HRMS_D1_TRAVEL_REQUEST travel  "
					+ " INNER JOIN HRMS_EMP_OFFC ON travel.TRAVEL_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " WHERE TRAVEL_APPROVER_STATUS = 'C'  travel.TRAVEL_ID DESC ";
			Object[][] pendingCancelAppObj = getSqlModel().getSingleResult(
					pendingCancelAppSql);

			if (pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingCancelApp,
						pendingCancelAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingCancelApp", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingCancelApp", Integer
						.parseInt(String.valueOf(pageIndex[3])));

				pendingCancelAppList = new ArrayList(pendingCancelAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					TravelApplicationAppBean bean = new TravelApplicationAppBean();
					bean.setTravelId(String.valueOf(pendingCancelAppObj[i][0]));
					bean.setEmpToken(checkNull(String
							.valueOf(pendingCancelAppObj[i][1])));
					bean.setEmpName(checkNull(String
							.valueOf(pendingCancelAppObj[i][2])));
					bean.setApplicationDate(checkNull(String
							.valueOf(pendingCancelAppObj[i][3])));
					pendingCancelAppList.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return pendingCancelAppList;
	}

	public List getPendingApplicationList(String userId,
			String pageForPendingApp, HttpServletRequest request) {

		List pendingAppList = null;

		try {

			String pendingAppSql = " SELECT TRAVEL_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS EMP_NAME, TO_CHAR(TRAVEL_APPLICATION_DATE, 'DD-MM-YYYY'),TRAVEL_FILE_HEADER_NAME, " 
					+ " HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') " 
					+ " FROM HRMS_D1_TRAVEL_REQUEST "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					//+ " WHERE TRAVEL_APPROVER_STATUS = 'P' AND TRAVEL_APPROVER_CODE =  "
					//+ " WHERE TRAVEL_APPROVER_STATUS IN ('F', 'P') AND TRAVEL_APPROVER_CODE =  "
					+ " WHERE TRAVEL_APPROVER_STATUS  = 'P' AND TRAVEL_APPROVER_CODE =  " + userId 
					+ " ORDER BY HRMS_D1_TRAVEL_REQUEST.TRAVEL_ID DESC  ";

			Object[][] pendingAppObj = getSqlModel().getSingleResult(
					pendingAppSql);

			if (pendingAppObj != null && pendingAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingApp,
						pendingAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingApp", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingApp", Integer
						.parseInt(String.valueOf(pageIndex[3])));

				pendingAppList = new ArrayList(pendingAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					TravelApplicationAppBean bean = new TravelApplicationAppBean();
					bean.setTravelId(String.valueOf(pendingAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(pendingAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(pendingAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(pendingAppObj[i][3])));
					bean.setAuthorizedToken(checkNull(String.valueOf(pendingAppObj[i][4])));
					bean.setDestinationItr(checkNull(String.valueOf(pendingAppObj[i][5])));
					bean.setFromTravelDateItr(checkNull(String.valueOf(pendingAppObj[i][6])));
					pendingAppList.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return pendingAppList;
	}

	public List getPendingCancellationApplicationList(String userId,
			String pageForPendingCancelApp, HttpServletRequest request) {
		List pendingCancelAppList = null;

		try {

			String pendingCancelAppSql = " SELECT TRAVEL_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS EMP_NAME, TO_CHAR(TRAVEL_APPLICATION_DATE, 'DD-MM-YYYY'),TRAVEL_FILE_HEADER_NAME, "
					+ " HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') "
					+ " FROM HRMS_D1_TRAVEL_REQUEST "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " WHERE TRAVEL_APPROVER_STATUS = 'C' AND TRAVEL_APPROVER_CODE =  " + userId 
					+ " ORDER BY HRMS_D1_TRAVEL_REQUEST.TRAVEL_ID DESC  ";

			Object[][] pendingCancelAppObj = getSqlModel().getSingleResult(
					pendingCancelAppSql);

			if (pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingCancelApp,
						pendingCancelAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingCancelApp", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingCancelApp", Integer
						.parseInt(String.valueOf(pageIndex[3])));

				pendingCancelAppList = new ArrayList(pendingCancelAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					TravelApplicationAppBean bean = new TravelApplicationAppBean();
					bean.setTravelId(String.valueOf(pendingCancelAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(pendingCancelAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(pendingCancelAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(pendingCancelAppObj[i][3])));
					bean.setAuthorizedToken(checkNull(String.valueOf(pendingCancelAppObj[i][4])));
					bean.setDestinationItr(checkNull(String.valueOf(pendingCancelAppObj[i][5])));
					bean.setFromTravelDateItr(checkNull(String.valueOf(pendingCancelAppObj[i][6])));
					pendingCancelAppList.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return pendingCancelAppList;
	}

	public List getApprovedApplicationList(String pageForApprovedApp,
			HttpServletRequest request) {

		List approvedAppList = null;

		try {
			String approvedAppSql = " SELECT TRAVEL_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(TRAVEL_APPLICATION_DATE, 'DD-MM-YYYY'),TRAVEL_FILE_HEADER_NAME, "
					+ " HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') "
					+ " FROM HRMS_D1_TRAVEL_REQUEST "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " WHERE TRAVEL_APPROVER_STATUS = 'A' ORDER BY HRMS_D1_TRAVEL_REQUEST.TRAVEL_ID DESC  ";

			Object[][] approvedAppObj = getSqlModel().getSingleResult(
					approvedAppSql);

			if (approvedAppObj != null && approvedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForApprovedApp,
						approvedAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForApprovedApp", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForApprovedApp", Integer
						.parseInt(String.valueOf(pageIndex[3])));

				approvedAppList = new ArrayList(approvedAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					TravelApplicationAppBean bean = new TravelApplicationAppBean();
					bean.setTravelId(String.valueOf(approvedAppObj[i][0]));
					bean.setEmpToken(String.valueOf(approvedAppObj[i][1]));
					bean.setEmpName(String.valueOf(approvedAppObj[i][2]));
					bean.setApplicationDate(String.valueOf(approvedAppObj[i][3]));
					bean.setAuthorizedToken(checkNull(String.valueOf(approvedAppObj[i][4])));
					bean.setDestinationItr(checkNull(String.valueOf(approvedAppObj[i][5])));
					bean.setFromTravelDateItr(checkNull(String.valueOf(approvedAppObj[i][6])));
					approvedAppList.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return approvedAppList;
	}

	public List getApprovedApplicationList(String userId,
			String pageForApprovedApp, HttpServletRequest request) {

		List approvedAppList = null;

		try {
			String approvedAppSql = " SELECT DISTINCT HRMS_D1_TRAVEL_DATA_PATH.TRAVEL_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " to_char(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS EMP_NAME, TO_CHAR(TRAVEL_APPLICATION_DATE, 'DD-MM-YYYY'),TRAVEL_FILE_HEADER_NAME, "
					+ " HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') "
					+ " FROM HRMS_D1_TRAVEL_REQUEST "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " INNER JOIN HRMS_D1_TRAVEL_DATA_PATH ON (HRMS_D1_TRAVEL_DATA_PATH.TRAVEL_ID = HRMS_D1_TRAVEL_REQUEST.TRAVEL_ID AND TRAVEL_APPROVER="+userId+") "
					//+ " WHERE TRAVEL_APPROVER_STATUS IN ('F', 'A') AND TRAVEL_APPROVER_CODE =  "
					+ " WHERE TRAVEL_APPROVER_STATUS IN('F','A','S','X')    OR (HRMS_D1_TRAVEL_DATA_PATH.TRAVEL_STATUS='P'AND TRAVEL_APPROVER="+userId+" )"
					 + "   ORDER BY HRMS_D1_TRAVEL_DATA_PATH.TRAVEL_ID DESC ";

			Object[][] approvedAppObj = getSqlModel().getSingleResult(
					approvedAppSql);

			if (approvedAppObj != null && approvedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForApprovedApp,
						approvedAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForApprovedApp", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForApprovedApp", Integer
						.parseInt(String.valueOf(pageIndex[3])));

				approvedAppList = new ArrayList(approvedAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					TravelApplicationAppBean bean = new TravelApplicationAppBean();
					bean.setTravelId(String.valueOf(approvedAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(approvedAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(approvedAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(approvedAppObj[i][3])));
					bean.setAuthorizedToken(checkNull(String.valueOf(approvedAppObj[i][4])));
					bean.setDestinationItr(checkNull(String.valueOf(approvedAppObj[i][5])));
					bean.setFromTravelDateItr(checkNull(String.valueOf(approvedAppObj[i][6])));
					approvedAppList.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return approvedAppList;
	}

	public List getRejectedApplicationList(String pageForRejectedApp,
			HttpServletRequest request) {

		List rejectedAppList = null;

		try {

			String rejectedAppSql = " SELECT TRAVEL_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(TRAVEL_APPLICATION_DATE, 'DD-MM-YYYY'), "
					+ " HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') "
					+ " FROM HRMS_D1_TRAVEL_REQUEST "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " WHERE TRAVEL_APPROVER_STATUS = 'R' ORDER BY HRMS_D1_TRAVEL_REQUEST.TRAVEL_ID DESC  ";

			Object[][] rejectedAppObj = getSqlModel().getSingleResult(
					rejectedAppSql);

			if (rejectedAppObj != null && rejectedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForRejectedApp,
						rejectedAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForRejectedApp", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForRejectedApp", Integer
						.parseInt(String.valueOf(pageIndex[3])));

				rejectedAppList = new ArrayList(rejectedAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					TravelApplicationAppBean bean = new TravelApplicationAppBean();
					bean.setTravelId(String.valueOf(rejectedAppObj[i][0]));
					bean.setEmpToken(checkNull(String.valueOf(rejectedAppObj[i][1])));
					bean.setEmpName(checkNull(String.valueOf(rejectedAppObj[i][2])));
					bean.setApplicationDate(checkNull(String.valueOf(rejectedAppObj[i][3])));
					bean.setDestinationItr(checkNull(String.valueOf(rejectedAppObj[i][4])));
					bean.setFromTravelDateItr(checkNull(String.valueOf(rejectedAppObj[i][5])));
					rejectedAppList.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return rejectedAppList;
	}

	public List getRejectedApplicationList(String userId,
			String pageForRejectedApp, HttpServletRequest request) {

		List rejectedAppList = null;

		try {

			String rejectedAppSql = " SELECT TRAVEL_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " to_char(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS EMP_NAME, TO_CHAR(TRAVEL_APPLICATION_DATE, 'DD-MM-YYYY'),TRAVEL_FILE_HEADER_NAME, "
					+ " HRMS_D1_TRAVEL_REQUEST.TRAVEL_DESTINATION, TO_CHAR(HRMS_D1_TRAVEL_REQUEST.TRAVEL_FROM_DATE, 'DD-MM-YYYY') "
					+ " FROM HRMS_D1_TRAVEL_REQUEST "
					+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_D1_TRAVEL_REQUEST.TRAVEL_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
					+ " WHERE TRAVEL_APPROVER_STATUS = 'R' AND TRAVEL_APPROVER_CODE =  "
					+ userId + "   ORDER BY HRMS_D1_TRAVEL_REQUEST.TRAVEL_ID DESC  ";

			Object[][] rejectedAppObj = getSqlModel().getSingleResult(
					rejectedAppSql);

			if (rejectedAppObj != null && rejectedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForRejectedApp,
						rejectedAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForRejectedApp", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForRejectedApp", Integer
						.parseInt(String.valueOf(pageIndex[3])));

				rejectedAppList = new ArrayList(rejectedAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					TravelApplicationAppBean bean = new TravelApplicationAppBean();
					bean.setTravelId(String.valueOf(rejectedAppObj[i][0]));
					bean.setEmpToken(checkNull(String
							.valueOf(rejectedAppObj[i][1])));
					bean.setEmpName(checkNull(String
							.valueOf(rejectedAppObj[i][2])));
					bean.setApplicationDate(checkNull(String
							.valueOf(rejectedAppObj[i][3])));
					bean.setAuthorizedToken(checkNull(String.valueOf(rejectedAppObj[i][4])));
					bean.setDestinationItr(checkNull(String.valueOf(rejectedAppObj[i][5])));
					bean.setFromTravelDateItr(checkNull(String.valueOf(rejectedAppObj[i][6])));
					rejectedAppList.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return rejectedAppList;
	}

	public Object[][] getPersDataChangeDetails(String cdromID) {
		String getDetailsSql = " SELECT CDROM_APPROVER_COMMENTS, CDROM_APPROVER_CODE, CDROM_APPROVER_STATUS FROM HRMS_D1_CDROM_REQUEST "
				+ " WHERE CDROM_ID =" + cdromID;
		return getSqlModel().getSingleResult(getDetailsSql);
	}

	public Object[][] getApproverCommentList(String travelId) {

		/*String apprCommentListSql = "SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CDROM_COMMENTS, "
				+ " TO_CHAR(CDROM_DATE, 'DD-MM-YYYY') AS CDROM_DATE, "
				+ " DECODE(CDROM_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') "
				+ " AS STATUS "
				+ " FROM HRMS_D1_CDROM_DATA_PATH PATH   "
				+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.CDROM_APPROVER) "
				+ " WHERE CDROM_ID = " + cdromID + " ORDER BY CDROM_ID DESC ";*/
		
		String apprCommentListSql = "SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, TRAVEL_COMMENTS," 
			+ " TO_CHAR(TRAVEL_DATE, 'DD-MM-YYYY') AS TRAVEL_DATE, "
			+ " DECODE(TRAVEL_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') "
			+ " AS STATUS "
			+ " FROM HRMS_D1_TRAVEL_DATA_PATH PATH   "
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.TRAVEL_APPROVER) "
			+ " WHERE TRAVEL_ID =  " + travelId + " ORDER BY TRAVEL_ID DESC ";
		return getSqlModel().getSingleResult(apprCommentListSql);
	}

	public boolean isUserAMagaer(String persDataChangeId, String userId) {
		String mgrApproverSql = " SELECT * FROM HRMS_D1_TRAVEL_REQUEST WHERE TRAVEL_ID = "
				+ persDataChangeId + " AND TRAVEL_APPROVER_CODE= " + userId;
		Object[][] mgrApproverObj = getSqlModel().getSingleResult(
				mgrApproverSql);

		if (mgrApproverObj != null && mgrApproverObj.length > 0) {
			return true;
		}

		return false;
	}

	public String approve(String travelId, String approverComments,
			String userId, String status, String nextApprover, int level,String emailId) {

		String statusToUpdate = "F";
		String updateApproversql = "";

		
		if (status.equals("C")) {
			statusToUpdate = "X";
		}/* else {
			if (isUserAMagaer(travelId, userId)) {
				statusToUpdate = "P";

				if (nextApprover.equals(userId)) {
					statusToUpdate = "F";
				}

				updateApproversql = ", TRAVEL_APPROVER_CODE = " + nextApprover;
			}
		}*/

		String updateApproverCommentsSql = " UPDATE HRMS_D1_TRAVEL_REQUEST   SET TRAVEL_APPROVER_STATUS  = '"
				+ statusToUpdate
				+ "', "
				+ " TRAVEL_APPROVER_CODE = "
				//+ nextApprover
				+ userId
				+ ", TRAVEL_LEVEL = "
				+ level
				+" , TRAVEL_TP_EMAIL='"+ emailId 
				+ "' WHERE TRAVEL_ID = " + travelId;
		getSqlModel().singleExecute(updateApproverCommentsSql);

		insertApproverComments(travelId, userId, approverComments,
				statusToUpdate);

		return statusToUpdate;
	}
	
	public String forward(String travelId, String approverComments,
			String userId, String status,String nextManager) {

		//String statusToUpdate = "F";
		String statusToUpdate = "P";
		String updateApproversql = "";

		String updateApproverCommentsSql = " UPDATE HRMS_D1_TRAVEL_REQUEST   SET TRAVEL_APPROVER_STATUS  = '"
				+ statusToUpdate
				+ "', "
				+ " TRAVEL_APPROVER_CODE = "
				+ nextManager
				+ " WHERE TRAVEL_ID = " + travelId;
		getSqlModel().singleExecute(updateApproverCommentsSql);

		insertApproverComments(travelId, userId, approverComments,
				statusToUpdate);

		return statusToUpdate;
	}

	public String reject(String travelId, String approverComments, String userId) {
		String message = "";

		try {
			String updateApproverCommentsSql = " UPDATE HRMS_D1_TRAVEL_REQUEST SET TRAVEL_APPROVER_STATUS = 'R' "
					+ " WHERE TRAVEL_ID = " + travelId;

			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(travelId, userId, approverComments, "R");
		} catch (Exception e) {
			logger.error(e);
		}

		return message;
	}

	public String sendBack(String travelId, String approverComments,
			String userId, String nextApprover) {
		String updateApproverCommentsSql = " UPDATE HRMS_D1_TRAVEL_REQUEST SET TRAVEL_APPROVER_STATUS = 'B', TRAVEL_LEVEL = 1 "
				//+ " TRAVEL_APPROVER_CODE = "
				//+ nextApprover
				+ " WHERE TRAVEL_ID = " + travelId;

		getSqlModel().singleExecute(updateApproverCommentsSql);

		insertApproverComments(travelId, userId, approverComments, "B");

		return "B";
	}

	private void insertApproverComments(String cdromId, String userId,
			String approverComments, String statusToUpdate) {
		String insertSql = " INSERT INTO HRMS_D1_TRAVEL_DATA_PATH (TRAVEL_PATH_ID,TRAVEL_ID, TRAVEL_APPROVER, TRAVEL_COMMENTS,TRAVEL_STATUS,TRAVEL_DATE) "
				+ " VALUES ((SELECT NVL(MAX(TRAVEL_PATH_ID), 0) + 1 FROM HRMS_D1_TRAVEL_DATA_PATH), ?, ?, ?, ?,sysdate) ";

		Object[][] insertObj = new Object[1][4];
		insertObj[0][0] = cdromId;
		insertObj[0][1] = userId;
		insertObj[0][2] = approverComments;
		insertObj[0][3] = statusToUpdate;

		getSqlModel().singleExecute(insertSql, insertObj);
	}

}
