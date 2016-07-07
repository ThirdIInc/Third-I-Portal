<%@ page contentType="text/html; charset=windows-1252" language="java"
	import="java.sql.*,java.util.*" import="javax.mail.Authenticator"
	errorPage="" import="javax.mail.Message.RecipientType"
	import="javax.mail.internet.InternetAddress"
	import="javax.mail.internet.MimeMessage" import="javax.mail.Message"
	import="javax.mail.Session" import="javax.mail.Transport"
	import="javax.mail.Authenticator" import="javax.activation.*"
	import="javax.mail.*" import="javax.mail.internet.*"%>

<%!

		String from = "";
		String to = "";
		String subject = "";
		String message = "";
		String attach="";
		BodyPart messageBodyPart = new MimeBodyPart();
		String htmlText;
		
%>

<% 

		from = request.getParameter("msgFrom");
		to = request.getParameter("msgTo");
		subject = request.getParameter("msgSubject");
		message = request.getParameter("msgText");
		attach=request.getParameter("msgAttach");
%>

<% 
try {
		boolean debug = false;
		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host","smtpout.secureserver.net");
		props.put("mail.smtp.port","80");
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new Authenticator() {
			
			public PasswordAuthentication getPasswordAuthentication() {
			
				
				return new PasswordAuthentication(
						"prem1977@gmail.com", "sachin007");
			}
 		};

		String serverPath = config.getServletContext().getRealPath("/");
	
	System.out.print("--------------------------------------------------------------------path="+serverPath);
 		
 		Session session1 = Session.getDefaultInstance(props,auth);
		
		session1.setDebug(debug);
		
		// create a message
		Message msg = new javax.mail.internet.MimeMessage(session1);
		// set the from and to address

		InternetAddress addressFrom = new InternetAddress("prem1977@gmail.com");
	
		
		String file =config.getServletContext().getRealPath("/")+"pages/upload/"+attach+"";
		System.out.println("value file----------------------------------------------------------------------------->>"+file);
		InternetAddress[] addressTo = new InternetAddress[1];
		addressTo[0] = new InternetAddress("prem1977@gmail.com");
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
		messageBodyPart.setHeader("Content-ID","<memememe>");
		
		multipart.addBodyPart(messageBodyPart);

		// Associate multi-part with message
		msg.setContent(multipart);
		Transport.send(msg);	
		
} catch (Exception e) {
	out.println("Problem in sending mails. Please try later.<br><br>The error details : <br>"+e);
}
 		
 		
%>