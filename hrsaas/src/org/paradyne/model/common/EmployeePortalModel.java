package org.paradyne.model.common;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.common.EmployeePortal;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.admin.srd.SendMailModel;

import com.businessobjects.reports.crprompting.CRPromptValue.Str;
import com.itextpdf.text.log.SysoLogger;

/**
 * @Modified by Priyanka. Kumbhar
 * 
 */
public class EmployeePortalModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeePortalModel.class);

	public void getEmployeeInfo(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			Object[][] empInfoObj = null;
			String loginString = " SELECT EMP_TOKEN ,HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||' '||EMP_LNAME,EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_PHOTO,'')  FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ " WHERE EMP_ID ='" + employeePortal.getUserEmpId() + "'";
			empInfoObj = getSqlModel().getSingleResult(loginString);
			if (empInfoObj != null && empInfoObj.length > 0) {
				request
						.setAttribute("UserID", String
								.valueOf(empInfoObj[0][0]));
				request.setAttribute("UserName", String
						.valueOf(empInfoObj[0][1]));
				request.setAttribute("login_EmpId", employeePortal
						.getUserEmpId());

				String photo = String.valueOf(empInfoObj[0][2]);
				if (String.valueOf(empInfoObj[0][1]) != null
						&& !photo.equals("")) {
					request.setAttribute("profilePhoto", String
							.valueOf(empInfoObj[0][3]));
				} else {
					request.setAttribute("profilePhoto", "empimage.gif");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method Name: getHRComm()
	 * 
	 * @purpose Used to display division wise Corporate list on More
	 * @param request
	 * @param empCode
	 */
	public void getHRComm(HttpServletRequest request, String empCode) {

		String[][] corpList = null;
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empCode;
			Object empDivObj[][] = getSqlModel().getSingleResult(empDivQuery);
			String query = "  SELECT CORPMSG_LINKNAME,CORPMSG_LINK FROM HRMS_SETTINGS_CORPMSG"
					+ " WHERE CORPMSG_FLAG='Y' AND (','||CORPMSG_DIVISION||',' LIKE '%,"
					+ empDivObj[0][0]
					+ ",%' OR CORPMSG_DIVISION IS NULL)"
					+ " ORDER BY CORPMSG_CODE DESC";

			Object accessDivObj[][] = getSqlModel().getSingleResult(query);
			if (accessDivObj != null && accessDivObj.length > 0) {// Check for
				// Null
				corpList = new String[accessDivObj.length][2];
				for (int i = 0; i < accessDivObj.length; i++) {
					corpList[i][0] = String.valueOf(accessDivObj[i][0]);// Link
					// Name
					corpList[i][1] = String.valueOf(accessDivObj[i][1]);// Link
				}// end for
			}// end if
			request.setAttribute("corpList", corpList);
		} catch (Exception e) {
			logger.error("Exception Caught - Corp Communication: " + e);
			// corpList = new String[0][0];
			e.printStackTrace();
		}
	}

	public String[][] processGenInfo(Document document) {
		String[][] link = null;
		try {

			List fonts = document
					.selectNodes("//HRHOME/COMMUNICATION[@name='GENERAL']/Link");
			link = new String[fonts.size()][2];
			int count = 0;
			for (Iterator iter = fonts.iterator(); iter.hasNext();) {// loop
				// x
				Element font = (Element) iter.next();
				link[count][1] = font.attributeValue("name");
				link[count][0] = font.attributeValue("value");
				count++;
			}// END of loop x
		} catch (Exception e) {
			e.printStackTrace();
		}
		return link;
	}

	public String[][] processPollInfo(Document document,
			EmployeePortal employeePortal) throws Exception {

		String[][] question = new String[1][2];
		String[][] option = null;
		try {
			// Setting Poll Code and Poll Question from XML
			List nodes = document
					.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION");
			question = new String[nodes.size()][3];

			for (Iterator iter = nodes.iterator(); iter.hasNext();) {
				Element node = (Element) iter.next();
				question[0][1] = node.attributeValue("id");
				question[0][2] = node.attributeValue("name");
			}// end of loop

			employeePortal.setPollCode(question[0][1]);
			employeePortal.setQuesName(question[0][2]);

			// Setting Options for the corresponding Poll
			List node1 = document
					.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION/OPTION");
			option = new String[node1.size()][5];
			int count = 0;
			// logger.info("Poll code----------"
			// + homepage.getPollCode());
			String query = " SELECT POLL_CODE, POLL_QUESTION FROM HRMS_POLL_HDR WHERE POLL_CODE<"
					+ employeePortal.getPollCode()
					+ " ORDER BY POLL_CODE DESC ";
			Object[][] obj = getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				// employeePortal.setHmPrevFlag(true);
			}// end of if
			int maxIndex = 0;
			int prevValue = 0;
			for (Iterator iter = node1.iterator(); iter.hasNext();) {

				Element node2 = (Element) iter.next();
				option[count][0] = node2.attributeValue("id");
				option[count][1] = node2.attributeValue("name");
				// logger.info("option[count][1]======>"+option[count][1]);
				option[count][2] = node2.attributeValue("value");
				// logger.info("option[count][2]======>"+option[count][2]);
				option[count][3] = node2.attributeValue("color");
				option[count][4] = "N";
				if (prevValue <= Integer.parseInt(String
						.valueOf(option[count][2]))) {
					maxIndex = count;
					prevValue = Integer.parseInt(String
							.valueOf(option[count][2]));
				}
				count++;
			}// end of loop
			option[maxIndex][4] = "Y";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return option;
	}

	/**
	 * Method Name: getKnowInfo()
	 * 
	 * @purpose Used to display Knowledge Information Division wise on More
	 * @param request
	 * @param employeePortal
	 * @throws Exception
	 */
	public void getKnowInfo(HttpServletRequest request,
			EmployeePortal employeePortal) throws Exception {
		String[][] knowList = null;
		String knowCatName = request.getParameter("knowCategory");
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ employeePortal.getUserEmpId();

			Object empDivObj[][] = getSqlModel().getSingleResult(empDivQuery);
			String query = " SELECT DISTINCT KNOW_CATEGORY FROM HRMS_SETTINGS_KNOWLEDGE"
					+ " WHERE KNOW_FLAG='Y'"
					+ " AND (','||KNOW_DIVISION||',' LIKE '%,"
					+ empDivObj[0][0]
					+ ",%' OR KNOW_DIVISION IS NULL)"
					+ " ORDER BY KNOW_CATEGORY";

			Object accessDivObj[][] = getSqlModel().getSingleResult(query);

			if (accessDivObj != null && accessDivObj.length > 0) { // Check for
				// Null
				knowList = new String[accessDivObj.length][2];
				for (int i = 0; i < accessDivObj.length; i++) {
					knowList[i][0] = String.valueOf(accessDivObj[i][0]);// Category
					// Name
				}// end for
			}// end if
			request.setAttribute("knowList", knowList);
		} catch (Exception e) {
			knowList = new String[0][0];
			logger.error("Exception Caught - Knowledge Info: " + e);
			e.printStackTrace();
		}
	}

	/**
	 * Method Name: getCorpInfo()
	 * 
	 * @purpose Used to display division wise Corporate Message on More
	 * @param request
	 * @param employeePortal
	 * @throws Exception
	 */
	public void getCorpInfo(HttpServletRequest request,
			EmployeePortal employeePortal) throws Exception {
		String[][] corpInfoList = null;
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ employeePortal.getUserEmpId();

			Object empDivObj[][] = getSqlModel().getSingleResult(empDivQuery);
			String query = "  SELECT CORPINFO_LINKNAME,CORPINFO_LINK FROM HRMS_SETTINGS_CORPINFO WHERE CORPINFO_FLAG='Y'"
					+ " AND (','||CORPINFO_DIVISION||',' LIKE '%,"
					+ empDivObj[0][0]
					+ ",%' OR CORPINFO_DIVISION IS NULL)"
					+ " ORDER BY CORPINFO_CODE DESC";
			Object accessDivObj[][] = getSqlModel().getSingleResult(query);
			if (accessDivObj != null && accessDivObj.length > 0) {// Check for
				// null
				corpInfoList = new String[accessDivObj.length][2];
				for (int i = 0; i < accessDivObj.length; i++) {
					corpInfoList[i][0] = String.valueOf(accessDivObj[i][0]);// Link
					// Name
					corpInfoList[i][1] = String.valueOf(accessDivObj[i][1]);// Link
				}// end for
			}// end if
			request.setAttribute("corpInfoList", corpInfoList);
		} catch (Exception e) {
			corpInfoList = new String[0][0];
			logger.error("Exception Caught - Corporate Info: " + e);
		}
	}

	public void savePoll(HttpServletRequest request,
			HttpServletResponse response, String path,
			EmployeePortal employeePortal) {
		String pollCode = "";
		String optionCode = "";
		try {
			pollCode = request.getParameter("pollCode");
			optionCode = request.getParameter("optionCode");
			logger.info("PollCode          :"
					+ request.getParameter("pollCode"));
			logger.info("OptionCode          :"
					+ request.getParameter("optionCode"));
			employeePortal.setPollCode(pollCode);
			employeePortal.setOptionValue(optionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("home.getPollCode()===" + employeePortal.getPollCode());
		String expiryQuery = "SELECT TO_CHAR(EXPIRY_DATE,'DD-MM-YYYY') FROM HRMS_POLL_HDR WHERE TO_DATE(SYSDATE,'dd-mm-yyyy') > TO_DATE(EXPIRY_DATE,'dd-mm-yyyy') "
				+ " AND POLL_CODE= " + employeePortal.getPollCode();
		Object expiryData[][] = getSqlModel().getSingleResult(expiryQuery);

		if (expiryData != null && expiryData.length > 0) {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			try {
				response.getWriter().write("<message>expiry</message>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String pollCheck = "SELECT * FROM HRMS_POLL_EMP WHERE POLL_EMP_ID ="
					+ employeePortal.getUserEmpId()
					+ " AND "
					+ "POLL_CODE="
					+ employeePortal.getPollCode() + " ";
			Object pollData[][] = getSqlModel().getSingleResult(pollCheck);
			if (!(pollData.length > 0)) {
				String pollQuery = " INSERT INTO HRMS_POLL_EMP(POLL_EMP_ID,POLL_CODE,POLL_DATE,POLL_OPTION_CODE) "
						+ " VALUES ("
						+ employeePortal.getUserEmpId()
						+ ","
						+ employeePortal.getPollCode()
						+ ",to_date(sysdate,'dd-mm-yyyy'),"
						+ employeePortal.getOptionValue() + ")  ";

				getSqlModel().singleExecute(pollQuery);
				Document doc = null;
				try {
					doc = writeInXML(new XMLReaderWriter() // Read from XML on
							// load
							.parse(new File(path)), employeePortal);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					new XMLReaderWriter().write(doc, path); // Write into XML on
					// submit
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				try {
					response.getWriter().write("<message>valid</message>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {

				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				try {
					response.getWriter().write("<message>invalid</message>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Document writeInXML(Document document, EmployeePortal employeePortal) {
		List nodes = document
				.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION");
		// Loop for Question
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			Element node = (Element) iter.next();
			if (node.attributeValue("id").equals(employeePortal.getPollCode())) {
				List node1 = document
						.selectNodes("//HRHOME/POLL[@name='OPINION POLL']/QUESTION/OPTION");

				int count = 0;
				// Loop for Option
				for (Iterator iter1 = node1.iterator(); iter1.hasNext();) {
					Element node2 = (Element) iter1.next();
					if (employeePortal.getOptionValue().equals(
							node2.attributeValue("id"))) {
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

	public boolean submitCeoDesc(String description, String subject,
			String hideIdentity, String loginEmpId) {
		boolean result = false;
		try {
			String identity = "N";
			Object insertObj[][] = new Object[1][4];
			insertObj[0][0] = loginEmpId.trim();
			insertObj[0][1] = subject.trim();
			insertObj[0][2] = description.trim();
			insertObj[0][3] = hideIdentity;

			String inserQuery = " INSERT INTO HRMS_CEO_MESSAGES(DTL_CODE ,EMP_ID, SUBJECT, DESCRIPTION, SHOW_IDENTITY, MESSAGE_DATE) "
					+ " VALUES((select nvl(max(DTL_CODE),0)+1 from HRMS_CEO_MESSAGES),?,?,?,?,sysdate)";
			result = getSqlModel().singleExecute(inserQuery, insertObj);
			if (result) {
				sendCeoMail(subject, description, hideIdentity, loginEmpId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String sendCeoMail(String subject, String message,
			String hideIdentity, String loginEmpId) {
		String result = "";
		try {
			String query = " SELECT HRMS_CEO_CONFIG.EMP_ID ,ADD_EMAIL ,emp_FNAME||' '||emp_lname FROM HRMS_CEO_CONFIG "
					+ " inner join hrms_emp_offc on(hrms_emp_offc.emp_id=HRMS_CEO_CONFIG.emp_id) "
					+ " INNER JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_CEO_CONFIG.EMP_ID)"
					+ " WHERE ISCEOORMSGADMIN='M'" + " AND ADD_TYPE='P'";
			Object dataObj[][] = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					String[] to_Emailid = new String[1];
					to_Emailid[0] = String.valueOf(dataObj[i][1]);
					String[] sub = { "Message to CEO" };
					String[] msg = new String[1];
					String name = String.valueOf(dataObj[i][2]);
					String empName = "";
					System.out.println("hideIdentity " + hideIdentity);
					if (hideIdentity.equals("Y")) {
						empName = "Annonymous";
					} else {
						String empNamequery = " select emp_FNAME||' '||emp_lname from hrms_emp_offc "
								+ " where emp_id=" + loginEmpId;
						Object empNamequeryObj[][] = getSqlModel()
								.getSingleResult(empNamequery);
						if (empNamequeryObj != null
								&& empNamequeryObj.length > 0) {
							empName = String.valueOf(empNamequeryObj[0][0]);
						}
					}
					msg[0] = getMessage(subject, message, name, empName);
					MailUtility mod = new MailUtility();
					mod.initiate(context, session);

					mod.sendMail(to_Emailid, mod.getDefaultMailIds(), sub[0],
							msg[0], "", "", true);
					mod.terminate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getMessage(String subject, String msg, String name,
			String empName) {
		String[] htmlText = new String[1];
		String companyName = "";
		String commpanyQuery = "SELECT COMPANY_LOGO,NVL(COMPANY_DISPLAY_NAME,COMPANY_NAME),COMPANY_NAME FROM  HRMS_COMPANY";
		Object data[][] = getSqlModel().getSingleResult(commpanyQuery);
		if (data != null && data.length > 0) {
			companyName = String.valueOf(data[0][1]);
		}
		String tempMsg = "";
		htmlText[0] = "<html> "
				+ "<style>"
				+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
				+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
				+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
				+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
				+ ".style15 {color: #CC0000} "
				+ ".style16 {color: #D23A49} "
				+ "</style>"
				+ "	<body> "
				+ "<table width='60%' border='0' cellpadding='0' cellspacing='0'>"
				+ "<tr> "
				+ "<td width='66%'>"
				+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
				+ "<tr> " + "<td><p>Dear&nbsp;<b>" + ""
				+ name
				+ ""
				+ "</b>, </p><br /> "
				+ "A message to connect CEO has been forwarded to you.<br/> "
				+ "Message sent by :"
				+ empName
				+ ".</p> "
				+ "<br/> Subject: "
				+ subject
				+ "<br/> Message: "
				+ msg
				+ "<br/><p>Thank you for using "
				+ companyName
				+ " Employee Portal</p><br />"
				+ "</td> "
				+ "</tr> "
				+ "</table> " + "</td> " + "</tr> "

				+ "</table> " + "</body> " + "</html> ";
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		tempMsg = model.getMassMessage(htmlText[0]);

		return tempMsg;

	}

	public void getImageGallery(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			String imageQuery = " select EVENT_IMGPATH from  HRMS_EVENT_MASTER_DTL WHERE EVENT_UPLOAD_CATEGORY ='P'  ";
			Object imageObj[][] = getSqlModel().getSingleResult(imageQuery);

			if (imageObj != null && imageObj.length > 0) {
				request.setAttribute("imgGalleryObj", imageObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAwardImages(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			/*
			 * " SELECT AWARD_PHOTO ,NVL(AWARD_NAME,' '),NVL(EMP_FNAME,' ') " + "
			 * FROM HRMS_AWARD " + " INNER JOIN HRMS_EMP_OFFC ON
			 * (HRMS_EMP_OFFC.EMP_ID = HRMS_AWARD .EMP_ID)" + " WHERE
			 * AWARD_PHOTO IS NOT NULL AND ROWNUM<=10 " + " ORDER BY
			 * DBMS_RANDOM.VALUE ";
			 */
			String awardPhotoQuery = " SELECT NVL(AWARD_PHOTO,'nophoto') ,NVL(EMP_FNAME,' ') ||' '||NVL(EMP_LNAME,' ') "
					+ ",NVL(DEPT_NAME,' ') ,NVL(RANK_NAME,' '),NVL(HRMS_AWARD.AWARD_TYPE,' ') "
					+ " ,NVL(CENTER_NAME,' ')  FROM HRMS_AWARD  "
					+ "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_AWARD .EMP_ID) "
					+ "  INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT) "
					+ "   LEFT JOIN HRMS_AWARD_MASTER ON (HRMS_AWARD_MASTER.AWARD_CODE=HRMS_AWARD.AWARD_TYPE_ID) "
					+ "	   INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_RANK) "
					+ " INNER JOIN HRMS_center ON (HRMS_center.CENTER_ID =HRMS_EMP_OFFC.EMP_center)  "
					+ " WHERE ROWNUM<=10 AND EMP_STATUS='S' AND AWARD_ISACTIVEINDASHLET='Y' ORDER BY DBMS_RANDOM.VALUE ";

			Object awardPhotoQueryObj[][] = getSqlModel().getSingleResult(
					awardPhotoQuery);
			if (awardPhotoQueryObj != null && awardPhotoQueryObj.length > 0) {
				request.setAttribute("awardPhoto", awardPhotoQueryObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setThoughtOfDay(HttpServletRequest request,
			EmployeePortal employeePortal, String path) {
		try {
			String[][] thoughtObj;
			String thought = "";
			Random random = new Random();
			thoughtObj = processThought(new XMLReaderWriter().parse(new File(
					path)));
			String[] thouthId = new String[thoughtObj.length];
			for (int i = 0; i < thouthId.length; i++) {
				thouthId[i] = thoughtObj[i][1];
			}
			int randomValue = Integer.parseInt(String.valueOf(thoughtObj[random
					.nextInt(thouthId.length)][1]));
			for (int k = 0; k < thoughtObj.length; k++) {
				if (randomValue == Integer.parseInt(String
						.valueOf(thoughtObj[k][1]))) {
					logger.info("Thought of the Day is " + thoughtObj[k][2]);
					thought = String.valueOf(thoughtObj[k][2]);
				}
			}
			request.setAttribute("thought", thought);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] processThought(Document document) throws Exception {
		String[][] link = null;

		List fonts = document
				.selectNodes("//HRHOME/COMMUNICATION[@name='THOUGHT']/Link");
		link = new String[fonts.size()][3];
		int count = 0;
		for (Iterator iter = fonts.iterator(); iter.hasNext();) {
			Element font = (Element) iter.next();
			link[count][1] = font.attributeValue("id");
			link[count][2] = font.attributeValue("name");
			count++;
		}
		return link;
	}

	public String getQuickLinks(HttpServletRequest request,
			EmployeePortal empPortalBeanInstance, String path) throws Exception {
		logger.info("in createMenu." + request.getContextPath());
		String mainMenu = empPortalBeanInstance.getHdMenuCode();
		logger.info("Main menu Code " + mainMenu);
		if (mainMenu.equals(""))
			mainMenu = request.getParameter("homeCode");
		try {
			if (mainMenu.equals(""))
				mainMenu = request.getParameter("menuCode");
		} catch (Exception e) {
			logger.error("Null Menu Code");
		}
		String contextPath = "";
		try {

			String[][] quickLinkObj;
			try {
				quickLinkObj = processQuickLink(new XMLReaderWriter()
						.parse(new File(path)));
			} catch (Exception e) {
				quickLinkObj = new String[0][0];
			}
			request.setAttribute("quickLinkObj", quickLinkObj);
			contextPath = request.getContextPath();
			String query = "SELECT HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_PARENT_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
					+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
					+ contextPath
					+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
					+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,HRMS_MENU.MENU_PLACEINMENU "
					+ " FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE "

					+ "  and HRMS_PROFILE_DTL.PROFILE_CODE ='"
					+ empPortalBeanInstance.getUserProfileId()
					+ "' AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ " OR PROFILE_GENERAL_FLAG ='Y')) "
					+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
					+ " WHERE MENU_ISRELEASED='Y'"
					// +" AND (HRMS_MENU.MENU_TYPE !='ES' OR HRMS_MENU.MENU_TYPE
					// IS NULL )"
					+ " START WITH HRMS_MENU.MENU_CODE = "
					+ mainMenu
					+ " CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE "
					+ " ORDER BY HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_CODE ";
			String[][] twoDimObjArr = getMenuData(query);
			request.setAttribute("twoDimObjArr", twoDimObjArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String[][] processQuickLink(Document document) throws Exception {
		String[][] link = null;

		List fonts = document
				.selectNodes("//HRHOME/COMMUNICATION[@name='QUICK']/Link");
		link = new String[fonts.size()][2];
		int count = 0;
		for (Iterator iter = fonts.iterator(); iter.hasNext();) {
			Element font = (Element) iter.next();
			link[count][0] = font.attributeValue("name");
			link[count][1] = font.attributeValue("value");
			count++;
		}
		return link;
	}

	public String[][] getMenuData(String query) {
		Object[][] obj = getSqlModel().getSingleResult(query);
		String[][] strObj;
		if (obj.length > 0) {
			strObj = new String[obj.length][obj[0].length];
			for (int i = 0; i < strObj.length; i++) {
				for (int j = 0; j < strObj[0].length; j++) {
					strObj[i][j] = String.valueOf(obj[i][j]);
				}
			}
		} else {
			strObj = new String[0][0];
		}
		return strObj;
	}

	public Object[][] getEmployeeDirectoryConfiguration() {
		Object[][] data;
		data = null;
		try {
			String query = "SELECT HRMS_EMP_DIRETORY_CONF.EMP_BDAY, HRMS_EMP_DIRETORY_CONF.EMP_BLOODGROUP, HRMS_EMP_DIRETORY_CONF.EMP_JOINING_DATE, HRMS_EMP_DIRETORY_CONF.EMP_DEPTARTMENT, HRMS_EMP_DIRETORY_CONF.EMP_ROLE_DESG, HRMS_EMP_DIRETORY_CONF.EMP_MANAGER_INFO, HRMS_EMP_DIRETORY_CONF.EMP_BRANCH, HRMS_EMP_DIRETORY_CONF.EMP_EMAILID, HRMS_EMP_DIRETORY_CONF.EMP_EXTENSION, HRMS_EMP_DIRETORY_CONF.EMP_MOBILENO, HRMS_EMP_DIRETORY_CONF.EMP_PHONENO,HRMS_EMP_DIRETORY_CONF.EMP_PHOTO FROM HRMS_EMP_DIRETORY_CONF";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void getSearch(String searchType, String searchString,
			HttpServletRequest request, String profileCode, String mypage,
			EmployeePortal employeePortal, String resultOfSearch) {

		try {
			if (!(searchType == null) && !searchType.equals("null")) {
				/*
				 * if (searchType.equals("menu")) {
				 * employeePortal.setIsmenuDataAvailable(true);
				 * employeePortal.setEmpDataAvailable(false);
				 * 
				 * Object[] searchMenuStrObj = new Object[3];
				 * searchMenuStrObj[0] = profileCode; searchMenuStrObj[1] = "%" +
				 * searchString + "%"; searchMenuStrObj[2] = "%" + searchString +
				 * "%";
				 * 
				 * String searchMenuQuery = " SELECT MENU_NAME, MENU_LINK,
				 * MENU_PARENT_CODE FROM HRMS_MENU " + " INNER JOIN
				 * HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE =
				 * HRMS_PROFILE_DTL.MENU_CODE " + " and
				 * HRMS_PROFILE_DTL.PROFILE_CODE IN(?)" + " AND (
				 * PROFILE_INSERT_FLAG='Y' " + " OR PROFILE_UPDATE_FLAG ='Y' OR
				 * PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' " + " OR
				 * PROFILE_GENERAL_FLAG ='Y') ) " + " WHERE " + "
				 * (UPPER(MENU_NAME) like UPPER(?) OR UPPER(MENU_KEYWORDS) " + "
				 * like UPPER(?)) AND MENU_LINK IS NOT NULL AND
				 * MENU_PARENT_CODE>0" + " AND MENU_ISRELEASED='Y' ";// Object
				 * menuSearchObj[][] = getSqlModel().getSingleResult(
				 * searchMenuQuery, searchMenuStrObj); Object
				 * pagingMenuSearchObj[][] = null; if (menuSearchObj != null &&
				 * menuSearchObj.length > 0) { String[] pageIndexMenu =
				 * Utility.doPaging( employeePortal.getMyPageMenu(),
				 * menuSearchObj.length, 20); if (pageIndexMenu == null) {
				 * pageIndexMenu[0] = "0"; pageIndexMenu[1] = "20";
				 * pageIndexMenu[2] = "1"; pageIndexMenu[3] = "1";
				 * pageIndexMenu[4] = ""; }
				 * request.setAttribute("totalPageMenu", Integer
				 * .parseInt(String.valueOf(pageIndexMenu[2])));
				 * request.setAttribute("pageNoMenu", Integer
				 * .parseInt(String.valueOf(pageIndexMenu[3]))); if
				 * (pageIndexMenu[4].equals("1"))
				 * employeePortal.setMyPageMenu("1"); int length = 0; try {
				 * length = Integer.parseInt(pageIndexMenu[1]) -
				 * Integer.parseInt(pageIndexMenu[0]); } catch (Exception e) {
				 * e.printStackTrace(); } int count = 0; pagingMenuSearchObj =
				 * new Object[length][2]; for (int i =
				 * Integer.parseInt(pageIndexMenu[0]); i < Integer
				 * .parseInt(pageIndexMenu[1]); i++) {
				 * employeePortal.setMyMenuPageFlag(true);
				 * pagingMenuSearchObj[count][0] = menuSearchObj[i][0];
				 * pagingMenuSearchObj[count][1] = menuSearchObj[i][1]; count++; }
				 * request.setAttribute("menuSearchObj", pagingMenuSearchObj); }
				 * else { request.setAttribute("menuSearchObj", menuSearchObj); } }
				 * else if (searchType.equals("emp"))
				 */ 
				if(resultOfSearch.equals("emp"))
				{
					employeePortal.setEmpDataAvailable(true);
					employeePortal.setIsmenuDataAvailable(false);
					Object[] searchEmpStrObj = new Object[1];
					searchEmpStrObj[0] = "%" + searchString+ "%";
					System.out.println("........."+searchEmpStrObj[0]);
					String searchEmpQuery = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME  "
							+ " ,CENTER_NAME,HRMS_RANK.RANK_NAME,' ', "
							+ " NVL(ADD_PH1,''),NVL(ADD_PH2,''),NVL(ADD_EXTENSION,0),NVL(ADD_FAX,''), NVL(ADD_MOBILE,''),NVL(ADD_EMAIL,' '),HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
							+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE ='P') "
							+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)  "
							+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
							+ " WHERE  "
							+ " LOWER(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME) like LOWER('"+searchEmpStrObj[0]+"') "
							+ "  AND EMP_STATUS='S' "; 
					Object empSearchObj[][] = getSqlModel().getSingleResult(searchEmpQuery);
					Object pagingempSearchObj[][] = null;
					if (empSearchObj != null && empSearchObj.length > 0) {
						String[] pageIndex = Utility.doPaging(employeePortal
								.getMyPage(), empSearchObj.length, 20);
						if (pageIndex == null) {
							pageIndex[0] = "0";
							pageIndex[1] = "20";
							pageIndex[2] = "1";
							pageIndex[3] = "1";
							pageIndex[4] = "";
						}
						request.setAttribute("totalPage", Integer
								.parseInt(String.valueOf(pageIndex[2])));
						request.setAttribute("pageNo", Integer.parseInt(String
								.valueOf(pageIndex[3])));
						if (pageIndex[4].equals("1"))
							employeePortal.setMyPage("1");

						int length = 0;
						try {
							length = Integer.parseInt(pageIndex[1])
									- Integer.parseInt(pageIndex[0]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						int count = 0;
						pagingempSearchObj = new Object[length][12];
						for (int i = Integer.parseInt(pageIndex[0]); i < Integer
								.parseInt(pageIndex[1]); i++) {
							employeePortal.setPageFlag(true);
							pagingempSearchObj[count][0] = empSearchObj[i][0];
							pagingempSearchObj[count][1] = empSearchObj[i][1];
							pagingempSearchObj[count][3] = empSearchObj[i][3];
							pagingempSearchObj[count][5] = empSearchObj[i][5];
							pagingempSearchObj[count][6] = empSearchObj[i][6];
							pagingempSearchObj[count][7] = empSearchObj[i][7];
							pagingempSearchObj[count][9] = empSearchObj[i][9];
							pagingempSearchObj[count][10] = empSearchObj[i][10];
							pagingempSearchObj[count][11] = empSearchObj[i][11];
							count++;
						}
						request
								.setAttribute("empSearchObj",
										pagingempSearchObj);
						request.setAttribute("loopStartIndex", Integer
								.parseInt(pageIndex[0]));

					} else {
						request.setAttribute("empSearchObj", empSearchObj);
					}
				}
				
				if(resultOfSearch.equals("menu"))
				{
					String menuSearch = "SELECT DISTINCT HRMS_MENU.MENU_CODE, HRMS_MENU.MENU_NAME, MENU_LINK, "
					+ " CASE WHEN MENU_TYPE='ES' THEN 'myservices.png' " 
					+ " WHEN MENU_TYPE='MA' THEN 'analytic24.png' "
					+ " ELSE TO_CHAR(MENU_IMAGE) END , "
					+ " MENU_KEYWORDS , " 
					+ " CASE WHEN MENU_TYPE='ES' THEN 'My Services' "
					+ " WHEN MENU_TYPE='MA' THEN 'My Analytics'"
					+ " ELSE TO_CHAR(MENU_GROUP) END , "
					+ " MENU_GROUP_ORDER,MENU_PLACEINMENU ,MENU_TYPE "
					+ " FROM HRMS_MENU "  
					+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE " 
					+ " AND ( PROFILE_INSERT_FLAG='Y'  OR PROFILE_UPDATE_FLAG ='Y' "  
					+ " OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y'  OR PROFILE_GENERAL_FLAG ='Y')) " 
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN ("+employeePortal.getMultipleProfileCode()+")"
					+ " AND LOWER(MENU_NAME) LIKE LOWER('%"+searchString+"%') "  
					+ " AND MENU_ISRELEASED='Y'  AND MENU_PARENT_CODE!=0 AND MENU_TYPE!='MN' "
					+ " ORDER BY  MENU_GROUP_ORDER,MENU_PLACEINMENU ";
					
					Object[][] menuSearchObj=getSqlModel().getSingleResult(menuSearch);
					HashMap map=getMenuInGroups(menuSearchObj);
					Set keySet = null;
					Iterator itKeyList = null;
					keySet = map.keySet();
					itKeyList = keySet.iterator();
					while (itKeyList.hasNext()) {
					String	key = (String)itKeyList.next();
					System.out.println("Group Name :" +key);
					
						Vector vect = (Vector)map.get(key);
						for (int i = 0; i < vect.size(); i++) {
							Object[] menuObj=(Object[])vect.get(i);
							System.out.println("MenuName :" +String.valueOf(menuObj[1]));
						}
						System.out.println("------------------");
					}
					request.setAttribute("groupMap",map);
					request.setAttribute("serviceMenulist", menuSearchObj);
				
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap getMenuInGroups(Object[][] menuSearchObj){
		LinkedHashMap<String, Vector> groupMap=new LinkedHashMap<String, Vector>();
		for (int i = 0; i < menuSearchObj.length; i++) {
			String key =String.valueOf(menuSearchObj[i][5]+"#"+menuSearchObj[i][3]);
			if(groupMap.containsKey(key)){
				Vector innerVector=groupMap.get(key);
				innerVector.add(menuSearchObj[i]);
				groupMap.put(key, innerVector);
			}else{
				Vector innerVector=new Vector();
				innerVector.add(menuSearchObj[i]);
				groupMap.put(key, innerVector);
			}
		}
		return groupMap;
	}

	public void getApplicationLoginDetails(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			HashMap<String, String> userNameMap = null;
			HashMap<String, String> passwordMap = null;
			String userNamePassQuery = " SELECT HRMS_APPL_CODE,HRMS_APPL_USERNAME,HRMS_APPL_PASSWORD FROM HRMS_APPL_CREDENTIAL  "
					+ " WHERE   HRMS_APPL_EMPCODE="
					+ employeePortal.getUserEmpId()
					+ " ORDER BY HRMS_APPL_CODE ";
			Object userNameQueryObj[][] = getSqlModel().getSingleResult(
					userNamePassQuery);
			userNameMap = new HashMap<String, String>();
			passwordMap = new HashMap<String, String>();
			if (userNameQueryObj != null && userNameQueryObj.length > 0) {
				for (int j = 0; j < userNameQueryObj.length; j++) {

					userNameMap.put(String.valueOf(userNameQueryObj[j][0]),
							String.valueOf(userNameQueryObj[j][1]));
					passwordMap.put(String.valueOf(userNameQueryObj[j][0]),
							String.valueOf(userNameQueryObj[j][2]));
				}
			}
			request.setAttribute("applUserName", userNameMap);
			request.setAttribute("applPassword", passwordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method Name: getEventData()
	 * 
	 * @purpose Used to display division wise Event on More
	 * @param request
	 * @param employeePortal
	 */
	public void getEventData(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ employeePortal.getUserEmpId();
			Object empDivObj[][] = getSqlModel().getSingleResult(empDivQuery);
			String query = " SELECT HRMS_EVENT_MASTER.EVENT_CODE, HRMS_EVENT_MASTER.EVENT_NAME,EVENT_CATEGORY_CODE,EVENT_YEAR,"
					+ " EVENT_LOCATION, HRMS_PORTAL_EVENT.EVENT_NAME"
					+ " FROM HRMS_EVENT_MASTER"
					+ " LEFT JOIN HRMS_PORTAL_EVENT ON (HRMS_PORTAL_EVENT.EVENT_CODE = HRMS_EVENT_MASTER.EVENT_CATEGORY_CODE)"
					+ " WHERE (','||EVENT_DIVISION||',' LIKE '%,"
					+ empDivObj[0][0]
					+ ",%' or EVENT_DIVISION IS NULL)"
					+ " ORDER BY EVENT_YEAR DESC, HRMS_EVENT_MASTER.EVENT_CODE DESC  ";
			Object eventDataObj[][] = getSqlModel().getSingleResult(query);
			Object pagingEventDataObj[][] = null;
			if (eventDataObj != null && eventDataObj.length > 0) {
				String[] pageIndexEvent = Utility.doPaging(employeePortal
						.getMyPage(), eventDataObj.length, 20);
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
					employeePortal.setMyPage("1");

				int length = 0;
				try {
					length = Integer.parseInt(pageIndexEvent[1])
							- Integer.parseInt(pageIndexEvent[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (eventDataObj != null && eventDataObj.length > 0) { // Check
					// for
					// null
					pagingEventDataObj = new Object[eventDataObj.length][6];
					for (int i = 0; i < eventDataObj.length; i++) {
						pagingEventDataObj[i][0] = eventDataObj[i][0]; // Event
						// Code
						pagingEventDataObj[i][1] = eventDataObj[i][1]; // Event
						// Name
						pagingEventDataObj[i][2] = eventDataObj[i][2]; // Event
						// Category
						pagingEventDataObj[i][3] = eventDataObj[i][3]; // Event
						// Year
						pagingEventDataObj[i][4] = eventDataObj[i][4]; // Event
						// Location
						pagingEventDataObj[i][5] = eventDataObj[i][5]; // HRMS_PORTAL_EVENT.EVENT_NAME
					}// end for
				}// end if
				request.setAttribute("eventDataObj", pagingEventDataObj);
				request.setAttribute("loopStartIndexEvent", Integer
						.parseInt(pageIndexEvent[0]));
			} else {
				request.setAttribute("eventDataObj", eventDataObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getHolidayList(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			String query = "  SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY'),HOLI_DESC FROM HRMS_HOLIDAY  "
					+ " 	WHERE TO_CHAR(HOLI_DATE,'YYYY') =TO_CHAR(SYSDATE,'YYYY') "
					+ "	ORDER BY TO_DATE(HOLI_DATE,'DD-MM-YYYY') ";
			Object[][] holidaydataObj = getSqlModel().getSingleResult(query);
			request.setAttribute("holidayListObj", holidaydataObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getBirthdayList(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			String query = " SELECT EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,DEPT_NAME,TO_CHAR(EMP_DOB,'DD-MON')  "
					+ "	,emp_id ,center_name FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT ) "
					+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC .EMP_DIV) "
					+ " INNER JOIN HRMS_center ON (HRMS_center.CENTER_ID=HRMS_EMP_OFFC .EMP_center) "
					+ "	WHERE 1=1 "
					// +"(TO_NUMBER( (TO_CHAR(SYSDATE+15,'YYYY')||
					// TO_CHAR(EMP_DOB,'MMDD')) ) BETWEEN TO_NUMBER(
					// (TO_CHAR(SYSDATE-15,'YYYYMMDD'))) AND TO_NUMBER(
					// (TO_CHAR(SYSDATE,'YYYY')|| TO_CHAR(SYSDATE+15,'MMDD')) )"
					// + " OR TO_NUMBER( (TO_CHAR(SYSDATE-15,'YYYY')||
					// TO_CHAR(EMP_DOB,'MMDD')) ) BETWEEN TO_NUMBER(
					// (TO_CHAR(SYSDATE-15,'YYYYMMDD'))) AND TO_NUMBER(
					// (TO_CHAR(SYSDATE,'YYYY')|| TO_CHAR(SYSDATE+15,'MMDD'))
					// ))"
					+ " AND HRMS_EMP_OFFC.EMP_STATUS='S' AND SEND_RECEIVE_BDAY='Y' ";
			if (employeePortal.getUserProfileDivision() != null
					&& employeePortal.getUserProfileDivision().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
						+ employeePortal.getUserProfileDivision() + ")";
			}
			String selectedMonth = "";
			String currentMonthQuery = " select to_CHAR(sysdate,'mon')from dual";
			Object[][] currentMonthObj = getSqlModel().getSingleResult(
					currentMonthQuery);
			if (currentMonthObj != null && currentMonthObj.length > 0) {
				selectedMonth = String.valueOf(currentMonthObj[0][0])
						.toUpperCase();
			}
			if (employeePortal.getMonthList() == null
					|| employeePortal.getMonthList().equals("")) {
				selectedMonth = selectedMonth;
				System.out.println("selectedMonth   " + selectedMonth);
			} else {
				selectedMonth = employeePortal.getMonthList().trim();
			}
			if (selectedMonth != null && !selectedMonth.equals("")) {
				query += " AND to_char(EMP_DOB,'MON') ='" + selectedMonth
						+ "' ";
			}
			query += "ORDER BY TO_CHAR(EMP_DOB,'MM-DD') ASC ";
			Object[][] birthdayListObj = getSqlModel().getSingleResult(query);
			Object pagingbirthdayListObj[][] = null;
			if (birthdayListObj != null && birthdayListObj.length > 0) {
				String[] pageIndexBirthday = Utility.doPaging(employeePortal
						.getMyPage(), birthdayListObj.length, 20);
				if (pageIndexBirthday == null) {
					pageIndexBirthday[0] = "0";
					pageIndexBirthday[1] = "20";
					pageIndexBirthday[2] = "1";
					pageIndexBirthday[3] = "1";
					pageIndexBirthday[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndexBirthday[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndexBirthday[3])));
				if (pageIndexBirthday[4].equals("1"))
					employeePortal.setMyPage("1");
				int length = 0;
				try {
					length = Integer.parseInt(pageIndexBirthday[1])
							- Integer.parseInt(pageIndexBirthday[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				int count = 0;
				pagingbirthdayListObj = new Object[length][6];
				for (int i = Integer.parseInt(pageIndexBirthday[0]); i < Integer
						.parseInt(pageIndexBirthday[1]); i++) {
					employeePortal.setBirthdayPageFlag(true);
					pagingbirthdayListObj[count][0] = birthdayListObj[i][0];
					pagingbirthdayListObj[count][1] = birthdayListObj[i][1];
					pagingbirthdayListObj[count][2] = birthdayListObj[i][2];
					pagingbirthdayListObj[count][3] = birthdayListObj[i][3];
					pagingbirthdayListObj[count][4] = birthdayListObj[i][4];
					pagingbirthdayListObj[count][5] = birthdayListObj[i][5];
					count++;
				}
				request.setAttribute("birthdayListObj", pagingbirthdayListObj);

				request.setAttribute("loopStartIndexBirthday", Integer
						.parseInt(pageIndexBirthday[0]));
			} else {
				request.setAttribute("birthdayListObj", birthdayListObj);
				request.setAttribute("loopStartIndexBirthday", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getNewJoineeList(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			String query = " SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') ,EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,RANK_NAME,DEPT_NAME,CENTER_NAME "
					+ "	,EMP_ID FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )"
					+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
					+ "	INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)"
					+ "	 WHERE EMP_REGULAR_DATE>= SYSDATE-45 AND HRMS_EMP_OFFC.EMP_STATUS='S' ";
			if (employeePortal.getUserProfileDivision() != null
					&& employeePortal.getUserProfileDivision().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
						+ employeePortal.getUserProfileDivision() + ")";
			}
			query += " ORDER BY TO_DATE(EMP_REGULAR_DATE,'DD-MM-YYYY') DESC";
			Object[][] newJoineeListObj = getSqlModel().getSingleResult(query);
			Object pagingNewJoineeListObj[][] = null;
			if (newJoineeListObj != null && newJoineeListObj.length > 0) {
				String[] pageIndex = Utility.doPaging(employeePortal
						.getMyPageNewJoinee(), newJoineeListObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPageNewJoinee", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoNewJoinee", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					employeePortal.setMyPageNewJoinee("1");
				int length = 0;
				try {
					length = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				int count = 0;
				pagingNewJoineeListObj = new Object[length][7];
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					employeePortal.setNewJoineePageFlag(true);
					pagingNewJoineeListObj[count][0] = newJoineeListObj[i][0];
					pagingNewJoineeListObj[count][1] = newJoineeListObj[i][1];
					pagingNewJoineeListObj[count][2] = newJoineeListObj[i][2];
					pagingNewJoineeListObj[count][3] = newJoineeListObj[i][3];
					pagingNewJoineeListObj[count][4] = newJoineeListObj[i][4];
					pagingNewJoineeListObj[count][5] = newJoineeListObj[i][5];
					pagingNewJoineeListObj[count][6] = newJoineeListObj[i][6];
					count++;
				}
				request
						.setAttribute("newJoineeListObj",
								pagingNewJoineeListObj);
				request.setAttribute("loopStartIndex", Integer
						.parseInt(pageIndex[0]));
			} else {
				request.setAttribute("newJoineeListObj", newJoineeListObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object[][] getEmployeeDetailtRecord(String empCode,
			EmployeePortal employeePortal, HttpServletRequest request) {
		Object empDtlObj[][] = null;
		try {
			String query = " SELECT EMP_TOKEN , EMP_FNAME||' '||EMP_LNAME ,case when SEND_RECEIVE_BDAY='Y' then TO_CHAR(EMP_DOB,'DD Mon') else '' end , "
					+ "	DECODE(EMP_GENDER,'M','Male','F','Female') "
					+ "	,EMP_BLDGP,TO_CHAR(EMP_REGULAR_DATE,'DD-MON-YYYY'), "
					+ "	DEPT_NAME ,RANK_NAME "
					+ "	,EMP_ROLE,EMP_REPORTING_OFFICER,(SELECT EMP_FNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC "
					+ "	WHERE EMP_ID=(SELECT EMP_REPORTING_OFFICER FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empCode
					+ "))  "
					+ "	AS IMMIDIATE_MANAGER "
					+ "	,CENTER_NAME ,ADD_EMAIL ,ADD_EXTENSION ,'NA' "
					+ "	, ADD_MOBILE ,ADD_PH1, ADD_PH2 ,NVL(HRMS_EMP_OFFC.EMP_PHOTO,'') FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_EMP_PERS ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT) "
					+ "	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_RANK) "
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =HRMS_EMP_OFFC.EMP_CENTER) "
					+ "	LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID =HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC .EMP_DIV)"
					+ "	WHERE HRMS_EMP_OFFC.EMP_ID="
					+ empCode
					+ " AND ADD_TYPE='P'	";

			empDtlObj = getSqlModel().getSingleResult(query);

			if (empDtlObj != null && empDtlObj.length > 0) {
				String photo = String.valueOf(empDtlObj[0][18]);
				if (!photo.equals("")) {
					request.setAttribute("empPhotograph", String
							.valueOf(empDtlObj[0][18]));
				} else {
					request.setAttribute("empPhotograph", "empimage.gif");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empDtlObj;
	}

	/**
	 * Method Name: getResignedEmployeeList()
	 * 
	 * @purpose Used for Resigned Employee List
	 * @param request
	 * @param employeePortal
	 */
	public void getResignedEmployeeList(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			String query = " SELECT TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY') ,EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,RANK_NAME,DEPT_NAME,CENTER_NAME "
					+ "	 FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT )"
					+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ "	LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)"
					+ "	INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)"
					+ "	 WHERE  1=1 AND EMP_LEAVE_DATE is not null AND EMP_LEAVE_DATE >= SYSDATE-60 AND HRMS_EMP_OFFC.EMP_STATUS='N'";
			if (employeePortal.getUserProfileDivision() != null
					&& employeePortal.getUserProfileDivision().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
						+ employeePortal.getUserProfileDivision() + ")";
			}
			query += " ORDER BY TO_DATE(EMP_LEAVE_DATE,'DD-MM-YYYY') ";
			Object[][] resignedListObj = getSqlModel().getSingleResult(query);
			Object pagingResignedListObj[][] = null;
			if (resignedListObj != null && resignedListObj.length > 0) {
				String[] pageIndex = Utility.doPaging(employeePortal
						.getMyPageResignDisplay(), resignedListObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPageResigned", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoResigned", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					employeePortal.setMyPageResignDisplay("1");
				int length = 0;
				try {
					length = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				int count = 0;
				pagingResignedListObj = new Object[length][6];
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					employeePortal.setResignPageFlag(true);
					pagingResignedListObj[count][0] = resignedListObj[i][0];
					pagingResignedListObj[count][1] = resignedListObj[i][1];
					pagingResignedListObj[count][2] = resignedListObj[i][2];
					pagingResignedListObj[count][3] = resignedListObj[i][3];
					pagingResignedListObj[count][4] = resignedListObj[i][4];
					pagingResignedListObj[count][5] = resignedListObj[i][5];
					count++;
				}
				request.setAttribute("resignedListObj", pagingResignedListObj);
				request.setAttribute("loopStartIndex", Integer
						.parseInt(pageIndex[0]));
			} else {
				request.setAttribute("resignedListObj", resignedListObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method Name: getBdayRecord()
	 * 
	 * @purpose Used for birthday search month wise
	 * @param request
	 * @param employeePortal
	 */
	public void getBdayRecord(HttpServletRequest request,
			EmployeePortal employeePortal) {
		try {
			String query = " SELECT EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,DEPT_NAME,TO_CHAR(EMP_DOB,'DD-MON')  "
					+ "	FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT ) "
					+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC .EMP_DIV) "
					+ "	WHERE (TO_NUMBER( (TO_CHAR(SYSDATE+15,'YYYY')|| TO_CHAR(EMP_DOB,'MMDD')) ) BETWEEN TO_NUMBER( (TO_CHAR(SYSDATE-15,'YYYYMMDD'))) AND TO_NUMBER( (TO_CHAR(SYSDATE,'YYYY')|| TO_CHAR(SYSDATE+15,'MMDD')) )"
					+ " OR TO_NUMBER( (TO_CHAR(SYSDATE-15,'YYYY')|| TO_CHAR(EMP_DOB,'MMDD')) ) BETWEEN TO_NUMBER( (TO_CHAR(SYSDATE-15,'YYYYMMDD'))) AND TO_NUMBER( (TO_CHAR(SYSDATE,'YYYY')|| TO_CHAR(SYSDATE+15,'MMDD')) ))"
					+ " AND HRMS_EMP_OFFC.EMP_STATUS='S' AND SEND_RECEIVE_BDAY='Y'  and to_char(EMP_DOB,'MON') ='"
					+ employeePortal.getMonthList() + "' ";
			if (employeePortal.getUserProfileDivision() != null
					&& employeePortal.getUserProfileDivision().length() > 0) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
						+ employeePortal.getUserProfileDivision() + ")";
			}
			query += "ORDER BY TO_CHAR(EMP_DOB,'MM-DD') ASC ";
			Object[][] birthdayListObj = getSqlModel().getSingleResult(query);
			Object pagingbirthdayListObj[][] = null;
			if (birthdayListObj != null && birthdayListObj.length > 0) {
				String[] pageIndexBirthday = Utility.doPaging(employeePortal
						.getMyPage(), birthdayListObj.length, 20);
				if (pageIndexBirthday == null) {
					pageIndexBirthday[0] = "0";
					pageIndexBirthday[1] = "20";
					pageIndexBirthday[2] = "1";
					pageIndexBirthday[3] = "1";
					pageIndexBirthday[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndexBirthday[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndexBirthday[3])));
				if (pageIndexBirthday[4].equals("1"))
					employeePortal.setMyPage("1");
				int length = 0;
				try {
					length = Integer.parseInt(pageIndexBirthday[1])
							- Integer.parseInt(pageIndexBirthday[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				int count = 0;
				pagingbirthdayListObj = new Object[length][4];
				for (int i = Integer.parseInt(pageIndexBirthday[0]); i < Integer
						.parseInt(pageIndexBirthday[1]); i++) {
					employeePortal.setBirthdayPageFlag(true);
					pagingbirthdayListObj[count][0] = birthdayListObj[i][0];
					pagingbirthdayListObj[count][1] = birthdayListObj[i][1];
					pagingbirthdayListObj[count][2] = birthdayListObj[i][2];
					pagingbirthdayListObj[count][3] = birthdayListObj[i][3];
					count++;
				}
				request.setAttribute("birthdayListObj", pagingbirthdayListObj);
				request.setAttribute("loopStartIndexBirthday", Integer
						.parseInt(pageIndexBirthday[0]));
			} else {
				request.setAttribute("birthdayListObj", birthdayListObj);
				request.setAttribute("loopStartIndexBirthday", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showQuickDownload(HttpServletRequest request,
			EmployeePortal employeePortal, String downloadCategory) {
		try {

			String empDivQuery = "SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID= "
					+ employeePortal.getUserEmpId();
			Object[][] empDivObj = getSqlModel().getSingleResult(empDivQuery);
			String query = " SELECT DOWNLOAD_CATEGORY,DOWNLOAD_SUBCATEGORY,DOWNLOAD_LINKNAME ,DOWNLOAD_LINK FROM HRMS_DASHLET_DOWNLOAD "
					+ " WHERE UPPER(DOWNLOAD_CATEGORY)='"
					+ downloadCategory.toUpperCase()
					+ "'  AND DOWNLOAD_STATUS='Y' "
					+ " AND (','||DOWNLOAD_DIVISION||',' LIKE '%,"
					+ empDivObj[0][0]
					+ ",%' OR DOWNLOAD_DIVISION IS NULL)"
					+ " ORDER BY DOWNLOAD_CATEGORY";
			Object categoryObj[][] = getSqlModel().getSingleResult(query);
			request.setAttribute("categoryObj", categoryObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showKnowledge(HttpServletRequest request,
			EmployeePortal employeePortal, String knowCat) {
		try {
			String empDivQuery = "SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID= "
					+ employeePortal.getUserEmpId();
			Object[][] empDivObj = getSqlModel().getSingleResult(empDivQuery);
			String query = " SELECT KNOW_CATEGORY,KNOW_LINKNAME,KNOW_LINK FROM HRMS_SETTINGS_KNOWLEDGE "
					+ " WHERE UPPER(KNOW_CATEGORY)='"
					+ knowCat.toUpperCase()
					+ "'  AND KNOW_FLAG='Y' "
					+ " AND (','||KNOW_DIVISION||',' LIKE '%,"
					+ empDivObj[0][0]
					+ ",%' OR KNOW_DIVISION IS NULL)"
					+ " ORDER BY KNOW_CATEGORY";
			Object knowObj[][] = getSqlModel().getSingleResult(query);
			request.setAttribute("knowObj", knowObj);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method Name :getQuickContacts()
	 * 
	 * @purpose Used to display Contacts Division Wise
	 * @param request
	 * @param employeePortal
	 */
	public void getQuickContacts(HttpServletRequest request,
			EmployeePortal employeePortal) {
		Object[][] finalContactObj = null;
		try {
			String divQuery = "SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ employeePortal.getUserEmpId();
			Object[][] empDivObj = getSqlModel().getSingleResult(divQuery);
			String query = " SELECT MYCONTACT_DEPT_NAME,MYCONTACT_CONTACT_NAME,MYCONTACT_CONTACT_NUMBER,"
					+ " MYCONTACT_CONTACT_EMAIL"
					+ " FROM HRMS_MYCONTACTS"
					+ " WHERE (','||MYCONTACT_DIVISION||',' LIKE '%,"
					+ empDivObj[0][0]
					+ ",%' OR MYCONTACT_DIVISION IS NULL)"
					+ " GROUP BY MYCONTACT_DEPT_NAME,MYCONTACT_CONTACT_NAME,"
					+ " MYCONTACT_CONTACT_NUMBER,MYCONTACT_CONTACT_EMAIL,MYCONTACT_DIVISION";
			Object[][] contactObj = getSqlModel().getSingleResult(query);

			if (contactObj != null && contactObj.length > 0) {// Check for
				// Null
				finalContactObj = new Object[contactObj.length][4];
				for (int i = 0; i < contactObj.length; i++) {
					finalContactObj[i][0] = String.valueOf(contactObj[i][0]); // Dept
					// Name
					finalContactObj[i][1] = String.valueOf(contactObj[i][1]); // Contact
					// Name
					finalContactObj[i][2] = String.valueOf(contactObj[i][2]); // Contact
					// Number
					finalContactObj[i][3] = String.valueOf(contactObj[i][3]); // Contact
					// Email
				}

			}
			request.setAttribute("contactObj", finalContactObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methiod Name: quickDownload()
	 * 
	 * @purpose Used to display Quick Download List on Dashlet
	 * @param request
	 * @param employeePortal
	 */
	public void quickDownload(HttpServletRequest request,
			EmployeePortal employeePortal) {
		Object[][] downloadList = null;
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ employeePortal.getUserEmpId();
			Object empDivObj[][] = getSqlModel().getSingleResult(empDivQuery);
			String query = " SELECT DISTINCT DOWNLOAD_CATEGORY"
					+ " FROM HRMS_DASHLET_DOWNLOAD"
					+ " WHERE DOWNLOAD_STATUS='Y'  AND (','||DOWNLOAD_DIVISION||',' LIKE '%,"
					+ empDivObj[0][0] + ",%' OR DOWNLOAD_DIVISION IS NULL)"
					+ " ORDER BY DOWNLOAD_CATEGORY ";
			downloadList = getSqlModel().getSingleResult(query);
			request.setAttribute("downloadList", downloadList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
