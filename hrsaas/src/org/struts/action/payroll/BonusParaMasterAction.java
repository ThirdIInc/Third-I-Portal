/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.BonusParaMaster;

import org.paradyne.model.payroll.BonusParaModel;

/**
 * @author Venkatesh
 *
 */
public class BonusParaMasterAction extends org.struts.lib.ParaActionSupport{

	BonusParaMaster bonusParaMaster;

	 public Object getModel() {
		// TODO Auto-generated method stub
		return bonusParaMaster;
	}

	/**
	 * @return the debitMaster
	 */
	public BonusParaMaster getBonusParaMaster() {
		return bonusParaMaster;
	}

	/**
	 * @param debitMaster the debitMaster to set
	 */
	public void setBonusParaMaster(BonusParaMaster bonusParaMaster) {
		this.bonusParaMaster = bonusParaMaster;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		bonusParaMaster=new BonusParaMaster();
	}
	public String reset()throws Exception {
		logger.info("In reset1");
		bonusParaMaster.setBonusCode("");
		bonusParaMaster.setBonusType("");
		bonusParaMaster.setBonDaysDec("");
		bonusParaMaster.setBonPrdFrom("");
		bonusParaMaster.setBonPrdTo("");
		bonusParaMaster.setTypeCode("");
		bonusParaMaster.setBonEmpType("");
		bonusParaMaster.setBonFormula("");
		logger.info("In reset2");
		return "input";
	}
	public String save()throws Exception {
		
		logger.info("In Save1");
		BonusParaModel model = new BonusParaModel();
		logger.info("In Save2");
		model.initiate(context,session);
		logger.info("In Save3");
		boolean result =false;
		if(String.valueOf(bonusParaMaster.getBonusCode()).equals(""))
		{
			logger.info("into add method");
			 result = model.addBonusPara(bonusParaMaster);
			 if(result) {
					addActionMessage(getText("addMessage",""));
				} else {
					addActionMessage("Record can not be added");
				}
		}
		else{
			 result = model.modBonusPara(bonusParaMaster);
			 if(result) {
					addActionMessage(getText("modMessage",""));
					
				} else {
					addActionMessage("Record can not be added");
				}
		}
		if(result)
		{
			reset();
		}
		
		logger.info("4");
		model.terminate();
		
		
		logger.info("5");
		return "success";
	}
	public String delete() throws Exception
	{
	logger.info("in delete");
	BonusParaModel model = new BonusParaModel();
	
	model.initiate(context,session);
	boolean result ;
	logger.info("Before deletequalification");
	result = model.deleteBonusPara(bonusParaMaster);
	logger.info("After deleteDesignation");
	if(result)
	{
		reset();
			
	}
	if(result)
	{
		addActionMessage(getText("delMessage",""));
	}

	model.terminate();
	return "success";
	}


	public String report() throws Exception 
	{
		BonusParaModel model = new BonusParaModel();
		logger.info("In report");
		model.initiate(context,session);
		model.getBonusParaReport(bonusParaMaster);
		model.terminate();
		return "report";
	}

	public String bonusParaRecord()throws Exception
	{	
		logger.info("I am Bonus ParaRecord");
		BonusParaModel model = new BonusParaModel();	
		model.initiate(context,session);
		model.getBonusParaRecord(bonusParaMaster);
		logger.info("I am Bonus ParaRecord1");
		
		model.terminate();
		logger.info("I am Bonus ParaRecord2");
		
		return "success";
	}

	public String f9action() throws Exception 
	{
	
	logger.info("in f9 action");
	
	String query = "SELECT BONUS_CODE,BONUS_TYPE FROM HRMS_BONUS_PARAMETER order by bonus_code ";		
	
	logger.info("in f9 action1");
	String[] headers={"Bonus Code", "Bonus Type"};
	
	String[] headerWidth={"20", "80"};
	logger.info("in f9 action2");
	
	String[] fieldNames={"bonusParaMaster.bonusCode","bonusParaMaster.bonusType"};
	
	
	int[] columnIndex={0,1};
	
	String submitFlag = "true";
	logger.info("in f9 action4");
	
	String submitToMethod="BonusParaMaster_bonusParaRecord.action";
	logger.info("in f9 action5");
	
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	logger.info("in f9 action6");
	return "f9page";
	}
	
	public String f9type() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  TYPE_ID, TYPE_NAME  FROM HRMS_EMP_TYPE ORDER BY TYPE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "EMPLOYEE TYPE ID"," EMPLOYEE TYPE NAME" };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20","80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "bonusParaMaster.typeCode","bonusParaMaster.bonEmptype"
				 };

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
	
	
	public String f9actionBonusPaybill() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "TYPE NAME" };

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

		String[] fieldNames = { "EmpBonus.bonusEmptype",
				"EmpBonus.typeCode" };

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
	

}
