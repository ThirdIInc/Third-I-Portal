package org.struts.action.D1;

import java.util.Calendar;
import java.util.Date;

import org.paradyne.bean.D1.PersonalRequisition;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.NewHireRehireModel;
import org.paradyne.model.D1.PersonalDataChangeModel;
import org.paradyne.model.D1.PersonalRequisitionModel;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.struts.lib.ParaActionSupport;


public class PersonalRequisitionAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(PersonalRequisitionAction.class);
	
	PersonalRequisition bean;
	
	public void prepare_local() throws Exception {
		bean = new PersonalRequisition();
		bean.setMenuCode(2030);
	}

	public Object getModel() {
		return bean;
	}
	
	public String input()
	{
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getPendingList(bean, request,userId);	
			}			
			bean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	
	/**
	 * Method to select the Grade of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9band() throws Exception {

		String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  "
				+ " WHERE CADRE_IS_ACTIVE = 'Y' ORDER BY CADRE_ID ";

		String[] headers = { getMessage("band.code"), getMessage("band") };

		String[] headerWidth = { "20", "80" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		String[] fieldNames = { "bandId", "bandName" };
	//	offDetail.setMasterMenuCode(19);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * Method to select the Caste of an Employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9castaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT CAST_ID,CAST_NAME FROM HRMS_CAST ORDER BY CAST_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("caste.code"), getMessage("caste") };

		String[] headerWidth = { "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "castCode", "castName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
	//	persDetail.setMasterMenuCode(32);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	
	
	
	
	/**
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9Approver() {
		String str = "0";

		if(!bean.getReqReplacingCode().equals("")) {
			 str = bean.getReqReplacingCode();
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
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + "," + bean.getUserEmpId() + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"approverToken", "approverName", "approverCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	public void setApproverName() {
		PersonalDataChangeModel model = new PersonalDataChangeModel();
		try {
			
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
							.setApproverCode(String.valueOf(data[0][1]));
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}
	/*
	 * For Selecting Approver
	 * 
	 * @return String
	 */
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
	
	
	
	public String f9rankaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK  ORDER BY RANK_ID ";
			

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Executive Code","Executive" };
		
		String[] headerWidth={"10","50"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"executiveCode","executiveName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	// end f9 ganesh
	public String getApprovedList() throws Exception {		
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getApprovedList(bean, request, userId);
			}	
			bean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		getNavigationPanel(6);
		return INPUT;
	}
	
	
	public String getCancelledList() throws Exception {		
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getCancelledList(bean, request, userId);
			}	
			bean.setListType("cancelled");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return INPUT;
	}
	
	
	public String getRejectedList() throws Exception {		
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser)
			{
				model.getRejectedList(bean, request, userId);
			}
			bean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String addNew()
	{ 		
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			model.getSysDate(bean);
			if (bean.isGeneralFlag()) {
				  bean.setEmployeeCode(bean.getEmployeeCode());
				  
				  model.getEmployeeDetails(bean.getUserEmpId(), bean); }// end of
				 else {
				
				model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
				// employee
				// branch,designation etc
				 }
			 
			/* String autoCodeQuery = " SELECT NVL(MAX(PERSONAL_REQ_ID),0)+1 FROM HRMS_D1_PERSONAL_REQUISITION ";
			 Object data[][] = model.getSqlModel()
				.getSingleResult(autoCodeQuery);
			 
			
				if (data != null && data.length > 0) {
					///bean.setRequestID(String.valueOf(data[0][0]));
				}
			
			 String reqId =String.valueOf(data[0][0]);
			 System.out.println("requestId $$$$$$$$$$" + reqId);
			// getTrackingNo(requestId);
*/			// setTrakingNumber();
			model.terminate();
			//reset();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String draftFunction() throws Exception {
		try {
			
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			boolean result;
			if (bean.getRequestID().equals("")) 
			{
				result = model.draftFunction(bean);
				if (result) 
				{
					addActionMessage("Application drafted successfully.");
				} 
				else 
				{
					addActionMessage("Error occured");
					reset();
				}			
			} 
			else 
			{
				result = model.updateRecords(bean);
				if (result) 
				{
					addActionMessage("Application Modified successfully.");
				} 
				else 
				{
					addActionMessage("Error occured");
					reset();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	public String back() throws Exception {
		bean.setRequestID("");
		String listType = bean.getListType();
		if(listType.equals("pending") || listType.equals("")) {
			input();
		} else if(listType.equals("approved")) {
			getApprovedList();
		} else {
			getRejectedList();
		}
		return INPUT;
	}
	
	public String callPage() throws Exception {
		input();
		return INPUT;
	}
	
	public String reset() throws Exception {
		bean.setRequestID("");
		bean.setPositionTitleName("");
		bean.setRequisition("");
		bean.setBandId("");
		bean.setBandName("");
		bean.setMaxSalary("");
		bean.setPositionDate("");
		bean.setDeptCode("");
		bean.setExecutiveCode("");

		bean.setExecutiveName("");
		bean.setWorkLocation("");
		
		bean.setApprovedExistingJob("");
		bean.setNewJobExist("");
		bean.setHeadCount("");
		bean.setReplacementType("");
		bean.setReqReplacing("");
		bean.setTerminationDate("");
		bean.setPositionType("");

		bean.setTemporaryType("");
		bean.setAgencyName("");
		bean.setContractorName("");
		bean.setContractorPhoneNumber("");
		bean.setContractorEmailAddress("");
		bean.setMaximumBillRate("");

		bean.setOvertimeRequired("");
		bean.setNumberOfOvertime("");
		bean.setDurationOfAssignment("");
		bean.setReasonForTemporaryNeed("");
		bean.setVariableWorkfroceRate("");
		bean.setRateType("");
		bean.setRateTypeRadioOptionValue("");
		
		bean.setDurationOfVariableAssignment("");
		bean.setReasonForVariableWorkforceNeed("");
		bean.setBudget("");
		bean.setStdHourPerWeek("");
		bean.setFulltimeParttime("");
		bean.setEducationRequirements("");
		bean.setExperienceRequirement("");
		bean.setEssentialPositionRequirements("");
		bean.setReason("");
		bean.setApproverCode("");
		bean.setApproverToken("");
		bean.setApproverName("");
		bean.setForwardedForApprovar("");
		
		getNavigationPanel(3);
		return SUCCESS;
	}
	
	
	public String delete()
	{
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
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
	
	public String editApplicationFunction()
	{
		try {
			String requestID = request.getParameter("personalRequisitionID");
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			model.editApplicationFunction(bean, requestID);
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return SUCCESS;
	}
	
	public String sendForApprovalFunction()
	{	
		
		
		
		//String employeeCode = bean.getEmployeeID();
		String userID = bean.getUserEmpId();
		String approverID = bean.getUserEmpId();	
	//	String trackCOde = bean.getTrackingNo();
				//String status = bean.getStatus();
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			
			boolean result = model.sendForApprovalFunction(bean, request);
			String applicationId = bean.getRequestID();
		
			model.terminate();
			if (result) {
				String trackCOde = bean.getTrackingNo();
				addActionMessage("Application sent for approval successfully \n Tracking No: "+trackCOde);
				sendApplicantToApprovalMail(applicationId, userID);

			} else {
				addActionMessage("Error occured sending application.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	private void sendApplicantToApprovalMail(String applicationId,
			String userID) {
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			// MAIL FROM Applicant To Approver
			model.initiate(context, session);
			
			System.out.println("applicationId=== " + applicationId);
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-PERSONAL REQUISITION DETAILS TO MANAGER");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userID);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, bean.getApproverCode());
			System.out.println("bean.getApproverCode()=== " + bean.getApproverCode());
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userID);
			
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, applicationId);
			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, applicationId);
			
			String[] empData = null;
			
		/*	
			if(data != null && data.length > 1) {
				System.out.println("data.length : "+data.length);
				empData = new String[data.length - 1];
				for(int i = 1; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
					System.out.println("empData[i] = " + empData[i]);
				}
			}*/
			
			
			
			templateApp.configMailAlert();
			

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String viewApplicationFunction()
	{		
		bean.setListTypeDetailPage(bean.getListType());
		try {
			String requestID = request.getParameter("personalRequisitionID");
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			
			/*
			 * FOR SUPER USER
			 */
			String applCode=request.getParameter("applCode");
			if(applCode !=null &&applCode.length()>0){
				requestID=applCode;
			}
			
			model.viewApplicationFunction(bean, requestID);
			String status = bean.getPersReqStatus();	
			if (status.equals("approved")) {
				bean.setCancellationFlag("true");
			} 
			if (status.equals("A")) {
				bean.setEnableAll("N");
				getNavigationPanel(2);
				
			} else if (status.equals("R")) {
				bean.setEnableAll("N");
				getNavigationPanel(7);
				
			}
			
			model.terminate();
			
			
		bean.setEnableAll("N");
		getNavigationPanel(7);
		if(applCode !=null &&applCode.length()>0){
			getNavigationPanel(0);
			bean.setEnableAll("N");
		}
	} catch (Exception e) {
		getNavigationPanel(0);
		bean.setEnableAll("N");
		e.printStackTrace();
	}
		return SUCCESS;
	}
	
	
	public String viewApprovedApplication()
	{		
		try {
			String requestID = request.getParameter("personalRequisitionID");
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			model.viewApprovedFunction(bean, requestID);
			String status = bean.getPersReqStatus();	
			System.out.println("status$$$$$$$$$$$$" + status);
			if (status.equals("A") || status.equals("B") || status.equals("S") ) {
				bean.setCancellationFlag("true");
			} 
			if (status.equals("A") ||status.equals("S") ) {
				bean.setEnableAll("N");
				getNavigationPanel(6);
				
			} else if (status.equals("R")) {
				bean.setEnableAll("N");
				getNavigationPanel(7);
				
			}
			else if (status.equals("B")) {
				bean.setEnableAll("N");
				getNavigationPanel(4);
				
			}
			model.terminate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.setEnableAll("N");
		
		
		//getNavigationPanel(7);
		return SUCCESS;
	}
	
	/*public String cancel()
	{
		String applicationId = bean.getPersonalRequisitionID();
		//String employeeCode = bean.getEmployeeID();
		String userID = bean.getUserEmpId();
		String approverID = bean.getUserEmpId();		
		String status = bean.getStatus();	
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			boolean result = model.cancelFunction(bean, status);
			model.terminate();
			if (result) {
				addActionMessage("Application successfully send for cancellation.");
				//sendApplicantToApprovalMail(applicationId, userID, employeeCode, status);
				} 
			else {
				addActionMessage("Error occured sending cancellation request.");
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			
		input();
		return INPUT;
	}*/

	public PersonalRequisition getBean() {
		return bean;
	}

	public void setBean(PersonalRequisition bean) {
		this.bean = bean;
	}

	private String getTrackingNo(String requestId) {
		String trackingNo = "";

		if(requestId.length() < 4) {
			int remChars = 4 - requestId.length();

			for(int i = 0; i < remChars; i++) {
				requestId = "0" + requestId;
			}
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		String strDay = day < 10 ? "0" + day : String.valueOf(day);
		String strMonth = month < 10 ? "0" + month : String.valueOf(month);
		String strYear = String.valueOf(year);

		trackingNo = "PERSONAL-" + strYear + strMonth + strDay + "-" + requestId;
		bean.setTrackingNo(trackingNo);
		return trackingNo;
	}
	
	private String setTrakingNumber()
	{System.out.println("@@@@@@@@@@@@@@@@");
		String trackingNo = "";
		PersonalRequisitionModel modelReq = new PersonalRequisitionModel();
		modelReq.initiate(context, session);
		String hireManager = bean.getHiringManagerCode();
		System.out.println("hireManager= "+hireManager);
		String qq="SELECT NVL(MAX(PERSONAL_REQ_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(PERSONAL_REQ_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_PERSONAL_REQUISITION	";
		System.out.println("qq============" + qq);
		 Object data[][] = modelReq.getSqlModel()
			.getSingleResult(qq);
		 
		System.out.println("data============" + data);
		
	//	Object[][]obj=getSqlModel().getSingleResult(qq);
		if(data !=null && data.length>0){
			bean.setTrackingNo(checkNull(String.valueOf(data[0][0])));
		}
		
		ApplCodeTemplateModel model = new ApplCodeTemplateModel();
		model.initiate(context, session);
		String applnCode = model.generateApplicationCode(String.valueOf(data[0][1]), "D1-PERSONAL", hireManager,String.valueOf(data[0][2]));
		System.out.println("applnCode =========" + applnCode);
		bean.setTrackingNo(checkNull(applnCode));
		model.terminate();
		
		modelReq.terminate();
		return trackingNo;
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	public String cancel()
	{
		String applicationId = bean.getRequestID();
		//String employeeCode = asipoBean.getCompletedByID();
		String userID = bean.getUserEmpId();
		String status = bean.getPersReqStatus();	
		String forwardedTo = bean.getForwardedForApprovar();	
		String apprCode = bean.getApproverCode();	
		try {
			PersonalRequisitionModel model = new PersonalRequisitionModel();
			model.initiate(context, session);
			bean.setCancellationFlag("true");
			boolean result = model.cancelFunction(bean, status,apprCode,forwardedTo);
			model.terminate();
			if (result) {
				addActionMessage("Application successfully send for cancellation.");
				sendCancelApplToApprovalMail(applicationId,userID,status);
				} 
			else {
				addActionMessage("Error occured sending cancellation request.");
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		input();
		return INPUT;
	}

	private void sendCancelApplToApprovalMail(String applicationId,
			String userID, String status) {
	try {
				
				NewHireRehireModel model = new NewHireRehireModel();
				// MAIL FROM Applicant To Approver
				model.initiate(context, session);
				
				EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp.setEmailTemplate("D1-PERSONAL REQUISITION DETAILS CANCELLED BY MANAGER");
				templateApp.getTemplateQueries();
				EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
				templateQueryApp1.setParameter(1, userID);
				EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
				templateQueryApp2.setParameter(1, bean.getApproverCode());
				EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, applicationId);
				EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, userID);
				EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, applicationId);
				
				EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
				templateQueryApp6.setParameter(1, applicationId);
				EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
				templateQueryApp7.setParameter(1, applicationId);
				
				templateApp.configMailAlert();
	
				templateApp.sendApplicationMail();
				templateApp.clearParameters();
				templateApp.terminate();
	
				model.terminate();
	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * For Selecting Employee
	 * 
	 * @return String
	 */
	public String f9ReqEmployee() {
		
		String str = "0";

		 if (!bean.getUserEmpId().equals("")){
				str = bean.getUserEmpId();
				
				System.out.println("bean.getUserEmpId()" + bean.getUserEmpId());
			}

		 
			/*if(!bean.getApproverCode().equals("")) {
			str +=","+bean.getApproverCode();
			System.out.println("bean.getFirstApproverCode()" + bean.getApproverCode());
		}*/
		
		
		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"reqReplacingToken", "reqReplacingName", "reqReplacingCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

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

		 if (!bean.getEmployeeCode().equals("")){
				str = bean.getEmployeeCode();
				
				System.out.println("bean.getEmployeeCode()" + bean.getEmployeeCode());
			}

		 
			/*if(!bean.getFirstApproverCode().equals("")) {
			str +=","+bean.getFirstApproverCode();
			System.out.println("bean.getFirstApproverCode()" + bean.getFirstApproverCode());
		}*/

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

		String[] fieldNames = {"hiringManagerToken", "hiringManagerName", "hiringManagerCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
}
