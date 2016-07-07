package org.struts.action.egov.payroll;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.paradyne.bean.eGov.payroll.NonIndustrialSalaryeGov;
import org.paradyne.model.eGov.payroll.NonIndustrialSalaryeGovModel;
import org.struts.lib.ParaActionSupport;

public class NonIndustrialSalaryeGovAction extends ParaActionSupport {

	NonIndustrialSalaryeGov nonIndustrialSalaryeGov;

	

	public NonIndustrialSalaryeGov getNonIndustrialSalaryeGov() {
		return nonIndustrialSalaryeGov;
	}

	public void setNonIndustrialSalaryeGov(
			NonIndustrialSalaryeGov nonIndustrialSalaryeGoveGov) {
		this.nonIndustrialSalaryeGov = nonIndustrialSalaryeGoveGov;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return nonIndustrialSalaryeGov;
	}

static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.paradyne.model.eGov.payroll.NonIndustrialSalaryeGovModel.class);
	
	//overriding super class 
	public void prepare_local() throws Exception {
		nonIndustrialSalaryeGov=new NonIndustrialSalaryeGov();	 
		nonIndustrialSalaryeGov.setMenuCode(85);	
	}


//on load method for setting default values
	
	public void prepare_withLoginProfileDetails() throws Exception {
		NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy");
		String year = formater.format(date);
		nonIndustrialSalaryeGov.setYear(year);
		model.initiate(context, session);
		model.getFilters(nonIndustrialSalaryeGov);
		model.terminate();
	} //end of method prepare_withLoginProfileDetails()
	
	
		
	/**
	 * This method is used for calculating and viewing the salary
	 * This method is called when, user clicks on process button
	 * @return String "success"
	 */
	public String getNonIndustrialSalaries() throws Exception {
		try {
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
			model.initiate(context,session);
			
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
			//checking whether the year entered table exists or not
			boolean result = model.tableExist(nonIndustrialSalaryeGov.getYear());
			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
		     */
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
			/**
			 * we are setting the ledger status in the bean for access in the client side for checking onhold and recalculate
			 */
			nonIndustrialSalaryeGov.setLedgerStatus(ledgerStatus);
			if (result) {				
				if((ledgerStatus.equals("SAL_START") || ledgerStatus.equals("SAL_FINAL") || ledgerStatus.equals("ATTN_UNLOCK"))){
					/**
					 * If ledger status is SAL_START or SAL_FINAL that means salary is already processed
					 * and saved, It will go to viewSalary method and pick up all the saved records into rows Object
					 */
					Object rows[][]=model.viewSalary(nonIndustrialSalaryeGov,request,ledgerStatus,path);	
					//we are setting rows Object in request attribute to access it in the client side.				
					request.setAttribute("rows", rows);
				} //end of inner if loop
				else if(ledgerStatus.equals("ATTN_READY")){
					/**
					 * If ledger status is ATTN_READY  that means salary is not processed, then
					 * we will calculate all the employees who are in attendance in getSalary method and put into rows Object
					 */
					Object rows[][] = model.getSalary(nonIndustrialSalaryeGov,request,ledgerStatus,path);
					if(rows==null){
						addActionMessage("Problem in Attendace (or) Salary Calucaltion");
					}
					else{
						//we are setting rows Object in request attribute to access it in the client side.					
						request.setAttribute("rows", rows);
					}
				} // end of 1st else if
				else if(ledgerStatus.equals("ATTN_START")){
					addActionMessage("Attendance has not been locked");
				} // end of 2nd else if
				else if(ledgerStatus.equals("") || ledgerStatus.equals("null")){
					addActionMessage("Attendance has not been processed");
				} // end of 3rd else if
			} // end of upper if loop
			else{
				addActionMessage("Tables for specified year does not exists");
			}
			//below line of code will ensure that the control is not coming through search employee action
			nonIndustrialSalaryeGov.setSearchFlagRes("");
			model.terminate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "success";
	} //end of method getNonIndustrialSalaries()
	
	/**
	 * For saving processed, modified salary
	 * @return String "success"
	 */
	public String saveNonIndustrialSalaries() throws Exception {
		try {
			String month = nonIndustrialSalaryeGov.getMonth();
			String year = nonIndustrialSalaryeGov.getYear();
			String branchCode = nonIndustrialSalaryeGov.getBranchCode();
			String typeCode = nonIndustrialSalaryeGov.getTypeCode();
			String paybillNo = nonIndustrialSalaryeGov.getPayBillNo();
			String deptCode = nonIndustrialSalaryeGov.getDeptCode();
			String divCode = nonIndustrialSalaryeGov.getDivisionCode();
			String[] onHoldEmp =(String[])request.getParameterValues("onholdEmp"); // Picked form client side to update On Hold flag
		
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
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
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
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
			
			String paybill = nonIndustrialSalaryeGov.getPayBillNo();
			String empType = nonIndustrialSalaryeGov.getTypeCode();
			String ledgerCode=nonIndustrialSalaryeGov.getAttCode();
			boolean record= model.save( rows,d,c,emp_id,month, year,total_credit,total_debit,paybill,empType,
					sal_days,onHold,ledgerCode,branchCode,typeCode,paybillNo,deptCode,divCode,nonIndustrialSalaryeGov.getRecoveryFlag());
			if(record){
				model.saveProcessStatus(nonIndustrialSalaryeGov.getAttCode());
				addActionMessage("Record saved successfully");
			//	d=null;c=null;total_credit=null;total_debit=null;emp_id=null;onHold=null;sal_days=null;
			} //end of if loop
			}else{
				addActionMessage("Salary already Locked");
			}
			/**
			 *  For reloading all the processed salary after saving the records
			 */
			getNonIndustrialSalaries();
			model.terminate();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		return "success";
	} //end of method saveNonIndustrialSalaries
	
	/**
	 * @return String for ajax
	 * Saving on hold status for employees through ajax 
	 */
	public String onholdSave() {
		try {
			String month = nonIndustrialSalaryeGov.getMonth();
			String year = nonIndustrialSalaryeGov.getYear();
			String attCode = nonIndustrialSalaryeGov.getAttCode(); // Ledger code for changing status
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
			model.initiate(context,session);

			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
			 */
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
				
			String[] emp_id = (String[])request.getParameterValues("onHoldFlag");
			 if(ledgerStatus.trim().equals("SAL_START")){
				 try {
					model.saveOnHold(emp_id,year,attCode);
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
	 * For removing On hold status of employees
	 */
	public String onholdRemove() {
		try {
			String month = nonIndustrialSalaryeGov.getMonth();
			String year = nonIndustrialSalaryeGov.getYear();
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
			model.initiate(context,session);
			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
			 */
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
			
			String[] emp_id = (String[])request.getParameterValues("onHoldFlag");
			 if(ledgerStatus.trim().equals("SAL_START")){
				 try {
					model.removeOnHold(emp_id,year,nonIndustrialSalaryeGov.getAttCode());
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
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
			model.initiate(context,session);
			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
			 */
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
			nonIndustrialSalaryeGov.setFromReCal("true");
			if(!(ledgerStatus.equals("SAL_FINAL"))){  //ledger status is SAL_START or ATTN_READY then the control goes into if loop
					/**
					 * In the rows[][] object we will get all the records of selected employees  
				   	 * i.e., all new credits and debits and net pay
					 */
					Object rows[][]=model.recalSalary(nonIndustrialSalaryeGov,request,recal_emp,ledgerStatus,path);	
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
					nonIndustrialSalaryeGov.setFromReCal("");
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
	
	/**
	 * @return String SUCCESS 
	 * For locking, updating Loan Paid Flag the salary 
	 */
	public String lockSalary(){
		
		try {
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
			model.initiate(context, session);
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
			boolean result=false;
			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
			 */
			if(!(ledgerStatus.equals("SAL_START")) && !(ledgerStatus.equals("SAL_FINAL"))){
				addActionMessage("Please save processed salary");
			}
			else if(ledgerStatus.equals("SAL_FINAL")){
				addActionMessage("Salary already Locked");
			}
			else if(ledgerStatus.equals("SAL_START")){
				String poolName = String.valueOf(session.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null )) {
					poolName = "/" + poolName;
				}
				String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
				Object d[][]			=	model.getDebitHeader(path);
				Object c[][]			=	model.getCreditHeader(path);
				String[] emp_id			=	request.getParameterValues("emp_id");
				Object [][] rows=null;
				if(emp_id !=null && emp_id.length>0){
					rows=new Object[emp_id.length][d.length+c.length]; 
					HashMap<String, Object[][]> creditMap = new HashMap<String, Object[][]>();
		 			for(int i=0;i<emp_id.length;i++)
					{					
						rows[i]=request.getParameterValues(String.valueOf(i));
						//creditMap.put(emp_id[i],request.getParameterValues(String.valueOf(i)));
					}
		 		}				
				result=model.updateLock(nonIndustrialSalaryeGov,emp_id,rows,c,d);
			}
			if(result){
				addActionMessage("Salary Locked Successfully");
			}
			reset();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return SUCCESS;
	} //end of method lockSalary()
	
	/**
	 * @return String SUCCESS 
	 * For unlocking the salary
	 */
	public String unLockSalary(){
		
		try {
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
			model.initiate(context, session);
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
			boolean result=false;
			/**
			 * From checkProcessStatus() we will get the status of the Ledger, 
			 * i.e whether the attendance or salary 
			 * for the selected month and year is processed or locked, etc.		       
			 */
			if(!(ledgerStatus.equals("SAL_START")) && !(ledgerStatus.equals("SAL_FINAL"))){
				addActionMessage("Please save processed salary");
			}
			else if(ledgerStatus.equals("SAL_START")){
				addActionMessage("Salary not locked");
			}
			else if(ledgerStatus.equals("SAL_FINAL")){
				result=model.unLock(nonIndustrialSalaryeGov);
			}
			if(result){
				//addActionMessage("Salary Unlocked Successfully");
			}
			reset();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return SUCCESS;
	} //end of method lockSalary()
	
	/**
	 * @return String SUCCESS 
	 * This action will be called when u search an employee
	 */
	public String empSearch()
	{
		try {
		
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
			model.initiate(context,session);
			//for getting server path where configuration files are saved.
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String path = getText("data_path") + "/datafiles/" + poolName
			+ "/xml/Payroll/";
			nonIndustrialSalaryeGov.setEmpSearchFlag("true");
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
			nonIndustrialSalaryeGov.setLedgerStatus(ledgerStatus);
				if((ledgerStatus.equals("SAL_START") || ledgerStatus.equals("SAL_FINAL") || ledgerStatus.equals("ATTN_UNLOCK"))){
					/**
					 * If ledger status is SAL_START or SAL_FINAL that means salary is already processed
					 * and saved, It will go to viewSalary method and pick up all the saved records into rows Object
					 */
					Object rows[][]=model.viewSalary(nonIndustrialSalaryeGov,request,ledgerStatus,path);	
					request.setAttribute("rows", rows);
				}
				else if(ledgerStatus.equals("ATTN_READY")){
					Object rows[][] = model.getSalary(nonIndustrialSalaryeGov,request,ledgerStatus,path);
					
					if(rows==null)
					{
						addActionMessage("Problem in Attendace (or) Salary Calucaltion");
					}
					else{
										
						request.setAttribute("rows", rows);
					}

				}
				else if(ledgerStatus.equals("ATTN_START")){
					addActionMessage("Attendance has not been locked");
				}
				else if(ledgerStatus.equals("") || ledgerStatus.equals("null")){
					addActionMessage("Attendance has not been processed");
				}
			
			model.terminate();
			nonIndustrialSalaryeGov.setEmpSearchFlag("");
			nonIndustrialSalaryeGov.setSearchFlagRes("true");
			nonIndustrialSalaryeGov.setEmpSearchName("");
			nonIndustrialSalaryeGov.setEmpSearchToken("");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "success";

	}
	
	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.type") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "nonIndustrialSalaryeGov.typeName",
				"nonIndustrialSalaryeGov.typeCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}
	
	
	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL "
						+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";
		
		query +=getprofilePaybillQuery(nonIndustrialSalaryeGov);
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("pay.bill"),getMessage("billno") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "80","20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "nonIndustrialSalaryeGov.payBillName","nonIndustrialSalaryeGov.payBillNo"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 ,1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		logger.info("4");
		return "f9page";
	}
	 
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"nonIndustrialSalaryeGov.branchName", "nonIndustrialSalaryeGov.branchCode"};

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

	public String f9Dept()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"nonIndustrialSalaryeGov.deptName", "nonIndustrialSalaryeGov.deptCode"};

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

	public String f9Div()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			
			if(nonIndustrialSalaryeGov.getUserProfileDivision() != null && nonIndustrialSalaryeGov.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + nonIndustrialSalaryeGov.getUserProfileDivision() + ") ";
			}
			query +="ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"nonIndustrialSalaryeGov.divisionName", "nonIndustrialSalaryeGov.divisionCode"};

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

	public String f9ShowRecord()
	{
		try
		{
			String query = " SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May', " +
			" 6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ATTN_MONTH, " +
			" LEDGER_YEAR, TYPE_NAME, PAYBILL_GROUP, DEPT_NAME, CENTER_NAME, DIV_NAME, LEDGER_STATUS, LEDGER_CODE,LEDGER_MONTH " +
			" FROM HRMS_SALARY_LEDGER " +
			" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID =  HRMS_SALARY_LEDGER.LEDGER_EMPTYPE) " +
			" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_SALARY_LEDGER.LEDGER_PAYBILL) " +
			" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  HRMS_SALARY_LEDGER.LEDGER_DEPT) " +
			" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  HRMS_SALARY_LEDGER.LEDGER_BRN) " +
			" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_SALARY_LEDGER.LEDGER_DIVISION) " +
			" WHERE LEDGER_STATUS IN ('SAL_START','SAL_FINAL','ATTN_UNLOCK')"; 
			if(nonIndustrialSalaryeGov.getUserProfileDivision() != null && nonIndustrialSalaryeGov.getUserProfileDivision().length() > 0) {
				query += " AND DIV_ID IN(" + nonIndustrialSalaryeGov.getUserProfileDivision() + ") ";
			}
			query +=" ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC ";

			String[] headers = {getMessage("month"), getMessage("year"), getMessage("employee.type"),getMessage("pay.bill"),getMessage("department"),getMessage("branch"),getMessage("division"),getMessage("status")};

			String[] headerWidth = {"12", "12", "12", "12", "12", "12", "12", "12"};

			String[] fieldNames = {"nonIndustrialSalaryeGov.month", "nonIndustrialSalaryeGov.year", "nonIndustrialSalaryeGov.typeName", "nonIndustrialSalaryeGov.payBillName",
									"nonIndustrialSalaryeGov.deptName", "nonIndustrialSalaryeGov.branchName", "nonIndustrialSalaryeGov.divisionName", "status","attCode",
									"nonIndustrialSalaryeGov.month"};

			int[] columnIndex = {0, 1 ,2, 3, 4 ,5 ,6 ,7,8,9};

			String submitFlag = "true";

			String submitToMethod = "NonIndustrialSalaryeGov_filters.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public String filters()
	{
		try
		{ 	
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
				poolName = "/" + poolName;
			}
			String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
			NonIndustrialSalaryeGovModel model = new NonIndustrialSalaryeGovModel();
			model.initiate(context, session);
			// For setting default filters from payroll settings
			model.filters(nonIndustrialSalaryeGov);
			String ledgerStatus = model.checkProcessStatus(nonIndustrialSalaryeGov);
			nonIndustrialSalaryeGov.setLedgerStatus(ledgerStatus);
			Object rows[][]=model.viewSalary(nonIndustrialSalaryeGov,request,ledgerStatus,path);	
			
			if(rows==null)
			{
				addActionMessage("Data is incorrect");
			}
			else{
				
			request.setAttribute("rows", rows);
			nonIndustrialSalaryeGov.setSearchFlagRes("");
			
			}
			model.terminate();
			return SUCCESS;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		}
	}

	public String f9SearchEmp()
	{
		try
		{
			String typeCode = nonIndustrialSalaryeGov.getTypeCode();
			String payBillNo = nonIndustrialSalaryeGov.getPayBillNo();
			String brnCode = nonIndustrialSalaryeGov.getBranchCode();
			String deptCode = nonIndustrialSalaryeGov.getDeptCode();
			String divCode = nonIndustrialSalaryeGov.getDivisionCode();
			
			String query = " SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, " +
			"DECODE(EMP_STATUS, 'S','Service','R','Retired','N','Resigned','E','Terminated') ,EMP_ID" +
			" FROM HRMS_EMP_OFFC " +
			" WHERE  EMP_ID IN( SELECT ATTN_EMP_ID FROM HRMS_MONTH_ATTENDANCE_"+nonIndustrialSalaryeGov.getYear()+" WHERE ATTN_CODE ="+nonIndustrialSalaryeGov.getAttCode()+") ";			
			if(!typeCode.equals(""))
			{
				query += " AND EMP_TYPE = "+typeCode;
			}
			if(!payBillNo.equals(""))
			{
				query += " AND EMP_PAYBILL = "+payBillNo;
			}
			if(!brnCode.equals(""))
			{
				query += " AND EMP_CENTER = "+brnCode;
			}
			if(!deptCode.equals(""))
			{
				query += " AND EMP_DEPT = "+deptCode;
			}
			if(!divCode.equals(""))
			{
				query += " AND EMP_DIV = "+divCode;
			}
			query += " ORDER BY UPPER(ENAME) ";
			
			String[] headers = {getMessage("employee.id"), getMessage("employee.name") ,getMessage("status")};

			String[] headerWidth = {"20", "80","20"};

			String[] fieldNames = {"nonIndustrialSalaryeGov.empSearchToken", "nonIndustrialSalaryeGov.empSearchName","nonIndustrialSalaryeGov.empSearchStatus","nonIndustrialSalaryeGov.empSearchId"};

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
	
	public String reset()throws Exception
	{
		nonIndustrialSalaryeGov.setAttCode("");
		nonIndustrialSalaryeGov.setBranchCode("");
		nonIndustrialSalaryeGov.setBranchFlag("");
		nonIndustrialSalaryeGov.setBranchName("");
		nonIndustrialSalaryeGov.setCreditAmount("");
		nonIndustrialSalaryeGov.setCreditHeader(null);
		nonIndustrialSalaryeGov.setDebitAmount("");
		nonIndustrialSalaryeGov.setDebitHeader(null);
		nonIndustrialSalaryeGov.setDepartmentFlag("");
		nonIndustrialSalaryeGov.setDeptCode("");
		nonIndustrialSalaryeGov.setDeptName("");
		nonIndustrialSalaryeGov.setDivisionCode("");
		nonIndustrialSalaryeGov.setDivisionFlag("");
		nonIndustrialSalaryeGov.setDivisionName("");
		nonIndustrialSalaryeGov.setEmpInfo(null);
		nonIndustrialSalaryeGov.setEmpSearchFlag("");
		nonIndustrialSalaryeGov.setEmpSearchId("");
		nonIndustrialSalaryeGov.setEmptypeFlag("");
		nonIndustrialSalaryeGov.setFromReCal("");
		nonIndustrialSalaryeGov.setFromTotRec("");
		nonIndustrialSalaryeGov.setGrossCredit(null);
		nonIndustrialSalaryeGov.setHdPage("");
		nonIndustrialSalaryeGov.setLedgerStatus("");
		nonIndustrialSalaryeGov.setMonth("0");
		nonIndustrialSalaryeGov.setPageNo("");
		nonIndustrialSalaryeGov.setPayName("");
		nonIndustrialSalaryeGov.setPaybillFlag("");
		nonIndustrialSalaryeGov.setSalDays("");
		nonIndustrialSalaryeGov.setTypeCode("");
		nonIndustrialSalaryeGov.setTypeName("");
		nonIndustrialSalaryeGov.setYear("");
		nonIndustrialSalaryeGov.setStatus("");
		prepare_withLoginProfileDetails();
		return SUCCESS;
	}

}
