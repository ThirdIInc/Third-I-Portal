package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.SkillsMISReportBean;
import org.paradyne.bean.admin.srd.VisaMISReportBean;
import org.paradyne.model.admin.srd.SkillsMISReportModel;
import org.paradyne.model.admin.srd.VisaMISReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author tinshuk.banafar
 *
 */
public class SkillsMISReportAction extends ParaActionSupport{
	
	SkillsMISReportBean skillMISbean;
	
	
	@Override
	public void prepare_local() throws Exception {
		skillMISbean = new 	SkillsMISReportBean();
		skillMISbean.setMenuCode(2288);
		
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return skillMISbean;
	}
	
	public String clear() {
		skillMISbean.setEmpid("");
		skillMISbean.setEmpName("");
		skillMISbean.setStatus("");
		skillMISbean.setToken("");
		skillMISbean.setDeptCode("");
		skillMISbean.setDeptName("");
		skillMISbean.setDesgCode("");
		skillMISbean.setDesgName("");
		skillMISbean.setCenterId("");
		skillMISbean.setCenterName("");
		skillMISbean.setDivCode("");
		skillMISbean.setDivsion("");
		skillMISbean.setSkillName("");
		skillMISbean.setSkillCode("");
		return SUCCESS;
	}
	
	public String f9div() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION  "

		+ " ORDER BY DIV_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Division Code", getMessage("division") };

		String header = getMessage("division");
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divsion" };
		String textAreaId = "paraFrm_divsion";
		String hiddenFieldId = "paraFrm_divCode";
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
		String submitFlag = "";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action divCode
		 */
		String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
		/*
		 * setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
		 * submitFlag, submitToMethod);
		 * 
		 * return "f9page";
		 */
	}// end of f9div

	/**
	 * THIS METHOD IS USED FOR SELECTING DEPARTMENT
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "Department Id", getMessage("department") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptCode", "deptName" };

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
		String submitFlag = "";

		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		String textAreaId = "paraFrm_deptName";
		String hiddenFieldId = "paraFrm_deptCode";
		String header = getMessage("department");

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}// end of f9dept

	/**
	 * THIS METHOD IS USED FOR SELECTING DESIGNATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9desg() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT RANK_ID,RANK_NAME" + "	FROM HRMS_RANK ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { "designation Id", getMessage("designation") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		String[] fieldNames = { "desgCode", "desgName" };
	
		int[] columnIndex = { 0, 1 };
		
		String submitFlag = "";
	
		String submitToMethod = "";
		String textAreaId = "paraFrm_desgName";
		String hiddenFieldId = "paraFrm_desgCode";
		String header = getMessage("designation");

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}// end of f9desg

	/**
	 * THIS METHOD IS USED FOR SELECTING BRANCH
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9center() throws Exception {

		String query = " SELECT " + " 	DISTINCT CENTER_ID ," + "		CENTER_NAME "
				+ " FROM " + " 	HRMS_CENTER " + " ORDER BY "
				+ "		UPPER (CENTER_NAME) ";

		String header = getMessage("branch");
		String textAreaId = "paraFrm_centerName";

		String hiddenFieldId = "paraFrm_centerId";

		String submitFlag = "";
		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";

	}
	
	public String f9skills() throws Exception {

		String query = " SELECT DISTINCT SKILL,SKILL FROM HRMS_EMP_SKILLDTL ";

		/*String[] headers = {"Sr No.",getMessage("skills") };
		String[] headerWidth = { "15", "35"  };
		String[] fieldNames = { "skillCode","skillName"};
		int[] columnIndex = { 0,1};
		
		String submitFlag = "false";
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";*/

		String header = getMessage("country");
		String textAreaId = "paraFrm_skillName";

		String hiddenFieldId = "paraFrm_skillCode";

		String submitFlag = "";
		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";

	}

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		try {
			String query = "SELECT EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME,EMP_ID "
					+ "  FROM HRMS_EMP_OFFC WHERE 1<2 ";

		
			// instance for F9Window
			String[] headers = { "Employee Code", getMessage("employee") };
			String[] headerWidth = { "15", "35" };
			String[] fieldNames = { "empToken", "empName", "empid" };
			int[] columnIndex = { 0, 1, 2 };

			// instance for MultiSelectF9
			/*
			 * String header = getMessage("employee"); String textAreaId =
			 * "paraFrm_empName"; String hiddenFieldId = "paraFrm_empid";
			 */

			String submitFlag = "false";
			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";

			/*
			 * setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
			 * submitFlag, submitToMethod); return "f9multiSelect";
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 * @throws Exception
	 */

	public final String getReport() throws Exception {
		SkillsMISReportModel model = new SkillsMISReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getSkillsMISReport(skillMISbean, request, response, reportPath);
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
		    final SkillsMISReportModel model = new SkillsMISReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			model.getSkillsMISReport(skillMISbean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}


}
