/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.srd.SendMail;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.email.HtmlEmail;
import org.paradyne.model.autoBirthday.AutoBirthday;

/**
 * @author shashikant
 * 
 */
public class SendMailModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SendMailModel.class);
	static String imagePath = "";
	HtmlEmail email = null;
	String[][] fromMailId = null;
	int mailCount = 0;

	static int cn = 0;

	/**
	 * to get the birthday list of employee
	 * 
	 * @param sendMail
	 * @param request
	 */
	public void getList(SendMail sendMail, HttpServletRequest request) {
		String query = "Select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID from hrms_emp_offc"
				+ " where to_char(EMP_DOB,'dd-mm') = to_char(sysdate,'dd-mm') and EMP_STATUS='S'";
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList<Object> obj = new ArrayList<Object>();
		String xyz[] = request.getParameterValues("hdeleteCode");

		for (int i = 0; i < data.length; i++) {
			SendMail bean = new SendMail();
			bean.setEmpName(checkNull(String.valueOf(data[i][0])));
			bean.setEmpCode(checkNull(String.valueOf(data[i][1])));
			if (xyz != null) {
				if (!xyz[i].equals("")) {
					bean.setConfChk("true");
				}
				bean.setHdeleteCode(xyz[i]);
			}

			obj.add(bean);

		}
		sendMail.setList(obj);
		String setQuery = " SELECT TEMPLATE_ID, TEMPLATE_NAME FROM HRMS_BIRTHDAYTEMPLATE_HDR ";
		Object[][] setData = getSqlModel().getSingleResult(setQuery);
		if (setData != null && setData.length > 0) {
			sendMail.setTempName(String.valueOf(setData[0][1]));
			sendMail.setTempCode(String.valueOf(setData[0][0]));
		}

	}

	public String getValue(String[] obj) {
		String str = "";
		try {
			for (int i = 0; i < obj.length; i++) {
				if (!obj[i].equals("")) {
					str += String.valueOf(obj[i]) + ",";
				}

			}// end of loop

			str = str.substring(0, str.length() - 1);
		} catch (RuntimeException e) {
			logger.error("Exception in getValue method : " + e);
		}

		return str;

	}

	/**
	 * to send b'day mail
	 * 
	 * @param sendMail
	 * @param xyz
	 * @return String
	 */
	public String sendBirthDayMail(SendMail sendMail, String[] xyz,
			HttpServletRequest request) {
		Object[][] twoDimListBirEmp = null;
		Object[][] twoDimSendToEmp = null;
		String flag = "";
		String empIds = getValue(xyz);

		MailUtility mail = new MailUtility();
		mail.initiate(context, session);
		String result[][] = getDefaultMailIds();
		MailModel model = new MailModel();
		model.initiate(context, session);
		// String companyName = model.getCompanyName();
		model.terminate();
		if (result == null || !(result.length > 0)) {
			flag = "false";
			return flag;
		}
		try {
			String dob = " SELECT  EMP_FNAME,HRMS_EMP_OFFC.EMP_ID,NVL(EMP_DEPT,''),NVL(EMP_CENTER,''),  NVL(EMP_RANK,''),NVL(EMP_DIV, ''),NVL(INITCAP(EMP_FNAME),' ')||' '||NVL(INITCAP(EMP_LNAME),' '),NVL(INITCAP(DEPT_NAME),''),NVL(INITCAP(CENTER_NAME),' ')  "
					+ "	FROM HRMS_EMP_OFFC   "
					+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ "	INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ "	 where EMP_ID IN(" + empIds + ") ";
			logger.info("Query----" + dob);
			twoDimListBirEmp = getSqlModel().getSingleResult(dob);

			String replaceWithTable = " <center><DIV style='align:center;width: 750px;'> ";
			String dept = "";
			String branch = "";
			String rank = "";
			String div = "";
			String empId = "";
			for (int j = 0; j < twoDimListBirEmp.length; j++) {
				/*
				 * replaceWithTable += String .valueOf(twoDimListBirEmp[j][6]) +
				 * "&nbsp;-\t\t\t" + String.valueOf(twoDimListBirEmp[j][7]) +
				 * "&nbsp;-\t\t\t" + String.valueOf(twoDimListBirEmp[j][8]) +
				 * "\t\t\t <br>";
				 */
				replaceWithTable += "<DIV style='float:left;width:320px;text-align:left'>"
						+ String.valueOf(twoDimListBirEmp[j][6])
						+ "</DIV><DIV style='float:left;text-align:left'> "
						+ String.valueOf(twoDimListBirEmp[j][7])
						+ "<br>  "
						+ String.valueOf(twoDimListBirEmp[j][8])
						+ "</DIV><br><br><br>";

			}

			replaceWithTable += " </DIV></center> ";

			String templateData = " SELECT TEMPLATE_BODY FROM HRMS_BIRTHDAYTEMPLATE_HDR WHERE TEMPLATE_ID = "
					+ sendMail.getTempCode();
			Object[][] templateObj = getSqlModel()
					.getSingleResult(templateData);

			if (!(twoDimListBirEmp.length > 0)) {
				flag = "absentDob";
				return flag;
			}
			String[] bdayEmp = empIds.split(",");

			for (int m = 0; m < bdayEmp.length; m++) {

				String listOfEmp = "select ADD_EMAIL from HRMS_EMP_ADDRESS "
						+ "	left join hrms_emp_offc on(hrms_emp_offc.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID)"
						+ "	where   hrms_emp_offc .EMP_STATUS='S' and ADD_TYPE='P' and  ADD_EMAIL is not null  ";

				if (!(sendMail.getEmpid().equals("") || sendMail.getEmpid()
						.equals(null))) {
					listOfEmp += " AND HRMS_EMP_OFFC.EMP_ID ="
							+ sendMail.getEmpid();
				}

				if (!sendMail.getDepartmentName().equals("-1")) {
					if (sendMail.getDepartmentName().equals("o")) {
						listOfEmp += " AND HRMS_EMP_OFFC.EMP_DEPT=(Select EMP_DEPT from HRMS_EMP_OFFC WHERE EMP_ID="
								+ bdayEmp[m] + ")";
					} else if (sendMail.getDepartmentName().equals("M")) {
						System.out.println("sendMail.getDepartmentCode()----"
								+ sendMail.getDepartmentCode());
						listOfEmp += " AND HRMS_EMP_OFFC.EMP_DEPT in("
								+ sendMail.getDepartmentCode() + ") ";
					}
				}
				if (!sendMail.getDivName().equals("-1")) {
					if (sendMail.getDivName().equals("o")) {
						listOfEmp += " AND HRMS_EMP_OFFC.EMP_DIV=(Select EMP_DIV from HRMS_EMP_OFFC WHERE EMP_ID="
								+ bdayEmp[m] + ")";
					} else if (sendMail.getDivName().equals("M")) {

						listOfEmp += " AND HRMS_EMP_OFFC.EMP_DIV in("
								+ sendMail.getDivCode() + ")";
					}
				}
				if (!sendMail.getBranch().equals("-1")) {
					if (sendMail.getBranch().equals("o")) {
						listOfEmp += " AND HRMS_EMP_OFFC.EMP_CENTER=(Select EMP_CENTER from HRMS_EMP_OFFC WHERE EMP_ID="
								+ bdayEmp[m] + ")";
					} else if (sendMail.getBranch().equals("M")) {

						listOfEmp += " AND HRMS_EMP_OFFC.EMP_CENTER in("
								+ sendMail.getBranchCode() + ") ";
					}
				}
				listOfEmp += " OR HRMS_EMP_OFFC.EMP_ID IN(" + empIds + ")";

				logger.info("the result of query ---------------" + listOfEmp);

				twoDimSendToEmp = getSqlModel().getSingleResult(listOfEmp);
				System.out.println("twoDimSendToEmp.length-----------"
						+ twoDimSendToEmp.length);

				if (twoDimSendToEmp != null && !twoDimSendToEmp.equals("null")
						&& !twoDimSendToEmp.equals("null")
						&& twoDimSendToEmp.length > 0) {

					try {
						String toSendEmp[] = new String[twoDimSendToEmp.length];
						for (int i = 0; i < toSendEmp.length; i++) {
							toSendEmp[i] = String
									.valueOf(twoDimSendToEmp[i][0]);
							logger.info("Employee mail ids---" + toSendEmp[i]);

							String oneEmp[] = new String[1];
							// for (int myCount = 0; myCount <
							// twoDimListBirEmp.length; myCount++) {}
							oneEmp[0] = toSendEmp[i];

							String subject[] = new String[1];
							// for (int myCount = 0; myCount <
							// twoDimListBirEmp.length; myCount++) {}
							subject[0] = "Birthday Greetings";

							String companyQry = " SELECT COMPANY_NAME,TO_CHAR(SYSDATE,'DDth MON') FROM HRMS_COMPANY ";
							Object[][] companyObj = getSqlModel()
									.getSingleResult(companyQry);

							try {
								// String subject = "Birthday Greetings ";

								String htmlText[] = new String[1];

								htmlText[0] = String.valueOf(templateObj[0][0]);

								htmlText[0] = String
										.valueOf(htmlText[0])
										.replace(
												"&lt;#COMPANY_NAME#&gt;",
												String
														.valueOf(companyObj[0][0]));

								htmlText[0] = String
										.valueOf(htmlText[0])
										.replace(
												"&lt;#BIRTH_DATE#&gt;",
												String
														.valueOf(companyObj[0][1]));

								htmlText[0] = String.valueOf(htmlText[0])
										.replace("&lt;#EMP_LIST#&gt;",
												replaceWithTable);
								/*
								 * String getMailBox = " SELECT MAILBOX_SERVER,
								 * MAILBOX_PROTOCOL, MAILBOX_PORT,
								 * MAILBOX_USERID,
								 * MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_FLAG
								 * FROM " + " HRMS_SETTINGS_MAILBOX WHERE
								 * MAILBOX_FLAG='O' ";
								 * 
								 * Object[][] mailBoxData =
								 * getSqlModel().getSingleResult(getMailBox);
								 */

								/*
								 * Object[][] mailBoxData =
								 * model.getServerMailBox(fromEmpId,
								 * from_mailIds[0]);
								 */

								if (twoDimSendToEmp.length > 0) {

									/*
									 * mail.sendMail(oneEmp, mail
									 * .getDefaultMailIds(), subject, htmlText,
									 * "");
									 */
									String[][] frmMails = getDefaultMailIds();

									/*
									 * sendMail(oneEmp, frmMails,"Birthday
									 * Greetings", htmlText,
									 * mailBoxData,request);
									 */
									/*
									 * mail.sendMail(oneEmp,
									 * mail.getDefaultMailIds(), "Birthday
									 * Greetings", htmlText, "","");
									 */

									sendMail(oneEmp, frmMails, subject,
											htmlText, "", "");

									flag = "true";
								}
								mail.terminate();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					} catch (Exception e) {
						logger.error("error......." + e);
					}
				} else {
					flag = "unavailable";
					return flag;
				}
			}

		} catch (Exception e) {

			logger.error("error......." + e);
		} finally {

		}

		return flag;

	}

	public String getBirthDayMessage(String empName, String location,
			String birthDate) {

		String companyName = "";
		MailModel model = new MailModel();
		model.initiate(context, session);
		companyName = model.getCompanyName();
		model.terminate();

		String htmlText = "<html>"
				+ "  <body>"
				+ " <table width='585' border='1' align='center'> "
				+ " <tr> "
				+ " <td><table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' bordercolor='#F671B3' >"
				+ "   <tr>"
				+ "     <td width='24%' height='70' align='center' style='font-family: monotype Corsiva; font-size: 32px; color: #FFFFFF; text-decoration:none; background-color:#F1439A;'>***** </td> "
				+ "     <td width='55%' align='center' style='font-family: monotype Corsiva; font-size: 32px; color: #FFFFFF; text-decoration:none; background-color:#F1439A;'>Happy Birthday !!! </td>"
				+ "      <td width='21%' align='center' style='font-family: monotype Corsiva; font-size: 32px; color: #FFFFFF; text-decoration:none; background-color:#F1439A;'>***** </td>"
				+ "     </tr>"
				+ "     <tr>"
				+ "       <td colspan='3' valign='top'><table width='100%' border='0' cellspacing='0' cellpadding='0'>"
				+ "          <tr> "
				+ "            <td width='31%' valign='top' bgcolor='#fdcefd'><p>&nbsp;</p>"
				+ "                <p>&nbsp;</p>"
				+ "              <table width='100%' border='0' cellspacing='0' cellpadding='0'>"
				+ "                  <tr>"
				+ "                   <td  style='font-family: monotype Corsiva; font-family: monotype Corsiva; color: #660000; font-size:22px; font-weight:normal; text-decoration:none; padding: 0px 0px 0px 15px;'><p>Name<strong> :<br />"
				+ "                       </strong><span style='color: #000000; font-size: 20px;'>	"
				+ empName
				+ ".</span>&nbsp;</span><br />"
				+ "                          <br />"
				+ "                       Location : <br />"
				+ "                       <span style='color: #000000; font-size: 20px;'>"
				+ location
				+ "</span></p>"
				+ "                         <p>Date of Birth  : <br />"
				+ "                             <span style='color: #000000; font-size: 20px;'>"
				+ birthDate
				+ " </span></p></td>"
				+ "                    </tr>"
				+ "               </table></td>"
				+ "              <td width='69%' style='font-family: monotype Corsiva; color: #FF0033; font-size:22px; padding-left:20px; border-right: #f671b3 solid 1px;'><p><br />"
				+ "                Dear<span style='color: #FF0033'> "
				+ empName
				+ ",</span></p>"
				+ "                 <p>&nbsp;</p>"
				+ "               <p style='color: #000000;'>Members of "
				+ companyName
				+ " <br />"
				+ "                wish you a very<span style='color: #FF00FF'> &quot; Happy Birthday&quot;</span></p>"
				+ "                <p>&nbsp;</p> "
				+ "                <p style='color: #000000;'>May this day bring you all the happiness <br />"
				+ "                 and good fortune.</p>"
				+ "                <p style='color: #006633'>&nbsp;</p></td>"
				+ "             </tr>"
				+ "         </table></td>"
				+ "       </tr>"
				+ "      <tr>"
				+ "        <td height='70' colspan='3' align='center' bgcolor='#F1439A'><span style='font-family: monotype Corsiva; font-size: 24px; color: #FFFFFF; text-decoration: none;'>Human Capital Group</span></td>"
				+ "      </tr>"
				+ "     </table></td>"
				+ "   </tr>"
				+ " 	</table>" + " 	</body> " + " 	</html> ";
		return htmlText;
	}

	public Object[] getSendToEmp(SendMail sendMail) {
		Object[][] twoDimSendToEmp = null;
		String listOfEmp = "select ADD_EMAIL from HRMS_EMP_ADDRESS "
				+ "	inner join hrms_emp_offc on(hrms_emp_offc.emp_id=HRMS_EMP_ADDRESS.emp_id)"
				+ "	where ADD_EMAIL is not NULL and hrms_emp_offc .EMP_STATUS='S' and ADD_TYPE='P'";
		if (!(String.valueOf(sendMail.getBranchCode()).equals("null") || String
				.valueOf(sendMail.getBranchCode()).equals(""))) {
			listOfEmp += " and	EMP_CENTER=" + sendMail.getBranchCode() + " ";
		}
		if (!(String.valueOf(sendMail.getDivisionCode()).equals("null") || String
				.valueOf(sendMail.getDivisionCode()).equals(""))) {
			listOfEmp += " and	EMP_DIV=" + sendMail.getDivisionCode() + " ";
		}
		if (!(String.valueOf(sendMail.getDeptCode()).equals("null") || String
				.valueOf(sendMail.getDeptCode()).equals(""))) {
			listOfEmp += " and	EMP_DEPT=" + sendMail.getDeptCode() + " ";
		}

		if (!(String.valueOf(sendMail.getDesgCode()).equals("null") || String
				.valueOf(sendMail.getDesgCode()).equals(""))) {
			listOfEmp += "	and EMP_RANK=" + sendMail.getDesgCode() + " ";
		}

		twoDimSendToEmp = getSqlModel().getSingleResult(listOfEmp);

		return twoDimSendToEmp;
	}

	public Object[] getSendByEmp(SendMail sendMail) {
		Object[][] twoDimSendToEmp = null;
		String listOfEmp = "select ADD_EMAIL from HRMS_EMP_ADDRESS "
				+ "	inner join hrms_emp_offc on(hrms_emp_offc.emp_id=HRMS_EMP_ADDRESS.emp_id)"
				+ "	where ADD_EMAIL is not NULL and hrms_emp_offc .EMP_ID="
				+ sendMail.getEmployeeCode() + " and ADD_TYPE='P' ";
		try {
			logger.info("    listOfEmp    " + listOfEmp);
			twoDimSendToEmp = getSqlModel().getSingleResult(listOfEmp);
			logger.info("    twoDimSendToEmp     " + twoDimSendToEmp[0][0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return twoDimSendToEmp;
	}
	
	
	

	public String getMassMessage(String message,Connection dbconn) {
		String textMss = "";
		String companyName = "";
		MailModel model = new MailModel();
		model.initiate(context, session);
		companyName = model.getCompanyName(dbconn);
		model.terminate();

		textMss = "<html>"
				+ " <body >"
				+ " <table width='90%' border='0' align='center' cellpadding='0' cellspacing='0'>"
				+ "   <tr><td width='100%' style='font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000;padding-right:5px;text-align: right;'> "
				+ "   This Message is sent through <a href='http://www.the3i.com' target='_new'>HRMS</a></td></tr><tr>"
				+ "    <td><table width='100%' border='0' cellspacing='0' cellpadding='0'>"
				+ "    <tr>"
				+ "    <td>"
				+ " 	<table width='100%' border='0' cellspacing='0' cellpadding='0' style='border: #84b4f9 solid 1px;'>"
				+ "      <tr>"
				+ "        <td width='60%' height='60' bgcolor='#003366' style='font-family:Arial, Helvetica, sans-serif; font-size:20px; color:#FFFFFF; padding-left:20px'>HRMS e-Communication</td>"
				+ "         <td width='40%' valign='bottom' bgcolor='#003366' style='font-family:Arial, Helvetica, sans-serif; font-size:18px; color:#FFFFFF; padding-left:20px ;padding-bottom:5px'><div align='right'>"
				+ companyName
				+ "&nbsp;</span></div></td>"
				+ "       </tr>"
				+ "       <tr>"
				+ "           <td colspan='2' valign='top'><table width='97%' border='0' align='center' cellpadding='0' cellspacing='0'>"
				+ "             <tr>"
				+ "               <td height='150' valign='top' style='font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000; padding-top:10px;padding-left:5px;padding-right:5px' ><div align='justify'>  <br /> <br /> "
				+ message
				+ "  </span></div></td> "
				+ "              </tr> "
				+ "            </table></td> "
				+ "          </tr> "
				+ "          <tr> <td  colspan='2' bgcolor='#e4eefd' style='margin-top: 5px; border-top: #7babf0 solid 1px;font-family:Arial, Helvetica, sans-serif; font-size:10px; color:#666666; padding-left:10px ; padding-right:10px'><div align='justify'>All information contained in this communication is confidential, proprietary, privileged "
				+ "          and is intended for the addressees only. If you have received this E-mail in error please notify mail administrator by E-mail or the sender by replying to this message, and then delete this E-mail and other copies of it from your computer system. Any unauthorized dissemination,publication, transfer or use of the contents of this communication, with or without modifications is punishable under the relevant law.<br />"			          
				+ "          </div></td></tr> "
				+ "        </table></td>"
				+ "      </tr>"
				+ "     </table></td>"
				+ "   </tr>"
				+ " </table></body></html>";

		return textMss;
	}

	public String getMassMessage(String message) {
		String textMss = "";
		String companyName = "";
		MailModel model = new MailModel();
		model.initiate(context, session);
		companyName = model.getCompanyName();
		model.terminate();

		textMss = "<html>"

				+ " <body >"
				+ " <table width='90%' border='0' align='center' cellpadding='0' cellspacing='0'>"
				+ "   <tr>"
				+ "    <td><table width='100%' border='0' cellspacing='0' cellpadding='0'>"

				+ "    <tr>"
				+ "    <td>"
				+ " 	<table width='100%' border='0' cellspacing='0' cellpadding='0' style='border: #84b4f9 solid 1px;'>"
				+ "      <tr>"
				+ "        <td width='60%' height='60' bgcolor='#003366' style='font-family:Arial, Helvetica, sans-serif; font-size:20px; color:#FFFFFF; padding-left:20px'>HRMS e-Communication</td>"
				+ "         <td width='40%' valign='bottom' bgcolor='#003366' style='font-family:Arial, Helvetica, sans-serif; font-size:18px; color:#FFFFFF; padding-left:20px ;padding-bottom:5px'><div align='right'>"
				+ companyName
				+ "&nbsp;</span></div></td>"
				+ "       </tr>"
				+ "       <tr>"
				+ "           <td colspan='2' valign='top'><table width='97%' border='0' align='center' cellpadding='0' cellspacing='0' style='margin-bottom: 5px; border-bottom: #7babf0 solid 1px;'>"
				+ "             <tr>"
				+ "               <td height='150' valign='top' style='font-family:Arial, Helvetica, sans-serif; font-size:11px; color:#000000; padding-top:10px;padding-left:5px;padding-right:5px' ><div align='justify'>  <br /> <br /> "
				+ message
				+ "  </span></div></td>"
				+ "              </tr>"
				+ "            </table></td>"
				+ "          </tr>"
				+ "          <tr>"
				+ "           <td  colspan='2' bgcolor='#e4eefd' style='font-family:Arial, Helvetica, sans-serif; font-size:10px; color:#666666; padding-left:10px ; padding-right:10px'><div align='justify'>All information contained in this communication is confidential, proprietary, privileged"
				+ "             and is intended for the addressees only. If you have received this E-mail in error please notify mail administrator by E-mail or the sender by replying to this message, and then delete this E-mail and other copies of it from your computer system. Any unauthorized dissemination,publication, transfer or use of the contents of this communication, with or without modifications is punishable under the relevant law.<br />"
				+ "             <br />"
				// + " Glodyne has scanned this mail with current virus checking
				// technologies. However, Glodyne makes no representations or
				// warranties to the effect that this communication is
				// virus-free.<br />"
				// + " <br />"
				// + " Glodyne reserves the right to monitor all E-mail
				// communications through its Corporate Network.</div></td>"
				+ "          </tr>"
				+ "        </table></td>"
				+ "      </tr>"
				+ "      <tr>"
				+ "        <td><p>&nbsp;</p>          </td>"
				+ "       </tr>"
				+ "     </table></td>"
				+ "   </tr>"
				+ " 	</table>" + " 	</body>	</html> ";

		return textMss;
	}

	public void saveMail(SendMail sendMail) {
		Object saveObj[][] = new Object[1][8];
		saveObj[0][0] = sendMail.getSubject();
		saveObj[0][1] = sendMail.getMessage();
		saveObj[0][2] = sendMail.getDeptCode();
		saveObj[0][3] = sendMail.getDivisionCode();
		saveObj[0][4] = sendMail.getBranchCode();
		saveObj[0][5] = sendMail.getDesgCode();
		saveObj[0][6] = sendMail.getAttachmentFile();
		saveObj[0][7] = sendMail.getEmployeeCode();
		String saveQuery = " INSERT INTO HRMS_SEND_MAIL (MAIL_CODE,SUBJECT,MESSAGE,DEPT_ID,DIV_ID,CENTER_ID,DESG_ID,ATTACHMENT,EMP_ID,DATE_TIME) "
				+ " VALUES ((SELECT NVL(MAX(MAIL_CODE),0)+1 FROM HRMS_SEND_MAIL ),?,?,?,?,?,?,?,?,SYSDATE) ";
		getSqlModel().singleExecute(saveQuery, saveObj);
	}

	public void getSentItems(SendMail sendMail) {
		String query = " SELECT  MESSAGE, HRMS_DEPT.DEPT_NAME, HRMS_DIVISION.DIV_NAME, HRMS_CENTER.CENTER_NAME, "
				+ " HRMS_RANK.RANK_NAME, ATTACHMENT, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, EMP_ID  "
				+ " FROM HRMS_SEND_MAIL "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_SEND_MAIL.DEPT_ID) "
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_SEND_MAIL.DIV_ID) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_SEND_MAIL.CENTER_ID) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_SEND_MAIL.DESG_ID) "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SEND_MAIL.EMP_ID) "
				+ " WHERE MAIL_CODE = " + sendMail.getMailCode();
		Object data[][] = getSqlModel().getSingleResult(query);
		for (int i = 0; i < data.length; i++) {
			sendMail.setMessage(checkNull(String.valueOf(data[0][0])));
			sendMail.setDeptName(checkNull(String.valueOf(data[0][1])));
			sendMail.setDivisionName(checkNull(String.valueOf(data[0][2])));
			sendMail.setBranchName(checkNull(String.valueOf(data[0][3])));
			sendMail.setDesgName(checkNull(String.valueOf(data[0][4])));
			sendMail.setAttachmentFile(checkNull(String.valueOf(data[0][5])));
			sendMail.setEmployeeName(checkNull(String.valueOf(data[0][6])));
			sendMail.setEmployeeCode(String.valueOf(data[0][7]));
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	// for getting Anniversary list
	public void getAnniversaryList(SendMail sendMail, HttpServletRequest request) {
		String query = "Select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_EMP_OFFC.EMP_ID from  HRMS_EMP_OFFC "
				+ " left join  HRMS_EMP_PERS ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " where to_char(HRMS_EMP_PERS.EMP_MARRG_DATE,'dd-mm') = to_char(sysdate,'dd-mm') and HRMS_EMP_PERS.EMP_MARITAL_STATUS='M'";
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList<Object> obj = new ArrayList<Object>();
		String xyz[] = request.getParameterValues("hdeleteCode");

		for (int i = 0; i < data.length; i++) {
			SendMail bean = new SendMail();
			bean.setEmpName(checkNull(String.valueOf(data[i][0])));
			bean.setEmpCode(checkNull(String.valueOf(data[i][1])));
			if (xyz != null) {
				if (!xyz[i].equals("")) {
					bean.setConfChk("true");
				}
				bean.setHdeleteCode(xyz[i]);
			}

			obj.add(bean);

		}
		sendMail.setArrlist(obj);
		String setQuery = " SELECT TEMPLATE_ID, TEMPLATE_NAME FROM HRMS_ANNIVERSARY_TEMPLATE ";
		Object[][] setData = getSqlModel().getSingleResult(setQuery);
		if (setData != null && setData.length > 0) {
			sendMail.setTempName(String.valueOf(setData[0][1]));
			sendMail.setTempCode(String.valueOf(setData[0][0]));
		}

	}

	// for Anniversary

	public String sendAnniversaryDayMail(SendMail sendMail, String[] code) {
		Object[][] twoDimListBirEmp = null;
		Object[][] twoDimSendToEmp = null;
		String flag = "";
		String empIds = getValue(code);
		MailUtility mail = new MailUtility();
		mail.initiate(context, session);
		String result[] = mail.getDefaultMailIds();
		MailModel model = new MailModel();
		model.initiate(context, session);
		String companyName = model.getCompanyName();
		model.terminate();
		if (result == null || !(result.length > 0)) {
			flag = "false";
			return flag;
		}
		try {
			/*
			 * String dob = " select
			 * round((to_date(sysdate,'DDth-MM-YYYY')-to_date(HRMS_EMP_PERS.EMP_MARRG_DATE,'DDth-MM-YYYY'))/365)
			 * as year,EMP_FNAME||' '||EMP_LNAME,' ',RANK_NAME ,CENTER_NAME,'
			 * ',EMP_CENTER,to_char(HRMS_EMP_PERS.EMP_MARRG_DATE,'ddth Month')
			 * FROM HRMS_EMP_PERS " +"LEFT JOIN HRMS_EMP_OFFC
			 * ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" + " left join
			 * HRMS_RANK on(HRMS_RANK.RANK_ID=hrms_emp_offc.EMP_RANK)" + " left
			 * join HRMS_CENTER
			 * on(HRMS_CENTER.CENTER_ID=hrms_emp_offc.EMP_CENTER)" + " where
			 * HRMS_EMP_OFFC.EMP_ID IN(" + empIds + ") " + " and
			 * round((to_date(sysdate,'DD-MM-YYYY')-to_date(HRMS_EMP_PERS.EMP_MARRG_DATE,'DD-MM-YYYY'))/365)!=0 ";
			 */
			String dob = "select round((to_date(TO_CHAR(sysdate,'DD-MM-YYYY'),'DDth-MM-YYYY')-to_date(TO_CHAR(HRMS_EMP_PERS.EMP_MARRG_DATE,'DD-MM-YYYY'),'DDth-MM-YYYY'))/365) as year, "
					+ " EMP_FNAME||' '||EMP_LNAME,' ',RANK_NAME ,CENTER_NAME,' ',EMP_CENTER,to_char(HRMS_EMP_PERS.EMP_MARRG_DATE,'ddth Month') "
					+ " FROM HRMS_EMP_PERS "
					+ " LEFT JOIN  HRMS_EMP_OFFC ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " left join HRMS_RANK on(HRMS_RANK.RANK_ID=hrms_emp_offc.EMP_RANK) "
					+ " left join HRMS_CENTER on(HRMS_CENTER.CENTER_ID=hrms_emp_offc.EMP_CENTER) "
					+ " where  HRMS_EMP_OFFC.EMP_ID IN("
					+ empIds
					+ ") "
					+ " and round((to_date(TO_CHAR(sysdate,'DD-MM-YYYY'),'DDth-MM-YYYY')-to_date(TO_CHAR(HRMS_EMP_PERS.EMP_MARRG_DATE,'DD-MM-YYYY'),'DDth-MM-YYYY')))!=0 ";

			logger.info("Query----" + dob);
			twoDimListBirEmp = getSqlModel().getSingleResult(dob);

			String templateData = " SELECT TEMPLATE_BODY FROM HRMS_ANNIVERSARY_TEMPLATE WHERE TEMPLATE_ID = "
					+ sendMail.getTempCode();
			Object[][] templateObj = getSqlModel()
					.getSingleResult(templateData);

			if (!(twoDimListBirEmp.length > 0)) {
				flag = "absentmarriagedate";
				return flag;
			}
			String[] bdayEmp = empIds.split(",");

			for (int m = 0; m < bdayEmp.length; m++) {

				String listOfEmp = "select ADD_EMAIL from HRMS_EMP_ADDRESS "
						+ "	left join hrms_emp_offc on(hrms_emp_offc.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID)"
						+ "	where   hrms_emp_offc .EMP_STATUS='S' and ADD_TYPE='P' ";

				if (!(sendMail.getEmpid().equals("") || sendMail.getEmpid()
						.equals(null))) {
					listOfEmp += " AND HRMS_EMP_OFFC.EMP_ID ="
							+ sendMail.getEmpid();
				}

				if (!sendMail.getDepartmentName().equals("-1")) {
					if (sendMail.getDepartmentName().equals("o")) {
						listOfEmp += " AND HRMS_EMP_OFFC.EMP_DEPT=(Select EMP_DEPT from HRMS_EMP_OFFC WHERE EMP_ID="
								+ bdayEmp[m] + ")";
					} else if (sendMail.getDepartmentName().equals("M")) {
						System.out.println("sendMail.getDepartmentCode()----"
								+ sendMail.getDepartmentCode());
						listOfEmp += " AND HRMS_EMP_OFFC.EMP_DEPT in("
								+ sendMail.getDepartmentCode() + ") ";
					}
				}
				if (!sendMail.getDivName().equals("-1")) {
					if (sendMail.getDivName().equals("o")) {
						listOfEmp += " AND HRMS_EMP_OFFC.EMP_DIV=(Select EMP_DIV from HRMS_EMP_OFFC WHERE EMP_ID="
								+ bdayEmp[m] + ")";
					} else if (sendMail.getDivName().equals("M")) {

						listOfEmp += " AND HRMS_EMP_OFFC.EMP_DIV in("
								+ sendMail.getDivCode() + ")";
					}
				}
				if (!sendMail.getBranch().equals("-1")) {
					if (sendMail.getBranch().equals("o")) {
						listOfEmp += " AND HRMS_EMP_OFFC.EMP_CENTER=(Select EMP_CENTER from HRMS_EMP_OFFC WHERE EMP_ID="
								+ bdayEmp[m] + ")";
					} else if (sendMail.getBranch().equals("M")) {

						listOfEmp += " AND HRMS_EMP_OFFC.EMP_CENTER in("
								+ sendMail.getBranchCode() + ") ";
					}
				}
				listOfEmp += " OR HRMS_EMP_OFFC.EMP_ID IN(" + empIds + ")";
				logger.info("the result of query ---------------" + listOfEmp);

				twoDimSendToEmp = getSqlModel().getSingleResult(listOfEmp);

				try {
					String toSendEmp[] = new String[twoDimSendToEmp.length];
					for (int i = 0; i < toSendEmp.length; i++) {
						toSendEmp[i] = String.valueOf(twoDimSendToEmp[i][0]);
						logger.info("Employee mail ids---" + toSendEmp[i]);

						String oneEmp[] = new String[1];
						// for (int myCount = 0; myCount <
						// twoDimListBirEmp.length; myCount++) {}
						oneEmp[0] = toSendEmp[i];

						try {
							String ordForm = getOrdinalFor(Integer
									.parseInt(String
											.valueOf(twoDimListBirEmp[m][0])));
							
							String subject[]=new String[1]; 
							 subject[0] = "Happy "
									+ String.valueOf(twoDimListBirEmp[m][0])
									+ ordForm + " anniversary to  "
									+ twoDimListBirEmp[m][1] + "-"
									+ twoDimListBirEmp[m][4];
							 
							 String htmlText[]=new String[1];
							 
							  htmlText[0] = String.valueOf(templateObj[0][0]);

							  htmlText[0] =  htmlText[0].replace(
									"&lt;#EMPLOYEE_NAME#&gt;", String
											.valueOf(twoDimListBirEmp[m][1]));
							  htmlText[0] =  htmlText[0].replace(
									"&lt;#COMPANY_NAME#&gt;", companyName);
							  htmlText[0] =  htmlText[0].replace(
									"&lt;#LOCATION_NAME#&gt;", String
											.valueOf(twoDimListBirEmp[m][4]));
							  htmlText[0] =  htmlText[0].replace(
									"&lt;#ANNIVERSARY_DATE#&gt;", String
											.valueOf(twoDimListBirEmp[m][7]));
							  htmlText[0] =  htmlText[0].replace(
									"&lt;#ANNIVERSARY_YEAR#&gt;", String
											.valueOf(twoDimListBirEmp[m][0])
											+ ordForm);
							if (twoDimSendToEmp.length > 0) {
								
								String[][] frmMails = getDefaultMailIds();

								/*
								 * sendMail(oneEmp, frmMails,"Birthday
								 * Greetings", htmlText,
								 * mailBoxData,request);
								 */
								/*
								 * mail.sendMail(oneEmp,
								 * mail.getDefaultMailIds(), "Birthday
								 * Greetings", htmlText, "","");
								 */
								
							/*	public void sendMail(String[] to_mailIds1, String[][] from_mailIds1,
										String subject1[], String messg1[], String attachPath1,
										String fromEmpId)*/
								
								sendMail(oneEmp, frmMails, subject,
										htmlText, "", "");

								flag = "true";
							}
							mail.terminate();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					logger.error("error......." + e);
				}
			}
		} catch (Exception e) {

			logger.error("error......." + e);
		} finally {

		}
		return flag;

	}

	public String getHtmlText(String textBody, HttpServletRequest request) {
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
				ResourceBundle globalProp = ResourceBundle
						.getBundle("globalMessages");// Get resource bundle
				Date dt = new Date();
				// imagePath=globalProp.getString("catalina_home");
				src = imagePath + "\\webapps" + src;
				System.out.println("src============================   " + src);
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

	public String[][] getDefaultMailIds() {
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object fromEmp[][] = getSqlModel().getSingleResult(fromQuery);
		String[][] mailIds = new String[fromEmp.length][2];
		for (int i = 0; i < fromEmp.length; i++) {
			mailIds[i][0] = String.valueOf(fromEmp[i][0]);
			mailIds[i][1] = String.valueOf(fromEmp[i][1]);
		}
		return mailIds;
	}

	private void fireBdayEmailWithImage(Object[][] mailBoxData,
			String[] subject, String[] messg, String[][] from_mailIds,
			String[] to_mailIds) {
		try {
			HtmlEmail email = new HtmlEmail();
			int mailCount = 0;
			if (mailCount < from_mailIds.length) {
				/*
				 * if(mailCount==0){ email=setHtmlEmail(mailBoxData, subject,
				 * textBody, request, tomailIds); }
				 */

				email = setHtmlEmail(mailBoxData, subject[0], messg[0],
						to_mailIds);
				email.setFrom(from_mailIds[0][0], ""
						+ from_mailIds[0][0]);
				email.addTo(to_mailIds[0], "" + to_mailIds[0]);
				logger.info("Authentication..."
						+ String.valueOf(from_mailIds[0][0]) + "----"
						+ String.valueOf(from_mailIds[0][1]));

				if (String.valueOf(mailBoxData[0][12]).equals("D")) {
					email.setAuthentication(String
							.valueOf(mailBoxData[0][3]), String
							.valueOf(mailBoxData[0][4]));

				} else {
					email.setAuthentication(String
							.valueOf(from_mailIds[0][0]), String
							.valueOf(from_mailIds[0][1]));

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
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][2]));
				}
				if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
					email.setSmtpWithSSL(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][2]));
				}

				String str = email.send();

				List unsentList = email.getUnsentList();

				if (unsentList.size() > 0) {
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
					/*
					 * List invalidList=email.getInvalidList(); for (int i = 0;
					 * i < invalidList.size(); i++) {
					 * AutoBirthday.out.println(); AutoBirthday.out.write("
					 * invalid address"+invalidList.get(i));
					 *  }
					 */

					fireBdayEmailWithImage(mailBoxData, subject, messg,
							from_mailIds, to_mailIds);
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

			System.out.println("EXCEPTION________________________________ ");
			e.printStackTrace();
			// fireEmail(mailBoxData, subject, textBody, request, tomailIds);
		}

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
			new AutoBirthday().addTo_CC(email, tomailIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}

	public String getHtmlText(HtmlEmail email, String textBody) {
		textBody = textBody.replace("src=\"", "$");
		StringTokenizer st = new StringTokenizer(textBody, "$");
		int counter = 0;
		String replacedString = "";
		String cid = "";
		ResourceBundle globalProp = ResourceBundle.getBundle("globalMessages");// Get
																				// resource
																				// bundle
		imagePath = globalProp.getString("catalina_home");

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
				// System.out.println("src============================11 "+src);
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

	public void sendMail(String[] to_mailIds1, String[][] from_mailIds1,
			String subject1[], String messg1[], String attachPath1,
			String fromEmpId) {
		final String[] to_mailIds = to_mailIds1;
		final String[][] from_mailIds = from_mailIds1;
		final String[] subject = subject1;
		final String[] messg = messg1;
		final String attachPath = attachPath1;
		final String fromEmpId1 = fromEmpId;
		/**
		 * Creates a separate thread
		 */
		new Thread(new Runnable() {
			public void run() {
				try {
					/**
					 * Get the server name, protocol, port, user name, password
					 * from database
					 */
					MailModel model = new MailModel();
					model.initiate(context, session);

					// Object[][] mailBoxData = model.getMailBoxPara();

					Object[][] mailBoxData = model.getServerMailBox(fromEmpId1,
							from_mailIds[0][0]);

					model.terminate();
					fireBdayEmailWithImage(mailBoxData, subject, messg,
							from_mailIds, to_mailIds);

				} catch (Exception e) {
					logger.info(e);
					e.printStackTrace();
				} // end of try-catch block
			}

		}).start();
	}

	public HtmlEmail setHtmlEmail(Object[][] mailBoxData, String[] subject,
			String[] textBody) {
		HtmlEmail email = new HtmlEmail();
		String replacedString = "";

		try {

			email.setHostName(String.valueOf(mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(mailBoxData[0][1])));// 80
			email.setSubject("" + subject[0]);
			replacedString = getHtmlText(email, textBody);
			// replacedString=getHtmlTextWithBackground(email, replacedString,
			// request);
			email.setHtmlMsg(replacedString);
			email
					.setTextMsg("Your email client does not support HTML messages");
			// addTo_CC(email, tomailIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}

	public String getHtmlText(HtmlEmail email, String[] textBody) {
		textBody[0] = textBody[0].replace("src=\"", "$");
		StringTokenizer st = new StringTokenizer(textBody[0], "$");
		int counter = 0;
		String replacedString = "";
		String cid = "";
		ResourceBundle globalProp = ResourceBundle.getBundle("globalMessages");// Get
																				// resource
																				// bundle
		imagePath = globalProp.getString("catalina_home");

		System.out.println("imagePath======================" + imagePath);
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
				System.out
						.println("src============================11   " + src);
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
}
