package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.EmpChangeHistory;
import org.paradyne.model.admin.srd.AcpHistoryModel;
import org.paradyne.model.admin.srd.EmpChangeHistoryModel;
import org.paradyne.model.admin.srd.EmployeeHouseModel;
import org.paradyne.model.advances.Festival.FestivalAdvancePmApproveModel;
 import org.struts.lib.ParaActionSupport;

public class EmpChangeHistoryAction extends ParaActionSupport {

	
	EmpChangeHistory emphistory=null;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		emphistory=new EmpChangeHistory();
		emphistory.setMenuCode(312);
		// TODO Auto-generated method stub
		
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		EmpChangeHistoryModel model = new EmpChangeHistoryModel();
		model.initiate(context,session);
 	if (emphistory.isGeneralFlag()) {
			model.getEmployeeDetails(emphistory.getUserEmpId(),emphistory);
			emphistory.setFlagGen("true");
		 
 	}
  else{
	    emphistory.setFlagGen("true");
		String offcEmp =(String)request.getSession().getAttribute("srdEmpCode");
		logger.info("Selected Employee record ****************"+offcEmp);
		model.getEmployeeDetails(offcEmp,emphistory);
		 
	}
 	 
	    model.display(emphistory);
		model.terminate();
	}
	

	 public Object getModel() {
		// TODO Auto-generated method stub
		return emphistory;
	}

	public EmpChangeHistory getEmphistory() {
		return emphistory;
	}

	public void setEmphistory(EmpChangeHistory emphistory) {
		this.emphistory = emphistory;
	}
	
	
	public String save()
	{
		emphistory.setFlag("true");
		EmpChangeHistoryModel model = new EmpChangeHistoryModel();
		model.initiate(context,session);
	    model.display(emphistory);
		boolean flag= model.add(emphistory);
		if(flag==true)
		{
		 
		addActionMessage("Record Saved Successfully");
		}else
		{
			addActionMessage("Home town can't be Updated more than two time");
		}
		
	 
		emphistory.setType("");
		emphistory.setOldValue("");
		emphistory.setNewValue("");
		emphistory.setAuthNO("");
		emphistory.setAuthDate("");
		
		model.display(emphistory);
		model.terminate();
		return "success"; 
		
	}
	
	
	public String reset()
	{if(emphistory.isGeneralFlag()==true)
	{
		emphistory.setType("");
		emphistory.setOldValue("");
		emphistory.setNewValue("");
		emphistory.setAuthNO("");
		emphistory.setAuthDate("");
	}
	else{
				
		emphistory.setEmployeeToken("");
		emphistory.setEmpName("");
		emphistory.setType("");
		emphistory.setCenter("");
		emphistory.setRank("");
		emphistory.setOldValue("");
		emphistory.setNewValue("");
		emphistory.setAuthNO("");
		emphistory.setAuthDate("");
		 
	}
	return "success"; 
		
	}
	
	
	
	public String dropDown() throws Exception
	{
		EmpChangeHistoryModel model = new EmpChangeHistoryModel();
		model.initiate(context,session);
		 
		model.downTable(emphistory);
		emphistory.setFlag("true");
            if(emphistory.getType().equals("H"))
            {
            	emphistory.setFlagList("true");
            	emphistory.setFlagGen("false");
            }
            
            if(emphistory.getType().equals("D"))
    		{
    			emphistory.setFlagType("true");
    			emphistory.setFlagGen("false");
    		}  if(emphistory.getType().equals("F")||emphistory.getType().equals("M")||emphistory.getType().equals("L")||emphistory.getType().equals("T"))
    		{
    			emphistory.setFlagGen("true");
    		}
            
            
            
		model.terminate();
		return "success";
		
	}
	
	
	public String display()
	{
		EmpChangeHistoryModel model = new EmpChangeHistoryModel();
		model.initiate(context,session); 
		boolean flag= model.display(emphistory);
		model.terminate();
		return "success"; 
	}
	
	
	public String f9empaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_TOKEN,TO_CHAR(TITLE_NAME||'  '||EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),TO_CHAR(EMP_CENTER||'-'||CENTER_NAME),TO_CHAR(RANK_NAME),EMP_ID"							
			+" FROM HRMS_EMP_OFFC "
			+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
			+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
			+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)";
					
		query+=getprofileQuery(emphistory);
		
		query+="ORDER BY EMP_ID";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Token No","Employee Name","Center Name","Rank Name"};
		
		String[] headerWidth={"10", "30", "30", "30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"emphistory.employeeToken","emphistory.empName","emphistory.center","emphistory.rank","emphistory.empID"};
		
		/** 
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="EmpChangeHistory_display.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	
	
	public String f9cityaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT CITY_ID,CITY_NAME FROM HRMS_CITY  ORDER BY CITY_ID ";	
					
	 
	 
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"City ID","City Name"};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"emphistory.cityID","emphistory.newValue"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod=" ";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	
	
	
	
	
	
	

}
