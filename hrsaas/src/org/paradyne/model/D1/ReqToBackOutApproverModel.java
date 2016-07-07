package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.ReqToBackOutApprover;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1380
 *
 */
public class ReqToBackOutApproverModel extends ModelBase {
	/** logger.	 *	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReqToBackOutApproverModel.class);
	/**	 zero. *	 */
	private final  String zero = "0";
	/**	 twenty. *	 */
	private final String twenty = "20";
	/**	 one. *	 */
	private final String one = "1";
	
	/**	creditMemoRadioYes. * 	 */
	private final String radioButtonYes = "ys";
	
	/**	creditMemoRadioNo. * 	 */
	private final String radioButtonNo = "no";
	
	/**	radioButtonTrue. * 	 */
	private final String radioButtonTrue = "Y";
	
	/**	radioButtonFalse. * 	 */
	private final String radioButtonFalse = "N";
	
	/**
	 * Method : getPendingList.
	 * Purpose : For getting vendor list
	 * @param reqApproverBean : ReqToBackOutApprover
	 * @param  request : HttpServletRequest
	 */
	public void getPendingList(final ReqToBackOutApprover reqApproverBean, final HttpServletRequest request) {
		try {
			Object[][] pendingListData = null;
			final List<ReqToBackOutApprover> pendingDataList = new ArrayList<ReqToBackOutApprover>();
			// Pending application Begins
			/*String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, " +  
				  " TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT "  + 
				  " WHERE VOUCHER_STATUS = 'P' ORDER BY VOUCHER_REQUEST_ID DESC"; 				
			pendingListData = this.getSqlModel().getSingleResult(selQuery);*/
			pendingListData = this.getAppropriateListFromQuery("'P'");
			final String[] pageIndex = Utility.doPaging(reqApproverBean.getMyPage(),
					pendingListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = this.zero;
				pageIndex[1] = this.twenty;
				pageIndex[2] = this.one;
				pageIndex[3] = this.one;
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.one)) {
				reqApproverBean.setMyPage(this.one);
			}
			
			if (pendingListData != null && pendingListData.length > 0) {
				reqApproverBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final ReqToBackOutApprover beanItt = new ReqToBackOutApprover();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(pendingListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(pendingListData[i][4])));
					pendingDataList.add(beanItt);
				}
				reqApproverBean.setPendingIteratorList(pendingDataList);
			}
			// Pending  application Ends
			
			// pending cancellation application Begins
			Object[][] pendingCancellationListData = null;
			final List<ReqToBackOutApprover>  pendingCancellationDataList = new ArrayList<ReqToBackOutApprover>();
			
			/*final String pendingCancellationQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, " 
				  								+" TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT " 
				  								+" WHERE VOUCHER_STATUS = 'C' ORDER BY VOUCHER_REQUEST_ID DESC"; 	
			pendingCancellationListData = this.getSqlModel().getSingleResult(pendingCancellationQuery);*/
			pendingCancellationListData = this.getAppropriateListFromQuery("'C'");			
			final String[] pagependingCancellationIndex = Utility.doPaging(reqApproverBean.getMyPagePendingCancel(),
					pendingCancellationListData.length, 20);
			if (pagependingCancellationIndex == null) {
				pagependingCancellationIndex[0] = this.zero;
				pagependingCancellationIndex[1] = this.twenty;
				pagependingCancellationIndex[2] = this.one;
				pagependingCancellationIndex[3] = this.one;
				pagependingCancellationIndex[4] = "";
			}
			
			request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String.valueOf(pagependingCancellationIndex[2])));
			request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String.valueOf(pagependingCancellationIndex[3])));
			if (pagependingCancellationIndex[4].equals(this.one)) {
				reqApproverBean.setMyPagePendingCancel(this.one);
			}
			
			if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
				reqApproverBean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer.parseInt(pagependingCancellationIndex[1]); i++) {
					final ReqToBackOutApprover beanItt = new ReqToBackOutApprover();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(pendingCancellationListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(pendingCancellationListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(pendingCancellationListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(pendingCancellationListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(pendingCancellationListData[i][4])));
					pendingCancellationDataList.add(beanItt);
				}
				reqApproverBean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// pending cancellation application Ends
		} catch (final Exception e) {
			this.logger.info(" Exception occure in getPendingList : " + e);
		}
	}

	/**
	 * Method : getApprovedList.
	 * Purpose : For getting approved list
	 * @param reqApproverBean : ReqToBackOutApprover
	 * @param request : HttpServletRequest
	 */
	public void getApprovedList(final ReqToBackOutApprover reqApproverBean, final HttpServletRequest request) {
		try {
			Object[][] approvedListData = null;
			final List<ReqToBackOutApprover> approvedList = new ArrayList<ReqToBackOutApprover>();
						
			// Approved application Begins
			final String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, " +  
				  				" TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT " + 
				  				" WHERE VOUCHER_STATUS IN('A','X') ORDER BY VOUCHER_REQUEST_ID DESC"; 
				
			approvedListData = this.getSqlModel().getSingleResult(selQuery);
			
				
			final String[] pageIndexApproved = Utility.doPaging(reqApproverBean.getMyPageApproved(),
					approvedListData.length, 20);
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
				reqApproverBean.setMyPageApproved(this.one);
			}
			
			if (approvedListData != null && approvedListData.length > 0) {
				reqApproverBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final ReqToBackOutApprover beanItt = new ReqToBackOutApprover();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				reqApproverBean.setApproveredIteratorList(approvedList);
			}
			// Approved application Ends
			
		} catch (final Exception e) {
			this.logger.info(" Exception occure in getApprovedList :  " + e);
		}
	}

	
	
	/**
	 * Method : getRejectedList.
	 * Purpose : For getting rejected list
	 * @param reqApproverBean : ReqToBackOutApprover
	 * @param request : HttpServletRequest
	 */
	public void getRejectedList(final ReqToBackOutApprover reqApproverBean, final HttpServletRequest request) {
		Object[][] rejectedListData = null;
		final List<ReqToBackOutApprover> rejectedList = new ArrayList<ReqToBackOutApprover>();
		
		// Rejected application Begins
		/*final String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, " 
							+" TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT " 
							+" WHERE VOUCHER_STATUS ='R' ORDER BY VOUCHER_REQUEST_ID DESC"; 
		rejectedListData = this.getSqlModel().getSingleResult(selQuery);*/
		rejectedListData = this.getAppropriateListFromQuery("'R'");
					
		final String[] pageIndexRejected = Utility.doPaging(reqApproverBean.getMyPageRejected(),
				rejectedListData.length, 20);
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
			reqApproverBean.setMyPageRejected(this.one);
		}
		
		if (rejectedListData != null && rejectedListData.length > 0) {
			reqApproverBean.setRejectedListLength(true);
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++)  {
				final ReqToBackOutApprover beanItt = new ReqToBackOutApprover();
				beanItt.setVouherHiddenID(this.checkNull(String.valueOf(rejectedListData[i][0])));
				beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(rejectedListData[i][1])));
				beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(rejectedListData[i][2])));
				beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			reqApproverBean.setRejectedIteratorList(rejectedList);
		}
		// Rejected application Ends
	}

	
	/**
	 * Method : updateStatus.
	 * Purpose : For updating status
	 * @param reqApproverBean : ReqToBackOutApprover
	 * @param status : Status
	 * @return : boolean
	 */	
	public boolean updateStatus(final ReqToBackOutApprover reqApproverBean, final String status) {
		final String applicationId = reqApproverBean.getRequestID();
		final String approverComments = reqApproverBean.getApproverComments();
		final String approverID = reqApproverBean.getUserEmpId();		
		boolean result = false;
		try {
			final String changeStatusQuery = "UPDATE HRMS_D1_REQ_BACK_OUT SET VOUCHER_STATUS = '" + status + "' WHERE VOUCHER_REQUEST_ID = " + applicationId;
			result =  this.getSqlModel().singleExecute(changeStatusQuery);
			this.insertApproverComments(applicationId, approverID, approverComments,	status);
		} catch (final Exception e) {
			this.logger.info(" Exception occured in updateStatus :  " + e);
		}
		return result;
	}
	
	
	/**
	 * Method : insertApproverComments.
	 * Purpose : For inserting approver comments
	 * @param applicationId : Application ID
	 * @param approverID : Approver ID
	 * @param approverComments : Approver Comments
	 * @param statusToUpdate : Status to update
	 */
	private void insertApproverComments(final String applicationId, final String approverID, final String approverComments, final String statusToUpdate) {
		try {
			final String insertSql = " INSERT INTO HRMS_D1_REQ_BACK_DATA_PATH (REQ_PATH_ID, REQ_APPLICATION_ID, REQ_APPROVER, REQ_COMMENTS, REQ_STATUS) " + 
									" VALUES ((SELECT NVL(MAX(REQ_PATH_ID), 0) + 1 FROM HRMS_D1_REQ_BACK_DATA_PATH), ?, ?, ?, ?) ";
			final Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = approverID;
			insertObj[0][2] = approverComments;
			insertObj[0][3] = statusToUpdate;
			this.getSqlModel().singleExecute(insertSql, insertObj);
		} catch (final Exception e) {
			this.logger.info(" Exception occured in insertApproverComments : " + e);
		}
	}
	
	/**
	 * Method : getApproverCommentList.
	 * Purpose : For getting approver comments
	 * @param bean : ReqToBackOutApprover
	 * @param requestID : Application ID
	 */
	public void getApproverCommentList(final ReqToBackOutApprover bean, final String requestID) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, REQ_COMMENTS, " + 
			" TO_CHAR(REQ_APPROVED_DATE, 'DD-MM-YYYY') AS REQ_APPROVED_DATE, " + 
			 " DECODE(REQ_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'X', 'Cancellation Rejected') AS STATUS " + 
			 " FROM HRMS_D1_REQ_BACK_DATA_PATH "  + 
			 " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_REQ_BACK_DATA_PATH.REQ_APPROVER) " + 
			 " WHERE REQ_APPLICATION_ID = " + requestID + " ORDER BY REQ_APPLICATION_ID DESC";
		
		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<ReqToBackOutApprover> approverList = new ArrayList<ReqToBackOutApprover>(apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final ReqToBackOutApprover innerBean = new ReqToBackOutApprover();
				innerBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
				innerBean.setApprComments(String.valueOf(apprCommentListObj[i][1]));
				innerBean.setApprDate(String.valueOf(apprCommentListObj[i][2]));
				innerBean.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}
	
	/**
	 * Method : viewApplicationFunction.
	 * Purpose : For showing details of application
	 * @param reqApproverBean : ReqToBackOutApprover
	 * @param requestID : Application ID 
	 * @param typeOfList : Type Of List 
	 */
	public void viewApplicationFunction(final ReqToBackOutApprover reqApproverBean, final String requestID, final String typeOfList) {
		try {
			final String viewQuery = "SELECT VOUCHER_REQUEST_ID, VOUCHER_FROM_EMP, OFFC1.EMP_TOKEN, OFFC1.EMP_FNAME||' '||OFFC1.EMP_LNAME, " +  
								" TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'), "  + 
								" VOUCHER_VENDOR_NAME, VOUCHER_VENDOR_NUMBER, VOUCHER_LINE_NUMBER, VOUCHER_QUANTITY, " +   
								" VOUCHER_VOUCHER_NUMBER, VOUCHER_REASON_FOR_REQUEST, VOUCHER_RMA, VOUCHER_AIR_BILL_NUMBER, " +  
								" VOUCHER_IS_CREDIT_MEMO, VOUCHER_COMMENTS, VOUCHER_STATUS, VOUCHER_PURCHASE_ORDER_NUMBER, "  + 
								" VOUCHER_TRACKING_NUMBER, VOUCHER_INITIATOR, " + 
								" OFFC2.EMP_TOKEN||' - '||OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME, TO_CHAR(VOUCHER_COMPLETED_ON,'DD-MM-YYYY HH24:MI') " + 
								" FROM HRMS_D1_REQ_BACK_OUT "  + 
								" INNER JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_REQ_BACK_OUT.VOUCHER_FROM_EMP) " + 
								" INNER JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_REQ_BACK_OUT.VOUCHER_INITIATOR) " + 
								" WHERE VOUCHER_REQUEST_ID =" + requestID;
			final Object[][] viewObj = this.getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				reqApproverBean.setRequestID(this.checkNull(String.valueOf(viewObj[0][0])));
				reqApproverBean.setEmployeeID(this.checkNull(String.valueOf(viewObj[0][1])));
				reqApproverBean.setEmployeeToken(this.checkNull(String.valueOf(viewObj[0][2])));
				reqApproverBean.setFromName(this.checkNull(String.valueOf(viewObj[0][3])));
				reqApproverBean.setToDate(this.checkNull(String.valueOf(viewObj[0][4])));
				reqApproverBean.setVendorName(this.checkNull(String.valueOf(viewObj[0][5])));
				reqApproverBean.setVendorNumber(this.checkNull(String.valueOf(viewObj[0][6])));
				reqApproverBean.setLineNumber(this.checkNull(String.valueOf(viewObj[0][7])));
				reqApproverBean.setQuantity(this.checkNull(String.valueOf(viewObj[0][8])));
				reqApproverBean.setVoucherNumber(this.checkNull(String.valueOf(viewObj[0][9])));
				reqApproverBean.setReasonForRequest(this.checkNull(String.valueOf(viewObj[0][10])));
				reqApproverBean.setRma(this.checkNull(String.valueOf(viewObj[0][11])));
				reqApproverBean.setAirBillNumber(this.checkNull(String.valueOf(viewObj[0][12])));
				
				if (String.valueOf(viewObj[0][13]).equals(this.radioButtonTrue)) {
					reqApproverBean.setDidVendorIssueCreditMemo(this.radioButtonYes);
					reqApproverBean.setCreditMemoRadio(this.radioButtonYes);
				}
				if (String.valueOf(viewObj[0][13]).equals(this.radioButtonFalse)) {
					reqApproverBean.setDidVendorIssueCreditMemo(this.radioButtonNo);
					reqApproverBean.setCreditMemoRadio(this.radioButtonNo);
				}
				
				if ("".equals(String.valueOf(viewObj[0][13]))) {
					reqApproverBean.setDidVendorIssueCreditMemo("");
					reqApproverBean.setCreditMemoRadio("");
				}
				
				reqApproverBean.setComments(this.checkNull(String.valueOf(viewObj[0][14])));
				reqApproverBean.setStatus(this.checkNull(String.valueOf(viewObj[0][15])));
				reqApproverBean.setPurchaseOrderNumber(this.checkNull(String.valueOf(viewObj[0][16])));
				reqApproverBean.setTrackingNumber(this.checkNull(String.valueOf(viewObj[0][17])));
				reqApproverBean.setCompletedByID(this.checkNull(String.valueOf(viewObj[0][18])));
				reqApproverBean.setCompletedByName(this.checkNull(String.valueOf(viewObj[0][19])));
				reqApproverBean.setCompletedOn(this.checkNull(String.valueOf(viewObj[0][20])));
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in viewApplicationFunction : " + e);
		}
		
	}

	/**
	 * Method : f9vendor.
	 * Purpose : For getting vendor list
	 * @param userId : Current User ID
	 * @return : String
	 */
	public boolean isFinanceApprover(final String userId) {
		final String financeApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'F' AND APP_SETTINGS_EMP_ID = " + userId;
		final Object[][] financeApproverObj = this.getSqlModel().getSingleResult(
				financeApproverSql);

		if (financeApproverObj != null && financeApproverObj.length > 0) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Method : this.checkNull.
	 * Purpose : For checking given string is null or not
	 * @param result : String
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
	 * Method : getAppropriateListFromQuery.
	 * Purpose : For getting status wise list of applications.
	 * @param status : String
	 * @return : Object.
	 */
	public Object[][] getAppropriateListFromQuery(final String status) {
		Object[][] result = null;
		final String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT " + 
				" WHERE VOUCHER_STATUS IN (" + status + ") ORDER BY VOUCHER_REQUEST_ID DESC";
		result = this.getSqlModel().getSingleResult(selQuery);
		return result;
	}
	
}
