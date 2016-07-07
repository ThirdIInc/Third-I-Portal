package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.email.HtmlEmail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.struts.action.ApplicationStudio.jobScheduling.JobLogger;

/**
 * @author aa1380
 *
 */
public class AutoAnniversaryModel extends ModelBase implements Job{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AutoAnniversaryModel.class);

	private static String dbDriver = null;
	private static String dbUrl = null;
	private static String dbUsername = null;
	private static String dbPassword = null;
	private String autoUploadID = "";
	private String client = "";
	private Connection dbConn = null;
	public HttpServletRequest request;
	
	String[][] fromMailId = null;
	int mailCount = 0;
	HtmlEmail email = null;
	static PrintWriter output = null;
	static PrintWriter out = null;
	static String imagePath = "";
	int fireCounter = 0;
	
	/**
	 * @return the client
	 */
	public String getClient() {
		return this.client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
	
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return this.request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the dbDriver
	 */
	public static String getDbDriver() {
		return dbDriver;
	}

	/**
	 * @param dbDriver the dbDriver to set
	 */
	public static void setDbDriver(String dbDriver) {
		AutoAnniversaryModel.dbDriver = dbDriver;
	}

	/**
	 * @return the dbUrl
	 */
	public static String getDbUrl() {
		return dbUrl;
	}

	/**
	 * @param dbUrl the dbUrl to set
	 */
	public static void setDbUrl(String dbUrl) {
		AutoAnniversaryModel.dbUrl = dbUrl;
	}

	/**
	 * @return the dbUsername
	 */
	public static String getDbUsername() {
		return dbUsername;
	}

	/**
	 * @param dbUsername the dbUsername to set
	 */
	public static void setDbUsername(String dbUsername) {
		AutoAnniversaryModel.dbUsername = dbUsername;
	}

	/**
	 * @return the dbPassword
	 */
	public static String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param dbPassword the dbPassword to set
	 */
	public static void setDbPassword(String dbPassword) {
		AutoAnniversaryModel.dbPassword = dbPassword;
	}

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			dbConn = getConnection(jobExecutionContext);
			autoUploadID = getAutoUploadId(jobExecutionContext);
			client = jobExecutionContext.getJobDetail().getJobDataMap().getString("CLIENT");
			System.out.println("CLIENT >>>>>>>>>>>>>>>>>"+client);
			sendAnniversaryMail();
		} catch (Exception e) {
			JobLogger.error(client, "Exception in execute-" + e);
			e.printStackTrace();
			JobLogger.printStackTrace(client, e);
		}
	}
	
	private String getAutoUploadId(JobExecutionContext jobExecutionContext) {
		String autoUploadID = jobExecutionContext.getJobDetail()
				.getJobDataMap().getString("NAME").split("_")[1];
		return autoUploadID;
	}
	
	private Connection getConnection(JobExecutionContext jobExecutionContext) {
		try {
			client = jobExecutionContext.getJobDetail().getJobDataMap()
					.getString("CLIENT");
 
			if (!(client == null || client.equals("null") || client
					.equals(null))) {
				ResourceBundle bundle = ResourceBundle
						.getBundle("org.paradyne.lib.ConnectionModel");

				dbDriver = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle
						.getString(client + ".driver"));
				dbUrl = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle
						.getString(client + ".url"));
				dbUsername = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle
						.getString(client + ".username"));
				dbPassword = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle
						.getString(client + ".password"));

				setDbDriver(dbDriver);
				setDbUrl(dbUrl);
				setDbUsername(dbUsername);
				setDbPassword(dbPassword);
				setClient(client);
				try {
					if (dbDriver != null) {
						Class.forName(dbDriver);
						dbConn = java.sql.DriverManager.getConnection(dbUrl,
								dbUsername, dbPassword);

					}
				} catch (Exception e) {
					JobLogger.error(client, "Exception in execute-" + e);
					JobLogger.printStackTrace(client, e);
					e.printStackTrace();
				} finally {
					// if(dbConn != null) { dbConn.close(); }
				}
			}
		} catch (Exception e) {
			JobLogger.error(client, "Exception in execute-" + e);
			e.printStackTrace();
			JobLogger.printStackTrace(client, e);
		}
		return dbConn;
	}
	
	public String getFinalBdayMessage(String message) {
		String outer = "<table width='90%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td width='100%' style='font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000;padding-right:5px;text-align: right;'>This Message is sent through <a href='http://www.the3i.com' target='_new'>HRMS</a></td></tr><tr><td> "
			+" <table width='100%' border='0' cellspacing='0' cellpadding='0' style='border: #84b4f9 solid 1px;'><tr><td colspan='2' valign='top'> "
			+" <table width='97%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td height='150' valign='top' style='font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000; padding-top:10px;padding-left:5px;padding-right:5px' ><div align='justify'>"+message+"</div></td></tr></table></td></tr><tr> "
			+" <td  colspan='2' bgcolor='#e4eefd' style='margin-top: 5px; border-top: #7babf0 solid 1px;font-family:Arial, Helvetica, sans-serif; font-size:10px; color:#666666; padding-left:10px ; padding-right:10px'><div align='justify'>All information contained in this communication is confidential, proprietary, privileged and is intended for "
			+" the addressees only. If you have received this E-mail in error please notify mail administrator by E-mail or the sender by replying to this message, and then delete this E-mail and other copies of it from your computer system. Any unauthorized dissemination,publication, transfer or use of the contents of this communication, with or without modifications is punishable under the relevant law. "
			+" </div></td></tr></table></td></tr><tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'></table></td></tr></table> ";
		return outer;
	}
	
	public HtmlEmail setHtmlEmail(Object[][] mailBoxData, String subject, String textBody, String[] tomailIds) {
		HtmlEmail email = new HtmlEmail();
		String replacedString = "";
		try {
			email.setHostName(String.valueOf(mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String.valueOf(mailBoxData[0][1])));// 80
			email.setSubject("" + subject);
			replacedString = getFinalBdayMessage(getHtmlText(email, textBody));
			email.setHtmlMsg(replacedString);
			email.setTextMsg("Your email client does not support HTML messages");
			addTo_CC(email, tomailIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
	
	public HtmlEmail addTo_CC(HtmlEmail email, String[] toObj) {
		try {
			for (int i = 0; i < toObj.length; i++) {
				email.addBcc(toObj[i]);
			}
		} catch (Exception e) {
			logger.error("Exception in addTo_CC method.");
		}
		return email;
	}
	
	public String getHtmlText(HtmlEmail email, String textBody) {
		textBody = textBody.replace("src=\"", "$");
		StringTokenizer st = new StringTokenizer(textBody, "$");
		int counter = 0;
		String replacedString = "";
		String cid = "";
		while (st.hasMoreTokens()) {
			String new_tokens = st.nextToken();
			if (counter != 0) {
				int endofURL = new_tokens.indexOf("\"");
				String src = new_tokens.substring(0, endofURL);
				src = imagePath + "\\webapps" + src;
				try {
					cid = email.embed(new File(src), "Image001" + counter);
				} catch (Exception e) {
					e.printStackTrace();
				}
				replacedString += "src=\"cid:" + cid + "\"" + new_tokens.substring(endofURL + 1, new_tokens.length());
			} else {
				replacedString += new_tokens;
			}
			counter++;
		}
		return replacedString;
	}
	
	public String[][] getDefaultMailIds(Connection conn) {
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE = 1 ";
		Object fromEmp[][] = SqlModel.getSingleResult(fromQuery, conn);
		String[][] mailIds = new String[fromEmp.length][2];
		for (int i = 0; i < fromEmp.length; i++) {
			mailIds[i][0] = String.valueOf(fromEmp[i][0]);
			mailIds[i][1] = String.valueOf(fromEmp[i][1]);
		}
		return mailIds;
	}
	
	private boolean getIndividualDivMail(Connection conn, Object[][] settingObj) {
		try {
			String companyQry = " SELECT COMPANY_NAME ,TO_CHAR(SYSDATE,'ddth MON') FROM HRMS_COMPANY ";
			Object[][] companyObj = SqlModel.getSingleResult(companyQry, conn);
			Object templateObj[][] = null;
			String[][] fromMailIds = getDefaultMailIds(conn);
			String divQuery = "SELECT DIV_ID FROM HRMS_DIVISION ";
			Object[][] divObj = SqlModel.getSingleResult(divQuery, conn);
			for (int k = 0; k < divObj.length; k++) {
				String empAnniversaryList = "SELECT  EMP_FNAME,HRMS_EMP_OFFC.EMP_ID,NVL(EMP_DEPT,''),"
						+ " NVL(EMP_CENTER,''),  NVL(EMP_RANK,''),NVL(EMP_DIV, ''),"
						+ " NVL(INITCAP(EMP_FNAME),' ')||' '||NVL(INITCAP(EMP_LNAME),' '),"
						+ " NVL(DEPT_NAME,''),NVL(INITCAP(CENTER_NAME),' '),NVL(RANK_NAME,' ') "
						+ " ,NVL(ADD_EMAIL,' '),NVL(ADD_MOBILE,' ') "
						+ "	FROM HRMS_EMP_OFFC   "
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
						+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ "	INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+ "	INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
						+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P') "
						+ " LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_PERS.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						+ "	WHERE TO_CHAR(EMP_MARRG_DATE,'DD-MM') = TO_CHAR(SYSDATE,'DD-MM') AND EMP_STATUS='S' AND EMP_DIV="
						+ String.valueOf(divObj[k][0]);
				Object[][] empAnniversaryListObj = SqlModel.getSingleResult(empAnniversaryList, conn);

				String templateData = " SELECT TEMPLATE_BODY FROM HRMS_ANNIVERSARY_TEMPLATE WHERE TEMPLATE_ID IN (" 
										+ String.valueOf(settingObj[0][9]) + 
										" ) ";

				if (empAnniversaryListObj != null && empAnniversaryListObj.length > 0)  {
					try {
						AutoAnniversaryModel.output.write("Anniversary of Employee====");
						AutoAnniversaryModel.output.println();
						templateObj = SqlModel.getSingleResult(templateData, conn);

						String toEmpQry = " SELECT HRMS_EMP_OFFC.EMP_ID,ADD_EMAIL FROM HRMS_EMP_ADDRESS "
								+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
								+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
								+ " WHERE HRMS_EMP_OFFC .EMP_STATUS='S' AND ADD_TYPE='P' "
								+ " AND ADD_EMAIL IS NOT NULL  AND EMP_DIV IN (" + String.valueOf(divObj[k][0]) + ")";

						toEmpQry += "  ORDER BY EMP_ID ";
						Object[][] toEmpObj = SqlModel.getSingleResult(toEmpQry, conn);
						 
						String[] toEmpAddArr = new String[toEmpObj.length];
						if (toEmpObj != null && toEmpObj.length > 0) {
							for (int j = 0; j < toEmpObj.length; j++) {
								toEmpAddArr[j] = String.valueOf(toEmpObj[j][1]);
							}  
						} else {
							return false;
						}  

						// Added by Lakkichand for sending mails to Group mail
						// Id
						if (String.valueOf(settingObj[0][10]).equals("Y")) {
							String groupId_str = String.valueOf(settingObj[0][11]);
							String[] groupIds = groupId_str.split(",");
							toEmpAddArr = new String[groupIds.length];
							for (int i = 0; i < groupIds.length; i++) {
								toEmpAddArr[i] = groupIds[i];
							}
						}

						String messageText = String.valueOf(templateObj[0][0]);
						messageText = messageText.replace("&lt;#COMPANY_NAME#&gt;", String.valueOf(companyObj[0][0]));
						messageText = messageText.replace("&lt;#ANNIVERSARY_DATE#&gt;", String.valueOf(companyObj[0][1]));
						messageText = messageText.replace("&lt;#EMPLOYEE_NAME#&gt;",String.valueOf(empAnniversaryListObj[0][0]));
						messageText = messageText.replace("&lt;#LOCATION_NAME#&gt;",String.valueOf(empAnniversaryListObj[0][8]));
						
						Object[][] mailBoxData = getServerMailBox("", fromMailIds[0][0], conn);

						AutoAnniversaryModel.out.println();
						AutoAnniversaryModel.out.write("TOTAL NO OF RECORD :" + toEmpAddArr.length);
						sendMail(toEmpAddArr, fromMailIds, "Anniversary Greeting  ", messageText, mailBoxData);
						// }

					} catch (Exception e) {
						AutoAnniversaryModel.out.println();
						AutoAnniversaryModel.out.write("Exception while executing query :" + e);
						e.printStackTrace();
						AutoAnniversaryModel.output.write(e.getMessage());
						AutoAnniversaryModel.output.println();
						return false;
					}  
				}  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public Object[][] getServerMailBox(String empId, String fromMailId,
			Connection conn) {
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
					+ " NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER  "
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
						if (sysMailData != null && sysMailData.length > 0) {
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
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
					+ " ,NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER "
					+ " WHERE SERVER_SYSTEMMAIL_FLAG='Y' ";
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
						if (sysMailData != null && sysMailData.length > 0) {
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
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

		return empMailBoxData;
	}

	public void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, Object[][] mailBoxData) {
		fromMailId = fromMailIds;

		// email. setDebug(true);
		try {
			// HtmlEmail email=null;
			int patch = Integer.parseInt(String.valueOf(mailBoxData[0][16]));
			if (patch == 0) {
				patch = 4;
			}

			if (patch > 0) {
				int count = toMailId.length / patch;
				int rem = toMailId.length % patch;
				if (rem > 0) {
					count = count + 1;
				}
				int k = 0;
				if (patch > toMailId.length) {
					patch = toMailId.length;
				}
				/*
				 * AutoAnniversaryModel.out.println(); AutoAnniversaryModel.out .write(" COUNT
				 * *** NO OF TIMES MAIN FOR LOPP RUNNUNG :" + count);
				 */
				for (int i = 0; i < count; i++) {
					String[] tomailIds = null;
					if (i == count - 1) {
						if (rem > 0) {
							tomailIds = new String[rem];
						} else {
							tomailIds = new String[patch];
						}
					} else {
						tomailIds = new String[patch];
					}
					AutoAnniversaryModel.out.println("\n");
					AutoAnniversaryModel.out.println("Batch Number : " + (i + 1));
					AutoAnniversaryModel.out.println();
					AutoAnniversaryModel.out.println("Email Batch Size : "
							+ tomailIds.length);

					for (int j = 0; j < tomailIds.length; j++) {
						tomailIds[j] = toMailId[k];
						k++;
					}
					// HtmlEmail email=setHtmlEmail(mailBoxData, subject,
					// textBody,
					// request, tomailIds);
					fireCounter = 0;
					fireEmail(mailBoxData, subject, textBody, tomailIds);
				}
			}

		} catch (Exception e) {
			AutoAnniversaryModel.out.println();
			AutoAnniversaryModel.out.write("EXCEPTION 2......");
			e.printStackTrace();
			AutoAnniversaryModel.output.write(e.getMessage());
			AutoAnniversaryModel.output.println();
		}
	}
	
	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		try {
			
			//BEGINS Writting to logs - toMailID
			for (int i = 0; i < tomailIds.length; i++) {
				AutoAnniversaryModel.out.println();
				AutoAnniversaryModel.out.write(" To mail ID >>>>>>>>>>>>>" + String.valueOf(tomailIds[i]));
			}
			//ENDS Writting to logs - toMailID
			AutoAnniversaryModel.out.println();
			AutoAnniversaryModel.out.write("============================");
			AutoAnniversaryModel.out.println();
			//BEGINS Writting to logs - fromMailID
			for (int i = 0; i < fromMailId.length; i++) {
				AutoAnniversaryModel.out.println();
				AutoAnniversaryModel.out.write(" From mail ID >>>>>>>>>>>>>" + String.valueOf(fromMailId[i][0]));
			}
			//ENDS Writting to logs - fromMailID
			
			if (mailCount < fromMailId.length) {
				email = setHtmlEmail(mailBoxData, subject, textBody, tomailIds);
				email.setFrom(fromMailId[mailCount][0], "" + fromMailId[mailCount][0]);
				email.addTo(fromMailId[mailCount][0], "" + fromMailId[mailCount][0]);
				AutoAnniversaryModel.out.println();
				if (String.valueOf(mailBoxData[0][5]).equals("Y")) {
					if (String.valueOf(mailBoxData[0][12]).equals("D")) {
						email.setAuthentication(String.valueOf(mailBoxData[mailCount][3]), 
								String.valueOf(mailBoxData[0][4]));
					} else {
						email.setAuthentication(String
								.valueOf(fromMailId[mailCount][0]), String
								.valueOf(fromMailId[mailCount][1]));
					}
				}
				if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
					try {
						email.setPopBeforeSmtp(true, String.valueOf(mailBoxData[0][7]), 
												String.valueOf(mailBoxData[0][3]), 
												String.valueOf(mailBoxData[0][4]));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")) {
					email.setSmtpWithTLS(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][1]));
				}
				if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
					email.setSmtpWithSSL(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][1]));
				}

				String str = email.send();
				List unsentList = email.getUnsentList();

				if (unsentList.size() > 0) {
					fireCounter++;
					String[] toObj = new String[unsentList.size()];
					for (int i = 0; i < unsentList.size(); i++) {
						String[] splitedStr = unsentList.get(i).toString().split("<");
						toObj[i] = splitedStr[1].substring(0, splitedStr[1].length() - 1);
					}

					AutoAnniversaryModel.out.println("\n Unsent Valid Email address size :"+ unsentList.size());
					List invalidList = email.getInvalidList();
					AutoAnniversaryModel.out.println("\n invalid address :");
					for (int i = 0; i < invalidList.size(); i++) {
						AutoAnniversaryModel.out.println("\n" + invalidList.get(i));
					}
					 
					if (fireCounter < 5) {
						AutoAnniversaryModel.out.println("\n. Resending Emails - counter :" + fireCounter);
						fireEmail(mailBoxData, subject, textBody, toObj);
					}
				}
			} else {
				System.out.println("SYSTEM MAIL IDS OVER....");
			}
		} catch (Exception e) {
			mailCount++;
			e.printStackTrace();
			AutoAnniversaryModel.output.write("" + e);
			AutoAnniversaryModel.output.println();
		}
	}

	
	public void getAnniversaryData(Connection conn) {
		try {
			String settingQry = " SELECT AUTO_SEND, INDIV_DEPT, ALL_DEPT, INDIV_BRANCH, ALL_BRANCH, INDIV_DESG, ALL_DESG, INDIV_DIV, ALL_DIV, TEMPLATE_ID, NVL(GROUP_ID,'N'),NVL(GROUP_MAILID,''), INDIV_EMP FROM HRMS_ANNIVERSARYMAIL_SETTINGS ";
			Object[][] settingObj = SqlModel.getSingleResult(settingQry, conn);
			if (settingObj != null && settingObj.length > 0) {
				if (String.valueOf(settingObj[0][0]).equals("Y"))  {
					String companyQry = " SELECT COMPANY_NAME FROM HRMS_COMPANY ";
					Object[][] companyObj = SqlModel.getSingleResult(companyQry, conn);
					String[][] fromMailIds = getDefaultMailIds(conn);
					Object[][] mailBoxData = getServerMailBox("", fromMailIds[0][0], conn);
					Object templateObj[][] = null;
					if (String.valueOf(settingObj[0][7]).equals("Y")) {
						getIndividualDivMail(conn, settingObj);
					} else {
						String empAnniversaryQry = " SELECT DISTINCT EMP_FNAME,HRMS_EMP_OFFC.EMP_ID,'',NVL(EMP_DEPT,''),NVL(EMP_CENTER,''), "
								+ " NVL(EMP_RANK,''),NVL(EMP_DIV, ''),CENTER_NAME,TO_CHAR(EMP_MARRG_DATE,'DD-Month'),NVL(EMP_LNAME,' ') FROM HRMS_EMP_OFFC "
								+ " LEFT JOIN HRMS_EMP_ADDRESS  ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
								+ " LEFT JOIN HRMS_EMP_PERS  ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_PERS.EMP_ID) "
								+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)"
								+ " WHERE TO_CHAR(EMP_MARRG_DATE,'DD-MM') = TO_CHAR(SYSDATE,'DD-MM') AND EMP_STATUS='S' AND EMP_MARITAL_STATUS='M' ";
						Object[][] empAnniversaryObj = SqlModel.getSingleResult(empAnniversaryQry, conn);

						String templateData = " SELECT TEMPLATE_BODY FROM HRMS_ANNIVERSARY_TEMPLATE WHERE TEMPLATE_ID = "
								+ String.valueOf(settingObj[0][9]);
						templateObj = SqlModel.getSingleResult(templateData, conn);

						if (empAnniversaryObj != null && empAnniversaryObj.length > 0) {
							try {
								for (int i = 0; i < empAnniversaryObj.length; i++) {
									String toEmpQry = " SELECT HRMS_EMP_OFFC.EMP_ID,ADD_EMAIL FROM HRMS_EMP_ADDRESS "
											+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
											+ " WHERE HRMS_EMP_OFFC .EMP_STATUS='S' AND ADD_TYPE='P' "
											+ " AND ADD_EMAIL IS NOT NULL ";
									if (!String.valueOf(settingObj[0][2]).equals("Y")
											&& String.valueOf(settingObj[0][1]).equals("Y")
											&& !(checkNull("" + empAnniversaryObj[i][3])).equals(""))
										toEmpQry += " AND EMP_DEPT=" + empAnniversaryObj[i][3];
									if (!String.valueOf(settingObj[0][4]).equals("Y")
											&& String.valueOf(settingObj[0][3]).equals("Y")
											&& !checkNull(""+ empAnniversaryObj[i][4]).equals(""))
										toEmpQry += " AND EMP_CENTER=" + empAnniversaryObj[i][4];
									if (!String.valueOf(settingObj[0][6]).equals("Y")
											&& String.valueOf(settingObj[0][5]).equals("Y")
											&& !checkNull(""+ empAnniversaryObj[i][5]).equals(""))
										toEmpQry += " AND EMP_RANK=" + empAnniversaryObj[i][5];
									if (!String.valueOf(settingObj[0][8]).equals("Y") && String.valueOf(settingObj[0][7]).equals("Y")
											&& !checkNull("" + empAnniversaryObj[i][6]).equals(""))
										toEmpQry += " AND EMP_DIV=" + empAnniversaryObj[i][6];
									Object[][] toEmpObj = SqlModel.getSingleResult(toEmpQry, conn);
									if (toEmpObj != null && toEmpObj.length > 0) {
										String[] toEmpAddArr = new String[toEmpObj.length];
										for (int j = 0; j < toEmpObj.length; j++) {
											toEmpAddArr[j] = ""+ toEmpObj[j][1];
										}
										String messageText = String.valueOf(templateObj[0][0]);
										messageText = messageText.replace("&lt;#EMPLOYEE_NAME#&gt;",String.valueOf(empAnniversaryObj[i][0]) + "  "+ String.valueOf(empAnniversaryObj[i][9]));
										messageText = messageText.replace("&lt;#COMPANY_NAME#&gt;",String.valueOf(companyObj[0][0]));
										messageText = messageText.replace("&lt;#LOCATION_NAME#&gt;",String.valueOf(empAnniversaryObj[i][7]));
										messageText = messageText.replace("&lt;#ANNIVERSARY_DATE#&gt;",String.valueOf(empAnniversaryObj[i][8]));

										sendMail(toEmpAddArr, fromMailIds,
														"Happy Anniversary To " + empAnniversaryObj[i][0] + " " + empAnniversaryObj[i][9] + "-" + String.valueOf(empAnniversaryObj[i][7]) + "", 
														messageText, mailBoxData);
									}
								}
							} catch (Exception e) {
								logger.info("Exception while executing query : "+ e);
								e.printStackTrace();
							}
						} // end if 2
					}

				} // end if 1
			} // end if Out
			else {
				logger.info("Setting not found for auto anniversary mail");
			}

		} catch (Exception e) {
			logger.info("Exception in getAnniversaryData() method " + e);
			e.printStackTrace();
		}
	}
	
	
	private void sendAnniversaryMail() {
		try {
			Class.forName(getDbDriver());
			Connection conn = java.sql.DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());

			ResourceBundle globalProp = ResourceBundle.getBundle("globalMessages");// Get resource bundle
			Date dt = new Date();
			imagePath = globalProp.getString("catalina_home");
			
			/*====================*/
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
			String date = sd.format(dt);
			String inFileName = "/logs" + date + ".txt";
			File file1 = new File(globalProp.getString("anniversary_logs") + "/" + client);
			if (!file1.exists()) {
				file1.mkdirs();
			}
			File file = new File(file1, inFileName);
			output = new PrintWriter(new FileWriter(file));
			
			
			SimpleDateFormat sdLog = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateLog = sdLog.format(dt);
			String inFileNameLog = "/logs" + dateLog + ".txt";
			File file1Log = new File(globalProp.getString("anniversary_logs") + "/" + client);
			if (!file1Log.exists()) {
				file1Log.mkdirs();
			}
			File file2 = new File(file1Log, inFileNameLog);
			out = new PrintWriter(new FileWriter(file2));
			/*====================*/
			
			AutoAnniversaryModel.out.println();
			AutoAnniversaryModel.out.write("START...");
			AutoAnniversaryModel.output.println();
			ResourceBundle bundle = ResourceBundle.getBundle("org.paradyne.lib.ConnectionModel"); 
			Enumeration list = bundle.getKeys();
			int count = 0;

			Vector vect = new Vector();
			while (list.hasMoreElements()) {
				String client = (String) list.nextElement();
				if (!client.contains(".")) {
					vect.add(client);
				}
			}
			
			for (int j = 0; j < vect.size(); j++) {
				try {
					String client = (String) vect.get(j);
					AutoAnniversaryModel.out.println();
					AutoAnniversaryModel.out.write("client--------------" + client);
					String driver = null;
					String url = null;
					String username = null;
					String password = null;

					try {
						driver = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
								StringEncrypter.DBPOOL_ENCRYPTION_KEY)
								.decrypt(bundle.getString(client + ".driver"));
						url = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
								StringEncrypter.DBPOOL_ENCRYPTION_KEY)
								.decrypt(bundle.getString(client + ".url"));
						username = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
								StringEncrypter.DBPOOL_ENCRYPTION_KEY)
								.decrypt(bundle.getString(client + ".username"));
						password = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
								StringEncrypter.DBPOOL_ENCRYPTION_KEY)
								.decrypt(bundle.getString(client + ".password"));
					} catch (Exception e) {
						e.printStackTrace();
						AutoAnniversaryModel.out.println();
						AutoAnniversaryModel.out.write(e.getMessage());
					} 
					
					try {
						if (!conn.isClosed()) {
							AutoAnniversaryModel.output.println();
							try {
								new AutoAnniversaryModel().getAnniversaryData(conn);
							} catch (Exception e) {
								logger.error("Exception in getAnniversaryData----------");
							}

						}
					} catch (Exception e) {
						conn.close();
						e.printStackTrace();
						AutoAnniversaryModel.out.println();
						AutoAnniversaryModel.out.write("" + e);
						AutoAnniversaryModel.out.println();
					}// end of catch
					finally {
						conn.close();
						AutoAnniversaryModel.output .write("****************End of AutoAnniversaryModel mails**************************");
					}
					AutoAnniversaryModel.out.println();
					AutoAnniversaryModel.out.write("client--------------" + j);
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					AutoAnniversaryModel.out.println();
					AutoAnniversaryModel.out.write("" + e);
				}
			} // end of loop
			output.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}


}
