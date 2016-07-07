/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.CcaParaMaster;

import org.paradyne.model.payroll.CcaParaModel;

/**
 * @author Venkatesh
 *
 */
public class CcaParaMasterAction extends org.struts.lib.ParaActionSupport{

	CcaParaMaster ccaParaMaster;

	 public Object getModel() {
		// TODO Auto-generated method stub
		return ccaParaMaster;
	}

	/**
	 * @return the debitMaster
	 */
	public CcaParaMaster getCcaParaMaster() {
		return ccaParaMaster;
	}

	/**
	 * @param debitMaster the debitMaster to set
	 */
	public void setCcaParaMaster(CcaParaMaster ccaParaMaster) {
		this.ccaParaMaster = ccaParaMaster;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		ccaParaMaster=new CcaParaMaster();
		ccaParaMaster.setMenuCode(132);
	}
	public String reset()throws Exception {
		logger.info("In reset1");
		ccaParaMaster.setCcaCode("");
		ccaParaMaster.setEquiSalFrom("");
		ccaParaMaster.setEquiSalTo("");
		ccaParaMaster.setCcaAmt("");
		logger.info("In reset2");
		return "input";
	}
	public String save()throws Exception {
		
		logger.info("In Save1");
		CcaParaModel model = new CcaParaModel();
		logger.info("In Save2");
		model.initiate(context,session);
		logger.info("In Save3");
		
		if((ccaParaMaster.getCcaCode()).equals(""))
		{
			logger.info("into add method"+ccaParaMaster.getCcaCode());
			
			boolean result = model.addCcaPara(ccaParaMaster);
			 if(result)
				{
				addActionMessage("Record Saved Successfully");
				}
				else{
					addActionMessage("Duplicate Entry Found");
				}
		}
		else{
			
			boolean result = model.modCcaPara(ccaParaMaster);
			 if(result)
				{
				addActionMessage("Record Saved Successfully");
				}
				else{
					addActionMessage("Duplicate Entry Found");
				}
		}
	
	
		model.terminate();
		reset();
		logger.info("5");
		return "success";
		
		
	}
	public String delete() throws Exception
	{
	logger.info("in delete");
	CcaParaModel model = new CcaParaModel();
	
	model.initiate(context,session);
	boolean result ;
	logger.info("Before deletequalification");
	result = model.deleteCcaPara(ccaParaMaster);
	logger.info("After deleteDesignation");
	if(result)
	{
		ccaParaMaster.setCcaCode("");
		ccaParaMaster.setEquiSalFrom("");
		ccaParaMaster.setEquiSalTo("");
		ccaParaMaster.setCcaAmt("");
	
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
		CcaParaModel model = new CcaParaModel();
		model.initiate(context,session);
		model.getCcaParaReport(ccaParaMaster);
		model.terminate();
		return "report";
	}

	public String ccaParaRecord()throws Exception
	{	
		logger.info("I am ccaParaRecord");
		CcaParaModel model = new CcaParaModel();	
		model.initiate(context,session);
		model.getCcaParaRecord(ccaParaMaster);
		model.terminate();
		return "success";
	}

	public String f9action() throws Exception 
	{
	
	logger.info("in f9 action");
	
	String query = "SELECT CCA_CODE,TO_CHAR(EQUI_SAL_FROM,'DD-MM-YYYY') FROM HRMS_PAY_CCA_PARAMETER ORDER BY CCA_CODE";		
	
	logger.info("in f9 action1");
	String[] headers={"CCA Code", "Equivalent Salary From"};
	
	String[] headerWidth={"20", "60"};
	logger.info("in f9 action2");
	
	String[] fieldNames={"ccaParaMaster.ccaCode","ccaParaMaster.equiSalFrom"};
	
	
	int[] columnIndex={0,1};
	
	String submitFlag = "true";
	logger.info("in f9 action4");
	
	String submitToMethod="CcaParaMaster_ccaParaRecord.action";
	logger.info("in f9 action5");
	
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	logger.info("in f9 action6");
	return "f9page";
	}

}
