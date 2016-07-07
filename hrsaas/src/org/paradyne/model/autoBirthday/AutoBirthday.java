package org.paradyne.model.autoBirthday;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.email.HtmlEmail;
import org.paradyne.lib.email.MultiPartEmail;

public class AutoBirthday {
	String[][] fromMailId = null;
	int mailCount = 0;
	HtmlEmail email = null;
	static PrintWriter output = null;
	static PrintWriter out = null;
	static String imagePath = "";
	/**
	 * @param args
	 */

	static InputStream is;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			ResourceBundle globalProp = ResourceBundle
					.getBundle("globalMessages");// Get resource bundle
			Date dt = new Date();
			imagePath = globalProp.getString("catalina_home");
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
			String date = sd.format(dt);
			String inFileName = globalProp.getString("birthday_logs")
					+ "\\logs" + date + ".txt";
			File file1 = new File(globalProp.getString("birthday_logs") + "\\");
			SimpleDateFormat sdLog = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateLog = sdLog.format(dt);
			String inFileNameLog = globalProp.getString("birthday_logs")
					+ "\\logs" + dateLog + ".txt";

			File file1Log = new File(globalProp.getString("birthday_logs")
					+ "\\");
			if (!file1.exists()) {
				file1.mkdirs();
			}
			File file = new File(inFileName);

			if (!file1Log.exists()) {
				file1Log.mkdirs();
			}
			File file2 = new File(inFileNameLog);

			output = new PrintWriter(new FileWriter(file));
			out = new PrintWriter(new FileWriter(file2));

			AutoBirthday.out.println();
			AutoBirthday.out.write("START...");
			AutoBirthday.output.println();
			ResourceBundle bundle = ResourceBundle
					.getBundle("org.paradyne.lib.ConnectionModel");// Get
			// resource
			// bundle

			Enumeration list = bundle.getKeys();
			int count = 0;

			Vector vect = new Vector();
			while (list.hasMoreElements()) {
				// AutoBirthday.out.println();AutoBirthday.out.write("Counter-----"+count+"-------"+list.nextElement());
				String client = (String) list.nextElement();
				if (!client.contains(".")) {
					vect.add(client);
				}

			}
			// AutoBirthday.out.println();
			// AutoBirthday.out.write("vect.size()--------------" +
			// vect.size());
			for (int j = 0; j < vect.size(); j++) {
				try {
					String client = (String) vect.get(j);
					System.out.println("client  :  " + client);

					AutoBirthday.out.println();
					AutoBirthday.out.write("client--------------" + client);
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
						AutoBirthday.out.println();
						AutoBirthday.out.write(e.getMessage());
					} // end of catch
					Connection conn = null;
					try {
						if (driver != null) {
							/*
							 * AutoBirthday.out.println();
							 * AutoBirthday.out.write("url==========" + url);
							 * AutoBirthday.out.println();
							 * AutoBirthday.out.write("username==========" +
							 * username); AutoBirthday.out.println();
							 * AutoBirthday.out.write("password==========" +
							 * password);
							 */
							Class.forName(driver);
							conn = java.sql.DriverManager.getConnection(url,
									username, password);
						} // end of if
						if (!conn.isClosed()) {
							AutoBirthday.output.println();
							try {
								new AutoBirthday().getBirthdayData(conn);
							} catch (Exception e) {
								System.out
										.println("Exception in getBirthdayData----------");
							}

						}
					} catch (Exception e) {
						conn.close();
						e.printStackTrace();
						AutoBirthday.out.println();
						AutoBirthday.out.write("" + e);
						AutoBirthday.out.println();
					}// end of catch
					finally {
						conn.close();
						AutoBirthday.output
								.write("****************End of AutoBirthday mails**************************");
					}
					AutoBirthday.out.println();
					AutoBirthday.out.write("client--------------" + j);
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					AutoBirthday.out.println();
					AutoBirthday.out.write("" + e);
				}
			} // end of loop
			output.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			AutoBirthday.output.write(e.getMessage());
			AutoBirthday.output.println();
			AutoBirthday.out.println();
			AutoBirthday.out.write("" + e);
			output.close();
			out.close();
		} // end of catch
	} // end of main method
	
	
	public String getFinalBdayMessage(String message) {
		String outer = "<table width='90%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td width='100%' style='font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000;padding-right:5px;text-align: right;'>This Message is sent through <a href='http://www.the3i.com' target='_new'>HRMS</a></td></tr><tr><td> "
			+" <table width='100%' border='0' cellspacing='0' cellpadding='0' style='border: #84b4f9 solid 1px;'><tr><td colspan='2' valign='top'> "
			+" <table width='97%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td height='150' valign='top' style='font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000; padding-top:10px;padding-left:5px;padding-right:5px' ><div align='justify'>"+message+"</div></td></tr></table></td></tr><tr> "
			+" <td  colspan='2' bgcolor='#e4eefd' style='margin-top: 5px; border-top: #7babf0 solid 1px;font-family:Arial, Helvetica, sans-serif; font-size:10px; color:#666666; padding-left:10px ; padding-right:10px'><div align='justify'>All information contained in this communication is confidential, proprietary, privileged and is intended for "
			+" the addressees only. If you have received this E-mail in error please notify mail administrator by E-mail or the sender by replying to this message, and then delete this E-mail and other copies of it from your computer system. Any unauthorized dissemination,publication, transfer or use of the contents of this communication, with or without modifications is punishable under the relevant law. "
			+" </div></td></tr></table></td></tr><tr><td><table width='100%' border='0' cellspacing='0' cellpadding='0'></table></td></tr></table> ";
		return outer;
	}

	public boolean getBirthdayData(Connection conn) {
		try {
			// String settingQry = "SELECT * FROM HRMS_BIRTHDAYMAIL_SETTINGS ";

			System.out
					.println("In getBirthdayData----------------------------");

			String settingQry = "SELECT AUTO_SEND,INDIV_DEPT,ALL_DEPT,INDIV_BRANCH,ALL_BRANCH,INDIV_DESG,ALL_DESG,INDIV_DIV,ALL_DIV,TEMPLATE_ID ,NVL(TEMP_FLAG,'S'),NVL(INDIV_EMP,'N'),NVL(INCLUDE_DEPT,'Y'), NVL(INCLUDE_DESG,'N'), NVL(INCLUDE_EMAIL,'N'), NVL(INCLUDE_PHONE,'N'),NVL(GROUP_ID,'N'),NVL(GROUP_MAILID,'') FROM HRMS_BIRTHDAYMAIL_SETTINGS ";

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
					/*
					 * String empBdayQry = " SELECT
					 * EMP_FNAME,HRMS_EMP_OFFC.EMP_ID,NVL(EMP_DEPT,''),NVL(EMP_CENTER,''), " + "
					 * NVL(EMP_RANK,''),NVL(EMP_DIV,
					 * ''),CENTER_NAME,TO_CHAR(EMP_DOB,'dd-Month'),NVL(EMP_LNAME,' ')
					 * FROM HRMS_EMP_OFFC " + " INNER JOIN HRMS_CENTER
					 * ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)" + "
					 * WHERE TO_CHAR(EMP_DOB,'DD-MM') = TO_CHAR(SYSDATE,'DD-MM')
					 * AND EMP_STATUS='S' "; Object[][] empBdayObj =
					 * SqlModel.getSingleResult(empBdayQry,conn);
					 */
					// if indv.. div
					if (String.valueOf(settingObj[0][7]).equals("Y")) {
						getIndividualDivMail(conn, settingObj);
					}// end of individual division if
					// forr loop
					else {
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
								+ "	WHERE TO_CHAR(EMP_DOB,'DD-MM') = TO_CHAR(SYSDATE,'DD-MM') AND EMP_STATUS='S' ";
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
									/*
									 * replaceWithTable += String
									 * .valueOf(empBdayListObj[j][6]) +
									 * "&nbsp;-\t\t\t" +
									 * String.valueOf(empBdayListObj[j][7]) +
									 * "&nbsp;-\t\t\t" +
									 * String.valueOf(empBdayListObj[j][8]) +
									 * "\t\t\t <br>";
									 */
									replaceWithTable += "<DIV style='float:left;width:320px;text-align:left'>"
											+ String
													.valueOf(empBdayListObj[j][6])
											+ "</DIV><DIV style='float:left;text-align:left'> ";

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
									replaceWithTable += "<br><br>";
									replaceWithTable += "</DIV><br><br><br>";

								}

								replaceWithTable += " </DIV></center> ";

								dept = dept.substring(0, dept.length() - 1);
								branch = branch.substring(0,
										branch.length() - 1);
								rank = rank.substring(0, rank.length() - 1);
								div = div.substring(0, div.length() - 1);
								empId = empId.substring(0, empId.length() - 1);
								// int cnt=0;
								// for (int i = 0; i < empBdayObj.length; i++) {
								AutoBirthday.output
										.write("Birthday of Employee====");
								AutoBirthday.output.println();
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

								AutoBirthday.out.println();
								AutoBirthday.out.write("TOTAL NO OF RECORD :"
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
								AutoBirthday.out.println();
								AutoBirthday.out
										.write("Exception while executing query :"
												+ e);
								e.printStackTrace();
								AutoBirthday.output.write(e.getMessage());
								AutoBirthday.output.println();
								return false;
							} // end of catch
						} // end if 2
					}

				} // end if 1
			} // end if Out
			else {
				AutoBirthday.out.println();
				AutoBirthday.out
						.write("Setting not found for auto birthday mail");
				return false;
			}

		} catch (Exception e) {
			AutoBirthday.out.println();
			AutoBirthday.out.write("Exception in method " + e);
			e.printStackTrace();
			AutoBirthday.output.write(e.getMessage());
			AutoBirthday.output.println();
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
							replaceWithTable += "<br><br>";
							replaceWithTable += "</DIV><br><br><br>";

						}

						replaceWithTable += " </DIV></center> ";

						dept = dept.substring(0, dept.length() - 1);
						branch = branch.substring(0, branch.length() - 1);
						rank = rank.substring(0, rank.length() - 1);
						div = div.substring(0, div.length() - 1);
						empId = empId.substring(0, empId.length() - 1);
						// int cnt=0;
						// for (int i = 0; i < empBdayObj.length; i++) {
						AutoBirthday.output.write("Birthday of Employee====");
						AutoBirthday.output.println();
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

						// "reeba.joseph@glodyne.com",
						String[] toEmpAddArr = new String[toEmpObj.length];
						if (toEmpObj != null && toEmpObj.length > 0) {
							for (int j = 0; j < toEmpObj.length; j++) {
								toEmpAddArr[j] = String.valueOf(toEmpObj[j][1]);
							} // end of loop
						} // end of if
						else {
							return false;
						} // end of else

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

						AutoBirthday.out.println();
						AutoBirthday.out.write("TOTAL NO OF RECORD :"
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
						AutoBirthday.out.println();
						AutoBirthday.out
								.write("Exception while executing query :" + e);
						e.printStackTrace();
						AutoBirthday.output.write(e.getMessage());
						AutoBirthday.output.println();
						return false;
					} // end of catch
				} // end if 2
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	private boolean testConnection(Object[][] mailBoxData,
			Object[][] companyObj, String[][] fromMailIds, Connection conn) {
		try {
			Session session1 = null;
			BodyPart messageBodyPart = new MimeBodyPart();

			/**
			 * Make host and port as persistent
			 */
			for (int i = 0; i < mailBoxData.length; i++) {
				for (int j = 0; j < mailBoxData[0].length; j++) {
					AutoBirthday.out.println();
					AutoBirthday.out.write("..." + mailBoxData[i][j]);
				}

			}
			Properties props = new Properties();
			props.put("mail.smtp.host", String.valueOf(mailBoxData[0][0])
					.trim());
			props.put("mail.smtp.port", String.valueOf(mailBoxData[0][1])
					.trim());

			Object[][] sysMailData = null;

			String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
					+ String.valueOf(mailBoxData[0][10]) + " AND ROWNUM=1";
			sysMailData = SqlModel.getSingleResult(sysmailQuery, conn);

			if (String.valueOf(mailBoxData[0][9]).equals("pop3/ssl")) {
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "*");

			}

			if (String.valueOf(mailBoxData[0][5]).equals("Y")) {// Authentication
				// is true
				props.put("mail.smtp.auth", "true");

				/**
				 * Authenticator -: Authenticates the network connection
				 * PasswordAuthentication -: Repository for a user name and a
				 * password used by Authenticator.
				 */

				String userNameStr = "";

				String passwordStr = "";

				if (String.valueOf(mailBoxData[0][12]).equals("S")) {
					userNameStr = String.valueOf(sysMailData[0][0]);
					passwordStr = String.valueOf(sysMailData[0][1]);
				} else {
					userNameStr = String.valueOf(mailBoxData[0][13]);
					passwordStr = String.valueOf(mailBoxData[0][14]);

				}

				final String userName = userNameStr;
				final String pass = passwordStr;

				Authenticator auth = new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, pass);
					}
				};
				session1 = Session.getInstance(props, auth);// Creates a mail
				// session

			} // end of if statement
			else {
				session1 = Session.getInstance(props);
			}
			/**
			 * Transport -: An abstract class that models a message transport
			 */
			javax.mail.Transport t = null;
			try {
				t = session1.getTransport("smtp");// Get a Transport object
				// that implements the
				// specified protocol
				try {
					t.connect();// Makes a connection to the service
				} catch (MessagingException e) {
					AutoBirthday.out.println();
					AutoBirthday.out.write("" + e);
					AutoBirthday.output.write(e.getMessage());
					AutoBirthday.output.println();
					e.printStackTrace();
					return false;
				} // end of try-catch block
			} catch (NoSuchProviderException e1) {
				AutoBirthday.output.write(e1.getMessage());
				AutoBirthday.out.println();
				AutoBirthday.out.write("" + e1);
				e1.printStackTrace();
				return false;
			} // end of try-catch block

			session1.setDebug(true);
			Date dt = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
			String date = sd.format(dt);
			javax.mail.Message message = new MimeMessage(session1);
			MimeMultipart multipart = new MimeMultipart("related");

			((MimeMessage) message)
					.setSubject("Test Mail of AutoBirthday for client "
							+ companyObj[0][0]);
			InternetAddress fromEmail = new InternetAddress(fromMailIds[0][0]);
			message.setFrom(fromEmail);

			message.addHeader("To", "prem1977@gmail.com");
			messageBodyPart.setContent("AutoBirthday mail sent on " + date
					+ " to clent " + companyObj[0][0], "text/html");

			multipart.addBodyPart(messageBodyPart);
			((Part) message).setContent(multipart);
			InternetAddress[] toEmail = new InternetAddress[1];

			toEmail[0] = new InternetAddress("prem1977@gmail.com");
			t.sendMessage(message, toEmail);

		} catch (Exception e) {
			AutoBirthday.output.write(e.getMessage());
			AutoBirthday.output.println();
			e.printStackTrace();
			return false;
		} // end of catch
		return true;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public String[][] getDefaultMailIds(Connection conn) {
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object fromEmp[][] = SqlModel.getSingleResult(fromQuery, conn);
		String[][] mailIds = new String[fromEmp.length][2];
		for (int i = 0; i < fromEmp.length; i++) {
			mailIds[i][0] = String.valueOf(fromEmp[i][0]);
			mailIds[i][1] = String.valueOf(fromEmp[i][1]);
		}
		return mailIds;
	}

	public void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, Object[][] mailBoxData) {
		fromMailId = fromMailIds;

		// email. setDebug(true);
		try {
			// HtmlEmail email=null;
			int patch = Integer.parseInt(String.valueOf(mailBoxData[0][16]));
			if (patch == 0) {
				patch = 80;
			}

			if (patch > 0) {
				System.out.println("patch" + patch);
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
				 * AutoBirthday.out.println(); AutoBirthday.out .write(" COUNT
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
					AutoBirthday.out.println("\n");
					AutoBirthday.out.println("Batch Number : " + (i + 1));
					AutoBirthday.out.println();
					AutoBirthday.out.println("Email Batch Size : "
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
			AutoBirthday.out.println();
			AutoBirthday.out.write("EXCEPTION 2......");
			e.printStackTrace();
			AutoBirthday.output.write(e.getMessage());
			AutoBirthday.output.println();
		}
	}

	int fireCounter = 0;

	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {

		try {

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
				AutoBirthday.out.println();
				/*
				 * AutoBirthday.out.write("USER NAME " +
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
				 * AutoBirthday.out.println(); AutoBirthday.out.write("send
				 * Error :" + str);
				 */
				List unsentList = email.getUnsentList();

				if (unsentList.size() > 0) {
					fireCounter++;
					String[] toObj = new String[unsentList.size()];
					for (int i = 0; i < unsentList.size(); i++) {
						// AutoBirthday.out.println();
						// AutoBirthday.out.write(" unsentList...Valid
						// "+unsentList.get(i));
						String[] splitedStr = unsentList.get(i).toString()
								.split("<");
						toObj[i] = splitedStr[1].substring(0, splitedStr[1]
								.length() - 1);

					}

					AutoBirthday.out
							.println("\n Unsent Valid Email address size :"
									+ unsentList.size());

					List invalidList = email.getInvalidList();
					AutoBirthday.out.println("\n invalid address :");
					for (int i = 0; i < invalidList.size(); i++) {
						AutoBirthday.out.println("\n" + invalidList.get(i));
					}
					System.out.println("Value of fireCounter is::::::::       "
							+ fireCounter);

					if (fireCounter < 5) {
						AutoBirthday.out
								.println("\n. Resending Emails - counter :"
										+ fireCounter);
						fireEmail(mailBoxData, subject, textBody, toObj);
					}
					// addTo_CC(email, toObj);
					// str= email.send();
				}
				// AutoBirthday.out.println();AutoBirthday.out.write("MAIL SENT
				// SUCCESSFULLY *********** ... FOR SYSMAIL ID "+mailCount);

			} else {
				System.out.println("SYSTEM MAIL IDS OVER....");
			}

			/*
			 * System.out .println("mail send
			 * successfully________________________________ 1
			 * mailCount"+mailCount);
			 */
		} catch (Exception e) {
			/*
			 * email=setHtmlEmail(mailBoxData, subject, textBody, request,
			 * tomailIds); mailCount++;
			 */
			mailCount++;
			System.out.println("EXCEPTION________________________________ ");
			e.printStackTrace();
			AutoBirthday.output.write("" + e);
			AutoBirthday.output.println();
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
		System.out.println("replacedString  :  " + replacedString);
		return replacedString;

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
				/*
				 * if (i % 2 == 0) { email.addTo(toObj[i]); } else {
				 * email.addCc(toObj[i]); }
				 */
				email.addTo(toObj[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return email;
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
			replacedString = getHtmlText(email, textBody);
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

	public void fireEmail(HtmlEmail email) {

	}

	/**
	 * 
	 * @param empId
	 * @return
	 */
	public Object[][] getServerMailBox(String empId, String fromMailId,
			Connection conn) {
		System.out.println("empId  in getServerMailBox   " + empId);
		System.out.println("fromMailId  in getServerMailBox   " + fromMailId);
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
					+ " ,NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER "
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

		return empMailBoxData;
	}

} // end of class

