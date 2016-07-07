 <%@ page contentType="text/html; charset=windows-1252" language="java"
	import="java.sql.*,java.util.*" import="javax.mail.Authenticator"
	errorPage="" import="javax.mail.Message.RecipientType"
	import="javax.mail.internet.InternetAddress"
	import="javax.mail.internet.MimeMessage" import="javax.mail.Message"
	import="javax.mail.Session" import="javax.mail.Transport"
	import="javax.mail.Authenticator" import="javax.activation.*"
	import="javax.mail.*" import="javax.mail.internet.*"
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Refresh" CONTENT="0;URL=request.jsp">
</head>

<body>


<%!int i, j, num;
	Connection con;
	Statement st;
	ResultSet rs;	Object[][]  twoDimObjArr=null;
	String email="";
	InternetAddress []addressTo = null;
%>

<%
		try {
		Class.forName("oracle.jdbc.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.206:1521:oradev9",
				"hrlive", "hrlive");
		System.out.println("connection done..");
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
				
		String query = " SELECT TO_CHAR(DOB, 'DD-MONTH' ),(FNAME || ' ' || SNAME || ' ' || LNAME ),hmsteadd.EMAIL  "
											 +" FROM  HMSTCNDT  "
											 +" left join hmsteadd on(hmsteadd.CCODE = HMSTCNDT.CCODE) "
											 +" WHERE TO_CHAR(DOB,'DD-MM') = (TO_CHAR(SYSDATE,'DD-MM')) "
			 								 +" ORDER BY  TO_CHAR(DOB, 'MM') ,TO_CHAR(DOB, 'DD') " ;
											
	
		try{
		
		java.sql.ResultSet rs = st.executeQuery(query);
//		ResultSet rs = new ResultSet
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();
		
		
		try{
			rs.last();
			twoDimObjArr = new Object[rs.getRow()][numberOfColumns];
		} catch (Exception e) {
			System.out.println("exception in 12");
			twoDimObjArr = new Object[0][numberOfColumns];
		}
		rs.beforeFirst();
		/*
		 * Mapping the resultSet
		 */
		int rowNumber = 0;
		
		while (rs.next()) {
			for (int i = 0; i < numberOfColumns; i++) {
				twoDimObjArr[rowNumber][i] = rs.getObject(i + 1);
			}
			rowNumber++;
		}
		
		rs.close();
		st.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		try{
			addressTo = new InternetAddress[twoDimObjArr.length];
			for(int i=0;i<twoDimObjArr.length;i++){
				System.out.println(twoDimObjArr[i][2]);
				email+=twoDimObjArr[i][2]+",";
				addressTo[i] = new InternetAddress(String.valueOf(twoDimObjArr[i][2]));
			}
		
		}catch(Exception e){
			System.out.println("exception in 123");
		}
		
		try {
			String to[] = new String[1];
			to[0] = email;
			String from = "prem1977@gmail.com";
			String subject = "hello";
			String message="";
			
			
			try {

		boolean debug = false;
		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtpout.secureserver.net");
		props.put("mail.smtp.port", "80");
		props.put("mail.smtp.auth", "true");


		Authenticator auth = new Authenticator (){
   public PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication("prem1977@gmail.com", "sachin007");
   }
  };

		Session session1 = Session.getDefaultInstance(props,
				auth);
		//      Session session = Session.getInstance(props);
		session1.setDebug(debug);

		// create a message
		Message msg = new MimeMessage(session1);
		// set the from and to address

		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
		//String.valueOf(request.getParameter("email")));
		msg.setRecipients(RecipientType.TO, addressTo);
		// Setting the Subject and Content Type
		msg.setSubject(subject);

		message = message
				+ "\n\n\n\n THIS IS A SYSTEM GENERATED EMAIL. FOR FURTHER DETAILS PLEASE CONTACT HR MANAGER";
		msg.setContent(message, "text/html");
		Transport.send(msg);

		System.out.println("MAILS SENT SUCCESSFULLY");

		//response = response+"\nMail Send Successfully to Employee";

			} catch (Exception e) {
				System.out.println("EXCEPTION IN 1");
				e.printStackTrace();
			}
		} catch (Exception e) {
							System.out.println("EXCEPTION IN 2");
			e.printStackTrace();
		}

	} catch (Exception e) {
					System.out.println("EXCEPTION IN 3");
					e.printStackTrace();
	}

	finally {
		out.println("IN FINALLY");
						System.out.println("exception in 4");
						
	}
	 
%>

</body>
</html>