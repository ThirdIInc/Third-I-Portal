package org.struts.action.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.paradyne.bean.payroll.IndustrialSalary;
import org.paradyne.model.ot.OtViewModel;
import org.paradyne.model.payroll.IndustrialSalaryModel;
 import org.struts.lib.ParaActionSupport;

/**
 * @author sunil
 *
 */
public class IndustrialSalaryAction extends ParaActionSupport {	 

	private IndustrialSalary industrialSalary;
	
	 public Object getModel() {
		return this.industrialSalary;
	}

	public String execute() throws Exception {		 
		return "success";
	}

	 
	 
	public IndustrialSalary getIndustrialSalary() {
		return industrialSalary;
	}

	public void setIndustrialSalary(IndustrialSalary industrialSalary) {
		this.industrialSalary = industrialSalary;
	}

	
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		industrialSalary=new IndustrialSalary();	
		industrialSalary.setMenuCode(84);	
	}

	// viewSalary
	
	public void prepare_withLoginProfileDetails() throws Exception {
		industrialSalary.setTypeCode("1");
		industrialSalary.setTypeName("INDUSTRIAL");
	}
	
	public String viewNonIndustrialSalaries() throws Exception {
		try {
			IndustrialSalaryModel model = new IndustrialSalaryModel();
			model.initiate(context,session);
			logger.info("get salary called");
			if(("P".equals(model.checkProcessStatus(industrialSalary)) || "L".equals(model.checkProcessStatus(industrialSalary)))){
			Object rows[][]=model.viewSalary(industrialSalary);	
			Object d[][]=model.getDebitHeader();
			Object c[][]=model.getCreditHeader();
			
			
			request.setAttribute("debitLength",d);
			request.setAttribute("creditLength",c);
			/*	int len=d.length+c.length+3;
			ArrayList<IndustrialSalary> salaries=new ArrayList<IndustrialSalary>();
			for(int i=0;i<rows.length;i++)
			{
				IndustrialSalary sal=new IndustrialSalary();
				sal.setEmpId(String.valueOf(String.valueOf(rows[i][0])));
			}*/
			request.setAttribute("rows", rows);
			}else{
				 addActionMessage("Please process salary and save !");
			 }
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	
	/*
	 * following method is for getting the current debit and credit of Employee 
	 * from HRMS_EMP_CREDITS AND HRMS_EMP_DEBIITS   
	 * 
	 * 
	 * 
	 */
	public String getNonIndustrialSalaries() throws Exception {
		try {
			IndustrialSalaryModel model = new IndustrialSalaryModel();
			model.initiate(context,session);
			logger.info("get salary called");
			boolean result = model.tableExist(industrialSalary.getYear());
			
			 if (result) {
				if (!("L".equals(model.checkProcessStatus(industrialSalary)))) {
					Object rows[][] = model.getSalary(industrialSalary);
					Object d[][] = model.getDebitHeader();
					Object c[][] = model.getCreditHeader();

					request.setAttribute("debitLength", d);
					request.setAttribute("creditLength", c);
					/*	int len=d.length+c.length+3;
					ArrayList<IndustrialSalary> salaries=new ArrayList<IndustrialSalary>();
					for(int i=0;i<rows.length;i++)
					{
						IndustrialSalary sal=new IndustrialSalary();
						sal.setEmpId(String.valueOf(String.valueOf(rows[i][0])));
					}*/
					request.setAttribute("rows", rows);
				} else {
					addActionMessage("Salary already Locked");
				}
			}else{
				addActionMessage("Enter valid year");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String saveNonIndustrialSalaries() throws Exception {
		try {
			IndustrialSalaryModel model = new IndustrialSalaryModel();
			model.initiate(context,session);
			 if(!("L".equals(model.checkProcessStatus(industrialSalary)))){
				Object d[][]=model.getDebitHeader();
				Object c[][]=model.getCreditHeader();
				String[] emp_id=request.getParameterValues("emp_id");
				String total_credit[]=new String [emp_id.length];
				String total_debit[]=new String[emp_id.length];
				String ot_amount[]=new  String[emp_id.length];
				try
				{
					for(int i=0;i<emp_id.length;i++)
					{
						/*
						 * FOR GETTING VALUE OF TOTAL CREDIT AND TOTAL DEBIT 
						 * FROM JSP
						 */
						total_credit[i]=request.getParameter("totalCredit"+i);
						total_debit[i]=request.getParameter("totalDebit"+i);
						ot_amount[i]=request.getParameter("otAmount"+i);
					}
				}
				catch(Exception e)
				{
					
				}
				String month=industrialSalary.getMonth();
				String year=industrialSalary.getYear();
				 Object [][] rows=new Object[emp_id.length][d.length+c.length]; 
				 
				
				 
				for(int i=0;i<emp_id.length;i++)
				{
					/*
					 * FOR GETTING CREDIT AND DEBIT FROM JSP
					 * 
					 */
					logger.info(emp_id[i]);
					rows[i]=request.getParameterValues(String.valueOf(i));
				}
				
				
				boolean record= model.save( rows,d,c,emp_id,month, year,total_credit,total_debit,ot_amount,
													industrialSalary.getPayBillNo(),industrialSalary.getTypeCode());
				if(record){
					model.saveProcessStatus(industrialSalary);
					addActionMessage("Record saved successfully");
				}
			 }else{
				 addActionMessage("Salary already Locked");
			 }
				
			model.terminate();
		} catch (Exception e) {
			logger.info("Error in Non Industrial action save "+e);
		}
		return "success";
	}
	
	public String lockNonIndustrialSalaries() throws Exception {
		try {
			IndustrialSalaryModel model = new IndustrialSalaryModel();
			model.initiate(context,session);
		 if("P".equals(model.checkProcessStatus(industrialSalary))){
			boolean lock=model.lockSalary(industrialSalary);	
			if(lock){
				addActionMessage("Salary have been Locked ");
			}
		 }else if("L".equals(model.checkProcessStatus(industrialSalary))){
			 addActionMessage("Salary already Locked");
		 }else{
			 addActionMessage("Salary may not processed\nPlease process salary first");
		 }
			 
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String processRecovery()throws Exception {
		IndustrialSalaryModel model = new IndustrialSalaryModel();
		model.initiate(context,session);
		boolean result = model.tableExist(industrialSalary.getYear());
		if(result){
			if("P".equals(model.checkProcessStatus(industrialSalary))){
				industrialSalary.setRecoveryProcess(true);
				Object rows[][] = model.getSalary(industrialSalary);
				Object d[][] = model.getDebitHeader();
				Object c[][] = model.getCreditHeader();

				request.setAttribute("debitLength", d);
				request.setAttribute("creditLength", c);
				request.setAttribute("rows", rows);
			}else if("L".equals(model.checkProcessStatus(industrialSalary))){
				 addActionMessage("Salary already Locked\nYou can't reprocess again");
			 }else{
				 addActionMessage("Salary may not processed\nPlease process salary first");
			 }
		}else{
			addActionMessage("Table does not exist for this year");
		}
		
		return "success";
	}
	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE WHERE TYPE_ID IN (1)";

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

		String[] fieldNames = { "industrialSalary.typeName",
				"industrialSalary.typeCode" };

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
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	public String f9payBill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT PAYBILL_GROUP,PAYBILL_ID FROM HRMS_PAYBILL "
			+" LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";
			

			query +=getprofilePaybillQuery(industrialSalary);
			
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

		String[] fieldNames = { "industrialSalary.payBillNo","industrialSalary.payBillNo"};

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
	}
	 
}
