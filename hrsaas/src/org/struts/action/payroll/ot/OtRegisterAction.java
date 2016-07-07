package org.struts.action.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.ot.OtRegister;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.ot.OtRegisterModel;
import org.struts.lib.ParaActionSupport;


/** Created on 29th Feb 2012.
 * @author aa1385
 *
 */
public class OtRegisterAction extends ParaActionSupport {
	/**
     * Log4j logger.
     */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	/** formatter. */
	NumberFormat formatter = new DecimalFormat("#0.00");
	/** bean. */
	private OtRegister bean; 
	/** falseValueConst. * */
	private final String falseValueConst = "false";
	/** f9PageConst. * */
	private final String f9PageConst = "f9page";
	/** divisionConst. * */
	private final String divisionConst = "division";
	/** trueValueConst. * */
	private final String trueValueConst = "true";
	/** hundreadConst. * */
	private final String hundreadConst = "100";
	/** f9MultiSelectConst. * */
	private final String f9MultiSelectConst = "f9multiSelect";
	/** returnSuccessConst. * */
	private final String returnSuccessConst = "success";
	/** templateNameConst. * */
	private final String templateNameConst = "TEMPLATE";
	/** manualConst. * */
	private final String manualConst = "Manual";
	/** systemConst. * */
	private final String systemConst = "System";
	/** Method : prepare_local.
	 * Purpose : This method is used to set menuCode
	 * @throws Exception : Exception
	 * */
	public void prepare_local() throws Exception {
		this.bean = new OtRegister();
		this.bean.setMenuCode(2299);
	}
	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.bean;
	}
	/** Method : getBonusBean.
	 * Purpose : This method is used to get bonusBean
	 * @return BonusAllowance
	 * */
	public OtRegister getbean() {
		return this.bean;
	}
	/** Method : setBonusBean.
	 * Purpose : This method is used to set bonusBean
	 * @param bean : OtRegister
	 * */
	public void setbean(final OtRegister bean) {
		this.bean = bean;
	}
	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is used to set data path
	 * */
	public void prepare_withLoginProfileDetails() {
	}
	/** Method : input.
	 * Purpose : this method is used to get initial list page
	 * @return String
	 */
	public String input() {
		try {
			final OtRegisterModel model = new OtRegisterModel();
			model.initiate(context, session);
			String checkboxvar = bean.getHiddenCheckBoxFlag();
			// To set User Login Token  , Name
			//// model.getManagerDetails(bean.getUserEmpId(), bean);
			 if (!bean.getManagerID().equals("")) {
					 model.getManagerDetails(bean.getManagerID(), bean);
				} else {
					 model.getManagerDetails(bean.getUserEmpId(), bean);
				}
			 //For adding data path BEGINS
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!("".equals(poolName) || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String fileDataPath = this.getText("data_path") + "/upload" + poolName + "/OT/";
			this.bean.setDataPath(fileDataPath);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysDate = formater.format(date);
			model.viewOtRegDtlList(bean, request, sysDate);
			model.terminate();
			//this.getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	/* method name : f9employee.
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	public String f9employee()throws Exception {
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
			+ " FROM HRMS_EMP_OFFC ";
		if (bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' ";
		
		if (bean.getSystemCalculatedOtCheckBox().equals("true")) {
			query += "	 AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerID() + " ";
		} else {
			query += "	 AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerManuallID() + " ";
		} 
		
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = {"50", "50"};
		String[] fieldNames = {"employeeToken", "employeeName" , "employeeID"};
		int[] columnIndex = {0, 1, 2};
		String submitFlag = "true";
		String submitToMethod = "OtRegister_getEmpOtDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	/**
	 * Method to set the emp after searching the record.
	 * @return String
	 * @throws Exception
	 */
	public String getEmpOtDetails() throws Exception {
		final OtRegisterModel model = new OtRegisterModel();
		model.initiate(context, session);
		final boolean result;
		bean.setViewOtRegisterDtlFlag(true);
		model.viewOtRegDtlList(bean, request, bean.getFromDate());
		model.terminate();
	//	getNavigationPanel(3);
		return SUCCESS;
	}
	/* method name : f9employee 
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	public String f9Manager()throws Exception {
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID,EMP_DIV  "
			+ " FROM HRMS_EMP_OFFC ";
		if (bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 AND EMP_ID IN(SELECT DISTINCT EMP_REPORTING_OFFICER FROM HRMS_EMP_OFFC ) ";
		}
		query += " AND EMP_STATUS='S' ";
	    query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = {"Manager Code", "Manager Name"};
		String[] headerWidth = {"50", "50"};
		String[] fieldNames = {"managerToken", "managerName" , "managerID", "divisionID"};
		int[] columnIndex = {0,1, 2, 3};
		String submitFlag = "true";
		String submitToMethod = "OtRegister_getMangerEmpOtDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	/**
	 * Method to set the emp after searching the record.
	 * @return String
	 * @throws Exception
	 */
	public String getMangerEmpOtDetails() throws Exception {
		final OtRegisterModel model = new OtRegisterModel();
		model.initiate(context, session);
		bean.setEmployeeID("");
		bean.setEmployeeToken("");
		bean.setEmployeeName("");
		bean.setEmployeeManuallID("");
		bean.setEmployeeManuallToken("");
		bean.setEmployeeManuallName("");
		final boolean result;
		bean.setViewOtRegisterDtlFlag(true);
		model.viewOtRegDtlList(bean, request, bean.getFromDate());	
		model.terminate();
	//	getNavigationPanel(3);
		return SUCCESS;
	}
	/**Method : reset.
	 * Purpose : This method is used to reset all the form fields 
	 * @return String 
	 */
	public String reset() {
		try {
			final OtRegisterModel model = new OtRegisterModel();
			model.initiate(context, session);
			model.getManagerDetails(bean.getUserEmpId(), bean);
			//bean.setFromDate("");
			//bean.setToDate("");
			//bean.setManagerID("");
			//bean.setManagerToken("");
			//bean.setManagerName("");
			bean.setEmployeeID("");
			bean.setEmployeeToken("");
			bean.setEmployeeName("");
			bean.setShiftID("");
			bean.setShiftName("");
			bean.setFileNameForManuallyCalculatedOtAllowance("");
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysDate = formater.format(date);
			model.viewOtRegDtlList(bean, request, sysDate);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnSuccessConst;
	}
	/**this is used to calculated OT Checked.
	 * @return success
	 * @throws Exception
	 */
	public String calculatedOtChecked() throws Exception {
		final OtRegisterModel model = new OtRegisterModel();
		model.initiate(context, session);
		bean.setViewOtRegisterDtlFlag(false);
		model.terminate();
		//reset();
		//getNavigationPanel(2);
		return "success";
	}
	/**
	 * Method to view Ot Register Dtl. 
	 * @return String
	 * @throws Exception
	 */
	public String viewOtRegisterDtl() throws Exception {
		final OtRegisterModel model = new OtRegisterModel();
		model.initiate(context, session);
		bean.setViewOtRegisterDtlFlag(true);
		model.viewOtRegDtlList(bean, request, bean.getFromDate());					
		model.terminate();
		return "success";
	}
	/**Method : f9Div.
	 *Purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT HRMS_DIVISION.DIV_NAME, HRMS_DIVISION.DIV_ID FROM HRMS_DIVISION ";
			if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
				query += " WHERE HRMS_DIVISION.DIV_ID IN(" + this.bean.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(HRMS_DIVISION.DIV_NAME) ";
			final String[] headers = {this.getMessage(this.divisionConst)};
			final String[] headerWidth = {this.hundreadConst};
			final String[] fieldNames = {"divisionName", "divisionID"};
			final int[] columnIndex = {0, 1};
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9PageConst;
	}
	/**Method : f9Branch.
	 *Purpose : Popup window contains list of all branches
	 * @return String f9page
	 */
	public String f9Branch() {
		try {
			final String query = " SELECT HRMS_CENTER.CENTER_ID, HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER(HRMS_CENTER.CENTER_NAME) ";
			final String textAreaId = "paraFrm_branchName";
			final String hiddenFieldId = "paraFrm_branchCode";
			final String header = this.getMessage("branch");
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9MultiSelectConst;
	}
	/**Method : f9Department.
	 * Popup window contains list of all departments
	 * @return String f9page
	 */
	public String f9Department() {
		try {
			final String query = " SELECT HRMS_DEPT.DEPT_ID, HRMS_DEPT.DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER(HRMS_DEPT.DEPT_NAME) ";
			final String departmentName = "paraFrm_departmentName";
			final String departmentHiddenID = "paraFrm_departmentID";
			final String header = this.getMessage("department");
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, departmentName, departmentHiddenID, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		} 
		return this.f9MultiSelectConst;
	}
	/**Method : f9Paybill.
	 * Popup window contains list of all payBills
	 * @return String f9page
	 */
	public String f9Paybill() {
		try {
			final String query = "SELECT HRMS_PAYBILL.PAYBILL_ID, HRMS_PAYBILL.PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			final String payBillName = "paraFrm_paybillName";
			final String payBillHiddenID = "paraFrm_paybillID";
			final String header = this.getMessage("paybill");
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setMultiSelectF9(query, header, payBillName, payBillHiddenID, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9MultiSelectConst;
	}
	/**Method : f9Costcenter.
	 * Popup window contains list of all Cost Center
	 * @return String f9page
	 */
	public String f9Costcenter()throws Exception{
		try {
			String query = " SELECT HRMS_COST_CENTER.COST_CENTER_ID,HRMS_COST_CENTER.COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID";
			String header = getMessage("costcenter");
			String textAreaId = "paraFrm_costcentername";
			String hiddenFieldId = "paraFrm_costcenterid";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.f9MultiSelectConst;
	}
	/**Method : f9Desg.
	 * Popup window contains list of all Designation
	 * @return String f9page
	 */
	public String f9Desg(){
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";
		String header = getMessage("designation");
		String textAreaId = "paraFrm_desgName";
		String hiddenFieldId = "paraFrm_desgCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return this.f9MultiSelectConst;
	}
	/**
	 * Method to select the Shift of an employee.
	 * @return String
	 * @throws Exception
	 */
	public String f9shiftaction() throws Exception {
		String query = " SELECT DISTINCT TO_CHAR(SHIFT_ID),TO_CHAR(SHIFT_NAME) FROM  HRMS_SHIFT "
				+ " ORDER BY  SHIFT_ID ";
		String[] headers = {"Shift Code", getMessage("shift")};
		String[] headerWidth = {"20", "80"};
		String[] fieldNames = {"shiftID", "shiftName"};
		int[] columnIndex = {0, 1};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	/**Method : downloadManuallyCalculatedOtTemplate.
	 * Purpose : This method is used to download template for manually calculated bonus/allowance.
	 */
	public void downloadManuallyCalculatedOtTemplate() {
		try {
			final OtRegisterModel model = new OtRegisterModel();
			model.initiate(context, session);
			/**Pass Last arguments as "Manual" if "Manually Calculated Bonus/Allowance" is checked
			 *and if you want to "Download Template for Uploading Bonus/Allowance" 
			 */ 
			model.generateTemplate(this.bean, response, this.templateNameConst, this.manualConst);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	/**Method : uploadBonusAllowanceDetails.
	 *Purpose : This function is used to upload the manual bonus allowance of the employees as per the filter
	 * @return String
	 */
	public String uploadBonusAllowanceDetails() {
		try {
			final OtRegisterModel model = new OtRegisterModel();
			model.initiate(context, session);
			final boolean result = model.uploadBonusAllowanceData(response, request, this.bean);
			if (result) {
				this.addActionMessage("OT Details uploaded successfully.");
			} else {
				this.addActionMessage("Unable to upload OT Details.");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		//this.getNavigationPanel(2);
		return this.returnSuccessConst;
	}
	/**Method : viewIndividualRatingsAlreadyProcessedRecords.
	 * Purpose : This method is used to view already processed records for individual ratings
	 * @return : String
	 */
	public String viewIndividualRatingsAlreadyProcessedRecords() {
		try {
			final OtRegisterModel model = new OtRegisterModel();
			model.initiate(context, session);
			model.generateTemplate(this.bean, response, "PROCESSEDRATINGS", this.systemConst);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/* method name : f9employee 
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	public String f9employeeManuall()throws Exception {
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
			+ " FROM HRMS_EMP_OFFC ";
		if (bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' ";
	
		if (bean.getSystemCalculatedOtCheckBox().equals("true")) {
			query += "	 AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerID() + " ";
		} else {
			query += "	 AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = " + bean.getManagerManuallID() + " ";
		} 
		
		
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = {"Employee Code", "Employee Name"};
		String[] headerWidth = {"50", "50"};
		String[] fieldNames = {"employeeManuallToken", "employeeManuallName" , "employeeManuallID"};
		int[] columnIndex = {0, 1, 2};
		String submitFlag = "true";
		String submitToMethod = "OtRegister_getEmpOtDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	/* method name : f9employee 
	 * purpose     : to select an employee name
	 * return type : String
	 * parameter   : none
	 */
	public String f9ManagerManuall()throws Exception {
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
			+ " FROM HRMS_EMP_OFFC ";
		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 AND EMP_ID IN(SELECT DISTINCT EMP_REPORTING_OFFICER FROM HRMS_EMP_OFFC ) ";
		}
		query += " AND EMP_STATUS='S' ";
	    query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = {"Manager Code", "Manager Name"};
		String[] headerWidth = {"50", "50"};
		String[] fieldNames = {"managerManuallToken", "managerManuallName" , "managerManuallID"};
		int[] columnIndex = {0, 1, 2};
		String submitFlag = "true";
		String submitToMethod = "OtRegister_getMangerEmpOtDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	/**
	 * Method to save Ot Details.
	 * @return String
	 * @throws Exception
	 */
	public String saveOtDetails() throws Exception {
		final OtRegisterModel model = new OtRegisterModel();
		model.initiate(context, session);
		final boolean result;
		result = model.saveOtDetailsList(bean, request);
		if (result) {
			this.addActionMessage("Ot Details Save Successfully.");
		////	reset();
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysDate = formater.format(date);
			model.viewOtRegDtlList(bean,request,bean.getFromDate());
		}
		model.terminate();
		return "success";
	}
	/** method name : report.
	 * purpose     : to generate the report
	 * return type : String
	 * parameter   : none
	 */
	public String getReport() {
		final OtRegisterModel model = new OtRegisterModel();
		model.initiate(context, session);
		//model.getMonthLeaveRegistryReport(this.bean, response);
		//model.report(request,response,bean);
		//logger.info("General Report");
		String reportPath = "";
		model.getReport(bean, response,  request, reportPath);
		model.terminate();
		return null;
	}
}
