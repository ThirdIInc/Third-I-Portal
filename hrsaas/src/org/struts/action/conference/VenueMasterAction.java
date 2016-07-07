package org.struts.action.conference;
import org.paradyne.bean.conference.VenueMaster;
import org.paradyne.model.conference.ConferenceMasterModel;
import org.paradyne.model.conference.VenueMasterModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi
 * 29-12-2007
 * VenueMasterAction class to save, update, delete
 * and view any conference rooms
 */
public class VenueMasterAction extends ParaActionSupport {

	/**
	 * Declare VenueMaster reference variable
	 */
	VenueMaster venueMaster;

	/**
	 * Make an instance of VenueMasterModel.
	 */
	VenueMasterModel model = new VenueMasterModel();

	/**
	 * getter method for VenueMaster Bean instance
	 */
	public VenueMaster getVenueMaster() {
		return venueMaster;
	}

	/**
	 * setter method for VenueMaster Bean instance
	 */
	public void setVenueMaster(VenueMaster venueMaster) {
		this.venueMaster = venueMaster;
	}

	/**
	 * Over ridden prepare_local() method
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		venueMaster = new VenueMaster();
		venueMaster.setMenuCode(418);

	}

	public String input() throws Exception {
		venueMaster.setEnableAll("N");
		getNavigationPanel(1);
		return "Success";
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return venueMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VenueMasterAction.class);

	/* method name : save 
	 * purpose     : to add new record or to modify the existing record
	 * return type : String
	 * parameter   : none
	 */
	public String save() {
		logger.info("inside save method");
		model.initiate(context, session); //initiates the model context
		logger.info("context initiated");

		/*boolean result;
		if(venueMaster.getVenueCode().equals("")){
			logger.info("add venue");
			result = model.addVenue(venueMaster);					//call to model.addVenue method to add new record
			if(result){
				addActionMessage(getText("addMessage", ""));
			}	//end of if
			else{
				addActionMessage("Venue can not be added");
			}	//end of else
		}	//end of if
		else{
			logger.info("modify the venue");
			result = model.modVenue(venueMaster);					//call to model.modVenue nethod to modify existing record
			if(result){
				addActionMessage(getText("modMessage", ""));
			}else{
				addActionMessage("Venue can nort be modified");
			}	//end of else
		}	//end of else*/

		String str = "";

		if (venueMaster.getVenueCode().equals("")) {
			//  write the action function  like this ....!  model.function(passbean as argument);
			str = model.addVenue(venueMaster);
		} //end of if
		else {
			str = model.modVenue(venueMaster);
		} //end of else

		model.Data(venueMaster, request);
		model.terminate();
		addActionMessage(getText(str));
		//reset();
		getNavigationPanel(3);
		return "Data";
	}

	/* method name : delete
	 * purpose     : to delete the selected conference room
	 * return type : String
	 * parameter   : none
	 */
	public String delete() {
		logger.info("inside delete method");
		model.initiate(context, session); //initiates the model context
		boolean result;
		result = model.deleteVenue(venueMaster); //call to model.deleteVenue method to delete record
		if (result) {
			addActionMessage(getText("delMessage", ""));
			venueMaster.setVenueName("");
			venueMaster.setVenueCode("");
		} //end of if
		else {
			addActionMessage("This record is referenced in other resources.So cannot delete.");
			venueMaster.setVenueName("");
			venueMaster.setVenueCode("");
		} //end of else
		model.Data(venueMaster, request);
		model.terminate(); //terminate the model context
		getNavigationPanel(1);
		return "Success";
	}

	/* method name : report 
	 * purpose     : to generate the report
	 * return type : String
	 * parameter   : none
	 */
	/*public String report(){
		logger.info("Inside report method");
		model.initiate(context,session);									//initiates the model context
		boolean result;
		result = model.report(venueMaster);					//call to model.report method to select report data
		if(!result){
			addActionMessage("Report Can not be generated");
		}
		model.terminate();											//terminate the model context
		return "report";
	}*/

	/* method name : report 
	 * purpose     : to generate the report
	 * return type : String
	 * parameter   : none
	 */
	public String report() throws Exception {
		model.initiate(context, session);
		String[] headers = { "Sr.No", getMessage("conference.name"),
				getMessage("branch"), getMessage("conference.respPerson") };
		model.getReport(venueMaster, request, response, headers);
		model.terminate();
		return null;
	}

	/* method name : reset 
	 * purpose     : to reset all the form fields to blank values
	 * return type : String
	 * parameter   : none
	 */
	public String reset() {
		venueMaster.setVenueCode(""); //set venue code to null		
		venueMaster.setVenueName("");
		venueMaster.setHiddencode("");
		venueMaster.setResPersonCode("");
		venueMaster.setResPersonName("");
		venueMaster.setResPersonToken("");
		venueMaster.setBranchCode("");
		venueMaster.setBranchName("");
		getNavigationPanel(2);
		return "Data";
	}

	/* method name : f9action 
	 * purpose     : to show the selected conference room details
	 * return type : String
	 * parameter   : none
	 */
	public String f9action() {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT VENUE_NAME,CENTER_NAME,EMP_FNAME ||' '||EMP_MNAME   ||' '||EMP_LNAME NAME, "
				+ "	VENUE_CODE,VENUE_BRANCH,VENUE_RES_PERSON,EMP_TOKEN FROM HRMS_CONF_VENUE "
				+ " LEFT JOIN HRMS_CENTER ON(CENTER_ID= VENUE_BRANCH)"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=VENUE_RES_PERSON) ORDER BY VENUE_NAME ";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("conference.name"),
				getMessage("branch"), getMessage("conference.respPerson") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "40", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = { "venueMaster.venueName", "branchName",
				"resPersonName", "venueMaster.venueCode", "branchCode",
				"resPersonCode", "resPersonToken" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "VenueMaster_data.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9ResPersonAction() throws Exception {

		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME) NAME,EMP_ID FROM HRMS_EMP_OFFC ";

		query += getprofileQuery(venueMaster);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "resPersonToken", "resPersonName",
				"resPersonCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9BranchAction() throws Exception {

		String query = "SELECT CENTER_ID,CENTER_NAME  FROM HRMS_CENTER  ORDER BY CENTER_NAME";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "branchCode", "branchName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/* method name : prepare_withLoginProfileDetails 
	 * purpose     : to show all the conference rooms at the time of page loading
	 * return type : String
	 * parameter   : none
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		VenueMasterModel model = new VenueMasterModel();
		model.initiate(context, session);
		model.Data(venueMaster, request);
		model.terminate();
	}

	/* method name : callPage 
	 * purpose     : to show all the conference rooms as per the selected page no
	 * return type : String
	 * parameter   : none
	 */
	public String callPage() throws Exception {

		VenueMasterModel model = new VenueMasterModel();
		model.initiate(context, session);
		model.Data(venueMaster, request);
		model.terminate();
		getNavigationPanel(1);
		return "Success";

	}

	public String data() throws Exception {
		VenueMasterModel model = new VenueMasterModel();
		model.initiate(context, session);
		getNavigationPanel(3);
		model.terminate();
		return "Data";
	}

	/* method name : calforedit 
	 * purpose     : to edit the selected conference room
	 * return type : String
	 * parameter   : none
	 */
	public String calforedit() throws Exception {
		VenueMasterModel model = new VenueMasterModel();
		model.initiate(context, session);
		model.calforedit(venueMaster);
		//	getRecord();

		model.Data(venueMaster, request);
		getNavigationPanel(3);
		venueMaster.setEnableAll("N");
		model.terminate();
		return "Data";
	}

	/* method name : calfordelete 
	 * purpose     : to delete the selected conference room
	 * return type : String
	 * parameter   : none
	 */
	public String calfordelete() {
		VenueMasterModel model = new VenueMasterModel();
		model.initiate(context, session);
		boolean result;
		result = model.calfordelete(venueMaster);
		if (result) {
			addActionMessage(getText("Record deleted Successfully"));
			venueMaster.setVenueCode("");
			venueMaster.setVenueName("");
			venueMaster.setHiddencode("");
		} //end of if
		else {
			addActionMessage("Record can not be deleted");
		} //end of else

		model.Data(venueMaster, request);
		model.terminate();

		return "Success";
	}

	/* method name : delete1 
	 * purpose     : to delete the selected conference room
	 * return type : String
	 * parameter   : none
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		VenueMasterModel model = new VenueMasterModel();

		model.initiate(context, session);
		boolean result = model.deletecheckedRecords(venueMaster, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
		} //end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		} //end of else

		model.Data(venueMaster, request);
		model.terminate();

		reset();
		getNavigationPanel(1);
		return "Success";
	}

	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return "Success";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "Data";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
}
