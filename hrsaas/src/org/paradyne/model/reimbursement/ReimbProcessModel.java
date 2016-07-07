/**
 * 
 */
package org.paradyne.model.reimbursement;

import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.reimbursement.ReimbProcess;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA0554
 *
 */
public class ReimbProcessModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ReimbProcessModel.class);
	public void getProcessList(ReimbProcess bean,HttpServletRequest request) {

		String processListQuery = "SELECT DIV_NAME,REIMB_CODE, REIMB_CREDIT_CODE,CREDIT_NAME, " 
						+" TO_CHAR(REIMB_PROCESS_DATE,'DD-MM-YYYY'), REIMB_PROCESS_TYPE, "
						+" TO_CHAR(TO_DATE(REIMB_FROM_MONTH,'mm'),'Month') FROMMO, TO_CHAR(TO_DATE(REIMB_TO_MONTH,'mm') ,'Month'),"
						+" REIMB_YEAR,  REIMB_STATUS, "
						+" TO_CHAR(REIMB_PROCESS_DATE,'DD-MM-YYYY'), REIMB_PROCESS_TYPE FROM HRMS_REIMB_PROCESS_HDR"
						+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=REIMB_CREDIT_CODE)"
						+" INNER JOIN HRMS_DIVISION ON(DIV_ID=REIMB_DIVISION) WHERE REIMB_STATUS='UNLOCK'"; 
		Object [][]processListObj=getSqlModel().getSingleResult(processListQuery);
		if(processListObj !=null && processListObj.length>0){

			String[] pageIndex = null;
				pageIndex = Utility.doPaging(bean.getMyPage(), processListObj.length,
						20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
		
				request.setAttribute("totalPagePending", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoPending", Integer.parseInt(String
						.valueOf(pageIndex[3])));
			
			if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				
			ArrayList tableList = new ArrayList();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				ReimbProcess beanList=new ReimbProcess();
				beanList.setDivisionNameList(Utility.checkNull(String.valueOf(processListObj[i][0])));
				beanList.setProcessCodeList(Utility.checkNull(String.valueOf(processListObj[i][1])));
				beanList.setReimbHeadCodeList(Utility.checkNull(String.valueOf(processListObj[i][2])));
				beanList.setReimbHeadList(Utility.checkNull(String.valueOf(processListObj[i][3])));
				
				beanList.setProcessDateList(Utility.checkNull(String.valueOf(processListObj[i][4])));
				beanList.setProcessTypeList(Utility.checkNull(String.valueOf(processListObj[i][5])));
				beanList.setProcessFromList(Utility.checkNull(String.valueOf(processListObj[i][6])));
				beanList.setProcessToList(Utility.checkNull(String.valueOf(processListObj[i][7])));
				tableList.add(beanList);
			}
				bean.setProcessList(tableList);
				bean.setPendingLength("true");
				bean.setListType("pending");
		
		}
		
		bean.setStatus("UNLOCK");
	}

	public String processReimb(ReimbProcess reimbProcess) {
		String fromDate=reimbProcess.getFromDate();
		String toDate=reimbProcess.getToDate();
		String daysFact="";
		if(reimbProcess.getReimbPeriod().equals("A")){
			fromDate="01-04-"+reimbProcess.getAnnYear();
			toDate="31-03-"+(Integer.parseInt(reimbProcess.getAnnYear())+1);
			daysFact="360";
		}else 
		if(reimbProcess.getReimbPeriod().equals("H")){
			fromDate="01-"+reimbProcess.getHalfMonth()+"-"+reimbProcess.getHalfYear();
			if(reimbProcess.getHalfMonth().equals("4")){
				toDate="30-09-"+(Integer.parseInt(reimbProcess.getHalfYear()));
			}else{
				toDate="31-03-"+(Integer.parseInt(reimbProcess.getHalfYear())+1);
			}
			
			daysFact="180";
		} else 
		if(reimbProcess.getReimbPeriod().equals("Q")){
			fromDate="01-"+reimbProcess.getQuarter()+"-"+reimbProcess.getQuarterYear();
			if(reimbProcess.getQuarter().equals("4")||reimbProcess.getQuarter().equals("7")){
				toDate="30-"+(Integer.parseInt(reimbProcess.getQuarter())+2)+"-"+Integer.parseInt(reimbProcess.getQuarterYear());
			}else
				toDate="31-"+(Integer.parseInt(reimbProcess.getQuarter())+2)+"-"+Integer.parseInt(reimbProcess.getQuarterYear());
			
			daysFact="90";
		}else 
		if(reimbProcess.getReimbPeriod().equals("M")){
			fromDate="01-"+reimbProcess.getMonth()+"-"+reimbProcess.getYear();
			String lastDay=String.valueOf(getSqlModel().getSingleResult("SELECT TO_CHAR(LAST_DAY(TO_DATE('"+fromDate+"','DD-MM-YYYY')),'DD') FROM DUAL")[0][0]);
			
			toDate=lastDay+"-03-"+reimbProcess.getYear();
			daysFact=lastDay;
		}else 
		if(reimbProcess.getReimbPeriod().equals("F")){
			fromDate=reimbProcess.getFromDate();
			toDate=reimbProcess.getToDate();
			daysFact="1";
		}
		String empQuery="SELECT HRMS_EMP_OFFC.EMP_ID"
					+" ,CASE WHEN EMP_REGULAR_DATE BETWEEN TO_DATE('"+fromDate+"','DD-MM-YYYY') AND  TO_DATE('"+toDate+"','DD-MM-YYYY') THEN "
					+" ((DECODE(REIMB_PERIOD,'M',CREDIT_AMT/12,'Q',CREDIT_AMT/4,'A',CREDIT_AMT,'H',CREDIT_AMT/2))/"+daysFact+" * (TO_DATE('"+toDate+"','DD-MM-YYYY')-EMP_REGULAR_DATE+1))  "
					+" WHEN EMP_REGULAR_DATE<=TO_DATE('"+fromDate+"','DD-MM-YYYY') THEN (DECODE(REIMB_PERIOD,'M',CREDIT_AMT/12,'Q',CREDIT_AMT/4,'A',CREDIT_AMT,'H',CREDIT_AMT/2)) ELSE 0 END AS AMT  FROM HRMS_EMP_OFFC"  
					+" INNER JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_CREDIT.EMP_ID) "
					+" INNER JOIN HRMS_REIMB_CONFIG ON (REIMB_CREDIT_HEAD=HRMS_EMP_CREDIT.CREDIT_CODE)"
					+" WHERE HRMS_EMP_CREDIT.CREDIT_CODE="+reimbProcess.getReimbHeadCode()+" AND EMP_STATUS='S' AND EMP_DIV="+reimbProcess.getDivisionCode()
					+" UNION ALL"
					+" SELECT EMP_ID,0 FROM HRMS_EMP_OFFC WHERE EMP_ID NOT IN (SELECT EMP_ID FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE="+reimbProcess.getReimbHeadCode()+")"
					+" AND EMP_STATUS='S' AND EMP_DIV="+reimbProcess.getDivisionCode()
					+" ORDER BY 1";
		boolean result=false;
		Object [][]empCreditObject= getSqlModel().getSingleResult(empQuery);
		if(empCreditObject!= null && empCreditObject.length>0){
			result=saveProcessHDR(reimbProcess,empCreditObject);
		}else{
			return "Employee data not available to process "; 
		}
		if(result){
			return "Reimbursement processed successfully. "; 
		}else{
			return "Error in reimbursement process. "; 
		}
	}

	public boolean saveProcessHDR(ReimbProcess bean,Object [][]empCreditObject) {
		Object[][]maxHDRObj=getSqlModel().getSingleResult("SELECT NVL(MAX(REIMB_CODE),0)+1 FROM HRMS_REIMB_PROCESS_HDR");
		String maxHdrId = String.valueOf(maxHDRObj[0][0]);
		String fromMonth = "";
		String toMonth = "";
		String fromDate = "";
		String toDate = "";
		String year = "";
		boolean result = false;
		String insertQuery="INSERT INTO HRMS_REIMB_PROCESS_HDR ( REIMB_CODE, REIMB_CREDIT_CODE, REIMB_FROM_MONTH,"
						+" REIMB_TO_MONTH, REIMB_YEAR, REIMB_DIVISION, REIMB_STATUS, REIMB_PROCESS_DATE,"
						+" REIMB_PROCESS_TYPE,REIMB_FROM_DATE,REIMB_TO_DATE ) VALUES ( ?, ?, ?, ?, ?, ?, 'UNLOCK', SYSDATE, ?, TO_DATE(?,'DD-MM-YYYY'), TO_DATE(?,'DD-MM-YYYY'))";

		if (bean.getReimbPeriod().equals("A")) {
			fromMonth = "04";
			toMonth = "03";
			year = bean.getAnnYear();
		} else if (bean.getReimbPeriod().equals("H")) {
			fromMonth = bean.getHalfMonth();
			year = bean.getHalfYear();
			if (fromMonth.equals("10"))
				toMonth = "03";
			else
				toMonth = "09";

		} else if (bean.getReimbPeriod().equals("Q")) {
			fromMonth = bean.getQuarter();
			year = bean.getQuarterYear();
			toMonth = String.valueOf((Integer.parseInt(fromMonth) + 2));
		} else if (bean.getReimbPeriod().equals("M")) {
			fromMonth = bean.getMonth();
			year = bean.getYear();
			toMonth = "";
		} else if (bean.getReimbPeriod().equals("F")) {
			fromMonth = "";
			toMonth = "";
			year = "";
			fromDate=bean.getFromDate();
			toDate=bean.getToDate();
		}
		
		Object[][] insertObj = new Object[1][9];
		insertObj[0][0] = maxHdrId;
		insertObj[0][1] = bean.getReimbHeadCode();
		insertObj[0][2] = fromMonth;
		insertObj[0][3] = toMonth;
		insertObj[0][4] = year;
		insertObj[0][5] = bean.getDivisionCode();
		insertObj[0][6] = bean.getReimbPeriod();
		insertObj[0][7] = fromDate;
		insertObj[0][8] = toDate;
		/*
		 * Insert in  HRMS_REIMB_PROCESS_HDR
		 */
		result = getSqlModel().singleExecute(insertQuery, insertObj);
		
		if(result){
			bean.setProcessCode(maxHdrId);
			/*
			 * Insert in  HRMS_REIMB_PROCESS_DTL
			 */
			String insertDtlQuery="INSERT INTO HRMS_REIMB_PROCESS_DTL ( REIMB_CODE, REIMB_EMP_ID,REIMB_AMOUNT ) VALUES ( ?,?,?)";
			Object [][]insertDtlObj=new Object[empCreditObject.length][3];
			for (int i = 0; i < insertDtlObj.length; i++) {
				insertDtlObj[i][0]=maxHdrId;
				insertDtlObj[i][1]=String.valueOf(empCreditObject[i][0]);
				insertDtlObj[i][2]=String.valueOf(empCreditObject[i][1]);
			}
			result = getSqlModel().singleExecute(insertDtlQuery, insertDtlObj);
			logger.info("result true;");
		}
		return result;
	}

	public void showProcessEmployee(ReimbProcess bean,HttpServletRequest request) {
		String empQuery="SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(REIMB_AMOUNT,0),REIMB_EMP_ID FROM HRMS_REIMB_PROCESS_DTL"
				+" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=REIMB_EMP_ID) AND REIMB_CODE="+bean.getProcessCode()
				+" UNION ALL"
				+" SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,0,EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_ID NOT IN "
				+" (SELECT REIMB_EMP_ID FROM HRMS_REIMB_PROCESS_DTL WHERE REIMB_CODE="+bean.getProcessCode()+" )" 
				+" AND EMP_STATUS='S' AND EMP_DIV="+bean.getDivisionCode()+" ORDER BY 2";
		
		Object [][]processListObj=getSqlModel().getSingleResult(empQuery);
		if(processListObj !=null && processListObj.length>0){

			String[] pageIndex = null;
				pageIndex = Utility.doPaging(bean.getMyPage(), processListObj.length,
						20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
		
				request.setAttribute("totalPagePending", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoPending", Integer.parseInt(String
						.valueOf(pageIndex[3])));
			
			if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				
			ArrayList tableList = new ArrayList();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				ReimbProcess beanList=new ReimbProcess();
				beanList.setEmpTokenList(Utility.checkNull(String.valueOf(processListObj[i][0])));
				beanList.setEmpNameList(Utility.checkNull(String.valueOf(processListObj[i][1])));
				beanList.setEmpReimbAmountList(Utility.checkNull(String.valueOf(processListObj[i][2])));
				beanList.setEmpCodeList(Utility.checkNull(String.valueOf(processListObj[i][3])));
				beanList.setStatusList(bean.getStatus());
				
				tableList.add(beanList);
			}
				bean.setEmpProcessList(tableList);
				bean.setEmpProcessLength("true");
		
	}else{
		bean.setEmpProcessLength("false");
	}
	}

	public void getProcessDetails(ReimbProcess reimbProcess) {
		String processQuery="SELECT REIMB_CREDIT_CODE,CREDIT_NAME, REIMB_FROM_MONTH, REIMB_TO_MONTH, REIMB_YEAR, REIMB_DIVISION,DIV_NAME," +
				"  REIMB_STATUS, REIMB_PROCESS_TYPE, TO_CHAR(REIMB_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(REIMB_TO_DATE,'DD-MM-YYYY')  FROM HRMS_REIMB_PROCESS_HDR"
				+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=REIMB_CREDIT_CODE)"
				+" INNER JOIN HRMS_DIVISION ON(DIV_ID=REIMB_DIVISION)"
				+" WHERE REIMB_CODE="+reimbProcess.getProcessCode();
		Object [][]processObj=getSqlModel().getSingleResult(processQuery);
		
		reimbProcess.setReimbHeadCode(Utility.checkNull(String.valueOf(processObj[0][0])));
		reimbProcess.setReimbHeadName(Utility.checkNull(String.valueOf(processObj[0][1])));
		reimbProcess.setStatus(Utility.checkNull(String.valueOf(processObj[0][7])));
		reimbProcess.setReimbPeriod(Utility.checkNull(String.valueOf(processObj[0][8])));
		reimbProcess.setDivisionCode(Utility.checkNull(String.valueOf(processObj[0][5])));
		reimbProcess.setDivisionName(Utility.checkNull(String.valueOf(processObj[0][6])));
		if (reimbProcess.getReimbPeriod().equals("A")) {
			reimbProcess.setAnnYear(Utility.checkNull(String.valueOf(processObj[0][4])));
		}else if (reimbProcess.getReimbPeriod().equals("H")) {
			reimbProcess.setHalfMonth(Utility.checkNull(String.valueOf(processObj[0][2])));
			reimbProcess.setHalfYear(Utility.checkNull(String.valueOf(processObj[0][4])));
		}else if (reimbProcess.getReimbPeriod().equals("Q")) {
			reimbProcess.setQuarter(Utility.checkNull(String.valueOf(processObj[0][2])));
			reimbProcess.setQuarterYear(Utility.checkNull(String.valueOf(processObj[0][4])));
		}else if (reimbProcess.getReimbPeriod().equals("M")) {
			reimbProcess.setMonth(Utility.checkNull(String.valueOf(processObj[0][2])));
			reimbProcess.setYear(Utility.checkNull(String.valueOf(processObj[0][4])));
			
		}else if (reimbProcess.getReimbPeriod().equals("F")) {
			reimbProcess.setFromDate(Utility.checkNull(String.valueOf(processObj[0][9])));
			reimbProcess.setToDate(Utility.checkNull(String.valueOf(processObj[0][10])));
		}
		
	}

	public void getLockedList(ReimbProcess bean,
			HttpServletRequest request) {

		String processListQuery = "SELECT DIV_NAME,REIMB_CODE, REIMB_CREDIT_CODE,CREDIT_NAME, " 
						+" TO_CHAR(REIMB_PROCESS_DATE,'DD-MM-YYYY'), REIMB_PROCESS_TYPE, "
						+" TO_CHAR(TO_DATE(REIMB_FROM_MONTH,'mm'),'Month') FROMMO, TO_CHAR(TO_DATE(REIMB_TO_MONTH,'mm') ,'Month'),"
						+" REIMB_YEAR,  REIMB_STATUS, "
						+" TO_CHAR(REIMB_PROCESS_DATE,'DD-MM-YYYY'), REIMB_PROCESS_TYPE FROM HRMS_REIMB_PROCESS_HDR"
						+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=REIMB_CREDIT_CODE)"
						+" INNER JOIN HRMS_DIVISION ON(DIV_ID=REIMB_DIVISION) WHERE REIMB_STATUS='LOCK'"; 
		Object [][]processListObj=getSqlModel().getSingleResult(processListQuery);
		if(processListObj !=null && processListObj.length>0){

			String[] pageIndex = null;
				pageIndex = Utility.doPaging(bean.getMyPageLocked(), processListObj.length,
						20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
		
				request.setAttribute("totalPage", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
			
			if (pageIndex[4].equals("1"))
					bean.setMyPageLocked("1");
				
			ArrayList tableList = new ArrayList();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				ReimbProcess beanList=new ReimbProcess();
				beanList.setDivisionNameList(Utility.checkNull(String.valueOf(processListObj[i][0])));
				beanList.setProcessCodeList(Utility.checkNull(String.valueOf(processListObj[i][1])));
				beanList.setReimbHeadCodeList(Utility.checkNull(String.valueOf(processListObj[i][2])));
				beanList.setReimbHeadList(Utility.checkNull(String.valueOf(processListObj[i][3])));
				
				beanList.setProcessDateList(Utility.checkNull(String.valueOf(processListObj[i][4])));
				beanList.setProcessTypeList(Utility.checkNull(String.valueOf(processListObj[i][5])));
				beanList.setProcessFromList(Utility.checkNull(String.valueOf(processListObj[i][6])));
				beanList.setProcessToList(Utility.checkNull(String.valueOf(processListObj[i][7])));
				tableList.add(beanList);
			}
				bean.setLockedList(tableList);
				bean.setLockedLength("true");
				bean.setListType("locked");
		
		}
		bean.setStatus("LOCK");
	}

	public void saveReimbAmount(ReimbProcess reimbProcess,
			HttpServletRequest request) {
		String []empId=request.getParameterValues("empCodeList");
		String []reimbAmount=request.getParameterValues("empReimbAmountList");
		boolean result=false;
		/*
		 * Delete HRMS_REIMB_PROCESS_DTL
		 */
		String deleteDtlQuery="DELETE HRMS_REIMB_PROCESS_DTL WHERE REIMB_CODE=? AND REIMB_EMP_ID=?";
		Object [][]deleteDtlObj=new Object[empId.length][2];
		for (int i = 0; i < deleteDtlObj.length; i++) {
			deleteDtlObj[i][0]=reimbProcess.getProcessCode();
			deleteDtlObj[i][1]=empId[i];
		}
		result = getSqlModel().singleExecute(deleteDtlQuery, deleteDtlObj);
		if(result){
		/*
		 * Insert in  HRMS_REIMB_PROCESS_DTL
		 */
		String insertDtlQuery="INSERT INTO HRMS_REIMB_PROCESS_DTL ( REIMB_CODE, REIMB_EMP_ID,REIMB_AMOUNT ) VALUES ( ?,?,?)";
		Object [][]insertDtlObj=new Object[empId.length][3];
		for (int i = 0; i < insertDtlObj.length; i++) {
			insertDtlObj[i][0]=reimbProcess.getProcessCode();
			insertDtlObj[i][1]=empId[i];
			
			insertDtlObj[i][2]=checkNullZero(reimbAmount[i]);
		}
		result = getSqlModel().singleExecute(insertDtlQuery, insertDtlObj);
		}
	}
	public static String checkNullZero(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		} else {
			return result;
		}
	}

	public boolean deleteReimbProcess(ReimbProcess reimbProcess,
			HttpServletRequest request) {
		Object [][]deleteObj=new Object [1][1];
		deleteObj[0][0]=reimbProcess.getProcessCode();
		String queryArray[]={"DELETE FROM HRMS_REIMB_PROCESS_DTL WHERE REIMB_CODE=?","DELETE FROM HRMS_REIMB_PROCESS_HDR WHERE REIMB_CODE=?"};
		Vector dataVector= new Vector();
		dataVector.add(deleteObj);
		dataVector.add(deleteObj);
		boolean result = getSqlModel().multiExecute(queryArray, dataVector);
		return result;
		
	}

	public boolean lockProcess(ReimbProcess reimbProcess) {
		boolean result=false;
		boolean isCarriedForward=false;
		boolean flushCFAmount=true;
		boolean yearStartingFlag=true;
		String updateStatusQuery="UPDATE HRMS_REIMB_PROCESS_HDR SET REIMB_STATUS='LOCK' WHERE REIMB_CODE="+reimbProcess.getProcessCode();
		
		result = getSqlModel().singleExecute(updateStatusQuery);
		
		String reimbConfQuery="SELECT REIMB_CARRIED_FORWARD, REIMB_CARRIED_PERCENTAGE, REIMB_CARRIED_PERIOD,FINANCIAL_FROM_MONTH FROM HRMS_REIMB_CONFIG WHERE REIMB_CREDIT_HEAD= "+reimbProcess.getReimbHeadCode();
		
		Object [][]reimbConfObj=getSqlModel().getSingleResult(reimbConfQuery);
		String carryPercentage=checkNullZero(String.valueOf(reimbConfObj[0][1]));
		
		boolean flagArray[] = getCarryForwardFlags(reimbConfObj, reimbProcess);
		
		isCarriedForward = flagArray[0];
		flushCFAmount = flagArray[1];
		yearStartingFlag = flagArray[2];
		
		if(!yearStartingFlag){
			isCarriedForward=false;
		}
		if (result) {
			String updateCFQuery="";
			String insertEmpNotAvailable="INSERT INTO HRMS_REIMB_BALANCE(REIMB_CREDIT_CODE, REIMB_EMP_ID, REIMB_BALANCE_AMT, REIMB_ONHOLD_AMT, REIMB_PRE_APPPROVED_AMT, REIMB_PRE_APPPROVED_AMT_DTL, REIMB_CF_AMT) (SELECT "+reimbProcess.getReimbHeadCode()+", REIMB_EMP_ID,0,0,0,'',0 FROM HRMS_REIMB_PROCESS_DTL WHERE REIMB_EMP_ID NOT IN " +
						"(SELECT REIMB_EMP_ID FROM HRMS_REIMB_BALANCE WHERE REIMB_CREDIT_CODE="+reimbProcess.getReimbHeadCode()+")AND REIMB_CODE="+reimbProcess.getProcessCode()+")  ";
						getSqlModel().singleExecute(insertEmpNotAvailable);
			String processEmpQuery="SELECT REIMB_EMP_ID,"+reimbProcess.getReimbHeadCode()+" FROM HRMS_REIMB_PROCESS_DTL WHERE REIMB_CODE="+reimbProcess.getProcessCode();
			Object[][] updateCFObj=getSqlModel().getSingleResult(processEmpQuery);
			if(isCarriedForward){
				updateCFQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_CF_AMT=ROUND(REIMB_BALANCE_AMT*"+carryPercentage+"/100) WHERE REIMB_EMP_ID = ? AND REIMB_CREDIT_CODE=? ";
			}else if(flushCFAmount){
				updateCFQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_CF_AMT=0  WHERE REIMB_EMP_ID = ? AND REIMB_CREDIT_CODE=? ";
			}
			if(isCarriedForward || flushCFAmount){
				getSqlModel().singleExecute(updateCFQuery,updateCFObj);
			}
			/*
			 * Update balance amount on lock
			 */
			processEmpQuery="SELECT REIMB_AMOUNT,REIMB_EMP_ID,"+reimbProcess.getReimbHeadCode()+" FROM HRMS_REIMB_PROCESS_DTL WHERE REIMB_CODE="+reimbProcess.getProcessCode();
			String updateBalanceQuery="";
			Object[][] updateBalanceObj=getSqlModel().getSingleResult(processEmpQuery);
			if(yearStartingFlag){
				updateBalanceQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_BALANCE_AMT=?, REIMB_ONHOLD_AMT=0,REIMB_PRE_APPPROVED_AMT='', REIMB_PRE_APPPROVED_AMT_DTL=''  "
					+" WHERE REIMB_EMP_ID=? and REIMB_CREDIT_CODE=?";
			}else {
				updateBalanceQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_BALANCE_AMT=REIMB_BALANCE_AMT+?  "
					+" WHERE REIMB_EMP_ID=? AND REIMB_CREDIT_CODE=?";
			}
			result = getSqlModel().singleExecute(updateBalanceQuery,updateBalanceObj);
		}
		return result;
	}
	public boolean[] getCarryForwardFlags(Object [][]reimbConfObj,ReimbProcess reimbProcess){
		boolean flushCFAmount=false;
		boolean isCarriedForward=true;
		boolean yearStartingFlag=true;
		String reimbPeriod=reimbProcess.getReimbPeriod();
		String carryPercentage=checkNullZero(String.valueOf(reimbConfObj[0][1]));
		String carryPeriod=checkNullZero(String.valueOf(reimbConfObj[0][2]));
		String yearStartingMonth=String.valueOf(reimbConfObj[0][3]);
		try{
			if(String.valueOf(reimbConfObj[0][0]).equals("Y") && !carryPercentage.equals("0") &&
					!carryPercentage.equals("0.0") && !carryPeriod.equals("0")){
				
				
				if(reimbPeriod.equals("A")){
					flushCFAmount = false; 		
					isCarriedForward = true;
					
				}else if(reimbPeriod.equals("H")){
					if(yearStartingMonth.equals("4")){
					if (carryPeriod.equals("1")
							&& reimbProcess.getHalfMonth().equals("10")) {
						flushCFAmount = true; 		
						isCarriedForward = false;
					} else {
						flushCFAmount = false;
						if (reimbProcess.getHalfMonth().equals("4"))
							isCarriedForward = true;
					}
					}else{
						if (carryPeriod.equals("1")
								&& reimbProcess.getHalfMonth().equals("7")) {
							flushCFAmount = true; 		
							isCarriedForward = false;
						} else {
							flushCFAmount = false;
							if (reimbProcess.getHalfMonth().equals("1"))
								isCarriedForward = true;
						}
					}
				} else if (reimbPeriod.equals("Q")) {
						if (!reimbProcess.getQuarter().equals(yearStartingMonth)) {
							if (carryPeriod.equals("1")) {
								flushCFAmount = true;
							} else if (carryPeriod.equals("2")) {
								if (!reimbProcess.getQuarter().equals(String.valueOf(Integer.parseInt(yearStartingMonth)+3))){
									flushCFAmount = true;
								}else{
									flushCFAmount = false;

								}
							} else if (carryPeriod.equals("3")) {
								if(yearStartingMonth.equals("4")){
								if (reimbProcess.getQuarter().equals("1"))
									flushCFAmount = true;
								}else if(yearStartingMonth.equals("1")){
									if (reimbProcess.getQuarter().equals("10"))
										flushCFAmount = true;
									}
							} else { // if carry period is 4 then CF amount need
										// not to be flushed
								flushCFAmount = false;
							}
						} else {
							flushCFAmount = false; // if its first quarter (i.e
													// April) then carry forward the
													// CF amount
							isCarriedForward = true;
						}
					
				} else if (reimbPeriod.equals("M")) {
					String month=reimbProcess.getMonth();
					int monthInt=Integer.parseInt(month);
					int addlMonthCount=0;
					if(yearStartingMonth.equals("4")){
						addlMonthCount=3;
					}
					if(monthInt<4){
						monthInt = monthInt+12;
					}
					if(month.equals(yearStartingMonth)){
						flushCFAmount = false; 		
						isCarriedForward = true;
					}
					else {
						if(monthInt >(Integer.parseInt(carryPeriod)+addlMonthCount) ){
						flushCFAmount = true; 		
						isCarriedForward = false;
					}else {
						flushCFAmount = false; 		
						isCarriedForward = false;
					}
						}
				} else if (reimbPeriod.equals("F")) {

					}
			}
				/*
				 * Set yearStartingFlag condition
				 */		
			if(reimbPeriod.equals("H")){
				if(yearStartingMonth.equals("4")){
				if (reimbProcess.getHalfMonth().equals(yearStartingMonth)) {
					yearStartingFlag=true;
				} else {
						yearStartingFlag=true;
				}
				}
			} else if (reimbPeriod.equals("Q")) {
					if (reimbProcess.getQuarter().equals(yearStartingMonth)) {
						yearStartingFlag=true;
					} else {
						yearStartingFlag=false;
					}
				
			} else if (reimbPeriod.equals("M")) {
				String month=reimbProcess.getMonth();
				if(month.equals(yearStartingMonth)){
					yearStartingFlag=true;
				}
				else {
					yearStartingFlag=false;
					}
			} else if (reimbPeriod.equals("F")) {

				}
			
			}catch (Exception e) {
				// TODO: handle exception
			}
			boolean returnArray[]=new boolean[]{isCarriedForward,flushCFAmount,yearStartingFlag};
			return returnArray;
	}
	
}
