package org.paradyne.model.attendance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.attendance.DailyAttendanceReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

//import com.itextpdf.text.BaseColor;

/**
 * Bhushan Apr 1, 2008
 */

/**
 * Changes for New Report By Nilesh Dhandare on 15th Feb 2012.
 */

/*
 * To define a business logic for generating a report of daily attendance
 */

public class DailyAttendanceReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * Generates a daily attendance report
	 */

	/**
	 * @param bean
	 * @param request
	 * @param response
	 * @param reportPath
	 */
	public void dailyReport(DailyAttendanceReport bean,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		try {
			String fromDate = bean.getFromDate();
			String toDate = bean.getToDate();
			String divName = bean.getDivName();
			String brnName = bean.getBrnName();
			String deptName = bean.getDeptName();
			String payBillName = bean.getPayBillName();
			String typeName = bean.getTypeName();

			String fromYear = fromDate.substring(6, 10);
			String toYear = toDate.substring(6, 10);
			int diffYear = Integer.parseInt(toYear)
					- Integer.parseInt(fromYear);
			int fYear = Integer.parseInt(fromYear);
			int tYear = Integer.parseInt(toYear);

			/**
			 * Get attendance data within from date and to date
			 */
			Object[][] dailyAttendance = null;
			Object[][] showExtraWorkFlag = getAttendanceSettingObj();

			int length = (showExtraWorkFlag != null)&&(String.valueOf(showExtraWorkFlag[0][0]).equals("Y")) ? showExtraWorkFlag.length
					: 0;

			String extraWorkHoursColumn = "";
			if (length>0) {
				/*
				 * extraWorkHoursColumn=" CASE WHEN
				 * (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 + "+ "
				 * NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) > "+ "
				 * (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),1,2)),0)*60 + "+ "
				 * NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),4,5)),0))
				 * THEN "+ "
				 * FLOOR(((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 + "+ "
				 * NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) - "+ "
				 * (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),1,2)),0)*60 + "+ "
				 * NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),4,5)),0)))/60)
				 * ||':'|| "+ "
				 * MOD((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 + "+ "
				 * NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) - "+
				 * "(NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),1,2)),0)*60 + "+ "
				 * NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),4,5)),0)),60)
				 * ||':'||'00' "+ " ELSE NVL(TO_CHAR(ATT_EXTRAHRS,
				 * 'HH24:MI:SS'), '00:00:00') END AS EXTRA_HOURS,";
				 */
				/**
				 * If Flexi-time is Allowed then Extra Hours = if(Regularized
				 * Out time > Shift working hours ) then (Regularized out time -
				 * shift working hours ) else extra time = "00:00:00"
				 * 
				 * If Flexi-time is NOT - Allowed then Extra Hours =
				 * if(Regularized Out time > Shift End Time ) then (Regularized
				 * out time - Shift End Time ) else extra time = "00:00:00"
				 */
				extraWorkHoursColumn = " CASE WHEN NVL(SFT_FLEXITIME_ALLOWED,'N') ='Y' THEN  CASE WHEN "
						+ " ((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 +  "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) -  "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),1,2)),0)*60 +    "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),4,5)),0))  ) >     "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),1,2)),0)*60 +    "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),4,5)),0)) THEN   "
						+ " FLOOR((((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 +   "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) - "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),1,2)),0)*60 +   "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),4,5)),0)) ) -     "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),1,2)),0)*60 +   "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),4,5)),0)))/60) ||':'||   "
						+ " MOD((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 +  "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) -  "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),1,2)),0)*60 +  "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),4,5)),0)),60) ||':'||'00' "
						+ " ELSE NVL(TO_CHAR(ATT_EXTRAHRS, 'HH24:MI:SS'), '00:00:00') END   ELSE    "
						+ " CASE WHEN ((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 +   "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) - "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),1,2)),0)*60 +   "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),4,5)),0))) >  "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_END_TIME,'HH24:MI'),1,2)),0)*60 +  "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_END_TIME,'HH24:MI'),4,5)),0)) THEN   "
						+ " FLOOR((((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 +  "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0))  -  "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),1,2)),0)*60 +  "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),4,5)),0))) -  "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_END_TIME,'HH24:MI'),1,2)),0)*60 +  "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_END_TIME,'HH24:MI'),4,5)),0)))/60) ||':'||  "
						+ " MOD((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 +    "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) -   "
						+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_END_TIME,'HH24:MI'),1,2)),0)*60 +  "
						+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(SFT_END_TIME,'HH24:MI'),4,5)),0)),60) ||':'||'00'  "
						+ " ELSE NVL(TO_CHAR(ATT_EXTRAHRS, 'HH24:MI:SS'), '00:00:00') END   END AS EXTRA_HOURS ";				
			} else {				
				extraWorkHoursColumn = "";
			}
			try {
				String dailyAttendanceSql = " SELECT * FROM ( ";
				for (int i = 0; i <= diffYear; i++) {
					/*
					 * dailyAttendanceSql += " SELECT EMP_TOKEN, (EMP_FNAME||'
					 * '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME,NVL(DIV_NAME,'
					 * '),NVL(CENTER_NAME,' '), " + "
					 * TO_CHAR(ATT_DATE,'DD-MM-YYYY') ATT_DATE, SHIFT_NAME,
					 * NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI:SS'), '00:00:00')
					 * ATT_LOGIN, " + " NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI:SS'),
					 * '00:00:00') ATT_LOGOUT, NVL(TO_CHAR(ATT_WORKING_HRS,
					 * 'HH24:MI:SS'), '00:00:00') ATT_WORKING_HRS, " + "
					 * NVL(TO_CHAR(ATT_EXTRAHRS, 'HH24:MI:SS'), '00:00:00')
					 * ATT_EXTRAHRS, NVL(TO_CHAR(ATT_LATE_HRS, 'HH24:MI:SS'),
					 * '00:00:00') ATT_LATE_HRS, " + "
					 * NVL(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI:SS'), '00:00:00')
					 * ATT_EARLY_HRS, " + " DECODE(ATT_STATUS_ONE, 'PR',
					 * 'Present', 'WO', 'Week-Off', 'HO', 'Holiday', 'AB',
					 * 'Absent') STATUS1, " + " DECODE(ATT_STATUS_TWO, 'IN',
					 * 'In-Time', 'LC', 'Late-Coming', 'EL', 'Early-Leaving',
					 * 'DL', 'Dual-Late', 'HD', 'Half-Day', 'AB', 'Absent')
					 * STATUS2 " + // " 'AB', DECODE(ATT_STATUS_ONE, 'PR',
					 * 'Present', 'WO', 'Week-Off', 'HO', 'Holiday', 'AB',
					 * 'Absent')) STATUS2 " + " FROM HRMS_DAILY_ATTENDANCE_" +
					 * fYear + " INNER JOIN HRMS_EMP_OFFC
					 * ON(HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_" + fYear +
					 * ".ATT_EMP_ID) " + " INNER JOIN HRMS_SHIFT
					 * ON(HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_" + fYear +
					 * ".ATT_SHIFT_ID) " + " INNER JOIN HRMS_DIVISION
					 * ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) " + "
					 * INNER JOIN HRMS_CENTER
					 * ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) " + "
					 * WHERE EMP_STATUS = 'S' ";
					 */
					dailyAttendanceSql += " SELECT DISTINCT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME,NVL(DIV_NAME,' '),NVL(CENTER_NAME,' '),  "
							+ " TO_CHAR(ATT_DATE,'MM/DD/YYYY') ATT_DATE, SHIFT_NAME, CASE WHEN ( (ATT_REG_STATUS_SWIPE='NR' OR ATT_REG_STATUS_SWIPE='PS') AND (ATT_REG_STATUS_LATE='NR' OR ATT_REG_STATUS_LATE='PS' ) AND (ATT_REG_STATUS_PERTIME='NR' OR ATT_REG_STATUS_PERTIME='PS') ) THEN "
							+ " NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI:SS'), '00:00:00') ELSE NVL(TO_CHAR(ATT_REG_LOGIN, 'HH24:MI:SS'), '00:00:00') END AS ATT_LOGIN, "
							+ " CASE WHEN ( (ATT_REG_STATUS_SWIPE='NR' OR ATT_REG_STATUS_SWIPE='PS') AND (ATT_REG_STATUS_LATE='NR' OR ATT_REG_STATUS_LATE='PS' ) AND (ATT_REG_STATUS_PERTIME='NR' OR ATT_REG_STATUS_PERTIME='PS') ) THEN "
							+ " NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI:SS'), '00:00:00') ELSE NVL(TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI:SS'), '00:00:00') END AS ATT_LOGOUT, "
							+ " CASE WHEN ((ATT_REG_STATUS_SWIPE='NR' OR ATT_REG_STATUS_SWIPE='PS' ) AND "
							+ " (ATT_REG_STATUS_LATE='NR' OR ATT_REG_STATUS_LATE='PS') AND "
							+ " (ATT_REG_STATUS_PERTIME='NR' OR ATT_REG_STATUS_PERTIME='PS') )THEN "
							+ " NVL(TO_CHAR(ATT_WORKING_HRS, 'HH24:MI:SS'), '00:00:00') ELSE  "
							+ " FLOOR(((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 +  "
							+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) -  "
							+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),1,2)),0)*60 +  "
							+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),4,5)),0)))/60) ||':'|| "
							+ " MOD((NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),1,2)),0)*60 +  "
							+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),4,5)),0)) -  "
							+ " (NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),1,2)),0)*60 +  "
							+ " NVL(TO_NUMBER(SUBSTR(TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),4,5)),0)),60) ||':'||'00' "
							+ " END AS WORKING_HOURS ";
					if (length>0){
						dailyAttendanceSql += " ,"+ extraWorkHoursColumn;
					}	
					dailyAttendanceSql +=" , CASE  "
					+ " WHEN ATT_REG_STATUS_TWO='LV' THEN 'Leave'  "
					+ " WHEN ATT_REG_STATUS_TWO='TR' THEN 'Travel' "
					+ " WHEN ATT_REG_STATUS_TWO='RG' THEN 'Regularized' "
					+ " WHEN ATT_REG_STATUS_TWO='EW' THEN 'ExtraWork'  "
					+ " WHEN (ATT_STATUS_ONE='PR' AND ATT_STATUS_TWO='IN') THEN 'Present' "
					+ " WHEN ATT_STATUS_ONE='PR' THEN DECODE(ATT_STATUS_TWO,'HD','Half Day','DL','Dual Late','LC','Late-Coming','EL','Early-Leaving','PR','Present') "
					+ " ELSE DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','HO','Holiday')  "
					+ " END  NEW_STATUS, "
					+ " NVL(TO_CHAR(ATT_LATE_HRS, 'HH24:MI:SS'), '00:00:00') ATT_LATE_HRS, "
					+ " NVL(TO_CHAR(ATT_EARLY_HRS, 'HH24:MI:SS'), '00:00:00') ATT_EARLY_HRS  "
							
							/*+ " CASE WHEN ((ATT_REG_STATUS_SWIPE='NR' OR ATT_REG_STATUS_SWIPE='PS' OR ATT_REG_STATUS_SWIPE='RS') AND (ATT_REG_STATUS_LATE='NR' OR ATT_REG_STATUS_LATE='PS' OR ATT_REG_STATUS_LATE='RS') AND (ATT_REG_STATUS_PERTIME='NR' OR ATT_REG_STATUS_PERTIME='PS' OR ATT_REG_STATUS_PERTIME='RS') ) THEN "
							+ " DECODE(ATT_STATUS_TWO, 'IN', 'In-Time', 'LC', 'Late-Coming', 'EL', 'Early-Leaving', 'DL', 'Dual-Late', 'HD', 'Half-Day', 'AB', 'Absent') ELSE "
							+ " DECODE(ATT_REG_STATUS_TWO, 'IN', 'In-Time', 'LC', 'Late-Coming', 'EL', 'Early-Leaving', 'DL', 'Dual-Late', 'HD', 'Half-Day', 'AB', 'Absent') END AS REG_STATUS2" */
							+ " FROM HRMS_DAILY_ATTENDANCE_"
							+ fYear
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_"
							+ fYear
							+ ".ATT_EMP_ID) "
							+ " INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_"
							+ fYear
							+ ".ATT_SHIFT_ID) "
							+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
							+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
							+ " LEFT JOIN   HRMS_LEAVE_DTLHISTORY ON (HRMS_LEAVE_DTLHISTORY.EMP_ID=HRMS_DAILY_ATTENDANCE_"
							+ fYear
							+ ".ATT_EMP_ID AND "
							+ " HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE=HRMS_DAILY_ATTENDANCE_"
							+ fYear
							+ ".ATT_DATE) "
							+ " WHERE EMP_STATUS = 'S' ";
					dailyAttendanceSql = setFiletrs(bean, dailyAttendanceSql);
					if (fYear != tYear) {
						dailyAttendanceSql += " UNION ALL ";
						fYear++;
					} else {
						break;
					}
				} // end of for loop
				dailyAttendanceSql += " ) WHERE TO_DATE(ATT_DATE, 'MM/DD/YYYY') BETWEEN TO_DATE('"
						+ fromDate
						+ "', 'DD-MM-YYYY') "
						+ " AND TO_DATE('"
						+ toDate + "', 'DD-MM-YYYY') ";
				if (bean.getSrchStatus1() != null) {
					if (!bean.getSrchStatus1().trim().equals("All")
							&& !bean.getSrchStatus1().trim().equals("1")) {
						if (bean.getSrchStatus1().equals("Late")) {
							dailyAttendanceSql += " and NEW_STATUS in('Early-Leaving','Late-Coming') ";
						} else {
							dailyAttendanceSql += " and NEW_STATUS in('"
									+ bean.getSrchStatus1().trim() + "') ";
						}
					}
				}
				dailyAttendanceSql += "ORDER BY UPPER(EMP_NAME), TO_CHAR(TO_DATE(ATT_DATE, 'MM/DD/YYYY'), 'YYYY'), TO_CHAR(TO_DATE(ATT_DATE, 'MM/DD/YYYY'), 'MM'), "
						+ " TO_CHAR(TO_DATE(ATT_DATE, 'MM/DD/YYYY'), 'DD') ";

				dailyAttendance = getSqlModel().getSingleResult(
						dailyAttendanceSql);
			} catch (Exception e) {
				logger.error("Exception in dailyAttendanceSql:" + e);
			}
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReport();// Pdf/Xls/Doc
			rds.setReportType(type);
			String fileName = "Daily Attendance Report "
					+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("Daily Attendance Report");
			rds.setPageOrientation("landscape");
			rds.setTotalColumns(10);
			rds.setShowPageNo(true);
			rds.setUserEmpId(bean.getUserEmpId());
			// Report Generator Starts here
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			// Attachment Path Definition
			// String reportPath="";
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action",
						"DailyAttendanceReport_input.action");
				// Initial Page Action name
			}
			// Filters to be displayed
			String filters = "";
			if (bean.getDivName() != null && !bean.getDivName().equals("")) {
				filters += "\nDivision : " + bean.getDivName();
			}
			if (bean.getBrnName() != null && !bean.getBrnName().equals("")) {
				filters += "\n\nBranch : " + bean.getBrnName();
			}
			if (bean.getDeptName() != null && !bean.getDeptName().equals("")) {
				filters += "\n\nDepartment : " + bean.getDeptName();
			}
			if (bean.getPayBillName() != null
					&& !bean.getPayBillName().equals("")) {
				filters += "\n\nPaybill : " + bean.getPayBillName();
			}
			if (bean.getTypeName() != null && !bean.getTypeName().equals("")) {
				filters += "\n\nEmployee Type : " + bean.getTypeName();
			}
			if (bean.getFromDate() != null && !bean.getFromDate().equals("")) {
				filters += "\n\nFrom Date : " + bean.getFromDate();
			}
			if (bean.getToDate() != null && !bean.getToDate().equals("")) {
				filters += "\n\nTo Date : " + bean.getToDate();
			}
			if (bean.getEmpName() != null && !bean.getEmpName().equals("")) {
				filters += "\n\nEmployee Name : " + bean.getEmpName();
			}
			if (bean.getSrchStatus1() != null
					&& !bean.getSrchStatus1().equals("")) {
				if (bean.getSrchStatus1().trim().equals("1")) {
					filters += "";
				} else {
					filters += "\n\nStatus  : " + bean.getSrchStatus1();
				}
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setBodyFontStyle(0);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[] { 8 });
			filterData.setBlankRowsBelow(1);
			filterData.setCellNoWrap(new boolean[] { false });
			// filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6,
			// Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			// filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			/*
			 * int cnt = 0; // To count the filters selected
			 * if(!divName.equals("")) { cnt++; } if(!typeName.equals("")) {
			 * cnt++; } if(!payBillName.equals("")) { cnt++; }
			 * if(!brnName.equals("")) { cnt++; } if(!deptName.equals("")) {
			 * cnt++; }
			 * if(cnt > 0) { Object[][] filterObj = new Object[cnt][2];//
			 * Contains the filters data which will appear on the top table
			 * int fil = 0;
			 * if(!divName.equals("")) { filterObj[fil][0] = " Division : ";
			 * filterObj[fil][1] = divName; fil++; } if(!brnName.equals("")) {
			 * filterObj[fil][0] = "Branch : "; filterObj[fil][1] = brnName;
			 * fil++; } if(!deptName.equals("")) { filterObj[fil][0] =
			 * "Department : "; filterObj[fil][1] = deptName; fil++; }
			 * if(!typeName.equals("")) { filterObj[fil][0] = " Employee Type : ";
			 * filterObj[fil][1] = typeName; fil++; }
			 * if(!payBillName.equals("")) { filterObj[fil][0] = "PayBill : ";
			 * filterObj[fil][1] = payBillName; fil++; }
			 *  } // end of if statement
			 */
			/** Sets column name*/
			String colnames[] = new String[10 + length];
			colnames[0] = "EMPLOYEE ID";
			colnames[1] = "EMPLOYEE NAME";
			colnames[2] = "DIVISION";
			colnames[3] = "BRANCH";
			colnames[4] = "DATE";
			colnames[5] = "SHIFT";
			colnames[6] = "IN TIME";
			colnames[7] = "OUT TIME";
			colnames[8] = "WORKING HRS.";
			if(length>0)
			{
			colnames[9] = "EXTRA WORKING HRS.";
			colnames[10] = "STATUS";
			}
			else{
			colnames[9] = "STATUS";
			}
			int[] alignment = new int[colnames.length];
			int[] cellwidth = new int[colnames.length];
			boolean[] bcellwrap = new boolean[colnames.length];

			if (showExtraWorkFlag != null && showExtraWorkFlag.length > 0) {
				//if (String.valueOf(showExtraWorkFlag[0][0]).equals("Y")) {
				for (int i = 0; i < colnames.length; i++) {

					if (i == 0) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 1) {
						alignment[i] = 1;
						cellwidth[i] = 40;
						bcellwrap[i] = true;
					} else if (i == 2) {
						alignment[i] = 1;
						cellwidth[i] = 25;
						bcellwrap[i] = true;
					} else if (i == 3) {
						alignment[i] = 1;
						cellwidth[i] = 25;
						bcellwrap[i] = true;
					} else if (i == 4) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 5) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 6) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 7) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 8) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 9) {
						alignment[i] = 1;
						cellwidth[i] = 20;
						bcellwrap[i] = true;
					}
					else {
						alignment[i] = 1;
						cellwidth[i] = 20;
						bcellwrap[i] = true;
					}
				}
				//}// end of if
				if (dailyAttendance != null && dailyAttendance.length != 0) {
					TableDataSet tds = new TableDataSet();
					tds = new TableDataSet();
					tds.setHeader(colnames);
					tds.setCellAlignment(alignment);
					tds.setCellWidth(cellwidth);
					tds.setData(dailyAttendance);
					tds.setHeaderBorderDetail(3);
					tds.setCellNoWrap(bcellwrap);
					tds.setBorder(true);
					tds.setBorderDetail(3);
					tds.setHeaderTable(true);
					tds.setBlankRowsBelow(1);
					rg.addTableToDoc(tds);
				} else {
					Object[][] objData = new Object[1][1];
					objData[0][0] = "No records to display";
					TableDataSet tds = new TableDataSet();
					tds.setCellAlignment(new int[] { 1 });
					tds.setCellWidth(new int[] { 100 });
					tds.setData(objData);
					tds.setBorderDetail(0);
					tds.setCellColSpan(new int[] { 8 });
					tds.setBlankRowsBelow(1);
					tds.setBlankRowsAbove(1);
					rg.addTableToDoc(tds);

				}// end of else

				rg.process();
				if (reportPath.equals("")) {
					rg.createReport(response);
				} else {
					/* Generates the report as attachment */
					rg.saveReport(response);
				}
			}

		} catch (Exception e) {
			logger.error("Exception in dailyReport:" + e);
			e.printStackTrace();
		}
	}

	private Object[][] getAttendanceSettingObj() {
		Object[][] getAttendanceSettingObj = null;
		try {
			String query = " SELECT CONF_SHOW_EXTRAWORK FROM HRMS_ATTENDANCE_CONF ";
			getAttendanceSettingObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAttendanceSettingObj;
	}

	/**
	 * Set filters to records which are coming from Attendance
	 */
	/**
	 * @param bean
	 * @param sqlQuery
	 * @return String as sql query
	 */
	public String setFiletrs(DailyAttendanceReport bean, String sqlQuery) {
		try {
			String divId = bean.getDivCode();
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String empID = bean.getEmpCode();
			String srchStatus1 = bean.getSrchStatus1();
			String srchStatus2 = bean.getSrchStatus2();
			if (!divId.equals("")) {
				sqlQuery += " AND EMP_DIV IN (" + divId + ")";
			}
			if (!typeCode.equals("")) {
				sqlQuery += " AND EMP_TYPE IN (" + typeCode + ")";
			}
			if (!payBillNo.equals("")) {
				sqlQuery += " AND EMP_PAYBILL IN (" + payBillNo + ")";
			}
			if (!brnCode.equals("")) {
				sqlQuery += " AND EMP_CENTER IN (" + brnCode + ")";
			}
			if (!deptCode.equals("")) {
				sqlQuery += " AND EMP_DEPT  IN (" + deptCode + ")";
			}
			if (!empID.equals("")) {
				sqlQuery += " AND HRMS_EMP_OFFC.EMP_ID = " + empID;
			}
			/*if (!srchStatus1.equals("AL")) {				
			   sqlQuery += " AND ATT_STATUS_ONE = '" + srchStatus1 + "'";					
			}
			 */
			/*if (!srchStatus1.equals("AL")) {
				if(srchStatus1.equals("Select")){								
				}else {
					sqlQuery += " AND ATT_STATUS_ONE = '" + srchStatus1 + "'";
					}							
			}*/
			return sqlQuery;
		} catch (Exception e) {
			logger.error("Exception in setFiletrs:" + e);
			e.printStackTrace();
			return "";
		} // end of try-catch block
	}

	/**
	 * Sets employee name when page loads, if login user is general
	 */
	/**
	 * @param bean
	 */
	public void setEmployee(DailyAttendanceReport bean) {
		try {
			String eId = bean.getUserEmpId();

			String empSql = " SELECT DISTINCT EMP_ID, EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME, "
					+ "  HRMS_DIVISION.DIV_ID, HRMS_DIVISION.DIV_NAME "
					+ " FROM HRMS_EMP_OFFC "
					+ "  INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + eId;
			Object[][] empObj = getSqlModel().getSingleResult(empSql);

			bean.setEmpCode(String.valueOf(empObj[0][0]));
			bean.setEmpName(String.valueOf(empObj[0][1]));
			bean.setDivCode(String.valueOf(empObj[0][2]));
			bean.setDivName(String.valueOf(empObj[0][3]));
		} catch (Exception e) {
			logger.error("Exception in setEmployee:" + e);
		} // end of try-catch block
	}
	
	
public void dailyAttendanceReport(DailyAttendanceReport bean,
			HttpServletRequest request, HttpServletResponse response,
			String reportPath) {
		try {	
			Date loginTimeInDate = null, logoutTimeInDate = null;
			SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm");
			String fromDate = bean.getFromDate();
			String toDate = bean.getToDate();
			String fromYear = fromDate.substring(6, 10);
			String toYear = toDate.substring(6, 10);
			int diffYear = Integer.parseInt(toYear)
					- Integer.parseInt(fromYear);
			int fYear = Integer.parseInt(fromYear);
			int tYear = Integer.parseInt(toYear);

			/**
			 * Get attendance data within from date and to date
			 */
			Object[][] dailyAttendance = null;
			Object[][] showExtraWorkFlag = getAttendanceSettingObj();
			/*int length = (showExtraWorkFlag != null)&&(String.valueOf(showExtraWorkFlag[0][0]).equals("Y")) ? showExtraWorkFlag.length
					: 0;
			String extraWorkHoursColumn = "";
			if (length>0) {
				//query for calculating extra working hrs from WORK DURATION and SHIFT WORKING HRS
				// check whether WORKDURATION is 00:00 or not if not then calculate EXTRAWORK else return 00:00
				// WORKDURATION will be 00:00 only if one of the value from INTIME or OUTIME is 00:00,or if status is absent 
				extraWorkHoursColumn= "CASE WHEN WORKDURATION !='00:00' THEN "   
					+ " CASE WHEN (TRIM(TO_CHAR(TRUNC((TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),1,2))*60 +"        
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),4,5)) -  "//check whether HRS value is 00 or not
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),1,2))*60 +   "
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),4,5)))/60 ),'00'))='00') AND" 
					+ " (TO_NUMBER(TO_CHAR(MOD((TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),1,2))*60 +"   
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),4,5)) - "
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),1,2))*60 +   " // check whether MINUTE value is in negative or not
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),4,5))),60 ),'00'))<0)"
					// code for calculating HRS value of ExtraWorking hrs 
					+ " THEN '-'||trim(TO_CHAR(TRUNC((TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),1,2))*60 +"        
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),4,5)) -  "// if MINUTES value is negative then add -(minus sign) before HRS value
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),1,2))*60 +   "
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),4,5)))/60 ),'00'))"
					+ " ELSE"
					+ " TO_CHAR(TRUNC((TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),1,2))*60 +"        
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),4,5)) -  "// if MINUTES value is not negative then simply calculate the HRS value
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),1,2))*60 +   "
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),4,5)))/60 ),'00')"
					+ " END       "
					+ " || ':'||"
					// code for calculating MINUTES value of ExtraWorking hrs
					+ " TRIM(TO_CHAR(abs(MOD((TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),1,2))*60 +"   
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORKDURATION,'HH24:MI'),'HH24:MI'),4,5)) - "
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),1,2))*60 +   "
					+ " TO_NUMBER(SUBSTR(TO_CHAR(TO_DATE(WORK_HRS,'HH24:MI'),'HH24:MI'),4,5))),60 ) ),'00'))"
					// set EXTRA_WORKING_HRS as 00:00 if WORKDURATION is 00:00 
					+ " ELSE '00:00' END"
					+ " EXTRA_WORKING_HRS ";				
			} else {
				extraWorkHoursColumn = "";
			}*/

			try {
				String dailyAttendanceSql = " SELECT EMP_TOKEN,EMP_NAME,DIVISION,BRANCH,ATT_DATE,SHIFT_NAME,INTIME, OUTTIME,"
					+ " WORKDURATION, NEW_STATUS, APPLICATION_STATUS FROM( ";
					
				/*	if (length>0){
						dailyAttendanceSql += " ,"+ extraWorkHoursColumn;
					}								
			  dailyAttendanceSql+= " ,NEW_STATUS " 
					+ " FROM( ";*/
				for (int i = 0; i <= diffYear; i++) {					
				dailyAttendanceSql += "	SELECT DISTINCT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, "
					+ " NVL(DIV_NAME,' ') DIVISION,NVL(CENTER_NAME,' ') BRANCH,SHIFT_NAME, "
					+ " TO_CHAR(ATT_DATE,'MM/DD/YYYY') ATT_DATE,TO_CHAR(ATT_LOGIN,'HH24:MI') A_IN,TO_CHAR(ATT_LOGOUT, 'HH24:MI') A_OUT, "
					+ " TO_CHAR(ATT_REG_LOGIN, 'HH24:MI') RE_IN,TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI') RE_OUT, "
					// query for calculating INTIME
					+ " CASE WHEN (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL)  "	 	
					+ "	AND TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')< TO_CHAR(ATT_LOGIN, 'HH24:MI')	 "
					+ "	THEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')	   "
					+ "	WHEN (TO_CHAR(ATT_LOGIN, 'HH24:MI')='00:00' OR ATT_LOGIN IS NULL) "   
					+ "	AND  (TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00' OR ATT_REG_LOGIN IS NOT NULL) " 
					+ "	THEN  NVL(TO_CHAR(ATT_REG_LOGIN, 'HH24:MI'),'00:00')  	  "
					+ "	ELSE NVL(TO_CHAR(ATT_LOGIN, 'HH24:MI'),'00:00')  END INTIME, "//INTIME ends here
					// query for calculating OUTTIME
					+ "	CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) " 	 
					+ "	THEN (  "
					+ "	CASE WHEN TO_char(ATT_REG_LOGOUT, 'HH24:MI')>NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00') "
					+ "	THEN TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI') ELSE TO_CHAR(ATT_LOGOUT, 'HH24:MI') END "
					+ "	 ) "
					+ "	WHEN (TO_CHAR(ATT_LOGOUT, 'HH24:MI')='00:00' OR ATT_LOGOUT IS NULL) "   
					+ "	THEN  NVL(TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI'),'00:00')  	  "
					+ "	ELSE NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')  END OUTTIME, "//OUTTIME ends here
					// Query for calculating WORKDURATION begins here
					+ " CASE WHEN (TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')!='00:00' OR ATT_REG_LOGOUT IS NOT NULL) " //check whether attendance has been regularized or not 
					+ " AND TO_CHAR(ATT_REG_LOGOUT, 'HH24:MI')> NVL(TO_CHAR(ATT_LOGOUT, 'HH24:MI'),'00:00')   "// and regularized logout time is greater than logout time
					+ " THEN   TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- "// if yes then select regularized logout time 
					+ " ( "									//for subtraction from login time to get WORKDURATION
					+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"// select least login time 
					+ " AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN " 
					+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') " 
					+ " ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "//from regularized and in time
					+ " ))*24),'00')|| ':' || "// convert it in hour and concat it with second
					+ " TO_CHAR(MOD(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')- " 
					+ " CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' THEN "//calculate second value
					+ "  LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
					+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') )"
					+ " ELSE ATT_REG_LOGIN END)*24*60),60),'00') "// from least login time 
					+ " ELSE  "// if regularized logout time is less than logout time then
					+ " (CASE WHEN TO_CHAR(ATT_REG_LOGIN, 'HH24:MI')!='00:00'"// check for null of regularized login time
					+ " THEN (  TO_CHAR(FLOOR((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI')-"
					+ " ("// if regularized login time is not null then 
					+ " CASE WHEN  TO_char(ATT_REG_LOGIN, 'HH24:MI')>TO_CHAR(ATT_LOGIN, 'HH24:MI')"// select least login time
					+ " THEN TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ELSE TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') END "
					+ " ) )*24),'00')"// convert it in hour
					+ " || ':' || "
					+ " TO_CHAR(MOD(FLOOR((GREATEST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"//Second value calculation
					+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGOUT,'HH24:MI'),'DD-MM-YYYYHH24:MI'))-CASE WHEN TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00' "
					+ " THEN LEAST(TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_REG_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI'),"
					+ " TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY')||TO_CHAR(ATT_LOGIN,'HH24:MI'),'DD-MM-YYYYHH24:MI') ) ELSE ATT_REG_LOGIN END)*24*60),60),'00')"
					+ " )  ELSE  (CASE  WHEN TO_CHAR(ATT_LOGOUT, 'HH24:MI')!='00:00' AND TO_CHAR(ATT_LOGIN, 'HH24:MI')!='00:00'"// If attendance is not regularized then
					+ " THEN (NVL(TO_CHAR(FLOOR((ATT_LOGOUT - ATT_LOGIN)*24),'00'),'00')|| ':' ||" // Working hours
					+ " NVL( TO_CHAR(MOD(FLOOR((ATT_LOGOUT-ATT_LOGIN)*24*60),60),'00'),'00') "// Working minutes
					+ " )"
					+ " ELSE '00:00' END)END)"
					+ " END WORKDURATION, "//WORKDURATION ends here
					+ " TO_CHAR(SFT_WORK_HRS,'HH24:MI') WORK_HRS," 
				    + " CASE WHEN ATT_REG_STATUS_TWO='LV' then 'Leave'"    
				    + " WHEN ATT_REG_STATUS_TWO='TR' THEN 'Travel'"    
				    + " WHEN ATT_REG_STATUS_TWO='RG' THEN 'Regularized'"    
				    + " WHEN ATT_REG_STATUS_TWO='EW' THEN 'ExtraWork'" 
					+ " WHEN ATT_REG_STATUS_TWO='HL' THEN 'Half Day Leave'"  
				    + " WHEN (ATT_STATUS_ONE='PR' and ATT_STATUS_TWO='IN') THEN 'Present'"   
				    + " WHEN ATT_STATUS_ONE='PR' then DECODE(ATT_STATUS_TWO,'HD','Half Day','DL'," 
				    + " 'Dual Late','LC','Late-Coming','EL','Early-Leaving','PR','Present','AB','Absent')"  
				    + " ELSE DECODE(ATT_STATUS_ONE,'AB','Absent','WO','Weekly Off','HO','Holiday')"   
				    + " END  NEW_STATUS,"   
				    + " DECODE(IS_APPL_PROCESS,'Y','In Process','N','') APPLICATION_STATUS"
					+ " FROM HRMS_DAILY_ATTENDANCE_"+fYear
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = " 
					+ " HRMS_DAILY_ATTENDANCE_"+ fYear+ ".ATT_EMP_ID) "
					+ " INNER JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID =" 
					+ " HRMS_DAILY_ATTENDANCE_"+ fYear+ ".ATT_SHIFT_ID) "
					+ " INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN   HRMS_LEAVE_DTLHISTORY ON (HRMS_LEAVE_DTLHISTORY.EMP_ID=" 
					+ " HRMS_DAILY_ATTENDANCE_"+ fYear+ ".ATT_EMP_ID AND "
					+ " HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE=" 
					+ " HRMS_DAILY_ATTENDANCE_"+ fYear+ ".ATT_DATE) "
					+ " WHERE EMP_STATUS = 'S' ";
					dailyAttendanceSql = setFiletrs(bean, dailyAttendanceSql);
					if (fYear != tYear) {
						dailyAttendanceSql += " UNION ALL ";
						fYear++;
					} else {
						break;
					}
				} // end of for loop
		         dailyAttendanceSql += " ) WHERE TO_DATE(ATT_DATE, 'MM/DD/YYYY') " +
		         		" BETWEEN TO_DATE('"+ fromDate+ "', 'DD-MM-YYYY') "
						+ " AND TO_DATE('"+ toDate + "', 'DD-MM-YYYY') ";
				if (bean.getSrchStatus1() != null) {
					if (!bean.getSrchStatus1().trim().equals("All")
							&& !bean.getSrchStatus1().trim().equals("1")) {
						if (bean.getSrchStatus1().equals("Late")) {
							dailyAttendanceSql += " AND NEW_STATUS IN('Early-Leaving','Late-Coming','Dual Late') ";
						} else {
							dailyAttendanceSql += " AND NEW_STATUS IN('"
									+ bean.getSrchStatus1().trim() + "') ";
						}
					}
				}
				dailyAttendanceSql += "ORDER BY UPPER(EMP_NAME), TO_CHAR(TO_DATE(ATT_DATE, 'MM/DD/YYYY'), 'YYYY')," 
						+ " TO_CHAR(TO_DATE(ATT_DATE, 'MM/DD/YYYY'), 'MM'), "
						+ " TO_CHAR(TO_DATE(ATT_DATE, 'MM/DD/YYYY'), 'DD') ";
				dailyAttendance = getSqlModel().getSingleResult(
						dailyAttendanceSql);
				if(dailyAttendance != null && dailyAttendance.length>0){
					for (int i = 0; i < dailyAttendance.length; i++) {
					String inTime = String.valueOf(dailyAttendance[i][6]);
					String outTime = String.valueOf(dailyAttendance[i][7]);
					loginTimeInDate = simpleDate.parse(inTime);
					logoutTimeInDate = simpleDate.parse(outTime);
					Calendar workingHrsCal = Calendar.getInstance();
					workingHrsCal.setTime(logoutTimeInDate);
					workingHrsCal.add(Calendar.HOUR, -loginTimeInDate
							.getHours());
					workingHrsCal.add(Calendar.MINUTE, -loginTimeInDate
							.getMinutes());
					workingHrsCal.add(Calendar.SECOND, -loginTimeInDate
							.getSeconds());
					if(inTime.equals("00:00") && (!(outTime.equals("00:00")))){
						dailyAttendance[i][8] ="00:00";
					}else if((!(inTime.equals("00:00")))&& outTime.equals("00:00")){
						dailyAttendance[i][8] ="00:00";
					}else if(inTime.equals(outTime)){
						dailyAttendance[i][8] ="00:00";
					}
					else{
						dailyAttendance[i][8] = simpleDate.format(workingHrsCal
								.getTime());
					}
				}
			
				}
			} catch (Exception e) {
				logger.error("Exception in dailyAttendanceSql:" + e);
			}
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReport();// Pdf/Xls/Doc
			rds.setReportType(type);
			String fileName = "Daily Attendance Report "
					+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("Daily Attendance Report");
			rds.setPageOrientation("landscape");
			rds.setTotalColumns(11);
			rds.setShowPageNo(true);
			rds.setUserEmpId(bean.getUserEmpId());
			// Report Generator Starts here
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			// Attachment Path Definition
			// String reportPath="";
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action",
						"DailyAttendanceReport_input.action");

				// Initial Page Action name
			}
			// Filters to be displayed
			String filters = "";
			if (bean.getDivName() != null && !bean.getDivName().equals("")) {
				filters += "\nDivision : " + bean.getDivName();
			}
			if (bean.getBrnName() != null && !bean.getBrnName().equals("")) {

				filters += "\n\nBranch : " + bean.getBrnName();
			}
			if (bean.getDeptName() != null && !bean.getDeptName().equals("")) {

				filters += "\n\nDepartment : " + bean.getDeptName();
			}
			if (bean.getPayBillName() != null
					&& !bean.getPayBillName().equals("")) {
				filters += "\n\nPaybill : " + bean.getPayBillName();
			}
			if (bean.getTypeName() != null && !bean.getTypeName().equals("")) {
				filters += "\n\nEmployee Type : " + bean.getTypeName();
			}
			if (bean.getFromDate() != null && !bean.getFromDate().equals("")) {
				filters += "\n\nFrom Date : " + bean.getFromDate();
			}
			if (bean.getToDate() != null && !bean.getToDate().equals("")) {
				filters += "\n\nTo Date : " + bean.getToDate();
			}
			if (bean.getEmpName() != null && !bean.getEmpName().equals("")) {
				filters += "\n\nEmployee Name : " + bean.getEmpName();
			}
			if (bean.getSrchStatus1() != null
					&& !bean.getSrchStatus1().equals("")) {
				if (bean.getSrchStatus1().trim().equals("1")) {
					filters += "";
				} else {

					filters += "\n\nStatus  : " + bean.getSrchStatus1();
				}
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setBodyFontStyle(0);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[] { 8 });
			filterData.setBlankRowsBelow(1);
			filterData.setCellNoWrap(new boolean[] { false });
			filterData.setBorder(false);
			rg.addTableToDoc(filterData);

			/** Sets column name*/
			//String colnames[] = new String[10 + length];
			String colnames[] = new String[11];
			colnames[0] = "EMPLOYEE ID";
			colnames[1] = "EMPLOYEE NAME";
			colnames[2] = "DIVISION";
			colnames[3] = "BRANCH";
			colnames[4] = "DATE";
			colnames[5] = "SHIFT";
			colnames[6] = "IN TIME";
			colnames[7] = "OUT TIME";
			colnames[8] = "WORKING HRS.";
			colnames[9] = "STATUS";
			colnames[10]= "APPLICATION STATUS";
			/*if(length>0)
			{
			//colnames[9] = "EXTRA WORKING HRS.";
			colnames[9] = "STATUS";
			}
			else{
			colnames[9] = "STATUS";
			}*/
			int[] alignment = new int[colnames.length];
			int[] cellwidth = new int[colnames.length];
			boolean[] bcellwrap = new boolean[colnames.length];
			//if (showExtraWorkFlag != null && showExtraWorkFlag.length > 0) {					
					System.out.println("showExtraWorkFlag--"+String.valueOf(showExtraWorkFlag[0][0]));
				for (int i = 0; i < colnames.length; i++) {
					if (i == 0) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 1) {
						alignment[i] = 1;
						cellwidth[i] = 40;
						bcellwrap[i] = true;
					} else if (i == 2) {
						alignment[i] = 1;
						cellwidth[i] = 25;
						bcellwrap[i] = true;
					} else if (i == 3) {
						alignment[i] = 1;
						cellwidth[i] = 25;
						bcellwrap[i] = true;
					} else if (i == 4) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 5) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 6) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 7) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 8) {
						alignment[i] = 1;
						cellwidth[i] = 15;
						bcellwrap[i] = true;
					} else if (i == 9) {
						alignment[i] = 1;
						cellwidth[i] = 20;
						bcellwrap[i] = true;
					}else if (i == 10) {
						alignment[i] = 1;
						cellwidth[i] = 20;
						bcellwrap[i] = true;
					}
					else {
						alignment[i] = 1;
						cellwidth[i] = 20;
						bcellwrap[i] = true;
					}
				}
				//}// end of if
				if (dailyAttendance != null && dailyAttendance.length != 0) {
					TableDataSet tds = new TableDataSet();
					tds = new TableDataSet();
					tds.setHeader(colnames);
					tds.setCellAlignment(alignment);
					tds.setCellWidth(cellwidth);
					tds.setData(dailyAttendance);
					tds.setHeaderBorderDetail(3);
					tds.setCellNoWrap(bcellwrap);
					tds.setBorder(true);
					tds.setBorderDetail(3);
					tds.setHeaderTable(true);
					tds.setBlankRowsBelow(1);
					rg.addTableToDoc(tds);
				} else {
					Object[][] objData = new Object[1][1];
					objData[0][0] = "No records to display";
					TableDataSet tds = new TableDataSet();
					tds.setCellAlignment(new int[] { 1 });
					tds.setCellWidth(new int[] { 100 });
					tds.setData(objData);
					tds.setBorderDetail(0);
					tds.setCellColSpan(new int[] { 8 });
					tds.setBlankRowsBelow(1);
					tds.setBlankRowsAbove(1);
					rg.addTableToDoc(tds);
				}// end of else
				rg.process();
				if (reportPath.equals("")) {
					rg.createReport(response);
				} else {
					/* Generates the report as attachment */
					rg.saveReport(response);
				}
				System.out.println("showExtraWorkFlag Done--"+String.valueOf(showExtraWorkFlag[0][0]));
		} catch (Exception e) {
			logger.error("Exception in dailyReport:" + e);
			e.printStackTrace();
		}
	}
}