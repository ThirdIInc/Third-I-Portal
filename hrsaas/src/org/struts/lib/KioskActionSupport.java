package org.struts.lib;

import java.util.HashMap;
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
import org.paradyne.model.common.UserProfileModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public abstract class KioskActionSupport extends ActionSupport implements
		ModelDriven, Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.DyneActionSupport.class);

	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	public ServletContext context;
	String Actionmessage = "";
	/**
	 * Sets the messages
	 */
	/**
	 * @param message
	 */
	public void addActionMessage(String message) {
		Actionmessage = Actionmessage + "\n" + message;
		BeanBase applicationBean = (BeanBase) getModel();
		applicationBean.setActionMessage(Actionmessage);
	}

 

	public String getMessage(String key) {
		String obj = "";
		try {
			HashMap hMap = (HashMap) context.getAttribute("common"
					+ session.getAttribute("session_pool"));
			obj = (String) hMap.get(key);
			if (obj == null || obj.equals(null)) {
				hMap = (HashMap) request.getAttribute("formLabels");
				obj = (String) hMap.get(key);
			}
		} catch (Exception e) {
			return "Message Not Found";
		}
		if (obj == null)
			return "Message Not Found";
		return obj;
	}

 
	
	public void prepare() throws Exception {
		/**
		 * Session Handling
		 */
		try {
			setSession(request.getSession());
		} catch(Exception e) {
			//logger.error(e);
		} // end of try-catch block

		try {
			prepare_local(); // TO BE OVERRIDEN IN EACH ACTION SUBCLASS
		} catch(Exception e) {
			//logger.error(e);
		} // end of try-catch block

		/**
		 * Session Check for Login
		 */
		try {
			KioskMaster bean = (KioskMaster)request.getSession().getAttribute("loginBeanData");
			
			System.out.println("value of bean in super class========"+bean);
			if(bean == null) {
				// forward to login Page
				response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/common/Login_input.action");
			} // end of if statement
			else {
				/**
				 * Instantiate BeanBase object
				 */
				BeanBase applicationBean = (BeanBase)getModel();
				applicationBean.setUserEmpId(bean.getEmpCode());
			
				UserProfileModel userModel = new UserProfileModel();
				userModel.initiate(context, session);

				/**
				 * Setting of Service Flag
				 */
				applicationBean.setVasFlag(false);
				/*
				 * try { Object[][] res = userModel.selectServiceFlag(); if (String.valueOf(res[0][0]).equals("Y")) {
				 * applicationBean.setVasFlag(true); } //end of if statement else { applicationBean.setVasFlag(false); } } catch
				 * (Exception e) { //logger.error(e); } //end of try-catch block
				 */
				/**
				 * Set lable properties
				 */

				//logger.info("menu code " + applicationBean.getMenuCode());
				long timeStart = System.currentTimeMillis();
				org.paradyne.model.common.LabelManagementModel model = new org.paradyne.model.common.LabelManagementModel();
				model.initiate(context, session);
				model.loadCommonLabels(getText("data_path"));
				if(applicationBean.getMenuCode() > 0) {
					java.util.HashMap formLabels = model.loadFormLabels(applicationBean.getMenuCode(), getText("data_path"));
					getRequest().setAttribute("formLabels", formLabels);
				}
				model.terminate();
				long timeEnd = System.currentTimeMillis();
				//logger.info("Time taken to load labels " + (timeEnd - timeStart));

				/**
				 * Set Access Rights
				 */
				try {
				//	boolean[] rights = userModel.getAccessRights(String.valueOf(applicationBean.getMenuCode()), bean.getProfileCode());

					/*applicationBean.setInsertFlag(rights[0]);
					applicationBean.setUpdateFlag(rights[1]);
					applicationBean.setDeleteFlag(rights[2]);
					applicationBean.setViewFlag(rights[3]);
					applicationBean.setGeneralFlag(rights[4]);
*/
					
					 //if (!applicationBean.isGeneralFlag()) { //Not a General Employee
						 //applicationBean.setUserProfileCenters(userModel.getProfileCenters(bean.getProfileCode())); //center
						// applicationBean.setUserProfileEmpType(userModel.getProfileEmpTypes(bean.getProfileCode())); //emptype
						 //applicationBean.setUserProfileDivision(userModel.getProfileDivision(bean.getAccessProfileCode())); //paybill 
					 //} //end of if statement
					 } catch(Exception e) {
					//logger.error(e);
				} // end of try-catch block

				// Redirect if no view access
				if(applicationBean.isInsertFlag() || applicationBean.isUpdateFlag() || applicationBean.isViewFlag() || applicationBean.getMenuCode() == -1) {} // end
																																								// of
																																								// if
																																								// statement
				else {
					response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/pages/common/noAccess.jsp");
				}
				userModel.terminate();
			} // end of else statement
		} catch(Exception e) {
			//logger.error(e);
		} // end of try-catch block

		// To user login and profile details
		try {
			prepare_withLoginProfileDetails();
		} catch(Exception e) {
			//logger.error(e);
		} // end of try-catch block
	}

	public void prepare_withLoginProfileDetails() throws Exception {}
	
	/**
	 * Allows the action to prepare itself.
	 */
	public void prepare_Old() throws Exception {
		/**
		 * Session Handling
		 */

		logger.info("_____prepare________");
		try {
			setSession(request.getSession());
		} catch (Exception e) {
			logger.error(e);
		} // end of try-catch block

		try {
			prepare_local(); // TO BE OVERRIDEN IN EACH ACTION SUBCLASS
		} catch (Exception e) {
			logger.error(e);
		} // end of try-catch block

		/**
		 * Session Check for Login
		 */
		try {
			KioskMaster bean = (KioskMaster) request.getSession().getAttribute(
					"loginData");

			if (bean == null) {
				logger.info("bean is null");
				/*
				 * // forward to login Page response.sendRedirect("http://" +
				 * request.getServerName() + ":" + request.getServerPort() +
				 * request.getContextPath() +
				 * "/recruit/CandidateLogin_input.action");
				 */

				// logger.info("bean.getCandidateCode(******** " +
				// bean.getCandidateCode());
			} // end of if statement
			else {
				/**
				 * Instantiate BeanBase object
				 */
			  
				BeanBase applicationBean = (BeanBase)getModel();
				applicationBean.setUserEmpId(bean.getEmpCode());
				
				 
				logger.info("bean  getUserEmpId  ---------------------" + bean.getUserEmpId());
				logger.info("menu code " + applicationBean.getMenuCode());
				long timeStart = System.currentTimeMillis();
				org.paradyne.model.common.LabelManagementModel model = new org.paradyne.model.common.LabelManagementModel();
				model.initiate(context, session);
				model.loadCommonLabels(getText("data_path"));
				if (applicationBean.getMenuCode() > 0) {
					java.util.HashMap formLabels = model
							.loadFormLabels(applicationBean.getMenuCode(),
									getText("data_path"));
					getRequest().setAttribute("formLabels", formLabels);
				}
				model.terminate();
				long timeEnd = System.currentTimeMillis();
				logger.info("Time taken to load labels "
						+ (timeEnd - timeStart));

			} // end of else statement
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} // end of try-catch block

	}

	/**
	 * Initialize the bean object and sets menu code
	 */
	public abstract void prepare_local() throws Exception;

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @return ServletContext object
	 */
	public ServletContext getServletContext() {
		return context;
	}

	/**
	 * @return HttpSession object
	 */
	public HttpSession getSession() {
		return session;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * Sets ServletContext object
	 */
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	/**
	 * Sets HttpServletRequest object
	 */
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	/**
	 * Sets HttpServletResponse object
	 */
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	/**
	 * @param session
	 */
	public void setSession(HttpSession session) {
		this.session = session;
		this.session.setMaxInactiveInterval(1800); // Sets session time-out
		// time in seconds
	}

 
}