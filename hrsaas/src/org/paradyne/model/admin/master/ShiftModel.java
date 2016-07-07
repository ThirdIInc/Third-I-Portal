/* Reeba Joseph 06 OCT 2009 */

package org.paradyne.model.admin.master;

import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.paradyne.bean.admin.master.ShiftMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;

/**
 * To define the business logic for defining shifts and assign the weekly off days
 */

public class ShiftModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ShiftModel.class);

	/**
	 * Adds new shift
	 * 
	 * @param bean
	 * @param sun
	 * @param mon
	 * @param tue
	 * @param wed
	 * @param thu
	 * @param fri
	 * @param sat
	 * @return boolean whether shift adds or not
	 */
	public String addShift(ShiftMaster bean, String[] sun, String[] mon, String[] tue, String[] wed, String[] thu, String[] fri, String[] sat) {
		String[] orgDaysVal = {"", "", "", "", "", ""};

		if(sun != null) {
			for(int i = 0; i < sun.length; i++) {
				orgDaysVal[0] += sun[i] + ",";
			}
		}

		if(mon != null) {
			for(int i = 0; i < mon.length; i++) {
				orgDaysVal[1] += mon[i] + ",";
			}
		}
		
		if(tue != null) {
			for(int i = 0; i < tue.length; i++) {
				orgDaysVal[2] += tue[i] + ",";
			}
		}
		
		if(wed != null) {
			for(int i = 0; i < wed.length; i++) {
				orgDaysVal[3] += wed[i] + ",";
			}
		}
		
		if(thu != null) {
			for(int i = 0; i < thu.length; i++) {
				orgDaysVal[4] += thu[i] + ",";
			}
		}
		
		if(fri != null) {
			for(int i = 0; i < fri.length; i++) {
				orgDaysVal[5] += fri[i] + ",";
			}
		}
		
		if(sat != null) {
			for(int i = 0; i < sat.length; i++) {
				orgDaysVal[6] += sat[i] + ",";
			}
		}
		
		Object resultCode[][] = getSqlModel().getSingleResult(" SELECT NVL(MAX(SHIFT_ID), 0) + 1 FROM HRMS_SHIFT ");

		boolean flag = false;
		
		if(!checkDuplicate(bean)) {
			Object addShiftObj[][] = new Object[1][55];
			String shiftId = String.valueOf(resultCode[0][0]);
			addShiftObj[0][0] = shiftId;
			
			// DIV 1
			addShiftObj[0][1] = checkNull(bean.getShiftName());
			addShiftObj[0][2] = checkNull(bean.getShiftStrTime().trim());
			addShiftObj[0][3] = checkNull(bean.getReportTimeLate().trim());
			addShiftObj[0][4] = checkNull(bean.getReportTimeHalf().trim());
			addShiftObj[0][5] = checkNull(bean.getLunchBreakStart().trim());
			addShiftObj[0][6] = checkNull(bean.getLunchBreakEnd().trim());
			addShiftObj[0][7] = checkNull(bean.getOffLeftHalfDay().trim());
			addShiftObj[0][8] = checkNull(bean.getOffLeftEarly().trim());
			addShiftObj[0][9] = checkNull(bean.getShiftEndTime().trim());
			addShiftObj[0][10] = checkNull(bean.getShiftWorkHrs().trim());
			addShiftObj[0][11] = checkNull(bean.getExtraWorkHrs().trim());
			if(checkNull(bean.getPersonalTime()).equals("true")) {
				addShiftObj[0][12] = "Y";
			} else {
				addShiftObj[0][12] = "N";
			}
			addShiftObj[0][13] = checkNull(bean.getFreePersonalTimeOf()).trim();
			addShiftObj[0][14] = checkNull(bean.getFreePersonalTimePer()).trim();
			if(checkNull(bean.getAdjPersonalTime()).equals("true")) {
				addShiftObj[0][15] = "Y";
			} else {
				addShiftObj[0][15] = "N";
			}
			
			// DIV 3
			if(checkNull(bean.getEnableMonitor()).equals("true")) {
				addShiftObj[0][16] = "Y";
			} else {
				addShiftObj[0][16] = "N";
			}
			if(checkNull(bean.getBLateMark()).equals("true")) {
				addShiftObj[0][17] = "Y";
			} else {
				addShiftObj[0][17] = "N";
			}
			addShiftObj[0][18] = checkNull(bean.getAdjustLMCount()).trim();
			addShiftObj[0][19] = checkNull(bean.getAdjustLMLevDays()).trim();
			addShiftObj[0][20] = checkNull(bean.getLateCombineLeaveCode()).trim();
			if(checkNull(bean.getLmHrsIsEnabled()).equals("true")) {
				addShiftObj[0][21] = "Y";
			} else {
				addShiftObj[0][21] = "N";
			}
			if(checkNull(bean.getLmReglIsEnabled()).equals("true")) {
				addShiftObj[0][22] = "Y";
			} else {
				addShiftObj[0][22] = "N";
			}
			addShiftObj[0][23] = checkNull(bean.getLateMarksLeaveCode()).trim();
			addShiftObj[0][24] = checkNull(bean.getDedLmInSlabsOf()).trim();
			addShiftObj[0][25] = checkNull(bean.getNonRegLateMarksLeaveCode()).trim();
			if(checkNull(bean.getAdjustExtraWorkLm()).equals("true")) {
				addShiftObj[0][26] = "Y";
			} else {
				addShiftObj[0][26] = "N";
			}
			addShiftObj[0][27] = checkNull(bean.getExtraWorkForLM()).trim();
			addShiftObj[0][28] = checkNull(bean.getExtraWorkForLmOf()).trim();

			// DIV 4
			if(checkNull(bean.getEnableHalfMonitor()).equals("true")) {
				addShiftObj[0][29] = "Y";
			} else {
				addShiftObj[0][29] = "N";
			}
			addShiftObj[0][30] = checkNull(bean.getRegHalfDayLeaveCode()).trim();
			if(checkNull(bean.getAdjustExtraWorkHd()).equals("true")) {
				addShiftObj[0][31] = "Y";
			} else {
				addShiftObj[0][31] = "N";
			}
			addShiftObj[0][32] = checkNull(bean.getExtraWorkForHD()).trim();
			addShiftObj[0][33] = checkNull(bean.getExtraWorkForHdOf()).trim();

			// DIV 5
			if(checkNull(bean.getVariableWeekOff()).equals("true")) {
				addShiftObj[0][34] = "Y";
			} else {
				addShiftObj[0][34] = "N";
			}
			addShiftObj[0][35] = checkNull(bean.getVariWoPerMonth()).trim();
			if(checkNull(bean.getFixedWeekOff()).equals("true")) {
				addShiftObj[0][36] = "Y";
			} else {
				addShiftObj[0][36] = "N";
			}
			addShiftObj[0][37] = checkNull(bean.getShiftNtFlag()).trim();

			for(int i = 0; i < 6; i++) {
				try {
					if(!(orgDaysVal[i] == null || orgDaysVal[i] == "")) {
						addShiftObj[0][i + 38] = orgDaysVal[i].substring(0, orgDaysVal[i].length() - 1);
					}
				} catch(Exception e) {
					logger.info(e);
				}
			}
			
			addShiftObj[0][44] = checkNull(bean.getMarkAbsentAfterThisTime()).trim();
			addShiftObj[0][45] = checkNull(bean.getFlexiTimeAllowed()).trim();
			if(checkNull(bean.getFlexiTimeAllowed()).equals("true")) {
				addShiftObj[0][45] = "Y";
			} else {
				addShiftObj[0][45] = "N";
			}
			addShiftObj[0][46] = checkNull(bean.getMarkFlexiLateAfterThisTime()).trim();
			addShiftObj[0][47] = checkNull(bean.getMarkFlexiHalfDayAfterThisTime()).trim();
			addShiftObj[0][48] = checkNull(bean.getWaiveOffLateMarks()).trim();
			
			//OT Configuration -- BEGINS
			addShiftObj[0][49] = checkNull(bean.getEnableOTConfigWorkflow()).trim().equals("true")?"Y":"N";
			if (checkNull(bean.getActualHoursWorkedOT()).trim().equals("true")) {
				addShiftObj[0][50] = "W";
			} else if(checkNull(bean.getActualOutTimeOT()).trim().equals("true")){
				addShiftObj[0][50] = "T";
			} 
			addShiftObj[0][51] = checkNull(bean.getRegularOtHourlyRateFormulaOT()).trim();
			addShiftObj[0][52] = checkNull(bean.getWeeklyOffHolidayOtHourlyFormulaOT()).trim();
			addShiftObj[0][53] = checkNull(bean.getDoubleOtHourlyFormulaOT()).trim();
			addShiftObj[0][54] = checkNull(bean.getMarkFlexiAbsentBeforeThisTime()).trim();
			//OT Configuration -- ENDS			
			flag = getSqlModel().singleExecute(getQuery(1), addShiftObj);
			
			if(flag) {
				// DIV 2
				boolean shiftDtlsShown = bean.isShiftDtlsShown();
				if(shiftDtlsShown) {
					boolean isShiftExceptionsAdded = addShiftExceptions(bean, shiftId, false);
					
					if(isShiftExceptionsAdded) {
						return "saved";
					} else {
						return "not saved";
					}
				}
				return "saved";
			} else {
				return "not saved";
			}
		} else {
			return "duplicate";
		}
	}
	
	private boolean addShiftExceptions(ShiftMaster bean, String shiftId, boolean isShiftExists) {
		boolean isShiftExceptionsAdded = false;
		try {
			boolean flag = true;
			
			String weekDay = bean.getWeekDay();
			String shiftDtlStartTime = bean.getDtlShiftStartTime();
			String shiftDtlEndTime = bean.getDtlShiftEndTime();
			
			if(shiftDtlStartTime.equals("null") || shiftDtlStartTime.equals("") || shiftDtlStartTime == null || 
					shiftDtlEndTime.equals("null") || shiftDtlEndTime.equals("") || shiftDtlEndTime == null) {
				flag = false;
			}
			
			if(isShiftExists && flag) {
				Object[][] deleteShiftExceptionObj = new Object[1][2];
				deleteShiftExceptionObj[0][0] = shiftId;
				deleteShiftExceptionObj[0][1] = weekDay;
				
				flag = getSqlModel().singleExecute(getQuery(10), deleteShiftExceptionObj);
			}
			
			if(flag) {
				Object[][] addShiftExceptionObj = new Object[1][13];
				addShiftExceptionObj[0][0] = shiftId;
				addShiftExceptionObj[0][1] = weekDay;
				addShiftExceptionObj[0][2] = shiftDtlStartTime;
				addShiftExceptionObj[0][3] = bean.getDtlReportTimeLate();
				addShiftExceptionObj[0][4] = bean.getDtlReportTimeHalf();
				addShiftExceptionObj[0][5] = bean.getDtlLunchBreakStart();
				addShiftExceptionObj[0][6] = bean.getDtlLunchBreakEnd();
				addShiftExceptionObj[0][7] = bean.getDtlOffLeftHalfDay();
				addShiftExceptionObj[0][8] = bean.getDtlOffLeftEarly();
				addShiftExceptionObj[0][9] = shiftDtlEndTime;
				addShiftExceptionObj[0][10] = bean.getDtlShiftWorkHrs();
				addShiftExceptionObj[0][11] = bean.getDtlExtraWorkHrs();
				addShiftExceptionObj[0][12] = bean.getDtlShiftNtFlag();
				
				isShiftExceptionsAdded = getSqlModel().singleExecute(getQuery(9), addShiftExceptionObj);
				
				if(isShiftExceptionsAdded) {
					bean.setShiftDtlsExists(true);
				}
			}
		} catch(Exception e) {
			logger.error("Exception in addShiftExceptions:" + e);
		}
		return isShiftExceptionsAdded;
	}

	/**
	 * Displays list of saved records on load
	 * 
	 * @param shfMaster
	 * @param request
	 */
	public void callShiftList(ShiftMaster shfMaster, HttpServletRequest request) {
		String listQuery =
			" SELECT SHIFT_ID, NVL(SHIFT_NAME,' '),  " + " TO_CHAR(SFT_START_TIME, 'HH24:mi'), TO_CHAR(SFT_END_TIME, 'HH24:mi') "
				+ " FROM HRMS_SHIFT " + " ORDER BY SHIFT_ID ";

		Object[][] result = getSqlModel().getSingleResult(listQuery);
		if(result != null && result.length > 0)
			shfMaster.setModeLength("true");
		else
			shfMaster.setModeLength("false");
		String[] pageIndex = Utility.doPaging(shfMaster.getMyPage(), result.length, 20);
		if(pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			shfMaster.setMyPage("1");
		ArrayList<Object> tableList = new ArrayList<Object>();
		if(result.length > 0) {
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				ShiftMaster bean1 = new ShiftMaster();
				bean1.setShiftIDItt(checkNull(String.valueOf(result[i][0])));
				bean1.setShiftNameItt(checkNull(String.valueOf(result[i][1])));
				bean1.setShiftStrTimeItt(checkNull(String.valueOf(result[i][2])));
				bean1.setShiftEndTimeItt(checkNull(String.valueOf(result[i][3])));
				tableList.add(bean1);
			}
			shfMaster.setTableList(tableList);
			shfMaster.setTotalRecords(String.valueOf(result.length));
		} else {
			shfMaster.setNoData("true");// No data to display message shown
			shfMaster.setTableList(null);
			shfMaster.setTotalRecords(String.valueOf(result.length));
		}

	}

	/**
	 * Check whether shift is exists or not
	 * 
	 * @param bean
	 * @return boolean as flag whether shift exists or not
	 */
	public boolean checkDuplicate(ShiftMaster bean) {
		boolean flag = true;
		String query = "";

		if(bean.getShiftID().equals("")) {
			query = " SELECT SHIFT_NAME FROM HRMS_SHIFT " + 
			" WHERE UPPER(SHIFT_NAME) LIKE '" + bean.getShiftName().trim().toUpperCase() + "' ";
		} else {
			query = " SELECT SHIFT_NAME FROM HRMS_SHIFT " +
			" WHERE UPPER(SHIFT_NAME) LIKE '" + bean.getShiftName().trim().toUpperCase() + "' AND SHIFT_ID NOT IN(" + bean.getShiftID() + ") ";
		}
		Object[][] data = getSqlModel().getSingleResult(query);

		try {
			if(data != null && data.length > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch(Exception e) {
			logger.error("Error in checkDuplicate method  : " + e);
		}
		return flag;
	}

	public String checkNull(String result) {
		try {
			if(result.equals("null")) {
				return "";
			} else {
				return result;
			}
		} catch(Exception e) {
			return "";
		}
	}

	public boolean delChkdRec(String[] code) {
		int count = 0;
		boolean result = false;
		
		for(int i = 0; i < code.length; i++) {
			if(!code[i].equals("")) {
				Object[][] param = new Object[1][1];
				param[0][0] = code[i];
				
				result = getSqlModel().singleExecute(getQuery(11), param);
				
				if(result) {
					result = getSqlModel().singleExecute(getQuery(3), param);
				}
				
				if(!result) {
					count++;
				}
			}
		}
		if(count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	/**
	 * Deletes the shift
	 * 
	 * @param bean
	 * @return boolean whether shift deletes or not
	 */
	public boolean deleteShift(ShiftMaster bean) {
		Object param[][] = new Object[1][1];
		param[0][0] = bean.getShiftID();
		
		boolean result = getSqlModel().singleExecute(getQuery(11), param);
		
		if(result) {
			result = getSqlModel().singleExecute(getQuery(3), param);
		}
		
		return result;
	}

	/**
	 * Set fixed weekly off details on a page
	 * 
	 * @param bean
	 * @return String[][] as week containing weekly off records
	 */
	public String[][] getFixedWeekRecords(ShiftMaster bean) {
		Object addObj[] = new Object[1];
		addObj[0] = bean.getShiftID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);
		String[][] week = new String[6][7];

		if(data != null) {			
			String daysStr[] = null;
			for(int i = 0; i < week.length; i++) {
				daysStr = String.valueOf(data[0][i + 9]).split(",");
				for(int j = 0; j < week[0].length; j++) {
					week[i][j] = "";
					if(daysStr != null) {
						for(int j2 = 0; j2 < daysStr.length; j2++) {
							int dayInt = 0;
							if(daysStr[j2] != null) {
								try {
									dayInt = Integer.parseInt(daysStr[j2]);
								} catch(NumberFormatException e) {}
							}
							if(j + 1 == dayInt) {
								week[i][j] = "Y";
							}
						}
					}

				}
			}			
		}
		return week;
	}

	/**
	 * method name : getLeaveAbbr purpose : to retrieve the leave abbreviation for a given leave code return type : String parameter :
	 * String leaveCode
	 */
	public String getLeaveAbbr(String leaveCode) {
		String[] leaveCodeSplit = leaveCode.split(",");
		String leaveAbbr = "";

		if(!leaveCode.equals("")) {
			for(int i = 0; i < leaveCodeSplit.length; i++) {
				logger.info("leaveCodeSplit[i]  :" + leaveCodeSplit[i]);
				if(!leaveCodeSplit[i].equals("0")) {
					// leaveAbbr = "Unpaid";
					// } else {
					String query = "SELECT LEAVE_ABBR FROM HRMS_LEAVE WHERE LEAVE_ID = " + leaveCodeSplit[i];
					Object[][] leaveAbbrData = getSqlModel().getSingleResult(query);
					if(leaveAbbrData != null && leaveAbbrData.length != 0) {
						if(leaveAbbr.equals("")) {
							logger.info("in if  : ");
							leaveAbbr = String.valueOf(leaveAbbrData[0][0]).trim();
						} // end of if
						else {
							logger.info("in else  : ");
							leaveAbbr += "," + String.valueOf(leaveAbbrData[0][0]).trim();
						} // end of else
						logger.info("leaveAbbr  : " + leaveAbbr);
					} // end of if
				} else {
					if(leaveAbbr.equals("")) {
						logger.info("in if 0 : ");
						leaveAbbr = "Unpaid";
					} // end of if
					else {
						logger.info("in else 0 : ");
						leaveAbbr += ",Unpaid";
					} // end of else
					logger.info("leaveAbbr 0 : " + leaveAbbr);
				}
			} // end of for loop
		} // end of if

		return leaveAbbr;
	}

	/**
	 * Generates a report
	 */
	/**
	 * @param bean
	 * @param request
	 * @param response
	 * @param context
	 * @param session
	 */
	public void getReport(ShiftMaster bean, HttpServletRequest request, HttpServletResponse response, ServletContext context,
		HttpSession session) {
		CrystalReport cr = new CrystalReport();
		String path = "org/paradyne/rpt/admin/master/shift.rpt";
		cr.createReport(request, response, context, session, path, "");
	}

	public Object[][] getShiftExceptionDetails(String shiftID, String weekDay) {
		Object[][] shiftExceptionDetails = null;
		try {
			Object[] param = new Object[2];
			param[0] = shiftID;
			param[1] = weekDay;
			
			shiftExceptionDetails = getSqlModel().getSingleResult(getQuery(8), param);
		} catch(Exception e) {
			logger.error("Exception in getShiftExceptionDetails:" + e);
		}
		return shiftExceptionDetails;
	}

	public void getShiftRecords(ShiftMaster bean) {
		String shiftQuery = " SELECT SHIFT_ID, SHIFT_NAME, TO_CHAR(SFT_START_TIME, 'HH24:MI') AS SFT_START_TIME, " +
		" TO_CHAR(SFT_IN_LM_AFTER_TIME, 'HH24:MI') AS SFT_IN_LM_AFTER_TIME, TO_CHAR(SFT_IN_HD_AFTER_TIME, 'HH24:MI') AS SFT_IN_HD_AFTER_TIME, " +
		" TO_CHAR(SFT_BREAK_TIME_START, 'HH24:MI') AS SFT_BREAK_TIME_START, TO_CHAR(SFT_BREAK_TIME_END, 'HH24:MI') AS SFT_BREAK_TIME_END, " +
		" TO_CHAR(SFT_OUT_HD_BEFORE_TIME, 'HH24:MI') AS SFT_OUT_HD_BEFORE_TIME, TO_CHAR(SFT_OUT_EL_BEFORE_TIME, 'HH24:MI') AS SFT_OUT_EL_BEFORE_TIME, " +
		" TO_CHAR(SFT_END_TIME, 'HH24:MI') AS SFT_END_TIME, TO_CHAR(SFT_WORK_HRS, 'HH24:MI') AS SFT_WORK_HRS, " +
		" TO_CHAR(SFT_EXTRAWORK_START,'HH24:MI') AS SFT_EXTRAWORK_START, NVL(SFT_PT_ISENABLED, 'N') AS SFT_PT_ISENABLED, " +
		" TO_CHAR(SFT_PT_DURATION,'HH24:MI') AS SFT_PT_DURATION, NVL(SFT_PT_PERIODICITY, ' ') AS SFT_PT_PERIODICITY, " +
		" NVL(SFT_PT_LM_HD_ISADJUSTED, 'N') AS SFT_PT_LM_HD_ISADJUSTED, NVL(SFT_LM_ISENABLED,'N') AS SFT_LM_ISENABLED, " +
		" NVL(SFT_LM_NUMBER_ISENABLED, 'N') AS SFT_LM_NUMBER_ISENABLED, SFT_ADJUST_LM_COUNT, SFT_ADJUST_LM_LEVDAYS, " +
		" NVL(SFT_ADJUST_LM_LEVTYPE, ' ') AS SFT_ADJUST_LM_LEVTYPE, NVL(SFT_LM_HRS_ISENABLED, 'N') AS SFT_LM_HRS_ISENABLED, " +
		" NVL(SFT_LM_REGL_ISENABLED, 'N') AS SFT_LM_REGL_ISENABLED, NVL(SFT_DED_REGL_LM_LEVTYPE, ' ') AS SFT_DED_REGL_LM_LEVTYPE, " +
		" SFT_DED_LM_IN_SLABS_OF, NVL(SFT_DED_NONREGL_LM_LEVTYPE, ' ') AS SFT_DED_NONREGL_LM_LEVTYPE, " +
		" NVL(SFT_EXTRAWORK_LM_ISADJUSTED, 'N') AS SFT_EXTRAWORK_LM_ISADJUSTED, TO_CHAR(SFT_EXTRAWORK_FOR_LM, 'HH24:MI') AS SFT_EXTRAWORK_FOR_LM, " +
		" NVL(SFT_EXTRAWORK_FOR_LM_OF, ' ') AS SFT_EXTRAWORK_FOR_LM_OF, NVL(SFT_HD_ISENABLED, 'N') AS SFT_HD_ISENABLED, " +
		" NVL(SFT_DED_HD_LEVTYPE, ' ') AS SFT_DED_HD_LEVTYPE, NVL(SFT_EXTRAWORK_HD_ISADJUSTED, 'N') AS SFT_EXTRAWORK_HD_ISADJUSTED, " +
		" TO_CHAR(SFT_EXTRAWORK_FOR_HD, 'HH24:MI') AS SFT_EXTRAWORK_FOR_HD, NVL(SFT_EXTRAWORK_FOR_HD_OF, ' ') AS SFT_EXTRAWORK_FOR_HD_OF, " +
		" NVL(SFT_VARIABLE_WO_ISENABLED,'N') AS SFT_VARIABLE_WO_ISENABLED, SFT_WO_PER_MONTH, " +
		" NVL(SFT_FIXED_WO_ISENABLED, 'N') AS SFT_FIXED_WO_ISENABLED, NVL(SFT_NIGHT_FLAG, ' ') AS SFT_NIGHT_FLAG, "+ 
		" TO_CHAR(SFT_MARK_ABSENT_AFTER, 'HH24:MI') AS SFT_MARK_ABSENT_AFTER, SFT_FLEXITIME_ALLOWED, "+
		" TO_CHAR(SFT_FLEXI_LATE_MARK, 'HH24:MI') AS SFT_FLEXI_LATE_MARK, TO_CHAR(SFT_FLEXI_HALFDAY_MARK, 'HH24:MI') AS SFT_FLEXI_HALFDAY_MARK, "+
		" SFT_WAIVEOFF_LATE_MARK, NVL(SFT_OT_ISENABLED,'N'),  NVL(SFT_OT_DURATION_FLAG,'N'),  NVL(SFT_OT_REG_FORMULA,''),  NVL(SFT_OT_WEEKLY_FORMULA,''),  NVL(SFT_OT_DOUBLE_FORMULA,'')," +
		" TO_CHAR(SFT_FLEXI_MARK_ABSENT_BEFORE, 'HH24:MI') AS SFT_FLEXI_MARK_ABSENT_BEFORE "+
		" FROM HRMS_SHIFT " +
		" WHERE SHIFT_ID = " + bean.getShiftID();
		Object[][] shiftData = getSqlModel().getSingleResult(shiftQuery);

		if(shiftData != null && shiftData.length != 0) {
			bean.setShiftID(checkNull(String.valueOf(shiftData[0][0])).trim());
			
			// DIV 1
			bean.setShiftName(checkNull(String.valueOf(shiftData[0][1])).trim());
			bean.setShiftStrTime(checkNull(String.valueOf(shiftData[0][2])).trim());
			bean.setReportTimeLate(checkNull(String.valueOf(shiftData[0][3])).trim());
			bean.setReportTimeHalf(checkNull(String.valueOf(shiftData[0][4])).trim());
			bean.setLunchBreakStart(checkNull(String.valueOf(shiftData[0][5])).trim());
			bean.setLunchBreakEnd(checkNull(String.valueOf(shiftData[0][6])).trim());
			bean.setOffLeftHalfDay(checkNull(String.valueOf(shiftData[0][7])).trim());
			bean.setOffLeftEarly(checkNull(String.valueOf(shiftData[0][8])).trim());
			bean.setShiftEndTime(checkNull(String.valueOf(shiftData[0][9])).trim());
			bean.setShiftWorkHrs(checkNull(String.valueOf(shiftData[0][10])).trim());
			bean.setExtraWorkHrs(checkNull(String.valueOf(shiftData[0][11])).trim());
			if(checkNull(String.valueOf(shiftData[0][12])).trim().equals("Y")) {
				bean.setPersonalTime("true");
			} else {
				bean.setPersonalTime("false");
			}
			bean.setFreePersonalTimeOf(checkNull(String.valueOf(shiftData[0][13])).trim());
			bean.setFreePersonalTimePer(checkNull(String.valueOf(shiftData[0][14])).trim());
			if(checkNull(String.valueOf(shiftData[0][15])).trim().equals("Y")) {
				bean.setAdjPersonalTime("true");
			} else {
				bean.setAdjPersonalTime("false");
			}

			// DIV 3
			if(checkNull(String.valueOf(shiftData[0][16])).trim().equals("Y")) {
				bean.setEnableMonitor("true");
			} else {
				bean.setEnableMonitor("false");
			}
			if(checkNull(String.valueOf(shiftData[0][17])).trim().equals("Y")) {
				bean.setBLateMark("true");
			} else {
				bean.setBLateMark("false");
			}
			bean.setAdjustLMCount(checkNull(String.valueOf(shiftData[0][18])).trim());
			bean.setAdjustLMLevDays(checkNull(String.valueOf(shiftData[0][19])).trim());
			bean.setLateCombineLeaveCode(checkNull(String.valueOf(shiftData[0][20])).trim());
			bean.setLateCombineLeave(getLeaveAbbr(checkNull(String.valueOf(shiftData[0][20])).trim()));
			if(checkNull(String.valueOf(shiftData[0][21])).trim().equals("Y")) {
				bean.setLmHrsIsEnabled("true");
			} else
				bean.setLmHrsIsEnabled("false");
			if(checkNull(String.valueOf(shiftData[0][22])).trim().equals("Y")) {
				bean.setLmReglIsEnabled("true");
			} else
				bean.setLmReglIsEnabled("false");
			bean.setLateMarksLeaveCode(checkNull(String.valueOf(shiftData[0][23])).trim());
			bean.setLateMarksLeave(getLeaveAbbr(checkNull(String.valueOf(shiftData[0][23])).trim()));
			bean.setDedLmInSlabsOf(checkNull(String.valueOf(shiftData[0][24])).trim());
			bean.setNonRegLateMarksLeaveCode(checkNull(String.valueOf(shiftData[0][25])).trim());
			bean.setNonRegLateMarksLeave(getLeaveAbbr(checkNull(String.valueOf(shiftData[0][25])).trim()));
			if(checkNull(String.valueOf(shiftData[0][26])).trim().equals("Y")) {
				bean.setAdjustExtraWorkLm("true");
			} else {
				bean.setAdjustExtraWorkLm("false");
			}
			bean.setExtraWorkForLM(checkNull(String.valueOf(shiftData[0][27])).trim());
			bean.setExtraWorkForLmOf(checkNull(String.valueOf(shiftData[0][28])).trim());

			// DIV 4
			if(checkNull(String.valueOf(shiftData[0][29])).trim().equals("Y")) {
				bean.setEnableHalfMonitor("true");
			} else {
				bean.setEnableHalfMonitor("false");
			}
			bean.setRegHalfDayLeaveCode(checkNull(String.valueOf(shiftData[0][30])).trim());
			bean.setRegHalfDayLeave(getLeaveAbbr(checkNull(String.valueOf(shiftData[0][30])).trim()));
			if(checkNull(String.valueOf(shiftData[0][31])).trim().equals("Y")) {
				bean.setAdjustExtraWorkHd("true");
			} else {
				bean.setAdjustExtraWorkHd("false");
			}
			bean.setExtraWorkForHD(checkNull(String.valueOf(shiftData[0][32])).trim());
			bean.setExtraWorkForHdOf(checkNull(String.valueOf(shiftData[0][33])).trim());

			// DIV 5
			if(checkNull(String.valueOf(shiftData[0][34])).trim().equals("Y")) {
				bean.setVariableWeekOff("true");
			} else {
				bean.setVariableWeekOff("false");
			}
			bean.setVariWoPerMonth(checkNull(String.valueOf(shiftData[0][35])).trim());
			if(checkNull(String.valueOf(shiftData[0][36])).trim().equals("Y")) {
				bean.setFixedWeekOff("true");
			} else {
				bean.setFixedWeekOff("false");
			}
			bean.setShiftNtFlag(checkNull(String.valueOf(shiftData[0][37])).trim());
			bean.setMarkAbsentAfterThisTime(checkNull(String.valueOf(shiftData[0][38])).trim());
			if(checkNull(String.valueOf(shiftData[0][39])).trim().equals("Y")) {
				bean.setFlexiTimeAllowed("true");
			} else {
				bean.setFlexiTimeAllowed("false");
			}
			bean.setMarkFlexiLateAfterThisTime(checkNull(String.valueOf(shiftData[0][40])).trim());
			bean.setMarkFlexiHalfDayAfterThisTime(checkNull(String.valueOf(shiftData[0][41])).trim());
			bean.setWaiveOffLateMarks(checkNull(String.valueOf(shiftData[0][42])).trim());
			
			if(checkNull(String.valueOf(shiftData[0][43])).trim().equals("Y")) {
				bean.setEnableOTConfigWorkflow("true");
			}
			
			if(checkNull(String.valueOf(shiftData[0][44])).trim().equals("W")) {
				bean.setActualHoursWorkedOT("true");
			} 
			
			if(checkNull(String.valueOf(shiftData[0][44])).trim().equals("T")) {
				bean.setActualOutTimeOT("true");
			} 
		 
			bean.setRegularOtHourlyRateFormulaOT(checkNull(String.valueOf(shiftData[0][45])).trim());
			bean.setWeeklyOffHolidayOtHourlyFormulaOT(checkNull(String.valueOf(shiftData[0][46])).trim());
			bean.setDoubleOtHourlyFormulaOT(checkNull(String.valueOf(shiftData[0][47])).trim()); 
			bean.setMarkFlexiAbsentBeforeThisTime(checkNull(String.valueOf(shiftData[0][48])).trim());
		}
	}

	public void getShiftReport(ShiftMaster bean) {

		Object[][] data = getSqlModel().getSingleResult(getQuery(5));

		ArrayList<Object> shfList = new ArrayList<Object>();

		for(int i = 0; i < data.length; i++) {
			ShiftMaster bean1 = new ShiftMaster();
			bean1.setShiftID(checkNull(String.valueOf(data[i][0])));
			logger.info("ID:" + String.valueOf(data[i][0]));

			bean1.setShiftName(checkNull(String.valueOf(data[i][1])));
			logger.info("Name:" + String.valueOf(data[i][1]));

			/*
			 * bean1.setShiftStrTime1(checkNull(String.valueOf(data[i][2]))); bean1.setShiftEndTime1(checkNull(String.valueOf(data[i][3])));
			 * bean1.setShiftStrTime2(checkNull(String.valueOf(data[i][4]))); bean1.setShiftEndTime2(checkNull(String.valueOf(data[i][5])));
			 * bean1.setShiftOTStrTime(checkNull(String.valueOf(data[i][6]))); bean1.setShiftWrHours(checkNull(String.valueOf(data[i][7])));
			 * logger.info("----1:" + data[i][7]); bean1.setShiftNtFlag(checkNull(String.valueOf(data[i][8])));
			 */
			shfList.add(bean1);

		}
		bean.setShiftList(shfList);

	}

	/**
	 * method name : leaveCombination purpose : to retrieve all the leave types and set them in a new window return type : void parameter :
	 * AttendanceSettings instance
	 */
	public void leaveCombination(ShiftMaster bean) {
		ArrayList<Object> leaveDataList = new ArrayList<Object>();
		Object[][] selectedLeaveCode = null;
		Object[][] leaveData = null;
		boolean flag = false;
		String type = bean.getLeaveCodeHid(); // type to which we want adjust
		// leave type i.e. late marks or
		// half day

		logger.info("type    : " + type);
		if(!type.equals("")) {
			String query = "SELECT ";

			if(type.equals("lateCombineLeaveCode")) {
				query += "SFT_ADJUST_LM_LEVTYPE ";
			} else if(type.equals("lateMarksLeaveCode")) {
				query += "SFT_DED_REGL_LM_LEVTYPE ";
			} else if(type.equals("nonRegLateMarksLeaveCode")) {
				query += "SFT_DED_NONREGL_LM_LEVTYPE ";
			} else if(type.equals("regHalfDayLeaveCode")) {
				query += "SFT_DED_HD_LEVTYPE ";
			} /*
				 * else if (type.equals("nonRegHalfDayLeaveCode")) { query += "SFT_DED_NONREGL_HD_LEVTYPE "; }
				 */
			query += "FROM HRMS_SHIFT WHERE SHIFT_ID = " + bean.getShiftID();
			// execute query and store result in selectedLeaveCode object
			// array
			selectedLeaveCode = getSqlModel().getSingleResult(query);
		}

		String[] leaveCodeSplit = null;

		/**
		 * if selectedLeaveCode array is not null or length of selectedLeaveCode is not 0 or element at 0 index in selectedLeaveCode array
		 * is not null or blank
		 */
		if(selectedLeaveCode != null && selectedLeaveCode.length != 0 && !String.valueOf(selectedLeaveCode[0][0]).equals("null")
			&& !String.valueOf(selectedLeaveCode[0][0]).equals("")) {

			leaveCodeSplit = selectedLeaveCode[0][0].toString().split(","); // split selectedLeaveCode

			/**
			 * query to select the already saved leave types in saved order
			 */
			String leaveDataQuery =
				"SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE " + "WHERE LEAVE_ID IN (" + selectedLeaveCode[0][0] + ")";

			Object[][] matchingLeaveData = getSqlModel().getSingleResult(leaveDataQuery);

			/**
			 * iterate over matchingLeaveData array and store the data in ShiftMaster instance and then add this instance to the list
			 */
			if(matchingLeaveData != null && matchingLeaveData.length != 0) {
				for(int i = 0; i < leaveCodeSplit.length; i++) {
					logger.info("leaveCodeSplit[i] : " + leaveCodeSplit[i]);
					for(int j = 0; j < matchingLeaveData.length; j++) {
						logger.info("matchingLeaveData[j][0] : " + matchingLeaveData[j][0]);
						if(leaveCodeSplit[i].equals(String.valueOf(matchingLeaveData[j][0]))) {
							ShiftMaster bean1 = new ShiftMaster();
							bean1.setLeaveCode(String.valueOf(matchingLeaveData[j][0])); // leave
							// code
							bean1.setLeaveName(String.valueOf(matchingLeaveData[j][1])); // leave
							// name
							bean1.setLeaveAbbr(String.valueOf(matchingLeaveData[j][2])); // leave
							bean1.setCheck("true"); // abbreviation
							leaveDataList.add(bean1);
							break;
						} else if(leaveCodeSplit[i].equals("0")) {
							// FOR UNPAID LEAVE TYPE SAVED
							flag = true;
						}
					}
				}
			}

			/**
			 * query to select the rest of leave types
			 */
			leaveDataQuery =
				"SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE " + "WHERE LEAVE_ID NOT IN (" + selectedLeaveCode[0][0]
					+ ") " + "ORDER BY  LEAVE_ID";

			leaveData = getSqlModel().getSingleResult(leaveDataQuery);
		} else { // if no link defined already then select all records from HRMS_LEAVE
			String query = "SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE " + "ORDER BY  LEAVE_ID";
			leaveData = getSqlModel().getSingleResult(query);
		}

		/**
		 * iterate over leaveData array and store the data in AttendanceSettings instance and then add this instance to the list
		 */
		if(leaveData != null && leaveData.length != 0) {
			for(int i = 0; i < leaveData.length; i++) {
				try {
					ShiftMaster bean1 = new ShiftMaster();
					bean1.setLeaveCode(String.valueOf(leaveData[i][0])); // leave code
					bean1.setLeaveName(String.valueOf(leaveData[i][1])); // leave name
					bean1.setLeaveAbbr(String.valueOf(leaveData[i][2])); // leave abbreviation
					bean1.setCheck("false");
					leaveDataList.add(bean1);
				} catch(Exception e) {
					logger.error("error in leave combination " + e);
				} // end of try-catch block
			} // end of for loop

			// ADD UNPAID LEAVE TYPE TO LIST
			ShiftMaster bean1 = new ShiftMaster();
			bean1.setLeaveCode("0"); // leave code
			bean1.setLeaveName("UnPaid Leave"); // leave name
			bean1.setLeaveAbbr("UnPaid");// leave abbreviation
			// DISABLES PRIORITY FOR UNPAID LEAVE TYPE
			bean1.setCheckFlag("true");
			if(flag) {
				// IF SAVED RECORD, CHECKBOX IS CHECKED
				bean1.setCheck("true");
			} else {
				bean1.setCheck("false");
			}
			leaveDataList.add(bean1);

			bean.setLeaveTypeFlag("true"); // set leaveTypeFlag to true
			bean.setLeaveDataList(leaveDataList); // set leaveDataList
		} // end of if statement
		else {
			bean.setLeaveTypeFlag("true");
			bean.setLeaveDataList(leaveDataList);
		}

	}

	/**
	 * Modifies the shifts
	 * 
	 * @param bean
	 * @param sun
	 * @param mon
	 * @param tue
	 * @param wed
	 * @param thu
	 * @param fri
	 * @param sat
	 * @return boolean as whether shift modifies or not
	 */
	public String modShift(ShiftMaster bean, String[] sun, String[] mon, String[] tue, String[] wed, String[] thu, String[] fri,
		String[] sat) {
		if(!checkDuplicate(bean)) {
			try {
				String[] orgDaysVal = {"", "", "", "", "", ""};
				
				if(sun != null) {
					for(int i = 0; i < sun.length; i++) {
						orgDaysVal[0] += sun[i] + ",";
					}
				}
				
				if(mon != null) {
					for(int i = 0; i < mon.length; i++) {
						orgDaysVal[1] += mon[i] + ",";
					}
				}
				
				if(tue != null) {
					for(int i = 0; i < tue.length; i++) {
						orgDaysVal[2] += tue[i] + ",";
					}
				}
				
				if(wed != null) {
					for(int i = 0; i < wed.length; i++) {
						orgDaysVal[3] += wed[i] + ",";
					}
				}
				
				if(thu != null) {
					for(int i = 0; i < thu.length; i++) {
						orgDaysVal[4] += thu[i] + ",";
					}
				}
				
				if(fri != null) {
					for(int i = 0; i < fri.length; i++) {
						orgDaysVal[5] += fri[i] + ",";
					}
				}
				
				if(sat != null) {
					for(int i = 0; i < sat.length; i++) {
						orgDaysVal[6] += sat[i] + ",";
					}
				}
				
				Object modObj[][] = new Object[1][55];
				// DIV 1
				modObj[0][0] = checkNull(bean.getShiftName());
				modObj[0][1] = checkNull(bean.getShiftStrTime()).trim();
				modObj[0][2] = checkNull(bean.getReportTimeLate()).trim();
				modObj[0][3] = checkNull(bean.getReportTimeHalf()).trim();
				modObj[0][4] = checkNull(bean.getLunchBreakStart()).trim();
				modObj[0][5] = checkNull(bean.getLunchBreakEnd()).trim();
				modObj[0][6] = checkNull(bean.getOffLeftHalfDay()).trim();
				modObj[0][7] = checkNull(bean.getOffLeftEarly()).trim();
				modObj[0][8] = checkNull(bean.getShiftEndTime()).trim();
				modObj[0][9] = checkNull(bean.getShiftWorkHrs()).trim();
				modObj[0][10] = checkNull(bean.getExtraWorkHrs()).trim();
				if(checkNull(bean.getPersonalTime()).equals("true")) {
					modObj[0][11] = "Y";
				} else {
					modObj[0][11] = "N";
				}
				modObj[0][12] = checkNull(bean.getFreePersonalTimeOf()).trim();
				modObj[0][13] = checkNull(bean.getFreePersonalTimePer()).trim();
				if(checkNull(bean.getAdjPersonalTime()).equals("true")) {
					modObj[0][14] = "Y";
				} else {
					modObj[0][14] = "N";
				}

				// DIV 2
				if(checkNull(bean.getEnableMonitor()).equals("true")) {
					modObj[0][15] = "Y";
				} else {
					modObj[0][15] = "N";
				}
				if(checkNull(bean.getBLateMark()).equals("true")) {
					modObj[0][16] = "Y";
				} else {
					modObj[0][16] = "N";
				}
				modObj[0][17] = checkNull(bean.getAdjustLMCount()).trim();
				modObj[0][18] = checkNull(bean.getAdjustLMLevDays()).trim();
				modObj[0][19] = checkNull(bean.getLateCombineLeaveCode()).trim();
				if(checkNull(bean.getLmHrsIsEnabled()).equals("true")) {
					modObj[0][20] = "Y";
				} else {
					modObj[0][20] = "N";
				}
				if(checkNull(bean.getLmReglIsEnabled()).equals("true")) {
					modObj[0][21] = "Y";
				} else {
					modObj[0][21] = "N";
				}
				modObj[0][22] = checkNull(bean.getLateMarksLeaveCode()).trim();
				modObj[0][23] = checkNull(bean.getDedLmInSlabsOf()).trim();
				modObj[0][24] = checkNull(bean.getNonRegLateMarksLeaveCode()).trim();
				if(checkNull(bean.getAdjustExtraWorkLm()).equals("true")) {
					modObj[0][25] = "Y";
				} else {
					modObj[0][25] = "N";
				}
				modObj[0][26] = checkNull(bean.getExtraWorkForLM()).trim();
				modObj[0][27] = checkNull(bean.getExtraWorkForLmOf()).trim();

				// DIV 3
				if(checkNull(bean.getEnableHalfMonitor()).equals("true")) {
					modObj[0][28] = "Y";
				} else {
					modObj[0][28] = "N";
				}
				modObj[0][29] = checkNull(bean.getRegHalfDayLeaveCode()).trim();
				if(checkNull(bean.getAdjustExtraWorkHd()).equals("true")) {
					modObj[0][30] = "Y";
				} else {
					modObj[0][30] = "N";
				}
				modObj[0][31] = checkNull(bean.getExtraWorkForHD()).trim();
				modObj[0][32] = checkNull(bean.getExtraWorkForHdOf()).trim();

				// DIV 4
				if(checkNull(bean.getVariableWeekOff()).equals("true")) {
					modObj[0][33] = "Y";
				} else {
					modObj[0][33] = "N";
				}
				modObj[0][34] = checkNull(bean.getVariWoPerMonth()).trim();
				if(checkNull(bean.getFixedWeekOff()).equals("true")) {
					modObj[0][35] = "Y";
				} else {
					modObj[0][35] = "N";
				}
				modObj[0][36] = checkNull(bean.getShiftNtFlag()).trim();
				for(int i = 0; i < 6; i++) {
					try {
						if(!(orgDaysVal[i] == null || orgDaysVal[i] == "")) {
							modObj[0][i + 37] = orgDaysVal[i].substring(0, orgDaysVal[i].length() - 1);
						}
					} catch(Exception e) {
						logger.info(e);
					}
				}
				
				modObj[0][43] = checkNull(bean.getMarkAbsentAfterThisTime()).trim();
				modObj[0][44] = checkNull(bean.getFlexiTimeAllowed()).trim();
				if(checkNull(bean.getFlexiTimeAllowed()).equals("true")) {
					modObj[0][44] = "Y";
				} else {
					modObj[0][44] = "N";
				}
				
				modObj[0][45] = checkNull(bean.getMarkFlexiLateAfterThisTime()).trim();
				modObj[0][46] = checkNull(bean.getMarkFlexiHalfDayAfterThisTime()).trim();
				modObj[0][47] = checkNull(bean.getWaiveOffLateMarks()).trim();
				
				//OT Configuration -- BEGINS
				modObj[0][48] = checkNull(bean.getEnableOTConfigWorkflow()).trim().equals("true")?"Y":"N";
				if (checkNull(bean.getActualHoursWorkedOT()).trim().equals("true")) {
					modObj[0][49] = "W";
				} else if(checkNull(bean.getActualOutTimeOT()).trim().equals("true")){
					modObj[0][49] = "T";
				} 
				modObj[0][50] = checkNull(bean.getRegularOtHourlyRateFormulaOT()).trim();
				modObj[0][51] = checkNull(bean.getWeeklyOffHolidayOtHourlyFormulaOT()).trim();
				modObj[0][52] = checkNull(bean.getDoubleOtHourlyFormulaOT()).trim();
				//OT Configuration -- ENDS
				modObj[0][53]=checkNull(bean.getMarkFlexiAbsentBeforeThisTime()).trim();
				modObj[0][54] = checkNull(bean.getShiftID());
				
				
				boolean result = getSqlModel().singleExecute(getQuery(2), modObj);
				if(result) {
					// DIV 2
					boolean shiftDtlsShown = bean.isShiftDtlsShown();
					if(shiftDtlsShown) {
						boolean isShiftExceptionsAdded = addShiftExceptions(bean, checkNull(bean.getShiftID()), true);
						
						if(isShiftExceptionsAdded) {
							return "updated";
						} else {
							return "not updated";
						}
					}
					return "updated";
				} else {
					return "not updated";
				}
			} catch(Exception e) {
				return "not updated";
			}
		} else {
			return "duplicate";
		}
	}

	/**
	 * @method setLeavePriority
	 * @purpose to set the leave priorities
	 * @return type void
	 * @param bean
	 * @param srNo
	 * @param buttonType
	 * @param leaveCode
	 * @param leaveName
	 * @param leaveAbbr
	 */
	public void setLeavePriority(ShiftMaster bean, String srNo, String buttonType, String[] leaveCode, String[] leaveName,
		String[] leaveAbbr) {
		ArrayList<Object> leaveDataList = new ArrayList<Object>();
		int serialNo = Integer.parseInt(srNo);
		if(leaveCode != null && leaveCode.length != 0) {
			try {
				for(int i = 0; i < leaveCode.length; i++) {
					ShiftMaster bean1 = new ShiftMaster();
					bean1.setLeaveCode(String.valueOf(leaveCode[i]));
					bean1.setLeaveName(String.valueOf(leaveName[i]));
					bean1.setLeaveAbbr(String.valueOf(leaveAbbr[i]));
					if(bean1.getLeaveCode().equals("0"))
						bean1.setCheckFlag("true");
					leaveDataList.add(bean1);
				} // end of for loop
			} catch(Exception e) {} // end of try-catch block
			if(buttonType.equals("up")) {
				ShiftMaster bean1 = (ShiftMaster)leaveDataList.get(serialNo - 1);
				serialNo = Integer.parseInt(srNo) - 1;
				if(serialNo > 0) {
					leaveDataList.add(serialNo - 1, bean1);
					leaveDataList.remove(serialNo + 1);
				} // end of if statement
			} // end of if statement
			else if(buttonType.equals("down")) {
				ShiftMaster bean1 = (ShiftMaster)leaveDataList.get(serialNo - 1);
				serialNo = Integer.parseInt(srNo) + 1;
				logger.info("serialNo   : " + serialNo);
				logger.info("leaveDataList.size()   : " + leaveDataList.size());
				if(serialNo > 0 && serialNo < leaveDataList.size()) {
					leaveDataList.add(serialNo, bean1);
					leaveDataList.remove(serialNo - 2);
				} // end of if statement
			} // end of else statement
			bean.setLeaveDataList(leaveDataList);
		} // end of if statement
	}

	public boolean deleteShiftExceptionDetails(String shiftID, String weekDay) {
		boolean isShiftExcpDeleted = false;
		try {
			Object[][] param = new Object[1][2];
			param[0][0] = shiftID;
			param[0][1] = weekDay;
			isShiftExcpDeleted = getSqlModel().singleExecute(getQuery(10), param);
		} catch(Exception e) {
			logger.error("Exception in deleteShiftExceptionDetails:" + e);
		}
		return isShiftExcpDeleted;
	}
}