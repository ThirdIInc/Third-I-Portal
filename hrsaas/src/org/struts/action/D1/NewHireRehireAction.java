package org.struts.action.D1;

import java.io.PrintWriter;

import org.paradyne.bean.D1.NewHireRehire;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.NewHireRehireModel;
import org.paradyne.model.D1.PersonalRequisitionApproverModel;
import org.struts.lib.ParaActionSupport;


/**
 * @author aa1385
 *
 */
public class NewHireRehireAction extends ParaActionSupport {
	 /**
     * Used for f9 windows.
     */
	private static final String F9_PAGE = "f9page";
    /**
     * Used for f9 windows submit Flag true.
     */
	private static final String SUBMIT_FLAG_TRUE = "true";
    /**
     * Used for f9 windows submit Flag false.
     */
	private static final String SUBMIT_FLAG_FALSE = "false";
	/**
     * Log4j logger.
     */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewHireRehireAction.class);
	/**
     * Used for setAnableAll Flag .
     */
	private static final String SET_ANABLE_ALL = "false";
	/** stringYes. *	 */
	private final String stringYes = "Y";
	
	/** stringNo. *	 */
	private final String stringNo = "N";
	/**
	 * object of NewHireRehire.
	 */
	private NewHireRehire bean;
	
	
	/**
	 * Method : prepare_local.
	 * Purpose : For setting menu code
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		this.bean = new NewHireRehire();
		this.bean.setMenuCode(2018);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	/**
     * Method to get bean.
     * 
     * @return bean
     */
	public Object getModel() {
		return this.bean;
	}
	
	@Override
	public String input() throws Exception {
		final NewHireRehireModel model = new NewHireRehireModel();
		model.initiate(context, session);
		final String userId = this.bean.getUserEmpId();
		final boolean isCurrentUser = model.isCurrentUser(userId);
		if (isCurrentUser) {
			model.getPendingList(this.bean , request , userId);	
		}			
		this.bean.setListType("pending");
		model.terminate();
		return INPUT;
	}
	
	/**
	 * Function to get Salary Plan .
	 */
	public void addSalaryPlan() {
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			// add salary plan, set message whether salary plan is added successfully or not
			final String message = model.addSalaryPlan(request);
			// set message in a response for salary plan if available
			response.setContentType("text/html");
			final PrintWriter printWriter = response.getWriter();
			printWriter.print(message);
			printWriter.flush();
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in addSalary" + e);
		}		
	}
	
	/**
	 * Method to select the CIty.
	 * 
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String f9cityaction() throws Exception {
		
		final String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ORDER BY LOCATION_CODE ";
		final String[] headers = {this.getMessage("city.code") , this.getMessage("city")};
		final String[] headerWidth = {"10" , "45"};
		final String[] fieldNames = {"cityId", "cityName"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_TRUE;
		final String submitToMethod = "NewHireRehire_getState.action ";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * Method to select the Shift of an employee.
	 * 
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String f9shiftaction() throws Exception {
		final String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT " +
				 " ORDER BY  SHIFT_ID ";
		final String[] headers = {this.getMessage("shift.code") , this.getMessage("shift")};
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"shiftCode" , "shiftType"};
 		final int[] columnIndex = {0, 1};
 		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
 		final String submitToMethod = "";
 		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * Method to select the Grade of an employee.
	 * 
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String f9gradeaction() throws Exception {
		final String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  " +
				 "WHERE CADRE_IS_ACTIVE = 'Y' ORDER BY CADRE_ID ";
		final String[] headers = {this.getMessage("grade.code"), this.getMessage("grade")};
		final String[] headerWidth = {"20", "80"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		final String[] fieldNames = {"cadreCode", "cadreName"};
	//	offDetail.setMasterMenuCode(19);
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * Method to select the Caste of an Employee.
	 * 
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String f9castaction() throws Exception {
		final String query = "SELECT ETHIC_CODE, ETHINICITY FROM HRMS_D1_ETHIC ORDER BY ETHIC_CODE";
		final String[] headers = {this.getMessage("caste.code"), this.getMessage("caste") };
		final String[] headerWidth = {"20", "20"};
		final String[] fieldNames = {"castCode", "castName"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * To select qualification.
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String f9qualaction() throws Exception {
		final String query = " SELECT QUA_ID,QUA_NAME FROM HRMS_QUA ORDER BY QUA_ID ";
		final String[] headers = {this.getMessage("qualification.code") , this.getMessage("qualname")};
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"qualCode" , "qualifyName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * To select refferal Source.
	 * @return f9page
	 * @throws Exception  -IOException for select data .
	 */
	public String f9ReferralSource() throws Exception {
		final String query = " SELECT REFERRAL_ID , REFERRAL_SOURCE_NAME FROM HRMS_D1_REFERRAL ORDER BY REFERRAL_ID ";		
		final String[] headers = {"Referral ID" , "Referral Name"};
		final String[] headerWidth = {"20", "20"};
		final String[] fieldNames = {"mediumCode" , "mediumName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * Method to set the city,state and country after searching the record.
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String getState() throws Exception {
		final NewHireRehireModel model = new NewHireRehireModel();
		model.initiate(context, session);
		model.getStateCountry(this.bean);
	//	setApproverName();
	//	setApproverList(bean.getEmployeeCode());// setting
		model.terminate();
		this.getNavigationPanel(3);
		return SUCCESS;
	}
	
	/**
	 * For Selecting Approver.
	 * 
	 * @return String
	 */
	public String f9Approver() {
		String str = "0";
		if (!"".equals(this.bean.getApproverCode())) {
			str = this.bean.getApproverCode();
		}
		final String query = "SELECT EMP_TOKEN AS MANAGER_TOKEN  , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MANAGER_NAME ,NVL(APP_SETTINGS_EMP_ID,0) AS MANAGER_ID" +
					" FROM HRMS_D1_APPROVAL_SETTINGS " +
					" INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)" +
					" WHERE APP_SETTINGS_TYPE = 'H'  ORDER BY APP_SETTINGS_EMP_ID";
		final String[] headers = {this.getMessage("employee.id") , this.getMessage("employee")};
		final String[] headerWidth = {"20" , "80"};
		final String[] fieldNames = {"approverToken" , "approverName" , "approverCode"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/** Function To set Approver Name.
	 */
	public void setApproverName() {
		final NewHireRehireModel model = new NewHireRehireModel();
		try {
			model.initiate(context, session);
			Object[][] empFlow = null;
			try {
				empFlow = this.generateEmpFlow(this.bean.getEmployeeCode(), "D1", 1);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {
				final String query = " SELECT EMP_FNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC " +
						 " WHERE EMP_ID=" + String.valueOf(empFlow[0][0]);
				final Object [][] data = model.getSqlModel().getSingleResult(query);
				
				if (data != null && data.length > 0) {
					this.bean.setApproverName(String.valueOf(data[0][0]));
					this.bean.setApproverCode(String.valueOf(data[0][1]));
				}
			} 
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in setApproverName" + e);
		}
		model.terminate();
	}
	
	/**
	 * To Select city name, country for perticuler customer.
	 * @return f9Page
	 * @throws Exception -IOException for select data .
	 */
	public String f9custCityaction() throws Exception {
		final String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ORDER BY LOCATION_CODE ";
		final String[] headers = {this.getMessage("city.code") , this.getMessage("city")};
		final String[] headerWidth = {"10" , "45"};
		final String[] fieldNames = {"custCityId", "custCityName"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_TRUE;
		final String submitToMethod = "NewHireRehire_getCustState.action ";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * To set Customer Details .i.e. state , country etc.
	 * @return SUCCESS
	 * @throws Exception -IOException for select data .
	 */
	public String getCustState() throws Exception {
		final NewHireRehireModel model = new NewHireRehireModel();
		model.initiate(context, session);
		model.getCustState(this.bean);
		model.terminate();
		this.getNavigationPanel(3);
		return SUCCESS;
	}
	/**
	 * Purpose : To Select city name, country for perticuler customer Shift .
	 * @return f9Page
	 * @throws Exception -IOException for select data .
	 */
	public String f9custShiftaction() throws Exception {
		final String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT " + 
				" ORDER BY  SHIFT_ID ";
		final String[] headers = {this.getMessage("shift.code") , this.getMessage("shift")};
		final String[] headerWidth = {"20" , "80"};
		final String[] fieldNames = {"custShiftCode", "custShiftType"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return NewHireRehireAction.F9_PAGE;
	}
	/**
	 * Purpose : To Select city name, country for perticuler Decision One .
	 * @return f9Page
	 * @throws Exception -IOException for select data .
	 */
	public String f9decisionCityaction() throws Exception {
		final String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ORDER BY LOCATION_CODE ";
		final String[] headers = {this.getMessage("city.code") , this.getMessage("city")};
		final String[] headerWidth = {"10", "45"};
		final String[] fieldNames = {"decisionCityId" , "decisionCityName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_TRUE;
		final String submitToMethod = "NewHireRehire_getDecisionState.action ";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	/**
	 * To set Decison One Details .i.e. state , country etc.
	 * @return SUCCESS
	 * @throws Exception -IOException for select data .
	 */
	public String getDecisionState() throws Exception {
		final NewHireRehireModel model = new NewHireRehireModel();
		model.initiate(context, session);
		model.getDecisionState(this.bean);
		final int level = 1;
		//Second Approver
		final String userId = this.bean.getUserEmpId();
		final String employeeCode = this.bean.getEmployeeCode();
		String nextApprover = userId;
		final Object[][] empFlow = this.generateEmpFlow(employeeCode , "D1" , level + 1);
		if (empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			this.setApproverName();
			this.bean.setSecondAppFlag(true);
		}
		model.terminate();
		this.getNavigationPanel(3);
		return SUCCESS;
	}
	
	/**
	 * Purpose : To Select city name, country for perticuler Decision Shift .
	 * @return f9Page
	 * @throws Exception -IOException for select data .
	 */
	public String f9decisionShiftaction() throws Exception {
		final String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT " +
				" ORDER BY  SHIFT_ID ";
		final String[] headers = {this.getMessage("shift.code") , this.getMessage("shift") };
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"decisionShiftCode" , "decisionShiftType"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * For Selecting Department.
	 * @return String
	 */
	public String f9deptNumber() {
		//final String query = "SELECT DEPT_ID,DEPT_NAME||' - '||DEPT_ABBR FROM HRMS_DEPT ORDER BY  DEPT_ID DESC  ";
		String query = "SELECT DEPT_ID, DEPT_NAME||' - '||DEPT_ABBR FROM HRMS_DEPT  ";

        if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DEPT_DIV_CODE IN ("
						+ bean.getUserProfileDivision() + ")";
			}
        query += " ORDER BY DEPT_ID DESC";
		//query += getprofileQuery(bean);
		//	query += "	ORDER BY HRMS_D1_DEPARTMENT.DEPT_ID";
		final String[] headers = {this.getMessage("dept.id") , this.getMessage("deptName")};
		final String[] headerWidth = {"20" , "80"};
		final String[] fieldNames = {"deptCode" , "deptName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	/** * Purpose : To Select city name, country for perticuler Office City action.
	 * @return f9Page
	 * @throws Exception -IOException for select data .
	 */
	public String f9offCityaction() throws Exception {
		final String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 2 ORDER BY LOCATION_CODE ";
		final String[] headers = {this.getMessage("city.code") , this.getMessage("city")};
		final String[] headerWidth = {"10" , "45"};
		final String[] fieldNames = {"officeCityId" , "officeCityName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_TRUE;
		final String submitToMethod = "NewHireRehire_getOfficeState.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	/**
	 * To set Office State Details .i.e. state , country etc.
	 * @return SUCCESS
	 * @throws Exception -IOException for select data .
	 */
	public String getOfficeState() throws Exception {
		final NewHireRehireModel model = new NewHireRehireModel();
		model.initiate(context, session);
		model.getOfficeState(this.bean);
		final int level = 1;
		//Second Approver
		final String userId = this.bean.getUserEmpId();
		final String employeeCode = this.bean.getEmployeeCode();
		String nextApprover = userId;
		final Object[][] empFlow = this.generateEmpFlow(employeeCode , "D1", level + 1);
		if (empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			this.setApproverName();
			this.bean.setSecondAppFlag(true);
		}
		// approver list
		// addDetRecord();
		model.terminate();
		this.getNavigationPanel(3);
		return SUCCESS;
	}
/*	*//**
	 * For Selecting Approver
	 * 
	 * @return String
	 *//*
	public String f9custZip() {
		

		String query = "SELECT  ZIP_CODE , ZIP_ID, SALARY_PLAN FROM HRMS_D1_HIRE_ZIP ";

		query += getprofileQuery(bean);
	
		query += "	ORDER BY HRMS_D1_HIRE_ZIP.ZIP_ID";

		String[] headers = { getMessage("deptName"),getMessage("dept.id")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {  "custZipId" , "custZipCode","salaryPlan"};

		int[] columnIndex = { 1,0,2};

		String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;

		String submitToMethod = "NewHireRehire_getSalaryPlan.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return NewHireRehireAction.F9_PAGE;

	}
	public String getSalaryPlan() throws Exception {
		NewHireRehireModel model = new NewHireRehireModel();
		model.initiate(context, session);
		
		model.getSalaryPlan(bean);
	
		model.terminate();
		getNavigationPanel(3);
		return SUCCESS;
	}*/
	/**
	 * To set Rank Details.
	 * @return f9Page
	 * @throws Exception -IOException for select data .
	 */
	public String f9rankaction() throws Exception {
		final String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK  ORDER BY RANK_ID ";
		final String[] headers = {"Executive Code" , "Executive Name"};
		final String[] headerWidth = {"10" , "50"};
		final String[] fieldNames = {"executiveCode" , "executiveName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
	/**
	 * To get approved list.
	 * @return INPUT
	 * * @throws Exception - this.input() throws Exception
	 */
	public String getApprovedList() throws Exception {		
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getApprovedList(this.bean, request, userId);
			}	
			this.bean.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in getApprovedList" + e);
		}
		this.getNavigationPanel(6);
		return INPUT;
	}
	
	/**
	 * To get cancelled list.
	 * @return INPUT
	 * @throws Exception - this.input() throws Exception
	 */
	public String getCancelledList() throws Exception {		
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getCancelledList(this.bean, request, userId);
			}	
			this.bean.setListType("cancelled");
			model.terminate();
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in getCancelledList" + e);
		}
		return INPUT;
	}
	/**
	 * To get rejected list.
	  * @return this.input()
	 * @throws Exception - this.input() throws Exception
	 */
	public String getRejectedList() throws Exception {		
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isCurrentUser = model.isCurrentUser(userId);
			if (isCurrentUser) {
				model.getRejectedList(this.bean, request, userId);
			}
			this.bean.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in getRejectedList" + e);
		}
		return INPUT;
	}
	/**
     * Method to add new application.
     * 
     * @return SUCCESS
     */
	public String addNew() { 		
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			model.getSystemDate(this.bean);
			model.getEmployeeDetails(this.bean.getUserEmpId() , this.bean); // getting
			// employee
			model.terminate();
			//reset();
			String salaryPlan = "GBR";
			if (!"".equals(this.bean.getCustZipId())) {
				salaryPlan = this.bean.getSalaryPlan();
			} else {
				this.bean.setSalaryPlan(salaryPlan);
			}
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in Adding Application" + e);
		}
		this.getNavigationPanel(3);
		return SUCCESS;
	}
	
	/**
     * Method to Draft new application.
     * 
     * @return SUCCESS
     * * @throws Exception - this.input() throws Exception
     */
	public String draftFunction() throws Exception {
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			final boolean result;
			if ("".equals(this.bean.getRequestID())) {
				result = model.draftFunction(this.bean);
				if (result) {
					this.addActionMessage("Application drafted successfully.");
				} else {
					this.addActionMessage("Error occured");
					this.reset();
				}			
			} else {
				result = model.updateRecords(this.bean);
				if (result) {
					this.addActionMessage("Application modified successfully.");
				} else {
					this.addActionMessage("Error occured");
					this.reset();
				}
			}
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in Draft List" + e);
		}
		this.input();
		return INPUT;
	}
	/**
     * Method to go list page .
     * 
     * @return INPUT
     * * @throws Exception - this.input() throws Exception
     */
	public String back() throws Exception {
		this.bean.setRequestID("");
		final NewHireRehireModel model = new NewHireRehireModel();
		model.initiate(context, session);
		 final String listType = model.checkNull(this.bean.getListType());

         if ("".equals(listType)) {
        	 input();
         } else if (listType.equals("approved")){
        	 getApprovedList();
         }  else if (listType.equals("rejected")){
        	 getRejectedList();
         } else {
        	input();
         }
		model.terminate();
		return INPUT;
	}
	/**
     * Method to call list page .
     * 
     * @return INPUT
     * * @throws Exception - this.input() throws Exception
     */
	public String callPage() throws Exception {
		this.input();
		return INPUT;
	}
	/**
     * Method to reset form fields.
     * 
     * @return SUCCESS
     * * @throws Exception - SUCCESS() throws Exception
     */
	public String reset() throws Exception {
		final NewHireRehireModel model = new NewHireRehireModel();
		model.initiate(context, session);
		
		this.bean.setRequestID("");
		this.bean.setEmpFirstName("");
		this.bean.setEmpMiddleName("");
		this.bean.setEmpLastName("");
		this.bean.setSocialSecurityNumber("");
		this.bean.setSocialInsuranceNumber("");
		this.bean.setEmpHomeAddress("");
		this.bean.setCityId("");
		this.bean.setStateName("");
		this.bean.setCountry("");
		this.bean.setZip("");
		this.bean.setHomePhoneNumber("");
		this.bean.setReqNumber("");
		this.bean.setSex("");
		this.bean.setMaritalStatus("");
		this.bean.setQualCode("");
		this.bean.setQualifyName("");
		this.bean.setBirthDate("");
		this.bean.setMediumCode("");
		this.bean.setMediumName("");
		this.bean.setCastCode("");
		this.bean.setCastName("");
		this.bean.setHireDate("");
		this.bean.setActionReason("");
		this.bean.setJobCode("");
		this.bean.setJobTitle("");
		this.bean.setAcquisitionDate("");
		this.bean.setAcquiredCompany("");
		this.bean.setPreacqusitionDate("");
		this.bean.setUserProfile("");
		this.bean.setShiftType("");
		this.bean.setRegTemp("");
		this.bean.setFlsaStatus("");
		this.bean.setCustomerName("");
		this.bean.setPhysicalAddress("");
		this.bean.setCustCityId("");
		this.bean.setCustCityName("");
		this.bean.setCustStateName("");
		this.bean.setCustZipId("");
		this.bean.setCustZipCode("");
		this.bean.setCustShiftCode("");
		this.bean.setCustShiftType("");
		this.bean.setCustRegTemp("");
		this.bean.setCustflsaStatus("");
		this.bean.setDecisionPhysicalAddress("");
		this.bean.setDecisionCityId("");
		this.bean.setDecisionCityName("");
		this.bean.setDecisionStateName("");
		this.bean.setDecisionzip("");
		this.bean.setDecisionShiftCode("");
		this.bean.setDecisionShiftType("");
		this.bean.setRegTempDecision("");
		this.bean.setDecisionflsaStatus("");
		this.bean.setSalaryPlan("");
		this.bean.setPayGroup("");
		this.bean.setCadreCode("");
		this.bean.setCadreName("");
		this.bean.setWeeklyHours("");
		this.bean.setBiweeklySalary("");
		this.bean.setAnnualSalary("");
		this.bean.setDeptCode("");
		this.bean.setExecutiveCode("");
		this.bean.setExecutiveName("");
		this.bean.setOfficeCityId("");
		this.bean.setOfficeCityName("");
		this.bean.setOfficeStateName("");
		this.bean.setApproverCode("");
		this.bean.setApproverToken("");
		this.bean.setApproverName("");
		model.getEmployeeDetails(this.bean.getUserEmpId(), this.bean); // getting
		// employee
		model.terminate();
		this.getNavigationPanel(3);
		return SUCCESS;
	}
	
	/**
     * Method to delete the application.
     * 
     * @return this.input()
     */
	public String delete() {
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			final boolean result = model.delRecord(this.bean);
			model.terminate();
			if (result) {
				this.addActionMessage(this.getMessage("delete"));

			} else {
				this.addActionMessage(this.getMessage("no result"));
			}
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in Delete List" + e);
		}
		//this.input();
		return INPUT;
	}
	/**
     * Method to edit the application.
     * 
     * @return SUCCESS
     */
	public String editApplicationFunction() {
		try {
			final String requestID = request.getParameter("hireRehireId");
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			model.editApplicationFunction(this.bean, requestID);
			model.terminate();
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in Edit Application " + e);
		}
		this.getNavigationPanel(4);
		return SUCCESS;
	}
	/**
     * Method to Send application for approval.
     * 
     * @return SUCCESS
     */
	public String sendForApprovalFunction() {	
		final String applicationId = this.bean.getRequestID();
		final String employeeCode = this.bean.getEmployeeID();
		final String userID = this.bean.getUserEmpId();
		final String approverID = this.bean.getUserEmpId();		
		final String status = this.bean.getStatus();
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			model.getEmployeeDetails(this.bean.getUserEmpId(), this.bean); // getting
			// employee
			final String checkApproverQuery = "SELECT APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS where APP_SETTINGS_TYPE = 'H'";
			final Object [][] chk = model.getSqlModel().getSingleResult(
					checkApproverQuery);
			if (chk != null && chk.length > 0) {
				final boolean result = model.sendForApprovalFunction(this.bean);
				model.terminate();
				if (result) {
					final String trackCOde = this.bean.getTrackingNo();
					this.addActionMessage("Application sent for approval successfully \n Tracking No: " + trackCOde);
					this.getNavigationPanel(2);
					//this.bean.setEnableAll("N");
					this.bean.setEnableAll(this.stringNo);
					this.sendApplicantToApprovalMail();
				} else {
					this.addActionMessage("Error occured sending application.");
				}
			} else {
				this.addActionMessage("HR Authorities Approver Not Defined for this application\n" + 
						"Please Contact your HR Department. ");
				this.getNavigationPanel(3);
				this.bean.setEnableAll(this.stringYes);
			}
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in sendForApprovalFunction" + e);
		}
		//input();
		return SUCCESS;
	}
	/**
     * Method to Send application for approval mail.
     * */
	private void sendApplicantToApprovalMail() {
		try {
			final String applicationId = this.bean.getRequestID();
			final String userID = this.bean.getUserEmpId();
			final NewHireRehireModel model = new NewHireRehireModel();
			// MAIL FROM Applicant To Approver
			model.initiate(context, session);
			final String query = " SELECT NVL(APP_EMAIL_ID,0) AS MANAGER_ID  " +
				" FROM HRMS_D1_APPROVAL_SETTINGS " + 
				" WHERE APP_SETTINGS_TYPE = 'H' AND APP_EMAIL_ID IS NOT NULL";
			final Object [][] data = model.getSqlModel().getSingleResult(query);
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-HIRE/REHIRE DETAILS TO MANAGER");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userID);
			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, "0");
			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, userID);
			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6); // TO EMAIL
			templateQueryApp6.setParameter(1, "0");
			/*String[] empData = null;
			if(data != null && data.length > 1) {
				System.out.println("data.length : "+data.length);
				empData = new String[data.length];
				for(int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
					System.out.println("empData[i] = " + empData[i]);
				}
			}*/
			templateApp.configMailAlert();
			//templateApp.sendApplicationMailToKeepInfo(empData);
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}
			//templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in sendApplicantToApprovalMail" + e);
		}
	}
	
	/**
     * Method to view the application.
     * 
     * @return SUCCESS
     */
	public String viewApplicationFunction()	{		
		this.bean.setListTypeDetailPage(this.bean.getListType());
		try {
			String requestID = request.getParameter("hireRehireId");
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			/*
			 * FOR SUPER USER
			 */
			final String applCode = request.getParameter("applCode");
			if (applCode != null && applCode.length() > 0) {
				requestID = applCode;
			}
			model.viewApplicationFunction(this.bean, requestID);
			model.terminate();
		
			this.bean.setEnableAll(this.stringNo);
			this.getNavigationPanel(2);
		
			if (applCode != null && applCode.length() > 0) {
				this.getNavigationPanel(0);
				this.bean.setEnableAll(this.stringNo);
				this.bean.setEmpOfficeDtlFlag(true);
			}
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in View Application -" + e);
		}
		return SUCCESS;
	}
	
	/**
     * Method to view the approved application.
     * 
     * @return SUCCESS
     */
	public String viewApprovedApplication() {		
		try {
			final String requestID = request.getParameter("hireRehireId");
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			String query = "SELECT ACTION_REASON, HIRE_EMP_ID from HRMS_D1_NEW_HIRE_REHIRE where HIRE_REHIRE_ID ="+requestID;
			Object [][] data = model.getSqlModel().getSingleResult(query); 
			model.viewApplicationFunction(this.bean, requestID);
			this.bean.setEmpOfficeDtlFlag(true);
		
			if(String.valueOf(data[0][0]).equals("R")){
				model.viewOfficialDetails(this.bean, String.valueOf(data[0][1]));
			}
			
			model.terminate();
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in View Approved Application" + e);
		}
		this.bean.setEnableAll(this.stringNo);
		this.getNavigationPanel(6);
		return SUCCESS;
	}
	/**
     * Method to cancel application.
     * 
     * @return INPUT
     */
	public String cancel() {
		final String applicationId = this.bean.getHireRehireId();
		//String employeeCode = asipothis.bean.getCompletedByID();
		final String userID = this.bean.getUserEmpId();
		final String status = this.bean.getHireStatus();	
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			model.initiate(context, session);
			final boolean result = model.cancelFunction(this.bean, status);
			model.terminate();
			if (result) {
				this.addActionMessage("Application successfully send for cancellation.");
				this.sendCancelApplToApprovalMail(applicationId , userID , status);
			} else {
				this.addActionMessage("Error occured sending cancellation request.");
			}
		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in Cancel Application" + e);
		}
		//input();
		return INPUT;
	}
	
	/**
	 * * Method to Send cancel application for approval mail.
	 * @param applicationId ID
	 * @param userID : User ID
	 * @param status i.e.X for cancellation
	 */
	private void sendCancelApplToApprovalMail(final String applicationId, final String userID, final String status) {
		try {
			final NewHireRehireModel model = new NewHireRehireModel();
			// MAIL FROM Applicant To Approver
			model.initiate(context, session);
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-HIRE/REHIRE DETAILS CANCELLED BY MANAGER");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userID);
			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, this.bean.getApproverCode());
			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, userID);
			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			templateApp.configMailAlert();
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();

		} catch (final Exception e) {
			NewHireRehireAction.logger.error("Exception in sendCancelApplToApprovalMail-" + e);
		}
	}
	/**
     * Method to get bean.
     * 
     * @return bean
     */
	public NewHireRehire getBean() {
		return this.bean;
	}

	/**
	 * @param bean : set bean
	 */
	public void setBean(final NewHireRehire bean) {
		this.bean = bean;
	}

	
	/**
	 * For Selecting Employee.
	 * 
	 * @return String
	 */
	public String f9Employee() {
		/*String str = "0";

		if(!bean.getFirstApproverCode().equals("")) {
			str = bean.getFirstApproverCode();
			System.out.println("bean.getFirstApproverCode()" + bean.getFirstApproverCode());
		}
		else if (!bean.getApproverCode().equals("")){
			str = bean.getApproverCode();
			
			System.out.println("bean.getApproverCode()" + bean.getApproverCode());
		}*/
		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";
		if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
		} else	{
			query += " WHERE 1=1 ";
		}
		//query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S'"; // AND EMP_ID NOT IN("+ str+")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		final String[] headers = {this.getMessage("employee.id") , this.getMessage("employee")};
		final String[] headerWidth = {"20" , "80"};
		final String[] fieldNames = {"exeEmployeeToken", "exeEmployeeName", "exeEmployeeCode"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = NewHireRehireAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "PersonalDataChange_getEmployeeDetailsAction.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireAction.F9_PAGE;
	}
	
}
