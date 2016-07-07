package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.LeaveMaster;

import org.paradyne.model.admin.master.LeaveMasterModel;

/**
 * @author Pradeep Ku.Sahoo 
 * 
 */

public class LeaveMasterAction extends org.struts.lib.ParaActionSupport {

	LeaveMaster lm;

	public Object getModel() {

		return lm;
	}

	public LeaveMaster getLm() {
		return lm;
	}

	/**
	 * @param lm the lm to set
	 */
	public void setLm(LeaveMaster lm) {
		this.lm = lm;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {

		lm = new LeaveMaster();
		lm.setMenuCode(202);
	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {

		LeaveMasterModel model = new LeaveMasterModel();
		model.initiate(context, session);

		model.leaveData(lm, request);
		model.terminate();
	}
	public String input() throws Exception {
		lm.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "leaveData";
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
	
	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		LeaveMasterModel model = new LeaveMasterModel();
		model.initiate(context, session);
		model.leaveData(lm, request);
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
		try {
			LeaveMasterModel model = new LeaveMasterModel();
			model.initiate(context, session);
			model.calforedit(lm);
			model.leaveData(lm, request);
			getNavigationPanel(3);
			lm.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "leaveData";
	}

	/*public String calfordelete()
		{
		LeaveMasterModel model = new LeaveMasterModel();
			model.initiate(context,session);
			boolean result;
			result = model.calfordelete(lm);
			if(result){
				addActionMessage(getText("Record deleted Successfully"));
				lm.setLeaveCode("");
				lm.setLeaveName("");
				lm.setLeaveAbbr("");
						
			}//end of if
			else{
				addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
			}//end of else
			
			model.leaveData(lm,request);
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

		LeaveMasterModel model = new LeaveMasterModel();

		model.initiate(context, session);
		String result = model.deletecheckedRecords(lm, code);

		if (result.equals("true")) {
			addActionMessage(getText("delMessage", ""));

		}//end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}//end of else
		reset();
		model.leaveData(lm, request);
		getNavigationPanel(1);
		model.terminate();

		return "success";

	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {

		LeaveMasterModel model = new LeaveMasterModel();

		model.initiate(context, session);
		boolean result;

		if (lm.getLeaveCode().equals("")) {
			logger.info("else addDept");
			result = model.addLeave(lm);
			if (result) {
				addActionMessage(getText("addMessage", ""));
				getNavigationPanel(3);
				return "leaveData";
				
			}//end of if
			else {
				addActionMessage("Duplicate Record Found");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of nested if
		else {
			logger.info("else modCity");

			result = model.modLeave(lm);
			if (result) {
				addActionMessage(getText("modMessage", ""));
				getNavigationPanel(3);
				return "leaveData";
			}//end of if
			else {
				addActionMessage("Duplicate Record Found");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of else
	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {

		try {

			lm.setLeaveCode("");
			lm.setLeaveName("");
			lm.setLeaveAbbr("");
			lm.setIsActive("");
			lm.setIsHalfPayApplicable("");
			getNavigationPanel(2);
		} catch (Exception e) {

			logger.error("Error in reset" + e);
		}
		return "leaveData";
	}

	/**
	 * To set the fields after search
	 * 
	 * @return Strin
	 * @throws Exception
	 */
	public String leaveRecord() throws Exception {
		try {
			LeaveMasterModel model = new LeaveMasterModel();
			model.initiate(context, session);
			model.getLeaveRecord(lm);
			getNavigationPanel(3);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "leaveData";
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		logger.info("in DELETE");
		LeaveMasterModel model = new LeaveMasterModel();

		model.initiate(context, session);
		boolean result;

		result = model.deleteLeave(lm);

		if (result) {

			addActionMessage(getText("delMessage",
					"Record deleted Successfully!"));

		}//end of if
		else {

			addActionMessage(getText("Record can't be deleted as \n it is associated with some other record"));

		}//end of else
		lm.setLeaveName("");
		lm.setLeaveAbbr("");		
		model.leaveData(lm, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
	}

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {

		try {
			LeaveMasterModel model = new LeaveMasterModel();
			model.initiate(context, session);
			String[] label = { "Sr.No", getMessage("leavetype"),
					getMessage("leaveabbr"),getMessage("is.halfPay"), getMessage("is.active") };
			model.getLeaveReport(lm, request, response, context, label);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT LEAVE_NAME,NVL(LEAVE_ABBR,' '),DECODE(IS_ACTIVE,'Y','YES','N','NO'),LEAVE_ID FROM HRMS_LEAVE ORDER BY upper(LEAVE_NAME) ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = {  getMessage("leavetype"), getMessage("leaveabbr"), getMessage("is.active") };

		String[] headerWidth = { "40", "40", "20" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = {  "leaveName", "leaveAbbr" , "isActive" , "leaveCode"};

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
			String submitToMethod="LeaveMaster_leaveRecord.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
