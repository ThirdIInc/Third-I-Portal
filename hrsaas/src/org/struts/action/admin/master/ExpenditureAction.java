package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.Expenditure;
import org.paradyne.model.admin.master.ExpenditureModel;
 import org.struts.lib.ParaActionSupport;

public class ExpenditureAction extends ParaActionSupport {
	Expenditure expend;
	 
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
	
		expend = new Expenditure();
		expend.setMenuCode(304);
	}

	 public Object getModel() {
		// TODO Auto-generated method stub
		return expend;
	}

	
	
	public Expenditure getExpend() {
		return expend;
	}

	public void setExpend(Expenditure expend) {
		this.expend = expend;
	}

	public String save()
	{
		ExpenditureModel model = new ExpenditureModel();
		model.initiate(context,session);
		/*if(expend.getExpCode().equals(""))
		{
			boolean flag =model.add(expend);
			if(flag==true)
			{
				addActionMessage("Record Saved Sucessfully");
			}else{
				addActionMessage("Expenditure cannot be added");
			}
		}else
		{
			boolean flag =model.update(expend);
			if(flag==true)
			{
				addActionMessage("Record modified Sucessfully");
			}
			else{
				addActionMessage("Expenditure cannot be added");
			}
			
		}*/
			String str="";
		
		if(expend.getExpCode().equals(""))
		{		
				//  write the action function  like this ....!  model.function(passbean as argument);
				
			 str = model.add(expend);
		
		}
		else
		{
		
			 str = model.update(expend);
			
			 
		}
		addActionMessage(getText(str));
		model.Data(expend,request);
		model.terminate();
		
		 expend.setExpCode("");
		 expend.setExpName("");
	
		return SUCCESS;
	}
	
	public String reset()
	{
		 expend.setExpCode("");
		expend.setExpName("");
		 
		return SUCCESS;
	}
	
	public String delete()
	{
		ExpenditureModel model = new ExpenditureModel();
		model.initiate(context,session);
		 
		boolean flag =model.deleteExpend(expend); 
		if(flag){
		addActionMessage("Record deleted Sucessfully ");
		}else
		{
			addActionMessage("This data used in another resources so cannot be deleted ");
		}
		expend.setExpCode("");
		expend.setExpName("");
		model.Data(expend,request);
		model.terminate();
		return SUCCESS;
	}
	
	
	public String report()
	{
		ExpenditureModel model = new ExpenditureModel();
		model.initiate(context,session);
		 
	// model.report(expend); 
		model.getReport(expend,request,response,context,session);
		model.terminate();
		//return "report";
		return null;
	}
	
	
	
	
	
	
	
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT EXPENDITURE_CODE,EXPENDITURE_NAME  FROM HRMS_DEPUT_EXPEND_TYPE ORDER BY EXPENDITURE_CODE ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Expenditure Code", "Expenditure Name"};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"expend.expCode","expend.expName"};
		
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
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	
	public void prepare_withLoginProfileDetails() throws Exception {
		ExpenditureModel model = new ExpenditureModel();	
				model.initiate(context,session);
					
				model.Data(expend,request);
				model.terminate();
		}
		public String callPage() throws Exception {
			
			ExpenditureModel model = new ExpenditureModel();
				model.initiate(context,session);
				model.Data(expend,request);
				model.terminate();
			 return SUCCESS;
			
			}
		
		public String calforedit() throws Exception{
			ExpenditureModel model = new ExpenditureModel();
			model.initiate(context,session);
			model.calforedit(expend);
			//getRecord();
			
		
			model.Data(expend,request);
			model.terminate();
			return "success";
		}
		
	public String calfordelete()
		{
		ExpenditureModel model = new ExpenditureModel();
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(expend);
			if(result){
				addActionMessage(getText("Record  Deleted Successfully"));
					//reseting all bean varibles		
				expend.setExpCode("");
				expend.setExpName("");
				expend.setHiddencode("");
			}
			else{
				addActionMessage("This record is referenced in other resources.So cannot delete.");
			}
			
			model.Data(expend,request);
			model.terminate();
			
		return "success";
		}


	public String delete1()throws Exception {
			String code[]=request.getParameterValues("hdeleteCode");
			
			ExpenditureModel model = new ExpenditureModel();
			
			model.initiate(context,session);
			boolean result =model.deletecheckedRecords(expend,code);
			
				if(result) {
					addActionMessage(getText("delMessage",""));
				} else {
					addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
				}
			

			
			model.Data(expend,request);
			model.terminate();
			
			return reset();

		}
	
	

}
