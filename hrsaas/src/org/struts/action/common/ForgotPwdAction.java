package org.struts.action.common;

/*import jChatBox.Service.UserLogin;
 import jChatBox.Util.XMLConfig;
 */

import java.util.TreeMap;

import org.apache.struts.action.ActionErrors;
import org.apache.struts2.components.ActionError;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.common.*;
import org.paradyne.lib.BeanBase;

import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.common.LoginModel;

import org.struts.lib.ParaActionSupport;

import com.octo.captcha.service.CaptchaServiceException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import javax.servlet.http.*;
import javax.servlet.*;

public class ForgotPwdAction extends ActionSupport implements ModelDriven,
		Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoginAction.class);
	LoginBean loginBean = null;
	public ActionContext actionContext = ActionContext.getContext();

	public HttpServletRequest request;

	public HttpServletResponse response;

	public ServletContext context;

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

	public org.paradyne.bean.common.LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(org.paradyne.bean.common.LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	// forgot password
	/**
	 * @author krishna
	 */

	/**
	 * use to call forgot password question page
	 * 
	 */
	
	
	
	public String forgotPage() throws Exception {

		logger.info(">>>>>>in forgotPage");
		LoginModel loginModel;
		try {
			/**
			 * set the session_pool here
			 * 
			 */
			String poolName = "";
			try {
				poolName = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.URL_ENCRYPTION_KEY).decrypt(loginBean
						.getInfoId());

				loginBean.setInfoId(loginBean.getInfoId());
				logger.info("login 4");
			} catch (Exception e) {
				logger.info(">>kriss>>>>>.exception");
				response.sendError(response.SC_INTERNAL_SERVER_ERROR,
						"There was a problem");
				e.printStackTrace();

				// TODO: handle exception
			}
			loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			// poolName = "pool_hrwork" ;
			logger.info("poolname--------------------" + poolName);
			/*String check = loginModel.checkIsUserFirstLogin(loginBean);
			System.out.println("check-------------------------------" + check);
			if (check.equals("YES")) {
				loginBean.setForgotPassFlag("false");
			} else {
				loginBean.setForgotPassFlag("true");
			}*/
			loginModel.setCompanyNameAndLogo(request);
			session.setAttribute("session_pool", poolName);
			getForgotPassQue();
			loginModel.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "forgotPage";
	}

	/**
	 * This method is used to submit the parameters like
	 * user name/email/employeeId etc. and used to send a mail with user name and
	 * password, who is active and in service with valid mail id
	 * 
	 */
	public void getForgotPassQue() {
		// TODO Auto-generated method stub
		try {
			LoginModel loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			String userLoginCodeQuery = "SELECT LOGIN_CODE  FROM HRMS_LOGIN  WHERE LOGIN_NAME='"
				+ loginBean.getLoginName()+ "'";

			Object[][] userLoginCode =loginModel.getSqlModel().getSingleResult(userLoginCodeQuery);
			
			String forgotPassQue="SELECT QUES_CODE,SECURITY_QUESNAME FROM HRMS_SECURITY_ANSWER "
				+" INNER JOIN HRMS_SECURITY_QUESTIONS ON(HRMS_SECURITY_ANSWER.QUES_CODE=HRMS_SECURITY_QUESTIONS.SECURITY_QUESCODE)"
				+" WHERE LOGIN_CODE="+userLoginCode[0][0]+" ORDER BY DBMS_RANDOM.VALUE ";
			
			Object[][] questionObj=loginModel.getSqlModel().getSingleResult(forgotPassQue);
			if(questionObj!=null && questionObj.length>0)
			{
				loginBean.setForgotPassQueCode(String.valueOf(questionObj[0][0]));
				loginBean.setForgotPassQue(String.valueOf(questionObj[0][1]));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getQuestionForEmailId() throws Exception
	{
		try {
			String result = "";
			LoginModel login = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			login.initiate(context, session);
			//result=
			loginBean.setUserNamefg(loginBean.getForgotUserName());
			login.terminate();
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getQuestionForEmailId------------"+e);
		}
		 	return submitForgot();
	  	}

	public String submitForgot() throws Exception {

		try {
			logger.info("loginInfoId-----------" + loginBean.getInfoId());
			String result = "";
			logger.info(">>>>>>>in submitForgot");
			LoginModel loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			//	System.out.println("result------------------------"+result);
			//	if(result.equals("Yes"))
			//	{
			result = loginModel.getUserDetails(request);
			loginModel.setCompanyNameAndLogo(request);
			//}
			logger.info(">>>>>>>>>>>>>>.in action " + result);
			if (result.equals("noMailBox")) {
				reset();
				request.setAttribute("result", result);
				return "page";
			}
			if (result.equals("invalidUserName")) {
				reset();
				addActionMessage("The Login ID does not exist.");
			}
			if (result.equals("noEmailId")) {
				reset();
				addActionMessage("The Email ID does not exist.");
			}
			if (result.equals("noEmpId")) {
				reset();
				addActionMessage("The Employee ID does not exist.");
			}
			if (result.equals("notInSevvice") || result.equals("inactive")) {

				request.setAttribute("result", result);
				reset();
				return "page";
			}
			if (result.equals("notPermanentAdd")) {
				reset();
				request.setAttribute("result", result);
				return "page";
			}
			if (result.equals("mail")) {
				logger.info("Result" + result);
				request.setAttribute("result", result);
				String poolName = (String) session.getAttribute("session_pool");
				String[] company = poolName.split("_");
				String URL = "http://" + request.getServerName() + ":"
						+ request.getServerPort() + "/hrwork/" + "client/"
						+ company[1];
				return "page";
			}
			System.out.println("result---------------------" + result);
			if (result.equals("forgot")) {
				return "forgotque";
			}
			if (result.equals("userNameJsp")) {

				return "userNameJsp";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in submitForgot----------"+e);
		}
		return "fail";
	}
	
	 
	
	public String submitAnswer()
	{
		String result="";
		try {
			LoginModel loginModel = new LoginModel(loginBean);
			HttpSession session = request.getSession();
			loginModel.initiate(context, session);
			result = loginModel.getAnswerDetails(request);
			loginModel.setCompanyNameAndLogo(request);
			if (result.equals("wrongans")) {
				request.setAttribute("result", result);
				return "page";
			}
			if (result.equals("No")) {
				addActionMessage("Given answer is wrong.");
				result = "forgotque";

			} else {
				result = loginModel.mailPasswordToUser(request);

				if (result.equals("noMailBox")) {
					reset();
					request.setAttribute("result", result);
					return "page";
				}

				if (result.equals("noEmailId")) {
					reset();
					addActionMessage("The Email ID does not exist.");
					return "page";
				}
				if (result.equals("notInSevvice") || result.equals("inactive")) {

					request.setAttribute("result", result);
					reset();
					return "page";
				}

				if (result.equals("mail")) {
					logger.info("Result" + result);
					request.setAttribute("result", result);
					String poolName = (String) session
							.getAttribute("session_pool");
					String[] company = poolName.split("_");
					String URL = "http://" + request.getServerName() + ":"
							+ request.getServerPort() + "/hrwork/" + "client/"
							+ company[1];
					return "page";
				}
			}
			loginModel.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	String Actionmessage = "";

	public void addActionMessage(String message) {
		if (!message.equals("")) {
			Actionmessage = Actionmessage + "\n" + message;
			BeanBase applicationBean = (BeanBase) getModel();
			applicationBean.setActionMessage(Actionmessage);
		}
	}

	public void reset() {
		loginBean.setUserNamefg("");
		loginBean.setEmailIdfg("");
		loginBean.setEmpIdfg("");
	}

}
