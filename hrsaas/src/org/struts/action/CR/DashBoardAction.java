package org.struts.action.CR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.paradyne.bean.CR.DashBoard;
import org.paradyne.bean.CR.DashBoardSchedular;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.Utility;
import org.paradyne.model.CR.DashBoardJobScheduler;
import org.paradyne.model.CR.DashBoardModel;
import org.paradyne.model.admin.srd.SendMailModel;
import org.struts.action.ApplicationStudio.jobScheduling.JobScheduler;
import org.struts.lib.ParaActionSupport;

/**
 * @purpose: DashBoard
 * @author Vijay.Gaikwad Date : 14-jan-2013
 * 
 */
public class DashBoardAction extends ParaActionSupport {
	DashBoard dashBean = null;
	DashBoardSchedular dashBoardSchedular = null;
	Object objectReportMenuList[][] = null;
	Object obj[][] = null;
	Object objCell[][] = null;
	Object ObjDetails[][] = null;
	Vector ObjVector = null;
	Object[][] dashObject = (Object[][]) null;
	String chartCode;
	
	/*public DashBoardAction(String dashboardID,String accountId ) {
		if(null!=dashboardID && !dashboardID.equals("")){
		dashBean.setDashBoardID(dashboardID);}
		if(null!=accountId && !accountId.equals("")){
			dashBean.setAccountID(accountId);
		}
	}*/

	private final String jobClass = "org.paradyne.model.ApplicationStudio.ProcessManagerAlertQuery";
	String jobGroup = "DashBoardEmailScheduler";

	static Logger logger = Logger.getLogger(DashBoardAction.class);

	public void prepare_local() throws Exception {
		this.dashBean = new DashBoard();
		dashBean.setMenuCode(2334);
	}

	/**
	 * Method Name :input() Used to assign SubMenu
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String input() throws Exception {
		String userType="";
		try {
			if(dashBean.getDashMonth().equals("")&&dashBean.getDashYear().equals("")){
			int month=Calendar.getInstance().get(Calendar.MONTH);
			int year=Calendar.getInstance().get(Calendar.YEAR);
			//System.out.println("Current Year is - "+year);
			String monthstr=String.valueOf(month+1);
			
			if(monthstr.length()>1){
				
			}
			else{
				monthstr="0"+monthstr;
			}
			dashBean.setDashMonth(monthstr);
			dashBean.setDashYear(String.valueOf(year));
			//System.out.println("Current Month after -"+monthstr);
			}
			DashBoardModel model = new DashBoardModel();
			model.initiate(this.context, this.session);
			// get Attribute from Database i.e DashBoardID
			String dashboardID = (String) request.getParameter("dashBoardID");
			userType = (String) request.getParameter("userType1");;//userType1
			String dashName = model.getDashboardDetails(dashboardID);
			//System.out.println("userType"+userType);
			this.dashBean.setDashBoardName(dashName);
			if (dashboardID != null) {
				dashBean.setDashBoardID(dashboardID);
			}
			// getting Account id From CRM
			String accountId = (String) request.getParameter("accountID");
			if (accountId != null) {
				dashBean.setAccountID(accountId);
			} else {
				dashBean.setAccountID("");
			}

			HttpSession session = request.getSession();
			//userType = (String) session.getAttribute("userType");
			String clientID = (String) session
					.getAttribute("customerUserEmpIdSession");
			String empId = (String) session.getAttribute("empId");
			//String profileId=(String)session.getAttribute("userProfileId");
			
			//System.out.println("Emp Id ===== >"+empId);
			//System.out.println("profileID == ===>"+this.dashBean.getUserProfileId());
			
			if(null==userType || userType.equals("") || userType.equals("null")){
				userType = (String) session.getAttribute("userType");
			}
			
			if(userType.equals("P")){
				userType = "HRM";
				this.dashBean.setUserID(this.dashBean.getUserProfileId());
			}
			
			else if (userType.equals("I")) {
				userType = "CRM";
				this.dashBean.setUserID(empId);
			} 
			
			else {
				userType = "Client";
				this.dashBean.setUserID(clientID);
			}
			dashBean.setUserType(userType);
			/*
			 * getting account name from CRM
			 */
			String accountName = (String) request.getParameter("accountName");
			String dashBoardAccountName = (String) request
					.getParameter("dashBoardAccountName");
			dashBean.setDashBoardAccountName(dashBoardAccountName);
			if (accountName != null) {
				dashBean.setAccountName(accountName);
			} else {
				dashBean.setAccountName("");
			}

			/*
			 * if account id is null i.e dashBoard for UAL
			 */
			if (accountId == null || accountId.equals("")
					|| accountId.equals("null")) {
				/*
				 * For getting Object for Reports Menu
				 */
				objectReportMenuList = model.getTableMenuList(dashBean);
				model.getDocumentFileList(this.dashBean);

				/*
				 * To get all the graphs data
				 */
				dashObject = (Object[][]) model.gettabfrmQuery(request,
						this.dashBean);
				request.setAttribute("objectReportMenuList",
						objectReportMenuList);
				request.setAttribute("dashObject", dashObject);
			} else {
				objectReportMenuList = model.getTableMenuList_Account(dashBean);
				// for Dash board row and columns and colspan

				dashObject = (Object[][]) model.gettabfrmQuery_account(request,
						this.dashBean);
				request.setAttribute("objectReportMenuList",
						objectReportMenuList);
				request.setAttribute("dashObject", dashObject);
			}
			
			dashBoardLog();
			model.terminate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return INPUT;
		
		
	}
	
	public String HRMDashBoardinput(){
		try {
			DashBoardModel model = new DashBoardModel();
			model.initiate(this.context, this.session);
			// get Attribute from Database i.e DashBoardID
			String dashboardID = (String) request.getParameter("dashBoardID");
			String dashName = model.getDashboardDetails(dashboardID);

			this.dashBean.setDashBoardName(dashName);
			if (dashboardID != null) {
				dashBean.setDashBoardID(dashboardID);
			}
			// getting Account id From CRM
			String accountId = (String) request.getParameter("accountID");
			if (accountId != null) {
				dashBean.setAccountID(accountId);
			} else {
				dashBean.setAccountID("");
			}

			HttpSession session = request.getSession();
			String userType = (String) session.getAttribute("userType");
			String clientID = (String) session
					.getAttribute("customerUserEmpIdSession");
			String empId = (String) session.getAttribute("empId");
			if (userType.equals("I")) {
				userType = "CRM";
				this.dashBean.setUserID(empId);
			} else {
				userType = "Client";
				this.dashBean.setUserID(clientID);
			}
			dashBean.setUserType(userType);
			/*
			 * getting account name from CRM
			 */
			String accountName = (String) request.getParameter("accountName");
			String dashBoardAccountName = (String) request
					.getParameter("dashBoardAccountName");
			dashBean.setDashBoardAccountName(dashBoardAccountName);
			if (accountName != null) {
				dashBean.setAccountName(accountName);
			} else {
				dashBean.setAccountName("");
			}

			/*
			 * if account id is null i.e dashBoard for UAL
			 */
			if (accountId == null || accountId.equals("")
					|| accountId.equals("null")) {

				/*
				 * To get all the graphs data
				 */
				dashObject = (Object[][]) model.gettabfrmQuery(request,
						this.dashBean);
				request.setAttribute("objectReportMenuList",
						objectReportMenuList);
				request.setAttribute("dashObject", dashObject);
			} else {
				objectReportMenuList = model.getTableMenuList_Account(dashBean);
				// for Dash board row and columns and colspan

				dashObject = (Object[][]) model.gettabfrmQuery_account(request,
						this.dashBean);
				request.setAttribute("objectReportMenuList",
						objectReportMenuList);
				request.setAttribute("dashObject", dashObject);
			}
			
			dashBoardLog();
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */

	public Object getModel() {

		return this.dashBean;

	}

	public void dashBoardLog() {
		try {

			String empId = (String) session.getAttribute("empId");
			String clientID = (String) session
					.getAttribute("customerUserEmpIdSession");
			String userType = (String) session.getAttribute("userType");
			String IP = request.getRemoteAddr();
			int port = request.getRemotePort();
			Object[][] logTableColoumValue = new Object[1][10];
			if (empId != null && !empId.equals("") && !empId.equals("null")) {
				logTableColoumValue[0][1] = empId;
			} else {
				logTableColoumValue[0][1] = "";
			}
			if (clientID != null && !clientID.equals("")
					&& !clientID.equals("null")) {
				logTableColoumValue[0][2] = clientID;
			} else {
				logTableColoumValue[0][2] = "";
			}
			logTableColoumValue[0][3] = userType;
			logTableColoumValue[0][4] = String.valueOf(IP);
			logTableColoumValue[0][5] = String.valueOf(port);
			logTableColoumValue[0][6] = dashBean.getDashBoardID();
			if (dashBean.getReportID() != null
					&& !dashBean.getReportID().equals("")
					&& !dashBean.getReportID().equals("null")) {
				logTableColoumValue[0][7] = dashBean.getReportID();
			} else {
				logTableColoumValue[0][7] = "";
			}
			if (dashBean.getCompoNo() != null
					&& !dashBean.getCompoNo().equals("")
					&& !dashBean.getCompoNo().equals("null")) {
				logTableColoumValue[0][8] = dashBean.getCompoNo();
			} else {
				logTableColoumValue[0][8] = "";
			}
			if (dashBean.getIsmail() != null
					&& !dashBean.getIsmail().equals("")
					&& !dashBean.getIsmail().equals("null")) {
				logTableColoumValue[0][9] = dashBean.getIsmail();
			} else {
				logTableColoumValue[0][9] = "0";
			}
			DashBoardModel model = new DashBoardModel();
			model.initiate(this.context, this.session);
			model.DashBoardLogModel(logTableColoumValue);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This funtion is used for getting report parameter and displaying jsp in
	 * window pop up
	 * 
	 * @return
	 */
	public String getMenuList() {
		try {
			DashBoardModel model = new DashBoardModel();
			model.initiate(this.context, this.session);
			String reportId = (String) request.getParameter("reportId");
			String reportName = (String) request.getParameter("reportName");
			String accountID = (String) request.getParameter("accountID");
			String accountName = (String) request.getParameter("accountName");
			request.setAttribute("reportId", reportId);
			request.setAttribute("reportName", reportName);
			request.setAttribute("account_ID", accountID);
			request.setAttribute("ReportURL", getText("analytics_url"));
			request.setAttribute("userType", this.dashBean.getUserType());
			this.dashBean.setReportID(reportId);
			Object[][] reportPrameter = model.getMenuParameter(this.dashBean);
			int Rparameterlen = reportPrameter.length;
			int Cparameterlen = reportPrameter[0].length;
			request.setAttribute("reportPrameter", reportPrameter);
			dashBoardLog();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "menulist";
	}

	/**
	 * Method Name :getComponent() Used to displaying detailed each component
	 * graph and table. graph drill down in new pages
	 * 
	 * @return String
	 * 
	 */

	public String getComponent() {
		try {

			DashBoardModel model = new DashBoardModel();
			model.initiate(this.context, this.session);
			String accountCode = (String) request.getParameter("accountID1");
			if("null".equals(accountCode) ||"".equals(accountCode)||null==accountCode){
				accountCode=this.dashBean.getAccountID();
			}
			String account_Id = (String) request.getParameter("accountName");
			String componentId = (String) request.getParameter("componentId");
			if(null==componentId || "null".equals(componentId) || "".equals(componentId)){
			componentId=this.dashBean.getCompoNo();
			}
			String dashBoardName = (String) request	.getParameter("dashBoardName");
			String dashBoardAccountName = (String) request.getParameter("dashBoardAccountName");
			this.dashBean.setDashBoardName(dashBoardName);
			this.dashBean.setDashBoardAccountName(dashBoardAccountName);
			String autoID = (String) request.getParameter("autoID");
			if(null==autoID || "null".equals(autoID) || "".equals(autoID)){
				autoID=this.dashBean.getDashAutoId();
			}
			dashBean.setCompoNo(componentId);
			String preComponentId = (String) request
					.getParameter("preComponentId");
			String graphParameter = (String) request
					.getParameter("graphParameter");
			String nextParam = (String) request.getParameter("nextParam");
			if (account_Id == null) {
				account_Id = dashBean.getCompoNo();
			}
			System.out.println("nextParam request : " + nextParam);
			dashBoardLog();
			
			if (graphParameter == null || graphParameter.equals("")|| graphParameter.equals("null")) {
				graphParameter = "";
			}
			model.getResultbyComponentId(preComponentId, componentId,
					graphParameter, nextParam, request, accountCode,
					account_Id, this.dashBean, autoID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(this.dashBean.getDashscFlag().equals("YY")){
			callMIS();
			return "callMIS";
		}
		else{
		return "showComponent";
		}
	}

	/*
	 * For Data Mining
	 */
	
	public String getObjMIS() {
		DashBoardModel model = new DashBoardModel();
		model.initiate(context, session);
		String scenerioId = request.getParameter("scenerioId");
		model.getscenerioInfo(scenerioId, this.dashBean);
		this.dashBean.setDashscFlag("YY");
		getComponent();
		model.terminate();
		return "callMIS";
	}
	
	
	
	
	/**
	 * Method Name :getEmailPage() Used to Email ReportMenu
	 * 
	 * @return String
	 * 
	 */
	public String getEmailPage() {
		try {
			String componetID = (String) request.getParameter("CompoNo");
			String componetName = (String) request
					.getParameter("componentName");
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String report_id = (String) request.getParameter("report_id");
			String account_ID = (String) request.getParameter("ACCOUNT_CODE");
			String accountName = (String) request.getParameter("ACCOUNT_ID");
			String reportName = (String) request.getParameter("reportName");
			String graphParameter = (String) request
					.getParameter("graphPrameter");
			String userType = (String) request.getParameter("userType");
			String isDataInternal = (String) request
					.getParameter("isDataInternal");
			String isDashBoard = (String) request.getParameter("isDashBoard");// For
																				// after
																				// sending
																				// redirect
			String menuParaCaption = (String) request
					.getParameter("paraCaption");
			if (isDashBoard.equals("YY")) {
				this.dashBean.setIsDashBoard("YY");
			} else {
				this.dashBean.setIsDashBoard("NN");
			}
			accountName = dashBean.getAccountName();
			account_ID = dashBean.getAccountID();
			this.dashBean.setIsmail("1");
			String[] reportIDSplit = null;

			if (report_id != null && !report_id.equals("")
					&& !report_id.equals("null")) {
				report_id = report_id.replaceAll("`", "&");
			}
			if (report_id != null) {
				reportIDSplit = report_id.split("&");
				System.out.println("reportIDSplit--" + reportIDSplit[0]);

				// Getting ReportID for DashBoardLog

				this.dashBean.setReportID(reportIDSplit[0]);
			}
			System.out.println("Parameter from dashBoardMenu is--ReportID-- "
					+ report_id + "account_ID-- " + account_ID
					+ "accountName-- " + accountName);

			if (componetName != null && !componetName.equals("")) {
				request.setAttribute("defoultSub", componetName);
			} else if (reportName != null) {
				request.setAttribute("defoultSub", reportName);
			}

			DashBoardModel model = new DashBoardModel();
			model.initiate(this.context, this.session);
			Random rand = new Random();
			String file = "";
			if (componetName != null && componetID != null) {
				file = componetName + componetID + rand.nextInt(999) + ".xls";
			} else if (account_ID != null && report_id != null) {
				file = reportName + account_ID + rand.nextInt(999) + ".xls";
			} else if (report_id != null) {
				file = reportName + reportIDSplit[0] + rand.nextInt(999)
						+ ".xls";
			}
			String fileName = getText("data_path") + "/Report/D1/" + poolName
					+ "/" + file;
			request.setAttribute("reportPath", fileName);
			File newFile = null;
			try {
				String makeDir = fileName;
				Boolean makeD = new File(getText("data_path") + "/Report/D1/"
						+ poolName).mkdirs();
				newFile = new File(fileName);
				if (!newFile.exists() && makeD) {
					newFile.createNewFile();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (componetID != null && !componetID.equals("")) {
				if (graphParameter != null && !graphParameter.equals("")
						&& !graphParameter.equals("null")) {
					graphParameter = graphParameter.replaceAll("3333", "&");
				} else {
					graphParameter = "";
				}
				System.out.println("for Component URL is-- "
						+ getText("analytics_url") + "?report_id=" + componetID
						+ "&xltype=Component" + graphParameter + "&ACCOUNT_ID="
						+ accountName);
				String stringUrl = "";
				if (accountName != null) {
					stringUrl = "report_id=" + componetID + "&xltype=Component"
							+ graphParameter + "&ACCOUNT_ID=" + accountName
							+ "&ACCOUNT_CODE=" + account_ID;
				} else {
					stringUrl = "report_id=" + componetID + "&xltype=Component"
							+ graphParameter;
				}
				stringUrl = stringUrl.replaceAll(" ", "!");

				/*
				 * Changes for set in dashBoard Configuration
				 */

				Object[][] configPara = model.getConfigurePara(this.dashBean);
				String configParaString = "";
				String configParaString1 = "";
				configParaString = configParaString + configParaString1;
				int configParalen = configPara.length;
				for (int i = 0; i < configParalen; i++) {
					if (!graphParameter.contains(String
							.valueOf(configPara[i][0]))
							&& !stringUrl.contains(String
									.valueOf(configPara[i][0]))) {

						configParaString1 = (String.valueOf(configPara[i][0])
								+ "=" + String.valueOf(configPara[i][1]) + "&");
						configParaString = configParaString + configParaString1;
					}
				}
				if (!configParaString.equals("")) {
					configParaString = configParaString.substring(0,
							configParaString.length() - 1);
				}
				if (isDataInternal.equals("I")) {
					model.saveUrlToFile(newFile, getText("analytics_url") + "?"
							+ stringUrl + "&" + configParaString);
				} else {
					// get xml url
					String query = model.getFilterDrilDownQuery(componetID,
							graphParameter, "", dashBean);
					System.out.println("component Email Query " + query);
					String reportData = getComponentXml(componetID, query,
							componetName, dashBean);// return required reports
													// information in xml
					System.out.println("xml url email - " + reportData);
					String URLString = "http://12.145.20.88/UALDIRECT/DirectLinkXMLXLS.aspx"; // get
																								// from
																								// database
					model.saveXmlURLtoFile(newFile, URLString, reportData);
				}
			} else if (account_ID != null && report_id != null) {
				if (graphParameter != null && !graphParameter.equals("")
						&& !graphParameter.equals("null")) {
					graphParameter = graphParameter.replaceAll("3333", "&");
				} else {
					graphParameter = "";
				}
				// properties url for attacamet
				String stringUrl = "report_id=" + report_id + graphParameter;
				// get xml url
				String reportData = getReportXml(report_id, reportName,
						menuParaCaption);// return required reports
											// information in xml
				System.out.println("xml url email - " + reportData);
				String URLString = "http://12.145.20.88/UALDIRECT/DirectLinkXMLXLS.aspx"; // get
																							// from
																							// database
				model.saveXmlURLtoFile(newFile, URLString, reportData);
			} else if (report_id != null) {
				if (graphParameter != null && !graphParameter.equals("")
						&& !graphParameter.equals("null")) {
					graphParameter = graphParameter.replace("3333", "&");
				} else {
					graphParameter = "";
				}
				String stringUrl = "report_id=" + report_id + graphParameter;
				String url = getText("analytics_url") + "?" + stringUrl;
				url = url.replaceAll(" ", "!");
				System.out.println("url is-- "
						+ URLDecoder.decode(url, "ISO-8859-1"));
				model.saveUrlToFile(newFile, url);
			}

			this.dashBean.setFileName(file);
			if (componetName != null) {
				this.dashBean.setDefoultSubject(componetName);
			} else if (reportName != null) {
				this.dashBean.setDefoultSubject(reportName);
			}
			dashBoardLog();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "sendMail";
	}

	/**
	 * Method Name :sendEmail() Used to Email ReportMenu
	 * 
	 * @return String
	 * 
	 */
	public String sendEmail() {
		String input = "";
		try {

			logger.info("SEND MAIL111::");
			String backAction = this.dashBean.getActionBack();
			Object[] toEmailIds = null;
			String toMail = this.dashBean.getMailToEmployeeEmail();
			try {
				if (toMail.charAt(toMail.length() - 1) == ';') {
					toMail = toMail.substring(0, toMail.length() - 1);
				}
				toEmailIds = (Object[]) toMail.split(";");
			} catch (Exception e) {
			}
			Object[] ccMailIds = null;
			if (!this.dashBean.getCcEmailIds().equals("")) {
				ccMailIds = this.dashBean.getCcEmailIds().split(";");
			}

			String emailSubject = this.dashBean.getSubject();
			String emailDescription = this.dashBean.getDescriptionBr();

			System.out.println("Discription Containts---" + emailDescription);
			System.out.println("description" + emailDescription);
			String reportPath = this.dashBean.getAttachmentPath();
			logger.info("reportPath :::" + reportPath);
			Object[] totalEmailIds = null;
			if (toEmailIds != null && toEmailIds.length > 0) {
				totalEmailIds = toEmailIds;
			}
			if (ccMailIds != null && ccMailIds.length > 0) {
				totalEmailIds = ccMailIds;
			}
			if ((toEmailIds != null && toEmailIds.length > 0)
					&& (ccMailIds != null && ccMailIds.length > 0)) {
				totalEmailIds = Utility.joinOneDimArray(toEmailIds, ccMailIds);
			}
			String[] mailToEmailIds = null;
			if (totalEmailIds != null && totalEmailIds.length > 0) {
				mailToEmailIds = new String[totalEmailIds.length];
				for (int i = 0; i < totalEmailIds.length; i++) {
					mailToEmailIds[i] = String.valueOf(totalEmailIds[i]);
					logger.info("mailToEmailIds::" + mailToEmailIds[i]);
				}
			}

			String[] fromEmail1 = null;
			final MailUtility mailModel1 = new MailUtility();
			mailModel1.initiate(context, session);

			System.out.println("this.dashBean.getUserEmpId()      "
					+ this.dashBean.getUserEmpId());
			String fromMailId = null;

			try {
				fromMailId = (String) session.getAttribute("ClientEmailID");
				System.out.println("-- the client id is : " + fromMailId);
			} catch (Exception e) {
				fromMailId = null;
			}
			if (fromMailId != null && !fromMailId.equals("")) {

				fromEmail1 = new String[] { fromMailId };
			} else {
				System.out.println("-- going for default ID");
				fromEmail1 = getDefaultMailIds(this.dashBean.getUserEmpId());
			}
			System.out.println("from mail id --" + fromEmail1);

			SendMailModel sendMail = new SendMailModel();
			sendMail.initiate(context, session);
			emailDescription = sendMail.getMassMessage(emailDescription);
			System.out.println("Discription Containts---" + emailDescription);
			if (fromEmail1 != null && fromEmail1.length > 0) {
				try {
					mailModel1.sendMail(mailToEmailIds, fromEmail1,
							emailSubject, emailDescription, reportPath, null,
							false);
					deleteFile(reportPath);
					this.dashBean.setMailSendMsg("Mail sent successfully.");
				} catch (Exception e) {
					this.dashBean.setMailSendMsg("Error sending email.");
				}
			} else {
				this.dashBean.setSentFlag("false");
				addActionMessage("E-mail id is not defined for you in official details");
			}
			request.setAttribute("action", backAction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "emailClosed";

	}

	/**
	 * Method Name :f9employeeToMail() Used to intellisense contact mail ID
	 * 
	 * @return String
	 * 
	 */
	public final String f9employeeToMail() {
		try {
			String query = "SELECT distinct NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ')"
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID)";
			query += " AND HRMS_EMP_ADDRESS.ADD_TYPE='P' ";
			System.out.println("Query emp toMail is--------" + query);
			String[] headers = { "Email Address Book" };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "mailToEmployeeEmail" };
			int[] columnIndex = { 0 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * Method Name :f9employeeCCMail() Used to intellisense contact mail ID
	 * 
	 * @return String
	 * 
	 */
	public final String f9employeeCCMail() {
		try {
			String query = "SELECT distinct NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ')"
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID)";
			// query += getprofileQuery(this.dashBean);
			query += " AND HRMS_EMP_ADDRESS.ADD_TYPE='P'  ";
			String[] headers = { "Email Address Book" };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "ccEmailIds" };
			int[] columnIndex = { 0 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * Method Name :getDefaultMailIds() Used to intellisense contact mail ID
	 * 
	 * @return String
	 * 
	 */
	public String[] getDefaultMailIds(String empId) {
		MailModel model = new MailModel();
		model.initiate(context, session);
		String fromQuery = "SELECT NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ') FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID"
				+ " AND HRMS_EMP_ADDRESS.ADD_TYPE='P') WHERE HRMS_EMP_ADDRESS.ADD_EMAIL IS NOT NULL AND HRMS_EMP_ADDRESS.EMP_ID= "
				+ empId;
		System.out.println("From DefaultMail Id---" + fromQuery);
		Object fromEmp[][] = model.getSqlModel().getSingleResult(fromQuery);

		if (fromEmp != null && fromEmp.length > 0) {
			String[] mailIds = new String[fromEmp.length];
			int fromEmplen = fromEmp.length;
			for (int i = 0; i < fromEmplen; i++) {
				mailIds[i] = String.valueOf(fromEmp[i][0]);
			}
			return mailIds;
		} else
			return null;
	}

	/**
	 * Method Name :deleteFile() used to delete file after sending Email
	 * 
	 * @parameter reportPath;
	 * @return void
	 * 
	 */

	public void deleteFile(String reportPath) {
		File file = new File(reportPath);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * Method Name :deleteFile() used to delete file after sending Email
	 * 
	 * @parameter reportPath;
	 * @return String
	 * 
	 */
	// set Email scheduler from MenuList
	public String getReportScheduler() {
		String reportID = request.getParameter("reportID");
		String reportName = request.getParameter("reportName");
		this.dashBean.setHiddenJobCode(reportID);
		DashBoardModel model = new DashBoardModel();
		model.initiate(context, session);
		String jobClient = (String) session.getAttribute("session_pool");
		String jobName = "DashBoard_" + reportID;
		HashMap<String, String> jobDetails = JobScheduler.getJobDetails(
				jobClient, jobName, jobGroup);
		model.setData(dashBean, jobDetails);
		this.dashBean.setReportName(reportName);
		this.dashBean.setStartShedulerFlag(false);
		return "reportScheduler";

	}

	// Email Scheduler start
	public String save() {
		try {

			DashBoardModel model = new DashBoardModel();
			model.initiate(context, session);
			String jobStartTime = request.getParameter("jobStartTime");
			String jobDuration = request.getParameter("jobDuration");
			String jobDayOfWeek = request.getParameter("jobDayOfWeek");
			String jobDayOfMonth = request.getParameter("jobDayOfMonth");

			String autoJobID = dashBean.getHiddenJobCode();

			String message = addUpdateJob("UPDATE", autoJobID, jobStartTime,
					jobDuration, jobDayOfWeek, jobDayOfMonth);

			if (message.equalsIgnoreCase("SAVED")) {
				addActionMessage("Record saved successfully");
				this.dashBean.setStartShedulerFlag(true);
			} else {
				addActionMessage("Record can not be saved");
			}

			if (message.equalsIgnoreCase("SAVED")) {
			} else {
				addActionMessage("Record can not be saved");
			}
			// setItteratorData();
			model.terminate();
		} catch (Exception e) {
		}
		return SUCCESS;

	}

	private String addUpdateJob(String typeOfTransaction, String autoUploadID,
			String jobStartTime, String jobDuration, String jobDayOfWeek,
			String jobDayOfMonth) {
		String message = "";
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String jobName = "DashBoard_" + autoUploadID;

			boolean result = false;

			/**
			 * Update job
			 */
			if (typeOfTransaction.equalsIgnoreCase("UPDATE")) {

				logger
						.info("In update Logic-----------------------------------------");
				result = JobScheduler.deleteJob(jobClient, jobName, jobGroup);

				logger.info("result-------------------------------    "
						+ result);
				if (result) {
					addActionMessage("Deleted");
				} else {
					addActionMessage("not Deleted");
				}

				result = JobScheduler.addJob(jobClient, jobName, jobGroup,
						jobClass, jobStartTime, jobDuration, jobDayOfWeek,
						jobDayOfMonth);

				if (result) {
					message = "SAVED";
				}

			}
		} catch (Exception e) {
			logger.error("Exception in editJobInScheduler:" + e);
			e.printStackTrace();
		}
		return message;
	}

	public String startScheduling() {
		try {
			String jobClient = (String) session.getAttribute("session_pool");

			String autoJobID = this.dashBean.getHiddenJobCode().trim();
			logger.info("HiddenJobCode is" + autoJobID);
			String jobName = "DashBoard_" + autoJobID;
			boolean isJobScheduled = DashBoardJobScheduler.startJobScheduling(
					jobClient, jobName, jobGroup);
			if (isJobScheduled) {
				addActionMessage("Scheduler started successfully.");
			} else {
				addActionMessage("Scheduler cannot be started.");
			}
			this.dashBean.setStartShedulerFlag(false);
			this.dashBean.setStopShedulerFlag(true);
		} catch (Exception e) {
			logger.error("Exception in startScheduling in action:" + e);
			e.printStackTrace();
			addActionMessage("Scheduler cannot be started.");
		}
		return "reportScheduler";
	}

	public String stopScheduling() {
		try {
			String jobClient = (String) session.getAttribute("session_pool");
			String autoJobID = this.dashBean.getHiddenJobCode().trim();
			String jobName = "AlertMessage_" + autoJobID;
			boolean result = DashBoardJobScheduler.stopJobScheduling(jobClient,
					jobName, jobGroup);
			if (result) {
				addActionMessage("Scheduler stopped successfully.");
			} else {
				addActionMessage("Scheduler cannot be stopped.");
			}
			this.dashBean.setStartShedulerFlag(true);
			this.dashBean.setStopShedulerFlag(false);

		} catch (Exception e) {
			logger.error("Exception in stopScheduling in action:" + e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// for resetting fi
	public String reset() {
		try {

			this.dashBean.setJobDuration("");
			this.dashBean.setJobDayOfWeek("");
			this.dashBean.setJobDayOfMonth("");
			this.dashBean.setJobStartTime("");
			this.dashBean.setModuleName("");
			this.dashBean.setEmployeeToken("");
			this.dashBean.setEmployeeName("");
			this.dashBean.setEmployeeCode("");
			this.dashBean.setJobQueryType("");
			this.dashBean.setSubject("");
		} catch (Exception e) {
		}
		return "success";
	}

	public void getUserDocument() throws Exception {
		String DocFile = (String) request.getParameter("FileLoc");
		String DocFileName = (String) request.getParameter("fileName");
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = DocFile;
		try {
			String path = DocFile;
			oStream = response.getOutputStream();
			String mimeType = "";
			String fileName1 = DocFile;
			fileName = fileName.replace(".", "#");
			String[] extension = fileName.split("#");
			String ext = extension[extension.length - 1];
			fileName = fileName.replace("#", ".");
			logger.info("extext--->>>" + ext);
			if (ext.equals("pdf")) {
				mimeType = "application/pdf ";
			} // end of if
			else if (ext.equals("doc")) {
				mimeType = "application/msword";
			} // end of else if
			else if (ext.equals("xls")) {
				mimeType = "application/msexcel";
			} // end of else if
			else if (ext.equals("xlsx")) {
				mimeType = "application/msexcel";
			} // end of else
			else if (ext.equals("jpg")) {
				mimeType = "application/jpg";
			} // end of else if
			else if (ext.equals("txt")) {
				mimeType = "text/html";
			} // end of else if
			else if (ext.equals("gif")) {
				mimeType = "application/gif";
			} else if (ext.equals("ppt")) {
				mimeType = "application/vnd.ms-powerpoint";
			} else if (ext.equals("docx")) {
				mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			} else if (ext.equals("pptx")) {
				mimeType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
			}
			// end of else if
			// if file name is null, open a blank document
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				} // end of if
			} // end of if

			response.setHeader("Content-type", mimeType);
			response.setHeader("Content-disposition", "inline;filename=\""
					+ DocFileName + "." + ext + "\"");
			int iChar;
			fsStream = new FileInputStream(path);
			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			addActionMessage("File not found");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
	}

	public String getReport() {
		String reportID = request.getParameter("report_id");
		String reportName = request.getParameter("reportName");
		reportName=reportName.replace(" ", "_");
		String paraCaption = "";
		try {
			paraCaption = request.getParameter("paraCaption");
		} catch (Exception e) {
		}
		/**
		 * Code to get data from Webservice
		 */

		String xmlData = getReportXml(reportID, reportName, paraCaption);
		System.out.println("final xml String - " + xmlData);
		try {

			String URLString = "http://12.145.20.88/UALDIRECT/DirectLinkXMLXLS.aspx"; // get
																						// from
																						// database
			xmlData = java.net.URLEncoder.encode(xmlData, "UTF-8");
			URL url = new URL(URLString);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection
					.getOutputStream());
			out.write(xmlData);
			out.close();
			 Random rand = new Random();

			    // nextInt is normally exclusive of the top value,
			    // so add 1 to make it inclusive
			    int randomNum = rand.nextInt((999999) + 1);
			
			
			response.setHeader("Content-disposition", "inline;filename=\""
					+ reportName+"_"+randomNum
					+".xls\"");
			response
					.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.getOutputStream().write(
					toByteArrayUsingJava(connection.getInputStream()));

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * To read file in byte
	 */
	public static byte[] toByteArrayUsingJava(InputStream is)
			throws IOException {
		java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
		int reads = is.read();
		while (reads != -1) {
			baos.write(reads);
			reads = is.read();
		}
		return baos.toByteArray();
	}

	public String getReportXml(String reportID, String reportName,
			String paraCaption) {
		String xmlString = "";
		try {
			String[] reportIDSplit = null;
			String[] paraCap = null;
			if (reportID != null && !reportID.equals("")
					&& !reportID.equals("null")) {
				reportID = reportID.replaceAll("`", "&");
			}
			if (reportID != null) {
				reportIDSplit = reportID.split("&");
				// Getting ReportID for DashBoardLog
				this.dashBean.setReportID(reportIDSplit[0]);
			}
			paraCap = paraCaption.split("`");
			xmlString = "<report>" + "<auth>" + "<user>UserName</user>"
					+ "<password>password</password>" + "</auth>"
					+ "<report_id>" + reportIDSplit[0] + "</report_id>"
					+ "<report_name>" + reportName + "</report_name>"
					+ "<workbook>";
			String[] reportIDSplit1 = reportIDSplit[0].split("=");
			DashBoardModel model = new DashBoardModel();
			model.initiate(context, session);
			Object[][] sheetinfo = model
					.getReportxmlParameter(reportIDSplit1[1]);
			int sheetinfoSize = sheetinfo.length;
			for (int i = 0; sheetinfoSize > i; i++) {
				xmlString = xmlString + "<worksheet>";
				// for(int j=0;sheetinfo[0].length>j;j++ ){
				xmlString = xmlString + "<sheet_num>" + sheetinfo[i][0]
						+ "</sheet_num>" + "<sheet_caption>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][1]))
						+ "</sheet_caption>" + "<width>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][6]))
						+ "</width>" + "<component>" + "<sheet_matrix_loc>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][2]))
						+ "</sheet_matrix_loc>" + "<id>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][3]))
						+ "</id>" + "<type>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][4]))
						+ "</type>" + "<mode>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][8]))
						+ "</mode>" + "<component_caption>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][9]))
						+ "</component_caption>" + "<graph_type>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][7]))
						+ "</graph_type>";
				String query = String.valueOf(sheetinfo[i][5]);
				query = query.replaceAll("<", "&lt;");
				query = query.replaceAll(">", "&gt;");

				xmlString = xmlString + "<query>" + query + "</query>"
						+ " <DBName>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][10]))
						+ "</DBName>" + "</component>";
				xmlString = xmlString + "</worksheet>";
			}
			xmlString = xmlString + "</workbook>" + "<parameters>";
			int reportIDSplitlen = reportIDSplit.length;
			for (int i = 0; i < reportIDSplitlen; i++) {
				xmlString = xmlString + "<param>";
				String[] reportnamenvalue = reportIDSplit[i].split("=");
				xmlString = xmlString + "<name>" + reportnamenvalue[0]
						+ "</name>";
				if (reportnamenvalue.length > 1) {
					xmlString = xmlString
							+ "<value>"
							+ (reportnamenvalue[1] != null ? reportnamenvalue[1]
									: "");
				} else {
					xmlString = xmlString + "<value>" + "";
				}
				xmlString = xmlString + "</value>";
				if (i != 0) {
					i = i - 1;
					xmlString = xmlString + "<caption>" + paraCap[i]
							+ "</caption>";
					i = i + 1;
				}
				xmlString = xmlString + "</param>";
			}
			xmlString = xmlString + "</parameters>" + "</report>";
			System.out.println("Report XmlString - " + xmlString);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlString;
	}

	public String getComponentXml(String componentID, String query,
			String componentName, DashBoard bean) {
		String xmlString = "";
		try {
			String[] reportIDSplit = null;
			xmlString = "<report>" + "<auth>" + "<user>UserName</user>"
					+ "<password>password</password>" + "</auth>"
					+ "<report_id>" + componentID + "</report_id>"
					+ "<report_name>" + componentName + "</report_name>"
					+ "<workbook>";

			DashBoardModel model = new DashBoardModel();
			model.initiate(context, session);
			Object[][] sheetinfo = model.getComponentxmlParameter(componentID);
			for (int i = 0; sheetinfo.length > i; i++) {
				xmlString = xmlString + "<worksheet>";
				// for(int j=0;sheetinfo[0].length>j;j++ ){
				xmlString = xmlString + "<sheet_num>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][0]))
						+ "</sheet_num>" + "<sheet_caption>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][1]))
						+ "</sheet_caption>" + "<width>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][6]))
						+ "</width>" + "<component>" + "<sheet_matrix_loc>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][2]))
						+ "</sheet_matrix_loc>" + "<id>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][3]))
						+ "</id>" + "<type>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][4]))
						+ "</type>" + "<mode>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][8]))
						+ "</mode>" + "<component_caption>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][9]))
						+ "</component_caption>" + "<graph_type>"
						+ Utility.checkNull(String.valueOf(sheetinfo[i][7]))
						+ "</graph_type>";
				if (query != null && !query.equals("")) {
					query = query.replaceAll("<", "&lt;");
					query = query.replaceAll(">", "&gt;");
				} else {
					query = String.valueOf(sheetinfo[i][5]);
					query = query.replaceAll("<", "&lt;");
					query = query.replaceAll(">", "&gt;");
				}

				xmlString = xmlString + "<query>" + query + "</query>"
						+ "</component>";
				// }
				xmlString = xmlString + "</worksheet>";
			}
			xmlString = xmlString + "</workbook>" + "<parameters>";

			Object[][] configPara = model.getConfigurePara(bean);

			String parameterString = "";
			for (int i = 0; i < configPara.length; i++) {

				parameterString = parameterString + "<param><name>"

				+ Utility.checkNull(String.valueOf(configPara[i][0]))
						+ "</name>";
				if (String.valueOf(configPara[i][0]).equals("ACCOUNT_CODE")) {
					parameterString = parameterString + "<value>"

					+ Utility.checkNull(String.valueOf(bean.getAccountID()))
							+ "</value>";
				} else if (String.valueOf(configPara[i][0])
						.equals("ACCOUNT_ID")) {
					parameterString = parameterString + "<value>"

					+ Utility.checkNull(String.valueOf(bean.getAccountName()))
							+ "</value>";
				} else {
					parameterString = parameterString
							+ "<value>"
							+ Utility.checkNull(String
									.valueOf(configPara[i][1])) + "</value>";
				}

				parameterString = parameterString + "</param>";
			}
			xmlString = xmlString + parameterString + "</parameters>"
					+ "</report>";
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return xmlString;
	}

	public String callMIS() {
		DashBoardModel model = new DashBoardModel();
		model.initiate(context, session);
		model.getSenerioList(this.dashBean);
		model.terminate();
		return "callMIS";
	}
	
	
	
	
	/*public String getObjMIS() {
		DashBoardModel model = new DashBoardModel();
		model.initiate(context, session);
		String scenerioId = request.getParameter("scenerioId");
		model.getscenerioInfo(scenerioId, this.dashBean);
		String dataQueryStr = model.getFilterDrilDownQuery(this.dashBean
				.getCompoNo(), "", "", this.dashBean);
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String response = null;
		String componetXml = getComponetXml(this.dashBean.getCompoNo(),
				dataQueryStr, this.dashBean);
		
		 * preparing xml String
		 
		try {
			componetXml = java.net.URLEncoder.encode(componetXml, "UTF-8");
			URL url = new URL(
					"http://12.145.20.88/UALDIRECT/DirectLinkXMLData.aspx");
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(180000);
			connection.setReadTimeout(180000);
			connection.setDoOutput(true);
			String charset = "UTF-8";
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "text/xml");
			OutputStreamWriter out = new OutputStreamWriter(connection
					.getOutputStream());
			out.write(componetXml);
			out.close();
			StringBuilder sb = new StringBuilder();
			String line = null;

			try {
				reader = new BufferedReader(new InputStreamReader(connection
						.getInputStream()));
			} catch (IOException e) {
				InputStream errorIp = connection.getErrorStream();
				if (errorIp != null) {
					BufferedReader errorReader = new BufferedReader(
							new InputStreamReader(errorIp));
					while ((line = errorReader.readLine()) != null) {
						sb.append(line);
					}
					System.out.println("Got Error Response: " + sb.toString());
				}
				throw e;
			}

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			response = sb.toString();
			System.out.println("responseggg--" + response);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String xmlDataString = response;// .substring(3, xmlData.length());
		List compList = null;
		try {
			compList = model.getObjectFromXml(xmlDataString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Object[][] tableObj = (Object[][]) compList.get(0);
		model.getSenerioList(this.dashBean);
		//if(dashBean.getGraphEnable().equals("Y")){
		String ChartString = model.getChartInfo(dataQueryStr, tableObj,
				this.dashBean.getGraphType(), "", "", this.dashBean
						.getCompoNo());
		request.setAttribute("ChartString", ChartString);
		//}
		request.setAttribute("tableObj", tableObj);
		
		model.terminate();
		return "callMIS";
	}*/

	public String getComponetXml(String componetNo, String componentQuery,
			DashBoard bean) {
		DashBoardModel model = new DashBoardModel();
		model.initiate(context, session);
		String xmlStr = "  <d1-Graph>	" + "<auth>" + "<user>" + "UserName"
				+ "</user>" + "<password>" + "password" + "</password>"
				+ "</auth>";
		String query = String.valueOf(componentQuery);
		query = query.replaceAll("<", "&lt;");
		query = query.replaceAll(">", "&gt;");

		xmlStr += "<component>" + "<componentid>" + componetNo + // componentId+
				"</componentid>" + "<query>" + query + // to add the
														// queryString
				"</query>" + "</component>";
		Object[][] configPara = model.getConfigurePara(bean);
		xmlStr = xmlStr + "<parameters>";
		String parameterString = "";
		for (int i = 0; i < configPara.length; i++) {
			parameterString = parameterString + "<param><name>"
					+ Utility.checkNull(String.valueOf(configPara[i][0]))
					+ "</name>";
			if (String.valueOf(configPara[i][0]).equals("ACCOUNT_CODE")) {
				parameterString = parameterString
						+ "<value>"
						+ Utility
								.checkNull(String.valueOf(bean.getAccountID()))
						+ "</value>";
			} else if (String.valueOf(configPara[i][0]).equals("ACCOUNT_ID")) {
				parameterString = parameterString
						+ "<value>"
						+ Utility.checkNull(String.valueOf(bean
								.getAccountName())) + "</value>";
			} else {
				parameterString = parameterString + "<value>"
						+ Utility.checkNull(String.valueOf(configPara[i][1]))
						+ "</value>";
			}
			parameterString = parameterString + "</param>";
		}
		xmlStr = xmlStr + parameterString + "</parameters>";
		xmlStr = xmlStr + " </d1-Graph> ";
		return xmlStr;
	}

	public void getComponetReport() {
		DashBoardModel model = new DashBoardModel();
		model.initiate(context, session);
		String scenerioId = request.getParameter("scenerioId");
		model.getscenerioInfo(scenerioId, this.dashBean);
		String dataQueryStr = model.getFilterDrilDownQuery(this.dashBean
				.getCompoNo(), "", "", this.dashBean);
		String xmlData = getComponentXml(this.dashBean.getCompoNo(),
				dataQueryStr, this.dashBean.getComponentName(), this.dashBean);
		System.out.println("final xml String - " + xmlData);
		String URLString = "http://12.145.20.88/UALDIRECT/DirectLinkXMLXLS.aspx"; // get // from// database
		try {
			xmlData = java.net.URLEncoder.encode(xmlData, "UTF-8");
			URL url = new URL(URLString);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection
					.getOutputStream());
			out.write(xmlData);
			out.close();
			response.setHeader("Content-disposition", "inline;filename="
					+ this.dashBean.getComponentName());
			response
					.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

			response.getOutputStream().write(
					toByteArrayUsingJava(connection.getInputStream()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.terminate();
	}

}
