
package org.struts.action.payroll;

import org.paradyne.bean.payroll.OtParaMaster;

import org.paradyne.model.payroll.OtParaModel;

/**
 * @author Venkatesh
 *
 */
public class OtParaMasterAction extends org.struts.lib.ParaActionSupport{

	OtParaMaster otParaMaster;

	 public Object getModel() {
		// TODO Auto-generated method stub
		return otParaMaster;
	}

	/**
	 * @return the debitMaster
	 */
	public OtParaMaster getOtParaMaster() {
		return otParaMaster;
	}

	/**
	 * @param debitMaster the debitMaster to set
	 */
	public void setOtParaMaster(OtParaMaster otParaMaster) {
		this.otParaMaster = otParaMaster;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		otParaMaster=new OtParaMaster();
	}
	public String reset()throws Exception {
		otParaMaster.setOtId("");
		otParaMaster.setTypeCode("");
		otParaMaster.setTypeName("");
		otParaMaster.setNormalCalSingle("");
		otParaMaster.setNormalCalDouble("");
		otParaMaster.setMaximumCap("");
		otParaMaster.setOtPolicy("");
		otParaMaster.setMinimumFloor("");
		otParaMaster.setHoliCalSingle("");
		otParaMaster.setHoliCalDouble("");
		
		return "input";
	}
	public String save()throws Exception { 
		OtParaModel model = new OtParaModel(); 
		model.initiate(context,session); 
		boolean result ; 
		if(otParaMaster.getFlag().equals("false"))
		{
			 logger.info("into add method");
			 result = model.addOtPara(otParaMaster);
			 
			 if(result==true)
			 {
				 addActionMessage("Record Saved Successfully");
			 }else
			 {
				 addActionMessage("Credit can not be added");	 
			 }
			 
		}  
		otParaMaster.setFlag("false");
		return "success";
	}
	
	
	public String update()
	{
		
		OtParaModel model = new OtParaModel();
		
		model.initiate(context,session);
		boolean result ;
		 result = model.modOtPara(otParaMaster);
		 
		 if(result==true)
		 {
			 addActionMessage("Record modified Successfully");
		 }else
		 {
			 addActionMessage("Credit can not be modified");	 
		 }
		 otParaMaster.setFlag("false");
		 model.terminate();
		return "success";
	}
	public String delete() throws Exception
	{
	logger.info("in delete");
	OtParaModel model = new OtParaModel();
	
	model.initiate(context,session);
	boolean result ;
	logger.info("Before deletequalification");
	result = model.deleteOtPara(otParaMaster);
	logger.info("After deleteDesignation");
	if(result)
	{
		otParaMaster.setOtId("");
		otParaMaster.setTypeCode("");
		otParaMaster.setTypeName("");
		otParaMaster.setNormalCalSingle("");
		otParaMaster.setNormalCalDouble("");
		otParaMaster.setMaximumCap("");
		otParaMaster.setOtPolicy("");
		otParaMaster.setMinimumFloor("");
		otParaMaster.setHoliCalSingle("");
		otParaMaster.setHoliCalDouble("");
		
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
		OtParaModel model = new OtParaModel();
		model.initiate(context,session);
		model.getOtParaReport(otParaMaster);
		model.terminate();
		return "report";
	}

	public String OtParaRecord()throws Exception
	{	
		logger.info("I am in model");
		OtParaModel model = new OtParaModel();	
		model.initiate(context,session);
		model.getOtParaRecord(otParaMaster);
		model.terminate();
		return "success";
	}
	
	
	public String setUpdate() throws Exception 
	{
		OtParaModel model = new OtParaModel();
		model.initiate(context,session);
		otParaMaster.setFlag("true");
		model.terminate();
		return "success";
	}

	public String f9action() throws Exception 
	{
	
	logger.info("in f9 action");
	
	String query = "  SELECT TYPE_NAME,OT_CALC_SIN,OT_CALC_DBL,NVL(HOLIDAY_CALC_SIN,'0'),NVL(HOLIDAY_CALC_DBL,'0'),EMP_TYPE FROM HRMS_OT_PARAMETER  "
                  +"  LEFT JOIN HRMS_EMP_TYPE ON(HRMS_OT_PARAMETER.EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID) ";		
	
	
	String[] headers={"Employee Type", "Normal Calculation Single"};
	
	String[] headerWidth={"50", "50"};
	
	
	String[] fieldNames={"otParaMaster.typeName","otParaMaster.normalCalSingle","otParaMaster.normalCalDouble","otParaMaster.holiCalSingle","otParaMaster.holiCalDouble","OtParaMaster.typeCode"};
	
	
	int[] columnIndex={0,1,2,3,4,5};
	
	String submitFlag = "true";
	
	
	String submitToMethod="OtParaMaster_setUpdate.action";
	
	
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
	}
	
	public String f9type() throws Exception {
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

		String[] fieldNames = { "OtParaMaster.typeName",
				"OtParaMaster.typeCode" };

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
