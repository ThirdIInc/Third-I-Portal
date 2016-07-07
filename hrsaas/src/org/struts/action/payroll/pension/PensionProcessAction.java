package org.struts.action.payroll.pension;

import org.paradyne.bean.payroll.pension.PensionProcess;
import org.paradyne.model.payroll.pension.PensionProcessModel;
import org.struts.lib.ParaActionSupport;

/**
@author aa0418 Prakash
* Date 30-09-2009
*
*/
public class PensionProcessAction extends ParaActionSupport {

	PensionProcess penProcess;
	public void prepare_local() throws Exception {

		penProcess = new PensionProcess();
		penProcess.setMenuCode(949);
		
	}

	public Object getModel() {
		
		return penProcess;
		
	}

	public PensionProcess getPenProcess() {
		return penProcess;
	}

	public void setPenProcess(PensionProcess penProcess) {
		this.penProcess = penProcess;
	}
	
	@Override
	public String input() {
		PensionProcessModel model = new PensionProcessModel();
		model.initiate(context, session);
		try {
			model.displayList(penProcess, request);
		} catch (Exception e) {
			//logger.error("Error in displayList method");
		}
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}//end of input method
	
	public String addNew() throws Exception {
		reset();
		getNavigationPanel(2);
		return SUCCESS;
	}//end of addNew method
	
	public String callForEdit() throws Exception{
		penProcess.setFilterFlag(true);
		penProcess.setFlag("false");
		getNavigationPanel(3);
		penProcess.setEnableAll("N");
		return SUCCESS;
	}//end of callForEdit method
	
	public String processPension(){
		PensionProcessModel model = new PensionProcessModel();
		model.initiate(context, session);
		
		boolean result = model.tableExist(penProcess.getYear());
		
		String status = model.pensionStatus(penProcess.getMonth(),penProcess.getYear(),penProcess.getDivCode());
		
		if(result){
			if(status.equals("PENS_START") || status.equals("PENS_FINAL")){
				addActionMessage("Pension already processed for specified month year and division.");
			}else{
				//ADDED BY REEBA=== BEGINS
				String ledgerCode="";
				String type="U";
				if(penProcess.getLedgerCode().equals("")){
					
					Object [][] ledgerObj = model.getLedgerCode();
					
					if(ledgerObj != null && ledgerObj.length > 0){
						ledgerCode = String.valueOf(ledgerObj[0][0]);
						penProcess.setLedgerCode(ledgerCode);
						type="I";
					}
				}else
					ledgerCode = penProcess.getLedgerCode();
				
				boolean process = model.processPension(penProcess,request,ledgerCode,type);
				if(process)
					addActionMessage("Pension processed successfully.");
				else
					addActionMessage("Error in processing pension.");
				
				//ADDED BY REEBA=== ENDS
			}
					
		}else
			addActionMessage("Tables for specified year does not exists.");
		
		model.terminate();
		return showFilters();
	}
	
	/**
	 * Method for drop and process pension
	 * @author REEBA_JOSEPH
	 * @return
	 */
	public String reProcessPension(){
		PensionProcessModel model = new PensionProcessModel();
		model.initiate(context, session);
		
		boolean result = model.tableExist(penProcess.getYear());
		if(result){
			String ledgerCode="";
			String type="";
			ledgerCode = penProcess.getLedgerCode();
			
			String delPensionCredit = "DELETE FROM HRMS_PENSION_CREDIT_"+penProcess.getYear()+" WHERE PENS_LEDGER_CODE = "+ledgerCode;
			model.getSqlModel().singleExecute(delPensionCredit);
			
			String delPension = "DELETE FROM HRMS_PENSION_"+penProcess.getYear()+" WHERE PENS_LEDGER_CODE = "+ledgerCode ;
			model.getSqlModel().singleExecute(delPension);
			
			String delPensionLedger = "DELETE FROM HRMS_PENSION_LEDGER WHERE LEDGER_CODE = "+ledgerCode;
			model.getSqlModel().singleExecute(delPensionLedger);
			
			String status = model.pensionStatus(penProcess.getMonth(),penProcess.getYear(),penProcess.getDivCode());
		
		
			if(status.equals("PENS_START") || status.equals("PENS_FINAL")){
				addActionMessage("Pension already processed for specified month year and division.");
			}//end of inner if
			else{
				
				
				Object [][] ledgerObj = model.getLedgerCode();
				
				if(ledgerObj != null && ledgerObj.length > 0){
					ledgerCode = String.valueOf(ledgerObj[0][0]);
					penProcess.setLedgerCode(ledgerCode);
					type="I";
				}//end of if
				
				boolean process = model.processPension(penProcess,request,ledgerCode,type);
				if(process)
					addActionMessage("Pension processed successfully.");
				else
					addActionMessage("Error in processing pension.");
			}//end of else
					
		}else
			addActionMessage("Tables for specified year does not exists.");
		
		model.terminate();
		return showFilters();
	}//end of reProcessPension
	
	
	
	public String callPaging(){
		
		PensionProcessModel model = new PensionProcessModel();
		model.initiate(context, session);
		//UPDATED BY REEBA
		if(penProcess.getLedgerCode().equals("")){
			//model.processPension(penProcess,request);
		}
		else
			getEmployeeList();
		
		model.terminate();
		return SUCCESS;
	}
	public String f9Div()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			
			 
			if(penProcess.getUserProfileDivision() !=null && penProcess.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+penProcess.getUserProfileDivision() +")"; 
				query+= " ORDER BY UPPER(DIV_NAME) ";
		 
			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"penProcess.divName", "penProcess.divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	public String savePension(){
							
		PensionProcessModel model = new PensionProcessModel();
		model.initiate(context, session);
		String ledgerCode="";
		String type="U";
		if(penProcess.getLedgerCode().equals("")){
			
			Object [][] ledgerObj = model.getLedgerCode();
			
			if(ledgerObj != null && ledgerObj.length > 0){
				ledgerCode = String.valueOf(ledgerObj[0][0]);
				penProcess.setLedgerCode(ledgerCode);
				type="I";
			}
		}else
			ledgerCode = penProcess.getLedgerCode();
		
		boolean result = model.save(request,penProcess.getYear(),penProcess.getMonth(),penProcess.getDivCode(),ledgerCode,type);
		
		if(result){
			
			String[] listOfFilters = new String[5];
			listOfFilters[0] = penProcess.getBranchViewId();
			listOfFilters[1] = penProcess.getDepartmentViewId();
			listOfFilters[2] = penProcess.getPayBillViewId();
			listOfFilters[3] = penProcess.getEmployeeTypeViewId();
			listOfFilters[4] = penProcess.getDivCode();
			String filterQuery = model.setEmpFiletrs(listOfFilters);
			if(!penProcess.getEmpViewId().equals("")){
				filterQuery +=" AND HRMS_EMP_OFFC.EMP_ID ="+penProcess.getEmpViewId();
			}
			model.getEmployeeList(penProcess,request, filterQuery);
			penProcess.setStatus("PENS_START");
		}
		
		if(result)
			addActionMessage("Pension saved successfully.");
		else
			addActionMessage("Error whie saving pension.");
		
		
		model.terminate();
		penProcess.setFilterFlag(true);
		penProcess.setFlag("true");
		getNavigationPanel(3);
		penProcess.setEnableAll("Y");
		return SUCCESS;
	}
	public String f9ShowRecord()
	{
		try
		{
			String query = " SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May', " +
							" 6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') PENSION_MONTH, " +
							" LEDGER_YEAR,DIV_NAME, LEDGER_STATUS, LEDGER_CODE,LEDGER_MONTH,LEDGER_DIVISION " +
							" FROM HRMS_PENSION_LEDGER " +
							//" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID =  HRMS_SALARY_LEDGER.LEDGER_EMPTYPE) " +
							//" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL) " +
							//" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  HRMS_SALARY_LEDGER.LEDGER_DEPT) " +
							//" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  HRMS_SALARY_LEDGER.LEDGER_BRN) " +
							" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_PENSION_LEDGER.LEDGER_DIVISION) " +
							" WHERE LEDGER_STATUS IN ('PENS_START','PENS_FINAL')" ;
			
			if(penProcess.getUserProfileDivision() !=null && penProcess.getUserProfileDivision().length()>0)
				query+= " AND DIV_ID IN ("+penProcess.getUserProfileDivision() +")"; 
				query+= " ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC  ";
		  
			String[] headers = {getMessage("pension.month"), getMessage("pension.year"),getMessage("division"),getMessage("pension.status")};

			String[] headerWidth = {"12","12","12","12","12","12","12",};

			String[] fieldNames = {"penProcess.monthView", "penProcess.year","penProcess.divName","penProcess.status","penProcess.ledgerCode","penProcess.month","penProcess.divCode"};

			int[] columnIndex = {0,1,2,3,4,5,6};

			String submitFlag = "true";

			String submitToMethod = "PensionProcess_showFilters.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @author Reeba_Joseph
	 * @return
	 */
	public String showFilters()
	{
		try {
			//ADDED BY REEBA
			penProcess.setFilterFlag(true);
			penProcess.setFlag("false");
			getNavigationPanel(3);
			penProcess.setEnableAll("N");
			return SUCCESS;
		} catch (Exception e) {
			
			return SUCCESS;
		}
	} // end of method
	
	
	public String getEmployeeList(){
		penProcess.setFilterFlag(true);
		PensionProcessModel model = new PensionProcessModel();
		model.initiate(context, session);
		penProcess.setFlag("true");
		String[] listOfFilters = new String[5];
		listOfFilters[0] = penProcess.getBranchViewId();
		listOfFilters[1] = penProcess.getDepartmentViewId();
		listOfFilters[2] = penProcess.getPayBillViewId();
		listOfFilters[3] = penProcess.getEmployeeTypeViewId();
		listOfFilters[4] = penProcess.getDivCode();
		String filterQuery = model.setEmpFiletrs(listOfFilters);
		if(!penProcess.getEmpViewId().equals("")){
			filterQuery +=" AND HRMS_EMP_OFFC.EMP_ID ="+penProcess.getEmpViewId();
		}
		model.getEmployeeList(penProcess,request, filterQuery);
		model.terminate();
		getNavigationPanel(4);
		penProcess.setEnableAll("Y");
		return SUCCESS;
		
	}
	public String reset(){
		
		penProcess.setMonth("");
		penProcess.setYear("");
		penProcess.setDivCode("");
		penProcess.setDivName("");
		penProcess.setStatus("");
		penProcess.setLedgerCode("");
		penProcess.setFlag("false");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String lockPension(){
		
		PensionProcessModel model = new PensionProcessModel();
		model.initiate(context, session);
		String status = model.pensionStatus(penProcess.getMonth(),penProcess.getYear(),penProcess.getDivCode());
		boolean result = false;
		
		if(status.trim().equals("")){
			addActionMessage("Please save the pension first.");
		}else if((status.equals("PENS_FINAL"))){
			addActionMessage("Pension already locked.");
		}else
			result = model.lockPension(penProcess.getLedgerCode(), penProcess.getMonth(), penProcess.getYear(), penProcess.getDivCode());
		
		if(result){
			penProcess.setStatus("PENS_FINAL");
			//model.getEmployeeList(penProcess,request);
		}

		if(result)
			addActionMessage("Pension locked successfully.");
		else
			addActionMessage("Error while locking the salary.");
		
		model.terminate();
		return input();
	}
	
	//=================ADDED BY REEBA==============================
	public String f9BranchView() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchViewName", "branchViewId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			//logger.error("Exception in f9Branch in action:" + e);
			return "";
		}
	}
	
	public String f9DepartmentView() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"departmentViewName", "departmentViewId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			//logger.error("Exception in f9Department in action:" + e);
			return "";
		}
	}
	
	public String f9EmployeeTypeView() {
		try {
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"employeeTypeViewName", "employeeTypeViewId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			//logger.error("Exception in f9EmployeeType in action:" + e);
			return null;
		}
	}
	
	public String f9PayBillView() {
		try {
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " +
					" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			
			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillViewName", "payBillViewId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			//logger.error("Exception in f9PayBill in action:" + e);
			return null;
		}
	}
	
	public String f9employee() throws Exception 
	{
		try
		{
						
			String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, " 
				+ " DECODE(EMP_STATUS, 'S','Service','R','Retired','N','Resigned','E','Terminated') ,EMP_ID" 
				+ " FROM HRMS_EMP_OFFC " 
				/*+ " WHERE EMP_ID IN (SELECT DISTINCT HRMS_PENSION_"+penProcess.getYear()+".PENS_EMP_ID FROM HRMS_PENSION_"+penProcess.getYear()
				+ " WHERE PENS_LEDGER_CODE = "+penProcess.getLedgerCode()+") "*/
				+" WHERE EMP_STATUS ='R'"
				+ " ORDER BY UPPER(ENAME) ";
			
			String[] headers = {getMessage("employee.id"), getMessage("employee") ,getMessage("status")};

			String[] headerWidth = {"20", "80","20"};

			String[] fieldNames = {"empTokenView", "empViewName","empStatusView","empViewId"};

			int[] columnIndex = {0, 1, 2,3};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);			
		}
		catch (Exception e)
		{
			e.printStackTrace();			
		}		
		return "f9page";
	}
	
}
