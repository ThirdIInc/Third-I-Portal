package org.struts.action.payroll;


import org.paradyne.bean.payroll.YearlyEDSummaryReportBean;
import org.paradyne.model.payroll.YearlyEDSummaryReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * Date : 13 Dec'2011.
 *
 */
public class YearlyEDSummaryReportAction extends ParaActionSupport {
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
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(YearlyEDSummaryReportAction.class);
	/**
	 * object of MonthlyLeaveRegistry.
	 */
	private YearlyEDSummaryReportBean bean;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		this.bean = new YearlyEDSummaryReportBean();
		this.bean.setMenuCode(2265);
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
		final YearlyEDSummaryReportModel model = new YearlyEDSummaryReportModel();
		model.initiate(context, session);
		String checkBoxVar = bean.getHiddenChechfrmId();
		System.out.println("bean.getHiddenChechfrmId()====="
				+ bean.getHiddenChechfrmId());
		String reportPath = "";
		
		model.getReport(bean, response,  request,checkBoxVar, reportPath);
		
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
		final String header =  this.getMessage("branch");
		
		String textAreaId = "paraFrm_branchName";

		String hiddenFieldId = "paraFrm_branchCode";
		
		final String submitFlag = YearlyEDSummaryReportAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		return "f9multiSelect";
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
		final String header = this.getMessage("division");
		String textAreaId = "paraFrm_divName";

		String hiddenFieldId = "paraFrm_divCode";
		final String submitFlag = YearlyEDSummaryReportAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		return "f9multiSelect";
	}
	
	
	/**
     * Method to get bean.
     * 
     * @return bean
     */
	public YearlyEDSummaryReportBean getBean() {
		return this.bean;
	}
	/**
	 * @param bean : set bean
	 */
	public void setBean(final YearlyEDSummaryReportBean bean) {
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
					.error("Error in YearlyEDSummaryReportAction.f9Department() method Action : "
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
					.error("Error in YearlyEDSummaryReportAction.f9PayBill() method Action : "
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
					.error("Error in YearlyEDSummaryReportAction.f9gradeaction() method Action : "
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
		try {
			bean.setBranchCode("");
			bean.setBranchName("");
			bean.setDivCode("");
			bean.setDivName("");
			bean.setToYear("");
			bean.setFromYear("");
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
			bean.setCheckConsolidatedArrears("");
			bean.setCreditCode("");
			bean.setCreditName("");
			bean.setDebitCode("");
			bean.setDebitName("");
			
			bean.setCostCenterId("");
			bean.setCostCenterName("");
			bean.setSubCostCenterId("");
			bean.setSubCostCenterName("");
			return SUCCESS;
		} catch(Exception e) {
			logger.info("Exception in reset in action:" + e);
			return "";
		} // end of try-catch block
	}
	
	public String f9CreditHead() {
		try {
			String query = " SELECT CREDIT_CODE, CREDIT_NAME, CREDIT_ABBR FROM HRMS_CREDIT_HEAD  WHERE CREDIT_PAYFLAG='Y'  ";

//			if (bean.getUserProfileDivision() != null
//					&& bean.getUserProfileDivision().length() > 0)
//				query += " WHERE DIV_ID IN (" + bean.getUserProfileDivision()
//						+ ")";
			query += " ORDER BY CREDIT_CODE ";

			String header = getMessage("earning.head");
			String textAreaId = "paraFrm_creditName";

			String hiddenFieldId = "paraFrm_creditCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in YearlyEDSummaryReportAction.f9CreditHead() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	public String f9DebitHead() {
		try {
			String query = " SELECT DEBIT_CODE, DEBIT_NAME, DEBIT_ABBR FROM HRMS_DEBIT_HEAD  WHERE DEBIT_PAYFLAG='Y'  ";

//			if (bean.getUserProfileDivision() != null
//					&& bean.getUserProfileDivision().length() > 0)
//				query += " WHERE DIV_ID IN (" + bean.getUserProfileDivision()
//						+ ")";
			query += " ORDER BY DEBIT_CODE ";

			String header = getMessage("deduction.head");
			String textAreaId = "paraFrm_debitName";

			String hiddenFieldId = "paraFrm_debitCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in YearlyEDSummaryReportAction.f9DebitHead() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	public final String f9reportType() {
		try {
			String[][] type = new String[][]{{"PDF"},{"Xls"},{"Html"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"report"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "YearlyEDSummaryReport_mailReport.action";
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public final String mailReport(){
		try {
			final YearlyEDSummaryReportModel model = new YearlyEDSummaryReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
			String checkBoxVar = bean.getHiddenChechfrmId();
			System.out.println("bean.getHiddenChechfrmId()====="
					+ bean.getHiddenChechfrmId());
			
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport(bean, response,  request,checkBoxVar, reportPath);
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
	/**
	 * Method to select the Cost Center.
	 * 
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	
	public String f9CostCenter() {
		try {
			String query = " SELECT DISTINCT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID ";

			String header = getMessage("cost.center");
			String textAreaId = "paraFrm_costCenterName";

			String hiddenFieldId = "paraFrm_costCenterId";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in YearlyEDSummaryReportAction.f9CostCenter() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	/**
	 * Method to select the Sub Cost Center.
	 * 
	 * @return String
	 * @throws Exception -IOException for select data .
	 */
	public String f9SubCostCenter() throws Exception {
		
		String query = "SELECT DISTINCT SUB_COST_CENTER_ID, SUB_COST_CENTER_NAME FROM HRMS_SUB_COST_CENTER " +
				" ORDER BY SUB_COST_CENTER_ID";
		
		String header = getMessage("sub.cost.center") ;

		String textAreaId = "paraFrm_subCostCenterName";

		String hiddenFieldId = "paraFrm_subCostCenterId";

		String submitFlag = "false";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		return "f9multiSelect";
	}
}
