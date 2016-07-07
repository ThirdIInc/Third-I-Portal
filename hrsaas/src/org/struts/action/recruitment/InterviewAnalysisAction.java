package org.struts.action.recruitment;

import java.util.HashMap;

import org.paradyne.bean.Recruitment.InterviewAnalysis;
import org.paradyne.bean.Recruitment.TestInterviewReport;
import org.paradyne.model.recruitment.InterviewAnalysisModel;
import org.paradyne.model.recruitment.ScheduleTestIntReportModel;
import org.struts.lib.ParaActionSupport;

public class InterviewAnalysisAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InterviewAnalysisAction.class);
	
	InterviewAnalysis intAnalysis;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		intAnalysis = new InterviewAnalysis();
		intAnalysis.setMenuCode(924);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return intAnalysis;
	}

	public InterviewAnalysis getIntAnalysis() {
		return intAnalysis;
	}

	public void setIntAnalysis(InterviewAnalysis intAnalysis) {
		this.intAnalysis = intAnalysis;
	}


	public void prepare_withLoginProfileDetails() throws Exception {
			
		setRecord();
		
	}
	
	public void setRecord()
	{
		InterviewAnalysisModel model=new InterviewAnalysisModel();
		model.initiate(context, session);
		String quer = "SELECT INTANAREP_CODE,INTANAREP_NAME FROM HRMS_REC_INTANAREP_FILTERS WHERE INTANAREP_USEREMPID="+intAnalysis.getUserEmpId();
		Object[][] iterator = model.getSqlModel().getSingleResult(quer);
		HashMap mp = new HashMap();
		for (int i = 0; i < iterator.length; i++) {
			mp.put(String.valueOf(iterator[i][0]),String.valueOf(iterator[i][1]));

		}
		intAnalysis.setHashMap(mp);
		model.terminate();
	}
	
	
	
	
	public String getFilterDetails(){
		InterviewAnalysisModel model=new InterviewAnalysisModel();
		model.initiate(context, session);
		
		model.getFilterOptions(intAnalysis);
		
		model.getSortingDet(intAnalysis);
		model.getColumnDef(intAnalysis, request);
		
		model.terminate();
		return "success";
	}
	public String generateReport() throws Exception{
		InterviewAnalysisModel model=new InterviewAnalysisModel();
		model.initiate(context, session);
		
		String [] colnames = {getMessage("serial.no"),getMessage("cand.name"),getMessage("rec.name"),getMessage("intvRound"),getMessage("qualification"),getMessage("technical"),getMessage("communication"),getMessage("managment"),getMessage("prsonality"),getMessage("learning"),getMessage("relevant"),getMessage("suitablity"),getMessage("makeOff"),getMessage("forwardNext"),getMessage("score.Parameter"),getMessage("percentage"),getMessage("status")
				};
		model.getGenerateReport(intAnalysis,response,request,colnames);
		//model.getPdfReport(response,testInt);
		model.terminate();
		return null;
	}
	
	public String getInterviewData() throws Exception{
		InterviewAnalysisModel model=new InterviewAnalysisModel();
		model.initiate(context, session);
		model.callJspView(intAnalysis,request);
		callJspView();
		model.terminate();
		return "view";
	}
	
	public String saveSetting(){
		InterviewAnalysisModel model=new InterviewAnalysisModel();
		model.initiate(context, session);
		
		boolean result=false;

		if(intAnalysis.getSettingCode().equals("-1")){
			result=model.saveRepSettings(intAnalysis);
			
			if(result){
				addActionMessage("Record saved successfully.");
				getFilterDetails();
				setRecord();
				
			}
		}else{
			result=model.updateSettings(intAnalysis);
			if(result){
				addActionMessage("Record updated successfully.");
				getFilterDetails();
				setRecord();
			}
		}
		
		
		return "success";
	}
	
	
	
	
	
	
public String callJspView(){
		
		InterviewAnalysisModel model=new InterviewAnalysisModel();
		model.initiate(context, session);
		model.callJspView(intAnalysis,request);
		model.terminate();
		
		return "view";
		
	}
public String f9Candidate()throws Exception {
	
	String query = "SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY CAND_CODE "; 

	String[] headers = {getMessage("cand.name")};
	String[] headerWidth = { "40"};
	String[] fieldNames = {"candName","candCode"};
	int[] columnIndex = { 0,1};
	String submitFlage ="false";
	String submitToMethod = "";
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlage,submitToMethod);
	return "f9page";
}



public String f9Interviewer() throws Exception {	
		
		
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC" 
		+"  INNER JOIN HRMS_REC_CANDEVAL ON (HRMS_REC_CANDEVAL.EVAL_INT_EMPID=HRMS_EMP_OFFC. EMP_ID)"
		+" ORDER BY EMP_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("intrvName")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = {"20"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"interviewerName","intrvCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = {0, 1};

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



}
