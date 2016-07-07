/**
 * 
 */
package org.struts.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.paradyne.lib.BeanBase;
import org.paradyne.model.common.LoginModel;


/**
 * 
 * @author Lakkichand_Golegaonkar
 * @date 29-August-2009
 */
public class SessionListener implements HttpSessionListener {
	public SessionListener() {
	}
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(SessionListener.class);
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		// Get the session that was created
		HttpSession session = sessionEvent.getSession();
		// Store something in the session, and log a message
		try {
			System.out.println("Session created: " + session);
		
		} catch (Exception e) {
			System.out.println("Error in setting session attribute: "
					+ e.getMessage());
		}
	}

	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		// Get the session that was invalidated
		HttpSession session = sessionEvent.getSession();
		long creationTime = session.getCreationTime();
		System.out.println("creationTime:  " + creationTime);
		// Log a message
		System.out.println("Session invalidated: " + session);
		
		BeanBase bean = (BeanBase) session.getAttribute("loginBeanData");
		LoginModel loginModel = new LoginModel();
		logger.info("bean.getLoginDate()================="
				+ bean.getLoginDate());
		logger.info("bean.getLoginTime()================="
				+ bean.getLoginTime());
		String dateTime = bean.getLoginDate() + bean.getLoginTime();
		loginModel.initiate(session.getServletContext(), session);
		String updLogoutQuery = "UPDATE HRMS_LOGIN_SESSION SET LOGOUT_DATETIME = SYSDATE "
				+ " WHERE LOGIN_ID = "
				+ bean.getLoginCode()
				+ " AND LOGIN_DATETIME =to_date('"
				+ dateTime
				+ "' ,'Day DD Mon YYYY HH:MI:SS')";
		loginModel.getSqlModel().singleExecute(updLogoutQuery);
		loginModel.terminate();
		session.removeAttribute("loginBeanData");
	}

}