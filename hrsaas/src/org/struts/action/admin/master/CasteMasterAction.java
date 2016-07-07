package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.CasteMaster;
import org.paradyne.model.admin.master.CasteModel;
import org.struts.lib.ParaActionSupport;

public class CasteMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CasteMasterAction.class);
	CasteMaster casteMaster;

	
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "casteData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	public String cancel() {
		try {

			prepare_withLoginProfileDetails();
			reset();
			getNavigationPanel(1);
			return SUCCESS;
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

	public String calfordelete() {
		CasteModel model = new CasteModel();
		model.initiate(context, session);
		
		boolean result;
		result = model.calfordelete(casteMaster);
		
		if(result) {
			addActionMessage(getMessage("delete"));
			casteMaster.setCasteCatgCode("");
			casteMaster.setCasteCatgName("");
			casteMaster.setCasteCode("");
			casteMaster.setCasteName("");
			casteMaster.setIsActive("");
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.castData(casteMaster, request);
		model.terminate();
		return "success";
	}

	public String calforedit() throws Exception {
		CasteModel model = new CasteModel();
		model.initiate(context, session);
		model.calforedit(casteMaster);
		getNavigationPanel(3);
		casteMaster.setEnableAll("N");
		model.terminate();
		return "casteData";
	}

	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		CasteModel model = new CasteModel();
		model.initiate(context, session);
		model.castData(casteMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		CasteModel model = new CasteModel();
		model.initiate(context, session);
		
		boolean result = model.delete(casteMaster);
		
		if(result == true) {
			addActionMessage(getMessage("delete"));

			//casteMaster.setCasteCatgName("");
			//casteMaster.setCasteName("");
		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else

		model.castData(casteMaster, request);
		reset();		
		getNavigationPanel(1);
		model.terminate();
		return "success";
	}

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		CasteModel model = new CasteModel();
		model.initiate(context, session);
		
		String result = model.deletecheckedRecords(casteMaster, code);

		if(result.equals("true")) {
			addActionMessage(getMessage("delete"));
			reset();
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.castData(casteMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
		//return reset();
	}
	public String casteRecord() throws Exception {
		CasteModel model = new CasteModel();
		model.initiate(context, session);
		
		boolean b = model.data(casteMaster);
		getNavigationPanel(3);
		model.terminate();
		return "casteData";
	}
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT  CAST_NAME , NVL(HRMS_CATG.CATG_NAME,'') ,DECODE(HRMS_CAST.IS_ACTIVE,'Y','YES','N','NO','NO'), CAST_CATG,CAST_ID  FROM HRMS_CAST  "
				+ " left JOIN HRMS_CATG ON (HRMS_CAST.CAST_CATG=HRMS_CATG.CATG_ID) ORDER BY upper(CAST_NAME)  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("castename"), getMessage("castcategory"), getMessage("is.active")};

		String[] headerWidth = { "40", "40", "20"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "casteMaster.casteName", "casteMaster.casteCatgName", "casteMaster.isActive", "casteMaster.casteCatgCode","casteMaster.casteCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1, 2, 3,4};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "CasteMaster_casteRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9catAction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT  CATG_NAME,CATG_ID  FROM HRMS_CATG ORDER BY CATG_NAME ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("castcategory")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "casteMaster.casteCatgName","casteMaster.casteCatgCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "flase";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		casteMaster.setMasterMenuCode(33);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public CasteMaster getCasteMaster() {
		return casteMaster;
	}

	public Object getModel() {
		return casteMaster;
	}
	public String input() throws Exception {
		casteMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public void prepare_local() throws Exception {
		casteMaster = new CasteMaster();
		casteMaster.setMenuCode(32);
	}

	/** called on load to set the list* */
	public void prepare_withLoginProfileDetails() throws Exception {
		CasteModel model = new CasteModel();
		model.initiate(context, session);

		model.castData(casteMaster, request);
		
		model.terminate();
	}

	/**
	 * To generate report
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		CasteModel model = new CasteModel();
		model.initiate(context, session);
		String [] label={"Sr.No",getMessage("castename"),getMessage("castcategory"),getMessage("is.active")};
		model.getReport(casteMaster, request, response, context, session,label);
		model.terminate();
		return null;
	}

	/**
	 * To reset the fields
	 * @return String
	 * @throws Exception
	 */
	public String reset() {
		casteMaster.setCasteCatgCode("");
		casteMaster.setCasteCatgName("");
		casteMaster.setCasteCode("");
		casteMaster.setCasteName("");
		casteMaster.setIsActive("");
		getNavigationPanel(2);
		return "casteData";
		//return SUCCESS;
	}

	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		CasteModel model = new CasteModel();
		model.initiate(context, session);
		
		if(casteMaster.getCasteCode().equals("")) {
			boolean result = model.add(casteMaster);
			if(result == true) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "casteData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";

			}// end of else
		} else {
			boolean result = model.updateCaste(casteMaster);
			
			if(result == true) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "casteData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}// end of else
		}// end of else
		
		/*model.castData(casteMaster, request);
		
		casteMaster.setCasteCatgCode("");
		casteMaster.setCasteCatgName("");
		casteMaster.setCasteCode("");
		casteMaster.setCasteName("");
		
		model.terminate();
		return "success";*/
	}

	public void setCasteMaster(CasteMaster casteMaster) {
		this.casteMaster = casteMaster;
	}
}