package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.D1.InformationSystemChangeRequestForm;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.InformationSystemChangeRequestApprModel;
import org.paradyne.model.D1.InformationSystemChangeRequestModel;
import org.paradyne.model.common.ReportingModel;
import org.struts.lib.ParaActionSupport;

public class InformationSystemChangeRequestAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(InformationSystemChangeRequestAction.class);

	InformationSystemChangeRequestForm bean;

	public String addNew() {
		try {
			bean.setTrackingFlag("false");
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/"
					+ poolName + "/";
			bean.setDataPath(dataPath);
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			model.getSysDate(bean);
			// reset();
			// model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
			// employee branch,designation etc

			if (bean.isGeneralFlag()) {
				bean.setEmployeeCode(bean.getEmployeeCode());
				model.getEmployeeDetails(bean.getUserEmpId(), bean);
			} else {
				model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
				// employee
				// branch,designation etc
			}
			bean.setFeedbackSubmitFlag(false);
			setApproverName();
			setApproverList(bean.getEmployeeCode()); // setting approver list
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return SUCCESS;
	}

	public void setApproverList(String empCode) {
		try {
			// bean.setFirstApproverCode("");
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			if (!empCode.equals("")) {
				Object[][] empFlow = model1.generateEmpFlow(empCode, "D1");
				model.setApproverData(bean, empFlow);
			}
			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
	}

	public void setApproverName() {
		InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
		try {
			// String claimId = request.getParameter("claimId");
			model.initiate(context, session);
			Object[][] empFlow = null;
			try {
				empFlow = generateEmpFlow(bean.getEmployeeCode(), "D1", 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id from hrms_emp_offc "
						+ " where emp_id=" + String.valueOf(empFlow[0][0]);
				Object data[][] = model.getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {
					bean.setApproverName(String.valueOf(data[0][0]));
					bean.setFirstApproverCode(String.valueOf(data[0][1]));
				}
			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
	}

	public void setApproverDetails(String code) {
		try {
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			Object[][] empFlow = generateEmpFlow(code, "D1", 1);
			if (empFlow != null && empFlow.length > 0) {
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id from hrms_emp_offc "
						+ " where emp_id=" + String.valueOf(empFlow[0][0]);
				Object data[][] = model.getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setApproverName(String.valueOf(data[0][0]));
					bean.setFirstApproverCode(String.valueOf(data[0][1]));
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InformationSystemChangeRequestForm getBean() {
		return bean;
	}

	public void setBean(InformationSystemChangeRequestForm bean) {
		this.bean = bean;
	}

	public String back() throws Exception {
		InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
		model.initiate(context, session);
		String listType = model.checkNull(bean.getListType());
		if ("".equals(listType) || "pending".equals(listType)) {
			input();
		} else if ("approved".equals(listType)) {
			getApprovedList();
		} else if ("rejected".equals(listType)) {
			getRejectedList();
		}	
		return INPUT;
	}

	/**
	 * To set the page according to the page numbers
	 */
	public String callPage() throws Exception {
		input();
		return INPUT;
	}

	public Object getModel() {
		return bean;
	}

	public String input() {
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
			bean.setDataPath(dataPath);
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getPendingList(bean, request, userId);
			}
			bean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}

	 
	public void prepare_local() throws Exception {
		bean = new InformationSystemChangeRequestForm();
		bean.setMenuCode(2056);
	}

	/**
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9Approver() {
		String str = "0";
		/*
		 * if (!bean.getInitiatorCode().equals("")){ str =
		 * bean.getEmployeeCode();
		 * 
		 * System.out.println("bean.getInitiatorCode()" +
		 * bean.getInitiatorCode()); }
		 */

		if (!bean.getFirstApproverCode().equals("")) {
			str = bean.getFirstApproverCode();
		}

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";

		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		// query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN("
				+ bean.getInitiatorCode() + "," + str + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "approverToken", "selectapproverName",
				"approverCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * For Selecting Employee
	 * 
	 * @return String
	 */
	public String f9Employee() {
		String str = "0";

		if (!bean.getEmployeeCode().equals("")) {
			str = bean.getEmployeeCode();

			System.out.println("bean.getEmployeeCode()"
					+ bean.getEmployeeCode());
		}

		/*
		 * if(!bean.getFirstApproverCode().equals("")) { str
		 * +=","+bean.getFirstApproverCode();
		 * System.out.println("bean.getFirstApproverCode()" +
		 * bean.getFirstApproverCode()); }
		 */

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
				+ "	EMP_ID  FROM HRMS_EMP_OFFC ";

		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		// query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "employeeToken", "employeeName", "employeeCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * For Selecting Course Name
	 * 
	 * @return String
	 */
	public String f9CourseName() {
		String query = "SELECT 	CLASS_REQUEST_ID , CLASS_NAME  FROM HRMS_D1_CLASS_REQUEST  where STATUS = 'A'";

		// query += getprofileQuery(bean);

		query += "	ORDER BY HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID";

		String[] headers = { getMessage("course.id"), getMessage("course.name") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "courseId", "courseName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "ClassEnrollmentForm_getCourseDetailsAction.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String draftFunction() throws Exception {
		try {

			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			boolean result;
			if (bean.getHiddenCode().equals("")) {
				result = model.draftFunction(bean, request);
				if (result) {
					addActionMessage("Application drafted successfully.");
				} else {
					addActionMessage("Error occured");
					// reset();
				}
			} else {
				result = model.updateRecords(bean, request);
				if (result) {
					addActionMessage("Application modified successfully.");
				} else {
					addActionMessage("Error occured");
					// /reset();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	public String feedbackFunction() throws Exception {
		try {
			String applicationId = bean.getHiddenCode();
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			boolean result;
			result = model.feedbackFunction(bean, request, applicationId);
			if (result) {
				addActionMessage("Feedback send successfully.");
				String path = bean.getDataPath();
				String fileanme = bean.getUploadFileName();
				String optioanlfileanme = bean.getUploadOptionalFileName();
				sendFeedbackMail(path, fileanme, optioanlfileanme);
			} else {
				addActionMessage("Error occured");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	public String sendForApprovalFunction() {
		try {
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			boolean result = model.sendForApprovalFunction(bean, request);
			model.terminate();
			if (result) {
				String trackCOde = bean.getTrackingNo();
				addActionMessage("Application send for approval successfully \n Tracking No: "
						+ trackCOde);
				String path = bean.getDataPath();
				String fileanme = bean.getUploadFileName();
				String optioanlfileanme = bean.getUploadOptionalFileName();
				sendMail(path, fileanme, optioanlfileanme);
			} else {
				addActionMessage("Error occured sending application.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	private void sendMail(String path, String fileanme, String optioanlfileanme) {
		// Mail From Applicant to Approver
		String applicationID = bean.getHiddenCode();
		// String apprCode = bean.getApproverCode();
		String userId = bean.getUserEmpId();
		InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
		// MAIL FROM APPROVER TO MANAGER
		model.initiate(context, session);

		EmailTemplateBody template = new EmailTemplateBody();
		template.initiate(context, session);

		template.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE REQUEST DETAILS TO APPROVER");
		template.getTemplateQueries();

		EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);  
		templateQuery1.setParameter(1, userId);

		EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); 
		String approverCode = "0";
		if (!bean.getApproverCode().equals("")) {
			approverCode = bean.getApproverCode();
		} else {
			approverCode = bean.getFirstApproverCode();
		}

		templateQuery2.setParameter(1, approverCode);

		EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
		templateQuery3.setParameter(1, applicationID);

		EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
		templateQuery4.setParameter(1, applicationID);

		EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
		templateQuery5.setParameter(1, applicationID);
		template.configMailAlert();
		// template.sendProcessManagerAlert(approver, module, msgType,
		// applnID, level);
		if (!fileanme.equals("")) {
			String[] attachment = new String[1];
			attachment[0] = path + fileanme;
			template.sendApplMailWithAttachment(attachment);
		} else if (!optioanlfileanme.equals("")) {
			String[] attachment = new String[1];
			attachment[0] = path + optioanlfileanme;
			template.sendApplMailWithAttachment(attachment);
		} else {
			template.sendApplicationMail();
		}
		template.clearParameters();
		template.terminate();
		// Mail From Applicant to Approver End
	}

	private void sendFeedbackMail(String path, String fileanme, String optioanlfileanme) {
		String applicationID = bean.getHiddenCode();
		String userId = bean.getUserEmpId();
		try {
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			
			String approverCode = "0";
			if (!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE REQUEST DETAILS FEEDBACK FROM  APPLICANT");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, String.valueOf(approverCode));
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationID);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationID);

			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationID);
			templateApp.configMailAlert();
			
			
			String[] attachment = new String[1];
			if (!fileanme.equals("")) {
				attachment[0] = path + fileanme;
			} else if (!optioanlfileanme.equals("")) {
				attachment[0] = path + optioanlfileanme;
			}
			
			String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID FROM HRMS_D1_APPROVAL_SETTINGS " + 
						   " WHERE APP_SETTINGS_TYPE = 'N' AND APP_EMAIL_ID IS NOT NULL ";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data, attachment);
			}

			String apprQuery = " SELECT DISTINCT INF_CNG_APPR_CODE FROM HRMS_D1_INF_CNG_PATH WHERE INF_CNG_CODE = " + applicationID + 
							   "AND HRMS_D1_INF_CNG_PATH.INF_CNG_APPR_CODE NOT IN (" + String.valueOf(approverCode) + ")";
			Object apprDataObj[][] = model.getSqlModel().getSingleResult(apprQuery);
			if (apprDataObj != null && apprDataObj.length > 0) {
				String[] keepInfoId = new String[apprDataObj.length];
				for (int i = 0; i < apprDataObj.length; i++) {
					keepInfoId[i] = String.valueOf(apprDataObj[i][0]);
				}
				
				if (!fileanme.equals("")) {
					templateApp.sendApplMailWithAttachmentToKeepInf(keepInfoId,attachment);
				} else {
					templateApp.sendApplMailWithAttachmentToKeepInf(keepInfoId,null);
				}
			} else {
				if (!fileanme.equals("")) {
					templateApp.sendApplMailWithAttachment(attachment);
				} else {
					templateApp.sendApplicationMail();
				}
			}
			
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String editApplicationFunction() {
		try {
			bean.setTrackingFlag("true");

			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/"
					+ poolName + "/";
			bean.setDataPath(dataPath);
			String requestID = request.getParameter("infoSysReqId");
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			model.editApplicationFunction(bean, requestID);
			String query = "SELECT CREATED_BY FROM  HRMS_D1_INF_CNG_REQ WHERE INF_CNG_CODE="
					+ requestID;
			Object[][] codeObj = model.getSqlModel().getSingleResult(query);
			String code = String.valueOf(codeObj[0][0]);
			setApproverDetails(code);
			setApproverList(code); // setting approver list
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return SUCCESS;
	}

	public String viewApplicationFunction() {
		try {
			bean.setTrackingFlag("true");
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
			bean.setDataPath(dataPath);
			String requestID = request.getParameter("infoSysReqId");
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);

			/*
			 * FOR SUPER USER
			 */
			String applCode = request.getParameter("applCode");
			if (applCode != null && applCode.length() > 0) {
				requestID = applCode;
			}

			model.viewApplicationFunction(bean, requestID);
			
			String query = "SELECT CREATED_BY FROM  HRMS_D1_INF_CNG_REQ WHERE INF_CNG_CODE="
					+ requestID;
			Object[][] codeObj = model.getSqlModel().getSingleResult(query);
			String code = String.valueOf(codeObj[0][0]);
			setApproverDetails(code);
			setApproverList(code); // setting approver list

			String status = bean.getStatus();
			if (status.equals("A") || status.equals("C")) {
				bean.setFeedbackFlag(true);
				bean.setFeedbackSubmitFlag(false);
				bean.setReOpenCommentsFlag(true);
				bean.setEnableAll("N");
				getNavigationPanel(6);
			} else if (status.equals("X")) {
				bean.setFeedbackFlag(false);
				bean.setFeedbackSubmitFlag(true);
				bean.setReOpenCommentsFlag(false);
				bean.setEnableAll("N");
				getNavigationPanel(2);
			} else if (status.equals("P") || status.equals("B")) {
				bean.setFeedbackFlag(false);
				bean.setFeedbackSubmitFlag(false);
				bean.setEnableAll("N");
				getNavigationPanel(2);
			} else {
				bean.setFeedbackFlag(false);
				bean.setFeedbackSubmitFlag(false);
				bean.setEnableAll("N");
				getNavigationPanel(2);
			}
			model.terminate();
			if (applCode != null && applCode.length() > 0) {
				getNavigationPanel(0);
				bean.setEnableAll("N");
			}
			model.getApproverCommentList(bean, requestID);
			
		} catch (Exception e) {
			getNavigationPanel(0);
			bean.setEnableAll("N");
			e.printStackTrace();
		}
		// bean.setEnableAll("N");
		// getNavigationPanel(2);
		return SUCCESS;
	}

	public String getApprovedList() throws Exception {
		try {
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getApprovedList(bean, request, userId);
			}
			bean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		getNavigationPanel(1);
		return INPUT;
	}

	public String getRejectedList() throws Exception {
		try {
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getRejectedList(bean, request, userId);
			}
			bean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}

	public String reset() throws Exception {
		try {
			/*
			 * bean.setCourseName(""); bean.setCourseLocationName("");
			 * bean.setStartDate(""); bean.setEndDate("");
			 */
			bean.setChangeTitle("");
			bean.setChangeSchedularOccur("");
			bean.setChangeCategory("");
			bean.setReason("");
			bean.setWhatChange("");
			bean.setImpactChange("");
			bean.setRiskAssociatedChange("");
			bean.setExpectResult("");
			bean.setCurrentStatusChange("");
			bean.setDetailPlanAction("");
			bean.setUploadFileName("");

			bean.setUploadOptionalFileName("");
			bean.setBackoutPlanEstimate("");
			bean.setWhoPerformChangeTesting("");
			bean.setHowChangeTested("");
			bean.setUpdateOptional("");
			bean.setSelectapproverName("");
			bean.setApproverCode("");
			bean.setApproverToken("");

			getNavigationPanel(3);
		} catch (Exception e) {
			logger.error("Error in reset" + e);

		}
		return SUCCESS;
	}

	/**
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9deptNumber() {

		String query = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY  DEPT_ID DESC ";

		// query += getprofileQuery(bean);

		// query += " ORDER BY HRMS_D1_DEPARTMENT.DEPT_ID";

		String[] headers = { "Department Id", "Department Name" };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "departmentCode", "departmentName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");

			/*
			 * MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,
			 * response);
			 */

			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				} // end of if
				fileName = uploadFileName;
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} // end of if
				else if (ext.equals("doc")) {
					mimeType = "msword";
				} // end of else if
				else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} // end of else if
				else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} // end of else
				else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} // end of else if
				else if (ext.equals("txt")) {
					mimeType = "txt";
				} // end of else if
				else if (ext.equals("gif")) {
					mimeType = "gif";
				} // end of else if
				// if file name is null, open a blank document
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					} // end of if
				} // end of if

				// for getting server path where configuration files are saved.
				String path = dataPath + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
					// response.setHeader("Content-type",
					// "application/"+mimeType+"");
				} // end of if
				else {
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}

				response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");

				int iChar;
				fsStream = new FileInputStream(path);

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				} // end of while

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				addActionMessage("proof document not found");
			} // end of catch
			catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			finally {
				if (fsStream != null) {
					fsStream.close();
				} // end of if
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} // end of if
			} // end of finally
			// return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			boolean result = model.delRecord(bean);
			model.terminate();

			if (result) {
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("no result"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		input();
		return INPUT;
	}

	/**
	 * Method : reopenFunction. Purpose : This method is used to re-open
	 * application. Once application is re-opened then it will directly going
	 * for IT group mgr. approval
	 * 
	 * @return String
	 */
	public String reopenFunction() {
		try {
			InformationSystemChangeRequestModel model = new InformationSystemChangeRequestModel();
			model.initiate(context, session);
			boolean result = model.getSqlModel().singleExecute( "UPDATE HRMS_D1_INF_CNG_REQ SET HRMS_D1_INF_CNG_REQ.APPL_STATUS = 'Q' WHERE HRMS_D1_INF_CNG_REQ.INF_CNG_CODE = " + bean.getHiddenCode());
			if (result) {
				this.addActionMessage("Application with Tracking No: " + bean.getTrackingNo() + " has been reopened successfully.");
				String applicationId = bean.getHiddenCode();
				String userId = bean.getUserEmpId();
				String reOpenComments = bean.getReOpenComments();
				String statusToUpdate = "Q";
				model.insertIntoActivityLogs(applicationId, userId, reOpenComments, statusToUpdate);
				
				sendReopenMailToGroupAndManager(applicationId, userId);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	/**
	 * Method : sendReopenMailToGroupAndManager. Purpose : This method is used
	 * to send re-open mail to IT-GROUP and Manager
	 * 
	 * @param applicationId :
	 *            Application ID
	 * @param userId :
	 *            Current user employee ID
	 */
	private void sendReopenMailToGroupAndManager(String applicationId,
			String userId) {
		try {
			String firstApproverCode = bean.getFirstApproverCode();
			String path = bean.getDataPath();
			String fileanme = bean.getUploadFileName();
			String optioanlfileanme = bean.getUploadOptionalFileName();

			InformationSystemChangeRequestApprModel model = new InformationSystemChangeRequestApprModel();
			model.initiate(context, session);

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template
					.setEmailTemplate("D1-INFORMATION SYSTEM CHANGE ACTION MAIL TO IT GROUP");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, userId);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, "0");

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applicationId);

			EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
			templateQuery7.setParameter(1, userId);

			template.configMailAlert();

			// BEGINS -- Send mail to IT group
			Object infoSysChngGroupID[][] = model
					.getSqlModel()
					.getSingleResult(
							" SELECT NVL(APP_EMAIL_ID,'') AS INFO_CHNG_GROUP_ID FROM HRMS_D1_APPROVAL_SETTINGS "
									+ " WHERE APP_SETTINGS_TYPE = 'N' AND APP_EMAIL_ID IS NOT NULL ");
			if (infoSysChngGroupID != null && infoSysChngGroupID.length > 0) {
				/*
				 * if(!fileanme.equals("")){ String[]attachment=new String[1];
				 * attachment[0]=path+fileanme;
				 * template.sendApplicationMailToGroups(infoSysChngGroupID,attachment); }
				 * else if(!optioanlfileanme.equals("")){ String[]attachment=new
				 * String[1]; attachment[0]=path+optioanlfileanme;
				 * template.sendApplicationMailToGroups(infoSysChngGroupID,attachment); }
				 * else {
				 * template.sendApplicationMailToGroups(infoSysChngGroupID); }
				 */
				template.sendApplicationMailToGroups(infoSysChngGroupID);
			}
			// ENDS-- Send mail to IT group

			// BEGINS -- Cc. mail to first-Approver of initiator.
			/*
			 * if(!fileanme.equals("")) { String[]attachment=new String[1];
			 * attachment[0]=path+fileanme;
			 * template.sendApplMailWithAttachmentToKeepInf(new
			 * String[]{firstApproverCode}, attachment); } else
			 * if(!optioanlfileanme.equals("")) { String[]attachment=new
			 * String[1]; attachment[0]=path+optioanlfileanme;
			 * template.sendApplMailWithAttachmentToKeepInf(new
			 * String[]{firstApproverCode}, attachment); } else {
			 * template.sendApplMailWithAttachmentToKeepInf(new
			 * String[]{firstApproverCode}, null); }
			 */
			// BEGINS -- Cc. mail to first-Approver of initiator.
			template.sendApplicationMailToKeepInfo(new String[] { firstApproverCode });
			template.clearParameters();
			template.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}