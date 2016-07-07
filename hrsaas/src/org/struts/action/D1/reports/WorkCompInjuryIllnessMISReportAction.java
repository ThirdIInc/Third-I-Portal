package org.struts.action.D1.reports;

import org.paradyne.bean.D1.reports.WorkCompInjuryIllnessMISReport;
import org.paradyne.model.D1.MISReportUtility;
import org.paradyne.model.D1.reports.WorkCompInjuryIllnessMISReportModel;
import org.struts.lib.ParaActionSupport;

public class WorkCompInjuryIllnessMISReportAction extends ParaActionSupport {
	/**
	 * Set Return Type to input.
	 */
	private static final String RETURN_TYPE_INPUT = "input";
	/**
	 * Set Return Type to f9page.
	 */
	private static final String F9_PAGE = "f9page";
	/**
	 * set Return Type to Checked.
	 */
	private static final String CHECKED = "checked";
	/**
	 * Set A.
	 */
	private static final String ASCENDING = "A";
	/**
	 * Set D.
	 */
	private static final String DESCENDING = "D";
	/**
	 * Set Size 100.
	 */
	private static final String DIGIT = "100";
	/**
	 * Set Flag as true.
	 */
	private static final String FLAG_TRUE = "true";
	/**
	 * Set Division.
	 */
	private static final String DIVISION_NAME = "division";
	/**
	 * Set Department.
	 */
	private static final String DEPARTMENT_NAME = "department"; 
	/**
	 * Set Branch.
	 */
	private static final String BRANCH_NAME = "branch";
	/**
	 * Set Designation.
	 */
	private static final String DESIGNATION_NAME = "designation";
	/**
	 * Set Manager.
	 */
	private static final String MANAGER_NAME = "manager";
	

	/**
	 * Object of WorkCompInjuryIllnessMISReport.
	 */
	private WorkCompInjuryIllnessMISReport workmisReport;

	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local()  {
		this.workmisReport = new WorkCompInjuryIllnessMISReport();
		this.workmisReport.setMenuCode(2066);

	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return object.
	 */
	public Object getModel() {

		return this.workmisReport;
	}
	/**
	 * @return workmisReport object.
	 */
	public WorkCompInjuryIllnessMISReport getWorkmisReport() {
		return this.workmisReport;
	}
	/**
	 * @param workmisReport -set bean object.
	 */
	public void setWorkmisReport(final WorkCompInjuryIllnessMISReport workmisReport) {
		this.workmisReport = this.workmisReport;
	}
	/**(non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * purpose - First method to execute. 
	 * @return String.
	 */
	public String input() {
		final WorkCompInjuryIllnessMISReportModel model = new WorkCompInjuryIllnessMISReportModel();
		model.initiate(context, session);
		model.terminate();
		return WorkCompInjuryIllnessMISReportAction.RETURN_TYPE_INPUT;
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
	 * Purpose - Set Division.
	 * @return String.
	 */
	public String f9division()  {

		String query = "SELECT DISTINCT NVL(DIV_NAME,' '),DIV_ID FROM  HRMS_DIVISION  ";
		if (this.workmisReport.getUserProfileDivision() != null && this.workmisReport.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN (" + this.workmisReport.getUserProfileDivision() + ")";
		}
		query += " ORDER BY  DIV_ID ";
		final String[] headers = {this.getMessage(WorkCompInjuryIllnessMISReportAction.DIVISION_NAME)};

		final String[] headerWidth = {WorkCompInjuryIllnessMISReportAction.DIGIT};

		final String[] fieldNames = {"divName", "divId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;
		final String submitToMethod = "WorkCompInjuryMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 * Purpsoe - Used Set Department.
	 * @return String
	 */
	public String f9department() {

		final String query = "SELECT DISTINCT NVL(DEPT_NAME,' '), DEPT_ID FROM  HRMS_DEPT  " 	+ " ORDER BY  DEPT_ID ";
		final String[] headers = {this.getMessage(WorkCompInjuryIllnessMISReportAction.DEPARTMENT_NAME)};

		final String[] headerWidth = {WorkCompInjuryIllnessMISReportAction.DIGIT};

		final String[] fieldNames = {"deptName", "deptId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;
		final String submitToMethod = "WorkCompInjuryMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 * Purpose - Used to set Branch.
	 * @return String
	 */
	public String f9branch() {

		final String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER " + " ORDER BY  CENTER_ID ";
		final String[] headers = {this.getMessage(WorkCompInjuryIllnessMISReportAction.BRANCH_NAME)};

		final String[] headerWidth = {WorkCompInjuryIllnessMISReportAction.DIGIT};

		final String[] fieldNames = {"branchName", "branchId" };
		final int[] columnIndex = {0, 1};
		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;
		final String submitToMethod = "WorkCompInjuryMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 * Search type details.
	 * @return String(f9 page)
	 */
	public String f9empType()  {
		final String query = " SELECT DISTINCT TO_CHAR(TYPE_NAME), TYPE_ID FROM  HRMS_EMP_TYPE " + " ORDER BY  TYPE_ID ";
		final String[] headers = {this.getMessage("type.name")};

		final String[] headerWidth = {WorkCompInjuryIllnessMISReportAction.DIGIT};

		final String[] fieldNames = {"empTypeName", "empTypeId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;
		final String submitToMethod = "WorkCompInjuryMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 *Purpose - Used to Set Manager.
	 * @return String
	 */
	public String f9manager() {
		final String query = " SELECT DISTINCT TO_CHAR(WORKERS_MANG_NAME), WORKERS_ID FROM  HRMS_D1_WRKRS_COMP_INJURY " + " ORDER BY  WORKERS_ID ";
		final String[] headers = {this.getMessage(WorkCompInjuryIllnessMISReportAction.MANAGER_NAME)};

		final String[] headerWidth = {WorkCompInjuryIllnessMISReportAction.DIGIT};

		final String[] fieldNames = {"managerName", "managerId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;
		final String submitToMethod = "WorkCompInjuryMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 * Purpose - Set Tracking Number.
	 * @return String
	 */
	public String f9trackingNo() {
		final String query = " SELECT DISTINCT TO_CHAR(TRACKING_NO), WORKERS_ID FROM  HRMS_D1_WRKRS_COMP_INJURY " + " ORDER BY  WORKERS_ID ";
		final String[] headers = {this.getMessage("tracking_no")};

		final String[] headerWidth = {WorkCompInjuryIllnessMISReportAction.DIGIT};

		final String[] fieldNames = {"trackingNumber", "trackingId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;
		final String submitToMethod = "WorkCompInjuryMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 * Search designation details.
	 * @return String(f9 page)
	 */
	public String f9designation() {

		final String query = " SELECT DISTINCT TO_CHAR(RANK_NAME),RANK_ID FROM  HRMS_RANK  " + " ORDER BY  RANK_ID ";
		final String[] headers = {this.getMessage(WorkCompInjuryIllnessMISReportAction.DESIGNATION_NAME)};

		final String[] headerWidth = {WorkCompInjuryIllnessMISReportAction.DIGIT };

		final String[] fieldNames = {"desigName", "desigId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;
		final String submitToMethod = "WorkCompInjuryMISReport_chk.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 * Purpose - Used to Set Employee Details.
	 * @return String
	 */
	public String f9employee()  {

		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID " + " FROM HRMS_EMP_OFFC ";
		query += " where  EMP_STATUS='S' ";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {this.getMessage("employee.id"), this.getMessage("employee")};
		final String[] headerWidth = {"25", "75"};

		final String[] fieldNames = {"empToken", "empName", "empId"};
		final int[] columnIndex = {0, 1, 2};

		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;

		final String submitToMethod = "WorkCompInjuryMISReport_chk1.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 * @return String.
	 */
	public String chk()  {

		this.workmisReport.setEmpId("");
		this.workmisReport.setEmpToken("");
		this.workmisReport.setEmpName("");

		return WorkCompInjuryIllnessMISReportAction.RETURN_TYPE_INPUT;
	}

	/**
	 * @return String.
	 */
	public String chk1()  {

		this.workmisReport.setDivId("");
		this.workmisReport.setDivName("");
		this.workmisReport.setDeptId("");
		this.workmisReport.setDeptName("");
		this.workmisReport.setBranchId("");
		this.workmisReport.setBranchName("");
		this.workmisReport.setEmpTypeId("");
		this.workmisReport.setEmpTypeName("");
		this.workmisReport.setDesigId("");
		this.workmisReport.setDesigName("");
		this.workmisReport.setManagerId("");
		this.workmisReport.setManagerName("");
		this.workmisReport.setTrackingId("");
		this.workmisReport.setTrackingNumberFlag("");

		this.workmisReport.setEmpstatus("");

		return WorkCompInjuryIllnessMISReportAction.RETURN_TYPE_INPUT;
	}

	/**
	 * Purpose - Save Report.
	 * @return String.
	 * @throws Exception
	 */
	public String saveReport() {
		final WorkCompInjuryIllnessMISReportModel model = new WorkCompInjuryIllnessMISReportModel();
		model.initiate(context, session);
		
		model.deleteSavedReport(this.workmisReport);
		final boolean result = model.saveReportCriteria(this.workmisReport);
		if (result) {
			this.addActionMessage(this.getMessage("save"));
		} else {
			this.addActionMessage(this.getMessage("duplicate"));
		}
		model.terminate();
		return WorkCompInjuryIllnessMISReportAction.RETURN_TYPE_INPUT;
	}

	/**
	 * @return String.
	 */
	public String reset()  {

		this.workmisReport.setBackFlag("");
		this.workmisReport.setSavedReport("");
		this.workmisReport.setDivId("");
		this.workmisReport.setDivName("");
		this.workmisReport.setDeptId("");
		this.workmisReport.setDeptName("");
		this.workmisReport.setBranchId("");
		this.workmisReport.setBranchName("");
		this.workmisReport.setEmpTypeId("");
		this.workmisReport.setEmpTypeName("");
		this.workmisReport.setDesigId("");
		this.workmisReport.setDesigName("");
		this.workmisReport.setEmpId("");
		this.workmisReport.setEmpToken("");
		this.workmisReport.setEmpName("");
		this.workmisReport.setEmpstatus("");

		this.workmisReport.setEmpNameFlag("");
		this.workmisReport.setDivFlag("");
		this.workmisReport.setDeptFlag("");
		this.workmisReport.setBranchFlag("");
		this.workmisReport.setDesigFlag("");
		this.workmisReport.setEmpTypeFlag("");
		this.workmisReport.setApplDateFlag("");

		this.workmisReport.setStatusFlag("");
		this.workmisReport.setSortBy("");
		this.workmisReport.setSortByAsc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		this.workmisReport.setSortByDsc("");
		this.workmisReport.setSortByOrder("");
		this.workmisReport.setHiddenSortBy("");
		this.workmisReport.setThenBy1("");
		this.workmisReport.setThenByOrder1Asc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		this.workmisReport.setThenByOrder1Dsc("");
		this.workmisReport.setThenByOrder1("");
		this.workmisReport.setHiddenThenBy1("");
		this.workmisReport.setThenBy2("");
		this.workmisReport.setThenByOrder2Asc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		this.workmisReport.setThenByOrder2Dsc("");
		this.workmisReport.setThenByOrder2("");
		this.workmisReport.setHiddenThenBy2("");

		this.workmisReport.setColumnOrdering("");
		this.workmisReport.setHiddenColumns("");

		this.workmisReport.setAppliDateSelect("");
		this.workmisReport.setAppliFromDate("");
		this.workmisReport.setAppliToDate("");

		this.workmisReport.setInjuryDateSelect("");
		this.workmisReport.setInjuryFromDate("");
		this.workmisReport.setInjuryToDate("");

		this.workmisReport.setApprovalDateSelect("");
		this.workmisReport.setApprovalFromDate("");
		this.workmisReport.setApprovalToDate("");

		this.workmisReport.setHidReportView(WorkCompInjuryIllnessMISReportAction.CHECKED);
		this.workmisReport.setHidReportRadio("");
		this.workmisReport.setReportType("");

		this.workmisReport.setSettingName("");
		this.workmisReport.setReqStatus("");

		this.workmisReport.setEmpId("");

		this.workmisReport.setTrackingId("");
		this.workmisReport.setTrackingNumberFlag("");

		this.workmisReport.setApplStatus("");

		this.workmisReport.setManagerId("");
		this.workmisReport.setManagerName("");
		
		this.workmisReport.setApplStatus("");
		this.workmisReport.setApplStatusFlag("");
		this.workmisReport.setCityFlag("");
		this.workmisReport.setTrackingNumberFlag("");

		return WorkCompInjuryIllnessMISReportAction.RETURN_TYPE_INPUT;
	}

	/**
	 * Purpose - Used to see saved report.
	 * @return String.
	 */
	public String searchSavedReport() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		final String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '), REPORT_ID FROM  HRMS_MISREPORT_HDR " + " WHERE REPORT_TYPE = 'WorkCompInjury'  ORDER BY  REPORT_ID ";

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
		final String submitFlag = WorkCompInjuryIllnessMISReportAction.FLAG_TRUE;

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "WorkCompInjuryMISReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return WorkCompInjuryIllnessMISReportAction.F9_PAGE;
	}

	/**
	 * @return String.
	 */
	public String setDetails()  {
		final WorkCompInjuryIllnessMISReportModel model = new WorkCompInjuryIllnessMISReportModel();
		this.reset();

		model.initiate(context, session);
		model.setDetailOnScreen(this.workmisReport);
		model.terminate();
		if (WorkCompInjuryIllnessMISReportAction.DESCENDING.equals(this.workmisReport.getSortByOrder().trim())) {
			this.workmisReport.setSortByDsc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		}
		if (WorkCompInjuryIllnessMISReportAction.ASCENDING.equals(this.workmisReport.getSortByOrder().trim())) {
			this.workmisReport.setSortByAsc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		}
		if (WorkCompInjuryIllnessMISReportAction.DESCENDING.equals(this.workmisReport.getThenByOrder1().trim())) {
			this.workmisReport.setThenByOrder1Dsc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		}
		if (WorkCompInjuryIllnessMISReportAction.ASCENDING.equals(this.workmisReport.getThenByOrder1().trim())) {
			this.workmisReport.setThenByOrder1Asc(WorkCompInjuryIllnessMISReportAction.CHECKED); 
		}
		if (WorkCompInjuryIllnessMISReportAction.DESCENDING.equals(this.workmisReport.getThenByOrder2().trim())) {
			this.workmisReport.setThenByOrder2Dsc(WorkCompInjuryIllnessMISReportAction.CHECKED); 
		}
		if (WorkCompInjuryIllnessMISReportAction.ASCENDING.equals(this.workmisReport.getThenByOrder2().trim())) {
			this.workmisReport.setThenByOrder2Asc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		}
		
		return WorkCompInjuryIllnessMISReportAction.RETURN_TYPE_INPUT;
	}

	/**
	 * Display the report on screen.
	 * @return String
	 */
	public String viewOnScreen() {
		this.workmisReport.setBackFlag("");

		try {
			final WorkCompInjuryIllnessMISReportModel model = new WorkCompInjuryIllnessMISReportModel();
			model.initiate(context, session);
			/**
			 *  PASSING LABEL NAMES.
			 */
			final String [] labelNames = {this.getMessage("name"), this.getMessage(WorkCompInjuryIllnessMISReportAction.DIVISION_NAME), this.getMessage(WorkCompInjuryIllnessMISReportAction.DEPARTMENT_NAME), this.getMessage(WorkCompInjuryIllnessMISReportAction.BRANCH_NAME), this.getMessage(WorkCompInjuryIllnessMISReportAction.DESIGNATION_NAME),
					this.getMessage("employee.type"), this.getMessage("req.tracking.no"), 	this.getMessage(WorkCompInjuryIllnessMISReportAction.MANAGER_NAME), this.getMessage("city.name"), this.getMessage("num.dependant"), this.getMessage("time.injury"), this.getMessage("injury.work.date"),
					this.getMessage("working.hours.from"), this.getMessage("working.hours.to"), this.getMessage("injury.date"), this.getMessage("loss.workdays"), this.getMessage("fulldayout"),
					this.getMessage("injuered.return"), this.getMessage("length.disebility"), this.getMessage("application.date"),	this.getMessage("appl.status") , this.getMessage("aaaaa")};
			
			model.callViewScreen(this.workmisReport, request, labelNames);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();	
		}
		return "viewOnScreen";
	}

	/**
	 * Called on Back button in view screen.
	 * @return String returns to the MIS filter page
	 */
	public String callBack() {

		this.workmisReport.setBackFlag(WorkCompInjuryIllnessMISReportAction.FLAG_TRUE);

		if (this.workmisReport.getSortByOrder().trim().equals(WorkCompInjuryIllnessMISReportAction.DESCENDING)) {
			this.workmisReport.setSortByDsc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		}
		if (this.workmisReport.getSortByOrder().trim().equals(WorkCompInjuryIllnessMISReportAction.ASCENDING)) {
			this.workmisReport.setSortByAsc(WorkCompInjuryIllnessMISReportAction.CHECKED); 
		}
		if (this.workmisReport.getThenByOrder1().trim().equals(WorkCompInjuryIllnessMISReportAction.DESCENDING)) {
			this.workmisReport.setThenByOrder1Dsc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		}
		if (this.workmisReport.getThenByOrder1().trim().equals(WorkCompInjuryIllnessMISReportAction.ASCENDING)) {
			this.workmisReport.setThenByOrder1Asc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		}
		if (this.workmisReport.getThenByOrder2().trim().equals(WorkCompInjuryIllnessMISReportAction.DESCENDING)) {
			this.workmisReport.setThenByOrder2Dsc(WorkCompInjuryIllnessMISReportAction.CHECKED); 
		}
		if (this.workmisReport.getThenByOrder2().trim().equals(WorkCompInjuryIllnessMISReportAction.ASCENDING)) {
			this.workmisReport.setThenByOrder2Asc(WorkCompInjuryIllnessMISReportAction.CHECKED);
		}

		this.workmisReport.setHiddenSortBy(this.workmisReport.getSortBy());
		this.workmisReport.setHiddenThenBy1(this.workmisReport.getThenBy1());
		this.workmisReport.setHiddenThenBy2(this.workmisReport.getThenBy2());
		return WorkCompInjuryIllnessMISReportAction.RETURN_TYPE_INPUT;
	}

	/**
	 * Generate report in PDF/XLS or DOC.
	 * @return String
	 * @throws Exception - Exception
	 */
	public String report() throws Exception {
		try {
			final WorkCompInjuryIllnessMISReportModel model = new WorkCompInjuryIllnessMISReportModel();
			model.initiate(context, session);
			this.workmisReport.setBackFlag("");
			final String [] labelNames = {this.getMessage("name"), this.getMessage(WorkCompInjuryIllnessMISReportAction.DIVISION_NAME), this.getMessage(WorkCompInjuryIllnessMISReportAction.DEPARTMENT_NAME),
					this.getMessage(WorkCompInjuryIllnessMISReportAction.BRANCH_NAME), this.getMessage(WorkCompInjuryIllnessMISReportAction.DESIGNATION_NAME), this.getMessage("employee.type"), 
					this.getMessage("req.tracking.no"), this.getMessage(WorkCompInjuryIllnessMISReportAction.MANAGER_NAME), this.getMessage("city.name"),
					this.getMessage("num.dependant"), this.getMessage("time.injury"), this.getMessage("injury.work.date"),
					this.getMessage("working.hours.from"), this.getMessage("working.hours.to"), this.getMessage("injury.date"),
					this.getMessage("loss.workdays"), this.getMessage("fulldayout"), this.getMessage("injuered.return"), this.getMessage("length.disebility"), this.getMessage("application.date"), this.getMessage("appl.status"), this.getMessage("aaaaa")};

			final String multiSelectValues = this.workmisReport.getHiddenColumns();
			String [] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0,	multiSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}
			int count = 0;
			final String queryWithCount = model.selectQuery(this.workmisReport, labelNames, count, splitComma, request);

			
			final String [] splitQuery = queryWithCount.split("#");
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];
			// QUERY APPENDED WITH CONDITIONS
			query += model.conditionQuery(this.workmisReport, labelNames);

			final MISReportUtility misModel = new MISReportUtility();
			misModel.initiate(context, session);
			model.getReport(this.workmisReport, response, labelNames, request);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
