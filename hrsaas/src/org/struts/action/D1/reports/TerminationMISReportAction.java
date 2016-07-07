package org.struts.action.D1.reports;

import org.paradyne.bean.D1.reports.TerminationMISReport;
import org.paradyne.model.D1.MISReportUtility;
import org.paradyne.model.D1.reports.TerminationMISReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class TerminationMISReportAction extends ParaActionSupport {
	
	 /**
     * Set JSP (INPUT).
     */
	public static final String DISPLAY_TYPE = "input"; 
	/**
	 * Set Division.
	 */
	public static final String DISPLAY_DIV = "division"; 
	/**
	 * Set Size 100.
	 */
	public static final String DISPLAY_SIZE = "100"; 
	/**
	 * Set true.
	 */
	public static final String FLAG_TRUE = "true"; 
	/**
	 * Set Checked.
	 */
	public static final String DISPLAY_TYPE_CHECKED = "checked"; 
	/**
	 * Set F9 Page.
	 */
	public static final String F9_PAGE = "f9page"; 
	
	/**
	 * Set Department.
	 */
	public static final String DISPLAY_DEPT = "department"; 
	
	/**
	 * Set Branch.
	 */
	public static final String DISPLAY_BRANCH = "branch"; 
	
	
	/**
	 * Set Manager.
	 */
	public static final String DISPLAY_MANAGER = "manager"; 

	
	/**
	 * Set D.
	 */
	public static final String STATUS_D = "D"; 
	/**
	 * Set A.
	 */
	public static final String STATUS_A = "A";
	
	/**
	 * a  TerminationMISReport object created.
	 */
	private TerminationMISReport terminationMIS;

	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() throws Exception {
		this.terminationMIS = new TerminationMISReport();
		this.terminationMIS.setMenuCode(2060);
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return object.
	 */
	public Object getModel() {

		return this.terminationMIS;
	}

	/**
	 * @return bean object.
	 */
	public TerminationMISReport getTerminationMIS() {
		return this.terminationMIS;
	}

	/**
	 * @param terminationMIS -set bean object.
	 */
	public void setTerminationMIS(final TerminationMISReport terminationMIS) {
		this.terminationMIS = terminationMIS;
	}

	/**(non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * purpose - First method to execute. 
	 * @return String.
	 */
	public String input() {
		final TerminationMISReportModel model = new TerminationMISReportModel();
		model.initiate(context, session);
		model.terminate();
		return TerminationMISReportAction.DISPLAY_TYPE;
	}

	/**
	 * purpose - Back to previous page.
	 * @return String.
	 */
	public String cancel() {
		this.input();
		return "list";
	}

	

	/**
	 * Purpose - set Division.
	 * @return String.
	 */
	public String f9division()  {

		String query = "SELECT DISTINCT NVL(DIV_NAME,' '),DIV_ID FROM  HRMS_DIVISION  ";
		if (this.terminationMIS.getUserProfileDivision() != null 	&& this.terminationMIS.getUserProfileDivision().length() > 0) { 
			query += " WHERE DIV_ID IN (" + this.terminationMIS.getUserProfileDivision() + ")";
			query += " ORDER BY  DIV_ID ";
		}
		final String[] headers = {this.getMessage(TerminationMISReportAction.DISPLAY_DIV)};
		final String[] headerWidth = {TerminationMISReportAction.DISPLAY_SIZE};
		final String[] fieldNames = {"divName", "divId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;
		final String submitToMethod = "TerminationMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod); 

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * purpose - Set Department.
	 * @return String.
	 */
	public String f9department()  {

		final String query = "SELECT DISTINCT NVL(DEPT_NAME,' '), DEPT_ID FROM  HRMS_DEPT  " + " ORDER BY  DEPT_ID ";
		final String[] headers = {this.getMessage(TerminationMISReportAction.DISPLAY_DEPT)};

		final String[] headerWidth = {TerminationMISReportAction.DISPLAY_SIZE};

		final String[] fieldNames = {"deptName", "deptId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;
		final String submitToMethod = "TerminationMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * puropose - Set Branch.
	 * @return String.
	 */
	public String f9branch()  {

		final String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER " + " ORDER BY  CENTER_ID ";
		final String[] headers = {this.getMessage(TerminationMISReportAction.DISPLAY_BRANCH)};

		final String[] headerWidth = {TerminationMISReportAction.DISPLAY_SIZE};

		final String[] fieldNames = {"branchName", "branchId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;
		final String submitToMethod = "TerminationMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * purpose - Set Employee Type.
	 * @return String.
	 */
	public String f9empType() {
		final String query = " SELECT DISTINCT TO_CHAR(TYPE_NAME), TYPE_ID FROM  HRMS_EMP_TYPE  " 	+ " ORDER BY  TYPE_ID ";
		
		final String[] headers = {this.getMessage("type.name")};

		final String[] headerWidth = {TerminationMISReportAction.DISPLAY_SIZE};

		final String[] fieldNames = {"empTypeName", "empTypeId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;
		final String submitToMethod = "TerminationMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,	submitFlag, submitToMethod);

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * purpose - Set Manager Name.
	 * @return String
	 */
	public String f9manager()  {
		String query = " SELECT  EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID,EMP_TOKEN " + " FROM HRMS_EMP_OFFC ";
	        
		if (this.terminationMIS.getUserProfileDivision() != null && this.terminationMIS.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.terminationMIS.getUserProfileDivision() + ")";
		} else  {
			query += " WHERE 1 = 1 ";
		}
		query += " AND EMP_STATUS = 'S' ";
		query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";
        
		final String[] headers = {this.getMessage(TerminationMISReportAction.DISPLAY_MANAGER)};

		final String[] headerWidth = {TerminationMISReportAction.DISPLAY_SIZE};

		final String[] fieldNames = {"managerName", "managerId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;
		final String submitToMethod = "TerminationMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * purpose - Set Tracking Number.
	 * @return String.
	 */
	public String f9trackingNo()  {
		final String query = " SELECT DISTINCT TO_CHAR(TER_TRACKING_NUMBER), TER_ID FROM  HRMS_D1_TERMINATION  " + " ORDER BY  TER_ID ";
		final String[] headers = {this.getMessage("tracking_no")};

		final String[] headerWidth = {TerminationMISReportAction.DISPLAY_SIZE};

		final String[] fieldNames = {"trackingNumber", "trackingId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;
		final String submitToMethod = "TerminationMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * purpose - Set Designation.
	 * @return String.
	 */
	public String f9designation()  {

		final String query = " SELECT DISTINCT TO_CHAR(RANK_NAME),RANK_ID FROM  HRMS_RANK  " 	+ " ORDER BY  RANK_ID ";
		final String[] headers = {this.getMessage("designation")};

		final String[] headerWidth = {TerminationMISReportAction.DISPLAY_SIZE};

		final String[] fieldNames = {"desigName", "desigId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;
		final String submitToMethod = "TerminationMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * purpose - Employee Name & Token.
	 * @return String
	 */
	public String f9employee()  {

		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID " + " FROM HRMS_EMP_OFFC ";
		
		if (this.terminationMIS.getUserProfileDivision() != null && this.terminationMIS.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.terminationMIS.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1 = 1 ";
		}
		//query += " AND EMP_STATUS = 'S' ";
     
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {this.getMessage("employee.id"), this.getMessage("employee")};
		final String[] headerWidth = {"25", "75"};

		final String[] fieldNames = {"empToken", "empName", "empId"};
		final int[] columnIndex = {0, 1, 2};

		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;

		final String submitToMethod = "TerminationMISReport_chk1.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * purpose - to reset Emp Id,token & Emp Name.
	 * @return String.
	 */
	public String chk()  {

		this.terminationMIS.setEmpId("");
		this.terminationMIS.setEmpToken("");
		this.terminationMIS.setEmpName("");

		return TerminationMISReportAction.DISPLAY_TYPE;
	}

	/**
	 * purpose to reset following fields.
	 * @return String.
	 */
	public String chk1() {

		this.terminationMIS.setDivId("");
		this.terminationMIS.setDivName("");
		this.terminationMIS.setDeptId("");
		this.terminationMIS.setDeptName("");
		this.terminationMIS.setBranchId("");
		this.terminationMIS.setBranchName("");
		this.terminationMIS.setEmpTypeId("");
		this.terminationMIS.setEmpTypeName("");
		this.terminationMIS.setDesigId("");
		this.terminationMIS.setDesigName("");
		this.terminationMIS.setManagerId("");
		this.terminationMIS.setManagerName("");
		this.terminationMIS.setTrackingId("");
		this.terminationMIS.setTrackingNumberFlag("");
		this.terminationMIS.setStatus("");

		return TerminationMISReportAction.DISPLAY_TYPE;
	}

	/**
	 * purpose - To save report related fields.
	 * @return String.
	 */
	public String saveReport()  {
		final TerminationMISReportModel model = new TerminationMISReportModel();
		model.initiate(context, session);
		
		model.deleteSavedReport(this.terminationMIS);
		final boolean result = model.saveReportCriteria(this.terminationMIS);
		if (result) {
			this.addActionMessage(this.getMessage("save"));
		} else {
			this.addActionMessage(this.getMessage("duplicate"));
		}
		model.terminate();
		return TerminationMISReportAction.DISPLAY_TYPE;
	}

	/**
	 * purpose - Reset following fields.
	 * @return String.
	 */
	public String reset()  {

		this.terminationMIS.setBackFlag("");
		this.terminationMIS.setSavedReport("");
		this.terminationMIS.setDivId("");
		this.terminationMIS.setDivName("");
		this.terminationMIS.setDeptId("");
		this.terminationMIS.setDeptName("");
		this.terminationMIS.setBranchId("");
		this.terminationMIS.setBranchName("");
		this.terminationMIS.setEmpTypeId("");
		this.terminationMIS.setEmpTypeName("");
		this.terminationMIS.setDesigId("");
		this.terminationMIS.setDesigName("");
		this.terminationMIS.setEmpId("");
		this.terminationMIS.setEmpToken("");
		this.terminationMIS.setEmpName("");
		this.terminationMIS.setStatus("");

		this.terminationMIS.setEmpNameFlag("");
		this.terminationMIS.setDivFlag("");
		this.terminationMIS.setDeptFlag("");
		this.terminationMIS.setBranchFlag("");
		this.terminationMIS.setDesigFlag("");
		this.terminationMIS.setEmpTypeFlag("");
		this.terminationMIS.setAppliDateFlag("");
		this.terminationMIS.setSortBy("");
		this.terminationMIS.setSortByAsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		this.terminationMIS.setSortByDsc("");
		this.terminationMIS.setSortByOrder("");
		this.terminationMIS.setHiddenSortBy("");
		this.terminationMIS.setThenBy1("");
		this.terminationMIS.setThenByOrder1Asc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		this.terminationMIS.setThenByOrder1Dsc("");
		this.terminationMIS.setThenByOrder1("");
		this.terminationMIS.setHiddenThenBy1("");
		this.terminationMIS.setThenBy2("");
		this.terminationMIS.setThenByOrder2Asc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		this.terminationMIS.setThenByOrder2Dsc("");
		this.terminationMIS.setThenByOrder2("");
		this.terminationMIS.setHiddenThenBy2("");

		this.terminationMIS.setColumnOrdering("");
		this.terminationMIS.setHiddenColumns("");

		this.terminationMIS.setAppliDateSelect("");
		this.terminationMIS.setAppliFromDate("");
		this.terminationMIS.setAppliToDate("");
		this.terminationMIS.setTerDateSelect("");
		this.terminationMIS.setTerFromDate("");
		this.terminationMIS.setTerToDate("");

		this.terminationMIS.setHidReportView(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		this.terminationMIS.setHidReportRadio("");
		this.terminationMIS.setReportType("");

		this.terminationMIS.setSettingName("");
		this.terminationMIS.setReqStatus("");

		this.terminationMIS.setEmpId("");
		this.terminationMIS.setTerminationReason("");
		this.terminationMIS.setTerminationType("");
		this.terminationMIS.setTrackingId("");
		this.terminationMIS.setTrackingNumberFlag("");

		this.terminationMIS.setApplStatus("");
		this.terminationMIS.setApprDateSelect("");
		this.terminationMIS.setApprFromDate("");
		this.terminationMIS.setApprToDate("");

		this.terminationMIS.setManagerId("");
		this.terminationMIS.setManagerName("");
		this.terminationMIS.setWorkDateSelect("");
		this.terminationMIS.setWorkFromDate("");
		this.terminationMIS.setWorkToDate("");
		this.terminationMIS.setCity("");
		this.terminationMIS.setLastDay("");
		this.terminationMIS.setTerminationTypeFlag("");
		this.terminationMIS.setTrackingNumberFlag("");
		this.terminationMIS.setTerminationReasonFlag("");
		this.terminationMIS.setApplStatusFlag("");
		this.terminationMIS.setCityFlag("");
		this.terminationMIS.setTerminationDateFlag("");
		this.terminationMIS.setApplicationDateFlag("");
		
		this.terminationMIS.setLastDayFlag("");
		this.terminationMIS.setManagerNameFlag("");

		return TerminationMISReportAction.DISPLAY_TYPE;
	}

	/**
	 * purpose - Used to set Report title & criteria.
	 * @return String.
	 */
	public String searchSavedReport()  {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		final String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '), REPORT_ID FROM  HRMS_MISREPORT_HDR " + " WHERE REPORT_TYPE = 'Termination'  ORDER BY  REPORT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		final String[] headers = {this.getMessage("report.criteria"), this.getMessage("report.title")};

		final String[] headerWidth = {"40", "60"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = {"settingName", "reportTitle", "reportId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		final int[] columnIndex = {0, 1, 2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		final String submitFlag = TerminationMISReportAction.FLAG_TRUE;

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "TerminationMISReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return TerminationMISReportAction.F9_PAGE;
	}

	/**
	 * purpose - set details on screen.
	 * @return String.
	 */
	public String setDetails()  {
		final TerminationMISReportModel model = new TerminationMISReportModel();
		this.reset();
		model.initiate(context, session);
		model.setDetailOnScreen(this.terminationMIS);
		model.terminate();
		if (TerminationMISReportAction.STATUS_D.equals(this.terminationMIS.getSortByOrder().trim())) {
			this.terminationMIS.setSortByDsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_A.equals(this.terminationMIS.getSortByOrder().trim())) {
			this.terminationMIS.setSortByAsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_D.equals(this.terminationMIS.getThenByOrder1().trim())) {
			this.terminationMIS.setThenByOrder1Dsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_A.equals(this.terminationMIS.getThenByOrder1().trim())) {
			this.terminationMIS.setThenByOrder1Asc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_D.equals(this.terminationMIS.getThenByOrder2().trim())) {
			this.terminationMIS.setThenByOrder2Dsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_A.equals(this.terminationMIS.getThenByOrder2().trim())) {
			this.terminationMIS.setThenByOrder2Asc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		return TerminationMISReportAction.DISPLAY_TYPE;
	}

	/**
	 * purpose - Display the report on screen.
	 * @return String
	 */
	public String viewOnScreen() {
		this.terminationMIS.setBackFlag("");

		try {
			final TerminationMISReportModel model = new TerminationMISReportModel();
			model.initiate(context, session);
			
			/**
			 * PASSING LABEL NAMES
			 */ 
			
			final String [] labelNames = {this.getMessage("name"), this.getMessage(TerminationMISReportAction.DISPLAY_DIV), this.getMessage(TerminationMISReportAction.DISPLAY_DEPT), this.getMessage(TerminationMISReportAction.DISPLAY_BRANCH), this.getMessage("employee.type"), this.getMessage("req.tracking.no"), this.getMessage(TerminationMISReportAction.DISPLAY_MANAGER), this.getMessage("city.name"), this.getMessage("termination.date"), this.getMessage("last.day.worked"), this.getMessage("ter.type"), this.getMessage("ter.reason"), this.getMessage("application.date"), this.getMessage("appl.status")};
			
			model.callViewScreen(this.terminationMIS, request, labelNames);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * Called on Back button in view screen.
	 * @return String returns to the MIS filter page
	 */
	public String callBack() {

		this.terminationMIS.setBackFlag(TerminationMISReportAction.FLAG_TRUE);

		if (TerminationMISReportAction.STATUS_D.equals(this.terminationMIS.getSortByOrder().trim())) {
			this.terminationMIS.setSortByDsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_A.equals(this.terminationMIS.getSortByOrder().trim())) {
			this.terminationMIS.setSortByAsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_D.equals(this.terminationMIS.getThenByOrder1().trim())) {
			this.terminationMIS.setThenByOrder1Dsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_A.equals(this.terminationMIS.getThenByOrder1().trim())) {
			this.terminationMIS.setThenByOrder1Asc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_D.equals(this.terminationMIS.getThenByOrder2().trim())) { 
			this.terminationMIS.setThenByOrder2Dsc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}
		if (TerminationMISReportAction.STATUS_A.equals(this.terminationMIS.getThenByOrder2().trim())) { 
			this.terminationMIS.setThenByOrder2Asc(TerminationMISReportAction.DISPLAY_TYPE_CHECKED);
		}

		this.terminationMIS.setHiddenSortBy(this.terminationMIS.getSortBy());
		this.terminationMIS.setHiddenThenBy1(this.terminationMIS.getThenBy1());
		this.terminationMIS.setHiddenThenBy2(this.terminationMIS.getThenBy2());
		return TerminationMISReportAction.DISPLAY_TYPE;
	}

	/**
	 * purpose - Generate report in PDF/XLS or DOC.
	 * @return String.
	 * @throws Exception .
	 */
	public String report() throws Exception {
		try {
			final TerminationMISReportModel model = new TerminationMISReportModel();
			model.initiate(context, session);
			this.terminationMIS.setBackFlag("");
			final String [] labelNames = {this.getMessage("name"), this.getMessage(TerminationMISReportAction.DISPLAY_DIV), this.getMessage(TerminationMISReportAction.DISPLAY_DEPT), this.getMessage(TerminationMISReportAction.DISPLAY_BRANCH), this.getMessage("employee.type"), this.getMessage("req.tracking.no"), this.getMessage(TerminationMISReportAction.DISPLAY_MANAGER), this.getMessage("city.name"), this.getMessage("termination.date"), this.getMessage("last.day.worked"), this.getMessage("ter.type"), this.getMessage("ter.reason"), this.getMessage("application.date"), this.getMessage("appl.status")};
			model.getReport(this.terminationMIS, response, labelNames, request);

			final String multiSelectValues = this.terminationMIS.getHiddenColumns();
			String [] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0, multiSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}
			int count = 0;
			final String queryWithCount = model.selectQuery(this.terminationMIS, labelNames, count, splitComma, request);

			final String [] splitQuery = queryWithCount.split("#");
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];

			// QUERY APPENDED WITH CONDITIONS
			query += model.conditionQuery(this.terminationMIS, labelNames);

			final MISReportUtility misModel = new MISReportUtility();
			misModel.initiate(context, session);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
