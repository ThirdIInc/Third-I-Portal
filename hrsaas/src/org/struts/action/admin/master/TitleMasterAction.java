package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.TitleMaster;

import org.paradyne.model.admin.master.CenterModel;
import org.paradyne.model.admin.master.TitleMasterModel;

/*
 * Pradeep Ku Sahoo
 * Date:26.06.2007
 */

public class TitleMasterAction extends org.struts.lib.ParaActionSupport {

	TitleMaster tm = null;

	public TitleMaster getTm() {
		return tm;
	}

	public void setTm(TitleMaster tm) {
		this.tm = tm;
	}

	public Object getModel() {

		return tm;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	
	

	public String input() throws Exception {
		tm.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "titleData";
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
	public void prepare_local() throws Exception {
		tm = new TitleMaster();
		tm.setMenuCode(206);

	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {

		TitleMasterModel model = new TitleMasterModel();

		model.initiate(context, session);

		model.hasData1(tm, request);
		model.terminate();
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String callPage() throws Exception {

		TitleMasterModel model = new TitleMasterModel();
		model.initiate(context, session);
		model.hasData1(tm, request);
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
		TitleMasterModel model = new TitleMasterModel();
		model.initiate(context, session);
		model.calforedit(tm);

		model.hasData1(tm, request);
		getNavigationPanel(3);
		tm.setEnableAll("N");
		model.terminate();
		return "titleData";
	}


	/**
	 * To delete any  records 
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {

		TitleMasterModel model = new TitleMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteTitle(tm);
		model.hasData1(tm, request);
		model.terminate();
		if (result) {
			addActionMessage(getMessage("delete"));
		}//end of if
		else {
			addActionMessage(getMessage("del.error"));
		}//end of else
		tm.setTitleCode("");
		tm.setTitleName("");		
		getNavigationPanel(1);
		return "success";
	}

	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		TitleMasterModel model = new TitleMasterModel();

		model.initiate(context, session);
		boolean result = model.deleteTitle(tm, code);
		if (result) {
			addActionMessage(getMessage("delete"));
			tm.setTitleCode("");
			tm.setTitleName("");

		}//end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}//end of else

		model.hasData1(tm, request);
		getNavigationPanel(1);
		model.terminate();

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

			tm.setTitleCode("");
			tm.setTitleName("");
			getNavigationPanel(2);
		} catch (Exception e) {

			logger.error("Error in reset" + e);
		}
		return "titleData";
	}

	/*public String report() throws Exception{
		TitleMasterModel model= new TitleMasterModel();
		model.initiate(context,session);
		model.callReport(tm);
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
		TitleMasterModel model = new TitleMasterModel();
		model.initiate(context, session);
		String []label={"Sr.No",getMessage("title"),getMessage("is.active")};
		model.getReport(tm, request, response, context, session,label);
		model.terminate();
		return null;
	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		TitleMasterModel model = new TitleMasterModel();
		model.initiate(context, session);
		System.out.println("############## IN SAVE METHOD #############");

		boolean result;

		if (tm.getTitleCode().equals("")) {
			result = model.addTitle(tm);
			if (result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "titleData";
			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of nested if
		else {
			result = model.modTitle(tm);
			if (result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "titleData";
			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of else
		//model.hasData1(tm, request);

		//model.terminate();

		//tm.setTitleCode("");
		//tm.setTitleName("");
		//return "success";
	}
	public String data() throws Exception {
		TitleMasterModel model = new TitleMasterModel();
		model.initiate(context, session);
		model.data(tm, request);
		getNavigationPanel(3);
		model.terminate();
		return "titleData";
	}

	public String f9action() throws Exception { // isActive field is added by Abhijit Samanta
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  TITLE_NAME, DECODE(IS_ACTIVE,'Y','Yes','N','No'), TITLE_CODE FROM HRMS_TITLE ORDER BY upper(TITLE_NAME) ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		//String[] headers ={  getMessage("title"), getMessage("is.active"), "Title Code"  };
		
		String[] headers ={  getMessage("title"), getMessage("is.active") }; 

		String[] headerWidth = { "50", "50" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = {  "titleName", "isActive", "titleCode" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1,  2};

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "TitleMaster_data.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
