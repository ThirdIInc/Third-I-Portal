package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ClassRequestApproveBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1381.
 *
 */
public class ClassRequestApproveModel extends ModelBase {
	
	/**
	 * Set 0.
	 */
	private static final String PAGE_ZERO = "0";
	/**
	 * Set 20.
	 */
	private static final String PAGE_TWENTY = "20";
	/**
	 * Set 1.
	 */
	private static final String PAGE_ONE = "1";
	
	
	
	
	
	/**
	 * @param applicationId - set application Id.
	 * @param userId - set  user id.
	 * @param approverComments - set Approver Comments.
	 * @param statusToUpdate - set ToUpdate status.
	 */
        private void insertApproverComments(final String applicationId, final String userId, final String approverComments, final String statusToUpdate) {
        	final 	String insertSql = " INSERT INTO HRMS_D1_CLASS_REQ_DATA_PATH (CLASS_REQ_PATH_ID, CLASS_REQUEST_ID, CLASS_REQ_APPROVER, CLASS_REQ_COMMENTS, CLASS_REQ_STATUS) " + " VALUES ((SELECT NVL(MAX(CLASS_REQ_PATH_ID), 0) + 1 FROM HRMS_D1_CLASS_REQ_DATA_PATH), ?, ?, ?,?) ";
        	final Object[][] insertObj = new Object[1][4];
		
		insertObj[0][0] = applicationId;
		insertObj[0][1] = userId;
		insertObj[0][2] = approverComments;
		insertObj[0][3] = statusToUpdate;

		this.getSqlModel().singleExecute(insertSql, insertObj);
	}
	
        /**
         * @param userId - Current user.
         * @return  true/false , whether current user is an Education approver or not
         */
        public boolean isUserAHRApprover(final String userId) {
        	final String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'E' AND APP_SETTINGS_EMP_ID = " + userId;
        	final	Object[][] hrApproverObj = this.getSqlModel().getSingleResult(hrApproverSql);

        	if (hrApproverObj != null && hrApproverObj.length > 0) { return true; }

        	return false;
	}

	
	
        /**
         * purpose - display pending application list.
         * @param bean - get & set My page also set pendingDataList.
         * @param request - set total page & page no attribute.
         */
        public void getPendingList(final ClassRequestApproveBean bean, final HttpServletRequest request) {
        	try {
        		Object[][] pendingListData = null;
        		final List<ClassRequestApproveBean> pendingDataList = new ArrayList<ClassRequestApproveBean>();

        		Object[][] pendingCancellationListData = null;
        		final List<ClassRequestApproveBean> pendingCancellationDataList = new ArrayList<ClassRequestApproveBean>();
					
        		final String selQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE STATUS='P' ORDER BY CLASS_APPL_DATE ";
		
		
        		pendingListData = this.getSqlModel().getSingleResult(selQuery);

        		final String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(), pendingListData.length, 20);
        		if (pageIndexDrafted == null) {
        			pageIndexDrafted[0] = ClassRequestApproveModel.PAGE_ZERO;
			        pageIndexDrafted[1] = ClassRequestApproveModel.PAGE_TWENTY;
			        pageIndexDrafted[2] = ClassRequestApproveModel.PAGE_ONE;
			        pageIndexDrafted[3] = ClassRequestApproveModel.PAGE_ONE;
			        pageIndexDrafted[4] = "";
        		}

        		request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
        		request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
        		if (ClassRequestApproveModel.PAGE_ONE.equals(pageIndexDrafted[4])) {
        			bean.setMyPage(ClassRequestApproveModel.PAGE_ONE);
        		}

        		if (pendingListData != null && pendingListData.length > 0) {
        			bean.setPendingListLength(true);
        			for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
        				final	ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
        				bean1.setClassAppCode(String.valueOf(pendingListData[i][0]));
				        bean1.setTrackingNum(String.valueOf(pendingListData[i][1]));
				        bean1.setClassName(String.valueOf(pendingListData[i][2]));
				        bean1.setClassDescription(String.valueOf(pendingListData[i][3]));
				        bean1.setClassAppDate(String.valueOf(pendingListData[i][4]));
				        pendingDataList.add(bean1);
        			}
        			bean.setPendingIteratorList(pendingDataList);
        		}
		
		
        		final String pendingCancellationQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " 	+ " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE STATUS='C' ORDER BY CLASS_APPL_DATE ";
		
		
        		pendingCancellationListData = this.getSqlModel().getSingleResult(pendingCancellationQuery);

        		final String[] pagependingCancellationIndex = Utility.doPaging(bean.getMyPagePendingCancel(), pendingCancellationListData.length, 20);
        		if (pageIndexDrafted == null) {
        			pagependingCancellationIndex[0] = ClassRequestApproveModel.PAGE_ZERO;
        			pagependingCancellationIndex[1] = ClassRequestApproveModel.PAGE_TWENTY;
        			pagependingCancellationIndex[2] = ClassRequestApproveModel.PAGE_ONE;
        			pagependingCancellationIndex[3] = ClassRequestApproveModel.PAGE_ONE;
        			pagependingCancellationIndex[4] = "";
        		}

        		request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String.valueOf(pagependingCancellationIndex[2])));
        		request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String.valueOf(pagependingCancellationIndex[3])));
        		if (ClassRequestApproveModel.PAGE_ONE.equals(pagependingCancellationIndex[4])) {
        			bean.setMyPagePendingCancel(ClassRequestApproveModel.PAGE_ONE);
        		}

        		if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
        			bean.setPendingCancellationListLength(true);
        			for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer.parseInt(pagependingCancellationIndex[1]); i++) {
        				final	ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
        				bean1.setClassAppCode(String.valueOf(pendingCancellationListData[i][0]));
        				bean1.setTrackingNum(String.valueOf(pendingCancellationListData[i][1]));
        				bean1.setClassName(String.valueOf(pendingCancellationListData[i][2]));
        				bean1.setClassDescription(String.valueOf(pendingCancellationListData[i][3]));
        				bean1.setClassAppDate(String.valueOf(pendingCancellationListData[i][4]));
        				pendingCancellationDataList.add(bean1);
        			}
        			bean.setPendingCancellationIteratorList(pendingCancellationDataList);
        		}
		// For pending cancellation application Ends

        	} catch (final Exception e) {
        		e.printStackTrace();
        	}
        }

/*Status P & C*/	
/*	public void getPendingList(ClassRequestApproveBean bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingDataList = new ArrayList();

			Object[][] pendingCancellationListData = null;
			ArrayList pendingCancellationDataList = new ArrayList();
			
			String selQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='P' AND CLASS_APPROVER="+userId+"  ORDER BY CLASS_APPL_DATE ";
			
			
			pendingListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean
					.getMyPage(), pendingListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				bean.setMyPage("1");

			if (pendingListData != null && pendingListData.length > 0) {
				bean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
					bean1.setClassAppCode(String.valueOf(pendingListData[i][0]));
					bean1.setTrackingNum(String.valueOf(pendingListData[i][1]));
					bean1.setClassName(String.valueOf(pendingListData[i][2]));
					bean1.setClassDescription(String.valueOf(pendingListData[i][3]));
					bean1.setClassAppDate(String.valueOf(pendingListData[i][4]));
					pendingDataList.add(bean1);
				}
				bean.setPendingIteratorList(pendingDataList);
			}
			
			String pendingCancellationQuery = "SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='C' AND CLASS_APPROVER="+userId+"  ORDER BY CLASS_APPL_DATE ";
			
			pendingCancellationListData = getSqlModel().getSingleResult(pendingCancellationQuery);

			String[] pagependingCancellationIndex = Utility.doPaging(bean
					.getMyPagePendingCancel(), pendingCancellationListData.length, 20);
			if (pageIndexDrafted == null) {
				pagependingCancellationIndex[0] = "0";
				pagependingCancellationIndex[1] = "20";
				pagependingCancellationIndex[2] = "1";
				pagependingCancellationIndex[3] = "1";
				pagependingCancellationIndex[4] = "";
			}

			request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String
					.valueOf(pagependingCancellationIndex[2])));
			request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String
					.valueOf(pagependingCancellationIndex[3])));
			if (pagependingCancellationIndex[4].equals("1"))
				bean.setMyPagePendingCancel("1");

			if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
				bean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer
						.parseInt(pagependingCancellationIndex[1]); i++) {
					ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
					bean1.setClassAppCode(String.valueOf(pendingListData[i][0]));
					bean1.setTrackingNum(String.valueOf(pendingListData[i][1]));
					bean1.setClassName(String.valueOf(pendingListData[i][2]));
					bean1.setClassDescription(String.valueOf(pendingListData[i][3]));
					bean1.setClassAppDate(String.valueOf(pendingListData[i][4]));
					pendingCancellationDataList.add(bean1);
				}
				bean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// For pending cancellation application Ends

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * @param classDataId - get appln data.
	 * @param userId - current user.
	 * @return true/false.
	 */
	public boolean isUserAMagaer(final String classDataId, final String userId) {
		final String mgrApproverSql = " SELECT * FROM HRMS_D1_CLASS_REQUEST WHERE CLASS_REQUEST_ID = " + classDataId + " AND CLASS_APPROVER = " + userId;
		final Object[][] mgrApproverObj = this.getSqlModel().getSingleResult(mgrApproverSql);

		if (mgrApproverObj != null && mgrApproverObj.length > 0) { return true; }

		return false;
	}
	
	
	/**
	 * @param classreqApp - get appln code,approver comments & user emp id.
	 * @param status - get appln Status.
	 * @return true/ false.
	 */
	public boolean approveApplicationFunction(final ClassRequestApproveBean classreqApp, final String status) {
		final String applicationId = classreqApp.getClassReqAppCode();
		final String approverComments = classreqApp.getApproverComments();
		final String approverID = classreqApp.getUserEmpId();		
		
		boolean result = false;
		try {
					
			final String changeStatusQuery = " UPDATE HRMS_D1_CLASS_REQUEST SET STATUS = '" + status + "' " 	+ " WHERE CLASS_REQUEST_ID = " + applicationId;
			result =  this.getSqlModel().singleExecute(changeStatusQuery);
			this.insertApproverComments(applicationId, approverID, approverComments,	status);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	/**
	 * @param applicationId - to set Application Id to isUserManager. 
	 * @param approverComments Approver Comments
	 * @param userId - to set Application Id to isUserManager.
	 * @param status - Appln Status.
	 * @param nextApprover - set approver
	 * @return statusToUpdate 
	 */
	public String approve(final String applicationId, final String approverComments, final String userId, final String status, final String nextApprover) {
		String statusToUpdate = "A";
		String updateApproversql = "";

		if ("C".equals(status)) {
			statusToUpdate = "X";
		} else {
			if (this.isUserAMagaer(applicationId, userId)) {
				statusToUpdate = "P";
				
				if (nextApprover.equals(userId)) {
					statusToUpdate = "F";
				}
				updateApproversql = ", CLASS_APPROVER = " + nextApprover;
			}
		}

		final String updateApproverCommentsSql = " UPDATE HRMS_D1_CLASS_REQUEST SET STATUS = '" + statusToUpdate + "' " + " WHERE CLASS_REQUEST_ID = " + applicationId;
		this.getSqlModel().singleExecute(updateApproverCommentsSql);

		this.insertApproverComments(applicationId, userId, approverComments, statusToUpdate);
		return statusToUpdate;
	}

	/**
	 * @param result if it is null or not.
	 * @return String
	 *  purpose - Checking Null Value Functionality
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}
	
	/**
	 * @param classDataId - get appln data.
	 * @param approverComments approverComments
	 * @param userId - get and pass to insertApproverComments method.
	 * @return message.
	 */
	public String sendBack(final String classDataId, final String approverComments, final String userId) {
		final String message = "";

		try {
			final String updateApproverCommentsSql = " UPDATE HRMS_D1_CLASS_REQUEST SET STATUS = 'B' " + " WHERE CLASS_REQUEST_ID = " + classDataId;
			this.getSqlModel().singleExecute(updateApproverCommentsSql);

			this.insertApproverComments(classDataId, userId, approverComments, "B");
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return message;
	}
		
	/**
	 * @param classDataChangeId - get application data.
	 * @return Object
	 */
	public Object[][] getApproverCommentList(final String classDataChangeId) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CLASS_REQ_COMMENTS, " + " TO_CHAR(CLASS_REQ_DATE, 'DD-MM-YYYY') AS CLASS_REQ_DATE, " + " DECODE(CLASS_REQ_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') AS STATUS " + " FROM HRMS_D1_CLASS_REQ_DATA_PATH PATH " + " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.CLASS_REQ_APPROVER) " + " WHERE CLASS_REQUEST_ID = " + classDataChangeId + " ORDER BY CLASS_REQ_PATH_ID DESC";
		return this.getSqlModel().getSingleResult(apprCommentListSql);
	}

	/**
	 * purpose - Rejected List Begins (FOR IT APPROVER == Final Approver).
	 * @param bean - getting my page.
	 * @param request - set total page & page no.
	 */
	public void getRejectedList(final ClassRequestApproveBean  bean, final HttpServletRequest request) {
		try {
			Object[][] rejectedListData = null;
			final List<ClassRequestApproveBean> rejectedList = new ArrayList<ClassRequestApproveBean>();
			final String selQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " + " WHERE STATUS='R' ORDER BY CLASS_REQUEST_ID DESC ";
			rejectedListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndexRejected = Utility.doPaging(bean.getMyPageRejected(), rejectedListData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = ClassRequestApproveModel.PAGE_ZERO;
				pageIndexRejected[1] = ClassRequestApproveModel.PAGE_TWENTY;
				pageIndexRejected[2] = ClassRequestApproveModel.PAGE_ONE;
				pageIndexRejected[3] = ClassRequestApproveModel.PAGE_ONE;
				pageIndexRejected[4] = "";
			}
			request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
			if (ClassRequestApproveModel.PAGE_ONE.equals(pageIndexRejected[4])) {
				bean.setMyPageRejected(ClassRequestApproveModel.PAGE_ONE);
			}
			if (rejectedListData != null && rejectedListData.length > 0) {
				bean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
					final ClassRequestApproveBean  bean1 = new ClassRequestApproveBean();
					bean1.setClassAppCode(String.valueOf(rejectedListData[i][0]));
					bean1.setTrackingNum(String.valueOf(rejectedListData[i][1]));
					bean1.setClassName(String.valueOf(rejectedListData[i][2]));
					bean1.setClassDescription(String.valueOf(rejectedListData[i][3]));
					bean1.setClassAppDate(String.valueOf(rejectedListData[i][4]));
					rejectedList.add(bean1);
				}
				bean.setRejectedIteratorList(rejectedList);
			}
		
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public void getRejectedList(ClassRequestApproveBean bean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] rejectedListData = null;
			ArrayList rejectedList = new ArrayList();
			String selQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='R' AND CLASS_APPROVER="+userId+" ORDER BY CLASS_APPL_DATE ";
			
			rejectedListData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexRejected = Utility.doPaging(bean
					.getMyPageRejected(), rejectedListData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}
			request.setAttribute("totalRejectedPage", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				bean.setMyPageRejected("1");
			if (rejectedListData != null && rejectedListData.length > 0) {
				bean.setRejectedListLength(true);
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
					bean1.setClassAppCode(String.valueOf(rejectedListData[i][0]));
					bean1.setTrackingNum(String.valueOf(rejectedListData[i][1]));
					bean1.setClassName(String.valueOf(rejectedListData[i][2]));
					bean1.setClassDescription(String.valueOf(rejectedListData[i][3]));
					bean1.setClassAppDate(String.valueOf(rejectedListData[i][4]));
					rejectedList.add(bean1);
				}
				bean.setRejectedIteratorList(rejectedList);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	/**
	 * @param bean - get my page value of paging field.
	 * @param request - set total page & page no.
	 */
	public void getApprovedList(final ClassRequestApproveBean bean, final HttpServletRequest request) {
		try {
			Object[][] approvedListData = null;
			final List<ClassRequestApproveBean> approvedList = new ArrayList<ClassRequestApproveBean>();
			final String selQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') " + " FROM HRMS_D1_CLASS_REQUEST  " 	+ " WHERE STATUS ='A' ORDER BY CLASS_REQUEST_ID DESC ";
			approvedListData = this.getSqlModel().getSingleResult(selQuery);

			final String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = ClassRequestApproveModel.PAGE_ZERO;
				pageIndexApproved[1] = ClassRequestApproveModel.PAGE_TWENTY;
				pageIndexApproved[2] = ClassRequestApproveModel.PAGE_ONE;
				pageIndexApproved[3] = ClassRequestApproveModel.PAGE_ONE;
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if (ClassRequestApproveModel.PAGE_ONE.equals(pageIndexApproved[4])) {
				bean.setMyPageApproved(ClassRequestApproveModel.PAGE_ONE);
			}
				

			if (approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
					bean1.setClassAppCode(String.valueOf(approvedListData[i][0]));
					bean1.setTrackingNum(String.valueOf(approvedListData[i][1]));
					bean1.setClassName(String.valueOf(approvedListData[i][2]));
					bean1.setClassDescription(String.valueOf(approvedListData[i][3]));
					bean1.setClassAppDate(String.valueOf(approvedListData[i][4]));
					approvedList.add(bean1);
				}
				bean.setApproveredIteratorList(approvedList);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public void getApprovedList(ClassRequestApproveBean bean,HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();
			String selQuery = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS IN ('F', 'A') AND CLASS_APPROVER="+userId+"  ORDER BY CLASS_APPL_DATE ";
			
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(bean
					.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				bean.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
					bean1.setClassAppCode(String.valueOf(approvedListData[i][0]));
					bean1.setTrackingNum(String.valueOf(approvedListData[i][1]));
					bean1.setClassName(String.valueOf(approvedListData[i][2]));
					bean1.setClassDescription(String.valueOf(approvedListData[i][3]));
					bean1.setClassAppDate(String.valueOf(approvedListData[i][4]));
					approvedList.add(bean1);
				}
				bean.setApproveredIteratorList(approvedList);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*public List getForwardedApplicationList(String pageForForwardedApp, HttpServletRequest request) {

		List forwardedAppList = null;
		try {
			String pendingAppSql = " SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='F' ORDER BY CLASS_APPL_DATE ";
			Object[][] pendingAppObj = getSqlModel().getSingleResult(pendingAppSql);

			if(pendingAppObj != null && pendingAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForForwardedApp, pendingAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				forwardedAppList = new ArrayList(pendingAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
					bean1.setClassAppCode(String.valueOf(pendingAppObj[i][0]));
					bean1.setTrackingNum(String.valueOf(pendingAppObj[i][1]));
					bean1.setClassName(String.valueOf(pendingAppObj[i][2]));
					bean1.setClassDescription(String.valueOf(pendingAppObj[i][3]));
					bean1.setClassAppDate(String.valueOf(pendingAppObj[i][4]));
					forwardedAppList.add(bean1);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return forwardedAppList;
	}*/

	/*public List getPendingCancellationApplicationList(String pageForPendingCancelApp, HttpServletRequest request) {
		List pendingCancelAppList = null;

		try {
			String pendingCancelAppSql = "  SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='C' ORDER BY CLASS_APPL_DATE ";
			Object[][] pendingCancelAppObj = getSqlModel().getSingleResult(pendingCancelAppSql);

			if(pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingCancelApp, pendingCancelAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingCancelAppList = new ArrayList(pendingCancelAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ClassRequestApproveBean bean1 = new ClassRequestApproveBean();
					bean1.setClassAppCode(String.valueOf(pendingCancelAppObj[i][0]));
					bean1.setTrackingNum(String.valueOf(pendingCancelAppObj[i][1]));
					bean1.setClassName(String.valueOf(pendingCancelAppObj[i][2]));
					bean1.setClassDescription(String.valueOf(pendingCancelAppObj[i][3]));
					bean1.setClassAppDate(String.valueOf(pendingCancelAppObj[i][4]));
					pendingCancelAppList.add(bean1);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return pendingCancelAppList;
	}*/
	
	/**
	 * @param classreqApp - getting appln code,approver comments & user emp id. 
	 * @param status -  get appln Status
	 * @return true/false.
	 */
	public boolean rejectApplicationFunction(final ClassRequestApproveBean classreqApp, final String status) {
		final String applicationId = classreqApp.getClassReqAppCode();
		final String approverComments = classreqApp.getApproverComments();
		final String approverID = classreqApp.getUserEmpId();		
		
		boolean result = false;
		try {
			final String changeStatusQuery = " UPDATE HRMS_D1_CLASS_REQUEST SET STATUS = '" + status + "'" + " WHERE CLASS_REQUEST_ID = " + applicationId;
			result =  this.getSqlModel().singleExecute(changeStatusQuery);
			this.insertApproverComments(applicationId, approverID, approverComments,	status);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * @param classreqApp - set Application data.
	 * @param applicationID  - get application data.
	 * @param typeOfList typeOfList
	 */
	public void classApplicationFunction(final ClassRequestApproveBean classreqApp, final String applicationID, final String typeOfList) {
		try {
			System.out.println("classreqApp.getClassAppCode() here we get in viewApplicationFunction is-------------" + applicationID);
			
			final String query = " SELECT CLASS_NAME, CLASS_DESCRIPTION,DIVISION_ID, HRMS_DIVISION.DIV_NAME, PRE_REQUISITE,INSTRUCTOR_NAME, to_char(REQUEST_START_DATE,'dd-mm-yyyy'), to_char(REQUEST_END_DATE,'dd-mm-yyyy')," + " DURATION_OF_CLASS,REQUEST_START_TIME,REQUEST_END_TIME,CLASSROOM, MAX_OF_STUDENTS,LOCATION,ADDRESS," 	+ " TELEPHONE, CONTACT_NAME, HOTEL_INFORMATION,HOTEL_ATTACHMENT,COMMENTS,CLASS_REQUEST_ID,STATUS,CLASS_INITIATOR,EMP_TOKEN||' - '||EMP_FNAME||' '||EMP_LNAME, PERS_INITIATOR_DATE" + " FROM HRMS_D1_CLASS_REQUEST " + " LEFT JOIN   HRMS_DIVISION on(  HRMS_DIVISION.DIV_ID =HRMS_D1_CLASS_REQUEST.DIVISION_ID)" + " INNER JOIN HRMS_EMP_OFFC  ON (HRMS_EMP_OFFC.EMP_ID=HRMS_D1_CLASS_REQUEST.CLASS_INITIATOR) " + " WHERE CLASS_REQUEST_ID=" + applicationID;
			
			final Object[][] data = this.getSqlModel().getSingleResult(query);

			classreqApp.setClassName(this.checkNull(String.valueOf(data[0][0])));
			classreqApp.setClassDescription(this.checkNull(String.valueOf(data[0][1])));
			classreqApp.setDivId(this.checkNull(String.valueOf(data[0][2])));
			classreqApp.setClassDivision(this.checkNull(String.valueOf(data[0][3])));
			classreqApp.setPreRequisite(this.checkNull(String.valueOf(data[0][4])));
			classreqApp.setInstructorId(this.checkNull(String.valueOf(data[0][5])));
			classreqApp.setStartDate(this.checkNull(String.valueOf(data[0][6])));
			classreqApp.setEndDate(this.checkNull(String.valueOf(data[0][7])));
			classreqApp.setDurationofClass(this.checkNull(String.valueOf(data[0][8])));
			classreqApp.setStartTime(this.checkNull(String.valueOf(data[0][9])));
			classreqApp.setEndTime(this.checkNull(String.valueOf(data[0][10])));
			classreqApp.setClassRoom(this.checkNull(String.valueOf(data[0][11])));
			classreqApp.setMaxnumOfStudents(this.checkNull(String.valueOf(data[0][12])));
			classreqApp.setLocation(this.checkNull(String.valueOf(data[0][13])));
			classreqApp.setAddress(this.checkNull(String.valueOf(data[0][14])));
			classreqApp.setTelephone(this.checkNull(String.valueOf(data[0][15])));
			classreqApp.setContactName(this.checkNull(String.valueOf(data[0][16])));
			classreqApp.setHotelInformation(this.checkNull(String.valueOf(data[0][17])));
			classreqApp.setHotelFile(this.checkNull(String.valueOf(data[0][18])));
			classreqApp.setComments(this.checkNull(String.valueOf(data[0][19])));
			classreqApp.setClassReqAppCode(this.checkNull(String.valueOf(data[0][20])));
			classreqApp.setApplnStatus(this.checkNull(String.valueOf(data[0][21])));
			classreqApp.setCompletedByID(this.checkNull(String.valueOf(data[0][22])));
			classreqApp.setCompletedBy(this.checkNull(String.valueOf(data[0][23])));
			classreqApp.setCompletedDate(this.checkNull(String.valueOf(data[0][24])));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	/**
	 * @param classreqApp - set appln data.
	 * @param request .
	 */
	public void editBusinessData(final ClassRequestApproveBean classreqApp, final HttpServletRequest request) {

		try {
			
			final String query = "SELECT CLASS_NAME, CLASS_DESCRIPTION,DIVISION_ID, HRMS_DIVISION.DIV_NAME, PRE_REQUISITE,INSTRUCTOR_NAME, to_char(REQUEST_START_DATE,'dd-mm-yyyy'), to_char(REQUEST_END_DATE,'dd-mm-yyyy')," + " DURATION_OF_CLASS, REQUEST_START_TIME,REQUEST_END_TIME,CLASSROOM, MAX_OF_STUDENTS, LOCATION,ADDRESS," + " TELEPHONE, CONTACT_NAME, HOTEL_INFORMATION,HOTEL_ATTACHMENT,COMMENTS " + " FROM HRMS_D1_CLASS_REQUEST " + " LEFT JOIN   HRMS_LOCATION on(  HRMS_LOCATION.LOCATION_CODE =HRMS_D1_CLASS_REQUEST.LOCATION_ID)" + " WHERE CLASS_REQUEST_ID=" + classreqApp.getClassAppCode();

			final Object[][] data = this.getSqlModel().getSingleResult(query);

			classreqApp.setClassName(this.checkNull(String.valueOf(data[0][0])));
			classreqApp.setClassDescription(this.checkNull(String.valueOf(data[0][1])));
			classreqApp.setDivId(this.checkNull(String.valueOf(data[0][2])));
			classreqApp.setClassDivision(this.checkNull(String.valueOf(data[0][3])));

			classreqApp.setPreRequisite(this.checkNull(String.valueOf(data[0][4])));
			classreqApp.setInstructorId(this.checkNull(String.valueOf(data[0][5])));
			classreqApp.setStartDate(this.checkNull(String.valueOf(data[0][6])));
			classreqApp.setEndDate(this.checkNull(String.valueOf(data[0][7])));
			classreqApp.setDurationofClass(this.checkNull(String.valueOf(data[0][8])));
			classreqApp.setStartTime(this.checkNull(String.valueOf(data[0][9])));
			classreqApp.setEndTime(this.checkNull(String.valueOf(data[0][10])));
			classreqApp.setClassRoom(this.checkNull(String.valueOf(data[0][11])));
			classreqApp.setMaxnumOfStudents(this.checkNull(String.valueOf(data[0][12])));
			classreqApp.setLocation(this.checkNull(String.valueOf(data[0][13])));
			classreqApp.setAddress(this.checkNull(String.valueOf(data[0][14])));
			classreqApp.setTelephone(this.checkNull(String.valueOf(data[0][15])));
			classreqApp.setContactName(this.checkNull(String.valueOf(data[0][16])));
			classreqApp.setHotelInformation(this.checkNull(String.valueOf(data[0][17])));
			classreqApp.setHotelAttachments(this.checkNull(String.valueOf(data[0][18])));
			classreqApp.setComments(this.checkNull(String.valueOf(data[0][19])));

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}
	
	/*public List getApprovedApplicationList(String pageForApprovedApp, HttpServletRequest request) {
		List approvedAppList = null;
		try {
			
			String QueryappApp="SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='A' ORDER BY CLASS_APPL_DATE";
			Object[][] approvedAppObj = getSqlModel().getSingleResult(QueryappApp);
			if(approvedAppObj != null && approvedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForApprovedApp, approvedAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				
				request.setAttribute("totalPageForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[3])));
				
				approvedAppList = new ArrayList(approvedAppObj.length);
				
				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ClassRequestApproveBean bean = new ClassRequestApproveBean();
					bean.setClassAppCode(String.valueOf(approvedAppObj[i][0]));
					bean.setTrackingNum(String.valueOf(approvedAppObj[i][1]));
					bean.setClassName(String.valueOf(approvedAppObj[i][2]));
					bean.setClassDescription(String.valueOf(approvedAppObj[i][3]));
					bean.setClassAppDate(String.valueOf(approvedAppObj[i][4]));
					approvedAppList.add(bean);
				}
			}
		} catch(final Exception e) {
			e.printStackTrace();
		}
		
		return approvedAppList;
	}*/

	/*public List getRejectedApplicationList(String userId, String pageForRejectedApp, HttpServletRequest request) {
		
		List rejectedAppList = null;
		
		try {
			String QueryRA= "SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='R' AND CLASS_APPROVER="+userId+" ORDER BY CLASS_APPL_DATE";
				
			Object[][] rejectedAppObj = getSqlModel().getSingleResult(QueryRA);
			if(rejectedAppObj != null && rejectedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForRejectedApp, rejectedAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				rejectedAppList = new ArrayList(rejectedAppObj.length);
				
				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ClassRequestApproveBean bean = new ClassRequestApproveBean();
					bean.setClassAppCode(String.valueOf(rejectedAppObj[i][0]));
					bean.setTrackingNum(String.valueOf(rejectedAppObj[i][1]));
					bean.setClassName(String.valueOf(rejectedAppObj[i][2]));
					bean.setClassDescription(String.valueOf(rejectedAppObj[i][3]));
					bean.setClassAppDate(String.valueOf(rejectedAppObj[i][4]));
					rejectedAppList.add(bean);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return rejectedAppList;
	}*/
	
	/*public List getPendingApplicationList(String userId, String pageForPendingApp, HttpServletRequest request) {

		List pendingAppList = null;

		try {
			String QueryPAL="SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='P' AND CLASS_APPROVER="+userId+"  ORDER BY CLASS_APPL_DATE";
				
			Object[][] pendingAppObj = getSqlModel().getSingleResult(QueryPAL);

			if(pendingAppObj != null && pendingAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingApp, pendingAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingAppList = new ArrayList(pendingAppObj.length);

				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ClassRequestApproveBean bean = new ClassRequestApproveBean();
					bean.setClassAppCode(String.valueOf(pendingAppObj[i][0]));
					bean.setTrackingNum(String.valueOf(pendingAppObj[i][1]));
					bean.setClassName(String.valueOf(pendingAppObj[i][2]));
					bean.setClassDescription(String.valueOf(pendingAppObj[i][3]));
					bean.setClassAppDate(String.valueOf(pendingAppObj[i][4]));
					pendingAppList.add(bean);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return pendingAppList;
	}*/

	/*public List getPendingCancellationApplicationList(String userId, String pageForPendingCancelApp, HttpServletRequest request) {
		List pendingCancelAppList = null;
		
		try {
			String QueryPCA="SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS='C'  AND CLASS_APPROVER="+userId+" ORDER BY CLASS_APPL_DATE";
			
			Object[][] pendingCancelAppObj = getSqlModel().getSingleResult(QueryPCA);
			
			if(pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForPendingCancelApp, pendingCancelAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForPendingCancelApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingCancelAppList = new ArrayList(pendingCancelAppObj.length);
				
				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ClassRequestApproveBean bean = new ClassRequestApproveBean();
					bean.setClassAppCode(String.valueOf(pendingCancelAppObj[i][0]));
					bean.setTrackingNum(String.valueOf(pendingCancelAppObj[i][1]));
					bean.setClassName(String.valueOf(pendingCancelAppObj[i][2]));
					bean.setClassDescription(String.valueOf(pendingCancelAppObj[i][3]));
					bean.setClassAppDate(String.valueOf(pendingCancelAppObj[i][4]));
					pendingCancelAppList.add(bean);
				}
			}
		} catch(final Exception e) {
			e.printStackTrace();
		}
		
		return pendingCancelAppList;
	}*/
	
	/*public List getApprovedApplicationList(String userId, String pageForApprovedApp, HttpServletRequest request) {
		
		List approvedAppList = null;
		
		try {
			String QueryAAPList="SELECT CLASS_REQUEST_ID,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS IN ('F', 'A') AND CLASS_APPROVER="+userId+"  ORDER BY CLASS_APPL_DATE";
			Object[][] approvedAppObj = getSqlModel().getSingleResult(QueryAAPList);
			
			if(approvedAppObj != null && approvedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForApprovedApp, approvedAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				
				request.setAttribute("totalPageForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForApprovedApp", Integer.parseInt(String.valueOf(pageIndex[3])));
				
				approvedAppList = new ArrayList(approvedAppObj.length);
				
				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ClassRequestApproveBean bean = new ClassRequestApproveBean();
					bean.setClassAppCode(String.valueOf(approvedAppObj[i][0]));
					bean.setClassName(String.valueOf(approvedAppObj[i][1]));
					bean.setClassDescription(String.valueOf(approvedAppObj[i][2]));
					bean.setClassAppDate(String.valueOf(approvedAppObj[i][3]));
					approvedAppList.add(bean);
				}
			}
		} catch(final Exception e) {
			e.printStackTrace();
		}
		
		return approvedAppList;
	}*/
	
	
	/*public List getRejectedApplicationList(String pageForRejectedApp, HttpServletRequest request) {
		
		List rejectedAppList = null;
		
		try {
			String QueryRAList="SELECT CLASS_REQUEST_ID,TRACKING_NUMBER,CLASS_NAME,CLASS_DESCRIPTION,TO_CHAR(CLASS_APPL_DATE,'DD-MM-YYYY') "
				+ " FROM HRMS_D1_CLASS_REQUEST  "
				+ " WHERE STATUS IN ='R' ORDER BY CLASS_APPL_DATE";
			
			Object[][] rejectedAppObj = getSqlModel().getSingleResult(QueryRAList);
			
			if(rejectedAppObj != null && rejectedAppObj.length > 0) {
				String[] pageIndex = Utility.doPaging(pageForRejectedApp, rejectedAppObj.length, 20);

				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPageForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoForRejectedApp", Integer.parseInt(String.valueOf(pageIndex[3])));

				rejectedAppList = new ArrayList(rejectedAppObj.length);
				
				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ClassRequestApproveBean bean = new ClassRequestApproveBean();
					bean.setClassAppCode(String.valueOf(rejectedAppObj[i][0]));
					bean.setTrackingNum(String.valueOf(rejectedAppObj[i][1]));
					bean.setClassName(String.valueOf(rejectedAppObj[i][2]));
					bean.setClassDescription(String.valueOf(rejectedAppObj[i][3]));
					bean.setClassAppDate(String.valueOf(rejectedAppObj[i][4]));
					rejectedAppList.add(bean);
				}
			}
		} catch(final Exception e) {
			e.printStackTrace();
		}
		
		return rejectedAppList;
	}*/
		
}
