/**
 * @author Bhushan Dasare
 * @date Feb 11, 2009
 */

package org.paradyne.model.ApplicationStudio;

import java.io.FileInputStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.PPEncrypter;

public class ProcessManagerAlertsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProcessManagerAlertsModel.class);

	public void addProcessAlertAppraisal(String[] empID, String module,
			String[] alertStatus, String applnID, String alertLevel,
			String[] alertSubject, String[] alertMessage) {
		try {
			String addAlertSql = " INSERT INTO HRMS_ALERT_MESSAGE(ALERT_ID, ALERT_SUBJECT, ALERT_EMP_ID, ALERT_MESSAGE, ALERT_DATE, "
					+ " ALERT_MODULE, ALERT_MESSAGE_TYPE, ALERT_APPLICATION_ID, ALERT_LEVEL, ALERT_ACTIVE, ALERT_APPLICANT_ID) "
					+ " VALUES ((SELECT NVL(MAX(ALERT_ID), 0) + 1 FROM HRMS_ALERT_MESSAGE), ?, ?, ?, SYSDATE, ?, ?, ?, ?, 'Y', ?) ";

			String emp = String.valueOf(empID[1]);
			String msgType = "", sub = "", msg = "";
			if (emp.equals("")) {
				emp = String.valueOf(empID[0]);
				msgType = "I";
				sub = alertSubject[0];
				msg = alertMessage[0];
			} else {
				msgType = "A";
				sub = alertSubject[1];
				msg = alertMessage[1];
			}
			Object[][] addAlertObj = new Object[1][8];
			addAlertObj[0][0] = sub;
			addAlertObj[0][1] = emp;
			addAlertObj[0][2] = msg;
			addAlertObj[0][3] = module;
			addAlertObj[0][4] = msgType;
			addAlertObj[0][5] = applnID;
			addAlertObj[0][6] = alertLevel;
			addAlertObj[0][7] = String.valueOf(empID[0]);
			getSqlModel().singleExecute(addAlertSql, addAlertObj);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void getAlert(String alertID, HttpServletRequest request) {
		String module = "", message = "";
		try {
			String messageSql = " SELECT ALERT_MODULE, ALERT_MESSAGE FROM HRMS_ALERT_MESSAGE WHERE ALERT_ID = "
					+ alertID;
			Object[][] messageObj = getSqlModel().getSingleResult(messageSql);
			module = String.valueOf(messageObj[0][0]);
			message = String.valueOf(messageObj[0][1]);
		} catch (Exception e) {
			logger.error(e);
		}
		request.setAttribute("module", module);
		request.setAttribute("message", message);
	}

	public void removeProcessAlert(String applnID, String module) {
		try {
			String removeAlertSql = " UPDATE HRMS_ALERT_MESSAGE SET ALERT_ACTIVE = 'N' WHERE ALERT_APPLICATION_ID = "
					+ applnID + " AND ALERT_MODULE LIKE '" + module + "'";
			getSqlModel().singleExecute(removeAlertSql);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void removeProcessAlertAppraisal(String applnID, String empID, String phaseCode) {
		try {
			/*String removeAlertSql = " UPDATE HRMS_ALERT_MESSAGE SET ALERT_ACTIVE = 'N' WHERE ALERT_APPLICATION_ID = "
					+ applnID
					+ " AND ALERT_APPLICANT_ID = "
					+ empID
					+ " AND ALERT_EMP_ID IN (SELECT NEXT_APPRAISER FROM PAS_APPR_TRACKING "
					+ " WHERE APPR_ID = "
					+ applnID
					+ " AND EMP_ID = "
					+ empID
					+ " AND NEXT_APPRAISER IS NOT NULL) ";*/
			String removeAlertSql = " UPDATE HRMS_ALERT_MESSAGE SET ALERT_ACTIVE = 'N' WHERE ALERT_APPLICATION_ID = "
					+ applnID
					+ " AND ALERT_APPLICANT_ID = "
					+ empID
					+ " AND ALERT_EMP_ID IN (SELECT NEXT_APPRAISER FROM PAS_APPR_TRACKING "
					+ " WHERE APPR_ID = "
					+ applnID
					+ " AND EMP_ID = "
					+ empID
					+ " AND NEXT_PHASE_ID = "+phaseCode+") ";
			getSqlModel().singleExecute(removeAlertSql);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void saveAlert(String subject, String empID, String message,
			String module, String msgType, String applnID, String level) {
		try {
			String addAlertSql = " INSERT INTO HRMS_ALERT_MESSAGE(ALERT_ID, ALERT_SUBJECT, ALERT_EMP_ID, ALERT_MESSAGE, ALERT_DATE, "
					+ " ALERT_MODULE, ALERT_MESSAGE_TYPE, ALERT_APPLICATION_ID, ALERT_LEVEL, ALERT_ACTIVE) "
					+ " VALUES ((SELECT NVL(MAX(ALERT_ID), 0) + 1 FROM HRMS_ALERT_MESSAGE), ?, ?, ?, SYSDATE, ?, ?, ?, ?, 'Y') ";

			Object[][] addAlertObj = new Object[1][7];
			addAlertObj[0][0] = subject;
			addAlertObj[0][1] = empID;
			addAlertObj[0][2] = message;
			addAlertObj[0][3] = module;
			addAlertObj[0][4] = msgType;
			addAlertObj[0][5] = applnID;
			addAlertObj[0][6] = level;

			getSqlModel().singleExecute(addAlertSql, addAlertObj);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void showAlerts(String userEmpID, HttpServletRequest request,
			String path) {
		Properties alertProp;
		FileInputStream alertFin;
		Object[][] alertsObj = null;
		try {
			alertFin = new FileInputStream(path
					+ "\\Alerts\\alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);

			String approvalAlertsSql = " SELECT ALERT_ID, ALERT_SUBJECT, ALERT_EMP_ID, ALERT_MESSAGE, TO_CHAR(ALERT_DATE, 'DD-MM-YYYY'), "
					+ " TRIM(ALERT_MODULE), DECODE(ALERT_MESSAGE_TYPE, 'I', 'Information', 'A', 'Alert') ALERT_MESSAGE_TYPE, ALERT_APPLICATION_ID, "
					+ " ALERT_LEVEL ,ALERT_APPL_PARAMS FROM HRMS_ALERT_MESSAGE WHERE "
					+ " 1=1 "
					+" AND( ALERT_EMP_ID =  "+ userEmpID+ "  OR INSTR(ALERT_DYNAMIC_EMPID, '"+userEmpID+"')>0 ) "  
					+ " AND TO_DATE(sysdate + 1,'dd-mm-yyyy') - (SELECT DECODE(NVL(CONF_LEAVE_ALERT,0),0,30,CONF_LEAVE_ALERT) FROM HRMS_ATTENDANCE_CONF) <= TO_DATE(ALERT_DATE,'dd-mm-yyyy') "
					+ " AND ALERT_MODULE NOT LIKE 'Appraisal' AND ALERT_ACTIVE='Y' AND ALERT_LINK IS  NULL ORDER BY ALERT_DATE DESC ";

			Object[][] processAlertsObj = getSqlModel().getSingleResult(
					approvalAlertsSql);

			if (processAlertsObj != null && processAlertsObj.length > 0) {
				alertsObj = new Object[processAlertsObj.length][11];
				for (int i = 0; i < processAlertsObj.length; i++) {
					alertsObj[i][0] = PPEncrypter.encrypt(String
							.valueOf(processAlertsObj[i][0])); // ALERT_ID
					alertsObj[i][1] = String.valueOf(processAlertsObj[i][1]); // ALERT_SUBJECT
					alertsObj[i][2] = String.valueOf(processAlertsObj[i][2]); // ALERT_EMP_ID
					alertsObj[i][3] = String.valueOf(processAlertsObj[i][3]); // ALERT_MESSAGE
					alertsObj[i][4] = String.valueOf(processAlertsObj[i][4]); // ALERT_DATE
					alertsObj[i][5] = String.valueOf(processAlertsObj[i][5]); // ALERT_MODULE
					alertsObj[i][6] = String.valueOf(processAlertsObj[i][6]); // ALERT_MESSAGE_TYPE
					alertsObj[i][7] = String.valueOf(processAlertsObj[i][7]); // ALERT_APPLICATION_ID
					alertsObj[i][8] = String.valueOf(processAlertsObj[i][8]); // ALERT_LEVEL
					String alertModule = String.valueOf(processAlertsObj[i][5])
							.replace(" ", "").toLowerCase(); // ALERT_MODULE
					System.out.println("alertModule--------------"+alertModule);
					alertsObj[i][9] = alertProp.getProperty(alertModule); // ALERT_LINK
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		request.setAttribute("alertsObj", alertsObj);
	}

	public void showAlertsAppraisal(String userEmpID, HttpServletRequest request) {
		Object[][] processAppraisalAlertsObj = null;
		try {
			String alertsSql = " SELECT ALERT_ID, ALERT_SUBJECT, ALERT_EMP_ID, ALERT_MESSAGE, TO_CHAR(ALERT_DATE, 'DD-MM-YYYY'),  "
					+ " ALERT_MODULE, DECODE(ALERT_MESSAGE_TYPE, 'I', 'Information', 'A', 'Alert') ALERT_MESSAGE_TYPE, ALERT_APPLICATION_ID,  "
					+ " ALERT_LEVEL, APPR_PHASE_ID, ALERT_APPLICANT_ID FROM HRMS_ALERT_MESSAGE "
					+ " LEFT JOIN PAS_APPR_TRACKING ON(PAS_APPR_TRACKING.APPR_ID = HRMS_ALERT_MESSAGE.ALERT_APPLICATION_ID "
					+ " AND PAS_APPR_TRACKING.EMP_ID = HRMS_ALERT_MESSAGE.ALERT_APPLICANT_ID AND NEXT_APPRAISER = "
					+ userEmpID
					+ ") "
					+ " LEFT JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_TRACKING.NEXT_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
					+ " WHERE ALERT_EMP_ID = "
					+ userEmpID
					+ " AND SYSDATE - ALERT_DATE <= 30 AND ALERT_ACTIVE = 'Y' AND ALERT_MODULE LIKE 'Appraisal' "
					+ " ORDER BY ALERT_DATE DESC  ";

			/*
			 * String alertsSql = " SELECT ALERT_ID, ALERT_SUBJECT,
			 * ALERT_EMP_ID, ALERT_MESSAGE, TO_CHAR(ALERT_DATE, 'DD-MM-YYYY'), " + "
			 * ALERT_MODULE, DECODE(ALERT_MESSAGE_TYPE, 'I', 'Information', 'A',
			 * 'Alert') ALERT_MESSAGE_TYPE, ALERT_APPLICATION_ID, " + "
			 * ALERT_LEVEL, APPR_PHASE_ID, ALERT_APPLICANT_ID FROM
			 * HRMS_ALERT_MESSAGE " + " LEFT JOIN PAS_APPR_TRACKING
			 * ON(PAS_APPR_TRACKING.APPR_ID =
			 * HRMS_ALERT_MESSAGE.ALERT_APPLICATION_ID " + " AND
			 * PAS_APPR_TRACKING.EMP_ID = HRMS_ALERT_MESSAGE.ALERT_APPLICANT_ID
			 * AND NEXT_APPRAISER = " + userEmpID + ") " + " LEFT JOIN
			 * PAS_APPR_PHASE_CONFIG ON (PAS_APPR_TRACKING.NEXT_PHASE_ID =
			 * PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) " + " WHERE ALERT_EMP_ID = " +
			 * userEmpID + " AND TO_DATE(ALERT_DATE,'DD-MM-YYYY') between
			 * TO_DATE(SYSDATE,'DD-MM-YYYY')-(SELECT CONF_LEAVE_ALERT FROM
			 * HRMS_ATTENDANCE_CONF) AND TO_DATE(SYSDATE,'DD-MM-YYYY') " + " AND
			 * ALERT_ACTIVE = 'Y' AND ALERT_MODULE LIKE 'Appraisal' " + " ORDER
			 * BY ALERT_DATE DESC ";
			 */

			Object[][] alertsObj = getSqlModel().getSingleResult(alertsSql);

			if (alertsObj != null && alertsObj.length > 0) {
				processAppraisalAlertsObj = new Object[alertsObj.length][11];

				for (int i = 0; i < alertsObj.length; i++) {
					processAppraisalAlertsObj[i][0] = String
							.valueOf(alertsObj[i][0]); // ALERT_ID
					processAppraisalAlertsObj[i][1] = String
							.valueOf(alertsObj[i][1]); // ALERT_SUBJECT
					processAppraisalAlertsObj[i][2] = String
							.valueOf(alertsObj[i][2]); // ALERT_EMP_ID
					processAppraisalAlertsObj[i][3] = String
							.valueOf(alertsObj[i][3]); // ALERT_MESSAGE
					processAppraisalAlertsObj[i][4] = String
							.valueOf(alertsObj[i][4]); // ALERT_DATE
					processAppraisalAlertsObj[i][5] = String
							.valueOf(alertsObj[i][5]); // ALERT_MODULE
					processAppraisalAlertsObj[i][6] = String
							.valueOf(alertsObj[i][6]); // ALERT_MESSAGE_TYPE
					processAppraisalAlertsObj[i][7] = String
							.valueOf(alertsObj[i][7]); // ALERT_APPLICATION_ID
					processAppraisalAlertsObj[i][8] = String
							.valueOf(alertsObj[i][8]); // ALERT_LEVEL
					processAppraisalAlertsObj[i][9] = String
							.valueOf(alertsObj[i][9]); // APPR_PHASE_ID
					processAppraisalAlertsObj[i][10] = String
							.valueOf(alertsObj[i][10]); // APPLICANT EMP_ID
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		request.setAttribute("processAppraisalAlertsObj",
				processAppraisalAlertsObj);
	}
}