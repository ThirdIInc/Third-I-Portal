package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.DivisionMaster;
import org.paradyne.model.admin.master.DivisionModel;

/**
 * @author Pradeep Kumar Date 14-05-07
 * @modified by Reeba
 * @modified by Debajani
 * modified by Anantha lakshmi
 */

public class DivisionAction extends org.struts.lib.ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DivisionAction.class);
	DivisionMaster divMast;

	/**
	 * @return the divMast
	 */
	public DivisionMaster getDivMast() {
		return divMast;
	}

	public Object getModel() {
		return divMast;
	}
	public String input() throws Exception {
		divMast.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public void prepare_local() throws Exception {
		divMast = new DivisionMaster();
		divMast.setMenuCode(42);
	}

	/**
	 * called on load to set the list
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		System.out.println("----------prepare_withLoginProfileDetails---------------");
		DivisionModel model = new DivisionModel();
		model.initiate(context, session);
		model.divData(divMast, request);
		model.terminate();
	}

	/**
	 * @param divMast the divMast to set
	 */
	public void setDivMast(DivisionMaster divMast) {
		this.divMast = divMast;
		divMast.setMenuCode(42);
		
	}
	
/**to display the save mode( next mode)
 * 
 */ 
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "showData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {		
		try {
			DivisionModel model = new DivisionModel();
			model.initiate(context, session);
			model.calforedit(divMast);
			model.data(divMast);
			//data();
			getNavigationPanel(3);
			divMast.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return "showData";
	}

	public String getRecords() throws Exception {		
		DivisionModel model = new DivisionModel();
		model.initiate(context, session);
		model.calforedit(divMast);
		model.data(divMast);
		//data();
		getNavigationPanel(2);
		divMast.setEnableAll("Y");
		model.terminate();
		return "showData";
	}
	
	
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		DivisionModel model = new DivisionModel();
		model.initiate(context, session);
		model.divData(divMast, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	/**
	 *  to cancel the all the  mode 
	 *   
	 * @return
	 */
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
	 * To set the fields after search
	 * @return String
	 * @throws Exception
	 */
	
   public String data() throws Exception {
		DivisionModel model = new DivisionModel();
		model.initiate(context, session);
		model.data(divMast);
		getNavigationPanel(3);
		model.terminate();
		return "showData";
	}

	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		DivisionModel model = new DivisionModel();
		model.initiate(context, session);
		
		boolean result = model.deleteDiv(divMast);
		model.divData(divMast, request);
		
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));

		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		//return reset();
		divMast.setDivId("");
		divMast.setDivName("");
		divMast.setCity("");
		//divMast.setBank("");
		//divMast.setBankid("");
		return "success";
	}

	/**
	 * To delete one or more records from the list
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		DivisionModel model = new DivisionModel();

		model.initiate(context, session);
		boolean result = model.deleteDivision(divMast, code);
		
		if(result) {
			addActionMessage(getMessage("delete"));
			reset();
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.divData(divMast, request);
		getNavigationPanel(1);
		model.terminate();
		
		return "success";
	}

	/**
	 * To set the fields after search
	 * @return String
	 * @throws Exception
	 */
	public String divRecord() throws Exception {
		DivisionModel model = new DivisionModel();
		model.initiate(context, session);
		model.getDivRecord(divMast);
		model.terminate();
		return SUCCESS;
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		/* String query = " SELECT DIV_NAME,HRMS_LOCATION.LOCATION_NAME " 
			    + " ,DIV_ID FROM HRMS_DIVISION "
				+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID) "
				+ " WHERE HRMS_LOCATION.LOCATION_TYPE='Ci' " 
				+ " ORDER BY DIV_NAME ";  */
		
		 String query = " SELECT DIV_NAME,HRMS_LOCATION.LOCATION_NAME " 
	    + " ,DIV_ID FROM HRMS_DIVISION "
		+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID) " 
		+ " ORDER BY DIV_NAME "; 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division"), getMessage("city")};

		String[] headerWidth = { "50", "50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"divMast.divName", "divMast.city","divMast.divId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1,2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "DivisionMaster_data.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9bank() throws Exception {
		String query = " SELECT BANK_NAME,BANK_MICR_CODE FROM HRMS_BANK  ORDER BY BANK_NAME ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("bank")};

		String[] headerWidth = { "100"};

		String[] fieldNames = { "divMast.bank","divMast.bankid"};

		int[] columnIndex = {0,1};

		String submitFlag = "false";

		String submitToMethod = "";
		
		divMast.setMasterMenuCode(34);

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9city() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT   LOCATION_NAME,LOCATION_CODE FROM HRMS_LOCATION where LOCATION_LEVEL_CODE=2 ORDER BY LOCATION_NAME";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("city")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "divMast.city","divMast.locId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		
		divMast.setMasterMenuCode(20);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9citCity() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT   LOCATION_NAME,LOCATION_CODE FROM HRMS_LOCATION where LOCATION_LEVEL_CODE=2 ORDER BY LOCATION_NAME";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("citCityName")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "citCity","citCityId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		
		divMast.setMasterMenuCode(20);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9lglStsEstablishment() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT   LEGALSTATUS_OF_ESTBLSHMNT,LEGAL_STATUS_ID FROM LMS_LEGALSTAT_OF_ESTBLSHMNT  ORDER BY LEGAL_STATUS_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("lglStsEstablishment")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "divMast.lglStsEstablishment","divMast.lglStsEstablishmentId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		
		divMast.setMasterMenuCode(20);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	
	public String f9titleAct() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT    TITLE_OF_ACT,TITLE_OF_ACT_ID FROM LMS_TITLE_OF_ACTS  ORDER BY TITLE_OF_ACT_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("titleAct")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "divMast.titleAct","divMast.titleActId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		
		divMast.setMasterMenuCode(20);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	
	public String f9ownership() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT   OWNERSHIP_NAME,OWNERSHIP_ID FROM LMS_OWNERSHIP  ORDER BY OWNERSHIP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("ownership")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "divMast.ownership","divMast.ownershipId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		
		divMast.setMasterMenuCode(20);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}


	

	/**
	 * To generate report
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		DivisionModel model = new DivisionModel();
		model.initiate(context, session);
		String[]label={"Sr.No",getMessage("division"),getMessage("divAbbr"),getMessage("divisiondesc"),getMessage("address"),
				getMessage("city"),getMessage("pincode"),getMessage("telephone"),getMessage("website"),
				getMessage("is.active")};
		model.getReport(divMast, request, response, context,label);
		model.terminate();
		return null;
	}

	/**
	 * To reset the fields
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			divMast.setDivId("");
			divMast.setDivName("");
			divMast.setDivDesc("");
			divMast.setLocId("");
			divMast.setLocName("");
			divMast.setCity("");
			divMast.setAdd1("");
			divMast.setAdd2("");
			divMast.setAdd3("");
			divMast.setPin("");
			divMast.setTel("");
			divMast.setTelStd("");
			divMast.setWebsite("");
			divMast.setDivAbbr("");
			divMast.setEmailAdress("");
			divMast.setEname("");
			divMast.setEdesignation("");
			divMast.setEteleNo("");
			divMast.setEmobNo("");
			divMast.setEfax("");
			divMast.setEemail("");
			divMast.setEtelStd("");
			divMast.setMname("");
			divMast.setMdesignation("");
			divMast.setMteleNo("");
			divMast.setMmobNo("");
			divMast.setMfax("");
			divMast.setMemail("");
			divMast.setLogoName("");
			divMast.setIsActive("Yes");
			divMast.setDivisionDisplayName("");
			getNavigationPanel(2);
		} catch(Exception e) {
			logger.error("Error in reset" + e);
		}
		return "showData";
	}

	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		DivisionModel model = new DivisionModel();
		model.initiate(context, session);
		
		boolean result;

		if(divMast.getDivId().equals("")) {
			logger.info("else addDiv");
			result = model.addDiv(divMast);
			if(result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "showData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}// end of else
		} else {
			result = model.modDiv(divMast);
			System.out.println("------RESULT--------------"+result);
			model.terminate();
			if(result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "showData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();// new added
				getNavigationPanel(1);
				return "success";
			}// end of else
		}// end of else
		
		
	}
}