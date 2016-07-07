/**
 * 
 */
package org.struts.action.payroll.incometax;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.paradyne.bean.payroll.incometax.*;
import org.paradyne.model.payroll.incometax.*;
import org.struts.lib.*;   

/**
 * @author Varun
 *Date: 05/03/2008
 */
public class TdsCalculationAction extends ParaActionSupport{
	
	TdsCalculation tdscal;
	//int month=0;
	

	/**
	 * @return the tdscal
	 */
	public TdsCalculation getTdscal() {
		return tdscal;
	}

	/**
	 * @param tdscal the tdscal to set
	 */
	public void setTdscal(TdsCalculation tdscal) {
		this.tdscal = tdscal;
	}

	 public Object getModel() {
		return tdscal;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void prepare_local() throws Exception {
		tdscal=new TdsCalculation();
		tdscal.setMenuCode(278);
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		TdsCalculationModel model = new TdsCalculationModel();
		model.initiate(context, session);
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		logger.info("sysDate   "+sysDate);
		String split[] = sysDate.split("/");
		double remianMonth=0;
		int month =  Integer.parseInt((split[1]));
		int year =  Integer.parseInt((split[2]));
		if(month < 4){
			tdscal.setCurrentYear(String.valueOf(year-1));
			year = year - 1;
		}
		else{
			tdscal.setCurrentYear(String.valueOf(year));
		}
		logger.info("year   "+year);
		tdscal.setCurrentMonth(String.valueOf(month));
		
		tdscal.setFromYear(String.valueOf(year));
		tdscal.setToYear(String.valueOf(year + 1));
		if(month>3 && month<=12){//if statement
			remianMonth = 15 - month;
		}///end of if
		else if(month<=3){//else if
			remianMonth = 3 - month;
		}//end of else if
		tdscal.setRemainMonthsHidden(String.valueOf(Math.round(remianMonth)));
		model.getFilters(tdscal);
		if (tdscal.isGeneralFlag()) {
			tdscal.setEmpID(tdscal.getUserEmpId());
			  GenEmpRecord();
		} //end of if
		model.terminate();
	} //end of method
	/**
	 * this mehtod is used to retrieve the data of employee. this method is called when the General
	 * flag is true
	 * @return success
	 */
	public String GenEmpRecord(){
		TdsCalculationModel model = new TdsCalculationModel();
		model.initiate(context, session);
		tdscal=model.generalRecord(tdscal);
		model.terminate();
		return "success";
	} //end of method

	/**
	 *  This method is for viewing the previously saved record
	 * @return success
	 * @throws Exception in view
	 */
	public String view()throws Exception{
		TdsCalculationModel model=new TdsCalculationModel();
		model.initiate(context,session);
		tdscal.setRemainMonths(tdscal.getRemainMonthsHidden());
		tdscal.setDetailCheck("");///to check for the detail button
		try {
			String[][] data = model.getRecordView(tdscal, request);
			if (data.length > 0) {
				request.setAttribute("data", data);
				tdscal.setSavebutFlag(true);
				tdscal.setIsFromEdit("true");
				tdscal.setIsFromCal("");
				tdscal.setIsShowCalculate("true");
				tdscal.setHdView("hello");
				 ArrayList list= new ArrayList(1);
				 list.add(String.valueOf("Y"));
				 tdscal.setChkList(list);
				 tdscal.setChkShwDetail("v");
				 tdscal.setSearchbutFlag(true);
			} //end of if
			else {
				addActionMessage("No Records to View!");
			}//end of else
		} catch (Exception e) {
			logger.info("Exception in view method");
		} //end of catch
		model.terminate();
		return SUCCESS;
	}// end of view method
	
	/**
	 * this method is used to reset all the values of text fields.
	 * @return success
	 */
	public String reset(){
		tdscal.setPayBillName("");
		tdscal.setDeptName("");
		tdscal.setDivisionName("");
		tdscal.setTypeName("");
		tdscal.setEmpName("");
		tdscal.setEmpCenter("");
		tdscal.setEmpRank("");
		tdscal.setEmpAge("");
		tdscal.setEmpGender("");
		tdscal.setEmpGrossSalList(null);
		tdscal.setEmpExemptInvList(null);
		tdscal.setEmpOtherInvList(null);
		tdscal.setEmpVIAInvList(null);
		tdscal.setEmpRebateInvList(null);
		tdscal.setEmpParaInvList(null);
		tdscal.setPTaxAmt("");
		tdscal.setTotalExemptInvAmt("");
		tdscal.setTotalGrossAmt("");
		tdscal.setTotalOtherInvAmt("");
		tdscal.setTotalParaInvAmt("");
		tdscal.setTotalRebateInvAmt("");
		tdscal.setTotalVIAInvAmt("");
		tdscal.setTotalNetInvAmt("");
		tdscal.setTotalTax("");
		tdscal.setEduCess("");
		tdscal.setTaxIncome("");
		tdscal.setTaxOnIncome("");
		tdscal.setTaxPaid("");
		tdscal.setTaxPerMon("");
		tdscal.setTaxPerMonth("");
		tdscal.setToYear("");
		tdscal.setFromYear("");
		tdscal.setRemainMonths("");
		tdscal.setSurCharge("");
		tdscal.setNetTax("");
		tdscal.setNetTaxableIncome("");
		tdscal.setEmpToken("");
		return "success";
	}//end of reset
	
	/**
	 * This method is used to calculate the Tds of all the employee
	 * @return success
	 * @throws Exception
	 */
	
	public String calculate()throws Exception{
		TdsCalculationModel model=new TdsCalculationModel();
		model.initiate(context,session);
		tdscal.setDetailCheck("true");///to check for the detail button
		/**
		 * this getRecord method is used to get all the calculated data of
		 * all the employees
		 */
		String[][] data=model.getRecord(tdscal,request);
		if(String.valueOf(data[0][0]).equals("1")){
			addActionMessage("Tax Parameters not available");
		} //end of if
		else if(String.valueOf(data[0][0]).equals("2")){
			addActionMessage("No Employees For Selected Criteria");
		} //end of else if
		else{
			 request.setAttribute("data", data);
			 tdscal.setSavebutFlag(true);
			 tdscal.setIsFromEdit("true");
			 ArrayList list= new ArrayList(1);
			 list.add(String.valueOf("Y"));
			 tdscal.setChkList(list);
			 tdscal.setChkShwDetail("c");
			 tdscal.setSearchbutFlag(true);
		} //end of else
		model.terminate();
		return SUCCESS;
	} // end of calculate method
	
	/**
	 * 
	 * @return success
	 * @throws Exception Record Cannot be Saved
	 */
	public String save()throws Exception{
		TdsCalculationModel model=new TdsCalculationModel();
		model.initiate(context,session);
		String[] empId=request.getParameterValues("empId");
		String[] grossSal=request.getParameterValues("grossSal");
		String[] excemp=request.getParameterValues("excemp");
		String[] rebate=request.getParameterValues("rebate");
		String[] othIncome=request.getParameterValues("othIncome");
		String[] taxIncome=request.getParameterValues("taxIncome");
		String[] totalTax=request.getParameterValues("totalTax");
		String[] edcCess=request.getParameterValues("edcCess");
		String[] surCharge=request.getParameterValues("surCharge");
		String[] netTax=request.getParameterValues("netTax");
		String[] taxPerMonth=request.getParameterValues("taxPerMonth");
		String[] taxPaid=request.getParameterValues("taxPaid");
		String[] deductions=request.getParameterValues("deductions");
		String[] remainMonth=request.getParameterValues("remainMonthToSave");
		String[] flags=(String[])request.getParameterValues("pmChkFlag");
		String[] hiddenFlags=(String[])request.getParameterValues("hdChkFlag");
		String[] ptax=request.getParameterValues("ptax");
		Object[][] add=new Object[empId.length][18];
		int count=0;
		tdscal.setDetailCheck("");///to check for the detail button
		tdscal.setEmpSearchId("");//to reset the search criteria..
		tdscal.setEmpSearchName("");//to reset the search criteria..
		tdscal.setEmpSearchToken("");//to reset the search criteria..
		/**
		 * by using this methods record are deleted first
		 * @param empId
		 */
		boolean result=model.deleteRecord(tdscal,empId);
		boolean result2=false;
		
		if(result)
		{ //(a loop)
		for(int i=0; i<empId.length;i++)
		{
			add[i][0]=tdscal.getFromYear();
			add[i][1]=tdscal.getToYear();
			add[i][2] = String.valueOf(empId[i]);
			add[i][3]= String.valueOf(grossSal[i]);
			add[i][4]= String.valueOf(excemp[i]);
			add[i][5]= String.valueOf(rebate[i]);
			add[i][6]=String.valueOf(othIncome[i]);
			add[i][7]=String.valueOf(taxIncome[i]);
			add[i][8]=String.valueOf(totalTax[i]);
			
			add[i][9]=String.valueOf(edcCess[i]);
			add[i][10]=String.valueOf(surCharge[i]);
			add[i][11]=String.valueOf(netTax[i]);
			 add[i][12]=String.valueOf(taxPerMonth[i]);
			 //add[i][13]=String.valueOf("N");
			 logger.info(" hiddenFlags[i]   :"+hiddenFlags[i]);
			if(String.valueOf(hiddenFlags[i]).equals("") || String.valueOf(hiddenFlags[i]).equals("null")){
				 add[i][13]=String.valueOf("N");
			} //end of nested if
			else{
					 add[i][13]=String.valueOf(hiddenFlags[i]);
			} //end of else
			logger.info(" add[i][13]   :"+add[i][13]);
			 add[i][14]=String.valueOf(taxPaid[i]);
			 logger.info(" add[i][14]   :"+add[i][14]);
			add[i][15]=String.valueOf(deductions[i]);
			add[i][16]=String.valueOf(ptax[i]);
			add[i][17]=String.valueOf(remainMonth[i]);
			if(hiddenFlags[i].equals("Y"))
			{
				count++;
			} //end of nested if
		} //end of For loop
			result2=model.saveRecord(tdscal,add);
			
			Object[][] addDebit=new Object[count][2];
			Object[][] delEmpDebit=new Object[count][1];
			count=0;
			if(result2)
			{
				for (int j = 0; j < empId.length; j++) {
				
					if(hiddenFlags[j].equals("Y"))
					{
						addDebit[count][0]= empId[j];
						addDebit[count][1]=taxPerMonth[j];
						delEmpDebit[count][0]= empId[j];
						count++;
					} //end of if
					
				} //end of for loop
				if(count>0){
				result2 = model.delEmpDebit(delEmpDebit,tdscal);
					if(result2){
					result2 = model.addDebit(addDebit, tdscal);
					} //end of nested if
				} //end of if
			} //end of nested if
			
		} //end of (a) loop
		
		if(result2){
			addActionMessage("Record Saved Successfully");
			view();
		} //end of if
		else{
			addActionMessage("Record Cannot be Saved");
			/**
			 * resets all the field
			 */
			reset();
		} //end of else
		model.terminate();
		return SUCCESS;
	
	}///end of getRecord method

	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL "
						+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";
		
		query += getprofilePaybillQuery(tdscal);
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "PAY BILL NAME","PAY BILL NO" };

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

		String[] fieldNames = { "tdscal.payBillNo","tdscal.payBillNo"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0,1 };

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
		
		return "f9page";
	} //end of paybill
	
	
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID,'','','' FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			
			String[] headers={getMessage("branch")};
			String[] headerWidth = {"100"};

			String[] fieldNames = {"tdscal.branchName", "tdscal.branchCode","employeeId","employeeName","employeeToken"};

			int[] columnIndex = {0, 1,2,3,4};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.info("Exception in f9Branch method");
			return "";
		} //end of catch
	 }//end of f9Branch method

	public String f9Dept()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID,'','','' FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {"DEPARTMENT NAME"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"tdscal.deptName", "tdscal.deptCode","employeeId","employeeName","employeeToken"};

			int[] columnIndex = {0, 1,2,3,4};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.info("Exception in f9Dept method");
			return "";
		} //end of catch
	}//end of f9Dept method
	
	public String f9Employee()
	{
		try
		{
			String query = " SELECT EMP_TOKEN, EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME,EMP_ID,'','','','','','','',''" 
						 + " FROM HRMS_EMP_OFFC ";
						 //";//ORDER BY UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";

			query += getprofileQuery(tdscal);
			query += " AND EMP_STATUS IN ('S','N')  ORDER BY UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
			String[] headers={getMessage("employee.id"),getMessage("employee")};
			String[] headerWidth = {"25","75"};

			String[] fieldNames = {"employeeToken", "employeeName","employeeId",
					"tdscal.divisionName","tdscal.divisionCode","tdscal.branchName","tdscal.branchCode",
					"tdscal.typeCode","tdscal.typeName","tdscal.deptName", "tdscal.deptCode"};

			int[] columnIndex = {0,1,2,3,4,5,6,7,8,9,10};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.info("Exception in f9Employee method");
			return "";
		} //end of catch
	} //end of f9Div method

	public String f9Div()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID,'','','' FROM HRMS_DIVISION ";
			
			if(tdscal.getUserProfileDivision() !=null && tdscal.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+tdscal.getUserProfileDivision() +")"; 
				query+= " ORDER BY  UPPER(DIV_NAME)";

			
			String[] headers={getMessage("division")};
			String[] headerWidth = {"100"};

			String[] fieldNames = {"tdscal.divisionName", "tdscal.divisionCode","employeeId","employeeName","employeeToken"};

			int[] columnIndex = {0, 1, 2, 3, 4};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.info("Exception in f9Div method");
			return "";
		} //end of catch
	} //end of f9Div method
	
	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   TYPE_NAME , TYPE_ID,'','','' FROM HRMS_EMP_TYPE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "EMPLOYEE TYPE" };

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

		String[] fieldNames = { "tdscal.typeName",
				"tdscal.typeCode","employeeId","employeeName","employeeToken" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1,2,3,4 };

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
		//logger.info("1");
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		//logger.info("4");
		return "f9page";
	} //end of f9Type method
	
	
	/**
	 * this method is called on the f9 of employee search. The records in the search window are same
	 * as the record which are in the grid.
	 * @return
	 */
	public String f9SearchEmp()
	{
		try
		{
			String query = "SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,EMP_ID "
						 +" FROM HRMS_EMP_OFFC " 
						 +" WHERE  EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS IN ('S','N') " 
						 +" AND EMP_REGULAR_DATE < TO_DATE('01-04-"+tdscal.getToYear()+"','DD-MM-YYYY')  ";
			
			if(tdscal.getBranchCode() != null)
				if(!tdscal.getBranchCode().equals(""))
					query+="AND EMP_CENTER = "+tdscal.getBranchCode();
			
			if(tdscal.getDivisionCode() != null)
				if(!tdscal.getDivisionCode().equals(""))
					query+="AND EMP_DIV = "+tdscal.getDivisionCode();
			
			if(tdscal.getTypeCode() != null)
				if(!tdscal.getTypeCode().equals(""))
					query+="AND EMP_TYPE = "+tdscal.getTypeCode();
			
			if(tdscal.getDeptCode() != null)
				if(!tdscal.getDeptCode().equals(""))
					query+="AND EMP_DEPT = "+tdscal.getDeptCode();
			
			if(tdscal.getEmployeeId() != null)
				if(!tdscal.getEmployeeId().equals(""))
					query+="AND EMP_ID = "+tdscal.getEmployeeId();
			
			query+=") ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";
			String[] headers = {"Employee Id", "Employee Name"};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"tdscal.empSearchToken", "tdscal.empSearchName","tdscal.empSearchId"};

			int[] columnIndex = {0, 1, 2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);			
		}
		catch (Exception e)
		{
			e.printStackTrace();			
		} //end of catch		 
		return "f9page";
	} //end of method f9SearchEmp
	
} //end of class
