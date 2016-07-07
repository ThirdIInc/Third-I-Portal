package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.paradyne.bean.D1.BusinessReqDocApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.BusinessReqDocApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class BusinessReqDocApprovalAction extends ParaActionSupport {
	/**
	 * Set input.
	 */
	private static final String RETURN_TYPE_INPUT = "input";

	/**
	 * Set Pending.
	 */
	private static final String STATUS_ONGOING = "ongoing";
	/**
	 * Send Back
	 */
	private static final String STATUS_CLOSE = "close";

	/**
	 * Set Hash.
	 */
	private static final String SPECL_CHAR_HASH = "#";
	/**
	 * Set GPG.
	 */
	private static final String EXTENSION_GPG = "jpg";
	/**
	 * GIF.
	 */
	private static final String EXTENSION_GIF = "txt";
	/**
	 * Set TXT.
	 */
	private static final String EXTENSION_TXT = "gif";

	BusinessReqDocApproval brdAppBean;

	public void prepare_local() throws Exception {
		brdAppBean = new BusinessReqDocApproval();
		brdAppBean.setMenuCode(2236);

	}

	/** (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {

		return brdAppBean;
	}

	/**
	 * @return the brdAppBean
	 */
	public BusinessReqDocApproval getBrdAppBean() {
		return brdAppBean;
	}

	/**
	 * @param brdAppBean the brdAppBean to set
	 */
	public void setBrdAppBean(BusinessReqDocApproval brdAppBean) {
		this.brdAppBean = brdAppBean;
	}

	/** 
	 * Method : CallPage().
	 * purpose - paging implementation purpose.
	 * @return input.
	 */
	public String callPage() {

		return BusinessReqDocApprovalAction.RETURN_TYPE_INPUT;
	}
	/**
	 * Method : input().
	 * Purpose : Set Lists.
	 *   
	 */
	public String input() {
		BusinessReqDocApprovalModel model = new BusinessReqDocApprovalModel();
		model.initiate(context, session);
		String userId = brdAppBean.getUserEmpId();
		String state = brdAppBean.getOngoingField();
		String state1 = brdAppBean.getSendbcakField();
		

		if (state.equals("f") || state.equals("")) {
			brdAppBean.setSendbcakField("");
			model.ongoingList(brdAppBean, request, userId);
			this.brdAppBean
					.setListType(BusinessReqDocApprovalAction.STATUS_ONGOING);

		}
		if (state1.equals("c")) {
			System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
			model.closeAppList(brdAppBean, request,userId);
			this.brdAppBean
					.setListType(BusinessReqDocApprovalAction.STATUS_CLOSE);

		}

		model.terminate();
		return INPUT;
	}
	/**
	 * Method : prepare_withLoginProfileDetails().
	 * Purpose : Setting Data Path.
	 */
	public void prepare_withLoginProfileDetails() {
		String poolName = String.valueOf(session.getAttribute("session_pool"));

		if (!("".equals(poolName) || poolName == null)) {
			poolName = "/" + poolName;
		}

		final String dataPath = this.getText("data_path") + "/upload" + poolName + "/BusinessRequirementDocumentApproval/";
		this.brdAppBean.setDataPath(dataPath);
	}

	/**
	 * Method : forward().
	 * purpose - Forward application to next level.
	 * @return String.
	 * @throws Exception - Exception.
	 */

	public String forward() throws Exception {
		try {
			BusinessReqDocApprovalModel model = new BusinessReqDocApprovalModel();
			model.initiate(context, session);

			String userID = this.brdAppBean.getUserEmpId();
			String fwdCode = brdAppBean.getFwdempCode().trim();
			String applicationId1 = brdAppBean.getViewCode().trim();
			String applicationId = brdAppBean.getBrdAppCode();

			boolean result = model.saveInfo(brdAppBean, userID, applicationId);
			String empId = brdAppBean.getFwdempCode();
			model.updateStatus(brdAppBean, empId,applicationId);
			model.updateCurrentStage(brdAppBean,applicationId);

			model.terminate();

			if (result) {
				addActionMessage("Application Forwarded Successfully ");
				sendMailToForwardedEmployee(applicationId, userID, fwdCode);
				this.sendMailToInitiator(brdAppBean, applicationId, fwdCode);
			} else {
				addActionMessage("Error occured sending application.");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	/**
	 * Method : sendBackApplication().
	 * Purpose : Send back application to initiators my action list.
	 * @return String.
	 */
	public String sendBackApplication() {
		try {
			final String applicationId = brdAppBean.getBrdAppCode();
			final String userId = this.brdAppBean.getUserEmpId();
			System.out.println("applicationId-----------------  " + applicationId);
			System.out.println("userId-----------------  " + userId);
			BusinessReqDocApprovalModel model = new BusinessReqDocApprovalModel();
			model.initiate(context, session);
			model.updateSendBackData(brdAppBean, applicationId);
			model.sendBack(applicationId, userId);
			///model.updateCurrentStage(brdAppBean,applicationId);
           sendBackMailToEmployee(applicationId, userId, applicationId);
			model.terminate();
			this.addActionMessage("Application Send Back successfully.");

		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**
	 * Method : viewApplication().
	 * Purpose : View Ongoing List Applications.
	 * @return String.
	 */
	public String viewApplication() {
		BusinessReqDocApprovalModel model = new BusinessReqDocApprovalModel();
		model.initiate(context, session);
		brdAppBean.setRoleFlag("true");
		brdAppBean.setStageFlag("true");
		brdAppBean.setDocFlag("true");
		brdAppBean.setAttachFile("");
		model.setDocumentType(brdAppBean);
		model.setRole(brdAppBean);
		model.setState(brdAppBean);
		String code = request.getParameter("id");
		/**
		 * Activity Log List.
		 */
		model.setDetails(brdAppBean, code);
		/**
		 * Project Information.
		 */
		model.viewData(brdAppBean, code);
		getNavigationPanel(1);
		return SUCCESS;

	}
	
	
	/**
	 * Method : viewClosedApplication().
	 * Purpose : View Closed Applications.
	 * @return String.
	 */
	public String viewClosedApplication()
	{
		BusinessReqDocApprovalModel model = new BusinessReqDocApprovalModel();
		model.initiate(context, session);
		String code = request.getParameter("brdAppId");
		System.out.println("ID ############ >>"+code);
		brdAppBean.setRoleFlag("false");
		brdAppBean.setStageFlag("false");
		brdAppBean.setDocFlag("false");
		/**
		 * Map Sets here.
		 */
		model.setRole(brdAppBean);
		model.setState(brdAppBean);
		model.setDocumentType(brdAppBean);
		model.setApproverDetails(brdAppBean,code);
		model.setDetails(brdAppBean, code);
		model.viewData(brdAppBean, code);
		getNavigationPanel(2);
		brdAppBean.setEnableAll("N");
		return SUCCESS;
	}

	/**
	 * Method : viewAttachedFile().
	 * Purpose : View attached file.
	 * @return String.
	 */
	public String viewAttachedFile() {
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));

			if (!("".equals(poolName) || poolName == null)) {
				poolName = "/" + poolName;
			}

			final String dataPath = this.getText("data_path") + "/upload" + poolName + "/BusinessRequirementDocumentApproval/";
			this.brdAppBean.setDataPath(dataPath);

			openAttachedFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * Method : closedApplication().
	 * purpose - To Close application .
	 * @return String.
	 */
	public String closeApplication() {
		try {
			final BusinessReqDocApprovalModel model = new BusinessReqDocApprovalModel();
			model.initiate(context, session);
			
			final String applicationId = this.brdAppBean.getBrdAppCode();
			final String userId = this.brdAppBean.getUserEmpId();
			final String applStatus = brdAppBean.getApplnStatus();
			final String fwdCode = brdAppBean.getFwdempCode();
			System.out.println("fwdCode-------------  "+ fwdCode);
			model.updateSendBackData(brdAppBean,applicationId);
			model.closed(brdAppBean, applicationId, applStatus,userId);
			//model.updateCurrentStage(brdAppBean,applicationId);
			model.terminate();
			this.addActionMessage("Application Closed successfully.");
			/**
			 * Mail Functionality.
			 */
			//applicationClosedMailToEmployee(applicationId, userId, fwdCode);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**
	 * purpose- to return back to list.
	 * @return input.
	 */
	public String cancel() {
		this.input();
		return INPUT;
	}

	/**
	 * Method : forwardToEmp().
	 * Purpose : Search particular employee.
	 * @return String.
	 */
	public String f9forwardToEmp() {

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";
		if (brdAppBean.getUserProfileDivision() != null && brdAppBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("	+ brdAppBean.getUserProfileDivision() + " )";
		} else {
			query += " WHERE 1=1 ";
		}

		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.name"), getMessage("employee.name")};

		String[] headerWidth = {"30", "70"};

		String[] fieldNames = {"forwardEmpToken", "forwardEmpName", "fwdempCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * Purpose : Method to open attached file.
	 * Purpose : Used to upload Document.
	 * @throws IOException - Exception.
	 */
	public void openAttachedFile() throws IOException {
		BusinessReqDocApprovalModel model = new BusinessReqDocApprovalModel();
		model.initiate(context, session);
		String fileCode = request.getParameter("viewCode11");
		String appCode = request.getParameter("appCode11");
		System.out.println("fileCode :"+fileCode);
		System.out.println("appCode :"+appCode);
		String filenameQuery = "SELECT BUSINESS_UPLOAD_FILE FROM HRMS_D1_BUSINESS_PATH where BUSINESS_CODE = "+fileCode+" AND BUSINESS_PROJ_CLOSE_BY ="+appCode;   
				
		Object[][] fileObj = model.getSqlModel().getSingleResult(filenameQuery);
if(fileObj!= null && fileObj.length>0) {
		final String addedFile = String.valueOf(fileObj[0][0]);
		final String[] extension = addedFile.replace(".", BusinessReqDocApprovalAction.SPECL_CHAR_HASH).split(BusinessReqDocApprovalAction.SPECL_CHAR_HASH);
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.brdAppBean.getDataPath();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = "pdf";
			final String applnDoc = "doc";
			final String applnXls = "xls";
			final String applnXlsx = "xlsx";
			final String applnJpg = BusinessReqDocApprovalAction.EXTENSION_GPG;
			final String applnTxt = BusinessReqDocApprovalAction.EXTENSION_GIF;
			final String applnGif = BusinessReqDocApprovalAction.EXTENSION_TXT;

			final String mimeTypeAcrobat = "acrobat";
			final String mimeTypeMSWord = "msword";
			final String mimeTypeMSExcel = "msexcel";
			final String mimeTypeJpg = BusinessReqDocApprovalAction.EXTENSION_GPG;
			final String mimeTypeTxt = BusinessReqDocApprovalAction.EXTENSION_GIF;
			final String mimeTypeGif = BusinessReqDocApprovalAction.EXTENSION_TXT;

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
			response.setHeader("Content-disposition", "attachment;filename= \"" + addedFile + "\"");

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
	}

	/**
	 *  Method : f9employee().
	 *  Purpose : Set an perticular employee.
	 * @return String
	 */
	public String f9employee() {

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";
		if (brdAppBean.getUserProfileDivision() != null
				&& brdAppBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ brdAppBean.getUserProfileDivision() + " )";
		} else {
			query += " WHERE 1=1 ";
		}

		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { "Employee Id", getMessage("employee.name") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "forwardEmpToken", "forwardEmpName", "fwdempCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	/**
	 * Method : sendMailToInitiator.
	 * Purpose : System generated mail to Initiator.
	 * @param brdAppBean : Used to get User Employee Id.
	 * @param applicationId : Application Id.
	 * @param fwdCode : Forwarded Employee Id.
	 */
	private void sendMailToInitiator(final BusinessReqDocApproval brdAppBean, final String applicationId, final String fwdCode) {
		/**
		 * Functionality (Mail From System to Applicant ).
		 */
		
		final EmailTemplateBody templateApplicant = new EmailTemplateBody();
		templateApplicant.initiate(context, session);
		templateApplicant.setEmailTemplate("BUSINESS REQUIREMNENT MAIL TO INITIATOR EMPLOYEE");
		templateApplicant.getTemplateQueries();
		
		/**
		 * FROM Mail Id.
		 */
		final EmailTemplateQuery templateQueryApp1 = templateApplicant.getTemplateQuery(1); 
		/**
		 *  TO Mail Id.
		 */
		final EmailTemplateQuery templateQueryApp2 = templateApplicant.getTemplateQuery(2);
		templateQueryApp2.setParameter(1, brdAppBean.getUserEmpId());

		final EmailTemplateQuery templateQueryApp3 = templateApplicant.getTemplateQuery(3);
		templateQueryApp3.setParameter(1, applicationId);
		
		final EmailTemplateQuery templateQueryApp4 = templateApplicant.getTemplateQuery(4);
		templateQueryApp4.setParameter(1, applicationId);
		
		final EmailTemplateQuery templateQueryApp5 = templateApplicant.getTemplateQuery(5);
		templateQueryApp5.setParameter(1, applicationId);
		
		final EmailTemplateQuery templateQueryApp6 = templateApplicant.getTemplateQuery(6);
		templateQueryApp6.setParameter(1, applicationId);
		
		templateApplicant.configMailAlert();
		templateApplicant.sendApplicationMail();
		templateApplicant.clearParameters();
		templateApplicant.terminate();
		
	}
	
	
	
	/**
	 * Method : sendMailToForwardedEmployee().
	 * Purpose : Project Forwarded Mail to Employee. 
	 * @param applnID
	 * @param userID
	 * @param approver
	 */
	public void sendMailToForwardedEmployee(final String applicationId , final String userID, final String fwdCode) {
		try {

			final  EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("BUSINESS REQUIREMNENT APPROVER MAIL FROM APPROVER TO FORWARDED EMPLOYEE");
			template.getTemplateQueries();
			/**
			 * FROM Mail Id.
			 */
			final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
			templateQuery1.setParameter(1, userID);
			/**
			 * To Mail Id.
			 */
			final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
			templateQuery2.setParameter(1, fwdCode);

			final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);

			final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);

			final EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);

			template.configMailAlert();
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Method : sendBackMailToEmployee().
	 * Purpose : Send BAck Project Mail to Employee. 
	 * @param applnID
	 * @param userID
	 * @param approver
	 */
	public void sendBackMailToEmployee(final String applnID, final String userID, final String approver) {
		try {

			final  EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("BUSINESS REQUIREMNENT APPROVER MAIL FROM APPROVER TO FORWARDED EMPLOYEE AND APPLICANT");
			template.getTemplateQueries();
			/**
			 * FROM Mail Id.
			 */
			final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
			templateQuery1.setParameter(1, userID);
			/**
			 * To Mail Id.
			 */
			final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
			templateQuery2.setParameter(1, approver);

			final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			final EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);

			template.configMailAlert();
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Method : applicationClosedMailToEmployee().
	 * Purpose : Send Project Close Mail to Employee. 
	 * @param applnID
	 * @param userID
	 * @param approver
	 */
	public void applicationClosedMailToEmployee(final String applnID, final String userID, final String approver) {
		try {

			final  EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("BUSINESS REQUIREMNENT APPROVER MAIL FROM APPROVER TO EMPLOYEE");
			template.getTemplateQueries();
			/**
			 * FROM Mail Id.
			 */
			final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); 
			templateQuery1.setParameter(1, userID);
			/**
			 * To Mail Id.
			 */
			final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
			templateQuery2.setParameter(1, approver);

			final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applnID);

			final EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);

			template.configMailAlert();
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
