package org.paradyne.model.attendance;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import org.paradyne.bean.attendance.MonthAttendance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import com.crystaldecisions.sdk.prompting.report.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bhushan Dasare
 * @date Jun 23, 2008
**/

/**
 * To define a business logic for processing the monthly attendance
**/

public class MonthAttendanceModel extends ModelBase
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MonthAttendanceModel.class);
	
	/** 
	 * Add new employee 
	**/	
	/**
	 * @param bean
	 * @param request
	 * @param response
	 * @return a message whether an employee is added successfully or not
	**/
	public String addEmp(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		String res = "";
		try
		{
			String month = Integer.parseInt(bean.getMonth()) < 10 ? "0" + bean.getMonth() : bean.getMonth();
			String year = bean.getYear();
			String newECode = bean.getNewECode(); // Emp Code of an new employee
			String newEName = bean.getNewEName(); // Emp Name of an new employee
			String newJoinDate = bean.getNewJoinDate(); // Join Date of an new employee
			String attdnCode = bean.getAttdnCode();
			boolean attnConnFlag = bean.isAttConnFlag();
			boolean lockFlag = bean.isLockFlag();
			boolean brnHDayFlag = bean.isBrnHDayFlag();
			boolean levConnFlag = bean.isLevConnFlag();
			
			String fromDate = "";
			String monthFlag = "", startDate = "";
			boolean empExists = false;
			
			String fDate = "01-" + month + "-" + year; 
			int monthDays = getMonthDays(month, year); // Calculate total days of a month
			String toDate = monthDays + "-" + month + "-" + year;
			
			// Get list of employees containing saved as well as non saved employees
			Object[][] eListObj = getEmpIDs(bean, toDate, attdnCode, year, lockFlag);
			
			if(eListObj != null || eListObj.length < 1)
			{
				for(int i = 0; i < eListObj.length; i++)
				{
					/* Attendance is already available of an employee, then don't allow to add an employee */
					if(newECode.equals(String.valueOf(eListObj[i][0])) && String.valueOf(eListObj[i][4]).equals("SAVE"))
					{
						res = "Attendance is already added for " + newEName + "!";
						empExists = true;
						break;
					}
				} //end of for loop
			} //end of if statement
			
			if(!empExists) //Calculate the attendance of new employee and save it.
			{
				if(attnConnFlag) //Daily Attendance is connected
				{
					monthDays = getMonthDays(month, year); //Calculate total days of a month
					
					String monthDateQuery = " SELECT CONF_SALARY_START_DATE, CONF_SALARY_START_FLAG FROM HRMS_ATTENDANCE_CONF ";			
					Object[][] dateFlagObject = getSqlModel().getSingleResult(monthDateQuery);

					startDate = String.valueOf(dateFlagObject[0][0]);
					startDate = Integer.parseInt(startDate) < 10 ? "0" + startDate : startDate;
					monthFlag = String.valueOf(dateFlagObject[0][1]);

					String newMonth = "";
					String newYear = "";							

					if(monthFlag.equals("P"))
					{
						if(month.equals("01"))
						{
							newMonth = "12";
							newYear = String.valueOf(Integer.parseInt(year) - 1);
						}
						else
						{
							newMonth = String.valueOf(Integer.parseInt(month) - 1);
							newMonth = Integer.parseInt(newMonth) < 10 ? "0" + newMonth : newMonth;
							newYear = year;
						}
						fDate = startDate + "-" + newMonth + "-" + newYear;
						toDate = startDate + "-" + month + "-" + year;
					} //end of if statement
				} //end of if statement
				
				fromDate = getFromDate(newJoinDate, fDate);
				
				// Calculate the attendance of an employee to be added
				Object[][] attdnObj = calculateAttdn(brnHDayFlag, levConnFlag, attnConnFlag, monthFlag, month, year, newECode, fromDate, toDate, false, attdnCode, "", "");
				
				Object[][] saveAttnObj = new Object[1][11];
				saveAttnObj[0][0] = attdnCode;
				saveAttnObj[0][1] = newECode;
				saveAttnObj[0][2] = String.valueOf(attdnObj[0][0]); //ATTN_DAYS
				saveAttnObj[0][3] = String.valueOf(attdnObj[0][1]); //ATTN_WOFF
				saveAttnObj[0][4] = String.valueOf(attdnObj[0][2]); //ATTN_HOLIDAY
				saveAttnObj[0][5] = String.valueOf(attdnObj[0][3]); //ATTN_PAID_LEVS
				saveAttnObj[0][6] = String.valueOf(attdnObj[0][4]); //ATTN_UNPAID_LEVS
				saveAttnObj[0][7] = String.valueOf(attdnObj[0][5]); //ATTN_SAL_DAYS
				saveAttnObj[0][8] = String.valueOf(attdnObj[0][6]); //ATTN_LATE_MARKS 
				saveAttnObj[0][9] = String.valueOf(attdnObj[0][7]); //ATTN_HALF_DAYS
				saveAttnObj[0][10] = String.valueOf(attdnObj[0][10]); //RECOVERY_DAYS
				
				
				String saveAttnSql = " INSERT INTO HRMS_MONTH_ATTENDANCE_" + year +
				" (ATTN_CODE, ATTN_EMP_ID, ATTN_DAYS, ATTN_WOFF, ATTN_HOLIDAY, ATTN_PAID_LEVS, " +
				" ATTN_UNPAID_LEVS, ATTN_SAL_DAYS, ATTN_LATE_MARKS, ATTN_HALF_DAYS, ATTN_RECAL,ATTN_RECOVERY_DAYS) " +
				" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'N',? ) ";
				getSqlModel().singleExecute(saveAttnSql, saveAttnObj);
				
				/* Add default leave details for an employee to be added */
				String attnDtlSql = " SELECT DISTINCT HRMS_LEAVE_POLICY.LEAVE_CODE, HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE, " +
				" SUM(NVL(LEAVE_DAYS, 0)) LEAVE_DAYS, NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE - SUM(NVL(LEAVE_DAYS, 0)), 0) LEAVES_AVAILABLE " +
				" FROM HRMS_LEAVE_DTLHISTORY " +
				" RIGHT JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE_POLICY.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE " +
				" AND HRMS_LEAVE_POLICY.EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID = " + newECode + ") " +
				" AND LEAVE_APPLICABLE = 'Y' AND HRMS_LEAVE_POLICY.LEAVE_PAID_UNPAID = 'P') " +
				" INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE = HRMS_LEAVE_POLICY.LEAVE_CODE " +
				" AND HRMS_LEAVE_POLICY.EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID = " + newECode + ") " +
				" AND HRMS_LEAVE_BALANCE.EMP_ID = " + newECode + ") " +
				" WHERE LEAVE_DTL_STATUS = 'A' AND HRMS_LEAVE_DTLHISTORY.EMP_ID = " + newECode + " AND TO_CHAR(LEAVE_FROM_DATE, 'MM') = " + month + 
				" AND TO_CHAR(LEAVE_FROM_DATE, 'YYYY') = " + year + " AND TO_CHAR(LEAVE_TO_DATE, 'MM') = " + month + 
				" AND TO_CHAR(LEAVE_TO_DATE, 'YYYY') = " + year +
				" GROUP BY HRMS_LEAVE_POLICY.LEAVE_CODE,HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE ";
				Object[][] attnDtlObj = getSqlModel().getSingleResult(attnDtlSql);//Select relevant leave details
				
				if(attnDtlObj != null && attnDtlObj.length > 0)
				{
					Object[][] attndtlSave = new Object[attnDtlObj.length][6];
					for(int j = 0; j < attnDtlObj.length; j++)
					{
						attndtlSave[j][0] = newECode;
						attndtlSave[j][1] = String.valueOf(attnDtlObj[j][0]); //ATT_LEAVE_CODE
						attndtlSave[j][2] = String.valueOf(attnDtlObj[j][1]); //ATT_LEAVE_AVAILABLE
						attndtlSave[j][3] = String.valueOf(attnDtlObj[j][2]); //ATT_LEAVE_ADJUST
						attndtlSave[j][4] = String.valueOf(attnDtlObj[j][3]); //ATT_LEAVE_BALANCE
						attndtlSave[j][5] = newECode;
					} //end of for loop
					String attnDtlSaveSql = " INSERT INTO HRMS_MONTH_ATT_DTL_" + year + 
					" (ATT_EMP_CODE, ATT_LEAVE_CODE, ATT_LEAVE_AVAILABLE, ATT_LEAVE_ADJUST, ATT_LEAVE_BALANCE, ATT_CODE) " + 
					" VALUES(?, ?, ?, ?, ?, ?) ";
					
					getSqlModel().singleExecute(attnDtlSaveSql, attndtlSave);
				} //end of if statement
				res = "Employee Added Successfully!";
			} //end of if statement
			
			/**
			 * If Attendance is locked, then it shows saved attendance. If not, it precesses the attendance.
			**/
			if(bean.getStatus().trim().equals("ATTN_START"))
			{
				if(bean.isAttConnFlag())
				{
					connectedAttn(bean, request);
				}
				else
				{
					processedAttn(bean, request, response);
				}
			} //end of if statement
			else
			{
				showAttendance(bean, request); //Set saved records of attendance in a page
			}
			
			bean.setFlag(true);
			bean.setNewECode("");
			bean.setNewEToken("");
			bean.setNewEName("");
		}
		catch (Exception e)
		{
			logger.error(e);
			bean.setFlag(false);
			res = "Employee can't be added!";
		} //end of try-catch block
		return res;
	} //end of addEmp
	
	/**
	 * Adjust half days against leaves assigned
	**/
	/**
	 * @param levDtl -: Specifies the approved leave days and remaining balances after adjusting late marks, if any
	 * @param halfDayLevs -: Specifies the leave types going to adjust with half days
	 * @param halfDays -: Specifies the number of half days
	 * @param bean -: Specifies MonthAttendance object
	 * @return Object[][] containing new leave days and leave balances including adjusted half days
	**/
	public Object[][] adjustHalfDays(Object[][] levDtl, String[] halfDayLevs, double halfDays, MonthAttendance bean)
	{
		Object[][] levHalfAdjObj = null;
		try
		{
			double paidHD = bean.getUnpaidLM();
			double unpaidHD = bean.getUnpaidHD();
			double halfDay = Double.parseDouble(String.valueOf(halfDays)) / 2; //Converts half days into days
			
			String levCode = "";
			String levBal = "";
			String levDays = "";
			String lmAdj = "";
			double hdAdj = 0;
			double levAval = 0;
			
			levHalfAdjObj = levDtl;			

			for(int j = 0; j < halfDayLevs.length; j++)
			{
				String hdLev = String.valueOf(halfDayLevs[j]);
				
				for(int i = 0; i < levHalfAdjObj.length; i++)
				{
					levCode = String.valueOf(levHalfAdjObj[i][0]);
					levBal = String.valueOf(levHalfAdjObj[i][1]);
					levDays = String.valueOf(levHalfAdjObj[i][2]);
					lmAdj = String.valueOf(levHalfAdjObj[i][3]);
					hdAdj = Double.parseDouble(String.valueOf(levHalfAdjObj[i][4]));
					levAval = Double.parseDouble(String.valueOf(levHalfAdjObj[i][5]));
					
					if(hdLev.equals(levCode))
					{
						if(halfDay > 0)
						{
							//Total half days are adjusted in available leave balance
							if(halfDay <= levAval)
							{
								levAval -= halfDay;
								hdAdj += halfDay;
								paidHD += halfDay;
								halfDay = 0;
							} //end of if statement
							else //Part of half days are adjusted in available leave balance
							{
								halfDay -= levAval;
								hdAdj += levAval;
								paidHD += levAval;
								levAval = 0;
							} //end of else statement
						} //end of if statement
					}
					
					levHalfAdjObj[i][0] = String.valueOf(levCode);
					levHalfAdjObj[i][1] = String.valueOf(levBal);
					levHalfAdjObj[i][2] = String.valueOf(levDays);
					levHalfAdjObj[i][3] = String.valueOf(lmAdj);
					levHalfAdjObj[i][4] = String.valueOf(hdAdj);
					levHalfAdjObj[i][5] = String.valueOf(levAval);
				}
			}
			
			unpaidHD = halfDay; //Remaining, not adjusted half days			
			bean.setPaidHD(paidHD);
			bean.setUnpaidHD(unpaidHD);
		} //end of try-catch block
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return levHalfAdjObj;
	} //end of adjustHalfDays
	
	/**
	 * Adjust late marks against leaves assigned
	**/
	/**
	 * @param levDtl -: Specifies the approved leave days and remaining balances
	 * @param lateLevs -: Specifies the leave types going to adjust with late marks
	 * @param lateMarks -: Specifies the number of late marks
	 * @param levLateNo -: Specifies the number of days for which late marks going to adjust
	 * @param bean -: Specifies MonthAttendance object
	 * @return Object[][] containing new leave days and leave balances including adjusted late marks
	**/
	public Object[][] adjustLateMarks(Object[][] levDtl, String[] lateLevs, double lateMarks, String levLateNo, MonthAttendance bean)
	{
		Object[][] levLateAdjObj = null;
		try
		{
			//levLateAdjObj = new Object[levDtl.length][6];
			double paidLM = bean.getPaidLM();
			double unpaidLM = bean.getUnpaidLM();
			double lateMark = (int)(lateMarks / Double.parseDouble(levLateNo)); //Converts late marks into days
			
			String levCode = "";
			String levBal = "";
			String levDays = "";
			double lmAdj = 0;
			String hdAdj = "";
			double levAval = 0;
			
			levLateAdjObj = levDtl;
				
			for(int j = 0; j < lateLevs.length; j++)
			{
				String lateLev = String.valueOf(lateLevs[j]);
				
				for(int i = 0; i < levLateAdjObj.length; i++)
				{
					levCode = String.valueOf(levLateAdjObj[i][0]);
					levBal = String.valueOf(levLateAdjObj[i][1]);
					levDays = String.valueOf(levLateAdjObj[i][2]);
					lmAdj = Double.parseDouble(String.valueOf(levLateAdjObj[i][3]));
					hdAdj = String.valueOf(levLateAdjObj[i][4]);
					levAval = Double.parseDouble(String.valueOf(levLateAdjObj[i][5]));
					
					if(lateLev.equals(levCode))
					{
						if(lateMark >= 1)
						{
							if(lateMark <= levAval)
							{
								levAval -= lateMark;
								lmAdj += lateMark;
								paidLM += lateMark;
								lateMark = 0;
							}
							else
							{
								lateMark -= levAval;
								lmAdj += levAval;
								paidLM += levAval;
								levAval = 0;
							}
						}
					}
					
					levLateAdjObj[i][0] = String.valueOf(levCode);
					levLateAdjObj[i][1] = String.valueOf(levBal);
					levLateAdjObj[i][2] = String.valueOf(levDays);
					levLateAdjObj[i][3] = String.valueOf(lmAdj);
					levLateAdjObj[i][4] = String.valueOf(hdAdj);
					levLateAdjObj[i][5] = String.valueOf(levAval);
				}
			}
			
			unpaidLM = lateMark; //Remaining, not adjusted late marks late marks			
			bean.setPaidLM(paidLM);
			bean.setUnpaidLM(unpaidLM);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return levLateAdjObj;
	} //end of adjustLateMarks
	
	/**
	 * Calculate the Attendance Days
	**/
	/**
	 * @param presentDays : Specifies present days of a month
	 * @param weeklyOffs : Specifies week off days of a month
	 * @param holidays : Specifies holidays of a month
	 * @param paidLevs : Specifies paid leave days of a month
	 * @param unPaidLevs : Specifies unpaid leave days of a month
	 * @return double as attendance days
	**/
	public double calAttdnDays(double presentDays, double weeklyOffs, double holidays, double paidLevs, double unPaidLevs)
	{
		double attdnDays = 0;
		try
		{
			attdnDays = presentDays - (weeklyOffs + holidays + paidLevs + unPaidLevs);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return attdnDays;
	} //end of calAttdnDays
	
	/**
	 * Calculate the attendance at the first time
	**/	
	/**
	 * @param bean
	 * @param request
	 * @param response
	 * @return String only if attendance can't calculated 
	**/
	public String calAttendance(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		String res = "";
		try
		{
			String month = bean.getMonth();
			String year = bean.getYear();		
			
			bean.setProcess(true);
			bean.setMonthName(Utility.month(Integer.parseInt(month)));
			
			String attdnCodeSql = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year;
			attdnCodeSql = setFiletrs(bean, attdnCodeSql);
			Object[][] attnCodeObj = getSqlModel().getSingleResult(attdnCodeSql);			
			
			if(attnCodeObj != null && attnCodeObj.length > 0)
			{
				res = "Attendance is already processed. Please click on Search button!";
				reset(bean);
			} //end of if statement
			else
			{
				if(bean.isAttConnFlag())
				{
					res = connectedAttn(bean, request);
				} //end of if statement
				else
				{
					res = processedAttn(bean, request, response);
				} //end of else statement
			} //end of else statement
		}
		catch (Exception e)
		{
			logger.error(e);
			res = "Attendance Not Available!";
			reset(bean);
		} //end of try-catch block
		return res;
	} //end of calAttendance
	
	/**
	 * Calculate the one complete attendance record for an employee
	**/
	/**
	 * @param bean
	 * @param attnConnFlag : Specifies whether attendance is connected with Daily Attendance
	 * @param monthFlag : Specifies whether attendance is processing from previous month or in current month
				P : Previous Month			C : Current Month
	 * @param month : Specifies month in which attendance is calculating.
	 * @param year : Specifies year in which attendance is calculating.
	 * @param eId : Specifies unique employee code
	 * @param fromDate : Specifies from which date attendance gets calculated
	 * @param toDate : Specifies to which date attendance gets calculated
	 * @param monthDays : Specifies total number of days of a month
	 * @return Object[][] containing a record of attendance for an employee
	**/
	public Object[][] calculateAttdn(boolean brnHDayFlag, boolean levConnFlag, boolean attnConnFlag, String monthFlag, String month, String year, String eId, String fromDate, String toDate, boolean recal, String attCode, String lateMark, String halfDay)
	{
		Object[][] attdnObj = null;
		try
		{
			attdnObj = new Object[1][11];
			double holidays = 0;
			
			Object[][] holidayObj = calHolidays(attnConnFlag, monthFlag, brnHDayFlag, month, year, eId, fromDate, toDate);
			if(!attnConnFlag)
			{
				if(holidayObj != null)
				{
					holidays = holidayObj.length;
				}
			} //end of if statement
			else
			{
				holidays = Double.parseDouble(String.valueOf(holidayObj[0][0]));
			}
			attdnObj[0][2] = String.valueOf(holidays);
			
			double weeklyOffs = calWeekOffs(attnConnFlag, monthFlag, month, year, eId, fromDate, toDate, holidayObj);
			attdnObj[0][1] = String.valueOf(weeklyOffs);

			double paidLevs = calPaidLevs(eId, month, year, fromDate, toDate, levConnFlag, recal, attCode);
			attdnObj[0][3] = String.valueOf(paidLevs);
			
			double presentDays = calPresentDays(fromDate, toDate, month, year);
			attdnObj[0][9] = String.valueOf(presentDays);
			
			double unPaidLevs = calUnPaidLevs(monthFlag, attnConnFlag, month, year, eId, fromDate, toDate, paidLevs, levConnFlag, recal, attCode);
			attdnObj[0][4] = String.valueOf(unPaidLevs);
			
			double attdnDays = calAttdnDays(presentDays, weeklyOffs, holidays, paidLevs, unPaidLevs);
			attdnObj[0][0] = String.valueOf(attdnDays);
			
			double salDays = calSalDays(presentDays, unPaidLevs);
			attdnObj[0][5] = String.valueOf(salDays);
			
			//if(lateMark.equals(""))
			//{
				int lateMarks = calLateMarks(attnConnFlag, monthFlag, month, year, eId, fromDate, toDate);
				attdnObj[0][6] = String.valueOf(lateMarks);
			//}
			//else
			//{
				//attdnObj[0][6] = lateMark;
			//}
			
			//if(halfDay.equals(""))
			//{
				int halfDays = calHalfDays(attnConnFlag, monthFlag, month, year, eId, fromDate, toDate);
				attdnObj[0][7] = String.valueOf(halfDays);
			//}
			//else
			//{
				//attdnObj[0][7] = String.valueOf(halfDay);
			//}
			
			attdnObj[0][8] = "false"; //ESAVE
			attdnObj[0][10] = "0"; //RECOVERY DAYS
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return attdnObj;
	} //end of calculateAttdn
	
	/**
	 * Calculates the half days
	**/
	/**
	 * @param dailyConFlag : Specifies whether attendance is connected with Daily Attendance
	 * @param monthFlag : Specifies whether attendance is processing from previous month or in current month
				P : Previous Month			C : Current Month
	 * @param month : Specifies month in which half days are calculating.
	 * @param year : Specifies year in which half days are calculating.
	 * @param eId : Specifies unique employee code
	 * @param fromDate : Specifies from which date half days gets calculated
	 * @param toDate : Specifies to which date half days gets calculated
	 * @return int as half days
	**/
	public int calHalfDays(boolean dailyConFlag, String monthFlag, String month, String year, String eId, String fromDate, String toDate)
	{
		int halfDays = 0;
		try
		{
			if(dailyConFlag) //Connection with Daily Attendance
			{
				String halfDaysSql = "";
				if(monthFlag.equals("P") && month.equals("01")) //Previous Month and Current Month Late Marks, if Previous Month is December
				{
					halfDaysSql = " SELECT NVL(SUM(HALF_DAYS), 0) HALF_DAYS FROM " +
					" ( " +
					"	SELECT COUNT(*) HALF_DAYS, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + year +
					"	WHERE ATT_EMP_ID = " + eId + " AND ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' " +
					" 	GROUP BY ATT_DATE " +
					"	UNION " +
					"	SELECT COUNT(*) HALF_DAYS, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
					"	WHERE ATT_EMP_ID = " + eId + " AND ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' " +
					" 	GROUP BY ATT_DATE " + 
					" ) " +
					" WHERE ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				} //end of if statement
				else
				{
					halfDaysSql = " SELECT COUNT(*) FROM HRMS_DAILY_ATTENDANCE_" + year +
					" WHERE ATT_EMP_ID = " + eId + " AND ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'HD' " +
					" AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				} //end of else statement
				Object[][] halfDaysObj = getSqlModel().getSingleResult(halfDaysSql);
				halfDays = Integer.parseInt(String.valueOf(halfDaysObj[0][0]));
			} //end of if statement
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return halfDays;
	} //end of calHalfDays
	
	/**
	 * Calculate the holidays 
	**/	
	/**
	 * @param dailyConFlag : Specifies whether attendance is connected with Daily Attendance
	 * @param monthFlag : Specifies whether attendance is processing from previous month or in current month
				P : Previous Month			C : Current Month
	 * @param brnHDayFlag : Specifies whether holidays are calculating as per branch or not. It is applicable only in Processed Attendance.
	 * @param month : Specifies month in which holidays are calculating.
	 * @param year : Specifies year in which holidays are calculating.
	 * @param eId : Specifies unique employee code
	 * @param fromDate : Specifies from which date holidays gets calculated
	 * @param toDate : Specifies to which date holidays gets calculated
	 * @return Object[][] containing the details of holidays
	**/
	public Object[][] calHolidays(boolean dailyConFlag, String monthFlag, boolean brnHDayFlag, String month, String year, String eId, String fromDate, String toDate)
	{
		Object holidayObj[][] = null;
		try
		{
			String holidayQuery = "";
			
			if(dailyConFlag) //Connection with Daily Attendance
			{
				if(monthFlag.equals("P") && month.equals("01")) //Previous Month and Current Month Holidays, if Previous Month is December
				{
					holidayQuery = 
					" SELECT NVL(SUM(HOLIDAYS), 0) FROM " +
					" ( " +
					"	SELECT COUNT(*) HOLIDAYS, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + year +
					" 	WHERE ATT_STATUS_ONE = 'HO' AND ATT_EMP_ID = " + eId + " GROUP BY ATT_DATE " +
					" 	UNION " +
					" 	SELECT COUNT(*) HOLIDAYS, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
					" 	WHERE ATT_STATUS_ONE = 'HO' AND ATT_EMP_ID = " + eId + " GROUP BY ATT_DATE " +
					" ) " +
					" WHERE ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				} //end of if statement
				else //Current Month Holidays
				{
					holidayQuery = " SELECT COUNT(*) FROM HRMS_DAILY_ATTENDANCE_" + year +
					" WHERE ATT_STATUS_ONE = 'HO' AND ATT_EMP_ID = " + eId +
					" AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				} //end of else statement
				Object[][] countDaysObj = getSqlModel().getSingleResult(holidayQuery);
				holidayObj = new Object[1][1];
				holidayObj[0][0] = String.valueOf(countDaysObj[0][0]);
			} //end of if statement
			else //Processed Attendance
			{
				if(brnHDayFlag) //Holidays for specific branch
				{
					holidayQuery = " SELECT TO_CHAR(HOLI_DATE, 'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH " +
					" WHERE HOLI_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					" AND CENTER_ID = (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = " + eId + ") ";
				} //end of if statement
				else //Holidays for all branches
				{
					holidayQuery = " SELECT TO_CHAR(HOLI_DATE, 'DD-MM-YYYY') FROM HRMS_HOLIDAY " +
					" WHERE HOLI_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				} //end of else statement
				holidayObj = getSqlModel().getSingleResult(holidayQuery);
			} //end of else statement
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return holidayObj;
	} //end of calHolidays
	
	/**
	 * Calculates the late marks
	**/
	/**
	 * @param dailyConFlag : Specifies whether attendance is connected with Daily Attendance
	 * @param monthFlag : Specifies whether attendance is processing from previous month or in current month
				P : Previous Month			C : Current Month
	 * @param month : Specifies month in which late marks are calculating.
	 * @param year : Specifies year in which late marks are calculating.
	 * @param eId : Specifies unique employee code
	 * @param fromDate : Specifies from which date late marks gets calculated
	 * @param toDate : Specifies to which date late marks gets calculated
	 * @return int as late marks
	**/
	public int calLateMarks(boolean dailyConFlag, String monthFlag, String month, String year, String eId, String fromDate, String toDate)
	{
		int lateMarks = 0;
		try
		{
			if(dailyConFlag) //Connection with Daily Attendance
			{
				String lateMarksSql = "";
				if(monthFlag.equals("P") && month.equals("01")) //Previous Month and Current Month Late Marks, if Previous Month is December
				{
					lateMarksSql = " SELECT NVL(SUM(LATE_MARKS), 0) LATE_MARKS FROM " +
					" ( " +
					"	SELECT ATT_DATE, CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2 " +
					"	ELSE COUNT(*) END LATE_MARKS FROM HRMS_DAILY_ATTENDANCE_" + year +
					" 	WHERE ATT_EMP_ID = " + eId + " AND ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') " +
					"	GROUP BY ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_DATE " +
					" 	UNION " +
					"	SELECT ATT_DATE, CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2 " +
					"	ELSE COUNT(*) END LATE_MARKS FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(month) - 1) +
					"	WHERE ATT_EMP_ID = " + eId + " AND ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') " +
					"	GROUP BY ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_DATE " +
					" ) " +
					" WHERE ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				} //end of if statement
				else //Current Month Late Marks
				{
					lateMarksSql = " SELECT NVL(SUM(LATE_MARKS), 0) LATE_MARKS FROM " +
					" ( " +
					"	SELECT CASE WHEN ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO = 'DL' THEN COUNT(*) * 2 " +
					"	ELSE COUNT(*) END LATE_MARKS FROM HRMS_DAILY_ATTENDANCE_" + year +
					"	WHERE ATT_EMP_ID = " + eId + " AND ATT_STATUS_ONE = 'PR' AND ATT_STATUS_TWO IN ('LC', 'EL', 'DL') " +
					"	AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
					"	GROUP BY ATT_STATUS_ONE, ATT_STATUS_TWO " +
					" ) ";
				} //end of else statement
				Object[][] lateMarksObj = getSqlModel().getSingleResult(lateMarksSql);
				lateMarks = Integer.parseInt(String.valueOf(lateMarksObj[0][0]));
			} //end of if statement
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return lateMarks;
	} //end of calLateMarks
	
	/**
	 * Calculate the paid leaves
	**/
	/**
	 * @param eId
	 * @param month
	 * @param year
	 * @return double as paid leaves
	**/
	public double calPaidLevs(String eId, String month, String year, String fromDate, String toDate, boolean levConnFlag, boolean recal, String attCode)
	{
		/* Calculate the paid leaves only if attendance is connected with Daily Attendance */
		double paidLevs = 0.0;
		try
		{
			double paidLevsAppln = 0, pLevsAttDtl = 0;
			if(levConnFlag)
			{
				String pLevsApplnSql = " SELECT SUM(NVL(LEAVE_DAYS, 0)) LEAVE_DAYS FROM HRMS_LEAVE_DTLHISTORY " +
				" RIGHT JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE_POLICY.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE " +
				" AND EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID = " + eId + ") AND LEAVE_APPLICABLE = 'Y' " +
				" AND HRMS_LEAVE_POLICY.LEAVE_PAID_UNPAID = 'P') " +
				" WHERE LEAVE_DTL_STATUS = 'A' AND EMP_ID = " + eId +
				" AND LEAVE_FROM_DATE >= TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND LEAVE_TO_DATE <= TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				Object[][] pLevsApplnData = getSqlModel().getSingleResult(pLevsApplnSql);
				paidLevsAppln = Double.parseDouble(String.valueOf(pLevsApplnData[0][0]));
				paidLevs = paidLevsAppln;
			}
			if(recal)
			{
				if(!attCode.equals(""))
				{
					String pLevsAttDtlSql = " SELECT NVL(SUM(ATT_LEAVE_ADJUST), 0) + NVL(SUM(ATT_LATEMARK_ADJUST), 0) + " +
					" NVL(SUM(ATT_HALFDAY_ADJUST), 0) + NVL(SUM(ATT_MANUAL_ADJUST), 0) PAID_LEVS " +
					" FROM HRMS_MONTH_ATT_DTL_" + year + " WHERE ATT_EMP_CODE = " + eId + " AND ATT_CODE = " + attCode;
					Object[][] pLevsAttDtlData = getSqlModel().getSingleResult(pLevsAttDtlSql);
					pLevsAttDtl = Double.parseDouble(String.valueOf(pLevsAttDtlData[0][0]));
					paidLevs = pLevsAttDtl;
				}				
			}
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return paidLevs;
	} //end of calPaidLevs
	
	/**
	 * Calculate total number of days within from date and to date
	**/
	/**
	 * @param fromDate
	 * @param toDate
	 * @return int as total number of days within from date and to date
	**/
	public double calPresentDays(String fromDate, String toDate, String month, String year)
	{
		double presDays = 0;
		try
		{
			int monthDays = getMonthDays(month, year);
			
			Calendar fromCal = Calendar.getInstance();
			Calendar toCal = Calendar.getInstance();
			
			fromCal.setTime(Utility.getDate(fromDate));
			toCal.setTime(Utility.getDate(toDate));
			
			double diffDays = (double)((toCal.getTime().getTime() - fromCal.getTime().getTime()) / (1000 * 60 * 60 * 24)) + 1;
			
			if(diffDays > monthDays)
			{
				presDays = monthDays;
			}
			else
			{
				presDays = diffDays;
			}
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return presDays;
	} //end of calPresDays
	
	/**
	 * Calculates the salary days
	**/
	/**
	 * @param presentDays : Specifies present days of a month
	 * @param unPaidLevs : Specifies unpaid leave days of a month
	 * @return double as salary days
	**/
	public double calSalDays(double presentDays, double unPaidLevs)
	{
		double salDays = 0;
		try
		{
			salDays = presentDays - unPaidLevs;
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return salDays;
	} //end of calSalDays
	
	public double calTotalAbsents(String monthFlag, String fromDate, String toDate, String month, String year, String eId, boolean attConnFlag)
	{
		double totAbs = 0;
		try
		{
			if(attConnFlag)
			{
				String totAbsSql = "";
				Object[][] totAbsObj = null;
				
				if(monthFlag.equals("P") && month.equals("01")) //Previous Month and Current Month Total Absents, if Previous Month is December
				{
					totAbsSql = " SELECT COUNT(*) TOTAL_ABSENTS FROM " +
					" ( " +
					"	SELECT ATT_EMP_ID, ATT_DATE, ATT_STATUS_ONE FROM HRMS_DAILY_ATTENDANCE_" + year +
					"	UNION " +
					"	SELECT ATT_EMP_ID, ATT_DATE, ATT_STATUS_ONE FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
					" ) " +
					" WHERE ATT_STATUS_ONE = 'AB' AND ATT_EMP_ID = " + eId +
					" AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				}
				else
				{
					totAbsSql = " SELECT COUNT(*) TOTAL_ABSENTS FROM HRMS_DAILY_ATTENDANCE_" + year +
					" WHERE ATT_STATUS_ONE = 'AB' AND ATT_EMP_ID = " + eId +
					" AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				}
				
				totAbsObj = getSqlModel().getSingleResult(totAbsSql);
				totAbs = Double.parseDouble(String.valueOf(totAbsObj[0][0]));
			}			
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return totAbs;
	}
	
	/**
	 * Calculates the unpaid leaves
	**/
	/**
	 * @param dailyConFlag : Specifies whether attendance is connected with Daily Attendance
	 * @param monthFlag : Specifies whether attendance is processing from previous month or in current month
				P : Previous Month			C : Current Month
	 * @param month : Specifies month in which unpaid leaves are calculating.
	 * @param year : Specifies year in which unpaid leaves are calculating.
	 * @param eId : Specifies unique employee code
	 * @param fromDate : Specifies from which date unpaid leaves gets calculated
	 * @param toDate : Specifies to which date unpaid leaves gets calculated
	 * @param monthDays : Specifies total number of days of a month
	 * @param presentDays : Specifies present days of a month
	 * @param paidLevs : Specifies paid leave days of a month
	 * @return double as unpaid leaves
	**/
	public double calUnPaidLevs(String monthFlag, boolean dailyConFlag, String month, String year, String eId, String fromDate, String toDate, double paidLevs, boolean levConnFlag, boolean recal, String attCode)
	{
		double unPaidLevs = 0;
		try
		{
			double upLevsAppln = 0, upLevsDtl = 0;
			
			if(dailyConFlag) //Connection with Daily Attendance
			{
				if(attCode.equals(""))
				{
					double totAbs = calTotalAbsents(monthFlag, fromDate, toDate, month, year, eId, dailyConFlag);
					unPaidLevs = totAbs - paidLevs;
				}
				else
				{
					if(recal)
					{
						String upLevsDtlSql = "SELECT NVL(ATTN_SYSTEM_UNPAID, 0) + NVL(ATTN_MANUAL_UNPAID, 0) UNPAID_LEVS " + 
						" FROM HRMS_MONTH_ATTENDANCE_" + year + " WHERE ATTN_EMP_ID = " + eId + 
						" AND ATTN_CODE = " + attCode;
						Object[][] upLevsApplnData = getSqlModel().getSingleResult(upLevsDtlSql);
						upLevsDtl = Double.parseDouble(String.valueOf(upLevsApplnData[0][0]));
						unPaidLevs = upLevsDtl;
					}					
				}
			}
			else
			{
				if(levConnFlag) //Leave Workflow is enabled
				{
					String upLevsApplnSql = " SELECT SUM(NVL(LEAVE_DAYS, 0)) LEAVE_DAYS FROM HRMS_LEAVE_DTLHISTORY " +
					" RIGHT JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE_POLICY.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE " +
					" AND EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID = " + eId + ") AND LEAVE_APPLICABLE = 'Y' " +
					" AND HRMS_LEAVE_POLICY.LEAVE_PAID_UNPAID = 'U') " +
					" WHERE LEAVE_DTL_STATUS = 'A' AND EMP_ID = " + eId + " AND LEAVE_MONTH = " + month + " AND LEAVE_YEAR = " + year +
					" AND LEAVE_FROM_DATE >= TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND LEAVE_TO_DATE <= TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
					Object[][] upLevsApplnData = getSqlModel().getSingleResult(upLevsApplnSql);
					upLevsAppln = Double.parseDouble(String.valueOf(upLevsApplnData[0][0]));
					unPaidLevs = upLevsAppln;
				}
				if(recal)
				{
					String upLevsDtlSql = " SELECT NVL(ATTN_SYSTEM_UNPAID, 0) + NVL(ATTN_MANUAL_UNPAID, 0) UNPAID_LEVS " + 
					" FROM HRMS_MONTH_ATTENDANCE_" + year + " WHERE ATTN_EMP_ID = " + eId + 
					" AND ATTN_CODE = " + attCode;
					Object[][] upLevsApplnData = getSqlModel().getSingleResult(upLevsDtlSql);
					upLevsDtl = Double.parseDouble(String.valueOf(upLevsApplnData[0][0]));
					unPaidLevs = upLevsDtl;
				}
			}
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return unPaidLevs;
	} //end of calUnPaidLevs
	
	/**
	 * Calculate the weekly offs
	**/
	/**
	 * @param dailyConFlag : Specifies whether attendance is connected with Daily Attendance
	 * @param monthFlag : Specifies whether attendance is processing from previous month or in current month
				P : Previous Month			C : Current Month
	 * @param month : Specifies month in which weekly offs are calculating.
	 * @param year : Specifies year in which weekly offs are calculating.
	 * @param eId : Specifies unique employee code
	 * @param fromDate : Specifies from which date weekly offs gets calculated
	 * @param toDate : Specifies to which date weekly offs gets calculated
	 * @param holiday : Specifies holiday dates on which, weekly offs can't be calculated.
	 * @return double as weekly off days
	**/
	public double calWeekOffs(boolean dailyConFlag, String monthFlag, String month, String year, String eId, String fromDate, String toDate, Object holiday[][])
	{
		//Don't consider a week off which is coming on holiday
		double countDays = 0.0; //No. of week offs
		try
		{
			String weekOffQuery = "";
			Object weekOffObj[][] = null;
						
			if(dailyConFlag) //Connection with Daily Attendance
			{
				if(monthFlag.equals("P") && month.equals("01")) //Previous Month and Current Month Weekly Offs, if Previous Month is December
				{
					weekOffQuery =
					" SELECT NVL(SUM(WEEKLYOFFS), 0) FROM " +
					" ( " +
					" 	SELECT COUNT(*) WEEKLYOFFS, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + year +
					" 	WHERE ATT_STATUS_ONE = 'WO' AND ATT_EMP_ID = " + eId + " GROUP BY ATT_DATE " +
					" 	UNION " +
					" 	SELECT COUNT(*) WEEKLYOFFS, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
					" 	WHERE ATT_STATUS_ONE = 'WO' AND ATT_EMP_ID = " + eId + " GROUP BY ATT_DATE " +
					" ) " +
					" WHERE ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				} //end of if statement
				else //Current Month Weekly Offs
				{
					weekOffQuery = " SELECT COUNT(*) FROM HRMS_DAILY_ATTENDANCE_" + year +
					" WHERE ATT_STATUS_ONE = 'WO' AND ATT_EMP_ID = " + eId +
					" AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
				}
				weekOffObj = getSqlModel().getSingleResult(weekOffQuery);
				countDays = Double.parseDouble(String.valueOf(weekOffObj[0][0]));
			} //end of if statement
			else
			{
				Calendar currentcalendarday = new Utility().getCalanderDate(fromDate);
				Calendar lastcalendarday = new Utility().getCalanderDate(toDate);
				
				weekOffQuery = " SELECT SHIFT_WEEK_1, SHIFT_WEEK_2, SHIFT_WEEK_3, SHIFT_WEEK_4, SHIFT_WEEK_5, SHIFT_WEEK_6 "
				+" FROM HRMS_SHIFT WHERE SHIFT_ID = (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID = " + eId + " ) ";
				weekOffObj = getSqlModel().getSingleResult(weekOffQuery); //Bring the week offs from shift
				
				if(weekOffObj != null && weekOffObj.length > 0)
				{
					if(currentcalendarday.equals(lastcalendarday)) //fromDate and toDate is same
					{
						countDays += weekenDays(currentcalendarday, weekOffObj);
					}
					else
					{
						while(!currentcalendarday.equals(lastcalendarday)) //fromDate and toDate is different
						{
							countDays += weekenDays(currentcalendarday, weekOffObj);
							currentcalendarday.add(Calendar.DATE, 1);
						}
						if(currentcalendarday.equals(lastcalendarday))
						{
							countDays += weekenDays(currentcalendarday, weekOffObj);
						}
					} //end of else statement
					if(holiday != null && holiday.length > 0)
					{
						for (int i = 0; i < holiday.length; i++)
						{
							Calendar holidaycalendar = new Utility().getCalanderDate(String.valueOf(holiday[i][0]));
							countDays -= weekenDays(holidaycalendar, weekOffObj);
						}
					} //end of if statement
				} //end of if statement
			} //end of else statement
		}
		catch (Exception e)
		{
			//logger.error(e);
			logger.error(e);
		} //end of try-catch block
		return countDays;
	} //end of calWeekOffs
	
	/**
	 * Calculate the attendance which is coming from Daily Attendance
	**/
	/**
	 * @param bean
	 * @param request
	 * @return
	**/
	public String connectedAttn(MonthAttendance bean, HttpServletRequest request)
	{
		String res = "";
		try
		{
			String month = Integer.parseInt(bean.getMonth()) < 10 ? "0" + bean.getMonth() : bean.getMonth();
			String year = bean.getYear();
			String attdnCode = bean.getAttdnCode();
			boolean brnHDayFlag = bean.isBrnHDayFlag();
			boolean levConnFlag = bean.isLevConnFlag();
			
			String newMonth = "", newYear = "", fromDate = "";
			
			String fDate = "01-" + month + "-" + year;
			int monthDays = getMonthDays(month, year); //Calculate total days of a month
			String toDate = monthDays + "-" + month + "-" + year;
			
			if(bean.isSavedFlag())
			{
				bean.setMonthName(Utility.month(Integer.parseInt(month))); //Set month name by passing month as integer
			}
			
			String monthDateQuery = " SELECT CONF_SALARY_START_DATE, CONF_SALARY_START_FLAG FROM HRMS_ATTENDANCE_CONF ";			
			Object[][] monthDateObject = getSqlModel().getSingleResult(monthDateQuery);

			String startDate = String.valueOf(monthDateObject[0][0]);
			startDate = Integer.parseInt(startDate) < 10 ? "0" + startDate : startDate;
			String monthFlag = String.valueOf(monthDateObject[0][1]);

			if(monthFlag.equals("P")) //Check that attendance is calculated from Previous Month
			{
				if(month.equals("01"))
				{
					newMonth = "12";
					newYear = String.valueOf(Integer.parseInt(year) - 1);
				}
				else
				{
					newMonth = String.valueOf(Integer.parseInt(month) - 1);
					newMonth = Integer.parseInt(newMonth) < 10 ? "0" + newMonth : newMonth;
					newYear = year;
				}
				fDate = startDate + "-" + newMonth + "-" + newYear;
				toDate = startDate + "-" + month + "-" + year;
			} //end of if statement
			
			// Get list of employees containing saved as well as non saved employees
			Object[][] eListObj = getEmpIDs(bean, toDate, attdnCode, year, false);
			
			doPaging(bean, eListObj.length, new Object[][]{}, request, false);
			int fromTotRec = Integer.parseInt(bean.getFromTotRec());
			int toTotRec = Integer.parseInt(bean.getToTotRec());
			
			if(eListObj != null && eListObj.length > 0)
			{
				ArrayList<Object> attdnList = new ArrayList<Object>();
				
				for(int i = fromTotRec; i < toTotRec; i++)
				{
					boolean dailyConFlag = true;
					
					Object[][] attdnObj = new Object[1][11];

					String eId = String.valueOf(eListObj[i][0]);
					String eToken = String.valueOf(eListObj[i][1]);
					String eName = String.valueOf(eListObj[i][2]);
					String eJoinDate = String.valueOf(eListObj[i][3]);
					String eFlag = String.valueOf(eListObj[i][4]);
					
					fromDate = getFromDate(eJoinDate, fDate); // Calculate from date
					
					int dailyDays = getTotalDailyAttdn(monthFlag, month, year, eId, fromDate, toDate);
					if(dailyDays == 0)
					{
						dailyConFlag = false;
					}
					
					if(eFlag.equals("SAVE")) //If attendance is already saved, then take record from saved attendance
					{
						attdnObj[0][0] = String.valueOf(eListObj[i][5]); //ATTN_DAYS
						attdnObj[0][1] = String.valueOf(eListObj[i][6]); //ATTN_WOFF
						attdnObj[0][2] = String.valueOf(eListObj[i][7]); //ATTN_HOLIDAY
						attdnObj[0][3] = String.valueOf(eListObj[i][8]); //ATTN_PAID_LEVS
						attdnObj[0][4] = String.valueOf(eListObj[i][9]); //ATTN_UNPAID_LEVS
						attdnObj[0][5] = String.valueOf(eListObj[i][10]); //ATTN_SAL_DAYS
						attdnObj[0][6] = String.valueOf(eListObj[i][11]); //ATTN_LATE_MARKS
						attdnObj[0][7] = String.valueOf(eListObj[i][12]); //ATTN_HALF_DAYS
						attdnObj[0][8] = "true"; //ESAVE
						
						
						double presDays = calPresentDays(fromDate, toDate, month, year);
						attdnObj[0][9] = String.valueOf(presDays); //PRESENT_DAYS
						attdnObj[0][10] = String.valueOf(eListObj[i][13]);//ATTN_RECOVERY_DAYS
					} //end of if statement
					else // If employee is not saved, then calculate the attendance
					{
						attdnObj = calculateAttdn(brnHDayFlag, levConnFlag, dailyConFlag, monthFlag, month, year, eId, fromDate, toDate, false, "", "", "");
					}
					
					if(attdnObj != null || attdnObj.length > 0)
					{
						MonthAttendance bean1 = new MonthAttendance();
						bean1.setEId(eId);
						bean1.setEToken(eToken);
						bean1.setEName(eName);
						bean1.setEJoinDate(eJoinDate);
						bean1.setAttdnDays(String.valueOf(attdnObj[0][0]));
						bean1.setWeeklyOffs(String.valueOf(attdnObj[0][1]));
						bean1.setHolidays(String.valueOf(attdnObj[0][2]));
						bean1.setPaidLevs(String.valueOf(attdnObj[0][3]));
						bean1.setUnPaidLevs(String.valueOf(attdnObj[0][4]));
						bean1.setSalDays(String.valueOf(attdnObj[0][5]));
						bean1.setLateMarks(String.valueOf(attdnObj[0][6]));
						bean1.setHalfDays(String.valueOf(attdnObj[0][7]));
						bean1.setESave(String.valueOf(attdnObj[0][8]));
						bean1.setPresentDays(String.valueOf(attdnObj[0][9]));
						bean1.setDailyConFlag(dailyConFlag);
						bean1.setLockFlag(bean.isLockFlag());
						bean1.setTotAbs(String.valueOf(calTotalAbsents(monthFlag, fromDate, toDate, month, year, eId, true)));
						bean1.setRecoveryDays(String.valueOf(attdnObj[0][10]));
						bean1.setRecoveryFlag(bean.getRecoveryFlag());
						double salDays = Double.parseDouble(bean1.getSalDays());
						double presentDays = Double.parseDouble(bean1.getPresentDays());
						double unPaidLevs = Double.parseDouble(bean1.getUnPaidLevs());
						double attdnDays = Double.parseDouble(bean1.getAttdnDays());						
						bean1.setReCal(isRecalculate(bean, monthFlag, month, year, eId, salDays, presentDays, unPaidLevs, attdnDays));
						
						bean1.setFromDate(fromDate);
						bean1.setToDate(toDate);
						
						attdnList.add(bean1);
					} //end of if statement
				} //end of for loop
				
				bean.setAttdnList(attdnList);
				bean.setFlag(true);
			} //end of if statement
			else
			{
				res = "Daily Attendance not available.";
				reset(bean); //Reset the fields on a page
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			res = "Daily Attendance not available.";
			reset(bean); //Reset the fields on a page
		} //end of try-catch block
		return res;
	} //end of connectedAttn

	/**
	 * This method is used to do pagination
	**/	
	/**
	 * @param bean : Specifies MonthAttendance object
	 * @param empLength : Specifies total number of records
	 * @param attnObj : Specifies the Object[][] containing records
	 * @param request : Specifies HttpServletRequest object
	 * @param empSearch : Specifies whether employee search is on or off.
	**/
	public void doPaging(MonthAttendance bean, int empLength, Object[][] attnObj, HttpServletRequest request, boolean empSearch)
	{
		try
		{
			/**
			 * totalRec -: No. of records per page
			 * fromTotRec -: Starting No. of record to show on a current page
			 * toTotRec -: Last No. of record to show on a current page
			 * pageNo -: Current page to show
			 * totalPage -: Total No. of Pages
			**/
			
			String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_ATTENDANCE_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));
			
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String empExists = "false";

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal((double)empLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
			
			if(String.valueOf(bean.getHdPage()).equals("null")||String.valueOf(bean.getHdPage()).equals(null)||String.valueOf(bean.getHdPage()).equals(""))
			{
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if(toTotRec > empLength)
				{
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			} //end of if statement
			else
			{
				if(empSearch)
				{
					for (int j = 0; j < attnObj.length; j++)
					{
						if(String.valueOf(attnObj[j][0]).equals(bean.getEmpCode()))
						{
							empExists = "true";
							break;
						}
					} //end of for loop
					if(empExists.equals("true"))
					{
						for (int i = 0; i < empLength; i++)
						{
							if(!String.valueOf(attnObj[i][0]).equals(bean.getEmpCode()))
							{
								searchCount = i;
							}
							else
							{
								searchCount = searchCount + 2;
								break;
							}
						}//end of for loop
						if(searchCount == 0)
						{
							searchCount = 1;
						}
						java.math.BigDecimal value2 = new java.math.BigDecimal((double)searchCount / totalRec);	
					    int pageSearch = Integer.parseInt(value2.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
					    pageNo = Integer.parseInt(String.valueOf(pageSearch));
					} //end of if statement
					else
					{
						pageNo = Integer.parseInt(bean.getHdPage());
					}
					if(pageNo == 1)
				    {
						 fromTotRec = 0;
						 toTotRec = totalRec;
				    }
				    else
				    {
				    	toTotRec = toTotRec * pageNo;
				    	fromTotRec = (toTotRec - totalRec);
				    }
				    if(toTotRec > empLength)
				    {
				    	toTotRec = empLength;
				    }
				} //end of if statement
				else
				{
					pageNo = Integer.parseInt(bean.getHdPage());
						  
					if(pageNo == 1)
					{
						fromTotRec = 0;
						toTotRec = totalRec;
					}
					else
					{
						toTotRec = toTotRec * pageNo;
						fromTotRec = toTotRec - totalRec;
					}
					if(toTotRec > empLength)
					{
						toTotRec = empLength;
					}
				} //end of else statement
			} //end of else statement
			
			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));
			
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
	} //end of doPaging
	
	/**
	 * Find a particular employee
	**/
	/**
	 * @param bean
	 * @param request
	**/
	public void empSearch(MonthAttendance bean, HttpServletRequest request)
	{
		/*
		 * If attendance is locked, then records from saved attendance will come and if not, 
		 * then by processing, records will come. It shows that record. Then by doing paging, 
		 * it moves to a particular page where an employee's record available.
		 */
		try
		{
			String month = Integer.parseInt(bean.getMonth()) < 10 ? "0" + bean.getMonth() : bean.getMonth();
			String year = bean.getYear();
			String attdnCode = bean.getAttdnCode();
			boolean brnHDayFlag = bean.isBrnHDayFlag();
			boolean attConnFlag = bean.isAttConnFlag();
			boolean levConnFlag = bean.isLevConnFlag();
			boolean lockFlag = bean.isLockFlag();
			
			String fromDate = "";
			String eId = "", eToken = "", eName = "", eJoinDate = "", eFlag = "";
			String monthFlag = "", startDate = "";
			
			Object[][] attdnObj = null;
			Object[][] eListObj = null;
			
			String fDate = "01-" + month + "-" + year;
			int monthDays = getMonthDays(month, year); //Calculate total days of a month
			String toDate = (monthDays < 10 ? "0" + monthDays : monthDays) + "-" + month + "-" + year;
			
			if(attConnFlag)
			{
				String monthDateQuery = " SELECT CONF_SALARY_START_DATE, CONF_SALARY_START_FLAG FROM HRMS_ATTENDANCE_CONF ";			
				Object[][] dateFlagObject = getSqlModel().getSingleResult(monthDateQuery);

				startDate = String.valueOf(dateFlagObject[0][0]);
				startDate = Integer.parseInt(startDate) < 10 ? "0" + startDate : startDate;
				monthFlag = String.valueOf(dateFlagObject[0][1]);
				
				String newMonth = "";
				String newYear = "";							
				
				if(monthFlag.equals("P"))
				{
					if(month.equals("01"))
					{
						newMonth = "12";
						newYear = String.valueOf(Integer.parseInt(year) - 1);
					}
					else
					{
						newMonth = String.valueOf(Integer.parseInt(month) - 1);
						newMonth = Integer.parseInt(newMonth) < 10 ? "0" + newMonth : newMonth;
						newYear = year;
					}
					fDate = startDate + "-" + newMonth + "-" + newYear;
					toDate = startDate + "-" + month + "-" + year;
				} //end of if statement
			} //end of if statement
			
			// Get list of employees containing saved as well as non saved employees
			eListObj = getEmpIDs(bean, toDate, attdnCode, year, lockFlag);
			
			if(bean.isLockFlag())
			{
				attdnObj = new Object[eListObj.length][16];
				
				if(eListObj != null && eListObj.length > 0)
				{
					for(int i = 0; i < eListObj.length; i++)
					{
						boolean dailyConFlag = attConnFlag;
						
						if(String.valueOf(eListObj[i][4]).equals("SAVE"))
						{
							attdnObj[i][0] = String.valueOf(eListObj[i][0]); //EID
							attdnObj[i][1] = String.valueOf(eListObj[i][1]); //TOKEN
							attdnObj[i][2] = String.valueOf(eListObj[i][2]); //NAME
							attdnObj[i][3] = String.valueOf(eListObj[i][3]); //JOIN_DATE
							attdnObj[i][4] = String.valueOf(eListObj[i][5]); //ATTN_DAYS
							attdnObj[i][5] = String.valueOf(eListObj[i][6]); //ATTN_WOFF
							attdnObj[i][6] = String.valueOf(eListObj[i][7]); //ATTN_HOLIDAY
							attdnObj[i][7] = String.valueOf(eListObj[i][8]); //ATTN_PAID_LEVS
							attdnObj[i][8] = String.valueOf(eListObj[i][9]); //ATTN_UNPAID_LEVS
							attdnObj[i][9] = String.valueOf(eListObj[i][10]); //ATTN_SAL_DAYS
							attdnObj[i][10] = String.valueOf(eListObj[i][11]); //ATTN_LATE_MARKS
							attdnObj[i][11] = String.valueOf(eListObj[i][12]); //ATTN_HALF_DAYS
							attdnObj[i][12] = "true"; //ESAVE
							
							String joinDate = String.valueOf(eListObj[i][3]); //Join Date
							fromDate = getFromDate(joinDate, fDate); // Calculate from date
							
							double presDays = calPresentDays(fromDate, toDate, month, year);
							attdnObj[i][13] = String.valueOf(presDays);
							
							if(dailyConFlag)
							{
								int dailyDays = getTotalDailyAttdn(monthFlag, month, year, eId, fromDate, toDate);
								if(dailyDays == 0)
								{
									dailyConFlag = false;
								} //end of if statement
							}							
							attdnObj[i][14] = String.valueOf(dailyConFlag); //DAILY_CONNECTED
							attdnObj[i][15] = String.valueOf(eListObj[i][13]); //RECOVERY_DAYS
						} // end of if statement						
					} //end of for loop
				} //end of if statement
			} //end of if statement
			else
			{
				attdnObj = new Object[eListObj.length][16];
				
				for(int i = 0; i < eListObj.length; i++)
				{
					boolean dailyConFlag = attConnFlag;
					
					eId = String.valueOf(eListObj[i][0]);
					eToken = String.valueOf(eListObj[i][1]);
					eName = String.valueOf(eListObj[i][2]);
					eJoinDate = String.valueOf(eListObj[i][3]);
					eFlag = String.valueOf(eListObj[i][4]);
					
					fromDate = getFromDate(eJoinDate, fDate); // Calculate from date
					
					/**
					 * Check daily attendance is available or not. If no, then process the attendance.
					**/
					if(dailyConFlag)
					{
						int dailyDays = getTotalDailyAttdn(monthFlag, month, year, eId, fromDate, toDate);
						if(dailyDays == 0)
						{
							dailyConFlag = false;
						}
					}
					
					if(eFlag.equals("SAVE"))
					{
						attdnObj[i][0] = String.valueOf(eListObj[i][0]); //EID
						attdnObj[i][1] = String.valueOf(eListObj[i][1]); //TOKEN
						attdnObj[i][2] = String.valueOf(eListObj[i][2]); //NAME
						attdnObj[i][3] = String.valueOf(eListObj[i][3]); //JOIN_DATE
						attdnObj[i][4] = String.valueOf(eListObj[i][5]); //ATTN_DAYS
						attdnObj[i][5] = String.valueOf(eListObj[i][6]); //ATTN_WOFF
						attdnObj[i][6] = String.valueOf(eListObj[i][7]); //ATTN_HOLIDAY
						attdnObj[i][7] = String.valueOf(eListObj[i][8]); //ATTN_PAID_LEVS
						attdnObj[i][8] = String.valueOf(eListObj[i][9]); //ATTN_UNPAID_LEVS
						attdnObj[i][9] = String.valueOf(eListObj[i][10]); //ATTN_SAL_DAYS
						attdnObj[i][10] = String.valueOf(eListObj[i][11]); //ATTN_LATE_MARKS
						attdnObj[i][11] = String.valueOf(eListObj[i][12]); //ATTN_HALF_DAYS
						attdnObj[i][12] = "true"; //ESAVE
						
						double presentDays = calPresentDays(fromDate, toDate, month, year);
						attdnObj[i][13] = String.valueOf(presentDays);
						
						attdnObj[i][14] = String.valueOf(dailyConFlag); //DAILY_CONNECTED
						attdnObj[i][15] = String.valueOf(eListObj[i][13]); //RECOVERY_DAYS
					} //end of if statement
					else
					{
						attdnObj[i][0] = eId;
						attdnObj[i][1] = eToken;
						attdnObj[i][2] = eName;
						attdnObj[i][3] = eJoinDate;
						
						double holidays = 0;
						Object[][] holidayObj = calHolidays(dailyConFlag, monthFlag, brnHDayFlag, month, year, eId, fromDate, toDate);
						if(!dailyConFlag)
						{
							if(holidayObj != null)
							{
								holidays = holidayObj.length;
							}
						}
						else
						{
							holidays = Double.parseDouble(String.valueOf(holidayObj[0][0]));
						}
						attdnObj[i][6] = String.valueOf(holidays);
												
						double weeklyOffs = calWeekOffs(dailyConFlag, monthFlag, month, year, eId, fromDate, toDate, holidayObj);
						attdnObj[i][5] = String.valueOf(weeklyOffs);
												
						double paidLevs = calPaidLevs(eId, month, year, fromDate, toDate, levConnFlag, false, "");
						attdnObj[i][7] = String.valueOf(paidLevs);
						
						double presentDays = calPresentDays(fromDate, toDate, month, year);
						attdnObj[i][13] = String.valueOf(presentDays);
												
						double unPaidLevs = calUnPaidLevs(monthFlag, dailyConFlag, month, year, eId, fromDate, toDate, paidLevs, levConnFlag, false, "");
						attdnObj[i][8] = String.valueOf(unPaidLevs);
												
						double attdnDays = calAttdnDays(presentDays, weeklyOffs, holidays, paidLevs, unPaidLevs); 
						attdnObj[i][4] = String.valueOf(attdnDays);
												
						double salDays = calSalDays(presentDays, unPaidLevs);
						attdnObj[i][9] = String.valueOf(salDays);
												
						int lateMarks = calLateMarks(dailyConFlag, monthFlag, month, year, eId, fromDate, toDate);
						attdnObj[i][10] = String.valueOf(lateMarks);
												
						int halfDays = calHalfDays(dailyConFlag, monthFlag, month, year, eId, fromDate, toDate);
						attdnObj[i][11] = String.valueOf(halfDays);
						
						attdnObj[i][12] = "false"; //ESAVE
						
						attdnObj[i][14] = String.valueOf(dailyConFlag); //DAILY_CONNECTED
						attdnObj[i][15] = "0"; //RECOVERY_DAYS
					} //end of if statement
				} //end of for loop
			} //end of else statement
			
			doPaging(bean, attdnObj.length, attdnObj, request, true);
			int fromTotRec = Integer.parseInt(bean.getFromTotRec());
			int toTotRec = Integer.parseInt(bean.getToTotRec());
			
			ArrayList<Object> attdnList = new ArrayList<Object>();
			
			for(int i = fromTotRec; i < toTotRec; i++)
			{
				MonthAttendance bean1 = new MonthAttendance();
				bean1.setEId(String.valueOf(attdnObj[i][0]));
				bean1.setEToken(String.valueOf(attdnObj[i][1]));
				bean1.setEName(String.valueOf(attdnObj[i][2]));
				bean1.setEJoinDate(String.valueOf(attdnObj[i][3]));
				bean1.setAttdnDays(String.valueOf(attdnObj[i][4]));
				bean1.setWeeklyOffs(String.valueOf(attdnObj[i][5]));
				bean1.setHolidays(String.valueOf(attdnObj[i][6]));
				bean1.setPaidLevs(String.valueOf(attdnObj[i][7]));
				bean1.setUnPaidLevs(String.valueOf(attdnObj[i][8]));
				bean1.setSalDays(String.valueOf(attdnObj[i][9]));
				bean1.setLateMarks(String.valueOf(attdnObj[i][10]));
				bean1.setHalfDays(String.valueOf(attdnObj[i][11]));
				bean1.setESave(String.valueOf(attdnObj[i][12]));
				bean1.setPresentDays(String.valueOf(attdnObj[i][13]));
				bean1.setDailyConFlag(Boolean.parseBoolean(String.valueOf(attdnObj[0][14])));
				bean1.setLockFlag(bean.isLockFlag());
				bean1.setTotAbs(String.valueOf(calTotalAbsents(monthFlag, fromDate, toDate, month, year, eId, attConnFlag)));
				bean1.setRecoveryFlag(bean.getRecoveryFlag());
				bean1.setRecoveryDays(String.valueOf(attdnObj[i][15]));
				double salDays = Double.parseDouble(bean1.getSalDays());
				double presentDays = Double.parseDouble(bean1.getPresentDays());
				double unPaidLevs = Double.parseDouble(bean1.getUnPaidLevs());
				double attdnDays = Double.parseDouble(bean1.getAttdnDays());				
				bean1.setReCal(isRecalculate(bean, monthFlag, month, year, eId, salDays, presentDays, unPaidLevs, attdnDays));
				
				bean1.setFromDate(fromDate);
				bean1.setToDate(toDate);
				
				attdnList.add(bean1);
			} //end of for loop
			
			bean.setAttdnList(attdnList);
			bean.setFlag(true);
			bean.setEmpSearch(true);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
	} //end of empSearch
	
	/**
	 * Sets the default filters specified in Application Settings
	**/
	/**
	 * @param bean
	 * @param request
	 * @param response
	**/
	public void filters(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			bean.setStatus("");
			
			String query = " SELECT LEDGER_PAYBILL, LEDGER_EMPTYPE, LEDGER_DEPT, LEDGER_BRN, LEDGER_DIVISION, TRIM(LEDGER_STATUS) " +
			" FROM HRMS_SALARY_LEDGER WHERE LEDGER_CODE = " + bean.getAttdnCode();
			Object[][] codesObj = getSqlModel().getSingleResult(query);//Get the filter details from backend
			
			if(String.valueOf(codesObj[0][0]).equals("")|| String.valueOf(codesObj[0][0]).equals("null"))//Paybill
			{
				bean.setPayBillNo("");
				bean.setPayBillFlag(false);
			} //end of if statement
			else
			{	
				bean.setPayBillNo(String.valueOf(codesObj[0][0]));
				bean.setPayBillFlag(true);
			} //end of else statement
			
			if(String.valueOf(codesObj[0][1]).equals("")|| String.valueOf(codesObj[0][1]).equals("null"))//Employee Type
			{
				bean.setTypeCode("");
				bean.setTypeFlag(false);
			} //end of if statement
			else
			{
				bean.setTypeCode(String.valueOf(codesObj[0][1]));
				bean.setTypeFlag(true);
			} //end of else statement
			
			if(String.valueOf(codesObj[0][2]).equals("")|| String.valueOf(codesObj[0][2]).equals("null"))//Department
			{
				bean.setDeptCode("");
				bean.setDeptFlag(false);
			} //end of if statement
			else
			{
				bean.setDeptCode(String.valueOf(codesObj[0][2]));
				bean.setDeptFlag(true);
			} //end of else statement
			
			if(String.valueOf(codesObj[0][3]).equals("")|| String.valueOf(codesObj[0][3]).equals("null"))//Branch
			{
				bean.setBrnCode("");
				bean.setBrnFlag(false);
			} //end of if statement
			else
			{
				bean.setBrnCode(String.valueOf(codesObj[0][3]));
				bean.setBrnFlag(true);
			} //end of else statement
			
			if(String.valueOf(codesObj[0][4]).equals("")|| String.valueOf(codesObj[0][4]).equals("null"))//Division
			{
				bean.setDivCode("");
				bean.setDivFlag(false);
			} //end of if statement
			else
			{
				bean.setDivCode(String.valueOf(codesObj[0][4]));
				bean.setDivFlag(true);
			} //end of else statement
			bean.setStatus(String.valueOf(codesObj[0][5]));//Set status as either ATTN_START, ATTN_READY, SAL_START or SAL_FINAL
			if(bean.getStatus().equals("SAL_FINAL"))//If salary has been locked, then attendance can't be modified.
			{
				bean.setStatusFlag(true);
			} //end of if statement
			else
			{
				bean.setStatusFlag(false);
			} //end of else statement
			
			if(bean.getStatus().equals("ATTN_START") || bean.getStatus().equals("ATTN_UNLOCK"))//Attendance is saved and does't lock
			{
				bean.setLockFlag(false);
			} //end of if statement
			else
			{
				bean.setLockFlag(true);
			} //end of else statement
			
			if(bean.getStatus().equals("ATTN_UNLOCK"))
			{
				bean.setUnlockFlag(true);
			}
			else
			{
				bean.setUnlockFlag(false);
			}
			
			bean.setSavedFlag(true); // Sets savedFlag to true and restricts it from processing the new attendance with the help of filters
			bean.setProcess(true);
			
			/**
			 * If Attendance is locked, then it shows saved attendance. If not, it precesses the attendance.
			**/
			if(bean.getStatus().trim().equals("ATTN_START"))
			{
				if(bean.isAttConnFlag())
				{
					connectedAttn(bean, request);
				}
				else
				{
					processedAttn(bean, request, response);
				}
			} //end of if statement
			else
			{
				showAttendance(bean, request); //Set saved records of attendance in a page
			}
			bean.setEmpSearch(false);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
	} //end of filters
	
	/**
	 * Save the attendance
	**/
	/**
	 * @param bean
	 * @param attnCode
	 * @param request
	 * @return boolean as whether attendance is saved or not
	**/
	public boolean getAttdnToSave(MonthAttendance bean, String attnCode, HttpServletRequest request)
	{
		/**
		 * Insert records into HRMS_MONTH_ATTENDANCE_year table. This method works in both situation either at 
		 * saving record first time or at updating existing records. While saving records at first time, it is 
		 * inserting default values for leaves in HRMS_MONTH_ATT_DTL_year table as well as in HRMS_MONTH_ATTENDANCE_year 
		 * table for default entries for late marks and half days.
		**/
		try
		{
			boolean res = false;
			boolean result = false;
			
			Object[][] lateHalfDayObj = null;
			Object[][] levLateAdjObj = null;
			Object[][] levHalfAdjObj = null;
			
			String month = bean.getMonth();
			String year = bean.getYear();
			boolean attConnFlag = bean.isAttConnFlag();
			
			Object[] eId = request.getParameterValues("eId");
			Object[] attdnDays = request.getParameterValues("attdnDays");
			Object[] weeklyOffs = request.getParameterValues("weeklyOffs");
			Object[] holidays = request.getParameterValues("holidays");
			Object[] paidLevs = request.getParameterValues("paidLevs");
			Object[] unPaidLevs = request.getParameterValues("unPaidLevs");
			Object[] salDays = request.getParameterValues("salDays");
			Object[] lateMarks = request.getParameterValues("lateMarks");
			Object[] halfDays = request.getParameterValues("halfDays");
			Object[] manAdjUnpaid = request.getParameterValues("manAdjUnpaid");
			Object[] fromDate = request.getParameterValues("fromDate");
			Object[] toDate = request.getParameterValues("toDate");
			Object[] recoveryDays = request.getParameterValues("recoveryDays");
			
			//Get settings assigned against late mark and half day if Daily Attendance Workflow is enabled
			if(attConnFlag)
			{
				lateHalfDayObj = getLateMarksHalfDays();
			}
			
			/**
			 * Initialize the paid and unpaid leaves to be adjusted with late marks and half days with 0
			**/
			bean.setPaidLM(0);
			bean.setPaidHD(0);
			bean.setUnpaidLM(0);
			bean.setUnpaidHD(0);
			
			for(int i = 0; i < eId.length; i++)
			{
				String empCode = String.valueOf(eId[i]);
				String fDate = String.valueOf(fromDate[i]);
				String tDate = String.valueOf(toDate[i]);
				
				double attdnDay = Double.parseDouble(String.valueOf(attdnDays[i]));
				double weekOff = Double.parseDouble(String.valueOf(weeklyOffs[i]));
				double holiday = Double.parseDouble(String.valueOf(holidays[i]));
				double paidLev = Double.parseDouble(String.valueOf(paidLevs[i]));
				double unPaidLev = Double.parseDouble(String.valueOf(unPaidLevs[i]));
				double salDay = Double.parseDouble(String.valueOf(salDays[i]));
				double lateMark = Double.parseDouble(String.valueOf(lateMarks[i]));
				double halfDay = Double.parseDouble(String.valueOf(halfDays[i]));
				double newHalfDay = halfDay;
				double manUnpaid = String.valueOf(manAdjUnpaid[i]).equals("") ? 0 : Double.parseDouble(String.valueOf(manAdjUnpaid[i]));
				double sysUnpaid = unPaidLev - manUnpaid;
				double recDays = Double.parseDouble(String.valueOf(recoveryDays[i]));
				if(bean.isFirstSave())
				{
					//Get approved leaves
					Object[][] attnDtlObj = getLeavesApplied(empCode, fDate, tDate, year);
					
					if(lateHalfDayObj != null) // If Attendance Settings are defined
					{
						if(lateHalfDayObj.length > 0)
						{
							//Get number of late marks for a day to adjust with leave
							String levLateNo = String.valueOf(lateHalfDayObj[0][0]);
							
							//Get number of late marks for half days to adjust with leave
							String hDayLateNo = String.valueOf(lateHalfDayObj[0][1]);
							
							//Get leave codes against late mark
							String lateLevCodes = String.valueOf(lateHalfDayObj[0][2]);
							String[] lateLevIDs = lateLevCodes.split(",");
							
							//Get leave codes against half days
							String halfDayLevCodes = String.valueOf(lateHalfDayObj[0][3]);
							String[] halfDayLevIDs = halfDayLevCodes.split(",");
							
							/**
							 * If leave codes are mention for late marks, then only adjust the late marks with leaves.
							 * Otherwise adjust late marks with half days
							**/
							if(lateLevCodes.length() > 0 && !(levLateNo.equals("") || levLateNo.equals("null")))
							{
								//Adjust late marks against leaves
								levLateAdjObj = adjustLateMarks(attnDtlObj, lateLevIDs, lateMark, levLateNo, bean);
								
								//Adjust half days against leaves
								levHalfAdjObj = adjustHalfDays(levLateAdjObj, halfDayLevIDs, newHalfDay, bean);
								
								//Save leave details and update leave balances
								result = saveAttendanceDtl(levHalfAdjObj, empCode, attnCode, month, year, true);
							}
							//Adjust late marks with half days
							else if(halfDayLevCodes.length() > 0 && !(hDayLateNo.equals("") || hDayLateNo.equals("null")))
							{
								double hdLateNo = Double.parseDouble(hDayLateNo);
								double dblLateMark = Math.floor(Double.parseDouble(String.valueOf(lateMark / hdLateNo)));
								newHalfDay += dblLateMark;
								
								//Adjust half days against leaves
								levHalfAdjObj = adjustHalfDays(attnDtlObj, halfDayLevIDs, newHalfDay, bean);
								
								//Save leave details and update leave balances
								result = saveAttendanceDtl(levHalfAdjObj, empCode, attnCode, month, year, true);
							}
							else if(!(levLateNo.equals("") || levLateNo.equals("null")))
							{
								double unpaidLM = (int)(lateMark / Double.parseDouble(levLateNo)); //Converts late marks into days
								bean.setUnpaidLM(unpaidLM);
								
								double unpaidHD = Double.parseDouble(String.valueOf(newHalfDay)) / 2; //Converts half days into days
								bean.setUnpaidHD(unpaidHD);
							}
							else if(!(hDayLateNo.equals("") || hDayLateNo.equals("null")))
							{
								double hdLateNo = Double.parseDouble(hDayLateNo);
								double dblLateMark = Math.floor(Double.parseDouble(String.valueOf(lateMark / hdLateNo)));
								newHalfDay += dblLateMark;
								
								double unpaidHD = Double.parseDouble(String.valueOf(newHalfDay)) / 2; //Converts half days into days
								bean.setUnpaidHD(unpaidHD);
							}
							else
							{
								//Save leave details and update leave balances
								result = saveAttendanceDtl(attnDtlObj, empCode, attnCode, month, year, false);
							}
						}
					}
					else
					{
						//Save leave details and update leave balances
						result = saveAttendanceDtl(attnDtlObj, empCode, attnCode, month, year, false);
					}
					
					/**
					 * Adjust Paid Leaves
					**/
					double paidLM = bean.getPaidLM();
					double paidHD = bean.getPaidHD();
					double paidLMHD = paidLM + paidHD;
					paidLev += paidLMHD;
					attdnDay -= paidLMHD;
					
					/**
					 * Adjust UnPaid Leaves
					**/
					double unpaidLM = bean.getUnpaidLM();
					double unpaidHD = bean.getUnpaidHD();
					double unpaidLMHD = unpaidLM + unpaidHD;
					unPaidLev += unpaidLMHD;
					attdnDay -= unpaidLMHD;
					salDay -= unpaidLMHD;
				} //end of if statement
				
				/**
				 * Insert new record in HRMS_MONTH_ATTENDANCE_year
				**/
				String monthAttnQuery = " INSERT INTO HRMS_MONTH_ATTENDANCE_" + year +
				" (ATTN_CODE, ATTN_EMP_ID, ATTN_DAYS, ATTN_WOFF, ATTN_HOLIDAY, ATTN_PAID_LEVS, ATTN_UNPAID_LEVS, " +
				" ATTN_SAL_DAYS, ATTN_LATE_MARKS, ATTN_HALF_DAYS, ATTN_SYSTEM_UNPAID, ATTN_MANUAL_UNPAID,ATTN_RECOVERY_DAYS) " +
				" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";				
				Object[][] monthAttdnSave = new Object[1][13];
				monthAttdnSave[0][0] = attnCode;
				monthAttdnSave[0][1] = empCode;
				monthAttdnSave[0][2] = String.valueOf(attdnDay);
				monthAttdnSave[0][3] = String.valueOf(weekOff);
				monthAttdnSave[0][4] = String.valueOf(holiday);
				monthAttdnSave[0][5] = String.valueOf(paidLev);
				monthAttdnSave[0][6] = String.valueOf(unPaidLev);
				monthAttdnSave[0][7] = String.valueOf(salDay);
				monthAttdnSave[0][8] = String.valueOf(lateMark);
				monthAttdnSave[0][9] = String.valueOf(halfDay);
				monthAttdnSave[0][10] = String.valueOf(sysUnpaid);
				monthAttdnSave[0][11] = String.valueOf(manUnpaid);	
				monthAttdnSave[0][12] = String.valueOf(recDays);
				res = getSqlModel().singleExecute(monthAttnQuery, monthAttdnSave);
			} //end of for loop
			return result || res;
		}
		catch (Exception e)
		{
			logger.error(e);
			return false;
		} //end of try-catch block
	} //end of getAttdnToSave	
	
	/**
	 * Get employees from Employee Office
	**/
	/**
	 * @param bean
	 * @param toDate
	 * @param attdnCode 
	 * @return Object[][] containing employee data
	**/
	public Object[][] getEmpIDs(MonthAttendance bean, String toDate, String attdnCode, String year, boolean lockFlag)
	{
		Object[][] eListObj = null;
		try
		{
			String eListSql = "";
			if(lockFlag)
			{
				eListSql = " SELECT EMP_ID, TRIM(EMP_TOKEN), TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, " +
				" TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY') JOIN_DATE, 'SAVE' FLAG, ATTN_DAYS, ATTN_WOFF, ATTN_HOLIDAY, " +
				" ATTN_PAID_LEVS, ATTN_UNPAID_LEVS, ATTN_SAL_DAYS, NVL(ATTN_LATE_MARKS, 0) ATTN_LATE_MARKS, " +
				" NVL(ATTN_HALF_DAYS, 0) ATTN_HALF_DAYS , NVL(ATTN_RECOVERY_DAYS, 0) ATTN_RECOVERY_DAYS " +
				" FROM HRMS_MONTH_ATTENDANCE_" + year +
				" INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE) " +
				" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_EMP_ID) " +
				" WHERE HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE = " + attdnCode +
				" ORDER BY EMP_CENTER, EMP_DEPT, UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME), EMP_TOKEN ";
			}
			else
			{
				if(attdnCode.equals(""))
				{
					eListSql = " SELECT EMP_ID, TRIM(EMP_TOKEN), EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ENAME, " +
					" TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY') JOIN_DATE, 'NEW' FLAG " +
					" FROM HRMS_EMP_OFFC " +
					" WHERE EMP_STATUS = 'S' AND EMP_REGULAR_DATE <= TO_DATE('" + toDate + "','DD-MM-YYYY') ";
					eListSql = setEmpOffcFiletrs(bean, eListSql);
					eListSql += " ORDER BY EMP_CENTER, EMP_DEPT, UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME), EMP_TOKEN ";
				}
				else
				{
					eListSql = " SELECT DISTINCT EMP_ID, EMP_TOKEN, ENAME, JOIN_DATE, FLAG, ATTN_DAYS, ATTN_WOFF, " +
					" ATTN_HOLIDAY, ATTN_PAID_LEVS, ATTN_UNPAID_LEVS, ATTN_SAL_DAYS, ATTN_LATE_MARKS, ATTN_HALF_DAYS, ATTN_RECOVERY_DAYS, " +
					" EMP_CENTER, EMP_DEPT FROM " +
					" ( " +
					" 	SELECT EMP_ID, TRIM(EMP_TOKEN) EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ENAME, " +
					" 	TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY') JOIN_DATE, 'NEW' FLAG, 0 ATTN_DAYS, 0 ATTN_WOFF, 0 ATTN_HOLIDAY, " +
					" 	0 ATTN_PAID_LEVS, 0 ATTN_UNPAID_LEVS, 0 ATTN_SAL_DAYS, 0 ATTN_LATE_MARKS, 0 ATTN_HALF_DAYS, 0 ATTN_RECOVERY_DAYS, " +
					"	EMP_CENTER, EMP_DEPT " +
					"	FROM HRMS_EMP_OFFC " +
					"	WHERE EMP_STATUS = 'S' AND EMP_REGULAR_DATE <= TO_DATE('" + toDate + "','DD-MM-YYYY')";
					eListSql = setEmpOffcFiletrs(bean, eListSql);
					eListSql += " 	AND EMP_ID NOT IN (SELECT ATTN_EMP_ID FROM HRMS_MONTH_ATTENDANCE_" + year + 
					" 	WHERE ATTN_CODE = " + attdnCode + ") " +
					"	UNION " +
					" 	SELECT EMP_ID, TRIM(EMP_TOKEN), TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME, " +
					"	TO_CHAR(EMP_REGULAR_DATE, 'DD-MM-YYYY') JOIN_DATE, 'SAVE' FLAG, ATTN_DAYS, ATTN_WOFF, ATTN_HOLIDAY, " +
					" 	ATTN_PAID_LEVS, ATTN_UNPAID_LEVS, ATTN_SAL_DAYS, NVL(ATTN_LATE_MARKS, 0) ATTN_LATE_MARKS, " +
					"	NVL(ATTN_HALF_DAYS, 0) ATTN_HALF_DAYS , NVL(ATTN_RECOVERY_DAYS,0) ATTN_RECOVERY_DAYS, EMP_CENTER, EMP_DEPT  " +
					"	FROM HRMS_MONTH_ATTENDANCE_" + year +
					"	INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE) " +
					"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_EMP_ID) " +
					" 	WHERE HRMS_MONTH_ATTENDANCE_" + year + ".ATTN_CODE = " + attdnCode +
					" ) " +
					" ORDER BY EMP_CENTER, EMP_DEPT, UPPER(ENAME), EMP_TOKEN ";
				}
			}
			eListObj = getSqlModel().getSingleResult(eListSql);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return eListObj;
	} //end of getEmpIDs
	
	/**
	 * Set filters on Page Load
	**/
	/**
	 * @param bean
	**/
	public void getFilters(MonthAttendance bean)
	{
		try
		{
			String payFiltersSql = " SELECT DECODE(CONF_BRN_FLAG, 'Y', 'true','N','false') BRN_FLAG, " +
			" DECODE(CONF_DEPT_FLAG, 'Y', 'true', 'N', 'false') DEPT_FLAG, " +
			" DECODE(CONF_PAYBILL_FLAG, 'Y', 'true', 'N', 'false') PAYBILL_FLAG, " +
			" DECODE(CONF_EMPTYPE_FLAG, 'Y', 'true', 'N', 'false') EMPTYPE_FLAG, " +
			" DECODE(CONF_DIVISION_FLAG, 'Y', 'true', 'N', 'false') DIVISION_FLAG, " +
			" DECODE(CONF_RECOVERY_FLAG, 'Y', 'true', 'N', 'false') RECOVERY_FLAG " +
			" FROM HRMS_SALARY_CONF ";
			Object[][] payFiltersObj = getSqlModel().getSingleResult(payFiltersSql);
			bean.setBrnFlag(Boolean.parseBoolean(String.valueOf(payFiltersObj[0][0])));
			bean.setDeptFlag(Boolean.parseBoolean(String.valueOf(payFiltersObj[0][1])));
			bean.setPayBillFlag(Boolean.parseBoolean(String.valueOf(payFiltersObj[0][2])));
			bean.setTypeFlag(Boolean.parseBoolean(String.valueOf(payFiltersObj[0][3])));
			bean.setDivFlag(Boolean.parseBoolean(String.valueOf(payFiltersObj[0][4])));
			bean.setRecoveryFlag(String.valueOf(payFiltersObj[0][5]));
			
			String attnFiltersSql = " SELECT DECODE(CONF_DAILY_ATT_CONNECT_FLAG, 'Y', 'true', 'N', 'false') ATTN_CONN_FLAG, " +
			" DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false') BRN_HDAY_FLAG, " +
			" DECODE(CONF_LEAVE_CONNECT_FLAG, 'Y', 'true', 'N', 'false') LEV_CONN_FLAG " +
			" FROM HRMS_ATTENDANCE_CONF ";
			Object[][] attnFiltersObj = getSqlModel().getSingleResult(attnFiltersSql);
			bean.setAttConnFlag(Boolean.parseBoolean(String.valueOf(attnFiltersObj[0][0])));
			bean.setBrnHDayFlag(Boolean.parseBoolean(String.valueOf(attnFiltersObj[0][1])));
			bean.setLevConnFlag(Boolean.parseBoolean(String.valueOf(attnFiltersObj[0][2])));
			
			Calendar c = Calendar.getInstance();
			String currYear = String.valueOf(c.get(Calendar.YEAR));
			bean.setYear(currYear);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
	} //end of getFilters
	
	public String getFromDate(String joinDate, String fDate)
	{
		try
		{
			Calendar joinCal = Calendar.getInstance();
			joinCal.setTime(Utility.getDate(joinDate));
			
			Calendar fromCal = Calendar.getInstance();
			fromCal.setTime(Utility.getDate(fDate));
			
			if(joinCal.after(fromCal))
			{
				return joinDate;
			}
			else
			{
				return fDate;
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			return "";
		}
	}
	
	/**
	 * Get configuration done to adjust late marks and half days
	**/
	/**
	 * @return Object[][] as settings done against late marks and half days
	**/
	public Object[][] getLateMarksHalfDays()
	{
		Object[][] lmhdObj = null;
		try
		{
			String lmhdSql = " SELECT CONF_LATEMARKS_FOR_LEAVE, CONF_LATEMARKS_FOR_HALFDAY, CONF_LATEMARKS_LEAVEADJUST, " +
			" CONF_HALFDAY_LEAVEADJUST FROM HRMS_ATTENDANCE_CONF ";
			lmhdObj = getSqlModel().getSingleResult(lmhdSql);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return lmhdObj;
	} //end of getLateMarksHalfDays
	
	/**
	 * Get the leaves approved
	**/
	/**
	 * @param empCode
	 * @param month
	 * @param year
	 * @return Object[][] as leaves approved for an employee in a particular month and year
	**/
	public Object[][] getLeavesApplied(String empCode, String fromDate, String toDate, String year)
	{
		Object[][] attnDtlObj = null;
		try
		{
			String attnDtlSql = " SELECT DISTINCT HRMS_LEAVE_POLICY.LEAVE_CODE, NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE + SUM(NVL(LEAVE_DAYS, 0)), 0) OPEN_BAL,  " +
			" SUM(NVL(LEAVE_DAYS, 0)) LEAVE_DAYS, 0 LM_ADJUST, 0 HD_ADJUST, HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE CLOSE_BAL FROM HRMS_LEAVE_DTLHISTORY " +
			" RIGHT JOIN HRMS_LEAVE_POLICY ON(HRMS_LEAVE_POLICY.LEAVE_CODE = HRMS_LEAVE_DTLHISTORY.LEAVE_CODE " +
			" AND HRMS_LEAVE_POLICY.EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID = " + empCode + ") " +
			" AND LEAVE_APPLICABLE = 'Y' AND HRMS_LEAVE_POLICY.LEAVE_PAID_UNPAID = 'P') " +
			" INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE = HRMS_LEAVE_POLICY.LEAVE_CODE " +
			" AND HRMS_LEAVE_POLICY.EMP_TYPE = (SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE EMP_ID = " + empCode + ") " +
			" AND HRMS_LEAVE_BALANCE.EMP_ID = " + empCode + ") " +
			" WHERE LEAVE_DTL_STATUS = 'A' AND HRMS_LEAVE_DTLHISTORY.EMP_ID = " + empCode + " AND LEAVE_FROM_DATE >= TO_DATE('" + fromDate + "', 'DD-MM-YYYY') " +
			" AND LEAVE_TO_DATE <= TO_DATE('" + toDate + "', 'DD-MM-YYYY') " +
			" GROUP BY HRMS_LEAVE_POLICY.LEAVE_CODE, HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE ";
			attnDtlObj = getSqlModel().getSingleResult(attnDtlSql);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return attnDtlObj;
	} //end of getLeavesApplied
	
	/**
	 * Calculate total number of days in month or within range of dates
	**/
	/**
	 * @param bean
	 * @param attConnFlag
	 * @return String as total number of days in month or within range of dates
	**/
	public int getMonthDays(String month, String year)
	{
		int monthDays = 0;
		try
		{
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			cal.setTime(Utility.getDate("01-" + month + "-" + year));
			monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return monthDays;
	} //end of getMonthDays
	
	/**
	 * Calculate total number of days of an employee in daily attendance
	**/	
	/**
	 * @param monthFlag : Specifies whether attendance is processing from previous month or in current month
					P : Previous Month			C : Current Month
	 * @param month : Specifies whether month is December or not.
	 * @param year : Specifies for which year daily attendance is retrieving
	 * @param eId : Specifies unique employee code
	 * @param fromDate : Specifies from which date daily attendance is retrieving
	 * @param toDate : Specifies to which date daily attendance is retrieving
	 * @return int as total number of days of an employee in daily attendance
	**/
	public int getTotalDailyAttdn(String monthFlag, String month, String year, String eId, String fromDate, String toDate)
	{
		int presentDays = 0;
		try
		{
			String dailyAttdnSql = "";
			
			if(monthFlag.equals("P") && month.equals("01")) //Sum of days from Previous Month date and Current Month date, if Previous Month is December
			{
				dailyAttdnSql =
				" SELECT NVL(SUM(PRESENTDAYS), 0) FROM " +
				" ( " +
				" 	SELECT COUNT(*) PRESENTDAYS, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + year +
				" 	WHERE ATT_EMP_ID = " + eId + " GROUP BY ATT_DATE " +
				" 	UNION " +
				" 	SELECT COUNT(*) PRESENTDAYS, ATT_DATE FROM HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) +
				" 	WHERE ATT_EMP_ID = " + eId + " GROUP BY ATT_DATE " +
				" ) " +
				" WHERE ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
			} //end of if statement
			else //Sum of days in Current Month
			{
				dailyAttdnSql = " SELECT COUNT(*) PRESENTDAYS FROM HRMS_DAILY_ATTENDANCE_" + year +
				" WHERE ATT_EMP_ID = " + eId +
				" AND ATT_DATE BETWEEN TO_DATE('" + fromDate + "', 'DD-MM-YYYY') AND TO_DATE('" + toDate + "', 'DD-MM-YYYY') ";
			} //end of else statement
			Object[][] dailyAttdnObj = getSqlModel().getSingleResult(dailyAttdnSql);
			presentDays = Integer.parseInt(String.valueOf(dailyAttdnObj[0][0]));
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return presentDays;
	} //end of getTotalDailyAttdn
	
	public boolean isRecalculate(MonthAttendance bean, String monthFlag, String month, String year, String eId, double salDays, double presentDays, double unPaidLevs, double attdnDays)
	{
		boolean recal = false;
		try
		{
			Object[][] levDailyObj = null;
			
			if(!bean.isLockFlag() && bean.isSavedFlag())
			{
				if(bean.isLevConnFlag() && bean.isAttConnFlag())
				{
					String levDailySql = "";
					if(monthFlag.equals("P") && month.equals("01")) //Previous Month and Current Month Weekly Offs, if Previous Month is December
					{
						levDailySql = " SELECT * FROM " +
						" ( " +
						"	SELECT EMP_ID, ATT_DATE, LEAVE_FROM_DATE, LEAVE_TO_DATE, ATT_STATUS_ONE FROM HRMS_LEAVE_DTLHISTORY " +
						" 	INNER JOIN HRMS_DAILY_ATTENDANCE_" + year + " ON(HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID = HRMS_LEAVE_DTLHISTORY.EMP_ID) " +
						"	UNION " +
						" 	SELECT EMP_ID, ATT_DATE, LEAVE_FROM_DATE, LEAVE_TO_DATE, ATT_STATUS_ONE FROM HRMS_LEAVE_DTLHISTORY " +
						" 	INNER JOIN HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) + " ON(HRMS_DAILY_ATTENDANCE_" + (Integer.parseInt(year) - 1) + ".ATT_EMP_ID = HRMS_LEAVE_DTLHISTORY.EMP_ID) " +
						" ) " +
						" WHERE ATT_STATUS_ONE != 'AB' AND EMP_ID = " + eId + " AND ATT_DATE BETWEEN LEAVE_FROM_DATE AND LEAVE_TO_DATE ";
					}
					else
					{
						levDailySql = " SELECT * FROM HRMS_LEAVE_DTLHISTORY " +
						" INNER JOIN HRMS_DAILY_ATTENDANCE_" + year + " ON(HRMS_DAILY_ATTENDANCE_" + year + ".ATT_EMP_ID = HRMS_LEAVE_DTLHISTORY.EMP_ID) " +
						" WHERE ATT_STATUS_ONE != 'AB' AND EMP_ID = " + eId + " AND ATT_DATE BETWEEN LEAVE_FROM_DATE AND LEAVE_TO_DATE ";
					}
					levDailyObj = getSqlModel().getSingleResult(levDailySql);
				}
				
				if((levDailyObj != null && levDailyObj.length > 0) || salDays > presentDays || unPaidLevs < 0 || attdnDays < 0)
				{
					recal = true;
				}
			}
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return recal;
	}
	
	/**
	 * Lock the attendance
	**/
	/**
	 * @param bean
	 * @param request
	 * @param response
	 * @return String as whether attendance has locked or not
	**/
	public String lockAttendance(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		String res = "";
		boolean result = false;
		try
		{
			String attnCode = bean.getAttdnCode();
			String oldStatus = bean.getStatus().trim();
			String newStatus = "";
			
			/**
			 * Locks the attendance only when attendance has started or attendance has unlocked.
			**/
			String statusQuery = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS = ";
			if(oldStatus.equals("ATTN_START")) //Changes the status from ATTN_START to ATTN_READY
			{
				statusQuery += " 'ATTN_READY' ";
				newStatus = "ATTN_READY";
			} //end of if statement
			else if(oldStatus.equals("ATTN_UNLOCK")) //Changes the status from ATTN_UNLOCK to SAL_START
			{
				statusQuery += " 'SAL_START' ";
				newStatus = "SAL_START";
			} //end of else if statement
			statusQuery += " WHERE LEDGER_CODE = " + attnCode;
			result = getSqlModel().singleExecute(statusQuery);
			
			if(result)
			{
				res = "Attendance locked successfully.";
				bean.setStatus(newStatus);
				bean.setLockFlag(true);
				bean.setUnlockFlag(false);
				showAttendance(bean, request); //Set saved records of attendance in a page
			} //end of if statement
			else
			{
				res = "Attendance can't lock.";
				bean.setLockFlag(false);
				bean.setUnlockFlag(true);
				
				if(bean.isAttConnFlag())
				{
					connectedAttn(bean, request);
				}
				else
				{
					processedAttn(bean, request, response);
				}
			} //end of else statement
		}
		catch (Exception e)
		{
			res = "Attendance can't lock.";
			bean.setLockFlag(false);
			bean.setUnlockFlag(true);
			logger.error(e);
		} //end of try-catch block
		bean.setSavedFlag(true);
		bean.setProcess(true);
		return res;
	} //end of lockAttendance
	
	/**
	 * Process the attendance. If attendance is coming from Daily Attendance, this method won't be used. 
	**/
	/**
	 * @param bean
	 * @param request
	 * @param response
	 * @return String as message when attendance is not available
	**/
	public String processedAttn(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		String res = "";
		try
		{
			String month = Integer.parseInt(bean.getMonth()) < 10 ? "0" + bean.getMonth() : bean.getMonth();
			String year = bean.getYear();
			String attdnCode = bean.getAttdnCode();
			boolean brnHDayFlag = bean.isBrnHDayFlag();
			boolean levConnFlag = bean.isLevConnFlag();
			
			String fromDate = "";
			
			if(bean.isSavedFlag())
			{
				bean.setMonthName(Utility.month(Integer.parseInt(month))); //Set month name by passing month as integer
			}
			
			String fDate = "01-" + month + "-" + year;
			int monthDays = getMonthDays(month, year); //Calculate total days of a month
			String toDate = monthDays + "-" + month + "-" + year;
			
			// Get list of employees containing saved as well as non saved employees
			Object[][] eListObj = getEmpIDs(bean, toDate, attdnCode, year, false);
			
			doPaging(bean, eListObj.length, new Object[][] {}, request, false);
			int fromTotRec = Integer.parseInt(bean.getFromTotRec());
			int toTotRec = Integer.parseInt(bean.getToTotRec());
			
			if(eListObj != null && eListObj.length > 0)
			{
				ArrayList<Object> attdnList = new ArrayList<Object>();
				for(int i = fromTotRec; i < toTotRec; i++)
				{
					Object[][] attdnObj = new Object[1][11];
					
					String eId = String.valueOf(eListObj[i][0]);
					String eToken = String.valueOf(eListObj[i][1]);
					String eName = String.valueOf(eListObj[i][2]);
					String eJoinDate = String.valueOf(eListObj[i][3]);
					String eFlag = String.valueOf(eListObj[i][4]);
					
					fromDate = getFromDate(eJoinDate, fDate); // Calculates from date
					
					if(eFlag.equals("SAVE")) //If attendance is already saved, then take record from saved attendance
					{
						attdnObj[0][0] = String.valueOf(eListObj[i][5]); //ATTN_DAYS
						attdnObj[0][1] = String.valueOf(eListObj[i][6]); //ATTN_WOFF
						attdnObj[0][2] = String.valueOf(eListObj[i][7]); //ATTN_HOLIDAY
						attdnObj[0][3] = String.valueOf(eListObj[i][8]); //ATTN_PAID_LEVS
						attdnObj[0][4] = String.valueOf(eListObj[i][9]); //ATTN_UNPAID_LEVS
						attdnObj[0][5] = String.valueOf(eListObj[i][10]); //ATTN_SAL_DAYS
						attdnObj[0][6] = String.valueOf(eListObj[i][11]); //ATTN_LATE_MARKS
						attdnObj[0][7] = String.valueOf(eListObj[i][12]); //ATTN_HALF_DAYS
						attdnObj[0][8] = "true"; //ESAVE
						
						double presDays = calPresentDays(fromDate, toDate, month, year);
						attdnObj[0][9] = String.valueOf(presDays); //PRESENT_DAYS
						attdnObj[0][10] = String.valueOf(eListObj[i][13]); //RECOVERY_DAYS
					}
					else // If employee is not saved, then calculate the attendance
					{
						attdnObj = calculateAttdn(brnHDayFlag, levConnFlag, false, "", month, year, eId, fromDate, toDate, false, "", "", "");
					}
					
					/**
					 * Set the records on a page using bean
					**/
					if(attdnObj != null || attdnObj.length > 0)
					{
						MonthAttendance bean1 = new MonthAttendance();
						bean1.setEId(eId);
						bean1.setEToken(eToken);
						bean1.setEName(eName);
						bean1.setEJoinDate(eJoinDate);
						bean1.setAttdnDays(String.valueOf(attdnObj[0][0]));
						bean1.setWeeklyOffs(String.valueOf(attdnObj[0][1]));
						bean1.setHolidays(String.valueOf(attdnObj[0][2]));
						bean1.setPaidLevs(String.valueOf(attdnObj[0][3]));
						bean1.setUnPaidLevs(String.valueOf(attdnObj[0][4]));
						bean1.setSalDays(String.valueOf(attdnObj[0][5]));
						bean1.setLateMarks(String.valueOf(attdnObj[0][6]));
						bean1.setHalfDays(String.valueOf(attdnObj[0][7]));
						bean1.setESave(String.valueOf(attdnObj[0][8]));
						bean1.setPresentDays(String.valueOf(attdnObj[0][9]));
						bean1.setDailyConFlag(false);
						bean1.setLockFlag(bean.isLockFlag());
						bean1.setTotAbs(String.valueOf(calTotalAbsents("", fromDate, toDate, month, year, eId, false)));
						bean1.setRecoveryDays(String.valueOf(attdnObj[0][10]));
						bean1.setRecoveryFlag(bean.getRecoveryFlag());
						double salDays = Double.parseDouble(bean1.getSalDays());
						double presentDays = Double.parseDouble(bean1.getPresentDays());
						double unPaidLevs = Double.parseDouble(bean1.getUnPaidLevs());
						double attdnDays = Double.parseDouble(bean1.getAttdnDays());							
						bean1.setReCal(isRecalculate(bean, "", month, year, eId, salDays, presentDays, unPaidLevs, attdnDays));
						
						bean1.setFromDate(fromDate);
						bean1.setToDate(toDate);
						
						attdnList.add(bean1);
					} //end of if statement
				} //end of for loop
				
				bean.setAttdnList(attdnList);
				bean.setFlag(true);
			} //end of if statement
			else
			{
				res = "Attendance Not Available!";
				reset(bean);
			} //end of else statement
		}
		catch (Exception e)
		{
			logger.error(e);
			res = "Attendance Not Available!";
			reset(bean);
		} //end of try-catch block
		return res;
	} //end of processedAttn
	
	public void reCalculate(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String month = Integer.parseInt(bean.getMonth()) < 10 ? "0" + bean.getMonth() : bean.getMonth();
			String year = bean.getYear();
			boolean attnConnFlag = bean.isAttConnFlag();
			String attCode = bean.getAttdnCode();
			boolean brnHDayFlag = bean.isBrnHDayFlag();
			boolean levConnFlag = bean.isLevConnFlag();
			
			String fromDate = "", startDate = "", monthFlag = "", responseText = "";
			Object[][] reCalObj = null;
			
			String fDate = "01-" + month + "-" + year;
			int monthDays = getMonthDays(month, year); //Calculate total days of a month
			String toDate = monthDays + "-" + month + "-" + year;
			
			String remEmps = request.getParameter("remEmps");
			String remDate = request.getParameter("remDate");
			String remLMs = request.getParameter("remLMs");
			String remHDs = request.getParameter("remHDs");
			
			Object[] remEmpList = remEmps.split(",");
			Object[] remDateList = remDate.split(",");
			Object[] remLMList = remLMs.split(",");
			Object[] remHDList = remHDs.split(",");
			
			if(remEmpList != null || remEmpList.length > 0)
			{
				if(attnConnFlag)
				{
					String monthDateQuery = " SELECT CONF_SALARY_START_DATE, CONF_SALARY_START_FLAG FROM HRMS_ATTENDANCE_CONF ";			
					Object[][] dateFlagObject = getSqlModel().getSingleResult(monthDateQuery);

					startDate = String.valueOf(dateFlagObject[0][0]);
					startDate = Integer.parseInt(startDate) < 10 ? "0" + startDate : startDate;
					monthFlag = String.valueOf(dateFlagObject[0][1]);
					
					String newMonth = "";
					String newYear = "";							

					if(monthFlag.equals("P"))
					{
						if(month.equals("01"))
						{
							newMonth = "12";
							newYear = String.valueOf(Integer.parseInt(year) - 1);
						}
						else
						{
							newMonth = String.valueOf(Integer.parseInt(month) - 1);
							newMonth = Integer.parseInt(newMonth) < 10 ? "0" + newMonth : newMonth;
							newYear = year;
						}
						fDate = startDate + "-" + newMonth + "-" + newYear;
						toDate = startDate + "-" + month + "-" + year;
					} //end of if statement
				} //end of if statement
				
				for(int i = 0; i < remEmpList.length; i++)
				{
					boolean dailyConFlag = attnConnFlag;
					
					String eId = String.valueOf(remEmpList[i]);
					String eJoinDate = String.valueOf(remDateList[i]);
					String lateMark = String.valueOf(remLMList[i]);
					String halfDay = String.valueOf(remHDList[i]);
					
					fromDate = getFromDate(eJoinDate, fDate); // Calculate from date
					
					if(dailyConFlag)
					{
						int dailyDays = getTotalDailyAttdn(monthFlag, month, year, eId, fromDate, toDate);
						if(dailyDays == 0)
						{
							dailyConFlag = false;
						}
					}										
					reCalObj = calculateAttdn(brnHDayFlag, levConnFlag, dailyConFlag, monthFlag, month, year, eId, fromDate, toDate, true, attCode, lateMark, halfDay);
					
					responseText += eId;
					for(int j = 0; j < reCalObj[0].length; j++)
					{
						responseText += "," + String.valueOf(reCalObj[0][j]);
					}
					responseText += "@";
				}
			}
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(responseText);
			out.flush();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
	}
	
	public String remove(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		String res = "";
		boolean result = false;
		try
		{
			String year = bean.getYear();
			String attnCode = bean.getAttdnCode();
			
			String remEmps = request.getParameter("remEmps");
			Object[] remEmpList = remEmps.split(",");
			
			if(remEmpList != null || remEmpList.length > 0)
			{
				for(int i = 0; i < remEmpList.length; i++)
				{
					String eId = String.valueOf(remEmpList[i]);
					
					String attnDtlSql = " SELECT ATT_LEAVE_CODE, NVL(ATT_MANUAL_ADJUST, 0) + NVL(ATT_LATEMARK_ADJUST, 0) + " +
					" NVL(ATT_HALFDAY_ADJUST, 0) MONTH_DTL_ADJ FROM HRMS_MONTH_ATT_DTL_" + year + 
					" WHERE ATT_CODE = " + attnCode + " AND ATT_EMP_CODE = " + eId;
					Object[][] attnDtlObj = getSqlModel().getSingleResult(attnDtlSql);
					
					if(attnDtlObj != null && attnDtlObj.length > 0)
					{
						String updateBalSql = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? " +
						" WHERE EMP_ID = ? AND LEAVE_CODE = ? ";
						Object[][] updateBalObj = null;
						for(int j = 0; j < attnDtlObj.length; j++)
						{
							updateBalObj = new Object[attnDtlObj.length][3];
							updateBalObj[j][0] = String.valueOf(attnDtlObj[j][1]); // ATT_MANUAL_ADJUST
							updateBalObj[j][1] = eId;
							updateBalObj[j][2] = String.valueOf(attnDtlObj[j][0]); // ATT_LEAVE_CODE
						}
						result = getSqlModel().singleExecute(updateBalSql, updateBalObj);
					}
					
					String remAttnSql = " DELETE FROM HRMS_MONTH_ATTENDANCE_" + year + 
					" WHERE ATTN_CODE = " + attnCode + " AND ATTN_EMP_ID = " + eId;
					result = getSqlModel().singleExecute(remAttnSql);
					
					if(result)
					{
						String remAttnDtlSql = " DELETE FROM HRMS_MONTH_ATT_DTL_" + year + 
						" WHERE ATT_CODE = " + attnCode + " AND ATT_EMP_CODE = " + eId;
						result = getSqlModel().singleExecute(remAttnDtlSql);
					}
					
					if(result)
					{
						String remSal = " DELETE FROM HRMS_SALARY_" + year +
						" WHERE SAL_LEDGER_CODE = '" + attnCode + "' AND EMP_ID = " + eId;
						result = getSqlModel().singleExecute(remSal);
						
						String remSalDr = " DELETE FROM HRMS_SAL_DEBITS_" + year +
						" WHERE SAL_LEDGER_CODE = '" + attnCode + "' AND EMP_ID = " + eId;
						result = getSqlModel().singleExecute(remSalDr);
						
						String remSalCr = " DELETE FROM HRMS_SAL_CREDITS_" + year +
						" WHERE SAL_LEDGER_CODE = '" + attnCode + "' AND EMP_ID = " + eId;
						result = getSqlModel().singleExecute(remSalCr);
					}
				}
				
				/**
				 * If Attendance is locked, then it shows saved attendance. If not, it precesses the attendance.
				**/
				if(bean.getStatus().trim().equals("ATTN_START"))
				{
					if(bean.isAttConnFlag())
					{
						connectedAttn(bean, request);
					}
					else
					{
						processedAttn(bean, request, response);
					}
				} //end of if statement
				else
				{
					showAttendance(bean, request); //Set saved records of attendance in a page
				}
				
				if(result)
				{
					res = "Records deleted successfully.";
				}
				else
				{
					res = "Records can't be deleted.";
				}
			}
		}
		catch (Exception e)
		{
			res = "Record can't be deleted.";
			logger.error(e);
		}
		return res;
	}
	
	/**
	 * Resets the form fields
	**/
	public void reset(MonthAttendance bean)
	{
		try
		{
			bean.setAttdnCode("");
			bean.setMonth("");
			bean.setMonthName("0");
			
			Calendar c = Calendar.getInstance();
			String currYear = String.valueOf(c.get(Calendar.YEAR));
			bean.setYear(currYear);
			
			bean.setEmpToken("");
			bean.setEmpCode("");
			bean.setEmpName("");
			bean.setNewECode("");
			bean.setNewEToken("");
			bean.setNewEName("");
			bean.setPayBillNo("");
			bean.setPayBillName("");
			bean.setTypeCode("");
			bean.setTypeName("");			
			bean.setBrnCode("");
			bean.setBrnName("");			
			bean.setDeptCode("");
			bean.setDeptName("");			
			bean.setDivCode("");
			bean.setDivName("");
			bean.setFlag(false);
			bean.setAttdnList(null);
			bean.setBrnFlag(false);
			bean.setDeptFlag(false);
			bean.setPayBillFlag(false);
			bean.setTypeFlag(false);
			bean.setDivFlag(false);
			bean.setStatus("");			
			bean.setStatusFlag(false);
			bean.setLockFlag(false);
			bean.setSavedFlag(false);
			bean.setSearchFlag(false);
			bean.setHiddenEmpid("");
			bean.setRowid("");
			bean.setHdPage("");
			bean.setFromTotRec("");
			bean.setToTotRec("");
			bean.setEIds(null);
			bean.setEmpSearch(false);
			bean.setPresentDays("");
			bean.setUnlockFlag(false);
			bean.setProcess(false);
			getFilters(bean);
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
	} //end of reset
	
	/**
	 * Saves the attendance
	**/
	/**
	 * @param bean
	 * @param request
	 * @param response
	 * @return String as message whether attendance is saved or not
	**/
	public String save(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		String res = "";
		try
		{
			boolean result = false;
			boolean result1 = false;
			String attdnCode = "";
			
			String month = bean.getMonth();
			String year = bean.getYear();
			String empType = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String divCode = bean.getDivCode();
			
			/* Check whether HRMS_MONTH_ATTENDANCE_year table is exists or not. If yes, i.e, if tblExists = true, then only save the record */
			boolean tblExists = false;
			Object[][] tblExistsObj = null;
			try
			{
				String tblExistsSql = " SELECT * FROM HRMS_MONTH_ATTENDANCE_" + year;
				tblExistsObj = getSqlModel().getSingleResult(tblExistsSql);
				if(tblExistsObj != null || tblExistsObj.length > 0)
				{
					tblExists = true;
				} //end of if statement
			}
			catch (Exception e)
			{
				logger.error(e);
				tblExists = false;
				res = "Record can't be saved!";
			} //end of try-catch block
			
			if(tblExists)
			{
				Object[] eId = request.getParameterValues("eId");
				if(eId != null && eId.length > 0)
				{
					String attdnCodeSql = " SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = " + month + 
					" AND LEDGER_YEAR = " + year;
					attdnCodeSql = setFiletrs(bean, attdnCodeSql);
					Object[][] attnCodeObj = getSqlModel().getSingleResult(attdnCodeSql);
					
					/* Check for the first time whether attendance is already saved or not. It saves only first page. */
					if(attnCodeObj.equals(null) || attnCodeObj.length < 1)
					{
						Object[][] attdnLedgrSave = new Object[1][7];
						attdnLedgrSave[0][0] = month;
						attdnLedgrSave[0][1] = year;
						attdnLedgrSave[0][2] = payBillNo.equals("") ? "" : payBillNo;
						attdnLedgrSave[0][3] = empType.equals("") ? "" : empType;
						attdnLedgrSave[0][4] = deptCode.equals("") ? "" : deptCode;
						attdnLedgrSave[0][5] = brnCode.equals("") ? "" : brnCode;
						attdnLedgrSave[0][6] = divCode.equals("") ? "" : divCode;
						
						String aLdgrQuery = " INSERT INTO HRMS_SALARY_LEDGER(LEDGER_CODE, LEDGER_MONTH, LEDGER_YEAR, " +
						" LEDGER_PAYBILL, LEDGER_EMPTYPE, LEDGER_DEPT, LEDGER_BRN, LEDGER_DIVISION, LEDGER_STATUS) " +
						" VALUES((SELECT NVL(MAX(LEDGER_CODE),0)+1 FROM HRMS_SALARY_LEDGER), ?, ?, ?, ?, ?, ?, ?, 'ATTN_START') ";
						result = getSqlModel().singleExecute(aLdgrQuery, attdnLedgrSave);
						
						/* After inserting record in HRMS_SALARY_LEDGER, take the relevant code for to insert in HRMS_MONTH_ATTENDANCE_year and HRMS_MONTH_ATT_DTL_year tables */
						String maxAttnQuery = " SELECT MAX(LEDGER_CODE) FROM HRMS_SALARY_LEDGER ";
						
						Object[][] maxAttnCode = getSqlModel().getSingleResult(maxAttnQuery);
						attdnCode = String.valueOf(maxAttnCode[0][0]);
						bean.setFirstSave(true);
					} //end of if statement
					else //Get attendance code to save remaining pages of attendance
					{
						attdnCode = String.valueOf(attnCodeObj[0][0]);
					}
					
					result1 = getAttdnToSave(bean, attdnCode, request);
					
					bean.setStatus("ATTN_START");
					bean.setAttdnCode(attdnCode);
					
					if(result && result1)
					{
						res = "Record saved successfully!";
					}
					else
					{
						res = "Record can't be saved!";
					}
					
					if(bean.isAttConnFlag()) //If saved only first page, then process the attendance again for remaining employees
					{
						connectedAttn(bean, request);
					}
					else
					{
						processedAttn(bean, request, response);
					}
					bean.setSavedFlag(true); //Attendance is partially saved, so set savedFlag to TRUE
					bean.setProcess(true);
					bean.setMonthName(Utility.month(Integer.parseInt(month)));
				} //end of if statement
				else
				{
					res = "Record can't be saved!";
				} //end of else statement
			} //end of if statement
			else
			{
				res = "Record can't be saved!";
			} //end of else statement
		}
		catch (Exception e)
		{
			logger.error(e);
			res = "Record can't be saved!";
		} //end of try-catch block
		return res;
	} //end of save
	
	/**
	 * Insert new records for leaves adjusted and update leave balances
	**/
	public boolean saveAttendanceDtl(Object[][] attndtl, String empCode, String attnCode, String month, String year, boolean flag)
	{
		boolean result = false;
		try
		{
			if(attndtl != null && attndtl.length > 0)//Get the leave details of an employee
			{
				Object[][] attndtlSaveObj = new Object[attndtl.length][8];
				Object[][] levBalSaveObj = new Object[attndtl.length][3];
				for(int i = 0; i < attndtl.length; i++)
				{
					//Insert record in HRMS_MONTH_ATT_DTL_year
					attndtlSaveObj[i][0] = empCode;
					attndtlSaveObj[i][1] = String.valueOf(attndtl[i][0]); //ATT_LEAVE_CODE
					attndtlSaveObj[i][2] = String.valueOf(attndtl[i][1]); //ATT_LEAVE_AVAILABLE
					attndtlSaveObj[i][3] = String.valueOf(attndtl[i][2]); //ATT_LEAVE_ADJUST
					attndtlSaveObj[i][4] = String.valueOf(attndtl[i][3]); //ATT_LATEMARK_ADJUST
					attndtlSaveObj[i][5] = String.valueOf(attndtl[i][4]); //ATT_HALFDAY_ADJUST
					attndtlSaveObj[i][6] = String.valueOf(attndtl[i][5]); //ATT_LEAVE_BALANCE
					attndtlSaveObj[i][7] = attnCode;
					
					//Update records in HRMS_LEAVE_BALANCE
					levBalSaveObj[i][0] = String.valueOf(attndtl[i][5]); //LEAVE_CLOSING_BALANCE
					levBalSaveObj[i][1] = empCode;
					levBalSaveObj[i][2] = String.valueOf(attndtl[i][0]); //LEAVE_CODE
				} //end of for loop
				
				String attnDtlSaveSql = " INSERT INTO HRMS_MONTH_ATT_DTL_" + year + " (ATT_EMP_CODE, ATT_LEAVE_CODE, " +
				" ATT_LEAVE_AVAILABLE, ATT_LEAVE_ADJUST, ATT_LATEMARK_ADJUST, ATT_HALFDAY_ADJUST, ATT_LEAVE_BALANCE, ATT_CODE) " +
				" VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
				result = getSqlModel().singleExecute(attnDtlSaveSql, attndtlSaveObj);
				
				if(flag && result)
				{
					String levBalSaveSql = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = ? " +
					" WHERE EMP_ID = ? AND LEAVE_CODE = ? ";
					result = getSqlModel().singleExecute(levBalSaveSql, levBalSaveObj);
				}				
			} //end of if statement
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
		return result;
	} //end of saveAttendanceDtl
	
	/**
	 * Set filters to records which are coming from Employee Office
	**/
	/**
	 * @param bean
	 * @param sqlQuery
	 * @return String as sql query
	**/
	public String setEmpOffcFiletrs(MonthAttendance bean, String sqlQuery)
	{
		try
		{
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String divCode = bean.getDivCode();
			
			if(!typeCode.equals(""))
			{
				sqlQuery += " AND EMP_TYPE = " + typeCode;
			} //end of if statement
			if(!payBillNo.equals(""))
			{
				sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
			} //end of if statement
			if(!brnCode.equals(""))
			{
				sqlQuery += " AND EMP_CENTER = " + brnCode;
			} //end of if statement
			if(!deptCode.equals(""))
			{
				sqlQuery += " AND EMP_DEPT = " + deptCode;
			} //end of if statement
			if(!divCode.equals(""))
			{
				sqlQuery += " AND EMP_DIV = " + divCode;
			} //end of if statement
			return sqlQuery;
		}
		catch (Exception e)
		{
			logger.error(e);
			return "";
		} //end of try-catch block
	} //end of setEmpOffcFiletrs
	
	/**
	 * Set filters to records which are coming from Attendance 
	**/
	/**
	 * @param bean
	 * @param sqlQuery
	 * @return String as sql query
	**/
	public String setFiletrs(MonthAttendance bean, String sqlQuery)
	{
		try
		{
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String divCode = bean.getDivCode();
			
			if(!typeCode.equals(""))
			{
				sqlQuery += " AND LEDGER_EMPTYPE = "+typeCode;
			} //end of if statement
			if(!payBillNo.equals(""))
			{
				sqlQuery += " AND LEDGER_PAYBILL = "+payBillNo;
			} //end of if statement
			if(!brnCode.equals(""))
			{
				sqlQuery += " AND LEDGER_BRN = "+brnCode;
			} //end of if statement
			if(!deptCode.equals(""))
			{
				sqlQuery += " AND LEDGER_DEPT = "+deptCode;
			} //end of if statement
			if(!divCode.equals(""))
			{
				sqlQuery += " AND LEDGER_DIVISION = "+divCode;
			} //end of if statement
			return sqlQuery;
		}
		catch (Exception e)
		{
			logger.error(e);
			return "";
		} //end of try-catch block
	} //end of setFiletrs
	
	/**
	 * Show saved attendance
	**/
	/**
	 * @param bean
	 * @param request
	**/
	public void showAttendance(MonthAttendance bean, HttpServletRequest request)
	{
		try
		{
			String month = Integer.parseInt(bean.getMonth()) < 10 ? "0" + bean.getMonth() : bean.getMonth();
			String year = bean.getYear();
			bean.setMonthName(Utility.month(Integer.parseInt(month)));			
			boolean attConnFlag = bean.isAttConnFlag();			
			String attdnCode = bean.getAttdnCode();
			boolean lockFlag = bean.isLockFlag();
			
			String fromDate = "", monthFlag = "";
			String fDate = "01-" + month + "-" + year;
			int monthDays = getMonthDays(month, year); //Calculate total days of a month
			String toDate = monthDays + "-" + month + "-" + year;
			
			ArrayList<Object> attdnList = new ArrayList<Object>();
			
			if(attConnFlag)
			{
				String monthDateQuery = " SELECT CONF_SALARY_START_DATE, CONF_SALARY_START_FLAG FROM HRMS_ATTENDANCE_CONF ";			
				Object[][] dateFlagObject = getSqlModel().getSingleResult(monthDateQuery);

				String startDate = String.valueOf(dateFlagObject[0][0]);
				startDate = Integer.parseInt(startDate) < 10 ? "0" + startDate : startDate;
				monthFlag = String.valueOf(dateFlagObject[0][1]);
				
				String newMonth = "";
				String newYear = "";							

				if(monthFlag.equals("P"))
				{
					if(month.equals("01"))
					{
						newMonth = "12";
						newYear = String.valueOf(Integer.parseInt(year) - 1);
					}
					else
					{
						newMonth = String.valueOf(Integer.parseInt(month) - 1);
						newMonth = Integer.parseInt(newMonth) < 10 ? "0" + newMonth : newMonth;
						newYear = year;
					}
					fDate = startDate + "-" + newMonth + "-" + newYear;
					toDate = startDate + "-" + month + "-" + year;
				} //end of if statement				
			} //end of if statement
			
			// Get list of employees containing saved as well as non saved employees
			Object[][] eListObj = getEmpIDs(bean, toDate, attdnCode, year, lockFlag);
			
			if(eListObj != null && eListObj.length > 0)
			{
				doPaging(bean, eListObj.length, new Object[][] {}, request, false);
				int fromTotRec = Integer.parseInt(bean.getFromTotRec());
				int toTotRec = Integer.parseInt(bean.getToTotRec());
				
				/**
				 * Set records in page using bean
				**/
				for(int i = fromTotRec; i < toTotRec; i++)
				{
					boolean dailyConFlag = attConnFlag;
					
					if(String.valueOf(eListObj[i][4]).equals("SAVE"))
					{
						MonthAttendance bean1 = new MonthAttendance();
						String eId = String.valueOf(eListObj[i][0]);
						bean1.setEId(eId);
						bean1.setEToken(String.valueOf(eListObj[i][1]));
						bean1.setEName(String.valueOf(eListObj[i][2]));
						bean1.setEJoinDate(String.valueOf(eListObj[i][3]));
						bean1.setAttdnDays(String.valueOf(eListObj[i][5]));
						bean1.setWeeklyOffs(String.valueOf(eListObj[i][6]));
						bean1.setHolidays(String.valueOf(eListObj[i][7]));
						bean1.setPaidLevs(String.valueOf(eListObj[i][8]));
						bean1.setUnPaidLevs(String.valueOf(eListObj[i][9]));
						bean1.setSalDays(String.valueOf(eListObj[i][10]));
						bean1.setLateMarks(String.valueOf(eListObj[i][11]));
						bean1.setHalfDays(String.valueOf(eListObj[i][12]));
						bean1.setRecoveryFlag(bean.getRecoveryFlag());
						bean1.setRecoveryDays(String.valueOf(eListObj[i][13]));
						if(dailyConFlag)
						{
							int dailyDays = getTotalDailyAttdn(monthFlag, month, year, eId, fromDate, toDate);
							if(dailyDays == 0)
							{
								dailyConFlag = false;
							}
						}							
						bean1.setDailyConFlag(dailyConFlag); //DAILY_CONNECTED
						
						String joinDate = bean1.getEJoinDate();
						fromDate = getFromDate(joinDate, fDate); // Calculate from date
						
						double presentDays = calPresentDays(fromDate, toDate, month, year);
						bean1.setPresentDays(String.valueOf(presentDays));
						
						bean1.setESave("true");
						bean1.setLockFlag(bean.isLockFlag());
						bean1.setTotAbs(String.valueOf(calTotalAbsents(monthFlag, fromDate, toDate, month, year, eId, dailyConFlag)));
						
						double salDays = Double.parseDouble(bean1.getSalDays());
						double unPaidLevs = Double.parseDouble(bean1.getUnPaidLevs());
						double attdnDays = Double.parseDouble(bean1.getAttdnDays());							
						bean1.setReCal(isRecalculate(bean, monthFlag, month, year, eId, salDays, presentDays, unPaidLevs, attdnDays));
						
						bean1.setFromDate(fromDate);
						bean1.setToDate(toDate);
						
						attdnList.add(bean1);
					} // end of if statement
				} //end of for loop
				
				bean.setAttdnList(attdnList);
				bean.setFlag(true); //Set flag to TRUE which specifies that attendance is ready to show
			}
			else
			{
				bean.setFlag(false); //Set flag to FALSE which specifies that attendance is not available
			}
		}
		catch (Exception e)
		{
			logger.error(e);
		} //end of try-catch block
	} //end of showAttendance

	/**
	 * Unlocks the attendance
	**/
	/**
	 * @param bean
	 * @param request
	 * @return String as whether attendance has unlocked or not
	**/
	public String unLockAttendance(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		String res = "";
		boolean result = false;
		try
		{
			String attnCode = bean.getAttdnCode();
			String oldStatus = bean.getStatus().trim();
			String newStatus = "";
			
			/**
			 * Unlocks the attendance only when attendance has locked initially or salary has started.
			**/
			String statusQuery = " UPDATE HRMS_SALARY_LEDGER SET LEDGER_STATUS = ";
			if(oldStatus.equals("ATTN_READY")) //Changes the status from ATTN_READY to ATTN_START
			{
				statusQuery += " 'ATTN_START' ";
				newStatus = "ATTN_START";
			} //end of if statement
			else if(oldStatus.equals("SAL_START")) //Changes the status from SAL_START to ATTN_UNLOCK
			{
				statusQuery += " 'ATTN_UNLOCK' ";
				newStatus = "ATTN_UNLOCK";
			} //end of else if statement
			statusQuery += " WHERE LEDGER_CODE = " + attnCode;
			result = getSqlModel().singleExecute(statusQuery);
			
			if(result)
			{
				res = "Attendance unlocked successfully.";
				bean.setStatus(newStatus);
				bean.setLockFlag(false);
				if(newStatus.equals("ATTN_UNLOCK"))
				{
					bean.setUnlockFlag(true);
				}
				
				if(newStatus.equals("ATTN_START"))
				{
					if(bean.isAttConnFlag())
					{
						connectedAttn(bean, request);
					}
					else
					{
						processedAttn(bean, request, response);
					}
				}
				else
				{
					showAttendance(bean, request); //Set saved records of attendance in a page
				}
			} //end of if statement
			else
			{
				res = "Attendance can't unlock.";
				bean.setLockFlag(true);
				bean.setUnlockFlag(false);
				
				showAttendance(bean, request); //Set saved records of attendance in a page
			} //end of else statement
		}
		catch (Exception e)
		{
			res = "Attendance can't unlock";
			bean.setLockFlag(true);
			bean.setUnlockFlag(false);
			logger.error(e);
		} //end of try-catch block
		
		bean.setSavedFlag(true);
		bean.setProcess(true);
		return res;
	} //end of lockAttendance

	/**
	 * Update existing attendance
	**/
	/**
	 * @param bean
	 * @param request
	 * @param response
	 * @return String as message whether attendance is updated or not
	**/
	public String update(MonthAttendance bean, HttpServletRequest request, HttpServletResponse response)
	{
		String res = "";
		try
		{
			boolean result = false;
			boolean result1 = false;
			
			String attdnCode = bean.getAttdnCode();
			String year = bean.getYear();
			
			/* Delete only those employees which are available on current page */
			Object[] eId = request.getParameterValues("eId");			
			for(int i = 0; i < eId.length; i++)
			{
				String delQuery = " DELETE FROM HRMS_MONTH_ATTENDANCE_" + year + " WHERE ATTN_EMP_ID = " + eId[i] + 
				" AND ATTN_CODE = " + attdnCode;
				result = getSqlModel().singleExecute(delQuery);
			}//end of for loop
			result1 = getAttdnToSave(bean, attdnCode, request);
			
			//If attendance is locked, then show saved attendance, otherwise process the attendance
			if(bean.isLockFlag())
			{
				showAttendance(bean, request); //Set saved records of attendance in a page
			}
			//If attendance is unlocked and salary has started, then show saved attendance, otherwise process the attendance
			else if(bean.isUnlockFlag())
			{
				showAttendance(bean, request); //Set saved records of attendance in a page
			}
			else
			{
				if(bean.isAttConnFlag())
				{
					connectedAttn(bean, request);
				}
				else
				{
					processedAttn(bean, request, response);
				}
			} //end of else statement
			
			if(result && result1)
			{
				res = "Record updated successfully!";
			} //end of if statement
			else
			{
				res = "Record can't be updated!";
			} //end of else statement
		}
		catch (Exception e)
		{
			logger.error(e);
			res = "Record can't be updated!";
		} //end of try-catch block
		return res;
	} //end of update

	/**
	 * Calculates week off days applicable
	**/
	/**
	 * @param currentcalendarday : Specifies Calendar object determining day in a week
	 * @param weeklyOffObj : Containing week off days
	 * @return int as total number of week off days applicable
	**/
	public int weekenDays(Calendar currentcalendarday,Object weeklyOffObj[][])
	{
		int countDays = 0;
		int DAY_OF_WEEK = currentcalendarday.get(java.util.Calendar.WEEK_OF_MONTH);//Day of week of specified date
		String []weekDays = null;
		for (int i = 0; i < weeklyOffObj[0].length; i++)
		{
			if(i+1 == DAY_OF_WEEK)
			{
				weekDays = String.valueOf(weeklyOffObj[0][i]).split(",");
			} //end of if statement
		}//end of for loop
		for(String string : weekDays)
		{
			int i = 0;
			try
			{
				i = Integer.parseInt(string.trim());
			}
			catch (Exception e)
			{
				i = 0;
			} //end of try-catch block
			
			switch (i)
			{
				case 1:
					if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 1) 
					{
						countDays++;
					} //end of if statement
					break;
				case 2 :
					if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 2) 
					{
						countDays++;
					} //end of if statement
					break;	
				case 3 :
					if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 3) 
					{
						countDays++;
					} //end of if statement
					break;	
				case 4 :
					if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 4) 
					{
						countDays++;
					} //end of if statement
					break;	
				case 5 :
					if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 5) 
					{
						countDays++;
					} //end of if statement
					break;
				case 6 :
					if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 6) 
					{
						countDays++;
					} //end of if statement
					break;	
				case 7 :
					if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 7) 
					{
						countDays++;
					} //end of if statement
					break;	
				default : break;
			}
		}//end of for loop
		return countDays;
	} //end of weekenDays}
}