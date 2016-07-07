package org.paradyne.model.leave;

import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.paradyne.bean.leave.LeaveAdminApproval;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.bean.leave.LeaveApproval;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author VISHWAMBHAR DESHPANDE
 * 
 */
public class LeaveAdminApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LeaveAdminApprovalModel.class);

	/**
	 * THIS METHOD IS USED FOR DISPLAYING PENDING LEAVE APPLICATION FOR APPROVAL
	 * OR REJECTION
	 * 
	 * @param levApp
	 * @param request
	 * @param status--------leave
	 *            application status
	 * 
	 * 
	 * 
	 */
	public void collect(LeaveAdminApproval levApp, String status,
			HttpServletRequest request) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			Object[] stat = new Object[3];
			stat[0] = status;
			stat[1] = levApp.getUserEmpId();// user employee id
			stat[2] = levApp.getUserEmpId();// user employee id
			Object[][] result = getSqlModel()
					.getSingleResult(getQuery(1), stat);
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

			System.out.println("val of riwC" + rowCount1);
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
			System.out.println("val of PageNo1" + PageNo1);
			request.setAttribute("xyz", PageNo1);

			for (int i = From_TOT; i < To_TOT; i++) {
				LeaveAdminApproval bean1 = new LeaveAdminApproval();
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
				Object[][] data = getSqlModel().getSingleResult(pathQuery,
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
		Object result[][] = reporting.generateEmpFlow(empCode, type, order);
		reporting.terminate();
		return result;
	}

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

	/*public String changeApplStatus(Object[][] empFlow, String leaveAppNo,
			String empId, String status, String adminApproverId) {
		String applStatus = "pending";
		boolean result = false;

		try {
			LeaveApplicationModel levModel = new LeaveApplicationModel();
			levModel.initiate(context, session);
			Object[][] updateData = new Object[1][2];
			Object[][] updateHDRData = new Object[1][3];
			updateData[0][0] = "A";
			updateHDRData[0][0] = "A";
			String query = "SELECT NVL(LEAVE_CLOSING_BALANCE,0),TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),TO_CHAR(LEAVE_DAYS_HRS,'HH24:MI'),HRMS_LEAVE_DTL.LEAVE_CODE,HRMS_LEAVE_DTL.EMP_ID FROM HRMS_LEAVE_DTL "
					+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_LEAVE_DTL.EMP_ID "
					+ "	AND HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_DTL.LEAVE_CODE) "
					+ " INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
					+ "	WHERE  LEAVE_HRS_FLAG='Y' AND LEAVE_APPLICATION_CODE= "
					+ leaveAppNo;
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				updateData[0][1] = leaveAppNo;
				updateHDRData[0][1] = adminApproverId;
				updateHDRData[0][2] = leaveAppNo;
				levModel.updateBalanceHrs(String.valueOf(data[0][4]), String
						.valueOf(data[0][0]), String.valueOf(data[0][1]),
						String.valueOf(data[0][2]), String.valueOf(data[0][3]),
						"");
				getSqlModel().singleExecute(getQuery(25), updateHDRData);
				getSqlModel().singleExecute(getQuery(26), updateData);
				getSqlModel().singleExecute(getQuery(27), updateData);
				applStatus = "approved";

			} else {
				Object[][] statusChanged = new Object[1][2];
				Object[][] statusheaderChanged = new Object[1][3];
				statusChanged[0][0] = "A"; // status
				statusheaderChanged[0][0] = "A"; // status
				statusChanged[0][1] = leaveAppNo; // application code
				statusheaderChanged[0][1] = adminApproverId; // application
				// code
				statusheaderChanged[0][2] = leaveAppNo; // application code
				result = getSqlModel().singleExecute(getQuery(5),
						statusheaderChanged); // hrms_leave_hdr
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
					setLeaveBalAndOnhold(leaveAppNo, empId);
				}// end of if
				applStatus = "approved";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// collect(leaveApp ,status, request);
		return applStatus;

	}// end of changeApplStatus
*/
	/**
	 * THIS METHOD IS USED FOR CALCULATING LEAVE ABLANCE AND ON HOLD LEAVES
	 * PENDING FOR APPROVAL
	 * 
	 * @param levApp
	 * @param leaveAppNo---leave
	 *            application number
	 * @return boolean
	 */
	/*public boolean setLeaveBalAndOnhold(String leaveAppNo, String empId) {
		String policyCode = getLeavePolicyCode(empId);

		boolean result = false;
		String balQuery = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
				+ " WHERE LEAVE_APPLICATION_CODE='" + leaveAppNo + "' ";
		Object[][] balObj = getSqlModel().getSingleResult(balQuery);
		*//**
		 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE APPLICABLE
		 * LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
		 *//*
		for (int j = 0; j < balObj.length; j++) {
			String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE LEAVE_POLICY_CODE="
					+ policyCode
					+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][2]);
			Object[][] zeroBalance = getSqlModel().getSingleResult(
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
*/
	/*public String approveReject(HttpServletRequest request, String empCode,
			String leaveAppNo, String status, String remarks,
			String approverId, String level) {
		String policyCode = getLeavePolicyCode(empCode);

		boolean result = false;
		String applStatus = "pending";
		Object[][] empFlow = null;

		Object[][] changeStatus = new Object[1][4];
		changeStatus[0][0] = leaveAppNo; // application code
		changeStatus[0][1] = approverId; // user employee id
		changeStatus[0][2] = status; // status
		changeStatus[0][3] = remarks; // remark
		if (String.valueOf(status).equals("A")) {
			String balQuery = " SELECT NVL(LEAVE_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE='"
					+ changeStatus[0][0]
					+ "' ";
			Object[][] balObj = getSqlModel().getSingleResult(balQuery);

			// METHODS FROM ACTION
			empFlow = generateEmpFlow(empCode, "Leave",
					Integer.parseInt(level) + 1);

			applStatus = changeApplStatus(empFlow, String.valueOf(leaveAppNo),
					empCode, status, approverId);

		}// end of if
		if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status); // status
			reject[0][1] = String.valueOf(leaveAppNo);// application code

			String leaveDtlRecord = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo + " ";

			Object[][] leaveDtlRecordObj = getSqlModel().getSingleResult(
					leaveDtlRecord);

			String balQuery = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE='"
					+ changeStatus[0][0]
					+ "' ";
			Object[][] balObj = getSqlModel().getSingleResult(balQuery);
			*//**
			 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE
			 * APPLICABLE LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
			 *//*
			for (int j = 0; j < balObj.length; j++) {
				String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][3]);
				Object[][] zeroBalance = getSqlModel().getSingleResult(
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

			String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";

			Object compOffObj[][] = getSqlModel().getSingleResult(
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
				approverId, level, empFlow, applStatus);

		return applStatus;
	}// end of forward
*/
	/*public void createTemplateWithAlerts(HttpServletRequest request,
			String status, String leaveAppNo, String remarks, String empCode,
			String approverId, String level, Object[][] empFlow,
			String applStatus) {
		// --------Email Templates + Process Manager Alert--------------start
		try {

			empFlow = generateEmpFlow(empCode, "Leave", 1);

			if (!String.valueOf(status).equals("P")) {
				String module = "";
				try {
					
					 * Remove process manager alert
					 
					MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
					processAlerts.initiate(context, session);
					module = "Leave";
					processAlerts.removeProcessAlert(
							String.valueOf(leaveAppNo), module);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String applicant = "", oldApprover = "";
				try {
					applicant = empCode;
					oldApprover = approverId;
				} catch (Exception e) {
					logger.error(e);
				}

				String empID = "", msgType = "";
				String applnID = String.valueOf(leaveAppNo);
				String alertLevel = String.valueOf(Integer.parseInt(level) + 1);

				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

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
				
				 try { template.sendProcessManagerAlert(empID, module,
				 * msgType, applnID, alertLevel); } catch (Exception e) {
				 * logger.error(e); e.printStackTrace(); }
				 
				String keepInfo = "";
				String keepInformedId = " SELECT  NVL(LEAVE_KEEP_INFORMED,'NA') FROM HRMS_LEAVE_HDR "
						+ " WHERE LEAVE_APPL_CODE=" + applnID;

				Object[][] keepInformedObj = getSqlModel().getSingleResult(
						keepInformedId);

				if (keepInformedObj != null) {
					keepInfo = !String.valueOf(keepInformedObj[0][0]).equals(
							"NA") ? String.valueOf(keepInformedObj[0][0]) : "";
					template.sendApplicationMailToKeepInfo(keepInfo);

				}

				try {
					if (!String.valueOf(empFlow[0][0]).equals("0")) {
						logger.info("HR TO APPROVER "
								+ String.valueOf(empFlow[0][0]));
						template.sendApplicationMailToAlternateApprover(String
								.valueOf(empFlow[0][0]));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (!String.valueOf(empFlow[0][3]).equals("0")) {
						logger.info("HR TO ALTERNATE APPROVER "
								+ String.valueOf(empFlow[0][3]));
						template.sendApplicationMailToAlternateApprover(String
								.valueOf(empFlow[0][3]));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					String link = "/leaves/LeaveApplication_retriveDetails.action";
					String linkParam = "levApplicationCode=" + applnID
							+ "&levStatus=" + status;

					String ccId = oldApprover;

					if (!keepInfo.equals("")) {
						ccId += "," + keepInfo;
					}

					String alternateApprover = (empFlow != null && !String
							.valueOf(empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";

					String managerId = (empFlow != null && !String.valueOf(
							empFlow[0][0]).equals("0")) ? String
							.valueOf(empFlow[0][0]) : "";

					System.out
							.println("alternateApprover---------------------------"
									+ alternateApprover);
					String actualStataus = "";

					if (status.equals("A")) {
						actualStataus = "Approved";
					}
					if (status.equals("R")) {
						actualStataus = "Rejected";
					}
					if (status.equals("B")) {
						actualStataus = "SentBack";
						template.sendProcessManagerAlert("", module, "A",
								applnID, alertLevel, linkParam, link,
								actualStataus, applicant, "", "", applicant,oldApprover);

						template.sendProcessManagerAlert(managerId, module,
								"I", applnID, alertLevel, linkParam, link,
								actualStataus, applicant, alternateApprover,
								ccId, "",oldApprover);

					} else {
						template.sendProcessManagerAlert(managerId, module,
								"I", applnID, alertLevel, linkParam, link,
								actualStataus, applicant, alternateApprover,
								ccId, applicant,oldApprover);
					}

				} catch (Exception e) {
					logger.error(e);

					e.printStackTrace();
				}

				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		// --------Email Templates + Process Manager Alert--------------end
	}*/

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
			Object[][] userData = getSqlModel().getSingleResult(query);
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
		Object[][] data = getSqlModel().getSingleResult(cancelQuery);
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
		String policyCode = getLeavePolicyCode(leave.getEmpCode());

		for (int i = 0; i < values.length; i++) {
			String balQuery = " SELECT NVL(LEAVE_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE='" + values[i] + "' ";
			Object[][] balObj = getSqlModel().getSingleResult(balQuery);
			/**
			 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE
			 * APPLICABLE LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
			 */
			for (int j = 0; j < balObj.length; j++) {
				String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
						+ " WHERE LEAVE_POLICY_CODE="
						+ policyCode
						+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][2]);
				Object[][] zeroBalance = getSqlModel().getSingleResult(
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
					String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='N' WHERE LEAVE_APPL_CODE ='"
							+ values[i] + "' ";
					getSqlModel().singleExecute(updateHdr);
					String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'N'  "
							+ " WHERE LEAVE_APPLICATION_CODE='"
							+ values[i]
							+ "' ";
					getSqlModel().singleExecute(updateDtl);
					String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'N'  "
							+ " WHERE LEAVE_APPLICATION_CODE='"
							+ values[i]
							+ "' ";
					getSqlModel().singleExecute(updateDtlHistory);
					String Query = "UPDATE HRMS_LEAVE_HDR SET LEAVE_APP_CANCEL_WITH='"
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

		String query = "  SELECT  LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),	"
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

		Object[][] values = getSqlModel().getSingleResult(query);

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
		String balQuery = " SELECT NVL(LEAVE_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
				+ " WHERE LEAVE_APPLICATION_CODE="
				+ leaveApplication.getLevAppCode() + " ";

		Object[][] balObj = getSqlModel().getSingleResult(balQuery);

		if (balObj.length > 0) {
			String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='C' WHERE LEAVE_APPL_CODE ="
					+ leaveApplication.getLevAppCode() + " ";
			getSqlModel().singleExecute(updateHdr);
			String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'C'  "
					+ " WHERE LEAVE_APPLICATION_CODE="
					+ leaveApplication.getLevAppCode() + " ";
			getSqlModel().singleExecute(updateDtl);
			String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'C'  "
					+ " WHERE LEAVE_APPLICATION_CODE="
					+ leaveApplication.getLevAppCode() + " ";
			getSqlModel().singleExecute(updateDtlHistory);
		}// end of if
		return true;

	}// end of cancelLeaves

	public String getLeavePolicyCode(String empId) {
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		System.out.println("leaveBean.getEmpCode()------" + empId);
		String leavePolicyCode = model.getLeavePolicy(empId);
		model.terminate();
		return leavePolicyCode;
	}

	/*public String onlineApproveReject(HttpServletRequest request,
			String empCode, String leaveAppNo, String status, String remarks,
			String approverId, String level) {
		String result = "";
		String res = "";
		String query = " SELECT APPROVED_BY,LEAVE_STATUS FROM HRMS_LEAVE_HDR WHERE EMP_ID="
				+ empCode + " AND LEAVE_APPL_CODE=" + leaveAppNo;

		Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverId)
					&& String.valueOf(approverIdObj[0][1]).equals("P")) {
				res = approveRejectSendBackLevApp(request, empCode, leaveAppNo,
						status, remarks, approverId, level);

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
*/
	/*public String onlineApproveRejectForCancellation(
			HttpServletRequest request, String empCode, String leaveAppNo,
			String status, String remarks, String approverId, String level) {
		String result = "";
		String res = "";
		String query = " SELECT APPROVED_BY,LEAVE_STATUS FROM HRMS_LEAVE_HDR WHERE EMP_ID="
				+ empCode + " AND LEAVE_APPL_CODE=" + leaveAppNo;

		Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverId)
					&& String.valueOf(approverIdObj[0][1]).equals("C")) {
				res = approveRejCancellationLeaveApp(request, empCode,
						leaveAppNo, status, remarks, approverId, level);

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
*/
	/*public String approveRejectSendBackLevApp(HttpServletRequest request,
			String empCode, String leaveAppNo, String status, String remarks,
			String approverId, String level) {
		String applStatus = "";
		try {
			String policyCode = getLeavePolicyCode(empCode);
			boolean result = false;
			applStatus = "pending";
			Object[][] empFlow = null;
			Object[][] changeStatus = new Object[1][4];
			changeStatus[0][0] = leaveAppNo; // application code
			changeStatus[0][1] = approverId; // user employee id
			changeStatus[0][2] = status; // status
			changeStatus[0][3] = remarks; // remark
			if (String.valueOf(status).equals("A")) {

				empFlow = generateEmpFlow(empCode, "Leave", Integer
						.parseInt(level) + 1);

				applStatus = changeApplStatus(empFlow, String
						.valueOf(leaveAppNo), empCode, status, approverId);

			}//
			if (String.valueOf(status).equals("B")) {
				Object[][] sendBackObj = new Object[1][2];
				sendBackObj[0][0] = status; // status
				sendBackObj[0][1] = leaveAppNo;// application code

				
				 * String leaveHdrQuery = " UPDATE HRMS_LEAVE_HDR SET
				 * LEAVE_STATUS='B' ,APPROVED_BY=" + approverId +
				 * ",LEAVE_LEVEL=1 WHERE LEAVE_APPL_CODE=" + leaveAppNo + " ";
				 

				String leaveHdrQuery = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS='B' ,LEAVE_APPROVEDBY_ADMIN="
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
				Object[][] updateData = new Object[1][2];
				Object[][] updateheaderData = new Object[1][3];
				updateData[0][0] = "R";
				updateheaderData[0][0] = "R";
				String query = "SELECT NVL(LEAVE_CLOSING_BALANCE,0),TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),TO_CHAR(LEAVE_DAYS_HRS,'HH24:MI'),HRMS_LEAVE_DTL.LEAVE_CODE,HRMS_LEAVE_DTL.EMP_ID FROM HRMS_LEAVE_DTL "
						+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_LEAVE_DTL.EMP_ID "
						+ "	AND HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_DTL.LEAVE_CODE) "
						+ " INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
						+ "	WHERE  LEAVE_HRS_FLAG='Y' AND LEAVE_APPLICATION_CODE= "
						+ leaveAppNo;
				Object[][] data = getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {
					updateData[0][1] = leaveAppNo;
					updateheaderData[0][1] = approverId;
					updateheaderData[0][2] = leaveAppNo;
					levModel.updateBalanceHrs(empCode, String
							.valueOf(data[0][0]), String.valueOf(data[0][1]),
							String.valueOf(data[0][2]), String
									.valueOf(data[0][3]), "add");
					getSqlModel().singleExecute(getQuery(25), updateheaderData);
					getSqlModel().singleExecute(getQuery(26), updateData);
					getSqlModel().singleExecute(getQuery(27), updateData);
					applStatus = "rejected";
				}

				else {

					Object[][] reject = new Object[1][2];
					Object[][] updateleavehdr = new Object[1][3];
					reject[0][0] = String.valueOf(status); // status
					reject[0][1] = String.valueOf(leaveAppNo);// application
					updateleavehdr[0][0] = String.valueOf(status); // status
					updateleavehdr[0][1] = approverId;
					updateleavehdr[0][2] = String.valueOf(leaveAppNo);// application
					// code

					String leaveDtlRecord = " SELECT  NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') FROM HRMS_LEAVE_DTL "
							+ " WHERE LEAVE_APPLICATION_CODE="
							+ leaveAppNo
							+ " ";

					Object[][] leaveDtlRecordObj = getSqlModel()
							.getSingleResult(leaveDtlRecord);

					String balQuery = " SELECT  NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0), NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
							+ " WHERE LEAVE_APPLICATION_CODE='"
							+ changeStatus[0][0] + "' ";
					Object[][] balObj = getSqlModel().getSingleResult(balQuery);
					*//**
					 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO
					 * BALANCE APPLICABLE LEAVE DAYS SET TO BE ZERO IE CLOSING
					 * BALANCE =0
					 *//*
					for (int j = 0; j < balObj.length; j++) {
						String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
								+ " WHERE LEAVE_POLICY_CODE="
								+ policyCode
								+ " AND LEAVE_CODE="
								+ String.valueOf(balObj[j][3]);
						Object[][] zeroBalance = getSqlModel().getSingleResult(
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
					getSqlModel().singleExecute(getQuery(5), updateleavehdr);// hrms_leave_hdr
					// leave status
					// update
					getSqlModel().singleExecute(getQuery(6), reject);// hrms_leave_dtl
					// leave status
					// update
					getSqlModel().singleExecute(getQuery(8), reject);// hrms_leave_dtlhistory
					// leave status
					// update

					String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";

					Object compOffObj[][] = getSqlModel().getSingleResult(
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

			}// end of if
			getSqlModel().singleExecute(getQuery(2), changeStatus);// insert
			// record
			// into
			// hrms_leave_path
			createTemplateWithAlerts(request, status, leaveAppNo, remarks,
					empCode, approverId, level, empFlow, applStatus);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return applStatus;

	}
*/
	public void getAllTypeOfApplications(LeaveAdminApproval levApp,
			HttpServletRequest request) {

		try {

			String sqlQuery = getSqlQuery(levApp);

			sqlQuery += "	AND  LEAVE_STATUS IN('P','C') ";

			sqlQuery += " ORDER BY LEAVE_APPL_CODE DESC ";

			Object pendingData[][] = getSqlModel().getSingleResult(sqlQuery);

			if (pendingData != null && pendingData.length > 0) {

				levApp.setPendingLength("true");
				String[] pageIndex = Utility.doPaging(levApp.getMyPage(),
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
					LeaveAdminApproval bean1 = new LeaveAdminApproval();
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
					bean1.setListstatus(String.valueOf(pendingData[i][7]));
					bean1.setApprovername(String.valueOf(pendingData[i][8]));
					// token
					pendingList.add(bean1);
				}
				levApp.setPendingList(pendingList);

			}
			// PAGING FOR APPROVED PENDING LIST

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void getApprovedList(LeaveAdminApproval levApp,
			HttpServletRequest request, String empId) {

		try {
			String sqlQuery = getSqlQuery(levApp);

			sqlQuery += "	AND  LEAVE_STATUS IN('A') ";

			sqlQuery += " ORDER BY LEAVE_APPL_CODE DESC ";

			Object approvedData[][] = getSqlModel().getSingleResult(sqlQuery);

			String[] pageIndexApproved = Utility.doPaging(levApp
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
					LeaveAdminApproval bean1 = new LeaveAdminApproval();
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
					bean1.setListstatus(String.valueOf(approvedData[i][7]));
					bean1.setApprovername(String.valueOf(approvedData[i][8]));

					approvedList.add(bean1);

				}
				levApp.setAppList(approvedList);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void getRejectedList(LeaveAdminApproval levApp,
			HttpServletRequest request, String empId) {
		try {
			// TODO Auto-generated method stub
			String sqlQuery = getSqlQuery(levApp);

			sqlQuery += "	AND  LEAVE_STATUS IN('R') ";

			sqlQuery += " ORDER BY LEAVE_APPL_CODE DESC ";

			Object rejectedData[][] = getSqlModel().getSingleResult(sqlQuery);

			String[] pageIndexRejected = Utility.doPaging(levApp
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

					LeaveAdminApproval leaveRejectedBean = new LeaveAdminApproval();
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
					leaveRejectedBean.setListstatus(String
							.valueOf(rejectedData[i][7]));
					leaveRejectedBean.setApprovername(String
							.valueOf(rejectedData[i][8]));

					rejectedList.add(leaveRejectedBean);

				}
				levApp.setRejList(rejectedList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*public String approveRejCancellationLeaveApp(HttpServletRequest request,
			String empCode, String leaveAppNo, String status, String remarks,
			String approverId, String level) {
		// TODO Auto-generated method stub

		String applStatus = "";
		try {
			String policyCode = getLeavePolicyCode(empCode);
			boolean result = false;
			applStatus = "pending";
			Object[][] empFlow = null;
			Object[][] changeStatus = new Object[1][4];
			changeStatus[0][0] = leaveAppNo; // application code
			changeStatus[0][1] = approverId; // user employee id
			changeStatus[0][2] = status; // status
			changeStatus[0][3] = remarks; // remark
			if (String.valueOf(status).equals("X")) { // Approve Cancellation
				
				  String balQuery = " SELECT LEAVE_DAYS,EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL " + " WHERE LEAVE_APPLICATION_CODE='" +
				  changeStatus[0][0] + "' "; Object[][] balObj =
				  getSqlModel().getSingleResult(balQuery);
				  
				  //METHODS FROM ACTION
				 empFlow = generateEmpFlow(empCode, "Leave", Integer
						.parseInt(level) + 1);

				applStatus = changeApplStatusForApprovedCancelAPPlication(
						empFlow, String.valueOf(leaveAppNo), empCode, status,approverId);

			}//
			if (String.valueOf(status).equals("Z")) {// Reject Cancellation
				String updateHdr = " UPDATE HRMS_LEAVE_HDR SET LEAVE_STATUS ='Z' WHERE LEAVE_APPL_CODE ="
						+ leaveAppNo + " ";
				getSqlModel().singleExecute(updateHdr);
				String updateDtl = " UPDATE HRMS_LEAVE_DTL SET LEAVE_DTL_STATUS = 'Z'  "
						+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo + " ";
				getSqlModel().singleExecute(updateDtl);
				String updateDtlHistory = " UPDATE HRMS_LEAVE_DTLHISTORY SET LEAVE_DTL_STATUS = 'Z'  "
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
					applStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;
	}
*/
	/*public String changeApplStatusForApprovedCancelAPPlication(
			Object[][] empFlow, String leaveAppNo, String empId, String status,String adminApproverId) {
		String applStatus = "pending";
		boolean result = false;
		try {
			LeaveApplicationModel levModel = new LeaveApplicationModel();
			levModel.initiate(context, session);
			Object[][] updateData = new Object[1][2];
			updateData[0][0] = "X";
			String query = "SELECT NVL(LEAVE_CLOSING_BALANCE,0),TO_CHAR(LEAVE_HRS_CLOSING_BALANCE,'HH24:MI'),TO_CHAR(LEAVE_DAYS_HRS,'HH24:MI'),HRMS_LEAVE_DTL.LEAVE_CODE,HRMS_LEAVE_DTL.EMP_ID FROM HRMS_LEAVE_DTL "
					+ "	LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_LEAVE_DTL.EMP_ID "
					+ "	AND HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_DTL.LEAVE_CODE) "
					+ " INNER JOIN 	HRMS_LEAVE_HDR ON(HRMS_LEAVE_HDR.LEAVE_APPL_CODE=HRMS_LEAVE_DTL.LEAVE_APPLICATION_CODE) "
					+ "	WHERE  LEAVE_HRS_FLAG='Y' AND LEAVE_APPLICATION_CODE= "
					+ leaveAppNo;
			Object[][] data = getSqlModel().getSingleResult(query);
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
				Object[][] statusChanged = new Object[1][2];
				Object[][] statusheaderChanged = new Object[1][3];
				statusChanged[0][0] = "X"; // status
				statusheaderChanged[0][0] = "X"; // status
				statusChanged[0][1] = leaveAppNo; // application code
				statusheaderChanged[0][1] = adminApproverId; // application
				// code
				statusheaderChanged[0][2] = leaveAppNo; // application code
				result = getSqlModel().singleExecute(getQuery(5),
						statusheaderChanged); // hrms_leave_hdr
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
				applStatus = "approved";
			
				 
			}
			applStatus = "approved";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// collect(leaveApp ,status, request);
		return applStatus;

	}// end of changeApplStatus
*/
	private void updateCompOffDetails(String leaveAppNo, String empCode) {
		try {
			String compOffAppQuery = " SELECT NVL(CONF_COMP_OFF,0),NVL(CONF_COMP_OFF_DAYS,0) FROM HRMS_ATTENDANCE_CONF ";

			Object compOffObj[][] = getSqlModel().getSingleResult(
					compOffAppQuery);

			String leaveDtlRecord = " SELECT  NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE,TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY') FROM HRMS_LEAVE_DTL "
					+ " WHERE LEAVE_APPLICATION_CODE=" + leaveAppNo + " ";

			Object[][] leaveDtlRecordObj = getSqlModel().getSingleResult(
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

	public boolean setLeaveBalAndOnholdForApprovedCancelApplication(
			String leaveAppNo, String empId) {
		String policyCode = getLeavePolicyCode(empId);

		boolean result = false;
		String balQuery = " SELECT NVL(LEAVE_DAYS+LEAVE_PENALTY_ADJUST_DAYS,0),EMP_ID,LEAVE_CODE FROM HRMS_LEAVE_DTL "
				+ " WHERE LEAVE_APPLICATION_CODE='" + leaveAppNo + "' ";
		Object[][] balObj = getSqlModel().getSingleResult(balQuery);
		/**
		 * CHECK FOR ZERO BALANCE APPLICABLE IF LEAVE IS ZERO BALANCE APPLICABLE
		 * LEAVE DAYS SET TO BE ZERO IE CLOSING BALANCE =0
		 */
		for (int j = 0; j < balObj.length; j++) {
			String zeroBlncQuery = "SELECT LEAVE_IS_ZERO_BALANCE FROM HRMS_LEAVE_POLICY_DTL "
					+ " WHERE LEAVE_POLICY_CODE="
					+ policyCode
					+ " AND LEAVE_CODE=" + String.valueOf(balObj[j][2]);
			Object[][] zeroBalance = getSqlModel().getSingleResult(
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

	public void createTemplateWithAlertsForCancellation(
			HttpServletRequest request, String status, String leaveAppNo,
			String remarks, String empCode, String approverId, String level,
			Object[][] empFlow, String applStatus) {
		// --------Email Templates + Process Manager Alert--------------start
		try {
			if (!String.valueOf(status).equals("P")) {
				String module;
				module = "Leave Cancellation";
				try {
					MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
					processAlerts.initiate(context, session);

					processAlerts.removeProcessAlert(
							String.valueOf(leaveAppNo), module);
				} catch (Exception e) {
					// TODO: handle exception
				}
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
				String applnID = String.valueOf(leaveAppNo);
				String alertLevel = String.valueOf(Integer.parseInt(level) + 1);

				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

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
				 

				String keepInformedId = " SELECT  NVL(LEAVE_KEEP_INFORMED,'NA') FROM HRMS_LEAVE_HDR "
						+ " WHERE LEAVE_APPL_CODE=" + applnID;

				Object[][] keepInformedObj = getSqlModel().getSingleResult(
						keepInformedId);
				String keepInfo = "";
				if (keepInformedObj != null && keepInformedObj.length > 0) {
					keepInfo = !String.valueOf(keepInformedObj[0][0]).equals(
							"NA") ? String.valueOf(keepInformedObj[0][0]) : "0";
					template.sendApplicationMailToKeepInfo(keepInfo);

				}

				try {
					String link = "/leaves/LeaveApplication_retriveDetails.action";
					String linkParam = "levApplicationCode=" + applnID
							+ "&levStatus=" + status;

					String ccId = oldApprover;

					if (!keepInfo.equals("")) {
						ccId += "," + keepInfo;
					}

					String alternateApprover = (empFlow != null && !String
							.valueOf(empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";

					String managerId = (empFlow != null && !String.valueOf(
							empFlow[0][0]).equals("0")) ? String
							.valueOf(empFlow[0][0]) : "";

					System.out
							.println("alternateApprover---------------------------"
									+ alternateApprover);
					String actualStataus = "";

					if (status.equals("X")) {
						actualStataus = "Cancellation Approved";
					}
					if (status.equals("Z")) {
						actualStataus = "Cancellation Rejected";
					}
					template.sendProcessManagerAlert(managerId, module, "I",
							applnID, alertLevel, linkParam, link,
							actualStataus, applicant, alternateApprover, ccId,
							applicant,oldApprover);

				} catch (Exception e) {
					logger.error(e);

					e.printStackTrace();
				}

				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();

			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		// --------Email Templates + Process Manager Alert--------------end
	}

	private String getSqlQuery(LeaveAdminApproval bean) {
		String sqlQuery = "";

		try {
			sqlQuery = "SELECT LEAVE_APPL_CODE,HRMS_LEAVE_HDR.EMP_ID,E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME,TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),	 LEAVE_STATUS,LEAVE_LEVEL,E1.EMP_TOKEN ,DECODE(LEAVE_STATUS,'P','Pending','C','Cancel','A','Approve','R','Rejected') "
					+ ",E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME AS APPROVER_NAME "
					+ "FROM HRMS_LEAVE_HDR "
					+ "INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_LEAVE_HDR.EMP_ID) "
					+ "INNER JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID=HRMS_LEAVE_HDR.APPROVED_BY) "
					+ "WHERE 1=1";
			if (!bean.getSearchEmpCode().equals("")) {
				sqlQuery += " AND HRMS_LEAVE_HDR.EMP_ID="
						+ bean.getSearchEmpCode().trim();
			}
			if (!bean.getFromdate().equals("") && !bean.getTodate().equals("")) {
				sqlQuery += " AND((LEAVE_FROM_DATE BETWEEN TO_DATE('"
						+ bean.getFromdate() + "','dd/mm/yyyy') AND TO_DATE('"
						+ bean.getTodate() + "','dd/mm/yyyy'))";
				
				sqlQuery += " OR (LEAVE_TO_DATE BETWEEN TO_DATE('"
					+ bean.getFromdate() + "','dd/mm/yyyy') AND TO_DATE('"
					+ bean.getTodate() + "','dd/mm/yyyy')))";
			}
			if (!bean.getBranchCode().equals("")) {
				sqlQuery += " AND E1.EMP_CENTER = "
						+ bean.getBranchCode().trim();
			}
			if (!bean.getDivisionCode().equals("")) {
				sqlQuery += " AND E1.EMP_DIV=" + bean.getDivisionCode().trim();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return sqlQuery;
	}

}// end of class
