/**
 * 
 */
package org.paradyne.model.payroll.salary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.payroll.salary.LTCProcess;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author REEBA JOSEPH
 * 22 NOVEMBER 2010
 *
 */
public class LTCProcessModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(LTCProcessModel.class);

	public void displayProcessedList(LTCProcess process,
			HttpServletRequest request) {
		String query = "SELECT LTC_CODE, LTC_MONTH, TO_CHAR(TO_DATE(LTC_MONTH,'MM'),'MONTH'), LTC_YEAR, LTC_DIVISION, NVL(DIV_NAME,' ') "
			+ " , NVL(LTC_STATUS,' ') FROM HRMS_LTC  "
			+ " INNER JOIN HRMS_DIVISION ON (DIV_ID = LTC_DIVISION) "
			+ " ORDER BY LTC_CODE "; 
		Object data[][] = getSqlModel().getSingleResult(query);
		
		if(data != null && data.length > 0) {
			process.setTotalRecords(data.length);
			process.setRecordsAvailable(true);
			
			String[] pageIndex = Utility.doPaging(process.getMyPage(), data.length, 20);	
			
			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			if(pageIndex[4].equals("1")) {
				process.setMyPage("1");
			}
			
			ArrayList<Object> processedLtcList = new ArrayList<Object>();
			
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) { 
				LTCProcess bean = new LTCProcess();
				bean.setLtcCodeIt(Utility.checkNull(String.valueOf(data[i][0])));
				bean.setMonthCodeIt(Utility.checkNull(String.valueOf(data[i][1])));
				bean.setMonthIt(Utility.checkNull(String.valueOf(data[i][2])));
				bean.setYearIt(Utility.checkNull(String.valueOf(data[i][3])));
				bean.setDivisionCodeIt(Utility.checkNull(String.valueOf(data[i][4])));
				bean.setDivisionIt(Utility.checkNull(String.valueOf(data[i][5])));
				bean.setStatusIt(Utility.checkNull(String.valueOf(data[i][6])));
				processedLtcList.add(bean);
			}
			process.setProcessedLtcList(processedLtcList);
		}
		
	}

	public String processLTC(LTCProcess process, String year, String month,
			String divisionId) {
		//RETRIEVE LTC HEAD
		String returnStr = "";
		String ltcCodeQuery = " SELECT LTC_CREDIT_HEAD FROM HRMS_LTC_CONFIG ";
		Object[][] ltcCode = getSqlModel().getSingleResult(ltcCodeQuery);
		if(ltcCode !=null && ltcCode.length > 0){
			logger.info("LTC CREDIT HEAD============="+ltcCode[0][0]);
			
			//RETRIEVE ATTENDANCE CONFIGURATION
			String attSettQuery = " SELECT CONF_SALARY_START_DATE, CONF_SALARY_START_FLAG FROM HRMS_ATTENDANCE_CONF ";
			Object[][] attendanceSettings = getSqlModel().getSingleResult(attSettQuery);
			logger.info("SAL START DATE============="+attendanceSettings[0][0]);
			logger.info("SAL START MNTH============="+attendanceSettings[0][1]);
			
			// CALCULATE ATTENDANCE STARTDATE AND ENDDATE
			String salaryStartDate = String.valueOf(attendanceSettings[0][0]);
			String salaryStartMonth = String.valueOf(attendanceSettings[0][1]);
			String attnStartDate = setFromDate(month, year, salaryStartDate, salaryStartMonth);
			String attnEndDate = setToDate(month, year, salaryStartDate, salaryStartMonth);
			logger.info("ATTENDANCE START============="+attnStartDate);
			logger.info("ATTENDANCE END============="+attnEndDate);
			
			String[] attnSplitStart = attnStartDate.split("-");
			String trvlStartDateIn = attnSplitStart[1]+"-"+attnSplitStart[2];
			String[] attnSplitEnd = attnEndDate.split("-");
			String trvlEndDateIn = attnSplitEnd[1]+"-"+attnSplitEnd[2];
			
			// GET MAXIMUM DAYS OF MONTH
			Calendar cal = Calendar.getInstance();
			cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
			int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			logger.info("DAYS_OF_MONTH============="+daysOfMonth);
			
			//RETRIEVE EMPLOYEES WITH LTC
			String empQuery = " SELECT EMP_ID, CREDIT_CODE, CREDIT_AMT FROM HRMS_EMP_CREDIT WHERE CREDIT_AMT > 0 "
				+ " AND CREDIT_CODE = "+ltcCode[0][0]+" ORDER BY EMP_ID";
			Object[][] empObj = getSqlModel().getSingleResult(empQuery);
			
			//RETRIEVE ATTENDANCE DAYS
			String attDaysQuery = " SELECT DISTINCT ATTN_CODE, HRMS_EMP_OFFC.EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME, "
				+ " NVL(ATTN_SAL_DAYS,0)||'d:'||NVL(TO_CHAR(ATTN_SAL_HRS,'HH24'),'00')||'h:'||NVL(TO_CHAR(ATTN_SAL_HRS,'MI'),'00')||'m' "
				+ " FROM HRMS_MONTH_ATTENDANCE_"+year
				+ " INNER JOIN HRMS_EMP_OFFC  on HRMS_MONTH_ATTENDANCE_"+year+".ATTN_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+ " LEFT JOIN HRMS_SALARY_LEDGER ON (LEDGER_CODE = ATTN_CODE) "
				+ " WHERE ATTN_CODE = (SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE " 
				+ " LEDGER_MONTH = "+month+" AND LEDGER_YEAR = "+year+" AND LEDGER_DIVISION = "+divisionId+") AND LEDGER_STATUS != 'ATTN_UNLOCK'"
				+ " ORDER BY UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
			Object[][] attDaysObj = getSqlModel().getSingleResult(attDaysQuery);
			
			HashMap<String, String> attDaysMap = new HashMap<String, String>();
			if(attDaysObj != null && attDaysObj.length > 0){
				for (int i = 0; i < attDaysObj.length; i++) {
					attDaysMap.put(String.valueOf(attDaysObj[i][1]), String.valueOf(attDaysObj[i][4]));
				}
			}else{
				returnStr = "Attendance not processed";
				return returnStr;
			}
			
			//RETRIEVE TRAVEL DAYS
			String trvlDaysQuery = " SELECT APPL_EMP_CODE, SUM( "
				+ " CASE WHEN TO_DATE('"+attnStartDate+"','DD-MM-YYYY') BETWEEN TOUR_TRAVEL_STARTDT  "
				+ " AND TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') THEN "
				+ " TOUR_TRAVEL_ENDDT-TO_DATE('"+attnStartDate+"','DD-MM-YYYY')+1 " 
				+ " WHEN TO_DATE('"+attnEndDate+"','DD-MM-YYYY') BETWEEN TOUR_TRAVEL_STARTDT "  
				+ " AND TO_DATE(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),'DD-MM-YYYY') THEN "
				+ " TO_DATE('"+attnEndDate+"','DD-MM-YYYY')-TOUR_TRAVEL_STARTDT+1 "
				+ " WHEN   TOUR_TRAVEL_ENDDT <TO_DATE('"+attnStartDate+"','DD-MM-YYYY') THEN 0 "
				+ " WHEN   TOUR_TRAVEL_STARTDT >TO_DATE('"+attnEndDate+"','DD-MM-YYYY') THEN 0 "
				+ " ELSE TOUR_TRAVEL_ENDDT-TOUR_TRAVEL_STARTDT+1 END) AS TRVL_DAYS " 
				+ " FROM TMS_APPLICATION "
				+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID = TMS_APPLICATION.APPL_ID ) " 
				+ " WHERE (TO_CHAR(TOUR_TRAVEL_ENDDT,'MM-YYYY') IN('"+trvlStartDateIn+"','"+trvlEndDateIn+"') OR " 
				+ " TO_CHAR(TOUR_TRAVEL_STARTDT ,'MM-YYYY') IN('"+trvlStartDateIn+"','"+trvlEndDateIn+"')) "
				+ " AND APPL_STATUS = 'A' " 
				//+ " AND APPL_EMP_CODE = "+empObj[i][0]
				+ " GROUP BY APPL_EMP_CODE";
			Object[][] trvlDaysObj = getSqlModel().getSingleResult(trvlDaysQuery);
			
			HashMap<String, String> trvlDaysMap = new HashMap<String, String>();
			if(trvlDaysObj != null && trvlDaysObj.length > 0){
				for (int i = 0; i < trvlDaysObj.length; i++) {
					trvlDaysMap.put(String.valueOf(trvlDaysObj[i][0]), String.valueOf(trvlDaysObj[i][1]));
				}
			}
			
			double ltcAmount = 0.0;
			if(empObj != null && empObj.length > 0){
				Object [] daysHrsMinObj = null;
				String attnDays = "";
				String trvlDays = "";
				Object[][] dtlObj = new Object[empObj.length][5];
				for (int i = 0; i < empObj.length; i++) {
					logger.info("EMPLOYEE CODE============="+empObj[i][0]);
					logger.info("CREDIT AMOUNT============="+empObj[i][2]);
					dtlObj[i][0] = empObj[i][0];//LTC_EMP_ID
					
					logger.info("ATTENDANCE DAYS1============="+attDaysMap.get(String.valueOf(empObj[i][0])));
					if(attDaysMap.get(String.valueOf(empObj[i][0])) != null){
						logger.info("ATTENDANCE DAYS============="+attDaysMap.get(String.valueOf(empObj[i][0])));
						daysHrsMinObj = getDaysWithHrsAndMinutes(String.valueOf(attDaysMap.get(String.valueOf(empObj[i][0]))));
						attnDays = String.valueOf(daysHrsMinObj[0]);
					}else
						attnDays = "0";
					logger.info("ATTENDANCE DAYS FINAL============="+attnDays);
					dtlObj[i][2] = attnDays;//ATTENDANCE_DAYS
					
					if(trvlDaysMap.get(String.valueOf(empObj[i][0])) != null){
						trvlDays = String.valueOf(trvlDaysMap.get(String.valueOf(empObj[i][0])));
					}else
						trvlDays = "0";
					logger.info("TRAVEL DAYS============="+trvlDays);
					dtlObj[i][3] = trvlDays;//TRAVEL_DAYS
					
					ltcAmount = (Double.parseDouble(String.valueOf(empObj[i][2]))/daysOfMonth)
							* ((Double.parseDouble(attnDays) - Double.parseDouble(trvlDays)));
					logger.info("LTC AMOUNT============= (CREDIT AMOUNT/daysOfMonth) * (ATTENDANCE DAYS-TRAVEL DAYS )");
					logger.info("LTC AMOUNT============="+ltcAmount);
					if(ltcAmount < 0){
						logger.info("LTC AMOUNT NEGATIVE====SET TO ZERO========="+ltcAmount);
						ltcAmount = 0;
					}
					dtlObj[i][1] = Math.round(ltcAmount);//LTC_AMOUNT
					dtlObj[i][4] = Double.parseDouble(attnDays) - Double.parseDouble(trvlDays);//LTC_DAYS
				}
				
				boolean result = false;
				//INSERT INTO LTC HEADER
				String insertHdr = " INSERT INTO HRMS_LTC (LTC_CODE, LTC_MONTH, LTC_YEAR, LTC_DIVISION, LTC_STATUS) VALUES( "
					+ " (SELECT NVL(MAX(LTC_CODE), 0)+1 FROM HRMS_LTC), "+month+", "+year+", "+divisionId+", 'LTC_START' ) ";
				result = getSqlModel().singleExecute(insertHdr);
				if(result){
					String codeQuery = " SELECT MAX(LTC_CODE) FROM HRMS_LTC";
					Object[][] hdrCode = getSqlModel().getSingleResult(codeQuery);
					
					String insertDtl = " INSERT INTO HRMS_LTC_DTL (LTC_CODE, LTC_EMP_ID, LTC_AMOUNT, ATTENDANCE_DAYS, TRAVEL_DAYS, LTC_DAYS)"
						+ " VALUES ("+hdrCode[0][0]+", ?, ?, ?, ?, ?)";
					result = getSqlModel().singleExecute(insertDtl, dtlObj);
					
					if(result){
						returnStr = "LTC processed successfully.";
					}
				}
			}
		}else{
			returnStr = "LTC Head not configured";
			return returnStr;
		}
		
		return returnStr;
	}
	
	public String setFromDate(String month, String year, String salaryStartDate, String salaryStartMonth) {
		String fromDate = "";
		try {
			fromDate = "01-" + month + "-" + year; // set default fromDate
			
			//if(dailyAttnConnFlag) { // Daily attendance work flow is enabled
				if(salaryStartMonth.equals("P")) { // Check that attendance is calculated from Previous Month
					String newMonth = "", newYear = "";
					
					/**
					 * If current month is Jan, then set new month as Dec and year by 1, else deduct month by 1 and set new year 
					 * same as current year
					 */
					if(month.equals("01")) {
						newMonth = "12";
						newYear = String.valueOf(Integer.parseInt(year) - 1);
					} else {
						newMonth = String.valueOf(Integer.parseInt(month) - 1);
						
						// if month is in between 1 to 9, then add 0 initially
						newMonth = Integer.parseInt(newMonth) < 10 ? "0" + newMonth : newMonth;
						newYear = year;
					}
					fromDate = salaryStartDate + "-" + newMonth + "-" + newYear; // set final from date
				}
			//}
		} catch(Exception e) {
			logger.error("Exception in setFromDate:" + e);
			return fromDate;
		}
		return fromDate;
	}
	
	public String setToDate(String month, String year, String salaryStartDate, String salaryStartMonth) {
		String toDate = "";
		try {
			int monthDays = getMonthDays(month, year); // Calculate total days of a month
			toDate = monthDays + "-" + month + "-" + year; // set default toDate
			int endDate= Integer.parseInt(salaryStartDate);
			endDate -= 1;
			//if(dailyAttnConnFlag) { // daily attendance work flow is enabled
				if(salaryStartMonth.equals("P")) { // Check that attendance is calculated from Previous Month					
					toDate = endDate + "-" + month + "-" + year;
				}
			//}
		} catch(Exception e) {
			logger.error("Exception in setToDate:" + e);
			return toDate;
		}
		return toDate;
	}
	
	private int getMonthDays(String month, String year) {
		int monthDays = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			cal.setTime(Utility.getDate("01-" + month + "-" + year));
			monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			logger.error("Exception in getMonthDays:" + e);
		}
		return monthDays;
	}
	
	public Object [] getDaysWithHrsAndMinutes(String days){
		  Object [] data = new Object[2];
		  try{
			if(days.contains("d")){
				Object []  daysData = days.split(":");
					data [0] = String.valueOf(daysData[0]).substring(0,String.valueOf(daysData[0]).length() - 1);
					data [1] = String.valueOf(daysData[1]).substring(0,String.valueOf(daysData[1]).length() - 1)+":"+
								String.valueOf(daysData[2]).substring(0,String.valueOf(daysData[2]).length() - 1);
			}else{
				  data [0] =days;
				  data [1] ="00:00";
			}
		  }catch(Exception e){
			  logger.error("Exception in getDaysWithHrsAndMinutes to split the days object to hrs and minutes ---------"+e);
			  data [0] ="00";
			  data [1] ="00:00";
		  }
		  return data;
	  }
	
	public String setEmpFiletrs(String[] listOfFilters) {
		String filterQuery="";
		try {
			// if branch is selected
			if(!listOfFilters[0].equals("")) {
				filterQuery += " AND EMP_CENTER = " + listOfFilters[0];
			}
			// if department is selected
			if(!listOfFilters[1].equals("")) {
				filterQuery += " AND EMP_DEPT = " + listOfFilters[1];
			}
			// if paybill group is selected
			if(!listOfFilters[2].equals("")) {
				filterQuery += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			// if employee type is selected
			if(!listOfFilters[3].equals("")) {
				filterQuery += " AND EMP_TYPE = " + listOfFilters[3];
			}
			// if division is selected
			if(!listOfFilters[4].equals("")) {
				filterQuery += " AND EMP_DIV = " + listOfFilters[4];
			}
		} catch (Exception e) {
			logger.error("Exception in setEmpFiletrs:" + e);
		}
		return filterQuery;
	}
	
	public void getEmployeeList(LTCProcess process,HttpServletRequest request, String filterQuery){
		
		try {
			String empQuery=" SELECT LTC_EMP_ID, EMP_TOKEN, EMP_FNAME||' '||NVL(EMP_MNAME,'')||'  '||EMP_LNAME AS NAME, "
				+ " NVL(ATTENDANCE_DAYS,0), NVL(TRAVEL_DAYS, 0), NVL(LTC_DAYS, 0), NVL(LTC_AMOUNT, 0) "
				+ " FROM HRMS_LTC_DTL "
				+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_LTC_DTL.LTC_EMP_ID "
				+ " WHERE 1 = 1 ";
			try{
				empQuery += filterQuery;
			}catch (Exception e) {
				logger.error("Exception in filterQuery: "+e);
			}
			empQuery += " AND LTC_CODE  = "+process.getLtcCode()
				+ " ORDER BY UPPER(NAME)";
			
			Object [][] empObj = getSqlModel().getSingleResult(empQuery);
			
			String[] pageIndex = Utility.doPaging(process.getMyGridPage(),empObj.length, 10);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="10";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			if(pageIndex[4].equals("1"))
				process.setMyGridPage("1");
			
			if (empObj != null && empObj.length != 0) {
				ArrayList<Object> empList = new ArrayList<Object>();
				
				int j=1;
				for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
					LTCProcess bean1 = new LTCProcess();
					try{
						bean1.setEmpId(Utility.checkNull(String.valueOf(empObj[i][0])));
						bean1.setEmpToken(Utility.checkNull(String.valueOf(empObj[i][1])));
						bean1.setEmpName(Utility.checkNull(String.valueOf(empObj[i][2])));
						bean1.setAttnDays(Utility.checkNull(String.valueOf(empObj[i][3])));
						bean1.setTrvlDays(Utility.checkNull(String.valueOf(empObj[i][4])));
						bean1.setLtcDays(String.valueOf(empObj[i][5]));
						bean1.setLtcAmount(String.valueOf(empObj[i][6]));
						
						request.setAttribute("empId"+j, Utility.checkNull(String.valueOf(empObj[i][0])));
						j++;
					}catch(Exception e){
						logger.error("exception in getEmployeeList: "+e);
					}
					empList.add(bean1);
									
				}
				process.setEmpList(empList);
				process.setNoData("false");
			}else
				process.setNoData("true");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public boolean lockLTC(String ltcCode, String month, String year,
			String divCode) {
		boolean result = false;
		String lockQuery = "UPDATE HRMS_LTC SET LTC_STATUS = 'LTC_FINAL' WHERE LTC_MONTH = "
				+ month
				+ " AND LTC_YEAR = "
				+ year
				+ " AND LTC_CODE = "
				+ ltcCode + " AND LTC_DIVISION = " + divCode;
		result = getSqlModel().singleExecute(lockQuery);
		return result;
	}
	
	public boolean save(HttpServletRequest request, String ltcCode){
		boolean result = false;
		try {
			String delLTCDtl = "DELETE FROM HRMS_LTC_DTL WHERE LTC_CODE = "+ ltcCode+" AND LTC_EMP_ID = ?";
			String insertDtl = " INSERT INTO HRMS_LTC_DTL (LTC_CODE, LTC_EMP_ID, LTC_AMOUNT, ATTENDANCE_DAYS, TRAVEL_DAYS, LTC_DAYS)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
			
			String[] emp_id	= request.getParameterValues("empId");
			String[] attnDays = request.getParameterValues("attnDays");
			String[] trvlDays = request.getParameterValues("trvlDays");
			String[] ltcDays = request.getParameterValues("ltcDays");
			String[] ltcAmount = request.getParameterValues("ltcAmount");   
			
			Object[][] dtlObject = new Object [emp_id.length][6];
			Object[][] deleteData = new Object[emp_id.length][1];
			
			if(emp_id != null && emp_id.length > 0){
				for(int i =0;i<emp_id.length;i++){
					try {
						dtlObject[i][0] = ltcCode;
						dtlObject[i][1] = String.valueOf(emp_id[i]);
						dtlObject[i][2] = String.valueOf(ltcAmount[i]);
						dtlObject[i][3] = String.valueOf(attnDays[i]);
						dtlObject[i][4] = String.valueOf(trvlDays[i]);
						dtlObject[i][5] = String.valueOf(ltcDays[i]);
						
						deleteData[i][0] = String.valueOf(emp_id[i]);
						
					} catch (Exception e) {
						logger.error("error in creating object for saving -- "+e);
						//e.printStackTrace();
					}
				}
			}
			try{
				result = getSqlModel().singleExecute(delLTCDtl,deleteData);
				
				result = getSqlModel().singleExecute(insertDtl, dtlObject);
				
			}catch(Exception e){
				logger.error("exception in deleting and inserting credit data 4 pension", e);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return result;
		
	}
	
	public boolean unlockLTC(String ltcCode) {
		boolean result =false;
		try {
			String lockQuery = " UPDATE HRMS_LTC SET LTC_STATUS='LTC_START' WHERE LTC_CODE="+ltcCode;
			result = getSqlModel().singleExecute(lockQuery);
		}catch(Exception e) {
			logger.error("Exception in unlockLTC : "+e);
		}
		return result;
	}

}
