/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.EmployeeHouse;
import org.paradyne.model.admin.srd.EmpChangeHistoryModel;
import org.paradyne.model.admin.srd.EmployeeHouseModel;
 import org.struts.lib.ParaActionSupport;

/**
 * @author balajim
 *
 */
public class EmployeeHouseAction extends ParaActionSupport {
	EmployeeHouse empHouse = null;

	 
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		empHouse = new EmployeeHouse();
		empHouse.setMenuCode(315);

	}
 
	 
	 
	 
	 public void prepare_withLoginProfileDetails() throws Exception {
		 EmployeeHouseModel model = new EmployeeHouseModel();
			model.initiate(context,session);
	 	if (empHouse.isGeneralFlag()) {
				model.getEmployeeDetails(empHouse.getUserEmpId(),empHouse);
	 	}
	  else{
			String offcEmp =(String)request.getSession().getAttribute("srdEmpCode");
			logger.info("Selected Employee record ****************"+offcEmp);
			model.getEmployeeDetails(offcEmp,empHouse);
		}
	 	 
		    model.display(empHouse);
			model.terminate();
		}
		
	 
 
	
	
	 public Object getModel() {
		// TODO Auto-generated method stub
		return empHouse;
	}

	public EmployeeHouse getEmpHouse() {
		return empHouse;
	}

	public void setEmpHouse(EmployeeHouse empHouse) {
		this.empHouse = empHouse;
	}
	
	
	public String save()
	{
		 
		EmployeeHouseModel model = new EmployeeHouseModel();
		model.initiate(context,session); 
		boolean flag= model.add(empHouse);
		if(flag==true)
		{
		addActionMessage("Record Saved Successfully");
		} 
		empHouse.setHouseColonyName("");
		empHouse.setHouseColonyID("");
		empHouse.setHouseNO("");
		empHouse.setStatus("");
		 model.display(empHouse);
		 
		return "success"; 
		
	}
	
	public String update()
	{
		 
		EmployeeHouseModel model = new EmployeeHouseModel();
		model.initiate(context,session); 
		boolean flag= model.update(empHouse);
		if(flag==true)
		{
		 
		addActionMessage("Record Updated Successfully");
		} 
		empHouse.setHouseColonyName("");
		empHouse.setHouseColonyID("");
		empHouse.setHouseNO("");
		empHouse.setStatus("");
		
		 model.display(empHouse);
		 
		return "success"; 
		
	}
	
	
	
	
	public String reset()
	{
		 
		
		if(empHouse.isGeneralFlag()==true)
		{
			empHouse.setHouseColonyName("");
			empHouse.setHouseColonyID("");
			empHouse.setHouseNO("");
			empHouse.setStatus("");
			empHouse.setHouseID("");
		}else
		{
			empHouse.setEmployeeToken("");
			empHouse.setCenter("");
			empHouse.setRank("");
			empHouse.setEmpID("");
			empHouse.setEmpName("");	 
			empHouse.setHouseColonyName("");
			empHouse.setHouseColonyID("");
			empHouse.setHouseNO("");
			empHouse.setStatus("");
			empHouse.setHouseID("");
		}
		 
		 
		empHouse.setFlag("false");
		
	
		 
		return "success"; 
	}
	
	public String delete()
	{
		EmployeeHouseModel model = new EmployeeHouseModel();
		model.initiate(context,session); 
		boolean flag= model.delete(empHouse);
		 model.display(empHouse);
		model.terminate();
		return "success"; 
	}
	
	public String edit()
	{
		EmployeeHouseModel model = new EmployeeHouseModel();
		model.initiate(context,session); 
	     model.edit(empHouse);
		 model.display(empHouse);
		model.terminate();
		return "success"; 
	}
	
	
	
	public String display()
	{
		EmployeeHouseModel model = new EmployeeHouseModel();
		model.initiate(context,session); 
		boolean flag= model.display(empHouse);
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
					
		query+=getprofileQuery(empHouse);
		
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
		
		String[] fieldNames={"empHouse.employeeToken","empHouse.empName","empHouse.center","empHouse.rank","empHouse.empID"};
		
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
		String submitToMethod="EmployeeHouse_display.action ";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	
	
	
	public String f9colonyidaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT COLONY_ID,COLONY_NAME FROM HRMS_HOUSING_COLONY ORDER BY COLONY_ID ";	 
	 
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Colony ID","Colony Name"};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"empHouse.houseColonyID","empHouse.houseColonyName"};
		
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
