package org.struts.action.D1.reports;

import org.paradyne.bean.D1.reports.ATRMISReport;
import org.paradyne.model.D1.MISReportUtility;
import org.paradyne.model.D1.reports.ATRMISReportModel;
import org.struts.lib.ParaActionSupport;


/**
 *@author Ganesh - Date 16-10-2011.
 *@author aa1381.
 *
 */
public class ATRMISReportAction extends ParaActionSupport {
	
	/**
	 * Set Return Type to input.
	 */
	private static final String RETURN_TYPE_SUCCESS = "success";
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
	 * Object of ATRMISReport.
	 */
	private ATRMISReport misreport;
	
	/**
	 * Looger.
	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() {
		this.misreport = new ATRMISReport();
		this.misreport.setMenuCode(2062);
	}
	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return object.
	 */
	public ATRMISReport getModel() {
		return this.misreport;
	}

/**
 * 
 * @return bean object.
 */
	public ATRMISReport getMisreport() {
		return this.misreport;
	}

	/**
	 * @param misreport -set bean object.
	 */
	public void setMisreport(final ATRMISReport misreport) {
		this.misreport = misreport;
	}

	/**
	 * Purpose - Used to Set Division Details.
	 * @return String.
	 */
	public String f9division() {

		String query = "SELECT DISTINCT NVL(DIV_NAME,' '),DIV_ID FROM  HRMS_DIVISION  ";
		if (this.misreport.getUserProfileDivision() != null && this.misreport.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN (" + this.misreport.getUserProfileDivision() + ")"; 
			query += " ORDER BY  DIV_ID "; 
		}
		final String[] headers = {this.getMessage(ATRMISReportAction.DIVISION_NAME)};
		final String[] headerWidth = {"85"};
		final String[] fieldNames = {"misreport.divName", "misreport.divId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = ATRMISReportAction.FLAG_TRUE;
		final String submitToMethod = "ATRMISReport_chk.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return ATRMISReportAction.F9_PAGE;
	}

	/**
	 * Purpose - Set Department Details.
	 * @return String.
	 */
	public String f9department() {

		final String query = "SELECT DISTINCT NVL(DEPT_NAME,' '), DEPT_ID FROM  HRMS_DEPT  " + " ORDER BY  DEPT_ID ";
		final String[] headers = {this.getMessage(ATRMISReportAction.DEPARTMENT_NAME)};
		final String[] headerWidth = {"90"};
		final String[] fieldNames = {"misreport.deptName", "misreport.deptId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = ATRMISReportAction.FLAG_TRUE;
		final String submitToMethod = "ATRMISReport_chk.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return ATRMISReportAction.F9_PAGE;
	}
	
	/**
	 * Purpose - Used to Set Branch Details. 
	 * @return String.
	 */
	public String f9branch() {

		final String query = " SELECT NVL(CENTER_NAME,' '),CENTER_ID FROM HRMS_CENTER " + " ORDER BY  CENTER_ID ";
		final String[] headers = {this.getMessage(ATRMISReportAction.BRANCH_NAME)};
		final String[] headerWidth = {"70"};
		final String[] fieldNames = {"misreport.branchName", "misreport.branchId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = ATRMISReportAction.FLAG_TRUE;
		final String submitToMethod = "ATRMISReport_chk.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return ATRMISReportAction.F9_PAGE;
	}	
	
	/** 
	 * Purpose - Set Employee Type Details.
	 * @return String.
	 */
	public String f9empType()  {
		final String query = " SELECT DISTINCT TO_CHAR(TYPE_NAME), TYPE_ID FROM  HRMS_EMP_TYPE  " 	+ " ORDER BY  TYPE_ID ";
		final String[] headers = {this.getMessage("type.name")};
		final String[] headerWidth = {"80"};
		final String[] fieldNames = {"misreport.empTypeName", "misreport.empTypeId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = ATRMISReportAction.FLAG_TRUE;
		final String submitToMethod = "ATRMISReport_chk.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return ATRMISReportAction.F9_PAGE;
	}	
	
	/**
	 * Used to set Designation Details.
	 * @return String.
	 */
	public String f9designation() {
		
		final String query = " SELECT DISTINCT TO_CHAR(RANK_NAME),RANK_ID FROM  HRMS_RANK " + " ORDER BY  RANK_ID ";
		final String[] headers = {this.getMessage(ATRMISReportAction.DESIGNATION_NAME)};
		final String[] headerWidth = {"100"};

		final String[] fieldNames = {"misreport.desigName", "misreport.desigId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = ATRMISReportAction.FLAG_TRUE;
		final String submitToMethod = "ATRMISReport_chk.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return ATRMISReportAction.F9_PAGE;
	}
	
	
	/**
	 * Purpose - Used to Set Employee Details.
	 * @return String.
	 */
	public String f9employee() {

		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID " + " FROM HRMS_EMP_OFFC ";
		query +=  " where EMP_STATUS='S' ";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		final String[] headers = {this.getMessage("employee.id"), this.getMessage("employee")};
		final String[] headerWidth = {"25", "75" };
		final String[] fieldNames = {"misreport.empToken", "misreport.empName", "misreport.empId"};
		final int[] columnIndex = {0, 1, 2};
		final String submitFlag = ATRMISReportAction.FLAG_TRUE;
		final String submitToMethod = "ATRMISReport_chk1.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return ATRMISReportAction.F9_PAGE;
	}
	
	/**
	 * Purpose - Used to reset Employee Id, Token & Name field. 
	 * @return String.
	 * @throws Exception
	 */
	public String chk() {
		
		this.misreport.setEmpId("");
		this.misreport.setEmpToken("");
		this.misreport.setEmpName("");
		
		return ATRMISReportAction.RETURN_TYPE_SUCCESS;
	}
	
	/**
	 * Purpose - Used to reset hidden fields.
	 * @return String.
	 */
	public String chk1() {
		
		this.misreport.setDivId("");
		this.misreport.setDivName("");
		this.misreport.setDeptId("");
		this.misreport.setDeptName("");
		this.misreport.setBranchId("");
		this.misreport.setBranchName("");
		this.misreport.setEmpTypeId("");
		this.misreport.setEmpTypeName("");
		this.misreport.setDesigId("");
		this.misreport.setDesigName("");
		this.misreport.setStatus("");
		
		return ATRMISReportAction.RETURN_TYPE_SUCCESS;
	}	

	/**
	 * Purpose  - Save Report Details.
	 * @return String.
	 */
	public String saveReport()  {
		final ATRMISReportModel model = new ATRMISReportModel();
		model.initiate(context, session);
		
		model.deleteSavedReport(this.misreport);
		final boolean result = model.saveReportCriteria(this.misreport);
		if (result) {
			this.addActionMessage(this.getMessage("save"));
		} else {
			this.addActionMessage(this.getMessage("duplicate")); 
		}
		model.terminate();
		return ATRMISReportAction.RETURN_TYPE_SUCCESS;
	}
	
	/**
	 * Purpose - Reset Fields.
	 * @return String.
	 */
	public String reset()  {
		
		this.misreport.setBackFlag("");
		this.misreport.setSavedReport("");
		this.misreport.setDivId("");
		this.misreport.setDivName("");
		this.misreport.setDeptId("");
		this.misreport.setDeptName("");
		this.misreport.setBranchId("");
		this.misreport.setBranchName("");
		this.misreport.setEmpTypeId("");
		this.misreport.setEmpTypeName("");
		this.misreport.setDesigId("");
		this.misreport.setDesigName("");
		this.misreport.setEmpId("");
		this.misreport.setEmpToken("");
		this.misreport.setEmpName("");
		this.misreport.setStatus("");
		
		this.misreport.setEmpNameFlag("");
		this.misreport.setDivFlag("");
		this.misreport.setDeptFlag("");
		this.misreport.setBranchFlag("");
		this.misreport.setDesigFlag("");
		this.misreport.setEmpTypeFlag("");
		this.misreport.setAppliDateFlag("");
		this.misreport.setAttDateFlag("");
		this.misreport.setStatusFlag("");
		this.misreport.setLogInFlag("");
		this.misreport.setLateHrsFlag("");
		this.misreport.setLateHrsDeductFlag("");
		
		this.misreport.setSortBy("");
		this.misreport.setSortByAsc(ATRMISReportAction.CHECKED);
		this.misreport.setSortByDsc("");
		this.misreport.setSortByOrder("");
		this.misreport.setHiddenSortBy("");
		this.misreport.setThenBy1("");
		this.misreport.setThenByOrder1Asc(ATRMISReportAction.CHECKED);
		this.misreport.setThenByOrder1Dsc("");
		this.misreport.setThenByOrder1("");
		this.misreport.setHiddenThenBy1("");
		this.misreport.setThenBy2("");
		this.misreport.setThenByOrder2Asc(ATRMISReportAction.CHECKED);
		this.misreport.setThenByOrder2Dsc("");
		this.misreport.setThenByOrder2("");
		this.misreport.setHiddenThenBy2("");
		
		this.misreport.setColumnOrdering("");
		this.misreport.setHiddenColumns("");
		
		this.misreport.setAppliDateSelect("");
		this.misreport.setAppliFromDate("");
		this.misreport.setAppliToDate("");
		this.misreport.setAttDateSelect("");
		this.misreport.setAttFromDate("");
		this.misreport.setAttToDate("");
		
		this.misreport.setHidReportView(ATRMISReportAction.CHECKED);
		this.misreport.setHidReportRadio("");
		this.misreport.setReportType("");
		
		this.misreport.setSettingName("");
		this.misreport.setReqStatus("");
		
		this.misreport.setTrackingNumber("");
		this.misreport.setEmpFirstName("");
		this.misreport.setActionReason("");
		this.misreport.setFirstNameFlag("");
		this.misreport.setMiddleNameFlag("");
		this.misreport.setLastNameFlag("");
		this.misreport.setSocialSecurityNumberFlag("");
		this.misreport.setCityFlag("");
		this.misreport.setStateFlag("");
		this.misreport.setPhoneNumberFlag("");
		this.misreport.setSexFlag("");
		this.misreport.setMartialStatusFlag("");
		this.misreport.setEducationFlag("");
		this.misreport.setBirthDateFlag("");
		this.misreport.setReferalSourceFlag("");
		this.misreport.setHireDateFlag("");
		this.misreport.setPhysicalWorkLocationFlag("");
		this.misreport.setActionReasonFlag("");
		this.misreport.setGradeFlag("");
		this.misreport.setPayGroupFlag("");
		this.misreport.setAnnualSalaryFlag("");
		this.misreport.setExecutiveEmployeeFlag("");
		this.misreport.setTrackingNumberFlag("");
		
		return ATRMISReportAction.RETURN_TYPE_SUCCESS;
	}
	
	/**
	 * Purpose - Saved Search Report.
	 * @return String.
	 */
	public String searchSavedReport() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		final String query = " SELECT REPORT_CRITERIA, NVL(REPORT_TITLE,' '), REPORT_ID FROM  HRMS_MISREPORT_HDR " + " WHERE REPORT_TYPE = 'ATRMIS'  ORDER BY  REPORT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		final String[] headers = {this.getMessage("report.criteria"), this.getMessage("report.title") };

		final String[] headerWidth = {"40", "60"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		final String[] fieldNames = {"settingName", "misreport.reportTitle", "misreport.reportId"};

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
		final String submitFlag = ATRMISReportAction.FLAG_TRUE;

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		final String submitToMethod = "ATRMISReport_setDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,	submitFlag, submitToMethod);

		return ATRMISReportAction.F9_PAGE;
	}	
	
	/**
	 * Purpose - set details on screen.
	 * @return String.
	 */
	public String setDetails() {
		final ATRMISReportModel model = new ATRMISReportModel();
		this.reset();
		
		model.initiate(context, session);
		model.setDetailOnScreen(this.misreport);
		model.terminate();
		if (ATRMISReportAction.DESCENDING.equals(this.misreport.getSortByOrder().trim())) {
			this.misreport.setSortByDsc(ATRMISReportAction.CHECKED);
		}
		if (ATRMISReportAction.ASCENDING.equals(this.misreport.getSortByOrder().trim())) {
			this.misreport.setSortByAsc(ATRMISReportAction.CHECKED); 
		}
		if (ATRMISReportAction.DESCENDING.equals(this.misreport.getThenByOrder1().trim())) {
			this.misreport.setThenByOrder1Dsc(ATRMISReportAction.CHECKED); 
		}
		if (ATRMISReportAction.ASCENDING.equals(this.misreport.getThenByOrder1().trim())) {
			this.misreport.setThenByOrder1Asc(ATRMISReportAction.CHECKED); 
		}
		if (ATRMISReportAction.DESCENDING.equals(this.misreport.getThenByOrder2().trim())) {
			this.misreport.setThenByOrder2Dsc(ATRMISReportAction.CHECKED); 
		}
		if (ATRMISReportAction.ASCENDING.equals(this.misreport.getThenByOrder2().trim())) {
			this.misreport.setThenByOrder2Asc(ATRMISReportAction.CHECKED);
		}
		return ATRMISReportAction.RETURN_TYPE_SUCCESS;
	}
	
	/**
	 * Display the report on screen.
	 * @return String
	 */
	public String viewOnScreen() {
		this.misreport.setBackFlag("");
		try {
			final ATRMISReportModel model = new ATRMISReportModel();
			model.initiate(context, session);
			//PASSING LABEL NAMES
			final String [] labelNames = {this.getMessage("name"), this.getMessage(ATRMISReportAction.DIVISION_NAME), 
					this.getMessage(ATRMISReportAction.DEPARTMENT_NAME), this.getMessage(ATRMISReportAction.BRANCH_NAME), 
					this.getMessage(ATRMISReportAction.DESIGNATION_NAME), this.getMessage("employee.type"), 
					this.getMessage("application.date"), this.getMessage("application.status"), 
					this.getMessage("tracking.number"), this.getMessage(ATRMISReportAction.MANAGER_NAME),
					this.getMessage("date.atr"), this.getMessage("from.travel.date"), this.getMessage("to.travel.date"), 
					this.getMessage("total.expense") };
			model.callViewScreen(misreport, request, labelNames);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "viewOnScreen";
	}
	
	/**
	 * Called on Back button in view screen.
	 * @return String
	 * returns to the mis filter page
	 */
	public String callBack() {

		this.misreport.setBackFlag(ATRMISReportAction.FLAG_TRUE);
		if (ATRMISReportAction.DESCENDING.equals(this.misreport.getSortByOrder().trim())) {
			this.misreport.setSortByDsc(ATRMISReportAction.CHECKED);
		}
		if (ATRMISReportAction.ASCENDING.equals(this.misreport.getSortByOrder().trim())) {
			this.misreport.setSortByAsc(ATRMISReportAction.CHECKED); 
		}
		if (ATRMISReportAction.DESCENDING.equals(this.misreport.getThenByOrder1().trim())) {
			this.misreport.setThenByOrder1Dsc(ATRMISReportAction.CHECKED); 
		}
		if (ATRMISReportAction.ASCENDING.equals(this.misreport.getThenByOrder1().trim())) {
			this.misreport.setThenByOrder1Asc(ATRMISReportAction.CHECKED);
		}
		if (ATRMISReportAction.DESCENDING.equals(this.misreport.getThenByOrder2().trim())) {
			this.misreport.setThenByOrder2Dsc(ATRMISReportAction.CHECKED); 
		}
		if (ATRMISReportAction.ASCENDING.equals(this.misreport.getThenByOrder2().trim())) {
			this.misreport.setThenByOrder2Asc(ATRMISReportAction.CHECKED);
		}	

		this.misreport.setHiddenSortBy(this.misreport.getSortBy());
		this.misreport.setHiddenThenBy1(this.misreport.getThenBy1());
		this.misreport.setHiddenThenBy2(this.misreport.getThenBy2());
		return ATRMISReportAction.RETURN_TYPE_SUCCESS;
	}	
	
	/**
	 * Purpose - Used to Generate report in PDF/XLS or DOC.
	 * @return String.
	 * @throws Exception -Exception..
	 */
	public String report() throws Exception {
		try {
			final ATRMISReportModel model = new ATRMISReportModel();
			model.initiate(context, session);
			this.misreport.setBackFlag("");
			final String [] labelNames = {this.getMessage("name"), this.getMessage(ATRMISReportAction.DIVISION_NAME), this.getMessage(ATRMISReportAction.DEPARTMENT_NAME),
					this.getMessage(ATRMISReportAction.BRANCH_NAME), this.getMessage(ATRMISReportAction.DESIGNATION_NAME), this.getMessage("employee.type"), this.getMessage("application.date"),
					this.getMessage("application.status"), this.getMessage("tracking.number"), this.getMessage(ATRMISReportAction.MANAGER_NAME),
					this.getMessage("date.atr"), this.getMessage("from.travel.date"), this.getMessage("to.travel.date"), this.getMessage("total.expense")};
			model.getReport(this.misreport, response, labelNames, request);
			final String multiSelectValues = this.misreport.getHiddenColumns();
			String [] splitComma = null;
			if (!"".equals(multiSelectValues)) {
				final String lastComma = multiSelectValues.substring(0, multiSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}
			int count = 0;
			final String queryWithCount = model.selectQuery(this.misreport, labelNames, count, splitComma, request);
			
			final String [] splitQuery = queryWithCount.split("#");
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			final String labels = splitQuery[2];
			
			// QUERY APPENDED WITH CONDITIONS
			query += model.conditionQuery(this.misreport, labelNames); 
			
			final MISReportUtility misModel = new MISReportUtility();
			misModel.initiate(context, session);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	
	/**
	 * Purpose - Set Tracking Nuumber.
	 * @return String.
	 */
	public String f9trackingNumber()  {
		final String query = " SELECT DISTINCT TO_CHAR(TRAVEL_FILE_HEADER_NAME), TRAVEL_ID FROM  HRMS_D1_TRAVEL_REQUEST " + " ORDER BY  TRAVEL_ID ";
		
		/*String query1 = " SELECT TRAVEL_FILE_HEADER_NAME FROM  HRMS_D1_TRAVEL_REQUEST  "
				+ " ORDER BY  TRAVEL_FILE_HEADER_NAME ";*/
		
		final String[] headers = {this.getMessage("tracking.number")};
		final String[] headerWidth = {"95"};
		final String[] fieldNames = {"misreport.trackingNumber", "misreport.trackingId"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = "false";
		final String submitToMethod = "ATRMISReport_chk.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return ATRMISReportAction.F9_PAGE;
	}
	
}
