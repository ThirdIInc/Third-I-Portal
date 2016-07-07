package org.struts.action.D1;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.bean.D1.CashAdvanceRequestForm;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CashAdvanceRequestModel;
import org.paradyne.model.D1.NewHireRehireApprovalModel;
import org.paradyne.model.common.ReportingModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Feb 15, 2011
 */

public class CashAdvanceRequestAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CashAdvanceRequestAction.class);

	CashAdvanceRequestForm bean;

	public String addNew() {
		try {
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
	
			
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(3);
		return SUCCESS;
	}

	public void setApproverName() {
		CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
	

	


	public CashAdvanceRequestForm getBean() {
		return bean;
	}

	public void setBean(CashAdvanceRequestForm bean) {
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
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
		bean = new CashAdvanceRequestForm();
		bean.setMenuCode(2045);
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

		String[] fieldNames = {"approverToken", "approverName", "approverCode"};

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

		//String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";
		 String query = "SELECT EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME || ' ' || EMP_LNAME AS ENAME, RANK.RANK_NAME, "
				+ "HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC LEFT JOIN HRMS_RANK RANK ON(RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) ";
				
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

		String[] headers = {getMessage("employee.id"), getMessage("employee"), getMessage("emp.designation")};

		String[] headerWidth = {"20", "60", "20"};

		String[] fieldNames = {"employeeToken", "employeeName","designation", "employeeCode"};

		int[] columnIndex = {0, 1, 2,3};

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

		String submitToMethod = "CashAdvanceRequest_getCourseDetailsAction.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}

	
	
	
	public String draftFunction() throws Exception {
		try {
			
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
			model.initiate(context, session);
			boolean result = model.sendForApprovalFunction(bean, request);
			model.terminate();
			if (result) {
				String trackCOde = bean.getTrackingNo();
				System.out.println("trackCOde#######" +trackCOde );
				addActionMessage("Application send for approval successfully \n Tracking No: "+trackCOde);

			} else {
				addActionMessage("Error occured sending application.");
			}
			
			sendMail(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	private void sendMail() {
		// Mail From Applicant to Approver
		
		String applicationID = bean.getHiddenCode();
		String apprCode = bean.getApproverCode();
		String userId = bean.getUserEmpId();
		System.out.println("MAIL FROM APPROVER TO MANAGER");
		CashAdvanceRequestModel model = new CashAdvanceRequestModel();
		// MAIL FROM APPROVER TO MANAGER
		model.initiate(context, session);

		EmailTemplateBody template = new EmailTemplateBody();
		template.initiate(context, session);
		

		template.setEmailTemplate("D1-CASH ADVANCE REQUEST DETAILS TO APPROVER");

		template.getTemplateQueries();

		EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
		// EMAIL
		templateQuery1.setParameter(1, userId);

		EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
		// EMAIL
		
		
		templateQuery2.setParameter(1, apprCode);

		EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
		templateQuery3.setParameter(1, applicationID);

		EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
		templateQuery4.setParameter(1, applicationID);
		
		EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
		templateQuery5.setParameter(1, applicationID);

		template.configMailAlert();
		// template.sendProcessManagerAlert(approver, module, msgType,
		// applnID, level);
		template.sendApplicationMail();
		template.clearParameters();
		template.terminate();

		// Mail From Applicant to Approver End
		
	}
	
	public String editApplicationFunction()
	{
		try {
			String requestID = request.getParameter("cashAdvID");
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
			String requestID = request.getParameter("cashAdvancId");
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
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
			

			/*bean.setCourseName("");
			bean.setCourseLocationName("");
			bean.setStartDate("");
			bean.setEndDate("");*/
			
			CashAdvanceRequestModel model = new CashAdvanceRequestModel();
			model.initiate(context, session);
			
			bean.setStartDate("");
			bean.setEndDate("");
			bean.setEmployeeToken("");
			bean.setEmployeeName("");
			bean.setEmployeeCode("");
			bean.setDeptNumber("");
			bean.setDeptName("");
			
			
			bean.setCityName("");
			bean.setCityId("");
			bean.setStateName("");
			bean.setStateZip("");
			
			bean.setPhoneNumber("");
			bean.setNoOfEmployeeTravel("");
			bean.setEmployeeAddress("");
			bean.setBusinessPurpose("");
			bean.setTripDuration("");
			bean.setAdvanceAmount("");
			bean.setAdvanceNeededDate("");
			bean.setComments("");
			bean.setDepartmentCode("");
			bean.setDepartmentName("");
			bean.setApproverName("");
			bean.setApproverCode("");
			bean.setApproverToken("");
			
			model.getEmployeeDetails(bean.getUserEmpId(), bean);// getting
			// employee
			// branch,designation etc
			model.terminate();
			getNavigationPanel(3);
		} catch(Exception e) {
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

		//query += getprofileQuery(bean);
	
	//	query += "	ORDER BY HRMS_D1_DEPARTMENT.DEPT_ID";

		String[] headers = { "Department Id","Department Name"};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {  "departmentCode", "departmentName"};

		int[] columnIndex = { 0,1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
}