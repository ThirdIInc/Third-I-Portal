package org.struts.action.recruitment;
import java.util.HashMap;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.Recruitment.TestInterviewReport;
import org.paradyne.model.recruitment.CandidateStatusModel;
import org.paradyne.model.recruitment.OpenVacReportModel;
import org.paradyne.model.recruitment.ScheduleTestIntReportModel;
public class ScheduleTestIntReportAction extends ParaActionSupport  {
	
/**
 * Author:Pradeep Kumar Sahoo
 * Date:19-05-2009
 */	
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeeReqAction.class);
	TestInterviewReport testInt;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		testInt = new TestInterviewReport();
		testInt.setMenuCode(830);

	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return testInt;
	}

public void prepare_withLoginProfileDetails() throws Exception {
		
	setting();
	
}



public String callBack(){
	ScheduleTestIntReportModel model = new ScheduleTestIntReportModel();
	model.initiate(context, session);
	//model.callBack(testInt);
	//model.callPreviousRecord(testInt); 
	model.terminate();
	return "success"; 
}
public void setting(){
	ScheduleTestIntReportModel model = new ScheduleTestIntReportModel();
	model.initiate(context,session);
	if(testInt.isGeneralFlag()){
	model.getEmployeeDetails(testInt.getUserEmpId(),testInt);
	}
String quer = "SELECT SCHDREP_CODE,SCHDREP_NAME FROM HRMS_REC_SCHDREP_FILTERS WHERE SCHDREP_USEREMPID="+testInt.getUserEmpId();
Object[][] iterator = model.getSqlModel().getSingleResult(quer);
HashMap mp = new HashMap();
for (int i = 0; i < iterator.length; i++) {
	mp.put(String.valueOf(iterator[i][0]),String.valueOf(iterator[i][1]));

}
testInt.setHashMap(mp);
model.terminate();
}
public String back(){		
	System.out.println("In back function");
	return "input";
}
	public String input(){
		testInt.setCandCode("");
		testInt.setCandName("");
		testInt.setFromDate("");
		testInt.setToDate("");
		testInt.setReqCode("");
		testInt.setReqName("");
		testInt.setRecId("");
		testInt.setRecName("");
		testInt.setType("");
		testInt.setTypeFlag("true");
		return "success";
	}


	public TestInterviewReport getTestInt() {
		return testInt;
	}

	public void setTestInt(TestInterviewReport testInt) {
		this.testInt = testInt;
	}
	/**
	 * following function is called to generate the report when Export to Pdf or Export to Text button is clicked.
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception{
		ScheduleTestIntReportModel model=new ScheduleTestIntReportModel();
		model.initiate(context, session);
		
		String type=request.getParameter("seltype");
		
		model.getReport(testInt,response,type);
		model.terminate();
		return null;
	}
	
	
	public String generateReport() throws Exception{
		ScheduleTestIntReportModel model=new ScheduleTestIntReportModel();
		model.initiate(context, session);
		
		String type=request.getParameter("seltype");
		
		String[] requisitionColumn = {getMessage("reqs.code"),getMessage("reqs.date"),getMessage("position"),getMessage("appstatus"),getMessage("division"),getMessage("reqstatus"),
				getMessage("branch"),getMessage("hiring.mgr"),getMessage("department")};
		String[] testColumn = {getMessage("serial.no"),getMessage("cand.name"),getMessage("email.id"),getMessage("contact.no"),getMessage("test.date"),getMessage("test.time"),
				getMessage("test.ven"),getMessage("rec.name"),getMessage("testStat")};
		
		String[] colnames = {getMessage("serial.no"),getMessage("cand.name"),getMessage("rndType"),getMessage("intDate"),getMessage("intTime"),getMessage("intven"),
				getMessage("intrvr"),getMessage("rec.name"),getMessage("conduct")};
		logger.info("seltype================="+type);
		model.getGenerateReport(testInt,response,type,colnames,testColumn,requisitionColumn);
		//model.getPdfReport(response,testInt);
		model.terminate();
		return null;
	}
	/**
	 * following function is called the interview or test of the candidates.  
	 * @return
	 * @throws Exception
	 */
	public String getInterviewData() throws Exception{
		ScheduleTestIntReportModel model=new ScheduleTestIntReportModel();
		model.initiate(context, session);
		model.jspViewInterview(testInt,request);
		callJspView();
		model.terminate();
		return "view";
	}
	/**
	 * following function is called to generate the requisition page wise and the 
	 * interview list/test list of the candidates for the corresponding requisition.     
	 * @return
	 * @throws Exception
	 */
	public String callJspView(){
		
		ScheduleTestIntReportModel model=new ScheduleTestIntReportModel();
		model.initiate(context, session);
		model.callJspView(testInt,request);
		model.terminate();
		
		return "view";
		
	}
	public String displayReq(){ 
		ScheduleTestIntReportModel model=new ScheduleTestIntReportModel();
		model.initiate(context, session);
		model.displayReq(testInt);
		model.terminate();
		return "viewReq";
	}
	
	public String summaryReport(){
		ScheduleTestIntReportModel model=new ScheduleTestIntReportModel();
		model.initiate(context, session);
		model.sumaryReport(testInt);
		model.terminate();
		return "viewsummaryReport";
		
	}
	public String getFilterDetails(){
		ScheduleTestIntReportModel model=new ScheduleTestIntReportModel();
		model.initiate(context, session);

		model.getFilterOptions(testInt);
		model.getReqsnDetails(testInt);
		model.getSortingDet(testInt);
		model.getSortingDetInt(testInt);
		
		model.getColumnDef(testInt, request);
		model.getIntColumnDef(testInt, request);
		model.getAdvanceDet(testInt);
		model.terminate();
		return "success";
	}
	public String saveSetting(){
		ScheduleTestIntReportModel model=new ScheduleTestIntReportModel();
		model.initiate(context, session);
		
		boolean result=false;
		
		if(testInt.getSchdReqCode().equals("-1")){
			result=model.saveRepSettings(testInt);
			
			if(result){
				addActionMessage("Record saved successfully.");
				getFilterDetails();
				setting();
				
			}
			else{
				addActionMessage("Duplicate  entry found");
				//getFilterDetails();
				//setting();
				
			}
		}else{
			result=model.updateSettings(testInt);
			if(result){
				addActionMessage("Record updated successfully.");
				getFilterDetails();
				setting();
			}
			else{
				addActionMessage("Duplicate  entry found");
				//getFilterDetails();
				//setting();
				
			}
		}
		
		
		return "success";
	}
	
	/**
	 * following function is called when the Search button for Candidate is clicked. 
	 * @return
	 * @throws Exception
	 */
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
	/**
	 * following function is called when the search button for Requisition is clicked.
	 * @return
	 * @throws Exception
	 */	
	public String f9Reqsn()throws Exception {
		String query="";
		/*if(testInt.getType().equals("I")){
			
			query+= " SELECT NVL(REQS_NAME,' '),TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),REQS_CODE "+
					" FROM HRMS_REC_REQS_HDR  WHERE REQS_CODE IN(SELECT DISTINCT(INT_REQS_CODE) FROM HRMS_REC_SCHINT WHERE 1=1 " ;
				if(testInt.isGeneralFlag()){ 
						query+= " AND INT_REC_EMPID="+testInt.getUserEmpId()+")";
				}else
				{
					query+= " )";
				}
				
				query+=  " ORDER BY REQS_DATE DESC";
			
		}else{
			query+= " SELECT NVL(REQS_NAME,' '),TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),REQS_CODE "+
					" FROM HRMS_REC_REQS_HDR  WHERE REQS_CODE IN(SELECT DISTINCT(TEST_REQS_CODE) FROM HRMS_REC_SCHTEST WHERE 1=1" ;
				
					if(testInt.isGeneralFlag()){ 
						query+= " AND TEST_REC_EMPID="+testInt.getUserEmpId()+")";
					}else
					{
					query+= " )";
					}
					
					query+=  " ORDER BY REQS_DATE DESC";
		}*/
		
		
		
		/*query+= " SELECT NVL(REQS_NAME,' '),TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),REQS_CODE "+
		" FROM HRMS_REC_REQS_HDR  WHERE REQS_APPROVAL_STATUS IN ('A','Q') order by REQS_DATE desc ";*/
		
		
		query+= "SELECT NVL(REQS_NAME,' '),TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),REQS_CODE"
			+" FROM HRMS_REC_REQS_HDR  "
			
			+" WHERE REQS_APPROVAL_STATUS IN ('A','Q') order by REQS_DATE desc ";
	
		
		
					
		String []headers ={getMessage("reqCode"),getMessage("schdDate")};
		String []headerwidth={"15","15"};
		String []fieldNames={"reqName","reqDate","reqCode"};
		int []columnIndex={0,1,2};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		return "f9page";
		
		/*query+= "SELECT NVL(REQS_NAME,' '),RANK_NAME ,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),REQS_CODE"
			+" FROM HRMS_REC_REQS_HDR  "
			+" INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION)" 
			+" WHERE REQS_APPROVAL_STATUS IN ('A','Q') order by REQS_DATE desc ";
	
		
		
					
		String []headers ={getMessage("reqCode"),getMessage("position"),getMessage("schdDate")};
		String []headerwidth={"15","15","15"};
		String []fieldNames={"reqName","rankName","reqDate","reqCode"};
		int []columnIndex={0,1,2,3};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		return "f9page";*/
	}
	
	

	/**
	 * following function is called when the search button for Recruiter is clicked. 
	 * @return
	 * @throws Exception
	 */
	public String f9Recruiter() throws Exception{
		String query = " SELECT	EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME as name,EMP_ID "
		+" FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' "
		+" ORDER BY UPPER(name)";
		
		
		/*String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC" 
		+" LEFT JOIN HRMS_EMP_OFFC RECTR ON(RECTR.EMP_ID=HRMS_REC_SCHTEST.TEST_REC_EMPID)"
		+" ORDER BY EMP_ID";*/
		
	
		String []headers ={getMessage("recrutName")};
		//String []headers ={getMessage("recName")};
		String []headerwidth={"25"};
		String []fieldNames={"recName","recId"};
		int []columnIndex={0,1};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		return "f9page";
		
	}
	/*public String f9RecruiterName() throws Exception{
		String query = " SELECT	EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID "
		+" FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' "
		+" ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";
	
		String []headers ={getMessage("recrutName")};
		String []headerwidth={"25"};
		String []fieldNames={"recruiterName","recrutCode"};
		int []columnIndex={0,1};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		return "f9page";
		
	}*/
	
public String f9Interviewer() throws Exception {	
		
		
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC" 
		+" INNER JOIN HRMS_REC_SCHINT_DTL ON (HRMS_REC_SCHINT_DTL.INT_VIEWER_EMPID=HRMS_EMP_OFFC. EMP_ID)"
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


	public String f9Position() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT RANK_NAME,RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("position")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "rankName","rankId" };

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
	
}
