package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.CreditCheckRequestApprover;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
/**
 * @author aa1380 : Manish Sakpal.
 */
public class CreditCheckRequestApproverModel extends ModelBase {
	/** zeroStr. * */
	private final String zeroStr = "0"; 
	/** twentyStr. * */
	private final String twentyStr = "20";
	/** oneStr. * */
	private final String oneStr = "1"; 
	
	/** moStr. * */
	private final String moStr = "mo"; 
	/** anStr. * */
	private final String anStr = "an"; 
	/** tiStr. * */
	private final String tiStr = "ti"; 
	/** yesStr. * */
	private final String yesStr = "ys"; 
	/** noStr. * */
	private final String noStr = "no"; 
	/** aStr. * */
	private final String aStr = "A"; 
	/** tStr. * */
	private final String tStr = "T"; 
	/** mStr. * */
	private final String mStr = "M"; 
	/** nStr. * */
	private final String nStr = "N"; 
	/** yStr. * */
	private final String yStr = "Y"; 
	
	/**Method : isFinanceApprover.
	 * Purpose : Used to check whether current login user belongs to finance department or not
	 * @param userId : userId
	 * @return boolean
	 */
	public boolean isFinanceApprover(final String userId) {
		final Object[][] isFinanceApproverObj = this.getSqlModel().getSingleResult(" SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'F' AND APP_SETTINGS_EMP_ID = " + userId);
		if (isFinanceApproverObj != null && isFinanceApproverObj.length > 0) {
			return true;
		}
		return false;
	}
	
	/**Method : getPendingList.
	 * Purpose : Used to get pending application list.
	 * @param creditApproverBean : Instance of CreditCheckRequestApprover
	 * @param request : Instance of HttpServletRequest
	 */
	public void getpendingList(final CreditCheckRequestApprover creditApproverBean, final HttpServletRequest request) {
		try {
			final List<CreditCheckRequestApprover> pendingDataList = new ArrayList<CreditCheckRequestApprover>();
			// Pending application Begins
			final Object[][] pendingListData = this.getSqlModel().getSingleResult(this.returnQuery("'P'"));
			final String[] pageIndex = Utility.doPaging(creditApproverBean.getMyPage(), pendingListData.length, 20);
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
				creditApproverBean.setMyPage(this.oneStr);
			}
			
			if (pendingListData != null && pendingListData.length > 0) {
				creditApproverBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CreditCheckRequestApprover beanItt = new CreditCheckRequestApprover();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(pendingListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(pendingListData[i][4])));
					pendingDataList.add(beanItt);
				}
				creditApproverBean.setPendingIteratorList(pendingDataList);
			}
			// Pending  application Ends
			
			// pending cancellation application Begins
			final List<CreditCheckRequestApprover> pendingCancellationDataList = new ArrayList<CreditCheckRequestApprover>();
			final String pendingCancellationQuery = this.returnQuery("'C'"); 
			final Object[][] pendingCancellationListData = this.getSqlModel().getSingleResult(pendingCancellationQuery);
			final String[] pagependingCancellationIndex = Utility.doPaging(creditApproverBean.getMyPagePendingCancel(),
					pendingCancellationListData.length, 20);
			if (pagependingCancellationIndex == null) {
				pagependingCancellationIndex[0] = this.zeroStr;
				pagependingCancellationIndex[1] = this.twentyStr;
				pagependingCancellationIndex[2] = this.oneStr;
				pagependingCancellationIndex[3] = this.oneStr;
				pagependingCancellationIndex[4] = "";
			}
			
			request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String.valueOf(pagependingCancellationIndex[2])));
			request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String.valueOf(pagependingCancellationIndex[3])));
			if (pagependingCancellationIndex[4].equals(this.oneStr)) {
				creditApproverBean.setMyPagePendingCancel(this.oneStr);
			}
				
			if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
				creditApproverBean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer.parseInt(pagependingCancellationIndex[1]); i++) {
					final CreditCheckRequestApprover beanItt = new CreditCheckRequestApprover();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(pendingCancellationListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(pendingCancellationListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(pendingCancellationListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(pendingCancellationListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(pendingCancellationListData[i][4])));
					pendingCancellationDataList.add(beanItt);
				}
				creditApproverBean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// pending cancellation application Ends
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method : getApprovedList.
	 * Purpose : Used to get approved application list.
	 * @param creditApproverBean : Instance of CreditCheckRequest
	 * @param request : Instance of HttpServletRequest
	 */
	public void getApprovedList(final CreditCheckRequestApprover creditApproverBean,
			final HttpServletRequest request) {
		try {
			final List<CreditCheckRequestApprover> approvedList = new ArrayList<CreditCheckRequestApprover>();
			// Approved application Begins
			final String selQuery =  "SELECT CREDIT_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, CREDIT_COMPANY, CREDIT_ID, CREDIT_STATUS " +
									"  FROM HRMS_D1_CREDIT_CHECK_REQ " +
									"  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_REQUESTING_ID) " + 
									"  WHERE CREDIT_STATUS = 'A' ORDER BY CREDIT_ID DESC";  
				
			final Object[][] approvedListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndexApproved = Utility.doPaging(creditApproverBean.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = this.zeroStr;
				pageIndexApproved[1] = this.twentyStr;
				pageIndexApproved[2] = this.oneStr;
				pageIndexApproved[3] = this.oneStr;
				pageIndexApproved[4] = "";
			}
			
			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals(this.oneStr)){
				creditApproverBean.setMyPageApproved(this.oneStr);
			}
			
			if (approvedListData != null && approvedListData.length > 0) {
				creditApproverBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final CreditCheckRequestApprover beanItt = new CreditCheckRequestApprover();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				creditApproverBean.setApproveredIteratorList(approvedList);
			}
			// Approved application Ends
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Method : getRejectedList.
	 * Purpose : Used to get approved application list.
	 * @param creditApproverBean : Instance of CreditCheckRequest
	 * @param request : Instance of HttpServletRequest
	 */
	public void getRejectedList(final CreditCheckRequestApprover creditApproverBean,
			final HttpServletRequest request) {
		final List<CreditCheckRequestApprover> rejectedList = new ArrayList<CreditCheckRequestApprover>();
		// Rejected application Begins
		final String selQuery =	"SELECT CREDIT_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME,  " + 
							" CREDIT_COMPANY, CREDIT_ID, CREDIT_STATUS  "  + 
							" FROM HRMS_D1_CREDIT_CHECK_REQ  " + 
							" INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_REQUESTING_ID) " +  
							" WHERE CREDIT_STATUS = 'R' ORDER BY CREDIT_ID DESC"; 

		final Object[][] rejectedListData = this.getSqlModel().getSingleResult(selQuery);
		final String[] pageIndexRejected = Utility.doPaging(creditApproverBean.getMyPageRejected(), rejectedListData.length, 20);
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
			creditApproverBean.setMyPageRejected(this.oneStr);
		}
		
		if (rejectedListData != null && rejectedListData.length > 0) {
			creditApproverBean.setRejectedListLength(true);
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++)  {
				final CreditCheckRequestApprover beanItt = new CreditCheckRequestApprover();
				beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(rejectedListData[i][0])));
				beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(rejectedListData[i][1])));
				beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(rejectedListData[i][2])));
				beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			creditApproverBean.setRejectedIteratorList(rejectedList);
		}
		// Rejected application Ends
	}

	
	/**Method : updateStatus.
	 * Purpose : USed to update application status
	 * @param creditApproverBean : Instance of CreditCheckRequestApprover
	 * @param status : status
	 * @param applicationId : application Id
	 * @return boolean
	 */
	public boolean updateStatus(final CreditCheckRequestApprover creditApproverBean, final String status, final String applicationId) {
		final String approverComments = creditApproverBean.getApproverComments();
		final String approverID = creditApproverBean.getUserEmpId();		
		boolean result = false;
		try {
			result =  this.getSqlModel().singleExecute("UPDATE HRMS_D1_CREDIT_CHECK_REQ  SET CREDIT_STATUS = '" + status + "'" + 
					" WHERE CREDIT_ID = " + applicationId);
			this.insertApproverComments(applicationId, approverID, approverComments,	status);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**Method : insertApproverComments.
	 * Purpose : insert Approver Comment into HRMS_D1_CREDIT_DATA_PATH.
	 * @param applicationId : application Id
	 * @param approverID : approver ID
	 * @param approverComments : approver Comments
	 * @param statusToUpdate : status To Update
	 */
	private void insertApproverComments(final String applicationId, final String approverID, final String approverComments, final String statusToUpdate) {
		try {  
			final String insertSql = " INSERT INTO HRMS_D1_CREDIT_DATA_PATH (CREDIT_PATH_ID, CREDIT_APPLICATION_ID, CREDIT_APPROVER, CREDIT_COMMENTS, CREDIT_STATUS) " + 
					" VALUES ((SELECT NVL(MAX(CREDIT_PATH_ID), 0) + 1 FROM HRMS_D1_CREDIT_DATA_PATH), ?, ?, ?, ?) ";
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
	
	/**Method : getApproverCommentList.
	 * Purpose : Used to get approver comments.
	 * @param bean : Instance of CreditCheckRequest
	 * @param applicationID : Application ID
	 */
	public void getApproverCommentList(final CreditCheckRequestApprover bean, final String applicationID) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CREDIT_COMMENTS, " + 
			" TO_CHAR(CREDIT_APPROVED_DATE, 'DD-MM-YYYY') AS APPROVED_DATE, " + 
			" DECODE(CREDIT_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS " + 
			" FROM HRMS_D1_CREDIT_DATA_PATH "  + 
			" INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CREDIT_DATA_PATH.CREDIT_APPROVER) " + 
			" WHERE CREDIT_APPLICATION_ID = " + applicationID + " ORDER BY CREDIT_APPLICATION_ID DESC";
		
		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<CreditCheckRequestApprover> approverList = new ArrayList<CreditCheckRequestApprover>(apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final CreditCheckRequestApprover innerBean = new CreditCheckRequestApprover();
				innerBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
				innerBean.setApprComments(String.valueOf(apprCommentListObj[i][1]));
				innerBean.setApprDate(String.valueOf(apprCommentListObj[i][2]));
				innerBean.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}

	/**Method : viewApplicationFunction.
	 * Purpose : Used to view application details
	 * @param creditApproverBean : Instance of CreditCheckRequestApprover
	 * @param applicationID : Application ID
	 * @param typeOfList : Type Of List
	 */
	public void viewApplicationFunction(final CreditCheckRequestApprover creditApproverBean,
			final String applicationID, final String typeOfList) {
		try {
			final String viewQuery = "SELECT CREDIT_ID, CREDIT_REQUESTING_ID, OFFC1.EMP_TOKEN, OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME, " + 
								" CREDIT_AUTHORIZING_ID, OFFC2.EMP_TOKEN, OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME, " + 
								" CREDIT_COMPANY, CREDIT_STREET_ADDRESS, CREDIT_CITY, CREDIT_STATE, CREDIT_ZIPCODE, CREDIT_PHONE, " + 
								" CREDIT_AMOUNT, CREDIT_IS_AMOUNT_MONTHLY, CREDIT_IS_EXISTING_CUST, CREDIT_CUSTOMER_NAME, " + 
								" CREDIT_TYPE_OF_EQUIPMENT, CREDIT_COMMENTS, CREDIT_NUMBER_OF_SITES, CREDIT_STATUS, " + 
								" CREDIT_TRACKING_NUMBER, CREDIT_INITIATOR, OFFC3.EMP_TOKEN||'-'||OFFC3.EMP_FNAME||' '||OFFC3.EMP_LNAME , " + 
								" TO_CHAR(CREDIT_INITIATOR_DATE,'DD-MM-YYYY  HH24:MI') " + 
								" FROM HRMS_D1_CREDIT_CHECK_REQ " + 
								" INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_REQUESTING_ID) " + 
								" INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_AUTHORIZING_ID) " + 
								" INNER JOIN HRMS_EMP_OFFC OFFC3 ON (OFFC3.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_INITIATOR) " + 
								" WHERE  CREDIT_ID = " + applicationID;
			final Object[][] viewObj = this.getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				creditApproverBean.setCreditID(Utility.checkNull(String.valueOf(viewObj[0][0])));
				creditApproverBean.setCreditRequestingPersonID(Utility.checkNull(String.valueOf(viewObj[0][1])));
				creditApproverBean.setCreditRequestingPersonToken(Utility.checkNull(String.valueOf(viewObj[0][2])));
				creditApproverBean.setCreditRequestingPersonName(Utility.checkNull(String.valueOf(viewObj[0][3])));
				creditApproverBean.setCreditAuthorizingPersonID(Utility.checkNull(String.valueOf(viewObj[0][4])));
				creditApproverBean.setCreditAuthorizingPersonToken(Utility.checkNull(String.valueOf(viewObj[0][5])));
				creditApproverBean.setCreditAuthorizingPersonName(Utility.checkNull(String.valueOf(viewObj[0][6])));
				creditApproverBean.setCompanyName(Utility.checkNull(String.valueOf(viewObj[0][7])));
				creditApproverBean.setStreetAddress(Utility.checkNull(String.valueOf(viewObj[0][8])));
				creditApproverBean.setCityName(Utility.checkNull(String.valueOf(viewObj[0][9])));
				creditApproverBean.setState(Utility.checkNull(String.valueOf(viewObj[0][10])));
				creditApproverBean.setZipCode(Utility.checkNull(String.valueOf(viewObj[0][11])));
				creditApproverBean.setPhoneNumber(Utility.checkNull(String.valueOf(viewObj[0][12])));
				creditApproverBean.setAmountOfCreditToBeAdvance(Utility.checkNull(String.valueOf(viewObj[0][13])));
				if (String.valueOf(viewObj[0][14]).equals(this.mStr)) {
					creditApproverBean.setIsMonthlyAnnually(this.moStr);
					creditApproverBean.setIsMonthlyAnnuallyRadioOptionValue(this.moStr);
				} else if (String.valueOf(viewObj[0][14]).equals(this.aStr)) {
					creditApproverBean.setIsMonthlyAnnually(this.anStr);
					creditApproverBean.setIsMonthlyAnnuallyRadioOptionValue(this.anStr);
				} else if (String.valueOf(viewObj[0][14]).equals(this.tStr)) {
					creditApproverBean.setIsMonthlyAnnually(this.tiStr);
					creditApproverBean.setIsMonthlyAnnuallyRadioOptionValue(this.tiStr);
				} else {
					creditApproverBean.setIsMonthlyAnnually("");
					creditApproverBean.setIsMonthlyAnnuallyRadioOptionValue("");
				}

				if (String.valueOf(viewObj[0][15]).equals(this.yStr)) {
					creditApproverBean.setIsExistingCustomer(this.yesStr);
					creditApproverBean.setIsExistingCustomerRadioOptionValue(this.yesStr);
				} else if (String.valueOf(viewObj[0][15]).equals(this.nStr)) {
					creditApproverBean.setIsExistingCustomer(this.noStr);
					creditApproverBean.setIsExistingCustomerRadioOptionValue(this.noStr);
				} else {
					creditApproverBean.setIsExistingCustomer("");
					creditApproverBean.setIsExistingCustomerRadioOptionValue("");
				}
				creditApproverBean.setCustomerName(Utility.checkNull(String.valueOf(viewObj[0][16])));
				creditApproverBean.setTypeOfEquipment(Utility.checkNull(String.valueOf(viewObj[0][17])));
				creditApproverBean.setComments(Utility.checkNull(String.valueOf(viewObj[0][18])));
				creditApproverBean.setNumberOfSites(Utility.checkNull(String.valueOf(viewObj[0][19])));
				creditApproverBean.setStatus(Utility.checkNull(String.valueOf(viewObj[0][20])));
				creditApproverBean.setTrackingNumber(Utility.checkNull(String.valueOf(viewObj[0][21])));
				creditApproverBean.setCompletedByID(Utility.checkNull(String.valueOf(viewObj[0][22])));
				creditApproverBean.setCompletedBy(Utility.checkNull(String.valueOf(viewObj[0][23])));
				creditApproverBean.setCompletedDate(Utility.checkNull(String.valueOf(viewObj[0][24])));
				this.getApproverCommentList(creditApproverBean, applicationID);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method : returnQuery.
	 * Purpose : Used to return query with supplied status and used id
	 * @param status : Application status
	 * @return String
	 */
	public String returnQuery(final String status) {
		final String queryToReturn = "SELECT CREDIT_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, " + 
									 " CREDIT_COMPANY, CREDIT_ID, CREDIT_STATUS "  + 
									 " FROM HRMS_D1_CREDIT_CHECK_REQ " + 
									 " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_REQUESTING_ID) " +  
									 " WHERE CREDIT_STATUS IN(" + status + ") ORDER BY CREDIT_ID DESC"; 
		return queryToReturn;
	}
}
