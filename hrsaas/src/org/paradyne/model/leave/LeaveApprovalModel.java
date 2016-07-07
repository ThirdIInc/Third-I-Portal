package org.paradyne.model.leave;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.bean.leave.LeaveApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.model.webservice.WebServiceModel;

/**
 * @author VISHWAMBHAR DESHPANDE
 * @Modified By AA1711
 * 
 */
public class LeaveApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LeaveApprovalModel.class);

	/**
	 * THIS METHOD IS USED FOR DISPLAYING PENDING LEAVE APPLICATION FOR
	 * APPROVAL. OR REJECTION
	 * 
	 * @param levApp
	 * @param request
	 * @param status--------leave
	 *            application status
	 * 
	 */
	public void collect(LeaveApproval levApp, String status,
			HttpServletRequest request) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			final Object[] stat = new Object[3];
			stat[0] = status;
			stat[1] = levApp.getUserEmpId();// user employee id
			stat[2] = levApp.getUserEmpId();// user employee id
			final Object[][] result = getSqlModel().getSingleResult(
					getQuery(1), stat);
			String pathQuery = "	SELECT	LEAVE_COMMENTS FROM HRMS_LEAVE_PATH WHERE "
					+ " LEAVE_ASSESED_BY="
					+ levApp.getUserEmpId()
					+ " "
					+ " AND LEAVE_APPL_CODE=? ";

			// PAGING========
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = result.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) result.length / 20.0;

			java.math.BigDecimal value1 = new java.math.BigDecimal(row);

			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());
			request.setAttribute("abc", rowCount1);

			// PageNo
			if (String.valueOf(levApp.getMyPage()).equals("null")
					|| String.valueOf(levApp.getMyPage()).equals(null)
					|| String.valueOf(levApp.getMyPage()).equals(" ")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;

				if (To_TOT > result.length) {
					To_TOT = result.length;
				}

				levApp.setMyPage("1");
			}

			else {

				pg1 = Integer.parseInt(levApp.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				} else {
					// From_TOTAL=To_TOTAL+1;
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}
				if (To_TOT > result.length) {
					To_TOT = result.length;
				}
			}
			request.setAttribute("xyz", PageNo1);

			for (int i = From_TOT; i < To_TOT; i++) {
				LeaveApproval bean1 = new LeaveApproval();
				bean1.setLeaveAppNo((String.valueOf(result[i][0])));// application
				// code
				bean1.setEmpCode(String.valueOf(result[i][1]));// employee id
				bean1.setEmpName(String.valueOf(result[i][2]));// employee name
				bean1.setDate(String.valueOf(result[i][3]));// application date
				bean1.setCheckStatus(String.valueOf(result[i][4]));// status
				if (status.equals("A")) {
					bean1.setStatusNew("Approved");
				} // end of if
				if (status.equals("R")) {
					bean1.setStatusNew("Rejected");
				} // end of if
				bean1.setLevel(String.valueOf(result[i][5]));// level
				bean1.setTokenNo(String.valueOf(result[i][6]));// employee
				// token
				final Object[][] data = getSqlModel().getSingleResult(
						pathQuery,
						new Object[] { String.valueOf((result[i][0])) });

				if (data == null || data.length == 0 || data.equals(null)) {
					bean1.setStatusRemark("");
				}// end of if
				else {
					bean1
							.setStatusRemark(checkNull(String
									.valueOf(data[0][0])));
				}// end of else
				tableList.add(bean1);

			}// end of for
		} // //end of try
		catch (Exception e) {
			// TODO Auto-generated catch
			logger.error("Exception in collect----------" + e);
		}// end of catch
		levApp.setList(tableList);
		if (tableList.size() != 0) {
			levApp.setListLength("1");
			levApp.setNoData("false");
		}// //end of if
		else {
			levApp.setListLength("0");
			levApp.setNoData("true");
		}// end of else
	}// end of collect

	/**
	 * Gets reporting officer's employee id from reporting structure for a
	 * particular employee
	 */
	/**
	 * @param empCode -:
	 *            Specifies employee id of employee whose reporting structure is
	 *            defined
	 * @param type -:
	 *            Specifies the purpose
	 * @param order -:
	 *            Specifies the level
	 * @return Object[][] as employee id of the reporting officer
	 */
	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		ReportingModel reporting = new ReportingModel();
		reporting.initiate(context, session);
		final Object result[][] = reporting.generateEmpFlow(empCode, type,
				order);
		reporting.terminate();
		return result;
	}

	/**@modified by @author Prajakta.Bhandare
	 * @date 12 March 2013
	 * THIS METHOD FOR SAVING STATUS ,LEAVE APPLICATION CODE,REMARKS,LEAVE
	 * APPLICATION DATE AND LEAVE ASSESSED BY CODE
	 * 
	 * @param levApp
	 * @param empCode
	 * @param status---------leave
	 *            application status
	 * @param leaveAppNo-----leave
	 *            application number
	 * @param remarks---approver
	 *            remarks
	 */

	public String approveReject(HttpServletRequest request, String empCode,
			String leaveAppNo, String status, String remarks,
			String approverId, String level,String isAdminApprovalClick) {
		String policyCode = getLeavePolicyCode(empCode);
		String Source = "";

		boolean result = false;
		String applStatus = "pending";
		Object[][] empFlow = null;

		final Object[][] changeStatus = new Object[1][4];
		changeStatus[0][0] = leaveAppNo; // application code
		changeStatus[0][1] = approverId; // user employee id
		changeStatus[0][2] = status; // status
		changeStatus[0][3] = remarks; // remark
		if (String.valueOf(status).equals("A")) {
			String balQuery = " SELECT NVL(LEAVE_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE='"
					+ changeStatus[0][0]
					+ "' ";
			final Object[][] balObj = getSqlModel().getSingleResult(balQuery);

			// METHODS FROM ACTION
			empFlow = generateEmpFlow(empCode, "Leave",
					Integer.parseInt(level) + 1);
			applStatus = changeApplStatus(empFlow, String.valueOf(leaveAppNo),
					empCode, status,isAdminApprovalClick,approverId);

		}// end of if
		if (String.valueOf(status).equals("R")) {
			final Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status); // status
			reject[0][1] = String.valueOf(leaveAppNo);// application code

			final String leaveDtlRecord = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo + " ";

			final Object[][] leaveDtlRecordObj = getSqlModel().getSingleResult(
					leaveDtlRecord);

			String balQuery = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE='"
					+ changeStatus[0][0]
					+ "' ";
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
			}// end of for
			if (balObj.length > 0) {
				balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? ,LEAVE_ONHOLD=CASE WHEN LEAVE_ONHOLD<=0 THEN 0 ELSE NVL(LEAVE_ONHOLD,0) - ? END "
						+ " WHERE EMP_ID =? AND LEAVE_CODE =?";
				result = getSqlModel().singleExecute(balQuery, balObj);

			} // end of if
			getSqlModel().singleExecute(getQuery(3), reject);// hrms_leave_hdr
			// leave status
			// update
			getSqlModel().singleExecute(getQuery(6), reject);// hrms_leave_dtl
			// leave status
			// update
			getSqlModel().singleExecute(getQuery(8), reject);// hrms_leave_dtlhistory
			// leave status
			// update

			final String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";

			final Object compOffObj[][] = getSqlModel().getSingleResult(
					compOffAppQuery);

			if (compOffObj != null && compOffObj.length > 0) {
				for (int i = 0; i < leaveDtlRecordObj.length; i++) {
					Double counter = 0.0;
					int value = 0;
					Object[][] compOffDateObj = null;
					if (String.valueOf(compOffObj[0][0]).equals(
							String.valueOf(leaveDtlRecordObj[i][2]))) {

						String compOffDatesQuery = " SELECT  TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_DTL.EMP_ID , "
								+ " HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_STATUS   FROM HRMS_EXTRAWORK_APPL_DTL  "
								+ " WHERE HRMS_EXTRAWORK_APPL_DTL.EMP_ID="
								+ empCode
								+ " AND EXTRAWORK_APPL_STATUS='A'  AND EXTRAWORK_IS_LEAVE_APPLIED='Y' "
								+ " AND EXTRAWORK_LEAVE_APPL_CODE="
								+ changeStatus[0][0] + " ";

						if (!String.valueOf(compOffObj[0][1]).equals("0")) {
							compOffDatesQuery += " AND TO_DATE('"
									+ String.valueOf(leaveDtlRecordObj[i][3])
									+ "','DD-MM-YYYY') < EXTRAWORK_DATE+"
									+ String.valueOf(compOffObj[0][1]) + " ";
						}
						compOffDatesQuery += " AND EXTRAWORK_IS_COMPOFF='Y' ORDER BY TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY')";
						logger
								.info("compOffDatesQuery in leave approval==========="
										+ compOffDatesQuery);
						compOffDateObj = getSqlModel().getSingleResult(
								compOffDatesQuery);
						counter += Double.parseDouble(String
								.valueOf(leaveDtlRecordObj[i][0]));
						value = counter.intValue();

						if (compOffDateObj != null && compOffDateObj.length > 0) {
							if (compOffDateObj.length >= value) {

								String updateCompOffRecord = " UPDATE HRMS_EXTRAWORK_APPL_DTL  SET EXTRAWORK_IS_LEAVE_APPLIED='N',EXTRAWORK_LEAVE_APPL_CODE='',EXTRAWORK_LEAVE_FROMDATE='',EXTRAWORK_LEAVE_TODATE='' "
										+ " WHERE EMP_ID = "
										+ empCode
										+ " AND EXTRAWORK_LEAVE_APPL_CODE="
										+ changeStatus[0][0];
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
								logger
										.info("updateCompOffRecord in leave approval=============="
												+ updateCompOffRecord);
								getSqlModel()
										.singleExecute(updateCompOffRecord);
							}
						}
					}
				}
			}
			applStatus = "rejected";
		}// end of if
		getSqlModel().singleExecute(getQuery(2), changeStatus);// insert record
		// into
		// hrms_leave_path

		createTemplateWithAlerts(request, status, leaveAppNo, remarks, empCode,
				approverId, level, empFlow, applStatus,isAdminApprovalClick);

		return applStatus;
	}// end of forward

	/**
	 * THIS METHOD IS USED FOR CHANGING PENDING LEAVE APPLICATION STATUS AS
	 * APPROVE OR REJECT
	 * 
	 * @param levApp
	 * @param request
	 * @param empFlow---------it
	 *            gives approver id
	 * @param leaveAppNo-----leave
	 *            application number
	 * @param empId--------employee
	 *            id
	 * @return boolean
	 */

	public String changeApplStatus(Object[][] empFlow, String leaveAppNo,
			String empId, String status, String isAdminApprovalClick, String approverId) {
		String applStatus = "pending";
		boolean result = false;
		try {

			if (empFlow != null && empFlow.length != 0) {

				final Object[][] updateApprover = new Object[1][4];
				updateApprover[0][0] = empFlow[0][2]; // level
				updateApprover[0][1] = empFlow[0][0]; // approver id
				updateApprover[0][2] = empFlow[0][3]; // alternate approver id
				updateApprover[0][3] = leaveAppNo; // application code
				result = getSqlModel().singleExecute(getQuery(4),
						updateApprover);
				final String updateDtlApproverQuery = "UPDATE HRMS_LEAVE_DTL  SET LEAVE_APPROVED_BY="
						+ updateApprover[0][1]
						+ "  WHERE LEAVE_APPLICATION_CODE="
						+ updateApprover[0][3];
				result = getSqlModel().singleExecute(updateDtlApproverQuery);
				applStatus = "forwarded";
			} // end of if
			else {

				LeaveApplicationModel levModel = new LeaveApplicationModel();
				levModel.initiate(context, session);
				final Object[][] updateData = new Object[1][2];
				updateData[0][0] = "A";
				final String query = "SELECT NVL(LEAVE_CLOSING_BALANCE,0),TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),TO_CHAR(LEAVE_DAYS_HRS,'HH24:MI'),HRMS_LEAVE_DTL.LEAVE_CODE,HRMS_LEAVE_DTL.EMP_ID FROM HRMS_LEAVE_DTL "
						+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_LEAVE_DTL.EMP_ID "
						+ "	AND HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_DTL.LEAVE_CODE) "
						+ " INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
						+ "	WHERE  LEAVE_HRS_FLAG='Y' AND LEAVE_APPLICATION_CODE= "
						+ leaveAppNo;
				final Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					updateData[0][1] = leaveAppNo;
					levModel.updateBalanceHrs(String.valueOf(data[0][4]),
							String.valueOf(data[0][0]), String
									.valueOf(data[0][1]), String
									.valueOf(data[0][2]), String
									.valueOf(data[0][3]), "");
					getSqlModel().singleExecute(getQuery(25), updateData);
					getSqlModel().singleExecute(getQuery(26), updateData);
					getSqlModel().singleExecute(getQuery(27), updateData);
					applStatus = "approved";

				} else {
					final Object[][] statusChanged = new Object[1][2];
					statusChanged[0][0] = "A"; // status
					statusChanged[0][1] = leaveAppNo; // application code
					String updateQuery="UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS='"+statusChanged[0][0]+"'";
					if((isAdminApprovalClick.equals("true"))){
						updateQuery+=",LEAVE_APPROVEDBY_ADMIN ="+approverId;
					}
					updateQuery+=" WHERE LEAVE_APPL_CODE="+statusChanged[0][1];
					getSqlModel().singleExecute(updateQuery);// hrms_leave_hdr
					// leave
					// status
					// update
					result = getSqlModel().singleExecute(getQuery(6),
							statusChanged); // hrms_leave_dtl
					// leave
					// status
					// update
					result = getSqlModel().singleExecute(getQuery(8),
							statusChanged); // hrms_leave_dtlhistory
					// leave
					// status
					// update
					if (result) {
						setLeaveBalAndOnhold(leaveAppNo, empId);
					}// end of if
					applStatus = "approved";
				}

			}
			// collect(leaveApp ,status, request);
			// Janisha Start here
			if (applStatus.equals("approved")) {
				insertAttendenceData(leaveAppNo);

				try {
					callWebService(leaveAppNo, "I");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			// End janisha
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;
	}// end of changeApplStatus
	/**
	 * This method is used for inserting approved leave application data into
	 * attendance table.
	 * @param leaveAppNo
	 * @return boolean
	 */
	public boolean insertAttendenceData(String leaveAppNo) {
		boolean result = false;
		try {
			final String sqlQuery = "SELECT EMP_ID,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),LEAVE_YEAR, LEAVE_DAYS FROM HRMS_LEAVE_DTLHISTORY "
					+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo;
			final Object[][] insertObj = getSqlModel()
					.getSingleResult(sqlQuery);

			if (insertObj != null && insertObj.length > 0) {

				final String shiftQuery = " SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ insertObj[0][0];
				final Object[][] shiftObj = getSqlModel().getSingleResult(
						shiftQuery);

				for (int j = 0; j < insertObj.length; j++) {

					final String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
							+ insertObj[j][2]
							+ " WHERE ATT_DATE=TO_DATE('"
							+ insertObj[j][1]
							+ "','DD-MM-YYYY') AND ATT_EMP_ID='"
							+ insertObj[j][0] + " ' ";

					final Object[][] selectObj = getSqlModel().getSingleResult(
							selectQry);
					/*To check for Half Day Leave 
 					  In Attendance Table Change Status for Half Day Leave*/
					double leaveDays=Double.parseDouble(String.valueOf(insertObj[j][3]));
					//int leaveDays= Integer.parseInt(str);
					if (leaveDays == 0.5) {
						if (selectObj != null && selectObj.length > 0) {
							final String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ insertObj[j][2]
									+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,ATT_REG_STATUS_TWO='HL'"
									+ " WHERE ATT_DATE=TO_DATE ( '"
									+ insertObj[j][1]
									+ "','DD-MM-YYYY')"
									+ " AND ATT_EMP_ID=" + insertObj[j][0];

							result = getSqlModel().singleExecute(
									myCardTimeActualDataUpdate);
						} else {
							final String actualDataInsertInAtt = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
									+ insertObj[j][2]
									+ "(ATT_DATE, ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO,ATT_SHIFT_ID) "
									+ "VALUES(TO_DATE('"
									+ insertObj[j][1]
									+ "','DD-MM-YYYY'),"
									+ insertObj[j][0]
									+ ",'AB','AB','AB','HL',"
									+ shiftObj[0][0]
									+ ")";

							result = getSqlModel().singleExecute(
									actualDataInsertInAtt);
						}
					}

						
					else {
						if (selectObj != null && selectObj.length > 0) {
							final String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ insertObj[j][2]
									+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,ATT_REG_STATUS_TWO='LV'"
									+ " WHERE ATT_DATE=TO_DATE ( '"
									+ insertObj[j][1]
									+ "','DD-MM-YYYY')"
									+ " AND ATT_EMP_ID=" + insertObj[j][0];

							result = getSqlModel().singleExecute(
									myCardTimeActualDataUpdate);
						} else {
							final String actualDataInsertInAtt = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
									+ insertObj[j][2]
									+ "(ATT_DATE, ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO,ATT_SHIFT_ID) "
									+ "VALUES(TO_DATE('"
									+ insertObj[j][1]
									+ "','DD-MM-YYYY'),"
									+ insertObj[j][0]
									+ ",'AB','AB','AB','LV',"
									+ shiftObj[0][0]
									+ ")";

							result = getSqlModel().singleExecute(
									actualDataInsertInAtt);
						}
					}
					if (result) {
						final String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
								+ insertObj[j][2]
								+ " SET IS_APPL_PROCESS='N' "
								+ " WHERE ATT_DATE=TO_DATE ( '"
								+ insertObj[j][1]
								+ "','DD-MM-YYYY')"
								+ " AND ATT_EMP_ID=" + insertObj[j][0];

						result = getSqlModel().singleExecute(
								myCardTimeActualDataUpdate);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * THIS METHOD IS USED FOR CALCULATING LEAVE ABLANCE AND ON HOLD LEAVES
	 * PENDING FOR APPROVAL
	 * 
	 * @param levApp
	 * @param leaveAppNo---leave
	 *            application number
	 * @return boolean
	 */
	public boolean setLeaveBalAndOnhold(String leaveAppNo, String empId) {
		String policyCode = getLeavePolicyCode(empId);

		boolean result = false;
		String balQuery = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
				+ " WHERE LEAVE_APPLICATION_CODE='" + leaveAppNo + "' ";
		final Object[][] balObj = getSqlModel().getSingleResult(balQuery);
		/**
		 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE APPLICABLE
		 * LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
		 */
		for (int j = 0; j < balObj.length; j++) {
			final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE LEAVE_POLICY_CODE="
					+ policyCode
					+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][2]);
			final Object[][] zeroBalance = getSqlModel().getSingleResult(
					zeroBlncQuery);
			if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
				// balObj[j][0] ="0"; //LEAVE DAYS SET TO BE ZERO
			}// end of if
		}// end of for
		if (balObj.length > 0) {
			// update leave balance and onhold
			balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_ONHOLD=CASE WHEN LEAVE_ONHOLD<=0 THEN 0 ELSE NVL(LEAVE_ONHOLD,0) - ? END "
					+ " WHERE EMP_ID =? AND LEAVE_CODE =?";
			result = getSqlModel().singleExecute(balQuery, balObj);
		}// end of if
		return result;
	}// end of setLeaveBalAndOnhold

	/**@modified by @author Prajakta.Bhandare
	 * @date 12 March 2013
	 * This method is used for sending mail
	 * 
	 * @param request
	 * @param status
	 * @param leaveAppNo
	 * @param remarks
	 * @param empCode
	 * @param approverId
	 * @param level
	 * @param empFlow
	 * @param applStatus
	 */
	public void createTemplateWithAlerts(HttpServletRequest request,
			String status, String leaveAppNo, String remarks, String empCode,
			String approverId, String level, Object[][] empFlow,
			String applStatus,String isAdminApprovalClick) {
		// --------Email Templates + Process Manager Alert--------------start
		try {
			if (!String.valueOf(status).equals("P")) {

				String alternateApprover = "";
				String actualStataus = "";
				String keepInfo = "";
				final String keepInformedId = " SELECT  LEAVE_KEEP_INFORMED,APPROVED_BY,ALTER_APPROVER FROM HRMS_LEAVE_HDR "
						+ " WHERE LEAVE_APPL_CODE=" + leaveAppNo;

				final Object[][] keepInformedObj = getSqlModel()
						.getSingleResult(keepInformedId);

				/**
				 * Remove process manager entry from mypage.
				 */
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				String module = "Leave";
				processAlerts.removeProcessAlert(String.valueOf(leaveAppNo),
						module);

				String link = "";
				String linkParam = "";

				String applicant = "", oldApprover = "", newApprover = "";
				try {
					applicant = empCode;
					oldApprover = approverId;
					if (status.equals("B") || status.equals("R")) {
						newApprover = "";
					} else {
						newApprover = String.valueOf(empFlow[0][0]);
					}
				} catch (Exception e) {
					logger.error(e);
					// e.printStackTrace();
				}
				if (status.equals("A")) {
					actualStataus = "Approved";
				}
				if (status.equals("R")) {
					actualStataus = "Rejected";
				}

				String empID = "", msgType = "";
				final String applnID = String.valueOf(leaveAppNo);
				final String alertLevel = String.valueOf(Integer
						.parseInt(level));// + 1

				if (Integer.parseInt(level) == 1) {
					empFlow = generateEmpFlow(empCode, "Leave", Integer
							.parseInt(level));
				} else {
					empFlow = generateEmpFlow(empCode, "Leave", Integer
							.parseInt(level));
				}

				if (!newApprover.equals("")) {
					// send alert from oldApprover to newApprover
					empID = newApprover;
					msgType = "A";

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template.setEmailTemplate("MAIL SENT TO SECOND APPROVER");

					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, newApprover);

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applicant);
					templateQuery5.setParameter(2, applnID);
					templateQuery5.setParameter(3, applicant);

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, newApprover);
					final String applicationType = "LeaveAppl";
					// Add approval link -pass parameters to the link
					final String[] link_param = new String[3];
					final String[] link_label = new String[3];
					/*HttpServletRequest request,
			String empCode, String leaveAppNo, String status, String remarks,
			String approverId, String level,String isAdminApprovalClick*/
					link_param[0] = applicationType + "#" + applicant + "#"
							+ applnID + "#" + "A" + "#" + "..." + "#"
							+ newApprover + "#" + alertLevel + "#" + isAdminApprovalClick;
					link_param[1] = applicationType + "#" + applicant + "#"
							+ applnID + "#" + "R" + "#" + "..." + "#"
							+ newApprover + "#" + alertLevel+ "#" + isAdminApprovalClick;
					link_param[2] = applicationType + "#" + applicant + "#"
							+ applnID + "#" + "B" + "#" + "..." + "#"
							+ newApprover + "#" + alertLevel+ "#" + isAdminApprovalClick;

					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";

					template.configMailAlert();

					try {
						link = "/leaves/LeaveApplication_retriveForApproval.action";
						linkParam = "leaveApplicationNo=" + applnID
								+ "&applicationStatus=" + "P";
						template.sendProcessManagerAlert(newApprover, module,
								msgType, applnID, alertLevel, linkParam, link,
								actualStataus, applicant, "", "", "",
								oldApprover);

					} catch (Exception e) {
						logger.error(e);
					}
					template.addOnlineLink(request, link_param, link_label);
					template.sendApplicationMail();

					template.clearParameters();
					template.terminate();

					// send alert from oldApprover to applicant
					empID = applicant;
					msgType = "I";

					EmailTemplateBody template1 = new EmailTemplateBody();
					template1.initiate(context, session);

					template1
							.setEmailTemplate("Mail to employee regarding first approval");

					template1.getTemplateQueries();

					EmailTemplateQuery templateQuery7 = template1
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery7.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery8 = template1
							.getTemplateQuery(2); // TO EMAIL
					templateQuery8.setParameter(1, applicant);

					EmailTemplateQuery templateQuery9 = template1
							.getTemplateQuery(3);
					templateQuery9.setParameter(1, applnID);

					EmailTemplateQuery templateQuery10 = template1
							.getTemplateQuery(4);
					templateQuery10.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery11 = template1
							.getTemplateQuery(5);
					templateQuery11.setParameter(1, applicant);
					templateQuery11.setParameter(2, applnID);
					templateQuery11.setParameter(3, applicant);

					EmailTemplateQuery templateQuery12 = template1
							.getTemplateQuery(6);
					templateQuery12.setParameter(1, newApprover);

					// Added approver comments

					EmailTemplateQuery templateQuery13 = template1
							.getTemplateQuery(7);
					templateQuery13.setParameter(1, applnID);

					template1.configMailAlert();
					alternateApprover = (empFlow != null && !String.valueOf(
							empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";

					oldApprover = String.valueOf(empFlow[0][0]);

					link = "/leaves/LeaveApplication_retriveDetails.action";
					linkParam = "levApplicationCode=" + applnID
							+ "&levStatus=R";

					if (status.equals("B")) {
						actualStataus = "SentBack";
						template1.sendProcessManagerAlert("", module, "A",
								applnID, alertLevel, linkParam, link,
								actualStataus, applicant, "", "", applicant,
								oldApprover);

						template1.sendProcessManagerAlert(oldApprover, module,
								"I", applnID, alertLevel, linkParam, link,
								actualStataus, applicant, alternateApprover,
								keepInfo, "", oldApprover);

					} else {
						template1.sendProcessManagerAlert(oldApprover, module,
								"I", applnID, alertLevel, linkParam, link,
								actualStataus, applicant, alternateApprover,
								keepInfo, applicant, oldApprover);
					}

					if (keepInformedObj != null && keepInformedObj.length > 0) {
						keepInfo = String.valueOf(keepInformedObj[0][0]);
						template1.sendApplicationMailToKeepInfo(keepInfo);
					}

					try {
						template1.sendApplicationMail();
					} catch (Exception e) {
						e.printStackTrace();
					}

					template1.clearParameters();
					template1.terminate();
				} else {
					// send alert from oldApprover to applicant
					empID = applicant;

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template
							.setEmailTemplate("Mail to employee regarding second approval");

					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, applicant);

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applicant);
					templateQuery5.setParameter(2, applnID);
					templateQuery5.setParameter(3, applicant);

					// Added for approver details

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, applnID);

					template.configMailAlert();
					try {
						link = "/leaves/LeaveApplication_retriveDetails.action";
						linkParam = "levApplicationCode=" + applnID
								+ "&levStatus=" + status;
						if(isAdminApprovalClick.equals("true")){
							empFlow = generateEmpFlow(empCode, "Leave", 1);
							if (!String.valueOf(empFlow[0][0]).equals("0")) {								
								template.sendApplicationMailToAlternateApprover(String
										.valueOf(empFlow[0][0]));
							}
							if (!String.valueOf(empFlow[0][3]).equals("0")) {								
								template.sendApplicationMailToAlternateApprover(String
										.valueOf(empFlow[0][3]));
							}		
						}
						else if (level.equals("1")) {
							empFlow = generateEmpFlow(empCode, "Leave", Integer
									.parseInt(level));
						} else {
							empFlow = generateEmpFlow(empCode, "Leave", Integer
									.parseInt(level) - 1);
						}
						alternateApprover = (empFlow != null && !String
								.valueOf(empFlow[0][3]).equals("0")) ? String
								.valueOf(empFlow[0][3]) : "";
						
						String keepInformedto = "";
						keepInformedto = !checkNull(
								String.valueOf(keepInformedObj[0][0])).equals(
								"") ? String.valueOf(keepInformedObj[0][0])
								: "";
						String ccId = (empFlow != null && !String.valueOf(
								empFlow[0][0]).equals("0")) ? String
								.valueOf(empFlow[0][0]) : "";
						if (!keepInformedto.equals("")) {
							ccId += "," + keepInformedto;
						}
						if(keepInformedObj!= null && keepInformedObj.length>0){
							String keepInformStr= String.valueOf(keepInformedObj[0][0]);
							template.sendApplicationMailToKeepInfo(keepInformStr);
						}

						if (status.equals("B")) {
							actualStataus = "SentBack";
							template.sendProcessManagerAlert("", module, "A",
									applnID, alertLevel, linkParam, link,
									actualStataus, applicant, "", "",
									applicant, oldApprover);

							linkParam = "levApplicationCode=" + applnID
									+ "&levStatus=R";

							template.sendProcessManagerAlert(oldApprover,
									module, "I", applnID, alertLevel,
									linkParam, link, actualStataus, applicant,
									alternateApprover, ccId, "", oldApprover);

						} else {
							template.sendProcessManagerAlert(oldApprover,
									module, "I", applnID, alertLevel,
									linkParam, link, actualStataus, applicant,
									alternateApprover, ccId, applicant,
									oldApprover);
						}

					} catch (Exception e) {
						logger.error(e);

						e.printStackTrace();
					}
					template.sendApplicationMail();		
					if(keepInformedObj!= null && keepInformedObj.length>0){
						String keepInformStr= String.valueOf(keepInformedObj[0][0]);
						template.sendApplicationMailToKeepInfo(keepInformStr);
					}
					template.clearParameters();
					template.terminate();
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		// --------Email Templates + Process Manager Alert--------------end
	}

	/**
	 * THIS METHOD IS USED FOR DISPLAYING LIST OF APPROVED LEAVE APPLICATION FOR
	 * CANCELLATION
	 * 
	 * @param cancel
	 */
	public void generateListForCancel(LeaveApplication cancel) {
		String cancelQuery = "";
		if (cancel.isGeneralFlag()) {
			String query = " SELECT EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID "
					+ "	FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ "  WHERE EMP_ID ='" + cancel.getUserEmpId() + "'"
					+ " ORDER BY EMP_ID  ";
			final Object[][] userData = getSqlModel().getSingleResult(query);
			if (userData != null && userData.length > 0) {
				cancel.setTokenNo(checkNull(String.valueOf(userData[0][0])));// token
				cancel.setEmpName(checkNull(String.valueOf(userData[0][1])));// employee
				// name
				cancel.setBranchName(checkNull(String.valueOf(userData[0][2])));// branch
				cancel
						.setDesignation(checkNull(String
								.valueOf(userData[0][3]))); // designation
				cancel.setEmpId(String.valueOf(userData[0][4])); // employee
				// id
			}// end of nested if
		}// end of if
		cancelQuery = " SELECT LEAVE_APPL_CODE,HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " NVL(TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),' '), "
				+ " DECODE(LEAVE_STATUS,'A','Approved'),HRMS_EMP_OFFC.EMP_ID "
				+ " FROM HRMS_LEAVE_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_HDR.EMP_ID) "
				+ " WHERE (HRMS_LEAVE_HDR.APPROVED_BY ='"
				+ cancel.getUserEmpId()
				+ "' OR ALTER_APPROVER='"
				+ cancel.getUserEmpId()
				+ "') AND LEAVE_STATUS ='A' AND LEAVE_APPL_DATE+60 > SYSDATE   ";
		final Object[][] data = getSqlModel().getSingleResult(cancelQuery);
		ArrayList<Object> cancelList = new ArrayList<Object>();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				LeaveApplication cancelBean = new LeaveApplication();
				cancelBean.setLeaveCode(String.valueOf(data[i][0]));// leave id
				cancelBean.setTokenNo(checkNull(String.valueOf(data[i][1])));// token
				cancelBean.setEmpName(checkNull(String.valueOf(data[i][2])));// employee
				// name
				cancelBean.setApplicationDate(checkNull(String
						.valueOf(data[i][3]))); // application date
				cancelBean.setStatus(checkNull(String.valueOf(data[i][4])));// status
				cancelBean.setEmpId(String.valueOf(data[i][5]));// employee id
				cancelList.add(cancelBean);
			}// end of for
			cancel.setCancelList(cancelList);
		} // end of if
	}// end of generateListForCancel

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
	 * THIS METHOD IS USED FOR CANCELLING LEAVE APPLICATION
	 * 
	 * @param values-------leave
	 *            application code
	 * @param leave
	 * @param empFlow---------it
	 *            gives approver id
	 * @return boolean
	 */
	public boolean cancelApplication(String[] values, LeaveApplication leave,
			String[] empFlow) {
		final String policyCode = getLeavePolicyCode(leave.getEmpCode());

		for (int i = 0; i < values.length; i++) {
			String balQuery = " SELECT NVL(LEAVE_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE='" + values[i] + "' ";
			final Object[][] balObj = getSqlModel().getSingleResult(balQuery);
			/**
			 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE
			 * APPLICABLE LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
			 */
			for (int j = 0; j < balObj.length; j++) {
				final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][2]);
				final Object[][] zeroBalance = getSqlModel().getSingleResult(
						zeroBlncQuery);
				if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
					balObj[j][0] = "0"; // leave days set to be zero
				}// end of if
			}// end of for
			if (balObj.length > 0) {
				balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? "
						+ " WHERE EMP_ID =? AND LEAVE_CODE =?";
				boolean result = getSqlModel().singleExecute(balQuery, balObj);
				if (result) {
					final String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='N' WHERE LEAVE_APPL_CODE ='"
							+ values[i] + "' ";
					getSqlModel().singleExecute(updateHdr);
					final String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'N'  "
							+ " WHERE LEAVE_APPLICATION_CODE='"
							+ values[i]
							+ "' ";
					getSqlModel().singleExecute(updateDtl);
					final String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'N'  "
							+ " WHERE LEAVE_APPLICATION_CODE='"
							+ values[i]
							+ "' ";
					getSqlModel().singleExecute(updateDtlHistory);
					final String Query = "UPDATE HRMS_LEAVE_HDR SET LEAVE_APP_CANCEL_WITH='"
							+ empFlow[i]
							+ "' WHERE LEAVE_APPL_CODE="
							+ values[i] + " ";
					getSqlModel().singleExecute(Query);

				}// end of nested if
			}// end of if
		}// end of for
		return true;
	}// end of cancelApplication

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE DETAILS.
	 * 
	 * @param leaveApplication
	 */

	public void getLeaveDtlHistory(LeaveApplication leaveApplication) {

		final String query = "  SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),	"
				+ " TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY'),NVL(LEAVE_DAYS,0) FROM HRMS_LEAVE_DTL "
				+ " INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE_DTL.LEAVE_CODE = HRMS_LEAVE.LEAVE_ID)	"
				+ " LEFT JOIN  HRMS_LEAVE_BALANCE ON (HRMS_LEAVE.LEAVE_ID =HRMS_LEAVE_BALANCE.LEAVE_CODE "
				+ " AND HRMS_LEAVE_BALANCE.EMP_ID = "
				+ leaveApplication.getEmpCode()
				+ ")"
				+ " WHERE LEAVE_APPLICATION_CODE="
				+ leaveApplication.getLevAppCode()
				+ "  AND HRMS_LEAVE_DTL.EMP_ID="
				+ leaveApplication.getEmpCode() + " ";

		final Object[][] values = getSqlModel().getSingleResult(query);

		ArrayList<Object> list = new ArrayList<Object>();
		try {
			for (int i = 0; i < values.length; i++) {
				LeaveApplication levApp = new LeaveApplication();
				levApp.setLeaveTypeCode(String.valueOf(values[i][0]));// leave
				// code
				levApp.setLeaveType(String.valueOf(values[i][1]));// leave
				// type
				levApp.setLeaveFromDate(String.valueOf(values[i][2]));// leave
				// from
				// date
				levApp.setLeaveTodate(String.valueOf(values[i][3]));// leave to
				// date
				levApp.setLeaveDays(String.valueOf(values[i][4]));// leave
				// days

				list.add(levApp);
			}// end of for loop
			leaveApplication.setAtt(list);

		}// end of try

		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getLeaveDtlHistory-----------" + e);
		}// end of catch
	}// end of getLeaveDtlHistory

	/**
	 * THIS METHOD IS USED FOR CANCEL LEAVE APPLICATION
	 * 
	 * @param leaveApplication
	 * @return boolean
	 */
	public boolean cancelLeaves(LeaveApplication leaveApplication) {
		// TODO Auto-generated method stub
		final String balQuery = " SELECT NVL(LEAVE_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
				+ " WHERE LEAVE_APPLICATION_CODE="
				+ leaveApplication.getLevAppCode() + " ";

		final Object[][] balObj = getSqlModel().getSingleResult(balQuery);

		if (balObj.length > 0) {
			final String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='C' WHERE LEAVE_APPL_CODE ="
					+ leaveApplication.getLevAppCode() + " ";
			getSqlModel().singleExecute(updateHdr);
			final String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'C'  "
					+ " WHERE LEAVE_APPLICATION_CODE="
					+ leaveApplication.getLevAppCode() + " ";
			getSqlModel().singleExecute(updateDtl);
			final String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'C'  "
					+ " WHERE LEAVE_APPLICATION_CODE="
					+ leaveApplication.getLevAppCode() + " ";
			getSqlModel().singleExecute(updateDtlHistory);
		}// end of if
		return true;

	}// end of cancelLeaves

	/**
	 * This method is used for getting leave policy code for an employee.
	 * 
	 * @param empId
	 * @return String
	 */
	public String getLeavePolicyCode(String empId) {
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		final String leavePolicyCode = model.getLeavePolicy(empId);
		model.terminate();
		return leavePolicyCode;
	}

	/**
	 * This method is used for Online approve/reject
	 * 
	 * @param request
	 * @param empCode
	 * @param leaveAppNo
	 * @param status
	 * @param remarks
	 * @param approverId
	 * @param level
	 * @return
	 */
	public String onlineApproveReject(HttpServletRequest request,
			String empCode, String leaveAppNo, String status, String remarks,
			String approverId, String level,String isAdminApprovalClick) {
		String result = "";
		String res = "";
		final String query = " SELECT APPROVED_BY,LEAVE_STATUS,LEAVE_LEVEL FROM HRMS_LEAVE_HDR WHERE EMP_ID="
				+ empCode + " AND LEAVE_APPL_CODE=" + leaveAppNo;
		final Object approverIdObj[][] = getSqlModel().getSingleResult(query);
		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverId)
					&& String.valueOf(approverIdObj[0][1]).equals("P")) {
				level = String.valueOf(approverIdObj[0][2]);
				res = approveRejectSendBackLevApp(request, empCode, leaveAppNo,
						status, remarks, approverId, level,isAdminApprovalClick);
				logger.info("res....." + res);
				if (res.equals("approved"))
					result = "Leave application has been approved.";
				else if (res.equals("rejected"))
					result = "Leave application has been rejected.";
				else if (res.equals("forwarded"))
					result = "Leave application has been forworded for next approval.";
				else if (res.equals("sendback"))
					result = "Leave application has been sent back to applicant.";

				else
					result = "Error Occured.";
			} else {
				result = "Leave Application has already been processed.";
			}
		}

		return result;

	}

	/**
	 * This method is used for Online Approve/Reject approved cancellation
	 * application
	 * 
	 * @param request
	 * @param empCode
	 * @param leaveAppNo
	 * @param status
	 * @param remarks
	 * @param approverId
	 * @param level
	 * @return
	 */
	public String onlineApproveRejectForCancellation(
			HttpServletRequest request, String empCode, String leaveAppNo,
			String status, String remarks, String approverId, String level,String isAdminApprovalClick) {
		String result = "";
		String res = "";
		final String query = " SELECT APPROVED_BY,LEAVE_STATUS,LEAVE_LEVEL FROM HRMS_LEAVE_HDR WHERE EMP_ID="
				+ empCode + " AND LEAVE_APPL_CODE=" + leaveAppNo;

		final Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverId)
					&& String.valueOf(approverIdObj[0][1]).equals("C")) {

				level = String.valueOf(approverIdObj[0][2]);

				res = approveRejCancellationLeaveApp(request, empCode,
						leaveAppNo, status, remarks, approverId, level, isAdminApprovalClick);

				logger.info("res....." + res);
				if (res.equals("approved"))
					result = "Leave cancellation application has been approved.";
				else if (res.equals("rejected"))
					result = "Leave cancellation application has been rejected.";
				else if (res.equals("forwarded"))
					result = "Leave cancellation application has been forworded for next approval.";
				else
					result = "Error Occured.";
			} else {
				result = "Leave cancellation Application has already been processed.";
			}
		}

		return result;

	}

	/**@modified by @author Prajakta.Bhandare
	 * @date 12 March 2013
	 * This method is used for approve/reject/send back leave application.
	 * 
	 * @param request
	 * @param empCode
	 * @param leaveAppNo
	 * @param status
	 * @param remarks
	 * @param approverId
	 * @param level
	 * @return
	 */
	public String approveRejectSendBackLevApp(HttpServletRequest request,
			String empCode, String leaveAppNo, String status, String remarks,
			String approverId, String level,String isAdminApprovalClick) {
		String applStatus = "";
	try {
			final String policyCode = getLeavePolicyCode(empCode);
			Object[][] empFlow = null;
			if(!(isAdminApprovalClick.equals("true")))
			{
			empFlow = generateEmpFlow(empCode, "Leave",
					Integer.parseInt(level) + 1);
			}
			boolean result = false;
			applStatus = "pending";

			final Object[][] changeStatus = new Object[1][4];
			changeStatus[0][0] = leaveAppNo; // application code
			changeStatus[0][1] = approverId; // user employee id
			changeStatus[0][2] = status; // status
			changeStatus[0][3] = remarks; // remark
			if (String.valueOf(status).equals("A")) {
				applStatus = changeApplStatus(empFlow, String
						.valueOf(leaveAppNo), empCode, status, isAdminApprovalClick,approverId);
			}//
			if (String.valueOf(status).equals("B")) {
				final Object[][] sendBackObj = new Object[1][2];
				sendBackObj[0][0] = status; // status
				sendBackObj[0][1] = leaveAppNo;// application code

				final String leaveHdrQuery = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS='B' ,APPROVED_BY="
						+ approverId
						+ ",LEAVE_LEVEL=1 WHERE LEAVE_APPL_CODE="
						+ leaveAppNo + " ";

				getSqlModel().singleExecute(leaveHdrQuery);// hrms_leave_hdr
				// leave status
				// update
				getSqlModel().singleExecute(getQuery(6), sendBackObj);// hrms_leave_dtl
				// leave status
				// update
				getSqlModel().singleExecute(getQuery(8), sendBackObj);// hrms_leave_dtlhistory
				// leave status
				// update

				applStatus = "sendback";

			}//
			if (String.valueOf(status).equals("R")) {

				LeaveApplicationModel levModel = new LeaveApplicationModel();
				levModel.initiate(context, session);
				final Object[][] updateData = new Object[1][2];
				updateData[0][0] = "R";
				final String query = "SELECT NVL(LEAVE_CLOSING_BALANCE,0),TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),TO_CHAR(LEAVE_DAYS_HRS,'HH24:MI'),HRMS_LEAVE_DTL.LEAVE_CODE,HRMS_LEAVE_DTL.EMP_ID FROM HRMS_LEAVE_DTL "
						+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_LEAVE_DTL.EMP_ID "
						+ "	AND HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_DTL.LEAVE_CODE) "
						+ " INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
						+ "	WHERE  LEAVE_HRS_FLAG='Y' AND LEAVE_APPLICATION_CODE= "
						+ leaveAppNo;
				final Object[][] data = getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {
					updateData[0][1] = leaveAppNo;
					levModel.updateBalanceHrs(empCode, String
							.valueOf(data[0][0]), String.valueOf(data[0][1]),
							String.valueOf(data[0][2]), String
									.valueOf(data[0][3]), "add");
					getSqlModel().singleExecute(getQuery(25), updateData);
					getSqlModel().singleExecute(getQuery(26), updateData);
					getSqlModel().singleExecute(getQuery(27), updateData);
					applStatus = "rejected";
				}

				else {

					final Object[][] reject = new Object[1][2];
					reject[0][0] = String.valueOf(status); // status
					reject[0][1] = String.valueOf(leaveAppNo);// application
					// code

					final String leaveDtlRecord = " SELECT  NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') FROM HRMS_LEAVE_DTL "
							+ " WHERE LEAVE_APPLICATION_CODE="
							+ leaveAppNo
							+ " ";

					final Object[][] leaveDtlRecordObj = getSqlModel()
							.getSingleResult(leaveDtlRecord);

					String balQuery = " SELECT  NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0), NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
							+ " WHERE LEAVE_APPLICATION_CODE='"
							+ changeStatus[0][0] + "' ";
					final Object[][] balObj = getSqlModel().getSingleResult(
							balQuery);
					//**
					  //CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO
					 // BALANCE APPLICABLE LEAVE DAYS SET TO BE ZERO IE CLOSING
					 // BALANCE =0
					 //*
					for (int j = 0; j < balObj.length; j++) {
						final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
								+ " WHERE LEAVE_POLICY_CODE="
								+ policyCode
								+ " AND LEAVE_CODE="
								+ String.valueOf(balObj[j][3]);
						final Object[][] zeroBalance = getSqlModel()
								.getSingleResult(zeroBlncQuery);
						if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
							balObj[j][0] = "0"; // leave days set to be zero
						}// end of if
					}// end of for
					if (balObj.length > 0) {
						balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? ,LEAVE_ONHOLD=CASE WHEN LEAVE_ONHOLD<=0 THEN 0 ELSE NVL(LEAVE_ONHOLD,0) - ? END "
								+ " WHERE EMP_ID =? AND LEAVE_CODE =?";
						result = getSqlModel().singleExecute(balQuery, balObj);

					} // end of if
					String updateQuery="UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS='"+reject[0][0]+"'";
					if((isAdminApprovalClick.equals("true"))){
						updateQuery+=",LEAVE_APPROVEDBY_ADMIN ="+approverId;
					}
					updateQuery+=" WHERE LEAVE_APPL_CODE="+reject[0][1];
					getSqlModel().singleExecute(updateQuery);// hrms_leave_hdr
					// leave status
					// update
					getSqlModel().singleExecute(getQuery(6), reject);// hrms_leave_dtl
					// leave status
					// update
					getSqlModel().singleExecute(getQuery(8), reject);// hrms_leave_dtlhistory
					// leave status
					// update

					final String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";

					final Object compOffObj[][] = getSqlModel()
							.getSingleResult(compOffAppQuery);

					if (compOffObj != null && compOffObj.length > 0) {
						for (int i = 0; i < leaveDtlRecordObj.length; i++) {
							Double counter = 0.0;
							int value = 0;
							Object[][] compOffDateObj = null;
							if (String.valueOf(compOffObj[0][0]).equals(
									String.valueOf(leaveDtlRecordObj[i][2]))) {

								String compOffDatesQuery = " SELECT  TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_DTL.EMP_ID , "
										+ " HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_STATUS   FROM HRMS_EXTRAWORK_APPL_DTL  "
										+ " WHERE HRMS_EXTRAWORK_APPL_DTL.EMP_ID="
										+ empCode
										+ " AND EXTRAWORK_APPL_STATUS='A'  AND EXTRAWORK_IS_LEAVE_APPLIED='Y' "
										+ " AND EXTRAWORK_LEAVE_APPL_CODE="
										+ changeStatus[0][0] + " ";

								if (!String.valueOf(compOffObj[0][1]).equals(
										"0")) {
									compOffDatesQuery += " AND TO_DATE('"
											+ String
													.valueOf(leaveDtlRecordObj[i][3])
											+ "','DD-MM-YYYY') < EXTRAWORK_DATE+"
											+ String.valueOf(compOffObj[0][1])
											+ " ";
								}
								compOffDatesQuery += " AND EXTRAWORK_IS_COMPOFF='Y' ORDER BY TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY')";
								logger
										.info("compOffDatesQuery in leave approval==========="
												+ compOffDatesQuery);
								compOffDateObj = getSqlModel().getSingleResult(
										compOffDatesQuery);
								counter += Double.parseDouble(String
										.valueOf(leaveDtlRecordObj[i][0]));
								value = counter.intValue();

								if (compOffDateObj != null
										&& compOffDateObj.length > 0) {
									if (compOffDateObj.length >= value) {
										String updateCompOffRecord = " UPDATE HRMS_EXTRAWORK_APPL_DTL  SET EXTRAWORK_IS_LEAVE_APPLIED='N',EXTRAWORK_LEAVE_APPL_CODE='',EXTRAWORK_LEAVE_FROMDATE='',EXTRAWORK_LEAVE_TODATE='' "
												+ " WHERE EMP_ID = "
												+ empCode
												+ " AND EXTRAWORK_LEAVE_APPL_CODE="
												+ changeStatus[0][0];
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
										logger
												.info("updateCompOffRecord in leave approval=============="
														+ updateCompOffRecord);
										getSqlModel().singleExecute(
												updateCompOffRecord);
									}
								}
							}
						}
					}
					applStatus = "rejected";

				}
				if (applStatus.equals("rejected")) {
					result = callRejectAttendenceData(leaveAppNo);
				}

			}// end of if
			getSqlModel().singleExecute(getQuery(2), changeStatus);// insert
			// record
			// into
			// hrms_leave_path

			createTemplateWithAlerts(request, status, leaveAppNo, remarks,
					empCode, approverId, level, empFlow, applStatus,isAdminApprovalClick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return applStatus;

	}

	/**
	 * This method is used for getting list of all leave applications.
	 * 
	 * @param levApp
	 * @param request
	 * @param empId
	 */
	public void getAllTypeOfApplications(LeaveApproval levApp,
			HttpServletRequest request, String empId) {

		try {
			Object pendingParam[] = null;
			Object penAppCanParam[] = null;
			pendingParam = new Object[] { "P", empId, empId };
			penAppCanParam = new Object[] { "C", empId, empId };

			final String sqlQuery = getSqlQuery(levApp);

			final Object pendingData[][] = getSqlModel().getSingleResult(
					sqlQuery, pendingParam);

			final Object penAppCanData[][] = getSqlModel().getSingleResult(
					sqlQuery, penAppCanParam);
			if (pendingData != null && pendingData.length > 0) {

				levApp.setPendingLength("true");
				final String[] pageIndex = Utility.doPaging(levApp.getMyPage(),
						pendingData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					levApp.setMyPage("1");

				ArrayList pendingList = new ArrayList();

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					LeaveApproval bean1 = new LeaveApproval();
					bean1.setLeaveAppNo((String.valueOf(pendingData[i][0])));// application
					// code
					bean1.setEmpCode(String.valueOf(pendingData[i][1]));// employee
					// id
					bean1.setEmpName(String.valueOf(pendingData[i][2]));// employee
					// name
					bean1.setDate(String.valueOf(pendingData[i][3]));// application
					// date
					bean1.setPendingStatus(String.valueOf(pendingData[i][4]));// status
					bean1.setLevel(String.valueOf(pendingData[i][5]));// level
					bean1.setTokenNo(String.valueOf(pendingData[i][6]));// employee
					// token
					pendingList.add(bean1);
				}
				levApp.setPendingList(pendingList);

			}
			// PAGING FOR APPROVED PENDING LIST
			if (penAppCanData != null && penAppCanData.length > 0) {

				levApp.setApprovedPendingLength("true");
				final String[] pageIndex = Utility.doPaging(
						levApp.getMyPage1(), penAppCanData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage1", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo1", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					levApp.setMyPage1("1");

				ArrayList approvedCancelledList = new ArrayList();

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					LeaveApproval bean1 = new LeaveApproval();
					bean1.setPenAppCanLevAppNo((String
							.valueOf(penAppCanData[i][0])));// application
					// code

					bean1
							.setPenAppCanEmpId(String
									.valueOf(penAppCanData[i][1]));// employee
					// id
					bean1.setPenAppCanEmpName(String
							.valueOf(penAppCanData[i][2]));// employee
					// name
					bean1.setPenAppCanAppDate(String
							.valueOf(penAppCanData[i][3]));// application
					// date
					bean1.setPenAppCanStatus(String
							.valueOf(penAppCanData[i][4]));// status
					bean1
							.setPenAppCanLevel(String
									.valueOf(penAppCanData[i][5]));// level
					bean1.setPenAppCanEmpToken(String
							.valueOf(penAppCanData[i][6]));// employee
					// token

					approvedCancelledList.add(bean1);
				}
				levApp.setPenAppCanList(approvedCancelledList);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method is used for getting approved leave application list.
	 * 
	 * @param levApp
	 * @param request
	 * @param empId
	 */
	public void getApprovedList(LeaveApproval levApp,
			HttpServletRequest request, String empId) {

		try {
			Object approvedParam[] = null;
			approvedParam = new Object[] { "A", empId, empId };

			final String sqlQuery = getSqlQuery(levApp);

			final Object approvedData[][] = getSqlModel().getSingleResult(
					sqlQuery, approvedParam);
			final String[] pageIndexApproved = Utility.doPaging(levApp
					.getMyPageApproved(), approvedData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}
			request.setAttribute("totalPageApproved", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("PageNoApproved", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				levApp.setMyPageApproved("1");
			if (approvedData != null && approvedData.length > 0) {

				levApp.setApprovedLength("true");
				ArrayList approvedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					LeaveApproval bean1 = new LeaveApproval();
					bean1
							.setAppLeaveAppNo((String
									.valueOf(approvedData[i][0])));// application
					// code
					bean1.setAppEmpId(String.valueOf(approvedData[i][1]));// employee
					// id
					bean1.setAppEmpName(String.valueOf(approvedData[i][2]));// employee
					// name
					bean1.setApplevAppDate(String.valueOf(approvedData[i][3]));// application
					// date
					bean1.setAppStatus(String.valueOf(approvedData[i][4]));// status
					bean1.setAppLevel(String.valueOf(approvedData[i][5]));// level
					bean1.setAppEmpToken(String.valueOf(approvedData[i][6]));// employee
					// token

					approvedList.add(bean1);

				}
				levApp.setAppList(approvedList);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method is used for getting list of rejected leave applications.
	 * 
	 * @param levApp
	 * @param request
	 * @param empId
	 */
	public void getRejectedList(LeaveApproval levApp,
			HttpServletRequest request, String empId) {
		try {
			// TODO Auto-generated method stub
			Object rejectedParam[] = null;
			rejectedParam = new Object[] { "R", empId, empId };

			final String sqlQuery = getSqlQuery(levApp);

			final Object rejectedData[][] = getSqlModel().getSingleResult(
					sqlQuery, rejectedParam);
			final String[] pageIndexRejected = Utility.doPaging(levApp
					.getMyPageApproved(), rejectedData.length, 20);
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
				levApp.setMyPageRejected("1");
			if (rejectedData != null && rejectedData.length > 0) {

				levApp.setRejectedLength("true");
				ArrayList rejectedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {

					LeaveApproval leaveRejectedBean = new LeaveApproval();
					leaveRejectedBean.setRejLeaveAppNo((String
							.valueOf(rejectedData[i][0])));// application
					// code
					leaveRejectedBean.setRejEmpId(String
							.valueOf(rejectedData[i][1]));// employee id
					leaveRejectedBean.setRejEmpName(String
							.valueOf(rejectedData[i][2]));// employee name
					leaveRejectedBean.setRejAppDate(String
							.valueOf(rejectedData[i][3]));// application date
					leaveRejectedBean.setRejStatus(String
							.valueOf(rejectedData[i][4]));// status
					leaveRejectedBean.setRejLevel(String
							.valueOf(rejectedData[i][5]));// level
					leaveRejectedBean.setRejEmpToken(String
							.valueOf(rejectedData[i][6]));// employee
					// token

					rejectedList.add(leaveRejectedBean);

				}
				levApp.setRejList(rejectedList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**@modified by @author Prajakta.Bhandare
	 * @date 12 March 2013
	 * This method is used for approve/reject/send back approved cancellation
	 * leave application.
	 * 
	 * @param request
	 * @param empCode
	 * @param leaveAppNo
	 * @param status
	 * @param remarks
	 * @param approverId
	 * @param level
	 * @return
	 */
	public String approveRejCancellationLeaveApp(HttpServletRequest request,
			String empCode, String leaveAppNo, String status, String remarks,
			String approverId, String level, String isAdminApprovalClick) {
		// TODO Auto-generated method stub

		String applStatus = "";
		try {
			final String policyCode = getLeavePolicyCode(empCode);
			boolean result = false;
			applStatus = "pending";
			Object[][] empFlow = null;
			final Object[][] changeStatus = new Object[1][4];
			changeStatus[0][0] = leaveAppNo; // application code
			changeStatus[0][1] = approverId; // user employee id
			changeStatus[0][2] = status; // status
			changeStatus[0][3] = remarks; // remark
			if (String.valueOf(status).equals("X")) { // Approve Cancellation
				/*
				 * String balQuery = " SELECT LEAVE_DAYS,EMP_ID,LEAVE_CODE FROM
				 * HRMS_LEAVE_DTL " + " WHERE LEAVE_APPLICATION_CODE='" +
				 * changeStatus[0][0] + "' "; Object[][] balObj =
				 * getSqlModel().getSingleResult(balQuery);
				 * 
				 * //METHODS FROM ACTION
				 */empFlow = generateEmpFlow(empCode, "Leave", Integer
						.parseInt(level) + 1);

				applStatus = changeApplStatusForApprovedCancelAPPlication(
						empFlow, String.valueOf(leaveAppNo), empCode, status);

			}//
			if (String.valueOf(status).equals("Z")) {// Reject Cancellation
				final String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='Z' WHERE LEAVE_APPL_CODE ="
						+ leaveAppNo + " ";
				getSqlModel().singleExecute(updateHdr);
				final String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'Z'  "
						+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo + " ";
				getSqlModel().singleExecute(updateDtl);
				final String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'Z'  "
						+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo + " ";
				getSqlModel().singleExecute(updateDtlHistory);

				applStatus = "rejected";
			}
			getSqlModel().singleExecute(getQuery(2), changeStatus);// insert
			// record
			// into
			// hrms_leave_path

			createTemplateWithAlertsForCancellation(request, status,
					leaveAppNo, remarks, empCode, approverId, level, empFlow,
					applStatus,isAdminApprovalClick);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;
	}

	/**
	 * This method is used for changing application status of approved cancel
	 * application.
	 * 
	 * @param empFlow
	 * @param leaveAppNo
	 * @param empId
	 * @param status
	 * @return
	 */
	public String changeApplStatusForApprovedCancelAPPlication(
			Object[][] empFlow, String leaveAppNo, String empId, String status) {
		String applStatus = "pending";
		boolean result = false;
		if (empFlow != null && empFlow.length != 0) {

			final Object[][] updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][2]; // level
			updateApprover[0][1] = empFlow[0][0]; // approver id
			updateApprover[0][2] = empFlow[0][3]; // alternate approver id
			updateApprover[0][3] = leaveAppNo; // application code
			result = getSqlModel().singleExecute(getQuery(4), updateApprover);
			final String updateDtlApproverQuery = "UPDATE HRMS_LEAVE_DTL  SET LEAVE_APPROVED_BY="
					+ updateApprover[0][1]
					+ "  WHERE LEAVE_APPLICATION_CODE="
					+ updateApprover[0][3];
			result = getSqlModel().singleExecute(updateDtlApproverQuery);
			applStatus = "forwarded";
		} // end of if
		else {
			LeaveApplicationModel levModel = new LeaveApplicationModel();
			levModel.initiate(context, session);
			final Object[][] updateData = new Object[1][2];
			updateData[0][0] = "X";
			final String query = "SELECT NVL(LEAVE_CLOSING_BALANCE,0),TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),TO_CHAR(LEAVE_DAYS_HRS,'HH24:MI'),HRMS_LEAVE_DTL.LEAVE_CODE,HRMS_LEAVE_DTL.EMP_ID FROM HRMS_LEAVE_DTL "
					+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_LEAVE_DTL.EMP_ID "
					+ "	AND HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_DTL.LEAVE_CODE) "
					+ " INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
					+ "	WHERE  LEAVE_HRS_FLAG='Y' AND LEAVE_APPLICATION_CODE= "
					+ leaveAppNo;
			final Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				updateData[0][1] = leaveAppNo;
				levModel.updateBalanceHrs(String.valueOf(data[0][4]), String
						.valueOf(data[0][0]), String.valueOf(data[0][1]),
						String.valueOf(data[0][2]), String.valueOf(data[0][3]),
						"add");
				getSqlModel().singleExecute(getQuery(25), updateData);
				getSqlModel().singleExecute(getQuery(26), updateData);
				getSqlModel().singleExecute(getQuery(27), updateData);
				applStatus = "approved";

			} else {
				final Object[][] statusChanged = new Object[1][2];
				statusChanged[0][0] = "X"; // status
				statusChanged[0][1] = leaveAppNo; // application code
				result = getSqlModel()
						.singleExecute(getQuery(5), statusChanged); // hrms_leave_hdr
				// leave
				// status
				// update
				result = getSqlModel()
						.singleExecute(getQuery(6), statusChanged); // hrms_leave_dtl
				// leave
				// status
				// update
				result = getSqlModel()
						.singleExecute(getQuery(8), statusChanged); // hrms_leave_dtlhistory
				// leave
				// status
				// update
				if (result) {
					setLeaveBalAndOnholdForApprovedCancelApplication(
							leaveAppNo, empId);
					updateCompOffDetails(leaveAppNo, empId);
				}// end of if

			}
			applStatus = "approved";
			// added ny janisha
			if (applStatus.equals("approved")) {
				try {
					inseertApprovedCaneclAendenceData(leaveAppNo);
					callWebService(leaveAppNo, "D");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			// end added by janisha

		}
		// collect(leaveApp ,status, request);
		return applStatus;

	}// end of changeApplStatus

	/**
	 * This method is used for inserting data into attendance table.
	 * 
	 * @param leaveAppNo
	 */
	public void inseertApprovedCaneclAendenceData(String leaveAppNo) {
		try {
			final String sqlQuery = "SELECT EMP_ID,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),LEAVE_YEAR FROM HRMS_LEAVE_DTLHISTORY "
					+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo;
			final Object[][] insertObj = getSqlModel()
					.getSingleResult(sqlQuery);
			if (insertObj != null && insertObj.length > 0) {
				for (int j = 0; j < insertObj.length; j++) {
				  String actualDataUpdateInAtt = " UPDATE "
						  					+ " HRMS_DAILY_ATTENDANCE_" + insertObj[j][2] 
						  					+ " SET ATT_REG_STATUS_TWO='AB' WHERE ATT_DATE=TO_DATE('" 
						  					+ insertObj[j][1] + "','DD-MM-YYYY') AND ATT_EMP_ID=" 
						  					+ insertObj[j][0];					 
					// for removing record from table when application is
					// canceled
					/*final String actualDataUpdateInAtt = "DELETE FROM HRMS_DAILY_ATTENDANCE_"
							+ insertObj[j][2]
							+ " WHERE ATT_DATE=TO_DATE('"
							+ insertObj[j][1]
							+ "','DD-MM-YYYY')"
							+ " AND ATT_EMP_ID="
							+ insertObj[j][0]
							+ " AND TO_DATE(ATT_DATE,'DD-MM-YYYY')>=TO_DATE(SYSDATE,'DD-MM-YYYY')  ";*/
					getSqlModel().singleExecute(actualDataUpdateInAtt);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used for updating comp off application details.
	 * 
	 * @param leaveAppNo
	 * @param empCode
	 */
	private void updateCompOffDetails(String leaveAppNo, String empCode) {
		try {
			final String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";

			final Object compOffObj[][] = getSqlModel().getSingleResult(
					compOffAppQuery);

			final String leaveDtlRecord = " SELECT  NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo + " ";

			final Object[][] leaveDtlRecordObj = getSqlModel().getSingleResult(
					leaveDtlRecord);

			if (compOffObj != null && compOffObj.length > 0) {
				for (int i = 0; i < leaveDtlRecordObj.length; i++) {
					Double counter = 0.0;
					int value = 0;
					Object[][] compOffDateObj = null;
					if (String.valueOf(compOffObj[0][0]).equals(
							String.valueOf(leaveDtlRecordObj[i][2]))) {

						String compOffDatesQuery = " SELECT  TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_DTL.EMP_ID , "
								+ " HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_APPL_STATUS   FROM HRMS_EXTRAWORK_APPL_DTL  "
								+ " WHERE HRMS_EXTRAWORK_APPL_DTL.EMP_ID="
								+ empCode
								+ " AND EXTRAWORK_APPL_STATUS='A'  AND EXTRAWORK_IS_LEAVE_APPLIED='Y' "
								+ " AND EXTRAWORK_LEAVE_APPL_CODE="
								+ leaveAppNo + " ";

						if (!String.valueOf(compOffObj[0][1]).equals("0")) {
							compOffDatesQuery += " AND TO_DATE('"
									+ String.valueOf(leaveDtlRecordObj[i][3])
									+ "','DD-MM-YYYY') < EXTRAWORK_DATE+"
									+ String.valueOf(compOffObj[0][1]) + " ";
						}
						compOffDatesQuery += " AND EXTRAWORK_IS_COMPOFF='Y' ORDER BY TO_CHAR(EXTRAWORK_DATE, 'DD-MM-YYYY')";
						logger
								.info("compOffDatesQuery in leave approval==========="
										+ compOffDatesQuery);
						compOffDateObj = getSqlModel().getSingleResult(
								compOffDatesQuery);
						counter += Double.parseDouble(String
								.valueOf(leaveDtlRecordObj[i][0]));
						value = counter.intValue();

						if (compOffDateObj != null && compOffDateObj.length > 0) {
							if (compOffDateObj.length >= value) {
								String updateCompOffRecord = " UPDATE HRMS_EXTRAWORK_APPL_DTL  SET EXTRAWORK_IS_LEAVE_APPLIED='N',EXTRAWORK_LEAVE_APPL_CODE='',EXTRAWORK_LEAVE_FROMDATE='',EXTRAWORK_LEAVE_TODATE='' "
										+ " WHERE EMP_ID = "
										+ empCode
										+ " AND EXTRAWORK_LEAVE_APPL_CODE="
										+ leaveAppNo;
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
								logger
										.info("updateCompOffRecord in leave approval=============="
												+ updateCompOffRecord);
								getSqlModel()
										.singleExecute(updateCompOffRecord);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method is used for setting leave balance and onhold balance for
	 * approved cancellation application.
	 * 
	 * @param leaveAppNo
	 * @param empId
	 * @return
	 */
	public boolean setLeaveBalAndOnholdForApprovedCancelApplication(
			String leaveAppNo, String empId) {
		final String policyCode = getLeavePolicyCode(empId);

		boolean result = false;
		String balQuery = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
				+ " WHERE LEAVE_APPLICATION_CODE='" + leaveAppNo + "' ";
		final Object[][] balObj = getSqlModel().getSingleResult(balQuery);
		/**
		 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE APPLICABLE
		 * LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
		 */
		for (int j = 0; j < balObj.length; j++) {
			final String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE LEAVE_POLICY_CODE="
					+ policyCode
					+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][2]);
			final Object[][] zeroBalance = getSqlModel().getSingleResult(
					zeroBlncQuery);
			if (String.valueOf(zeroBalance[0][0]).equals("Y")) {
				balObj[j][0] = "0"; // LEAVE DAYS SET TO BE ZERO
			}// end of if
		}// end of for
		if (balObj.length > 0) {
			// update leave balance and onhold

			balQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE = LEAVE_CLOSING_BALANCE + ? "
					+ " WHERE EMP_ID =? AND LEAVE_CODE =?";

			result = getSqlModel().singleExecute(balQuery, balObj);

		}// end of if
		return result;
	}// end of setLeaveBalAndOnhold

	/**@modified by @author Prajakta.Bhandare
	 * @date 12 March 2013
	 * This method is used for sending mail for approved cancellation
	 * application
	 * 
	 * @param request
	 * @param status
	 * @param leaveAppNo
	 * @param remarks
	 * @param empCode
	 * @param approverId
	 * @param level
	 * @param empFlow
	 * @param applStatus
	 */
	public void createTemplateWithAlertsForCancellation(
			HttpServletRequest request, String status, String leaveAppNo,
			String remarks, String empCode, String approverId, String level,
			Object[][] empFlow, String applStatus, String isAdminApprovalClick) {
		try {
			if (!String.valueOf(status).equals("P")) {
				// for mypage coding
				String keepInfo = "";
				String actualstatus = "";
				String alternateApprover = "";

				final String keepInformedId = " SELECT  LEAVE_KEEP_INFORMED FROM HRMS_LEAVE_HDR "
						+ " WHERE LEAVE_APPL_CODE=" + leaveAppNo;

				final Object[][] keepInformedObj = getSqlModel()
						.getSingleResult(keepInformedId);

				/**
				 * Remove process manager entry from mypage.
				 */
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);

				final String module = "Leave Cancellation";
				processAlerts.removeProcessAlert(String.valueOf(leaveAppNo),
						"Leave Cancellation");

				String applicant = "", oldApprover = "", newApprover = "";
				try {
					applicant = empCode;
					oldApprover = approverId;
					if (status.equals("B"))
						newApprover = "";
					else
						newApprover = String.valueOf(empFlow[0][0]);
				} catch (Exception e) {
					logger.error(e);
				}

				String empID = "", msgType = "";
				final String applnID = String.valueOf(leaveAppNo);
				final String alertLevel = String.valueOf(Integer
						.parseInt(level) + 1);

				if (Integer.parseInt(level) == 1) {
					empFlow = generateEmpFlow(empCode, "Leave", Integer
							.parseInt(level));
				} else {
					empFlow = generateEmpFlow(empCode, "Leave", Integer
							.parseInt(level));
				}

				if (!newApprover.equals("")) {
					// send alert from oldApprover to newApprover
					empID = newApprover;
					msgType = "A";
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template
							.setEmailTemplate("LEAVE CANCELLATION MAIL SENT TO SECOND APPROVER");
					template.getTemplateQueries();
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, newApprover);

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applicant);
					templateQuery5.setParameter(2, applnID);
					templateQuery5.setParameter(3, applicant);

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, newApprover);
					// Add approval link -pass parameters to the link
					final String[] link_param = new String[2];
					final String[] link_label = new String[2];
					final String applicationType = "LeaveCancelAppl";
					link_param[0] = applicationType + "#" + applicant + "#"
							+ applnID + "#" + "X" + "#" + "..." + "#"
							+ newApprover + "#" + alertLevel + "#" + isAdminApprovalClick;

					link_param[1] = applicationType + "#" + applicant + "#"
							+ applnID + "#" + "Z" + "#" + "..." + "#"
							+ newApprover + "#" + alertLevel + "#" + isAdminApprovalClick;

					link_label[0] = "Approve Cancellation";

					link_label[1] = "Reject Cancellation ";

					template.configMailAlert();

					try {
						final String link = "/leaves/LeaveApplication_retriveForApproval.action";
						final String linkParam = "leaveApplicationNo="
								+ applnID + "&applicationStatus=" + "C";

						alternateApprover = (empFlow != null && !String
								.valueOf(empFlow[0][3]).equals("0")) ? String
								.valueOf(empFlow[0][3]) : "";
						template.sendProcessManagerAlert(newApprover, module,
								"A", applnID, alertLevel, linkParam, link,
								"Applied For Cancellation", applicant, "", "",
								"", oldApprover);
					} catch (Exception e) {
						logger.error(e);
						e.printStackTrace();
					}

					template.addOnlineLink(request, link_param, link_label);
					template.sendApplicationMail();

					template.clearParameters();
					template.terminate();

					// send alert from oldApprover to applicant
					empID = applicant;
					msgType = "I";

					EmailTemplateBody template1 = new EmailTemplateBody();
					template1.initiate(context, session);

					template1
							.setEmailTemplate("Mail to employee regarding first leave cancellation approval");

					template1.getTemplateQueries();

					EmailTemplateQuery templateQuery7 = template1
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery7.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery8 = template1
							.getTemplateQuery(2); // TO EMAIL
					templateQuery8.setParameter(1, applicant);

					EmailTemplateQuery templateQuery9 = template1
							.getTemplateQuery(3);
					templateQuery9.setParameter(1, applnID);

					EmailTemplateQuery templateQuery10 = template1
							.getTemplateQuery(4);
					templateQuery10.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery11 = template1
							.getTemplateQuery(5);
					templateQuery11.setParameter(1, applicant);
					templateQuery11.setParameter(2, applnID);
					templateQuery11.setParameter(3, applicant);

					EmailTemplateQuery templateQuery12 = template1
							.getTemplateQuery(6);
					templateQuery12.setParameter(1, newApprover);

					// Added approver comments

					EmailTemplateQuery templateQuery13 = template1
							.getTemplateQuery(7);
					templateQuery13.setParameter(1, applnID);

					template1.configMailAlert();

					try {

						final String link = "/leaves/LeaveApplication_retriveDetails.action";
						final String linkParam = "levApplicationCode="
								+ applnID + "&levStatus=" + status;

						if (status.equals("X")) {
							actualstatus = "Cancellation Approved";
						}
						if (status.equals("Z")) {
							actualstatus = "Cancellation Rejected";
						}
						if (keepInformedObj != null
								&& keepInformedObj.length > 0) {
							keepInfo = String.valueOf(keepInformedObj[0][0]);
						}

						oldApprover = String.valueOf(empFlow[0][0]);

						template1.sendProcessManagerAlert(oldApprover, module,
								"I", applnID, alertLevel, linkParam, link,
								actualstatus, applicant, alternateApprover,
								keepInfo, applicant, oldApprover);

					} catch (Exception e) {
						logger.error(e);
					}

					if (keepInformedObj != null && keepInformedObj.length > 0) {
						keepInfo = String.valueOf(keepInformedObj[0][0]);
						template1.sendApplicationMailToKeepInfo(keepInfo);

					}

					try {
						template1.sendApplicationMail();
					} catch (Exception e) {
						e.printStackTrace();
					}
					logger.info("after send mail====");
					template1.clearParameters();
					template1.terminate();
				} else {
					// send alert from oldApprover to applicant
					empID = applicant;
					msgType = "I";
					keepInfo  = String.valueOf(keepInformedObj[0][0]);
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);

					template
							.setEmailTemplate("Mail to employee regarding leave cancellation approval");

					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, applicant);

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applicant);
					templateQuery5.setParameter(2, applnID);
					templateQuery5.setParameter(3, applicant);

					// Added for approver details

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, applnID);

					template.configMailAlert();
					try {

						final String link = "/leaves/LeaveApplication_retriveDetails.action";
						final String linkParam = "levApplicationCode="
								+ applnID + "&levStatus=" + status;

						empFlow = generateEmpFlow(empCode, "Leave", Integer
								.parseInt(level) - 1);
						alternateApprover = (empFlow != null && !String
								.valueOf(empFlow[0][3]).equals("0")) ? String
								.valueOf(empFlow[0][3]) : "";
						String keepInformedto = "";
						keepInformedto = (empFlow != null && !String.valueOf(
								empFlow[0][0]).equals("0")) ? String
								.valueOf(empFlow[0][0]) : "";
						if (!keepInformedto.equals("")) {
							keepInfo += "," + keepInformedto;
						}
						if (status.equals("X")) {
							actualstatus = "Cancellation Approved";
						}
						if (status.equals("Z")) {
							actualstatus = "Cancellation Rejected";
						}
						template.sendProcessManagerAlert(oldApprover, module,
								"I", applnID, alertLevel, linkParam, link,
								actualstatus, applicant, alternateApprover,
								keepInfo, applicant, oldApprover);
					} catch (Exception e) {
						logger.error(e);
					}
					if (keepInformedObj != null && keepInformedObj.length > 0) {
						keepInfo = String.valueOf(keepInformedObj[0][0]);
						template.sendApplicationMailToKeepInfo(keepInfo);
					}
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		// --------Email Templates + Process Manager Alert--------------end
	}
/**
 * This method is used for getting leave application details for selected employee.
 * @param bean
 * @return String
 */
	private String getSqlQuery(LeaveApproval bean) {
		  String sqlQuery = "";

		try {
			sqlQuery = "SELECT LEAVE_APPL_CODE,HRMS_LEAVE_HDR.EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),"
					+ "	 LEAVE_STATUS,LEAVE_LEVEL,HRMS_EMP_OFFC.EMP_TOKEN "
					+ "	 FROM HRMS_LEAVE_HDR "
					+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_HDR.EMP_ID)"
					+ "	 WHERE  LEAVE_STATUS=?  AND (APPROVED_BY=? OR ALTER_APPROVER=? ) ";
			if (!bean.getSearchEmpCode().equals("")) {
				sqlQuery += " AND HRMS_LEAVE_HDR.EMP_ID="
						+ bean.getSearchEmpCode().trim();
			}
			sqlQuery += " ORDER BY LEAVE_APPL_CODE DESC ";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sqlQuery;
	}
/**
 * This method is used for updating attendance table for reject status.
 * @param leaveAppNo
 * @return boolean
 */
	public boolean callRejectAttendenceData(String leaveAppNo) {
		boolean result = false;
		try {
			final String sqlQuery = "select EMP_ID,to_char(LEAVE_FROM_DATE,'DD-MM-YYYY'),LEAVE_YEAR from HRMS_LEAVE_DTLHISTORY "
					+ " where LEAVE_APPLICATION_CODE=" + leaveAppNo;
			final Object[][] insertObj = getSqlModel()
					.getSingleResult(sqlQuery);
			if (insertObj != null && insertObj.length > 0) {
				for (int j = 0; j < insertObj.length; j++) {
					final String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
							+ insertObj[j][2]
							+ " where ATT_DATE=to_date('"
							+ insertObj[j][1]
							+ "','dd-mm-yyyy') and ATT_EMP_ID='"
							+ insertObj[j][0] + " ' ";
					final Object[][] selectObj = getSqlModel().getSingleResult(
							selectQry);
					if (selectObj != null && selectObj.length > 0) {
						final String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
								+ insertObj[j][2]
								+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,ATT_REG_STATUS_TWO='AB',IS_APPL_PROCESS='N'"
								+ " WHERE ATT_DATE=to_date ( '"
								+ insertObj[j][1]
								+ "','dd-mm-yyyy')"
								+ " and ATT_EMP_ID=" + insertObj[j][0];

						result = getSqlModel().singleExecute(
								myCardTimeActualDataUpdate);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Web Service Calling for Leave Approval Modified By Lakkichand
	 */
	public String callWebService(String leaveAppNo, String insertORDelete) {
		String returnStr = "";
		final String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, LEAVE_NAME, LEAVE_DAYS, TO_CHAR(HRMS_LEAVE_DTLHISTORY.LEAVE_FROM_DATE,'YYYY-MM-DD'), "
				+ "TO_CHAR(HRMS_LEAVE_DTLHISTORY.LEAVE_TO_DATE,'YYYY-MM-DD'),TO_CHAR(LEAVE_APPL_DATE,'YYYY-MM-DD'),TO_CHAR(SYSDATE,'YYYY-MM-DD')"
				+ " FROM HRMS_LEAVE_DTLHISTORY"
				+ " INNER JOIN HRMS_LEAVE_HDR ON (HRMS_LEAVE_DTLHISTORY.LEAVE_APPLICATION_CODE=HRMS_LEAVE_HDR.LEAVE_APPL_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_DTLHISTORY.EMP_ID)"
				+ " INNER JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_DTLHISTORY.LEAVE_CODE)"
				+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo;
		final Object[][] dataObj = getSqlModel().getSingleResult(query);
		if (dataObj != null && dataObj.length > 0) {
			for (int i = 0; i < dataObj.length; i++) {
				try {
					// String getXML =
					// "<CreateLeave><Records><Flag>I</Flag><EmployeeCode>CS61</EmployeeCode><EmployeeName>Admina</EmployeeName><LeaveOrWFH>H</LeaveOrWFH><LeaveType>Test
					// new</LeaveType><FromDate>2012-05-15</FromDate><ToDate>2012-05-15</ToDate><HalfDay>0</HalfDay><Status>Approved</Status><AppliedDate>2012-12-30</AppliedDate><ApprovedDate>2011-12-19</ApprovedDate><NumberOfDays>1</NumberOfDays></Records></CreateLeave>";
					final String xmlData = "<CreateLeave><Records>" + "<Flag>"
							+ insertORDelete + "</Flag>" + "<EmployeeCode>"
							+ String.valueOf(dataObj[i][0]) + "</EmployeeCode>"
							+ "<EmployeeName>" + String.valueOf(dataObj[i][1])
							+ "</EmployeeName>" + "<LeaveOrWFH>L</LeaveOrWFH>"
							+ "<LeaveType>" + String.valueOf(dataObj[i][2])
							+ "</LeaveType>" + "<FromDate>"
							+ String.valueOf(dataObj[i][4]) + "</FromDate>"
							+ "<ToDate>" + String.valueOf(dataObj[i][5])
							+ "</ToDate>" + "<HalfDay>0</HalfDay>"
							+ "<Status>Approved</Status>" + "<AppliedDate>"
							+ String.valueOf(dataObj[i][6]) + "</AppliedDate>"
							+ "<ApprovedDate>" + String.valueOf(dataObj[i][7])
							+ "</ApprovedDate>" + "<NumberOfDays>"
							+ String.valueOf(dataObj[i][3]) + "</NumberOfDays>"
							+ "</Records></CreateLeave>";
					WebServiceModel serviceModel = new WebServiceModel();
					serviceModel.initiate(context, session);
					serviceModel.sendWebSeviceData(xmlData, "Leave");
					serviceModel.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return returnStr;
	}
}// end of class
