/**
 * 
 */
package org.paradyne.lib;

import java.awt.print.Printable;
import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;


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

import org.dom4j.*;



/**
 * @author Lakkichand
 * @date 28-11-2008
 *
 */
public class SendMailUtility extends ModelBase{

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SendMailUtility.class);
	static int cn = 0;
	/**
	 * Do the settings and sends an email
	**/
	/**
	 * @param to_mailIds1
	 * @param from_mailIds1
	 * @param subject1
	 * @param messg1
	 * @param attachPath1
	**/
	public void sendMail(String[] to_mailIds, String[] from_mailIds, String subject[], String messg[]) {
		final String[] toMailIds = to_mailIds;//{"lakkichand.golegaonkar@glodyne.com"};
		final String[] fromMailIds =from_mailIds;// {"debajani.dash@glodyne.com"};
		final String[] sub =subject;// {"Test"};
		final String[] msg = messg;//{"Hi...."};
		//final String directoryPath = "";
		//final String pooName="";
		ResourceBundle boundle =ResourceBundle.getBundle("globalMessages");
		
		final String directoryPath= boundle.getString("data_path");
		final String pooName=(String)getSession().getAttribute("session_pool");
		
		/**
		 * Creates a separate thread
		**/
		new Thread(new Runnable() {
			public void run() {
				try {
					String htmlText;
					BodyPart messageBodyPart = new MimeBodyPart();
					BodyPart messageBodyPart2 = new MimeBodyPart();
					Session session1 = null;
					boolean debug = false;
					
					/**
					 * Get the server name, protocol, port, user name, password from database
					**/
				/*	String path = ParaActionSupport.getText("data_path") + "\\datafiles\\" + ""
					+ "\\xml\\poll\\poll.xml";*/
				
					
					String path = directoryPath+ "\\datafiles\\" + pooName
					+ "\\xml\\mailbox\\mailbox.xml";
					
					String[] mailBoxData=getMailBoxInfo(
							new XMLReaderWriter().parse(new File(path)));
					//MailModel model = new MailModel();
					//model.initiate(context, session);
					/*Object[][] mailBoxData = model.getMailBoxPara();
					model.terminate();*/					

					final String userName = String.valueOf(mailBoxData[3]);
					final String pass = String.valueOf(mailBoxData[4]);
					logger.info("-----userName-------" + userName);
					logger.info("-----pass-------" + pass);
					
					/**
					 * Make host and port as persistent
					**/
					Properties props = new Properties();
					props.put("mail.smtp.host", String.valueOf(mailBoxData[0]).trim());
					props.put("mail.smtp.port", String.valueOf(mailBoxData[2]).trim());

					if (String.valueOf(mailBoxData[5]).equals("Y")) {//Authentication is true
						props.put("mail.smtp.auth", "true");
						
						/**
						 * Authenticator -: Authenticates the network connection
						 * PasswordAuthentication -: Repository for a user name and a password used by Authenticator.
						**/
						Authenticator auth = new Authenticator() {
							public PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(userName, pass);
							}};
						session1 = Session.getInstance(props, auth);//Creates a mail session
					} //end of if statement
					else {
						session1 = Session.getInstance(props);
					}

					/**
					 * Transport -: An abstract class that models a message transport
					**/
					javax.mail.Transport t = null;
					try {
						t = session1.getTransport("smtp");//Get a Transport object that implements the specified protocol
						try {
							t.connect();//Makes a connection to the service
						} catch (MessagingException e) {
							logger.error(e);
						} //end of try-catch block
					} catch (NoSuchProviderException e1) {
						logger.error(e1);
					} //end of try-catch block

					

					if (toMailIds.length > 0) {
						try {
							
							/**
							 * InternetAddress -: Represents an Internet email address using the syntax of RFC822
							**/
							InternetAddress addressFrom = null;
							for (int z = 0; z < toMailIds.length; z++) {
								if (!(String.valueOf(toMailIds[z]).equals("null"))) {
									htmlText = msg[z];
									InternetAddress toEmail[] = new InternetAddress[1];
									toEmail[0] = new InternetAddress(String.valueOf(toMailIds[z]));
									logger.info("To email :"+String.valueOf(toMailIds[z]));
									if (cn < fromMailIds.length) {
										addressFrom = new InternetAddress(fromMailIds[cn]);
										logger.info("from email :"+fromMailIds[cn]);
										cn++;
									} else {
										cn = 0;
										addressFrom = new InternetAddress(fromMailIds[cn]);
										logger.info("from email :"+fromMailIds[cn]);
										cn++;
									}
									try {
										session1.setDebug(debug);//Set the debug setting for Session. 
										
										/**
										 * Message -: Models an email message
										 * MimeMessage -: Represents a MIME style email message, implements the Message abstract class
										 * MimeMultipart -: Create a new MIME message
										**/
										javax.mail.Message message = new MimeMessage(session1);
										MimeMultipart multipart = new MimeMultipart("related");
										
										((MimeMessage) message).setSubject(sub[z]);
										message.setFrom(addressFrom);
										
										
										
										/**
										 * Creates a body of the mail
										**/
										messageBodyPart.setContent(htmlText, "text/html");
										multipart.addBodyPart(messageBodyPart);
										((Part) message).setContent(multipart);
										
										t.sendMessage(message, toEmail);//Send mail
										logger.info("MAILS SENT SUCCESSFULLY");
									} catch (Exception e) {
										logger.error(e);e.printStackTrace();
									} //end of try-catch block
								} //end of if statement
							} //end of for loop
						} catch (Exception e) {
							logger.error(e);e.printStackTrace();
						} //end of try-catch block
					} //end of if statement
					try {
						t.close();
					} catch (MessagingException e) {
						logger.error(e);e.printStackTrace();
					} //end of try-catch block
				} catch (Exception e) {
					logger.error(e);e.printStackTrace();
				} //end of try-catch block
			}
		}).start();
	}
	

	/**
	 * Reading from xml. Reading nodes & elements General Information
	 * 
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public String[] getMailBoxInfo(Document document) throws Exception {
		String[] mailBoxInfo = new String[6];

		Element element =(Element) document
				.selectSingleNode("//MAILBOX/OUTBOUND/DATA");
		
		mailBoxInfo[0]=element.attributeValue("server");
		mailBoxInfo[1]=element.attributeValue("protocol");
		mailBoxInfo[2]=element.attributeValue("port");
		mailBoxInfo[3]=element.attributeValue("username");
		mailBoxInfo[4]=element.attributeValue("password");
		mailBoxInfo[5]=element.attributeValue("authRequired");
		
		for (int i = 0; i < mailBoxInfo.length; i++) {
			logger.info("mailBox......."+mailBoxInfo[i]);
		}
		/*link = new String[fonts.size()][2];
		int count = 0;

		for (Iterator iter = fonts.iterator(); iter.hasNext();) {// loop x
			Element font = (Element) iter.next();

			logger.info("attribute name----------------------"
					+ font.attributeValue("name"));
			logger.info("attribute value----------------------"
					+ font.attributeValue("value"));
			System.out.println("found font: " + font.getData());
			link[count][1] = font.attributeValue("name");
			link[count][0] = font.attributeValue("value");
			count++;
		}// END of loop x
*/		return mailBoxInfo;
	}
	
}
