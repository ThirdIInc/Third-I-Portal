package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.ReqToBackOutVoucher;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author aa1380.
 *
 */
public class ReqToBackOutVoucherModel extends ModelBase {
	/** logger.	 *	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReqToBackOutVoucherModel.class);
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
	 * Purpose : For getting drafted, in-process and sent-back list
	 * @param bean : ReqToBackOutVoucher
	 * @param request : HttpServletRequest
	 * @param userId : CurrentUserID
	 */
	public void getPendingList(final ReqToBackOutVoucher bean,
			final HttpServletRequest request, final String userId) {
		try {
			Object[][] draftListData = null;
			final List<ReqToBackOutVoucher> draftvoucherList = new ArrayList<ReqToBackOutVoucher>();

			// For drafted application Begins
			/*final String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, "
					+ " TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT "
					+ " WHERE VOUCHER_STATUS = 'D' AND VOUCHER_FROM_EMP="
					+ userId + " ORDER BY VOUCHER_REQUEST_ID DESC";
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
				bean.setDraftVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					final ReqToBackOutVoucher beanItt = new ReqToBackOutVoucher();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(draftListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(draftListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(draftListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(draftListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(draftListData[i][4])));
					draftvoucherList.add(beanItt);
				}
				bean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends

			// For in-Process application Begins
			Object[][] inProcessListData = null;
			final List<ReqToBackOutVoucher> inProcessVoucherList = new ArrayList<ReqToBackOutVoucher>();

			/*final String inProcessQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, " + 
					" TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT " + 
					" WHERE VOUCHER_STATUS = 'P' AND VOUCHER_FROM_EMP=" + userId + " ORDER BY VOUCHER_REQUEST_ID DESC";
			inProcessListData = this.getSqlModel().getSingleResult(inProcessQuery);*/
			inProcessListData = this.getAppropriateListFromQuery("'P'", userId);

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
				bean.setInProcessVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					final ReqToBackOutVoucher beanItt = new ReqToBackOutVoucher();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(inProcessListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(inProcessListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(inProcessListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(inProcessListData[i][4])));
					inProcessVoucherList.add(beanItt);
				}
				bean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends

			// Sent-Back application Begins
			Object[][] sentBackListData = null;
			final List<ReqToBackOutVoucher> sentBackVoucherList = new ArrayList<ReqToBackOutVoucher>();

			/*final String sentBackQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, "
					+ " TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT "
					+ " WHERE VOUCHER_STATUS = 'B' AND VOUCHER_FROM_EMP="
					+ userId + " ORDER BY VOUCHER_REQUEST_ID DESC";
			sentBackListData = this.getSqlModel().getSingleResult(sentBackQuery);*/
			
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
				bean.setSentBackVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer.parseInt(pageIndexSentBack[1]); i++) {
					final ReqToBackOutVoucher beanItt = new ReqToBackOutVoucher();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(sentBackListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(sentBackListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(sentBackListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(sentBackListData[i][4])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends

		} catch (final Exception e) {
			this.logger.info("Exception occured in getPendingList : " + e);
		}
	}

	/**
	 * Method : getApproverCommentList.
	 * Purpose : For getting approver comments
	 * @param bean : ReqToBackOutVoucher
	 * @param requestID : ApplicationID
	 */
	public void getApproverCommentList(final ReqToBackOutVoucher bean, final String requestID) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, REQ_COMMENTS, " + 
				" TO_CHAR(REQ_APPROVED_DATE, 'DD-MM-YYYY') AS REQ_APPROVED_DATE, " + 
				" DECODE(REQ_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS " + 
				" FROM HRMS_D1_REQ_BACK_DATA_PATH " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_REQ_BACK_DATA_PATH.REQ_APPROVER) " + 
				" WHERE REQ_APPLICATION_ID = " + requestID + " ORDER BY REQ_APPLICATION_ID DESC";

		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<ReqToBackOutVoucher> approverList = new ArrayList<ReqToBackOutVoucher>(apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			bean.setApproverCommentsFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final ReqToBackOutVoucher innerBean = new ReqToBackOutVoucher();
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
	 * Method : getFormRelatedInformation.
	 * Purpose : For getting application details
	 * @param reqbean : ReqToBackOutVoucher
	 */
	public void getFormRelatedInformation(final ReqToBackOutVoucher reqbean) {
		try {
			final String infoQuery = "SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(SYSDATE,'DD-MM-YYYY')," + 
					" EMP_ID, EMP_TOKEN||' - '||EMP_FNAME||' '||EMP_LNAME, TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI')" + 
					" FROM HRMS_EMP_OFFC   WHERE EMP_ID =" + reqbean.getUserEmpId();
			final Object[][] infoObj = this.getSqlModel().getSingleResult(infoQuery);
			if (infoObj != null && infoObj.length > 0) {
				reqbean.setEmployeeID(this.checkNull(String.valueOf(infoObj[0][0])));
				reqbean.setEmployeeToken(this.checkNull(String.valueOf(infoObj[0][1])));
				reqbean.setFromName(this.checkNull(String.valueOf(infoObj[0][2])));
				reqbean.setToDate(this.checkNull(String.valueOf(infoObj[0][3])));
				reqbean.setCompletedByID(this.checkNull(String.valueOf(infoObj[0][4])));
				reqbean.setCompletedByName(this.checkNull(String.valueOf(infoObj[0][5])));
				reqbean.setCompletedOn(this.checkNull(String.valueOf(infoObj[0][6])));
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in getFormRelatedInformation : " + e);
		}
	}

	/**
	 * Method : getFormRelatedInformation.
	 * Purpose : For checking whether string is null or not. If blank or null then return blank else return result.
	 * @param result : string as a final result
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
	 * Method : draftFunction.
	 * Purpose : For drafting application
	 * @param reqbean : ReqToBackOutVoucher
	 * @return : boolean
	 */
	public boolean draftFunction(final ReqToBackOutVoucher reqbean) {
		boolean result = false;
		try {
			//Tracking Number Begins
			final String trackingQuery = "SELECT NVL(MAX(VOUCHER_REQUEST_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(VOUCHER_REQUEST_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_REQ_BACK_OUT	";
			final Object[][] trackingObj = this.getSqlModel().getSingleResult(trackingQuery);
			if (trackingObj != null && trackingObj.length > 0) {
				try {
					final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					final String autoIncrementApplicationCode = model.generateApplicationCode(String.valueOf(trackingObj[0][1]), "D1-REQBACKOUT", reqbean.getUserEmpId(), String.valueOf(trackingObj[0][2]));
					reqbean.setTrackingNumber(this.checkNull(autoIncrementApplicationCode));
					model.terminate();
				} catch (final Exception e) {
					this.logger.error("Exception occured in draftFunction " + e);
				}
			}
			//Tracking Number Ends

			final Object[][] addObj = new Object[1][17];
			addObj[0][0] = reqbean.getEmployeeID();
			addObj[0][1] = reqbean.getToDate();
			addObj[0][2] = reqbean.getVendorName();
			addObj[0][3] = reqbean.getVendorNumber();
			addObj[0][4] = reqbean.getLineNumber();
			addObj[0][5] = reqbean.getQuantity();
			addObj[0][6] = reqbean.getVoucherNumber();
			addObj[0][7] = reqbean.getReasonForRequest();
			addObj[0][8] = reqbean.getRma();
			addObj[0][9] = reqbean.getAirBillNumber();
			if (this.radioButtonYes.equals(reqbean.getCreditMemoRadio())) {
				addObj[0][10] = this.radioButtonTrue;
			} else if (this.radioButtonNo.equals(reqbean.getCreditMemoRadio())) {
				addObj[0][10] = this.radioButtonFalse;
			} else {
				addObj[0][10] = "";
			}
			addObj[0][11] = reqbean.getComments();
			addObj[0][12] = reqbean.getStatus();
			addObj[0][13] = reqbean.getPurchaseOrderNumber();
			addObj[0][14] = reqbean.getTrackingNumber();
			addObj[0][15] = reqbean.getCompletedByID();
			addObj[0][16] = reqbean.getCompletedOn();

			final String draftQuery = "INSERT INTO HRMS_D1_REQ_BACK_OUT(VOUCHER_REQUEST_ID, VOUCHER_FROM_EMP, VOUCHER_REQ_TO_DATE, VOUCHER_VENDOR_NAME, " + 
					" VOUCHER_VENDOR_NUMBER, VOUCHER_LINE_NUMBER, VOUCHER_QUANTITY, VOUCHER_VOUCHER_NUMBER, VOUCHER_REASON_FOR_REQUEST, " + 
					" VOUCHER_RMA, VOUCHER_AIR_BILL_NUMBER, VOUCHER_IS_CREDIT_MEMO, VOUCHER_COMMENTS, VOUCHER_STATUS, VOUCHER_PURCHASE_ORDER_NUMBER, " + 
					" VOUCHER_TRACKING_NUMBER, VOUCHER_INITIATOR, VOUCHER_COMPLETED_ON)" + 
					" VALUES((SELECT NVL(MAX(VOUCHER_REQUEST_ID),0)+1 FROM HRMS_D1_REQ_BACK_OUT ),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'))";

			result = this.getSqlModel().singleExecute(draftQuery, addObj);
			if (result) {
				final String autoCodeQuery = " SELECT NVL(MAX(VOUCHER_REQUEST_ID),0) FROM HRMS_D1_REQ_BACK_OUT ";
				final Object[][] data = this.getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					reqbean.setRequestID(String.valueOf(data[0][0]));
				}
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in draftFunction : " + e);
		}
		return result;
	}

	/**
	 * Method : updateRecords.
	 * Purpose : For updating application data
	 * @param reqbean : ReqToBackOutVoucher
	 * @return : boolean
	 */
	public boolean updateRecords(final ReqToBackOutVoucher reqbean) {
		boolean result = false;
		try {
			final Object[][] updateObj = new Object[1][18];
			updateObj[0][0] = reqbean.getEmployeeID();
			updateObj[0][1] = reqbean.getToDate();
			updateObj[0][2] = reqbean.getVendorName();
			updateObj[0][3] = reqbean.getVendorNumber();
			updateObj[0][4] = reqbean.getLineNumber();
			updateObj[0][5] = reqbean.getQuantity();
			updateObj[0][6] = reqbean.getVoucherNumber();
			updateObj[0][7] = reqbean.getReasonForRequest();
			updateObj[0][8] = reqbean.getRma();
			updateObj[0][9] = reqbean.getAirBillNumber();
			if (this.radioButtonYes.equals(reqbean.getCreditMemoRadio())) {
				updateObj[0][10] = this.radioButtonTrue;
			} else if (this.radioButtonNo.equals(reqbean.getCreditMemoRadio())) {
				updateObj[0][10] = this.radioButtonFalse;
			} else {
				updateObj[0][10] = "";
			}
			updateObj[0][11] = reqbean.getComments();
			updateObj[0][12] = reqbean.getStatus();
			updateObj[0][13] = reqbean.getPurchaseOrderNumber();
			updateObj[0][14] = reqbean.getTrackingNumber();
			updateObj[0][15] = reqbean.getCompletedByID();
			updateObj[0][16] = reqbean.getCompletedOn();
			updateObj[0][17] = reqbean.getRequestID();

			final String updateQuery = " UPDATE HRMS_D1_REQ_BACK_OUT SET VOUCHER_FROM_EMP =?, VOUCHER_REQ_TO_DATE =TO_DATE(?,'DD-MM-YYYY'), " + 
					" VOUCHER_VENDOR_NAME =?, VOUCHER_VENDOR_NUMBER =?, VOUCHER_LINE_NUMBER =?, VOUCHER_QUANTITY =?, VOUCHER_VOUCHER_NUMBER =?, " + 
					" VOUCHER_REASON_FOR_REQUEST=? ,VOUCHER_RMA=?, VOUCHER_AIR_BILL_NUMBER=?, VOUCHER_IS_CREDIT_MEMO=?, VOUCHER_COMMENTS=?, " + 
					" VOUCHER_STATUS=?, VOUCHER_PURCHASE_ORDER_NUMBER=?, VOUCHER_TRACKING_NUMBER=?, VOUCHER_INITIATOR=?, " + 
					" VOUCHER_COMPLETED_ON=TO_DATE(?,'DD-MM-YYYY HH24:MI') WHERE VOUCHER_REQUEST_ID=? ";

			result = this.getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (final Exception e) {
			this.logger.info("Exception occured in updateRecords : " + e);
		}
		return result;
	}

	/**
	 * Method : viewApplicationFunction.
	 * Purpose : For showing application data
	 * @param reqbean : ReqToBackOutVoucher
	 * @param requestID : ApplicationID
	 */
	public void viewApplicationFunction(final ReqToBackOutVoucher reqbean, final String requestID) {
		try {
			final String viewQuery = "SELECT VOUCHER_REQUEST_ID, VOUCHER_FROM_EMP, OFFC1.EMP_TOKEN, OFFC1.EMP_FNAME||' '||OFFC1.EMP_LNAME, " + 
					" TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'), " + 
					" VOUCHER_VENDOR_NAME, VOUCHER_VENDOR_NUMBER, VOUCHER_LINE_NUMBER, VOUCHER_QUANTITY, " + 
					" VOUCHER_VOUCHER_NUMBER, VOUCHER_REASON_FOR_REQUEST, VOUCHER_RMA, VOUCHER_AIR_BILL_NUMBER, " + 
					" VOUCHER_IS_CREDIT_MEMO, VOUCHER_COMMENTS, VOUCHER_STATUS, VOUCHER_PURCHASE_ORDER_NUMBER, " + 
					" VOUCHER_TRACKING_NUMBER, VOUCHER_INITIATOR, " + 
					" OFFC2.EMP_TOKEN||' - '||OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME, TO_CHAR(VOUCHER_COMPLETED_ON,'DD-MM-YYYY HH24:MI') " + 
					" FROM HRMS_D1_REQ_BACK_OUT " + 
					" INNER JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_REQ_BACK_OUT.VOUCHER_FROM_EMP) " + 
					" INNER JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID = HRMS_D1_REQ_BACK_OUT.VOUCHER_INITIATOR) " + 
					" WHERE VOUCHER_REQUEST_ID =" + requestID;
			final Object[][] viewObj = this.getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				reqbean.setRequestID(this.checkNull(String.valueOf(viewObj[0][0])));
				reqbean.setEmployeeID(this.checkNull(String.valueOf(viewObj[0][1])));
				reqbean.setEmployeeToken(this.checkNull(String.valueOf(viewObj[0][2])));
				reqbean.setFromName(this.checkNull(String.valueOf(viewObj[0][3])));
				reqbean.setToDate(this.checkNull(String.valueOf(viewObj[0][4])));
				reqbean.setVendorName(this.checkNull(String.valueOf(viewObj[0][5])));
				reqbean.setVendorNumber(this.checkNull(String.valueOf(viewObj[0][6])));
				reqbean.setLineNumber(this.checkNull(String.valueOf(viewObj[0][7])));
				reqbean.setQuantity(this.checkNull(String.valueOf(viewObj[0][8])));
				reqbean.setVoucherNumber(this.checkNull(String.valueOf(viewObj[0][9])));
				reqbean.setReasonForRequest(this.checkNull(String.valueOf(viewObj[0][10])));
				reqbean.setRma(this.checkNull(String.valueOf(viewObj[0][11])));
				reqbean.setAirBillNumber(this.checkNull(String.valueOf(viewObj[0][12])));

				if (this.radioButtonTrue.equals(String.valueOf(viewObj[0][13]))) {
					reqbean.setDidVendorIssueCreditMemo(this.radioButtonYes);
					reqbean.setCreditMemoRadio(this.radioButtonYes);
				}
				if (this.radioButtonFalse.equals(String.valueOf(viewObj[0][13]))) {
					reqbean.setDidVendorIssueCreditMemo(this.radioButtonNo);
					reqbean.setCreditMemoRadio(this.radioButtonNo);
				}

				if ("".equals(String.valueOf(viewObj[0][13]))) {
					reqbean.setDidVendorIssueCreditMemo("");
					reqbean.setCreditMemoRadio("");
				}

				reqbean.setComments(this.checkNull(String.valueOf(viewObj[0][14])));
				reqbean.setStatus(this.checkNull(String.valueOf(viewObj[0][15])));
				reqbean.setPurchaseOrderNumber(this.checkNull(String.valueOf(viewObj[0][16])));
				reqbean.setTrackingNumber(this.checkNull(String.valueOf(viewObj[0][17])));
				reqbean.setCompletedByID(this.checkNull(String.valueOf(viewObj[0][18])));
				reqbean.setCompletedByName(this.checkNull(String.valueOf(viewObj[0][19])));
				reqbean.setCompletedOn(this.checkNull(String.valueOf(viewObj[0][20])));
				this.getApproverCommentList(reqbean, requestID);
			}
		} catch (final Exception e) {
			this.logger.info("Exception occured in viewApplicationFunction : " + e);
		}
	}

	/**
	 * Method : delRecord.
	 * Purpose : For deleting application
	 * @param bean : ReqToBackOutVoucher
	 * @return boolean : boolean
	 */
	public boolean delRecord(final ReqToBackOutVoucher bean) {
		boolean result = false;
		try {
			final String delQuery = " DELETE FROM  HRMS_D1_REQ_BACK_OUT WHERE VOUCHER_REQUEST_ID=" + bean.getRequestID();
			result = this.getSqlModel().singleExecute(delQuery);
		} catch (final Exception e) {
			this.logger.info("Exception occured in delRecord : " + e);
		}
		return result;
	}

	/**
	 * Method : sendForApprovalFunction.
	 * Purpose : For sending application for approval
	 * @param reqbean : ReqToBackOutVoucher
	 * @return boolean : boolean
	 */
	public boolean sendForApprovalFunction(final ReqToBackOutVoucher reqbean) {
		boolean result = false;
		if ("".equals(reqbean.getRequestID())) {
			result = this.draftFunction(reqbean);
		} else {
			result = this.updateRecords(reqbean);
		}
		return result;
	}

	/**
	 * Method : isCurrentUser.
	 * Purpose : For checking whether application is view to the currently login user
	 * @param userId : String
	 * @return boolean : boolean
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
	 * Method : isFinanceApproverAvailable.
	 * Purpose : For checking whether financial person is available or not. 
	 * @return boolean : boolean
	 */
	public boolean isFinanceApproverAvailable() {
		final String financeApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'F'";
		final Object[][] financeApproverObj = this.getSqlModel().getSingleResult(financeApproverSql);
		if (financeApproverObj != null && financeApproverObj.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method : cancelFunction.
	 * Purpose : For canceling application
	 * @param reqbean : ReqToBackOutVoucher
	 * @param status : Status
	 * @return boolean : boolean
	 */
	public boolean cancelFunction(final ReqToBackOutVoucher reqbean, final String status) {
		boolean result = false;
		try {
			final String changeStatusQuery = "UPDATE HRMS_D1_REQ_BACK_OUT SET VOUCHER_STATUS = '"	+ status + "'" + " WHERE VOUCHER_REQUEST_ID = " + reqbean.getRequestID();
			result = this.getSqlModel().singleExecute(changeStatusQuery);
		} catch (final Exception e) {
			this.logger.info("Exception occured in cancelFunction : " + e);
		}
		return result;
	}


	/**
	 * Method : getApprovedList.
	 * Purpose : For getting application
	 * @param reqbean : ReqToBackOutVoucher
	 * @param userId : Application ID
	 * @param request : HttpServletRequest
	 */
	public void getApprovedList(final ReqToBackOutVoucher reqbean, final HttpServletRequest request, final String userId) {
		try {
			// Approved application Begins
			Object[][] approvedListData = null;
			final List<ReqToBackOutVoucher> approvedList = new ArrayList<ReqToBackOutVoucher>();
			approvedListData = this.getAppropriateListFromQuery("'A'", userId);
			/*final String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, "
					+ " TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT "
					+ " WHERE VOUCHER_STATUS = 'A' AND VOUCHER_FROM_EMP="
					+ userId + " ORDER BY VOUCHER_REQUEST_ID DESC";
			approvedListData = this.getSqlModel().getSingleResult(selQuery);*/

			final String[] pageIndexApproved = Utility.doPaging(reqbean.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = this.zero;
				pageIndexApproved[1] = this.twenty;
				pageIndexApproved[2] = this.one;
				pageIndexApproved[3] = this.one;
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if (this.one.equals(pageIndexApproved[4])) {
				reqbean.setMyPageApproved(this.one);
			}

			if (approvedListData != null && approvedListData.length > 0) {
				reqbean.setApprovedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final ReqToBackOutVoucher beanItt = new ReqToBackOutVoucher();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				reqbean.setApprovedVoucherIteratorList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins
			Object[][] approvedCancellationListData = null;
			final List<ReqToBackOutVoucher> approvedCancellationList = new ArrayList<ReqToBackOutVoucher>();
			approvedCancellationListData = this.getAppropriateListFromQuery("'X'", userId);
			
			/*String approvedcancellationQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, "
					+ " TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT "
					+ " WHERE VOUCHER_STATUS = 'X' AND VOUCHER_FROM_EMP="
					+ userId + " ORDER BY VOUCHER_REQUEST_ID DESC";
			approvedCancellationListData = this.getSqlModel().getSingleResult(approvedcancellationQuery);*/

			final String[] pageIndexApprovedCancel = Utility.doPaging(reqbean.getMyPageApprovedCancel(),
					approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = this.zero;
				pageIndexApprovedCancel[1] = this.twenty;
				pageIndexApprovedCancel[2] = this.one;
				pageIndexApprovedCancel[3] = this.one;
				pageIndexApprovedCancel[4] = "";
			}

			request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if (this.one.equals(pageIndexApprovedCancel[4])) {
				reqbean.setMyPageApprovedCancel(this.one);
			}

			if (approvedCancellationListData != null && approvedCancellationListData.length > 0) {
				reqbean.setApprovedCancellationVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer.parseInt(pageIndexApprovedCancel[1]); i++) {
					final ReqToBackOutVoucher beanItt = new ReqToBackOutVoucher();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(approvedCancellationListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(approvedCancellationListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(approvedCancellationListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(approvedCancellationListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(approvedCancellationListData[i][4])));
					approvedCancellationList.add(beanItt);
				}
				reqbean.setApprovedCancellationVoucherIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends

		} catch (final Exception e) {
			this.logger.info("Exception occured in getApprovedList : " + e);
		}
	}

	/**
	 * Method : getCancelledList.
	 * Purpose : For getting canceled application
	 * @param reqbean : ReqToBackOutVoucher
	 * @param userId : Application ID
	 * @param request : HttpServletRequest
	 */
	public void getCancelledList(final ReqToBackOutVoucher reqbean, final HttpServletRequest request, final String userId) {
		try {
			// Cancelled application Begins
			Object[][] cancelledListData = null;
			final List<ReqToBackOutVoucher> cancelledList = new ArrayList<ReqToBackOutVoucher>();
			cancelledListData = this.getAppropriateListFromQuery("'C'", userId);
			
			/*String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, "
					+ " TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT "
					+ " WHERE VOUCHER_STATUS = 'C' AND VOUCHER_FROM_EMP="
					+ userId + " ORDER BY VOUCHER_REQUEST_ID DESC";
			cancelledListData = this.getSqlModel().getSingleResult(selQuery);*/

			final String[] pageIndexCancel = Utility.doPaging(reqbean.getMyPageCancel(), cancelledListData.length, 20);
			if (pageIndexCancel == null) {
				pageIndexCancel[0] = this.zero;
				pageIndexCancel[1] = this.twenty;
				pageIndexCancel[2] = this.one;
				pageIndexCancel[3] = this.one;
				pageIndexCancel[4] = "";
			}

			request.setAttribute("totalCancelPage", Integer.parseInt(String.valueOf(pageIndexCancel[2])));
			request.setAttribute("cancelPageNo", Integer.parseInt(String.valueOf(pageIndexCancel[3])));
			if (this.one.equals(pageIndexCancel[4])) {
				reqbean.setMyPageCancel(this.one);
			}

			if (cancelledListData != null && cancelledListData.length > 0) {
				reqbean.setCancelledVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexCancel[0]); i < Integer.parseInt(pageIndexCancel[1]); i++) {
					final ReqToBackOutVoucher beanItt = new ReqToBackOutVoucher();
					beanItt.setVouherHiddenID(this.checkNull(String.valueOf(cancelledListData[i][0])));
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(cancelledListData[i][1])));
					beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(cancelledListData[i][2])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(cancelledListData[i][3])));
					beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(cancelledListData[i][4])));
					cancelledList.add(beanItt);
				}
				reqbean.setCancelledVoucherIteratorList(cancelledList);
			}
			// Cancelled application Ends

		} catch (final Exception e) {
			this.logger.info("Exception occured in getCancelledList : " + e);
		}
	}

	/**
	 * Method : getRejectedList.
	 * Purpose : For getting rejected application
	 * @param reqbean : ReqToBackOutVoucher
	 * @param userId : Application ID
	 * @param request : HttpServletRequest
	 */
	public void getRejectedList(final ReqToBackOutVoucher reqbean, final HttpServletRequest request, final String userId) {
		// Rejected application Begins
		Object[][] rejectedListData = null;
		final List<ReqToBackOutVoucher> rejectedList = new ArrayList<ReqToBackOutVoucher>();
		rejectedListData = this.getAppropriateListFromQuery("'R'", userId);
		
		/*String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, "
				+ " TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT "
				+ " WHERE VOUCHER_STATUS = 'R' AND VOUCHER_FROM_EMP="
				+ userId
				+ " ORDER BY VOUCHER_REQUEST_ID DESC";
		rejectedListData = this.getSqlModel().getSingleResult(selQuery);*/

		final String[] pageIndexRejected = Utility.doPaging(reqbean.getMyPageRejected(), rejectedListData.length, 20);
		if (pageIndexRejected == null) {
			pageIndexRejected[0] = this.zero;
			pageIndexRejected[1] = this.twenty;
			pageIndexRejected[2] = this.one;
			pageIndexRejected[3] = this.one;
			pageIndexRejected[4] = "";
		}

		request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
		request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
		if (this.one.equals(pageIndexRejected[4])) {
			reqbean.setMyPageRejected(this.one);
		}

		if (rejectedListData != null && rejectedListData.length > 0) {
			reqbean.setRejectedVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
				final ReqToBackOutVoucher beanItt = new ReqToBackOutVoucher();
				beanItt.setVouherHiddenID(this.checkNull(String.valueOf(rejectedListData[i][0])));
				beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(rejectedListData[i][1])));
				beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(rejectedListData[i][2])));
				beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			reqbean.setRejectedVoucherIteratorList(rejectedList);
		}
		// Rejected application Ends

		// Rejected cancellation application Begins
		Object[][] rejectedCancellationListData = null;
		final List<ReqToBackOutVoucher> rejectedCancellationList = new ArrayList<ReqToBackOutVoucher>();
		rejectedCancellationListData = this.getAppropriateListFromQuery("'Z'", userId);
		/*String approvedcancellationQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, "
				+ " TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT "
				+ " WHERE VOUCHER_STATUS = 'Z' AND VOUCHER_FROM_EMP=" + userId + " ORDER BY VOUCHER_REQUEST_ID DESC";
		rejectedCancellationListData = this.getSqlModel().getSingleResult(approvedcancellationQuery);*/

		final String[] pageIndexRejectedCancellation = Utility.doPaging(reqbean.getMyPageCancelRejected(), rejectedCancellationListData.length, 20);
		if (pageIndexRejectedCancellation == null) {
			pageIndexRejectedCancellation[0] = this.zero;
			pageIndexRejectedCancellation[1] = this.twenty;
			pageIndexRejectedCancellation[2] = this.one;
			pageIndexRejectedCancellation[3] = this.one;
			pageIndexRejectedCancellation[4] = "";
		}

		request.setAttribute("totalRejectedCancellationPage", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[2])));
		request.setAttribute("rejectedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[3])));
		if (this.one.equals(pageIndexRejectedCancellation[4])) {
			reqbean.setMyPageCancelRejected(this.one);
		}

		if (rejectedCancellationListData != null && rejectedCancellationListData.length > 0) {
			reqbean.setRejectedCancelVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer.parseInt(pageIndexRejectedCancellation[1]); i++) {
				final ReqToBackOutVoucher beanItt = new ReqToBackOutVoucher();
				beanItt.setVouherHiddenID(this.checkNull(String.valueOf(rejectedCancellationListData[i][0])));
				beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(rejectedCancellationListData[i][1])));
				beanItt.setEmployeeNameIterator(this.checkNull(String.valueOf(rejectedCancellationListData[i][2])));
				beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(rejectedCancellationListData[i][3])));
				beanItt.setVouherHiddenStatus(this.checkNull(String.valueOf(rejectedCancellationListData[i][4])));
				rejectedCancellationList.add(beanItt);
			}
			reqbean.setRejectedCancelVoucherIteratorList(rejectedCancellationList);
		}
		// Rejected cancellation application Ends
	}
	
	/**
	 * Method : getAppropriateListFromQuery.
	 * Purpose : For getting status wise list of applications.
	 * @param status : String
	 * @param userId : Current User ID
	 * @return : Object.
	 */
	public Object[][] getAppropriateListFromQuery(final String status,  final String userId) {
		Object[][] result = null;
		final String selQuery = " SELECT VOUCHER_REQUEST_ID, VOUCHER_TRACKING_NUMBER, VOUCHER_VENDOR_NAME, " + 
				" TO_CHAR(VOUCHER_REQ_TO_DATE,'DD-MM-YYYY'),VOUCHER_STATUS  FROM HRMS_D1_REQ_BACK_OUT " + 
				" WHERE VOUCHER_STATUS IN (" + status + ") AND VOUCHER_FROM_EMP=" + userId + " ORDER BY VOUCHER_REQUEST_ID DESC";
		result = this.getSqlModel().getSingleResult(selQuery);

		return result;
	}
}
