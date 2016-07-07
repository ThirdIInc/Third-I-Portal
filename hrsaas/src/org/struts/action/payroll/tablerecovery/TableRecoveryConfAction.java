/**
 * 
 */
package org.struts.action.payroll.tablerecovery;

import org.paradyne.bean.payroll.tablerecovery.*;
import org.paradyne.model.admin.tyduty.TyDutyAdvPmAppModel;
import org.paradyne.model.admin.tyduty.TyDutyClaimDpModel;
import org.paradyne.model.payroll.tablerecovery.*;
import org.struts.lib.*;   

/**
 * @author Venkatesh
 *
 */
public class TableRecoveryConfAction extends ParaActionSupport{
	
	TableRecoveryConf tabRecConf;
	
	/**
	 *
	 * 
	 */
	

	/**
	 * @return the empInvestment
	 */
	/**
	 * @return the tabRecConf
	 */
	public TableRecoveryConf getTabRecConf() {
		return tabRecConf;
	}

	/**
	 * @param tabRecConf the tabRecConf to set
	 */
	public void setTabRecConf(TableRecoveryConf tabRecConf) {
		this.tabRecConf = tabRecConf;
	}
	
	 public Object getModel() {
		return tabRecConf;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		tabRecConf=new TableRecoveryConf();
	}
	
	/*public String input()throws Exception{
		try{		
			TableRecoveryConfModel model = new TableRecoveryConfModel();
			model.initiate(context,session);
			model.getRecord(tabRecConf,request);
			model.terminate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "success";
	}*/
	
	public String reset()throws Exception
	{
		tabRecConf.setEmpID("");
		tabRecConf.setEmpName("");
		tabRecConf.setEmpTokenNo("");
		tabRecConf.setDepartment("");
		tabRecConf.setCenter("");
		tabRecConf.setDisplay("");
		return "success";
	}
	
	public String getList()throws Exception
	{
		TableRecoveryConfModel model = new TableRecoveryConfModel();
		model.initiate(context,session);
		String[][] centList 	= model.getCenterList(tabRecConf); 
		model.getEmpDet(tabRecConf);
		model.terminate();
		request.setAttribute("centerChk",centList);
		logger.info("&&&&&&&&&&&&&&&&&&&After"+centList.length);
		if(centList.length>0)
			tabRecConf.setDisplay("true");
		return "success";
	}
	
	public String save()throws Exception
	{
		TableRecoveryConfModel model = new TableRecoveryConfModel();
		model.initiate(context,session);
		
		String[] flags=(String[])request.getParameterValues("debitCode");
		
		logger.info("-------------After Get Flag length----------------------"+flags.length);
		boolean result = false;
		for(int i=0;i<flags.length;i++)
		{
			
				 result=model.addRec(tabRecConf,flags[i]);
			
			
		}
		if(result)
			addActionMessage("Record Saved Successfully");
		for(int i=0;i<flags.length;i++)
		{
			logger.info("VAlues are++++++++++++++++++++++++++++++++++++++++++++++of Debit Code="+flags[i]);
		}
		
		model.terminate();
		return "success";
	}
		
		
		public String f9empaction() throws Exception {
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			
			logger.info("In f9 action===========1");
			String query = "	SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," +
				   " nvl(HRMS_RANK.RANK_NAME,''),HRMS_CENTER.CENTER_ID||' - '||HRMS_CENTER.CENTER_NAME,HRMS_EMP_OFFC.EMP_ID "
				+ "	FROM HRMS_EMP_OFFC  "
				+ "	left JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER " +
					" left JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " +
					" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "  ;
				
				
				
				/*
				"SELECT EMP_ID,TO_CHAR(EMP_FNAME||'  '||EMP_LNAME),EMP_DEPT,TO_CHAR(DEPT_NAME),EMP_CENTER,TO_CHAR(CENTER_NAME)"
								+" FROM HRMS_EMP_OFFC "
								+" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
								+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
								+"  ORDER BY EMP_ID ";	*/	
						
			
			/**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 */ 
			String[] headers={"Token No.","Employee Name", "Rank Name","Center Name"};
			
			String[] headerWidth={"10", "30","30","30"};
			logger.info("In f9 action===========2");
			
			
			/**
			 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
			 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
			 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
			 * */ 	
			
			String[] fieldNames={"tabRecConf.empTokenNo","tabRecConf.empName","tabRecConf.department","tabRecConf.center","empID"};
			
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
			logger.info("In f9 action===========3");
			
			
			String submitToMethod="TabRecConf_getList.action";
			
			logger.info("In f9 action===========4");
			
			/**
			 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
			 */
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			
			return "f9page";
		}

		
		
		/*public String f9empaction() throws Exception {
			logger.info("In f9 action===========1");
			String query = "	SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_TITLE_CODE||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," +
				   " HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_ID||' - '||HRMS_CENTER.CENTER_NAME,EMP_TYPE,HRMS_EMP_OFFC.EMP_ID "
				+ "	FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER " +
					" INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	WHERE HRMS_EMP_OFFC.EMP_CENTER = (SELECT HRMS_EMP_OFFC.EMP_CENTER FROM HRMS_EMP_OFFC "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID ='"
				+ tyDutyProp.getPropInitBy() + "') "
				+ "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";
				
				
				"SELECT EMP_ID,TO_CHAR(EMP_FNAME||'  '||EMP_LNAME),EMP_DEPT,TO_CHAR(DEPT_NAME),EMP_CENTER,TO_CHAR(CENTER_NAME)"
				+" FROM HRMS_EMP_OFFC "
				+" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)" +
						"ORDER BY EMP_ID";
				
						
			
			*//**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 *//* 
			String[] headers={"Emp token","Emp Name", "Dept name","Center Name"};
			
			String[] headerWidth={"10", "30","30","30"};
			logger.info("In f9 action===========2");
			
			
			*//**
			 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
			 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
			 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
			 * *//* 	
			
			String[] fieldNames={"tyDutyProp.empTokenNo","tyDutyProp.empName","tyDutyProp.empDepartment","tyDutyProp.empCenter","tyDutyProp.isOfficer","tyDutyProp.empID"};
			
			*//**
			 * 	 	SET THE COLUMN INDEX
			 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
			 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
			 * 			THEN THE COLUMN INDEX CAN BE {1,3}
			 * 		
			 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
			 * 
			 *//* 
			int[] columnIndex={0,1,2,3,4,5};
			
			*//**
			 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
			 * 
			 *//*
			String submitFlag = "true";
			
			*//**		 
			 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
			 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
			 *//*
			logger.info("In f9 action===========3");
			
			
			String submitToMethod="TyDutyProp_noMethod.action";
			
			logger.info("In f9 action===========4");
			
			*//**
			 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
			 *//*
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			
			return "f9page";
		}*/
		

		
}
