/**
 * 
 */
package org.paradyne.model.extraWorkBenefits;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.extraWorkBenefits.ExtraWorkApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;

/**
 * @author Reeba Joseph
 * 
 */
public class ExtraWorkApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ExtraWorkApprovalModel.class);

	/**
	 * Get the list of Pending Applications
	 * 
	 * @param approval
	 * @param request
	 * @param empId
	 */
	public void getAllTypeOfApplications(ExtraWorkApproval approval,
			HttpServletRequest request, String empId) {
		Object pendingParam[] = null;

		pendingParam = new Object[] { "P", empId, empId };
		Object pendingData[][] = getSqlModel().getSingleResult(getQuery(1),
				pendingParam);

		String[] pageIndexPending = Utility.doPaging(approval.getMyPage(),
				pendingData.length, 20);
		if (pageIndexPending == null) {
			pageIndexPending[0] = "0";
			pageIndexPending[1] = "20";
			pageIndexPending[2] = "1";
			pageIndexPending[3] = "1";
			pageIndexPending[4] = "";
		}

		request.setAttribute("totalPagePending", Integer.parseInt(String
				.valueOf(pageIndexPending[2])));
		request.setAttribute("PageNoPending", Integer.parseInt(String
				.valueOf(pageIndexPending[3])));
		if (pageIndexPending[4].equals("1"))
			approval.setMyPage("1");

		if (pendingData != null && pendingData.length > 0) {
			approval.setPendingLength("true");
			ArrayList<Object> pendingList = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndexPending[0]); i < Integer
					.parseInt(pageIndexPending[1]); i++) {
				ExtraWorkApproval bean1 = new ExtraWorkApproval();
				bean1.setExtWrkAppNo((String.valueOf(pendingData[i][0])));// application
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
			approval.setPendingList(pendingList);
		}

	}

	/**
	 * get the list of Approved applications
	 * 
	 * @param approval
	 * @param request
	 * @param empId
	 */
	public void getApprovedList(ExtraWorkApproval approval,
			HttpServletRequest request, String empId) {
		Object approvedParam[] = null;
		approvedParam = new Object[] { "A", empId, empId };
		Object approvedData[][] = getSqlModel().getSingleResult(getQuery(1),
				approvedParam);

		String[] pageIndexApproved = Utility.doPaging(approval
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
			approval.setMyPageApproved("1");

		if (approvedData != null && approvedData.length > 0) {

			approval.setApprovedLength("true");
			ArrayList<Object> approvedList = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
					.parseInt(pageIndexApproved[1]); i++) {
				ExtraWorkApproval bean1 = new ExtraWorkApproval();
				bean1.setAppExtAppNo((String.valueOf(approvedData[i][0])));// application
				// code
				bean1.setAppEmpId(String.valueOf(approvedData[i][1]));// employee
				// id
				bean1.setAppEmpName(String.valueOf(approvedData[i][2]));// employee
				// name
				bean1.setAppAppDate(String.valueOf(approvedData[i][3]));// application
				// date
				bean1.setAppStatus(String.valueOf(approvedData[i][4]));// status
				bean1.setAppLevel(String.valueOf(approvedData[i][5]));// level
				bean1.setAppEmpToken(String.valueOf(approvedData[i][6]));// employee
				// token

				approvedList.add(bean1);
			}
			approval.setAppList(approvedList);
		}
	}

	/**
	 * Get the list of rejected applications
	 * 
	 * @param approval
	 * @param request
	 * @param empId
	 */
	public void getRejectedList(ExtraWorkApproval approval,
			HttpServletRequest request, String empId) {
		Object rejectedParam[] = null;
		rejectedParam = new Object[] { "R", empId, empId };
		Object rejectedData[][] = getSqlModel().getSingleResult(getQuery(1),
				rejectedParam);

		String[] pageIndexRejected = Utility.doPaging(approval
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
			approval.setMyPageRejected("1");
		if (rejectedData != null && rejectedData.length > 0) {
			approval.setRejectedLength("true");
			ArrayList<Object> rejectedList = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
					.parseInt(pageIndexRejected[1]); i++) {
				ExtraWorkApproval rejectedBean = new ExtraWorkApproval();
				rejectedBean
						.setRejExtAppNo((String.valueOf(rejectedData[i][0])));// application
				// code
				rejectedBean.setRejEmpId(String.valueOf(rejectedData[i][1]));// employee
				// id
				rejectedBean.setRejEmpName(String.valueOf(rejectedData[i][2]));// employee
				// name
				rejectedBean.setRejAppDate(String.valueOf(rejectedData[i][3]));// application
				// date
				rejectedBean.setRejStatus(String.valueOf(rejectedData[i][4]));// status
				rejectedBean.setRejLevel(String.valueOf(rejectedData[i][5]));// level
				rejectedBean.setRejEmpToken(String.valueOf(rejectedData[i][6]));// employee
				// token
				rejectedList.add(rejectedBean);
			}
			approval.setRejList(rejectedList);
		}
	}

	/**
	 * Updates status on approve/reject/send back. Sends Email and displays
	 * process manager alerts
	 * 
	 * @param request
	 * @param empCode
	 * @param extWrkAppNo
	 * @param status
	 * @param remarks
	 * @param approverId
	 * @param level
	 * @return String value
	 */
	public String approveRejectSendBackApp(HttpServletRequest request,
			String empCode, String extWrkAppNo, String status, String remarks,
			String approverId, String level) {
		String applStatus = "pending";
		Object[][] empFlow = null;

		Object[][] changeStatus = new Object[1][4];
		changeStatus[0][0] = extWrkAppNo; // application code
		changeStatus[0][1] = approverId; // user employee id
		changeStatus[0][2] = status; // status
		changeStatus[0][3] = remarks; // remark

		if (String.valueOf(status).equals("A")) {
			// METHODS FROM ACTION
			empFlow = generateEmpFlow(empCode, "ExtraDayBenefit", Integer
					.parseInt(level) + 1);
			applStatus = changeApplStatus(empFlow, String.valueOf(extWrkAppNo),
					empCode, status, request);

		}//
		if (String.valueOf(status).equals("B")) {
			Object[][] sendBackObj = new Object[1][4];
			sendBackObj[0][0] = status; // status
			sendBackObj[0][1] = "1";
			empFlow = generateEmpFlow(empCode, "ExtraDayBenefit", 1);
			sendBackObj[0][2] = String.valueOf(empFlow[0][0]);
			sendBackObj[0][3] = extWrkAppNo;// application code
			Object[][] sendBackObjDtl = new Object[1][3];
			sendBackObjDtl[0][0] = status; // status
			sendBackObjDtl[0][1] = String.valueOf(empFlow[0][0]);
			sendBackObjDtl[0][2] = extWrkAppNo;
			getSqlModel().singleExecute(getQuery(7), sendBackObj);// hrms_leave_hdr
			getSqlModel().singleExecute(getQuery(8), sendBackObjDtl);// hrms_leave_dtl

			applStatus = "sendback";

		}//

		if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status); // status
			reject[0][1] = String.valueOf(extWrkAppNo);// application code

			getSqlModel().singleExecute(getQuery(3), reject);// hrms_leave_hdr
			getSqlModel().singleExecute(getQuery(4), reject);// hrms_leave_dtl

			applStatus = "rejected";
		}// end of if

		// insert record in to path table
		getSqlModel().singleExecute(getQuery(6), changeStatus);

		// calls email template
		createTemplateWithAlerts(request, status, extWrkAppNo, remarks,
				empCode, approverId, level, empFlow, applStatus);

		if(applStatus.equals("rejected")){
			rejectExtraWorkAttendenceData(empCode,extWrkAppNo);
		}
		
		return applStatus;

	}

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
	 * THIS METHOD IS USED FOR CHANGING PENDING APPLICATION STATUS AS APPROVE OR
	 * REJECT OR SEND BACK
	 * 
	 * @param empFlow
	 * @param extWrkAppNo
	 * @param empId
	 * @param status
	 * @param request
	 * @return String
	 */
	public String changeApplStatus(Object[][] empFlow, String extWrkAppNo,
			String empId, String status, HttpServletRequest request) {
		String applStatus = "pending";
		boolean result = false;
		if (empFlow != null && empFlow.length != 0) {

			Object[][] updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][2]; // level
			updateApprover[0][1] = empFlow[0][0]; // approver id
			updateApprover[0][2] = empFlow[0][3]; // alternate approver id
			updateApprover[0][3] = extWrkAppNo; // application code
			result = getSqlModel().singleExecute(getQuery(2), updateApprover);
			String updateDtlApproverQuery = "UPDATE HRMS_EXTRAWORK_APPL_DTL  SET EXTRAWORK_APPROVED_BY="
					+ updateApprover[0][1]
					+ "  WHERE EXTRAWORK_APPL_CODE="
					+ updateApprover[0][3];
			result = getSqlModel().singleExecute(updateDtlApproverQuery);
			if (result) {
				applStatus = "forwarded";
			}
		} // end of if
		else {
			Object[][] statusChanged = new Object[1][2];
			statusChanged[0][0] = "A"; // status
			statusChanged[0][1] = extWrkAppNo; // application code
			// hrms_leave_hdr status update
			result = getSqlModel().singleExecute(getQuery(3), statusChanged);
			if (result) {
				// hrms_leave_dtl status update
				result = getSqlModel()
						.singleExecute(getQuery(4), statusChanged);
				if (result) {
					try {
						// UPDATING LEAVE BALANCE FOR EACH EXTRA WORK DATE
						String extraWorkDayQuery = " SELECT EXTRAWORK_DATE,EXTRAWORK_DAY,EXTRAWORK_DAY_TYPE FROM HRMS_EXTRAWORK_APPL_DTL "
								+ " WHERE EXTRAWORK_APPL_CODE=" + extWrkAppNo;
						Object[][] extraWorkDayObj = getSqlModel()
								.getSingleResult(extraWorkDayQuery);
						boolean flag = false;
						if (extraWorkDayObj != null
								&& extraWorkDayObj.length > 0) {
							for (int i = 0; i < extraWorkDayObj.length; i++) {
								flag = getLeaveUpdate(empId, String
										.valueOf(extraWorkDayObj[i][1]), String
										.valueOf(extraWorkDayObj[i][2]),extWrkAppNo);
							}

							if (flag) {
								String updateQuery = " 	 UPDATE HRMS_EXTRAWORK_APPL_DTL "
										+ " SET EXTRAWORK_IS_COMPOFF='Y' "
										+ " WHERE EXTRAWORK_APPL_CODE="
										+ extWrkAppNo;
								getSqlModel().singleExecute(updateQuery);
							}

						}
					} catch (Exception e) {
						logger
								.error("Error in calling getLeaveUpdate function ");
						e.printStackTrace();
					}
					applStatus = "approved";
				}
			}
		}
		if (applStatus.equals("approved")) {
			insertExtraWorkAttendenceData(extWrkAppNo);
		}

		return applStatus;

	}// end of changeApplStatus

	/**
	 * Updates leave balance on approval if setting is defined for extra work
	 * day
	 * 
	 * @param empId
	 * @param benefitFor
	 */
	public boolean getLeaveUpdate(String approverEmpId, String benefitFor, String type,String extWrkAppNo) {

		String leaveType = "";
		String noOfLeaves = "";
		String empId="";
		boolean partialVoilation=false;
		boolean result = false;

		String empIdQuery= "SELECT EMP_ID FROM HRMS_EXTRAWORK_APPL_HDR WHERE EXTRAWORK_APPL_CODE="+extWrkAppNo;
		Object[][] empIdObj= getSqlModel().getSingleResult(empIdQuery);	
		if(empIdObj != null && empIdObj.length >0){
			empId =String.valueOf(empIdObj[0][0]);
		}
		String shiftQuery = " SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID = "+ empId;
	    Object[][] shiftObj = getSqlModel().getSingleResult(shiftQuery);
	    
	   
	    
	    String queryShiftHrs  = "SELECT  TO_CHAR(SFT_WORK_HRS,'HH24:MI') FROM HRMS_SHIFT  "
			+ " WHERE HRMS_SHIFT.SHIFT_ID=" + shiftObj[0][0];
	    Object shiftHrsObj[][] = getSqlModel().getSingleResult(queryShiftHrs);
	    
		
		String selectQuery = " SELECT EMP_DIVISION, EMP_DEPT, EMP_CENTER, EMP_RANK, EMP_TYPE,HRMS_EXTRAWORK_SETTING.EXTRAWORK_BENEFIT_CODE "
				+ "  ,EXTRAWORK_BENEFIT_FOR,EXTRAWORK_BENEFIT_TYPE,EXTRAWORK_LEAVE_CREDIT_TYPE, NVL(EXTRAWORK_LEAVE_CREDIT_AMT,0) "
				+ "  ,EXTRAWORK_FIXBENEFIT_FULLDAY,EXTRAWORK_FIXBENEFIT_HALFDAY,EXTRAWORK_FIXBENEFIT_HOUR,EXTRAWORK_VARBENEFIT_FORMULA,EXTRAWORK_BENEFIT_CREDITCODE   "
				+ "  FROM HRMS_EXTRAWORK_SETTING "
				+ " INNER JOIN HRMS_EXTRAWORK_BENEFIT ON(HRMS_EXTRAWORK_BENEFIT.EXTRAWORK_BENEFIT_CODE=HRMS_EXTRAWORK_SETTING.EXTRAWORK_BENEFIT_CODE)      "
				+ "  WHERE HRMS_EXTRAWORK_SETTING.EMP_DIVISION IN(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ empId
				+ " AND EXTRAWORK_BENEFIT_FOR ='"
				+ benefitFor
				+ "' AND EXTRAWORK_BENEFIT_TYPE='EL')"
				+ "  ORDER BY  (CASE WHEN EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+  "
				+ "(CASE WHEN EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
				+ "(CASE WHEN EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+   "
				+ "(CASE WHEN EMP_RANK IS NULL THEN 1 ELSE 0 END )+   "
				+ " (CASE WHEN EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC  ";
       
		
		Object selObj[][] = getSqlModel().getSingleResult(selectQuery);
		if (selObj != null && selObj.length > 0){
			String compShiftHrs = checkShiftHours(empId,extWrkAppNo,String.valueOf(shiftHrsObj[0][0]), String.valueOf(shiftObj[0][0]));
		  if (compShiftHrs.equals("partialForExtraDayPayViolation")) {
				partialVoilation=true;
	       }
		}
		
		for (int i = 0; i < selObj.length; i++) {
			String query = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 ";
			if (!(String.valueOf(selObj[i][4]).equals(""))
					&& !(String.valueOf(selObj[i][4]) == null)
					&& !String.valueOf(selObj[i][4]).equals("null")) {
				// if emp type not null
				query += " AND EMP_TYPE =" + String.valueOf(selObj[i][4]);

			}// end if
			if (!(String.valueOf(selObj[i][1]).equals(""))
					&& !(String.valueOf(selObj[i][1]) == null)
					&& !String.valueOf(selObj[i][1]).equals("null")) {
				// if dept not null
				query += " AND EMP_DEPT =" + String.valueOf(selObj[i][1]);
			}// end if
			if (!(String.valueOf(selObj[i][2]).equals(""))
					&& !(String.valueOf(selObj[i][2]) == null)
					&& !String.valueOf(selObj[i][2]).equals("null")) {
				// if branch not null
				query += " AND EMP_CENTER =" + String.valueOf(selObj[i][2]);

			}// end if
			if (!(String.valueOf(selObj[i][3]).equals(""))
					&& !(String.valueOf(selObj[i][3]) == null)
					&& !String.valueOf(selObj[i][3]).equals("null")) {
				// if desg not null
				query += " AND EMP_RANK =" + String.valueOf(selObj[i][3]);
			}// end if
			query += " AND EMP_DIV=" + String.valueOf(selObj[i][0])
					+ "  AND HRMS_EMP_OFFC.EMP_ID=" + empId;
			Object obj[][] = getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				leaveType = String.valueOf(selObj[i][8]);
				noOfLeaves = String.valueOf(selObj[i][9]);
				logger.info("noOfLeavesold   : " + noOfLeaves);
				if (type.equals("F")) {
					noOfLeaves = String.valueOf(Double.parseDouble(noOfLeaves));
				} else if (type.equals("H")) {
					noOfLeaves = String
							.valueOf(Double.parseDouble(noOfLeaves) / 2);
					logger.info("in half day noOfLeaves   : " + noOfLeaves);
				} else
					noOfLeaves = String.valueOf(Double.parseDouble(noOfLeaves));
				logger.info("leaveType   : " + leaveType);
				logger.info("noOfLeavesnew   : " + noOfLeaves);
			}
		}

		// CHECK FOR UNADJUSTED PENALTY LEAVES
		/*
		 * String unadjustedLeaves = "SELECT LEAVE_PENALTY_UNADJUST_DAYS FROM
		 * HRMS_LEAVE_DTL WHERE LEAVE_CODE=" + leaveType+""; Object[][]
		 * unadjustedLeavesObj =
		 * getSqlModel().getSingleResult(unadjustedLeaves);
		 * if(unadjustedLeavesObj!=null && unadjustedLeavesObj.length>0){
		 * noOfLeaves =
		 * noOfLeaves+Integer.parseInt(String.valueOf(unadjustedLeavesObj[0][0]));
		 * logger.info("No of leaves in penalty unadjusted : "+noOfLeaves); }
		 */

		// CHECK IF LEAVE CODE PRESENT IN LEAVE BALANCE D/B
		String checkQuery = "SELECT LEAVE_CODE FROM HRMS_LEAVE_BALANCE WHERE EMP_ID="
				+ empId + " AND LEAVE_CODE =" + leaveType;
		Object[][] checkObj = getSqlModel().getSingleResult(checkQuery);
		if (checkObj != null && checkObj.length > 0) {
			// UPDATE LEAVE BALANCE
			try {
				if(!partialVoilation){
				String updateQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CODE="
						+ leaveType
						+ ", LEAVE_OPENING_BALANCE=LEAVE_OPENING_BALANCE+"
						+ noOfLeaves
						+ " , LEAVE_CLOSING_BALANCE=LEAVE_CLOSING_BALANCE+"
						+ noOfLeaves
						+ " WHERE EMP_ID="
						+ empId
						+ " AND LEAVE_CODE =" + leaveType;
				result = getSqlModel().singleExecute(updateQuery);
				}
			} catch (Exception e) {
				logger.error("Error while updating Leave balance : " + e);
				e.printStackTrace();
			}
		} else {
			// INSERT LEAVE BALANCE
			try {
				if(!partialVoilation){
				String updateQuery = "INSERT INTO HRMS_LEAVE_BALANCE (EMP_ID,LEAVE_CODE,"
						+ " LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE) "
						+ " VALUES ("
						+ empId
						+ ", "
						+ leaveType
						+ ", "
						+ noOfLeaves + ", " + noOfLeaves + ")";
				result = getSqlModel().singleExecute(updateQuery);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Calling email templates
	 * 
	 * @param request
	 * @param status
	 * @param extWrkAppNo
	 * @param remarks
	 * @param empCode
	 * @param approverId
	 * @param level
	 * @param empFlow
	 * @param applStatus
	 */
	public void createTemplateWithAlerts(HttpServletRequest request,
			String status, String extWrkAppNo, String remarks, String empCode,
			String approverId, String level, Object[][] empFlow,
			String applStatus) {
		String keepInfo = "";
		String linkParam = "";
		String link = "";
		String actualStatus= applStatus;
		String alternateApprover="";
		try {
			/*alternateApprover = (empFlow != null && !String.valueOf(
					empFlow[0][3]).equals("0")) ? String.valueOf(empFlow[0][3])
					: "";*/
		} catch (Exception e) {
			// TODO: handle exception
		}
		String keepInformedId = " SELECT  EXTRAWORK_KEEP_INFORMED FROM HRMS_EXTRAWORK_APPL_HDR "
			+ " WHERE EXTRAWORK_APPL_CODE=" + extWrkAppNo;

		Object[][] keepInformedObj = getSqlModel().getSingleResult(
			keepInformedId);

		// --------Email Templates + Process Manager Alert--------------start
		try {
			if (!String.valueOf(status).equals("P")) {
				ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				String module = "ExtraWork";
				processAlerts.removeProcessAlert(String.valueOf(extWrkAppNo),
						module);
				
				if (status.equals("A")) {
					actualStatus = "Approved";
				}
				if (status.equals("R")) {
					actualStatus = "Rejected";
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
				String applnID = String.valueOf(extWrkAppNo);
				String alertLevel = String.valueOf(Integer.parseInt(level) + 1);

				if (!newApprover.equals("")) {
					String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' '), MODULE_NAME FROM  HRMS_MAIL_EVENTS "
							+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
							+ " LEFT JOIN HRMS_MODULE ON (HRMS_MODULE.MODULE_CODE = HRMS_MAIL_EVENTS.EVENT_MODULE_CODE) "
							+ " WHERE EVENT_CODE=76";

					Object[][] tempData = getSqlModel()
							.getSingleResult(tempSql);
					String templateCode = "";
					if (tempData != null && tempData.length > 0) {

						if (String.valueOf(tempData[0][0]).equals("N")) {
							logger.info("Event Option Flage is False.");
							return;
						}

						if (tempData[0][1] != null
								&& !String.valueOf(tempData[0][1]).equals("")) {
							templateCode = String.valueOf(tempData[0][1]);
						} else {
							logger.info("Template is not Defined. : "
									+ templateCode);
							return;
						}

					} else {
						logger.info("Event Template is not Defined.");
						return;
					}
					// send alert from oldApprover to newApprover
					//EXTRAWORK APPL-MAIL SENT TO SECOND APPROVER
					empID = newApprover;
					msgType = "A";
					module = String.valueOf(tempData[0][4]);

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));

					template.getTemplateQueries();
					try{
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);
					} catch(Exception e){
						e.printStackTrace();
					}

					try{
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, newApprover);
					} catch(Exception e){
						e.printStackTrace();
					}

					try{
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);
					} catch(Exception e){
						e.printStackTrace();
					}
					try{
	
					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, oldApprover);
					} catch(Exception e){
						e.printStackTrace();
					}
					try{

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, applnID);
					templateQuery5.setParameter(2, applicant);
					} catch(Exception e){
						e.printStackTrace();
					}
					try{
					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, newApprover);
					} catch(Exception e){
						e.printStackTrace();
					}
					try{
						EmailTemplateQuery templateQuery7 = template
								.getTemplateQuery(7);
						templateQuery7.setParameter(1, applnID);
						} catch(Exception e){
							e.printStackTrace();
						}
						
					String applicationType = "ExtraWorkAppl";
					// Add approval link -pass parameters to the link
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					link_param[0] = applicationType + "#" + applicant + "#"
							+ applnID + "#" + "A" + "#" + "..." + "#"
							+ newApprover + "#" + alertLevel;
					link_param[1] = applicationType + "#" + applicant + "#"
							+ applnID + "#" + "R" + "#" + "..." + "#"
							+ newApprover + "#" + alertLevel;
					link_param[2] = applicationType + "#" + applicant + "#"
							+ applnID + "#" + "B" + "#" + "..." + "#"
							+ newApprover + "#" + alertLevel;

					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";
					try{
					template.configMailAlert();

					}catch(Exception e){		
						e.printStackTrace();
					}
					
					try {						
					 link = "/extraWorkBenefits/ExtraWorkApplication_retrieveForApproval.action";		
					 linkParam = "extWrkApplicationNo="+extWrkAppNo+"&"+"applicationStatus=" + "P"; 
					 template.sendProcessManagerAlert(newApprover, "ExtraWork",
								"A", applnID, alertLevel, linkParam, link,
								actualStatus, applicant, "", "", "",
								oldApprover);						
					 template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}					
					template.addOnlineLink(request, link_param, link_label);
					template.clearParameters();
					template.terminate();

					// send alert to applicant regarding first approval
					String tempSqlAppl = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' '), MODULE_NAME FROM  HRMS_MAIL_EVENTS "
							+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
							+ " LEFT JOIN HRMS_MODULE ON (HRMS_MODULE.MODULE_CODE = HRMS_MAIL_EVENTS.EVENT_MODULE_CODE) "
							+ " WHERE EVENT_CODE=77";

					Object[][] tempDataAppl = getSqlModel().getSingleResult(
							tempSqlAppl);
					String templateCodeAppl = "";
					if (tempDataAppl != null && tempDataAppl.length > 0) {

						if (String.valueOf(tempDataAppl[0][0]).equals("N")) {
							logger.info("Event Option Flage is False.");
							return;
						}

						if (tempDataAppl[0][1] != null
								&& !String.valueOf(tempDataAppl[0][1]).equals(
										"")) {
							templateCodeAppl = String
									.valueOf(tempDataAppl[0][1]);
						} else {
							logger.info("Template is not Defined. : "
									+ templateCodeAppl);
							return;
						}

					} else {
						logger.info("Event Template is not Defined.");
						return;
					}

					empID = applicant;
					msgType = "I";
					module = String.valueOf(tempDataAppl[0][4]);

					EmailTemplateBody template1 = new EmailTemplateBody();
					template1.initiate(context, session);
					template1.setEmailTemplate(String
							.valueOf(tempDataAppl[0][3]));

					template1.getTemplateQueries();

					EmailTemplateQuery templateQuery8 = template1
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery8.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery9 = template1
							.getTemplateQuery(2); // TO EMAIL
					templateQuery9.setParameter(1, applicant);

					EmailTemplateQuery templateQuery10 = template1
							.getTemplateQuery(3);
					templateQuery10.setParameter(1, applnID);

					EmailTemplateQuery templateQuery11 = template1
							.getTemplateQuery(4);
					templateQuery11.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery12 = template1
							.getTemplateQuery(5);
					templateQuery12.setParameter(1, applnID);
					templateQuery12.setParameter(2, applicant);

					EmailTemplateQuery templateQuery13 = template1
							.getTemplateQuery(6);
					templateQuery13.setParameter(1, newApprover);

					EmailTemplateQuery templateQuery14 = template1
					.getTemplateQuery(7);
			         templateQuery14.setParameter(1, applnID);
					try{
					template1.configMailAlert();
					}catch(Exception e){
						e.printStackTrace();
					}
					
					try {
						link = "/extraWorkBenefits/ExtraWorkApplication_retrieveDetails.action";
						linkParam ="extWrkApplCode=" + applnID + "&extWrkStatus=A";
						if (keepInformedObj != null
								&& keepInformedObj.length > 0) {
							keepInfo = String.valueOf(keepInformedObj[0][0]);
							template1.sendApplicationMailToKeepInfo(keepInfo);
						}
						template1.sendProcessManagerAlert(oldApprover,
								"ExtraWork", "I", applnID, alertLevel,
								linkParam, link, "Approved", applicant,
								alternateApprover, keepInfo, applicant,
								oldApprover);
						
						template1.sendApplicationMail();

					} catch (Exception e) {
						logger.error(e);
					}
					template1.clearParameters();
					template1.terminate();
				} else {
					// send alert from oldApprover to applicant
					String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' '), MODULE_NAME FROM  HRMS_MAIL_EVENTS "
							+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
							+ " LEFT JOIN HRMS_MODULE ON (HRMS_MODULE.MODULE_CODE = HRMS_MAIL_EVENTS.EVENT_MODULE_CODE) "
							+ " WHERE EVENT_CODE=78";

					Object[][] tempData = getSqlModel()
							.getSingleResult(tempSql);
					String templateCode = "";
					if (tempData != null && tempData.length > 0) {

						if (String.valueOf(tempData[0][0]).equals("N")) {
							logger.info("Event Option Flage is False.");
							return;
						}

						if (tempData[0][1] != null
								&& !String.valueOf(tempData[0][1]).equals("")) {
							templateCode = String.valueOf(tempData[0][1]);
						} else {
							logger.info("Template is not Defined. : "
									+ templateCode);
							return;
						}

					} else {
						logger.info("Event Template is not Defined.");
						return;
					}

					empID = applicant;
					msgType = "I";
					module = String.valueOf(tempData[0][4]);

					EmailTemplateBody template2 = new EmailTemplateBody();
					template2.initiate(context, session);
					template2.setEmailTemplate(String.valueOf(tempData[0][3]));

					template2.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template2
								.getTemplateQuery(1); // FROM EMAIL
					templateQuery1.setParameter(1, oldApprover);
					
					EmailTemplateQuery templateQuery2 = template2
								.getTemplateQuery(2); // TO EMAIL
					templateQuery2.setParameter(1, applicant);
					
					EmailTemplateQuery templateQuery3 = template2
								.getTemplateQuery(3);
					templateQuery3.setParameter(1, applnID);
					
					EmailTemplateQuery templateQuery4 = template2
								.getTemplateQuery(4);
					templateQuery4.setParameter(1, oldApprover);
					
					EmailTemplateQuery templateQuery5 = template2
								.getTemplateQuery(5);
					templateQuery5.setParameter(1, applnID);
					templateQuery5.setParameter(2, applicant);
					
					EmailTemplateQuery templateQuery6 = template2
					.getTemplateQuery(6);
					templateQuery6.setParameter(1, applnID);
					templateQuery6.setParameter(2, oldApprover);
					
					template2.configMailAlert();
					
				if (keepInformedObj != null && keepInformedObj.length > 0) {
					 keepInfo = String.valueOf(keepInformedObj[0][0]);
					template2.sendApplicationMailToKeepInfo(keepInfo);
				}
				if (status.equals("P")) {
					actualStatus = "Pending";
				}
				if (status.equals("A")) {
					actualStatus = "Approved";
				}
				if (status.equals("R")) {
					actualStatus = "Rejected";
				}
					try {	
						 link = "/extraWorkBenefits/ExtraWorkApplication_retrieveDetails.action";
						 linkParam ="extWrkApplCode=" + applnID + "&extWrkStatus="+ status;
						
						if (level.equals("1")) {
							empFlow = generateEmpFlow(empCode, "ExtraDayBenefit", Integer
									.parseInt(level));
						} else {
							empFlow = generateEmpFlow(empCode, "ExtraDayBenefit", Integer
									.parseInt(level) - 1);
						}						
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
						if (status.equals("B")) {
							actualStatus = "SentBack";
							template2.sendProcessManagerAlert("", "ExtraWork", "A",
									applnID, alertLevel, linkParam, link,
									actualStatus, applicant, "", "",
									applicant, oldApprover);
							
							link = "/extraWorkBenefits/ExtraWorkApplication_retrieveForApproval.action";		
							 linkParam = "extWrkApplicationNo="+extWrkAppNo+"&"+"applicationStatus=" + "A"; ;
							 
							template2.sendProcessManagerAlert(oldApprover,
									"ExtraWork", "I", applnID, alertLevel,
									linkParam, link, actualStatus, applicant,
									alternateApprover, ccId, "", oldApprover);
						} else {

							template2.sendProcessManagerAlert(oldApprover,
									"ExtraWork", "I", applnID, alertLevel,
									linkParam, link, actualStatus, applicant,
									alternateApprover, ccId, applicant,
									oldApprover);
						}
						template2.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
					template2.clearParameters();
					template2.terminate();
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		// --------Email Templates + Process Manager Alert--------------end
	}

	/**
	 * Method to approve/reject/send back application through email
	 * 
	 * @param request
	 * @param empCode
	 * @param extWrkAppNo
	 * @param status
	 * @param remarks
	 * @param approverId
	 * @param level
	 * @return String value
	 */
	public String onlineApproveReject(HttpServletRequest request,
			String empCode, String extWrkAppNo, String status, String remarks,
			String approverId, String level) {
		String result = "";
		String res = "";
		String query = " SELECT EXTRAWORK_APPL_APPROVER,EXTRAWORK_APPL_STATUS FROM HRMS_EXTRAWORK_APPL_HDR WHERE EMP_ID="
				+ empCode + " AND EXTRAWORK_APPL_CODE=" + extWrkAppNo;

		Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverId)
					&& String.valueOf(approverIdObj[0][1]).equals("P")) {
				res = approveRejectSendBackApp(request, empCode, extWrkAppNo,
						status, remarks, approverId, level);

				if (res.equals("approved"))
					result = "Extra work benefits application has been approved.";
				else if (res.equals("rejected"))
					result = "Extra work benefits application has been rejected.";
				else if (res.equals("forwarded"))
					result = "Extra work benefits application has been forwarded for next approval.";
				else if (res.equals("sendback"))
					result = "Extra work benefits application has been sent back to applicant.";

				else
					result = "Error Occured.";
			} else {
				result = "Extra work benefits application has already been processed.";
			}
		}

		return result;

	}

	public boolean insertExtraWorkAttendenceData(String extWrkAppNo) {
		boolean result = false;
		String empId= "0";
		try {
			String empIdQuery= "SELECT EMP_ID FROM HRMS_EXTRAWORK_APPL_HDR WHERE EXTRAWORK_APPL_CODE="+extWrkAppNo;
			Object[][] empIDObj= getSqlModel().getSingleResult(empIdQuery);
			if(empIDObj != null && empIDObj.length >0){
				empId = String.valueOf(empIDObj[0][0]);
			}
			String extraWorkDayQuery = " SELECT TO_CHAR(EXTRAWORK_DATE,'DD-MM-YYYY') FROM HRMS_EXTRAWORK_APPL_DTL "
					+ " WHERE EMP_ID ="
					+ empId
					+ "AND EXTRAWORK_APPL_CODE="
					+ extWrkAppNo;
			Object[][] extraWorkDayObj = getSqlModel().getSingleResult(
					extraWorkDayQuery);

			if (extraWorkDayObj != null && extraWorkDayObj.length > 0) {

				for (int j = 0; j < extraWorkDayObj.length; j++) {

					String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
							+ String.valueOf(extraWorkDayObj[j][0]).substring(
									6, 10)
							+ " where ATT_DATE=to_date('"
							+ String.valueOf(extraWorkDayObj[j][0])
							+ "','dd-mm-yyyy') and ATT_EMP_ID='"
							+ empId
							+ " ' ";

					Object[][] selectObj = getSqlModel().getSingleResult(
							selectQry);

					if (selectObj != null && selectObj.length > 0) {
						String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
								+ String.valueOf(extraWorkDayObj[j][0])
										.substring(6, 10)
								+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,ATT_REG_STATUS_TWO='EW',IS_APPL_PROCESS='N'"
								+ " WHERE ATT_DATE=TO_DATE ( '"
								+ extraWorkDayObj[j][0]
								+ "','DD-MM-YYYY')"
								+ " AND ATT_EMP_ID=" + empId;

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

	

	public boolean rejectExtraWorkAttendenceData(String empId,
			String extWrkAppNo) {
		boolean result = false;
		try {

			String extraWorkDayQuery = " SELECT TO_CHAR(EXTRAWORK_DATE,'DD-MM-YYYY') FROM HRMS_EXTRAWORK_APPL_DTL "
					+ " WHERE EMP_ID ="
					+ empId
					+ "AND EXTRAWORK_APPL_CODE="
					+ extWrkAppNo;
			Object[][] extraWorkDayObj = getSqlModel().getSingleResult(
					extraWorkDayQuery);

			if (extraWorkDayObj != null && extraWorkDayObj.length > 0) {

				for (int j = 0; j < extraWorkDayObj.length; j++) {

					String selectQry = "SELECT  TO_CHAR(ATT_DATE,'DD-MM-YYYY') FROM HRMS_DAILY_ATTENDANCE_"
							+ String.valueOf(extraWorkDayObj[j][0]).substring(
									6, 10)
							+ " WHERE ATT_DATE=TO_DATE('"
							+ String.valueOf(extraWorkDayObj[j][0])
							+ "','DD-MM-YYYY') AND ATT_EMP_ID='"
							+ empId
							+ " ' ";

					Object[][] selectObj = getSqlModel().getSingleResult(
							selectQry);

					if (selectObj != null && selectObj.length > 0) {
						String myCardTimeActualDataUpdate = " UPDATE HRMS_DAILY_ATTENDANCE_"
								+ String.valueOf(extraWorkDayObj[j][0])
										.substring(6, 10)
								+ " SET ATT_REG_STATUS_ONE=ATT_STATUS_ONE,ATT_REG_STATUS_TWO='AB',IS_APPL_PROCESS='N'"
								+ " WHERE ATT_DATE=TO_DATE ( '"
								+ extraWorkDayObj[j][0]
								+ "','DD-MM-YYYY')"
								+ " AND ATT_EMP_ID=" + empId;

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
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull
	
	public String checkShiftHours(String empId,String applnId,String shiftWorkingHours,String shiftId) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm");
		Date extraEndTime=null, extraInTime=null;
		Object timeDiffObj[][]= new Object[1][1];
		String result = "";
		try {
			int check = 0;
			
			String query="SELECT TO_CHAR(HRMS_SHIFT.SFT_START_TIME,'HH24:MI') ,"+
						" TO_CHAR(HRMS_SHIFT.SFT_END_TIME,'HH24:MI'),SFT_NIGHT_FLAG "+
						" FROM HRMS_SHIFT WHERE HRMS_SHIFT.SHIFT_ID="+shiftId;
			Object shiftTimeObj[][] = getSqlModel().getSingleResult(query);
			

			 String extraWorkTimeQuery="SELECT TO_CHAR(EXTRAWORK_DATE,'DD-MM-YYYY'),EXTRAWORK_DAY,EXTRAWORK_DAY_TYPE, "
			    	+ " HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_STARTTIME,"
			    	+ " HRMS_EXTRAWORK_APPL_DTL.EXTRAWORK_ENDTIME "
			    	+ " FROM HRMS_EXTRAWORK_APPL_DTL  WHERE EMP_ID ="+empId+" AND EXTRAWORK_APPL_CODE="+applnId;
			
			 Object extraTimeObj[][] = getSqlModel().getSingleResult(extraWorkTimeQuery);
			 
			String[] startTime = String.valueOf(shiftTimeObj[0][0]).split(":");
			String[] endTime = String.valueOf(shiftTimeObj[0][1]).split(":");
			String[] diffHours = new String[2];
			diffHours[0] = String.valueOf(Integer.parseInt(endTime[0])
					- Integer.parseInt(startTime[0]));
			if (Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]) >= 0) {
				diffHours[1] = String.valueOf(Integer.parseInt(endTime[1])
						- Integer.parseInt(startTime[1]));
			} else {
				diffHours[1] = String.valueOf(Integer.parseInt(endTime[1])
						+ Integer.parseInt(startTime[1]));
				diffHours[0] = String
						.valueOf(Integer.parseInt(diffHours[0]) - 1);
			}
			String timeDifference = diffHours[0] + ":" + diffHours[1];

			long startTimeCheck = getMinute(shiftWorkingHours);
			long endTimeCheck = getMinute(timeDifference);
			
			String calculateTimeDiffQuery = "  SELECT TRIM(FLOOR(((TO_DATE('"
					+ String.valueOf(extraTimeObj[0][0]) + "-" + String.valueOf(extraTimeObj[0][4])
					+ "','dd-mm-yyyy-HH24:MI')+" + check + " -TO_DATE('"
					+ String.valueOf(extraTimeObj[0][0]) + "-"
					+ String.valueOf(extraTimeObj[0][3])
					+ "','dd-mm-yyyy-HH24:MI'))*24*60*60)/3600)"
					+ "  || ':' || " + "  FLOOR((((TO_DATE('"
					+ String.valueOf(extraTimeObj[0][0]) + "-" + String.valueOf(extraTimeObj[0][4])
					+ "','dd-mm-yyyy-HH24:MI')-TO_DATE('"
					+ String.valueOf(extraTimeObj[0][0]) + "-"
					+ String.valueOf(extraTimeObj[0][3])
					+ "','dd-mm-yyyy-HH24:MI'))*24*60*60) - "
					+ "   FLOOR(((TO_DATE('" + String.valueOf(extraTimeObj[0][0]) + "-"
					+ String.valueOf(extraTimeObj[0][4])
					+ "','dd-mm-yyyy-HH24:MI')-TO_DATE('"
					+ String.valueOf(extraTimeObj[0][0]) + "-"
					+ String.valueOf(extraTimeObj[0][3])
					+ "','dd-mm-yyyy-HH24:MI'))*24*60*60)/3600)*3600)/60)"
					+ " )TIME_DIFFERENCE FROM DUAL ";

			if (shiftTimeObj != null && shiftTimeObj.length >0) {
				if (shiftTimeObj[0][2].equals("Y")) {
					extraInTime = simpleDate.parse(String.valueOf(extraTimeObj[0][3]));
					extraEndTime = simpleDate.parse(String.valueOf(extraTimeObj[0][4]));
					Calendar workingHrsCal = Calendar.getInstance();
					workingHrsCal.setTime(extraEndTime);
					workingHrsCal.add(Calendar.HOUR, -extraInTime
							.getHours());
					workingHrsCal.add(Calendar.MINUTE, -extraInTime
							.getMinutes());
					workingHrsCal.add(Calendar.SECOND, -extraInTime
							.getSeconds());
					timeDiffObj[0][0]= simpleDate.format(workingHrsCal
							.getTime());					
				}else{
					 timeDiffObj = getSqlModel().getSingleResult(
								calculateTimeDiffQuery);
				}
			}
			long startEndTimeDiff = 0;
			long actualShiftHrs = 0;

			if (timeDiffObj != null && timeDiffObj.length > 0) {
				startEndTimeDiff = getMinute(String.valueOf(timeDiffObj[0][0]));				
				actualShiftHrs = getMinute(shiftWorkingHours);
									
					if (!((startEndTimeDiff) >= (actualShiftHrs / 2))) {
						result += "partialForExtraDayPayViolation";
					}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getMinute(String result) {
		int min = 0;
		try {
			if (result == null || result.equals("null") || result.equals("")) {
				return min;
			}// end of if
			else {
				if (result.contains(":")) {
					String[] chk = result.split(":");
					try {
						min = Integer.parseInt(chk[0]) * 60;
						min = min + Integer.parseInt(chk[1]);
					} catch (Exception e) {
					}
				}
			}// end of else
		} catch (Exception e) {
			e.printStackTrace();
		}

		return min;
	}
}
