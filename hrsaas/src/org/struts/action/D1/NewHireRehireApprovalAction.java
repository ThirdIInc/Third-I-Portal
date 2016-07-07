package org.struts.action.D1;

import org.paradyne.bean.D1.NewHireRehireApprover;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.NewHireRehireApprovalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1385
 *
 */
public class NewHireRehireApprovalAction extends ParaActionSupport {
	/**
     * Used for f9 windows.
     */
	private static final String F9_PAGE = "f9page";
	/**
     * Used for f9 windows submit Flag false.
     */
	private static final String SUBMIT_FLAG_FALSE = "false";
	/**
     * Log4j logger.
     */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewHireRehireApprovalAction.class);
	/**
     * Object of NewHireRehireApprover.
     */
	private NewHireRehireApprover bean;
	 /*
     * (non-Javadoc)
     * 
     * @see org.struts.lib.ParaActionSupport#prepare_local()
     */
    @Override
	public void prepare_local() throws Exception {
		this.bean = new NewHireRehireApprover();
		this.bean.setMenuCode(2019);
	}
	/**
     * Method to get bean.
     * 
     * @return bean
     */
	public Object getModel() {
		return this.bean;
	}
	/**
	 * Method : input.
	 * Purpose : For getting pending list
	 * @return String : String
	 */
	public String input() {
		try {
			final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isITDeptApprover = model.isITDeptApprover(userId);
			if (isITDeptApprover) {
				//model.getPendingList(bean, request);	
			/*}
			else
			{*/
				model.getPendingList(this.bean , request , userId);	
			}
			this.bean.setListType("pending");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	/**
     * Method to get list of approved applications.
     * @throws Exception - this.input() throws Exception
     * @return INPUT
     */
	public String getApprovedList() throws Exception {		
		try {
			final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isITDeptApprover = model.isITDeptApprover(userId);
			if (isITDeptApprover) {
				//model.getApprovedList(bean, request);
			/*}
			else
			{*/
				model.getApprovedList(this.bean, request, userId);
			}
			this.bean.setListType("approved");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
     * Method to get list of rejected applications.
     * @throws Exception - this.input() throws Exception
     * @return INPUT
     */
	public String getRejectedList() throws Exception {		
		try {
			final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
			model.initiate(context, session);
			final String userId = this.bean.getUserEmpId();
			final boolean isITDeptApprover = model.isITDeptApprover(userId);
			model.getRejectedList(this.bean, request, userId);
			this.bean.setListType("rejected");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	/**
	 * Method : approveApplication.
	 * Purpose : For approving application
	 * @return : String
	 */
	public String approveApplication() {	
		final String applicationId = this.bean.getHireRehireApprovalId();
		//String employeeCode = reqApproverBean.getEmployeeID();
		final String userID = this.bean.getUserEmpId();
		final String status = this.bean.getStatus();
		final String actionType = this.bean.getActionType();
		System.out.println("actionType ===========  " + actionType);
		
		
		try {
			final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
			model.initiate(context, session);
			final boolean checkFlag = model.checkTokenExit(this.bean.getEmpToken());
			if (checkFlag) {
				this.bean.setApproverCommentsFlag(true);
				this.bean.setEmpOfficeDtlFlag(false);
				this.getNavigationPanel(5);
				this.bean.setEnableAll("N");
				this.bean.setEmpToken("");
				this.addActionMessage("Employee Id already Exit. Please Enter new Employee Id.");
				this.bean.setDisplayFlag("true");
				return SUCCESS;
			}
			final boolean result = model.approveApplicationFunction(this.bean, status,actionType);
			model.terminate();
			if (result) {
				
				String mailId = bean.getEmailId().trim();
				System.out.println("mailId  here we got is =============="+ mailId);
				this.addActionMessage("Application approved successfully.");
				this.sendApprovalMail(applicationId, userID, status,mailId); 
			} else {
				this.addActionMessage("Error occured.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * Method : rejectApplication.
	 * Purpose : For Rejecting application
	 * @return : String
	 */
	public String rejectApplication() {
		try {
			final String applicationId = this.bean.getHireRehireApprovalId();
			final String approverComments = this.bean.getApproverComments();
			final String userId = this.bean.getUserEmpId();
		//	String requesterCode = bean.getRequesterID();
			final String status = this.bean.getStatus();
			final String initiatorCode = this.bean.getInitiatorCode();
			final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
			model.initiate(context, session);
			model.reject(applicationId, approverComments, userId);
			model.terminate();
			this.addActionMessage("Application rejected successfully.");

			this.sendBackRejectApprovalMail(applicationId , userId , status , initiatorCode); 
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * Method : sendBackRejectApprovalMail.
	 * Purpose : For sending mail from approver to applicant 
	 * @param applicationId : Application ID
	 * @param userId : Current User ID
	 * @param initiatorCode : initiator ID
	 * @param status : Status
	 */
	private void sendBackRejectApprovalMail(final String applicationId ,
			final String userId , final String status , final String initiatorCode) {
		try {
			final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			final String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID " + 
				" FROM HRMS_D1_APPROVAL_SETTINGS " + 
				" WHERE APP_SETTINGS_TYPE ='H'AND APP_EMAIL_ID IS NOT NULL ";
			final Object [][] data = model.getSqlModel().getSingleResult(query);
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-HIRE/REHIRE DETAILS SENDBACK/REJECT FROM MANAGER TO HR");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, initiatorCode);
			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, userId);
			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			final EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, applicationId);
			
			/*String[] empData = null;
			
			if(data != null && data.length > 1) {
				empData = new String[data.length];
				for(int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
			}*/
			templateApp.configMailAlert();
			if (data != null && data.length > 0) {
				templateApp.sendApplicationMailToGroups(data);
			}
		//	templateApp.sendApplicationMailToKeepInfo(empData);
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Method : sendBackApplication.
	 * Purpose : For sending back application to applicant.
	 * @return : String
	 */
	public String sendBackApplication()	{
		try {
			final String applicationId = this.bean.getHireRehireApprovalId();
			final String approverComments = this.bean.getApproverComments();
			final String userId = this.bean.getUserEmpId();
			//String requesterCode = bean.getEmployeeID();

			final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
			model.initiate(context, session);
			final String nextApprover = userId;
			final String initiatorCode = this.bean.getInitiatorCode();
			/*Object[][] empFlow = generateEmpFlow(requesterCode, "D1", 1);
			if(empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
			}*/

			final String status = model.sendBack(applicationId, approverComments, userId, nextApprover);
			
			this.bean.setStatus(status);
			this.bean.setApproverComments("");
			model.terminate();

			this.addActionMessage("Application send back successfully.");
			this.sendBackRejectApprovalMail(applicationId , userId , status , initiatorCode); 
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.input();
		return INPUT;
	}
	
	/**
	 * Method : viewApplicationFunction.
	 * Purpose : For sending back application to applicant.
	 * @return : SUCCESS
	 */	
	public String viewApplicationFunction()	{
		final String typeOfList = this.bean.getListType();		
		this.bean.setListTypeDetailPage(typeOfList);
		
		final String applCode = request.getParameter("applCode");
		try {
			String applicationID = request.getParameter("hireRehireApprovalId");
			System.out.println("applicationID =========" + applicationID);
			String actionType = bean.getActionType();
			System.out.println("actionType   === " +actionType);
			final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
			model.initiate(context, session);
			
			/*
			 * FOR SUPER USER
			 */
			
			if (applCode != null && applCode.length() > 0) {
				applicationID = applCode;
			}
			
			String actionQuery = "select ACTION_REASON , HIRE_EMP_ID from HRMS_D1_NEW_HIRE_REHIRE where HIRE_REHIRE_ID = "+applicationID;
			Object [][] actionTypeData = model.getSqlModel().getSingleResult(actionQuery);
			
			String type = String.valueOf(actionTypeData[0][0]);
			String code = String.valueOf(actionTypeData[0][1]);
			System.out.println("code >>>>>>>>>>>>>> "+ code);

			
			if(type.equals("R"))
			{
				bean.setLabelFlag("true");
			} else {
				bean.setLabelFlag("false");
			}
			
		   model.viewApplicationFunction(this.bean, applicationID, typeOfList);
			
			
		//	Object[][] apprCommentListObj = model.getApproverCommentList(applicationID);
		//	populateApproverComments(apprCommentListObj);
			model.terminate();
		
		if ("pending".equals(typeOfList)) {
			this.bean.setApproverCommentsFlag(true);
			this.bean.setEmpOfficeDtlFlag(false);
			this.getNavigationPanel(5);
			this.bean.setEnableAll("N");
		} else
			if ("approved".equals(typeOfList)) {
				this.bean.setApproverCommentsFlag(false);
				this.bean.setEmpOfficeDtlFlag(true);
			
				if(type.equals("R"))
				{
					
					model.viewOfficialDetails(this.bean, code);
				}
				this.getNavigationPanel(2);
				this.bean.setEnableAll("N");
			} else {
				 //bean.setApproverCommentsFlag(false);
				  // bean.setEmpOfficeDtlFlag(false);
				this.getNavigationPanel(2);
				this.bean.setEnableAll("N");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		if (applCode != null && applCode.length() > 0) {
			if(bean.getStatus().equals("D") || bean.getStatus().equals("A")|| bean.getStatus().equals("B")|| bean.getStatus().equals("R"))
			{
				getNavigationPanel(7);
				bean.setEnableAll("N");
			}else
				{
				bean.setEmpOfficeDtlFlag(false);
					getNavigationPanel(6);
					bean.setEnableAll("N");
				}
			
    }
		return SUCCESS;
	}
	/**
     * Method to go list page .
     * 
     * @return INPUT
     * * @throws Exception - this.input() throws Exception
     */
	public String backToList() throws Exception {
		final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
		model.initiate(context, session);
		 final String listType = model.checkNull(this.bean.getListType());
         if ("".equals(listType)) {
        	 input();
         }  else if (listType.equals("approved")){
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
	 * Method : sendApprovalMail.
	 * Purpose : For sending mail from approver to applicant 
	 * @param applicationId : Application ID
	 * @param userId : Current User ID
	 * @param status : Status
	 */
	private void sendApprovalMail(final String applicationId , final String userId , final String status ,final String mailId) {
		try {
			try {
				final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
				model.initiate(context, session);
				String pass = "pass@123";
				String chkQuery = "SELECT ACTION_REASON, HIRE_EMP_ID FROM HRMS_D1_NEW_HIRE_REHIRE WHERE HIRE_REHIRE_ID="+applicationId; 
				Object [][] chkData = model.getSqlModel().getSingleResult(chkQuery); 
				String empCode = String.valueOf(chkData[0][1]);
				
				if(String.valueOf(chkData[0][0]).equals("R"))
				{
					
					System.out.println("my query >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					final String passQuery = "SELECT LOGIN_PASSWORD  FROM HRMS_LOGIN WHERE EMP_ID = "+empCode+" AND PROFILE_CODE = 68 ";
					final Object[][]passObj = model.getSqlModel().getSingleResult(passQuery);
					if (passObj != null && passObj.length > 0) {
						pass = String.valueOf(passObj[0][0]);
					}
					try {
						pass = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt(pass); // encrypted password
					} catch (final Exception e) {
						e.printStackTrace();
					}
					final String query = "select ADD_EMAIL from hrms_emp_address where EMP_ID ="+empCode;
					final Object [][] empData = model.getSqlModel().getSingleResult(query);
					
					System.out.println("empData ========= " + empData[0][0]);
					
					final EmailTemplateBody templateApp = new EmailTemplateBody();
					templateApp.initiate(context, session);
					templateApp.setEmailTemplate("D1-HIRE/REHIRE DETAILS FROM MANAGER TO HR");
					templateApp.getTemplateQueries();
					final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, userId);
					
					final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
					templateQueryApp2.setParameter(1, this.bean.getInitiatorCode());
					
					//System.out.println("String.valueOf(data[0][0]) = " + String.valueOf(data[0][0]).length());
					
					final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, applicationId);
					
					final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, this.bean.getUserEmpId());
					
					final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, applicationId);
					
					final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
					templateQueryApp6.setParameter(1, applicationId);
					
					final EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
					templateQueryApp7.setParameter(1, applicationId);
				
					final EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
					templateQueryApp8.setParameter(1, pass);
					templateQueryApp8.setParameter(2, empCode);
					
					templateApp.configMailAlert();
				
					if (empData != null && empData.length > 0) {
						
						templateApp.sendApplicationMailToGroups(empData);
					}
					templateApp.sendApplicationMail();
					templateApp.clearParameters();
					templateApp.terminate();
					
				} else {
					final String passQuery = "SELECT LOGIN_PASSWORD  FROM HRMS_LOGIN WHERE EMP_ID = " + this.bean.getEmpId() + "  AND PROFILE_CODE = 68 ";
					final Object[][]passObj = model.getSqlModel().getSingleResult(passQuery);
					if (passObj != null && passObj.length > 0) {
						pass = String.valueOf(passObj[0][0]);
					}
				
					try {
						pass = new StringEncrypter(
								StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt(pass); // encrypted password
					} catch (final Exception e) {
						e.printStackTrace();
					}
					final String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID  " + 
						" FROM HRMS_D1_APPROVAL_SETTINGS " + 
						" WHERE APP_SETTINGS_TYPE IN ('H','P') AND APP_EMAIL_ID IS NOT NULL ";
					final Object [][] data = model.getSqlModel().getSingleResult(query);
					final EmailTemplateBody templateApp = new EmailTemplateBody();
					templateApp.initiate(context, session);
					templateApp.setEmailTemplate("D1-HIRE/REHIRE DETAILS FROM MANAGER TO HR");
					templateApp.getTemplateQueries();
					final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
					templateQueryApp1.setParameter(1, userId);
					
					final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
					templateQueryApp2.setParameter(1, this.bean.getInitiatorCode());
					
					//System.out.println("String.valueOf(data[0][0]) = " + String.valueOf(data[0][0]).length());
					
					final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, applicationId);
					
					final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, this.bean.getUserEmpId());
					
					final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, applicationId);
					
					final EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
					templateQueryApp6.setParameter(1, applicationId);
					
					final EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
					templateQueryApp7.setParameter(1, applicationId);
				
					final EmailTemplateQuery templateQueryApp8 = templateApp.getTemplateQuery(8);
					templateQueryApp8.setParameter(1, pass);
					templateQueryApp8.setParameter(2, this.bean.getEmpId());
					
					templateApp.configMailAlert();
					//templateApp.sendApplicationMailToKeepInfo(empData);
					if (data != null && data.length > 0) {
						
						templateApp.sendApplicationMailToGroups(data);
					}
					templateApp.sendApplicationMail();
					templateApp.clearParameters();
					templateApp.terminate();
					
				}
				model.terminate();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		} catch (final Exception e) {
			NewHireRehireApprovalAction.logger.error("Exception in sendApprovalMail" + e);
		}
	}

	/**
	 * @return NewHireRehireApprover bean
	 */
	public NewHireRehireApprover getBean() {
		return this.bean;
	}

	/**
	 * @param bean this.the bean
	 */
	public void setBean(final NewHireRehireApprover bean) {
		this.bean = bean;
	}
	/**
	 * Method to select the Division.
	 * @return String
	 * @throws Exception - this.input() throws Exception
	 */
	public String f9divaction() throws Exception {
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
		if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN (" + this.bean.getUserProfileDivision() + ")"; 
			query += " ORDER BY  DIV_ID ";
		}
		final String[] headers = {this.getMessage("division.code"),	this.getMessage("division")};
		final String[] headerWidth = {"30", "30"};
		final String[] fieldNames = {"divCode", "divName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireApprovalAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex ,
				submitFlag , submitToMethod);
		return NewHireRehireApprovalAction.F9_PAGE;
	}

	/**
	 * Method to select the Center.
	 * @return Center
	 * @throws Exception -- this.input() throws Exception
	 */
	public String f9centeraction() throws Exception {
		final String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER  ORDER BY  CENTER_ID ";
		final String[] headers = {this.getMessage("branch.code") , this.getMessage("branch")};
		final String[] headerWidth = {"30", "30"};
		final String[] fieldNames = {"centerCode", "centerName"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = NewHireRehireApprovalAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return NewHireRehireApprovalAction.F9_PAGE;
	}
	/**
	 * Method to select the rank of an employee.
	 * @return String
	 * @throws Exception - this.input() throws Exception
	 */
	public String f9rankaction() throws Exception {
		final String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME) FROM  HRMS_RANK  ORDER BY  RANK_ID ";
		final String[] headers = {"designation.code" , "designation"};
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"rankCode" , "rankName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireApprovalAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex ,	submitFlag , submitToMethod);
		return NewHireRehireApprovalAction.F9_PAGE;
	}

	/**
	 * Method to select the Shift of an employee.
	 * @return String
	 * @throws Exception - this.input() throws Exception
	 */
	public String f9shiftaction() throws Exception {
		final String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT  ORDER BY  SHIFT_ID ";
		final String[] headers = {this.getMessage("shift.code") , this.getMessage("shift")};
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"shiftCodeAppr", "shiftTypeAppr"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = NewHireRehireApprovalAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireApprovalAction.F9_PAGE;
	}
	/**
	 * To select Employee Type.
	 * @return String
	 * @throws Exception-- this.input() throws Exception
	 */
	public String f9typeaction() throws Exception {
		final String query = " SELECT DISTINCT TYPE_ID,TO_CHAR(TYPE_NAME) FROM  HRMS_EMP_TYPE  ORDER BY  TYPE_ID ";
		final String[] headers = {this.getMessage("employee.type.code") , this.getMessage("employee.type")};
		final String[] headerWidth = {"20", "80"};
		final String[] fieldNames = {"empTypeCode" , "empTypeName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = NewHireRehireApprovalAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return NewHireRehireApprovalAction.F9_PAGE;
	}
	
	/**
	 * For Selecting Employee.
	 * 
	 * @return String
	 */
	public String f9Employee() {
		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";
		if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
		} else	{
			query += " WHERE 1=1 ";
		}
		
		query += " AND EMP_STATUS IN('N','R','E','A') "; 
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		final String[] headers = {this.getMessage("employee.id") , this.getMessage("employee")};
		final String[] headerWidth = {"20" , "80"};
		final String[] fieldNames = {"newHiredEmployeeToken", "newHiredEmployeeName", "newHiredEmployeeCode"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = "true";
		final String submitToMethod = "NewHireRehireApprover_getEmployeeDetails.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String getEmployeeDetails()
	{
		final NewHireRehireApprovalModel model = new NewHireRehireApprovalModel();
		model.initiate(context, session);
		String applicationID = request.getParameter("hireRehireApprovalId");
		
		model.getEmpValues(bean);
		model.setEmailId(bean);
		model.getApproverCommentList(bean,applicationID);
		this.bean.setApproverCommentsFlag(true);
		this.bean.setApproverCommentsFlag(true);
		bean.setDisplayFlag("false");
		this.getNavigationPanel(5);
		
		return SUCCESS;
	}
}
