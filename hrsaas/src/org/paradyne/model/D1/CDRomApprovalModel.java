package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.CDRomApprovalBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa1439/aa1381.
 *
 */
public class CDRomApprovalModel extends ModelBase {
	
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
	 * Set 1.
	 */
	private static final String STATUS = "B";
	
	/**
	 * Set Total Page Rejected.
	 */
	private static final String TOTAL_PAGE_REJECTED = "totalPageForRejectedApp";
	/**
	 * Set Page No Rejected.
	 */
	private static final String  PAGE_NO_REJECTED = "pageNoForRejectedApp";
	/**
	 * Set Total Page Rejected.
	 */
	private static final String  TOTAL_PAGE_APPROVED = "totalPageForApprovedApp";
	/**
	 * Set Page No Approved.
	 */
	private static final String  PAGE_NO_APPROVED = "pageNoForApprovedApp";
	/**
	 * Set Total Page PENDING CANCEL.
	 */
	private static final String  TOTAL_PAGE_PENDING_CANCEL = "totalPageForPendingCancelApp";
	/**
	 * Set Page No PENDING CANCEL.
	 */
	private static final String  PAGE_NO_PENDING_CANCEL = "pageNoForPendingCancelApp";
	
	
	
	/**
	 * Set Total Page PENDING CANCEL.
	 */
	private static final String  TOTAL_PAGE_PENDING = "totalPageForPendingApp";
	/**
	 * Set Page No PENDING CANCEL.
	 */
	private static final String  PAGE_NO_PENDING = "pageNoForPendingApp";
	
	
	
	/**
	 * Logger.
	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PersonalDataChangeApprovalModel.class);
	/**
	 * @param userId - Used  to get User emp Id.
	 * @return true/false.
	 */
	public boolean isUserAHRApprover(final String userId) {
		final String hrApproverSql = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'I' AND APP_SETTINGS_EMP_ID = " + userId;
		final Object[][] hrApproverObj = this.getSqlModel().getSingleResult(hrApproverSql);

		if (hrApproverObj != null && hrApproverObj.length > 0) { 
			return true; 
		}

		return false;
	}
	
	/**
	 * @param result - Used to check whether that data contains null value or not.
	 * @return String.
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}
	/**
	 * @param bean - Used to get User EmpId.
	 * @param pageForForwardedApp - paging field.
	 * @param request - Used to set attribute totalPageForPendingApp & pageNoForPendingApp.
	 * @return List.
	 */
	public List<CDRomApprovalBean> getForwardedApplicationList(final CDRomApprovalBean bean, final String pageForForwardedApp, final HttpServletRequest request) {

		List<CDRomApprovalBean> forwardedAppList = null;

		try {
			final String pendingAppSql = " SELECT CDROM_ID, CDROM_FILE_HEADER_NAME, " + " HRMS_EMP_OFFC.EMP_TOKEN|| ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(CDROM_APP_DATE, 'DD-MM-YYYY') " + " FROM HRMS_D1_CDROM_REQUEST CDROM " + " INNER JOIN HRMS_EMP_OFFC ON CDROM.CDROM_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE CDROM_APPROVER_STATUS = 'F' OR (CDROM_APPROVER_STATUS = 'P' AND CDROM_APPROVER_CODE = " + bean.getUserEmpId() + ") ORDER BY CDROM_ID DESC";
			final Object[][] pendingAppObj = this.getSqlModel().getSingleResult(pendingAppSql);

			if (pendingAppObj != null && pendingAppObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(pageForForwardedApp, pendingAppObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = CDRomApprovalModel.PAGE_ZERO;
					pageIndex[1] = CDRomApprovalModel.PAGE_TWENTY;
					pageIndex[2] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[3] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[4] = "";
				}

				request.setAttribute(CDRomApprovalModel.TOTAL_PAGE_PENDING, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(CDRomApprovalModel.PAGE_NO_PENDING, Integer.parseInt(String.valueOf(pageIndex[3])));

				forwardedAppList = new ArrayList<CDRomApprovalBean>(pendingAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CDRomApprovalBean bean1 = new CDRomApprovalBean();
					bean1.setCdRomID(String.valueOf(pendingAppObj[i][0]));
					bean1.setAuthorizedToken(this.checkNull(String.valueOf(pendingAppObj[i][1])));
					bean1.setEmpName(this.checkNull(String.valueOf(pendingAppObj[i][2])));
					bean1.setApplicationDate(this.checkNull(String.valueOf(pendingAppObj[i][3])));
					forwardedAppList.add(bean1);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return forwardedAppList;
	}

	/**
	 * @param pageForPendingCancelApp - Paging field.
	 * @param request - Used to set attributes totalPageForPendingCancelApp & pageNoForPendingCancelApp.
	 * @return List.
	 */
	public List<CDRomApprovalBean> getPendingCancellationApplicationList(final String pageForPendingCancelApp, final HttpServletRequest request) {
		List<CDRomApprovalBean> pendingCancelAppList = null;

		try {
			
			final String pendingCancelAppSql = " SELECT CDROM_ID, HRMS_EMP_OFFC.EMP_TOKEN, " + " EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME AS EMP_NAME, TO_CHAR(CDROM_APP_DATE, 'DD-MM-YYYY') " + " FROM HRMS_D1_CDROM_REQUEST CDROM " + " INNER JOIN HRMS_EMP_OFFC ON CDROM.CDROM_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE CDROM_APPROVER_STATUS = 'C' ORDER BY CDROM_ID ";
			final Object[][] pendingCancelAppObj = this.getSqlModel().getSingleResult(pendingCancelAppSql);

			if (pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(pageForPendingCancelApp, pendingCancelAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = CDRomApprovalModel.PAGE_ZERO;
					pageIndex[1] = CDRomApprovalModel.PAGE_TWENTY;
					pageIndex[2] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[3] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[4] = "";
				}

				request.setAttribute(CDRomApprovalModel.TOTAL_PAGE_PENDING_CANCEL, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(CDRomApprovalModel.PAGE_NO_PENDING_CANCEL, Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingCancelAppList = new ArrayList<CDRomApprovalBean>(pendingCancelAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CDRomApprovalBean bean = new CDRomApprovalBean();
					bean.setCdRomID(String.valueOf(pendingCancelAppObj[i][0]));
					bean.setEmpToken(this.checkNull(String.valueOf(pendingCancelAppObj[i][1])));
					bean.setEmpName(this.checkNull(String.valueOf(pendingCancelAppObj[i][2])));
					bean.setApplicationDate(this.checkNull(String.valueOf(pendingCancelAppObj[i][3])));
					pendingCancelAppList.add(bean);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return pendingCancelAppList;
	}
	
	
	/**
	 * @param userId - Used to get User ID. 
	 * @param pageForPendingApp - Paging field.
	 * @param request - Used to set totalPageForPendingApp &  pageNoForPendingApp attributes.
	 * @return List.
	 */
	public List<CDRomApprovalBean> getPendingApplicationList(final String userId, final String pageForPendingApp, final HttpServletRequest request) {

		List<CDRomApprovalBean> pendingAppList = null;

		try {
			
			final String pendingAppSql = " SELECT CDROM_ID,CDROM_FILE_HEADER_NAME," + " EMP_TOKEN,EMP_FNAME ||''|| EMP_MNAME ||''|| EMP_LNAME AS EMP_NAME, TO_CHAR(CDROM_APP_DATE, 'DD-MM-YYYY') " + " FROM HRMS_D1_CDROM_REQUEST CDROM  " + " INNER JOIN HRMS_EMP_OFFC ON CDROM.CDROM_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE CDROM_APPROVER_STATUS = 'P' AND CDROM_APPROVER_CODE= " + userId + " ORDER BY CDROM_ID ";
			
			final Object[][] pendingAppObj = this.getSqlModel().getSingleResult(pendingAppSql);

			if (pendingAppObj != null && pendingAppObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(pageForPendingApp, pendingAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = CDRomApprovalModel.PAGE_ZERO;
					pageIndex[1] = CDRomApprovalModel.PAGE_TWENTY;
					pageIndex[2] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[3] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[4] = "";
				}

				request.setAttribute(CDRomApprovalModel.TOTAL_PAGE_PENDING, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(CDRomApprovalModel.PAGE_NO_PENDING, Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingAppList = new ArrayList<CDRomApprovalBean>(pendingAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CDRomApprovalBean bean = new CDRomApprovalBean();
					bean.setCdRomID(String.valueOf(pendingAppObj[i][0]));
					bean.setAuthorizedToken(this.checkNull(String.valueOf(pendingAppObj[i][1])));
					bean.setEmpToken(this.checkNull(String.valueOf(pendingAppObj[i][2])));
					bean.setEmpName(this.checkNull(String.valueOf(pendingAppObj[i][3])));
					bean.setApplicationDate(this.checkNull(String.valueOf(pendingAppObj[i][4])));
					pendingAppList.add(bean);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return pendingAppList;
	}
	
	/**
	 * @param userId - Used to get User ID. 
	 * @param pageForPendingCancelApp - Paging field.
	 * @param request - Used to set totalPageForPendingCancelApp &  pageNoForPendingCancelApp attributes.
	 * @return List.
	 */
	public List<CDRomApprovalBean> getPendingCancellationApplicationList(final String userId, final String pageForPendingCancelApp, final HttpServletRequest request) {
		List<CDRomApprovalBean> pendingCancelAppList = null;

		try {
			
			final String pendingCancelAppSql = " SELECT CDROM_ID,CDROM_FILE_HEADER_NAME,EMP_TOKEN ,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_MNAME " + " TO_CHAR(CDROM_APP_DATE, 'DD-MM-YYYY') " + " FROM HRMS_D1_CDROM_REQUEST CDROM  " + " INNER JOIN HRMS_EMP_OFFC ON CDROM.CDROM_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE CDROM_APPROVER_STATUS = 'C' AND CDROM_APPROVER_CODE= " + userId + " ORDER BY CDROM_APP_DATE ";
			
			final Object[][] pendingCancelAppObj = this.getSqlModel().getSingleResult(pendingCancelAppSql);

			if (pendingCancelAppObj != null && pendingCancelAppObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(pageForPendingCancelApp, pendingCancelAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = CDRomApprovalModel.PAGE_ZERO;
					pageIndex[1] = CDRomApprovalModel.PAGE_TWENTY;
					pageIndex[2] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[3] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[4] = "";
				}

				request.setAttribute(CDRomApprovalModel.TOTAL_PAGE_PENDING_CANCEL, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(CDRomApprovalModel.PAGE_NO_PENDING_CANCEL, Integer.parseInt(String.valueOf(pageIndex[3])));

				pendingCancelAppList = new ArrayList<CDRomApprovalBean>(pendingCancelAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CDRomApprovalBean bean = new CDRomApprovalBean();
					bean.setCdRomID(String.valueOf(pendingCancelAppObj[i][0]));
					bean.setAuthorizedToken(this.checkNull(String.valueOf(pendingCancelAppObj[i][1])));
					bean.setEmpToken(this.checkNull(String.valueOf(pendingCancelAppObj[i][2])));
					bean.setEmpName(this.checkNull(String.valueOf(pendingCancelAppObj[i][3])));
					bean.setApplicationDate(this.checkNull(String.valueOf(pendingCancelAppObj[i][4])));
					pendingCancelAppList.add(bean);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return pendingCancelAppList;
	}
	
	/**
	 * @param pageForApprovedApp - Paging field.
	 * @param request - Used to set totalPageForPendingCancelApp &  pageNoForPendingCancelApp attributes.
	 * @return List.
	 */
	public List<CDRomApprovalBean> getApprovedApplicationList(final String pageForApprovedApp, final HttpServletRequest request) {

		List<CDRomApprovalBean> approvedAppList = null;

		try {
			final String approvedAppSql = "  SELECT CDROM_ID,CDROM_FILE_HEADER_NAME, " + " EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME , TO_CHAR(CDROM_APP_DATE, 'DD-MM-YYYY') " + " FROM HRMS_D1_CDROM_REQUEST CDROM  " + " INNER JOIN HRMS_EMP_OFFC ON CDROM.CDROM_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE CDROM_APPROVER_STATUS = 'A' ORDER BY CDROM_ID DESC"; 
			
			final Object[][] approvedAppObj = this.getSqlModel().getSingleResult(approvedAppSql);

			if (approvedAppObj != null && approvedAppObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(pageForApprovedApp, approvedAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = CDRomApprovalModel.PAGE_ZERO;
					pageIndex[1] = CDRomApprovalModel.PAGE_TWENTY;
					pageIndex[2] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[3] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[4] = "";
				}

				request.setAttribute(CDRomApprovalModel.TOTAL_PAGE_APPROVED, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(CDRomApprovalModel.PAGE_NO_APPROVED, Integer.parseInt(String.valueOf(pageIndex[3])));

				approvedAppList = new ArrayList<CDRomApprovalBean>(approvedAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CDRomApprovalBean bean = new CDRomApprovalBean();
					bean.setCdRomID(String.valueOf(approvedAppObj[i][0]));
					bean.setAuthorizedToken(String.valueOf(approvedAppObj[i][1]));
					bean.setEmpToken(this.checkNull(String.valueOf(approvedAppObj[i][2])));
					bean.setEmpName(String.valueOf(approvedAppObj[i][3]));
					bean.setApplicationDate(String.valueOf(approvedAppObj[i][4]));
					approvedAppList.add(bean);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return approvedAppList;
	}
	
	/**
	 * @param userId - Use to Get USER ID. 
	 * @param pageForApprovedApp - Paging Field.
	 * @param request - Used to set totalPageForApprovedApp & pageNoForApprovedApp attributes.
	 * @return List.
	 */
	public List<CDRomApprovalBean> getApprovedApplicationList(final String userId, final String pageForApprovedApp, final HttpServletRequest request) {

		List<CDRomApprovalBean> approvedAppList = null;

		try {
				
			final String approvedAppSql = " SELECT CDROM_ID,CDROM_FILE_HEADER_NAME, " + " HRMS_EMP_OFFC.EMP_TOKEN|| ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(CDROM_APP_DATE, 'DD-MM-YYYY') " + " FROM HRMS_D1_CDROM_REQUEST CDROM  " + " INNER JOIN HRMS_EMP_OFFC ON CDROM.CDROM_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE CDROM_APPROVER_STATUS IN ('F', 'A') AND CDROM_APPROVER_CODE= " + userId + " ORDER BY CDROM_ID DESC";
			
			final Object[][] approvedAppObj = this.getSqlModel().getSingleResult(approvedAppSql);

			if (approvedAppObj != null && approvedAppObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(pageForApprovedApp, approvedAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = CDRomApprovalModel.PAGE_ZERO;
					pageIndex[1] = CDRomApprovalModel.PAGE_TWENTY;
					pageIndex[2] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[3] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[4] = "";
				}

				request.setAttribute(CDRomApprovalModel.TOTAL_PAGE_APPROVED, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(CDRomApprovalModel.PAGE_NO_APPROVED, Integer.parseInt(String.valueOf(pageIndex[3])));

				approvedAppList = new ArrayList<CDRomApprovalBean>(approvedAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CDRomApprovalBean bean = new CDRomApprovalBean();
					bean.setCdRomID(String.valueOf(approvedAppObj[i][0]));
					bean.setAuthorizedToken(this.checkNull(String.valueOf(approvedAppObj[i][1])));
					bean.setEmpName(this.checkNull(String.valueOf(approvedAppObj[i][2])));
					bean.setApplicationDate(this.checkNull(String.valueOf(approvedAppObj[i][3])));
					approvedAppList.add(bean);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return approvedAppList;
	}
	
	/**
	 * @param pageForRejectedApp - Paging Field.
	 * @param request - Used to set totalPageForRejectedApp & pageNoForRejectedApp attributes.
	 * @return List.
	 */
	public List<CDRomApprovalBean> getRejectedApplicationList(final String pageForRejectedApp, final HttpServletRequest request) {

		List<CDRomApprovalBean> rejectedAppList = null;

		try {
			
			final String rejectedAppSql = "  SELECT CDROM_ID,CDROM_FILE_HEADER_NAME, " + " HRMS_EMP_OFFC.EMP_TOKEN|| ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(CDROM_APP_DATE, 'DD-MM-YYYY') " + " FROM HRMS_D1_CDROM_REQUEST CDROM  " + " INNER JOIN HRMS_EMP_OFFC ON CDROM.CDROM_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE CDROM_APPROVER_STATUS = 'R' ORDER BY CDROM_ID DESC ";
			final Object[][] rejectedAppObj = this.getSqlModel().getSingleResult(rejectedAppSql);

			if (rejectedAppObj != null && rejectedAppObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(pageForRejectedApp, rejectedAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = CDRomApprovalModel.PAGE_ZERO;
					pageIndex[1] = CDRomApprovalModel.PAGE_TWENTY;
					pageIndex[2] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[3] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[4] = "";
				}

				request.setAttribute(CDRomApprovalModel.TOTAL_PAGE_REJECTED, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(CDRomApprovalModel.PAGE_NO_REJECTED, Integer.parseInt(String.valueOf(pageIndex[3])));

				rejectedAppList = new ArrayList<CDRomApprovalBean>(rejectedAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CDRomApprovalBean bean = new CDRomApprovalBean();
					bean.setCdRomID(String.valueOf(rejectedAppObj[i][0]));
					bean.setAuthorizedToken(this.checkNull(String.valueOf(rejectedAppObj[i][1])));
					bean.setEmpName(this.checkNull(String.valueOf(rejectedAppObj[i][2])));
					bean.setApplicationDate(this.checkNull(String.valueOf(rejectedAppObj[i][3])));
					rejectedAppList.add(bean);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return rejectedAppList;
	}
	
	/**
	 * @param userId - Used to get User ID.
	 * @param pageForRejectedApp - Paging Field.
	 * @param request - Used to set totalPageForRejectedApp & pageNoForRejectedApp attributes.
	 * @return List.
	 */
	public List<CDRomApprovalBean> getRejectedApplicationList(final String userId, final String pageForRejectedApp, final HttpServletRequest request) {

		List<CDRomApprovalBean> rejectedAppList = null;

		try {
			final String rejectedAppSql = " SELECT CDROM_ID,CDROM_FILE_HEADER_NAME,  " + " HRMS_EMP_OFFC.EMP_TOKEN|| ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, TO_CHAR(CDROM_APP_DATE, 'DD-MM-YYYY') " + " FROM HRMS_D1_CDROM_REQUEST CDROM  " + " INNER JOIN HRMS_EMP_OFFC ON CDROM.CDROM_EMP_ID = HRMS_EMP_OFFC.EMP_ID " + " WHERE CDROM_APPROVER_STATUS = 'R' AND CDROM_APPROVER_CODE= " + userId + " ORDER BY CDROM_ID  DESC";
			
			final Object[][] rejectedAppObj = this.getSqlModel().getSingleResult(rejectedAppSql);

			if (rejectedAppObj != null && rejectedAppObj.length > 0) {
				final String[] pageIndex = Utility.doPaging(pageForRejectedApp, rejectedAppObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = CDRomApprovalModel.PAGE_ZERO;
					pageIndex[1] = CDRomApprovalModel.PAGE_TWENTY;
					pageIndex[2] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[3] = CDRomApprovalModel.PAGE_ONE;
					pageIndex[4] = "";
				}

				request.setAttribute(CDRomApprovalModel.TOTAL_PAGE_REJECTED, Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute(CDRomApprovalModel.PAGE_NO_REJECTED, Integer.parseInt(String.valueOf(pageIndex[3])));

				rejectedAppList = new ArrayList<CDRomApprovalBean>(rejectedAppObj.length);

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final CDRomApprovalBean bean = new CDRomApprovalBean();
					bean.setCdRomID(String.valueOf(rejectedAppObj[i][0]));
					bean.setAuthorizedToken(this.checkNull(String.valueOf(rejectedAppObj[i][1])));
					bean.setEmpName(this.checkNull(String.valueOf(rejectedAppObj[i][2])));
					bean.setApplicationDate(this.checkNull(String.valueOf(rejectedAppObj[i][3])));
					rejectedAppList.add(bean);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return rejectedAppList;
	}
	
	/**
	 * @param cdromID - Used to get CD ROM ID.
	 * @return Object.
	 */
	public Object[][] getPersDataChangeDetails(final String cdromID) {
		final String getDetailsSql = " SELECT CDROM_APPROVER_COMMENTS, CDROM_APPROVER_CODE, CDROM_APPROVER_STATUS FROM HRMS_D1_CDROM_REQUEST " + " WHERE CDROM_ID =" + cdromID;
		return this.getSqlModel().getSingleResult(getDetailsSql);
	}
	
	/**
	 * @param cdromID - Used to get CD ROM ID.
	 * @return Object.
	 */
	public Object[][] getApproverCommentList(final String cdromID) {
		
		final String apprCommentListSql = "SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, CDROM_COMMENTS, " + " TO_CHAR(CDROM_DATE, 'DD-MM-YYYY') AS CDROM_DATE, " + " DECODE(CDROM_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Send Back') " + " AS STATUS " 	+ " FROM HRMS_D1_CDROM_DATA_PATH PATH   " + " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.CDROM_APPROVER) " + " WHERE CDROM_ID = " + cdromID + " ORDER BY CDROM_ID DESC ";
		return this.getSqlModel().getSingleResult(apprCommentListSql);
	}
	
	/**
	 * @param persDataChangeId - Used to get CD ROM ID.
	 * @param userId - Used to get User ID.
	 * @return true/false.
	 */
	public boolean isUserAMagaer(final String persDataChangeId, final String userId) {
		final String mgrApproverSql = " SELECT * FROM HRMS_D1_CDROM_REQUEST WHERE CDROM_ID = " + persDataChangeId + " AND CDROM_APPROVER_CODE= " + userId;
		final Object[][] mgrApproverObj = this.getSqlModel().getSingleResult(mgrApproverSql);

		if (mgrApproverObj != null && mgrApproverObj.length > 0) { return true; }

		return false;
	}
	
	/**
	 * @param cdromId - Used to pass as an argument to  insertApproverComments.
	 * @param approverComments - Used to pass as an argument to  insertApproverComments.
	 * @param userId - Used to pass as an argument to  insertApproverComments.
	 * @param status - check status.
	 * @param nextApprover - not used.
	 * @param level - Level purpose.
	 * @return String.
	 */
	public String approve(final String cdromId, final String approverComments, final String userId, final String status, final String nextApprover, final int level) {
		

		String statusToUpdate = "A";
		//final String updateApproversql = "";

		if ("C".equals(status)) {
			statusToUpdate = "X";
		} else {
			/*if(isUserAMagaer(cdromId, userId)) {
				statusToUpdate = "P";
				
				if(nextApprover.equals(userId)) {
					statusToUpdate = "F";
				}
				
				updateApproversql = ", CDROM_APPROVER_CODE = " + nextApprover;
			}*/
			
			if ("P".equals(status)) {
				statusToUpdate = "F";
			}
		}

		final String updateApproverCommentsSql = " UPDATE HRMS_D1_CDROM_REQUEST  SET CDROM_APPROVER_STATUS = '" + statusToUpdate + "', " + "  CDROM_LEVEL = " + level + " WHERE CDROM_ID = " + cdromId;
		this.getSqlModel().singleExecute(updateApproverCommentsSql);

		this.insertApproverComments(cdromId, userId, approverComments, statusToUpdate);
		
		return statusToUpdate;
	}
	
	/**
	 * @param cdromId - Used to get CD ROM ID.
	 * @param approverComments - Used to pass as an argument to  insertApproverComments.
	 * @param userId - Used to pass as an argument to  insertApproverComments.
	 * @return String.
	 */
	public String reject(final String cdromId, final String approverComments, final String userId) {
		final String message = "";

		try {
			final String updateApproverCommentsSql = " UPDATE HRMS_D1_CDROM_REQUEST SET CDROM_APPROVER_STATUS = 'R' " 	+ " WHERE CDROM_ID = " + cdromId;
			this.getSqlModel().singleExecute(updateApproverCommentsSql);

			this.insertApproverComments(cdromId, userId, approverComments, "R");
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return message;
	}
	
	/**
	 * added by nilesh.
	 */
	
	/**
	 * @param cdromId - Used to get CD ROM ID.
	 * @param approverComments - Used to pass as an argument to  insertApproverComments.
	 * @param userId - Used to pass as an argument to  insertApproverComments.
	 * @return String.
	 */
	public String sendBack(final String cdromId, final String approverComments, final String userId) {
		final String updateApproverCommentsSql = " UPDATE HRMS_D1_CDROM_REQUEST SET CDROM_APPROVER_STATUS = 'B', CDROM_LEVEL = 1 " + " WHERE CDROM_ID = " + cdromId;
		this.getSqlModel().singleExecute(updateApproverCommentsSql);

		this.insertApproverComments(cdromId, userId, approverComments, CDRomApprovalModel.STATUS);
		
		return CDRomApprovalModel.STATUS;
	}

	
	/**
	 * @param cdromId - Used to set CD ROM ID at [0][0].
	 * @param userId - Used to set USER ID at [0][1].
	 * @param approverComments - Used to set Approver Comments at [0][2].
	 * @param statusToUpdate - Used to set Status To Update at [0][3].
	 */
	private void insertApproverComments(final String cdromId, final String userId, final String approverComments, final String statusToUpdate) {
		final String insertSql = " INSERT INTO HRMS_D1_CDROM_DATA_PATH (CDROM_PATH_ID,CDROM_ID, CDROM_APPROVER, CDROM_COMMENTS,CDROM_STATUS,CDROM_DATE) " + " VALUES ((SELECT NVL(MAX(CDROM_PATH_ID), 0) + 1 FROM HRMS_D1_CDROM_DATA_PATH), ?, ?, ?, ?,sysdate) ";

		final Object[][] insertObj = new Object[1][4];
		insertObj[0][0] = cdromId;
		insertObj[0][1] = userId;
		insertObj[0][2] = approverComments;
		insertObj[0][3] = statusToUpdate;

		this.getSqlModel().singleExecute(insertSql, insertObj);
	}

}
