package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.TerminationApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

/** * @author aa1380. * */
public class TerminationApprovalModel extends ModelBase {
	/**	 voStr. *	 */
	private final String voStr = "vo";
	/**	 ivoStr. *	 */
	private final String ivoStr = "ivo";
	/**	 otStr. *	 */
	private final String otStr = "ot";
	/**	 zeroStr. *	 */
	private final String zeroStr = "0";
	/**	 twentyStr. *	 */
	private final String twentyStr = "20";
	/**	 oneStr. *	 */
	private final String oneStr = "1";
	
	/**Used to get pending list of application.
	 * @param terApprBean : 
	 * @param request : 
	 */
	public void getPendingList(final TerminationApproval terApprBean, final HttpServletRequest request) {
		try {
			final List<TerminationApproval> pendingDataList = new ArrayList<TerminationApproval>();
			// Pending application Begins
			final String selQuery = " SELECT HRMS_D1_TERMINATION.TER_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), TER_ID, TER_STATUS " + 
					" FROM HRMS_D1_TERMINATION  " + 
					" INNER JOIN HRMS_EMP_OFFC  ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID)" + 
					" WHERE TER_STATUS = 'P' " + 
					" ORDER BY  HRMS_D1_TERMINATION.TER_TRACKING_NUMBER DESC";

			final Object[][] pendingListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndex = Utility.doPaging(terApprBean.getMyPage(), pendingListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = this.zeroStr;
				pageIndex[1] = this.twentyStr;
				pageIndex[2] = this.oneStr;
				pageIndex[3] = this.oneStr;
				pageIndex[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.oneStr)) {
				terApprBean.setMyPage(this.oneStr);
			}

			if (pendingListData != null && pendingListData.length > 0) {
				terApprBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final TerminationApproval beanItt = new TerminationApproval();
					beanItt.setTrackingNumIterator(Utility.checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setEmployeeNameIterator(Utility.checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setTerminationDateIterator(Utility.checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setTerminationHiddenID(Utility.checkNull(String.valueOf(pendingListData[i][3])));
					beanItt.setTerminationHiddenStatus(Utility.checkNull(String.valueOf(pendingListData[i][4])));
					pendingDataList.add(beanItt);
				}
				terApprBean.setPendingIteratorList(pendingDataList);
			}
			// Pending  application Ends
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to get approved list of application.
	 * @param terApprBean : 
	 * @param request : 
	 */
	public void getApprovedList(final TerminationApproval terApprBean, final HttpServletRequest request) {
		try {
			final List<TerminationApproval> approvedList = new ArrayList<TerminationApproval>();
			// Approved application Begins
			final String selQuery = " SELECT TER_TRACKING_NUMBER,  EMP_FNAME||' '||EMP_LNAME,  TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), TER_ID, TER_STATUS " + 
					" FROM   HRMS_D1_TERMINATION " + 
					" INNER  JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID)" + 
					" WHERE TER_STATUS IN('A','X') " + 
					" ORDER  BY  HRMS_D1_TERMINATION.TER_TRACKING_NUMBER  DESC";

			final Object[][] approvedListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndexApproved = Utility.doPaging(terApprBean.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = this.zeroStr;
				pageIndexApproved[1] = this.twentyStr;
				pageIndexApproved[2] = this.oneStr;
				pageIndexApproved[3] = this.oneStr;
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals(this.oneStr)) {
				terApprBean.setMyPageApproved(this.oneStr);
			}

			if (approvedListData != null && approvedListData.length > 0) {
				terApprBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final TerminationApproval beanItt = new TerminationApproval();
					beanItt.setTrackingNumIterator(Utility.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeNameIterator(Utility.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setTerminationDateIterator(Utility.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setTerminationHiddenID(Utility.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setTerminationHiddenStatus(Utility.checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				terApprBean.setApproveredIteratorList(approvedList);
			}
			// Approved application Ends

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to get rejected list of application.
	 * @param terApprBean : 
	 * @param request : 
	 */
	public void getRejectedList(final TerminationApproval terApprBean, final HttpServletRequest request) {
		final List<TerminationApproval> rejectedList = new ArrayList<TerminationApproval>();
		try {
			// Rejected application Begins
			final String selQuery = " SELECT TER_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), TER_ID, TER_STATUS " + 
					"  FROM  HRMS_D1_TERMINATION " + 
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID)" + 
					" WHERE TER_STATUS = 'R' " + 
					" ORDER BY HRMS_D1_TERMINATION.TER_TRACKING_NUMBER DESC";
			final Object[][] rejectedListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndexRejected = Utility.doPaging(terApprBean.getMyPageRejected(), rejectedListData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = this.zeroStr;
				pageIndexRejected[1] = this.twentyStr;
				pageIndexRejected[2] = this.oneStr;
				pageIndexRejected[3] = this.oneStr;
				pageIndexRejected[4] = "";
			}
			request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals(this.oneStr)) {
				terApprBean.setMyPageRejected(this.oneStr);
			}
			if (rejectedListData != null && rejectedListData.length > 0) {
				terApprBean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
					final TerminationApproval beanItt = new TerminationApproval();
					beanItt.setTrackingNumIterator(Utility.checkNull(String.valueOf(rejectedListData[i][0])));
					beanItt.setEmployeeNameIterator(Utility.checkNull(String.valueOf(rejectedListData[i][1])));
					beanItt.setTerminationDateIterator(Utility.checkNull(String.valueOf(rejectedListData[i][2])));
					beanItt.setTerminationHiddenID(Utility.checkNull(String.valueOf(rejectedListData[i][3])));
					beanItt.setTerminationHiddenStatus(Utility.checkNull(String.valueOf(rejectedListData[i][4])));
					rejectedList.add(beanItt);
				}
				terApprBean.setRejectedIteratorList(rejectedList);
			}
			// Rejected application Ends
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to view application details.
	 * @param trBean : 
	 * @param terminationHiddenID : 
	 */
	public void viewApplication(final TerminationApproval trBean, final String terminationHiddenID) {
		final String viewQuery = " SELECT TER_ID, TER_EMP_ID, OFFC1.EMP_TOKEN, OFFC1.EMP_FNAME||' '||OFFC1.EMP_LNAME, " + 
				" TER_ADDRESS, TER_ZIPCODE, TER_CITY, TER_STATE, " + 
				" DEPT_ID,TER_DEPT, EMP_RANK, RANK_NAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), " + 
				" TO_CHAR(TER_LAST_DAY_WORKED,'DD-MM-YYYY'), TER_DIFFER_DATE_EXPLAIN, TER_TERMINATION_TYPE, " + 
				" TER_TERMINATION_REASON, TER_ELIGIBLE_TO_REHIRE, TER_IF_NO_PRO_EXPLAIN,TER_VACATION_HOURS, " + 
				" TER_COMMENTS, TER_COMPLETED_BY, OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME, " + 
				" TO_CHAR(TER_COMPLETED_DATE,'DD-MM-YYYY  HH24:MI'), TER_STATUS,TER_IF_OTHER_REASON, TER_TRACKING_NUMBER ,MGR.EMP_TOKEN, MGR.EMP_FNAME||' '||MGR.EMP_MNAME||' '||MGR.EMP_LNAME, TER_MANG_NAME " + 
				" FROM HRMS_D1_TERMINATION " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID) " + 
				" LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = OFFC1.EMP_RANK) " + 
				" LEFT JOIN HRMS_D1_DEPARTMENT ON (HRMS_D1_DEPARTMENT.DEPT_ID = OFFC1.EMP_DEPT_NO) " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HRMS_D1_TERMINATION.TER_COMPLETED_BY) " + 
				" LEFT JOIN HRMS_EMP_OFFC MGR ON (MGR.EMP_ID = HRMS_D1_TERMINATION.TER_MANG_NAME) " + 
				" WHERE TER_ID =" + terminationHiddenID;

		final Object[][] viewQueryObj = this.getSqlModel().getSingleResult(viewQuery);
		if (viewQueryObj != null && viewQueryObj.length > 0) {
			trBean.setTerminationID(Utility.checkNull(String.valueOf(viewQueryObj[0][0])));
			trBean.setEmployeeNumber(Utility.checkNull(String.valueOf(viewQueryObj[0][1])));
			trBean.setEmployeeToken(Utility.checkNull(String.valueOf(viewQueryObj[0][2])));
			trBean.setEmployeeName(Utility.checkNull(String.valueOf(viewQueryObj[0][3])));
			trBean.setHomeAddress(Utility.checkNull(String.valueOf(viewQueryObj[0][4])));
			trBean.setZipCode(Utility.checkNull(String.valueOf(viewQueryObj[0][5])));
			trBean.setCityName(Utility.checkNull(String.valueOf(viewQueryObj[0][6])));
			trBean.setState(Utility.checkNull(String.valueOf(viewQueryObj[0][7])));
			trBean.setDeptID(Utility.checkNull(String.valueOf(viewQueryObj[0][8])));
			trBean.setDepetNumber(Utility.checkNull(String.valueOf(viewQueryObj[0][9])));
			trBean.setExecutiveID(Utility.checkNull(String.valueOf(viewQueryObj[0][10])));
			trBean.setExecutiveName(Utility.checkNull(String.valueOf(viewQueryObj[0][11])));
			trBean.setTerminationDate(Utility.checkNull(String.valueOf(viewQueryObj[0][12])));
			trBean.setLastDayWorkDate(Utility.checkNull(String.valueOf(viewQueryObj[0][13])));
			trBean.setIfTerDateAndLastDayWorkDateDiffer(Utility.checkNull(String.valueOf(viewQueryObj[0][14])));

			if ("V".equals(Utility.checkNull(String.valueOf(viewQueryObj[0][15])))) {
				trBean.setTerminationType(this.voStr);
				trBean.setTerminationTypeRadioOptionValue(this.voStr);
				trBean.setVoluntaryTerminationReason(Utility.checkNull(String.valueOf(viewQueryObj[0][16])));
			} else if ("N".equals(Utility.checkNull(String.valueOf(viewQueryObj[0][15])))) {
				trBean.setTerminationType(this.ivoStr);
				trBean.setTerminationTypeRadioOptionValue(this.ivoStr);
				trBean.setInVoluntaryTerminationReason(Utility.checkNull(String.valueOf(viewQueryObj[0][16])));
			} else {
				trBean.setTerminationType(this.otStr);
				trBean.setTerminationTypeRadioOptionValue(this.otStr);
				trBean.setOtherTerminationReason(Utility.checkNull(String.valueOf(viewQueryObj[0][16])));
			}

			trBean.setEligibleToRehire(Utility.checkNull(String.valueOf(viewQueryObj[0][17])));
			trBean.setIfNoOrProvisional(Utility.checkNull(String.valueOf(viewQueryObj[0][18])));
			trBean.setVacationHrsTaken(Utility.checkNull(String.valueOf(viewQueryObj[0][19])));
			trBean.setCommentsEntered(Utility.checkNull(String.valueOf(viewQueryObj[0][20])));
			trBean.setCompletedByID(Utility.checkNull(String.valueOf(viewQueryObj[0][21])));
			trBean.setCompletedByName(Utility.checkNull(String.valueOf(viewQueryObj[0][22])));
			trBean.setCompletedDate(Utility.checkNull(String.valueOf(viewQueryObj[0][23])));
			trBean.setStatus(Utility.checkNull(String.valueOf(viewQueryObj[0][24])));
			trBean.setIfOtherTerminationReasonTextArea(Utility.checkNull(String.valueOf(viewQueryObj[0][25])));
			trBean.setTrackingNumber(Utility.checkNull(String.valueOf(viewQueryObj[0][26])));
			trBean.setManagerToken(Utility.checkNull(String.valueOf(viewQueryObj[0][27])));
			trBean.setManagerName(Utility.checkNull(String.valueOf(viewQueryObj[0][28])));
			trBean.setManagerCode(Utility.checkNull(String.valueOf(viewQueryObj[0][29])));
			this.getApproverCommentList(trBean, terminationHiddenID);
		}
	}

	/**Used to update application status.
	 * @param terApprBean : 
	 * @param status : 
	 * @param applicationId : 
	 * @return boolean
	 */
	public boolean updateStatus(final TerminationApproval terApprBean, final String status, final String applicationId) {
		final String approverComments = terApprBean.getApproverComments();
		final String approverID = terApprBean.getUserEmpId();
		boolean result = false;
		try {
			final String changeStatusQuery = "UPDATE HRMS_D1_TERMINATION SET TER_STATUS = '" + status + "'" + " WHERE  TER_ID = " + applicationId;
			result = this.getSqlModel().singleExecute(changeStatusQuery);
			this.insertApproverComments(applicationId, approverID, approverComments, status);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		if ("A".equals(status)) {
			this.updateTerminationData(applicationId);
		}
		return result;
	}

	/**Used to update employee status as terminated.
	 * @param applicationId : 
	 */
	private void updateTerminationData(final String applicationId) {
		final String terminationDataSql = " SELECT TER_EMP_ID,TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY') " + 
				" FROM HRMS_D1_TERMINATION TERMINATION WHERE TERMINATION.TER_ID = " + applicationId;
		final Object[][] terminationDataObj = this.getSqlModel().getSingleResult(terminationDataSql);
		final String empId = String.valueOf(terminationDataObj[0][0]);
		final String terminationDate = String.valueOf(terminationDataObj[0][1]);
		final String updateOffcialDetailsSql = " UPDATE HRMS_EMP_OFFC SET EMP_STATUS = 'E', " + 
										 " EMP_LEAVE_DATE=TO_DATE('" + terminationDate + "','DD-MM-YYYY') WHERE EMP_ID = " + empId;
		this.getSqlModel().singleExecute(updateOffcialDetailsSql);

	}

	/**Used to insert approver's comments into table.
	 * @param applicationId : 
	 * @param approverID : 
	 * @param approverComments :
	 * @param statusToUpdate : 
	 */
	private void insertApproverComments(final String applicationId,
			final String approverID, final String approverComments, final String statusToUpdate) {
		try {
			final String insertSql = " INSERT INTO HRMS_D1_TER_DATA_PATH (TER_PATH_ID, TER_ID, TER_APPROVER, TER_COMMENTS, TER_STATUS) " + 
					" VALUES ((SELECT NVL(MAX(TER_PATH_ID), 0) + 1 FROM HRMS_D1_TER_DATA_PATH), ?, ?, ?, ?) ";
			final Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = approverID;
			insertObj[0][2] = approverComments;
			insertObj[0][3] = statusToUpdate;
			this.getSqlModel().singleExecute(insertSql, insertObj);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to get approver details.
	 * @param trBean : 
	 * @param terminationID : 
	 */
	public void getApproverCommentList(final TerminationApproval trBean, final String terminationID) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, TER_COMMENTS, " + 
				" TO_CHAR(TER_APPROVED_DATE, 'DD-MM-YYYY') AS APPROVED_DATE, " + 
				" DECODE(TER_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS " + 
				" FROM HRMS_D1_TER_DATA_PATH " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_TER_DATA_PATH.TER_APPROVER) " + 
				" WHERE TER_ID = " + terminationID + " ORDER BY TER_ID DESC";

		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<TerminationApproval> approverList = new ArrayList<TerminationApproval>();
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final TerminationApproval innerBean = new TerminationApproval();
				innerBean.setApprName(Utility.checkNull(String.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(Utility.checkNull(String.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(Utility.checkNull(String.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(Utility.checkNull(String.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			trBean.setApproverCommentList(approverList);
		}
	}

	/**Used to check whether current login user id HR person or not.
	 * @param userId : 
	 * @return boolean
	 */
	public boolean isHRApprover(final String userId) {
		final String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'H' AND APP_SETTINGS_EMP_ID = " + userId;
		final Object[][] hrApproverObj = this.getSqlModel().getSingleResult(hrApproverSql);
		if (hrApproverObj != null && hrApproverObj.length > 0) {
			return true;
		}
		return false;
	}


	/**
	 * Method : insertIntoApplicationSecurity.
	 * Purpose : This methos id used to insert one record into application security application.
	 * @param terApprBean : instance of TerminationApproval
	 */
	public void insertIntoApplicationSecurity(final TerminationApproval terApprBean) {
		try {
			final Object[][] applnSecIdObj = this.getSqlModel().getSingleResult("SELECT NVL(MAX(APPLN_SEC_ID), 0) + 1 FROM HRMS_D1_APPLN_SECURITY");
			final String applicationId = String.valueOf(applnSecIdObj[0][0]);
			final String applicationSecurityTrackingNumber = this.getTrackingNo(terApprBean.getCompletedByID());
			final String comments = "The last working day of " + terApprBean.getEmployeeName() + " is " + terApprBean.getTerminationDate() + 
							  ". \n" + 
							  "Please delete/disable his access to all the systems/applications. ";

			final Object[][] mgrDetails = this.managerDetails(terApprBean.getManagerCode());
			final Object[][] employeeDetails = this.employeeDetails(terApprBean.getEmployeeNumber());
			final String employeeType = this.employeeTypeDetails(Utility.checkNull(String.valueOf(employeeDetails[0][8])));

			final Object[][] insertObj = new Object[1][32];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = "D";
			insertObj[0][2] = comments;
			insertObj[0][3] = "P";
			insertObj[0][4] = terApprBean.getManagerCode();
			insertObj[0][5] = applicationSecurityTrackingNumber;
			insertObj[0][6] = terApprBean.getTerminationDate(); //Termination Date
			insertObj[0][7] = terApprBean.getManagerCode(); // Manager ID
			insertObj[0][8] = Utility.checkNull(String.valueOf(mgrDetails[0][0])); //Manager Division
			insertObj[0][9] = Utility.checkNull(String.valueOf(mgrDetails[0][1])); //Manager Designation
			insertObj[0][10] = Utility.checkNull(String.valueOf(mgrDetails[0][2])); //Manager Department
			insertObj[0][11] = Utility.checkNull(String.valueOf(mgrDetails[0][3])); //Manager City
			insertObj[0][12] = Utility.checkNull(String.valueOf(mgrDetails[0][4])); //Manager State
			insertObj[0][13] = Utility.checkNull(String.valueOf(mgrDetails[0][5])); //Manager Pincode
			insertObj[0][14] = Utility.checkNull(String.valueOf(mgrDetails[0][6])); //Manager Work Phone
			insertObj[0][15] = Utility.checkNull(String.valueOf(mgrDetails[0][7])); //Manager Extension
			insertObj[0][16] = Utility.checkNull(String.valueOf(mgrDetails[0][8])); //Manager FAX
			insertObj[0][17] = Utility.checkNull(String.valueOf(mgrDetails[0][9])); //Manager e-mail

			insertObj[0][18] = Utility.checkNull(String.valueOf(employeeDetails[0][0])); //Emp -ID
			insertObj[0][19] = Utility.checkNull(String.valueOf(employeeDetails[0][1])); //Emp - Designation
			insertObj[0][20] = Utility.checkNull(String.valueOf(employeeDetails[0][2])); //Emp - e-mail
			insertObj[0][21] = Utility.checkNull(String.valueOf(employeeDetails[0][3])); //Emp - phone
			insertObj[0][22] = Utility.checkNull(String.valueOf(employeeDetails[0][4])); //Emp - Extension
			insertObj[0][23] = Utility.checkNull(String.valueOf(employeeDetails[0][5])); //Emp - Fax
			insertObj[0][24] = Utility.checkNull(String.valueOf(employeeDetails[0][6])); //Emp - Token
			insertObj[0][25] = Utility.checkNull(String.valueOf(employeeDetails[0][7])); //Emp - Name
			insertObj[0][26] = Utility.checkNull(employeeType); //Emp - Type

			insertObj[0][27] = Utility.checkNull(String.valueOf(mgrDetails[0][0])); //Emp  Division
			insertObj[0][28] = Utility.checkNull(String.valueOf(mgrDetails[0][2])); //Emp Department
			insertObj[0][29] = Utility.checkNull(String.valueOf(mgrDetails[0][3])); //Emp City
			insertObj[0][30] = Utility.checkNull(String.valueOf(mgrDetails[0][4])); //Emp State
			insertObj[0][31] = Utility.checkNull(String.valueOf(mgrDetails[0][5])); //Emp Pincode

			final String insertQuery = " INSERT INTO HRMS_D1_APPLN_SECURITY (APPLN_SEC_ID, APPLN_SEC_REQ_TYPE, APPLN_SEC_COMMENTS, APPLN_SEC_STATUS, APPLN_SEC_INITIATOR, APPLN_SEC_TRACKING_NO, APPLN_SEC_EMP_EXP_DATE, " + 
					" APPLN_SEC_MGR_ID, APPLN_SEC_MGR_DIV, APPLN_SEC_MGR_DESGN, APPLN_SEC_MGR_DEPT, APPLN_SEC_MGR_CITY, APPLN_SEC_MGR_STATE, APPLN_SEC_MGR_PIN, APPLN_SEC_MGR_WPHONE, APPLN_SEC_MGR_EXT, APPLN_SEC_MGR_FAX, APPLN_SEC_MGR_EMAIL, " + 
					" APPLN_SEC_EMP_ID, APPLN_SEC_EMP_DESGN, APPLN_SEC_EMP_EMAIL, APPLN_SEC_EMP_WORKPHONE, APPLN_SEC_EMP_EXTENSION, APPLN_SEC_EMP_FAX, APPLN_SEC_EMP_TOKEN, APPLN_SEC_EMP_NAME, APPLN_SEC_EMP_TYPE, " + 
					" APPLN_SEC_EMP_DIVISION, APPLN_SEC_EMP_DEPARTMENT, APPLN_SEC_EMP_CITY, APPLN_SEC_EMP_STATE, APPLN_SEC_EMP_PIN, " + 
					" APPLN_SEC_REQ_DATE ) " + 
					" VALUES (?, ?, ?, ?, ?, ?, TO_DATE(?, 'DD-MM-YYYY'), " + 
					" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + 
					" ?, ?, ?, ?, ?, ?, ?, ?, ?, " + 
					" ?, ?, ?, ?, ?, " + 
					" TO_DATE(SYSDATE, 'DD-MM-YYYY')) ";

			this.getSqlModel().singleExecute(insertQuery, insertObj);
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to get tracking number.
	 * @param empId - Used in model.generateApplicationCode()
	 * @return - trackingNo
	 */
	private String getTrackingNo(final String empId) {
		String trackingNo = "";
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		final int day = cal.get(Calendar.DAY_OF_MONTH);
		final int month = cal.get(Calendar.MONTH) + 1;
		final int year = cal.get(Calendar.YEAR);

		final String strDay = day < 10 ? this.zeroStr + day : String.valueOf(day);
		final String strMonth = month < 10 ? this.zeroStr + month : String.valueOf(month);
		final String strYear = String.valueOf(year);
		final String dash = "-";
		final String sysDate = strDay + dash + strMonth + dash + strYear;
		try {
			final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
			model.initiate(context, session);
			trackingNo = model.generateApplicationCode("", "D1-APPLN_SEC_REQ", empId, sysDate);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return trackingNo;
	}

	/**
	 * Method to get manager details.
	 * @param mgrId - Used to get manager details
	 * @return - Object[][] containing manager data
	 */
	public Object[][] managerDetails(final String mgrId) {
		final String mgrDetailsSql = "SELECT DIV.DIV_NAME AS DIVISON, RANK.RANK_NAME AS DESIGNATION, DEPT.DEPT_NAME AS DEPARTMENT, " + 
				" CITY.LOCATION_NAME AS CITY, STATE.LOCATION_NAME AS STATE, ADDR.ADD_PINCODE AS PINCODE, ADDR.ADD_PH1 AS WORKPHONE, " + 
				" ADDR.ADD_EXTENSION AS EXTENSION, ADDR.ADD_FAX AS FAX, ADDR.ADD_EMAIL AS EMAIL FROM HRMS_EMP_OFFC OFFC " + 
				" LEFT JOIN HRMS_DIVISION DIV ON(DIV.DIV_ID = OFFC.EMP_DIV) " + 
				" LEFT JOIN HRMS_RANK RANK ON(RANK.RANK_ID = OFFC.EMP_RANK) " + 
				" LEFT JOIN HRMS_DEPT DEPT ON(DEPT.DEPT_ID = OFFC.EMP_DEPT) " + 
				" LEFT JOIN HRMS_EMP_ADDRESS ADDR ON (ADDR.EMP_ID = OFFC.EMP_ID AND ADDR.ADD_TYPE = 'P') " + 
				" LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = ADDR.ADD_CITY) " + 
				" LEFT JOIN HRMS_LOCATION STATE ON(STATE.LOCATION_CODE = CITY.LOCATION_PARENT_CODE) WHERE OFFC.EMP_ID = " + mgrId;
		return this.getSqlModel().getSingleResult(mgrDetailsSql);
	}

	/**This method is used to get employee details.
	 * @param employeeNumber : employeeNumber
	 * @return Object
	 */
	private Object[][] employeeDetails(final String employeeNumber) {
		final String employeeDetailsSql = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_RANK.RANK_NAME, HRMS_EMP_ADDRESS.ADD_EMAIL, HRMS_EMP_ADDRESS.ADD_PH1, HRMS_EMP_ADDRESS.ADD_EXTENSION, HRMS_EMP_ADDRESS.ADD_FAX, " + 
				" HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME ||' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME,  HRMS_EMP_OFFC.EMP_TYPE" + 
				" FROM HRMS_EMP_OFFC " + 
				" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " + 
				" LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE = 'P') " + 
				" WHERE EMP_ID = " + employeeNumber;
		return this.getSqlModel().getSingleResult(employeeDetailsSql);
	}

	/**This method is used to get employee type details.
	 * @param empTypeID :
	 * @return String
	 */
	private String employeeTypeDetails(final String empTypeID) {
		String result = "";
		if ("6".equals(empTypeID) || "8".equals(empTypeID)) {
			result = "R";
		} else if ("7".equals(empTypeID) || "9".equals(empTypeID)) {
			result = "T";
		} else {
			result = "V";
		}
		return result;
	}

}
