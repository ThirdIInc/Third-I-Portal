package org.paradyne.model.mypage;

import java.io.FileInputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.mypage.MypageProcessManagerAlerts;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;

/**
 * @Modified by AA1711 
 * @purpose : To show Record of Team Members and 
 * 				System Login with Night and Flexi Shift 
 * 				to display order of Alerts.
 * @Date : 29-Oct-2012
 */
public class MypageProcessManagerAlertsModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MypageProcessManagerAlertsModel.class);

	public void getAlert(String alertID, HttpServletRequest request) {
		String module = "", message = "";
		try {
			String messageSql = " SELECT HRMS_ALERT_MESSAGE.ALERT_MODULE, HRMS_ALERT_MESSAGE.ALERT_MESSAGE FROM HRMS_ALERT_MESSAGE WHERE HRMS_ALERT_MESSAGE.ALERT_ID = "
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

	private static String getDayName(int dayOfWeek) {
		String name = "";
		try {
			switch (dayOfWeek) {
			case 1:
				name = "Sun";
				break;
			case 2:
				name = "Mon";
				break;
			case 3:
				name = "Tue";
				break;
			case 4:
				name = "Wed";
				break;
			case 5:
				name = "Thu";
				break;
			case 6:
				name = "Fri";
				break;
			case 7:
				name = "Sat";
				break;

			default:
				name = "---";
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	/**@Method Name: showAlerts()
	 * This method is used for showing process manager record for mypage.
	 * @param userEmpID :-employee id
	 * @param request:-request
	 * @param path:-alert file path
	 * @param filterStatus
	 * @param bean
	 * @param from
	 */

	public void showAlerts(String userEmpID, HttpServletRequest request,
			String path, String moduleName, String status, String year,
			String searchMessage, String click, String sortOption,
			String source, String filterStatus, MypageProcessManagerAlerts bean) {

		String dateQuery = " SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL ";
		Object currentYearObj[][] = getSqlModel().getSingleResult(dateQuery);
		int previousYear;
		Properties alertProp;
		FileInputStream alertFin;
		Object[][] alertsObj = null;
		try {
			alertFin = new FileInputStream(path
					+ "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			String approvalAlertsSql = " SELECT ALERT_ID,ALERT_SUBJECT,ALERT_EMP_ID,ALERT_MESSAGE,ALERT_DATE,"
					+ " ALERT_MODULE,ALERT_MESSAGE_TYPE,ALERT_APPLICATION_ID,ALERT_LEVEL,ALERT_APPL_PARAMS,"
					+ " ALERT_LINK,NAME,DAY_STATUS,NAME1 "
					+ " FROM (SELECT HRMS_ALERT_MESSAGE.ALERT_ID, HRMS_ALERT_MESSAGE.ALERT_SUBJECT,"
					+ " HRMS_ALERT_MESSAGE.ALERT_EMP_ID, HRMS_ALERT_MESSAGE.ALERT_MESSAGE, "
					+ " TO_CHAR(HRMS_ALERT_MESSAGE.ALERT_DATE, 'DD-MM-YYYY HH24:MI') ALERT_DATE , "
					+ " TRIM(HRMS_ALERT_MESSAGE.ALERT_MODULE) ALERT_MODULE, "
					+ " DECODE(HRMS_ALERT_MESSAGE.ALERT_MESSAGE_TYPE, 'I', 'INFORMATION', 'A', 'ALERT') ALERT_MESSAGE_TYPE,"
					+ " HRMS_ALERT_MESSAGE.ALERT_APPLICATION_ID, HRMS_ALERT_MESSAGE.ALERT_LEVEL ,"
					+ " HRMS_ALERT_MESSAGE.ALERT_APPL_PARAMS,HRMS_ALERT_MESSAGE.ALERT_LINK,"
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||substr(HRMS_EMP_OFFC.EMP_LNAME,0,1) NAME ";

			approvalAlertsSql += ", CASE WHEN TO_CHAR(ALERT_DATE,'DD-MM-YYYY')=TO_CHAR(SYSDATE,'DD-MM-YYYY') THEN 1 WHEN "
					+ "TO_CHAR(ALERT_DATE,'DD-MM-YYYY')=TO_CHAR(SYSDATE-1,'DD-MM-YYYY') THEN 	2 "
					+ "	WHEN TO_CHAR(ALERT_DATE) <SYSDATE-2 AND TO_CHAR(ALERT_DATE) >SYSDATE-7 THEN 3 "
					+ " WHEN TO_CHAR(ALERT_DATE) BETWEEN SYSDATE-8 AND SYSDATE-30  THEN 4 ELSE 5 END DAY_STATUS ";

			approvalAlertsSql += " ,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME1 FROM HRMS_ALERT_MESSAGE  "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =HRMS_ALERT_MESSAGE.ALERT_FROM) "
					+ " WHERE  (ALERT_EMP_ID="+ userEmpID
					+ " OR ALERT_MANAGER_ID="+ userEmpID
					+ " OR ALTER_ALTERNATEID="+ userEmpID
					+ " OR TO_CHAR(','||HRMS_ALERT_MESSAGE.ALERT_CC||',') like '%,"
					+ userEmpID + ",%'" + " )";
			approvalAlertsSql += " AND ALERT_LINK IS NOT NULL AND HRMS_ALERT_MESSAGE.ALERT_MESSAGE_TYPE='A' "
								+ " AND   SYSDATE - HRMS_ALERT_MESSAGE.ALERT_DATE <= 45";

			if (click != null && click.equals("Input")) {
				approvalAlertsSql += " AND HRMS_ALERT_MESSAGE.ALERT_ACTIVE='Y'";
			} else {
				approvalAlertsSql += " AND HRMS_ALERT_MESSAGE.ALERT_ACTIVE IN('Y','N')";
			}
			// approvalAlertsSql += " AND HRMS_ALERT_MESSAGE.ALERT_MODULE NOT
			// LIKE 'Appraisal' ";
			if (moduleName != null && moduleName.equals("--Select--")) {
				moduleName = "";
			}
			if (moduleName != null && !moduleName.equals("")) {
				approvalAlertsSql += " AND UPPER(ALERT_MODULE)='"
						+ moduleName.trim().toUpperCase() + "' ";
			}
			// && !status.equals("") && !status.equals("Inbox")
			// && !status.equals("NotInbox")
			if (filterStatus != null && filterStatus.equals("--Select--")) {
				filterStatus = "";
			}
			if (filterStatus != null && !filterStatus.equals("")) {
				approvalAlertsSql += " AND UPPER(ALERT_STATUS)='"
						+ filterStatus.trim().toUpperCase() + "' ";
			}
			if (status != null && status.equals("Inbox")) {
				approvalAlertsSql += "  AND ALERT_STATUS!='Draft'";
			}
			if (status != null
					&& (status.equals("NotInbox") || source.equals("D"))) {
				approvalAlertsSql += " AND ALERT_STATUS='Draft'";
			}
			/*
			 * if(year==null) {
			 */
			year = String.valueOf(currentYearObj[0][0]);
			// approvalAlertsSql += " AND
			// to_CHAR(HRMS_ALERT_MESSAGE.ALERT_DATE,'yyyy') IN ('"
			// + year + "','"+(Integer.parseInt(year)-1)+"') ";
			if (searchMessage != null && !searchMessage.trim().equals("")) {
				approvalAlertsSql += " AND UPPER(ALERT_SUBJECT||ALERT_MODULE||HRMS_EMP_OFFC.EMP_FNAME||HRMS_EMP_OFFC.EMP_LNAME||TO_CHAR(HRMS_ALERT_MESSAGE.ALERT_DATE, 'DD-MM-YYYY HH24:MI')) LIKE UPPER (TRIM('%"
						+ searchMessage.trim() + "%'))";
			}
			// Add For UNION
			approvalAlertsSql += " UNION ALL SELECT HRMS_ALERT_MESSAGE.ALERT_ID, HRMS_ALERT_MESSAGE.ALERT_SUBJECT, "
					+ " HRMS_ALERT_MESSAGE.ALERT_EMP_ID, HRMS_ALERT_MESSAGE.ALERT_MESSAGE,"
					+ " TO_CHAR(HRMS_ALERT_MESSAGE.ALERT_DATE,'DD-MM-YYYY HH24:MI') ALERT_DATE , "
					+ " TRIM(HRMS_ALERT_MESSAGE.ALERT_MODULE) ALERT_MODULE, "
					+ " DECODE(HRMS_ALERT_MESSAGE.ALERT_MESSAGE_TYPE, 'I', 'INFORMATION', 'A', 'ALERT') ALERT_MESSAGE_TYPE,"
					+ " HRMS_ALERT_MESSAGE.ALERT_APPLICATION_ID,HRMS_ALERT_MESSAGE.ALERT_LEVEL ,"
					+ " HRMS_ALERT_MESSAGE.ALERT_APPL_PARAMS,HRMS_ALERT_MESSAGE.ALERT_LINK,"
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||substr(HRMS_EMP_OFFC.EMP_LNAME,0,1) NAME ";

			approvalAlertsSql += " , CASE WHEN TO_CHAR(ALERT_DATE,'DD-MM-YYYY')=TO_CHAR(SYSDATE,'dd-mm-yyyy') THEN 1 "
					+ " WHEN TO_CHAR(ALERT_DATE,'DD-MM-YYYY')=TO_CHAR(SYSDATE-1,'dd-mm-yyyy') THEN 2 "
					+ "	WHEN TO_CHAR(ALERT_DATE) <SYSDATE-2 AND TO_CHAR(ALERT_DATE) >SYSDATE-7 THEN 3 "
					+ " WHEN TO_CHAR(ALERT_DATE) BETWEEN SYSDATE-8 AND SYSDATE-30  THEN 4 "
					+ " ELSE 5 END DAY_STATUS ";

			approvalAlertsSql += " ,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME1 FROM HRMS_ALERT_MESSAGE  "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =HRMS_ALERT_MESSAGE.ALERT_FROM) "
					+ " WHERE  (ALERT_EMP_ID="+ userEmpID
					+ " OR ALERT_MANAGER_ID="+ userEmpID
					+ " OR ALTER_ALTERNATEID="+ userEmpID
					+ " OR TO_CHAR(','||HRMS_ALERT_MESSAGE.ALERT_CC||',') LIKE '%,"
					+ userEmpID + ",%'" + " )";
			approvalAlertsSql += " AND ALERT_LINK IS NOT NULL AND HRMS_ALERT_MESSAGE.ALERT_MESSAGE_TYPE='I' "
								+ " AND   SYSDATE - HRMS_ALERT_MESSAGE.ALERT_DATE <= 15";

			if (click != null && click.equals("Input")) {
				approvalAlertsSql += " AND HRMS_ALERT_MESSAGE.ALERT_ACTIVE='Y'";
			} else {
				approvalAlertsSql += " AND HRMS_ALERT_MESSAGE.ALERT_ACTIVE IN('Y','N')";
			}
			// approvalAlertsSql += " AND HRMS_ALERT_MESSAGE.ALERT_MODULE NOT
			// LIKE 'Appraisal' ";
			if (moduleName != null && moduleName.equals("--Select--")) {
				moduleName = "";
			}
			if (moduleName != null && !moduleName.equals("")) {
				approvalAlertsSql += " AND UPPER(ALERT_MODULE)='"
						+ moduleName.trim().toUpperCase() + "' ";
			}
			// && !status.equals("") && !status.equals("Inbox")
			// && !status.equals("NotInbox")

			if (filterStatus != null && filterStatus.equals("--Select--")) {
				filterStatus = "";
			}
			if (filterStatus != null && !filterStatus.equals("")) {
				approvalAlertsSql += " AND UPPER(ALERT_STATUS)='"
						+ filterStatus.trim().toUpperCase() + "' ";
			}
			if (status != null && status.equals("Inbox")) {
				approvalAlertsSql += "  AND ALERT_STATUS!='Draft'";
			}
			if (status != null
					&& (status.equals("NotInbox") || source.equals("D"))) {
				approvalAlertsSql += " AND ALERT_STATUS='Draft'";
			}
			/*
			 * if(year==null) {
			 */
			year = String.valueOf(currentYearObj[0][0]);
			// approvalAlertsSql += " AND
			// to_CHAR(HRMS_ALERT_MESSAGE.ALERT_DATE,'yyyy') IN ('"
			// + year + "','"+(Integer.parseInt(year)-1)+"') ";
			if (searchMessage != null && !searchMessage.trim().equals("")) {
				approvalAlertsSql += " AND UPPER(ALERT_SUBJECT||ALERT_MODULE||HRMS_EMP_OFFC.EMP_FNAME||"
								+ " HRMS_EMP_OFFC.EMP_LNAME||TO_CHAR(HRMS_ALERT_MESSAGE.ALERT_DATE, 'DD-MM-YYYY HH24:MI')) "
								+ " LIKE UPPER (trim('%"+ searchMessage.trim() + "%'))";
			}
			approvalAlertsSql += " ) ALERTMSG ";
			// End Of Union
			if (sortOption != null && sortOption.equals("from")) {
				approvalAlertsSql += " ORDER BY DAY_STATUS,ALERTMSG.NAME1 ";
			}
			if (sortOption != null && sortOption.equals("received")) {
				approvalAlertsSql += " ORDER BY DAY_STATUS,ALERTMSG.ALERT_DATE,ALERTMSG.ALERT_ID DESC ";
			}
			if (sortOption != null && sortOption.equals("module")) {
				approvalAlertsSql += " ORDER BY DAY_STATUS,TRIM(ALERTMSG.ALERT_MODULE) ";
			}
			Object[][] processAlertsObj = getSqlModel().getSingleResult(
					approvalAlertsSql);
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					processAlertsObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			int length = 0;
			try {
				length = Integer.parseInt(pageIndex[1])
						- Integer.parseInt(pageIndex[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int count = 0;
			if (processAlertsObj != null && processAlertsObj.length > 0) {
				alertsObj = new Object[length][13];
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					alertsObj[count][0] = PPEncrypter.encrypt(String
							.valueOf(processAlertsObj[i][0])); // ALERT_ID
					alertsObj[count][1] = String
							.valueOf(processAlertsObj[i][1]); // ALERT_SUBJECT
					alertsObj[count][2] = String
							.valueOf(processAlertsObj[i][2]); // ALERT_EMP_ID
					alertsObj[count][3] = String
							.valueOf(processAlertsObj[i][3]); // ALERT_MESSAGE
					alertsObj[count][4] = String
							.valueOf(processAlertsObj[i][4]); // ALERT_DATE
					alertsObj[count][5] = String
							.valueOf(processAlertsObj[i][5]); // ALERT_MODULE
					alertsObj[count][6] = String
							.valueOf(processAlertsObj[i][6]); // ALERT_MESSAGE_TYPE
					alertsObj[count][7] = String
							.valueOf(processAlertsObj[i][7]); // ALERT_APPLICATION_ID
					alertsObj[count][8] = String
							.valueOf(processAlertsObj[i][8]); // ALERT_LEVEL
					String alertModule = String.valueOf(processAlertsObj[i][5])
							.replace(" ", "").toLowerCase(); // ALERT_MODULE
					/*
					 * if
					 * (!String.valueOf(processAlertsObj[i][9]).equals("null")) {
					 * alertModule += "_new";
					 */
					/*
					 * alertsObj[i][9] = String
					 * .valueOf(processAlertsObj[i][10]) // ALERT_LINK +
					 * "?encr=Y&" +
					 * PPEncrypter.encrypt(String.valueOf(processAlertsObj[i][9]) +
					 * "&src=mymessages");
					 */
					alertsObj[count][9] = String
							.valueOf(processAlertsObj[i][10]) // ALERT_LINK
							+ "?"
							+ String.valueOf(processAlertsObj[i][9])
							+ "&src=mymessages";
					/* } *//*
							 * else { alertsObj[i][9] =
							 * alertProp.getProperty(alertModule); // ALERT_LINK }
							 */
					alertsObj[count][10] = String
							.valueOf(processAlertsObj[i][11]);
					alertsObj[count][11] = String
							.valueOf(processAlertsObj[i][12]);
					alertsObj[count][12] = String
							.valueOf(processAlertsObj[i][13]);
					count++;
				}
				/**To differentiate Alerts according to Days filter
				 * such as Today, Yesterday, Last Week...etc*/
				HashMap alertMap = getAlertMap(alertsObj);
				Set keySet = null;
				Iterator ittKey = null;
				keySet = alertMap.keySet();
				ittKey = keySet.iterator();
				while (ittKey.hasNext()) {
					String key = (String) ittKey.next();
					Vector vectAlert = (Vector) alertMap.get(key);
					for (int i = 0; i < vectAlert.size(); i++) {
						Object[] alertMsg = (Object[]) vectAlert.get(i);
					}
				}
				request.setAttribute("noOfItem", alertMap.size());
				request.setAttribute("alertsMap", alertMap);
				bean.setShowPageFlag("true");
			} else {
				request.setAttribute("noOfItem", 0);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**@Method Name :getAlertMap() 
	 * @Purpose : This Method is used to display Alerts in My Page order by Dates 
	 * @param alertObject
	 * @return HashMap
	 */
	public HashMap getAlertMap(Object[][] alertObject) {
		LinkedHashMap<String, Vector> alertMap = new LinkedHashMap<String, Vector>();
		for (int i = 0; i < alertObject.length; i++) {
			String key = String.valueOf(alertObject[i][11]);
			if (alertMap.containsKey(key)) {
				Vector alertVector = alertMap.get(key);
				alertVector.add(alertObject[i]);
				alertMap.put(key, alertVector);
			} else {
				Vector alertVector = new Vector();
				alertVector.add(alertObject[i]);
				alertMap.put(key, alertVector);
			}
		}
		return alertMap;
	}
	
	
	public void showAlertsAppraisal(String userEmpID, HttpServletRequest request) {

		Object[][] processAppraisalAlertsObj = null;
		try {
			String alertsSql = " SELECT HRMS_ALERT_MESSAGE.ALERT_ID, HRMS_ALERT_MESSAGE.ALERT_SUBJECT, HRMS_ALERT_MESSAGE.ALERT_EMP_ID, HRMS_ALERT_MESSAGE.ALERT_MESSAGE, TO_CHAR(HRMS_ALERT_MESSAGE.ALERT_DATE, 'DD-MM-YYYY'),  "
					+ " HRMS_ALERT_MESSAGE.ALERT_MODULE, DECODE(HRMS_ALERT_MESSAGE.ALERT_MESSAGE_TYPE, 'I', 'Information', 'A', 'Alert') ALERT_MESSAGE_TYPE, HRMS_ALERT_MESSAGE.ALERT_APPLICATION_ID,  "
					+ " HRMS_ALERT_MESSAGE.ALERT_LEVEL, PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID, HRMS_ALERT_MESSAGE.ALERT_APPLICANT_ID FROM HRMS_ALERT_MESSAGE "
					+ " LEFT JOIN PAS_APPR_TRACKING ON(PAS_APPR_TRACKING.APPR_ID = HRMS_ALERT_MESSAGE.ALERT_APPLICATION_ID "
					+ " AND PAS_APPR_TRACKING.EMP_ID = HRMS_ALERT_MESSAGE.ALERT_APPLICANT_ID AND NEXT_APPRAISER = "
					+ userEmpID
					+ ") "
					+ " LEFT JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_TRACKING.NEXT_PHASE_ID = PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID) "
					+ " WHERE ALERT_EMP_ID = "
					+ userEmpID
					+ " AND SYSDATE - HRMS_ALERT_MESSAGE.ALERT_DATE <= 30 AND HRMS_ALERT_MESSAGE.ALERT_ACTIVE = 'Y' AND HRMS_ALERT_MESSAGE.ALERT_MODULE LIKE 'Appraisal' AND ALERT_LINK IS NOT NULL "
					+ " ORDER BY HRMS_ALERT_MESSAGE.ALERT_DATE DESC  ";

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

	/**
	 * This method is used for saving draft record entry into process manager
	 * alert table.
	 * 
	 * @param subject :
	 *            subject
	 * @param empID
	 *            :employee id
	 * @param message
	 *            :message
	 * @param module
	 *            :module name
	 * @param msgType
	 *            :message type
	 * @param applnID:application
	 *            id
	 * @param level:level
	 * @param link_param
	 *            :link parameters
	 * @param link
	 *            :link name
	 * @param status
	 *            :status actual status like pending
	 * @param applicantId
	 *            :application id.
	 * @param keepInformedTo
	 * @param managerId
	 */
	public void saveAlertForDraft(String subject, String empID, String message,
			String module, String msgType, String applnID, String level,
			String link_param, String link, String status, String applicantId,
			String fromId, String keepInformedTo, String managerId,
			boolean checkIsDraft) {
		try {
			boolean isSave = false;
			String query = " SELECT * FROM HRMS_ALERT_MESSAGE WHERE ALERT_APPLICATION_ID="
					+ applnID
					+ " AND "
					+ " UPPER(ALERT_MODULE) LIKE '"
					+ module.trim().toUpperCase() + "'";
			Object result[][] = getSqlModel().getSingleResult(query);

			// It check that is it Drafted or Withdrawn application

			if (checkIsDraft) {
				isSave = (result != null && result.length == 0) ? true : false;
			} else {
				isSave = true;
			}

			if (isSave) {
				String addAlertSql = " INSERT INTO HRMS_ALERT_MESSAGE(ALERT_ID, ALERT_SUBJECT, ALERT_EMP_ID, ALERT_MESSAGE, ALERT_DATE, "
						+ " ALERT_MODULE, ALERT_MESSAGE_TYPE, ALERT_APPLICATION_ID, ALERT_LEVEL, ALERT_ACTIVE,ALERT_APPL_PARAMS,ALERT_LINK,ALERT_STATUS,ALERT_APPLICANT_ID,ALERT_FROM,ALERT_CC,ALERT_MANAGER_ID) "
						+ " VALUES ((SELECT NVL(MAX(ALERT_ID), 0) + 1 FROM HRMS_ALERT_MESSAGE), ?, ?, ?, SYSDATE, ?, ?, ?, ?, 'Y',?,?,?,?,?,?,?) ";

				Object[][] addAlertObj = new Object[1][14];
				addAlertObj[0][0] = subject;
				addAlertObj[0][1] = empID;
				addAlertObj[0][2] = "";
				addAlertObj[0][3] = module;
				addAlertObj[0][4] = msgType;
				addAlertObj[0][5] = applnID;
				addAlertObj[0][6] = level;
				addAlertObj[0][7] = link_param;
				addAlertObj[0][8] = link;
				addAlertObj[0][9] = status;

				addAlertObj[0][10] = applicantId;
				addAlertObj[0][11] = fromId;
				addAlertObj[0][12] = keepInformedTo;
				addAlertObj[0][13] = managerId;

				getSqlModel().singleExecute(addAlertSql, addAlertObj);
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param subject :
	 *            subject for alert
	 * @param managerId :
	 *            Manager id
	 * @param message :
	 *            message
	 * @param module
	 *            :module name
	 * @param msgType
	 *            :message type A/I action/Information action for manager and
	 *            information for employee and cc maanger
	 * @param applnID :
	 *            application code
	 * @param level :
	 *            level
	 * @param link_param :
	 *            link parameter
	 * @param link :
	 *            link name
	 * @param status
	 *            :status actual like pending
	 * @param applicantId :
	 *            employee id
	 * @param alternateApproverId
	 *            :alternate approver id
	 * @param ccId
	 *            :keep informed to ids
	 * @param empId
	 *            :employee id
	 */

	public void saveAlert(String subject, String managerId, String message,
			String module, String msgType, String applnID, String level,
			String link_param, String link, String status, String applicantId,
			String alternateApproverId, String ccId, String empId, String fromId) {
		try {
			String addAlertSql = " INSERT INTO HRMS_ALERT_MESSAGE(ALERT_ID, ALERT_SUBJECT, ALERT_MANAGER_ID, ALERT_MESSAGE, ALERT_DATE, "
					+ " ALERT_MODULE, ALERT_MESSAGE_TYPE, ALERT_APPLICATION_ID, ALERT_LEVEL, ALERT_ACTIVE,ALERT_APPL_PARAMS,ALERT_LINK,ALERT_STATUS ,ALERT_APPLICANT_ID,ALTER_ALTERNATEID,ALERT_CC,ALERT_EMP_ID ,ALERT_FROM ) "
					+ " VALUES ((SELECT NVL(MAX(ALERT_ID), 0) + 1 FROM HRMS_ALERT_MESSAGE), ?, ?, ?, SYSDATE, ?, ?, ?, ?, 'Y',?,?,?,?,?,?,?,?) ";

			Object[][] addAlertObj = new Object[1][15];
			addAlertObj[0][0] = subject;
			addAlertObj[0][1] = managerId;
			addAlertObj[0][2] = "";
			addAlertObj[0][3] = module;
			addAlertObj[0][4] = msgType;
			addAlertObj[0][5] = applnID;
			addAlertObj[0][6] = level;
			addAlertObj[0][7] = link_param;
			addAlertObj[0][8] = link;
			addAlertObj[0][9] = status;

			addAlertObj[0][10] = applicantId;

			addAlertObj[0][11] = alternateApproverId;

			addAlertObj[0][12] = ccId;

			addAlertObj[0][13] = empId;

			addAlertObj[0][14] = fromId;			
			getSqlModel().singleExecute(addAlertSql, addAlertObj);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void saveAlert(String subject, String managerId, String message,
			String module, String msgType, String applnID, String level,
			String link_param, String link, String status, String applicantId,
			String alternateApproverId, String ccId, String empId,
			Connection dbconn) {
		try {
			String addAlertSql = " INSERT INTO HRMS_ALERT_MESSAGE(ALERT_ID, ALERT_SUBJECT, ALERT_MANAGER_ID, ALERT_MESSAGE, ALERT_DATE, "
					+ " ALERT_MODULE, ALERT_MESSAGE_TYPE, ALERT_APPLICATION_ID, ALERT_LEVEL, ALERT_ACTIVE,ALERT_APPL_PARAMS,ALERT_LINK,ALERT_STATUS ,ALERT_APPLICANT_ID,ALTER_ALTERNATEID,ALERT_CC,ALERT_EMP_ID ) "
					+ " VALUES ((SELECT NVL(MAX(ALERT_ID), 0) + 1 FROM HRMS_ALERT_MESSAGE), ?, ?, ?, SYSDATE, ?, ?, ?, ?, 'Y',?,?,?,?,?,?,?) ";

			Object[][] addAlertObj = new Object[1][14];
			addAlertObj[0][0] = subject;
			addAlertObj[0][1] = managerId;
			addAlertObj[0][2] = "";
			addAlertObj[0][3] = module;
			addAlertObj[0][4] = msgType;
			addAlertObj[0][5] = applnID;
			addAlertObj[0][6] = level;
			addAlertObj[0][7] = link_param;
			addAlertObj[0][8] = link;
			addAlertObj[0][9] = status;

			addAlertObj[0][10] = applicantId;

			addAlertObj[0][11] = alternateApproverId;

			addAlertObj[0][12] = ccId;

			addAlertObj[0][13] = empId;

			getSqlModel().singleExecute(addAlertSql, addAlertObj);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for removing process manager entry from mypage.
	 * 
	 * @param applnID
	 *            :-application id
	 * @param module
	 *            :-module name
	 */

	public void removeProcessAlert(String applnID, String module) {
		try {
			String removeAlertSql = " UPDATE HRMS_ALERT_MESSAGE SET ALERT_ACTIVE = 'N' WHERE ALERT_APPLICATION_ID = "
					+ applnID + " AND ALERT_MODULE LIKE '" + module + "'";
			getSqlModel().singleExecute(removeAlertSql);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * This method is used for removing process manager entry from mypage.
	 * 
	 * @param applnID
	 *            :-application id
	 * @param empID
	 *            :-employee id.
	 */

	public void removeProcessAlertAppraisal(String applnID, String empID) {
		try {
			String removeAlertSql = " UPDATE HRMS_ALERT_MESSAGE SET ALERT_ACTIVE = 'N' WHERE ALERT_APPLICATION_ID = "
					+ applnID
					+ " AND ALERT_APPLICANT_ID = "
					+ empID
					+ " AND ALERT_EMP_ID IN (SELECT NEXT_APPRAISER FROM PAS_APPR_TRACKING "
					+ " WHERE APPR_ID = "
					+ applnID
					+ " AND EMP_ID = "
					+ empID
					+ " AND NEXT_APPRAISER IS NOT NULL) ";
			getSqlModel().singleExecute(removeAlertSql);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * This method is used for show employee details
	 * 
	 * @param request :-
	 *            request
	 * @param bean :-
	 *            bean
	 */
	public void getEmployeeInfo(HttpServletRequest request,
			MypageProcessManagerAlerts bean) {
		try {
			Object[][] empInfoObj = null;
			String loginString = " SELECT EMP_TOKEN ,HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||' '||EMP_LNAME,EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_PHOTO,''),EMP_FNAME||' '||EMP_LNAME AS NAME  FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ " WHERE EMP_ID ='" + bean.getUserEmpId() + "'";
			empInfoObj = getSqlModel().getSingleResult(loginString);
			if (empInfoObj != null && empInfoObj.length > 0) {

				request
						.setAttribute("UserID", String
								.valueOf(empInfoObj[0][0]));
				request.setAttribute("UserName", String
						.valueOf(empInfoObj[0][1]));
				request.setAttribute("login_EmpId", bean.getUserEmpId());
				request.setAttribute("EmployeeName", String
						.valueOf(empInfoObj[0][4]));

				String photo = String.valueOf(empInfoObj[0][2]);
				if (String.valueOf(empInfoObj[0][1]) != null
						&& !photo.equals("")) {
					request.setAttribute("profilePhoto", String
							.valueOf(empInfoObj[0][3]));
				} else {
					request.setAttribute("profilePhoto", "empimage.gif");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getEmployeeDtl(MypageProcessManagerAlerts bean, String empcode, String linkFlag) {
		try {
			Object[][] empInfoObj = null;

			if (empcode == null || empcode.equals("null") || empcode.equals("")) {
				empcode = bean.getUserEmpId();
			}

			String loginString = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME,EMP_ID,EMP_TOKEN|| ' ' ||'-' || ' ' || HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
					+ " HRMS_EMP_OFFC.EMP_LNAME   FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ " WHERE EMP_ID ='" + empcode + "'";
			empInfoObj = getSqlModel().getSingleResult(loginString);
			if (empInfoObj != null && empInfoObj.length > 0) {

				if (linkFlag.equals("leave")) {
					bean.setLeaveEmpToken(String.valueOf(empInfoObj[0][0]));
					bean.setLeaveEmpName(String.valueOf(empInfoObj[0][3]));
					bean.setLeaveEmpCode(String.valueOf(empInfoObj[0][2]));
				}
				else{
					bean.setEmpTokenMyPage(String.valueOf(empInfoObj[0][0]));
					bean.setEmpNameMyPage(String.valueOf(empInfoObj[0][3]));
					bean.setEmpCodeMyPage(String.valueOf(empInfoObj[0][2]));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getEmployeeDtlAttendanceRegularization(
			MypageProcessManagerAlerts bean, String empcode) {
		try {
			Object[][] empInfoObj = null;

			if (empcode == null || empcode.equals("null")) {
				empcode = bean.getUserEmpId();
			}

			String loginString = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_LNAME,EMP_ID,EMP_TOKEN|| ' ' ||'-' || ' ' || HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
					+ " HRMS_EMP_OFFC.EMP_LNAME   FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ " WHERE EMP_ID ='" + empcode + "'";
			empInfoObj = getSqlModel().getSingleResult(loginString);
			if (empInfoObj != null && empInfoObj.length > 0) {

				bean.setEmpTokenAttReg(String.valueOf(empInfoObj[0][0]));
				bean.setEmpNameAttReg(String.valueOf(empInfoObj[0][3]));

				bean.setEmpCodeAttReg(String.valueOf(empInfoObj[0][2]));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used give the application login details
	 * 
	 * @param request
	 *            :-request
	 * @param bean
	 *            :-bean
	 */
	public void getApplicationLoginDetails(HttpServletRequest request,
			MypageProcessManagerAlerts bean) {
		try {
			HashMap<String, String> userNameMap = null;
			HashMap<String, String> passwordMap = null;
			String userNamePassQuery = " SELECT HRMS_APPL_CODE,HRMS_APPL_USERNAME,HRMS_APPL_PASSWORD FROM HRMS_APPL_CREDENTIAL  "
					+ " WHERE   HRMS_APPL_EMPCODE="
					+ bean.getUserEmpId()
					+ " ORDER BY HRMS_APPL_CODE ";
			Object userNameQueryObj[][] = getSqlModel().getSingleResult(
					userNamePassQuery);
			userNameMap = new HashMap<String, String>();
			passwordMap = new HashMap<String, String>();
			if (userNameQueryObj != null && userNameQueryObj.length > 0) {
				for (int j = 0; j < userNameQueryObj.length; j++) {

					userNameMap.put(String.valueOf(userNameQueryObj[j][0]),
							String.valueOf(userNameQueryObj[j][1]));
					passwordMap.put(String.valueOf(userNameQueryObj[j][0]),
							String.valueOf(userNameQueryObj[j][2]));
				}

			}
			request.setAttribute("applUserName", userNameMap);
			request.setAttribute("applPassword", passwordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getMyServiceMenu(HttpServletRequest request,
			MypageProcessManagerAlerts bean, String menuType) {
		try {

			if (menuType == null) {
				menuType = "ES";
			}
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE 
			String groupQuery = " select DISTINCT MENU_GROUP from HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   and HRMS_PROFILE_DTL.PROFILE_CODE IN("
					+ bean.getMultipleProfileCode()
					+ "))"
					+ " WHERE MENU_TYPE='"
					+ menuType
					+ "' AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP ";
			Object groupData[][] = getSqlModel().getSingleResult(groupQuery);

			String query = " SELECT DISTINCT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME, MENU_SERVICE_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP,MENU_PLACEINMENU FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ "	OR PROFILE_GENERAL_FLAG ='Y'))"
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN("
					+ bean.getMultipleProfileCode()
					+ ") and MENU_TYPE='"
					+ menuType
					+ "' "
					+ " AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP,MENU_PLACEINMENU";

			Object data[][] = getSqlModel().getSingleResult(query);
			request.setAttribute("serviceMenulst", data);
			request.setAttribute("groupData", groupData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is use for give Employee Directory Configuration Record.
	 * 
	 * @return Objec :- Object
	 */
	public Object[][] getEmployeeDirectoryConfiguration() {
		Object[][] data;
		data = null;
		try {

			String query = "SELECT HRMS_EMP_DIRETORY_CONF.EMP_BDAY, HRMS_EMP_DIRETORY_CONF.EMP_BLOODGROUP, HRMS_EMP_DIRETORY_CONF.EMP_JOINING_DATE, HRMS_EMP_DIRETORY_CONF.EMP_DEPTARTMENT, HRMS_EMP_DIRETORY_CONF.EMP_ROLE_DESG, HRMS_EMP_DIRETORY_CONF.EMP_MANAGER_INFO, HRMS_EMP_DIRETORY_CONF.EMP_BRANCH, HRMS_EMP_DIRETORY_CONF.EMP_EMAILID, HRMS_EMP_DIRETORY_CONF.EMP_EXTENSION, HRMS_EMP_DIRETORY_CONF.EMP_MOBILENO, HRMS_EMP_DIRETORY_CONF.EMP_PHONENO,HRMS_EMP_DIRETORY_CONF.EMP_PHOTO FROM HRMS_EMP_DIRETORY_CONF";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Object[][] getTimecardData(String empcode,
			String myTimecardfromDate, String myTimecardtoDate,
			String loginEmpId, String myTimecardPrstatus,
			String myTimecardAbstatus, String regularizedStatusstr,
			String leaveStatusStr, String traveltimecardStr,
			String halfDaytimecardStr, String showallFilterValue,
			String latetimecardStatusStr, String myTeam, String notLoginUser) {

		Object[][] data;
		data = null;
		Object[][] dataTeam;
		dataTeam = null;
		Date loginTimeInDate = null, logoutTimeInDate = null;
		SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm");
		// Object finalObj[][] = null;
		String empCode = "";
		String yearName = "";
		Object[][] dateObj = null;
		String query = "";
		String selectedStatus = "'NA'";

		if (latetimecardStatusStr != null && !latetimecardStatusStr.equals("") && latetimecardStatusStr.equals("true")) {
			selectedStatus += ",'Early-Leaving','Late-Coming','Dual Late'";
		}
		if (myTimecardAbstatus != null && !myTimecardAbstatus.equals("") && myTimecardAbstatus.equals("true")) {
			selectedStatus += ",'Absent'";
		}

		if (myTimecardPrstatus != null && !myTimecardPrstatus.equals("") && myTimecardPrstatus.equals("true")) {
			selectedStatus += ",'Present'";
		}
		if (halfDaytimecardStr != null && !halfDaytimecardStr.equals("") && halfDaytimecardStr.equals("true")) {
			selectedStatus += ",'Half Day','Half Day Leave'";
		}

		if (regularizedStatusstr != null && !regularizedStatusstr.equals("") && regularizedStatusstr.equals("true")) {
			selectedStatus += ",'Regularized'";
		}

		if (leaveStatusStr != null && !leaveStatusStr.equals("") && leaveStatusStr.equals("true")) {
			selectedStatus += ",'Leave'";
		}
		if (traveltimecardStr != null && !traveltimecardStr.equals("") && traveltimecardStr.equals("true")) {
			selectedStatus += ",'Travel'";
		}
		if ((showallFilterValue == null  ||  showallFilterValue.equals("") || showallFilterValue.equals("null")) ||
				((latetimecardStatusStr.equals("null") || latetimecardStatusStr.equals("") || latetimecardStatusStr==null)&& myTimecardAbstatus.equals("null") && 
				(myTimecardPrstatus.equals("null") || myTimecardPrstatus.equals("") || myTimecardPrstatus== null) &&
				(halfDaytimecardStr.equals("null")|| halfDaytimecardStr.equals("") || halfDaytimecardStr== null) &&  
				(regularizedStatusstr.equals("null") || regularizedStatusstr.equals("") || regularizedStatusstr== null)&& 
				(leaveStatusStr.equals("null") || leaveStatusStr.equals("") || leaveStatusStr==null)&& 
				(traveltimecardStr.equals("null")||traveltimecardStr.equals("") || traveltimecardStr==null))){
				selectedStatus+=",'Early-Leaving','Late-Coming','Absent','Present','Half Day','Half Day Leave','Regularized','Leave','Travel','Weekly Off','Dual Late','Holiday'";
		}
		else if ((showallFilterValue != null  && ! showallFilterValue.equals("") && showallFilterValue.equals("true")) ||
			(latetimecardStatusStr.equals("false")&& myTimecardAbstatus.equals("false") && myTimecardPrstatus.equals("false") &&
			halfDaytimecardStr.equals("false") && regularizedStatusstr.equals("false") && leaveStatusStr.equals("false") && 
			traveltimecardStr.equals("false") && showallFilterValue.equals("false"))){
			selectedStatus+=",'Early-Leaving','Late-Coming','Absent','Present','Half Day','Half Day Leave','Regularized','Leave','Travel','Weekly Off','Dual Late','Holiday'";
		}
			
		if (myTeam.equals("true")) {
			try {
				String myTeamStr = "0";
				String[] myTeamCode = getMyTeamEmpCode(loginEmpId);
				if (myTeamCode != null && !myTeamCode.equals("")) {
					for (int i = 0; i < myTeamCode.length; i++) {
						if (i == myTeamCode.length) {
							myTeamStr += myTeamCode[i];
						} else {
							myTeamStr += "," + myTeamCode[i];
						}
					}
				}
				if ((myTimecardfromDate != null && !myTimecardfromDate
						.equals(""))
						&& (myTimecardtoDate != null && !myTimecardtoDate
								.equals(""))) {
					// If show button of My Timecard is clicked

					String fromDate = myTimecardfromDate;
					String toDate = myTimecardtoDate;

					/**
					 * Calculate number of years between from year and to year
					 */

					String fromYear = fromDate.substring(6, 10);
					String toYear = toDate.substring(6, 10);
					int diffYear = Integer.parseInt(toYear)
							- Integer.parseInt(fromYear);

					int fYear = Integer.parseInt(fromYear);
					int tYear = Integer.parseInt(toYear);
					String showQuery = " SELECT * FROM ( ";
					for (int i = 0; i <= diffYear; i++) {

						showQuery += " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME,TO_CHAR(ATT_DATE,'DD-MM-YYYY') ATTENDANCE_DATE ,SUBSTR(TO_CHAR(ATT_DATE, 'DAY'),0,3), "
								+ "  CASE WHEN (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) 	 	"
								+ "  AND TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')< TO_CHAR(ATT_LOGIN, 'HH24:MI')	"
								+ "  THEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')	  "
								+ "  WHEN (TO_CHAR(ATT_LOGIN, 'HH24:MI')='00:00' OR ATT_LOGIN IS NULL)   "
								+ "  AND  (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) "
								+ "  THEN  NVL(TO_CHAR(ATT_REG_LOGIN, 'HH24:MI'),'00:00')  	 "
								+ "  ELSE NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI'),'00:00')  END INTIME, "
								+ "  CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) 	 "
								+ "  THEN ( "
								+ "  CASE WHEN TO_char(ATT_REG_LOGOUT, 'HH24:MI')>NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')"
								+ "  THEN TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI') ELSE TO_CHAR(ATT_LOGOUT, 'HH24:MI') END"
								+ "  )"
								+ "  WHEN (TO_CHAR(ATT_LOGOUT, 'HH24:MI')='00:00' OR ATT_LOGOUT IS NULL)   "
								+ "  THEN  NVL(TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI'),'00:00')  	 "
								+ "  ELSE NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')  END OUTTIME, "
								// Query for calculating WORKDURATION begins
								// here
								+ " CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) " 
								+ " AND TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')> NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')   "
								+ " THEN   TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
								+ " ( " 
								+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"
								+ " AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') "
								+ " ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
								+ " ))*24),'00')|| ':' || "
								+ " TO_CHAR(MOD(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
								+ " CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
								+ "  LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') )"
								+ " ELSE ATT_REG_LOGIN END)*24*60),60),'00') "
								+ " ELSE  "
								+ " (CASE WHEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00'"
								+ " THEN (  TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')-"
								+ " ("
								+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"
								+ " THEN TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
								+ " ) )*24),'00')"
								+ " || ':' || "
								+ " TO_CHAR(MOD(FLOOR((GREATEST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'))-CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' "
								+ " THEN LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ) ELSE ATT_REG_LOGIN END)*24*60),60),'00')"
								+ " )  ELSE  (CASE  WHEN TO_CHAR(ATT_LOGOUT, 'HH24:MI')!='00:00' AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00'"
								+ " THEN (NVL(TO_CHAR(FLOOR((ATT_LOGOUT - ATT_LOGIN)*24),'00'),'00')|| ':' ||" 
								+ " NVL( TO_CHAR(MOD(FLOOR((ATT_LOGOUT-ATT_LOGIN)*24*60),60),'00'),'00') "
								+ " )"
								+ " ELSE '00:00' END)END)"
								+ " END WORKDURATION, "
								+ "  CASE  "
								+ "  WHEN ATT_STATUS_ONE='WO' THEN 'Weekly Off'"
								+ "  WHEN ATT_STATUS_ONE='HO' THEN 'Holiday'"
								+ "  WHEN ATT_REG_STATUS_TWO='LV' THEN 'Leave'  "
								+ "  WHEN ATT_REG_STATUS_TWO='TR' THEN 'Travel' "
								+ "  WHEN ATT_REG_STATUS_TWO='RG' THEN 'Regularized' "
								+ "  WHEN ATT_REG_STATUS_TWO='HL' THEN 'Half Day Leave'"
								+ "  WHEN ATT_REG_STATUS_TWO='EW' THEN 'ExtraWork'  "
								+ "  WHEN (ATT_STATUS_ONE='PR' and ATT_STATUS_TWO='IN') THEN 'Present' "
								+ "  WHEN ATT_STATUS_ONE='PR' then DECODE(ATT_STATUS_TWO,'HD','Half Day','DL','Dual Late','LC','Late-Coming','EL','Early-Leaving','PR','Present','AB','Absent',NULL,'Present') "
								+ "  ELSE DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','HO','Holiday') "
								+ "  END NEW_STATUS, "
								+ "  NVL(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), '00:00') SHIFTDURATION,DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','PR','Present') STATUS_ONE,DECODE(ATT_STATUS_TWO,'HD','Half Day','AB','Absent','IN','Intime','DL','Dual Late' "
								+ "  ,'LC','Late-Coming','EL','Early-Leaving') STATUS_TWO "
								+ "  ,Shift_name,SHIFT_ID ,DECODE(IS_APPL_PROCESS,'Y','Application processing','N','NA'),ATT_DATE, ATT_EMP_ID FROM HRMS_DAILY_ATTENDANCE_"
								+ fYear
								+ "  INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_"
								+ fYear
								+ ".ATT_SHIFT_ID) "
								+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_DAILY_ATTENDANCE_"
								+ fYear
								+ ".ATT_EMP_ID)";
								if(notLoginUser.equals("true")&& !empcode.equals("")){								
									showQuery+= " WHERE ATT_EMP_ID="+empcode;
								}else{
									showQuery+= " WHERE ATT_EMP_ID IN( " + myTeamStr + ")";
								}
						if (fYear != tYear) {
							showQuery += " UNION ALL ";
							fYear++;
						} else {
							break;
						}
						
					}
					showQuery += ")WHERE" + "   ATT_DATE >= TO_DATE('"
							+ fromDate + "', 'DD-MM-YYYY')  "
							+ " AND ATT_DATE<=TO_DATE('" + toDate
							+ "', 'DD-MM-YYYY')";

					if (showallFilterValue != null
							&& showallFilterValue.equals("All")) {

					} else {
						if (selectedStatus != null
								&& !selectedStatus.equals("'NA'")) {
							//selectedStatus = selectedStatus +",'Weekly Off'," + "'Dual Late'";
							showQuery += " AND NEW_STATUS IN(" + selectedStatus
									+ ")";
						}
					}

					showQuery += " ORDER BY  EMPNAME,TO_DATE(ATT_DATE, 'DD-MM-YYYY') ";

					query = showQuery;
				} else {
					// Called when My Attendance/My Timecard Link is clicked

					String dateQuery = "SELECT TO_CHAR(SYSDATE,'yyyy'),TO_CHAR(SYSDATE,'MM') FROM DUAL";
					dateObj = getSqlModel().getSingleResult(dateQuery);
					if (dateObj != null && dateObj.length > 0) {
						query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME,TO_CHAR(ATT_DATE,'DD-MM-YYYY') ATTENDANCE_DATE ,SUBSTR(TO_CHAR(ATT_DATE, 'DAY'),0,3), "
								// Query for selecting INTIME begins here
								+ "  CASE WHEN (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) "
								+ "  AND TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')< TO_CHAR(ATT_LOGIN, 'HH24:MI')	" 
								+ "  THEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')	  "
								+ "  WHEN (TO_CHAR(ATT_LOGIN, 'HH24:MI')='00:00' OR ATT_LOGIN IS NULL)   "
								+ "  AND  (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) "
								+ "  THEN  NVL(TO_CHAR(ATT_REG_LOGIN, 'HH24:MI'),'00:00')  	 "
								+ "  ELSE NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI'),'00:00')  END INTIME, "
								// Query for selecting OUTTIME begins here
								+ "  CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) "
								+ "  THEN ( "// if yes then
								+ "  CASE WHEN TO_char(ATT_REG_LOGOUT, 'HH24:MI')>NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')"
								+ "  THEN TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI') ELSE TO_CHAR(ATT_LOGOUT, 'HH24:MI') END"
								+ "  )"
								+ "  WHEN (TO_CHAR(ATT_LOGOUT, 'HH24:MI')='00:00' OR ATT_LOGOUT IS NULL)   "
								+ "  THEN  NVL(TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI'),'00:00')  	 "
								+ "  ELSE NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')  END OUTTIME, "
								// Query for calculating WORKDURATION begins
								// here
								+ " CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) " 
								+ " AND TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')> NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')   "
								+ " THEN   TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
								+ " ( " // for subtraction from login time to
										// get WORKDURATION
								+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"
								+ " AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') "
								+ " ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
								+ " ))*24),'00')|| ':' || "
								+ " TO_CHAR(MOD(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
								+ " CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
								+ "  LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') )"
								+ " ELSE ATT_REG_LOGIN END)*24*60),60),'00') "
								+ " ELSE  "
								+ " (CASE WHEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00'"
								+ " THEN (  TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')-"
								+ " ("
								+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"
								+ " THEN TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
								+ " ) )*24),'00')"// convert it in hour
								+ " || ':' || "
								+ " TO_CHAR(MOD(FLOOR((GREATEST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'))-CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' "
								+ " THEN LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ) ELSE ATT_REG_LOGIN END)*24*60),60),'00')"
								+ " )  ELSE  (CASE  WHEN TO_CHAR(ATT_LOGOUT, 'HH24:MI')!='00:00' AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00'"
								+ " THEN (NVL(TO_CHAR(FLOOR((ATT_LOGOUT - ATT_LOGIN)*24),'00'),'00')|| ':' ||" 
								+ " NVL( TO_CHAR(MOD(FLOOR((ATT_LOGOUT-ATT_LOGIN)*24*60),60),'00'),'00') "
								+ " )"
								+ " ELSE '00:00' END)END)"
								+ " END WORKDURATION, "
								// Query for STATUS begins here
								+ "  CASE  "
								+ "  WHEN ATT_STATUS_ONE='WO' THEN 'Weekly Off' " 
								+ "  WHEN ATT_STATUS_ONE='HO' THEN 'Holiday' "
								+ "  WHEN ATT_REG_STATUS_TWO='LV' THEN 'Leave'  " 
								+ "  WHEN ATT_REG_STATUS_TWO='TR' THEN 'Travel' " 
								+ "  WHEN ATT_REG_STATUS_TWO='RG' THEN 'Regularized' "
								+ "  WHEN ATT_REG_STATUS_TWO='EW' THEN 'ExtraWork'  " 
								+ "  WHEN ATT_REG_STATUS_TWO='HL' THEN 'Half Day Leave'"
								+ "  WHEN (ATT_STATUS_ONE='PR' AND ATT_STATUS_TWO='IN') THEN 'Present' "
								+ "  WHEN ATT_STATUS_ONE='PR' THEN DECODE(ATT_STATUS_TWO,'HD','Half Day','DL','Dual Late','LC','Late-Coming','EL','Early-Leaving','PR','Present','AB','Absent',NULL,'Present') "
								+ "  ELSE DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','HO','Holiday') "
								+ "  END NEW_STATUS, "
								+ "  NVL(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), '00:00') SHIFTDURATION,DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','PR','Present') STATUS_ONE,DECODE(ATT_STATUS_TWO,'HD','Half Day','AB','Absent','IN','Intime','DL','Dual Late' "
								+ "  ,'LC','Late-Coming','EL','Early-Leaving') STATUS_TWO "
								+ "  ,Shift_name,SHIFT_ID ,DECODE(IS_APPL_PROCESS,'Y','Application processing','N','NA'),ATT_DATE, ATT_EMP_ID FROM HRMS_DAILY_ATTENDANCE_"
								+ String.valueOf(dateObj[0][0])
								+ "  INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_"
								+ String.valueOf(dateObj[0][0])
								+ ".ATT_SHIFT_ID) "
								+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_DAILY_ATTENDANCE_"
								+ String.valueOf(dateObj[0][0])
								+ ".ATT_EMP_ID)";

						if (notLoginUser.equals("true")) {
							query += " WHERE ATT_EMP_ID= " + empcode;
						} else {
							query += " WHERE ATT_EMP_ID IN( " + myTeamStr + ")";
						}

						query += " AND TO_char(ATT_DATE, 'MM')='"
								+ String.valueOf(dateObj[0][1]) + "' ";

						query += " ORDER BY  TO_DATE(ATT_DATE, 'DD-MM-YYYY')";
					}
				}
				dataTeam = getSqlModel().getSingleResult(query);				
					if (dataTeam != null && dataTeam.length > 0) {
						for (int i = 0; i < dataTeam.length; i++) {
						String inTime = String.valueOf(dataTeam[i][3]);
						String outTime = String.valueOf(dataTeam[i][4]);
						loginTimeInDate = simpleDate.parse(inTime);
						logoutTimeInDate = simpleDate.parse(outTime);
						Calendar workingHrsCal = Calendar.getInstance();
						workingHrsCal.setTime(logoutTimeInDate);
						workingHrsCal.add(Calendar.HOUR, -loginTimeInDate
								.getHours());
						workingHrsCal.add(Calendar.MINUTE, -loginTimeInDate
								.getMinutes());
						workingHrsCal.add(Calendar.SECOND, -loginTimeInDate
								.getSeconds());
						if(inTime.equals("00:00") && (!(outTime.equals("00:00")))){
							dataTeam[i][5] ="00:00";
						}else if((!(inTime.equals("00:00")))&& outTime.equals("00:00")){
							dataTeam[i][5] ="00:00";
						}else if(inTime.equals(outTime)){
							dataTeam[i][5] ="00:00";
						}
						else{
							dataTeam[i][5] = simpleDate.format(workingHrsCal
									.getTime());
						}
					}
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dataTeam;
		} else {
			try {
				if (empcode != null && !empcode.equals(""))
					empCode = empcode;
				else
					empCode = empcode;
				if ((myTimecardfromDate != null && !myTimecardfromDate
						.equals(""))
						&& (myTimecardtoDate != null && !myTimecardtoDate
								.equals(""))) {
					// If show button of My Timecard is clicked
					String fromDate = myTimecardfromDate;
					String toDate = myTimecardtoDate;
					/**
					 * Calculate number of years between from year and to year
					 */
					String fromYear = fromDate.substring(6, 10);
					String toYear = toDate.substring(6, 10);
					int diffYear = Integer.parseInt(toYear)
							- Integer.parseInt(fromYear);

					int fYear = Integer.parseInt(fromYear);
					int tYear = Integer.parseInt(toYear);
					String showQuery = " SELECT * FROM ( ";
					for (int i = 0; i <= diffYear; i++) {
						showQuery += " SELECT TO_CHAR(ATT_DATE,'DD-MM-YYYY') ATTENDANCE_DATE ,SUBSTR(TO_CHAR(ATT_DATE, 'DAY'),0,3), "
								+ "  CASE WHEN (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) 	 	"
								+ "  AND TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')< TO_CHAR(ATT_LOGIN, 'HH24:MI')	"
								+ "  THEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')	  "
								+ "  WHEN (TO_CHAR(ATT_LOGIN, 'HH24:MI')='00:00' OR ATT_LOGIN IS NULL)   "
								+ "  AND  (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) "
								+ "  THEN  NVL(TO_CHAR(ATT_REG_LOGIN, 'HH24:MI'),'00:00')  	 "
								+ "  ELSE NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI'),'00:00')  END INTIME, "
								+ "  CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) 	 "
								+ "  THEN ( "
								+ "  CASE WHEN TO_char(ATT_REG_LOGOUT, 'HH24:MI')>NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')"
								+ "  THEN TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI') ELSE TO_CHAR(ATT_LOGOUT, 'HH24:MI') END"
								+ "  )"
								+ "  WHEN (TO_CHAR(ATT_LOGOUT, 'HH24:MI')='00:00' OR ATT_LOGOUT IS NULL)   "
								+ "  THEN  NVL(TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI'),'00:00')  	 "
								+ "  ELSE NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')  END OUTTIME, "								
								+ " CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) " 
								+ " AND TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')> NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')   "
								+ " THEN   TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
								+ " ( " 
								+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"
								+ " AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') "
								+ " ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
								+ " ))*24),'00')|| ':' || "
								+ " TO_CHAR(MOD(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
								+ " CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
								+ "  LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') )"
								+ " ELSE ATT_REG_LOGIN END)*24*60),60),'00') "
								+ " ELSE  "
								+ " (CASE WHEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00'"
								+ " THEN (  TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')-"
								+ " ("
								+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"
								+ " THEN TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
								+ " ) )*24),'00')"// convert it in hour
								+ " || ':' || "
								+ " TO_CHAR(MOD(FLOOR((GREATEST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'))-CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' "
								+ " THEN LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ) ELSE ATT_REG_LOGIN END)*24*60),60),'00')"
								+ " )  ELSE  (CASE  WHEN TO_CHAR(ATT_LOGOUT, 'HH24:MI')!='00:00' AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00'"
								+ " THEN (NVL(TO_CHAR(FLOOR((ATT_LOGOUT - ATT_LOGIN)*24),'00'),'00')|| ':' ||" 
								+ " NVL( TO_CHAR(MOD(FLOOR((ATT_LOGOUT-ATT_LOGIN)*24*60),60),'00'),'00') "
								+ " )"
								+ " ELSE '00:00' END)END)"
								+ " END WORKDURATION, "
								+ "  CASE  "
								+ "  WHEN ATT_STATUS_ONE='WO' THEN 'Weekly Off'"
								+ "  WHEN ATT_STATUS_ONE='HO' THEN 'Holiday'"
								+ "  WHEN ATT_REG_STATUS_TWO='LV' THEN 'Leave'  "
								+ "  WHEN ATT_REG_STATUS_TWO='TR' THEN 'Travel' "
								+ "  WHEN ATT_REG_STATUS_TWO='RG' THEN 'Regularized' "
								+ "  WHEN ATT_REG_STATUS_TWO='EW' THEN 'ExtraWork'  "
								+ "  WHEN ATT_REG_STATUS_TWO='HL' THEN 'Half Day Leave'"
								+ "  WHEN (ATT_STATUS_ONE='PR' and ATT_STATUS_TWO='IN') THEN 'Present' "
								+ "  WHEN ATT_STATUS_ONE='PR' then DECODE(ATT_STATUS_TWO,'HD','Half Day','DL','Dual Late','LC','Late-Coming','EL','Early-Leaving','PR','Present','AB','Absent', NULL,'Present') "
								+ "  ELSE DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','HO','Holiday') "
								+ "  END NEW_STATUS, "
								+ "  NVL(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), '00:00') SHIFTDURATION,DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','PR','Present') STATUS_ONE,DECODE(ATT_STATUS_TWO,'HD','Half Day','AB','Absent','IN','Intime','DL','Dual Late' "
								+ "  ,'LC','Late-Coming','EL','Early-Leaving') STATUS_TWO "
								+ "  ,Shift_name,SHIFT_ID ,DECODE(IS_APPL_PROCESS,'Y','Application processing','N','NA'),ATT_DATE, ATT_EMP_ID FROM HRMS_DAILY_ATTENDANCE_"
								+ fYear
								+ "  INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_"
								+ fYear
								+ ".ATT_SHIFT_ID) "
								+ " WHERE ATT_EMP_ID= " + empCode;
						if (fYear != tYear) {
							showQuery += " UNION ALL ";
							fYear++;
						} else {
							break;
						}
					}
					showQuery += ")WHERE" + "   ATT_DATE >= TO_DATE('"
							+ fromDate + "', 'DD-MM-YYYY')  "
							+ " AND ATT_DATE<=TO_DATE('" + toDate
							+ "', 'DD-MM-YYYY')";

					if (showallFilterValue != null
							&& showallFilterValue.equals("All")) {

					} else {
						if (selectedStatus != null
								&& !selectedStatus.equals("'NA'")) {
							showQuery += " AND NEW_STATUS IN(" + selectedStatus
									+ ")";
						}
					}
					showQuery += " ORDER BY  TO_DATE(ATT_DATE, 'DD-MM-YYYY') ";
					query = showQuery;
				} else {
					// Called when My Attendance/My Timecard Link is clicked
					String dateQuery = "SELECT TO_CHAR(SYSDATE,'yyyy'),TO_CHAR(SYSDATE,'MM') FROM DUAL";
					dateObj = getSqlModel().getSingleResult(dateQuery);
					if (dateObj != null && dateObj.length > 0) {
						query = "SELECT TO_CHAR(ATT_DATE,'DD-MM-YYYY') ATTENDANCE_DATE ,SUBSTR(TO_CHAR(ATT_DATE, 'DAY'),0,3), "
								// Query for selecting INTIME begins here
								+ "  CASE WHEN (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) "
								+ "  AND TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')< TO_CHAR(ATT_LOGIN, 'HH24:MI')	" 
								+ "  THEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')	  "
								+ "  WHEN (TO_CHAR(ATT_LOGIN, 'HH24:MI')='00:00' OR ATT_LOGIN IS NULL)   "
								+ "  AND  (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) "
								+ "  THEN  NVL(TO_CHAR(ATT_REG_LOGIN, 'HH24:MI'),'00:00')  	 "
								+ "  ELSE NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI'),'00:00')  END INTIME, "							
								+ "  CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) "
								+ "  THEN ( "
								+ "  CASE WHEN TO_char(ATT_REG_LOGOUT, 'HH24:MI')>NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')"
								+ "  THEN TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI') ELSE TO_CHAR(ATT_LOGOUT, 'HH24:MI') END"
								+ "  )"
								+ "  WHEN (TO_CHAR(ATT_LOGOUT, 'HH24:MI')='00:00' OR ATT_LOGOUT IS NULL)   "
								+ "  THEN  NVL(TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI'),'00:00')  	 "
								+ "  ELSE NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')  END OUTTIME, "
								+ " CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) " 
								+ " AND TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')> NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')   "
								+ " THEN   TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
								+ " ( " 
								+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"
								+ " AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') "
								+ " ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
								+ " ))*24),'00')|| ':' || "
								+ " TO_CHAR(MOD(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
								+ " CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
								+ "  LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') )"
								+ " ELSE ATT_REG_LOGIN END)*24*60),60),'00') "
								+ " ELSE  "
								+ " (CASE WHEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00'"
								+ " THEN (  TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')-"
								+ " ("
								+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"
								+ " THEN TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
								+ " ) )*24),'00')"
								+ " || ':' || "
								+ " TO_CHAR(MOD(FLOOR((GREATEST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'))-CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' "
								+ " THEN LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
								+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ) ELSE ATT_REG_LOGIN END)*24*60),60),'00')"
								+ " )  ELSE  (CASE  WHEN TO_CHAR(ATT_LOGOUT, 'HH24:MI')!='00:00' AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00'"
								+ " THEN (NVL(TO_CHAR(FLOOR((ATT_LOGOUT - ATT_LOGIN)*24),'00'),'00')|| ':' ||" 
								+ " NVL( TO_CHAR(MOD(FLOOR((ATT_LOGOUT-ATT_LOGIN)*24*60),60),'00'),'00') "
								+ " )"
								+ " ELSE '00:00' END)END)"
								+ " END WORKDURATION, "
								+ "  CASE  "
								+ "  WHEN ATT_STATUS_ONE='WO' THEN 'Weekly Off' " 
								+ "  WHEN ATT_STATUS_ONE='HO' THEN 'Holiday' "
								+ "  WHEN ATT_REG_STATUS_TWO='LV' THEN 'Leave'  " 
								+ "  WHEN ATT_REG_STATUS_TWO='TR' THEN 'Travel' " 
								+ "  WHEN ATT_REG_STATUS_TWO='RG' THEN 'Regularized' "
								+ "  WHEN ATT_REG_STATUS_TWO='EW' THEN 'ExtraWork'  " 
								+ "  WHEN ATT_REG_STATUS_TWO='HL' THEN 'Half Day Leave'"
								+ "  WHEN (ATT_STATUS_ONE='PR' AND ATT_STATUS_TWO='IN') THEN 'Present' "
								+ "  WHEN ATT_STATUS_ONE='PR' THEN DECODE(ATT_STATUS_TWO,'HD','Half Day','DL','Dual Late','LC','Late-Coming','EL','Early-Leaving','PR','Present','AB','Absent', NULL,'Present') "
								+ "  ELSE DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','HO','Holiday') "
								+ "  END NEW_STATUS, "
								+ "  NVL(TO_CHAR(SFT_WORK_HRS, 'HH24:MI'), '00:00') SHIFTDURATION,DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','PR','Present') STATUS_ONE,DECODE(ATT_STATUS_TWO,'HD','Half Day','AB','Absent','IN','Intime','DL','Dual Late' "
								+ "  ,'LC','Late-Coming','EL','Early-Leaving') STATUS_TWO "
								+ "  ,Shift_name,SHIFT_ID ,DECODE(IS_APPL_PROCESS,'Y','Application processing','N','NA'),ATT_DATE, ATT_EMP_ID FROM HRMS_DAILY_ATTENDANCE_"
								+ String.valueOf(dateObj[0][0])
								+ "  INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_"
								+ String.valueOf(dateObj[0][0])
								+ ".ATT_SHIFT_ID) "
								+ " WHERE ATT_EMP_ID= "
								+ empCode
								+ " AND TO_char(ATT_DATE, 'MM')='"
								+ String.valueOf(dateObj[0][1]) + "' ";
						query += " ORDER BY  TO_DATE(ATT_DATE, 'DD-MM-YYYY')";
					}
				}
				data = getSqlModel().getSingleResult(query);				
					if (data != null && data.length > 0) {
						for (int i = 0; i < data.length; i++) {
						String inTime = String.valueOf(data[i][2]);
						String outTime = String.valueOf(data[i][3]);
						loginTimeInDate = simpleDate.parse(inTime);
						logoutTimeInDate = simpleDate.parse(outTime);
						Calendar workingHrsCal = Calendar.getInstance();
						workingHrsCal.setTime(logoutTimeInDate);

						workingHrsCal.add(Calendar.HOUR, -loginTimeInDate
								.getHours());
						workingHrsCal.add(Calendar.MINUTE, -loginTimeInDate
								.getMinutes());
						workingHrsCal.add(Calendar.SECOND, -loginTimeInDate
								.getSeconds());
						if(inTime.equals("00:00") && (!(outTime.equals("00:00")))){
							data[i][4] ="00:00";
						}else if((!(inTime.equals("00:00")))&& outTime.equals("00:00")){
							data[i][4] ="00:00";
						}else if(inTime.equals(outTime)){
							data[i][4] ="00:00";
						}
						else{
							data[i][4] = simpleDate.format(workingHrsCal
									.getTime());
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
	}

	public String[] getMyTeamEmpCode(String loginEmpId) {
		String[] teamEmpCode = null;
		String query = "SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE  EMP_ID IN (SELECT HRMS_EMP_OFFC.EMP_ID"
				+ " FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' AND HRMS_EMP_OFFC.EMP_ID<>"
				+ loginEmpId
				+ " CONNECT BY PRIOR  HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER"
				+ " START WITH HRMS_EMP_OFFC.EMP_ID="
				+ loginEmpId
				+ ")"
				+ " ORDER BY EMP_ID";
		Object[][] teamObj = getSqlModel().getSingleResult(query);
		if (teamObj != null && teamObj.length > 0) {
			teamEmpCode = new String[teamObj.length];
			for (int i = 0; i < teamObj.length; i++) {
				teamEmpCode[i] = String.valueOf(teamObj[i][0]);
			}
		}
		return teamEmpCode;
	}

	public Object[][] getAttendanceRegularized(String empCode,
			String loginempcode, String myAttendRegulAppliedstatus,
			String myAttendRegulApprovestatus,
			String myAttendRegulRejectedstatus, String regularizationYear,
			MypageProcessManagerAlerts bean) {
		Object data[][] = null;
		Object yearobj[][] = null;
		try {
			LinkedHashMap<String, String> yearMap = new LinkedHashMap<String, String>();
			String yearquery = " SELECT DISTINCT TO_CHAR(SWIPE_REG_DTL_DATE,'YYYY')  FROM  HRMS_SWIPE_REG_DTL "
					+ " INNER JOIN  HRMS_SWIPE_REG_HDR ON (HRMS_SWIPE_REG_HDR.SWIPE_REG_ID= HRMS_SWIPE_REG_DTL.SWIPE_REG_ID)"
					+ " WHERE SWIPE_REG_EMP_ID="
					+ bean.getUserEmpId()
					+ " ORDER BY TO_CHAR(SWIPE_REG_DTL_DATE,'YYYY')  DESC ";
			yearobj = getSqlModel().getSingleResult(yearquery);
			if (yearobj != null && yearobj.length > 0) {
				for (int i = 0; i < yearobj.length; i++) {
					yearMap.put(String.valueOf(yearobj[i][0]), String
							.valueOf(yearobj[i][0]));
				}
				bean.setYearMap(yearMap);
			} else {
				yearquery = "SELECT TO_CHAR(SYSDATE,'yyyy')FROM DUAL";
				yearobj = getSqlModel().getSingleResult(yearquery);
				if (yearobj != null && yearobj.length > 0) {
					yearMap.put(String.valueOf(yearobj[0][0]), String
							.valueOf(yearobj[0][0]));
				}
				bean.setYearMap(yearMap);
			}
			if (regularizationYear == null) {
				yearquery = "SELECT TO_CHAR(SYSDATE,'yyyy')FROM DUAL";
				yearobj = getSqlModel().getSingleResult(yearquery);
				regularizationYear = (yearobj != null && yearobj.length > 0) ? String
						.valueOf(yearobj[0][0])
						: null;
			}
			Object dateObj[][] = null;

			if (empCode != null && !empCode.equals("")) {
				empCode = empCode;
			} else {
				empCode = loginempcode;
			}
			String selectedStatus = "'NA'";
			if (myAttendRegulAppliedstatus != null
					&& myAttendRegulAppliedstatus.length() > 0) {
				selectedStatus += "," + "'" + myAttendRegulAppliedstatus + "'";
			}
			if (myAttendRegulApprovestatus != null
					&& myAttendRegulApprovestatus.length() > 0) {
				selectedStatus += "," + "'" + myAttendRegulApprovestatus + "'";
			}
			if (myAttendRegulRejectedstatus != null
					&& myAttendRegulRejectedstatus.length() > 0) {
				selectedStatus += "," + "'" + myAttendRegulRejectedstatus + "'";
			}
			String query = "  SELECT TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY'),	DECODE(HRMS_SWIPE_REG_DTL.SWIPE_REG_REASON,'1','FORGOT TO BRING ACCESS CARD','2','FORGOT TO LOGIN/LOGOUT','3','ACCESS CARD NOT ISSUED','4','SPECIAL SANCTION','5','SWIPE SYSTEM NOT WORKING','6','OUTDOOR VISIT','7','LATE REGULARIZATION') "
					+ "	,TO_CHAR(SWIPE_REG_APPLICATION_DATE,'DD-MM-YYYY')   AS APPL_DATE "
					+ " ,CASE WHEN SWIPE_REG_STATUS='P' THEN '' ELSE  TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)   END "
					+ " , DECODE(SWIPE_REG_STATUS,'P','APPLIED','A','APPROVED','R','REJECTED') "
					+ " FROM HRMS_SWIPE_REG_DTL "
					+ " INNER JOIN HRMS_SWIPE_REG_HDR ON( HRMS_SWIPE_REG_HDR.SWIPE_REG_ID =HRMS_SWIPE_REG_DTL.SWIPE_REG_ID ) "
					+ " INNER JOIN HRMS_EMP_OFFC ON ( HRMS_EMP_OFFC.EMP_ID = HRMS_SWIPE_REG_HDR.SWIPE_REG_APPROVER)  "
					+ " LEFT JOIN HRMS_SWIPE_REG_PATH ON( HRMS_SWIPE_REG_PATH.SWIPE_REG_ID = HRMS_SWIPE_REG_HDR.SWIPE_REG_ID) "
					+ "	WHERE SWIPE_REG_EMP_ID = " + empCode;

			if (regularizationYear != null && regularizationYear.length() > 0) {
				query += " AND " + " TO_CHAR(SWIPE_REG_DTL_DATE,'YYYY')='"
						+ regularizationYear + "' ";
			}
			if (selectedStatus != null && !selectedStatus.equals("'NA'")) {
				query += " AND SWIPE_REG_STATUS IN(" + selectedStatus + ")";
			}
			query += " AND SWIPE_REG_STATUS NOT IN ('B') ";
			query += "order by SWIPE_REG_DTL_DATE desc ";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getNameOfaDay(int year, int month, int day) {
		String finalReault = "";
		try {
			Calendar cal = new GregorianCalendar(year, month - 1, day);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			finalReault = getDayName(dayOfWeek);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalReault;
	}

	public void getSearch(String searchType, String searchString,
			HttpServletRequest request, String profileCode, String mypage,
			MypageProcessManagerAlerts employeePortal) {

		try {
			if (searchType != null) {
				if (searchType.equals("emp")) {
					employeePortal.setEmpDataAvailable(true);
					employeePortal.setIsmenuDataAvailable(false);
					Object[] searchEmpStrObj = new Object[1];
					searchEmpStrObj[0] = "%" + searchString + "%";
					//Query Updated By Prajakta B(5 June 2013) 
					String searchEmpQuery = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,"
							+ " CENTER_NAME, HRMS_RANK.RANK_NAME,' ', "
							+ " NVL(ADD_PH1,''),NVL(ADD_PH2,''),NVL(ADD_EXTENSION,0),NVL(ADD_FAX,''), NVL(ADD_MOBILE,''),NVL(ADD_EMAIL,' '),"
							+ " HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
							// + " LEFT JOIN HRMS_LOCATION
							// ON(HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY)
							// "
							+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE ='P') "
							+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  "
							+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
							+ " WHERE  "
							+ " UPPER(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME) like UPPER(?) "
							+ " AND EMP_STATUS='S' "; // AND
					// ROWNUM<=25";
					Object empSearchObj[][] = getSqlModel().getSingleResult(
							searchEmpQuery, searchEmpStrObj);
					Object pagingempSearchObj[][] = null;
					if (empSearchObj != null && empSearchObj.length > 0) {
						String[] pageIndex = Utility.doPaging(employeePortal
								.getMyPage(), empSearchObj.length, 20);
						if (pageIndex == null) {
							pageIndex[0] = "0";
							pageIndex[1] = "20";
							pageIndex[2] = "1";
							pageIndex[3] = "1";
							pageIndex[4] = "";
						}
						request.setAttribute("totalPage", Integer
								.parseInt(String.valueOf(pageIndex[2])));
						request.setAttribute("pageNo", Integer.parseInt(String
								.valueOf(pageIndex[3])));
						if (pageIndex[4].equals("1"))
							employeePortal.setMyPage("1");
						int length = 0;
						try {
							length = Integer.parseInt(pageIndex[1])
									- Integer.parseInt(pageIndex[0]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						int count = 0;
						pagingempSearchObj = new Object[length][12];
						for (int i = Integer.parseInt(pageIndex[0]); i < Integer
								.parseInt(pageIndex[1]); i++) {
							employeePortal.setPageFlag(true);
							pagingempSearchObj[count][0] = empSearchObj[i][0];
							pagingempSearchObj[count][1] = empSearchObj[i][1];
							pagingempSearchObj[count][3] = empSearchObj[i][3];
							pagingempSearchObj[count][5] = empSearchObj[i][5];
							pagingempSearchObj[count][6] = empSearchObj[i][6];
							pagingempSearchObj[count][7] = empSearchObj[i][7];
							pagingempSearchObj[count][9] = empSearchObj[i][9];
							pagingempSearchObj[count][10] = empSearchObj[i][10];
							pagingempSearchObj[count][11] = empSearchObj[i][11];
							count++;
						}

						request
								.setAttribute("empSearchObj",
										pagingempSearchObj);

						request.setAttribute("loopStartIndex", Integer
								.parseInt(pageIndex[0]));

					} else {
						request.setAttribute("empSearchObj", empSearchObj);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for give employee details .
	 * 
	 * @param empCode :-
	 *            empCode .
	 * @param employeePortal :-
	 *            employeePortal .
	 * @param request : -
	 *            request .
	 * @return Object :- Object
	 */
	public Object[][] getEmployeeDetailtRecord(String empCode,
			MypageProcessManagerAlerts employeePortal,
			HttpServletRequest request) {
		Object empDtlObj[][] = null;
		try {
			//Query Updated By Prajakta B(5 June 2013)
			String query = " SELECT EMP_TOKEN , EMP_FNAME||' '||EMP_LNAME ,case when SEND_RECEIVE_BDAY='Y' then TO_CHAR(EMP_DOB,'DD Mon') else '' end , "
					+ "	DECODE(EMP_GENDER,'M','Male','F','Female') "
					+ "	,EMP_BLDGP,TO_CHAR(EMP_REGULAR_DATE,'DD-MON-YYYY'), "
					+ "	DEPT_NAME ,RANK_NAME "
					+ "	,EMP_ROLE,EMP_REPORTING_OFFICER,(SELECT EMP_FNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC "
					+ "	WHERE EMP_ID=(SELECT EMP_REPORTING_OFFICER FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empCode
					+ "))  "
					+ "	AS IMMIDIATE_MANAGER "
					+ "	,CENTER_NAME ,ADD_EMAIL ,ADD_EXTENSION ,'NA' "
					+ "	, ADD_MOBILE ,ADD_PH1, ADD_PH2 ,NVL(HRMS_EMP_OFFC.EMP_PHOTO,'') FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_EMP_PERS ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT) "
					+ "	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_RANK) "
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =HRMS_EMP_OFFC.EMP_CENTER) "
					+ "	LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID =HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P') "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC .EMP_DIV)"
					+ "	WHERE HRMS_EMP_OFFC.EMP_ID="
					+ empCode;

			empDtlObj = getSqlModel().getSingleResult(query);
			if (empDtlObj != null && empDtlObj.length > 0) {
				String photo = String.valueOf(empDtlObj[0][18]);
				if (!photo.equals("")) {
					request.setAttribute("empPhotograph", String
							.valueOf(empDtlObj[0][18]));
				} else {
					request.setAttribute("empPhotograph", "empimage.gif");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empDtlObj;
	}

	public void setModuleNmae(MypageProcessManagerAlerts bean,
			HttpServletRequest request) {
		Object dataObj[][] = null;
		try {
			
			TreeMap<String, String> moduleMap = new TreeMap<String, String>();
			String query = " SELECT DISTINCT ALERT_MODULE,'' FROM  HRMS_ALERT_MESSAGE  WHERE ";
				query +=" ALERT_LINK IS NOT NULL AND ALERT_MODULE IS NOT NULL AND HRMS_ALERT_MESSAGE.ALERT_ACTIVE='Y' " 
				+" AND (ALERT_EMP_ID="+bean.getUserEmpId()+" OR ALERT_MANAGER_ID="+bean.getUserEmpId()+" OR ALTER_ALTERNATEID="+bean.getUserEmpId()+" OR TO_CHAR(','||HRMS_ALERT_MESSAGE.ALERT_CC||',') LIKE '%,"+bean.getUserEmpId()+",%' ) " 
				+" AND SYSDATE-HRMS_ALERT_MESSAGE.ALERT_DATE <= 45  ";
			dataObj = getSqlModel().getSingleResult(query);

			if (dataObj != null && dataObj.length > 0) {
				moduleMap.put("--Select--", "--Select--");
				for (int i = 0; i < dataObj.length; i++) {
					moduleMap.put(String.valueOf(dataObj[i][0]), String
							.valueOf(dataObj[i][0]));
				}
				bean.setModuleMap(moduleMap);
			} else {
				moduleMap.put("--Select--", "--Select--");
				bean.setModuleMap(moduleMap);
			}
			bean.setModuleName(bean.getHiddenModuleName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * System.out.println("bean.getModuleName()-----------------"+bean.getModuleName());
		 * for (int i = 0; i < dataObj.length; i++) {
		 * 
		 * if(bean.getModuleName().equals(String.valueOf(dataObj[i][0]))){
		 * dataObj[i][1]="selected"; } } if(dataObj!=null && dataObj.length>0){
		 * request.setAttribute("moduleNameObj", dataObj); }
		 */

	}

	public void setStatusOnload(MypageProcessManagerAlerts bean,
			String modulName) {
		try {
			// modulName ="Loan";
			TreeMap<String, String> statusMap = new TreeMap<String, String>();

			String query = " SELECT DISTINCT ALERT_STATUS FROM  HRMS_ALERT_MESSAGE ";

			query += " WHERE "
				+ "     (ALERT_EMP_ID="
				+ bean.getUserEmpId()
				+ " or ALERT_MANAGER_ID="
				+  bean.getUserEmpId()
				+ " or ALTER_ALTERNATEID="
				+  bean.getUserEmpId()
				+ " or TO_CHAR(','||HRMS_ALERT_MESSAGE.ALERT_CC||',') like '%,"+ bean.getUserEmpId()+",%'"
				+	" )";
			// if (modulName != null) {
			query += " AND ALERT_LINK IS NOT NULL AND UPPER(ALERT_MODULE)=UPPER('" + modulName + "')";
			// }
			query += " AND ALERT_STATUS IS NOT NULL ";

			Object[][] dataObj = getSqlModel().getSingleResult(query);
			bean.setStatusMap(null);
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					statusMap.put("--Select--", "--Select--");
					statusMap.put(String.valueOf(dataObj[i][0]), String
							.valueOf(dataObj[i][0]));
				}
				bean.setStatusMap(statusMap);
			} else {
				statusMap.put("--Select--", "--Select--");

				bean.setStatusMap(statusMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] setStatus(MypageProcessManagerAlerts bean,
			String modulName) {
		Object[][] dataObj = null;
		try {
			// modulName ="Loan";
			TreeMap<String, String> statusMap = new TreeMap<String, String>();

			String query = " SELECT DISTINCT ALERT_STATUS FROM  HRMS_ALERT_MESSAGE ";

			query += " WHERE 1=1 ";

			if (modulName != null) {
				query += " AND UPPER(ALERT_MODULE)=UPPER('" + modulName + "')";
			}
			query += " AND ALERT_STATUS IS NOT NULL ";

			dataObj = getSqlModel().getSingleResult(query);
			/*
			 * bean.setStatusMap(null); if (dataObj != null && dataObj.length >
			 * 0) { for (int i = 0; i < dataObj.length; i++) {
			 * statusMap.put(String.valueOf(dataObj[i][0]), String
			 * .valueOf(dataObj[i][0])); } bean.setStatusMap(statusMap); } else {
			 * statusMap.put("Pending", "Pending");
			 * bean.setStatusMap(statusMap); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataObj;
	}

	public void applyFilter(String moduleName, String status, String year,
			String searchMessage, MypageProcessManagerAlerts bean,
			String userEmpID, HttpServletRequest request) {
		try {

			String dateQuery = " SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL ";
			Object currentYearObj[][] = getSqlModel()
					.getSingleResult(dateQuery);
			int previousYear;
			Object[][] alertsObj = null;
			try {

				String approvalAlertsSql = " SELECT HRMS_ALERT_MESSAGE.ALERT_ID, HRMS_ALERT_MESSAGE.ALERT_SUBJECT, HRMS_ALERT_MESSAGE.ALERT_EMP_ID, HRMS_ALERT_MESSAGE.ALERT_MESSAGE, HRMS_ALERT_MESSAGE.ALERT_DATE , "
						+ " TRIM(HRMS_ALERT_MESSAGE.ALERT_MODULE), DECODE(HRMS_ALERT_MESSAGE.ALERT_MESSAGE_TYPE, 'I', 'INFORMATION', 'A', 'ALERT') ALERT_MESSAGE_TYPE, HRMS_ALERT_MESSAGE.ALERT_APPLICATION_ID, "
						+ " HRMS_ALERT_MESSAGE.ALERT_LEVEL ,HRMS_ALERT_MESSAGE.ALERT_APPL_PARAMS,HRMS_ALERT_MESSAGE.ALERT_LINK "
						+ " , HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
						+ " FROM HRMS_ALERT_MESSAGE  "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =HRMS_ALERT_MESSAGE.ALERT_FROM) "
						+ " WHERE 1=1   "
						+ " and    (ALERT_EMP_ID="
						+ userEmpID
						+ " or ALERT_MANAGER_ID="
						+ userEmpID
						+ " or ALTER_ALTERNATEID="
						+ userEmpID
						+ " OR TO_CHAR(','||HRMS_ALERT_MESSAGE.ALERT_CC||',') like '%,"+userEmpID+",%'"
						+"  )"
						+ "  AND HRMS_ALERT_MESSAGE.ALERT_MODULE NOT LIKE 'Appraisal' AND ALERT_LINK IS NOT NULL   ";

				if (!moduleName.equals("")) {
					approvalAlertsSql += " AND UPPER(ALERT_MODULE)='"
							+ moduleName.trim().toUpperCase() + "' ";
				}

				if (!status.equals("")) {
					approvalAlertsSql += " AND UPPER(ALERT_STATUS)='"
							+ status.trim().toUpperCase() + "' ";
				}

				if (!year.equals("")) {

					if (year.equals("CY")) {
						year = String.valueOf(currentYearObj[0][0]);
					}
					if (year.equals("PY")) {
						previousYear = Integer.parseInt(String
								.valueOf(currentYearObj[0][0])) - 1;
						year = String.valueOf(previousYear);
					}
					approvalAlertsSql += " AND to_CHAR(HRMS_ALERT_MESSAGE.ALERT_DATE,'yyyy')='"
							+ year + "' ";
				}

				approvalAlertsSql += " ORDER BY HRMS_ALERT_MESSAGE.ALERT_DATE DESC ";

				Object[][] processAlertsObj = getSqlModel().getSingleResult(
						approvalAlertsSql);

				if (processAlertsObj != null && processAlertsObj.length > 0) {
					alertsObj = new Object[processAlertsObj.length][11];
					for (int i = 0; i < processAlertsObj.length; i++) {
						alertsObj[i][0] = PPEncrypter.encrypt(String
								.valueOf(processAlertsObj[i][0])); // ALERT_ID
						alertsObj[i][1] = String
								.valueOf(processAlertsObj[i][1]); // ALERT_SUBJECT
						alertsObj[i][2] = String
								.valueOf(processAlertsObj[i][2]); // ALERT_EMP_ID
						alertsObj[i][3] = String
								.valueOf(processAlertsObj[i][3]); // ALERT_MESSAGE
						alertsObj[i][4] = String
								.valueOf(processAlertsObj[i][4]); // ALERT_DATE
						alertsObj[i][5] = String
								.valueOf(processAlertsObj[i][5]); // ALERT_MODULE
						alertsObj[i][6] = String
								.valueOf(processAlertsObj[i][6]); // ALERT_MESSAGE_TYPE
						alertsObj[i][7] = String
								.valueOf(processAlertsObj[i][7]); // ALERT_APPLICATION_ID
						alertsObj[i][8] = String
								.valueOf(processAlertsObj[i][8]); // ALERT_LEVEL
						String alertModule = String.valueOf(
								processAlertsObj[i][5]).replace(" ", "")
								.toLowerCase(); // ALERT_MODULE
						if (!String.valueOf(processAlertsObj[i][9]).equals(
								"null")) {
							alertModule += "_new";
							alertsObj[i][9] = String
									.valueOf(processAlertsObj[i][10]) // ALERT_LINK
									+ "?"
									+ String.valueOf(processAlertsObj[i][9])
									+ "&src=mymessages";
						}

						alertsObj[i][10] = String
								.valueOf(processAlertsObj[i][11]); // Alert
						// from id
					}
				}
			} catch (Exception e) {
				logger.error(e);
			}
			request.setAttribute("alertsObj", alertsObj);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int setRecordCountForInbox(HttpServletRequest request,
			String loginUserId) {
		int count = 0;
		try {
			// UPPER(ALERT_STATUS)!='DRAFT' AND
			String query = "SELECT COUNT(*) FROM HRMS_ALERT_MESSAGE "
					+ " WHERE   (ALERT_EMP_ID="
					+ loginUserId
					+ " OR ALERT_MANAGER_ID="
					+ loginUserId
					+ " OR ALTER_ALTERNATEID="
					+ loginUserId
					+ "   OR TO_CHAR(','||HRMS_ALERT_MESSAGE.ALERT_CC||',') like '%,"+loginUserId+",%'"
					+"  ) AND HRMS_ALERT_MESSAGE.ALERT_ACTIVE='Y'   AND ALERT_LINK IS NOT NULL AND UPPER(ALERT_STATUS)!='DRAFT' AND SYSDATE - HRMS_ALERT_MESSAGE.ALERT_DATE <= 15";

			Object noOfRecordObj[][] = getSqlModel().getSingleResult(query);
			if (noOfRecordObj != null && noOfRecordObj.length > 0) {
				count = Integer.parseInt(String.valueOf(noOfRecordObj[0][0]));
			}

		} catch (Exception e) {
			count = 0;
			e.printStackTrace();
		}
		request.setAttribute("InboxCount", count);
		return count;
	}

	public int setRecordCountForDraft(HttpServletRequest request,
			String loginUserId) {
		int count = 0;
		try {

			String query = "SELECT COUNT(*) FROM HRMS_ALERT_MESSAGE "
					+ " WHERE UPPER(ALERT_STATUS)='DRAFT'  AND    (ALERT_EMP_ID="
					+ loginUserId
					+ " OR ALERT_MANAGER_ID="
					+ loginUserId
					+ " OR ALTER_ALTERNATEID="
					+ loginUserId
					+ " "
					+ "   OR TO_CHAR(','||HRMS_ALERT_MESSAGE.ALERT_CC||',') like '%,"+loginUserId+",%'"
					+ " ) AND HRMS_ALERT_MESSAGE.ALERT_ACTIVE='Y' AND ALERT_LINK IS NOT NULL AND SYSDATE - HRMS_ALERT_MESSAGE.ALERT_DATE <= 15";

			Object noOfRecordObj[][] = getSqlModel().getSingleResult(query);
			if (noOfRecordObj != null && noOfRecordObj.length > 0) {
				count = Integer.parseInt(String.valueOf(noOfRecordObj[0][0]));
			}

		} catch (Exception e) {
			count = 0;
			e.printStackTrace();
		}
		request.setAttribute("DraftCount", count);
		return count;
	}

	public void currentYearsAllMonthsAndLoginUserInfo(
			MypageProcessManagerAlerts bean) {
		try {
			String currentYearQuery = "SELECT TO_CHAR(SYSDATE,'yyyy') AS CURRENT_YEAR FROM DUAL";
			Object[][] currentYearObj = getSqlModel().getSingleResult(
					currentYearQuery);
			if (currentYearObj != null && currentYearObj.length > 0) {
				bean.setAnnualAttendanceFromMonth("1");
				bean.setAnnualAttendanceFromYear(String
						.valueOf(currentYearObj[0][0]));
				bean.setAnnualAttendanceToMonth("12");
				bean.setAnnualAttendanceToYear(String
						.valueOf(currentYearObj[0][0]));
			}

			String empCode = bean.getUserEmpId();

			String loginUserDataQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME) ,EMP_TOKEN|| ' ' ||'-' || ' ' || HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
					+ " HRMS_EMP_OFFC.EMP_LNAME ,EMP_TOKEN|| ' ' ||'-' || ' ' || HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
					+ "	HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + empCode;
			Object[][] loginUserDataObj = getSqlModel().getSingleResult(
					loginUserDataQuery);
			if (loginUserDataObj != null && loginUserDataObj.length > 0) {
				bean.setAnnualAttendanceEmpCode(Utility.checkNull(String
						.valueOf(loginUserDataObj[0][0])));
				bean.setAnnualAttendanceEmpToken(Utility.checkNull(String
						.valueOf(loginUserDataObj[0][1])));
				bean.setAnnualAttendanceEmpName(Utility.checkNull(String
						.valueOf(loginUserDataObj[0][3])));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] getAnnualAttendanceData(MypageProcessManagerAlerts bean,
			String annualAttendanceFromMonth, String annualAttendanceFromYear,
			String annualAttendanceToMonth, String annualAttendanceToYear,
			String annualAttendanceEmpCode) {
		Object[][] annualAttendaceDataObj = null;
		try {

			TreeMap yearMap = new TreeMap();

			String yearquery = "SELECT TO_CHAR(SYSDATE,'yyyy')FROM DUAL";
			Object[][] yearobj = getSqlModel().getSingleResult(yearquery);

			if (yearobj != null && yearobj.length > 0) {
				yearMap.put(String.valueOf(yearobj[0][0]), String
						.valueOf(yearobj[0][0]));
			}
			bean.setAttendanceFromyearMap(yearMap);
			bean.setAttendanceToyearMap(yearMap);

			String annualAttendanceDataQuery = "SELECT CASE "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 1 THEN 'January' "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 2 THEN 'February' "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 3 THEN 'March'  "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 4 THEN 'April'  "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 5 THEN 'May'  "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 6 THEN 'June'  "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 7 THEN 'July'  "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 8 THEN 'August'  "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 9 THEN 'September' "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 10 THEN 'October'  "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 11 THEN 'November'  "
					+ " WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH = 12 THEN 'December'  "
					+ " ELSE '---' END AS MONTH_NAME,  "
					+ " CASE WHEN HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_YEAR IS NULL THEN 0 ELSE HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_YEAR END AS YEAR_NAME, "
					+ " TO_CHAR(LAST_DAY(TO_DATE (HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH||'-'||HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_YEAR,'MM-YYYY')),'DD')  AS MONTH_DAYS , "
					+ " NVL(HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_DAYS, 0) AS PRESENT_DAYS,  "
					+ " HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_PAID_LEVS AS LEAVE_DAYS, "
					+ " HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_UNPAID_LEVS AS ABSENT_DAYS, "
					+ " NVL(HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_WOFF, 0) AS WEEKLY_OFF,  "
					+ " NVL(HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_HOLIDAY, 0) AS HOLIDAY  "
					+ " FROM HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ""
					+ " WHERE HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_EMP_ID = "
					+ annualAttendanceEmpCode
					+ " AND (HRMS_MONTH_ATTENDANCE_"
					+ annualAttendanceFromYear
					+ ".ATTN_MONTH BETWEEN "
					+ annualAttendanceFromMonth
					+ " AND "
					+ annualAttendanceToMonth + " ) ";
			/*
			 * + " AND
			 * (HRMS_MONTH_ATTENDANCE_"+annualAttendanceFromYear+".ATTN_YEAR
			 * BETWEEN " + annualAttendanceFromYear + " AND " +
			 * annualAttendanceToYear + ") "
			 */
			if (!annualAttendanceFromYear.equals(annualAttendanceToYear)) {
				annualAttendanceDataQuery = annualAttendanceDataQuery
						+ " UNION ALL "
						+ " SELECT CASE "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 1 THEN 'January' "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 2 THEN 'February' "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 3 THEN 'March'  "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 4 THEN 'April'  "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 5 THEN 'May'  "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 6 THEN 'June'  "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 7 THEN 'July'  "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 8 THEN 'August'  "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 9 THEN 'September' "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 10 THEN 'October'  "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 11 THEN 'November'  "
						+ " WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH = 12 THEN 'December'  "
						+ " ELSE '---' END AS MONTH_NAME,  "
						+ " CASE WHEN HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_YEAR IS NULL THEN 0 ELSE HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_YEAR END AS YEAR_NAME, "
						+ " TO_CHAR(LAST_DAY(TO_DATE (HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_MONTH||'-'||HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_YEAR,'MM-YYYY')),'DD')  AS MONTH_DAYS , "
						+ " NVL(HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_DAYS, 0) AS PRESENT_DAYS,  "
						+ " HRMS_MONTH_ATTENDANCE_" + annualAttendanceToYear
						+ ".ATTN_PAID_LEVS AS LEAVE_DAYS, "
						+ " HRMS_MONTH_ATTENDANCE_" + annualAttendanceToYear
						+ ".ATTN_UNPAID_LEVS AS ABSENT_DAYS, "
						+ " NVL(HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_WOFF, 0) AS WEEKLY_OFF,  "
						+ " NVL(HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear
						+ ".ATTN_HOLIDAY, 0) AS HOLIDAY  "
						+ " FROM HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear + ""
						+ " WHERE HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear + ".ATTN_EMP_ID = "
						+ annualAttendanceEmpCode
						+ " AND (HRMS_MONTH_ATTENDANCE_"
						+ annualAttendanceToYear + ".ATTN_MONTH BETWEEN "
						+ annualAttendanceFromMonth + " AND "
						+ annualAttendanceToMonth + " ) ";
				/*
				 * + " AND
				 * (HRMS_MONTH_ATTENDANCE_"+annualAttendanceToYear+".ATTN_YEAR
				 * BETWEEN " + annualAttendanceToYear + " AND " +
				 * annualAttendanceToYear + ") ";
				 */
			}

			annualAttendaceDataObj = getSqlModel().getSingleResult(
					annualAttendanceDataQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return annualAttendaceDataObj;
	}

	public Object[][] getNationalHolidays(MypageProcessManagerAlerts bean,
			String holidayYear) {
		Object[][] nationalHolidayObj = null;
		try {

			String nationalHolidayQuery = "SELECT ROWNUM ,TO_CHAR(HOLI_DATE,'DD-MM-YYYY') , SUBSTR(TO_CHAR(HOLI_DATE,'Day'),0,3), HOLI_DESC "
					+ " FROM HRMS_HOLIDAY WHERE HOLI_TYPE='N' AND IS_ACTIVE='Y'";
			if (holidayYear != null) {
				nationalHolidayQuery = nationalHolidayQuery
						+ "AND TO_CHAR(HOLI_DATE,'YYYY') IN (" + holidayYear
						+ ")";
			}
			nationalHolidayObj = getSqlModel().getSingleResult(
					nationalHolidayQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nationalHolidayObj;
	}

	public Object[][] getLocationSpecificHolidaysObj(
			MypageProcessManagerAlerts bean, String holidayYear,
			String holidaySelectedLocation) {
		Object[][] locationSpecificHolidaysObj = null;
		try {

			String locationSpecificHolidaysQuery = "SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') ,SUBSTR(TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'Day'),0,3), HRMS_HOLIDAY.HOLI_DESC, ROWNUM, HRMS_CENTER.CENTER_NAME ,HRMS_CENTER.CENTER_ID "
					+ " FROM HRMS_HOLIDAY_BRANCH "
					+ " INNER JOIN  HRMS_HOLIDAY ON (HRMS_HOLIDAY_BRANCH.HOLI_DATE = HRMS_HOLIDAY.HOLI_DATE) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID =HRMS_HOLIDAY_BRANCH.CENTER_ID) "
					+ " WHERE TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'YYYY') IN ("
					+ holidayYear + ")";

			if (holidaySelectedLocation == (null)
					|| holidaySelectedLocation == "-1") {
				locationSpecificHolidaysQuery += " AND HRMS_CENTER.CENTER_ID = ( SELECT HRMS_EMP_OFFC.EMP_CENTER FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID="
						+ bean.getUserEmpId() + ")";
			} else {
				locationSpecificHolidaysQuery += " AND HRMS_CENTER.CENTER_ID = "
						+ holidaySelectedLocation;
			}
			locationSpecificHolidaysQuery += "ORDER BY HRMS_HOLIDAY_BRANCH.HOLI_DATE";
			locationSpecificHolidaysObj = getSqlModel().getSingleResult(
					locationSpecificHolidaysQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locationSpecificHolidaysObj;
	}

	public void setLocationDropDownValue(MypageProcessManagerAlerts bean,
			String centerCode) {
		try {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

			String sqlQuery = " SELECT center_id,HRMS_CENTER.CENTER_NAME  FROM  hrms_center "
					+ " where center_id=" + centerCode;

			Object data[][] = getSqlModel().getSingleResult(sqlQuery);
			if (data != null && data.length > 0) {
				map.put(String.valueOf(data[0][0]), String.valueOf(data[0][0]));
			}
			String selectSql = " SELECT CENTER_ID, INITCAP(CENTER_NAME) FROM HRMS_CENTER  ORDER BY UPPER(CENTER_NAME)  ";
			Object dataObj[][] = getSqlModel().getSingleResult(selectSql);
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					map.put(String.valueOf(dataObj[i][0]), String
							.valueOf(dataObj[i][1]));
				}
			}
			bean.setHolidayLocationNameList(map);
			map = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLocationDropDownValueOnload(MypageProcessManagerAlerts bean) {
		try {

			bean.setHolidayLocationNameList(null);

			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

			String sqlQuery = " SELECT HRMS_EMP_OFFC.EMP_CENTER ,HRMS_CENTER.CENTER_NAME "
					+ " FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID =HRMS_EMP_OFFC.EMP_CENTER)"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID=" + bean.getUserEmpId();
			Object data[][] = getSqlModel().getSingleResult(sqlQuery);
			if (data != null && data.length > 0) {
				map.put(String.valueOf(data[0][0]), String.valueOf(data[0][0]));
			}
			String selectSql = " SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER  ORDER BY UPPER(CENTER_NAME)  ";
			Object dataObj[][] = getSqlModel().getSingleResult(selectSql);
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					map.put(String.valueOf(dataObj[i][0]), String
							.valueOf(dataObj[i][1]));
				}
			}
			bean.setHolidayLocationNameList(map);
			map = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayModuleStatus(MypageProcessManagerAlerts bean,
			String moduleName) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getLeaveHistory(String empCode, String leaveYear,
			String appliedStatus, String approvedStatus, String rejectedStatus,
			String cancelledStatus, String teamLeaveFlg, String showMyFlg, 
			String showMyTeamFlag, HttpServletRequest request,
			MypageProcessManagerAlerts bean, String fromLeaveDate, String toLeaveDate) {
		try {
			String myTeamStr = "0";
		/*	LinkedHashMap yearMap = new LinkedHashMap();

			String yearquery = " select  distinct to_CHAR(LEAVE_TO_DATE,'YYYY') from HRMS_LEAVE_dtl  where EMP_ID="
					+ bean.getUserEmpId()
					+ " order by to_CHAR(LEAVE_TO_DATE,'YYYY') desc ";

			Object[][] yearobj = getSqlModel().getSingleResult(yearquery);
			if (yearobj != null && yearobj.length > 0) {
				for (int i = 0; i < yearobj.length; i++) {
					yearMap.put(String.valueOf(yearobj[i][0]), String
							.valueOf(yearobj[i][0]));
				}
				bean.setLeaveYearMap(yearMap);
			} else {*/
			if (leaveYear == null || leaveYear.equals("")) {
				String yearquery = "SELECT TO_CHAR(SYSDATE,'yyyy')FROM DUAL";
				Object[][] yearobj = getSqlModel().getSingleResult(yearquery);

				if (yearobj != null && yearobj.length > 0) {
					leaveYear = (String.valueOf(yearobj[0][0]));
					bean.setLeaveYear(String.valueOf(yearobj[0][0]));
				}		
			}
			if (fromLeaveDate != null && !fromLeaveDate.equals("")) {
				bean.setFromLeaveDate(fromLeaveDate);
			} else {
				bean.setFromLeaveDate("");
			}
			if (toLeaveDate != null && !toLeaveDate.equals("")) {
				bean.setToLeaveDate(toLeaveDate);
			} else {
				bean.setToLeaveDate("");
			}
			
			
			if (teamLeaveFlg.equals("true")) {				
				String[] myTeamCode = getMyTeamEmpCode(bean.getUserEmpId());
				if (myTeamCode != null && !myTeamCode.equals("")) {
					for (int i = 0; i < myTeamCode.length; i++) {
						if (i == myTeamCode.length) {
							myTeamStr += myTeamCode[i];
						} else {
							myTeamStr += "," + myTeamCode[i];
						}
					}
				}
			}
			String status = "'NA'";
			if (appliedStatus != null && appliedStatus.length() > 0 && appliedStatus.equals("true") ) {
				status += "," + "'P','C'";
			}
			if (approvedStatus != null && approvedStatus.length() > 0 && approvedStatus.equals("true")) {
				status += "," + "'A','X'";
			}
			if (rejectedStatus != null && rejectedStatus.length() > 0 && rejectedStatus.equals("true")) {
				status += "," + "'R'";
			}
			if (cancelledStatus != null && cancelledStatus.length() > 0 && cancelledStatus.equals("true")) {
				status += "," + "'N','Z'";
			}
			String query = " SELECT NVL(E2.EMP_FNAME,' ')||' '||NVL(E2.EMP_MNAME,' ')||' '||NVL(E2.EMP_LNAME,' ')EMPNAME,"
							+ " TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),NVL(LEAVE_DAYS,0),"
							+ " NVL(LEAVE_ABBR,''),TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'DD-MM-YYYY'), "
							+ " NVL(E1.EMP_FNAME,' ')||' '||NVL(E1.EMP_MNAME,' ')||' '||NVL(E1.EMP_LNAME,' ') , "
							+ " NVL(DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'P','Applied','N','Cancelled','R','Rejected','A','Approved','C','Applied' ,'X','Approved','Z','Cancelled'),'')"
							+ " FROM HRMS_LEAVE_HDR "
							+ " INNER JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID=HRMS_LEAVE_HDR.EMP_ID) "
							+ " LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_LEAVE_HDR.APPROVED_BY) "
							+ " INNER JOIN HRMS_LEAVE_DTL ON(HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE=HRMS_LEAVE_HDR.LEAVE_APPL_CODE) "
							+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_DTL.LEAVE_CODE) ";
					if(showMyFlg.equals("false")&& teamLeaveFlg.equals("true") && showMyTeamFlag.equals("false")){
						query+= " WHERE HRMS_LEAVE_HDR.EMP_ID=" + empCode + " ";
					}else if(showMyFlg.equals("true")&& showMyTeamFlag.equals("false")){
						query+= " WHERE HRMS_LEAVE_HDR.EMP_ID=" + bean.getUserEmpId()+ " ";
					}else{					
						query+= " WHERE HRMS_LEAVE_HDR.EMP_ID IN("+myTeamStr+")";
					}					

			if (fromLeaveDate != null && !fromLeaveDate.equals("")
					&& !fromLeaveDate.equals("null") && toLeaveDate != null 
					&& !toLeaveDate.equals("") && !toLeaveDate.equals("null")) {

				/*query += " AND  TO_CHAR(LEAVE_TO_DATE,'YYYY')='" + leaveYear
						+ "' "*/ 
						
				query += " AND ((LEAVE_FROM_DATE >= TO_DATE('" + fromLeaveDate + "', 'DD-MM-YYYY') AND "
						+ " LEAVE_FROM_DATE<=TO_DATE('" + toLeaveDate	+ "', 'DD-MM-YYYY')) OR"
						+ " (LEAVE_TO_DATE >= TO_DATE('" + fromLeaveDate + "', 'DD-MM-YYYY') AND "
						+ " LEAVE_TO_DATE<=TO_DATE('" + toLeaveDate	+ "', 'DD-MM-YYYY')))";
						
			}
			if (status != null && !status.equals("'NA'")) {
				query += " AND HRMS_LEAVE_HDR.LEAVE_STATUS IN(" + status + ") ";
			}
			query += "   AND ROWNUM<=30 AND HRMS_LEAVE_HDR.LEAVE_STATUS NOT IN ('D','B') ";
			query += " ORDER BY EMPNAME,LEAVE_TO_DATE DESC  ";
			Object[][] leaveRecordobj = getSqlModel().getSingleResult(query);
			request.setAttribute("leaveDataobj", leaveRecordobj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void setEmpLocation(MypageProcessManagerAlerts bean) { try {
	 * String sqlQuery = " SELECT HRMS_EMP_OFFC.EMP_CENTER
	 * ,HRMS_CENTER.CENTER_NAME " + " FROM HRMS_EMP_OFFC " + " INNER JOIN
	 * HRMS_CENTER ON (HRMS_CENTER.CENTER_ID =HRMS_EMP_OFFC.EMP_CENTER)" + "
	 * WHERE HRMS_EMP_OFFC.EMP_ID=" + bean.getUserEmpId(); Object data[][] =
	 * getSqlModel().getSingleResult(sqlQuery); if (data != null && data.length >
	 * 0) { bean.setHolidayLocation(String.valueOf(data[0][1])); } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	public void getEmployeeLeaveDtl(MypageProcessManagerAlerts bean,
			String empCode) {
		try {

			if (empCode == null) {
				empCode = bean.getUserEmpId();
			} else {
				empCode = bean.getLeaveEmpCode();
			}
			String loginUserDataQuery = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),EMP_TOKEN|| ' ' ||'-' || ' ' || HRMS_EMP_OFFC.EMP_FNAME || ' ' || "
					+ " HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + empCode;
			Object[][] loginUserDataObj = getSqlModel().getSingleResult(
					loginUserDataQuery);
			if (loginUserDataObj != null && loginUserDataObj.length > 0) {
				bean.setLeaveEmpCode(Utility.checkNull(String
						.valueOf(loginUserDataObj[0][0])));
				bean.setLeaveEmpToken(Utility.checkNull(String
						.valueOf(loginUserDataObj[0][1])));
				bean.setLeaveEmpName(Utility.checkNull(String
						.valueOf(loginUserDataObj[0][3])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void report(MypageProcessManagerAlerts bean,
			HttpServletRequest request, HttpServletResponse response,
			String myTimecardfromDate, String myTimecardtoDate, 
			String myTimecardPrstatus, String myTimecardAbstatus, 
			String regularizedStatusstr, String leaveStatusStr, 
			String traveltimecardStr, String halfDaytimecardStr, 
			String showallFilterValue, String latetimecardStatusStr) {

		try {
			String notLoginUser="false";
			String query = null;
			org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
			String type= bean.getReport();
			rds.setReportType(type);
			String fileName = "MyTimecardReport"
					+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("MyTimecard Report");
			rds.setTotalColumns(8);
			rds.setShowPageNo(true);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
			if (!bean.getUserEmpId().equals(bean.getEmpCodeMyPage())) {
				bean.setNotLoginEmp("true");
				notLoginUser = "true";
				bean.setMyTeamFlag("true");
			}
			String selectedStatus = "'NA'";

			if (latetimecardStatusStr != null && !latetimecardStatusStr.equals("")) {
				selectedStatus += ",'Early-Leaving','Late-Coming','Dual Late'";
			}

			if (myTimecardAbstatus != null && !myTimecardAbstatus.equals("")) {
				selectedStatus += ",'Absent'";
			}

			if (myTimecardPrstatus != null && !myTimecardPrstatus.equals("")) {
				selectedStatus += ",'Present'";
			}

			if (halfDaytimecardStr != null && !halfDaytimecardStr.equals("")) {
				selectedStatus += ",'Half Day'";
			}

			if (regularizedStatusstr != null && !regularizedStatusstr.equals("")) {
				selectedStatus += ",'Regularized'";
			}

			if (leaveStatusStr != null && !leaveStatusStr.equals("")) {
				selectedStatus += ",'Leave'";
			}
			if (traveltimecardStr != null && !traveltimecardStr.equals("")) {
				selectedStatus += ",'Travel'";
			}

			if (myTimecardfromDate != null && !myTimecardfromDate.equals("")) {
				String fromYear = myTimecardfromDate.substring(6, 10);
				String toYear = myTimecardtoDate.substring(6, 10);				
				int tyear = Integer.parseInt(toYear);
				 int fyear = Integer.parseInt(fromYear);
				final int diffYear = Integer.parseInt(toYear)
						- Integer.parseInt(fromYear);
				String[] fromDays;
				String [] toDays;
				fromDays=myTimecardfromDate.split("-");
				toDays =myTimecardtoDate.split("-");
				
				final int diffDays = Integer.parseInt(toDays[0])
				- Integer.parseInt(fromDays[0]);				
				
				String myTeamStr = "0";
				String[] myTeamCode = getMyTeamEmpCode(bean.getUserEmpId());
				if (myTeamCode != null && !myTeamCode.equals("")) {
					for (int i = 0; i < myTeamCode.length; i++) {
						if (i == myTeamCode.length) {
							myTeamStr += myTeamCode[i];
						} else {
							myTeamStr += "," + myTeamCode[i];
						}
					}
				}

				query = " SELECT * FROM ( ";
				for (int i = 0; i <= diffYear; i++) {
					query += " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPLOYEENAME, TO_CHAR(ATT_DATE,'DD-MM-YYYY') ATTENDANCE_DATE , "
							+ " SUBSTR(TO_CHAR(ATT_DATE, 'DAY'),0,3),CASE WHEN (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR "
							+ " ATT_REG_LOGIN IS NOT NULL) AND TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')< TO_CHAR(ATT_LOGIN, 'HH24:MI') "
							+ " THEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')	 WHEN (TO_CHAR(ATT_LOGIN, 'HH24:MI')='00:00' OR ATT_LOGIN IS NULL) AND"
							+ "(TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) THEN "
							+ " NVL(TO_CHAR(ATT_REG_LOGIN, 'HH24:MI'),'00:00') ELSE NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI'),'00:00')  END INTIME,"
							+ " CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL)  THEN "
							+ " (CASE WHEN TO_char(ATT_REG_LOGOUT, 'HH24:MI')>NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')  THEN "
							+ " TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI') ELSE TO_CHAR(ATT_LOGOUT, 'HH24:MI') END  )  WHEN (TO_CHAR(ATT_LOGOUT, 'HH24:MI')='00:00' "
							+ " OR ATT_LOGOUT IS NULL) THEN  NVL(TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI'),'00:00') ELSE NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')  END OUTTIME,"
							+ " CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) AND TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')> NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')"
							+ " THEN   TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "
							+ " (CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI') AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "
							+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI')  ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END  ))*24),'00')|| ':' || "
							+ " TO_CHAR(MOD(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')-  CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN"
							+ " LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),  TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') )"
							+ " ELSE ATT_REG_LOGIN END)*24*60),60),'00')  ELSE   (CASE WHEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' THEN (  TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')-"
							+ " (CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI') THEN TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ELSE "
							+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END  ) )*24),'00') || ':' ||  TO_CHAR(MOD(FLOOR((GREATEST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||"
							+ " TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'), TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'))- CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00'"
							+ " THEN LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'), TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ) ELSE ATT_REG_LOGIN END)*24*60),60),'00') )"
							+ " ELSE  (CASE  WHEN TO_CHAR(ATT_LOGOUT, 'HH24:MI')!='00:00' AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN (NVL(TO_CHAR(FLOOR((ATT_LOGOUT - ATT_LOGIN)*24),'00'),'00')|| ':' ||"
							+ " NVL( TO_CHAR(MOD(FLOOR((ATT_LOGOUT-ATT_LOGIN)*24*60),60),'00'),'00')  ) ELSE '00:00' END)END) END WORKDURATION, CASE WHEN ATT_STATUS_ONE='WO' THEN 'Weekly Off' WHEN ATT_STATUS_ONE='HO' THEN 'Holiday' WHEN"
							+ " ATT_REG_STATUS_TWO='LV' THEN 'Leave'    WHEN ATT_REG_STATUS_TWO='TR' THEN 'Travel'   WHEN ATT_REG_STATUS_TWO='RG' THEN 'Regularized'   WHEN ATT_REG_STATUS_TWO='EW' THEN 'ExtraWork' WHEN (ATT_STATUS_ONE='PR' AND ATT_STATUS_TWO='IN')"
							+ " THEN 'Present'   WHEN ATT_STATUS_ONE='PR' THEN DECODE(ATT_STATUS_TWO,'HD','Half Day','DL','Dual Late','LC','Late-Coming','EL','Early-Leaving','PR','Present','AB','Absent') "
							+ " ELSE DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','HO','Holiday')   END NEW_STATUS,SHIFT_NAME,ATT_DATE FROM HRMS_DAILY_ATTENDANCE_"
							+ fyear
							+ " INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_"
							+ fyear
							+ ".ATT_SHIFT_ID)"
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_"
							+ fyear + ".ATT_EMP_ID) ";
					if (notLoginUser.equals("true") && ! bean.getEmpCodeMyPage().equals("")) {
						query += " WHERE ATT_EMP_ID="+bean.getEmpCodeMyPage()+" )";
					}else if ((!bean.getUserEmpId().equals(bean.getEmpCodeMyPage()))&&(bean.getMyTeamFlag().equals("true"))) {
						query += " WHERE ATT_EMP_ID IN(" + myTeamStr + "))";
					} 
					else {
						query += " WHERE ATT_EMP_ID=" + bean.getUserEmpId()+ ")";
					}

					if (fyear != tyear) {
						query += " UNION ALL ";
						fyear++;
					} else {
						break;
					}
				}
				query += "WHERE ATT_DATE >= TO_DATE('"
						+ myTimecardfromDate + "', 'DD-MM-YYYY')  "
						+ " AND ATT_DATE<=TO_DATE('" + myTimecardtoDate
						+ "', 'DD-MM-YYYY')";
				
				if (showallFilterValue != null
						&& showallFilterValue.equals("All")) {

				} else {
					if (selectedStatus != null
							&& !selectedStatus.equals("'NA'")) {
						query += " AND NEW_STATUS IN(" + selectedStatus
								+ ")";
					}

				}
				
				query += " ORDER BY  EMPLOYEENAME,TO_DATE(ATT_DATE, 'DD-MM-YYYY') ";
				org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
				Object[][] queryData = getSqlModel().getSingleResult(query);
				if (queryData == null || queryData.length == 0) {
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No records available";
					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					noData.setBorder(false);
					rg.addTableToDoc(noData);
					rg.process();
				} else {
					tdstable.setHeader(new String[] { "EMPLOYEE NAME",
							"ATTENDANCE DATE", "DAY", "IN TIME", "OUTTIME",
							"WORK DURATION", "STATUS", "SHIFT" });
					tdstable.setHeaderTable(true);
					tdstable.setHeaderBorderDetail(3);
					tdstable.setCellAlignment(new int[] { 0, 1, 0, 1, 1, 1, 0,
							0 });
					tdstable.setCellWidth(new int[] { 15, 15, 10, 10, 10, 15,
							15, 10 });
					tdstable.setData(queryData);
					tdstable.setBorderDetail(3);
					rg.addTableToDoc(tdstable);
					rg.process();
				}
				rg.createReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void leaveReport(HttpServletRequest request,
			HttpServletResponse response, MypageProcessManagerAlerts bean, 
			String yearLeave, String appliedStatus, String approvedStatus, 
			String rejectedStatus, String cancelledStatus, 
			String showallLeaveStatus, String fromLeaveDate, String toLeaveDate) {		
	try {			
			String status = "'NA'";
			if (appliedStatus != null && appliedStatus.length() > 0) {
				status += "," + "'P','C'";
			}
			if (approvedStatus != null && approvedStatus.length() > 0) {
				status += "," + "'A','X'";
			}
			if (rejectedStatus != null && rejectedStatus.length() > 0) {
				status += "," + "'R'";
			}
			if (cancelledStatus != null && cancelledStatus.length() > 0) {
				status += "," + "'N','Z'";
			}
			
			org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
			String fileName = "My LeaveReport" + Utility.getRandomNumber(100);
			String type= bean.getReport();
			rds.setReportType(type);
			rds.setFileName(fileName);
			rds.setReportName("My Leave Report");
			rds.setTotalColumns(7);
			rds.setShowPageNo(true);
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());

			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
					context, request);
			
			if (!bean.getUserEmpId().equals(bean.getLeaveEmpCode())) {
				bean.setMyTeamLeaveFlag("true");
			}
			String myTeamStr = "0";
			String[] myTeamCode = getMyTeamEmpCode(bean.getUserEmpId());
			if (myTeamCode != null && !myTeamCode.equals("")) {
				for (int i = 0; i < myTeamCode.length; i++) {
					if (i == myTeamCode.length) {
						myTeamStr += myTeamCode[i];
					} else {
						myTeamStr += "," + myTeamCode[i];
					}
				}
			}

			if (yearLeave == null) {
				String yearquery = "SELECT TO_CHAR(SYSDATE,'yyyy')FROM DUAL";
				Object[][] yearobj = getSqlModel().getSingleResult(yearquery);
				if (yearobj != null && yearobj.length > 0) {
					bean.setLeaveYear(String.valueOf(yearobj[0][0]));
					yearLeave = (String.valueOf(yearobj[0][0]));
				}
			}
			
			if (fromLeaveDate != null && !fromLeaveDate.equals("")) {
				bean.setFromLeaveDate(fromLeaveDate);
			} else {
				bean.setFromLeaveDate("");
			}
			if (toLeaveDate != null && !toLeaveDate.equals("")) {
				bean.setToLeaveDate(toLeaveDate);
			} else {
				bean.setToLeaveDate("");
			}			
			
			String query = " SELECT NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ') AS EMPNAME,"
					+ " TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),NVL(LEAVE_DAYS,0),NVL(LEAVE_ABBR,''),"
					+ " TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'DD-MM-YYYY'),"					
					+ " NVL(DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'P','Applied','N','Cancelled','R','Rejected','A','Approved','C','Applied' ,'X','Approved','Z','Cancelled'),'')"
					+ " FROM HRMS_LEAVE_HDR"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID)"
					+ " INNER JOIN HRMS_LEAVE_DTL ON(HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE=HRMS_LEAVE_HDR.LEAVE_APPL_CODE)"
					+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_DTL.LEAVE_CODE)";
			if ((!bean.getUserEmpId().equals(bean.getLeaveEmpCode()))
					&& bean.getMyTeamLeaveFlag().equals("true")&& (bean.getLeaveEmpCode().equals(""))) {
				query += " WHERE HRMS_LEAVE_HDR.EMP_ID IN(" + myTeamStr + ")";
			}else if((bean.getLeaveEmpCode()!= null && ! bean.getLeaveEmpCode().equals(""))&& bean.getMyTeamLeaveFlag().equals("true")) {
				query += " WHERE HRMS_LEAVE_HDR.EMP_ID= " + bean.getLeaveEmpCode();
			}
			else {
				query += " WHERE HRMS_LEAVE_HDR.EMP_ID= " + bean.getUserEmpId();
			}
			if (status != null && !status.equals("'NA'")) {
				query += " AND HRMS_LEAVE_HDR.LEAVE_STATUS IN(" + status + ") ";
			}
			/*
			query += " AND TO_CHAR(LEAVE_TO_DATE,'YYYY')='"
					+ yearLeave
					+ "'";
			*/		
			if (fromLeaveDate != null && !fromLeaveDate.equals("")
					&& !fromLeaveDate.equals("null") && toLeaveDate != null 
					&& !toLeaveDate.equals("") && !toLeaveDate.equals("null")) {
				
				query += " AND ((LEAVE_FROM_DATE >= TO_DATE('" + fromLeaveDate + "', 'DD-MM-YYYY') AND "
					+ " LEAVE_FROM_DATE<=TO_DATE('" + toLeaveDate	+ "', 'DD-MM-YYYY')) OR"
					+ " (LEAVE_TO_DATE >= TO_DATE('" + fromLeaveDate + "', 'DD-MM-YYYY') AND "
					+ " LEAVE_TO_DATE<=TO_DATE('" + toLeaveDate	+ "', 'DD-MM-YYYY')))";
			}
					
			query+= " AND ROWNUM<=30 AND HRMS_LEAVE_HDR.LEAVE_STATUS NOT IN ('D','B')"
					+ " ORDER BY EMPNAME,LEAVE_TO_DATE DESC ";

			org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
			Object[][] queryData = getSqlModel().getSingleResult(query);
			if (queryData == null || queryData.length == 0) {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
				rg.process();
			} else {
				tdstable.setHeader(new String[] { "EMPLOYEE NAME", "FROM DATE",
						"TO DATE", "LEAVE DAYS", "LEAVE TYPE",
						"APPLICATION DATE", "STATUS" });
				tdstable.setHeaderTable(true);
				tdstable.setHeaderBorderDetail(3);
				tdstable.setCellAlignment(new int[] { 0, 1, 1, 2, 2, 1, 0 });
				tdstable.setCellWidth(new int[] { 20, 10, 10, 10, 10, 20, 20 });
				tdstable.setData(queryData);
				tdstable.setBorderDetail(3);
				rg.addTableToDoc(tdstable);
				rg.process();
			}
			rg.createReport(response);
		}catch(Exception e){
		e.printStackTrace();
	}
			
	}
}