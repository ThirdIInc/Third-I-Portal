package org.struts.action.payroll;


import org.paradyne.bean.payroll.PFSubsReportBean;
import org.paradyne.model.payroll.PFSubsReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * Date : 06-12-2011
 *
 */
public class PFSubsReportAction extends ParaActionSupport {
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
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PFSubsReportAction.class);
	/**
	 * object of MonthlyLeaveRegistry.
	 */
	private PFSubsReportBean bean;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		this.bean = new PFSubsReportBean();
		this.bean.setMenuCode(2260);
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
		final PFSubsReportModel model = new PFSubsReportModel();
		model.initiate(context, session);
		//model.getMonthLeaveRegistryReport(this.bean, response);
		//model.report(request,response,bean);
		//logger.info("General Report");
		model.getReport(bean, response,  request);
		
		model.terminate();
		return null;
	}
	
	
	/**
	 * Method to select the Branch.
	 * 
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String f9brn() throws Exception {
		final String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";
		final String[] headers = {this.getMessage("branch.code"), this.getMessage("branch")};
		final String[] headerWidth = {"20", "80" };
		final String[] fieldNames = {"branchCode", "branchName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = PFSubsReportAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return PFSubsReportAction.F9_PAGE;
	}
	
	/**
	 * Method to select the Division.
	 * 
	 * @return String
	 * @throws Exception -IOException for selected data .
	 */
	public String f9div() throws Exception {
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN (" + this.bean.getUserProfileDivision() + ")"; 
		}
		query += " ORDER BY  DIV_ID ";
		final String[] headers = {this.getMessage("division.code"), this.getMessage("division") };
		final String[] headerWidth = {"20", "80" };
		final String[] fieldNames = {"divCode", "divName"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = PFSubsReportAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return PFSubsReportAction.F9_PAGE;
	}
	
	
	/**
     * Method to get bean.
     * 
     * @return bean
     */
	public PFSubsReportBean getBean() {
		return this.bean;
	}
	/**
	 * @param bean : set bean
	 */
	public void setBean(final PFSubsReportBean bean) {
		this.bean = bean;
	}
	
	
	public String f9Department() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptName", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Department:" + e);
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
				" SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " + " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query += getprofilePaybillQuery(bean);

			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"paybillName", "paybillId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9PayBill:" + e);
			return null;
		}
	}
	
	/**
	 * Method to select the Grade of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9gradeaction() throws Exception {

		String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  "
				+ " ORDER BY CADRE_ID ";

		String[] headers = { getMessage("grade.code"), getMessage("grade") };

		String[] headerWidth = { "20", "80" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		String[] fieldNames = { "cadreCode", "cadreName" };
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
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
		try {
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
		
			return SUCCESS;
		} catch(Exception e) {
			logger.info("Exception in reset in action:" + e);
			return "";
		} // end of try-catch block
	}
	
}
