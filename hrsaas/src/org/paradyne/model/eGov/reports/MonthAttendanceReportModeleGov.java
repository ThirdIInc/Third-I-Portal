package org.paradyne.model.eGov.reports;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.attendance.MonthAttendanceReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.attendance.MonthAttendanceReportModel;

/**
 * @author Bhushan
 * @date Jul 11, 2008
 */

/**
 * To define a business logic of both monthly as well as annual attendance
 */

public class MonthAttendanceReportModeleGov extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MonthAttendanceReportModeleGov.class);

	/**
	 * Generates a annual attendance report
	 */
	/**
	 * @param bean
	 * @param response
	 */
	public void annualReport(MonthAttendanceReport bean, HttpServletResponse response) {
		try {
			String divName = bean.getDivName();
			String typeName = bean.getTypeName();
			String payBillName = bean.getPayBillName();
			String brnName = bean.getBrnName();
			String deptName = bean.getDeptName();
			String reportType = bean.getReportType();

			int currMonth = Integer.parseInt(bean.getMonth());
			int currYear = Integer.parseInt(bean.getYear());
			int newMonth = currMonth == 1 ? 12 : currMonth - 1;
			int newYear = currMonth == 1 ? currYear : currYear + 1;
			
			String[][] months = new String[12][2];
			int itMonth = currMonth;
			for(int i = 0; i < months.length; i++) {
				months[i][0] = String.valueOf(itMonth);
				months[i][1] = Utility.month(itMonth);
				
				if(itMonth == 12) {
					itMonth = 1;
				} else {
					itMonth++;
				}
			}

			/**
			 * Gets salary days of all the months which are coming in between currMonth and newMonth
			 */
			Object[][] annualAttendance = null;
			try {
				String sqlQuery = " SELECT EMP_TOKEN, ENAME, CENTER_NAME, SUM(" + months[0][1] + ") AS " + months[0][1] + ", " + 
				" SUM(" + months[1][1] + ") AS " + months[1][1] + ", SUM(" + months[2][1] + ") AS " + months[2][1] + ", " +
				" SUM(" + months[3][1] + ") AS " + months[3][1] + ", SUM(" + months[4][1] + ") AS " + months[4][1] + ", " +
				" SUM(" + months[5][1] + ") AS " + months[5][1] + ", SUM(" + months[6][1] + ") AS " + months[6][1] + ", " +
				" SUM(" + months[7][1] + ") AS " + months[7][1] + ", SUM(" + months[8][1] + ") AS " + months[8][1] + ", " +
				" SUM(" + months[9][1] + ") AS " + months[9][1] + ", SUM(" + months[10][1] + ") AS " + months[10][1] + ", " +
				" SUM(" + months[11][1] + ") AS " + months[11][1] + ", SUM(TOT_SAL_DAYS) TOT_SAL_DAYS FROM ( " + 
				" 	SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, CENTER_NAME, " +
				" 	SUM(DECODE(LEDGER_MONTH, " + months[0][0] + ", ATTN_SAL_DAYS, 0 )) " + months[0][1] + ", " + 
				"	SUM(DECODE(LEDGER_MONTH, " + months[1][0] + ", ATTN_SAL_DAYS, 0 )) " + months[1][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[2][0] + ", ATTN_SAL_DAYS, 0 )) " + months[2][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[3][0] + ", ATTN_SAL_DAYS, 0 )) " + months[3][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[4][0] + ", ATTN_SAL_DAYS, 0 )) " + months[4][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[5][0] + ", ATTN_SAL_DAYS, 0 )) " + months[5][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[6][0] + ", ATTN_SAL_DAYS, 0 )) " + months[6][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[7][0] + ", ATTN_SAL_DAYS, 0 )) " + months[7][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[8][0] + ", ATTN_SAL_DAYS, 0 )) " + months[8][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[9][0] + ", ATTN_SAL_DAYS, 0 )) " + months[9][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[10][0] + ", ATTN_SAL_DAYS, 0 )) " + months[10][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[11][0] + ", ATTN_SAL_DAYS, 0 )) " + months[11][1] + ", " +
				"	SUM(DECODE(LEDGER_YEAR, LEDGER_YEAR, ATTN_SAL_DAYS, 0 ))  TOT_SAL_DAYS " + 
				" 	FROM HRMS_MONTH_ATTENDANCE_" + currYear + 
				" 	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_" + currYear + ".ATTN_EMP_ID) " +
				"	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +
				" 	INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_" + currYear + ".ATTN_CODE) " + 
				" 	WHERE ATTN_CODE IN (SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH >= " + currMonth + 
				" 	AND LEDGER_YEAR = " + currYear + ") ";
				sqlQuery = setFiletrs(bean, sqlQuery);
				sqlQuery += " GROUP BY EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME), CENTER_NAME " + 
				" 	UNION " + 
				" 	SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, CENTER_NAME, " +
				" 	SUM(DECODE(LEDGER_MONTH, " + months[0][0] + ", ATTN_SAL_DAYS, 0 )) " + months[0][1] + ", " + 
				"	SUM(DECODE(LEDGER_MONTH, " + months[1][0] + ", ATTN_SAL_DAYS, 0 )) " + months[1][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[2][0] + ", ATTN_SAL_DAYS, 0 )) " + months[2][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[3][0] + ", ATTN_SAL_DAYS, 0 )) " + months[3][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[4][0] + ", ATTN_SAL_DAYS, 0 )) " + months[4][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[5][0] + ", ATTN_SAL_DAYS, 0 )) " + months[5][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[6][0] + ", ATTN_SAL_DAYS, 0 )) " + months[6][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[7][0] + ", ATTN_SAL_DAYS, 0 )) " + months[7][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[8][0] + ", ATTN_SAL_DAYS, 0 )) " + months[8][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[9][0] + ", ATTN_SAL_DAYS, 0 )) " + months[9][1] + ", " +
				"	SUM(DECODE(LEDGER_MONTH, " + months[10][0] + ", ATTN_SAL_DAYS, 0 )) " + months[10][1] + ", " + 
				" 	SUM(DECODE(LEDGER_MONTH, " + months[11][0] + ", ATTN_SAL_DAYS, 0 )) " + months[11][1] + ", " +
				"	SUM(DECODE(LEDGER_YEAR, LEDGER_YEAR, ATTN_SAL_DAYS, 0 ))  TOT_SAL_DAYS " + 
				" 	FROM HRMS_MONTH_ATTENDANCE_" + newYear + 
				" 	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_" + newYear + ".ATTN_EMP_ID) " +
				"	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +
				" 	INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_" + newYear + ".ATTN_CODE) " + 
				" 	WHERE ATTN_CODE IN (SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH <= " + newMonth + 
				" 	AND LEDGER_YEAR = " + newYear + ") ";
				sqlQuery = setFiletrs(bean, sqlQuery);
				sqlQuery += " GROUP BY EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME), CENTER_NAME " + 
				" ) " + 
				" GROUP BY EMP_TOKEN, ENAME, CENTER_NAME ORDER BY CENTER_NAME, UPPER(ENAME), EMP_TOKEN ";
				annualAttendance = getSqlModel().getSingleResult(sqlQuery);
			} catch(Exception e) {
				logger.error("Exception in sql query for attendance:" + e);
			}

			String reportName = "Annual Attendance Report";
			ReportGenerator rg = new ReportGenerator(reportType, reportName, "A4");
			
			if(reportType.equals("Pdf")) { // Report is opening in Pdf
				rg.addFormatedText(reportName, 6, 0, 1, 0);
			} else { // Report is opening in xls or txt
				Object[][] title = new Object[1][3];
				title[0][0] = "";
				title[0][1] = "";
				title[0][2] = reportName;

				int[] cols = {20, 20, 30};
				int[] align = {0, 0, 1};
				rg.tableBodyNoCellBorder(title, cols, align, 1);
			} // end of else statement
			
			// set current date
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String toDay = sdf.format(today);
			
			rg.addText("Date: " + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addText("Month : " + Utility.month(currMonth) + "             Year : " + currYear, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);

			int cnt = 0;
			
			if(!divName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Division : " + divName, 1, 1, 0);
				} else {
					cnt++;
				}
			}
			if(!typeName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Employee Type : " + typeName, 1, 1, 0);
				} else {
					cnt++;
				}
			}
			if(!payBillName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Pay Bill Group : " + payBillName, 1, 1, 0);
				} else {
					cnt++;
				}
			}
			if(!brnName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Branch : " + brnName, 1, 1, 0);
				} else {
					cnt++;
				}
			}
			if(!deptName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Department : " + deptName, 1, 1, 0);
				} else {
					cnt++;
				}
			}

			if(cnt > 0) {
				Object[][] filterObj = new Object[cnt][2]; // Contains the filters data which will appear on the top table

				int fil = 0;

				if(!divName.equals("")) {
					filterObj[fil][0] = " Division : ";
					filterObj[fil][1] = divName;
					fil++;
				}
				if(!brnName.equals("")) {
					filterObj[fil][0] = "Branch : ";
					filterObj[fil][1] = brnName;
					fil++;
				}
				if(!deptName.equals("")) {
					filterObj[fil][0] = "Department : ";
					filterObj[fil][1] = deptName;
				}
				if(!typeName.equals("")) {
					filterObj[fil][0] = " Employee Type : ";
					filterObj[fil][1] = typeName;
					fil++;
				}
				if(!payBillName.equals("")) {
					filterObj[fil][0] = "PayBill : ";
					filterObj[fil][1] = payBillName;
					fil++;
				}
				rg.tableBodyNoCellBorder(filterObj, new int[]{8, 8}, new int[]{2, 0}, 0);
			}
			rg.addText("\n", 0, 0, 0);

			/**
			 * Sets column name
			 */
			String[] colNames = {"Employee ID", "Employee Name", "Branch",  months[0][1], months[1][1], months[2][1], months[3][1], 
					months[4][1], months[5][1], months[6][1], months[7][1], months[8][1], months[9][1], months[10][1], months[11][1], 
					"Total"};
			int[] cellWidth = {15, 30, 30, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15}; // Set width to every column
			int[] cellAln = {0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}; // Set alignment of every column

			int month = currMonth;
			
			for(int i = 3; i < colNames.length - 1; i++) {
				if(month >= currMonth) {
					colNames[i] = colNames[i] + "-\n" + currYear;
				} else {
					colNames[i] = colNames[i] + "-\n" + newYear;
				}
				
				if(month == 12) {
					month = 1;
				} else {
					month++;
				}
			} // end of for loop
			
			if(annualAttendance != null && annualAttendance.length != 0) {
				if(reportType.equals("Xls")) {
					rg.xlsTableBody(colNames, annualAttendance, cellWidth, cellAln);
				} else if(reportType.equals("Pdf")) {
					rg.tableBody(colNames, annualAttendance, cellWidth, cellAln);
				}
			} else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}

			rg.createReport(response); // Creates a report
		} catch(Exception e) {
			logger.error("Exception in annualReport:" + e);
		} // end of try-catch block
	}

	public void getMonthAttendanceReport(MonthAttendanceReport bean, HttpServletResponse response) {
		try {
			String month = bean.getMonth(); // month
			String year = bean.getYear(); // year
			String divName = bean.getDivName(); // division
			String typeName = bean.getTypeName(); // employee type
			String payBillName = bean.getPayBillName(); // pay bill no
			String brnName = bean.getBrnName(); // branch
			String deptName = bean.getDeptName(); // department
			String reportType = bean.getReportType(); // report type

			Object[][] attendance = null, leaveTypes = null, attendanceDtl = null;
			try {
				String attendanceSql = " SELECT ATTN_EMP_ID, EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) AS ENAME, CENTER_NAME, " +
				" ATTN_DAYS, ATTN_WOFF, ATTN_HOLIDAY, ATTN_LATE_MARKS, ATTN_HALF_DAYS, ATTN_PAID_LEVS, ATTN_UNPAID_LEVS, ATTN_SAL_DAYS  " +
				" , NVL(DEPT_NAME,' ') " +
				" FROM HRMS_MONTH_ATTENDANCE_" + year + 
				" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_EMP_ID) " + 
				" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " + 
				" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " + 
				" INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE) " + 
				" WHERE LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year;
				attendanceSql = setFiletrs(bean, attendanceSql);
				attendanceSql += " ORDER BY UPPER(CENTER_NAME), UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";			
				attendance = getSqlModel().getSingleResult(attendanceSql);
				
				String leaveTypeQuery = " SELECT LEAVE_ID, LEAVE_ABBR FROM HRMS_LEAVE ORDER BY LEAVE_ID ";
				leaveTypes = getSqlModel().getSingleResult(leaveTypeQuery);
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% :::::");
				
							
				String attendanceDtlSql = " SELECT ATT_EMP_CODE, ATT_LEAVE_CODE, ATT_LEAVE_AVAILABLE, NVL(ATT_LEAVE_ADJUST, 0) + " +
				" NVL(ATT_LATEMARK_ADJUST, 0) +  NVL(ATT_HALFDAY_ADJUST, 0) + NVL(ATT_MANUAL_ADJUST, 0) AS PAID_LEAVES, " +
				" ATT_LEAVE_BALANCE ,NVL(ATT_LEAVE_ADJUST_RECOVERY,0) FROM HRMS_MONTH_ATT_DTL_" + year +
				" INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATT_DTL_" + year + ".ATT_CODE) " +
				" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATT_DTL_" + year + ".ATT_EMP_CODE) " +
				" WHERE LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year;
				attendanceDtlSql = setFiletrs(bean, attendanceDtlSql);
				attendanceDtlSql += " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
				attendanceDtl = getSqlModel().getSingleResult(attendanceDtlSql);
			} catch(Exception e) {
				logger.error("Exception in attendanceDtlSql:" + e);
			}			

			String reportName = "Monthly Attendance Report";
			ReportGenerator rg = new ReportGenerator(reportType, reportName + "_" + Utility.month(Integer.parseInt(month)) + "_" + year, "A4");
			
			if(reportType.equals("Pdf")) {
				rg.addFormatedText(reportName, 6, 0, 1, 0);
			} else {
				Object[][] title = new Object[1][3];
				title[0][0] = "";
				title[0][1] = "";
				title[0][2] = reportName;

				int[] cols = {20, 20, 30};
				int[] align = {0, 0, 1};
				rg.tableBodyNoCellBorder(title, cols, align, 1);
			}
			
			// set current date
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String toDay = sdf.format(today);
			
			rg.addText("Date: " + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addText("Month : " + Utility.month(Integer.parseInt(month)) + "             Year : " + year, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);

			int cntFilter = 0;
			
			if(!divName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Division : " + divName, 1, 1, 0);
				} else {
					cntFilter++;
				}
			}
			if(!typeName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Employee Type : " + typeName, 1, 1, 0);
				} else {
					cntFilter++;
				}
			}
			if(!payBillName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Pay Bill Group : " + payBillName, 1, 1, 0);
				} else {
					cntFilter++;
				}
			}
			if(!brnName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Branch : " + brnName, 1, 1, 0);
				} else {
					cntFilter++;
				}
			}
			if(!deptName.equals("")) {
				if(reportType.equals("Pdf")) {
					rg.addText("Department : " + deptName, 1, 1, 0);
				} else {
					cntFilter++;
				}
			}
			
			Object[][] filterObj = null;
			
			if(cntFilter > 0) {
				filterObj = new Object[cntFilter][2]; // Contains the filters data which will appear on the top table
				int fil = 0;

				if(!divName.equals("")) {
					filterObj[fil][0] = " Division : ";
					filterObj[fil][1] = divName;
					fil++;
				}
				if(!brnName.equals("")) {
					filterObj[fil][0] = "Branch : ";
					filterObj[fil][1] = brnName;
					fil++;
				}
				if(!deptName.equals("")) {
					filterObj[fil][0] = "Department : ";
					filterObj[fil][1] = deptName;
					fil++;
				}
				if(!typeName.equals("")) {
					filterObj[fil][0] = " Employee Type : ";
					filterObj[fil][1] = typeName;
					fil++;
				}
				if(!payBillName.equals("")) {
					filterObj[fil][0] = "PayBill : ";
					filterObj[fil][1] = payBillName;
					fil++;
				}
				rg.tableBodyNoCellBorder(filterObj, new int[]{8, 8}, new int[]{2, 0}, 0);
			} // end of if statement
			rg.addText("\n", 0, 0, 0);

			String[] attendanceCols = {"Employee Id", "Employee Name", "Branch Name","Department Name", "Attendance Days", "Weekly Offs", "Holidays", 
					"Late Marks", "Half Days", "Paid Leaves", "Unpaid Leaves", "Salary Days"};

			String[] allCols = new String[attendanceCols.length + (leaveTypes.length * 4)];

			int[] cellWidth = new int[allCols.length];
			cellWidth[0] = 15;
			cellWidth[1] = 30;
			cellWidth[2] = 30;
			cellWidth[3] = 30;
			
			cellWidth[4] = 15;
			cellWidth[5] = 15;
			cellWidth[6] = 15;
			cellWidth[7] = 15;
			cellWidth[8] = 15;
			cellWidth[9] = 15;
			cellWidth[10] = 15;
			cellWidth[11] = 15;

			int[] cellAln = new int[allCols.length];
			cellAln[0] = 0;
			cellAln[1] = 0;
			cellAln[2] = 0;
			cellAln[3] = 0;		
			
			cellAln[4] = 2;
			cellAln[5] = 2;
			cellAln[6] = 2;
			cellAln[7] = 2;
			cellAln[8] = 2;
			cellAln[9] = 2;
			cellAln[10] = 2;
			cellAln[11] = 2;

			for(int i = 0; i < attendanceCols.length; i++) {
				allCols[i] = attendanceCols[i];
			}
			
			int count = attendanceCols.length;
			
			if(leaveTypes != null && leaveTypes.length > 0) {
				for(int i = 0; i < leaveTypes.length; i++) {
					allCols[count] = "Opening " + String.valueOf(leaveTypes[i][1]);
					cellWidth[count] = 15;
					cellAln[count] = 2;
					count++;
					
					allCols[count] = String.valueOf(leaveTypes[i][1]) + " Adjusted";
					cellWidth[count] = 15;
					cellAln[count] = 2;
					count++;
					
					allCols[count] = "Closing " + String.valueOf(leaveTypes[i][1]);
					cellWidth[count] = 15;
					cellAln[count] = 2;
					count++;
					//RECOVERY DAYS
					allCols[count] = "Recovery " + String.valueOf(leaveTypes[i][1]);
					cellWidth[count] = 15;
					cellAln[count] = 2;
					count++;
				}
			}
			
			Object[][] finalObject = null;

			if(attendance != null && attendance.length > 0) {
				finalObject = new Object[attendance.length][allCols.length];
				
				for(int i = 0; i < attendance.length; i++) {
					String empId = String.valueOf(attendance[i][0]);
					
					finalObject[i][0] = String.valueOf(attendance[i][1]); // EMPLOYEE ID
					finalObject[i][1] = String.valueOf(attendance[i][2]); // EMPLOYEE NAME
					finalObject[i][2] = String.valueOf(attendance[i][3]); // BRANCH NAME
					
					finalObject[i][3] = String.valueOf(attendance[i][12]);  //Department Name
					
					finalObject[i][4] = String.valueOf(attendance[i][4]); // ATTENDANCE DAYS
					finalObject[i][5] = String.valueOf(attendance[i][5]); // WEEKLY OFFS
					finalObject[i][6] = String.valueOf(attendance[i][6]); // HOLIDAYS
					finalObject[i][7] = String.valueOf(attendance[i][7]); // LATE MARKS
					finalObject[i][8] = String.valueOf(attendance[i][8]); // HALF DAYS
					finalObject[i][9] = String.valueOf(attendance[i][9]); // PAID LEAVES
					finalObject[i][10] = String.valueOf(attendance[i][10]); // UNPAID LEAVES
					finalObject[i][11] = String.valueOf(attendance[i][11]); // SAL DAYS
					
					int cnt = attendanceCols.length;
					
					for(int j = 0; j < leaveTypes.length; j++) {
						String leaveId = String.valueOf(leaveTypes[j][0]);
						boolean findEmp = false;
						for(int k = 0; k < attendanceDtl.length; k++) {
							String empIDDtl = String.valueOf(attendanceDtl[k][0]);
							String leaveIdDtl = String.valueOf(attendanceDtl[k][1]);
							
							if(empId.equals(empIDDtl) && leaveIdDtl.equals(leaveId)) {
								finalObject[i][cnt] = String.valueOf(attendanceDtl[k][2]);
								cnt++;
								finalObject[i][cnt] = String.valueOf(attendanceDtl[k][3]);
								cnt++;
								finalObject[i][cnt] = String.valueOf(attendanceDtl[k][4]);
								cnt++;
								//RECOVERY DAYS
								finalObject[i][cnt] = String.valueOf(attendanceDtl[k][5]);
								cnt++;
								findEmp = true;
								break;
							}
						}
						
						if(!findEmp) {
							finalObject[i][cnt] = 0;
							cnt++;
							finalObject[i][cnt] = 0;
							cnt++;
							finalObject[i][cnt] = 0;
							cnt++;
						}
					}
				}
			}

			if(finalObject != null && finalObject.length > 0) {
				if(reportType.equals("Xls")) {
					rg.xlsTableBody(allCols, finalObject, cellWidth, cellAln);
				} else if(reportType.equals("Pdf")) {
					rg.tableBody(allCols, finalObject, cellWidth, cellAln);
				}
			} else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}

			rg.createReport(response);// Creates a report

		} catch(Exception e) {
			logger.error("Exception in getReport:" + e);
		}
	}// end of getReport

	public void monthDtlReport(MonthAttendanceReport bean, HttpServletResponse response) {
		try {
			String month = bean.getMonth();
			String year = bean.getYear();
			String divName = bean.getDivName();
			String typeName = bean.getTypeName();
			String payBillName = bean.getPayBillName();
			String brnName = bean.getBrnName();
			String deptName = bean.getDeptName();
			String reportType = bean.getReportType();

			/**
			 * Get attendance data for a given month and year
			 */
			String sqlQuery = " SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, ATTN_DAYS," + " ATTN_WOFF, ATTN_HOLIDAY, ATTN_PAID_LEVS, ATTN_UNPAID_LEVS,ATTN_LATE_MARKS, ATTN_HALF_DAYS,ATTN_SAL_DAYS ,HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE,ATTN_EMP_ID, " + " NVL(ATTN_SYSTEM_UNPAID,0), NVL(ATTN_MANUAL_UNPAID,0),NVL(ATTN_SYSTEM_UNPAID,0)+NVL(ATTN_MANUAL_UNPAID,0) FROM HRMS_MONTH_ATTENDANCE_" + year + " INNER JOIN  HRMS_EMP_OFFC ON( HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_EMP_ID) " + " INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE) " + " WHERE ATTN_CODE IN (SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year + " ) ";
			sqlQuery = setFiletrs(bean, sqlQuery);
			sqlQuery += " ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ";
			Object[][] result = getSqlModel().getSingleResult(sqlQuery);

			/**
			 * Get current date
			 */
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String toDay = sdf.format(today);

			String reportName = "MONTHLY ATTENDANCE REPORT";
			ReportGenerator rg = new ReportGenerator(reportType, reportName);

			if(null != result && result.length != 0) {
				if(reportType.equals("Pdf"))// Report is opening in Pdf
				{
					rg.addFormatedText(reportName, 6, 0, 1, 0);
				} // end of if statement
				else// Report is opening in xls or txt
				{
					Object[][] title = new Object[1][3];
					title[0][0] = "";
					title[0][1] = "";
					title[0][2] = reportName;

					int[] cols = {20, 20, 30};
					int[] align = {0, 0, 1};
					rg.tableBodyNoCellBorder(title, cols, align, 1);
				} // end of else statement
				rg.addText("Date: " + toDay, 0, 2, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addText("Month : " + Utility.month(Integer.parseInt(month)) + "             Year : " + year, 0, 1, 0);
				rg.addText("\n", 0, 0, 0);

				int cnt = 0;// To count the filters selected
				if(!divName.equals("")) {
					cnt++;
				} // end of if statement
				if(!brnName.equals("")) {
					cnt++;
				} // end of if statement
				if(!deptName.equals("")) {
					cnt++;
				} // end of if statement
				if(!typeName.equals("")) {
					cnt++;
				} // end of if statement
				if(!payBillName.equals("")) {
					cnt++;
				} // end of if statement

				if(cnt > 0) {
					Object[][] filterObj = new Object[cnt][2];// Contains the
					// filters data
					// which will
					// appear on the
					// top table

					int fil = 0;

					if(!divName.equals("")) {
						filterObj[fil][0] = " Division : ";
						filterObj[fil][1] = divName;
						fil++;
					} // end of if statement
					if(!brnName.equals("")) {
						filterObj[fil][0] = "Branch : ";
						filterObj[fil][1] = brnName;
						fil++;
					} // end of if statement
					if(!deptName.equals("")) {
						filterObj[fil][0] = "Department : ";
						filterObj[fil][1] = deptName;
						fil++;
					} // end of if statement
					if(!typeName.equals("")) {
						filterObj[fil][0] = " Employee Type : ";
						filterObj[fil][1] = typeName;
						fil++;
					} // end of if statement
					if(!payBillName.equals("")) {
						filterObj[fil][0] = "PayBill : ";
						filterObj[fil][1] = payBillName;
						fil++;
					} // end of if statement
					rg.tableBodyNoBorder(filterObj, new int[]{8, 8}, new int[]{2, 0});
				} // end of if statement

				rg.addText("\n", 0, 0, 0);

				/**
				 * Sets column name
				 */
				String[] colNames = {"Attendance Days", "Weekly Offs", "Holidays", "Paid Leaves", "Unpaid Leaves", "Late Marks", "Half Days", "Salary Days"};
				int[] cellWidth = {20, 13, 15, 12, 12, 15, 15, 12};// Set
				// width
				// to
				// every
				// column
				int[] cellAln = {1, 1, 1, 1, 1, 1, 1, 1};// Set alignment of
				// every column

				int[] cellWidthLabel = {100};// Set width to every column
				int[] cellAlnLabel = {0};// Set alignment of every column

				if(result != null && result.length > 0) {
					Object[][] labelObj = new Object[1][1];
					Object[][] resultObj = new Object[1][8];
					Object[][] unPaidObj = new Object[1][7];
					Object[][] unPaidTot = new Object[1][3];
					Object[][] unPaidTotXls = new Object[1][7];
					for(int i = 0; i < result.length; i++) {
						labelObj[0][0] = "Attendance Details : "; // for Label
						if(reportType.equals("Pdf") || reportType.equals("Txt")) {
							String label = "\n\nEMPLOYEE ID : " + result[i][0] + "    \t" + "EMPLOYEE NAME : " + result[i][1];
							rg.addText(label, 0, 0, 0);
						} else {
							Object[][] empObj = new Object[1][1];
							empObj[0][0] = "EMPLOYEE ID : " + result[i][0] + " EMPLOYEE NAME : " + result[i][1];
							// rg.tableBodyNoBorder(empObj, new int []{60}, new
							// int []{1}); // for Label
							rg.tableBodyNoCellBorder(empObj, new int[]{60}, new int[]{0}, 1);
						}
						resultObj[0][0] = "" + result[i][2]; // for
						// Attendance
						// detail
						resultObj[0][1] = "" + result[i][3];
						resultObj[0][2] = "" + result[i][4];
						resultObj[0][3] = "" + result[i][5];
						resultObj[0][4] = "" + result[i][6];
						resultObj[0][5] = "" + result[i][7];
						resultObj[0][6] = "" + result[i][8];
						resultObj[0][7] = "" + result[i][9];

						unPaidObj[0][0] = "Unpaid Leaves";
						unPaidObj[0][1] = "";
						unPaidObj[0][2] = "" + result[i][12];
						unPaidObj[0][3] = "";
						unPaidObj[0][4] = "";
						unPaidObj[0][5] = "" + result[i][13];
						unPaidObj[0][6] = "";

						unPaidTot[0][0] = "Total Unpaid Leaves :";
						unPaidTot[0][1] = "" + result[i][14];
						unPaidTot[0][2] = "";

						unPaidTotXls[0][0] = "";
						unPaidTotXls[0][1] = "";
						unPaidTotXls[0][2] = "";
						unPaidTotXls[0][3] = "Total Unpaid Leaves :";
						unPaidTotXls[0][4] = "" + result[i][14];
						unPaidTotXls[0][5] = "";
						unPaidTotXls[0][6] = "";

						if(reportType.equals("Pdf") || reportType.equals("Txt")) {
							rg.tableBodyNoBorder(labelObj, cellWidthLabel, cellAlnLabel); // for Label
						} else {
							rg.addText("Attendance Details : ", 0, 0, 0);
						}
						if(reportType.equals("Pdf") || reportType.equals("Txt")) {
							rg.tableBody(colNames, resultObj, cellWidth, cellAln); // for Attendance detail
							rg.addFormatedText("\n", 0, 0, 0, 0);
						} else {
							rg.tableBody(colNames, resultObj, cellWidth, cellAln); // for Attendance detail
						}

						try {
							String sqlLeaveDtl = "  SELECT LEAVE_NAME , NVL(ATT_LEAVE_AVAILABLE,0), NVL(ATT_LEAVE_ADJUST,0), NVL(ATT_LATEMARK_ADJUST,0),  " + " NVL(ATT_HALFDAY_ADJUST,0),  NVL(ATT_MANUAL_ADJUST,0) ,NVL(ATT_LEAVE_BALANCE,0) " + " FROM HRMS_MONTH_ATT_DTL_" + year + " LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID=HRMS_MONTH_ATT_DTL_" + year + ".ATT_LEAVE_CODE) " + " WHERE ATT_CODE =" + String.valueOf(result[i][10]) + " AND ATT_EMP_CODE =" + String.valueOf(result[i][11]);
							Object[][] leaveData = getSqlModel().getSingleResult(sqlLeaveDtl);

							String[] colNamesLeave = {"Leave Types", "Original Leave Balance", "System adjusted Leaves", "System Adjusted Late Marks", "System Adjusted Half Days", "Manually Adjusted Leaves", "Current Leave Balance"};
							int[] cellWidthLeave = {15, 20, 18, 20, 20, 17, 15};// Set
							// width
							// to
							// every
							// column
							int[] cellAlnLeave = {0, 2, 2, 2, 2, 2, 2};// Set
							// alignment
							// of
							// every
							// column

							int[] cellWidthLeaveTot = {73, 16, 30};// Set
							// width
							// to
							// every
							// column
							int[] cellAlnLeaveTot = {2, 2, 0};// Set alignment
							// of every
							// column

							Object[][] label1Obj = new Object[1][1];
							label1Obj[0][0] = "Leave Details : ";

							if(reportType.equals("Pdf") || reportType.equals("Txt")) {
								rg.tableBodyNoBorder(label1Obj, cellWidthLabel, cellAlnLabel); // for Label
							} else {
								rg.addText("Leave Details : ", 0, 0, 0); // for
								// Label
							}
							if(leaveData != null && leaveData.length > 0) {

								Object[][] leaveObj = new Object[leaveData.length][7];
								Object[][] leaveTot = new Object[1][3];

								double totpaidLeave = 0;
								for(int j = 0; j < leaveData.length; j++) {

									leaveObj[j][0] = "" + leaveData[j][0];
									leaveObj[j][1] = "" + leaveData[j][1];
									leaveObj[j][2] = "" + leaveData[j][2];
									leaveObj[j][3] = "" + leaveData[j][3];
									leaveObj[j][4] = "" + leaveData[j][4];
									leaveObj[j][5] = "" + leaveData[j][5];
									leaveObj[j][6] = "" + leaveData[j][6];
									totpaidLeave += (Double.parseDouble("" + leaveData[j][2]) + Double.parseDouble("" + leaveData[j][3]) + Double.parseDouble("" + leaveData[j][4]) + Double.parseDouble("" + leaveData[j][5]));
								}

								leaveTot[0][0] = "Total Paid Leaves :";
								leaveTot[0][1] = "" + totpaidLeave;
								leaveTot[0][2] = "";

								Object[][] leaveTotXls = new Object[1][7];
								leaveTotXls[0][0] = "";
								leaveTotXls[0][1] = "";
								leaveTotXls[0][2] = "";
								leaveTotXls[0][3] = "Total Paid Leaves :";
								leaveTotXls[0][4] = "" + totpaidLeave;
								leaveTotXls[0][5] = "";
								leaveTotXls[0][6] = "";

								if(reportType.equals("Pdf") || reportType.equals("Txt")) {
									rg.tableBody(colNamesLeave, leaveObj, cellWidthLeave, cellAlnLeave); // for
									// Leave
									// detail

								} else {
									int[] cellWidthLeaveForXls = {35, 35, 35, 35, 35, 35, 35};// Set width to
									// every column
									rg.tableBody(colNamesLeave, leaveObj, cellWidthLeaveForXls, cellAlnLeave); // for
									// Leave
									// detail
								}
								if(reportType.equals("Pdf") || reportType.equals("Txt")) {
									rg.tableBodyNoCellBorder(leaveTot, cellWidthLeaveTot, cellAlnLeaveTot, 1);
									// rg.tableBody(leaveTot, cellWidthLeaveTot,
									// cellAlnLeaveTot);
								} else {
									int[] cellAlnLeaveXls = {0, 0, 0, 0, 2, 0, 0};
									rg.tableBodyNoCellBorder(leaveTotXls, cellWidthLeave, cellAlnLeaveXls, 1);
								}
								// rg.tableBodyBold(leaveTot, cellWidthLeave,
								// cellAlnLeave); // for Leave Total
							} else {
								Object[][] leaveObj = new Object[1][7];
								leaveObj[0][0] = "";
								leaveObj[0][1] = "";
								leaveObj[0][2] = "";
								leaveObj[0][3] = "";
								leaveObj[0][4] = "";
								leaveObj[0][5] = "";
								leaveObj[0][6] = "";

								rg.tableBody(colNamesLeave, leaveObj, cellWidthLeave, cellAlnLeave); // for
								// Leave
								// detail
								// rg.addText("\n", 0, 0, 0);
							}
						} catch(Exception e) {
							logger.error(e);
						}

						// rg.addText("\n UNPAID LEAVE DETAILS ", 0, 0, 0);
						// rg.addText("\n", 0, 0, 0);
						// String[] colNamesUnpaidLeave = {"system adjusted
						// unpaid ", " manually adjusted unpaid leaves","total
						// unpaid leaves"};

						int[] cellWidthUnpaidLeave = {15, 20, 18, 20, 20, 17, 15};// Set width to every column
						int[] cellAlnUnpaidLeave = {0, 2, 2, 2, 2, 2, 2};// Set
						// alignment
						// of
						// every
						// column

						rg.tableBody(unPaidObj, cellWidthUnpaidLeave, cellAlnUnpaidLeave); // for Leave detail
						int[] cellWidthUnpaidTot = {73, 16, 30};// Set width
						// to every
						// column
						int[] cellAlnUnpaidTot = {2, 2, 0};// Set alignment
						// of every
						// column
						if(reportType.equals("Pdf") || reportType.equals("Txt")) {

							rg.tableBodyNoCellBorder(unPaidTot, cellWidthUnpaidTot, cellAlnUnpaidTot, 1);
						} else {
							int[] cellWidthXls = {15, 20, 18, 20, 20, 17, 15};// Set
							// width
							// to
							// every
							// column
							int[] cellAlnXls = {0, 2, 2, 2, 2, 2, 2};// Set
							// alignment
							// of
							// every
							// column
							rg.tableBodyNoCellBorder(unPaidTotXls, cellWidthXls, cellAlnXls, 1);

						}
						// rg.addText("\n", 0, 0, 0);
					} // end of for loop
				} else {
					rg.addText("No record to display", 1, 0, 0);
				}
			} // end of if statement
			rg.createReport(response);// Creates a report
		} catch(Exception e) {
			logger.error("Exception in monthDtlReport:" + e);
		} // end of try-catch block
	}
	
	/**
	 * Generates a monthly attendance report
	 */
	/**
	 * @param bean
	 * @param response
	 */
	public void monthReport(MonthAttendanceReport bean, HttpServletResponse response) {
		try {
			String month = bean.getMonth();
			String year = bean.getYear();
			String divName = bean.getDivName();
			String typeName = bean.getTypeName();
			String payBillName = bean.getPayBillName();
			String brnName = bean.getBrnName();
			String deptName = bean.getDeptName();
			String reportType = bean.getReportType();

			/**
			 * Get attendance data for a given month and year
			 */
			String sqlQuery = " SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, ATTN_DAYS, " + " ATTN_WOFF, ATTN_HOLIDAY, ATTN_PAID_LEVS, ATTN_UNPAID_LEVS, ATTN_SAL_DAYS " + " FROM HRMS_MONTH_ATTENDANCE_" + year + " INNER JOIN  HRMS_EMP_OFFC ON( HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_EMP_ID) " + " INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE) " + " WHERE ATTN_CODE IN (SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year + " ) ";
			sqlQuery = setFiletrs(bean, sqlQuery);
			sqlQuery += " ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ";
			Object[][] result = getSqlModel().getSingleResult(sqlQuery);

			/**
			 * Get current date
			 */
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String toDay = sdf.format(today);

			String reportName = "MONTHLY ATTENDANCE REPORT";
			ReportGenerator rg = new ReportGenerator(reportType, reportName);

			/**
			 * Sets column name
			 */
			String[] colNames = {"EMPLOYEE ID", "EMPLOYEE NAME", "ATTENDANCE DAYS", "WEEKLY OFFS", "HOLIDAYS", "PAID LEAVES", "UNPAID LEAVES", "SALARY DAYS"};
			int[] cellWidth = {17, 23, 20, 13, 15, 12, 12, 12};// Set width
			// to every
			// column
			int[] cellAln = {0, 0, 1, 1, 1, 1, 1, 1};// Set alignment of
			// every column

			if(null != result && result.length != 0) {
				if(reportType.equals("Pdf"))// Report is opening in Pdf
				{
					rg.addFormatedText(reportName, 6, 0, 1, 0);
				} // end of if statement
				else// Report is opening in xls or txt
				{
					Object[][] title = new Object[1][3];
					title[0][0] = "";
					title[0][1] = "";
					title[0][2] = reportName;

					int[] cols = {20, 20, 30};
					int[] align = {0, 0, 1};
					rg.tableBodyNoCellBorder(title, cols, align, 1);
				} // end of else statement
				rg.addText("Date: " + toDay, 0, 2, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addText("Month : " + Utility.month(Integer.parseInt(month)) + "             Year : " + year, 0, 1, 0);
				rg.addText("\n", 0, 0, 0);

				int cnt = 0;// To count the filters selected
				if(!divName.equals("")) {
					cnt++;
				} // end of if statement
				if(!brnName.equals("")) {
					cnt++;
				} // end of if statement
				if(!deptName.equals("")) {
					cnt++;
				} // end of if statement
				if(!typeName.equals("")) {
					cnt++;
				} // end of if statement
				if(!payBillName.equals("")) {
					cnt++;
				} // end of if statement

				if(cnt > 0) {
					Object[][] filterObj = new Object[cnt][2];// Contains the
					// filters data
					// which will
					// appear on the
					// top table

					int fil = 0;

					if(!divName.equals("")) {
						filterObj[fil][0] = " Division : ";
						filterObj[fil][1] = divName;
						fil++;
					} // end of if statement
					if(!brnName.equals("")) {
						filterObj[fil][0] = "Branch : ";
						filterObj[fil][1] = brnName;
						fil++;
					} // end of if statement
					if(!deptName.equals("")) {
						filterObj[fil][0] = "Department : ";
						filterObj[fil][1] = deptName;
						fil++;
					} // end of if statement
					if(!typeName.equals("")) {
						filterObj[fil][0] = " Employee Type : ";
						filterObj[fil][1] = typeName;
						fil++;
					} // end of if statement
					if(!payBillName.equals("")) {
						filterObj[fil][0] = "PayBill : ";
						filterObj[fil][1] = payBillName;
						fil++;
					} // end of if statement
					rg.tableBodyNoBorder(filterObj, new int[]{8, 8}, new int[]{2, 0});
				} // end of if statement

				rg.addText("\n", 0, 0, 0);
				rg.tableBody(colNames, result, cellWidth, cellAln);
			} // end of if statement
			rg.createReport(response);// Creates a report
		} catch(Exception e) {
			logger.error("Exception in monthReport:" + e);
		} // end of try-catch block
	}
	
	/**
	 * Set filters to records which are coming from Attendance
	 */
	/**
	 * @param bean
	 * @param sqlQuery
	 * @return String as sql query
	 */
	public String setFiletrs(MonthAttendanceReport bean, String sqlQuery) {
		try {
			String divCode = bean.getDivCode();
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();

			if(!divCode.equals("")) {
				sqlQuery += " AND EMP_DIV = " + divCode;
			}
			if(!typeCode.equals("")) {
				sqlQuery += " AND EMP_TYPE = " + typeCode;
			}
			if(!payBillNo.equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
			}
			if(!brnCode.equals("")) {
				sqlQuery += " AND EMP_CENTER = " + brnCode;
			}
			if(!deptCode.equals("")) {
				sqlQuery += " AND EMP_DEPT = " + deptCode;
			}
			return sqlQuery;
		} catch(Exception e) {
			logger.error("Exception in setFiletrs:" + e);
			return "";
		} // end of try-catch block
	}
}