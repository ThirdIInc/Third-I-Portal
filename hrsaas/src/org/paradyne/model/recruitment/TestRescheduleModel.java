/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.TestReschedule;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.struts.action.recruitment.SchdInterviewListAction;

/**
 * @author AA0517
 *
 */
public class TestRescheduleModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SchdInterviewListAction.class);
	public void getSchdTestList(TestReschedule testResch, String status,HttpServletRequest request) {
		Object[][]schdTestData = null;
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			String query = "SELECT  RESUME_REQS_CODE, REQS_NAME, RESUME_CAND_CODE, CAND_FIRST_NAME||' '|| "
				+" CAND_LAST_NAME,TO_CHAR(TEST_DATE,'DD-MM-YYYY'),TEST_TIME,TEST_COMMENTS,TEST_CODE,TEST_DTL_CODE,TEST_ROUND_TYPE,DECODE(TEST_TYPE,'W','Written','O','Online') "
				+" FROM HRMS_REC_RESUME_BANK  "
				+" INNER JOIN HRMS_REC_SCHTEST_DTL ON (HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE " 
				+" AND TEST_STATUS ='"+status+"' AND HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)  "
				+" INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) " 
				+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)  "
				+" INNER JOIN HRMS_REC_SCHTEST ON(HRMS_REC_SCHTEST.TEST_CODE = HRMS_REC_SCHTEST_DTL.TEST_CODE)"
				+" WHERE (RESUME_SHEDULE_STATUS = 'T'  "
				+" OR RESUME_SHEDULE_STATUS = 'F') AND RESUME_REC_EMPID = "+testResch.getUserEmpId()+" AND REQS_STATUS != 'C' ORDER BY TEST_DATE DESC";
			schdTestData = getSqlModel().getSingleResult(query);
			if(schdTestData!=null && schdTestData.length>0)
			{
				testResch.setModeLength("true");	
			}else
			{
				testResch.setModeLength("false");	
			}
			
		} catch (Exception e) {
			logger.error("exception in schdTestData query",e);
		} //end of catch
		
		String[] pageIndex = Utility.doPaging(testResch.getMyPage(),schdTestData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		logger.info("my page in action"+testResch.getMyPage());
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			testResch.setMyPage("1");
		
		if (schdTestData == null) {
			logger.info("3");
			testResch.setNoData("true");
			testResch.setList(null);
			testResch.setTotalRecords(String.valueOf(schdTestData.length));
		}//end of if 
		else if (schdTestData.length == 0) {
			logger.info("4");
			testResch.setNoData("true");
			testResch.setList(null);
			testResch.setTotalRecords(String.valueOf("0"));
		}//end of else
		else{
			logger.info("1");
			for (int i =Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++) {
				TestReschedule bean = new TestReschedule();
				logger.info("2");	
				bean.setRequisitionCode(String.valueOf(schdTestData[i][0]));
				bean.setRequisitionName(String.valueOf(schdTestData[i][1]));
				bean.setCandidateCode(String.valueOf(schdTestData[i][2]));
				bean.setCandidateName(String.valueOf(schdTestData[i][3]));
				bean.setTestDate(String.valueOf(schdTestData[i][4]));
				bean.setTestTime(String.valueOf(schdTestData[i][5]));
				bean.setComments(checkNull(String.valueOf(schdTestData[i][6])));
				bean.setTestCode(String.valueOf(schdTestData[i][7]));
				bean.setTestDtlCode(String.valueOf(schdTestData[i][8]));
				bean.setTestRound(checkNull(String.valueOf(schdTestData[i][9])));
				bean.setTestType(checkNull(String.valueOf(schdTestData[i][10])));
				
				tableList.add(bean);//data added in list
				testResch.setTotalRecords(String.valueOf(schdTestData.length));
				
			} //end of loop
			testResch.setList(tableList);
			testResch.setNoData("false");
		} //end of else
		
	} //end of getSchdTestList method
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void getCancelledTestList(TestReschedule testResch, String status,HttpServletRequest request) {
	Object[][]cancelList = null;
	
	ArrayList<Object> tableList = new ArrayList<Object>();
	try {
		String query = "SELECT  RESUME_REQS_CODE, REQS_NAME, RESUME_CAND_CODE, CAND_FIRST_NAME||' '|| "
			+" CAND_LAST_NAME,TO_CHAR(TEST_DATE,'DD-MM-YYYY'),TEST_TIME,TEST_COMMENTS,TEST_CODE,TEST_DTL_CODE,DECODE(TEST_TYPE,'W','Written','O','Online') "
			+" FROM HRMS_REC_RESUME_BANK  "
			+" INNER JOIN HRMS_REC_SCHTEST_DTL ON (HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE " 
			+" AND TEST_STATUS = '"+status+"' AND HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)  "
			+" INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE = HRMS_REC_RESUME_BANK.RESUME_CAND_CODE) " 
			+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)  "
			+" INNER JOIN HRMS_REC_SCHTEST ON(HRMS_REC_SCHTEST.TEST_CODE = HRMS_REC_SCHTEST_DTL.TEST_CODE)"
			+" WHERE (RESUME_SHEDULE_STATUS = 'T'  "
			+" OR RESUME_SHEDULE_STATUS = 'F') AND RESUME_REC_EMPID = "+testResch.getUserEmpId()+"  ORDER BY TEST_DATE DESC";
		cancelList = getSqlModel().getSingleResult(query);
		if(cancelList!=null && cancelList.length>0)
		{
			testResch.setModeLength("true");	
		}else
		{
			testResch.setModeLength("false");	
		}
	} catch (Exception e) {
		logger.error("exception in cancelList query",e);
	} //end of catch
	
	String[] pageIndex = Utility.doPaging(testResch.getMyPage(),cancelList.length, 20);	
	if(pageIndex==null){
		pageIndex[0] = "0";
		pageIndex[1] ="20";
		pageIndex[2] = "1";
		pageIndex[3] = "1";
		pageIndex[4] = "";
	}
	logger.info("my page in action"+testResch.getMyPage());
	request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
	request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
	if(pageIndex[4].equals("1"))
		testResch.setMyPage("1");
	
	if (cancelList == null) {
		logger.info("3");
		testResch.setNoData("true");
		testResch.setList(null);
		testResch.setTotalRecords(String.valueOf(0));
	}//end of if 
	else if (cancelList.length == 0) {
		logger.info("4");
		testResch.setNoData("true");
		testResch.setList(null);
		testResch.setTotalRecords(String.valueOf("0"));
	}//end of else
	else{
		logger.info("1");
		for (int i =Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer.parseInt(String.valueOf(pageIndex[1])); i++) {
			TestReschedule bean = new TestReschedule();
			logger.info("2");	
			bean.setRequisitionCode(String.valueOf(cancelList[i][0]));
			bean.setRequisitionName(String.valueOf(cancelList[i][1]));
			bean.setCandidateCode(String.valueOf(cancelList[i][2]));
			bean.setCandidateName(String.valueOf(cancelList[i][3]));
			bean.setTestDate(String.valueOf(cancelList[i][4]));
			bean.setTestTime(String.valueOf(cancelList[i][5]));
			bean.setComments(checkNull(String.valueOf(cancelList[i][6])));
			bean.setTestCode(String.valueOf(cancelList[i][7]));
			bean.setTestDtlCode(String.valueOf(cancelList[i][8]));
			bean.setTestType(String.valueOf(cancelList[i][9]));
			
			tableList.add(bean);//data added in list
			testResch.setTotalRecords(String.valueOf(cancelList.length));
			
		} //end of loop
		testResch.setList(tableList);
		testResch.setNoData("false");
	} //end of else
		
	} //end of getCancelledTestList method

	public String cancelSchedule(TestReschedule testResch,HttpServletRequest request, String requisitionCode,
			String testCode, String testDtlCode) {
		boolean result = false;
		String value = "";
		try {
			String update = "UPDATE HRMS_REC_SCHTEST_DTL SET TEST_STATUS = 'C' "
					+ " WHERE TEST_DTL_CODE = " + testDtlCode + " ";
			result = getSqlModel().singleExecute(update);
		} catch (Exception e) {
			logger.error("exception in update cancel schedule",e);
		} //end of catch
		if(result){
			value = "1";
			return value;
		}
		return null;
	} //end of cancelSchedule method
} //end of class
