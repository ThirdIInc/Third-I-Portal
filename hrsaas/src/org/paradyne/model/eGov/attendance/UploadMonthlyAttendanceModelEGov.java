package org.paradyne.model.eGov.attendance;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.eGov.attendance.UploadMonthlyAttendanceEGov;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.leave.LeavePolicyData;

public class UploadMonthlyAttendanceModelEGov extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(UploadMonthlyAttendanceModelEGov.class);
private Object[][] leavePolicies = null;

public Object[][] callAttendance(String month, String year,
	String concatEmployeeIds, String salaryStartFlag, String fromDay) {
String sql = "";
String toDate = "";
String fromDate = "";

if (salaryStartFlag.equals("P")) {
	int endDate = Integer.parseInt(fromDay);
	endDate -= 1;
	toDate = endDate + "-" + month + "-" + year;
	if (month.equals("1")) {
		String dateYear = String.valueOf(Integer.parseInt(year) - 1);
		fromDate = fromDay + "-12-" + dateYear;
		sql = " SELECT ATT_EMP_ID, SUM(NVL(LATE_MARKS, 0)) AS LATE_MARKS, SUM(NVL(HALF_DAYS, 0)) AS HALF_DAYS FROM ( "
				+ " 	SELECT ATT_EMP_ID, CASE WHEN ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2 WHEN ATT_STATUS_TWO IN('LC', 'EL') "
				+ "	THEN COUNT(*) END AS LATE_MARKS, CASE WHEN ATT_STATUS_TWO = 'HD' THEN COUNT(*) END AS HALF_DAYS, "
				+ "	TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS LATE_MARK_DATE FROM HRMS_DAILY_ATTENDANCE_"
				+ (Integer.parseInt(year) - 1)
				+ "	WHERE ATT_STATUS_ONE = 'PR' AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY "
				+ "	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS IN ('A','P') AND LEAVE_DAYS = 0.5) "
				+ "	GROUP BY ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_DATE "
				+ "	UNION ALL "
				+ "	SELECT ATT_EMP_ID, CASE WHEN ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2 WHEN ATT_STATUS_TWO IN('LC', 'EL') "
				+ "	THEN COUNT(*) END LATE_MARKS, CASE WHEN ATT_STATUS_TWO = 'HD' THEN COUNT(*) END AS HALF_DAYS, "
				+ "	TO_CHAR(ATT_DATE, 'DD-MM-YYYY') AS LATE_MARK_DATE FROM HRMS_DAILY_ATTENDANCE_"
				+ year
				+ "	WHERE ATT_STATUS_ONE = 'PR' AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY "
				+ "	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS IN ('A','P') AND LEAVE_DAYS = 0.5) "
				+ "	GROUP BY ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_DATE "
				+ " ) "
				+ " WHERE TO_DATE(LATE_MARK_DATE, 'DD-MM-YYYY') BETWEEN TO_DATE('"
				+ fromDate
				+ "', 'DD-MM-YYYY') "
				+ " AND TO_DATE('"
				+ toDate
				+ "', 'DD-MM-YYYY') "
				+ Utility.getConcatenatedIds("ATT_EMP_ID",
						concatEmployeeIds) + // get employee ids
												// separated by comma
				" GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";
	} else {
		String dateMonth = String.valueOf(Integer.parseInt(month) - 1);
		String fromDateSal = fromDay + "-" + dateMonth + "-" + year;
		sql = " SELECT ATT_EMP_ID, CASE WHEN SUM(NVL(LATE_MARKS, 0)) = 0 THEN '0' "
				+ " ELSE CAST(SUM(NVL(LATE_MARKS, 0)) AS VARCHAR2(4)) END AS LATE_MARKS, "
				+ " CASE WHEN SUM(NVL(HALF_DAYS, 0)) = 0 THEN '0' ELSE CAST(SUM(NVL(HALF_DAYS, 0)) AS VARCHAR2(4)) END AS HALF_DAYS "
				+ " FROM ( "
				+ " 	SELECT ATT_EMP_ID, CASE WHEN ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2  WHEN ATT_STATUS_TWO IN('LC', 'EL') "
				+ " 	THEN COUNT(*) END AS LATE_MARKS, CASE WHEN ATT_STATUS_TWO = 'HD' THEN COUNT(*) END AS HALF_DAYS "
				+ " 	FROM HRMS_DAILY_ATTENDANCE_"
				+ year
				+ " 	WHERE ATT_DATE BETWEEN TO_DATE('"
				+ fromDateSal
				+ "', 'DD-MM-YYYY') AND TO_DATE('"
				+ toDate
				+ "', 'DD-MM-YYYY')"
				+ " 	AND ATT_STATUS_ONE = 'PR' AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY "
				+ "	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS IN ('A','P') AND LEAVE_DAYS = 0.5) "
				+ " 	GROUP BY ATT_STATUS_TWO, ATT_EMP_ID "
				+ " ) "
				+ " WHERE 1 = 1 "
				+ Utility.getConcatenatedIds("ATT_EMP_ID",
						concatEmployeeIds)
				+ " GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";
	}
} else {
	sql = " SELECT ATT_EMP_ID, CASE WHEN SUM(NVL(LATE_MARKS, 0)) = 0 THEN '0' "
			+ " ELSE CAST(SUM(NVL(LATE_MARKS, 0)) AS VARCHAR2(4)) END AS LATE_MARKS, "
			+ " CASE WHEN SUM(NVL(HALF_DAYS, 0)) = 0 THEN '0' ELSE CAST(SUM(NVL(HALF_DAYS, 0)) AS VARCHAR2(4)) END AS HALF_DAYS "
			+ " FROM ( "
			+ " 	SELECT ATT_EMP_ID, CASE WHEN ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2  WHEN ATT_STATUS_TWO IN('LC', 'EL') "
			+ " 	THEN COUNT(*) END AS LATE_MARKS, CASE WHEN ATT_STATUS_TWO = 'HD' THEN COUNT(*) END AS HALF_DAYS "
			+ "	FROM HRMS_DAILY_ATTENDANCE_"
			+ year
			+ " 	WHERE ATT_DATE BETWEEN TO_DATE('01-"
			+ month
			+ "-"
			+ year
			+ "', 'DD-MM-YYYY') AND "
			+ "	LAST_DAY(TO_DATE('01-"
			+ month
			+ "-"
			+ year
			+ "', 'DD-MM-YYYY')) AND ATT_STATUS_ONE = 'PR' "
			+ "	AND ATT_DATE NOT IN (SELECT LEAVE_FROM_DATE FROM HRMS_LEAVE_DTLHISTORY "
			+ "	WHERE HRMS_LEAVE_DTLHISTORY.EMP_ID = ATT_EMP_ID AND LEAVE_DTL_STATUS IN ('A','P') AND LEAVE_DAYS = 0.5) "
			+ " 	GROUP BY ATT_STATUS_TWO, ATT_EMP_ID "
			+ " ) "
			+ " WHERE 1 = 1 "
			+ Utility.getConcatenatedIds("ATT_EMP_ID",
					concatEmployeeIds)
			+ " GROUP BY ATT_EMP_ID ORDER BY ATT_EMP_ID ";
}
Object[][] attData = getSqlModel().getSingleResult(sql);
if (attData != null && attData.length > 0) {
	return attData;
} else {
	return null;
}
}

public Object[][] callCheckWorkFlow() {
String sqlSet = " SELECT HRMS_ATTENDANCE_CONF.CONF_DAILY_ATT_CONNECT_FLAG, HRMS_ATTENDANCE_CONF.CONF_LEAVE_CONNECT_FLAG, HRMS_ATTENDANCE_CONF.CONF_SALARY_START_FLAG,"
		+ " HRMS_ATTENDANCE_CONF.CONF_SALARY_START_DATE, CONF_UPLOAD_MONTH_ATT_FLAG,NVL(CONF_RECOVERY_FLAG,'N') FROM HRMS_ATTENDANCE_CONF,HRMS_SALARY_CONF ";
Object[][] flagObj = getSqlModel().getSingleResult(sqlSet);
return flagObj;
}

public String checkNull(String result) {
if (result == null || result.equals("null")) {
	return "";
} else {
	return result;
}
}

public void downloadCalculateFile(UploadMonthlyAttendanceEGov bean,
	HttpServletResponse response) {
// for Auto present Hashmap

String autoSql = "SELECT AUTO_PRESENT_EMP_ID, AUTO_PRESENT_LATE_MARK, AUTO_PRESENT_HALF_DAY,AUTO_PRESENT_ABSENT FROM HRMS_AUTO_PRESENT ";
Object[][] autoData = getSqlModel().getSingleResult(autoSql);

HashMap lateMarkMap = new HashMap();
HashMap halfDayMap = new HashMap();
HashMap unpaidMap = new HashMap();

if (autoData != null && autoData.length > 0) {
	for (int i = 0; i < autoData.length; i++) {
		lateMarkMap.put(String.valueOf(autoData[i][0]), String
				.valueOf(autoData[i][1]));
		halfDayMap.put(String.valueOf(autoData[i][0]), String
				.valueOf(autoData[i][2]));
		unpaidMap.put(String.valueOf(autoData[i][0]), String
				.valueOf(autoData[i][3]));
	}
}

String title = "Employee List_"
		+ Utility.month(Integer.parseInt(bean.getMonth())) + "_"
		+ bean.getYear();
org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls",
		title);
Object[][] flagObj = callCheckWorkFlow();

// get all employee according to the filters
Object[][] empObj = setEmpOffcFiletrs(bean, String
		.valueOf(flagObj[0][2]), String.valueOf(flagObj[0][3]));

String concatEmployeeIds = "";
Object[][] attData = null;
Object[][] leaveCodeData = null;
LeavePolicyData leavePolicyData = null;

if (empObj != null && empObj.length > 0) {
	for (int i = 0; i < empObj.length; i++) {
		if (i == (empObj.length - 1)) {
			concatEmployeeIds += String.valueOf(empObj[i][1]);
		} else {
			concatEmployeeIds += String.valueOf(empObj[i][1]) + ",";
		}
	}
} // end of if for canocat all empId

String attWorkFlow = "";
String leaveWorkFlow = "";
String recoveryWorkFlow = "";
int dynamicCol = 0;
if (flagObj != null && flagObj.length > 0) {
	attWorkFlow = "" + flagObj[0][0];
	leaveWorkFlow = "" + flagObj[0][1];
	recoveryWorkFlow = "" + flagObj[0][5];
	if (attWorkFlow.equals("Y")) {
		attData = callAttendance(bean.getMonth(), bean.getYear(),
				concatEmployeeIds, String.valueOf(flagObj[0][2]),
				String.valueOf(flagObj[0][3]));
	}

	if (leaveWorkFlow.equals("Y")) {
		leaveCodeData = getLeaveCode(bean.getMonth(), bean.getYear(),
				concatEmployeeIds, String.valueOf(flagObj[0][2]),
				String.valueOf(flagObj[0][3]));

		// Set leave policy information
		leavePolicyData = new LeavePolicyData(bean.getDivisionId());
		leavePolicyData.initiate(context, session);
		leavePolicyData.setLeavePolicyObject();
	}
}
if (recoveryWorkFlow.equals("Y")) {
	dynamicCol = 1;
}
Object[][] totalObj = new Object[empObj.length][7 + dynamicCol];
// for attendance late marks and half days
for (int i = 0; i < empObj.length; i++) {
	String empId = String.valueOf(empObj[i][1]);
	String empDiv = String.valueOf(empObj[i][4]);
	String empDept = String.valueOf(empObj[i][5]);
	String empDesg = String.valueOf(empObj[i][6]);
	String empBranch = String.valueOf(empObj[i][7]);
	String empType = String.valueOf(empObj[i][8]);

	totalObj[i][0] = empObj[i][0]; // employee token
	totalObj[i][1] = empObj[i][2]; // employee name
	totalObj[i][2] = empObj[i][3]; // branch name

	// for late marks and half days
	boolean attFlag = false;

	if (attWorkFlow.equals("Y")) {
		if (attData != null && attData.length > 0) {
			for (int j = 0; j < attData.length; j++) {
				if (empObj[i][1].equals(attData[j][0])) {
					totalObj[i][3] = attData[j][1]; // late Marks
					totalObj[i][4] = attData[j][2]; // Half Days
					attFlag = true;
					break;
				}
			}
		}
	} else {
		totalObj[i][3] = "0";
		totalObj[i][4] = "0";
	}
	if (attFlag == false) {
		totalObj[i][3] = "0"; // blank late Marks when not attendance
		totalObj[i][4] = "0"; // blank half days when not attendance
	}
	if (lateMarkMap.get(empId) != null
			&& lateMarkMap.get(empId).equals("Y")) { // checking the
														// Auto present
														// flag for late
														// Marks
		totalObj[i][3] = "0";
	}
	if (halfDayMap.get(empId) != null
			&& halfDayMap.get(empId).equals("Y")) { // checking the Auto
													// present flag for
													// Half Day
		totalObj[i][4] = "0";
	}

	// for paid and unpaid leave
	if (leaveWorkFlow.equals("Y")) {
		totalObj[i][5] = "0.0";
		totalObj[i][6] = "0.0";
		Object[][] leave = getLeavePaidUnpaid(concatEmployeeIds,
				leavePolicyData, leaveCodeData, empId, empDiv, empDept,
				empDesg, empBranch, empType);

		/*totalObj[i][5] = checkNull(String.valueOf(leave[0][0])).equals(
				"") ? "0" : String.valueOf(leave[0][0]);*/

			totalObj[i][5] = checkNull(String.valueOf(leave[0][0])).equals(
							"") ? "0" : String.valueOf(leave[0][0]);		
				if (unpaidMap.get(empId) != null&& unpaidMap.get(empId).equals("Y")) { 													
							totalObj[i][5] = "0";
				} else {
					totalObj[i][5] = checkNull(String.valueOf(leave[0][1]))
							.equals("") ? "0" : String.valueOf(leave[0][1]);
				}
		
		/**
		 * FOR RECOVERY
		 */
		if (recoveryWorkFlow.equals("Y")) {
			totalObj[i][5] = "0.0";
			totalObj[i][6] = "0.0";
			if (unpaidMap.get(empId) != null
					&& unpaidMap.get(empId).equals("Y")) { 														
				totalObj[i][5] = "0";
			} else {
				totalObj[i][5] = checkNull(String.valueOf(leave[0][1]))
						.equals("") ? "0" : String.valueOf(leave[0][1]);
			}
		}
		
	} else { // when work flow is false
		totalObj[i][5] = "0.0";
		totalObj[i][6] = "0.0";
	}
	if (dynamicCol > 0) {
		totalObj[i][7] = "0";
	}
}

		/**
		 * SET LEAVE APPLICATION DAYS
		 */
		HashMap<String, String>leaveApplDaysMap=new HashMap<String, String>();
		if(leaveCodeData !=null && leaveCodeData.length>0){
			for (int j = 0; j < leaveCodeData.length; j++) {
				String key=String.valueOf(leaveCodeData[j][0])+"#"+String.valueOf(leaveCodeData[j][1]);
				leaveApplDaysMap.put(key, String.valueOf(leaveCodeData[j][2]));
			}
		}
		/**
		 * GET LEAVE CREDIT CONFIGURATON
		 */
		 String leaveQuery = " SELECT LEAVE_CODE , HRMS_LEAVE.LEAVE_ABBR FROM HRMS_LEAVE_CREDIT_CONFIG LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CODE) ORDER BY HRMS_LEAVE.LEAVE_ID ";
         Object[][] leaveData = getSqlModel().getSingleResult(leaveQuery);

try {
	if (recoveryWorkFlow.equals("Y")) {
	        int count=7;
                //query is used for adding types of leave in XSL 
	       
                
                int leaveConCount=0;
                if(leaveData!=null && leaveData.length>0){
                        leaveConCount=leaveData.length; 
                }
                
                String abc[] = new String[count+leaveConCount+leaveConCount+1];         
                abc[0]="Employee Id";
                abc[1]="Employee Name";         
                abc[2]="Branch Name";
                abc[3]="Unadjusted Late Marks";
                abc[4]="Unadjusted Half Days";
               // abc[5]="Paid Leaves";
                abc[5]="Unpaid Leaves";
                abc[6]="Recovery Days";
                
                int cellwidth[]=new int[count+leaveConCount+leaveConCount+1];
                cellwidth[0]=15;
                cellwidth[1]=30;
                cellwidth[2]=30;
                cellwidth[3]=25;
                cellwidth[4]=25;
                cellwidth[5]=16;
                cellwidth[6]=20;
               // cellwidth[7]=20;              
                
                int alignment[]=new int[count+leaveConCount+leaveConCount+1];
                alignment[0]=0;
                alignment[1]=0;
                alignment[2]=0;
                alignment[3]=1;
                alignment[4]=1;
                alignment[5]=1;
                alignment[6]=1;
                //alignment[7]=1;
                
                for (int j = 0; j < leaveConCount; j++) {
                        abc[count]=String.valueOf(leaveData[j][1]); 
                        cellwidth[count]=15;
                        alignment[count]=1;
                        count++;
                        abc[count]=String.valueOf(leaveData[j][1])+"_REC"; 
                        cellwidth[count]=15;
                        alignment[count]=1;
                        count++;
                }
               /**
                * for narration
                */
                abc[count]="Narration";
                cellwidth[count]=30;
                alignment[count]=0;
                
                
                if(totalObj !=null && totalObj.length>0){                
                        Object[][]totalObjLeave=new Object[totalObj.length][(totalObj[0].length-1)+leaveConCount+leaveConCount+1];
                        for (int j = 0; j < totalObjLeave.length; j++) {
                                int cnt=totalObj[0].length-1;
                                for (int j2 = 0; j2 < totalObj[0].length-1; j2++) {
                                        totalObjLeave[j][j2]= totalObj[j][j2]; 
                                }
                                int leaveCount=0;
                                for (int j2 = 0; j2 < (leaveConCount+leaveConCount); j2++) {
                                	totalObjLeave[j][cnt]= "0"; 
                                	if(leaveApplDaysMap!=null && leaveApplDaysMap.size()>0){
                                		if(j2%2==0){
                                    		String key=String.valueOf(empObj[j][1])+"#"+String.valueOf(leaveData[leaveCount][0]);
                                    		String leaveValue=leaveApplDaysMap.get(key);
                                    		if(leaveValue!=null && leaveValue.length()>0){
                                    			totalObjLeave[j][cnt]= leaveValue; 
                                    		}
                                    		leaveCount++;
                                    	}
                                	}                                 	
                                  cnt++;
                                }
                                totalObjLeave[j][cnt]= "                   "; 
                         }
                        int colsAsDouble[] = { 3, 4, 5, 6 ,7,8,9,10,11,12};
        		rg.tableBodyAsText(abc, totalObjLeave, cellwidth, alignment,
                       			colsAsDouble);
                       }
                       else{
                              String[] head=new String[1];
                              head[0]="Data Not Present";
                              Object[][] data=new Object[1][1];
                              data[0][0]="";
                              int[]width=new int[1];
                              width[0]=100;
                              int[]align=new int[1];
                              align[0]=1;
                              int colsAsDouble[] = { 3, 4, 5, 6 ,7,8,9,10,11,12};
                               rg.tableBodyAsText(head, data, width, align,colsAsDouble);   
                       }
		
		
				
	} else {
		
		System.out.println("UNDER RECOVERY***********");
			int COUNT=6;
			int leaveCreditConf=0;
			if(leaveData!=null && leaveData.length>0){
				leaveCreditConf=leaveData.length;
			}
		
			String header[] = new String[COUNT+leaveCreditConf];         
			header[0]="Employee Id";
			header[1]="Employee Name";         
			header[2]="Branch Name";
			header[3]="Unadjusted Late Marks";
			header[4]="Unadjusted Half Days";
			header[5]="Unpaid Leaves";
			//header[6]="Unpaid Leaves";		
		
			 int cellwidth[]=new int[COUNT+leaveCreditConf];
             cellwidth[0]=15;
             cellwidth[1]=30;
             cellwidth[2]=30;
             cellwidth[3]=25;
             cellwidth[4]=25;
             cellwidth[5]=16;
            // cellwidth[6]=20;
             
             int alignment[]=new int[COUNT+leaveCreditConf];
             alignment[0]=0;
             alignment[1]=0;
             alignment[2]=0;
             alignment[3]=1;
             alignment[4]=1;
             alignment[5]=1;
           //  alignment[6]=1;		
             int colsAsDouble[] = { 3, 4, 5, 6,7,8,9,10,11,12,13 };
             /**
              * SET LEAVE CONFIGURATION HEADER
              */
             for (int j = 0; j < leaveCreditConf; j++) {
            	 header[COUNT+j]=String.valueOf(leaveData[j][1]); 
                 cellwidth[COUNT+j]=15;
                 alignment[COUNT+j]=1;
             }
             
             if(totalObj !=null && totalObj.length>0){                
                 Object[][]totalObjLeave=new Object[totalObj.length][COUNT+leaveCreditConf];                
                 for (int j = 0; j < totalObjLeave.length; j++) {
                         int cnt=COUNT;
                         //SET BASIC INFORMATION
                         for (int j2 = 0; j2 < COUNT; j2++) {
                                 totalObjLeave[j][j2]= totalObj[j][j2]; 
                         }
                         
                         int leaveCount=0;
                         for (int j2 = 0; j2 < (leaveCreditConf); j2++) {
                         	totalObjLeave[j][cnt]= "0"; 
                         	if(leaveApplDaysMap!=null && leaveApplDaysMap.size()>0){
                             		String key=String.valueOf(empObj[j][1])+"#"+String.valueOf(leaveData[leaveCount][0]);
                             		String leaveValue=leaveApplDaysMap.get(key);

                             		//System.out.println("key :"+key);
                             		if(leaveValue!=null && leaveValue.length()>0){
                             			//System.out.println("RECOVERY FOR 463 :"+leaveValue);
                             			totalObjLeave[j][cnt]= leaveValue; 
                             		}
                             		leaveCount++;
                         	}                                 	
                           cnt++;
                         }
                  }
             	rg.tableBodyAsText(header, totalObjLeave, cellwidth, alignment,colsAsDouble);
                }
	
	}
	rg.createReport(response);
} catch (Exception e) {
	e.printStackTrace();
}
} // end of downloadCalculateFile

/**
* Get the filters, like branch, department, paybill, employee type,
* division, from Payroll Settings
* 
* @return Object[][] as list of filters
*/
public Object[][] getFilters() {
Object[][] attendanceFiltersObj = null;
try {
	String attendanceFiltersSql = " SELECT DECODE(CONF_BRN_FLAG, 'Y', 'true','N','false') AS BRANCH_FLAG, "
			+ " DECODE(CONF_DEPT_FLAG, 'Y', 'true', 'N', 'false') AS DEPARTMENT_FLAG, "
			+ " DECODE(CONF_PAYBILL_FLAG, 'Y', 'true', 'N', 'false') AS PAYBILL_FLAG, "
			+ " DECODE(CONF_EMPTYPE_FLAG, 'Y', 'true', 'N', 'false') AS EMPLOYEE_TYPE_FLAG, "
			+ " DECODE(CONF_DIVISION_FLAG, 'Y', 'true', 'N', 'false') AS DIVISION_FLAG FROM HRMS_SALARY_CONF ";
	attendanceFiltersObj = getSqlModel().getSingleResult(
			attendanceFiltersSql);
} catch (Exception e) {
	logger.error("Exception in getFilters:" + e);
}
return attendanceFiltersObj;
}

// getting the leave code and leave days
public Object[][] getLeaveCode(String month, String year,
	String concatEmployeeIds, String salaryStartFlag, String fromDay) {
String sqlLeave = "";
String toDate = "";
String fromDate = "";

int endDate = Integer.parseInt(fromDay);
endDate -= 1;

if (salaryStartFlag.equals("P")) {
	if (month.equals("1")) {
		String DateYear = String.valueOf(Integer.parseInt(year) - 1);
		fromDate = fromDay + "-12-" + DateYear;
	} else {
		String DateMonth = String.valueOf(Integer.parseInt(month) - 1);
		fromDate = fromDay + "-" + DateMonth + "-" + year;
	}
	toDate = endDate + "-" + month + "-" + year;

	/*sqlLeave = " SELECT HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, SUM(NVL(LEAVE_DAYS, 0)) AS LEAVE_DAYS "
			+ " FROM HRMS_LEAVE_BALANCE "
			+ " LEFT JOIN HRMS_LEAVE_DTLHISTORY ON(HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID "
			+ " AND HRMS_LEAVE_DTLHISTORY.LEAVE_CODE = HRMS_LEAVE_BALANCE.LEAVE_CODE AND LEAVE_DTL_STATUS IN ('A','P') "
			+ " AND LEAVE_FROM_DATE BETWEEN TO_DATE('"
			+ fromDate
			+ "', 'DD-MM-YYYY') "
			+ " AND TO_DATE('"
			+ toDate
			+ "', 'DD-MM-YYYY'))"
			+ " WHERE 1 = 1 "
			+ Utility.getConcatenatedIds("HRMS_LEAVE_BALANCE.EMP_ID",
					concatEmployeeIds)
			+ " GROUP BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE "
			+ " ORDER BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE ";*/


	sqlLeave="SELECT HRMS_LEAVE_DTLHISTORY.EMP_ID, HRMS_LEAVE_DTLHISTORY.LEAVE_CODE, SUM(NVL(LEAVE_DAYS, 0)) AS LEAVE_DAYS "  
		+"		FROM  HRMS_LEAVE_DTLHISTORY  "
		+"		WHERE  LEAVE_DTL_STATUS IN ('A','P')   AND " 
		+"	(LEAVE_FROM_DATE BETWEEN TO_DATE('"+fromDate+"', 'DD-MM-YYYY')  AND TO_DATE('"+toDate+"', 'DD-MM-YYYY'))  "+Utility.getConcatenatedIds("HRMS_LEAVE_DTLHISTORY.EMP_ID",concatEmployeeIds)+" " 
		+"		GROUP BY HRMS_LEAVE_DTLHISTORY.EMP_ID, HRMS_LEAVE_DTLHISTORY.LEAVE_CODE";



} else {
	sqlLeave = " SELECT HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, SUM(NVL(LEAVE_DAYS, 0)) AS LEAVE_DAYS "
			+ " FROM   HRMS_LEAVE_BALANCE "
			+ " LEFT JOIN HRMS_LEAVE_DTLHISTORY ON(HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_LEAVE_BALANCE.EMP_ID "
			+ " AND HRMS_LEAVE_DTLHISTORY.LEAVE_CODE = HRMS_LEAVE_BALANCE.LEAVE_CODE "
			+ " AND LEAVE_DTL_STATUS IN ('A','P') AND LEAVE_FROM_DATE >= TO_DATE('01-"
			+ month
			+ "-"
			+ year
			+ "', 'DD-MM-YYYY') "
			+ " AND LEAVE_TO_DATE <= LAST_DAY(TO_DATE('01-"
			+ month
			+ "-"
			+ year
			+ "', 'DD-MM-YYYY'))) "
			+ " WHERE 1 = 1 "
			+ Utility.getConcatenatedIds("HRMS_LEAVE_BALANCE.EMP_ID",
					concatEmployeeIds)
			+ " GROUP BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE "
			+ " ORDER BY HRMS_LEAVE_BALANCE.EMP_ID, HRMS_LEAVE_BALANCE.LEAVE_CODE ";
	
	
	
}
Object[][] leaveData = getSqlModel().getSingleResult(sqlLeave);
if (leaveData != null && leaveData.length > 0) {
	return leaveData;
} else {
	return null;
}
}

// leave iD as per policy
private ArrayList<String> getLeaveIdsFromPolicy(
	LeavePolicyData leavePolicyData, String empId, String empDiv,
	String empDept, String empDesg, String empBranch, String empType,
	String leaveType) {
ArrayList<String> leaveIdsFromPolicy = new ArrayList<String>();
try {
	if (leavePolicies != null && leavePolicies.length > 0) {
		// Get Leave Policy Id for an employee
		String leavePolicyId = leavePolicyData.getLeavePolicyCode(
				empId, empDiv, empDept, empDesg, empBranch, empType);

		for (int i = 0; i < leavePolicies.length; i++) {
			// leave policy id for an employee matches with policy ids
			// in leavePolicies and
			// leave type in leavePolicies matches with leaveType (P/U),
			// then get leave id
			if (leavePolicyId.equals(String
					.valueOf(leavePolicies[i][2]))
					&& String.valueOf(leavePolicies[i][1])
							.equalsIgnoreCase(leaveType)) {
				leaveIdsFromPolicy.add(String
						.valueOf(leavePolicies[i][0])); // added leave
														// code
			}
		}
	}
} catch (Exception e) {
	logger.error("Exception in getLeaveIdsFromPolicy:" + e);
}
return leaveIdsFromPolicy;
}

public Object[][] getLeavePaidUnpaid(String concatEmployeeIds,
	LeavePolicyData leavePolicyData, Object[][] leaveCodeData,
	String empId, String empDiv, String empDept, String empDesg,
	String empBranch, String empType) {
Object[][] leavePaidUnpaid = new Object[1][2];

// get Policy Id
leavePolicies = leavePolicyData.getLeavePolicyObj();
if (leaveCodeData != null && leaveCodeData.length > 0) {
	double paidLeaves = 0.0;
	double unPaidLeaves = 0.0;
	leavePaidUnpaid[0][0] = "0.0";
	leavePaidUnpaid[0][1] = "0.0";

	for (int j = 0; j < leaveCodeData.length; j++) { // leave code
														// for loop
		if (empId.equals(String.valueOf(leaveCodeData[j][0]))) { // verify
																	// employee
																	// id
			// for paid leaves calculation
			ArrayList<String> paidLeaveIds = getLeaveIdsFromPolicy(
					leavePolicyData, empId, empDiv, empDept, empDesg,
					empBranch, empType, "P");

			if (paidLeaveIds != null) {
				for (int k = 0; k < paidLeaveIds.size(); k++) {
					if (paidLeaveIds.get(k).equals(
							String.valueOf(leaveCodeData[j][1]))) {
						// set the paid leaves
						leavePaidUnpaid[0][0] = paidLeaves
								+ (Double.parseDouble(String
										.valueOf(leaveCodeData[j][2])));
						paidLeaves = Double.parseDouble(String
								.valueOf(leavePaidUnpaid[0][0]));
					}
				}
			}

			// for unpaid leaves calculation
			ArrayList<String> unPaidLeaveIds = getLeaveIdsFromPolicy(
					leavePolicyData, empId, empDiv, empDept, empDesg,
					empBranch, empType, "U");
			if (unPaidLeaveIds != null) {
				for (int m = 0; m < unPaidLeaveIds.size(); m++) {
					if (unPaidLeaveIds.get(m).equals(
							String.valueOf(leaveCodeData[j][1]))) {
						// set the unPaid leaves
						leavePaidUnpaid[0][1] = unPaidLeaves
								+ (Double.parseDouble(String
										.valueOf(leaveCodeData[j][2])));
						unPaidLeaves = Double.parseDouble(String
								.valueOf(leavePaidUnpaid[0][1]));
					}
				}
			}
		}
	} // end of leave code for loop
}
return leavePaidUnpaid;
}

/**
* insert the file data in to the database according the applied filter.
* 
* @param fileEmpObj
* @param bean
* @return
*/
public boolean saveFilterEmp(Object[][] fileEmpObj,
	UploadMonthlyAttendanceEGov bean,Object[][] leaveConfObj) {

String autoSql = "SELECT AUTO_PRESENT_EMP_ID, AUTO_PRESENT_LATE_MARK, AUTO_PRESENT_HALF_DAY,AUTO_PRESENT_ABSENT FROM HRMS_AUTO_PRESENT ";
Object[][] autoData = getSqlModel().getSingleResult(autoSql);

HashMap lateMarkMap = new HashMap();
HashMap halfDayMap = new HashMap();
HashMap unpaidMap = new HashMap();
if (autoData != null && autoData.length > 0) {
	for (int i = 0; i < autoData.length; i++) {
		lateMarkMap.put(String.valueOf(autoData[i][0]), String
				.valueOf(autoData[i][1]));
		halfDayMap.put(String.valueOf(autoData[i][0]), String
				.valueOf(autoData[i][2]));
		unpaidMap.put(String.valueOf(autoData[i][0]), String
				.valueOf(autoData[i][3]));
	}
}

Object[][] flagObj = callCheckWorkFlow();
// get all the employee from HRMS_EMP_OFFC according to the filter
// verifying the employee id is valid or not according to
// filter and calculate the length of valid employee id
Object[][] filterEmpObj = setEmpOffcFiletrs(bean, String
		.valueOf(flagObj[0][2]), String.valueOf(flagObj[0][3]));
int objLength = 0;
for (int i = 0; i < filterEmpObj.length; i++) {
	for (int j = 0; j < fileEmpObj.length; j++) {
		if (String.valueOf(filterEmpObj[i][0]).equals(
				String.valueOf(fileEmpObj[j][2]))) {
			objLength++;
			break;
		}
	}
}

// merging the data from file whose employee id is valid according to
// the applied filter
Object[][] emoObj = new Object[objLength][fileEmpObj[0].length];
int count = 0;
String duplicateEmpId = "";
for (int i = 0; i < filterEmpObj.length; i++) {
	for (int j = 0; j < fileEmpObj.length; j++) {
		if (String.valueOf(filterEmpObj[i][0]).equals(
				String.valueOf(fileEmpObj[j][2]))) {
			emoObj[count][0] = fileEmpObj[j][0];
			emoObj[count][1] = fileEmpObj[j][1];
			emoObj[count][2] = filterEmpObj[i][1];
			emoObj[count][3] = fileEmpObj[j][3];
			emoObj[count][4] = fileEmpObj[j][4];
			emoObj[count][5] = fileEmpObj[j][5];
			emoObj[count][6] = fileEmpObj[j][6];
			if (String.valueOf(flagObj[0][5]).equals("Y")) {
				emoObj[count][7] = fileEmpObj[j][7];
				emoObj[count][8] = fileEmpObj[j][8];
			}

			if (lateMarkMap.get(String.valueOf(filterEmpObj[i][1])) != null
					&& lateMarkMap.get(
							String.valueOf(filterEmpObj[i][1])).equals(
							"Y")) { // checking the Auto present flag
									// for late Marks
				emoObj[count][3] = "0";
			}
			if (halfDayMap.get(String.valueOf(filterEmpObj[i][1])) != null
					&& halfDayMap.get(
							String.valueOf(filterEmpObj[i][1])).equals(
							"Y")) { // checking the Auto present flag
									// for Half Day
				emoObj[count][4] = "0";
			}
			if (unpaidMap.get(String.valueOf(filterEmpObj[i][1])) != null
					&& unpaidMap
							.get(String.valueOf(filterEmpObj[i][1]))
							.equals("Y")) { // checking the Auto present
											// flag for Unpaid leaves
				emoObj[count][6] = "0";
			}

			if (duplicateEmpId.equals("")) {
				duplicateEmpId += "" + emoObj[count][2];
			} else {
				duplicateEmpId += "," + emoObj[count][2];
			}
			count++;
			break;
		}
	}
}

/*
 * for (int i = 0; i < emoObj.length; i++) { for (int j = 0; j <
 * emoObj[0].length; j++) {
 * logger.info("emoObj["+i+"]["+j+"]=="+emoObj[i][j]); } }
 */
// for delete the previous duplicate record from the table whose
// attendance is already uploaded.
boolean res = false;
if (!duplicateEmpId.equals("")) {
	String delSql = " DELETE FROM HRMS_UPLOAD_ATTENDANCE_"
			+ bean.getYear() + " WHERE ATTN_MONTH =" + bean.getMonth()
			+ " AND ATTN_YEAR =" + bean.getYear()
			+ Utility.getConcatenatedIds("ATTN_EMP_ID", duplicateEmpId);
	res = getSqlModel().singleExecute(delSql);
}

// insert the data of file into the table.
if (emoObj != null && emoObj.length > 0) {
	String sqlQuery = "";
	if (String.valueOf(flagObj[0][5]).equals("Y")) {
		sqlQuery = " INSERT INTO HRMS_UPLOAD_ATTENDANCE_"
				+ bean.getYear()
				+ " (ATTN_MONTH, ATTN_YEAR , ATTN_EMP_ID, "
				+ " ATTN_LATE_MARKS, ATTN_HALF_DAYS, ATTN_PAID_LEAVES, ATTN_UNPAID_LEAVES,ATTN_RECOVERY_DAYS,IS_REUPLOAD_FLAG,EMP_NARRATION) VALUES (?, ?, ?, ?, ?, ?, ?, ?,'Y',?) ";
	} else {
		sqlQuery = " INSERT INTO HRMS_UPLOAD_ATTENDANCE_"
				+ bean.getYear()
				+ " (ATTN_MONTH, ATTN_YEAR , ATTN_EMP_ID, "
				+ " ATTN_LATE_MARKS, ATTN_HALF_DAYS, ATTN_PAID_LEAVES, ATTN_UNPAID_LEAVES,IS_REUPLOAD_FLAG) VALUES (?, ?, ?, ?, ?, ?, ?,'Y') ";
	}
	res = getSqlModel().singleExecute(sqlQuery, emoObj);
}
                  //DELETE AND INSERT DATA  HRMS_UPLOAD_ATTN_DTL_     
                  if(leaveConfObj!=null && leaveConfObj.length>0){
                       
                  //DELETE DATA FROM  HRMS_UPLOAD_ATTN_DTL_       
                    if (!duplicateEmpId.equals("")) {
                    String deleteDtlQuery="DELETE FROM HRMS_UPLOAD_ATTN_DTL_"+bean.getYear()+" WHERE ATTN_DTL_MONTH="+bean.getMonth()+" AND ATTN_DTL_YEAR="+bean.getYear()+"  "+Utility.getConcatenatedIds("ATTN_DTL_EMP_ID", duplicateEmpId)+" ";
                  getSqlModel().singleExecute(deleteDtlQuery); 
                    }
                  //INSERT DATA FROM  HRMS_UPLOAD_ATTN_DTL_        
                   String insertDtlQuery="INSERT INTO HRMS_UPLOAD_ATTN_DTL_"+bean.getYear()+"(ATTN_DTL_MONTH, ATTN_DTL_YEAR, ATTN_DTL_EMP_ID, ATTN_DTL_LEAVE_ID, ATTN_DTL_LEAVE_VALUE, ATTN_DTL_LEAVE_REC_VALUE) VALUES(?,?,(SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_TOKEN=?),?,?,?)";
                  getSqlModel().singleExecute(insertDtlQuery,leaveConfObj);
                  }

return res;
}

/**
* Set filters to records which are coming from Employee Office
* 
* @param bean
* @param sqlQuery
* @return String as sql query
*/
public Object[][] setEmpOffcFiletrs(UploadMonthlyAttendanceEGov bean,
	String salaryStartFlag, String fromDay) {
try {
	String typeCode = bean.getEmployeeTypeId();
	String payBillNo = bean.getPayBillId();
	String brnCode = bean.getBranchId();
	String deptCode = bean.getDepartmentId();
	String divCode = bean.getDivisionId();
	String empSearchId = bean.getEmpSerachId();

	String sqlQuery = " SELECT EMP_TOKEN, EMP_ID, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, "
			+ " CENTER_NAME, EMP_DIV, EMP_DEPT, EMP_RANK, EMP_CENTER, EMP_TYPE FROM HRMS_EMP_OFFC "
			+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER) "
			+ " WHERE EMP_STATUS = 'S' ";

	if (salaryStartFlag.equals("P")) {
		String date = "";
		String month = "";
		String Year = "";
		/*if (bean.getMonth().equals("1")) {
			month = "12";
			Year = String.valueOf(Integer.parseInt(bean.getYear()) - 1);
			date = fromDay + "-" + month + "-" + Year;
		} else {
			month = bean.getMonth();
			Year = bean.getYear();
			date = fromDay + "-" + month + "-" + Year;
		}*/
		month = bean.getMonth();
		Year = bean.getYear();
		date = fromDay + "-" + month + "-" + Year;

		sqlQuery += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
				+ "','DD-MM-YYYY')";
	} else {
		sqlQuery += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
				+ bean.getMonth() + "-" + bean.getYear()
				+ "', 'DD-MM-YYYY'))";
	}
	if (!typeCode.equals("")) {
		sqlQuery += " AND EMP_TYPE = " + typeCode;
	}
	if (!payBillNo.equals("")) {
		sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
	}
	if (!brnCode.equals("")) {
		sqlQuery += " AND EMP_CENTER = " + brnCode;
	}
	if (!deptCode.equals("")) {
		sqlQuery += " AND EMP_DEPT = " + deptCode;
	}
	if (!divCode.equals("")) {
		sqlQuery += " AND EMP_DIV = " + divCode;
	}
	if (!empSearchId.equals("")) {
		sqlQuery += " AND EMP_ID = " + empSearchId;
	}

	sqlQuery += " ORDER BY UPPER(CENTER_NAME), UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), UPPER(EMP_TOKEN) ";
	return getSqlModel().getSingleResult(sqlQuery);
} catch (Exception e) {
	logger.error("Exception in setEmpOffcFiletrs:" + e);
	return null;
} // end of try-catch block
} // end of setEmpOffcFiletrs

public String uploadMonthlySheet(UploadMonthlyAttendanceEGov bean,
	String recDaysWorkFlow) {
String filePath = context.getRealPath("/") + "pages/images/"
		+ session.getAttribute("session_pool") + "/attendance/"
		+ bean.getUploadFileName();
String fileExtension = filePath.substring(filePath.length() - 3,
		filePath.length());

// for wrong format
if (!(fileExtension.equalsIgnoreCase("xls"))) {
	return "notValidFormat";
}

HSSFWorkbook wb = null;
try {
	InputStream myXls = new FileInputStream(filePath);
	wb = new HSSFWorkbook(myXls);
} catch (Exception e) {
	logger.error(e);
}

try {
	Vector vect1 = new Vector();
	Vector leaveConfVector = new Vector();
	HSSFSheet sheet1 = wb.getSheetAt(0);

	// for no data in file
	if (!(sheet1.getLastRowNum() > 0)) {
		return "noDataInfile";
	}

	 String leaveQuery = " SELECT LEAVE_CODE , HRMS_LEAVE.LEAVE_ABBR FROM HRMS_LEAVE_CREDIT_CONFIG INNER JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CODE) ORDER BY HRMS_LEAVE.LEAVE_ID ";
         Object[][] leaveConfData = getSqlModel().getSingleResult(leaveQuery);
	int leaveConfCounter=0;
	int NORECOVERYCOUNR=0;
	if(leaveConfData !=null && leaveConfData.length>0){
	        leaveConfCounter=leaveConfData.length*2;
	        NORECOVERYCOUNR=leaveConfData.length;
	}
         
         
	String employeeError = "";
	 HashMap<String, String>narrationMap=new HashMap<String, String>();
	boolean errorFlag = false;
	for (int j = 1; j <= sheet1.getLastRowNum(); j++) {
		String localEmpToken = "";
		String EmpTokenName = "";
		HSSFRow row = sheet1.getRow(j);
		HSSFCell empToken = row.getCell((short) 0);//EMPLOYEE ID
		HSSFCell latemark = row.getCell((short) 3);//UNADJUSTEDLATE MARKS
		HSSFCell halfday = row.getCell((short) 4);//UNADJUSTED HALF DAY
		HSSFCell paidLeave = row.getCell((short) 5);
		HSSFCell unpaidLeave = row.getCell((short) 5);//UNPAID LEAVE
		HSSFCell recoveryDays = row.getCell((short) 6);//RECOVERY DAYS
		
		/**
		 * FOR UNPAID RECOVERY CALCULATION
		 */
		if (!recDaysWorkFlow.equals("Y")) {
			unpaidLeave = row.getCell((short) 5);//UNPAID LEAVE
		}
		
		// if emp token is valid and other is null then added the value
		// in vector.
		//System.out.println("EmpTokenName11 :"+EmpTokenName);
		try {
			if (empToken != null) {
				//System.out.println("EmpTokenName22 :"+EmpTokenName);
				if (empToken.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					vect1.add(String.valueOf((int) empToken
							.getNumericCellValue()));
					EmpTokenName = String.valueOf((int) empToken
							.getNumericCellValue());
					//System.out.println("EmpTokenName :"+EmpTokenName);
				} else if (empToken.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					vect1.add(String.valueOf(empToken
							.getStringCellValue().replaceAll("'", "")));
					EmpTokenName = String.valueOf(empToken
							.getStringCellValue().replaceAll("'", ""));
					//System.out.println("EmpTokenName33 :"+EmpTokenName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vect1.add("");
		}// end of if

		// for lateMarks
		try {
			if (latemark != null) {
				String lateMarkVal = "";
				double lateMarkDig = 0.0;
				if (latemark.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					lateMarkVal = String.valueOf(latemark
							.getNumericCellValue());
				} else if (latemark.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					lateMarkVal = String.valueOf(latemark
							.getStringCellValue().replaceAll("'", "")
							.trim());
				}
				if (!lateMarkVal.equals("")) {
					lateMarkDig = Double.parseDouble(lateMarkVal);
					if (lateMarkDig >= 0) {
						vect1.add(lateMarkDig);
					} else {
						vect1.add("0");
						localEmpToken = EmpTokenName;
						errorFlag = true;
					}
				} else {
					vect1.add("0");
				}
			} else {
				vect1.add("0");
			}
		} catch (Exception e) {
			vect1.add("0");
			errorFlag = true;
			localEmpToken = EmpTokenName;
		}

		// for halfDays
		try {
			if (halfday != null) {
				String halfdayVal = "";
				double halfdayDig = 0.0;
				if (halfday.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					halfdayVal = String.valueOf(halfday
							.getNumericCellValue());
				} else if (halfday.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					halfdayVal = String.valueOf(halfday
							.getStringCellValue().replaceAll("'", "")
							.trim());
				}
				if (!halfdayVal.equals("")) {
					halfdayDig = Double.parseDouble(halfdayVal);
					if (halfdayDig >= 0) {
						vect1.add(halfdayDig);
					} else {
						vect1.add("0");
						localEmpToken = EmpTokenName;
						errorFlag = true;
					}
				} else {
					vect1.add("0");
				}
			} else {
				vect1.add("0");
			}
		} catch (Exception e) {
			vect1.add("0");
			errorFlag = true;
			localEmpToken = EmpTokenName;
		}

		// for paidLevaes
		try {
			if (paidLeave != null) {
				String paidLeaveVal = "0";
				double paidLeaveDig = 0.0;
				
				/*if (!recDaysWorkFlow.equals("Y")) {
					if (paidLeave.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						paidLeaveVal = String.valueOf(paidLeave
								.getNumericCellValue());
					} else if (paidLeave.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						paidLeaveVal = String.valueOf(paidLeave
								.getStringCellValue().replaceAll("'", "")
								.trim());
					}
				}*/
				
				if (!paidLeaveVal.equals("")) {
					paidLeaveDig = Double.parseDouble(paidLeaveVal);
					if (paidLeaveDig >= 0) {
						vect1.add(paidLeaveDig);
					} else {
						vect1.add("0");
						localEmpToken = EmpTokenName;
						errorFlag = true;
					}
				} else {
					vect1.add("0");
				}
			} else {
				vect1.add("0");
			} // end of if
		} catch (Exception e) {
			vect1.add("0");
			errorFlag = true;
			localEmpToken = EmpTokenName;
		}
		// for unpaidLevaes
		try {
			if (unpaidLeave != null) {
				String unpaidLeaveVal = "";
				double unpaidLeaveDig = 0.0;
				if (unpaidLeave.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					unpaidLeaveVal = String.valueOf(unpaidLeave
							.getNumericCellValue());
				} else if (paidLeave.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					unpaidLeaveVal = String.valueOf(unpaidLeave
							.getStringCellValue().replaceAll("'", "")
							.trim());
				}
				if (!unpaidLeaveVal.equals("")) {
					unpaidLeaveDig = Double.parseDouble(unpaidLeaveVal);
					if (unpaidLeaveDig >= 0) {
						vect1.add(unpaidLeaveDig);
					} else {
						vect1.add("0");
						localEmpToken = EmpTokenName;
						errorFlag = true;
					}
				} else {
					vect1.add("0");
				}
			} else {
				vect1.add("0");
			}
		} catch (Exception e) {
			vect1.add("0");
			errorFlag = true;
			localEmpToken = EmpTokenName;
		}

		// for recoveryDays
		if (recDaysWorkFlow.equals("Y")) {
			try {
				if (recoveryDays != null) {
					String recDayVal = "";
					double recDayDig = 0.0;
					if (recoveryDays.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						recDayVal = String.valueOf(recoveryDays
								.getNumericCellValue());
					} else if (recoveryDays.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						recDayVal = String.valueOf(recoveryDays
								.getStringCellValue().replaceAll("'",
										"").trim());
					}
					if (!recDayVal.equals("")) {
						recDayDig = Double.parseDouble(recDayVal);
						if (recDayDig >= 0) {
							vect1.add(recDayDig);
						} else {
							vect1.add("0");
							localEmpToken = EmpTokenName;
							errorFlag = true;
						}
					} else {
						vect1.add("0");
					}
				} else {
					vect1.add("0");
				}
			} catch (Exception e) {
				vect1.add("0");
				errorFlag = true;
				localEmpToken = EmpTokenName;
			}
			
			//==================================================
			/**
			 * GET DATA FOR LEAVE CREDIT CONFIGURATION
			 */
			int leaveConfCount=7;
			
			
			 
			  HSSFCell narration = row.getCell((short) (leaveConfCount+leaveConfCounter));//NARRATION
			  String narrationEmpID="";
			  if (empToken != null) {
                  if (empToken.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                	  narrationEmpID=String.valueOf((int) empToken
                                          .getNumericCellValue());                          
                  } else if (empToken.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                	  narrationEmpID=String.valueOf(empToken
                                          .getStringCellValue().replaceAll("'", ""));                    
                  }
			  }
			  
			  
			  if (narration != null) {
	                  if (narration.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
	                	  narrationMap.put(narrationEmpID,String.valueOf((int) narration
	                                          .getNumericCellValue()));
	                  } else if (narration.getCellType() == HSSFCell.CELL_TYPE_STRING) {
	                	  narrationMap.put(narrationEmpID,String.valueOf(narration
	                                          .getStringCellValue().replaceAll("'", "")));
	                  }
			  }
			
			  
			  
			  
			
			if(leaveConfData !=null && leaveConfData.length>0){
			       
			      
			        
			        int cnt=0;
			        for (int i = 0; i < leaveConfCounter; i++) {
			                
			               
			                
			                if(i%2==0){
			                        //SET EMPLOYEE TOKEN
		                                try {
		                                        if (empToken != null) {
		                                                if (empToken.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
		                                                        leaveConfVector.add(String.valueOf((int) empToken
		                                                                        .getNumericCellValue()));
		                                                        EmpTokenName = String.valueOf((int) empToken
		                                                                        .getNumericCellValue());
		                                                } else if (empToken.getCellType() == HSSFCell.CELL_TYPE_STRING) {
		                                                        leaveConfVector.add(String.valueOf(empToken
		                                                                        .getStringCellValue().replaceAll("'", "")));
		                                                        EmpTokenName = String.valueOf(empToken
		                                                                        .getStringCellValue().replaceAll("'", ""));
		                                                }
		                                        }
		                                } catch (Exception e) {
		                                        leaveConfVector.add("");
		                                }// end of if
		                                
		                                //SET LEAVE CODE
			                        leaveConfVector.add(String.valueOf(leaveConfData[cnt][0]));  
			                        cnt++;
			                }
			                
			                
			                HSSFCell leaveConfValue = row.getCell((short) leaveConfCount);//RECOVERY DAYS 
			                try {
		                                if (leaveConfValue != null) {
		                                        String leaveConfVal = "";
		                                        double leaveConfDayDig = 0.0;
		                                        if (leaveConfValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
		                                                leaveConfVal = String.valueOf(leaveConfValue
		                                                                .getNumericCellValue());
		                                        } else if (leaveConfValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
		                                                leaveConfVal = String.valueOf(leaveConfValue
		                                                                .getStringCellValue().replaceAll("'",
		                                                                                "").trim());
		                                        }
		                                        if (!leaveConfVal.equals("")) {
		                                                leaveConfDayDig = Double.parseDouble(leaveConfVal);
		                                                if (leaveConfDayDig >= 0) {
		                                                        leaveConfVector.add(leaveConfDayDig);
		                                                } else {
		                                                        leaveConfVector.add("0");
		                                                        localEmpToken = EmpTokenName;
		                                                        errorFlag = true;
		                                                }
		                                        } else {
		                                                leaveConfVector.add("0");
		                                        }
		                                } else {
		                                        leaveConfVector.add("0");
		                                }
		                        } catch (Exception e) {
		                                leaveConfVector.add("0");
		                                errorFlag = true;
		                                localEmpToken = EmpTokenName;
		                        }
		                        leaveConfCount++;
	                        }
			}
			
			//==================================================
			
		}
		else{
			/**
			 * UNRECOVERY CALCULATION
			 */
			//*****************************************
			/**
			 * GET DATA FOR NON RECOVERY
			 */
			int leaveConfCount=6;
			if(leaveConfData !=null && leaveConfData.length>0){			        
			    for (int i = 0; i < NORECOVERYCOUNR; i++) {			               
			                        //SET EMPLOYEE TOKEN
		                                try {
		                                        if (empToken != null) {
		                                                if (empToken.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
		                                                        leaveConfVector.add(String.valueOf((int) empToken
		                                                                        .getNumericCellValue()));
		                                                        EmpTokenName = String.valueOf((int) empToken
		                                                                        .getNumericCellValue());
		                                                } else if (empToken.getCellType() == HSSFCell.CELL_TYPE_STRING) {
		                                                        leaveConfVector.add(String.valueOf(empToken
		                                                                        .getStringCellValue().replaceAll("'", "")));
		                                                        EmpTokenName = String.valueOf(empToken
		                                                                        .getStringCellValue().replaceAll("'", ""));
		                                                }
		                                        }
		                                } catch (Exception e) {
		                                        leaveConfVector.add("");
		                                }// end of if		                                
		                                //SET LEAVE CODE
			                        leaveConfVector.add(String.valueOf(leaveConfData[i][0]));  
			                        
			                HSSFCell leaveConfValue = row.getCell((short) leaveConfCount);//LEAVE APPL DAY 
			                try {
		                                if (leaveConfValue != null) {
		                                        String leaveConfVal = "";
		                                        double leaveConfDayDig = 0.0;
		                                        if (leaveConfValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
		                                                leaveConfVal = String.valueOf(leaveConfValue
		                                                                .getNumericCellValue());
		                                        } else if (leaveConfValue.getCellType() == HSSFCell.CELL_TYPE_STRING) {
		                                                leaveConfVal = String.valueOf(leaveConfValue
		                                                                .getStringCellValue().replaceAll("'",
		                                                                                "").trim());
		                                        }
		                                        if (!leaveConfVal.equals("")) {
		                                                leaveConfDayDig = Double.parseDouble(leaveConfVal);
		                                                if (leaveConfDayDig >= 0) {
		                                                        leaveConfVector.add(leaveConfDayDig);
		                                                } else {
		                                                        leaveConfVector.add("0");
		                                                        localEmpToken = EmpTokenName;
		                                                        errorFlag = true;
		                                                }
		                                        } else {
		                                                leaveConfVector.add("0");
		                                        }
		                                } else {
		                                        leaveConfVector.add("0");
		                                }
		                        } catch (Exception e) {
		                                leaveConfVector.add("0");
		                                errorFlag = true;
		                                localEmpToken = EmpTokenName;
		                        }
		                        //SET RECOVERY VALUES ZERO 
		                        leaveConfVector.add("0");
		                        leaveConfCount++;
	                        }
			}
			
			
			//*****************************************8
			
		}
		
		
		
		if (!localEmpToken.equals("")) {
			employeeError += "," + localEmpToken;
		}
	}

	int count = 0;
	int leaveCount = 0;
	String[][] obj = null;
	Object[][] leaveConfObj = null;
	int leaveCounter=0;
        if(leaveConfData !=null && leaveConfData.length>0){
                leaveCounter=leaveConfData.length;  
        }
	if (recDaysWorkFlow.equals("Y")&& leaveConfCounter>0) {
	        leaveConfObj=new Object[(leaveConfVector.size()/((leaveCounter*2)+leaveConfCounter))*leaveCounter][6];    
	}
	else{
		 leaveConfObj=new Object[(leaveConfVector.size()/((leaveCounter*2)+leaveConfCounter))*leaveCounter][6];
	}
	if(leaveConfObj!=null&& leaveConfObj.length>0){
	        int k=0;
	        for (int i = 0; i < leaveConfVector.size()/((leaveCounter*2)+leaveConfCounter); i++) {
	                for (int j = 0; j < leaveCounter; j++) {
	                        leaveConfObj[k][0] = bean.getMonth();//MONTH
	                        leaveConfObj[k][1] = bean.getYear(); //YEAR
	                        leaveConfObj[k][2] =(String.valueOf(leaveConfVector.get(leaveCount++)));//EMP ID
	                        leaveConfObj[k][3] =(String.valueOf(leaveConfVector.get(leaveCount++)));//LEAVE ID
	                        leaveConfObj[k][4] =(String.valueOf(leaveConfVector.get(leaveCount++)));//LEAVE VALUE
	                        leaveConfObj[k][5] =(String.valueOf(leaveConfVector.get(leaveCount++)));//LEAVE REC VALUE  
	                        
	                       
	                        /**
	                         * CHECK FOR ZERO APPLICABLE LEAVE
	                         */
	                        
	                        k++;
                        }
                }
	}
	
	int COUNTER=0;
	if (recDaysWorkFlow.equals("Y")) {
		obj = new String[vect1.size() / 6][9];	
		COUNTER=6;
	} else {
		obj = new String[vect1.size() / 5][7];
		COUNTER=5;
	}
	for (int i = 0; i < vect1.size() / COUNTER; i++) {
		obj[i][0] = bean.getMonth();
		obj[i][1] = bean.getYear();
		obj[i][2] = (String.valueOf(vect1.get(count++)));
		obj[i][3] = (String.valueOf(vect1.get(count++)));
		obj[i][4] = (String.valueOf(vect1.get(count++)));
		obj[i][5] = (String.valueOf(vect1.get(count++)));
		obj[i][6] = (String.valueOf(vect1.get(count++)));
		if (recDaysWorkFlow.equals("Y")) {
			obj[i][7] = (String.valueOf(vect1.get(count++)));
			obj[i][8] ="";
			 if(narrationMap!=null && narrationMap.size()>0){
             	String narrationValue=narrationMap.get(String.valueOf(obj[i][2]));
             	obj[i][8] =narrationValue;
             }
		}
		//System.out.println("obj[i][2]:"+obj[i][2]);
	}
	boolean res = saveFilterEmp(obj, bean,leaveConfObj);
	if (res && errorFlag == false) {
		return "success";
	}
	if (res && errorFlag) {
		return "successWithError" + employeeError;
	}
} catch (Exception e) {
	logger.error("Exception in uploadMonthlySheet:" + e);
	e.printStackTrace();
}
return "notSuccess";
}

/**
 * METHOD TO GENERATE REPORT
 * @param bean
 * @param response
 */
public void report(UploadMonthlyAttendanceEGov bean,
		HttpServletResponse response) {
	
	String typeCode = bean.getEmployeeTypeId();
	String payBillNo = bean.getPayBillId();
	String brnCode = bean.getBranchId();
	String deptCode = bean.getDepartmentId();
	String divCode = bean.getDivisionId();
	String empSearchId = bean.getEmpSerachId();
	String month=bean.getMonth();
	String year=bean.getYear();
	String recoveryWorkFlow = "";
	String salaryStartFlag = "";
	String fromDay= "";
	String reportType = "Xls"; // report type
	String reportName = "Monthly Upload Attendance Report";
	ReportGenerator rg = new ReportGenerator(reportType, reportName + "_" + Utility.month(Integer.parseInt(month)) + "_" + year, "A4");
	
	Object[][] flagObj = callCheckWorkFlow();
	if(flagObj!=null && flagObj.length>0){
		recoveryWorkFlow=String.valueOf(flagObj[0][5]);
		salaryStartFlag=String.valueOf(flagObj[0][2]);
		fromDay=String.valueOf(flagObj[0][3]);
	}
	
	
			String sqlQuery = " SELECT  EMP_ID,EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,  CENTER_NAME " 
							+"	,ATTN_LATE_MARKS, ATTN_HALF_DAYS,ATTN_UNPAID_LEAVES,NVL(ATTN_RECOVERY_DAYS,0) "
							+"	FROM  	HRMS_UPLOAD_ATTENDANCE_"+year+" "
							+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_UPLOAD_ATTENDANCE_"+year+".ATTN_EMP_ID=HRMS_EMP_OFFC.EMP_ID " 
							+"	AND ATTN_MONTH="+month+" AND ATTN_YEAR="+year+" ) "
							+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER) "  
							+"	WHERE EMP_STATUS = 'S'  AND  EMP_DIV = "+divCode+"";
			if (salaryStartFlag.equals("P")) {
				String date = "";				
				String Year = "";			
				month = bean.getMonth();
				Year = bean.getYear();
				date = fromDay + "-" + month + "-" + Year;

				sqlQuery += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
						+ "','DD-MM-YYYY')";
			} else {
				sqlQuery += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
						+ bean.getMonth() + "-" + bean.getYear()
						+ "', 'DD-MM-YYYY'))";
			}
			
			if (!typeCode.equals("")) {
				sqlQuery += " AND EMP_TYPE = " + typeCode;
			}
			if (!payBillNo.equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
			}
			if (!brnCode.equals("")) {
				sqlQuery += " AND EMP_CENTER = " + brnCode;
			}
			if (!deptCode.equals("")) {
				sqlQuery += " AND EMP_DEPT = " + deptCode;
			}
			if (!divCode.equals("")) {
				sqlQuery += " AND EMP_DIV = " + divCode;
			}
			if (!empSearchId.equals("")) {
				sqlQuery += " AND EMP_ID = " + empSearchId;
			}

			sqlQuery += " ORDER BY UPPER(CENTER_NAME), UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), UPPER(EMP_TOKEN) ";
		Object[][]empList=getSqlModel().getSingleResult(sqlQuery);
		if(empList!=null && empList.length>0){
			 String leaveQuery = " SELECT LEAVE_CODE , HRMS_LEAVE.LEAVE_ABBR FROM HRMS_LEAVE_CREDIT_CONFIG INNER JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CODE) ORDER BY HRMS_LEAVE.LEAVE_ID ";
	         Object[][] leaveConfData = getSqlModel().getSingleResult(leaveQuery);
	         int leaveConfCounter=0;
	         int COUNT=6;
			if(leaveConfData !=null && leaveConfData.length>0){		      
				leaveConfCounter=leaveConfData.length;
				if(recoveryWorkFlow.equals("Y")){
					leaveConfCounter=leaveConfData.length*2;
					COUNT=7;
				}
			}
			/**
			 * GET LEAVE APLLICATION DAYS DETAILS
			 */
			String leaveDtlQuery="SELECT ATTN_DTL_EMP_ID,ATTN_DTL_LEAVE_ID,ATTN_DTL_LEAVE_VALUE,NVL(ATTN_DTL_LEAVE_REC_VALUE,0), "   
								+"	ATTN_DTL_EMP_ID||'#'||ATTN_DTL_LEAVE_ID "
								+"	FROM HRMS_UPLOAD_ATTN_DTL_"+year+"	"
								+"	WHERE ATTN_DTL_MONTH="+month+" AND  ATTN_DTL_YEAR="+year+"";
			HashMap<String, Object[][]>leaveDtlMap=getSqlModel().getSingleResultMap(leaveDtlQuery, 4, 2);
			
			//6=empid+empname+branch+late+half+unpaidleave
			Object[][]data=new Object[empList.length][COUNT+leaveConfCounter];
			String[] allCols = new String[COUNT+leaveConfCounter];
			int[] cellWidth = new int[COUNT+leaveConfCounter];	
			int[] cellAln = new int[COUNT+leaveConfCounter];
			allCols[0]="Employee Id";
			allCols[1]="Employee Name";
			allCols[2]="Branch Name";
			allCols[3]="Unadjusted Late Marks";
			allCols[4]="Unadjusted Half Days";
			allCols[5]="Unpaid Leaves";
			
			cellWidth[0] = 15;
			cellWidth[1] = 30;
			cellWidth[2] = 30;
			cellWidth[3] = 30;			
			cellWidth[4] = 15;
			cellWidth[5] = 15;
			
			cellAln[0] = 1;
			cellAln[1] = 1;
			cellAln[2] = 0;
			cellAln[3] = 0;	
			cellAln[4] = 0;	
			cellAln[5] = 0;	
			
			if(recoveryWorkFlow.equals("Y")){
				allCols[6]="Recovery Days";
				cellWidth[6] = 15;
				cellAln[6] = 0;	
			}
			
			for (int i = 0; i < data.length; i++) {
				data[i][0]=empList[i][1];//EMPTOKEN
				data[i][1]=empList[i][2];//EMPNAME
				data[i][2]=empList[i][3];//CENTER
				data[i][3]=empList[i][4];//UNADJ LATE MARK
				data[i][4]=empList[i][5];//UNADJ HALF MARK
				data[i][5]=empList[i][6];//UNPAID
				if(recoveryWorkFlow.equals("Y")){
					data[i][6]=empList[i][7];//UNPAID
				}
				int cnt=COUNT;
				//INITIATE ZERO VALUES	
				int leaveCnt=0;
				for (int k = 0; k < leaveConfCounter; k++) {
					if(recoveryWorkFlow.equals("Y")){
						if((k%2==0)&&k>1){
							leaveCnt++;
						}
						//System.out.println("leaveCnt :"+leaveCnt);
						String key=String.valueOf(empList[i][0])+"#"+String.valueOf(leaveConfData[leaveCnt][0]);
						data[i][cnt+k]="0";
						allCols[cnt+k]=String.valueOf(leaveConfData[leaveCnt][1]);//LEAVE ABR
						if(!(k%2==0)){
							allCols[cnt+k]=String.valueOf(leaveConfData[leaveCnt][1])+" REC";//LEAVE ABR	
						}						
						cellWidth[cnt+k]=15;
						cellAln[cnt+k] = 2;	
						if(leaveDtlMap !=null && leaveDtlMap.size()>0){
							Object[][]attObj=leaveDtlMap.get(key);
							if(attObj!=null && attObj.length>0){
								data[i][cnt+k]=attObj[0][2];//LEAVE DAYS
								if(!(k%2==0)){
									data[i][cnt+k]=String.valueOf(attObj[0][3]);//RECOVERY DAYS
								}
							}
							
						}
						
					}
					else{
						String key=String.valueOf(empList[i][0])+"#"+String.valueOf(leaveConfData[k][0]);
						data[i][cnt+k]="0";
						allCols[cnt+k]=String.valueOf(leaveConfData[k][1]);//LEAVE ABR
						cellWidth[cnt+k]=15;
						cellAln[cnt+k] = 2;	
						if(leaveDtlMap !=null && leaveDtlMap.size()>0){
							Object[][]attObj=leaveDtlMap.get(key);
							if(attObj!=null && attObj.length>0){
								data[i][cnt+k]=attObj[0][2];
							}
						}	
					}					
				}				
			}//END MAIN FOR LOOP
			
			
			
			if(reportType.equals("Xls")) {
				rg.xlsTableBody(allCols, data, cellWidth, cellAln);
			}
			
			
		}//END OF EMP LIST IF
		else{
			rg.addTextBold("There is no data to display.", 0, 1, 0);
		}
		
		rg.createReport(response);// Creates a report
			
}

public void showStatistics(UploadMonthlyAttendanceEGov bean) {
	Object[][] flagObj = callCheckWorkFlow();
	// get all employee according to the filters
	Object[][] empObj = setEmpOffcFiletrs(bean, String
			.valueOf(flagObj[0][2]), String.valueOf(flagObj[0][3]));
	
	/**
	 * GET BRANCH LIST
	 */
	
	String salaryStartFlag =String.valueOf(flagObj[0][2]);
	String fromDay=String.valueOf(flagObj[0][3]);
	String typeCode = bean.getEmployeeTypeId();
	String payBillNo = bean.getPayBillId();
	String brnCode = bean.getBranchId();
	String deptCode = bean.getDepartmentId();
	String divCode = bean.getDivisionId();
	String empSearchId = bean.getEmpSerachId();
	
	String attMonth=bean.getMonth();
	String attYear=bean.getYear();
	
	
	String sqlQuery = " SELECT DISTINCT EMP_CENTER,  CENTER_NAME FROM HRMS_EMP_OFFC "
			+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER) "
			+ " WHERE EMP_STATUS = 'S' ";

	if (salaryStartFlag.equals("P")) {
		String date = "";
		String month = "";
		String Year = "";
		month = bean.getMonth();
		Year = bean.getYear();
		date = fromDay + "-" + month + "-" + Year;
		sqlQuery += " AND EMP_REGULAR_DATE <= TO_DATE('" + date
				+ "','DD-MM-YYYY')";
	} else {
		sqlQuery += " AND EMP_REGULAR_DATE <= LAST_DAY(TO_DATE('01-"
				+ bean.getMonth() + "-" + bean.getYear()
				+ "', 'DD-MM-YYYY'))";
	}
	if (!typeCode.equals("")) {
		sqlQuery += " AND EMP_TYPE = " + typeCode;
	}
	if (!payBillNo.equals("")) {
		sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
	}
	if (!brnCode.equals("")) {
		sqlQuery += " AND EMP_CENTER = " + brnCode;
	}
	if (!deptCode.equals("")) {
		sqlQuery += " AND EMP_DEPT = " + deptCode;
	}
	if (!divCode.equals("")) {
		sqlQuery += " AND EMP_DIV = " + divCode;
	}
	if (!empSearchId.equals("")) {
		sqlQuery += " AND EMP_ID = " + empSearchId;
	}

	Object[][]obj=getSqlModel().getSingleResult(sqlQuery);
	
	
	String countQuery="SELECT EMP_CENTER,  CENTER_NAME,COUNT(*) "
					  +"		FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"	"
					  +"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_2011.ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "  
					  +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)"
					  +"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+" ";
	countQuery+=getFilterQuery(bean);
	countQuery+=" GROUP BY EMP_CENTER ,CENTER_NAME";
	HashMap<String, Object[][]>statisticMap=getSqlModel().getSingleResultMap(countQuery, 0, 2);
	
	String onHoldQuery="SELECT EMP_CENTER,  CENTER_NAME,COUNT(*) "
		  +"		FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"	"
		  +"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_UPLOAD_ATTENDANCE_2011.ATTN_EMP_ID = HRMS_EMP_OFFC. EMP_ID) "  
		  +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)"
		  +"	WHERE ATTN_MONTH="+attMonth+" AND ATTN_YEAR="+attYear+" AND ATT_EMP_STATUS='O' ";
	onHoldQuery+=getFilterQuery(bean);
	onHoldQuery+=" GROUP BY EMP_CENTER ,CENTER_NAME";
HashMap<String, Object[][]>onHoldMap=getSqlModel().getSingleResultMap(onHoldQuery, 0, 2);
	
	
	
	String pendingLEaveQuery="SELECT EMP_CENTER,  CENTER_NAME,COUNT(*)  "
							+"	FROM  HRMS_LEAVE_DTLHISTORY   "
							+"	INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_DTLHISTORY.EMP_ID = HRMS_EMP_OFFC. EMP_ID) " 
							+"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER)"
							+"	WHERE LEAVE_DTL_STATUS='P' AND LEAVE_MONTH="+attMonth+"	 AND LEAVE_YEAR="+attYear+"	";
	pendingLEaveQuery+=getFilterQuery(bean);
	pendingLEaveQuery+=" GROUP BY EMP_CENTER ,CENTER_NAME";
	HashMap<String, Object[][]>pendingLeaveMap=getSqlModel().getSingleResultMap(pendingLEaveQuery, 0, 2);
	
	
	
	if(obj!=null && obj.length>0){
		ArrayList list =new ArrayList();
		
		double uploadedEmpCount=0.0;
		double leaveEmpCount=0.0;
		double resignedEmpCount=0.0;
		double onHoldEmpCount=0.0;
		
		
		for (int i = 0; i < obj.length; i++) {
			UploadMonthlyAttendanceEGov subBean=new UploadMonthlyAttendanceEGov();
			subBean.setIttBranchName(String.valueOf(obj[i][1]));
			subBean.setIttBranchCode(String.valueOf(obj[i][0]));
			subBean.setIttUploadedEmployee("0");
			subBean.setIttLeaveApplication("0");
			subBean.setIttResignedEmployee("0");
			subBean.setIttOnHoldEmployee("0");
			//SET UPLOADED EMPLOYEE
			if(statisticMap!=null && statisticMap.size()>0){
				String key=String.valueOf(obj[i][0]);
				Object[][]data=statisticMap.get(key);
				if(data !=null && data.length>0){
					subBean.setIttUploadedEmployee(String.valueOf(data[0][2]));
					uploadedEmpCount+=Double.parseDouble(String.valueOf(data[0][2]));
				}
			}
			//SET PENDING LEAVE APPLICATION
			if(pendingLeaveMap!=null && pendingLeaveMap.size()>0){
				String key=String.valueOf(obj[i][0]);
				Object[][]data=pendingLeaveMap.get(key);
				if(data !=null && data.length>0){
					subBean.setIttLeaveApplication(String.valueOf(data[0][2]));
					leaveEmpCount+=Double.parseDouble(String.valueOf(data[0][2]));
				}
			}
			//SET ONHOLD EMPLOYEE
			if(onHoldMap!=null && onHoldMap.size()>0){
				String key=String.valueOf(obj[i][0]);
				Object[][]data=onHoldMap.get(key);
				if(data !=null && data.length>0){
					subBean.setIttOnHoldEmployee(String.valueOf(data[0][2]));
					onHoldEmpCount+=Double.parseDouble(String.valueOf(data[0][2]));
				}
			}
			
			list.add(subBean);
		}	
		
		bean.setIttTotalUploadedEmployee(String.valueOf(uploadedEmpCount));
		bean.setIttTotalLeaveApplication(String.valueOf(leaveEmpCount));
		bean.setIttTotalResignedEmployee(String.valueOf(resignedEmpCount));
		bean.setIttTotalOnHoldEmployee(String.valueOf(onHoldEmpCount));
		
		
		bean.setStatisticsList(list);
	}
	else{
		bean.setStatisticsList(null);
	}
	
	//=======================
	
	
}

public String getFilterQuery(UploadMonthlyAttendanceEGov bean){
	String sqlQuery="";
	String typeCode = bean.getEmployeeTypeId();
	String payBillNo = bean.getPayBillId();
	String brnCode = bean.getBranchId();
	String deptCode = bean.getDepartmentId();
	String divCode = bean.getDivisionId();
	String empSearchId = bean.getEmpSerachId();
	
	if (!typeCode.equals("")) {
		sqlQuery += " AND EMP_TYPE = " + typeCode;
	}
	if (!payBillNo.equals("")) {
		sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
	}
	if (!brnCode.equals("")) {
		sqlQuery += " AND EMP_CENTER = " + brnCode;
	}
	if (!deptCode.equals("")) {
		sqlQuery += " AND EMP_DEPT = " + deptCode;
	}
	if (!divCode.equals("")) {
		sqlQuery += " AND EMP_DIV = " + divCode;
	}
	if (!empSearchId.equals("")) {
		sqlQuery += " AND EMP_ID = " + empSearchId;
	}
	return sqlQuery;
}
	/**
	 * METHOD TO REMOVE THE SELECTED EMPLOYEE
	 * @param bean
	 * @return
	 */
	public boolean removeEmployee(UploadMonthlyAttendanceEGov bean) {
		boolean result=false;
		String divCode = bean.getDivisionId();
		String empCode = bean.getRemoveEmpCode();	
		String attMonth=bean.getMonth();
		String attYear=bean.getYear();
		Object[][]data=new Object[1][3];
		data[0][0]=attMonth;
		data[0][1]=attYear;
		data[0][2]=empCode;
		//DELETE FROM UPLOADED ATTENDANCE		
		String deleteQuery="DELETE FROM HRMS_UPLOAD_ATTENDANCE_"+attYear+"	 WHERE ATTN_MONTH=? AND ATTN_YEAR=? AND ATTN_EMP_ID	=?";
		getSqlModel().singleExecute(deleteQuery,data);
		String deleteQuerydtl="DELETE FROM HRMS_UPLOAD_ATTN_DTL_"+attYear+"	WHERE ATTN_DTL_MONTH=? AND ATTN_DTL_YEAR=? AND ATTN_DTL_EMP_ID=?";
		getSqlModel().singleExecute(deleteQuerydtl,data);
		
		//DELETE FROM MONTHLY ATTENDANCE
		String deleteMonthQuery="DELETE FROM HRMS_MONTH_ATTENDANCE_"+attYear+"	WHERE  ATTN_MONTH=? AND ATTN_YEAR=? AND ATTN_EMP_ID=?";
		getSqlModel().singleExecute(deleteMonthQuery,data);
		String deleteMonthQuerydtl="DELETE FROM HRMS_MONTH_ATT_DTL_"+attYear+"	WHERE ATT_MONTH=? AND ATT_YEAR=? AND ATT_EMP_CODE=?";
		getSqlModel().singleExecute(deleteMonthQuerydtl,data);
		
		//DELETE FROM SALARY PROCRSS
		String deletesalaryQuery="DELETE FROM HRMS_SALARY_"+attYear+"	WHERE SAL_MONTH=? AND SAL_YEAR=? AND EMP_ID=?";
		getSqlModel().singleExecute(deletesalaryQuery,data);
		String deletesalaryCreditQuerydtl="DELETE FROM HRMS_SAL_CREDITS_"+attYear+"	WHERE SAL_MONTH=? AND SAL_YEAR=? AND EMP_ID=?	";
		getSqlModel().singleExecute(deletesalaryCreditQuerydtl,data);
		String deletesalaryDebiteQuerydtl="DELETE FROM HRMS_SAL_DEBITS_"+attYear+"	WHERE SAL_MONTH=? AND SAL_YEAR=? AND EMP_ID=?	";
		getSqlModel().singleExecute(deletesalaryDebiteQuerydtl,data);
		
		return result;
	}
	/**
	 * METHOD TO REMOVE THE SELECTED EMPLOYEE
	 * @param bean
	 * @return
	 */
	public boolean addOnHold(UploadMonthlyAttendanceEGov bean,String type,String empCode) {
		String divCode = bean.getDivisionId();	
		String attMonth=bean.getMonth();
		String attYear=bean.getYear();
		Object[][]data=new Object[1][3];
		data[0][0]=attMonth;
		data[0][1]=attYear;
		data[0][2]=empCode;
		System.out.println("data[0][0]:"+data[0][0]);
		System.out.println("data[0][1]:"+data[0][1]);
		System.out.println("data[0][2] :"+data[0][2]);
		
		String deleteQuery="UPDATE  HRMS_UPLOAD_ATTENDANCE_"+attYear+" SET ATT_EMP_STATUS='"+type+"'	 WHERE ATTN_MONTH=? AND ATTN_YEAR=? AND ATTN_EMP_ID	=?";
		return getSqlModel().singleExecute(deleteQuery,data);
	}
}
