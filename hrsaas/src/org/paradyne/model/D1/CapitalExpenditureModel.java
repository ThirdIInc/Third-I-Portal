package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.CapitalExpenditure;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.apache.log4j.Logger;

/**
 * @author aa1380.
 */
public class CapitalExpenditureModel extends ModelBase {
	/**	logger. *	 */
	private Logger logger = Logger.getLogger(CapitalExpenditureModel.class);
	/**	 * trueStr. */
	private String trueStr = "true";
	/**	 * falseStr. */
	private String falseStr = "false";
	/**	 * yStr. */
	private String yStr = "Y";
	/**	 * nStr. */
	private String nStr = "N";
	/**	 * yesStr. */
	private String yesStr = "yes";
	/**	 * noStr. */
	private String noStr = "no";
	/**	 * zeroStr. */
	private String zeroStr = "0";
	/**	 * oneStr. */
	private String oneStr = "1";
	/**	 * twentyStr. */
	private String twentyStr = "20";
	
	
	/**
	 * Method : isCurrentUser.
	 * Purpose : Used to check whether current login user is applicant or not.
	 * @param userId : employee id
	 * @return boolean
	 */
	public boolean isCurrentUser(final String userId) {
		final Object[][] currentUserObj = this.getSqlModel().getSingleResult(" SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method : getPendingList.
	 * Purpose : Used to check whether current login user is applicant or not.
	 * @param capitalBean : Instance of CapitalExpenditure
	 * @param userId : employee id
	 * @param request : Instance of CapitalExpenditure
	 */
	public void getPendingList(final CapitalExpenditure capitalBean,
			final HttpServletRequest request, final String userId) {
		try {
			// For drafted application Begins
			final List<CapitalExpenditure> draftvoucherList = new ArrayList<CapitalExpenditure>();
			final String selQuery = this.returnRequiredQuery("'D'", userId); 
			final Object[][] draftListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndexDrafted = Utility.doPaging(capitalBean.getMyPage(), draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = this.zeroStr;
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = this.oneStr;
				pageIndexDrafted[3] = this.oneStr;
				pageIndexDrafted[4] = "";
			}
			
			request.setAttribute("totalDraftPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals(this.oneStr)) {
				capitalBean.setMyPage(this.oneStr);
			}
			
			if (draftListData != null && draftListData.length > 0) {
				capitalBean.setDraftVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					final CapitalExpenditure beanItt = new CapitalExpenditure();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(draftListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(draftListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(draftListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(draftListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(draftListData[i][4])));
					draftvoucherList.add(beanItt);
				}
				capitalBean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends
			
			// For in-Process application Begins
			Object[][] inProcessListData = null;
			final List<CapitalExpenditure> inProcessVoucherList = new ArrayList<CapitalExpenditure>();
			final String inProcessQuery = this.returnRequiredQuery("'P','F','S'", userId); 
			inProcessListData = this.getSqlModel().getSingleResult(inProcessQuery);
			
			final String[] pageIndexInProcess = Utility.doPaging(capitalBean.getMyPageInProcess(), inProcessListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexInProcess[0] = this.zeroStr;
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = this.oneStr;
				pageIndexInProcess[3] = this.oneStr;
				pageIndexInProcess[4] = "";
			}
			
			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals(this.oneStr)) {
				capitalBean.setMyPageInProcess(this.oneStr);
			}
			
			if (inProcessListData != null && inProcessListData.length > 0) {
				capitalBean.setInProcessVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					final CapitalExpenditure beanItt = new CapitalExpenditure();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(inProcessListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(inProcessListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(inProcessListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(inProcessListData[i][4])));
					inProcessVoucherList.add(beanItt);
				}
				capitalBean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends
			
			
			// Sent-Back application Begins
			Object[][] sentBackListData = null;
			final List<CapitalExpenditure> sentBackVoucherList = new ArrayList<CapitalExpenditure>();
			final String sentBackQuery = this.returnRequiredQuery("'B'", userId);  
			sentBackListData = this.getSqlModel().getSingleResult(sentBackQuery);
			
			final String[] pageIndexSentBack = Utility.doPaging(capitalBean.getMyPageSentBack(), sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = this.zeroStr;
				pageIndexSentBack[1] = "20";
				pageIndexSentBack[2] = this.oneStr;
				pageIndexSentBack[3] = this.oneStr;
				pageIndexSentBack[4] = "";
			}
			
			request.setAttribute("totalSentBackPage", Integer.parseInt(String.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String.valueOf(pageIndexSentBack[3])));
			if (pageIndexSentBack[4].equals(this.oneStr)) {
				capitalBean.setMyPageSentBack(this.oneStr);
			}
			
			if (sentBackListData != null && sentBackListData.length > 0) {
				capitalBean.setSentBackVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer.parseInt(pageIndexSentBack[1]); i++) {
					final CapitalExpenditure beanItt = new CapitalExpenditure();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(sentBackListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(sentBackListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(sentBackListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(sentBackListData[i][4])));
					sentBackVoucherList.add(beanItt);
				}
				capitalBean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method : getApprovedList.
	 * Purpose : get all approved application.
	 * @param bean : Instance of CapitalExpenditure
	 * @param userId : employee id
	 * @param request : Instance of HttpServletRequest
	 */
	public void getApprovedList(final CapitalExpenditure bean,
			final HttpServletRequest request, final String userId) {
		try {
			final List<CapitalExpenditure> approvedList = new ArrayList<CapitalExpenditure>();
			
			// Approved application Begins
			final String selQuery = this.returnRequiredQuery("'A'", userId);   
			final Object[][] approvedListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = this.zeroStr;
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = this.oneStr;
				pageIndexApproved[3] = this.oneStr;
				pageIndexApproved[4] = "";
			}
			
			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals(this.oneStr)) {
				bean.setMyPageApproved(this.oneStr);
			}
			
			if (approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final CapitalExpenditure beanItt = new CapitalExpenditure();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				bean.setApprovedVoucherIteratorList(approvedList);
			}
			// Approved application Ends
			
			// Approved cancellation application Begins
			final List<CapitalExpenditure> approvedCancellationList = new ArrayList<CapitalExpenditure>();
			final String approvedcancellationQuery = this.returnRequiredQuery("'X'", userId);    
			final Object[][] approvedCancellationListData = this.getSqlModel().getSingleResult(approvedcancellationQuery);
			final String[] pageIndexApprovedCancel = Utility.doPaging(bean.getMyPageApprovedCancel(), approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = this.zeroStr;
				pageIndexApprovedCancel[1] = "20";
				pageIndexApprovedCancel[2] = this.oneStr;
				pageIndexApprovedCancel[3] = this.oneStr;
				pageIndexApprovedCancel[4] = "";
			}
			
			request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if (pageIndexApprovedCancel[4].equals(this.oneStr)) {
				bean.setMyPageApprovedCancel(this.oneStr);
			}
			
			if (approvedCancellationListData != null && approvedCancellationListData.length > 0) {
				bean.setApprovedCancellationVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer.parseInt(pageIndexApprovedCancel[1]); i++) {
					final CapitalExpenditure beanItt = new CapitalExpenditure();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(approvedCancellationListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(approvedCancellationListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(approvedCancellationListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(approvedCancellationListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(approvedCancellationListData[i][4])));
					approvedCancellationList.add(beanItt);
				}
				bean.setApprovedCancellationVoucherIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Method : getCancelledList.
	 * Purpose : get all cancel application.
	 * @param bean : Instance of CapitalExpenditure
	 * @param userId : employee id
	 * @param request : Instance of HttpServletRequest
	 */
	public void getCancelledList(final CapitalExpenditure bean,
			final HttpServletRequest request, final String userId) {
		try {
			final List<CapitalExpenditure> cancelledList = new ArrayList<CapitalExpenditure>();
			// Cancelled application Begins
			final String selQuery = this.returnRequiredQuery("'C'", userId);     
			final Object[][] cancelledListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndexCancel = Utility.doPaging(bean.getMyPageCancel(), cancelledListData.length, 20);
			if (pageIndexCancel == null) {
				pageIndexCancel[0] = this.zeroStr;
				pageIndexCancel[1] = "20";
				pageIndexCancel[2] = this.oneStr;
				pageIndexCancel[3] = this.oneStr;
				pageIndexCancel[4] = "";
			}
			
			request.setAttribute("totalCancelPage", Integer.parseInt(String.valueOf(pageIndexCancel[2])));
			request.setAttribute("cancelPageNo", Integer.parseInt(String.valueOf(pageIndexCancel[3])));
			if (pageIndexCancel[4].equals(this.oneStr)) {
				bean.setMyPageCancel(this.oneStr);
			}
			
			if (cancelledListData != null && cancelledListData.length > 0) {
				bean.setCancelledVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexCancel[0]); i < Integer.parseInt(pageIndexCancel[1]); i++)  {
					final CapitalExpenditure beanItt = new CapitalExpenditure();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(cancelledListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(cancelledListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(cancelledListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(cancelledListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(cancelledListData[i][4])));
					cancelledList.add(beanItt);
				}
				bean.setCancelledVoucherIteratorList(cancelledList);
			}
			// Cancelled application Ends
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method : getRejectedList.
	 * Purpose : get all rejected application.
	 * @param bean : Instance of CapitalExpenditure
	 * @param userId : employee id
	 * @param request : Instance of HttpServletRequest
	 */
	public void getRejectedList(final CapitalExpenditure bean,
			final HttpServletRequest request, final String userId) {

		final List<CapitalExpenditure> rejectedList = new ArrayList<CapitalExpenditure>();
		// Rejected application Begins
		final String selQuery = this.returnRequiredQuery("'R'", userId); 
		final Object[][] rejectedListData = this.getSqlModel().getSingleResult(selQuery);
			
		final String[] pageIndexRejected = Utility.doPaging(bean.getMyPageRejected(), rejectedListData.length, 20);
		if (pageIndexRejected == null) {
			pageIndexRejected[0] = this.zeroStr;
			pageIndexRejected[1] = "20";
			pageIndexRejected[2] = this.oneStr;
			pageIndexRejected[3] = this.oneStr;
			pageIndexRejected[4] = "";
		}
		
		request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
		request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
		if (pageIndexRejected[4].equals(this.oneStr)) {
			bean.setMyPageRejected(this.oneStr);
		}
		
		if (rejectedListData != null && rejectedListData.length > 0) {
			bean.setRejectedVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++)  {
				final CapitalExpenditure beanItt = new CapitalExpenditure();
				beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(rejectedListData[i][0])));
				beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(rejectedListData[i][1])));
				beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(rejectedListData[i][2])));
				beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			bean.setRejectedVoucherIteratorList(rejectedList);
		}
		// Rejected application Ends
		
		// Rejected cancellation application Begins
		final List<CapitalExpenditure> rejectedCancellationList = new ArrayList<CapitalExpenditure>();
		final String rejectedcancellationQuery =  this.returnRequiredQuery("'Z'", userId); 
		final Object[][] rejectedCancellationListData = this.getSqlModel().getSingleResult(rejectedcancellationQuery);
		final String[] pageIndexRejectedCancellation = Utility.doPaging(bean.getMyPageCancelRejected(), rejectedCancellationListData.length, 20);
		if (pageIndexRejectedCancellation == null) {
			pageIndexRejectedCancellation[0] = this.zeroStr;
			pageIndexRejectedCancellation[1] = "20";
			pageIndexRejectedCancellation[2] = this.oneStr;
			pageIndexRejectedCancellation[3] = this.oneStr;
			pageIndexRejectedCancellation[4] = "";
		}
		
		request.setAttribute("totalRejectedCancellationPage", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[2])));
		request.setAttribute("rejectedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[3])));
		if (pageIndexRejectedCancellation[4].equals(this.oneStr)) {
			bean.setMyPageCancelRejected(this.oneStr);
		}
		
		if (rejectedCancellationListData != null && rejectedCancellationListData.length > 0) {
			bean.setRejectedCancelVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer.parseInt(pageIndexRejectedCancellation[1]); i++) {
				final CapitalExpenditure beanItt = new CapitalExpenditure();
				beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][0])));
				beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][1])));
				beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][2])));
				beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][3])));
				beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][4])));
				rejectedCancellationList.add(beanItt);
			}
			bean.setRejectedCancelVoucherIteratorList(rejectedCancellationList);
		}
		// Rejected cancellation application Ends
	}


	/**
	 * Method : checkDouble.
	 * Purpose : Used to check double values
	 * @param result : result
	 * @return String
	 */
	public String checkDouble(final String result) {
		final String defaultVal = "0.0";
		if (result == null || "".equals(result) || defaultVal.equals(result) || "null".equals(result)) {
			return defaultVal;
		} else {
			return result;
		}
	}

	/**Used to get current user details.
	 * @param capitalBean : Instance of CapitalExpenditure
	 */
	public void getcurrentUserInformation(final CapitalExpenditure capitalBean) {
		try {
			final Object[][] queryObj = this.getSqlModel().getSingleResult("SELECT EMP_ID,EMP_FNAME||' '||EMP_LNAME, TO_CHAR(SYSDATE,'DD-MM-YYYY') " + 
																	 " FROM HRMS_EMP_OFFC WHERE EMP_ID = " + capitalBean.getUserEmpId());
			if (queryObj != null && queryObj.length > 0) {
				capitalBean.setSubmittedByID(Utility.checkNull(String.valueOf(queryObj[0][0])));
				capitalBean.setSubmittedByName(Utility.checkNull(String.valueOf(queryObj[0][1])));
				capitalBean.setSubmittedDate(Utility.checkNull(String.valueOf(queryObj[0][2])));
			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in getcurrentUserInformation() : " + e);
		}
	}
	
	/** Used to save application data.
	 * @param capitalBean : instance of CapitalExpenditure
	 * @param request : instance of HttpServletRequest
	 * @return boolean
	 */
	public boolean draftFunction(final CapitalExpenditure capitalBean, final HttpServletRequest request) {
		boolean saveCapitalExpResult = false;
		boolean saveDetailListResult = false;
		boolean finalResult = false;
		try {
			// Tracking Number Begins
			final String trackingQuery = "SELECT NVL(MAX(EXP_CODE),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'), NVL(MAX(EXP_CODE),0)+1, TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_D1_CAPITALEXP ";
			final Object[][] trackingObj = this.getSqlModel().getSingleResult(trackingQuery);
			if (trackingObj != null && trackingObj.length > 0) {
				try {
					final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					final String autoIncrementApplicationCode = model.generateApplicationCode(String.valueOf(trackingObj[0][1]), "D1-CEAR",
									capitalBean.getUserEmpId(), String.valueOf(trackingObj[0][2]));
					capitalBean.setTrackingNumber(Utility.checkNull(autoIncrementApplicationCode));
					model.terminate();
				} catch (final Exception e) {
					this.logger.error("Exception occured in (Draft)ApplCodeTemplateModel" + e);
				}
			}
			// Tracking Number Ends

			Object[][] addCapitalHeader = null;

			// Inserting records into HRMS_ACCIDENT_HDR Begins
			addCapitalHeader = new Object[1][42];
			addCapitalHeader[0][0] = capitalBean.getSubmittedByID();
			addCapitalHeader[0][1] = capitalBean.getSubmittedDate();
			if (capitalBean.getOriginalCheckbox().equals(this.trueStr)) {
				addCapitalHeader[0][2] = this.yStr;
			} else {
				addCapitalHeader[0][2] = this.nStr;
			}

			if (capitalBean.getPurchaseDeptCheckbox().equals(this.trueStr)) {
				addCapitalHeader[0][3] = this.yStr;
			} else {
				addCapitalHeader[0][3] = this.nStr;
			}

			if (capitalBean.getComputerITCheckbox().equals(this.trueStr)) {
				addCapitalHeader[0][4] = this.yStr;
			} else {
				addCapitalHeader[0][4] = this.nStr;
			}

			if (capitalBean.getLocalPurchaseCheckbox().equals(this.trueStr)) {
				addCapitalHeader[0][5] = this.yStr;
			} else {
				addCapitalHeader[0][5] = this.nStr;
			}

			addCapitalHeader[0][6] = capitalBean.getBusinessJustification();
			addCapitalHeader[0][7] = capitalBean.getReasonForLocalPurchase();
			addCapitalHeader[0][8] = capitalBean.getDateRequired();
			addCapitalHeader[0][9] = capitalBean.getLocationID();
			addCapitalHeader[0][10] = capitalBean.getIfOtherLocation();
			addCapitalHeader[0][11] = capitalBean.getAttention();
			if (capitalBean.getQuotesAttachedRadioOptionValue().equals(
					this.yesStr)) {
				addCapitalHeader[0][12] = this.yStr;
			} else {
				addCapitalHeader[0][12] = this.nStr;
			}
			addCapitalHeader[0][13] = capitalBean.getQuotesReason();
			addCapitalHeader[0][14] = capitalBean.getUploadFileName();
			addCapitalHeader[0][15] = capitalBean.getComments();
			addCapitalHeader[0][16] = capitalBean.getCostSubTotal();
			addCapitalHeader[0][17] = capitalBean.getInvoicePoNumber();
			addCapitalHeader[0][18] = capitalBean.getAssetSubTotal();
			addCapitalHeader[0][19] = capitalBean.getCostInstallation();
			addCapitalHeader[0][20] = capitalBean.getInvoiceVendorID();
			addCapitalHeader[0][21] = capitalBean.getActualInstallation();
			addCapitalHeader[0][22] = capitalBean.getCostMaterial();
			addCapitalHeader[0][23] = capitalBean.getInvoiceTotal();
			addCapitalHeader[0][24] = capitalBean.getActualMaterial();
			addCapitalHeader[0][25] = capitalBean.getCostFreight();
			addCapitalHeader[0][26] = capitalBean.getActualFreight();
			addCapitalHeader[0][27] = capitalBean.getCostTax();
			addCapitalHeader[0][28] = capitalBean.getDateTagged();
			addCapitalHeader[0][29] = capitalBean.getActualTax();
			addCapitalHeader[0][30] = capitalBean.getCostTotal();
			addCapitalHeader[0][31] = capitalBean.getActualTotal();
			addCapitalHeader[0][32] = capitalBean.getStatus();
			addCapitalHeader[0][33] = capitalBean.getForwardedManagerID();
			addCapitalHeader[0][34] = capitalBean.getShipToCompany();
			addCapitalHeader[0][35] = capitalBean.getCity();
			addCapitalHeader[0][36] = capitalBean.getState();
			addCapitalHeader[0][37] = capitalBean.getStreetAddress();
			addCapitalHeader[0][38] = capitalBean.getZipCode();
			addCapitalHeader[0][39] = capitalBean.getTelePhoneNumber();
			addCapitalHeader[0][40] = capitalBean.getTrackingNumber();
			addCapitalHeader[0][41] = capitalBean.getDepartmentNumber();

			final String insertCapitalHeaderQuery = "INSERT INTO HRMS_D1_CAPITALEXP " + 
					" (EXP_CODE, EXP_EMP_ID, EXP_APPLICATION_DATE, EXP_ORIGINAL, " + 
					" EXP_PURCHASE_DEPT, EXP_COMP_IT, EXP_LOCAL_PURCHASE, EXP_BUSS_JUSTFICATION," + 
					" EXP_RES_LOCAL_PURCH, EXP_DATE_REQUIRED, EXP_LOCATION, EXP_OTHER_LOCATION, " + 
					" EXP_ATTEN, EXP_IS_QUOTES, EXP_QUOTES_REASON, EXP_OTHER_ATTACH, " + 
					" EXP_ADDITIONAL_COMMENTS, EXP_COST_BRKDWN_SUB_TOTAL, EXP_PO_NO, EXP_ASSET_SUB_TOTAL, " + 
					" EXP_INSTALL_COST, EXP_VENDOR, EXP_ACT_INSTALL_COST, EXP_MATERIAL_COST, " + 
					" EXP_INVOICE_TOTAL, EXP_ACT_MATERIAL_COST, EXP_FREIGHT, EXP_ACT_FREIGHT, " + 
					" EXP_TAX, EXP_DATE_TAGGED, EXP_ACT_TAX, EXP_BRK_DWN_TOAL, " + 
					" EXP_ACT_TOTAL, EXP_APPLICATION_STATUS, EXP_APPROVER_CODE, " + 
					" EXP_SHIP_TO_COMPANY, EXP_CITY, EXP_STATE, EXP_STREET_ADDRESS, " + 
					" EXP_ZIPCODE, EXP_PHONE, EXP_TRACKING_NUMBER, EXP_DEPT_NUMBER)" + 
					" VALUES ((SELECT NVL(MAX(EXP_CODE),0)+1 FROM HRMS_D1_CAPITALEXP),?,TO_DATE(?,'DD-MM-YYYY'),?," + 
					" ?, ?,?,?," + " ?,TO_DATE(?,'DD-MM-YYYY'),?,?, " + 
					" ?,? , ?,?, " + 
					" ?, ?, ?,?, " + 
					" ?, ?, ?, ?, " + 
					" ?, ?, ?, ?,  " + 
					" ?,TO_DATE(?,'DD-MM-YYYY'),?,?," + " ?,?,?," + 
					" ?,?,?,?," + " ?,?,?,?)";

			saveCapitalExpResult = this.getSqlModel().singleExecute(
					insertCapitalHeaderQuery, addCapitalHeader);

			if (saveCapitalExpResult) {
				final Object[][] data = this.getSqlModel().getSingleResult("SELECT MAX(EXP_CODE) FROM HRMS_D1_CAPITALEXP");
				capitalBean.setCapitalExpID(String.valueOf(data[0][0]));

				final Object[][] maxCodeDescriptionObj = this.getSqlModel().getSingleResult("SELECT NVL(MAX(EXP_ITEM_NO),0) FROM HRMS_D1_CAPITALEXP_DESC");
				final int maxCodeDescription = Integer.parseInt("" + maxCodeDescriptionObj[0][0]);
				// Inserting Records into HRMS_D1_CAPITALEXP_DESC Begins
				Object[][] addDetail = null;
				int k = 0;

				addDetail = new Object[10][10];
				for (int i = 1; i <= 10; i++) {
					addDetail[k][0] = data[0][0];
					addDetail[k][1] = i;
					addDetail[k][2] = request.getParameter("quantity" + i);
					addDetail[k][3] = request.getParameter("description" + i);
					// addDetail[k][4] = request.getParameter("vendorID"+i);
					addDetail[k][4] = request.getParameter("vendorName" + i);
					addDetail[k][5] = request.getParameter("unitPrice" + i);
					addDetail[k][6] = request.getParameter("totalCost" + i);
					addDetail[k][7] = request.getParameter("tagNumber" + i);
					addDetail[k][8] = request.getParameter("serialNumber" + i);
					addDetail[k][9] = request.getParameter("cearPrice" + i);
					k++;
				}

				addDetail[0][0] = data[0][0];
				addDetail[0][0] = data[0][0];

				final String detailQuery = "INSERT INTO  HRMS_D1_CAPITALEXP_DESC (EXP_CODE, EXP_ITEM_NO, EXP_QUANTITY, EXP_DESC, EXP_VENDOR_NAME, " + 
						" EXP_UNIT_PRICE, EXP_TOTAL_COST,  EXP_TAG_NO, EXP_SERIAL_NO, EXP_CEAR_PRICE) " + 
						" VALUES(?,?,?,?,?, ?, ?,?,?,?)";
				saveDetailListResult = this.getSqlModel().singleExecute(
						detailQuery, addDetail);
				// Inserting Records into HRMS_D1_CAPITALEXP_DESC Ends

			}
		} catch (final Exception e) {
			this.logger.error(" Exception occured in model draftFunction() : " + e);
			e.printStackTrace();
		}

		if (saveCapitalExpResult & saveDetailListResult) {
			finalResult = true;
		}
		return finalResult;
	}

	/** Used to update application data.
	 * @param capitalBean : instance of CapitalExpenditure
	 * @param request : instance of HttpServletRequest
	 * @return boolean
	 */
	public boolean updateRecords(final CapitalExpenditure capitalBean, final HttpServletRequest request) {
		boolean updateCapitalExpResult = false;
		boolean updateDetailListResult = false;
		boolean finalResult = false;
		try {
			// Delete records from HRMS_D1_CAPITALEXP_DESC and then insert into
			// HRMS_D1_CAPITALEXP_DESC and update HRMS_ACCIDENT_HDR
			final String delDetailsQuery = "DELETE FROM HRMS_D1_CAPITALEXP_DESC WHERE EXP_CODE=" + capitalBean.getCapitalExpID();
			this.getSqlModel().singleExecute(delDetailsQuery);

			Object[][] updateCapitalHeader = null;

			// Inserting records into HRMS_ACCIDENT_HDR Begins
			updateCapitalHeader = new Object[1][42];
			updateCapitalHeader[0][0] = capitalBean.getSubmittedByID();
			updateCapitalHeader[0][1] = capitalBean.getSubmittedDate();
			if (capitalBean.getOriginalCheckbox().equals(this.trueStr)) {
				updateCapitalHeader[0][2] = this.yStr;
			} else {
				updateCapitalHeader[0][2] = this.nStr;
			}

			if (capitalBean.getPurchaseDeptCheckbox().equals(this.trueStr)) {
				updateCapitalHeader[0][3] = this.yStr;
			} else {
				updateCapitalHeader[0][3] = this.nStr;
			}

			if (capitalBean.getComputerITCheckbox().equals(this.trueStr)) {
				updateCapitalHeader[0][4] = this.yStr;
			} else {
				updateCapitalHeader[0][4] = this.nStr;
			}

			if (capitalBean.getLocalPurchaseCheckbox().equals(this.trueStr)) {
				updateCapitalHeader[0][5] = this.yStr;
			} else {
				updateCapitalHeader[0][5] = this.nStr;
			}

			updateCapitalHeader[0][6] = capitalBean.getBusinessJustification();
			updateCapitalHeader[0][7] = capitalBean.getReasonForLocalPurchase();
			updateCapitalHeader[0][8] = capitalBean.getDateRequired();
			updateCapitalHeader[0][9] = capitalBean.getLocationID();
			updateCapitalHeader[0][10] = capitalBean.getIfOtherLocation();
			updateCapitalHeader[0][11] = capitalBean.getAttention();
			if (capitalBean.getQuotesAttachedRadioOptionValue().equals(this.yesStr)) {
				updateCapitalHeader[0][12] = this.yStr;
			} else {
				updateCapitalHeader[0][12] = this.nStr;
			}
			updateCapitalHeader[0][13] = capitalBean.getQuotesReason();
			updateCapitalHeader[0][14] = capitalBean.getUploadFileName();
			updateCapitalHeader[0][15] = capitalBean.getComments();
			updateCapitalHeader[0][16] = capitalBean.getCostSubTotal();
			updateCapitalHeader[0][17] = capitalBean.getInvoicePoNumber();
			updateCapitalHeader[0][18] = capitalBean.getAssetSubTotal();
			updateCapitalHeader[0][19] = capitalBean.getCostInstallation();
			updateCapitalHeader[0][20] = capitalBean.getInvoiceVendorID();
			updateCapitalHeader[0][21] = capitalBean.getActualInstallation();
			updateCapitalHeader[0][22] = capitalBean.getCostMaterial();
			updateCapitalHeader[0][23] = capitalBean.getInvoiceTotal();
			updateCapitalHeader[0][24] = capitalBean.getActualMaterial();
			updateCapitalHeader[0][25] = capitalBean.getCostFreight();
			updateCapitalHeader[0][26] = capitalBean.getActualFreight();
			updateCapitalHeader[0][27] = capitalBean.getCostTax();
			updateCapitalHeader[0][28] = capitalBean.getDateTagged();
			updateCapitalHeader[0][29] = capitalBean.getActualTax();
			updateCapitalHeader[0][30] = capitalBean.getCostTotal();
			updateCapitalHeader[0][31] = capitalBean.getActualTotal();
			updateCapitalHeader[0][32] = capitalBean.getStatus();
			updateCapitalHeader[0][33] = capitalBean.getForwardedManagerID();
			updateCapitalHeader[0][34] = capitalBean.getShipToCompany();
			updateCapitalHeader[0][35] = capitalBean.getCity();
			updateCapitalHeader[0][36] = capitalBean.getState();
			updateCapitalHeader[0][37] = capitalBean.getStreetAddress();
			updateCapitalHeader[0][38] = capitalBean.getZipCode();
			updateCapitalHeader[0][39] = capitalBean.getTelePhoneNumber();
			updateCapitalHeader[0][40] = capitalBean.getTrackingNumber();
			updateCapitalHeader[0][41] = capitalBean.getDepartmentNumber();

			final String updateCapitalHeaderQuery = "UPDATE HRMS_D1_CAPITALEXP " + 
					" SET EXP_EMP_ID=?, EXP_APPLICATION_DATE=TO_DATE(?,'DD-MM-YYYY'), EXP_ORIGINAL=?, " + 
					" EXP_PURCHASE_DEPT=?, EXP_COMP_IT=?, EXP_LOCAL_PURCHASE=?, EXP_BUSS_JUSTFICATION=?," + 
					" EXP_RES_LOCAL_PURCH=?, EXP_DATE_REQUIRED=TO_DATE(?,'DD-MM-YYYY'), EXP_LOCATION=?, EXP_OTHER_LOCATION=?, " + 
					" EXP_ATTEN=?, EXP_IS_QUOTES=?, EXP_QUOTES_REASON=?, EXP_OTHER_ATTACH=?, " + 
					" EXP_ADDITIONAL_COMMENTS=?, EXP_COST_BRKDWN_SUB_TOTAL=?, EXP_PO_NO=?, EXP_ASSET_SUB_TOTAL=?, " + 
					" EXP_INSTALL_COST=?, EXP_VENDOR=?, EXP_ACT_INSTALL_COST=?, EXP_MATERIAL_COST=?, " + 
					" EXP_INVOICE_TOTAL=?, EXP_ACT_MATERIAL_COST=?, EXP_FREIGHT=?, EXP_ACT_FREIGHT=?, " + 
					" EXP_TAX=?, EXP_DATE_TAGGED=TO_DATE(?,'DD-MM-YYYY'), EXP_ACT_TAX=?, EXP_BRK_DWN_TOAL=?, " + 
					" EXP_ACT_TOTAL=?, EXP_APPLICATION_STATUS=?, EXP_APPROVER_CODE=?, " + 
					" EXP_SHIP_TO_COMPANY=?, EXP_CITY=?, EXP_STATE=?, EXP_STREET_ADDRESS=?, " + 
					" EXP_ZIPCODE=?, EXP_PHONE=?, EXP_TRACKING_NUMBER=?, EXP_DEPT_NUMBER=? WHERE EXP_CODE=" + capitalBean.getCapitalExpID();

			updateCapitalExpResult = this.getSqlModel().singleExecute(updateCapitalHeaderQuery, updateCapitalHeader);
			if (updateCapitalExpResult) {
				// Inserting Records into HRMS_D1_CAPITALEXP_DESC Begins
				Object[][] addDetail = null;
				int k = 0;

				addDetail = new Object[10][10];
				for (int i = 1; i <= 10; i++) {
					addDetail[k][0] = capitalBean.getCapitalExpID();
					addDetail[k][1] = i;
					addDetail[k][2] = request.getParameter("quantity" + i);
					addDetail[k][3] = request.getParameter("description" + i);
					// addDetail[k][4] = request.getParameter("vendorID"+i);
					addDetail[k][4] = request.getParameter("vendorName" + i);
					addDetail[k][5] = request.getParameter("unitPrice" + i);
					addDetail[k][6] = request.getParameter("totalCost" + i);
					addDetail[k][7] = request.getParameter("tagNumber" + i);
					addDetail[k][8] = request.getParameter("serialNumber" + i);
					addDetail[k][9] = request.getParameter("cearPrice" + i);
					k++;
				}
				final String detailQuery = "INSERT INTO HRMS_D1_CAPITALEXP_DESC (EXP_CODE, EXP_ITEM_NO, EXP_QUANTITY, EXP_DESC, EXP_VENDOR_NAME, " + 
						" EXP_UNIT_PRICE, EXP_TOTAL_COST, EXP_TAG_NO, EXP_SERIAL_NO, EXP_CEAR_PRICE) " + 
						" VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?)";
				updateDetailListResult = this.getSqlModel().singleExecute(
						detailQuery, addDetail);
				// Inserting Records into HRMS_D1_CAPITALEXP_DESC Ends

			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in model draftFunction() : " + e);
			e.printStackTrace();
		}

		if (updateCapitalExpResult & updateDetailListResult) {
			finalResult = true;
		}
		return finalResult;
	}

	/**Used to get selected branch details.
	 * @param capitalBean : Instance of CapitalExpenditure
	 */
	public void getBranchRelatedInformation(final CapitalExpenditure capitalBean) {
		try {
			final String branchInfoQuery = "SELECT  CENTER_NAME AS LOCATION,CENTER_CITY,CENTER_ID AS LOCATIONID, CENTER_ADDRESS1||' '||CENTER_ADDRESS2||' '||CENTER_ADDRESS3, " + 
									" CITY.LOCATION_NAME AS CITY, STATE.LOCATION_NAME AS STATE, CENTER_PINCODE, CENTER_TELEPHONE " + 
									" FROM HRMS_CENTER " + 
									" LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_CENTER.CENTER_LOCATION) " + 
									" LEFT JOIN HRMS_LOCATION STATE ON(STATE.LOCATION_CODE = CITY.LOCATION_PARENT_CODE) "  + 
									" WHERE CENTER_ID = " + capitalBean.getLocationID();
			final Object[][] branchObj = this.getSqlModel().getSingleResult(branchInfoQuery);
			if (branchObj != null && branchObj.length > 0) {
				
				capitalBean.setLocation(Utility.checkNull(String.valueOf(branchObj[0][0])));
				capitalBean.setCityName(Utility.checkNull(String.valueOf(branchObj[0][1])));
				capitalBean.setLocationID(Utility.checkNull(String.valueOf(branchObj[0][2])));
				capitalBean.setStreetAddress(Utility.checkNull(String.valueOf(branchObj[0][3])));
				capitalBean.setCity(Utility.checkNull(String.valueOf(branchObj[0][4])));
				capitalBean.setState(Utility.checkNull(String.valueOf(branchObj[0][5])));
				capitalBean.setZipCode(Utility.checkNull(String.valueOf(branchObj[0][6])));
				capitalBean.setTelePhoneNumber(Utility.checkNull(String.valueOf(branchObj[0][7])));
			}
		} catch (final Exception e) {
			this.logger.error("Exception occured  in getBranchRelatedInformation(): " + e);
			e.printStackTrace();
		}
		
	}

	/**Used to view application details.
	 * @param capitalBean : instance of CapitalExpenditure
	 * @param capitalHiddenID : application id
	 * @param hiddenStatus : status
	 * @param request : instance of HttpServletRequest
	 */
	public void viewApplication(final CapitalExpenditure capitalBean, final String capitalHiddenID, final String hiddenStatus, final HttpServletRequest request) {
		try {
			final String viewQuery = " SELECT EXP_CODE, EXP_EMP_ID,  OFFC1.EMP_FNAME||' '||OFFC1.EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), " + 
							   " EXP_ORIGINAL, EXP_PURCHASE_DEPT, EXP_COMP_IT, EXP_LOCAL_PURCHASE, " + 
							   " EXP_BUSS_JUSTFICATION, EXP_RES_LOCAL_PURCH, TO_CHAR(EXP_DATE_REQUIRED,'DD-MM-YYYY'), " + 
							   " CENTER_NAME, EXP_OTHER_LOCATION, EXP_ATTEN, EXP_IS_QUOTES, " + 
							   " EXP_QUOTES_REASON, EXP_OTHER_ATTACH, EXP_ADDITIONAL_COMMENTS, EXP_COST_BRKDWN_SUB_TOTAL, " + 
							   " EXP_PO_NO, EXP_ASSET_SUB_TOTAL, EXP_INSTALL_COST, EXP_VENDOR, " + 
							   " EXP_ACT_INSTALL_COST, EXP_MATERIAL_COST, EXP_INVOICE_TOTAL, EXP_ACT_MATERIAL_COST, " + 
							   " EXP_FREIGHT, EXP_ACT_FREIGHT, EXP_TAX, TO_CHAR(EXP_DATE_TAGGED,'DD-MM-YYYY'), " + 
							   " EXP_ACT_TAX, EXP_BRK_DWN_TOAL, EXP_ACT_TOTAL, EXP_SHIP_TO_COMPANY, " + 
							   " EXP_CITY, EXP_STATE, EXP_STREET_ADDRESS, EXP_ZIPCODE, " + 
							   " EXP_PHONE, '_'||EXP_APPLICATION_STATUS, OFFC2.EMP_ID, OFFC2.EMP_TOKEN, " + 
							   " OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME, EXP_APPROVER_TYPE, EXP_DEPT_NUMBER, EXP_VENDOR, EXP_TRACKING_NUMBER " + 
							   " ,EXP_LOCATION,PO_NUMBER, PO_ATTACHMENT FROM HRMS_D1_CAPITALEXP " + 
							   " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID =  HRMS_D1_CAPITALEXP.EXP_EMP_ID) " + 
							   " LEFT JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID =  HRMS_D1_CAPITALEXP.EXP_APPROVER_CODE) " + 
							   " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_D1_CAPITALEXP.EXP_LOCATION) WHERE EXP_CODE =" + capitalHiddenID;
			final Object[][] viewObj = this.getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				capitalBean.setCapitalExpID(Utility.checkNull(String.valueOf(viewObj[0][0])));
				capitalBean.setSubmittedByID(Utility.checkNull(String.valueOf(viewObj[0][1])));
				capitalBean.setSubmittedByName(Utility.checkNull(String.valueOf(viewObj[0][2])));
				capitalBean.setSubmittedDate(Utility.checkNull(String.valueOf(viewObj[0][3])));
				if (String.valueOf(viewObj[0][4]).equals(this.yStr)) {
					capitalBean.setOriginalCheckbox(this.trueStr);
				} else {
					capitalBean.setOriginalCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][5]).equals(this.yStr)) {
					capitalBean.setPurchaseDeptCheckbox(this.trueStr);
				} else {
					capitalBean.setPurchaseDeptCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][6]).equals(this.yStr)) {
					capitalBean.setComputerITCheckbox(this.trueStr);
				} else {
					capitalBean.setComputerITCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][7]).equals(this.yStr)) {
					capitalBean.setLocalPurchaseCheckbox(this.trueStr);
				} else {
					capitalBean.setLocalPurchaseCheckbox(this.falseStr);
				}

				capitalBean.setBusinessJustification(Utility.checkNull(String.valueOf(viewObj[0][8])));
				capitalBean.setReasonForLocalPurchase(Utility.checkNull(String.valueOf(viewObj[0][9])));
				capitalBean.setDateRequired(Utility.checkNull(String.valueOf(viewObj[0][10])));
				capitalBean.setLocation(Utility.checkNull(String.valueOf(viewObj[0][11])));
				capitalBean.setIfOtherLocation(Utility.checkNull(String.valueOf(viewObj[0][12])));
				capitalBean.setAttention(Utility.checkNull(String.valueOf(viewObj[0][13])));
				if (String.valueOf(viewObj[0][14]).equals(this.yStr)) {
					capitalBean.setQuotesAttached(this.yesStr);
					capitalBean.setQuotesAttachedRadioOptionValue(this.yesStr);
				} else {
					capitalBean.setQuotesAttached(this.noStr);
					capitalBean.setQuotesAttachedRadioOptionValue(this.noStr);
				}

				capitalBean.setQuotesReason(Utility.checkNull(String.valueOf(viewObj[0][15])));
				capitalBean.setUploadFileNameFlag(true);
				capitalBean.setUploadFileName(Utility.checkNull(String.valueOf(viewObj[0][16])));
				capitalBean.setComments(Utility.checkNull(String.valueOf(viewObj[0][17])));
				capitalBean.setCostSubTotal(Utility.checkNull(String.valueOf(viewObj[0][18])));
				capitalBean.setInvoicePoNumber(Utility.checkNull(String.valueOf(viewObj[0][19])));
				capitalBean.setAssetSubTotal(Utility.checkNull(String.valueOf(viewObj[0][20])));
				capitalBean.setCostInstallation(Utility.checkNull(String.valueOf(viewObj[0][21])));
				capitalBean.setInvoiceVendorID(Utility.checkNull(String.valueOf(viewObj[0][22])));
				capitalBean.setActualInstallation(Utility.checkNull(String.valueOf(viewObj[0][23])));
				capitalBean.setCostMaterial(Utility.checkNull(String.valueOf(viewObj[0][24])));
				capitalBean.setInvoiceTotal(Utility.checkNull(String.valueOf(viewObj[0][25])));
				capitalBean.setActualMaterial(Utility.checkNull(String.valueOf(viewObj[0][26])));
				capitalBean.setCostFreight(Utility.checkNull(String.valueOf(viewObj[0][27])));
				capitalBean.setActualFreight(Utility.checkNull(String.valueOf(viewObj[0][28])));
				capitalBean.setCostTax(Utility.checkNull(String.valueOf(viewObj[0][29])));
				capitalBean.setDateTagged(Utility.checkNull(String.valueOf(viewObj[0][30])));
				capitalBean.setActualTax(Utility.checkNull(String.valueOf(viewObj[0][31])));
				capitalBean.setCostTotal(Utility.checkNull(String.valueOf(viewObj[0][32])));
				capitalBean.setActualTotal(Utility.checkNull(String.valueOf(viewObj[0][33])));
				capitalBean.setShipToCompany(Utility.checkNull(String.valueOf(viewObj[0][34])));
				capitalBean.setCity(Utility.checkNull(String.valueOf(viewObj[0][35])));
				capitalBean.setState(Utility.checkNull(String.valueOf(viewObj[0][36])));
				capitalBean.setStreetAddress(Utility.checkNull(String.valueOf(viewObj[0][37])));
				capitalBean.setZipCode(Utility.checkNull(String.valueOf(viewObj[0][38])));
				capitalBean.setTelePhoneNumber(Utility.checkNull(String.valueOf(viewObj[0][39])));
				capitalBean.setStatus(Utility.checkNull(String.valueOf(viewObj[0][40])));

				if ("_B".equals(capitalBean.getStatus())) {
					this.getReportingManager(capitalBean);
				} else {
					capitalBean.setForwardedManagerID(Utility.checkNull(String.valueOf(viewObj[0][41])));
					capitalBean.setForwardedManagerToken(Utility.checkNull(String.valueOf(viewObj[0][42])));
					capitalBean.setForwardedManagerName(Utility.checkNull(String.valueOf(viewObj[0][43])));
				}

				// capitalBean.setForwardedApproverType(Utility.checkNull(String.valueOf(viewObj[0][44])));
				capitalBean.setDepartmentNumber(Utility.checkNull(String.valueOf(viewObj[0][45])));
				capitalBean.setInvoiceVendorNumber(Utility.checkNull(String.valueOf(viewObj[0][46])));
				capitalBean.setTrackingNumber(Utility.checkNull(String.valueOf(viewObj[0][47])));
				capitalBean.setLocationID(Utility.checkNull(String.valueOf(viewObj[0][48])));
				capitalBean.setPpoNumber(Utility.checkNull(String.valueOf(viewObj[0][49])));
				capitalBean.setPpoAttachement(Utility.checkNull(String.valueOf(viewObj[0][50])));

				// Sun-total of Cost-Total
				capitalBean.setSubTotalTotalCost(Utility.checkNull(String.valueOf(viewObj[0][18])));
			}

			// Detail-Table entry Begins
			final String deatilQuery = "SELECT EXP_QUANTITY, EXP_DESC, EXP_VENDOR_NAME, " + 
					" EXP_UNIT_PRICE, EXP_TOTAL_COST, EXP_TAG_NO, EXP_SERIAL_NO, " + 
					" EXP_CEAR_PRICE FROM HRMS_D1_CAPITALEXP_DESC " + 
					" WHERE  EXP_CODE = " + capitalHiddenID + 
					" ORDER BY EXP_ITEM_NO";
			final Object[][] detailObj = this.getSqlModel().getSingleResult(deatilQuery);
			if (detailObj != null && detailObj.length > 0) {
				/*
				 * for (int i = 0; i < detailObj.length; i++) {
				 * System.out.println("String.valueOf(detailObj[i][0]) : "+i+" :
				 * "+String.valueOf(detailObj[i][0]));
				 * System.out.println("String.valueOf(detailObj[i][1]) : "+i+" :
				 * "+String.valueOf(detailObj[i][1]));
				 * System.out.println("String.valueOf(detailObj[i][2]) : "+i+" :
				 * "+String.valueOf(detailObj[i][2]));
				 * System.out.println("String.valueOf(detailObj[i][3]) : "+i+" :
				 * "+String.valueOf(detailObj[i][3]));
				 * System.out.println("String.valueOf(detailObj[i][4]) : "+i+" :
				 * "+String.valueOf(detailObj[i][4]));
				 * System.out.println("String.valueOf(detailObj[i][5]) : "+i+" :
				 * "+String.valueOf(detailObj[i][5]));
				 * System.out.println("String.valueOf(detailObj[i][6]) : "+i+" :
				 * "+String.valueOf(detailObj[i][6]));
				 * System.out.println("String.valueOf(detailObj[i][7]) : "+i+" :
				 * "+String.valueOf(detailObj[i][7]));
				 * System.out.println("String.valueOf(detailObj[i][8]) : "+i+" :
				 * "+String.valueOf(detailObj[i][8]));
				 * 
				 * 
				 * request.setAttribute("quantity"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][0])));
				 * request.setAttribute("description"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][1])));
				 * request.setAttribute("vendorID"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][2])));
				 * request.setAttribute("vendorName"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][3])));
				 * request.setAttribute("unitPrice"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][4])));
				 * request.setAttribute("totalCost"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][5])));
				 * 
				 * request.setAttribute("tagNumber"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][6])));
				 * request.setAttribute("serialNumber"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][7])));
				 * request.setAttribute("cearPrice"+i,
				 * Utility.checkNull(String.valueOf(detailObj[i][8]))); }
				 */

				try {
					double unitPriceSubTotal = 0.0;
					unitPriceSubTotal = unitPriceSubTotal + 
						(Double.parseDouble(this.checkDouble(String.valueOf(detailObj[0][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[1][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[2][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[3][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[4][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[5][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[6][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[7][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[8][3]))) + 
							Double.parseDouble(this.checkDouble(String.valueOf(detailObj[9][3]))));

					// System.out.println("Unit Price Sub-Total :
					// "+Double.valueOf(unitPriceSubTotal) );
					capitalBean.setSubTotalUnitPrice(Double.valueOf(unitPriceSubTotal));
				} catch (final NumberFormatException nmf) {
					this.logger.error("Exception Unit Price Sub-Total : " + nmf);
				}

				try {
					double cEARSubTotal = 0.0;
					cEARSubTotal = cEARSubTotal +
							(Double.parseDouble(this.checkDouble(String.valueOf(detailObj[0][7]))) +  
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[1][7]))) + 
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[2][7]))) + 
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[3][7]))) + 
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[4][7]))) + 
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[5][7]))) + 
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[6][7]))) + 
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[7][7]))) + 
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[8][7]))) + 
									Double.parseDouble(this.checkDouble(String.valueOf(detailObj[9][7]))));

					// System.out.println("CEAR Sub-Total :
					// "+Double.valueOf(cEARSubTotal) );
					capitalBean.setSubTotalCEAR(Double.valueOf(cEARSubTotal));
				} catch (final NumberFormatException e) {
					this.logger.error("Exception CEAR Sub-Total : " + e);
				}

				capitalBean.setQuantity1(Utility.checkNull(String.valueOf(detailObj[0][0])));
				capitalBean.setDescription1(Utility.checkNull(String.valueOf(detailObj[0][1])));
				capitalBean.setVendorName1(Utility.checkNull(String.valueOf(detailObj[0][2])));
				capitalBean.setUnitPrice1(Utility.checkNull(String.valueOf(detailObj[0][3])));
				capitalBean.setTotalCost1(Utility.checkNull(String.valueOf(detailObj[0][4])));
				capitalBean.setTagNumber1(Utility.checkNull(String.valueOf(detailObj[0][5])));
				capitalBean.setSerialNumber1(Utility.checkNull(String.valueOf(detailObj[0][6])));
				capitalBean.setCearPrice1(Utility.checkNull(String.valueOf(detailObj[0][7])));

				capitalBean.setQuantity2(Utility.checkNull(String.valueOf(detailObj[1][0])));
				capitalBean.setDescription2(Utility.checkNull(String.valueOf(detailObj[1][1])));
				capitalBean.setVendorName2(Utility.checkNull(String.valueOf(detailObj[1][2])));
				capitalBean.setUnitPrice2(Utility.checkNull(String.valueOf(detailObj[1][3])));
				capitalBean.setTotalCost2(Utility.checkNull(String.valueOf(detailObj[1][4])));
				capitalBean.setTagNumber2(Utility.checkNull(String.valueOf(detailObj[1][5])));
				capitalBean.setSerialNumber2(Utility.checkNull(String.valueOf(detailObj[1][6])));
				capitalBean.setCearPrice2(Utility.checkNull(String.valueOf(detailObj[1][7])));

				capitalBean.setQuantity3(Utility.checkNull(String.valueOf(detailObj[2][0])));
				capitalBean.setDescription3(Utility.checkNull(String.valueOf(detailObj[2][1])));
				capitalBean.setVendorName3(Utility.checkNull(String.valueOf(detailObj[2][2])));
				capitalBean.setUnitPrice3(Utility.checkNull(String.valueOf(detailObj[2][3])));
				capitalBean.setTotalCost3(Utility.checkNull(String.valueOf(detailObj[2][4])));
				capitalBean.setTagNumber3(Utility.checkNull(String.valueOf(detailObj[2][5])));
				capitalBean.setSerialNumber3(Utility.checkNull(String.valueOf(detailObj[2][6])));
				capitalBean.setCearPrice3(Utility.checkNull(String.valueOf(detailObj[2][7])));

				capitalBean.setQuantity4(Utility.checkNull(String.valueOf(detailObj[3][0])));
				capitalBean.setDescription4(Utility.checkNull(String.valueOf(detailObj[3][1])));
				capitalBean.setVendorName4(Utility.checkNull(String.valueOf(detailObj[3][2])));
				capitalBean.setUnitPrice4(Utility.checkNull(String.valueOf(detailObj[3][3])));
				capitalBean.setTotalCost4(Utility.checkNull(String.valueOf(detailObj[3][4])));
				capitalBean.setTagNumber4(Utility.checkNull(String.valueOf(detailObj[3][5])));
				capitalBean.setSerialNumber4(Utility.checkNull(String.valueOf(detailObj[3][6])));
				capitalBean.setCearPrice4(Utility.checkNull(String.valueOf(detailObj[3][7])));

				capitalBean.setQuantity5(Utility.checkNull(String.valueOf(detailObj[4][0])));
				capitalBean.setDescription5(Utility.checkNull(String.valueOf(detailObj[4][1])));
				capitalBean.setVendorName5(Utility.checkNull(String.valueOf(detailObj[4][2])));
				capitalBean.setUnitPrice5(Utility.checkNull(String.valueOf(detailObj[4][3])));
				capitalBean.setTotalCost5(Utility.checkNull(String.valueOf(detailObj[4][4])));
				capitalBean.setTagNumber5(Utility.checkNull(String.valueOf(detailObj[4][5])));
				capitalBean.setSerialNumber5(Utility.checkNull(String.valueOf(detailObj[4][6])));
				capitalBean.setCearPrice5(Utility.checkNull(String.valueOf(detailObj[4][7])));

				capitalBean.setQuantity6(Utility.checkNull(String.valueOf(detailObj[5][0])));
				capitalBean.setDescription6(Utility.checkNull(String.valueOf(detailObj[5][1])));
				capitalBean.setVendorName6(Utility.checkNull(String.valueOf(detailObj[5][2])));
				capitalBean.setUnitPrice6(Utility.checkNull(String.valueOf(detailObj[5][3])));
				capitalBean.setTotalCost6(Utility.checkNull(String.valueOf(detailObj[5][4])));
				capitalBean.setTagNumber6(Utility.checkNull(String.valueOf(detailObj[5][5])));
				capitalBean.setSerialNumber6(Utility.checkNull(String.valueOf(detailObj[5][6])));
				capitalBean.setCearPrice6(Utility.checkNull(String.valueOf(detailObj[5][7])));

				capitalBean.setQuantity7(Utility.checkNull(String.valueOf(detailObj[6][0])));
				capitalBean.setDescription7(Utility.checkNull(String.valueOf(detailObj[6][1])));
				capitalBean.setVendorName7(Utility.checkNull(String.valueOf(detailObj[6][2])));
				capitalBean.setUnitPrice7(Utility.checkNull(String.valueOf(detailObj[6][3])));
				capitalBean.setTotalCost7(Utility.checkNull(String.valueOf(detailObj[6][4])));
				capitalBean.setTagNumber7(Utility.checkNull(String.valueOf(detailObj[6][5])));
				capitalBean.setSerialNumber7(Utility.checkNull(String.valueOf(detailObj[6][6])));
				capitalBean.setCearPrice7(Utility.checkNull(String.valueOf(detailObj[6][7])));

				capitalBean.setQuantity8(Utility.checkNull(String.valueOf(detailObj[7][0])));
				capitalBean.setDescription8(Utility.checkNull(String.valueOf(detailObj[7][1])));
				capitalBean.setVendorName8(Utility.checkNull(String.valueOf(detailObj[7][2])));
				capitalBean.setUnitPrice8(Utility.checkNull(String.valueOf(detailObj[7][3])));
				capitalBean.setTotalCost8(Utility.checkNull(String.valueOf(detailObj[7][4])));
				capitalBean.setTagNumber8(Utility.checkNull(String.valueOf(detailObj[7][5])));
				capitalBean.setSerialNumber8(Utility.checkNull(String.valueOf(detailObj[7][6])));
				capitalBean.setCearPrice8(Utility.checkNull(String.valueOf(detailObj[7][7])));

				capitalBean.setQuantity9(Utility.checkNull(String.valueOf(detailObj[8][0])));
				capitalBean.setDescription9(Utility.checkNull(String.valueOf(detailObj[8][1])));
				capitalBean.setVendorName9(Utility.checkNull(String.valueOf(detailObj[8][2])));
				capitalBean.setUnitPrice9(Utility.checkNull(String.valueOf(detailObj[8][3])));
				capitalBean.setTotalCost9(Utility.checkNull(String.valueOf(detailObj[8][4])));
				capitalBean.setTagNumber9(Utility.checkNull(String.valueOf(detailObj[8][5])));
				capitalBean.setSerialNumber9(Utility.checkNull(String.valueOf(detailObj[8][6])));
				capitalBean.setCearPrice9(Utility.checkNull(String.valueOf(detailObj[8][7])));

				capitalBean.setQuantity10(Utility.checkNull(String.valueOf(detailObj[9][0])));
				capitalBean.setDescription10(Utility.checkNull(String.valueOf(detailObj[9][1])));
				capitalBean.setVendorName10(Utility.checkNull(String.valueOf(detailObj[9][2])));
				capitalBean.setUnitPrice10(Utility.checkNull(String.valueOf(detailObj[9][3])));
				capitalBean.setTotalCost10(Utility.checkNull(String.valueOf(detailObj[9][4])));
				capitalBean.setTagNumber10(Utility.checkNull(String.valueOf(detailObj[9][5])));
				capitalBean.setSerialNumber10(Utility.checkNull(String.valueOf(detailObj[9][6])));
				capitalBean.setCearPrice10(Utility.checkNull(String.valueOf(detailObj[9][7])));
			}
			//Detail-Table entry Begins	

		} catch (final Exception e) {
			e.printStackTrace();
		}
	} // End of viewApplication Function.
	
	
	/**Used to get reporting manager inormation.
	 * @param capitalBean : CapitalExpenditure
	 */
	public void getReportingManager(final CapitalExpenditure capitalBean) {
		try {
			final Object[][] queryObj = this.getSqlModel().getSingleResult("SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC " + 
								 " WHERE EMP_ID=(SELECT EMP_REPORTING_OFFICER FROM HRMS_EMP_OFFC WHERE EMP_ID = " + capitalBean.getUserEmpId() + ")");
			if (queryObj != null && queryObj.length > 0) {
				capitalBean.setForwardedManagerID(Utility.checkNull(String.valueOf(queryObj[0][0])));
				capitalBean.setForwardedManagerToken(Utility.checkNull(String.valueOf(queryObj[0][1])));
				capitalBean.setForwardedManagerName(Utility.checkNull(String.valueOf(queryObj[0][2])));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method : getApproverCommentList.
	 * Purpose : USed to display approver comments.
	 * @param capitalBean : Instance of CapitalExpenditure
	 * @param applicationID : applicationID
	 */
	public void getApproverCommentList(final CapitalExpenditure capitalBean, final String applicationID) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, EXP_COMMENTS, " + 
								 		  " TO_CHAR(EXP_APPR_DATE, 'DD-MM-YYYY') AS REQ_APPROVED_DATE, " + 
								 		  " DECODE(EXP_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'X', 'Cancellation Rejected', 'S','Authorized Sign Off') AS STATUS " + 
								 		  " FROM HRMS_D1_CAPITALEXP_PATH "  + 
								 		  " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CAPITALEXP_PATH.EXP_APPROVER_CODE) " + 
								 		  " WHERE EXP_CODE = " + applicationID + " ORDER BY EXP_APPR_DATE DESC";
		
		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<CapitalExpenditure> approverList = new ArrayList<CapitalExpenditure>();
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			capitalBean.setApproverCommentsFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final CapitalExpenditure innerBean = new CapitalExpenditure();
				innerBean.setApprName(Utility.checkNull(String.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(Utility.checkNull(String.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(Utility.checkNull(String.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(Utility.checkNull(String.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			capitalBean.setApproverCommentList(approverList);
		}
	}
	
	
	/**Used to send application for approval. First save all the data then sned email notification.
	 * @param capitalBean : instance of CapitalExpenditure
	 * @param request : instance of CapitalExpenditure
	 * @return boolean
	 */
	public boolean sendForApprovalFunction(final CapitalExpenditure capitalBean, final HttpServletRequest request) {
		boolean result = false;
		if ("".equals(capitalBean.getCapitalExpID())) {
			result = this.draftFunction(capitalBean, request);
		} else {
			result = this.updateRecords(capitalBean, request);
		}
		return result;
	}

	
	/**Used to delete application.
	 * @param capitalBean : instance of CapitalExpenditure
	 * @return boolean
	 */
	public boolean deleteRecord(final CapitalExpenditure capitalBean) {
		boolean delDetailsRecods = false;
		boolean delHeaders = false;
		boolean finalResult = false;
		try {
			delDetailsRecods = this.getSqlModel().singleExecute("DELETE FROM HRMS_D1_CAPITALEXP_DESC WHERE EXP_CODE = " + capitalBean.getCapitalExpID());
			if (delDetailsRecods) {
				delHeaders = this.getSqlModel().singleExecute("DELETE FROM HRMS_D1_CAPITALEXP WHERE EXP_CODE = " + capitalBean.getCapitalExpID());
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		if (delDetailsRecods & delHeaders) {
			finalResult =  true;
		}
		return finalResult;
	}
	
	/**Used to return required query.
	 * @param status : Application status
	 * @param userId : User employee id
	 * @return String
	 */
	public String returnRequiredQuery(final String status, final String userId) {
		final String queryToReturn = "SELECT HRMS_D1_CAPITALEXP.EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(HRMS_D1_CAPITALEXP.EXP_APPLICATION_DATE,'DD-MM-YYYY'), " +  
									" HRMS_D1_CAPITALEXP.EXP_CODE, HRMS_D1_CAPITALEXP.EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP " +
									" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID)  " +
									" WHERE HRMS_D1_CAPITALEXP.EXP_APPLICATION_STATUS IN (" + status + ") AND EXP_EMP_ID = " + userId + 
									" ORDER BY HRMS_D1_CAPITALEXP.EXP_CODE DESC";
		return queryToReturn;
	}
	
}
