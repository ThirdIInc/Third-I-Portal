package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.ProbationSetting;
import org.paradyne.model.admin.srd.ProbationSettingModel;
import org.struts.lib.ParaActionSupport;

public class ProbationSettingAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ProbationSettingAction.class);
	
	ProbationSetting probationSetting;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		probationSetting = new ProbationSetting();
		probationSetting.setMenuCode(931);
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return probationSetting;
	}

	public ProbationSetting getProbationSetting() {
		return probationSetting;
	}

	public void setProbationSetting(ProbationSetting probationSetting) {
		this.probationSetting = probationSetting;
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		
		try {
			ProbationSettingModel model = new ProbationSettingModel();
			model.initiate(context, session);
			model.setRecord(probationSetting);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in prepare_withLoginProfileDetails--------"+e);
		}
	}
	
	public String save()
	{
		boolean flag=false;
		try {
			ProbationSettingModel model = new ProbationSettingModel();
			model.initiate(context, session);
			String gradeIds[]=request.getParameterValues("gradeId");
			String gradeDays[]=request.getParameterValues("probationGradeDays"); 
			String query=" SELECT * FROM HRMS_PROBATION_HDR ";
			Object result[][] =model.getSqlModel().getSingleResult(query);
			if(result != null && result.length != 0){
				flag=model.updateProbation(probationSetting,gradeIds,gradeDays);
				if(flag)
				{
					addActionMessage("Record updated successfully");
					model.setSavedRecord(probationSetting);
				}
				else
				{
					addActionMessage("Record not updated");
				}
			}	//if statement ends
			else{
				flag=model.saveProbation(probationSetting,gradeIds,gradeDays);
				if(flag)
				{
					addActionMessage("Record saved successfully");
					model.setSavedRecord(probationSetting);
				}
				else
				{
					addActionMessage("Record not saved");
				}
			}	//else statement ends
		 
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in save--------"+e);
		}
		return SUCCESS;
	}
	
	public String reset()
	{
		try {
			probationSetting.setEmpType("");
			probationSetting.setEmpTypeCode("");
			probationSetting.setProbationGradeDays("");
			probationSetting.setProbationDays("");
		} catch (Exception e) {
			logger.error("Exception in reset--------"+e);
		}
		return SUCCESS;
	}
	
	public String f9empTypeAction() throws Exception {

		// BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		// OUTPUT ALONG WITH PROFILES
		//
		String query = " SELECT    TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE ";

		// SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *

		String[] headers = {"TypeId","TypeName"};
 

		// DEFINE THE PERCENT WIDTH OF EACH COLUMN

		String[] headerWidth = { "20", "80" };

		// -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		// ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		// -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		// INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		// FIELDNAMES

		String[] fieldNames = {"empTypeCode","empType" };

		// SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		// CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		// IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}

		// NOTE: COLUMN NUMBERS STARTS WITH 0

		int[] columnIndex = { 0, 1 };

		// WHEN SET TO 'true' WILL SUBMIT THE FORM

		String submitFlag = "false";

		// IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		// FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		// ACTION>_<METHOD TO CALL>.action

		String submitToMethod = "";

		// CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
	 
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}// end of f9type

}
