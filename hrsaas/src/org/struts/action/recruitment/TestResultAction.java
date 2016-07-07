/**
 * 
 */
package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.TestResult;
import org.paradyne.model.recruitment.TestResultModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0540
 *
 */
public class TestResultAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TestResultAction.class);
	
	TestResult testResult = null;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		testResult = new TestResult();
		testResult.setMenuCode(801);
		// TODO Auto-generated method stub

	}
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return testResult;
	}
	
	/**@method showTestResult 
	 * @purpose to display the test result of the candidates
	 * @return String
	 */
	public String showTestResult(){
		logger.info("in showTestResult");
		
		TestResultModel model = new TestResultModel();
		model.initiate(context, session);
		
		model.showTestResult(testResult);
		model.terminate();
		testResult.setShowFlag("true");
		return "success";
	}
	/**
	 * 
	 * @return
	 */
			/*
	public String backFromCancel(){
		TestResultModel model = new TestResultModel();
		model.initiate(context, session);
		
		model.showTestResult(testResult);
		model.terminate();
		testResult.setShowFlag("true");
		return "success";
	}*/
	
	/**@method save 
	 * @purpose to save the result of the written test of the candidates
	 * @return String
	 */
	public String save(){
		logger.info("in save");
		
		TestResultModel model = new TestResultModel();
		model.initiate(context, session);
		
		boolean result = model.save(testResult, request);
		model.terminate();
		
		if(result){
			addActionMessage(getMessage("save"));
		}
		else{
			addActionMessage(getMessage("save.error"));
		}
		showTestResult();
		return "success";
	}
	
	/**@method reset 
	 * @purpose to clear all the form fields
	 * @return String
	 */
	public String reset(){
		logger.info("in reset ");
		
		testResult.setFromDate("");
		testResult.setHiringManager("");
		testResult.setHiringManagerCode("");
		testResult.setMarksObtained("");
		testResult.setOnLineCandCode("");
		testResult.setOnLineCandName("");
		testResult.setOnLineContactNo("");
		testResult.setOnLineEmailId("");
		testResult.setOnLineFlag("");
		testResult.setOnLineObtMarks("");
		testResult.setOnLineReqCode("");
		testResult.setOnLineReqName("");
		testResult.setOnLineResult("");
		testResult.setOnLineTestDate("");
		testResult.setOnLineTestTime("");
		testResult.setOnLineTotalMarks("");
		testResult.setPosition("");
		testResult.setRequisitionCode("");
		testResult.setRequisitionName("");
		testResult.setResultCode("");
		testResult.setResultType("");
		testResult.setSearchCriteria("");
		testResult.setTestType("");
		testResult.setToDate("");
		testResult.setWrittenCandCode("");
		testResult.setWrittenCandName("");
		testResult.setWrittenContactNo("");
		testResult.setWrittenEmailId("");
		testResult.setWrittenFlag("");
		testResult.setWrittenHiddenResult("");
		testResult.setWrittenObtMarks("");
		testResult.setWrittenReqCode("");
		testResult.setWrittenReqName("");
		testResult.setWrittenResult("");
		testResult.setWrittenResultCode("");
		testResult.setWrittenTestDate("");
		testResult.setWrittenTestTime("");
		testResult.setWrittenTotalMarks("");		
		return "success";
	}
	
	/**
	 * to navigate to the schedule test form..
	 * @return
	 */
	public String toScheduleTest(){
		logger.info("in toScheduleTestRound ");
		
		String candCode = "";
		String onlinecandCode = "";
		Object [] formValues = {"testResult", testResult.getFromDate(), testResult.getToDate(), testResult.getHiringManagerCode(), testResult.getHiringManager(), 
				testResult.getTestType(), testResult.getSearchCriteria(), testResult.getMarksObtained(), testResult.getResultType(), "true","false","","false"};
		Object [] onlineformValues = {"testResult", "", "", testResult.getHiringManagerCode(), testResult.getHiringManager(), 
				"", "", "", "", "true"};
		Object [][] writtenListValues = null;
		Object [][] onLineListValues = null;
		
		testResult.setRequisitionCode(testResult.getRequisitionCode());
		testResult.setHidTestResultFlag("true");
		writtenListValues = setValuesForTransfer(request,"written");
		onLineListValues = setOnlineValuesForTransfer(request,"online");
		//request.setAttribute("formListValues", writtenListValues);
		
		
		//String [] onLineCandCode  = request.getParameterValues("onLineCandCode");//cand code of online test list
		//String [] onLineCheckBox  = request.getParameterValues("onLine"); //check box of online test list
		if(writtenListValues!=null){
			for (int i = 0; i < writtenListValues.length; i++) {
				if(writtenListValues[i][10]!=null){
				candCode += writtenListValues[i][10]+",";
				}
			}
		}
		if(onLineListValues!=null){
			for (int i = 0; i < onLineListValues.length; i++) {
				if(onLineListValues[i][10]!=null){
					onlinecandCode += onLineListValues[i][10]+",";
				}
			}
		}
		
		String totalcandCode = candCode + onlinecandCode;
		candCode = totalcandCode.substring(0, totalcandCode.length()-1);
		//onlinecandCode = onlinecandCode.substring(0, onlinecandCode.length()-1);
		
		request.setAttribute("candCode", candCode);//set cand code as request attribute
		request.setAttribute("formValues", formValues);//set formValues code as request attribute
		request.setAttribute("formListValues", writtenListValues);//set formListValues code as request attribute
		
	//	request.setAttribute("onlinecandCode", onlinecandCode);//set cand code as request attribute
		request.setAttribute("onlineformValues", onlineformValues);//set formValues code as request attribute
		request.setAttribute("onlineformListValues", onLineListValues);//set formListValues code as request attribute
		
     //  candCode = candCode.substring(0, candCode.length());
		
		//request.setAttribute("candCode", candCode);//set cand code as request attribute
		//request.setAttribute("formValues", formValues);//set formValues code as request attribute
		//request.setAttribute("formListValues", writtenListValues);//set formListValues code as request attribute
		
		logger.info("candCode  "+candCode);
		
		 return "schTestRound";
	} //end of toScheduleTest method
	
	/**@method toScheduleInterview 
	 * @purpose to navigate to the schedule interview form
	 * @return String
	 */
	public String toScheduleInterview(){
		logger.info("in toScheduleInterview ");
		
		String candCode = "";
		String onlinecandCode = "";
		Object [] formValues = {"testResult", testResult.getFromDate(), testResult.getToDate(), testResult.getHiringManagerCode(), testResult.getHiringManager(), 
				testResult.getTestType(), testResult.getSearchCriteria(), testResult.getMarksObtained(), testResult.getResultType(), "true","false"};
		Object [] onlineformValues = {"testResult", "", "", testResult.getHiringManagerCode(), testResult.getHiringManager(), 
				"", "", "", "", "true"};
		Object [][] writtenListValues = null;
		Object [][] onLineListValues = null;
		
		testResult.setRequisitionCode(testResult.getRequisitionCode());
		testResult.setHidTestResultFlag("true");
		writtenListValues = setValuesForTransfer(request,"written");
		onLineListValues = setOnlineValuesForTransfer(request,"online");
		//request.setAttribute("formListValues", writtenListValues);
		
		
		//String [] onLineCandCode  = request.getParameterValues("onLineCandCode");//cand code of online test list
		//String [] onLineCheckBox  = request.getParameterValues("onLine"); //check box of online test list
		if(writtenListValues!=null){
			for (int i = 0; i < writtenListValues.length; i++) {
				if(writtenListValues[i][10]!=null){
				candCode += writtenListValues[i][10]+",";
				}
			}
		}
		if(onLineListValues!=null){
			for (int i = 0; i < onLineListValues.length; i++) {
				if(onLineListValues[i][10]!=null){
					onlinecandCode += onLineListValues[i][10]+",";
				}
			}
		}
		
		String totalcandCode = candCode + onlinecandCode;
		candCode = totalcandCode.substring(0, totalcandCode.length()-1);
		//onlinecandCode = onlinecandCode.substring(0, onlinecandCode.length()-1);
		
		request.setAttribute("candCode", candCode);//set cand code as request attribute
		request.setAttribute("formValues", formValues);//set formValues code as request attribute
		request.setAttribute("formListValues", writtenListValues);//set formListValues code as request attribute
		
	//	request.setAttribute("onlinecandCode", onlinecandCode);//set cand code as request attribute
		request.setAttribute("onlineformValues", onlineformValues);//set formValues code as request attribute
		request.setAttribute("onlineformListValues", onLineListValues);//set formListValues code as request attribute
		
     //  candCode = candCode.substring(0, candCode.length());
		
		//request.setAttribute("candCode", candCode);//set cand code as request attribute
		//request.setAttribute("formValues", formValues);//set formValues code as request attribute
		//request.setAttribute("formListValues", writtenListValues);//set formListValues code as request attribute
		
		logger.info("candCode  "+candCode);
		
		return "schInt";
	}
		
	
	private Object[][] setOnlineValuesForTransfer(HttpServletRequest request,String type) {
		Object [][] onLineListValues = null;
		if(type.equals("online")){
			
			String [] onlineCandCode = request.getParameterValues("onLineCandCode");	//cand code of written test list
			String [] onlineCheckBox = request.getParameterValues("onLine");	//check box of written test list
			
			String [] onlineReqName = request.getParameterValues("onLineReqName");
			String [] onlineReqCode = request.getParameterValues("onLineReqCode");
			
			String [] onlineCandName = request.getParameterValues("onLineReqCode");
			//String [] onlineCandCode = request.getParameterValues("writtenCandCode");
			String [] onlineEmailId = request.getParameterValues("onLineEmailId");
			String [] onlineContactNo = request.getParameterValues("onLineContactNo");
			String [] onlineTestDate = request.getParameterValues("onLineTestDate");
			String [] onlineTestTime = request.getParameterValues("onLineTestTime");
			String [] onlineObtMarks = request.getParameterValues("onLineObtMarks");
			String [] onlineTotalMarks = request.getParameterValues("onLineTotalMarks");
			String [] onlineResult = request.getParameterValues("onLineResult");
			int c = 0;
			if(onlineCandCode!=null){
				for (int i = 0; i < onlineCandCode.length; i++) {
						if(String.valueOf(onlineCheckBox[i]).equals("Y")){
							c++;
						}
				}
			}
			if(onlineCheckBox != null)
			{
			onLineListValues = new Object[onlineCheckBox.length][11];
			}
			if(onlineCheckBox != null && onlineCheckBox.length != 0){
				for (int j = 0; j < onlineCheckBox.length ; j++) {
					if(String.valueOf(onlineCheckBox[j]).equals("Y")){
						logger.info("onlineCheckBox  "+onlineCheckBox[j]);
						onLineListValues[j][0]=onlineReqName[j];
						onLineListValues[j][1]=onlineReqCode[j];
						onLineListValues[j][2]=onlineCandName[j];
						onLineListValues[j][3]=onlineCandCode[j];
						onLineListValues[j][4]=onlineEmailId[j];
						onLineListValues[j][5]=onlineContactNo[j];
						onLineListValues[j][6]=onlineTestDate[j];
						onLineListValues[j][7]=onlineTestTime[j];
						onLineListValues[j][8]=onlineObtMarks[j];
						onLineListValues[j][9]=onlineTotalMarks[j];
						onLineListValues[j][10]=onlineCandCode[j];
						logger.info("formListValues    :" + onLineListValues[j][0]);
						logger.info("formListValues    :" + onLineListValues[j][1]);
						logger.info("formListValues    :" + onLineListValues[j][2]);
						logger.info("formListValues    :" + onLineListValues[j][3]);
						logger.info("formListValues    :" + onLineListValues[j][4]);
						logger.info("formListValues    :" + onLineListValues[j][5]);
						logger.info("formListValues    :" + onLineListValues[j][6]);
						logger.info("formListValues    :" + onLineListValues[j][7]);
						logger.info("formListValues    :" + onLineListValues[j][8]);
						logger.info("formListValues    :" + onLineListValues[j][9]);
						logger.info("formListValues    :" + onLineListValues[j][10]);
					}
					
				}
						
					}
		}
		
		return onLineListValues;
	}


	
	private Object[][] setValuesForTransfer(HttpServletRequest request,String type) {
		Object [][] writtenListValues = null;
		if(type.equals("written")){
			
			String [] writtenCandCode = request.getParameterValues("writtenCandCode");	//cand code of written test list
			String [] writtenCheckBox = request.getParameterValues("written");	//check box of written test list
			
			String [] writtenReqName = request.getParameterValues("writtenReqName");
			String [] writtenReqCode = request.getParameterValues("writtenReqCode");
			
			String [] writtenCandName = request.getParameterValues("writtenCandName");
			//String [] writtenCandCode = request.getParameterValues("writtenCandCode");
			String [] writtenEmailId = request.getParameterValues("writtenEmailId");
			String [] writtenContactNo = request.getParameterValues("writtenContactNo");
			String [] writtenTestDate = request.getParameterValues("writtenTestDate");
			String [] writtenTestTime = request.getParameterValues("writtenTestTime");
			String [] writtenObtMarks = request.getParameterValues("writtenObtMarks");
			String [] writtenTotalMarks = request.getParameterValues("writtenTotalMarks");
			String [] writtenResult = request.getParameterValues("writtenResult");
			int c = 0;
			if(writtenCandCode!=null){
			for (int i = 0; i < writtenCandCode.length; i++) {
				if(String.valueOf(writtenCheckBox[i]).equals("Y")){
					c++;
				}
			}
			}
			if(writtenCheckBox != null)
			{
			writtenListValues = new Object[writtenCheckBox.length][11];
			}
			if(writtenCheckBox != null && writtenCheckBox.length != 0){
				for (int j = 0; j < writtenCheckBox.length; j++) {
					if(String.valueOf(writtenCheckBox[j]).equals("Y")){
						logger.info("writtenCheckBox  "+writtenCheckBox[j]);
						writtenListValues[j][0]=writtenReqName[j];
						writtenListValues[j][1]=writtenReqCode[j];
						writtenListValues[j][2]=writtenCandName[j];
						writtenListValues[j][3]=writtenCandCode[j];
						writtenListValues[j][4]=writtenEmailId[j];
						writtenListValues[j][5]=writtenContactNo[j];
						writtenListValues[j][6]=writtenTestDate[j];
						writtenListValues[j][7]=writtenTestTime[j];
						writtenListValues[j][8]=writtenObtMarks[j];
						writtenListValues[j][9]=writtenTotalMarks[j];
						writtenListValues[j][10]=writtenCandCode[j];
						logger.info("formListValues    :" + writtenListValues[j][0]);
						logger.info("formListValues    :" + writtenListValues[j][1]);
						logger.info("formListValues    :" + writtenListValues[j][2]);
						logger.info("formListValues    :" + writtenListValues[j][3]);
						logger.info("formListValues    :" + writtenListValues[j][4]);
						logger.info("formListValues    :" + writtenListValues[j][5]);
						logger.info("formListValues    :" + writtenListValues[j][6]);
						logger.info("formListValues    :" + writtenListValues[j][7]);
						logger.info("formListValues    :" + writtenListValues[j][8]);
						logger.info("formListValues    :" + writtenListValues[j][9]);
						logger.info("formListValues    :" + writtenListValues[j][10]);
					}
					
				}
						
					}
		}
		
		return writtenListValues;
	}

	
	public String resetList(){
		testResult.setWrittenTestList(null);
		testResult.setOnLineTestList(null);
		testResult.setShowFlag("false");
		
		return "success";
	}
	/**@method f9RequisitionCodeAction 
	 * @purpose to select the requisition code
	 * @return String
	 */
	public String f9RequisitionCodeAction(){
		
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT REQS_NAME,  RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'), REQS_CODE,  "
					   +"EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID "
					   +"FROM HRMS_REC_REQS_HDR "
					   +"INNER JOIN HRMS_EMP_OFFC ON (HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER = HRMS_EMP_OFFC.EMP_ID) "
					   +"INNER JOIN HRMS_RANK ON (RANK_ID = REQS_POSITION) "
					   +"INNER JOIN HRMS_REC_VACPUB_HDR ON (PUB_REQS_CODE =  REQS_CODE) " +
					   	" INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE =  HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) " 
					   +"INNER JOIN HRMS_REC_VACPUB_RECDTL ON (HRMS_REC_VACPUB_HDR.PUB_CODE =  HRMS_REC_VACPUB_RECDTL.PUB_CODE "
					   +"AND PUB_REC_EMPID = "+testResult.getUserEmpId()+") " 
					   +"WHERE REQS_APPROVAL_STATUS IN ('A','Q') AND REQS_STATUS = 'O' AND PUB_STATUS = 'P' "
					   +"ORDER BY REQS_DATE desc";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String [] headers = {getMessage("reqs.code"), getMessage("position"), getMessage("reqs.date"),"Required By Date"};
		
		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */ 
		String [] headerWidth = {"30", "50","50","15"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		String [] fieldNames = {"requisitionName", "position","reqDate","dummyField", "requisitionCode", "hiringManager", "hiringManagerCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int [] columnIndex = {0, 1, 2,3, 4, 5,6};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "TestResult_resetList.action";
		
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		
		return "f9page";
	}

}
