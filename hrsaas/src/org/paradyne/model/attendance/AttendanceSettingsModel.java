package org.paradyne.model.attendance;

import java.util.ArrayList;
import org.paradyne.bean.attendance.AttendanceSettings;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Tarun Chaturvedi 23-06-2008 AttendanceSettingsModel class to write
 *         business logic define settings for file uploading
 */
public class AttendanceSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AttendanceSettingsModel.class);

	/**
	 * method name : getDailyAttendanceDetail purpose : to retrieve the details
	 * for daily work flow return type : void parameter : AttendanceSettings
	 * instance
	 */
	public void getDailyAttendanceDetail(AttendanceSettings bean) {
		Object[][] attendanceData = getSqlModel().getSingleResult(getQuery(7));

		if (attendanceData != null && attendanceData.length != 0) {
			if (String.valueOf(attendanceData[0][0]).equals("Y")) {
				bean.setAttendanceFlag(true);
			} else {
				bean.setAttendanceFlag(false);
			}

			bean.setStartDate(String.valueOf(attendanceData[0][1]).trim());
			bean.setSalaryFlag(String.valueOf(attendanceData[0][2]).trim());
			bean.setCompOffCode(String.valueOf(attendanceData[0][3]).trim());
			bean.setCompOffName(String.valueOf(attendanceData[0][4]).trim());

			if (String.valueOf(attendanceData[0][5]).equals("Y")) {
				bean.setLeaveApplicationFlag(true);
			} else {
				bean.setLeaveApplicationFlag(false);
			}

			if (String.valueOf(attendanceData[0][6]).equals("Y")) {
				bean.setBranchHolidayFlag(true);
			} else {
				bean.setBranchHolidayFlag(false);
			}

			bean.setRecordPerPage(String.valueOf(attendanceData[0][7]));
			bean.setLeaveMngtStartMonth(String.valueOf(attendanceData[0][8]));
			bean.setMonthlyAttendanceFlag(String.valueOf(attendanceData[0][9])
					.equals("Y") ? true : false);
			bean.setMonthlyPaidleave(getLeaveAbbr(String.valueOf(
					attendanceData[0][10]).trim()));
			bean.setMonthlyPaidleaveCode(String.valueOf(attendanceData[0][10]));
			bean.setCompoffdays(String.valueOf(attendanceData[0][11]).trim());
			bean.setEmpJoinBefore(Utility.checkNull(String.valueOf(
					attendanceData[0][12]).trim()));
			bean.setAlertLeave(Utility.checkNull(String.valueOf(
					attendanceData[0][13]).trim()));

			if (String.valueOf(attendanceData[0][14]).equals("Y")) {
				bean.setLoginAttendanceFlag(true);
			} else {
				bean.setLoginAttendanceFlag(false);
			}
			bean.setDefaultDayHidden(String.valueOf(attendanceData[0][15]));

			if (String.valueOf(attendanceData[0][16]).equals("Y")) {
				bean.setLateRegularizationCheckBox("true");
			} else {
				bean.setLateRegularizationCheckBox("false");
			}

			if (String.valueOf(attendanceData[0][17]).equals("Y")) {
				bean.setHalfDayRegularizationCheckBox("true");
			} else {
				bean.setHalfDayRegularizationCheckBox("false");
			}

			if (String.valueOf(attendanceData[0][18]).equals("Y")) {
				bean.setAbsentRegularizationCheckBox("true");
			} else {
				bean.setAbsentRegularizationCheckBox("false");
			}

			if (String.valueOf(attendanceData[0][19]).equals("Y")) {
				bean.setRegularTimeRegularizationCheckBox("true");
			} else {
				bean.setRegularTimeRegularizationCheckBox("false");
			}

			if (String.valueOf(attendanceData[0][20]).equals("Y")) {
				bean.setWeekyOffHolidayCheckBox("true");
			} else {
				bean.setWeekyOffHolidayCheckBox("false");
			}

			if (String.valueOf(attendanceData[0][21]).equals("Y")) {
				bean.setShowExtraWorkBenifitCheckBox("true");
			} else {
				bean.setShowExtraWorkBenifitCheckBox("false");
			}
			
			
		}
		bean.setOutdoorBlockAfterDays(String.valueOf(attendanceData[0][22]));

	}

	/**
	 * method name : getLeaveAbbr purpose : to retrieve the leave abbreviation
	 * for a given leave code return type : String parameter : String leaveCode
	 */
	public String getLeaveAbbr(String leaveCode) {
		String[] leaveCodeSplit = leaveCode.split(",");
		String leaveAbbr = "";

		if (!leaveCode.equals("")) {
			for (int i = 0; i < leaveCodeSplit.length; i++) {
				String query = "SELECT LEAVE_ABBR FROM HRMS_LEAVE WHERE LEAVE_ID = "
						+ leaveCodeSplit[i];
				Object[][] leaveAbbrData = getSqlModel().getSingleResult(query);

				if (leaveAbbrData != null && leaveAbbrData.length != 0) {
					if (leaveAbbr.equals("")) {
						leaveAbbr = String.valueOf(leaveAbbrData[0][0]).trim();
					} // end of if
					else {
						leaveAbbr += ","
								+ String.valueOf(leaveAbbrData[0][0]).trim();
					} // end of else
				} // end of if
			} // end of for loop
		} // end of if

		return leaveAbbr;
	}

	/**
	 * method name : insertDtlData purpose : to save the attendance settings
	 * details eg column numbers for a particular field etc return type :
	 * boolean parameter : AttendanceSettings instance
	 */
	public void insertDtlData(AttendanceSettings bean) {
		Object[][] attenDtlData = new Object[1][7];

		attenDtlData[0][0] = bean.getAttSettingsCode();
		attenDtlData[0][1] = bean.getAttSettingsCode();

		// Employee Number Details
		attenDtlData[0][2] = "Employee Number";

		if (bean.isEmpFlag()) {
			attenDtlData[0][3] = "Y";
		} else {
			attenDtlData[0][3] = "N";
		}

		attenDtlData[0][4] = bean.getEmpCharFrom();
		attenDtlData[0][5] = bean.getEmpCharTo();

		if (bean.getReportType().equals("x")) {
			attenDtlData[0][6] = bean.getEmpColumn();
		} else if (bean.getReportType().equals("c")) {
			attenDtlData[0][6] = bean.getEmpColumnNoCsv();
		} else {
			attenDtlData[0][6] = "";
		}

		getSqlModel().singleExecute(getQuery(4), attenDtlData);

		// Employee Number Details
		/*
		 * attenDtlData [0][2] = "Employee Name"; if(bean.isEmpNameFlag()){
		 * attenDtlData [0][3] = "Y"; }else{ attenDtlData [0][3] = "N"; }
		 * attenDtlData [0][4] = bean.getEmpNameCharFrom(); attenDtlData [0][5] =
		 * bean.getEmpNameCharTo(); if(bean.getReportType().equals("x")){
		 * attenDtlData [0][6] = bean.getEmpNameColumn(); }else
		 * if(bean.getReportType().equals("c")){ attenDtlData [0][6] =
		 * bean.getEmpNameColumnNoCsv(); }else{ attenDtlData [0][6] = ""; }
		 * getSqlModel().singleExecute(getQuery(4), attenDtlData);
		 */

		// Attendance Date Details
		attenDtlData[0][2] = "Attendance Date";

		if (bean.isDateFlag()) {
			attenDtlData[0][3] = "Y";
		} else {
			attenDtlData[0][3] = "N";
		}

		attenDtlData[0][4] = bean.getDateCharFrom();
		attenDtlData[0][5] = bean.getDateCharTo();

		if (bean.getReportType().equals("x")) {
			attenDtlData[0][6] = bean.getDateColumn();
		} else if (bean.getReportType().equals("c")) {
			attenDtlData[0][6] = bean.getDateColumnNoCsv();
		} else {
			attenDtlData[0][6] = "";
		}

		getSqlModel().singleExecute(getQuery(4), attenDtlData);

		/**
		 * In/Out Flag Details 1. In Time Details
		 */
		attenDtlData[0][2] = "In Time";
		if (bean.isInTimeFlag()) {
			attenDtlData[0][3] = "Y";
		} else {
			attenDtlData[0][3] = "N";
		}

		attenDtlData[0][4] = bean.getInTimeCharFrom();
		attenDtlData[0][5] = bean.getInTimeCharTo();

		if (bean.getReportType().equals("x")) {
			attenDtlData[0][6] = bean.getInTimeColumn();
		} else if (bean.getReportType().equals("c")) {
			attenDtlData[0][6] = bean.getInTimeColumnNoCsv();
		} else {
			attenDtlData[0][6] = "";
		}

		getSqlModel().singleExecute(getQuery(4), attenDtlData);

		/**
		 * In/Out Flag Details 1. Out Time Details
		 */
		attenDtlData[0][2] = "Out Time";
		if (bean.isOutTimeFlag()) {
			attenDtlData[0][3] = "Y";
		} else {
			attenDtlData[0][3] = "N";
		}

		attenDtlData[0][4] = bean.getOutTimeCharFrom();
		attenDtlData[0][5] = bean.getOutTimeCharTo();

		if (bean.getReportType().equals("x")) {
			attenDtlData[0][6] = bean.getOutTimeColumn();
		} else if (bean.getReportType().equals("c")) {
			attenDtlData[0][6] = bean.getOutTimeColumnNoCsv();
		} else {
			attenDtlData[0][6] = "";
		}

		getSqlModel().singleExecute(getQuery(4), attenDtlData);

		/**
		 * One Time Flag Details 1. Time Details
		 */
		attenDtlData[0][2] = "Time";
		if (bean.isTimeFlag()) {
			attenDtlData[0][3] = "Y";
		} else {
			attenDtlData[0][3] = "N";
		}

		attenDtlData[0][4] = bean.getTimeCharFrom();
		attenDtlData[0][5] = bean.getTimeCharTo();

		if (bean.getReportType().equals("x")) {
			attenDtlData[0][6] = bean.getTimeColumn();
		} else if (bean.getReportType().equals("c")) {
			attenDtlData[0][6] = bean.getTimeColumnNoCsv();
		} else {
			attenDtlData[0][6] = "";
		}

		getSqlModel().singleExecute(getQuery(4), attenDtlData);

		/**
		 * One Time Flag Details 1. Flag Details
		 */
		attenDtlData[0][2] = "I/O Flag";
		if (bean.isCheckFlag()) {
			attenDtlData[0][3] = "Y";
		} else {
			attenDtlData[0][3] = "N";
		}
		attenDtlData[0][4] = bean.getCheckCharFrom();
		attenDtlData[0][5] = bean.getCheckCharTo();

		if (bean.getReportType().equals("x")) {
			attenDtlData[0][6] = bean.getCheckColumn();
		} else if (bean.getReportType().equals("c")) {
			attenDtlData[0][6] = bean.getCheckColumnNoCsv();
		} else {
			attenDtlData[0][6] = "";
		}

		getSqlModel().singleExecute(getQuery(4), attenDtlData);

		// Attendance Work Hours Details
		attenDtlData[0][2] = "Work Hours";
		if (bean.isWorkHrsFlag()) {
			attenDtlData[0][3] = "Y";
		} else {
			attenDtlData[0][3] = "N";
		}

		attenDtlData[0][4] = bean.getWorkHrsCharFrom();
		attenDtlData[0][5] = bean.getWorkHrsCharTo();

		if (bean.getReportType().equals("x")) {
			attenDtlData[0][6] = bean.getWorkHrsColumn();
		} else if (bean.getReportType().equals("c")) {
			attenDtlData[0][6] = bean.getWorkHrsColumnNoCsv();
		} else {
			attenDtlData[0][6] = "";
		}

		getSqlModel().singleExecute(getQuery(4), attenDtlData);

		// Attendance Shift Details
		attenDtlData[0][2] = "Shift";
		if (bean.isShiftFlag()) {
			attenDtlData[0][3] = "Y";
		} else {
			attenDtlData[0][3] = "N";
		}

		attenDtlData[0][4] = bean.getShiftCharFrom();
		attenDtlData[0][5] = bean.getShiftCharTo();

		if (bean.getReportType().equals("x")) {
			attenDtlData[0][6] = bean.getShiftColumn();
		} else if (bean.getReportType().equals("c")) {
			attenDtlData[0][6] = bean.getShiftColumnNoCsv();
		} else {
			attenDtlData[0][6] = "";
		}

		getSqlModel().singleExecute(getQuery(4), attenDtlData);
	}

	/**
	 * method name : isDataExist purpose : to check whether the settings already
	 * present in table or not return type : void parameter : AttendanceSettings
	 * instance
	 */
	public boolean isDataExist(AttendanceSettings bean) {
		Object[] hdrData = new Object[3];
		hdrData[0] = bean.getReportType();
		hdrData[1] = bean.getBranchCode();
		hdrData[2] = bean.getDivCode();

		Object[][] isDataExist = getSqlModel().getSingleResult(getQuery(5),
				hdrData);

		if (isDataExist != null && isDataExist.length != 0) {
			bean.setAttSettingsCode(String.valueOf(isDataExist[0][0]));
			return true;
		} // end of if
		else {
			return false;
		} // end of else
	}

	/**
	 * method name : leaveCombination purpose : to retrieve all the leave types
	 * and set them in a new window return type : void parameter :
	 * AttendanceSettings instance
	 */
	public void leaveCombination(AttendanceSettings bean) {
		ArrayList<Object> leaveDataList = new ArrayList<Object>();
		Object[][] leaveData = null;

		String query = " SELECT CONF_PAIDLEAVES_LEAVEADJUST FROM HRMS_ATTENDANCE_CONF ";
		Object[][] selectedLeaveCode = getSqlModel().getSingleResult(query); // execute
																				// query
																				// and
																				// store
																				// result
																				// in
																				// selectedLeaveCode
		// object array

		/**
		 * if selectedLeaveCode array is not null or length of selectedLeaveCode
		 * is not 0 or element at 0 index in selectedLeaveCode array is not null
		 * or blank
		 */
		if (selectedLeaveCode != null && selectedLeaveCode.length != 0
				&& !String.valueOf(selectedLeaveCode[0][0]).equals("null")
				&& !String.valueOf(selectedLeaveCode[0][0]).equals("")) {
			String[] leaveCodeSplit = selectedLeaveCode[0][0].toString().split(
					","); // split selectedLeaveCode

			/**
			 * query to select the already saved leave types in saved order
			 */
			String leaveDataQuery = " SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE "
					+ " WHERE LEAVE_ID IN (" + selectedLeaveCode[0][0] + ") ";
			Object[][] matchingLeaveData = getSqlModel().getSingleResult(
					leaveDataQuery);

			/**
			 * iterate over matchingLeaveData array and store the data in
			 * AttendanceSettings instance and then add this instance to the
			 * list
			 */
			if (matchingLeaveData != null && matchingLeaveData.length != 0) {
				for (int i = 0; i < leaveCodeSplit.length; i++) {
					for (int j = 0; j < matchingLeaveData.length; j++) {
						try {
							if (leaveCodeSplit[i].equals(String
									.valueOf(matchingLeaveData[j][0]))) {
								AttendanceSettings bean1 = new AttendanceSettings();

								bean1.setLeaveCode(String
										.valueOf(matchingLeaveData[j][0])); // leave
																			// code
								bean1.setLeaveName(String
										.valueOf(matchingLeaveData[j][1])); // leave
																			// name
								bean1.setLeaveAbbr(String
										.valueOf(matchingLeaveData[j][2])); // leave
																			// abbreviation

								leaveDataList.add(bean1);
								break;
							}
						} catch (Exception e) {
						}
					}
				}
			}

			/**
			 * query to select the rest of leave types
			 */
			leaveDataQuery = " SELECT DISTINCT LEAVE_ID, LEAVE_NAME, LEAVE_ABBR FROM HRMS_LEAVE "
					+ " WHERE LEAVE_ID NOT IN ("
					+ selectedLeaveCode[0][0]
					+ ") ORDER BY LEAVE_ID ";
			leaveData = getSqlModel().getSingleResult(leaveDataQuery);
		} else { // if no link defined already then select all records from
					// HRMS_LEAVE
			leaveData = getSqlModel().getSingleResult(getQuery(10));
		}

		/**
		 * iterate over leaveData array and store the data in AttendanceSettings
		 * instance and then add this instance to the list
		 */
		if (leaveData != null && leaveData.length != 0) {
			for (int i = 0; i < leaveData.length; i++) {
				try {
					AttendanceSettings bean1 = new AttendanceSettings();

					bean1.setLeaveCode(String.valueOf(leaveData[i][0])); // leave
																			// code
					bean1.setLeaveName(String.valueOf(leaveData[i][1])); // leave
																			// name
					bean1.setLeaveAbbr(String.valueOf(leaveData[i][2])); // leave
																			// abbreviation

					leaveDataList.add(bean1);
				} catch (Exception e) {
					logger.error("error in leave combination " + e);
				} // end of try-catch block
			} // end of for loop

			bean.setLeaveDataList(leaveDataList); // set leaveDataList
		} else {
			bean.setLeaveDataList(leaveDataList);
		}
	}

	/**
	 * method name : saveDailyAttendanceDetails purpose : to save the attendance
	 * settings details eg division, branch, file type etc return type : boolean
	 * parameter : AttendanceSettings instance
	 */
	public boolean saveDailyAttendanceDetails(AttendanceSettings bean) {
		boolean result = false;
		Object[][] attenHdrData = new Object[1][6];
		String dateFormat = bean.getDdFormat() + bean.getDdSeparator()
				+ bean.getMmFormat() + bean.getMmSeparator()
				+ bean.getYyFormat();
		attenHdrData[0][0] = bean.getReportType();
		attenHdrData[0][1] = bean.getBranchCode();
		attenHdrData[0][2] = bean.getDivCode();
		attenHdrData[0][3] = dateFormat;
		attenHdrData[0][4] = bean.getTimeFormat();
		attenHdrData[0][5] = bean.getSheetNum();

		result = getSqlModel().singleExecute(getQuery(2), attenHdrData);

		if (result) {
			Object[][] attendanceSettingsCode = getSqlModel().getSingleResult(
					getQuery(3));
			bean.setAttSettingsCode(String
					.valueOf(attendanceSettingsCode[0][0]));
			insertDtlData(bean);
		}
		showRecords(bean);
		return result;
	}

	/**
	 * @method leaveCombination
	 * @purpose to set the leave priorities
	 * @return type void
	 * @param bean
	 * @param srNo
	 * @param buttonType
	 * @param leaveCode
	 * @param leaveName
	 * @param leaveAbbr
	 */
	public void setLeavePriority(AttendanceSettings bean, String srNo,
			String buttonType, String[] leaveCode, String[] leaveName,
			String[] leaveAbbr) {
		ArrayList<Object> leaveDataList = new ArrayList<Object>();
		int serialNo = Integer.parseInt(srNo);

		if (leaveCode != null && leaveCode.length != 0) {
			try {
				for (int i = 0; i < leaveCode.length; i++) {
					AttendanceSettings bean1 = new AttendanceSettings();

					bean1.setLeaveCode(String.valueOf(leaveCode[i]));
					bean1.setLeaveName(String.valueOf(leaveName[i]));
					bean1.setLeaveAbbr(String.valueOf(leaveAbbr[i]));

					leaveDataList.add(bean1);
				} // end of for loop
			} catch (Exception e) {
			} // end of try-catch block

			if (buttonType.equals("up")) {
				AttendanceSettings bean1 = (AttendanceSettings) leaveDataList
						.get(serialNo - 1);
				serialNo = Integer.parseInt(srNo) - 1;

				if (serialNo > 0) {
					leaveDataList.add(serialNo - 1, bean1);
					leaveDataList.remove(serialNo + 1);
				} // end of if statement
			} // end of if statement

			else if (buttonType.equals("down")) {
				AttendanceSettings bean1 = (AttendanceSettings) leaveDataList
						.get(serialNo - 1);
				serialNo = Integer.parseInt(srNo) + 1;

				if (serialNo > 0 && serialNo < leaveDataList.size() + 1) {
					leaveDataList.add(serialNo, bean1);

					leaveDataList.remove(serialNo - 2);
				} // end of if statement
			} // end of else statement

			bean.setLeaveDataList(leaveDataList);
		} // end of if statement
	}

	/**
	 * method name : showRecords purpose : to show the saved records return type :
	 * void parameter : AttendanceSettings instance
	 */
	public void showRecords(AttendanceSettings bean) {
		Object[] queryData = new Object[3];
		queryData[0] = bean.getReportType();
		queryData[1] = bean.getBranchCode();
		queryData[2] = bean.getDivCode();

		Object[][] hdrCode = getSqlModel().getSingleResult(getQuery(5),
				queryData);

		if (hdrCode != null && hdrCode.length != 0) {
			Object[] dtlCode = new Object[1];
			dtlCode[0] = hdrCode[0][0];
			String dateFormat = String.valueOf(hdrCode[0][1]);
			String format = String.valueOf(hdrCode[0][1]);
			String delimeter = "";
			String date = "";

			dateFormat = dateFormat.replaceAll("d", "h");
			dateFormat = dateFormat.replaceAll("M", "h");
			dateFormat = dateFormat.replaceAll("y", "h");

			for (int i = 0; i < dateFormat.length(); i++) {
				if (dateFormat.charAt(i) != 'h') {
					delimeter = String.valueOf(dateFormat.charAt(i));
					dateFormat = dateFormat.substring(i + 1);
					format = format.substring(i + 1);
					break;
				} else {
					date += format.charAt(i);
				}
			}

			bean.setDdFormat(date);
			bean.setDdSeparator(delimeter);

			date = "";

			for (int i = 0; i < dateFormat.length(); i++) {
				if (dateFormat.charAt(i) != 'h') {
					delimeter = String.valueOf(dateFormat.charAt(i));
					dateFormat = dateFormat.substring(i + 1);
					format = format.substring(i + 1);
					break;
				} else {
					date += format.charAt(i);
				}
			}

			bean.setMmFormat(date);
			bean.setMmSeparator(delimeter);
			bean.setYyFormat(format);
			bean.setAttSettingsCode(String.valueOf(hdrCode[0][0]).trim());
			bean.setTimeFormat(String.valueOf(hdrCode[0][2]).trim());
			bean.setSheetNum(String.valueOf(hdrCode[0][3]).trim());

			Object[][] dtlData = getSqlModel().getSingleResult(getQuery(6),
					dtlCode);

			if (dtlData != null && dtlData.length != 0) {
				for (int i = 0; i < dtlData.length; i++) {

					/** *********Employee Number*************** */
					if (String.valueOf(dtlData[i][0]).equals("Employee Number")) {
						if (String.valueOf(dtlData[i][1]).equals("Y")) {
							bean.setEmpFlag(true);
						} else {
							bean.setEmpFlag(false);
						}

						bean.setEmpCharFrom(String.valueOf(dtlData[i][2])
								.trim());
						bean.setEmpCharTo(String.valueOf(dtlData[i][3]).trim());

						if (bean.getReportType().equals("x")) {
							bean.setEmpColumn(String.valueOf(dtlData[i][4])
									.trim());
						} else if (bean.getReportType().equals("c")) {
							bean.setEmpColumnNoCsv(String
									.valueOf(dtlData[i][4]).trim());
						}
					}

					/** *********Employee Name*************** */
					/*
					 * if(String.valueOf(dtlData[i][0]).equals("Employee
					 * Name")){ if(String.valueOf(dtlData[i][1]).equals("Y")){
					 * bean.setEmpNameFlag(true); }else{
					 * bean.setEmpNameFlag(false); }
					 * bean.setEmpNameCharFrom(String.valueOf(dtlData[i][2]));
					 * bean.setEmpNameCharTo(String.valueOf(dtlData[i][3]));
					 * if(bean.getReportType().equals("x")){
					 * bean.setEmpNameColumn(String.valueOf(dtlData[i][4]));
					 * }else if(bean.getReportType().equals("c")){
					 * bean.setEmpNameColumnNoCsv(String.valueOf(dtlData[i][4])); } }
					 */

					/** *********Attendance Date*************** */
					if (String.valueOf(dtlData[i][0]).equals("Attendance Date")) {
						if (String.valueOf(dtlData[i][1]).equals("Y")) {
							bean.setDateFlag(true);
						} else {
							bean.setDateFlag(false);
						}

						bean.setDateCharFrom(String.valueOf(dtlData[i][2])
								.trim());
						bean
								.setDateCharTo(String.valueOf(dtlData[i][3])
										.trim());

						if (bean.getReportType().equals("x")) {
							bean.setDateColumn(String.valueOf(dtlData[i][4])
									.trim());
						} else if (bean.getReportType().equals("c")) {
							bean.setDateColumnNoCsv(String.valueOf(
									dtlData[i][4]).trim());
						}
					}

					/** *********In Time*************** */
					if (String.valueOf(dtlData[i][0]).equals("In Time")) {
						if (String.valueOf(dtlData[i][1]).equals("Y")) {
							bean.setInOutFlag(true);
							bean.setOneTimeFlag(false);
							bean.setInTimeFlag(true);
						} else {
							bean.setInTimeFlag(false);
						}

						bean.setInTimeCharFrom(String.valueOf(dtlData[i][2])
								.trim());
						bean.setInTimeCharTo(String.valueOf(dtlData[i][3])
								.trim());

						if (bean.getReportType().equals("x")) {
							bean.setInTimeColumn(String.valueOf(dtlData[i][4])
									.trim());
						} else if (bean.getReportType().equals("c")) {
							bean.setInTimeColumnNoCsv(String.valueOf(
									dtlData[i][4]).trim());
						}
					}

					/** *********Out Time*************** */
					if (String.valueOf(dtlData[i][0]).equals("Out Time")) {
						if (String.valueOf(dtlData[i][1]).equals("Y")) {
							bean.setInOutFlag(true);
							bean.setOneTimeFlag(false);
							bean.setOutTimeFlag(true);
						} else {
							bean.setOutTimeFlag(false);
						}

						bean.setOutTimeCharFrom(String.valueOf(dtlData[i][2])
								.trim());
						bean.setOutTimeCharTo(String.valueOf(dtlData[i][3])
								.trim());

						if (bean.getReportType().equals("x")) {
							bean.setOutTimeColumn(String.valueOf(dtlData[i][4])
									.trim());
						} else if (bean.getReportType().equals("c")) {
							bean.setOutTimeColumnNoCsv(String.valueOf(
									dtlData[i][4]).trim());
						}
					}

					/** *********Time*************** */
					if (String.valueOf(dtlData[i][0]).equals("Time")) {
						if (String.valueOf(dtlData[i][1]).equals("Y")) {
							bean.setInOutFlag(false);
							bean.setOneTimeFlag(true);
							bean.setTimeFlag(true);
						} else {
							bean.setTimeFlag(false);
						}

						bean.setTimeCharFrom(String.valueOf(dtlData[i][2])
								.trim());
						bean
								.setTimeCharTo(String.valueOf(dtlData[i][3])
										.trim());

						if (bean.getReportType().equals("x")) {
							bean.setTimeColumn(String.valueOf(dtlData[i][4])
									.trim());
						} else if (bean.getReportType().equals("c")) {
							bean.setTimeColumnNoCsv(String.valueOf(
									dtlData[i][4]).trim());
						}
					}

					/** *********I/O Flag*************** */
					if (String.valueOf(dtlData[i][0]).equals("I/O Flag")) {
						if (String.valueOf(dtlData[i][1]).equals("Y")) {
							bean.setInOutFlag(false);
							bean.setOneTimeFlag(true);
							bean.setCheckFlag(true);
						} else {
							bean.setCheckFlag(false);
						}

						bean.setCheckCharFrom(String.valueOf(dtlData[i][2])
								.trim());
						bean.setCheckCharTo(String.valueOf(dtlData[i][3])
								.trim());

						if (bean.getReportType().equals("x")) {
							bean.setCheckColumn(String.valueOf(dtlData[i][4])
									.trim());
						} else if (bean.getReportType().equals("c")) {
							bean.setCheckColumnNoCsv(String.valueOf(
									dtlData[i][4]).trim());
						}
					}

					/** *********Work Hours*************** */
					if (String.valueOf(dtlData[i][0]).equals("Work Hours")) {
						if (String.valueOf(dtlData[i][1]).equals("Y")) {
							bean.setWorkHrsFlag(true);
						} else {
							bean.setWorkHrsFlag(false);
						}

						bean.setWorkHrsCharFrom(String.valueOf(dtlData[i][2])
								.trim());
						bean.setWorkHrsCharTo(String.valueOf(dtlData[i][3])
								.trim());

						if (bean.getReportType().equals("x")) {
							bean.setWorkHrsColumn(String.valueOf(dtlData[i][4])
									.trim());
						} else if (bean.getReportType().equals("c")) {
							bean.setWorkHrsColumnNoCsv(String.valueOf(
									dtlData[i][4]).trim());
						}
					}

					/** *********Shift*************** */
					if (String.valueOf(dtlData[i][0]).equals("Shift")) {
						if (String.valueOf(dtlData[i][1]).equals("Y")) {
							bean.setShiftFlag(true);
						} else {
							bean.setShiftFlag(false);
						}

						bean.setShiftCharFrom(String.valueOf(dtlData[i][2])
								.trim());
						bean.setShiftCharTo(String.valueOf(dtlData[i][3])
								.trim());

						if (bean.getReportType().equals("x")) {
							bean.setShiftColumn(String.valueOf(dtlData[i][4])
									.trim());
						} else if (bean.getReportType().equals("c")) {
							bean.setShiftColumnNoCsv(String.valueOf(
									dtlData[i][4]).trim());
						}
					}
				}
			}
		}
	}

	/**
	 * method name : updateAttendanceSettings purpose : to update the daily work
	 * flow details return type : boolean parameter : AttendanceSettings
	 * instance
	 */
	public boolean updateAttendanceSettings(AttendanceSettings bean) {
		Object[][] dailyAttenData = new Object[1][22];
		boolean result = false;

		if (bean.isLeaveApplicationFlag()) {
			dailyAttenData[0][0] = "Y";
		} else {
			dailyAttenData[0][0] = "N";
		}

		if (bean.isAttendanceFlag()) {
			dailyAttenData[0][1] = "Y";
		} else {
			dailyAttenData[0][1] = "N";
		}

		dailyAttenData[0][2] = bean.getStartDate();
		dailyAttenData[0][3] = bean.getSalaryFlag();

		if (bean.getCompOffCode().equals("0")
				|| bean.getCompOffCode().equals("")
				|| bean.getCompOffCode().equals("null")) {
			dailyAttenData[0][4] = "";
		} else {
			dailyAttenData[0][4] = bean.getCompOffCode();
		}

		if (bean.isBranchHolidayFlag()) {
			dailyAttenData[0][5] = "Y";
		} else {
			dailyAttenData[0][5] = "N";
		}

		dailyAttenData[0][6] = bean.getRecordPerPage();
		dailyAttenData[0][7] = bean.getLeaveMngtStartMonth();
		dailyAttenData[0][8] = bean.isMonthlyAttendanceFlag() ? "Y" : "N";
		dailyAttenData[0][9] = bean.getMonthlyPaidleaveCode();
		dailyAttenData[0][10] = bean.getCompoffdays().trim();
		dailyAttenData[0][11] = bean.getEmpJoinBefore().trim().equals("") ? null
				: bean.getEmpJoinBefore();
		System.out
				.println("bean.getAlertLeave()-------" + bean.getAlertLeave());
		if (!(bean.getAlertLeave().equals("")) && bean.getAlertLeave() != null) {
			dailyAttenData[0][12] = Integer.parseInt(bean.getAlertLeave()
					.trim());
		} else {
			dailyAttenData[0][12] = 0;
		}

		// dailyAttenData[0][12] = Integer.parseInt(bean.getAlertLeave());
		dailyAttenData[0][13] = bean.isLoginAttendanceFlag() ? "Y" : "N";
		dailyAttenData[0][14] = bean.getDefaultDay();
		dailyAttenData[0][15] = bean.getLateRegularizationCheckBox().equals(
				"true") ? "Y" : "N";
		dailyAttenData[0][16] = bean.getHalfDayRegularizationCheckBox().equals(
				"true") ? "Y" : "N";
		dailyAttenData[0][17] = bean.getAbsentRegularizationCheckBox().equals(
				"true") ? "Y" : "N";
		dailyAttenData[0][18] = bean.getRegularTimeRegularizationCheckBox()
				.equals("true") ? "Y" : "N";
		dailyAttenData[0][19] = bean.getWeekyOffHolidayCheckBox()
				.equals("true") ? "Y" : "N";
		dailyAttenData[0][20] = bean.getShowExtraWorkBenifitCheckBox().equals(
				"true") ? "Y" : "N";

		dailyAttenData[0][21] = bean.getOutdoorBlockAfterDays().trim();
		

		String selectQuery = "SELECT * FROM HRMS_ATTENDANCE_CONF";

		Object[][] queryData = getSqlModel().getSingleResult(selectQuery);

		if (queryData != null && queryData.length != 0) {
			result = getSqlModel().singleExecute(getQuery(1), dailyAttenData);
		} else {
			result = getSqlModel().singleExecute(getQuery(11), dailyAttenData);
		}

		getDailyAttendanceDetail(bean);
		return result;
	}

	/**
	 * method name : updateDailyAttendanceDetails purpose : to update the
	 * attendance settings details return type : boolean parameter :
	 * AttendanceSettings instance
	 */
	public boolean updateDailyAttendanceDetails(AttendanceSettings bean) {
		Object[][] attenHdrData = new Object[1][7];
		boolean result = false;

		String dateFormat = bean.getDdFormat() + bean.getDdSeparator()
				+ bean.getMmFormat() + bean.getMmSeparator()
				+ bean.getYyFormat();

		attenHdrData[0][0] = bean.getReportType();
		attenHdrData[0][1] = bean.getBranchCode();
		attenHdrData[0][2] = bean.getDivCode();
		attenHdrData[0][3] = dateFormat;
		attenHdrData[0][4] = bean.getTimeFormat();
		attenHdrData[0][5] = bean.getSheetNum();
		attenHdrData[0][6] = bean.getAttSettingsCode();

		result = getSqlModel().singleExecute(getQuery(8), attenHdrData);

		if (result) {
			Object[][] hdrCode = new Object[1][1];
			hdrCode[0][0] = bean.getAttSettingsCode().trim();

			try {
				result = getSqlModel().singleExecute(getQuery(9), hdrCode);
			} catch (Exception e) {
				logger.error("" + e);
			}

			if (result) {
				insertDtlData(bean);
			} // end of if
		} // end of if

		showRecords(bean);

		return result;
	}
}