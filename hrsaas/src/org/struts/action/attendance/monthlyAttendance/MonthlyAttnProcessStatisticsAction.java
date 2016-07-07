package org.struts.action.attendance.monthlyAttendance;

import org.paradyne.bean.attendance.monthlyAttendance.MonthlyAttnProcessStatistics;
import org.paradyne.model.attendance.monthlyAttendance.MonthlyAttnProcessStatisticsModel;
import org.paradyne.model.attendance.monthlyAttendance.UploadMonthlyAttnStatisticsModel;
import org.struts.lib.ParaActionSupport;
import java.util.Calendar;

public class MonthlyAttnProcessStatisticsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MonthlyAttnProcessStatisticsAction.class);
	
	MonthlyAttnProcessStatistics bean;
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public MonthlyAttnProcessStatistics getBean() {
		return bean;
	}

	public void setBean(MonthlyAttnProcessStatistics bean) {
		this.bean = bean;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean=new MonthlyAttnProcessStatistics();
		bean.setMenuCode(2238);
	}

	
	/**
	 * METHOD FOR INPUT
	 * @return
	 * @throws Exception
	 */
	public String input() throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);		
		model.input(bean,request);
		model.terminate();
		bean.setLedgerCode("");
		bean.setLedgerStatus("");
		getNavigationPanel(1);
		return INPUT;
	}
	
	/**
	 * METHOD FOR INPUT
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);		
		model.input(bean,request);
		model.terminate();
		return INPUT;
	}
	/**
	 * METHOD FOR INPUT
	 * @return
	 * @throws Exception
	 */
	public String callForEdit() throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);		
		getNavigationPanel(3);
		String divisionId = bean.getDivisionId();
		String ledgerCode = bean.getLedgerCode();
		String status = bean.getLedgerStatus();
		/**
		 * SET NAVIGATION PANNEL
		 */
		if(!ledgerCode.equals("")&& (status.equals("ATTN_START")||status.equals("ATTN_UNLOCK"))){
			getNavigationPanel(4);
		}
		if(!ledgerCode.equals("")&& (status.equals("ATTN_READY"))){
			getNavigationPanel(5);
		}
		if(!ledgerCode.equals("")&& (status.equals("SAL_FINAL"))){
			getNavigationPanel(1);
		}
		if(!ledgerCode.equals("")&& (status.equals("SAL_START"))){
			getNavigationPanel(5);
		}
		model.showStatistics(bean);
		
		bean.setOnHoldEmpCode("");
		bean.setOnHoldEmpName("");
		bean.setOnHoldEmpToken("");
		bean.setOnHoldAddEmpCode("");
		bean.setOnHoldAddEmpName("");
		bean.setOnHoldAddEmpToken("");
		bean.setRemoveEmpCode("");
		bean.setRemoveEmpName("");
		bean.setRemoveEmpToken("");
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * METHOD FOR INPUT
	 * @return
	 * @throws Exception
	 */
	public String showStatistics() throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);	
		
		
		String divisionId = bean.getDivisionId();
		String ledgerCode = bean.getLedgerCode();
		String status = bean.getLedgerStatus();
		
		String result=model.showStatistics(bean);
		if(!result.equals("")){
			addActionMessage(result);
		}
		getNavigationPanel(3);
		if(ledgerCode.equals("")){
			getNavigationPanel(2);
		}
		
		/**
		 * SET NAVIGATION PANNEL
		 */
		if(!ledgerCode.equals("")&& (status.equals("ATTN_START")||status.equals("ATTN_UNLOCK"))){
			getNavigationPanel(4);
		}
		if(!ledgerCode.equals("")&& (status.equals("ATTN_READY"))){
			getNavigationPanel(5);
		}		
		model.terminate();
		return SUCCESS;
	}
	
	
	
	/**
	 * METHOD FOR INPUT
	 * @return
	 * @throws Exception
	 */
	public String addNew() throws Exception{
		getNavigationPanel(2);
		return SUCCESS;
	}
	

	
	
	
	public String lockAttendance()throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);
		// Get the filters from a page
		String month = bean.getMonth();
		String year = bean.getYear();
		String branchId = bean.getBranchId();
		String departmentId = bean.getDepartmentId();
		String payBillId = bean.getPayBillId();
		String employeeTypeId = bean.getEmployeeTypeId();
		String divisionId = bean.getDivisionId();
		
		// Set list of filters
		String[] listOfFilters = new String[5];
		listOfFilters[0] = branchId;
		listOfFilters[1] = departmentId;
		listOfFilters[2] = payBillId;
		listOfFilters[3] = employeeTypeId;
		listOfFilters[4] = divisionId;
		getNavigationPanel(5);
		String result=model.lockAttendance(month, year, divisionId, listOfFilters);
		System.out.println("result:"+result);
		if(!result.equals("")){
			addActionMessage(result);
			getNavigationPanel(5);
		}
		else{
			addActionMessage("Attendance Locked Successfully");
		}
		String ledgerCode = bean.getLedgerCode();
		String status = bean.getLedgerStatus();
		
		bean.setEnableAll("Y");
		model.setLedgerStatus(bean);
		model.showStatistics(bean);
		return SUCCESS;
	}
	
	public String unLockAttendance()throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);
		// Get the filters from a page
		String month = bean.getMonth();
		String year = bean.getYear();
		String branchId = bean.getBranchId();
		String departmentId = bean.getDepartmentId();
		String payBillId = bean.getPayBillId();
		String employeeTypeId = bean.getEmployeeTypeId();
		String divisionId = bean.getDivisionId();
		
		// Set list of filters
		String[] listOfFilters = new String[5];
		listOfFilters[0] = branchId;
		listOfFilters[1] = departmentId;
		listOfFilters[2] = payBillId;
		listOfFilters[3] = employeeTypeId;
		listOfFilters[4] = divisionId;
		String result=model.unLockAttendance(month, year, divisionId, listOfFilters);
		if(!result.equals("")){
			addActionMessage(result);
		}
		else{
			addActionMessage("Attendance Locked Successfully");
		}
		String ledgerCode = bean.getLedgerCode();
		String status = bean.getLedgerStatus();
		getNavigationPanel(4);
		bean.setEnableAll("Y");
		model.setLedgerStatus(bean);
		model.showStatistics(bean);
		return SUCCESS;
	}
	
	
	/**
	 * Processes the attendance
	 * @return SUCCESS
	 */
	public String attendanceProcess() {
		try {
			MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
			model.initiate(context, session);
			try {
				String listMonth = getMonth(Integer.parseInt(bean.getMonth()));
				bean.setIttMonthName(listMonth);
			} catch (Exception e) {
				// TODO: handle exception
			}
			// Get the filters from a page
			String month = bean.getMonth();
			String year = bean.getYear();
			String branchId = bean.getBranchId();
			String departmentId = bean.getDepartmentId();
			String payBillId = bean.getPayBillId();
			String employeeTypeId = bean.getEmployeeTypeId();
			String divisionId = bean.getDivisionId();
			String ledgerCode = bean.getLedgerCode();
			String status = bean.getLedgerStatus();
			// Set list of filters
			String[] listOfFilters = new String[5];
			listOfFilters[0] = branchId;
			listOfFilters[1] = departmentId;
			listOfFilters[2] = payBillId;
			listOfFilters[3] = employeeTypeId;
			listOfFilters[4] = divisionId;
			
			
			String result =model.isAttendanceProcess(month, year, listOfFilters);
			if(ledgerCode.equals("")&&!result.equals("")){
				addActionMessage(result);
				return input();
			}
			
			// Process the attendance
			String message = model.attendanceProcess(month, year, divisionId, listOfFilters,bean);
			
			// message whether attendance is processed properly or not
			addActionMessage(message);
			
			//reset();
			getNavigationPanel(4);
			if(!ledgerCode.equals("")&& (status.equals("ATTN_START")||status.equals("ATTN_UNLOCK"))){
				getNavigationPanel(4);
			}
			if(!ledgerCode.equals("")&& (status.equals("ATTN_READY"))){
				getNavigationPanel(5);
			}
			
			/**
			 * SHOW STATISTICS
			 */
			model.showStatistics(bean);
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in attendanceProcess in action: " + e);
		}
		return SUCCESS;
	}

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

	/**
	 * Opens a popup window containing a list of all division
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION WHERE 1=1 ";
			if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
				query += " AND DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY DIV_ID ";

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
	
	/**
	 * Opens a popup window containing a list of all paybill group
	 * @return String f9page
	 */
	public String f9PayBill() {
		try {
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL " +
			" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID)";
			query += getprofilePaybillQuery(bean);
			
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
	
	
	
	
	public String f9action() throws Exception {
		try
		{
			String query="SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, " +
			" LEDGER_YEAR,DIV_NAME,CENTER_NAME,PAYBILL_GROUP, TYPE_NAME,  DEPT_NAME,   LEDGER_STATUS, " +
			" TYPE_ID,PAYBILL_ID,DEPT_ID,CENTER_ID,DIV_ID, LEDGER_CODE,LEDGER_MONTH " +
			"  FROM HRMS_SALARY_LEDGER " +
			"  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID =  HRMS_SALARY_LEDGER.LEDGER_EMPTYPE) " +
			" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL) " +
			" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  HRMS_SALARY_LEDGER.LEDGER_DEPT) " +
			" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  HRMS_SALARY_LEDGER.LEDGER_BRN) " +
			" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_SALARY_LEDGER.LEDGER_DIVISION) " +
			" WHERE LEDGER_STATUS IN ('ATTN_START','ATTN_READY','SAL_START','SAL_FINAL','ATTN_UNLOCK') " ;
			if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
				query += " AND DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
			}
			
			query += " ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC ";

			String[] headers = {getMessage("month"), getMessage("year"), getMessage("division"),getMessage("branch"),getMessage("pay.bill"),getMessage("employee.type"),getMessage("department"),getMessage("status")};

			String[] headerWidth = {"12", "12", "12", "12", "12", "12", "12", "12"};

			String[] fieldNames = {"ittMonthName", "year", "divisionName","branchName","payBillName","employeeTypeName", 
									"departmentName",   "ledgerStatus","employeeTypeId","payBillId","departmentId","branchId","divisionId","ledgerCode","month"};

			int[] columnIndex = {0, 1 ,2, 3, 4 ,5 ,6 ,7,8,9,10,11,12,13,14};

			String submitFlag = "true";

			String submitToMethod = "MonthlyAttnProcessStatistics_callForEdit.action";

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
	 * Popup window contains list of all branches	 * 
	 * @return String f9page
	 */
	public String f9ViewEmpStatistics() {
		try {
			String query = "  ";
			String attMonth=bean.getMonth();
			String attYear=bean.getYear();
			String center=bean.getHBranchCode();
			String salaryStartFlag="";
			String fromDay="";
			UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
			model.initiate(context, session);
			Object[][]flagObj=model.callCheckWorkFlow();
			if(flagObj!=null && flagObj.length>0){
			salaryStartFlag =String.valueOf(flagObj[0][2]);
			fromDay=String.valueOf(flagObj[0][3]);
			}
			if(bean.getHEmpType().equals("T")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_EMP_OFFC	"
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S'  AND EMP_DIV = "+bean.getDivisionId()+" ";
				
				if (salaryStartFlag.equals("P")) {
					String date = "";
					String month = "";
					String Year = "";
					month = bean.getMonth();
					Year = bean.getYear();
					date = fromDay + "-" + month + "-" + Year;
					query += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
							+ "','DD-MM-YYYY')";
				} else {
					query += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
							+ bean.getMonth() + "-" + bean.getYear()
							+ "', 'DD-MM-YYYY'))";
				}				
			}			
			if(bean.getHEmpType().equals("U")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+"  AND EMP_DIV = "+bean.getDivisionId()+" ";
			}
			
			if(bean.getHEmpType().equals("O")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+" AND ATT_EMP_STATUS='O'  AND EMP_DIV = "+bean.getDivisionId()+" ";
			}
						
			if(bean.getHEmpType().equals("P")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_MONTH_ATTENDANCE_"+attYear+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_MONTH_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+"   AND EMP_DIV = "+bean.getDivisionId()+" ";
			
				String selquery = " SELECT HRMS_EMP_OFFC. EMP_ID, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
					+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
					+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
					+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
					+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+" AND IS_REUPLOAD_FLAG='Y'  AND EMP_DIV = "+bean.getDivisionId()+" ";
				if(center.equals("HH")||center.equals("")){
					
				}else{
					selquery+=" AND EMP_CENTER="+bean.getHBranchCode()+" ";
				}
				Object[][]obj=model.getSqlModel().getSingleResult(selquery);
				String empcode="0";
				if(obj!=null && obj.length>0){
					for (int i = 0; i < obj.length; i++) {
						empcode+=","+String.valueOf(obj[i][0]);
					}
					query+= " AND HRMS_EMP_OFFC.EMP_ID NOT IN("+empcode+") ";
				}
				
			}
			if(bean.getHEmpType().equals("NP")){
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
						+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+" AND IS_REUPLOAD_FLAG='Y'  AND EMP_DIV = "+bean.getDivisionId()+" ";
			}
			if(bean.getHEmpType().equals("NI")){				
				//NOT INCLUDED EMPLOYEE=TOTALNOOFEMPLOYEE-ATTENUPLOADEDEMPLOYEE
				String selQuery = " SELECT  HRMS_EMP_OFFC. EMP_ID,EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
					+"	FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"		"
					+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_"+attYear+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "	
					+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
					+"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+"  AND EMP_DIV = "+bean.getDivisionId()+" ";
				if (salaryStartFlag.equals("P")) {
					String date = "";
					String month = "";
					String Year = "";
					month = bean.getMonth();
					Year = bean.getYear();
					date = fromDay + "-" + month + "-" + Year;
					selQuery += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
							+ "','DD-MM-YYYY')";
				} else {
					selQuery += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
							+ bean.getMonth() + "-" + bean.getYear()
							+ "', 'DD-MM-YYYY'))";
				}				
				if(center.equals("HH")||center.equals("")){
					
				}else{
					selQuery+=" AND EMP_CENTER="+bean.getHBranchCode()+" ";
				}
				Object[][]obj=model.getSqlModel().getSingleResult(selQuery);
				
				query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME 	"	
					+"	FROM HRMS_EMP_OFFC	"
					+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
					+"	WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S'  AND EMP_DIV = "+bean.getDivisionId()+" ";
				String empcode="0";
				if(obj!=null && obj.length>0){
					for (int i = 0; i < obj.length; i++) {
						empcode+=","+String.valueOf(obj[i][0]);
					}
					query+= " AND HRMS_EMP_OFFC.EMP_ID NOT IN("+empcode+") ";
				}
			}			
			if (salaryStartFlag.equals("P")) {
				String date = "";
				String month = "";
				String Year = "";
				month = bean.getMonth();
				Year = bean.getYear();
				date = fromDay + "-" + month + "-" + Year;
				query += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
						+ "','DD-MM-YYYY')";
			} else {
				query += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
						+ bean.getMonth() + "-" + bean.getYear()
						+ "', 'DD-MM-YYYY'))";
			}	
			if(bean.isPayBillFlag()){
				query+=" AND HRMS_EMP_OFFC.EMP_PAYBILL="+bean.getPayBillId() ;
			}			
			if(bean.isDepartmentFlag()){
				query+= " AND HRMS_EMP_OFFC.EMP_DEPT= "+bean.getDepartmentId();
			}
			if(bean.isEmployeeTypeFlag()){
				query+= " AND HRMS_EMP_OFFC.EMP_TYPE= "+bean.getEmployeeTypeId();
			}
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			
			if(center.equals("HH")||center.equals("")){
				
			}else{
				query+=" AND EMP_CENTER="+bean.getHBranchCode()+" ";
			}
			query+=" ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ,HRMS_EMP_OFFC. EMP_CENTER";
			String[] headers = {"Employee Id","Employee Name","Branch"};
			String[] headerWidth = {"20","40","20"};
			String[] fieldNames = {"statisticsName", "statisticsCode", "statisticsCenter"};
			int[] columnIndex = {0, 1,2};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:" + e);
			return "";
		}
	}
	
	
	/**
	 * Popup window contains list of all branches
	 * 
	 * @return String f9page
	 */
	public String f9ResignEmpStatistics() {
		try {
			String salaryStartFlag="";
			String fromDay="";
			UploadMonthlyAttnStatisticsModel model = new UploadMonthlyAttnStatisticsModel();
			model.initiate(context, session);
			Object[][]flagObj=model.callCheckWorkFlow();
			if(flagObj!=null && flagObj.length>0){
			salaryStartFlag =String.valueOf(flagObj[0][2]);
			fromDay=String.valueOf(flagObj[0][3]);
			}			
			String query = "  ";
			String attMonth=bean.getMonth();
			String attYear=bean.getYear();
			String center=bean.getHBranchCode();
			String divCode = bean.getDivisionId();
			
			query="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,CENTER_NAME	"	
							+ "	,TO_CHAR(RESIGN_ACCEPT_DATE,'DD-MM-YYYY'),TO_CHAR(RESIGN_SEPR_DATE,'DD-MM-YYYY')	 FROM HRMS_RESIGN	"
							+ "	INNER JOIN HRMS_EMP_OFFC ON (HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC. EMP_ID) "	
							+ "	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)"
							+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC. EMP_DIV)"
							+ "	WHERE HRMS_EMP_OFFC.EMP_STATUS = 'S' AND RESIGN_APPL_STATUS IN ('A','Y') "
							+ " AND DIV_ID= "+divCode;	
			if(bean.isPayBillFlag()){
				query+=" AND HRMS_EMP_OFFC.EMP_PAYBILL="+bean.getPayBillId() ;
			}			 		
			if(bean.isDepartmentFlag()){
				query+= " AND HRMS_EMP_OFFC.EMP_DEPT= "+bean.getDepartmentId();
			}
			if(bean.isEmployeeTypeFlag()){
				query+= " AND HRMS_EMP_OFFC.EMP_TYPE= "+bean.getEmployeeTypeId();
			}
			String toDate = "";
			String fromDate = "";
			String month= bean.getMonth();
			String year=bean.getYear();
			int endDate = Integer.parseInt(fromDay);
			endDate -= 1;
			if (salaryStartFlag.equals("P")) {
			if (month.equals("1")) {
				String DateYear = String.valueOf(Integer.parseInt(year) - 1);
				fromDate = fromDay + "-12-" + DateYear;
			} else {
				String DateMonth = String.valueOf(Integer.parseInt(month) - 1);
				fromDate = fromDay + "-" + DateMonth + "-" + year;
			}
			toDate = endDate + "-" + month + "-" + year;
			query+="AND (RESIGN_ACCEPT_DATE BETWEEN TO_DATE('"+fromDate+"', 'DD-MM-YYYY')  AND TO_DATE('"+toDate+"', 'DD-MM-YYYY') "
						+"  OR(RESIGN_SEPR_DATE BETWEEN TO_DATE('"+fromDate+"', 'DD-MM-YYYY')  AND TO_DATE('"+toDate+"', 'DD-MM-YYYY')) "
						+"	)";
			
			}else{				
			
			//RESIGNED EMPLOYEE
				query+="AND (RESIGN_ACCEPT_DATE >= TO_DATE('01-"+month+"-"+year+"', 'DD-MM-YYYY')  AND RESIGN_ACCEPT_DATE <= LAST_DAY(TO_DATE('01-"+month+"-"+year+"', 'DD-MM-YYYY')) "
			+"  OR(RESIGN_SEPR_DATE >= TO_DATE('01-"+month+"-"+year+"', 'DD-MM-YYYY')  AND RESIGN_SEPR_DATE <= LAST_DAY( TO_DATE('01-"+month+"-"+year+"', 'DD-MM-YYYY'))) "
			+"	)";
			}			
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			if(center.equals("HH")||center.equals("")){
				
			}else{
				query+=" AND EMP_CENTER="+bean.getHBranchCode()+" ";
			}
			query+=" ORDER BY HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC. EMP_CENTER ";
			String[] headers = {"Employee Id","Employee Name","Branch","Resign Accept Date","Resign Separation Date"};

			String[] headerWidth = {"10","30","20","20","20"};

			String[] fieldNames = {"statisticsName", "statisticsCode", "statisticsCenter","resignAcceptDate","resignSeparationDate"};

			int[] columnIndex = {0, 1,2,3,4};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			return "f9page";
			//e.printStackTrace();
			
		}
	}
	
	/* 
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	/**
	 * Call when page loads at first time. Set the filters which are defined in Payroll Setting in Configuration module
	 */
	public void prepare_withLoginProfileDetails() {
		try {
			MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
			model.initiate(context, session);
			
			// Get the filters from back end
			Object[][] attendanceFilters = model.getFilters();
			
			// Set the filters on a page via been 
			bean.setBranchFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][0])));
			bean.setDepartmentFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][1])));
			bean.setPayBillFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][2])));
			bean.setEmployeeTypeFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][3])));
			bean.setDivisionFlag(Boolean.parseBoolean(String.valueOf(attendanceFilters[0][4])));
			
			// Set the current year
			Calendar c = Calendar.getInstance();
			bean.setYear(String.valueOf(c.get(Calendar.YEAR)));
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in prepare_withLoginProfileDetails in action: " + e);
		}
	}

	/**
	 * Reset the fields, set year as current year
	 * @return SUCCESS
	 */
	public String reset() {
		try {
			bean.setMonth("");
			
			// Set the current year
			Calendar c = Calendar.getInstance();
			bean.setYear(String.valueOf(c.get(Calendar.YEAR)));
			
			bean.setDivisionFlag(false);
			bean.setDivisionId("");
			bean.setDivisionName("");
			bean.setBranchFlag(false);
			bean.setBranchId("");
			bean.setBranchName("");
			bean.setDepartmentFlag(false);
			bean.setDepartmentId("");
			bean.setDepartmentName("");
			bean.setPayBillFlag(false);
			bean.setPayBillId("");
			bean.setPayBilName("");
			bean.setEmployeeTypeFlag(false);
			bean.setEmployeeTypeId("");
			bean.setEmployeeTypeName("");
			
			MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
			model.initiate(context, session);
			model.resetToNull(); // reset global variable to null
			model.terminate();
			
			// Set the filters
			prepare_withLoginProfileDetails();
			getNavigationPanel(2);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in reset in action: " + e);
			return "";
		}
	}
	
	/**
	 * Generates a report
	 */
	public void report() {
		try {
			MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
			model.initiate(context, session);
			//String type = bean.getAttdnType();
			// Get the filters from a page
			String month = bean.getMonth();
			String year = bean.getYear();
			String branchId = bean.getBranchId();
			String departmentId = bean.getDepartmentId();
			String payBillId = bean.getPayBillId();
			String employeeTypeId = bean.getEmployeeTypeId();
			String divisionId = bean.getDivisionId();
			
			// Set list of filters
			String[] listOfFilters = new String[5];
			listOfFilters[0] = branchId;
			listOfFilters[1] = departmentId;
			listOfFilters[2] = payBillId;
			listOfFilters[3] = employeeTypeId;
			listOfFilters[4] = divisionId;
				model.getMonthAttendanceReport(bean, response,listOfFilters);// Generates a report for a given month and year
			
			/*if(type.equals("A")) {
				model.annualReport(bean, response);// Generates a report for 12 months starting from a given month and year
			} // end of else statement
*/
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in report in action" + e);
		} // end of try-catch block
	}
	
	public String getMonth(int month)
	{
		String str="";
		switch (month)
		{
		 	case 1 : str="January";
						break;
			case 2 : str= "February";
			break;
			case 3 : str= "March";
			break;
			case 4 : str= "April";
			break;
			case 5 : str= "May";
			break;
			case 6 : str= "June";
			break;
			case 7 : str= "July";
			break;
			case 8 : str= "August";
			break;
			case 9 : str= "September";
			break;
			case 10 : str= "October";
			break;
			case 11 : str= "November";
			break;
			case 12 : str= "December";
		}
		return str;
	}
	
	/**
	 * METHOD TO REMOVE EMPLOYEE
	 * @return
	 * @throws Exception
	 */
	public String removeEmployee() throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);
		boolean flag=model.removeEmployee(bean);
		if(flag){
			addActionMessage("Employee removed successfully");
		}
		model.terminate();
		return callForEdit();
	}
	/**
	 * METHOD TO ADD ONHOLD EMPLOYEE
	 * @return
	 * @throws Exception
	 */
	public String addOnHoldEmployee() throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);
		boolean flag=model.addOnHoldEmployee(bean);
		if(flag){
			addActionMessage("Employee added onhold successfully");
		}
		model.terminate();
		return callForEdit();
	}
	/**
	 * METHOD TO CLEAR ONHOLD EMPLOYEE
	 * @return
	 * @throws Exception
	 */
	public String clearOnHoldEmployee() throws Exception{
		MonthlyAttnProcessStatisticsModel model = new MonthlyAttnProcessStatisticsModel();
		model.initiate(context, session);
		boolean flag=model.clearOnHoldEmployee(bean);
		if(flag){
			addActionMessage("Employee clear onhold successfully");
		}
		model.terminate();
		return callForEdit();
	}
	
	/**
	 * Popup window contains list of all departments
	 * 
	 * @return String f9page
	 */

	public String f9removeEmployee() {
		try {
			String divCode = bean.getDivisionId();
			String month = bean.getMonth();
			String year = bean.getYear();
			String query ="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
				+" ,CENTER_NAME,HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID	FROM HRMS_MONTH_ATTENDANCE_"+year+"		"
				+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
				+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
				+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+"   ";
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			query +="  ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";
			String[] headers = {getMessage("emp.id"), getMessage("emp.Name"),"Branch"};
			String[] headerWidth = {"20", "60","20"};
			String[] fieldNames = {"removeEmpToken", "removeEmpName", "onholdBranchName","removeEmpCode"};
			int[] columnIndex = {0, 1, 2,3};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:");
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * Popup window contains list of all departments
	 * 
	 * @return String f9page
	 */

	public String f9clearOnHoldEmployee() {
		try {
			String divCode = bean.getDivisionId();
			String month = bean.getMonth();
			String year = bean.getYear();
			String query ="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
				+" ,CENTER_NAME,HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID	FROM HRMS_MONTH_ATTENDANCE_"+year+"		"
				+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
				+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
				+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+" AND(EMP_ONHOLD_FLAG ='Y') ";
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			query +="  ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";
			
			String[] headers = {getMessage("emp.id"), getMessage("emp.Name"),"Branch"};
			String[] headerWidth = {"20", "60","20"};
			String[] fieldNames = {"onHoldEmpToken", "onHoldEmpName","onholdBranchName", "onHoldEmpCode"};
			int[] columnIndex = {0, 1, 2,3};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:");
			e.printStackTrace();
			return "";
		}
	}
	public String f9addOnHoldEmployee() {
		try {
			String divCode = bean.getDivisionId();
			String month = bean.getMonth();
			String year = bean.getYear();
			String query ="SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME 	"	
						+" ,CENTER_NAME,HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID	FROM HRMS_MONTH_ATTENDANCE_"+year+"		"
						+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID)" 	
						+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)	"
						+"	WHERE ATTN_MONTH="+month+" AND ATTN_YEAR="+year+"  AND EMP_DIV = "+divCode+" AND(EMP_ONHOLD_FLAG !='Y') ";
			if(!bean.getBranchId().equals("")){
				query +=" AND HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
			}
			query +="  ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";
			String[] headers = {getMessage("emp.id"), getMessage("emp.Name"),"Branch"};
			String[] headerWidth = {"20", "60","20"};
			String[] fieldNames = {"onHoldAddEmpToken", "onHoldAddEmpName","onholdBranchName", "onHoldAddEmpCode"};
			int[] columnIndex = {0, 1, 2,3};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Branch:");
			e.printStackTrace();
			return "";
		}
	}
	
}
