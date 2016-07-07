package org.struts.action.WBT;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.paradyne.bean.WBT.OnlineProgram;
import org.paradyne.model.WBT.OnlineProgramModel;
import org.paradyne.model.WBT.TechPortalWebServiceModel;
import org.struts.action.ApplicationStudio.AutoBirthdayAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class OnlineProgramAction extends ActionSupport implements ModelDriven,
		Preparable, ServletRequestAware, ServletResponseAware,
		ServletContextAware {

	OnlineProgram bean = null;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AutoBirthdayAction.class);
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	public ServletContext context;

	public ActionContext getActionContext() {
		return actionContext;
	}

	public void setActionContext(ActionContext actionContext) {
		this.actionContext = actionContext;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void prepare() throws Exception {
		try {
			bean = new OnlineProgram();
			bean.setMenuCode(2311);
			setSession(request.getSession());
			setContext(session.getServletContext());
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!("".equals(poolName) || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPath = this.getText("data_path") + "/upload"
					+ poolName + "/OnlineTestDocs/";
			this.bean.setDataPath(fileDataPath);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public OnlineProgram getBean() {
		return bean;
	}

	public void setBean(OnlineProgram bean) {
		this.bean = bean;
	}

	public Object getModel() {
		return bean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {

		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = "/" + poolName;
		}
		final String fileDataPath = this.getText("data_path") + "/upload"
				+ poolName + "/OnlineTestDocs/";
		this.bean.setDataPath(fileDataPath);
	}

	public String input() {
		try {

			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);
			model.getListDetails(bean);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
	
	public String startWBT()
	{
		 try {
			 
			String isTestView = checkUserSession();
			if (isTestView.equals("N")) {
				return "invalidTest";
			}
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return  startTest();
	}

	public String callProgram() {
		String userType="T";
		String programCode =null;
		String userCode=null;
		String applicationCode=null;
		String from=null;
		String datetime="11/21/2012 4:34:17 PM";
		String instance = "DEVGOLIVE";
		try {
			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);
			programCode=((String)request.getSession().getAttribute("programCode"));
			bean.setProgramCode(programCode);
			bean.setUserCode((String)request.getSession().getAttribute("userCode"));
			
			bean.setUserType(userType);
			if (applicationCode == null) {
				applicationCode = "0";

			}
			bean.setApplicationCode(applicationCode);
			model.startProgram(bean, request, programCode);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "testScreenTemplate";
	}
	public String startTest() {
		try {
	 	
			System.out.println("in startTest-----------------------");

			String infoId = request.getParameter("infoId");
			
			// For Development
			request.setAttribute("infoId", "7mar5Lf/k7Ap9WGxdbhufQ==");
			
			// For Live
			//request.setAttribute("infoId", "o1JOiMDqSoAn2nVvXGHeWw==");
			// For Development
			request.getSession().setAttribute("session_pool", "pool_test");
			
			// For Live
			//request.getSession().setAttribute("session_pool", "pool_glodyne");
			
			String userType="T";
			String programCode =null;
			String userCode=null;
			String applicationCode=null;
			String from=null;
			String datetime="11/21/2012 4:34:17 PM";
			String instance = "DEVGOLIVE";
			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);
			
			 try {
				programCode = request.getParameter("ProgramCode");
				from = request.getParameter("from");
				userCode = request.getParameter("EmpID");
				applicationCode = request.getParameter("applicationCode");
				datetime=request.getParameter("DateTime");
				instance = request.getParameter("INSTANCE_NAME");
			} catch (Exception e) {
				// TODO: handle exception
			}
			request.getSession().setAttribute("datetime", datetime);
			request.getSession().setAttribute("instance", instance);
			request.getSession().setAttribute("userType", userType);
			request.getSession().setAttribute("userCode", userCode);
			request.getSession().setAttribute("programCode", programCode);
			
			if (userType == null || userType.equalsIgnoreCase("") || userType.equals("null")) {
				userType = "T";

			}

			System.out.println("programCode " + programCode);

			System.out.println("userCode " + userCode);

			System.out.println("applicationCode " + applicationCode);

			System.out.println("userType " + userType);

			bean.setProgramCode(programCode);

			bean.setUserCode(userCode);
			bean.setUserType(userType);
			if (applicationCode == null) {
				applicationCode = "0";

			}

			bean.setApplicationCode(applicationCode);
			bean.setSource(from);
			model.startProgram(bean, request, programCode);
			model.terminate();
			System.out.println("end------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
		//session.invalidate();
		return "testScreenTemplate";
	}

	public String checkUserSession() {
		String isTestView="N";
		try {
			String empCode = request.getParameter("EmpID");
			String programCode = request.getParameter("ProgramCode");
			String userType = request.getParameter("userType");
			if (userType == null) {
				userType = "T";

			}
			String PKToken = request.getParameter("PKToken");
			String instance = "";
			
			try {
				instance = request.getParameter("INSTANCE_NAME");
				session.setAttribute("instance", instance);
			} catch (Exception e) {
				// TODO: handle exception
			}
			bean.setInstance_name(instance);
			System.out.println("bean.getInstance_name():"+bean.getInstance_name());
			System.out.println("empCode "+empCode);
			System.out.println("programCode "+programCode);
			System.out.println("userType "+userType);
			System.out.println("PKToken "+PKToken);
			String xmlUserSession="<WBT><EmployeeID>"+empCode+"</EmployeeID><WBTType>"+programCode+"</WBTType><USER_GUID>"+PKToken+"</USER_GUID><INSTANCE_NAME>"+instance+"</INSTANCE_NAME></WBT>";
			System.out.println("xmlUserSession   "+xmlUserSession);
			
			String xmlUserSessionUpdate="<WBT><EmployeeID>"+empCode+"</EmployeeID><INSTANCE_NAME>"+instance+"</INSTANCE_NAME></WBT>";
			System.out.println("xmlUserSession   "+xmlUserSessionUpdate);
			TechPortalWebServiceModel model= new TechPortalWebServiceModel();
			String isValidSession=model.checkTechnicianSession(xmlUserSession);
			
			if(isValidSession.equals("SUCCESS")){
				isTestView="Y";
				model.updateEmp_USERGUID_ToTechPortal(xmlUserSessionUpdate);
			}
				
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isTestView ;
	}

	public String callModule() {

		try {
			//bean.setSectionTakeTestBtnFlag("false");
			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);
			String moduleCodeStr = request.getParameter("moduleCodeStr");
			System.out.println("moduleCodeStr" + moduleCodeStr);
			String programCodeStr = request.getParameter("programCodeStr");
			System.out.println("programCodeStr" + programCodeStr);
			String sectionCodeStr = request.getParameter("sectionCodeStr");
			System.out.println("sectionCodeStr" + sectionCodeStr);
			String from = request.getParameter("from");
			String testStatus = request.getParameter("testStatus");
			System.out.println("from  --callModule--------------  " + from);

			String attemptCountStr = request.getParameter("attemptCountStr");

			System.out.println("attemptCountStr---------------"
					+ attemptCountStr);

			int remainingAttemptCount = model
					.setRemainingAttemptCountForModelAndSection(programCodeStr,
							moduleCodeStr, "", bean);

			bean
					.setRemainingAttemptCount(String
							.valueOf(remainingAttemptCount));

			model.getTestTemplateDetails(request, bean, moduleCodeStr,
					programCodeStr, sectionCodeStr);

			bean.setAttempt(model.setAttemptCount(bean, programCodeStr,
					sectionCodeStr, moduleCodeStr));

			model.callModule(bean, request, moduleCodeStr, programCodeStr,
					sectionCodeStr, from, "N");
			String moduleHasQue = model.isModuleContainQue(programCodeStr,
					moduleCodeStr);
			bean.setSectionDisplayFlag("false");

			String moduleCompleted = model.checkTestCompleted(bean
					.getUserCode(), bean.getApplicationCode(), programCodeStr,
					bean.getUserType(), moduleCodeStr, sectionCodeStr, bean);

			String checkMarkAsRead = model
					.checkMarkAsRead(bean.getUserCode(), bean
							.getApplicationCode(), programCodeStr, bean
							.getUserType(), moduleCodeStr, sectionCodeStr, bean);

			if (moduleHasQue.equals("N")) {
				System.out.println("111111111111 aaaaaaaaaaaaaaaaaaaa");
				bean.setTakeTestBtnFlag("false");
				if (checkMarkAsRead.equals("Y")) {
					bean.setFinishBtnFlag("false");
				} else {
					bean.setFinishBtnFlag("true");
				}
			} else if (bean.getNoQuesAvailable().equals("0")) {
				System.out.println("2222222222222222 aaaaaaaaaaaaaa");
				bean.setTakeTestBtnFlag("false");
				if (checkMarkAsRead.equals("Y")) {
					bean.setFinishBtnFlag("false");
				} else {
					bean.setFinishBtnFlag("true");
				}
			} else if (moduleCompleted.equals("Y")) {
				System.out.println("33333333333333333333 aaaaaaaaaaa");
				bean.setTakeTestBtnFlag("false");
				bean.setFinishBtnFlag("false");
				/*if(checkMarkAsRead.equals("Y"))
				{
					bean.setFinishBtnFlag("false");
				}
				else{
					bean.setFinishBtnFlag("true");
				}*/
			} else if (bean.getRemainingAttemptCount().equals("0")) {
				bean.setTakeTestBtnFlag("false");
				bean.setFinishBtnFlag("false");
			} else {
				System.out.println("44444444444444444444444 aaaaaaaaaaaaa");
				bean.setTakeTestBtnFlag("true");
				bean.setFinishBtnFlag("false");
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "moduleScreen";
	}

	public String callSection() {
		try {
			//bean.setTakeTestBtnFlag("false");
			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);
			String moduleCodeStr = request.getParameter("moduleCodeStr");
			System.out.println("moduleCodeStr" + moduleCodeStr);
			String programCodeStr = request.getParameter("programCodeStr");
			System.out.println("programCodeStr" + programCodeStr);
			String sectionCodeStr = request.getParameter("sectionCodeStr");
			System.out.println("sectionCodeStr" + sectionCodeStr);
			String from = request.getParameter("from");
			String testStatus = request.getParameter("testStatus");
			System.out.println("from  --callModule--------------  " + from);

			String attemptCountStr = request.getParameter("attemptCountStr");

			System.out.println("attemptCountStr---------------"
					+ attemptCountStr);

			int remainingAttemptCount = model
					.setRemainingAttemptCountForModelAndSection(programCodeStr,
							moduleCodeStr, sectionCodeStr, bean);

			bean
					.setRemainingAttemptCount(String
							.valueOf(remainingAttemptCount));

			model.getTestTemplateDetails(request, bean, moduleCodeStr,
					programCodeStr, sectionCodeStr);

			bean.setAttempt(model.setAttemptCount(bean, programCodeStr,
					sectionCodeStr, moduleCodeStr));

			model.callModule(bean, request, moduleCodeStr, programCodeStr,
					sectionCodeStr, from, "N");
			bean.setSectionDisplayFlag("true");

			System.out.println("bean.getNoQuesAvailable()  "
					+ bean.getNoQuesAvailable());

			String sectionHasQue = model.isSectionContainQue(programCodeStr,
					moduleCodeStr, sectionCodeStr);

			String sectionCompleted = model.checkTestCompleted(bean
					.getUserCode(), bean.getApplicationCode(), programCodeStr,
					bean.getUserType(), moduleCodeStr, sectionCodeStr, bean);

			String checkMarkAsRead = model
					.checkMarkAsRead(bean.getUserCode(), bean
							.getApplicationCode(), programCodeStr, bean
							.getUserType(), moduleCodeStr, sectionCodeStr, bean);

			if (sectionHasQue.equals("N")) {
				System.out.println("in call section 1111111111111111111111");
				bean.setTakeTestBtnFlag("false");
				if (checkMarkAsRead.equals("Y")) {
					bean.setFinishBtnFlag("false");
				} else {
					bean.setFinishBtnFlag("true");
				}

			} else if (bean.getNoQuesAvailable().equals("0")) {
				System.out.println("in call section 2222222222222222222");
				bean.setTakeTestBtnFlag("false");
				if (checkMarkAsRead.equals("Y")) {
					bean.setFinishBtnFlag("false");
				} else {
					bean.setFinishBtnFlag("true");
				}
			} else if (sectionCompleted.equals("Y")) {
				System.out.println("in call section 333333333333333333333");
				bean.setTakeTestBtnFlag("false");
				bean.setFinishBtnFlag("false");
			} else if (bean.getRemainingAttemptCount().equals("0")) {
				bean.setTakeTestBtnFlag("false");
				bean.setFinishBtnFlag("false");
			} else {
				System.out
						.println("in call section 44444444444444444444444444");
				bean.setTakeTestBtnFlag("true");
				bean.setFinishBtnFlag("false");
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "moduleScreen";

	}

	public void bactTotechportal()
	{
		try {
			response.sendRedirect("http://192.168.100.34/TechPortalLive/");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String backToProgram() {
		try {
			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);
			model.startProgram(bean, request, bean.getProgramCode());
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "testScreenTemplate";
	}

	public String submit() throws Exception {
		try {

			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);

			String moduleCode = request.getParameter("moduleCodeStr");
			String programCode = request.getParameter("programCodeStr");
			String sectionCodeStr = request.getParameter("sectionCodeStr");

			System.out.println("moduleCode  submit " + moduleCode);
			System.out.println("programCode  submit " + programCode);
			System.out.println("sectionCodeStr  submit " + sectionCodeStr);

			String applicationCode = bean.getApplicationCode();
			String userCode = bean.getUserCode();
			String userType = bean.getUserType();

			String from = request.getParameter("from");
			bean.setSource(from);

			model.submit(bean, request, moduleCode, programCode,
					applicationCode, userCode, userType, sectionCodeStr);

			model.terminate();

			System.out.println("bean.getSource()=-------------"
					+ bean.getSource());

			model.callModule(bean, request, moduleCode, programCode,
					sectionCodeStr, from, "Y");

			bean.setProgramCode(programCode);

			model.getTestResult(bean, request, applicationCode, moduleCode,
					sectionCodeStr, programCode, userCode, userType, "Y", bean
							.getAttempt());
			request.setAttribute("moduleCode", moduleCode);
			request.setAttribute("sectionCode", sectionCodeStr);

			if (bean.getResult().equals("Pass")) {
				bean.setResultFlag("true");
			} else {
				bean.setResultFlag("false");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "onlineProgramSubmit";
	}

	public String takeTest() {

		try {

			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);
			String moduleCodeStr = request.getParameter("moduleCodeValue");
			String programCode = request.getParameter("sectionProgramCode");
			String sectionCode = request.getParameter("sectionCodeStr");

			String from = request.getParameter("from");

			System.out.println("sectionCode" + sectionCode);
			System.out.println("moduleCodeStr" + moduleCodeStr);
			System.out.println("programCode" + programCode);
			model.getTestTemplateDetails(request, bean, moduleCodeStr,
					programCode, sectionCode);

			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				bean.setTestSectionDisplayFlag("true");
			}

			//bean.setAttempt("1");

			bean.setAttempt(model.setAttemptCount(bean, programCode,
					sectionCode, moduleCodeStr));

			model.startTest(bean, request, moduleCodeStr, programCode,
					sectionCode, from);

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "testScreen";
	}

	public String callFinish() {

		try {

			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);
			String moduleCodeStr = request.getParameter("moduleCodeValue");
			String programCode = request.getParameter("sectionProgramCode");
			String sectionCode = request.getParameter("sectionCodeStr");

			String from = request.getParameter("from");

			System.out.println("sectionCode" + sectionCode);
			System.out.println("moduleCodeStr" + moduleCodeStr);
			System.out.println("programCode" + programCode);
			/*	model.getTestTemplateDetails(request, bean, moduleCodeStr,
						programCode, sectionCode);
			 */
			if (sectionCode != null && !sectionCode.equals("null")
					&& !sectionCode.equals("")) {
				bean.setTestSectionDisplayFlag("true");
			}

			String applicationCode = bean.getApplicationCode();
			String userCode = bean.getUserCode();
			String userType = bean.getUserType();

			model.callFinish(bean, request, applicationCode, moduleCodeStr,
					sectionCode, programCode, userCode, userType, "Y", "Y");
			//model.startProgram(bean, request,programCode);

			if (from.equals("sectionPage")) {

				int remainingAttemptCount = model
						.setRemainingAttemptCountForModelAndSection(
								programCode, moduleCodeStr, "", bean);

				bean.setRemainingAttemptCount(String
						.valueOf(remainingAttemptCount));

				bean.setAttempt(model.setAttemptCount(bean, programCode,
						sectionCode, moduleCodeStr));

				model.getTestTemplateDetails(request, bean, moduleCodeStr,
						programCode, "");

				model.callModule(bean, request, moduleCodeStr, programCode,
						sectionCode, from, "Y");
				String moduleHasQue = model.isModuleContainQue(programCode,
						moduleCodeStr);
				bean.setShowModuleDtlFlag("true");

				bean.setSectionDisplayFlag("false");

				String moduleCompleted = model.checkTestCompleted(bean
						.getUserCode(), bean.getApplicationCode(), programCode,
						bean.getUserType(), moduleCodeStr, "", bean);

				String checkMarkAsRead = model.checkMarkAsRead(bean
						.getUserCode(), bean.getApplicationCode(), programCode,
						bean.getUserType(), moduleCodeStr, "", bean);

				if (moduleHasQue.equals("N")) {
					System.out.println("111111111111");
					bean.setTakeTestBtnFlag("false");
					if (checkMarkAsRead.equals("Y")) {
						bean.setFinishBtnFlag("false");
					} else {
						bean.setFinishBtnFlag("true");
					}
				} else if (bean.getNoQuesAvailable().equals("0")) {
					System.out.println("2222222222222222");
					bean.setTakeTestBtnFlag("false");
					if (checkMarkAsRead.equals("Y")) {
						bean.setFinishBtnFlag("false");
					} else {
						bean.setFinishBtnFlag("true");
					}
				} else if (moduleCompleted.equals("Y")) {
					System.out.println("33333333333333333333");
					bean.setTakeTestBtnFlag("false");
					bean.setFinishBtnFlag("false");
					/*if(checkMarkAsRead.equals("Y"))
					{
						bean.setFinishBtnFlag("false");
					}
					else{
						bean.setFinishBtnFlag("true");
					}*/
				} else if (bean.getRemainingAttemptCount().equals("0")) {
					bean.setTakeTestBtnFlag("false");
					bean.setFinishBtnFlag("false");
				} else {
					System.out.println("44444444444444444444444");
					bean.setTakeTestBtnFlag("true");
					bean.setFinishBtnFlag("false");
				}
				return "moduleScreen";
			} else {
				model.startProgram(bean, request, programCode);
				model.terminate();
				return "testScreenTemplate";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "testScreenTemplate";
	}

	public String previous() throws Exception {
		try {

			String sequence1 = bean.getSequenceCode();
			OnlineProgramModel model = new OnlineProgramModel();
			model.initiate(context, session);

			String moduleCode = request.getParameter("moduleCodeValue");
			String programCode = request.getParameter("sectionProgramCode");

			String sectionCode = request.getParameter("sectionCodeStr");

			String from = request.getParameter("from");

			bean.setSource(from);

			//model.startTest(bean, request, moduleCode, programCode, sectionCode, from);
			
			model.getPrevious(bean, request, moduleCode, programCode,
					sectionCode);
			/**
			 * this if condition is checked if in case SEQUENCE VALUE is greater than 1 than the Previous Button
			 * should be visible. So the PreviousButton flag is set true...
			 */
			if (!(request.getParameter("sequenceCode") == null
					|| request.getParameter("sequenceCode").equals("") || request
					.getParameter("sequenceCode").equals("null"))) {
				int sequence = Integer.parseInt(bean.getSequenceCode());
				if (sequence > 1) {
					bean.setPreviousButton("true");
				}
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "testScreen";
	} //end of method

	/**
	 * Method : openAttachedFile().
	 * Purpose : Method to open attached file.
	 * @throws IOException - Exception.
	 */
	public void openUploadedFile() throws IOException {
		String addedFile = request.getParameter("fileName");
		final String[] extension = addedFile.replace(".", "#").split("#");
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.bean.getDataPath();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = "pdf";
			final String applnDoc = "doc";
			final String applnXls = "xls";
			final String applnXlsx = "xlsx";
			final String applnJpg = "jpg";
			final String applnTxt = "gif";
			final String applnGif = "txt";

			final String mimeTypeAcrobat = "acrobat";
			final String mimeTypeMSWord = "msword";
			final String mimeTypeMSExcel = "msexcel";
			final String mimeTypeJpg = "jpg";
			final String mimeTypeTxt = "gif";
			final String mimeTypeGif = "txt";

			if (applnPdf.equals(ext)) {
				mimeType = mimeTypeAcrobat;
			} else if (applnDoc.equals(ext)) {
				mimeType = mimeTypeMSWord;
			} else if (applnXls.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnXlsx.equals(ext)) {
				mimeType = mimeTypeMSExcel;
			} else if (applnJpg.equals(ext)) {
				mimeType = mimeTypeJpg;
			} else if (applnTxt.equals(ext)) {
				mimeType = mimeTypeTxt;
			} else if (applnGif.equals(ext)) {
				mimeType = mimeTypeGif;
			}

			response.setHeader("Content-type", "application/" + mimeType);
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ addedFile + "\"");

			int iChar;
			fsStream = new FileInputStream(filePath);
			oStream = response.getOutputStream();

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}
		} catch (final Exception e) {
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
	 * Sets ServletContext object
	 */
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
	
	public String getContent() throws Exception {
		
		String contentId = request.getParameter("contentId");
		
		OnlineProgramModel model = new OnlineProgramModel();
		model.initiate(context, session);
		if (contentId != null && !contentId.equals("")) {
			String content = model.getContent(contentId, request);
			System.out.println(content);
		}
		model.terminate();
		return "displayContent";
	}

}