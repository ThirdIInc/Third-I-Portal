package org.paradyne.model.TravelManagement.TravelProcess;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.TravelManagement.TravelProcess.TmsTrvlApproval;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelApplication;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

public class TmsTrvlApprovalModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlApprovalModel.class);

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals(" ")
				|| result.equals("")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	public void getAllApplications(TmsTrvlApproval travelApplicationBean,
			HttpServletRequest request, String empId, String status) {

		ArrayList<Object> travelApplicationList;

		try {
			String str = "0";

			String allApplicationStatusQuery = " SELECT DISTINCT APPL_INITIATOR,NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,'') AS INITIATOR, TOUR_TRAVEL_REQ_NAME, TO_CHAR(APPL_DATE,'DD-MM-YYYY'), TRIM(TMS_APPLICATION.APPL_STATUS), TMS_APPLICATION.APPL_ID ";

			if (status.equals("processed")) {
				allApplicationStatusQuery += " ,DECODE(TRIM(APPL_STATUS),'P','Pending for manager approval','C','Booking Completed','A','Approved','N','Draft','R','Rejected','F','Revoked') ,APPL_TRAVEL_ID ,APPL_DATE FROM TMS_APPLICATION ";
				str = "Pending for manager approval";
			} else {
				allApplicationStatusQuery += " ,DECODE(TRIM(APPL_STATUS),'P','Pending','A','Approved','N','Draft','R','Rejected','F','Revoked') ,APPL_TRAVEL_ID ,APPL_DATE FROM TMS_APPLICATION ";
			}

			if ((status.equals("processed"))) {
				allApplicationStatusQuery += " INNER JOIN TMS_APP_APPROVAL_DTL  ON (TMS_APP_APPROVAL_DTL.APPL_ID  = TMS_APPLICATION.APPL_ID)";
			}
			allApplicationStatusQuery += " INNER JOIN HRMS_EMP_OFFC ON (TMS_APPLICATION.APPL_INITIATOR = HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE 1=1  AND ";

			if (status.equals("processed")) {
				allApplicationStatusQuery += " APPR_APPROVER_ID="
						+ travelApplicationBean.getUserEmpId();
				travelApplicationBean.setListType("processed");
				str = "Approved";
			} else if (status.equals("under_process")) {
				allApplicationStatusQuery += " APPL_STATUS IN('P') "; // APPL_STATUS
				// IN('P','F')
				travelApplicationBean.setListType("under_process");
			}

			if (status.equals("under_process")) {
				allApplicationStatusQuery += " AND (APPL_MAIN_APPROVER="
						+ travelApplicationBean.getUserEmpId()
						+ " OR APPL_ALTER_APPROVER="
						+ travelApplicationBean.getUserEmpId() + " )";
			}
			if (!checkNull(travelApplicationBean.getTravelId().trim()).equals(
					"")) {
				allApplicationStatusQuery += "AND APPL_TRAVEL_ID = '"
						+ travelApplicationBean.getTravelId().trim() + "'";
			}
			if (!checkNull(travelApplicationBean.getSearchempId().trim())
					.equals("")) {
				allApplicationStatusQuery += "AND APPL_INITIATOR = "
						+ travelApplicationBean.getSearchempId();
			}
			allApplicationStatusQuery += " ORDER BY APPL_DATE DESC";

			Object applicationDataObj[][] = getSqlModel().getSingleResult(
					allApplicationStatusQuery);
			String[] pageIndex = Utility.doPaging(travelApplicationBean
					.getMyPage(), applicationDataObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1")) {
				travelApplicationBean.setMyPage("1");
			}
			logger.info("Integer.parseInt(pageIndex[0])=="
					+ Integer.parseInt(pageIndex[0]));

			logger.info("Integer.parseInt(pageIndex[1])=="
					+ Integer.parseInt(pageIndex[1]));
			travelApplicationList = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				TmsTrvlApproval travelBean = new TmsTrvlApproval();

				travelBean.setInitiatorIdItt(String
						.valueOf(applicationDataObj[i][1]));// Initiator Id
				travelBean.setInitiatorNameItt(String
						.valueOf(applicationDataObj[i][1]));// Initiator name
				travelBean.setTravelRequestNameItt(String
						.valueOf(applicationDataObj[i][2]));// Travel request
				// name
				travelBean.setTravleDateItt(String
						.valueOf(applicationDataObj[i][3]));// Travel date
				travelBean.setTravleApplicationStatusItt(String
						.valueOf(applicationDataObj[i][4]));// Travel
				// application
				// status
				travelBean.setTravelApplicationIdItt(String
						.valueOf(applicationDataObj[i][5]));// Application Id
				if (str.equals("Pending for manager approval")) {
					travelBean.setTravleApplicationStatusNameItt(str);
				} else {
					travelBean.setTravleApplicationStatusNameItt(String
							.valueOf(applicationDataObj[i][6]));// Decoded
					// status
				}
				// name

				travelBean.setTravelIdItt(String
						.valueOf(applicationDataObj[i][7]));

				travelApplicationList.add(travelBean);

			}
			logger.info("travelApplicationList.size()=="
					+ travelApplicationList.size());
			travelApplicationBean
					.setTravelApplicationIteratorlist(travelApplicationList);
			logger.info("travelApplicationBean.size()=="
					+ travelApplicationBean.getTravelApplicationIteratorlist()
							.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public String approveRejectSendBackLevApp(HttpServletRequest request,
			String empCode, String hiddenApplicationCode, String status,
			String approverComments, String approverId, String level) {

		String applStatus = "pending";
		try {
			Object[][] empFlow = null;
			Object[][] changeStatus = new Object[1][4];
			changeStatus[0][0] = hiddenApplicationCode; // application code
			changeStatus[0][1] = approverId; // user employee id
			changeStatus[0][2] = status; // status
			changeStatus[0][3] = approverComments; // remark
			if (String.valueOf(status).equals("A")) {

				empFlow = generateEmpFlow(empCode, "TYD", Integer
						.parseInt(level) + 1);
				/*System.out.println("empFlow[0][0]------------"+empFlow[0][0]);
				System.out.println("empFlow[0][1]------------"+empFlow[0][1]);
				System.out.println("empFlow[0][2]------------"+empFlow[0][2]);				
				System.out.println("empFlow[0][3]------------"+empFlow[0][3]);*/
				
				applStatus = changeApplStatus(empFlow, String
						.valueOf(hiddenApplicationCode), empCode, status,
						level, request);

			}//
			if (String.valueOf(status).equals("B")) {

				String updateStatusQuery = " UPDATE TMS_APPLICATION SET APPL_STATUS='B',APPL_MAIN_APPROVER="
						+ approverId
						+ ",APPL_LEVEL=1 WHERE APPL_ID="
						+ hiddenApplicationCode + " ";

				getSqlModel().singleExecute(updateStatusQuery);

				String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='B' "
						+ " WHERE APPL_ID=" + hiddenApplicationCode;

				getSqlModel().singleExecute(updatedtlQueryForSelf);

				String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='B' "
						+ " WHERE APPL_ID= " + hiddenApplicationCode;

				getSqlModel().singleExecute(updatedtlQueryForGuest);

				applStatus = "sendback";
				try {
					// UPDATED BY REEBA
					sendApprovalMail(hiddenApplicationCode, approverId, empCode,
							request, status, empFlow);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}//
			if (String.valueOf(status).equals("R")) {

				String updateStatusQuery = " UPDATE TMS_APPLICATION SET APPL_STATUS='R',APPL_MAIN_APPROVER="
						+ approverId
						+ ",APPL_LEVEL=1 WHERE APPL_ID="
						+ hiddenApplicationCode + " ";

				getSqlModel().singleExecute(updateStatusQuery);

				String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='R' "
						+ " WHERE APPL_ID=" + hiddenApplicationCode;

				getSqlModel().singleExecute(updatedtlQueryForSelf);

				String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='R' "
						+ " WHERE APPL_ID= " + hiddenApplicationCode;

				getSqlModel().singleExecute(updatedtlQueryForGuest);

				applStatus = "rejected";
			}// end of if

			String insertApproverDtlQuery = " INSERT INTO TMS_APP_APPROVAL_DTL(APPR_DTL_COMMENTS, APPR_APPROVER_ID, APPR_LEVEL, APPR_DTL_APPDATE, APPL_ID, APPR_STATUS) "
					+ " VALUES(?,?,?,SYSDATE,?,?)";

			Object insertApproverDtlQueryObj[][] = new Object[1][5];

			insertApproverDtlQueryObj[0][0] = approverComments;
			insertApproverDtlQueryObj[0][1] = approverId;
			insertApproverDtlQueryObj[0][2] = level;
			insertApproverDtlQueryObj[0][3] = hiddenApplicationCode;
			insertApproverDtlQueryObj[0][4] = status;

			getSqlModel().singleExecute(insertApproverDtlQuery,
					insertApproverDtlQueryObj);

			if(applStatus.equals("rejected")){
				callRejectAttendenceData(hiddenApplicationCode);
			}
			
			try {
				// UPDATED BY REEBA
				sendApprovalMail(hiddenApplicationCode, approverId, empCode,
						request, status, empFlow);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;

	}

	public String changeApplStatus(Object[][] empFlow, String applicationCode,
			String empId, String status, String level,
			HttpServletRequest request) {
		String applStatus = "pending";

		try {

			if (empFlow != null && empFlow.length != 0) {
				Object[][] updateApprover = new Object[1][4];
				updateApprover[0][0] = empFlow[0][2]; // level
				updateApprover[0][1] = empFlow[0][0]; // approver id
				updateApprover[0][2] = empFlow[0][3]; // alternate approver id
				updateApprover[0][3] = applicationCode; // application code

				String updateQuery = "  UPDATE TMS_APPLICATION  SET APPL_LEVEL=? ,APPL_MAIN_APPROVER=? ,APPL_ALTER_APPROVER=? WHERE APPL_ID=?  ";

				getSqlModel().singleExecute(updateQuery, updateApprover);

				applStatus = "forwarded";
			} // end of if
			else {

				Object[][] statusChanged = new Object[1][2];
				statusChanged[0][0] = "A"; // status
				applStatus = "approved";

				String isPolicyViolatedQuery = "SELECT NVL(APPL_ISPOLICYVIOLATED,'N'),APPL_MAIN_APPROVER,APPL_LEVEL FROM TMS_APPLICATION WHERE APPL_ID = "
						+ applicationCode;

				Object violationStatusObj[][] = getSqlModel().getSingleResult(
						isPolicyViolatedQuery);

				if (violationStatusObj != null && violationStatusObj.length > 0
						&& String.valueOf(violationStatusObj[0][0]).equals("Y")) {

					applStatus = "forwarded";

					String checkForOfficial = "SELECT NVL(REPORTING_IS_SAMEASOFFICIAL,'N'),NVL(REPORTING_LEVELS,0) FROM HRMS_REPORTING_APPL_TYPE WHERE  REPORTING_APPL_TYPE_ABBREV='TYD'";
					Object OfficialObj[][] = getSqlModel().getSingleResult(
							checkForOfficial);

					if (Integer.parseInt(String.valueOf(OfficialObj[0][1])) == Integer
							.parseInt(level)) {
						empFlow = generateEmpFlowForViolation(String
								.valueOf(violationStatusObj[0][1]), "TYD");

						if (empFlow != null && empFlow.length > 0) {

							if (empFlow[0][0] != null
									&& !empFlow[0][0].equals("null")
									&& !empFlow[0][0].equals("")) {

								int approverlevel = Integer.parseInt(level) + 1;

								String updateStatusQuery = "  UPDATE TMS_APPLICATION  SET APPL_STATUS='P',APPL_MAIN_APPROVER="
										+ String.valueOf(empFlow[0][0])
										+ ",APPL_ALTER_APPROVER="
										+ String.valueOf(empFlow[0][3])
										+ ",APPL_LEVEL="
										+ approverlevel
										+ " WHERE APPL_ID=" + applicationCode;

								getSqlModel().singleExecute(updateStatusQuery);

								String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='P' "
										+ " WHERE APPL_ID= " + applicationCode;

								getSqlModel().singleExecute(
										updatedtlQueryForSelf);

								String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='P' "
										+ " WHERE APPL_ID=" + applicationCode;

								getSqlModel().singleExecute(
										updatedtlQueryForGuest);

							}

							else {
								String updateStatusQuery = "  UPDATE TMS_APPLICATION  SET APPL_STATUS='A'"
										+ " WHERE APPL_ID=" + applicationCode;

								getSqlModel().singleExecute(updateStatusQuery);

								String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='A' "
										+ " WHERE APPL_ID= " + applicationCode;

								getSqlModel().singleExecute(
										updatedtlQueryForSelf);

								String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='A' "
										+ " WHERE APPL_ID=" + applicationCode;

								getSqlModel().singleExecute(
										updatedtlQueryForGuest);
							}

						} else {

							String updateStatusQuery = "  UPDATE TMS_APPLICATION  SET APPL_STATUS='A' WHERE APPL_ID="
									+ applicationCode;

							getSqlModel().singleExecute(updateStatusQuery);

							String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='A' "
									+ " WHERE APPL_ID= " + applicationCode;

							getSqlModel().singleExecute(updatedtlQueryForSelf);

							String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='A' "
									+ " WHERE APPL_ID=" + applicationCode;

							getSqlModel().singleExecute(updatedtlQueryForGuest);
						}

					} else {
						
						applStatus = "approved";
						String updateStatusQuery = "  UPDATE TMS_APPLICATION  SET APPL_STATUS='A'"
								+ " WHERE APPL_ID=" + applicationCode;

						getSqlModel().singleExecute(updateStatusQuery);

						String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='A' "
								+ " WHERE APPL_ID= " + applicationCode;

						getSqlModel().singleExecute(updatedtlQueryForSelf);

						String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='A' "
								+ " WHERE APPL_ID=" + applicationCode;

						getSqlModel().singleExecute(updatedtlQueryForGuest);
					}
				}

				else {
					applStatus = "approved";
					statusChanged[0][1] = applicationCode; // application code

					String updateStatusQuery = "  UPDATE TMS_APPLICATION  SET APPL_STATUS=? WHERE APPL_ID=? ";

					getSqlModel().singleExecute(updateStatusQuery,
							statusChanged);

					String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS=? "
							+ " WHERE APPL_ID=? ";

					getSqlModel().singleExecute(updatedtlQueryForSelf,
							statusChanged);

					String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS=? "
							+ " WHERE APPL_ID=? ";

					getSqlModel().singleExecute(updatedtlQueryForGuest,
							statusChanged);
				}

				// applStatus = "approved";

			}

			String[] approvedAdvanceAmount = request
					.getParameterValues("approvedAdvanceAmount");

			if (approvedAdvanceAmount != null
					&& approvedAdvanceAmount.length > 0) {
				updateApprovedAdvanceAmount(applicationCode, request);
			}

			if (applStatus.equals("approved")) {
				insertAttendenceData(applicationCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;

	}// end of changeApplStatus

	
	public boolean callRejectAttendenceData(String travelAppNo){


		System.out
				.println("In callRejectAttendenceData----------------------------------travelAppNo--------------------------------Trl "
						+ travelAppNo);

		boolean result = false;
		try {
			String sqlQuery = "	SELECT TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') ,TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),APPL_INITIATOR AS EMP_ID "
					+ " FROM  TMS_APPLICATION "
					+ " WHERE APPL_ID="
					+ travelAppNo;

			Object[][] selectobj = getSqlModel().getSingleResult(sqlQuery);

			String[][] splitObj = null;

			if (selectobj != null && selectobj.length > 0) {
				
				String dateQuery = "  SELECT TO_DATE('"
						+ String.valueOf(selectobj[0][1])
						+ "','DD-MM-YYYY')-TO_DATE('"
						+ String.valueOf(selectobj[0][0])
						+ "','DD-MM-YYYY') FROM DUAL ";

				Object dateQueryObj[][] = getSqlModel().getSingleResult(
						dateQuery);
				int looplength = 0;
				if (dateQueryObj != null && dateQueryObj.length > 0) {
					looplength = Integer.parseInt(String
							.valueOf(dateQueryObj[0][0]));
				}

				splitObj = DateUtility.splitDatesObj(String
						.valueOf(selectobj[0][0]), looplength + 1);

				if (splitObj != null && splitObj.length > 0) {

					for (int i = 0; i < splitObj.length; i++) {

						String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
								+ splitObj[i][0].substring(6, 10)
								+ " where ATT_DATE=to_date('"
								+ splitObj[i][0]
								+ "','dd-mm-yyyy') and ATT_EMP_ID='"
								+ selectobj[0][2] + " ' ";

						Object[][] selectObj = getSqlModel().getSingleResult(
								selectQry);

						if (selectObj != null && selectObj.length > 0) {
							String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ splitObj[i][0].substring(6, 10)
									+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,ATT_REG_STATUS_TWO='AB',IS_APPL_PROCESS='N'"
									+ " WHERE ATT_DATE=to_date ( '"
									+ splitObj[i][0]
									+ "','dd-mm-yyyy')"
									+ " and ATT_EMP_ID=" + selectobj[0][2];

							result = getSqlModel().singleExecute(
									myCardTimeActualDataUpdate);
						} 

						
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}
	
	public boolean insertAttendenceData(String travelAppNo) {

		System.out
				.println("In insertAttendenceData----------------------------------travelAppNo--------------------------------Trl "
						+ travelAppNo);

		boolean result = false;
		try {
			String sqlQuery = "	SELECT TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') ,TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),APPL_INITIATOR AS EMP_ID "
					+ " FROM  TMS_APPLICATION "
					+ " WHERE APPL_ID="
					+ travelAppNo;

			Object[][] selectobj = getSqlModel().getSingleResult(sqlQuery);

			String[][] splitObj = null;

			if (selectobj != null && selectobj.length > 0) {
				String shiftQuery = " select EMP_SHIFT from hrms_emp_offc where emp_id="
						+ selectobj[0][2];
				Object[][] shiftObj = getSqlModel().getSingleResult(shiftQuery);

				String dateQuery = "  SELECT TO_DATE('"
						+ String.valueOf(selectobj[0][1])
						+ "','DD-MM-YYYY')-TO_DATE('"
						+ String.valueOf(selectobj[0][0])
						+ "','DD-MM-YYYY') FROM DUAL ";

				Object dateQueryObj[][] = getSqlModel().getSingleResult(
						dateQuery);
				int looplength = 0;
				if (dateQueryObj != null && dateQueryObj.length > 0) {
					looplength = Integer.parseInt(String
							.valueOf(dateQueryObj[0][0]));
				}

				splitObj = DateUtility.splitDatesObj(String
						.valueOf(selectobj[0][0]), looplength + 1);

				if (splitObj != null && splitObj.length > 0) {

					for (int i = 0; i < splitObj.length; i++) {

						String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
								+ splitObj[i][0].substring(6, 10)
								+ " where ATT_DATE=to_date('"
								+ splitObj[i][0]
								+ "','dd-mm-yyyy') and ATT_EMP_ID='"
								+ selectobj[0][2] + " ' ";

						Object[][] selectObj = getSqlModel().getSingleResult(
								selectQry);

						if (selectObj != null && selectObj.length > 0) {
							String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ splitObj[i][0].substring(6, 10)
									+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,ATT_REG_STATUS_TWO='TR'"
									+ " WHERE ATT_DATE=to_date ( '"
									+ splitObj[i][0]
									+ "','dd-mm-yyyy')"
									+ " and ATT_EMP_ID=" + selectobj[0][2];

							result = getSqlModel().singleExecute(
									myCardTimeActualDataUpdate);
						} else {
							String actualDataInsertInAtt = " INSERT INTO HRMS_DAILY_ATTENDANCE_"
									+ splitObj[i][0].substring(6, 10)
									+ "(ATT_DATE, ATT_EMP_ID, ATT_STATUS_ONE, ATT_STATUS_TWO, ATT_REG_STATUS_ONE, ATT_REG_STATUS_TWO,ATT_SHIFT_ID) "
									+ "VALUES(to_date('"
									+ splitObj[i][0]
									+ "','dd-mm-yyyy'),"
									+ selectobj[0][2]
									+ ",'AB','AB','AB','TR',"
									+ shiftObj[0][0]
									+ ")";

							result = getSqlModel().singleExecute(
									actualDataInsertInAtt);
						}

						if (result) {
							String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
									+ splitObj[i][0].substring(6, 10)
									+ " SET IS_APPL_PROCESS='N' "
									+ " WHERE ATT_DATE=to_date ( '"
									+ splitObj[i][0]
									+ "','dd-mm-yyyy')"
									+ " and ATT_EMP_ID=" + selectobj[0][2];

							result = getSqlModel().singleExecute(
									myCardTimeActualDataUpdate);
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] generateEmpFlowForViolation(String empCode, String type) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlowForViolation(empCode, type);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String onlineApproveReject(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String remarks, String highAuthId) {

		String applStatus = "pending";

		try {
			if (String.valueOf(status).equals("A")) {

				String updateStatusQuery = " UPDATE TMS_APPLICATION SET APPL_STATUS='R',APPL_MAIN_APPROVER="
						+ highAuthId
						+ ",APPL_LEVEL=1 WHERE APPL_ID="
						+ applicationCode + " ";

				getSqlModel().singleExecute(updateStatusQuery);

				String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='A' "
						+ " WHERE APPL_ID=" + applicationCode;

				getSqlModel().singleExecute(updatedtlQueryForSelf);

				String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='A' "
						+ " WHERE APPL_ID= " + applicationCode;

				getSqlModel().singleExecute(updatedtlQueryForGuest);

				applStatus = "Approved";
			}// end of if

			if (String.valueOf(status).equals("R")) {

				String updateStatusQuery = " UPDATE TMS_APPLICATION SET APPL_STATUS='R',APPL_MAIN_APPROVER="
						+ highAuthId
						+ ",APPL_LEVEL=1 WHERE APPL_ID="
						+ applicationCode + " ";

				getSqlModel().singleExecute(updateStatusQuery);

				String updatedtlQueryForSelf = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='R' "
						+ " WHERE APPL_ID=" + applicationCode;

				getSqlModel().singleExecute(updatedtlQueryForSelf);

				String updatedtlQueryForGuest = " UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS='R' "
						+ " WHERE APPL_ID= " + applicationCode;

				getSqlModel().singleExecute(updatedtlQueryForGuest);

				applStatus = "rejected";
			}// end of if
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applStatus;

	}

	/** METHOD ADDED BY REEBA* */
	public String onlineApproveRejectSendBackAppl(HttpServletRequest request,
			String empCode, String applicationCode, String status,
			String remarks, String approverId, String level) {
		String result = "";
		String res = "";
		String query = " SELECT APPL_MAIN_APPROVER,APPL_STATUS FROM TMS_APPLICATION WHERE APPL_ID="
				+ applicationCode;

		Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverId)
					&& String.valueOf(approverIdObj[0][1]).equals("P")) {
				res = approveRejectSendBackLevApp(request, empCode,
						applicationCode, status, remarks, approverId, level);

				logger.info("res....." + res);
				if (res.equals("approved"))
					result = "Travel application has been approved.";
				else if (res.equals("rejected"))
					result = "Travel application has been rejected.";
				else if (res.equals("forwarded"))
					result = "Travel application has been forwarded for next approval.";
				else if (res.equals("sendback"))
					result = "Travel application has been sent back to applicant.";

				else
					result = "Error Occured.";
			} else {
				result = "Travel Application has already been processed.";
			}
		}

		return result;

	}
	
	
	public void sendApprovalMail(String applicationCode, String approverId,
			String empCode, HttpServletRequest request, String status,
			Object[][] empFlow) {

		String applicant = "", oldApprover = "", newApprover = "";
		String keepInfo = "";
		logger.info("######### aplicationCode #############" + applicationCode);
		try {
			/*Remove process manager entry from mypage.*/
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Travel";
			processAlerts.removeProcessAlert(String.valueOf(applicationCode),
					module);
			processAlerts.terminate();
			
			String adminId = "";
			String level="1";
			String actualStataus = "";
			String alternateApprover = "";
			String empID = "", msgType = "";
			String applnID = applicationCode;
			applicant = empCode;
			String alertLevel = String.valueOf(Integer.parseInt(level));// + 1				
				String keepInformedId = " SELECT   NVL(APPL_KEEP_INFO,0),APPL_MAIN_APPROVER,APPL_ALTER_APPROVER,APPL_LEVEL FROM tms_application "
						+" WHERE APPL_ID= " + applicationCode;
	
				Object[][] keepInformedObj = getSqlModel().getSingleResult(
						keepInformedId);		
				level =String.valueOf(keepInformedObj[0][3]);		
				applicant = empCode;
				oldApprover = approverId;
				alternateApprover = ((empFlow != null && empFlow.length>0 )&&(!String
						.valueOf(empFlow[0][3]).equals("0"))) ? String
						.valueOf(empFlow[0][3]) : "";

				String [] keepInformedto = null;
			try {				
				if (status.equals("B"))
					newApprover = "";
				else
					newApprover = String.valueOf(empFlow[0][0]);
			} catch (Exception e) {
				logger.error(e);
			}
			if (!String.valueOf(status).equals("P")) {
				TravelApplicationModel model = new TravelApplicationModel();
				model.initiate(context, session);

				String query = "SELECT APPL_EMP_CODE FROM TMS_APP_EMPDTL WHERE APPL_EMP_CODE NOT IN ("
						+ empCode + ") AND  APPL_ID= " + applicationCode;

				Object applicantCodeObj[][] = model.getSqlModel()
						.getSingleResult(query);
								
				if(!newApprover.equals("")){
					/*Send Email From Approver1 To Approver*/
					EmailTemplateBody templateApp = new EmailTemplateBody();// Initiate template
					templateApp.initiate(context, session);// Set respective template name
					templateApp.setEmailTemplate("Travel Application  OldApprover To NewApprover");
					// call compulsory for set the queries of template
					templateApp.getTemplateQueries();
						
					EmailTemplateQuery templateQueryApp1 = templateApp
								.getTemplateQuery(1);// FROM EMAIL
					templateQueryApp1.setParameter(1, approverId);
					
					EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2);// TO EMAIL
					templateQueryApp2.setParameter(1, newApprover);
					
					EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
					templateQueryApp3.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
					templateQueryApp4.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
					templateQueryApp5.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateQueryApp6 = templateApp
					.getTemplateQuery(6);
					templateQueryApp6.setParameter(1, applicationCode);
					templateQueryApp6.setParameter(2, applicationCode);
					
					EmailTemplateQuery templateQueryApp7 = templateApp
					.getTemplateQuery(7);
					templateQueryApp7.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateQueryApp8 = templateApp
					.getTemplateQuery(8);
					templateQueryApp8.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateQueryApp9 = templateApp
					.getTemplateQuery(9);
					templateQueryApp9.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateQueryApp10 = templateApp
					.getTemplateQuery(10);
					templateQueryApp10.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateQueryApp11 = templateApp
					.getTemplateQuery(11);
					templateQueryApp11.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateQueryApp12 = templateApp
					.getTemplateQuery(12);
					templateQueryApp12.setParameter(1, applicationCode);
					
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					String applicationType = "TravelAppl";
					link_param[0] = applicationType + "#" + applicant + "#"
							+ applicationCode + "#" + "A" + "#" + "..." + "#"
							+ approverId + "#" + level;

					link_param[1] = applicationType + "#" + applicant + "#"
							+ applicationCode + "#" + "R" + "#" + "..." + "#"
							+ approverId + "#" + level;
					link_param[2] = applicationType + "#" + applicant + "#"
							+ applicationCode + "#" + "B" + "#" + "..." + "#"
							+ approverId + "#" + level;
					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";
					
					templateApp.configMailAlert();
					
					String alertLink = "/TMS/TravelAppvr_showApprovalDetails.action";

					String linkParam = "applicationId=" + applicationCode
							+ "&applstatus=A";
					try {

						templateApp.sendProcessManagerAlert(newApprover, "Travel", "A",
								applicationCode, level, linkParam, alertLink, "Approved",
								applicant, "", "", "",approverId);

					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try{
						templateApp.sendApplicationMail();
						templateApp.addOnlineLink(request, link_param, link_label);
						templateApp.clearParameters();// terminate template
						templateApp.terminate();						
					}catch(Exception e){
						e.printStackTrace();
					}	
					
					/*Send Email From NewApprover to Applicant and keep Informed To*/
					
					EmailTemplateBody templateApplicant = new EmailTemplateBody();// Initiate template
					templateApplicant.initiate(context, session);// Set respective template name
					templateApplicant.setEmailTemplate("Travel Email To Applicant Regarding First Approval");
					// call compulsory for set the queries of template
					templateApplicant.getTemplateQueries();
						
					EmailTemplateQuery templateApplicant1 = templateApplicant
								.getTemplateQuery(1);// FROM EMAIL
					templateApplicant1.setParameter(1, approverId);
					
					EmailTemplateQuery templateApplicant2 = templateApplicant
					.getTemplateQuery(2);// TO EMAIL
					templateApplicant2.setParameter(1, newApprover);
					
					EmailTemplateQuery templateApplicant3 = templateApplicant
					.getTemplateQuery(3);
					templateApplicant3.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateApplicant4 = templateApplicant
					.getTemplateQuery(4);
					templateApplicant4.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateApplicant5 = templateApplicant
					.getTemplateQuery(5);
					templateApplicant5.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateApplicant6 = templateApplicant
					.getTemplateQuery(6);
					templateApplicant6.setParameter(1, applicationCode);
					templateApplicant6.setParameter(2, applicationCode);
					
					EmailTemplateQuery templateApplicant7 = templateApplicant
					.getTemplateQuery(7);
					templateApplicant7.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateApplicant8 = templateApplicant
					.getTemplateQuery(8);
					templateApplicant8.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateApplicant9 = templateApplicant
					.getTemplateQuery(9);
					templateApplicant9.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateApplicant10 = templateApplicant
					.getTemplateQuery(10);
					templateApplicant10.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateApplicant11 = templateApplicant
					.getTemplateQuery(11);
					templateApplicant11.setParameter(1, applicationCode);
					
					EmailTemplateQuery templateApplicant12 = templateApplicant
					.getTemplateQuery(12);
					templateApplicant12.setParameter(1, applicationCode);
					
					templateApplicant.configMailAlert();
					
					if(keepInformedObj != null && keepInformedObj.length >0){
						keepInformedto = new String [keepInformedObj.length];
						for(int i=0;i<keepInformedto.length;i++){
							keepInformedto[i]= String.valueOf(keepInformedObj[i][0]);
							
						 }
					 }
					
					if(keepInformedto != null &&  keepInformedto.length >0){ //Send Email to Keep Informed To person
						templateApplicant.sendApplicationMailToKeepInfo(keepInformedto);						
					}
					String ccId="0";	
					if (keepInformedto != null) {
						for (int i = 0; i < keepInformedto.length; i++) {
							if (i == keepInformedto.length) {
								ccId += keepInformedto[i];
						    } else {
								ccId += "," + keepInformedto[i];
							}
						}
					}					
					 alertLink = "/TMS/TravelAppvr_showApprovalDetails.action";
					linkParam = "applicationId=" + applicationCode
							+ "&applstatus="+status;
					try {

						templateApplicant.sendProcessManagerAlert(approverId, "Travel", "I",
								applicationCode, level, linkParam, alertLink, "Approved",
								applicant, alternateApprover, ccId, applicant,approverId);

					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try{
						templateApplicant.sendApplicationMail();
						templateApplicant.clearParameters();// terminate template
						templateApplicant.terminate();						
					}catch(Exception e){
						e.printStackTrace();
					}	
				}
				
				/* SEND MAIL FROM OLDAPPROVER TO APPLICANT STARTS */
				else{
				EmailTemplateBody template = new EmailTemplateBody();// Initiate template
				template.initiate(context, session);// Set respective template name
				template.setEmailTemplate("Travel application  Approver to Applicant");
				// call compulsory for set the queries of template
				template.getTemplateQueries();
				try {
					// get the query as per number
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQuery1.setParameter(1, approverId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);// To EMAIL
					templateQuery2.setParameter(1, empCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, applicationCode);
					templateQuery6.setParameter(2, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);
					templateQuery8.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(9);
					templateQuery9.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery10 = template
							.getTemplateQuery(10);
					templateQuery10.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery11 = template
							.getTemplateQuery(11);
					templateQuery11.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery12 = template
							.getTemplateQuery(12);
					templateQuery12.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// configure the actual contents of the template
				template.configMailAlert();

				/*String level="1";
				String actualStataus = "";
				String alternateApprover = "";
				String empID = "", msgType = "";
				String applnID = applicationCode;
				String alertLevel = String.valueOf(Integer.parseInt(level));// + 1*/
				try {					
					/*String keepInformedId = " SELECT   NVL(APPL_KEEP_INFO,0),APPL_MAIN_APPROVER,APPL_ALTER_APPROVER,APPL_LEVEL FROM tms_application "
							+" WHERE APPL_ID= " + applicationCode;
		
					Object[][] keepInformedObj = getSqlModel().getSingleResult(
							keepInformedId);		
					level =String.valueOf(keepInformedObj[0][3]);		
					applicant = empCode;
					oldApprover = approverId;*/
							
					String link = "/TMS/TravelApplication_viewApplications.action";
					String linkParam = "applicationId=" + applnID
							+ "&applstatus=" + status;
					/*if (level.equals("1")) {
						empFlow = generateEmpFlow(empCode, "TYD", Integer
								.parseInt(level));
					} else {
						empFlow = generateEmpFlow(empCode, "TYD", Integer
								.parseInt(level));
					}
					if (status.equals("B")) {
						newApprover = "";
					} else {
						newApprover = String.valueOf(empFlow[0][0]);
					}*/					

					/*keepInformedto = !checkNull(
							String.valueOf(keepInformedObj[0][0])).equals(
							"") ? String.valueOf(keepInformedObj[0][0])
							: "";*/
					/*String ccId = (empFlow != null && !String.valueOf(
							empFlow[0][0]).equals("0")) ? String
							.valueOf(empFlow[0][0]) : "";
					if (!keepInformedto.equals("")) {
						ccId += "," + keepInformedto;
					}*/
					if(keepInformedObj != null && keepInformedObj.length >0){
					keepInformedto = new String [keepInformedObj.length];
					for(int i=0;i<keepInformedto.length;i++){
						keepInformedto[i]= String.valueOf(keepInformedObj[i][0]);
						
					}
				}
						String ccId="0";	
						if (keepInformedto != null) {
							for (int i = 0; i < keepInformedto.length; i++) {
								if (i == keepInformedto.length) {
									ccId += keepInformedto[i];
								} else {
									ccId += "," + keepInformedto[i];
								}
							}
						}
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
								actualStataus, applicant, "", "",
								applicant, oldApprover);	 
						 linkParam = "applicationId=" + applnID
							+ "&applstatus=R";

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
				// call for sending the email
				String applicantCode = "";
				if (applicantCodeObj != null && applicantCodeObj.length > 0) {
					for (int i = 0; i < applicantCodeObj.length; i++) {
						logger
								.info("########## APPLICANT ID's ################"
										+ applicantCodeObj[i][0]);
						if (applicantCodeObj.length - 1 == i) {
							applicantCode += applicantCodeObj[i][0];
						} else {
							applicantCode += String
									.valueOf(applicantCodeObj[i][0])
									+ ",";
						}
					}
					logger
							.info("####### KEEP INFO APPLICANT ID's #################"
									+ applicantCode);
					template.sendApplicationMailToKeepInfo(applicantCode);
				}
				template.sendApplicationMail();// clear the template
				template.clearParameters();// terminate template
				template.terminate();

				/* SEND MAIL FROM APPROVER TO APPLICANT ENDS */				

				/* SEND MAIL FROM APPROVER TO ADMIN STARTS */

				logger.info("######### approverId #############" + approverId);
				logger.info("######### EMP ID #############" + empCode);

				//String adminId = "";
				
				String StatusQuery ="SELECT APPL_STATUS FROM TMS_APPLICATION  WHERE APPL_ID="+applicationCode;
				Object[][] statusOutObj = getSqlModel().getSingleResult(StatusQuery);
				
				String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
						+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
						+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
						+ empCode + ")";// branch_Id
				Object[][] adminObj = model.getSqlModel().getSingleResult(
						adminQuery);

				if (adminObj != null && adminObj.length > 0) {
					adminId = String.valueOf(adminObj[0][0]);
				}
				adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
						+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch_Id
				adminObj = model.getSqlModel().getSingleResult(adminQuery);
				if (adminObj != null && adminObj.length > 0) {
					adminId = String.valueOf(adminObj[0][0]);
				}

				logger.info("########### ADMIN ID #############" + adminId);
				if (!adminId.equals("") && String.valueOf(statusOutObj[0][0]).equals("A")) {
					EmailTemplateBody adminTemplate = new EmailTemplateBody();// Initiate template
					adminTemplate.initiate(context, session);// Set respective template name
					adminTemplate
							.setEmailTemplate("Travel application Approver to Admin");
					// call compulsory for set the queries of template
					adminTemplate.getTemplateQueries();
					try {
						// get the query as per number
						EmailTemplateQuery templateQuery1 = adminTemplate
								.getTemplateQuery(1);// FROM EMAIL
						// set the parameter of queries
						templateQuery1.setParameter(1, approverId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						// And so on
						EmailTemplateQuery templateQuery2 = adminTemplate
								.getTemplateQuery(2);// To EMAIL
						templateQuery2.setParameter(1, adminId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery3 = adminTemplate
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, approverId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery4 = adminTemplate
								.getTemplateQuery(4);
						templateQuery4.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery5 = adminTemplate
								.getTemplateQuery(5);
						templateQuery5.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery6 = adminTemplate
								.getTemplateQuery(6);
						templateQuery6.setParameter(1, applicationCode);
						templateQuery6.setParameter(2, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery7 = adminTemplate
								.getTemplateQuery(7);
						templateQuery7.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery8 = adminTemplate
								.getTemplateQuery(8);
						templateQuery8.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery9 = adminTemplate
								.getTemplateQuery(9);
						templateQuery9.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery10 = adminTemplate
								.getTemplateQuery(10);
						templateQuery10.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery11 = adminTemplate
								.getTemplateQuery(11);
						templateQuery11.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery12 = adminTemplate
								.getTemplateQuery(12);
						templateQuery12.setParameter(1, adminId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery13 = adminTemplate
								.getTemplateQuery(13);
						templateQuery13.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery14 = adminTemplate
								.getTemplateQuery(14);
						templateQuery14.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// configure the actual contents of the template
					adminTemplate.configMailAlert();
					// Add approval link -pass parameters to the link
					String[] link_parameter = new String[1];
					String[] link_labels = new String[1];
					String applicationType1 = "Travel";
					link_parameter[0] = applicationType1 + "#" + adminId
							+ "#applicationDtls#";
					String selfManager = "  SELECT  APPL_MANAGE_JOURNEY, APPL_MANAGE_ACCOMODATION, APPL_MANAGE_LOCAL_CONV  FROM TMS_APPLICATION "
										+ " WHERE APPL_ID="+applicationCode;
					Object[][] selfMgrObj = model.getSqlModel().getSingleResult(selfManager);
					boolean journeySelf=true;
					if(selfMgrObj!=null && selfMgrObj.length>0){
						if(String.valueOf(selfMgrObj[0][0]).equals("S")&&(String.valueOf(selfMgrObj[0][1]).equals("S"))&& String.valueOf(selfMgrObj[0][2]).equals("S")){
							journeySelf=false;
						}
					}
					if(journeySelf){
					String link = "/TMS/TravelQuickBooking_startBooking.action?applicationId="
							+ applicationCode
							+ "$applicationStatus=A$applicationType=bookingApp";
					// link= PPEncrypter.encrypt(link);
					System.out.println("applicationDtls  ..." + link);
					link_parameter[0] += link;
					link_labels[0] = "Start Booking";
					
					String alertLink = "/TMS/TravelQuickBooking_startBooking.action";
					 
					String linkParam = "applicationId=" + applicationCode +"&applicationStatus=P"+"&applicationType=bookingApp"; 
					try {
						adminTemplate.sendProcessManagerAlert(adminId, "Travel", "A",
								applicationCode, level, linkParam, alertLink, "Pending",
								empCode, "", "", "",approverId);
					
					 System.out.println("Start Booking ------------------------");
					} catch (Exception e) {
						e.printStackTrace();
					}
					}
					adminTemplate.addOnlineLink(request, link_parameter,
							link_labels);

					// call for sending the email
					adminTemplate.sendApplicationMail();// send mail to ADMIN

					adminTemplate.clearParameters();

					// terminate template
					adminTemplate.terminate();
				}
			}
				/* SEND MAIL FROM APPROVER TO ADMIN ENDS */

				/*
				 * SEND MAIL FROM APPROVER TO HIGHER AUTHORITY WHEN POLICY IS
				 * VIOLATED STARTS
				 * 
				 */

				String higherAuthId = "";
				String applicantID ="";
				
				String isPolicyViolatedQuery = "SELECT APPL_ISPOLICYVIOLATED,APPL_STATUS FROM TMS_APPLICATION WHERE APPL_ID = "
						+ applicationCode;

				Object violationStatusObj[][] = model.getSqlModel()
						.getSingleResult(isPolicyViolatedQuery);

				if (violationStatusObj != null && violationStatusObj.length > 0) {
					
					
					if (String.valueOf(violationStatusObj[0][0]).equals("Y") && String.valueOf(violationStatusObj[0][1]).equals("P") ) {

						String higherAuthQuery = "SELECT APPL_MAIN_APPROVER ,APPL_INITIATOR FROM TMS_APPLICATION WHERE APPL_ID="
								+ applicationCode;

						Object higerAuthObj[][] = model.getSqlModel()
								.getSingleResult(higherAuthQuery);

						if (higerAuthObj != null && higerAuthObj.length > 0) {
							higherAuthId = String.valueOf(higerAuthObj[0][0]);
							applicantID =String.valueOf(higerAuthObj[0][1]);
						}

						logger
								.info("$$$$$$$$$$$$$$$$ VIOLATION MAIL @@@@@@@@@@@@@@@@@@@@@@@@@"
										+ higherAuthId);

						EmailTemplateBody escalateToHigherAuthTemplate = new EmailTemplateBody(); // Initiate
						// template
						escalateToHigherAuthTemplate.initiate(context, session);
						// Set respective template name
						escalateToHigherAuthTemplate
								.setEmailTemplate("APPROVER ESCALATION MAIL TO HIGHER AUTHORITY WHEN POLICY IS VIOLATED"); // call
						// compulsory for set the queries of template
						escalateToHigherAuthTemplate.getTemplateQueries();

						try {
							// FROM EMAIL SET THE PARAMETER OF QUERIES
							EmailTemplateQuery templateQuery1 = escalateToHigherAuthTemplate
									.getTemplateQuery(1);
							templateQuery1.setParameter(1, approverId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							// To EMAIL
							EmailTemplateQuery templateQuery2 = escalateToHigherAuthTemplate
									.getTemplateQuery(2);
							templateQuery2.setParameter(1, higherAuthId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery3 = escalateToHigherAuthTemplate
									.getTemplateQuery(3);
							templateQuery3.setParameter(1, approverId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery4 = escalateToHigherAuthTemplate
									.getTemplateQuery(4);
							templateQuery4.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery5 = escalateToHigherAuthTemplate
									.getTemplateQuery(5);
							templateQuery5.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery6 = escalateToHigherAuthTemplate
									.getTemplateQuery(6);
							templateQuery6.setParameter(1, applicationCode);
							templateQuery6.setParameter(2, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery7 = escalateToHigherAuthTemplate
									.getTemplateQuery(7);
							templateQuery7.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery8 = escalateToHigherAuthTemplate
									.getTemplateQuery(8);
							templateQuery8.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery9 = escalateToHigherAuthTemplate
									.getTemplateQuery(9);
							templateQuery9.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery10 = escalateToHigherAuthTemplate
									.getTemplateQuery(10);
							templateQuery10.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery11 = escalateToHigherAuthTemplate
									.getTemplateQuery(11);
							templateQuery11.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery12 = escalateToHigherAuthTemplate
									.getTemplateQuery(12);
							templateQuery12.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery13 = escalateToHigherAuthTemplate
									.getTemplateQuery(13);
							templateQuery13.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// Add approval link -pass parameters to the link

						String[] link_param = new String[3];
						String[] link_label = new String[3];
						
						
						String applicationType = "TravelAppl";
						link_param[0] = applicationType + "#" + applicantID + "#"
								+ applicationCode + "#" + "A" + "#" + "..." + "#"
								+ higherAuthId + "#" + level;

						link_param[1] = applicationType + "#" + applicantID + "#"
								+ applicationCode + "#" + "R" + "#" + "..." + "#"
								+ higherAuthId + "#" + level;
						
						link_param[2] = applicationType + "#" + applicantID + "#"
								+ applicationCode + "#" + "B" + "#" + "..." + "#"
								+ higherAuthId + "#" + level;
						
						link_label[0] = "Approve";
						link_label[1] = "Reject";
						link_label[2] = "Send Back";
						// configure the actual contents of the template
						// configure the actual contents of the template
						escalateToHigherAuthTemplate.configMailAlert();
						
						
						String alertLink = "/TMS/TravelAppvr_showApprovalDetails.action";
						 
						String linkParam = "applicationId=" + applicationCode +"&applstatus=P";
					 
						try {
							
							escalateToHigherAuthTemplate.sendProcessManagerAlert(higherAuthId, "Travel", "A",
									applicationCode, level, linkParam, alertLink, "Pending",
									applicantID, "", "", "",
									approverId);
						 
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
						
						
						escalateToHigherAuthTemplate.addOnlineLink(request,
								link_param, link_label); // call for sending
						// the email
						escalateToHigherAuthTemplate.sendApplicationMail();

						// send mail to ADMIN

						escalateToHigherAuthTemplate.clearParameters();
						// terminate template
						escalateToHigherAuthTemplate.terminate();
					}
				}

				/*
				 * SEND MAIL FROM APPROVER TO HIGHER AUTHORITY WHEN POLICY IS
				 * VIOLATED ENDS
				 */

				// ADDED BY REEBA
				// SEND MAIL TO ACCOUNTANT FOR ADVANCE DISBURSEMENTS
				// INDIVIDUAL MAILS FOR EACH EMPLOYEE WITH ADVANCE AMOUNT
				String advCountQuery = " SELECT TMS_APP_EMPDTL.APPL_ID, APPL_CODE, APPL_EMP_CODE, APPL_EMP_ADVANCE_AMT, APPL_LEVEL, APPL_STATUS "
						+ " FROM TMS_APP_EMPDTL "
						+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('A','C','F') AND APPL_EMP_ADVANCE_AMT > 0 AND TMS_APP_EMPDTL.APPL_ID="
						+ applicationCode;
				Object[][] advCountObj = getSqlModel().getSingleResult(
						advCountQuery);
				
				String accountatnt="";
				
				if (advCountObj != null && advCountObj.length > 0) {
					for (int i = 0; i < advCountObj.length; i++) {
						String authId = "";
						String authQuery = "  SELECT  AUTH_ID, AUTH_ACCOUNTENT, AUTH_ACCOUNTANT_EMAIL_ID   FROM TMS_MANG_AUTH_HDR "
								+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
								+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
								+ advCountObj[i][2] + ")";// branch Id
						Object[][] authObj = model.getSqlModel()
								.getSingleResult(authQuery);
						logger.info("employees with advance======"
								+ advCountObj[i][2]);
						if (authObj != null && authObj.length > 0) {
							
							accountatnt =String.valueOf(authObj[0][1]);
							
							authId = String.valueOf(authObj[0][0]);
							logger.info("0 1======"
									+ String.valueOf(authObj[0][1]));
						}
						authQuery = "  SELECT  AUTH_ID, AUTH_ACCOUNTENT, AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
								+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
						// Id
						Object[][] new_authObj = model.getSqlModel()
								.getSingleResult(authQuery);
						if (new_authObj != null && new_authObj.length > 0) {
							authObj = new_authObj;
							
							accountatnt =String.valueOf(authObj[0][1]);
							
							authId = String.valueOf(new_authObj[0][0]);
							logger.info("0 1 1 1======"
									+ String.valueOf(authObj[0][1]));
							logger.info("Accountant email id======"
									+ String.valueOf(authObj[0][2]));
						}
						logger.info("Authorisation id======" + authId);

						EmailTemplateBody accountantTemplate = new EmailTemplateBody();
						// Initiate template
						accountantTemplate.initiate(context, session);

						// Set respective template name
						accountantTemplate
								.setEmailTemplate("TRAVEL ADVANCE APPL MAIL FROM SYSTEM TO ACCOUNTANT");

						// call compulsory for set the queries of template
						accountantTemplate.getTemplateQueries();

						try {
							// get the query as per number
							EmailTemplateQuery templateQuery1 = accountantTemplate
									.getTemplateQuery(1);// FROM EMAIL
							// NO PARAMETERES TO BE SET FOR SYSMAIL
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							// And so on
							EmailTemplateQuery templateQuery2 = accountantTemplate
									.getTemplateQuery(2);// To EMAIL
							templateQuery2.setParameter(1, authId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery3 = accountantTemplate
									.getTemplateQuery(3);
							templateQuery3.setParameter(1, String
									.valueOf(advCountObj[i][0]));// APPL ID
							templateQuery3.setParameter(2, String
									.valueOf(advCountObj[i][2]));// EMP CODE
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery4 = accountantTemplate
									.getTemplateQuery(4);
							templateQuery4.setParameter(1, applicationCode);// APPL
							// ID
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery5 = accountantTemplate
									.getTemplateQuery(5);
							templateQuery5.setParameter(1, String
									.valueOf(advCountObj[i][0]));// APPL ID
							templateQuery5.setParameter(2, String
									.valueOf(advCountObj[i][4]));// LEVEL
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery6 = accountantTemplate
									.getTemplateQuery(6);
							templateQuery6.setParameter(1, String
									.valueOf(advCountObj[i][0]));// APPL ID
							templateQuery6.setParameter(2, String
									.valueOf(advCountObj[i][4]));// LEVEL
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery7 = accountantTemplate
									.getTemplateQuery(7);
							templateQuery7.setParameter(1, adminId);// MAIN
							// SCHEDULER
							// ID
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery8 = accountantTemplate
									.getTemplateQuery(8);
							templateQuery8.setParameter(1, String
									.valueOf(advCountObj[i][0]));// APPL ID
							templateQuery8.setParameter(2, String
									.valueOf(advCountObj[i][2]));// EMP CODE
						} catch (Exception e) {
							e.printStackTrace();
						}

						// configure the actual contents of the template
						accountantTemplate.configMailAlert();
						// Add approval link -pass parameters to the link
						
						String alertLink="";
						String alertParameter ="";
						
						try {
							System.out.println("before advance alert logic");
							alertLink = "/TMS/AdvClmDisbursement_calforedit.action";
							alertParameter = "hiddencode="
									+ String.valueOf(advCountObj[i][2])
									+ "&hiddenapplId="
									+ String.valueOf(advCountObj[i][0])
									+ "&hiddenapplCode="
									+ String.valueOf(advCountObj[i][1])
									+ "&hiddengustflag=S"
									+ "&hidApplStatus="
									+ String.valueOf(advCountObj[i][5]);
							accountantTemplate.sendProcessManagerAlert(
									accountatnt, "Travel", "A",
									applicationCode, level, alertParameter,
									alertLink, "Approved", applicantID, "", "",
									"", approverId);
							System.out.println("after advance alert logic");
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							String[] acc_link_parameter = new String[1];
							String[] acc_link_labels = new String[1];
							String acc_applicationType = "TravelAppl";
							acc_link_parameter[0] = acc_applicationType + "#"
									+ String.valueOf(authObj[0][1])
									+ "#applicationDtls#";
							String accountant_link = "/TMS/AdvClmDisbursement_calforedit.action?hiddencode="
									+ String.valueOf(advCountObj[i][2])
									+ "$hiddenapplId="
									+ String.valueOf(advCountObj[i][0])
									+ "$hiddenapplCode="
									+ String.valueOf(advCountObj[i][1])
									+ "$hiddengustflag=S"
									+ "$hidApplStatus="
									+ String.valueOf(advCountObj[i][5]);
							// link= PPEncrypter.encrypt(link);
							System.out.println("applicationDtls  ..."
									+ accountant_link);
							acc_link_parameter[0] += accountant_link;
							acc_link_labels[0] = "Advance Disbursement";
							accountantTemplate.addOnlineLink(request,
									acc_link_parameter, acc_link_labels);
						} catch (Exception e) {
							logger
									.error("Error in Advance disb mail link:"
											+ e);
							e.printStackTrace();
						}
						// call for sending the email
						accountantTemplate.sendApplicationMail();

						accountantTemplate.clearParameters();

						// terminate template
						accountantTemplate.terminate();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ADDED BY REEBA
	 * 
	 * @param applicationCode
	 * @param approverId
	 * @param empCode
	 * @param request
	 * @param status
	 * @param empFlow
	 */
	public void sendApprovalMail_OLD(String applicationCode, String approverId,
			String empCode, HttpServletRequest request, String status,
			Object[][] empFlow) {
		logger.info("######### aplicationCode #############" + applicationCode);
		try {
			/* Remove process manager entry from mypage.*/
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Travel";
			processAlerts.removeProcessAlert(String.valueOf(applicationCode),
					module);
			processAlerts.terminate();
						
			if (!String.valueOf(status).equals("P")) {
				TravelApplicationModel model = new TravelApplicationModel();
				model.initiate(context, session);

				String query = "SELECT APPL_EMP_CODE FROM TMS_APP_EMPDTL WHERE APPL_EMP_CODE NOT IN ("
						+ empCode + ") AND  APPL_ID= " + applicationCode;

				Object applicantCodeObj[][] = model.getSqlModel()
						.getSingleResult(query);

				/* SEND MAIL FROM APPROVER TO APPLICANT STARTS */

				EmailTemplateBody template = new EmailTemplateBody();
				// Initiate template
				template.initiate(context, session);

				// Set respective template name
				template
						.setEmailTemplate("Travel application  Approver to Applicant");

				// call compulsory for set the queries of template
				template.getTemplateQueries();

				try {
					// get the query as per number
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQuery1.setParameter(1, approverId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);// To EMAIL
					templateQuery2.setParameter(1, empCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, applicationCode);
					templateQuery6.setParameter(2, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);
					templateQuery8.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(9);
					templateQuery9.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery10 = template
							.getTemplateQuery(10);
					templateQuery10.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery11 = template
							.getTemplateQuery(11);
					templateQuery11.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery12 = template
							.getTemplateQuery(12);
					templateQuery12.setParameter(1, applicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// configure the actual contents of the template
				template.configMailAlert();
				String level="1";
				String actualStataus = "";
				String alternateApprover = "";
				String empID = "", msgType = "";
				String applnID = applicationCode;
				String alertLevel = String.valueOf(Integer.parseInt(level));// + 1
				try {
					String applicant = "", oldApprover = "", newApprover = "";
					String keepInfo = "";
					String keepInformedId = " SELECT   NVL(APPL_KEEP_INFO,0),APPL_MAIN_APPROVER,APPL_ALTER_APPROVER FROM tms_application "
							+" WHERE APPL_ID= " + applicationCode;

					Object[][] keepInformedObj = getSqlModel().getSingleResult(
							keepInformedId);
					applicant = empCode;
					oldApprover = approverId;
					
					String link = "/TMS/TravelApplication_viewApplications.action";
					String linkParam = "applicationId=" + applnID
							+ "&applstatus=" + status;
					
					System.out
							.println("level-----------------------------------"
									+ level);

					if (level.equals("1")) {
						empFlow = generateEmpFlow(empCode, "TYD", Integer
								.parseInt(level));
					} else {
						empFlow = generateEmpFlow(empCode, "TYD", Integer
								.parseInt(level) - 1);
					}

					if (status.equals("B")) {
						newApprover = "";
					} else {
						newApprover = String.valueOf(empFlow[0][0]);
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
								actualStataus, applicant, "", "",
								applicant, oldApprover);
						 
						 linkParam = "applicationId=" + applnID
							+ "&applstatus=R";

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

				// call for sending the email
				String applicantCode = "";
				if (applicantCodeObj != null && applicantCodeObj.length > 0) {
					for (int i = 0; i < applicantCodeObj.length; i++) {
						logger
								.info("########## APPLICANT ID's ################"
										+ applicantCodeObj[i][0]);
						if (applicantCodeObj.length - 1 == i) {
							applicantCode += applicantCodeObj[i][0];
						} else {
							applicantCode += String
									.valueOf(applicantCodeObj[i][0])
									+ ",";
						}
					}
					logger
							.info("####### KEEP INFO APPLICANT ID's #################"
									+ applicantCode);
					template.sendApplicationMailToKeepInfo(applicantCode);
				}

				template.sendApplicationMail();

				// clear the template
				template.clearParameters();

				// terminate template
				template.terminate();

				/* SEND MAIL FROM APPROVER TO APPLICANT ENDS */

				/* SEND MAIL FROM APPROVER TO ADMIN STARTS */

				logger.info("######### approverId #############" + approverId);
				logger.info("######### EMP ID #############" + empCode);

				String adminId = "";

				String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
						+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
						+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
						+ empCode + ")";// branch_Id
				Object[][] adminObj = model.getSqlModel().getSingleResult(
						adminQuery);

				if (adminObj != null && adminObj.length > 0) {
					adminId = String.valueOf(adminObj[0][0]);
				}
				adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
						+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch_Id
				adminObj = model.getSqlModel().getSingleResult(adminQuery);
				if (adminObj != null && adminObj.length > 0) {
					adminId = String.valueOf(adminObj[0][0]);
				}

				logger.info("########### ADMIN ID #############" + adminId);
				if (!adminId.equals("") && String.valueOf(status).equals("A")) {
					EmailTemplateBody adminTemplate = new EmailTemplateBody();
					// Initiate template
					adminTemplate.initiate(context, session);

					// Set respective template name
					adminTemplate
							.setEmailTemplate("Travel application Approver to Admin");

					// call compulsory for set the queries of template
					adminTemplate.getTemplateQueries();

					try {
						// get the query as per number
						EmailTemplateQuery templateQuery1 = adminTemplate
								.getTemplateQuery(1);// FROM EMAIL
						// set the parameter of queries
						templateQuery1.setParameter(1, approverId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						// And so on
						EmailTemplateQuery templateQuery2 = adminTemplate
								.getTemplateQuery(2);// To EMAIL
						templateQuery2.setParameter(1, adminId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery3 = adminTemplate
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, approverId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery4 = adminTemplate
								.getTemplateQuery(4);
						templateQuery4.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery5 = adminTemplate
								.getTemplateQuery(5);
						templateQuery5.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery6 = adminTemplate
								.getTemplateQuery(6);
						templateQuery6.setParameter(1, applicationCode);
						templateQuery6.setParameter(2, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery7 = adminTemplate
								.getTemplateQuery(7);
						templateQuery7.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery8 = adminTemplate
								.getTemplateQuery(8);
						templateQuery8.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery9 = adminTemplate
								.getTemplateQuery(9);
						templateQuery9.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery10 = adminTemplate
								.getTemplateQuery(10);
						templateQuery10.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery11 = adminTemplate
								.getTemplateQuery(11);
						templateQuery11.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery12 = adminTemplate
								.getTemplateQuery(12);
						templateQuery12.setParameter(1, adminId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery13 = adminTemplate
								.getTemplateQuery(13);
						templateQuery13.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						EmailTemplateQuery templateQuery14 = adminTemplate
								.getTemplateQuery(14);
						templateQuery14.setParameter(1, applicationCode);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// configure the actual contents of the template
					adminTemplate.configMailAlert();
					// Add approval link -pass parameters to the link
					String[] link_parameter = new String[1];
					String[] link_labels = new String[1];
					String applicationType1 = "Travel";
					link_parameter[0] = applicationType1 + "#" + adminId
							+ "#applicationDtls#";

					String link = "/TMS/TravelQuickBooking_startBooking.action?applicationId="
							+ applicationCode
							+ "$applicationStatus=P$applicationType=bookingApp";
					// link= PPEncrypter.encrypt(link);
					System.out.println("applicationDtls  ..." + link);
					link_parameter[0] += link;
					link_labels[0] = "Start Booking";
					adminTemplate.addOnlineLink(request, link_parameter,
							link_labels);
					// call for sending the email
					adminTemplate.sendApplicationMail();// send mail to ADMIN
					adminTemplate.clearParameters();
					// terminate template
					adminTemplate.terminate();
				}
				/* SEND MAIL FROM APPROVER TO ADMIN ENDS */

				/*
				 * SEND MAIL FROM APPROVER TO HIGHER AUTHORITY WHEN POLICY IS
				 * VIOLATED STARTS
				 * 
				 */

				String higherAuthId = "";
				String applicantID ="";
				
				String isPolicyViolatedQuery = "SELECT APPL_ISPOLICYVIOLATED,APPL_STATUS FROM TMS_APPLICATION WHERE APPL_ID = "
						+ applicationCode;

				Object violationStatusObj[][] = model.getSqlModel()
						.getSingleResult(isPolicyViolatedQuery);

				if (violationStatusObj != null && violationStatusObj.length > 0) {
					
					
					if (String.valueOf(violationStatusObj[0][0]).equals("Y") && String.valueOf(violationStatusObj[0][1]).equals("P") ) {

						String higherAuthQuery = "SELECT APPL_MAIN_APPROVER ,APPL_INITIATOR FROM TMS_APPLICATION WHERE APPL_ID="
								+ applicationCode;

						Object higerAuthObj[][] = model.getSqlModel()
								.getSingleResult(higherAuthQuery);

						if (higerAuthObj != null && higerAuthObj.length > 0) {
							higherAuthId = String.valueOf(higerAuthObj[0][0]);
							applicantID =String.valueOf(higerAuthObj[0][1]);
						}

						logger
								.info("$$$$$$$$$$$$$$$$ VIOLATION MAIL @@@@@@@@@@@@@@@@@@@@@@@@@"
										+ higherAuthId);

						EmailTemplateBody escalateToHigherAuthTemplate = new EmailTemplateBody(); // Initiate
						// template
						escalateToHigherAuthTemplate.initiate(context, session);
						// Set respective template name
						escalateToHigherAuthTemplate
								.setEmailTemplate("APPROVER ESCALATION MAIL TO HIGHER AUTHORITY WHEN POLICY IS VIOLATED"); // call
						// compulsory for set the queries of template
						escalateToHigherAuthTemplate.getTemplateQueries();

						try {
							// FROM EMAIL SET THE PARAMETER OF QUERIES
							EmailTemplateQuery templateQuery1 = escalateToHigherAuthTemplate
									.getTemplateQuery(1);
							templateQuery1.setParameter(1, approverId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							// To EMAIL
							EmailTemplateQuery templateQuery2 = escalateToHigherAuthTemplate
									.getTemplateQuery(2);
							templateQuery2.setParameter(1, higherAuthId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery3 = escalateToHigherAuthTemplate
									.getTemplateQuery(3);
							templateQuery3.setParameter(1, approverId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery4 = escalateToHigherAuthTemplate
									.getTemplateQuery(4);
							templateQuery4.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery5 = escalateToHigherAuthTemplate
									.getTemplateQuery(5);
							templateQuery5.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery6 = escalateToHigherAuthTemplate
									.getTemplateQuery(6);
							templateQuery6.setParameter(1, applicationCode);
							templateQuery6.setParameter(2, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery7 = escalateToHigherAuthTemplate
									.getTemplateQuery(7);
							templateQuery7.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery8 = escalateToHigherAuthTemplate
									.getTemplateQuery(8);
							templateQuery8.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery9 = escalateToHigherAuthTemplate
									.getTemplateQuery(9);
							templateQuery9.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery10 = escalateToHigherAuthTemplate
									.getTemplateQuery(10);
							templateQuery10.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery11 = escalateToHigherAuthTemplate
									.getTemplateQuery(11);
							templateQuery11.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery12 = escalateToHigherAuthTemplate
									.getTemplateQuery(12);
							templateQuery12.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery13 = escalateToHigherAuthTemplate
									.getTemplateQuery(13);
							templateQuery13.setParameter(1, applicationCode);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// Add approval link -pass parameters to the link

						String[] link_param = new String[2];
						String[] link_label = new String[2];
						String applicationType = "Travel";
						link_param[0] = applicationType + "#" + approverId
								+ "#" + applicationCode + "#" + "A" + "#"
								+ "..." + "#" + higherAuthId;

						link_param[1] = applicationType + "#" + approverId
								+ "#" + applicationCode + "#" + "R" + "#"
								+ "..." + "#" + higherAuthId;

						link_label[0] = "Approve";
						link_label[1] = "Reject";
						// configure the actual contents of the template
						escalateToHigherAuthTemplate.configMailAlert();
						
						
						String alertLink = "/TMS/TravelAppvr_showApprovalDetails.action";
						 
						String linkParam = "applicationId=" + applicationCode +"&applstatus=P";
					 
						try {
							
							escalateToHigherAuthTemplate.sendProcessManagerAlert(higherAuthId, "Travel", "A",
									applicationCode, level, linkParam, alertLink, "Pending",
									applicantID, "", "", "",
									approverId);
						 
						} catch (Exception e) {
							e.printStackTrace();
						}						
						escalateToHigherAuthTemplate.addOnlineLink(request,
								link_param, link_label); // call for sending the email
						escalateToHigherAuthTemplate.sendApplicationMail();// send mail to ADMIN
						escalateToHigherAuthTemplate.clearParameters();// terminate template
						escalateToHigherAuthTemplate.terminate();
					}
				}

				/*
				 * SEND MAIL FROM APPROVER TO HIGHER AUTHORITY WHEN POLICY IS
				 * VIOLATED ENDS
				 */

				// ADDED BY REEBA
				// SEND MAIL TO ACCOUNTANT FOR ADVANCE DISBURSEMENTS
				// INDIVIDUAL MAILS FOR EACH EMPLOYEE WITH ADVANCE AMOUNT
				String advCountQuery = " SELECT TMS_APP_EMPDTL.APPL_ID, APPL_CODE, APPL_EMP_CODE, APPL_EMP_ADVANCE_AMT, APPL_LEVEL, APPL_STATUS "
						+ " FROM TMS_APP_EMPDTL "
						+ " INNER JOIN TMS_APPLICATION ON (TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " WHERE TMS_APPLICATION.APPL_STATUS IN ('A','C','F') AND APPL_EMP_ADVANCE_AMT > 0 AND TMS_APP_EMPDTL.APPL_ID="
						+ applicationCode;
				Object[][] advCountObj = getSqlModel().getSingleResult(
						advCountQuery);
				
				String accountatnt="";
				
				if (advCountObj != null && advCountObj.length > 0) {
					for (int i = 0; i < advCountObj.length; i++) {
						String authId = "";
						String authQuery = "  SELECT  AUTH_ID, AUTH_ACCOUNTENT, AUTH_ACCOUNTANT_EMAIL_ID   FROM TMS_MANG_AUTH_HDR "
								+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
								+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
								+ advCountObj[i][2] + ")";// branch Id
						Object[][] authObj = model.getSqlModel()
								.getSingleResult(authQuery);
						logger.info("employees with advance======"
								+ advCountObj[i][2]);
						if (authObj != null && authObj.length > 0) {
							
							accountatnt =String.valueOf(authObj[0][1]);
							
							authId = String.valueOf(authObj[0][0]);
							logger.info("0 1======"
									+ String.valueOf(authObj[0][1]));
						}
						authQuery = "  SELECT  AUTH_ID, AUTH_ACCOUNTENT, AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
								+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
						// Id
						Object[][] new_authObj = model.getSqlModel()
								.getSingleResult(authQuery);
						if (new_authObj != null && new_authObj.length > 0) {
							authObj = new_authObj;
							
							accountatnt =String.valueOf(authObj[0][1]);
							
							authId = String.valueOf(new_authObj[0][0]);
							logger.info("0 1 1 1======"
									+ String.valueOf(authObj[0][1]));
							logger.info("Accountant email id======"
									+ String.valueOf(authObj[0][2]));
						}
						logger.info("Authorisation id======" + authId);

						EmailTemplateBody accountantTemplate = new EmailTemplateBody();
						// Initiate template
						accountantTemplate.initiate(context, session);

						// Set respective template name
						accountantTemplate
								.setEmailTemplate("TRAVEL ADVANCE APPL MAIL FROM SYSTEM TO ACCOUNTANT");

						// call compulsory for set the queries of template
						accountantTemplate.getTemplateQueries();

						try {
							// get the query as per number
							EmailTemplateQuery templateQuery1 = accountantTemplate
									.getTemplateQuery(1);// FROM EMAIL
							// NO PARAMETERES TO BE SET FOR SYSMAIL
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							// And so on
							EmailTemplateQuery templateQuery2 = accountantTemplate
									.getTemplateQuery(2);// To EMAIL
							templateQuery2.setParameter(1, authId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery3 = accountantTemplate
									.getTemplateQuery(3);
							templateQuery3.setParameter(1, String
									.valueOf(advCountObj[i][0]));// APPL ID
							templateQuery3.setParameter(2, String
									.valueOf(advCountObj[i][2]));// EMP CODE
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							EmailTemplateQuery templateQuery4 = accountantTemplate
									.getTemplateQuery(4);
							templateQuery4.setParameter(1, applicationCode);// APPL
							// ID
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery5 = accountantTemplate
									.getTemplateQuery(5);
							templateQuery5.setParameter(1, String
									.valueOf(advCountObj[i][0]));// APPL ID
							templateQuery5.setParameter(2, String
									.valueOf(advCountObj[i][4]));// LEVEL
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery6 = accountantTemplate
									.getTemplateQuery(6);
							templateQuery6.setParameter(1, String
									.valueOf(advCountObj[i][0]));// APPL ID
							templateQuery6.setParameter(2, String
									.valueOf(advCountObj[i][4]));// LEVEL
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery7 = accountantTemplate
									.getTemplateQuery(7);
							templateQuery7.setParameter(1, adminId);// MAIN
							// SCHEDULER
							// ID
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							EmailTemplateQuery templateQuery8 = accountantTemplate
									.getTemplateQuery(8);
							templateQuery8.setParameter(1, String
									.valueOf(advCountObj[i][0]));// APPL ID
							templateQuery8.setParameter(2, String
									.valueOf(advCountObj[i][2]));// EMP CODE
						} catch (Exception e) {
							e.printStackTrace();
						}

						// configure the actual contents of the template
						accountantTemplate.configMailAlert();
						// Add approval link -pass parameters to the link
						
						String alertLink="";
						String alertParameter ="";
						
						try {
							System.out.println("before advance alert logic");
							alertLink = "/TMS/AdvClmDisbursement_calforedit.action";
							alertParameter = "hiddencode="
									+ String.valueOf(advCountObj[i][2])
									+ "&hiddenapplId="
									+ String.valueOf(advCountObj[i][0])
									+ "&hiddenapplCode="
									+ String.valueOf(advCountObj[i][1])
									+ "&hiddengustflag=S"
									+ "&hidApplStatus="
									+ String.valueOf(advCountObj[i][5]);
							accountantTemplate.sendProcessManagerAlert(
									accountatnt, "Travel", "A",
									applicationCode, level, alertParameter,
									alertLink, "Approved", applicantID, "", "",
									"", approverId);
							System.out.println("after advance alert logic");
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							String[] acc_link_parameter = new String[1];
							String[] acc_link_labels = new String[1];
							String acc_applicationType = "TravelAppl";
							acc_link_parameter[0] = acc_applicationType + "#"
									+ String.valueOf(authObj[0][1])
									+ "#applicationDtls#";
							String accountant_link = "/TMS/AdvClmDisbursement_calforedit.action?hiddencode="
									+ String.valueOf(advCountObj[i][2])
									+ "$hiddenapplId="
									+ String.valueOf(advCountObj[i][0])
									+ "$hiddenapplCode="
									+ String.valueOf(advCountObj[i][1])
									+ "$hiddengustflag=S"
									+ "$hidApplStatus="
									+ String.valueOf(advCountObj[i][5]);
							// link= PPEncrypter.encrypt(link);
							System.out.println("applicationDtls  ..."
									+ accountant_link);
							acc_link_parameter[0] += accountant_link;
							acc_link_labels[0] = "Advance Disbursement";
							accountantTemplate.addOnlineLink(request,
									acc_link_parameter, acc_link_labels);
						} catch (Exception e) {
							logger
									.error("Error in Advance disb mail link:"
											+ e);
							e.printStackTrace();
						}
						// call for sending the email
						accountantTemplate.sendApplicationMail();

						accountantTemplate.clearParameters();

						// terminate template
						accountantTemplate.terminate();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean setApproverComments(TmsTrvlApproval trvlApprvl) {

		boolean result = false;
		try {
			String approverCommentQuery = " SELECT EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' ')	,to_CHAR(APPR_DTL_APPDATE,'DD-MM-YYYY') "
					+ "	,DECODE(TRIM(APPR_STATUS),'P','Pending for approval','C','Booking Completed','A','Approved','N','Draft','B','Sent Back','F','Revoked') AS STATUS "
					+ "	,nvl(APPR_DTL_COMMENTS,'') "
					+ "	from TMS_APP_APPROVAL_DTL "
					+ "	 INNER JOIN HRMS_EMP_OFFC ON (TMS_APP_APPROVAL_DTL.APPR_APPROVER_ID= HRMS_EMP_OFFC.EMP_ID) "
					+ "	  WHERE APPL_ID="
					+ trvlApprvl.getHiddenApplicationCode().trim();

			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList arrList = new ArrayList();
				for (int i = 0; i < approverCommentObj.length; i++) {
					TravelApplication bean = new TravelApplication();
					bean.setPrevApproverID(checkNull(String
							.valueOf(approverCommentObj[i][0])));
					bean.setPrevApproverName(checkNull(String
							.valueOf(approverCommentObj[i][1])));
					bean.setPrevApproverDate(checkNull(String
							.valueOf(approverCommentObj[i][2])));
					bean.setPrevApproverStatus(checkNull(String
							.valueOf(approverCommentObj[i][3])));
					bean.setPrevApproverComment(checkNull(String
							.valueOf(approverCommentObj[i][4])));
					/*
					 * String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
					 * leaveBean.setAppSrNo(srNo);
					 */
					arrList.add(bean);

					result = true;
				}
				trvlApprvl.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	public void getApplicationDetailsByApplicationId(
			TmsTrvlApproval trvlApprvl, HttpServletRequest request) {

		try {
			logger.info("############## APPLICATION ID ##################"
					+ trvlApprvl.getHiddenApplicationCode());
			String employeeInformationQuery = " SELECT NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,'')AS APPLICANT,NVL(APPL_EMP_AGE,0) AS AGE , "
					+ " NVL(APPL_EMP_CONTACT,''),NVL(APPL_EMP_ADVANCE_AMT,0), APPL_EMP_CODE,'S',EMP_CADRE, NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY'),' ') AS DOB "
					+ " , NVL(APPL_APPROVED_ADVANCE_AMOUNT,0), TO_CHAR(NVL(CADRE_NAME,'')), TO_CHAR(NVL(APPL_CURRENCY,''))"
					+ " FROM TMS_APP_EMPDTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(TMS_APP_EMPDTL.APPL_EMP_CODE = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P') "
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID =HRMS_EMP_OFFC.EMP_CADRE)"
					+ " WHERE APPL_ID= "
					+ trvlApprvl.getHiddenApplicationCode()
					+ " UNION ALL  "
					+ " SELECT NVL(GUEST_NAME,'') AS APPLICANT, NVL(GUEST_AGE,0) AS AGE, "
					+ " NVL(GUEST_CONTACT,''),NVL(GUEST_ADVANCE_AMT,0),0 ,'G',0, '',0, '', ''"
					+ " FROM TMS_APP_GUEST_DTL WHERE APPL_ID= "
					+ trvlApprvl.getHiddenApplicationCode();
			String employeeDetailsQuery = " SELECT TRIM(TRIM(NVL(EMP_FNAME,' '))||' '|| TRIM(NVL(EMP_MNAME,' '))||' '||TRIM(NVL(EMP_LNAME,' '))) AS INITIATOR_NAME "
					+ " ,TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPLICATIONDATE , DECODE(TRIM(APPL_STATUS),'P','Pending for approval','C','Booking Completed','A','Approved','N','Draft','B','Sent Back','F','Revoked') AS STATUS , "
					+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS TRAVELSTARTDATE, TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS  TRAVELENDDATE "
					+ " ,TOUR_TRAVEL_REQ_NAME AS REQNAME , NVL(PURPOSE_NAME,''),TOUR_TRAVEL_PURPOSE,NVL(PROJECT_NAME,''),TOUR_PROJECT "
					+ " ,NVL(TOUR_OTHER_PROJECT	,''),TOUR_CUSTOMER,NVL(TRAVEL_CUST_NAME,''),NVL(TOUR_OTHER_CUSTOMER,''),TOUR_TRAVEL_TYPE,NVL(LOCATION_TYPE_NAME,''),NVL(APPL_APPLCANT_COMMENTS,''), "
					+ " APPL_MANAGE_JOURNEY,APPL_MANAGE_ACCOMODATION, APPL_MANAGE_LOCAL_CONV  "
					+ " ,NVL(EMP_TOKEN,' '),EMP_ID,APPL_APPLCANT_COMMENTS ,APPL_VIOLATION_REASON, APPL_ISPOLICYVIOLATED, APPL_VIOLATION_DETAILS, EMP_CADRE, TO_CHAR(NVL(CADRE_NAME,'')) FROM TMS_APPLICATION "
					+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
					+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID =TMS_APPLICATION.TOUR_TRAVEL_PURPOSE) "
					+ " LEFT JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID =TMS_APPLICATION.TOUR_PROJECT ) "
					+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_APPLICATION.TOUR_CUSTOMER) "
					+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID = TMS_APPLICATION.TOUR_TRAVEL_TYPE) "
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID =HRMS_EMP_OFFC.EMP_CADRE)"
					+ " WHERE APPL_ID= "
					+ trvlApprvl.getHiddenApplicationCode();
			String journeyDetailsQuery = " SELECT  JOURNEY_FROM, JOURNEY_TO,NVL(JOURNEY_NAME||'-'||CLASS_NAME,' '), TO_CHAR(JOURNEY_DATE,'DD-MM-YYYY'), JOURNEY_TIME, JOURNEY_MODECLASS,JOURNEY_CODE "
					+ " FROM TMS_APP_JOURNEY_DTL "
					+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON (HRMS_TMS_JOURNEY_CLASS.CLASS_ID=TMS_APP_JOURNEY_DTL.JOURNEY_MODECLASS)"
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON (HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
					+ " WHERE APPL_ID= "
					+ trvlApprvl.getHiddenApplicationCode();
			String lodgingDetailsQuery = " SELECT  LODGE_CODE, HOTEL_TYPE_NAME,ROOM_TYPE_NAME, LODGE_CITY, LODGE_PRE_LOCATION, TO_CHAR(LODGE_FROMDATE,'DD-MM-YYYY'), TO_CHAR(LODGE_TODATE,'DD-MM-YYYY') , LODGE_FROMTIME, LODGE_TOTIME "
					+ " ,LODGE_HOTELTYPE, LODGE_ROOMTYPE "
					+ " FROM TMS_APP_LODGE_DTL "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON (HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID =TMS_APP_LODGE_DTL.LODGE_HOTELTYPE ) "
					+ " INNER JOIN HRMS_TMS_ROOM_TYPE ON (HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID =TMS_APP_LODGE_DTL.LODGE_ROOMTYPE ) "
					+ " WHERE APPL_ID= "
					+ trvlApprvl.getHiddenApplicationCode();
			String localConveyanceDetailsQuery = " SELECT CONV_CODE, CONV_CITY, CONV_TRAVELDTL, CONV_MEDIUM, TO_CHAR(CONV_FROMDATE,'DD-MM-YYYY'), TO_CHAR(CONV_TODATE,'DD-MM-YYYY'), CONV_FROMTIME, CONV_TOTIME "
					+ " FROM TMS_APP_CONV_DTL WHERE APPL_ID= "
					+ trvlApprvl.getHiddenApplicationCode();
			Object employeeInformationDataObj[][] = getSqlModel()
					.getSingleResult(employeeInformationQuery);
			Object employeeDetailsDataObj[][] = getSqlModel().getSingleResult(
					employeeDetailsQuery);
			Object journeyDataObj[][] = getSqlModel().getSingleResult(
					journeyDetailsQuery);
			Object lodgingDetailsDataObj[][] = getSqlModel().getSingleResult(
					lodgingDetailsQuery);
			Object localConveyanceDetailsDataObj[][] = getSqlModel()
					.getSingleResult(localConveyanceDetailsQuery);
			if (employeeInformationDataObj != null
					&& employeeInformationDataObj.length > 0) {

				ArrayList empTravellerList = new ArrayList();

				for (int i = 0; i < employeeInformationDataObj.length; i++) {
					TmsTrvlApproval travellerBean = new TmsTrvlApproval();

					travellerBean.setEmployeeNameFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][0])));// emp_name
					travellerBean.setEmployeeAgeFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][1])));// emp_age
					travellerBean.setEmployeeContactFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][2])));// emp_contact
					travellerBean.setEmployeeAdvanceFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][3])));// advance_amount
					travellerBean
							.setEmployeeTravellerIdFromList(checkNull(String
									.valueOf(employeeInformationDataObj[i][4])));// traveller_ID
					travellerBean.setEmployeeTypeFromList(checkNull(String
							.valueOf(employeeInformationDataObj[i][5])));// traveller
					travellerBean.setTravellerGradeId(checkNull(String
							.valueOf(employeeInformationDataObj[i][6])));// S_O_G
					travellerBean
							.setEmployeeDateOfBirthFromList(checkNull(String
									.valueOf(employeeInformationDataObj[i][7])));// DOB
					travellerBean.setApprovedAdvanceAmount(checkNull(String
							.valueOf(employeeInformationDataObj[i][8])));
					travellerBean.setEmployeeBandFromList(String
							.valueOf(employeeInformationDataObj[i][9]));
					travellerBean.setCurrencyEmployeeAdvance(String.valueOf(employeeInformationDataObj[i][10]));
					empTravellerList.add(travellerBean);
				}
				trvlApprvl.setTravellerList(empTravellerList);
			}
			if (employeeDetailsDataObj != null
					&& employeeDetailsDataObj.length > 0) {

				trvlApprvl.setInitName(checkNull(String
						.valueOf(employeeDetailsDataObj[0][0])));// initiator
				// name
				trvlApprvl.setAppDate(checkNull(String
						.valueOf(employeeDetailsDataObj[0][1])));// application
				// date
				trvlApprvl.setAppStatus(checkNull(String
						.valueOf(employeeDetailsDataObj[0][2])));// application
				// status
				trvlApprvl.setStartDate(checkNull(String
						.valueOf(employeeDetailsDataObj[0][3])));// travel
				// start
				// date
				trvlApprvl.setEndDate(checkNull(String
						.valueOf(employeeDetailsDataObj[0][4])));// travel
				// end
				// date
				trvlApprvl.setTrvlReqName(checkNull(String
						.valueOf(employeeDetailsDataObj[0][5])));// travel
				// request
				// name
				trvlApprvl.setPurpose(checkNull(String
						.valueOf(employeeDetailsDataObj[0][6])));// travel
				// purpose
				// name
				trvlApprvl.setPurposeId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][7])));// travel
				// purpose
				// id
				trvlApprvl.setProject(checkNull(String
						.valueOf(employeeDetailsDataObj[0][8])));// travel
				// project
				// name
				trvlApprvl.setProjectId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][9])));// tour
				// project
				trvlApprvl.setOtherProject(checkNull(String
						.valueOf(employeeDetailsDataObj[0][10])));// tour
				// other
				// project
				trvlApprvl.setCustomerId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][11])));// tour
				// customer
				// id
				trvlApprvl.setCustomerName(checkNull(String
						.valueOf(employeeDetailsDataObj[0][12])));// tour
				// customer
				// name
				trvlApprvl.setOtherCustomerName(checkNull(String
						.valueOf(employeeDetailsDataObj[0][13])));// tour
				// other
				// project
				trvlApprvl.setTrvlTypeId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][14])));// travel
				// type
				// id
				trvlApprvl.setTrvlType(checkNull(String
						.valueOf(employeeDetailsDataObj[0][15])));// travel
				// type
				// name
				trvlApprvl.setApplicantComments(checkNull(String
						.valueOf(employeeDetailsDataObj[0][16])));// travel
				// type
				// id
				trvlApprvl.setJourneyRadio(String
						.valueOf(employeeDetailsDataObj[0][17]));// journey
				// radio
				trvlApprvl.setAccomodationRadio(String
						.valueOf(employeeDetailsDataObj[0][18]));// accomodation
				// radio
				trvlApprvl.setLocalConvRadio(String
						.valueOf(employeeDetailsDataObj[0][19]));// local
				// conv
				// radio

				trvlApprvl.setInitToken(checkNull(String
						.valueOf(employeeDetailsDataObj[0][20])));

				trvlApprvl.setInitId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][21])));

				trvlApprvl.setApplComm(checkNull(String
						.valueOf(employeeDetailsDataObj[0][22])));
				trvlApprvl.setViolationReason(checkNull(String
						.valueOf(employeeDetailsDataObj[0][23])));// violation
				// reason
				trvlApprvl.setViolationStatus(checkNull(String
						.valueOf(employeeDetailsDataObj[0][24])));// violation
				// status Y
				// or N
				trvlApprvl.setPolicyViolationMsg(checkNull(String
						.valueOf(employeeDetailsDataObj[0][25])));// violation
				// msg
				trvlApprvl.setInitiatorGradeId(checkNull(String
						.valueOf(employeeDetailsDataObj[0][26])));// initiator_grade_Id
				trvlApprvl.setEmpBand(checkNull(String
						.valueOf(employeeDetailsDataObj[0][27])));// initiator_grade_Name
			}
			if (journeyDataObj != null && journeyDataObj.length > 0) {

				ArrayList empJourneyList = new ArrayList();

				for (int i = 0; i < journeyDataObj.length; i++) {

					TravelApplication journeyBean = new TravelApplication();

					journeyBean.setJourneyFromPlace(checkNull(String
							.valueOf(journeyDataObj[i][0])));// journey from
					journeyBean.setJourneyToPlace(checkNull(String
							.valueOf(journeyDataObj[i][1])));// journey to

					journeyBean.setJourneyMode(checkNull(String
							.valueOf(journeyDataObj[i][2])));// journey
					// date

					journeyBean.setJourneyDate(checkNull(String
							.valueOf(journeyDataObj[i][3])));// journey
					// date
					journeyBean.setJourneyTime(checkNull(String
							.valueOf(journeyDataObj[i][4])));// journey
					// time
					journeyBean.setJourneyModeId(checkNull(String
							.valueOf(journeyDataObj[i][5])));// journeyModeId

					journeyBean.setJourCode(checkNull(String
							.valueOf(journeyDataObj[i][6])));// journey
					// code
					empJourneyList.add(journeyBean);
				}
				trvlApprvl.setJourneyList(empJourneyList);
			}
			if (lodgingDetailsDataObj != null
					&& lodgingDetailsDataObj.length > 0) {

				ArrayList empAccomodationList = new ArrayList();
				for (int i = 0; i < lodgingDetailsDataObj.length; i++) {

					TravelApplication accomodationBean = new TravelApplication();

					accomodationBean.setAccomodationId(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][0])));// accomodation
					// Id
					accomodationBean.setAccomodationHotelType(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][1])));// hotel
					// type
					// name
					accomodationBean.setAccomodationRoomType(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][2])));// room type
					// name
					accomodationBean.setAccomodationCity(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][3])));// city
					accomodationBean
							.setAccomodationPrefLocation(checkNull(String
									.valueOf(lodgingDetailsDataObj[i][4])));// preferred
					// loc
					accomodationBean.setAccomodationFromDate(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][5])));// from date
					accomodationBean.setAccomodationToDate(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][6])));// to date
					accomodationBean.setAccomodationFromTime(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][7])));// from time
					accomodationBean.setAccomodationToTime(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][8])));// to time
					accomodationBean
							.setAccomodationHotelTypeId(checkNull(String
									.valueOf(lodgingDetailsDataObj[i][9])));// hotel
					// type id
					accomodationBean.setAccomodationRoomTypeId(checkNull(String
							.valueOf(lodgingDetailsDataObj[i][10])));// room
					// type
					// id
					empAccomodationList.add(accomodationBean);

				}
				trvlApprvl.setAccomodationList(empAccomodationList);
			}
			if (localConveyanceDetailsDataObj != null
					&& localConveyanceDetailsDataObj.length > 0) {

				ArrayList empLocalConveyanceList = new ArrayList();

				for (int i = 0; i < localConveyanceDetailsDataObj.length; i++) {

					TravelApplication localConveyanceBean = new TravelApplication();
					localConveyanceBean.setLocalConveyanceCode(checkNull(String
							.valueOf(localConveyanceDetailsDataObj[i][0])));// conveyance
					// code
					localConveyanceBean.setLocalConveyanceCity(checkNull(String
							.valueOf(localConveyanceDetailsDataObj[i][1])));// conveyance
					// city
					localConveyanceBean
							.setLocalConveyanceTravelDetail(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][2])));// travel
					// detail
					localConveyanceBean
							.setLocalConveyanceTravelMedium(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][3])));// travel
					// medium
					localConveyanceBean
							.setLocalConveyanceFromDate(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][4])));// conveyance
					// from
					// date
					localConveyanceBean
							.setLocalConveyanceToDate(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][5])));// conveyance
					// to
					// date
					localConveyanceBean
							.setLocalConveyanceFromTime(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][6])));// conveyance
					// from
					// time
					localConveyanceBean
							.setLocalConveyanceToTime(checkNull(String
									.valueOf(localConveyanceDetailsDataObj[i][7])));// conveyance
					// to
					// time
					empLocalConveyanceList.add(localConveyanceBean);

				}
				trvlApprvl.setLocalConveyanceList(empLocalConveyanceList);
			}
			getKeepInformedSavedRecord(trvlApprvl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getKeepInformedSavedRecord(TmsTrvlApproval trvlApprvl) {

		try {

			String selectQuery = " SELECT NVL(APPL_KEEP_INFO,'') FROM TMS_APPLICATION WHERE APPL_ID="
					+ trvlApprvl.getHiddenApplicationCode();

			Object selectDataObj[][] = getSqlModel().getSingleResult(
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
				Object result[][] = getSqlModel().getSingleResult(query);

				ArrayList list = new ArrayList();
				if (result != null && result.length > 0) {

					for (int i = 0; i < result.length; i++) {
						TravelApplication bean = new TravelApplication();
						bean.setKeepInformToCode(String.valueOf(result[i][1]));
						bean.setKeepInformToName(String.valueOf(result[i][0]));
						list.add(bean);
					}
					trvlApprvl.setKeepInformedList(list);
					trvlApprvl.setKeepInformedFlag(true);
				} else {
					logger
							.info("NO KEEP INFORMED TO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					trvlApprvl.setKeepInformedFlag(false);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getKeepInformedSavedRecord----------"
					+ e);
		}
	}

	public void updateApprovedAdvanceAmount(String applicationID,
			HttpServletRequest request) {
		try {

			String[] empID = request
					.getParameterValues("employeeTravellerIdFromList");
			String[] approvedAdvanceAmount = request
					.getParameterValues("approvedAdvanceAmount");

			if (empID != null && empID.length > 0) {
				int count = 0;
				for (String i : empID) {
					String updateQuery = "  UPDATE TMS_APP_EMPDTL SET APPL_APPROVED_ADVANCE_AMOUNT="
							+ approvedAdvanceAmount[count]
							+ " WHERE APPL_ID= "
							+ applicationID
							+ " "
							+ " AND APPL_EMP_CODE= "
							+ empID[count];

					getSqlModel().singleExecute(updateQuery);

					count++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
