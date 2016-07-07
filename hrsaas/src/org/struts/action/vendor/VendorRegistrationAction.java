package org.struts.action.vendor;
/** @ Author : Archana Salunkhe
 * @ purpose : Developed for Vendor Registration  
 * @ Date : 08-May-2012
 */
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
import org.paradyne.bean.vendor.VendorLogin;
import org.paradyne.lib.MyCaptchaService;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.model.vendor.VendorRegistrationModel;
import com.octo.captcha.service.CaptchaServiceException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class VendorRegistrationAction extends ActionSupport implements
ModelDriven, Preparable, ServletRequestAware, ServletResponseAware,
ServletContextAware {
	
	public ActionContext actionContext = ActionContext.getContext();

	public HttpServletRequest request;

	public HttpServletResponse response;

	public ServletContext context;

	
	private static final long serialVersionUID = 1L;
	
	String sImgType = "png";
	VendorLogin vendorBean;
	
	public void prepare() throws Exception {
		vendorBean =new VendorLogin();
		
	}
	public Object getModel() {
		return vendorBean;
	}
	public VendorLogin getVendorBean() {
		return vendorBean;
	}

	public void setVendorBean(VendorLogin vendorBean) {
		this.vendorBean = vendorBean;
	}
	
	public void setServletRequest(HttpServletRequest httpservletrequest) {
		this.request = httpservletrequest;
	}

	public void setServletResponse(HttpServletResponse httpservletresponse) {
		this.response=  httpservletresponse;		
	}

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	public ServletContext getServletContext() {
		return context;
	}
	
	public String input() {
		String poolName = "";
		try {
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY).decrypt(vendorBean
					.getInfoId());
			HttpSession session = request.getSession();
			session.setAttribute("session_pool", poolName);
			
			VendorRegistrationModel model = new VendorRegistrationModel();
			model.initiate(context, session);
			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	/**
	 * following function is used to display the characters.
	 * @return
	 * @throws Exception
	 */
	public void getKeyCode() throws Exception {
		try {
			getImage(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * following function is called to display the characters in the jsp page. 
	 * @param request,response
	 * @throws ServletException,IOException
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

	public String reset() {
		try {
			VendorRegistrationModel model = new VendorRegistrationModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
			vendorBean.setPartnerName("");
			vendorBean.setEmailId("");
			vendorBean.setPartnerCode("");
			vendorBean.setLoginName("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * following function is called when the Submit button is clicked 
	 * @return
	 */
	public String save() {
		String message = "";
		try {
			VendorRegistrationModel model = new VendorRegistrationModel();
			HttpSession session = request.getSession();
			model.initiate(context, session);
			boolean result = false;
			Object[][] logo = null;
			logo = model.getComponyLogo();
			request.setAttribute("logo", String.valueOf(logo[0][0]));
			request.setAttribute("comanyName", String.valueOf(logo[0][1]));
				boolean res = checkKeyCode(request, response);
				if (!res) {
					addActionMessage("The entered characters and the characters appearing in the image below are not same.");
					message= "success";
				} else {
					result = model.saveData(vendorBean, request);
					if (result) {
						vendorBean.setSubmitFlag("true");
					}
				}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * following function is called to check whether the characters appearing in the image
	 * and the typed characters are same or not. 
	 * @param request, response
	 * @throws ServletException,IOException
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
		return passedCaptchaTest;
	}
	
	/** Following method checks the input characters and the
	 * the characters those appears in the image. 
	 * @param captchaId, inputChars
	 * @ return boolean
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


}
