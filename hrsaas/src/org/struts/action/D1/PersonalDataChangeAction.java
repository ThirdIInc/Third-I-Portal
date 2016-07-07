package org.struts.action.D1;

import java.util.ArrayList;
import java.util.List;
import org.paradyne.bean.D1.PersonalDataChange;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.PersonalDataChangeApprovalModel;
import org.paradyne.model.D1.PersonalDataChangeModel;
import org.paradyne.model.common.ReportingModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class PersonalDataChangeAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeAction.class);

	PersonalDataChange bean;

	public String addNew() {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			//reset();
			model.getSysDate(bean);

			 if (bean.isGeneralFlag()) {
				  bean.setEmployeeCode(bean.getEmployeeCode());
				  
				  model.getEmployeeDetails(bean.getUserEmpId(), bean); }// end of
				 else {
				
				model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
				// employee
				// branch,designation etc
				 }
			
		//	setInitiatorName();
			bean.setRevokeFlag(true);
			setApproverName();
			setApproverList(bean.getEmployeeCode()); // setting approver list
			
			int level=1;
			System.out.println("level---"+level);
			
			
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(3);
		return SUCCESS;
	}

	
	
	public void setApproverName() {
		PersonalDataChangeModel model = new PersonalDataChangeModel();
		try {
			
			//bean.setFirstApproverCode("");
			
			model.initiate(context, session);
			Object[][] empFlow = null;
			try {
				empFlow = generateEmpFlow(bean.getEmployeeCode(), "D1", 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {
				String query = " SELECT EMP_FNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC "
						+ " WHERE EMP_ID=" + String.valueOf(empFlow[0][0]);
				Object data[][] = model.getSqlModel().getSingleResult(query);
				
				if (data != null && data.length > 0) {

					bean.setApproverName(String.valueOf(data[0][0]));
					bean
							.setFirstApproverCode(String.valueOf(data[0][1]));
				}
			} else {
				bean.setFirstApproverCode("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}

	
	public String approve_old() {
		String persDataChangeId = bean.getPersDataChangeId();
		String approverComments = bean.getApproverComments();
		String userId = bean.getUserEmpId();
		String employeeCode = bean.getEmployeeCode();
		String status = bean.getApplnStatus();
		int level = Integer.parseInt(bean.getLevel());

		PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
		model.initiate(context, session);

		String nextApprover = userId;

		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", (level + 1));

		if(empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			level += 1;
		}

		status = model.approve(persDataChangeId, approverComments, userId, status, nextApprover, level);

		Object[][] apprCommentListObj = model.getApproverCommentList(persDataChangeId);
		populateApproverComments(apprCommentListObj);
		bean.setApplnStatus(status);
		bean.setReadOnlyDetails(true);
		bean.setApproverComments("");

		addActionMessage("Application approved successfully.");

		if(model.isUserAMagaer(persDataChangeId, userId)) {
			sendApprovalMail(persDataChangeId, userId, employeeCode, status);
		} else {
			sendApprovalMailToApplicantFinal(persDataChangeId, userId, employeeCode, status);
		}

		model.terminate();
		bean.setForApproval(true);
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String back() throws Exception {
		input();
		//getNavigationPanel(1);
		//bean.setEnableAll("Y");
		
		return INPUT;
	}

	/**
	 * For Selecting Employee for pending details
	 * 
	 * @return String
	 */
	public String callApprovedCancellationDetailsView() {
		PersonalDataChangeModel model = new PersonalDataChangeModel();
		model.initiate(context, session);
		String personalDataId = request.getParameter("persDataId");
		bean.setApprovedCancellationFlag(true);
		model.view(bean, request, personalDataId);
		setApproverName();
		bean.setEnableAll("N");
		model.terminate();
		return SUCCESS;

	}

	/**
	 * For Selecting Employee for pending details
	 * 
	 * @return String
	 */
	public String callApprovedView() {
		PersonalDataChangeModel model = new PersonalDataChangeModel();
		model.initiate(context, session);
		String personalDataId = request.getParameter("persDataId");
		bean.setApprovedFlag(true);
		model.view(bean, request, personalDataId);
		setApproverName();
		bean.setEnableAll("N");
		model.terminate();
		return SUCCESS;

	}

	/**
	 * To set the page according to the page numbers
	 */
	public String callPage() throws Exception {
		input();
		return INPUT;
	}

	/**
	 * For Selecting Employee for pending details
	 * 
	 * @return String
	 */
	public String callPendingView() {
		PersonalDataChangeModel model = new PersonalDataChangeModel();
		model.initiate(context, session);
		String personalDataId = request.getParameter("persDataId");
		bean.setPendingFlag(true);
		model.view(bean, request, personalDataId);
		setApproverName();
		
		bean.setEnableAll("Y");
		model.terminate();
		return SUCCESS;

	}

	/**
	 * For Selecting Employee draft details
	 * 
	 * @return String
	 */
	public String callView() {
		PersonalDataChangeModel model = new PersonalDataChangeModel();
		model.initiate(context, session);
		String personalDataId = request.getParameter("persDataId");
		String status = request.getParameter("sendBack");
		getNavigationPanel(3);

		if(status.equals("sendBack") || status.equals("draft")) {
			getNavigationPanel(3);
			bean.setApplnStatus("D");
		} else {
			getNavigationPanel(2);
		}

		model.view(bean, request, personalDataId);
		setApproverName();
		setApproverList(bean.getEmployeeCode()); // setting approver list
		bean.setPendingFlag(true);
		model.terminate();

		if(!status.equals("D")) {
			PersonalDataChangeApprovalModel apprModel = new PersonalDataChangeApprovalModel();
			apprModel.initiate(context, session);
			Object[][] apprCommentListObj = apprModel.getApproverCommentList(personalDataId);
			populateApproverComments(apprCommentListObj);

			if(status.equals("B")) {
				bean.setReadOnlyDetails(false);
			} else {
				bean.setReadOnlyDetails(true);
			}

			apprModel.terminate();
		}

		return SUCCESS;
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			boolean result = model.delRecord(bean);
			model.terminate();

			if(result) {
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("no result"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		input();
		return INPUT;
	}

	/**
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9Approver() {
		String str = "0";

		 if (!bean.getEmployeeCode().equals("")){
				str = bean.getEmployeeCode();
				
				System.out.println("bean.getEmployeeCode()" + bean.getEmployeeCode());
			}

		 
			if(!bean.getFirstApproverCode().equals("")) {
			str +=","+bean.getFirstApproverCode();
			System.out.println("bean.getFirstApproverCode()" + bean.getFirstApproverCode());
		}
		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";
		
		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}

	//	query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"approverToken", "selectapproverName", "approverCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * Method to select the CIty.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9cityaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ORDER BY LOCATION_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("city.code"), getMessage("city")};

		String[] headerWidth = {"10", "45"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"cityId", "cityName"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN
		 * THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF ACTION>_<METHOD
		 * TO CALL>.action
		 */
		String submitToMethod = "PersonalDataChange_getState.action ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * For Selecting Employee
	 * 
	 * @return String
	 */
	public String f9Employee() {
		
		String str = "0";

		if(!bean.getFirstApproverCode().equals("")) {
			str = bean.getFirstApproverCode();
			System.out.println("bean.getFirstApproverCode()" + bean.getFirstApproverCode());
		}
		else if (!bean.getApproverCode().equals("")){
			str = bean.getApproverCode();
			
			System.out.println("bean.getApproverCode()" + bean.getApproverCode());
		}
		
		
		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN("+ str+")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"employeeToken", "employeeName", "employeeCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "true";

		String submitToMethod = "PersonalDataChange_getEmployeeDetailsAction.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * For Selecting relation to employee
	 * 
	 * @return String
	 */
	public String f9relation() {
		String query = "SELECT RELATION_CODE ,RELATION_NAME  " + "	 FROM HRMS_RELATION ";

		query += "	ORDER BY RELATION_CODE";

		String[] headers = {getMessage("relation.id"), getMessage("relation.name")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"relationCode", "relationName"};

		int[] columnIndex = {0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}

	public String getApprovedList() throws Exception {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getApprovedList(bean, request, userId);
			}
			bean.setListType("approved");
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		getNavigationPanel(1);
		return INPUT;
	}

	public PersonalDataChange getBean() {
		return bean;
	}

	public String getCancelledList() throws Exception {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getCancelledList(bean, request, userId);
			}
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return INPUT;
	}

	/**
	 * @method getEmployeeDetailsAction
	 * @purpose to retrieve required details of the selected employee from HRMS_EMP_OFFC table
	 * @return String
	 */
	public String getEmployeeDetailsAction() {
		logger.info("in getEmployeeDetailsAction");

		PersonalDataChangeModel model = new PersonalDataChangeModel();
		model.initiate(context, session); // initialize the LoanApplicationModel class
		bean.setRevokeFlag(true);
		/**
		 * call getEmployeeDetails(loanApp) method of LoanApplicationModel class to retrieve all the details of selected employee
		 */
		model.getEmployeeDetails(bean);
		bean.setApproverName("");
		bean.setApproverCode("");
		bean.setApproverToken("");
		bean.setSelectapproverName("");
		setApproverName();
		setApproverList(bean.getEmployeeCode());
		model.terminate(); // terminate the model class
		//setApproverList(bean.getUserEmpId());// setting
		// approver list
		
		int level=1;
		System.out.println("level---"+level);
		//Second Approver
		String userId = bean.getUserEmpId();
		String employeeCode = bean.getEmployeeCode();
		System.out.println("employeeCode--"+employeeCode);
		String nextApprover = userId;
		Object[][] empFlow = generateEmpFlow(employeeCode , "D1", level+1);

		if(empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			setApproverName();
			bean.setSecondAppFlag(true);
			
		}
		/**
		 * call resetOnEmployeeChange() method to clear all the employee detail fields values on employee change
		 */
		// resetOnEmployeeChange();
		getNavigationPanel(3);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}

	public String getRejectedList() throws Exception {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getRejectedList(bean, request, userId);
			}
			bean.setListType("rejected");
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * Method to set the city,state and country after searching the record.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getState() throws Exception {
		PersonalDataChangeModel model = new PersonalDataChangeModel();
		model.initiate(context, session);
		bean.setRevokeFlag(true);
		model.getStateCountry(bean);
		setApproverName();
		setApproverList(bean.getEmployeeCode());// setting
		
		int level=1;
		System.out.println("level---"+level);
		//Second Approver
		String userId = bean.getUserEmpId();
		String employeeCode = bean.getEmployeeCode();
		System.out.println("employeeCode--"+employeeCode);
		String nextApprover = userId;
		Object[][] empFlow = generateEmpFlow(employeeCode , "D1", level+1);

		if(empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			setApproverName();
			bean.setSecondAppFlag(true);
			
		}
		
		// approver list
		// addDetRecord();
		model.terminate();
		getNavigationPanel(3);
		return SUCCESS;
	}

	public String input() {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getPendingList(bean, request, userId);
			}

			
			 bean.setListType("pending");
			
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//getNavigationPanel(1);
		return INPUT;
	}

	private void populateApproverComments(Object[][] apprCommentListObj) {
		List<Object> approverCommentList = new ArrayList<Object>(apprCommentListObj.length);

		for(int i = 0; i < apprCommentListObj.length; i++) {
			PersonalDataChange persDataChange = new PersonalDataChange();
			persDataChange.setApprName(String.valueOf(apprCommentListObj[i][0]));
			persDataChange.setApprComments(String.valueOf(apprCommentListObj[i][1]));
			persDataChange.setApprDate(String.valueOf(apprCommentListObj[i][2]));
			persDataChange.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
			approverCommentList.add(persDataChange);
		}

		bean.setApproverCommentList(approverCommentList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new PersonalDataChange();
		bean.setMenuCode(1170);
	}

	public String reject() {
		String persDataChangeId = bean.getPersDataChangeId();
		String approverComments = bean.getApproverComments();
		String employeeCode = bean.getEmployeeCode();
		String userId = bean.getUserEmpId();
		String status = bean.getApplnStatus();

		PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
		model.initiate(context, session);
		model.reject(persDataChangeId, approverComments, userId);

		Object[][] apprCommentListObj = model.getApproverCommentList(persDataChangeId);
		populateApproverComments(apprCommentListObj);

		model.terminate();

		addActionMessage("Application rejected successfully.");

		sendRejectSenBackMailToApplicant(persDataChangeId, userId, employeeCode, status);

		bean.setForApproval(true);
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String reset() throws Exception {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			
			bean.setPersonalDataId("");

			bean.setFirstName("");
			bean.setInitialName("");
			bean.setLastName("");
			bean.setMaritalStatus("");
			bean.setCity("");
			bean.setCityName("");
			bean.setCountry("");
			bean.setStateName("");
			bean.setZip("");
			bean.setHomePhoneNumber("");
			bean.setWorkPhoneNumber("");
			bean.setMoveDate("");
			bean.setEmergencyName("");
			bean.setEmergencyContact("");
			bean.setRelationType("");
			bean.setRelationName("");
			bean.setSameAddressType("");
			bean.setWorkPhoneNumberEmergency("");
			bean.setHomePhoneNumberEmergency("");
			//bean.setEmployeeCode("");
			//bean.setHiddenCode("");

			//bean.setEmployeeName("");

			//bean.setEmployeeToken("");
			bean.setApplnStatus("");
			bean.setStreetAddress("");
			model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
			// employee
			setApproverName();
			setApproverList(bean.getEmployeeCode()); // setting approver list
			model.terminate();
			getNavigationPanel(3);
		} catch(Exception e) {
			logger.error("Error in reset" + e);

		}
		return SUCCESS;
	}

	public String save() {
		PersonalDataChangeModel model = new PersonalDataChangeModel();

		String status = request.getParameter("applnStatus");
		System.out.println("status update" + status);
		model.initiate(context, session);
		boolean result;
		if(bean.getHiddenCode().equals("")) {
			result = model.save(bean,status);

			if(result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage("duplicate record found");
			}
		} else {
			result = model.update(bean);
			if(result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				addActionMessage("duplicate record found");
			}// end of else
		}
		// model.getList(bean, request);
		model.terminate();
		try {
			input();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(1);
		bean.setEnableAll("Y");

		return INPUT;
	}

	private void sendApprovalMail(String persDataChangeId, String userId, String employeeCode, String status) {

		try {
			System.out.println("SEND APPROVAL MAIL");
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT

			try {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS FROM  APPROVER TO APPLICANT");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, employeeCode);
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, persDataChangeId);
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, persDataChangeId);
				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
				
			} catch(Exception e) {
				e.printStackTrace();
			}

			try {
				PersonalDataChangeModel model = new PersonalDataChangeModel();
				// MAIL FROM APPROVER TO MANAGER
				model.initiate(context, session);
				/*
				 * String query = "SELECT NVL(APP_SETTINGS_EMP_ID,0) FROM HRMS_D1_APPROVAL_SETTINGS " + " where APP_SETTINGS_TYPE = 'H' ORDER BY
				 * APP_SETTINGS_EMP_ID";
				 */

				String query = " SELECT NVL(APP_SETTINGS_EMP_ID,0) AS MANAGER_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MANAGER_NAME "
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)"
					+ " WHERE APP_SETTINGS_TYPE = 'H'  ORDER BY APP_SETTINGS_EMP_ID";
				Object data[][] = model.getSqlModel().getSingleResult(query);
				EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS FROM  APPROVER TO MANAGER");
				templateApp.getTemplateQueries();
				EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
				templateQueryApp1.setParameter(1, userId);
				EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
				templateQueryApp2.setParameter(1, String.valueOf(data[0][0]));
				EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, persDataChangeId);
				EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, persDataChangeId);
				EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, String.valueOf(data[0][0]));
				String[] empData = null;
				
				if(data != null && data.length > 1) {
					empData = new String[data.length];
					for(int i = 1; i < empData.length; i++) {
						empData[i] = String.valueOf(data[i][0]);
					}
				}
				System.out.println("empData = " + empData.length);
				templateApp.configMailAlert();
				templateApp.sendApplicationMailToKeepInfo(empData);

				templateApp.sendApplicationMail();
				templateApp.clearParameters();
				templateApp.terminate();

				model.terminate();

			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			logger.error(e);
		}

	}

	private void sendApprovalMailToApplicantFinal(String persDataChangeId, String userId, String employeeCode, String status) {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			String query = " SELECT NVL(APP_SETTINGS_EMP_ID,0) AS MANAGER_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MANAGER_NAME "
				+ " FROM HRMS_D1_APPROVAL_SETTINGS "
				+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)"
				+ " WHERE APP_SETTINGS_TYPE = 'H'  ORDER BY APP_SETTINGS_EMP_ID";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS APPROVE/REJECTED/SENDBACK FROM  HR");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, persDataChangeId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, persDataChangeId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, persDataChangeId);
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, String.valueOf(data[0][0]));

			templateApp.configMailAlert();

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public String sendBack() {
		String persDataChangeId = bean.getPersDataChangeId();
		String approverComments = bean.getApproverComments();
		String employeeCode = bean.getEmployeeCode();
		String userId = bean.getUserEmpId();

		PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
		model.initiate(context, session);

		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
		String nextApprover = String.valueOf(empFlow[0][0]);

		String status = model.sendBack(persDataChangeId, approverComments, userId, nextApprover);

		Object[][] apprCommentListObj = model.getApproverCommentList(persDataChangeId);
		populateApproverComments(apprCommentListObj);

		bean.setApplnStatus(status);
		bean.setReadOnlyDetails(true);
		bean.setApproverComments("");
		model.terminate();

		addActionMessage("Application send back successfully.");

		sendRejectSenBackMailToApplicant(persDataChangeId, userId, employeeCode, status);

		getNavigationPanel(2);

		return SUCCESS;
	}
	
	public String sendForApprovalFunction()
	{
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			boolean result = model.sendForApprovalFunction(bean, request);
				model.terminate();
				if (result) {
					String trackCOde = bean.getTrackingNo();
					System.out.println("trackCOde#######" +trackCOde );
					addActionMessage("Application send for approval successfully \n Tracking No: "+trackCOde);
					
					sendMail(); 

				} else {
					addActionMessage("Error occured sending application.");
				}
			
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}

	public String sendForApprovalFunction_old() {
		try {
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);

			save();
			boolean result = model.sendForApprovalFunction(bean);
			//reset();
			
			if(result) {
				addActionMessage("Application send for approver.");

			} else {
				addActionMessage("Error occured sending application.");
			}
			//bean.setPersonalDataId("");

			Object[][] empFlow = generateEmpFlow(bean.getUserEmpId(), "D1", 1);
			if(empFlow != null && empFlow.length > 0) {
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id from hrms_emp_offc " + " where emp_id="
					+ String.valueOf(empFlow[0][1]);
				Object data[][] = model.getSqlModel().getSingleResult(query);

				if(data != null && data.length > 0) {
					bean.setApproverName(String.valueOf(data[0][0]));
					bean.setFirstApproverCode(String.valueOf(data[0][1]));

					String approver = String.valueOf(data[0][1]);
					System.out.println("approver = " + approver);
				}

			} else {

			}
			
			String employeeCode = bean.getUserEmpId();
			
			
		//	sendMail(employeeCode);

			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getPendingList(bean, request, userId);
			}

			getApprovedList();
			getRejectedList();
			getCancelledList();
			
			model.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}
		//input();
		return "input";
	}

	public void sendMail() {

		try {

			String applicationID = bean.getHiddenCode();
			
			// Mail From Applicant to Approver

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS TO APPROVER");

			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, bean.getEmployeeCode());
			

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			
			String approverCode = "0";
			if(!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
		
			templateQuery2.setParameter(1, approverCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationID);

			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

			// Mail From Applicant to Approver End

		} catch(Exception e) {
			logger.error(e);
		}

	}

	private void sendRejectSenBackMailToApplicant(String persDataChangeId, String userId, String employeeCode, String status) {
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-PERSONAL DATA CHANGE APPLICATION DETAILS REJECTED/SENDBACK FROM  APPROVER");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, persDataChangeId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, persDataChangeId);
			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			template.getTemplateQueries();
			template.clearParameters();
			template.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void setApproverList(String empCode) {
		try {
			//bean.setFirstApproverCode("");
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			if(!empCode.equals("")){
			Object[][] empFlow = model1.generateEmpFlow(empCode, "D1");			
			model.setApproverData(bean, empFlow);
			}
			model1.terminate();
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
	}

	

	public void setBean(PersonalDataChange bean) {
		this.bean = bean;
	}

	public String viewDetails() {
		try {
			String persDataChangeId = request.getParameter("persDataId");
			bean.setForApproval(true);
			bean.setPersDataChangeId(persDataChangeId);

			PersonalDataChangeApprovalModel model = new PersonalDataChangeApprovalModel();
			model.initiate(context, session);
			Object[][] persDataObj = model.getPersDataChangeDetails(persDataChangeId);

			Object[][] apprCommentListObj = model.getApproverCommentList(persDataChangeId);
			populateApproverComments(apprCommentListObj);

			model.terminate();

			PersonalDataChangeModel personalDataChangeModel = new PersonalDataChangeModel();
			personalDataChangeModel.initiate(context, session);
			personalDataChangeModel.view(bean, request, persDataChangeId);
			personalDataChangeModel.terminate();
			setApproverName();

			String status = String.valueOf(persDataObj[0][2]);
			boolean readOnlyDetails = false;

			if(status.equals("P") || status.equals("C")) {
				getNavigationPanel(5);
			} else if(status.equals("F")) {
				String userId = bean.getUserEmpId();
				boolean isUserAHRApprover = model.isUserAHRApprover(userId);

				if(isUserAHRApprover) {
					getNavigationPanel(5);
				} else {
					readOnlyDetails = true;
					getNavigationPanel(2);
				}
			} else {
				readOnlyDetails = true;
				getNavigationPanel(2);
			}

			bean.setReadOnlyDetails(readOnlyDetails);

			return SUCCESS;
		} catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			return "";
		}
	}
	//new added start
	public String editApplicationFunction()
	{
		try {
			String requestID = request.getParameter("persDataId");
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			
		
			model.editApplicationFunction(bean, requestID);
			setApproverName();
			setApproverList(bean.getEmployeeCode()); // setting approver list
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.setEnableAll("Y");
		getNavigationPanel(4);
		return SUCCESS;
	}
	public String viewApplicationFunction()
	{		
		
		try {
			String requestID = request.getParameter("persDataId");
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			/*
			 * FOR SUPER USER
			 */
			String applCode=request.getParameter("applCode");
			if(applCode !=null &&applCode.length()>0){
				requestID=applCode;
			}
			
			setApproverList(bean.getEmployeeCode()); // setting approver list
			model.viewApplicationFunction(bean, requestID);
			setApproverName();
			setApproverList(bean.getEmployeeCode()); // setting approver list
			model.terminate();
			
			model.getApproverCommentList(bean, requestID);
			
			
			
		
		bean.setEnableAll("N");
		getNavigationPanel(2);
		
		if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			bean.setEnableAll("N");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String draftFunction() throws Exception {
		try {
			
			PersonalDataChangeModel model = new PersonalDataChangeModel();
			model.initiate(context, session);
			boolean result;
			if (bean.getHiddenCode().equals("")) 
			{
				result = model.draftFunction(bean,request);
				if (result) 
				{
					addActionMessage("Application drafted successfully.");
				} 
				else 
				{
					addActionMessage("Error occured");
					//reset();
				}			
			} 
			else 
			{
				result = model.updateRecords(bean,request);
				if (result) 
				{
					addActionMessage("Application modified successfully.");
				} 
				else 
				{
					addActionMessage("Error occured");
//					/reset();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	/*
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9deptNumber() {
		

		String query = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY  DEPT_ID DESC ";

		//query += getprofileQuery(bean);
	
		//query += "	ORDER BY HRMS_D1_DEPARTMENT.DEPT_ID";

		String[] headers = { getMessage("dept.id"),getMessage("deptName")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {  "deptCode","deptName"};

		int[] columnIndex = { 0,1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	
	/*
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9deptEmpNumber() {
		

		String query = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY  DEPT_ID DESC ";

		//query += getprofileQuery(bean);
	
		//query += "	ORDER BY HRMS_D1_DEPARTMENT.DEPT_ID";

		String[] headers = { getMessage("dept.id"),getMessage("deptName")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {  "empDeptCode","empDeptName"};

		int[] columnIndex = { 0,1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
}