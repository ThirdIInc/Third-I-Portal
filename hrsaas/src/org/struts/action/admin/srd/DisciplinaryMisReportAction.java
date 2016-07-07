package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.DisciplinaryMisReport;
import org.paradyne.model.admin.srd.DisciplinaryMisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Archana
 * GTL-AA 1711
 *
 */

public class DisciplinaryMisReportAction  extends ParaActionSupport{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(DisciplinaryMisReportAction.class);
	
	DisciplinaryMisReport disMisReport;
	

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		disMisReport = new DisciplinaryMisReport ();
		disMisReport.setMenuCode(2291);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return disMisReport;
	}

	public DisciplinaryMisReport getDisMisReport() {
		return disMisReport;
	}

	public void setDisMisReport(DisciplinaryMisReport disMisReport) {
		this.disMisReport = disMisReport;
	}
	
	// Generate Report using iVreport library
	public String disciplinaryReport() throws Exception {
		System.out.println("In Action");
		DisciplinaryMisReportModel model = new DisciplinaryMisReportModel();
		model.initiate(context, session);
		String reportPath="";
		model.getDisMISReport(disMisReport, request, response, reportPath);
		model.terminate();
		return null;
	}
	
	//To generate Mail Report
	
	public final String mailReport()
	{
		try
		{
		  final DisciplinaryMisReportModel model = new DisciplinaryMisReportModel();
		  model.initiate(context, session);
		  String poolName= String.valueOf(session.getAttribute("session_pool"));
		  if(!(poolName.equals("")|| poolName==null))
		  {
	 		 poolName="\\"+poolName;
	      }
		  String reportPath= getText("data_path") + "\\Report\\Master" + poolName + "\\";
		  model.getDisMISReport(disMisReport, request, response, reportPath);
		  model.terminate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String clear()
	{
		disMisReport.setDivCode("");
		disMisReport.setDivision("");
		disMisReport.setDeptCode("");
		disMisReport.setDeptName("");
		disMisReport.setDeptCode("");
		disMisReport.setDesgName("");
		disMisReport.setCenterId("");
		disMisReport.setCenterName("");
		disMisReport.setEmpid("");
		disMisReport.setEmpName("");		
		disMisReport.setToken("");
		disMisReport.setStatus("");
		return "success";
	}
	
	public String f9MultiDiv() {
		try {
			String query = " SELECT " + " 	DISTINCT DIV_ID, " + "		DIV_NAME "
					+ " FROM " + " 	HRMS_DIVISION ";

			if (this.disMisReport.getUserProfileDivision()!= null
					&& this.disMisReport.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN (" + this.disMisReport.getUserProfileDivision()
						+ ")";
			query += " ORDER BY  DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_division";

			String hiddenFieldId = "paraFrm_divCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger.error("Error in DisciplinaryMisReportAction.f9MultiDiv() method Action : "
							+ e.getMessage());
			return "";
		}
	}
		/**
		 * Method to select the Branch
		 */
		
		public String f9Brch() {
			try {
				String query = " SELECT " + " 	DISTINCT CENTER_ID ,"
						+ "		CENTER_NAME " + " FROM " + " 	HRMS_CENTER "
						+ " ORDER BY " + "		UPPER (CENTER_NAME) ";

				String header = getMessage("branch");
				String textAreaId = "paraFrm_centerName";

				String hiddenFieldId = "paraFrm_centerId";

				String submitFlag = "";
				String submitToMethod = "";

				setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
						submitFlag, submitToMethod);
				return "f9multiSelect";
			} catch (Exception e) {
				logger
						.error("Error in DisciplinaryMisReportAction.f9Brch() method Action : "
								+ e.getMessage());
				return "";
			}
		}
		
		public String f9MultiDept() {
			try {
				String query = " SELECT " + "		DISTINCT DEPT_ID," + "		DEPT_NAME "
						+ "	FROM " + " 	HRMS_DEPT  " + " ORDER BY "
						+ "		UPPER (DEPT_NAME) ";

				String header = getMessage("department");
				String textAreaId = "paraFrm_deptName";

				String hiddenFieldId = "paraFrm_deptCode";

				String submitFlag = "";
				String submitToMethod = "";

				setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
						submitFlag, submitToMethod);
				return "f9multiSelect";

			} catch (Exception e) {
				logger
						.error("Error in DisciplinaryMisReportAction.f9MultiDept() method Action : "
								+ e.getMessage());
				return "";
			}
		}
		
		public String f9MultiRank() {
			try {
				String query = " SELECT " + "		DISTINCT RANK_ID," + "		RANK_NAME "
						+ "	FROM " + " 	HRMS_RANK  " + " ORDER BY "
						+ "		UPPER (RANK_NAME) ";

				String header = getMessage("designation");
				String textAreaId = "paraFrm_desgName";

				String hiddenFieldId = "paraFrm_desgCode";

				String submitFlag = "";
				String submitToMethod = "";

				setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
						submitFlag, submitToMethod);
				return "f9multiSelect";

			} catch (Exception e) {
				logger
						.error("Error in DisciplinaryMisReportAction.f9MultiRank() method Action : "
								+ e.getMessage());
				return "";
			}
		}
		
		public String f9action() throws Exception {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
			 * OUTPUT ALONG WITH PROFILES
			 */
			String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
					+ "  EMP_ID FROM HRMS_EMP_OFFC";
					
			 //SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. 

			String[] headers = { "Employee Id", getMessage("employee")};
			 //DEFINE THE PERCENT WIDTH OF EACH COLUMN
			String[] headerWidth = { "15", "35" };

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
			 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
			 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
			 * FIELDNAMES
			 */

			String[] fieldNames = { "token", "empName","empid" };

			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
			 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
			 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
			 * 
			 * NOTE: COLUMN NUMBERS STARTS WITH 0
			 */
			 
			int[] columnIndex = { 0, 1, 2 };
			//WHEN SET TO 'true' WILL SUBMIT THE FORM
			String submitFlag = "false";

			/**
			 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
			 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
			 * ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";

			// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		}

}
