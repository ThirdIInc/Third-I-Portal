package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.CurrencyMaster;
import org.paradyne.model.TravelManagement.Master.CurrencyMasterModel;
import org.paradyne.model.admin.master.DivisionModel;
import org.struts.lib.ParaActionSupport;

 

/**
 * @author ayyappa
 *
 */

public class CurrencyMasterAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	CurrencyMaster currencyMaster;
	public CurrencyMaster getCurrencybean() {
		return currencyMaster;
	}

	public void setCurrencybean(CurrencyMaster currencyMaster) {
		this.currencyMaster = currencyMaster;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		currencyMaster=new CurrencyMaster();
		currencyMaster.setMenuCode(970);

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		CurrencyMasterModel model = new CurrencyMasterModel();
		model.initiate(context, session);
		model.data(currencyMaster,request);
		model.terminate();
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return currencyMaster;
	}

	public String input() throws Exception{
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String addNew() throws Exception {
		
		getNavigationPanel(2);
		return "view";
	}

	public String f9action() throws Exception
	{
			String query = "SELECT  NVL(CURRENCY_NAME,''), NVL(CURRENCY_ABBR,''),decode(CURRENCY_STATUS,'A','Active','D','Deactive'),NVL(CURRENCY_STATUS,''),CURRENCY_ID " 
							+ " FROM HRMS_CURRENCY ORDER BY CURRENCY_ID";
			String[] headers = {  getMessage("currencyname"), getMessage("currencyabbr"), getMessage("currencystatus") };

			String[] headerWidth = {  "50", "25", "25" };

			String[] fieldNames = {  "curName", "curAbbr","curStatus","curStatus","curCode" };

			int[] columnIndex = { 0, 1, 2, 3 ,4};

			String submitFlag = "true";

			String submitToMethod = "Currency_getRecord.action";
			
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			
			return "f9page";
	}
	public String getRecord() throws Exception
	{
		try{
		CurrencyMasterModel model = new CurrencyMasterModel();
		model.initiate(context, session);
		model.getCurrencyRecord(currencyMaster);
		getNavigationPanel(3);
		model.terminate();
		return "view";
		}catch(Exception e){
			logger.info("getRcord - Model -- "+e);
			return null;
		}
	}
	
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		
		String code[] = request.getParameterValues("hdeleteCode");
		
		CurrencyMasterModel model = new CurrencyMasterModel();
		
		model.initiate(context, session);
		boolean result = model.deleteCurrency(currencyMaster, code);
		if(result)
		{
			addActionMessage(getText("delMessage"));			
		}
		else
		{
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}
		reset();
		getNavigationPanel(1);
		model.data(currencyMaster, request);
		model.terminate();
		return SUCCESS;
	}
	
	public String save()
	{
		try{
			CurrencyMasterModel model = new CurrencyMasterModel();
			model.initiate(context, session);
			boolean result;
			if(currencyMaster.getCurCode().equals(""))
			{
				result = model.addCurrency(currencyMaster);
				if(result)
				{	
					getNavigationPanel(3);
					currencyMaster.setEnableAll("N");
					addActionMessage(getText("addMessage", ""));
					return "view";
				}
				else
				{	
					getNavigationPanel(1);
					addActionMessage("Duplicate Entry Found");
					return SUCCESS;
				}
			}
			else
			{
				logger.info("Modification");
				result = model.modCurrency(currencyMaster);
				if(result)
				{
					getNavigationPanel(3);
					currencyMaster.setEnableAll("N");
					addActionMessage(getText("modMessage",""));
					return "view";
				}
				else
				{
					getNavigationPanel(1);
					addActionMessage("Duplicate Entry Found");
					return SUCCESS;
				}
			}//End of else
		}//End of try
		catch(Exception e){
			logger.error("Exception in save method "+e);
			return null;
		}
	}//End of save
	public String reset()
	{
		try{
			currencyMaster.setCurCode("");
			currencyMaster.setCurName("");
			currencyMaster.setCurAbbr("");
			currencyMaster.setDescription("");
			currencyMaster.setCurStatus("");
			getNavigationPanel(2);
			return "view";
		}catch(Exception e){
			logger.info("Exception at cancel in Travel Currency Master "+e);
			return null;
		}
	}//End of reset()
	public String cancel()
	{
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		}catch(Exception e){
			logger.info("Exception at cancel in Travel Currency Master "+e);
			return null;
		}
	}//End of cancel
	
	
	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {
		
		CurrencyMasterModel model = new CurrencyMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteCurrency(currencyMaster);
		if(result)
		{
			addActionMessage(getText("delMessage"));
		}
		else
		{
			addActionMessage("Record can't be deleted \n as it is associated with some other record");
		}
		reset();
		getNavigationPanel(1);
		model.data(currencyMaster, request);
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callforedit(){
		try{
			CurrencyMasterModel model = new CurrencyMasterModel();
			model.initiate(context, session);
			model.callforedit(currencyMaster);
			getNavigationPanel(3);
			currencyMaster.setEnableAll("N");
			model.terminate();
			return "view";
		}catch(Exception e){
			logger.info("Exception in callforEdit"+e);
			return null;
		}
	}
	
	
	public String report() throws Exception {
		try {
			CurrencyMasterModel model = new CurrencyMasterModel();
			model.initiate(context, session);
			String[] label = { "Sr.No", getMessage("currencyname"),
					getMessage("currencyabbr"),
					getMessage("currencydescription"),
					getMessage("currencystatus") };
			model.getReport(currencyMaster, request, response, context, label);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
