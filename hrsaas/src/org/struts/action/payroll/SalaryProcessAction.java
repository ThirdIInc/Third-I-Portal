package org.struts.action.payroll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.payroll.SalaryProcess;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.SalaryProcessModel;
import org.paradyne.model.payroll.SalaryRegisterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author : Shashikant Doke
 *  Date   : dec 11, 2011
 *
 */

public class SalaryProcessAction extends ParaActionSupport {

	SalaryProcess salProcess;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SalaryProcessAction.class);
	
	public void prepare_local() throws Exception {
		salProcess = new SalaryProcess();
		salProcess.setMenuCode(1043);
	}
	
	public Object getModel() {
		return salProcess;
	}

	public SalaryProcess getSalProcess() {
		return salProcess;
	}

	public void setSalProcess(SalaryProcess salProcess) {
		this.salProcess = salProcess;
	}
	
	public String input() {
		try{
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context, session);
			model.getSalProcessList(salProcess,request);
			model.terminate();
		}catch(Exception e){
			logger.error("Exception in input Action");
		}
		return "input";
	}
	
	public String callPage() throws Exception {
		try {
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context, session);
			model.getSalProcessList(salProcess, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in callPage Action");
		}
		return "input";
	}
	
	public String addNew() throws Exception {
		loadPayrollSettings();
		return SUCCESS;
	}
	
	public String viewEditableSalary() {
		return "editableSalary";
	}
	
	/**
	 * Opens a popup window containing a list of all division
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(salProcess.getUserProfileDivision() != null && salProcess.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + salProcess.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(DIV_ID) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divisionName", "divisionId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Division in action:" + e);
			return "";
		}
	} //end of f9Div
	
	/**
	 * Opens a popup window containing a list of all branches
	 * @return String f9page
	 */
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Branch in action:" + e);
			return "";
		}
	}
	
	
	
	/**
	 * Opens a popup window containing a list of all branches
	 * @return String f9page
	 */
	public String f9editEmployee() {
		try {
			String year=salProcess.getYear();
			String ledgerCOde=salProcess.getLedgerCode();
			String query = "  SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '),NVL(SAL_DAYS,0),HRMS_EMP_OFFC.EMP_ID FROM HRMS_SALARY_"+year+" "	
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID) "
							+" WHERE SAL_LEDGER_CODE="+ledgerCOde+"	ORDER BY EMP_TOKEN ";

			String[] headers = {"Employee Id","Employee Name","Salary Days"};

			String[] headerWidth = {"20","60","20"};

			String[] fieldNames = {"editEmpToken", "editEmpName","editEmpSalaryDays","editEmpCode"};

			int[] columnIndex = {0, 1,2,3};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Branch in action:" + e);
			return "";
		}
	}
	
	
	
	/**
	 * Opens a popup window containing a list of all PREVIOUS ONHOLD EMPLOYEE
	 * @return String f9page
	 */
	public String f9prevOnHold() {
		try {
			String year=salProcess.getYearOnHold();
			String month=salProcess.getMonthOnHold();
			
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			
			
			String query = "  SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '),HRMS_EMP_OFFC.EMP_ID FROM HRMS_SALARY_"+year+" "	
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID) "
							+" WHERE SAL_ONHOLD='Y' AND SAL_MONTH="+month+" AND SAL_YEAR="+year+" AND EMP_RELEASE_ONHOLD_MONTH IS NULL AND EMP_RELEASE_ONHOLD_YEAR IS NULL	";
			if(!salProcess.getDivisionId().equals("")){
				query+=" AND SAL_DIVISION= "+salProcess.getDivisionId();
			}
			if(!salProcess.getBranchId().equals("")){
				query+=" AND SAL_EMP_CENTER= "+salProcess.getBranchId();
			}
			
			if(!salProcess.getPayBillId().equals("")){
				query+=" AND SAL_EMP_PAYBILL= "+salProcess.getPayBillId();
			}
			
			if(!salProcess.getEmployeeTypeId().equals("")){
				query+=" AND SAL_EMP_TYPE= "+salProcess.getEmployeeTypeId();
			}
			
			query+=" ORDER BY EMP_TOKEN ";
			
			String[] headers = {"Employee Id","Employee Name"};

			String[] headerWidth = {"20","80"};

			String[] fieldNames = {"editEmpToken", "editEmpName","editEmpCode"};

			int[] columnIndex = {0, 1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Branch in action:" + e);
			return "";
		}
	}
	
	
	
	
	/**
	 * Opens a popup window containing a list of all employee for adding onhold
	 * @return String f9page
	 */
	public String f9onholdEmployee() {
		try {
			
			String emp="0";
			String[] empOnhild=request.getParameterValues("onholdEmpID");
			if(empOnhild!=null && empOnhild.length>0){
				for (int i = 0; i < empOnhild.length; i++) {
					emp+=","+empOnhild[i];
				}
			}
			String year=salProcess.getYear();
			String ledgerCOde=salProcess.getLedgerCode();
			String query = "  SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '),HRMS_EMP_OFFC.EMP_ID FROM HRMS_SALARY_"+year+" "	
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_"+year+".EMP_ID) "
							+" WHERE SAL_LEDGER_CODE="+ledgerCOde+" AND HRMS_EMP_OFFC.EMP_ID NOT IN("+emp+")	ORDER BY EMP_TOKEN ";

			String[] headers = {"Employee Id","Employee Name"};

			String[] headerWidth = {"20","80"};

			String[] fieldNames = {"editEmpToken", "editEmpName","editEmpCode"};

			int[] columnIndex = {0, 1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Branch in action:" + e);
			return "";
		}
	}
	
	
	/**
	 * Opens a popup window containing a list of all branches
	 * @return String f9page
	 */
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
			logger.error("Exception in f9Branch in action:" + e);
			return "";
		}
	}
	
	/**
	 * Opens a popup window containing a list of all departments
	 * @return String f9page
	 */
	public String f9Department() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"departmentName", "departmentId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Department in action:" + e);
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
			logger.error("Exception in f9Department in action:" + e);
			return "";
		}
	}
	
	public void setOtherIncomeFlags(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		String[] listOfFilters = new String[5];
		listOfFilters[0] = salProcess.getBranchId();
		listOfFilters[1] = salProcess.getDepartmentId();
		listOfFilters[2] = salProcess.getPayBillId();
		listOfFilters[3] = salProcess.getEmployeeTypeId();
		listOfFilters[4] = "";
		//model.setOtherIncomeFlags(salProcess);
		model.terminate();
	}
	
	/**
	 * Opens a popup window containing a list of all employee types
	 * @return String f9page
	 */
	public String f9EmployeeType() {
		try {
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"employeeTypeName", "employeeTypeId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9EmployeeType in action:" + e);
			return null;
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
			logger.error("Exception in f9EmployeeType in action:" + e);
			return null;
		}
	}
	
	/**
	 * Opens a popup window containing a list of all paybill group
	 * @return String f9page
	 */
	public String f9PayBill() {
		try {
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " +
					" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			
			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillName", "payBillId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9PayBill in action:" + e);
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
			logger.error("Exception in f9PayBill in action:" + e);
			return null;
		}
	}
	
	/**
	 * Call when page loads at first time. Set the filters which are defined in Payroll Setting in Configuration module
	 */
	public void loadPayrollSettings() {
		try {
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context, session);
			// Get the filters from back end
			Object[][] salaryFilters = model.getPayrollConfig();
			// Set the filters on a page via been 
			try {
				if(salaryFilters != null && salaryFilters.length > 0) {
					salProcess.setBranchFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][0])));
					salProcess.setDepartmentFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][1])));
					salProcess.setPayBillFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][2])));
					salProcess.setEmployeeTypeFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][3])));
					salProcess.setDivisionFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][4])));
					salProcess.setRecordsPerPage(String.valueOf(salaryFilters[0][5]));
					salProcess.setJoinDaysFlag(String.valueOf(salaryFilters[0][6]));
					salProcess.setRecoveryFlag(String.valueOf(salaryFilters[0][7]));
					salProcess.setProfHandiFLag(String.valueOf(salaryFilters[0][8]));
					salProcess.setIncomeTaxFlag(String.valueOf(salaryFilters[0][9]));
					salProcess.setVpfFlag(String.valueOf(salaryFilters[0][10]));
					salProcess.setCreditRound(String.valueOf(salaryFilters[0][11]));
					salProcess.setTotalCreditRound(String.valueOf(salaryFilters[0][12]));
					salProcess.setTotalDebitRound(String.valueOf(salaryFilters[0][13]));
					salProcess.setNetPayRound(String.valueOf(salaryFilters[0][14]));
					salProcess.setRecoveryDebitCode(String.valueOf(salaryFilters[0][15]));
				}
			} catch (Exception e) {
				logger.error("Exception in prepare_withLoginProfileDetails in action setting payroll configuration: " + e);
			}
			Object [][] lwfObj = model.getLwfConfig();
			try {
				if(lwfObj != null && lwfObj.length > 0) {
					salProcess.setLwfFlag(String.valueOf(lwfObj[0][0]));
					salProcess.setLwfDebitCode(String.valueOf(lwfObj[0][1]));
					salProcess.setLwfCreditCode(String.valueOf(lwfObj[0][2]));
				}
			} catch (Exception e) {
				logger.error("Exception in prepare_withLoginProfileDetails in action setting lwf configuration: " + e);
			}
			// Set the current year
			Calendar c = Calendar.getInstance();
			if(!salProcess.isShowFlag()){
			salProcess.setYear(String.valueOf(c.get(Calendar.YEAR)));
			}
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in prepare_withLoginProfileDetails in action: " + e);
		}
	}
	
	public String processSalary() {
		SalaryProcessModel model = new SalaryProcessModel();
		try {			
			boolean result = false;
			// Set list of filters
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
			
			model.initiate(context, session);
			//check whether table for selected year exists ot not.
			boolean isTableExists = model.isTableExist(salProcess.getYear());
			String divEsicFlag =String.valueOf(model.getSqlModel().getSingleResult("SELECT NVL(DIV_ESI_ZONE,'N') FROM HRMS_DIVISION WHERE DIV_ID="+salProcess.getDivisionId())[0][0]);
			if(isTableExists){
				// get the salary ledger status to check whether salary already processed or attendance processed or locked etc.
				String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);
				if((salStatus.equals("SAL_START") || salStatus.equals("SAL_FINAL"))){
					addActionMessage("Salary already processed!");				
				}else if(salStatus.equals("ATTN_READY")){
					String [] dataString = new String [20];
						
						dataString[0] = salProcess.getMonth();
						dataString[1] = salProcess.getYear();
						dataString[2] = salProcess.getLedgerCode();
						dataString[3] = salProcess.getJoinDaysFlag();
						dataString[4] = salProcess.getCreditRound();
						dataString[5] = path;
						dataString[6] = salProcess.getVpfFlag();
						dataString[7] = salProcess.getLwfFlag();
						dataString[8] = "";
						dataString[9] = salProcess.getProfHandiFLag();
						dataString[10] = salProcess.getLwfCreditCode();
						dataString[11] = salProcess.getLwfDebitCode();
						dataString[12] = salProcess.getIncomeTaxFlag();
						dataString[13] = salProcess.getTotalCreditRound();
						dataString[14] = salProcess.getTotalDebitRound();
						dataString[15] = salProcess.getNetPayRound();
						dataString[16] = "N";				//salProcess.getRecoveryFlag();
						dataString[17] = "0";				//salProcess.getRecoveryDebitCode();
						dataString[18] = divEsicFlag; // Division-wise ESIC flag)
						dataString[19]=salProcess.getCompanyPFFlag();
						Object empObj[][] = model.getAttendanceEmployeeToProcess(salProcess.getYear(),salProcess.getLedgerCode(),"");
						result = model.processSalary(dataString,listOfFilters,empObj,"");
						
						if(result) {
							addActionMessage("Salary processed successfully!");
							salProcess.setShowFlag(true);
							salProcess.setLedgerStatus("SAL_START");
							salProcess.setMonthView(Utility.month(Integer.parseInt(salProcess.getMonth())));
							model.saveSalProcessStatus(salProcess.getLedgerCode());
						}else
							addActionMessage("Error in salary processing!");
						
				}else if(salStatus.equals("ATTN_START") || salStatus.equals("ATTN_UNLOCK")){
					addActionMessage("Attendance has not been locked!");
				}else if(salStatus.equals("") || salStatus.equals("null")){
					addActionMessage("Attendance has not been processed!");
				} 
			}else {
				addActionMessage("Salary can not be processed as tables for mentioned year not exists in databse!");
			}
		
		} catch (Exception e) {
			logger.error("Exception in processSalary in action: " + e);
		}
	//	setOtherIncomeFlags();
		model.showMiscStatistics(salProcess);
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * METHOD TO REPROCESS ALL EMPLOYEE
	 * @return
	 */
	public String rPprocessSalary() {
		SalaryProcessModel model = new SalaryProcessModel();
		try {			
			boolean result = false;
			// Set list of filters
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();		
			String path = "";			
			model.initiate(context, session);
			//check whether table for selected year exists ot not.
			boolean isTableExists = model.isTableExist(salProcess.getYear());
			String divEsicFlag =String.valueOf(model.getSqlModel().getSingleResult("SELECT NVL(DIV_ESI_ZONE,'N') FROM HRMS_DIVISION WHERE DIV_ID="+salProcess.getDivisionId())[0][0]);
			if(isTableExists){
				// get the salary ledger status to check whether salary already processed or attendance processed or locked etc.
				String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);
				if((salStatus.equals("SAL_START") || salStatus.equals("SAL_FINAL"))){
					addActionMessage("Salary already processed!");				
				}else if(salStatus.equals("ATTN_READY")){
					    String [] dataString = new String [20];						
						dataString[0] = salProcess.getMonth();
						dataString[1] = salProcess.getYear();
						dataString[2] = salProcess.getLedgerCode();
						dataString[3] = salProcess.getJoinDaysFlag();
						dataString[4] = salProcess.getCreditRound();
						dataString[5] = path;
						dataString[6] = salProcess.getVpfFlag();
						dataString[7] = salProcess.getLwfFlag();
						dataString[8] = "";
						dataString[9] = salProcess.getProfHandiFLag();
						dataString[10] = salProcess.getLwfCreditCode();
						dataString[11] = salProcess.getLwfDebitCode();
						dataString[12] = salProcess.getIncomeTaxFlag();
						dataString[13] = salProcess.getTotalCreditRound();
						dataString[14] = salProcess.getTotalDebitRound();
						dataString[15] = salProcess.getNetPayRound();
						dataString[16] = "N";				//salProcess.getRecoveryFlag();
						dataString[17] = "0";				//salProcess.getRecoveryDebitCode();
						dataString[18] = divEsicFlag; // Division-wise ESIC flag)	
						dataString[19]=salProcess.getCompanyPFFlag();
						Object empObj[][] = model.getAttendanceEmployeeToProcess(salProcess.getYear(),salProcess.getLedgerCode(),"");
						result = model.processSalary(dataString,listOfFilters,empObj,"");						
						if(result) {
							addActionMessage("Salary Re-processed Successfully!");
							salProcess.setShowFlag(true);
							salProcess.setLedgerStatus("SAL_START");
							salProcess.setMonthView(Utility.month(Integer.parseInt(salProcess.getMonth())));
							model.saveSalProcessStatus(salProcess.getLedgerCode());
						}else
							addActionMessage("Error in salary re-processing!");
						
				}else if(salStatus.equals("ATTN_START") || salStatus.equals("ATTN_UNLOCK")){
					addActionMessage("Attendance has not been locked!");
				}else if(salStatus.equals("") || salStatus.equals("null")){
					addActionMessage("Attendance has not been processed!");
				} 
			}else {
				addActionMessage("Salary can not be processed as tables for mentioned year not exists in databse!");
			}		
		} catch (Exception e) {
			logger.error("Exception in processSalary in action: " + e);
		}	
		model.showMiscStatistics(salProcess);
		model.terminate();
		return SUCCESS;
	}
	
	public String processAll() {
		try {
			// Set list of filters
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context, session);
			String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);			
				model.deleteSalary(salProcess.getLedgerCode(), salProcess.getYear());
				//===================================				
				boolean result = false;					
				String path = "";			
				model.initiate(context, session);
				//check whether table for selected year exists ot not.
				boolean isTableExists = model.isTableExist(salProcess.getYear());
				String divEsicFlag =String.valueOf(model.getSqlModel().getSingleResult("SELECT NVL(DIV_ESI_ZONE,'N') FROM HRMS_DIVISION WHERE DIV_ID="+salProcess.getDivisionId())[0][0]);
				if(isTableExists){
					 if(salStatus.equals("ATTN_READY")){
						    String [] dataString = new String [20];						
							dataString[0] = salProcess.getMonth();
							dataString[1] = salProcess.getYear();
							dataString[2] = salProcess.getLedgerCode();
							dataString[3] = salProcess.getJoinDaysFlag();
							dataString[4] = salProcess.getCreditRound();
							dataString[5] = path;
							dataString[6] = salProcess.getVpfFlag();
							dataString[7] = salProcess.getLwfFlag();
							dataString[8] = "";
							dataString[9] = salProcess.getProfHandiFLag();
							dataString[10] = salProcess.getLwfCreditCode();
							dataString[11] = salProcess.getLwfDebitCode();
							dataString[12] = salProcess.getIncomeTaxFlag();
							dataString[13] = salProcess.getTotalCreditRound();
							dataString[14] = salProcess.getTotalDebitRound();
							dataString[15] = salProcess.getNetPayRound();
							dataString[16] = "N";				//salProcess.getRecoveryFlag();
							dataString[17] = "0";				//salProcess.getRecoveryDebitCode();
							dataString[18] = divEsicFlag; // Division-wise ESIC flag)						
							dataString[19]=salProcess.getCompanyPFFlag();
							Object empObj[][] = model.getAttendanceEmployeeToProcess(salProcess.getYear(),salProcess.getLedgerCode(),"");
							result = model.processSalary(dataString,listOfFilters,empObj,"");	
							if(result) {
								addActionMessage("Salary processed Successfully!");
								salProcess.setShowFlag(true);
								salProcess.setLedgerStatus("SAL_START");
								salProcess.setMonthView(Utility.month(Integer.parseInt(salProcess.getMonth())));
								model.saveSalProcessStatus(salProcess.getLedgerCode());
							}else
								addActionMessage("Error in salary re-processing!");
							
					}
				}else {
					addActionMessage("Salary can not be processed as tables for mentioned year not exists in databse!");
				}		
				
				model.showMiscStatistics(salProcess);		
			model.terminate();
		}catch(Exception e) {
			logger.error("Exception in deleteAndProcessSalary in action: " + e);
		}
		return SUCCESS;
	}
	
	
	
	public String deleteAndProcessSalary() {
		try {
			// Set list of filters
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context, session);
			String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);
			if(salStatus.equals("SAL_FINAL")){
				addActionMessage("Salary is locked.Please unlock and then process!");
			}else{
				model.deleteSalary(salProcess.getLedgerCode(), salProcess.getYear());
				rPprocessSalary();
			}
			model.terminate();
		}catch(Exception e) {
			logger.error("Exception in deleteAndProcessSalary in action: " + e);
		}
		return SUCCESS;
	}
	
	public String lockSalary() {
		try {
			boolean result = false;
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context, session);
			String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);
			if(salStatus.equals("SAL_FINAL")){
				addActionMessage("Salary already locked!");
			}else if( !(salStatus.equals("SAL_FINAL")) &&  !(salStatus.equals("SAL_START"))){
				addActionMessage("Salary not processed!");
			}else if(salStatus.equals("SAL_START")){
				result = model.lockSalary(salProcess.getLedgerCode(),salProcess);
				if(result) {
					addActionMessage("Salary locked successfully!");
					salProcess.setLedgerStatus("SAL_FINAL");
				}else
					addActionMessage("Error in salary lock!");
			}
			model.showMiscStatistics(salProcess);
			model.terminate();
		}catch(Exception e) {
			logger.error("Exception in lockSalary in action: " + e);
		}
		return SUCCESS;
	}
	
	public String unLockSalary() {
		SalaryProcessModel model = new SalaryProcessModel();
		try {
			boolean result = false;
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
				
			
			model.initiate(context, session);
			String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);
			if(salStatus.equals("SAL_START")){
				addActionMessage("Salary not locked!");
			}else if( !(salStatus.equals("SAL_FINAL")) &&  !(salStatus.equals("SAL_START"))){
				addActionMessage("Salary not processed!");
			}else if(salStatus.equals("SAL_FINAL")){
				result = model.unlockSalary(salProcess.getLedgerCode(),salProcess);
				if(result) {
					//addActionMessage("Salary unlocked successfully!");
					salProcess.setLedgerStatus("SAL_START");
				}else
					addActionMessage("Error in salary unlock!");
			}
			
		}catch(Exception e) {
			logger.error("Exception in unLockSalary in action: " + e);
		}
		
		model.showMiscStatistics(salProcess);
		model.terminate();
		return SUCCESS;
	}
	

/*	public void viewSalaryReport() {

		SalaryRegisterModel model = new SalaryRegisterModel();
		model.initiate(context, session);
		model.genReport("Xls", salProcess.getMonth(), salProcess.getYear(),
				"N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N","N",
				salProcess.getDivisionId(), salProcess.getBranchId(), "A",
				salProcess.getDepartmentId(), salProcess.getEmployeeTypeId(),
				"", "N", response, salProcess.getBranchName(), salProcess
						.getDepartmentName(), "", salProcess
						.getEmployeeTypeName(), salProcess.getDivisionName(),
				"N", "N", "N",salProcess
				.getPayBillId(),"N","","","","");
		model.terminate();
	}*/
	
	public void viewMiscSalaryUpload() {
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		model.genMiscSalaryUpload("Xls",salProcess,response);
		model.terminate();
	}
	
	
		
	public String f9action() throws Exception {
		try
		{
			String listQuery = " SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, " 
				+  " LEDGER_YEAR,DIV_NAME,CENTER_NAME,PAYBILL_GROUP, LEDGER_STATUS, TYPE_NAME,  DEPT_NAME,  LEDGER_CODE, "
				+  " TYPE_ID,PAYBILL_ID,DEPT_ID,CENTER_ID,DIV_ID, LEDGER_MONTH, 'N'||''||NVL(LEDGER_PROCESS_TYPE,'N') "
				+  " FROM HRMS_SALARY_LEDGER  "
				+  " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID =  HRMS_SALARY_LEDGER.LEDGER_EMPTYPE) "
				+  " LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL) "
				+  " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  HRMS_SALARY_LEDGER.LEDGER_DEPT) "
				+  " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  HRMS_SALARY_LEDGER.LEDGER_BRN) " 
				+  " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_SALARY_LEDGER.LEDGER_DIVISION) " 
				+  " WHERE LEDGER_STATUS IN ('SAL_START','SAL_FINAL','ATTN_UNLOCK','ATTN_READY') ";
			if(salProcess.getUserProfileDivision() != null && salProcess.getUserProfileDivision().length() > 0) {
				listQuery += " AND DIV_ID IN(" + salProcess.getUserProfileDivision() + ") ";
			}
			listQuery	+=  " ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC ";

			String[] headers = {getMessage("month"), getMessage("year"),getMessage("division"),getMessage("branch"), getMessage("pay.bill"),getMessage("status")};

			String[] headerWidth = {"10", "10", "30", "20", "20", "10"};

			String[] fieldNames = {"listMonthName", "year","divisionName","branchName", "payBillName", "ledgerStatus","employeeTypeName", 
									"departmentName",  "ledgerCode",
									"employeeTypeId","payBillId","departmentId","branchId",
									"divisionId","month","uploadSalaryFlag"};

			int[] columnIndex = {0, 1 ,2, 3, 4 ,5 ,6 ,7,8,9,10,11,12,13,14,15};

			String submitFlag = "true";

			String submitToMethod = "SalaryProcess_callForEdit.action";

			setF9Window(listQuery, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public String callForEdit() throws Exception {
		try {
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context, session);
			
			/**
			 * WHEN USER CAME FROM BANK STATEMENT
			 */
			String linkSource=request.getParameter("linkSourceCode");
			if(linkSource!=null && linkSource.length()>0){
				salProcess.setLedgerCode(linkSource);
				model.setOnloadData(salProcess);
			}
			
			
			salProcess.setEditEmpCode("");
			salProcess.setEditEmpName("");
			salProcess.setEditEmpToken("");
			salProcess.setReProcessEmpCode("");
			
			// Get the filters from back end
			Object[][] salaryFilters = model.getPayrollConfig();
			// Set the filters on a page via been 
			salProcess.setDataFlag(false);
			salProcess.setMonthView(Utility.month(Integer.parseInt(salProcess.getMonth())));
			try {
				if(salaryFilters != null && salaryFilters.length > 0) {
					salProcess.setBranchFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][0])));
					salProcess.setDepartmentFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][1])));
					salProcess.setPayBillFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][2])));
					salProcess.setEmployeeTypeFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][3])));
					salProcess.setDivisionFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][4])));
					salProcess.setRecordsPerPage(String.valueOf(salaryFilters[0][5]));
					salProcess.setJoinDaysFlag(String.valueOf(salaryFilters[0][6]));
					salProcess.setRecoveryFlag(String.valueOf(salaryFilters[0][7]));
					salProcess.setProfHandiFLag(String.valueOf(salaryFilters[0][8]));
					salProcess.setIncomeTaxFlag(String.valueOf(salaryFilters[0][9]));
					salProcess.setVpfFlag(String.valueOf(salaryFilters[0][10]));
					salProcess.setCreditRound(String.valueOf(salaryFilters[0][11]));
					salProcess.setTotalCreditRound(String.valueOf(salaryFilters[0][12]));
					salProcess.setTotalDebitRound(String.valueOf(salaryFilters[0][13]));
					salProcess.setNetPayRound(String.valueOf(salaryFilters[0][14]));
					salProcess.setCompanyPFFlag(String.valueOf(salaryFilters[0][16]));
				}
			} catch (Exception e) {
				logger.error("Exception in callfor edit in action setting payroll configuration: " + e);
			}
			Object [][] lwfObj = model.getLwfConfig();
			try {
				if(lwfObj != null && lwfObj.length > 0) {
					salProcess.setLwfFlag(String.valueOf(lwfObj[0][0]));
					salProcess.setLwfDebitCode(String.valueOf(lwfObj[0][1]));
					salProcess.setLwfCreditCode(String.valueOf(lwfObj[0][2]));
				}
			} catch (Exception e) {
				logger.error("Exception in callfor edit in action setting lwf configuration: " + e);
			}
			salProcess.setShowFlag(true);
			model.showMiscStatistics(salProcess);
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//setOtherIncomeFlags();
		return SUCCESS;
	}
	
	/**
	 * METHOD TO GET EMPLOYEE EDITABLE MODE
	 * @return
	 */
	public String showEmpRecord(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		model.showEmpRecord(salProcess);
		model.terminate();
		return "showEmpRecord";
	}
	
	/**
	 * METHOD CALL ON MANAGE CURRENT ONHOLD 
	 */
	public String manageCurrentOnHold(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		model.manageCurrentOnHold(salProcess);
		model.terminate();
		return "manageCurrentOnHold";
	}
	
	
	
	/**
	 * METHOD CALL ON MANAGE CURRENT ONHOLD 
	 */
	public String viewManagePrevious(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		model.managePreviousOnHold(salProcess,salProcess.getMonth(),salProcess.getYear());
		model.terminate();
		return "managePreviousOnHold";
	}
	/**
	 * METHOD CALL ON MANAGE CURRENT ONHOLD 
	 */
	public String viewOnHoldEmployee(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		String mm="";
		String yy=salProcess.getYear();
		if(!salProcess.getMonthOnHold().equals("0")){
			mm=salProcess.getMonthOnHold();
		}
		if(!salProcess.getYearOnHold().equals("")){
			yy=salProcess.getYearOnHold();
		}
		model.managePreviousOnHold(salProcess,mm,yy);
		model.terminate();
		return "managePreviousOnHold";
	}
	
	/**
	 * METHOD CALL ON MANAGE CURRENT ONHOLD 
	 */
	public String removeOnHoldEmp(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		boolean result=model.removeOnHoldEmp(salProcess,request);
		if(result){
			addActionMessage("Record removed onhold successfully");
		}
		model.showMiscStatistics(salProcess);
		model.manageCurrentOnHold(salProcess);
		model.terminate();
		return "manageCurrentOnHold";
	}
	
	//
	/**
	 * METHOD CALL ON MANAGE CURRENT ONHOLD 
	 */
	public String addOnHoldPrevEmp(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		boolean result=model.addOnHoldPrevEmp(salProcess,request);
		if(result){
			addActionMessage("Record released onhold successfully");
		}
		salProcess.setMonthOnHold("0");
		salProcess.setYearOnHold("");
		salProcess.setEditEmpCode("");
		salProcess.setEditEmpName("");
		salProcess.setEditEmpToken("");
		model.managePreviousOnHold(salProcess,salProcess.getMonth(),salProcess.getYear());
		/*model.showMiscStatistics(salProcess);
		String mm="";
		String yy=salProcess.getYear();
		if(!salProcess.getMonthOnHold().equals("0")){
			mm=salProcess.getMonthOnHold();
		}
		if(!salProcess.getYearOnHold().equals("")){
			yy=salProcess.getYearOnHold();
		}
		model.managePreviousOnHold(salProcess,mm,yy);
		model.terminate();*/
		return "managePreviousOnHold";
	}
	
	
	//
	/**
	 * METHOD CALL ON MANAGE CURRENT ONHOLD 
	 */
	public String removeOnHoldPrevEmp(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		boolean result=model.removeOnHoldPrevEmp(salProcess,request);
		if(result){
			addActionMessage("Record removed released onhold successfully");
		}
		salProcess.setMonthOnHold("0");
		salProcess.setYearOnHold("");
		salProcess.setEditEmpCode("");
		salProcess.setEditEmpName("");
		salProcess.setEditEmpToken("");
		model.managePreviousOnHold(salProcess,salProcess.getMonth(),salProcess.getYear());	
		return "managePreviousOnHold";
	}
	
	/**
	 * METHOD TO SAVE THE INDIVIDUAL RECORDS
	 * @return
	 */
	public String saveEmpRecord(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		boolean result=model.saveEmpRecord(salProcess,request);
		if(result){
			addActionMessage("Record saved successfully");
		}
		model.showEmpRecord(salProcess);
		model.terminate();
		return "showEmpRecord";
	}
	/**
	 * METHOD TO ADD ONHOLD THE EMPLOYEE
	 * @return
	 */
	public String onHoldEmpRecord(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		boolean result=model.onHoldEmpRecord(salProcess,"Y");
		if(result){
			addActionMessage("Record onhold successfully");			
		}
		model.showEmpRecord(salProcess);
		salProcess.setOnHoldFlag("true");
		model.terminate();
		return "showEmpRecord";
	}
	
	/**
	 * METHOD TO ADD ONHOLD THE EMPLOYEE
	 * @return
	 */
	public String removeOnHoldEmpRecord(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		boolean result=model.onHoldEmpRecord(salProcess,"N");
		if(result){
			addActionMessage("Record removed onhold successfully");			
		}
		model.showEmpRecord(salProcess);
		salProcess.setOnHoldFlag("false");
		model.terminate();
		return "showEmpRecord";
	}
	
	/**
	 * METHOD TO ADD ONHOLD THE EMPLOYEE
	 * @return
	 */
	public String addonHoldEmpRecord(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		boolean result=model.onHoldEmpRecord(salProcess,"Y");
		if(result){
			addActionMessage("Record onhold successfully");
			salProcess.setEditEmpCode("");
			salProcess.setEditEmpName("");
			salProcess.setEditEmpToken("");
			salProcess.setReProcessEmpCode("");
		}
		model.showMiscStatistics(salProcess);
		model.manageCurrentOnHold(salProcess);
		model.terminate();
		return "manageCurrentOnHold";
	}
	
	
	/**
	 * METHOD TO SAVE THE INDIVIDUAL RECORDS
	 * @return
	 */
	public String recalculateEmpRecord(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		boolean result=model.saveEmpRecord(salProcess,request);
		if(result){
			addActionMessage("Record saved successfully");
		}
		model.showEmpRecord(salProcess);
		model.terminate();
		return "showEmpRecord";
	}
	public String getEditableSalary() {
		try {
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchViewId();
			listOfFilters[1] = salProcess.getDepartmentViewId();
			listOfFilters[2] = salProcess.getPayBillViewId();
			listOfFilters[3] = salProcess.getEmployeeTypeViewId();
			listOfFilters[4] = salProcess.getDivisionId();
			
			/*
					if(salProcess.getEmpViewId()!=null && !salProcess.getEmpViewId().equals("")){
						String divisionQuery="SELECT SAL_DIVISION from HRMS_SALARY_"+salProcess.getYear()
						+" INNER JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE=SAL_LEDGER_CODE  AND EMP_ID="+salProcess.getEmpViewId()+")"
								//+" AND SAL_YEAR = "+year
						+" WHERE LEDGER_MONTH ="+salProcess.getMonth()+"  AND LEDGER_YEAR="+salProcess.getYear();
						Object divObj[][]=model.getSqlModel().getSingleResult(divisionQuery);
						if(divObj!=null && divObj.length>0){
							listOfFilters[4]=String.valueOf(divObj[0][0]);
						}
					}*/
				
				String filterQuery = model.setEmpFiletrs(listOfFilters);
				if(!salProcess.getEmpViewId().equals("")){
					filterQuery +=" AND HRMS_EMP_OFFC.EMP_ID ="+salProcess.getEmpViewId();
				}
			Object [][] attnEmpIdSaved = model.getAttnEmployeeToView(salProcess.getYear(), salProcess.getLedgerCode(),filterQuery);
			salProcess.setDataFlag(true);
			String [] dataString = new String [7];
			dataString[0] = path;
			dataString[1] = salProcess.getYear();
			dataString[2] = salProcess.getLedgerCode();
			dataString[3] = salProcess.getRecordsPerPage();
			dataString[4] = salProcess.getTotalCreditRound();
			dataString[5] = salProcess.getTotalDebitRound();
			dataString[6] = salProcess.getNetPayRound();
			Object [][] empData = model.getEmployeeDataToView(dataString, request, salProcess, attnEmpIdSaved,listOfFilters);
			request.setAttribute("rows", empData);
			model.terminate();
		}catch(Exception e) {
			logger.error("Exception in getEditableSalary in action: " + e);
		}
		return "editableSalary";
	}
	
	/**
	 * Reset the fields, set year as current year
	 * @return SUCCESS
	 */
	public String removeOnHold() {
		try {
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context,session);
			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
			 */
			String salStatus = salProcess.getLedgerStatus();
			String[] emp_id = (String[])request.getParameterValues("onHoldFlag");
			
			 if(salStatus.trim().equals("SAL_START")){
				 try {
					model.removeOnHold(emp_id,salProcess.getYear(),salProcess.getLedgerCode());
				} catch (Exception e) {
					logger.error("Exception in removeOnHold in action while updating status : " + e);
				}
			 }
			 else if(salStatus.trim().equals("SAL_FINAL")){
				 String html = "Salary locked";
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 out.print(html);
				 out.flush();
			 }
			 else{
				 String html = "Not Performed";
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 out.print(html);
				 out.flush();
			 }
		} catch (Exception e) {
			logger.error("Exception in removeOnHold in action : " + e);
		}
		return null;
	} // end of method onholdRemove 

	/**
	 * @return Object[][] with all calculated values converted as String for ajax 
	 * This action will be called when we click recalculate button in UI
	 * this method will gather employee who are selected for recalculate and the response
	 * will be shown through ajax, without refreshing the page 
	 */
	
	public String recalSalary()  {
		try {
			// In this Object we will get all employees those are selected in the screen
			String[] recal_emp = (String[])request.getParameterValues("onHoldFlag");
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
			String html = "";
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context,session);
			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
			 */
			boolean result = false;
			String divEsicFlag =String.valueOf(model.getSqlModel().getSingleResult("SELECT NVL(DIV_ESI_ZONE,'N') FROM HRMS_DIVISION WHERE DIV_ID="+salProcess.getDivisionId())[0][0]);
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			
			String [] dataString = new String [19];
			
			dataString[0] = salProcess.getMonth();
			dataString[1] = salProcess.getYear();
			dataString[2] = salProcess.getLedgerCode();
			dataString[3] = salProcess.getJoinDaysFlag();
			dataString[4] = salProcess.getCreditRound();
			dataString[5] = path;
			dataString[6] = salProcess.getVpfFlag();
			dataString[7] = salProcess.getLwfFlag();
			dataString[8] = "";
			dataString[9] = salProcess.getProfHandiFLag();
			dataString[10] = salProcess.getLwfCreditCode();
			dataString[11] = salProcess.getLwfDebitCode();
			dataString[12] = salProcess.getIncomeTaxFlag();
			dataString[13] = salProcess.getTotalCreditRound();
			dataString[14] = salProcess.getTotalDebitRound();
			dataString[15] = salProcess.getNetPayRound();
			dataString[16] = "N";					//salProcess.getRecoveryFlag();
			dataString[17] = "0";					//salProcess.getRecoveryDebitCode();
			dataString[18] = divEsicFlag; //Division-wise ESIC flag)
				
			String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);
			if(!(salStatus.equals("SAL_FINAL"))){  //ledger status is SAL_START or ATTN_READY then the control goes into if loop
					/**
					 * In the rows[][] object we will get all the records of selected employees  
				   	 * i.e., all new credits and debits and net pay
					 */
					Object rows[][]= model.recalSalary(request,recal_emp,salStatus,path,salProcess,dataString,listOfFilters);	
					/**
					 * the below for loop is for reflecting the fields which are responsible to change (selected employees fields)
				  	 * through ajax. For that we are appending '#' in between each and every filed and '@' in between each and every employee
					 */
					if(rows==null){
					
					}
					else{
						for (int i = 0; i < rows.length; i++) {
							for (int j = 0; j < rows[0].length; j++) {
								if( j==rows[0].length-1 ){
									html += String.valueOf(rows[i][j]);	
								}
								else{
									html += String.valueOf(rows[i][j]);
									html += "#";
								}
							} // end on inner for loop
							/**
							 * below if condition is to check whether the record is lasta on or not, if it is 
						  	 * last record the # will not be appended.
							 */
							if(rows.length>1){
								if(i==rows.length-1){
									
								}
								else{
									html +="@";
								}
							} // end of 2nd if loop
							
						}
					}// end of outer for loop
				    // Write the HTML to response
				    response.setContentType("text/html");
				    PrintWriter out = response.getWriter();
				    out.println(html);
				    //we are setting rows Object in request attribute to access it in the client side.
					request.setAttribute("rows", rows);
			} // end of 1st if loop
			else{
				 html = "Salary Locked";
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 out.print(html);
				   
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	} // end of method recalSalary
	
	public String reset() {
		try {
			salProcess.setMonth("");
			// Set the current year
			Calendar c = Calendar.getInstance();
			if(salProcess.isShowFlag()){
				salProcess.setUploadCreditCode("");
				salProcess.setUploadCreditName("");
				salProcess.setUploadFileNameCredit("");
				salProcess.setUploadDebitCode("");
				salProcess.setUploadDebitName("");
				salProcess.setUploadFileNameDebit("");
				//salProcess.setYear(salProcess.getYear());
			}else{
				salProcess.setYear(String.valueOf(c.get(Calendar.YEAR)));
				salProcess.setDivisionFlag(false);
				salProcess.setDivisionId("");
				salProcess.setDivisionName("");
				salProcess.setBranchFlag(false);
				salProcess.setBranchId("");
				salProcess.setBranchName("");
				salProcess.setDepartmentFlag(false);
				salProcess.setDepartmentId("");
				salProcess.setDepartmentName("");
				salProcess.setPayBillFlag(false);
				salProcess.setPayBillId("");
				salProcess.setPayBillName("");
				salProcess.setEmployeeTypeFlag(false);
				salProcess.setEmployeeTypeId("");
				salProcess.setEmployeeTypeName("");
				salProcess.setShowFlag(false);
			}
			loadPayrollSettings();
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in reset in action: " + e);
			return "";
		}
	}
	
	public String f9CreditAction() throws Exception 
	{
		
		logger.info("in f9 action");
		
		String query = "SELECT CREDIT_NAME,CREDIT_CODE FROM HRMS_CREDIT_HEAD" +
					   " WHERE CREDIT_PERIODICITY = 'M' AND CREDIT_PAYFLAG = 'Y' ORDER BY CREDIT_CODE";		
		
		
		String[] headers={getMessage("credit.Name")};
		
		String[] headerWidth={"100"};
		
		
		String[] fieldNames={"uploadCreditName","uploadCreditCode"};
		
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String f9DebitAction() throws Exception 
	{
		
		logger.info("in f9 action");
		
		String query = "SELECT DEBIT_NAME,DEBIT_CODE FROM HRMS_DEBIT_HEAD  ORDER BY DEBIT_CODE";		
		
		
		String[] headers={getMessage("debit.Name")};
		
		String[] headerWidth={"100"};
		
		
		String[] fieldNames={ "uploadDebitName","uploadDebitCode"};
		
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public String f9employee() throws Exception 
	{
		try
		{
						
			String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, " 
				+" DECODE(EMP_STATUS, 'S','Service','R','Retired','N','Resigned','E','Terminated') ,EMP_ID" 
				+" FROM HRMS_EMP_OFFC " 
				+" WHERE  EMP_ID IN( SELECT ATTN_EMP_ID FROM HRMS_MONTH_ATTENDANCE_"+salProcess.getYear()+" WHERE ATTN_CODE ="+salProcess.getLedgerCode()+") "			
				+" ORDER BY UPPER(ENAME) ";
			
			String[] headers = {getMessage("employee.id"), getMessage("employee") ,getMessage("status")};

			String[] headerWidth = {"20", "80","20"};

			String[] fieldNames = {"empTokenView", "empViewName","empStatusView","empViewId"};

			int[] columnIndex = {0, 1, 2,3};

			/*String submitFlag = "true";

			String submitToMethod = "MonthAttendance_setSearchFlag.action";*/
			
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
	
	
	public String f9arrearEmployee() throws Exception 
	{
		try
		{
			
			/**
			 * SET MANAGE PREVIOUS ONHOLD EMPLOYEE COUNT
			 */
			String month=salProcess.getMonth();
			String year=salProcess.getYear();
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			String arrearLedgerCode="0";	
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context,session);
			String ledgerQuery="SELECT ARREARS_CODE,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL FROM HRMS_ARREARS_LEDGER"
								+"  WHERE ARREARS_PAY_IN_SAL='Y' AND ARREARS_STATUS='L' AND  ARREARS_PAID_MONTH="+month+" AND ARREARS_PAID_YEAR="+year+"  ";
			if(!listOfFilters[4].equals("")){
				ledgerQuery+=" AND ARREARS_DIVISION=  "+listOfFilters[4];
			}
			if(!listOfFilters[0].equals("")){
				ledgerQuery+=" AND ARREARS_BRANCH=  "+listOfFilters[0];
			}
			if(!listOfFilters[1].equals("")){
				ledgerQuery+=" AND ARREARS_DEPARTMENT=  "+listOfFilters[1];
			}
			if(!listOfFilters[2].equals("")){
				ledgerQuery+=" AND ARREARS_PAYBILL=  "+listOfFilters[2];
			}
			if(!listOfFilters[3].equals("")){
				ledgerQuery+=" AND ARREARS_EMPTYPE=  "+listOfFilters[3];
			}
			Object[][]ledgerObj=model.getSqlModel().getSingleResult(ledgerQuery);
			if(ledgerObj!=null && ledgerObj.length>0){
				for (int i = 0; i < ledgerObj.length; i++) {
					arrearLedgerCode+=","+String.valueOf(ledgerObj[i][0]);
				}
			}
						
			String query="SELECT  EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,SUM(NVL(ARREARS_NET_AMT,0))  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_ARREARS_"+year+" ON(HRMS_ARREARS_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID ) "	
			+"	WHERE (ARREARS_PAY_TYPE !='O' OR ARREARS_PAY_TYPE IS NULL ) AND ARREARS_CODE IN("+arrearLedgerCode+") GROUP BY EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";	
			
			String[] headers = {getMessage("employee.id"), getMessage("employee") ,"Net Salary"};

			String[] headerWidth = {"20", "60","20"};


			String[] fieldNames = {"statusEmpCode", "statusEmpName","statusEmpCode"};

			int[] columnIndex = {0, 1, 2};

			/*String submitFlag = "true";

			String submitToMethod = "MonthAttendance_setSearchFlag.action";*/
			
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
	
	
	//f9miscSalary
	
	public String f9miscSalary() throws Exception 
	{
		try
		{			
			String month=salProcess.getMonth();
			String year=salProcess.getYear();
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			
			String creditType="";
			if(salProcess.getMiscTypeHidden().trim().equals("Credit")){
				creditType="C";
			}
			else if(salProcess.getMiscTypeHidden().trim().equals("Debit")){
				creditType="D";
			}
			System.out.println("creditType::"+creditType);
			String creditCode=salProcess.getMiscCodeHidden();
			
			String query="SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,CREDIT_NAME,SALARY_AMOUNT "
						+"	,DECODE(UPLOAD_IS_OVERWRITE,'N','Append','Y','Override')  FROM HRMS_MISC_SALARY_UPLOAD   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_MISC_SALARY_UPLOAD.EMP_ID) " 	
						+"   INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_MISC_SALARY_UPLOAD.SALARY_CODE ) "
						+"	WHERE MONTH="+month+" AND YEAR="+year+" AND SALARY_AMOUNT>0 AND DISPLAY_FLAG='Y' AND HRMS_EMP_OFFC.EMP_DIV=  "+salProcess.getDivisionId()+" ";	
			
			if(creditType.trim().equals("D")){
				query="SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,DEBIT_NAME,SALARY_AMOUNT "
					+"	,DECODE(UPLOAD_IS_OVERWRITE,'N','Append','Y','Override')  FROM HRMS_MISC_SALARY_UPLOAD   INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_MISC_SALARY_UPLOAD.EMP_ID) " 	
					+"   INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_MISC_SALARY_UPLOAD.SALARY_CODE ) "
					+"	WHERE MONTH="+month+" AND YEAR="+year+" AND SALARY_AMOUNT>0 AND DISPLAY_FLAG='Y' AND HRMS_EMP_OFFC.EMP_DIV=  "+salProcess.getDivisionId()+" ";	
		
			}
			if(!creditCode.equals("")){
				query+= " AND SALARY_CODE="+creditCode+" ";
			}
			if(!creditType.equals("")){
				query+= " AND UPLOAD_PAY_TYPE= '"+creditType+"'";
			}
			//query+= " AND SALARY_CODE="+creditCode+"  AND UPLOAD_PAY_TYPE= '"+creditType+"'";
			
			
			
			
			if(!listOfFilters[4].equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_DIV=  "+listOfFilters[4];
			}
			if(!listOfFilters[0].equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_CENTER=  "+listOfFilters[0];
			}
			if(!listOfFilters[1].equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_DEPT=  "+listOfFilters[1];
			}
			if(!listOfFilters[2].equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_PAYBILL=  "+listOfFilters[2];
			}
			if(!listOfFilters[3].equals("")){
				query+=" AND HRMS_EMP_OFFC.EMP_TYPE=  "+listOfFilters[3];
			}
			String[] headers = {getMessage("employee.id"), getMessage("employee") ,"Head","Amount"};
			String[] headerWidth = {"20", "40","15","15"};
			String[] fieldNames = {"miscCodeHidden", "miscCodeHidden","miscCodeHidden","miscCodeHidden"};
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
	
	public String f9Statistics() throws Exception 
	{
		try
		{
						
			String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME ,SAL_DAYS,ATTN_SAL_DAYS " 
				+" FROM HRMS_EMP_OFFC " 
				+"   "			
				+" ORDER BY UPPER(ENAME) ";
			
			String type=salProcess.getStatisticsHidden();
			String year=salProcess.getYear();
			String ledgerCOde=salProcess.getLedgerCode();
			if(type.equals("T")){
				String totalEmpId=salProcess.getTotalEmp();
				query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,SAL_DAYS " 
					+" FROM HRMS_EMP_OFFC " 
					+"  INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+ledgerCOde+" ) "
					+" WHERE 1=1  "			
					+" ORDER BY UPPER(ENAME) ";
			}
			if(type.equals("S")){
				String totalEmpId=salProcess.getStatusChange();
				query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME ,NVL(SAL_DAYS,0) ,ATTN_SAL_DAYS " 
					+" FROM HRMS_EMP_OFFC " 
					+"  LEFT JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+ledgerCOde+" ) "
					+"   INNER JOIN HRMS_MONTH_ATTENDANCE_"+year+" ON(HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ATTN_CODE="+ledgerCOde+" ) "
					+" WHERE HRMS_EMP_OFFC.EMP_ID IN("+totalEmpId+")  "			
					+" ORDER BY UPPER(ENAME) ";
			}
			if(type.equals("Z")){
				String totalEmpId=salProcess.getZeroDays();
				query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,SAL_DAYS,ATTN_SAL_DAYS " 
					+" FROM HRMS_EMP_OFFC " 
					+"  INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+ledgerCOde+" ) "
					+"   INNER JOIN HRMS_MONTH_ATTENDANCE_"+year+" ON(HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID=HRMS_SALARY_"+year+".EMP_ID AND ATTN_CODE="+ledgerCOde+" ) "
					+" WHERE HRMS_EMP_OFFC.EMP_ID IN("+totalEmpId+")  "			
					+" ORDER BY UPPER(ENAME) ";
			}
			if(type.equals("O")){
				String totalEmpId=salProcess.getOnHoldEmp();
				query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,SAL_DAYS ,ATTN_SAL_DAYS " 
					+" FROM HRMS_EMP_OFFC " 
					+"  INNER JOIN HRMS_SALARY_"+year+" ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SAL_LEDGER_CODE="+ledgerCOde+" ) "
					+"   INNER JOIN HRMS_MONTH_ATTENDANCE_"+year+" ON(HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID=HRMS_SALARY_"+year+".EMP_ID AND ATTN_CODE="+ledgerCOde+" ) "
					+" WHERE HRMS_EMP_OFFC.EMP_ID IN("+totalEmpId+")  "			
					+" ORDER BY UPPER(ENAME) ";
			}
			
			String[] headers = {getMessage("employee.id"), getMessage("employee"),"Salary Days"};

			String[] headerWidth = {"20", "40","20"};

			String[] fieldNames = {"statusEmpCode", "statusEmpName","statusEmpCode"};

			int[] columnIndex = {0, 1,2};

			/*String submitFlag = "true";

			String submitToMethod = "MonthAttendance_setSearchFlag.action";*/
			
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
	
	
	
	public String downloadTemplate(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		///it generated the report of all the employees who r in Service (Emp_Status = 'S')
		model.downloadTemplate(salProcess,response);
		model.terminate();
		return null;
	}
	
	public String uploadCredit(){

		/// In this fullFile the Downloaded Excel Sheet is copied from the path where it is saved.
	
	
		String fullFile = context.getRealPath("/")+ "pages/oo/"+session.getAttribute("session_pool")+"/pay/" +salProcess.getUploadFileNameCredit() ; 
		System.out.println("fullfile:"+fullFile);
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		try {
			///this InputStream class is imported.
			InputStream myxls = new FileInputStream(fullFile);
			//this HSSFWorkbook class is used to read the columns and Rows from the Downloaded Excel sheet. 
			HSSFWorkbook wb     = new HSSFWorkbook(myxls);
			
			HSSFSheet sheet = wb.getSheetAt(0); 
			
				int c=0;
				
				//Object add is created according to the RowNum...and  -2 is used...because the 
				//the first 2 rows of the XLS sheet are empty ven it is generated.
			Object[][] add=new Object[Integer.parseInt(String.valueOf(sheet.getLastRowNum()))+4][2];
				for(int i=0; i<=sheet.getLastRowNum();i++){
					try {
						HSSFRow row    = sheet.getRow(i);
						///in this cell...the value of the amount field is read.
						HSSFCell cell1   = row.getCell((short)2);
						///in this cell1 the value of Employee Code is read.
						HSSFCell cell   = row.getCell((short)0);
						///cell.getNumericCellValue is used because both rows contain Numeric Cell Values....
						/// if the cell contains String(Names) u can use cell.getStringCellValue()
						///Object add is created which contains all the employee amount and Code from the Excel Sheet
						
						if(cell !=null){
							if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
								add[c][1]=(double)cell.getNumericCellValue();
								//logger.info("Employee Code in if------"+add[c][0]);
							}else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
								add[c][1]= cell.getStringCellValue();
								//logger.info("Employee Code in else if------"+add[c][0]);
							}
						}
						
						if(cell1!=null){
							if(cell1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
								add[c][0]= (double)cell1.getNumericCellValue();
								//logger.info("Employee Code in if------"+add[c][0]);
							}else if(cell1.getCellType() == HSSFCell.CELL_TYPE_STRING){
								add[c][0]= cell1.getStringCellValue();
								//logger.info("Employee Code in else if------"+add[c][0]);
								if(cell1.getStringCellValue().equals("Amount")){
								}
								else{
									addActionMessage("Characters found at amount column for employee "+String.valueOf(add[c][1]));
									addActionMessage("Enter only Numbers in the amount column in excel sheet ");
									salProcess.setUploadFileNameCredit("");
									return "success";
								}
							}
						}
						
						//logger.info("Employee Code-------"+add[c][0]);
						if(cell1!=null || cell !=null){
							c++;
							//logger.info("val c----------------"+c);
						}
					} catch (RuntimeException e) {
						logger.error(e.getMessage());
					}
				}
				int objCnt=0;
				for (int i = 0; i < add.length; i++) {
					if(!(add[i][1] ==null || String.valueOf(add[i][1]).equals("null") || 
							String.valueOf(add[i][1]).equalsIgnoreCase("Employee Code"))){
						objCnt++;
					}
				}
				Object[][] finalObj = new Object[objCnt][3];
				objCnt=0;
				for (int i = 0; i < add.length; i++) {
					if(!(add[i][1] ==null || String.valueOf(add[i][1]).equals("null") || 
							String.valueOf(add[i][1]).equalsIgnoreCase("Employee Code"))){
						finalObj[objCnt][0] = add[i][0];
						finalObj[objCnt][1] = add[i][1];
						finalObj[objCnt][2] = salProcess.getUploadCreditCode();
						objCnt++;
					}
				}
				String poolName = String.valueOf(session.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null )) {
					poolName = "/" + poolName;
				}
				//for getting server path where configuration files are saved.
				String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
				///this method is called to update the Credits with the amount which is entered in the XLS Sheet
				// add Object is passed in the method.
				String result=model.updCredits(salProcess,finalObj,path);
				
				if(result.equals("2")){
					addActionMessage("Credit Updated Successfully");
				}else if(result.equals("1")){
					addActionMessage("Salary Not Processed");
				}
				
			/*}
			else{
				addActionMessage("Invalid Xls File Uploaded");
			}*/
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		
		model.terminate();
		
		return SUCCESS;
	
	}
	
	public String pullExtraWorkBenefit(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null )) {
			poolName = "/" + poolName;
		}
		String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
		String[] listOfFilters = new String[5];
		listOfFilters[0] = salProcess.getBranchId();
		listOfFilters[1] = salProcess.getDepartmentId();
		listOfFilters[2] = salProcess.getPayBillId();
		listOfFilters[3] = salProcess.getEmployeeTypeId();
		listOfFilters[4] = "";
		String result = model.pullExtraWorkBenefit(salProcess,path,listOfFilters);
		if(!result.equals("0")){
			addActionMessage("ExtraWork benifit data of "+result+" employees updated in Salary successfully");
		}else {
			addActionMessage("ExtraWork benifit data not available");
		}
		model.terminate();
		return SUCCESS;
	}
	
	public String pullLeaveEncashment(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null )) {
			poolName = "/" + poolName;
		}
		String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
		String[] listOfFilters = new String[5];
		listOfFilters[0] = salProcess.getBranchId();
		listOfFilters[1] = salProcess.getDepartmentId();
		listOfFilters[2] = salProcess.getPayBillId();
		listOfFilters[3] = salProcess.getEmployeeTypeId();
		listOfFilters[4] = "";
		String result = model.pullLeaveEncashment(salProcess,path,listOfFilters);
		if(!result.equals("0")){
			addActionMessage("Leave Encashment data of "+result+" employees updated in Salary successfully");
		}else {
			addActionMessage("Leave Encashment data not available");
		}
		model.terminate();
		return SUCCESS;
	}
	
	/*public String pullAllowance(){
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null )) {
			poolName = "/" + poolName;
		}
		String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
		String[] listOfFilters = new String[5];
		listOfFilters[0] = salProcess.getBranchId();
		listOfFilters[1] = salProcess.getDepartmentId();
		listOfFilters[2] = salProcess.getPayBillId();
		listOfFilters[3] = salProcess.getEmployeeTypeId();
		listOfFilters[4] = "";
		String result = model.pullAllowance(salProcess,path,listOfFilters);
		if(!result.equals("0")){
			addActionMessage("Allowance benifit data of "+result+" employees updated in Salary successfully");
		}else {
			addActionMessage("ExtraWork benifit data not available");
		}
		model.terminate();
		return SUCCESS;
	}
	*/
	public String uploadDebit()throws Exception {
	
	String fullFile = context.getRealPath("/")+ "pages/oo/"+session.getAttribute("session_pool")+"/pay/" +salProcess.getUploadFileNameDebit() ; 
	logger.info("file name------------------------------------"+fullFile);
	SalaryProcessModel model = new SalaryProcessModel();
	model.initiate(context, session);
	try {
		logger.info("in try........");
		InputStream myxls = new FileInputStream(fullFile);
		HSSFWorkbook wb     = new HSSFWorkbook(myxls);
		
		HSSFSheet sheet = wb.getSheetAt(0); 
		int c=0;
		Object[][] add=new Object[Integer.parseInt(String.valueOf(sheet.getLastRowNum()))+4][2];
			for(int i=0; i<=sheet.getLastRowNum();i++){
				try {
					HSSFRow row = sheet.getRow(i);
					HSSFCell cell1 = row.getCell((short) 2);
					HSSFCell cell = row.getCell((short) 0);
					
					if(cell !=null){
						if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							add[c][1]=(double)cell.getNumericCellValue();
						}else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
							add[c][1]= cell.getStringCellValue();
						}
					}
					
					if(cell1!=null){
						if(cell1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							add[c][0]= (double)cell1.getNumericCellValue();
							//logger.info("Employee Code in if------"+add[c][0]);
						}else if(cell1.getCellType() == HSSFCell.CELL_TYPE_STRING){
							add[c][0]= cell1.getStringCellValue();
							//logger.info("Employee Code in else if------"+add[c][0]);
							if(cell1.getStringCellValue().equals("Amount")){
								
							}
							else{
								addActionMessage("Characters found at amount column for employee "+String.valueOf(add[c][1]));
								addActionMessage("Enter only Numbers in the amount field in excel sheet ");
								salProcess.setUploadFileNameDebit("");
								return "success";
							}
						}
					}						
					
					//logger.info("Employee Code-------"+add[c][0]);
					if(cell1!=null || cell !=null){
						c++;
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					
				}
			}
			int objCnt=0;
			for (int i = 0; i < add.length; i++) {
				if(!(add[i][1] ==null || String.valueOf(add[i][1]).equals("null") || 
						String.valueOf(add[i][1]).equalsIgnoreCase("Employee Code"))){
					objCnt++;
				}
			}
			Object[][] finalObj = new Object[objCnt][2];
			objCnt=0;
			for (int i = 0; i < add.length; i++) {
				if(!(add[i][1] ==null || String.valueOf(add[i][1]).equals("null") || 
						String.valueOf(add[i][1]).equalsIgnoreCase("Employee Code"))){
					finalObj[objCnt][0] = add[i][0];
					finalObj[objCnt][1] = add[i][1];
					objCnt++;
				}
			}
			
			String result=model.updDebits(salProcess,finalObj,salProcess.getUploadDebitCode());
			
			if(result.equals("2")){
				addActionMessage("Debit Updated Successfully");
				// addActionMessage(getText("addMessage", ""));
			}else if(result.equals("1")){
				addActionMessage("Salary Not Processed or Salary has been Locked");
			}
			
		/*}
		else{
			logger.info("in else action-----------------------------------------");
			addActionMessage("Invalid Xls File Uploaded");
		}*/
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	model.terminate();
	
	return SUCCESS;

	}
	
	/**
	 * @return String for ajax
	 * Saving on hold status for employees through ajax 
	 */
	public String onholdSave() {
		try {
			String month = salProcess.getMonth();
			String year = salProcess.getYear();
			String ledgerCode = salProcess.getLedgerCode(); // Ledger code for changing status
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context,session);

			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
			 */
			String ledgerStatus = salProcess.getLedgerStatus();
				
			String[] emp_id = (String[])request.getParameterValues("onHoldFlag");
			 if(ledgerStatus.trim().equals("SAL_START")){
				 try {
					model.saveOnHold(emp_id,year,ledgerCode);
				} catch (RuntimeException e) {
					logger.error(e.getMessage());
				}
			 }
			 else if(ledgerStatus.trim().equals("SAL_FINAL")){
				 String html = "Salary locked";
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 out.print(html);
				 out.flush();
			 }
			 else{
				 String html = "Not Performed";
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 out.print(html);
				 out.flush();
			 }
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return null;
	} //end of method onholdSave
	
	/**
	 * For saving processed, modified salary
	 * @return String "success"
	 */
	public String saveSalaryAfterRecalculate() throws Exception {
		try {
			String month = salProcess.getMonth();
			String year = salProcess.getYear();
			String branchCode = salProcess.getBranchId();
			String typeCode = salProcess.getEmployeeTypeId();
			String paybillNo = salProcess.getPayBillId();
			String deptCode = salProcess.getDepartmentId();
			String divCode = salProcess.getDivisionId();
			String[] onHoldEmp =(String[])request.getParameterValues("onholdEmp"); // Picked form client side to update On Hold flag
		
			SalaryProcessModel model = new SalaryProcessModel();
			model.initiate(context,session);
			//for getting server path where configuration files are saved.
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
		     */
			String ledgerStatus = salProcess.getLedgerStatus();
			if(!(ledgerStatus.equals("SAL_FINAL"))){
			Object d[][]			=	model.getDebitHeader(path);
			Object c[][]			=	model.getCreditHeader(path);
			String[] emp_id			=	request.getParameterValues("emp_id");
			String total_credit[]	=	new String [emp_id.length];     // For total credit
			String total_debit[]	=	new String[emp_id.length];		// For total debit
			String sal_days[]		=	new String[emp_id.length];		// For salary days
			String onHold[]			=	new String[emp_id.length];		// For on hold employees
			try
			{
				for(int i=0;i<emp_id.length;i++)
				{
					/**
					* FOR GETTING VALUE OF TOTAL CREDIT AND TOTAL DEBIT 
					* FROM JSP
					*/
					total_credit[i]=request.getParameter("totalCredit"+i);
					total_debit[i]=request.getParameter("totalDebit"+i);
					sal_days[i]=request.getParameter("salDays"+i);
					onHold[i]=onHoldEmp[i];
					logger.info("-----------onHold value"+onHold[i]);
				}
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			
			Object [][] rows=new Object[emp_id.length][d.length+c.length]; 
			 		
			for(int i=0;i<emp_id.length;i++)
			{
				/**
				 * FOR GETTING CREDIT AND DEBIT FROM JSP 
				 */
				rows[i]=request.getParameterValues(String.valueOf(i));
			}			
			String empType = salProcess.getEmployeeTypeId();
			String ledgerCode=salProcess.getLedgerCode();
			boolean record= model.saveSalaryAfterRecalculate( rows,d,c,emp_id,month, year,total_credit,total_debit,empType,
					sal_days,onHold,ledgerCode,branchCode,typeCode,paybillNo,deptCode,divCode,"N");					//salProcess.getRecoveryFlag());
			if(record){
				model.saveSalProcessStatus(ledgerCode);
				addActionMessage("Record saved successfully");
			//	d=null;c=null;total_credit=null;total_debit=null;emp_id=null;onHold=null;sal_days=null;
			} //end of if loop
			}else{
				addActionMessage("Salary already Locked");
			}
			/**
			 *  For reloading all the processed salary after saving the records
			 */
			getEditableSalary();
			model.terminate();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "editableSalary";
	} //end of method saveNonIndustrialSalaries
	
	public String recalculateTax() {
		String[] listOfFilters = new String[5];
		listOfFilters[0] = salProcess.getBranchId();
		listOfFilters[1] = salProcess.getDepartmentId();
		listOfFilters[2] = salProcess.getPayBillId();
		listOfFilters[3] = salProcess.getEmployeeTypeId();
		listOfFilters[4] = salProcess.getDivisionId();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int fromYear = Integer.parseInt(salProcess.getYear());
		int month = Integer.parseInt(salProcess.getMonth());
		logger.info("month ====" + month);
		logger.info("fromYear ====" + fromYear);
		if (month <= 3)
			fromYear--;
		boolean isTaxCalclulated=false;
		String debitUpdate="";
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		
		String salStatus = model.checkSalaryProcessStatus(salProcess,
				listOfFilters);
		if (salStatus.equals("SAL_FINAL")) {
			addActionMessage("Salary already locked!");
		} else if (salStatus.equals("ATTN_READY")) {
			addActionMessage("Salary is not processed!");
		} else {
			String debitCode = model.getTdsDebitCode(String.valueOf(fromYear),
					String.valueOf(fromYear + 1));
			
			String empQuery = "SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN FROM  HRMS_SALARY_"+ salProcess.getYear()
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+salProcess.getYear()+".EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+" WHERE SAL_LEDGER_CODE ="+ salProcess.getLedgerCode() 
				+" ORDER BY EMP_ID";

			Object[][] empList = model.getSqlModel().getSingleResult(empQuery);
			long startTime = System.currentTimeMillis();
			
			model.setTdsAmountToZero(empList,salProcess.getLedgerCode(),salProcess.getYear(),debitCode);
			
			if (empList != null || empList.length > 0) {
				
				isTaxCalclulated = model.recalculateTax(empList, fromYear,salProcess.getMonth());
				logger.info("isTaxCalclulated==="+isTaxCalclulated);
				if(isTaxCalclulated){

				Object[][] empListWithDebitAmt = model.getEmpListWithDebitAmt(
						empList, salProcess.getYear(), salProcess
								.getLedgerCode(), fromYear);
				
				debitUpdate=model.updDebits(salProcess, empListWithDebitAmt, debitCode);
				logger.info("time required by process is====="+ (System.currentTimeMillis() - startTime));
				}
				if(isTaxCalclulated && debitUpdate.equals("2")){
					addActionMessage("Tax recalculated successfully.");
				}
			} else {
				addActionMessage("Employees not available to calculate tax.");
			}

		}
		
		model.showMiscStatistics(salProcess);
		model.terminate();
		return SUCCESS;
	}
	
	
	public String resetRecords(){
		salProcess.setDataFlag(true);
		salProcess.setDepartmentViewId("");
		salProcess.setDepartmentViewName("");
		salProcess.setBranchViewId("");
		salProcess.setBranchViewName("");
		salProcess.setEmployeeTypeViewId("");
		salProcess.setEmployeeTypeViewName("");
		salProcess.setPayBillViewId("");
		salProcess.setPayBillViewName("");
		salProcess.setEmpViewId("");
		salProcess.setEmpViewName("");
		salProcess.setDataFlag(false);
		return "editableSalary";
	
	}
	
	public String recalculate() {
		recalculateEmpChangeSalary();
		salProcess.setReProcessEmpCode("");
		return SUCCESS;
	}
	

		/**
		 * RECALUCLATE SELECTED EMPLOYEE SALARY
		 */
	
	public String recalculateEmpSalary() {
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		try {	
			

			// Get the filters from back end
			Object[][] salaryFilters = model.getPayrollConfig();
			try {
				if(salaryFilters != null && salaryFilters.length > 0) {
					salProcess.setBranchFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][0])));
					salProcess.setDepartmentFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][1])));
					salProcess.setPayBillFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][2])));
					salProcess.setEmployeeTypeFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][3])));
					salProcess.setDivisionFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][4])));
					salProcess.setRecordsPerPage(String.valueOf(salaryFilters[0][5]));
					salProcess.setJoinDaysFlag(String.valueOf(salaryFilters[0][6]));
					salProcess.setRecoveryFlag(String.valueOf(salaryFilters[0][7]));
					salProcess.setProfHandiFLag(String.valueOf(salaryFilters[0][8]));
					salProcess.setIncomeTaxFlag(String.valueOf(salaryFilters[0][9]));
					salProcess.setVpfFlag(String.valueOf(salaryFilters[0][10]));
					salProcess.setCreditRound(String.valueOf(salaryFilters[0][11]));
					salProcess.setTotalCreditRound(String.valueOf(salaryFilters[0][12]));
					salProcess.setTotalDebitRound(String.valueOf(salaryFilters[0][13]));
					salProcess.setNetPayRound(String.valueOf(salaryFilters[0][14]));
				}
			} catch (Exception e) {
				logger.error("Exception in callfor edit in action setting payroll configuration: " + e);
			}
						
			boolean result = false;
			// Set list of filters
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			
			
			//check whether table for selected year exists ot not.
			boolean isTableExists = model.isTableExist(salProcess.getYear());
			String divEsicFlag =String.valueOf(model.getSqlModel().getSingleResult("SELECT NVL(DIV_ESI_ZONE,'N') FROM HRMS_DIVISION WHERE DIV_ID="+salProcess.getDivisionId())[0][0]);
			if(isTableExists){
				// get the salary ledger status to check whether salary already processed or attendance processed or locked etc.
				String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);
				if((salStatus.equals("SAL_FINAL"))){
					addActionMessage("Salary is locked.Please unlock and then recalculate!");			
				}
				else if(salStatus.equals("ATTN_START") || salStatus.equals("ATTN_UNLOCK")){
					addActionMessage("Attendance has been not locked!");
				}else if(salStatus.equals("") || salStatus.equals("null")){
					addActionMessage("Attendance has not been processed!");
				} 
				else {
					String [] dataString = new String [20];
						
						dataString[0] = salProcess.getMonth();
						dataString[1] = salProcess.getYear();
						dataString[2] = salProcess.getLedgerCode();
						dataString[3] = salProcess.getJoinDaysFlag();
						dataString[4] = salProcess.getCreditRound();
						dataString[5] = "";
						dataString[6] = salProcess.getVpfFlag();
						dataString[7] = salProcess.getLwfFlag();
						dataString[8] = "";
						dataString[9] = salProcess.getProfHandiFLag();
						dataString[10] = salProcess.getLwfCreditCode();
						dataString[11] = salProcess.getLwfDebitCode();
						dataString[12] = salProcess.getIncomeTaxFlag();
						dataString[13] = salProcess.getTotalCreditRound();
						dataString[14] = salProcess.getTotalDebitRound();
						dataString[15] = salProcess.getNetPayRound();
						dataString[16] = "N";				//salProcess.getRecoveryFlag();
						dataString[17] = "0";				//salProcess.getRecoveryDebitCode();
						dataString[18] = divEsicFlag; // Division-wise ESIC flag)
						dataString[19]=salProcess.getCompanyPFFlag();
						Object empObj[][] = model.getAttendanceEmployeeToProcess(salProcess.getYear(),salProcess.getLedgerCode(),salProcess.getReProcessEmpCode());
						//empObj[0][0]=salProcess.getEditEmpCode();
						result = model.processSalary(dataString,listOfFilters,empObj,"Y");
						
						if(result) {
							addActionMessage("Salary recalculated successfully!");
							salProcess.setShowFlag(true);
							salProcess.setLedgerStatus("SAL_START");
							salProcess.setMonthView(Utility.month(Integer.parseInt(salProcess.getMonth())));
							model.saveSalProcessStatus(salProcess.getLedgerCode());
						}else
							addActionMessage("Error in salary processing!");
						
				}
			}else {
				addActionMessage("Salary can not be processed as tables for mentioned year not exists in databse!");
			}
			
		} catch (Exception e) {
			logger.error("Exception in processSalary in action: " + e);
		}
		//setOtherIncomeFlags();
		model.showMiscStatistics(salProcess);
		model.showEmpRecalRecord(salProcess);
		model.terminate();
		return "showEmpRecord";
	}
	
	public String recalculateEmpChangeSalary() {
		SalaryProcessModel model = new SalaryProcessModel();
		model.initiate(context, session);
		try {	
			

			// Get the filters from back end
			Object[][] salaryFilters = model.getPayrollConfig();
			try {
				if(salaryFilters != null && salaryFilters.length > 0) {
					salProcess.setBranchFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][0])));
					salProcess.setDepartmentFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][1])));
					salProcess.setPayBillFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][2])));
					salProcess.setEmployeeTypeFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][3])));
					salProcess.setDivisionFlag(Boolean.parseBoolean(String.valueOf(salaryFilters[0][4])));
					salProcess.setRecordsPerPage(String.valueOf(salaryFilters[0][5]));
					salProcess.setJoinDaysFlag(String.valueOf(salaryFilters[0][6]));
					salProcess.setRecoveryFlag(String.valueOf(salaryFilters[0][7]));
					salProcess.setProfHandiFLag(String.valueOf(salaryFilters[0][8]));
					salProcess.setIncomeTaxFlag(String.valueOf(salaryFilters[0][9]));
					salProcess.setVpfFlag(String.valueOf(salaryFilters[0][10]));
					salProcess.setCreditRound(String.valueOf(salaryFilters[0][11]));
					salProcess.setTotalCreditRound(String.valueOf(salaryFilters[0][12]));
					salProcess.setTotalDebitRound(String.valueOf(salaryFilters[0][13]));
					salProcess.setNetPayRound(String.valueOf(salaryFilters[0][14]));
				}
			} catch (Exception e) {
				logger.error("Exception in callfor edit in action setting payroll configuration: " + e);
			}
						
			boolean result = false;
			// Set list of filters
			String[] listOfFilters = new String[5];
			listOfFilters[0] = salProcess.getBranchId();
			listOfFilters[1] = salProcess.getDepartmentId();
			listOfFilters[2] = salProcess.getPayBillId();
			listOfFilters[3] = salProcess.getEmployeeTypeId();
			listOfFilters[4] = salProcess.getDivisionId();
			
			
			//check whether table for selected year exists ot not.
			boolean isTableExists = model.isTableExist(salProcess.getYear());
			String divEsicFlag =String.valueOf(model.getSqlModel().getSingleResult("SELECT NVL(DIV_ESI_ZONE,'N') FROM HRMS_DIVISION WHERE DIV_ID="+salProcess.getDivisionId())[0][0]);
			if(isTableExists){
				// get the salary ledger status to check whether salary already processed or attendance processed or locked etc.
				String salStatus = model.checkSalaryProcessStatus(salProcess, listOfFilters);
				if((salStatus.equals("SAL_FINAL"))){
					addActionMessage("Salary is locked.Please unlock and then recalculate!");			
				}
				else if(salStatus.equals("ATTN_START") || salStatus.equals("ATTN_UNLOCK")){
					addActionMessage("Attendance has been not locked!");
				}else if(salStatus.equals("") || salStatus.equals("null")){
					addActionMessage("Attendance has not been processed!");
				} 
				else {
					String [] dataString = new String [20];
						
						dataString[0] = salProcess.getMonth();
						dataString[1] = salProcess.getYear();
						dataString[2] = salProcess.getLedgerCode();
						dataString[3] = salProcess.getJoinDaysFlag();
						dataString[4] = salProcess.getCreditRound();
						dataString[5] = "";
						dataString[6] = salProcess.getVpfFlag();
						dataString[7] = salProcess.getLwfFlag();
						dataString[8] = "";
						dataString[9] = salProcess.getProfHandiFLag();
						dataString[10] = salProcess.getLwfCreditCode();
						dataString[11] = salProcess.getLwfDebitCode();
						dataString[12] = salProcess.getIncomeTaxFlag();
						dataString[13] = salProcess.getTotalCreditRound();
						dataString[14] = salProcess.getTotalDebitRound();
						dataString[15] = salProcess.getNetPayRound();
						dataString[16] = "N";				//salProcess.getRecoveryFlag();
						dataString[17] = "0";				//salProcess.getRecoveryDebitCode();
						dataString[18] = divEsicFlag; // Division-wise ESIC flag)
						dataString[19]=salProcess.getCompanyPFFlag();
						Object empObj[][] = model.getAttendanceEmployeeToProcess(salProcess.getYear(),salProcess.getLedgerCode(),salProcess.getReProcessEmpCode());
						//empObj[0][0]=salProcess.getEditEmpCode();
						result = model.processSalary(dataString,listOfFilters,empObj,"");
						
						if(result) {
							addActionMessage("Salary recalculated successfully!");
							salProcess.setShowFlag(true);
							salProcess.setLedgerStatus("SAL_START");
							salProcess.setMonthView(Utility.month(Integer.parseInt(salProcess.getMonth())));
							model.saveSalProcessStatus(salProcess.getLedgerCode());
						}else
							addActionMessage("Error in salary processing!");
						
				}
			}else {
				addActionMessage("Salary can not be processed as tables for mentioned year not exists in databse!");
			}
			
		} catch (Exception e) {
			logger.error("Exception in processSalary in action: " + e);
		}
		//setOtherIncomeFlags();
		model.showMiscStatistics(salProcess);
		model.showEmpRecord(salProcess);
		model.terminate();
		return "showEmpRecord";
	}
	
	
	
	public void testMethod() throws IOException{
		String str="1";
		for (int i = 0; i < 500; i++) {
			System.out.println("11111:::"+i);
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    str=String.valueOf(i);
		    out.println(str);	
		}
		
	}
	
}
