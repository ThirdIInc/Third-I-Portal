/**
 * 
 */
package org.paradyne.model.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.common.HomePage;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


/**
 * @author lakkichand modified by : Reeba_Joseph
 * @modified By AA1711 to display HomePage Dashlet DivisionWise
 * 
 */
public class HomePageModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
  public static int count=0;
	/**
	 * Retrieving Corporate Communication List
	 * @param homePage
	 * @return strObj
	 */
	public String[][] getCorpList(HomePage homePage) {
		String query = "SELECT CORPMSG_LINK,CORPMSG_LINKNAME FROM HRMS_SETTINGS_CORPMSG WHERE CORPMSG_FLAG='Y'"
				+ " ORDER BY CORPMSG_LINK_DATE	DESC";
		String[][] strObj = null;
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null) {
			strObj = new String[obj.length][2];
			for (int i = 0; i < obj.length; i++) {
				strObj[i][0] = String.valueOf(obj[i][0]);
				strObj[i][1] = String.valueOf(obj[i][1]);
			}// END of loop
		}// END if
		return strObj;
	}

	/**
	 * Retrieving Corporate Information List 
	 * @param homePage
	 * @return strObj
	 */
	public String[][] getCorpInfoList(HomePage homePage) {
		String query = "SELECT CORPINFO_LINK,CORPINFO_LINKNAME FROM HRMS_SETTINGS_CORPINFO WHERE CORPINFO_FLAG='Y'"
				+ " ORDER BY CORPINFO_LINK_DATE	DESC";
		String[][] strObj = null;
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null) {
			strObj = new String[obj.length][2];

			for (int i = 0; i < obj.length; i++) {
				strObj[i][0] = String.valueOf(obj[i][0]);
				strObj[i][1] = String.valueOf(obj[i][1]);

			}// END of loop
		}// END if
		return strObj;
	}

	/**
	 * Reading from xml. Reading nodes & elements Corporate Info
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public String[][] processCorpInfo(Document document) throws Exception {
		String[][] link = null;

		List fonts = document
				.selectNodes("//HRHOME/COMMUNICATION[@name='CORPORATE INFO']/Link");
		link = new String[fonts.size()][3];
		int count = 0;
		for (Iterator iter = fonts.iterator(); iter.hasNext();) {// loop x
			Element font = (Element) iter.next();

			// logger.info("found font: " + font.getData());
			link[count][1] = font.attributeValue("name");
			link[count][2] = font.attributeValue("value");
			count++;
		}// END of loop x
		return link;
	}

	/**
	 * Retrieving Knowledge Information List
	 * @param homePage
	 * @return strObj
	 */
	public String[][] getKnowInfoList(HomePage homePage) {
		String query = "SELECT KNOW_LINK,KNOW_LINKNAME FROM HRMS_SETTINGS_KNOWLEDGE WHERE KNOW_FLAG='Y' "
				+ " ORDER BY KNOW_DATE DESC";
		String[][] strObj = null;
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null) {
			strObj = new String[obj.length][2];
			for (int i = 0; i < obj.length; i++) {
				strObj[i][0] = String.valueOf(obj[i][0]);
				strObj[i][1] = String.valueOf(obj[i][1]);

			}// END of loop
		}// END if
		return strObj;
	}

	/**
	 * Reading from xml. Reading nodes & elements Knowledge Info 
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public String[][] processKnowInfo(Document document) throws Exception {
		String[][] link = null;

		List fonts = document
				.selectNodes("//HRHOME/COMMUNICATION[@name='KNOWLEDGE']/Link");
		link = new String[fonts.size()][3];
		int count = 0;
		for (Iterator iter = fonts.iterator(); iter.hasNext();) {// loop x
			Element font = (Element) iter.next();
			link[count][1] = font.attributeValue("name");
			link[count][2] = font.attributeValue("value");
			count++;
		}// END of loop x
		return link;
	}

	/**
	 * Reading from xml. Reading nodes & elements HrWorK Communication
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public String[][] processHrWorkComm(Document document) throws Exception {
		String[][] link = null;
		List fonts = document
				.selectNodes("//HRHOME/COMMUNICATION[@name='HRWORQ COMMUNICATION']/Link");
		link = new String[fonts.size()][3];
		int count = 0;
		for (Iterator iter = fonts.iterator(); iter.hasNext();) {// loop x
			Element font = (Element) iter.next();
			link[count][1] = font.attributeValue("name");
			link[count][2] = font.attributeValue("value");
			count++;
		}// END of loop x
		return link;
	}

	/**
	 * Retrieving general information List
	 * @param homePage
	 * @return strObj
	 */
	public String[][] getGenInfoList(HomePage homePage) {
		String query = "SELECT HOLIDAY_LINKPATH,HOLIDAY_LINKNAME FROM HRMS_SETTINGS_GENERAL";
		String[][] strObj = null;
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null) {
			strObj = new String[obj.length][2];

			for (int i = 0; i < obj.length; i++) {
				strObj[i][0] = String.valueOf(obj[i][0]);
				strObj[i][1] = String.valueOf(obj[i][1]);

			}// END of loop
		}// END if
		return strObj;
	}

	/**
	 * Reading from xml. Reading nodes & elements Corporate Communication
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public String[][] processCorpComm(Document document) throws Exception {
		String[][] link = null;
		List fonts = document
				.selectNodes("//HRHOME/COMMUNICATION[@name='CORPORATE COMMUNICATION']/Link");
		link = new String[fonts.size()][3];
		int count = 0;
		for (Iterator iter = fonts.iterator(); iter.hasNext();) {// loop x
			Element font = (Element) iter.next();
			link[count][1] = font.attributeValue("name");
			link[count][2] = font.attributeValue("value");
			count++;
		}// END of loop x
		return link;
	}

	/**
	 * Reading from xml. Reading nodes & elements General Information
	 * 
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public String[][] processGenInfo(Document document) throws Exception {
		String[][] link = null;
		List fonts = document
				.selectNodes("//HRHOME/COMMUNICATION[@name='GENERAL']/Link");
		link = new String[fonts.size()][2];
		int count = 0;
		for (Iterator iter = fonts.iterator(); iter.hasNext();) {// loop x
			Element font = (Element) iter.next();
			link[count][1] = font.attributeValue("name");
			link[count][0] = font.attributeValue("value");
			count++;
		}// END of loop x
		return link;
	}

	/**
	 * Retrieving Quick Link detaILS
	 * @param homePage
	 */
	public void getQuickLink(HomePage homePage) {
		String quickQuery = "SELECT QUICK_ADMIN_LINK	,QUICK_ADMIN_LINKNAME FROM HRMS_SETTINGS_QUICKLINK_ADMIN "
				+ " UNION ALL "
				+ " SELECT QUICK_EMP_LINK,QUICK_EMP_LINKNAME FROM HRMS_SETTINGS_QUICKLINK_EMP";
		Object[][] quickObj = getSqlModel().getSingleResult(quickQuery);
		HashMap hash = new HashMap();
		for (int i = 0; i < quickObj.length; i++) {
			hash.put(i, String.valueOf(quickObj[i][1]));
		}// End of loop
		homePage.setQuickMap(hash);
	}

	/**
	 * retrieving Helpdesk call details
	 * 
	 * @return strObj
	 */
	public String[][] getCalls() {
		String[][] strObj = null;
		String selSql = " SELECT HELPDESK_DEPT.DEPT_CODE,HELPDESK_DEPT.DEPT_NAME,COUNT(*) FROM HELPDESK_CALL "
				+ " INNER JOIN HELPDESK_EMPLOYEE ON(HELPDESK_CALL.EMPLOYEE_CODE=HELPDESK_EMPLOYEE.EMPLOYEE_CODE AND HELPDESK_CALL.CALL_FINAL_STATUS NOT IN 'c') "
				+ " INNER JOIN HELPDESK_DEPT ON(HELPDESK_EMPLOYEE.DEPT_CODE=HELPDESK_DEPT.DEPT_CODE)"
				+ " GROUP BY HELPDESK_DEPT.DEPT_CODE,HELPDESK_DEPT.DEPT_NAME";	
		Object[][] obj = getSqlModel().getSingleResult(selSql);
		strObj = new String[obj.length][3];
		for (int i = 0; i < obj.length; i++) {
			strObj[i][0] = String.valueOf(obj[i][0]);
			strObj[i][1] = String.valueOf(obj[i][1]);
			strObj[i][2] = String.valueOf(obj[i][2]);
		}// End of loop
		return strObj;
	}

	/**
	 * Retrieving HrWorK Communication List
	 * 
	 * @param homePage
	 * @return strObj
	 */
	public String[][] getBulletinList(HomePage homePage) {
		String query = "SELECT BULLETIN_LINK ,BULLETIN_LINKNAME	FROM HRMS_SETTINGS_BULLETIN WHERE BULLETIN_FLAG	='Y' "
				+ " ORDER BY BULLETIN_LINK_DATE	 DESC";
		String[][] strObj = null;
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null) {
			strObj = new String[obj.length][2];

			for (int i = 0; i < obj.length; i++) {
				strObj[i][0] = String.valueOf(obj[i][0]);
				strObj[i][1] = String.valueOf(obj[i][1]);

			}// END of loop
		}// END if

		return strObj;

	}

	/**
	 * Sending Mail via HrWorK
	 * @param home
	 * @param context
	 * @return
	 */
	public String sendMail(HomePage home, ServletContext context) {
		BodyPart messageBodyPart = new MimeBodyPart();
		String msgTo = home.getMsgTo();
		String msgcc = home.getMsgcc();
		String msgBcc = home.getMsgBcc();
		String msgFrom = home.getMsgFrom();
		String msgSubject = home.getMsgSubject();
		String textMsg = home.getMsgText();
		String attach = home.getMsgAttach();
		try {
			String to[] = new String[1];
			to[0] = msgTo;
			String from = msgFrom;
			String subject = msgSubject;

			String message = textMsg;

			LoginBean loginBean = (LoginBean) context.getAttribute("email");
			String serverName = loginBean.getSendHostName();
			int serverPort = Integer.parseInt(loginBean.getSendPortNo());
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
					 * pwd=home.getEmailPwd();
					 */
					return new PasswordAuthentication(
							"prem1977@gmail.com", "glodyne01");
				}
			};
			// String serverPath = context.getRealPath("/");
			Session session1 = Session.getDefaultInstance(props, auth);
			session1.setDebug(debug);
			// create a message
			Message msg = new javax.mail.internet.MimeMessage(session1);
			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(
					"prem1977@gmail.com");
			String file = context.getRealPath("/") + "pages/upload/" + attach
					+ "";
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
			 logger.error("Exception cught Send Mail - HomepAge: " + e);
			return "F";
		}
		return "S";
	}

	/* ***************** POLL SETTINGS ***************** */

	/**
	 * Retrieving Poll List 
	 * @param homePage
	 * @return optionObj Object
	 */
	public Object[][] getPollList(HomePage homePage) {
		String quesQuery = "SELECT POLL_CODE,POLL_QUESTION FROM HRMS_POLL_HDR WHERE POLL_CODE	=(SELECT MAX(POLL_CODE) FROM HRMS_POLL_HDR) "
				+ " ORDER BY POLL_DATE	 DESC";
		Object[][] quesobj = getSqlModel().getSingleResult(quesQuery);
		homePage.setPollCode(String.valueOf(quesobj[0][0]));
		homePage.setQuesName(String.valueOf(quesobj[0][1]));

		String optionQuery = "SELECT POLL_OPTION_CODE	,POLL_OPTION_DESC,POLL_OPTION_COLOR FROM HRMS_POLL_DTL WHERE POLL_CODE="
				+ String.valueOf(quesobj[0][0]) + " ORDER BY POLL_OPTION_CODE";
		Object[][] optionobj = getSqlModel().getSingleResult(optionQuery);
		return optionobj;
	}

	/**Method Name: processPollInfo()
	 * @purpose: Reading from Database according to Divisions 
	 * @param document
	 * @param homepage
	 * @return String - options
	 * @throws Exception
	 */
	public String[][] processPollInfo(HomePage homepage)
			throws Exception {
		String[][] question = new String[1][2];
		String[][] option = null;
		try {			
			 String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ homepage.getUserEmpId();
			Object empDivObj[][] = getSqlModel().getSingleResult(
					empDivQuery);
			 String pollQueQuery=" SELECT POLL_CODE, POLL_QUESTION FROM HRMS_POLL_HDR"		
	 				+" WHERE (','||POLL_DIVISION||',' LIKE '%,"+empDivObj[0][0] 
                    +",%' OR POLL_DIVISION IS NULL)"
	 				+" AND NVL(EXPIRY_DATE,SYSDATE) >= SYSDATE"
	 				+" AND ROWNUM=1"
	 				+" ORDER BY POLL_CODE DESC";
			 Object[][] queObject= getSqlModel().getSingleResult(pollQueQuery);	
			 
			 if (queObject!= null && queObject.length >0) {				
					homepage.setPollCode(String.valueOf(queObject[0][0]));
					homepage.setQuesName(String.valueOf(queObject[0][1]));
				}
			 int count = 0;			
			 String query = " SELECT POLL_OPTION_CODE,POLL_OPTION_DESC,"
						+" POLL_OPTION_COLOR FROM HRMS_POLL_DTL WHERE POLL_CODE= "+homepage.getPollCode();
			 Object[][] obj = getSqlModel().getSingleResult(query);
			 if (obj != null && obj.length > 0) {
				 	homepage.setHmPrevFlag(true);
			 }// end of if
			 int maxIndex = 0;
			 int prevValue = 0;	
			 option = new String[obj.length][5];
			 for (int i = 0; i < obj.length; i++) {	
				 option[i][0] = String.valueOf(obj[i][0]);
				 option[i][1] = String.valueOf(obj[i][1]);
				 option[i][2] = String.valueOf(obj[i][2]);
				 option[i][3] = String.valueOf(obj[i][0]);
				 if (prevValue <= Integer.parseInt(String.valueOf(option[0][0]))) {
					 maxIndex = i;
					 prevValue = Integer.parseInt(String.valueOf(option[0][0]));
				 }
				 count++;
				 option[maxIndex][4] = "Y";
			 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return option;
	}
	
	/**
	 * Writing into XML on Submit
	 * @param document
	 * @param home
	 * @return document - XML
	 */

	public Document writeInXML(Document document, HomePage home) {
		List nodes = document
				.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION");
		// Loop for Question
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			Element node = (Element) iter.next();
			if (node.attributeValue("id").equals(home.getPollCode())) {
				List node1 = document
						.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION/OPTION");

				int count = 0;
				// Loop for Option
				for (Iterator iter1 = node1.iterator(); iter1.hasNext();) {
					Element node2 = (Element) iter1.next();
					if (home.getOptionValue()
							.equals(node2.attributeValue("id"))) {
						int value = Integer.parseInt(node2
								.attributeValue("value"));
						value = value + 1;
						node2.setAttributeValue("value", String.valueOf(value));

					}// end of nested if
					count++;
				}// end of inner loop(options)
			}// end of if
			node.attributeValue("name");
		}// end of outer loop(question)
		return document;
	}

	/**
	 * Retrieving Department List for DepartmentWise Statistics 
	 * @param homePage
	 * @return String - All departments & Percentages
	 */
	public String[][] getDeptPoll(HomePage homePage) {
		// For Setting Poll Code
		String query = "SELECT POLL_CODE,POLL_QUESTION FROM HRMS_POLL_HDR WHERE POLL_CODE	="
				+ homePage.getPollCode() + "" + " ORDER BY POLL_DATE	 DESC";
		Object[][] data = getSqlModel().getSingleResult(query);
		homePage.setPollCode(String.valueOf(data[0][0]));

		// Selecting Departments from whom employees have voted and percentage
		String query2 = "select DEPT_ID,NVL(DEPT_ABBR,DEPT_NAME),COUNT(HRMS_EMP_OFFC.EMP_DEPT)/(SELECT COUNT(*) FROM HRMS_POLL_EMP WHERE POLL_CODE="
				+ String.valueOf(data[0][0])
				+ ") * 100 "
				+ " FROM HRMS_DEPT "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_DEPT= HRMS_DEPT.DEPT_ID)  "
				+ " INNER JOIN HRMS_POLL_EMP ON (HRMS_EMP_OFFC.EMP_ID = HRMS_POLL_EMP.POLL_EMP_ID AND POLL_CODE ="
				+ String.valueOf(data[0][0])
				+ ")  "
				+ " GROUP BY DEPT_ID,DEPT_ABBR,DEPT_NAME ";
		Object[][] data2 = getSqlModel().getSingleResult(query2);
		String[][] deptObj = null;
		if (data2 != null && data2.length > 0) {
			deptObj = new String[data2.length][3];
			for (int i = 0; i < data2.length; i++) {
				deptObj[i][0] = String.valueOf(data2[i][0]);
				deptObj[i][1] = String.valueOf(data2[i][1]);
				deptObj[i][2] = String.valueOf(data2[i][2]);

			}// end of loop
		}// end of if
		return deptObj;
	}

	/**
	 * DISPLAYING POLL STATISTICS
	 * @param homePage
	 * @return percentage values - GenderWise
	 */
	public Object[][] getPollStatistics(HomePage homePage) {
		String query = "SELECT POLL_CODE,POLL_QUESTION FROM HRMS_POLL_HDR WHERE POLL_CODE	="
				+ homePage.getPollCode() + "" + " ORDER BY POLL_DATE	 DESC";
		Object[][] data = getSqlModel().getSingleResult(query);
		// Calculating percentage of polls GenderWise and counting no. of
		// employees of each gender
		String query1 = "SELECT DECODE(HRMS_EMP_OFFC.EMP_GENDER,'M','Male','F','Female','O','Other'), COUNT(HRMS_EMP_OFFC.EMP_GENDER), "
				+ " ((COUNT(HRMS_EMP_OFFC.EMP_GENDER)/(SELECT COUNT(*) FROM HRMS_POLL_EMP WHERE POLL_CODE="
				+ String.valueOf(data[0][0])
				+ ")) * 100) "
				+ " FROM HRMS_POLL_EMP "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_POLL_EMP.POLL_EMP_ID AND HRMS_EMP_OFFC.EMP_GENDER IS NOT NULL) "
				+ " WHERE POLL_CODE="
				+ String.valueOf(data[0][0])
				+ " "
				+ " GROUP BY HRMS_EMP_OFFC.EMP_GENDER ";
		Object[][] data1 = getSqlModel().getSingleResult(query1);

		String[] percentage = new String[3];
		String male = "0";
		String female = "0";
		String other = "0";
		try {
			if (data1 != null) {
				for (int i = 0; i < data1.length; i++) {
					if (String.valueOf(data1[i][0]).equals("M")) {
						// if Males
						male = Utility.twoDecimals(String.valueOf(data1[i][2]));
					}// end of nested if
					else if (String.valueOf(data1[i][0]).equals("F")) {
						// if Females
						female = Utility.twoDecimals(String
								.valueOf(data1[i][2]));
					}// end of else if
					else {
						// if Other
						other = Utility
								.twoDecimals(String.valueOf(data1[i][2]));
					}// end of else
				}// end of loop
			}// end of if
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("Exception Caught in Poll Statistics Model" + e);
		}
		percentage[0] = female;
		percentage[1] = male;
		percentage[2] = other;

		return data1;
	}

	/**
	 * DIPLSYING PREVIOUS POLLS AND ITS STATISTICS
	 */
	public void getPrevousCode() {
		String prevquery = "SELECT * FROM HRMS_POLL_HDR where poll_code<4 order by poll_code desc  ";
		Object prevdata[][] = getSqlModel().getSingleResult(prevquery);

	}

	/**Method Name: getPreviousPoll()
	 * @purpose: Retrieving Division Wise Previous Polls & Next Polls 
	 * @param homePage
	 * @return String - finalObj previous code, next code, no of hits, total
	 *         votes
	 */
	public String[][] getPreviousPoll(HomePage homePage) {

		String[][] finalObj = null;
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ homePage.getUserEmpId();
			Object empDivObj[][] = getSqlModel().getSingleResult(
				empDivQuery);
			String query = " SELECT POLL_CODE, POLL_QUESTION FROM HRMS_POLL_HDR"		
 				+" WHERE (','||POLL_DIVISION||',' LIKE '%,"+empDivObj[0][0] 
 				 +",%' OR POLL_DIVISION IS NULL) AND POLL_CODE";

			if (homePage.getClickType().equals("Pr")) {
				// for previous poll
				query += " <  " + homePage.getPollCode()
						+ " ORDER BY POLL_CODE DESC ";
			} else if (homePage.getClickType().equals("N")) {
				// for next poll
				query += " > " + homePage.getPollCode()
						+ " ORDER BY POLL_CODE ASC ";
			} else if (homePage.getClickType().equals("P")) {
				query += " = " + homePage.getPollCode()
						+ " ORDER BY POLL_CODE ASC ";
			}

			Object data[][] = getSqlModel().getSingleResult(query);

			// finding minimum poll for previous function
			String prevquery = "SELECT MIN(POLL_CODE) FROM HRMS_POLL_HDR  "
				+" WHERE (','||POLL_DIVISION||',' LIKE '%,"+empDivObj[0][0] 
				+",%' OR POLL_DIVISION IS NULL)";
			Object prevdata[][] = getSqlModel().getSingleResult(prevquery);

			// checking if min poll code & current pollcode is equal
			if (String.valueOf(data[0][0]).equals(
					String.valueOf(prevdata[0][0]))) {
				homePage.setPrevFlag(true);
			}// end of if
			// finding maximum poll for next function
			String nextquery = "SELECT MAX(POLL_CODE) FROM HRMS_POLL_HDR  " 
							   +" WHERE (','||POLL_DIVISION||',' LIKE '%,"+empDivObj[0][0] 
					           +",%' OR POLL_DIVISION IS NULL) ";
			Object nextdata[][] = getSqlModel().getSingleResult(nextquery);

			// checking if max poll code & current pollcode is equal
			if (String.valueOf(data[0][0]).equals(
					String.valueOf(nextdata[0][0]))) {
				homePage.setNextFlag(true);
			}// end of if

			homePage.setPrevPollCode(String.valueOf(data[0][0]));
			homePage.setPrevQuesName(String.valueOf(data[0][1]));

			// Finding no. of hits on each option
			String query1 = " SELECT POLL_OPTION_CODE , COUNT(*) FROM HRMS_POLL_EMP "
					+ " WHERE  POLL_CODE = "
					+ String.valueOf(data[0][0])
					+ " GROUP BY POLL_OPTION_CODE  "
					+ " UNION  "
					+ " SELECT POLL_OPTION_CODE ,0  FROM HRMS_POLL_DTL WHERE POLL_OPTION_CODE NOT IN (SELECT DISTINCT POLL_OPTION_CODE FROM HRMS_POLL_EMP WHERE POLL_CODE = "
					+ String.valueOf(data[0][0])
					+ ") "
					+ " AND POLL_CODE = "
					+ String.valueOf(data[0][0]);
			Object data1[][] = getSqlModel().getSingleResult(query1);
			finalObj = new String[data1.length][4];
			int totalVotes = 0;

			for (int i = 0; i < data1.length; i++) {
				String query2 = " SELECT POLL_OPTION_CODE , POLL_OPTION_DESC,POLL_OPTION_COLOR FROM HRMS_POLL_DTL  "
						+ " WHERE POLL_CODE="
						+ String.valueOf(data[0][0])
						+ "  AND POLL_OPTION_CODE="
						+ String.valueOf(data1[i][0]);

				Object data2[][] = getSqlModel().getSingleResult(query2);
				finalObj[i][0] = String.valueOf(data1[i][0]);// option code
				finalObj[i][1] = String.valueOf(data2[0][1]);// option desc
				finalObj[i][2] = String.valueOf(data1[i][1]);// option hits
				finalObj[i][3] = String.valueOf(data2[0][2]);// option color
				// total votes or total no of hits
				totalVotes += Integer.parseInt(String.valueOf(data1[i][1]));
			}  // end of loop	
			homePage.setPollCode(String.valueOf(data[0][0]));
			homePage.setTotalVotes(totalVotes);
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return finalObj;

	}

	/**Method Name:getAwardImages()
	 * @purpose : It Displays Award according to corresponding Division
	 * @Modified By : AA1711 - To display current Financial year 
	 *                Award on Dashlet and to display year and AwardType with filters 
	 * @param request
	 * @param home
	 */
	public void getAwardImages(HttpServletRequest request, HomePage home, int fromYear) {
		try {
			count++;
			String awardPhotoQuery = " SELECT NVL(AWARD_PHOTO,'nophoto') ,NVL(EMP_FNAME,' ') ||' '||NVL(EMP_LNAME,' '), "
					+ " DEPT_NAME ,RANK_NAME,NVL(HRMS_AWARD.AWARD_TYPE,' '), "
					+ " NVL(CENTER_NAME,' '),AWARD_ID  FROM HRMS_AWARD  "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_AWARD .EMP_ID) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT) "
					+ " LEFT JOIN HRMS_AWARD_MASTER ON (HRMS_AWARD_MASTER.AWARD_CODE=HRMS_AWARD.AWARD_TYPE_ID) "
					+ "	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_RANK) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_center.CENTER_ID =HRMS_EMP_OFFC.EMP_CENTER)  "
					+ " WHERE EMP_STATUS='S' AND AWARD_ISACTIVEINDASHLET='Y' AND  "
					+ " AWARD_DATE>= TO_DATE('01-04-"+String.valueOf(fromYear)+"', 'DD-MM-YYYY') AND "
					+ " AWARD_DATE<= TO_DATE('31-03-"+String.valueOf(fromYear+1)+"', 'DD-MM-YYYY')"		
					+ " AND HRMS_EMP_OFFC.EMP_DIV IN(SELECT HRMS_EMP_OFFC.EMP_DIV FROM HRMS_EMP_OFFC"
					+ " WHERE HRMS_EMP_OFFC.EMP_ID = "+home.getUserEmpId()+")"
					+ " ORDER BY AWARD_DATE DESC,AWARD_ID ";
						//DBMS_RANDOM.VALUE
			HashMap awardMap = getSqlModel().getSingleResultMap(awardPhotoQuery,6,2);
			if (awardMap != null && awardMap.size() > 0) {
				Vector vect= new Vector();				
				
				Iterator it = awardMap.entrySet().iterator();
			     while (it.hasNext()) {
			     Map.Entry entry = (Map.Entry) it.next();
			     String key = (String)entry.getKey();
			     Object[][] val = (Object[][])entry.getValue();
			     vect.add(val);                                            
			}				
			     /**To move latest record Randomly*/
			Object[][]awardPhotoQueryObj=getRandomElement(vect);
			if (awardPhotoQueryObj != null && awardPhotoQueryObj.length > 0) {
					for (int i = 0; i < awardPhotoQueryObj.length; i++) {
						for (int j = 0; j < awardPhotoQueryObj.length; j++) {
							System.out.println("awardPhotoQueryObj "
									+ awardPhotoQueryObj[i][j]);
						}
					}
				}
				request.setAttribute("awardPhoto", awardPhotoQueryObj);
			}else{
				if(count <=5){
					getAwardImages(request, home, fromYear-1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Object[][] getRandomElement (Vector v) {
	       Random generator = new Random();
	       int rnd = generator.nextInt(v.size());
	       if(v.size()==1){
	    	   rnd =0;
	       }
	       return (Object[][])v.get(rnd); // Cast the vector value into a String object
	   }

	/**Method Name: ceoConnect()
	 * @purpose Used to display division wise CEO Dashlet
	 * @param request
	 * @param home
	 */
	public void ceoConnect(HttpServletRequest request, HomePage home) {
		try {
			String ceoId =null;
			String queryPhoto = "  SELECT NVL(EMP_PHOTO,'nophoto'),HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC  "
					+ " WHERE EMP_ID=(SELECT HRMS_CEO_CONFIG.EMP_ID FROM HRMS_CEO_CONFIG  "
					+ "  WHERE  ISCEOORMSGADMIN='C' )";
			Object[][] ceophtodata = getSqlModel().getSingleResult(queryPhoto);
			if (ceophtodata != null && ceophtodata.length > 0) {
				String userDivQuery = "SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ home.getUserEmpId();
				Object empDivObj[][] = getSqlModel().getSingleResult(
					userDivQuery);
				String divQuery = " SELECT HRMS_CEO_CONFIG.EMP_ID FROM HRMS_CEO_CONFIG" 
						 		+ " WHERE  HRMS_CEO_CONFIG.EMP_ID="+ ceophtodata[0][1]
						 		+ " AND (','||CEO_DIVISION||',' LIKE '%,"+empDivObj[0][0]+",%' OR CEO_DIVISION IS NULL)";
						
				Object[][] ceoDivObj = getSqlModel().getSingleResult(divQuery);			
				if (ceoDivObj != null && ceoDivObj.length > 0) { //check for null
					for (int i = 0; i < ceoDivObj.length; i++) {
						request.setAttribute("ceoPhoto", ceophtodata);
					}
				}//end if
				ceoId = String.valueOf(ceophtodata[0][1]);
			}
			
			if(home.getUserEmpId().equals(ceoId)){//if CEO and user are same
				home.setCeoFlag("true");
			}//end if CEO and user are same
					/*
					 * if (String.valueOf(ceoDivObj[i][1]).equals("N")) {
					 * request.setAttribute("ceoPhoto", ceophtodata); } String
					 * str[] = String.valueOf(ceoDivObj[i][1]).split( ","); if
					 * (str != null && str.length > 0) { for (int j = 0; j <
					 * str.length; j++) { if (String.valueOf(str[j]).equals(
					 * String.valueOf(empDivObj[0][0]))) {
					 * request.setAttribute("ceoPhoto", ceophtodata); } } } } }
					 */
		
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	/** Method to retrieve messages for CEO Connect- Show Messages link
	 * @author Prajakta.Bhandare
	 * @param request
	 * @param home
	 */
	public void getCeoConnectMsg(HttpServletRequest request, HomePage home){
		try {
			String ceoDivisionQuery="SELECT CEO_DIVISION FROM HRMS_CEO_CONFIG WHERE EMP_ID="+home.getUserEmpId();
			Object[][] ceoDiv=getSqlModel().getSingleResult(ceoDivisionQuery);
			String query="SELECT DTL_CODE,SUBJECT,DESCRIPTION,NVL(TO_CHAR(MESSAGE_DATE,'DD-MM-YYYY'),' ')," 
					+" (HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),SHOW_IDENTITY " 
					+" FROM  HRMS_CEO_MESSAGES " 
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_CEO_MESSAGES.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " 
					+" WHERE STATUS='A'  AND EMP_DIV IN("+ceoDiv[0][0]+") ORDER BY MESSAGE_DATE DESC";
			Object[][] ceoMsgData = getSqlModel().getSingleResult(query);
			if(ceoMsgData!=null && ceoMsgData.length > 0){//if data 
				home.setModeLength("true");
				home.setTotalRecords(String.valueOf(ceoMsgData.length));
				// the total
				// record in
				// the list

				String[] pageIndex = Utility.doPaging(home.getMyPage(),
						ceoMsgData.length, 20);// to display the page number
				if (pageIndex == null) {//if pageIndex null
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}//end if pageIndex null
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2]))); // to set the total number of page
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));// to set the page number
				if (pageIndex[4].equals("1"))
					home.setMyPage("1");
				ArrayList<HomePage> List = new ArrayList<HomePage>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {//for loop to retrieve data
					HomePage bean1 = new HomePage();
					bean1.setCeoMsgCode(checkNull(String.valueOf(ceoMsgData[i][0])));//message code
					bean1.setCeoMsgSub(checkNull(String.valueOf(ceoMsgData[i][1])));//subject
					bean1.setCeoMsgDesc(checkNull(String.valueOf(ceoMsgData[i][2]))); //discription
					bean1.setCeoMsgDate(checkNull(String.valueOf(ceoMsgData[i][3]))); //message data
					if(checkNull(String.valueOf(ceoMsgData[i][5])).equals("Y")){
						bean1.setMsgFromName("Anonymous");
					}//END OF IF
					else{
					bean1.setMsgFromName(checkNull(String.valueOf(ceoMsgData[i][4])));//message From
					}//END OF ELSE
					List.add(bean1);
				}// end for loop to retrieve data
				home.setMsgList(List);// to display the MESSAGE list in the  page
			}//end if data
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**Method Name: getEventData()
	 * @purpose Used to display division wise Events on Dashlet
	 * @param request
	 * @param home
	 */
	public void getEventData(HttpServletRequest request, HomePage home) {
		try {
			
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ home.getUserEmpId();
			Object empDivObj[][] = getSqlModel().getSingleResult(
					empDivQuery);
			String query = " SELECT HRMS_EVENT_MASTER.EVENT_CODE, HRMS_EVENT_MASTER.EVENT_NAME,EVENT_CATEGORY_CODE,EVENT_YEAR," 
			    + " EVENT_LOCATION, HRMS_PORTAL_EVENT.EVENT_NAME"
			    + " FROM HRMS_EVENT_MASTER"   
			    + " LEFT JOIN HRMS_PORTAL_EVENT ON (HRMS_PORTAL_EVENT.EVENT_CODE = HRMS_EVENT_MASTER.EVENT_CATEGORY_CODE)"
				+ " WHERE (','||EVENT_DIVISION||',' LIKE '%,"+empDivObj[0][0]+",%' OR EVENT_DIVISION IS NULL)"					 
			    + " ORDER BY EVENT_YEAR DESC, HRMS_EVENT_MASTER.EVENT_CODE DESC  ";
			Object eventDataObj[][] = getSqlModel().getSingleResult(query);
			int count = 0;

			Object pagingEventDataObj[][] = null;
			if (eventDataObj != null && eventDataObj.length > 0) {//Check for Null
				String[] pageIndexEvent = Utility.doPaging(home.getMyPage(),
						eventDataObj.length, 20);
				if (pageIndexEvent == null) {
					pageIndexEvent[0] = "0";
					pageIndexEvent[1] = "20";
					pageIndexEvent[2] = "1";
					pageIndexEvent[3] = "1";
					pageIndexEvent[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndexEvent[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndexEvent[3])));
				if (pageIndexEvent[4].equals("1"))
					home.setMyPage("1");

				int length = 0;
				try {
					length = Integer.parseInt(pageIndexEvent[1])
							- Integer.parseInt(pageIndexEvent[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (eventDataObj != null && eventDataObj.length > 0) {
					pagingEventDataObj = new Object[eventDataObj.length][6];
					for (int i = 0; i < eventDataObj.length; i++) {
						pagingEventDataObj[i][0] = eventDataObj[i][0]; //Event Code
						pagingEventDataObj[i][1] = eventDataObj[i][1]; //Event Name
						pagingEventDataObj[i][2] = eventDataObj[i][2]; //Event_Category
						pagingEventDataObj[i][3] = eventDataObj[i][3]; //Event_Year
						pagingEventDataObj[i][4] = eventDataObj[i][4]; //Event_Location
						pagingEventDataObj[i][5] = eventDataObj[i][5]; //HRMS_PORTAL_EVENT.EVENT_NAME
					}
				}				
				request.setAttribute("eventDataObj", pagingEventDataObj);
				request.setAttribute("loopStartIndexEvent", Integer
						.parseInt(pageIndexEvent[0]));
			}//End if 
			else {
				request.setAttribute("eventDataObj", eventDataObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method Name: quickDownload()
	 * @purpose Used to display division wise Quick Download on Dashlet
	 * @param request
	 * @param home
	 * @throws Exception
	 */
	public void quickDownload(HttpServletRequest request, HomePage home) throws Exception {
		String[][] downloadList = null;		
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ home.getUserEmpId();
			Object empDivObj[][] = getSqlModel().getSingleResult(
					empDivQuery);
			String query ="  SELECT DISTINCT DOWNLOAD_CATEGORY"
						+ " FROM HRMS_DASHLET_DOWNLOAD" 
						+ " WHERE DOWNLOAD_STATUS='Y'AND (','||DOWNLOAD_DIVISION||',' LIKE '%,"+empDivObj[0][0]
						+ ",%' OR DOWNLOAD_DIVISION IS NULL)"   
						+ " ORDER BY DOWNLOAD_CATEGORY DESC  ";
				Object [][] downloadObj =getSqlModel().getSingleResult(query);
				if (downloadObj != null && downloadObj.length > 0) {//Check for null
				downloadList = new String[downloadObj.length][1];
				for (int i = 0; i < downloadObj.length; i++) {
					downloadList[i][0] = String.valueOf(downloadObj[i][0]);
				}
			}
				request.setAttribute("downloadList", downloadList);						
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**Method Name :getPieChartData()
	 * @purpose : Used to display pie Chart for Opinion Poll
	 * @param home
	 * @param request
	 * @return String
	 */
	public String getPieChartData(HomePage home, HttpServletRequest request) {
		String pieQuery = " SELECT POLL_OPTION_CODE , COUNT(*) FROM HRMS_POLL_EMP"
				+ " WHERE  POLL_CODE = "
				+ home.getPollCode()
				+ " GROUP BY POLL_OPTION_CODE   UNION"
				+ " SELECT POLL_OPTION_CODE ,0  FROM HRMS_POLL_DTL"
				+ " WHERE POLL_OPTION_CODE NOT IN"
				+ " (SELECT DISTINCT POLL_OPTION_CODE FROM HRMS_POLL_EMP"
				+ " WHERE POLL_CODE = "
				+ home.getPollCode()
				+ ")  AND POLL_CODE = "
				+ home.getPollCode()
				+ " ORDER BY POLL_OPTION_CODE";
		Object[][] pieMapPrevObj = getSqlModel().getSingleResultWithCol(
				pieQuery);
		String pieChartsStr = "";
		Object[][] optionNameObj = null;
		Object[][] finaloptionObj = null;
		/**Store Option Code comma seprated*/
		if (pieMapPrevObj != null && pieMapPrevObj.length > 0) {
			String optionCode = "0";
			for (int k = 1; k < pieMapPrevObj.length; k++) {
				if (k == pieMapPrevObj.length + 1) {
					optionCode += String.valueOf(pieMapPrevObj[k][0]);
				} else {
					optionCode += "," + String.valueOf(pieMapPrevObj[k][0]);
				}
			}//end for
			String optionNmQuery = "SELECT POLL_OPTION_DESC,POLL_OPTION_COLOR FROM HRMS_POLL_DTL WHERE POLL_CODE="
					+ home.getPollCode()
					+ " AND POLL_OPTION_CODE IN("
					+ optionCode + ")";
			optionNameObj = getSqlModel().getSingleResult(optionNmQuery);
		}//end if
		if (pieMapPrevObj != null && pieMapPrevObj.length > 0) {
			finaloptionObj = new Object[pieMapPrevObj.length - 1][3];
			for (int k = 0; k < pieMapPrevObj.length - 1; k++) {
				finaloptionObj[k][0] = optionNameObj[k][0];//Poll Description
				finaloptionObj[k][1] = pieMapPrevObj[k + 1][1];//Count for each Option
				finaloptionObj[k][2] = optionNameObj[k][1];// Option Color
			}
		}
		/** It set Data for Pie chart to Display on View Link*/
		if (finaloptionObj != null && finaloptionObj.length > 0) {
			pieChartsStr = "<chart showPercentageValues='1'>";
			for (int j = 0; j < finaloptionObj.length; j++) {
				pieChartsStr += "<set label='" + finaloptionObj[j][0]
						+ "' value='" + finaloptionObj[j][1] + "' color='"
						+ finaloptionObj[j][2] + "' />";
			}//end for
			pieChartsStr += " </chart>";
		}//End if
		String replace = pieChartsStr.replace("null", "");
		return replace;
	}
	
	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	/*public void getDecisionOneMenuList(HttpServletRequest request,
			HomePage homePage, String menuType) {
		try {

			if (menuType == null) {
				menuType = "MN";
			}

			String groupQuery = " select DISTINCT MENU_GROUP,MENU_GROUP_ORDER, MENU_IMAGE from HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   and HRMS_PROFILE_DTL.PROFILE_CODE ='"
					+ homePage.getUserProfileId()
					+ "')"
					+ " WHERE MENU_TYPE='"
					+ menuType
					+ "' AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER ";
			Object groupData[][] = getSqlModel().getSingleResult(groupQuery);

			String query = " SELECT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME, MENU_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ "	OR PROFILE_GENERAL_FLAG ='Y'))"
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN("+homePage.getUserProfileId()+") and MENU_TYPE='" + menuType + "' "
					+ " AND MENU_ISRELEASED='Y' "
					+ " ORDER BY MENU_GROUP_ORDER,MENU_PLACEINMENU";

			
			 * String query = "SELECT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME,
			 * MENU_SERVICE_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP FROM
			 * HRMS_MENU INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE =
			 * HRMS_PROFILE_DTL.MENU_CODE and HRMS_PROFILE_DTL.PROFILE_CODE ='" +
			 * bean.getUserProfileId() + "') WHERE MENU_TYPE='" + menuType + "'
			 * AND MENU_ISRELEASED='Y' ORDER BY MENU_GROUP,MENU_PLACEINMENU";
			 
			Object data[][] = getSqlModel().getSingleResult(query);
			request.setAttribute("serviceMenulst", data);
			request.setAttribute("groupData", groupData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	public void getMenuList(HttpServletRequest request,
			BeanBase bean, String menuType) {
		try {

			if (menuType == null) {
				menuType = "MN";
			}
			String query = " SELECT DISTINCT HRMS_MENU.MENU_CODE, "
				    + " NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), " 
				    + " MENU_LINK,MENU_IMAGE ,MENU_KEYWORDS , MENU_GROUP,MENU_GROUP_ORDER, " 
				    + " HRMS_MENU.MENU_PLACEINMENU FROM HRMS_MENU "
					+ " LEFT JOIN HRMS_PROFILE_DTL " 
					+ " ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE)"
					+ " LEFT JOIN HRMS_MENU_CLIENT ON HRMS_MENU_CLIENT.MENU_CODE=HRMS_MENU.MENU_CODE"
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE in("+bean.getMultipleProfileCode()+") and MENU_TYPE='" + menuType + "' "
					+ " AND MENU_ISRELEASED='Y' AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ "	OR PROFILE_GENERAL_FLAG ='Y')" 
					+ " ORDER BY HRMS_MENU.MENU_PLACEINMENU, MENU_GROUP_ORDER";
			Object data[][] = getSqlModel().getSingleResult(query);
			HashMap map=getMenuInGroups(data);
			Set keySet=null;
			Iterator ittKeyList=null;
			keySet=map.keySet();
			ittKeyList=keySet.iterator();
			while (ittKeyList.hasNext())
			{
				String key=(String)ittKeyList.next();
			    Vector vect=(Vector)map.get(key);
			    for (int i = 0; i < vect.size(); i++)
			    {
			    	Object[] menuObject=(Object[])vect.get(i);
				}
			}
			request.setAttribute("groupMap", map);
			request.setAttribute("serviceMenulst", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap getMenuInGroups(Object[][] menuGroupObject) 
    {
		LinkedHashMap<String, Vector> groupMap= new LinkedHashMap<String, Vector>();
		for (int i = 0; i < menuGroupObject.length; i++) {
			String key= String.valueOf(menuGroupObject[i][5]+"#" +menuGroupObject[i][3]);
			if(groupMap.containsKey(key))
			{
				Vector innerVector=groupMap.get(key);
				innerVector.add(menuGroupObject[i]);
				groupMap.put(key, innerVector);
			}
			else
			{
				Vector innerVector=new Vector();
				innerVector.add(menuGroupObject[i]);
				groupMap.put(key,innerVector);
			}
		}
		return groupMap;
    }
			
} 
