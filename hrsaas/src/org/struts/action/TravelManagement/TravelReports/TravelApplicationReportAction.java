package org.struts.action.TravelManagement.TravelReports;

import java.util.TreeMap;

import org.paradyne.bean.TravelManagement.TravelReports.TravelApplicationReport;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.TravelManagement.TravelReports.TravelApplicationReportModel;
import org.struts.lib.ParaActionSupport;

public class TravelApplicationReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TravelApplicationReportAction.class);
	TravelApplicationReport applicationBean = null;


	public void prepare_local() throws Exception {
	
		applicationBean = new TravelApplicationReport();
		applicationBean.setMenuCode(1114);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return applicationBean;
	}
	
	public String input() throws Exception {
		TravelApplicationReportModel model = new TravelApplicationReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, applicationBean);
		model.terminate();
		
		return INPUT ;
	
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		
		
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("zone");
		applicationBean.setMap(map);
		dmu.terminate();
	}

	public TravelApplicationReport getApplicationBean() {
		return applicationBean;
	}

	public void setApplicationBean(TravelApplicationReport applicationBean) {
		this.applicationBean = applicationBean;
	}
	
	public String f9Division() {
		try {
			String query = "SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION";
			if (applicationBean.getUserProfileDivision() != null
					&& applicationBean.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN ("
						+ applicationBean.getUserProfileDivision() + ")";
			query += " ORDER BY  DIV_ID ";
			String[] headers = { "Division Id", "Division Name" };
			String[] headerWidth = { "50", "50" };
			String[] fieldNames = {"divisionName", "divisionId" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
		
	}
	
	public String f9Initiator() {
		
		try {
			String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID "
					+ " FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S'";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };
			String[] headerWidth = { "30", "70" };
			String[] fieldNames = { "initiatorToken", "initiatorName", "initiatorId" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
		
	}
	

	public String f9Approver() {
		
		try {
			String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID "
				+ " FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S'";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };
			String[] headerWidth = { "30", "70" };
			String[] fieldNames = { "approverToken", "approverName", "approverId" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
	}
	

	public String f9Department() {
		try {
			String query = 	" SELECT  DEPT_NAME,DEPT_ID FROM HRMS_DEPT ORDER BY DEPT_NAME";

			String[] headers = { getMessage("department") };

			String[] headerWidth = { "100" };
			
			
			String[] fieldNames = { "departmentName" ,"departmentCode"};

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
		
		}
		
		return "f9page";
	}
	
	public String f9Branch() {
		
		try {
			String query = 	" SELECT CENTER_NAME, CENTER_ID "
				+ " FROM HRMS_CENTER "
				+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_LOCATION) "
				+ " WHERE HRMS_LOCATION.LOCATION_TYPE='Ci' ORDER BY upper(CENTER_NAME)";

			String[] headers = { getMessage("branch") };

			String[] headerWidth = { "100" };
			
			
			String[] fieldNames = { "branchName" ,"branchCode"};

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

		} catch (Exception e) {
			
		}
		return "f9page";
	}
	
	public String f9Designation() {
		
		try {
			
			String query = "SELECT NVL(RANK_NAME,' '),RANK_ID  FROM HRMS_RANK ORDER BY RANK_NAME";
			String[] headers = { "Designation" };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "designationName", "designationCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
		
	}
	
	public String f9Purpose() throws Exception {

		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
			 * OUTPUT
			 */
			String query = " SELECT PURPOSE_NAME,PURPOSE_ID FROM HRMS_TMS_PURPOSE WHERE PURPOSE_STATUS='A' ORDER BY UPPER(PURPOSE_NAME) ASC";
			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = { "Purpose" };
			/**
			 * SET THE WIDTH OF TABLE COLUMNS.
			 */
			String[] headerWidth = { "30" };
			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
			 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
			 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
			 * FIELDNAMES
			 */
			String[] fieldNames = { "purposeName", "purposeId" };
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
			String submitToMethod = "";
			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";

	}

	public String f9TravelType() throws Exception {

		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
			 * OUTPUT
			 */
			String query = " SELECT LOCATION_TYPE_NAME,LOCATION_TYPE_ID FROM HRMS_TMS_LOCATION_TYPE WHERE LOCATION_TYPE_STATUS='A' ORDER BY UPPER(LOCATION_TYPE_NAME) ASC";
			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = { "Travel Type" };
			/**
			 * SET THE WIDTH OF TABLE COLUMNS.
			 */
			String[] headerWidth = { "30" };
			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
			 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
			 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
			 * FIELDNAMES
			 */
			String[] fieldNames = {  "travelTypeName","travelTypeId" };
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
			String submitToMethod = "";
			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";

	}
	
	public String f9gradeName(){
		
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CADRE_NAME , CADRE_ID  FROM HRMS_CADRE  ORDER BY CADRE_NAME  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("grade") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "gradeName", "gradeId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		
		return "f9page";
	}

	
	public String f9Customer() throws Exception {

		try {
			String query = " SELECT TRAVEL_CUST_NAME,TRAVEL_CUST_ID FROM TMS_TRAVEL_CUSTOMER ORDER BY TRAVEL_CUST_ID";
			String[] headers = { "Customer" };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "customerName", "customerId" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";

	}
	
	public String f9Project() throws Exception {

		try {
			String query = " SELECT PROJECT_NAME,PROJECT_ID FROM TMS_TRAVEL_PROJECT ORDER BY PROJECT_ID";
			String[] headers = { "Project" };
			String[] headerWidth = { "30" };
			String[] fieldNames = { "projectName", "projectId" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";

	}

	
	public String generateReport(){
		
		try {
			TravelApplicationReportModel model = new TravelApplicationReportModel();
			model.initiate(context, session);
			model.generateReport(applicationBean, response,request,"");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	return null;	
	}
	
	public String reset() {
		try {
			applicationBean.setInitiatorToken("");
			applicationBean.setInitiatorName("");
			applicationBean.setApproverToken("");
			applicationBean.setApproverName("");
			applicationBean.setDivisionName("");
			applicationBean.setDivisionId("");
			applicationBean.setDepartmentCode("");
			applicationBean.setDepartmentName("");
			applicationBean.setBranchCode("");
			applicationBean.setDesignationCode("");
			applicationBean.setDesignationName("");
			applicationBean.setGradeId("");
			applicationBean.setGradeName("");
			applicationBean.setStartDate("");
			applicationBean.setEndDate("");
			applicationBean.setApplicationStatus("");
			applicationBean.setInitiatorId("");
			applicationBean.setApproverId("");
			applicationBean.setReportType("");
			applicationBean.setPurposeName("");
			applicationBean.setPurposeId("");
			applicationBean.setTravelTypeId("");
			applicationBean.setTravelTypeName("");
			applicationBean.setCustomerId("");
			applicationBean.setCustomerName("");
			applicationBean.setProjectId("");
			applicationBean.setProjectName("");
			applicationBean.setZone("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
	
	public final String mailReport() {
		try {
			TravelApplicationReportModel model = new TravelApplicationReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			model.generateReport(applicationBean,response,request,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
}
