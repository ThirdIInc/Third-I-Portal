package org.struts.action.admin.master;
 import org.struts.lib.ParaActionSupport;

import org.paradyne.bean.admin.master.ForeignExchange;

import org.paradyne.model.admin.master.ForeignExchangeModel;
/*
 * author:Pradeep Kumar Sahoo
 * date:24.08.2007
 */
public class ForeignExchangeAction extends ParaActionSupport {
	
	ForeignExchange fe;
	
	public Object getModel()
	{
		
		return fe;
	}

	public ForeignExchange getFe() {
		return fe;
	}

	public void setFe(ForeignExchange fe) {
		this.fe = fe;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
	
		fe =new ForeignExchange();
		fe.setMenuCode(289);
	}
	
	
	public String save()throws Exception 
	{
		
		ForeignExchangeModel model = new ForeignExchangeModel();
		
		model.initiate(context,session);
		String str="";
		
		if(fe.getFrnCode().equals(""))
		{
			
			
			str=model.addRecord(fe);
		}
		else
		{
			
			 str = model.modRecord(fe);
			 
		}
		model.Data(fe,request);
		model.terminate();
		 
	
			addActionMessage(getText(str));
			fe.setConvRate("");
			fe.setCountry("");
			fe.setFrnCode("");
			fe.setCurrency("");
			fe.setType("");
	
		
		
		return "success";
		}
	
	public String delete() throws Exception
	{
		
		ForeignExchangeModel model = new ForeignExchangeModel();
		
		model.initiate(context,session);
		boolean result ;
		
		result = model.delete(fe);  
		
		if(result)
		{
			
			addActionMessage(getText("delMessage"));
			fe.setConvRate("");
			fe.setCountry("");
			fe.setFrnCode("");
			fe.setCurrency("");
			fe.setType("");
			
		}else {
			addActionMessage(getText("This record is referenced in other resources.So cannot delete."));
			
		}
		model.Data(fe,request);
		model.terminate();
		return "success";
	}
	
	
	public String reset() {
		
		try {
		fe.setConvRate("");
		fe.setCountry("");
		fe.setFrnCode("");
		fe.setCurrency("");
		fe.setType("");
		} catch (Exception e) { }
		
		return "success";
		
	}
	
	public String frnRecord()throws Exception{
		ForeignExchangeModel model = new ForeignExchangeModel();
		model.initiate(context,session);
		model.getRecord(fe);
		model.terminate();
		
		return SUCCESS;
	}

	public String report()throws Exception{
		ForeignExchangeModel model = new ForeignExchangeModel();
		model.initiate(context,session);
		/*model.getReport(fe);
		model.terminate();
		
		return "report";*/

		model.getReport(fe,request,response,context);
		model.terminate();
		return null;

	
		
	}
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT COUNTRY_CODE,NVL(COUNTRY_NAME,' '),NVL(COUNTRY_CURRENCY,'') FROM HRMS_COUNTRY ORDER BY COUNTRY_CODE ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Exchange Code", "Country","Country Currency" };
		
		String[] headerWidth={"20", "25","20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"frnCode","country","currency"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="ForeignExchange_frnRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		ForeignExchangeModel model = new ForeignExchangeModel();	
				model.initiate(context,session);
					
				model.Data(fe,request);
				model.terminate();
		}
		public String callPage() throws Exception {
			
			ForeignExchangeModel model = new ForeignExchangeModel();
				model.initiate(context,session);
				model.Data(fe,request);
				model.terminate();
			 return SUCCESS;
			
			}
		
		public String calforedit() throws Exception{
			ForeignExchangeModel model = new ForeignExchangeModel();
			model.initiate(context,session);
			model.calforedit(fe);
			//getRecord();
			
		
			model.Data(fe,request);
			model.terminate();
			return "success";
		}
		
	public String calfordelete()
		{
		ForeignExchangeModel model = new ForeignExchangeModel();
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(fe);
			if(result){
				addActionMessage(getText("Record  Deleted Successfully"));
					//reseting all bean varibles
				fe.setConvRate("");
				fe.setCountry("");
				fe.setCurrency("");
				fe.setFrnCode("");
				fe.setType("");
				fe.setHiddencode("");
			}
			else{
				addActionMessage("This record is referenced in other resources.So cannot delete.");
			}
			
			model.Data(fe,request);
			model.terminate();
			
		return "success";
		}
	


	public String delete1()throws Exception {
			String code[]=request.getParameterValues("hdeleteCode");
			
			ForeignExchangeModel model = new ForeignExchangeModel();
			
			model.initiate(context,session);
			boolean result =model.deletecheckedRecords(fe,code);
			
				if(result) {
					addActionMessage(getText("delMessage",""));
				} else {
					addActionMessage("One or more records can't be deleted \n as they are associated with some other records.");
				}
			

			
			model.Data(fe,request);
			model.terminate();
			
			return reset();

		}

	
	

}
