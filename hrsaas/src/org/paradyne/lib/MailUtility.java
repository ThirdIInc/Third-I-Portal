package org.paradyne.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.mail.util.ByteArrayDataSource;

import org.paradyne.lib.ModelBase;
import org.paradyne.model.admin.srd.SendMailModel;
import org.paradyne.model.autoBirthday.AutoBirthday;

/**
 * @author Shashikant DokeMODIFIED BY VISHWAMBHAR DESHPANDE
 * @date 30 May 2008
 **/

/**
 * This class is used to send mails
 */

public class MailUtility extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MailUtility.class);
	static int cn = 0;

	String exceptionMessage = "";

	static PrintWriter output = null;

	/**
	 * @return String[] as mail IDs
	 */
	public String[] getDefaultMailIds() {
		MailModel model = new MailModel();
		model.initiate(context, session);
		String[] fromMailIds = model.getDefaultMailIds();// Get mail IDs
		// which is set as
		// default in the
		// software
		model.terminate();
		return fromMailIds;
	}

	/**
	 * Create HTML text to send an email
	 */
	/**
	 * @param to_mailIds -:
	 *            Specifies emp IDS to whom mail will be sent
	 * @param from_mailIds -:
	 *            Specifies emp IDs from whom mail will be sent
	 * @param appType -:
	 *            Specifies the name of module
	 * @param appCode -:
	 *            Specifies unique application code
	 * @param status -:
	 *            Specifies status like P:Pending, A:Approved, R:Rejected
	 */
	public void sendMailWithMsg(Object[][] to_mailIds, Object[][] from_mailIds,
			String appType, String mailMsg, String status) {
		MailModel model = new MailModel();
		model.initiate(context, session);

		String[] fromMail = model.getMailIds(from_mailIds);// Get mail IDs for
		// corresponding emp
		// IDs
		String[] toMails = model.getMailIds(to_mailIds);// Get mail IDs for
		// corresponding emp IDs
		String empName = model.getEmpName(from_mailIds);// Get emp name for the
		// first emp ID
		String empNameTo = model.getEmpName(to_mailIds);// Get emp name for the
		// first emp ID

		String[] subject = new String[1];
		String[] messg = new String[1];

		/**
		 * Sets the messages as per the module and as per the status
		 */

		if (appType.equals("AssetAssignment")) {
			if (status.equals("RJ")) {

				subject[0] = "Your Asset Application has been rejected by "
						+ empName + ". ";

			}
			if (status.equals("SB")) {

				subject[0] = "Asset Application has been sent back by "
						+ empName + ". ";

			}
		}

		if (status.equals("P")) {
			subject[0] += "is pending for approval.";
		}

		/**
		 * Create mail body containing message in HTML format
		 */
		String msg = "<html> "
				+ "<style>"
				+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
				+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
				+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
				+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
				+ ".style15 {color: #CC0000} "
				+ ".style16 {color: #D23A49} "
				+ "</style>"
				+ "	<body> "
				+ "<table width='100%' border='0' cellpadding='0' cellspacing='0'>"
				+ "<tr> "
				+ "<td width='100%'>"
				+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
				+ "<tr> " + "<td><p>Dear&nbsp;<b>" + empNameTo
				+ "</b>, </p><br /> " + subject[0] + "<p>" + mailMsg + "</td> "
				+ "</tr> " + "</table> " + "</td> " + "</tr> " + "</table> "
				+ "</body> " + "</html> ";
		messg[0] = msg;

		SendMailModel sendMail = new SendMailModel();
		sendMail.initiate(context, session);
		messg[0] = sendMail.getMassMessage(messg[0]);// Get HTML text
		logger.info("messg in the senmailmodel======" + messg[0]);
		sendMail(toMails, fromMail, subject, messg, "", "");
		sendMail.terminate();
		model.terminate();
	}

	/**
	 * Create HTML text to send an email
	 */
	/**
	 * @param to_mailIds -:
	 *            Specifies emp IDS to whom mail will be sent
	 * @param from_mailIds -:
	 *            Specifies emp IDs from whom mail will be sent
	 * @param appType -:
	 *            Specifies the name of module
	 * @param appCode -:
	 *            Specifies unique application code
	 * @param status -:
	 *            Specifies status like P:Pending, A:Approved, R:Rejected
	 */
	public void sendMail(Object[][] to_mailIds, Object[][] from_mailIds,
			String appType, String appCode, String status) {
		MailModel model = new MailModel();
		model.initiate(context, session);

		String[] fromMail = model.getMailIds(from_mailIds);// Get mail IDs for
		// corresponding emp
		// IDs
		String[] toMails = model.getMailIds(to_mailIds);// Get mail IDs for
		// corresponding emp IDs
		String empName = model.getEmpName(from_mailIds);// Get emp name for the
		// first emp ID
		String empNameTo = model.getEmpName(to_mailIds);// Get emp name for the
		// first emp ID

		String[] subject = new String[1];
		String[] messg = new String[1];
		String[] messageBody = new String[1];
		/**
		 * Sets the messages as per the module and as per the status
		 */
		if (appType.equals("Help")) {
			subject[0] = "Help Desk Application of " + empName + " ";
			if (status.equals("D")) {
				subject[0] = "Help Desk Application has been deleted by "
						+ empName + " ";
			}
		} // end of if statement

		if (appType.equals("Conf")) {
			subject[0] = "Conference Booking Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Conference Booking Application has been approved by "
						+ empName + " ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Conference Booking Application has been rejected by "
						+ empName + " ";
			}
			if (status.equals("D")) {
				subject[0] = "Conference Booking Application has been deleted by "
						+ empName + " ";
			}
		} // end of if statement

		if (appType.equals("Loan")) {
			subject[0] = "Loan Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Loan Application has been approved by "
						+ empName + " ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Loan Application has been rejected by "
						+ empName + " ";
			}
			if (status.equals("D")) {
				subject[0] = "Loan Application has been deleted by " + empName
						+ " ";
			}
		} // end of if statement

		if (appType.equals("Voucher")) {
			subject[0] = "Voucher Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Voucher Application has been approved by "
						+ empName + " ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Voucher Application has been rejected by "
						+ empName + " ";
			}
			if (status.equals("D")) {
				subject[0] = "Voucher Application has been deleted by "
						+ empName + " ";
			}
		} // end of if statement

		if (appType.equals("Travel")) {
			subject[0] = "Travel Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Travel Application has been approved by "
						+ empName + " ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Travel Application has been rejected by "
						+ empName + " ";
			}
			if (status.equals("D")) {
				subject[0] = "Travel Application has been deleted by "
						+ empName + " ";
			}
		} // end of if statement

		if (appType.equals("Travel")) {
			if (status.equals("S")) {
				Object[][] empData = new Object[1][1];
				empData[0][0] = appCode;
				subject[0] = "Travel Application of "
						+ model.getEmpName(empData)
						+ " is pending for schedule ";
			}
			if (status.equals("C")) {
				subject[0] = "Travel Confirmed by " + empName + " ";
			}
			if (status.equals("N")) {
				subject[0] = "Travel Canceled by " + empName + " ";
			}
			if (status.equals("SC")) {
				subject[0] = "Travel schedule is created by " + empName + " ";
			}
		} // end of if statement

		if (appType.equals("TravelVch")) {
			subject[0] = "Travel Voucher of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Travel Voucher has been approved by "
						+ empName + " ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Travel Voucher has been rejected by "
						+ empName + " ";
			}
			if (status.equals("D")) {
				subject[0] = "Travel Voucher Application has been deleted by "
						+ empName + " ";
			}

		} // end of if statement

		if (appType.equals("Leave")) {
			subject[0] = "Leave Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Leave Application has been approved by "
						+ empName + " ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Leave Application has been rejected by "
						+ empName + " ";
			}
			if (status.equals("N")) {
				subject[0] = "Your Leave Application has been cancelled by "
						+ empName + " ";
			}
			if (status.equals("B")) {
				subject[0] = "Your Leave Application has been sent back   by "
						+ empName + "  for some changes";
			}
			if (status.equals("C")) {
				subject[0] = "Your Leave Application has been forwarded  by "
						+ empName + "  for leave application cancellation";
			}
		} // end of if statement

		if (appType.equals("LeaveEncash")) {
			subject[0] = "Leave Encashment Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Leave Encashment Application has been approved by "
						+ empName + " ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Leave Encashment Application has been rejected by "
						+ empName + " ";
			}
			if (status.equals("N")) {
				subject[0] = "Your Leave Encashment Application has been cancelled by "
						+ empName + " ";
			}
			if (status.equals("B")) {
				subject[0] = "Your Leave Encashment Application has been sent back   by "
						+ empName + "  for some changes";
			}
		} // end of if statement
		if (appType.equals("CompOff")) {
			subject[0] = "Compensatory Off Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Compensatory Off Application has been approved by "
						+ empName + " ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Compensatory Off Application has been rejected by "
						+ empName + " ";
			}
			if (status.equals("D")) {
				subject[0] = "Compensatory Off Application of " + empName
						+ " has been Deleted.";
			}
		} // end of if statement

		if (appType.equals("Outdoor")) {
			subject[0] = "Outdoor Visit Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Outdoor Visit Application has been approved by "
						+ empName + " .";
			}
			if (status.equals("R")) {
				subject[0] = "Your Outdoor Visit Application has been rejected by "
						+ empName + " .";
			}

			if (status.equals("D")) {
				subject[0] = "Outdoor Visit Application of " + empName
						+ " has been Deleted.";
			}
		}

		if (appType.equals("AssetTransfer")) {

			if (status.equals("TR")) {
				subject[0] = "Requested Assets has been transfered to your warehouse from "
						+ empName + ".";
			}
			if (status.equals("RQ")) {
				subject[0] = "Asset request from " + empName + " is pending.";
			}
			if (status.equals("CL")) {
				subject[0] = "Your Asset request has been canceled by "
						+ empName + ".";
			}
		}
		if (appType.equals("Purchase")) {
			subject[0] = "Purchase Order Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Purchase Order Application has been approved by "
						+ empName + ". ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Purchase Order Application has been rejected by "
						+ empName + ". ";
			}
			if (status.equals("D")) {
				subject[0] = "Your Purchase Order Application has been deleted by "
						+ empName + ". ";
			}
			if (status.equals("C")) {
				subject[0] = "Your Purchase Order Application has been canceled by "
						+ empName + ". ";
			}
			if (status.equals("AE")) {
				subject[0] = "Purchase Order Application of " + appCode
						+ " has been updated by " + empName + ". ";
			}
			if (status.equals("EE")) {
				subject[0] = "Your Purchase Order Application has been updated by "
						+ empName + ". ";
			}
		} // end of if statement

		if (appType.equals("PurchaseInward")) {
			subject[0] = "Purchase Order of " + empName
					+ " is pending for Inward ";
		}

		if (appType.equals("Asset")) {
			subject[0] = "Asset Application of " + empName + " ";
			if (status.equals("A")) {
				subject[0] = "Your Asset Application has been approved by "
						+ empName + ". ";
			}
			if (status.equals("R")) {
				subject[0] = "Your Asset Application has been rejected by "
						+ empName + ". ";
			}
			if (status.equals("D")) {
				subject[0] = "Asset Application has been deleted by " + empName
						+ ". ";
			}
			if (status.equals("S")) {
				subject[0] = "Asset Application has been sent back by "
						+ empName + ". ";
			}
			if (status.equals("AE")) {
				subject[0] = "Asset Application of " + appCode
						+ " has been updated by " + empName + ".";
			}
			if (status.equals("EE")) {
				subject[0] = "Your Asset Application has been updated by "
						+ empName + ".";
			}
		} // end of if statement

		if (appType.equals("AssetAssignment")) {
			if (status.equals("AP")) {

				subject[0] = "Asset Application of " + empName
						+ " is pending for assignment. ";
			}
			if (status.equals("AS")) {
				subject[0] = "Asset has been assigned to you by " + empName
						+ ".";
			}
		}

		if (appType.equals("Sugg")) {
			subject[0] = "Suggestion Application of " + empName + " ";
			System.out.println("Employee name...!!" + empName);
			if (status.equals("A")) {
				subject[0] = "Your Suggestion Application has been approved by "
						+ empName + " .";
			}
			if (status.equals("R")) {
				subject[0] = "Your Suggestion Application has been rejected by "
						+ empName + " .";
			}

			if (status.equals("D")) {
				subject[0] = "Suggestion Application of " + empName
						+ " has been Deleted.";
			}
		}

		if (appType.equals("Appraisal")) {
			subject[0] = "Appraisal of " + empName + " ";

			if (status.equals("A")) {

				subject[0] = "Appraisal of " + empNameTo
						+ " is forwarded to next level.";

				messageBody[0] = "This is to inform you that <b>"
						+ empName
						+ "</b>  has completed your Manager Appraisal."
						// + " for the period "+ appCode
						+ " <br><br>Your appraisal is forwarded to the next level for further evaluation and soon you will be informed about your performance rating by our Human Capital Department.";

			}
			if (status.equals("SB")) {
				messageBody[0] = "Your Appraisal has been sent back by "
						+ empName + " .";
			}

			if (status.equals("D")) {
				messageBody[0] = "Appraisal of " + empName
						+ " has been Deleted.";
			}

			if (status.equals("P")) {

				messageBody[0] = "This is to inform you that <b>"
						+ empName
						+ "</b>  has submitted his/her Self Appraisal form. "
						// + " for the period "+ appCode
						+ " <br><br>Please visit your Evaluator Panel to complete the evaluations activity.";

			}
			if (status.equals("RM")) {

				subject[0] = "Appraisal Reminder Mail";
				messageBody[0] = "This is to inform you that your appraisal is due on "
						+ appCode
						+ " for the period "
						+ appCode
						+ " <br><br>Please visit your Evaluator Panel to complete the evaluations activity.";

			}

		}
		if (status.equals("P")) {
			subject[0] += "is pending .";
		}

		/**
		 * Create mail body containing message in HTML format
		 */
		String msg = null;

		if (appType.equals("Appraisal")) {
			msg = "<html> "
					+ "<style>"
					+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
					+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
					+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
					+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
					+ ".style15 {color: #CC0000} "
					+ ".style16 {color: #D23A49} "
					+ "</style>"
					+ "	<body> "
					+ "<table width='100%' border='0' cellpadding='0' cellspacing='0'>"
					+ "<tr> "
					+ "<td width='100%'>"
					+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
					+ "<tr> " + "<td><p>Dear&nbsp;<b>"
					+ empNameTo
					+ "</b>, </p><br /> "
					+ messageBody[0]
					+ "</td> "
					+ "</tr> "
					+ "<tr>"
					+ "<td width='100%'>"
					+ "<br><br><br>Have a great day ! "
					+ "</td></tr>"
					+ "<tr>"
					+ "<td width='100%'>"
					+ "<br><br>With Regards"
					+ "</td></tr>"
					+ "<tr>"
					+ "<td width='100%'>"
					+ "Human Resource Department."
					+ "</td></tr>"
					+ "<tr>"
					+ "<td width='100%'>"
					+ "<br><br>For any queries or assistance please contact HR department"
					+ "</td></tr>"
					+ "<tr>"
					+ "<td width='100%'>"
					+ "<br>(Note: This is a system generated mail and hence do not reply to this mail.)"
					+ "</td></tr>"
					+ "</table> "
					+ "</td> "
					+ "</tr> "
					+ "</table> " + "</body> " + "</html> ";
		} else {

			msg = "<html> "
					+ "<style>"
					+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
					+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
					+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
					+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
					+ ".style15 {color: #CC0000} "
					+ ".style16 {color: #D23A49} "
					+ "</style>"
					+ "	<body> "
					+ "<table width='100%' border='0' cellpadding='0' cellspacing='0'>"
					+ "<tr> "
					+ "<td width='100%'>"
					+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
					+ "<tr> " + "<td><p>Dear&nbsp;<b>" + empNameTo
					+ "</b>, </p><br /> " + subject[0] + "</td> " + "</tr> "
					+ "</table> " + "</td> " + "</tr> " + "</table> "
					+ "</body> " + "</html> ";
		}
		messg[0] = msg;

		SendMailModel sendMail = new SendMailModel();
		sendMail.initiate(context, session);
		messg[0] = sendMail.getMassMessage(messg[0]);// Get HTML text
		sendMail(toMails, fromMail, subject, messg, "",
				"sdadadsadsadsadasdsadsa");
		sendMail.terminate();
		model.terminate();
	}

	/** * */
	public void sendMail(String[] to_mailIds1, String[] from_mailIds1,
			String subject1[], String messg1[], String attachPath1,
			String fromEmpId) {
		final String[] to_mailIds = to_mailIds1;
		final String[] from_mailIds = from_mailIds1;
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
					String htmlText;
					BodyPart messageBodyPart = new MimeBodyPart();
					BodyPart messageBodyPart2 = new MimeBodyPart();
					Session session1 = null;
					boolean debug = false;
					Store store = null;

					javax.mail.Transport t = null;

					/**
					 * Get the server name, protocol, port, user name, password
					 * from database
					 */
					MailModel model = new MailModel();
					model.initiate(context, session);

					// Object[][] mailBoxData = model.getMailBoxPara();

					Object[][] mailBoxData = model.getServerMailBox(fromEmpId1,
							from_mailIds[0]);

					model.terminate();

					final String userName = String.valueOf(mailBoxData[0][3]);
					final String pass = String.valueOf(mailBoxData[0][4]);
					logger.info("-----userName-------" + userName);
					logger.info("-----pass-------" + pass);

					/**
					 * Make host and port as persistent
					 */
					Properties props = new Properties();
					props.put("mail.smtp.host", String.valueOf(
							mailBoxData[0][0]).trim());
					props.put("mail.smtp.port", String.valueOf(
							mailBoxData[0][1]).trim());

					if (String.valueOf(mailBoxData[0][9]).equals("pop3/ssl")) {
						props.put("mail.smtp.starttls.enable", "true");
						props.put("mail.smtp.ssl.trust", "*");

					}

					if (String.valueOf(mailBoxData[0][5]).equals("Y")) {// Authentication
						// is
						// true
						props.put("mail.smtp.auth", "true");

						/**
						 * Authenticator -: Authenticates the network connection
						 * PasswordAuthentication -: Repository for a user name
						 * and a password used by Authenticator.
						 */
						Authenticator auth = new Authenticator() {
							public PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(userName,
										pass);
							}
						};
						session1 = Session.getInstance(props, auth);// Creates a
						// mail
						// session
					} // end of if statement
					else {
						session1 = Session.getInstance(props);
					}

					/*
					 * POP before SMTP Check
					 */

					if (String.valueOf(mailBoxData[0][6]).equals("Y")) {

						logger
								.info("-----String.valueOf(mailBoxData[0][7])-------"
										+ String.valueOf(mailBoxData[0][6]));

						/*
						 * String inBoundSettingQuery = " SELECT MAILBOX_SERVER,
						 * MAILBOX_PROTOCOL, MAILBOX_PORT, " + " MAILBOX_USERID,
						 * MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_FLAG
						 * ,MAILBOX_POP_BEFORE_SMTP " + " FROM " + "
						 * HRMS_SETTINGS_MAILBOX " + " WHERE " + "
						 * MAILBOX_FLAG='I'";
						 * 
						 * Object inBoundSettingObj[][] = getSqlModel()
						 * .getSingleResult(inBoundSettingQuery);
						 */
						try {
							store = session1.getStore("pop3");
						} catch (NoSuchProviderException e1) {
							e1.printStackTrace();
						}
						try {
							logger.info("---in store.connect--");
							String popPass = "";

							popPass = String.valueOf(mailBoxData[0][4]);

							store.connect(String.valueOf(mailBoxData[0][7]),
									String.valueOf(mailBoxData[0][3]), popPass);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					/**
					 * Transport -: An abstract class that models a message
					 * transport
					 */
					else {
						try {
							t = session1.getTransport("smtp");// Get a
							// Transport
							// object that
							// implements the
							// specified
							// protocol
						} catch (NoSuchProviderException e1) {
							logger.info(e1);
							e1.printStackTrace();
						}
						try {
							if (t != null) {
								t.connect();// Makes a connection to the service
							}
						} catch (AuthenticationFailedException ae) {
							logger.info(ae);
							ae.printStackTrace();
						} catch (Exception e) {
							logger.info(e);
							e.printStackTrace();
						}
					}

					logger
							.info("-connect----by vishwambhar----------------------");
					/**
					 * Create an attachment
					 */
					String file1 = "";
					if (!(String.valueOf(attachPath).equalsIgnoreCase("null") || String
							.valueOf(attachPath.trim()).equalsIgnoreCase(""))) {
						file1 = context.getRealPath("//") + "/pages/images/"
								+ session.getAttribute("session_pool")
								+ "/mails/" + attachPath + "";
					} // end of if statement

					if (to_mailIds.length > 0) {
						try {

							/**
							 * InternetAddress -: Represents an Internet email
							 * address using the syntax of RFC822
							 */
							InternetAddress addressFrom = null;
							for (int z = 0; z < to_mailIds.length; z++) {
								if (!(String.valueOf(to_mailIds[z])
										.equals("null"))) {
									htmlText = messg[z];
									InternetAddress toEmail[] = new InternetAddress[1];
									toEmail[0] = new InternetAddress(String
											.valueOf(to_mailIds[z]));
									logger.info("To email :"
											+ String.valueOf(to_mailIds[z]));
									if (cn < from_mailIds.length) {
										addressFrom = new InternetAddress(
												from_mailIds[cn]);
										logger.info("from email :"
												+ from_mailIds[cn]);
										cn++;
									} else {
										cn = 0;
										addressFrom = new InternetAddress(
												from_mailIds[cn]);
										logger.info("from email :"
												+ from_mailIds[cn]);
										cn++;
									}
									try {
										session1.setDebug(debug);// Set the
										// debug
										// setting
										// for
										// Session.

										/**
										 * Message -: Models an email message
										 * MimeMessage -: Represents a MIME
										 * style email message, implements the
										 * Message abstract class MimeMultipart -:
										 * Create a new MIME message
										 */
										javax.mail.Message message = new MimeMessage(
												session1);
										MimeMultipart multipart = new MimeMultipart(
												"related");

										((MimeMessage) message)
												.setSubject(subject[z]);
										message.setFrom(addressFrom);
										message.addHeader("To", String
												.valueOf(to_mailIds[z]));

										/**
										 * Add attachments
										 */
										/**
										 * DataSource -: Provides arbitrary
										 * collection of data, provides a type
										 * for that data as well as access to it
										 * in the form of InputStreams and
										 * OutputStreams
										 */
										if (!(String.valueOf(attachPath)
												.equalsIgnoreCase("null") || String
												.valueOf(attachPath)
												.equalsIgnoreCase(""))) {
											DataSource fds1 = new FileDataSource(
													file1);
											messageBodyPart2
													.setDataHandler(new DataHandler(
															fds1));
											messageBodyPart2.setHeader(
													"Content-ID", "<cake>");
											multipart
													.addBodyPart(messageBodyPart2);
										} // end of if statement

										/**
										 * Creates a body of the mail
										 */
										messageBodyPart.setContent(htmlText,
												"text/html");
										multipart.addBodyPart(messageBodyPart);
										((Part) message).setContent(multipart);

										t.sendMessage(message, toEmail);// Send
										// mail
									} catch (Exception e) {
										logger.info(e);
									} // end of try-catch block
								} // end of if statement
							} // end of for loop
						} catch (Exception e) {
							logger.info(e);
							e.printStackTrace();
						} // end of try-catch block
					} // end of if statement
					try {
						t.close();
					} catch (MessagingException e) {
						logger.info(e);
					} // end of try-catch block
				} catch (Exception e) {
					logger.info(e);
				} // end of try-catch block
			}
		}).start();
	}

	public String sendMail(Object[][] from_mailIds, Object[][] toEmpObj,
			String[] subject, String[] messageBody) {
		String result = "false";

		int count = 0;
		Object tempEmpObj[][] = null;

		logger.info("mail Subject=='" + subject[0] + "'");
		logger.info("mail message body=='" + messageBody[0] + "'");

		try {

			int initValue = 100;

			int counter = toEmpObj.length;
			do {

				try {
					if (counter < initValue) {

						tempEmpObj = new Object[counter][1];
						for (int j = 0; j < counter; j++) {
							tempEmpObj[j][0] = toEmpObj[count + j][0];
						}

					} else {
						tempEmpObj = new Object[initValue][1];
						for (int j = 0; j < initValue; j++) {
							tempEmpObj[j][0] = toEmpObj[count + j][0];
						}
						count += initValue;

					}
					counter = counter - initValue;
					logger.info("tempEmpObj length==" + tempEmpObj.length);
					sendApprReminderMail(tempEmpObj, from_mailIds, messageBody,
							subject);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} while (counter >= 0);

			result = "true";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "false";
		}

		return result;
	}

	public void sendApprReminderMail(Object[][] to_mailIds,
			Object[][] from_mailIds, String[] messageBody, String[] subject) {
		MailModel model = new MailModel();
		model.initiate(context, session);

		String[] fromMail = new String[1];

		fromMail[0] = "" + from_mailIds[0][0];

		String[] toMails = new String[to_mailIds.length];
		for (int i = 0; i < toMails.length; i++) {
			toMails[i] = "" + to_mailIds[i][0];
		}
		// String empName = model.getEmpName(from_mailIds);//Get emp name for
		// the first emp ID
		// String empNameTo = model.getEmpName(to_mailIds);//Get emp name for
		// the first emp ID

		String[] messg = new String[1];

		/**
		 * Create mail body containing message in HTML format
		 */
		String msg = null;

		msg = "<html> "
				+ "<style>"
				+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
				+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
				+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
				+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
				+ ".style15 {color: #CC0000} "
				+ ".style16 {color: #D23A49} "
				+ "</style>"
				+ "	<body> "
				+ "<table width='100%' border='0' cellpadding='0' cellspacing='0'>"
				+ "<tr> "
				+ "<td width='100%'>"
				+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
				+ "<tr> " + "<td><p>Dear Colleagues&nbsp;<b>"
				+ "</b>, </p><br /> "
				+ messageBody[0]
				+ "</td> "
				+ "</tr> "
				+ "<tr>"
				+ "<td width='100%'>"
				+ "<br><br>With Regards,"
				+ "</td></tr>"
				+ "<tr>"
				+ "<td width='100%'>"
				+ "Vikas V. Pathak"
				+ "</td></tr>"
				+ "<tr>"
				+ "<td width='100%'>"
				+ "Sr. Vice President & Head "
				+ "</td></tr>"
				+ "<tr>"
				+ "<td width='100%'>"
				+ "Human Capital & Admin (Global Operations) "
				+ "</td></tr>"
				+ "<tr>"
				+ "<td width='100%'>"
				+ "<br><br>For any queries or assistance please mail to <u>support@peoplepower.com.</u>"
				+ "</td></tr>"
				+ "<tr>"
				+ "<td width='100%'>"
				+ "<br>(Note: This is a system generated mail and hence do not reply to this mail.)"
				+ "</td></tr>"
				+ "</table> "
				+ "</td> "
				+ "</tr> "
				+ "</table> " + "</body> " + "</html> ";

		messg[0] = msg;

		SendMailModel sendMail = new SendMailModel();
		sendMail.initiate(context, session);
		messg[0] = sendMail.getMassMessage(messg[0]);// Get HTML text
		sendBdayMail(toMails, fromMail, subject, messg, "");
		sendMail.terminate();
		model.terminate();
	}

	public void sendBdayMail(String[] to_mailIds1, String[] from_mailIds1,
			String subject1[], String messg1[], String attachPath1) {
		final String[] to_mailIds = to_mailIds1;
		final String[] from_mailIds = from_mailIds1;
		final String[] subject = subject1;
		final String[] messg = messg1;
		final String attachPath = attachPath1;
		/**
		 * Creates a separate thread
		 */
		new Thread(new Runnable() {
			public void run() {
				try {
					try {
						String htmlText;
						BodyPart messageBodyPart = new MimeBodyPart();
						BodyPart messageBodyPart2 = new MimeBodyPart();
						Session session1 = null;
						boolean debug = false;
						MailModel model = new MailModel();
						model.initiate(context, session);
						Object[][] mailBoxData = model.getMailBoxPara();
						model.terminate();
						if (mailBoxData.length > 0) {
							Properties props = new Properties();

							final String userName = String
									.valueOf(mailBoxData[0][3]);
							final String pass = String
									.valueOf(mailBoxData[0][4]);
							logger.info("-----userName-------" + userName);
							logger.info("-----pass-------" + pass);
							props.put("mail.smtp.host", String.valueOf(
									mailBoxData[0][0]).trim());
							props.put("mail.smtp.port", String.valueOf(
									mailBoxData[0][2]).trim());

							if (String.valueOf(mailBoxData[0][9]).equals(
									"pop3/ssl")) {
								props.put("mail.smtp.starttls.enable", "true");
								props.put("mail.smtp.ssl.trust", "*");

							}

							if (String.valueOf(mailBoxData[0][5]).equals("Y")) {

								props.put("mail.smtp.auth", "true");
								Authenticator auth = new Authenticator() {
									public PasswordAuthentication getPasswordAuthentication() {
										return new PasswordAuthentication(
												userName, pass);
									}
								};
								session1 = Session.getInstance(props, auth);
							} else {

								session1 = Session.getInstance(props);
							}

						}
						javax.mail.Transport t = null;
						try {
							t = session1.getTransport("smtp");
							try {
								t.connect();
							} catch (MessagingException e) {
								logger.info(e);
							}
						} catch (NoSuchProviderException e1) {
							logger.info(e1);
						}

						logger.info("-connect------");
						String file1 = "";
						if (!(String.valueOf(attachPath).equalsIgnoreCase(
								"null") || String.valueOf(attachPath.trim())
								.equalsIgnoreCase(""))) {
							file1 = context.getRealPath("//")
									+ "/pages/images/"
									+ session.getAttribute("session_pool")
									+ "/mails/" + attachPath + "";
						}

						if (to_mailIds.length > 0) {
							InternetAddress toEmail[] = new InternetAddress[to_mailIds.length];

							htmlText = messg[0];// "<html> "

							try {
								javax.mail.Message message = new MimeMessage(
										session1);

								InternetAddress addressFrom = null;
								for (int z = 0; z < to_mailIds.length; z++) {
									if (!(String.valueOf(to_mailIds[z])
											.equals("null"))) {
										// logger.info("11111----*****+++++++++***---->>
										// "+to_mailIds[z]);

										toEmail[z] = new InternetAddress(String
												.valueOf(to_mailIds[z]));
										logger
												.info("TO Employee ----********---->> "
														+ toEmail[z]);

										addressFrom = new InternetAddress(
												from_mailIds[0]);
										message.addHeader("To", String
												.valueOf(to_mailIds[z]));
										logger
												.info("From Employee ----********---->> "
														+ from_mailIds[0]);

									}
								}
								try {
									session1.setDebug(debug);

									MimeMultipart multipart = new MimeMultipart(
											"related");
									((MimeMessage) message)
											.setSubject(subject[0]);
									message.setFrom(addressFrom);

									if (!(String.valueOf(attachPath)
											.equalsIgnoreCase("null") || String
											.valueOf(attachPath)
											.equalsIgnoreCase(""))) {
										DataSource fds1 = new FileDataSource(
												file1);
										messageBodyPart2
												.setDataHandler(new DataHandler(
														fds1));
										messageBodyPart2.setHeader(
												"Content-ID", "");
										multipart.addBodyPart(messageBodyPart2);
									}
									messageBodyPart.setContent(htmlText,
											"text/html");

									multipart.addBodyPart(messageBodyPart);
									((Part) message).setContent(multipart);
									t.sendMessage(message, toEmail);
									logger.info("TOTAL MAILS SENT= "
											+ to_mailIds.length);
								} catch (Exception e) {
									logger.info(e);
									e.printStackTrace();
								}

							} catch (Exception e) {
								logger.info(e);
							}
						}
						try {
							t.close();
						} catch (MessagingException e) {
							logger.info(e);
						}
					} catch (Exception e) {
						logger.info(e);
					}
				} catch (Exception e) {
					logger.info(e);
				} // end of try-catch block
			}
		}).start();
	}

	public String sendMail(String[] newToMailIds, String[] newFromMailId,
			String subject1, String messg1, String[] attachPath1,
			String fromEmpId1, boolean isThread) {
		return sendMail(newToMailIds, newFromMailId, subject1, messg1,
				attachPath1, fromEmpId1, isThread, false, null, null, null,
				null);
	}
	
	 
	
 
	 
	 
	/**
	 * CREATED BY VISHWAMBHAR THIS METHOD IS USED FOR 1)TESTING MAIL CONNECTION
	 * 2)FOR SENDING LEAVE APPLICATION MAILS 3)FOR FORGOT PASSWORD 4k 5)FOR
	 * ANNIVERSARY DAY MAIL 6)MASS MAIL 7)SEND APPLICATION MAIL TO KEEPINFO
	 * 8)SENDAPPLICATION MAIL TO ALTERNATE APPROVER
	 * 
	 * @param newToMailIds
	 * @param newFromMailId
	 * @param subject1
	 * @param messg1
	 * @param attachPath1
	 * @param fromEmpId1
	 */

	public String sendMail(String[] newToMailIds, String[] newFromMailId,
			String subject1, String messg1, String attachPath1,
			String fromEmpId1, boolean isThread) {
		return sendMail(newToMailIds, newFromMailId, subject1, messg1,
				new String[] { attachPath1 }, fromEmpId1, isThread, false,
				null, null, null, null);
	}
	
	public String sendMail(String[] newToMailIds, String[] newFromMailId,
			String subject1, String messg1, String attachPath1,
			String fromEmpId1, boolean isThread,Connection dbconn) {
		return sendMail(newToMailIds, newFromMailId, subject1, messg1,
				new String[] { attachPath1 }, fromEmpId1, isThread, false,
				null, null, null, null,dbconn);
	}
	
	
	
	public String sendMail(String[] newToMailIds, String[] newFromMailId,
			String subject1, String messg1, String[] attachPath1,
			String fromEmpId1, boolean isThread, boolean isCalendar,
			String fromDate, String toDate, String calMessage, String calSubject,final Connection dbconn) {
		String message = "";

		try {
			final boolean isCalendarIn = isCalendar;
			final String[] to_mailIds = newToMailIds;
			final String[] from_mailIds = newFromMailId;
			final String subject = subject1;
			final String messg = messg1;
			final String fromEmpId = fromEmpId1;
			final String fromDateIn = fromDate;
			final String toDateIn = toDate;
			final String calMessageIn = calMessage;
			final String[] attachPath = attachPath1;
			final String calSubjectIn = calSubject;

			if (isThread) {
				new Thread(new Runnable() {
					public void run() {
						sendMail(to_mailIds, from_mailIds, subject, messg,
								attachPath, fromEmpId, isCalendarIn,
								fromDateIn, toDateIn, calMessageIn,
								calSubjectIn,dbconn);
					}
				}).start();
			} else {
				message = sendMail(to_mailIds, from_mailIds, subject, messg,
						attachPath1, fromEmpId, isCalendarIn, fromDateIn,
						toDateIn, calMessageIn, calSubjectIn,dbconn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}
	 
	
 
	public String sendMail(String[] newToMailIds, String[] newFromMailId,
			String subject1, String messg1, String[] attachPath1,
			String fromEmpId1, boolean isThread, boolean isCalendar,
			String fromDate, String toDate, String calMessage, String calSubject) {
		String message = "";

		try {
			final boolean isCalendarIn = isCalendar;
			final String[] to_mailIds = newToMailIds;
			final String[] from_mailIds = newFromMailId;
			final String subject = subject1;
			final String messg = messg1;
			final String fromEmpId = fromEmpId1;
			final String fromDateIn = fromDate;
			final String toDateIn = toDate;
			final String calMessageIn = calMessage;
			final String[] attachPath = attachPath1;
			final String calSubjectIn = calSubject;

			if (isThread) {
				new Thread(new Runnable() {
					public void run() {
						sendMail(to_mailIds, from_mailIds, subject, messg,
								attachPath, fromEmpId, isCalendarIn,
								fromDateIn, toDateIn, calMessageIn,
								calSubjectIn);
					}
				}).start();
			} else {
				message = sendMail(to_mailIds, from_mailIds, subject, messg,
						attachPath1, fromEmpId, isCalendarIn, fromDateIn,
						toDateIn, calMessageIn, calSubjectIn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	public String sendMail(String[] newToMailIds, String[] newFromMailId,
			String subject1, String messg1, String attachPath1,
			String fromEmpId1) {
		return sendMail(newToMailIds, newFromMailId, subject1, messg1,
				new String[] { attachPath1 }, fromEmpId1, false, null, null,
				null, null);
	}

	/**
	 * @param newToMailIds
	 * @param newFromMailId
	 * @param subject1
	 * @param messg1
	 * @param attachPath1
	 * @param fromEmpId1
	 */
	
	
	public String sendMail(String[] newToMailIds, String[] newFromMailId,
			String subject1, String messg1, String[] attachPath1,
			String fromEmpId1, boolean isCalendar, String fromDate,
			String toDate, String calMessage, String calSubject,Connection dbconn) {
		// for sending mails

		String msgTolog = "";

		try {
			ResourceBundle globalProp = ResourceBundle
					.getBundle("globalMessages");// Get
			// resource
			// bundle
			Date dt = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
			String date = sd.format(dt);
			String inFileName = globalProp.getString("email_logs") + "\\logs"
					+ date + ".txt";

			File file1Log = new File(globalProp.getString("email_logs") + "\\");

			File fileName = new File(inFileName); // file changes
			if (!file1Log.exists()) {
				file1Log.mkdirs();
			}
			output = new PrintWriter(new FileWriter(fileName, true));

			MailUtility.output.println();

			MailUtility.output.println();
			MailUtility.output.write("value of date is------" + date);
			MailUtility.output.println();
			MailUtility.output.println();

		} catch (Exception e) {

		}
		String[] to_mailIds = newToMailIds;
		String[] from_mailIds = newFromMailId;
		String subject = subject1;

		MailUtility.output.write("subject is-------" + subject);
		MailUtility.output.println();
		MailUtility.output.println();

		String messg = messg1;
		String[] attachPath = attachPath1;
		String fromEmpId = fromEmpId1;

		String htmlText;
		BodyPart messageBodyPart = new MimeBodyPart();

		Session session1 = null;
		boolean debug = false;

		javax.mail.Transport t = null;
		Store store = null;

		MailModel model = new MailModel();
		model.initiate(context, session);

		System.out.println("fromEmpId  in sendMail" + fromEmpId);

		System.out.println("from_mailIds[0]  in sendMail " + from_mailIds[0]);

		Object[][] mailBoxData = model.getServerMailBox(fromEmpId,
				from_mailIds[0],dbconn);
		model.terminate();
		if (mailBoxData.length > 0) {

			String fromMailId[] = new String[1];
			fromMailId[0] = String.valueOf(mailBoxData[0][11]);

			if (String.valueOf(mailBoxData[0][15]).equals("Y")) {
				from_mailIds = fromMailId;
			}

			logger
					.info("-----mailBoxData.length----from_mailIds--from_mailIds-"
							+ from_mailIds);

			Properties props = new Properties();
			logger.info("-----mailBoxData.length-------" + mailBoxData.length);

			final String userName = String.valueOf(mailBoxData[0][3]);
			String passSMTP = "";

			passSMTP = String.valueOf(mailBoxData[0][4]);

			final String pass = passSMTP;
			logger.info("-----userName-------" + userName);
			logger.info("-----pass-------" + pass);

			if (String.valueOf(mailBoxData[0][2]).equals("SMTP")
					|| String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")
					|| String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
				props.put("mail.smtp.host", String.valueOf(mailBoxData[0][0])
						.trim());
				props.put("mail.smtp.port", String.valueOf(mailBoxData[0][1])
						.trim());

			} else if (String.valueOf(mailBoxData[0][2]).equals("IMAP")) {
				props.put("mail.imap.host", String.valueOf(mailBoxData[0][0])
						.trim());
				props.put("mail.imap.port", String.valueOf(mailBoxData[0][1])
						.trim());
			}
			if (String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")) {
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "*");
			}
			if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "*");
				props.put("mail.smtp.socketFactory.port", String.valueOf(
						mailBoxData[0][1]).trim());
				props.put("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "false");
				props.setProperty("mail.smtp.quitwait", "false");
			}

			/*
			 * Authentication Required Check
			 */
			if (String.valueOf(mailBoxData[0][5]).equals("Y")) {

				if (String.valueOf(mailBoxData[0][2]).equals("SMTP")
						|| String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")
						|| String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
					props.put("mail.smtp.auth", "true");
				} else if (String.valueOf(mailBoxData[0][2]).equals("IMAP")) {
					props.put("mail.imap.auth.login.disable", "false");
				}
				Authenticator auth = null;
				try {
					auth = new Authenticator() {
						public PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userName, pass);
						}
					};
				} catch (Exception e) {
					exceptionMessage += "\n" + e.getMessage();
				}
				try {
					session1 = Session.getInstance(props, auth);
				} catch (Exception e) {
					exceptionMessage += "\n" + e;
				}

			} else {

				try {
					session1 = Session.getInstance(props);
				} catch (Exception e) {
					exceptionMessage += "\n" + e;
				}
			}

		}
		/*
		 * POP before SMTP Check
		 */
		if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
			try {
				store = session1.getStore("pop3");
			} catch (NoSuchProviderException e1) {
				exceptionMessage += "\n" + e1;
			}
			try {
				logger.info("---in store.connect------------------");
				String popPass = "";

				popPass = String.valueOf(mailBoxData[0][4]);

				store.connect(String.valueOf(mailBoxData[0][7]), String
						.valueOf(mailBoxData[0][3]), popPass);
			} catch (Exception e) {
				exceptionMessage += "\n" + e;
			}
		} else {
			try {
				t = session1.getTransport("smtp");
			} catch (NoSuchProviderException e1) {
				exceptionMessage += "\n" + e1;
			}
			try {
				if (t != null) {
					t.connect();// Makes a connection to the service
				}
			} catch (AuthenticationFailedException ae) {
				exceptionMessage += "\n"
						+ "Authentication Failed.\nPlease verify Authentication username and password.";
			} catch (Exception e) {
				exceptionMessage += "\n" + e.getMessage();
			}
		}
		logger.info("-connect--------------------------");

		if (to_mailIds.length > 0) {
			htmlText = messg;// "<html> "
			try {
				InternetAddress addressFrom = null;
				for (int z = 0; z < to_mailIds.length; z++) {
					if (!(String.valueOf(to_mailIds[z]).equals("null"))) {
						// logger.info("11111----*****+++++++++***---->>
						// "+to_mailIds[z]);
						InternetAddress toEmail[] = new InternetAddress[1];
						try {
							toEmail[0] = new InternetAddress(String
									.valueOf(to_mailIds[z]));
							logger.info("TO Employee ----********---->> "
									+ to_mailIds[z]);
							MailUtility.output.write("to_mailIds is-------"
									+ to_mailIds[z]);
							MailUtility.output.println();
							MailUtility.output.println();
						} catch (AddressException e) {
							exceptionMessage += "\n" + "Invalid from address.";
							e.printStackTrace();
						}
						String fromMailId = "";
						if (cn < from_mailIds.length) {

							try {
								fromMailId = from_mailIds[cn];
								addressFrom = new InternetAddress(
										from_mailIds[cn]);

								MailUtility.output
										.write("from_mailIds is-------"
												+ from_mailIds[cn]);
								MailUtility.output.println();
								MailUtility.output.println();
							} catch (AddressException e) {
								exceptionMessage += "\n"
										+ "Invalid from address.";
								e.printStackTrace();
							}
							logger.info("From Employee ----********---->> "
									+ from_mailIds[cn]);
							cn++;
						} else {
							cn = 0;
							try {
								addressFrom = new InternetAddress(
										from_mailIds[cn]);
								fromMailId = from_mailIds[cn];
							} catch (AddressException e) {
								exceptionMessage += "\n"
										+ "Invalid from address.";
							}
							logger.info("From Employee ----********---->> "
									+ from_mailIds[cn]);
							cn++;
						}

						try {
							// session1.setDebug(debug);
							// session1.setDebug(true);
							javax.mail.Message message = new MimeMessage(
									session1);

							MimeMultipart multipart = new MimeMultipart(
									"related");
							// *************88************************************************************************************/
							if (isCalendar) {
								((MimePart) message)
										.addHeaderLine("method=REQUEST");
								((MimePart) message)
										.addHeaderLine("charset=UTF-8");
								((MimePart) message)
										.addHeaderLine("component=VEVENT");
								SimpleDateFormat sd = new SimpleDateFormat(
										"yyyyMMdd");
								System.out.println("....88888."
										+ sd.format(Utility.getDate(fromDate)));
								StringBuffer sb = new StringBuffer();

								StringBuffer buffer = sb
										.append("BEGIN:VCALENDAR\n"
												+ "PRODID:BCP\n"
												+ "VERSION:2.0\n"
												+ "METHOD:REQUEST\n"
												+ "BEGIN:VEVENT\n"
												+ "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:"
												+ to_mailIds[z]
												+ "\n"
												+ "ORGANIZER:MAILTO:"
												+ fromMailId
												+ "\n"
												+ "DTSTART:"
												+ sd.format(Utility
														.getDate(fromDate))
												+ "T000000Z"
												+ "\n"
												+ "DTEND:"
												+ sd.format(Utility
														.getDate(toDate))
												+ "T000000Z"
												+ "\n"
												+ "LOCATION:\n"
												+ "TRANSP:OPAQUE\n"
												+ "SEQUENCE:0\n"
												+ "UID:040000008200E00074C5B7101A82E00800000000002FF466CE3AC5010000000000000000100\n"
												+ " 000004377FE5C37984842BF9440448399EB02\n"
												+ "DTSTAMP:"
												+ sd.format(Utility
														.getDate(fromDate))
												+ "T053000Z"
												+ "\n"
												+ "CATEGORIES:Travel\n"
												+ "DESCRIPTION:"
												+ calMessage
												+ "\n\n"
												+ "SUMMARY:"
												+ calSubject
												+ "\n"
												+ "PRIORITY:5\n"
												+ "CLASS:PUBLIC\n"
												+ "BEGIN:VALARM\n"
												+ "TRIGGER:PT1440M\n"
												+ "ACTION:DISPLAY\n"
												+ "DESCRIPTION:"
												+ calMessage
												+ "\n"
												+ "END:VALARM\n"
												+ "END:VEVENT\n"
												+ "END:VCALENDAR");
								System.out.println("buffer   "
										+ buffer.toString());
								// Create the message part
								BodyPart calendarBodyPart = new MimeBodyPart();

								// Fill the message
								calendarBodyPart.setHeader("Content-Class",
										"urn:content-classes:calendarmessage");
								calendarBodyPart.setHeader("Content-ID",
										"calendar_message");
								calendarBodyPart
										.setDataHandler(new DataHandler(
												new ByteArrayDataSource(buffer
														.toString(),
														"text/calendar")));// very
								// Add part one
								multipart.addBodyPart(calendarBodyPart);
							}
							// *************88************************************************************************************/

							try {
								((MimeMessage) message).setSubject(subject);
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
								e.printStackTrace();
							}
							try {
								message.setFrom(addressFrom);
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
								e.printStackTrace();
							}
							try {
								message.addHeader("To", String
										.valueOf(to_mailIds[z]));
							} catch (Exception e) {
								// TODO: handle exception
							}

							try {

								if (attachPath != null && attachPath.length > 0) {
									System.out.println("attachPath....."
											+ attachPath.length);
									for (int i = 0; i < attachPath.length; i++) {
										String file1 = "";
										System.out.println("attachPath[i]   "
												+ attachPath[i]);
										file1 = String.valueOf(attachPath[i]);
										if (file1 != null && !file1.equals("")
												&& !file1.equals("null")) {
											File fileAttach = null;
											try {
												fileAttach = new File(file1);
											} catch (Exception e) {
												fileAttach = null;
											}

											if (fileAttach != null) {
												logger.info("  file1-------  "
														+ file1);
												DataSource fds1 = new FileDataSource(
														fileAttach);
												BodyPart messageBodyPart2 = new MimeBodyPart();
												messageBodyPart2
														.setDataHandler(new DataHandler(
																fds1));
												  messageBodyPart2.setFileName(fileAttach.getName());
												multipart
														.addBodyPart(messageBodyPart2);
											}
										}
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								messageBodyPart.setContent(htmlText,
										"text/html");
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
								e.printStackTrace();
							}
							try {
								multipart.addBodyPart(messageBodyPart);
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
								e.printStackTrace();
							}
							try {
								((Part) message).setContent(multipart);
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
							}
							if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
								try {
									Transport.send(message);
								} catch (MessagingException e1) {
									// TODO Auto-generated catch block
									exceptionMessage += "\n" + e1.getMessage();
									e1.printStackTrace();
								}
								try {
									store.close();
								} catch (MessagingException e1) {
									// TODO Auto-generated catch block
									exceptionMessage += "\n" + e1.getMessage();
									e1.printStackTrace();
								}
								try {
									Thread.sleep(5000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									exceptionMessage += "\n" + e.getMessage();
								}
							} else {
								try {

									if (t.isConnected()) {
										// t.sendMessage(message, toEmail);
										t.send(message);

										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
										}
									}

								} catch (javax.mail.MessagingException se) {

									try {
										Thread.sleep(5);
									} catch (InterruptedException e) {
									}
									
									  try {
										Address[] ads = ((SendFailedException) se)
												.getValidUnsentAddresses();
										if (ads != null) {
											for (int i = 0; i < ads.length; i++) {
												exceptionMessage += "\nInvalid Addresses:"
														+ ads[i];
											}
										}
									} catch (Exception e) {
										// TODO: handle exception
									}
									exceptionMessage += "\n" + se.getCause();
									se.printStackTrace();
								}
							}

						} catch (Exception e) {
							exceptionMessage += "\n" + e.getMessage();
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				exceptionMessage += "\n" + e.getMessage();
			}
		}
		if (exceptionMessage.equals("")) {
			msgTolog = "mail send successfully";
		} else {
			msgTolog += exceptionMessage;
		}

		logger.info("final value of message is-----------" + msgTolog);

		MailUtility.output.write("final value of message is-----------"
				+ msgTolog);
		MailUtility.output.println();
		MailUtility.output.println();

		output.close();

		System.out.println("final value of exceptionMessage is equal======== "
				+ exceptionMessage);

		return exceptionMessage;
	}

	public String sendMail(String[] newToMailIds, String[] newFromMailId,
			String subject1, String messg1, String[] attachPath1,
			String fromEmpId1, boolean isCalendar, String fromDate,
			String toDate, String calMessage, String calSubject) {
		// for sending mails

		String msgTolog = "";

		try {
			ResourceBundle globalProp = ResourceBundle
					.getBundle("globalMessages");// Get
			// resource
			// bundle
			Date dt = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
			String date = sd.format(dt);
			String inFileName = globalProp.getString("email_logs") + "\\logs"
					+ date + ".txt"; 

			File file1Log = new File(globalProp.getString("email_logs") + "\\");

			File fileName = new File(inFileName); // file changes
			if (!file1Log.exists()) {
				file1Log.mkdirs();
			}
			output = new PrintWriter(new FileWriter(fileName, true));

			MailUtility.output.println();

			MailUtility.output.println();
			MailUtility.output.write("value of date is------" + date);
			MailUtility.output.println();
			MailUtility.output.println();

		} catch (Exception e) {

		}
		String[] to_mailIds = newToMailIds;
		String[] from_mailIds = newFromMailId;
		String subject = subject1;

		MailUtility.output.write("subject is-------" + subject);
		MailUtility.output.println();
		MailUtility.output.println();

		String messg = messg1;
		String[] attachPath = attachPath1;
		String fromEmpId = fromEmpId1;

		String htmlText;
		BodyPart messageBodyPart = new MimeBodyPart();

		Session session1 = null;
		boolean debug = false;

		javax.mail.Transport t = null;
		Store store = null;

		MailModel model = new MailModel();
		model.initiate(context, session);

		System.out.println("fromEmpId  in sendMail" + fromEmpId);

		System.out.println("from_mailIds[0]  in sendMail " + from_mailIds[0]);

		Object[][] mailBoxData = model.getServerMailBox(fromEmpId,
				from_mailIds[0]);
		model.terminate();
		if (mailBoxData.length > 0) {

			String fromMailId[] = new String[1];
			fromMailId[0] = String.valueOf(mailBoxData[0][11]);

			if (String.valueOf(mailBoxData[0][15]).equals("Y")) {
				from_mailIds = fromMailId;
			}

			logger
					.info("-----mailBoxData.length----from_mailIds--from_mailIds-"
							+ from_mailIds);

			Properties props = new Properties();
			logger.info("-----mailBoxData.length-------" + mailBoxData.length);

			final String userName = String.valueOf(mailBoxData[0][3]);
			String passSMTP = "";

			passSMTP = String.valueOf(mailBoxData[0][4]);

			final String pass = passSMTP;
			logger.info("-----userName-------" + userName);
			logger.info("-----pass-------" + pass);

			if (String.valueOf(mailBoxData[0][2]).equals("SMTP") || String.valueOf(mailBoxData[0][2]).equals("SMTPTLS") || String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
				
				props.put("mail.smtp.host", String.valueOf(mailBoxData[0][0]).trim());
				props.put("mail.smtp.port", String.valueOf(mailBoxData[0][1]).trim());
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "*");
				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "false");

			} else if (String.valueOf(mailBoxData[0][2]).equals("IMAP")) {
				
				props.put("mail.imap.host", String.valueOf(mailBoxData[0][0]).trim());
				props.put("mail.imap.port", String.valueOf(mailBoxData[0][1]).trim());
			}
			if (String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")) {
				
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "*");
			}
			if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
				
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "*");
				props.put("mail.smtp.socketFactory.port", String.valueOf(mailBoxData[0][1]).trim());
				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "false");
				props.setProperty("mail.smtp.quitwait", "false");
			}

			/*
			 * Authentication Required Check
			 */
			if (String.valueOf(mailBoxData[0][5]).equals("Y")) {

				if (String.valueOf(mailBoxData[0][2]).equals("SMTP") || String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")	|| String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {

					props.put("mail.smtp.auth", "true");
				
				} else if (String.valueOf(mailBoxData[0][2]).equals("IMAP")) {
				
					props.put("mail.imap.auth.login.disable", "false");
				}
				Authenticator auth = null;
				try {
					auth = new Authenticator() {
						public PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userName, pass);
						}
					};
				} catch (Exception e) {
					exceptionMessage += "\n" + e.getMessage();
				}
				try {
					session1 = Session.getInstance(props, auth);
				} catch (Exception e) {
					exceptionMessage += "\n" + e;
				}

			} else {

				try {
					session1 = Session.getInstance(props);
				} catch (Exception e) {
					exceptionMessage += "\n" + e;
				}
			}

		}
		/*
		 * POP before SMTP Check
		 */
		if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
			try {
				store = session1.getStore("pop3");
			} catch (NoSuchProviderException e1) {
				exceptionMessage += "\n" + e1;
			}
			try {
				logger.info("---in store.connect------------------");
				String popPass = "";

				popPass = String.valueOf(mailBoxData[0][4]);

				store.connect(String.valueOf(mailBoxData[0][7]), String
						.valueOf(mailBoxData[0][3]), popPass);
			} catch (Exception e) {
				exceptionMessage += "\n" + e;
			}
		} else {
			try {
				t = session1.getTransport("smtp");
			} catch (NoSuchProviderException e1) {
				exceptionMessage += "\n" + e1;
			}
			try {
				if (t != null) {
					t.connect();// Makes a connection to the service
				}
			} catch (AuthenticationFailedException ae) {
				exceptionMessage += "\n"
						+ "Authentication Failed.\nPlease verify Authentication username and password.";
			} catch (Exception e) {
				exceptionMessage += "\n" + e.getMessage();
			}
		}
		logger.info("-connect--------------------------");

		if (to_mailIds.length > 0) {
			htmlText = messg;// "<html> "
			try {
				InternetAddress addressFrom = null;
				for (int z = 0; z < to_mailIds.length; z++) {
					if (!(String.valueOf(to_mailIds[z]).equals("null"))) {
						// logger.info("11111----*****+++++++++***---->>
						// "+to_mailIds[z]);
						InternetAddress toEmail[] = new InternetAddress[1];
						try {
							toEmail[0] = new InternetAddress(String
									.valueOf(to_mailIds[z]));
							logger.info("TO Employee ----********---->> "
									+ to_mailIds[z]);
							MailUtility.output.write("to_mailIds is-------"
									+ to_mailIds[z]);
							MailUtility.output.println();
							MailUtility.output.println();
						} catch (AddressException e) {
							exceptionMessage += "\n" + "Invalid from address.";
							e.printStackTrace();
						}
						String fromMailId = "";
						if (cn < from_mailIds.length) {

							try {
								fromMailId = from_mailIds[cn];
								addressFrom = new InternetAddress(
										from_mailIds[cn]);

								MailUtility.output
										.write("from_mailIds is-------"
												+ from_mailIds[cn]);
								MailUtility.output.println();
								MailUtility.output.println();
							} catch (AddressException e) {
								exceptionMessage += "\n"
										+ "Invalid from address.";
								e.printStackTrace();
							}
							logger.info("From Employee ----********---->> "
									+ from_mailIds[cn]);
							cn++;
						} else {
							cn = 0;
							try {
								addressFrom = new InternetAddress(
										from_mailIds[cn]);
								fromMailId = from_mailIds[cn];
							} catch (AddressException e) {
								exceptionMessage += "\n"
										+ "Invalid from address.";
							}
							logger.info("From Employee ----********---->> "
									+ from_mailIds[cn]);
							cn++;
						}

						try {
							// session1.setDebug(debug);
							// session1.setDebug(true);
							javax.mail.Message message = new MimeMessage(
									session1);

							MimeMultipart multipart = new MimeMultipart(
									"related");
							// *************88************************************************************************************/
							if (isCalendar) {
								((MimePart) message)
										.addHeaderLine("method=REQUEST");
								((MimePart) message)
										.addHeaderLine("charset=UTF-8");
								((MimePart) message)
										.addHeaderLine("component=VEVENT");
								SimpleDateFormat sd = new SimpleDateFormat(
										"yyyyMMdd");
								System.out.println("....88888."
										+ sd.format(Utility.getDate(fromDate)));
								StringBuffer sb = new StringBuffer();

								StringBuffer buffer = sb
										.append("BEGIN:VCALENDAR\n"
												+ "PRODID:BCP\n"
												+ "VERSION:2.0\n"
												+ "METHOD:REQUEST\n"
												+ "BEGIN:VEVENT\n"
												+ "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:"
												+ to_mailIds[z]
												+ "\n"
												+ "ORGANIZER:MAILTO:"
												+ fromMailId
												+ "\n"
												+ "DTSTART:"
												+ sd.format(Utility
														.getDate(fromDate))
												+ "T000000Z"
												+ "\n"
												+ "DTEND:"
												+ sd.format(Utility
														.getDate(toDate))
												+ "T000000Z"
												+ "\n"
												+ "LOCATION:\n"
												+ "TRANSP:OPAQUE\n"
												+ "SEQUENCE:0\n"
												+ "UID:040000008200E00074C5B7101A82E00800000000002FF466CE3AC5010000000000000000100\n"
												+ " 000004377FE5C37984842BF9440448399EB02\n"
												+ "DTSTAMP:"
												+ sd.format(Utility
														.getDate(fromDate))
												+ "T053000Z"
												+ "\n"
												+ "CATEGORIES:Travel\n"
												+ "DESCRIPTION:"
												+ calMessage
												+ "\n\n"
												+ "SUMMARY:"
												+ calSubject
												+ "\n"
												+ "PRIORITY:5\n"
												+ "CLASS:PUBLIC\n"
												+ "BEGIN:VALARM\n"
												+ "TRIGGER:PT1440M\n"
												+ "ACTION:DISPLAY\n"
												+ "DESCRIPTION:"
												+ calMessage
												+ "\n"
												+ "END:VALARM\n"
												+ "END:VEVENT\n"
												+ "END:VCALENDAR");
								System.out.println("buffer   "
										+ buffer.toString());
								// Create the message part
								BodyPart calendarBodyPart = new MimeBodyPart();

								// Fill the message
								calendarBodyPart.setHeader("Content-Class",
										"urn:content-classes:calendarmessage");
								calendarBodyPart.setHeader("Content-ID",
										"calendar_message");
								calendarBodyPart
										.setDataHandler(new DataHandler(
												new ByteArrayDataSource(buffer
														.toString(),
														"text/calendar")));// very
								// Add part one
								multipart.addBodyPart(calendarBodyPart);
							}
							// *************88************************************************************************************/

							try {
								((MimeMessage) message).setSubject(subject);
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
								e.printStackTrace();
							}
							try {
								message.setFrom(addressFrom);
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
								e.printStackTrace();
							}
							try {
								message.addHeader("To", String
										.valueOf(to_mailIds[z]));
							} catch (Exception e) {
								// TODO: handle exception
							}

							try {

								if (attachPath != null && attachPath.length > 0) {
									System.out.println("attachPath....."
											+ attachPath.length);
									for (int i = 0; i < attachPath.length; i++) {
										String file1 = "";
										System.out.println("attachPath[i]   "
												+ attachPath[i]);
										file1 = String.valueOf(attachPath[i]);
										if (file1 != null && !file1.equals("")
												&& !file1.equals("null")) {
											File fileAttach = null;
											try {
												fileAttach = new File(file1);
											} catch (Exception e) {
												fileAttach = null;
											}

											if (fileAttach != null) {
												logger.info("  file1-------  "
														+ file1);
												DataSource fds1 = new FileDataSource(
														fileAttach);
												BodyPart messageBodyPart2 = new MimeBodyPart();
												messageBodyPart2
														.setDataHandler(new DataHandler(
																fds1));
												  messageBodyPart2.setFileName(fileAttach.getName());
												multipart
														.addBodyPart(messageBodyPart2);
											}
										}
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								messageBodyPart.setContent(htmlText,
										"text/html");
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
								e.printStackTrace();
							}
							try {
								multipart.addBodyPart(messageBodyPart);
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
								e.printStackTrace();
							}
							try {
								((Part) message).setContent(multipart);
							} catch (Exception e) {
								exceptionMessage += "\n" + e.getMessage();
							}
							if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
								try {
									Transport.send(message);
								} catch (MessagingException e1) {
									// TODO Auto-generated catch block
									exceptionMessage += "\n" + e1.getMessage();
									e1.printStackTrace();
								}
								try {
									store.close();
								} catch (MessagingException e1) {
									// TODO Auto-generated catch block
									exceptionMessage += "\n" + e1.getMessage();
									e1.printStackTrace();
								}
								try {
									Thread.sleep(5000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									exceptionMessage += "\n" + e.getMessage();
								}
							} else {
								try {

									if (t.isConnected()) {
										// t.sendMessage(message, toEmail);
										t.send(message);

										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
										}
									}

								} catch (javax.mail.MessagingException se) {

									try {
										Thread.sleep(5);
									} catch (InterruptedException e) {
									}
									
									  try {
										Address[] ads = ((SendFailedException) se)
												.getValidUnsentAddresses();
										if (ads != null) {
											for (int i = 0; i < ads.length; i++) {
												exceptionMessage += "\nInvalid Addresses:"
														+ ads[i];
											}
										}
									} catch (Exception e) {
										// TODO: handle exception
									}
									exceptionMessage += "\n" + se.getCause();
									se.printStackTrace();
								}
							}

						} catch (Exception e) {
							exceptionMessage += "\n" + e.getMessage();
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				exceptionMessage += "\n" + e.getMessage();
			}
		}
		if (exceptionMessage.equals("")) {
			msgTolog = "mail send successfully";
		} else {
			msgTolog += exceptionMessage;
		}

		logger.info("final value of message is-----------" + msgTolog);

		MailUtility.output.write("final value of message is-----------"
				+ msgTolog);
		MailUtility.output.println();
		MailUtility.output.println();

		output.close();

		System.out.println("final value of exceptionMessage is equal======== "
				+ exceptionMessage);

		return exceptionMessage;
	}

	
}