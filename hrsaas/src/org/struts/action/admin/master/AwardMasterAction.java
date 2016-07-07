package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.AwardMaster;

import org.paradyne.model.admin.master.AwardMasterModel; /*
import org.paradyne.model.admin.master.DivisionModel;
 * Pradeep Kumar Sahoo
 * Date:28.06.2007
 */
import org.paradyne.model.admin.master.EmpTypeModel;

public class AwardMasterAction extends org.struts.lib.ParaActionSupport {

	AwardMaster am = null;

	public AwardMaster getAm() {
		return am;
	}

	public void setAm(AwardMaster am) {
		this.am = am;
	}

	public Object getModel() {

		return am;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		am = new AwardMaster();
		am.setMenuCode(207);
			
	}
	public String input() throws Exception {
		am.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "awardData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {

		AwardMasterModel model = new AwardMasterModel();

		model.initiate(context, session);

		model.awardData(am, request);
		model.terminate();
	}

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		AwardMasterModel model = new AwardMasterModel();
		model.initiate(context, session);
		model.awardData(am, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String calforedit() throws Exception {
		AwardMasterModel model = new AwardMasterModel();
		model.initiate(context, session);
		model.calforedit(am);

		model.awardData(am, request);
		getNavigationPanel(3);
		am.setEnableAll("N");
		model.terminate();
		return "awardData";
	}

	/*public String calfordelete()
		{
		AwardMasterModel model= new AwardMasterModel();
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(am);
			if(result){
				addActionMessage(getText("Record deleted Successfully"));
				am.setAwardType("");	
				am.setAwardCode("");	
			}
			else{
				addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
			}
			
			model.awardData(am,request);
			model.terminate();
			
		return "success";
		}*/
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		AwardMasterModel model = new AwardMasterModel();

		model.initiate(context, session);
		boolean result = model.deleteEmptype(am, code);
		if (result) {
			addActionMessage(getMessage("delete"));
			am.setAwardType("");
			am.setIsActive("");
			am.setAwardCode("");

		}//end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}//end of else

		model.awardData(am, request);
		getNavigationPanel(1);
		model.terminate();

		return "success";

	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {

		AwardMasterModel model = new AwardMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteAward(am);
		model.awardData(am, request);
		model.terminate();
		if (result) {
			addActionMessage(getMessage("delete"));
		}//end of if
		else {
			addActionMessage(getMessage("del.error"));
		}//end of else
		am.setAwardType("");
		am.setAwardCode("");		
		getNavigationPanel(1);
		return "success";
	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {

			am.setAwardType("");
			am.setIsActive("");
			am.setAwardCode("");
			getNavigationPanel(2);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in reset");
		}
		return "awardData";
	}

	/*public String report() throws Exception{
		AwardMasterModel model= new AwardMasterModel();
		model.initiate(context,session);
		model.getReport(am);
		model.terminate();
		return "report";
	}*/
	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		AwardMasterModel model = new AwardMasterModel();
		model.initiate(context, session);
		String [] label={"Sr.No", getMessage("awardtype"),getMessage("is.active")};
		model.getReport(am, request, response, context, session,label);
		model.terminate();
		return null;
	}

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String save() throws Exception {

		AwardMasterModel model = new AwardMasterModel();
		model.initiate(context, session);
		//boolean result ;
		/*	String str="";
			
			if(am.getAwardCode().equals("")){
				
				logger.info("Value of award code"+am.getAwardCode());
			//	result = model.addAward(am);
				str = model.addAward(am);
			}else{
				
				 //result = model.modAward(am);
				str = model.modAward(am);
			}*/
		boolean result;

		if (am.getAwardCode().equals("")) {
			result = model.addAward(am);
			if (result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "awardData";

			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";

			}//end of else 
		}//end of nested if
		else {

			result = model.modAward(am);
			if (result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "awardData";
			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";

			} //end of else
		}
		/*model.awardData(am, request);
		logger.info("Model Terminated1");
		model.terminate();

		logger.info("5");
		am.setAwardType("");
		am.setAwardCode("");
		return "success";*/
	}

	public String award() throws Exception {
		AwardMasterModel model = new AwardMasterModel();
		model.initiate(context, session);
		boolean b = model.data(am);
		getNavigationPanel(3);
		model.terminate();
		return "awardData";
	}
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  AWARD_TYPE,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),AWARD_CODE FROM HRMS_AWARD_MASTER ORDER BY upper(AWARD_TYPE)";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("awardtype"), getMessage("is.active") };

		String[] headerWidth = {  "50" , "50" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "awardType","isActive","awardCode" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AwardMaster_award.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
