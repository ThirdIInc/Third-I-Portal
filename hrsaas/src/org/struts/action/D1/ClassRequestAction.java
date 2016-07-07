package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.paradyne.bean.D1.ClassRequestBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ClassRequestModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 * 
 */
public class ClassRequestAction extends ParaActionSupport {
	
	/**
	 * Set input.
	 */
	private static final String RETURN_TYPE_INPUT = "input";
	/**
	 * Set success.
	 */
	private static final String RETURN_TYPE_SUCCESS = "success"; 
	/**
	 * Set LeaveApplication Code.
	 */
	private static final String	 LEAVEAPP_CODE = "levApplicationCode"; 
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
	/**
	 * Set F9 page.
	 */
	private static final String F9_PAGE = "f9page";
	/**
	 * Set N.
	 */
	private static final String SETENABLE_ALL_N = "N";
	/**
	 * Set false.
	 */
	private static final String FLAG_FALSE = "false";
	
	/**
	* Set duplicate mesg. 
	*/
	private static final String DUPLICATE_MESG = "duplicate";
	/**
	 * Set Application Code.
	 */
	private static final String APPL_CODE = "applCode";
	
	

	/**
	 *  Object of ClassRequestBean.
	 */
	private ClassRequestBean classreqBean;

	/**
	 * (non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_local().
	 */
	public void prepare_local()  {
		this.classreqBean = new ClassRequestBean();
		this.classreqBean.setMenuCode(2008);

	}

	/**
	 * (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel().
	 * @return Object.
	 */
	public Object getModel() {

		return this.classreqBean;
	}

	/**
	 * @return bean object.
	 */
	public ClassRequestBean getClassreqBean() {
		return this.classreqBean;
	}

	/**
	 * @param classreqBean - bean object.
	 */
	public void setClassreqBean(final ClassRequestBean classreqBean) {
		this.classreqBean = classreqBean;
	}

	/**
	 * (non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 * Purpose  - Used to set Data path.
	 */
	public void prepare_withLoginProfileDetails() {

		String poolName = String.valueOf(session.getAttribute("session_pool"));

		if (!("".equals(poolName) || poolName == null)) {
			poolName = "/" + poolName;
		}

		final String dataPath = this.getText("data_path") + "/upload" + poolName + "/ClassRequest/";
		this.classreqBean.setDataPath(dataPath);

	}

	/**
	 * (non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input().
	 * @return String.
	 */
	public String input() {
		final String userId = this.classreqBean.getUserEmpId();

		final ClassRequestModel model = new ClassRequestModel();
		model.initiate(context, session);
		model.initialData(this.classreqBean, request, userId);
		model.pendingData(this.classreqBean, request, userId);
		model.sendBack(this.classreqBean, request, userId);
		model.approvedData(this.classreqBean, request, userId);
		model.rejectedData(this.classreqBean, request, userId);
		model.approvedCancelData(this.classreqBean, request, userId);
		model.approvedRejectedData(this.classreqBean, request, userId);
		model.cancel(this.classreqBean, request, userId);

		model.terminate();
		this.getNavigationPanel(1);
		return ClassRequestAction.RETURN_TYPE_INPUT;
	}

	/**
	 * purpose -Method to get list of applications as per the list type, when clicked on paging buttons.
	 * @return input.
	 */
	public String callPage()  {

		final ClassRequestModel model = new ClassRequestModel();
		model.initiate(context, session);
		this.input();
		this.getNavigationPanel(1);
		model.terminate();

		return ClassRequestAction.RETURN_TYPE_INPUT;
	}

	/**
	 * purpose - Method to add new application.
	 * @return success
	 */
	public String addNew() {

		final ClassRequestModel model = new ClassRequestModel();
		model.initiate(context, session);
		this.reset();
		model.getcurrentUserAndSysDate(this.classreqBean);
		this.getNavigationPanel(2);
		return ClassRequestAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * Added for clearing form.
	 * @return success
	 */
	public String reset() {
		this.classreqBean.setClassName("");
		this.classreqBean.setClassDescription("");
		this.classreqBean.setClassDivision("");
		this.classreqBean.setPreRequisite("");
		this.classreqBean.setInstructorName("");
		this.classreqBean.setStartDate("");
		this.classreqBean.setEndDate("");
		this.classreqBean.setDurationofClass("");
		this.classreqBean.setStartTime("");
		this.classreqBean.setEndTime("");
		this.classreqBean.setClassRoom("");
		this.classreqBean.setMaxnumOfStudents("");
		this.classreqBean.setLocation("");
		this.classreqBean.setAddress("");
		this.classreqBean.setTelephone("");
		this.classreqBean.setContactName("");
		this.classreqBean.setHotelInformation("");
		this.classreqBean.setComments("");
		this.classreqBean.setClassCode("");
		this.classreqBean.setDivId("");
		this.classreqBean.setLocId("");
		this.classreqBean.setClassCode("");
		this.classreqBean.setHotelAttachments("");
		this.classreqBean.setHotelFile("");

		this.getNavigationPanel(2);
		return ClassRequestAction.RETURN_TYPE_SUCCESS;

	}

	/**
	 * purpose - Method call for Saving Data.
	 * @return input()
	 * @throws Exception - if  input () throws Exception
	 */
	public String saveDraftData() throws Exception {
		try {

			final ClassRequestModel model = new ClassRequestModel();
			model.initiate(context, session);
			boolean result;

			final String userID = this.classreqBean.getUserEmpId();

			if ("".equals(this.classreqBean.getClassCode())) {
				result = model.insertBusinessData(this.classreqBean, userID);

				if (result) {

					this.addActionMessage(this.getMessage("save"));

				} else {
					this.addActionMessage(this.getMessage(ClassRequestAction.DUPLICATE_MESG));
					this.reset();

				}
			} else {

				result = model.updateBusinessData(this.classreqBean);

				if (result) {
					this.addActionMessage(this.getMessage("update"));

				} else {
					this.addActionMessage(this.getMessage(ClassRequestAction.DUPLICATE_MESG));
					this.reset();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.input();
		return ClassRequestAction.RETURN_TYPE_INPUT;
	}

	/**
	 * purpose - Sending Applicant to approver mail.
	 * @param applicationId - Unique application id.
	 * @param userID -Users unique id.
	 * @throws Exception -Exception.
	 */
	private void sendApplicantToApprovalMail(final String applicationId, final String userID) throws Exception {
		try {

			// MAIL FROM Applicant To Approver
			final ClassRequestModel model = new ClassRequestModel();
			model.initiate(context, session);

			final String query = " SELECT NVL(APP_SETTINGS_EMP_ID,0) AS APPROVER_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPROVER_NAME " 	+ " FROM HRMS_D1_APPROVAL_SETTINGS " + " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)" 	+ " WHERE APP_SETTINGS_TYPE = 'E' ORDER BY APP_SETTINGS_EMP_ID ";
			final Object[][] data = model.getSqlModel().getSingleResult(query);

			final String query1 = " SELECT NVL(APP_EMAIL_ID,0) AS APPROVER_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPROVER_NAME " + " FROM HRMS_D1_APPROVAL_SETTINGS " + " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)" + " WHERE APP_SETTINGS_TYPE = 'E'  AND APP_EMAIL_ID IS NOT NULL ORDER BY APP_SETTINGS_EMP_ID ";
			final Object[][] data1 = model.getSqlModel().getSingleResult(query1);

			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-CLASS REQUEST APPLICANT TO APPROVER");
			templateApp.getTemplateQueries();

			/**
			 * FROM EMAIL
			 */
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);

			templateQueryApp1.setParameter(1, userID);
			/**
			 * TO EMAIL
			 */
			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);
			templateQueryApp2.setParameter(1, String.valueOf(data[0][0]));
			/**
			 * TABLE DATA
			 */

			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			System.out.println("applicationId here is ========="	+ applicationId);

			/**
			 * Others.
			 */

			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, String.valueOf(data[0][0]));

			/**
			 * APPLICANT NAME.
			 */
			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userID);

			/**
			 * location.
			 */
			final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			/**
			 * hotel info.
			 */

			final EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, applicationId);

			String[] empData = null;

			if (data != null && data.length > 1) {
				empData = new String[data.length];
				for (int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
			}

			templateApp.configMailAlert();
			/*
			 * String[] attachedFile = new String[1]; attachedFile[0] =
			 * classreqBean.getDataPath() + classreqBean.getHotelFile();
			 * System.out.println("attachedFile[0]========" + attachedFile[0]);
			 * 
			 * templateApp.sendApplMailWithAttachment(attachedFile);
			 */
			if (data1 != null && data1.length > 0) {
				templateApp.sendApplicationMailToGroups(data1);

			}

			// templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
			// MAIL FROM Applicant To Approver End
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * purpose - Cancel particular application.
	 * 
	 * @return String.
	 */
	public String cancel() {
		
		final String status = this.classreqBean.getStatus();
		try {
			final ClassRequestModel model = new ClassRequestModel();
			model.initiate(context, session);
			final boolean result = model.cancelFunction(this.classreqBean,
					status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application successfully send for cancellation.");
				
			} else {
				this.addActionMessage("Error occured sending cancellation request.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}

	/**
	 * purpose - Edit particular record.
	 * @return success
	 * @throws Exception -Exception.
	 */
	public String editBusinessData()  throws Exception {
		try {
			// setApproverName();
			String classId = request.getParameter("classCode");
			final ClassRequestModel model = new ClassRequestModel();
			model.initiate(context, session);
			/*
			 * FOR SUPER USER
			 */
			final String applCode = request.getParameter(ClassRequestAction.APPL_CODE);
			if (applCode != null && applCode.length() > 0) {
				classId = applCode;
			}
			model.editBusinessData(this.classreqBean, classId);
			this.getNavigationPanel(2);
			this.classreqBean.setEnableAll("Y");
			model.terminate();

			if (applCode != null && applCode.length() > 0) {
				this.getNavigationPanel(0);
				this.classreqBean.setEnableAll(ClassRequestAction.SETENABLE_ALL_N);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return ClassRequestAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * purpose - Delete Particular record.
	 * @return input
	 * @throws Exception - Exception.
	 */
	public String delete() throws Exception {
		final ClassRequestModel model = new ClassRequestModel();
		model.initiate(context, session);

		boolean result;
		result = model.delete(this.classreqBean);

		if (result) {
			this.addActionMessage("Record Deleted successfully.");

		}
		model.terminate();
		this.input();
		this.getNavigationPanel(1);

		return ClassRequestAction.RETURN_TYPE_INPUT;
	}

	
	/**
	 * purpose - This method Sets the Division.  
	 * @return f9page.
	 */
	public String f9division()  {

		String query = "select DIV_ID, DIV_NAME from HRMS_DIVISION WHERE IS_ACTIVE ='Y'";

		if (this.classreqBean.getUserProfileDivision() != null && this.classreqBean.getUserProfileDivision().length() > 0) {

			query += " AND DIV_ID IN (" + this.classreqBean.getUserProfileDivision() + ")";
			query += " ORDER BY  DIV_ID ";
		}

		final String[] headers = {"DivisionId", this.getMessage("division")};

		final String[] headerWidth = {"25", "75"};

		final String[] fieldNames = {"divId", "classDivision"};

		final int[] columnIndex = {0, 1};

		final String submitFlag = ClassRequestAction.FLAG_FALSE;

		final String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return ClassRequestAction.F9_PAGE;

	}

	/**
	 * THIS METHOD IS USED FOR SELECTING LOCATION.
	 * @return f9page.
	 */
	public String f9location()  {

		final String query = "select LOCATION_CODE, LOCATION_NAME from HRMS_LOCATION";

		final String[] headers = {"LocationId", this.getMessage("location")};

		final String[] headerWidth = {"20", "80"};

		final String[] fieldNames = {"locId", "location"};

		final int[] columnIndex = {0, 1};

		final String submitFlag = ClassRequestAction.FLAG_FALSE;

		final String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return ClassRequestAction.F9_PAGE;

	}

	/**
	 * Purpose - Method to open attached file.
	 * @throws IOException - Exception.
	 */
	public void openAttachedFile() throws IOException {
		
		final String addedFile = this.classreqBean.getHotelFile();
		final String[] extension = addedFile.replace(".", ClassRequestAction.SPECL_CHAR_HASH).split(ClassRequestAction.SPECL_CHAR_HASH);
		final String ext = extension[extension.length - 1];
		String mimeType = "";
		final String dataPath = this.classreqBean.getDataPath();
		final String filePath = dataPath + addedFile;

		OutputStream oStream = null;
		FileInputStream fsStream = null;

		try {
			final String applnPdf = "pdf";
			final String applnDoc = "doc";
			final String applnXls = "xls";
			final String applnXlsx = "xlsx";
			final String applnJpg = ClassRequestAction.EXTENSION_GPG;
			final String applnTxt = ClassRequestAction.EXTENSION_GIF;
			final String applnGif = ClassRequestAction.EXTENSION_TXT;
              
			final String mimeTypeAcrobat = "acrobat";
			final String mimeTypeMSWord = "msword";
			final String mimeTypeMSExcel = "msexcel";
			final String mimeTypeJpg = ClassRequestAction.EXTENSION_GPG;
			final String mimeTypeTxt = ClassRequestAction.EXTENSION_GIF;
			final String mimeTypeGif = ClassRequestAction.EXTENSION_TXT;
              
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

	/**
	 * purpose - Method to view details..
	 * @return success
	 * @throws Exception - Exception.
	 */
	public String retrivePendingDetails() throws Exception {
		try {
			final ClassRequestModel model = new ClassRequestModel();
			model.initiate(context, session);
			String id = request.getParameter(ClassRequestAction.LEAVEAPP_CODE);

			final String applCode = request.getParameter(ClassRequestAction.APPL_CODE);
			if (applCode != null && applCode.length() > 0) {
				id = applCode;
			}

			model.view(this.classreqBean, id);

			model.terminate();

			this.getNavigationPanel(3);

			this.classreqBean.setEnableAll(ClassRequestAction.SETENABLE_ALL_N);

			if (applCode != null && applCode.length() > 0) {
				this.getNavigationPanel(0);
				this.classreqBean.setEnableAll(ClassRequestAction.SETENABLE_ALL_N);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return ClassRequestAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * Purpose - Cancel Application.
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String cancelDetails() throws Exception {
		try {
			
			final ClassRequestModel model = new ClassRequestModel();
			model.initiate(context, session);
			final String id = request.getParameter(ClassRequestAction.LEAVEAPP_CODE);
			System.out.println("hello id=---" + id);
			model.view(this.classreqBean, id);

			model.terminate();

			this.getNavigationPanel(4);
			this.classreqBean.setEnableAll(ClassRequestAction.SETENABLE_ALL_N);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return ClassRequestAction.RETURN_TYPE_SUCCESS;
	}
	
	
	/**
	 * purpose - Send application for approval.
	 * @return this.input();
	 * @throws Exception - Exception.
	 */
	public String approve() throws Exception {
		try {
			final ClassRequestModel model = new ClassRequestModel();
			model.initiate(context, session);

			final String checkApproverQuery = "SELECT APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS where APP_SETTINGS_TYPE = 'E'";
			final Object[][] chk = model.getSqlModel().getSingleResult(
					checkApproverQuery);

			if (chk != null && chk.length > 0) {

				final String userID = this.classreqBean.getUserEmpId();
				final boolean result = model.sendForApprovalFunction(
						this.classreqBean, userID);

				final String applicationId = this.classreqBean.getClassCode();
				model.terminate();
               


				if (result) {
					 String traNumber = classreqBean.getTrackingNum();
					 System.out.println("traNumber *************************** =" + traNumber);
					this.addActionMessage("Application Successfully Send For Approval.\n Tracking Number : " + traNumber);
					
					this.sendApplicantToApprovalMail(applicationId, userID);
				} else {
					this.addActionMessage("Error occured sending application.");
				}

			} else {

				this.addActionMessage("Application cannot be forwarded for approval as there is no Education Ministry approver is defined in the application ");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return ClassRequestAction.RETURN_TYPE_INPUT;
	}
}
