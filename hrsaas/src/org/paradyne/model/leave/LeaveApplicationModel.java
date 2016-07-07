package org.paradyne.model.leave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.common.ReportingModel;

/**
 * @author VISHWAMBHAR DESHPANDE
 * @modified By AA1711
 * @purpose: Change to display records in My Attendance
 */
/**
 * Note:- Please find comments as (Please refer Leave Policy Document section)
 * for policy rules.
 */
public class LeaveApplicationModel extends ModelBase {

	/** leaveApplication. */
	private LeaveApplication leaveApplication;
	/** alertMessage. */
	private String alertMessage = "";
	/** policyCode. */
	private String policyCode = "";
	/** logger. */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * Method Name: getFilters() 
	 * @purpose: THIS METHOD IS USED FOR CHECKING BRANCH WISE HOLIDAY FLAG
	 * @param bean
	 */
	public void getFilters(final LeaveApplication bean) {
		try {
			final String fiterquery = " SELECT DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false') BRN_HDAY_FLAG "
					+ " FROM HRMS_ATTENDANCE_CONF ";
			final Object[][] fitObj = getSqlModel().getSingleResult(fiterquery);
			if (fitObj != null && fitObj.length > 0) {
				bean.setBrnHDayFlag(String.valueOf(fitObj[0][0]));
			}
		} catch (Exception e) {
			logger.error("Exception in getFilters--------- " + e);
		}
	}// end of getFilters

	/**
	 * Method Name: calculate() 
	 * @purpose: THIS METHOD IS USED FOR CALCULATING LEAVE DAYS ACCORDING TO
	 *           LEAVE POLICY
	 * @param startDate
	 * @param endDate
	 * @param leaveApplication
	 * @return number of leave days
	 */
	public int calculate(String startDate, String endDate,
			LeaveApplication leaveApplication) {
		int total = 0;
		try {
			final String policyCode = getLeavePolicyCode(leaveApplication
					.getEmpCode());
			int totalHol = 0;
			int weekenHol = 0;
			Object[][] holidayObj = null;
			final String select = "SELECT TO_DATE('" + endDate
					+ "','DD-MM-YYYY')-TO_DATE('" + startDate
					+ "','DD-MM-YYYY') from  DUAL";
			final Object[][] dateDiff = getSqlModel().getSingleResult(select);
			final String leaveType = leaveApplication.getLevCode();
			final String holidayFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_CODE"
					+ " FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE  LEAVE_POLICY_CODE="
					+ policyCode
					+ "  AND (LEAVE_HOLIDAY_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
					+ leaveApplication.getLevCode() + ")";
			final Object[][] holidayCheckObj = getSqlModel().getSingleResult(
					holidayFlag);
			final String weekFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_WEEKOFF_INCLUDE_FLAG,LEAVE_CODE"
					+ " FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE  LEAVE_POLICY_CODE="
					+ policyCode
					+ "  AND (LEAVE_WEEKOFF_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
					+ leaveApplication.getLevCode() + ")";
			final Object[][] weeklyCheckObj = getSqlModel().getSingleResult(
					weekFlag);
			/**
			 * CHECK HOLIDAY WILL EXCLUDE FROM LEAVE
			 */
			/*
			 * if (holidayCheckObj.length > 0) { String holStr = " SELECT
			 * TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY" + " WHERE
			 * HOLI_DATE >=TO_DATE('" + startDate + "','DD-MM-YYYY') AND
			 * HOLI_DATE <=TO_DATE('" + endDate + "','DD-MM-YYYY')"; holidayObj =
			 * getSqlModel().getSingleResult(holStr); totalHol =
			 * holidayObj.length; } // end of if
			 *//**
			 * CHECK HOLIDAY WILL EXCLUDE FROM LEAVE TAKING HOLIDAY BRANCH
			 * WISE
			 */
			if (holidayCheckObj.length > 0) {
				if (leaveApplication.getBrnHDayFlag().equals("true")) {
					final String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH "
							+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
							+ leaveApplication.getEmpCode()
							+ ")"
							+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE >=TO_DATE('"
							+ startDate
							+ "','DD-MM-YYYY') "
							+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE <=TO_DATE('"
							+ endDate + "','DD-MM-YYYY')";
					holidayObj = getSqlModel().getSingleResult(holStr);
					totalHol = holidayObj.length;

				} // end of if
				else {
					final String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
							+ " WHERE HOLI_DATE >=TO_DATE('"
							+ startDate
							+ "','DD-MM-YYYY') AND HOLI_DATE <=TO_DATE('"
							+ endDate + "','DD-MM-YYYY')";
					holidayObj = getSqlModel().getSingleResult(holStr);
					totalHol = holidayObj.length;

				} // end of else
			} // end of if
			/** CHECK WEEKEND WILL INCLUDE IN LEAVE */
			if (weeklyCheckObj.length > 0) {
				/** check whether holiday falls in weekend */
				if (holidayObj != null) {
					if (!(new Utility().checkDate(startDate, endDate) == 1 || new Utility()
							.checkDate(startDate, endDate) == 9999)) {
						weekenHol = countNumberOfWeeklyOff(new Utility()
								.getCalanderDate(startDate), new Utility()
								.getCalanderDate(endDate), holidayObj,
								leaveApplication.getEmpCode());

					}// end of nested if
					totalHol += weekenHol;
				} // end of if
				else { // holiday will not exclude
					if (!(new Utility().checkDate(startDate, endDate) == 1 || new Utility()
							.checkDate(startDate, endDate) == 9999)) {
						totalHol = countNumberOfWeeklyOff(new Utility()
								.getCalanderDate(startDate), new Utility()
								.getCalanderDate(endDate), leaveApplication
								.getEmpCode());
					}// end of if
				}// end of else
			}// end of if
			if (leaveApplication.getCheckMe().equals("true")
					|| leaveApplication.getCheckMeForhalfTodate()
							.equals("true")) {
				if (!(new Utility().checkDate(startDate, endDate) == 1 || new Utility()
						.checkDate(startDate, endDate) == 9999)) {
					totalHol = countNumberOfWeeklyOff(new Utility()
							.getCalanderDate(startDate), new Utility()
							.getCalanderDate(endDate), leaveApplication
							.getEmpCode());
				}
			}
			total = (Integer.parseInt(String.valueOf(dateDiff[0][0])) + 1)
					- (totalHol);
			/**
			 * }else{ total =Integer.parseInt(String.valueOf(dateDiff[0][0]))+1 ; }
			 * boolean result = isZeroBalApplicable(leaveApplication
			 * .getLevCode(), leaveApplication);// checking for zero balance
			 * applicable according to leave policy if (result) { total =
			 * Integer.parseInt(String.valueOf(dateDiff[0][0])); }// end of if
			 */
		} catch (Exception e) {
			logger.error("Exception in calculate--------- " + e);
		}
		return total;
	}// end of calculate

	/**
	 * Method Name: LeaveApplication() 
	 * @purpose: THIS METHOD IS USED FOR DISPLAYING EMPLOYEE INFORMATRION
	 * @param empId
	 * @param leaveApplication
	 * @return LeaveApplication
	 */
	public LeaveApplication getEmployeeDetails(String empId,
			LeaveApplication leaveApplication) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = empId;
			final Object[][] data = getSqlModel().getSingleResult(getQuery(11),
					beanObj);
			for (int i = 0; i < data.length; i++) {
				leaveApplication.setTokenNo(String.valueOf(data[i][0]));// employee
				// token
				leaveApplication.setEmpName(checkNull(String
						.valueOf(data[i][1])));// employee name
				leaveApplication
						.setCenter(checkNull(String.valueOf(data[i][2])));// branch
				leaveApplication.setDepartment(checkNull(String
						.valueOf(data[i][3])));// department
				leaveApplication.setEmpCode(checkNull(String
						.valueOf(data[i][4])));// employee id
				leaveApplication.setEmpGender(checkNull(String
						.valueOf(data[i][6])));// employee
			}// end of for loop
		} catch (Exception e) {
			logger.info("Exception in getEmployeeDetails------------" + e);
		}
		return leaveApplication;
	}// end of getEmployeeDetails

	/**
	 * Method Name: showEmp() 
	 * @purpose: THIS METHOD IS USED FOR DISPLAYING EMPLOYEE INFORMARTION
	 * @param leaveApp
	 * @return String
	 */
	public String showEmp(LeaveApplication leaveApp) {
		try {
			final Object[] emp = new Object[1];
			emp[0] = leaveApp.getEmpCode();
			final Object[][] empdata = getSqlModel().getSingleResult(
					getQuery(9), emp);
			leaveApp.setTokenNo(String.valueOf(empdata[0][0]));// employee
			// token
			leaveApp.setEmpName(String.valueOf(empdata[0][1]));// employee name
			leaveApp.setCenter(String.valueOf(empdata[0][2]));// branch
			leaveApp.setDepartment(String.valueOf(empdata[0][3]));// department
			leaveApp.setEmpCode(String.valueOf(empdata[0][4]));// employee id
		} catch (Exception e) {
			logger.error("Exception in showEmp--------- " + e);
		}
		return "success";
	}// end of showEmp

	/**
	 * Method Name:getRecord()
	 * @purpose : THIS METHOD IS USED FOR LEAVE APPROVAL
	 * @param leaveApp
	 */

	public void getRecord(LeaveApplication leaveApp) {
		try {
			final Object[] param = new Object[1];
			/*
			 * getrecord displaying the leave details after selecting the
			 * application from f9 action
			 */
			param[0] = leaveApp.getLeaveCode();
			/* getquery(12) for selecting the status ,details,level*/
			final Object applData[][] = getSqlModel().getSingleResult(
					getQuery(10), param);
			/* setting the status of the application 1)if level is 1 & status
			 * will be set to value coming from database*/
			if (applData != null && applData.length > 0) {
				if (String.valueOf(applData[0][1]).equals("1")) {

					leaveApp.setStatus(String.valueOf(applData[0][0]));
					leaveApp.setHiddenStatus(String.valueOf(applData[0][0]));
				}// end of if
				/**
				 * 2)if level is higher than 1 & application is pending status
				 * will be set to "forwarded"
				 */

				else if (!(String.valueOf(applData[0][1]).equals("1"))
						&& String.valueOf(applData[0][0]).equals("P")) {
					leaveApp.setStatus("F");
					leaveApp.setHiddenStatus("F");
				}// end of else if

				else if ((!(String.valueOf(applData[0][1]).equals("1")))
						&& String.valueOf(applData[0][0]).equals("C")) {
					leaveApp.setStatus("C");
					leaveApp.setHiddenStatus("C");
				}// end of else if
				/* 3)if level is higher than 1 & application is not pending
				 * status will be set to value coming from database 
				 */
				else {
					leaveApp.setStatus(String.valueOf(applData[0][0]));
					leaveApp.setHiddenStatus(String.valueOf(applData[0][0]));
				}// end of else
				leaveApp.setLevel(String.valueOf(applData[0][1]));
			}
		} catch (Exception e) {
			logger.error("Exception in getRecord--------- " + e);
		}

	}// end of getRecord

	/**
	 * Method Name:deleteData()
	 * @purpose : THIS METHOD IS USED FOR REMOVING LEAVE
	 * @param leaveApplication
	 * @param documentPath
	 * @param document
	 * @param halfDayTypeToDate
	 * @param halfDayType
	 * @param iteratorUnAdjustPenaltyDays
	 * @param iteratorAdjustPenaltyDays
	 * @param iteratorPenaltyDays
	 * @param expectedDeliveryDate
	 * @param isHalfDayLeaveItt
	 * @param srno
	 * @param leavetype
	 * @param frmDate
	 * @param toDate
	 * @param request
	 * @param leaveDays
	 * @param leaveCode
	 * @param availBalance
	 * @param closeBalance
	 * @param onholdhidden
	 * @return boolean
	 */
	public boolean deleteData(final LeaveApplication leaveApplication,
			final String[] srNo, final String[] leaveType,
			final String[] frmDate, String[] toDate,
			HttpServletRequest request, String[] leaveDays, String[] leaveCode,
			String[] availBalance, String[] closeBalance,
			String[] onholdhidden, String[] halfDayType,
			String[] halfDayTypeToDate, String[] document,
			String[] documentPath, String[] iteratorPenaltyDays,
			String[] iteratorAdjustPenaltyDays,
			String[] iteratorUnAdjustPenaltyDays,
			String[] expectedDeliveryDate, String[] isHalfDayLeaveItt) {

		try {
			final String policyCode = getLeavePolicyCode(leaveApplication
					.getEmpCode());
			final ArrayList<Object> att = new ArrayList<Object>();
			String removeLeave = "";
			String removeLeaveDays = "";
			String closeBal = "";
			String onhold = "";
			String removePenaltyDays = "";
			double unadjustPenaltyDays = 0.0d;
			double adjustedPenaltyDays = 0.0d;

			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					int trial = Integer.parseInt(leaveApplication
							.getCheckEdit()) - 1;
					if (Integer.parseInt(leaveApplication.getCheckEdit()) - 1 == i) {
						removeLeave = leaveCode[i];
						removeLeaveDays = leaveDays[i];
						removePenaltyDays = iteratorPenaltyDays[i];
						unadjustPenaltyDays = Double.parseDouble(String
								.valueOf(iteratorUnAdjustPenaltyDays[i]));
						adjustedPenaltyDays = Double.parseDouble(String
								.valueOf(iteratorAdjustPenaltyDays[i]));
						if (unadjustPenaltyDays > 0) {
							closeBal = String
									.valueOf(Double
											.parseDouble(closeBalance[i])
											+ Double
													.parseDouble(String
															.valueOf(iteratorAdjustPenaltyDays[i]))
											+ Double.parseDouble(String
													.valueOf(leaveDays[i])));
							onhold = String.valueOf(Double
									.parseDouble(onholdhidden[i])
									- adjustedPenaltyDays);
						} else {

							closeBal = String.valueOf(Double
									.parseDouble(closeBalance[i])
									+ Double.parseDouble(removeLeaveDays)
									+ Double.parseDouble(removePenaltyDays));
							onhold = String
									.valueOf(Double
											.parseDouble(onholdhidden[i])
											- (Double
													.parseDouble(removeLeaveDays) + Double
													.parseDouble(removePenaltyDays)));
						}
					}// end of if

				}// end of for
				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication levApp = new LeaveApplication();
					levApp.setSlevType(leaveType[i]);// leave type
					levApp.setSleaveFromDtl(frmDate[i]);// leave from date
					levApp.setSleaveToDtl(toDate[i]);// leave to date
					levApp.setSlevClosingBalance(leaveDays[i]);// leave dyas
					levApp.setSlevCode(leaveCode[i]);// leave code
					levApp.setSrNo(srNo[i]);// sr no
					levApp.setAvailBalance(availBalance[i]);// available balance
					if (leaveCode[i].equals(removeLeave)) {
						levApp.setCloseBalance(closeBal);// closing balance
						levApp.setOnholdhidden(onhold);// on hold balance
					}// end of if
					else {
						levApp.setCloseBalance(closeBalance[i]);
						levApp.setOnholdhidden(onholdhidden[i]);
					}// end of else
					levApp.setHalfDayType(halfDayType[i]);
					levApp.setHalfDayTypeToDate(halfDayTypeToDate[i]);
					levApp.setUploadDoc(document[i]);
					levApp.setUploadDocPath(documentPath[i]);
					levApp.setIteratorPenaltyDays(iteratorPenaltyDays[i]);
					levApp
							.setIteratorAdjustPenaltyDays(iteratorAdjustPenaltyDays[i]);
					levApp
							.setIteratorUnAdjustPenaltyDays(iteratorUnAdjustPenaltyDays[i]);

					levApp.setExpectedDeliveryDate(expectedDeliveryDate[i]);

					levApp.setIsHalfDayLeaveItt(isHalfDayLeaveItt[i]);

					if (document != null && document.length > 0) {

						final String[] splitDocumentPath = documentPath[i]
								.split(",");
						final String[] splitDocument = document[i].split(",");
						final ArrayList list = new ArrayList();
						for (int j = 0; j < splitDocument.length; j++) {
							LeaveApplication bean = new LeaveApplication();
							bean.setUploadDocPath(splitDocumentPath[j]);
							bean.setUploadDoc(splitDocument[j]);
							list.add(bean);
						}
						levApp.setIttUploadList(list);
					}
					att.add(levApp);
				}// end of for loop
				att
						.remove(Integer.parseInt(leaveApplication
								.getCheckEdit()) - 1);
			}// end of if
			leaveApplication.setAtt(att);
			if (leaveApplication.getIsAddFlag().equals("false")) {

				/**
				 * deleting record from hrms_leave_balance_temp
				 */
				final String deletTemp = "DELETE FROM HRMS_LEAVE_BALANCE_TEMP WHERE EMP_ID ="
						+ leaveApplication.getEmpCode();
				getSqlModel().singleExecute(deletTemp);
				/**
				 * selecting record from original hrms_leave_balance
				 */
				final String orgBalance = "SELECT EMP_ID,LEAVE_CODE,NVL(LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0) FROM HRMS_LEAVE_BALANCE WHERE EMP_ID ="
						+ leaveApplication.getEmpCode();
				final Object[][] orgBalanceObj = getSqlModel().getSingleResult(
						orgBalance);

				for (int i = 0; i < orgBalanceObj.length; i++) {
					if (String.valueOf(orgBalanceObj[i][1]).equals(removeLeave)) {
						final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
								+ " WHERE LEAVE_POLICY_CODE="
								+ policyCode
								+ " AND LEAVE_CODE=" + removeLeave;
						final Object[][] zeroBalance = getSqlModel()
								.getSingleResult(zeroBlncQuery);
						double totalDays = 0.0d;
						if (!String.valueOf(zeroBalance[0][0]).equals("Y")) {
							if (unadjustPenaltyDays > 0) {
								totalDays = Double.parseDouble(String
										.valueOf(adjustedPenaltyDays))
										+ Double.parseDouble(String
												.valueOf(removeLeaveDays));
							} else {
								totalDays = Double.parseDouble(String
										.valueOf(removeLeaveDays))
										+ Double.parseDouble(String
												.valueOf(removePenaltyDays));
							}

							orgBalanceObj[i][2] = Double.parseDouble(String
									.valueOf(orgBalanceObj[i][2]))
									+ totalDays;
							orgBalanceObj[i][3] = Double.parseDouble(String
									.valueOf(orgBalanceObj[i][3]))
									- totalDays;
						}// end of nested if
						else {
							orgBalanceObj[i][2] = orgBalanceObj[i][2];
							if (unadjustPenaltyDays > 0) {
								totalDays = Double.parseDouble(String
										.valueOf(adjustedPenaltyDays))
										+ Double.parseDouble(String
												.valueOf(removeLeaveDays));
							} else {
								totalDays = Double.parseDouble(String
										.valueOf(removeLeaveDays))
										+ Double.parseDouble(String
												.valueOf(removePenaltyDays));
							}
							orgBalanceObj[i][3] = Double.parseDouble(String
									.valueOf(orgBalanceObj[i][3]))
									- totalDays;
						}// end of else
					}// end of if
				}// end of for loop
				final String insertTemp = " INSERT INTO HRMS_LEAVE_BALANCE_TEMP (EMP_ID,LEAVE_CODE,LEAVE_CLOSING_BALANCE,LEAVE_ONHOLD) "
						+ " VALUES (?,?,?,?)";
				getSqlModel().singleExecute(insertTemp, orgBalanceObj);
			} // end of if

			else {
				final Object[][] updateTempObj = new Object[1][4];
				/**
				 * update hrms_leave_balance_temp
				 */
				final String updateTemp = " UPDATE HRMS_LEAVE_BALANCE_TEMP SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE+? ,LEAVE_ONHOLD=LEAVE_ONHOLD -? "
						+ " WHERE EMP_ID = ? AND LEAVE_CODE = ? ";

				final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_CODE=" + removeLeave;
				final Object[][] zeroBalance = getSqlModel().getSingleResult(
						zeroBlncQuery);
				double totalDays = 0.0d;
				if (!String.valueOf(zeroBalance[0][0]).equals("Y")) {
					if (unadjustPenaltyDays > 0) {
						totalDays = Double.parseDouble(String
								.valueOf(adjustedPenaltyDays))
								+ Double.parseDouble(String
										.valueOf(removeLeaveDays));
					} else {
						totalDays = Double.parseDouble(String
								.valueOf(removeLeaveDays))
								+ Double.parseDouble(String
										.valueOf(removePenaltyDays));
					}

					updateTempObj[0][0] = totalDays;
				} // end of if
				else {
					updateTempObj[0][0] = "0";
					if (unadjustPenaltyDays > 0) {
						totalDays = Double.parseDouble(String
								.valueOf(adjustedPenaltyDays))
								+ Double.parseDouble(String
										.valueOf(removeLeaveDays));
					} else {
						totalDays = Double.parseDouble(String
								.valueOf(removeLeaveDays))
								+ Double.parseDouble(String
										.valueOf(removePenaltyDays));
					}
				}// end of else
				updateTempObj[0][1] = totalDays;
				updateTempObj[0][2] = leaveApplication.getEmpCode();
				updateTempObj[0][3] = removeLeave;
				getSqlModel().singleExecute(updateTemp, updateTempObj);
			}// end of else
			leaveApplication.setIsAddFlag("true");
		} catch (Exception e) {
			logger.error("Exception in deleteData--------- " + e);
			e.printStackTrace();
		}
		return true;
	}// end of deleteData

	/**
	 * Method Name: deleteLeaveApplication()
	 * 
	 * @purpose : THIS METHOD IS USED FOR DELETING LEAVE APPLICATION
	 * @param leaveApplication
	 * @return boolean
	 */
	public boolean deleteLeaveApplication(LeaveApplication leaveApplication) {

		boolean result = false;
		try {

			final Object[][] bean = new Object[1][1];
			bean[0][0] = leaveApplication.getLeaveCode();
			result = updateBalanceTable(leaveApplication);
			if (result) {
				result = getSqlModel().singleExecute(getQuery(7), bean);
				result = getSqlModel().singleExecute(getQuery(20), bean);
				result = getSqlModel().singleExecute(getQuery(21), bean);

				final String updateQuery = "  UPDATE HRMS_EXTRAWORK_APPL_DTL SET EXTRAWORK_IS_LEAVE_APPLIED='N',EXTRAWORK_LEAVE_APPL_CODE='', "
						+ " EXTRAWORK_LEAVE_FROMDATE='',EXTRAWORK_LEAVE_TODATE=''"
						+ " WHERE EXTRAWORK_LEAVE_APPL_CODE="
						+ leaveApplication.getLeaveCode();
				getSqlModel().singleExecute(updateQuery);
			}
		} catch (Exception e) {
			logger.error("Exception in deleteLeaveApplication--------- " + e);
		}
		return result;
	}// end of deleteLeaveApplication

	/**
	 * Method Name: updateBalanceTable()
	 * 
	 * @purpose : This method is used for updating leave balance table
	 * @param leave
	 * @return boolean
	 */
	public boolean updateBalanceTable(LeaveApplication leave) {

		boolean result = false;
		try {

			final String policyCode = getLeavePolicyCode(leave.getEmpCode());
			String balQuery = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE="
					+ leave.getLeaveCode()
					+ " ";
			final Object[][] balObj = getSqlModel().getSingleResult(balQuery);
			/**
			 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE
			 * APPLICABLE LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
			 */
			for (int j = 0; j < balObj.length; j++) {
				final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][3]);

				final Object[][] zeroBalance = getSqlModel().getSingleResult(
						zeroBlncQuery);
				if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
					balObj[j][0] = "0"; // leave days set to be zero

				}// end of if
				else {
					balObj[j][0] = balObj[j][0];
				}// end of else
			}// end of for loop
			if (balObj != null && balObj.length > 0) {

				balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? ,LEAVE_ONHOLD=CASE WHEN LEAVE_ONHOLD<=0 THEN 0 ELSE NVL(LEAVE_ONHOLD,0) - ? END "
						+ " WHERE EMP_ID =? AND LEAVE_CODE =?";
				/*
				 * 
				 * balQuery = " UPDATE HRMS_LEAVE_BALANCE SET
				 * LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE +
				 * ?,LEAVE_ONHOLD=NVL(LEAVE_ONHOLD,0)-? " + " WHERE EMP_ID =?
				 * AND LEAVE_CODE =?";
				 */
				result = getSqlModel().singleExecute(balQuery, balObj);

			}// end of if
		} catch (Exception e) {
			logger.error("Exception in updateBalanceTable--------- " + e);
		}
		return result;
	}// end of updateBalanceTable

	/**
	 * Method Name: getLeaveAllDetails()
	 * 
	 * @purpose : THIS METHOD IS USED FOR GETTING PREFIX,SUFFIX,COMMENTS,AND
	 *          REASON
	 * @param leaveApplication
	 */
	public void getLeaveAllDetails(LeaveApplication leaveApplication) {
		try {
			final Object[] bean = new Object[1];
			bean[0] = leaveApplication.getLeaveCode();
			final Object[][] values = getSqlModel().getSingleResult(
					getQuery(3), bean);
			if (values != null && values.length > 0) {
				leaveApplication.setComments(checkNull(String
						.valueOf(values[0][0])));// comments

				leaveApplication.setMedicalCert(checkNull(String
						.valueOf(values[0][1])));// reason
				leaveApplication.setApplicationDate(checkNull(String
						.valueOf(values[0][3])));

				leaveApplication.setLeaveReasonName(checkNull(String
						.valueOf(values[0][4])));// reason code

				leaveApplication.setLeaveReasonCode(checkNull(String
						.valueOf(values[0][5])));// reason name
			}
			getLeaveDtlHistory(leaveApplication);
		} catch (Exception e) {
			logger.error("Exception in getLeaveAllDetails--------- " + e);
			e.printStackTrace();
		}
	}// end of getLeaveAllDetails

	/**
	 * Method Name: getLeaveDtlHistory()
	 * 
	 * @purpose: THIS METHOD IS USED FOR GETTING LEAVE DETAILS
	 * @param leaveApplication
	 */
	public void getLeaveDtlHistory(LeaveApplication leaveApplication) {

		final String policyCode = getLeavePolicyCode(leaveApplication
				.getEmpCode());
		final ArrayList<Object> att = new ArrayList<Object>();
		try {
			final Object levObj[] = new Object[3];
			levObj[0] = leaveApplication.getEmpCode();// employee id
			levObj[1] = leaveApplication.getLeaveCode();// application code
			levObj[2] = leaveApplication.getEmpCode();
			final Object[][] values = getSqlModel().getSingleResult(
					getQuery(8), levObj);
			for (int i = 0; i < values.length; i++) {
				LeaveApplication levApp = new LeaveApplication();
				levApp.setSlevCode(String.valueOf(values[i][0]));// leave
				// Code
				levApp.setSlevType(String.valueOf(values[i][1]));// leave
				// type
				levApp.setSleaveFromDtl(String.valueOf(values[i][2]));// leave
				// from
				// Date
				levApp.setSleaveToDtl(String.valueOf(values[i][3]));// leave to
				// Date
				levApp.setSlevClosingBalance(String.valueOf(values[i][4])
						.equals(".5") ? "0.5" : String.valueOf(values[i][4]));// leave
				// days
				levApp.setCloseBalance(String.valueOf(values[i][6]));
				final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_CODE=" + values[i][0];
				final Object[][] zeroBalance = getSqlModel().getSingleResult(
						zeroBlncQuery);

				if (zeroBalance != null && zeroBalance.length > 0) {
					if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
						levApp.setAvailBalance(String.valueOf(values[i][6]));
					}// end of if
					else {
						/**
						 * available balance = leave closing balance + on hold
						 * balance
						 */
						levApp.setAvailBalance(String.valueOf(values[i][5]));
					} // end of else
				}

				levApp.setOnholdhidden(String.valueOf(values[i][7]));
				levApp.setHalfDayType(checkNull(String.valueOf(values[i][8])));
				levApp.setHalfDayTypeToDate(checkNull(String
						.valueOf(values[i][9])));
				levApp
						.setUploadDocPath(checkNull(String
								.valueOf(values[i][10])));
				levApp.setUploadDoc(checkNull(String.valueOf(values[i][11])));
				levApp.setIteratorPenaltyDays(checkNull(String
						.valueOf(values[i][12])));
				levApp.setIteratorAdjustPenaltyDays(checkNull(String
						.valueOf(values[i][13])));
				levApp.setIteratorUnAdjustPenaltyDays(checkNull(String
						.valueOf(values[i][14])));
				levApp.setExpectedDeliveryDate(checkNull(String
						.valueOf(values[i][15])));
				levApp.setIsHalfDayLeaveItt(checkNull(String
						.valueOf(values[i][16])));
				if (String.valueOf(values[i][10]) != null
						&& !String.valueOf(values[i][10]).equals("null")) {
					String[] splitDocumentPath = levApp.getUploadDocPath()
							.split(",");
					final String[] splitDocument = levApp.getUploadDoc().split(
							",");
					final ArrayList list = new ArrayList();
					for (int j = 0; j < splitDocument.length; j++) {
						LeaveApplication bean = new LeaveApplication();
						bean.setUploadDocPath(splitDocumentPath[j]);
						bean.setUploadDoc(splitDocument[j]);
						list.add(bean);
					}
					levApp.setIttUploadList(list);
				}
				att.add(levApp);
			}// end of for loop
			leaveApplication.setAtt(att);
		}// end of try
		catch (Exception e) {
			logger.error("Exception in getLeaveDtlHistory-----------" + e);
			e.printStackTrace();
		}// end of catch
	}// end of getLeaveDtlHistory

	/**
	 * @modified by
	 * @author Prajakta.Bhandare Method Name: getLeaveHistory()
	 * @purpose : THIS METHOD IS USED FOR GETTING LEAVE HISTORY OF EMPLOYEE
	 * @param leaveApplication
	 */
	public void getLeaveHistory(LeaveApplication leaveApplication) {
		final ArrayList<Object> att = new ArrayList<Object>();
		try {
			String query = "";
			final Object levObj[] = new Object[1];
			levObj[0] = leaveApplication.getEmpCode();// employee id

			if (leaveApplication.getLevstatus().equals("A")) {

				query = " SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),	"
						+ "  DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_DAYS_HRS,'HH24:MI')||'  (HH24:MI)',LEAVE_DAYS),DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','P','Pending','B','Sent Back','N','Cancelled','R','Rejected','A','Approved','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected') FROM HRMS_LEAVE_DTL 	"
						+ "  INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
						+ "  INNER JOIN HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE)"
						+ "   WHERE  HRMS_LEAVE_DTL.EMP_ID=?  ORDER BY  HRMS_LEAVE_DTL.LEAVE_FROM_DATE DESC ";

			} // end of if
			else if (leaveApplication.getLevstatus().equals("CM")) {
				query = "SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),"
						+ "	 DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_DAYS_HRS,'HH24:MI')||'  (HH24:MI)',LEAVE_DAYS) ,DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','P','Pending','B','Sent Back','N','Cancelled','R','Rejected','A','Approved','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected')FROM HRMS_LEAVE_DTL 	"
						+ "		 INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
						+ "  INNER JOIN HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE)"
						+ "		  WHERE  HRMS_LEAVE_DTL.EMP_ID=? "
						+ "		  AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'MM')=TO_CHAR(SYSDATE,'MM')"
						+ " AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY') "
						+ "		    ORDER BY  HRMS_LEAVE_DTL.LEAVE_FROM_DATE DESC  ";
			} // end of else if
			else if (leaveApplication.getLevstatus().equals("PM")) {
				query = "SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),"
						+ "	 DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_DAYS_HRS,'HH24:MI')||'  (HH24:MI)',LEAVE_DAYS),DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','P','Pending','B','Sent Back','N','Cancelled','R','Rejected','A','Approved','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected') FROM HRMS_LEAVE_DTL 	"
						+ "		 INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
						+ "  INNER JOIN HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE)"
						+ "		  WHERE  HRMS_LEAVE_DTL.EMP_ID=? "
						+ "		  AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'MM')=TO_CHAR(SYSDATE,'MM')-1		"
						+ " AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY') "
						+ "		   ORDER BY  HRMS_LEAVE_DTL.LEAVE_FROM_DATE DESC ";

			} // end of else if
			else if (leaveApplication.getLevstatus().equals("CY")) {
				query = "SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),"
						+ "	 DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_DAYS_HRS,'HH24:MI')||'  (HH24:MI)',LEAVE_DAYS),DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','P','Pending','B','Sent Back','N','Cancelled','R','Rejected','A','Approved','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected') FROM HRMS_LEAVE_DTL 	"
						+ "		 INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
						+ "  INNER JOIN HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE)"
						+ "		  WHERE  HRMS_LEAVE_DTL.EMP_ID=? "
						+ "		  AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY') "
						+ "		   ORDER BY  HRMS_LEAVE_DTL.LEAVE_FROM_DATE DESC ";

			}// end of else if
			else if (leaveApplication.getLevstatus().equals("PY")) {
				query = "SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),"
						+ "	 DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_DAYS_HRS,'HH24:MI')||'  (HH24:MI)',LEAVE_DAYS),DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','P','Pending','B','Sent Back','N','Cancelled','R','Rejected','A','Approved','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected') FROM HRMS_LEAVE_DTL 	"
						+ "		 INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
						+ "  INNER JOIN HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE)"
						+ "		  WHERE  HRMS_LEAVE_DTL.EMP_ID=? "
						+ "		  AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY')-1 "
						+ "		   ORDER BY  HRMS_LEAVE_DTL.LEAVE_FROM_DATE DESC ";
			}// end of else if
			else {
				query = "SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),"
						+ "	 DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY:HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_DAYS_HRS,'HH24:MI')||'  (HH24:MI)',LEAVE_DAYS),DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','P','Pending','B','Sent Back','N','Cancelled','R','Rejected','A','Approved','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected') FROM HRMS_LEAVE_DTL 	"
						+ "		 INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
						+ "  INNER JOIN HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE)"
						+ "		  WHERE  HRMS_LEAVE_DTL.EMP_ID=? "
						+ "		  AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'MM')=TO_CHAR(SYSDATE,'MM') "
						+ "  AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY') "
						+ "		  ORDER BY  HRMS_LEAVE_DTL.LEAVE_FROM_DATE DESC ";

			}// end of else
			final Object[][] values = getSqlModel().getSingleResult(query,
					levObj);
			for (int i = 0; i < values.length; i++) {
				LeaveApplication levApp = new LeaveApplication();
				levApp.setSlevCode(String.valueOf(values[i][0]));// leave
				// code
				levApp.setSlevType(String.valueOf(values[i][1]));// leave
				// type
				levApp.setSleaveFromDtl(String.valueOf(values[i][2]));// leave
				// from
				// date
				levApp.setSleaveToDtl(String.valueOf(values[i][3]));// leave to
				// date
				levApp.setSlevClosingBalance(String.valueOf(values[i][4])
						.equals(".5") ? "0.5" : String.valueOf(values[i][4]));// leave
				// days
				levApp.setLeavestatus(String.valueOf(values[i][5]));// leave
				// status
				att.add(levApp);
			}
			leaveApplication.setAtt(att);
		} // end of try
		catch (Exception e) {
			logger.error("Exception in getLeaveHistory------------------" + e);
		}
	}// end of getLeaveHistory

	/**
	 * THIS METHOD IS USED FOR APPROVER DETAILS
	 * 
	 * @param leaveApplication
	 */
	public boolean setApproverDetails(LeaveApplication leaveApplication) {
		boolean isRecordInPath = false;
		try {
			final String approverQuery = "  SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '), "
					+ " TO_CHAR(HRMS_LEAVE_PATH.LEAVE_ASSESS_DATE,'DD-MM-YYYY'),HRMS_LEAVE_PATH.LEAVE_COMMENTS "
					+ "  FROM HRMS_LEAVE_PATH  "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_PATH.LEAVE_ASSESED_BY= HRMS_EMP_OFFC.EMP_ID) "
					+ "  LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
					+ "  WHERE HRMS_LEAVE_PATH.LEAVE_APPL_CODE ="
					+ leaveApplication.getLeaveCode()
					+ " ORDER BY HRMS_LEAVE_PATH.LEAVE_ASSESS_DATE ";
			final Object[][] approverDataObj = getSqlModel().getSingleResult(
					approverQuery);
			final ArrayList<Object> apprList = new ArrayList<Object>();
			if (approverDataObj != null && approverDataObj.length != 0) {
				isRecordInPath = true;
				for (int i = 0; i < approverDataObj.length; i++) {

					LeaveApplication leaveBean = new LeaveApplication();
					leaveBean.setApprName(checkNull(String
							.valueOf(approverDataObj[i][1])));
					leaveBean.setApprDate(checkNull(String
							.valueOf(approverDataObj[i][2])));
					if (String.valueOf(approverDataObj[i][3]).equals("null")
							|| String.valueOf(approverDataObj[i][3]) == null) {
						leaveBean.setApprComments("");
					}// end of if
					else {

						leaveBean.setApprComments(checkNull(String
								.valueOf(approverDataObj[i][3])));
					}// end of else
					apprList.add(leaveBean);
				}// end of for loop

			}// end of if
			leaveApplication.setApproveList(apprList);
		} catch (Exception e) {
			logger.error("Exception in setApproverDetails--------- " + e);
		}
		return isRecordInPath;
	}// end of setApproverDetails

	/**
	 * THIS METHOD IS USED FOR GENERATING LEAVE APPLICATION REPORT
	 * 
	 * @param request
	 * @param response
	 * @param leaveApplication
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, LeaveApplication leaveApplication) {

		try {
			final String policyCode = getLeavePolicyCode(leaveApplication
					.getUserEmpId());
			boolean isPenalty = setPenaltyFlag(policyCode);
			final String title = "\n Leave Application Report\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", title);
			rg.addFormatedText(title, 6, 0, 1, 0);
			final String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'DD-MM-YYYY'),HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,DECODE(HRMS_LEAVE_HDR.LEAVE_STATUS,'D','Draft','B','Sent Back','P','Pending','R','Rejected','A','Approved','N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected')  "
					+ "  FROM HRMS_LEAVE_HDR "
					+ "  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_HDR.EMP_ID "
					+ "  INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "  INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ " WHERE HRMS_LEAVE_HDR.LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode();
			final Object[][] Data = getSqlModel().getSingleResult(query);
			if (Data != null && Data.length > 0) {
				final Object obj[][] = new Object[3][4];

				obj[0][0] = "Employee No   :";
				obj[0][1] = checkNull(String.valueOf(Data[0][0]));
				obj[0][2] = "Employee Name   :";
				obj[0][3] = checkNull(String.valueOf(Data[0][1]));
				obj[1][0] = "Branch   :";
				obj[1][1] = checkNull(String.valueOf(Data[0][4]));
				obj[1][2] = "Designation    :";
				obj[1][3] = checkNull(String.valueOf(Data[0][3]));
				obj[2][0] = "Application Date   :";
				obj[2][1] = checkNull(String.valueOf(Data[0][2]));
				obj[2][2] = "Status     :";
				obj[2][3] = checkNull(String.valueOf(Data[0][5]));

				rg.tableBodyNoBorder(obj, new int[] { 47, 53, 47, 53 },
						new int[] { 30, 70, 30, 70 });
			}// end of if
			final String query1 = " SELECT ROWNUM,TO_CHAR(LEAVE_NAME) , DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY : HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),"
					+ " DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY : HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_DAYS_HRS,'HH24:MI')||'  (HH24:MI)',LEAVE_DAYS) ,NVL(LEAVE_PENALTY_DAYS,0),NVL(LEAVE_PENALTY_ADJUST_DAYS,0),NVL(LEAVE_PENALTY_UNADJUST_DAYS,0) FROM HRMS_LEAVE  "
					+ " INNER JOIN HRMS_LEAVE_DTL ON(HRMS_LEAVE_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID ) "
					+ "  INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
					+ " WHERE HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE = "
					+ leaveApplication.getLeaveCode() + "  ORDER BY LEAVE_ID ";
			final Object[][] result = getSqlModel().getSingleResult(query1);
			final String query3 = " SELECT LEAVE_COMMENTS,LEAVE_REASON ,NVL(REASON_NAME,'') FROM HRMS_LEAVE_HDR "
					+ "  LEFT JOIN  HRMS_LEAVE_REASON ON(HRMS_LEAVE_REASON.REASON_CODE =HRMS_LEAVE_HDR.LEAVE_REASON_CODE) "
					+ "   WHERE LEAVE_APPL_CODE ="
					+ leaveApplication.getLeaveCode();
			final Object[][] result1 = getSqlModel().getSingleResult(query3);

			if (isPenalty) {
				final String[] header = { "Sr No", "Leave Type", "From Date",
						"To Date", "Leave Days", "Penalty Days",
						"Penalty Adjusted Days", "Penalty Unadjusted Days" };
				int appCell[] = { 5, 30, 15, 20, 30, 20, 20, 20 };
				int apprAlign[] = { 0, 0, 1, 1, 1, 1, 1, 1 };
				rg.addFormatedText("Leave Details : \n ", 6, 0, 0, 0);
				rg.tableBody(header, result, appCell, apprAlign);
				rg.addText("\n", 0, 0, 0);
			} else {
				final String[] header = { "Sr No", "Leave Type", "From Date",
						"To Date", "Leave Days" };
				int appCell[] = { 5, 30, 15, 20, 30 };
				int apprAlign[] = { 0, 0, 1, 1, 1 };
				rg.addFormatedText("Leave Details : \n ", 6, 0, 0, 0);
				rg.tableBody(header, result, appCell, apprAlign);
				rg.addText("\n", 0, 0, 0);
			}

			if (result1 != null && result1.length > 0) {
				final Object levObj[][] = new Object[2][4];
				String reason = "";
				/*
				 * levObj[0][0] = "Prefix :"; levObj[0][1] =
				 * checkNull(String.valueOf(result1[0][2])); levObj[0][2] =
				 * "Suffix :"; levObj[0][3] =
				 * checkNull(String.valueOf(result1[0][3]));
				 */
				if (!String.valueOf(result1[0][1]).equals("null")
						|| String.valueOf(result1[0][1]).equals("")
						|| String.valueOf(result1[0][1]) == null) {
					reason = checkNull(String.valueOf(result1[0][1]));

				} else {
					reason = checkNull(String.valueOf(result1[0][2]));

				}

				levObj[0][0] = "Reasons   :";
				levObj[0][1] = String.valueOf(reason);
				levObj[0][2] = "";
				levObj[0][3] = "";
				levObj[1][0] = "Applicants Comments" + ":";
				levObj[1][1] = checkNull(String.valueOf(result1[0][0]));
				levObj[1][2] = "";
				levObj[1][3] = "";
				rg.tableBodyNoBorder(levObj, new int[] { 47, 53, 47, 53 },
						new int[] { 30, 70, 30, 70 });

			}// end of if
			final String approver = " SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(LEAVE_ASSESS_DATE,'DD-MM-YYYY'), "
					+ "	 LEAVE_COMMENTS ABC ,DECODE(LEAVE_STATUS,'D','Draft','B','Sent Back','P','Pending','A','Approved','R','Rejected','N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected')"
					+ "		 FROM HRMS_LEAVE_PATH "
					+ "		INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_PATH.LEAVE_ASSESED_BY= HRMS_EMP_OFFC.EMP_ID) "
					+ "	    WHERE LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode()
					+ " UNION "
					+ "		 SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),'',"
					+ "	   CAST(''AS NVARCHAR2(100)) A,  DECODE(LEAVE_STATUS,'D','Draft','B','Sent Back','P','Pending','A','Approved','R','Rejected','N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected') "
					+ "		FROM HRMS_LEAVE_HDR "
					+ "		INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_HDR.APPROVED_BY= HRMS_EMP_OFFC.EMP_ID) "
					+ "	  WHERE LEAVE_STATUS='P' AND HRMS_LEAVE_HDR.LEAVE_APPL_CODE ="
					+ leaveApplication.getLeaveCode();
			final Object[][] approverData = getSqlModel().getSingleResult(
					approver);
			final Object[][] approvalTable = new Object[approverData.length][7];
			for (int i = 0; i < approverData.length; i++) {
				approvalTable[i][0] = String.valueOf(i + 1);
				approvalTable[i][1] = checkNull(String
						.valueOf(approverData[i][0]));
				approvalTable[i][2] = checkNull(String
						.valueOf(approverData[i][1]));
				approvalTable[i][3] = checkNull(String
						.valueOf(approverData[i][2]));
				approvalTable[i][4] = checkNull(String
						.valueOf(approverData[i][3]));
				approvalTable[i][5] = checkNull(String
						.valueOf(approverData[i][4]));
			}// end of for loop
			final String appCol[] = { "Sr. No", "Approver Id", "Approver Name",
					"Date", "Remarks", "Status" };
			int appCell1[] = { 5, 10, 25, 10, 30, 10 };
			int apprAlign1[] = { 1, 1, 0, 1, 0, 0 };
			rg.addFormatedText("Approver Details : \n ", 6, 0, 0, 0);
			rg.tableBody(appCol, approvalTable, appCell1, apprAlign1);
			rg.createReport(response);
		} catch (Exception e) {
			logger.error("Exception in getReport--------- " + e);
		}
	}// end of getReport

	/**
	 * THIS METHOD IS USED FOR REPORT
	 * 
	 * @param leaveApplication
	 */

	public void getLeaveReport(LeaveApplication leaveApplication) {
		try {
			final Object addObj[] = new Object[1];
			addObj[0] = leaveApplication.getLeaveCode();
			final Object[][] data = getSqlModel().getSingleResult(getQuery(13),
					addObj);
			int totalLeav = 0;
			final ArrayList<Object> att = new ArrayList<Object>();
			for (int i = 0; i < data.length; i++) {
				LeaveApplication bean1 = new LeaveApplication();
				bean1.setLevType(String.valueOf(data[i][1]));
				bean1.setLeaveFromDtl(String.valueOf(data[i][2]));
				bean1.setLeaveToDtl(String.valueOf(data[i][3]));
				bean1.setLevTotal(String.valueOf(data[i][4]));
				totalLeav += Double.parseDouble(String.valueOf(data[i][4]));
				att.add(bean1);
			}// end of for loop
			leaveApplication.setAtt(att);
			leaveApplication.setLeaveTotalDays(String.valueOf(totalLeav));
		} catch (Exception e) {
			logger.error("Exception in getLeaveReport--------- " + e);
		}

	}// end of getLeaveReport

	/**
	 * THIS METHOD IS USED FOR ADDING LEAVE TYPE
	 * 
	 * @param leaveApplication
	 * @param halfDayTypeToDate
	 * @param proofFileName
	 * @param proofName
	 * @param srProofNo
	 * @param documentPath
	 * @param document
	 * @param penaltyDays
	 * @param iteratorPenaltyDays
	 * @param iteratorUnAdjustPenaltyDays
	 * @param iteratorAdjustPenaltyDays
	 * @param expectedDeliveryDate
	 * @param isHalfDayLeaveItt
	 * @param srNo-------
	 *            sr no
	 * @param leaveType---leave
	 *            type
	 * @param frmDate------leave
	 *            from date
	 * @param toDate-----leave
	 *            to date
	 * @param request------request
	 * @param leaveDays---------leave
	 *            days
	 * @param leaveCode--------leave
	 *            code
	 * @param availBalance---------available
	 *            balance
	 * @param closeBalance-------closing
	 *            balance
	 * @param onholdhidden--------on
	 *            hold balance
	 * @return boolean
	 */

	public boolean addLeaveType(LeaveApplication leaveApplication,
			String[] srNo, String[] leaveType, String[] frmDate,
			String[] toDate, HttpServletRequest request, String[] leaveDays,
			String[] leaveCode, String[] availBalance, String[] closeBalance,
			String[] onholdhidden, String[] halfDayType,
			String[] halfDayTypeToDate, String[] srProofNo, String[] proofName,
			String[] proofFileName, String[] document, String[] documentPath,
			String[] iteratorPenaltyDays, String[] iteratorAdjustPenaltyDays,
			String[] iteratorUnAdjustPenaltyDays,
			String[] expectedDeliveryDate, String[] isHalfDayLeaveItt) {

		try {
			LeaveApplication levApp = new LeaveApplication();
			levApp.setSlevType(leaveApplication.getLevType());// leave type
			levApp.setSleaveFromDtl(leaveApplication.getLeaveFromDtl());// leave
			// from date
			levApp.setSleaveToDtl(leaveApplication.getLeaveToDtl());// leave to
			// date
			levApp.setSlevClosingBalance(leaveApplication.getLeaveTotalDays());// leave
			// days
			levApp.setSlevCode(leaveApplication.getLevCode());// leave code
			levApp.setAvailBalance(leaveApplication.getLevOpeningBalance());// available
			// balance
			levApp.setCloseBalance(leaveApplication.getLevClosingBalance());// closing
			// balance
			double newOnhold = Double.parseDouble(leaveApplication.getOnhold())
					+ Double.parseDouble(leaveApplication.getLeaveTotalDays());
			levApp.setOnholdhidden(String.valueOf(newOnhold));
			levApp.setIteratorPenaltyDays(leaveApplication
					.getHiddenPenaltyDays());// penalty days
			levApp.setIteratorAdjustPenaltyDays(leaveApplication
					.getHiddenAdjustPenaltyDays());
			levApp.setIteratorUnAdjustPenaltyDays(leaveApplication
					.getHiddenUnAdjustPenaltyDays());
			if (leaveApplication.getCheckMe().equals("true")) {
				levApp.setHalfDayType(leaveApplication.getHalfDayFlag());
			}
			if (leaveApplication.getCheckMeForhalfTodate().equals("true")) {
				levApp.setHalfDayTypeToDate(leaveApplication
						.getHalfDayFlagTodate());
			}

			String uploaddocument = "";
			String uploaddocumentPath = "";
			if (proofName != null && proofName.length > 0) {
				for (int i = 0; i < proofName.length; i++) {
					// uploadLinks+="<a href='#'
					// onclick='showRecord('"+proofName[i]
					// +"');'>"+proofFileName[i]+"</a>,";
					uploaddocument += proofName[i] + ",";
					uploaddocumentPath += proofFileName[i] + ",";
				}
				uploaddocument = uploaddocument.substring(0, uploaddocument
						.length() - 1);
				uploaddocumentPath = uploaddocumentPath.substring(0,
						uploaddocumentPath.length() - 1);
				// uploadLinks=uploadLinks.substring(0,uploadLinks.length()-1);
				final String[] splitDocumentPath = uploaddocumentPath
						.split(",");
				final String[] splitDocument = uploaddocument.split(",");
				final ArrayList list = new ArrayList();
				if (splitDocument != null && splitDocument.length > 0) {
					for (int j = 0; j < splitDocument.length; j++) {
						LeaveApplication bean = new LeaveApplication();
						bean.setUploadDocPath(splitDocumentPath[j]);
						bean.setUploadDoc(splitDocument[j]);
						list.add(bean);
					}
					levApp.setIttUploadList(list);
				}

			}

			levApp.setUploadDoc(uploaddocument);
			levApp.setUploadDocPath(uploaddocumentPath);
			levApp.setExpectedDeliveryDate(leaveApplication.getDeliveryDate());// expected
			// delivery
			// date
			// levApp.setIteratorProof(uploadLinks);

			levApp.setIsHalfDayLeaveItt(leaveApplication.getIsHalfDayLeave());

			final ArrayList<LeaveApplication> leaveList = displayNewValue(srNo,
					leaveType, frmDate, toDate, leaveDays, leaveCode,
					availBalance, closeBalance, onholdhidden, leaveApplication
							.getLevClosingBalance(), leaveApplication
							.getLevCode(), String.valueOf(newOnhold),
					leaveApplication, halfDayType, halfDayTypeToDate, document,
					documentPath, iteratorPenaltyDays,
					iteratorAdjustPenaltyDays, iteratorUnAdjustPenaltyDays,
					expectedDeliveryDate, isHalfDayLeaveItt);
			levApp.setSrNo(String.valueOf(leaveList.size() + 1));// sr no

			leaveList.add(levApp);
			// leaveList.add(uploadLinks);
			leaveApplication.setAtt(leaveList);
			if (leaveApplication.getIsAddFlag().equals("false")) {
				final String deletTemp = "DELETE FROM HRMS_LEAVE_BALANCE_TEMP WHERE EMP_ID ="
						+ leaveApplication.getEmpCode();
				getSqlModel().singleExecute(deletTemp);
				final String orgBalance = "SELECT EMP_ID,LEAVE_CODE,NVL(LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0) FROM HRMS_LEAVE_BALANCE WHERE EMP_ID ="
						+ leaveApplication.getEmpCode();
				final Object[][] orgBalanceObj = getSqlModel().getSingleResult(
						orgBalance);
				if (orgBalanceObj != null && orgBalanceObj.length > 0) {
					for (int i = 0; i < orgBalanceObj.length; i++) {
						if (String.valueOf(orgBalanceObj[i][1]).equals(
								leaveApplication.getLevCode())) {
							orgBalanceObj[i][2] = leaveApplication
									.getLevClosingBalance(); // closing
							// balance

							orgBalanceObj[i][3] = Double
									.parseDouble(leaveApplication.getOnhold())
									+ Double.parseDouble(leaveApplication
											.getLeaveTotalDays()); // leave
							// applied
							// days

						}// end of if

					}// end of for loop
					final String insertTemp = " INSERT INTO HRMS_LEAVE_BALANCE_TEMP (EMP_ID,LEAVE_CODE,LEAVE_CLOSING_BALANCE,LEAVE_ONHOLD) "
							+ " VALUES (?,?,?,?)";
					getSqlModel().singleExecute(insertTemp, orgBalanceObj);
				}

			}// end of if

			else {
				final String updateTemp = " UPDATE HRMS_LEAVE_BALANCE_TEMP SET LEAVE_CLOSING_BALANCE = ? ,LEAVE_ONHOLD=? "
						+ " WHERE EMP_ID = ? AND LEAVE_CODE = ? ";
				final Object[][] updateTempObj = new Object[1][4];

				updateTempObj[0][0] = leaveApplication.getLevClosingBalance();// leave
				// closing
				// balance
				updateTempObj[0][1] = Double.parseDouble(leaveApplication
						.getOnhold())
						+ Double.parseDouble(leaveApplication
								.getLeaveTotalDays()); // leave
				// applied
				// days
				updateTempObj[0][2] = leaveApplication.getEmpCode();// employee
				// id
				updateTempObj[0][3] = leaveApplication.getLevCode();// leave
				// code
				getSqlModel().singleExecute(updateTemp, updateTempObj);

			}// end of else
			leaveApplication.setIsAddFlag("true");
		} catch (Exception e) {
			logger.error("Exception in addLeaveType--------- " + e);
		}
		return true;
	}// end of addLeaveType

	/**
	 * THIS METHOD IS USED FOR EDITING LEAVE APPLICATION
	 * 
	 * @param leaveApplication
	 * @param halfDayTypeToDate
	 * @param documentPath
	 * @param document
	 * @param penaltyDays
	 * @param iteratorPenaltyDays
	 * @param iteratorUnAdjustPenaltyDays
	 * @param iteratorAdjustPenaltyDays
	 * @param expectedDeliveryDate
	 * @param isHalfDayLeaveItt
	 * @param documentPath2
	 * @param document2
	 * @param srNo--------sr
	 *            no
	 * @param leaveType--------leave
	 *            type
	 * @param frmDate---------leave
	 *            from date
	 * @param toDate--------leave
	 *            to date
	 * @param request--------request
	 * @param leaveDays-----------leave
	 *            days
	 * @param leaveCode----------------leave
	 *            code
	 * @param availBalance------------------available
	 *            balance
	 * @param closeBalance-----------closing
	 *            balance
	 * @param onholdhidden-----------on
	 *            hold balance
	 * @return boolean
	 */

	public boolean editLeaveType(LeaveApplication leaveApplication,
			String[] srNo, String[] leaveType, String[] frmDate,
			String[] toDate, HttpServletRequest request, String[] leaveDays,
			String[] leaveCode, String[] availBalance, String[] closeBalance,
			String[] onholdhidden, String[] halfDayType,
			String[] halfDayTypeToDate, String[] proofName,
			String[] proofFileName, String[] document, String[] documentPath,
			String[] iteratorPenaltyDays, String[] iteratorAdjustPenaltyDays,
			String[] iteratorUnAdjustPenaltyDays,
			String[] expectedDeliveryDate, String[] isHalfDayLeaveItt) {

		try {
			LeaveApplication levApp = new LeaveApplication();
			/*
			 * double newonhold = Double.parseDouble(leaveApplication
			 * .getLeaveTotalDays()) -
			 * Double.parseDouble(leaveApplication.getOldLeaveDays());
			 */

			// newonhold =(leave days+adjust penalty days)-(leave days+oldadjust
			// penalty days)
			double newonhold = (Double.parseDouble(leaveApplication
					.getLeaveTotalDays()) + Double.parseDouble(leaveApplication
					.getHiddenAdjustPenaltyDays()))
					- (Double.parseDouble(leaveApplication.getOldLeaveDays()) + Double
							.parseDouble(leaveApplication
									.getOldPenaltyAdjDays()));
			levApp.setSlevType(leaveApplication.getLevType());// leave type
			levApp.setSleaveFromDtl(leaveApplication.getLeaveFromDtl());// leave
			// from date
			levApp.setSleaveToDtl(leaveApplication.getLeaveToDtl());// leave to
			// date
			levApp.setSlevClosingBalance(leaveApplication.getLeaveTotalDays());// leave
			// days
			levApp.setSlevCode(leaveApplication.getLevCode());// leave code
			levApp.setSrNo(String.valueOf(leaveApplication.getChkEdit()));// sr
			// no
			levApp.setAvailBalance(leaveApplication.getLevOpeningBalance());// available
			// balance
			levApp.setCloseBalance(leaveApplication.getLevClosingBalance());// closing
			// balance
			levApp.setIteratorPenaltyDays(leaveApplication
					.getHiddenPenaltyDays());
			levApp.setIteratorAdjustPenaltyDays(leaveApplication
					.getHiddenAdjustPenaltyDays());
			levApp.setIteratorUnAdjustPenaltyDays(leaveApplication
					.getHiddenUnAdjustPenaltyDays());
			if (leaveApplication.getCheckMe().equals("true")) {
				levApp.setHalfDayType(leaveApplication.getHalfDayFlag());
			}
			if (leaveApplication.getCheckMeForhalfTodate().equals("true")) {
				levApp.setHalfDayTypeToDate(leaveApplication
						.getHalfDayFlagTodate());
			}

			String uploaddocument = "";
			String uploaddocumentPath = "";
			if (proofName != null && proofName.length > 0) {
				for (int i = 0; i < proofName.length; i++) {
					// uploadLinks+="<a href='#'
					// onclick='showRecord('"+proofName[i]
					// +"');'>"+proofFileName[i]+"</a>,";
					uploaddocument += proofName[i] + ",";
					uploaddocumentPath += proofFileName[i] + ",";
				}
				uploaddocument = uploaddocument.substring(0, uploaddocument
						.length() - 1);
				uploaddocumentPath = uploaddocumentPath.substring(0,
						uploaddocumentPath.length() - 1);
				// uploadLinks=uploadLinks.substring(0,uploadLinks.length()-1);
				final String[] splitDocumentPath = uploaddocumentPath
						.split(",");
				final String[] splitDocument = uploaddocument.split(",");
				final ArrayList list = new ArrayList();
				if (splitDocument != null && splitDocument.length > 0) {
					for (int j = 0; j < splitDocument.length; j++) {
						LeaveApplication bean = new LeaveApplication();
						bean.setUploadDocPath(splitDocumentPath[j]);
						bean.setUploadDoc(splitDocument[j]);
						list.add(bean);
					}
					levApp.setIttUploadList(list);
				}

			}

			levApp.setUploadDoc(uploaddocument);
			levApp.setUploadDocPath(uploaddocumentPath);
			levApp.setExpectedDeliveryDate(leaveApplication.getDeliveryDate());

			levApp.setIsHalfDayLeaveItt(leaveApplication.getIsHalfDayLeave());

			boolean result = isZeroBalApplicable(leaveApplication.getLevCode(),
					leaveApplication);
			if (result) {
				levApp.setCloseBalance(leaveApplication.getLevOpeningBalance());
			}// end of if
			String tempclosebal = "";
			String temponholdbal = "";
			if (leaveApplication.getIsAddFlag().equals("false")) {
				final String deletTemp = "DELETE FROM HRMS_LEAVE_BALANCE_TEMP WHERE EMP_ID ="
						+ leaveApplication.getEmpCode();
				getSqlModel().singleExecute(deletTemp);
				final String orgBalance = "SELECT EMP_ID,LEAVE_CODE,NVL(LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0) FROM HRMS_LEAVE_BALANCE WHERE EMP_ID ="
						+ leaveApplication.getEmpCode();
				final Object[][] orgBalanceObj = getSqlModel().getSingleResult(
						orgBalance);

				for (int i = 0; i < orgBalanceObj.length; i++) {
					if (String.valueOf(orgBalanceObj[i][1]).equals(
							leaveApplication.getLevCode())) {
						final String newValue = String
								.valueOf(orgBalanceObj[i][2]);
						orgBalanceObj[i][2] = String.valueOf(Double
								.parseDouble(String
										.valueOf(orgBalanceObj[i][2]))
								- newonhold);
						// closing balance
						boolean res = isZeroBalApplicable(leaveApplication
								.getLevCode(), leaveApplication);
						if (res) {
							orgBalanceObj[i][2] = newValue;
						}// end of nested if
						tempclosebal = String.valueOf(orgBalanceObj[i][2]);
						orgBalanceObj[i][3] = Double.parseDouble(String
								.valueOf(orgBalanceObj[i][3]))
								+ newonhold;
						temponholdbal = String.valueOf(orgBalanceObj[i][3]);
					}// end of if

				}// end o for loop
				levApp.setOnholdhidden(temponholdbal);
				final ArrayList<LeaveApplication> leaveList = displayNewValue(
						srNo, leaveType, frmDate, toDate, leaveDays, leaveCode,
						availBalance, closeBalance, onholdhidden, tempclosebal,
						leaveApplication.getLevCode(), temponholdbal,
						leaveApplication, halfDayType, halfDayTypeToDate,
						document, documentPath, iteratorPenaltyDays,
						iteratorAdjustPenaltyDays, iteratorUnAdjustPenaltyDays,
						expectedDeliveryDate, isHalfDayLeaveItt);

				if (leaveApplication.getChkEdit() != null
						&& !(leaveApplication.getChkEdit().trim().equals(""))) {
					leaveList.remove(Integer.parseInt(leaveApplication
							.getChkEdit()) - 1);
					leaveList.add(Integer.parseInt(leaveApplication
							.getChkEdit()) - 1, levApp);
				}// end of if
				else {
					leaveList.add(levApp);
				}// end of else
				leaveApplication.setAtt(leaveList);
				final String insertTemp = " INSERT INTO HRMS_LEAVE_BALANCE_TEMP (EMP_ID,LEAVE_CODE,LEAVE_CLOSING_BALANCE,LEAVE_ONHOLD) "
						+ " VALUES (?,?,?,?)";
				getSqlModel().singleExecute(insertTemp, orgBalanceObj);
			} // end of if

			else {

				final String orgBalance = "SELECT EMP_ID,LEAVE_CODE,NVL(LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0) FROM HRMS_LEAVE_BALANCE_TEMP WHERE EMP_ID ="
						+ leaveApplication.getEmpCode();
				final Object[][] orgBalanceObj = getSqlModel().getSingleResult(
						orgBalance);
				for (int i = 0; i < orgBalanceObj.length; i++) {
					if (String.valueOf(orgBalanceObj[i][1]).equals(
							leaveApplication.getLevCode())) {
						orgBalanceObj[i][2] = Double.parseDouble(String
								.valueOf(orgBalanceObj[i][2]))
								- newonhold;// CLOSING BALANCE
						tempclosebal = String.valueOf(orgBalanceObj[i][2]);
						orgBalanceObj[i][3] = Double.parseDouble(String
								.valueOf(orgBalanceObj[i][3]))
								+ newonhold;
						temponholdbal = String.valueOf(orgBalanceObj[i][3]);
					}// end of if

				}// end of for loop
				levApp.setOnholdhidden(temponholdbal);
				final ArrayList<LeaveApplication> leaveList = displayNewValue(
						srNo, leaveType, frmDate, toDate, leaveDays, leaveCode,
						availBalance, closeBalance, onholdhidden, tempclosebal,
						leaveApplication.getLevCode(), temponholdbal,
						leaveApplication, halfDayType, halfDayTypeToDate,
						document, documentPath, iteratorPenaltyDays,
						iteratorAdjustPenaltyDays, iteratorUnAdjustPenaltyDays,
						expectedDeliveryDate, isHalfDayLeaveItt);
				int data = Integer.parseInt(leaveApplication.getChkEdit()) - 1;
				if (leaveApplication.getChkEdit() != null
						&& !(leaveApplication.getChkEdit().trim().equals(""))) {
					leaveList.remove(Integer.parseInt(leaveApplication
							.getChkEdit()) - 1);
					leaveList.add(Integer.parseInt(leaveApplication
							.getChkEdit()) - 1, levApp);
				}// end of if
				else {
					leaveList.add(levApp);
				}// end of else
				leaveApplication.setAtt(leaveList);
				final String updateTemp = " UPDATE HRMS_LEAVE_BALANCE_TEMP SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE -? ,LEAVE_ONHOLD=LEAVE_ONHOLD+? "
						+ " WHERE EMP_ID = ? AND LEAVE_CODE = ? ";
				final Object[][] updateTempObj = new Object[1][4];
				updateTempObj[0][0] = newonhold;// leave closing balance
				updateTempObj[0][1] = newonhold;
				boolean res = isZeroBalApplicable(
						leaveApplication.getLevCode(), leaveApplication);
				if (res) {
					updateTempObj[0][0] = "0";
				}// end of if
				updateTempObj[0][2] = leaveApplication.getEmpCode();// employee
				// id
				updateTempObj[0][3] = leaveApplication.getLevCode();// leave
				// code
				getSqlModel().singleExecute(updateTemp, updateTempObj);

			}// end of else
			leaveApplication.setIsAddFlag("true");
		} catch (Exception e) {
			logger.error("Exception in editLeaveType--------- " + e);
		}
		return true;
	}// end of editLeaveType

	/**
	 * THIS METHOD IS USED FOR DISPLAYING LEAVE LIST
	 * 
	 * @param halfDayType
	 * @param halfDayTypeToDate
	 * @param documentPath
	 * @param document
	 * @param iteratorPenaltyDays
	 * @param iteratorUnAdjustPenaltyDays
	 * @param iteratorAdjustPenaltyDays
	 * @param expectedDeliveryDate
	 * @param isHalfDayLeaveItt
	 * 
	 * @param srNo
	 * @param leaveType
	 * @param frmDate
	 * @param toDate
	 * @param leaveDays
	 * @param leaveCode
	 * @param availBalance
	 * @param closeBalance
	 * @param onholdhidden
	 * @return ArrayList
	 */
	public ArrayList<LeaveApplication> displayIteratorValue(String[] srNo,
			String[] leaveType, String[] frmDate, String[] toDate,
			String[] leaveDays, String[] leaveCode, String[] availBalance,
			String[] closeBalance, String[] onholdhidden, String[] halfDayType,
			String[] halfDayTypeToDate, String[] document,
			String[] documentPath, String[] iteratorPenaltyDays,
			String[] iteratorAdjustPenaltyDays,
			String[] iteratorUnAdjustPenaltyDays,
			String[] expectedDeliveryDate, String[] isHalfDayLeaveItt) {
		ArrayList<LeaveApplication> leaveList = null;
		try {
			leaveList = new ArrayList<LeaveApplication>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication levApp = new LeaveApplication();
					levApp.setSlevType(leaveType[i]); // leave name
					levApp.setSleaveFromDtl(frmDate[i]);// leave from date
					levApp.setSleaveToDtl(toDate[i]);// leave to date
					levApp.setSlevClosingBalance(leaveDays[i]);// leave days
					levApp.setSlevCode(leaveCode[i]);// leave code
					levApp.setSrNo(srNo[i]);// sr no
					levApp.setAvailBalance(availBalance[i]);// available balance
					levApp.setCloseBalance(closeBalance[i]);// closing balance
					levApp.setOnholdhidden(onholdhidden[i]);// onhold
					levApp.setHalfDayType(halfDayType[i]);
					levApp.setHalfDayTypeToDate(halfDayTypeToDate[i]);
					levApp.setUploadDoc(document[i]);
					levApp.setUploadDocPath(documentPath[i]);
					levApp.setIteratorPenaltyDays(iteratorPenaltyDays[i]);
					levApp
							.setIteratorAdjustPenaltyDays(iteratorAdjustPenaltyDays[i]);
					levApp
							.setIteratorUnAdjustPenaltyDays(iteratorUnAdjustPenaltyDays[i]);
					levApp.setExpectedDeliveryDate(expectedDeliveryDate[i]);// expected
					// delivery
					// date

					if (document != null && document.length > 0) {

						final String[] splitDocumentPath = documentPath[i]
								.split(",");
						final String[] splitDocument = document[i].split(",");
						final ArrayList list = new ArrayList();
						for (int j = 0; j < splitDocument.length; j++) {
							LeaveApplication bean = new LeaveApplication();
							bean.setUploadDocPath(splitDocumentPath[j]);
							bean.setUploadDoc(splitDocument[j]);
							list.add(bean);
						}
						levApp.setIttUploadList(list);
					}
					levApp.setIsHalfDayLeaveItt(isHalfDayLeaveItt[i]);
					leaveList.add(i, levApp);
				}// end of for loop
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in displayIteratorValue--------- " + e);
			e.printStackTrace();
		}
		return leaveList;
	}// end of displayIteratorValue

	/**
	 * THIS METHOD IS USED FOR editing the leave application
	 * 
	 * @param srNo
	 * @param leaveType
	 * @param frmDate
	 * @param toDate
	 * @param leaveDays
	 * @param leaveCode
	 * @param availBalance
	 * @param closeBalance
	 * @param onholdhidden
	 * @param totalLeaveDays
	 * @param levCode
	 * @param newonhold
	 * @param leaveBean
	 * @param halfDayTypeToDate
	 * @param documentPath
	 * @param document
	 * @param iteratorPenaltyDays
	 * @param iteratorUnAdjustPenaltyDays
	 * @param iteratorAdjustPenaltyDays
	 * @param expectedDeliveryDate
	 * @return ArrayList
	 */
	public ArrayList<LeaveApplication> displayNewValue(String[] srNo,
			String[] leaveType, String[] frmDate, String[] toDate,
			String[] leaveDays, String[] leaveCode, String[] availBalance,
			String[] closeBalance, String[] onholdhidden,
			String totalLeaveDays, String levCode, String newonhold,
			LeaveApplication leaveBean, String[] halfDayType,
			String[] halfDayTypeToDate, String[] document,
			String[] documentPath, String[] iteratorPenaltyDays,
			String[] iteratorAdjustPenaltyDays,
			String[] iteratorUnAdjustPenaltyDays,
			String[] expectedDeliveryDate, String[] isHalfDayLeaveItt) {
		ArrayList<LeaveApplication> leaveList = null;
		try {
			leaveList = new ArrayList<LeaveApplication>();
			if (srNo != null) {
				Vector uploadVect = new Vector();
				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication levApp = new LeaveApplication();
					levApp.setSlevType(leaveType[i]); // leave name
					levApp.setSleaveFromDtl(frmDate[i]);// leave from date
					levApp.setSleaveToDtl(toDate[i]);// leave to date
					if (levCode.equals(leaveCode[i])) {
						boolean result = isZeroBalApplicable(leaveCode[i],
								leaveBean);
						if (result) {
							levApp.setCloseBalance(leaveBean
									.getLevOpeningBalance());
							levApp.setOnholdhidden(newonhold);// on hold
							// balance
						}// end of nested if
						else {
							levApp.setCloseBalance(String
									.valueOf(totalLeaveDays));// closing
							// balance
							levApp.setOnholdhidden(newonhold);
						}// end of else
					}// end of if
					else {
						levApp.setCloseBalance(closeBalance[i]);// closing
						// balance
						levApp.setOnholdhidden(onholdhidden[i]);
					}// end of else
					levApp.setSlevClosingBalance(leaveDays[i]);// leave days
					levApp.setSlevCode(leaveCode[i]);// leave code
					levApp.setSrNo(srNo[i]);// sr no
					levApp.setAvailBalance(availBalance[i]);// available balance
					levApp.setHalfDayType(halfDayType[i]);
					levApp.setHalfDayTypeToDate(halfDayTypeToDate[i]);
					levApp.setUploadDoc(document[i]);
					levApp.setUploadDocPath(documentPath[i]);
					levApp.setIteratorPenaltyDays(iteratorPenaltyDays[i]);
					levApp
							.setIteratorAdjustPenaltyDays(iteratorAdjustPenaltyDays[i]);
					levApp
							.setIteratorUnAdjustPenaltyDays(iteratorUnAdjustPenaltyDays[i]);
					levApp.setExpectedDeliveryDate(expectedDeliveryDate[i]);

					levApp.setIsHalfDayLeaveItt(isHalfDayLeaveItt[i]);

					if (document != null && document.length > 0) {

						String[] splitDocumentPath = documentPath[i].split(",");
						String[] splitDocument = document[i].split(",");
						final ArrayList list = new ArrayList();
						for (int j = 0; j < splitDocument.length; j++) {
							LeaveApplication bean = new LeaveApplication();
							bean.setUploadDocPath(splitDocumentPath[j]);
							bean.setUploadDoc(splitDocument[j]);
							list.add(bean);
						}
						levApp.setIttUploadList(list);
					}
					leaveList.add(i, levApp);
				}// end of for loop
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in displayNewValue--------- " + e);
		}
		return leaveList;
	}// end of displayNewValue

	/**
	 * THIS METHOD IS USED FOR CHECKING LEAVE DETAILS AND LEAVE POLICY
	 * @param bean
	 * @param empCode
	 * @param halfDayType
	 * @param halfDayTypeToDate
	 * @param iteratorUnAdjustPenaltyDays
	 * @param iteratorAdjustPenaltyDays
	 * @param iteratorPenaltyDays
	 * @param status
	 * @param expectedDeliveryDate
	 * @param isHalfDayLeaveItt
	 * @param proofFileName
	 * @param proofName
	 * @param leaveCode
	 * @param leaveDays
	 * @param fromDate
	 * @param toDate
	 * @param approverId
	 * @param alterapproverId
	 * @return boolean
	 */
	public boolean checkLeaveDetails(LeaveApplication bean, String[] leaveCode,
			String[] leaveDays, String[] fromDate, String[] toDate,
			String approverId, String alterapproverId, String[] empCode,
			String[] halfDayType, String[] halfDayTypeToDate,
			String[] document, String[] documentPath,
			String[] iteratorPenaltyDays, String[] iteratorAdjustPenaltyDays,
			String[] iteratorUnAdjustPenaltyDays, String status,
			String[] expectedDeliveryDate, String[] isHalfDayLeaveItt) {
		boolean result = false;
		try {
			if (leaveCode != null) {
				List<Date> shortFromDtList = new ArrayList<Date>();
				Date[] frmDateFormat = new Date[fromDate.length];
				for (int i = 0; i < fromDate.length; i++) {
					for (int j = 0; j < fromDate.length; j++) {
						if ((i != j) && (fromDate[i].equals(fromDate[j]))
								&& !(leaveDays[j].contains(".5"))) {
							setAlertMessage("You have already apply leave from "
									+ fromDate[j]);
							return false;
						}// end of if
					}// end of nested for loop
					try {
						SimpleDateFormat frmSimplDt = new SimpleDateFormat(
								"dd-MM-yyyy");
						Date frmDt = frmSimplDt.parse(fromDate[i]);
						frmDateFormat[i] = frmDt;
						shortFromDtList.add(frmDt);
					} // end of try
					catch (Exception e) {
						logger
								.error("Exception in checkLeaveDetails------------"
										+ e);
					}// end of catch
				}// end of for loop
				/** sorting and searching from Date */
				Collections.sort(shortFromDtList);
				final String[][] leaveDtlStr = new String[shortFromDtList
						.size()][13];
				int count = 0;
				// for (Date stringFrmDt : shortFromDtList) {
				for (int i = 0; i < leaveCode.length; i++) {
					// if (stringFrmDt.equals(frmDateFormat[i])) {
					leaveDtlStr[count][0] = leaveCode[count]; // leave code
					leaveDtlStr[count][1] = leaveDays[count]; // leave days
					leaveDtlStr[count][2] = fromDate[count]; // from date
					leaveDtlStr[count][3] = toDate[count]; // to date
					leaveDtlStr[count][4] = iteratorPenaltyDays[count];// penalty
					// days
					leaveDtlStr[count][5] = iteratorAdjustPenaltyDays[count];// adjust
					// penalty
					// days
					leaveDtlStr[count][6] = iteratorUnAdjustPenaltyDays[count];// non
					// adjust
					// penalty
					// days
					leaveDtlStr[count][7] = halfDayType[count]; // from date
					// half days
					leaveDtlStr[count][8] = halfDayTypeToDate[count];// to
					// date
					// half
					// day
					leaveDtlStr[count][9] = documentPath[count];// document path
					leaveDtlStr[count][10] = document[count];// document name
					leaveDtlStr[count][11] = expectedDeliveryDate[count];// expected
					// delivery
					// date
					leaveDtlStr[count][12] = isHalfDayLeaveItt[count];
					count++;
					// }// end of if
				}// end of nested for loop
				// }// end of for loop
				for (int i = 0; i < leaveDtlStr.length; i++) {
					result = checkIsLeaveAppliedBefore(bean, leaveDtlStr, i);
					if (!result) {
						return false;
					}// end of if
				}
				for (int i = 0; i < leaveDtlStr.length; i++) {
					result = checkDateRange(bean, leaveDtlStr, i);
					if (!result) {
						return false;
					}// end of if
				}// end of for loop				

				for (int i = 0; i < leaveDtlStr.length; i++) {
					result = checkNonConsecutiveWithIterator(bean, leaveDtlStr,
							i);
					if (!result) {
						return false;
					}// end of if
				}// end of for loop				

				for (int i = 0; i < leaveDtlStr.length; i++) {
					result = checkNonConsecutiveWithDB(bean, leaveDtlStr, i);
					if (!result) {
						return false;
					}// end of if
				}// end of for loop

				for (int i = 0; i < leaveDtlStr.length; i++) {
					result = checkNonConsecutiveLeaveWithIterator(bean,
							leaveDtlStr, i);
					if (!result) {
						return false;
					}// end of if
				}// end of for loop

				for (int i = 0; i < leaveDtlStr.length; i++) {
					result = checkNonConsecutiveLeaveWithDB(bean, leaveDtlStr,
							i);
					if (!result) {
						return false;
					}// end of if
				}// end of for loop

				for (int i = 0; i < leaveDtlStr.length; i++) {
					result = checkNonConsecutiveMaternityLeaveWithDB(bean,
							leaveDtlStr, i);
					if (!result) {
						return false;
					}// end of if
				}// end of for loop
				// map for leave application days
				Map<String, String> leaveMap = new TreeMap<String, String>();
				for (int i = 0; i < leaveDtlStr.length; i++) {

					// /////////////
					double leaveAppliedDays = 0.0d;
					double penaltyDays = 0.0d;
					double totalLeaveDays = 0.0d;
					double unadjustedPenaltyDays = 0.0d;

					unadjustedPenaltyDays = Double
							.parseDouble(leaveDtlStr[i][6]);
					if (unadjustedPenaltyDays == 0.0
							|| unadjustedPenaltyDays == 0) {
						leaveAppliedDays = Double
								.parseDouble(leaveDtlStr[i][1]);
						penaltyDays = Double.parseDouble(leaveDtlStr[i][4]);
						totalLeaveDays = leaveAppliedDays + penaltyDays;
					} else {
						totalLeaveDays = Double.parseDouble(leaveDtlStr[i][5])
								+ Double.parseDouble(leaveDtlStr[i][1]);
					}

					if (!leaveMap.isEmpty()
							&& leaveMap.containsKey(leaveDtlStr[i][0])) {
						double leaveAppDays = Double.parseDouble(leaveMap
								.get(leaveDtlStr[i][0]));
						// leaveAppDays +=
						// Double.parseDouble(leaveDtlStr[i][1]);
						leaveAppDays += Double.parseDouble(String
								.valueOf(totalLeaveDays));
						leaveMap.put(leaveDtlStr[i][0], String
								.valueOf(leaveAppDays));

					} // end of if
					else {
						leaveMap.put(leaveDtlStr[i][0], String
								.valueOf(totalLeaveDays));
					}// end of else
				}// end of for loop
				Map hashMap = getTotalLevDaysInDate(leaveDtlStr);
				Iterator entries = hashMap.entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) entries
							.next();
					if (Double.parseDouble(entry.getValue()) > 1.0) {
						setAlertMessage("You can not applied leave more than 1 day in a same day.");
						return false;
					}
				}
				if (bean.getLeaveCode().equals("")) {
					result = saveLeaveApplication(bean, leaveMap, leaveDtlStr,
							approverId, alterapproverId, empCode, status);
				}// end of if
				else {
					result = updateLeaveApplication(bean, leaveMap,
							leaveDtlStr, approverId, alterapproverId, empCode,
							status);
				}// end of else
			}// end of if

			else {

				setAlertMessage("Please apply atleast one leave");
				return false;
			}// end of else
		} catch (Exception e) {
			logger.error("Exception in checkLeaveDetails--------- " + e);
			e.printStackTrace();
		}
		return result;
	} // end of checkLeaveDetails	

	/**
	 * Method Name: saveLeaveApplication()
	 * 
	 * @purpose: THIS METHOD IS USED FOR SAVING LEAVE APPLICATION
	 * @param leaveBean
	 * @param leaveMap
	 * @param empCode
	 * @param status
	 * @param proofFileName
	 * @param proofName
	 * @param leaveDtlStr
	 * @param approverId
	 * @param alterapproverId
	 * @return boolean
	 */
	public synchronized boolean saveLeaveApplication(
			LeaveApplication leaveBean, Map<String, String> leaveMap,
			String[][] leaveDtlStr, String approverId, String alterapproverId,
			String[] empCode, String status) {
		final String policyCode = getLeavePolicyCode(leaveBean.getEmpCode());
		final String query = " SELECT NVL(MAX(LEAVE_APPL_CODE),0)+1 FROM HRMS_LEAVE_HDR ";
		final Object obj[][] = getSqlModel().getSingleResult(query);
		leaveBean.setLeaveCode(String.valueOf(obj[0][0]));
		final Object[][] addParam = new Object[1][10];
		addParam[0][0] = leaveBean.getLeaveCode(); // employee id
		addParam[0][1] = leaveBean.getEmpCode(); // employee id
		addParam[0][2] = leaveBean.getComments();// comments
		// addParam[0][3] = leaveBean.getMedicalCert();// reasons
		if (!leaveBean.getMedicalCert().equals("")) {
			addParam[0][3] = leaveBean.getMedicalCert();// reasons
		} else {
			addParam[0][3] = "";
		}
		addParam[0][4] = status;// status
		addParam[0][5] = leaveDtlStr[0][2];// leave from date
		addParam[0][6] = leaveDtlStr[leaveDtlStr.length - 1][3];// leave to date
		/** approver id according to reporting structure */
		addParam[0][7] = approverId;
		/** alternate approver id according to reporting structure */
		addParam[0][8] = alterapproverId;
		// addParam[0][9] = leaveBean.getLeaveReasonCode();// reasons
		if (!leaveBean.getLeaveReasonCode().equals("")) {
			addParam[0][9] = leaveBean.getLeaveReasonCode();// reasons
		} else {
			addParam[0][9] = "";
		}
		if (!(leaveBean.getLeaveReasonCode().equals("") || leaveBean
				.getLeaveReasonCode().equals("0"))
				& !leaveBean.getMedicalCert().equals("")) {
			addParam[0][3] = "";
			addParam[0][9] = leaveBean.getLeaveReasonCode();// reasons./
		}
		boolean result = getSqlModel().singleExecute(getQuery(1), addParam);
		Object[][] levCodeObj = null;
		if (result) {
			levCodeObj = getSqlModel().getSingleResult(getQuery(14));
			final Object[][] addDetails = new Object[leaveDtlStr.length][17];
			for (int i = 0; i < addDetails.length; i++) {
				addDetails[i][0] = String.valueOf(levCodeObj[0][0]); // leave
				// code
				addDetails[i][1] = leaveDtlStr[i][0];// leave code
				addDetails[i][2] = leaveDtlStr[i][1];// leave days
				addDetails[i][3] = leaveDtlStr[i][2];// leave from date
				addDetails[i][4] = leaveDtlStr[i][3];// leave to date
				addDetails[i][5] = leaveBean.getEmpCode();// employee id
				addDetails[i][6] = status;// status
				addDetails[i][7] = approverId;// according to reporting
				// structure
				addDetails[i][8] = leaveDtlStr[i][7];// from date half day
				addDetails[i][9] = leaveDtlStr[i][8];// to date half day
				addDetails[i][10] = leaveDtlStr[i][9];// document path
				addDetails[i][11] = leaveDtlStr[i][10];// document name
				addDetails[i][12] = leaveDtlStr[i][4];// penalty days
				addDetails[i][13] = leaveDtlStr[i][5];// adjusted penalty days
				addDetails[i][14] = leaveDtlStr[i][6];// un adjusted penalty
				// days
				addDetails[i][15] = leaveDtlStr[i][11];// delivery date
				addDetails[i][16] = leaveDtlStr[i][12];// half pay leave
			}// end of for loop
			result = getSqlModel().singleExecute(getQuery(15), addDetails);
			String[][] str = null;
			try {
				for (int i = 0; i < addDetails.length; i++) {
					leaveBean.setLevCode(String.valueOf(addDetails[i][1]));
					/* It splits in months */
					Object[][] addDetailsDtl = null;
					Vector vect = new Vector();
					int chDate = new Utility().checkDate(String
							.valueOf(addDetails[i][3]), String
							.valueOf(addDetails[i][4]));
					if (chDate == 0) {
						addDetailsDtl = new Object[1][9];
						addDetailsDtl[0][0] = String.valueOf(levCodeObj[0][0]); // leave
						// code
						addDetailsDtl[0][1] = leaveDtlStr[i][0];// leave code
						addDetailsDtl[0][2] = leaveDtlStr[i][1];// leave days
						addDetailsDtl[0][3] = leaveDtlStr[i][2];// leave from
						// date
						addDetailsDtl[0][4] = leaveDtlStr[i][3];// leave to date
						addDetailsDtl[0][5] = leaveBean.getEmpCode();// employee
						// id
						addDetailsDtl[0][6] = status; // status
						final String month_year_single[] = leaveDtlStr[i][2]
								.split("-");
						addDetailsDtl[0][7] = month_year_single[1];
						addDetailsDtl[0][8] = month_year_single[2];
					} else {
						String[][] splitObj = null;
						String firstORsecond = "";
						final String dateQuery = "  SELECT TO_DATE('"
								+ String.valueOf(addDetails[i][4])
								+ "','DD-MM-YYYY')-TO_DATE('"
								+ String.valueOf(addDetails[i][3])
								+ "','DD-MM-YYYY') FROM DUAL ";
						final Object dateQueryObj[][] = getSqlModel()
								.getSingleResult(dateQuery);
						int looplength = 0;
						if (dateQueryObj != null && dateQueryObj.length > 0) {
							looplength = Integer.parseInt(String
									.valueOf(dateQueryObj[0][0]));
						}
						splitObj = new DateUtility().splitDatesObj(String
								.valueOf(addDetails[i][3]), looplength + 1);
						double lDays = 0;
						if (splitObj != null && splitObj.length > 0) {
							for (int k = 0; k < splitObj.length; k++) {
								lDays = calculate(String
										.valueOf(splitObj[k][0]), String
										.valueOf(splitObj[k][0]), leaveBean);
								lDays = lDays;
								if (String.valueOf(addDetails[i][4]).equals(
										String.valueOf(splitObj[k][0]))) {
									if (leaveDtlStr[i][8] != null
											&& !leaveDtlStr[i][8].trim()
													.equals("")) {
										firstORsecond = "S";

										if (firstORsecond.equals("F")
												|| firstORsecond.equals("S")) {
											lDays = lDays - 0.5;
										}
									}
								}
								if (String.valueOf(addDetails[i][3]).equals(
										String.valueOf(splitObj[k][0]))) {
									if (leaveDtlStr[i][7] != null
											&& !leaveDtlStr[i][7].trim()
													.equals("")) {
										firstORsecond = "F";
										if (firstORsecond.equals("F")
												|| firstORsecond.equals("S")) {
											lDays = lDays - 0.5;
										}
									}
								}
								vect.add(String.valueOf(levCodeObj[0][0])); // leave
								// application
								// code
								vect.add(leaveDtlStr[i][0]);// leave code
								vect.add(lDays);// leave days
								vect.add(splitObj[k][0]);// leave frm date
								vect.add(splitObj[k][0]);// leave to date
								vect.add(leaveBean.getEmpCode());// employee
								// id
								String month_year[] = splitObj[k][0].split("-");
								vect.add(status);
								vect.add(month_year[1]);
								vect.add(month_year[2]);
							}
						}

						int counter = 0;
						addDetailsDtl = new Object[vect.size() / 9][9];
						for (int k = 0; k < vect.size() / 9; k++) {
							for (int k2 = 0; k2 < 9; k2++) {
								addDetailsDtl[k][k2] = vect.get(counter++);
							}
						}
					}// end of else
					try {
						result = getSqlModel().singleExecute(getQuery(18),
								addDetailsDtl);
					}// end of try
					catch (Exception e) {
						e.printStackTrace();
					}// end of catch
					chDate = -1;
				}// end of for loop
			} // end of try block
			catch (Exception e) {
				e.printStackTrace();
			}// end of catch block
			if (result) {
				final Object[][] updateBalance = new Object[leaveMap.size()][4];
				int count = 0;
				double leaveAppliedDays = 0.0d;
				double penaltyDays = 0.0d;
				double totalLeaveDays = 0.0d;
				double unadjustedPenaltyDays = 0.0d;
				for (String keys : leaveMap.keySet()) {

					updateBalance[count][0] = leaveMap.get(keys); // leave
					// applied
					// Leave
					updateBalance[count][1] = leaveMap.get(keys);// leave
					// applied
					// dyas
					updateBalance[count][2] = keys; // leave code
					updateBalance[count][3] = leaveBean.getEmpCode(); // employee
					// id
					/**
					 * ZERO BALANCE APPLICABLE CHECK
					 */
					final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
							+ " WHERE LEAVE_POLICY_CODE="
							+ policyCode
							+ " AND LEAVE_CODE=" + updateBalance[count][2];
					final Object[][] zeroBalance = getSqlModel()
							.getSingleResult(zeroBlncQuery);
					if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
						updateBalance[count][0] = "0"; // leave days set to be
						// 0
					}// end of if
					count++;
				}// end of for loop
				result = getSqlModel().singleExecute(getQuery(16),
						updateBalance);
				/**
				 * DELETING FROM HRMS_LEAVE_BALANCE_TEMP BEFORE SAVING
				 */
				final String deletTemp = "DELETE FROM HRMS_LEAVE_BALANCE_TEMP WHERE EMP_ID ="
						+ leaveBean.getEmpCode();
				result = getSqlModel().singleExecute(deletTemp);
				saveKeepInformedList(empCode, leaveBean);
				final String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";
				final Object compOffObj[][] = getSqlModel().getSingleResult(
						compOffAppQuery);
				if (compOffObj != null && compOffObj.length > 0) {
					for (int i = 0; i < addDetails.length; i++) {
						Double counter = 0.0;
						int value = 0;
						Object[][] compOffDateObj = null;
						if (String.valueOf(compOffObj[0][0]).equals(
								leaveDtlStr[i][0])) {
							String compOffDatesQuery = " SELECT  TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_DTL.EMP_ID , "
									+ " HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_STATUS   FROM HRMS_EXTRAWORK_APPL_DTL  "
									+ " WHERE HRMS_EXTRAWORK_APPL_DTL.EMP_ID="
									+ leaveBean.getEmpCode()
									+ " AND EXTRAWORK_APPL_STATUS='A'  AND EXTRAWORK_IS_LEAVE_APPLIED='N' ";
							if (!leaveBean.getCompOffDays().equals("0")) {
								compOffDatesQuery += "  AND TO_DATE('"
										+ leaveDtlStr[i][3]
										+ "','DD-MM-YYYY') <= EXTRAWORK_DATE+"
										+ leaveBean.getCompOffDays() + " "
										+ " AND  TO_DATE('" + leaveDtlStr[i][3]
										+ "','DD-MM-YYYY')>=EXTRAWORK_DATE  ";
							}
							compOffDatesQuery += " AND EXTRAWORK_IS_COMPOFF='Y' ORDER BY TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY') ";
							/*
							 * String compOffDatesQuery = " SELECT
							 * TO_CHAR(COMPDTL_PROJECTDATE,
							 * 'DD-MM-YYYY'),HRMS_COMPOFF_HDR.COMP_EMPID,COMP_STATUS
							 * FROM HRMS_COMPOFF_DTL " + " INNER JOIN
							 * HRMS_COMPOFF_HDR
							 * ON(HRMS_COMPOFF_HDR.COMP_ID=HRMS_COMPOFF_DTL.COMPDTL_COMPID)" + "
							 * WHERE HRMS_COMPOFF_HDR.COMP_EMPID=" +
							 * leaveBean.getEmpCode() + " AND COMP_STATUS='A'" + "
							 * AND COMPDTL_LEAVE_APPLIED='N'"; if
							 * (!leaveBean.getCompOffDays().equals("0")) {
							 * compOffDatesQuery += " AND TO_DATE('" +
							 * leaveDtlStr[i][3] + "','DD-MM-YYYY') <=
							 * COMPDTL_PROJECTDATE+" +
							 * leaveBean.getCompOffDays() + " AND TO_DATE('" +
							 * leaveDtlStr[i][3] +
							 * "','DD-MM-YYYY')>=COMPDTL_PROJECTDATE "; }
							 */
							// compOffDatesQuery += " ORDER BY
							// TO_CHAR(COMPDTL_PROJECTDATE, 'DD-MM-YYYY')";
							// logger.info("compOffDatesQuery==========="
							// + compOffDatesQuery);
							compOffDateObj = getSqlModel().getSingleResult(
									compOffDatesQuery);
							counter += Double.parseDouble(String
									.valueOf(leaveDtlStr[i][1]));
							value = counter.intValue();
							if (compOffDateObj != null
									&& compOffDateObj.length > 0) {
								if (compOffDateObj.length >= value) {
									String updateCompOffRecord = " UPDATE HRMS_EXTRAWORK_APPL_DTL  SET EXTRAWORK_IS_LEAVE_APPLIED='Y',EXTRAWORK_LEAVE_APPL_CODE="
											+ leaveBean.getLeaveCode()
											+ ",EXTRAWORK_LEAVE_FROMDATE=TO_DATE('"
											+ leaveDtlStr[i][2]
											+ "','DD-MM-YYYY'),EXTRAWORK_LEAVE_TODATE=TO_DATE('"
											+ leaveDtlStr[i][3]
											+ "','DD-MM-YYYY') "
											+ " WHERE HRMS_EXTRAWORK_APPL_DTL.EMP_ID = "
											+ leaveBean.getEmpCode();
									for (int j = 0; j < value; j++) {
										if (j == 0) {
											updateCompOffRecord += " AND ";
										} else {
											updateCompOffRecord += " OR ";
										}
										updateCompOffRecord += " EXTRAWORK_DATE=TO_DATE('"
												+ String
														.valueOf(compOffDateObj[j][0])
												+ "', 'DD-MM-YYYY')";
									}
									getSqlModel().singleExecute(
											updateCompOffRecord);
								}
							}
						}
					}
				}
				leaveBean.setIsAddFlag("false");
			}// end of if
			try {
				if (status.equals("P")) {
					isApplicationUnderProcess( leaveBean,leaveBean.getLeaveCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} // end of if
		else {
			return false;
		}// end of else
	} // end of saveLeaveApplication

	/**
	 * THIS METHOD IS USED FOR UPDATING LEAVE APPLICATION
	 * 
	 * @param leaveBean
	 * @param leaveMap
	 * @param empCode
	 * @param status
	 * @param proofFileName
	 * @param proofName
	 * @param leaveDtlStr
	 * @param approverId
	 * @param alterapproverId
	 * @return boolean
	 */
	private boolean updateLeaveApplication(LeaveApplication leaveBean,
			Map<String, String> leaveMap, String[][] leaveDtlStr,
			String approverId, String alterapproverId, String[] empCode,
			String status) {
		try {
			saveKeepInformedList(empCode, leaveBean);
			try {
				if (status.equals("P")) {
					isApplicationUnderProcess(leaveBean, leaveBean.getLeaveCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		boolean result = false;
		try {
			final Object[][] addParam = new Object[1][10];
			addParam[0][0] = leaveBean.getEmpCode(); // employee code
			addParam[0][1] = leaveBean.getComments();// comments

			if (!leaveBean.getMedicalCert().equals("")) {
				addParam[0][2] = leaveBean.getMedicalCert();// reasons
			} else {
				addParam[0][2] = "";
			}
			addParam[0][3] = leaveDtlStr[0][2]; // leave from date
			addParam[0][4] = leaveDtlStr[leaveDtlStr.length - 1][3];// leave to
			// date
			addParam[0][5] = approverId; // approver id according to
			// reporting structure
			addParam[0][6] = alterapproverId; // approver id according to
			// reporting structure
			addParam[0][7] = status;// status
			if (!leaveBean.getLeaveReasonCode().equals("")) {
				addParam[0][8] = leaveBean.getLeaveReasonCode();// reasons
			} else {
				addParam[0][8] = "";
			}
			if (!(leaveBean.getLeaveReasonCode().equals("") || leaveBean
					.getLeaveReasonCode().equals("0"))
					& !leaveBean.getMedicalCert().equals("")) {
				addParam[0][2] = "";
				addParam[0][8] = leaveBean.getLeaveReasonCode();// reasons./
			}
			addParam[0][9] = leaveBean.getLeaveCode();// leave application
			// code
			result = getSqlModel().singleExecute(getQuery(5), addParam);
			// !leaveBean.getIsAddFlag().equals("false")
			if (result) {
				final String deletLeaveDtl = " DELETE FROM HRMS_LEAVE_DTL WHERE LEAVE_APPLICATION_CODE="
						+ leaveBean.getLeaveCode();
				result = getSqlModel().singleExecute(deletLeaveDtl);
				final String deletLeaveDtl1 = " DELETE FROM HRMS_LEAVE_DTLHISTORY WHERE LEAVE_APPLICATION_CODE="
						+ leaveBean.getLeaveCode();
				boolean flag = getSqlModel().singleExecute(deletLeaveDtl1);
				Object[][] addDetails = null;
				if (result) {
					addDetails = new Object[leaveDtlStr.length][17];
					for (int i = 0; i < addDetails.length; i++) {
						addDetails[i][0] = leaveBean.getLeaveCode();// leave
						// appl code
						addDetails[i][1] = leaveDtlStr[i][0];// leave code
						addDetails[i][2] = leaveDtlStr[i][1];// leave days
						addDetails[i][3] = leaveDtlStr[i][2];// leave from
						// date
						addDetails[i][4] = leaveDtlStr[i][3];// leave to date
						addDetails[i][5] = leaveBean.getEmpCode();// employee
						// id
						addDetails[i][6] = status;
						addDetails[i][7] = approverId;// approver employee id
						addDetails[i][8] = leaveDtlStr[i][7];// from date HD
						addDetails[i][9] = leaveDtlStr[i][8];// to date half
						// day
						addDetails[i][10] = leaveDtlStr[i][9];// document path
						addDetails[i][11] = leaveDtlStr[i][10];// document name
						addDetails[i][12] = leaveDtlStr[i][4];// penalty days
						addDetails[i][13] = leaveDtlStr[i][5];// adjusted
						// penalty days
						addDetails[i][14] = leaveDtlStr[i][6];// un adjusted
						// penalty days
						addDetails[i][15] = leaveDtlStr[i][11];// delivery date
						addDetails[i][16] = leaveDtlStr[i][12];// half pay
						// leave
					}// end of for loop
					result = getSqlModel().singleExecute(getQuery(15),
							addDetails);
				}// end of if
				String[][] str = null;
				try {
					for (int i = 0; i < addDetails.length; i++) {
						leaveBean.setLevCode(String.valueOf(addDetails[i][1]));
						str = new DateUtility().getDatesBetween(String
								.valueOf(addDetails[i][3]), String
								.valueOf(addDetails[i][4]));
						Object[][] addDetailsDtl = null;
						Vector vect = new Vector();
						int chDate = new Utility().checkDate(String
								.valueOf(addDetails[i][3]), String
								.valueOf(addDetails[i][4]));

						if (chDate == 0) {
							addDetailsDtl = new Object[1][9];
							addDetailsDtl[0][0] = leaveBean.getLeaveCode(); // leave
							// application
							// code
							addDetailsDtl[0][1] = leaveDtlStr[i][0];// leave
							// code
							addDetailsDtl[0][2] = leaveDtlStr[i][1];// leave
							// days
							addDetailsDtl[0][3] = leaveDtlStr[i][2];// leave
							// from
							// date
							addDetailsDtl[0][4] = leaveDtlStr[i][3];// leave to
							// date
							addDetailsDtl[0][5] = leaveBean.getEmpCode();// employee
							// id
							addDetailsDtl[0][6] = status; // status
							final String month_year_single[] = leaveDtlStr[i][2]
									.split("-");
							addDetailsDtl[0][7] = month_year_single[1];
							addDetailsDtl[0][8] = month_year_single[2];
						} else {
							String[][] splitObj = null;
							String firstORsecond = "";

							final String dateQuery = "  SELECT TO_DATE('"
									+ String.valueOf(addDetails[i][4])
									+ "','DD-MM-YYYY')-TO_DATE('"
									+ String.valueOf(addDetails[i][3])
									+ "','DD-MM-YYYY') FROM DUAL ";

							final Object dateQueryObj[][] = getSqlModel()
									.getSingleResult(dateQuery);
							int looplength = 0;
							if (dateQueryObj != null && dateQueryObj.length > 0) {
								looplength = Integer.parseInt(String
										.valueOf(dateQueryObj[0][0]));
							}

							splitObj = new DateUtility().splitDatesObj(String
									.valueOf(addDetails[i][3]), looplength + 1);
							double lDays = 0;
							if (splitObj != null && splitObj.length > 0) {
								for (int k = 0; k < splitObj.length; k++) {
									lDays = calculate(String
											.valueOf(splitObj[k][0]), String
											.valueOf(splitObj[k][0]), leaveBean);
									lDays = lDays;

									if (String
											.valueOf(addDetails[i][4])
											.equals(
													String
															.valueOf(splitObj[k][0]))) {
										if (leaveDtlStr[i][8] != null
												&& !leaveDtlStr[i][8].trim()
														.equals("")) {
											firstORsecond = "S";

											if (firstORsecond.equals("F")
													|| firstORsecond
															.equals("S")) {
												lDays = lDays - 0.5;
											}
										}

									}

									if (String
											.valueOf(addDetails[i][3])
											.equals(
													String
															.valueOf(splitObj[k][0]))) {

										if (leaveDtlStr[i][7] != null
												&& !leaveDtlStr[i][7].trim()
														.equals("")) {
											firstORsecond = "F";

											if (firstORsecond.equals("F")
													|| firstORsecond
															.equals("S")) {
												lDays = lDays - 0.5;
											}

										}

									}

									vect.add(leaveBean.getLeaveCode()); // leave
									// application
									// code
									vect.add(leaveDtlStr[i][0]);// leave
									// code
									vect.add(lDays);// leave
									// days
									vect.add(splitObj[k][0]);// leave
									// from date
									vect.add(splitObj[k][0]);// leave to
									// date
									vect.add(leaveBean.getEmpCode());// employee
									// id
									String month_year[] = splitObj[k][0]
											.split("-");
									vect.add(status);
									vect.add(month_year[1]);
									vect.add(month_year[2]);
								}
							}
							int counter = 0;
							addDetailsDtl = new Object[vect.size() / 9][9];
							for (int k = 0; k < vect.size() / 9; k++) {
								for (int k2 = 0; k2 < 9; k2++) {
									addDetailsDtl[k][k2] = vect.get(counter++);
								}

							}
						}

						try {
							result = getSqlModel().singleExecute(getQuery(18),
									addDetailsDtl);
						} // end of try
						catch (Exception e) {
							logger
									.error("Exception in updateLeaveApplication first----------------"
											+ e);
							// TODO: handle exception
						}// end of catch
					}// end of for loop
				}// end of try
				catch (Exception e) {
					logger
							.error("Exception in updateLeaveApplication second----------------"
									+ e);
				}// end of catch

				String updatedCode = "";
				String updatedLeave = "";
				for (String keys : leaveMap.keySet()) {
					updatedCode += keys + ",";
				}// end of for loop
				if (!updatedCode.equals("")) {
					updatedLeave = updatedCode.substring(0, updatedCode
							.length() - 1);

				}// end of if
				/**
				 * SELECTING RECORD FROM HRMS_LEAVE_BALANCE_TEMP
				 */

				final String tempBalance = "SELECT NVL(LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ONHOLD,0),LEAVE_CODE,EMP_ID FROM HRMS_LEAVE_BALANCE_TEMP WHERE EMP_ID ="
						+ leaveBean.getEmpCode();
				final Object[][] tempBalanceObj = getSqlModel()
						.getSingleResult(tempBalance);

				/**
				 * UPDATE INTO HRMS_LEAVE_BALANCE ORIGINAL
				 */
				final String updateOrgBalance = " UPDATE HRMS_LEAVE_BALANCE SET "
						+ " LEAVE_CLOSING_BALANCE = ?,LEAVE_ONHOLD =? "
						+ " WHERE  LEAVE_CODE =? AND EMP_ID =? ";
				getSqlModel().singleExecute(updateOrgBalance, tempBalanceObj);
				/**
				 * DELETING FROM HRMS_LEAVE_BALANCE_TEMP BEFORE UPDATE
				 */
				final String deletTemp = "DELETE FROM HRMS_LEAVE_BALANCE_TEMP WHERE EMP_ID ="
						+ leaveBean.getEmpCode();
				result = getSqlModel().singleExecute(deletTemp);

				final String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";
				final Object compOffObj[][] = getSqlModel().getSingleResult(
						compOffAppQuery);

				if (compOffObj != null && compOffObj.length > 0) {

					final String updateCompOffRecordQuery = "  UPDATE HRMS_EXTRAWORK_APPL_DTL SET EXTRAWORK_IS_LEAVE_APPLIED='N',EXTRAWORK_LEAVE_APPL_CODE='', "
							+ " EXTRAWORK_LEAVE_FROMDATE='',EXTRAWORK_LEAVE_TODATE=''"
							+ " WHERE EXTRAWORK_LEAVE_APPL_CODE="
							+ leaveBean.getLeaveCode();

					getSqlModel().singleExecute(updateCompOffRecordQuery);

					for (int i = 0; i < addDetails.length; i++) {
						Double counter = 0.0;
						int value = 0;
						Object[][] compOffDateObj = null;
						if (String.valueOf(compOffObj[0][0]).equals(
								leaveDtlStr[i][0])) {

							String compOffDatesQuery = " SELECT  TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_DTL.EMP_ID , "
									+ " HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_STATUS   FROM HRMS_EXTRAWORK_APPL_DTL  "
									+ " WHERE HRMS_EXTRAWORK_APPL_DTL.EMP_ID="
									+ leaveBean.getEmpCode()
									+ " AND EXTRAWORK_APPL_STATUS='A'  AND EXTRAWORK_IS_LEAVE_APPLIED='N' ";

							if (!leaveBean.getCompOffDays().equals("0")) {
								compOffDatesQuery += "  AND TO_DATE('"
										+ leaveDtlStr[i][3]
										+ "','DD-MM-YYYY') <= EXTRAWORK_DATE+"
										+ leaveBean.getCompOffDays() + " "
										+ " AND  TO_DATE('" + leaveDtlStr[i][3]
										+ "','DD-MM-YYYY')>=EXTRAWORK_DATE  ";

							}

							compOffDatesQuery += " AND EXTRAWORK_IS_COMPOFF='Y' ORDER BY TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY')";

							// logger.info("compOffDatesQuery==========="
							// + compOffDatesQuery);
							compOffDateObj = getSqlModel().getSingleResult(
									compOffDatesQuery);
							counter += Double.parseDouble(String
									.valueOf(leaveDtlStr[i][1]));
							value = counter.intValue();

							if (compOffDateObj != null
									&& compOffDateObj.length > 0) {
								if (compOffDateObj.length >= value) {

									String updateCompOffRecord = " UPDATE HRMS_EXTRAWORK_APPL_DTL  SET EXTRAWORK_IS_LEAVE_APPLIED='Y',EXTRAWORK_LEAVE_APPL_CODE="
											+ leaveBean.getLeaveCode()
											+ ",EXTRAWORK_LEAVE_FROMDATE=TO_DATE('"
											+ leaveDtlStr[i][2]
											+ "','DD-MM-YYYY'),EXTRAWORK_LEAVE_TODATE=TO_DATE('"
											+ leaveDtlStr[i][3]
											+ "','DD-MM-YYYY') "
											+ " WHERE HRMS_EXTRAWORK_APPL_DTL.EMP_ID = "
											+ leaveBean.getEmpCode();

									for (int j = 0; j < value; j++) {
										if (j == 0) {
											updateCompOffRecord += " AND ";
										} else {
											updateCompOffRecord += " OR ";
										}

										updateCompOffRecord += " EXTRAWORK_DATE=TO_DATE('"
												+ String
														.valueOf(compOffDateObj[j][0])
												+ "', 'DD-MM-YYYY')";
									}
									// logger
									// .info("updateCompOffRecord in
									// updateLeaveApplication=============="
									// + updateCompOffRecord);
									getSqlModel().singleExecute(
											updateCompOffRecord);
								}
							}
						}
					}
				}
			}// end of if
			leaveBean.setIsAddFlag("false");
		} catch (Exception e) {
			logger.error("Exception in updateLeaveApplication--------- " + e);
			e.printStackTrace();
		}
		return result;
	}// end of updateLeaveApplication

	/** Method Name : checkIsLeaveAppliedBefore()
	 * @purpose :  To check is leave applied before on that particular date 
	 * @param bean
	 * @param leaveDtlStr
	 * @param rowNum
	 * @return boolean
	 */
	public boolean checkIsLeaveAppliedBefore(LeaveApplication bean,
			String[][] leaveDtlStr, int rowNum) {
		boolean result = false;
		String query = " SELECT EMP_ID,LEAVE_APPLICATION_CODE,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),"
				+ " TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DTL_STATUS"
				+ " FROM HRMS_LEAVE_DTLHISTORY"
				+ " WHERE EMP_ID = "
				+ bean.getEmpCode()
				+ " AND (LEAVE_DTL_STATUS IS NULL OR (LEAVE_DTL_STATUS != 'R' AND"
				+ " LEAVE_DTL_STATUS != 'N' AND LEAVE_DTL_STATUS != 'X' ))"
				+ " AND LEAVE_FROM_DATE >= TO_DATE('"
				+ leaveDtlStr[rowNum][2]
				+ "','DD-MM-YYYY')"
				+ " AND LEAVE_FROM_DATE <= TO_DATE('"
				+ leaveDtlStr[rowNum][3] + "','DD-MM-YYYY') ";
		if (!bean.getLeaveCode().equals("")) {
			query = query + " AND LEAVE_APPLICATION_CODE != "
					+ bean.getLeaveCode();
		}
		Object[][] appliedLeavObj = getSqlModel().getSingleResult(query);
		if (appliedLeavObj != null && appliedLeavObj.length > 0) {
			setAlertMessage("Leave has already been applied from"
					+ leaveDtlStr[rowNum][2] + " to " + leaveDtlStr[rowNum][3]
					+ "\nChoose a different date and apply again.");
			return result;
		}
		return true;
	}

	/**
	 * IF USER IS ADDING NEW LEAVE, IT WILL CHECK DATE RANGE
	 * 
	 * @param bean
	 * @param leaveDtlStr
	 * @param rowNum
	 * @return boolean
	 */
	private boolean checkDateRange(LeaveApplication bean,
			String[][] leaveDtlStr, int rowNum) {

		boolean message = false;
		try {
			String checkFromDate = "";
			if (bean.getLeaveCode().equals("")) {
				checkFromDate = " SELECT TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')"
						+ " FROM HRMS_LEAVE_DTL  WHERE (LEAVE_TO_DATE >= TO_DATE('"
						+ leaveDtlStr[rowNum][2]
						+ "','DD-MM-YYYY') "
						+ " AND LEAVE_FROM_DATE <= TO_DATE('"
						+ leaveDtlStr[rowNum][3]
						+ "','DD-MM-YYYY') ) "
						+ " AND EMP_ID ="
						+ bean.getEmpCode()
						+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS!='R' AND LEAVE_DTL_STATUS!='N' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')";
			} // end of if
			else {
				checkFromDate = " SELECT TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')"
						+ " FROM HRMS_LEAVE_DTL  WHERE (LEAVE_TO_DATE >= TO_DATE('"
						+ leaveDtlStr[rowNum][2]
						+ "','DD-MM-YYYY') "
						+ " AND LEAVE_FROM_DATE <= TO_DATE('"
						+ leaveDtlStr[rowNum][3]
						+ "','DD-MM-YYYY') ) "
						+ " AND EMP_ID ="
						+ bean.getEmpCode()
						+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS!='R' AND LEAVE_DTL_STATUS!='N' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
						+ " AND LEAVE_APPLICATION_CODE !="
						+ bean.getLeaveCode();
			}// end of else
			final Object[][] validFromDate = getSqlModel().getSingleResult(
					checkFromDate);

			double totalDays = getTotalLeaveDaysInDate(leaveDtlStr[rowNum][2],
					leaveDtlStr[rowNum][3], bean.getEmpCode());

			// (String.valueOf(leaveDtlStr[rowNum][1]).contains(".5")
			if (validFromDate != null && validFromDate.length > 0
					&& (totalDays > 1.0)) {
				setAlertMessage("Leave has already been applied from"
						+ leaveDtlStr[rowNum][2] + " to "
						+ leaveDtlStr[rowNum][3]
						+ "\nChoose a different date and apply again.");
				return message;
			}// end of if
			if (rowNum > 0) {
				int datDiff = 0;
				datDiff = new Utility().checkDate(leaveDtlStr[rowNum][2],
						leaveDtlStr[rowNum - 1][3]);// from date greater than to
				// date it retuns one
				double leaveDay = 0.0;
				leaveDay = Double.parseDouble(String
						.valueOf(leaveDtlStr[rowNum][1]));
				if (datDiff == 0 && ((leaveDay > 0.0))) {
					setAlertMessage("Leave has already been applied from"
							+ leaveDtlStr[rowNum][2] + " to "
							+ leaveDtlStr[rowNum - 1][3]
							+ "\nChoose a different date and apply again.");
					return message;
				}// end of nested if
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in checkDateRange--------- " + e);
		}
		return true;

	}// end of checkDateRange

	/**
	 * IF USER IS ADDING NEW LEAVE, IT WILL CHECK NON CONSECUTIVE LEAVE
	 * ACOORDING TO LEAVE POLICY
	 * 
	 * Please refer Leave Policy Document section 4.2.19
	 * 
	 * @param bean
	 * @param leaveDtlStr
	 * @param rowNum
	 * @return boolean
	 */
	private boolean checkNonConsecutiveWithDB(LeaveApplication bean,
			String[][] leaveDtlStr, int rowNum) {
		boolean isConsecutive = true;
		try {
			final String policyCode = getLeavePolicyCode(bean.getEmpCode());
			String nonConsLeave = "";
			Object[][] nonConsData = null;
			nonConsLeave = " SELECT LEAVE_CANNOT_COMBINE  FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE LEAVE_CODE ="
					+ leaveDtlStr[rowNum][0]
					+ " AND LEAVE_POLICY_CODE=" + policyCode;
			nonConsData = getSqlModel().getSingleResult(nonConsLeave);
			if (nonConsData != null && nonConsData.length > 0) {
				int totalHol = 0;
				int weekenHol = 0;
				int secSaterdaySunday = 0;
				int actualDay = 0;
				String previousLeaveQuery = "";
				if (bean.getLeaveCode().equals("")) {
					previousLeaveQuery = "SELECT LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')"
							+ " FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
							+ bean.getEmpCode()
							+ " AND LEAVE_CODE IN ("
							+ String.valueOf(nonConsData[0][0])
							+ ")"
							+ " AND LEAVE_TO_DATE < TO_DATE('"
							+ leaveDtlStr[rowNum][2]
							+ "','DD-MM-YYYY') "
							+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
							+ " ORDER BY LEAVE_TO_DATE desc ";
				}// end of nested if
				else {
					previousLeaveQuery = "SELECT LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')"
							+ " FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
							+ bean.getEmpCode()
							+ " AND LEAVE_CODE IN ("
							+ String.valueOf(nonConsData[0][0])
							+ ")"
							+ " AND LEAVE_TO_DATE < TO_DATE('"
							+ leaveDtlStr[rowNum][2]
							+ "','DD-MM-YYYY') "
							+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
							+ " AND LEAVE_APPLICATION_CODE !="
							+ bean.getLeaveCode()
							+ " ORDER BY LEAVE_TO_DATE DESC ";
				}// end of else
				final Object beforeData[][] = getSqlModel().getSingleResult(
						previousLeaveQuery);
				if (beforeData != null && beforeData.length > 0) {
					final String fromDate = String.valueOf(beforeData[0][1]);
					Object holidayObj[][] = null;
					if (bean.getBrnHDayFlag().equals("true")) {// FOR BRANCH
						// WISE HOLIDAY
						// CHECK

						final String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH "
								+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
								+ bean.getEmpCode()
								+ ")"
								+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE >=TO_DATE('"
								+ fromDate
								+ "','DD-MM-YYYY') "
								+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE <=TO_DATE('"
								+ leaveDtlStr[rowNum][2] + "','DD-MM-YYYY')";
						holidayObj = getSqlModel().getSingleResult(holStr);

					} // end of if
					else // holiday check
					{
						final String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
								+ " WHERE HOLI_DATE >=TO_DATE('"
								+ fromDate
								+ "','DD-MM-YYYY') AND HOLI_DATE <=TO_DATE('"
								+ leaveDtlStr[rowNum][2] + "','DD-MM-YYYY')";
						holidayObj = getSqlModel().getSingleResult(holStr);
					}

					final String select = "SELECT TO_DATE('"
							+ leaveDtlStr[rowNum][2]
							+ "','DD-MM-YYYY')-TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') from  DUAL";
					final Object[][] dateDiff = getSqlModel().getSingleResult(
							select);
					actualDay = Integer
							.parseInt(String.valueOf(dateDiff[0][0]));
					if (holidayObj != null) {
						weekenHol = new Utility()
								.removeSecSatSunFromHolidayList(holidayObj);
					}// end of nested if
					secSaterdaySunday = new Utility()
							.countNumberOfSecSaturdaysAndSundays(new Utility()
									.getCalanderDate(fromDate), new Utility()
									.getCalanderDate(leaveDtlStr[rowNum][2]));
					totalHol = weekenHol + secSaterdaySunday;
					if (actualDay - 1 <= totalHol) {
						isConsecutive = false;
						setAlertMessage("You can't apply leave from "
								+ leaveDtlStr[rowNum][2]);
						return isConsecutive;
					}// end of if

					else if (actualDay - 1 < 1) {
						setAlertMessage("You can't apply leave from "
								+ leaveDtlStr[rowNum][2]);
						return isConsecutive;
					}// end of else if
				}// end of if
				String afterLeaveQuery = "";
				if (bean.getLeaveCode().equals("")) {
					afterLeaveQuery = "SELECT LEAVE_CODE,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY')"
							+ " FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
							+ bean.getEmpCode()
							+ " AND LEAVE_CODE IN ("
							+ String.valueOf(nonConsData[0][0])
							+ ")"
							+ " AND LEAVE_FROM_DATE > TO_DATE('"
							+ leaveDtlStr[rowNum][3]
							+ "','DD-MM-YYYY') "
							+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
							+ " ORDER BY LEAVE_FROM_DATE  ";
				} // end of if
				else {
					afterLeaveQuery = "SELECT LEAVE_CODE,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY')"
							+ " FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
							+ bean.getEmpCode()
							+ " AND LEAVE_CODE IN ("
							+ String.valueOf(nonConsData[0][0])
							+ ")"
							+ " AND LEAVE_FROM_DATE > TO_DATE('"
							+ leaveDtlStr[rowNum][3]
							+ "','DD-MM-YYYY') "
							+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' AND LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
							+ " AND LEAVE_APPLICATION_CODE !="
							+ bean.getLeaveCode()
							+ " ORDER BY LEAVE_FROM_DATE  ";
				}// end of else
				final Object afterData[][] = getSqlModel().getSingleResult(
						afterLeaveQuery);

				if (afterData != null && afterData.length > 0) {
					final String toDate = String.valueOf(afterData[0][1]);
					Object holidayObj[][] = null;
					if (bean.getBrnHDayFlag().equals("true")) {// FOR
						// BRANCHWISE
						// HOLIDAY FLAG
						final String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH "
								+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
								+ bean.getEmpCode()
								+ ")"
								+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE >=TO_DATE('"
								+ leaveDtlStr[rowNum][3]
								+ "','DD-MM-YYYY') "
								+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE <=TO_DATE('"
								+ toDate + "','DD-MM-YYYY')";
						holidayObj = getSqlModel().getSingleResult(holStr);

					} // end of if
					else // for holiday
					{
						final String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
								+ " WHERE HOLI_DATE >=TO_DATE('"
								+ leaveDtlStr[rowNum][3]
								+ "','DD-MM-YYYY') AND HOLI_DATE <=TO_DATE('"
								+ toDate + "','DD-MM-YYYY')";
						holidayObj = getSqlModel().getSingleResult(holStr);
					}

					final String select = "SELECT TO_DATE('" + toDate
							+ "','DD-MM-YYYY')-TO_DATE('"
							+ leaveDtlStr[rowNum][3]
							+ "','DD-MM-YYYY') from  DUAL";
					final Object[][] dateDiff = getSqlModel().getSingleResult(
							select);
					actualDay = Integer
							.parseInt(String.valueOf(dateDiff[0][0]));
					if (holidayObj != null) {
						weekenHol = new Utility()
								.removeSecSatSunFromHolidayList(holidayObj);
					}// end of if
					secSaterdaySunday = countNumberOfWeeklyOff(new Utility()
							.getCalanderDate(leaveDtlStr[rowNum][3]),
							new Utility().getCalanderDate(toDate), bean
									.getEmpCode());
					totalHol = weekenHol + secSaterdaySunday;
					if (actualDay - 1 <= totalHol) {
						isConsecutive = false;
						setAlertMessage("You can't apply leave from "
								+ leaveDtlStr[rowNum][3]);
						return isConsecutive;
					}// end of if
				}// end of if
			}// end of if
		} catch (Exception e) {
			logger
					.error("Exception in checkNonConsecutiveWithDB--------- "
							+ e);
		}
		return isConsecutive;
	}// end of checkNonConsecutiveWithDB

	/**
	 * This method is used for checking non consecutive leave with iterator
	 * leave list data .
	 * @param bean
	 * @param leaveDtlStr
	 * @param rowNum
	 * @return
	 */
	private boolean checkNonConsecutiveLeaveWithIterator(LeaveApplication bean,
			String[][] leaveDtlStr, int rowNum) {
		boolean isNonAttached = true;
		boolean flg = false;

		try {
			final String policyCode = getLeavePolicyCode(bean.getEmpCode());
			final String holidayFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_CODE"
					+ " FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE  LEAVE_POLICY_CODE="
					+ policyCode
					+ "  AND (LEAVE_HOLIDAY_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
					+ leaveDtlStr[rowNum][0] + ")";
			final Object[][] holidayCheckObj = getSqlModel().getSingleResult(
					holidayFlag);
			final String weekFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_WEEKOFF_INCLUDE_FLAG,LEAVE_CODE"
					+ " FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE  LEAVE_POLICY_CODE="
					+ policyCode
					+ "  AND (LEAVE_WEEKOFF_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
					+ leaveDtlStr[rowNum][0] + ")";
			final Object[][] weeklyCheckObj = getSqlModel().getSingleResult(
					weekFlag);
			if (rowNum > 0) {

				String toDate = leaveDtlStr[rowNum][2]; // from date of current row
				String fromDate = leaveDtlStr[rowNum - 1][3]; // to date of previous row			
				String fromDateOfpreviousRow = leaveDtlStr[rowNum - 1][2]; // from date of previous row
				String toDateOfCurrentRow = leaveDtlStr[rowNum][3]; // to date of current row		
				/**For Sequencing*/
				Date firstToDate = new Date();
				Date firstFromDate = new Date();
				Date fromDateOfpreviousRowDate = new Date();
				Date toDateOfCurrentRowDate = new Date();
				SimpleDateFormat simpleFormat = new SimpleDateFormat(
						"dd-MM-yyyy");

				firstToDate = simpleFormat.parse(toDate);
				firstFromDate = simpleFormat.parse(fromDate);
				fromDateOfpreviousRowDate = simpleFormat
						.parse(fromDateOfpreviousRow);
				toDateOfCurrentRowDate = simpleFormat.parse(toDateOfCurrentRow);

				int totalHol = 0;
				int weekenHol = 0;
				int actualDay = 0;
				Object holidayObj[][] = null;
				if (toDateOfCurrentRowDate.before(fromDateOfpreviousRowDate)) {
					flg = true;
				}
				if (holidayCheckObj.length == 0) {

					if (bean.getBrnHDayFlag().equals("true")) {// for
						// branch wise
						// holiday check

						final String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH "
								+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
								+ bean.getEmpCode()
								+ ")"
								+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE >=TO_DATE('"
								+ fromDate
								+ "','DD-MM-YYYY') "
								+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE <=TO_DATE('"
								+ toDate + "','DD-MM-YYYY')";
						holidayObj = getSqlModel().getSingleResult(holStr);

					} // end of if
					else { // for holiday check
						final String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
								+ " WHERE HOLI_DATE >=TO_DATE('"
								+ fromDate
								+ "','DD-MM-YYYY') AND HOLI_DATE <=TO_DATE('"
								+ toDate + "','DD-MM-YYYY')";
						holidayObj = getSqlModel().getSingleResult(holStr);
					}
					if (holidayObj != null) {
						totalHol = holidayObj.length;

					}// end of if
				}
				int weeken_hol = 0;
				final String select = "SELECT TO_DATE('" + toDate
						+ "','DD-MM-YYYY')-TO_DATE('" + fromDate
						+ "','DD-MM-YYYY') from  DUAL";
				final Object[][] dateDiff = getSqlModel().getSingleResult(
						select);
				actualDay = Integer.parseInt(String.valueOf(dateDiff[0][0]));
				boolean isHalfDayCheck = false;

				if ((String.valueOf(leaveDtlStr[rowNum][1]).contains(".5") || String
						.valueOf(leaveDtlStr[rowNum][1]).contains(".5"))
						|| (String.valueOf(leaveDtlStr[rowNum - 1][1])
								.contains(".5") || String.valueOf(
								leaveDtlStr[rowNum - 1][1]).contains(".5"))) {
					isHalfDayCheck = true;
				}
				if (weeklyCheckObj.length == 0) {

					if ((String.valueOf(leaveDtlStr[rowNum][1]).contains("0.5") || String
							.valueOf(leaveDtlStr[rowNum][1]).contains(".5"))
							|| (String.valueOf(leaveDtlStr[rowNum - 1][1])
									.contains("0.5") || String.valueOf(
									leaveDtlStr[rowNum - 1][1]).contains(".5"))) {
					} else {
						/** Suppose user applied leave of Current month
						 * on 2nd row user applied leave of Previous month
						 * following code is allow this*/
						if (firstFromDate.before(firstToDate)) {
							//then correct pass same--
						} else if (toDateOfCurrentRowDate
								.before(fromDateOfpreviousRowDate)) {
							fromDate = toDateOfCurrentRow;
							toDate = fromDateOfpreviousRow;
							flg = true;
						}
						weekenHol = countNumberOfWeeklyOff(new Utility()
								.getCalanderDate(fromDate), new Utility()
								.getCalanderDate(toDate), holidayObj, bean
								.getEmpCode());
						weeken_hol = weekenHol + totalHol;
					}
				}
				if (!isHalfDayCheck) {
					/**Each and every row check with other row */
					for (int i = 0; i <= rowNum; i++) {
						for (int j = 0; j < rowNum; j++) {
							final String constFromStr = leaveDtlStr[i][2];
							final String constToStr = leaveDtlStr[i][3];
							final String varFromStr = leaveDtlStr[j][2];
							final String varToStr = leaveDtlStr[j][3];
							Date constFromDate = new Date();
							Date constToDate = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"dd-MM-yyyy");
							constFromDate = dateFormat.parse(constFromStr);
							constToDate = dateFormat.parse(constToStr);
							Date varFrmDate = new Date();
							Date varToDate = new Date();
							varFrmDate = dateFormat.parse(varFromStr);
							varToDate = dateFormat.parse(varToStr);
							if (i != j) {
								if (varFrmDate.after(constFromDate)
										&& varFrmDate.before(constToDate)) {
									isNonAttached = false;
									setAlertMessage("Leave is already applied"
											+ fromDateOfpreviousRow + " to "
											+ toDateOfCurrentRow);

									if (varToDate.before(constToDate)
											&& varToDate.after(constFromDate)) {
										setAlertMessage("Leave is already applied"
												+ fromDateOfpreviousRow
												+ " to " + toDateOfCurrentRow);
										return isNonAttached;
									}
								}
							}
						}
					}
					if (((actualDay - 1) != 0) && (actualDay - 1 <= weeken_hol)
							&& (!flg)) {
						isNonAttached = false;
						setAlertMessage("You must apply leave from"
								+ fromDateOfpreviousRow + " to "
								+ toDateOfCurrentRow);
						return isNonAttached;
					}// end of nested if				
				}

			}
		} catch (Exception e) {
			logger
					.error("Exception in checkNonConsecutiveLeaveWithIterator------"
							+ e);
		}
		return isNonAttached;
	}// end of checkNonConsecutiveLeaveWithIterator

	/**
	 * THIS METHOD IS USED FOR CHECKING NON CONSECUTIVE LEAVE WITH ITERATOR
	 * ACCORDING TO LEAVE POLICY	
	 * Please refer Leave Policy Document section 4.2.19
	 * @param bean
	 * @param leaveDtlStr
	 * @param rowNum
	 * @return boolean
	 */

	private boolean checkNonConsecutiveWithIterator(LeaveApplication bean,
			String[][] leaveDtlStr, int rowNum) {
		String policyCode = getLeavePolicyCode(bean.getEmpCode());
		boolean isNonAttached = true;
		try {
			if (rowNum > 0) {
				int varRowNum = rowNum;
				boolean flg = false;
				int actualDay = 0;
				int newRowNum = 0;
				/** One row of Leave Type,
				 * From Date, To Date will check its details according to the 
				 * Leave Policy with Previous Rows*/
				do {
					newRowNum = varRowNum - 1;
					String toDate = leaveDtlStr[rowNum][2];
					String fromDate = leaveDtlStr[(newRowNum)][3];
					String fromDateOfpreviousRow = leaveDtlStr[(newRowNum)][2];
					String toDateOfCurrentRow = leaveDtlStr[rowNum][3];

					Date firstToDate = new Date();
					Date firstFromDate = new Date();
					Date fromDateOfpreviousRowDate = new Date();
					Date toDateOfCurrentRowDate = new Date();
					SimpleDateFormat simpleFormat = new SimpleDateFormat(
							"dd-MM-yyyy");

					firstToDate = simpleFormat.parse(toDate);
					firstFromDate = simpleFormat.parse(fromDate);
					fromDateOfpreviousRowDate = simpleFormat
							.parse(fromDateOfpreviousRow);
					toDateOfCurrentRowDate = simpleFormat
							.parse(toDateOfCurrentRow);
					String select = "SELECT TO_DATE('" + toDate
							+ "','DD-MM-YYYY')-TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') from  DUAL";
					Object[][] dateDiff = getSqlModel().getSingleResult(select);
					actualDay = Integer
							.parseInt(String.valueOf(dateDiff[0][0]));

					/**According to the Policy Setting one Leave Type is cannot be 
					 * Combine with another */
					Object[][] nonConsData = (Object[][]) null;
					String nonConsQuery = "";
					nonConsQuery = " SELECT LEAVE_CANNOT_COMBINE  FROM HRMS_LEAVE_POLICY_DTL  WHERE LEAVE_CODE ="
							+ leaveDtlStr[rowNum][0]
							+ " AND LEAVE_POLICY_CODE=" + policyCode;
					nonConsData = getSqlModel().getSingleResult(nonConsQuery);
					String[] nonConsLeave = (String[]) null;
					if ((nonConsData != null) && (nonConsData.length > 0)) {
						nonConsLeave = String.valueOf(nonConsData[0][0]).split(
								",");
					}
					Object[][] rowObject = null;
					String query = " SELECT LEAVE_CANNOT_COMBINE  FROM HRMS_LEAVE_POLICY_DTL  WHERE LEAVE_CODE ="
							+ leaveDtlStr[newRowNum][0]
							+ " AND LEAVE_POLICY_CODE=" + policyCode;
					rowObject = getSqlModel().getSingleResult(query);
					String[] nonConsLeaveRow = (String[]) null;
					if ((rowObject != null) && (rowObject.length > 0)) {
						nonConsLeaveRow = String.valueOf(rowObject[0][0])
								.split(",");
						if ((nonConsLeaveRow != null)
								&& (nonConsLeaveRow.length > 0)) {
							for (int j = 0; j < nonConsLeaveRow.length; j++) {
								if (leaveDtlStr[(rowNum)][0]
										.equals(nonConsLeaveRow[j])) {
									int totalHol = 0;
									int weekenHol = 0;
									Object[][] holidayObj = (Object[][]) null;
									if (bean.getBrnHDayFlag().equals("true")) {
										String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH  WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
												+ bean.getEmpCode()
												+ ")"
												+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE >=TO_DATE('"
												+ fromDate
												+ "','DD-MM-YYYY') "
												+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE <=TO_DATE('"
												+ toDate + "','DD-MM-YYYY')";
										holidayObj = getSqlModel()
												.getSingleResult(holStr);
									} else {
										String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY WHERE HOLI_DATE >=TO_DATE('"
												+ fromDate
												+ "','DD-MM-YYYY') AND HOLI_DATE <=TO_DATE('"
												+ toDate + "','DD-MM-YYYY')";
										holidayObj = getSqlModel()
												.getSingleResult(holStr);
									}
									if (holidayObj != null) {
										totalHol = holidayObj.length;
									}
									/**Suppose User applied leave of current month
									 * and then in 2nd row user applied leave previous month
									 * the following code allowed this*/
									if (!firstFromDate.before(firstToDate)) {
										if (toDateOfCurrentRowDate
												.before(fromDateOfpreviousRowDate)) {
											fromDate = toDateOfCurrentRow;
											toDate = fromDateOfpreviousRow;
											flg = true;
										}
									}
									weekenHol = countNumberOfWeeklyOff(
											new Utility()
													.getCalanderDate(fromDate),
											new Utility()
													.getCalanderDate(toDate),
											holidayObj, bean.getEmpCode());
									int weeken_hol = weekenHol + totalHol;

									/**Each and every row check with other row */
									for (int k = 0; k <= rowNum; k++) {
										for (int l = 0; l < rowNum; l++) {
											String constFromStr = leaveDtlStr[k][2];
											String constToStr = leaveDtlStr[k][3];
											String varFromStr = leaveDtlStr[l][2];
											String varToStr = leaveDtlStr[l][3];
											Date constFromDate = new Date();
											Date constToDate = new Date();
											SimpleDateFormat dateFormat = new SimpleDateFormat(
													"dd-MM-yyyy");
											constFromDate = dateFormat
													.parse(constFromStr);
											constToDate = dateFormat
													.parse(constToStr);
											Date varFrmDate = new Date();
											Date varToDate = new Date();
											varFrmDate = dateFormat
													.parse(varFromStr);
											varToDate = dateFormat
													.parse(varToStr);
											if (k != l) {
												if ((!varFrmDate
														.after(constFromDate))
														|| (!varFrmDate
																.before(constToDate))) {
													if (!varToDate
															.before(constToDate))
														continue;
													if (!varToDate
															.after(constFromDate))
														continue;
												} else {
													isNonAttached = false;
													setAlertMessage("Leave is already applied"
															+ fromDateOfpreviousRow
															+ " to "
															+ toDateOfCurrentRow);
													return isNonAttached;
												}
											}
										}
									}
									if ((actualDay - 1 > 0)
											&& (actualDay - 1 <= weeken_hol)
											&& (!flg)) {
										isNonAttached = false;
										setAlertMessage("You must apply leave from"
												+ fromDateOfpreviousRow
												+ " to " + toDateOfCurrentRow);
										return isNonAttached;
									}

									if ((actualDay - 1 <= weeken_hol) && (!flg)) {
										isNonAttached = false;
										setAlertMessage("You can't apply leave from "
												+ fromDate + " to " + toDate);
										return isNonAttached;
									}
								}
								if (rowNum == leaveDtlStr.length - 1)
									continue;
							}
						}
					}
					varRowNum--;
				} while (varRowNum > 0);
			}
		} catch (Exception e) {
			logger
					.error("Exception in checkNonConsecutiveWithIterator--------- "
							+ e);
		}
		return isNonAttached;
	}// end of checkNonConsecutiveWithIterator

	/**
	 * THIS METHOD IS USED FOR CHECKING LEAVE BALANCE
	 * @param bean
	 * @param leaveMap
	 * @return boolean
	 */
	private boolean checkLeaveBalance(LeaveApplication bean,
			Map<String, String> leaveMap) {
		String policyCode = getLeavePolicyCode(bean.getEmpCode());
		try {
			final String leaveBalQuery = " SELECT  HRMS_LEAVE.LEAVE_ID,NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0),HRMS_LEAVE.LEAVE_NAME "
					+ " FROM HRMS_LEAVE  "
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE "
					+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
					+ policyCode
					+ ")"
					+ " LEFT JOIN  HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE "
					+ " AND HRMS_LEAVE_BALANCE.EMP_ID = "
					+ bean.getEmpCode()
					+ ") "
					+ "	WHERE  HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE ='Y' ORDER BY HRMS_LEAVE.LEAVE_ID ";
			final Object balanceObj[][] = getSqlModel().getSingleResult(
					leaveBalQuery);
			for (int i = 0; i < balanceObj.length; i++) {
				if (leaveMap.containsKey(String.valueOf(balanceObj[i][0]))) {
					double leaveAppDays = Double.parseDouble(leaveMap
							.get(String.valueOf(balanceObj[i][0])));
					double leaveBalance = Double.parseDouble(String
							.valueOf(balanceObj[i][1]));
					if (leaveBalance - leaveAppDays < 0) {
						setAlertMessage("You don't have sufficient leave balance of "
								+ String.valueOf(balanceObj[i][2]));
						return false;
					}// end of nested if
				}// end of if
			}// end of for loop
		} catch (Exception e) {
			logger.error("Exception in checkLeaveBalance--------- " + e);
		}
		return true;
	}// end of checkLeaveBalance

	/**
	 * THIS METHOD IS USED FOR COUNTING NUMBER OF WEEKLY OFF ACCORDING TO
	 * EMPLOYEE SHIFT
	 * 
	 * @param first
	 *            --take from date as GregorianCalendar date
	 * @param second---take
	 *            to date as GregorianCalendar date
	 * @param empId----is
	 *            employee id
	 * @return number of WeeklyOff specified in shift for an employee between 2
	 *         dates
	 */

	public int countNumberOfWeeklyOff(GregorianCalendar first,
			GregorianCalendar second, String empId) {
		int countDays = 0;
		try {
			Calendar currentcalendarday = first;
			Calendar lastcalendarday = second;
			final String queryWeeklyOff = "SELECT  NVL(SHIFT_WEEK_1,0),NVL( SHIFT_WEEK_2,0),NVL(SHIFT_WEEK_3,0),NVL(SHIFT_WEEK_4,0),NVL(SHIFT_WEEK_5,0), NVL(SHIFT_WEEK_6,0) "
					+ " FROM HRMS_SHIFT WHERE SHIFT_ID ="
					+ " (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empId + ")";
			final Object weeklyOffObj[][] = getSqlModel().getSingleResult(
					queryWeeklyOff);
			if (weeklyOffObj != null && weeklyOffObj.length > 0) {

				if (currentcalendarday.equals(lastcalendarday)) {
					countDays += weekenDays(currentcalendarday, weeklyOffObj);
				} // end of nested if
				else {
					while (!currentcalendarday.equals(lastcalendarday)) {
						countDays += weekenDays(currentcalendarday,
								weeklyOffObj);
						currentcalendarday.add(Calendar.DATE, 1);
					}// end of while loop
					if (currentcalendarday.equals(lastcalendarday)) {
						countDays += weekenDays(currentcalendarday,
								weeklyOffObj);
					}// end of if
				}// end of else
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in countNumberOfWeeklyOff--------- " + e);
		}
		return countDays;
	}// end of countNumberOfWeeklyOff

	/**
	 * THIS METHOD IS OVERIDE TO CHECK IS HOLIDAY COMING IN BETWEEN WEEKLY OFF
	 * 
	 * @param first
	 *            --take from date as GregorianCalendar date
	 * @param second---take
	 *            to date as GregorianCalendar date
	 * @param holiday--
	 *            holiday list
	 * @param empId----is
	 *            employee id
	 * @return --------number of WeeklyOff specified in shift for an employee
	 *         between 2 dates remove holidays if falls in weekend;
	 */

	public int countNumberOfWeeklyOff(GregorianCalendar first,
			GregorianCalendar second, Object holiday[][], String empId) {
		int countDays = 0;
		try {
			Calendar currentcalendarday = first;
			Calendar lastcalendarday = second;
			final String queryWeeklyOff = "SELECT  NVL(SHIFT_WEEK_1,0),NVL(SHIFT_WEEK_2,0),NVL(SHIFT_WEEK_3,0),NVL(SHIFT_WEEK_4,0),NVL(SHIFT_WEEK_5,0), NVL(SHIFT_WEEK_6,0) "
					+ " FROM HRMS_SHIFT WHERE SHIFT_ID ="
					+ " (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empId + ")";
			final Object weeklyOffObj[][] = getSqlModel().getSingleResult(
					queryWeeklyOff);
			if (weeklyOffObj != null && weeklyOffObj.length > 0) {

				if (currentcalendarday.equals(lastcalendarday)) {
					countDays += weekenDays(currentcalendarday, weeklyOffObj);
				}// end of nested if
				else {
					while (!currentcalendarday.equals(lastcalendarday)) {
						countDays += weekenDays(currentcalendarday,
								weeklyOffObj);
						currentcalendarday.add(Calendar.DATE, 1);
					}// end of while
					if (currentcalendarday.equals(lastcalendarday)) {
						countDays += weekenDays(currentcalendarday,
								weeklyOffObj);
					}// end of if
				}// end of else
				if (holiday != null && holiday.length > 0) {
					for (int i = 0; i < holiday.length; i++) {
						Calendar holidaycalendar = new Utility()
								.getCalanderDate(String.valueOf(holiday[i][0]));
						countDays -= weekenDays(holidaycalendar, weeklyOffObj);
					}// end of for loop
				}// end of nested if
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in countNumberOfWeeklyOff--------- " + e);
		}
		return countDays;
	}// end of countNumberOfWeeklyOff

	/**
	 * THIS METHOD IS USED FOR CALCULATING WEEKENDS
	 * 
	 * @param currentcalendarday
	 * @param weeklyOffObj------it
	 *            gives no of weekly off
	 * @return no of weekend
	 */

	public int weekenDays(Calendar currentcalendarday, Object weeklyOffObj[][]) {
		int countDays = 0;
		try {
			int DAY_OF_WEEK = currentcalendarday
					.get(java.util.Calendar.WEEK_OF_MONTH);
			String[] weekDays = null;
			for (int i = 0; i < weeklyOffObj[0].length; i++) {
				if (i + 1 == DAY_OF_WEEK) {
					weekDays = String.valueOf(weeklyOffObj[0][i]).split(",");
				}// end of if
			}// end of for loop
			if (weekDays != null) {
				for (String string : weekDays) {
					switch (Integer.parseInt(string)) {
					case 1:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 1)
							countDays++;
						break;
					case 2:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 2)
							countDays++;
						break;
					case 3:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 3)
							countDays++;
						break;
					case 4:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 4)
							countDays++;
						break;
					case 5:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 5)
							countDays++;
						break;
					case 6:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 6)
							countDays++;
						break;
					case 7:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 7)
							countDays++;
						break;
					default:
						break;
					}// end of switch loop
				}// end of for loop
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in weekenDays--------- " + e);
		}
		return countDays;
	}// end of weekenDays

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/**
	 * THIS METHOD IS USED FOR CHECKING ZERO BALANCE APPLICABLE ACCORDING TO
	 * LEAVE POLICY Please refer Leave Policy Document section 4.2.20
	 * 
	 * @param str
	 * @param leaveBean
	 * @return boolean
	 */
	public boolean isZeroBalApplicable(String str, LeaveApplication leaveBean) {
		String policyCode = getLeavePolicyCode(leaveBean.getEmpCode());

		final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
				+ " WHERE LEAVE_POLICY_CODE="
				+ policyCode
				+ " AND LEAVE_CODE="
				+ str;
		final Object[][] zeroBalance = getSqlModel().getSingleResult(
				zeroBlncQuery);
		if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
			return true;

		}// end of if
		else {
			return false;
		}// end of else

	}// end of isZeroBalApplicable

	/**
	 * This method is used for checking half day applicable accroding to leave
	 * policy setting for an employee. Please refer Leave Policy Document
	 * section 4.2.22
	 * 
	 * @param strCode
	 * @param leaveBean
	 * @return boolean
	 */
	public boolean isHalfDayApplicable(String strCode,
			LeaveApplication leaveBean) {
		final String policyCode = getLeavePolicyCode(leaveBean.getEmpCode());
		final String halfDayQuery = "SELECT LEAVE_IS_HALF_DAY  FROM HRMS_LEAVE_POLICY_DTL "
				+ " WHERE LEAVE_POLICY_CODE="
				+ policyCode
				+ " AND LEAVE_CODE="
				+ strCode;
		final Object[][] halfDayQueryObj = getSqlModel().getSingleResult(
				halfDayQuery);
		if (String.valueOf(halfDayQueryObj[0][0]).equals("Y")) {
			return true;

		}// end of if
		else {
			return false;
		}// end of else

	}// end of isHalfDayApplicable

	/**
	 * This method is used for getting leave policy code for an employee.
	 * 
	 * @param empId
	 * @return String
	 */
	public String getLeavePolicyCode(String empId) {
		String leavePolicyCode = "";
		try {

			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			leavePolicyCode = model.getLeavePolicy(empId);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getLeavePolicyCode----------" + e);
		}
		return leavePolicyCode;
	}

	/**
	 * This method is used for checking no of leaves to be taken for a
	 * particular leave type according to leave policy.
	 * 
	 * Please refer Leave Policy Document section 4.2.15
	 * 
	 * @param leaveCode
	 * @param empCode
	 * @return Object[][]
	 */
	public Object[][] checkForGrantedNoOfLeaves(String leaveCode, String empCode) {
		Object[][] grantedDaysObj = null;
		try {

			final String policyCode = getLeavePolicyCode(empCode);
			final String noOfDaysQuery = " SELECT NVL(LEAVE_GRANT_MIN_APPLY_DAYS,0) , NVL(LEAVE_GRANT_MAX_APPLY_DAYS,0) FROM HRMS_LEAVE_POLICY_DTL WHERE LEAVE_CODE="
					+ leaveCode
					+ " AND LEAVE_POLICY_CODE="
					+ policyCode
					+ " AND LEAVE_GRANT_MAX_APPLY_DAYS!=0 ";
			grantedDaysObj = getSqlModel().getSingleResult(noOfDaysQuery);
		} catch (Exception e) {
			logger.error("Exception in checkForGrantedNoOfLeaves---------" + e);
		}
		return grantedDaysObj;
	}

	/**
	 * This method is used for checking minimum granted leaves for a leave type
	 * according to leave policy
	 * 
	 * Please refer Leave Policy Document section 4.2.15
	 * 
	 * @param leaveCode
	 * @param empCode
	 * @return Object[][]
	 */

	public Object[][] checkForMinGrantedNoOfLeaves(String leaveCode,
			String empCode) {
		Object[][] grantedMinDaysObj = null;
		try {

			final String policyCode = getLeavePolicyCode(empCode);
			final String noOfDaysQuery = " SELECT NVL(LEAVE_GRANT_MIN_APPLY_DAYS,0) FROM HRMS_LEAVE_POLICY_DTL WHERE LEAVE_CODE="
					+ leaveCode
					+ " AND LEAVE_POLICY_CODE="
					+ policyCode
					+ " AND LEAVE_GRANT_MAX_APPLY_DAYS=0 ";

			grantedMinDaysObj = getSqlModel().getSingleResult(noOfDaysQuery);
		} catch (Exception e) {
			logger.error("Exception in checkForGrantedNoOfLeaves---------" + e);
		}
		return grantedMinDaysObj;
	}

	/**
	 * This method is used for checking comp off applicable for an employee
	 * according to comp off leave policy setting.
	 * 
	 * @param levCode
	 * @param leaveBean
	 * @return
	 */
	public boolean checkCompOffApplicable(String levCode,
			LeaveApplication leaveBean) {
		boolean result = false;
		try {

			final String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";

			final Object compOffObj[][] = getSqlModel().getSingleResult(
					compOffAppQuery);

			if (compOffObj != null && compOffObj.length > 0) {
				if (levCode.equals(String.valueOf(compOffObj[0][0]))) {
					leaveBean.setCompOffDays(String.valueOf(compOffObj[0][1]));
					result = true;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in checkCompOffApplicable--------------"
					+ e);
		}
		return result;
	}

	/**
	 * This method is used for setting comp off days
	 * 
	 * @param leaveBean
	 */
	public void setCompOffDays(LeaveApplication leaveBean) {
		try {
			String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";
			final Object compOffObj[][] = getSqlModel().getSingleResult(
					compOffAppQuery);
			if (compOffObj != null && compOffObj.length > 0) {
				leaveBean.setCompOffDays(String.valueOf(compOffObj[0][0]));
			}
		} catch (Exception e) {
			logger.error("Exception in setCompOffDays--------------" + e);
		}

	}

	/**
	 * This method is used for checking is advance leave balance allowed for an
	 * employee according to leave policy.
	 * 
	 * Please refer Leave Policy Document section 4.2.34
	 * 
	 * @param leaveCode
	 * @param leaveBean
	 * @return boolean
	 */
	public boolean isNegativeBalanceAllow(String leaveCode,
			LeaveApplication leaveBean) {

		boolean isnegativeBalAllow = false;

		try {
			final String policyCode = getLeavePolicyCode(leaveBean.getEmpCode());
			final String negativeBalAllowquery = " SELECT NVL(LEAVE_ADVANCE_UPTO_MTH,0) FROM HRMS_LEAVE_POLICY_DTL"
					+ " WHERE LEAVE_POLICY_CODE="
					+ policyCode
					+ " AND LEAVE_CODE=" + leaveCode;
			final Object[][] negativeBalAllowObj = getSqlModel()
					.getSingleResult(negativeBalAllowquery);
			if (negativeBalAllowObj != null && negativeBalAllowObj.length > 0) {
				if (!String.valueOf(negativeBalAllowObj[0][0]).equals("0")) {
					leaveBean.setNegativeAllowBal(String
							.valueOf(negativeBalAllowObj[0][0]));
					isnegativeBalAllow = true;
				}// end of if
			}
		} catch (Exception e) {
			logger.error("Exception in isNegativeBalanceAllow----------" + e);
		}
		return isnegativeBalAllow;

	}// end of isNegativeBalanceAllow

	/**
	 * This method is used for setting keep informed to list.
	 * 
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param leaveApplication
	 */
	public void setKeepInformed(String[] srNo, String[] empCode,
			String[] empName, LeaveApplication leaveApplication) {

		try {
			LeaveApplication levApp = new LeaveApplication();
			levApp.setKeepInformedEmpId(leaveApplication.getEmployeeId());
			levApp.setKeepInformedEmpName(leaveApplication.getEmployeeName());
			final ArrayList<LeaveApplication> leaveList = displayNewValueForKeepInformed(
					srNo, empCode, empName, leaveApplication);
			levApp.setSerialNo(String.valueOf(leaveList.size() + 1));// sr no
			leaveList.add(levApp);
			leaveApplication.setKeepInformedList(leaveList);
		} catch (Exception e) {
			logger.error("Exception in setKeepInformed----------" + e);
		}

	}

	/**
	 * This method is used for editing record
	 * 
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param leaveApplication2
	 * @return ArrayList<LeaveApplication>
	 */
	private ArrayList<LeaveApplication> displayNewValueForKeepInformed(
			String[] srNo, String[] empCode, String[] empName,
			LeaveApplication leaveApplication2) {
		ArrayList<LeaveApplication> leaveList = null;
		try {
			leaveList = new ArrayList<LeaveApplication>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication levApp = new LeaveApplication();
					levApp.setKeepInformedEmpId(empCode[i]);
					levApp.setKeepInformedEmpName(empName[i]);
					levApp.setSerialNo(srNo[i]);
					leaveList.add(levApp);

				}

			}
		} catch (Exception e) {
			logger
					.error("Exception in displayNewValueForKeepInformed----------"
							+ e);
		}
		return leaveList;
	}

	/**
	 * This method is used for displaying keep informed to employee.
	 * 
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param levAppBean
	 */

	public void displayIteratorValueForKeepInformed(String[] srNo,
			String[] empCode, String[] empName, LeaveApplication levAppBean) {
		try {
			final ArrayList<LeaveApplication> leaveList = new ArrayList<LeaveApplication>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication levApp = new LeaveApplication();
					levApp.setKeepInformedEmpId(empCode[i]);
					levApp.setKeepInformedEmpName(empName[i]);
					levApp.setSerialNo(srNo[i]);// sr no
					leaveList.add(levApp);
				}
				levAppBean.setKeepInformedList(leaveList);
			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForKeepInformed----------"
							+ e);
		}
	}

	/**
	 * This method is used for getting saved keep informed to employee
	 * 
	 * @param leaveApplication
	 * @return String
	 */
	public String getKeepInformedSavedRecord(LeaveApplication leaveApplication) {
		String isKeepInfoLogin = "N";
		try {
			final String selectQuery = " SELECT LEAVE_KEEP_INFORMED FROM "
					+ " HRMS_LEAVE_HDR " + " WHERE LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode() + " AND EMP_ID="
					+ leaveApplication.getEmpCode();
			final Object selectDataObj[][] = getSqlModel().getSingleResult(
					selectQuery);
			String str = "";
			String query = "";
			if (selectDataObj != null && selectDataObj.length > 0) {
				str = String.valueOf(selectDataObj[0][0]);

				if (str.length() > 0) {
					query = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
							+ " FROM HRMS_EMP_OFFC "
							+ "  WHERE  EMP_ID IN("
							+ str + ")";
				}
				final Object result[][] = getSqlModel().getSingleResult(query);

				final ArrayList<LeaveApplication> leaveList = new ArrayList<LeaveApplication>();
				if (result != null) {

					for (int i = 0; i < result.length; i++) {
						LeaveApplication levApp = new LeaveApplication();
						levApp.setKeepInformedEmpId(String
								.valueOf(result[i][1]));

						if (leaveApplication.getUserEmpId().equals(
								levApp.getKeepInformedEmpId())) {
							isKeepInfoLogin = "Y";
						}
						levApp.setKeepInformedEmpName(String
								.valueOf(result[i][0]));
						levApp.setSerialNo(String.valueOf(i + 1));// sr no
						leaveList.add(levApp);
					}
					leaveApplication.setKeepInformedList(leaveList);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getKeepInformedSavedRecord----------"
					+ e);
		}
		return isKeepInfoLogin;
	}

	/**
	 * This method is used for saving keep informed to employee list.
	 * 
	 * @param empCode
	 * @param leaveBean
	 */
	public void saveKeepInformedList(String empCode[],
			LeaveApplication leaveBean) {

		try {
			String empId = "";
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					if (i < empCode.length - 1) {
						empId += empCode[i] + ",";
					} else {
						empId = empId + empCode[i];
					}
				}
			}
			final String updateQuery = "  UPDATE "
					+ " HRMS_LEAVE_HDR SET LEAVE_KEEP_INFORMED=?  WHERE LEAVE_APPL_CODE=? AND EMP_ID=? ";
			final Object updateObj[][] = new Object[1][3];
			updateObj[0][0] = empId;
			updateObj[0][1] = leaveBean.getLeaveCode();
			updateObj[0][2] = leaveBean.getEmpCode();
			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			logger.error("Exception in saveKeepInformedList----------" + e);
		}

	}

	/**
	 * This method is used for removing keep informed to employee list.
	 * 
	 * @param serialNo
	 * @param empCode
	 * @param empName
	 * @param leaveApplication
	 */
	public void removeKeepInformedData(String[] serialNo, String[] empCode,
			String[] empName, LeaveApplication leaveApplication) {
		try {
			final ArrayList<Object> tableList = new ArrayList<Object>();
			if (serialNo != null) {
				for (int i = 0; i < serialNo.length; i++) {
					LeaveApplication bean = new LeaveApplication();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setKeepInformedEmpId(empCode[i]);
					bean.setKeepInformedEmpName(empName[i]);
					tableList.add(bean);

				}
				tableList.remove(Integer.parseInt(leaveApplication
						.getCheckRemove()) - 1);

			}

			leaveApplication.setKeepInformedList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformedData------" + e);
		}

	}

	public String logic(int level) {
		String str = "";
		final ReportingModel model1 = new ReportingModel();
		model1.initiate(context, session);
		final Object[][] empFlow = model1.generateEmpFlow("1212307", "Leave",
				level);
		if (empFlow != null && empFlow.length > 0) {
			str = String.valueOf(empFlow[0][0]);
		}
		return str;
	}

	/**
	 * This method is used for setting approver list for leave application
	 * 
	 * @param leaveApplication
	 * @param empFlow
	 */
	public void setApproverData(LeaveApplication leaveApplication,
			Object[][] empFlow) {
		try {
			if (empFlow != null && empFlow.length > 0) {
				final Object[][] approverDataObj = new Object[empFlow.length][1];
				for (int i = 0; i < empFlow.length; i++) {

					final String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					final Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						LeaveApplication leaveBean = new LeaveApplication();
						leaveBean.setApproverName(String
								.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)
								+ "-Approver";
						leaveBean.setSrNoIterator(srNo);
						arrList.add(leaveBean);
					}
					leaveApplication.setApproverList(arrList);
				}

			}
		} catch (Exception e) {
			logger.error("Exception in setApproverData------" + e);
			e.printStackTrace();
		}
	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value
	 *            value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		final int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if

		final int tenRemainder = value % 10;

		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	/**
	 * This method is used for getting data for starting list page.
	 * 
	 * @param leaveApplication
	 * @param request
	 * @param empId
	 */
	public void getAllTypeOfApplications(LeaveApplication leaveApplication,
			HttpServletRequest request, String empId) {

		Object draftParam[] = null;
		Object returnParam[] = null;

		try {

			final String query = "   SELECT  HRMS_EMP_OFFC.EMP_TOKEN,  "
					+ " 	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
					+ "	TO_CHAR(HRMS_LEAVE_HDR.LEAVE_APPL_DATE,'DD-MM-YYYY'),HRMS_LEAVE_HDR.EMP_ID,HRMS_LEAVE_HDR.LEAVE_APPL_CODE,HRMS_LEAVE_HDR.LEAVE_STATUS "
					+ "	 FROM HRMS_LEAVE_HDR "
					+ "	  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID  = HRMS_LEAVE_HDR.EMP_ID  "
					+ "	 WHERE HRMS_LEAVE_HDR.LEAVE_STATUS IN('P','C') AND HRMS_LEAVE_HDR.EMP_ID="
					+ empId + "	 ORDER BY LEAVE_APPL_CODE DESC ";

			Object submitData[][] = getSqlModel().getSingleResult(query);

			draftParam = new Object[] { "D", empId };
			returnParam = new Object[] { "B", empId };
			Object draftData[][] = getSqlModel().getSingleResult(getQuery(19),
					draftParam);

			Object returnData[][] = getSqlModel().getSingleResult(getQuery(19),
					returnParam);
			if (draftData != null && draftData.length > 0) {
				final ArrayList DraftList = new ArrayList();
				for (int i = 0; i < draftData.length; i++) {
					LeaveApplication leaveBeanDraft = new LeaveApplication();
					leaveBeanDraft.setLevEmpToken(String
							.valueOf(draftData[i][0]));
					leaveBeanDraft.setLevEmpName(String
							.valueOf(draftData[i][1]));
					leaveBeanDraft.setLevAppDate(String
							.valueOf(draftData[i][2]));
					leaveBeanDraft.setDraftEmpId(String
							.valueOf(draftData[i][3]));
					leaveBeanDraft.setDraftLeaveAppNo(String
							.valueOf(draftData[i][4]));
					leaveBeanDraft.setLevAppStatus(String
							.valueOf(draftData[i][5]));
					DraftList.add(leaveBeanDraft);
				}
				leaveApplication.setDraftList(DraftList);
			}
			if (submitData != null && submitData.length > 0) {
				final ArrayList submitList = new ArrayList();
				for (int i = 0; i < submitData.length; i++) {
					LeaveApplication leaveSubmitBean = new LeaveApplication();
					leaveSubmitBean.setLevEmpToken(String
							.valueOf(submitData[i][0]));
					leaveSubmitBean.setLevEmpName(String
							.valueOf(submitData[i][1]));
					leaveSubmitBean.setLevAppDate(String
							.valueOf(submitData[i][2]));
					leaveSubmitBean.setSubmitEmpId(String
							.valueOf(submitData[i][3]));
					leaveSubmitBean.setSubmitLeaveAppNo(String
							.valueOf(submitData[i][4]));
					leaveSubmitBean.setLevAppStatus(String
							.valueOf(submitData[i][5]));
					submitList.add(leaveSubmitBean);
				}
				leaveApplication.setSubmitList(submitList);
			}
			if (returnData != null && returnData.length > 0) {
				final ArrayList returnList = new ArrayList();
				for (int i = 0; i < returnData.length; i++) {
					LeaveApplication leaveReturnBean = new LeaveApplication();
					leaveReturnBean.setLevEmpToken(String
							.valueOf(returnData[i][0]));
					leaveReturnBean.setLevEmpName(String
							.valueOf(returnData[i][1]));
					leaveReturnBean.setLevAppDate(String
							.valueOf(returnData[i][2]));
					leaveReturnBean.setReturnEmpId(String
							.valueOf(returnData[i][3]));
					leaveReturnBean.setReturnLeaveAppNo(String
							.valueOf(returnData[i][4]));
					leaveReturnBean.setLevAppStatus(String
							.valueOf(returnData[i][5]));
					returnList.add(leaveReturnBean);
				}
				leaveApplication.setReturnList(returnList);
			}
		} catch (Exception e) {
			logger.error("Exception in getAllTypeOfApplications------" + e);
			e.printStackTrace();
		}

	}

	/**
	 * This method is used for getting data for approved leave application list.
	 * 
	 * @param bean
	 * @param request
	 * @param empId
	 */
	public void getApprovedList(LeaveApplication bean,
			HttpServletRequest request, String empId) {

		try {

			Object approvedParam[] = null;
			approvedParam = new Object[] { "A", empId };
			final Object approvedData[][] = getSqlModel().getSingleResult(
					getQuery(19), approvedParam);

			final String[] pageIndexApproved = Utility.doPaging(bean
					.getMyPage(), approvedData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				bean.setMyPage("1");

			Object cancelledApprovedParam[] = null;
			cancelledApprovedParam = new Object[] { "X", empId };
			Object cancelledApprovedData[][] = getSqlModel().getSingleResult(
					getQuery(19), cancelledApprovedParam);

			final String[] pageIndexCancelApproved = Utility.doPaging(bean
					.getMyPageCancelApproved(), cancelledApprovedData.length,
					20);
			if (pageIndexApproved == null) {
				pageIndexCancelApproved[0] = "0";
				pageIndexCancelApproved[1] = "20";
				pageIndexCancelApproved[2] = "1";
				pageIndexCancelApproved[3] = "1";
				pageIndexCancelApproved[4] = "";
			}

			request.setAttribute("totalPageCancelApproved", Integer
					.parseInt(String.valueOf(pageIndexCancelApproved[2])));
			request.setAttribute("PageNoCancelApproved", Integer
					.parseInt(String.valueOf(pageIndexCancelApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				bean.setMyPageCancelApproved("1");

			if (approvedData != null && approvedData.length > 0) {
				bean.setApproveLength("true");
				final ArrayList approvedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					LeaveApplication leaveApprovedBean = new LeaveApplication();
					leaveApprovedBean.setLevEmpToken(String
							.valueOf(approvedData[i][0]));
					leaveApprovedBean.setLevEmpName(String
							.valueOf(approvedData[i][1]));
					leaveApprovedBean.setLevAppDate(String
							.valueOf(approvedData[i][2]));
					leaveApprovedBean.setApprovedEmpId(String
							.valueOf(approvedData[i][3]));
					leaveApprovedBean.setApprovedLeaveAppNo(String
							.valueOf(approvedData[i][4]));
					leaveApprovedBean.setLevAppStatus(String
							.valueOf(approvedData[i][5]));
					approvedList.add(leaveApprovedBean);
				}
				bean.setApprovedList(approvedList);
			}

			if (cancelledApprovedData != null
					&& cancelledApprovedData.length > 0) {
				bean.setApproveCancelLength("true");
				final ArrayList cancelledApprovedList = new ArrayList();

				for (int i = Integer.parseInt(pageIndexCancelApproved[0]); i < Integer
						.parseInt(pageIndexCancelApproved[1]); i++) {
					LeaveApplication leaveCancelledApprovedBean = new LeaveApplication();
					leaveCancelledApprovedBean.setLevEmpToken(String
							.valueOf(cancelledApprovedData[i][0]));
					leaveCancelledApprovedBean.setLevEmpName(String
							.valueOf(cancelledApprovedData[i][1]));
					leaveCancelledApprovedBean.setLevAppDate(String
							.valueOf(cancelledApprovedData[i][2]));
					leaveCancelledApprovedBean.setCancelledApprovedEmpId(String
							.valueOf(cancelledApprovedData[i][3]));
					leaveCancelledApprovedBean
							.setCancelledApprovedLeaveAppNo(String
									.valueOf(cancelledApprovedData[i][4]));
					leaveCancelledApprovedBean.setLevAppStatus(String
							.valueOf(cancelledApprovedData[i][5]));
					cancelledApprovedList.add(leaveCancelledApprovedBean);
				}
				bean.setCancelledApprovedList(cancelledApprovedList);
			}

		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
			e.printStackTrace();
		}

	}

	/**
	 * This method is used for getting data for cancelled leave application
	 * list.
	 * 
	 * @param leaveApplication
	 * @param request
	 * @param empId
	 */
	public void getCancelledList(LeaveApplication leaveApplication,
			HttpServletRequest request, String empId) {

		try {
			Object cancelledParam[] = null;
			cancelledParam = new Object[] { "N", empId };
			final Object cancelledData[][] = getSqlModel().getSingleResult(
					getQuery(19), cancelledParam);

			final String[] pageIndexCancelled = Utility.doPaging(
					leaveApplication.getMyPageCancelled(),
					cancelledData.length, 20);
			if (pageIndexCancelled == null) {
				pageIndexCancelled[0] = "0";
				pageIndexCancelled[1] = "20";
				pageIndexCancelled[2] = "1";
				pageIndexCancelled[3] = "1";
				pageIndexCancelled[4] = "";
			}

			request.setAttribute("totalPageCancelled", Integer.parseInt(String
					.valueOf(pageIndexCancelled[2])));
			request.setAttribute("PageNoCancelled", Integer.parseInt(String
					.valueOf(pageIndexCancelled[3])));
			if (pageIndexCancelled[4].equals("1"))
				leaveApplication.setMyPageCancelled("1");

			if (cancelledData != null && cancelledData.length > 0) {

				leaveApplication.setCancelledLength("true");

				final ArrayList cancelledList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexCancelled[0]); i < Integer
						.parseInt(pageIndexCancelled[1]); i++) {

					LeaveApplication leaveCancelledBean = new LeaveApplication();
					leaveCancelledBean.setLevEmpToken(String
							.valueOf(cancelledData[i][0]));
					leaveCancelledBean.setLevEmpName(String
							.valueOf(cancelledData[i][1]));
					leaveCancelledBean.setLevAppDate(String
							.valueOf(cancelledData[i][2]));
					leaveCancelledBean.setCancelledEmpId(String
							.valueOf(cancelledData[i][3]));
					leaveCancelledBean.setCancelledLeaveAppNo(String
							.valueOf(cancelledData[i][4]));
					leaveCancelledBean.setLevAppStatus(String
							.valueOf(cancelledData[i][5]));
					cancelledList.add(leaveCancelledBean);
				}
				leaveApplication.setCancelledList(cancelledList);
			}
		} catch (Exception e) {
			logger.error("Exception in getCancelledList------" + e);
		}
	}

	/**
	 * This method is used for getting data for rejected leave application list.
	 * 
	 * @param bean
	 * @param request
	 * @param empId
	 */
	public void getRejectedList(LeaveApplication bean,
			HttpServletRequest request, String empId) {

		try {
			Object rejectedParam[] = null;
			rejectedParam = new Object[] { "R", empId };

			Object approvedCancelrejectedParam[] = null;
			approvedCancelrejectedParam = new Object[] { "Z", empId };

			final Object rejectedData[][] = getSqlModel().getSingleResult(
					getQuery(19), rejectedParam);

			final Object approvedCancelrejectedData[][] = getSqlModel()
					.getSingleResult(getQuery(19), approvedCancelrejectedParam);

			final String[] pageIndexRejected = Utility.doPaging(bean
					.getMyPageRejected(), rejectedData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}

			request.setAttribute("totalPageRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("PageNoRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				bean.setMyPageRejected("1");

			final String[] pageIndexCancelReject = Utility.doPaging(bean
					.getMyPageCancelRejected(),
					approvedCancelrejectedData.length, 20);
			if (pageIndexCancelReject == null) {
				pageIndexCancelReject[0] = "0";
				pageIndexCancelReject[1] = "20";
				pageIndexCancelReject[2] = "1";
				pageIndexCancelReject[3] = "1";
				pageIndexCancelReject[4] = "";
			}

			request.setAttribute("totalPageCancelRejected", Integer
					.parseInt(String.valueOf(pageIndexCancelReject[2])));
			request.setAttribute("PageNoCancelRejected", Integer
					.parseInt(String.valueOf(pageIndexCancelReject[3])));
			if (pageIndexCancelReject[4].equals("1"))
				bean.setMyPageCancelRejected("1");

			if (rejectedData != null && rejectedData.length > 0) {

				bean.setRejectedLength("true");
				final ArrayList rejectedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {

					LeaveApplication leaveRejectedBean = new LeaveApplication();
					leaveRejectedBean.setLevEmpToken(String
							.valueOf(rejectedData[i][0]));
					leaveRejectedBean.setLevEmpName(String
							.valueOf(rejectedData[i][1]));
					leaveRejectedBean.setLevAppDate(String
							.valueOf(rejectedData[i][2]));
					leaveRejectedBean.setRejectedEmpId(String
							.valueOf(rejectedData[i][3]));
					leaveRejectedBean.setRejectedLeaveAppNo(String
							.valueOf(rejectedData[i][4]));
					leaveRejectedBean.setLevAppStatus(String
							.valueOf(rejectedData[i][5]));
					rejectedList.add(leaveRejectedBean);

				}
				bean.setRejectedList(rejectedList);
			}

			if (approvedCancelrejectedData != null
					&& approvedCancelrejectedData.length > 0) {
				bean.setRejectedCancelLength("true");
				final ArrayList appCanrejectedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexCancelReject[0]); i < Integer
						.parseInt(pageIndexCancelReject[1]); i++) {
					LeaveApplication leaveAppCanRejBean = new LeaveApplication();
					leaveAppCanRejBean.setLevEmpToken(String
							.valueOf(approvedCancelrejectedData[i][0]));
					leaveAppCanRejBean.setLevEmpName(String
							.valueOf(approvedCancelrejectedData[i][1]));
					leaveAppCanRejBean.setLevAppDate(String
							.valueOf(approvedCancelrejectedData[i][2]));
					leaveAppCanRejBean.setAppCanrejectedEmpId(String
							.valueOf(approvedCancelrejectedData[i][3]));
					leaveAppCanRejBean.setAppCanrejectedLeaveAppNo(String
							.valueOf(approvedCancelrejectedData[i][4]));
					leaveAppCanRejBean.setLevAppStatus(String
							.valueOf(approvedCancelrejectedData[i][5]));
					appCanrejectedList.add(leaveAppCanRejBean);
				}
				bean.setApproveCancelrejectList(appCanrejectedList);
			}
		} catch (Exception e) {
			logger.error("Exception in getRejectedList------" + e);
		}

	}

	/**
	 * This method is used for updating leave application status for send back
	 * application.
	 * 
	 * @param leaveApplication
	 * @return boolean
	 */
	public boolean sentBackApp(LeaveApplication leaveApplication) {

		boolean result = false;
		try {
			String updateHdrStatusQuery = "UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS='B',LEAVE_COMMENTS='"
					+ leaveApplication.getComments()
					+ "' WHERE LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode();
			result = getSqlModel().singleExecute(updateHdrStatusQuery);
			final String updateDtlStatusQuery = "UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS='B' WHERE LEAVE_APPLICATION_CODE="
					+ leaveApplication.getLeaveCode();
			result = getSqlModel().singleExecute(updateDtlStatusQuery);
			final String updateDtlHistoryStatusQuery = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS='B' WHERE LEAVE_APPLICATION_CODE="
					+ leaveApplication.getLeaveCode();
			result = getSqlModel().singleExecute(updateDtlHistoryStatusQuery);
		} catch (Exception e) {
			logger.error("Exception in sentBackApp------" + e);
		}
		return result;

	}

	/**
	 * This method is used for getting data for approved cancelled leave
	 * application .
	 * 
	 * @param leaveApplication
	 * @param empFlow
	 * @return boolean
	 */
	public boolean cancelApprovedApplication(LeaveApplication leaveApplication,
			Object[][] empFlow) {

		boolean result = false;
		try {
			if (empFlow != null && empFlow.length > 0) {
				final String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='C',LEAVE_LEVEL=1 ,APPROVED_BY="
						+ String.valueOf(empFlow[0][0])
						+ "WHERE LEAVE_APPL_CODE ="
						+ leaveApplication.getLeaveCode() + " ";
				result = getSqlModel().singleExecute(updateHdr);
				final String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'C'  "
						+ " WHERE LEAVE_APPLICATION_CODE="
						+ leaveApplication.getLeaveCode() + " ";
				result = getSqlModel().singleExecute(updateDtl);
				final String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'C'  "
						+ " WHERE LEAVE_APPLICATION_CODE="
						+ leaveApplication.getLeaveCode() + " ";
				result = getSqlModel().singleExecute(updateDtlHistory);
			}

		} catch (Exception e) {
			logger.error("Exception in cancelApprovedApplication------" + e);
		}
		return result;
	}

	/**
	 * This method is used for updating leave balance for cancelling pending
	 * leave application.
	 * 
	 * @param leave
	 * @return boolean
	 */
	public boolean cancelPendingApplication(LeaveApplication leave) {

		boolean result = false;
		try {
			final String policyCode = getLeavePolicyCode(leave.getEmpCode());
			String balQuery = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE="
					+ leave.getLeaveCode()
					+ " ";
			final Object[][] balObj = getSqlModel().getSingleResult(balQuery);
			/**
			 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE
			 * APPLICABLE LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
			 */
			for (int j = 0; j < balObj.length; j++) {
				final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][3]);
				final Object[][] zeroBalance = getSqlModel().getSingleResult(
						zeroBlncQuery);
				if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
					balObj[j][0] = "0"; // leave days set to be zero

				}// end of if
				else {
					balObj[j][0] = balObj[j][0];
				}// end of else
			}// end of for loop
			if (balObj.length > 0) {

				balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? ,LEAVE_ONHOLD=CASE WHEN LEAVE_ONHOLD<=0 THEN 0 ELSE NVL(LEAVE_ONHOLD,0) - ? END "
						+ " WHERE EMP_ID =? AND LEAVE_CODE =?";
				/*
				 * 
				 * balQuery = " UPDATE HRMS_LEAVE_BALANCE SET
				 * LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE +
				 * ?,LEAVE_ONHOLD=NVL(LEAVE_ONHOLD,0)-? " + " WHERE EMP_ID =?
				 * AND LEAVE_CODE =?";
				 */
				result = getSqlModel().singleExecute(balQuery, balObj);
				if (result) {

					final String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='N' WHERE LEAVE_APPL_CODE ="
							+ leave.getLeaveCode() + " ";
					result = getSqlModel().singleExecute(updateHdr);
					final String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'N'  "
							+ " WHERE LEAVE_APPLICATION_CODE="
							+ leave.getLeaveCode() + " ";
					result = getSqlModel().singleExecute(updateDtl);
					final String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'N'  "
							+ " WHERE LEAVE_APPLICATION_CODE="
							+ leave.getLeaveCode() + " ";
					result = getSqlModel().singleExecute(updateDtlHistory);

					final String updateCompOffRecordQuery = "  UPDATE HRMS_EXTRAWORK_APPL_DTL SET EXTRAWORK_IS_LEAVE_APPLIED='N',EXTRAWORK_LEAVE_APPL_CODE='', "
							+ " EXTRAWORK_LEAVE_FROMDATE='',EXTRAWORK_LEAVE_TODATE=''"
							+ " WHERE EXTRAWORK_LEAVE_APPL_CODE="
							+ leave.getLeaveCode();

					getSqlModel().singleExecute(updateCompOffRecordQuery);

				}// end of nested if
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in cancelPendingApplication------" + e);
		}
		if (result) {
			updateCancelIsprocessApplicationStatus(leave.getLeaveCode());
		}
		return result;
	} // end of cancelPendingApplication

	/**
	 * This method is used for displaying uploaded proof.
	 * 
	 * @param srNo
	 * @param proofName
	 * @param proofFileName
	 * @param leaveApplication
	 */
	public void displayIteratorValueForUploadProof(String[] srNo,
			String[] proofName, String[] proofFileName,
			LeaveApplication leaveApplication) {

		try {
			final ArrayList<LeaveApplication> proofList = new ArrayList<LeaveApplication>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication levApp = new LeaveApplication();
					levApp.setProofName(proofName[i]);
					levApp.setProofFileName(proofFileName[i]);
					levApp.setSerialNo(srNo[i]);// sr no
					proofList.add(levApp);
				}
				leaveApplication.setProofList(proofList);

			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForUploadProof------"
							+ e);
		}

	}

	/**
	 * This method is used for setting proof list for application
	 * 
	 * @param srNo
	 * @param proofName
	 * @param proofFileName
	 * @param leaveApplication
	 */
	public void setProofList(String[] srNo, String[] proofName,
			String[] proofFileName, LeaveApplication leaveApplication) {
		try {
			LeaveApplication levApp = new LeaveApplication();
			levApp.setProofName(leaveApplication.getUploadFileName());
			levApp.setProofFileName(leaveApplication.getUserUploadFileName());
			final ArrayList<LeaveApplication> proofList = displayNewValueProofList(
					srNo, proofName, proofFileName, leaveApplication);
			levApp.setProofSrNo(String.valueOf(proofList.size() + 1));// sr no
			proofList.add(levApp);
			leaveApplication.setProofList(proofList);
		} catch (Exception e) {
			logger.error("Exception in setProofList------" + e);
		}

	}

	/**
	 * This method is used for editing proof list and display new proof value.
	 * 
	 * @param srNo
	 * @param proofName
	 * @param proofFileName
	 * @param leaveApplication
	 * @return ArrayList<LeaveApplication>
	 */
	private ArrayList<LeaveApplication> displayNewValueProofList(String[] srNo,
			String[] proofName, String[] proofFileName,
			LeaveApplication leaveApplication) {
		ArrayList<LeaveApplication> proofList = null;
		try {
			proofList = new ArrayList<LeaveApplication>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication levApp = new LeaveApplication();
					levApp.setProofName(proofName[i]);
					levApp.setProofSrNo(srNo[i]);
					levApp.setProofFileName(proofFileName[i]);
					proofList.add(levApp);

				}

			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueProofList------" + e);
		}
		return proofList;
	}

	/**
	 * This method is used for removing uploaded file
	 * 
	 * @param srNo
	 * @param proofName
	 * @param proofFileName
	 * @param leaveApplication
	 */
	public void removeUploadFile(String[] srNo, String[] proofName,
			String[] proofFileName, LeaveApplication leaveApplication) {
		try {
			final ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication bean = new LeaveApplication();
					bean.setProofSrNo(String.valueOf(i + 1));
					bean.setProofName(proofName[i]);
					bean.setProofFileName(proofFileName[i]);
					tableList.add(bean);
				}
				tableList.remove(Integer.parseInt(leaveApplication
						.getCheckRemoveUpload()) - 1);

			}

			leaveApplication.setProofList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile------" + e);
		}

	}

	/**
	 * This method is used to check is proof required for particular leave type
	 * according to leave policy.
	 * 
	 * Please refer Leave Policy Document section 4.2.25
	 * 
	 * @param str
	 * @param leaveApplication
	 * @return String
	 */
	public String isProofRequiredApplicable(String str,
			LeaveApplication leaveApplication) {

		String isProofApp = "N";
		try {

			final String policyCode = getLeavePolicyCode(leaveApplication
					.getEmpCode());
			final String proofRequired = " SELECT LEAVE_IS_PROOF_REQUIRED FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE LEAVE_CODE="
					+ str
					+ " AND LEAVE_POLICY_CODE="
					+ policyCode;
			final Object[][] proofRequiredObj = getSqlModel().getSingleResult(
					proofRequired);
			if (proofRequiredObj != null && proofRequiredObj.length > 0) {
				isProofApp = String.valueOf(proofRequiredObj[0][0]);
			}
		} catch (Exception e) {
			logger.error("Exception in isProofRequiredApplicable------" + e);
		}
		return isProofApp;
	}

	/**
	 * THis method is used for setting approver comments.
	 * 
	 * @param leaveApplication
	 */
	public void setApproverComments(LeaveApplication leaveApplication) {

		try {
			final String approverCommentQuery = " SELECT EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' '),TO_CHAR(LEAVE_ASSESS_DATE,'DD-MM-YYYY'), "
					+ " DECODE(LEAVE_STATUS,'D','Draft','P','Pending','B','Sent Back','A','Approved','R','Rejected','N','Cancelled','F','Forwarded','C','Applied For Cancellation','X','Cancellation Approved','Z','Cancellation Rejected'),LEAVE_COMMENTS  "
					+ " FROM HRMS_LEAVE_PATH "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_PATH.LEAVE_ASSESED_BY= HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE  HRMS_LEAVE_PATH.LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode()
					+ " ORDER BY LEAVE_PATH_ID DESC ";
			final Object approverCommentObj[][] = getSqlModel()
					.getSingleResult(approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				final ArrayList arrList = new ArrayList();
				for (int i = 0; i < approverCommentObj.length; i++) {
					LeaveApplication leaveBean = new LeaveApplication();
					leaveBean.setPrevApproverID(checkNull(String
							.valueOf(approverCommentObj[i][0])));
					leaveBean.setPrevApproverName(checkNull(String
							.valueOf(approverCommentObj[i][1])));
					leaveBean.setPrevApproverDate(checkNull(String
							.valueOf(approverCommentObj[i][2])));
					leaveBean.setPrevApproverStatus(checkNull(String
							.valueOf(approverCommentObj[i][3])));
					leaveBean.setPrevApproverComment(checkNull(String
							.valueOf(approverCommentObj[i][4])));
					/*
					 * String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
					 * leaveBean.setAppSrNo(srNo);
					 */
					arrList.add(leaveBean);
				}
				leaveApplication.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}

	}

	/**
	 * This method is used for setting approver comment list
	 * 
	 * @param srNo
	 * @param approverId
	 * @param approverName
	 * @param approvedDate
	 * @param approvedComments
	 * @param prevApproverStatus
	 * @param leaveApplication
	 */
	public void setApproverCommentList(String[] srNo, String[] approverId,
			String[] approverName, String[] approvedDate,
			String[] approvedComments, String[] prevApproverStatus,
			LeaveApplication leaveApplication) {

		try {
			if (approverName != null && approverName.length > 0) {
				final ArrayList arrList = new ArrayList();
				for (int i = 0; i < srNo.length; i++) {
					LeaveApplication leaveBean = new LeaveApplication();
					leaveBean.setPrevApproverID(approverId[i]);
					leaveBean.setPrevApproverName(approverName[i]);
					leaveBean.setPrevApproverDate(approvedDate[i]);
					leaveBean.setPrevApproverComment(approvedComments[i]);
					leaveBean.setPrevApproverStatus(prevApproverStatus[i]);
					/*
					 * String serialNo = i + 1 + getOrdinalFor(i + 1) +
					 * "-Approver"; leaveBean.setAppSrNo(serialNo);
					 */
					arrList.add(leaveBean);
				}
				leaveApplication.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverCommentList------" + e);
		}
	}

	/**
	 * This method is used for checking non consecutive leave with save
	 * record(DB).
	 * 
	 * @param bean
	 * @param leaveDtlStr
	 * @param rowNum
	 * @return boolean
	 */
	private boolean checkNonConsecutiveLeaveWithDB(LeaveApplication bean,
			String[][] leaveDtlStr, int rowNum) {
		boolean isConsecutive = true;
		try {
			int totalHol = 0;
			int weekenHol = 0;
			int actualDay = 0;
			int weeken_hol = 0;
			String previousLeaveQuery = "";
			final String policyCode = getLeavePolicyCode(bean.getEmpCode());
			final String holidayFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_CODE"
					+ " FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE  LEAVE_POLICY_CODE="
					+ policyCode
					+ "  AND (LEAVE_HOLIDAY_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
					+ leaveDtlStr[rowNum][0] + ")";
			final Object[][] holidayCheckObj = getSqlModel().getSingleResult(
					holidayFlag);
			final String weekFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_WEEKOFF_INCLUDE_FLAG,LEAVE_CODE"
					+ " FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE  LEAVE_POLICY_CODE="
					+ policyCode
					+ "  AND (LEAVE_WEEKOFF_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
					+ leaveDtlStr[rowNum][0] + ")";
			final Object[][] weeklyCheckObj = getSqlModel().getSingleResult(
					weekFlag);
			if (bean.getLeaveCode().equals("")) {
				previousLeaveQuery = "SELECT TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DAYS"
						+ " FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
						+ bean.getEmpCode()
						+ " AND LEAVE_TO_DATE < TO_DATE('"
						+ leaveDtlStr[rowNum][2]
						+ "','DD-MM-YYYY') "
						+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
						+ " ORDER BY LEAVE_TO_DATE DESC ";
			}// end of nested if
			else {
				previousLeaveQuery = "SELECT TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),LEAVE_DAYS"
						+ " FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
						+ bean.getEmpCode()
						+ " AND LEAVE_TO_DATE < TO_DATE('"
						+ leaveDtlStr[rowNum][2]
						+ "','DD-MM-YYYY') "
						+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
						+ " AND LEAVE_APPLICATION_CODE !="
						+ bean.getLeaveCode() + " ORDER BY LEAVE_TO_DATE DESC ";
			}// end of else
			final Object beforeData[][] = getSqlModel().getSingleResult(
					previousLeaveQuery);
			if (beforeData != null && beforeData.length > 0) {

				if (String.valueOf(beforeData[0][1]).contains(".5")) {

				} else {
					String fromDate = String.valueOf(beforeData[0][0]);

					String nextDateQuery = "SELECT TO_CHAR((TO_DATE('"
							+ fromDate
							+ "','DD-MM-YYYY')+1),'DD-MM-YYYY')   FROM DUAL ";

					final Object nextDateQueryObj[][] = getSqlModel()
							.getSingleResult(nextDateQuery);

					Object holidayObj[][] = null;
					if (holidayCheckObj.length == 0) {

						if (bean.getBrnHDayFlag().equals("true")) { // FOR
							// BRANCHWISE
							// HOLIDAY CHECK
							final String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH "
									+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
									+ bean.getEmpCode()
									+ ")"
									+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE >=TO_DATE('"
									+ fromDate
									+ "','DD-MM-YYYY') "
									+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE <=TO_DATE('"
									+ leaveDtlStr[rowNum][2]
									+ "','DD-MM-YYYY')";
							holidayObj = getSqlModel().getSingleResult(holStr);

						} // end of if
						else {
							final String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
									+ " WHERE HOLI_DATE >=TO_DATE('"
									+ fromDate
									+ "','DD-MM-YYYY') AND HOLI_DATE <TO_DATE('"
									+ leaveDtlStr[rowNum][2]
									+ "','DD-MM-YYYY')";
							holidayObj = getSqlModel().getSingleResult(holStr);
						}

					}

					final String select = "SELECT TO_DATE('"
							+ leaveDtlStr[rowNum][2]
							+ "','DD-MM-YYYY')-TO_DATE('" + fromDate
							+ "','DD-MM-YYYY') from  DUAL";
					final Object[][] dateDiff = getSqlModel().getSingleResult(
							select);
					actualDay = Integer
							.parseInt(String.valueOf(dateDiff[0][0]));

					if (holidayObj != null) {
						totalHol = holidayObj.length;
					}// end of if
					if (weeklyCheckObj.length == 0) {
						weekenHol = countNumberOfWeeklyOff(new Utility()
								.getCalanderDate(fromDate), new Utility()
								.getCalanderDate(leaveDtlStr[rowNum][2]),
								holidayObj, bean.getEmpCode());
						weeken_hol = weekenHol + totalHol;
					}
					totalHol = weeken_hol;
					if (actualDay - 1 <= totalHol) {
						if (leaveDtlStr[rowNum][2].equals(String
								.valueOf(nextDateQueryObj[0][0]))) {
							isConsecutive = true;
						} else {
							setAlertMessage("Leave has already been applied for "
									+ fromDate
									+ " ,according to the policy you must apply leave from \n "
									+ String.valueOf(nextDateQueryObj[0][0])
									+ " to " + leaveDtlStr[rowNum][3]);
							/*
							 * setAlertMessage("Leave has already been applied
							 * from" + String.valueOf(nextDateQueryObj[0][0]) + "
							 * to " + leaveDtlStr[rowNum][3]);
							 */
							isConsecutive = false;
						}

						return isConsecutive;
					}// end of if

					else if (actualDay - 1 < 1) {
						setAlertMessage("You must apply leave from"
								+ String.valueOf(nextDateQueryObj[0][0])
								+ " to " + leaveDtlStr[rowNum][2]);
						return isConsecutive;
					}// end of else if
				}

			}
			String afterLeaveQuery = "";
			if (bean.getLeaveCode().equals("")) {
				afterLeaveQuery = "SELECT TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),LEAVE_DAYS"
						+ " FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
						+ bean.getEmpCode()
						+ " AND LEAVE_FROM_DATE > TO_DATE('"
						+ leaveDtlStr[rowNum][3]
						+ "','DD-MM-YYYY') "
						+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
						+ " ORDER BY LEAVE_FROM_DATE  ";
			} // end of if
			else {
				afterLeaveQuery = "SELECT TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),LEAVE_DAYS "
						+ " FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
						+ bean.getEmpCode()
						+ " AND LEAVE_FROM_DATE > TO_DATE('"
						+ leaveDtlStr[rowNum][3]
						+ "','DD-MM-YYYY') "
						+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' AND LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
						+ " AND LEAVE_APPLICATION_CODE !="
						+ bean.getLeaveCode() + " ORDER BY LEAVE_FROM_DATE  ";
			}// end of else
			final Object afterData[][] = getSqlModel().getSingleResult(
					afterLeaveQuery);
			if (afterData != null && afterData.length > 0) {

				if (String.valueOf(afterData[0][1]).contains(".5")) {

				} else {
					final String toDate = String.valueOf(afterData[0][0]);
					Object holidayObj[][] = null;
					if (holidayCheckObj.length == 0) {

						if (bean.getBrnHDayFlag().equals("true")) {// for
							// branch
							// wise holiday
							// check

							final String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH "
									+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
									+ bean.getEmpCode()
									+ ")"
									+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE >=TO_DATE('"
									+ leaveDtlStr[rowNum][3]
									+ "','DD-MM-YYYY') "
									+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE <=TO_DATE('"
									+ toDate + "','DD-MM-YYYY')";
							holidayObj = getSqlModel().getSingleResult(holStr);

						} // end of if
						else {
							final String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
									+ " WHERE HOLI_DATE >=TO_DATE('"
									+ leaveDtlStr[rowNum][3]
									+ "','DD-MM-YYYY') AND HOLI_DATE <=TO_DATE('"
									+ toDate + "','DD-MM-YYYY')";
							holidayObj = getSqlModel().getSingleResult(holStr);
						}
					}
					final String select = "SELECT TO_DATE('" + toDate
							+ "','DD-MM-YYYY')-TO_DATE('"
							+ leaveDtlStr[rowNum][3]
							+ "','DD-MM-YYYY') from  DUAL";
					final Object[][] dateDiff = getSqlModel().getSingleResult(
							select);
					actualDay = Integer
							.parseInt(String.valueOf(dateDiff[0][0]));
					if (holidayObj != null) {
						totalHol = holidayObj.length;
					}// end of if
					if (weeklyCheckObj.length == 0) {
						weekenHol = countNumberOfWeeklyOff(new Utility()
								.getCalanderDate(leaveDtlStr[rowNum][3]),
								new Utility().getCalanderDate(toDate),
								holidayObj, bean.getEmpCode());
						weeken_hol = weekenHol + totalHol;
					}
					totalHol = weeken_hol;
					if (actualDay - 1 <= totalHol) {
						final String nextDateQuery = "SELECT TO_CHAR((TO_DATE('"
								+ toDate
								+ "','DD-MM-YYYY')-1),'DD-MM-YYYY')   FROM DUAL ";
						final Object nextDateQueryObj[][] = getSqlModel()
								.getSingleResult(nextDateQuery);
						if (leaveDtlStr[rowNum][3].equals(String
								.valueOf(nextDateQueryObj[0][0]))) {
							isConsecutive = true;
						} else {
							setAlertMessage("You must apply leave from"
									+ leaveDtlStr[rowNum][2] + " to "
									+ String.valueOf(nextDateQueryObj[0][0]));
							isConsecutive = false;
						}
						return isConsecutive;
					}// end of if
				}
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in checkNonConsecutiveLeaveWithDB------"
					+ e);
			e.printStackTrace();
		}
		return isConsecutive;
	}// end of checkNonConsecutiveLeaveWithDB

	/**
	 * Check previously maternity leave application with saved application in
	 * DB.
	 * @param bean
	 * @param leaveDtlStr
	 * @param i
	 * @return boolean
	 */
	private boolean checkNonConsecutiveMaternityLeaveWithDB(
			LeaveApplication bean, String[][] leaveDtlStr, int rowNum) {
		boolean isConsecutive = true;
		try {
			final Object[][] maternityDataObj = getMaternityLeaveSetting();
			if (maternityDataObj != null && maternityDataObj.length > 0) {
				if (String.valueOf(maternityDataObj[0][0]).equals(
						leaveDtlStr[rowNum][0])) {
					String previousMaternityLeaveQuery = "";
					if (bean.getLeaveCode().equals("")) {
						previousMaternityLeaveQuery = "  SELECT TO_CHAR(LEAVE_MATERNITY_DATE,'DD-MM-YYYY')"
								+ "  FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
								+ bean.getEmpCode()
								+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
								+ " AND LEAVE_MATERNITY_DATE IS NOT NULL "
								+ "   ORDER BY LEAVE_MATERNITY_DATE DESC ";
					} else {
						previousMaternityLeaveQuery = "  SELECT TO_CHAR(LEAVE_MATERNITY_DATE,'DD-MM-YYYY')"
								+ "  FROM HRMS_LEAVE_DTL  WHERE EMP_ID = "
								+ bean.getEmpCode()
								+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')"
								+ " AND LEAVE_MATERNITY_DATE IS NOT NULL "
								+ "  AND LEAVE_APPLICATION_CODE !="
								+ bean.getLeaveCode()
								+ " ORDER BY LEAVE_MATERNITY_DATE DESC ";
					}
					final Object previousMaternityLeaveQueryObj[][] = getSqlModel()
							.getSingleResult(previousMaternityLeaveQuery);
					if (previousMaternityLeaveQueryObj != null
							&& previousMaternityLeaveQueryObj.length > 0) {
						final String dateDiff = " SELECT to_DATE('"
								+ leaveDtlStr[rowNum][11]
								+ "','DD-MM-YYYY')-to_DATE('"
								+ String
										.valueOf(previousMaternityLeaveQueryObj[0][0])
								+ "','DD-MM-YYYY') FROM DUAL ";
						final Object dateDiffObj[][] = getSqlModel()
								.getSingleResult(dateDiff);
						double diff = 300.0;
						if (!(Double.parseDouble(String
								.valueOf(dateDiffObj[0][0])) >= diff)) {
							setAlertMessage("You can't apply for Maternity Leave  \n as the maximum period gap is of 300 days from the past applied  "
									+ String
											.valueOf(previousMaternityLeaveQueryObj[0][0]));
							isConsecutive = false;
						}
					}
				}
			}
		} catch (Exception e) {
			logger
					.error("Exception in checkNonConsecutiveMaternityLeaveWithDB------"
							+ e);
		}
		logger.info("isConsecutive   " + isConsecutive);
		return isConsecutive;
	}// end of checkNonConsecutiveMaternityLeaveWithDB

	/**
	 * This method is used for checking unplanned leave application enable
	 * according to leave policy.
	 * Please refer Leave Policy Document section 4.2.33
	 * @param leaveApplication
	 * @return String
	 */
	public String unplannedLeaveApplicationEnable(
			LeaveApplication leaveApplication) {
		String unplnnedLevAppEnable = "N";
		try {
			final String policyCode = getLeavePolicyCode(leaveApplication
					.getEmpCode());
			final String checkUnplnnedEnable = " SELECT LEAVE_UNPLAN_ISENABLED FROM HRMS_LEAVE_POLICY_HDR "
					+ " WHERE LEAVE_POLICY_CODE=" + policyCode;
			final Object checkUnplnnedEnableObj[][] = getSqlModel()
					.getSingleResult(checkUnplnnedEnable);
			if (checkUnplnnedEnableObj != null
					&& checkUnplnnedEnableObj.length > 0) {
				if (String.valueOf(checkUnplnnedEnableObj[0][0]).equals("Y")) {
					unplnnedLevAppEnable = "Y";
				}
			}
		} catch (Exception e) {
			logger.error("Exception in unplannedLeaveApplicationEnable------"
					+ e);
		}
		return unplnnedLevAppEnable;
	}// end of unplannedLeaveApplicationEnable

	/**
	 * This method is used for calculating unplanned leave penalty amount. 
	 * @param leaveApplication
	 * @return Object[][]
	 */
	public Object[][] unplannedPenaltyAmt(LeaveApplication leaveApplication) {
		Object obj[][] = null;
		try {
			double unplannedPenalty = 0.0d;
			final String dateDiff = " SELECT TO_DATE('"
					+ leaveApplication.getLeaveFromDtl()
					+ " ','DD-MM-YYYY')-TO_DATE('"
					+ leaveApplication.getApplicationDate()
					+ " ','DD-MM-YYYY') from  DUAL ";
			final Object dateDiffObj[][] = getSqlModel().getSingleResult(
					dateDiff);
			final String policyCode = getLeavePolicyCode(leaveApplication
					.getEmpCode());
			final String unplannedSetting = " SELECT  NVL(LEAVE_CODE,0), NVL(LEAVE_FROM_DAYS,0),NVL( LEAVE_TO_DAYS,0),NVL( LEAVE_INADVANCE_DAYS,0),NVL( LEAVE_PENALTY_DAYS,0),NVL( LEAVE_UNPLAN_CODE,0) "
					+ " FROM HRMS_LEAVE_POLICY_UNPLAN_RULE "
					+ " WHERE LEAVE_POLICY_CODE="
					+ policyCode
					+ " AND LEAVE_CODE=" + leaveApplication.getLevCode();
			final Object unplannedSettingObj[][] = getSqlModel()
					.getSingleResult(unplannedSetting);
			if (unplannedSettingObj != null && unplannedSettingObj.length > 0) {
				for (int i = 0; i < unplannedSettingObj.length; i++) {
					double applyDays = Double.parseDouble(String
							.valueOf(dateDiffObj[0][0]));
					double leaveAppliedDays = Double
							.parseDouble(leaveApplication.getLeaveTotalDays());
					// leaveapplieddays > setting first value &&
					// leaveapplieddays<= setting second value
					if (leaveAppliedDays > Double.parseDouble(String
							.valueOf(unplannedSettingObj[i][1]))
							&& leaveAppliedDays <= Double.parseDouble(String
									.valueOf(unplannedSettingObj[i][2]))) {
						if (applyDays < Double.parseDouble(String
								.valueOf(unplannedSettingObj[i][3]))) {
							obj = new Object[1][2];
							if (leaveAppliedDays == 0.5) {
								unplannedPenalty = Double.parseDouble(String
										.valueOf(unplannedSettingObj[i][4]));
							} else {
								unplannedPenalty = leaveAppliedDays
										* Double
												.parseDouble(String
														.valueOf(unplannedSettingObj[i][4]));
							}
							obj[0][0] = Double.parseDouble(String
									.valueOf(unplannedSettingObj[i][3]));// days							
							obj[0][1] = unplannedPenalty;// penalty
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in unplannedPenaltyAmt------" + e);
		}

		return obj;

	}// end of unplannedPenaltyAmt

	/**
	 * This method is used for getting send back comments
	 * @param leaveApplication
	 */
	public void getSendBackComments(LeaveApplication leaveApplication) {
		try {
			final String sendBackCommentQuery = " SELECT LEAVE_COMMENTS FROM HRMS_LEAVE_PATH "
					+ "	WHERE LEAVE_APPL_CODE="
					+ leaveApplication.getLeaveCode()
					+ "AND LEAVE_STATUS='"
					+ leaveApplication.getHiddenStatus() + "'";
			final Object sendBackCOmmentObj[][] = getSqlModel()
					.getSingleResult(sendBackCommentQuery);
			if (sendBackCOmmentObj != null && sendBackCOmmentObj.length > 0) {
				leaveApplication.setApproverComments(checkNull(String
						.valueOf(sendBackCOmmentObj[0][0])));
			}
		} catch (Exception e) {
			logger.error("Exception in getSendBackComments------" + e);
		}
	}

	/**
	 * This method is used for setting upload proof value.
	 */
	public boolean setUploadProofIttValue(String policyCode) {
		boolean result = false;
		try {
			if (!policyCode.equals("")) {
				final String query = " SELECT  LEAVE_IS_PROOF_REQUIRED FROM HRMS_LEAVE_POLICY_DTL  "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_IS_PROOF_REQUIRED='Y' ";
				final Object obj[][] = getSqlModel().getSingleResult(query);
				if (obj != null && obj.length > 0) {
					if (String.valueOf(obj[0][0]).equals("Y"))
						result = true;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in setUploadProofIttValue------" + e);
			result = false;
		}
		return result;
	}

	/**
	 * This method is used for setting penalty flag
	 * @param policyCode
	 * @return boolean
	 */
	public boolean setPenaltyFlag(String policyCode) {
		boolean result = false;
		try {
			if (!policyCode.equals("")) {
				final String query = " SELECT  LEAVE_UNPLAN_ISENABLED FROM HRMS_LEAVE_POLICY_HDR "
						+ " WHERE LEAVE_POLICY_CODE=" + policyCode;
				final Object obj[][] = getSqlModel().getSingleResult(query);
				if (obj != null && obj.length > 0) {
					if (String.valueOf(obj[0][0]).equals("Y"))
						result = true;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in setPenaltyFlag------" + e);
		}
		return result;
	}

	/**
	 * This method is used for getting maternity leave setting 
	 * @return Object[][]
	 */
	public Object[][] getMaternityLeaveSetting() {
		Object settingObj[][] = null;
		try {
			final String maternitySettingQue = " SELECT HRMS_MATERNITY_LEAVE_TYPE, NVL(HRMS_MATERNITY_LEAVE_DAYS,0),"
					+ " NVL(HRMS_MIN_SERVICE_PERIOD,0), HRMS_MATERNITY_PRE_LEAVES,NVL( HRMS_MATERNITY_PRE_LEAVEDAYS,0),"
					+ " HRMS_MATERNITY_POST_LEAVES,NVL( HRMS_MATERNITY_POST_LEAVEDAYS,0), HRMS_PATERNITY_LEAVE_TYPE, "
					+ " NVL(HRMS_PATERNITY_LEAVE_DAYS,0) FROM HRMS_MATERNITY_LEAVE_POLICY ";
			settingObj = getSqlModel().getSingleResult(maternitySettingQue);
		} catch (Exception e) {
			logger.error("Exception in getMaternityLeaveSetting------" + e);
		}
		return settingObj;
	}

	/**
	 * This method is used for getting maternity leave type. 
	 * @return
	 */
	public String getMaternityLeaveCode() {
		String maternityCode = "0";
		try {
			Object maternityCodeObj[][] = null;
			final String query = " SELECT NVL(HRMS_MATERNITY_LEAVE_TYPE,0) FROM HRMS_MATERNITY_LEAVE_POLICY ";
			maternityCodeObj = getSqlModel().getSingleResult(query);
			if (maternityCodeObj != null && maternityCodeObj.length > 0) {
				maternityCode = String.valueOf(maternityCodeObj[0][0]);
			}
		} catch (Exception e) {
			logger.error("Exception in getMaternityLeaveCode------" + e);
		}
		return maternityCode;
	}

	/**
	 * This method is used for checking is leave pull setting according to leave
	 * policy.
	 * Please refer Leave Policy Document section 4.2.26
	 * @param leaveApplication
	 */
	public void checkIsPoolDefine(LeaveApplication leaveApplication) {
		try {
			/***Functionality is removed on 7th August 
			/*
			double openBal = 0.0;
			if (!leaveApplication.getLevOpeningBalance().equals("")) {
				openBal = Double.parseDouble(leaveApplication
						.getLevOpeningBalance());
			}if (openBal < 1.0 || openBal < 1) {
				final String policyCode = getLeavePolicyCode(leaveApplication
						.getEmpCode());
				final String poolSettingQuery = " SELECT NVL(LEAVE_BALANCE_POOL_LEAVETYPE,0) FROM HRMS_LEAVE_POLICY_DTL "
						+ "	WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND  LEAVE_CODE=" + leaveApplication.getLevCode();
				final Object poolSettingQueryObj[][] = getSqlModel()
						.getSingleResult(poolSettingQuery);
				if (poolSettingQueryObj != null
						&& poolSettingQueryObj.length > 0) {
					String balanceQuery = "";
					if (leaveApplication.getIsAddFlag().equals("false")) {
						balanceQuery = " SELECT LEAVE_CODE,HRMS_LEAVE.LEAVE_ABBR,NVL(LEAVE_CLOSING_BALANCE,0) "
								+ " FROM  HRMS_LEAVE_BALANCE "
								+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE )"
								+ " WHERE EMP_ID="
								+ leaveApplication.getEmpCode()
								+ " AND LEAVE_CODE IN ("
								+ String.valueOf(poolSettingQueryObj[0][0])
								+ ")"
								+ " AND LEAVE_CLOSING_BALANCE >0 ORDER BY HRMS_LEAVE_BALANCE.LEAVE_CODE ";
					} else {
						balanceQuery = " SELECT LEAVE_CODE,HRMS_LEAVE.LEAVE_ABBR,NVL(LEAVE_CLOSING_BALANCE,0) "
								+ " FROM  HRMS_LEAVE_BALANCE_TEMP "
								+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE_TEMP.LEAVE_CODE )"
								+ " WHERE EMP_ID="
								+ leaveApplication.getEmpCode()
								+ " AND LEAVE_CODE IN ("
								+ String.valueOf(poolSettingQueryObj[0][0])
								+ ")"
								+ " AND LEAVE_CLOSING_BALANCE >0 ORDER BY HRMS_LEAVE_BALANCE_TEMP.LEAVE_CODE ";
					}
					final Object balanceQueryObj[][] = getSqlModel()
							.getSingleResult(balanceQuery);
					final ArrayList list = new ArrayList();
					if (balanceQueryObj != null && balanceQueryObj.length > 0) {
						for (int j = 0; j < balanceQueryObj.length; j++) {
							leaveApplication.setIsPoolDefine("true");
							LeaveApplication bean = new LeaveApplication();
							bean.setPoolLevCode(String
									.valueOf(balanceQueryObj[j][0]));
							bean.setPoolLevName(String
									.valueOf(balanceQueryObj[j][1]));
							bean.setPoolLevBalance(String
									.valueOf(balanceQueryObj[j][2]));
							list.add(bean);
						}
						leaveApplication.setPullFrmList(list);
					} else {
						leaveApplication.setIsPoolDefine("false");
					}
				}
			} else {
				leaveApplication.setIsPoolDefine("false");
			}
			 */
		} catch (Exception e) {
			logger.error("Exception in checkIsPoolDefine------" + e);
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for updating pull leave balance.
	 * @param pullLevCode
	 * @param leaveAdjust
	 * @param leaveCode
	 * @param empCode
	 * @param ishalfPayLeave
	 * @return boolean
	 */
	public boolean updatePullLeaveBal(String pullLevCode, String leaveAdjust,
			String leaveCode, String empCode, String ishalfPayLeave) {
		boolean result = false;
		try {
			final Object[][] updateObj = new Object[2][3];
			if (ishalfPayLeave.equals("Y")) {
				updateObj[0][0] = Double.parseDouble(leaveAdjust) * 2;
			} else {
				updateObj[0][0] = leaveAdjust;
			}
			updateObj[0][1] = empCode;
			updateObj[0][2] = leaveCode;
			if (ishalfPayLeave.equals("Y")) {
				updateObj[1][0] = -Double.parseDouble(leaveAdjust) * 2;
			} else {
				updateObj[1][0] = -Double.parseDouble(leaveAdjust);
			}
			updateObj[1][1] = empCode;
			updateObj[1][2] = pullLevCode;
			final String updateBalQuery = " UPDATE  HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE=LEAVE_CLOSING_BALANCE+? "
					+ " WHERE EMP_ID=? AND LEAVE_CODE=?";
			result = getSqlModel().singleExecute(updateBalQuery, updateObj);

			final String updateTemp = " UPDATE HRMS_LEAVE_BALANCE_TEMP SET LEAVE_CLOSING_BALANCE=LEAVE_CLOSING_BALANCE+? "
					+ " WHERE EMP_ID = ? AND LEAVE_CODE = ? ";
			result = getSqlModel().singleExecute(updateTemp, updateObj);
		} catch (Exception e) {
			logger.error("Exception in updatePullLeaveBal------" + e);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This method is used for saving leave data for hourly based leave
	 * application.
	 * @param leaveBean
	 * @param approverId
	 * @param alterapproverId
	 * @param status
	 * @param request
	 * @return String
	 */
	public String saveHrs(LeaveApplication leaveBean, String approverId,
			String alterapproverId, String status, HttpServletRequest request) {
		/**
		 * CHECK PREVIOUS CONDITION
		 */
		final String chk = isAlreadyApplied(leaveBean);
		if (!chk.equals("")) {
			return chk;
		}
		/**
		 * CHECK CONDITION FROM SHIFT TABLE
		 */
		String[] empCode = request.getParameterValues("keepInformedEmpId");
		String qq = "SELECT NVL(SFT_LM_HRS_ISENABLED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0) "
				+ " ,NVL(TO_CHAR(SFT_START_TIME,'HH24:MI'),'00:00'),NVL(TO_CHAR(SFT_END_TIME ,'HH24:MI'),'00:00') FROM HRMS_SHIFT where SHIFT_ID= (SELECT NVL(EMP_SHIFT,0) FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ leaveBean.getEmpCode() + ") ";
		final Object[][] shiftCode = getSqlModel().getSingleResult(qq);
		String shifTime = "";
		int shiftFromTime = 0;
		int shiftToTime = 0;
		String shiftFromTime1 = "";
		String shiftToTime1 = "";
		if (shiftCode != null && shiftCode.length > 0) {
			if (String.valueOf(shiftCode[0][0]).equals("N")) {
				return "First configure shift for this employee";
			}
			if (String.valueOf(shiftCode[0][4]).equals("0")) {
				return "First configure shift for this employee";
			}
			shifTime = String.valueOf(shiftCode[0][4]);
			shiftFromTime = TimeUtility.getMinute(String
					.valueOf(shiftCode[0][5]));
			shiftToTime = TimeUtility
					.getMinute(String.valueOf(shiftCode[0][6]));
			shiftFromTime1 = String.valueOf(shiftCode[0][5]);
			shiftToTime1 = String.valueOf(shiftCode[0][6]);
			if (shiftFromTime == 0 && shiftToTime == 0) {
				return "First configure shift for this employee";
			}
			leaveBean.setShiftTime(shifTime);
		}
		int shiftMinuts = TimeUtility.getMinute(shifTime);
		int fromTimeMIN = 0;
		int toTimeMIN = 0;
		fromTimeMIN = TimeUtility.getMinute(leaveBean.getFromTime());
		toTimeMIN = TimeUtility.getMinute(leaveBean.getToTime());
		if (fromTimeMIN < shiftFromTime || fromTimeMIN > shiftToTime) {
			return "Leave from time & to time should be in between "
					+ shiftFromTime1 + " - " + shiftToTime1;
		}
		if (toTimeMIN < shiftFromTime || toTimeMIN > shiftToTime) {
			return "Leave from time & to time should be in between shift start & end time";
		}
		final String policyCode = getLeavePolicyCode(leaveBean.getEmpCode());
		boolean insertFlag = false;
		if (leaveBean.getLeaveCode().equals("")) {
			final String query = " SELECT NVL(MAX(LEAVE_APPL_CODE),0)+1 FROM HRMS_LEAVE_HDR ";
			final Object obj[][] = getSqlModel().getSingleResult(query);
			leaveBean.setLeaveCode(String.valueOf(obj[0][0]));
			insertFlag = true;
		}
		final Object[][] addParam = new Object[1][10];
		addParam[0][9] = leaveBean.getLeaveCode(); // LEAVE CODE
		addParam[0][1] = leaveBean.getEmpCode(); // EMPLOYEE ID
		addParam[0][2] = leaveBean.getComments();// COMMENTS
		if (!leaveBean.getMedicalCert().equals("")) {
			addParam[0][3] = leaveBean.getMedicalCert();// REASON
		} else {
			addParam[0][3] = "";
		}
		addParam[0][4] = status;// status
		addParam[0][5] = leaveBean.getDateHrs();// FROM TIME
		addParam[0][6] = leaveBean.getDateHrs();// TO TIME
		addParam[0][7] = approverId; // approver id according to reporting structure
		addParam[0][8] = alterapproverId; // alternate approver id according to reporting structure
		if (!leaveBean.getLeaveReasonCode().equals("")) {
			addParam[0][0] = leaveBean.getLeaveReasonCode();// reasons
		} else {
			addParam[0][0] = "";
		}
		if (!(leaveBean.getLeaveReasonCode().equals("") || leaveBean
				.getLeaveReasonCode().equals("0"))
				& !leaveBean.getMedicalCert().equals("")) {
			addParam[0][3] = "";
			addParam[0][0] = leaveBean.getLeaveReasonCode();// reasons./
		}
		final Object[][] delData = new Object[1][1];
		delData[0][0] = leaveBean.getLeaveCode();
		final String delHDR = "DELETE FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE=?";
		boolean result = false;// =getSqlModel().singleExecute(delHDR,
		// delData);
		if (insertFlag) {
			result = getSqlModel().singleExecute(getQuery(22), addParam);
		} else {
			result = getSqlModel().singleExecute(getQuery(28), addParam);
		}
		if (result) {
			int applyTimeDiffMIN = 0;
			int balabceHrsMIN = 0;
			// int avlDiffMM=0;
			// shiftMinuts
			int totalAvailableBalMIN = 0;
			int availableDaysMIN = 0;
			String[] avlSpt = null;
			try {
				if (leaveBean.getAvailableBalanceDay().contains(".")) {
					String abc = leaveBean.getAvailableBalanceDay().replace(
							".", ":");
					avlSpt = abc.split(":");
					availableDaysMIN = Integer.parseInt(avlSpt[0].trim())
							* shiftMinuts;
					availableDaysMIN = availableDaysMIN
							+ ((Integer.parseInt(avlSpt[1].trim()) * shiftMinuts) / 10);
				} else {
					availableDaysMIN = Integer.parseInt(leaveBean
							.getAvailableBalanceDay().trim())
							* shiftMinuts;
				}
			} catch (Exception e) {
			}
			String remBalanveDay = "0";
			String remBalanveHrs = "0";
			String availableBalDAY_HH_MM = "";
			String[] remBalance;
			String applyTimeHH_MM = "0";
			balabceHrsMIN = TimeUtility.getMinute(leaveBean
					.getAvailableBalanceHrs());
			applyTimeDiffMIN = toTimeMIN - fromTimeMIN;
			final Object[][] addDetails = new Object[1][17];
			addDetails[0][0] = leaveBean.getLeaveCode(); // application code
			addDetails[0][1] = leaveBean.getLeaveTypeCodeHrs();// leave code
			addDetails[0][2] = "0";// leave days
			addDetails[0][3] = leaveBean.getDateHrs() + " "
					+ leaveBean.getFromTime();// leave from date
			addDetails[0][4] = leaveBean.getDateHrs() + " "
					+ leaveBean.getToTime();// leave to date
			addDetails[0][5] = leaveBean.getEmpCode();// employee id
			addDetails[0][6] = status;// status
			addDetails[0][7] = approverId;// approver id according to
			addDetails[0][8] = "";// from date half day
			addDetails[0][9] = "";// to date half day
			addDetails[0][10] = "";// document path
			addDetails[0][11] = "";// document name
			addDetails[0][12] = "";// penalty days
			addDetails[0][13] = "";// adjusted penalty days
			addDetails[0][14] = "";// un adjusted penalty// days
			addDetails[0][15] = "";// delivery date
			addDetails[0][16] = TimeUtility.getHH_MM(applyTimeDiffMIN);

			final String delDTL = "DELETE FROM HRMS_LEAVE_DTL WHERE LEAVE_APPLICATION_CODE=?";
			result = getSqlModel().singleExecute(delDTL, delData);
			if (result) {
				result = getSqlModel().singleExecute(getQuery(23), addDetails);// 15
			}
			try {
				final Object[][] addDetailsDtl = new Object[1][10];
				addDetailsDtl[0][0] = leaveBean.getLeaveCode(); // leave appl code
				addDetailsDtl[0][1] = leaveBean.getLeaveTypeCodeHrs();// leave code
				addDetailsDtl[0][2] = "0";// leave days
				addDetailsDtl[0][3] = leaveBean.getDateHrs() + " "
						+ leaveBean.getFromTime();// leave from time
				addDetailsDtl[0][4] = leaveBean.getDateHrs() + " "
						+ leaveBean.getToTime();// leave to time
				addDetailsDtl[0][5] = leaveBean.getEmpCode();// employee id
				addDetailsDtl[0][6] = status; // status
				final String month_year_single[] = leaveBean.getDateHrs()
						.split("-");
				addDetailsDtl[0][7] = month_year_single[1];
				addDetailsDtl[0][8] = month_year_single[2];
				addDetailsDtl[0][9] = TimeUtility.getHH_MM(applyTimeDiffMIN);
				final String deletLeaveDtl1 = " DELETE FROM HRMS_LEAVE_DTLHISTORY WHERE LEAVE_APPLICATION_CODE="
						+ leaveBean.getLeaveCode();
				boolean flag = getSqlModel().singleExecute(deletLeaveDtl1);
				if (flag) {
					result = getSqlModel().singleExecute(getQuery(24),
							addDetailsDtl);// 18
				}
			} // end of try block
			catch (Exception e) {
				e.printStackTrace();
			}// end of catch block
			if (result) {
				final Object[][] updateBalance = new Object[1][6];
				totalAvailableBalMIN = availableDaysMIN + balabceHrsMIN;
				if (totalAvailableBalMIN > applyTimeDiffMIN) {
					totalAvailableBalMIN = totalAvailableBalMIN
							- applyTimeDiffMIN;
				} else {
					return "You dont have sufficient balance";
				}
				availableBalDAY_HH_MM = TimeUtility.getDAYS_HH_MM(
						totalAvailableBalMIN, shiftMinuts);
				remBalance = availableBalDAY_HH_MM.split("Days");
				remBalanveDay = remBalance[0];
				remBalanveHrs = remBalance[1];
				// applyTimeHH_MM=TimeUtility.getHH_MM(applyTimeDiffMIN);
				updateBalance[0][0] = remBalanveDay; // applied time
				updateBalance[0][1] = remBalanveHrs;// leave
				updateBalance[0][2] = "0";// leave
				updateBalance[0][3] = applyTimeHH_MM;// leave
				updateBalance[0][4] = leaveBean.getLeaveTypeCodeHrs(); // leave
				// code
				updateBalance[0][5] = leaveBean.getEmpCode(); // employee id
				/** ZERO BALANCE APPLICABLE CHECK*/
				final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_CODE=" + leaveBean.getLeaveTypeCodeHrs();
				;
				final Object[][] zeroBalance = getSqlModel().getSingleResult(
						zeroBlncQuery);
				/*
				 * GET LEAVE ONLOAD, LEAVE ONLOAD HRS FROM BALANCE
				 */
				final Object[][] holdBalData = new Object[1][2];
				holdBalData[0][0] = leaveBean.getLeaveTypeCodeHrs();
				holdBalData[0][1] = leaveBean.getEmpCode();

				final String holdBalance = "SELECT  NVL(LEAVE_ONHOLD,0), NVL(TO_CHAR(LEAVE_HRS_ONHOLD, 'HH24:MI'),'00:00') FROM HRMS_LEAVE_BALANCE "
						+ " WHERE LEAVE_CODE="
						+ leaveBean.getLeaveTypeCodeHrs()
						+ " AND EMP_ID= "
						+ leaveBean.getEmpCode();
				final Object[][] onHoldData = getSqlModel().getSingleResult(
						holdBalance);
				int onHoldMIN = 0;
				if (onHoldData != null && onHoldData.length > 0) {
					onHoldMIN = TimeUtility.getMinute(String
							.valueOf(onHoldData[0][1]));
				}
				applyTimeHH_MM = TimeUtility.getHH_MM(applyTimeDiffMIN
						+ onHoldMIN
						- TimeUtility.getMinute(leaveBean.getDiffTime()));
				updateBalance[0][3] = applyTimeHH_MM;// leave
				leaveBean.setDiffTime(TimeUtility.getHH_MM(applyTimeDiffMIN));
				final String updateBalanceHrs = " UPDATE HRMS_LEAVE_BALANCE SET "
						+ " LEAVE_CLOSING_BALANCE = ?,LEAVE_HRS_CLOSING_BALANCE=TO_DATE(?,'HH24:MI') , LEAVE_ONHOLD =NVL(LEAVE_ONHOLD,0) + ?"
						+ " ,LEAVE_HRS_ONHOLD=TO_DATE(?,'HH24:MI') WHERE  LEAVE_CODE =? AND EMP_ID =? ";
				if (zeroBalance != null
						&& !String.valueOf(zeroBalance[0][0]).equals("Y")) {
					result = getSqlModel().singleExecute(updateBalanceHrs,
							updateBalance);
				}// end of if
				saveKeepInformedListHrs(empCode, leaveBean.getLeaveCode(),
						leaveBean.getEmpCode());
			}// end of if
		}
		return "";
	}

	/**
	 * This method is used for saving keep informed to list for hourly based
	 * leave application. 
	 * @param empCode
	 * @param leaveCode
	 * @param employeeId
	 */
	public void saveKeepInformedListHrs(String empCode[], String leaveCode,
			String employeeId) {
		try {
			String empId = "";
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					if (i < empCode.length - 1) {
						empId += empCode[i] + ",";
					} else {
						empId = empId + empCode[i];
					}
				}
			}
			final String updateQuery = "  UPDATE "
					+ " HRMS_LEAVE_HDR SET LEAVE_KEEP_INFORMED=?  WHERE LEAVE_APPL_CODE=? AND EMP_ID=? ";
			final Object updateObj[][] = new Object[1][3];
			updateObj[0][0] = empId;
			updateObj[0][1] = leaveCode;
			updateObj[0][2] = employeeId;
			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			logger.error("Exception in saveKeepInformedList----------" + e);
		}
	}

	/**
	 * This method is used for getting leave details for hourly based leave
	 * application.
	 * @param leaveApplication
	 * @param leaveCode
	 * @return String
	 */
	public String getLeaveHrsApplication(LeaveApplication leaveApplication,
			String leaveCode) {

		// String levAppCode = request.getParameter("levApplicationCode");
		leaveApplication.setLeaveCode(leaveCode);
		/**
		 * CHECK CONDITION FROM SHIFT TABLE
		 */
		final String qq = "SELECT NVL(SFT_LM_HRS_ISENABLED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0) "
				+ " FROM HRMS_SHIFT where SHIFT_ID= (SELECT NVL(EMP_SHIFT,0) FROM HRMS_EMP_OFFC WHERE EMP_ID=(SELECT EMP_ID FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE="
				+ leaveApplication.getLeaveCode() + ")) ";
		final Object[][] shiftCode = getSqlModel().getSingleResult(qq);
		String shifTime = "";
		if (shiftCode != null && shiftCode.length > 0) {
			// Based on hours of late reporting
			if (String.valueOf(shiftCode[0][4]).equals("0")) {
				// result="notDefine";
				return "Shift is not define";
			}
			shifTime = String.valueOf(shiftCode[0][4]);
			leaveApplication.setShiftTime(shifTime);
		}
		int shiftMinuts = TimeUtility.getMinute(shifTime);
		final String selectQuery = " SELECT  HRMS_LEAVE_DTL.LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'HH24:MI'),	 TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'HH24:MI'),LEAVE_DAYS_HRS,NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0),   NVL(TO_CHAR(LEAVE_HRS_CLOSING_BALANCE, 'HH24:MI'),'00:00'),NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0) "
				+ "	,NVL(TO_CHAR(HRMS_LEAVE_BALANCE.LEAVE_HRS_ONHOLD, 'HH24:MI'),'00:00'),TO_CHAR(HRMS_LEAVE_HDR.LEAVE_FROM_DATE,'DD-MM-YYYY') 	 "
				+ "	,NVL(TO_CHAR(LEAVE_DAYS_HRS, 'HH24:MI'),'00:00') FROM HRMS_LEAVE_DTL "
				+ "	INNER JOIN HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
				+ "	INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	 LEFT JOIN  HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE  AND HRMS_LEAVE_BALANCE.EMP_ID = (SELECT EMP_ID FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE="
				+ leaveApplication.getLeaveCode()
				+ ")) "
				+ " WHERE HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE="
				+ leaveApplication.getLeaveCode()
				+ "  AND HRMS_LEAVE_DTL.EMP_ID=(SELECT EMP_ID FROM HRMS_LEAVE_HDR WHERE LEAVE_APPL_CODE="
				+ leaveApplication.getLeaveCode()
				+ ") AND HRMS_LEAVE_HDR.LEAVE_HRS_FLAG='Y' ";
		final Object[][] leaveHrsData = getSqlModel().getSingleResult(
				selectQuery);
		if (leaveHrsData != null && leaveHrsData.length > 0) {
			leaveApplication.setFlagHrs("flag");
			leaveApplication.setEditFlagHrs("false");
			leaveApplication.setLeaveTypeCodeHrs(String
					.valueOf(leaveHrsData[0][0]));
			leaveApplication.setLeaveTypeNameHrs(String
					.valueOf(leaveHrsData[0][1]));
			leaveApplication.setFromTime(String.valueOf(leaveHrsData[0][2]));
			leaveApplication.setToTime(String.valueOf(leaveHrsData[0][3]));
			leaveApplication.setAvailableBalanceDay(String
					.valueOf(leaveHrsData[0][5]));
			leaveApplication.setAvailableBalanceHrs(String
					.valueOf(leaveHrsData[0][6]));
			leaveApplication.setDateHrs(String.valueOf(leaveHrsData[0][9]));
			leaveApplication.setDiffTime(String.valueOf(leaveHrsData[0][10]));
			int applyMIN = 0;
			int avlMIN = 0;
			String totalAvlDay_HH_MM = "";
			String[] totalDayMM = null;
			applyMIN = TimeUtility.getMinute(String
					.valueOf(leaveHrsData[0][10]));
			int availableDaysMIN = 0;
			if (String.valueOf(leaveHrsData[0][5]).contains(".")) {
				String abc = String.valueOf(leaveHrsData[0][5]).replace(".",
						":");
				final String[] avlSpt = abc.split(":");
				availableDaysMIN = Integer.parseInt(avlSpt[0].trim())
						* shiftMinuts;
				availableDaysMIN = availableDaysMIN
						+ ((Integer.parseInt(avlSpt[1].trim()) * shiftMinuts) / 10);
			} else {
				availableDaysMIN = Integer.parseInt(String.valueOf(
						leaveHrsData[0][5]).trim())
						* shiftMinuts;
			}
			avlMIN = (applyMIN)
					+ (availableDaysMIN + TimeUtility.getMinute(String
							.valueOf(leaveHrsData[0][6])));
			totalAvlDay_HH_MM = TimeUtility.getDAYS_HH_MM(avlMIN, shiftMinuts);
			totalDayMM = totalAvlDay_HH_MM.split("Days");
			leaveApplication.setAvailableBalanceDay(totalDayMM[0]);
			leaveApplication.setAvailableBalanceHrs(totalDayMM[1]);
		}
		return "";
	}

	/**
	 * This method is used for deleting hourly based leave application. 
	 * @param leaveBean
	 * @return boolean
	 */
	public boolean deleteHrs(LeaveApplication leaveBean) {
		boolean result = false;
		boolean resultDtl = false;
		try {
			final Object[][] bean = new Object[1][1];
			bean[0][0] = leaveBean.getLeaveCode();
			result = getSqlModel().singleExecute(getQuery(7), bean);
			resultDtl = getSqlModel().singleExecute(getQuery(20), bean);
			result = getSqlModel().singleExecute(getQuery(21), bean);
			if (result && resultDtl) {
				final String avlBalDay = leaveBean.getAvailableBalanceDay();
				final String avlBalHrs = leaveBean.getAvailableBalanceHrs();
				final String applyDiff = leaveBean.getDiffTime();
				final String leaveCode = leaveBean.getLeaveTypeCodeHrs();
				updateBalanceHrs(leaveBean.getEmpCode(), avlBalDay, avlBalHrs,
						applyDiff, leaveCode, "");
			}
		} catch (Exception e) {
			logger.error("Exception in deleteLeaveHRS-------- " + e);
		}
		return result;
	}

	/**
	 * This method is used for updating hourly based leave application.
	 * @param empCode
	 * @param avlBalDay
	 * @param avlBalHrs
	 * @param applyDiff
	 * @param leaveCode
	 * @param check
	 * @return String
	 */
	public String updateBalanceHrs(String empCode, String avlBalDay,
			String avlBalHrs, String applyDiff, String leaveCode, String check) {
		boolean result = false;
		/**
		 * CHECK CONDITION FROM SHIFT TABLE
		 */
		final String qq = "SELECT NVL(SFT_LM_HRS_ISENABLED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0) "
				+ " FROM HRMS_SHIFT where SHIFT_ID= (SELECT NVL(EMP_SHIFT,0) FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ empCode + ") ";
		final Object[][] shiftCode = getSqlModel().getSingleResult(qq);
		String shifTime = "";

		if (shiftCode != null && shiftCode.length > 0) {
			// Based on hours of late reporting
			if (String.valueOf(shiftCode[0][4]).equals("0")) {
				// result="notDefine";
				return "Shift is not define";
			}
			shifTime = String.valueOf(shiftCode[0][4]);
			// leaveBean.setShiftTime(shifTime);
		}
		int shiftMinuts = TimeUtility.getMinute(shifTime);
		final String policyCode = getLeavePolicyCode(empCode);
		int applyTimeDiffMIN = 0;
		int balabceHrsMIN = 0;
		int totalAvailableBalMIN = 0;
		int availableDaysMIN = 0;
		String[] avlSpt = null;
		try {
			if (avlBalDay.contains(".")) {
				final String abc = avlBalDay.replace(".", ":");
				avlSpt = abc.split(":");
				availableDaysMIN = Integer.parseInt(avlSpt[0].trim())
						* shiftMinuts;
				availableDaysMIN = availableDaysMIN
						+ ((Integer.parseInt(avlSpt[1].trim()) * shiftMinuts) / 10);
			} else {
				availableDaysMIN = Integer.parseInt(avlBalDay.trim())
						* shiftMinuts;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String remBalanveDay = "0";
		String remBalanveHrs = "0";
		String availableBalDAY_HH_MM = "";
		String[] remBalance;
		String applyTimeHH_MM = "00:00";
		balabceHrsMIN = TimeUtility.getMinute(avlBalHrs);
		applyTimeDiffMIN = TimeUtility.getMinute(applyDiff);
		final Object[][] updateBalance = new Object[1][6];
		totalAvailableBalMIN = availableDaysMIN + balabceHrsMIN;
		if (check.equals("add")) {
			totalAvailableBalMIN = totalAvailableBalMIN + applyTimeDiffMIN;
		}
		// totalAvailableBalMIN=totalAvailableBalMIN;
		availableBalDAY_HH_MM = TimeUtility.getDAYS_HH_MM(totalAvailableBalMIN,
				shiftMinuts);
		remBalance = availableBalDAY_HH_MM.split("Days");
		remBalanveDay = remBalance[0];
		remBalanveHrs = remBalance[1];
		updateBalance[0][0] = remBalanveDay; // applied time
		updateBalance[0][1] = remBalanveHrs;// leave
		updateBalance[0][2] = "0";// leave
		updateBalance[0][3] = applyTimeHH_MM;// leave
		updateBalance[0][4] = leaveCode; // leave code
		updateBalance[0][5] = empCode; // employee id
		final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
				+ " WHERE LEAVE_POLICY_CODE="
				+ policyCode
				+ " AND LEAVE_CODE="
				+ leaveCode;
		final Object[][] zeroBalance = getSqlModel().getSingleResult(
				zeroBlncQuery);
		final String holdBalance = "SELECT  NVL(LEAVE_ONHOLD,0), NVL(TO_CHAR(LEAVE_HRS_ONHOLD, 'HH24:MI'),'00:00') FROM HRMS_LEAVE_BALANCE "
				+ " WHERE LEAVE_CODE=" + leaveCode + " AND EMP_ID= " + empCode;
		final Object[][] onHoldData = getSqlModel()
				.getSingleResult(holdBalance);
		int onHoldMIN = 0;
		if (onHoldData != null && onHoldData.length > 0) {
			onHoldMIN = TimeUtility.getMinute(String.valueOf(onHoldData[0][1]));
		}
		if (onHoldMIN > applyTimeDiffMIN) {
			applyTimeHH_MM = TimeUtility
					.getHH_MM((onHoldMIN - applyTimeDiffMIN));
		}
		updateBalance[0][3] = applyTimeHH_MM;// leave
		final String updateBalanceHrs = " UPDATE HRMS_LEAVE_BALANCE SET "
				+ " LEAVE_CLOSING_BALANCE = ?,LEAVE_HRS_CLOSING_BALANCE=TO_DATE(?,'HH24:MI') , LEAVE_ONHOLD =NVL(LEAVE_ONHOLD,0) + ?"
				+ " ,LEAVE_HRS_ONHOLD=TO_DATE(?,'HH24:MI') WHERE  LEAVE_CODE =? AND EMP_ID =? ";

		if (zeroBalance != null
				&& !String.valueOf(zeroBalance[0][0]).equals("Y")) {
			result = getSqlModel().singleExecute(updateBalanceHrs,
					updateBalance);
		}
		return "";
	}

	/**
	 * This method is used for checking is leave already applied for same date. 
	 * @param leaveBean
	 * @return String
	 */
	public String isAlreadyApplied(LeaveApplication leaveBean) {
		String result = "";
		String frmTime = leaveBean.getFromTime();
		String toTime = leaveBean.getToTime();
		final String policyCode = getLeavePolicyCode(leaveBean.getEmpCode());
		/*
		 * FROM TIME SHOULD BE LESS THAN TO TIME
		 */
		if (TimeUtility.getMinute(leaveBean.getFromTime()) > TimeUtility
				.getMinute(leaveBean.getToTime())) {
			return "From time should be less than to time";
		}
		/*
		 * FOR HOLIDAY
		 */
		final String holidayFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_CODE"
				+ " FROM HRMS_LEAVE_POLICY_DTL "
				+ " WHERE  LEAVE_POLICY_CODE="
				+ policyCode
				+ "  AND (LEAVE_HOLIDAY_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
				+ leaveBean.getLeaveTypeCodeHrs() + ")";
		final Object[][] holidayCheckObj = getSqlModel().getSingleResult(
				holidayFlag);

		Object[][] holidayObj = null;
		final String startDate = leaveBean.getDateHrs();
		if (holidayCheckObj != null && holidayCheckObj.length > 0
				&& leaveBean.getBrnHDayFlag().equals("true")) {
			final String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH "
					+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ leaveBean.getEmpCode()
					+ ")"
					+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE =TO_DATE('"
					+ startDate + "','DD-MM-YYYY') " + " ";
			holidayObj = getSqlModel().getSingleResult(holStr);
		} // end of if
		else {
			final String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
					+ " WHERE HOLI_DATE =TO_DATE('"
					+ startDate
					+ "','DD-MM-YYYY') ";
			holidayObj = getSqlModel().getSingleResult(holStr);
		} // end of else
		if (holidayObj != null && holidayObj.length > 0) {
			return "You can not apply for Holiday";
		}
		/*
		 * FOR SECOND SATURDAY & SUNDAY
		 */
		final String weekFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_WEEKOFF_INCLUDE_FLAG,LEAVE_CODE"
				+ " FROM HRMS_LEAVE_POLICY_DTL "
				+ " WHERE  LEAVE_POLICY_CODE="
				+ policyCode
				+ "  AND (LEAVE_WEEKOFF_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
				+ leaveBean.getLeaveTypeCodeHrs() + ")";
		final Object[][] weeklyCheckObj = getSqlModel().getSingleResult(
				weekFlag);
		final String qq = "SELECT NVL(SFT_FIXED_WO_ISENABLED,'N'),NVL(SHIFT_WEEK_1,'0'),NVL(SHIFT_WEEK_2,'0'),NVL(SHIFT_WEEK_3,'0'),NVL(SHIFT_WEEK_4,'0'),NVL(SHIFT_WEEK_5,'0'),NVL(SHIFT_WEEK_6,'0') FROM HRMS_SHIFT where SHIFT_ID= (SELECT NVL(EMP_SHIFT,0) FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ leaveBean.getEmpCode() + ") ";
		final Object[][] shiftCode = getSqlModel().getSingleResult(qq);

		if (weeklyCheckObj != null && weeklyCheckObj.length > 0) {
			try {
				java.text.DateFormat df = new java.text.SimpleDateFormat(
						"dd-MM-yyyy");
				java.util.Date dt = df.parse(startDate);
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.setTime(dt);
				int WEEK_OF_MONTH = cal.get(java.util.Calendar.WEEK_OF_MONTH);
				int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
				boolean flag = false;
				if (shiftCode != null && shiftCode.length > 0
						&& String.valueOf(shiftCode[0][0]).equals("Y")) {
					// if(WEEK_OF_MONTH==1){
					if (String.valueOf(shiftCode[0][WEEK_OF_MONTH]).contains(
							",")) {
						final String[] str = String.valueOf(
								shiftCode[0][WEEK_OF_MONTH]).split(",");
						for (int i = 0; i < str.length; i++) {
							if (String.valueOf(str[i]).equals(
									String.valueOf(dayOfWeek))) {
								flag = true;
							}
						}
					} else {
						if (String.valueOf(shiftCode[0][WEEK_OF_MONTH]).equals(
								String.valueOf(dayOfWeek))) {
							flag = true;
						}
					}
					// }
				}
				if (flag) {
					return "You can not apply for Weekly Offs";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String query = "SELECT DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')) FROM HRMS_LEAVE_DTL "
				+ "	  INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE)  WHERE  (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')  AND to_char(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')='"
				+ leaveBean.getDateHrs()
				+ "'	AND ( "
				+ "	 (  "
				+ "	  to_char(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'HH24:MI') = '"
				+ frmTime
				+ "'  "
				+ "	 AND to_char(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'HH24:MI') = '"
				+ toTime
				+ "'  "
				+ "	 ) OR   "
				+ "	 (  "
				+ "	   to_char(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'HH24:MI') <= '"
				+ frmTime
				+ "'  "
				+ "	  AND to_char(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'HH24:MI') >= '"
				+ frmTime
				+ "' "
				+ "	  )OR   "
				+ "	  (  "
				+ "	    to_char(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'HH24:MI') <= '"
				+ toTime
				+ "'  "
				+ "	  AND to_char(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'HH24:MI') >= '"
				+ toTime
				+ "'  "
				+ "	  )OR "
				+ "	  (  "
				+ "	    to_char(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'HH24:MI') >= '"
				+ frmTime
				+ "' "
				+ "	  AND to_char(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'HH24:MI') <= '"
				+ toTime + "'   " + "	  ) " + "	  )";
		if (!leaveBean.getLeaveCode().equals("")) {
			query += " AND LEAVE_APPLICATION_CODE NOT IN("
					+ leaveBean.getLeaveCode() + ") ";
		}
		if (!leaveBean.getLeaveTypeCodeHrs().equals("")) {
			query += " AND HRMS_LEAVE_DTL.LEAVE_CODE  IN("
					+ leaveBean.getLeaveTypeCodeHrs() + ") ";
		}
		if (!leaveBean.getEmpCode().equals("")) {
			query += " AND HRMS_LEAVE_DTL.EMP_ID  IN(" + leaveBean.getEmpCode()
					+ ") ";
		}
		// FOR LEAVE APPLICATION
		query += " UNION SELECT DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY')),DECODE(LEAVE_HRS_FLAG,'Y',TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'HH24:MI'),TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')) FROM HRMS_LEAVE_DTL "
				+ "	 INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE)  WHERE  (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS !='N' and LEAVE_DTL_STATUS !='R' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z') AND ('"
				+ leaveBean.getDateHrs()
				+ "' BETWEEN	 TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-YYYY') AND "
				+ "	TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY')) AND  TO_CHAR(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'HH24:MI') = '00:00' AND TO_CHAR(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'HH24:MI') = '00:00'   ";
		if (!leaveBean.getLeaveCode().equals("")) {
			query += " AND LEAVE_APPLICATION_CODE NOT IN("
					+ leaveBean.getLeaveCode() + ") ";
		}
		if (!leaveBean.getLeaveTypeCodeHrs().equals("")) {
			query += " AND LEAVE_CODE  IN(" + leaveBean.getLeaveTypeCodeHrs()
					+ ") ";
		}
		if (!leaveBean.getEmpCode().equals("")) {
			query += " AND HRMS_LEAVE_DTL.EMP_ID  IN(" + leaveBean.getEmpCode()
					+ ") ";
		}
		final Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = "You already apply leave for this slab("
					+ String.valueOf(data[0][0]) + "  -  "
					+ String.valueOf(data[0][1]) + ")";
		}
		return result;

	}

	/**
	 * This method is used for canceling pending hourly based leave
	 * application.
	 * @param leaveBean
	 * @return boolean
	 */
	public boolean cancelPendingApplicationHrs(LeaveApplication leaveBean) {
		boolean flag = false;
		final Object[][] updateData = new Object[1][2];
		updateData[0][0] = "N";
		final String query = "SELECT NVL(LEAVE_CLOSING_BALANCE,0),TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),TO_CHAR(LEAVE_DAYS_HRS,'HH24:MI'),HRMS_LEAVE_DTL.LEAVE_CODE FROM HRMS_LEAVE_DTL "
				+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_LEAVE_DTL.EMP_ID "
				+ "	AND HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_DTL.LEAVE_CODE) "
				+ "	WHERE LEAVE_APPLICATION_CODE= " + leaveBean.getLeaveCode();
		final Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			updateData[0][1] = leaveBean.getLeaveCode();
			updateBalanceHrs(leaveBean.getEmpCode(),
					String.valueOf(data[0][0]), String.valueOf(data[0][1]),
					String.valueOf(data[0][2]), String.valueOf(data[0][3]),
					"add");
			flag = getSqlModel().singleExecute(getQuery(25), updateData);
			flag = getSqlModel().singleExecute(getQuery(26), updateData);
			flag = getSqlModel().singleExecute(getQuery(27), updateData);
		}
		return flag;
	}

	/**
	 * This method is used for checking hourly based leave application.
	 * @param leaveApplication2
	 * @param userEmpId
	 * @return boolean
	 */
	public boolean checkHrsAppl(LeaveApplication leaveApplication2,
			String userEmpId) {
		final String qq = "SELECT NVL(SFT_LM_HRS_ISENABLED,'N'),SFT_LM_REGL_ISENABLED,SFT_DED_REGL_LM_LEVTYPE,NVL(SFT_DED_LM_IN_SLABS_OF,0),NVL(TO_CHAR(SFT_WORK_HRS,'HH24:MI'),0) "
				+ " ,NVL(TO_CHAR(SFT_START_TIME,'HH24:MI'),'00:00'),NVL(TO_CHAR(SFT_END_TIME ,'HH24:MI'),'00:00') FROM HRMS_SHIFT where SHIFT_ID= (SELECT NVL(EMP_SHIFT,0) FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ userEmpId + ") ";
		final Object[][] shiftCode = getSqlModel().getSingleResult(qq);
		if (shiftCode != null && shiftCode.length > 0) {
			if (String.valueOf(shiftCode[0][0]).equals("Y")
					&& String.valueOf(shiftCode[0][1]).equals("Y")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is used for getting comp off details.
	 * @param leaveAppBean
	 * @return Object[][]
	 */
	public Object[][] getCompoffDetails(LeaveApplication leaveAppBean) {
		boolean result = false;
		Object compOffDateObj[][] = null;
		try {
			String compOffDatesQuery = "  SELECT  TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_DTL.EMP_ID , "
					+ " HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_STATUS "
					+ "  FROM HRMS_EXTRAWORK_APPL_DTL "
					+ "  where HRMS_EXTRAWORK_APPL_Dtl.EMP_ID="
					+ leaveAppBean.getEmpCode()
					+ " and EXTRAWORK_APPL_STATUS='A' ";
			if (!leaveAppBean.getLeaveCode().equals("")) {
				compOffDatesQuery += " AND(EXTRAWORK_IS_LEAVE_APPLIED='Y' OR EXTRAWORK_IS_LEAVE_APPLIED='N' OR EXTRAWORK_LEAVE_APPL_CODE="
						+ leaveAppBean.getLeaveCode()
						+ " AND TO_CHAR(EXTRAWORK_LEAVE_FROMDATE, 'DD-MM-YYYY')='"
						+ leaveAppBean.getOldFromDate()
						+ "' AND TO_CHAR(EXTRAWORK_LEAVE_TODATE, 'DD-MM-YYYY')='"
						+ leaveAppBean.getOldToDate() + "')";
			} else {
				compOffDatesQuery += " AND EXTRAWORK_IS_LEAVE_APPLIED='N'";
			}
			if (!leaveAppBean.getCompOffDays().equals("0")) {
				compOffDatesQuery += " AND TO_DATE('"
						+ leaveAppBean.getLeaveToDtl()
						+ "','DD-MM-YYYY') <= EXTRAWORK_DATE+"
						+ leaveAppBean.getCompOffDays() + "  AND "
						+ " TO_DATE('" + leaveAppBean.getLeaveToDtl()
						+ "','DD-MM-YYYY')>=EXTRAWORK_DATE ";
			}
			compOffDatesQuery += " AND EXTRAWORK_IS_COMPOFF='Y' ORDER BY TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY') ";
			compOffDateObj = getSqlModel().getSingleResult(compOffDatesQuery);
		} catch (Exception e) {
			logger.error("Exception in getCompoffDetails-----------" + e);
		}
		return compOffDateObj;
	}

	/**
	 * This method is used for time card.
	 * @param leaveAppNo
	 * @return boolean
	 */
	public boolean isApplicationUnderProcess(LeaveApplication leaveApp, String leaveAppNo) {
		boolean result = false;
		try {
			Object [][] holidayObj = null;
			final String sqlQuery = "SELECT EMP_ID,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),LEAVE_YEAR,LEAVE_DAYS FROM HRMS_LEAVE_DTLHISTORY "
					+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo;
			final Object[][] insertObj = getSqlModel()
					.getSingleResult(sqlQuery);
			if (insertObj != null && insertObj.length > 0) {
				final String shiftQuery = " SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ insertObj[0][0];
				final Object[][] shiftObj = getSqlModel().getSingleResult(
						shiftQuery);
				for (int j = 0; j < insertObj.length; j++) {
					final String weekOffSql = " SELECT SHIFT_WEEK_1, SHIFT_WEEK_2, SHIFT_WEEK_3, SHIFT_WEEK_4, SHIFT_WEEK_5, SHIFT_WEEK_6 "
							+ " FROM HRMS_SHIFT WHERE SHIFT_ID ="
							+ shiftObj[0][0];
					final Object[][] getWeeklyOffData = getSqlModel()
							.getSingleResult(weekOffSql);
					Calendar currentDay = new Utility()
							.getCalanderDate(checkNull(String
									.valueOf(insertObj[j][1])));
					// UploadAttendanceModel uploadAtt=new
					// UploadAttendanceModel();
					// int count =
					// uploadAtt.isWeeklyOff(currentDay,getWeeklyOffData);
					int count = isWeeklyOff(currentDay, getWeeklyOffData);
					String status = "";
					String applProcess = "Y";
					if (count == 1) {
						status = "WO"; // set status as weekly off
					} else {
						status = "AB";// set status as absent
					}
					
					final String holidayFlag = " SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_HOLIDAY_INCLUDE_FLAG,LEAVE_CODE"
						+ " FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE  LEAVE_POLICY_CODE="
						+ leaveApp.getPolicyCode()
						+ "  AND (LEAVE_HOLIDAY_INCLUDE_FLAG ='Y' AND LEAVE_CODE ="
						+ leaveApp.getLevCode() + ")";
				final Object[][] holidayExcludeObj = getSqlModel().getSingleResult(
						holidayFlag);
				
				
				if (holidayExcludeObj != null && holidayExcludeObj.length >0 ) {
					if (leaveApp.getBrnHDayFlag().equals("true")) {
						final String holStr = "  SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') "
								+ " FROM HRMS_HOLIDAY_BRANCH "
								+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
								+ insertObj[j][0]
								+ ")"
								+ " AND HRMS_HOLIDAY_BRANCH.HOLI_DATE = TO_DATE('"
								+ insertObj[j][1] + "','DD-MM-YYYY') ";
						holidayObj = getSqlModel().getSingleResult(holStr);
					} // end of if
					else {
						final String holStr = "SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
								+ " WHERE HOLI_DATE =TO_DATE('"
								+ insertObj[j][1] + "','DD-MM-YYYY')";
						holidayObj = getSqlModel().getSingleResult(holStr);
					} // end of else
				}
				if (holidayObj != null && holidayObj.length >0) {
					status = "HO"; // set status as weekly off
				} else {
					status = "AB";// set status as absent
				}
					final String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
							+ insertObj[j][2]
							+ " WHERE ATT_DATE=TO_DATE('"
							+ insertObj[j][1]
							+ "','DD-MM-YYYY') AND ATT_EMP_ID='"
							+ insertObj[j][0] + " ' ";

					final Object[][] selectObj = getSqlModel().getSingleResult(
							selectQry);
					double leaveDays = Double.parseDouble(String
							.valueOf(insertObj[j][3]));
					/**
					 * 'In Process' Application Status not display for Weekly
					 * Off and Holiday When Applied Leave contains Weekly Off
					 * And Holiday
					 */
					if (leaveDays > 0) {
						applProcess = "Y";
					} else {
						applProcess = "N";
					}
					if (selectObj != null && selectObj.length > 0) {
						final String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
								+ insertObj[j][2]
								+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE, ATT_REG_STATUS_TWO='AB',"
								+ " IS_APPL_PROCESS='"
								+ applProcess
								+ "'"
								+ " WHERE ATT_DATE=TO_DATE ( '"
								+ insertObj[j][1]
								+ "','DD-MM-YYYY')"
								+ " AND ATT_EMP_ID=" + insertObj[j][0];
						result = getSqlModel().singleExecute(
								myCardTimeActualDataUpdate);
					} else {
						final String actualDataInsertInAtt = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
								+ insertObj[j][2]
								+ "(ATT_DATE, ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO,IS_APPL_PROCESS,ATT_SHIFT_ID) "
								+ "VALUES(TO_DATE('"
								+ insertObj[j][1]
								+ "','DD-MM-YYYY'),"
								+ insertObj[j][0]
								+ ",'"
								+ status
								+ "','AB','AB','AB','"
								+ applProcess
								+ "'," + shiftObj[0][0] + ")";
						result = getSqlModel().singleExecute(
								actualDataInsertInAtt);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This method is used for time card updating is process application status.
	 * @param leaveAppNo
	 */
	public void updateCancelIsprocessApplicationStatus(String leaveAppNo) {
		try {
			final String sqlQuery = "SELECT EMP_ID,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),LEAVE_YEAR FROM HRMS_LEAVE_DTLHISTORY "
					+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo;
			final Object[][] insertObj = getSqlModel()
					.getSingleResult(sqlQuery);
			if (insertObj != null && insertObj.length > 0) {
				for (int j = 0; j < insertObj.length; j++) {
					String actualDataUpdateInAtt = "UPDATE HRMS_DAILY_ATTENDANCE_"
							+ insertObj[j][2]
							+ " SET IS_APPL_PROCESS='N', ATT_REG_STATUS_TWO='AB' "
							+ " WHERE ATT_DATE=TO_DATE('"
							+ insertObj[j][1]
							+ "','DD-MM-YYYY') AND ATT_EMP_ID="
							+ insertObj[j][0];
					getSqlModel().singleExecute(actualDataUpdateInAtt);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for getting no of leave days
	 * @param date
	 * @param empCode
	 * @return double
	 */
	public double getTotalLeaveDaysInDate(String fromDate, String toDate,
			String empCode) {
		double totalDays = 0.0;
		try {
			final String query = " SELECT NVL(SUM(LEAVE_DAYS),'0') FROM HRMS_LEAVE_DTLHISTORY "
					+ " WHERE (LEAVE_TO_DATE >= TO_DATE('"
					+ fromDate
					+ "','DD-MM-YYYY')"
					+ " AND LEAVE_FROM_DATE <= TO_DATE('"
					+ toDate
					+ "','DD-MM-YYYY') )"
					+ " AND EMP_ID ="
					+ empCode
					+ " AND (LEAVE_DTL_STATUS IS NULL OR LEAVE_DTL_STATUS!='R' AND LEAVE_DTL_STATUS!='N' AND LEAVE_DTL_STATUS!='X' AND LEAVE_DTL_STATUS!='Z')";
			final Object[][] totalDaysObj = getSqlModel()
					.getSingleResult(query);
			if (totalDaysObj != null && totalDaysObj.length > 0) {
				if (!totalDaysObj[0][0].equals("0")) {
					totalDays = Double.parseDouble(String
							.valueOf(totalDaysObj[0][0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalDays;
	}

	/**
	 * This method is used for getting total no of leave days for a particular
	 * date.
	 * @param leaveDtlStr
	 * @return Map
	 */
	public Map<String, String> getTotalLevDaysInDate(String[][] leaveDtlStr) {
		Map<String, String> leaveMap = null;
		try {
			leaveMap = new TreeMap<String, String>();
			for (int i = 0; i < leaveDtlStr.length; i++) {
				double leaveAppliedDays = 0.0d;
				double penaltyDays = 0.0d;
				double totalLeaveDays = 0.0d;
				double unadjustedPenaltyDays = 0.0d;
				unadjustedPenaltyDays = Double.parseDouble(leaveDtlStr[i][6]);
				if (unadjustedPenaltyDays == 0.0 || unadjustedPenaltyDays == 0) {
					leaveAppliedDays = Double.parseDouble(leaveDtlStr[i][1]);
					penaltyDays = Double.parseDouble(leaveDtlStr[i][4]);
					totalLeaveDays = leaveAppliedDays + penaltyDays;
				} else {
					totalLeaveDays = Double.parseDouble(leaveDtlStr[i][5])
							+ Double.parseDouble(leaveDtlStr[i][1]);
				}
				if (leaveDtlStr[i][2].equals(leaveDtlStr[i][3])) {
					if (!leaveMap.isEmpty()
							&& leaveMap.containsKey(leaveDtlStr[i][2])) {
						double leaveAppDays = Double.parseDouble(leaveMap
								.get(leaveDtlStr[i][2]));
						// leaveAppDays +=
						// Double.parseDouble(leaveDtlStr[i][1]);						
						leaveAppDays += Double.parseDouble(String
								.valueOf(totalLeaveDays));
						leaveMap.put(leaveDtlStr[i][2], String
								.valueOf(leaveAppDays));
					} // end of if
					else {
						leaveMap.put(leaveDtlStr[i][2], String
								.valueOf(totalLeaveDays));
					}// end of else
				}
			}// end of for loop
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveMap;
	}

	/**
	 * This method is used for checking weekly off
	 * @param currentDay
	 * @param getWeeklyOffData
	 * @return int
	 */
	public int isWeeklyOff(Calendar currentDay, Object getWeeklyOffData[][]) {
		int countDays = 0;
		int DAY_OF_WEEK = currentDay.get(java.util.Calendar.WEEK_OF_MONTH); // Day of week of specified date
		String[] weekDays = null;
		for (int i = 0; i < getWeeklyOffData[0].length; i++) {
			if (i + 1 == DAY_OF_WEEK) {
				weekDays = String.valueOf(getWeeklyOffData[0][i]).split(",");
			}
		}
		for (String string : weekDays) {
			int i = 0;
			try {
				i = Integer.parseInt(string.trim());
			} catch (Exception e) {
				logger.error(e);
				i = 0;
			}
			switch (i) {
			case 1:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 1) {
					countDays++;
				}
				break;
			case 2:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 2) {
					countDays++;
				}
				break;
			case 3:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 3) {
					countDays++;
				}
				break;
			case 4:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 4) {
					countDays++;
				}
				break;
			case 5:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 5) {
					countDays++;
				}
				break;
			case 6:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 6) {
					countDays++;
				}
				break;
			case 7:
				if (currentDay.get(Calendar.DAY_OF_WEEK) == 7) {
					countDays++;
				}
				break;
			default:
				break;
			} // end of switch
		} // end of for loop
		return countDays;
	}
}// end of class