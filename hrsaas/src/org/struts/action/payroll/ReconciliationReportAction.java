package org.struts.action.payroll;


import org.paradyne.bean.payroll.ReconciliationReportBean;
import org.paradyne.model.payroll.ReconciliationReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * Date : 30Nov'2011.
 *
 */
public class ReconciliationReportAction extends ParaActionSupport {
	/**
     * Used for f9 windows.
     */
	private static final String F9_PAGE = "f9page";
	/**
     * Used for f9 windows submit Flag false.
     */
	private static final String SUBMIT_FLAG_FALSE = "false";
	/**
     * Log4j logger.
     */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReconciliationReportAction.class);
	/**
	 * object of MonthlyLeaveRegistry.
	 */
	private ReconciliationReportBean bean;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		this.bean = new ReconciliationReportBean();
		this.bean.setMenuCode(2255);
	}

	/**
     * Method to get bean.
     * 
     * @return bean
     */
	public Object getModel() {
		return this.bean;
	}
	
	/*
	 * Following function is called when the Payment Statement button is clicked in the jsp page
	 */
	/**
	 * @return null
	 */
	public String getReport() {
		final ReconciliationReportModel model = new ReconciliationReportModel();
		model.initiate(context, session);
		//model.getMonthLeaveRegistryReport(this.bean, response);
		//model.report(request,response,bean);
		//logger.info("General Report");
		String reportPath = "";
		model.getReport(bean, response,  request, reportPath);
		
		model.terminate();
		return null;
	}
	
	
	/**
	 * Method to select the Branch.
	 * 
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String f9Brch() {
		try {
			String query = " SELECT " + " 	DISTINCT CENTER_ID ,"
					+ "		CENTER_NAME " + " FROM " + " 	HRMS_CENTER "
					+ " ORDER BY " + "		UPPER (CENTER_NAME) ";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_branchName";

			String hiddenFieldId = "paraFrm_branchCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in ReconciliationReportAction.f9Brch() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * Method to select the Division.
	 * 
	 * @return String
	 * @throws Exception -IOException for selected data .
	 */
	
	
	public String f9Div() {
		try {
			String query = " SELECT " + " 	DISTINCT DIV_ID, " + "		DIV_NAME "
					+ " FROM " + " 	HRMS_DIVISION ";

			if (this.bean.getUserProfileDivision() != null
					&& this.bean.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN (" + this.bean.getUserProfileDivision()
						+ ")";
			query += " ORDER BY  DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_divName";

			String hiddenFieldId = "paraFrm_divCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in ReconciliationReportAction.f9Div() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	
	/**
     * Method to get bean.
     * 
     * @return bean
     */
	public ReconciliationReportBean getBean() {
		return this.bean;
	}
	/**
	 * @param bean : set bean
	 */
	public void setBean(final ReconciliationReportBean bean) {
		this.bean = bean;
	}
	
	
	public String f9Department() {
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
					.error("Error in ReconciliationReportAction.f9Department() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * Popup window contains list of all employee types
	 * 
	 * @return String f9page
	 */
	public String f9EmployeeType() {
		try {
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"typeName", "typeCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9EmployeeType:" + e);
			return null;
		}
	}

	/**
	 * Popup window contains list of all paybill group
	 * 
	 * @return String f9page
	 */
	
	public String f9PayBill() {
		try {
			String query =
				" SELECT DISTINCT  PAYBILL_ID, PAYBILL_GROUP FROM HRMS_PAYBILL " + " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query += getprofilePaybillQuery(bean);

			String header = getMessage("pay.bill");
			String textAreaId = "paraFrm_paybillName";

			String hiddenFieldId = "paraFrm_paybillId";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in ReconciliationReportAction.f9PayBill() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * Method to select the Grade of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9gradeaction() {
		try {
			String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  "
				+ " ORDER BY CADRE_ID ";
			query += getprofilePaybillQuery(bean);

			String header = getMessage("grade");
			String textAreaId = "paraFrm_cadreName";

			String hiddenFieldId = "paraFrm_cadreCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in ReconciliationReportAction.f9gradeaction() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
	 * For Selecting Employee
	 * 
	 * @return String
	 */
	public String f9Employee() {
				
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S' ";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"empToken", "empName", "empId"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	/**
	 * Resets the fields
	 */
	/**
	 * @return String SUCCESS
	 */
	public String reset() {
	
			bean.setBranchCode("");
			bean.setBranchName("");
			bean.setDivCode("");
			bean.setDivName("");
			bean.setMonth("0");
			bean.setYear("");
			bean.setDeptCode("");
			bean.setDeptName("");
			bean.setCadreCode("");
			bean.setCadreName("");
			bean.setPaybillId("");
			bean.setPaybillName("");
			bean.setTypeCode("");
			bean.setTypeName("");
			bean.setEmpId("");
			bean.setEmpName("");
			bean.setEmpToken("");
			bean.setPrevMonth("");
			bean.setPrevYear("");
			bean.setPreviousMonth("");
		
			return SUCCESS;
		
	}
	
	public final String f9reportType() {
		try {
			String[][] type = new String[][]{{"Xls"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"report"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "ReconciliationReport_mailReport.action";
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public final String mailReport(){
		try {
			final ReconciliationReportModel model = new ReconciliationReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
		
			
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport(bean, response,  request, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String input(){
		//deleteFile();
		return INPUT;
	}
}
