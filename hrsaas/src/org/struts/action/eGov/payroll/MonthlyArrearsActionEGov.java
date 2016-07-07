/**
 * 
 */
package org.struts.action.eGov.payroll;

import java.io.PrintWriter;
import java.util.Calendar;

import org.paradyne.bean.eGov.payroll.MonthlyArrearsEGov;
import org.paradyne.lib.Utility;
import org.paradyne.model.eGov.attendance.MonthlyArrearsModelEGov;
import org.paradyne.model.eGov.payroll.SalaryProcessModeleGov;
import org.struts.lib.ParaActionSupport;

/**
 * @author Shashikant
 * Date 06 July 2011
 */
public class MonthlyArrearsActionEGov extends ParaActionSupport {

	MonthlyArrearsEGov monthlyArrears;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(MonthlyArrearsActionEGov.class);
	public void prepare_local() throws Exception {
		monthlyArrears=new MonthlyArrearsEGov();
		monthlyArrears.setMenuCode(2110);
	}
	
	public MonthlyArrearsEGov getMonthlyArrears() {
		return monthlyArrears;
	}

	public void setMonthlyArrears(MonthlyArrearsEGov monthlyArrears) {
		this.monthlyArrears = monthlyArrears;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return monthlyArrears;
	}

	@Override
	/**
	 * @author REEBA_JOSEPH
	 */
	public String input() throws Exception {
		MonthlyArrearsModelEGov model = new MonthlyArrearsModelEGov();
		model.initiate(context, session);
		model.displayList(monthlyArrears, request);
		model.terminate();
		getNavigationPanel(1);
		monthlyArrears.setEnableAll("Y");
		return INPUT;
	}
	
	/**
	 * @author REEBA_JOSEPH
	 * @return
	 * @throws Exception
	 */
	public String addNew() throws Exception {
		reset();
		monthlyArrears.setAddFlag(true);
		getNavigationPanel(2);
		monthlyArrears.setEnableAll("Y");
		return SUCCESS;
	}//end of addNew method
	
	/**
	 * @author REEBA_JOSEPH
	 * @return
	 * @throws Exception
	 */
	public String callForEdit() throws Exception{
		monthlyArrears.setFilterFlag(true);
		monthlyArrears.setAddFlag(false);
		logger.info("getPayinSalFlag="+monthlyArrears.getPayinSalFlag());
		getNavigationPanel(3);
		monthlyArrears.setEnableAll("N");
		return SUCCESS;
	}
	
	/**
	 * @author REEBA_JOSEPH
	 * @return
	 */
	public String showFilters()
	{
		try {
			if(String.valueOf(monthlyArrears.getArrearPayType()).trim().equals("ADD"))
					monthlyArrears.setArrearPayTypeName("ADDITIVE");
			else
				monthlyArrears.setArrearPayTypeName("DEDUCTIVE");
			monthlyArrears.setFilterFlag(true);
			monthlyArrears.setAddFlag(false);
			getNavigationPanel(3);
			monthlyArrears.setEnableAll("N");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Error in promotion arrears "+e);
			return SUCCESS;
		}
	} // end of method
	
	/**
	 * @author REEBA_JOSEPH
	 * @return
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
	 * @author REEBA_JOSEPH
	 * @return
	 */
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
	
	/**
	 * @author REEBA_JOSEPH
	 * @return
	 */
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
	 * @author REEBA_JOSEPH
	 * @return
	 */
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
	 * @author REEBA_JOSEPH
	 * @return
	 * @throws Exception
	 */
	public String f9employeeView() throws Exception 
	{
		try
		{
						
			String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, " 
				+ " DECODE(EMP_STATUS, 'S','Service','R','Retired','N','Resigned','E','Terminated') ,EMP_ID" 
				+ " FROM HRMS_EMP_OFFC " 
				+ " WHERE EMP_ID IN (SELECT DISTINCT HRMS_ARREARS_"+monthlyArrears.getArrRefYear()+".EMP_ID FROM HRMS_ARREARS_"+monthlyArrears.getArrRefYear()
				+ " WHERE ARREARS_CODE = "+monthlyArrears.getArrCode()+") "
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
	
	public String resetList() {
		try {
			monthlyArrears.setDepartmentViewId("");
			monthlyArrears.setDepartmentViewName("");
			monthlyArrears.setBranchViewId("");
			monthlyArrears.setBranchViewName("");
			monthlyArrears.setEmployeeTypeViewId("");
			monthlyArrears.setEmployeeTypeViewName("");
			monthlyArrears.setPayBillViewId("");
			monthlyArrears.setPayBillViewName("");
			monthlyArrears.setEmpViewId("");
			monthlyArrears.setEmpViewName("");
			monthlyArrears.setEmpTokenView("");
			monthlyArrears.setEmpStatusView("");
			//UPDATED BY REEBA
			showFilters();
			
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Error in promotion arrears "+e);
			return SUCCESS;
		}
	} // end of method

	// action on select employee
	public String f9Employee() 
	{
		try {
			String query = " SELECT EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS NAME, "
					+ " EMP_ID FROM HRMS_EMP_OFFC ";
			query += getprofileQuery(monthlyArrears);
			query += " AND  EMP_STATUS = 'S'";
			query = setFilters(monthlyArrears, query) + " ORDER BY UPPER(NAME)";
			String headers[] = { getMessage("employee.id"),
					getMessage("employee") };
			String headerWidth[] = { "10", "40" };
			String fieldNames[] = { "empToken", "empName", "empId" };
			int columnIndex[] = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	// this method gets call while loading
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			MonthlyArrearsModelEGov model = new MonthlyArrearsModelEGov();
			model.initiate(context, session);
			model.getFilters(monthlyArrears);
			model.terminate();
			logger.info("In action after caling");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// action on add button to add employees to the list 
	public String processArrears() 
	{
		try {
			//ADDED BY REEBA - BEGINS
			SalaryProcessModeleGov salModel = new SalaryProcessModeleGov();
			salModel.initiate(context, session);
			// Get the filters from back end
			Object[][] salaryFilters = salModel.getPayrollConfig();
			salModel.terminate();
			
			Object roundParam[][] = new Object[1][4];
			roundParam[0][0] = salaryFilters[0][11];//creditRound
			roundParam[0][1] = salaryFilters[0][12];//totalCreditRound
			roundParam[0][2] = salaryFilters[0][13];//totalDebitRound
			roundParam[0][3] = salaryFilters[0][14];//netPayRound
			//ADDED BY REEBA - ENDS
			
			
			MonthlyArrearsModelEGov model = new MonthlyArrearsModelEGov();
			
			model.initiate(context, session);
			//fetch the exisiting employee from list
			String[] empId = request.getParameterValues("emp_id"); // EMPLOYEES ID
			Object[][] rows = null;
			Object[][]leaveRecObjNew=null;
			leaveRecObjNew=new Object[1][3];
			leaveRecObjNew[0][0]=monthlyArrears.getArrearFor();//ARREAR FOR
			leaveRecObjNew[0][1]=monthlyArrears.getLeaveRecoveryCode();//LEAVE RECOVERY
			leaveRecObjNew[0][2]=monthlyArrears.getLeaveRecoveryName();//LEAVE RECOVERY Name
			Object [][]leaveRecObj=null;
			if(empId != null && empId.length > 0)
			{
				leaveRecObj=new Object[empId.length][3];
				Object d[][] = model.getDebitHeader(); // TO CALL ALL DEBITS 
				Object c[][] = model.getCreditHeader();
				String[] empToken = request.getParameterValues("eToken");
				String[] empName = request.getParameterValues("eName");
				String[] period = request.getParameterValues("period");
				String totalCredit[] = request.getParameterValues("totalCredit");
				String totalDebit[] = request.getParameterValues("totalDebit");
				String arrearsDays[] = request.getParameterValues("salDays");
				String eligDays[] = request.getParameterValues("eligDays");
				String netPay[] = request.getParameterValues("netPay");
				String forMonth[] = request.getParameterValues("hMonth");
				String forYear[] = request.getParameterValues("year");
				
				String hArrearFor[] = request.getParameterValues("hArrearFor");
				String hLeaveRecoveryCode[] = request.getParameterValues("hLeaveRecoveryCode");
				String hLeaveRecoveryName[] = request.getParameterValues("hLeaveRecoveryName");
				
				rows = new Object[empId.length][c.length+d.length+11];
				//0-Arrear for,1-Leave Recovery
				
				
				Object[][] credit_debit = new Object[empId.length][d.length + c.length];
				for (int i = 0; i < empId.length; i++) {
					/**
					 * FOR GETTING CREDIT AND DEBIT FROM JSP
					 */
					credit_debit[i] = request.getParameterValues(String.valueOf(i)); // WILL GET ALL CREDITS AND DEBIT
				}
				//iterate employee object
				for (int i = 0; i < empId.length; i++) {
							rows[i][0] = empId[i];
							rows[i][1] = empToken[i];
							rows[i][2] = empName[i];
							int j,m=0;
							for (j = 0; j < c.length+d.length; j++) {
								if(j==c.length)
									rows[i][j+3] = totalCredit[i];
								else 
								{
									rows[i][j+3] = credit_debit[i][m];
									m++;
								}
							}
							rows[i][j+3] = credit_debit[i][m];
							rows[i][j+4] = totalDebit[i];
							rows[i][j+5] = netPay[i];
							rows[i][j+6] = arrearsDays[i];
							rows[i][j+7] = period[i];
							rows[i][j+8] = forMonth[i];
							rows[i][j+9] = forYear[i];
							rows[i][j+10] = eligDays[i];
							
							//SET ARREAR FOR AND RECOVERY
							leaveRecObj[i][0]=hArrearFor[i];
							leaveRecObj[i][1]=hLeaveRecoveryCode[i];
							leaveRecObj[i][2]=hLeaveRecoveryName[i];
				}
			}
			Object[][] resultData = null;
			Object[][] finalData = null;
			
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
				poolName = "/" + poolName;
			}
			String path = getText("data_path") + "/datafiles/" + poolName
			+ "/xml/Payroll/";
			double salDaysValue=0;
			
			//if reflecting month salary is locked 
			// record cant be added
			if(model.chkSalFinalize(monthlyArrears))
			{
				finalData = rows;
				addActionMessage("Salary for the month "+Utility.month((Integer.parseInt(monthlyArrears.getArrRefMonth())))
						+"-"+monthlyArrears.getArrRefYear()+" has been finalized. So monthly arrears cannot be added or modified");
				request.setAttribute("rows", null);
				model.terminate();
				getNavigationPanel(2);
				return SUCCESS;
			}
			// if salary is processed for reflecting month
			// if salary not processed for that employee
			// it checks for joining date 
			else if(!model.chkSalProcess(monthlyArrears) && !model.chkJoiningDate(monthlyArrears))
			{
				finalData = rows;
				addActionMessage("Salary for "+monthlyArrears.getEmpName()+" for the month "+Utility.month((Integer.parseInt(monthlyArrears.getArrMonth())))
						+"-"+monthlyArrears.getArrYear()+" has not been processed. So monthly arrears cannot be added");
				model.setHeaders(monthlyArrears, request);
				request.setAttribute("rows", rows);
				request.setAttribute("leaveRecObj", leaveRecObj);
				reset_Emp();
				model.terminate();
				getNavigationPanel(2);
				return SUCCESS;
			} 
			else
			{
				// if arrears already processed
				if(!model.chkArrProcessed(monthlyArrears))
				{
					// processing for selected employee - UPDATED BY REEBA
					//resultData = model.processArrears(request, monthlyArrears,path, roundParam,monthlyArrears.getDivEsicZone());
					resultData = model.processArrears(request, monthlyArrears,path, roundParam);
					Calendar cal = Calendar.getInstance();
					cal.setTime(Utility.getDate(01 + "-" + monthlyArrears.getArrMonth() + "-" + monthlyArrears.getArrYear()));
					double daysOfMonth = Double.parseDouble(String.valueOf(cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)));
					if(!monthlyArrears.getSalDays().equals(""))
						salDaysValue = Double.parseDouble(monthlyArrears.getSalDays());
					if(monthlyArrears.getJoinDate() != null && !monthlyArrears.getJoinDate().equals(""))
					{
						String msgStr="Joining date for "+monthlyArrears.getEmpName()
						+" is "+monthlyArrears.getJoinDate();
						if(salDaysValue>0.0)
							msgStr+=" and already received salary for "+salDaysValue+" days.";
						msgStr+=" \nSo arrears days cannot exceed "+monthlyArrears.getArrDays()+" days.";
						addActionMessage(msgStr);
						request.setAttribute("rows", rows);
						request.setAttribute("leaveRecObj", leaveRecObj);
						model.terminate();
						getNavigationPanel(2);
						return SUCCESS;
					}
					if(salDaysValue!=0){
						logger.info("Org Arr Days "+monthlyArrears.getOrgArrDays());
						if(Double.parseDouble((String.valueOf(monthlyArrears.getOrgArrDays()))) > (daysOfMonth - salDaysValue)){
							logger.info("In Arr Days > daysOfMonth - salDaysValue" );
							double arrDays=  daysOfMonth - salDaysValue;
							addActionMessage("Already received salary for "+salDaysValue+" days. \nSo arrear days should not exceed "+arrDays+" days");
							request.setAttribute("rows", rows);
							request.setAttribute("leaveRecObj", leaveRecObj);
							model.terminate();
							getNavigationPanel(2);
							return SUCCESS;
						}
					}
					else if(monthlyArrears.getJoinDate() != null && !monthlyArrears.getJoinDate().equals(""))
					{
						addActionMessage("Joining date for "+monthlyArrears.getEmpName()
								+" is "+monthlyArrears.getJoinDate()+" so arrears days cannot exceed "+monthlyArrears.getArrDays()+" days.");
						request.setAttribute("rows", rows);
						request.setAttribute("leaveRecObj", leaveRecObj);
						model.terminate();
						getNavigationPanel(2);
						return SUCCESS;
					}
						if (rows != null) {
							finalData = new Object[rows.length + resultData.length][rows[0].length];
							finalData = org.paradyne.lib.Utility.joinArrays(resultData,
									rows, 1, 0); // join both arrays current employee and
													// previous employees
							logger.info("in if rows!= null");
						} else { // if it the first one
							finalData = new Object[resultData.length][resultData[0].length];
							for (int i = 0; i < resultData.length; i++) 
								for (int j = 0; j < resultData[0].length; j++) 
									finalData[i][j] = resultData[i][j];
							logger.info("In else first record");
						}
						if(leaveRecObj!=null && leaveRecObj.length>0)
						leaveRecObjNew =Utility.joinArrays(leaveRecObjNew,leaveRecObj, 1, 0);
						request.setAttribute("rows", finalData);
						request.setAttribute("leaveRecObj", leaveRecObjNew);
						if(rows != null || finalData!= null)
							monthlyArrears.setFlag("true");
						reset_Emp();
						model.terminate();
						getNavigationPanel(2);
						return SUCCESS;
				}
				else
				{
					addActionMessage("Arrears for "+Utility.month((Integer.parseInt(monthlyArrears.getArrRefMonth())))
							+"-"+monthlyArrears.getArrRefYear()+" has been already saved. To add or view the record click search");
					request.setAttribute("rows", rows);
					request.setAttribute("leaveRecObj", leaveRecObj);
					model.terminate();
					reset_Emp();
					getNavigationPanel(2);
					return SUCCESS;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
	}
	
	// to reset employee fields in order to add other employee
	public void reset_Emp()
	{
		monthlyArrears.setEmpId("");
		monthlyArrears.setEmpName("");
		monthlyArrears.setArrDays("");
		monthlyArrears.setArrMonth("");
		monthlyArrears.setArrYear("");
		monthlyArrears.setArrearFor("A");
		monthlyArrears.setLeaveRecoveryCode("");
		monthlyArrears.setLeaveRecoveryName("");
	}
	
	// to reset the whole application
	public String reset()
	{
		reset_Emp();
		monthlyArrears.setFlag("false");
		monthlyArrears.setArrCode("");
		monthlyArrears.setArrMonth("");
		monthlyArrears.setArrYear("");
		monthlyArrears.setArrRefMonth("");
		monthlyArrears.setArrRefYear("");
		monthlyArrears.setDivCode("");
		monthlyArrears.setDivName("");
		monthlyArrears.setTypeCode("");
		monthlyArrears.setTypeName("");
		monthlyArrears.setDeptCode("");
		monthlyArrears.setDeptName("");
		monthlyArrears.setBrnCode("");
		monthlyArrears.setBrnName("");
		monthlyArrears.setPayBillNo("");
		monthlyArrears.setPayBillName("");
		monthlyArrears.setArrearPayType("ADD");
		monthlyArrears.setArrearFor("A");
		monthlyArrears.setLeaveRecoveryCode("");
		monthlyArrears.setLeaveRecoveryName("");
		//monthlyArrears.setDivEsicZone("N");
		getNavigationPanel(2);
		return SUCCESS;
	}

	// action called on save button
	public String saveArrears() {
		try {
			MonthlyArrearsModelEGov model = new MonthlyArrearsModelEGov();
			model.initiate(context, session);
			Object d[][] = model.getDebitHeader(); // TO CALL ALL DEBITS 
			Object c[][] = model.getCreditHeader(); // TO CALL ALL CREDITS 
			String[] emp_id = request.getParameterValues("emp_id"); // EMPLOYEES ID
			logger.info("month 1 : "+monthlyArrears.getMonthRef());
			logger.info("month 2 : "+monthlyArrears.getArrRefMonth());
			//monthlyArrears.setArrRefMonth(monthlyArrears.getMonthRef());
			
			if(model.chkSalFinalize(monthlyArrears))
			{
				addActionMessage("Salary for "+Utility.month((Integer.parseInt(monthlyArrears.getArrRefMonth())))
						+"-"+monthlyArrears.getArrRefYear()+" has been finalised. So monthly arrears cannot be added or modified.");
			}
			else if(emp_id != null && emp_id.length > 0)
			{
				String total_credit[] = request.getParameterValues("totalCredit");
				String total_debit[] = request.getParameterValues("totalDebit");
				String sal_days[] = request.getParameterValues("salDays");
				String netPay[] = request.getParameterValues("netPay");
				String forMonth[] =  request.getParameterValues("hMonth");
				String forYear[] = request.getParameterValues("year");
				String eligDays[] = request.getParameterValues("eligDays");
				String arrearFor[] = request.getParameterValues("hArrearFor");
				String leaveRecoveryCode[] = request.getParameterValues("hLeaveRecoveryCode");
				String month = monthlyArrears.getArrRefMonth();
				String year = monthlyArrears.getArrRefYear();
				Object[][] rows = new Object[emp_id.length][d.length + c.length];
				for (int i = 0; i < emp_id.length; i++) {
					/**
					 * FOR GETTING CREDIT AND DEBIT FROM JSP
					 * WILL GET ALL CREDITS AND DEBIT
					 */
					rows[i] = request.getParameterValues(String.valueOf(i)); 
				}
				//save method call passing all employee id, credit,debit, total, netsal, total, days, month, year
				int saveType = model.save(rows, d, c, emp_id, month, year,
						total_credit, total_debit, sal_days, monthlyArrears,netPay,forMonth,forYear,eligDays,arrearFor,leaveRecoveryCode);
				if (saveType == 1)
					addActionMessage("Arrears saved successfully");
				else if(saveType == 2)
					addActionMessage("Arrears modified successfully");
				else
					addActionMessage("Error while saving arrears");
			}
			else
			{
				if(model.chkArrProcessed(monthlyArrears))
				{
					addActionMessage("Arrears for "+Utility.month((Integer.parseInt(monthlyArrears.getArrRefMonth())))
							+"-"+monthlyArrears.getArrRefYear()+" has been already saved. To view or add the record click search");
				}
				else
				{
					int saveType = model.saveWithoutRecord(monthlyArrears);
					if(saveType == 1)
						addActionMessage("Arrears saved successfully");
					else if(saveType == 2)
						addActionMessage("Arrears modified successfully");
					else
						addActionMessage("Error while saving arrears");
				}
			}
			model.terminate();
			reset();
			getNavigationPanel(1);
			monthlyArrears.setEnableAll("Y");
			return input();
		} catch (Exception e) {
			addActionMessage("Error While Saving Arrears");
			e.printStackTrace();
			return SUCCESS;
		}

	}
	
	//methods to view records according to month and selected year
	public String viewRecords()
	{
		try {
			logger.info("month 1 : "+monthlyArrears.getMonthRef());
			logger.info("month 2 : "+monthlyArrears.getArrRefMonth());
			//monthlyArrears.setArrRefMonth(monthlyArrears.getMonthRef());
			monthlyArrears.setAddFlag(true);
			MonthlyArrearsModelEGov model = new MonthlyArrearsModelEGov();
			model.initiate(context, session);
			logger.info("In Action " + monthlyArrears.getArrCode());
			String[] listOfFilters = new String[5];
			listOfFilters[0] = monthlyArrears.getBranchViewId();
			listOfFilters[1] = monthlyArrears.getDepartmentViewId();
			listOfFilters[2] = monthlyArrears.getPayBillViewId();
			listOfFilters[3] = monthlyArrears.getEmployeeTypeViewId();
			listOfFilters[4] = monthlyArrears.getDivCode();
			String empFilterQuery = model.setEmpFiletrs(listOfFilters);
			if(!monthlyArrears.getEmpViewId().equals("")){
				empFilterQuery +=" AND HRMS_EMP_OFFC.EMP_ID ="+monthlyArrears.getEmpViewId();
			}
			Object rows[][] = model.showArrearRecords(request, monthlyArrears, empFilterQuery);
			request.setAttribute("rows", rows);
			monthlyArrears.setFlag("true");
			if(model.chkSalFinalize(monthlyArrears))
				monthlyArrears.setSalStatus("L");
			else
				monthlyArrears.setSalStatus("P");
			model.terminate();
			logger.info("month 1 : "+monthlyArrears.getArrRefMonth());
			//monthlyArrears.setArrRefMonth(Utility.month(Integer.parseInt(monthlyArrears.getArrRefMonth())));
			getNavigationPanel(2);
			monthlyArrears.setFilterFlag(true);
			monthlyArrears.setEnableAll("Y");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
	}
	
	public String delete()
	{
		try {
			MonthlyArrearsModelEGov model = new MonthlyArrearsModelEGov();
			model.initiate(context, session);
			if (model.delete(monthlyArrears))
				addActionMessage("Record Deleted Successfully");
			else
				addActionMessage("Error While Deleting");
			reset();
			//UPDATED BY REEBA
			return input();
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
	}
	
	// this action gets the call on search button
	public String f9Search()
	{
		try {
			String query = " SELECT TO_CHAR(TO_DATE(ARREARS_REF_MONTH,'MM'),'MONTH') , "
						  +" ARREARS_REF_YEAR,HRMS_DIVISION.DIV_NAME,HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, "
						  +" HRMS_EMP_TYPE.TYPE_NAME,HRMS_PAYBILL.PAYBILL_GROUP,ARREARS_REF_MONTH, ARREARS_CODE,NVL(ARREARS_PAY_TYPE,'ADD'),DECODE(ARREARS_PAY_IN_SAL,'N','false','true') FROM HRMS_ARREARS_LEDGER "
						  +" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_ARREARS_LEDGER.ARREARS_DIVISION) "
						  +" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_ARREARS_LEDGER.ARREARS_BRANCH) "
						  +" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_ARREARS_LEDGER.ARREARS_DEPARTMENT) "
						  +" LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_ARREARS_LEDGER.ARREARS_EMPTYPE) "
						  +" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_ARREARS_LEDGER.ARREARS_PAYBILL) "
						  +" WHERE ARREARS_TYPE = 'M'";
			
			if(monthlyArrears.getUserProfileDivision() != null && monthlyArrears.getUserProfileDivision().length() > 0) {
				query += " AND DIV_ID IN(" + monthlyArrears.getUserProfileDivision() + ") ";
			}
			query +=" ORDER BY ARREARS_REF_YEAR DESC, ARREARS_REF_MONTH DESC";
			
	/*		try {
				String query = " SELECT TO_CHAR(TO_DATE(ARREARS_REF_MONTH,'MM'),'MONTH') , "
							  +" ARREARS_REF_YEAR,HRMS_DIVISION.DIV_NAME,HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, "
							  +" HRMS_EMP_TYPE.TYPE_NAME,HRMS_PAYBILL.PAYBILL_GROUP,ARREARS_REF_MONTH, ARREARS_CODE,NVL(ARREARS_PAY_TYPE,'ADD'),NVL(DIV_ESI_ZONE,'N') FROM HRMS_ARREARS_LEDGER "
							  +" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_ARREARS_LEDGER.ARREARS_DIVISION) "
							  +" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_ARREARS_LEDGER.ARREARS_BRANCH) "
							  +" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_ARREARS_LEDGER.ARREARS_DEPARTMENT) "
							  +" LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_ARREARS_LEDGER.ARREARS_EMPTYPE) "
							  +" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_ARREARS_LEDGER.ARREARS_PAYBILL) "
							  +" WHERE ARREARS_TYPE = 'M'"
							  +" ORDER BY ARREARS_REF_YEAR DESC, ARREARS_REF_MONTH DESC";*/

			String[] headers = {getMessage("reflecting.month"),getMessage("reflecting.year"),getMessage("division"),getMessage("branch"),getMessage("department"),getMessage("employee.type"),getMessage("pay.bill")};

			String[] headerWidth = {"10","10","15","15","15","10","10"};

		/*	//UPDATED BY REEBA
			String[] fieldNames = {"monthRef","arrRefYear","divName","brnName","deptName","typeName","payBillName","arrRefMonth","arrCode","monthRef","arrearPayType","divEsicZone"};
			//eArrMonth
			int[] columnIndex = {0,1,2,3,4,5,6,7,8,7,9,10};*/

			//UPDATED BY REEBA
			String[] fieldNames = {"monthRef","arrRefYear","divName","brnName","deptName","typeName","payBillName","arrRefMonth","arrCode","monthRef","arrearPayType","payinSalFlag"};
			//eArrMonth
			int[] columnIndex = {0,1,2,3,4,5,6,7,8,7,9,10};
			
			String submitFlag = "true";

			//String submitToMethod = "MonthlyArrears_viewRecords.action";
			//UPDATED BY REEBA
			//String submitToMethod = "MonthlyArrears_showFilters.action";
			
			String submitToMethod = "MonthlyArrearsEGov_viewRecords.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	
	/**
	 * @author REEBA_JOSEPH
	 * Set filters while calculating arrears
	 * @param monthlyArrears
	 * @param sqlQuery
	 * @return
	 */
	public String setFilters(MonthlyArrearsEGov monthlyArrears, String sqlQuery)  
	{
		try
		{
			String typeCode = monthlyArrears.getTypeCode();
			String payBillNo = monthlyArrears.getPayBillNo();
			String brnCode = monthlyArrears.getBrnCode();
			String deptCode = monthlyArrears.getDeptCode();
			String divCode = monthlyArrears.getDivCode();
			
			if(!divCode.equals(""))
			{
				sqlQuery += " AND EMP_DIV = "+divCode;
				monthlyArrears.setEmpChkFlag("true");
			}
			if(!typeCode.equals(""))
			{
				sqlQuery += " AND EMP_TYPE = "+typeCode;
				monthlyArrears.setEmpChkFlag("true");
			}
			if(!payBillNo.equals(""))
			{
				sqlQuery += " AND EMP_PAYBILL = "+payBillNo;
				monthlyArrears.setEmpChkFlag("true");
			}
			if(!brnCode.equals(""))
			{
				sqlQuery += " AND EMP_CENTER = "+brnCode;
				monthlyArrears.setEmpChkFlag("true");
			}
			if(!deptCode.equals(""))
			{
				sqlQuery += " AND EMP_DEPT = "+deptCode;
				monthlyArrears.setEmpChkFlag("true");
			}
			return sqlQuery;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"brnName", "brnCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Branch
	
	/**
	 * Popup window contains list of all departments
	**/
	/**
	 * @return String f9page
	**/
	public String f9Dept()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptName", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Dept
	
	/**
	 * Popup window contains list of all division
	**/
	/**
	 * @return String f9page
	**/
	public String f9Div()
	{
		try
		{
			/*String query = " SELECT DIV_NAME, DIV_ID,NVL(DIV_ESI_ZONE,'N') FROM HRMS_DIVISION ";*/
			
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			
			if(monthlyArrears.getUserProfileDivision() !=null && monthlyArrears.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+monthlyArrears.getUserProfileDivision() +")"; 
				query+= " ORDER BY UPPER(DIV_NAME) ";
			 
			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			/*String[] fieldNames = {"divName", "divCode","divEsicZone"};

			int[] columnIndex = {0,1,2};*/
			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Div
	
	/**
	 * Popup window contains list of all employee types
	**/
	/**
	 * @return String f9page
	**/
	public String f9EmpType()
	{
		try
		{
			String query = " SELECT TYPE_NAME, TYPE_ID FROM HRMS_EMP_TYPE ";

			String[] headers = {getMessage("employee.type")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"typeName", "typeCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block
	} //end of f9EmpType
	
	//f9LeaveRecovery
	/**
	 * Popup window contains list of all employee types
	**/
	/**
	 * @return String f9page
	**/
	public String f9LeaveRecovery()
	{
		try
		{
			String query = " SELECT LEAVE_ABBR,LEAVE_ID FROM HRMS_LEAVE ORDER BY LEAVE_ID ";

			String[] headers = {"Leave Type"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"leaveRecoveryName", "leaveRecoveryCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block
	} //end of f9EmpType
	
	/**
	 * Popup window contains list of all paybill group
	**/
	/**
	 * @return String f9page
	**/
	public String f9PayBill()
	{
		try
		{
			String query = " SELECT DISTINCT PAYBILL_GROUP, PAYBILL_ID FROM HRMS_PAYBILL "
			+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL = PAYBILL_ID) ";
			query +=getprofilePaybillQuery(monthlyArrears);
			
			String[] headers = {getMessage("pay.bill"), getMessage("billno")};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"payBillName", "payBillNo"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		} //end of try-catch block		
	} //end of f9PayBill
	
	public void recalculate()
	{
		try {
			//ADDED BY REEBA - BEGINS
			SalaryProcessModeleGov salModel = new SalaryProcessModeleGov();
			salModel.initiate(context, session);
			// Get the filters from back end
			Object[][] salaryFilters = salModel.getPayrollConfig();
			salModel.terminate();
			
			Object roundParam[][] = new Object[1][4];
			roundParam[0][0] = salaryFilters[0][11];//creditRound
			roundParam[0][1] = salaryFilters[0][12];//totalCreditRound
			roundParam[0][2] = salaryFilters[0][13];//totalDebitRound
			roundParam[0][3] = salaryFilters[0][14];//netPayRound
			//ADDED BY REEBA - ENDS
			
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "/" + poolName;
			}
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/xml/Payroll/";
			MonthlyArrearsModelEGov model = new MonthlyArrearsModelEGov();
			model.initiate(context, session);
			String recalChkEmp[] = request.getParameterValues("chkEmp");
			String recalEmp[][] = new String[recalChkEmp.length][6];
			String temp[] = new String[7];
			String empId[] = new String[recalChkEmp.length];
			String rowNo[] = new String[recalChkEmp.length];
			String responseText = "";
			for (int i = 0; i < recalChkEmp.length; i++) {
				System.out.println("recalChkEmp[i] :"+recalChkEmp[i]);
				temp = recalChkEmp[i].split("&");
				recalEmp[i][0] = temp[0];
				recalEmp[i][1] = temp[1];
				recalEmp[i][2] = temp[2];
				recalEmp[i][3] = temp[3];
				
				recalEmp[i][4] = temp[5];//arrear for
				recalEmp[i][5] = temp[6];//leave recovery
				
				empId[i] = temp[0];
				rowNo[i] = temp[4];
			}
			for (int i = 0; i < recalEmp[0].length; i++) {
				logger.info("Value : "+recalEmp[0][i]);
			}
			/*Object [][]rowsObj = model.recalculate(recalEmp, path,
					monthlyArrears, roundParam,monthlyArrears.getDivEsicZone());*/
			
			Object [][]rowsObj = model.recalculate(recalEmp, path,
					monthlyArrears, roundParam);
			
			Object recalArrObj[][] = new Object[rowsObj.length][rowsObj[0].length + 1];
			logger.info("Rows Col Length ------------------ "+rowsObj[0].length);
			logger.info("Recal Col Length ------------------ "+recalArrObj[0].length);
			for (int i = 0; i < rowsObj.length; i++) {
				for (int j = 0; j < rowsObj[0].length; j++) {
					recalArrObj[i][j] = rowsObj[i][j];
					recalArrObj[i][rowsObj[0].length] = rowNo[i];
				}
			}
			for (int i = 0; i < recalArrObj.length; i++) {
				responseText += empId[i] + "#";
				for (int j = 0; j < recalArrObj[0].length; j++) {
					if (j == (recalArrObj[0].length - 1)) {
						responseText = responseText
								+ String.valueOf(recalArrObj[i][j]);
					} else
						responseText = responseText
								+ String.valueOf(recalArrObj[i][j]) + "#";
				} // end inner for loop'
				if (i == (recalArrObj.length - 1)) {
				} else
					responseText = responseText + "@";
			} // end outer for loop
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseText);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception in monthly arrears action "+e);
		}
	}
	
	public String removeRecord()
	{
		try {
			MonthlyArrearsModelEGov model = new MonthlyArrearsModelEGov();
			model.initiate(context, session);
			Object d[][] = model.getDebitHeader(); // TO CALL ALL DEBITS 
			Object c[][] = model.getCreditHeader();
			String[] empId = request.getParameterValues("emp_id"); // EMPLOYEES ID
			String[] empToken = request.getParameterValues("eToken");
			String[] empName = request.getParameterValues("eName");
			String[] period = request.getParameterValues("period");
			String totalCredit[] = request.getParameterValues("totalCredit");
			String totalDebit[] = request.getParameterValues("totalDebit");
			String arrDays[] = request.getParameterValues("salDays");
			String eligDays[] = request.getParameterValues("eligDays");
			String netPay[] = request.getParameterValues("netPay");
			String forMonth[] = request.getParameterValues("hMonth");
			String forYear[] = request.getParameterValues("year");
			String[] removeEmp = request.getParameterValues("chkEmp");
			String arrearFor[] = request.getParameterValues("hArrearFor");
			String leaveRecoveryCode[] = request.getParameterValues("hLeaveRecoveryCode");
			String leaveRecoveryName[] = request.getParameterValues("hLeaveRecoveryName");
			Object[][] rows = new Object[empId.length - removeEmp.length][c.length+d.length+11];
			Object[][] leaveRecObj = new Object[empId.length - removeEmp.length][3];
			for (int i = 0; i < leaveRecObj.length; i++) {
				leaveRecObj [i][0]=arrearFor[i];
				leaveRecObj [i][1]=leaveRecoveryCode[i];
				leaveRecObj [i][2]=leaveRecoveryName[i];
			}
			Object[][] credit_debit = new Object[empId.length][d.length + c.length];
			for (int i = 0; i < empId.length; i++) {
				/**
				 * FOR GETTING CREDIT AND DEBIT FROM JSP
				 */
				credit_debit[i] = request.getParameterValues(String.valueOf(i)); // WILL GET ALL CREDITS AND DEBIT
			}
			String[] temp = new String[5];
			for (int i = 0; i < removeEmp.length; i++) {
				temp = removeEmp[i].split("&");
				removeEmp[i] = temp[4];
			}
			int k = 0;
			
			for (int i = 0, z = 0; i < empId.length; i++) {
				if((removeEmp.length > k && Integer.parseInt(removeEmp[k]) != i) || removeEmp.length <= k )
				{
						rows[z][0] = empId[i];
						rows[z][1] = empToken[i];
						rows[z][2] = empName[i];
						int j,m=0;
						for (j = 0; j < c.length+d.length; j++) {
							if(j==c.length)
							{
								rows[z][j+3] = totalCredit[i];
								logger.info("Rows Value in == length "+rows[z][j+3]);
								logger.info("J Value "+j);
							}
							else 
							{
								rows[z][j+3] = credit_debit[i][m];
								logger.info("Rows Value in credit "+rows[z][j+3]);
								logger.info("J Value "+j);
								m++;
							}
						}
						rows[z][j+3] = credit_debit[i][m];
						rows[z][j+4] = totalDebit[i];
						rows[z][j+5] = netPay[i];
						rows[z][j+6] = arrDays[i];
						rows[z][j+7] = period[i];
						rows[z][j+8] = forMonth[i];
						rows[z][j+9] = forYear[i];
						rows[z][j+10] = eligDays[i];
						z++;
				}
				else
					k++;
			}
			request.setAttribute("rows", rows);
			request.setAttribute("leaveRecObj", leaveRecObj);
			model.setHeaders(monthlyArrears, request);
			model.terminate();
			monthlyArrears.setFlag("true");
			addActionMessage("Selected records have been removed. Please click save to reflect the changes.");
			getNavigationPanel(2);
			monthlyArrears.setFilterFlag(true);
			monthlyArrears.setEnableAll("Y");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in Monthly Arrears while Removing "+e);
			return SUCCESS;
		}
	}
}
