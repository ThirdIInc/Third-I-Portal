package org.struts.action.ApplicationStudio.configAuth;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.struts.lib.*;
import org.paradyne.bean.ApplicationStudio.configAuth.ConfigAuthorization;
import org.paradyne.model.ApplicationStudio.configAuth.ConfigAuthorizationModel;
import org.paradyne.model.admin.master.BankModel;
import org.struts.lib.ParaActionSupport;

public class ConfigAuthorizationAction extends ParaActionSupport {


	ConfigAuthorization bean =null;
	public void prepare_local() throws Exception {
		bean = new ConfigAuthorization();
		bean.setMenuCode(969);
	} 
	public Object getModel() {
	 
		return bean;
	}

	public ConfigAuthorization getBean() {
		return bean;
	}

	public void setBean(ConfigAuthorization bean) {
		this.bean = bean;
	}

	public String input(){
		ConfigAuthorizationModel model = new ConfigAuthorizationModel();
		model.initiate(context, session);
		boolean isFileAvaiable =false;
		String fileName = getText("data_path")+ "\\Authorization\\Authorization.xml";
		boolean isDataSaved = model.checkSaveData();
		if(isDataSaved)
		{
			   isFileAvaiable=model.getSaveRecord(bean, fileName, request); 
		}else{
			try { 
				isFileAvaiable = model.getRecord(bean, fileName, request);  
			} catch (Exception e) {
				// TODO: handle exception
			}
		} 
		
		if(isFileAvaiable==false){
			addActionMessage("Modules not availbale for unlock.");
		}
		model.terminate();
		
		return "success";
	}
	
	public String addEmployee()
	{
		ConfigAuthorizationModel model = new ConfigAuthorizationModel();
		model.initiate(context, session);
		model.addEmployee(bean,request);
		model.terminate(); 
		return "success";
	}
	
	public String removeEmployee()
	{
		ConfigAuthorizationModel model = new ConfigAuthorizationModel();
		model.initiate(context, session);
		model.removeEmployee(bean,request);
		model.terminate(); 
		return "success";
	}
	
	public String save()
	{
		ConfigAuthorizationModel model = new ConfigAuthorizationModel();
		model.initiate(context, session);
		boolean flag = model.save(bean,request);
		if(flag)
		{
			addActionMessage("Record saved successfully.");
		}else{
			addActionMessage("Record can't saved.");
		}
		model.terminate(); 
		return "success";
	}
	
	
	public String f9Employee()
	{
		try
		{ 
			String finalEmp ="";
			String []hidEmployee = request.getParameterValues("hidEmployee"+bean.getRowId()); 
			 
			if(hidEmployee!=null && hidEmployee.length >0)
			   { 
					for (int j = 0; j < hidEmployee.length; j++) {  
							String dispEmpId = request.getParameter("dispEmpId_"+ bean.getRowId() + "_" + j);  
							if(j < hidEmployee.length){
								finalEmp+=dispEmpId+",";
							}else{
								finalEmp+=dispEmpId;
							} 
					} 
			}
			
			String chkString="";
			if(!finalEmp.equals("")&& finalEmp.length()>0){
				chkString=finalEmp.substring(finalEmp.length()-1, finalEmp.length());
			}  
			if(chkString.equals(",")){
				finalEmp=finalEmp.substring(0,finalEmp.length()-1);
			}  
			
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC " 
						 +"	WHERE EMP_STATUS ='S' " ;
						if(!chkString.equals("")){
							query += " AND EMP_ID NOT IN ("+finalEmp+")";
						}
						query += " ORDER BY EMP_FNAME,EMP_MNAME ,EMP_LNAME"; 
			
			String[] headers = {"Employee ID", "Employee Name"};

			String[] headerWidth = {"20", "80"};
			 
			String[] fieldNames = {"empToken","empName"+bean.getRowId() ,"empId"+bean.getRowId()};

			int[] columnIndex = {0,1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
		 e.printStackTrace();
			return null;
		}
	} // end of f9Employee
	
	/**
	 * following function is called when the view button is clicked in the house rent form.
	 * @return
	 */
	public String viewMiscSalaryUpoad ()  {
		try{
			ConfigAuthorizationModel model = new ConfigAuthorizationModel();
			model.initiate(context,session);
		bean.setInsertRecordFlag(true);
		bean.setUpdateRecordFlag(false);
			model.getMiscSalaryUpoadDetails(bean);
			
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
			return "miscSalaryPage";
	}
	
	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
		if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divCode", "divName" };

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
		//offDetail.setMasterMenuCode(42);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	/**
	 * following function is called when the view button is clicked in the house rent form.
	 * @return
	 */
	public String viewModuleAuth ()  {
		try{
			ConfigAuthorizationModel model = new ConfigAuthorizationModel();
			model.initiate(context, session);
			boolean isFileAvaiable =false;
			String fileName = getText("data_path")+ "\\Authorization\\Authorization.xml";
			boolean isDataSaved = model.checkSaveData();
			if(isDataSaved)
			{
				   isFileAvaiable=model.getSaveRecord(bean, fileName, request); 
			}else{
				try { 
					isFileAvaiable = model.getRecord(bean, fileName, request);  
				} catch (Exception e) {
					// TODO: handle exception
				}
			} 
			
			if(isFileAvaiable==false){
				addActionMessage("Modules not availbale for unlock.");
			}
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * Method to select the Pay bill group.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9payBillaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL "
				+ " ORDER BY  PAYBILL_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("pay.bill.no"), getMessage("pay.bill") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "payBillId", "payBillName" };

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
		//offDetail.setMasterMenuCode(214);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/**
	 * Method to select the department. *
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("department.code"),
				getMessage("department") };

		String[] headerWidth = { "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "deptCode", "deptName" };

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
		//offDetail.setMasterMenuCode(23);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * For Selecting Manager for Authorization
	 * 
	 * @return String
	 */
	public String f9ManagerAuth() {
		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"managerToken", "managerName", "managerCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	public String getComponentHead()
    {  
            try {
            	ConfigAuthorizationModel model = new ConfigAuthorizationModel();
                   // String hCode = request.getParameter("hCode");
                   // bean.setHLeaveCode(hCode);
                    model.initiate(context, session);
                    String query = " SELECT DEBIT_CODE,  DEBIT_NAME FROM HRMS_DEBIT_HEAD  ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
                    Object obj[][] = model.getSqlModel().getSingleResult(query);
                    ArrayList<Object> arr = new ArrayList<Object>();
                    for (int i = 0; i < obj.length; i++) {
                            // if(!hCode.equals(String.valueOf(obj[i][0]))){
                    	ConfigAuthorization bean1 = new ConfigAuthorization();
                            bean1.setDebitCode(String.valueOf(obj[i][0]));// debit code
                            bean1.setDebitName(String.valueOf(obj[i][1]));// debit name
                            arr.add(bean1);
                            // }
                            System.out.println("obj[i][1]===="+obj[i][1]);
                    }// end of for
                    bean.setArr(arr);
                    String str = request.getParameter("id");
                    String str1 = request.getParameter("id1");
                    bean.setFrmId(str);
                    bean.setHiddenfrmId(str1);
                    model.terminate();
            } catch (Exception e) {
                    e.printStackTrace();
            }
            
            
            return "componenthead";
    }
	
	public String addConfigAuth()throws Exception {
		try {
			String configureType = request
			.getParameter("configureGroupType");
			
			ConfigAuthorizationModel model = new ConfigAuthorizationModel();
			model.initiate(context, session);
			boolean result;
			if (bean.getParacode().equals("")) {
				result = model.addConfigAuth(bean);
				if (result) {
					
					addActionMessage("Misc Salary Earnings upload Authorization saved successfully.");
					model.getMiscSalaryUpoadDetails(bean);
					bean.setInsertRecordFlag(true);
					bean.setUpdateRecordFlag(false);
					reset();
				} else {
					addActionMessage(getMessage("Error occured."));
					reset();
					//bean.setEmailId("");
				}
			}
			else 
			{
				result = model.updateRecords(bean,request);
				if (result) 
				{
					addActionMessage("Misc Salary Earnings upload Authorization modified successfully.");
					bean.setInsertRecordFlag(true);
					bean.setUpdateRecordFlag(false);
					model.getMiscSalaryUpoadDetails(bean);	
					reset();
				} 
				else 
				{
					addActionMessage("Error occured");
//					/reset();
				}
			}
			
			model.terminate();
			return "miscSalaryPage";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * this method is used to reset all the fields
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			ConfigAuthorizationModel model = new ConfigAuthorizationModel();
			model.initiate(context, session);
			
			bean.setConfigAuth("");
			bean.setDivCode("");
			bean.setDivName("");
			bean.setDeptCode("");
			bean.setDeptName("");
			bean.setPayBillId("");
			bean.setPayBillName("");
			bean.setManagerToken("");
			bean.setManagerCode("");
			bean.setManagerName("");
			bean.setDebitCode("");
			bean.setDebitName("");
			bean.setCreditCode("");
			bean.setCreditName("");
			bean.setCenterCode("");
			bean.setCenterName("");
			bean.setParacode("");
			model.getMiscSalaryUpoadDetails(bean);
			bean.setInsertRecordFlag(true);
			bean.setUpdateRecordFlag(false);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "miscSalaryPage";
	}//end of reset method
	/**
	 * To set value of the fields for edit.
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		ConfigAuthorizationModel model = new ConfigAuthorizationModel();
		model.initiate(context, session);
		//model.getRecord(bankMaster);
		bean.setInsertRecordFlag(false);
		bean.setUpdateRecordFlag(true);
		model.getMiscSalUploadRecord(bean,request);
		model.getMiscSalaryUpoadDetails(bean);
		
		model.terminate();
		
	
		
		return "miscSalaryPage";
	}
	
	/**
	 * Method to delete the misc sal upload.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
	
		try {
			ConfigAuthorizationModel model = new ConfigAuthorizationModel();
			model.initiate(context, session);
			boolean result = model.delMiscSalUploadDtl(bean,request);
			model.getMiscSalaryUpoadDetails(bean);
			
			model.terminate();
			if (result) {
				addActionMessage(getMessage("delete"));
				reset();
			} else {
				addActionMessage(getMessage("no result"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "miscSalaryPage";
	}
	
	public String getCreditHead()
    {  
            try {
            	ConfigAuthorizationModel model = new ConfigAuthorizationModel();
                   // String hCode = request.getParameter("hCode");
                   // bean.setHLeaveCode(hCode);
                    model.initiate(context, session);
                    String query = " SELECT CREDIT_CODE,  CREDIT_NAME FROM HRMS_CREDIT_HEAD  ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
                    Object obj[][] = model.getSqlModel().getSingleResult(query);
                    ArrayList<Object> arr = new ArrayList<Object>();
                    for (int i = 0; i < obj.length; i++) {
                            // if(!hCode.equals(String.valueOf(obj[i][0]))){
                    	ConfigAuthorization bean1 = new ConfigAuthorization();
                            bean1.setCreditCode(String.valueOf(obj[i][0]));// credit code
                            bean1.setCreditName(String.valueOf(obj[i][1]));// credit name
                            arr.add(bean1);
                            // }
                            System.out.println("obj[i][1]===="+obj[i][1]);
                    }// end of for
                    bean.setArr(arr);
                    String str = request.getParameter("id");
                    String str1 = request.getParameter("id1");
                    bean.setFrmId(str);
                    bean.setHiddenCreditCodefrmId(str1);
                    model.terminate();
            } catch (Exception e) {
                    e.printStackTrace();
            }
            
            
            return "credithead";
    }
	
	/**
	 * Method to select the Center
	 * 
	 * @return Center
	 * @throws Exception
	 */
	public String f9centeraction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		String[] headerWidth = { "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "centerCode", "centerName" };

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

		

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
}
