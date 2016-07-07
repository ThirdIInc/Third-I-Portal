package org.paradyne.lib;

import java.lang.annotation.Documented;
import java.sql.Connection;

/**
 * @author Shashikant Doke
 * @date 3 Jun 2008
 **/

/**
 * This class gets the required information to send an email
 */

public class MailModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MailModel.class);

	/**
	 * @return String[] as mail IDs which is set as default in the software
	 */
	public String[] getDefaultMailIds() {
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_CODE=1 ";
		Object fromEmp[][] = getSqlModel().getSingleResult(fromQuery);
		String[] mailIds = new String[fromEmp.length];
		for (int i = 0; i < fromEmp.length; i++) {
			mailIds[i] = String.valueOf(fromEmp[i][0]);
		}
		return mailIds;
	}

	@Deprecated
	/**
	 * @return Object[][] as settings stored for email This is an deprecated
	 *         method .. Instead of using this method use
	 *         getServerMailBox(String empId) method
	 */
	public Object[][] getMailBoxPara() {
		String getMailBox = " SELECT MAILBOX_SERVER, MAILBOX_PROTOCOL, MAILBOX_PORT, MAILBOX_USERID, MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_FLAG,MAILBOX_POP_BEFORE_SMTP FROM "
				+ "	HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG='O' ";
		return getSqlModel().getSingleResult(getMailBox);
	}

	/**
	 * 
	 * @param empId
	 * @return
	 */
	public Object[][] getServerMailBox(String empId, String fromMailId,
			Connection conn) {
		Object[][] empMailBoxData = null;
		try {
			System.out.println("empId  in getServerMailBox   " + empId);
			System.out.println("fromMailId  in getServerMailBox   "
					+ fromMailId);
			String getMailBox = "";
			empMailBoxData = null;
			Object[][] sysMailData = null;
			if (empId != null && !empId.equals("")) {// this is for pop
														// before
				// smtp check
				getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,EMAIL_USER_NAME,EMAIL_USER_PASS, SERVER_AUTH_REQUIRED, "
						+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,EMAIL_USER_NAME  "
						+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD ,SERVER_USESYSTEMMAILID_FLAG " // newly
						// added
						// for
						// logon
						// using
						// same
						// or
						// different
						// Id
						+ " FROM HRMS_EMAIL_SERVER  "
						+ " INNER JOIN HRMS_EMAIL_ACCOUNT ON (HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE=HRMS_EMAIL_SERVER.SERVER_CODE) "
						+ " WHERE EMAIL_EMP_ID="
						+ empId
						+ "  AND EMAIL_OFFICIAL_FLAG='Y'";
				empMailBoxData = SqlModel.getSingleResult(getMailBox, conn);
				if (empMailBoxData != null && empMailBoxData.length > 0) {
					String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
							+ String.valueOf(empMailBoxData[0][10])
							+ " AND ROWNUM=1";
					sysMailData = SqlModel.getSingleResult(sysmailQuery, conn);
					if (sysMailData != null && sysMailData.length > 0) {
						if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
							fromMailId = String.valueOf(sysMailData[0][0]);
						}
					}
					if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
						if (empId == null || empId.length() == 0) {
							System.out.println("emp Id NULL :");
							if (sysMailData != null && sysMailData.length > 0) {
								System.out.println("sysMailData not NULL :");
								empMailBoxData[0][3] = sysMailData[0][0];
								empMailBoxData[0][4] = sysMailData[0][1];
								empMailBoxData[0][11] = sysMailData[0][0];
							}
						} else {
							System.out.println("fromMailId   emp Id present:"
									+ fromMailId);
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = fromMailId;
						}
					} else {
						empMailBoxData[0][3] = empMailBoxData[0][13];
						empMailBoxData[0][4] = empMailBoxData[0][14];
						empMailBoxData[0][11] = fromMailId;
					}

				}
				System.out.println(" in POP BEFORE SMTP");
			}
			if (empMailBoxData == null || empMailBoxData.length == 0) {

				getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,' ',' ',SERVER_AUTH_REQUIRED, "
						+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,'' "
						+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD,SERVER_USESYSTEMMAILID_FLAG " // newly
						// added
						// for
						// logon
						// using
						// same
						// or
						// different
						// Id
						+ " FROM HRMS_EMAIL_SERVER "
						+ " WHERE SERVER_SYSTEMMAIL_FLAG='Y' ";
				empMailBoxData = SqlModel.getSingleResult(getMailBox, conn);
				System.out.println(" ELSE POP");
				if (empMailBoxData != null && empMailBoxData.length > 0) {
					String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
							+ String.valueOf(empMailBoxData[0][10])
							+ " AND ROWNUM=1";
					sysMailData = SqlModel.getSingleResult(sysmailQuery, conn);
					if (sysMailData != null && sysMailData.length > 0) {
						if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
							fromMailId = String.valueOf(sysMailData[0][0]);
						}
					}
					if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
						if (empId == null || empId.length() == 0) {
							System.out.println("emp Id NULL :");
							if (sysMailData != null && sysMailData.length > 0) {
								System.out.println("sysMailData not NULL :");
								empMailBoxData[0][3] = sysMailData[0][0];
								empMailBoxData[0][4] = sysMailData[0][1];
								empMailBoxData[0][11] = sysMailData[0][0];
							}
						} else {
							System.out.println("fromMailId   emp Id present:"
									+ fromMailId);
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = fromMailId;
						}
					} else {
						empMailBoxData[0][3] = empMailBoxData[0][13];
						empMailBoxData[0][4] = empMailBoxData[0][14];
						empMailBoxData[0][11] = fromMailId;
					}

				}
			}
			if (sysMailData != null && sysMailData.length > 0) {
				for (int i = 0; i < sysMailData[0].length; i++) {
					System.out.println("  EMAIL SERVER DATA  : "
							+ String.valueOf(sysMailData[0][i]));
				}
			}
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				for (int i = 0; i < empMailBoxData.length; i++) {
					for (int j = 0; j < empMailBoxData[0].length; j++) {
						System.out.println("empMailBoxData   [" + i + "][" + j
								+ "]" + empMailBoxData[i][j]);
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empMailBoxData;
	}

	/**
	 * 
	 * @param empId
	 * @return
	 */
	public Object[][] getServerMailBox(String empId, String fromMailId) {
		logger.info("empId  in getServerMailBox   " + empId);
		logger.info("fromMailId  in getServerMailBox   " + fromMailId);
		String getMailBox = "";
		Object[][] empMailBoxData = null;
		Object[][] sysMailData = null;

		if (empId != null && !empId.equals("")) {// this is for pop before
													// smtp check
			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,EMAIL_USER_NAME,EMAIL_USER_PASS, SERVER_AUTH_REQUIRED, "
					+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,EMAIL_USER_NAME  "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD ,SERVER_USESYSTEMMAILID_FLAG " // newly
																															// added
																															// for
																															// logon
																															// using
																															// same
																															// or
																															// different
																															// Id
					+ " FROM HRMS_EMAIL_SERVER  "
					+ " INNER JOIN HRMS_EMAIL_ACCOUNT ON (HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE=HRMS_EMAIL_SERVER.SERVER_CODE) "
					+ " WHERE EMAIL_EMP_ID="
					+ empId
					+ "  AND EMAIL_OFFICIAL_FLAG='Y'";
			empMailBoxData = getSqlModel().getSingleResult(getMailBox);
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = getSqlModel().getSingleResult(sysmailQuery);
				if (sysMailData != null && sysMailData.length > 0) {
					if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
						fromMailId = String.valueOf(sysMailData[0][0]);
					}
				}
				if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
					if (empId == null || empId.length() == 0) {
						logger.info("emp Id NULL :");
						if (sysMailData != null && sysMailData.length > 0) {
							logger.info("sysMailData not NULL :");
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
						logger
								.info("fromMailId   emp Id present:"
										+ fromMailId);
						empMailBoxData[0][3] = sysMailData[0][0];
						empMailBoxData[0][4] = sysMailData[0][1];
						empMailBoxData[0][11] = fromMailId;
					}
				} else {
					empMailBoxData[0][3] = empMailBoxData[0][13];
					empMailBoxData[0][4] = empMailBoxData[0][14];
					empMailBoxData[0][11] = fromMailId;
				}

			}
			logger.info(" in POP BEFORE SMTP");
		}
		if (empMailBoxData == null || empMailBoxData.length == 0) {

			String divisionMailbox = " SELECT SERVER_DIVISION FROM HRMS_EMAIL_SERVER  WHERE SERVER_DIVISION IS NOT NULL";
			Object[][] divisionMailboxObj = getSqlModel().getSingleResult(
					divisionMailbox);
			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,' ',' ',SERVER_AUTH_REQUIRED, "
					+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,'' "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD,SERVER_USESYSTEMMAILID_FLAG " // newly
																														// added
																														// for
																														// logon
																														// using
																														// same
																														// or
																														// different
																														// Id
					+ " FROM HRMS_EMAIL_SERVER " + " WHERE 1=1 ";

			if (empId == null || empId.equals("") || empId.equals("null")) {
				System.out.println("empId ::::---::::" + empId);
				empId = (String) session.getAttribute("empId");

				System.out.println("empId ::::---::::" + empId);
			}

			if (divisionMailboxObj != null && divisionMailboxObj.length > 0) {
				if(empId != null && !empId.equals("") && ! empId.equals("null")){
				getMailBox += " AND SERVER_DIVISION LIKE (SELECT '%'||EMP_DIV||',%' FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ empId + ")";
				}
			} else {
				getMailBox += " AND SERVER_SYSTEMMAIL_FLAG='Y' ";
			}
			empMailBoxData = getSqlModel().getSingleResult(getMailBox);
			logger.info(" ELSE POP");
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = getSqlModel().getSingleResult(sysmailQuery);
				if (sysMailData != null && sysMailData.length > 0) {
					if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
						fromMailId = String.valueOf(sysMailData[0][0]);
					}
				}
				if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
					if (empId == null || empId.length() == 0) {
						logger.info("emp Id NULL :");
						if (sysMailData != null && sysMailData.length > 0) {
							logger.info("sysMailData not NULL :");
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
						logger
								.info("fromMailId   emp Id present:"
										+ fromMailId);
						empMailBoxData[0][3] = sysMailData[0][0];
						empMailBoxData[0][4] = sysMailData[0][1];
						empMailBoxData[0][11] = fromMailId;
					}
				} else {
					empMailBoxData[0][3] = empMailBoxData[0][13];
					empMailBoxData[0][4] = empMailBoxData[0][14];
					empMailBoxData[0][11] = fromMailId;
				}

			}
		}
		if (sysMailData != null && sysMailData.length > 0) {
			for (int i = 0; i < sysMailData[0].length; i++) {
				logger.info("  EMAIL SERVER DATA  : "
						+ String.valueOf(sysMailData[0][i]));
			}
		}
		if (empMailBoxData != null && empMailBoxData.length > 0) {
			for (int i = 0; i < empMailBoxData.length; i++) {
				for (int j = 0; j < empMailBoxData[0].length; j++) {
					logger.info("empMailBoxData   [" + i + "][" + j + "]"
							+ empMailBoxData[i][j]);
				}

			}

		}

		return empMailBoxData;
	}

	/**
	 * @return String as company name
	 */
	public String getCompanyName() {
		String companyName = "";
		String QueryCompanyName = " SELECT COMPANY_CODE,NVL(COMPANY_NAME,' ') FROM HRMS_COMPANY ";
		Object[][] getConmanyName = getSqlModel().getSingleResult(
				QueryCompanyName);
		if (getConmanyName != null && getConmanyName.length > 0) {
			companyName = String.valueOf(getConmanyName[0][1]);
		}
		return companyName;
	}

	public String getCompanyName(Connection dbconn) {
		String companyName = "";
		String QueryCompanyName = " SELECT COMPANY_CODE,NVL(COMPANY_NAME,' ') FROM HRMS_COMPANY ";
		Object[][] getConmanyName = getSqlModel().getSingleResult(
				QueryCompanyName, dbconn);
		if (getConmanyName != null && getConmanyName.length > 0) {
			companyName = String.valueOf(getConmanyName[0][1]);
		}
		return companyName;
	}

	/**
	 * @param obj
	 * @return String[] as official email IDs
	 */
	public String[] getMailIds(Object[][] obj) {
		String str = getValue(obj);// Get emp IDs separated by comma

		String query = " SELECT ADD_EMAIL FROM HRMS_EMP_ADDRESS "
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID)"
				+ "	WHERE ADD_EMAIL IS NOT NULL AND HRMS_EMP_OFFC .EMP_ID IN ("
				+ str + ")" + " AND ADD_TYPE = 'P' AND ADD_EMAIL IS NOT NULL";
		Object result[][] = getSqlModel().getSingleResult(query);

		String[] strObj;
		if (result.length > 0) {
			strObj = new String[result.length];
			for (int i = 0; i < strObj.length; i++) {
				strObj[i] = String.valueOf(result[i][0]);
			}
		} else {
			strObj = new String[0];
		}
		return strObj;
	}

	/**
	 * @param obj
	 * @return String emp IDs separated by comma
	 */
	public String getValue(Object[][] obj) {
		String str = "";
		try {
			for (int i = 0; i < obj.length; i++) {
				str += String.valueOf(obj[i][0]) + ",";
			}
			str = str.substring(0, str.length() - 1);
		} catch (RuntimeException e) {
			logger.error(e);
		} // end of try-catch block
		return str;

	}

	/**
	 * @param from_mailIds
	 * @return String as emp name of a person from whom mail will be sent
	 */
	public String getEmpName(Object[][] from_mailIds) {
		String empNameQuery = " SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+ " FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID = "
				+ String.valueOf(from_mailIds[0][0]);
		Object empNameObj[][] = getSqlModel().getSingleResult(empNameQuery);
		return String.valueOf(empNameObj[0][0]);
	}
}