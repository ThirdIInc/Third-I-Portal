/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.DaParaMaster;

import org.paradyne.model.payroll.DaParaModel;

/**
 * @author Venkatesh
 *
 */
public class DaParaMasterAction extends org.struts.lib.ParaActionSupport{

	DaParaMaster daParaMaster;

	 public Object getModel() {
		// TODO Auto-generated method stub
		return daParaMaster;
	}

	/**
	 * @return the debitMaster
	 */
	public DaParaMaster getDaParaMaster() {
		return daParaMaster;
	}

	/**
	 * @param debitMaster the debitMaster to set
	 */
	public void setDaParaMaster(DaParaMaster daParaMaster) {
		this.daParaMaster = daParaMaster;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		daParaMaster=new DaParaMaster();
	}
	public String reset()throws Exception {
		logger.info("In reset1");
		daParaMaster.setDaCode("");
		daParaMaster.setDaRate("");
		daParaMaster.setDaEffDate("");
		logger.info("In reset2");
		return "input";
	}
	public String save()throws Exception {
		
		logger.info("In Save1");
		DaParaModel model=new DaParaModel();
		model.initiate(context,session);
		boolean result;
		boolean check=model.checkValidDate(daParaMaster);
		logger.info("check......"+check);
		if(check==true){
		if(daParaMaster.getDaCode().equals(""))
		{
			logger.info("In add");
			result=model.addDaPara(daParaMaster,context);
			if(result) {
				addActionMessage(getText("addMessage",""));
				reset();
			} else {
				addActionMessage("DA can not be added");
				reset();
			}
		}
		else
		{
			result=model.modDaPara(daParaMaster);
			if(result) {
				addActionMessage("Record Modified Successfully ");
				reset();
			} else {
				addActionMessage("DA Cannot be Modified");
				reset();
			}
			
		}
		}
		else{
			addActionMessage("DA can't be added for this efffective date");

		}
	
		logger.info("4");
		model.terminate();
		
	
		logger.info("5");
		return "success";
	}
	public String delete() throws Exception
	{
	logger.info("into delete");
	DaParaModel model=new DaParaModel();
	model.initiate(context,session);
	boolean result;
	result=model.deleteDaPara(daParaMaster);
	if(result)
	{
		daParaMaster.setDaCode("");
		daParaMaster.setDaRate("");
		daParaMaster.setDaEffDate("");
		
	}
	if(result)
	{
		addActionMessage(getText("delMessage",""));
	}
	 else {
			addActionMessage("DA Cannot be Deleted");
			
		}

	model.terminate();
	return "success";
	}


	public String report() throws Exception 
	{
		DaParaModel model=new DaParaModel();
		model.initiate(context,session);
		model.getDaParaReport(daParaMaster);
		model.terminate();
		return "report";
	}

	public String daParaRecord()throws Exception
	{	
		DaParaModel model=new DaParaModel();
		model.initiate(context,session);
		model.getDaParaRecord(daParaMaster);
		model.terminate();
		return "success";
	}

	public String f9action() throws Exception 
	{
	
	logger.info("in f9 action");
	
	String query = "SELECT DA_CODE,DA_RATE,TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY') FROM HRMS_DA_PARAMETER order by da_code ";		
	
	logger.info("in f9 action1");
	String[] headers={"DA Code", "DA Rate","DA Effect Date"};
	
	String[] headerWidth={"20", "20","25"};
	logger.info("in f9 action2");
	
	String[] fieldNames={"daParaMaster.daCode","daParaMaster.daRate","daParaMaster.daEffDate"};
	
	
	int[] columnIndex={0,1,2};
	
	String submitFlag = "true";
	logger.info("in f9 action4");
	
	String submitToMethod="DaParaMaster_daParaRecord.action";
	logger.info("in f9 action5");
	
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	logger.info("in f9 action6");
	return "f9page";
	}

}
