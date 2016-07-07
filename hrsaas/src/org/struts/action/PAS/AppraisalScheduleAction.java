package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalSchedule;

import org.paradyne.model.PAS.AppraisalScheduleModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalScheduleAction extends ParaActionSupport {

	AppraisalSchedule appraisalSchedule;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		appraisalSchedule = new AppraisalSchedule();
		appraisalSchedule.setMenuCode(748);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return appraisalSchedule;
	}

	public AppraisalSchedule getAppraisalSchedule() {
		return appraisalSchedule;
	}

	public void setAppraisalSchedule(AppraisalSchedule appraisalSchedule) {
		this.appraisalSchedule = appraisalSchedule;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		model.getSavedSchedule(appraisalSchedule, request);
		model.terminate();
	}

	/* method name : callPage
	 * purpose     : to displays the records in the form
	 * return type : String
	 * parameter   : none
	 */

	public String callPage() throws Exception {
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		getNavigationPanel(1);
		model.initiate(context, session);
		model.getSavedSchedule(appraisalSchedule, request);
		model.terminate();
		return INPUT;

	}

	/* method name : calforedit
	 * purpose     : to edit the records 
	 * return type : String
	 * parameter   : none
	 * throws      : Exception
	 */

	public String calforedit() throws Exception {
		String requestID = request.getParameter("appraisalIdList");
		if(requestID!=null && !requestID.equals("")){
		appraisalSchedule.setApprCode(requestID);
		appraisalSchedule.setHiddencode(requestID);
		appraisalSchedule.setCalUpdateFlag("true");
		}
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		System.out.println("action calforedit");
		model.calforedit(appraisalSchedule);
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String addNew() throws Exception {
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		appraisalSchedule.setApprId("");
		appraisalSchedule.setApprCode("");
		appraisalSchedule.setStartDate("");
		appraisalSchedule.setEndDate("");
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}

	/* method name : calfordelete
	 * purpose     : to delete the selected Record 
	 * return type : String
	 * parameter   : none
	 */
	public String calfordelete() {
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		boolean result = false;
		//result = model.calfordelete(unitMaster);
		if (result) {
			addActionMessage(getText("Record  Deleted Successfully."));
		}//end of if
		else {
			addActionMessage("Record can not be deleted.");
		}//end of else
		//model.Data(unitMaster, request);
		model.terminate();
		return "success";
	}

	/* method name : delete1
	 * purpose     : to delete the record selected by check on the Check Box
	 * return type : String
	 * parameter   : none
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		AppraisalScheduleModel model = new AppraisalScheduleModel();
		boolean result = false;
		model.initiate(context, session);
		result = model.deletecheckedRecords(appraisalSchedule, code);
		model.getSavedSchedule(appraisalSchedule, request);
		if (result) {
			addActionMessage(getMessage("delete"));
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}

		
		model.terminate();
		getNavigationPanel(1);
		return INPUT;

	}

	public String save() {
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		boolean addResult = false;
		boolean updateResult = false;
		String flag = appraisalSchedule.getAddFlag();

		String apprCode = appraisalSchedule.getApprId();
		Object phaseCode[] = request.getParameterValues("phaseCode");
		Object phaseStartDate[] = request.getParameterValues("phaseStartDate");
		Object phaseEndDate[] = request.getParameterValues("phaseEndDate");
		Object phaseLock[] = request.getParameterValues("phaseLockFlag");

		if (flag.equals("true")) {
			addResult = model.addNewSchedule(apprCode, phaseCode,
					phaseStartDate, phaseEndDate, phaseLock);
			if (addResult) {
				addActionMessage(getMessage("save"));
			} else
				addActionMessage(getMessage("save.error"));
		} else {
			updateResult = model.updateSchedule(apprCode, phaseCode,
					phaseStartDate, phaseEndDate, phaseLock);
			if (updateResult) {
				addActionMessage(getMessage("update"));
			} else
				addActionMessage(getMessage("update.error"));

		}

		model.getSchedule(appraisalSchedule);

		model.terminate();
		getNavigationPanel(3);
		return "input";
	}

	public String search() {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  DISTINCT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), "
				+ " PAS_APPR_PHASE_SCHEDULE.APPR_ID,APPR_CAL_IS_STARTED FROM PAS_APPR_PHASE_SCHEDULE "
				+ " LEFT JOIN PAS_APPR_CALENDAR  ON (PAS_APPR_PHASE_SCHEDULE.APPR_ID = PAS_APPR_CALENDAR.APPR_ID) "
				+ " ORDER BY PAS_APPR_PHASE_SCHEDULE.APPR_ID ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("appraisal.code"), getMessage("appr.start.date"), getMessage("appr.end.date") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "50", "25", "25" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "apprCode", "startDate", "endDate", "apprId", "lockAppr" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3,4 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AppraisalSchedule_retrieveSchedule.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String edit() {
		getNavigationPanel(2);

		Object phaseCode[] = request.getParameterValues("phaseCode");
		Object phaseName[] = request.getParameterValues("phaseName");
		Object phaseStartDate[] = request.getParameterValues("phaseStartDate");
		Object phaseEndDate[] = request.getParameterValues("phaseEndDate");
		Object phaseLock[] = request.getParameterValues("phaseLockFlag");
		
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		model.getEditData(appraisalSchedule, phaseCode, phaseName,
				phaseStartDate, phaseEndDate, phaseLock);

		model.terminate();
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		getNavigationPanel(1);
		appraisalSchedule.setApprId("");
		appraisalSchedule.setApprCode("");
		appraisalSchedule.setStartDate("");
		appraisalSchedule.setEndDate("");
		appraisalSchedule.setPhaseCode("");
		appraisalSchedule.setPhaseName("");
		appraisalSchedule.setPhaseEndDate("");
		appraisalSchedule.setPhaseStartDate("");
		appraisalSchedule.setPhaseLockFlag("");
		appraisalSchedule.setAddFlag("true");
		appraisalSchedule.setReadFlag("true");
		return INPUT;
	}

	public String cancelView() throws Exception {
		getNavigationPanel(1);
		appraisalSchedule.setReadFlag("true");
		return INPUT;
	}

	public String retrievePhases() {
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		model.getPhases(appraisalSchedule);
		getNavigationPanel(2);
		model.terminate();
		return SUCCESS;
	}

	public String retrieveSchedule() {
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		model.getSchedule(appraisalSchedule);
		getNavigationPanel(3);
		model.terminate();
		return "input";
	}

	public String reset() {

		appraisalSchedule.setApprId("");
		appraisalSchedule.setApprCode("");
		appraisalSchedule.setStartDate("");
		appraisalSchedule.setEndDate("");
		appraisalSchedule.setPhaseCode("");
		appraisalSchedule.setPhaseName("");
		appraisalSchedule.setPhaseEndDate("");
		appraisalSchedule.setPhaseStartDate("");
		appraisalSchedule.setPhaseLockFlag("");
		appraisalSchedule.setAddFlag("true");
		appraisalSchedule.setReadFlag("true");
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String delete() {
		AppraisalScheduleModel model = new AppraisalScheduleModel();
		model.initiate(context, session);
		boolean result = false;
		Object[][] apprCode = new Object[1][1];
		apprCode[0][0] = String.valueOf(appraisalSchedule.getApprId());
				
		result = model.deleteSchedule(apprCode);
		if (result)
			addActionMessage(getMessage("delete"));
		else
			addActionMessage(getMessage("del.error"));

		reset();
		getNavigationPanel(2);
		 return "calendarList";
	}

	public String f9SelectAppraisal() throws Exception {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID,APPR_CAL_IS_STARTED "
				+ " FROM PAS_APPR_CALENDAR  WHERE APPR_ID NOT IN (SELECT APPR_ID FROM PAS_APPR_PHASE_SCHEDULE )ORDER BY APPR_ID ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { getMessage("appraisal.code"), getMessage("appr.from.date"), getMessage("appr.to.date") };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "50", "25", "25" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "apprCode", "startDate", "endDate", "apprId","lockAppr" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AppraisalSchedule_retrievePhases.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
