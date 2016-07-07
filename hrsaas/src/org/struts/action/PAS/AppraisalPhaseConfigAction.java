package org.struts.action.PAS;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.PAS.AppraisalPhaseConfig;
import org.paradyne.model.PAS.AppraisalPhaseConfigModel;
import org.paradyne.model.admin.master.SkillMasterModel;

public class AppraisalPhaseConfigAction extends ParaActionSupport{
	
	AppraisalPhaseConfig apc;
	
	static org.apache.log4j.Logger logger = 
		org.apache.log4j.Logger.getLogger
		(org.struts.lib.ParaActionSupport.class);
	
	
	
	public AppraisalPhaseConfig getApc() {
		return apc;
	}

	public void setApc(AppraisalPhaseConfig apc) {
		System.out.println("HEMANT");
		this.apc = apc;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apc;
	}
	
	public void prepare_local() throws Exception {
		System.out.println("Inside preparelocal...");
		// TODO Auto-generated method stub
		apc = new AppraisalPhaseConfig();
		apc.setMenuCode(748);
		apc.setPStatus("A");
		reset();
		apc.setEditMode("true");
	}
	
	public void reset(){
		System.out.println("Inside reset...");
		
		/*if(){
			
		}*/
		
		apc.setViewMode("false");
		apc.setEditMode("false");
		apc.setMode("");
		//apc.setApprCode("");
		//apc.setApprId("");
		//apc.setFrmDate("");
		//apc.setToDate("");
	}
	
	@Override
	public String input() throws Exception {
		System.out.println("Inside input...");
		// TODO Auto-generated method stub
		getNavigationPanel(1);
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);
		model.getAppraisalCalendar(apc,request);
		model.terminate();
		
		reset();		
		apc.setMode("readonly");		
		return "input";
	}
	
	public String callPage() throws Exception {
			getNavigationPanel(1);
			
			AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
			model.initiate(context, session);
			model.getAppraisalCalendar(apc,request);
			model.terminate();
		
		 return SUCCESS;
		
		}
	
	
	public String deleteProcessConfigurations(){
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);
		boolean result = model.deleteProcessConfigurations(apc,request);
		model.getAppraisalCalendar(apc,request);
		model.terminate();
		
		if(result){
			//addActionMessage("Record deleted successfully.");
			addActionMessage(getMessage("delete"));
		}else{
			//addActionMessage("Cannot delete the record.");
			addActionMessage(getMessage("multiple.del.error"));
		}
		
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String addPhase()throws Exception{
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);
		model.addPhase(apc,request);
		model.terminate();
		
		
		
		getNavigationPanel(2);
		apc.setRemoveFlag("true");
		//apc.setMode("update");
		
		apc.setPhase("");
		apc.setPRating("");
		apc.setPWeightage("");
		apc.setPStatus("");
		apc.setPDescr("");
		apc.setPRatingCheck("");		
		apc.setNewFlag("true");
		apc.setEditMode("true");
		
		return SUCCESS;
		
	}
	
	/**
	 * @return
	 */
	public String addNew()throws Exception{
		getNavigationPanel(2);
		apc.setEditMode("true");	
		apc.setMode("");
		return SUCCESS;
	}
	
	public String save()throws Exception{
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		boolean insert = false;
		boolean update = false;
		
	/*	String phaseId[]=request.getParameterValues("phaseId");
		
		
		
		if(phaseId!=null && phaseId.length>0){
			
			if(){
				
			}
			
		}*/
		
		model.initiate(context, session);
		
		if(!apc.getMode().equals("update")){
			insert = model.save(apc,request);
		}else{
			update = model.update(apc,request);
		}
		
		
		model.getPhaseConfigDetails(apc,request);//get phase details in view mode
		model.terminate();
		
		//reset();
		apc.setMode("");
		apc.setViewMode("true");
		apc.setEditMode("false");
		
		if(insert){
			//addActionMessage("Record saved successfully.");		
			addActionMessage(getMessage("save"));
		}else if(update){
			addActionMessage(getMessage("update"));
			//addActionMessage("Record updated successfully.");				
		}else{
			addActionMessage(getMessage("save.error"));
			//addActionMessage("Cannot save record.");		
		}
		
		getNavigationPanel(3); 
				
		
		return SUCCESS;
	}
	
	/**
	 * @return
	 */
	public String edit()throws Exception{
		System.out.println("Inside edit()....");
		String requestID = request.getParameter("appraisalIdList");
		if(requestID!=null && requestID.equalsIgnoreCase("")){
		apc.setApprId(requestID);
		}
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);
		model.getPhaseConfigDetails(apc,request);//get phase details in view mode
		model.terminate();	
		
		reset();
		apc.setNewFlag("true");
		apc.setEditMode("true");
		apc.setMode("update");
		apc.setRemovePhases("");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String remove() throws Exception{
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);	
		boolean result = false;
		
		if(apc.getMode().equals("update")){//UPDATION MODE
			
			 result = model.removePhase(apc,request);	
			 //model.getPhaseConfigDetails(apc,request);//get phase details in view mode
		}else{//ADD NEW MODE
			
			 result = model.removeStaticPhase(apc, request);
			 //model.getPhaseConfigDetails(apc,request);//get phase details in view mode
		}
		
		if(result){			
			//addActionMessage("Phase removed successfully.");			
			addActionMessage(getMessage("delete"));
		}else{
			
			//addActionMessage("Cannot remove the phase as references exists.\nMake the phase De-Active to make it unusable");
			addActionMessage(getMessage("del.error"));
		}
		//model.getPhaseConfigDetails(apc,request);//get phase details in view mode
		model.terminate();
		getNavigationPanel(2);
		apc.setNewFlag("true");
		apc.setEditMode("true");
		return SUCCESS;
	}
	
	
	public String delete()throws Exception{
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);
		boolean result = model.delete(apc);//get phase details in view mode
		model.getAppraisalCalendar(apc,request);
		model.terminate();	
		
		if(result){
			//addActionMessage("Record deleted successfully.");
			addActionMessage(getMessage("delete"));
		}else{
			//addActionMessage("Cannot delete the record, due to \ndependencies.");
			addActionMessage(getMessage("del.error"));
			
		}
		reset();
		apc.setEditMode("false");		
		apc.setMode("readonly");
		getNavigationPanel(1);
		
		return "calendarList";
	}
	
	public String cancel()throws Exception{
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);
		model.getAppraisalCalendar(apc,request);
		model.terminate();
		
		getNavigationPanel(1);
		reset();
		apc.setEditMode("false");
		apc.setRemoveFlag("false");
		
		if(apc.getMode().equals("update")){
			
		}
		apc.setMode("readonly");
		
		apc.setApprCode("");
		apc.setFrmDate("");
		apc.setToDate("");
		apc.setPhase("");
		apc.setPOrder("");
		apc.setPRating("");
		apc.setPRatingCheck("");
		apc.setPWeightage("");
		apc.setPDescr("");
		
		//apc.setMode("readonly");
		
		return SUCCESS;
	}
	
	public String getPhaseConfigDetails()throws Exception{
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);
		model.getPhaseConfigDetails(apc,request);//get phase details in view mode
		model.terminate();
		
		reset();
		apc.setViewMode("true");
		getNavigationPanel(3); 
		
		return "success";
		
	}
	
	public String setNewFlag()throws Exception{
		
		
		//System.out.println("Inside input...");
		// TODO Auto-generated method stub
		getNavigationPanel(2);
		
		AppraisalPhaseConfigModel model = new AppraisalPhaseConfigModel();
		model.initiate(context, session);
		model.getAppraisalCalendar(apc,request);
		model.terminate();
		
		reset();		
		apc.setNewFlag("true");
		apc.setEditMode("true");			
		return "success";
	}
	
	/*
	 * This method serves a pop up for the search button
	 */
	public String f9Action() throws Exception{
		
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DISTINCT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'), TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),PAS_APPR_CALENDAR.APPR_ID FROM PAS_APPR_CALENDAR"
					  +" INNER JOIN PAS_APPR_PHASE_CONFIG ON(PAS_APPR_PHASE_CONFIG.APPR_ID = PAS_APPR_CALENDAR.APPR_ID)"
					  +" ORDER BY PAS_APPR_CALENDAR.APPR_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */ 
		//String[] headers = { "Appraisal Code","Start Date","End Date"};
		 String[] headers = { getMessage("f9.appr.code"),getMessage("f9.appr.start.date"),getMessage("f9.appr.end.date")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "50","25","25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "apprCode","frmDate","toDate","apprId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0,1,2,3 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AppraisalPhaseConfig_getPhaseConfigDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		logger.info("4");		
		
		return "f9page";
		
	}
	
	
	public String f9AppCal()throws Exception{
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR "
			+" WHERE APPR_ID NOT IN (SELECT DISTINCT APPR_ID FROM PAS_APPR_PHASE_CONFIG)ORDER BY APPR_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Appraisal Code","Start Date","End Date"};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "50","25","25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "apprCode","frmDate","toDate","apprId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0,1,2,3 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AppraisalPhaseConfig_setNewFlag.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		logger.info("4");
		return "f9page";
	}
	
}
