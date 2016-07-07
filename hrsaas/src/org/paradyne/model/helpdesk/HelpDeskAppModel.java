package org.paradyne.model.helpdesk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.helpdesk.HelpDeskApproval;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

/**
 * @author Reeba_Joseph
 * @date HelpDeskAppModel class to write business logic to change the status of
 *       the application from pending to approved or rejected and also to
 *       forward the application to the next approver
 */

public class HelpDeskAppModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HelpDeskAppModel.class);

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals(" ")
				|| result.equals("")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	String approved = "false";

	public void getPendingRequests(HelpDeskApproval helpBean,
			HttpServletRequest request) {
		try {

			String listQuery = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " TO_CHAR(HELPDESK_REQUEST_HDR.REQUEST_DATE,'DD-MM-YYYY'),HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR, "
					+ " NVL(HELPDESK_REQUEST_HDR.REQUEST_TOKEN,HELPDESK_REQUEST_HDR.REQUEST_ID),HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS, "
					+ " NVL(STATUS_ISLAST,' '), STATUS_ORDER, NVL(STATUS_CATAGORY_NAME,' '), "
					+ " DECODE(REQUEST_MGR_STATUS,'N','Not Applicable','P','Pending for manager approval','A','Approved by manager',"
					+ " 'R','Rejected by manager','B','Sent back by manager'), "
					+ " REQUEST_SUBJECT,HELPDESK_DEPT.DEPT_NAME,HELPDESK_REQUEST_HDR.REQUEST_ID, NVL(HELPDESK_REQUEST_HDR.REQUEST_MGR_STATUS,'N') "
					+ " FROM HELPDESK_REQUEST_HDR "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY) "
					+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV = HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS) "
					+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
					+ " WHERE REQUEST_MGR_STATUS = 'P' AND REQUEST_PENDING_WITH= "
					+ helpBean.getUserEmpId() + " ORDER BY REQUEST_DATE DESC";

			Object pendingData[][] = getSqlModel().getSingleResult(listQuery);

			String[] pageIndexPending = Utility.doPaging(helpBean.getMyPage(),
					pendingData.length, 20);
			if (pageIndexPending == null) {
				pageIndexPending[0] = "0";
				pageIndexPending[1] = "20";
				pageIndexPending[2] = "1";
				pageIndexPending[3] = "1";
				pageIndexPending[4] = "";
			}// END IF

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexPending[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexPending[3])));
			if (pageIndexPending[4].equals("1"))
				helpBean.setMyPage("1");

			if (pendingData != null && pendingData.length > 0) {
				helpBean.setPendingLength("true");
				ArrayList<Object> pendingList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexPending[0]); i < Integer
						.parseInt(pageIndexPending[1]); i++) {
					HelpDeskApproval pendingBean = new HelpDeskApproval();
					pendingBean.setReqEmpToken(String
							.valueOf(pendingData[i][0]));
					pendingBean
							.setReqEmpName(String.valueOf(pendingData[i][1]));
					pendingBean.setReqDate(String.valueOf(pendingData[i][2]));
					pendingBean.setPendingEmpId(String
							.valueOf(pendingData[i][3]));
					pendingBean.setPendingReqNo(String
							.valueOf(pendingData[i][4]));
					pendingBean.setReqStatus(String.valueOf(pendingData[i][5]));
					pendingBean.setReqStatusIsLast(String
							.valueOf(pendingData[i][6]));
					pendingBean.setReqStatusOrder(String
							.valueOf(pendingData[i][7]));

					if (String.valueOf(pendingData[i][9]).equals(" ")) {
						pendingBean.setReqStatusName(String
								.valueOf(pendingData[i][8]));
					} else {
						pendingBean.setReqStatusName(String
								.valueOf(pendingData[i][9]));
					}
					if (String.valueOf(pendingData[i][10]).length() > 15) {
						pendingBean.setReqSubject(String.valueOf(
								pendingData[i][10]).substring(0, 15)
								+ "...");
					} else
						pendingBean.setReqSubject(String
								.valueOf(pendingData[i][10]));
					pendingBean.setPendingReqDept(String
							.valueOf(pendingData[i][11]));
					pendingBean.setPendingReqCode(String
							.valueOf(pendingData[i][12]));
					pendingBean.setReqMgrStatus(String
							.valueOf(pendingData[i][13]));

					pendingList.add(pendingBean);
				}// END FOR LOOP
				helpBean.setPendingList(pendingList);
			}// END IF DATA NOT NULL
		} catch (Exception e) {
			e.printStackTrace();
		}// END TRY CATCH BLOCK

	}

	public HelpDeskApproval getEmployeeDetails(String reqAppCode,
			HelpDeskApproval bean, HttpServletRequest request) {
		Object[] requestId = new Object[1];
		requestId[0] = reqAppCode;
		Object[][] empDtail = getSqlModel().getSingleResult(getQuery(4),
				requestId);
		if (empDtail != null && empDtail.length > 0) {
			bean.setHelpcode(reqAppCode);

			bean.setReqToken(String.valueOf(empDtail[0][0]));// req token
			bean.setReqDate(String.valueOf(empDtail[0][1]));// req date
			bean.setEmpId(String.valueOf(empDtail[0][3]));// posted by Id
			bean.setEmpToken(String.valueOf(empDtail[0][4]));// posted by
			// token
			bean.setEmpName(String.valueOf(empDtail[0][5]));// posted by name
			bean.setBranchCode(String.valueOf(empDtail[0][6]));// branch code
			bean.setBranchName(String.valueOf(empDtail[0][7]));// branch name
			bean.setDeptId(String.valueOf(empDtail[0][8]));// dept Id
			bean.setDeptName(String.valueOf(empDtail[0][9]));// dept name
			bean.setDesgCode(String.valueOf(empDtail[0][10]));// designation
			// Id
			bean.setDesgName(String.valueOf(empDtail[0][11]));// designation
			// name

			if (String.valueOf(empDtail[0][2]).equals("S")) {
				bean.setRequestForEmpId(String.valueOf(empDtail[0][12]));// req
				// appl
				// for
				bean.setInitiatorId(String.valueOf(empDtail[0][12]));// initiator
				// Id
				bean.setInitiatorToken(String.valueOf(empDtail[0][13]));// initiator
				// token
				bean.setInitiatorName(String.valueOf(empDtail[0][14]));// initiator
				// name
			} else if (String.valueOf(empDtail[0][2]).equals("O")) {
				bean.setRequestForEmpId(String.valueOf(empDtail[0][12]));// req
				// appl
				// for
				bean.setOtherEmpId(String.valueOf(empDtail[0][12]));// other emp
				// Id
				bean.setOtherEmpToken(String.valueOf(empDtail[0][13]));// other
				// emp
				// token
				bean.setOtherEmpName(String.valueOf(empDtail[0][14]));// other
				// emp
				// name
			} else {
				bean.setClientName(String.valueOf(empDtail[0][15]));// client
				// name
			}
			request.setAttribute("radioStatus", String.valueOf(empDtail[0][2]));
			bean.setReqDeptCode(String.valueOf(empDtail[0][16]));// req dept
			bean.setReqDeptName(String.valueOf(empDtail[0][17]));// req dept
			// name
			bean.setReqTypeCode(String.valueOf(empDtail[0][18]));// req type
			// code
			bean.setReqType(String.valueOf(empDtail[0][19]));// req type code
			bean.setSubReqTypeCode(String.valueOf(empDtail[0][20]));// req sub
			// type
			bean.setSubReqType(String.valueOf(empDtail[0][21]));// req sub type
			bean.setSubject(String.valueOf(empDtail[0][22]));// subject
			bean.setComments(String.valueOf(empDtail[0][23]));// req desc

			if (!String.valueOf(empDtail[0][24]).trim().equals("")) {
				ArrayList<HelpDeskApproval> proofList = new ArrayList<HelpDeskApproval>();
				String[] proofNames = String.valueOf(empDtail[0][24])
						.split(",");
				for (int i = 0; i < proofNames.length; i++) {
					HelpDeskApproval bean1 = new HelpDeskApproval();
					bean1.setProofName(proofNames[i]);
					bean1.setProofSrNo("" + (i + 1));// sr no
					proofList.add(bean1);
				}
				bean.setProofList(proofList);
				bean.setProofListFlag(true);
			}
			bean.setPendingEmpId(String.valueOf(empDtail[0][25]));// pending
			// emp Id
			bean.setPendingEmpToken(String.valueOf(empDtail[0][26]));// pending
			// emp
			// token
			bean.setPendingEmpName(String.valueOf(empDtail[0][27]));// pending
			// emp name
			bean.setReqManagerStatus(String.valueOf(empDtail[0][28]));// manager
			// status
			bean.setReqEmpStatus(String.valueOf(empDtail[0][29]));// emp
			// status
			bean.setHiddenStatus(String.valueOf(empDtail[0][30]));// request
			// status
			bean.setDecodedReqStatus(String.valueOf(empDtail[0][31]));// request
			// status
			bean.setAssetTypeCode(String.valueOf(empDtail[0][32]));// asset
			// type code
			bean.setAssetType(String.valueOf(empDtail[0][33]));// asset type
			bean.setAssetSubTypeCode(String.valueOf(empDtail[0][34]));// asset
			// sub
			// type
			// code
			bean.setAssetSubType(String.valueOf(empDtail[0][35]));// asset sub
			// type
			bean.setAssetQuantity(String.valueOf(empDtail[0][36]));// asset
			// quantity
			bean.setRequestId(String.valueOf(empDtail[0][37]));// request Id
			bean.setReqEmpContactNo(String.valueOf(empDtail[0][38]));
			bean.setReqEmpExtensionNo(String.valueOf(empDtail[0][39]));
			bean.setReqEmpEmailId(String.valueOf(empDtail[0][40]));
		}

		String mgrAppr = "SELECT IS_MANAGER_APPROVAL FROM HELPDESK_REQUEST_SUBTYPE WHERE REQUEST_SUBTYPE_ID = "
				+ bean.getSubReqTypeCode();
		Object[][] mgrApprObj = getSqlModel().getSingleResult(mgrAppr);
		if (mgrApprObj != null && mgrApprObj.length > 0) {
			bean.setIsManagerApproval(String.valueOf(mgrApprObj[0][0]));
		}

		return bean;

	}

	public boolean getPreviousApproverComments(HelpDeskApproval helpBean) {
		boolean result = false;
		try {
			String approverCommentQuery = " ";

			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList arrList = new ArrayList();
				for (int i = 0; i < approverCommentObj.length; i++) {
					HelpDeskApproval bean = new HelpDeskApproval();
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
					arrList.add(bean);

					result = true;
				}
				helpBean.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	public String approveRejectApplication(HttpServletRequest request,
			String appStatus, int level, String initiatorId,String requestForEmpId,
			String isManagerAppr, String deptId, String reqTypeId,
			String requestId, String approverComments, String currentUser) {
		String applicationStatus = "pending";
		String forwardedTo = "";
		boolean result = false;
		Object[][] empFlow = null;
		try {
			level += 1;

			if (String.valueOf(appStatus).equals("A")) {
				if (isManagerAppr.equals("Y")) {
					empFlow = generateEmpFlow(initiatorId, "Help", (level));
					if (empFlow != null && empFlow.length > 0) {
						appStatus = "P";
					}
				}

				if (appStatus.equals("A")) {
					String ownerQuery = " SELECT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR "
							+ " WHERE DEPT_CODE = "
							+ deptId
							+ " AND REQ_TYPE_CODE = "
							+ reqTypeId
							+ " AND BRANCH_CODE = ( "
							+ " SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "
							+ initiatorId + ") ";
					empFlow = getSqlModel().getSingleResult(ownerQuery);
				}
				if (empFlow != null && empFlow.length > 0) {
					forwardedTo = String.valueOf(empFlow[0][0]);
				} else {
					forwardedTo = "0";
				}

				String updateApprove = " UPDATE HELPDESK_REQUEST_HDR SET REQUEST_MGR_STATUS = '"
						+ appStatus
						+ "' , REQUEST_PENDING_WITH= "
						+ forwardedTo
						+ " ,REQUEST_APPL_LEVEL="
						+ (level)
						+ " WHERE REQUEST_ID = " + requestId;

				result = getSqlModel().singleExecute(updateApprove);
				applicationStatus = "approved";
				// sendForwardNotificationMail(request, requestId, initiatorId,
				// forwardedTo, isManagerAppr, deptId, reqTypeId, currentUser,
				// level);

			} else if (String.valueOf(appStatus).equals("B")) {
				forwardedTo = initiatorId;
				level = 1;
				String updateSendBack = " UPDATE HELPDESK_REQUEST_HDR SET REQUEST_MGR_STATUS = '"
						+ appStatus
						+ "' , REQUEST_PENDING_WITH= "
						+ forwardedTo
						+ " , REQUEST_APPL_LEVEL="
						+ (level)
						+ " WHERE REQUEST_ID = " + requestId;

				result = getSqlModel().singleExecute(updateSendBack);
				applicationStatus = "sendback";
			} else {
				forwardedTo = initiatorId;

				String updateReject = " UPDATE HELPDESK_REQUEST_HDR SET REQUEST_MGR_STATUS = '"
						+ appStatus
						+ "' , REQUEST_PENDING_WITH= "
						+ forwardedTo
						+ ", REQUEST_APPL_STATUS='N' WHERE REQUEST_ID = "
						+ requestId;

				result = getSqlModel().singleExecute(updateReject);
				applicationStatus = "rejected";
			}
			if (result) {
				saveDetails(forwardedTo, appStatus, requestId, currentUser,
						approverComments);
				if (appStatus.equals("A") || appStatus.equals("P")) {
					sendForwardNotificationMail(request, requestId,
							initiatorId,requestForEmpId, forwardedTo, isManagerAppr, deptId,
							reqTypeId, currentUser, level);
					approved = "true";
				}
				sendForwardNotificationToApplicant(appStatus, requestId,
						initiatorId,requestForEmpId, isManagerAppr, deptId, reqTypeId,
						currentUser, approved);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applicationStatus;
	}

	public boolean saveDetails(String forwardedTo, String status,
			String requestId, String currentUser, String approverComments) {
		boolean result = false;
		Object[][] dtlObj = new Object[1][9];
		dtlObj[0][0] = requestId;// REQUEST_ID
		dtlObj[0][1] = currentUser;// REQUEST_ACTION_BY
		dtlObj[0][2] = forwardedTo;// REQUEST_FORWARDED_TO
		if (status.equals("R")) {
			dtlObj[0][3] = "N";// REQUEST_APPL_STATUS
		} else {
			dtlObj[0][3] = "O";// REQUEST_APPL_STATUS
		}
		dtlObj[0][4] = approverComments;// REQUEST_COMMENTS
		dtlObj[0][5] = status;// REQUEST_MGR_STATUS
		dtlObj[0][6] = "N";// REQUEST_EMP_STATUS

		String MadateQuery = "SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY HH24:MI')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID ="
			+ requestId;
		Object[][] MaxdateQueryObj = getSqlModel().getSingleResult(MadateQuery);
		
		if(MaxdateQueryObj!=null && MaxdateQueryObj.length >0){
			
			String diffDateTime ="SELECT (SYSDATE- TO_DATE('"+String.valueOf(MaxdateQueryObj[0][0])+"','DD-MM-YYYY HH24:MI'))*24 *60 FROM DUAL";
			Object[][] diffDateTimeObj = getSqlModel().getSingleResult(diffDateTime);
			
			if(diffDateTimeObj !=null && diffDateTimeObj.length >0){
				dtlObj[0][7] =String.valueOf(diffDateTimeObj[0][0]) ;
			}else{
				dtlObj[0][7] = 0.00;
			}
				
		}
		
		String dateQuery = "SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID ="
				+ requestId;
		Object[][] dateQueryObj = getSqlModel().getSingleResult(dateQuery);
		if (dateQueryObj != null && dateQueryObj[0][0] != null) {
						
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			  
			String query = " SELECT TO_DATE('" + sysDate
					+ "','dd-mm-yyyy')-TO_DATE('"
					+ String.valueOf(dateQueryObj[0][0])
					+ "','dd-mm-yyyy') from dual ";

			Object dateDiffQueryObj[][] = getSqlModel().getSingleResult(query);
			int looplength = 0;
			if (dateDiffQueryObj != null && dateDiffQueryObj.length > 0) {
				looplength = Integer.parseInt(String
						.valueOf(dateDiffQueryObj[0][0]));
			}
			String[][] splitObj = null;
			splitObj = new DateUtility().splitDatesObj(String
					.valueOf(dateQueryObj[0][0]), looplength + 1);
			int minutes = 0;
			for (int i = 0; i < splitObj.length; i++) {
				System.out.println("splitObj  " + splitObj[i][0]);
				if (IsWeekEnd(splitObj[i][0], currentUser)) {
					System.out.println("True sataraday");
					minutes = 0;
				}
				
				if (IsHoliday(splitObj[i][0], currentUser)) {
					System.out.println("True holiday");
					minutes = 0;
				}
				if(!IsWeekEnd(splitObj[i][0], currentUser) && !IsHoliday(splitObj[i][0], currentUser)) {
					if(!splitObj[i][0].equals(sysDate)){
						int workingHours=getWorkingHours(String.valueOf(splitObj[i][0]),currentUser,requestId);
					minutes +=workingHours;
				}else{
					minutes = getCurrentDayWorkingHour(currentUser,requestId);
				}
			}else{
				minutes=0;
			}

		}
			dtlObj[0][8] = minutes;
		}else {
			dtlObj[0][8] = 0;
		}
		boolean isshiftallowed=false;
		String shiftAllowedQuery="SELECT SFT_FLEXITIME_ALLOWED from HRMS_SHIFT where SHIFT_ID = "+
		 						" (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID ="+currentUser+") ";
		Object[][] shftAllowedObj = getSqlModel().getSingleResult(shiftAllowedQuery);
		if(shftAllowedObj!=null && shftAllowedObj.length>0){
			if(String.valueOf(shftAllowedObj[0][0]).equals("Y")){
			isshiftallowed=true;
			}	
		}
		if(isshiftallowed){
			dtlObj[0][8] = dtlObj[0][7];
		}
		/*
		 * String dateQuery = "SELECT TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY
		 * HH24:MI:SS') FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID ="
		 * +requestId;
		 * Object[][]dateQueryObj=getSqlModel().getSingleResult(dateQuery);
		 * if(dateQueryObj!=null && dateQueryObj[0][0] !=null){ String
		 * datetime="SELECT TO_NUMBER(TO_CHAR(TO_DATE('1','J')+(SYSDATE -
		 * TO_DATE('"+String.valueOf(dateQueryObj[0][0])+"','DD-MM-YYYY
		 * HH24:MI:SS')
		 * ),'J')-1)DAYS,TO_CHAR(TO_DATE('00:00:00','HH24:MI:SS')+(SYSDATE -
		 * TO_DATE('"+String.valueOf(dateQueryObj[0][0])+"','DD-MM-YYYY
		 * HH24:MI:SS')),'HH24:MI:SS') TIME"+ " FROM HELPDESK_ACTIVITY_LOG WHERE
		 * REQUEST_ID=" + requestId;
		 * Object[][]datetimeObj=getSqlModel().getSingleResult(datetime);
		 * if(datetimeObj!=null && datetimeObj.length>0){ String
		 * days=String.valueOf(datetimeObj[0][0]); String time=
		 * String.valueOf(datetimeObj[0][1]); String[] splitTime =
		 * time.split(":"); String hourdata=splitTime[0]; String minutedata =
		 * splitTime[1]; String seconddata =splitTime[2]; String
		 * finalDateTime=""; if(!days.equals("0")){ finalDateTime +=days+"Days"; }
		 * if(!hourdata.equals("00")){ finalDateTime +=hourdata+" Hours "; }
		 * 
		 * if(!minutedata.equals("00")){ finalDateTime +=minutedata+" Minutes "; }
		 * if(!seconddata.equals("00")){ finalDateTime +=seconddata+" Seconds "; }
		 * 
		 * dtlObj[0][7] = finalDateTime; } }else{ dtlObj[0][7] = "00.00"; }
		 */
		// INSERT INTO DTL TABLE
		 String insertDtl = " INSERT INTO HELPDESK_ACTIVITY_LOG (REQUEST_ID, REQUEST_ACTION_BY, REQUEST_FORWARDED_TO, "
				+ " REQUEST_STATUS, REQUEST_DATE, REQUEST_COMMENTS,REQUEST_MGR_STATUS, REQUEST_EMP_STATUS,ACTUAL_CALL_DURATION,REQUEST_CALL_DURATION) VALUES(?, ?, ?, ?, SYSDATE, ?,?,?,?,?)";
		result = getSqlModel().singleExecute(insertDtl, dtlObj);
		
		double totalMinutes =0;
		double totalActualduration=0;
		String updateReqSumQuery = "";
		Object[][] updateReqSumObj = null;
		String sumQuery = "SELECT SUM(REQUEST_CALL_DURATION),SUM(ACTUAL_CALL_DURATION) from HELPDESK_ACTIVITY_LOG where REQUEST_ID = "+requestId;
		Object[][] sumObj = getSqlModel().getSingleResult(sumQuery);
		if(sumObj!=null && sumObj.length >0){
			totalMinutes = Double.parseDouble(String.valueOf(sumObj[0][0]));
			totalActualduration = Double.parseDouble(String.valueOf(sumObj[0][1]));
		}
		updateReqSumQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_COMPLETION_TIME = ?,ACTUAL_COMPLITION_TIME=?,REQUEST_COMPLITION_DATE=SYSDATE WHERE REQUEST_ID=?";
		updateReqSumObj = new Object[1][3];
		
		
		updateReqSumObj[0][0] = totalMinutes;
		updateReqSumObj[0][1] = totalActualduration;
		updateReqSumObj[0][2] = requestId;
		
		result = getSqlModel().singleExecute(updateReqSumQuery,
				updateReqSumObj);

		return result;
	}

	public void getClosedList(HelpDeskApproval helpBean,
			HttpServletRequest request) {
		try {
			String listQuery = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " TO_CHAR(HELPDESK_REQUEST_HDR.REQUEST_DATE,'DD-MM-YYYY'),HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR, "
					+ " NVL(HELPDESK_REQUEST_HDR.REQUEST_TOKEN,HELPDESK_REQUEST_HDR.REQUEST_ID),HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS, "
					+ " NVL(STATUS_ISLAST,' '), STATUS_ORDER, NVL(STATUS_CATAGORY_NAME,' '), "
					+ " DECODE(HELPDESK_ACTIVITY_LOG.REQUEST_MGR_STATUS,'N','Not Applicable','P','Pending for manager approval','A','Approved by manager', "
					+ " 'R','Rejected by manager','B','Sent back by manager'), "
					+ " REQUEST_SUBJECT,HELPDESK_DEPT.DEPT_NAME,HELPDESK_REQUEST_HDR.REQUEST_ID, NVL(HELPDESK_REQUEST_HDR.REQUEST_MGR_STATUS,'N') "
					+ " FROM HELPDESK_ACTIVITY_LOG "
					+ " INNER JOIN HELPDESK_REQUEST_HDR ON (HELPDESK_REQUEST_HDR.REQUEST_ID = HELPDESK_ACTIVITY_LOG.REQUEST_ID) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID  = HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY) "
					+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV = HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS) "
					+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
					+ " WHERE HELPDESK_ACTIVITY_LOG.REQUEST_MGR_STATUS IN ('A','R') AND REQUEST_ACTION_BY = "
					+ helpBean.getUserEmpId()
					+ " ORDER BY HELPDESK_ACTIVITY_LOG.REQUEST_DATE DESC";

			Object closedData[][] = getSqlModel().getSingleResult(listQuery);

			String[] pageIndexClosed = Utility.doPaging(helpBean
					.getMyPageClosed(), closedData.length, 20);
			if (pageIndexClosed == null) {
				pageIndexClosed[0] = "0";
				pageIndexClosed[1] = "20";
				pageIndexClosed[2] = "1";
				pageIndexClosed[3] = "1";
				pageIndexClosed[4] = "";
			}// END IF

			request.setAttribute("totalPageClosed", Integer.parseInt(String
					.valueOf(pageIndexClosed[2])));
			request.setAttribute("PageNoClosed", Integer.parseInt(String
					.valueOf(pageIndexClosed[3])));
			if (pageIndexClosed[4].equals("1"))
				helpBean.setMyPageClosed("1");

			if (closedData != null && closedData.length > 0) {

				helpBean.setClosedLength("true");
				ArrayList<Object> closedList = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndexClosed[0]); i < Integer
						.parseInt(pageIndexClosed[1]); i++) {

					HelpDeskApproval closedBean = new HelpDeskApproval();
					closedBean.setReqEmpToken(String.valueOf(closedData[i][0]));
					closedBean.setReqEmpName(String.valueOf(closedData[i][1]));
					closedBean.setReqDate(String.valueOf(closedData[i][2]));
					closedBean.setClosedEmpId(String.valueOf(closedData[i][3]));
					closedBean.setClosedReqNo(String.valueOf(closedData[i][4]));
					closedBean.setReqStatus(String.valueOf(closedData[i][5]));
					closedBean.setReqStatusIsLast(String
							.valueOf(closedData[i][6]));
					closedBean.setReqStatusOrder(String
							.valueOf(closedData[i][7]));

					if (String.valueOf(closedData[i][9]).equals(" ")) {
						closedBean.setReqStatusName(String
								.valueOf(closedData[i][8]));
					} else {
						closedBean.setReqStatusName(String
								.valueOf(closedData[i][9]));
					}

					if (String.valueOf(closedData[i][10]).length() > 15) {
						closedBean.setReqSubject(String.valueOf(
								closedData[i][10]).substring(0, 15)
								+ "...");
					} else
						closedBean.setReqSubject(String
								.valueOf(closedData[i][10]));
					closedBean.setClosedReqDept(String
							.valueOf(closedData[i][11]));
					closedBean.setClosedReqCode(String
							.valueOf(closedData[i][12]));
					closedBean.setReqMgrStatus(String
							.valueOf(closedData[i][13]));
					closedList.add(closedBean);
				}// END LOOP
				helpBean.setClosedList(closedList);
			}// END IF DATA NOT NULL
		} catch (Exception e) {
			logger.error("Exception in getClosedList------" + e);
		}// END TRY CATCH BLOCK
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

	public void setActivityLogDetails(HelpDeskApproval bean) {
		String query = "SELECT FROM_EMP.EMP_FNAME||' '||FROM_EMP.EMP_MNAME||' '||FROM_EMP.EMP_LNAME,TO_EMP.EMP_FNAME||' '||TO_EMP.EMP_MNAME||' '||TO_EMP.EMP_LNAME,"
				+ " DECODE(HELPDESK_ACTIVITY_LOG.REQUEST_STATUS,'C','Closed','N','Rejected',HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME),TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH24:MI:SS'),"
				+ "NVL(REQUEST_COMMENTS,'') FROM HELPDESK_ACTIVITY_LOG "
				+ " LEFT JOIN HRMS_EMP_OFFC FROM_EMP  ON (FROM_EMP.EMP_ID=REQUEST_ACTION_BY)"
				+ " LEFT JOIN HRMS_EMP_OFFC TO_EMP ON (TO_EMP.EMP_ID=REQUEST_FORWARDED_TO)"
				+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =HELPDESK_ACTIVITY_LOG.REQUEST_STATUS)"
				+ "WHERE REQUEST_ID="
				+ bean.getRequestId()
				+ " ORDER BY REQUEST_DATE DESC";
		Object[][] logObj = getSqlModel().getSingleResult(query);
		if (logObj != null && logObj.length > 0) {
			ArrayList<Object> logList = new ArrayList<Object>();
			for (int i = 0; i < logObj.length; i++) {
				HelpDeskApproval beanList = new HelpDeskApproval();
				beanList.setLogFromEmp(Utility.checkNull(String
						.valueOf(logObj[i][0])));
				beanList.setLogToEmp(Utility.checkNull(String
						.valueOf(logObj[i][1])));
				beanList.setLogStatus(Utility.checkNull(String
						.valueOf(logObj[i][2])));
				beanList.setLogDate(Utility.checkNull(String
						.valueOf(logObj[i][3])));
				beanList.setLogComments(Utility.checkNull(String
						.valueOf(logObj[i][4])));
				logList.add(beanList);
			}
			bean.setLogList(logList);
			bean.setLogLength("true");
		}
	}

	public String onlineApproveRejectSendBack(HttpServletRequest request,
			String appStatus, String level, String initiatorId,String requestForEmpId,
			String isManagerAppr, String deptId, String reqTypeId,
			String requestId, String approverComments, String currentUser,
			String approverId) {

		String result = "";
		String res = "";
		try {
			String query = " SELECT REQUEST_PENDING_WITH,REQUEST_APPL_STATUS FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID="
					+ requestId;
			Object approverIdObj[][] = getSqlModel().getSingleResult(query);
			if (approverIdObj != null && approverIdObj.length > 0) {
				if (String.valueOf(approverIdObj[0][0]).equals(approverId)
						&& String.valueOf(approverIdObj[0][1]).equals("O")) {

					res = approveRejectApplication(request, appStatus, Integer
							.parseInt(level), initiatorId,requestForEmpId, isManagerAppr,
							deptId, reqTypeId, requestId, approverComments,
							approverId);
					if (res.equals("approved")) {
						result = "Helpdesk request has been approved.";
					} else if (res.equals("rejected")) {
						result = "Helpdesk request has been rejected.";
					} else if (res.equals("sendback")) {
						result = "Helpdesk request has been sent back to applicant.";
					} else {
						result = "Error Occured.";
					}
				} else {
					result = "Helpdesk request has already been processed.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void sendForwardNotificationMail(HttpServletRequest request,
			String requestId, String initiatorId,String requestForEmpId, String managerId,
			String isManagerAppr, String deptId, String reqTypeId,
			String userEmpId, int level) {
		try {
			HelpDeskAppModel model = new HelpDeskAppModel();
			model.initiate(context, session);

			/* 2. Request Submit Notification to Request Owner */

			EmailTemplateBody templateForRequestApproval = new EmailTemplateBody();
			templateForRequestApproval.initiate(context, session);
			templateForRequestApproval
					.setEmailTemplate("Helpdesk Request Forward Notification to Owner by Approver");
			templateForRequestApproval.getTemplateQueries();

			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "HelpDesk";
			processAlerts.removeProcessAlert(String.valueOf(requestId), module);

			try {
				// get the query as per number
				EmailTemplateQuery templateQueryForApproval1 = templateForRequestApproval
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQueryForApproval1.setParameter(1, userEmpId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForApproval2 = templateForRequestApproval
						.getTemplateQuery(2);// To EMAIL
				templateQueryForApproval2.setParameter(1, managerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForApproval3 = templateForRequestApproval
						.getTemplateQuery(3);
				templateQueryForApproval3.setParameter(1, requestId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForApproval4 = templateForRequestApproval
						.getTemplateQuery(4);
				templateQueryForApproval4.setParameter(1, requestId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForApproval5 = templateForRequestApproval
						.getTemplateQuery(5);
				templateQueryForApproval5.setParameter(1, requestId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQueryForApproval6 = templateForRequestApproval
						.getTemplateQuery(6);
				templateQueryForApproval6.setParameter(1, userEmpId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String ownerQuery = " SELECT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR "
					+ " WHERE DEPT_CODE = "
					+ deptId
					+ " AND REQ_TYPE_CODE = "
					+ reqTypeId
					+ " AND BRANCH_CODE = ( "
					+ " SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "
					+ initiatorId + ") ";
			Object[][] managerIdObj = getSqlModel().getSingleResult(ownerQuery);

			String reqManagerId = "";

			if (managerIdObj != null && managerIdObj.length > 0) {
				reqManagerId = String.valueOf(managerIdObj[0][0]);
			}
			try {
				String alertLink = "";
				String linkParam = "";
				templateForRequestApproval.configMailAlert();
				if (!(reqManagerId.equals(managerId))) {
					alertLink = "/help/HelpDeskApproval_retrieveDetails.action";
					linkParam = "reqAppCode=" + requestId + "&mgrStatus=P";
				} else {
					alertLink = "/help/HelpDeskProcess_retrieveDetails.action";
					linkParam = "reqAppCode=" + requestId + "&reqStatus=P"
							+ "&listStatus=PD";

				}
				templateForRequestApproval.sendProcessManagerAlert(managerId,
						"HelpDesk", "A", requestId, "1", linkParam, alertLink,
						"Pending", initiatorId, "", "", "", userEmpId);

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!(managerId.equals("0")) && !(reqManagerId.equals(managerId))) {
				// Add approval link -pass parameters to the link
				// String level = "1";
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				String applicationType = "HelpdeskSubmission";

				link_param[0] = applicationType + "#" + "A" + "#" + level + "#"
						+ initiatorId +"#"+requestForEmpId+"#"+ "#" + isManagerAppr + "#" + deptId
						+ "#" + reqTypeId + "#" + requestId + "#" + "...#"
						+ userEmpId + "#" + managerId;
				link_param[1] = applicationType + "#" + "R" + "#" + level + "#"
						+ initiatorId + "#" +requestForEmpId+"#" + isManagerAppr + "#" + deptId
						+ "#" + reqTypeId + "#" + requestId + "#" + "...#"
						+ userEmpId + "#" + managerId;
				link_param[2] = applicationType + "#" + "B" + "#" + level + "#"
						+ initiatorId +"#"+requestForEmpId+"#"+ isManagerAppr + "#" + deptId
						+ "#" + reqTypeId + "#" + requestId + "#" + "...#"
						+ userEmpId + "#" + managerId;

				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";

				templateForRequestApproval.addOnlineLink(request, link_param,
						link_label);
			}
			templateForRequestApproval.sendApplicationMail();
			templateForRequestApproval.clearParameters();
			templateForRequestApproval.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendForwardNotificationToApplicant(String status,
			String requestId, String initiatorId,String requestForEmpId ,String isManagerAppr,
			String deptId, String reqTypeId, String userEmpId,
			String checkApproved) {

		try {
			EmailTemplateBody templateForRequestApproval = new EmailTemplateBody();
			templateForRequestApproval.initiate(context, session);
			templateForRequestApproval
					.setEmailTemplate("HELP DESK - APPROVER TO APPLICANT");
			templateForRequestApproval.getTemplateQueries();
			String module = "HelpDesk";
			if (!checkApproved.equals("true")) {
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				processAlerts.removeProcessAlert(String.valueOf(requestId),
						module);
				// String module = "HelpDesk";
			}
			EmailTemplateQuery templateQueryForApproval1 = templateForRequestApproval
					.getTemplateQuery(1);// FROM EMAIL
			templateQueryForApproval1.setParameter(1, userEmpId);

			EmailTemplateQuery templateQueryForApproval2 = templateForRequestApproval
					.getTemplateQuery(2);// To EMAIL
			templateQueryForApproval2.setParameter(1, initiatorId);

			EmailTemplateQuery templateQueryForApproval3 = templateForRequestApproval
					.getTemplateQuery(3);
			templateQueryForApproval3.setParameter(1, requestId);

			EmailTemplateQuery templateQueryForApproval4 = templateForRequestApproval
					.getTemplateQuery(4);
			templateQueryForApproval4.setParameter(1, userEmpId);

			EmailTemplateQuery templateQueryForApproval5 = templateForRequestApproval
					.getTemplateQuery(5);
			templateQueryForApproval5.setParameter(1, requestId);

			EmailTemplateQuery templateQueryForApproval6 = templateForRequestApproval
					.getTemplateQuery(6);
			templateQueryForApproval6.setParameter(1, requestId);

			String actualStataus = "";
			if (status.equals("A")) {
				actualStataus = "Approved";
			}
			if (status.equals("R")) {
				actualStataus = "Rejected";
			}
			if (status.equals("B")) {
				actualStataus = "SentBack";
			}
			if (status.equals("P")) {
				actualStataus = "Pending";
			}
			templateForRequestApproval.configMailAlert();
			String applicantLink = "/help/HelpDesk_retrieveDetails.action";
			String applicantLinkParam = "reqAppCode=" + requestId;

			String approverLink = "/help/HelpDeskApproval_retrieveDetails.action";
			String approverLinkParam = "reqAppCode=" + requestId + "&"
					+ "mgrStatus=R";
			// SendManger ALert -- BEGINS
			if (status.equals("B")) {
				templateForRequestApproval.sendProcessManagerAlert(initiatorId,
						"HelpDesk", "A", requestId, "1", applicantLinkParam,
						applicantLink, actualStataus, initiatorId, "", "", "",
						userEmpId);
				if(!initiatorId.equals(requestForEmpId)){
					templateForRequestApproval.sendProcessManagerAlert(userEmpId,
							module, "I", requestId, "1", approverLinkParam,
							approverLink, actualStataus, initiatorId, "", "", requestForEmpId,
							userEmpId);
				}else{
				templateForRequestApproval.sendProcessManagerAlert(userEmpId,
						module, "I", requestId, "1", approverLinkParam,
						approverLink, actualStataus, initiatorId, "", "", "",
						userEmpId);
				}
			} else {
				if(!initiatorId.equals(requestForEmpId)){
					templateForRequestApproval.sendProcessManagerAlert(initiatorId,
							"HelpDesk", "I", requestId, "1", applicantLinkParam,
							applicantLink, actualStataus, initiatorId, "",
							requestForEmpId, userEmpId, userEmpId);
				}else{
				templateForRequestApproval.sendProcessManagerAlert(initiatorId,
						"HelpDesk", "I", requestId, "1", applicantLinkParam,
						applicantLink, actualStataus, initiatorId, "",
						initiatorId, userEmpId, userEmpId);
				}
			}
			// SendManger ALert -- ENDS
			templateForRequestApproval.sendApplicationMail();
			templateForRequestApproval.clearParameters();
			templateForRequestApproval.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean IsHoliday(String fromDate, String empId) {

		boolean isholiday = false;
		try {
			Object[][] holidayObj = null;
			String fiterquery = " SELECT DECODE(CONF_BRANCH_HOLI_FLAG, 'Y', 'true', 'N', 'false') BRN_HDAY_FLAG "
					+ " FROM HRMS_ATTENDANCE_CONF ";
			Object fitObj[][] = getSqlModel().getSingleResult(fiterquery);
			if (fitObj != null && fitObj.length > 0
					&& String.valueOf(fitObj[0][0]).equals("true")) {
				String holStr = " SELECT TO_CHAR(HRMS_HOLIDAY_BRANCH.HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY_BRANCH "
						+ " WHERE CENTER_ID = ( SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ empId
						+ ") "
						+ "  AND HRMS_HOLIDAY_BRANCH.HOLI_DATE =TO_DATE('"
						+ fromDate + "','DD-MM-YYYY')  ";

				holidayObj = getSqlModel().getSingleResult(holStr);

			} // end of if
			else { // for holiday check
				String holStr = " SELECT TO_CHAR(HOLI_DATE,'DD-MM-YYYY') FROM HRMS_HOLIDAY"
						+ " WHERE HOLI_DATE =TO_DATE('"
						+ fromDate
						+ "','DD-MM-YYYY') ";

				holidayObj = getSqlModel().getSingleResult(holStr);
			}
			if (holidayObj != null && holidayObj.length > 0) {
				isholiday = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isholiday;
	}

	public int getWorkingHours(String date,String empId,String requestId) {
		
		Object workHoursObj[][] = null;
		int workMinutes=0;
		String queryWorkHours = " SELECT TO_CHAR(SFT_START_TIME, 'HH24:MI'),TO_CHAR(SFT_END_TIME, 'HH24:MI'),TO_CHAR(SFT_WORK_HRS, 'HH24:MI'),SFT_FLEXITIME_ALLOWED from HRMS_SHIFT where SHIFT_ID = "+
		 						" (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID ="+empId+") ";
		workHoursObj = getSqlModel().getSingleResult(queryWorkHours);
		if(workHoursObj!=null && workHoursObj.length>0){
			
			 String RequestTimeQuery ="SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY HH24:MI')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID ="
					+ requestId;
			 Object[][] MaxdateQueryObj = getSqlModel().getSingleResult(RequestTimeQuery);
			//Added by Prajakta B
				String shiftStartTime="";
				String shiftEndTime="";
			 String maxDateTime =String.valueOf(MaxdateQueryObj[0][0]);
			 String maxDate = maxDateTime.substring(0,10); 
			if(date.equals(maxDate)){
				if(workHoursObj[0][3].equals("Y")){//if flexi shift
					shiftStartTime="0000";
					shiftEndTime="0000";
				}//end if flexi shift
				else{
					 shiftStartTime = String.valueOf(workHoursObj[0][0]).replace(
							":", "");
					
					shiftEndTime = String.valueOf(workHoursObj[0][1]).replace(
							":", "");
				}
					long shiftStart_Time = Long.parseLong(shiftStartTime);
					long shiftEnd_Time = Long.parseLong(shiftEndTime);
					
					String maxtime = maxDateTime.substring(11,maxDateTime.length());
					 String RequestTimeSplit = maxtime.replace(
								":", "");
					 long RequestTime = Long.parseLong(RequestTimeSplit);
					 
					 if(RequestTime >= shiftStart_Time && RequestTime <= shiftEnd_Time){
						 String diffHourquery = "SELECT round((TO_DATE('"+String.valueOf(workHoursObj[0][1])+"','HH24:MI') - TO_DATE('"+maxtime+"','HH24:MI')) * 24*60 )" +
				 			" FROM dual ";
			 		 	Object diffHourObj[][] = getSqlModel().getSingleResult(diffHourquery);
			 		 	String DiffTime = String.valueOf(diffHourObj[0][0]);
			 		 	workMinutes = Integer.parseInt(DiffTime);
					 }
			}else{			
			String strHourMinute = String.valueOf(workHoursObj[0][2]);
			String[] spilthourMinute = strHourMinute.split(":");
			int hour = Integer.parseInt(spilthourMinute[0]);
			int minute = Integer.parseInt(spilthourMinute[1]);
			workMinutes = (hour*60) + minute;
			
			}
				
		}
		return workMinutes;
	}
	
	public int getCurrentDayWorkingHour(String empId,String requestId) {
		
		Object shiftHrsObj[][] = null;
		int workMinutes=0;
		try {
			String shiftStartTmEndTmQuery = " SELECT TO_CHAR(SFT_START_TIME, 'HH24:MI'),TO_CHAR(SFT_END_TIME, 'HH24:MI') ,SFT_FLEXITIME_ALLOWED from HRMS_SHIFT where SHIFT_ID = "+
			 						" (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID ="+empId+") ";
			 shiftHrsObj = getSqlModel().getSingleResult(
					shiftStartTmEndTmQuery);
			if(shiftHrsObj!=null && shiftHrsObj[0].length>0){
				//Added by Prajakta B
				String shiftStartTime="";
				String shiftEndTime="";
				if(shiftHrsObj[0][2].equals("Y")){//if flexi shift
					shiftStartTime="0000";
					shiftEndTime="0000";
				}//end if flexi shift
				else{
				shiftStartTime = String.valueOf(shiftHrsObj[0][0]).replace(
						":", "");
				shiftEndTime = String.valueOf(shiftHrsObj[0][1]).replace(
						":", "");
				}//end of else
			//end added 
				long shiftStart_Time = Long.parseLong(shiftStartTime);
				long shiftEnd_Time = Long.parseLong(shiftEndTime);
				
				String currentDatefromSql = "SELECT TO_CHAR(SYSDATE,'HH24:MI') FROM DUAL";
				Object[][] currentDatefromObj = getSqlModel().getSingleResult(currentDatefromSql);
				 /*DateFormat hhmmssFormat = new SimpleDateFormat("kk:mm");
				  Date now = new Date();*/
				 /*String currentTime = hhmmssFormat.format(now);*/
				 String currentTime =String.valueOf(currentDatefromObj[0][0]);
				 String currentTimeSplit = String.valueOf(currentTime).replace(
							":", "");
				 long CurrentWorkHour = Long.parseLong(currentTimeSplit);
				 
				 String RequestTimeQuery ="SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY HH24:MI')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID ="
						+ requestId;
				 Object[][] MaxdateQueryObj = getSqlModel().getSingleResult(RequestTimeQuery);
				 String ReqDateTime = String.valueOf(MaxdateQueryObj[0][0]);
				 String ReqDate = ReqDateTime.substring(0,10);
				 String Reqtime =ReqDateTime.substring(11,ReqDateTime.length());
				 
				 DateFormat currentdateFm = new SimpleDateFormat("dd-MM-yyyy");
				  Date today = new Date();
				  		  
				 String currentDate = currentdateFm.format(today);
				 Object diffHourObj[][] =null;
				 String diffHourquery="";
				 if(CurrentWorkHour >= shiftStart_Time && CurrentWorkHour <= shiftEnd_Time ){
					 System.out.println("Current Time "+currentTime);
					 if(currentDate.equals(ReqDate)){
						 diffHourquery = "SELECT round((TO_DATE('"+currentTime+"','HH24:MI') - TO_DATE('"+Reqtime+"','HH24:MI')) * 24*60) " +
				 			" FROM dual ";
			 
						 diffHourObj = getSqlModel().getSingleResult(diffHourquery);
					 }else{
					  String ShiftStartTime = String.valueOf(shiftHrsObj[0][0]);
					   diffHourquery = "SELECT round((TO_DATE('"+currentTime+"','HH24:MI') - TO_DATE('"+ShiftStartTime+"','HH24:MI')) * 24*60) " +
						 			" FROM dual ";
					 
					  diffHourObj = getSqlModel().getSingleResult(diffHourquery);
					 }
					 String DiffTime = String.valueOf(diffHourObj[0][0]);
					 workMinutes = Integer.parseInt(DiffTime);
					 
					
				 }
				  
				 System.out.println("currentTime"+currentTime);
				 System.out.println("shiftStart_Time------------------"
						+ shiftStart_Time);
				System.out.println("shiftEnd_Time----------------------"
						+ shiftEnd_Time);
				
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workMinutes;
	}
	
	public boolean IsWeekEnd(String date, String empId) {
		boolean weekend = false;
		try {
			Calendar currentcalendarday = new Utility().getCalanderDate(date);
			weekend = isWeekOffCheckWithShift(empId, currentcalendarday);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return weekend;
	}

	private boolean isWeekOffCheckWithShift(String empId,
			Calendar currentcalendarday) {

		boolean weekend = false;
		Object weeklyOffObj[][] = null;
		try {
			String queryWeeklyOff = "SELECT  NVL(SHIFT_WEEK_1,0),NVL(SHIFT_WEEK_2,0),NVL(SHIFT_WEEK_3,0),NVL(SHIFT_WEEK_4,0),NVL(SHIFT_WEEK_5,0), NVL(SHIFT_WEEK_6,0) "
					+ " FROM HRMS_SHIFT WHERE SHIFT_ID ="
					+ " (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empId + ")";
			weeklyOffObj = getSqlModel().getSingleResult(queryWeeklyOff);
		} catch (Exception e) {
			// TODO: handle exception
		}

		weekend = weekenDays(currentcalendarday, weeklyOffObj);
		return weekend;

	}

	public boolean weekenDays(Calendar currentcalendarday,
			Object weeklyOffObj[][]) {

		boolean isWeekend = false;
		try {
			//To find out currentcalendarday falls in which week of the month 
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
							isWeekend = true;
						break;
					case 2:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 2)
							isWeekend = true;
						break;
					case 3:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 3)
							isWeekend = true;
						break;
					case 4:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 4)
							isWeekend = true;
						break;
					case 5:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 5)
							isWeekend = true;
						break;
					case 6:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 6)
							isWeekend = true;
						break;
					case 7:
						if (currentcalendarday.get(Calendar.DAY_OF_WEEK) == 7)
							isWeekend = true;
						break;
					default:
						break;
					}// end of switch loop
				}// end of for loop
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in weekenDays--------- " + e);
		}
		return isWeekend;
	}// end of weekenDays

}
