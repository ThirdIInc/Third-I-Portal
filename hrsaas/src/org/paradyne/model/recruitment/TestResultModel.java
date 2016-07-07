package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.TestResult;
import org.paradyne.lib.ModelBase;

/**
 * @author aa0540
 *
 */
public class TestResultModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TestResultModel.class);
	
	/**
	 * @Method showTestResult
	 * @Purpose to display the test result of the candidates
	 * @param bean
	**/
	public void showTestResult(TestResult bean) {
		ArrayList<Object> writtenTestList = new ArrayList<Object>();
		ArrayList<Object> onLineTestList  = new ArrayList<Object>();
		
		String query = getQuery(1);
		
		//select the test details as per the selected criteria
		query += "WHERE TEST_REC_EMPID = "+bean.getUserEmpId()+" AND"
			  +" HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = "+bean.getRequisitionCode();
		
		//date criteria
		if(!bean.getFromDate().equals("") && !bean.getToDate().equals("")){
			query += " AND HRMS_REC_SCHTEST_DTL.TEST_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY') "
					 +" AND TO_DATE('"+bean.getToDate()+"', 'DD-MM-YYYY') ";
		}
		else if(!bean.getFromDate().equals("")){
			query += " AND HRMS_REC_SCHTEST_DTL.TEST_DATE >= TO_DATE('"+bean.getFromDate()+"', 'DD-MM-YYYY')";
		}
		else if(!bean.getToDate().equals("")){
			query += " AND HRMS_REC_SCHTEST_DTL.TEST_DATE <= TO_DATE('"+bean.getToDate()+"', 'DD-MM-YYYY')";
		}
		
		//test type criteria
		
		if(bean.getHidTestTypeForSchTest().equals("B")){
			query += " AND HRMS_REC_SCHTEST.TEST_TYPE IN ('W', 'O')";
		} else if(bean.getHidTestTypeForSchTest().equals("O")) {
			query += " AND HRMS_REC_SCHTEST.TEST_TYPE = '"+bean.getHidTestTypeForSchTest()+"' " +
					 " AND (HRMS_REC_SCHTEST_DTL.TEST_STATUS = 'Y' AND HRMS_REC_SCHTEST.TEST_TYPE = 'O') " ;
		} else {
			query += " AND HRMS_REC_SCHTEST.TEST_TYPE = '"+bean.getHidTestTypeForSchTest()+"' ";
		}
		
		
		
		//obtained marks criteria
		if(bean.getSearchCriteria().equals("=")){
			query += " AND TEST_OBT_MARKS = "+bean.getMarksObtained();
		}
		else if(bean.getSearchCriteria().equals("<")){
			query += " AND TEST_OBT_MARKS < "+bean.getMarksObtained();
		}
		else if(bean.getSearchCriteria().equals(">")){
			query += " AND TEST_OBT_MARKS > "+bean.getMarksObtained();
		}
		else if(bean.getSearchCriteria().equals("<=")){
			query += " AND TEST_OBT_MARKS <= "+bean.getMarksObtained();
		}
		else if(bean.getSearchCriteria().equals(">=")){
			query += " AND TEST_OBT_MARKS >= "+bean.getMarksObtained();
		}
		else if(bean.getSearchCriteria().equals("!=")){
			query += " AND TEST_OBT_MARKS != "+bean.getMarksObtained();
		}
		
		//test result type criteria
		if(!bean.getResultType().equals("")){
			query += " AND TEST_RESULT = '"+bean.getResultType()+"'";
		}
		
		query += " AND HRMS_REC_SCHTEST_DTL.TEST_STATUS NOT IN ('R','X','C') AND HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE NOT IN (SELECT INT_CAND_CODE "
			 	 +"FROM HRMS_REC_SCHINT_DTL WHERE INT_REQS_CODE = "+bean.getRequisitionCode()+")";
		
		query += " ORDER BY UPPER(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME)";
		logger.info("query   "+query);
		
		Object [][] candData = getSqlModel().getSingleResult(query);
		
		try {
			if(candData != null && candData.length != 0){
				logger.info("cand length "+candData.length);
				try {
					for (int i = 0; i < candData.length; i++) {
						if(String.valueOf(candData[i][12]).equals("W")){
							TestResult bean1 = new TestResult();
							bean.setNotAvailable("false");
							bean1.setWrittenReqCode(bean.getRequisitionCode());	//reqs code for written test result list
							bean1.setWrittenReqName(bean.getRequisitionName());	//reqs name for written test result list
							bean1.setWrittenTestDtlCode(checkNull(String.valueOf(candData[i][0])));
							bean1.setWrittenCandCode(checkNull(String.valueOf(candData[i][2])));//cand code for written test result list
							bean1.setWrittenCandName(checkNull(String.valueOf(candData[i][3])));//cand name for written test result list
							bean1.setWrittenEmailId(checkNull(String.valueOf(candData[i][5])));//email id for written test result list
							bean1.setWrittenContactNo(checkNull(String.valueOf(candData[i][4]).trim()));//contact no for written test result list
							bean1.setWrittenTestDate(checkNull(String.valueOf(candData[i][7])));//test date for written test result list
							bean1.setWrittenTestTime(checkNull(String.valueOf(candData[i][8]).trim()));//test time for written test result list
							
							//hidden test result for written test result list
							if(checkNull(String.valueOf(candData[i][11])).equals("P"))bean1.setWrittenHiddenResult("Pass");
							else if(checkNull(String.valueOf(candData[i][11])).equals("F"))bean1.setWrittenHiddenResult("Fail");
							
							//written flag
							/*if(!checkNull(String.valueOf(candData[i][13])).equals(""))bean1.setWrittenFlag("true");
							else bean1.setWrittenFlag("false");*/
							logger.info("candData[i][16]==="+candData[i][16]);
							if(String.valueOf(candData[i][16]).equals("Y")){
								bean1.setWrittenFlag("true");
								bean1.setWrittenObtMarks(checkNull(String.valueOf(candData[i][10])));//obtained marks for written test result list
								bean1.setWrittenTotalMarks(checkNull(String.valueOf(candData[i][9])));//total marks for written test result list
								bean1.setWrittenResult(checkNull(String.valueOf(candData[i][11])));//test result for written test result list
								bean1.setWrittenResultCode(checkNull(String.valueOf(candData[i][13])));
								bean1.setWrittenComments(checkNull(String.valueOf(candData[i][14])));//written comments
							} //end of if
							else{
								bean1.setWrittenFlag("false");
								bean1.setWrittenObtMarks("");
								bean1.setWrittenTotalMarks("");
								bean1.setWrittenResult("S");
								bean1.setWrittenResultCode("");
								bean1.setWrittenComments("");//written comments
							} //end of else
							
							
							//bean1.setWrittenResultCode(checkNull(String.valueOf(candData[i][13])));
							bean1.setWrittenTestRound(checkNull(String.valueOf(candData[i][15]))); //round type
							bean1.setWrittenTotalMarks(checkNull(String.valueOf(candData[i][19])));
							writtenTestList.add(bean1);
						}
						else if(String.valueOf(candData[i][12]).equals("O")){
							TestResult bean2 = new TestResult();
							bean.setNotAvailableOnline("false");
							bean2.setOnLineReqCode(bean.getRequisitionCode());//reqs code for online test result list
							bean2.setOnLineReqName(bean.getRequisitionName());//reqs name for online test result list
							bean2.setOnlineTestDtlCode(checkNull(String.valueOf(candData[i][0])));
							bean2.setOnLineCandCode(checkNull(String.valueOf(candData[i][2])));//cand code for online test result list
							bean2.setOnLineCandName(checkNull(String.valueOf(candData[i][3])));//cand name for online test result list
							bean2.setOnLineEmailId(checkNull(String.valueOf(candData[i][5])));//email id for online test result list
							bean2.setOnLineContactNo(checkNull(String.valueOf(candData[i][4])));//contact no for online test result list
							bean2.setOnLineTestDate(checkNull(String.valueOf(candData[i][7])));//test date for online test result list
							bean2.setOnLineTestTime(checkNull(String.valueOf(candData[i][8])));//test time for online test result list
							bean2.setOnLineTestRound(checkNull(String.valueOf(candData[i][15])));//round type
							if(checkNull(String.valueOf(candData[i][11])).equals("P")) {
								if (checkNull(String.valueOf(candData[i][20])).equals("O")) {
									bean2.setOnLineResult("Pass");
								} else {
									bean2.setOnLineResult("P");
								}
							} else if(checkNull(String.valueOf(candData[i][11])).equals("F")) {
								if (checkNull(String.valueOf(candData[i][20])).equals("O")) {
									bean2.setOnLineResult("Fail");
								} else {
									bean2.setOnLineResult("F");
								}
							} else {
								bean2.setOnLineResult("");
							}
							
							if(String.valueOf(candData[i][16]).equals("Y")){
								bean2.setOnlineFlag("true");
								bean2.setOnLineObtMarks(checkNull(String.valueOf(candData[i][10])));//obtained marks for online test result list
								//bean2.setOnLineTotalMarks(checkNull(String.valueOf(candData[i][9])));//total marks for online test result list
								bean2.setOnlineResultCode(checkNull(String.valueOf(candData[i][13])));
								bean2.setOnlineComments(checkNull(String.valueOf(candData[i][14])));//online comments
							} //end of if
							else{
								bean2.setOnlineFlag("false");
								bean2.setOnLineObtMarks("");//obtained marks for online test result list
								//bean2.setOnLineTotalMarks("");//total marks for online test result list
								bean2.setOnlineResultCode("");
								bean2.setOnlineComments("");//online comments
							} //end of else
							bean2.setObjectiveMarksObtained(checkNull(String.valueOf(candData[i][17]))); 
							bean2.setSubjectiveMarksObtained(checkNull(String.valueOf(candData[i][18]))); 
							bean2.setOnLineTotalMarks(checkNull(String.valueOf(candData[i][19])));//total marks for online test result list
							if (checkNull(String.valueOf(candData[i][20])).equals("S") || 
								checkNull(String.valueOf(candData[i][20])).equals("B")	) {
								bean2.setBothOrSubjectiveTypeTestFlag(true);
							}
							bean2.setOnlineTestType(checkNull(String.valueOf(candData[i][20])));
							bean2.setOnlineTestTemplateCode(checkNull(String.valueOf(candData[i][21])));
							onLineTestList.add(bean2);
						}
					}
				} catch (Exception e) {
					logger.error("exception in candData for loop", e);
				} //end of catch
			} //end of if
			else{
				bean.setNotAvailable("true");
				bean.setNotAvailableOnline("true");
			} //end of else
		} catch (Exception e) {
			// TODO: handle exception
		}
		bean.setWrittenTestList(writtenTestList);
		bean.setOnLineTestList(onLineTestList);
	}
	
	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result value of the data
	 * @return String
	**/
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}	//end of if
		else {
			return result;
		}	//end of else
	}
	
	public String checkNull1(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		}	//end of if
		else {
			return result;
		}	//end of else
	}

	/**
	 * @Method save
	 * @Purpose to save the result of the written test of the candidate
	 * @param bean
	 * @param request
	 * @return boolean
	**/
	public boolean save(TestResult bean, HttpServletRequest request) {
		Object [][] insertData = null;
		Object [][] updateFlagData = null;
		boolean result = false;
		int count = 0;
		String [] writtenTestDtlCode    = request.getParameterValues("writtenTestDtlCode");	//test dtl code
		String [] resultCode    = request.getParameterValues("writtenResultCode");	//reqs code
		String [] candCode      = request.getParameterValues("writtenCandCode");	//cand code
		String [] totalMarks    = request.getParameterValues("writtenTotalMarks");	//total marks
		String [] obtainedMarks = request.getParameterValues("writtenObtMarks");	//obtained marks
		String [] testResult    = request.getParameterValues("writtenResult");		//test result
		String [] checkBox      = request.getParameterValues("written");			//value of checkbox
		String [] writtenComment      = request.getParameterValues("writtenComments");//value of written comment
		String updateQuery = "UPDATE HRMS_REC_SCHTEST_DTL set TEST_STATUS='Y' where TEST_REQS_CODE=? and TEST_CAND_CODE=? AND TEST_STATUS='N'";
		try {
			if(resultCode != null && resultCode.length != 0){
				for (int i = 0; i < resultCode.length; i++) {
					if(String.valueOf(resultCode[i]).equals("") && String.valueOf(checkBox[i]).equals("Y"))count++;
				}
			}
			if(count != 0){
				insertData = new Object[count][8];
				updateFlagData = new Object[count][2];
				count = 0;
				if(resultCode != null && resultCode.length != 0){
					for (int i = 0; i < resultCode.length; i++) {
						if(String.valueOf(resultCode[i]).equals("") && String.valueOf(checkBox[i]).equals("Y")){
							insertData [count][0] = bean.getRequisitionCode();	//reqs code
							insertData [count][1] = candCode[i];				//cand code
							insertData [count][2] = totalMarks[i];				//total marks
							insertData [count][3] = obtainedMarks[i];			//obtained marks
							insertData [count][4] = testResult[i];				//test result
							insertData [count][5] = "W";						//test type
							insertData [count][6] = writtenComment[i];//written comment
							insertData [count][7] = writtenTestDtlCode[i];//test dtl code
							
							updateFlagData[count][0] = bean.getRequisitionCode();	//reqs code
							updateFlagData[count][1] =candCode[i];	//cand code
							
							count++;
						}
					}
				}
				
				result = getSqlModel().singleExecute(getQuery(2), insertData);
				result = getSqlModel().singleExecute(updateQuery, updateFlagData);
			}
			else result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/**
		 * edited by varun on 02-11-2009
		 */
		Object[][] insertOnlineData = null;
		Object[][] updateOnlineFlagData = null;
		int onlineCount = 0;
		
		try {
			String [] onlineTestDtlCode    = request.getParameterValues("onlineTestDtlCode");	//test dtl code
			String [] onlineResultCode    = request.getParameterValues("onlineResultCode");	//reqs code
			String [] onlineReqCode    = request.getParameterValues("onLineReqCode");	//reqs code
			String [] onlineCandCode      = request.getParameterValues("onLineCandCode");	//cand code
			String [] onlineTotalMarks    = request.getParameterValues("onLineTotalMarks");	//total marks
			String [] onlineObtainedMarks = request.getParameterValues("onLineObtMarks");	//obtained marks
			String [] onlineTestResult    = request.getParameterValues("onLineResult");		//test result
			String [] onlineCheckBox      = request.getParameterValues("onLine");			//value of checkbox
			String [] onlineComment      = request.getParameterValues("onlineComments");//value of written comment
			String[] subjectiveMarksObtained = request.getParameterValues("subjectiveMarksObtained");
			String[] onlineTestType = request.getParameterValues("onlineTestType");
			
			//String updateOnlineQuery = "update HRMS_REC_SCHTEST_DTL set TEST_STATUS='Y' where TEST_REQS_CODE=? and TEST_CAND_CODE=? AND TEST_STATUS='N'";
			
			if(onlineResultCode != null && onlineResultCode.length != 0){
				for (int i = 0; i < onlineResultCode.length; i++) {
					if(!String.valueOf(onlineResultCode[i]).equals("") && String.valueOf(onlineCheckBox[i]).equals("Y"))
						onlineCount++;
				} //end of loop
			} //end of if
			 
			if(onlineCount != 0){
				insertOnlineData = new Object[onlineCount][10];
				//updateOnlineFlagData = new Object[onlineCount][2];
				onlineCount = 0;
				if(onlineResultCode != null && onlineResultCode.length != 0){
					for (int i = 0; i < onlineResultCode.length; i++) {
						if(!String.valueOf(onlineResultCode[i]).equals("") && String.valueOf(onlineCheckBox[i]).equals("Y")){
							
							insertOnlineData [onlineCount][0] = onlineTotalMarks[i];//total marks
							insertOnlineData [onlineCount][1] = onlineObtainedMarks[i];//obtained marks
							if (onlineTestResult[i].equals("Pass") || onlineTestResult[i].equals("P")) {
								insertOnlineData [onlineCount][2] = "P";
							} else {
								insertOnlineData [onlineCount][2] = "F";
							}
							//insertOnlineData [onlineCount][2] = onlineTestResult[i];//test result
							insertOnlineData [onlineCount][3] = onlineTestType[i];//test type
							insertOnlineData [onlineCount][4] = onlineComment[i];//written comment
							insertOnlineData [onlineCount][5] = onlineTestDtlCode[i];//Test Dtl code
							insertOnlineData [onlineCount][6] = subjectiveMarksObtained[i]; //subjective marks obtained
							insertOnlineData [onlineCount][7] = bean.getRequisitionCode();	//reqs code
							insertOnlineData [onlineCount][8] = onlineCandCode[i];//cand code
							insertOnlineData [onlineCount][9] = onlineResultCode[i];//result code
							
							//updateOnlineFlagData[onlineCount][0] = bean.getRequisitionCode();//reqs code
							//updateOnlineFlagData[onlineCount][1] =onlineCandCode[i];//cand code
							
							onlineCount++;
						} //end of if
					} //end of loop
				} //end of if
				 
				String updateTestReultDataQuery = "UPDATE HRMS_REC_TESTRESULT SET TEST_TOTAL_MARKS=?, TEST_OBT_MARKS=?, TEST_RESULT=?, TEST_TYPE=?, TEST_COMMENTS=?,TEST_DTL_CODE=?, TEST_SUBJECTIVE_MARKS=?" +
												  " WHERE TEST_REQS_CODE = ? AND TEST_CAND_CODE = ? AND TEST_RESULT_CODE = ?";
				result = getSqlModel().singleExecute(updateTestReultDataQuery, insertOnlineData);
				//result = getSqlModel().singleExecute(updateOnlineQuery, updateOnlineFlagData);
					
			} //end of online if
			else result = true;
			
		} catch (Exception e) {
			logger.error("exception in online save",e);
		} //end of catch
		
		return result;
	} //end of save method

}
