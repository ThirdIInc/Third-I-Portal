package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.QualificationMisReport;
import org.paradyne.model.admin.srd.QualificationMisReportModel;
import org.paradyne.model.payroll.CreditModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author tinshuk.banafar
 *
 */
public class QualificationMisReportAction extends ParaActionSupport {

/**
 * (non-Javadoc)
 * @see org.struts.lib.ParaActionSupport#prepare_local()
 */
private QualificationMisReport rpt;


	/*(non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		rpt = new QualificationMisReport();
		rpt.setMenuCode(747);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return rpt;
	}

	/**
	 * @return
	 */
	public QualificationMisReport getRpt() {
		return rpt;
	}

	/**
	 * @param rpt : QualificationMisReport Instance
	 */
	public void setRpt(QualificationMisReport rpt) {
		this.rpt = rpt;
	}

	/**
	 * @return String
	 */
	public String report() {
		QualificationMisReportModel model = new QualificationMisReportModel();
		model.initiate(context, session);
		String result = model.getReport(rpt, response);

		model.terminate();
		clear();
		return null;

	}

	/**
	 * @return String
	 */
	public String clear() {
		rpt.setEmpid("");
		rpt.setEmpName("");
		rpt.setStatus("");
		rpt.setToken("");
		rpt.setDeptCode("");
		rpt.setDeptName("");
		rpt.setDesgCode("");
		rpt.setDesgName("");
		rpt.setCenterId("");
		rpt.setCenterName("");
		rpt.setReportType("");
		rpt.setDivCode("");
		rpt.setDivsion("");
		rpt.setQuaCode("");
		rpt.setQuaName("");
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING DIVISION
	 * 
	 * @return String
	 * @throws Exception
	 */
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

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "desgCode", "desgName" };

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
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

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

		/*	System.out.println("DIVISION >>>>>>>" + rpt.getDivCode());

			if (!rpt.getDivCode().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN(" + rpt.getDivCode()
						+ ")";
			}

			if (!rpt.getDeptCode().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DEPT IN(" + rpt.getDeptCode()
						+ ")";
			}

			if (!rpt.getCenterId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
						+ rpt.getCenterId() + ")";
			}

			if (!rpt.getDesgCode().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DESG IN(" + rpt.getDesgCode()
						+ ")";
			}*/

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
	 * THIS METHOD IS USED FOR SELECTING QUALIFICATION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9qua() throws Exception {
		//
		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//

		String query = " SELECT QUA_ID ,QUA_NAME FROM HRMS_QUA ORDER BY QUA_ID ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = {"Qualification Id", getMessage("qualification") };

		// DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = {"40", "60" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES
		//

		String[] fieldNames = {"quaCode", "quaName" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		// NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = {0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *

		String textAreaId = "paraFrm_quaName";
		String hiddenFieldId = "paraFrm_quaCode";
		String header = getMessage("qualification");

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	} // end of f9center

	// Added by Tinshuk Banafar...Begin...
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 * @throws Exception
	 */

	public final String getReport() throws Exception {
		QualificationMisReportModel model = new QualificationMisReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getQualificationReport(rpt, request, response, reportPath);
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
		    final QualificationMisReportModel model = new QualificationMisReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			model.getQualificationReport(rpt, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	// Added by Tinshuk Banafar...End...
}
