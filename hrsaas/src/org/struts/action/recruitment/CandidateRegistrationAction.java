package org.struts.action.recruitment;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.Recruitment.CandidateLoginBean;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.MyCaptchaService;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.recruitment.CandidateRegistrationModel;
import com.octo.captcha.service.CaptchaServiceException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @author Pradeep Kumar Sahoo
 * Date:05-05-2009
 *
 */
public class CandidateRegistrationAction extends ActionSupport implements
		ModelDriven, Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandidateRegistrationAction.class);

	String sImgType = "png";
	CandidateLoginBean loginBean = null;
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
		loginBean = new CandidateLoginBean();
	}

	public CandidateLoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(CandidateLoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String input() {
		String poolName = "";
		try {
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(loginBean
					.getInfoId());
			HttpSession session = request.getSession();
			session.setAttribute("session_pool", poolName);
			
			CandidateRegistrationModel model = new CandidateRegistrationModel();
			model.initiate(context, session);
			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/**
	 * following function is used to display the characters.
	 * @return
	 * @throws Exception
	 */
	public String getKeyCode() throws Exception {
		try {
			getImage(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * following function is called to display the characters in the jsp page. 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
		byte[] captchaBytes;
		if (request.getQueryString() != null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"GET request should have no query string.");
			return;
		}
		try {
			// Session ID is used to identify the particular captcha.
			String captchaId = request.getSession().getId();
			// Generate the captcha image.
			BufferedImage challengeImage = MyCaptchaService.getInstance()
					.getImageChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challengeImage, sImgType, imgOutputStream);
			captchaBytes = imgOutputStream.toByteArray();
			// Clear any existing flag.
			request.getSession().removeAttribute("PassedCaptcha");
		} catch (CaptchaServiceException cse) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Problem generating captcha image.");
			return;
		} catch (IOException ioe) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Problem generating captcha image.");
			return;
		}

		// Set appropriate http headers.
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/"
				+ (sImgType.equalsIgnoreCase("png") ? "png" : "jpeg"));

		// Write the image to the client.
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(captchaBytes);
		outStream.flush();
		outStream.close();
	}

	/**
	 * following function is called to check whether the characters appearing in the image
	 * and the typed characters are same or not. 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	protected boolean checkKeyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean passedCaptchaTest = false;
		try {
			Map paramMap = request.getParameterMap();
			if (paramMap.isEmpty()) {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
						"Post method not allowed without parameters.");
				return false;
			}
			String[] arr1 = (String[]) paramMap.get("hidCaptchaID");
			String[] arr2 = (String[]) paramMap.get("inCaptchaChars");
			if (arr1 == null || arr2 == null) {
				response.sendError(
						HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Expected parameters were not found.");
				return false;
			}
			String sessId = request.getSession().getId();
			String incomingCaptchaId = arr1.length > 0 ? arr1[0] : "";
			String inputChars = arr2.length > 0 ? arr2[0] : "";
			// Check validity and consistency of the data.
			if (sessId == null || incomingCaptchaId == null
					|| !sessId.equals(incomingCaptchaId)) {
				response.sendError(
						HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Browser must support session cookies.");
				return false;
			}
			// Validate whether input from user is correct.
			passedCaptchaTest = validateCaptcha(incomingCaptchaId, inputChars);
			// Set flag into session.
			request.getSession().setAttribute("PassedCaptcha", new Boolean(passedCaptchaTest));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Forward request to results page.
		// RequestDispatcher rd = getServletContext().getRequestDispatcher(
		// "/results.jsp" );
		// rd.forward( request, response );
		return passedCaptchaTest;

	}

	/**
	 * Following method checks the input characters and the
	 * the characters those appears in the image. 
	 * @param captchaId
	 * @param inputChars
	 * @return
	 */
	private boolean validateCaptcha(String captchaId, String inputChars) {
		boolean bValidated = false;
		try {
			bValidated = MyCaptchaService.getInstance().validateResponseForID(
					captchaId, inputChars);
		} catch (CaptchaServiceException cse) {
			cse.printStackTrace();
		}
		return bValidated;
	}

	/**
	 * following function is called when the Submit button is clicked 
	 * @return
	 */
	public String save() {
		String message = "";
		try {
			CandidateRegistrationModel model = new CandidateRegistrationModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			boolean result = false;
			String str = "";

			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			
			str = model.chkUserAvailability(loginBean);
			if (str.equals("true")) {
				addActionMessage("This email id already exists.Please select a different email id.");
				message = "success";
			} else {
				boolean res = checkKeyCode(request, response);
				if (!res) {
					addActionMessage("The entered characters and the characters appearing in the image below are not same.");
					message = "success";
				} else {
					result = model.saveData(loginBean, request);
					if (result) {
						loginBean.setSubmitFlag("true");
					}
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * following function is called to check the user name whether it already exists or not.
	 * This function is called when the check availability button is clicked. 
	 * @return
	 * @throws Exception
	 */
	public String checkAvailUser() throws Exception {
		try {
			CandidateRegistrationModel model = new CandidateRegistrationModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			String str = "";
			str = model.chkUserAvailability(loginBean);
			if (str.equals("true")) {
				addActionMessage("This email id already exists.Please select a different email id.");
				loginBean.setEmailId("");
			} else {
				addActionMessage("This email id is available for you.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	String Actionmessage = "";

	public void addActionMessage(String message) {
		if (!message.equals("")) {
			Actionmessage = Actionmessage + "\n" + message;
			BeanBase applicationBean = (BeanBase) getModel();
			applicationBean.setActionMessage(Actionmessage);
		}
	}

	public String reset() {
		try {
			CandidateRegistrationModel model = new CandidateRegistrationModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			loginBean.setFirstName("");
			loginBean.setLastname("");
			loginBean.setEmailId("");
			loginBean.setDateOfBirth("");
			loginBean.setMobile("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String forgotPassword() {
		try {
			CandidateRegistrationModel model = new CandidateRegistrationModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "candidateForgotPassword";
	}
	
	
	public String submitForgotCandidatePassword() {
		try {
			boolean result = false;
			CandidateRegistrationModel model = new CandidateRegistrationModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			
			result = model.getForgotedPassword(loginBean, request);
			model.terminate();
			if(result) {
				loginBean.setForgotCandidatePasswordSubmitFlag("true");
			} else {
				loginBean.setForgotCandidatePasswordSubmitFlag("false");
				addActionMessage("Invalid Username.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "candidateForgotPassword";
	}
}
