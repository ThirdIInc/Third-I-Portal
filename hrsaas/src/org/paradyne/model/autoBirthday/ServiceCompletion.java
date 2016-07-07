package org.paradyne.model.autoBirthday;

/**
 * Created by Vishwambhar Deshpande
 */
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

public class ServiceCompletion {
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
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
			String date = sd.format(dt);
			String inFileName = globalProp.getString("servicecompletion_logs")
					+ "\\logs" + date + ".txt";
			File file1 = new File(globalProp.getString("servicecompletion_logs") + "\\");
			SimpleDateFormat sdLog = new SimpleDateFormat("yyyyMMdd");
			String dateLog = sdLog.format(dt);
			String inFileNameLog = globalProp.getString("servicecompletion_logs")
					+ "\\logs" + dateLog + ".txt";

			File file1Log = new File(globalProp.getString("servicecompletion_logs")
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

			ServiceCompletion.out.println();
			ServiceCompletion.out.write("START...");
			ServiceCompletion.output.println();
			ResourceBundle bundle = ResourceBundle
					.getBundle("org.paradyne.lib.ConnectionModel");// Get
			// resource
			// bundle
System.out.println("bundle ========="+bundle);
			Enumeration list = bundle.getKeys();
			int count = 0;

			Vector vect = new Vector();
			while (list.hasMoreElements()) {
				// ServiceCompletion.out.println();ServiceCompletion.out.write("Counter-----"+count+"-------"+list.nextElement());
				String client = (String) list.nextElement();
				if (!client.contains(".")) {
					vect.add(client);
				}

		}
			ServiceCompletion.out.println();
			ServiceCompletion.out.write("vect.size()--------------" + vect.size());
			for (int j = 0; j < vect.size(); j++) {
				try {
					String client = (String) vect.get(j);
					System.out.println("client  :  " + client);

					ServiceCompletion.out.println();
					ServiceCompletion.out.write("client--------------" + client);
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
						ServiceCompletion.out.println();
						ServiceCompletion.out.write(e.getMessage());
					} // end of catch
					Connection conn = null;
					try {
						if (driver != null) {
							ServiceCompletion.out.println();
							ServiceCompletion.out.write("url==========" + url);
							ServiceCompletion.out.println();
							ServiceCompletion.out.write("username=========="
									+ username);
							ServiceCompletion.out.println();
							ServiceCompletion.out.write("password=========="
									+ password);
							Class.forName(driver);
							conn = java.sql.DriverManager.getConnection(url,
									username, password);
						} // end of if
						if (!conn.isClosed()) {
							ServiceCompletion.output.println();
							try {
								new ServiceCompletion()
										.getServiceCompletionData(conn);
							} catch (Exception e) {
								System.out.println("Exception in getServiceCompletionData----------");
							}
						}
					} catch (Exception e) {
						conn.close();
						e.printStackTrace();
						ServiceCompletion.out.println();
						ServiceCompletion.out.write("" + e);
						ServiceCompletion.out.println();
					}// end of catch
					finally {
						conn.close();
						ServiceCompletion.output
								.write("****************End of ServiceCompletion mails**************************");
					}
					ServiceCompletion.out.println();
					ServiceCompletion.out.write("client--------------" + j);
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					ServiceCompletion.out.println();
					ServiceCompletion.out.write("" + e);
				}
			} // end of loop
			output.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			ServiceCompletion.output.write(e.getMessage());
			ServiceCompletion.output.println();
			ServiceCompletion.out.println();
			ServiceCompletion.out.write("" + e);
			output.close();
			out.close();
		} // end of catch
	} // end of main method

	public boolean getServiceCompletionData(Connection conn) {
System.out.println("getServiceCompletionData call ################################");
		try {
			System.out.println("In getServiceCompletionData--------------");
			String settingQuery = " SELECT AUTO_SEND,INDIV_DEPT,ALL_DEPT,INDIV_BRANCH,ALL_BRANCH,INDIV_DESG,ALL_DESG,INDIV_DIV,ALL_DIV,TEMPLATE_ID ,NVL(TEMP_FLAG,'S'),NVL(INDIV_EMP,'N'),REPORTINGTO_FLAG FROM HRMS_AUTO_MAIL_ALERT "
					+ " WHERE AUTO_CODE=1 ";

			Object[][] settingObj = SqlModel
					.getSingleResult(settingQuery, conn);

		
			
			
			if (settingObj != null && settingObj.length > 0) {
				if (String.valueOf(settingObj[0][0]).equals("Y")) {
					System.out.println("in if loop-----------");
					String companyQry = " SELECT COMPANY_NAME ,TO_CHAR(SYSDATE,'DDth MON') FROM HRMS_COMPANY ";
					Object[][] companyObj = SqlModel.getSingleResult(
							companyQry, conn);
					Object templateObj[][] = null;
					String[][] fromMailIds = getDefaultMailIds(conn);
					String empOneyrServiceCompleteList = " SELECT  EMP_FNAME||' '||EMP_LNAME,HRMS_EMP_OFFC.EMP_ID,NVL(EMP_DEPT,''),NVL(EMP_CENTER,''),  NVL(EMP_RANK,''),NVL(EMP_DIV, ''),NVL(INITCAP(EMP_FNAME),' ')||' '||NVL(INITCAP(EMP_LNAME),' '),NVL(INITCAP(DEPT_NAME),''),NVL(INITCAP(CENTER_NAME),' ')  "
							+ "	,FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)as year ,NVL(EMP_REPORTING_OFFICER,0)  FROM HRMS_EMP_OFFC   "
							+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
							+ "	INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
							+ " WHERE EMP_STATUS='S' "
							+ " AND FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)>=1 ";

					Object[][] empOneyrServiceCompleteListObj = SqlModel
							.getSingleResult(empOneyrServiceCompleteList, conn);

					String templateData = " SELECT TEMPLATE_BODY,TEMPLATE_ID FROM HRMS_AUTOMAIL_TEMPLATE WHERE TEMPLATE_USE_FLAG='N' AND TEMPLATE_CATAGORY='S' ORDER BY TEMPLATE_ID ";

					System.out
							.println("empOneyrServiceCompleteListObj.length    "
									+ empOneyrServiceCompleteListObj.length);

					if (empOneyrServiceCompleteListObj != null
							&& empOneyrServiceCompleteListObj.length > 0) {
						try {
							templateObj = SqlModel.getSingleResult(
									templateData, conn);
							System.out.println("templateObj.length  "
									+ templateObj.length);
							if (templateObj != null && templateObj.length == 0) {
								String update = "UPDATE HRMS_AUTOMAIL_TEMPLATE SET TEMPLATE_USE_FLAG='N' ";
								SqlModel.singleExecute(update, conn);
								templateObj = SqlModel.getSingleResult(
										templateData, conn);
							}

							if (empOneyrServiceCompleteListObj != null
									&& empOneyrServiceCompleteListObj.length > 0) {
								for (int m = 0; m < empOneyrServiceCompleteListObj.length; m++) {
									
									String reportingEmpId ="0";
									
									if(String.valueOf(settingObj[0][12]).equals("Y"))
									{
										System.out.println("empOneyrServiceCompleteListObj[m][10] == == =="+ empOneyrServiceCompleteListObj[m][10]);
     									reportingEmpId=String.valueOf(empOneyrServiceCompleteListObj[m][10]);
									
									}
									
									
									String serviceCompleteempId ="0";
									serviceCompleteempId=String.valueOf(empOneyrServiceCompleteListObj[m][1]);
									System.out.println("empOneyrServiceCompleteListObj[m][1] == == =="+ empOneyrServiceCompleteListObj[m][1]);
									
									
									
									String toEmpQry = " SELECT HRMS_EMP_OFFC.EMP_ID,ADD_EMAIL FROM HRMS_EMP_ADDRESS "
											+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID) "
											+ " WHERE   HRMS_EMP_OFFC .EMP_STATUS='S' AND ADD_TYPE='P' "
											+ " AND ADD_EMAIL IS NOT NULL AND EMP_DEPT IN (1) OR HRMS_EMP_OFFC.EMP_ID IN("+reportingEmpId+","+serviceCompleteempId+") ";
									
									toEmpQry += "  ORDER BY EMP_ID ";
									Object[][] twoDimSendToEmp = SqlModel
											.getSingleResult(toEmpQry, conn);

									System.out.println("twoDimSendToEmp.length is found to be ========"+twoDimSendToEmp.length);
									
									
									
									String toSendEmp[] = new String[twoDimSendToEmp.length];
									for (int i = 0; i < toSendEmp.length; i++) {
										toSendEmp[i] = String
												.valueOf(twoDimSendToEmp[i][0]);
										System.out
												.println("Employee mail ids---"
														+ toSendEmp[i]);

										String oneEmp[] = new String[1];
										// for (int myCount = 0; myCount <
										// twoDimListBirEmp.length;
										// myCount++) {}
										oneEmp[0] = toSendEmp[i];

										String subject = " Congratulation mail- completion of years of services in "+String.valueOf(companyObj[0][0])+" ";
										System.out.println("subject==========="+subject);
										
										String htmlText = String
												.valueOf(templateObj[0][0]);

										htmlText = htmlText
												.replace(
														"&lt;#EMPLOYEE_NAME#&gt;",
														String
																.valueOf(empOneyrServiceCompleteListObj[m][0]));
										htmlText = htmlText
												.replace(
														"&lt;#COMPANY_NAME#&gt;",
														String
																.valueOf(companyObj[0][0]));

										htmlText = htmlText
												.replace(
														"&lt;#NOOFSER_YEAR#&gt;",
														String
																.valueOf(empOneyrServiceCompleteListObj[m][9]));

										if (twoDimSendToEmp.length > 0) {

											Object[][] mailBoxData = getServerMailBox(
													"", fromMailIds[0][0], conn);

											if (mailBoxData != null
													&& mailBoxData.length > 0
													&& fromMailIds != null
													&& fromMailIds.length > 0) {
												ServiceCompletion.output
														.write("****************Start ServiceCompletion Mails for one year service completion "
																+ companyObj[0][0]
																+ "*****************");
												ServiceCompletion.output.println();
												   boolean	test =false;
												try {
		                                    	test = testConnection(
															mailBoxData,
															companyObj,
															fromMailIds, conn);

												} catch (Exception e) {
													e.printStackTrace();
													ServiceCompletion.output
															.println();
													ServiceCompletion.output
															.write("=============" + e);
												}
                                                  
												if (!test)
													return false;
											} // end of if
											else {
												return false;
											}
											ServiceCompletion.out.println();
											ServiceCompletion.out
													.write("TOTAL NO OF RECORD============= :"
															+ oneEmp.length);
											if (String.valueOf(
													settingObj[0][10]).equals(
													"S")) {
												sendMail(oneEmp, fromMailIds,
														subject, htmlText,
														mailBoxData);
											} else {
												String[] multiMessageText = new String[templateObj.length];
												for (int j = 0; j < multiMessageText.length; j++) {
													String multimessageText = String
															.valueOf(templateObj[j][0]);
													multimessageText = subject;
													multiMessageText[j] = multimessageText;
												}

												String textBodyMss = "";
												textBodyMss = String
														.valueOf(multiMessageText[0]);
												String update1 = "UPDATE HRMS_AUTOMAIL_TEMPLATE SET TEMPLATE_USE_FLAG='Y' WHERE TEMPLATE_ID="
														+ templateObj[0][1];
												SqlModel.singleExecute(update1,
														conn);

												sendMail(oneEmp, fromMailIds,
														subject, htmlText,
														mailBoxData);
											}

										}
									}
								}

							}

						} catch (Exception e) {
							ServiceCompletion.out.println();
							ServiceCompletion.out
									.write("Exception while executing query================ :"
											+ e);
							e.printStackTrace();
							ServiceCompletion.output.write("=========="+e.getMessage());
							ServiceCompletion.output.println("==============");
							return false;
						}
					}

				}
			} else {
				ServiceCompletion.out.println();
				ServiceCompletion.out
						.write("Setting not found for one year service completion");
				return false;
			}

		} catch (Exception e) {
			ServiceCompletion.out.println();
			ServiceCompletion.out.write("Exception in method " + e);
			e.printStackTrace();
			ServiceCompletion.output.write(e.getMessage());
			ServiceCompletion.output.println();
		}

		return true;
	}

	 

	private boolean testConnection(Object[][] mailBoxData,
			Object[][] companyObj, String[][] fromMailIds, Connection conn) {
		
		System.out.println("in testConnection method>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
		
		try {
			Session session1 = null;
			BodyPart messageBodyPart = new MimeBodyPart();

			/**
			 * Make host and port as persistent
			 */
			System.out.println("mailBoxData.length is ========"+mailBoxData.length);
			
			for (int i = 0; i < mailBoxData.length; i++) {
				for (int j = 0; j < mailBoxData[0].length; j++) {
					ServiceCompletion.out.println();
					ServiceCompletion.out.write("..." + mailBoxData[i][j]);
				}

			}
			Properties props = new Properties();
			props.put("mail.smtp.host", String.valueOf(mailBoxData[0][0])
					.trim());
		
			System.out.println("mailBoxData[0][0] ==="+mailBoxData[0][0]);
			props.put("mail.smtp.port", String.valueOf(mailBoxData[0][1])
					.trim());
			System.out.println("mailBoxData[0][0] ==="+mailBoxData[0][1]);
			Object[][] sysMailData = null;

			String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
					+ String.valueOf(mailBoxData[0][10]) + " AND ROWNUM=1";
			sysMailData = SqlModel.getSingleResult(sysmailQuery, conn);

			System.out.println("mailBoxData[0][9]"+mailBoxData[0][9]);
			
			if (String.valueOf(mailBoxData[0][9]).equals("pop3/ssl")) {
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "*");

			}

			System.out.println("mailBoxData[0][5] ==="+mailBoxData[0][5]);
			
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

				System.out.println("mailBoxData[0][12] =====" +mailBoxData[0][12]);
				
				if (String.valueOf(mailBoxData[0][12]).equals("S")) {
					userNameStr = String.valueOf(sysMailData[0][0]);
					passwordStr = String.valueOf(sysMailData[0][1]);
				} else {
					System.out.println("in else part");
					System.out.println("mailBoxData[0][13] " +mailBoxData[0][13] );
					System.out.println(",mailBoxData[0][14] "+ mailBoxData[0][14]);
					userNameStr = String.valueOf(mailBoxData[0][13]);
					passwordStr = String.valueOf(mailBoxData[0][14]);

				}

				final String userName = userNameStr;
				final String pass = passwordStr;

				Authenticator auth = new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						System.out.println("in PasswordAuthentication call)))))))");
						
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
				System.out.println("session1.getTransport****************** ");
				// that implements the
				// specified protocol
				try {
					
					t.connect();// Makes a connection to the service
				System.out.println("t.connect() ************************");
				
				} catch (MessagingException e) {
					
					
					ServiceCompletion.out.println();
					ServiceCompletion.out.write("" + e);
					ServiceCompletion.output.write(e.getMessage());
					ServiceCompletion.output.println();
					e.printStackTrace();
					return false;
				} // end of try-catch block
			} catch (NoSuchProviderException e1) {
				
				System.out.println("error bcz of  getTransport(smtp)===========>");
				
				ServiceCompletion.output.write(e1.getMessage());
				ServiceCompletion.out.println();
				ServiceCompletion.out.write("" + e1);
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
					.setSubject("Test Mail of ServiceCompletion for client "
							+ companyObj[0][0]);
			InternetAddress fromEmail = new InternetAddress(fromMailIds[0][0]);
			message.setFrom(fromEmail);

			message.addHeader("To", "prem1977@gmail.com");
			messageBodyPart.setContent("ServiceCompletion mail sent on " + date
					+ " to clent " + companyObj[0][0], "text/html");

			multipart.addBodyPart(messageBodyPart);
			((Part) message).setContent(multipart);
			InternetAddress[] toEmail = new InternetAddress[1];

			toEmail[0] = new InternetAddress("prem1977@gmail.com");
			t.sendMessage(message, toEmail);

		} catch (Exception e) {
			ServiceCompletion.output.write(e.getMessage());
			ServiceCompletion.output.println();
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
			int patch = 80;
			int count = toMailId.length / patch;
			int rem = toMailId.length % patch;
			if (rem > 0) {
				count = count + 1;
			}
			int k = 0;
			if (patch > toMailId.length) {
				patch = toMailId.length;
			}
			ServiceCompletion.out.println();
			ServiceCompletion.out
					.write(" COUNT  *** NO OF TIMES MAIN FOR LOPP RUNNUNG    :"
							+ count);
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
				ServiceCompletion.out.println();
				ServiceCompletion.out
						.write("tomailIds.length                             "
								+ tomailIds.length);
				for (int j = 0; j < tomailIds.length; j++) {
					tomailIds[j] = toMailId[k];
					k++;
				}
				// HtmlEmail email=setHtmlEmail(mailBoxData, subject, textBody,
				// request, tomailIds);
				fireEmail(mailBoxData, subject, textBody, tomailIds);

			}
		} catch (Exception e) {
			ServiceCompletion.out.println();
			ServiceCompletion.out.write("EXCEPTION 2......");
			e.printStackTrace();
			ServiceCompletion.output.write(e.getMessage());
			ServiceCompletion.output.println();
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
				
				ServiceCompletion.output.println();
				ServiceCompletion.output.println("From mail id is-----"+fromMailId[mailCount][0]);
				ServiceCompletion.output.println();
				
				email.addTo(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);
				ServiceCompletion.out.println();
				ServiceCompletion.out.write("USER NAME    "
						+ fromMailId[mailCount][0] + "     PASS    "
						+ fromMailId[mailCount][1]);
				if (String.valueOf(mailBoxData[0][12]).equals("D")) {
					email.setAuthentication(String
							.valueOf(mailBoxData[mailCount][3]), String
							.valueOf(mailBoxData[0][4]));

				} else {
					email.setAuthentication(String
							.valueOf(fromMailId[mailCount][0]), String
							.valueOf(fromMailId[mailCount][1]));

				}
				if(String.valueOf(mailBoxData[0][6]).equals("Y"))
				{
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
				ServiceCompletion.out.println();
				ServiceCompletion.out.write("send Error  :" + str);
				List unsentList = email.getUnsentList();
				ServiceCompletion.output.println("unsentList.size()--------"+unsentList.size());
				
				 
				  
				  
				
				if (unsentList.size() > 0) {
					fireCounter++;
					String[] toObj = new String[unsentList.size()];
					for (int i = 0; i < unsentList.size(); i++) {
						 ServiceCompletion.output.println();
						  ServiceCompletion.output.write(" unsentList...Valid		 "+unsentList.get(i));
						String[] splitedStr = unsentList.get(i).toString()
								.split("<");
						toObj[i] = splitedStr[1].substring(0, splitedStr[1]
								.length() - 1);
						 
					}
					/*
					 * List invalidList=email.getInvalidList(); for (int i = 0;
					 * i < invalidList.size(); i++) {
					 * ServiceCompletion.out.println(); ServiceCompletion.out.write("
					 * invalid address"+invalidList.get(i)); }
					 */
					if (fireCounter < 5) {
						fireEmail(mailBoxData, subject, textBody, toObj);
					} else {
						fireCounter = 0;
					}
					// addTo_CC(email, toObj);
					// str= email.send();
				}
				// ServiceCompletion.out.println();ServiceCompletion.out.write("MAIL SENT
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

			System.out.println("EXCEPTION________________________________ ");
			e.printStackTrace();
			ServiceCompletion.output.write("" + e);
			ServiceCompletion.output.println();
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
			// ServiceCompletion.out.println();ServiceCompletion.out.write("new_tokens :"
			// + new_tokens);
			// ServiceCompletion.out.println();ServiceCompletion.out.write("\n-----------------------------------------------");
			if (counter != 0) {
				int endofURL = new_tokens.indexOf("\"");
				// ServiceCompletion.out.println();ServiceCompletion.out.write("...endofURL.."
				// + endofURL);
				String src = new_tokens.substring(0, endofURL);
				// ServiceCompletion.out.println();ServiceCompletion.out.write("src====="+src);
				// if (!src.startsWith("http")) {
				// src =
				// "D:\\Struts2\\hrsaas-21\\12\\2009\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\"+src;
				src = imagePath + "\\webapps" + src;
				// }
				// ServiceCompletion.out.println();ServiceCompletion.out.write("src====="+src);
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

		// ServiceCompletion.out.println();ServiceCompletion.out.write("messages :" +
		// replacedString);
		System.out.println("replacedString  :  " + replacedString);
		return replacedString;

	}

	public HtmlEmail addTo_CC(HtmlEmail email, String[] toObj) {

		try {
			// ServiceCompletion.out.println();
			// ServiceCompletion.out.write("************************************************************************************");
			for (int i = 0; i < toObj.length; i++) {
				// ServiceCompletion.out.println();
				// ServiceCompletion.out.write(" "+toObj[i]+" ");
				// ServiceCompletion.out.println();
				email.addBcc(toObj[i]);
			}
			// ServiceCompletion.out.println();
			// ServiceCompletion.out.write("************************************************************************************");
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
			
			System.out.println("here host and port displaying>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
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
			if(tomailIds!=null && tomailIds.length >0)
			{
				
				for (int i = 0; i < tomailIds.length; i++) {
					ServiceCompletion.output.println("to mailIds ["+i+"]-------------"+String.valueOf(tomailIds[i]));
				}
				ServiceCompletion.output.println();	
			}
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
	public Object[][] getServerMailBox(String empId,String fromMailId,Connection conn) {
		System.out.println("empId  in getServerMailBox   "+empId);
		System.out.println("fromMailId  in getServerMailBox   "+fromMailId);
		String getMailBox ="";
		Object[][]empMailBoxData=null;
		Object[][]sysMailData=null;
		
		
		
		if(empId!=null && !empId.equals("")){//this is for pop before smtp check
			
			System.out.println("In first SERVER_OUT_IP  start query +++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,EMAIL_USER_NAME,EMAIL_USER_PASS, SERVER_AUTH_REQUIRED, " 
				+" SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,EMAIL_USER_NAME  "
				+" ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD ,SERVER_USESYSTEMMAILID_FLAG " //newly added for logon using same or different Id
				+" FROM HRMS_EMAIL_SERVER  "
				+" INNER JOIN HRMS_EMAIL_ACCOUNT ON (HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE=HRMS_EMAIL_SERVER.SERVER_CODE) "
				+" WHERE EMAIL_EMP_ID="+empId+"  AND EMAIL_OFFICIAL_FLAG='Y'";
			empMailBoxData= SqlModel.getSingleResult(getMailBox, conn);
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = SqlModel.getSingleResult(sysmailQuery, conn); 
				if(sysMailData!=null && sysMailData.length>0)
				{
					if(String.valueOf(empMailBoxData[0][15]).equals("Y"))
					{
						fromMailId =String.valueOf(sysMailData[0][0]);
					}
				}
				if(String.valueOf(empMailBoxData[0][12]).equals("S"))
				{
					if(empId==null || empId.length()==0){
						System.out.println("emp Id NULL :");					
						if(sysMailData!=null && sysMailData.length>0){
							System.out.println("sysMailData not NULL :");
							empMailBoxData[0][3]=sysMailData[0][0];
							empMailBoxData[0][4]=sysMailData[0][1];
							empMailBoxData[0][11]=sysMailData[0][0];
						}					                 
					}else{
						System.out.println("fromMailId   emp Id present:"+fromMailId);
						empMailBoxData[0][3]=sysMailData[0][0];
						empMailBoxData[0][4]=sysMailData[0][1];
						empMailBoxData[0][11]=fromMailId;
					}
				}
				else
				{
					empMailBoxData[0][3]=empMailBoxData[0][13];
					empMailBoxData[0][4]=empMailBoxData[0][14];
					empMailBoxData[0][11]=fromMailId;
				}
				
				
			}
			System.out.println(" in POP BEFORE SMTP");
		}
		if(empMailBoxData==null || empMailBoxData.length==0){
			
			System.out.println("In second SERVER_OUT_IP  start query +++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,' ',' ',SERVER_AUTH_REQUIRED, " 
				+" SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,'' " 
				+" ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD,SERVER_USESYSTEMMAILID_FLAG " //newly added for logon using same or different Id
				+" FROM HRMS_EMAIL_SERVER "
				+" WHERE SERVER_SYSTEMMAIL_FLAG='Y' ";
			empMailBoxData=SqlModel.getSingleResult(getMailBox, conn); 
			System.out.println(" ELSE POP");
			if(empMailBoxData !=null &&  empMailBoxData.length>0){
				String sysmailQuery="SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "+String.valueOf(empMailBoxData[0][10]) +" AND ROWNUM=1";
				sysMailData=SqlModel.getSingleResult(sysmailQuery, conn);
					if(sysMailData!=null && sysMailData.length>0)
				{
					if(String.valueOf(empMailBoxData[0][15]).equals("Y"))
					{
						fromMailId =String.valueOf(sysMailData[0][0]);
					}
				}
				if(String.valueOf(empMailBoxData[0][12]).equals("S"))
				{
					if(empId==null || empId.length()==0){
						System.out.println("emp Id NULL :");					
						if(sysMailData!=null && sysMailData.length>0){
							System.out.println("sysMailData not NULL :");
							empMailBoxData[0][3]=sysMailData[0][0];
							empMailBoxData[0][4]=sysMailData[0][1];
							empMailBoxData[0][11]=sysMailData[0][0];
						}					                 
					}else{
						System.out.println("fromMailId   emp Id present:"+fromMailId);
						empMailBoxData[0][3]=sysMailData[0][0];
						empMailBoxData[0][4]=sysMailData[0][1];
						empMailBoxData[0][11]=fromMailId;
					}
				}
				else
				{
					empMailBoxData[0][3]=empMailBoxData[0][13];
					empMailBoxData[0][4]=empMailBoxData[0][14];
					empMailBoxData[0][11]=fromMailId;
				}
				
			
			}
		}
		if(sysMailData!=null && sysMailData.length>0){
		for (int i = 0; i < sysMailData[0].length; i++) {
			System.out.println("  EMAIL SERVER DATA  : "+String.valueOf(sysMailData[0][i]));
		}
		}
		if(empMailBoxData!=null && empMailBoxData.length>0){
			for (int i = 0; i < empMailBoxData.length; i++) {
				for (int j = 0; j < empMailBoxData[0].length; j++) {
					System.out.println("empMailBoxData   ["+i+"]["+j+"]"+empMailBoxData[i][j]);
				}
				
			}
			
		}
		
		return empMailBoxData;
	}

} // end of class

