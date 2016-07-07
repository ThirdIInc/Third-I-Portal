package org.paradyne.model.recruitment;
 
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.Recruitment.PartnerHomePage;
import org.paradyne.bean.common.HomePage;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author lakkichand
 * modified by : Reeba_Joseph
 * 
 */
public class PartnerHomePageModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);  
	 
	 
	/**
	 * Sending Mail via HrWorK
	 * 
	 * @param home
	 * @param context
	 * @return
	 */
	public String sendMail(PartnerHomePage home, ServletContext context) {
		BodyPart messageBodyPart = new MimeBodyPart();
		String msgTo = home.getMsgTo();
		String msgcc = home.getMsgcc();
		String msgBcc = home.getMsgBcc();
		String msgFrom = home.getMsgFrom();
		String msgSubject = home.getMsgSubject();
		String textMsg = home.getMsgText();
		String attach = home.getMsgAttach();
		logger
				.info("value of attach------------------------------------------>>"
						+ attach);
		logger
				.info("value of attach------------------------------------------>>to="
						+ msgTo);
		logger
				.info("value of attach------------------------------------------>>to="
						+ textMsg);

		try {
			String to[] = new String[1];
			to[0] = msgTo;
			String from = msgFrom;
			String subject = msgSubject;

			String message = textMsg;

			LoginBean loginBean = (LoginBean) context.getAttribute("email");
			String serverName = loginBean.getSendHostName();
			int serverPort = Integer.parseInt(loginBean.getSendPortNo());
			logger.info("---------------server Name" + serverName);
			logger.info("---------------server Name" + serverPort);

			boolean debug = false;
			// Set the host smtp address

			Properties props = new Properties();
			props.put("mail.smtp.host", serverName);
			props.put("mail.smtp.port", serverPort);
			props.put("mail.smtp.auth", "true");

			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					/*
					 * HomePage home =new HomePage(); String
					 * userId=home.getEmailUserId(); String
					 * pwd=home.getEmailPwd(); logger.info("---------------user
					 * Id"+userId); logger.info("---------------pwd"+pwd);
					 */
					return new PasswordAuthentication(
							"prem1977@gmail.com", "glodyne01");
				}// End PassWordAuthentication
			};// END Authenticator

			// String serverPath = context.getRealPath("/");

			// System.out.print("--------------------------------------------------------------------path="+serverPath);

			Session session1 = Session.getDefaultInstance(props, auth);

			session1.setDebug(debug);

			// create a message
			Message msg = new javax.mail.internet.MimeMessage(session1);
			// set the from and to address

			InternetAddress addressFrom = new InternetAddress(
					"prem1977@gmail.com");

			String file = context.getRealPath("/") + "pages/upload/" + attach
					+ "";
			logger
					.info("value file----------------------------------------------------------------------------->>"
							+ file);
			InternetAddress[] addressTo = new InternetAddress[1];
			addressTo[0] = new InternetAddress(msgTo);
			msg.setRecipients(RecipientType.TO, addressTo);
			msg.setFrom(addressFrom);
			msg.setSubject(subject);

			messageBodyPart.setContent(message, "text/html");

			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();

			// Fetch the image and associate to part
			DataSource fds = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<memememe>");

			multipart.addBodyPart(messageBodyPart);

			// Associate multi-part with message
			msg.setContent(multipart);
			Transport.send(msg);

			/*
			 * Session session1 = Session.getDefaultInstance(props,auth);
			 * 
			 * session1.setDebug(debug); // create a message Message msg = new
			 * javax.mail.internet.MimeMessage(session1); // set the from and to
			 * address
			 * 
			 * InternetAddress addressFrom = new InternetAddress(from);
			 * msg.setFrom(addressFrom); InternetAddress[] addressTo = new
			 * InternetAddress[1]; addressTo[0] = new InternetAddress(to[0]);
			 * msg.setRecipients(RecipientType.TO, addressTo); // Setting the
			 * Subject and Content Type msg.setSubject(subject);
			 * 
			 * 
			 * msg.setContent(message, "text/html"); Transport.send(msg);
			 * 
			 */

		} catch (Exception e) {
			// out.println("Problem in sending mails. Please try
			// later.<br><br>The error details : <br>"+e);
			logger.error("Exception cught Send Mail - HomepAge: " + e);
			return "F";
		}
		return "S";
	}
	
 

 
 
 

}