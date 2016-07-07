package org.paradyne.lib;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.lib.email.EmailAttachment;
import org.paradyne.lib.email.HtmlEmail;
import org.paradyne.lib.email.MultiPartEmail;
import org.paradyne.model.ApplicationStudio.AutoBirthdayModel;
/**
 * @author Lakkichand_Golegaonkar
 */
public class SendEmail extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(SendEmail.class);
	
	String[][] fromMailId=null;
	int mailCount=0;
	HtmlEmail email=null;
	public void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, HttpServletRequest request,
			Object[][] mailBoxData) {		
			fromMailId=fromMailIds;
			// email. setDebug(true);
			String replacedString = "";
			try {				
				//HtmlEmail email=null;
				 int patch=80;
				 int count=toMailId.length/patch;
				 int mailCount=0;
				 int rem=toMailId.length%patch;
				if(rem>0)
				{ count=count+1;
				}
				int k=0;
				if(patch>toMailId.length){
					patch=toMailId.length;
				}
				logger.info(" COUNT  *** NO OF TIMES MAIN FOR LOPP RUNNUNG    :"+count);
				for (int i = 0; i < count; i++) {
					String[] tomailIds=null;
						if(i==count-1){
						if(rem>0){						
							tomailIds=new String[rem];
						}
						else{
							tomailIds=new String[patch];
						}						
					}
					else{
						tomailIds=new String[patch];
					}					
						logger.info("tomailIds.length                             "+tomailIds.length);
					for (int j = 0; j < tomailIds.length; j++) {
						tomailIds[j]=toMailId[k];
						k++;
					}					
					//HtmlEmail email=setHtmlEmail(mailBoxData, subject, textBody, request, tomailIds);
					fireEmail(mailBoxData, subject, textBody, request, tomailIds);

				}
		} catch (Exception e) {
			logger.info("EXCEPTION 2......");
			e.printStackTrace();
		}
	}

	public void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody[], HttpServletRequest request,
			Object[][] mailBoxData) {		
			fromMailId=fromMailIds;
			// email. setDebug(true);
			String replacedString = "";
			try {				
				//HtmlEmail email=null;
				 int patch=80;
				 int count=toMailId.length/patch;
				 int mailCount=0;
				 int rem=toMailId.length%patch;
				if(rem>0)
				{ count=count+1;
				}
				int k=0;
				if(patch>toMailId.length){
					patch=toMailId.length;
				}
				logger.info(" COUNT  *** NO OF TIMES MAIN FOR LOPP RUNNUNG    :"+count);
				int tempCnt=textBody.length;
				int cnt=0;
				for (int i = 0; i < count; i++) {
					String[] tomailIds=null;
						if(i==count-1){
						if(rem>0){						
							tomailIds=new String[rem];
						}
						else{
							tomailIds=new String[patch];
						}						
					}
					else{
						tomailIds=new String[patch];
					}					
						logger.info("tomailIds.length                             "+tomailIds.length);
					for (int j = 0; j < tomailIds.length; j++) {
						tomailIds[j]=toMailId[k];
						k++;
					}		
					
					String textBodyMss="";
					if(cnt==tempCnt){
					 cnt=0;
					 textBodyMss=String.valueOf(textBody[cnt]);
					 cnt++;
					}
					else{
						textBodyMss=String.valueOf(textBody[cnt]);
						 cnt++;
					}
					fireEmail(mailBoxData, subject, textBodyMss, request, tomailIds);

				}
		} catch (Exception e) {
			logger.info("EXCEPTION 2......");
			e.printStackTrace();
		}
	}

	/** This method is used to trigger mail with attachment without using thread.
	 * @param toMailId
	 * @param fromMailIds
	 * @param subject
	 * @param textBody
	 * @param mailBoxData
	 * @param attachFile
	 */
	public void sendMailAttachment(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody,
			Object[][] mailBoxData,String[] attachFile){
		final String[]  final_toMailId=toMailId;
		final String[][] final_fromMailIds=fromMailIds;
		final String final_subject=subject;
		final String final_textBody=textBody;
		final Object[][] final_mailBoxData=mailBoxData;
		final String[] final_attachFile=attachFile;
		
					
		try {
			HtmlEmail email = new HtmlEmail();			
			email.setHostName(String.valueOf(final_mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(final_mailBoxData[0][1])));// 80
			email.setSubject("" + final_subject);
			String replacedString =final_textBody;// getHtmlText(email, textBody, request);			
			email.setHtmlMsg(replacedString);
			addTo(email, final_toMailId);					
			email.setFrom(final_fromMailIds[0][0], ""
					+ final_fromMailIds[0][0]);
			/*email
					.addTo(fromMailIds[0][0], ""
							+ fromMailIds[0][0]);	*/		
			logger.info("USER NAME    " + String.valueOf(final_mailBoxData[0][3])
					+ "     PASS    " + String.valueOf(final_mailBoxData[0][4]));
			if (String.valueOf(final_mailBoxData[0][12]).equals("D")) {
				email.setAuthentication(String
						.valueOf(final_mailBoxData[0][3]), String
						.valueOf(final_mailBoxData[0][4]));

			} else {
				email.setAuthentication(String
						.valueOf(final_fromMailIds[0][0]), String
						.valueOf(final_fromMailIds[0][1]));

			}
			if(String.valueOf(final_mailBoxData[0][6]).equals("Y"))
			{
				try {
					 	email.setPopBeforeSmtp(true, String
								.valueOf(final_mailBoxData[0][7]), String
								.valueOf(final_mailBoxData[0][3]), String
								.valueOf(final_mailBoxData[0][4]));
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}

			if (String.valueOf(final_mailBoxData[0][2]).equals("SMTPTLS")) {
				email.setSmtpWithTLS(true);
			}
			if (String.valueOf(final_mailBoxData[0][2]).equals("SMTPSSL")) {
				email.setSmtpWithSSL(true);
			}
		 
			if(final_attachFile!=null && final_attachFile.length >0)
			{
				for (int i = 0; i < final_attachFile.length; i++) {
					 EmailAttachment attachment = new EmailAttachment();
					if(final_attachFile[i]!=null && !final_attachFile[i].equals("null")&& !final_attachFile[i].equals("")){
					  attachment.setPath(final_attachFile[i]);
					 // attachment.setURL(new URL("D:\\Files_Upload\\923.properties"));
					  attachment.setDisposition(EmailAttachment.ATTACHMENT);
					  attachment.setName(final_attachFile[i].substring(final_attachFile[i].lastIndexOf("\\")+1,final_attachFile[i].length()));
					  // add the attachment
					  email.attach(attachment);
					}
				}
			}
	
			email.send();
			
			List unsentList=email.getUnsentList();
			if(unsentList.size()>0){
				String[] toObj=new String[unsentList.size()]; 
				for (int i = 0; i < unsentList.size(); i++) {
					logger.info(" unsentList...Valid "+unsentList.get(i));
					String [] splitedStr=unsentList.get(i).toString().split("<");
					toObj[i]=splitedStr[1].substring(0,splitedStr[1].length()-1);
				}
				sendMailAttachment(toObj, final_fromMailIds, final_subject, final_textBody, final_mailBoxData, final_attachFile);
			}
			logger.info("MAIL SENT SUCCESSFULLY ***********");
			
		} catch (Exception e) {
			logger.info("MAIL SENDING ERROR ***********");
			e.printStackTrace();
		}
			
	}
		
	
	
	
	
	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, HttpServletRequest request, String[] tomailIds) {
		
		
		
		try {
			if(mailCount<fromMailId.length){
				/*if(mailCount==0){
					email=setHtmlEmail(mailBoxData, subject, textBody, request, tomailIds);
				}*/
				email=setHtmlEmail(mailBoxData, subject, textBody, request, tomailIds);	
			email.setFrom(fromMailId[mailCount][0], ""
					+ fromMailId[mailCount][0]);
			email.addTo(fromMailId[mailCount][0], ""
					+ fromMailId[mailCount][0]);
			logger.info("USER NAME    " + fromMailId[mailCount][0]
					+ "     PASS    " + fromMailId[mailCount][1]);
				
			if (String.valueOf(mailBoxData[0][12]).equals("D")) {
				email.setAuthentication(String
						.valueOf(mailBoxData[0][3]), String
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
			}
			if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
				email.setSmtpWithSSL(true);
			}
			String str=email.send();	
			logger.info("send Error  :"+str);
			List unsentList=email.getUnsentList();
			if(unsentList.size()>0){
				String[] toObj=new String[unsentList.size()]; 
				for (int i = 0; i < unsentList.size(); i++) {
					logger.info(" unsentList...Valid "+unsentList.get(i));
					String [] splitedStr=unsentList.get(i).toString().split("<");
					toObj[i]=splitedStr[1].substring(0,splitedStr[1].length()-1);
				}
				fireEmail( mailBoxData,subject,
						textBody,request,toObj);
				//addTo_CC(email, toObj);
				//str= email.send();
			}
			//logger.info("MAIL SENT SUCCESSFULLY ***********  ... FOR SYSMAIL ID "+mailCount);
			
			}else{
				System.out
				.println("SYSTEM MAIL IDS OVER....");
			}
			
			/*System.out
					.println("mail send successfully________________________________ 1 mailCount"+mailCount);*/
		} catch (Exception e) {
			/*email=setHtmlEmail(mailBoxData, subject, textBody, request, tomailIds);
			mailCount++;*/
			
			System.out
			.println("EXCEPTION________________________________ ");
			e.printStackTrace();			
			//fireEmail(mailBoxData, subject, textBody, request, tomailIds);
		}
		
	}

	public String getHtmlText(HtmlEmail email, String textBody,
			HttpServletRequest request) {
		textBody = textBody.replace("src=\"", "$");
		StringTokenizer st = new StringTokenizer(textBody, "$");
		int counter = 0;
		int tokens = st.countTokens();
		String replacedString = "";
		String cid = "";
		while (st.hasMoreTokens()) {
			String new_tokens = st.nextToken();
			logger.info("new_tokens        :" + new_tokens);
			System.out
					.println("\n-----------------------------------------------");
			if (counter != 0) {
				int endofURL = new_tokens.indexOf("\"");
				// logger.info("...endofURL.." + endofURL);
				String src = new_tokens.substring(0, endofURL);
				//if (!src.startsWith("http")) {
				src = "http://" + request.getServerName() + ":"
							+ request.getServerPort() + src;
				//}

				try {
					URL url = new URL(src);
					cid = email.embed(url, "Image001"+counter);
				} catch (Exception e) {
					// TODO: handle exception
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

		logger.info("messages    :" + replacedString);

		return replacedString;

	}
	public String getHtmlTextWithBackground(HtmlEmail email, String textBody,
			HttpServletRequest request) {
		textBody = textBody.replace("style=\"background-image: url(", "$");
		StringTokenizer st = new StringTokenizer(textBody, "$");
		int counter = 0;
		int tokens = st.countTokens();
		String replacedString = "";
		String cid = "";
		while (st.hasMoreTokens()) {
			String new_tokens = st.nextToken();
			logger.info("new_tokens        :" + new_tokens);
			System.out
					.println("\n-----------------------------------------------");
			if (counter != 0) {
				int endofURL = new_tokens.indexOf("\"");
				// logger.info("...endofURL.." + endofURL);
				String src = new_tokens.substring(0, endofURL);
				if (!src.startsWith("http")) {
					src = "http://" + request.getServerName() + ":"
							+ request.getServerPort() + src;
				}

				try {
					URL url = new URL(src);
					cid = email.embed(url, "");
				} catch (Exception e) {
					// TODO: handle exception
				}
				replacedString += "style=\"background-image: url(cid:"
						+ cid
						+ "\""
						+ new_tokens.substring(endofURL + 1, new_tokens
								.length());
			} else {
				replacedString += new_tokens;
			}
			counter++;
		}

		logger.info("messages    :" + replacedString);

		return replacedString;

	}

	public HtmlEmail addTo_CC(HtmlEmail email, String[] toObj) {

		try {
			for (int i = 0; i < toObj.length; i++) {
				/*if (i % 2 == 0) {
					email.addTo(toObj[i]);
				} else {
					email.addCc(toObj[i]);
				}*/
				email.addBcc(toObj[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return email;
	}
	public MultiPartEmail addTo(MultiPartEmail email, String[] toObj) {

		try {
			for (int i = 0; i < toObj.length; i++) {
				/*if (i % 2 == 0) {
					email.addTo(toObj[i]);
				} else {
					email.addCc(toObj[i]);
				}*/
				email.addTo(toObj[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return email;
	}
	public HtmlEmail setHtmlEmail(Object[][] mailBoxData, String subject,
			String textBody, HttpServletRequest request, String[] tomailIds) {
		HtmlEmail email = new HtmlEmail();
		String replacedString = "";

		try {
			email.setHostName(String.valueOf(mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(mailBoxData[0][2])));// 80
			email.setSubject("" + subject);
			replacedString = getHtmlText(email, textBody, request);
			//replacedString=getHtmlTextWithBackground(email, replacedString, request);
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
	
	
	
	public void sendMailToKeepInfo(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody,
			Object[][] mailBoxData){
		
		System.out.println("Your email client does not support HTML messages");
	 
		try {
			HtmlEmail email = new HtmlEmail();			
			email.setHostName(String.valueOf(mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(mailBoxData[0][1])));// 80
			email.setSubject("" + subject);
			String replacedString =textBody;// getHtmlText(email, textBody, request);			
			email.setHtmlMsg(replacedString);
			email
					.setTextMsg("Your email client does not support HTML messages");
			addTo_CC(email, toMailId);					
			email.setFrom(fromMailIds[0][0], ""
					+ fromMailIds[0][0]);
			/*email
					.addTo(fromMailIds[0][0], ""
							+ fromMailIds[0][0]);	*/		
			logger.info("USER NAME    " + String.valueOf(mailBoxData[0][3])
					+ "     PASS    " + String.valueOf(mailBoxData[0][4]));
				
			
			if (String.valueOf(mailBoxData[0][12]).equals("D")) {
				email.setAuthentication(String
						.valueOf(mailBoxData[0][3]), String
						.valueOf(mailBoxData[0][4]));

			} 
			
			else {
				email.setAuthentication(String
						.valueOf(fromMailIds[0][0]), String
						.valueOf(fromMailIds[0][1]));

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
			email.send();
			logger.info("MAIL SENT SUCCESSFULLY ***********  ... FOR SYSMAIL ID "+mailCount);
		} catch (Exception e) {
			logger.info("EXCEPTION $$$......");
			e.printStackTrace();
		}
		
	}
	
	
	public void sendMailToAlternateApprover(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody,
			Object[][] mailBoxData){
	 
		try {
			HtmlEmail email = new HtmlEmail();			
			email.setHostName(String.valueOf(mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(mailBoxData[0][1])));// 80
			email.setSubject("" + subject);
			String replacedString =textBody;// getHtmlText(email, textBody, request);			
			email.setHtmlMsg(replacedString);
			email
					.setTextMsg("Your email client does not support HTML messages");
			addTo_CC(email, toMailId);					
			email.setFrom(fromMailIds[0][0], ""
					+ fromMailIds[0][0]);
			/*email
					.addTo(fromMailIds[0][0], ""
							+ fromMailIds[0][0]);	*/		
			logger.info("USER NAME    " + String.valueOf(mailBoxData[0][3])
					+ "     PASS    " + String.valueOf(mailBoxData[0][4]));
			if (String.valueOf(mailBoxData[0][12]).equals("D")) {
				email.setAuthentication(String
						.valueOf(mailBoxData[mailCount][3]), String
						.valueOf(mailBoxData[mailCount][4]));

			} else {
				email.setAuthentication(String
						.valueOf(fromMailIds[0][0]), String
						.valueOf(fromMailIds[0][1]));

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
			}
			if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
				email.setSmtpWithSSL(true);
			}
			email.send();
			logger.info("MAIL SENT SUCCESSFULLY ***********  ... FOR SYSMAIL ID "+mailCount);
		} catch (Exception e) {
			logger.info("EXCEPTION $$$......");
			e.printStackTrace();
		}
		
	}
	
	//UPDATE BY VISHWAMBHAR
	
	public void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody,
			Object[][] mailBoxData,String[] attachFile){
		final String[]  final_toMailId=toMailId;
		final String[][] final_fromMailIds=fromMailIds;
		final String final_subject=subject;
		final String final_textBody=textBody;
		final Object[][] final_mailBoxData=mailBoxData;
		final String[] final_attachFile=attachFile;
		
		new Thread(new Runnable() {
			public void run() {
				
					
		try {
			HtmlEmail email = new HtmlEmail();			
			email.setHostName(String.valueOf(final_mailBoxData[0][0]));// 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(final_mailBoxData[0][1])));// 80
			email.setSubject("" + final_subject);
			String replacedString =final_textBody;// getHtmlText(email, textBody, request);			
			email.setHtmlMsg(replacedString);
			addTo(email, final_toMailId);					
			email.setFrom(final_fromMailIds[0][0], ""
					+ final_fromMailIds[0][0]);
			/*email
					.addTo(fromMailIds[0][0], ""
							+ fromMailIds[0][0]);	*/		
			logger.info("USER NAME    " + String.valueOf(final_mailBoxData[0][3])
					+ "     PASS    " + String.valueOf(final_mailBoxData[0][4]));
			if (String.valueOf(final_mailBoxData[0][12]).equals("D")) {
				email.setAuthentication(String
						.valueOf(final_mailBoxData[0][3]), String
						.valueOf(final_mailBoxData[0][4]));

			} else {
				email.setAuthentication(String
						.valueOf(final_fromMailIds[0][0]), String
						.valueOf(final_fromMailIds[0][1]));

			}
			if(String.valueOf(final_mailBoxData[0][6]).equals("Y"))
			{
				try {
					 	email.setPopBeforeSmtp(true, String
								.valueOf(final_mailBoxData[0][7]), String
								.valueOf(final_mailBoxData[0][3]), String
								.valueOf(final_mailBoxData[0][4]));
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}

			if (String.valueOf(final_mailBoxData[0][2]).equals("SMTPTLS")) {
				email.setSmtpWithTLS(true);
			}
			if (String.valueOf(final_mailBoxData[0][2]).equals("SMTPSSL")) {
				email.setSmtpWithSSL(true);
			}
		 
			if(final_attachFile!=null && final_attachFile.length >0)
			{
				for (int i = 0; i < final_attachFile.length; i++) {
					 EmailAttachment attachment = new EmailAttachment();
					if(final_attachFile[i]!=null && !final_attachFile[i].equals("null")&& !final_attachFile[i].equals("")){
					  attachment.setPath(final_attachFile[i]);
					 // attachment.setURL(new URL("D:\\Files_Upload\\923.properties"));
					  attachment.setDisposition(EmailAttachment.ATTACHMENT);
					  attachment.setName(final_attachFile[i].substring(final_attachFile[i].lastIndexOf("\\")+1,final_attachFile[i].length()));
					  // add the attachment
					  email.attach(attachment);
					}
				}
			}
	
			email.send();
			
			List unsentList=email.getUnsentList();
			if(unsentList.size()>0){
				String[] toObj=new String[unsentList.size()]; 
				for (int i = 0; i < unsentList.size(); i++) {
					logger.info(" unsentList...Valid "+unsentList.get(i));
					String [] splitedStr=unsentList.get(i).toString().split("<");
					toObj[i]=splitedStr[1].substring(0,splitedStr[1].length()-1);
				}
					sendMail(toObj, final_fromMailIds, final_subject, final_textBody, final_mailBoxData, final_attachFile);
			}
			logger.info("MAIL SENT SUCCESSFULLY ***********");
			
		} catch (Exception e) {
			logger.info("MAIL SENDING ERROR ***********");
			e.printStackTrace();
		}
			}}).start();
		
	}
	
	 
}
