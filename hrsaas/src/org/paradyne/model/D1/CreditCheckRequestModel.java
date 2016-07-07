package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.CreditCheckRequest;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;

/**
 * @author aa1380 : Manish Sakpal.
 * Created on 11th March 2011
 */
public class CreditCheckRequestModel extends ModelBase {
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
	
	/**Method : isCurrentUser.
	 * Purpose : Check whether current user is initiator or not.
	 * @param userId : applicant id.
	 * @return boolean
	 */
	public boolean isCurrentUser(final String userId) {
		final Object[][] currentUserObj = this.getSqlModel().getSingleResult(" SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID = " + userId);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}

	/**Method : getInformation.
	 * Purpose : Used to get application details
	 * @param creditBean : Instance of  CreditCheckRequest
	 * @param userId : Current login employee id
	 */
	public void getInformation(final CreditCheckRequest creditBean, final String userId) {
		try {
			final String currentUserQuery = "SELECT EMP_ID AS REQUESTER_ID, EMP_TOKEN AS REQUESTER_TOKEN, EMP_FNAME||' '||EMP_LNAME AS REQUESTER_NAME, " + 
					" EMP_ID AS COMPLETED_BY_ID, EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_LNAME  AS COMPLETED_BY_NAME, " + 
					" TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID = " + userId;
			final Object[][] currentQueryObj = this.getSqlModel().getSingleResult(currentUserQuery);
			if (currentQueryObj != null && currentQueryObj.length > 0) {
				creditBean.setCreditRequestingPersonID(Utility.checkNull(String.valueOf(currentQueryObj[0][0])));
				creditBean.setCreditRequestingPersonToken(Utility.checkNull(String.valueOf(currentQueryObj[0][1])));
				creditBean.setCreditRequestingPersonName(Utility.checkNull(String.valueOf(currentQueryObj[0][2])));
				creditBean.setCompletedByID(Utility.checkNull(String.valueOf(currentQueryObj[0][3])));
				creditBean.setCompletedBy(Utility.checkNull(String.valueOf(currentQueryObj[0][4])));
				creditBean.setCompletedDate(Utility.checkNull(String.valueOf(currentQueryObj[0][5])));
			}

			Object[][] empFlow = null;
			try {
				final ReportingModel reporting = new ReportingModel();
				reporting.initiate(context, session);
				empFlow = reporting.generateEmpFlow(creditBean.getCreditRequestingPersonID(), "D1", 1);
				reporting.terminate();
			} catch (final Exception e) {
				e.printStackTrace();
			}
			if (empFlow != null && empFlow.length > 0) {
				final Object[][] data = this.getSqlModel().getSingleResult(" SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME  FROM HRMS_EMP_OFFC " +
						   											 " WHERE EMP_ID=" + String.valueOf(empFlow[0][0]));
				if (data != null && data.length > 0) {
					creditBean.setCreditAuthorizingPersonID(Utility.checkNull(String.valueOf(data[0][0])));
					creditBean.setCreditAuthorizingPersonToken(Utility.checkNull(String.valueOf(data[0][1])));
					creditBean.setCreditAuthorizingPersonName(Utility.checkNull(String.valueOf(data[0][2])));
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Method : getPendingList.
	 * Purpose : Used to get drafted, application in process and sent-back application list.
	 * @param bean : Instance of CreditCheckRequest
	 * @param request : Instance of HttpServletRequest
	 * @param userId : Applicant Id
	 */
	public void getPendingList(final CreditCheckRequest bean,
			final HttpServletRequest request, final String userId) {
		try {
			// For drafted application Begins
			final List<CreditCheckRequest> draftvoucherList = new ArrayList<CreditCheckRequest>();
			final Object[][] draftListData = this.getSqlModel().getSingleResult(this.returnQuery("'D'", userId));
			final String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(), draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = this.zeroStr;
				pageIndexDrafted[1] = this.twentyStr;
				pageIndexDrafted[2] = this.oneStr;
				pageIndexDrafted[3] = this.oneStr;
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals(this.oneStr)) {
				bean.setMyPage(this.oneStr);
			}

			if (draftListData != null && draftListData.length > 0) {
				bean.setDraftVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					final CreditCheckRequest beanItt = new CreditCheckRequest();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(draftListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(draftListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(draftListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(draftListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(draftListData[i][4])));
					draftvoucherList.add(beanItt);
				}
				bean.setDraftVoucherIteratorList(draftvoucherList);
			}
			// For drafted application Ends

			// For in-Process application Begins
			final List<CreditCheckRequest> inProcessVoucherList = new ArrayList<CreditCheckRequest>();
			final Object[][] inProcessListData = this.getSqlModel().getSingleResult(this.returnQuery("'P'", userId));
			final String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(), inProcessListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexInProcess[0] = this.zeroStr;
				pageIndexInProcess[1] = this.twentyStr;
				pageIndexInProcess[2] = this.oneStr;
				pageIndexInProcess[3] = this.oneStr;
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals(this.oneStr)) {
				bean.setMyPageInProcess(this.oneStr);
			}

			if (inProcessListData != null && inProcessListData.length > 0) {
				bean.setInProcessVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					final CreditCheckRequest beanItt = new CreditCheckRequest();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(inProcessListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(inProcessListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(inProcessListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(inProcessListData[i][4])));
					inProcessVoucherList.add(beanItt);
				}
				bean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends

			// Sent-Back application Begins
			final List<CreditCheckRequest> sentBackVoucherList = new ArrayList<CreditCheckRequest>();
			final Object[][] sentBackListData = this.getSqlModel().getSingleResult(this.returnQuery("'B'", userId));
			final String[] pageIndexSentBack = Utility.doPaging(bean.getMyPageSentBack(), sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = this.zeroStr;
				pageIndexSentBack[1] = this.twentyStr;
				pageIndexSentBack[2] = this.oneStr;
				pageIndexSentBack[3] = this.oneStr;
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String.valueOf(pageIndexSentBack[3])));
			if (pageIndexSentBack[4].equals(this.oneStr)) {
				bean.setMyPageSentBack(this.oneStr);
			}

			if (sentBackListData != null && sentBackListData.length > 0) {
				bean.setSentBackVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer.parseInt(pageIndexSentBack[1]); i++) {
					final CreditCheckRequest beanItt = new CreditCheckRequest();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(sentBackListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(sentBackListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(sentBackListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(sentBackListData[i][4])));
					sentBackVoucherList.add(beanItt);
				}
				bean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Method : draftFunction.
	 * Purpose : Used to save application details.
	 * @param creditBean : Instance of CreditCheckRequest
	 * @return boolean
	 */
	public boolean draftFunction(final CreditCheckRequest creditBean) {
		boolean result = false;
		try {
			//Tracking Number Begins
			final String trackingQuery = "SELECT NVL(MAX(CREDIT_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'), NVL(MAX(CREDIT_ID),0)+1, TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_CREDIT_CHECK_REQ	";
			final Object[][] trackingObj = this.getSqlModel().getSingleResult(trackingQuery);
			if (trackingObj != null && trackingObj.length > 0) {
				try {
					final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					final String autoIncrementApplicationCode = model.generateApplicationCode(String.valueOf(trackingObj[0][1]), "D1-CCR", creditBean.getUserEmpId(), String.valueOf(trackingObj[0][2]));
					creditBean.setTrackingNumber(Utility.checkNull(autoIncrementApplicationCode));
					model.terminate();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
			//Tracking Number Ends
			final Object[][] addObj = new Object[1][19];
			addObj[0][0] = creditBean.getCreditRequestingPersonID();
			addObj[0][1] = creditBean.getCreditAuthorizingPersonID();
			addObj[0][2] = creditBean.getCompanyName();
			addObj[0][3] = creditBean.getStreetAddress();
			addObj[0][4] = creditBean.getCityName();
			addObj[0][5] = creditBean.getState();
			addObj[0][6] = creditBean.getZipCode();
			addObj[0][7] = creditBean.getPhoneNumber();
			addObj[0][8] = creditBean.getAmountOfCreditToBeAdvance();

			if (creditBean.getIsMonthlyAnnuallyRadioOptionValue().equals(this.moStr)) {
				addObj[0][9] = this.mStr;
			} else if (creditBean.getIsMonthlyAnnuallyRadioOptionValue().equals(this.anStr)) {
				addObj[0][9] = this.aStr;
			} else if (creditBean.getIsMonthlyAnnuallyRadioOptionValue().equals(this.tiStr)) {
				addObj[0][9] = this.tStr;
			} else {
				addObj[0][9] = "";
			}
			if (creditBean.getIsExistingCustomerRadioOptionValue().equals(this.yesStr)) {
				addObj[0][10] = this.yStr;
			} else if (creditBean.getIsExistingCustomerRadioOptionValue().equals(this.noStr)) {
				addObj[0][10] = this.nStr;
			} else {
				addObj[0][10] = "";
			}

			addObj[0][11] = creditBean.getCustomerName();
			addObj[0][12] = creditBean.getTypeOfEquipment();
			addObj[0][13] = creditBean.getComments();
			addObj[0][14] = creditBean.getNumberOfSites();
			addObj[0][15] = creditBean.getStatus();

			addObj[0][16] = creditBean.getTrackingNumber();
			addObj[0][17] = creditBean.getCompletedByID();
			addObj[0][18] = creditBean.getCompletedDate();

			final String insertQuery = "INSERT INTO HRMS_D1_CREDIT_CHECK_REQ (CREDIT_ID, CREDIT_REQUESTING_ID, CREDIT_AUTHORIZING_ID, " + 
					" CREDIT_COMPANY,  CREDIT_STREET_ADDRESS, CREDIT_CITY, CREDIT_STATE, CREDIT_ZIPCODE, CREDIT_PHONE, " + 
					" CREDIT_AMOUNT, CREDIT_IS_AMOUNT_MONTHLY, CREDIT_IS_EXISTING_CUST, " + 
					" CREDIT_CUSTOMER_NAME, CREDIT_TYPE_OF_EQUIPMENT, CREDIT_COMMENTS, CREDIT_NUMBER_OF_SITES, CREDIT_STATUS, " + 
					" CREDIT_TRACKING_NUMBER, CREDIT_INITIATOR, CREDIT_INITIATOR_DATE)" + 
					" VALUES((SELECT NVL(MAX(CREDIT_ID),0)+1 FROM HRMS_D1_CREDIT_CHECK_REQ), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'DD-MM-YYYY  HH24:MI')) ";
			result = this.getSqlModel().singleExecute(insertQuery, addObj);
			if (result) {
				final Object[][] data = this.getSqlModel().getSingleResult(" SELECT NVL(MAX(CREDIT_ID),0) FROM HRMS_D1_CREDIT_CHECK_REQ ");
				if (data != null && data.length > 0) {
					creditBean.setCreditID(String.valueOf(data[0][0]));
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**Method : updateRecords.
	 * Purpose : Used to update application details
	 * @param creditBean : Instance of CreditCheckRequest
	 * @return boolean
	 */
	public boolean updateRecords(final CreditCheckRequest creditBean) {
		boolean result = false;
		try {
			final Object[][] updateObj = new Object[1][20];
			updateObj[0][0] = creditBean.getCreditRequestingPersonID();
			updateObj[0][1] = creditBean.getCreditAuthorizingPersonID();
			updateObj[0][2] = creditBean.getCompanyName();
			updateObj[0][3] = creditBean.getStreetAddress();
			updateObj[0][4] = creditBean.getCityName();
			updateObj[0][5] = creditBean.getState();
			updateObj[0][6] = creditBean.getZipCode();
			updateObj[0][7] = creditBean.getPhoneNumber();
			updateObj[0][8] = creditBean.getAmountOfCreditToBeAdvance();

			if (creditBean.getIsMonthlyAnnuallyRadioOptionValue().equals(this.moStr)) {
				updateObj[0][9] = this.mStr;
			} else if (creditBean.getIsMonthlyAnnuallyRadioOptionValue().equals(this.anStr)) {
				updateObj[0][9] = this.aStr;
			} else if (creditBean.getIsMonthlyAnnuallyRadioOptionValue().equals(this.tiStr)) {
				updateObj[0][9] = this.tStr;
			} else {
				updateObj[0][9] = "";
			}

			if (creditBean.getIsExistingCustomerRadioOptionValue().equals(this.yesStr)) {
				updateObj[0][10] = this.yStr;
			} else if (creditBean.getIsExistingCustomerRadioOptionValue().equals(this.noStr)) {
				updateObj[0][10] = this.nStr;
			} else {
				updateObj[0][10] = "";
			}

			updateObj[0][11] = creditBean.getCustomerName();
			updateObj[0][12] = creditBean.getTypeOfEquipment();
			updateObj[0][13] = creditBean.getComments();
			updateObj[0][14] = creditBean.getNumberOfSites();
			updateObj[0][15] = creditBean.getStatus();

			updateObj[0][16] = creditBean.getTrackingNumber();
			updateObj[0][17] = creditBean.getCompletedByID();
			updateObj[0][18] = creditBean.getCompletedDate();

			updateObj[0][19] = creditBean.getCreditID();

			final String updateQuery = "UPDATE HRMS_D1_CREDIT_CHECK_REQ SET CREDIT_REQUESTING_ID=?, CREDIT_AUTHORIZING_ID=?, " + 
					" CREDIT_COMPANY=?, CREDIT_STREET_ADDRESS=?, CREDIT_CITY=?, CREDIT_STATE=?, CREDIT_ZIPCODE=?, CREDIT_PHONE=?, " + 
					" CREDIT_AMOUNT=?, CREDIT_IS_AMOUNT_MONTHLY=?, CREDIT_IS_EXISTING_CUST=?, " + 
					" CREDIT_CUSTOMER_NAME=?, CREDIT_TYPE_OF_EQUIPMENT=?, CREDIT_COMMENTS=?, CREDIT_NUMBER_OF_SITES=?, CREDIT_STATUS=?, " + 
					" CREDIT_TRACKING_NUMBER=?, CREDIT_INITIATOR=?, CREDIT_INITIATOR_DATE=TO_DATE(?,'DD-MM-YYYY  HH24:MI') WHERE CREDIT_ID=?";
			result = this.getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method : viewApplicationFunction.
	 * Purpose : Used to view application details
	 * @param creditBean : Instance of CreditCheckRequest
	 * @param creditHiddenID : Application ID
	 */
	public void viewApplicationFunction(final CreditCheckRequest creditBean, final String creditHiddenID) {
		try {
			final String viewQuery = "SELECT CREDIT_ID, CREDIT_REQUESTING_ID, OFFC1.EMP_TOKEN, OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME, " + 
					" CREDIT_AUTHORIZING_ID, OFFC2.EMP_TOKEN, OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME, " + 
					" CREDIT_COMPANY, CREDIT_STREET_ADDRESS, CREDIT_CITY, CREDIT_STATE, CREDIT_ZIPCODE, CREDIT_PHONE, " + 
					" CREDIT_AMOUNT, CREDIT_IS_AMOUNT_MONTHLY, CREDIT_IS_EXISTING_CUST, CREDIT_CUSTOMER_NAME, " + 
					" CREDIT_TYPE_OF_EQUIPMENT, CREDIT_COMMENTS, CREDIT_NUMBER_OF_SITES, CREDIT_STATUS, " + 
					" CREDIT_TRACKING_NUMBER, CREDIT_INITIATOR, OFFC3.EMP_TOKEN||'-'||OFFC3.EMP_FNAME||' '||OFFC3.EMP_LNAME , " + 
					" TO_CHAR(CREDIT_INITIATOR_DATE,'DD-MM-YYYY  HH24:MI') " + 
					" FROM  HRMS_D1_CREDIT_CHECK_REQ " + 
					" INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_REQUESTING_ID) " + 
					" INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_AUTHORIZING_ID) " + 
					//	+ " INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_CITY) "
					" INNER JOIN HRMS_EMP_OFFC OFFC3 ON (OFFC3.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_INITIATOR) " + 
					" WHERE  CREDIT_ID = " + creditHiddenID;
			final Object[][] viewObj = this.getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				creditBean.setCreditID(Utility.checkNull(String.valueOf(viewObj[0][0])));
				creditBean.setCreditRequestingPersonID(Utility.checkNull(String.valueOf(viewObj[0][1])));
				creditBean.setCreditRequestingPersonToken(Utility.checkNull(String.valueOf(viewObj[0][2])));
				creditBean.setCreditRequestingPersonName(Utility.checkNull(String.valueOf(viewObj[0][3])));
				creditBean.setCreditAuthorizingPersonID(Utility.checkNull(String.valueOf(viewObj[0][4])));
				creditBean.setCreditAuthorizingPersonToken(Utility.checkNull(String.valueOf(viewObj[0][5])));
				creditBean.setCreditAuthorizingPersonName(Utility.checkNull(String.valueOf(viewObj[0][6])));
				creditBean.setCompanyName(Utility.checkNull(String.valueOf(viewObj[0][7])));
				creditBean.setStreetAddress(Utility.checkNull(String.valueOf(viewObj[0][8])));
				creditBean.setCityName(Utility.checkNull(String.valueOf(viewObj[0][9])));
				creditBean.setState(Utility.checkNull(String.valueOf(viewObj[0][10])));
				creditBean.setZipCode(Utility.checkNull(String.valueOf(viewObj[0][11])));
				creditBean.setPhoneNumber(Utility.checkNull(String.valueOf(viewObj[0][12])));
				creditBean.setAmountOfCreditToBeAdvance(Utility.checkNull(String.valueOf(viewObj[0][13])));
				if (String.valueOf(viewObj[0][14]).equals(this.mStr)) {
					creditBean.setIsMonthlyAnnually(this.moStr);
					creditBean.setIsMonthlyAnnuallyRadioOptionValue(this.moStr);
				} else if (String.valueOf(viewObj[0][14]).equals(this.aStr)) {
					creditBean.setIsMonthlyAnnually(this.anStr);
					creditBean.setIsMonthlyAnnuallyRadioOptionValue(this.anStr);
				} else if (String.valueOf(viewObj[0][14]).equals(this.tStr)) {
					creditBean.setIsMonthlyAnnually(this.tiStr);
					creditBean.setIsMonthlyAnnuallyRadioOptionValue(this.tiStr);
				} else {
					creditBean.setIsMonthlyAnnually("");
					creditBean.setIsMonthlyAnnuallyRadioOptionValue("");
				}

				if (String.valueOf(viewObj[0][15]).equals(this.yStr)) {
					creditBean.setIsExistingCustomer(this.yesStr);
					creditBean.setIsExistingCustomerRadioOptionValue(this.yesStr);
				} else if (String.valueOf(viewObj[0][15]).equals(this.nStr)) {
					creditBean.setIsExistingCustomer(this.noStr);
					creditBean.setIsExistingCustomerRadioOptionValue(this.noStr);
				} else {
					creditBean.setIsExistingCustomer("");
					creditBean.setIsExistingCustomerRadioOptionValue("");
				}
				creditBean.setCustomerName(Utility.checkNull(String.valueOf(viewObj[0][16])));
				creditBean.setTypeOfEquipment(Utility.checkNull(String.valueOf(viewObj[0][17])));
				creditBean.setComments(Utility.checkNull(String.valueOf(viewObj[0][18])));
				creditBean.setNumberOfSites(Utility.checkNull(String.valueOf(viewObj[0][19])));
				creditBean.setStatus(Utility.checkNull(String.valueOf(viewObj[0][20])));

				creditBean.setTrackingNumber(Utility.checkNull(String.valueOf(viewObj[0][21])));
				creditBean.setCompletedByID(Utility.checkNull(String.valueOf(viewObj[0][22])));
				creditBean.setCompletedBy(Utility.checkNull(String.valueOf(viewObj[0][23])));
				creditBean.setCompletedDate(Utility.checkNull(String.valueOf(viewObj[0][24])));

				this.getApproverCommentList(creditBean, creditHiddenID);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Method : getApproverCommentList.
	 * Purpose : Used to get approver comments.
	 * @param bean : Instance of CreditCheckRequest
	 * @param applicationID : Application ID
	 */
	public void getApproverCommentList(final CreditCheckRequest bean, final String applicationID) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CREDIT_COMMENTS, " + 
				" TO_CHAR(CREDIT_APPROVED_DATE, 'DD-MM-YYYY') AS APPROVED_DATE, " + 
				" DECODE(CREDIT_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS " + 
				" FROM HRMS_D1_CREDIT_DATA_PATH " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CREDIT_DATA_PATH.CREDIT_APPROVER) " + 
				" WHERE CREDIT_APPLICATION_ID = " + applicationID +
				" ORDER BY CREDIT_APPLICATION_ID DESC";

		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<CreditCheckRequest> approverList = new ArrayList<CreditCheckRequest>();
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			bean.setApproverCommentsFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final CreditCheckRequest innerBean = new CreditCheckRequest();
				innerBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
				innerBean.setApprComments(String.valueOf(apprCommentListObj[i][1]));
				innerBean.setApprDate(String.valueOf(apprCommentListObj[i][2]));
				innerBean.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}


	/**Method : delRecord.
	 * Purpose : Used to delete saved application
	 * @param creditBean : Instance of CreditCheckRequest
	 * @return boolean
	 */
	public boolean delRecord(final CreditCheckRequest creditBean) {
		boolean result = false;
		try {
			result = this.getSqlModel().singleExecute("DELETE FROM HRMS_D1_CREDIT_CHECK_REQ WHERE CREDIT_ID = " + creditBean.getCreditID());
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method : sendForApprovalFunction.
	 * Purpose : Used to save application data as well as send application for approval
	* @param creditBean : Instance of CreditCheckRequest
	 * @return boolean
	 */
	public boolean sendForApprovalFunction(final CreditCheckRequest creditBean) {
		boolean result = false;
		if ("".equals(creditBean.getCreditID())) {
			result = this.draftFunction(creditBean);
		} else {
			result = this.updateRecords(creditBean);
			try {
				result = this.getSqlModel().singleExecute("UPDATE HRMS_D1_CREDIT_CHECK_REQ SET CREDIT_STATUS = 'P' WHERE CREDIT_ID = " + creditBean.getCreditID());
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**Method : getApprovedList.
	 * Purpose : Used to get approved application list.
	 * @param creditBean : Instance of CreditCheckRequest
	 * @param request : Instance of HttpServletRequest
	 * @param userId : Applicant Id
	 */
	public void getApprovedList(final CreditCheckRequest creditBean,
			final HttpServletRequest request, final String userId) {
		try {
			final List<CreditCheckRequest> approvedList = new ArrayList<CreditCheckRequest>();

			// Approved application Begins
			final Object[][] approvedListData = this.getSqlModel().getSingleResult(this.returnQuery("'A'", userId));

			final String[] pageIndexApproved = Utility.doPaging(creditBean.getMyPageApproved(), approvedListData.length, 20);
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
				creditBean.setMyPageApproved(this.oneStr);
			}

			if (approvedListData != null && approvedListData.length > 0) {
				creditBean.setApprovedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					final CreditCheckRequest beanItt = new CreditCheckRequest();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				creditBean.setApprovedVoucherIteratorList(approvedList);
			}
			// Approved application Ends

			// Approved cancellation application Begins
			final List<CreditCheckRequest> approvedCancellationList = new ArrayList<CreditCheckRequest>();
			final Object[][] approvedCancellationListData = this.getSqlModel().getSingleResult(this.returnQuery("'X'", userId));
			final String[] pageIndexApprovedCancel = Utility.doPaging(creditBean.getMyPageApprovedCancel(), approvedCancellationListData.length, 20);
			if (pageIndexApprovedCancel == null) {
				pageIndexApprovedCancel[0] = this.zeroStr;
				pageIndexApprovedCancel[1] = this.twentyStr;
				pageIndexApprovedCancel[2] = this.oneStr;
				pageIndexApprovedCancel[3] = this.oneStr;
				pageIndexApprovedCancel[4] = "";
			}

			request.setAttribute("totalApprovedCancellationPage", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[2])));
			request.setAttribute("approvedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexApprovedCancel[3])));
			if (pageIndexApprovedCancel[4].equals(this.oneStr)) {
				creditBean.setMyPageApprovedCancel(this.oneStr);
			}

			if (approvedCancellationListData != null && approvedCancellationListData.length > 0) {
				creditBean.setApprovedCancellationVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexApprovedCancel[0]); i < Integer.parseInt(pageIndexApprovedCancel[1]); i++) {
					final CreditCheckRequest beanItt = new CreditCheckRequest();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(approvedCancellationListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(approvedCancellationListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(approvedCancellationListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(approvedCancellationListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(approvedCancellationListData[i][4])));
					approvedCancellationList.add(beanItt);
				}
				creditBean.setApprovedCancellationVoucherIteratorList(approvedCancellationList);
			}
			// Approved cancellation application Ends

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Method : getCancelledList.
	 * Purpose : Used to get cancel application list.
	 * @param creditBean : Instance of CreditCheckRequest
	 * @param request : Instance of HttpServletRequest
	 * @param userId : Applicant Id
	 */
	public void getCancelledList(final CreditCheckRequest creditBean,
			final HttpServletRequest request, final String userId) {
		try {
			final List<CreditCheckRequest> cancelledList = new ArrayList<CreditCheckRequest>();
			// Cancelled application Begins
			final Object[][] cancelledListData = this.getSqlModel().getSingleResult(this.returnQuery("'C'", userId));
			final String[] pageIndexCancel = Utility.doPaging(creditBean.getMyPageCancel(), cancelledListData.length, 20);
			if (pageIndexCancel == null) {
				pageIndexCancel[0] = this.zeroStr;
				pageIndexCancel[1] = this.twentyStr;
				pageIndexCancel[2] = this.oneStr;
				pageIndexCancel[3] = this.oneStr;
				pageIndexCancel[4] = "";
			}

			request.setAttribute("totalCancelPage", Integer.parseInt(String.valueOf(pageIndexCancel[2])));
			request.setAttribute("cancelPageNo", Integer.parseInt(String.valueOf(pageIndexCancel[3])));
			if (pageIndexCancel[4].equals(this.oneStr)) {
				creditBean.setMyPageCancel(this.oneStr);
			}

			if (cancelledListData != null && cancelledListData.length > 0) {
				creditBean.setCancelledVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexCancel[0]); i < Integer.parseInt(pageIndexCancel[1]); i++) {
					final CreditCheckRequest beanItt = new CreditCheckRequest();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(cancelledListData[i][0])));
					beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(cancelledListData[i][1])));
					beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(cancelledListData[i][2])));
					beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(cancelledListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(cancelledListData[i][4])));
					cancelledList.add(beanItt);
				}
				creditBean.setCancelledVoucherIteratorList(cancelledList);
			}
			// Cancelled application Ends

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Method : getRejectedList.
	 * Purpose : Used to get approved application list.
	 * @param creditBean : Instance of CreditCheckRequest
	 * @param request : Instance of HttpServletRequest
	 * @param userId : Applicant Id
	 */
	public void getRejectedList(final CreditCheckRequest creditBean, final HttpServletRequest request, final String userId) {
		final List<CreditCheckRequest> rejectedList = new ArrayList<CreditCheckRequest>();
		// Rejected application Begins
		final Object[][] rejectedListData = this.getSqlModel().getSingleResult(this.returnQuery("'R'", userId));
		final String[] pageIndexRejected = Utility.doPaging(creditBean.getMyPageRejected(), rejectedListData.length, 20);
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
			creditBean.setMyPageRejected(this.oneStr);
		}

		if (rejectedListData != null && rejectedListData.length > 0) {
			creditBean.setRejectedVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
				final CreditCheckRequest beanItt = new CreditCheckRequest();
				beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(rejectedListData[i][0])));
				beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(rejectedListData[i][1])));
				beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(rejectedListData[i][2])));
				beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(rejectedListData[i][3])));
				beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			creditBean.setRejectedVoucherIteratorList(rejectedList);
		}
		// Rejected application Ends

		// Rejected cancellation application Begins
		final List<CreditCheckRequest> rejectedCancellationList = new ArrayList<CreditCheckRequest>();
		final Object[][] rejectedCancellationListData = this.getSqlModel().getSingleResult(this.returnQuery("'Z'", userId));
		final String[] pageIndexRejectedCancellation = Utility.doPaging(creditBean.getMyPageCancelRejected(), rejectedCancellationListData.length, 20);
		if (pageIndexRejectedCancellation == null) {
			pageIndexRejectedCancellation[0] = this.zeroStr;
			pageIndexRejectedCancellation[1] = this.twentyStr;
			pageIndexRejectedCancellation[2] = this.oneStr;
			pageIndexRejectedCancellation[3] = this.oneStr;
			pageIndexRejectedCancellation[4] = "";
		}

		request.setAttribute("totalRejectedCancellationPage", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[2])));
		request.setAttribute("rejectedCancellationPageNo", Integer.parseInt(String.valueOf(pageIndexRejectedCancellation[3])));
		if (pageIndexRejectedCancellation[4].equals(this.oneStr)) {
			creditBean.setMyPageCancelRejected(this.oneStr);
		}

		if (rejectedCancellationListData != null && rejectedCancellationListData.length > 0) {
			creditBean.setRejectedCancelVoucherListLength(true);
			for (int i = Integer.parseInt(pageIndexRejectedCancellation[0]); i < Integer.parseInt(pageIndexRejectedCancellation[1]); i++) {
				final CreditCheckRequest beanItt = new CreditCheckRequest();
				beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][0])));
				beanItt.setRequestingPersonNameIterator(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][1])));
				beanItt.setCompanyNameIterator(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][2])));
				beanItt.setCreditHiddenID(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][3])));
				beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(rejectedCancellationListData[i][4])));
				rejectedCancellationList.add(beanItt);
			}
			creditBean.setRejectedCancelVoucherIteratorList(rejectedCancellationList);
		}
		// Rejected cancellation application Ends
	}

	/**Method : cancelFunction.
	 * Purpose : Used to cancel application.
	 * @param creditBean : Instance of CreditCheckRequest
	 * @param status : Application status
	 * @return boolean
	 */
	public boolean cancelFunction(final CreditCheckRequest creditBean, final String status) {
		boolean result = false;
		try {
			final String changeStatusQuery = "UPDATE HRMS_D1_CREDIT_CHECK_REQ  SET HRMS_D1_CREDIT_CHECK_REQ.CREDIT_STATUS = '" + status + "'" + 
											 " WHERE HRMS_D1_CREDIT_CHECK_REQ.CREDIT_ID = " + creditBean.getCreditID();
			result = this.getSqlModel().singleExecute(changeStatusQuery);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method : isFinancePersonPresent.
	 * Purpose : Used to check whether current login user belongs to finance department or not
	 * @param creditBean : Instance of CreditCheckRequest
	 * @return boolean
	 */
	public boolean isFinancePersonPresent(final CreditCheckRequest creditBean) {
		boolean finalresult = false;
		try {
			final Object[][] queryObj = this.getSqlModel().getSingleResult("SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE ='F' ");
			if (queryObj != null && queryObj.length > 0) {
				finalresult = true;
			} else {
				finalresult = false;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return finalresult;
	}
	
	
	/**Method : returnQuery.
	 * Purpose : Used to return query with supplied status and used id
	 * @param status : Application status
	 * @param userId : Approver id
	 * @return String
	 */
	public String returnQuery(final String status, final String userId) {
		final String queryToReturn = "SELECT HRMS_D1_CREDIT_CHECK_REQ.CREDIT_TRACKING_NUMBER, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "	+ 
							   " HRMS_D1_CREDIT_CHECK_REQ.CREDIT_COMPANY, HRMS_D1_CREDIT_CHECK_REQ.CREDIT_ID, HRMS_D1_CREDIT_CHECK_REQ.CREDIT_STATUS " + 
							   " FROM HRMS_D1_CREDIT_CHECK_REQ " + 
							   " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CREDIT_CHECK_REQ.CREDIT_REQUESTING_ID) " + 
							   " WHERE HRMS_D1_CREDIT_CHECK_REQ.CREDIT_STATUS IN(" + status + ") AND HRMS_D1_CREDIT_CHECK_REQ.CREDIT_REQUESTING_ID = " + userId + 
							   " ORDER BY HRMS_D1_CREDIT_CHECK_REQ.CREDIT_ID DESC";
		return queryToReturn;
	}
}
