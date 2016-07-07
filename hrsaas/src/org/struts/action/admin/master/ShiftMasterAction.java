/* Reeba Joseph 06 OCT 2009 */

package org.struts.action.admin.master;

import java.io.PrintWriter;
import org.paradyne.bean.admin.master.ShiftMaster;
import org.paradyne.model.admin.master.ShiftModel;
import org.struts.lib.ParaActionSupport;

/**
 * To define the shifts and assign the weekly off days
 */

public class ShiftMasterAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ShiftMasterAction.class);
	ShiftMaster shfMaster = null;

	public String addNew() {
		try {
			getNavigationPanel(2);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/**
	 * Called on Double clicking any record Edits the record selected.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callforedit() {
		try {
			String shiftCode = request.getParameter("shiftCode");
			shfMaster.setShiftID(shiftCode);
			ShiftModel model = new ShiftModel();
			model.initiate(context, session);
			model.getShiftRecords(shfMaster);
			String weekDays[][] = model.getFixedWeekRecords(shfMaster);
			request.setAttribute("weekDays", weekDays);
			model.terminate();
			getNavigationPanel(3);
			shfMaster.setEnableAll("N");
		} catch (Exception e) {
			logger.error("Exception in callforedit in action:" + e);
		}
		return SUCCESS;
	}

	private String checkNull(String result) {
		try {
			if (result.equals("null")) {
				return "";
			} else {
				return result;
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Deletes the shift defined along with weekly off assign
	 * 
	 * @return String SUCCESS
	 */
	public String delete() {
		try {
			ShiftModel model = new ShiftModel();
			model.initiate(context, session);

			boolean result = model.deleteShift(shfMaster);

			model.terminate();

			if (result) {
				addActionMessage(getMessage("delete"));
				reset();
				getNavigationPanel(1);
				input();
				return INPUT;
			} else {
				addActionMessage(getMessage("del.error"));
				getNavigationPanel(3);
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error("Exception in delete in action:" + e);
			return null;
		}
	}

	/**
	 * Called on Remove button For deleting multiple records
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete1() {
		try {
			getNavigationPanel(1);

			ShiftModel model = new ShiftModel();
			model.initiate(context, session);

			String[] code = request.getParameterValues("hdeleteCode");
			boolean result = model.delChkdRec(code);

			if (result) {
				addActionMessage(getMessage("delete"));
			} else {
				addActionMessage(getMessage("multiple.del.error"));
			}

			input();
		} catch (Exception e) {
			logger.error("Exception in delete1 in action:" + e);
		}
		return INPUT;
	}

	/**
	 * Overriding super class method
	 * 
	 * @return java bean object
	 */
	public Object getModel() {
		return shfMaster;
	}

	/**
	 * Gets the ShiftMaster bean object
	 * 
	 * @return ShiftMaster bean object
	 */
	public ShiftMaster getShfMaster() {
		return shfMaster;
	}

	public String getShiftExceptionDetails() {
		try {
			ShiftModel shiftModel = new ShiftModel();
			shiftModel.initiate(context, session);

			String shiftID = request.getParameter("shiftID");
			String weekDay = request.getParameter("selWeekDay");

			// Added by manish (start)
			shiftModel.getShiftRecords(shfMaster);
			String weekDays[][] = shiftModel.getFixedWeekRecords(shfMaster);
			request.setAttribute("weekDays", weekDays);
			// Added by manish (End)

			Object[][] shiftExceptionDetails = shiftModel
					.getShiftExceptionDetails(shiftID, weekDay);

			shiftModel.terminate();

			if (shiftExceptionDetails != null
					&& shiftExceptionDetails.length > 0) {
				shfMaster.setDtlShiftStartTime(checkNull(String
						.valueOf(shiftExceptionDetails[0][0])));
				shfMaster.setDtlReportTimeLate(checkNull(String
						.valueOf(shiftExceptionDetails[0][1])));
				shfMaster.setDtlReportTimeHalf(checkNull(String
						.valueOf(shiftExceptionDetails[0][2])));
				shfMaster.setDtlLunchBreakStart(checkNull(String
						.valueOf(shiftExceptionDetails[0][3])));
				shfMaster.setDtlLunchBreakEnd(checkNull(String
						.valueOf(shiftExceptionDetails[0][4])));
				shfMaster.setDtlOffLeftHalfDay(checkNull(String
						.valueOf(shiftExceptionDetails[0][5])));
				shfMaster.setDtlOffLeftEarly(checkNull(String
						.valueOf(shiftExceptionDetails[0][6])));
				shfMaster.setDtlShiftEndTime(checkNull(String
						.valueOf(shiftExceptionDetails[0][7])));
				shfMaster.setDtlShiftWorkHrs(checkNull(String
						.valueOf(shiftExceptionDetails[0][8])));
				shfMaster.setDtlExtraWorkHrs(checkNull(String
						.valueOf(shiftExceptionDetails[0][9])));
				shfMaster.setDtlShiftNtFlag(checkNull(String
						.valueOf(shiftExceptionDetails[0][10])));
				shfMaster.setShiftDtlsExists(true);
			} else {
				shfMaster.setDtlShiftStartTime("");
				shfMaster.setDtlReportTimeLate("");
				shfMaster.setDtlReportTimeHalf("");
				shfMaster.setDtlLunchBreakStart("");
				shfMaster.setDtlLunchBreakEnd("");
				shfMaster.setDtlOffLeftHalfDay("");
				shfMaster.setDtlOffLeftEarly("");
				shfMaster.setDtlShiftEndTime("");
				shfMaster.setDtlShiftWorkHrs("");
				shfMaster.setDtlExtraWorkHrs("");
				shfMaster.setDtlShiftNtFlag("");
			}

			shfMaster.setWeekDay(weekDay);

			switch (Integer.parseInt(weekDay)) {
			case 1:
				shfMaster.setDayOfWeek("Sunday");
				break;
			case 2:
				shfMaster.setDayOfWeek("Monday");
				break;
			case 3:
				shfMaster.setDayOfWeek("Tuesday");
				break;
			case 4:
				shfMaster.setDayOfWeek("Wednesday");
				break;
			case 5:
				shfMaster.setDayOfWeek("Thursday");
				break;
			case 6:
				shfMaster.setDayOfWeek("Friday");
				break;
			case 7:
				shfMaster.setDayOfWeek("Saturday");
				break;
			default:
				break;
			}

			shfMaster.setSelWeekDay("");
			shfMaster.setShiftDtlsShown(true);
			String currentMode = shfMaster.getCurrentMode();
			String[] mode = currentMode.split(",");
			int navigationMode = Integer.parseInt(mode[0]);
			System.out.println(navigationMode);
			// int currentMode = Integer.parseInt(shfMaster.getCurrentMode());
			getNavigationPanel(navigationMode);

			return SUCCESS;
		} catch (Exception e) {
			logger
					.error("Exception in getShiftExceptionDetails in action:"
							+ e);
			return null;
		}
	}

	@Override
	public String input() {
		try {
			reset();

			ShiftModel model = new ShiftModel();
			model.initiate(context, session);

			model.callShiftList(shfMaster, request);

			model.terminate();

			getNavigationPanel(1);
		} catch (Exception e) {
			logger.error("Exception in input in action:" + e);
		}
		return INPUT;
	}

	/**
	 * Updated by : vivek wadhwani
	 * 
	 * @return - String SUCCESS
	 */
	public String back() {
		try {
			String currentMode = shfMaster.getCurrentMode();
			String[] mode = currentMode.split(",");
			int navigationMode = Integer.parseInt(mode[0]);
			System.out.println(navigationMode);
			getNavigationPanel(navigationMode);

		} catch (Exception e) {
			logger.error("Exception in back in action:" + e);
		}
		return SUCCESS;
	}

	/**
	 * method name : leaveCombination purpose : to display all the available
	 * leave types in new window return type : String parameter : none
	 */
	public String leaveCombination() {
		try {
			shfMaster.setLeaveCodeHidNext(shfMaster.getLeaveCodeHid());
			shfMaster.setLeaveAbbrHidNext(shfMaster.getLeaveAbbrHid());

			ShiftModel model = new ShiftModel();
			model.initiate(context, session);

			model.leaveCombination(shfMaster);

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in leaveCombination in action:" + e);
		}
		return "leaveList";
	}

	/**
	 * Overriding super class method
	 */
	public void prepare_local() throws Exception {
		shfMaster = new ShiftMaster();
		shfMaster.setMenuCode(93);
	}

	/**
	 * Generates a report
	 */
	/**
	 * @return String SUCCESS
	 */
	public String report() {
		try {
			ShiftModel model = new ShiftModel();
			model.initiate(context, session);

			model.getReport(shfMaster, request, response, context, session);

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in report in action:" + e);
		}
		return null;
	}

	/**
	 * Resets the fields
	 */
	/**
	 * @return String SUCCESS
	 */
	public String reset() {
		try {
			getNavigationPanel(2);

			// DIV 1
			shfMaster.setShiftID("");
			shfMaster.setShiftName("");
			shfMaster.setShiftStrTime("");
			shfMaster.setReportTimeLate("");
			shfMaster.setReportTimeHalf("");
			shfMaster.setLunchBreakStart("");
			shfMaster.setLunchBreakEnd("");
			shfMaster.setOffLeftHalfDay("");
			shfMaster.setOffLeftEarly("");
			shfMaster.setShiftEndTime("");
			shfMaster.setShiftWorkHrs("");
			shfMaster.setExtraWorkHrs("");
			shfMaster.setPersonalTime("");
			shfMaster.setFreePersonalTimeOf("");
			shfMaster.setFreePersonalTimePer("");
			shfMaster.setAdjPersonalTime("");
			shfMaster.setMarkAbsentAfterThisTime("");
			shfMaster.setFlexiTimeAllowed("");
			shfMaster.setMarkFlexiLateAfterThisTime("");
			shfMaster.setMarkFlexiHalfDayAfterThisTime("");

			// DIV 2
			shfMaster.setDtlShiftStartTime("");
			shfMaster.setDtlReportTimeLate("");
			shfMaster.setDtlReportTimeHalf("");
			shfMaster.setDtlLunchBreakStart("");
			shfMaster.setDtlLunchBreakEnd("");
			shfMaster.setDtlOffLeftHalfDay("");
			shfMaster.setDtlOffLeftEarly("");
			shfMaster.setDtlShiftEndTime("");
			shfMaster.setDtlShiftWorkHrs("");
			shfMaster.setDtlExtraWorkHrs("");
			shfMaster.setDtlShiftNtFlag("");
			shfMaster.setShiftDtlsShown(false);

			// DIV 3
			shfMaster.setEnableMonitor("");
			shfMaster.setBLateMark("");
			shfMaster.setAdjustLMCount("");
			shfMaster.setAdjustLMLevDays("");
			shfMaster.setLateCombineLeave("");
			shfMaster.setLateCombineLeaveCode("");
			shfMaster.setLmHrsIsEnabled("");
			shfMaster.setLmReglIsEnabled("");
			shfMaster.setLateMarksLeave("");
			shfMaster.setLateMarksLeaveCode("");
			shfMaster.setDedLmInSlabsOf("");
			shfMaster.setNonRegLateMarksLeave("");
			shfMaster.setNonRegLateMarksLeaveCode("");
			shfMaster.setAdjustExtraWorkLm("");
			shfMaster.setExtraWorkForLM("");
			shfMaster.setExtraWorkForLmOf("");
			shfMaster.setWaiveOffLateMarks("");

			// DIV 4
			shfMaster.setEnableHalfMonitor("");
			shfMaster.setEnableHalfDayRegl("");
			shfMaster.setRegHalfDayLeave("");
			shfMaster.setRegHalfDayLeaveCode("");
			shfMaster.setNonRegHalfDayLeave("");
			shfMaster.setNonRegHalfDayLeaveCode("");
			shfMaster.setAdjustExtraWorkHd("");
			shfMaster.setExtraWorkForHD("");
			shfMaster.setExtraWorkForHdOf("");

			// DIV 5
			shfMaster.setVariableWeekOff("");
			shfMaster.setVariWoPerMonth("");
			shfMaster.setFixedWeekOff("");
			shfMaster.setShiftNtFlag("");

			// DIV 6 -- OT Configuration
			shfMaster.setEnableOTConfigWorkflow("");
			shfMaster.setActualHoursWorkedOT("");
			shfMaster.setActualOutTimeOT("");
			shfMaster.setRegularOtHourlyRateFormulaOT("");
			shfMaster.setWeeklyOffHolidayOtHourlyFormulaOT("");
			shfMaster.setDoubleOtHourlyFormulaOT("");
		} catch (Exception e) {
			logger.error("Exception in reset in action:" + e);
		}
		return SUCCESS;
	}

	/**
	 * Saves the shifts
	 * 
	 * @return String SUCCESS
	 */
	public String save() {
		try {
			String str = "";

			String weekDay = request.getParameter("weekDay");

			if (!weekDay.equals("")) {
				switch (Integer.parseInt(weekDay)) {
				case 1:
					shfMaster.setDayOfWeek("Sunday");
					break;
				case 2:
					shfMaster.setDayOfWeek("Monday");
					break;
				case 3:
					shfMaster.setDayOfWeek("Tuesday");
					break;
				case 4:
					shfMaster.setDayOfWeek("Wednesday");
					break;
				case 5:
					shfMaster.setDayOfWeek("Thursday");
					break;
				case 6:
					shfMaster.setDayOfWeek("Friday");
					break;
				case 7:
					shfMaster.setDayOfWeek("Saturday");
					break;
				default:
					break;
				}
			}

			String[] sun = request.getParameterValues("week1");
			String[] mon = request.getParameterValues("week2");
			String[] tue = request.getParameterValues("week3");
			String[] wed = request.getParameterValues("week4");
			String[] thu = request.getParameterValues("week5");
			String[] fri = request.getParameterValues("week6");
			String[] sat = request.getParameterValues("week7");

			ShiftModel model = new ShiftModel();
			model.initiate(context, session);

			if (!shfMaster.getShiftID().equals("")) {
				try {
					str = model.modShift(shfMaster, sun, mon, tue, wed, thu,
							fri, sat);
				} catch (Exception e) {
					logger.error(e);
				}

				if (str == "updated") {
					setShiftRecords();
					addActionMessage(getMessage("update"));
				} else if (str == "not updated") {
					getNavigationPanel(2);
					addActionMessage(getMessage("update.error"));
					reset();
					shfMaster.setEnableAll("Y");
				} else {
					getNavigationPanel(2);
					addActionMessage(getMessage("duplicate"));
					reset();
					shfMaster.setEnableAll("Y");
				}
			} else {
				str = model.addShift(shfMaster, sun, mon, tue, wed, thu, fri,
						sat);

				if (str == "saved") {
					String codeQuery = " SELECT MAX(SHIFT_ID) FROM HRMS_SHIFT ";
					Object[][] codeObj = model.getSqlModel().getSingleResult(
							codeQuery);

					if (codeObj != null && codeObj.length > 0) {
						shfMaster.setShiftID(String.valueOf(codeObj[0][0]));
					}

					setShiftRecords();
					addActionMessage(getMessage("save"));
				} else if (str == "not saved") {
					getNavigationPanel(2);
					addActionMessage(getMessage("save.error"));
					reset();
					shfMaster.setEnableAll("Y");
				} else {
					getNavigationPanel(2);
					addActionMessage(getMessage("duplicate"));
					reset();
					shfMaster.setEnableAll("Y");
				}
			}
			shfMaster.setSelWeekDay("");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in save in action:" + e);
		}
		return SUCCESS;
	}

	/**
	 * Popup window contains saved records of shifts
	 * 
	 * @return String SUCCESS
	 */
	public String search() {
		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE
			 * DESIRED OUTPUT
			 */
			String query = " SELECT SHIFT_NAME, TO_CHAR(SFT_START_TIME, 'HH24:MI'), TO_CHAR(SFT_END_TIME, 'HH24:MI'), SHIFT_ID "
					+ " FROM HRMS_SHIFT ORDER BY UPPER(SHIFT_NAME) ";

			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP
			 * WINDOW. *
			 */
			String[] headers = { getMessage("shift"),
					getMessage("shiftstarttime"), getMessage("shiftendtime") };

			String[] headerWidth = { "40", "30", "30" };

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED
			 * AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS
			 * 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS
			 * CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN INDEX MUST
			 * BE SAME AS THE LENGTH OF FIELDNAMES
			 */

			String[] fieldNames = { "shiftName", "shiftStrTime",
					"shiftEndTime", "shiftID" };

			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT
			 * ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE
			 * SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE
			 * {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
			 */
			int[] columnIndex = { 0, 1, 2, 3 };

			/**
			 * WHEN SET TO 'true' WILL SUBMIT THE FORM
			 */
			String submitFlag = "true";

			/**
			 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
			 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
			 * ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "ShiftMaster_setShiftRecords.action";

			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
			 */

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			logger.error("Exception in search in action:" + e);
		}
		return "f9page";
	}

	/**
	 * method name : setLeavePriority purpose :to set the leave priorities
	 * return type : String parameter : none
	 */
	public String setLeavePriority() {
		try {
			/**
			 * getting all the form fields values which are necessary to set the
			 * leave priority
			 */
			String srNo = shfMaster.getSrNo();
			String buttonType = request.getParameter("type");
			String[] leaveCode = request.getParameterValues("leaveCode");
			String[] leaveName = request.getParameterValues("leaveName");
			String[] leaveAbbr = request.getParameterValues("leaveAbbr");

			ShiftModel model = new ShiftModel();
			model.initiate(context, session);

			model.setLeavePriority(shfMaster, srNo, buttonType, leaveCode,
					leaveName, leaveAbbr);

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setLeavePriority in action:" + e);
		}
		return "leaveList";
	}

	/**
	 * Sets the ShiftMaster bean object
	 * 
	 * @param shfMaster
	 */
	public void setShfMaster(ShiftMaster shfMaster) {
		this.shfMaster = shfMaster;
	}

	/**
	 * Show saved shifts
	 * 
	 * @return String SUCCESS
	 */
	public String setShiftRecords() {
		try {
			getNavigationPanel(3);

			ShiftModel model = new ShiftModel();
			model.initiate(context, session);

			model.getShiftRecords(shfMaster);
			String weekDays[][] = model.getFixedWeekRecords(shfMaster);
			request.setAttribute("weekDays", weekDays);

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setShiftRecords in action:" + e);
		}
		return SUCCESS;
	}

	public String deleteShiftExceptionDetails() {
		try {
			String shiftID = request.getParameter("shiftID");
			String weekDay = request.getParameter("weekDay");

			ShiftModel shiftModel = new ShiftModel();
			shiftModel.initiate(context, session);

			boolean isShiftExcpDeleted = shiftModel
					.deleteShiftExceptionDetails(shiftID, weekDay);

			shiftModel.terminate();

			String message = "Shift Exception Details cannot be deleted.";
			if (isShiftExcpDeleted) {
				message = "Shift Exception Details deleted successfully.";
			}
			getNavigationPanel(3);
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.print(message);
			pw.flush();
		} catch (Exception e) {
			logger.error("Exception in deleteShiftExceptionDetails in action:"
					+ e);
		}

		return null;

	}
}