package org.struts.action.TravelManagement.TravelReports;

import java.util.TreeMap;

import org.paradyne.bean.TravelManagement.TravelReports.TravelClaimMisReport;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.PAS.Competency.CompDevPlanMisReportModel;
import org.paradyne.model.TravelManagement.TravelReports.TravelApplicationReportModel;
import org.paradyne.model.TravelManagement.TravelReports.TravelClaimMisReportModel;
import org.struts.lib.ParaActionSupport;

public class TravelClaimMisReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TravelClaimMisReportAction.class);
	
	TravelClaimMisReport claimBean = null;
	@Override
	public void prepare_local() throws Exception {
		
		claimBean = new TravelClaimMisReport();
		claimBean.setMenuCode(1115);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return claimBean;
	}

	public TravelClaimMisReport getClaimBean() {
		return claimBean;
	}

	public void setClaimBean(TravelClaimMisReport claimBean) {
		this.claimBean = claimBean;
	}
	
	public String input() throws Exception {
		logger.info("######## IN INPUT METHOD ##############");
		TravelClaimMisReportModel model = new TravelClaimMisReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, claimBean);
		model.terminate();
		return INPUT ;
	
	}
	
public void prepare_withLoginProfileDetails() throws Exception {
		
		
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("zone");
		claimBean.setMap(map);
		dmu.terminate();
	}
	
public String f9Applicant() {
		
		try {
			String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID "
					+ " FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S'";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };
			String[] headerWidth = { "30", "70" };
			String[] fieldNames = { "applicantToken", "applicantName", "applicantId" };
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

	
	
	public String generateReport(){
		
		try {
			TravelClaimMisReportModel model = new TravelClaimMisReportModel();
			model.initiate(context, session);
			model.genReport(claimBean, response,request,"");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	return null;	
	}
	
	public String reset() {
		try {
		
			claimBean.setApplicantToken("");
			claimBean.setApplicantName("");
			claimBean.setApplicantId("");
			claimBean.setApproverToken("");
			claimBean.setApproverName("");
			claimBean.setStartDate("");
			claimBean.setEndDate("");
			claimBean.setApplicationStatus("");
			claimBean.setApproverId("");
			claimBean.setReportType("");
			claimBean.setPurposeName("");
			claimBean.setPurposeId("");
			claimBean.setTravelTypeId("");
			claimBean.setTravelTypeName("");
			claimBean.setCustomerId("");
			claimBean.setCustomerName("");
			claimBean.setProjectId("");
			claimBean.setProjectName("");
			claimBean.setBranchCode("");
			claimBean.setBranchName("");
			claimBean.setDepartmentCode("");
			claimBean.setDepartmentName("");
			claimBean.setGradeId("");
			claimBean.setGradeName("");
			claimBean.setBlockedStatus("");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
	public final String mailReport() {
		try {
			TravelClaimMisReportModel model = new TravelClaimMisReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			model.genReport(claimBean,response,request,reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}
