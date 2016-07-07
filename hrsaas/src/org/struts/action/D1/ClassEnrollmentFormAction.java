package org.struts.action.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.bean.D1.ClassEnrollmentForm;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.ClassEnrollmentApproverModel;
import org.paradyne.model.D1.ClassEnrollmentFormModel;
import org.paradyne.model.common.ReportingModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class ClassEnrollmentFormAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ClassEnrollmentFormAction.class);

	ClassEnrollmentForm bean;

	public String addNew() {
		try {
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
			model.initiate(context, session);
			
			model.getSysDate(bean);
			//reset();
			//model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting employee branch,designation etc

			 if (bean.isGeneralFlag()) {
				  bean.setEmployeeCode(bean.getEmployeeCode());
				  
				  model.getEmployeeDetails(bean .getUserEmpId(), bean); }// end of
				 else {
				
				model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
				// employee
				// branch,designation etc
				 }
			
			
		//	bean.setRevokeFlag(true);
			setApproverName();
			setApproverList(bean.getEmployeeCode()); // setting approver list
			
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
				setApproverName(nextApprover);
				bean.setSecondAppFlag(true);
				
			}
			
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(3);
		return SUCCESS;
	}

	public void setApproverName() {
		ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
		try {

			// String claimId = request.getParameter("claimId");
			model.initiate(context, session);
			Object[][] empFlow = null;
			try {
				empFlow = generateEmpFlow(bean.getEmployeeCode(), "D1", 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(empFlow != null && empFlow.length > 0) {
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id from hrms_emp_offc " + " where emp_id="
					+ String.valueOf(empFlow[0][0]);
				Object data[][] = model.getSqlModel().getSingleResult(query);

				if(data != null && data.length > 0) {
					bean.setApproverName(String.valueOf(data[0][0]));
					bean.setFirstApproverCode(String.valueOf(data[0][1]));
				}
			} else {

			}
		} catch(Exception e) {
			// TODO: handle exception
		}
		model.terminate();
	}
	

	public ClassEnrollmentForm getBean() {
		return bean;
	}




	public void setBean(ClassEnrollmentForm bean) {
		this.bean = bean;
	}




	public String back() throws Exception {
		input();
		getNavigationPanel(1);
		bean.setEnableAll("Y");
		return INPUT;
	}

	

	

	/**
	 * To set the page according to the page numbers
	 */
	public String callPage() throws Exception {
		input();
		return INPUT;
	}

	

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}

	

	
	public String input() {
		try {
			System.out.println("sdfsdf");
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getPendingList(bean, request,userId);	
			}			
			bean.setListType("pending");
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new ClassEnrollmentForm();
		bean.setMenuCode(2003);
	}
	
	
	/**
	 * For Selecting Employee
	 * 
	 * @return String
	 */
	public String f9Employee() {
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"employeeToken", "employeeName", "employeeCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "true";

		String submitToMethod = "ClassEnrollmentForm_getEmployeeDetailsAction.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	/**
	 * @method getEmployeeDetailsAction
	 * @purpose to retrieve required details of the selected employee from HRMS_EMP_OFFC table
	 * @return String
	 */
	public String getEmployeeDetailsAction() {
		logger.info("in getEmployeeDetailsAction");

		ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
		model.initiate(context, session); // initialize the LoanApplicationModel class
		
		/**
		 * call getEmployeeDetails(loanApp) method of LoanApplicationModel class to retrieve all the details of selected employee
		 */
		model.getEmployeeDetails(bean);
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
			setApproverName(nextApprover);
			bean.setSecondAppFlag(true);
			
		}
		/**
		 * call resetOnEmployeeChange() method to clear all the employee detail fields values on employee change
		 */
		// resetOnEmployeeChange();
		getNavigationPanel(3);
		return "success";
	}
	
	//Second Default Manager from reporting structure
	public void setApproverName(String nextApprover) {
		ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
		try {
			System.out.println("in setApp method-----");
			model.initiate(context, session);
			
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id, EMP_TOKEN from hrms_emp_offc "
						+ " where emp_id=" + nextApprover;
				Object data[][] = model.getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {
					
					bean.setSecondApproverName(String.valueOf(data[0][0]));
					bean.setSecondApproverId(String.valueOf(data[0][1]));
					bean.setSecondApproverToken(String.valueOf(data[0][2]));
				}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
	}
	
	public void setApproverList(String empCode) {
		try {
			System.out.println("empCode =============" + empCode);
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			Object[][] empFlow = model1.generateEmpFlow(empCode, "D1");
			model.setApproverData(bean, empFlow);
			model1.terminate();
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in setApproverList------" + e);
			e.printStackTrace();
		}
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
	 * For Selecting Course Name
	 * 
	 * @return String
	 */
	public String f9CourseName() {
		String query = "SELECT 	CLASS_REQUEST_ID , CLASS_NAME  FROM HRMS_D1_CLASS_REQUEST  where STATUS = 'A'";

	//	query += getprofileQuery(bean);
		
		query += "	ORDER BY HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID";

		String[] headers = {getMessage("course.id"), getMessage("course.name")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"courseId","courseName" };

		int[] columnIndex = {0, 1};

		String submitFlag = "true";

		String submitToMethod = "ClassEnrollmentForm_getCourseDetailsAction.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * @method getEmployeeDetailsAction
	 * @purpose to retrieve required details of the selected employee from HRMS_EMP_OFFC table
	 * @return String
	 */
	public String getCourseDetailsAction() {
		logger.info("in getCourseDetailsAction");

		ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
		model.initiate(context, session); // initialize the LoanApplicationModel class
		
		/**
		 * call getEmployeeDetails(loanApp) method of LoanApplicationModel class to retrieve all the details of selected employee
		 */
		model.getCourseDetails(bean);
		setApproverName();
		setApproverList(bean.getEmployeeCode()); // setting approver list
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
			setApproverName(nextApprover);
			bean.setSecondAppFlag(true);
			
		}
		/**
		 * call resetOnEmployeeChange() method to clear all the employee detail fields values on employee change
		 */
		// resetOnEmployeeChange();
		getNavigationPanel(3);
		return "success";
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
		String submitToMethod = "ClassEnrollmentForm_getState.action ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * Method to set the city,state and country after searching the record.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getState() throws Exception {
		ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
		model.initiate(context, session);
	//	bean.setRevokeFlag(true);
		model.getStateCountry(bean);
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
			setApproverName(nextApprover);
			bean.setSecondAppFlag(true);
			
		}
		
		// approver list
		// addDetRecord();
		model.terminate();
		getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String draftFunction() throws Exception {
		try {
			
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
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
	
	public String sendForApprovalFunction()
	{
		try {
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
			model.initiate(context, session);
		//	model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
			String checkApproverQuery = "SELECT APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS where APP_SETTINGS_TYPE = 'T'";
			Object chk[][] = model.getSqlModel().getSingleResult(
					checkApproverQuery);
			
			if (chk != null && chk.length > 0) {
			
			boolean result = model.sendForApprovalFunction(bean, request);
			model.terminate();
			if (result) {
				String trackCOde = bean.getTrackingNo();
				addActionMessage("Application send for approval successfully \n Tracking No: "+trackCOde);
				getNavigationPanel(2);
				bean.setEnableAll("N");
				sendMail(); 

			} else {
				addActionMessage("Error occured sending application.");
			}
			
			} else {
				
				addActionMessage("Training Authorities Approver Not Defined for this application\n"
						+ "Please Contact your HR Department. ");
				getNavigationPanel(3);
				bean.setEnableAll("Y");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//input();
		/*String typeOfList = bean.getListType();		
		bean.setListTypeDetailPage(typeOfList);
		
		if(typeOfList.equals("pending"))
		{
			//bean.setApproverCommentsFlag(true);
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}else
			if(typeOfList.equals("draft"))
			{
			  // bean.setApproverCommentsFlag(false);
				getNavigationPanel(3);
				bean.setEnableAll("Y");
			}
			else
				{
					
					getNavigationPanel(2);
					bean.setEnableAll("N");
				}
		*/
		
	//	getNavigationPanel(3);
		return SUCCESS;
	}
	
	private void sendMail() {
		// Mail From Applicant to Approver
		
		

		/*EmailTemplateBody template = new EmailTemplateBody();
		template.initiate(context, session);

		template.setEmailTemplate("D1-CLASS ENROLLMENT APPLICATION DETAILS TO APPROVER");

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
		template.terminate();*/
		
		try {
			
			String applicationID = bean.getHiddenCode();
			System.out.println("applicationID = " + applicationID);
			String userId = bean.getEmployeeCode();
			System.out.println("userId = " + userId);
			ClassEnrollmentApproverModel model = new ClassEnrollmentApproverModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			/*
			 * String query = "SELECT NVL(APP_SETTINGS_EMP_ID,0) FROM HRMS_D1_APPROVAL_SETTINGS " + " where APP_SETTINGS_TYPE = 'H' ORDER BY
			 * APP_SETTINGS_EMP_ID";
			 */

			
		
					
					EmailTemplateBody templateApp = new EmailTemplateBody();
					templateApp.initiate(context, session);
					templateApp.setEmailTemplate("D1-CLASS ENROLLMENT APPLICATION DETAILS TO APPROVER");
					templateApp.getTemplateQueries();
					EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, userId);
					EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
					templateQueryApp2.setParameter(1, "0");
					EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, applicationID);
					EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, applicationID);
					EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, userId);
					
					/*EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
					templateQueryApp6.setParameter(1, String.valueOf(data[0][0]));*/
					EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
					templateQueryApp6.setParameter(1, applicationID);
					
					
					String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID  "
						+ " FROM HRMS_D1_APPROVAL_SETTINGS "
						+ " WHERE APP_SETTINGS_TYPE = 'T' AND APP_EMAIL_ID IS NOT NULL ";
					Object data[][] = model.getSqlModel().getSingleResult(query);
					
					/*String[] empData = new String[data.length];
					
					if(data != null && data.length > 1) {
						empData = new String[data.length];
						for(int i = 1; i < empData.length; i++) {
							empData[i] = String.valueOf(data[i][0]);
						}
					}*/
					//System.out.println("empData = " + empData.length);
					templateApp.configMailAlert();
					//templateApp.sendApplicationMailToGroups(data);
					if(data != null && data.length > 0) {
						
						templateApp.sendApplicationMailToGroups(data);
					}

				//	templateApp.sendApplicationMail();
					templateApp.clearParameters();
					templateApp.terminate();
				
			
			
			model.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}

		// Mail From Applicant to Approver End
		
	}
	
	public String editApplicationFunction()
	{
		try {
			String requestID = request.getParameter("enrollID");
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
			model.initiate(context, session);
			model.editApplicationFunction(bean, requestID);
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return SUCCESS;
	}
	
	public String viewApplicationFunction()
	{		
		
		try {
			String requestID = request.getParameter("inProcessVoucherID");
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
			model.initiate(context, session);
			
			/*
			 * FOR SUPER USER
			 */
			String applCode=request.getParameter("applCode");
			if(applCode !=null &&applCode.length()>0){
				requestID=applCode;
			}
			
			model.viewApplicationFunction(bean, requestID);
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
	
	public String delete()
	{
		try {
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
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
	public String getApprovedList() throws Exception {
		try {
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
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
			ClassEnrollmentFormModel model = new ClassEnrollmentFormModel();
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
			

			bean.setCourseName("");
			bean.setCourseLocationName("");
			bean.setStartDate("");
			bean.setEndDate("");
			bean.setStreetAddress("");
			bean.setCityName("");
			bean.setStateName("");
			bean.setStateZip("");
			bean.setProvidence("");
			bean.setCanadaZipcode("");
			bean.setPhoneNumber("");
			bean.setFaxNumber("");
			bean.setEmailAddress("");
			
			//bean.setEmployeeCode("");
			//bean.setHiddenCode("");

			//bean.setEmployeeName("");

			//bean.setEmployeeToken("");
			
			
			setApproverName();
			setApproverList(bean.getEmployeeCode()); // setting approver list
			
			getNavigationPanel(3);
		} catch(Exception e) {
			logger.error("Error in reset" + e);

		}
		return SUCCESS;
	}
public String f9deptNumber() {
		

	String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT  ";

    if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE DEPT_DIV_CODE IN ("
					+ bean.getUserProfileDivision() + ")";
		}
    query += " ORDER BY DEPT_ID DESC";

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
}