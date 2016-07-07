 package org.paradyne.model.leave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.leave.Regularization;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

public class RegularizationApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RegularizationApprovalModel.class);

	int scriptPageNo = 10;

	public void callOnLoad(Regularization regularization, String status,
			HttpServletRequest request) {
		String empCode = regularization.getUserEmpId();
		/**
		 * CODING FOR SWIPE MISS SYSTEM
		 */
		String swipeQuery = "SELECT SWIPE_REG_ID,EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,"
							 + " TO_CHAR(SWIPE_REG_APPLICATION_DATE,'DD-MM-YYYY'),SWIPE_REG_STATUS,"
							 + " HRMS_SWIPE_REG_DTL.SWIPE_REG_REASON,SWIPE_REG_EMP_ID FROM HRMS_SWIPE_REG_HDR" 
							 + " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SWIPE_REG_HDR.SWIPE_REG_EMP_ID)"
							 + " INNER JOIN HRMS_SWIPE_REG_DTL ON(HRMS_SWIPE_REG_DTL.SWIPE_REG_ID=HRMS_SWIPE_REG_HDR.SWIPE_REG_ID)"
							 + " WHERE SWIPE_REG_STATUS='"+status +"' AND HRMS_SWIPE_REG_HDR.SWIPE_REG_APPROVER="+empCode
							 + " ORDER BY SWIPE_REG_ID DESC ";
		Object[][] swipeData = getSqlModel().getSingleResult(swipeQuery);
		regularization.setSwipeList(null);
		regularization.setPageSwipe("false");
		if (swipeData != null && swipeData.length > 0) {
			ArrayList swipeList = new ArrayList();
			for (int i = 0; i < swipeData.length; i++) {
				Regularization bean = new Regularization();
				bean.setSwipeAppCode(String.valueOf(swipeData[i][0]));
				bean.setEmpToken(String.valueOf(swipeData[i][1]));
				bean.setEmpName(String.valueOf(swipeData[i][2]));
				bean.setApplicationDate(String.valueOf(swipeData[i][3]));
				bean.setApplicationType(String.valueOf(swipeData[i][4]));
				bean.setApplyForitt(String.valueOf(swipeData[i][5]));
				bean.setApplEmpCode((String.valueOf(swipeData[i][6])));
				swipeList.add(bean);
				if (status.equals("P")) {
					bean.setViewApplication("View/Approve");
				} else {
					bean.setViewApplication("View Application");
				}
			}
			regularization.setSwipeList(swipeList);
			regularization.setPageSwipe("true");
			try {
				request.setAttribute("swipeTotalPages",
						(swipeList.size() % scriptPageNo == 0) ? (swipeList
								.size() / scriptPageNo)
								: (swipeList.size() / scriptPageNo) + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * CODING FOR LATE REGULARIZATION
		 */
		String lateQuery = "SELECT LATE_REG_ID,EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME ,TO_CHAR(LATE_REG_APPLICATION_DATE,'DD-MM-YYYY'),LATE_REG_STATUS,LATE_REG_EMP_ID FROM HRMS_LATE_REG_HDR "
				+ "	  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LATE_REG_HDR.LATE_REG_EMP_ID) "
				+ "	  WHERE LATE_REG_STATUS='"
				+ status
				+ "' AND HRMS_LATE_REG_HDR.LATE_REG_APPROVER="
				+ empCode
				+ " ORDER BY LATE_REG_ID DESC ";
		Object[][] lateData = getSqlModel().getSingleResult(lateQuery);
		regularization.setRegList(null);
		regularization.setPageLate("false");
		if (lateData != null && lateData.length > 0) {
			ArrayList regList = new ArrayList();
			for (int i = 0; i < lateData.length; i++) {
				Regularization bean = new Regularization();
				bean.setEmpToken(String.valueOf(lateData[i][1]));
				bean.setEmpName(String.valueOf(lateData[i][2]));
				bean.setApplicationDate(String.valueOf(lateData[i][3]));
				bean.setApplicationType(String.valueOf(lateData[i][4]));
				bean.setApplEmpCode((String.valueOf(lateData[i][5])));
				bean.setLateAppCode(String.valueOf(lateData[i][0]));
				regList.add(bean);
				if (status.equals("P")) {
					bean.setViewApplication("View/Approve");
				} else {
					bean.setViewApplication("View Application");
				}
			}
			regularization.setRegList(regList);
			regularization.setPageLate("true");
			try {
				request.setAttribute("lateTotalPages", (regList.size()
						% scriptPageNo == 0) ? (regList.size() / scriptPageNo)
						: (regList.size() / scriptPageNo) + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * CODING FOR REDRESSAL
		 */
		String redreQuery = "SELECT REDRESSAL_ID,EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME ,TO_CHAR(REDRESSAL_APPLICATION_DATE,'DD-MM-YYYY'),REDRESSAL_STATUS,REDRESSAL_EMP_ID FROM HRMS_REDRESSAL_HDR "
				+ "	  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REDRESSAL_HDR.REDRESSAL_EMP_ID) "
				+ "	  WHERE REDRESSAL_STATUS='"
				+ status
				+ "' AND HRMS_REDRESSAL_HDR.REDRESSAL_APPROVER="
				+ empCode
				+ " ORDER BY REDRESSAL_ID DESC ";
		Object[][] redreData = getSqlModel().getSingleResult(redreQuery);
		regularization.setList(null);
		regularization.setPageRedressal("false");
		if (redreData != null && redreData.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < redreData.length; i++) {
				Regularization bean = new Regularization();
				bean.setEmpToken(String.valueOf(redreData[i][1]));
				bean.setEmpName(String.valueOf(redreData[i][2]));
				bean.setApplicationDate(String.valueOf(redreData[i][3]));
				bean.setApplicationType(String.valueOf(redreData[i][4]));
				bean.setApplEmpCode((String.valueOf(redreData[i][5])));
				bean.setRedressalAppCode(String.valueOf(redreData[i][0]));
				list.add(bean);
				if (status.equals("P")) {
					bean.setViewApplication("View/Approve");
				} else {
					bean.setViewApplication("View Application");
				}
			}
			regularization.setList(list);
			regularization.setPageRedressal("true");
			try {
				request.setAttribute("redressalTotalPages", (list.size()
						% scriptPageNo == 0) ? (list.size() / scriptPageNo)
						: (list.size() / scriptPageNo) + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * CODING FOR PERSONAL TIME
		 */
		String ptQuery = "SELECT PT_REG_ID,EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME ,TO_CHAR(PT_REG_APPLICATION_DATE,'DD-MM-YYYY'),PT_REG_STATUS ,'PT',PT_REG_EMP_ID FROM HRMS_PT_REG_HDR "
				+ "	  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PT_REG_HDR.PT_REG_EMP_ID) "
				+ "	  WHERE PT_REG_STATUS='"
				+ status
				+ "' AND HRMS_PT_REG_HDR.PT_REG_APPROVER="
				+ empCode
				+ " ORDER BY PT_REG_ID DESC";
		Object[][] ptData = getSqlModel().getSingleResult(ptQuery);
		regularization.setPtList(null);
		regularization.setPagePT("false");
		if (ptData != null && ptData.length > 0) {
			ArrayList ptList = new ArrayList();
			for (int i = 0; i < ptData.length; i++) {
				Regularization bean = new Regularization();
				bean.setPersonalTimeCode(String.valueOf(ptData[i][0]));
				bean.setEmpToken(String.valueOf(ptData[i][1]));
				bean.setEmpName(String.valueOf(ptData[i][2]));
				bean.setApplicationDate(String.valueOf(ptData[i][3]));
				bean.setApplicationType(String.valueOf(ptData[i][4]));
				bean.setApplyFor(String.valueOf(ptData[i][5]));
				bean.setPersonalTimeCode(String.valueOf(ptData[i][0]));
				bean.setApplEmpCode((String.valueOf(ptData[i][6])));
				ptList.add(bean);
				if (status.equals("P")) {
					bean.setViewApplication("View/Approve");
				} else {
					bean.setViewApplication("View Application");
				}
			}
			regularization.setPtList(ptList);
			regularization.setPagePT("true");
			try {
				request.setAttribute("ptTotalPages", (ptList.size()
						% scriptPageNo == 0) ? (ptList.size() / scriptPageNo)
						: (ptList.size() / scriptPageNo) + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void addInformList(Regularization regularization,
			String[] keepInformCode, String[] keepInform, String chk) {
		// TODO Auto-generated method stub
		regularization.setInformList(null);
		ArrayList arrList = new ArrayList();
		if (keepInform != null && keepInform.length > 0) {
			for (int i = 0; i < keepInform.length; i++) {
				Regularization leaveBean = new Regularization();
				leaveBean.setKeepInformCode(keepInformCode[i]);
				leaveBean.setKeepInform(keepInform[i]);
				arrList.add(leaveBean);
			}
		}
		if (!chk.equals("")) {
			Regularization leaveBean = new Regularization();
			leaveBean.setKeepInformCode(regularization.getInformCode());
			leaveBean.setKeepInform(regularization.getInformName());
			arrList.add(leaveBean);
		}
		regularization.setInformList(arrList);
	}

	public void applyRedressal(Regularization regularization,
			String[] fromDate, String month, String year, String type,
			String empCode, String[] delDate) {
		// TODO Auto-generated method stub
		regularization.setCondTrueFlag("false");
		regularization.setActionName("applyRedressalApplication");
		/**
		 * GET EMPLOYEE INFORMATION
		 */
		String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(SYSDATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
				+ "  WHERE HRMS_EMP_OFFC.EMP_ID = " + empCode;
		Object[][] empData = getSqlModel().getSingleResult(empQuery);
		if (empData != null && empData.length > 0) {
			regularization.setEmpCode(checkNull(String.valueOf(empData[0][0])));
			regularization
					.setEmpToken(checkNull(String.valueOf(empData[0][1])));
			regularization.setEmpName(checkNull(String.valueOf(empData[0][2])));
			regularization
					.setEmpBranch(checkNull(String.valueOf(empData[0][3])));
			regularization.setEmpDesg(checkNull(String.valueOf(empData[0][4])));
			regularization.setApplicationDate(checkNull(String
					.valueOf(empData[0][5])));
			regularization
					.setEmpGender(checkNull(String.valueOf(empData[0][6])));
			regularization.setShiftCode(String.valueOf(empData[0][7]));
		}

		String delEmpList = "";
		if (delDate != null && delDate.length > 0) {
			for (int i = 0; i < delDate.length; i++) {
				delEmpList += " '" + String.valueOf(delDate[i]) + "' ,";
			}
			delEmpList = delEmpList.substring(0, delEmpList.length() - 1);
		}

		String selectDate = "";
		for (int i = 0; i < fromDate.length; i++) {
			selectDate += " '" + String.valueOf(fromDate[i]) + "' ,";
		}
		selectDate = selectDate.substring(0, selectDate.length() - 1);
		// TO_CHAR(LEAVE_FROM_DATE,'MM')="+month+" AND
		// TO_CHAR(LEAVE_FROM_DATE,'YYYY')="+year+" AND
		regularization.setRedressalFlag("true");
		regularization.setListFlag("false");
		String query = "SELECT LEAVE_CODE,LEAVE_ABBR,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') "
				+ "	,LEAVE_PENALTY_ADJUST_DAYS "
				+ "	 FROM HRMS_LEAVE_DTL "
				+ "	 INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_DTL.LEAVE_CODE AND EMP_ID="
				+ empCode + ") " + "	 WHERE   LEAVE_DTL_STATUS='A'  ";
		if (!selectDate.equals("")) {
			query += " AND   TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY') IN("
					+ selectDate + ")  ";
		}
		if (!delEmpList.equals("")) {
			query += " AND   TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY') NOT IN("
					+ delEmpList + ")  ";
		}

		query += " ORDER BY LEAVE_FROM_DATE";
		Object[][] data = getSqlModel().getSingleResult(query);
		regularization.setList(null);
		if (data != null && data.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				Regularization bean = new Regularization();
				bean.setRLeaveCode(String.valueOf(data[i][0]));
				bean.setLeaveType(String.valueOf(data[i][1]));
				bean.setRFromDate(String.valueOf(data[i][2]));
				bean.setRToDate(String.valueOf(data[i][3]));
				bean.setRPenaltyDays(String.valueOf(data[i][4]));
				bean.setRrdressalDays(String.valueOf(data[i][4]));
				list.add(bean);
			}
			regularization.setList(list);
		}

	}

	public void setApproverData(Regularization regularization,
			Object[][] empFlow) {

		if (empFlow != null && empFlow.length > 0) {

			Object[][] approverDataObj = new Object[empFlow.length][2];
			for (int i = 0; i < empFlow.length; i++) {
				approverDataObj[i][0] = "";
				String selectQuery = "  SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') ,' '||'('||HRMS_RANK.RANK_NAME||')',HRMS_EMP_OFFC.EMP_ID "
						+ "  FROM HRMS_EMP_OFFC "
						+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
						+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

				Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
				if (temObj != null && temObj.length > 0) {
					approverDataObj[i][0] = checkNull(String
							.valueOf(temObj[0][0]));
					approverDataObj[i][1] = checkNull(String
							.valueOf(temObj[0][2]));
				}
			}
			regularization.setApproverList(null);
			if (approverDataObj != null && approverDataObj.length > 0) {
				ArrayList arrList = new ArrayList();
				for (int i = 0; i < approverDataObj.length; i++) {
					Regularization leaveBean = new Regularization();
					leaveBean.setApproverName(String
							.valueOf(approverDataObj[i][0]));
					String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
					leaveBean.setApprSrNo(srNo);
					leaveBean.setApproverCode(String
							.valueOf(approverDataObj[i][1]));
					arrList.add(leaveBean);
				}
				regularization.setApproverList(arrList);
			}

		}
	}

	/**
	 * SET EMPLOYEE INFORMATION NAME,BRANCH,DESIGNATION
	 * 
	 * @param regularization
	 * @param applCode
	 */
	public void setEmployeeInformation(Regularization regularization,
			String query) {
		/**
		 * GET EMPLOYEE INFORMATION
		 */
		Object[][] empData = getSqlModel().getSingleResult(query);
		if (empData != null && empData.length > 0) {
			regularization.setEmpCode(checkNull(String.valueOf(empData[0][0])));
			regularization
					.setEmpToken(checkNull(String.valueOf(empData[0][1])));
			regularization.setEmpName(checkNull(String.valueOf(empData[0][2])));
			regularization
					.setEmpBranch(checkNull(String.valueOf(empData[0][3])));
			regularization.setEmpDesg(checkNull(String.valueOf(empData[0][4])));
			regularization.setApplicationDate(checkNull(String
					.valueOf(empData[0][5])));
			regularization
					.setEmpGender(checkNull(String.valueOf(empData[0][6])));
			regularization.setShiftCode(String.valueOf(empData[0][7]));
		}
	}

	/**
	 * SET KEEP INFORMATION
	 * 
	 * @param regularization
	 * @param applCode
	 */
	public void setKeepInform(Regularization regularization, String applCode,
			String query) {
		// TODO Auto-generated method stub
		/**
		 * GET KEEP INFORM & REASON
		 */
		// String hdr="SELECT
		// NVL(REDRESSAL_KEEP_INFORM,'0'),NVL(REDRESSAL_REASON,' ') FROM
		// HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="+applCode;
		Object[][] hdrData = getSqlModel().getSingleResult(query);
		regularization.setInformList(null);
		if (hdrData != null && hdrData.length > 0) {
			String info = String.valueOf(hdrData[0][0]);
			if (String.valueOf(hdrData[0][3]).equals("1")) {
				regularization.setCommentsFlag("false");
			}

			if (!info.equals("0")) {
				String emp = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID IN("
						+ info + ")";
				Object[][] empInfoData = getSqlModel().getSingleResult(emp);
				ArrayList arrList = new ArrayList();
				if (empInfoData != null && empInfoData.length > 0) {
					for (int i = 0; i < empInfoData.length; i++) {
						Regularization leaveBean = new Regularization();
						// leaveBean.setKeepInformCode(String.valueOf(empInfoData[i][0]));
						leaveBean.setKeepInform(String
								.valueOf(empInfoData[i][0]));
						arrList.add(leaveBean);
					}
					regularization.setInformList(arrList);
				}
			}
			regularization.setReason(String.valueOf(hdrData[0][1]));
			// regularization.setApplyFor(String.valueOf(hdrData[0][2]));
		}
	}

	/**
	 * METHOD FOR VIEW REDRESSAL APLICATION
	 * 
	 * @param regularization
	 * @param status
	 * @param applCode
	 */

	public void viewRedressalApplication(Regularization regularization,
			String applCode, String status, String[] delDate, String FromDate[]) {
		// TODO Auto-generated method stub
		regularization.setCondTrueFlag("false");
		regularization.setActionName("applyRedressalApplication");
		regularization.setRedressalFlag("true");
		regularization.setListFlag("false");

		String delEmpList = "";
		if (delDate != null && delDate.length > 0) {
			for (int i = 0; i < delDate.length; i++) {
				delEmpList += " '" + String.valueOf(delDate[i]) + "' ,";
			}
			delEmpList = delEmpList.substring(0, delEmpList.length() - 1);
		}

		String getDate = "";
		if (FromDate != null && FromDate.length > 0) {
			for (int i = 0; i < FromDate.length; i++) {
				getDate += " '" + String.valueOf(FromDate[i]) + "' ,";
			}
			getDate = getDate.substring(0, getDate.length() - 1);
		}
		/**
		 * GET LEAVE DTL INFORMATION
		 */
		String query = "SELECT HRMS_REDRESSAL_DTL.REDRESSAL_DTL_LEAVE_CODE,HRMS_LEAVE.LEAVE_ABBR,TO_CHAR(REDRESSAL_DTL_FROM_DATE,'DD-MM-YYYY') "
				+ "		,TO_CHAR(REDRESSAL_DTL_TO_DATE,'DD-MM-YYYY'),REDRESSAL_DTL_PENALTY_DAYS,REDRESSAL_DTL_REDRESSED_DAYS,REDRESSAL_DTL_PENALTY_ADJ_DAYS ,DECODE(REDRESSAL_DTL_PENALTY_DAYS,REDRESSAL_DTL_PENALTY_ADJ_DAYS,'Y','N') "
				+ "		 FROM HRMS_REDRESSAL_DTL  "
				+ "		INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_REDRESSAL_DTL.REDRESSAL_DTL_LEAVE_CODE)"
				+ "		WHERE REDRESSAL_ID="
				+ applCode
				+ " AND REDRESSAL_DTL_STATUS='" + status + "'";

		if (!delEmpList.equals("")) {
			query += " AND TO_CHAR(REDRESSAL_DTL_FROM_DATE,'DD-MM-YYYY') NOT IN("
					+ delEmpList + ") ";
		}
		if (!getDate.equals("")) {
			query += " AND TO_CHAR(REDRESSAL_DTL_FROM_DATE,'DD-MM-YYYY')  IN("
					+ getDate + ") ";
		}
		Object[][] data = getSqlModel().getSingleResult(query);
		regularization.setList(null);
		if (data != null && data.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				Regularization bean = new Regularization();
				bean.setRLeaveCode(String.valueOf(data[i][0]));
				bean.setLeaveType(String.valueOf(data[i][1]));
				bean.setRFromDate(String.valueOf(data[i][2]));
				bean.setRToDate(String.valueOf(data[i][3]));
				bean.setRPenaltyDays(String.valueOf(data[i][4]));
				bean.setRrdressalDays(String.valueOf(data[i][5]));
				bean.setRedressalAdjDays(String.valueOf(data[i][6]));// ADJUST
																		// DAY
				bean.setRedressalAdjStatus(String.valueOf(data[i][7]));
				list.add(bean);
			}
			regularization.setList(list);
		}
	}

	/**
	 * METHOD FOR VIEW LATE REGULARIZATION
	 * 
	 * @param regularization
	 * @param status
	 * @param applCode
	 */
	public void viewLateApplication(Regularization regularization,
			String applCode, String status, String[] date, String[] delDate) {
		regularization.setCondTrueFlag("false");
		regularization.setCountValue("0");

		regularization.setListFlag("false");
		regularization.setLateFlag("true");
		regularization.setSwipeFlag("false");
		regularization.setRedressalFlag("false");

		String delEmpList = "";
		if (delDate != null && delDate.length > 0) {
			for (int i = 0; i < delDate.length; i++) {
				delEmpList += " '" + String.valueOf(delDate[i]) + "' ,";
			}
			delEmpList = delEmpList.substring(0, delEmpList.length() - 1);
		}

		String getDate = "";
		if (date != null && date.length > 0) {
			for (int i = 0; i < date.length; i++) {
				getDate += " '" + String.valueOf(date[i]) + "' ,";
			}
			getDate = getDate.substring(0, getDate.length() - 1);
		}

		String query = "SELECT TO_CHAR(LATE_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(LATE_REG_DTL_SFTTIME,'HH24:MI'),TO_CHAR(LATE_REG_DTL_INTIME,'HH24:MI') "
				+ "	,TRUNC(MOD( ( TO_DATE(TO_char(LATE_REG_DTL_INTIME, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(LATE_REG_DTL_SFTTIME, 'HH24.MI'),'HH24.MI'))*24,24)) "
				+ "	 || ' : ' ||  "
				+ "	 TRUNC(MOD( ( TO_DATE(TO_char(LATE_REG_DTL_INTIME, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(LATE_REG_DTL_SFTTIME, "
				+ "	'HH24.MI'),'HH24.MI'))*24*60,60)) || '' AS DIFFERENCE,TO_CHAR(LATE_REG_DTL_HRS_DED,'HH24:MI'),HRMS_LEAVE.LEAVE_ABBR ,HRMS_LEAVE.LEAVE_ID "
				+ "	,LATE_REG_ID FROM HRMS_LATE_REG_DTL "
				+ "	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LATE_REG_DTL.LATE_REG_DTL_LEAVE_ID) "
				+ "	WHERE LATE_REG_ID="
				+ applCode
				+ " AND LATE_REG_DTL_STATUS='" + status + "' ";
		if (!delEmpList.equals("")) {
			query += " AND TO_CHAR(LATE_REG_DTL_DATE,'DD-MM-YYYY') NOT IN("
					+ delEmpList + ") ";
		}
		if (!getDate.equals("")) {
			query += " AND TO_CHAR(LATE_REG_DTL_DATE,'DD-MM-YYYY')  IN("
					+ getDate + ") ";
		}

		query += " ORDER BY LATE_REG_ID";
		Object[][] data = getSqlModel().getSingleResult(query);
		regularization.setRegList(null);
		ArrayList regList = new ArrayList();
		regularization.setListFlag("true");
		regularization.setLateFlag("true");
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			Regularization bean = new Regularization();
			bean.setDate(String.valueOf(data[i][0]));
			bean.setShiftTime(String.valueOf(data[i][1]));
			bean.setInTime(String.valueOf(data[i][2]));
			bean.setLateHrs(getHH_MM(String.valueOf(data[i][3])));
			bean.setLateHrsDeduct(getHH_MM(String.valueOf(data[i][4])));
			bean.setLateHrsDeductFrom(String.valueOf(data[i][5]));
			bean.setLateHrsDeductFromCode(String.valueOf(data[i][6]));
			sum += getMinute(getHH_MM(String.valueOf(data[i][4])));

			regList.add(bean);

		}
		logger.info("TOTAL SUM IS=========  " + sum);
		regularization.setTotalLateHrs(getHH_MM(sum));
		regularization.setRegList(regList);
	}

	/**
	 * METHOD FOR VIEW SWIPE MISS APPLICATION
	 * 
	 * @param regularization
	 * @param status
	 * @param applCode
	 */
	public void viewSwipeApplication(Regularization regularization,
			String applCode, String status, String[] date, String[] delDate) {
		try {
			regularization.setCondTrueFlag("false");
			regularization.setCountValue("0");
			regularization.setListFlag("false");
			regularization.setLateFlag("false");
			regularization.setSwipeFlag("true");
			regularization.setRedressalFlag("false");
			String delEmpList = "";
			if (delDate != null && delDate.length > 0) {
				for (int i = 0; i < delDate.length; i++) {
					delEmpList += " '" + String.valueOf(delDate[i]) + "' ,";
				}
				delEmpList = delEmpList.substring(0, delEmpList.length() - 1);
			}
			String getDate = "";
			if (date != null && date.length > 0) {
				for (int i = 0; i < date.length; i++) {
					getDate += " '" + String.valueOf(date[i]) + "' ,";
				}
				getDate = getDate.substring(0, getDate.length() - 1);
			}
			String query = "SELECT TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(SWIPE_REG_DTL_FROM_TIME,'HH24:MI') "
					+ "  ,TO_CHAR(SWIPE_REG_DTL_TO_TIME,'HH24:MI'),TO_CHAR(SWIPE_REG_DTL_ACT_INTIME,'HH24:MI') "
					+ "  ,TO_CHAR(SWIPE_REG_DTL_ACT_OUTTIME,'HH24:MI') "
					+ "  ,NVL(SWIPE_REG_REASON,0) FROM HRMS_SWIPE_REG_DTL  "
					+ "  WHERE SWIPE_REG_ID="
					+ applCode + " AND SWIPE_REG_DTL_STATUS='" + status + "'";
			if (!delEmpList.equals("")) {
				query += " AND TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY') NOT IN("
						+ delEmpList + ") ";
			}
			if (!getDate.equals("")) {
				query += " AND TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY')  IN("
						+ getDate + ") ";
			}
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				regularization.setActionName("applySwipeApplication");
				regularization.setSwipeFlag("true");
				regularization.setSwipeList(null);
				ArrayList list = new ArrayList();
				for (int i = 0; i < data.length; i++) {
					Regularization bean = new Regularization();
					bean.setDate(String.valueOf(data[i][0]));
					bean.setShiftTime(String.valueOf(data[i][1]));// intime
					bean.setInTime(String.valueOf(data[i][2]));// outtime
					bean.setFromTime(String.valueOf(data[i][3]));
					bean.setToTime(checkNull(String.valueOf(data[i][4])));
					// bean.setTotalDiff("");
					bean.setReasonItt(checkNull(String.valueOf(data[i][5])));
					list.add(bean);
				}
				regularization.setSwipeList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value
	 *            value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if

		int tenRemainder = value % 10;

		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	/**
	 * CONVERT HH:MM INTO MINUTS
	 * 
	 * @param result
	 * @return
	 */
	public int getMinute(String result) {
		int min = 0;
		if (result == null || result.equals("null") || result.equals("")) {
			return min;
		}// end of if
		else {
			if (result.contains(":")) {
				String[] chk = result.split(":");
				try {
					min = Integer.parseInt(chk[0]) * 60;
					min = min + Integer.parseInt(chk[1]);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			return min;
		}// end of else
	}

	/**
	 * CONVERT MINUTS INTO HH:MM FORMATE
	 * 
	 * @param result
	 * @return
	 */
	public String getHH_MM(int minuts) {
		String hrs = "00";
		String minute = "00";
		int mm = 0;
		int hh = 0;
		try {
			hh = minuts / 60;
			mm = (minuts % 60);
			hrs = "" + hh;
			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return (hrs + ":" + minute);
	}

	/**
	 * CONVERT H:M INTO HH:MM FORMATE
	 * 
	 * @param result
	 * @return
	 */
	public String getHH_MM(String hh_mm) {
		String hrs = "00";
		String minute = "00";
		int mm = 0;
		int hh = 0;
		try {
			String[] data = hh_mm.split(":");
			hh = Integer.parseInt(String.valueOf(data[0]).trim());
			mm = Integer.parseInt(String.valueOf(data[1]).trim());
			hrs = "" + hh;
			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (hrs + ":" + minute);
	}

	/**
	 * CONVERT MONUTS INTO DAY HH:MM
	 * 
	 * @param result
	 * @return
	 */
	public String getDAYS_HH_MM(int minuts, int shiftMinuts) {
		logger.info("total min  " + minuts + "  ======  Shift min  "
				+ shiftMinuts);
		String days = "0 Days 00:00";
		String hrs = "00";
		String minute = "00";
		int dd = 0;
		int mm = 0;
		int hh = 0;
		float newDD = 0.0f;
		try {
			dd = minuts > shiftMinuts ? minuts / shiftMinuts : 0;
			minuts = dd > 0 ? (minuts - (dd * shiftMinuts)) : minuts;
			hh = minuts >= 60 ? minuts / 60 : 0;
			mm = hh > 0 ? minuts - (hh * 60) : minuts;
			/**
			 * IF HRS GRATER THAN HALF DAY CONVERT INTO HALF DAY
			 * 
			 */
			newDD = dd;
			if ((hh * 60) >= (shiftMinuts / 2)) {
				newDD = newDD + 0.5f;
				hh = (hh * 60) - (shiftMinuts / 2);
				mm = mm + hh % 60;
				hh = hh / 60;
				hh = (mm >= 60) ? (hh + 1) : hh;
				mm = (mm >= 60) ? (mm - 60) : mm;
			}

			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		days = newDD + " Days " + hrs + ":" + minute;
		return days;
	}

	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		ReportingModel reporting = new ReportingModel();
		reporting.initiate(context, session);
		Object result[][] = reporting.generateEmpFlow(empCode, type, order);
		reporting.terminate();
		return result;
	}

	public String getLeavePolicyCode(String empId) {
		String leavePolicyCode = "";
		try {

			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			leavePolicyCode = model.getLeavePolicy(empId);
			model.terminate();
			logger.info("LEAVE POLICY CODE **************    "
					+ leavePolicyCode);
		} catch (Exception e) {

		}
		return leavePolicyCode;
	}

	public String sendBackSwipeAppl(Regularization regularization) {
		String result = "";
		String level = "1";
		String empCode = regularization.getEmpCode();
		String applicationCode = regularization.getApplicationCode();
		logger.info("APPLICATION CODE IS===================  "
				+ applicationCode);
		// UPDATE HEADER
		String App = "UPDATE HRMS_SWIPE_REG_HDR  SET SWIPE_REG_STATUS='B' WHERE SWIPE_REG_ID= "
				+ applicationCode;
		getSqlModel().singleExecute(App);
		// UPDATE DTL TABLE
		String dtl = "UPDATE HRMS_SWIPE_REG_DTL  SET SWIPE_REG_DTL_STATUS='B' WHERE SWIPE_REG_ID= "
				+ applicationCode;
		getSqlModel().singleExecute(dtl);
		String insPath = "INSERT INTO HRMS_SWIPE_REG_PATH(SWIPE_REG_PATH_CODE,SWIPE_REG_ID,SWIPE_REG_PATH_ASSESS_BY,SWIPE_REG_PATH_STATUS,SWIPE_REG_PATH_ASSESS_DATE,SWIPE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(SWIPE_REG_PATH_CODE),0)+1 FROM HRMS_SWIPE_REG_PATH WHERE SWIPE_REG_ID="
				+ applicationCode + "),?,?,?,sysdate,?)";
		Object[][] path = new Object[1][4];
		path[0][0] = applicationCode;
		path[0][1] = regularization.getUserEmpId();
		path[0][2] = "B";
		path[0][3] = regularization.getApproverComents();
		getSqlModel().singleExecute(insPath, path);

		result = "Application has been send back";
		return result;
	}

	public String rejectSwipeAppl(Regularization regularization,
			String[] fromDate) {
		String result = "";
		String level = "1";
		String year="";
		String empCode = regularization.getEmpCode();
		String applicationCode = regularization.getApplicationCode();
		try {
			logger.info("APPLICATION CODE IS===================  "
					+ applicationCode);
			// UPDATE HEADER
			String App = "UPDATE HRMS_SWIPE_REG_HDR  SET SWIPE_REG_STATUS='R' WHERE SWIPE_REG_ID= "
					+ applicationCode;
			getSqlModel().singleExecute(App);
			// UPDATE DTL TABLE
			String dtl = "UPDATE HRMS_SWIPE_REG_DTL  SET SWIPE_REG_DTL_STATUS='R' WHERE SWIPE_REG_ID= "
					+ applicationCode;
			getSqlModel().singleExecute(dtl);
			// UPDATE PATH
			/*
			 * String upQuery="UPDATE HRMS_SWIPE_REG_PATH SET
			 * SWIPE_REG_PATH_COMMENTS=?,
			 * SWIPE_REG_PATH_ASSESS_DATE=SYSDATE,SWIPE_REG_PATH_STATUS=? WHERE
			 * SWIPE_REG_ID ="+applicationCode+" AND
			 * SWIPE_REG_PATH_ASSESS_BY="+regularization.getUserEmpId()+"";
			 * Object[][]upData=new Object[1][2];
			 * upData[0][0]=regularization.getApproverComents(); upData[0][1]="R";
			 * getSqlModel().singleExecute(upQuery,upData);
			 */
			String insPath = "INSERT INTO HRMS_SWIPE_REG_PATH(SWIPE_REG_PATH_CODE,SWIPE_REG_ID,SWIPE_REG_PATH_ASSESS_BY,SWIPE_REG_PATH_STATUS,SWIPE_REG_PATH_ASSESS_DATE,SWIPE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(SWIPE_REG_PATH_CODE),0)+1 FROM HRMS_SWIPE_REG_PATH WHERE SWIPE_REG_ID="
					+ applicationCode + "),?,?,?,sysdate,?)";
			Object[][] path = new Object[1][4];
			path[0][0] = applicationCode;
			path[0][1] = regularization.getUserEmpId();
			path[0][2] = "R";
			path[0][3] = regularization.getApproverComents();
			getSqlModel().singleExecute(insPath, path);
			/**
			 * update attendance table
			 */
			if (fromDate != null && fromDate.length > 0) {
				year = fromDate[0].substring(6, 10);
				String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " SET ATT_REG_STATUS_LATE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
				Object[][] upDataAtt = new Object[fromDate.length][3];
				for (int i = 0; i < fromDate.length; i++) {
					upDataAtt[i][0] = "RS";
					upDataAtt[i][1] = fromDate[i];
					upDataAtt[i][2] = empCode;
				}
				getSqlModel().singleExecute(upAtten, upDataAtt);
			}
			result = "Application has been rejected";
			if(result.equals("Application has been rejected")){
				if (fromDate != null && fromDate.length > 0) {
					for (int i = 0; i < fromDate.length; i++) {
						rejectReglAttendanceData(year, fromDate[i], empCode);	
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String sendBackLateRegAppl(Regularization regularization) {
		String result = "";
		String empCode = regularization.getEmpCode();
		String applicationCode = regularization.getApplicationCode();
		logger.info("APPLICATION CODE IS===================  "
				+ applicationCode);
		// UPDATE HEADER
		String App = "UPDATE HRMS_LATE_REG_HDR  SET LATE_REG_STATUS='B' WHERE LATE_REG_ID= "
				+ applicationCode;
		getSqlModel().singleExecute(App);
		// UPDATE DTL TABLE
		String dtl = "UPDATE HRMS_LATE_REG_DTL  SET LATE_REG_DTL_STATUS='B' WHERE LATE_REG_ID= "
				+ applicationCode;
		getSqlModel().singleExecute(dtl);
		// UPDATE PATH
		String insPath = "INSERT INTO HRMS_LATE_REG_PATH(LATE_REG_PATH_CODE,LATE_REG_ID,LATE_REG_PATH_ASSESS_BY,LATE_REG_PATH_STATUS,LATE_REG_PATH_ASSESS_DATE,LATE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(LATE_REG_PATH_CODE),0)+1 FROM HRMS_LATE_REG_PATH WHERE LATE_REG_ID="
				+ applicationCode + "),?,?,?,sysdate,?)";
		Object[][] path = new Object[1][4];
		path[0][0] = applicationCode;
		path[0][1] = regularization.getUserEmpId();
		path[0][2] = "B";
		path[0][3] = regularization.getApproverComents();
		getSqlModel().singleExecute(insPath, path);

		return result = "Application has been send back";
	}

	public String rejectLateRegAppl(Regularization regularization,
			String[] fromDate, String[] lateHrsDeductFromCode,
			String[] lateHrsDeduct, String chk) {
		String result = "";
		String empCode = regularization.getEmpCode();
		String applicationCode = regularization.getApplicationCode();
		logger.info("APPLICATION CODE IS===================  "
				+ applicationCode);
		// UPDATE HEADER
		String App = "UPDATE HRMS_LATE_REG_HDR  SET LATE_REG_STATUS='" + chk
				+ "' WHERE LATE_REG_ID= " + applicationCode;
		getSqlModel().singleExecute(App);
		// UPDATE DTL TABLE
		String dtl = "UPDATE HRMS_LATE_REG_DTL  SET LATE_REG_DTL_STATUS='"
				+ chk + "' WHERE LATE_REG_ID= " + applicationCode;
		getSqlModel().singleExecute(dtl);
		// UPDATE PATH
		String insPath = "INSERT INTO HRMS_LATE_REG_PATH(LATE_REG_PATH_CODE,LATE_REG_ID,LATE_REG_PATH_ASSESS_BY,LATE_REG_PATH_STATUS,LATE_REG_PATH_ASSESS_DATE,LATE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(LATE_REG_PATH_CODE),0)+1 FROM HRMS_LATE_REG_PATH WHERE LATE_REG_ID="
				+ applicationCode + "),?,?,?,sysdate,?)";
		Object[][] path = new Object[1][4];
		path[0][0] = applicationCode;
		path[0][1] = regularization.getUserEmpId();
		path[0][2] = chk;
		path[0][3] = regularization.getApproverComents();
		getSqlModel().singleExecute(insPath, path);

		/**
		 * update attendance table
		 */
		if (fromDate != null && fromDate.length > 0) {
			String year = fromDate[0].substring(6, 10);
			String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " SET ATT_REG_STATUS_LATE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
			Object[][] upDataAtt = new Object[fromDate.length][3];
			for (int i = 0; i < fromDate.length; i++) {
				upDataAtt[i][0] = "RL";
				if (chk.equals("B")) {
					upDataAtt[i][0] = "BL";
				}
				upDataAtt[i][1] = fromDate[i];
				upDataAtt[i][2] = empCode;
			}
			getSqlModel().singleExecute(upAtten, upDataAtt);
		}
		/**
		 * UPDATE LEAVE BALANCE
		 */
		if (lateHrsDeductFromCode != null && lateHrsDeductFromCode.length > 0) {
			String year = fromDate[0].substring(6, 10);
			/**
			 * GET SHIFT INFORMATION
			 */
			String qq = "SELECT NVL(SFT_LM_HRS_ISENABLED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0)  FROM HRMS_SHIFT where SHIFT_ID="
					+ regularization.getShiftCode();
			Object[][] shiftCode = getSqlModel().getSingleResult(qq);
			String shifTime = "";
			if (shiftCode != null && shiftCode.length > 0) {
				shifTime = String.valueOf(shiftCode[0][4]);
			}
			int shiftMinuts = getMinute(shifTime);

			String upAtten = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE=?,LEAVE_HRS_CLOSING_BALANCE=TO_DATE(?,'HH24:MI') WHERE LEAVE_CODE=? AND EMP_ID=?";

			for (int i = 0; i < lateHrsDeductFromCode.length; i++) {
				Object[][] upDataAtt = new Object[1][4];
				String balQuery = "SELECT NVL(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),'00:00'),NVL(LEAVE_CLOSING_BALANCE,0) FROM HRMS_LEAVE_BALANCE WHERE LEAVE_CODE="
						+ lateHrsDeductFromCode[i] + " AND EMP_ID=" + empCode;
				Object[][] balData = getSqlModel().getSingleResult(balQuery);
				int exitBalMi = 0;
				float days = 0.0f;
				int updateBal = getMinute(lateHrsDeduct[i]);
				int tot = 0;
				if (balData != null && balData.length > 0) {
					exitBalMi = getMinute(String.valueOf(balData[0][0]));
					days = Float.parseFloat(String.valueOf(balData[0][1]));
				}
				tot = exitBalMi + updateBal;
				if ((tot) >= (shiftMinuts / 2)) {
					days = days + 0.5f;
					tot = tot - (shiftMinuts / 2);
				}

				if ((tot) >= (shiftMinuts / 2)) {
					days = days + 0.5f;
					tot = tot - (shiftMinuts / 2);
				}
				upDataAtt[0][0] = days;
				upDataAtt[0][1] = getHH_MM(tot);
				upDataAtt[0][2] = lateHrsDeductFromCode[i];
				upDataAtt[0][3] = empCode;
				getSqlModel().singleExecute(upAtten, upDataAtt);
			}

		}
		result = "Application has been rejected";
		if (chk.equals("B")) {
			result = "Application has been send back";
		}
		return result;
	}

	public String approveSwipeAppl(Regularization regularization,
			String[] fromDate, String[] fromTime, String[] toTime) {
		String result = "";
		String year = "";
		try {
			String level = "1";
			String empCode = regularization.getEmpCode();
			String applicationCode = regularization.getApplicationCode();
			logger.info("APPLICATION CODE IS===================  "
					+ applicationCode);
			String query = "SELECT SWIPE_REG_STATUS,NVL(SWIPE_LEVEL,1) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
					+ applicationCode + " ";// AND REDRESSAL_STATUS='P'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				level = String.valueOf(data[0][1]);
			}
			Object[][] empFlow = null;
			empFlow = generateEmpFlow(empCode, "Regularize", Integer
					.parseInt(level) + 1);
			/**
			 * NEXT APPROVER IS PRESENT SEND TO NEXT APPROVER
			 */
			if (empFlow != null && empFlow.length > 0) {
				logger
						.info("UNDER  APPROVER  NO ********************************   :  "
								+ level);
				regularization.setSecondApproverCode(String
						.valueOf(empFlow[0][0]));
				Object[][] updateApprover = new Object[1][4];
				updateApprover[0][0] = empFlow[0][2]; // level
				updateApprover[0][1] = empFlow[0][0]; // approver id
				updateApprover[0][2] = empFlow[0][3]; // alternate approver id
				updateApprover[0][3] = applicationCode; // application code
				String nextApp = "UPDATE HRMS_SWIPE_REG_HDR  SET SWIPE_LEVEL=? ,SWIPE_REG_APPROVER=? ,SWIPE_REG_ALT_APPROVER=? WHERE SWIPE_REG_ID=? ";
				getSqlModel().singleExecute(nextApp, updateApprover);
				/*
				 * String upQuery="UPDATE HRMS_SWIPE_REG_PATH SET
				 * SWIPE_REG_PATH_COMMENTS=?,
				 * SWIPE_REG_PATH_ASSESS_DATE=SYSDATE,SWIPE_REG_PATH_STATUS=?
				 * WHERE SWIPE_REG_ID ="+applicationCode+" AND
				 * SWIPE_REG_PATH_ASSESS_BY="+regularization.getUserEmpId()+"";
				 * Object[][]upData=new Object[1][2];
				 * upData[0][0]=regularization.getApproverComents();
				 * upData[0][1]="A";
				 * getSqlModel().singleExecute(upQuery,upData);
				 */
				String insPath = "INSERT INTO HRMS_SWIPE_REG_PATH(SWIPE_REG_PATH_CODE,SWIPE_REG_ID,SWIPE_REG_PATH_ASSESS_BY,SWIPE_REG_PATH_STATUS,SWIPE_REG_PATH_ASSESS_DATE,SWIPE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(SWIPE_REG_PATH_CODE),0)+1 FROM HRMS_SWIPE_REG_PATH WHERE SWIPE_REG_ID="
						+ applicationCode + "),?,?,?,sysdate,?)";
				Object[][] path = new Object[1][4];
				path[0][0] = applicationCode;
				path[0][1] = regularization.getUserEmpId();
				path[0][2] = "A";
				path[0][3] = regularization.getApproverComents();
				getSqlModel().singleExecute(insPath, path);

				Object[][] hdrObj = new Object[fromDate.length][3];
				for (int i = 0; i < fromDate.length; i++) {
					hdrObj[i][0] = fromTime[i];// in
					hdrObj[i][1] = toTime[i];// in
					hdrObj[i][2] = fromDate[i];// date
				}

				String dtl = "UPDATE HRMS_SWIPE_REG_DTL  SET SWIPE_REG_DTL_ACT_INTIME=TO_DATE(?,'hh24:mi:ss'),SWIPE_REG_DTL_ACT_OUTTIME=TO_DATE(?,'hh24:mi:ss') WHERE SWIPE_REG_DTL_DATE=TO_DATE(?,'DD-MM-YYYY') AND SWIPE_REG_ID= "
						+ applicationCode;
				getSqlModel().singleExecute(dtl, hdrObj);

				result = "This application has been forwarded to next approval";

			} else {
				/**
				 * LAST APPROVER
				 */

				String selectQury = "SELECT EMP_SHIFT, SHIFT_NAME, NVL(TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI:SS'),'00:00:00')START_TIME, "
						+ "	NVL(TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI:SS'),'00:00:00')END_TIME, NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS'),'00:00:00')FIRST_HALF, "
						+ "	NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'),'00:00:00')SECOND_HALF ,"
						+ "	NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS'),'00:00:00')LATE_MARK ,"
						+ "	NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'),'00:00:00')EL,NVL(SFT_NIGHT_FLAG,'N'), "
						+ "  NVL(TO_CHAR(SFT_MARK_ABSENT_AFTER, 'HH24:MI:SS'),'00:00:00'), "
						+ "  SFT_FLEXITIME_ALLOWED, NVL(TO_CHAR(SFT_FLEXI_LATE_MARK, 'HH24:MI:SS'),'00:00:00'), "
						+ "  NVL(TO_CHAR(SFT_FLEXI_HALFDAY_MARK, 'HH24:MI:SS'),'00:00:00'), NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI:SS'),'00:00:00') "
						+ "	FROM HRMS_EMP_OFFC "
						+ "	INNER JOIN HRMS_SHIFT ON (EMP_SHIFT = HRMS_SHIFT.SHIFT_ID) 	"
						+ "WHERE EMP_ID = " + empCode;
				Object[][] shiftData = getSqlModel()
						.getSingleResult(selectQury);

				if (shiftData != null && shiftData.length > 0
						&& fromDate != null && fromDate.length > 0) {
					year = fromDate[0].substring(6, 10);
					String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
							+ year
							+ " SET ATT_REG_STATUS_SWIPE=?, ATT_REG_LC_HRS=TO_DATE(?,'hh24:mi:ss'),ATT_REG_EL_HRS=TO_DATE(?,'hh24:mi:ss')"
							+ " ,ATT_REG_STATUS_TWO=? ,ATT_REG_STATUS_ONE='PR',ATT_REG_LOGIN=TO_DATE(?,'hh24:mi:ss'),ATT_REG_LOGOUT=TO_DATE(?,'hh24:mi:ss') "
							+ "  WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
					Object[][] upDataAtt = new Object[fromDate.length][8];
					Object[][] hdrObj = new Object[fromDate.length][3];
					for (int i = 0; i < fromDate.length; i++) {
						String status = "";
						String empInTime = fromTime[i] + ":00";
						String empOutTime = toTime[i] + ":00";
						String offcStartTime = String.valueOf(shiftData[0][2]);
						String offcEndTime = String.valueOf(shiftData[0][3]);
						String firstHalfTime = String.valueOf(shiftData[0][4]);
						String secondHalfTime = String.valueOf(shiftData[0][5]);
						String lateMarkTime = String.valueOf(shiftData[0][6]);
						String earlyLeavingTime = String
								.valueOf(shiftData[0][7]);
						String nightFlag = String.valueOf(shiftData[0][8]);
						String markAbsentAfterThisTime = String
								.valueOf(shiftData[0][9]);
						String flexiTimeFromQuery = String
								.valueOf(shiftData[0][10]);
						String markFlexiLateLessThan = String
								.valueOf(shiftData[0][11]);
						String markFlexiHalfDayLateLessThan = String
								.valueOf(shiftData[0][12]);
						String shiftWorkingHoursFromQuery = String
								.valueOf(shiftData[0][13]);
						// late hrs early hrs extra hrs status in time out time
						// work hrs
						Object[] calculateTime = { "00:00:00", "00:00:00", "N",
								"N", "00:00:00", status, empInTime, empOutTime,
								"00:00:00" };

						upDataAtt[i][0] = "AS";

						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"HH:mm:ss");
							Date dateEmpInTime = sdf.parse(empInTime); // convert in time employee in to date format
							Date dateEmpOutTime = sdf.parse(empOutTime); // convert in time employee out to date format
							Date dateOffcStartTime = sdf.parse(offcStartTime); // convert shift in time in to date format
							Date dateOffcEndTime = sdf.parse(offcEndTime); // convert shift out time in to date format
							Date dateFirstHalfTime = sdf.parse(firstHalfTime); // convert 1st HD time in to date format
							Date dateSecondHalfTime = sdf.parse(secondHalfTime); // convert 2nd HD time in to date format
							Date lateMarkStartTime = sdf.parse(lateMarkTime);
							Date earlyLeavingEndTime = sdf
									.parse(earlyLeavingTime);
							Calendar c1 = Calendar.getInstance();
							/**
							 * DUAL LATE
							 */

							logger.info("dateEmpOutTime : "
									+ dateEmpOutTime.getHours() + ":"
									+ dateEmpOutTime.getMinutes());
							logger.info("dateSecondHalfTime : "
									+ dateSecondHalfTime.getHours() + ":"
									+ dateSecondHalfTime.getMinutes());
							logger.info("earlyLeavingEndTime : "
									+ earlyLeavingEndTime.getHours() + ":"
									+ earlyLeavingEndTime.getMinutes());
							logger.info("dateFirstHalfTime : "
									+ dateFirstHalfTime.getHours() + ":"
									+ dateFirstHalfTime.getMinutes());
							logger.info("lateMarkStartTime : "
									+ lateMarkStartTime.getHours() + ":"
									+ lateMarkStartTime.getMinutes());
							logger.info("earlyLeavingEndTime : "
									+ earlyLeavingEndTime.getHours() + ":"
									+ earlyLeavingEndTime.getMinutes());

							if (((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime
									.before(dateFirstHalfTime) || dateEmpInTime
									.equals(dateFirstHalfTime)))
									&& ((dateEmpOutTime
											.after(dateSecondHalfTime) || dateEmpOutTime
											.equals(dateSecondHalfTime)) && dateEmpOutTime
											.before(earlyLeavingEndTime))) {
								calculateTime[3] = "Y";

								c1.set(dateEmpInTime.getYear(), dateEmpInTime
										.getMonth(), dateEmpInTime.getDate(),
										dateEmpInTime.getHours(), dateEmpInTime
												.getMinutes(), dateEmpInTime
												.getSeconds());
								c1.add(Calendar.HOUR, -lateMarkStartTime
										.getHours());
								c1.add(Calendar.MINUTE, -lateMarkStartTime
										.getMinutes());
								c1.add(Calendar.SECOND, -lateMarkStartTime
										.getSeconds());

								calculateTime[0] = sdf.format(c1.getTime()); // late
																				// hours

								c1.set(earlyLeavingEndTime.getYear(),
										earlyLeavingEndTime.getMonth(),
										earlyLeavingEndTime.getDate(),
										earlyLeavingEndTime.getHours(),
										earlyLeavingEndTime.getMinutes(),
										earlyLeavingEndTime.getSeconds());
								c1.add(Calendar.HOUR, -dateEmpOutTime
										.getHours());
								c1.add(Calendar.MINUTE, -dateEmpOutTime
										.getMinutes());
								c1.add(Calendar.SECOND, -dateEmpOutTime
										.getSeconds());

								calculateTime[1] = sdf.format(c1.getTime()); // early
																				// hours
								status = "DL";
							} else if (dateEmpInTime.after(dateFirstHalfTime)
									&& !empInTime.equals("00:00:00")) {
								calculateTime[2] = "Y";
								status = "HD";
							} else if (dateEmpOutTime
									.before(dateSecondHalfTime)
									&& !empOutTime.equals("00:00:00")) {
								calculateTime[2] = "Y";
								status = "HD";
							} else if (((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime
									.before(dateFirstHalfTime) || dateEmpInTime
									.equals(dateFirstHalfTime)))) {
								c1.set(dateEmpInTime.getYear(), dateEmpInTime
										.getMonth(), dateEmpInTime.getDate(),
										dateEmpInTime.getHours(), dateEmpInTime
												.getMinutes(), dateEmpInTime
												.getSeconds());
								status = "LC";
								c1.add(Calendar.HOUR, -lateMarkStartTime
										.getHours());
								c1.add(Calendar.MINUTE, -lateMarkStartTime
										.getMinutes());
								c1.add(Calendar.SECOND, -lateMarkStartTime
										.getSeconds());

								calculateTime[0] = sdf.format(c1.getTime()); // late
																				// hours

							} else if (((dateEmpOutTime
									.after(dateSecondHalfTime) || dateEmpOutTime
									.equals(dateSecondHalfTime)) && dateEmpOutTime
									.before(earlyLeavingEndTime))) {
								status = "EL";
								c1.set(earlyLeavingEndTime.getYear(),
										earlyLeavingEndTime.getMonth(),
										earlyLeavingEndTime.getDate(),
										earlyLeavingEndTime.getHours(),
										earlyLeavingEndTime.getMinutes(),
										earlyLeavingEndTime.getSeconds());
								c1.add(Calendar.HOUR, -dateEmpOutTime
										.getHours());
								c1.add(Calendar.MINUTE, -dateEmpOutTime
										.getMinutes());
								c1.add(Calendar.SECOND, -dateEmpOutTime
										.getSeconds());

								calculateTime[1] = sdf.format(c1.getTime()); // early
																				// hours

							} else if ((!empInTime.equals("00:00:00") && !empOutTime
									.equals("00:00:00"))
									&& (dateEmpInTime.before(lateMarkStartTime) || dateEmpInTime
											.equals(lateMarkStartTime))
									&& (dateEmpOutTime
											.after(earlyLeavingEndTime) || dateEmpOutTime
											.equals(earlyLeavingEndTime))) {
								status = "IN";
							}

							if (flexiTimeFromQuery.equals("N")) {
								if (!empInTime.equals("00.00.00")
										&& !markAbsentAfterThisTime
												.equals("00:00:00")) {
									System.out.println("UPLOAD : MARK ABSENT");
									int employeeInTime = TimeUtility
											.getMinute(empInTime);
									int markAbsent = TimeUtility
											.getMinute(markAbsentAfterThisTime);
									if(nightFlag.equals("Y")){
										if(employeeInTime < markAbsent){
											status = "AB";
										}
									}
									else if (employeeInTime > markAbsent) {
										status = "AB";
									}
								}
							}
							if (flexiTimeFromQuery.equals("Y")) {
								Calendar flex = Calendar.getInstance();
								flex.setTime(dateEmpOutTime);
								flex.add(Calendar.HOUR, -dateEmpInTime
										.getHours());
								flex.add(Calendar.MINUTE, -dateEmpInTime
										.getMinutes());
								flex.add(Calendar.SECOND, -dateEmpInTime
										.getSeconds());
								int employeeWorkHours = TimeUtility
										.getMinute(String.valueOf(sdf
												.format(flex.getTime())));
								// int shiftWorkingHours =
								// Integer.parseInt(shiftWorkingHoursFromQuery);
								int flexiLateHours = TimeUtility
										.getMinute(markFlexiLateLessThan);
								int flexiHalfDayHours = TimeUtility
										.getMinute(markFlexiHalfDayLateLessThan);

								if (employeeWorkHours == 0) {
									status = "AB";
								} else if (employeeWorkHours >= flexiLateHours) {
									status = "IN";
								} else if (employeeWorkHours < flexiHalfDayHours) {
									status = "HD";
								} else if ((employeeWorkHours < flexiLateHours)
										& (flexiLateHours > flexiHalfDayHours)) {
									status = "LC";
								}

								if (!empInTime.equals("00:00:00")
										&& empOutTime.equals("00:00:00")) {
									status = "HD";
								}
							}
							/**
							 * CALCULATION FOR NIGHT SHIFT
							 */
							if (nightFlag.equals("Y")) {
								int inTime = Integer.parseInt((empInTime
										.substring(0, 5)).replaceAll(":", ""));
								int outTime = Integer.parseInt((empOutTime
										.substring(0, 5)).replaceAll(":", ""));
								int startTime = Integer.parseInt((offcStartTime
										.substring(0, 5)).replaceAll(":", ""));
								int endTime = Integer.parseInt((offcEndTime
										.substring(0, 5)).replaceAll(":", ""));
								int fstHalfTime = Integer
										.parseInt((firstHalfTime
												.substring(0, 5)).replaceAll(
												":", ""));
								int scdHalfTime = Integer
										.parseInt((secondHalfTime.substring(0,
												5)).replaceAll(":", ""));

								int nightlateMarkTime = Integer
										.parseInt((lateMarkTime.substring(0, 5))
												.replaceAll(":", ""));
								int nightearlyLeavingEndTime = Integer
										.parseInt((earlyLeavingTime.substring(
												0, 5)).replaceAll(":", ""));

								if (inTime < 1200) {
									inTime += 2400;
								}
								if (outTime < 1200) {
									outTime += 2400;
								}
								if (startTime < 1200) {
									startTime += 2400;
								}
								if (endTime < 1200) {
									endTime += 2400;
								}
								if (fstHalfTime < 1200) {
									fstHalfTime += 2400;
								}
								if (scdHalfTime < 1200) {
									scdHalfTime += 2400;
								}

								if (nightlateMarkTime < 1200) {
									nightlateMarkTime += 2400;
								}

								if (nightearlyLeavingEndTime < 1200) {
									nightearlyLeavingEndTime += 2400;
								}

								if ((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))
										&& ((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
									status = "DL";
								} else if (inTime > fstHalfTime) {
									status = "HD";
								} else if (outTime < scdHalfTime) {
									status = "HD";
								} else if ((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))) {
									status = "LC";
								} else if (((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
									status = "EL";
								}

								// else if((!empInTime.equals("00:00:00") &&
								// !empOutTime.equals("00:00:00")) &&
								// (dateEmpInTime.before(dateOffcStartTime) ||
								// dateEmpInTime.equals(dateOffcStartTime)) &&
								// (dateEmpOutTime.after(dateOffcEndTime) ||
								// dateEmpOutTime.equals(dateOffcEndTime))){
								else if (inTime > 0
										&& outTime > 0
										&& (inTime == nightlateMarkTime || inTime < nightlateMarkTime)
										&& (outTime == nightearlyLeavingEndTime)
										|| outTime > nightearlyLeavingEndTime) {
									status = "IN";
								} else {
									status = "AB";
								}
							}

							calculateTime[5] = status;

							logger.info("LATE HRS   =        :   "
									+ calculateTime[0]);
							logger.info("EARLY HRS   =       :   "
									+ calculateTime[1]);
							logger.info("STATUS HRS   =      :   "
									+ calculateTime[5]);
							logger.info("IN TIME    =       :   "
									+ calculateTime[6]);
							logger.info("OUT TIME   =        :   "
									+ calculateTime[7]);

							upDataAtt[i][1] = calculateTime[0];// late
							upDataAtt[i][2] = calculateTime[1];// early
							upDataAtt[i][3] = calculateTime[5];// status
							upDataAtt[i][4] = calculateTime[6];// in
							upDataAtt[i][5] = calculateTime[7];// out
							upDataAtt[i][6] = fromDate[i];// date
							upDataAtt[i][7] = empCode;// empcode

							hdrObj[i][0] = calculateTime[6];// in
							hdrObj[i][1] = calculateTime[7];// in
							hdrObj[i][2] = fromDate[i];// date

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					boolean updateresult = false;
					// UPDATE HEADER
					String App = "UPDATE HRMS_SWIPE_REG_HDR  SET SWIPE_REG_STATUS='A' WHERE SWIPE_REG_ID= "
							+ applicationCode;
					getSqlModel().singleExecute(App);
					// UPDATE DTL TABLE
					String dtl = "UPDATE HRMS_SWIPE_REG_DTL  SET SWIPE_REG_DTL_STATUS='A',SWIPE_REG_DTL_ACT_INTIME=TO_DATE(?,'hh24:mi:ss'),SWIPE_REG_DTL_ACT_OUTTIME=TO_DATE(?,'hh24:mi:ss') WHERE SWIPE_REG_DTL_DATE=TO_DATE(?,'DD-MM-YYYY') AND SWIPE_REG_ID= "
							+ applicationCode;
					updateresult = getSqlModel().singleExecute(dtl, hdrObj);
					// UPDATE PATH
					/*
					 * String upQuery="UPDATE HRMS_SWIPE_REG_PATH SET
					 * SWIPE_REG_PATH_COMMENTS=?,
					 * SWIPE_REG_PATH_ASSESS_DATE=SYSDATE,SWIPE_REG_PATH_STATUS=?
					 * WHERE SWIPE_REG_ID ="+applicationCode+" AND
					 * SWIPE_REG_PATH_ASSESS_BY="+regularization.getUserEmpId()+"";
					 * Object[][]upData=new Object[1][2];
					 * upData[0][0]=regularization.getApproverComents();
					 * upData[0][1]="A";
					 * getSqlModel().singleExecute(upQuery,upData);
					 */

					String insPath = "INSERT INTO HRMS_SWIPE_REG_PATH(SWIPE_REG_PATH_CODE,SWIPE_REG_ID,SWIPE_REG_PATH_ASSESS_BY,SWIPE_REG_PATH_STATUS,SWIPE_REG_PATH_ASSESS_DATE,SWIPE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(SWIPE_REG_PATH_CODE),0)+1 FROM HRMS_SWIPE_REG_PATH WHERE SWIPE_REG_ID="
							+ applicationCode + "),?,?,?,sysdate,?)";
					Object[][] path = new Object[1][4];
					path[0][0] = applicationCode;
					path[0][1] = regularization.getUserEmpId();
					path[0][2] = "A";
					path[0][3] = regularization.getApproverComents();
					updateresult = getSqlModel().singleExecute(insPath, path);
					/**
					 * UPDATE ATTENDANCE upAtten
					 */
					updateresult = getSqlModel().singleExecute(upAtten,
							upDataAtt);

				} else {
					result = "Shift configuration not define";
				}
				result = "Application has been approved";

				if (result.equals("Application has been approved")) {
					if (fromDate != null && fromDate.length > 0) {
						for (int i = 0; i < fromDate.length; i++) {
							updateAttendance(year, fromDate[i], empCode);
						}
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private boolean updateAttendance(String year, String date, String empCode) {
		boolean result = false;
		try {
			String updateQuery = " UPDATE HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " set ATT_REG_STATUS_TWO='RG' ,IS_APPL_PROCESS='N'  WHERE ATT_DATE=TO_DATE('"
					+ date + "','DD-MM-YYYY') AND ATT_EMP_ID=" + empCode;
			result = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	// rejectPTAppl

	public String rejectPTAppl(Regularization regularization, String status) {
		String empCode = regularization.getEmpCode();
		String applicationCode = regularization.getApplicationCode();
		logger.info("APPLICATION CODE IS===================  "
				+ applicationCode);
		String result = "";
		/**
		 * LAST APPROVER CHANGES IN ATTENDANCE TABLE
		 */
		// UPDATE HEADER
		String App = "UPDATE HRMS_PT_REG_HDR  SET PT_REG_STATUS='" + status
				+ "' WHERE PT_REG_ID= " + applicationCode;
		getSqlModel().singleExecute(App);
		// UPDATE DTL TABLE
		String dtl = "UPDATE HRMS_PT_REG_DTL  SET PT_REG_DTL_STATUS='" + status
				+ "' WHERE PT_REG_ID= " + applicationCode;
		getSqlModel().singleExecute(dtl);
		// UPDATE PATH
		/*
		 * String upQuery="UPDATE HRMS_PT_REG_PATH SET PT_REG_PATH_COMMENTS=?,
		 * PT_REG_PATH_ASSESS_DATE=SYSDATE,PT_REG_PATH_STATUS=? WHERE PT_REG_ID
		 * ="+applicationCode+" AND
		 * PT_REG_PATH_ASSESS_BY="+regularization.getUserEmpId()+"";
		 * Object[][]upData=new Object[1][2];
		 * upData[0][0]=regularization.getApproverComents();
		 * upData[0][1]=status; getSqlModel().singleExecute(upQuery,upData);
		 */

		String insPath = "INSERT INTO HRMS_PT_REG_PATH(PT_REG_PATH_CODE,PT_REG_ID,PT_REG_PATH_ASSESS_BY,PT_REG_PATH_STATUS,PT_REG_PATH_ASSESS_DATE,PT_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(PT_REG_PATH_CODE),0)+1 FROM HRMS_PT_REG_PATH WHERE PT_REG_ID="
				+ applicationCode + "),?,?,?,sysdate,?)";
		Object[][] path = new Object[1][4];
		path[0][0] = applicationCode;
		path[0][1] = regularization.getUserEmpId();
		path[0][2] = status;
		path[0][3] = regularization.getApproverComents();
		getSqlModel().singleExecute(insPath, path);

		result = "Application has been rejected";
		if (status.equals("B")) {
			result = "Application has been send back";
		}

		return result;
	}

	public String approvePTAppl(Regularization regularization, String empCode,
			String applicationCode) {

		logger.info("APPLICATION CODE IS===================  "
				+ applicationCode);
		String result = "";
		String level = "1";
		String query = "SELECT PT_REG_STATUS,NVL(PT_REG_LEVEL,1),PT_REG_APPROVER FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="
				+ applicationCode + " ";// AND REDRESSAL_STATUS='P'";
		Object[][] data = getSqlModel().getSingleResult(query);
		boolean check = false;
		String approverId = regularization.getUserEmpId();
		if (data != null && data.length > 0) {
			level = String.valueOf(data[0][1]);
			// CHECK ALREADY APPROVED OR NOT
			if (String.valueOf(data[0][2]).equals(approverId)
					&& String.valueOf(data[0][0]).equals("P")) {
				check = true;
			}
		}
		if (!check) {
			return "Application has already been processed";
		}
		Object[][] empFlow = null;
		empFlow = generateEmpFlow(empCode, "Regularize", Integer
				.parseInt(level) + 1);
		/**
		 * NEXT APPROVER IS PRESENT SEND TO NEXT APPROVER
		 */
		if (empFlow != null && empFlow.length != 0) {
			regularization.setSecondApproverCode(String.valueOf(empFlow[0][0]));
			Object[][] updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][2]; // level
			updateApprover[0][1] = empFlow[0][0]; // approver id
			updateApprover[0][2] = empFlow[0][3]; // alternate approver id
			updateApprover[0][3] = applicationCode; // application code

			String nextApp = "UPDATE HRMS_PT_REG_HDR  SET PT_REG_LEVEL=? ,PT_REG_APPROVER=? ,PT_REG_ALT_APPROVER=? WHERE PT_REG_ID=? ";
			getSqlModel().singleExecute(nextApp, updateApprover);
			String upQuery = "UPDATE  HRMS_PT_REG_PATH SET PT_REG_PATH_COMMENTS=?, PT_REG_PATH_ASSESS_DATE=SYSDATE,PT_REG_PATH_STATUS=? WHERE PT_REG_ID ="
					+ applicationCode
					+ " AND PT_REG_PATH_ASSESS_BY="
					+ regularization.getUserEmpId() + "";
			Object[][] upData = new Object[1][2];
			upData[0][0] = regularization.getApproverComents();
			upData[0][1] = "A";
			getSqlModel().singleExecute(upQuery, upData);

			String insPath = "INSERT INTO HRMS_PT_REG_PATH(PT_REG_PATH_CODE,PT_REG_ID,PT_REG_PATH_ASSESS_BY,PT_REG_PATH_STATUS,PT_REG_PATH_ASSESS_DATE,PT_REG_PATH_COMMENTS) VALUES(?,?,?,?,sysdate,?)";
			Object[][] path = new Object[1][5];
			path[0][0] = level;
			path[0][1] = applicationCode;
			path[0][2] = regularization.getUserEmpId();
			path[0][3] = "A";
			path[0][4] = regularization.getApproverComents();
			getSqlModel().singleExecute(insPath, path);

			result = "This application has been forwarded to next approval";

		} else {
			/**
			 * LAST APPROVER CHANGES IN ATTENDANCE TABLE
			 */
			// UPDATE HEADER
			String App = "UPDATE HRMS_PT_REG_HDR  SET PT_REG_STATUS='A' WHERE PT_REG_ID= "
					+ applicationCode;
			getSqlModel().singleExecute(App);
			// UPDATE DTL TABLE
			String dtl = "UPDATE HRMS_PT_REG_DTL  SET PT_REG_DTL_STATUS='A' WHERE PT_REG_ID= "
					+ applicationCode;
			getSqlModel().singleExecute(dtl);
			// UPDATE PATH
			String upQuery = "UPDATE  HRMS_PT_REG_PATH SET PT_REG_PATH_COMMENTS=?, PT_REG_PATH_ASSESS_DATE=SYSDATE,PT_REG_PATH_STATUS=? WHERE PT_REG_ID ="
					+ applicationCode
					+ " AND PT_REG_PATH_ASSESS_BY="
					+ regularization.getUserEmpId() + "";
			Object[][] upData = new Object[1][2];
			upData[0][0] = regularization.getApproverComents();
			upData[0][1] = "A";
			getSqlModel().singleExecute(upQuery, upData);
			String insPath = "INSERT INTO HRMS_PT_REG_PATH(PT_REG_PATH_CODE,PT_REG_ID,PT_REG_PATH_ASSESS_BY,PT_REG_PATH_STATUS,PT_REG_PATH_ASSESS_DATE,PT_REG_PATH_COMMENTS) VALUES(?,?,?,?,sysdate,?)";
			Object[][] path = new Object[1][5];
			path[0][0] = level;
			path[0][1] = applicationCode;
			path[0][2] = regularization.getUserEmpId();
			path[0][3] = "A";
			path[0][4] = regularization.getApproverComents();
			getSqlModel().singleExecute(insPath, path);
			result = "Application has been approved";

			/**
			 * ADJUST PERSONAL TIME AGAINST LATE
			 */
			String qq = "SELECT NVL(SFT_PT_LM_HD_ISADJUSTED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0)  FROM HRMS_SHIFT where HRMS_SHIFT.SHIFT_ID="
					+ "  (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empCode + "  ) ";
			Object[][] shiftCode = getSqlModel().getSingleResult(qq);
			if (shiftCode != null && shiftCode.length > 0
					&& String.valueOf(shiftCode[0][0]).equals("Y")) {

				String getQuery = "SELECT PT_REG_ID,TO_CHAR(PT_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(PT_REG_DTL_FROM_TIME,'HH24:MI'),TO_CHAR(PT_REG_DTL_TO_TIME,'HH24:MI') FROM HRMS_PT_REG_DTL "
						+ "	WHERE PT_REG_ID=" + applicationCode + " ";
				Object[][] dataLate = getSqlModel().getSingleResult(getQuery);
				if (dataLate != null && dataLate.length > 0) {
					String yyyy = String.valueOf(dataLate[0][1]).substring(6,
							10);
					String mmmm = String.valueOf(dataLate[0][1])
							.substring(3, 5);

					String attQuery = " SELECT ATT_EMP_ID,TO_CHAR(ATT_DATE,'DD-MM-YYYY'),TO_CHAR(DECODE(SFT_IN_LM_AFTER_TIME,NULL,SFT_START_TIME,SFT_IN_LM_AFTER_TIME),'HH24:MI') AS SHIFT_TIME,TO_CHAR(ATT_LOGIN,'HH24:MI'),   CASE WHEN ATT_STATUS_TWO='LC' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) WHEN ATT_STATUS_TWO='EL' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 +  		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) 		  END  AS TOTAL ,DECODE(ATT_STATUS_ONE,'PR','Present','AB','Absent')AS STATUS1, 	DECODE(ATT_STATUS_TWO,'LC','Late Coming','EL','Early Leaving','DL','Dual Late','AB','Absent','HD','Half Day')AS STATUS2 	,TO_CHAR(ATT_LATE_HRS, 'HH24:MI'),TO_CHAR(ATT_EARLY_HRS, 'HH24:MI') FROM HRMS_DAILY_ATTENDANCE_"
							+ yyyy
							+ " 	INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_DAILY_ATTENDANCE_"
							+ yyyy
							+ ".ATT_SHIFT_ID) 					WHERE ATT_EMP_ID="
							+ empCode
							+ " AND TO_CHAR(ATT_DATE,'MM')="
							+ mmmm
							+ "  AND ATT_STATUS_ONE IN('PR') AND ATT_STATUS_TWO IN('LC','EL','DL')  AND (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0))>0  AND ( ATT_REG_STATUS_LATE IS NULL OR ATT_REG_STATUS_LATE='NR' ) AND ( ATT_REG_STATUS_SWIPE IS NULL OR ATT_REG_STATUS_SWIPE='NR' ) ";
					Object[][] attData = getSqlModel()
							.getSingleResult(attQuery);
					if (attData != null && attData.length > 0) {
						Utility utiliy = new Utility();
						for (int i = 0; i < dataLate.length; i++) {
							for (int j = 0; j < attData.length; j++) {
								int chkDate1 = utiliy.checkDate(String
										.valueOf(attData[j][1]), String
										.valueOf(dataLate[i][1]));
								if (chkDate1 == 0) {
									int chkDate2 = checkTime(String
											.valueOf(dataLate[i][2]), String
											.valueOf(attData[j][2]));
									int chkDate3 = checkTime(String
											.valueOf(dataLate[i][2]), String
											.valueOf(attData[j][3]));

									int chkDate4 = checkTime(String
											.valueOf(dataLate[i][3]), String
											.valueOf(attData[j][2]));
									int chkDate5 = checkTime(String
											.valueOf(dataLate[i][3]), String
											.valueOf(attData[j][3]));

									if ((chkDate2 == 0 || chkDate2 == -1)
											&& (chkDate3 == -1)
											&& chkDate4 == 1
											&& (chkDate5 == 0 || chkDate5 == 1)) {
										/**
										 * UPDATE ATTENDANCE
										 */

										String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
												+ yyyy
												+ " SET ATT_REG_STATUS_LATE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
										Object[][] upDataAtt = new Object[1][3];
										upDataAtt[0][0] = "AL";
										upDataAtt[0][1] = String
												.valueOf(attData[j][1]);
										upDataAtt[0][2] = empCode;
										getSqlModel().singleExecute(upAtten,
												upDataAtt);
									}
								}

							}
						}
					}

				}

			}
		}

		return result;
	}

	public int checkTime(String date1, String date2) {
		int result = 1234;

		try {
			String DATE_FORMAT = "HH:MM";
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);
			java.util.Date dt1 = sdf.parse(date1);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(dt1);
			java.util.Date dt2 = sdf.parse(date2);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(dt2);
			result = c1.compareTo(c2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String approveRedressalAppl(Regularization regularization,
			String status, String[] leaveCode, String[] rrdressalDays,
			String[] redressalAdjStatus, String[] rPenaltyDays,
			String[] redressalAdjDays, String[] rFromDate) {
		String result = "";
		String level = "1";
		String empCode = regularization.getEmpCode();
		String applicationCode = regularization.getApplicationCode();
		String query = "SELECT REDRESSAL_STATUS,NVL(REDRESSAL_LEVEL,1) FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="
				+ applicationCode + " ";// AND REDRESSAL_STATUS='P'";
		Object[][] data = getSqlModel().getSingleResult(query);
		boolean flag = true;
		if (data != null && data.length > 0) {
			level = String.valueOf(data[0][1]);
			String updateQuery = "UPDATE HRMS_REDRESSAL_HDR SET REDRESSAL_STATUS='"
					+ status + "' WHERE REDRESSAL_ID=" + applicationCode + " ";
			String updateDtlQuery = "UPDATE HRMS_REDRESSAL_DTL SET REDRESSAL_DTL_STATUS='"
					+ status + "' WHERE REDRESSAL_ID=" + applicationCode + " ";
			String upQuery = "UPDATE  HRMS_REDRESSAL_PATH SET REDRESSAL_PATH_COMMENTS=?, REDRESSAL_PATH_ASSESS_DATE=SYSDATE,REDRESSAL_PATH_STATUS='"
					+ status
					+ "' WHERE REDRESSAL_ID ="
					+ applicationCode
					+ " AND REDRESSAL_PATH_ASSESS_BY="
					+ regularization.getUserEmpId() + "";
			Object[][] upData = new Object[1][1];
			upData[0][0] = regularization.getApproverComents();
			if (status.equals("R") || status.equals("B")) {
				flag = getSqlModel().singleExecute(updateQuery);
				getSqlModel().singleExecute(updateDtlQuery);

				String insPath = "INSERT INTO HRMS_REDRESSAL_PATH(REDRESSAL_PATH_CODE,REDRESSAL_ID,REDRESSAL_PATH_ASSESS_BY,REDRESSAL_PATH_STATUS,REDRESSAL_PATH_ASSESS_DATE,REDRESSAL_PATH_COMMENTS) VALUES(?,?,?,?,sysdate,?)";
				Object[][] path = new Object[1][5];
				path[0][0] = level;
				path[0][1] = applicationCode;
				path[0][2] = regularization.getUserEmpId();
				path[0][3] = status;
				path[0][4] = regularization.getApproverComents();
				getSqlModel().singleExecute(insPath, path);
			}
			if (flag) {

				// getSqlModel().singleExecute(upQuery,upData);
			}
			/**
			 * UPDATE LEAVE BALANCE IF APPLICATION HAS BEEN APPROVED
			 */
			if (status.equals("R")) {
				result = "Application has been rejected";
			} else if (status.equals("B")) {
				result = "Application has been send back";
			} else if (status.equals("A")) {

				String policyCode = getLeavePolicyCode(regularization
						.getEmpCode());

				Object[][] empFlow = null;
				empFlow = generateEmpFlow(empCode, "Regularize", Integer
						.parseInt(level) + 1);

				if (empFlow != null && empFlow.length != 0) {
					regularization.setSecondApproverCode(String
							.valueOf(empFlow[0][0]));
					Object[][] updateApprover = new Object[1][4];
					updateApprover[0][0] = empFlow[0][2]; // level
					updateApprover[0][1] = empFlow[0][0]; // approver id
					updateApprover[0][2] = empFlow[0][3]; // alternate
															// approver id
					updateApprover[0][3] = applicationCode; // application code
					String nextApp = "UPDATE HRMS_REDRESSAL_HDR  SET REDRESSAL_LEVEL=? ,REDRESSAL_APPROVER=? ,REDRESSAL_ALT_APPROVER=? WHERE REDRESSAL_ID=? ";
					getSqlModel().singleExecute(nextApp, updateApprover);
					Object[][] update = new Object[leaveCode.length][2];
					for (int i = 0; i < update.length; i++) {
						update[i][0] = rrdressalDays[i];
						update[i][1] = leaveCode[i];
					}
					String updateDtlQuery_1 = "UPDATE HRMS_REDRESSAL_DTL SET REDRESSAL_DTL_REDRESSED_DAYS=? WHERE REDRESSAL_ID="
							+ applicationCode
							+ " AND REDRESSAL_DTL_LEAVE_CODE=? ";
					getSqlModel().singleExecute(updateDtlQuery_1, update);
					/*
					 * String updateDtlApproverQuery = "UPDATE
					 * HRMS_REDRESSAL_HDR SET REDRESSAL_APPROVER=" +
					 * updateApprover[0][1] + " WHERE REDRESSAL_ID=" +
					 * updateApprover[0][3];
					 * getSqlModel().singleExecute(updateDtlApproverQuery);
					 */
					result = "This application has been forwarded to next approval";
				} // end of if
				else {
					Object[][] obj = new Object[1][1];
					obj[0][0] = "A";
					String updateDtlQuery1 = "UPDATE HRMS_REDRESSAL_DTL SET REDRESSAL_DTL_STATUS='A' WHERE REDRESSAL_ID="
							+ applicationCode + " ";
					getSqlModel().getSingleResult(updateDtlQuery1);

					String hdrQuery = "UPDATE HRMS_REDRESSAL_HDR SET REDRESSAL_STATUS='A' WHERE REDRESSAL_ID="
							+ applicationCode + " ";
					getSqlModel().getSingleResult(hdrQuery);
					String updateDtlQuery_1 = "UPDATE HRMS_REDRESSAL_DTL SET REDRESSAL_DTL_REDRESSED_DAYS=? WHERE REDRESSAL_ID="
							+ applicationCode
							+ " AND REDRESSAL_DTL_LEAVE_CODE=? ";

					String upBalanceQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE=LEAVE_CLOSING_BALANCE+? WHERE EMP_ID=? AND LEAVE_CODE=?";
					String leave = "UPDATE HRMS_LEAVE_DTL SET LEAVE_PENALTY_ADJUST_DAYS=? , LEAVE_PENALTY_UNADJUST_DAYS=? WHERE EMP_ID=? AND LEAVE_CODE=? AND LEAVE_FROM_DATE=TO_DATE(?,'DD-MM-YYYY') ";

					String commentQuery = "UPDATE  HRMS_REDRESSAL_PATH SET REDRESSAL_PATH_COMMENTS=?, REDRESSAL_PATH_ASSESS_DATE=SYSDATE,REDRESSAL_PATH_STATUS='"
							+ status
							+ "' WHERE REDRESSAL_ID ="
							+ applicationCode
							+ " AND REDRESSAL_PATH_ASSESS_BY="
							+ regularization.getUserEmpId() + "";
					Object[][] upData_1 = new Object[1][1];
					upData[0][0] = regularization.getApproverComents();
					// getSqlModel().singleExecute(commentQuery,upData_1);
					if (leaveCode != null && leaveCode.length > 0) {
						Object[][] updateData = new Object[leaveCode.length][3];
						Object[][] update = new Object[leaveCode.length][2];
						Object[][] leaveData = new Object[leaveCode.length][5];
						for (int i = 0; i < update.length; i++) {
							update[i][0] = rrdressalDays[i];
							update[i][1] = leaveCode[i];
						}
						for (int i = 0; i < updateData.length; i++) {
							String policy = "SELECT NVL(LEAVE_IS_ZERO_BALANCE,'N') FROM HRMS_LEAVE_POLICY_DTL WHERE LEAVE_POLICY_CODE="
									+ policyCode
									+ " AND LEAVE_CODE="
									+ leaveCode[i];
							Object[][] chkPolicy = getSqlModel()
									.getSingleResult(policy);
							Object[][] policyData = new Object[1][3];

							float penaltyDay = Float
									.parseFloat(rPenaltyDays[i]);
							float redreDay = Float.parseFloat(rrdressalDays[i]);
							float redreAdjDay = Float
									.parseFloat(redressalAdjDays[i]);
							float unAdjDay = 0;
							float adjDay = 0;
							float nonAdjDay = 0;
							float balance = 0;
							// check there is no un adjust leave
							if (redressalAdjStatus[i].equals("Y")) {
								policyData[0][0] = redreDay;
								leaveData[i][0] = (penaltyDay - redreDay);
								leaveData[i][1] = 0;
							} else {
								unAdjDay = penaltyDay - redreAdjDay;

								balance = redreDay >= unAdjDay ? (redreDay - unAdjDay)
										: 0;
								policyData[0][0] = balance;
								// redreDay=Integer.parseInt(rrdressalDays[i]);
								nonAdjDay = redreDay >= unAdjDay ? 0
										: (unAdjDay - redreDay);
								leaveData[i][1] = nonAdjDay;

								adjDay = nonAdjDay == 0 ? (redreAdjDay - (redreDay - unAdjDay))
										: redreAdjDay;
								leaveData[i][0] = adjDay;
							}
							leaveData[i][2] = regularization.getEmpCode();
							leaveData[i][3] = leaveCode[i];
							leaveData[i][4] = rFromDate[i];
							// rPenaltyDays
							policyData[0][1] = regularization.getEmpCode();
							policyData[0][2] = leaveCode[i];
							/**
							 * CHECK POLICY ZERO APPLICABLE OR NOT
							 */
							if (chkPolicy != null
									&& chkPolicy.length > 0
									&& String.valueOf(chkPolicy[0][0]).equals(
											"N")) {
								getSqlModel().singleExecute(upBalanceQuery,
										policyData);
							}
						}
						// getSqlModel().singleExecute(upBalanceQuery,updateData);
						getSqlModel().singleExecute(updateDtlQuery_1, update);
						getSqlModel().singleExecute(leave, leaveData);
					}
					result = "Application has been approved";
				}

			}
		} else {
			result = "This application has been already approved";
		}
		return result;
	}

	/**
	 * ONLINE APPROVED / REJECT / SEND BACK APPLICATION
	 * 
	 * @param request
	 * @param empCode
	 * @param applicationCode
	 * @param status
	 * @param approverComments
	 * @param approverId
	 * @return
	 */
	public String onlineApproveRejectPTAppl(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String approverComments, String approverId) {

		String result = "";
		String level = "1";
		String query = "SELECT PT_REG_STATUS,NVL(PT_REG_LEVEL,1),PT_REG_APPROVER FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="
				+ applicationCode + " ";// AND REDRESSAL_STATUS='P'";
		Object[][] data = getSqlModel().getSingleResult(query);
		boolean check = false;
		if (data != null && data.length > 0) {
			level = String.valueOf(data[0][1]);
			// CHECK ALREADY APPROVED OR NOT
			if (String.valueOf(data[0][2]).equals(approverId)
					&& String.valueOf(data[0][0]).equals("P")) {
				check = true;
			}
		}
		if (!check) {
			return "Application has already been processed";
		}
		Object[][] empFlow = null;
		empFlow = generateEmpFlow(empCode, "Regularize", Integer
				.parseInt(level) + 1);
		/**
		 * FOR SEND BACK & REJECT APPLICATION
		 */

		if (status.trim().equals("R") || status.trim().equals("B")) {
			// UPDATE HEADER
			String App = "UPDATE HRMS_PT_REG_HDR  SET PT_REG_STATUS='" + status
					+ "' WHERE PT_REG_ID= " + applicationCode;
			getSqlModel().singleExecute(App);
			// UPDATE DTL TABLE
			String dtl = "UPDATE HRMS_PT_REG_DTL  SET PT_REG_DTL_STATUS='"
					+ status + "' WHERE PT_REG_ID= " + applicationCode;
			getSqlModel().singleExecute(dtl); // UPDATE PATH

			String insPath = "INSERT INTO HRMS_PT_REG_PATH(PT_REG_PATH_CODE,PT_REG_ID,PT_REG_PATH_ASSESS_BY,PT_REG_PATH_STATUS,PT_REG_PATH_ASSESS_DATE,PT_REG_PATH_COMMENTS) VALUES(?,?,?,?,sysdate,?)";
			Object[][] path = new Object[1][5];
			path[0][0] = level;
			path[0][1] = applicationCode;
			path[0][2] = approverId;
			path[0][3] = status;
			path[0][4] = approverComments;
			getSqlModel().singleExecute(insPath, path);
			result = "Application has been rejected";

			String appQuery = "SELECT NVL(PT_REG_KEEP_INFORM,0) FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="
					+ applicationCode;
			Object[][] keep = getSqlModel().getSingleResult(appQuery);
			String keepData = "";
			if (keep != null && keep.length > 0
					&& !String.valueOf(keep[0][0]).equals("0")) {
				keepData = String.valueOf(keep[0][0]);
			}
			// Mail to employee regarding second approval
			String secondAppr = approverId;// regularization.getUserEmpId();
			try {
				String[] link_param1 = null;
				String[] link_label1 = null;
				/*
				 * sendMailTOSecondApprover(request,"98", secondAppr, empCode,
				 * applicationCode, "", keepData,link_param1,link_label1);
				 */
				sendMailTOSecondApprover(
						request,
						"Personal time  Mail to employee regarding second approval",
						secondAppr, empCode, applicationCode, "", keepData,
						link_param1, link_label1);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			/**
			 * NEXT APPROVER IS PRESENT SEND TO NEXT APPROVER
			 */
			if (empFlow != null && empFlow.length != 0) {
				// regularization.setSecondApproverCode(String.valueOf(empFlow[0][0]));
				Object[][] updateApprover = new Object[1][4];
				updateApprover[0][0] = empFlow[0][2]; // level
				updateApprover[0][1] = empFlow[0][0]; // approver id
				updateApprover[0][2] = empFlow[0][3]; // alternate approver id
				updateApprover[0][3] = applicationCode; // application code
				String secondApprover = String.valueOf(empFlow[0][0]);
				String nextApp = "UPDATE HRMS_PT_REG_HDR  SET PT_REG_LEVEL=? ,PT_REG_APPROVER=? ,PT_REG_ALT_APPROVER=? WHERE PT_REG_ID=? ";
				getSqlModel().singleExecute(nextApp, updateApprover);

				String insPath = "INSERT INTO HRMS_PT_REG_PATH(PT_REG_PATH_CODE,PT_REG_ID,PT_REG_PATH_ASSESS_BY,PT_REG_PATH_STATUS,PT_REG_PATH_ASSESS_DATE,PT_REG_PATH_COMMENTS) VALUES(?,?,?,?,sysdate,?)";
				Object[][] path = new Object[1][5];
				path[0][0] = level;
				path[0][1] = applicationCode;
				path[0][2] = approverId;
				path[0][3] = "A";
				path[0][4] = approverComments;
				getSqlModel().singleExecute(insPath, path);

				result = "This application has been forwarded to next approval";

				try {
					String applicationType = "Regularization";
					// Add approval link -pass parameters to the link
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					link_param[0] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "A" + "#" + "..." + "#"
							+ secondApprover;

					link_param[1] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "R" + "#" + "..." + "#"
							+ secondApprover;
					link_param[2] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "B" + "#" + "..." + "#"
							+ secondApprover;

					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";
					// Mail sent to second approver for approval
					/*
					 * sendMailTOSecondApprover(request,"95", approverId,
					 * secondApprover, applicationCode, secondApprover,
					 * "",link_param,link_label);
					 */
					sendMailTOSecondApprover(
							request,
							"Prsonal time Mail sent to second approver  for approval",
							approverId, secondApprover, applicationCode,
							secondApprover, "", link_param, link_label);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					String[] link_param1 = null;
					String[] link_label1 = null;
					// mail to employee regarding 1st approval
					/*
					 * sendMailTOSecondApprover(request,"97", approverId,
					 * empCode, applicationCode, secondApprover,
					 * "",link_param1,link_label1);
					 */
					sendMailTOSecondApprover(
							request,
							"Personal Time Mail to employee regarding first approval",
							approverId, empCode, applicationCode,
							secondApprover, "", link_param1, link_label1);
				} catch (Exception e) {
					// TODO: handle exception
				}

			} else {
				/**
				 * LAST APPROVER CHANGES IN ATTENDANCE TABLE
				 */
				// UPDATE HEADER
				String App = "UPDATE HRMS_PT_REG_HDR  SET PT_REG_STATUS='A' WHERE PT_REG_ID= "
						+ applicationCode;
				getSqlModel().singleExecute(App);
				// UPDATE DTL TABLE
				String dtl = "UPDATE HRMS_PT_REG_DTL  SET PT_REG_DTL_STATUS='A' WHERE PT_REG_ID= "
						+ applicationCode;
				getSqlModel().singleExecute(dtl); // UPDATE PATH

				String insPath = "INSERT INTO HRMS_PT_REG_PATH(PT_REG_PATH_CODE,PT_REG_ID,PT_REG_PATH_ASSESS_BY,PT_REG_PATH_STATUS,PT_REG_PATH_ASSESS_DATE,PT_REG_PATH_COMMENTS) VALUES(?,?,?,?,sysdate,?)";
				Object[][] path = new Object[1][5];
				path[0][0] = level;
				path[0][1] = applicationCode;
				path[0][2] = approverId;
				path[0][3] = "A";
				path[0][4] = approverComments;
				getSqlModel().singleExecute(insPath, path);
				result = "Application has been approved";

				/**
				 * ADJUST PERSONAL TIME AGAINST LATE
				 */
				String qq = "SELECT NVL(SFT_PT_LM_HD_ISADJUSTED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0)  FROM HRMS_SHIFT where HRMS_SHIFT.SHIFT_ID="
						+ "  (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ empCode + "  ) ";
				Object[][] shiftCode = getSqlModel().getSingleResult(qq);
				if (shiftCode != null && shiftCode.length > 0
						&& String.valueOf(shiftCode[0][0]).equals("Y")) {

					String getQuery = "SELECT PT_REG_ID,TO_CHAR(PT_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(PT_REG_DTL_FROM_TIME,'HH24:MI'),TO_CHAR(PT_REG_DTL_TO_TIME,'HH24:MI') FROM HRMS_PT_REG_DTL "
							+ "	WHERE PT_REG_ID=" + applicationCode + " ";
					Object[][] dataLate = getSqlModel().getSingleResult(
							getQuery);
					if (dataLate != null && dataLate.length > 0) {
						String yyyy = String.valueOf(dataLate[0][1]).substring(
								6, 10);
						String mmmm = String.valueOf(dataLate[0][1]).substring(
								3, 5);

						String attQuery = " SELECT ATT_EMP_ID,TO_CHAR(ATT_DATE,'DD-MM-YYYY'),TO_CHAR(DECODE(SFT_IN_LM_AFTER_TIME,NULL,SFT_START_TIME,SFT_IN_LM_AFTER_TIME),'HH24:MI') AS SHIFT_TIME,TO_CHAR(ATT_LOGIN,'HH24:MI'),   CASE WHEN ATT_STATUS_TWO='LC' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) WHEN ATT_STATUS_TWO='EL' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 +  		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) 		  END  AS TOTAL ,DECODE(ATT_STATUS_ONE,'PR','Present','AB','Absent')AS STATUS1, 	DECODE(ATT_STATUS_TWO,'LC','Late Coming','EL','Early Leaving','DL','Dual Late','AB','Absent','HD','Half Day')AS STATUS2 	,TO_CHAR(ATT_LATE_HRS, 'HH24:MI'),TO_CHAR(ATT_EARLY_HRS, 'HH24:MI') FROM HRMS_DAILY_ATTENDANCE_"
								+ yyyy
								+ " 	INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_DAILY_ATTENDANCE_"
								+ yyyy
								+ ".ATT_SHIFT_ID) 					WHERE ATT_EMP_ID="
								+ empCode
								+ " AND TO_CHAR(ATT_DATE,'MM')="
								+ mmmm
								+ "  AND ATT_STATUS_ONE IN('PR') AND ATT_STATUS_TWO IN('LC','EL','DL')  AND (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0))>0  AND ( ATT_REG_STATUS_LATE IS NULL OR ATT_REG_STATUS_LATE='NR' ) AND ( ATT_REG_STATUS_SWIPE IS NULL OR ATT_REG_STATUS_SWIPE='NR' ) ";
						Object[][] attData = getSqlModel().getSingleResult(
								attQuery);
						if (attData != null && attData.length > 0) {
							Utility utiliy = new Utility();
							for (int i = 0; i < dataLate.length; i++) {
								for (int j = 0; j < attData.length; j++) {
									int chkDate1 = utiliy.checkDate(String
											.valueOf(attData[j][1]), String
											.valueOf(dataLate[i][1]));
									if (chkDate1 == 0) {
										int chkDate2 = checkTime(String
												.valueOf(dataLate[i][2]),
												String.valueOf(attData[j][2]));
										int chkDate3 = checkTime(String
												.valueOf(dataLate[i][2]),
												String.valueOf(attData[j][3]));

										int chkDate4 = checkTime(String
												.valueOf(dataLate[i][3]),
												String.valueOf(attData[j][2]));
										int chkDate5 = checkTime(String
												.valueOf(dataLate[i][3]),
												String.valueOf(attData[j][3]));

										if ((chkDate2 == 0 || chkDate2 == 1)
												&& chkDate3 == -1
												&& chkDate4 == 1
												&& (chkDate5 == 0 || chkDate5 == -1)) {
											/**
											 * UPDATE ATTENDANCE
											 */

											String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
													+ yyyy
													+ " SET ATT_REG_STATUS_LATE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
											Object[][] upDataAtt = new Object[1][3];
											upDataAtt[0][0] = "AL";
											upDataAtt[0][1] = String
													.valueOf(attData[j][1]);
											upDataAtt[0][2] = empCode;
											getSqlModel().singleExecute(
													upAtten, upDataAtt);
										}
									}
								}
							}
						}

					}
				}

				String appQuery = "SELECT NVL(PT_REG_KEEP_INFORM,0) FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="
						+ applicationCode;
				Object[][] keep = getSqlModel().getSingleResult(appQuery);
				String keepData = "";
				if (keep != null && keep.length > 0
						&& !String.valueOf(keep[0][0]).equals("0")) {
					keepData = String.valueOf(keep[0][0]);
				}
				// Mail to employee regarding second approval
				String secondAppr = approverId;// regularization.getUserEmpId();
				try {
					String[] link_param1 = null;
					String[] link_label1 = null;
					/*
					 * sendMailTOSecondApprover(request,"98", secondAppr,
					 * empCode, applicationCode, "",
					 * keepData,link_param1,link_label1);
					 */
					sendMailTOSecondApprover(
							request,
							"Personal time  Mail to employee regarding second approval",
							secondAppr, empCode, applicationCode, "", keepData,
							link_param1, link_label1);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		}

		return result;
	}

	public String approveLateRegAppl(Regularization regularization,
			String[] fromDate) {
		// TODO Auto-generated method stub
		String level = "1";
		String result = "";
		String empCode = regularization.getEmpCode();
		String applicationCode = regularization.getApplicationCode();

		String query = "SELECT LATE_REG_STATUS,NVL(LATE_LEVEL,1) FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="
				+ applicationCode + " ";// AND REDRESSAL_STATUS='P'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			level = String.valueOf(data[0][1]);
		}
		Object[][] empFlow = null;
		empFlow = generateEmpFlow(empCode, "Regularize", Integer
				.parseInt(level) + 1);
		/**
		 * NEXT APPROVER IS PRESENT SEND TO NEXT APPROVER
		 */
		if (empFlow != null && empFlow.length != 0) {
			regularization.setSecondApproverCode(String.valueOf(empFlow[0][0]));
			Object[][] updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][2]; // level
			updateApprover[0][1] = empFlow[0][0]; // approver id
			updateApprover[0][2] = empFlow[0][3]; // alternate approver id
			updateApprover[0][3] = applicationCode; // application code
			String nextApp = "UPDATE HRMS_LATE_REG_HDR  SET LATE_LEVEL=? ,LATE_REG_APPROVER=? ,LATE_REG_ALT_APPROVER=? WHERE LATE_REG_ID=? ";
			getSqlModel().singleExecute(nextApp, updateApprover);
			/*
			 * String upQuery="UPDATE HRMS_LATE_REG_PATH SET
			 * LATE_REG_PATH_COMMENTS=?,
			 * LATE_REG_PATH_ASSESS_DATE=SYSDATE,LATE_REG_PATH_STATUS=? WHERE
			 * LATE_REG_ID ="+applicationCode+" AND
			 * LATE_REG_PATH_ASSESS_BY="+regularization.getUserEmpId()+"";
			 * Object[][]upData=new Object[1][2];
			 * upData[0][0]=regularization.getApproverComents();
			 * upData[0][1]="A"; getSqlModel().singleExecute(upQuery,upData);
			 */
			String insPath = "INSERT INTO HRMS_LATE_REG_PATH(LATE_REG_PATH_CODE,LATE_REG_ID,LATE_REG_PATH_ASSESS_BY,LATE_REG_PATH_STATUS,LATE_REG_PATH_ASSESS_DATE,LATE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(LATE_REG_PATH_CODE),0)+1 FROM HRMS_LATE_REG_PATH WHERE LATE_REG_ID="
					+ applicationCode + "),?,?,?,sysdate,?)";
			Object[][] path = new Object[1][4];
			path[0][0] = applicationCode;
			path[0][1] = regularization.getUserEmpId();
			path[0][2] = "A";
			path[0][3] = regularization.getApproverComents();
			getSqlModel().singleExecute(insPath, path);

			result = "This application has been forwarded to next approval";

		} else {
			/**
			 * LAST APPROVER CHANGES IN ATTENDANCE TABLE
			 */
			// UPDATE HEADER
			String App = "UPDATE HRMS_LATE_REG_HDR  SET LATE_REG_STATUS='A' WHERE LATE_REG_ID= "
					+ applicationCode;
			getSqlModel().singleExecute(App);
			// UPDATE DTL TABLE
			String dtl = "UPDATE HRMS_LATE_REG_DTL  SET LATE_REG_DTL_STATUS='A' WHERE LATE_REG_ID= "
					+ applicationCode;
			getSqlModel().singleExecute(dtl);
			// UPDATE PATH
			/*
			 * String upQuery="UPDATE HRMS_LATE_REG_PATH SET
			 * LATE_REG_PATH_COMMENTS=?,
			 * LATE_REG_PATH_ASSESS_DATE=SYSDATE,LATE_REG_PATH_STATUS=? WHERE
			 * LATE_REG_ID ="+applicationCode+" ";//AND
			 * LATE_REG_PATH_ASSESS_BY="+regularization.getUserEmpId()+"";
			 * Object[][]upData=new Object[1][2];
			 * upData[0][0]=regularization.getApproverComents();
			 * upData[0][1]="A"; getSqlModel().singleExecute(upQuery,upData);
			 */

			String insPath = "INSERT INTO HRMS_LATE_REG_PATH(LATE_REG_PATH_CODE,LATE_REG_ID,LATE_REG_PATH_ASSESS_BY,LATE_REG_PATH_STATUS,LATE_REG_PATH_ASSESS_DATE,LATE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(LATE_REG_PATH_CODE),0)+1 FROM HRMS_LATE_REG_PATH WHERE LATE_REG_ID="
					+ applicationCode + "),?,?,?,sysdate,?)";
			Object[][] path = new Object[1][4];
			path[0][0] = applicationCode;
			path[0][1] = regularization.getUserEmpId();
			path[0][2] = "A";
			path[0][3] = regularization.getApproverComents();
			getSqlModel().singleExecute(insPath, path);
			/**
			 * update attendance table
			 */
			if (fromDate != null && fromDate.length > 0) {
				String year = fromDate[0].substring(6, 10);
				String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " SET ATT_REG_STATUS_LATE=?  WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
				Object[][] upDataAtt = new Object[fromDate.length][3];
				for (int i = 0; i < fromDate.length; i++) {
					upDataAtt[i][0] = "AL";
					upDataAtt[i][1] = fromDate[i];
					upDataAtt[i][2] = empCode;
				}
				getSqlModel().singleExecute(upAtten, upDataAtt);
			}
			result = "Application has been approved";
		}

		return result;
	}

	public String onlineApproveRejectLate(HttpServletRequest request,
			String empCode, String applicationCode, String statusMain,
			String comments, String approverId) {
		// TODO Auto-generated method stub
		String level = "1";
		String result = "";
		String query = "SELECT LATE_REG_STATUS,NVL(LATE_LEVEL,1),LATE_REG_APPROVER FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="
				+ applicationCode + " ";// AND REDRESSAL_STATUS='P'";
		Object[][] data = getSqlModel().getSingleResult(query);
		boolean check = false;
		if (data != null && data.length > 0) {
			level = String.valueOf(data[0][1]);
			// CHECK ALREADY APPROVED OR NOT
			if (String.valueOf(data[0][2]).equals(approverId)
					&& String.valueOf(data[0][0]).equals("P")) {
				check = true;
			}
		}
		if (!check) {
			return "Application has already been processed";
		}
		String leaveQuery = "SELECT TO_CHAR(LATE_REG_DTL_DATE,'DD-MM-YYYY'),LATE_REG_DTL_LEAVE_ID,TO_CHAR(LATE_REG_DTL_HRS_DED,'HH24:MI')  FROM HRMS_LATE_REG_DTL WHERE LATE_REG_ID= "
				+ applicationCode + "	ORDER BY LATE_REG_DTL_DATE";
		Object[][] fromDate = getSqlModel().getSingleResult(leaveQuery);

		if (statusMain.equals("R") || statusMain.equals("B")) {
			String App = "UPDATE HRMS_LATE_REG_HDR  SET LATE_REG_STATUS='"
					+ statusMain + "' WHERE LATE_REG_ID= " + applicationCode;
			getSqlModel().singleExecute(App);
			// UPDATE DTL TABLE
			String dtl = "UPDATE HRMS_LATE_REG_DTL  SET LATE_REG_DTL_STATUS='"
					+ statusMain + "' WHERE LATE_REG_ID= " + applicationCode;
			getSqlModel().singleExecute(dtl);
			// UPDATE PATH
			String insPath = "INSERT INTO HRMS_LATE_REG_PATH(LATE_REG_PATH_CODE,LATE_REG_ID,LATE_REG_PATH_ASSESS_BY,LATE_REG_PATH_STATUS,LATE_REG_PATH_ASSESS_DATE,LATE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(LATE_REG_PATH_CODE),0)+1 FROM HRMS_LATE_REG_PATH WHERE LATE_REG_ID="
					+ applicationCode + "),?,?,?,sysdate,?)";
			Object[][] path = new Object[1][4];
			path[0][0] = applicationCode;
			path[0][1] = approverId;
			path[0][2] = statusMain;
			path[0][3] = comments;
			getSqlModel().singleExecute(insPath, path);

			/**
			 * update attendance table
			 */
			if (fromDate != null && fromDate.length > 0) {
				String year = String.valueOf(fromDate[0][0]).substring(6, 10);
				String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " SET ATT_REG_STATUS_LATE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
				Object[][] upDataAtt = new Object[fromDate.length][3];
				for (int i = 0; i < fromDate.length; i++) {
					upDataAtt[i][0] = "RL";
					if (statusMain.equals("B")) {
						upDataAtt[i][0] = "BL";
					}
					upDataAtt[i][1] = fromDate[i][0];
					upDataAtt[i][2] = empCode;
				}
				getSqlModel().singleExecute(upAtten, upDataAtt);
			}
			/**
			 * UPDATE LEAVE BALANCE
			 */
			if (fromDate != null && fromDate.length > 0) {
				String year = String.valueOf(fromDate[0][0]).substring(6, 10);
				/**
				 * GET SHIFT INFORMATION
				 */
				String qq = "SELECT NVL(SFT_LM_HRS_ISENABLED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0)  FROM HRMS_SHIFT where HRMS_SHIFT.SHIFT_ID="
						+ "  (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ empCode + "  ) ";
				Object[][] shiftCode = getSqlModel().getSingleResult(qq);
				String shifTime = "";
				if (shiftCode != null && shiftCode.length > 0) {
					shifTime = String.valueOf(shiftCode[0][4]);
				}
				int shiftMinuts = getMinute(shifTime);

				String upAtten = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE=?,LEAVE_HRS_CLOSING_BALANCE=TO_DATE(?,'HH24:MI') WHERE LEAVE_CODE=? AND EMP_ID=?";

				for (int i = 0; i < fromDate.length; i++) {
					Object[][] upDataAtt = new Object[1][4];
					String balQuery = "SELECT NVL(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),'00:00'),NVL(LEAVE_CLOSING_BALANCE,0) FROM HRMS_LEAVE_BALANCE WHERE LEAVE_CODE="
							+ fromDate[i][1] + " AND EMP_ID=" + empCode;
					Object[][] balData = getSqlModel()
							.getSingleResult(balQuery);
					int exitBalMi = 0;
					float days = 0.0f;
					int updateBal = getMinute(String.valueOf(fromDate[i][2]));
					int tot = 0;
					if (balData != null && balData.length > 0) {
						exitBalMi = getMinute(String.valueOf(balData[0][0]));
						days = Float.parseFloat(String.valueOf(balData[0][1]));
					}
					tot = exitBalMi + updateBal;
					if ((tot) >= (shiftMinuts / 2)) {
						days = days + 0.5f;
						tot = tot - (shiftMinuts / 2);
					}

					if ((tot) >= (shiftMinuts / 2)) {
						days = days + 0.5f;
						tot = tot - (shiftMinuts / 2);
					}
					upDataAtt[0][0] = days;
					upDataAtt[0][1] = getHH_MM(tot);
					upDataAtt[0][2] = fromDate[i][1];
					upDataAtt[0][3] = empCode;
					getSqlModel().singleExecute(upAtten, upDataAtt);
				}

			}
			result = "Application has been rejected";
			if (statusMain.equals("B")) {
				result = "Application has been send back";
			}
			String appQuery = "SELECT NVL(LATE_REG_KEEP_INFORM,0) FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="
					+ applicationCode;
			Object[][] keep = getSqlModel().getSingleResult(appQuery);
			String keepData = "";
			if (keep != null && keep.length > 0
					&& !String.valueOf(keep[0][0]).equals("0")) {
				keepData = String.valueOf(keep[0][0]);
			}
			String level1[] = null;
			String lable[] = null;
			try {
				/*
				 * sendMailTOSecondApprover(request,"88", approverId, empCode,
				 * applicationCode, "", keepData, level1, lable);
				 */
				sendMailTOSecondApprover(
						request,
						"Late regularization Mail to employee regarding second approval",
						approverId, empCode, applicationCode, "", keepData,
						level1, lable);
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {
			Object[][] empFlow = null;
			empFlow = generateEmpFlow(empCode, "Regularize", Integer
					.parseInt(level) + 1);
			/**
			 * NEXT APPROVER IS PRESENT SEND TO NEXT APPROVER
			 */

			if (empFlow != null && empFlow.length != 0) {
				System.out
						.println("UNDER  APPROVER  NO ********************************   :  "
								+ level);
				String secondApprover = String.valueOf(empFlow[0][0]);
				Object[][] updateApprover = new Object[1][4];
				updateApprover[0][0] = empFlow[0][2]; // level
				updateApprover[0][1] = empFlow[0][0]; // approver id
				updateApprover[0][2] = empFlow[0][3]; // alternate approver id
				updateApprover[0][3] = applicationCode; // application code
				String nextApp = "UPDATE HRMS_LATE_REG_HDR  SET LATE_LEVEL=? ,LATE_REG_APPROVER=? ,LATE_REG_ALT_APPROVER=? WHERE LATE_REG_ID=? ";
				getSqlModel().singleExecute(nextApp, updateApprover);
				String insPath = "INSERT INTO HRMS_LATE_REG_PATH(LATE_REG_PATH_CODE,LATE_REG_ID,LATE_REG_PATH_ASSESS_BY,LATE_REG_PATH_STATUS,LATE_REG_PATH_ASSESS_DATE,LATE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(LATE_REG_PATH_CODE),0)+1 FROM HRMS_LATE_REG_PATH WHERE LATE_REG_ID="
						+ applicationCode + "),?,?,?,sysdate,?)";
				Object[][] path = new Object[1][4];
				path[0][0] = applicationCode;
				path[0][1] = approverId;
				path[0][2] = "A";
				path[0][3] = comments;
				getSqlModel().singleExecute(insPath, path);
				result = "This application has been forwarded to next approval";
				String applicationType = "LateRegularization";
				// Add approval link -pass parameters to the link
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				link_param[0] = applicationType + "#" + empCode + "#"
						+ applicationCode + "#" + "A" + "#" + "..." + "#"
						+ secondApprover;

				link_param[1] = applicationType + "#" + empCode + "#"
						+ applicationCode + "#" + "R" + "#" + "..." + "#"
						+ secondApprover;
				link_param[2] = applicationType + "#" + empCode + "#"
						+ applicationCode + "#" + "B" + "#" + "..." + "#"
						+ secondApprover;

				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";
				try {
					// Mail sent to second approver for approval
					/*
					 * sendMailTOSecondApprover(request,"85", approverId,
					 * secondApprover, applicationCode, secondApprover, "",
					 * link_param, link_label);
					 */
					sendMailTOSecondApprover(
							request,
							"Late Regularization Mail sent to second approver  for approval",
							approverId, secondApprover, applicationCode,
							secondApprover, "", link_param, link_label);
				} catch (Exception e) {
					// TODO: handle exception
				}
				// Mail to employee regarding first approval
				String[] link_param1 = null;
				String[] link_label1 = null;
				try {
					/*
					 * sendMailTOSecondApprover(request,"87", approverId,
					 * empCode, applicationCode, secondApprover, "",
					 * link_param1, link_label1);
					 */
					sendMailTOSecondApprover(
							request,
							"Late regularization Mail to employee regarding first approval",
							approverId, empCode, applicationCode,
							secondApprover, "", link_param1, link_label1);
				} catch (Exception e) {
					// TODO: handle exception
				}

			} else {
				/**
				 * LAST APPROVER CHANGES IN ATTENDANCE TABLE
				 */
				// UPDATE HEADER
				String App = "UPDATE HRMS_LATE_REG_HDR  SET LATE_REG_STATUS='A' WHERE LATE_REG_ID= "
						+ applicationCode;
				getSqlModel().singleExecute(App);
				// UPDATE DTL TABLE
				String dtl = "UPDATE HRMS_LATE_REG_DTL  SET LATE_REG_DTL_STATUS='A' WHERE LATE_REG_ID= "
						+ applicationCode;
				getSqlModel().singleExecute(dtl);
				String insPath = "INSERT INTO HRMS_LATE_REG_PATH(LATE_REG_PATH_CODE,LATE_REG_ID,LATE_REG_PATH_ASSESS_BY,LATE_REG_PATH_STATUS,LATE_REG_PATH_ASSESS_DATE,LATE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(LATE_REG_PATH_CODE),0)+1 FROM HRMS_LATE_REG_PATH WHERE LATE_REG_ID="
						+ applicationCode + "),?,?,?,sysdate,?)";
				Object[][] path = new Object[1][4];
				path[0][0] = applicationCode;
				path[0][1] = approverId;
				path[0][2] = "A";
				path[0][3] = comments;
				getSqlModel().singleExecute(insPath, path);
				/**
				 * cef update attendance table
				 */
				if (fromDate != null && fromDate.length > 0) {
					String year = String.valueOf(fromDate[0][0]).substring(6,
							10);
					String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
							+ year
							+ " SET ATT_REG_STATUS_LATE=?  WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
					Object[][] upDataAtt = new Object[fromDate.length][3];
					for (int i = 0; i < fromDate.length; i++) {
						upDataAtt[i][0] = "AL";
						upDataAtt[i][1] = fromDate[i][0];
						upDataAtt[i][2] = empCode;
					}
					getSqlModel().singleExecute(upAtten, upDataAtt);
				}
				result = "Application has been approved";

				// Mail to employee regarding second approval
				String appQuery = "SELECT NVL(LATE_REG_KEEP_INFORM,0) FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="
						+ applicationCode;
				Object[][] keep = getSqlModel().getSingleResult(appQuery);
				String keepData = "";
				if (keep != null && keep.length > 0
						&& !String.valueOf(keep[0][0]).equals("0")) {
					keepData = String.valueOf(keep[0][0]);
				}
				String secondAppr = approverId;
				String[] link_param1 = null;
				String[] link_label1 = null;
				try {
					/*
					 * sendMailTOSecondApprover(request,"88", secondAppr,
					 * empCode, applicationCode, "", keepData, link_param1,
					 * link_label1);
					 */
					sendMailTOSecondApprover(
							request,
							"Late regularization Mail to employee regarding second approval",
							secondAppr, empCode, applicationCode, "", keepData,
							link_param1, link_label1);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		return result;
	}

	/**
	 * METHOD FOR ONLINE APPROVED / REJECT / SEND BACK APPLICATION
	 * 
	 * @param request
	 * @param empCode
	 * @param applicationCode
	 * @param statusMain
	 * @param comments
	 * @param approverId
	 * @return
	 */

	public String onlineApproveRejectSwipe(HttpServletRequest request,
			String empCode, String applicationCode, String statusMain,
			String comments, String approverId) {
		String result="";
		try {
			String level = "1";
			result = "";
			String query = "SELECT SWIPE_REG_STATUS,NVL(SWIPE_LEVEL,1),SWIPE_REG_APPROVER FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
					+ applicationCode + " ";// AND REDRESSAL_STATUS='P'";
			Object[][] data = getSqlModel().getSingleResult(query);
			boolean check = false;
			if (data != null && data.length > 0) {
				level = String.valueOf(data[0][1]);
				// CHECK ALREADY APPROVED OR NOT
				if (String.valueOf(data[0][2]).equals(approverId)
						&& String.valueOf(data[0][0]).equals("P")) {
					check = true;
				}
			}
			if (!check) {
				return "Application has already been processed";
			}
			String leaveQuery = "SELECT TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(SWIPE_REG_DTL_ACT_INTIME,'HH24:MI'),TO_CHAR(SWIPE_REG_DTL_ACT_OUTTIME,'HH24:MI')  FROM HRMS_SWIPE_REG_DTL WHERE SWIPE_REG_ID= "
					+ applicationCode + "	ORDER BY SWIPE_REG_DTL_DATE";
			Object[][] fromDate = getSqlModel().getSingleResult(leaveQuery);
			if (statusMain.equals("R") || statusMain.equals("B")) {
				// UPDATE HEADER
				String App = "UPDATE HRMS_SWIPE_REG_HDR  SET SWIPE_REG_STATUS='"
						+ statusMain
						+ "' WHERE SWIPE_REG_ID= "
						+ applicationCode;
				getSqlModel().singleExecute(App);
				// UPDATE DTL TABLE
				String dtl = "UPDATE HRMS_SWIPE_REG_DTL  SET SWIPE_REG_DTL_STATUS='"
						+ statusMain
						+ "' WHERE SWIPE_REG_ID= "
						+ applicationCode;
				getSqlModel().singleExecute(dtl);
				String insPath = "INSERT INTO HRMS_SWIPE_REG_PATH(SWIPE_REG_PATH_CODE,SWIPE_REG_ID,SWIPE_REG_PATH_ASSESS_BY,SWIPE_REG_PATH_STATUS,SWIPE_REG_PATH_ASSESS_DATE,SWIPE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(SWIPE_REG_PATH_CODE),0)+1 FROM HRMS_SWIPE_REG_PATH WHERE SWIPE_REG_ID="
						+ applicationCode + "),?,?,?,sysdate,?)";
				Object[][] path = new Object[1][4];
				path[0][0] = applicationCode;
				path[0][1] = approverId;
				path[0][2] = statusMain;
				path[0][3] = comments;
				getSqlModel().singleExecute(insPath, path);
				/**
				 * update attendance table
				 */
				if (fromDate != null && fromDate.length > 0) {
					String year = String.valueOf(fromDate[0][0]).substring(6,
							10);
					String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
							+ year
							+ " SET ATT_REG_STATUS_LATE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
					Object[][] upDataAtt = new Object[fromDate.length][3];
					for (int i = 0; i < fromDate.length; i++) {
						upDataAtt[i][0] = "RS";
						if (statusMain.equals("B")) {
							upDataAtt[i][0] = "BS";
						}
						upDataAtt[i][1] = fromDate[i][0];
						upDataAtt[i][2] = empCode;
					}
					getSqlModel().singleExecute(upAtten, upDataAtt);
				}
				result = "Application has been rejected";
				if (statusMain.equals("B")) {
					result = "Application has been send back";
				}
				String appQuery = "SELECT NVL(SWIPE_REG_KEEP_INFORM,0) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
						+ applicationCode;
				Object[][] keep = getSqlModel().getSingleResult(appQuery);
				String keepData = "";
				if (keep != null && keep.length > 0
						&& !String.valueOf(keep[0][0]).equals("0")) {
					keepData = String.valueOf(keep[0][0]);
				}
				String[] link_param = null;
				String[] link_label = null;
				try {
					/*
					 * sendMailTOSecondApprover(request,"93", approverId, empCode,
					 * applicationCode, "", keepData, link_param, link_label);
					 */
					sendMailTOSecondApprover(
							request,
							"Attendance regularization Mail to employee regarding second approval",
							approverId, empCode, applicationCode, "", keepData,
							link_param, link_label);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				/**
				 * status is approved
				 */

				Object[][] empFlow = null;
				empFlow = generateEmpFlow(empCode, "Regularize", Integer
						.parseInt(level) + 1);
				/**
				 * NEXT APPROVER IS PRESENT SEND TO NEXT APPROVER
				 */
				if (empFlow != null && empFlow.length > 0) {
					Object[][] updateApprover = new Object[1][4];
					updateApprover[0][0] = empFlow[0][2]; // level
					updateApprover[0][1] = empFlow[0][0]; // approver id
					updateApprover[0][2] = empFlow[0][3]; // alternate approver id
					updateApprover[0][3] = applicationCode; // application code
					String secondApprover = String.valueOf(empFlow[0][0]);
					String nextApp = "UPDATE HRMS_SWIPE_REG_HDR  SET SWIPE_LEVEL=? ,SWIPE_REG_APPROVER=? ,SWIPE_REG_ALT_APPROVER=? WHERE SWIPE_REG_ID=? ";
					getSqlModel().singleExecute(nextApp, updateApprover);
					String insPath = "INSERT INTO HRMS_SWIPE_REG_PATH(SWIPE_REG_PATH_CODE,SWIPE_REG_ID,SWIPE_REG_PATH_ASSESS_BY,SWIPE_REG_PATH_STATUS,SWIPE_REG_PATH_ASSESS_DATE,SWIPE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(SWIPE_REG_PATH_CODE),0)+1 FROM HRMS_SWIPE_REG_PATH WHERE SWIPE_REG_ID="
							+ applicationCode + "),?,?,?,sysdate,?)";
					Object[][] path = new Object[1][4];
					path[0][0] = applicationCode;
					path[0][1] = approverId;
					path[0][2] = "A";
					path[0][3] = comments;
					getSqlModel().singleExecute(insPath, path);

					Object[][] hdrObj = new Object[fromDate.length][3];
					for (int i = 0; i < fromDate.length; i++) {
						hdrObj[i][0] = fromDate[i][1];// in
						hdrObj[i][1] = fromDate[i][2];// out
						hdrObj[i][2] = fromDate[i][0];// date
					}
					String dtl = "UPDATE HRMS_SWIPE_REG_DTL  SET SWIPE_REG_DTL_ACT_INTIME=TO_DATE(?,'hh24:mi:ss'),SWIPE_REG_DTL_ACT_OUTTIME=TO_DATE(?,'hh24:mi:ss') WHERE SWIPE_REG_DTL_DATE=TO_DATE(?,'DD-MM-YYYY') AND SWIPE_REG_ID= "
							+ applicationCode;
					getSqlModel().singleExecute(dtl, hdrObj);
					result = "This application has been forwarded to next approval";
					String applicationType = "SwipeRegularization";
					// Add approval link -pass parameters to the link
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					link_param[0] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "A" + "#" + "..." + "#"
							+ secondApprover;

					link_param[1] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "R" + "#" + "..." + "#"
							+ secondApprover;
					link_param[2] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "B" + "#" + "..." + "#"
							+ secondApprover;

					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";
					try {
						// Mail sent to second approver for approval
						/*
						 * sendMailTOSecondApprover(request,"90", approverId,
						 * secondApprover, applicationCode, secondApprover, "",
						 * link_param, link_label);
						 */
						sendMailTOSecondApprover(
								request,
								"Attendance Regularization Mail sent to second approver  for approval",
								approverId, secondApprover, applicationCode,
								secondApprover, "", link_param, link_label);
					} catch (Exception e) {
						// TODO: handle exception
					}
					String[] link_param1 = null;
					String[] link_label1 = null;
					try {
						// Mail to employee regarding first approval
						/*
						 * sendMailTOSecondApprover(request,"92", approverId,
						 * empCode, applicationCode, secondApprover, "",
						 * link_param1, link_label1);
						 */
						sendMailTOSecondApprover(
								request,
								"Attendance Mail to employee regarding first approval",
								approverId, empCode, applicationCode,
								secondApprover, "", link_param1, link_label1);
					} catch (Exception e) {
						// TODO: handle exception
					}

				} else {
					/**
					 * LAST APPROVER
					 */

					/*
					 * String selectQury = "SELECT EMP_SHIFT, SHIFT_NAME,
					 * TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI:SS')START_TIME, " +"
					 * TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI:SS')END_TIME,
					 * TO_CHAR(HRMS_SHIFT.SFT_IN_HD_AFTER_TIME,
					 * 'HH24:MI:SS')FIRST_HALF, " +"
					 * TO_CHAR(HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME,
					 * 'HH24:MI:SS')SECOND_HALF ," +"
					 * TO_CHAR(HRMS_SHIFT.SFT_IN_LM_AFTER_TIME,
					 * 'HH24:MI:SS')LATE_MARK ," +"
					 * TO_CHAR(HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME,
					 * 'HH24:MI:SS')EL,NVL(SFT_NIGHT_FLAG,'N') " +" FROM
					 * HRMS_EMP_OFFC " +" INNER JOIN HRMS_SHIFT ON (EMP_SHIFT =
					 * HRMS_SHIFT.SHIFT_ID) " + "WHERE EMP_ID = " + empCode;
					 */
					String selectQury = "SELECT EMP_SHIFT, SHIFT_NAME, NVL(TO_CHAR(HRMS_SHIFT.SFT_START_TIME, 'HH24:MI:SS'),'00:00:00')START_TIME, "
							+ "	NVL(TO_CHAR(HRMS_SHIFT.SFT_END_TIME, 'HH24:MI:SS'),'00:00:00')END_TIME, NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_HD_AFTER_TIME, 'HH24:MI:SS'),'00:00:00')FIRST_HALF, "
							+ "	NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_HD_BEFORE_TIME, 'HH24:MI:SS'),'00:00:00')SECOND_HALF ,"
							+ "	NVL(TO_CHAR(HRMS_SHIFT.SFT_IN_LM_AFTER_TIME, 'HH24:MI:SS'),'00:00:00')LATE_MARK ,"
							+ "	NVL(TO_CHAR(HRMS_SHIFT.SFT_OUT_EL_BEFORE_TIME, 'HH24:MI:SS'),'00:00:00')EL,NVL(SFT_NIGHT_FLAG,'N') "
							+ "	FROM HRMS_EMP_OFFC "
							+ "	INNER JOIN HRMS_SHIFT ON (EMP_SHIFT = HRMS_SHIFT.SHIFT_ID) 	"
							+ "WHERE EMP_ID = " + empCode;
					Object[][] shiftData = getSqlModel().getSingleResult(
							selectQury);

					if (shiftData != null && shiftData.length > 0
							&& fromDate != null && fromDate.length > 0) {
						String year = String.valueOf(fromDate[0][0]).substring(
								6, 10);

						String upAtten = "UPDATE HRMS_DAILY_ATTENDANCE_"
								+ year
								+ " SET ATT_REG_STATUS_SWIPE=?, ATT_REG_LC_HRS=TO_DATE(?,'hh24:mi:ss'),ATT_REG_EL_HRS=TO_DATE(?,'hh24:mi:ss')"
								+ " ,ATT_REG_STATUS_TWO=? ,ATT_REG_STATUS_ONE='PR',ATT_REG_LOGIN=TO_DATE(?,'hh24:mi:ss'),ATT_REG_LOGOUT=TO_DATE(?,'hh24:mi:ss') "
								+ " WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=?";
						Object[][] upDataAtt = new Object[fromDate.length][8];
						Object[][] hdrObj = new Object[fromDate.length][3];
						for (int i = 0; i < fromDate.length; i++) {
							String status = "";
							String empInTime = String.valueOf(fromDate[i][1])
									+ ":00";// in time
							String empOutTime = String.valueOf(fromDate[i][2])
									+ ":00";
							String offcStartTime = String
									.valueOf(shiftData[0][2]);
							String offcEndTime = String
									.valueOf(shiftData[0][3]);
							String firstHalfTime = String
									.valueOf(shiftData[0][4]);
							String secondHalfTime = String
									.valueOf(shiftData[0][5]);
							String lateMarkTime = String
									.valueOf(shiftData[0][6]);
							String earlyLeavingTime = String
									.valueOf(shiftData[0][7]);
							String nightFlag = String.valueOf(shiftData[0][8]);
							// late hrs early hrs extra hrs status in time out time
							// work hrs
							Object[] calculateTime = { "00:00:00", "00:00:00",
									"N", "N", "00:00:00", status, empInTime,
									empOutTime, "00:00:00" };

							upDataAtt[i][0] = "AS";

							try {
								SimpleDateFormat sdf = new SimpleDateFormat(
										"HH:mm:ss");
								Date dateEmpInTime = sdf.parse(empInTime); // convert
								// in
								// time
								// employee
								// in to
								// date
								// format
								Date dateEmpOutTime = sdf.parse(empOutTime); // convert
								// in
								// time
								// employee
								// out
								// to
								// date
								// format
								Date dateOffcStartTime = sdf
										.parse(offcStartTime); // convert
								// shift
								// in
								// time
								// in
								// to
								// date
								// format
								Date dateOffcEndTime = sdf.parse(offcEndTime); // convert
								// shift
								// out
								// time
								// in
								// to
								// date
								// format
								Date dateFirstHalfTime = sdf
										.parse(firstHalfTime); // convert
								// fist
								// half
								// day
								// time
								// in
								// to
								// date
								// format
								Date dateSecondHalfTime = sdf
										.parse(secondHalfTime); // convert
								// second
								// half
								// day
								// time
								// in
								// to
								// date
								// format

								Date lateMarkStartTime = sdf
										.parse(lateMarkTime);
								Date earlyLeavingEndTime = sdf
										.parse(earlyLeavingTime);
								Calendar c1 = Calendar.getInstance();
								/**
								 * DUAL LATE
								 */
								if (((dateEmpInTime.after(lateMarkStartTime)) && (dateEmpInTime
										.before(dateFirstHalfTime) || dateEmpInTime
										.equals(dateFirstHalfTime)))
										&& ((dateEmpOutTime
												.after(dateSecondHalfTime) || dateEmpOutTime
												.equals(dateSecondHalfTime)) && dateEmpOutTime
												.before(earlyLeavingEndTime))) {
									calculateTime[3] = "Y";

									c1.set(dateEmpInTime.getYear(),
											dateEmpInTime.getMonth(),
											dateEmpInTime.getDate(),
											dateEmpInTime.getHours(),
											dateEmpInTime.getMinutes(),
											dateEmpInTime.getSeconds());
									c1.add(Calendar.HOUR, -lateMarkStartTime
											.getHours());
									c1.add(Calendar.MINUTE, -lateMarkStartTime
											.getMinutes());
									c1.add(Calendar.SECOND, -lateMarkStartTime
											.getSeconds());

									calculateTime[0] = sdf.format(c1.getTime()); // late
									// hours

									c1.set(earlyLeavingEndTime.getYear(),
											earlyLeavingEndTime.getMonth(),
											earlyLeavingEndTime.getDate(),
											earlyLeavingEndTime.getHours(),
											earlyLeavingEndTime.getMinutes(),
											earlyLeavingEndTime.getSeconds());
									c1.add(Calendar.HOUR, -dateEmpOutTime
											.getHours());
									c1.add(Calendar.MINUTE, -dateEmpOutTime
											.getMinutes());
									c1.add(Calendar.SECOND, -dateEmpOutTime
											.getSeconds());

									calculateTime[1] = sdf.format(c1.getTime()); // early
									// hours
									status = "DL";
								} else if (dateEmpInTime
										.after(dateFirstHalfTime)
										&& !empInTime.equals("00:00:00")) {
									calculateTime[2] = "Y";
									status = "HD";
								} else if (dateEmpOutTime
										.before(dateSecondHalfTime)
										&& !empOutTime.equals("00:00:00")) {
									calculateTime[2] = "Y";
									status = "HD";
								} else if (((dateEmpInTime
										.after(lateMarkStartTime)) && (dateEmpInTime
										.before(dateFirstHalfTime) || dateEmpInTime
										.equals(dateFirstHalfTime)))) {
									c1.set(dateEmpInTime.getYear(),
											dateEmpInTime.getMonth(),
											dateEmpInTime.getDate(),
											dateEmpInTime.getHours(),
											dateEmpInTime.getMinutes(),
											dateEmpInTime.getSeconds());
									status = "LC";
									c1.add(Calendar.HOUR, -lateMarkStartTime
											.getHours());
									c1.add(Calendar.MINUTE, -lateMarkStartTime
											.getMinutes());
									c1.add(Calendar.SECOND, -lateMarkStartTime
											.getSeconds());

									calculateTime[0] = sdf.format(c1.getTime()); // late
									// hours

								} else if (((dateEmpOutTime
										.after(dateSecondHalfTime) || dateEmpOutTime
										.equals(dateSecondHalfTime)) && dateEmpOutTime
										.before(earlyLeavingEndTime))) {
									status = "EL";
									c1.set(earlyLeavingEndTime.getYear(),
											earlyLeavingEndTime.getMonth(),
											earlyLeavingEndTime.getDate(),
											earlyLeavingEndTime.getHours(),
											earlyLeavingEndTime.getMinutes(),
											earlyLeavingEndTime.getSeconds());
									c1.add(Calendar.HOUR, -dateEmpOutTime
											.getHours());
									c1.add(Calendar.MINUTE, -dateEmpOutTime
											.getMinutes());
									c1.add(Calendar.SECOND, -dateEmpOutTime
											.getSeconds());

									calculateTime[1] = sdf.format(c1.getTime()); // early
									// hours

								} else if ((!empInTime.equals("00:00:00") && !empOutTime
										.equals("00:00:00"))
										&& (dateEmpInTime
												.before(lateMarkStartTime) || dateEmpInTime
												.equals(lateMarkStartTime))
										&& (dateEmpOutTime
												.after(earlyLeavingEndTime) || dateEmpOutTime
												.equals(earlyLeavingEndTime))) {
									status = "IN";
								}
								/**
								 * CALCULATION FOR NIGHT SHIFT
								 */
								if (nightFlag.equals("Y")) {
									int inTime = Integer.parseInt((empInTime
											.substring(0, 5)).replaceAll(":",
											""));
									int outTime = Integer.parseInt((empOutTime
											.substring(0, 5)).replaceAll(":",
											""));
									int startTime = Integer
											.parseInt((offcStartTime.substring(
													0, 5)).replaceAll(":", ""));
									int endTime = Integer.parseInt((offcEndTime
											.substring(0, 5)).replaceAll(":",
											""));
									int fstHalfTime = Integer
											.parseInt((firstHalfTime.substring(
													0, 5)).replaceAll(":", ""));
									int scdHalfTime = Integer
											.parseInt((secondHalfTime
													.substring(0, 5))
													.replaceAll(":", ""));

									int nightlateMarkTime = Integer
											.parseInt((lateMarkTime.substring(
													0, 5)).replaceAll(":", ""));
									int nightearlyLeavingEndTime = Integer
											.parseInt((earlyLeavingTime
													.substring(0, 5))
													.replaceAll(":", ""));

									if (inTime < 1200) {
										inTime += 2400;
									}
									if (outTime < 1200) {
										outTime += 2400;
									}
									if (startTime < 1200) {
										startTime += 2400;
									}
									if (endTime < 1200) {
										endTime += 2400;
									}
									if (fstHalfTime < 1200) {
										fstHalfTime += 2400;
									}
									if (scdHalfTime < 1200) {
										scdHalfTime += 2400;
									}

									if (nightlateMarkTime < 1200) {
										nightlateMarkTime += 2400;
									}

									if (nightearlyLeavingEndTime < 1200) {
										nightearlyLeavingEndTime += 2400;
									}

									if ((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))
											&& ((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
										status = "DL";
									} else if (inTime > fstHalfTime) {
										status = "HD";
									} else if (outTime < scdHalfTime) {
										status = "HD";
									} else if ((inTime > nightlateMarkTime && (inTime < fstHalfTime || inTime == fstHalfTime))) {
										status = "LC";
									} else if (((outTime < nightearlyLeavingEndTime) && (outTime > scdHalfTime || outTime == scdHalfTime))) {
										status = "EL";
									}

									// else if((!empInTime.equals("00:00:00") &&
									// !empOutTime.equals("00:00:00")) &&
									// (dateEmpInTime.before(dateOffcStartTime) ||
									// dateEmpInTime.equals(dateOffcStartTime)) &&
									// (dateEmpOutTime.after(dateOffcEndTime) ||
									// dateEmpOutTime.equals(dateOffcEndTime))){
									else if (inTime > 0
											&& outTime > 0
											&& (inTime == nightlateMarkTime || inTime < nightlateMarkTime)
											&& (outTime == nightearlyLeavingEndTime)
											|| outTime > nightearlyLeavingEndTime) {
										status = "IN";
									} else {
										status = "AB";
									}
								}

								calculateTime[5] = status;

								logger.info("LATE HRS   =        :   "
										+ calculateTime[0]);
								logger.info("EARLY HRS   =       :   "
										+ calculateTime[1]);
								logger.info("STATUS HRS   =      :   "
										+ calculateTime[5]);
								logger.info("IN TIME    =       :   "
										+ calculateTime[6]);
								logger.info("OUT TIME   =        :   "
										+ calculateTime[7]);

								upDataAtt[i][1] = calculateTime[0];// late
								upDataAtt[i][2] = calculateTime[1];// early
								upDataAtt[i][3] = calculateTime[5];// status
								upDataAtt[i][4] = calculateTime[6];// in
								upDataAtt[i][5] = calculateTime[7];// out
								upDataAtt[i][6] = fromDate[i][0];// date
								upDataAtt[i][7] = empCode;// empcode

								hdrObj[i][0] = calculateTime[6];// in
								hdrObj[i][1] = calculateTime[7];// in
								hdrObj[i][2] = fromDate[i][0];// date

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}

						}

						// UPDATE HEADER
						String App = "UPDATE HRMS_SWIPE_REG_HDR  SET SWIPE_REG_STATUS='A' WHERE SWIPE_REG_ID= "
								+ applicationCode;
						getSqlModel().singleExecute(App);
						// UPDATE DTL TABLE
						String dtl = "UPDATE HRMS_SWIPE_REG_DTL  SET SWIPE_REG_DTL_STATUS='A',SWIPE_REG_DTL_ACT_INTIME=TO_DATE(?,'hh24:mi:ss'),SWIPE_REG_DTL_ACT_OUTTIME=TO_DATE(?,'hh24:mi:ss') WHERE SWIPE_REG_DTL_DATE=TO_DATE(?,'DD-MM-YYYY') AND SWIPE_REG_ID= "
								+ applicationCode;
						getSqlModel().singleExecute(dtl, hdrObj);
						String insPath = "INSERT INTO HRMS_SWIPE_REG_PATH(SWIPE_REG_PATH_CODE,SWIPE_REG_ID,SWIPE_REG_PATH_ASSESS_BY,SWIPE_REG_PATH_STATUS,SWIPE_REG_PATH_ASSESS_DATE,SWIPE_REG_PATH_COMMENTS) VALUES((SELECT NVL(MAX(SWIPE_REG_PATH_CODE),0)+1 FROM HRMS_SWIPE_REG_PATH WHERE SWIPE_REG_ID="
								+ applicationCode + "),?,?,?,sysdate,?)";
						Object[][] path = new Object[1][4];
						path[0][0] = applicationCode;
						path[0][1] = approverId;
						path[0][2] = "A";
						path[0][3] = comments;
						getSqlModel().singleExecute(insPath, path);
						/**
						 * UPDATE ATTENDANCE upAtten
						 */
						getSqlModel().singleExecute(upAtten, upDataAtt);
					} else {
						result = "Shift configuration not define";
					}
					result = "Application has been approved";
					// Mail to employee regarding second approval
					String appQuery = "SELECT NVL(SWIPE_REG_KEEP_INFORM,0) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
							+ applicationCode;
					Object[][] keep = getSqlModel().getSingleResult(appQuery);
					String keepData = "";
					if (keep != null && keep.length > 0
							&& !String.valueOf(keep[0][0]).equals("0")) {
						keepData = String.valueOf(keep[0][0]);
					}
					String secondAppr = approverId;
					String[] link_param = null;
					String[] link_label = null;
					try {
						/*
						 * sendMailTOSecondApprover(request,"93", secondAppr,
						 * empCode, applicationCode, "", keepData, link_param,
						 * link_label);
						 */
						sendMailTOSecondApprover(
								request,
								"Attendance regularization Mail to employee regarding second approval",
								secondAppr, empCode, applicationCode, "",
								keepData, link_param, link_label);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				if (result.equals("Application has been approved")) {
					if (fromDate != null && fromDate.length > 0) {
						for (int i = 0; i < fromDate.length; i++) {
							String year =String.valueOf(fromDate[i][0]).substring(6, 10);
							updateAttendance(year, String.valueOf(fromDate[i][0]), empCode);
						}
					}

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return result;
	}

	/**
	 * ONLINE APPROVE / REJECT REDRESSAL APPLICATION
	 * 
	 * @param request
	 * @param empCode
	 * @param applicationCode
	 * @param status
	 * @param comments
	 * @param approverId
	 * @return
	 */

	public String OnlineApproveRejectRedressal(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String comments, String approverId) {
		String result = "";
		String level = "1";
		// String[]redressalAdjStatus :- 2
		// String[]rPenaltyDays :-3
		// String[]redressalAdjDays :-4
		// String[]rFromDate :-5
		String query = "SELECT REDRESSAL_STATUS,NVL(REDRESSAL_LEVEL,1),REDRESSAL_APPROVER FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="
				+ applicationCode + " ";// AND REDRESSAL_STATUS='P'";
		Object[][] data = getSqlModel().getSingleResult(query);
		boolean check = false;
		if (data != null && data.length > 0) {
			level = String.valueOf(data[0][1]);
			// CHECK ALREADY APPROVED OR NOT
			if (String.valueOf(data[0][2]).equals(approverId)
					&& String.valueOf(data[0][0]).equals("P")) {
				check = true;
			}
		}
		if (!check) {
			return "Application has already been processed";
		}
		boolean flag = true;
		if (data != null && data.length > 0) {
			level = String.valueOf(data[0][1]);
			String updateQuery = "UPDATE HRMS_REDRESSAL_HDR SET REDRESSAL_STATUS='"
					+ status + "' WHERE REDRESSAL_ID=" + applicationCode + " ";
			String updateDtlQuery = "UPDATE HRMS_REDRESSAL_DTL SET REDRESSAL_DTL_STATUS='"
					+ status + "' WHERE REDRESSAL_ID=" + applicationCode + " ";
			String insPath = "INSERT INTO HRMS_REDRESSAL_PATH(REDRESSAL_PATH_CODE,REDRESSAL_ID,REDRESSAL_PATH_ASSESS_BY,REDRESSAL_PATH_STATUS,REDRESSAL_PATH_ASSESS_DATE,REDRESSAL_PATH_COMMENTS) VALUES(?,?,?,?,sysdate,?)";
			Object[][] path = new Object[1][5];
			path[0][0] = level;
			path[0][1] = applicationCode;
			path[0][2] = approverId;
			path[0][3] = status;
			path[0][4] = comments;
			getSqlModel().singleExecute(insPath, path);

			if (status.equals("R") || status.equals("B")) {
				flag = getSqlModel().singleExecute(updateQuery);
				getSqlModel().singleExecute(updateDtlQuery);

				String appQuery = "SELECT NVL(REDRESSAL_KEEP_INFORM,0) FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="
						+ applicationCode;
				Object[][] keep = getSqlModel().getSingleResult(appQuery);
				String keepData = "";
				if (keep != null && keep.length > 0
						&& !String.valueOf(keep[0][0]).equals("0")) {
					keepData = String.valueOf(keep[0][0]);
				}
				String[] link_param1 = null;
				String[] link_label1 = null;
				/**
				 * MAIL TO EMPLOYEE REGARDING REJECT/SEND BACK APPLICATION
				 */
				try {
					/*
					 * sendMailTOSecondApprover(request,"83", approverId,
					 * empCode, applicationCode, "", keepData, link_param1,
					 * link_label1);
					 */
					sendMailTOSecondApprover(
							request,
							"Redressal Mail to employee regarding second approval",
							approverId, empCode, applicationCode, "", keepData,
							link_param1, link_label1);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			/**
			 * UPDATE LEAVE BALANCE IF APPLICATION HAS BEEN APPROVED
			 */
			if (status.equals("R")) {
				result = "Application has been rejected";
			} else if (status.equals("B")) {
				result = "Application has been send back";
			} else if (status.equals("A")) {

				String policyCode = getLeavePolicyCode(empCode);

				Object[][] empFlow = null;
				empFlow = generateEmpFlow(empCode, "Regularize", Integer
						.parseInt(level) + 1);

				String leaveQuery = "SELECT REDRESSAL_DTL_LEAVE_CODE,REDRESSAL_DTL_REDRESSED_DAYS,DECODE(REDRESSAL_DTL_PENALTY_DAYS,REDRESSAL_DTL_PENALTY_ADJ_DAYS,'Y','N') ,REDRESSAL_DTL_PENALTY_DAYS ,REDRESSAL_DTL_PENALTY_ADJ_DAYS,TO_CHAR(REDRESSAL_DTL_FROM_DATE,'DD-MM-YYYY') FROM HRMS_REDRESSAL_DTL WHERE REDRESSAL_ID= "
						+ applicationCode + "	ORDER BY REDRESSAL_DTL_FROM_DATE";
				Object[][] leaveCode = getSqlModel()
						.getSingleResult(leaveQuery);

				if (empFlow != null && empFlow.length != 0) {
					String secondApprover = String.valueOf(empFlow[0][0]);
					Object[][] updateApprover = new Object[1][4];
					updateApprover[0][0] = empFlow[0][2]; // level
					updateApprover[0][1] = empFlow[0][0]; // approver id
					updateApprover[0][2] = empFlow[0][3]; // alternate
															// approver id
					updateApprover[0][3] = applicationCode; // application code
					String nextApp = "UPDATE HRMS_REDRESSAL_HDR  SET REDRESSAL_LEVEL=? ,REDRESSAL_APPROVER=? ,REDRESSAL_ALT_APPROVER=? WHERE REDRESSAL_ID=? ";
					getSqlModel().singleExecute(nextApp, updateApprover);
					Object[][] update = new Object[leaveCode.length][2];
					for (int i = 0; i < update.length; i++) {
						update[i][0] = leaveCode[i][i];// REDRESSALDAYS
						update[i][1] = leaveCode[i][0];
					}
					String updateDtlQuery_1 = "UPDATE HRMS_REDRESSAL_DTL SET REDRESSAL_DTL_REDRESSED_DAYS=? WHERE REDRESSAL_ID="
							+ applicationCode
							+ " AND REDRESSAL_DTL_LEAVE_CODE=? ";
					getSqlModel().singleExecute(updateDtlQuery_1, update);
					result = "This application has been forwarded to next approval";
					String applicationType = "RedressalRegularization";
					// Add approval link -pass parameters to the link
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					link_param[0] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "A" + "#" + "..." + "#"
							+ secondApprover;

					link_param[1] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "R" + "#" + "..." + "#"
							+ secondApprover;
					link_param[2] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "B" + "#" + "..." + "#"
							+ secondApprover;

					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";
					// Mail sent to second approver for approval

					try {
						/*
						 * sendMailTOSecondApprover(request,"81", approverId,
						 * secondApprover, applicationCode, secondApprover, "",
						 * link_param, link_label);
						 */
						sendMailTOSecondApprover(
								request,
								"Redressal Mail sent to second approver  for approval",
								approverId, secondApprover, applicationCode,
								secondApprover, "", link_param, link_label);
					} catch (Exception e) {
						// TODO: handle exception
					}
					// Mail to employee regarding first approval
					String[] link_param1 = null;
					String[] link_label1 = null;
					try {
						/*
						 * sendMailTOSecondApprover(request,"82", approverId,
						 * empCode, applicationCode, secondApprover, "",
						 * link_param1, link_label1);
						 */
						sendMailTOSecondApprover(
								request,
								"Redressal Mail to employee regarding first approval",
								approverId, empCode, applicationCode,
								secondApprover, "", link_param1, link_label1);
					} catch (Exception e) {
						// TODO: handle exception
					}

				} // end of if
				else {
					Object[][] obj = new Object[1][1];
					obj[0][0] = "A";
					String updateDtlQuery1 = "UPDATE HRMS_REDRESSAL_DTL SET REDRESSAL_DTL_STATUS='A' WHERE REDRESSAL_ID="
							+ applicationCode + " ";
					getSqlModel().getSingleResult(updateDtlQuery1);

					String hdrQuery = "UPDATE HRMS_REDRESSAL_HDR SET REDRESSAL_STATUS='A' WHERE REDRESSAL_ID="
							+ applicationCode + " ";
					getSqlModel().getSingleResult(hdrQuery);
					String updateDtlQuery_1 = "UPDATE HRMS_REDRESSAL_DTL SET REDRESSAL_DTL_REDRESSED_DAYS=? WHERE REDRESSAL_ID="
							+ applicationCode
							+ " AND REDRESSAL_DTL_LEAVE_CODE=? ";

					String upBalanceQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE=LEAVE_CLOSING_BALANCE+? WHERE EMP_ID=? AND LEAVE_CODE=?";
					String leave = "UPDATE HRMS_LEAVE_DTL SET LEAVE_PENALTY_ADJUST_DAYS=? , LEAVE_PENALTY_UNADJUST_DAYS=? WHERE EMP_ID=? AND LEAVE_CODE=? AND LEAVE_FROM_DATE=TO_DATE(?,'DD-MM-YYYY') ";

					if (leaveCode != null && leaveCode.length > 0) {
						Object[][] updateData = new Object[leaveCode.length][3];
						Object[][] update = new Object[leaveCode.length][2];
						Object[][] leaveData = new Object[leaveCode.length][5];
						for (int i = 0; i < update.length; i++) {
							update[i][0] = leaveCode[i][1];// REDRESSAL DAYS
							update[i][1] = leaveCode[i][0];
						}
						for (int i = 0; i < updateData.length; i++) {
							String policy = "SELECT NVL(LEAVE_IS_ZERO_BALANCE,'N') FROM HRMS_LEAVE_POLICY_DTL WHERE LEAVE_POLICY_CODE="
									+ policyCode
									+ " AND LEAVE_CODE="
									+ leaveCode[i][0];
							Object[][] chkPolicy = getSqlModel()
									.getSingleResult(policy);
							Object[][] policyData = new Object[1][3];

							float penaltyDay = Float.parseFloat(String
									.valueOf(leaveCode[i][3]));// PENELTYDAYS
							float redreDay = Float.parseFloat(String
									.valueOf(leaveCode[i][1]));// REDRESSALDAYS
							float redreAdjDay = Float.parseFloat(String
									.valueOf(leaveCode[i][4]));// redressal adj
																// days
							float unAdjDay = 0;
							float adjDay = 0;
							float nonAdjDay = 0;
							float balance = 0;
							// check there is no un adjust leave
							// leaveCode[i][2] : - REDRESSAL ADJUST DAY
							if (String.valueOf(leaveCode[i][2]).equals("Y")) {
								policyData[0][0] = redreDay;
								leaveData[i][0] = (penaltyDay - redreDay);
								leaveData[i][1] = 0;
							} else {
								unAdjDay = penaltyDay - redreAdjDay;

								balance = redreDay >= unAdjDay ? (redreDay - unAdjDay)
										: 0;
								policyData[0][0] = balance;
								// redreDay=Integer.parseInt(rrdressalDays[i]);
								nonAdjDay = redreDay >= unAdjDay ? 0
										: (unAdjDay - redreDay);
								leaveData[i][1] = nonAdjDay;

								adjDay = nonAdjDay == 0 ? (redreAdjDay - (redreDay - unAdjDay))
										: redreAdjDay;
								leaveData[i][0] = adjDay;
							}
							leaveData[i][2] = empCode;
							leaveData[i][3] = leaveCode[i][0];
							leaveData[i][4] = leaveCode[i][5];// from date
							// rPenaltyDays
							policyData[0][1] = empCode;
							policyData[0][2] = leaveCode[i][0];
							/**
							 * CHECK POLICY ZERO APPLICABLE OR NOT
							 */
							if (chkPolicy != null
									&& chkPolicy.length > 0
									&& String.valueOf(chkPolicy[0][0]).equals(
											"N")) {
								getSqlModel().singleExecute(upBalanceQuery,
										policyData);
							}
						}
						getSqlModel().singleExecute(updateDtlQuery_1, update);
						getSqlModel().singleExecute(leave, leaveData);
					}
					result = "Application has been approved";
					//Mail to employee regarding second approval
					String appQuery = "SELECT NVL(REDRESSAL_KEEP_INFORM,0) FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="
							+ applicationCode;
					Object[][] keep = getSqlModel().getSingleResult(appQuery);
					String keepData = "";
					if (keep != null && keep.length > 0
							&& !String.valueOf(keep[0][0]).equals("0")) {
						keepData = String.valueOf(keep[0][0]);
					}
					String secondAppr = approverId;
					String[] link_param1 = null;
					String[] link_label1 = null;

					try {
						/*sendMailTOSecondApprover(request,"83", secondAppr, empCode,
								applicationCode, "", keepData, link_param1,
								link_label1);*/
						sendMailTOSecondApprover(
								request,
								"Redressal Mail to employee regarding second approval",
								secondAppr, empCode, applicationCode, "",
								keepData, link_param1, link_label1);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}
		} else {
			result = "This application has been already approved";
		}
		return result;
	}

	public void setApproverNameComments(Regularization regularization,
			String query) {
		// TODO Auto-generated method stub
		regularization.setViewApproverList(null);
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			regularization.setCommentsFlag("true");
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				Regularization bean = new Regularization();
				bean.setApproverNameView(String.valueOf(data[i][0]));
				bean.setApprverComments(String.valueOf(data[i][1]));
				list.add(bean);
			}
			regularization.setViewApproverList(list);
		} else {
			regularization.setCommentsFlag("false");
		}

	}

	/**
	 * METHOD FOR VIEW LATE REGULARIZATION
	 * @param regularization	
	 * @param status 
	 * @param applCode 
	 */
	public void viewPersonalTimeApplication(Regularization regularization,
			String applCode, String status) {

		regularization.setCondTrueFlag("false");
		regularization.setCountValue("0");
		regularization.setListFlag("false");
		regularization.setLateFlag("false");
		regularization.setSwipeFlag("false");
		regularization.setRedressalFlag("false");
		regularization.setPersonalTimeFlag("true");
		regularization.setViewApplFlag("true");

		String query = "SELECT PT_REG_ID,TO_CHAR(PT_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(PT_REG_DTL_FROM_TIME,'HH24:MI'),TO_CHAR(PT_REG_DTL_TO_TIME,'HH24:MI')"
				+ " , NVL(TRUNC(MOD( ( TO_DATE(TO_char(PT_REG_DTL_TO_TIME, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(PT_REG_DTL_FROM_TIME, 'HH24.MI'),'HH24.MI'))*24,24))	||' :'|| TRUNC(MOD( ( TO_DATE(TO_char(PT_REG_DTL_TO_TIME, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(PT_REG_DTL_FROM_TIME, 	'HH24.MI'),'HH24.MI'))*24*60,60)),0) AS TOTAL  "
				+ " FROM HRMS_PT_REG_DTL "
				+ "	WHERE PT_REG_ID="
				+ applCode
				+ " AND PT_REG_DTL_STATUS='" + status + "' ORDER BY PT_REG_ID";

		Object[][] data = getSqlModel().getSingleResult(query);
		regularization.setPtList(null);
		ArrayList ptList = new ArrayList();
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			Regularization bean = new Regularization();
			bean.setPtDateItt(String.valueOf(data[i][1]));
			bean.setPtFromTimeItt(String.valueOf(data[i][2]));
			bean.setPtToTimeItt(String.valueOf(data[i][3]));
			bean.setDifference(String.valueOf(data[i][4]));
			ptList.add(bean);
		}
		regularization.setPtList(ptList);
	}

	/**
	 * Mail sent to second approver  for approval	
	 * sendMailTOSecondApprover("97", firstApprover, empCode, applicationCode,"");
	 */
	public String sendMailTOSecondApprover(HttpServletRequest request,
			String templateName, String firstApprover, String secondApprover,
			String applicationCode, String secondApp, String keepData,
			String[] link_param, String[] link_label) throws Exception {
		Object[][] eventData = null;
		Object[][] templateData = null;
		RegularizationApprovalModel model = new RegularizationApprovalModel();
		model.initiate(context, session);
		/*
			try {
			String eventQuery = "SELECT  EVENT_CODE,EVENT_NAME,EVENT_OPTION_FLAG,NVL(EVENT_TEMPLATE_CODE,0), "  
			+" HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME,EVENT_TRIGGER_OPTION   "
			+" FROM HRMS_MAIL_EVENTS   "
			+" LEFT JOIN HRMS_EMAILTEMPLATE_HDR ON (HRMS_MAIL_EVENTS.EVENT_TEMPLATE_CODE=HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID) " 
			+" WHERE EVENT_CODE = "+eventCode+"";
			eventData = model.getSqlModel().getSingleResult(eventQuery);
		} catch (Exception e) {
			
		} //end of catch
		if(eventData !=null && eventData.length>0 && String.valueOf(eventData[0][2]).equals("Y")){			
		String templateQuery = "SELECT TEMPLATE_NAME "
				+" FROM HRMS_EMAILTEMPLATE_HDR  "
				+" WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID = "+eventData[0][3]+"";
				templateData = model.getSqlModel().getSingleResult(templateQuery);
			if(templateData !=null && templateData.length>0){
				String templateName=String.valueOf(templateData[0][0]).trim();*/

		try {
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Regularization";
			processAlerts.removeProcessAlert(applicationCode, module);
			processAlerts.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		EmailTemplateBody template = new EmailTemplateBody();
		template.initiate(context, session);
		template.setEmailTemplate(templateName);

		template.getTemplateQueries();
		//from email id
		EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL			
		templateQuery1.setParameter(1, firstApprover);

		EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL			
		templateQuery2.setParameter(1, secondApprover);

		EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
		templateQuery3.setParameter(1, applicationCode);

		EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
		templateQuery4.setParameter(1, firstApprover);

		EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
		templateQuery5.setParameter(1, applicationCode);

		EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
		templateQuery6.setParameter(1, applicationCode);

		if (!secondApp.equals("")) {
			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, secondApp);
		}
		template.configMailAlert();
		
		//Added by janisha for Online approval
		

		String query = " SELECT  decode(SWIPE_REG_STATUS,'P','Pending','A','Approved','R','Rejected','B','SentBack'),SWIPE_REG_STATUS FROM HRMS_SWIPE_REG_HDR "
			+" WHERE SWIPE_REG_ID="+applicationCode;
		
		Object statusdata[][] =model.getSqlModel().getSingleResult(query);
		String status ="";
		String linkstatus ="";
		if(statusdata!=null && statusdata.length>0)
		{
			status =String.valueOf(statusdata[0][0]);
			linkstatus=String.valueOf(statusdata[0][1]);
		}
			
		if(templateName.equals("Attendance regularization Mail to employee regarding second approval"))
		{
			String applicant =secondApprover;
			if(linkstatus.equals("B"))
			{
				
				String link = "/leaves/Regularization_viewApplication.action";
				
				String linkParam ="appCode="+applicationCode+"&status="+linkstatus+"&type=SR";
			 
				template.sendProcessManagerAlert("", "Regularization", "A",
						applicationCode, "1", linkParam, link,
						status, applicant, "", "",
						applicant, firstApprover);
				
				
				linkParam ="appCode="+applicationCode+"&status=R&type=SR";

				template.sendProcessManagerAlert(firstApprover,
						"Regularization", "I", applicationCode, "1",
						linkParam, link, status, applicant,
						"0", keepData, "", firstApprover);
				
				 

			}
			else{
				String link = "/leaves/Regularization_viewApplication.action";
				
				String linkParam ="appCode="+applicationCode+"&status="+linkstatus+"&type=SR";
				 
				
				template.sendProcessManagerAlert(firstApprover,
						"Regularization", "I", applicationCode, "1",
						linkParam, link, status, applicant,
						"0", keepData, applicant,
						firstApprover);
			}
			
			
		}
		
		
		
		//End janisha
		
		
		if (!keepData.equals("")) {
			template.sendApplicationMailToKeepInfo(keepData);
		}
		if (link_param != null && link_param.length > 0) {
			template.addOnlineLink(request, link_param, link_label);
		}

		template.sendApplicationMail();
		template.clearParameters();
		template.terminate();
		//}

		//}		
		return "";
	}

	private boolean rejectReglAttendanceData(String year, String date, String empCode) {
		boolean result = false;
		try {
			String updateQuery = " UPDATE HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " set ATT_REG_STATUS_TWO='AB' ,IS_APPL_PROCESS='N'  WHERE ATT_DATE=TO_DATE('"
					+ date + "','DD-MM-YYYY') AND ATT_EMP_ID=" + empCode;
			result = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
