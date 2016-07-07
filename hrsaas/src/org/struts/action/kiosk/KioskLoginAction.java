/**
 * 
 */
package org.struts.action.kiosk;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.bean.kiosk.KioskMaster;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.kiosk.KioskModel;

import com.lowagie.text.Image;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author aa1383
 * 
 */
public class KioskLoginAction extends ActionSupport implements ModelDriven,
		Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(KioskLoginAction.class);

	KioskMaster bean = null;
	String Actionmessage = "";

	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request;
	public HttpServletResponse response;
	private ServletContext context;

	public Object getModel() {
		// TODO Auto-generated method stub
		logger.info("kiosk model");
		return bean;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		bean = new KioskMaster();
	}

	public String input() {
		try {
			HttpSession session = request.getSession();
			KioskModel model = new KioskModel();
			model.initiate(context, session);
			 
			String poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(bean
					.getInfoId());
			session.setAttribute("session_pool", poolName);
		 
			Object[][] data =null;
			String logo="";
			String ppurl="";
			try {
				data = model.getComponyLogo();
				
				if(data!=null && data.length >0)
				{
					  logo = String.valueOf(data[0][0]);
					  ppurl = String.valueOf(data[0][1]);
				}
			} catch (Exception e) {
				logger.error("Exception in getComponyLogo input-----------------"+ e);
			}
			request.setAttribute("logo", logo);
			request.setAttribute("ppurl", ppurl);
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	public String submit() {
		try {
			HttpSession session = request.getSession();
			KioskModel model = new KioskModel();
			model.initiate(context, session);
			String poolName = "";
			try {
				poolName = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.URL_ENCRYPTION_KEY).decrypt(bean
						.getInfoId());
				//logger.info("login 4----------");
			} catch (Exception e) {
				//logger.error("Exception in checkUserPassword1--------" + e);
			}
			session.setAttribute("session_pool", poolName);
			request.setAttribute("session_pool", poolName);
			KioskMaster loginBean_session = new KioskMaster();
			Object loginData[][] = (Object[][]) model.selectLoginData(bean
					.getLoginName());
			System.out.println("loginData.length   "+loginData.length);
			if(loginData!=null && loginData.length >0)
			{
				
				String machineIpAddress = request.getRemoteAddr();
				String str1 = machineIpAddress.replace(".", "#");
				String[] strSpilt = str1.split("#");
				
				int strPort = request.getRemotePort();
				
				Object result[][] =model.saveLoginSessionDetails(strSpilt[0], strSpilt[1],
						strSpilt[2], strSpilt[3], strPort,String.valueOf(loginData[0][3]));
				
				System.out.println("result.length   "+result.length);
				
				if(result!=null && result.length >0)
				{
					String time = String.valueOf(result[0][1]).substring(
							String.valueOf(result[0][1]).length() - 8,
							String.valueOf(result[0][1]).length());
					String date = String.valueOf(result[0][1]).substring(0,
							String.valueOf(result[0][1]).length() - 8);
					loginBean_session.setLoginDate(date);
					loginBean_session.setLoginTime(time);
					
				}
				
				loginBean_session.setLoginCode(String.valueOf(loginData[0][3]));
				loginBean_session.setEmpCode(String.valueOf(loginData[0][1]));
				session.setAttribute("loginBeanData", loginBean_session);
				
				request.setAttribute("loginData", loginData);
				request.setAttribute("loginName", bean
						.getLoginName());
				
			 
				String photo = String.valueOf(loginData[0][7]) ;
				String str = (String) session.getAttribute("session_pool");
				String img = model.getServletContext().getRealPath("//")
						+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
				 
				try {
					Image image = Image.getInstance(img);
				}// end of try
				catch (Exception e) {
					photo = "NoImage.jpg";
				}// end of catch
				request.setAttribute("photo", photo);
				input();
				bean.setActionMessage("");
			}
			else{
				addActionMessage("Invalid PF NO ");
				return INPUT;
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public void addActionMessage(String message) {
		if (!message.equals("")) {
			Actionmessage = Actionmessage + "\n" + message;
			BeanBase applicationBean = (BeanBase) getModel();
			applicationBean.setActionMessage(Actionmessage);
		}
	}

	public KioskMaster getBean() {
		return bean;
	}

	public void setBean(KioskMaster bean) {
		this.bean = bean;
	}

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}
	
	public String returnHome(){
		
		HttpSession session = request.getSession();
		KioskModel model = new KioskModel();
		model.initiate(context, session);
		String empName=request.getParameter("empName");
		String infoId=request.getParameter("infoIds");
	 
		String poolName = "";
		try {
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(infoId);
			//logger.info("login 4----------");
		} catch (Exception e) {
			//logger.error("Exception in checkUserPassword1--------" + e);
		}
		
		System.out.println("empName v  "+empName);
		System.out.println("infoId   "+infoId);
		
		System.out.println("poolName   "+poolName);
		
		session.setAttribute("session_pool", poolName);
		
		KioskMaster loginBean_session = new KioskMaster();
		Object loginData[][] = (Object[][]) model.selectLoginData(empName);
		System.out.println("loginData.length   "+loginData.length);
		if(loginData!=null && loginData.length >0)
		{
			loginBean_session.setEmpCode(String.valueOf(loginData[0][1]));
			session.setAttribute("loginBeanData", loginBean_session);
			
			request.setAttribute("loginData", loginData);
		}
		request.setAttribute("session_pool", infoId);
		request.setAttribute("loginName", empName);
		String photo = String.valueOf(loginData[0][7]) ;
		String str = (String) session.getAttribute("session_pool");
		String img = model.getServletContext().getRealPath("//")
				+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}// end of catch
		request.setAttribute("photo", photo);
		input();
		bean.setActionMessage("");
		
		return "success";
	}
	
	public String logout(){
		
		KioskModel model = new KioskModel();
		model.initiate(context, request.getSession());
		String empName=request.getParameter("loginName");
		String poolName=request.getParameter("infoIds");
		/*
		
		Object loginData[][] = (Object[][]) model.selectLoginData(empName);
		
		HttpSession session= request.getSession();
		String updateQuery = " UPDATE HRMS_LOGIN SET LOGIN_LASTLOGIN_DATE=SYSDATE WHERE LOGIN_CODE="
			+ String.valueOf(loginData[0][3]);
		model.getSqlModel().singleExecute(updateQuery);// update query
*/		model.terminate();
		request.getSession().invalidate();
		request.setAttribute("poolName", poolName);
		return "input";
		
	}

}
