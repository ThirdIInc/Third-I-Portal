
package org.struts.action.Loan;

import org.paradyne.bean.Loan.LoanMISApplicationReport;
import org.paradyne.bean.Loan.LoanMisReport;
import org.paradyne.model.loan.LoanApprovalModel;
import org.paradyne.model.loan.LoanMISApplicationReportModel;
import org.paradyne.model.loan.LoanMisReportModel;
import org.paradyne.model.payroll.YearlyEDSummaryReportModel;
import org.struts.action.payroll.YearlyEDSummaryReportAction;
import org.struts.lib.ParaActionSupport;

/**
 * @author ganesh
 * @date 17-01-2011
 * LoanMisReportAction class to generate the MIS report
 * as per the selected filters
 */
public class LoanMISApplicationReportAction extends ParaActionSupport {
	/**
     * Used for f9 windows submit Flag false.
     */
	private static final String SUBMIT_FLAG_FALSE = "false";
	/**
     * Log4j logger.
     */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoanMISApplicationReportAction.class);

	LoanMISApplicationReport loanMISApplicationReport;
	
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		loanMISApplicationReport = new  LoanMISApplicationReport();
		loanMISApplicationReport.setMenuCode(1140);
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return loanMISApplicationReport;
	}
	/**
	 * @return loanMISApplicationReport.
	 */
	public LoanMISApplicationReport getLoanMISApplicationReport() {
		return loanMISApplicationReport;
	}

	/**
	 * @param loanMISApplicationReport :setLoanMISApplicationReport.
	 */
	public void setLoanMISApplicationReport(
			LoanMISApplicationReport loanMISApplicationReport) {
		this.loanMISApplicationReport = loanMISApplicationReport;
	}

	
	/**
	 * Method to select the Division.
	 * 
	 * @return String
	 * @throws Exception -IOException for selected data .
	 */
	public String f9div() throws Exception {
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		if (this.loanMISApplicationReport.getUserProfileDivision() != null && this.loanMISApplicationReport.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN (" + this.loanMISApplicationReport.getUserProfileDivision() + ")"; 
		}
		query += " ORDER BY  DIV_ID ";
		final String header = this.getMessage("division");
		String textAreaId = "paraFrm_divName";

		String hiddenFieldId = "paraFrm_divId";
		final String submitFlag = LoanMISApplicationReportAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		return "f9multiSelect";
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
		
		final String submitFlag = LoanMISApplicationReportAction.SUBMIT_FLAG_FALSE;
		final String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		return "f9multiSelect";
	}

	/* method name : f9department 
	 * purpose     : to select an dept name
	 * return type : String
	 * parameter   : none
	 */
	
	public String f9Department() {
		try {
			String query = " SELECT " + "		DISTINCT DEPT_ID," + "		DEPT_NAME "
					+ "	FROM " + " 	HRMS_DEPT  " + " ORDER BY "
					+ "		UPPER (DEPT_NAME) ";

			String header = getMessage("department");
			String textAreaId = "paraFrm_deptName";

			String hiddenFieldId = "paraFrm_deptId";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LoanMISApplicationReportAction.f9Department() method Action : "
							+ e.getMessage());
			return "";
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
			query += getprofilePaybillQuery(loanMISApplicationReport);

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
					.error("Error in LoanMISApplicationReportAction.f9PayBill() method Action : "
							+ e.getMessage());
			return "";
		}
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
					.error("Error in LoanMISApplicationReportAction.f9CostCenter() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/* method name : f9employee 
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	
	public String f9employee()throws Exception
	{
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
			+ " FROM HRMS_EMP_OFFC ";
	query += " WHERE EMP_STATUS='S' ";
	query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = {"empToken","empName" ,"empCode"}; 
		
		int[] columnIndex = { 0,1 ,2};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	public String f9employee_multiselect()throws Exception
	{
		String query = " SELECT DISTINCT  EMP_ID,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME  "
			+ " FROM HRMS_EMP_OFFC ";
	query += " WHERE EMP_STATUS='S' ";
	query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		
	String header = getMessage("employee");
	String textAreaId = "paraFrm_empName";

	String hiddenFieldId = "paraFrm_empCode";

	String submitFlag = "";
	String submitToMethod = "";
	
	setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
			submitFlag, submitToMethod);
	return "f9multiSelect";
	}
	
	/* method name : f9designation 
	 * purpose     : to select an designation name
	 * return type : String
	 * parameter   : none
	 */
	
	
	public String f9designation()throws Exception{
		
		String query = " SELECT RANK_NAME,RANK_ID	FROM HRMS_RANK";	
		String[] headers = { "Designation" };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "designationName", "designationCode" }; 
		int[] columnIndex = { 0, 1 };
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
			loanMISApplicationReport.setBranchCode("");
			loanMISApplicationReport.setBranchName("");
			loanMISApplicationReport.setDivId("");
			loanMISApplicationReport.setDivName("");
			loanMISApplicationReport.setToDate("");
			loanMISApplicationReport.setFromDate("");
			loanMISApplicationReport.setDeptId("");
			loanMISApplicationReport.setDeptName("");
			
			loanMISApplicationReport.setPaybillId("");
			loanMISApplicationReport.setPaybillName("");
			
			loanMISApplicationReport.setEmpId("");
			loanMISApplicationReport.setEmpName("");
			loanMISApplicationReport.setEmpToken("");
			loanMISApplicationReport.setHiddenChechfrmId("");
			loanMISApplicationReport.setStatusChkAll("");
			loanMISApplicationReport.setStatusChkPending("");
			loanMISApplicationReport.setStatusChkProcessed("");
			loanMISApplicationReport.setCostCenterId("");
			loanMISApplicationReport.setCostCenterName("");
			
			return SUCCESS;
		} catch(Exception e) {
			logger.info("Exception in reset in action:" + e);
			return "";
		} // end of try-catch block
	}
	
	public final String mailReport(){
		try {
			LoanMISApplicationReportModel model = new LoanMISApplicationReportModel(); //creating an instance of LoanApprovalModel class
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport(loanMISApplicationReport, response,  request, reportPath);
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
	
	public String getReport() {
		LoanMISApplicationReportModel model = new LoanMISApplicationReportModel(); //creating an instance of LoanApprovalModel class
		model.initiate(context, session);
		String reportPath = "";
		model.getReport(loanMISApplicationReport, response,  request, reportPath);
		model.terminate();
		return null;
	}
}
