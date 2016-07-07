package org.struts.action.helpdesk;
import java.util.TreeMap;

import org.paradyne.bean.helpdesk.HelpdeskRequestDurationReport;
import org.paradyne.model.helpdesk.HelpDeskMisReportModel;
import org.paradyne.model.helpdesk.HelpDeskProcessModel;
import org.paradyne.model.helpdesk.HelpdeskRequestDurationReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pramila Naik - 
 * @date 18/04/2012
 * HelpdeskRequestDurationAction class to generate the MIS report
 * as per the selected filters
 */

public class HelpdeskRequestDurationAction extends ParaActionSupport {

	HelpdeskRequestDurationReport helpDeskReport;
		static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HelpDeskMisReportAction.class);
		
		@Override
		public void prepare_local() throws Exception {
			// TODO Auto-generated method stub
			helpDeskReport = new HelpdeskRequestDurationReport();
			helpDeskReport.setMenuCode(5017);
			HelpDeskProcessModel model = new HelpDeskProcessModel();
			model.initiate(context, session);
			TreeMap<String, String> map = model.getStatusMap();
			helpDeskReport.setStatusMap(map);
			model.terminate();
		}

		public Object getModel() {
			// TODO Auto-generated method stub
			return helpDeskReport;
		}
		
		/* method name : reset 
		 * purpose     : to reset all the form fields to blank values
		 * return type : String
		 * parameter   : none
		 */
		public String reset(){
			helpDeskReport.setEmployeeCode("");
			helpDeskReport.setEmployeeToken("");
			helpDeskReport.setEmployeeName("");
			helpDeskReport.setFromDate("");
			helpDeskReport.setToDate("");
			helpDeskReport.setStatus("");
			helpDeskReport.setTeamMemberCode(""); 
			helpDeskReport.setTeamMemberToken("");
			helpDeskReport.setTeamMemberName("");
			helpDeskReport.setDeptCode("");
			helpDeskReport.setDeptName("");
			helpDeskReport.setCatagoryCode("");
			helpDeskReport.setCatagoryName("");
			helpDeskReport.setReportType("");
			helpDeskReport.setCenterId("");
			helpDeskReport.setCenterName("");
			
			return "success";
		}
		
		
		/**
		 * Method : f9teamMember().
		 * Purpose : F9 for Assign To Member.
		 * @return String. 
		 */
		public String f9teamMember(){
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			/*String managerCode = helpDeskReport.getEmployeeCode(); 
			System.out.println("managerCode ----------- = "+ managerCode);
			String query =  " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TECH_CODE " 
								+ " FROM HELPDESK_TECHNICIANS " 
								+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HELPDESK_TECHNICIANS.TECH_CODE)"
								+" WHERE MANAGER_CODE ="+managerCode;*/
			
					String query =  " SELECT EMP_TOKEN, EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME NAME, EMP_ID FROM HRMS_EMP_OFFC ";		
						
						/*if (helpDeskReport.getUserProfileDivision() != null 	&& helpDeskReport.getUserProfileDivision().length() > 0) {
							query += " AND   HRMS_EMP_OFFC.EMP_DIV IN ("+ helpDeskReport.getUserProfileDivision() + " )";
						}
						
						query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";*/
					
					query += getprofileQuery(helpDeskReport);
					query +=  " AND EMP_STATUS ='S' ";
					query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
					
			/**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 */ 
			String [] hesderNames = {"Employee Id", "Employee Name"};
			
			/**
			 * 		SET THE WIDTH OF TABLE COLUMNS.	
			 */ 
			String [] headerWidth = {"30", "70"};
			
			/**
			 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
			 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
			 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
			 * */
			String [] fieldNames  = {"teamMemberToken", "teamMemberName", "teamMemberCode"};
			
			/**
			 * 	 	SET THE COLUMN INDEX
			 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
			 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
			 * 			THEN THE COLUMN INDEX CAN BE {1,3}
			 * 		
			 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
			 * 
			 */ 
			int [] columnIndex    = {0, 1, 2};
			
			/**
			 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
			 * 
			 */
			String submitFlag     = "false";
			
			/**		 
			 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
			 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";
			
			/**
			 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
			 */
			setF9Window(query, hesderNames, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}

		
			public String f9deptName() throws Exception {
				/**
				 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
				 * OUTPUT
				 */
				String query = "SELECT NVL(HELPDESK_DEPT.DEPT_NAME,' '), HELPDESK_DEPT.DEPT_CODE FROM HELPDESK_DEPT  WHERE  HELPDESK_DEPT.IS_ACTIVE='Y'  ORDER BY  HELPDESK_DEPT.DEPT_CODE ";

				/**
				 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
				 */
				String[] headers = {"Department Name" };

				String[] headerWidth = { "100" };

				/**
				 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
				 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
				 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
				 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
				 * FIELDNAMES
				 */

				String[] fieldNames = { "deptName", "deptCode" };

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

				return "f9page";
			}
			
		public String f9catagoryName(){
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
			 * OUTPUT
			 */
			String query = "SELECT NVL(REQUEST_TYPE_NAME,' '), REQUEST_TYPE_ID  FROM HELPDESK_REQUEST_TYPE  WHERE HELPDESK_REQUEST_TYPE.IS_ACTIVE = 'Y' ORDER BY REQUEST_TYPE_ID";

			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = {"Catagory Name"};

			String[] headerWidth = { "100" };

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
			 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
			 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
			 * FIELDNAMES
			 */

			String[] fieldNames = {"catagoryName" , "catagoryCode"};

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

			return "f9page";
		}
		
		
		/* method name : f9actionEmployeeName 
		 * purpose     : to select an employee name
		 * return type : String
		 * parameter   : none
		 */
		public String f9actionEmployeeName(){
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			String query = "SELECT EMP_TOKEN, EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME NAME, EMP_ID FROM HRMS_EMP_OFFC ";
							//+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
			
			
			query += getprofileQuery(helpDeskReport);
			
			query +=  " AND EMP_STATUS ='S' ";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
			
			
			/*if (helpDeskReport.getUserProfileDivision() != null 	&& helpDeskReport.getUserProfileDivision().length() > 0) {
				query += " AND   HRMS_EMP_OFFC.EMP_DIV IN ("+ helpDeskReport.getUserProfileDivision() + " )";
			}
			
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";*/
			
			/**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 */ 
			String [] hesderNames = {"Employee Id", "Employee Name"};
			
			/**
			 * 		SET THE WIDTH OF TABLE COLUMNS.	
			 */ 
			String [] headerWidth = {"30", "70"};
			
			/**
			 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
			 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
			 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
			 * */
			String [] fieldNames  = {"employeeToken", "employeeName", "employeeCode"};
			
			/**
			 * 	 	SET THE COLUMN INDEX
			 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
			 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
			 * 			THEN THE COLUMN INDEX CAN BE {1,3}
			 * 		
			 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
			 * 
			 */ 
			int [] columnIndex    = {0, 1, 2};
			
			/**
			 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
			 * 
			 */
			String submitFlag     = "false";
			
			/**		 
			 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
			 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";
			
			/**
			 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
			 */
			setF9Window(query, hesderNames, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}

		//Added By Tinshuk Banafar Begins
		
		public String f9centerName() throws Exception {
				
				String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER "
						
						+ " ORDER BY  CENTER_ID ";

				String[] headers = { "Branch Name" };

				String[] headerWidth = { "100" };

				

				String[] fieldNames = { "centerName", "centerId" };

				
				int[] columnIndex = { 0, 1 };

							String submitFlag = "false";

				String submitToMethod = "";
				
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
						submitFlag, submitToMethod);

				return "f9page";
		}

		public final String getReport() throws Exception {
			HelpdeskRequestDurationReportModel model = new HelpdeskRequestDurationReportModel();
			model.initiate(context, session);
			String reportPath = "";
			model.RequestDurationReport(helpDeskReport,response,request,reportPath);
			model.terminate();
			return null;
		}
		
		public final String mailReport() {
			try {
			    final HelpdeskRequestDurationReportModel model = new HelpdeskRequestDurationReportModel();
				model.initiate(context, session);
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				String reportPath = getText("data_path") + "/Report/Master"
						+ poolName + "/";
				model.RequestDurationReport(helpDeskReport,response, request, reportPath);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "mailReport";
		}
		
		
	}


