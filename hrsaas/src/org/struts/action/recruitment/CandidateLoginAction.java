package org.struts.action.recruitment;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.Recruitment.CandidateLoginBean;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.recruitment.CandidateLoginModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author varunK
 * 
 */

public class CandidateLoginAction extends ActionSupport implements ModelDriven,
		Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandidateLoginAction.class);

	public ActionContext actionContext = ActionContext.getContext();

	CandidateLoginBean loginBean = null;

	public void prepare() throws Exception {
		loginBean = new CandidateLoginBean();
	}

	public Object getModel() {
		return loginBean;
	}

	public HttpServletRequest request;

	public HttpServletResponse response;

	public ServletContext context;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public CandidateLoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(CandidateLoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String input() {
		String poolName = "";
		try {
			poolName = request.getParameter("infoId");
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(loginBean
					.getInfoId());
			HttpSession session = request.getSession();
			session.setAttribute("session_pool", poolName);
			CandidateLoginModel model = new CandidateLoginModel();
			model.initiate(context, session);
			model.getCandName(loginBean, request);
			Object[][] logo = null;
			logo = model.getComponyLogo();
			Object[][] listObj = null;
			listObj = model.getJobBoardList();
			request.setAttribute("listObj", listObj);
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public String submitnew() throws Exception {
		String poolName = null;
		String flag = "";
		Object[][] logo = null;
		CandidateLoginModel model =null;
		try {
			 
			poolName = request.getParameter("infoId");
			if (poolName != null) {
				try {
					loginBean.setInfoId(poolName);
					poolName = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
							StringEncrypter.URL_ENCRYPTION_KEY)
							.decrypt(loginBean.getInfoId());
					request.getSession().setAttribute("session_pool", poolName);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			model = new CandidateLoginModel();
			HttpSession session = request.getSession();
			// bean = new LoginBean();
			session.setAttribute("session_pool", poolName);
			model.initiate(context, session);
			Object[][] listObj = null;
			listObj = model.getJobBoardList();
			
			model.createMainMenu(request);
			
			request.setAttribute("listObj", listObj);
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			flag = model.submitnewuser(loginBean);
			CandidateLoginBean loginBean_session = new CandidateLoginBean();
			loginBean_session.setCandidateCode(loginBean.getCandidateCode());

			System.out.println("Candidate Code: "
					+ loginBean_session.getCandidateCode());
			session.setAttribute("loginBeanData", loginBean_session);
			session.setAttribute("poolId", loginBean.getInfoId());
			// String userFlag=model.checkUser(onlinePaper);
			if (flag.equals("1")) {
				request.setAttribute("logo", String.valueOf(logo[0][0]));
				request.setAttribute("comanyName", String.valueOf(logo[0][1]));
				addActionMessage("Invalid UserName");
			} else if (flag.equals("2")) {
				request.setAttribute("logo", String.valueOf(logo[0][0]));
				request.setAttribute("comanyName", String.valueOf(logo[0][1]));
				addActionMessage("Invalid Password");
			}
			if (flag.equals("")) {
				request.setAttribute("logo", String.valueOf(logo[0][0]));
				request.setAttribute("comanyName", String.valueOf(logo[0][1]));
				model.createMenu(request);
				model.getCandName(loginBean, request);
				return SUCCESS;
			}
			
		} catch (Exception e) {
			Object[][] listObj = null;
			listObj = model.getJobBoardList();
			request.setAttribute("listObj", listObj);
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			e.printStackTrace();
		}
		model.terminate();
		return INPUT;
	} // end of method

	public String logout() throws Exception {
		try {
			HttpSession session = request.getSession();
			String str = (String) session.getAttribute("session_pool");
			String poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).encrypt(str);
			session.invalidate();
			request.setAttribute("poolName", poolName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "logout";
	}

	String Actionmessage = "";

	public void addActionMessage(String message) {
		if (!message.equals("")) {
			Actionmessage = Actionmessage + "\n" + message;
			BeanBase applicationBean = (BeanBase) getModel();
			applicationBean.setActionMessage(Actionmessage);
		}
	}
} //end of class
