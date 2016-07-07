 package org.paradyne.model.leave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.leave.Regularization;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


public class RegularizationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RegularizationModel.class);
	int scriptPageNo = 10;

	public void showRegularizationList(Regularization regularization,
			String month, String year, String type, String empCode) {
		try {
			String attendanceSettingQuery = "SELECT CONF_LATE_REGULARIZE, CONF_HD_REGULARIZE, CONF_ABSENT_REGULARIZE, CONF_REG_TIME_REGULARIZE FROM HRMS_ATTENDANCE_CONF";
			Object[][] attendanceSettingData = getSqlModel().getSingleResult(
					attendanceSettingQuery);
			String query = "SELECT ATT_EMP_ID,TO_CHAR(ATT_DATE,'DD-MM-YYYY'), NVL(TO_CHAR(DECODE(SFT_IN_LM_AFTER_TIME,NULL,SFT_START_TIME,SFT_IN_LM_AFTER_TIME),'HH24:MI'),'00:00'), "
					+ "  TO_CHAR(ATT_LOGIN,'HH24:MI'), CASE WHEN ATT_STATUS_TWO='LC' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) WHEN ATT_STATUS_TWO='EL' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 +  "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) "
					+ "	 END  ,DECODE(ATT_STATUS_ONE,'PR','Present','AB','Absent','WO','Weekly Off','HO','Holiday')AS STATUS1, "
					+ "	 DECODE(ATT_STATUS_TWO,'LC','Late Coming','EL','Early Leaving','DL','Dual Late','AB','Absent','HD','Half Day','IN','In Time')AS STATUS2,"
					+ "	 TO_CHAR(ATT_LATE_HRS, 'HH24:MI'),TO_CHAR(ATT_EARLY_HRS, 'HH24:MI') FROM HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " "
					+ "	INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_DAILY_ATTENDANCE_"
					+ year
					+ ".ATT_SHIFT_ID) "
					+ "	WHERE ATT_EMP_ID="
					+ empCode + " AND TO_CHAR(ATT_DATE,'MM')=" + month;
			if (type.equals("LR")) {

				query = "SELECT ATT_EMP_ID,TO_CHAR(ATT_DATE,'DD-MM-YYYY'),NVL(TO_CHAR(DECODE(SFT_IN_LM_AFTER_TIME,NULL,SFT_START_TIME,SFT_IN_LM_AFTER_TIME),'HH24:MI'),'00:00'),TO_CHAR(ATT_LOGIN,'HH24:MI'), "
						+ "  CASE WHEN ATT_STATUS_TWO='LC' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) WHEN ATT_STATUS_TWO='EL' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 +  "
						+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + "
						+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) "
						+ "	 END  ,DECODE(ATT_STATUS_ONE,'PR','Present','AB','Absent')AS STATUS1, "
						+ "	 DECODE(ATT_STATUS_TWO,'LC','Late Coming','EL','Early Leaving','DL','Dual Late','AB','Absent','HD','Half Day')AS STATUS2, "
						+ "	 TO_CHAR(ATT_LATE_HRS, 'HH24:MI'),TO_CHAR(ATT_EARLY_HRS, 'HH24:MI') FROM HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " "
						+ "	INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_DAILY_ATTENDANCE_"
						+ year
						+ ".ATT_SHIFT_ID) "
						+ "	 WHERE ATT_EMP_ID="
						+ empCode + " AND TO_CHAR(ATT_DATE,'MM')=" + month;

				query += " AND ATT_STATUS_ONE IN('PR') AND ATT_STATUS_TWO IN('LC','EL','DL') ";
				query += " AND (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0))>0 ";

				query += " AND ( ATT_REG_STATUS_LATE IS NULL OR ATT_REG_STATUS_LATE='NR' ) AND ( ATT_REG_STATUS_SWIPE IS NULL OR ATT_REG_STATUS_SWIPE='NR' ) ";
				// query += " ORDER BY ATT_DATE ";
				query += " UNION  ";

				query += " SELECT ATT_EMP_ID,TO_CHAR(ATT_DATE,'DD-MM-YYYY'),NVL(TO_CHAR(DECODE(SFT_IN_LM_AFTER_TIME,'00:00',SFT_START_TIME,SFT_IN_LM_AFTER_TIME),'HH24:MI'),'00:00'),TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),   CASE WHEN ATT_REG_STATUS_TWO='LC' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0) WHEN ATT_REG_STATUS_TWO='EL' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0) ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 +  		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0) + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0) 		  END  ,DECODE(ATT_REG_STATUS_ONE,'PR','Present','AB','Absent')AS STATUS1, 	DECODE(ATT_REG_STATUS_TWO,'LC','Late Coming','EL','Early Leaving','DL','Dual Late','AB','Absent','HD','Half Day')AS STATUS2, "
						+ "	NVL(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'),'00:00'),NVL(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'),'00:00') FROM HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " 	"
						+ "	INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_DAILY_ATTENDANCE_"
						+ year
						+ ".ATT_SHIFT_ID) "
						+ "	WHERE ATT_EMP_ID="
						+ empCode
						+ " AND TO_CHAR(ATT_DATE,'MM')="
						+ month
						+ " AND ATT_REG_STATUS_ONE IN('PR') AND ATT_REG_STATUS_TWO IN('LC','EL','DL') "
						+ "	AND (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0) + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0))>0 "
						+ "	AND ATT_REG_STATUS_SWIPE='AS' AND ( ATT_REG_STATUS_LATE IS NULL OR ATT_REG_STATUS_LATE='NR' )   "
						+ "	ORDER BY 2 ";

				regularization.setRedressalFlag("false");
				regularization.setListFlag("true");
			} else if (type.equals("RR")) {
				/*
				 * QUERY FOR REDRESAL
				 * 
				 */
				regularization.setRedressalFlag("true");
				regularization.setListFlag("false");
				query = "SELECT LEAVE_CODE,LEAVE_ABBR,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'), "
						+ "	NVL(LEAVE_PENALTY_DAYS,0) "
						+ "	FROM HRMS_LEAVE_DTL "
						+ "	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_DTL.LEAVE_CODE AND EMP_ID="
						+ empCode
						+ ") "
						+ "	WHERE TO_CHAR(LEAVE_FROM_DATE,'MM')="
						+ month
						+ " AND TO_CHAR(LEAVE_FROM_DATE,'YYYY')="
						+ year
						+ " AND NVL(LEAVE_PENALTY_DAYS,0)>0 AND LEAVE_DTL_STATUS='A' AND LEAVE_REDRESSAL_FLAG IS NULL ORDER BY LEAVE_FROM_DATE ";
			} else {

				// query += " AND ATT_STATUS_ONE IN('PR','AB') AND ATT_STATUS_TWO
				// IN('LC','EL','DL','AB','HD') ";

				// Added by manish sakpal begins
				if (attendanceSettingData != null
						&& attendanceSettingData.length > 0) {
					String lateRegularize = String
							.valueOf(attendanceSettingData[0][0]);
					String halfDayRegularize = String
							.valueOf(attendanceSettingData[0][1]);
					String absentRegularize = String
							.valueOf(attendanceSettingData[0][2]);
					String regularTimeRegularize = String
							.valueOf(attendanceSettingData[0][3]);
					String statusToAppend = "";
					if (lateRegularize.equals("Y")
							|| halfDayRegularize.equals("Y")
							|| absentRegularize.equals("Y")
							|| regularTimeRegularize.equals("Y")) {

						statusToAppend = getStatus(statusToAppend,
								lateRegularize, "LC");
						statusToAppend = getStatus(statusToAppend,
								lateRegularize, "EL");
						statusToAppend = getStatus(statusToAppend,
								lateRegularize, "DL");
						statusToAppend = getStatus(statusToAppend,
								halfDayRegularize, "HD");
						statusToAppend = getStatus(statusToAppend,
								absentRegularize, "AB");
						statusToAppend = getStatus(statusToAppend,
								regularTimeRegularize, "IN");
						statusToAppend = statusToAppend.substring(3,
								statusToAppend.length());
						query += " AND ATT_STATUS_ONE IN('PR','AB','WO','HO') AND ATT_STATUS_TWO IN('"
								+ statusToAppend + "') ";
					} else {
						query += " AND ATT_STATUS_ONE IN('PR','AB','WO','HO') AND ATT_STATUS_TWO IN('NO STATUS') ";
					}
				}
				// Added by manish sakpal ends

				// query+= " AND (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS,
				// 'HH24:MI'), 1, 2)), 0) * 60 +
				// NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0)
				// + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)),
				// 0) * 60 + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'),
				// 4, 5)), 0))>0 ";

				query += " AND ( ATT_REG_STATUS_SWIPE IS NULL OR ATT_REG_STATUS_SWIPE='NR' ) AND ( ATT_REG_STATUS_LATE IS NULL OR ATT_REG_STATUS_LATE='NR' ) ";
				query += " ORDER BY ATT_DATE ";
				regularization.setRedressalFlag("false");
				regularization.setListFlag("true");
			}
			Object[][] data = getSqlModel().getSingleResult(query);
			regularization.setList(null);
			if (data != null && data.length > 0) {
				ArrayList list = new ArrayList();
				if (type.equals("RR")) {
					for (int i = 0; i < data.length; i++) {
						Regularization bean = new Regularization();
						bean.setLeaveCode(checkNull(String.valueOf(data[i][0])));
						bean.setLeaveType(checkNull(String.valueOf(data[i][1])));
						bean.setRFromDate(checkNull(String.valueOf(data[i][2])));
						bean.setRToDate(checkNull(String.valueOf(data[i][3])));
						bean.setRPenaltyDays(checkNull(String.valueOf(data[i][4])));
						list.add(bean);
					}
				} else {
					for (int i = 0; i < data.length; i++) {
						Regularization bean = new Regularization();
						bean.setDate(checkNull(String.valueOf(data[i][1])));
						bean.setShiftTime(checkNull(String.valueOf(data[i][2])));
						bean.setInTime(checkNull(String.valueOf(data[i][3])));
						bean.setTotalHrs(checkNull(getHH_MM(Integer.parseInt(String
								.valueOf(data[i][4])))));
						bean.setStatus1(checkNull(String.valueOf(data[i][5])));
						bean.setStatus2(checkNull(String.valueOf(data[i][6])));
						bean.setLateHrs(checkNull(String.valueOf(data[i][7])));
						bean.setEarlyHrs(checkNull(String.valueOf(data[i][8])));
						list.add(bean);
					}
				}
				regularization.setList(list);
			}// end if
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * GET STATUS FROM ATTENDANCE SETTING.
	 * 
	 * @param value
	 * @param statusFromSetting
	 * @param decodedStatus
	 */
	private String getStatus(String value, String fromAttendanceSetting,
			String decodedStatus) {
		String result = "";
		try {
			if (fromAttendanceSetting.equals("Y")) {
				result = value + "','" + decodedStatus + ",";
				result = result.substring(0, result.length() - 1);
			} else {
				result = value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * SET EMPLOYEE INFORMATION NAME,BRANCH,DESIGNATION
	 * 
	 * @param regularization
	 * @param applCode
	 */
	public void setEmployeeInformation(Regularization regularization,
			String query) {
		try {
			/**
			 * GET EMPLOYEE INFORMATION
			 */
			Object[][] empData = getSqlModel().getSingleResult(query);
			if (empData != null && empData.length > 0) {
				regularization.setEmpCode(checkNull(String
						.valueOf(empData[0][0])));
				regularization.setEmpToken(checkNull(String
						.valueOf(empData[0][1])));
				regularization.setEmpName(checkNull(String
						.valueOf(empData[0][2])));
				regularization.setEmpBranch(checkNull(String
						.valueOf(empData[0][3])));
				regularization.setEmpDesg(checkNull(String
						.valueOf(empData[0][4])));
				regularization.setApplicationDate(checkNull(String
						.valueOf(empData[0][5])));
				regularization.setEmpGender(checkNull(String
						.valueOf(empData[0][6])));
				regularization.setShiftCode(String.valueOf(empData[0][7]));
			}
			/**
			 * CHECK CONDITION FROM SHIFT TABLE
			 */
			String qq = "SELECT NVL(SFT_PT_ISENABLED,'N'),NVL(TO_CHAR(SFT_PT_DURATION,'HH24:MI'),0) ,SFT_PT_PERIODICITY FROM HRMS_SHIFT where SHIFT_ID="
					+ regularization.getShiftCode();
			Object[][] shiftCode = getSqlModel().getSingleResult(qq);
			if (shiftCode != null && shiftCode.length > 0) {
				// Based on hours of late reporting
				regularization.setPtType(String.valueOf(shiftCode[0][2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * SET KEEP INFORMATION
	 * 
	 * @param regularization
	 * @param applCode
	 */
	public String setKeepInform(Regularization regularization, String applCode,
			String query) {
		
		String isKeepInfoLogin ="N";
		try {
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
				if (!info.equals("0")) {
					String emp = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_ID IN("
							+ info + ")";
					Object[][] empInfoData = getSqlModel().getSingleResult(emp);
					ArrayList arrList = new ArrayList();
					if (empInfoData != null && empInfoData.length > 0) {
						for (int i = 0; i < empInfoData.length; i++) {
							Regularization leaveBean = new Regularization();
							leaveBean.setKeepInform(String
									.valueOf(empInfoData[i][0]));
							leaveBean.setKeepInformCode(String
									.valueOf(empInfoData[i][1]));
							
							if (regularization.getUserEmpId().equals(
									leaveBean.getKeepInform())) {
								isKeepInfoLogin = "Y";
							}
							arrList.add(leaveBean);
						}
						regularization.setInformList(arrList);
					}
				}
				regularization.setReason(String.valueOf(hdrData[0][1]));
				regularization.setApplyFor(String.valueOf(hdrData[0][2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isKeepInfoLogin;
	}

	/**
	 * METHOD FOR VIEW REDRESSAL APLICATION
	 * @param regularization
	 * @param status
	 * @param applCode
	 */

	public void viewRedressalApplication(Regularization regularization,
			String applCode, String status) {
		// TODO Auto-generated method stub
		regularization.setCondTrueFlag("false");
		regularization.setActionName("applyRedressalApplication");
		regularization.setSendBackActionName("showRegularizationList");
		regularization.setRedressalFlag("true");
		regularization.setListFlag("false");
		/**
		 * GET LEAVE DTL INFORMATION
		 */
		String query = "SELECT HRMS_REDRESSAL_DTL.REDRESSAL_DTL_LEAVE_CODE,HRMS_LEAVE.LEAVE_ABBR,TO_CHAR(REDRESSAL_DTL_FROM_DATE,'DD-MM-YYYY') "
				+ "		,TO_CHAR(REDRESSAL_DTL_TO_DATE,'DD-MM-YYYY'),REDRESSAL_DTL_PENALTY_DAYS,REDRESSAL_DTL_REDRESSED_DAYS,REDRESSAL_DTL_PENALTY_ADJ_DAYS ,DECODE(REDRESSAL_DTL_PENALTY_DAYS,REDRESSAL_DTL_PENALTY_ADJ_DAYS,'Y','N')	"
				+ "		 FROM HRMS_REDRESSAL_DTL  "
				+ "		INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_REDRESSAL_DTL.REDRESSAL_DTL_LEAVE_CODE)"
				+ "		WHERE REDRESSAL_ID="
				+ applCode
				+ " AND REDRESSAL_DTL_STATUS='" + status + "'";

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
				bean.setRedressalAdjDays(String.valueOf(data[i][6]));
				// bean.setRedressalAdjStatus(String.valueOf(data[i][7]));
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

		String query = "SELECT PT_REG_ID,TO_CHAR(PT_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(PT_REG_DTL_FROM_TIME,'HH24:MI'),TO_CHAR(PT_REG_DTL_TO_TIME,'HH24:MI') FROM HRMS_PT_REG_DTL "
				+ "	WHERE PT_REG_ID="
				+ applCode
				+ " AND PT_REG_DTL_STATUS='"
				+ status + "' ORDER BY PT_REG_ID";

		Object[][] data = getSqlModel().getSingleResult(query);
		regularization.setPtList(null);
		ArrayList ptList = new ArrayList();
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			Regularization bean = new Regularization();
			bean.setPtDateItt(String.valueOf(data[i][1]));
			bean.setPtFromTimeItt(String.valueOf(data[i][2]));
			bean.setPtToTimeItt(String.valueOf(data[i][3]));
			int diff = getMinute(String.valueOf(data[i][3]))
					- getMinute(String.valueOf(data[i][2]));
			bean.setDifference(getHH_MM(diff));
			ptList.add(bean);
		}
		regularization.setPtList(ptList);
	}

	/**
	 * METHOD FOR VIEW LATE REGULARIZATION
	 * @param regularization
	 * @param status
	 * @param applCode
	 */
	public void viewLateApplication(Regularization regularization,
			String applCode, String status) {
		regularization.setCondTrueFlag("false");
		regularization.setCountValue("0");
		regularization.setListFlag("false");
		regularization.setLateFlag("false");
		regularization.setSwipeFlag("false");
		regularization.setRedressalFlag("false");
		String query = "SELECT TO_CHAR(LATE_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(LATE_REG_DTL_SFTTIME,'HH24:MI'),TO_CHAR(LATE_REG_DTL_INTIME,'HH24:MI') "
				+ "	,TRUNC(MOD( ( TO_DATE(TO_char(LATE_REG_DTL_INTIME, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(LATE_REG_DTL_SFTTIME, 'HH24.MI'),'HH24.MI'))*24,24)) "
				+ "	 || ' : ' ||  "
				+ "	 TRUNC(MOD( ( TO_DATE(TO_char(LATE_REG_DTL_INTIME, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(LATE_REG_DTL_SFTTIME, "
				+ "	'HH24.MI'),'HH24.MI'))*24*60,60)) || '' AS DIFFERENCE,TO_CHAR(LATE_REG_DTL_HRS_DED,'HH24:MI'),HRMS_LEAVE.LEAVE_ABBR ,HRMS_LEAVE.LEAVE_ID "
				+ "	,LATE_REG_ID FROM HRMS_LATE_REG_DTL "
				+ "	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LATE_REG_DTL.LATE_REG_DTL_LEAVE_ID) "
				+ "	WHERE LATE_REG_ID="
				+ applCode
				+ " AND LATE_REG_DTL_STATUS='"
				+ status
				+ "' ORDER BY LATE_REG_ID";
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
		regularization.setTotalLateHrs(getHH_MM(sum));
		regularization.setRegList(regList);
	}

	/**
	 * METHOD FOR VIEW SWIPE MISS APPLICATION 
	 * @param regularization
	 * @param status
	 * @param applCode
	 */
	public void viewSwipeApplication(Regularization regularization,
			String applCode, String status) {
		try {
			regularization.setCondTrueFlag("false");
			regularization.setCountValue("0");
			regularization.setListFlag("false");
			regularization.setLateFlag("false");
			regularization.setSwipeFlag("true");
			regularization.setRedressalFlag("false");
			String query = "SELECT TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(SWIPE_REG_DTL_FROM_TIME,'HH24:MI') "
					+ "  ,TO_CHAR(SWIPE_REG_DTL_TO_TIME,'HH24:MI'),TO_CHAR(SWIPE_REG_DTL_ACT_INTIME,'HH24:MI') "
					+ "  ,TO_CHAR(SWIPE_REG_DTL_ACT_OUTTIME,'HH24:MI') "
					+ "  , NVL(SWIPE_REG_REASON,0) FROM HRMS_SWIPE_REG_DTL  "
					+ "  WHERE SWIPE_REG_ID="+ applCode;					
					//+ " AND SWIPE_REG_DTL_STATUS='" + status + "'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				regularization.setActionName("applySwipeApplication");
				regularization.setSendBackActionName("showRegularizationList");
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

	public void applyRedressal(Regularization regularization,
			String[] fromDate, String month, String year, String type,
			String empCode, String[] delDate) {
		regularization.setCondTrueFlag("false");
		regularization.setActionName("applyRedressalApplication");
		regularization.setSendBackActionName("showRegularizationList");
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

		String delEmpList = "'00-00-0000',";
		if (delDate != null && delDate.length > 0) {
			for (int i = 0; i < delDate.length; i++) {
				delEmpList += " '" + String.valueOf(delDate[i]) + "' ,";
			}
			delEmpList = delEmpList.substring(0, delEmpList.length() - 1);
		} else {
			delEmpList = "'00-00-0000'";
		}
		String selectDate = "'00-00-0000',";
		if (fromDate != null && fromDate.length > 0) {
			for (int i = 0; i < fromDate.length; i++) {
				selectDate += " '" + String.valueOf(fromDate[i]) + "' ,";
			}
			selectDate = selectDate.substring(0, selectDate.length() - 1);
		} else {
			selectDate = "'00-00-0000'";
		}
		regularization.setRedressalFlag("true");
		regularization.setListFlag("false");
		String query = "SELECT LEAVE_CODE,LEAVE_ABBR,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') "
				+ "	,NVL(LEAVE_PENALTY_DAYS,0),NVL(LEAVE_PENALTY_ADJUST_DAYS,0) "
				+ "	 FROM HRMS_LEAVE_DTL "
				+ "	 INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_DTL.LEAVE_CODE AND EMP_ID="
				+ empCode + ") " + "	 WHERE LEAVE_DTL_STATUS='A'  ";
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
				bean.setRedressalAdjDays(String.valueOf(data[i][5]));
				list.add(bean);
			}
			regularization.setList(list);
		}

	}

	public String apply(Regularization regularization, String[] date,
			String month, String year, String type, String empCode,
			String[] delDate, String[] actIN, String[] actOUT, String timecardEmp) {
		String result = "success";
		try {
			regularization.setCondTrueFlag("false");
			regularization.setCountValue("0");
			regularization.setSwipeList(null);
			regularization.setRegList(null);
			regularization.setList(null);
			regularization.setListFlag("false");
			regularization.setLateFlag("false");
			regularization.setSwipeFlag("false");
			regularization.setRedressalFlag("false");
			if (type.equals("RR")) {
				regularization.setRedressalFlag("true");
			} else if (type.equals("LR")) {
				regularization.setLateFlag("true");
			} else {
				regularization.setSwipeFlag("true");
			}
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
				regularization.setEmpCode(checkNull(String
						.valueOf(empData[0][0])));
				regularization.setEmpToken(checkNull(String
						.valueOf(empData[0][1])));
				regularization.setEmpName(checkNull(String
						.valueOf(empData[0][2])));
				regularization.setEmpBranch(checkNull(String
						.valueOf(empData[0][3])));
				regularization.setEmpDesg(checkNull(String
						.valueOf(empData[0][4])));
				regularization.setApplicationDate(checkNull(String
						.valueOf(empData[0][5])));
				regularization.setEmpGender(checkNull(String
						.valueOf(empData[0][6])));
				regularization.setShiftCode(String.valueOf(empData[0][7]));
			}
			/**
			 * CHECK CONDITION FROM SHIFT TABLE
			 */
			String qq = "SELECT NVL(SFT_LM_HRS_ISENABLED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0)  FROM HRMS_SHIFT where SHIFT_ID="
					+ regularization.getShiftCode();
			Object[][] shiftCode = getSqlModel().getSingleResult(qq);
			String shifTime = "";
			if (type.equals("LR")) {
				if (shiftCode != null && shiftCode.length > 0) {
					// Based on hours of late reporting
					if (String.valueOf(shiftCode[0][0]).equals("N")
							|| String.valueOf(shiftCode[0][1]).equals("N")) {
						// result="notDefine";
						return "notDefine";
					}
					shifTime = String.valueOf(shiftCode[0][4]);
				}
			}
			int shiftMinuts = getMinute(shifTime);
			/**
			 * DEPENDING ON APPLY OR DELETE RECORD DATE OBJECT CREATE
			 */
			String delEmpList = "'00-00-0000',";
			if (delDate != null && delDate.length > 0) {
				for (int i = 0; i < delDate.length; i++) {
					delEmpList += " '" + String.valueOf(delDate[i]) + "' ,";
				}
				delEmpList = delEmpList.substring(0, delEmpList.length() - 1);
			} else {
				delEmpList = "'00-00-0000'";
			}
			String emp_list = "'00-00-0000',";
			if (date != null && date.length > 0) {
				for (int i = 0; i < date.length; i++) {
					emp_list += " '" + String.valueOf(date[i]) + "' ,";
				}
				emp_list = emp_list.substring(0, emp_list.length() - 1);
			} else {
				emp_list = "'00-00-0000'";
			}
			String query = "SELECT ATT_EMP_ID,TO_CHAR(ATT_DATE,'DD-MM-YYYY'),TO_CHAR(DECODE(SFT_IN_LM_AFTER_TIME,NULL,SFT_START_TIME,SFT_IN_LM_AFTER_TIME),'HH24:MI'),TO_CHAR(ATT_LOGIN,'HH24:MI'), "
					+ "  CASE WHEN ATT_STATUS_TWO='LC' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) WHEN ATT_STATUS_TWO='EL' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 +  "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) end ,"
					+ "  NVL(TRUNC(MOD( ( TO_DATE(TO_char(ATT_LOGIN, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(SFT_START_TIME, 'HH24.MI'),'HH24.MI'))*24,24))*60	 + TRUNC(MOD( ( TO_DATE(TO_char(ATT_LOGIN, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(SFT_START_TIME, 	'HH24.MI'),'HH24.MI'))*24*60,60)),0)  AS DIFFERENCE,    "
					+ "  TO_CHAR(ATT_LOGOUT,'HH24:MI')  "
					+ "	 FROM HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " "
					+ "	INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_DAILY_ATTENDANCE_"
					+ year
					+ ".ATT_SHIFT_ID) "
					+ "	WHERE ATT_EMP_ID="
					+ empCode
					+ " AND TO_CHAR(ATT_DATE,'MM')="
					+ month
					+ " AND ATT_STATUS_ONE IN('PR','AB','WO','HO') "
					+ "	AND ATT_STATUS_TWO IN('LC','EL','DL','AB','HD','IN') ";
			if (!emp_list.equals("")) {
				query += " AND TO_CHAR(ATT_DATE,'DD-MM-YYYY') in(" + emp_list
						+ ")   ";
			}
			if (!delEmpList.equals("")) {
				query += " AND  TO_CHAR(ATT_DATE,'DD-MM-YYYY') NOT in("
						+ delEmpList + ")  ";
			}
			query += " AND (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0))>0";
			query += " AND (ATT_REG_STATUS_LATE is null OR ATT_REG_STATUS_LATE='NR') AND (ATT_REG_STATUS_SWIPE IS NULL OR ATT_REG_STATUS_SWIPE='NR')  ";
			query += " UNION  ";
			query += " SELECT ATT_EMP_ID,TO_CHAR(ATT_DATE,'DD-MM-YYYY'),TO_CHAR(DECODE(SFT_IN_LM_AFTER_TIME,NULL,SFT_START_TIME,SFT_IN_LM_AFTER_TIME),'HH24:MI'),TO_CHAR(ATT_REG_LOGIN,'HH24:MI'), "
					+ "  CASE WHEN ATT_REG_STATUS_TWO='LC' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0) WHEN ATT_REG_STATUS_TWO='EL' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0) ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 +  "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0) + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "	 NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0) end ,"
					+ "  NVL(TRUNC(MOD( ( TO_DATE(TO_char(ATT_LOGIN, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(SFT_START_TIME, 'HH24.MI'),'HH24.MI'))*24,24))*60	 + TRUNC(MOD( ( TO_DATE(TO_char(ATT_LOGIN, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(SFT_START_TIME, 	'HH24.MI'),'HH24.MI'))*24*60,60)),0)  AS DIFFERENCE,    "
					+ "  TO_CHAR(ATT_LOGOUT,'HH24:MI')  "
					+ "	 FROM HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " "
					+ "	INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_DAILY_ATTENDANCE_"
					+ year
					+ ".ATT_SHIFT_ID) "
					+ "	WHERE ATT_EMP_ID="
					+ empCode
					+ " AND TO_CHAR(ATT_DATE,'MM')="
					+ month
					+ " AND ATT_REG_STATUS_ONE IN('PR','AB','WO','HO') "
					+ "	AND ATT_REG_STATUS_TWO IN('LC','EL','DL','AB','HD','IN') AND ATT_REG_STATUS_SWIPE='AS' ";
			query += "	AND (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LC_HRS, 'HH24:MI'), 4, 5)), 0) + NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + 	NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_EL_HRS, 'HH24:MI'), 4, 5)), 0))>0 ";
			query += "  AND ATT_REG_STATUS_SWIPE='AS' AND ( ATT_REG_STATUS_LATE IS NULL OR ATT_REG_STATUS_LATE='NR' ) ";
			if (!emp_list.equals("")) {
				query += " AND TO_CHAR(ATT_DATE,'DD-MM-YYYY') in(" + emp_list
						+ ")   ";
			}
			if (!delEmpList.equals("")) {
				query += " AND  TO_CHAR(ATT_DATE,'DD-MM-YYYY') NOT in("
						+ delEmpList + ")  ";
			}
			query += " ORDER BY 2 ";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				ArrayList regList = new ArrayList();
				regularization.setListFlag("true");
				ArrayList list = new ArrayList();
				/**
				 * CODING FOR LATE REGULARIZATION
				 */
				if (type.equals("LR")) {
					// SET THE ACTION FOR SELECTED FILTER
					regularization.setActionName("applyLateApplication");
					regularization
							.setSendBackActionName("showRegularizationList");
					regularization.setLateFlag("true");
					String sftCode = "0";
					int slab = 0;
					int balanceInMin = 0;
					int totalLateHrs = 0;
					if (shiftCode != null && shiftCode.length > 0) {
						for (int i = 0; i < shiftCode.length; i++) {
							// DEDUCT LATE REG FROM THESE LEAVE
							sftCode = String.valueOf(shiftCode[i][2]);
							// SET LEAVE DEDUCT SLAB
							slab = Integer.parseInt(String
									.valueOf(shiftCode[i][3]));
						}
					}
					// sftCode=sftCode.substring(0,sftCode.length()-1);
					String sQUery = "  SELECT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME,LEAVE_CLOSING_BALANCE, NVL(TO_CHAR(HRMS_LEAVE_BALANCE.LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),'00:00')AS BALANCE "
							+ " ,''AS AVL,LEAVE_ABBR   FROM HRMS_LEAVE  "
							+ "   INNER JOIN HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE "
							+ "   AND HRMS_LEAVE_BALANCE.EMP_ID = '"
							+ empCode
							+ "') WHERE HRMS_LEAVE.LEAVE_ID IN ("
							+ sftCode
							+ ") ORDER BY HRMS_LEAVE.LEAVE_ID ";
					Object[][] sData = getSqlModel().getSingleResult(sQUery);
					Object[][] ordShift = new Object[sData.length][6];
					sftCode = sftCode.replaceAll(",", "#");
					String ssCode[] = sftCode.split("#");
					for (int j = 0; j < ssCode.length; j++) {
						for (int i = 0; i < sData.length; i++) {
							if (String.valueOf(ssCode[j]).equals(
									String.valueOf(sData[i][0]))) {
								ordShift[j][0] = sData[i][0];
								ordShift[j][1] = sData[i][1];
								ordShift[j][2] = sData[i][2];
								ordShift[j][3] = sData[i][3];
								ordShift[j][4] = sData[i][4];
								ordShift[j][5] = sData[i][5];
							}
						}
					}
					if (ordShift != null && ordShift.length > 0) {
						float days = 0;
						int totalMinuts = 0;
						for (int i = 0; i < ordShift.length; i++) {
							days += Float.parseFloat(String
									.valueOf(ordShift[i][2]));
							try {
								String[] mon = String.valueOf(ordShift[i][3])
										.split(":");
								int hh = Integer.parseInt(mon[0]) * 60;
								int mm = Integer.parseInt(mon[1]);
								totalMinuts += (hh + mm);
								ordShift[i][4] = (int) (Float.parseFloat(String
										.valueOf(ordShift[i][2])) * shiftMinuts)
										+ (hh + mm);
							} catch (Exception e) {
								e.printStackTrace();
								totalMinuts = totalMinuts;
							}
						}
						// SET TOTAL BAL IN LEAVE BALANCE
						balanceInMin = (int) ((days * shiftMinuts) + (totalMinuts));
					}
					int count = 0;
					// SET EMPLOYEE DATA FROM HRMS_DAILY_ATTANDANCE TABLE
					for (int i = 0; i < data.length; i++) {
						Regularization bean = new Regularization();
						bean.setDate(String.valueOf(data[i][1]));
						bean.setShiftTime(String.valueOf(data[i][2]));
						bean.setInTime(String.valueOf(data[i][3]));
						bean.setLateHrs(getHH_MM(Integer.parseInt(String
								.valueOf(data[i][4]))));
						/**
						 * CODING FOR SLAB
						 */
						int min = Integer.parseInt(String.valueOf(data[i][4]));
						// int slab=30;
						int totalMin = 0;
						// CHECK SLAB--IF 0 THEN SAME AS TOTAL MIN
						if (slab == 0) {
							totalMin = min;
						} else {
							// GET INTO SLAB
							totalMin = (min % slab == 0 ? min : (min / slab)
									* slab + slab);
						}
						totalLateHrs += totalMin;
						int hh = 0;
						String hrs = "";
						String minute = "";
						int mm = 0;
						hh = totalMin / 60;
						mm = (totalMin % 60);
						hrs = "" + hh;
						if (hh < 10) {
							hrs = "0" + hh;
						}
						minute = "" + mm;
						if (mm < 10) {
							minute = "0" + mm;
						}
						bean.setLateHrsDeduct(String
								.valueOf(hrs + ":" + minute));
						// ADLUST IN WICH LEAVE TYPE
						if (ordShift != null && ordShift.length > 0) {
							for (int j = 0; j < ordShift.length; j++) {
								int pl_clBal_in_Min = 0;

								pl_clBal_in_Min = (int) Float.parseFloat(String
										.valueOf(ordShift[j][4]));

								if (pl_clBal_in_Min > totalMin) {
									ordShift[j][4] = ""
											+ (int) (Float.parseFloat(String
													.valueOf(ordShift[j][4])) - totalMin);
									bean.setLateHrsDeductFrom(String
											.valueOf(ordShift[j][5]));
									bean.setLateHrsDeductFromCode(String
											.valueOf(ordShift[j][0]));
									break;
								}
							}
						}
						regList.add(bean);
						if (totalLateHrs > balanceInMin) {
							count++;
						}
					}
					logger.info("TOTAL LATE HRS " + totalLateHrs);
					int hh = 0;
					String hrs = "";
					String minute = "";
					int mm = 0;
					hh = totalLateHrs / 60;
					mm = (totalLateHrs % 60);
					hrs = "" + hh;
					if (hh < 10) {
						hrs = "0" + hh;
					}
					minute = "" + mm;
					if (mm < 10) {
						minute = "0" + mm;
					}
					// SET TOTAL IN HH:MM
					regularization.setTotalLateHrs(hrs + ":" + minute);
					// SET TOTAL IN MIN
					regularization.setTotalLateHrsInMin(String
							.valueOf(getMinute(hrs + ":" + minute)));
					// GET DIFFERENCE
					// regularization.setDifference("0");
					regularization.setDifference(""
							+ (balanceInMin - totalLateHrs));
					regularization.setCondTrueFlag("false");

					if (balanceInMin > totalLateHrs) {
						regularization.setDifference(""
								+ (balanceInMin - totalLateHrs));
						regularization.setSClosingbalance(getDAYS_HH_MM(
								(balanceInMin - totalLateHrs), shiftMinuts));
					} else {
						regularization.setSClosingbalance("0 Days 00:00");
						regularization.setCondTrueFlag("true");
						// set count value for set colour
						regularization.setCountValue(String.valueOf(count));
					}
					regularization.setRegList(regList);

					/**
					 * SET LEAVE TYPE , BALANCE, AVAILABLE BALANCE
					 */
					regularization.setBList(null);
					if (ordShift != null && ordShift.length > 0) {
						ArrayList lis = new ArrayList();
						float days_1 = 0;
						int totalMinuts = 0;
						for (int i = 0; i < ordShift.length; i++) {
							Regularization bean = new Regularization();
							bean.setSLeaveCode(String.valueOf(ordShift[i][0]));// code
							bean.setSLeaveType(String.valueOf(ordShift[i][1]));// name
							days_1 += Float.parseFloat(String
									.valueOf(ordShift[i][2]));
							try {
								int hhhh = 0, mmmm = 0;
								String[] mon = String.valueOf(ordShift[i][3])
										.split(":");
								hhhh = Integer.parseInt(mon[0]) * 60;
								mmmm = Integer.parseInt(mon[1]);
								totalMinuts += (hhhh + mmmm);
							} catch (Exception e) {
								totalMinuts = totalMinuts;
							}

							bean.setSBalance(String.valueOf(ordShift[i][2])
									+ " Days " + ordShift[i][3]);// balance
							bean.setRemainingBalance(getDAYS_HH_MM(Integer
									.parseInt(String.valueOf(ordShift[i][4])),
									shiftMinuts));
							lis.add(bean);
						}

						int hr = 0;
						String hrss = "";
						String minutes = "";
						int min = 0;
						hr = totalMinuts / 60;
						min = (totalMinuts % 60);
						hrss = "" + hr;
						if (hr < 10)
							hrss = "0" + hr;
						minutes = "" + min;
						if (mm < 10)
							minutes = "0" + min;
						// SET TOTAL EMPLOYEE LEAVE BALANCE

						String getTotalHH_MM = getHH_MM(totalMinuts);
						regularization.setTotalBalance("" + days_1 + " Days "
								+ getTotalHH_MM + "");
						// SET TOTAL BAL IN LEAVE BALANCE
						balanceInMin = (int) ((days_1 * shiftMinuts) + (totalMinuts));
						regularization.setTotalBalanceInMin("" + balanceInMin);
						regularization.setBList(lis);
					}

				} else if (type.equals("RR")) {
					regularization.setRedressalFlag("true");
				}
				/**
				 * CODING FOR SWIPE REGULARIZATION
				 */
				else {
				}

			} else {
				regularization.setSwipeList(null);
				regularization.setRegList(null);
				regularization.setList(null);
			}
			if (type.equals("RR")) {

			} else if (type.equals("LR")) {

			} else {
				regularization.setSwipeFlag("true");

				String querySeipe = "SELECT ATT_EMP_ID,TO_CHAR(ATT_DATE,'DD-MM-YYYY'),TO_CHAR(DECODE(SFT_IN_LM_AFTER_TIME,NULL,SFT_START_TIME,SFT_IN_LM_AFTER_TIME),'HH24:MI'),TO_CHAR(ATT_LOGIN,'HH24:MI'), "
						+ "  CASE WHEN ATT_STATUS_TWO='LC' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) WHEN ATT_STATUS_TWO='EL' THEN NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) ELSE NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 1, 2)), 0) * 60 +  "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_LATE_HRS, 'HH24:MI'), 4, 5)), 0) + "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI'), 4, 5)), 0) end ,"
						+ " NVL(TRUNC(MOD( ( TO_DATE(TO_char(ATT_LOGIN, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(SFT_START_TIME, 'HH24.MI'),'HH24.MI'))*24,24))*60	 + TRUNC(MOD( ( TO_DATE(TO_char(ATT_LOGIN, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(SFT_START_TIME, 	'HH24.MI'),'HH24.MI'))*24*60,60)),0)  AS DIFFERENCE     "
						+ "  ,TO_CHAR(ATT_LOGOUT,'HH24:MI')  "
						+ "	 FROM HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " "
						+ "	INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_DAILY_ATTENDANCE_"
						+ year
						+ ".ATT_SHIFT_ID) "
						+ "	WHERE ATT_EMP_ID="
						+ empCode
						+ " AND TO_CHAR(ATT_DATE,'MM')="
						+ month
						+ " AND ATT_STATUS_ONE IN('PR','AB','WO','HO') "
						+ "	AND ATT_STATUS_TWO IN('LC','EL','DL','AB','HD','IN') ";

				query += " and (ATT_REG_STATUS_LATE is null OR ATT_REG_STATUS_LATE='NR') AND (ATT_REG_STATUS_SWIPE IS NULL OR ATT_REG_STATUS_SWIPE='NR') ";

				if (!emp_list.equals("")) {
					querySeipe += " AND TO_CHAR(ATT_DATE,'DD-MM-YYYY') in("
							+ emp_list + ")   ";
				}
				if (!delEmpList.equals("")) {
					querySeipe += " AND  TO_CHAR(ATT_DATE,'DD-MM-YYYY') NOT in("
							+ delEmpList + ")  ";
				}
				querySeipe += " ORDER BY ATT_DATE ";
				Object[][] swipe = getSqlModel().getSingleResult(querySeipe);
				ArrayList list = new ArrayList();
				regularization.setActionName("applySwipeApplication");
				regularization.setSendBackActionName("showRegularizationList");
				regularization.setSwipeFlag("true");
				regularization.setSwipeList(null);
				// String[] actIN, String[] actOUT
				if (swipe != null && swipe.length > 0) {
					for (int i = 0; i < swipe.length; i++) {
						Regularization bean = new Regularization();
						bean.setDate(String.valueOf(swipe[i][1]));

						if (regularization.getSource() != null) {
							if (regularization.getSource().equals("mytimecard")) {

								bean.setShiftTime(actIN[0]);

								bean.setInTime(actOUT[0]);

							}

						} else {
							bean.setShiftTime(String.valueOf(swipe[i][3]));// recorded
							// in time
							bean.setInTime(String.valueOf(swipe[i][6]));// recorded
							// out
							// time
						}

						bean.setFromTime("");
						bean.setToTime("");
						bean.setTotalDiff("");
						if (actIN != null && actIN.length > 0) {
							bean.setFromTime(actIN[i]);// in time
						}
						if (actOUT != null && actOUT.length > 0) {
							bean.setToTime(actOUT[i]);// in time
						}
						if (regularization.getSource() != null) {
							if (regularization.getSource().equals("mytimecard")) {
								bean.setFromTime("");
								bean.setToTime("");
							}
						}

						list.add(bean);
					}
				}
				regularization.setSwipeList(list);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
					e.printStackTrace();
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
			e.printStackTrace();
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
			// dd=hh>=(shiftMinuts/2):dd
			newDD = dd;

			/*
			 * if((hh)>=(shiftMinuts/120)){ newDD=newDD+0.5f;
			 * hh=hh-(shiftMinuts/120);
			 * System.out.println("hh------------------------- : "+hh); }
			 */
			if ((hh * 60) >= (shiftMinuts / 2)) {
				newDD = newDD + 0.5f;
				hh = (hh * 60) - (shiftMinuts / 2);
				// hh=hh/60>0?hh/60:
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
			e.printStackTrace();
		}
		days = newDD + " Days " + hrs + ":" + minute;
		return days;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void deleteReg(Regularization regularization, String[] date,
			String month, String year, String type, String empCode,
			String[] recIN, String[] recOUT, String[] actIN, String[] actOUT) {
		if (date != null && date.length > 0) {
			for (int i = 0; i < date.length; i++) {
			}
		}
		String query = "INSERT INTO(SWIPE_REG_ID, SWIPE_REG_EMP_ID, SWIPE_REG_APPLICATION_DATE, SWIPE_REG_STATUS, SWIPE_REG_REASON, SWIPE_REG_APPROVER, SWIPE_REG_ALT_APPROVER, SWIPE_REG_KEEP_INFORM) VALUES((SELECT NVL(MAX(SWIPE_REG_ID),0)+1 FROM HRMS_SWIPE_REG_HDR),?,?,?,?,?,?,?)";
		Object[][] data = new Object[1][7];

	}
	public boolean saveRedressalApplication(Regularization regularization,
			String[] leaveCode, String[] fromDate, String[] toDate,
			String[] penaltyDays, String[] redressalDays,
			String[] keepInformCode, String[] approverCode,
			String[] redressalAdjDays) {

		String year = regularization.getYear();
		String empCode = regularization.getUserEmpId();
		String appName = "";
		if (approverCode != null && approverCode.length > 0) {
			appName = approverCode[0];
		}
		// GET INFORM LIST
		String keepInfo = "";
		if (keepInformCode != null && keepInformCode.length > 0) {
			for (int i = 0; i < keepInformCode.length; i++) {
				keepInfo += keepInformCode[i] + ",";
			}
			keepInfo = keepInfo.substring(0, keepInfo.length() - 1);
		}
		logger.info("LIST OF KEEP INFO : " + keepInfo);
		String redreCode = "";
		boolean flag = false;
		if (regularization.getStatus().equals("B")) {
			/**
			 * APPLY SEND BACK APPLICATION
			 */
			String upQuery = "UPDATE HRMS_REDRESSAL_HDR SET REDRESSAL_APPLICATION_DATE=TO_DATE(?,'DD-MM-YYYY'),REDRESSAL_STATUS=?,REDRESSAL_REASON=?,REDRESSAL_APPROVER=?,REDRESSAL_ALT_APPROVER=?,REDRESSAL_KEEP_INFORM=?,REDRESSAL_LEVEL=? WHERE REDRESSAL_ID=? ";
			Object[][] data = new Object[1][8];
			data[0][0] = regularization.getApplicationDate();// application
			// date
			data[0][1] = "P";// status
			data[0][2] = regularization.getReason();// reason
			data[0][3] = appName;// approver
			data[0][4] = "";// alternate approver
			data[0][5] = keepInfo;// keep infor
			data[0][6] = "1";
			data[0][7] = regularization.getApplicationCode();
			flag = getSqlModel().singleExecute(upQuery, data);
			redreCode = regularization.getApplicationCode();
			regularization.setRegularizationApplCode(redreCode);
			if (flag) {
				String delPATHQuery = "DELETE FROM HRMS_REDRESSAL_PATH WHERE REDRESSAL_ID="
						+ redreCode;
				String delDTLQuery = "DELETE FROM HRMS_REDRESSAL_DTL WHERE REDRESSAL_ID="
						+ redreCode;
				// getSqlModel().singleExecute(delPATHQuery);
				getSqlModel().singleExecute(delDTLQuery);
			}
		} else {
			/**
			 * NEW APPLICATION
			 */
			String query = "INSERT INTO HRMS_REDRESSAL_HDR(REDRESSAL_ID, REDRESSAL_EMP_ID, REDRESSAL_APPLICATION_DATE, REDRESSAL_STATUS, REDRESSAL_REASON, REDRESSAL_APPROVER, REDRESSAL_ALT_APPROVER, REDRESSAL_KEEP_INFORM,REDRESSAL_LEVEL)"
					+ "   VALUES((SELECT NVL(MAX(REDRESSAL_ID),0)+1 FROM HRMS_REDRESSAL_HDR),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,1)";
			Object[][] data = new Object[1][7];
			data[0][0] = regularization.getEmpCode();// emp code
			data[0][1] = regularization.getApplicationDate();// application
			// date
			data[0][2] = "P";// status
			data[0][3] = regularization.getReason();// reason
			data[0][4] = appName;// approver
			data[0][5] = "";// alternate approver
			data[0][6] = keepInfo;// keep infor
			flag = getSqlModel().singleExecute(query, data);

			String swipeQue = "(SELECT NVL(MAX(REDRESSAL_ID),0) FROM HRMS_REDRESSAL_HDR)";
			Object[][] sCode = getSqlModel().getSingleResult(swipeQue);
			if (sCode != null && sCode.length > 0) {
				redreCode = String.valueOf(sCode[0][0]);
				regularization.setRegularizationApplCode(redreCode);
			}

		}

		if (flag) {
			/**
			 * INSERT INTO PATCH
			 */
			if (approverCode != null && approverCode.length > 0) {
				String insPath = "INSERT INTO HRMS_REDRESSAL_PATH(REDRESSAL_PATH_CODE,REDRESSAL_ID,REDRESSAL_PATH_ASSESS_BY,REDRESSAL_PATH_STATUS) VALUES(?,?,?,?)";
				Object[][] path = new Object[approverCode.length][4];
				for (int i = 0; i < path.length; i++) {

					path[i][0] = (i + 1);
					path[i][1] = redreCode;
					path[i][2] = approverCode[i];
					path[i][3] = "P";
				}
				// getSqlModel().singleExecute(insPath,path);
			}
			String insQuery = "INSERT INTO HRMS_REDRESSAL_DTL(REDRESSAL_ID,REDRESSAL_DTL_LEAVE_CODE, REDRESSAL_DTL_FROM_DATE, REDRESSAL_DTL_TO_DATE,  REDRESSAL_DTL_PENALTY_DAYS, REDRESSAL_DTL_REDRESSED_DAYS, REDRESSAL_DTL_STATUS,REDRESSAL_DTL_PENALTY_ADJ_DAYS)"
					+ "         VALUES (?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,?)";
			Object[][] insData = new Object[fromDate.length][8];
			for (int i = 0; i < fromDate.length; i++) {
				insData[i][0] = redreCode;
				insData[i][1] = leaveCode[i];// fromdate
				insData[i][2] = fromDate[i];// fromdate
				insData[i][3] = toDate[i];// todate
				insData[i][4] = penaltyDays[i];// penaltyDays
				insData[i][5] = redressalDays[i];// redressaldays
				insData[i][6] = "P";// status
				insData[i][7] = redressalAdjDays[i];
			}
			boolean flag1 = getSqlModel().singleExecute(insQuery, insData);
			/*
			 * UPDATE LEAVE DTL STATUS
			 */
			String updateLeave = "UPDATE HRMS_LEAVE_DTL SET LEAVE_REDRESSAL_FLAG='Y' WHERE LEAVE_CODE=? AND LEAVE_FROM_DATE=TO_DATE(?,'DD-MM-YYYY') AND LEAVE_TO_DATE=TO_DATE(?,'DD-MM-YYYY') AND EMP_ID=?";
			Object[][] upData = new Object[fromDate.length][4];
			for (int i = 0; i < fromDate.length; i++) {
				upData[i][0] = leaveCode[i];
				upData[i][1] = fromDate[i];// fromdate
				upData[i][2] = toDate[i];// todate
				upData[i][3] = regularization.getEmpCode();// emp code
			}
			boolean flag2 = getSqlModel().singleExecute(updateLeave, upData);
		}

		return flag;
	}

	public boolean applySwipeApplication(Regularization regularization,
			String[] date, String[] recIN, String[] recOUT, String[] actIN,
			String[] actOUT, String[] keepInformCode, String[] approverCode, String[] reasonItt) {
		// TODO Auto-generated method stub
		// SET APPROVER
		boolean result = false;
		try {
			String year = "";
			/*if (regularization.getSource().equals("mytimecard")) {
				year = date != null ? date[0].substring(6, 10) : "";
			} else {
				year = regularization.getYear();
			}*/

			String empCode =regularization.getEmpCode(); //regularization.getUserEmpId();
			String appName = "";
			if (approverCode != null && approverCode.length > 0) {
				appName = approverCode[0];
			}
			// GET INFORM LIST
			String keepInfo = "";
			if (keepInformCode != null && keepInformCode.length > 0) {
				for (int i = 0; i < keepInformCode.length; i++) {
					keepInfo += keepInformCode[i] + ",";
				}
				keepInfo = keepInfo.substring(0, keepInfo.length() - 1);
			}
			logger.info("LIST OF KEEP INFO : " + keepInfo);
			String swipeCode = "";
			if (regularization.getStatus().equals("B")) {
				// get date
				if (date != null && date.length > 0) {
					year = String.valueOf(date[0]).substring(6, 10);
				}

				String upQuery = "UPDATE HRMS_SWIPE_REG_HDR SET SWIPE_REG_APPLICATION_DATE=TO_DATE(?,'DD-MM-YYYY'),"
								+ " SWIPE_REG_STATUS=?,SWIPE_REG_REASON=?,SWIPE_REG_APPROVER=?,SWIPE_REG_ALT_APPROVER=?,"
								+ " SWIPE_REG_KEEP_INFORM=?,SWIPE_LEVEL=?, SWIPE_REG_TYPE=? WHERE SWIPE_REG_ID=? ";
				Object[][] data = new Object[1][9];
				data[0][0] = regularization.getApplicationDate();// application
				// date
				data[0][1] = "P";// status
				data[0][2] = regularization.getReason();// reason
				data[0][3] = appName;// approver
				data[0][4] = "";// alternate approver
				data[0][5] = keepInfo;// keep infor
				data[0][6] = "1";
				data[0][7] = regularization.getApplyFor();
				data[0][8] = regularization.getApplicationCode();
				result = getSqlModel().singleExecute(upQuery, data);
				swipeCode = regularization.getApplicationCode();
				regularization.setRegularizationApplCode(swipeCode);
				if (result) {
					String delDTLQuery = "DELETE FROM HRMS_SWIPE_REG_DTL WHERE SWIPE_REG_ID="
							+ swipeCode;
					getSqlModel().singleExecute(delDTLQuery);
				}
			} else {
				String query = "INSERT INTO HRMS_SWIPE_REG_HDR(SWIPE_REG_ID, SWIPE_REG_EMP_ID, "
							  + " SWIPE_REG_APPLICATION_DATE, SWIPE_REG_STATUS,SWIPE_REG_TYPE, SWIPE_REG_APPROVER, SWIPE_REG_ALT_APPROVER, SWIPE_REG_KEEP_INFORM,"
							  + " SWIPE_REG_REASON,SWIPE_LEVEL)"
							  + " VALUES((SELECT NVL(MAX(SWIPE_REG_ID),0)+1 FROM HRMS_SWIPE_REG_HDR),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,1)";
				Object[][] data = new Object[1][8];
				data[0][0] = regularization.getEmpCode();// emp code
				data[0][1] = regularization.getApplicationDate();// application
				// date
				data[0][2] = "P";// status
				data[0][3] = regularization.getApplyFor();//Apply For LT, SR, RR OR PR
				data[0][4] = appName;// approver
				data[0][5] = "";// alternate approver
				data[0][6] = keepInfo;// keep infor
				//data[0][7] = regularization.getApplyFor();// keep infor
				data[0][7]= regularization.getReason();
				result = getSqlModel().singleExecute(query, data);
				String swipeQue = "(SELECT NVL(MAX(SWIPE_REG_ID),0) FROM HRMS_SWIPE_REG_HDR)";
				Object[][] sCode = getSqlModel().getSingleResult(swipeQue);
				if (sCode != null && sCode.length > 0) {
					swipeCode = String.valueOf(sCode[0][0]);
					regularization.setRegularizationApplCode(swipeCode);
				}
			}
			if (result) {

				/**
				 * INSERT INTO PATCH
				 */
				if (approverCode != null && approverCode.length > 0) {
					String insPath = "INSERT INTO HRMS_SWIPE_REG_PATH(SWIPE_REG_PATH_CODE,SWIPE_REG_ID,SWIPE_REG_PATH_ASSESS_BY,SWIPE_REG_PATH_STATUS) VALUES(?,?,?,?)";
					Object[][] path = new Object[approverCode.length][4];
					for (int i = 0; i < path.length; i++) {
						path[i][0] = (i + 1);
						path[i][1] = swipeCode;
						path[i][2] = approverCode[i];
						path[i][3] = "P";
					}
					// getSqlModel().singleExecute(insPath,path);
				}

				String insQuery = "INSERT INTO HRMS_SWIPE_REG_DTL(SWIPE_REG_ID, SWIPE_REG_DTL_DATE, SWIPE_REG_DTL_FROM_TIME, SWIPE_REG_DTL_TO_TIME, SWIPE_REG_DTL_STATUS, SWIPE_REG_DTL_ACT_INTIME, SWIPE_REG_DTL_ACT_OUTTIME,SWIPE_REG_REASON)"
						+ "         VALUES (?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),?,TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),?)";
				Object[][] insData = new Object[date.length][8];

				for (int i = 0; i < date.length; i++) {
					insData[i][0] = swipeCode;
					insData[i][1] = date[i];// date
					insData[i][2] = recIN[i];// reported in
					insData[i][3] = recOUT[i];// reported out
					insData[i][4] = "P";
					insData[i][5] = actIN[i];// reported in
					insData[i][6] = actOUT[i];// reported out
					insData[i][7] = reasonItt[i];
				}
				boolean flag1 = getSqlModel().singleExecute(insQuery, insData);

				/**
				 * CHANGES IN DAILY ATTANDANCE TABLE CHANGES---ACTUAL LOGIN
				 * TIME,ACTUAL LOGOUT TIME
				 */
				
				
					if(flag1)
					{
						result=insertOrUpdateAttendance(date, empCode, actIN, actOUT);
					}
				
				
				

				/*String upDate = "UPDATE  HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " SET ATT_REG_LOGIN=TO_DATE(?,'HH24:MI') , ATT_REG_LOGOUT=TO_DATE(?,'HH24:MI') , ATT_REG_STATUS_SWIPE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=? ";
				Object[][] sData = new Object[date.length][5];
				for (int i = 0; i < date.length; i++) {
					sData[i][0] = actIN[i];// reported in
					sData[i][1] = actOUT[i];// reported out
					sData[i][2] = "PS";
					sData[i][3] = date[i];
					sData[i][4] = empCode;
				}
				result = getSqlModel().singleExecute(upDate, sData);

				if (result) {
					if (date != null && date.length > 0) {
						for (int i = 0; i < date.length; i++) {
							result = updateAttendance(date[i], empCode, year);
						}

					}

				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private boolean insertOrUpdateAttendance(String[]dateObj,String empCode,String[]actIN ,String[] actOUT )
	{
		boolean result =false;
		
		if(dateObj!=null && dateObj.length >0)
		{
			for (int i = 0; i< dateObj.length; i++) {
				String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
					+ dateObj[i].substring(6, 10)
					+ " where ATT_DATE=to_date('"
					+ dateObj[i]
					+ "','dd-mm-yyyy') and ATT_EMP_ID='"
					+ empCode + " ' ";

			Object[][] selectObj = getSqlModel().getSingleResult(selectQry);
			
			String shiftQuery = " select EMP_SHIFT from hrms_emp_offc where emp_id="
				+ empCode;
			Object[][] shiftObj = getSqlModel().getSingleResult(shiftQuery);
		
			 
				if(selectObj!=null && selectObj.length>0)
				{
					String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
						+ dateObj[i].substring(6, 10)
						+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,IS_APPL_PROCESS='Y'"
						//+" , ATT_REG_LOGIN=TO_DATE('"+actIN[i]+"','HH24:MI') , ATT_REG_LOGOUT=TO_DATE('"+actOUT[i]+"','HH24:MI') , ATT_REG_STATUS_SWIPE='PS' "
						+ " WHERE ATT_DATE=to_date ( '"
						+ dateObj[i]
						+ "','dd-mm-yyyy')"
						+ " and ATT_EMP_ID=" + empCode;

				result =getSqlModel().singleExecute(
						myCardTimeActualDataUpdate);
				}
				else{
					String actualDataInsertInAtt = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
						+ dateObj[i].substring(6, 10)
						+ "(ATT_DATE, ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_REG_STATUS_ONE, IS_APPL_PROCESS,ATT_SHIFT_ID,ATT_REG_LOGIN,ATT_REG_LOGOUT) "
						+ "VALUES(to_date('"
						+ dateObj[i]
						+ "','dd-mm-yyyy'),"
						+ empCode
						+ ",'AB','AB','AB','Y',"
						+ shiftObj[0][0]+",TO_DATE('"+actIN[i]+"','HH24:MI'),TO_DATE('"+actOUT[i]+"','HH24:MI'))";

				result =getSqlModel().singleExecute(actualDataInsertInAtt);
				}
			}
			
		}
		
		
		return result ;
	}

	/*private boolean updateAttendance(String date, String empCode, String year) {
		boolean result = false;
		try {
			String upDate = "UPDATE  HRMS_DAILY_ATTENDANCE_" + year
					+ " set  IS_APPL_PROCESS='Y'   WHERE ATT_DATE=TO_DATE('"
					+ date + "','DD-MM-YYYY') AND ATT_EMP_ID=" + empCode;
			result = getSqlModel().singleExecute(upDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	/**
	 * APPLY FOR PERSONAL TIME
	 * 
	 * @param regularization
	 * @param keepInformCode
	 * @param approverCode
	 * @param ptDateItt
	 * @param ptFromTimeItt
	 * @param ptToTimeItt
	 * @return
	 */
	public boolean applyPersonalTimeApplication(Regularization regularization,
			String[] keepInformCode, String[] approverCode, String[] ptDateItt,
			String[] ptFromTimeItt, String[] ptToTimeItt) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String year = regularization.getYear();
		String empCode = regularization.getUserEmpId();
		String appName = "";
		if (approverCode != null && approverCode.length > 0) {
			appName = approverCode[0];
		}
		// GET INFORM LIST
		String keepInfo = "";
		if (keepInformCode != null && keepInformCode.length > 0) {
			for (int i = 0; i < keepInformCode.length; i++) {
				keepInfo += keepInformCode[i] + ",";
			}
			keepInfo = keepInfo.substring(0, keepInfo.length() - 1);
		}
		logger.info("LIST OF KEEP INFO : " + keepInfo);
		/**
		 * check send bak application or new application
		 */
		String ptCode = "";
		if (regularization.getStatus().equals("B")) {
			String upQuery = "UPDATE HRMS_PT_REG_HDR SET PT_REG_APPLICATION_DATE=TO_DATE(?,'DD-MM-YYYY'),PT_REG_STATUS=?,PT_REG_REASON=?,PT_REG_APPROVER=?,PT_REG_ALT_APPROVER=?,PT_REG_KEEP_INFORM=?,PT_REG_LEVEL=? WHERE PT_REG_ID=? ";
			Object[][] data = new Object[1][8];
			data[0][0] = regularization.getApplicationDate();// application
			// date
			data[0][1] = "P";// status
			data[0][2] = regularization.getReason();// reason
			data[0][3] = appName;// approver
			data[0][4] = "";// alternate approver
			data[0][5] = keepInfo;// keep infor
			data[0][6] = "1";
			data[0][7] = regularization.getApplicationCode();
			flag = getSqlModel().singleExecute(upQuery, data);
			ptCode = regularization.getApplicationCode();
			regularization.setRegularizationApplCode(ptCode);
			if (flag) {
				String delPATHQuery = "DELETE FROM HRMS_PT_REG_PATH WHERE PT_REG_ID="
						+ ptCode;
				String delDTLQuery = "DELETE FROM HRMS_PT_REG_DTL WHERE PT_REG_ID="
						+ ptCode;
				// getSqlModel().singleExecute(delPATHQuery);
				getSqlModel().singleExecute(delDTLQuery);
			}
		} else {
			String query = "INSERT INTO HRMS_PT_REG_HDR(PT_REG_ID, PT_REG_EMP_ID, PT_REG_APPLICATION_DATE, PT_REG_STATUS, PT_REG_REASON, PT_REG_APPROVER, PT_REG_ALT_APPROVER, PT_REG_KEEP_INFORM,PT_REG_LEVEL)"
					+ "   VALUES((SELECT NVL(MAX(PT_REG_ID),0)+1 FROM HRMS_PT_REG_HDR),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,1)";
			Object[][] data = new Object[1][7];
			data[0][0] = regularization.getEmpCode();// emp code
			data[0][1] = regularization.getApplicationDate();// application
			// date
			data[0][2] = "P";// status
			data[0][3] = regularization.getReason();// reason
			data[0][4] = appName;// approver
			data[0][5] = "";// alternate approver
			data[0][6] = keepInfo;// keep infor
			flag = getSqlModel().singleExecute(query, data);

			String swipeQue = "(SELECT NVL(MAX(PT_REG_ID),0) FROM HRMS_PT_REG_HDR)";
			Object[][] sCode = getSqlModel().getSingleResult(swipeQue);
			if (sCode != null && sCode.length > 0) {
				ptCode = String.valueOf(sCode[0][0]);
				regularization.setRegularizationApplCode(ptCode);
			}

		}

		if (flag) {
			/**
			 * INSERT INTO PATCH
			 */
			if (approverCode != null && approverCode.length > 0) {
				String insPath = "INSERT INTO HRMS_PT_REG_PATH(PT_REG_PATH_CODE,PT_REG_ID,PT_REG_PATH_ASSESS_BY,PT_REG_PATH_STATUS) VALUES(?,?,?,?)";
				Object[][] path = new Object[approverCode.length][4];
				for (int i = 0; i < path.length; i++) {
					path[i][0] = (i + 1);
					path[i][1] = ptCode;
					path[i][2] = approverCode[i];
					path[i][3] = "P";
				}
				// getSqlModel().singleExecute(insPath,path);
			}
			String insQuery = "INSERT INTO HRMS_PT_REG_DTL(PT_REG_ID, PT_REG_DTL_DATE,PT_REG_DTL_FROM_TIME, PT_REG_DTL_TO_TIME, PT_REG_DTL_STATUS)"
					+ "         VALUES (?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),?)";
			Object[][] insData = new Object[ptDateItt.length][5];

			for (int i = 0; i < ptDateItt.length; i++) {
				insData[i][0] = ptCode;
				insData[i][1] = ptDateItt[i];// date
				insData[i][2] = ptFromTimeItt[i];// date
				insData[i][3] = ptToTimeItt[i];// SHIFTTIME
				insData[i][4] = "P";// STATUS
			}
			getSqlModel().singleExecute(insQuery, insData);
		}

		return flag;
	}

	public boolean applyLateApplication(Regularization regularization,
			String[] date, String[] shiftTime, String[] inTime,
			String[] lateHrs, String[] lateHrsDeduct,
			String[] lateHrsDeductFromCode, String[] keepInformCode,
			String[] approverCode, String[] sLeaveCode,
			String[] remainingBalance) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String result = "";
		String year = regularization.getYear();
		String empCode = regularization.getUserEmpId();
		String appName = "";
		if (approverCode != null && approverCode.length > 0) {
			appName = approverCode[0];
		}
		// GET INFORM LIST
		String keepInfo = "";
		if (keepInformCode != null && keepInformCode.length > 0) {
			for (int i = 0; i < keepInformCode.length; i++) {
				keepInfo += keepInformCode[i] + ",";
			}
			keepInfo = keepInfo.substring(0, keepInfo.length() - 1);
		}
		logger.info("LIST OF KEEP INFO : " + keepInfo);
		String lateCode = "";
		if (regularization.getStatus().equals("B")) {

			String upQuery = "UPDATE HRMS_LATE_REG_HDR SET LATE_REG_APPLICATION_DATE=TO_DATE(?,'DD-MM-YYYY'),LATE_REG_STATUS=?,LATE_REG_REASON=?,LATE_REG_APPROVER=?,LATE_REG_ALT_APPROVER=?,LATE_REG_KEEP_INFORM=?,LATE_LEVEL=? WHERE LATE_REG_ID=? ";
			Object[][] data = new Object[1][8];
			data[0][0] = regularization.getApplicationDate();// application
			// date
			data[0][1] = "P";// status
			data[0][2] = regularization.getReason();// reason
			data[0][3] = appName;// approver
			data[0][4] = "";// alternate approver
			data[0][5] = keepInfo;// keep infor
			data[0][6] = "1";
			data[0][7] = regularization.getApplicationCode();
			flag = getSqlModel().singleExecute(upQuery, data);
			lateCode = regularization.getApplicationCode();
			regularization.setRegularizationApplCode(lateCode);
			if (flag) {
				String delDTLQuery = "DELETE FROM HRMS_LATE_REG_DTL WHERE LATE_REG_ID="
						+ lateCode;
				getSqlModel().singleExecute(delDTLQuery);
			}
			String insQuery = "INSERT INTO HRMS_LATE_REG_DTL(LATE_REG_ID, LATE_REG_DTL_DATE,LATE_REG_DTL_LEAVE_ID, LATE_REG_DTL_SFTTIME, LATE_REG_DTL_INTIME, LATE_REG_DTL_HRS_DED,  LATE_REG_DTL_STATUS)"
					+ "         VALUES (?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),?)";
			Object[][] insData = new Object[date.length][7];

			for (int i = 0; i < date.length; i++) {
				insData[i][0] = lateCode;
				insData[i][1] = date[i];// date
				insData[i][2] = lateHrsDeductFromCode[i];// date
				insData[i][3] = shiftTime[i];// SHIFTTIME
				insData[i][4] = inTime[i];// INTIME
				insData[i][5] = lateHrsDeduct[i];// DEDLATEHRS
				insData[i][6] = "P";// STATUS
			}
			boolean flag1 = getSqlModel().singleExecute(insQuery, insData);

			/**
			 * CHANGES IN DAILY ATTANDANCE TABLE CHANGES---ACTUAL LOGIN
			 * TIME,ACTUAL LOGOUT TIME
			 */
			if (date != null && date.length > 0) {
				year = String.valueOf(date[0]).substring(6, 10);
			}

			String upDate = "UPDATE  HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " SET  ATT_REG_STATUS_LATE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=? ";
			Object[][] sData = new Object[date.length][3];
			for (int i = 0; i < date.length; i++) {
				sData[i][0] = "PL";
				sData[i][1] = date[i];
				sData[i][2] = empCode;
			}
			if (flag1) {
				/**
				 * UPDATE ATTENDANCE TABLE
				 */
				getSqlModel().singleExecute(upDate, sData);
				/**
				 * UPDATE EMPLOYEE BALANCE
				 */
				if (lateHrsDeductFromCode != null
						&& lateHrsDeductFromCode.length > 0) {
					Object[][] balData = new Object[1][3];
					for (int i = 0; i < lateHrsDeductFromCode.length; i++) {
						String qq = "SELECT NVL(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),'00:00') FROM HRMS_LEAVE_BALANCE WHERE EMP_ID="
								+ regularization.getEmpCode()
								+ " AND LEAVE_CODE=" + lateHrsDeductFromCode[i];
						Object[][] bal = getSqlModel().getSingleResult(qq);
						if (bal != null && bal.length > 0) {
							String difference = "00:00";
							int avalBal = getMinute(String.valueOf(bal[0][0]));
							int deductBal = getMinute(lateHrsDeduct[i]);
							if (avalBal >= deductBal) {
								difference = getHH_MM(avalBal - deductBal);
							}
							balData[0][0] = difference.trim();
							balData[0][1] = regularization.getEmpCode();
							balData[0][2] = lateHrsDeductFromCode[i];
							String upQueryBack = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_HRS_CLOSING_BALANCE=TO_DATE(?,'HH24:MI')"
									+ "   WHERE EMP_ID=? AND LEAVE_CODE=?";
							getSqlModel().singleExecute(upQueryBack, balData);
						}
					}

				}

			}

		}

		else {
			String query = "INSERT INTO HRMS_LATE_REG_HDR(LATE_REG_ID, LATE_REG_EMP_ID, LATE_REG_APPLICATION_DATE, LATE_REG_STATUS, LATE_REG_REASON, LATE_REG_APPROVER, LATE_REG_ALT_APPROVER, LATE_REG_KEEP_INFORM,LATE_LEVEL)"
					+ "   VALUES((SELECT NVL(MAX(LATE_REG_ID),0)+1 FROM HRMS_LATE_REG_HDR),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,1)";
			Object[][] data = new Object[1][7];
			data[0][0] = regularization.getEmpCode();// emp code
			data[0][1] = regularization.getApplicationDate();// application
			// date
			data[0][2] = "P";// status
			data[0][3] = regularization.getReason();// reason
			data[0][4] = appName;// approver
			data[0][5] = "";// alternate approver
			data[0][6] = keepInfo;// keep infor
			flag = getSqlModel().singleExecute(query, data);

			String swipeQue = "(SELECT NVL(MAX(LATE_REG_ID),0) FROM HRMS_LATE_REG_HDR)";
			Object[][] sCode = getSqlModel().getSingleResult(swipeQue);
			if (sCode != null && sCode.length > 0) {
				lateCode = String.valueOf(sCode[0][0]);
				regularization.setRegularizationApplCode(lateCode);
			}
			if (flag) {

				String insQuery = "INSERT INTO HRMS_LATE_REG_DTL(LATE_REG_ID, LATE_REG_DTL_DATE,LATE_REG_DTL_LEAVE_ID, LATE_REG_DTL_SFTTIME, LATE_REG_DTL_INTIME, LATE_REG_DTL_HRS_DED,  LATE_REG_DTL_STATUS)"
						+ "         VALUES (?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),TO_DATE(?,'HH24:MI'),?)";
				Object[][] insData = new Object[date.length][7];

				for (int i = 0; i < date.length; i++) {
					insData[i][0] = lateCode;
					insData[i][1] = date[i];// date
					insData[i][2] = lateHrsDeductFromCode[i];// date
					insData[i][3] = shiftTime[i];// SHIFTTIME
					insData[i][4] = inTime[i];// INTIME
					insData[i][5] = lateHrsDeduct[i];// DEDLATEHRS
					insData[i][6] = "P";// STATUS
				}
				boolean flag1 = getSqlModel().singleExecute(insQuery, insData);

				/**
				 * CHANGES IN DAILY ATTANDANCE TABLE CHANGES---ACTUAL LOGIN
				 * TIME,ACTUAL LOGOUT TIME
				 */
				String upDate = "UPDATE  HRMS_DAILY_ATTENDANCE_"
						+ year
						+ " SET  ATT_REG_STATUS_LATE=? WHERE ATT_DATE=TO_DATE(?,'DD-MM-YYYY') AND ATT_EMP_ID=? ";
				Object[][] sData = new Object[date.length][3];
				for (int i = 0; i < date.length; i++) {
					sData[i][0] = "PL";
					sData[i][1] = date[i];
					sData[i][2] = empCode;
				}
				if (flag1) {
					/**
					 * UPDATE ATTENDANCE TABLE
					 */
					getSqlModel().singleExecute(upDate, sData);
					/**
					 * UPDATE EMPLOYEE BALANCE
					 */
					if (sLeaveCode != null && sLeaveCode.length > 0) {
						Object[][] balData = new Object[sLeaveCode.length][4];
						for (int i = 0; i < sLeaveCode.length; i++) {
							String remBala = remainingBalance[i];
							String[] balance = remBala.split("Days");
							balData[i][0] = balance[0].trim();
							balData[i][1] = balance[1].trim();
							balData[i][2] = regularization.getEmpCode();
							balData[i][3] = sLeaveCode[i];
						}
						String upQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE=?,LEAVE_HRS_CLOSING_BALANCE=TO_DATE(?,'HH24:MI')"
								+ "   WHERE EMP_ID=? AND LEAVE_CODE=?";
						getSqlModel().singleExecute(upQuery, balData);
					}

				}

			}
		}

		return flag;
	}

	public void setApproverData(Regularization regularization,
			Object[][] empFlow) {

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
				approverDataObj[i][0] = checkNull(String.valueOf(temObj[0][0]));
				approverDataObj[i][1] = checkNull(String.valueOf(temObj[0][2]));
			}
		}
		regularization.setApproverList(null);
		if (approverDataObj != null && approverDataObj.length > 0) {
			ArrayList arrList = new ArrayList();
			for (int i = 0; i < approverDataObj.length; i++) {
				Regularization leaveBean = new Regularization();
				leaveBean
						.setApproverName(String.valueOf(approverDataObj[i][0]));
				String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
				leaveBean.setApprSrNo(srNo);
				leaveBean
						.setApproverCode(String.valueOf(approverDataObj[i][1]));
				arrList.add(leaveBean);
			}
			regularization.setApproverList(arrList);
		}
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

	public void addInformList(Regularization regularization,
			String[] keepInformCode, String[] keepInform, String chk) {
		// TODO Auto-generated method stub
		regularization.setInformList(null);
		ArrayList arrList = new ArrayList();
		int count = 0;
		try {
			count = Integer.parseInt(regularization.getKeepHidden());
		} catch (Exception e) {
			e.printStackTrace();
		}
		regularization.setKeepHidden("");

		if (keepInform != null && keepInform.length > 0) {
			if (chk.equals("remove")) {
				for (int i = 0; i < keepInform.length; i++) {
					Regularization leaveBean = new Regularization();
					if (i != (count)) {
						leaveBean.setKeepInformCode(keepInformCode[i]);
						leaveBean.setKeepInform(keepInform[i]);
						arrList.add(leaveBean);
					}
				}
			} else {
				for (int i = 0; i < keepInform.length; i++) {
					Regularization leaveBean = new Regularization();
					leaveBean.setKeepInformCode(keepInformCode[i]);
					leaveBean.setKeepInform(keepInform[i]);
					arrList.add(leaveBean);
				}
			}
		}

		if (chk.equals("add")) {
			Regularization leaveBean = new Regularization();
			leaveBean.setKeepInformCode(regularization.getInformCode());
			leaveBean.setKeepInform(regularization.getInformName());
			arrList.add(leaveBean);
		}
		regularization.setInformList(arrList);
	}

	public void callOnLoad(Regularization regularization, String status,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String empCode = regularization.getUserEmpId();

		/**
		 * CODING FOR SWIPE MISS SYSTEM
		 */
		String swipeQuery = "SELECT SWIPE_REG_ID,EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME ,TO_CHAR(SWIPE_REG_APPLICATION_DATE,'DD-MM-YYYY'),SWIPE_REG_STATUS ,NVL(SWIPE_REG_TYPE,0) FROM HRMS_SWIPE_REG_HDR "
				+ "	  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SWIPE_REG_HDR.SWIPE_REG_EMP_ID) "
				+ "	  WHERE SWIPE_REG_STATUS='"
				+ status
				+ "' AND HRMS_SWIPE_REG_HDR.SWIPE_REG_EMP_ID="
				+ empCode
				+ " ORDER BY SWIPE_REG_ID DESC";
		Object[][] swipeData = getSqlModel().getSingleResult(swipeQuery);
		regularization.setSwipeList(null);
		regularization.setPageSwipe("false");
		if (swipeData != null && swipeData.length > 0) {
			ArrayList swipeList = new ArrayList();
			for (int i = 0; i < swipeData.length; i++) {
				Regularization bean = new Regularization();
				bean.setEmpToken(String.valueOf(swipeData[i][1]));
				bean.setEmpName(String.valueOf(swipeData[i][2]));
				bean.setApplicationDate(String.valueOf(swipeData[i][3]));
				bean.setApplicationType(String.valueOf(swipeData[i][4]));
				bean.setApplyFor(String.valueOf(swipeData[i][5]));
				bean.setSwipeAppCode(String.valueOf(swipeData[i][0]));
				swipeList.add(bean);
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
		String lateQuery = "SELECT LATE_REG_ID,EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME ,TO_CHAR(LATE_REG_APPLICATION_DATE,'DD-MM-YYYY'),LATE_REG_STATUS FROM HRMS_LATE_REG_HDR "
				+ "	  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LATE_REG_HDR.LATE_REG_EMP_ID) "
				+ "	  WHERE LATE_REG_STATUS='"
				+ status
				+ "' AND HRMS_LATE_REG_HDR.LATE_REG_EMP_ID="
				+ empCode
				+ " ORDER BY LATE_REG_ID DESC";
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
				bean.setLateAppCode(String.valueOf(lateData[i][0]));
				regList.add(bean);
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
		String redreQuery = "SELECT REDRESSAL_ID,EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME ,TO_CHAR(REDRESSAL_APPLICATION_DATE,'DD-MM-YYYY'),REDRESSAL_STATUS FROM HRMS_REDRESSAL_HDR "
				+ "	  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REDRESSAL_HDR.REDRESSAL_EMP_ID) "
				+ "	  WHERE REDRESSAL_STATUS='"
				+ status
				+ "' AND HRMS_REDRESSAL_HDR.REDRESSAL_EMP_ID="
				+ empCode
				+ " ORDER BY REDRESSAL_ID DESC";
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
				bean.setRedressalAppCode(String.valueOf(redreData[i][0]));
				list.add(bean);
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
		String ptQuery = "SELECT PT_REG_ID,EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME ,TO_CHAR(PT_REG_APPLICATION_DATE,'DD-MM-YYYY'),PT_REG_STATUS ,'PT' FROM HRMS_PT_REG_HDR "
				+ "	  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PT_REG_HDR.PT_REG_EMP_ID) "
				+ "	  WHERE PT_REG_STATUS='"
				+ status
				+ "' AND HRMS_PT_REG_HDR.PT_REG_EMP_ID="
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
				ptList.add(bean);
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

	public void setApproverNameComments(Regularization regularization,
			String query) {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * METHOD FOR PERSONAL TIME
	 * 
	 * @param regularization
	 * @param empCode
	 */
	public String personalTIme(Regularization regularization, String empCode) {
		regularization.setCondTrueFlag("false");
		regularization.setCountValue("0");
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

		/**
		 * CHECK CONDITION FROM SHIFT TABLE
		 */

		String qq = "SELECT NVL(SFT_PT_ISENABLED,'N'),NVL(TO_CHAR(SFT_PT_DURATION,'HH24:MI'),0) ,SFT_PT_PERIODICITY ,TO_CHAR(SFT_START_TIME,'HH24:MI'),TO_CHAR(SFT_END_TIME,'HH24:MI') FROM HRMS_SHIFT where SHIFT_ID="
				+ regularization.getShiftCode();
		Object[][] shiftCode = getSqlModel().getSingleResult(qq);
		String mess = "";
		String PTime = "";
		if (shiftCode != null && shiftCode.length > 0) {
			// Based on hours of late reporting
			if (String.valueOf(shiftCode[0][0]).equals("N")) {
				mess = "For this employee personal time not allowed";
			}
			PTime = String.valueOf(shiftCode[0][1]);
			regularization.setShiftStartTime(String.valueOf(shiftCode[0][3]));
			regularization.setShiftEndTime(String.valueOf(shiftCode[0][4]));
			// regularization.setPersonalTimeHH_MI(PTime);
		}

		int PTMinuts = getMinute(PTime);
		// regularization.setPersonalTime(""+PTMinuts);
		// regularization.setDifferencePT(String.valueOf(PTMinuts));
		regularization.setPtType(String.valueOf(shiftCode[0][2]));
		return mess;
	}

	// callPersonalTime
	public void callPersonalTime(Regularization regularization,
			String[] ptDateItt, String[] ptFromTimeItt, String[] ptToTimeItt) {
		regularization.setPtList(null);
		ArrayList list = new ArrayList();
		if (ptDateItt != null && ptDateItt.length > 0) {

			for (int i = 0; i < ptDateItt.length; i++) {
				Regularization bean = new Regularization();
				bean.setPtDateItt(ptDateItt[i]);
				bean.setPtFromTimeItt(ptFromTimeItt[i]);
				bean.setPtToTimeItt(ptToTimeItt[i]);
				int diff = getMinute(ptToTimeItt[i])
						- getMinute(ptFromTimeItt[i]);
				bean.setDifference(getHH_MM(diff));
				list.add(bean);
			}
			regularization.setPtList(list);
		}

	}

	public void addPersonalTime(Regularization regularization,
			String[] ptDateItt, String[] ptFromTimeItt, String[] ptToTimeItt,
			String add, String remove, String edit) {
		int editSr = 0;
		try {
			editSr = Integer.parseInt(regularization.getHiddenEdit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		regularization.setHiddenEdit("");
		regularization.setPtList(null);
		ArrayList list = new ArrayList();
		if (ptDateItt != null && ptDateItt.length > 0) {
			// REMOVE RECORD
			if (remove.equals("remove")) {
				for (int i = 0; i < ptDateItt.length; i++) {
					Regularization bean = new Regularization();
					if (i != (editSr)) {
						bean.setPtDateItt(ptDateItt[i]);
						bean.setPtFromTimeItt(ptFromTimeItt[i]);
						bean.setPtToTimeItt(ptToTimeItt[i]);
						int diff = getMinute(ptToTimeItt[i])
								- getMinute(ptFromTimeItt[i]);
						bean.setDifference(getHH_MM(diff));
						list.add(bean);
					}
				}
			}
			// EDIT RECORD
			if (edit.equals("edit")) {
				for (int i = 0; i < ptDateItt.length; i++) {
					Regularization bean = new Regularization();
					if (i == (editSr)) {
						bean.setPtDateItt(regularization.getPtDate());
						bean.setPtFromTimeItt(regularization.getPtFromTime());
						bean.setPtToTimeItt(regularization.getPtToTime());
						int diff = getMinute(regularization.getPtToTime())
								- getMinute(regularization.getPtFromTime());
						bean.setDifference(getHH_MM(diff));
						list.add(bean);
					} else {
						bean.setPtDateItt(ptDateItt[i]);
						bean.setPtFromTimeItt(ptFromTimeItt[i]);
						bean.setPtToTimeItt(ptToTimeItt[i]);
						int diff = getMinute(ptToTimeItt[i])
								- getMinute(ptFromTimeItt[i]);
						bean.setDifference(getHH_MM(diff));
						list.add(bean);
					}
				}
			}
			if (edit.equals("") && remove.equals("")) {
				for (int i = 0; i < ptDateItt.length; i++) {
					Regularization bean = new Regularization();
					bean.setPtDateItt(ptDateItt[i]);
					bean.setPtFromTimeItt(ptFromTimeItt[i]);
					bean.setPtToTimeItt(ptToTimeItt[i]);
					int diff = getMinute(ptToTimeItt[i])
							- getMinute(ptFromTimeItt[i]);
					bean.setDifference(getHH_MM(diff));
					list.add(bean);
				}
			}

		}
		/**
		 * FOR ADDING NEW RECORD
		 */
		if (add.equals("add")) {
			Regularization bean = new Regularization();
			bean.setPtDateItt(regularization.getPtDate());
			bean.setPtFromTimeItt(regularization.getPtFromTime());
			bean.setPtToTimeItt(regularization.getPtToTime());
			int diff = getMinute(regularization.getPtToTime())
					- getMinute(regularization.getPtFromTime());
			bean.setDifference(getHH_MM(diff));
			list.add(bean);
		}
		regularization.setPtList(list);
	}

	/**
	 * added by Lakkichand
	 * 
	 * @param date
	 * @return start and end date of the week
	 */
	public String[] getStartEndOfWeek(String date) {

		String[] dateStartEnd = new String[2];
		Calendar c = Calendar.getInstance();
		c.setTime(Utility.getDate(date));
		int day = c.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DATE, -(day - 1));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		logger.info(" START  week    :" + sdf.format(c.getTime()));
		dateStartEnd[0] = sdf.format(c.getTime());
		c.add(Calendar.DATE, 6);
		logger.info(" END  week    :" + sdf.format(c.getTime()));
		dateStartEnd[1] = sdf.format(c.getTime());
		return dateStartEnd;
	}

	/**
	 * added by Lakkichand
	 * 
	 * @param date
	 * @return start and end date of the month
	 */
	public String[] getStartEndOfMonth(String date) {
		String[] dateStartEnd = new String[2];
		Calendar c = Calendar.getInstance();
		c.setTime(Utility.getDate(date));
		int maxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		String month = date.substring(3, 5);
		String year = date.substring(6, 10);
		c.setTime(Utility.getDate("01-" + month + "-" + year));
		dateStartEnd[0] = "01-" + month + "-" + year;
		c.add(Calendar.DATE, maxDays - 1);
		logger.info(" START  month    :" + dateStartEnd[0]);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dateStartEnd[1] = sdf.format(c.getTime());
		logger.info(" End month      :" + dateStartEnd[1]);
		return dateStartEnd;
	}

	public String getPTBalance(Regularization regularization, String[] dateItt,
			String[] fromTime, String[] toTime) {

		int usePT = 0;
		int availablePT = 0;
		String qq = "SELECT NVL(SFT_PT_ISENABLED,'N'),NVL(TO_CHAR(SFT_PT_DURATION,'HH24:MI'),0) ,SFT_PT_PERIODICITY FROM HRMS_SHIFT where SHIFT_ID="
				+ regularization.getShiftCode();
		Object[][] shiftCode = getSqlModel().getSingleResult(qq);
		String PTime = "0";
		int PTdataBase = 0;
		if (shiftCode != null && shiftCode.length > 0) {
			// Based on hours of late reporting
			if (String.valueOf(shiftCode[0][0]).equals("Y")) {
				PTime = String.valueOf(shiftCode[0][1]);
			}
		}

		PTdataBase = getMinute(PTime);

		if (regularization.getPtType().equals("D")) {
			String dd = regularization.getPtDate();
			String qqqqq = "SELECT NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_TO_TIME, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_TO_TIME, 'HH24:MI'), 4, 5)), 0) -( "
					+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_FROM_TIME, 'HH24:MI'), 1, 2)), 0) * 60 + "
					+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_FROM_TIME, 'HH24:MI'), 4, 5)), 0))AS TOTAL "
					+ "		FROM HRMS_PT_REG_DTL "
					+ " INNER JOIN HRMS_PT_REG_HDR ON (HRMS_PT_REG_HDR.PT_REG_ID=HRMS_PT_REG_DTL.PT_REG_ID AND PT_REG_EMP_ID="
					+ regularization.getUserEmpId()
					+ ")  "
					+ "   WHERE PT_REG_DTL_STATUS NOT IN('R') AND	 TO_CHAR(PT_REG_DTL_DATE,'DD-MM-YYYY') ='"
					+ dd + "' ";

			if (!regularization.getApplicationCode().equals("")) {
				qqqqq += " AND  HRMS_PT_REG_DTL.PT_REG_ID NOT IN("
						+ regularization.getApplicationCode() + ")";
			}

			Object[][] data = getSqlModel().getSingleResult(qqqqq);
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					usePT += Integer.parseInt(String.valueOf(data[i][0]));
				}
			}

		} else if (regularization.getPtType().equals("W")) {
			String dd = "";
			dd = regularization.getPtDate();
			if (regularization.getPtDate().equals("")) {
				dd = regularization.getHiddenDate();
			}
			try {
				String date[] = getStartEndOfWeek(dd.trim());
				String qqqqq = "SELECT NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_TO_TIME, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_TO_TIME, 'HH24:MI'), 4, 5)), 0) -( "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_FROM_TIME, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_FROM_TIME, 'HH24:MI'), 4, 5)), 0))AS TOTAL "
						+ "		FROM HRMS_PT_REG_DTL "
						+ " INNER JOIN HRMS_PT_REG_HDR ON (HRMS_PT_REG_HDR.PT_REG_ID=HRMS_PT_REG_DTL.PT_REG_ID AND PT_REG_EMP_ID="
						+ regularization.getUserEmpId()
						+ ")  "
						+ "   WHERE PT_REG_DTL_STATUS NOT IN('R') AND	 PT_REG_DTL_DATE >=TO_DATE('"
						+ date[0]
						+ "','DD-MM-YYYY') "
						+ "		AND PT_REG_DTL_DATE<=TO_DATE('"
						+ date[1]
						+ "','DD-MM-YYYY')";

				if (!regularization.getApplicationCode().equals("")) {
					qqqqq += " AND  HRMS_PT_REG_DTL.PT_REG_ID NOT IN("
							+ regularization.getApplicationCode() + ")";
				}

				Object[][] data = getSqlModel().getSingleResult(qqqqq);
				if (data != null && data.length > 0) {
					for (int i = 0; i < data.length; i++) {
						usePT += Integer.parseInt(String.valueOf(data[i][0]));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String dd = "";
			dd = regularization.getPtDate();
			try {
				String date[] = getStartEndOfMonth(dd.trim());
				String qqq = "SELECT NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_TO_TIME, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_TO_TIME, 'HH24:MI'), 4, 5)), 0) -( "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_FROM_TIME, 'HH24:MI'), 1, 2)), 0) * 60 + "
						+ "		NVL(TO_NUMBER(SUBSTR(TO_CHAR(PT_REG_DTL_FROM_TIME, 'HH24:MI'), 4, 5)), 0))AS TOTAL "
						+ "		FROM HRMS_PT_REG_DTL "
						+ " INNER JOIN HRMS_PT_REG_HDR ON (HRMS_PT_REG_HDR.PT_REG_ID=HRMS_PT_REG_DTL.PT_REG_ID AND PT_REG_EMP_ID="
						+ regularization.getUserEmpId()
						+ ")  "
						+ " WHERE	PT_REG_DTL_STATUS NOT IN('R') AND	 PT_REG_DTL_DATE >=TO_DATE('"
						+ date[0]
						+ "','DD-MM-YYYY') "
						+ "		AND PT_REG_DTL_DATE<=TO_DATE('"
						+ date[1]
						+ "','DD-MM-YYYY')";
				if (!regularization.getApplicationCode().equals("")) {
					qqq += " AND  HRMS_PT_REG_DTL.PT_REG_ID NOT IN("
							+ regularization.getApplicationCode() + ")";
				}

				Object[][] data = getSqlModel().getSingleResult(qqq);
				if (data != null && data.length > 0) {
					for (int i = 0; i < data.length; i++) {
						usePT += Integer.parseInt(String.valueOf(data[i][0]));
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * if(!regularization.getPersonalTime().equals(""))
		 * pt=Integer.parseInt(regularization.getPersonalTime());
		 */
		regularization.setHiddenDate("");
		/**
		 * CHECK ITT DATE
		 */

		int editSr = 9999;
		try {
			editSr = Integer.parseInt(regularization.getHiddenEdit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Utility utiliy = new Utility();
		int ittUse = 0;

		if (dateItt != null && dateItt.length > 0) {
			String ODate = regularization.getPtDate();
			for (int i = 0; i < dateItt.length; i++) {
				if (i != editSr) {
					if (regularization.getPtType().equals("W")) {
						String date[] = getStartEndOfWeek(ODate.trim());

						int chkDate1 = utiliy.checkDate(dateItt[i], date[0]);
						int chkDate2 = utiliy.checkDate(dateItt[i], date[1]);

						if ((chkDate1 == 1 || chkDate1 == 0)
								&& (chkDate2 == -1 || chkDate2 == 0)) {
							ittUse += (getMinute(toTime[i]) - getMinute(fromTime[i]));
						}
					} else if (regularization.getPtType().equals("M")) {
						String date[] = getStartEndOfMonth(ODate.trim());
						int chkDate1 = utiliy.checkDate(dateItt[i], date[0]);
						int chkDate2 = utiliy.checkDate(dateItt[i], date[1]);
						if ((chkDate1 == 1 || chkDate1 == 0)
								&& (chkDate2 == -1 || chkDate2 == 0)) {
							ittUse += (getMinute(toTime[i]) - getMinute(fromTime[i]));
						}
					} else if (regularization.getPtType().equals("D")) {
						int chkDate1 = utiliy.checkDate(dateItt[i], ODate);
						if (chkDate1 == 0)
							ittUse += (getMinute(toTime[i]) - getMinute(fromTime[i]));
					}
				}
			}
		}

		usePT = usePT + ittUse;
		if (PTdataBase > usePT) {
			availablePT = PTdataBase - usePT;
		} else {
			availablePT = 0;
		}
		regularization.setDifferencePT(String.valueOf(availablePT));
		regularization.setPersonalTimeHH_MI(getHH_MM(availablePT));
		// regularization.setHiddenEdit("");
		return regularization.getPersonalTimeHH_MI();
	}

	public boolean checkPreviousApplyForSameDate(Regularization regularization) {

		String frmTime = regularization.getPtFromTime();
		String toTime = regularization.getPtToTime();

		boolean flag = false;
		String query = " SELECT to_char(PT_REG_DTL_FROM_TIME,'HH24:MI'),to_char(PT_REG_DTL_TO_TIME,'HH24:MI') "
				+ "		FROM HRMS_PT_REG_DTL  "
				+ "	 INNER JOIN HRMS_PT_REG_HDR ON (HRMS_PT_REG_HDR.PT_REG_ID=HRMS_PT_REG_DTL.PT_REG_ID AND PT_REG_EMP_ID="
				+ regularization.getUserEmpId()
				+ ") "
				+ "	 WHERE to_char(PT_REG_DTL_DATE,'DD-MM-YYYY')='"
				+ regularization.getPtDate()
				+ "'	AND ( "
				+ "	 (  "
				+ "	  to_char(PT_REG_DTL_FROM_TIME,'HH24:MI') = '"
				+ frmTime
				+ "'  "
				+ "	 AND to_char(PT_REG_DTL_TO_TIME,'HH24:MI') = '"
				+ toTime
				+ "'  "
				+ "	 ) OR   "
				+ "	 (  "
				+ "	   to_char(PT_REG_DTL_FROM_TIME,'HH24:MI') < '"
				+ frmTime
				+ "'  "
				+ "	  AND to_char(PT_REG_DTL_TO_TIME,'HH24:MI') > '"
				+ frmTime
				+ "' "
				+ "	  )OR   "
				+ "	  (  "
				+ "	    to_char(PT_REG_DTL_FROM_TIME,'HH24:MI') < '"
				+ toTime
				+ "'  "
				+ "	  AND to_char(PT_REG_DTL_TO_TIME,'HH24:MI') > '"
				+ toTime
				+ "'  "
				+ "	  )OR "
				+ "	  (  "
				+ "	    to_char(PT_REG_DTL_FROM_TIME,'HH24:MI') > '"
				+ frmTime
				+ "' "
				+ "	  AND to_char(PT_REG_DTL_TO_TIME,'HH24:MI') < '"
				+ toTime + "'   " + "	  ) "

				+ "	  )";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			regularization.setDataBaseFromTime(String.valueOf(data[0][0]));
			regularization.setDataBaseToTime(String.valueOf(data[0][1]));
			flag = true;
		}
		return flag;
	}

	public void setSwipeApplication(Regularization regularization,
			String applicationCode, String status, String[] inSwipe,
			String[] outSwipe) {
		String query = "SELECT TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(SWIPE_REG_DTL_FROM_TIME,'HH24:MI') "
				+ "  ,TO_CHAR(SWIPE_REG_DTL_TO_TIME,'HH24:MI'),TO_CHAR(SWIPE_REG_DTL_ACT_INTIME,'HH24:MI') "
				+ "  ,TO_CHAR(SWIPE_REG_DTL_ACT_OUTTIME,'HH24:MI') "
				+ "  FROM HRMS_SWIPE_REG_DTL  "
				+ "  WHERE SWIPE_REG_ID="
				+ applicationCode
				+ " AND SWIPE_REG_DTL_STATUS='"
				+ status
				+ "'";

		String selectDate = "'00-00-0000',";
		if (inSwipe != null && inSwipe.length > 0) {
			for (int i = 0; i < inSwipe.length; i++) {
				selectDate += " '" + String.valueOf(inSwipe[i]) + "' ,";
			}
			selectDate = selectDate.substring(0, selectDate.length() - 1);
		} else {
			selectDate = "'00-00-0000'";
		}
		String removeDate = "'00-00-0000',";
		if (outSwipe != null && outSwipe.length > 0) {
			for (int i = 0; i < outSwipe.length; i++) {
				removeDate += " '" + String.valueOf(outSwipe[i]) + "' ,";
			}
			removeDate = removeDate.substring(0, removeDate.length() - 1);
		} else {
			removeDate = "'00-00-0000'";
		}
		if (!selectDate.equals("")) {
			query += " AND   TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY') IN("
					+ selectDate + ")  ";
		}
		if (!removeDate.equals("")) {
			query += " AND   TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY') NOT IN("
					+ removeDate + ")  ";
		}

		Object[][] data = getSqlModel().getSingleResult(query);
		regularization.setSwipeList(null);
		if (data != null && data.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				Regularization bean = new Regularization();
				bean.setDate(String.valueOf(data[i][0]));
				bean.setShiftTime(String.valueOf(data[i][1]));// intime
				bean.setInTime(String.valueOf(data[i][2]));// outtime
				bean.setFromTime(String.valueOf(data[i][3]));
				bean.setToTime(checkNull(String.valueOf(data[i][4])));
				// bean.setTotalDiff("");
				list.add(bean);
			}
			regularization.setSwipeList(list);
		}

	}

	public void setLateRegularizationApplication(Regularization regularization,
			String applicationCode, String status, String[] backDate,
			String[] backDelDate) {
		String query = "SELECT TO_CHAR(LATE_REG_DTL_DATE,'DD-MM-YYYY'),TO_CHAR(LATE_REG_DTL_SFTTIME,'HH24:MI'),TO_CHAR(LATE_REG_DTL_INTIME,'HH24:MI') "
				+ "	,TRUNC(MOD( ( TO_DATE(TO_char(LATE_REG_DTL_INTIME, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(LATE_REG_DTL_SFTTIME, 'HH24.MI'),'HH24.MI'))*24,24)) "
				+ "	 || ' : ' ||  "
				+ "	 TRUNC(MOD( ( TO_DATE(TO_char(LATE_REG_DTL_INTIME, 'HH24.MI'),'HH24.MI') - TO_DATE(TO_char(LATE_REG_DTL_SFTTIME, "
				+ "	'HH24.MI'),'HH24.MI'))*24*60,60)) || '' AS DIFFERENCE,TO_CHAR(LATE_REG_DTL_HRS_DED,'HH24:MI'),HRMS_LEAVE.LEAVE_ABBR ,HRMS_LEAVE.LEAVE_ID "
				+ "	,LATE_REG_ID FROM HRMS_LATE_REG_DTL "
				+ "	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LATE_REG_DTL.LATE_REG_DTL_LEAVE_ID) "
				+ "	WHERE LATE_REG_ID="
				+ applicationCode
				+ " AND LATE_REG_DTL_STATUS='" + status + "' ";

		String selectDate = "'00-00-0000',";
		if (backDate != null && backDate.length > 0) {
			for (int i = 0; i < backDate.length; i++) {
				selectDate += " '" + String.valueOf(backDate[i]) + "' ,";
			}
			selectDate = selectDate.substring(0, selectDate.length() - 1);
		} else {
			selectDate = "'00-00-0000'";
		}
		String removeDate = "'00-00-0000',";
		if (backDelDate != null && backDelDate.length > 0) {
			for (int i = 0; i < backDelDate.length; i++) {
				removeDate += " '" + String.valueOf(backDelDate[i]) + "' ,";
			}
			removeDate = removeDate.substring(0, removeDate.length() - 1);
		} else {
			removeDate = "'00-00-0000'";
		}
		if (!selectDate.equals("")) {
			query += " AND   TO_CHAR(LATE_REG_DTL_DATE,'DD-MM-YYYY') IN("
					+ selectDate + ")  ";
		}
		if (!removeDate.equals("")) {
			query += " AND   TO_CHAR(LATE_REG_DTL_DATE,'DD-MM-YYYY') NOT IN("
					+ removeDate + ")  ";
		}

		query += " ORDER BY LATE_REG_ID ";
		Object[][] data = getSqlModel().getSingleResult(query);
		regularization.setRegList(null);
		ArrayList regList = new ArrayList();
		regularization.setListFlag("true");
		regularization.setLateFlag("true");
		for (int i = 0; i < data.length; i++) {
			Regularization bean = new Regularization();
			bean.setDate(String.valueOf(data[i][0]));
			bean.setShiftTime(String.valueOf(data[i][1]));
			bean.setInTime(String.valueOf(data[i][2]));
			bean.setLateHrs(getHH_MM(String.valueOf(data[i][3])));
			bean.setLateHrsDeduct(getHH_MM(String.valueOf(data[i][4])));
			bean.setLateHrsDeductFrom(String.valueOf(data[i][5]));
			bean.setLateHrsDeductFromCode(String.valueOf(data[i][6]));
			regList.add(bean);
		}
		regularization.setRegList(regList);
	}

	// Added by manish sakpal Begins
	/**
	 * @method : checkForAttendanceRecors
	 * @purpose : To check whether the attendance for all the days of given
	 *          month and year in database OR not If it is available then don't
	 *          not insert record for that days. else insert records with
	 *          status1 and status2 as absent
	 * @param :
	 *            regularization,month,year,empCode
	 */
	public void checkForAttendanceRecors(Regularization regularization,
			String month, String year, String empCode) {
		try {
			Object[][] getRecordFromDailyAttendanceObj = getEmployeeDetails(
					empCode, year, month);
			String currentDateQuery = "SELECT TO_CHAR(SYSDATE,'dd'),TO_CHAR(SYSDATE,'mm'),TO_CHAR(SYSDATE,'yyyy') FROM DUAL";
			Object[][] currentDateObj = getSqlModel().getSingleResult(
					currentDateQuery);
			int enteredMonth = Integer.parseInt(month);
			int enteredYear = Integer.parseInt(year);
			int currentDay = Integer.parseInt(String
					.valueOf(currentDateObj[0][0]));
			int currentMonth = Integer.parseInt(String
					.valueOf(currentDateObj[0][1]));
			int currentYear = Integer.parseInt(String
					.valueOf(currentDateObj[0][2]));

			Calendar cal = Calendar.getInstance();
			cal.setTime(Utility.getDate(01 + "-" + getproperValue(month) + "-"
					+ year));

			if (getRecordFromDailyAttendanceObj != null
					&& getRecordFromDailyAttendanceObj.length > 0) {
				String empoyee_ID = checkNull(String
						.valueOf(getRecordFromDailyAttendanceObj[0][0]));
				String shift_ID = checkNull(String
						.valueOf(getRecordFromDailyAttendanceObj[0][1]));
				String division_ID = checkNull(String
						.valueOf(getRecordFromDailyAttendanceObj[0][2]));
				String branch_ID = checkNull(String
						.valueOf(getRecordFromDailyAttendanceObj[0][3]));
				// If entered month is equal to current month then insert
				// records up-to current day
				// in daily attendance table except for the available records,
				// weekly-off and holidays.
				if ((enteredMonth == currentMonth)
						&& (enteredYear == currentYear)) {
					insertAbsentDaysRecordInAttendance(currentDay, month, year,
							empoyee_ID, shift_ID, division_ID, branch_ID,
							empCode);
				}

				// If entered month is less than current month then insert
				// records for all the days in daily attendance table except for
				// the available records,
				// weekly-off and holidays.
				if (enteredMonth < currentMonth) {
					int totalDaysOfMonth = cal
							.getActualMaximum(cal.DAY_OF_MONTH);
					insertAbsentDaysRecordInAttendance(totalDaysOfMonth, month,
							year, empoyee_ID, shift_ID, division_ID, branch_ID,
							empCode);
				}

				if ((enteredMonth > currentMonth)
						&& (enteredYear < currentYear)) {
					int totalDaysOfMonth = cal
							.getActualMaximum(cal.DAY_OF_MONTH);
					insertAbsentDaysRecordInAttendance(totalDaysOfMonth, month,
							year, empoyee_ID, shift_ID, division_ID, branch_ID,
							empCode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Object[][] getAttendanceSettingObj() {
		Object[][] getAttendanceSettingObj = null;
		try {
			String query = " select CONF_WEEKLYOF_HOLI_REGULARIZE from HRMS_ATTENDANCE_CONF ";
			getAttendanceSettingObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAttendanceSettingObj;
	}

	private Object[][] getEmployeeDetails(String empCode, String year,
			String month) {
		Object[][] empDataObj = null;
		try {
			String getRecordFromDailyAttendanceQuery = "SELECT ATT_EMP_ID, ATT_SHIFT_ID, EMP_DIV, EMP_CENTER FROM HRMS_DAILY_ATTENDANCE_"
					+ year
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_"
					+ year
					+ ".ATT_EMP_ID)  WHERE ATT_EMP_ID IN("
					+ empCode
					+ ") AND TO_CHAR(ATT_DATE,'MM-YYYY')= '"
					+ getproperValue(month)
					+ "-"
					+ year
					+ "' ORDER BY ATT_DATE DESC ";
			Object[][] getRecordFromDailyAttendanceObj = getSqlModel()
					.getSingleResult(getRecordFromDailyAttendanceQuery);
			if (getRecordFromDailyAttendanceObj != null
					&& getRecordFromDailyAttendanceObj.length > 0) {
				empDataObj = getRecordFromDailyAttendanceObj;
			}
			String empDetailQuery = "SELECT EMP_ID, EMP_SHIFT, EMP_DIV, EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "
					+ empCode;
			Object[][] empDetailObj = getSqlModel().getSingleResult(
					empDetailQuery);
			if (empDetailObj != null && empDetailObj.length > 0) {
				empDataObj = empDetailObj;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return empDataObj;
	}

	private void insertAbsentDaysRecordInAttendance(int loopLength,
			String month, String year, String empoyee_ID, String shift_ID,
			String division_ID, String branch_ID, String empCode) {
		if (loopLength > 0) {

			for (int i = 0; i < loopLength; i++) {

				String dateToInsert = new String(getproperValue((String
						.valueOf(i + 1)))
						+ "-" + getproperValue(month) + "-" + year);

				Object[][] insertData = new Object[1][13];
				insertData[0][0] = dateToInsert; // Attendance
				// date
				insertData[0][1] = empCode; // Employee ID
				insertData[0][2] = shift_ID; // Shift_ID
				insertData[0][3] = "00:00:00"; // Log-in Time
				insertData[0][4] = "00:00:00"; // Log-out Time
				insertData[0][5] = "00:00:00"; // Working hours
				insertData[0][6] = "00:00:00"; // Extra hours
				insertData[0][7] = "00:00:00"; // Late hours
				insertData[0][8] = "00:00:00"; // Early hours
				insertData[0][9] = division_ID; // Division
				insertData[0][10] = branch_ID; // Branch
				insertData[0][11] = "AB"; // Status One
				insertData[0][12] = "AB"; // Status Two

				if (isAttendanceAvailableForThisDate(empCode, year,
						dateToInsert)) {

				}

				else if (isWeeklyOff(shift_ID, dateToInsert)) {

				} else if (isHoliday(empoyee_ID, dateToInsert)) {

				} else {
					String insertQuery = "INSERT INTO HRMS_DAILY_ATTENDANCE_"
							+ year
							+ " ( ATT_DATE, ATT_EMP_ID, ATT_SHIFT_ID, ATT_LOGIN, ATT_LOGOUT, "
							+ " ATT_WORKING_HRS, ATT_EXTRAHRS, ATT_LATE_HRS, ATT_EARLY_HRS, ATT_DIVISION, ATT_BRANCH, "
							+ " ATT_STATUS_ONE, ATT_STATUS_TWO ) "
							+ " VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), "
							+ " TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), TO_DATE(?, 'HH24:MI:SS'), ?, ?, "
							+ " ?, ?) ";
					getSqlModel().singleExecute(insertQuery, insertData);
				}
			}
		}
	}

	/**
	 * @method : getproperValue
	 * @purpose : append "0" in-front of the months below 10 (i.e. before
	 *          october)
	 * @param :
	 *            value
	 * @return : String
	 */
	public String getproperValue(String value) {
		String properValue = "0";
		if (Integer.parseInt(value) < 10) {
			properValue += value;
		} else {
			properValue = value;
		}
		return properValue;
	}

	/**
	 * @method : isAttendanceAvailableForThisDate
	 * @purpose : To check whether the attendance given month and year is
	 *          present in database OR not If it is available then return true
	 *          else return false
	 * @param :
	 *            empCode,year,dateToInsert
	 * @return : boolean
	 */
	private boolean isAttendanceAvailableForThisDate(String empCode,
			String year, String dateToInsert) {
		boolean result = false;
		try {
			String getAttendanceQuery = "SELECT * FROM HRMS_DAILY_ATTENDANCE_"
					+ year + " WHERE ATT_EMP_ID IN(" + empCode + ") "
					+ " AND TO_CHAR(ATT_DATE,'DD-MM-YYYY')= '" + dateToInsert
					+ "' ";

			Object[][] getAttendanceObj = getSqlModel().getSingleResult(
					getAttendanceQuery);
			if (getAttendanceObj != null && getAttendanceObj.length > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Result : " + result);
		return result;
	}

	/**
	 * @method : isHoliday
	 * @purpose : to check whether the particular date is Holiday or not
	 * @param :
	 *            empId, dateToInsert
	 * @return : boolean
	 */
	private boolean isHoliday(String empId, String dateToInsert) {
		boolean result = false;
		try {
			Object[][] getAttendanceSettingDataObj = getAttendanceSettingObj();
			String query = " SELECT CONF_BRANCH_HOLI_FLAG FROM HRMS_ATTENDANCE_CONF ";
			Object[][] branchFlag = getSqlModel().getSingleResult(query);
			Object[][] holiDay = null;
			String holidaySql = "";
			if (branchFlag != null && branchFlag.length > 0
					&& String.valueOf(branchFlag[0][0]).equals("Y")) {
				Object[] branchHolidayDate = new Object[3];
				branchHolidayDate[0] = dateToInsert; // holiday date
				branchHolidayDate[1] = empId; // employee code
				branchHolidayDate[2] = dateToInsert; // holiday date

				holidaySql = " SELECT CASE WHEN HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') THEN 'TRUE' ELSE 'FALSE' END "
						+ " FROM HRMS_HOLIDAY_BRANCH WHERE CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = ?) AND "
						+ " HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY')";
				holiDay = getSqlModel().getSingleResult(holidaySql,
						branchHolidayDate);
			} else {
				// retrieve holiday details from HRMS_HOLIDAY table
				Object[] holidayDate = new Object[2];
				holidayDate[0] = dateToInsert; // holiday date
				holidayDate[1] = dateToInsert; // holiday date

				holidaySql = " SELECT CASE WHEN HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') THEN 'TRUE' ELSE 'FALSE' END FROM HRMS_HOLIDAY "
						+ " WHERE HOLI_DATE = TO_DATE(?, 'DD-MM-YYYY') ";
				holiDay = getSqlModel()
						.getSingleResult(holidaySql, holidayDate);
			}
			if (holiDay != null && holiDay.length != 0
					&& String.valueOf(holiDay[0][0]).equalsIgnoreCase("true")) {

				if (getAttendanceSettingDataObj != null
						&& getAttendanceSettingDataObj.length > 0) {
					if (String.valueOf(getAttendanceSettingDataObj[0][0])
							.equals("Y")) {
						result = false;
					} else {
						result = true;
					}
				}
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private boolean isWeeklyOff(String shiftId, String dateToInsert) {
		boolean result = false;
		// retrieve the weekly off details from HRMS_SHIFT table for given date
		try {
			Object[][] getAttendanceSettingDataObj = getAttendanceSettingObj();

			String weekOffSql = " SELECT SHIFT_WEEK_1, SHIFT_WEEK_2, SHIFT_WEEK_3, SHIFT_WEEK_4, SHIFT_WEEK_5, SHIFT_WEEK_6 "
					+ " FROM HRMS_SHIFT WHERE SHIFT_ID = ? ";
			Object[][] getWeeklyOffData = getSqlModel().getSingleResult(
					weekOffSql, new Object[] { shiftId });
			// convert date in to calendar date
			Calendar currentDay = new Utility()
					.getCalanderDate(checkNull(dateToInsert));
			// call isWeeklyOff method to check whether given date is weekly off
			// or not
			int count = isWeeklyOff(currentDay, getWeeklyOffData);
			// if isWeeklyOff method return 1, given date is a weekly off
			if (count == 1) {

				if (getAttendanceSettingDataObj != null
						&& getAttendanceSettingDataObj.length > 0) {
					if (String.valueOf(getAttendanceSettingDataObj[0][0])
							.equals("Y")) {
						result = false;
					} else {
						result = true;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("isWeeklyOff                    " + result);
		return result;
	}

	/**
	 * @method : isWeeklyOff
	 * @purpose : To check whether the particular date is weekly off for that
	 *          employee or not
	 * @param :
	 *            currentDay, getWeeklyOffData
	 * @return : int
	 */
	public int isWeeklyOff(Calendar currentDay, Object getWeeklyOffData[][]) {
		int countDays = 0;
		int DAY_OF_WEEK = currentDay.get(java.util.Calendar.WEEK_OF_MONTH); // Day
		// of
		// week
		// of
		// specified
		// date

		String[] weekDays = null;

		for (int i = 0; i < getWeeklyOffData[0].length; i++) {
			if (i + 1 == DAY_OF_WEEK) {
				weekDays = String.valueOf(getWeeklyOffData[0][i]).split(",");
			}
		}

		for (String string : weekDays) {
			int i = 0;
			try {
				i = Integer.parseInt(string.trim());
			} catch (Exception e) {
				logger.error(e);
				i = 0;
			}
			switch (i) {
			case 1:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 1) {
					countDays++;
				}
				break;
			case 2:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 2) {
					countDays++;
				}
				break;
			case 3:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 3) {
					countDays++;
				}
				break;
			case 4:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 4) {
					countDays++;
				}
				break;
			case 5:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 5) {
					countDays++;
				}
				break;
			case 6:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 6) {
					countDays++;
				}
				break;
			case 7:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 7) {
					countDays++;
				}
				break;
			default:
				break;
			} // end of switch
		} // end of for loop
		return countDays;
	}

	// Added by manish sakpal Ends
}
