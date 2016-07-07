package org.struts.action.conference;

import org.paradyne.bean.conference.ConferenceMaster;
import org.paradyne.model.conference.ConferenceMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi
 * 20-12-2007
 * ConferenceMasterModel class to write business logic to save, update, delete
 * and view any conference accessories
 */
public class ConferenceMasterAction extends ParaActionSupport {

	/**
	 * Declare ConferenceMaster reference variable
	 */
	ConferenceMaster confMaster;
	
	/**
	 * Make an instance of ConferenceMasterModel.
	 */
	ConferenceMasterModel model = new ConferenceMasterModel();
	
	/**
	 * getter method for ConferenceMaster Bean instance
	 */
	public ConferenceMaster getConfMaster() {
		return confMaster;
	}

	/**
	 * setter method for ConferenceMaster Bean instance
	 */
	public void setConfMaster(ConferenceMaster confMaster) {
		this.confMaster = confMaster;
	}

	

	public Object getModel() {
		// TODO Auto-generated method stub
		return confMaster;
	}
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConferenceMasterAction.class);
	
	/**
	 * Over ridden prepare_local() method
	 */
	public void prepare_local() throws Exception {
		confMaster = new ConferenceMaster();
		confMaster.setMenuCode(394);
		//confMaster.setEnableAll("N");
		// TODO Auto-generated method stub

	}
	public String input() throws Exception {
		confMaster.setEnableAll("N");
		getNavigationPanel(1);
		return "Success";
	}
	
	/* method name : reset 
	 * purpose     : to reset all the form fields to blank values
	 * return type : String
	 * parameter   : none
	 */
	public String reset(){
		confMaster.setAccessCode("");		//set Accessory code to null
		confMaster.setAccessoryName("");	//set Accessory name to null
		confMaster.setResPersonName("");	//set responsible person name to null
		confMaster.setResPersonToken("");	//set responsible person token to null
		confMaster.setHiddencode("");
		confMaster.setHdeleteCode("");
		getNavigationPanel(2);
		return "Data";
	}
	
	/* method name : save 
	 * purpose     : to add new record or to modify the existing record
	 * return type : String
	 * parameter   : none
	 */
	public String save(){
		logger.info("inside save method------------");
		logger.info("model instance created");
		model.initiate(context,session);								//initiates the model context
		logger.info("context initialised");
		boolean result;
		
		if(confMaster.getAccessCode().equals("")){
			logger.info("add conference");
			result = model.addConference(confMaster);			//call to model.addConference method to add new record
			if(result){
				addActionMessage(getText("addMessage", ""));
				getNavigationPanel(3);
			}	//end of if
			else{
				addActionMessage("Duplicate entry found.");
				getNavigationPanel(1);
			}	//end of else
		}	//end of if
		else{
			logger.info("modify conference");
			result = model.modConference(confMaster);			//call to model.modConference nethod to modify existing record
			if(result){
				addActionMessage("Record updated successfully.");
				getNavigationPanel(3);
			}	//end of if
			else{
				addActionMessage("Duplicate entry found.");
				getNavigationPanel(1);
			}	//end of else
		}	//end of else
		model.Data(confMaster,request);
		logger.info("model terminated");
		model.terminate();										//terminate the model context
		//reset();
		return "Data";
	}
	
	/**
	 * delete method to delete record
	 */
	/* method name : delete 
	 * purpose     : to delete the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String delete(){
		logger.info("inside delete method");
		model.initiate(context,session);								//initiates the model context
		boolean result;
		result = model.deleteConference(confMaster);			//call to model.deleteConference method to delete record
		
		if(result){
			addActionMessage(getText("delMessage", ""));
			confMaster.setAccessoryName("");					//set Accessory code to null
			confMaster.setAccessCode("");						//set Accessory name to null
			confMaster.setResPersonName("");					//set responsible person name to null
			confMaster.setResPersonToken("");	
			confMaster.setHiddencode("");
			
			//set responsible person token to null
		}	//end of if
		else {
			addActionMessage("This record is referenced in other resources.So cannot delete.");
			reset();
		}	//end of else
		model.Data(confMaster,request);
		model.terminate();	
		getNavigationPanel(1); 
		return "Success";
	}
	
	/* method name : report 
	 * purpose     : to generate report
	 * return type : String
	 * parameter   : none
	 */
	/*public String report(){
		logger.info("inside report method");
		model.initiate(context,session);								//initiates the model context
		boolean result;
		result = model.report(confMaster);			//call to model.report method to select repoprt data
		
		if(!result){
			addActionMessage("Report can not be generated");
		}
		model.terminate();										//terminate the model context
		return "report";
		
	}*/
	
	/* method name : report 
	 * purpose     : to generate report
	 * return type : String
	 * parameter   : none
	 */
	public String report()throws Exception{
		logger.info("in report");
		ConferenceMasterModel model = new ConferenceMasterModel();
		logger.info("before initiate");
		model.initiate(context,session);
		 String[] headers = {"Sr.No",getMessage("conf.accessname")};		
		model.getReport(confMaster,request,response,headers);
		model.terminate();
		return null;
		
	}
	
	/* method name : f9action 
	 * purpose     : to show the selected record
	 * return type : String
	 * parameter   : none
	 */
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT ACCESSORY_CODE, ACCESSORY_NAME  FROM HRMS_CONF_ACCESORIES"
					   //+" HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, ACCESSORY_RES_PERSON, HRMS_EMP_OFFC.EMP_TOKEN  "
					   //+"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CONF_ACCESORIES.ACCESSORY_RES_PERSON) "
					   //+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					   +" ORDER BY ACCESSORY_CODE ";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Accessory Code",getMessage("conf.accessname")};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String[] headerWidth={"20", "40"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"confMaster.accessCode","confMaster.accessoryName"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0, 1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="ConferenceMaster_data.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		return "f9page";
	}
	
	/**
	 * f9action method to show the selected responsible person details
	 */
	public String f9ResPerson() throws Exception{
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query ="SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , EMP_ID "
					  +"FROM HRMS_EMP_OFFC  "
					  //+"LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "
					  +"WHERE EMP_STATUS ='S' "
					  +"ORDER BY EMP_ID";
		  
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {"Employee ID", "Employee Name"};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"confMaster.resPersonToken", "confMaster.resPersonName", "confMaster.resPersonCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}

	/* method name : prepare_withLoginProfileDetails 
	 * purpose     : to show all the accessories at the time of page loading
	 * return type : void
	 * parameter   : none
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		ConferenceMasterModel model = new ConferenceMasterModel();	
		model.initiate(context,session);
		model.Data(confMaster,request);
		model.terminate();
	}
	
	
	
	public String calfordelete()
	{
		ConferenceMasterModel model = new ConferenceMasterModel();
		model.initiate(context,session);
		boolean result;
		result = model.calfordelete(confMaster);
		if(result){
			addActionMessage(getText("trade  deleted Successfully"));
			confMaster.setAccessCode("");
			confMaster.setAccessoryName("");
			confMaster.setResPersonName("");
			confMaster.setResPersonToken("");
			confMaster.setResPersonCode("");
			//reseting all bean varibles			
		}	//end of if
		else{
			addActionMessage("Record can not be deleted");
		}	//end of else

		model.Data(confMaster,request);
		model.terminate();

		return "Success";
	}
	
	/* method name : calforedit1 
	 * purpose     : to edit the selected accessory
	 * return type : void
	 * parameter   : none
	 */
	public String calforedit1() throws Exception{
		confMaster.setAccessCode(request.getParameter("Codeparam"));
		confMaster.setHiddencode(request.getParameter("Codeparam"));
		logger.info("acccoee"+request.getParameter("Codeparam"));
		return calforedit();
	}

	
	
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return "Success";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "Data";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String calforedit() throws Exception {
		ConferenceMasterModel model = new ConferenceMasterModel();
		model.initiate(context, session);
		model.calforedit(confMaster);		
		getNavigationPanel(3);
		confMaster.setEnableAll("N");
		model.terminate();
		return "Data";
	}

	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		ConferenceMasterModel model = new ConferenceMasterModel();
		model.initiate(context, session);	
		model.Data(confMaster,request);
		getNavigationPanel(1);
		model.terminate();
		return "Success";
	}
	
	/**
	 * To set the fields after search
	 * @return String
	 * @throws Exception
	 */
	public String data() throws Exception {
		ConferenceMasterModel model = new ConferenceMasterModel();
		model.initiate(context, session);		
		getNavigationPanel(3);
		confMaster.setEnableAll("N");
		model.terminate();
		return "Data";
	}
/*public String calfordelete1()
{
ConferenceMasterModel model = new ConferenceMasterModel();
model.initiate(context,session);
String [] checkFlag=request.getParameterValues("checkList");
String [] hiddenid=request.getParameterValues("CodeIterator");
  reset();
   model.calfordelete1(confMaster,checkFlag,hiddenid);
	model.Data(confMaster,request);
	model.terminate();
	
return "Success";
}*/
	


/*public String delete1()throws Exception {
		String code[]=request.getParameterValues("hdeleteCode");
		
		ConferenceMasterModel model = new ConferenceMasterModel();
		
		model.initiate(context,session);
		boolean result =model.deletecheckedRecords(confMaster,code);
		
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("Record can not be Deleted");
			}
		

		
		model.Data(beanname,request);
		model.terminate();
		
		return reset();

	}*/
	
	/* method name : delete1 
	 * purpose     : to delete the selected accessory
	 * return type : void
	 * parameter   : none
	 */
	public String delete1()throws Exception {
		String code[]=request.getParameterValues("hdeleteCode");

		ConferenceMasterModel model = new ConferenceMasterModel();

		model.initiate(context,session);

		logger.info("within delete1 action....!");
		boolean result =model.deletecheckedRecords(confMaster,code);

		logger.info("after delete action...!");

		logger.info("after delete action...!"+result);

		if(result) {
			addActionMessage(getText("delMessage",""));
		}	//end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records.");
		}	//end of else

		logger.info("after delete action...!data...!");


		model.Data(confMaster,request);
		logger.info("model.Data");
		confMaster.setAccessCode("");		//set Accessory code to null
		confMaster.setAccessoryName("");	//set Accessory name to null
		confMaster.setResPersonName("");	//set responsible person name to null
		confMaster.setResPersonToken("");
		model.terminate();
		getNavigationPanel(1);
		logger.info("At end...!");

		return "Success";

	}

}
