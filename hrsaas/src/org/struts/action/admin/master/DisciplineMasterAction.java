package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.DisciplineMaster;

import org.paradyne.model.admin.master.DisciplineModel;

/**
 * @author Muzaffar modified by Debjani
 */
public class DisciplineMasterAction extends org.struts.lib.ParaActionSupport {

	/**
	 * 
	 */
	DisciplineMaster dispMaster;

	public org.paradyne.bean.admin.master.DisciplineMaster getdispMaster() {
		return dispMaster;
	}

	public Object getModel() {
		return dispMaster;
	}

	/**
	 * @param cadMaster
	 *            the cadMaster to set
	 */
	public void setdispMaster(DisciplineMaster dispMaster) {
		this.dispMaster = dispMaster;
	}

	public DisciplineMaster getDispMaster() {
		return dispMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		dispMaster = new DisciplineMaster();
		dispMaster.setMenuCode(21);

	}

	/** called on load to set the list* */
	public void prepare_withLoginProfileDetails() throws Exception {

		DisciplineModel model = new DisciplineModel();

		model.initiate(context, session);

		model.dispData(dispMaster, request);
		model.terminate();
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String callPage() throws Exception {

		DisciplineModel model = new DisciplineModel();
		model.initiate(context, session);
		model.dispData(dispMaster, request);
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
		DisciplineModel model = new DisciplineModel();
		model.initiate(context, session);
		model.calforedit(dispMaster);

		model.dispData(dispMaster, request);
		model.terminate();
		return "success";
	}

	/*
	 * public String calfordelete() { DisciplineModel model= new
	 * DisciplineModel(); model.initiate(context,session); boolean result;
	 * result = model.calfordelete(dispMaster); if(result){
	 * addActionMessage(getText("Record deleted Successfully"));
	 * dispMaster.setDisciplineID(""); dispMaster.setDisciplineName("");
	 * dispMaster.setDisciplineParName(""); dispMaster.setDisciplineDesc("");
	 * dispMaster.setDisciplineParID(""); } else{ addActionMessage("One or more
	 * records can't be deleted \n as they are associated with some other
	 * record(s) ."); }
	 * 
	 * model.dispData(dispMaster,request); model.terminate();
	 * 
	 * return "success"; }
	 */

	/*
	 * public String delete1()throws Exception { String
	 * code[]=request.getParameterValues("hdeleteCode");
	 * 
	 * DisciplineModel model= new DisciplineModel();
	 * 
	 * model.initiate(context,session); boolean result
	 * =model.deleteDescipline(dispMaster,code); if(result) {
	 * addActionMessage("Record deleted successfully"); reset();
	 *  } else { addActionMessage("One or more records can't be deleted \n as
	 * they are associated with some other record(s) ."); }
	 * 
	 * 
	 * 
	 * model.dispData(dispMaster,request); model.terminate();
	 * 
	 * return "success"; }
	 */
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		DisciplineModel model = new DisciplineModel();

		model.initiate(context, session);
		String result = model.deletecheckedRecords(dispMaster, code);

		if (result.equals("true")) {
			addActionMessage(getText("delMessage", ""));
		}//end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}//end of else

		model.dispData(dispMaster, request);
		model.terminate();

		return reset();

	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {
		logger.info(" into delete");

		logger.info(" into delete<--------------------------------------->");
		DisciplineModel model = new DisciplineModel();
		logger.info("bean model");
		model.initiate(context, session);
		boolean result = model.deleteDiscipline(dispMaster);
		model.dispData(dispMaster, request);
		model.terminate();
		if (result) {
			dispMaster.setDisciplineID("");
			dispMaster.setDisciplineName("");
			dispMaster.setDisciplineParName("");
			dispMaster.setDisciplineDesc("");
			dispMaster.setDisciplineParID("");
		}// end of if
		if (result) {
			addActionMessage(getText("delMessage", ""));
		}// end of if
		else {
			addActionMessage("Record can't be deleted as \n it is associated with some other record");
		}// end of else
		return "success";

	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		dispMaster.setDisciplineID("");
		dispMaster.setDisciplineName("");
		dispMaster.setDisciplineParName("");
		dispMaster.setDisciplineDesc("");
		dispMaster.setDisciplineParID("");
		return "success";

	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {

		logger.info("1");
		DisciplineModel model = new DisciplineModel();
		logger.info("2");
		model.initiate(context, session);
		logger.info("3");
		/*
		 * boolean result = false ; boolean result1 = false ;
		 * 
		 * if(dispMaster.getDisciplineID().equals("")){ result =
		 * model.addDiscipline(dispMaster); if(result)
		 * addActionMessage(getText("addMessage","")); }else{ result1 =
		 * model.modDiscipline(addDiscipline); if(result1)
		 * addActionMessage(getText("modMessage","")); } logger.info("4");
		 * model.terminate(); if(!(result || result1 )) {
		 * 
		 * addActionMessage("Discipline can not be added"); }
		 */

		boolean result;

		if (dispMaster.getDisciplineID().equals("")) {
			logger.info("else addDept");
			result = model.addDiscipline(dispMaster);
			if (result) {
				addActionMessage(getText("addMessage", ""));
			}//end of if
			else {
				addActionMessage("Duplicate entry found.");
			}//end of else
		}//end of nested if 
		else {
			logger.info("else modCity");

			result = model.modDiscipline(dispMaster);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}//end of if
			else {
				addActionMessage("Duplicate entry found.");
			}//end of else
		}//end of else
		model.dispData(dispMaster, request);
		logger.info("Model Terminated");
		model.terminate();
		logger.info("5");
		return reset();
	}

	/*
	 * public String DisciplineRecord()throws Exception { logger.info("I am in
	 * model"); DisciplineModel model = new DisciplineModel();
	 * model.initiate(context,session); model.getDisciplineRecord(dispMaster);
	 * model.terminate(); return "success"; }
	 */

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT HRMS_DISCIPLINE .DISCP_ID,NVL(HRMS_DISCIPLINE .DISCP_NAME,' '),NVL(D1.DISCP_NAME,' ') ,NVL(HRMS_DISCIPLINE .DISCP_PARENT_CODE,0),NVL(HRMS_DISCIPLINE .DISCP_DESC,' ') "
				+ "  FROM HRMS_DISCIPLINE "
				+ "  LEFT JOIN HRMS_DISCIPLINE  D1 ON (D1.DISCP_ID = HRMS_DISCIPLINE .DISCP_PARENT_CODE)"
				+ "   ORDER BY HRMS_DISCIPLINE .DISCP_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Discipline Code", "Discipline Name",
				"Discipline ParentName" };

		String[] headerWidth = { "20", "40", "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "dispMaster.disciplineID",
				"dispMaster.disciplineName", "dispMaster.disciplineParName",
				"dispMaster.disciplineParID", "dispMaster.disciplineDesc" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Dispaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT DISCP_ID,DISCP_NAME  FROM  HRMS_DISCIPLINE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "DisciplineParent Code", "Discipline ParentName" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "dispMaster.disciplineParID",
				"dispMaster.disciplineParName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		DisciplineModel model = new DisciplineModel();
		model.initiate(context, session);
		/*
		 * model.getDisciplineReport(dispMaster); model.terminate();
		 * logger.info("into report"); return "report";
		 */
		model.getReport(dispMaster, request, response, context);
		model.terminate();
		return null;

	}

}
