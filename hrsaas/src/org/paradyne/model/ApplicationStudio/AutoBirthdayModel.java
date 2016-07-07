/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.email.HtmlEmail;
import org.paradyne.lib.email.MultiPartEmail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.struts.action.ApplicationStudio.jobScheduling.JobLogger;

/**
 * @author Pankaj_Jain
 * 
 */
public class AutoBirthdayModel extends ModelBase implements Job {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AutoBirthdayModel.class);
	
	String[][] fromMailId = null;
	int mailCount = 0;
	HtmlEmail email = null;
	//static PrintWriter output = null;
	static PrintWriter out = null;
	static String imagePath = "";
	int fireCounter = 0;
	public HttpServletRequest request;
	private String client = "";
	private Connection dbConn = null;
	private String autoUploadID = "";
	private static String dbDriver = null;
	private static String dbUrl = null;
	private static String dbUsername = null;
	private static String dbPassword = null;

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public static String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public void execute(JobExecutionContext jobExecutionContext) {
		try {
			dbConn = getConnection(jobExecutionContext);
			autoUploadID = getAutoUploadId(jobExecutionContext);
			client = jobExecutionContext.getJobDetail().getJobDataMap().getString("CLIENT");
			System.out.println("CLIENT >>>>>>>>>>>>>>>>>"+client);
			sendBirthDayMail(dbConn);
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
 
			String autoUploadID = jobExecutionContext.getJobDetail()
					.getJobDataMap().getString("NAME").split("_")[1];

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

	private void sendBirthDayMail(Connection conn ) {
		try {
			
			
			/*===============================*/
			ResourceBundle globalProp = ResourceBundle.getBundle("globalMessages");// Get resource bundle
			Date dt = new Date();
			imagePath = globalProp.getString("catalina_home");
			
			File file1 = new File(globalProp.getString("birthday_logs") + "/" + client );
			if (!file1.exists()) {
				file1.mkdirs();
			}
			SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = sd.format(dt);
			String inFileName = "/logs" + date + ".txt";
			File file = new File(file1 ,inFileName);
			out = new PrintWriter(new FileWriter(file));
			
			
			File file1Log = new File(globalProp.getString("birthday_logs") + "/" + client);
			if (!file1Log.exists()) {
				file1Log.mkdirs();
			}
			/*SimpleDateFormat sdLog = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateLog = sdLog.format(dt);
			String inFileNameLog = "/logs" + dateLog + ".txt";
			File file2 = new File(file1Log, inFileNameLog);
			out = new PrintWriter(new FileWriter(file2));*/
			/*===============================*/
			
			AutoBirthdayModel.out.println();
			AutoBirthdayModel.out.write("START...");
			AutoBirthdayModel.out.println();

				try {
				
					AutoBirthdayModel.out.println();
					AutoBirthdayModel.out.write("client--------------" + client);
					try {
						
						if (!conn.isClosed()) {
							AutoBirthdayModel.out.println();
							try {
								new AutoBirthdayModel().getBirthdayData(conn);
							} catch (Exception e) {
								System.out
										.println("Exception in getBirthdayData----------");
							}

						}
					} catch (Exception e) {
						conn.close();
						e.printStackTrace();
						AutoBirthdayModel.out.println();
						AutoBirthdayModel.out.write("" + e);
						AutoBirthdayModel.out.println();
					}// end of catch
					finally {
						conn.close();
						AutoBirthdayModel.out
								.write("****************End of AutoBirthdayModel mails**************************");
					}
					AutoBirthdayModel.out.println();
				
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					AutoBirthdayModel.out.println();
					AutoBirthdayModel.out.write("" + e);
				}
			
			out.close();
			out.close();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getBirthdayData(Connection conn) {
		try {
			 
			String settingQry = "SELECT AUTO_SEND,INDIV_DEPT,ALL_DEPT,INDIV_BRANCH,ALL_BRANCH,INDIV_DESG,ALL_DESG,INDIV_DIV,ALL_DIV,TEMPLATE_ID " +
					",NVL(TEMP_FLAG,'S'),NVL(INDIV_EMP,'N'),NVL(INCLUDE_DEPT,'Y'), NVL(INCLUDE_DESG,'N'), NVL(INCLUDE_EMAIL,'N'), " +
					"NVL(INCLUDE_PHONE,'N'),NVL(GROUP_ID,'N'),NVL(GROUP_MAILID,''),NVL(EXCEPTION_EMP,''), NVL(INCLUDE_TITLE,'N') FROM HRMS_BIRTHDAYMAIL_SETTINGS ";

			Object[][] settingObj = SqlModel.getSingleResult(settingQry, conn);
			if (settingObj != null && settingObj.length > 0) // if Out
			{
				if (String.valueOf(settingObj[0][0]).equals("Y")) // if 1
				{
					String companyQry = " SELECT COMPANY_NAME ,TO_CHAR(SYSDATE,'DDth MON') FROM HRMS_COMPANY ";
					Object[][] companyObj = SqlModel.getSingleResult(
							companyQry, conn);
					Object templateObj[][] = null;
					String[][] fromMailIds = getDefaultMailIds(conn);
					
					if (String.valueOf(settingObj[0][7]).equals("Y")) {
						getIndividualDivMail(conn, settingObj);
					} else {
						String empBdayList = "SELECT  EMP_FNAME,HRMS_EMP_OFFC.EMP_ID,NVL(EMP_DEPT,''),"
								+ " NVL(EMP_CENTER,''),  NVL(EMP_RANK,''),NVL(EMP_DIV, ''),"
								+ " NVL(INITCAP(EMP_FNAME),' ')||' '||NVL(INITCAP(EMP_LNAME),' '),"
								+ " NVL(DEPT_NAME,''),NVL(INITCAP(CENTER_NAME),' '),NVL(RANK_NAME,' ') "
								+ " ,NVL(ADD_EMAIL,' '),NVL(ADD_MOBILE,' '),NVL(TITLE_NAME,' ') "
								+ "	FROM HRMS_EMP_OFFC   "
								+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
								+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
								+ "	INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
								+ "	INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
								+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE)"
								+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')"
								+ "	WHERE TO_CHAR(EMP_DOB,'DD-MM') = TO_CHAR(SYSDATE,'DD-MM') AND EMP_STATUS='S' ";
						try {
							if (checkNull(String.valueOf(settingObj[0][18])) != null
									&& !checkNull(
											String.valueOf(settingObj[0][18]))
											.equals("")
									&& !checkNull(
											String.valueOf(settingObj[0][18]))
											.equals("null")) {
								empBdayList += " AND  HRMS_EMP_OFFC.EMP_ID NOT IN ("
										+ String.valueOf(settingObj[0][18])
										+ ") ";
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
								Object[][] empBdayListObj = SqlModel.getSingleResult(
								empBdayList, conn);

						String templateData = " SELECT TEMPLATE_BODY,TEMPLATE_ID FROM HRMS_BIRTHDAYTEMPLATE_HDR WHERE TEMPLATE_ID IN ( "
								+ " "
								+ String.valueOf(settingObj[0][9])
								+ " ) AND TEMPLATE_USE_FLAG='N' ORDER BY TEMPLATE_ID ";


						if (empBdayListObj != null && empBdayListObj.length > 0) // if 2
						{
							try {

								String dept = "";
								String branch = "";
								String rank = "";
								String div = "";
								String empId = "";
								

								String replaceWithTable = " <center><DIV style='align:center;width: 750px;'> ";

								for (int j = 0; j < empBdayListObj.length; j++) {
									
									dept += String
											.valueOf(empBdayListObj[j][2])
											+ ",";
									branch += String
											.valueOf(empBdayListObj[j][3])
											+ ",";
									rank += String
											.valueOf(empBdayListObj[j][4])
											+ ",";
									div += String.valueOf(empBdayListObj[j][5])
											+ ",";

									empId += String
											.valueOf(empBdayListObj[j][1])
											+ ",";		
									
									if(String.valueOf(settingObj[0][19]).equals("Y")){
									replaceWithTable += "<DIV style='float:left;width:320px;text-align:left'>"
											+ String.valueOf(empBdayListObj[j][12])+" "+(String
											.valueOf(empBdayListObj[j][6]))
											+ "</DIV><DIV style='float:left;text-align:left'> ";
									}
									else{
										replaceWithTable += "<DIV style='float:left;width:320px;text-align:left'>"
											+ String
											.valueOf(empBdayListObj[j][6])
											+ "</DIV><DIV style='float:left;text-align:left'> ";
									}
									/*******************************************
									 * if desg....yes add desg
									 */
									if (String.valueOf(settingObj[0][13])
											.equals("Y")) {
										replaceWithTable += String
												.valueOf(empBdayListObj[j][9])
												+ "<br>  ";

									}
									/*******************************************
									 * 
									 * 
									 * /***********************************************
									 * if dept....yes add dept
									 */
									if (String.valueOf(settingObj[0][12])
											.equals("Y")) {
										replaceWithTable += String
												.valueOf(empBdayListObj[j][7])
												+ "<br>  "
												+ String
														.valueOf(empBdayListObj[j][8])
												+ "<br>  ";
									}
									/*******************************************
									 * 
									 * /***********************************************
									 * if email....yes add email
									 */
									if (String.valueOf(settingObj[0][14])
											.equals("Y")) {
										replaceWithTable += String
												.valueOf(empBdayListObj[j][10])
												+ "<br>  ";
									}
									/*******************************************
									 * if phone....yes add phone
									 */
									if (String.valueOf(settingObj[0][15])
											.equals("Y")) {
										replaceWithTable += String
												.valueOf(empBdayListObj[j][11]);
									}
									//replaceWithTable += "<br><br>";
									replaceWithTable += "</DIV><br>";

								}

								replaceWithTable += " </DIV></center> ";

								dept = dept.substring(0, dept.length() - 1);
								branch = branch.substring(0,
										branch.length() - 1);
								rank = rank.substring(0, rank.length() - 1);
								div = div.substring(0, div.length() - 1);
								empId = empId.substring(0, empId.length() - 1);
								//title = title.substring(0,title.length()-1);
								// int cnt=0;
								// for (int i = 0; i < empBdayObj.length; i++) {
								AutoBirthdayModel.out.write("Birthday of Employee====");
								for (int i = 0; i < empBdayListObj.length; i++) {
									AutoBirthdayModel.out.write(i+">"+checkNull(String.valueOf(empBdayListObj[i][0])));
								}
								AutoBirthdayModel.out.println();
								templateObj = SqlModel.getSingleResult(
										templateData, conn);

								if (templateObj != null
										&& templateObj.length == 0) {
									String update = "UPDATE HRMS_BIRTHDAYTEMPLATE_HDR SET TEMPLATE_USE_FLAG='N' ";
									SqlModel.singleExecute(update, conn);
									templateObj = SqlModel.getSingleResult(
											templateData, conn);
								}

								String toEmpQry = " SELECT HRMS_EMP_OFFC.EMP_ID,ADD_EMAIL FROM HRMS_EMP_ADDRESS "
										+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
										+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
										+ " WHERE   HRMS_EMP_OFFC .EMP_STATUS='S' AND ADD_TYPE='P' "
										+ " AND ADD_EMAIL IS NOT NULL ";

								/**
								 * FOR INDIVIDUAL EMPLOYEE
								 */
								if (String.valueOf(settingObj[0][11]).equals(
										"Y")) {
									toEmpQry += " AND HRMS_EMP_OFFC.EMP_ID IN ("
											+ empId + ")";
								} else {
									if (!String.valueOf(settingObj[0][2])
											.equals("Y")
											&& String.valueOf(settingObj[0][1])
													.equals("Y")
											&& !(checkNull("" + dept))
													.equals(""))
										toEmpQry += " AND EMP_DEPT IN (" + dept
												+ ")";
									if (!String.valueOf(settingObj[0][4])
											.equals("Y")
											&& String.valueOf(settingObj[0][3])
													.equals("Y")
											&& !checkNull("" + branch).equals(
													""))
										toEmpQry += " AND EMP_CENTER IN ("
												+ branch + ")";
									if (!String.valueOf(settingObj[0][6])
											.equals("Y")
											&& String.valueOf(settingObj[0][5])
													.equals("Y")
											&& !checkNull("" + rank).equals(""))
										toEmpQry += " AND EMP_RANK IN (" + rank
												+ ")";
									if (!String.valueOf(settingObj[0][8])
											.equals("Y")
											&& String.valueOf(settingObj[0][7])
													.equals("Y")
											&& !checkNull("" + div).equals(""))
										toEmpQry += " AND EMP_DIV IN (" + div
												+ ")";

								}

								toEmpQry += "  ORDER BY EMP_ID ";
								Object[][] toEmpObj = SqlModel.getSingleResult(
										toEmpQry, conn);

								// "reeba.joseph@glodyne.com",
								String[] toEmpAddArr = new String[toEmpObj.length];
								if (toEmpObj != null && toEmpObj.length > 0) {
									for (int j = 0; j < toEmpObj.length; j++) {
										toEmpAddArr[j] = String
												.valueOf(toEmpObj[j][1]);
									} // end of loop
								} // end of if
								else {
									return false;
								} // end of else

								// Added by Lakkichand for sending mails to
								// Group mail Id
								if (String.valueOf(settingObj[0][16]).equals(
										"Y")) {
									String groupId_str = String
											.valueOf(settingObj[0][17]);
									String[] groupIds = groupId_str.split(",");
									toEmpAddArr = new String[groupIds.length];
									for (int i = 0; i < groupIds.length; i++) {
										toEmpAddArr[i] = groupIds[i];
									}
								}

								String messageText = String
										.valueOf(templateObj[0][0]);
								messageText = messageText.replace(
										"&lt;#COMPANY_NAME#&gt;", String
												.valueOf(companyObj[0][0]));

								messageText = messageText.replace(
										"&lt;#BIRTH_DATE#&gt;", String
												.valueOf(companyObj[0][1]));

								messageText = messageText.replace(
										"&lt;#EMP_LIST#&gt;", replaceWithTable);
								
								Object[][] mailBoxData = getServerMailBox("",
										fromMailIds[0][0], conn);

								AutoBirthdayModel.out.println();
								AutoBirthdayModel.out.write("TOTAL NO OF RECORD :"
										+ toEmpAddArr.length);
								if (String.valueOf(settingObj[0][10]).equals(
										"S")) {
									sendMail(toEmpAddArr, fromMailIds,
											"Birthday Greeting  ", messageText,
											mailBoxData);
								} else {
									String[] multiMessageText = new String[templateObj.length];
									for (int j = 0; j < multiMessageText.length; j++) {
										String multimessageText = String
												.valueOf(templateObj[j][0]);
										multimessageText = "Birthday Greeting ";
										multiMessageText[j] = multimessageText;
									}

									String textBodyMss = "";
									textBodyMss = String
											.valueOf(multiMessageText[0]);
									String update = "UPDATE HRMS_BIRTHDAYTEMPLATE_HDR SET TEMPLATE_USE_FLAG='Y' WHERE TEMPLATE_ID="
											+ templateObj[0][1];
									SqlModel.singleExecute(update, conn);

									sendMail(toEmpAddArr, fromMailIds,
											"Birthday Greetings", messageText,
											mailBoxData);
								}

								// }

							} catch (Exception e) {
								AutoBirthdayModel.out.println();
								AutoBirthdayModel.out
										.write("Exception while executing query :"
												+ e);
								e.printStackTrace();
								AutoBirthdayModel.out.write(e.getMessage());
								AutoBirthdayModel.out.println();
								return false;
							} // end of catch
						} // end if 2
					}

				} // end if 1
			} // end if Out
			else {
				AutoBirthdayModel.out.println();
				AutoBirthdayModel.out
						.write("Setting not found for auto birthday mail");
				return false;
			}

		} catch (Exception e) {
			AutoBirthdayModel.out.println();
			AutoBirthdayModel.out.write("Exception in method " + e);
			e.printStackTrace();
			AutoBirthdayModel.out.write(e.getMessage());
			AutoBirthdayModel.out.println();
		}
		return true;
	}
	
	private boolean getIndividualDivMail(Connection conn, Object[][] settingObj) {

		try {
			String companyQry = " SELECT COMPANY_NAME ,TO_CHAR(SYSDATE,'DDth MON') FROM HRMS_COMPANY ";
			Object[][] companyObj = SqlModel.getSingleResult(companyQry, conn);
			Object templateObj[][] = null;
			String[][] fromMailIds = getDefaultMailIds(conn);
			String divQuery = "SELECT DIV_ID FROM HRMS_DIVISION ";
			Object[][] divObj = SqlModel.getSingleResult(divQuery, conn);
			for (int k = 0; k < divObj.length; k++) {

				String empBdayList = "SELECT  EMP_FNAME,HRMS_EMP_OFFC.EMP_ID,NVL(EMP_DEPT,''),"
						+ " NVL(EMP_CENTER,''),  NVL(EMP_RANK,''),NVL(EMP_DIV, ''),"
						+ " NVL(INITCAP(EMP_FNAME),' ')||' '||NVL(INITCAP(EMP_LNAME),' '),"
						+ " NVL(DEPT_NAME,''),NVL(INITCAP(CENTER_NAME),' '),NVL(RANK_NAME,' ') "
						+ " ,NVL(ADD_EMAIL,' '),NVL(ADD_MOBILE,' ') "
						+ "	FROM HRMS_EMP_OFFC   "
						+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
						+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ "	INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
						+ "	INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
						+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')"
						+ "	WHERE TO_CHAR(EMP_DOB,'DD-MM') = TO_CHAR(SYSDATE,'DD-MM') AND EMP_STATUS='S' AND EMP_DIV="
						+ String.valueOf(divObj[k][0]);
				Object[][] empBdayListObj = SqlModel.getSingleResult(
						empBdayList, conn);

				String templateData = " SELECT TEMPLATE_BODY,TEMPLATE_ID FROM HRMS_BIRTHDAYTEMPLATE_HDR WHERE TEMPLATE_ID IN ( "
						+ " "
						+ String.valueOf(settingObj[0][9])
						+ " ) AND TEMPLATE_USE_FLAG='N' ORDER BY TEMPLATE_ID ";

				/*
				 * String getMailBox = " SELECT MAILBOX_SERVER,
				 * MAILBOX_PROTOCOL, MAILBOX_PORT, MAILBOX_USERID,
				 * MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_FLAG FROM " + "
				 * HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG='O' ";
				 * 
				 * Object[][] mailBoxData =
				 * SqlModel.getSingleResult(getMailBox,conn);
				 */

				if (empBdayListObj != null && empBdayListObj.length > 0) // if 2
				{
					try {

						String dept = "";
						String branch = "";
						String rank = "";
						String div = "";
						String empId = "";

						String replaceWithTable = " <center><DIV style='align:center;width: 750px;'> ";

						for (int j = 0; j < empBdayListObj.length; j++) {

							dept += String.valueOf(empBdayListObj[j][2]) + ",";
							branch += String.valueOf(empBdayListObj[j][3])
									+ ",";
							rank += String.valueOf(empBdayListObj[j][4]) + ",";
							div += String.valueOf(empBdayListObj[j][5]) + ",";

							empId += String.valueOf(empBdayListObj[j][1]) + ",";
							/*
							 * replaceWithTable += String
							 * .valueOf(empBdayListObj[j][6]) + "&nbsp;-\t\t\t" +
							 * String.valueOf(empBdayListObj[j][7]) +
							 * "&nbsp;-\t\t\t" +
							 * String.valueOf(empBdayListObj[j][8]) + "\t\t\t
							 * <br>";
							 */
							replaceWithTable += "<DIV style='float:left;width:320px;text-align:left'>"
									+ String.valueOf(empBdayListObj[j][6])
									+ "</DIV><DIV style='float:left;text-align:left'> ";
							
							AutoBirthdayModel.out.write("Birthday of Employee===="+ String.valueOf(empBdayListObj[j][6]));
							
							/***************************************************
							 * if desg....yes add desg
							 */
							if (String.valueOf(settingObj[0][13]).equals("Y")) {
								replaceWithTable += String
										.valueOf(empBdayListObj[j][9])
										+ "<br>  ";

							}
							/***************************************************
							 * 
							 * 
							 * /***********************************************
							 * if dept....yes add dept
							 */
							if (String.valueOf(settingObj[0][12]).equals("Y")) {
								replaceWithTable += String
										.valueOf(empBdayListObj[j][7])
										+ "<br>  "
										+ String.valueOf(empBdayListObj[j][8])
										+ "<br>  ";
							}
							/***************************************************
							 * 
							 * /***********************************************
							 * if email....yes add email
							 */
							if (String.valueOf(settingObj[0][14]).equals("Y")) {
								replaceWithTable += String
										.valueOf(empBdayListObj[j][10])
										+ "<br>  ";
							}
							/***************************************************
							 * if phone....yes add phone
							 */
							if (String.valueOf(settingObj[0][15]).equals("Y")) {
								replaceWithTable += String
										.valueOf(empBdayListObj[j][11]);
							}
							//replaceWithTable += "<br><br>";
							//replaceWithTable += "</DIV><br><br><br>";
							
							replaceWithTable += "</DIV><br>";

						}

						replaceWithTable += " </DIV></center> ";

						AutoBirthdayModel.out.write("Employee List html :"+replaceWithTable);
						
						dept = dept.substring(0, dept.length() - 1);
						branch = branch.substring(0, branch.length() - 1);
						rank = rank.substring(0, rank.length() - 1);
						div = div.substring(0, div.length() - 1);
						empId = empId.substring(0, empId.length() - 1);
						// int cnt=0;
						// for (int i = 0; i < empBdayObj.length; i++) {
						
						AutoBirthdayModel.out.println();
						templateObj = SqlModel.getSingleResult(templateData,
								conn);

						if (templateObj != null && templateObj.length == 0) {
							String update = "UPDATE HRMS_BIRTHDAYTEMPLATE_HDR SET TEMPLATE_USE_FLAG='N' ";
							SqlModel.singleExecute(update, conn);
							templateObj = SqlModel.getSingleResult(
									templateData, conn);
						}

						String toEmpQry = " SELECT HRMS_EMP_OFFC.EMP_ID,ADD_EMAIL FROM HRMS_EMP_ADDRESS "
								+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
								+ " INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID AND HRMS_DIVISION.SEND_RECEIVE_BDAY != 'N') "
								+ " WHERE   HRMS_EMP_OFFC .EMP_STATUS='S' AND ADD_TYPE='P' "
								+ " AND ADD_EMAIL IS NOT NULL  AND EMP_DIV="
								+ String.valueOf(divObj[k][0]);

						toEmpQry += "  ORDER BY EMP_ID ";
						Object[][] toEmpObj = SqlModel.getSingleResult(
								toEmpQry, conn);

						 
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
						if (String.valueOf(settingObj[0][16]).equals("Y")) {
							String groupId_str = String
									.valueOf(settingObj[0][17]);
							String[] groupIds = groupId_str.split(",");
							toEmpAddArr = new String[groupIds.length];
							for (int i = 0; i < groupIds.length; i++) {
								toEmpAddArr[i] = groupIds[i];
							}
						}

						String messageText = String.valueOf(templateObj[0][0]);
						messageText = messageText.replace(
								"&lt;#COMPANY_NAME#&gt;", String
										.valueOf(companyObj[0][0]));

						messageText = messageText.replace(
								"&lt;#BIRTH_DATE#&gt;", String
										.valueOf(companyObj[0][1]));

						messageText = messageText.replace("&lt;#EMP_LIST#&gt;",
								replaceWithTable);

						Object[][] mailBoxData = getServerMailBox("",
								fromMailIds[0][0], conn);

						AutoBirthdayModel.out.println();
						AutoBirthdayModel.out.write("TOTAL NO OF RECORD :"
								+ toEmpAddArr.length);
						if (String.valueOf(settingObj[0][10]).equals("S")) {
							sendMail(toEmpAddArr, fromMailIds,
									"Birthday Greeting  ", messageText,
									mailBoxData);
						} else {
							String[] multiMessageText = new String[templateObj.length];
							for (int j = 0; j < multiMessageText.length; j++) {
								String multimessageText = String
										.valueOf(templateObj[j][0]);
								multimessageText = "Birthday Greeting ";
								multiMessageText[j] = multimessageText;
							}

							String textBodyMss = "";
							textBodyMss = String.valueOf(multiMessageText[0]);
							String update = "UPDATE HRMS_BIRTHDAYTEMPLATE_HDR SET TEMPLATE_USE_FLAG='Y' WHERE TEMPLATE_ID="
									+ templateObj[0][1];
							SqlModel.singleExecute(update, conn);

							sendMail(toEmpAddArr, fromMailIds,
									"Birthday Greetings", messageText,
									mailBoxData);
						}

						// }

					} catch (Exception e) {
						AutoBirthdayModel.out.println();
						AutoBirthdayModel.out
								.write("Exception while executing query :" + e);
						e.printStackTrace();
						AutoBirthdayModel.out.write(e.getMessage());
						AutoBirthdayModel.out.println();
						return false;
					} // end of catch
				} // end if 2
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
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
				 * AutoBirthdayModel.out.println(); AutoBirthdayModel.out .write(" COUNT
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
					AutoBirthdayModel.out.println("\n");
					AutoBirthdayModel.out.println("Batch Number : " + (i + 1));
					AutoBirthdayModel.out.println();
					AutoBirthdayModel.out.println("Email Batch Size : "
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
			AutoBirthdayModel.out.println();
			AutoBirthdayModel.out.write("EXCEPTION 2......");
			e.printStackTrace();
			AutoBirthdayModel.out.write(e.getMessage());
			AutoBirthdayModel.out.println();
		}
	}
	
	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {

		try {
			
			//BEGINS Writting to logs - toMailID
			for (int i = 0; i < tomailIds.length; i++) {
				AutoBirthdayModel.out.println();
				AutoBirthdayModel.out.write(" To mail ID >>>>>>>>>>>>>" + String.valueOf(tomailIds[i]));
			}
			//ENDS Writting to logs - toMailID
			AutoBirthdayModel.out.println();
			AutoBirthdayModel.out.write("============================");
			AutoBirthdayModel.out.println();
			//BEGINS Writting to logs - fromMailID
			for (int i = 0; i < fromMailId.length; i++) {
				AutoBirthdayModel.out.println();
				AutoBirthdayModel.out.write(" From mail ID >>>>>>>>>>>>>" + String.valueOf(fromMailId[i][0]));
			}
			//ENDS Writting to logs - fromMailID
			
			
			
			
			if (mailCount < fromMailId.length) {
				/*
				 * if(mailCount==0){ email=setHtmlEmail(mailBoxData, subject,
				 * textBody, request, tomailIds); }
				 */
				email = setHtmlEmail(mailBoxData, subject, textBody, tomailIds);
				email.setFrom(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);
				email.addTo(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);
				AutoBirthdayModel.out.println();
				/*
				 * AutoBirthdayModel.out.write("USER NAME " +
				 * fromMailId[mailCount][0] + " PASS " +
				 * fromMailId[mailCount][1]);
				 */
				if (String.valueOf(mailBoxData[0][5]).equals("Y")) {
					if (String.valueOf(mailBoxData[0][12]).equals("D")) {
						email.setAuthentication(String
								.valueOf(mailBoxData[mailCount][3]), String
								.valueOf(mailBoxData[0][4]));

					} else {
						email.setAuthentication(String
								.valueOf(fromMailId[mailCount][0]), String
								.valueOf(fromMailId[mailCount][1]));

					}
				}
				if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
					try {
						email.setPopBeforeSmtp(true, String
								.valueOf(mailBoxData[0][7]), String
								.valueOf(mailBoxData[0][3]), String
								.valueOf(mailBoxData[0][4]));

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
				// mailCount ++;
				/*
				 * AutoBirthdayModel.out.println(); AutoBirthdayModel.out.write("send
				 * Error :" + str);
				 */
				List unsentList = email.getUnsentList();

				if (unsentList.size() > 0) {
					fireCounter++;
					String[] toObj = new String[unsentList.size()];
					for (int i = 0; i < unsentList.size(); i++) {
						// AutoBirthdayModel.out.println();
						// AutoBirthdayModel.out.write(" unsentList...Valid
						// "+unsentList.get(i));
						String[] splitedStr = unsentList.get(i).toString()
								.split("<");
						toObj[i] = splitedStr[1].substring(0, splitedStr[1]
								.length() - 1);

					}

					AutoBirthdayModel.out
							.println("\n Unsent Valid Email address size :"
									+ unsentList.size());

					List invalidList = email.getInvalidList();
					AutoBirthdayModel.out.println("\n invalid address :");
					for (int i = 0; i < invalidList.size(); i++) {
						AutoBirthdayModel.out.println("\n" + invalidList.get(i));
					}
					 
					if (fireCounter < 5) {
						AutoBirthdayModel.out
								.println("\n. Resending Emails - counter :"
										+ fireCounter);
						fireEmail(mailBoxData, subject, textBody, toObj);
					}
					// addTo_CC(email, toObj);
					// str= email.send();
				}
				// AutoBirthdayModel.out.println();AutoBirthdayModel.out.write("MAIL SENT
				// SUCCESSFULLY *********** ... FOR SYSMAIL ID "+mailCount);

			} else {
				System.out.println("SYSTEM MAIL IDS OVER....");
			}

			 
		} catch (Exception e) {
			/*
			 * email=setHtmlEmail(mailBoxData, subject, textBody, request,
			 * tomailIds); mailCount++;
			 */
			mailCount++;
			e.printStackTrace();
			AutoBirthdayModel.out.write("" + e);
			AutoBirthdayModel.out.println();
			// fireEmail(mailBoxData, subject, textBody, request, tomailIds);
		}

	}
	
	public String getHtmlText(HtmlEmail email, String textBody) {
		textBody = textBody.replace("src=\"", "$");
		StringTokenizer st = new StringTokenizer(textBody, "$");
		int counter = 0;
		String replacedString = "";
		String cid = "";
		while (st.hasMoreTokens()) {
			String new_tokens = st.nextToken();
			// AutoBirthday.out.println();AutoBirthday.out.write("new_tokens :"
			// + new_tokens);
			// AutoBirthday.out.println();AutoBirthday.out.write("\n-----------------------------------------------");
			if (counter != 0) {
				int endofURL = new_tokens.indexOf("\"");
				// AutoBirthday.out.println();AutoBirthday.out.write("...endofURL.."
				// + endofURL);
				String src = new_tokens.substring(0, endofURL);
				// AutoBirthday.out.println();AutoBirthday.out.write("src====="+src);
				// if (!src.startsWith("http")) {
				// src =
				// "D:\\Struts2\\hrsaas-21\\12\\2009\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\"+src;
				src = imagePath + "\\webapps" + src;
				// }
				// AutoBirthday.out.println();AutoBirthday.out.write("src====="+src);
				try {
					cid = email.embed(new File(src), "Image001" + counter);
					// URL url = new URL(src);
					// cid = email.embed(url, "Image001"+counter);
				} catch (Exception e) {
					e.printStackTrace();
				}
				replacedString += "src=\"cid:"
						+ cid
						+ "\""
						+ new_tokens.substring(endofURL + 1, new_tokens
								.length());
			} else {
				replacedString += new_tokens;
			}
			counter++;
		}

		// AutoBirthday.out.println();AutoBirthday.out.write("messages :" +
		// replacedString);
		return replacedString;

	}
	public HtmlEmail setHtmlEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		HtmlEmail email = new HtmlEmail();
		String replacedString = "";

		try {
			email.setHostName(String.valueOf(mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(mailBoxData[0][1])));// 80
			email.setSubject("" + subject);
			replacedString = getFinalBdayMessage(getHtmlText(email, textBody));
			// replacedString=getHtmlTextWithBackground(email, replacedString,
			// request);
			email.setHtmlMsg(replacedString);
			email
					.setTextMsg("Your email client does not support HTML messages");
			addTo_CC(email, tomailIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
	
	public HtmlEmail addTo_CC(HtmlEmail email, String[] toObj) {
		try {
			// AutoBirthday.out.println();
			// AutoBirthday.out.write("************************************************************************************");
			for (int i = 0; i < toObj.length; i++) {
				// AutoBirthday.out.println();
				// AutoBirthday.out.write(" "+toObj[i]+" ");
				// AutoBirthday.out.println();
				email.addBcc(toObj[i]);
			}
			// AutoBirthday.out.println();
			// AutoBirthday.out.write("************************************************************************************");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return email;
	}

	public MultiPartEmail addTo(MultiPartEmail email, String[] toObj) {
		try {
			for (int i = 0; i < toObj.length; i++) {
				email.addTo(toObj[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}



	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

}
