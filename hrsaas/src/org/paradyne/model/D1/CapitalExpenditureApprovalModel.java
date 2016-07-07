package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.D1.CapitalExpenditureApproval;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class CapitalExpenditureApprovalModel extends ModelBase {
	/** * logger. */
	private Logger logger = Logger.getLogger(CapitalExpenditureApprovalModel.class);
	/**	 * trueStr. */
	private String trueStr = "true";
	/**	 * falseStr. */
	private String falseStr = "false";
	/**	 * yStr. */
	private String yStr = "Y";
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

	/**Get pending list of applications.
	 * @param capApprBean : Instance of CapitalExpenditureApproval
	 * @param request : Instance of HttpServletRequest
	 */
	public void getPendingList(final CapitalExpenditureApproval capApprBean, final HttpServletRequest request) {
		try {
			final List<CapitalExpenditureApproval> pendingDataList = new ArrayList<CapitalExpenditureApproval>();
			final String selQuery =	" SELECT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), " + 
 								   " EXP_CODE, EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP " + 
 								   " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) " +
 								   " WHERE EXP_APPLICATION_STATUS IN ('P','F') AND EXP_APPROVER_CODE = " + capApprBean.getUserEmpId() + " ORDER BY EXP_TRACKING_NUMBER DESC "; 				
			
			final Object[][] pendingListData = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndex = Utility.doPaging(capApprBean.getMyPage(), pendingListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = this.zeroStr;
				pageIndex[1] = this.twentyStr;
				pageIndex[2] = this.oneStr;
				pageIndex[3] = this.oneStr;
				pageIndex[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.oneStr)){
				capApprBean.setMyPage(this.oneStr);
			}

			if (pendingListData != null && pendingListData.length > 0) {
				capApprBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CapitalExpenditureApproval beanItt = new CapitalExpenditureApproval();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(pendingListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(pendingListData[i][4])));
					pendingDataList.add(beanItt);
				}
				capApprBean.setPendingIteratorList(pendingDataList);
			}
			// Pending application Ends

			// pending cancellation application Begins
			final List<CapitalExpenditureApproval> pendingCancellationDataList = new ArrayList<CapitalExpenditureApproval>();
			String pendingCancellationQuery = returnRequiredQuery ("'C'", capApprBean.getUserEmpId());
			final Object[][] pendingCancellationListData  = this.getSqlModel().getSingleResult(pendingCancellationQuery);
			final String[] pagependingCancellationIndex = Utility.doPaging(capApprBean.getMyPagePendingCancel(), pendingCancellationListData.length, 20);
			if (pagependingCancellationIndex == null) {
				pagependingCancellationIndex[0] = this.zeroStr;
				pagependingCancellationIndex[1] = this.twentyStr;
				pagependingCancellationIndex[2] = this.oneStr;
				pagependingCancellationIndex[3] = this.oneStr;
				pagependingCancellationIndex[4] = "";
			}

			request.setAttribute("totalPendingCancellationPage", Integer.parseInt(String.valueOf(pagependingCancellationIndex[2])));
			request.setAttribute("pendingCancellationPageNo", Integer.parseInt(String.valueOf(pagependingCancellationIndex[3])));
			if (pagependingCancellationIndex[4].equals(this.oneStr)){
				capApprBean.setMyPagePendingCancel(this.oneStr);
			}

			if (pendingCancellationListData != null && pendingCancellationListData.length > 0) {
				capApprBean.setPendingCancellationListLength(true);
				for (int i = Integer.parseInt(pagependingCancellationIndex[0]); i < Integer.parseInt(pagependingCancellationIndex[1]); i++) {
					final CapitalExpenditureApproval beanItt = new CapitalExpenditureApproval();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(pendingCancellationListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(pendingCancellationListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(pendingCancellationListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(pendingCancellationListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(pendingCancellationListData[i][4])));
					pendingCancellationDataList.add(beanItt);
				}
				capApprBean.setPendingCancellationIteratorList(pendingCancellationDataList);
			}
			// pending cancellation application Ends
		} catch (Exception e) {
			this.logger.error("Exception occured in getPedingList : " + e);
		}
	} // End of getPending List

	/**Get approved list of applications.
	 * @param capApprBean : Instance of CapitalExpenditureApproval
	 * @param request : Instance of HttpServletRequest
	 */
	public void getApprovedList(final CapitalExpenditureApproval capApprBean,
			final HttpServletRequest request, final String userId) {
		try {
			Object[][] approvedListData = null;
			final List<CapitalExpenditureApproval> approvedList = new ArrayList<CapitalExpenditureApproval>();
			String code = this.zeroStr;
			final String groupQuery = " SELECT APP_SETTINGS_ID, APP_SETTINGS_EMP_ID, APP_SETTINGS_TYPE from HRMS_D1_APPROVAL_SETTINGS " + 
								" WHERE APP_SETTINGS_TYPE = 'S' AND  APP_SETTINGS_EMP_ID = " + userId + 
								" AND APP_SETTINGS_EMP_ID is not null order by APP_SETTINGS_ID";
			final Object[][] groupData = this.getSqlModel().getSingleResult(groupQuery);

			if (groupData != null && groupData.length > 0) {
				Object[][] pathData = this.getSqlModel().getSingleResult("SELECT DISTINCT EXP_CODE FROM HRMS_D1_CAPITALEXP_PATH WHERE EXP_APPROVER_CODE =" + userId);
				if (pathData != null && pathData.length > 0) {
					for (int i = 0; i < pathData.length; i++) {
						code += "," + String.valueOf(pathData[i][0]);
					}
				}
				final String selQuery11 = " SELECT DISTINCT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(HRMS_D1_CAPITALEXP.EXP_APPLICATION_DATE,'DD-MM-YYYY'), " + 
								    " EXP_CODE, EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP " + 
								    " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) " + 
								    " WHERE EXP_APPLICATION_STATUS IN('F','S') AND  HRMS_D1_CAPITALEXP.EXP_CODE in ("	+ code + ") ORDER BY EXP_TRACKING_NUMBER DESC";

				// Approved application Begins
				String selQuery = " SELECT DISTINCT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(HRMS_D1_CAPITALEXP.EXP_APPLICATION_DATE,'DD-MM-YYYY'), "
						+ " EXP_CODE, EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) "
						+ " WHERE EXP_APPLICATION_STATUS IN('A') " + "   ";

				selQuery += " UNION  " + selQuery11;
				approvedListData = this.getSqlModel().getSingleResult(selQuery);
				String[] pageIndexApproved = Utility.doPaging(capApprBean.getMyPageApproved(), approvedListData.length, 20);
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
					capApprBean.setMyPageApproved(this.oneStr);
				}

				if (approvedListData != null && approvedListData.length > 0) {
					capApprBean.setApprovedListLength(true);
					for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
						final CapitalExpenditureApproval beanItt = new CapitalExpenditureApproval();
						beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(approvedListData[i][0])));
						beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(approvedListData[i][1])));
						beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(approvedListData[i][2])));
						beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(approvedListData[i][3])));
						beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(approvedListData[i][4])));
						approvedList.add(beanItt);
					}
					capApprBean.setApproveredIteratorList(approvedList);
				}
			} else {
				final Object[][] pathData = this.getSqlModel().getSingleResult("SELECT DISTINCT EXP_CODE FROM HRMS_D1_CAPITALEXP_PATH WHERE EXP_APPROVER_CODE =" + userId);
				if (pathData != null && pathData.length > 0) {
					for (int i = 0; i < pathData.length; i++) {
						code += "," + String.valueOf(pathData[i][0]);
					}
				}

				if ((pathData != null && pathData.length > 0)) {
					String selQuery = " SELECT DISTINCT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(HRMS_D1_CAPITALEXP.EXP_APPLICATION_DATE,'DD-MM-YYYY'), " + 
									  " EXP_CODE, EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP " + 
									  " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) " + 
									  " WHERE EXP_APPLICATION_STATUS IN('A','F','S') AND  HRMS_D1_CAPITALEXP.EXP_CODE in ("	+ code + ")  " + 
									  " ORDER BY EXP_TRACKING_NUMBER DESC  ";

					approvedListData = this.getSqlModel().getSingleResult(selQuery);

					final String[] pageIndexApproved = Utility.doPaging(capApprBean.getMyPageApproved(), approvedListData.length, 20);
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
						capApprBean.setMyPageApproved(this.oneStr);
					}

					if (approvedListData != null && approvedListData.length > 0) {
						capApprBean.setApprovedListLength(true);
						for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
							final CapitalExpenditureApproval beanItt = new CapitalExpenditureApproval();
							beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(approvedListData[i][0])));
							beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(approvedListData[i][1])));
							beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(approvedListData[i][2])));
							beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(approvedListData[i][3])));
							beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(approvedListData[i][4])));
							approvedList.add(beanItt);
						}
						capApprBean.setApproveredIteratorList(approvedList);
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	} // End of getApprovedList List

	/**Get rejected list of applications.
	 * @param capApprBean : Instance of CapitalExpenditureApproval
	 * @param request : Instance of HttpServletRequest
	 */
	public void getRejectedList(final CapitalExpenditureApproval capApprBean,
			final HttpServletRequest request) {
		try {
			Object[][] rejectedListData = null;
			final List<CapitalExpenditureApproval> rejectedList = new ArrayList<CapitalExpenditureApproval>();
			final String groupQuery = " SELECT APP_SETTINGS_ID, APP_SETTINGS_EMP_ID, APP_SETTINGS_TYPE from HRMS_D1_APPROVAL_SETTINGS "
					+ " where APP_SETTINGS_TYPE = 'S' AND  APP_SETTINGS_EMP_ID = "
					+ capApprBean.getUserEmpId()
					+ " AND APP_SETTINGS_EMP_ID is not null order by APP_SETTINGS_ID";
			final Object[][] groupData = this.getSqlModel().getSingleResult(groupQuery);

			if (groupData != null && groupData.length > 0) {
				// Rejected application Begins
				final String selQuery = " SELECT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), "
						+ " EXP_CODE, EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) "
						+ " WHERE EXP_APPLICATION_STATUS = 'R'  ORDER BY EXP_TRACKING_NUMBER DESC ";
				rejectedListData = this.getSqlModel().getSingleResult(selQuery);
				final String[] pageIndexRejected = Utility.doPaging(capApprBean.getMyPageRejected(), rejectedListData.length, 20);
				if (pageIndexRejected == null) {
					pageIndexRejected[0] = this.zeroStr;
					pageIndexRejected[1] = this.twentyStr;
					pageIndexRejected[2] = this.oneStr;
					pageIndexRejected[3] = this.oneStr;
					pageIndexRejected[4] = "";
				}
				request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
				request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
				if (pageIndexRejected[4].equals(this.oneStr)){
					capApprBean.setMyPageRejected(this.oneStr);
				}
				if (rejectedListData != null && rejectedListData.length > 0) {
					capApprBean.setRejectedListLength(true);
					for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
						final CapitalExpenditureApproval beanItt = new CapitalExpenditureApproval();
						beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(rejectedListData[i][0])));
						beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(rejectedListData[i][1])));
						beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(rejectedListData[i][2])));
						beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(rejectedListData[i][3])));
						beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(rejectedListData[i][4])));
						rejectedList.add(beanItt);
					}
					capApprBean.setRejectedIteratorList(rejectedList);
				}
			} else {
				// Rejected application Begins
				String selQuery = " SELECT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), "
						+ " EXP_CODE, EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) "
						+ " WHERE EXP_APPLICATION_STATUS = 'R'  UNION ";

				selQuery += " SELECT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), "
						+ " EXP_CODE, EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) "
						+ " WHERE EXP_APPLICATION_STATUS = 'R' AND EXP_APPROVER_CODE = "
						+ capApprBean.getUserEmpId();

				rejectedListData = this.getSqlModel().getSingleResult(selQuery);
				final String[] pageIndexRejected = Utility.doPaging(capApprBean.getMyPageRejected(), rejectedListData.length, 20);
				if (pageIndexRejected == null) {
					pageIndexRejected[0] = this.zeroStr;
					pageIndexRejected[1] = this.twentyStr;
					pageIndexRejected[2] = this.oneStr;
					pageIndexRejected[3] = this.oneStr;
					pageIndexRejected[4] = "";
				}
				request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndexRejected[2])));
				request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndexRejected[3])));
				if (pageIndexRejected[4].equals(this.oneStr)){
					capApprBean.setMyPageRejected(this.oneStr);
				}
				if (rejectedListData != null && rejectedListData.length > 0) {
					capApprBean.setRejectedListLength(true);
					for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer.parseInt(pageIndexRejected[1]); i++) {
						final CapitalExpenditureApproval beanItt = new CapitalExpenditureApproval();
						beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(rejectedListData[i][0])));
						beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(rejectedListData[i][1])));
						beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(rejectedListData[i][2])));
						beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(rejectedListData[i][3])));
						beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(rejectedListData[i][4])));
						rejectedList.add(beanItt);
					}
					capApprBean.setRejectedIteratorList(rejectedList);
				}
			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in getRejectedList() : " + e);
		}
	} // End of getRejectedList List

	/**Get pending for authorization list of applications.
	 * @param capApprBean : Instance of CapitalExpenditureApproval
	 * @param request : Instance of HttpServletRequest
	 */
	public void getPendingAuthorizedList(final CapitalExpenditureApproval capApprBean, final HttpServletRequest request) {
		try {
			Object[][] pendingListData = null;
			final List<CapitalExpenditureApproval> pendingDataList = new ArrayList<CapitalExpenditureApproval>();

			// Pending application Begins
			String selQuery = " SELECT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), " + 
									" EXP_CODE, EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP " + 
									" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) " + 
									" WHERE EXP_APPLICATION_STATUS IN('P','S','F') " + 
									" AND (EXP_APPROVER_CODE = " + capApprBean.getUserEmpId() + 
									" OR EXP_APPROVER_CODE IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'S')) "; 

			selQuery += " UNION   SELECT EXP_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'),  EXP_CODE, EXP_APPLICATION_STATUS  FROM HRMS_D1_CAPITALEXP  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID)  WHERE EXP_APPLICATION_STATUS IN('S') ORDER BY EXP_TRACKING_NUMBER DESC  ";
			pendingListData = this.getSqlModel().getSingleResult(selQuery);

			final String[] pageIndex = Utility.doPaging(capApprBean.getMyPage(), pendingListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = this.zeroStr;
				pageIndex[1] = this.twentyStr;
				pageIndex[2] = this.oneStr;
				pageIndex[3] = this.oneStr;
				pageIndex[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.oneStr)){
				capApprBean.setMyPage(this.oneStr);
			}

			if (pendingListData != null && pendingListData.length > 0) {
				capApprBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CapitalExpenditureApproval beanItt = new CapitalExpenditureApproval();
					beanItt.setTrackingNumberIterator(Utility.checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setCompletedByIterator(Utility.checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setCompletedDateIterator(Utility.checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setCapitalHiddenID(Utility.checkNull(String.valueOf(pendingListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(pendingListData[i][4])));
					pendingDataList.add(beanItt);
				}
				capApprBean.setPendingIteratorList(pendingDataList);
			}
		} catch (Exception e) {
			this.logger.error("Exception occured in getPedingList : " + e);
		}
	} // End of Pending Forwarded List

	/**Used to view application details.
	 * @param capApprBean : CapitalExpenditureApproval
	 * @param capitalHiddenID : capitalHiddenID
	 * @param hiddenStatus : hiddenStatus
	 * @param request : request
	 */
	public void viewApplication(final CapitalExpenditureApproval capApprBean,
			final String capitalHiddenID, final String hiddenStatus,
			final HttpServletRequest request) {
		try {
			final String viewQuery = " SELECT EXP_CODE, EXP_EMP_ID, OFFC1.EMP_FNAME||' '||OFFC1.EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), " + 
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
					" OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME, EXP_APPROVER_TYPE, EXP_DEPT_NUMBER, " + 
					" EXP_VENDOR, EXP_TRACKING_NUMBER ,PO_NUMBER, PO_ATTACHMENT" + 
					" FROM HRMS_D1_CAPITALEXP " + 
					" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID =  HRMS_D1_CAPITALEXP.EXP_EMP_ID) " + 
					" LEFT JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID =  HRMS_D1_CAPITALEXP.EXP_APPROVER_CODE) " + 
					" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_D1_CAPITALEXP.EXP_LOCATION) " + 
					" WHERE EXP_CODE =" + capitalHiddenID;
			Object[][] viewObj = this.getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				capApprBean.setCapitalExpID(Utility.checkNull(String.valueOf(viewObj[0][0])));
				capApprBean.setSubmittedByID(Utility.checkNull(String.valueOf(viewObj[0][1])));
				capApprBean.setSubmittedByName(Utility.checkNull(String.valueOf(viewObj[0][2])));
				capApprBean.setSubmittedDate(Utility.checkNull(String.valueOf(viewObj[0][3])));
				if (String.valueOf(viewObj[0][4]).equals(this.yStr)) {
					capApprBean.setOriginalCheckbox(this.trueStr);
				} else {
					capApprBean.setOriginalCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][5]).equals(this.yStr)) {
					capApprBean.setPurchaseDeptCheckbox(this.trueStr);
				} else {
					capApprBean.setPurchaseDeptCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][6]).equals(this.yStr)) {
					capApprBean.setComputerITCheckbox(this.trueStr);
				} else {
					capApprBean.setComputerITCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][7]).equals(this.yStr)) {
					capApprBean.setLocalPurchaseCheckbox(this.trueStr);
				} else {
					capApprBean.setLocalPurchaseCheckbox(this.falseStr);
				}

				capApprBean.setBusinessJustification(Utility.checkNull(String.valueOf(viewObj[0][8])));
				capApprBean.setReasonForLocalPurchase(Utility.checkNull(String.valueOf(viewObj[0][9])));
				capApprBean.setDateRequired(Utility.checkNull(String.valueOf(viewObj[0][10])));
				capApprBean.setLocation(Utility.checkNull(String.valueOf(viewObj[0][11])));
				capApprBean.setIfOtherLocation(Utility.checkNull(String.valueOf(viewObj[0][12])));
				capApprBean.setAttention(Utility.checkNull(String.valueOf(viewObj[0][13])));
				if (String.valueOf(viewObj[0][14]).equals(this.yStr)) {
					capApprBean.setQuotesAttached(this.yesStr);
					capApprBean.setQuotesAttachedRadioOptionValue(this.yesStr);
				} else {
					capApprBean.setQuotesAttached(this.noStr);
					capApprBean.setQuotesAttachedRadioOptionValue(this.noStr);
				}

				capApprBean.setQuotesReason(Utility.checkNull(String.valueOf(viewObj[0][15])));
				capApprBean.setUploadFileNameFlag(true);
				capApprBean.setUploadFileName(Utility.checkNull(String.valueOf(viewObj[0][16])));
				capApprBean.setComments(Utility.checkNull(String.valueOf(viewObj[0][17])));
				capApprBean.setCostSubTotal(Utility.checkNull(String.valueOf(viewObj[0][18])));
				capApprBean.setInvoicePoNumber(Utility.checkNull(String.valueOf(viewObj[0][19])));
				capApprBean.setAssetSubTotal(Utility.checkNull(String.valueOf(viewObj[0][20])));
				capApprBean.setCostInstallation(Utility.checkNull(String.valueOf(viewObj[0][21])));
				capApprBean.setInvoiceVendorID(Utility.checkNull(String.valueOf(viewObj[0][22])));
				capApprBean.setActualInstallation(Utility.checkNull(String.valueOf(viewObj[0][23])));
				capApprBean.setCostMaterial(Utility.checkNull(String.valueOf(viewObj[0][24])));
				capApprBean.setInvoiceTotal(Utility.checkNull(String.valueOf(viewObj[0][25])));
				capApprBean.setActualMaterial(Utility.checkNull(String.valueOf(viewObj[0][26])));
				capApprBean.setCostFreight(Utility.checkNull(String.valueOf(viewObj[0][27])));
				capApprBean.setActualFreight(Utility.checkNull(String.valueOf(viewObj[0][28])));
				capApprBean.setCostTax(Utility.checkNull(String.valueOf(viewObj[0][29])));
				capApprBean.setDateTagged(Utility.checkNull(String.valueOf(viewObj[0][30])));
				capApprBean.setActualTax(Utility.checkNull(String.valueOf(viewObj[0][31])));
				capApprBean.setCostTotal(Utility.checkNull(String.valueOf(viewObj[0][32])));
				capApprBean.setActualTotal(Utility.checkNull(String.valueOf(viewObj[0][33])));
				capApprBean.setShipToCompany(Utility.checkNull(String.valueOf(viewObj[0][34])));
				capApprBean.setCity(Utility.checkNull(String.valueOf(viewObj[0][35])));
				capApprBean.setState(Utility.checkNull(String.valueOf(viewObj[0][36])));
				capApprBean.setStreetAddress(Utility.checkNull(String.valueOf(viewObj[0][37])));
				capApprBean.setZipCode(Utility.checkNull(String.valueOf(viewObj[0][38])));
				capApprBean.setTelePhoneNumber(Utility.checkNull(String.valueOf(viewObj[0][39])));
				capApprBean.setStatus(Utility.checkNull(String.valueOf(viewObj[0][40])));

				if (hiddenStatus.equals("F") || hiddenStatus.equals("P")) {
					capApprBean.setForwardedApproverID("");
					capApprBean.setForwardedApproverToken("");
					capApprBean.setForwardedApproverName("");
				} else {
					capApprBean.setForwardedApproverID(Utility.checkNull(String.valueOf(viewObj[0][41])));
					capApprBean.setForwardedApproverToken(Utility.checkNull(String.valueOf(viewObj[0][42])));
					capApprBean.setForwardedApproverName(Utility.checkNull(String.valueOf(viewObj[0][43])));
				}

				capApprBean.setDepartmentNumber(Utility.checkNull(String.valueOf(viewObj[0][45])));
				capApprBean.setInvoiceVendorNumber(Utility.checkNull(String.valueOf(viewObj[0][46])));
				capApprBean.setTrackingNumber(Utility.checkNull(String.valueOf(viewObj[0][47])));

				capApprBean.setPpoNumber(Utility.checkNull(String.valueOf(viewObj[0][48])));
				capApprBean.setPpoAttachement(Utility.checkNull(String.valueOf(viewObj[0][49])));
				// Sun-total of Cost-Total
				capApprBean.setSubTotalTotalCost(Utility.checkNull(String.valueOf(viewObj[0][18])));
				capApprBean.setHiddenForwardedType(Utility.checkNull(String.valueOf(viewObj[0][44])));
			}

			// Detail-Table entry Begins
			final String deatilQuery = "SELECT EXP_QUANTITY, EXP_DESC, EXP_VENDOR_NAME, " + 
					" EXP_UNIT_PRICE, EXP_TOTAL_COST, EXP_TAG_NO, EXP_SERIAL_NO, " + 
					" EXP_CEAR_PRICE FROM HRMS_D1_CAPITALEXP_DESC WHERE EXP_CODE=" + capitalHiddenID +
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
							(Double.parseDouble(checkDouble(String.valueOf(detailObj[0][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[1][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[2][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[3][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[4][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[5][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[6][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[7][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[8][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[9][3]))));
					capApprBean.setSubTotalUnitPrice(Double.valueOf(unitPriceSubTotal));
				} catch (final NumberFormatException nmf) {
					this.logger.error("Exception Unit Price Sub-Total : " + nmf);
				}

				try {
					double cEARSubTotal = 0.0;
					cEARSubTotal = cEARSubTotal + 
							(Double.parseDouble(checkDouble(String.valueOf(detailObj[0][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[1][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[2][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[3][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[4][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[5][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[6][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[7][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[8][7]))) +  
									Double.parseDouble(checkDouble(String.valueOf(detailObj[9][7]))));
 
					capApprBean.setSubTotalCEAR(Double.valueOf(cEARSubTotal));
				} catch (final NumberFormatException e) {
					this.logger.error("Exception CEAR Sub-Total : " + e);
				}

				capApprBean.setQuantity1(Utility.checkNull(String.valueOf(detailObj[0][0])));
				capApprBean.setDescription1(Utility.checkNull(String.valueOf(detailObj[0][1])));
				capApprBean.setVendorName1(Utility.checkNull(String.valueOf(detailObj[0][2])));
				capApprBean.setUnitPrice1(Utility.checkNull(String.valueOf(detailObj[0][3])));
				capApprBean.setTotalCost1(Utility.checkNull(String.valueOf(detailObj[0][4])));
				capApprBean.setTagNumber1(Utility.checkNull(String.valueOf(detailObj[0][5])));
				capApprBean.setSerialNumber1(Utility.checkNull(String.valueOf(detailObj[0][6])));
				capApprBean.setCearPrice1(Utility.checkNull(String.valueOf(detailObj[0][7])));

				capApprBean.setQuantity2(Utility.checkNull(String.valueOf(detailObj[1][0])));
				capApprBean.setDescription2(Utility.checkNull(String.valueOf(detailObj[1][1])));
				capApprBean.setVendorName2(Utility.checkNull(String.valueOf(detailObj[1][2])));
				capApprBean.setUnitPrice2(Utility.checkNull(String.valueOf(detailObj[1][3])));
				capApprBean.setTotalCost2(Utility.checkNull(String.valueOf(detailObj[1][4])));
				capApprBean.setTagNumber2(Utility.checkNull(String.valueOf(detailObj[1][5])));
				capApprBean.setSerialNumber2(Utility.checkNull(String.valueOf(detailObj[1][6])));
				capApprBean.setCearPrice2(Utility.checkNull(String.valueOf(detailObj[1][7])));

				capApprBean.setQuantity3(Utility.checkNull(String.valueOf(detailObj[2][0])));
				capApprBean.setDescription3(Utility.checkNull(String.valueOf(detailObj[2][1])));
				capApprBean.setVendorName3(Utility.checkNull(String.valueOf(detailObj[2][2])));
				capApprBean.setUnitPrice3(Utility.checkNull(String.valueOf(detailObj[2][3])));
				capApprBean.setTotalCost3(Utility.checkNull(String.valueOf(detailObj[2][4])));
				capApprBean.setTagNumber3(Utility.checkNull(String.valueOf(detailObj[2][5])));
				capApprBean.setSerialNumber3(Utility.checkNull(String.valueOf(detailObj[2][6])));
				capApprBean.setCearPrice3(Utility.checkNull(String.valueOf(detailObj[2][7])));

				capApprBean.setQuantity4(Utility.checkNull(String.valueOf(detailObj[3][0])));
				capApprBean.setDescription4(Utility.checkNull(String.valueOf(detailObj[3][1])));
				capApprBean.setVendorName4(Utility.checkNull(String.valueOf(detailObj[3][2])));
				capApprBean.setUnitPrice4(Utility.checkNull(String.valueOf(detailObj[3][3])));
				capApprBean.setTotalCost4(Utility.checkNull(String.valueOf(detailObj[3][4])));
				capApprBean.setTagNumber4(Utility.checkNull(String.valueOf(detailObj[3][5])));
				capApprBean.setSerialNumber4(Utility.checkNull(String.valueOf(detailObj[3][6])));
				capApprBean.setCearPrice4(Utility.checkNull(String.valueOf(detailObj[3][7])));

				capApprBean.setQuantity5(Utility.checkNull(String.valueOf(detailObj[4][0])));
				capApprBean.setDescription5(Utility.checkNull(String.valueOf(detailObj[4][1])));
				capApprBean.setVendorName5(Utility.checkNull(String.valueOf(detailObj[4][2])));
				capApprBean.setUnitPrice5(Utility.checkNull(String.valueOf(detailObj[4][3])));
				capApprBean.setTotalCost5(Utility.checkNull(String.valueOf(detailObj[4][4])));
				capApprBean.setTagNumber5(Utility.checkNull(String.valueOf(detailObj[4][5])));
				capApprBean.setSerialNumber5(Utility.checkNull(String.valueOf(detailObj[4][6])));
				capApprBean.setCearPrice5(Utility.checkNull(String.valueOf(detailObj[4][7])));

				capApprBean.setQuantity6(Utility.checkNull(String.valueOf(detailObj[5][0])));
				capApprBean.setDescription6(Utility.checkNull(String.valueOf(detailObj[5][1])));
				capApprBean.setVendorName6(Utility.checkNull(String.valueOf(detailObj[5][2])));
				capApprBean.setUnitPrice6(Utility.checkNull(String.valueOf(detailObj[5][3])));
				capApprBean.setTotalCost6(Utility.checkNull(String.valueOf(detailObj[5][4])));
				capApprBean.setTagNumber6(Utility.checkNull(String.valueOf(detailObj[5][5])));
				capApprBean.setSerialNumber6(Utility.checkNull(String.valueOf(detailObj[5][6])));
				capApprBean.setCearPrice6(Utility.checkNull(String.valueOf(detailObj[5][7])));

				capApprBean.setQuantity7(Utility.checkNull(String.valueOf(detailObj[6][0])));
				capApprBean.setDescription7(Utility.checkNull(String.valueOf(detailObj[6][1])));
				capApprBean.setVendorName7(Utility.checkNull(String.valueOf(detailObj[6][2])));
				capApprBean.setUnitPrice7(Utility.checkNull(String.valueOf(detailObj[6][3])));
				capApprBean.setTotalCost7(Utility.checkNull(String.valueOf(detailObj[6][4])));
				capApprBean.setTagNumber7(Utility.checkNull(String.valueOf(detailObj[6][5])));
				capApprBean.setSerialNumber7(Utility.checkNull(String.valueOf(detailObj[6][6])));
				capApprBean.setCearPrice7(Utility.checkNull(String.valueOf(detailObj[6][7])));

				capApprBean.setQuantity8(Utility.checkNull(String.valueOf(detailObj[7][0])));
				capApprBean.setDescription8(Utility.checkNull(String.valueOf(detailObj[7][1])));
				capApprBean.setVendorName8(Utility.checkNull(String.valueOf(detailObj[7][2])));
				capApprBean.setUnitPrice8(Utility.checkNull(String.valueOf(detailObj[7][3])));
				capApprBean.setTotalCost8(Utility.checkNull(String.valueOf(detailObj[7][4])));
				capApprBean.setTagNumber8(Utility.checkNull(String.valueOf(detailObj[7][5])));
				capApprBean.setSerialNumber8(Utility.checkNull(String.valueOf(detailObj[7][6])));
				capApprBean.setCearPrice8(Utility.checkNull(String.valueOf(detailObj[7][7])));

				capApprBean.setQuantity9(Utility.checkNull(String.valueOf(detailObj[8][0])));
				capApprBean.setDescription9(Utility.checkNull(String.valueOf(detailObj[8][1])));
				capApprBean.setVendorName9(Utility.checkNull(String.valueOf(detailObj[8][2])));
				capApprBean.setUnitPrice9(Utility.checkNull(String.valueOf(detailObj[8][3])));
				capApprBean.setTotalCost9(Utility.checkNull(String.valueOf(detailObj[8][4])));
				capApprBean.setTagNumber9(Utility.checkNull(String.valueOf(detailObj[8][5])));
				capApprBean.setSerialNumber9(Utility.checkNull(String.valueOf(detailObj[8][6])));
				capApprBean.setCearPrice9(Utility.checkNull(String.valueOf(detailObj[8][7])));

				capApprBean.setQuantity10(Utility.checkNull(String.valueOf(detailObj[9][0])));
				capApprBean.setDescription10(Utility.checkNull(String.valueOf(detailObj[9][1])));
				capApprBean.setVendorName10(Utility.checkNull(String.valueOf(detailObj[9][2])));
				capApprBean.setUnitPrice10(Utility.checkNull(String.valueOf(detailObj[9][3])));
				capApprBean.setTotalCost10(Utility.checkNull(String.valueOf(detailObj[9][4])));
				capApprBean.setTagNumber10(Utility.checkNull(String.valueOf(detailObj[9][5])));
				capApprBean.setSerialNumber10(Utility.checkNull(String.valueOf(detailObj[9][6])));
				capApprBean.setCearPrice10(Utility.checkNull(String.valueOf(detailObj[9][7])));
			}
			// Detail-Table entry Begins
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**View approved application details.
	 * @param capApprBean : CapitalExpenditureApproval
	 * @param capitalHiddenID : capitalHiddenID
	 * @param hiddenStatus : hiddenStatus
	 * @param request : request
	 */
	public void viewApplicationApproved(final CapitalExpenditureApproval capApprBean,
			final String capitalHiddenID, final String hiddenStatus,
			final HttpServletRequest request) {
		try {
			final String viewQuery = " SELECT EXP_CODE, EXP_EMP_ID, OFFC1.EMP_FNAME||' '||OFFC1.EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), " + 
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
					" OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME, EXP_APPROVER_TYPE, EXP_DEPT_NUMBER, " + 
					" EXP_VENDOR, EXP_TRACKING_NUMBER ,PO_NUMBER, PO_ATTACHMENT" + 
					" FROM HRMS_D1_CAPITALEXP " + 
					" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID =  HRMS_D1_CAPITALEXP.EXP_EMP_ID) " + 
					" LEFT JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID =  HRMS_D1_CAPITALEXP.EXP_APPROVER_CODE) " + 
					" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_D1_CAPITALEXP.EXP_LOCATION) " + 
					" WHERE EXP_CODE =" + capitalHiddenID;
			final Object[][] viewObj = this.getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				capApprBean.setCapitalExpID(Utility.checkNull(String.valueOf(viewObj[0][0])));
				capApprBean.setSubmittedByID(Utility.checkNull(String.valueOf(viewObj[0][1])));
				capApprBean.setSubmittedByName(Utility.checkNull(String.valueOf(viewObj[0][2])));
				capApprBean.setSubmittedDate(Utility.checkNull(String.valueOf(viewObj[0][3])));
				if (String.valueOf(viewObj[0][4]).equals(this.yStr)) {
					capApprBean.setOriginalCheckbox(this.trueStr);
				} else {
					capApprBean.setOriginalCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][5]).equals(this.yStr)) {
					capApprBean.setPurchaseDeptCheckbox(this.trueStr);
				} else {
					capApprBean.setPurchaseDeptCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][6]).equals(this.yStr)) {
					capApprBean.setComputerITCheckbox(this.trueStr);
				} else {
					capApprBean.setComputerITCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][7]).equals(this.yStr)) {
					capApprBean.setLocalPurchaseCheckbox(this.trueStr);
				} else {
					capApprBean.setLocalPurchaseCheckbox(this.falseStr);
				}

				capApprBean.setBusinessJustification(Utility.checkNull(String.valueOf(viewObj[0][8])));
				capApprBean.setReasonForLocalPurchase(Utility.checkNull(String.valueOf(viewObj[0][9])));
				capApprBean.setDateRequired(Utility.checkNull(String.valueOf(viewObj[0][10])));
				capApprBean.setLocation(Utility.checkNull(String.valueOf(viewObj[0][11])));
				capApprBean.setIfOtherLocation(Utility.checkNull(String.valueOf(viewObj[0][12])));
				capApprBean.setAttention(Utility.checkNull(String.valueOf(viewObj[0][13])));
				if (String.valueOf(viewObj[0][14]).equals(this.yStr)) {
					capApprBean.setQuotesAttached(this.yesStr);
					capApprBean.setQuotesAttachedRadioOptionValue(this.yesStr);
				} else {
					capApprBean.setQuotesAttached(this.noStr);
					capApprBean.setQuotesAttachedRadioOptionValue(this.noStr);
				}

				capApprBean.setQuotesReason(Utility.checkNull(String.valueOf(viewObj[0][15])));
				capApprBean.setUploadFileNameFlag(true);
				capApprBean.setUploadFileName(Utility.checkNull(String.valueOf(viewObj[0][16])));
				capApprBean.setComments(Utility.checkNull(String.valueOf(viewObj[0][17])));
				capApprBean.setCostSubTotal(Utility.checkNull(String.valueOf(viewObj[0][18])));
				capApprBean.setInvoicePoNumber(Utility.checkNull(String.valueOf(viewObj[0][19])));
				capApprBean.setAssetSubTotal(Utility.checkNull(String.valueOf(viewObj[0][20])));
				capApprBean.setCostInstallation(Utility.checkNull(String.valueOf(viewObj[0][21])));
				capApprBean.setInvoiceVendorID(Utility.checkNull(String.valueOf(viewObj[0][22])));
				capApprBean.setActualInstallation(Utility.checkNull(String.valueOf(viewObj[0][23])));
				capApprBean.setCostMaterial(Utility.checkNull(String.valueOf(viewObj[0][24])));
				capApprBean.setInvoiceTotal(Utility.checkNull(String.valueOf(viewObj[0][25])));
				capApprBean.setActualMaterial(Utility.checkNull(String.valueOf(viewObj[0][26])));
				capApprBean.setCostFreight(Utility.checkNull(String.valueOf(viewObj[0][27])));
				capApprBean.setActualFreight(Utility.checkNull(String.valueOf(viewObj[0][28])));
				capApprBean.setCostTax(Utility.checkNull(String.valueOf(viewObj[0][29])));
				capApprBean.setDateTagged(Utility.checkNull(String.valueOf(viewObj[0][30])));
				capApprBean.setActualTax(Utility.checkNull(String.valueOf(viewObj[0][31])));
				capApprBean.setCostTotal(Utility.checkNull(String.valueOf(viewObj[0][32])));
				capApprBean.setActualTotal(Utility.checkNull(String.valueOf(viewObj[0][33])));
				capApprBean.setShipToCompany(Utility.checkNull(String.valueOf(viewObj[0][34])));
				capApprBean.setCity(Utility.checkNull(String.valueOf(viewObj[0][35])));
				capApprBean.setState(Utility.checkNull(String.valueOf(viewObj[0][36])));
				capApprBean.setStreetAddress(Utility.checkNull(String.valueOf(viewObj[0][37])));
				capApprBean.setZipCode(Utility.checkNull(String.valueOf(viewObj[0][38])));
				capApprBean.setTelePhoneNumber(Utility.checkNull(String.valueOf(viewObj[0][39])));
				capApprBean.setStatus(Utility.checkNull(String.valueOf(viewObj[0][40])));

				if (hiddenStatus.equals("S")) {
					capApprBean.setForwardedApproverID("");
					capApprBean.setForwardedApproverToken("");
					capApprBean.setForwardedApproverName("");
				}

				else if (hiddenStatus.equals("A")) {
					capApprBean.setForwardedApproverID("");
					capApprBean.setForwardedApproverToken("");
					capApprBean.setForwardedApproverName("");
				}

				else if (hiddenStatus.equals("F")) {
					capApprBean.setForwardedApproverID(Utility.checkNull(String.valueOf(viewObj[0][41])));
					capApprBean.setForwardedApproverToken(Utility.checkNull(String.valueOf(viewObj[0][42])));
					capApprBean.setForwardedApproverName(Utility.checkNull(String.valueOf(viewObj[0][43])));

				} else {
					capApprBean.setForwardedApproverID("");
					capApprBean.setForwardedApproverToken("");
					capApprBean.setForwardedApproverName("");
				}

				capApprBean.setForwardedApproverType(Utility.checkNull(String.valueOf(viewObj[0][44])));
				capApprBean.setDepartmentNumber(Utility.checkNull(String.valueOf(viewObj[0][45])));
				capApprBean.setInvoiceVendorNumber(Utility.checkNull(String.valueOf(viewObj[0][46])));
				capApprBean.setTrackingNumber(Utility.checkNull(String.valueOf(viewObj[0][47])));

				capApprBean.setPpoNumber(Utility.checkNull(String.valueOf(viewObj[0][48])));
				capApprBean.setPpoAttachement(Utility.checkNull(String.valueOf(viewObj[0][49])));
				// Sun-total of Cost-Total
				capApprBean.setSubTotalTotalCost(Utility.checkNull(String.valueOf(viewObj[0][18])));
				capApprBean.setHiddenForwardedType(Utility.checkNull(String.valueOf(viewObj[0][44])));
			}

			// Detail-Table entry Begins
			final String deatilQuery = "SELECT EXP_QUANTITY, EXP_DESC, EXP_VENDOR_NAME, " + 
					" EXP_UNIT_PRICE, EXP_TOTAL_COST, EXP_TAG_NO, EXP_SERIAL_NO, " + 
					" EXP_CEAR_PRICE FROM HRMS_D1_CAPITALEXP_DESC " + 
					" WHERE EXP_CODE = "+ capitalHiddenID + 
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
							(Double.parseDouble(checkDouble(String.valueOf(detailObj[0][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[1][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[2][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[3][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[4][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[5][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[6][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[7][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[8][3]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[9][3]))));
					capApprBean.setSubTotalUnitPrice(Double.valueOf(unitPriceSubTotal));
				} catch (final NumberFormatException nmf) {
					this.logger.error("Exception Unit Price Sub-Total : " + nmf);
				}

				try {
					double cEARSubTotal = 0.0;
					cEARSubTotal = cEARSubTotal + 
							 (Double.parseDouble(checkDouble(String.valueOf(detailObj[0][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[1][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[2][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[3][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[4][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[5][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[6][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[7][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[8][7]))) + 
									Double.parseDouble(checkDouble(String.valueOf(detailObj[9][7]))));
					capApprBean.setSubTotalCEAR(Double.valueOf(cEARSubTotal));
				} catch (final NumberFormatException e) {
					this.logger.error("Exception CEAR Sub-Total : " + e);
				}

				capApprBean.setQuantity1(Utility.checkNull(String.valueOf(detailObj[0][0])));
				capApprBean.setDescription1(Utility.checkNull(String.valueOf(detailObj[0][1])));
				capApprBean.setVendorName1(Utility.checkNull(String.valueOf(detailObj[0][2])));
				capApprBean.setUnitPrice1(Utility.checkNull(String.valueOf(detailObj[0][3])));
				capApprBean.setTotalCost1(Utility.checkNull(String.valueOf(detailObj[0][4])));
				capApprBean.setTagNumber1(Utility.checkNull(String.valueOf(detailObj[0][5])));
				capApprBean.setSerialNumber1(Utility.checkNull(String.valueOf(detailObj[0][6])));
				capApprBean.setCearPrice1(Utility.checkNull(String.valueOf(detailObj[0][7])));

				capApprBean.setQuantity2(Utility.checkNull(String.valueOf(detailObj[1][0])));
				capApprBean.setDescription2(Utility.checkNull(String.valueOf(detailObj[1][1])));
				capApprBean.setVendorName2(Utility.checkNull(String.valueOf(detailObj[1][2])));
				capApprBean.setUnitPrice2(Utility.checkNull(String.valueOf(detailObj[1][3])));
				capApprBean.setTotalCost2(Utility.checkNull(String.valueOf(detailObj[1][4])));
				capApprBean.setTagNumber2(Utility.checkNull(String.valueOf(detailObj[1][5])));
				capApprBean.setSerialNumber2(Utility.checkNull(String.valueOf(detailObj[1][6])));
				capApprBean.setCearPrice2(Utility.checkNull(String.valueOf(detailObj[1][7])));

				capApprBean.setQuantity3(Utility.checkNull(String.valueOf(detailObj[2][0])));
				capApprBean.setDescription3(Utility.checkNull(String.valueOf(detailObj[2][1])));
				capApprBean.setVendorName3(Utility.checkNull(String.valueOf(detailObj[2][2])));
				capApprBean.setUnitPrice3(Utility.checkNull(String.valueOf(detailObj[2][3])));
				capApprBean.setTotalCost3(Utility.checkNull(String.valueOf(detailObj[2][4])));
				capApprBean.setTagNumber3(Utility.checkNull(String.valueOf(detailObj[2][5])));
				capApprBean.setSerialNumber3(Utility.checkNull(String.valueOf(detailObj[2][6])));
				capApprBean.setCearPrice3(Utility.checkNull(String.valueOf(detailObj[2][7])));

				capApprBean.setQuantity4(Utility.checkNull(String.valueOf(detailObj[3][0])));
				capApprBean.setDescription4(Utility.checkNull(String.valueOf(detailObj[3][1])));
				capApprBean.setVendorName4(Utility.checkNull(String.valueOf(detailObj[3][2])));
				capApprBean.setUnitPrice4(Utility.checkNull(String.valueOf(detailObj[3][3])));
				capApprBean.setTotalCost4(Utility.checkNull(String.valueOf(detailObj[3][4])));
				capApprBean.setTagNumber4(Utility.checkNull(String.valueOf(detailObj[3][5])));
				capApprBean.setSerialNumber4(Utility.checkNull(String.valueOf(detailObj[3][6])));
				capApprBean.setCearPrice4(Utility.checkNull(String.valueOf(detailObj[3][7])));

				capApprBean.setQuantity5(Utility.checkNull(String.valueOf(detailObj[4][0])));
				capApprBean.setDescription5(Utility.checkNull(String.valueOf(detailObj[4][1])));
				capApprBean.setVendorName5(Utility.checkNull(String.valueOf(detailObj[4][2])));
				capApprBean.setUnitPrice5(Utility.checkNull(String.valueOf(detailObj[4][3])));
				capApprBean.setTotalCost5(Utility.checkNull(String.valueOf(detailObj[4][4])));
				capApprBean.setTagNumber5(Utility.checkNull(String.valueOf(detailObj[4][5])));
				capApprBean.setSerialNumber5(Utility.checkNull(String.valueOf(detailObj[4][6])));
				capApprBean.setCearPrice5(Utility.checkNull(String.valueOf(detailObj[4][7])));

				capApprBean.setQuantity6(Utility.checkNull(String.valueOf(detailObj[5][0])));
				capApprBean.setDescription6(Utility.checkNull(String.valueOf(detailObj[5][1])));
				capApprBean.setVendorName6(Utility.checkNull(String.valueOf(detailObj[5][2])));
				capApprBean.setUnitPrice6(Utility.checkNull(String.valueOf(detailObj[5][3])));
				capApprBean.setTotalCost6(Utility.checkNull(String.valueOf(detailObj[5][4])));
				capApprBean.setTagNumber6(Utility.checkNull(String.valueOf(detailObj[5][5])));
				capApprBean.setSerialNumber6(Utility.checkNull(String.valueOf(detailObj[5][6])));
				capApprBean.setCearPrice6(Utility.checkNull(String.valueOf(detailObj[5][7])));

				capApprBean.setQuantity7(Utility.checkNull(String.valueOf(detailObj[6][0])));
				capApprBean.setDescription7(Utility.checkNull(String.valueOf(detailObj[6][1])));
				capApprBean.setVendorName7(Utility.checkNull(String.valueOf(detailObj[6][2])));
				capApprBean.setUnitPrice7(Utility.checkNull(String.valueOf(detailObj[6][3])));
				capApprBean.setTotalCost7(Utility.checkNull(String.valueOf(detailObj[6][4])));
				capApprBean.setTagNumber7(Utility.checkNull(String.valueOf(detailObj[6][5])));
				capApprBean.setSerialNumber7(Utility.checkNull(String.valueOf(detailObj[6][6])));
				capApprBean.setCearPrice7(Utility.checkNull(String.valueOf(detailObj[6][7])));

				capApprBean.setQuantity8(Utility.checkNull(String.valueOf(detailObj[7][0])));
				capApprBean.setDescription8(Utility.checkNull(String.valueOf(detailObj[7][1])));
				capApprBean.setVendorName8(Utility.checkNull(String.valueOf(detailObj[7][2])));
				capApprBean.setUnitPrice8(Utility.checkNull(String.valueOf(detailObj[7][3])));
				capApprBean.setTotalCost8(Utility.checkNull(String.valueOf(detailObj[7][4])));
				capApprBean.setTagNumber8(Utility.checkNull(String.valueOf(detailObj[7][5])));
				capApprBean.setSerialNumber8(Utility.checkNull(String.valueOf(detailObj[7][6])));
				capApprBean.setCearPrice8(Utility.checkNull(String.valueOf(detailObj[7][7])));

				capApprBean.setQuantity9(Utility.checkNull(String.valueOf(detailObj[8][0])));
				capApprBean.setDescription9(Utility.checkNull(String.valueOf(detailObj[8][1])));
				capApprBean.setVendorName9(Utility.checkNull(String.valueOf(detailObj[8][2])));
				capApprBean.setUnitPrice9(Utility.checkNull(String.valueOf(detailObj[8][3])));
				capApprBean.setTotalCost9(Utility.checkNull(String.valueOf(detailObj[8][4])));
				capApprBean.setTagNumber9(Utility.checkNull(String.valueOf(detailObj[8][5])));
				capApprBean.setSerialNumber9(Utility.checkNull(String.valueOf(detailObj[8][6])));
				capApprBean.setCearPrice9(Utility.checkNull(String.valueOf(detailObj[8][7])));

				capApprBean.setQuantity10(Utility.checkNull(String.valueOf(detailObj[9][0])));
				capApprBean.setDescription10(Utility.checkNull(String.valueOf(detailObj[9][1])));
				capApprBean.setVendorName10(Utility.checkNull(String.valueOf(detailObj[9][2])));
				capApprBean.setUnitPrice10(Utility.checkNull(String.valueOf(detailObj[9][3])));
				capApprBean.setTotalCost10(Utility.checkNull(String.valueOf(detailObj[9][4])));
				capApprBean.setTagNumber10(Utility.checkNull(String.valueOf(detailObj[9][5])));
				capApprBean.setSerialNumber10(Utility.checkNull(String.valueOf(detailObj[9][6])));
				capApprBean.setCearPrice10(Utility.checkNull(String.valueOf(detailObj[9][7])));
			}
			// Detail-Table entry Begins

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void viewApplicationRejected(final CapitalExpenditureApproval capApprBean,
			String capitalHiddenID, String hiddenStatus,
			HttpServletRequest request) {
		try {
			String viewQuery = " SELECT EXP_CODE, EXP_EMP_ID, OFFC1.EMP_FNAME||' '||OFFC1.EMP_LNAME, TO_CHAR(EXP_APPLICATION_DATE,'DD-MM-YYYY'), "
					+ " EXP_ORIGINAL, EXP_PURCHASE_DEPT, EXP_COMP_IT, EXP_LOCAL_PURCHASE, "
					+ " EXP_BUSS_JUSTFICATION, EXP_RES_LOCAL_PURCH, TO_CHAR(EXP_DATE_REQUIRED,'DD-MM-YYYY'), "
					+ " CENTER_NAME, EXP_OTHER_LOCATION, EXP_ATTEN, EXP_IS_QUOTES, "
					+ " EXP_QUOTES_REASON, EXP_OTHER_ATTACH, EXP_ADDITIONAL_COMMENTS, EXP_COST_BRKDWN_SUB_TOTAL, "
					+ " EXP_PO_NO, EXP_ASSET_SUB_TOTAL, EXP_INSTALL_COST, EXP_VENDOR, "
					+ " EXP_ACT_INSTALL_COST, EXP_MATERIAL_COST, EXP_INVOICE_TOTAL, EXP_ACT_MATERIAL_COST, "
					+ " EXP_FREIGHT, EXP_ACT_FREIGHT, EXP_TAX, TO_CHAR(EXP_DATE_TAGGED,'DD-MM-YYYY'), "
					+ " EXP_ACT_TAX, EXP_BRK_DWN_TOAL, EXP_ACT_TOTAL, EXP_SHIP_TO_COMPANY, "
					+ " EXP_CITY, EXP_STATE, EXP_STREET_ADDRESS, EXP_ZIPCODE, "
					+ " EXP_PHONE, '_'||EXP_APPLICATION_STATUS, OFFC2.EMP_ID, OFFC2.EMP_TOKEN, "
					+ " OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME, EXP_APPROVER_TYPE, EXP_DEPT_NUMBER, "
					+ " EXP_VENDOR, EXP_TRACKING_NUMBER ,PO_NUMBER, PO_ATTACHMENT"
					+ " FROM HRMS_D1_CAPITALEXP "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID =  HRMS_D1_CAPITALEXP.EXP_EMP_ID) "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID =  HRMS_D1_CAPITALEXP.EXP_APPROVER_CODE) "
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_D1_CAPITALEXP.EXP_LOCATION) "
					+ " WHERE EXP_CODE =" + capitalHiddenID;
			Object[][] viewObj = this.getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				capApprBean.setCapitalExpID(Utility.checkNull(String.valueOf(viewObj[0][0])));
				capApprBean.setSubmittedByID(Utility.checkNull(String.valueOf(viewObj[0][1])));
				capApprBean.setSubmittedByName(Utility.checkNull(String.valueOf(viewObj[0][2])));
				capApprBean.setSubmittedDate(Utility.checkNull(String.valueOf(viewObj[0][3])));
				if (String.valueOf(viewObj[0][4]).equals(this.yStr)) {
					capApprBean.setOriginalCheckbox(this.trueStr);
				} else {
					capApprBean.setOriginalCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][5]).equals(this.yStr)) {
					capApprBean.setPurchaseDeptCheckbox(this.trueStr);
				} else {
					capApprBean.setPurchaseDeptCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][6]).equals(this.yStr)) {
					capApprBean.setComputerITCheckbox(this.trueStr);
				} else {
					capApprBean.setComputerITCheckbox(this.falseStr);
				}

				if (String.valueOf(viewObj[0][7]).equals(this.yStr)) {
					capApprBean.setLocalPurchaseCheckbox(this.trueStr);
				} else {
					capApprBean.setLocalPurchaseCheckbox(this.falseStr);
				}

				capApprBean.setBusinessJustification(Utility.checkNull(String.valueOf(viewObj[0][8])));
				capApprBean.setReasonForLocalPurchase(Utility.checkNull(String.valueOf(viewObj[0][9])));
				capApprBean.setDateRequired(Utility.checkNull(String.valueOf(viewObj[0][10])));
				capApprBean.setLocation(Utility.checkNull(String.valueOf(viewObj[0][11])));
				capApprBean.setIfOtherLocation(Utility.checkNull(String.valueOf(viewObj[0][12])));
				capApprBean.setAttention(Utility.checkNull(String.valueOf(viewObj[0][13])));
				if (String.valueOf(viewObj[0][14]).equals(this.yStr)) {
					capApprBean.setQuotesAttached(this.yesStr);
					capApprBean.setQuotesAttachedRadioOptionValue(this.yesStr);
				} else {
					capApprBean.setQuotesAttached(this.noStr);
					capApprBean.setQuotesAttachedRadioOptionValue(this.noStr);
				}

				capApprBean.setQuotesReason(Utility.checkNull(String.valueOf(viewObj[0][15])));
				capApprBean.setUploadFileNameFlag(true);
				capApprBean.setUploadFileName(Utility.checkNull(String.valueOf(viewObj[0][16])));
				capApprBean.setComments(Utility.checkNull(String.valueOf(viewObj[0][17])));
				capApprBean.setCostSubTotal(Utility.checkNull(String.valueOf(viewObj[0][18])));
				capApprBean.setInvoicePoNumber(Utility.checkNull(String.valueOf(viewObj[0][19])));
				capApprBean.setAssetSubTotal(Utility.checkNull(String.valueOf(viewObj[0][20])));
				capApprBean.setCostInstallation(Utility.checkNull(String.valueOf(viewObj[0][21])));
				capApprBean.setInvoiceVendorID(Utility.checkNull(String.valueOf(viewObj[0][22])));
				capApprBean.setActualInstallation(Utility.checkNull(String.valueOf(viewObj[0][23])));
				capApprBean.setCostMaterial(Utility.checkNull(String.valueOf(viewObj[0][24])));
				capApprBean.setInvoiceTotal(Utility.checkNull(String.valueOf(viewObj[0][25])));
				capApprBean.setActualMaterial(Utility.checkNull(String.valueOf(viewObj[0][26])));
				capApprBean.setCostFreight(Utility.checkNull(String.valueOf(viewObj[0][27])));
				capApprBean.setActualFreight(Utility.checkNull(String.valueOf(viewObj[0][28])));
				capApprBean.setCostTax(Utility.checkNull(String.valueOf(viewObj[0][29])));
				capApprBean.setDateTagged(Utility.checkNull(String.valueOf(viewObj[0][30])));
				capApprBean.setActualTax(Utility.checkNull(String.valueOf(viewObj[0][31])));
				capApprBean.setCostTotal(Utility.checkNull(String.valueOf(viewObj[0][32])));
				capApprBean.setActualTotal(Utility.checkNull(String.valueOf(viewObj[0][33])));
				capApprBean.setShipToCompany(Utility.checkNull(String.valueOf(viewObj[0][34])));
				capApprBean.setCity(Utility.checkNull(String.valueOf(viewObj[0][35])));
				capApprBean.setState(Utility.checkNull(String.valueOf(viewObj[0][36])));
				capApprBean.setStreetAddress(Utility.checkNull(String.valueOf(viewObj[0][37])));
				capApprBean.setZipCode(Utility.checkNull(String.valueOf(viewObj[0][38])));
				capApprBean.setTelePhoneNumber(Utility.checkNull(String.valueOf(viewObj[0][39])));
				capApprBean.setStatus(Utility.checkNull(String.valueOf(viewObj[0][40])));

				if (hiddenStatus.equals("R")) {
					capApprBean.setForwardedApproverID("");
					capApprBean.setForwardedApproverToken("");
					capApprBean.setForwardedApproverName("");
				} else {
					capApprBean.setForwardedApproverID(Utility.checkNull(String.valueOf(viewObj[0][41])));
					capApprBean.setForwardedApproverToken(Utility.checkNull(String.valueOf(viewObj[0][42])));
					capApprBean.setForwardedApproverName(Utility.checkNull(String.valueOf(viewObj[0][43])));
				}

				capApprBean.setForwardedApproverType(Utility.checkNull(String.valueOf(viewObj[0][44])));
				capApprBean.setDepartmentNumber(Utility.checkNull(String.valueOf(viewObj[0][45])));
				capApprBean.setInvoiceVendorNumber(Utility.checkNull(String.valueOf(viewObj[0][46])));
				capApprBean.setTrackingNumber(Utility.checkNull(String.valueOf(viewObj[0][47])));

				capApprBean.setPpoNumber(Utility.checkNull(String.valueOf(viewObj[0][48])));
				capApprBean.setPpoAttachement(Utility.checkNull(String.valueOf(viewObj[0][49])));
				// Sun-total of Cost-Total
				capApprBean.setSubTotalTotalCost(Utility.checkNull(String.valueOf(viewObj[0][18])));
				capApprBean.setHiddenForwardedType(Utility.checkNull(String.valueOf(viewObj[0][44])));
			}

			// Detail-Table entry Begins
			final String deatilQuery = "SELECT EXP_QUANTITY, EXP_DESC, EXP_VENDOR_NAME, " + 
					" EXP_UNIT_PRICE, EXP_TOTAL_COST, EXP_TAG_NO, EXP_SERIAL_NO, " + 
					" EXP_CEAR_PRICE FROM HRMS_D1_CAPITALEXP_DESC " + 
					" WHERE EXP_CODE = " + capitalHiddenID + 
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
					capApprBean.setSubTotalUnitPrice(Double.valueOf(unitPriceSubTotal));
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
					capApprBean.setSubTotalCEAR(Double.valueOf(cEARSubTotal));
				} catch (final NumberFormatException e) {
					this.logger.error("Exception CEAR Sub-Total : " + e);
				}
				
				capApprBean.setQuantity1(Utility.checkNull(String.valueOf(detailObj[0][0])));
				capApprBean.setDescription1(Utility.checkNull(String.valueOf(detailObj[0][1])));
				capApprBean.setVendorName1(Utility.checkNull(String.valueOf(detailObj[0][2])));
				capApprBean.setUnitPrice1(Utility.checkNull(String.valueOf(detailObj[0][3])));
				capApprBean.setTotalCost1(Utility.checkNull(String.valueOf(detailObj[0][4])));
				capApprBean.setTagNumber1(Utility.checkNull(String.valueOf(detailObj[0][5])));
				capApprBean.setSerialNumber1(Utility.checkNull(String.valueOf(detailObj[0][6])));
				capApprBean.setCearPrice1(Utility.checkNull(String.valueOf(detailObj[0][7])));

				capApprBean.setQuantity2(Utility.checkNull(String.valueOf(detailObj[1][0])));
				capApprBean.setDescription2(Utility.checkNull(String.valueOf(detailObj[1][1])));
				capApprBean.setVendorName2(Utility.checkNull(String.valueOf(detailObj[1][2])));
				capApprBean.setUnitPrice2(Utility.checkNull(String.valueOf(detailObj[1][3])));
				capApprBean.setTotalCost2(Utility.checkNull(String.valueOf(detailObj[1][4])));
				capApprBean.setTagNumber2(Utility.checkNull(String.valueOf(detailObj[1][5])));
				capApprBean.setSerialNumber2(Utility.checkNull(String.valueOf(detailObj[1][6])));
				capApprBean.setCearPrice2(Utility.checkNull(String.valueOf(detailObj[1][7])));

				capApprBean.setQuantity3(Utility.checkNull(String.valueOf(detailObj[2][0])));
				capApprBean.setDescription3(Utility.checkNull(String.valueOf(detailObj[2][1])));
				capApprBean.setVendorName3(Utility.checkNull(String.valueOf(detailObj[2][2])));
				capApprBean.setUnitPrice3(Utility.checkNull(String.valueOf(detailObj[2][3])));
				capApprBean.setTotalCost3(Utility.checkNull(String.valueOf(detailObj[2][4])));
				capApprBean.setTagNumber3(Utility.checkNull(String.valueOf(detailObj[2][5])));
				capApprBean.setSerialNumber3(Utility.checkNull(String.valueOf(detailObj[2][6])));
				capApprBean.setCearPrice3(Utility.checkNull(String.valueOf(detailObj[2][7])));

				capApprBean.setQuantity4(Utility.checkNull(String.valueOf(detailObj[3][0])));
				capApprBean.setDescription4(Utility.checkNull(String.valueOf(detailObj[3][1])));
				capApprBean.setVendorName4(Utility.checkNull(String.valueOf(detailObj[3][2])));
				capApprBean.setUnitPrice4(Utility.checkNull(String.valueOf(detailObj[3][3])));
				capApprBean.setTotalCost4(Utility.checkNull(String.valueOf(detailObj[3][4])));
				capApprBean.setTagNumber4(Utility.checkNull(String.valueOf(detailObj[3][5])));
				capApprBean.setSerialNumber4(Utility.checkNull(String.valueOf(detailObj[3][6])));
				capApprBean.setCearPrice4(Utility.checkNull(String.valueOf(detailObj[3][7])));

				capApprBean.setQuantity5(Utility.checkNull(String.valueOf(detailObj[4][0])));
				capApprBean.setDescription5(Utility.checkNull(String.valueOf(detailObj[4][1])));
				capApprBean.setVendorName5(Utility.checkNull(String.valueOf(detailObj[4][2])));
				capApprBean.setUnitPrice5(Utility.checkNull(String.valueOf(detailObj[4][3])));
				capApprBean.setTotalCost5(Utility.checkNull(String.valueOf(detailObj[4][4])));
				capApprBean.setTagNumber5(Utility.checkNull(String.valueOf(detailObj[4][5])));
				capApprBean.setSerialNumber5(Utility.checkNull(String.valueOf(detailObj[4][6])));
				capApprBean.setCearPrice5(Utility.checkNull(String.valueOf(detailObj[4][7])));

				capApprBean.setQuantity6(Utility.checkNull(String.valueOf(detailObj[5][0])));
				capApprBean.setDescription6(Utility.checkNull(String.valueOf(detailObj[5][1])));
				capApprBean.setVendorName6(Utility.checkNull(String.valueOf(detailObj[5][2])));
				capApprBean.setUnitPrice6(Utility.checkNull(String.valueOf(detailObj[5][3])));
				capApprBean.setTotalCost6(Utility.checkNull(String.valueOf(detailObj[5][4])));
				capApprBean.setTagNumber6(Utility.checkNull(String.valueOf(detailObj[5][5])));
				capApprBean.setSerialNumber6(Utility.checkNull(String.valueOf(detailObj[5][6])));
				capApprBean.setCearPrice6(Utility.checkNull(String.valueOf(detailObj[5][7])));

				capApprBean.setQuantity7(Utility.checkNull(String.valueOf(detailObj[6][0])));
				capApprBean.setDescription7(Utility.checkNull(String.valueOf(detailObj[6][1])));
				capApprBean.setVendorName7(Utility.checkNull(String.valueOf(detailObj[6][2])));
				capApprBean.setUnitPrice7(Utility.checkNull(String.valueOf(detailObj[6][3])));
				capApprBean.setTotalCost7(Utility.checkNull(String.valueOf(detailObj[6][4])));
				capApprBean.setTagNumber7(Utility.checkNull(String.valueOf(detailObj[6][5])));
				capApprBean.setSerialNumber7(Utility.checkNull(String.valueOf(detailObj[6][6])));
				capApprBean.setCearPrice7(Utility.checkNull(String.valueOf(detailObj[6][7])));

				capApprBean.setQuantity8(Utility.checkNull(String.valueOf(detailObj[7][0])));
				capApprBean.setDescription8(Utility.checkNull(String.valueOf(detailObj[7][1])));
				capApprBean.setVendorName8(Utility.checkNull(String.valueOf(detailObj[7][2])));
				capApprBean.setUnitPrice8(Utility.checkNull(String.valueOf(detailObj[7][3])));
				capApprBean.setTotalCost8(Utility.checkNull(String.valueOf(detailObj[7][4])));
				capApprBean.setTagNumber8(Utility.checkNull(String.valueOf(detailObj[7][5])));
				capApprBean.setSerialNumber8(Utility.checkNull(String.valueOf(detailObj[7][6])));
				capApprBean.setCearPrice8(Utility.checkNull(String.valueOf(detailObj[7][7])));

				capApprBean.setQuantity9(Utility.checkNull(String.valueOf(detailObj[8][0])));
				capApprBean.setDescription9(Utility.checkNull(String.valueOf(detailObj[8][1])));
				capApprBean.setVendorName9(Utility.checkNull(String.valueOf(detailObj[8][2])));
				capApprBean.setUnitPrice9(Utility.checkNull(String.valueOf(detailObj[8][3])));
				capApprBean.setTotalCost9(Utility.checkNull(String.valueOf(detailObj[8][4])));
				capApprBean.setTagNumber9(Utility.checkNull(String.valueOf(detailObj[8][5])));
				capApprBean.setSerialNumber9(Utility.checkNull(String.valueOf(detailObj[8][6])));
				capApprBean.setCearPrice9(Utility.checkNull(String.valueOf(detailObj[8][7])));

				capApprBean.setQuantity10(Utility.checkNull(String.valueOf(detailObj[9][0])));
				capApprBean.setDescription10(Utility.checkNull(String.valueOf(detailObj[9][1])));
				capApprBean.setVendorName10(Utility.checkNull(String.valueOf(detailObj[9][2])));
				capApprBean.setUnitPrice10(Utility.checkNull(String.valueOf(detailObj[9][3])));
				capApprBean.setTotalCost10(Utility.checkNull(String.valueOf(detailObj[9][4])));
				capApprBean.setTagNumber10(Utility.checkNull(String.valueOf(detailObj[9][5])));
				capApprBean.setSerialNumber10(Utility.checkNull(String.valueOf(detailObj[9][6])));
				capApprBean.setCearPrice10(Utility.checkNull(String.valueOf(detailObj[9][7])));
			}
			// Detail-Table entry Begins

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to check double.
	 * @param result : result
	 * @return String
	 */
	public String checkDouble(final String result) {
		if (result == null || "".equals(result) || "0.0".equals(result) || "null".equals(result)) {
			return "0.0";
		} else {
			return result;
		}
	}

	/**Used to check whether finance person availabel or not.
	 * @return boolean
	 */
	public boolean isFinancePersonAvailable() {
		final Object[][] financeApproverObj = this.getSqlModel().getSingleResult(" SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'S'");
		if (financeApproverObj != null && financeApproverObj.length > 0) {
			return true;
		}
		return false;
	}

	/**Check whether current user is belongs to finance department or not.
	 * @param userId : current login user id
	 * @param capApprBean : CapitalExpenditureApproval
	 * @return boolean
	 */
	public boolean isFinanceDeptPerson(final String userId, final CapitalExpenditureApproval capApprBean) {
		final Object[][] financeApproverObj = this.getSqlModel().getSingleResult("SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'S' AND APP_SETTINGS_EMP_ID = " + userId);
		if (financeApproverObj != null && financeApproverObj.length > 0) {
			return true;
		}
		return false;
	}

	/**Used to update approver and application status.
	 * @param capApprBean : 
	 * @param status : 
	 * @param applicationId : 
	 * @return boolean
	 */
	public boolean updateApproverAndStatus(final CapitalExpenditureApproval capApprBean, final String status, final String applicationId) {
		final String approverID = capApprBean.getUserEmpId();
		final String approverComments = capApprBean.getApproverComments();
		boolean result = false;
		try {
			final String changeStatusQuery = " UPDATE HRMS_D1_CAPITALEXP SET EXP_APPLICATION_STATUS = '" + status + "'" +
									   ",EXP_APPROVER_CODE = " + capApprBean.getForwardedApproverID() + 
									   ",EXP_APPROVER_TYPE = '"	+ capApprBean.getForwardedApproverType() + "'" + 
									   " WHERE EXP_CODE = " + applicationId;
			result = this.getSqlModel().singleExecute(changeStatusQuery);
			if (result) {
				this.insertApproverComments(applicationId, approverID, approverComments, status);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * a> If any approver reject application then only status is updated.
	 * b> If any approver sent-back application then update status to 'B ' and approver type to ''
	 * c> If any approver authorized application then update status to 'S' and 
	 * approver type to '' and approver code to first finance person's ID
	 * 
	 * @param capApprBean : capApprBean
	 * @param status : 
	 * @param applicationId : 
	 * @param poNumber : 
	 * @param poAttachment : 
	 * @return boolean
	 */
	public boolean UpdateCapitalExpTable(final CapitalExpenditureApproval capApprBean, final String status,
			final String applicationId, final String poNumber, final String poAttachment) {
		final String approverID = capApprBean.getUserEmpId();
		final String approverComments = capApprBean.getApproverComments();
		final Object[][] financeApproverObj = this.getSqlModel().getSingleResult(" SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'S' AND APP_SETTINGS_EMP_ID IS NOT NULL");
		boolean result = false;
		try {
			String changeStatusQuery = "UPDATE HRMS_D1_CAPITALEXP SET EXP_APPLICATION_STATUS = '" + status + "'";
			if ("B".equals(status)) {
				changeStatusQuery += " , EXP_APPROVER_TYPE =' '";
			} else if ("S".equals(status)) {
				if (financeApproverObj != null && financeApproverObj.length > 0) {
					changeStatusQuery += " ,EXP_APPROVER_CODE = " + (Utility.checkNull(String.valueOf(financeApproverObj[0][0])));
					changeStatusQuery += " ,EXP_APPROVER_TYPE  =' '";
				} else {
					changeStatusQuery += " ,EXP_APPROVER_TYPE =' '";
				}
			}

			changeStatusQuery += " , PO_NUMBER='" + poNumber + 
								"', PO_ATTACHMENT='" + poAttachment	+ 
								"' WHERE EXP_CODE = " + applicationId;
			result = this.getSqlModel().singleExecute(changeStatusQuery);
			if (result) {
				this.insertApproverComments(applicationId, approverID, approverComments, status);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Inert approvers comments into HRMS_D1_CAPITALEXP_PATH.
	 * @param applicationId : applicationId
	 * @param approverID : approverID
	 * @param approverComments : approverCommets
	 * @param statusToUpdate : statusToUpdate
	 */
	private void insertApproverComments(final String applicationId, final String approverID, final String approverComments, final String statusToUpdate) {
		try {
			final String insertSql = " INSERT INTO HRMS_D1_CAPITALEXP_PATH (EXP_PATH_ID, EXP_CODE, EXP_APPROVER_CODE, EXP_COMMENTS, EXP_STATUS) " + 
					" VALUES ((SELECT NVL(MAX(EXP_PATH_ID), 0) + 1 FROM HRMS_D1_CAPITALEXP_PATH), ?, ?, ?, ?) ";
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

	/**Used to update data.
	 * @param capApprBean : CapitalExpenditureApproval
	 * @param status : status
	 * @param applicationId : applicationId
	 * @param request : request
	 * @param poNumber : poNumber
	 * @param poAttachment : poAttachment
	 * @return boolean
	 */
	public boolean updateDetailsTable(final CapitalExpenditureApproval capApprBean,
			final String status, final String applicationId, final HttpServletRequest request,
			final String poNumber, final String poAttachment) {
		boolean updateCapitalExpResult = false;
		boolean updateDetailListResult = false;
		boolean finalResult = false;
		final String approverID = capApprBean.getUserEmpId();
		final String approverComments = capApprBean.getApproverComments();
		try {
			// Delete records from HRMS_D1_CAPITALEXP_DESC and then insert into
			// HRMS_D1_CAPITALEXP_DESC and update HRMS_ACCIDENT_HDR
			this.getSqlModel().singleExecute("DELETE FROM HRMS_D1_CAPITALEXP_DESC WHERE EXP_CODE=" + applicationId);
			Object[][] updateCapitalHeader = null;

			// Inserting records into HRMS_ACCIDENT_HDR Begins
			updateCapitalHeader = new Object[1][13];
			updateCapitalHeader[0][0] = capApprBean.getInvoicePoNumber();
			updateCapitalHeader[0][1] = capApprBean.getAssetSubTotal();
			updateCapitalHeader[0][2] = capApprBean.getInvoiceVendorNumber();
			updateCapitalHeader[0][3] = capApprBean.getActualInstallation();
			updateCapitalHeader[0][4] = capApprBean.getInvoiceTotal();
			updateCapitalHeader[0][5] = capApprBean.getActualMaterial();
			updateCapitalHeader[0][6] = capApprBean.getActualFreight();
			updateCapitalHeader[0][7] = capApprBean.getDateTagged();
			updateCapitalHeader[0][8] = capApprBean.getActualTax();
			updateCapitalHeader[0][9] = capApprBean.getActualTotal();
			updateCapitalHeader[0][10] = capApprBean.getStatus();
			updateCapitalHeader[0][11] = capApprBean.getPpoNumber();
			updateCapitalHeader[0][12] = capApprBean.getPpoAttachement();

			final String updateCapitalHeaderQuery = "UPDATE HRMS_D1_CAPITALEXP SET EXP_PO_NO=?, EXP_ASSET_SUB_TOTAL=?, " + 
					" EXP_VENDOR=?, EXP_ACT_INSTALL_COST=?, " + 
					" EXP_INVOICE_TOTAL=?, EXP_ACT_MATERIAL_COST=?, EXP_ACT_FREIGHT=?, " + 
					" EXP_DATE_TAGGED=TO_DATE(?,'DD-MM-YYYY'), EXP_ACT_TAX=?, " + 
					" EXP_ACT_TOTAL=?, EXP_APPLICATION_STATUS=?,PO_NUMBER=?, PO_ATTACHMENT=? WHERE EXP_CODE="	+ applicationId;

			updateCapitalExpResult = this.getSqlModel().singleExecute(
					updateCapitalHeaderQuery, updateCapitalHeader);

			if (updateCapitalExpResult) {
				// Inserting Records into HRMS_D1_CAPITALEXP_DESC Begins
				Object[][] addDetail = null;
				int k = 0;

				addDetail = new Object[10][10];
				for (int i = 1; i <= 10; i++) {
					addDetail[k][0] = capApprBean.getCapitalExpID();
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
						" VALUES(?,?,?,?,?,?,?,?,?,?)";
				updateDetailListResult = this.getSqlModel().singleExecute(
						detailQuery, addDetail);
				// Inserting Records into HRMS_D1_CAPITALEXP_DESC Ends

			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in model draftFunction() : " + e);
			e.printStackTrace();
		}

		if (updateCapitalExpResult & updateDetailListResult) {
			this.insertApproverComments(applicationId, approverID, approverComments, status);
			finalResult = true;
		}
		return finalResult;
	}

	/**Used to get approver comment list.
	 * @param bean : CapitalExpenditureApproval
	 * @param applicationID : applicationID
	 */
	public void getApproverCommentList(final CapitalExpenditureApproval bean, final String applicationID) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, EXP_COMMENTS, " + 
				" TO_CHAR(EXP_APPR_DATE, 'DD-MM-YYYY') AS REQ_APPROVED_DATE, " + 
				" DECODE(EXP_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'X', 'Cancellation Rejected', 'S','Authorized Sign Off') AS STATUS " + 
				" FROM HRMS_D1_CAPITALEXP_PATH " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CAPITALEXP_PATH.EXP_APPROVER_CODE) " + 
				" WHERE EXP_CODE = " + applicationID + 
				" ORDER BY EXP_PATH_ID DESC";

		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<CapitalExpenditureApproval> approverList = new ArrayList<CapitalExpenditureApproval>();
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final CapitalExpenditureApproval innerBean = new CapitalExpenditureApproval();
				innerBean.setApprName(Utility.checkNull(String.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(Utility.checkNull(String.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(Utility.checkNull(String.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(Utility.checkNull(String.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			bean.setApproverCommentList(approverList);
		}
	}
	
	/**Used to return required query.
	 * @param status : Application status
	 * @param approverId : Approver id
	 * @return String
	 */
	public String returnRequiredQuery(final String status, final String approverId) {
		final String queryToReturn = " SELECT HRMS_D1_CAPITALEXP.EXP_TRACKING_NUMBER, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, TO_CHAR(HRMS_D1_CAPITALEXP.EXP_APPLICATION_DATE,'DD-MM-YYYY'), " + 
									 " HRMS_D1_CAPITALEXP.EXP_CODE, HRMS_D1_CAPITALEXP.EXP_APPLICATION_STATUS FROM HRMS_D1_CAPITALEXP " + 
									 " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CAPITALEXP.EXP_EMP_ID) " + 
									 " WHERE HRMS_D1_CAPITALEXP.EXP_APPLICATION_STATUS IN (" + status + ") AND HRMS_D1_CAPITALEXP.EXP_APPROVER_CODE = " + approverId +   
									 " ORDER BY HRMS_D1_CAPITALEXP.EXP_TRACKING_NUMBER DESC; ";
		return queryToReturn;
	}
}
