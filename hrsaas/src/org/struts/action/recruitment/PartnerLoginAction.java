package org.struts.action.recruitment;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.Recruitment.PartnerLogin;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.recruitment.PartnerLoginModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PartnerLoginAction extends ActionSupport implements ModelDriven,
		Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PartnerLoginAction.class);
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request;
	public HttpServletResponse response;
	public ServletContext context;
	PartnerLogin loginBean = null;

	public Object getModel() {
		return loginBean;
	}

	public void prepare() throws Exception {
		loginBean = new PartnerLogin();

	}

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

	
	public PartnerLogin getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(PartnerLogin loginBean) {
		this.loginBean = loginBean;
	}

	public String input() {
		try {
			String poolName = "";
			long startTime = System.currentTimeMillis();
			poolName = request.getParameter("infoId");
			poolName = "";
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(loginBean
					.getInfoId());
			PartnerLoginModel loginModel = new PartnerLoginModel();
			HttpSession session = request.getSession();
			session.setAttribute("session_pool", poolName);
			loginModel.initiate(context, session);
			String logo = null;
			logo = loginModel.getComponyLogo();
			long endTime = System.currentTimeMillis();
			double responseTime = (endTime - startTime) / 1000.0;
			request.setAttribute("respTime", "" + responseTime);
			request.setAttribute("logo", logo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public String submitUser() throws Exception {
		String result = "";
		try {
			PartnerLoginModel model = new PartnerLoginModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			String logo = null;
			logo = model.getComponyLogo();
			result = checkUserPassword();
			if (result.equals("invalid")) {
				String loginName = loginBean.getLoginName().trim();
				String password = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(loginBean.getLoginPassword().trim());
				String loginData = model.getLoginData(loginName, password, request);
				if(loginData.equals("InCorrectUserName")) {
					addActionMessage("Invalid username");
					loginBean.setLoginName("");
				}
				
				if(loginData.equals("InCorrectPassword")) {
					addActionMessage("Invalid password");
					loginBean.setLoginPassword("");
				}
			}
			request.setAttribute("logo", logo);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	String Actionmessage = "";

	public void addActionMessage(String message) {
		try {
			if (!message.equals("")) {
				Actionmessage = Actionmessage + "\n" + message;
				BeanBase applicationBean = (BeanBase) getModel();
				applicationBean.setActionMessage(Actionmessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void createMenu(HttpServletRequest request) {
		try {
			String contextPath = request.getContextPath();
			String query = "SELECT DISTINCT HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
					+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
					+ contextPath
					+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
					+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,MENU_PLACEINMENU,MENU_TABORDER "
					+ " FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE )"
					/*"AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ " OR PROFILE_GENERAL_FLAG ='Y'))"*/
					+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
					+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE =69"
					+ " AND MENU_PARENT_CODE=0"
					+ " AND MENU_ISRELEASED='Y'"
					+ " ORDER BY MENU_TABORDER,HRMS_MENU.MENU_CODE";
			String[][] strObj = null;
			PartnerLoginModel model = new PartnerLoginModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			Object[][] obj = model.getSqlModel().getSingleResult(query);
			if (obj != null) {
				strObj = new String[obj.length][5];

				for (int i = 0; i < obj.length; i++) {
					strObj[i][0] = String.valueOf(obj[i][0]);
					strObj[i][1] = String.valueOf(obj[i][1]);
					strObj[i][2] = String.valueOf(obj[i][2]);
					strObj[i][3] = String.valueOf(obj[i][3]);
					strObj[i][4] = String.valueOf(obj[i][4]);
				}
			}
			request.setAttribute("menuList", strObj);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkUserPassword() throws Exception {
		String forwardString = "invalid";
		try {
			String poolName = "";
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(loginBean
					.getInfoId());
			PartnerLoginModel model = new PartnerLoginModel();
			HttpSession session = request.getSession();
			session.setAttribute("session_pool", poolName);
			model.initiate(context, session);
			String loginName = loginBean.getLoginName().trim();
			String password = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(loginBean.getLoginPassword().trim());
			Object loginData[][] = (Object[][]) model.selectLoginData(loginName, password, request);
			if (loginData != null && loginData.length > 0) {
				PartnerLogin loginBean_session = new PartnerLogin();
				loginBean_session.setLoginName(String.valueOf(loginData[0][0]));
				loginBean_session.setEmpId(String.valueOf(loginData[0][1]));
				loginBean_session.setEmailId(String.valueOf(loginData[0][2]));
				loginBean_session.setProfileCode("69");
				session.setAttribute("loginBeanData", loginBean_session);
				session.setAttribute("empId", String.valueOf(loginData[0][1]));
				session.setAttribute("lname", loginName);
				session.setAttribute("poolId", loginBean.getInfoId());
				request.setAttribute("UserName", String.valueOf(loginData[0][3]));
				forwardString = "success";
			} else {
				forwardString = "invalid";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (forwardString.equals("success")) {
			createMenu(request);
			return SUCCESS;
		} else {
			return forwardString;
		}
	}

	public String logout() throws Exception {
		try {
			HttpSession session = request.getSession();
			String str = (String) session.getAttribute("session_pool");
			String poolName = "";
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).encrypt(str);
			session.invalidate();
			request.setAttribute("poolName", poolName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "logout";
	}

	public String forgotPartnerPassword() {
		return "partnerForgotPassword";
	}
	
	
	public String submitForgotPartnerPassword() {
		try {
			boolean result = false;
			PartnerLoginModel model = new PartnerLoginModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			result = model.getForgotPartnerPassword(loginBean, request);
			model.terminate();
			if(result) {
				loginBean.setForgotPasswordSubmitFlag("true");
			} else {
				loginBean.setForgotPasswordSubmitFlag("false");
				addActionMessage("Invalid Username.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "partnerForgotPassword";
	}
}
