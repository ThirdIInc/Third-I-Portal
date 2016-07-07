package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.CBTFormBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author aa1380
 * 
 */
public class CBTFormModel extends ModelBase {
	/** logger. * */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CBTFormModel.class);

	/**	 zero. *	 */
	private final  String zero = "0";
	/**	 twenty. *	 */
	private final String twenty = "20";
	/**	 one. *	 */
	private final String one = "1";
	
	/** stringCanada. * */
	private final String stringCanada = "canada";
	
	/** stringUS. * */
	private final String stringUS = "us";
	
	/** US. * */
	private final String uS = "U";
	
	/** stringC . * */
	private final String stringC = "C";
	
	/**
	 * Method : isCurrentUser. 
	 * Purpose : For getting current user information
	 * @param userId : Current User ID
	 * @return : boolean
	 */
	public boolean isCurrentUser(final String userId) {
		final String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId;
		final Object[][] currentUserObj = this.getSqlModel().getSingleResult(currentUserSql);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method : f9vendor.
	 * Purpose : For getting vendor list
	 * @param bean : CBTFormBean
	 * @param request : HttpServletRequest
	 * @param userId : Current User ID
	 */
	public void getPendingList(final CBTFormBean bean, final HttpServletRequest request, final String userId) {
		try {
			Object[][] draftListData = null;
			final List<CBTFormBean> draftList = new ArrayList<CBTFormBean>();

			// For drafted application Begins
			/*String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
					+ " FROM HRMS_D1_CBT_REQUEST  "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
					+ " WHERE CBT_STATUS = 'D' AND CBT_COMPLETED_BY = "
					+ userId + " " + "  ORDER BY CBT_COMPLETED_ON DESC ";
			draftListData = getSqlModel().getSingleResult(selQuery);*/
			draftListData = this.getAppropriateListFromQuery("'D'", userId);

			final String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(),
					draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = this.zero;
				pageIndexDrafted[1] = this.twenty;
				pageIndexDrafted[2] = this.one;
				pageIndexDrafted[3] = this.one;
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals(this.one)) {
				bean.setMyPage(this.one);
			}
				
			if (draftListData != null && draftListData.length > 0) {
				bean.setDraftListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					final CBTFormBean beanItt = new CBTFormBean();
					beanItt.setTrackingNumberItr(this.checkNull(String.valueOf(draftListData[i][0])));
					beanItt.setEmpNameItr(this.checkNull(String.valueOf(draftListData[i][1])));
					beanItt.setApplicationDateItr(this.checkNull(String.valueOf(draftListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(draftListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(draftListData[i][4])));
					draftList.add(beanItt);
				}
				bean.setDraftList(draftList);
			}
			// For drafted application Ends

			// For in-Process application Begins
			Object[][] inProcessListData = null;
			final List<CBTFormBean> inProcessList = new ArrayList<CBTFormBean>();

			/*String pendingQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, " + " TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS " + 
					" FROM HRMS_D1_CBT_REQUEST  LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) " + 
					+ " WHERE CBT_STATUS IN ('P','F') AND CBT_COMPLETED_BY = " + userId + " " + "  ORDER BY CBT_COMPLETED_ON DESC ";
			inProcessListData = getSqlModel().getSingleResult(pendingQuery);*/
			inProcessListData = this.getAppropriateListFromQuery("'P','F'", userId);
			final String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(), inProcessListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexInProcess[0] = this.zero;
				pageIndexInProcess[1] = this.twenty;
				pageIndexInProcess[2] = this.one;
				pageIndexInProcess[3] = this.one;
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals(this.one)) {
				bean.setMyPageInProcess(this.one);
			}
				

			if (inProcessListData != null && inProcessListData.length > 0) {
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					final CBTFormBean beanItt = new CBTFormBean();
					beanItt.setTrackingNumberItr(this.checkNull(String.valueOf(inProcessListData[i][0])));
					beanItt.setEmpNameItr(this.checkNull(String.valueOf(inProcessListData[i][1])));
					beanItt.setApplicationDateItr(this.checkNull(String.valueOf(inProcessListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(inProcessListData[i][4])));
					inProcessList.add(beanItt);
				}
				bean.setInProcessApplicationList(inProcessList);
				bean.setInProcessListLength(true);
			}

			// For in-Process application Ends

			// Sent-Back application Begins

			Object[][] sentBackListData = null;
			final List<CBTFormBean> sentBackVoucherList = new ArrayList<CBTFormBean>();

			/*String sentBackQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
					+ " FROM HRMS_D1_CBT_REQUEST  "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
					+ " WHERE CBT_STATUS = 'B' AND CBT_COMPLETED_BY = "
					+ userId + " " + "  ORDER BY CBT_COMPLETED_ON DESC ";

			sentBackListData = getSqlModel().getSingleResult(sentBackQuery);*/
			sentBackListData = this.getAppropriateListFromQuery("'B'", userId);
			final String[] pageIndexSentBack = Utility.doPaging(bean.getMyPageSentBack(), sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = this.zero;
				pageIndexSentBack[1] = this.twenty;
				pageIndexSentBack[2] = this.one;
				pageIndexSentBack[3] = this.one;
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String.valueOf(pageIndexSentBack[3])));
			if (pageIndexSentBack[4].equals(this.one)) {
				bean.setMyPageSentBack(this.one);
			}

			if (sentBackListData != null && sentBackListData.length > 0) {
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer.parseInt(pageIndexSentBack[1]); i++) {
					final CBTFormBean beanItt = new CBTFormBean();
					beanItt.setTrackingNumberItr(this.checkNull(String.valueOf(sentBackListData[i][0])));
					beanItt.setEmpNameItr(this.checkNull(String.valueOf(sentBackListData[i][1])));
					beanItt.setApplicationDateItr(this.checkNull(String.valueOf(sentBackListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(sentBackListData[i][4])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSendBackList(sentBackVoucherList);
				bean.setSendBackLength(true);
			}
			// Sent-Back application Ends

		} catch (final Exception e) {
			this.logger.info("Exception occured in getPendingList : " + e);
		}
	}

	/**
	 * Method : getApprovedList.
	 * Purpose : For getting approved list
	 * @param bean : CBTFormBean
	 * @param request : HttpServletRequest
	 * @param userId : Current User ID
	 */
	public void getApprovedList(final CBTFormBean bean, final HttpServletRequest request, final String userId) {
		try {
			Object[][] approvedListData = null;
			final List<CBTFormBean> approvedList = new ArrayList<CBTFormBean>();

			// Approved application Begins
			/*String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
					+ " FROM HRMS_D1_CBT_REQUEST  "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
					+ " WHERE CBT_STATUS = 'A' AND CBT_COMPLETED_BY = "
					+ userId + " " + "  ORDER BY CBT_COMPLETED_ON DESC ";

			approvedListData = this.getSqlModel().getSingleResult(selQuery);*/
			approvedListData = this.getAppropriateListFromQuery("'A'", userId);

			final String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = this.zero;
				pageIndexApproved[1] = this.twenty;
				pageIndexApproved[2] = this.one;
				pageIndexApproved[3] = this.one;
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals(this.one)) {
				bean.setMyPageApproved(this.one);
			}
				
			if (approvedListData != null && approvedListData.length > 0) {
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final CBTFormBean beanItt = new CBTFormBean();
					beanItt.setTrackingNumberItr(this.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setEmpNameItr(this.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setApplicationDateItr(this.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				bean.setApprovedListLength(true);
				bean.setApprovedList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins
			Object[][] approvedCancellationListData = null;
			final List<CBTFormBean> approvedCancellationList = new ArrayList<CBTFormBean>();

			/*String approvedcancellationQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
					+ " TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS, "
					+ " FROM HRMS_D1_CBT_REQUEST  "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
					+ " WHERE CBT_STATUS = 'X' AND CBT_COMPLETED_BY = "
					+ userId + " " + "  ORDER BY CBT_COMPLETED_ON DESC ";

			approvedCancellationListData = this.getSqlModel().getSingleResult(
					approvedcancellationQuery);*/
			approvedCancellationListData = this.getAppropriateListFromQuery("'X'", userId);

			final String[] pageIndexApprovedCancel = Utility.doPaging(bean.getMyPageApprovedCancel(), approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = this.zero;
				pageIndexApprovedCancel[1] = this.twenty;
				pageIndexApprovedCancel[2] = this.one;
				pageIndexApprovedCancel[3] = this.one;
				pageIndexApprovedCancel[4] = "";
			}

			request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if (pageIndexApprovedCancel[4].equals(this.one)) {
				bean.setMyPageApprovedCancel(this.one);
			}

			if (approvedCancellationListData != null && approvedCancellationListData.length > 0) {
				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer.parseInt(pageIndexApprovedCancel[1]); i++) {
					final CBTFormBean beanItt = new CBTFormBean();
					beanItt.setTrackingNumberItr(this.checkNull(String.valueOf(approvedCancellationListData[i][0])));
					beanItt.setEmpNameItr(this.checkNull(String.valueOf(approvedCancellationListData[i][1])));
					beanItt.setApplicationDateItr(this.checkNull(String.valueOf(approvedCancellationListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(approvedCancellationListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(approvedCancellationListData[i][4])));
					approvedCancellationList.add(beanItt);
				}
				bean.setApprovedCancelListLength(true);
				bean.setApprovedCancelList(approvedCancellationList);
			}
			// Approved cancellation application Ends
		} catch (final Exception e) {
			this.logger.info("Exception occured in getApprovedList : " + e);
		}
	}

	/**
	 * Method : getRejectedList.
	 * Purpose : For getting status wise list of applications.
	 * @param bean : CBTFormBean
	 * @param request : HttpServletRequest
	 * @param userId : String
	 */
	public void getRejectedList(final CBTFormBean bean, final HttpServletRequest request, final String userId) {
		Object[][] rejectedListData = null;
		final List<CBTFormBean> rejectedList = new ArrayList<CBTFormBean>();

		// Rejected application Begins
		/*String rejectQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
				+ " TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
				+ " FROM HRMS_D1_CBT_REQUEST  "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
				+ " WHERE CBT_STATUS = 'R' AND CBT_COMPLETED_BY = "
				+ userId
				+ " " + "  ORDER BY CBT_COMPLETED_ON DESC ";

		rejectedListData = this.getSqlModel().getSingleResult(rejectQuery);*/
		rejectedListData = this.getAppropriateListFromQuery("'R'", userId);

		final String[] pageIndexRejected = Utility.doPaging(bean.getMyPageRejected(), rejectedListData.length, 20);
		if (pageIndexRejected == null) {
			pageIndexRejected[0] = this.zero;
			pageIndexRejected[1] = this.twenty;
			pageIndexRejected[2] = this.one;
			pageIndexRejected[3] = this.one;
			pageIndexRejected[4] = "";
		}

		request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
		request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
		if (pageIndexRejected[4].equals(this.one)) {
			bean.setMyPageRejected(this.one);
		}
			
		if (rejectedListData != null && rejectedListData.length > 0) {
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
				final CBTFormBean beanItt = new CBTFormBean();
				beanItt.setTrackingNumberItr(this.checkNull(String.valueOf(rejectedListData[i][0])));
				beanItt.setEmpNameItr(this.checkNull(String.valueOf(rejectedListData[i][1])));
				beanItt.setApplicationDateItr(this.checkNull(String.valueOf(rejectedListData[i][2])));
				beanItt.setCbtHiddenID(this.checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setHiddenStatus(this.checkNull(String.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			bean.setRejectListLength(true);
			bean.setRejectList(rejectedList);
		}
		// Rejected application Ends

		// Rejected cancellation application Begins
		Object[][] rejectedCancellationListData = null;
		final List<CBTFormBean> rejectedCancellationList = new ArrayList<CBTFormBean>();

		/*String rejectcancellationQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
				+ " TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
				+ " FROM HRMS_D1_CBT_REQUEST  "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
				+ " WHERE CBT_STATUS = 'Z' AND CBT_COMPLETED_BY = "
				+ userId
				+ " " + "  ORDER BY CBT_COMPLETED_ON DESC ";

		rejectedCancellationListData = this.getSqlModel().getSingleResult(
				rejectcancellationQuery);*/
		rejectedCancellationListData = this.getAppropriateListFromQuery("'Z'", userId);

		final String[] pageIndexRejectedCancellation = Utility.doPaging(bean.getMyPageCancelRejected(),
				rejectedCancellationListData.length, 20);
		if (pageIndexRejectedCancellation == null) {
			pageIndexRejectedCancellation[0] = this.zero;
			pageIndexRejectedCancellation[1] = this.twenty;
			pageIndexRejectedCancellation[2] = this.one;
			pageIndexRejectedCancellation[3] = this.one;
			pageIndexRejectedCancellation[4] = "";
		}

		request.setAttribute("totalRejectedCancellationPage", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[2])));
		request.setAttribute("rejectedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[3])));
		if (pageIndexRejectedCancellation[4].equals(this.one)) {
			bean.setMyPageCancelRejected(this.one);
		}	
		if (rejectedCancellationListData != null && rejectedCancellationListData.length > 0) {
			for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer.parseInt(pageIndexRejectedCancellation[1]); i++) {
				final CBTFormBean beanItt = new CBTFormBean();
				beanItt.setTrackingNumberItr(this.checkNull(String.valueOf(rejectedCancellationListData[i][0])));
				beanItt.setEmpNameItr(this.checkNull(String.valueOf(rejectedCancellationListData[i][1])));
				beanItt.setApplicationDateItr(this.checkNull(String.valueOf(rejectedCancellationListData[i][2])));
				beanItt.setCbtHiddenID(this.checkNull(String.valueOf(rejectedCancellationListData[i][3])));
				beanItt.setHiddenStatus(this.checkNull(String.valueOf(rejectedCancellationListData[i][4])));
				rejectedCancellationList.add(beanItt);
			}
			bean.setRejectCancelListLength(true);
			bean.setRejectCancelList(rejectedCancellationList);
		}
		// Rejected cancellation application Ends
	}

	// End List

	/**
	 * Method : checkNull.
	 * Purpose : For checking whether given string is null OR not
	 * @param result : result
	 * @return : String
	 */
	public String checkNull(final String result) {
		if (result == null || "".equals(result) || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * Method : getEmployeeDetails.
	 * Purpose : For getting vendor list
	 * @param empId : Current USer ID
	 * @param bean : CBTFormBean
	 */
	public void getEmployeeDetails(final String empId, final CBTFormBean bean) {
		try {
			final CBTFormModel model = new CBTFormModel();
			model.initiate(context, session);
			final String query = "SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " + 
					 " HRMS_D1_DEPARTMENT.DEPT_NUMBER , HRMS_DEPT.DEPT_NAME " + 
					 " FROM HRMS_EMP_OFFC " + 
					 " LEFT JOIN HRMS_D1_DEPARTMENT ON(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) " + 
					 " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " + 
					 " WHERE HRMS_EMP_OFFC.EMP_ID = " + bean.getUserEmpId();
			final Object[][] queryObj = model.getSqlModel().getSingleResult(query);
			if (queryObj != null && queryObj.length > 0) {
				bean.setEmployeeID(this.checkNull(String.valueOf(queryObj[0][0])));
				bean.setEmployeeToken(this.checkNull(String.valueOf(queryObj[0][1])));
				bean.setEmployeeName(this.checkNull(String.valueOf(queryObj[0][2])));
				bean.setDeptNo(this.checkNull(String.valueOf(queryObj[0][3])));
				bean.setDeptName(this.checkNull(String.valueOf(queryObj[0][4])));
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in getEmployeeDetails : " + e);
		}
	}

	/**
	 * Method : getReportingManager.
	 * Purpose : For getting reporting manager
	 * @param cbtBean : CBTFormBean
	 */
	public void getReportingManager(final CBTFormBean cbtBean) {
		try {
			final String query = "SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID=(SELECT EMP_REPORTING_OFFICER FROM HRMS_EMP_OFFC WHERE EMP_ID = " + cbtBean.getEmployeeID() + " )";
			final Object[][] queryObj = this.getSqlModel().getSingleResult(query);
			if (queryObj != null && queryObj.length > 0) {
				cbtBean.setDefaultManagerID(this.checkNull(String.valueOf(queryObj[0][0])));
				cbtBean.setDefaultManagerToken(this.checkNull(String.valueOf(queryObj[0][1])));
				cbtBean.setDefaultManagerName(this.checkNull(String.valueOf(queryObj[0][2])));

				cbtBean.setChangeMyManagerID("");
				cbtBean.setChangeMyManagerToken("");
				cbtBean.setChangeMyManagerName("");
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in getReportingManager : " + e);
		}
	}

	/**
	 * Method : save.
	 * Purpose : For saving records
	 * @param bean : CBTFormBean
	 * @return : boolean
	 */
	public boolean save(final CBTFormBean bean) {
		boolean result = false;
		try {
			// Tracking Number Begins
			final String trackingQuery = "SELECT NVL(MAX(VOUCHER_REQUEST_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(VOUCHER_REQUEST_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_REQ_BACK_OUT	";
			final Object[][] trackingObj = this.getSqlModel().getSingleResult(trackingQuery);
			if (trackingObj != null && trackingObj.length > 0) {
				try {
					final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					final String autoIncrementApplicationCode = model.generateApplicationCode(String.valueOf(trackingObj[0][1]), "D1-CBT", 
							bean.getUserEmpId(), String.valueOf(trackingObj[0][2]));
					bean.setTrackingNumber(this.checkNull(autoIncrementApplicationCode));
					model.terminate();
				} catch (final Exception e) {
					this.logger.error("Exception occured in save : " + e);
				}
			}
			// Tracking Number Ends

			String approverCode = "";
			final Object[][] insertObj = new Object[1][20];
			insertObj[0][0] = bean.getEmployeeID();
			insertObj[0][1] = bean.getDeptName();
			insertObj[0][2] = bean.getDeptNo();
			insertObj[0][3] = bean.getCourseNo();
			if (bean.getLocationRadioValue().equals(this.stringUS)) {
				insertObj[0][4] = this.uS;
			} else {
				insertObj[0][4] = this.stringC;
			}
			insertObj[0][5] = bean.getAddress();
			insertObj[0][6] = bean.getCity();
			insertObj[0][7] = bean.getState();
			insertObj[0][8] = bean.getProvidence();
			insertObj[0][9] = bean.getCountry();
			insertObj[0][10] = bean.getZip();
			insertObj[0][11] = bean.getPhNo();
			insertObj[0][12] = bean.getFaxNo();
			insertObj[0][13] = bean.getEmailAddress();

			if (!"".equals(bean.getChangeMyManagerID()) && bean.getChangeMyManagerID() != null) {
				approverCode = bean.getChangeMyManagerID();
			} else {
				approverCode = bean.getDefaultManagerID();
			}
			insertObj[0][14] = approverCode;
			insertObj[0][15] = bean.getStatus();
			insertObj[0][16] = bean.getCompletedByID();
			insertObj[0][17] = bean.getCompletedOn();
			insertObj[0][18] = bean.getTrackingNumber();
			insertObj[0][19] = bean.getCourseDesc();

			final String insertQuery = "INSERT INTO HRMS_D1_CBT_REQUEST " + 
					 " (CBT_ID, CBT_EMP_ID, CBT_DEPT_NAME, CBT_DEPT_NUMBER, CBT_COURSE_NUMBER, CBT_LOCATION, " + 
					 " CBT_ADDRESS, CBT_CITY, CBT_STATE, CBT_PROVIDENCE, CBT_COUNTRY, CBT_ZIP, " + 
					 " CBT_PHONE, CBT_FAX, CBT_EMAIL, CBT_APPROVER_ID, CBT_STATUS, CBT_COMPLETED_BY, " + 
					 " CBT_COMPLETED_ON, CBT_TRACKING_NUM, CBT_COURSE_DESC) " + 
					 " VALUES((SELECT NVL(MAX(CBT_ID),0)+1 FROM HRMS_D1_CBT_REQUEST),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),?,?) ";

			result = this.getSqlModel().singleExecute(insertQuery, insertObj);

			if (result) {
				final String autoCodeQuery = " SELECT NVL(MAX(CBT_ID),0) FROM HRMS_D1_CBT_REQUEST ";
				final Object[][] data = this.getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setCbtID(String.valueOf(data[0][0]));
				}
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in save() : " + e);
		}
		return result;
	}

	/**
	 * Method : update.
	 * Purpose : For updating records
	 * @param bean : CBTFormBean
	 * @return : boolean
	 */
	public boolean update(final CBTFormBean bean) {
		boolean result = false;
		try {
			String approverCode = "";
			final Object[][] updateObj = new Object[1][21];
			updateObj[0][0] = bean.getEmployeeID();
			updateObj[0][1] = bean.getDeptName();
			updateObj[0][2] = bean.getDeptNo();
			updateObj[0][3] = bean.getCourseNo();
			if (bean.getLocationRadioValue().equals(this.stringUS)) {
				updateObj[0][4] = this.uS;
			} else {
				updateObj[0][4] = this.stringC;
			}
			updateObj[0][5] = bean.getAddress();
			updateObj[0][6] = bean.getCity();
			updateObj[0][7] = bean.getState();
			updateObj[0][8] = bean.getProvidence();
			updateObj[0][9] = bean.getCountry();
			updateObj[0][10] = bean.getZip();
			updateObj[0][11] = bean.getPhNo();
			updateObj[0][12] = bean.getFaxNo();
			updateObj[0][13] = bean.getEmailAddress();

			if (!"".equals(bean.getChangeMyManagerID()) && bean.getChangeMyManagerID() != null) {
				approverCode = bean.getChangeMyManagerID();
			} else {
				approverCode = bean.getDefaultManagerID();
			}
			updateObj[0][14] = approverCode;
			updateObj[0][15] = bean.getStatus();
			updateObj[0][16] = bean.getCompletedByID();
			updateObj[0][17] = bean.getCompletedOn();
			updateObj[0][18] = bean.getTrackingNumber();
			updateObj[0][19] = bean.getCourseDesc();
			updateObj[0][20] = bean.getCbtID();

			final String insertQuery = "UPDATE HRMS_D1_CBT_REQUEST SET " + 
					 " CBT_EMP_ID=?, CBT_DEPT_NAME=?, CBT_DEPT_NUMBER=?,  CBT_COURSE_NUMBER=?, CBT_LOCATION=?, " + 
					 " CBT_ADDRESS=?, CBT_CITY=?, CBT_STATE=?, CBT_PROVIDENCE=?, CBT_COUNTRY=?, CBT_ZIP=?, " + 
					 " CBT_PHONE=?, CBT_FAX=?, CBT_EMAIL=?, CBT_APPROVER_ID=?, CBT_STATUS=?, " + 
					 " CBT_COMPLETED_BY=?, CBT_COMPLETED_ON=TO_DATE(?,'DD-MM-YYYY HH24:MI'), CBT_TRACKING_NUM=?, " + 
					 " CBT_COURSE_DESC=? WHERE CBT_ID=? ";

			result = this.getSqlModel().singleExecute(insertQuery, updateObj);

		} catch (final Exception e) {
			this.logger.info("Exception occured in update() : " + e);
		}
		return result;
	}

	/**
	 * Method : viewApplicationFunction.
	 * Purpose : For showing details of application
	 * @param bean : CBTFormBean
	 * @param cbtId : Application ID
	 */
	public void viewApplicationFunction(final CBTFormBean bean, final String cbtId) {
		try {
			final String query = " SELECT CBT_ID, CBT_EMP_ID, EMPLOYEE.EMP_TOKEN, EMPLOYEE.EMP_FNAME||' '||EMPLOYEE.EMP_MNAME||' '||EMPLOYEE.EMP_LNAME, " + 
					 " CBT_DEPT_NAME, CBT_DEPT_NUMBER, CBT_COURSE_NUMBER, CBT_COURSE_DESC, CBT_LOCATION, CBT_ADDRESS, CBT_CITY, CBT_STATE, " + 
					 " CBT_PROVIDENCE, CBT_COUNTRY, CBT_ZIP, CBT_PHONE, CBT_FAX, CBT_EMAIL, " + 
					 " CBT_APPROVER_ID, APPROVER.EMP_TOKEN, APPROVER.EMP_FNAME||' '||APPROVER.EMP_MNAME||' '||APPROVER.EMP_LNAME," + 
					 " '_'||CBT_STATUS, CBT_COMPLETED_BY, INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME, " + 
					 " TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY HH24:MI'), CBT_TRACKING_NUM " + 
					 " FROM HRMS_D1_CBT_REQUEST " + 
					 " LEFT JOIN HRMS_EMP_OFFC EMPLOYEE ON (EMPLOYEE.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) " + 
					// +" LEFT JOIN HRMS_D1_CLASS_REQUEST ON
					// (HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID =
					// HRMS_D1_CBT_REQUEST.CBT_COURSE_ID) "
					 " LEFT JOIN HRMS_EMP_OFFC APPROVER ON (APPROVER.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_APPROVER_ID) " + 
					 " LEFT JOIN HRMS_EMP_OFFC INITIATOR ON (INITIATOR.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_COMPLETED_BY) WHERE CBT_ID = " + cbtId;

			final Object[][] data = this.getSqlModel().getSingleResult(query);

			bean.setCbtID(this.checkNull(String.valueOf(data[0][0])));
			bean.setEmployeeID(this.checkNull(String.valueOf(data[0][1])));
			bean.setEmployeeToken(this.checkNull(String.valueOf(data[0][2])));
			bean.setEmployeeName(this.checkNull(String.valueOf(data[0][3])));
			bean.setDeptName(this.checkNull(String.valueOf(data[0][4])));
			bean.setDeptNo(this.checkNull(String.valueOf(data[0][5])));
			bean.setCourseNo(this.checkNull(String.valueOf(data[0][6])));
			bean.setCourseDesc(this.checkNull(String.valueOf(data[0][7])));
			if (this.checkNull(String.valueOf(data[0][8])).equals(this.uS)) {
				bean.setLocationName(this.stringUS);
				bean.setLocationRadioValue(this.stringUS);
			} else {
				bean.setLocationName(this.stringCanada);
				bean.setLocationRadioValue(this.stringCanada);
			}
			bean.setAddress(this.checkNull(String.valueOf(data[0][9])));
			bean.setCity(this.checkNull(String.valueOf(data[0][10])));
			bean.setState(this.checkNull(String.valueOf(data[0][11])));
			bean.setProvidence(this.checkNull(String.valueOf(data[0][12])));
			bean.setCountry(this.checkNull(String.valueOf(data[0][13])));
			bean.setZip(this.checkNull(String.valueOf(data[0][14])));
			bean.setPhNo(this.checkNull(String.valueOf(data[0][15])));
			bean.setFaxNo(this.checkNull(String.valueOf(data[0][16])));
			bean.setEmailAddress(this.checkNull(String.valueOf(data[0][17])));

			// for approver Use only
			// bean.setChangeMyManagerID(this.checkNull(String.valueOf(data[0][18])));
			// bean.setChangeMyManagerToken(this.checkNull(String.valueOf(data[0][19])));
			// bean.setChangeMyManagerName(this.checkNull(String.valueOf(data[0][20])));

			bean.setStatus(this.checkNull(String.valueOf(data[0][21])));
			bean.setCompletedByID(this.checkNull(String.valueOf(data[0][22])));
			bean.setCompletedByName(this.checkNull(String.valueOf(data[0][23])));
			bean.setCompletedOn(this.checkNull(String.valueOf(data[0][24])));
			bean.setTrackingNumber(this.checkNull(String.valueOf(data[0][25])));

			bean.setApplnStatus(bean.getStatus());

			try {
				final String approverQuery = "SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC " + 
						 " WHERE EMP_ID=(SELECT EMP_REPORTING_OFFICER FROM HRMS_EMP_OFFC WHERE EMP_ID = " + bean.getUserEmpId() + ")";
				final Object[][] queryObj = this.getSqlModel().getSingleResult(approverQuery);
				if (queryObj != null && queryObj.length > 0) {
					final String defaultApprover = this.checkNull(String.valueOf(queryObj[0][0]));
					final String changedApprover = this.checkNull(String.valueOf(data[0][18]));

					if (defaultApprover.equals(changedApprover)) {
						bean.setDefaultManagerID(this.checkNull(String.valueOf(queryObj[0][0])));
						bean.setDefaultManagerToken(this.checkNull(String.valueOf(queryObj[0][1])));
						bean.setDefaultManagerName(this.checkNull(String.valueOf(queryObj[0][2])));
					} else {
						bean.setDefaultManagerID(this.checkNull(String.valueOf(queryObj[0][0])));
						bean.setDefaultManagerToken(this.checkNull(String.valueOf(queryObj[0][1])));
						bean.setDefaultManagerName(this.checkNull(String.valueOf(queryObj[0][2])));
						bean.setChangeMyManagerID(this.checkNull(String.valueOf(data[0][18])));
						bean.setChangeMyManagerToken(this.checkNull(String.valueOf(data[0][19])));
						bean.setChangeMyManagerName(this.checkNull(String.valueOf(data[0][20])));
					}

				} else {
					bean.setChangeMyManagerID(this.checkNull(String.valueOf(data[0][18])));
					bean.setChangeMyManagerToken(this.checkNull(String.valueOf(data[0][19])));
					bean.setChangeMyManagerName(this.checkNull(String.valueOf(data[0][20])));
				}
			} catch (final Exception e) {
				this.logger.info("Exception occured in viewApplicationFunction() : " + e);
			}
			this.getApproverCommentList(bean, cbtId);
		} catch (final Exception e) {
			this.logger.error("Exception in viewApplicationFunction : " + e);
		}
	}

	/**
	 * Method : getApproverCommentList.
	 * Purpose : For getting approver comments list
	 * @param bean : CBTFormBean
	 * @param applicationID : String
	 */
	public void getApproverCommentList(final CBTFormBean bean, final String applicationID) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CBT_COMMENTS, " + 
				 " TO_CHAR(CBT_DATE, 'DD-MM-YYYY') AS APPROVED_DATE, " + 
				 " DECODE(CBT_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS " + 
				 " FROM HRMS_D1_CBT_DATA_PATH " + 
				 " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CBT_DATA_PATH.CBT_APPROVER) WHERE CBT_ID = " + applicationID + " ORDER BY CBT_DATE DESC";

		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<CBTFormBean> approverList = new ArrayList<CBTFormBean>(apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			bean.setApproverCommentsFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final CBTFormBean innerBean = new CBTFormBean();
				innerBean.setApprName(this.checkNull(String.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(this.checkNull(String.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(this.checkNull(String.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(this.checkNull(String.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}

	/**
	 * Method : delete.
	 * Purpose : For deleting application
	 * @param bean : CBTFormBean
	 * @param request : HttpServletRequest
	 * @return : boolean
	 */
	public boolean delete(final CBTFormBean bean, final HttpServletRequest request) {
		boolean result = false;
		final String code = bean.getCbtID();
		final String delQuery = "DELETE FROM HRMS_D1_CBT_REQUEST WHERE CBT_ID=" + code;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}
	
	/**
	 * Method : sendForApproval.
	 * Purpose : For getting vendor list
	 * @param bean : CBTFormBean
	 * @return : boolean
	 */
	public boolean sendForApproval(final CBTFormBean bean) {
		boolean result = false;
		if ("".equals(bean.getCbtID())) {
			result = this.save(bean);
		} else {
			result = this.update(bean);
		}
		return result;
	}

	/**
	 * Method : getInitiatorDetail.
	 * Purpose : For getting initiator details
	 * @param cbtBean : CBTFormBean
	 */
	public void getInitiatorDetail(final CBTFormBean cbtBean) {
		try {
			final String query = "SELECT EMP_ID, EMP_TOKEN||' - '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME," + 
				 	"TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID = " + cbtBean.getUserEmpId();
			final Object[][] queryObj = this.getSqlModel().getSingleResult(query);
			if (queryObj != null && queryObj.length > 0) {
				cbtBean.setCompletedByID(this.checkNull(String.valueOf(queryObj[0][0])));
				cbtBean.setCompletedByName(this.checkNull(String.valueOf(queryObj[0][1])));
				cbtBean.setCompletedOn(this.checkNull(String.valueOf(queryObj[0][2])));
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in getInitiatorDetail : " + e);
		}
	}

	/**
	 * Method : getAppropriateListFromQuery.
	 * Purpose : For getting status wise list of applications.
	 * @param status : String
	 * @param status : userID
	 * @return : Object.
	 */
	public Object[][] getAppropriateListFromQuery(final String status, final String userID) {
		Object[][] result = null;
		final String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS " + 
						" FROM HRMS_D1_CBT_REQUEST LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) " + 
						" WHERE CBT_STATUS IN (" + status + ") AND CBT_COMPLETED_BY = " + userID + " ORDER BY CBT_COMPLETED_ON DESC ";
		result = this.getSqlModel().getSingleResult(selQuery);
		return result;
	}
}
