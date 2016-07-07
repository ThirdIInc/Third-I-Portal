package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.CBTApprovalBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1380
 *
 */
public class CBTApprovalModel  extends ModelBase {
	/** logger. * */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CBTApprovalModel.class);
	
	/**	strZero. *	 */
	private final String  strZero = "0";
	
	/**	strOne. *	 */
	private final String  strOne = "1";
	
	/**	strTwenty. *	 */
	private final String  strTwenty = "20";
	
	/**	strUs. *	 */
	private final String  strUs = "us";
	
	
	/**	strCanada. *	 */
	private final String  strCanada = "canada";
	
	
	/**	totalPendingPage. *	 */
	private final String  totalPendingPage = "totalPendingPage";
	
	/**	pendingPageNo. *	 */
	private final String  pendingPageNo = "pendingPageNo";
	
	/**	totalApprovedPage. *	 */
	private final String  totalApprovedPage = "totalApprovedPage";
	
	/**	approvedPageNo. *	 */
	private final String  approvedPageNo = "approvedPageNo";
	
	/**	statusP. *	 */
	private final String  statusP = "_P";
	
	/**	status. *	 */
	private final String  statusF = "_F";
	
	/**
	 * Method : isTrainingAuthority.
	 * Purpose : For checking current login user is training authority person OR not.
	 * @param userId : Current User ID
	 * @return : boolean
	 */
	public boolean isTrainingAuthority(final String userId) {
		final String query = " SELECT APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'T' AND APP_SETTINGS_EMP_ID = " + userId;
		final Object[][] queryObj = this.getSqlModel().getSingleResult(query);
		if (queryObj != null && queryObj.length > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method : isTrainingAuthorityAvailable.
	 * Purpose : For checking whether training authority person is available OR not.
	 * @return : boolean
	 */
	public boolean isTrainingAuthorityAvailable() {
		final String query = " SELECT APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'T'";
		final Object[][] queryObj = this.getSqlModel().getSingleResult(query);
		if (queryObj != null && queryObj.length > 0) {
			return true;
		}
		return false;
	}
	
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
	 * Method : getPendingList.
	 * Purpose : For getting pending list.
	 * @param cbtApprBean : CBTApprovalBean
	 * @param request : HttpServletRequest
	 * @param currentUserID : CurrentUser ID
	 */
	public void getPendingList(final CBTApprovalBean cbtApprBean, final HttpServletRequest request, final String currentUserID) {
		try {
			Object[][] pendingListData = null;
			final List<CBTApprovalBean> pendingDataList = new ArrayList<CBTApprovalBean>();
			
			// Pending application Begins
			/*String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
							 +" TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
							 + " FROM HRMS_D1_CBT_REQUEST  "
							 + " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
							 + " WHERE CBT_STATUS = 'P' AND CBT_APPROVER_ID = "+currentUserID
							 +" ORDER BY CBT_COMPLETED_ON DESC ";
			pendingListData = getSqlModel().getSingleResult(selQuery);*/
			
			pendingListData = this.getAppropriateListFromQuery("'P'");
			final String[] pageIndex = Utility.doPaging(cbtApprBean.getMyPage(), pendingListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = this.strZero;
				pageIndex[1] = this.strTwenty;
				pageIndex[2] = this.strOne;
				pageIndex[3] = this.strOne;
				pageIndex[4] = "";
			}
			
			request.setAttribute(this.totalPendingPage, Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute(this.pendingPageNo, Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.strOne)) {
				cbtApprBean.setMyPage(this.strOne);
			}
			
			if (pendingListData != null && pendingListData.length > 0) {
				cbtApprBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CBTApprovalBean beanItt = new CBTApprovalBean();
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setEmpNameIterator(this.checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(pendingListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(pendingListData[i][4])));
					pendingDataList.add(beanItt);
				}
				cbtApprBean.setPendingIteratorList(pendingDataList);
			}
			// Pending  application Ends
		} catch (final Exception e) {
			this.logger.error("Exception occured in getPending list : " + e);
		}
	}

	/**
	 * Method : getForwardedPendingList.
	 * Purpose : For getting forwarded pending list.
	 * @param cbtApprBean : CBTApprovalBean
	 * @param request : HttpServletRequest
	 * @param currentUserID : CurrentUser ID
	 */
	public void getForwardedPendingList(final CBTApprovalBean cbtApprBean, final HttpServletRequest request, final String currentUserID) {
		try {
			Object[][] pendingListData = null;
			final List<CBTApprovalBean> pendingDataList = new ArrayList<CBTApprovalBean>();
			
			final String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS " + 
							  " FROM HRMS_D1_CBT_REQUEST LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) " + 
							 " WHERE (CBT_STATUS = 'P' AND CBT_APPROVER_ID = " + currentUserID + " ) OR (CBT_STATUS = 'F') ORDER BY CBT_COMPLETED_ON DESC ";
							
			pendingListData = this.getSqlModel().getSingleResult(selQuery);
				
			final String[] pageIndex = Utility.doPaging(cbtApprBean.getMyPage(), pendingListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = this.strZero;
				pageIndex[1] = this.strTwenty;
				pageIndex[2] = this.strOne;
				pageIndex[3] = this.strOne;
				pageIndex[4] = "";
			}
			
			request.setAttribute(this.totalPendingPage, Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute(this.pendingPageNo, Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.strOne)) {
				cbtApprBean.setMyPage(this.strOne);
			}
			
			if (pendingListData != null && pendingListData.length > 0) {
				cbtApprBean.setPendingListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CBTApprovalBean beanItt = new CBTApprovalBean();
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setEmpNameIterator(this.checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(pendingListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(pendingListData[i][4])));
					pendingDataList.add(beanItt);
				}
				cbtApprBean.setPendingIteratorList(pendingDataList);
			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in getForwardedPendingList list : " + e);
		}
	}

	/**
	 * Method : getApprovedListInTrainingAuthority.
	 * Purpose : Getting approved list for Training Authority. 
	 * @param cbtApprBean : CBTApprovalBean
	 * @param request : HttpServletRequest
	 */
	public void getApprovedListInTrainingAuthority(final CBTApprovalBean cbtApprBean, final HttpServletRequest request) {
		try {
			Object[][] approvedListData = null;
			final List<CBTApprovalBean> approvedDataList = new ArrayList<CBTApprovalBean>();
			
			/*String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
							 + " FROM HRMS_D1_CBT_REQUEST  "
							 + " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
							 + " WHERE CBT_STATUS IN ('A') ORDER BY CBT_COMPLETED_ON DESC ";
							
			approvedListData = getSqlModel().getSingleResult(selQuery);*/
			approvedListData = this.getAppropriateListFromQuery("'A'");	
			final String[] approvedPageIndex = Utility.doPaging(cbtApprBean.getMyPageApproved(), approvedListData.length, 20);
			if (approvedPageIndex == null) {
				approvedPageIndex[0] = this.strZero;
				approvedPageIndex[1] = this.strTwenty;
				approvedPageIndex[2] = this.strOne;
				approvedPageIndex[3] = this.strOne;
				approvedPageIndex[4] = "";
			}
			
			request.setAttribute(this.totalApprovedPage, Integer.parseInt(String.valueOf(approvedPageIndex[2])));
			request.setAttribute(this.approvedPageNo, Integer.parseInt(String.valueOf(approvedPageIndex[3])));
			if (approvedPageIndex[4].equals(this.strOne)) { 
				cbtApprBean.setMyPageApproved(this.strOne);
			}
			if (approvedListData != null && approvedListData.length > 0) {
				cbtApprBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(approvedPageIndex[0]); i < Integer.parseInt(approvedPageIndex[1]); i++) {
					final CBTApprovalBean beanItt = new CBTApprovalBean();
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setEmpNameIterator(this.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(approvedListData[i][4])));
					approvedDataList.add(beanItt);
				}
				cbtApprBean.setApproveredIteratorList(approvedDataList);
			}
		} catch (final Exception e) {
			this.logger.error(" Exception occured in getApprovedList list : " + e);
		}
	}
	
	
	/**
	 * Method : getApprovedList.
	 * Purpose : Getting approved list. 
	 * @param cbtApprBean : CBTApprovalBean
	 * @param request : HttpServletRequest
	 */
	public void getApprovedList(final CBTApprovalBean cbtApprBean, final HttpServletRequest request) {
		try {
			Object[][] approvedListData = null;
			final List<CBTApprovalBean> approvedDataList = new ArrayList<CBTApprovalBean>();
			
			/*String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
							 +" TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
							 + " FROM HRMS_D1_CBT_REQUEST  "
							 + " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
							 + " WHERE CBT_STATUS IN ('A','F') AND CBT_APPROVER_ID = "+cbtApprBean.getUserEmpId()
							 +"ORDER BY CBT_COMPLETED_ON DESC ";
							
			approvedListData = getSqlModel().getSingleResult(selQuery);*/
				
			approvedListData = this.getAppropriateListFromQuery("'A','F'");
			final String[] approvedPageIndex = Utility.doPaging(cbtApprBean.getMyPageApproved(), approvedListData.length, 20);
			if (approvedPageIndex == null) {
				approvedPageIndex[0] = this.strZero;
				approvedPageIndex[1] = this.strTwenty;
				approvedPageIndex[2] = this.strOne;
				approvedPageIndex[3] = this.strOne;
				approvedPageIndex[4] = "";
			}
			
			request.setAttribute(this.totalApprovedPage, Integer.parseInt(String.valueOf(approvedPageIndex[2])));
			request.setAttribute(this.approvedPageNo, Integer.parseInt(String.valueOf(approvedPageIndex[3])));
			if (approvedPageIndex[4].equals(this.strOne)) { 
				cbtApprBean.setMyPageApproved(this.strOne);
			}
			
			if (approvedListData != null && approvedListData.length > 0) {
				cbtApprBean.setApprovedListLength(true);
				for (int i = Integer.parseInt(approvedPageIndex[0]); i < Integer.parseInt(approvedPageIndex[1]); i++) {
					final CBTApprovalBean beanItt = new CBTApprovalBean();
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setEmpNameIterator(this.checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(approvedListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(approvedListData[i][4])));
					approvedDataList.add(beanItt);
				}
				cbtApprBean.setApproveredIteratorList(approvedDataList);
			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in getApprovedList list : " + e);
		}
	}

	/**
	 * Method : getRejectedList.
	 * Purpose : Getting approved list. 
	 * @param cbtApprBean : CBTApprovalBean
	 * @param request : HttpServletRequest
	 */
	public void getRejectedList(final CBTApprovalBean cbtApprBean, final HttpServletRequest request) {
		try {
			Object[][] rejectedListData = null;
			final List<CBTApprovalBean> rejectedDataList = new ArrayList<CBTApprovalBean>();
			
			/*String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, "
							 +" TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS "
							 + " FROM HRMS_D1_CBT_REQUEST  "
							 + " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) "
							 + " WHERE CBT_STATUS = 'R' ORDER BY CBT_COMPLETED_ON DESC ";
							
			rejectedListData = getSqlModel().getSingleResult(selQuery);*/
			rejectedListData = this.getAppropriateListFromQuery("'R'");	
			final String[] rejectedPageIndex = Utility.doPaging(cbtApprBean.getMyPageRejected(), rejectedListData.length, 20);
			if (rejectedPageIndex == null) {
				rejectedPageIndex[0] = this.strZero;
				rejectedPageIndex[1] = this.strTwenty;
				rejectedPageIndex[2] = this.strOne;
				rejectedPageIndex[3] = this.strOne;
				rejectedPageIndex[4] = "";
			}
			
			request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(rejectedPageIndex[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(rejectedPageIndex[3])));
			if (rejectedPageIndex[4].equals(this.strOne)) {
				cbtApprBean.setMyPageRejected(this.strOne);
			}
				
			if (rejectedListData != null && rejectedListData.length > 0) {
				cbtApprBean.setRejectedListLength(true);
				for (int i = Integer.parseInt(rejectedPageIndex[0]); i < Integer.parseInt(rejectedPageIndex[1]); i++) {
					final CBTApprovalBean beanItt = new CBTApprovalBean();
					beanItt.setTrackingNumberIterator(this.checkNull(String.valueOf(rejectedListData[i][0])));
					beanItt.setEmpNameIterator(this.checkNull(String.valueOf(rejectedListData[i][1])));
					beanItt.setApplicationDateIterator(this.checkNull(String.valueOf(rejectedListData[i][2])));
					beanItt.setCbtHiddenID(this.checkNull(String.valueOf(rejectedListData[i][3])));
					beanItt.setHiddenStatus(this.checkNull(String.valueOf(rejectedListData[i][4])));
					rejectedDataList.add(beanItt);
				}
				cbtApprBean.setRejectedIteratorList(rejectedDataList);
			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in getRejectedList list : " + e);
		}
	}

	/**
	 * Method : viewApplication.
	 * Purpose : Getting approved list. 
	 * @param cbtApprBean : CBTApprovalBean
	 * @param cbtHiddenID : Application ID
	 */
	public void viewApplication(final CBTApprovalBean cbtApprBean, final String cbtHiddenID) {
		try {
			final String query = " SELECT CBT_ID, CBT_EMP_ID, EMPLOYEE.EMP_TOKEN, EMPLOYEE.EMP_FNAME||' '||EMPLOYEE.EMP_MNAME||' '||EMPLOYEE.EMP_LNAME, " + 
				" CBT_DEPT_NAME, CBT_DEPT_NUMBER, CBT_COURSE_NUMBER, CBT_COURSE_DESC, CBT_LOCATION, CBT_ADDRESS, CBT_CITY, CBT_STATE, "  + 
				" CBT_PROVIDENCE, CBT_COUNTRY, CBT_ZIP, CBT_PHONE, CBT_FAX, CBT_EMAIL, "  + 
				" CBT_APPROVER_ID, APPROVER.EMP_TOKEN, APPROVER.EMP_FNAME||' '||APPROVER.EMP_MNAME||' '||APPROVER.EMP_LNAME," + 
				" '_'||CBT_STATUS, CBT_COMPLETED_BY, INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME, " + 
				" TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY HH24:MI'), CBT_TRACKING_NUM "  + 
				" FROM HRMS_D1_CBT_REQUEST " + 
				" LEFT JOIN HRMS_EMP_OFFC EMPLOYEE ON (EMPLOYEE.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) " + 
				//+" LEFT JOIN HRMS_D1_CLASS_REQUEST ON (HRMS_D1_CLASS_REQUEST.CLASS_REQUEST_ID = HRMS_D1_CBT_REQUEST.CBT_COURSE_ID) "
				" LEFT JOIN HRMS_EMP_OFFC APPROVER ON (APPROVER.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_APPROVER_ID) " + 
				" LEFT JOIN HRMS_EMP_OFFC INITIATOR ON (INITIATOR.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_COMPLETED_BY) " + 
				" WHERE CBT_ID = " + cbtHiddenID;

			final Object[][] data = this.getSqlModel().getSingleResult(query);

			cbtApprBean.setCbtID(this.checkNull(String.valueOf(data[0][0])));
			cbtApprBean.setEmployeeID(this.checkNull(String.valueOf(data[0][1])));
			cbtApprBean.setEmployeeToken(this.checkNull(String.valueOf(data[0][2])));
			cbtApprBean.setEmployeeName(this.checkNull(String.valueOf(data[0][3])));
			cbtApprBean.setDeptName(this.checkNull(String.valueOf(data[0][4])));
			cbtApprBean.setDeptNo(this.checkNull(String.valueOf(data[0][5])));
			cbtApprBean.setCourseNo(this.checkNull(String.valueOf(data[0][6])));
			cbtApprBean.setCourseDesc(this.checkNull(String.valueOf(data[0][7])));
			if ("U".equals(this.checkNull(String.valueOf(data[0][8])))) {
				cbtApprBean.setLocationName(this.strUs);
				cbtApprBean.setLocationRadioValue(this.strUs);
			} else {
				cbtApprBean.setLocationName(this.strCanada);
				cbtApprBean.setLocationRadioValue(this.strCanada);
			}
			cbtApprBean.setAddress(this.checkNull(String.valueOf(data[0][9])));
			cbtApprBean.setCity(this.checkNull(String.valueOf(data[0][10])));
			cbtApprBean.setState(this.checkNull(String.valueOf(data[0][11])));
			cbtApprBean.setProvidence(this.checkNull(String.valueOf(data[0][12])));
			cbtApprBean.setCountry(this.checkNull(String.valueOf(data[0][13])));
			cbtApprBean.setZip(this.checkNull(String.valueOf(data[0][14])));
			cbtApprBean.setPhNo(this.checkNull(String.valueOf(data[0][15])));
			cbtApprBean.setFaxNo(this.checkNull(String.valueOf(data[0][16])));
			cbtApprBean.setEmailAddress(this.checkNull(String.valueOf(data[0][17])));
			cbtApprBean.setApprovedID(this.checkNull(String.valueOf(data[0][18])));
			
			cbtApprBean.setStatus(this.checkNull(String.valueOf(data[0][21])));
			cbtApprBean.setCompletedByID(this.checkNull(String.valueOf(data[0][22])));
			cbtApprBean.setCompletedByName(this.checkNull(String.valueOf(data[0][23])));
			cbtApprBean.setCompletedOn(this.checkNull(String.valueOf(data[0][24])));
			cbtApprBean.setTrackingNumber(this.checkNull(String.valueOf(data[0][25])));

			cbtApprBean.setApplnStatus(cbtApprBean.getStatus());
			this.getApproverCommentList(cbtApprBean, cbtHiddenID);
		} catch (final Exception e) {
			this.logger.error("Exception occured in view() : " + e);
		}
	}

	/**
	 * Method : updateStatus.
	 * Purpose : Updating Status. 
	 * @param cbtApprBean : CBTApprovalBean
	 * @param applicationID : Application ID
	 * @param status : Status
	 * @param approverID : Approver ID
	 * @param approverComments : Approver Comments
	 * @return boolean
	 */
	public boolean updateStatus(final CBTApprovalBean cbtApprBean, final String status, final String applicationID, final String approverID, final String approverComments) {
		boolean result = false;
		
		try {
			final String query = "UPDATE HRMS_D1_CBT_REQUEST SET CBT_STATUS= '" + status + "' WHERE  CBT_ID=" + applicationID;
			result = this.getSqlModel().singleExecute(query);
			if (result) {
				this.insertApproverComments(applicationID, approverID, approverComments, status);
			}
		} catch (final Exception e) {
			this.logger.error("Exception  occurred in updateStatus : " + e);
		}
		return result;
	}
	
	/**
	 * Method : updateStatus.
	 * Purpose : Updating Status. 
	 * @param cbtApprBean : CBTApprovalBean
	 * @param applicationID : Application ID
	 * @param status : Status
	 * @param approverID : Approver ID
	 * @param approverComments : Approver Comments
	 * @return boolean
	 */
	public boolean updateRejectStatus(final CBTApprovalBean cbtApprBean, final String status, final String applicationID, final String approverID, final String approverComments) {
		boolean result = false;
		String statusToUpdate = "";
		if (this.statusP.equals(status) || this.statusF.equals(status)) {
			statusToUpdate = "R";
		}
		 
		try {
			final String query = " UPDATE HRMS_D1_CBT_REQUEST SET CBT_STATUS='" + statusToUpdate + "'  WHERE CBT_ID=" + applicationID;
			result = this.getSqlModel().singleExecute(query);
			if (result) {
				this.insertApproverComments(applicationID, approverID, approverComments,	statusToUpdate);
			}
		} catch (final Exception e) {
			this.logger.error("Exception occurred in updateRejectStatus : " + e);
		}
		return result;
	}
	
	
	/**
	 * @param cbtApprBean : CBTApprovalBean
	 * @param status : Status
	 * @param applicationID : Application ID
	 * @param approverID : Approver ID
	 * @param approverComments : Approver Comments
	 * @return boolean
	 */
	public boolean updateSentBackStatus(final CBTApprovalBean cbtApprBean, final String status, final String applicationID, final String approverID, final String approverComments) {
		boolean result = false;
		String statusToUpdate = "";
		if (this.statusP.equals(status) || this.statusF.equals(status)) {
			statusToUpdate = "B";
		}
		 
		try {
			final String query = "UPDATE HRMS_D1_CBT_REQUEST SET CBT_STATUS='" + statusToUpdate + "' WHERE CBT_ID=" + applicationID;
			result = this.getSqlModel().singleExecute(query);
			if (result) {
				this.insertApproverComments(applicationID, approverID, approverComments,	statusToUpdate);
			}
		} catch (final Exception e) {
			this.logger.error("Exception occurred in updateStatus : " + e);
		}
		return result;
	}
	
	/**
	 * Method : insertApproverComments.
	 * Purpose : For saving approver comments into table
	 * @param applicationId : Application ID
	 * @param approverID : Approver ID
	 * @param approverComments : Approver Comments
	 * @param statusToUpdate : Status To Update
	 */
	private void insertApproverComments(final String applicationId, final String approverID, final String approverComments, final String statusToUpdate) {
		try {  
			final String insertSql = " INSERT INTO HRMS_D1_CBT_DATA_PATH (CBT_PATH_ID, CBT_ID, CBT_APPROVER, CBT_COMMENTS, CBT_STATUS) " + 
					 " VALUES ((SELECT NVL(MAX(CBT_PATH_ID), 0) + 1 FROM HRMS_D1_CBT_DATA_PATH), ?, ?, ?, ?) ";
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
	
	/**
	 * Method : insertApproverComments.
	 * Purpose : For saving approver comments into table
	 * @param bean : CBTApprovalBean
	 * @param applicationID : Application ID
	 */
	public void getApproverCommentList(final CBTApprovalBean bean, final String applicationID) {
		try {
			final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CBT_COMMENTS, " + 
					 " TO_CHAR(CBT_DATE, 'DD-MM-YYYY') AS APPROVED_DATE, " + 
					 " DECODE(CBT_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back', 'Z', 'Cancellation Rejected') AS STATUS " + 
					 " FROM HRMS_D1_CBT_DATA_PATH " + 
					 " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_CBT_DATA_PATH.CBT_APPROVER) WHERE CBT_ID = " + applicationID + " ORDER BY CBT_DATE DESC";
			final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
			final List<CBTApprovalBean> approverList = new ArrayList<CBTApprovalBean>(apprCommentListObj.length);
			if (apprCommentListObj != null && apprCommentListObj.length > 0) {
				for (int i = 0; i < apprCommentListObj.length; i++) {
					final CBTApprovalBean innerBean = new CBTApprovalBean();
					innerBean.setApprName(this.checkNull(String.valueOf(apprCommentListObj[i][0])));
					innerBean.setApprComments(this.checkNull(String.valueOf(apprCommentListObj[i][1])));
					innerBean.setApprDate(this.checkNull(String.valueOf(apprCommentListObj[i][2])));
					innerBean.setApprStatus(this.checkNull(String.valueOf(apprCommentListObj[i][3])));
					approverList.add(innerBean);
				}
				bean.setApproverCommentList(approverList);
			}
		} catch (final Exception e) {
			this.logger.error("Exception occured in getApproverCommentList : " + e);
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
		final String selQuery = " SELECT CBT_TRACKING_NUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TO_CHAR(CBT_COMPLETED_ON,'DD-MM-YYYY'), CBT_ID, CBT_STATUS " + 
							 " FROM HRMS_D1_CBT_REQUEST LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_CBT_REQUEST.CBT_EMP_ID) " + 
							 " WHERE CBT_STATUS IN (" + status + ") ORDER BY CBT_COMPLETED_ON DESC ";
		result = this.getSqlModel().getSingleResult(selQuery);
		return result;
	}
}
