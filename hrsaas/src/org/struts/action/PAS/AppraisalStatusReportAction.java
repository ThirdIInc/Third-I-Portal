/**
 * 
 */
package org.struts.action.PAS;

import java.util.LinkedHashMap;
import org.paradyne.bean.PAS.AppraisalStatusReport;
import org.paradyne.model.PAS.AppraisalEligibleEmpReportModel;
import org.paradyne.model.PAS.AppraisalStatusReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class AppraisalStatusReportAction extends ParaActionSupport {

	AppraisalStatusReport appStatus;
	public void prepare_local() throws Exception {
		appStatus = new AppraisalStatusReport();
		appStatus.setMenuCode(843);
	}

	public Object getModel() {
		return appStatus;
	}
	
	public String getPhaseList()
	{
		AppraisalStatusReportModel model = new AppraisalStatusReportModel();
		model.initiate(context, session);
		try {
			LinkedHashMap hMap = model.getPhaseList(appStatus.getApprCode());
			appStatus.setPhaseList(hMap);
		} catch (Exception e) {
		}
		model.terminate();
		return SUCCESS;
	}
	
	public String f9AppraisalCode()
	{
		String query = " SELECT APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR ";
		String headers[] = {getMessage("appr.code"),"Appraisal Start Date","Appraisal End Date"};
		String headerWidth[]= {"50","25","25"};
		String fieldNames[]={"apprName","startDate","endDate","apprCode"};
		int columnIndex[]={0,1,2,3};
		String submitFlag="true";
		String submitToMethod="AppraisalStatusReport_getPhaseList.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9Div()
	{
		try
		{
			String query = " SELECT  DISTINCT DIV_NAME,DIV_ID FROM HRMS_DIVISION "
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = " +appStatus.getApprCode()+ " AND IS_ACTIVE='Y'" 
				+" ORDER BY DIV_ID ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Div
	
	public String f9Branch()
	{
		try
		{
			String query = " SELECT  DISTINCT CENTER_NAME,CENTER_ID FROM HRMS_CENTER " 
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CENTER.CENTER_ID = hrms_emp_offc.EMP_CENTER) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = " +appStatus.getApprCode()+ " AND IS_ACTIVE='Y'" 
				+" ORDER BY CENTER_ID ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Branch
	
	/**
	 * Popup window contains list of all departments
	**/
	/**
	 * @return String f9page
	**/
	public String f9Dept()
	{
		try
		{
			String query = " SELECT DISTINCT DEPT_NAME,DEPT_ID FROM  HRMS_DEPT "
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = hrms_emp_offc.EMP_DEPT) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (hrms_emp_offc.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appStatus.getApprCode()+ " AND IS_ACTIVE='Y' "
				+" ORDER BY DEPT_ID ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptName", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Dept
	
	public String f9Desg() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  DISTINCT RANK_NAME,RANK_ID FROM HRMS_RANK "
			+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
			+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
			+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appStatus.getApprCode()+ " AND IS_ACTIVE='Y' "
			+" ORDER BY RANK_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("designation")};

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "desgName", "desgCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
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
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9Employee()throws Exception
	{
		String query =    " SELECT " +
						  "		EMP_TOKEN ," +
						  "		(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) EMPLOYEE_NAME, " +
						  "		EMP_ID " +
						  " FROM " +
						  "		PAS_APPR_APPRAISER_GRP_HDR A  " +
						  "		INNER JOIN PAS_APPR_APPRAISER_GRP_DTL B ON (A.APPR_APPRAISER_GRP_ID = B.APPR_APPRAISER_GRP_ID) " +
						  " 	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =  B.APPR_APPRAISEE_ID)" +
						  " 	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)" +
						  " 	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_CADRE)" +
						  " WHERE " +
						  "		A.APPR_ID = " + appStatus.getApprCode() + " AND EMP_STATUS='S' " +
						  " ORDER BY " +
						  "		EMPLOYEE_NAME ";
		
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = {"empCode","empName", "empId" };
		int[] columnIndex = { 0,1,2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String generateReport()
	{
		AppraisalStatusReportModel model = new AppraisalStatusReportModel();
		model.initiate(context, session);
		model.generateReport(appStatus, response);
		model.terminate();
		return null;
	}

	
	
	
	// Added by Tinshuk Banafar...Begin...
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 * @throws Exception
	 */

	public final String getReport() throws Exception {
		AppraisalStatusReportModel model = new AppraisalStatusReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getStatusReport(appStatus, request, response, reportPath);
		model.terminate();
		return null;
	}
	/**
	 * THIS METHOD IS USED FOR GENERATING E-MAIL REPORT
	 * 
	 * @return String
	 * @throws Exception
	 */

	public final String mailReport() {
		try {
		    final AppraisalStatusReportModel model = new AppraisalStatusReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			model.getStatusReport(appStatus, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	// Added by Tinshuk Banafar...End...
}
