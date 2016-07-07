/**
 * 
 */
package org.struts.action.payroll.salary;

import java.util.Calendar;
import org.paradyne.bean.payroll.salary.LTCProcess;
import org.paradyne.model.payroll.salary.LTCProcessModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author REEBA JOSEPH
 * 22 NOVEMBER 2010
 *
 */
public class LTCProcessAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
		.getLogger(LTCProcessAction.class);
	
	LTCProcess process = null;
	public LTCProcess getProcess() {
		return process;
	}

	public void setProcess(LTCProcess process) {
		this.process = process;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		process = new LTCProcess();
		process.setMenuCode(1113);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return process;
	}
	
	@Override
	public String input() throws Exception {
		LTCProcessModel model = new LTCProcessModel();
		model.initiate(context, session);
		model.displayProcessedList(process, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}//end of method input
	
	/**
	 * Method to add new
	 * @return String
	 * @throws Exception
	 */
	public String addNew() throws Exception {
		reset();
		// Set the current year
		Calendar c = Calendar.getInstance();
		process.setYear(String.valueOf(c.get(Calendar.YEAR)));
		getNavigationPanel(2);
		return SUCCESS;
	}//end of method addNew

	/**
	 * Reset method
	 * @return String
	 */
	public String reset() {
		process.setMonth("");
		// Set the current year
		Calendar c = Calendar.getInstance();
		process.setYear(String.valueOf(c.get(Calendar.YEAR)));
		process.setDivisionId("");
		process.setDivisionName("");
		process.setMonthCode("");
		process.setStatus("");
		getNavigationPanel(2);
		return SUCCESS;
	}//end of method reset
	
	/**
	 * Search saved records
	 * @return f9page
	 * @throws Exception
	 */
	public String search() throws Exception {
		String listQuery = "SELECT TO_CHAR(TO_DATE(LTC_MONTH,'MM'),'MONTH'), LTC_YEAR, NVL(DIV_NAME,' '), NVL(LTC_STATUS,' '), LTC_DIVISION, LTC_MONTH,  "
			+ " LTC_CODE FROM HRMS_LTC  "
			+ " INNER JOIN HRMS_DIVISION ON (DIV_ID = LTC_DIVISION) "
			+ " ORDER BY LTC_CODE ";
		if(process.getUserProfileDivision() != null && process.getUserProfileDivision().length() > 0) {
			listQuery += " AND DIV_ID IN(" + process.getUserProfileDivision() + ") ";
		}//end of if
		listQuery	+=  " ORDER BY LTC_YEAR DESC, LTC_MONTH DESC ";

		String[] headers = {getMessage("month"), getMessage("year"), getMessage("division"),getMessage("status")};

		String[] headerWidth = {"25", "15", "40"};

		String[] fieldNames = {"month", "year", "divisionName", "status", "divisionId", "monthCode"};

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

		String submitFlag = "true";

		String submitToMethod = "LTCCalc_callForEdit.action";

		setF9Window(listQuery, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}// end of method search
	
	public String f9Division() throws Exception {
		String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
		if(process.getUserProfileDivision() != null && process.getUserProfileDivision().length() > 0) {
			query += " WHERE DIV_ID IN(" + process.getUserProfileDivision() + ") ";
		}//end of if
		query += " ORDER BY UPPER(DIV_NAME) ";

		String[] headers = {getMessage("division")};

		String[] headerWidth = {"100"};

		String[] fieldNames = {"divisionName", "divisionId"};

		int[] columnIndex = {0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	} //end of method f9Division
	
	public String process() throws Exception {
		LTCProcessModel model = new LTCProcessModel();
		model.initiate(context, session);
		String returnStr = model.processLTC(process, process
				.getYear(), process.getMonth(), process.getDivisionId());
		model.terminate();
		addActionMessage(returnStr);
		getNavigationPanel(1);
		input();
		process.setEnableAll("Y");
		return INPUT;
	}//end of method process
	
	public String callForEdit() throws Exception {
		getNavigationPanel(3);
		process.setEditFlag(true);
		process.setEnableAll("Y");
		return SUCCESS;
	}//end of method callForEdit
	
	public String f9BranchView() {
		String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";
		String[] headers = { getMessage("branch") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "branchViewName", "branchViewId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}//end of method f9BranchView

	public String f9DepartmentView() {
		String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";
		String[] headers = { getMessage("department") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "departmentViewName", "departmentViewId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}//end of method f9DepartmentView
	
	public String f9EmployeeTypeView() {
		String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";
		String[] headers = { getMessage("employee.type") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "employeeTypeViewName", "employeeTypeViewId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}//end of method f9EmployeeTypeView
	
	
	public String f9PayBillView() {
		String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
		String[] headers = { getMessage("pay.bill"), getMessage("billno") };
		String[] headerWidth = { "80", "20" };
		String[] fieldNames = { "payBillViewName", "payBillViewId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}//end of method f9PayBillView
	
	public String f9employeeView() throws Exception {
		String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, "
				+ " DECODE(EMP_STATUS, 'S','Service','R','Retired','N','Resigned','E','Terminated') ,EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_ID IN (SELECT DISTINCT LTC_EMP_ID FROM HRMS_LTC_DTL "
				+ " WHERE LTC_CODE = "
				+ process.getLtcCode()
				+ ") "
				+ " ORDER BY UPPER(ENAME) ";
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("status") };
		String[] headerWidth = { "20", "80", "20" };
		String[] fieldNames = { "empTokenView", "empViewName", "empStatusView",
				"empViewId" };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}//end of method f9employeeView
	
	public String viewRecords() throws Exception {
		LTCProcessModel model = new LTCProcessModel();
		model.initiate(context, session);
		process.setFlag("true");
		String[] listOfFilters = new String[5];
		listOfFilters[0] = process.getBranchViewId();
		listOfFilters[1] = process.getDepartmentViewId();
		listOfFilters[2] = process.getPayBillViewId();
		listOfFilters[3] = process.getEmployeeTypeViewId();
		listOfFilters[4] = process.getDivisionId();
		String empFilterQuery = model.setEmpFiletrs(listOfFilters);
		if(!process.getEmpViewId().equals("")){
			empFilterQuery +=" AND HRMS_EMP_OFFC.EMP_ID ="+process.getEmpViewId();
		}
		model.getEmployeeList(process,request, empFilterQuery);
		model.terminate();
		logger.info("Status========" + process.getStatus());
		String status = process.getStatus();
		if(status.trim().equals("LTC_START")){
			getNavigationPanel(4);
		}else{
			getNavigationPanel(5);
		}//end of if-else block
		process.setEditFlag(true);
		process.setEnableAll("Y");
		return SUCCESS;
	}//end of method viewRecords
	
	public String lockLTC(){
		LTCProcessModel model = new LTCProcessModel();
		model.initiate(context, session);
		String ltcCode = process.getLtcCode();
		String month = process.getMonthCode();
		String year = process.getYear();
		String divisionID = process.getDivisionId();
		logger.info("Status========" + process.getStatus());
		String status = process.getStatus();
		boolean result = false;
		
		if(status.trim().equals("")){
			addActionMessage("Please save LTC first.");
		}else if((status.equals("LTC_FINAL"))){
			addActionMessage("LTC already locked.");
		}else
			result = model.lockLTC(ltcCode, month, year, divisionID);
		//end of nested if else block
		
		if(result){
			process.setStatus("LTC_FINAL");
		}//end of if

		if(result)
			addActionMessage("LTC locked successfully.");
		else
			addActionMessage("Error while locking LTC.");
		
		model.terminate();
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}//end of try-catch block
		getNavigationPanel(1);
		return INPUT;
	}//end of method lockLTC
	
	public String reProcessLTC() {
		LTCProcessModel model = new LTCProcessModel();
		model.initiate(context, session);

		String ltcCode = process.getLtcCode();
		String month = process.getMonthCode();
		String year = process.getYear();
		String divisionID = process.getDivisionId();
		logger.info("Status========" + process.getStatus());
		String status = process.getStatus();

		if (status.equals("LTC_FINAL")) {
			addActionMessage("LTC already processed for specified month, year and division.");
		}// end of inner if
		else {
			String delLTCDtl = "DELETE FROM HRMS_LTC_DTL WHERE LTC_CODE = "
					+ ltcCode;
			model.getSqlModel().singleExecute(delLTCDtl);

			String delLTCHdr = "DELETE FROM HRMS_LTC WHERE LTC_CODE = "
					+ ltcCode;
			model.getSqlModel().singleExecute(delLTCHdr);

			String returnStr = model.processLTC(process, year, month,
					divisionID);
			addActionMessage(returnStr);

		}// end of else

		model.terminate();
		getNavigationPanel(1);
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}//end of try-catch block
		process.setEnableAll("Y");
		return INPUT;
	}// end of reProcessLTC
	
	public String saveLTC(){
		LTCProcessModel model = new LTCProcessModel();
		model.initiate(context, session);
		String ltcCode=process.getLtcCode();
		try{
			boolean result = model.save(request, ltcCode);
			
			if(result){
				
				String[] listOfFilters = new String[5];
				listOfFilters[0] = process.getBranchViewId();
				listOfFilters[1] = process.getDepartmentViewId();
				listOfFilters[2] = process.getPayBillViewId();
				listOfFilters[3] = process.getEmployeeTypeViewId();
				listOfFilters[4] = process.getDivisionId();
				String filterQuery = model.setEmpFiletrs(listOfFilters);
				if(!process.getEmpViewId().equals("")){
					filterQuery +=" AND HRMS_EMP_OFFC.EMP_ID ="+process.getEmpViewId();
				}
				model.getEmployeeList(process,request, filterQuery);
				process.setStatus("LTC_START");
			}//end of if
			
			if(result)
				addActionMessage("LTC saved successfully.");
			else
				addActionMessage("Error whie saving LTC.");
		}catch(Exception e){
			e.printStackTrace();
		}//end of try-catch block
		model.terminate();
		process.setEditFlag(true);
		process.setFlag("true");
		getNavigationPanel(4);
		process.setEnableAll("Y");
		return SUCCESS;
	}//end of method saveLTC
	
	public String callPaging(){
		LTCProcessModel model = new LTCProcessModel();
		model.initiate(context, session);
		String[] listOfFilters = new String[5];
		listOfFilters[0] = process.getBranchViewId();
		listOfFilters[1] = process.getDepartmentViewId();
		listOfFilters[2] = process.getPayBillViewId();
		listOfFilters[3] = process.getEmployeeTypeViewId();
		listOfFilters[4] = process.getDivisionId();
		String filterQuery = model.setEmpFiletrs(listOfFilters);
		if(!process.getEmpViewId().equals("")){
			filterQuery +=" AND HRMS_EMP_OFFC.EMP_ID ="+process.getEmpViewId();
		}//end of if
		model.getEmployeeList(process,request, filterQuery);
		process.setStatus("LTC_START");
		model.terminate();
		process.setEditFlag(true);
		process.setFlag("true");
		getNavigationPanel(4);
		process.setEnableAll("Y");
		return SUCCESS;
	}//end of method callPaging
	
	public String unLockLTC() {
		try {
			boolean result = false;
			String ltcCode = process.getLtcCode();
			logger.info("Status========" + process.getStatus());
			String status = process.getStatus();
				
			LTCProcessModel model = new LTCProcessModel();
			model.initiate(context, session);
			if(status.trim().equals("LTC_START")){
				addActionMessage("LTC not locked!");
			}else if( !(status.equals("LTC_FINAL")) &&  !(status.equals("LTC_START"))){
				addActionMessage("LTC not processed!");
			}else if(status.equals("LTC_FINAL")){
				result = model.unlockLTC(ltcCode);
				if(result) {
					process.setStatus("LTC_START");
				}else
					addActionMessage("Error in LTC unlock!");
			}//end of nested if-else block
			model.terminate();
		}catch(Exception e) {
			logger.error("Exception in unLockLTC : " + e);
		}
		getNavigationPanel(1);
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		process.setEnableAll("Y");
		return INPUT;
	}//end of method unLockLTC
	
}
