package org.struts.action.payroll;

import org.paradyne.bean.payroll.HraMaster;

import org.paradyne.model.payroll.HraModel;

@SuppressWarnings("serial")
public class HraMasterAction extends org.struts.lib.ParaActionSupport{
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	HraMaster hramaster;
	 public Object getModel() {
		// TODO Auto-generated method stub
		return hramaster;
	}
	public HraMaster getHramaster() {
		return hramaster;
	}
	public void setHramaster(HraMaster hramaster) {
		this.hramaster = hramaster;
	}

	public void prepare_local() throws Exception{
		hramaster=new HraMaster();
	}

	public String delete()throws Exception {
		logger.info(" into delete");
		
		logger.info(" into delete<--------------------------------------->");	
		HraModel model = new HraModel();
		logger.info("bean model");
		model.initiate(context,session);
		boolean result =model.deleteHra(hramaster);
		if(result)
		{
			hramaster.setHraParameterCode("");
			hramaster.setHraParameterClass("");
			hramaster.setHraParameterName("");
			hramaster.setHraParameterPercentage("");
		}
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("Hra can not be Deleted");
			}
		return "success";

	}

	public String reset()throws Exception 
	{
		hramaster.setHraParameterCode("");
		hramaster.setHraParameterClass("");
		hramaster.setHraParameterName("");
		hramaster.setHraParameterPercentage("");
		return "success";
		
	}
		
		public String save()throws Exception {
			
			logger.info("1");
			HraModel model = new HraModel();
			logger.info("2");
			model.initiate(context,session);
			logger.info("3");
			boolean result ;
			
			if(hramaster.getHraParameterCode().equals("")){
				 result = model.addHra(hramaster);
			}else{
				 result = model.modHra(hramaster);
			}
			logger.info("4");
			model.terminate();
			
			if(result) {
				addActionMessage(getText("addMessage",""));
			} else {
				addActionMessage("Hra can not be added");
			}
			logger.info("5");
			return "success";
		}
		
		public String HraRecord()throws Exception {	
			logger.info("I am in model");
			HraModel model = new HraModel();
			model.initiate(context,session);
			model.getHraRecord(hramaster);
			model.terminate();
			return "success";
		}
		
		public String f9action() throws Exception {
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			String query = " SELECT DISCP_ID,DISCP_NAME FROM HRMS_DISCIPLINE ";		
			
			/**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 */ 
			String[] headers={"Discipline code", "Discipline name"};
			
			String[] headerWidth={"20", "20"};
			
			/**
			 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
			 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
			 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
			 * */ 	
			
			String[] fieldNames={"dispMaster.disciplineID","dispMaster.disciplineName"};
			
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
			String submitFlag = "true";
			
			/**		 
			 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
			 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod="DisciplineMaster_DisciplineRecord.action";
			
			/**
			 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
			 */
			
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			
			return "f9page";
		}
		
	/*	public String report() throws Exception 
		{
			HraModel model = new HraModel();
			model.initiate(context,session);
			model.getHraReport(dispMaster);
			
		return "report";
		}*/
				
}
