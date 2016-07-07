/**
 * 
 */
package org.struts.action.ApplicationStudio;

import java.lang.reflect.Method;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.lib.StringEncrypter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.ApplicationStudio.OnlineApprovalModel;
import org.paradyne.model.common.LoginModel;
import org.paradyne.model.common.MenuModel;

/**
 * @author Lakkichand_Golegaonkar
 * @date 11-August-2009
 * 
 */
public class OnlineApproveReject extends ActionSupport implements ModelDriven,
		Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {
	LoginBean loginBean = null;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OnlineApproveReject.class);

	public ActionContext actionContext = ActionContext.getContext();

	public HttpServletRequest request;

	public HttpServletResponse response;

	private ServletContext context;

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	public ServletContext getServletContext() {
		return context;
	}

	public Object getModel() {
		return loginBean;
	}

	public void prepare() throws Exception {
		loginBean = new LoginBean();
	}

	public String approveRejectApp() throws Exception {
		String str = "";

		String url_param = request.getParameter("parameter");
		String pool = request.getParameter("pool");
		HttpSession session = request.getSession();
		try {
			pool = PPEncrypter.decrypt(pool);
			url_param = PPEncrypter.decrypt(url_param);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		request.getSession().setAttribute("session_pool", pool);
		StringTokenizer tokens = new StringTokenizer(url_param, "#");
		int counter = 0;
		String applicationDtls = "";
		String newString = "";
		while (tokens.hasMoreElements()) {
			String token = tokens.nextToken();
			if (token.equals("applicationDtls")) {
				System.out.println("if  applicationDtls ..");
				applicationDtls = tokens.nextToken();
				break;
			} else {
				newString += token + "#";
			}
		}
		newString = newString.substring(0, newString.length() - 1);
		
		StringTokenizer newTokens = new StringTokenizer(newString, "#");
		String[] parameters = new String[newTokens.countTokens()];
		while (newTokens.hasMoreElements()) {
			String newToken = newTokens.nextToken();
			parameters[counter++] = newToken;
		}
		OnlineApprovalModel model = new OnlineApprovalModel();
		model.initiate(context, session);
		// String result = model.checkOnlineAuth(parameters[0]);
		System.out.println("applicationDtls....."+applicationDtls);
		if (!applicationDtls.equals("")) {
			System.out.println(" parameters[0]" + parameters[0]);
			request.setAttribute("approverId", parameters[1]);
			request.setAttribute("applicationDtls", applicationDtls);
		}

		model.terminate();

		/*
		 * Link to Confirmation...
		 */
		try {
			pool = PPEncrypter.encrypt(pool);
			newString = PPEncrypter.encrypt(newString);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		String urlApproval = "pool=" + pool + "&parameter=" + newString;
		if(!applicationDtls.equals("")){
			urlApproval+="&applicationDtls="+applicationDtls;
		}
		request.setAttribute("urlApproval", urlApproval);
		
		return "approvalConfirm";
	}

	public String approveReject() throws Exception {

		String url_param = "";
		String pool = request.getParameter("pool");

		String[] applicationDtls = null;
		String[] approverId = null;
		String[] username = null;
		String[] password = null;
		String[] commentText = null;
		String application_link="";
		
		try {
			approverId = request.getParameterValues("approverId");
			username = request.getParameterValues("username");
			password = request.getParameterValues("password");
			commentText = request.getParameterValues("commentText");
			applicationDtls =request.getParameterValues("applicationDtls");
			
			logger.info("username   :" + username[0]);
			logger.info("password   :" + password[0]);
			logger.info("approverId   :" + approverId[0]);
			logger.info("approverId   :" + applicationDtls[0]);

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			url_param = request.getParameter("parameter");	
			System.out.println("url_param   "+url_param);
			application_link = applicationDtls[0];
			System.out.println("application_link~~~~~~~~"+application_link);
		} catch (Exception e) {

		}
		HttpSession session = request.getSession();
		logger.info("url_param   :" + url_param);

	
		
		try {
			pool = PPEncrypter.decrypt(pool);
			url_param = PPEncrypter.decrypt(url_param);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		StringTokenizer tokens = new StringTokenizer(url_param, "#");
		int paramLength = tokens.countTokens();
		int counter = 0;

		String[] parameters = new String[paramLength];

		while (tokens.hasMoreElements()) {
			String token = tokens.nextToken();
			if (token.equals("...")) {
				
				try {
					token = commentText[0];
				} catch (Exception e) {
					// TODO: handle exception
					token="NoText";
				}
				
			}
			parameters[counter++] = token;
		}

		/**
		 * First Parameter should be poolName used to set the connection pool
		 */
		session.setAttribute("session_pool", pool);
		logger.info("pool     :" + pool);
		for (int i = 0; i < parameters.length; i++) {
			logger.info("parameters[" + i + "]" + parameters[i]);
		}
		OnlineApprovalModel model = new OnlineApprovalModel();
		model.initiate(context, session);
		boolean userFlag = false;
		if (approverId != null && approverId.length > 0) {
			logger.info("approverId     ");

			userFlag = model.checkUser( approverId[0],username[0], password[0]);

			/** ************************************************************* */
			if (userFlag) {
				if (application_link != null && !application_link.equals("")) {
					LoginModel loginModel = new LoginModel(loginBean);
					loginModel.initiate(context, session);
					password[0] = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
							.encrypt(password[0]);
					Object loginData[][] = (Object[][]) loginModel
							.selectLoginData(username[0], password[0]);

					
					String logo = null;
					String ppurl = null;
					Object data[][] = null;
					String comanyName = null;
					try {
						data = loginModel.getComponyLogo();
						if (data != null && data.length > 0) {
							logo = String.valueOf(data[0][0]);
							ppurl = String.valueOf(data[0][1]);
							comanyName = String.valueOf(data[0][2]);
						}

					} catch (Exception e) {
						logger.error("Exception in getComponyLogo--------" + e);
					}
					
					String empDivisionName =loginModel.getEmployeeDivisionName(String
							.valueOf(loginData[0][1]));
					request.setAttribute("empDivisionName", empDivisionName);
					request.setAttribute("comanyName", comanyName);
					request.setAttribute("logo", logo);
					
					String str = request.getRemoteAddr();
					String str1 = str.replace(".", "#");
					String[] strSpilt = str1.split("#");
					// logger.info("login 2----------");
					int strPort = request.getRemotePort();
					/*  loginModel.saveLoginSession(String
							.valueOf(loginData[0][3]), strSpilt[0],
							strSpilt[1], strSpilt[2], strSpilt[3], strPort);*/

					LoginBean loginBean_session = new LoginBean();
					loginBean_session.setEmpId(String.valueOf(loginData[0][1]));
					loginBean_session.setProfileCode(String
							.valueOf(loginData[0][2]));
					loginBean_session.setAccessProfileCode(String
							.valueOf(loginData[0][6]));
					loginBean_session.setLoginCode(String
							.valueOf(loginData[0][3]));
					//Added by Prajakta B(4 june 2013) for multiple profile code
					loginBean.setMultipleProfileCode(String
							.valueOf(loginData[0][7]));
					// Ends Added by Prajakta B(4 june 2013) for multiple profile code
					/*loginBean_session
							.setVisitorNo(String.valueOf(result[0][0]));*/
					loginBean_session.setLoginName(username[0]);
					model.terminate();

				/*	String time = String.valueOf(result[0][1]).substring(
							String.valueOf(result[0][1]).length() - 8,
							String.valueOf(result[0][1]).length());
					String dat = String.valueOf(result[0][1]).substring(0,
							String.valueOf(result[0][1]).length() - 8);*/
					/*loginBean_session.setLoginDate(dat);
					loginBean_session.setLoginTime(time);*/
					session.setAttribute("loginBeanData", loginBean_session);
					session.setAttribute("empId", String
							.valueOf(loginData[0][1]));
					session.setAttribute("lname", username[0]);
					session.setAttribute("poolId", loginBean.getInfoId());
					session.setMaxInactiveInterval(120);
					/*Object[][] theme = loginModel.setMyTheme(loginBean);
					System.out
							.println("application_link  ..." + application_link);
					application_link=application_link.replace('$', '&');
					System.out
					.println("application_link.replace"+ application_link);
					session.setAttribute("homePage", application_link);
					if (theme != null && theme.length > 0) {
						session.setAttribute("themeName", String
								.valueOf(theme[0][0]));
						session.setAttribute("fontName", String
								.valueOf(theme[0][1]));
						session.setAttribute("fontSize", String
								.valueOf(theme[0][2]));
						session.setAttribute("homePage",application_link);
						session.setAttribute("homeCode", String
								.valueOf(theme[0][4]));
					} else {
						session.setAttribute("themeName", "peoplePower");
						session.setAttribute("fontName", "Arial");
						session.setAttribute("fontSize", "11");
					}*/
					createMainMenu();
					return "gotoApplication";
				}
			}
			/** **************************************************************************** */
			if (userFlag) {
				return callApproveReject(parameters);
			} else {
				/*
				 * Link to Confirmation...
				 */
				try {
					pool = PPEncrypter.encrypt(pool);
					url_param = PPEncrypter.encrypt(url_param);

				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
				String urlApproval = "pool=" + pool + "&parameter=" + url_param;
				if(!applicationDtls.equals("")){
					urlApproval+="&applicationDtls="+application_link;
				}
				request.setAttribute("urlApproval", urlApproval);

				request.setAttribute("notValid", "Invalid Username/Password.");
				request.setAttribute("approverId", approverId[0]);
				request.setAttribute("applicationDtls",application_link);

				return "approvalConfirm";
			}
		} else {
			return callApproveReject(parameters);
		}

	}

	/**
	 * 
	 * @param parameters
	 * @return
	 */
	public String callApproveReject(String[] parameters) {

		/**
		 * For Reading the relative Class and method
		 * 
		 */
		String orgXmlFile = getText("data_path")
				+ "\\OnlineApproval\\online_approval.xml";

		File file = new File(orgXmlFile);

		System.out.println(file.exists());
		Document document;
		Object[][] objData = null;
		String applicationType = parameters[0];
		try {
			document = new XMLReaderWriter().parse(file);
			List nodes = document.selectNodes("//ORGANISATION/"
					+ applicationType);
			objData = new Object[nodes.size()][3];
			int cnt = 0;

			for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
				Element data = (Element) it.next();
				objData[cnt][0] = String.valueOf(data.element("PARAM")
						.attributeValue("MODEL"));
				objData[cnt][1] = String.valueOf(data.element("PARAM")
						.attributeValue("METHOD"));
				objData[cnt][2] = String.valueOf(data.element("PARAM")
						.attributeValue("NO_OF_PARAM"));
				cnt++;
			}
		} catch (Exception e) {
			logger.info(" Exception....IN XML READING FOR ONLINE APPROVAL" + e);
			e.printStackTrace();
		}

		/**
		 * For Leave Application First Approval Mail
		 */

		/**
		 * Approve/Reject Using individual classes and their methods
		 * 
		 */
		int no_of_param = 0;
		String modelClassName = "";
		String methodName = "";
		if (objData != null && objData.length > 0) {
			modelClassName = String.valueOf(objData[0][0]);// "org.paradyne.model.leave.LeaveApprovalModel"
			methodName = String.valueOf(objData[0][1]);// "onlineApproveReject";
			no_of_param = Integer.parseInt(String.valueOf(objData[0][2]));// 6;//
		}

		try {
			Class[] paramClass = new Class[no_of_param + 1];
			Object[] params = new Object[no_of_param + 1];
			int count = 1;
			for (int i = 0; i < paramClass.length; i++) {
				if (i == 0) {
					paramClass[i] = HttpServletRequest.class;
					params[i] = request;
				} else {
					paramClass[i] = String.class;
					params[i] = parameters[count++];
				}

			}
			String approveResult = invoke(modelClassName, methodName,
					paramClass, params, request);
			request.setAttribute("result", approveResult);

		} catch (Exception e) {
			logger.info(" Exception....3" + e);
			e.printStackTrace();

		}
		return "approveRejectApp";

	}

	/**
	 * @author Lakkichand_Golegaonkar
	 * @param aClass
	 * @param aMethod
	 * @param params
	 * @param args
	 * @param context
	 * @param session
	 * @return String
	 */
	public static String invoke(String aClass, String aMethod, Class[] params,
			Object[] args, HttpServletRequest request) {
		String result = "";
		try {
			Class modelClass = Class.forName(aClass);
			Method modelMethod = modelClass.getDeclaredMethod(aMethod, params);
			ModelBase modelInstance = (ModelBase) modelClass.newInstance();
			modelInstance.initiate(request.getSession().getServletContext(),
					request.getSession());
			result = (String) modelMethod.invoke(modelInstance, args);
			modelInstance.terminate();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return result;
	}

	public String createMainMenu() throws Exception {
		LoginBean lb = (LoginBean) request.getSession().getAttribute(
				"loginBeanData");
		String contextPath = request.getContextPath();
		Random random = new Random();
		//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE CODE
		String query = "SELECT distinct HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
				+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
				+ contextPath
				+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
				+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,MENU_PLACEINMENU,MENU_TABORDER "
				+ " FROM HRMS_MENU "
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
				+ " OR PROFILE_GENERAL_FLAG ='Y'))"
				+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN ("
				+ lb.getMultipleProfileCode()
				+ ")"
				+ " AND MENU_PARENT_CODE=0"
				+ " AND MENU_ISRELEASED='Y'"
				+ " ORDER BY MENU_TABORDER,HRMS_MENU.MENU_CODE";

		MenuModel model = new MenuModel();
		model.initiate(context, request.getSession());
		String[][] menuList = model.getMenuList(query);
		String poolName = "";

		try {

			poolName = String.valueOf(request.getSession().getAttribute(
					"session_pool"));
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\thought\\thought.xml";

			String[][] thoughtObj;
			String thought = "";
			try {

				thoughtObj = model.processThought(new XMLReaderWriter()
						.parse(new File(path)));
				String[] thouthId = new String[thoughtObj.length];
				for (int i = 0; i < thouthId.length; i++) {
					thouthId[i] = thoughtObj[i][1];
				}
				int randomValue = Integer
						.parseInt(String.valueOf(thoughtObj[random
								.nextInt(thouthId.length)][1]));

				for (int k = 0; k < thoughtObj.length; k++) {
					if (randomValue == Integer.parseInt(String
							.valueOf(thoughtObj[k][1]))) {
						logger
								.info("Thought of the Day is "
										+ thoughtObj[k][2]);
						thought = String.valueOf(thoughtObj[k][2]);
					}
				}

			} catch (Exception e) {
				thoughtObj = new String[0][0];
				e.printStackTrace();
			}

			Object[][] cmpName = model.getCmpName();
			String cmpanyName = null;
			try {
				cmpanyName = String.valueOf(cmpName[0][0]);
			} catch (Exception e) {

			}
			HttpSession session = request.getSession();

			LoginModel loginModel = new LoginModel(loginBean);
			loginModel.initiate(context, request.getSession());
			Object[][] lastAcctActivity = loginModel.getLastLoginTime(lb
					.getEmpId());
			Object[][] obj = loginModel.setLoginInfo(lb.getEmpId());
			if (obj != null && obj.length > 0) {
				request.setAttribute("UserID", String.valueOf(obj[0][0]));
				request.setAttribute("UserName", String.valueOf(obj[0][1]));
				request.setAttribute("thought", thought);
				session.setAttribute("lastAcctActivity", String
						.valueOf(lastAcctActivity[0][0]));
				request.setAttribute("gender", String.valueOf(obj[0][4]));
				String photo = String.valueOf(obj[0][2]);
				if (String.valueOf(obj[0][1]) != null && !photo.equals("")) {
					request.setAttribute("profilePhoto", String
							.valueOf(obj[0][3]));
				} else {
					request.setAttribute("profilePhoto", "empimage.gif");
				}
			}
			request.setAttribute("menuList", menuList);
			request.setAttribute("compName", cmpanyName);
			
		 
			model.terminate();
		} catch (Exception e) {

		}
		return "gotoApplication";
	}

}
