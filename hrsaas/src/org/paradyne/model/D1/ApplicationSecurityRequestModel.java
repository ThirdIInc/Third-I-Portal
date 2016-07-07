package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.D1.ApplicationSecurityRequest;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * Bhushan Dasare Feb 28, 2011 Model for IT security request application and approval.
 */

public class ApplicationSecurityRequestModel extends ModelBase {
        
        /**
         * Draft status.
         */
        public static final String STATUS_DRAFT = "D";
        
        /**
         * Pending status.
         */
        public static final String STATUS_PENDING = "P";
        
        /**
         * Send Back status.
         */
        public static final String STATUS_SEND_BACK = "B";
        
        /**
         * Approved status.
         */
        public static final String STATUS_APPROVED = "A";
        
        /**
         * Rejected status.
         */
        public static final String STATUS_REJECTED = "R";
        
        /**
         * Forwarded status.
         */
        public static final String STATUS_FORWARDED = "F";
        
        /**
         * Canceled status.
         */
        public static final String STATUS_CANCELED = "C";
        
        /**
         * Canceled Approved status.
         */
        public static final String STATUS_CANCELED_APPROVED = "X";
        
        /**
         * Canceled Rejected status.
         */
        public static final String STATUS_CANCELED_REJECTED = "Z";

        /**
         * Log4j logger.
         */
        private static Logger logger = Logger.getLogger(ApplicationSecurityRequestModel.class);
        
        /**
         * Used to set false value.
         */
        private static final String FLAG_FALSE = "false";
        
        /**
         * Used to set true value.
         */
        private static final String FLAG_TRUE = "true";
        
        /**
         * Used to set Y.
         */
        private static final String FLAG_Y = "Y";
        
        /**
         * Used to set N.
         */
        private static final String FLAG_N = "N";
        

        /**
         * Method to approve application.
         * 
         * @param bean - Used in insertApproverComments())
         * @return - message
         */
        public String approve(final ApplicationSecurityRequest bean) {

                String message = "Application cannot be approved!";

                final String applnStatus = bean.getApplnStatus();
                String statusToUpdate = ApplicationSecurityRequestModel.STATUS_APPROVED;

                if (applnStatus.equals(ApplicationSecurityRequestModel.STATUS_CANCELED)) {
                        statusToUpdate = ApplicationSecurityRequestModel.STATUS_CANCELED_APPROVED;
                }

                final String updateSql = "UPDATE HRMS_D1_APPLN_SECURITY SET APPLN_SEC_STATUS = '" + statusToUpdate + "' WHERE APPLN_SEC_ID = " + 
                        bean.getApplicationId();
                this.getSqlModel().singleExecute(updateSql);

                final boolean isSaved = this.insertApproverComments(bean, statusToUpdate);

                if (isSaved) {
                        try {
                                this.sendMailFromApproverToApplicant(bean);
                        } catch (final Exception e) {
                                ApplicationSecurityRequestModel.logger.error(e);
                        }

                        message = "Application approved successfully!";
                }

                return message;
        }

        /**
         * Method to cancel application.
         * 
         * @param bean - Used to get applicationId
         * @return - message
         */
        public String cancel(final ApplicationSecurityRequest bean) {

                String message = "Application cannot be send for cancellation!";

                final String updateSql = "UPDATE HRMS_D1_APPLN_SECURITY SET APPLN_SEC_STATUS = 'C' WHERE APPLN_SEC_ID = " + bean.getApplicationId();
                final boolean isUpdated = this.getSqlModel().singleExecute(updateSql);

                if (isUpdated) {
                        message = "Application successfully send for cancellation approval!";
                }

                return message;
        }

        /**
         * Method to check string is null or not.
         * 
         * @param result - Check null or not
         * @return - String after checking null
         */
        public String checkNull(final String result) {

                if (result == null || "null".equals(result)) {
                        return "";
                } else {
                        return result;
                }
        }
        public String checkNullValue(final String result) {

            if (result == null || "null".equals(result)) {
                    return "0";
            } else {
                    return result;
            }
    }
        /**
         * Method to delete application.
         * 
         * @param applicationId - Used in delete query
         * @return - message
         */
        public String delete(final String applicationId) {

                String message = "Application cannot be deleted!";

                final String delApplnSec = "DELETE FROM HRMS_D1_APPLN_SECURITY WHERE APPLN_SEC_ID = " + applicationId;
                final boolean isDeleted = this.getSqlModel().singleExecute(delApplnSec);

                if (isDeleted) {
                        message = "Application deleted successfully!";
                }

                return message;
        }

        /**
         * Method to draft the application.
         * 
         * @param bean - Used get applicationId, userEmpId. Also used in save, update, used to set trackingInfo
         * @return - message
         */
        public String draft(final ApplicationSecurityRequest bean) {

                String message = "Application cannot be saved!";

                String applicationId = bean.getApplicationId();

                if ("".equals(applicationId)) {
                        final String applnSecIdSql = "SELECT NVL(MAX(APPLN_SEC_ID), 0) + 1 FROM HRMS_D1_APPLN_SECURITY";
                        final Object [][] applnSecIdObj = this.getSqlModel().getSingleResult(applnSecIdSql);
                        applicationId = String.valueOf(applnSecIdObj [0] [0]);

                        bean.setTrackingNo(this.getTrackingNo(bean.getUserEmpId()));

                        message = this.save(bean, applicationId);
                } else {
                        message = this.update(bean, applicationId);
                }

                return message;
        }

        /**
         * Method to get application list.
         * 
         * @param bean - Used to get hidnApplications
         * @param request - Used to set applicationList in attribute
         */
        public void getApplicationList(final ApplicationSecurityRequest bean, final HttpServletRequest request) {
                
                final String applnSql = "SELECT APPLN_ID, APPLN_NAME, APPLN_SECTION FROM HRMS_D1_IT_SEC_APPLICATIONS " + 
                        "WHERE APPLN_ACTIVE = 'Y' ORDER BY APPLN_ID DESC";
                final Object [][] applnObj = this.getSqlModel().getSingleResult(applnSql);
                
                final String comma = ",";

                Object [][] applicationList = null;
                String [] selectedApplns = null;

                if (!"".equals(this.checkNull(bean.getHdnApplications()))) {
                        selectedApplns = bean.getHdnApplications().split(comma);
                }

                if (applnObj != null && applnObj.length > 0) {
                        String hdnSections = "";
                        applicationList = new Object [applnObj.length] [4];

                        for (int i = 0; i < applicationList.length; i++) {
                                final String applnId = this.checkNull(String.valueOf(applnObj [i] [0])); // APPLN_ID

                                applicationList [i] [0] = applnId;
                                applicationList [i] [1] = this.checkNull(String.valueOf(applnObj [i] [1])); // APPLN_NAME
                                applicationList [i] [2] = this.checkNull(String.valueOf(applnObj [i] [2])); // APPLN_SECTION

                                boolean isApplnUsed = false;
                                if (selectedApplns != null && selectedApplns.length > 0) {
                                        for (int j = 0; j < selectedApplns.length; j++) {
                                                if (applnId.equals(selectedApplns [j])) {
                                                        isApplnUsed = true;
                                                        break;
                                                }
                                        }
                                }

                                if (isApplnUsed) {
                                        applicationList [i] [3] = ApplicationSecurityRequestModel.FLAG_Y;

                                        if ("".equals(hdnSections)) {
                                                hdnSections = this.checkNull(String.valueOf(applnObj [i] [2]));
                                        } else {
                                                hdnSections += comma + this.checkNull(String.valueOf(applnObj [i] [2]));
                                        }
                                } else {
                                        applicationList [i] [3] = ApplicationSecurityRequestModel.FLAG_N;
                                }
                        }

                        bean.setHdnSections(hdnSections);
                }

                request.setAttribute("applicationList", applicationList);
        }

        /**
         * Method to get selected applications.
         * 
         * @param applications - Used to get application names
         * @return application names separated by comma
         */
        private String getSelectedApplications(final String applications) {

                final String applnSql = "SELECT TRIM(APPLN_NAME) FROM HRMS_D1_IT_SEC_APPLICATIONS WHERE APPLN_ID IN(" + applications + ")";
                final Object [][] applnObj = this.getSqlModel().getSingleResult(applnSql);

                String strApplications = "";

                if (applnObj != null && applnObj.length > 0) {
                        for (int i = 0; i < applnObj.length; i++) {
                                if (i == applnObj.length - 1) {
                                        strApplications += this.checkNull(String.valueOf(applnObj [i] [0]));
                                } else {
                                        strApplications += this.checkNull(String.valueOf(applnObj [i] [0])) + ", ";
                                }
                        }
                }

                return strApplications;
        }

        /**
         * Method to get list of approve cancel applications.
         * 
         * @param bean - Used to get userEmpId, pageForApproveCancel, used to set approvedCancelListLength, setApproveCancelList
         * @param isUserAITApprover - When false, then get list of applications of initiator only
         * @param request - Used to set totalPageForApproveCancel and pageNoForApproveCancel in attribute
         */
        public void getApproveCacelledList(final ApplicationSecurityRequest bean, final boolean isUserAITApprover, final HttpServletRequest request) {

                String approvedCancelListSql = "SELECT APPSEC.APPLN_SEC_ID, APPLN_SEC_TRACKING_NO, (OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME) AS MANAGER_NAME ,NVL(APPLN_SEC_EMP_TOKEN, ' '),NVL(APPLN_SEC_EMP_NAME, ' ') AS EMPLOYEE_NAME, " +
                        "TO_CHAR(APPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, " +
                        " DECODE(APPLN_SEC_REQ_TYPE,'A','Add', 'C','Change', 'D','Delete') " +
                        " FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) WHERE APPSEC.APPLN_SEC_STATUS = 'X' ";

                if (!isUserAITApprover) {
                        approvedCancelListSql += "AND APPSEC.APPLN_SEC_INITIATOR = " + bean.getUserEmpId();
                }

                approvedCancelListSql += " ORDER BY APPSEC.APPLN_SEC_REQ_DATE DESC, MANAGER_NAME ASC, EMPLOYEE_NAME  ASC";
                final Object [][] approvedCancelListObj = this.getSqlModel().getSingleResult(approvedCancelListSql);

                int totalPageForApproveCancel = 0;
                int pageNoForApproveCancel = 0;

                if (approvedCancelListObj != null && approvedCancelListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForApproveCancel(), approvedCancelListObj.length, 20);

                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        totalPageForApproveCancel = Integer.parseInt(String.valueOf(pageIndex [2]));
                        pageNoForApproveCancel = Integer.parseInt(String.valueOf(pageIndex [3]));

                        final List<ApplicationSecurityRequest> approveCancelList = new ArrayList<ApplicationSecurityRequest>(Integer.parseInt(pageIndex [1]));
                        
                        // Added by Nilesh Dhandare
                        bean.setApprovedCancelListLength(true);
                        
                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ApplicationSecurityRequest applnSecReq = new ApplicationSecurityRequest();
                                
                                applnSecReq.setApplnSecReqId(this.checkNull(String.valueOf(approvedCancelListObj [i] [0])));
                                applnSecReq.setTrackingNo(this.checkNull(String.valueOf(approvedCancelListObj [i] [1])));
                                applnSecReq.setMgrName(this.checkNull(String.valueOf(approvedCancelListObj [i] [2])));
                                applnSecReq.setEmpToken(this.checkNull(String.valueOf(approvedCancelListObj [i] [3])));
                                applnSecReq.setEmpIniName(this.checkNull(String.valueOf(approvedCancelListObj [i] [4])));
                                applnSecReq.setRequestDate(this.checkNull(String.valueOf(approvedCancelListObj [i] [5])));
                                applnSecReq.setRequestTypeItr(this.checkNull(String.valueOf(approvedCancelListObj [i] [6])));

                                approveCancelList.add(applnSecReq);
                        }

                        bean.setApproveCancelList(approveCancelList);
                }

                request.setAttribute("totalPageForApproveCancel", totalPageForApproveCancel);
                request.setAttribute("pageNoForApproveCancel", pageNoForApproveCancel);
        }

        /**
         * Method to get list of approved applications.
         * 
         * @param bean - Used to get userEmpId, pageForApprove, used to set approvedListLength, approve list
         * @param isUserAITApprover - When false, then get list of applications of initiator only
         * @param request - Used to set totalPageForApprove and pageNoForApprove in attribute
         */
        public void getApprovedList(final ApplicationSecurityRequest bean, final boolean isUserAITApprover, final HttpServletRequest request) {

                String approvedListSql = "SELECT APPSEC.APPLN_SEC_ID, APPLN_SEC_TRACKING_NO, (OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME) AS MANAGER_NAME ,NVL(APPLN_SEC_EMP_TOKEN,' '),NVL(APPLN_SEC_EMP_NAME,' ') AS EMPLOYEE_NAME, " +
                        "TO_CHAR(APPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, " +
                        " DECODE(APPLN_SEC_REQ_TYPE,'A','Add', 'C','Change', 'D','Delete') " +
                        " FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) WHERE APPSEC.APPLN_SEC_STATUS = 'A' ";

                if (!isUserAITApprover) {
                        approvedListSql += "AND APPSEC.APPLN_SEC_INITIATOR = " + bean.getUserEmpId();
                }

                approvedListSql += " ORDER BY APPSEC.APPLN_SEC_REQ_DATE DESC , MANAGER_NAME ASC , EMPLOYEE_NAME ASC";
                final Object [][] approvedListObj = this.getSqlModel().getSingleResult(approvedListSql);

                int totalPageForApprove = 0;
                int pageNoForApprove = 0;

                if (approvedListObj != null && approvedListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForApprove(), approvedListObj.length, 20);

                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        totalPageForApprove = Integer.parseInt(String.valueOf(pageIndex [2]));
                        pageNoForApprove = Integer.parseInt(String.valueOf(pageIndex [3]));

                        final List<ApplicationSecurityRequest> approveList = new ArrayList<ApplicationSecurityRequest>(Integer.parseInt(pageIndex [1]));
                        
                        // Added by Nilesh Dhandare
                        bean.setApprovedListLength(true);
                        
                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ApplicationSecurityRequest applnSecReq = new ApplicationSecurityRequest();
                                applnSecReq.setApplnSecReqId(this.checkNull(String.valueOf(approvedListObj [i] [0])));
                                applnSecReq.setTrackingNo(this.checkNull(String.valueOf(approvedListObj [i] [1])));
                                applnSecReq.setMgrName(this.checkNull(String.valueOf(approvedListObj [i] [2])));
                                applnSecReq.setEmpToken(this.checkNull(String.valueOf(approvedListObj [i] [3])));
                                applnSecReq.setEmpIniName(this.checkNull(String.valueOf(approvedListObj [i] [4])));
                                applnSecReq.setRequestDate(this.checkNull(String.valueOf(approvedListObj [i] [5])));
                                applnSecReq.setRequestTypeItr(this.checkNull(String.valueOf(approvedListObj [i] [6])));

                                approveList.add(applnSecReq);
                        }

                        bean.setApproveList(approveList);
                }

                request.setAttribute("totalPageForApprove", totalPageForApprove);
                request.setAttribute("pageNoForApprove", pageNoForApprove);
        }

        /**
         * Method to get list of draft applications.
         * 
         * @param bean - Used to get userEmpId, pageForDraft, used to set draft list
         * @param request - Used to set totalPageForApprove and pageNoForApprove in attribute
         */
        public void getDraftList(final ApplicationSecurityRequest bean, final HttpServletRequest request) {

                final String draftListSql = "SELECT APPSEC.APPLN_SEC_ID, APPLN_SEC_TRACKING_NO, (OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME) AS MANAGER_NAME ,NVL(APPLN_SEC_EMP_TOKEN,' '),NVL(APPLN_SEC_EMP_NAME,' ') AS EMPLOYEE_NAME, " +
                        "TO_CHAR(APPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE,  " +
                        " DECODE(APPLN_SEC_REQ_TYPE,'A','Add', 'C','Change', 'D','Delete') " +
                        " FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) " +
                        "WHERE APPSEC.APPLN_SEC_STATUS = 'D' AND APPSEC.APPLN_SEC_INITIATOR = " + bean.getUserEmpId() + 
                        " ORDER BY APPSEC.APPLN_SEC_REQ_DATE DESC , MANAGER_NAME ASC , EMPLOYEE_NAME ASC";
                final Object [][] draftListObj = this.getSqlModel().getSingleResult(draftListSql);

                int totalPageForDraft = 0;
                int pageNoForDraft = 0;

                if (draftListObj != null && draftListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForDraft(), draftListObj.length, 20);

                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        totalPageForDraft = Integer.parseInt(String.valueOf(pageIndex [2]));
                        pageNoForDraft = Integer.parseInt(String.valueOf(pageIndex [3]));

                        final List<ApplicationSecurityRequest> draftList = new ArrayList<ApplicationSecurityRequest>(Integer.parseInt(pageIndex [1]));

                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ApplicationSecurityRequest applnSecReq = new ApplicationSecurityRequest();
                                applnSecReq.setApplnSecReqId(this.checkNull(String.valueOf(draftListObj [i] [0])));
                                applnSecReq.setTrackingNo(this.checkNull(String.valueOf(draftListObj [i] [1])));
                                applnSecReq.setMgrName(this.checkNull(String.valueOf(draftListObj [i] [2])));
                                applnSecReq.setEmpToken(this.checkNull(String.valueOf(draftListObj [i] [3])));
                                applnSecReq.setEmpIniName(this.checkNull(String.valueOf(draftListObj [i] [4])));
                                applnSecReq.setRequestDate(this.checkNull(String.valueOf(draftListObj [i] [5])));
                                applnSecReq.setRequestTypeItr(this.checkNull(String.valueOf(draftListObj [i] [6])));
                                draftList.add(applnSecReq);
                        }

                        bean.setDraftList(draftList);
                }

                request.setAttribute("totalPageForDraft", totalPageForDraft);
                request.setAttribute("pageNoForDraft", pageNoForDraft);
        }

        /**
         * Method to get initiator details.
         * 
         * @param initiatorId - Used in query for initiator details
         * @return Object[][] containing initiator details
         */
        public Object [][] getInitiatorDetails(final String initiatorId) {

                final String initiatorSql = "SELECT INITIATOR.EMP_ID, INITIATOR.EMP_TOKEN || ' ' || INITIATOR.EMP_FNAME ||' ' || " +
                        "INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI') AS COMPLETED_ON " +
                        "FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID = " + initiatorId;
                return this.getSqlModel().getSingleResult(initiatorSql);
        }

        /**
         * Method to get list of pending cancel applications.
         * 
         * @param bean - Used to get userEmpId, pageForPendingCancel, used to set pendingCancelListLength, pendingCancelList
         * @param isUserAITApprover - When false, then get list of applications of initiator only
         * @param request - Used to set totalPageForPendingCancel and pageNoForPendingCancel in attribute
         */
        public void getPendingCancelList(final ApplicationSecurityRequest bean, final boolean isUserAITApprover, final HttpServletRequest request) {

                String pendingCancelListSql = "SELECT APPSEC.APPLN_SEC_ID, APPLN_SEC_TRACKING_NO, (OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME) AS MANAGER_NAME ,NVL(APPLN_SEC_EMP_TOKEN,' '),NVL(APPLN_SEC_EMP_NAME,' ') AS EMPLOYEE_NAME, " +
                        "TO_CHAR(APPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, " +
                        " DECODE(APPLN_SEC_REQ_TYPE,'A','Add', 'C','Change', 'D','Delete') " +
                        "FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) WHERE APPSEC.APPLN_SEC_STATUS = 'C' ";

                if (!isUserAITApprover) {
                        pendingCancelListSql += "AND APPSEC.APPLN_SEC_INITIATOR = " + bean.getUserEmpId();
                }
                
                pendingCancelListSql += "ORDER BY APPSEC.APPLN_SEC_REQ_DATE DESC , MANAGER_NAME ASC , EMPLOYEE_NAME ASC";
                final Object [][] pendingCancelListObj = this.getSqlModel().getSingleResult(pendingCancelListSql);

                int totalPageForPendingCancel = 0;
                int pageNoForPendingCancel = 0;

                if (pendingCancelListObj != null && pendingCancelListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForPendingCancel(), pendingCancelListObj.length, 20);
                      String value = bean.getPageForPendingCancel();
                    
                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        try {
							totalPageForPendingCancel = Integer.parseInt(checkNullValue(String.valueOf(pageIndex [2])));
							pageNoForPendingCancel = Integer.parseInt(checkNullValue(String.valueOf(pageIndex [3])));
							
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}

                        final List<ApplicationSecurityRequest> pendingCancelList = new ArrayList<ApplicationSecurityRequest>(Integer.parseInt(pageIndex [1]));
                        bean.setPendingCancelListLength(true);
                        
                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ApplicationSecurityRequest applnSecReq = new ApplicationSecurityRequest();
                                applnSecReq.setApplnSecReqId(this.checkNull(String.valueOf(pendingCancelListObj [i] [0])));
                                applnSecReq.setTrackingNo(this.checkNull(String.valueOf(pendingCancelListObj [i] [1])));
                                applnSecReq.setMgrName(this.checkNull(String.valueOf(pendingCancelListObj [i] [2])));
                                applnSecReq.setEmpToken(this.checkNull(String.valueOf(pendingCancelListObj [i] [3])));
                                applnSecReq.setEmpIniName(this.checkNull(String.valueOf(pendingCancelListObj [i] [4])));
                                applnSecReq.setRequestDate(this.checkNull(String.valueOf(pendingCancelListObj [i] [5])));
                                applnSecReq.setRequestTypeItr(this.checkNull(String.valueOf(pendingCancelListObj [i] [6])));

                                pendingCancelList.add(applnSecReq);
                        }

                        bean.setPendingCancelList(pendingCancelList);
                }

                request.setAttribute("totalPageForPendingCancel", totalPageForPendingCancel);
                request.setAttribute("pageNoForPendingCancel", pageNoForPendingCancel);
        }

        /**
         * Method to get list of pending applications.
         * 
         * @param bean - Used to get userEmpId, pageForPending, used to set pendingListLength, pendingList
         * @param isUserAITApprover - When false, then get list of applications of initiator only
         * @param request - Used to set totalPageForPending and pageNoForPending in attribute
         */
        public void getPendingList(final ApplicationSecurityRequest bean, final boolean isUserAITApprover, final HttpServletRequest request) {

                String pendingListSql = "SELECT APPSEC.APPLN_SEC_ID, APPLN_SEC_TRACKING_NO, (OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME) AS MANAGER_NAME ,NVL(APPLN_SEC_EMP_TOKEN,' '),NVL(APPLN_SEC_EMP_NAME,' ') AS  EMPLOYEE_NAME , " +
                        "TO_CHAR(APPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, APPLN_SEC_STATUS, " +
                        " DECODE(APPLN_SEC_REQ_TYPE,'A','Add', 'C','Change', 'D','Delete') " +
                        " FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) WHERE APPSEC.APPLN_SEC_STATUS = 'P' ";

                if (!isUserAITApprover) {
                        pendingListSql += "AND APPSEC.APPLN_SEC_INITIATOR = " + bean.getUserEmpId();
                }

                pendingListSql += " ORDER BY APPSEC.APPLN_SEC_REQ_DATE DESC , MANAGER_NAME ASC ,  EMPLOYEE_NAME ASC ";
                final Object [][] pendingListObj = this.getSqlModel().getSingleResult(pendingListSql);

                int totalPageForPending = 0;
                int pageNoForPending = 0;

                if (pendingListObj != null && pendingListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForPending(), pendingListObj.length, 20);

                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        totalPageForPending = Integer.parseInt(String.valueOf(pageIndex [2]));
                        pageNoForPending = Integer.parseInt(String.valueOf(pageIndex [3]));

                        final List<ApplicationSecurityRequest> pendingList = new ArrayList<ApplicationSecurityRequest>(Integer.parseInt(pageIndex [1]));
                        
                        // Added by Nilesh For Pending Application List
                        bean.setPendingListLength(true);
                        
                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ApplicationSecurityRequest applnSecReq = new ApplicationSecurityRequest();
                                applnSecReq.setApplnSecReqId(this.checkNull(String.valueOf(pendingListObj [i] [0])));
                                applnSecReq.setTrackingNo(this.checkNull(String.valueOf(pendingListObj [i] [1])));
                                applnSecReq.setMgrName(this.checkNull(String.valueOf(pendingListObj [i] [2])));
                                applnSecReq.setEmpToken(this.checkNull(String.valueOf(pendingListObj [i] [3])));
                                applnSecReq.setEmpIniName(this.checkNull(String.valueOf(pendingListObj [i] [4])));
                                applnSecReq.setRequestDate(this.checkNull(String.valueOf(pendingListObj [i] [5])));
                                applnSecReq.setApplicationStatus(this.checkNull(String.valueOf(pendingListObj [i] [6])));
                                applnSecReq.setRequestTypeItr(this.checkNull(String.valueOf(pendingListObj [i] [7])));
                                pendingList.add(applnSecReq);
                        }

                        bean.setPendingList(pendingList);
                }

                request.setAttribute("totalPageForPending", totalPageForPending);
                request.setAttribute("pageNoForPending", pageNoForPending);
        }

        /**
         * Method to get list of rejected cancel applications.
         * 
         * @param bean - Used to get userEmpId, pageForRejectedCancel, used to set rejectedCancelListLength, rejectedCancelList
         * @param isUserAITApprover - When false, then get list of applications of initiator only
         * @param request - Used to set totalPageForRejectedCancel and pageNoForRejectedCancel in attribute
         */
        public void getRejectedCancelList(final ApplicationSecurityRequest bean, final boolean isUserAITApprover, final HttpServletRequest request) {

                String rejectedCancelListSql = "SELECT APPSEC.APPLN_SEC_ID, APPLN_SEC_TRACKING_NO, (OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME) AS MANAGER_NAME ,NVL(APPLN_SEC_EMP_TOKEN,' '),NVL(APPLN_SEC_EMP_NAME,' ') AS EMPLOYEE_NAME, " +
                        "TO_CHAR(APPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, " +
                        " DECODE(APPLN_SEC_REQ_TYPE,'A','Add', 'C','Change', 'D','Delete') " +
                        " FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) WHERE APPSEC.APPLN_SEC_STATUS = 'Z' ";

                if (!isUserAITApprover) {
                        rejectedCancelListSql += "AND APPSEC.APPLN_SEC_INITIATOR = " + bean.getUserEmpId();
                }

                rejectedCancelListSql += " ORDER BY APPSEC.APPLN_SEC_REQ_DATE DESC , MANAGER_NAME ASC , EMPLOYEE_NAME ASC";
                final Object [][] rejectedCancelListObj = this.getSqlModel().getSingleResult(rejectedCancelListSql);

                int totalPageForRejectedCancel = 0;
                int pageNoForRejectedCancel = 0;

                if (rejectedCancelListObj != null && rejectedCancelListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForRejectedCancel(), rejectedCancelListObj.length, 20);

                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        totalPageForRejectedCancel = Integer.parseInt(String.valueOf(pageIndex [2]));
                        pageNoForRejectedCancel = Integer.parseInt(String.valueOf(pageIndex [3]));

                        final List<ApplicationSecurityRequest> rejectedCancelList = new ArrayList<ApplicationSecurityRequest>(Integer.parseInt(pageIndex [1]));
                        
                        // Added by Nilesh Dhandare
                        bean.setRejectedCancelListLength(true);
                        
                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ApplicationSecurityRequest applnSecReq = new ApplicationSecurityRequest();
                                applnSecReq.setApplnSecReqId(this.checkNull(String.valueOf(rejectedCancelListObj [i] [0])));
                                applnSecReq.setTrackingNo(this.checkNull(String.valueOf(rejectedCancelListObj [i] [1])));
                                applnSecReq.setMgrName(this.checkNull(String.valueOf(rejectedCancelListObj [i] [2])));
                                applnSecReq.setEmpToken(this.checkNull(String.valueOf(rejectedCancelListObj [i] [3])));
                                applnSecReq.setEmpIniName(this.checkNull(String.valueOf(rejectedCancelListObj [i] [4])));
                                applnSecReq.setRequestDate(this.checkNull(String.valueOf(rejectedCancelListObj [i] [5])));
                                applnSecReq.setRequestTypeItr(this.checkNull(String.valueOf(rejectedCancelListObj [i] [6])));

                                rejectedCancelList.add(applnSecReq);
                        }

                        bean.setRejectedCancelList(rejectedCancelList);
                }

                request.setAttribute("totalPageForRejectedCancel", totalPageForRejectedCancel);
                request.setAttribute("pageNoForRejectedCancel", pageNoForRejectedCancel);
        }

        /**
         * Method to get list of rejected applications.
         * 
         * @param bean - Used to get userEmpId, pageForRejected, used to set rejectedListLength, rejectedList
         * @param isUserAITApprover - When false, then get list of applications of initiator only
         * @param request - Used to set totalPageForRejected and pageNoForRejected in attribute
         */
        public void getRejectedList(final ApplicationSecurityRequest bean, final boolean isUserAITApprover, final HttpServletRequest request) {

                String rejectedListSql = "SELECT APPSEC.APPLN_SEC_ID, APPLN_SEC_TRACKING_NO, (OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME) AS MANAGER_NAME ,NVL(APPLN_SEC_EMP_TOKEN,' '),NVL(APPLN_SEC_EMP_NAME,' ') AS EMPLOYEE_NAME, " +
                        "TO_CHAR(APPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, " +
                        " DECODE(APPLN_SEC_REQ_TYPE,'A','Add', 'C','Change', 'D','Delete') " +
                        " FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) WHERE APPSEC.APPLN_SEC_STATUS = 'R' ";

                if (!isUserAITApprover) {
                        rejectedListSql += "AND APPSEC.APPLN_SEC_INITIATOR = " + bean.getUserEmpId();
                }

                rejectedListSql += " ORDER BY APPSEC.APPLN_SEC_REQ_DATE DESC , MANAGER_NAME ASC, EMPLOYEE_NAME ASC";
                final Object [][] rejectedListObj = this.getSqlModel().getSingleResult(rejectedListSql);

                int totalPageForRejected = 0;
                int pageNoForRejected = 0;

                if (rejectedListObj != null && rejectedListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForRejected(), rejectedListObj.length, 20);

                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        totalPageForRejected = Integer.parseInt(String.valueOf(pageIndex [2]));
                        pageNoForRejected = Integer.parseInt(String.valueOf(pageIndex [3]));

                        final List<ApplicationSecurityRequest> rejectedList = new ArrayList<ApplicationSecurityRequest>(Integer.parseInt(pageIndex [1]));
                        
                        // Added by Nilesh Dhandare
                        bean.setRejectedListLength(true);
                        
                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ApplicationSecurityRequest applnSecReq = new ApplicationSecurityRequest();
                                applnSecReq.setApplnSecReqId(this.checkNull(String.valueOf(rejectedListObj [i] [0])));
                                applnSecReq.setTrackingNo(this.checkNull(String.valueOf(rejectedListObj [i] [1])));
                                applnSecReq.setMgrName(this.checkNull(String.valueOf(rejectedListObj [i] [2])));
                                applnSecReq.setEmpToken(this.checkNull(String.valueOf(rejectedListObj [i] [3])));
                                applnSecReq.setEmpIniName(this.checkNull(String.valueOf(rejectedListObj [i] [4])));
                                applnSecReq.setRequestDate(this.checkNull(String.valueOf(rejectedListObj [i] [5])));
                                applnSecReq.setRequestTypeItr(this.checkNull(String.valueOf(rejectedListObj [i] [6])));

                                rejectedList.add(applnSecReq);
                        }

                        bean.setRejectedList(rejectedList);
                }

                request.setAttribute("totalPageForRejected", totalPageForRejected);
                request.setAttribute("pageNoForRejected", pageNoForRejected);
        }

        /**
         * Method to get list of send back applications.
         * 
         * @param bean - Used to get userEmpId, pageForSendBack, used to set sendBackList
         * @param request - Used to set totalPageForSendBack and pageNoForSendBack in attribute
         */
        public void getSendBackList(final ApplicationSecurityRequest bean, final HttpServletRequest request) {

                final String sendBackListSql = "SELECT APPSEC.APPLN_SEC_ID, APPLN_SEC_TRACKING_NO, (OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME) AS MANAGER_NAME ,NVL(APPLN_SEC_EMP_TOKEN,' '),NVL(APPLN_SEC_EMP_NAME,' ') AS EMPLOYEE_NAME, " +
                        "TO_CHAR(APPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, " +
                        " DECODE(APPLN_SEC_REQ_TYPE,'A','Add', 'C','Change', 'D','Delete') " +
                        " FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) " +
                        "WHERE APPSEC.APPLN_SEC_STATUS = 'B' AND APPSEC.APPLN_SEC_INITIATOR = " + bean.getUserEmpId() + " ORDER BY APPSEC.APPLN_SEC_REQ_DATE DESC , MANAGER_NAME ASC , EMPLOYEE_NAME ASC";
                final Object [][] sendBackListObj = this.getSqlModel().getSingleResult(sendBackListSql);

                int totalPageForSendBack = 0;
                int pageNoForSendBack = 0;

                if (sendBackListObj != null && sendBackListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForSendBack(), sendBackListObj.length, 20);

                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        totalPageForSendBack = Integer.parseInt(String.valueOf(pageIndex [2]));
                        pageNoForSendBack = Integer.parseInt(String.valueOf(pageIndex [3]));

                        final List<ApplicationSecurityRequest> sendBackList = new ArrayList<ApplicationSecurityRequest>(Integer.parseInt(pageIndex [1]));

                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ApplicationSecurityRequest applnSecReq = new ApplicationSecurityRequest();
                                applnSecReq.setApplnSecReqId(this.checkNull(String.valueOf(sendBackListObj [i] [0])));
                                applnSecReq.setTrackingNo(this.checkNull(String.valueOf(sendBackListObj [i] [1])));
                                applnSecReq.setMgrName(this.checkNull(String.valueOf(sendBackListObj [i] [2])));
                                applnSecReq.setEmpToken(this.checkNull(String.valueOf(sendBackListObj [i] [3])));
                                applnSecReq.setEmpIniName(this.checkNull(String.valueOf(sendBackListObj [i] [4])));
                                applnSecReq.setRequestDate(this.checkNull(String.valueOf(sendBackListObj [i] [5])));
                                applnSecReq.setRequestTypeItr(this.checkNull(String.valueOf(sendBackListObj [i] [6])));

                                sendBackList.add(applnSecReq);
                        }

                        bean.setSendBackList(sendBackList);
                }

                request.setAttribute("totalPageForSendBack", totalPageForSendBack);
                request.setAttribute("pageNoForSendBack", pageNoForSendBack);
        }

        /**
         * Method to get tracking number.
         * 
         * @param empId - Used in model.generateApplicationCode()
         * @return - trackingNo
         */
        private String getTrackingNo(final String empId) {

                String trackingNo = "";

                final Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                final int day = cal.get(Calendar.DAY_OF_MONTH);
                final int month = cal.get(Calendar.MONTH) + 1;
                final int year = cal.get(Calendar.YEAR);

                final String strDay = day < 10 ? "0" + day : String.valueOf(day);
                final String strMonth = month < 10 ? "0" + month : String.valueOf(month);
                final String strYear = String.valueOf(year);
                final String dash = "-";
                final String sysDate = strDay + dash + strMonth + dash + strYear;

                try {
                        final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
                        model.initiate(context, session);
                        trackingNo = model.generateApplicationCode("", "D1-APPLN_SEC_REQ", empId, sysDate);

                        model.terminate();
                } catch (final Exception e) {
                        ApplicationSecurityRequestModel.logger.error(e);
                }

                return trackingNo;
        }

        /**
         * Method to insert approver comments.
         * 
         * @param bean - Used to get approver comments and application id
         * @param statusToUpdate - Contains status to update
         * @return - true/false, whether record saved successfully or not
         */
        private boolean insertApproverComments(final ApplicationSecurityRequest bean, final String statusToUpdate) {

                final String insertSql = "INSERT INTO HRMS_D1_APPLN_SECURITY_PATH (PATH_ID, PATH_APPROVER, PATH_COMMENTS, PATH_STATUS, APPLN_SEC_ID) " +
                        "VALUES ((SELECT NVL(MAX(PATH_ID), 0) + 1 FROM HRMS_D1_APPLN_SECURITY_PATH), ?, ?, ?, ?)";
                
                final Object [][] insertObj = new Object [1] [4];
                insertObj [0] [0] = this.checkNull(bean.getUserEmpId());
                insertObj [0] [1] = this.checkNull(bean.getApproverComments());
                insertObj [0] [2] = statusToUpdate;
                insertObj [0] [3] = this.checkNull(bean.getApplicationId());

                return this.getSqlModel().singleExecute(insertSql, insertObj);
        }

        /**
         * Method to check whether current user is an IT approver or not.
         * 
         * @param userId - Current user
         * @return - true/false, whether current user is an IT approver or not
         */
        public boolean isUserAITApprover(final String userId) {
                final String itApproverSql = "SELECT * FROM HRMS_D1_APPROVAL_SETTINGS " +
                        "WHERE APP_SETTINGS_TYPE = 'K' AND APP_SETTINGS_EMP_ID = " + userId;
                final Object [][] itApproverObj = this.getSqlModel().getSingleResult(itApproverSql);

                if (itApproverObj != null && itApproverObj.length > 0) { return true; }

                return false;
        }

        /**
         * Method to populate application details.
         * 
         * @param applnDetailsObj - Contains application data
         * @param bean - Used to set application data
         */
        private void populateApplicationDetails(final Object [][] applnDetailsObj, final ApplicationSecurityRequest bean) {

                bean.setApplicationId(this.checkNull(String.valueOf(applnDetailsObj [0] [0])));
                bean.setRequestDate(this.checkNull(String.valueOf(applnDetailsObj [0] [1])));
                bean.setRequestType(this.checkNull(String.valueOf(applnDetailsObj [0] [2])));
                bean.setStatus(this.checkNull(String.valueOf(applnDetailsObj [0] [3])));
                System.out.println("applnDetailsObj [0] [3] -------- "+ applnDetailsObj [0][3]);
                bean.setMgrId(this.checkNull(String.valueOf(applnDetailsObj [0] [4])));
                bean.setMgrToken(this.checkNull(String.valueOf(applnDetailsObj [0] [5])));
                bean.setMgrName(this.checkNull(String.valueOf(applnDetailsObj [0] [6])));
                bean.setMgrDivision(this.checkNull(String.valueOf(applnDetailsObj [0] [7])));
                bean.setMgrDesignation(this.checkNull(String.valueOf(applnDetailsObj [0] [8])));
                bean.setMgrDepartment(this.checkNull(String.valueOf(applnDetailsObj [0] [9])));
                bean.setMgrCity(this.checkNull(String.valueOf(applnDetailsObj [0] [10])));
                bean.setMgrState(this.checkNull(String.valueOf(applnDetailsObj [0] [11])));
                bean.setMgrPincode(this.checkNull(String.valueOf(applnDetailsObj [0] [12])));
                bean.setMgrWorkPhone(this.checkNull(String.valueOf(applnDetailsObj [0] [13])));
                bean.setMgrExt(this.checkNull(String.valueOf(applnDetailsObj [0] [14])));
                bean.setMgrFax(this.checkNull(String.valueOf(applnDetailsObj [0] [15])));
                bean.setMgrEmail(this.checkNull(String.valueOf(applnDetailsObj [0] [16])));
                bean.setEmpFileRequest(this.checkNull(String.valueOf(applnDetailsObj [0] [17])));
                bean.setAddedFile(this.checkNull(String.valueOf(applnDetailsObj [0] [18])));
                // bean.setEmpId(this.checkNull(String.valueOf(applnDetailsObj[0][19])));
                bean.setEmpToken(this.checkNull(String.valueOf(applnDetailsObj [0] [19])));
                bean.setEmpName(this.checkNull(String.valueOf(applnDetailsObj [0] [20])));
                bean.setEmpDesignation(this.checkNull(String.valueOf(applnDetailsObj [0] [21])));
                bean.setHdnEmpType(this.checkNull(String.valueOf(applnDetailsObj [0] [22])));
                bean.setEmpExpDate(this.checkNull(String.valueOf(applnDetailsObj [0] [23])));
                bean.setCopyMgrDiv(this.checkNull(String.valueOf(applnDetailsObj [0] [24])));
                bean.setCopyMgrDept(this.checkNull(String.valueOf(applnDetailsObj [0] [25])));
                bean.setCopyMgrCity(this.checkNull(String.valueOf(applnDetailsObj [0] [26])));
                bean.setCopyMgrState(this.checkNull(String.valueOf(applnDetailsObj [0] [27])));
                bean.setCopyMgrPincode(this.checkNull(String.valueOf(applnDetailsObj [0] [28])));
                bean.setCopyMgrEmail(this.checkNull(String.valueOf(applnDetailsObj [0] [29])));
                bean.setCopyMgrWorkPhone(this.checkNull(String.valueOf(applnDetailsObj [0] [30])));
                bean.setCopyMgrExt(this.checkNull(String.valueOf(applnDetailsObj [0] [31])));
                bean.setCopyMgrFax(this.checkNull(String.valueOf(applnDetailsObj [0] [32])));
                bean.setHdnApplications(this.checkNull(String.valueOf(applnDetailsObj [0] [33])));
                bean.setAsteaWorkgroup(this.checkNull(String.valueOf(applnDetailsObj [0] [34])));
                bean.setAsteaFieldRole(this.checkNull(String.valueOf(applnDetailsObj [0] [35])));
                bean.setAsteaFieldDefaultWarehouse(this.checkNull(String.valueOf(applnDetailsObj [0] [36])));
                bean.setAsteaFinanceRole(this.checkNull(String.valueOf(applnDetailsObj [0] [37])));
                bean.setAsteaLogisticsRole(this.checkNull(String.valueOf(applnDetailsObj [0] [38])));
                bean.setUnixHost1(this.checkNull(String.valueOf(applnDetailsObj [0] [39])));
                bean.setUnixHost2(this.checkNull(String.valueOf(applnDetailsObj [0] [40])));
                bean.setUnixHost3(this.checkNull(String.valueOf(applnDetailsObj [0] [41])));
                bean.setUnixHost4(this.checkNull(String.valueOf(applnDetailsObj [0] [42])));
                bean.setUnixHostAccess(this.checkNull(String.valueOf(applnDetailsObj [0] [43])));
                bean.setUnixGroup(this.checkNull(String.valueOf(applnDetailsObj [0] [44])));
                bean.setNtHost1(this.checkNull(String.valueOf(applnDetailsObj [0] [45])));
                bean.setNtHost2(this.checkNull(String.valueOf(applnDetailsObj [0] [46])));
                bean.setNtHost3(this.checkNull(String.valueOf(applnDetailsObj [0] [47])));
                bean.setNtHost4(this.checkNull(String.valueOf(applnDetailsObj [0] [48])));
                bean.setNtHostAccess(this.checkNull(String.valueOf(applnDetailsObj [0] [49])));
                bean.setNtGroup(this.checkNull(String.valueOf(applnDetailsObj [0] [50])));
                bean.setFrontDoorAccess(ApplicationSecurityRequestModel.FLAG_Y.equals(this.checkNull(String.valueOf(applnDetailsObj [0] [51]))) ? 
                        ApplicationSecurityRequestModel.FLAG_TRUE : ApplicationSecurityRequestModel.FLAG_FALSE);
                bean.setDataRoomAccess(ApplicationSecurityRequestModel.FLAG_Y.equals(this.checkNull(String.valueOf(applnDetailsObj [0] [52]))) ? 
                        ApplicationSecurityRequestModel.FLAG_TRUE : ApplicationSecurityRequestModel.FLAG_FALSE);
                bean.setPictureIdAccess(ApplicationSecurityRequestModel.FLAG_Y.equals(this.checkNull(String.valueOf(applnDetailsObj [0] [53]))) ? 
                        ApplicationSecurityRequestModel.FLAG_TRUE : ApplicationSecurityRequestModel.FLAG_FALSE);
                bean.setAgency(this.checkNull(String.valueOf(applnDetailsObj [0] [54])));
                bean.setComments(this.checkNull(String.valueOf(applnDetailsObj [0] [55])));
                bean.setInitiatorId(this.checkNull(String.valueOf(applnDetailsObj [0] [56])));
                bean.setInitiatorName(this.checkNull(String.valueOf(applnDetailsObj [0] [57])));
                bean.setCompletedOn(this.checkNull(String.valueOf(applnDetailsObj [0] [1])));
                bean.setTrackingNo(this.checkNull(String.valueOf(applnDetailsObj [0] [58])));
        }

        /**
         * Method to populate application data to save.
         * 
         * @param bean - Used to get application data
         * @param applicationId - Used 
         * @return Object[][] containing application data to save
         */
        private Object [][] populateApplicationToSave(final ApplicationSecurityRequest bean, final String applicationId) {

                final Object [][] draftObj = new Object [1] [56];

                draftObj [0] [0] = this.checkNull(bean.getRequestDate()); // APPLN_SEC_REQ_DATE
                draftObj [0] [1] = this.checkNull(bean.getRequestType()); // APPLN_SEC_REQ_TYPE
                draftObj [0] [2] = this.checkNull(bean.getMgrId()); // APPLN_SEC_MGR_ID
                draftObj [0] [3] = this.checkNull(bean.getMgrDivision()); // APPLN_SEC_MGR_DIV
                draftObj [0] [4] = this.checkNull(bean.getMgrDesignation()); // APPLN_SEC_MGR_DESGN
                draftObj [0] [5] = this.checkNull(bean.getMgrDepartment()); // APPLN_SEC_MGR_DEPT
                draftObj [0] [6] = this.checkNull(bean.getMgrCity()); // APPLN_SEC_MGR_CITY
                draftObj [0] [7] = this.checkNull(bean.getMgrState()); // APPLN_SEC_MGR_STATE
                draftObj [0] [8] = this.checkNull(bean.getMgrPincode()); // APPLN_SEC_MGR_PIN
                draftObj [0] [9] = this.checkNull(bean.getMgrWorkPhone()); // APPLN_SEC_MGR_WPHONE
                draftObj [0] [10] = this.checkNull(bean.getMgrExt()); // APPLN_SEC_MGR_EXT
                draftObj [0] [11] = this.checkNull(bean.getMgrFax()); // APPLN_SEC_MGR_FAX
                draftObj [0] [12] = this.checkNull(bean.getMgrEmail()); // APPLN_SEC_MGR_EMAIL
                draftObj [0] [13] = this.checkNull(bean.getEmpFileRequest()); // APPLN_IS_FILE_ATTACHED
                draftObj [0] [14] = this.checkNull(bean.getAddedFile()); // APPLN_SEC_FILE_NAME
                draftObj [0] [15] = this.checkNull(bean.getEmpId()); // APPLN_SEC_EMP_ID
                draftObj [0] [16] = this.checkNull(bean.getEmpDesignation()); // APPLN_SEC_EMP_DESGN
                draftObj [0] [17] = this.checkNull(bean.getEmpType()); // APPLN_SEC_EMP_TYPE
                draftObj [0] [18] = this.checkNull(bean.getEmpExpDate()); // APPLN_SEC_EMP_EXP_DATE
                draftObj [0] [19] = this.checkNull(bean.getCopyMgrDiv()); // APPLN_SEC_EMP_DIVISION
                draftObj [0] [20] = this.checkNull(bean.getCopyMgrDept()); // APPLN_SEC_EMP_DEPARTMENT
                draftObj [0] [21] = this.checkNull(bean.getCopyMgrCity()); // APPLN_SEC_EMP_CITY
                draftObj [0] [22] = this.checkNull(bean.getCopyMgrState()); // APPLN_SEC_EMP_STATE
                draftObj [0] [23] = this.checkNull(bean.getCopyMgrPincode()); // APPLN_SEC_EMP_PIN
                draftObj [0] [24] = this.checkNull(bean.getCopyMgrEmail()); // APPLN_SEC_EMP_EMAIL
                draftObj [0] [25] = this.checkNull(bean.getCopyMgrWorkPhone()); // APPLN_SEC_EMP_WORKPHONE
                draftObj [0] [26] = this.checkNull(bean.getCopyMgrExt()); // APPLN_SEC_EMP_EXTENSION
                draftObj [0] [27] = this.checkNull(bean.getCopyMgrFax()); // APPLN_SEC_EMP_FAX
                draftObj [0] [28] = this.checkNull(bean.getHdnApplications()); // APPLN_SEC_APPLICATIONS
                draftObj [0] [29] = this.checkNull(bean.getAsteaWorkgroup()); // APPLN_SEC_ASTEA_WORKGRP
                draftObj [0] [30] = this.checkNull(bean.getAsteaFieldRole()); // APPLN_SEC_ASTEA_FLD_ROLE
                draftObj [0] [31] = this.checkNull(bean.getAsteaFieldDefaultWarehouse()); // APPLN_SEC_ASTEA_FLD_WH
                draftObj [0] [32] = this.checkNull(bean.getAsteaFinanceRole()); // APPLN_SEC_ASTEA_FIN_ROLE
                draftObj [0] [33] = this.checkNull(bean.getAsteaLogisticsRole()); // APPLN_SEC_ASTEA_LGS_ROLE
                draftObj [0] [34] = this.checkNull(bean.getUnixHost1()); // APPLN_SEC_UNIX_HOST_1
                draftObj [0] [35] = this.checkNull(bean.getUnixHost2()); // APPLN_SEC_UNIX_HOST_2
                draftObj [0] [36] = this.checkNull(bean.getUnixHost3()); // APPLN_SEC_UNIX_HOST_3
                draftObj [0] [37] = this.checkNull(bean.getUnixHost4()); // APPLN_SEC_UNIX_HOST_4
                draftObj [0] [38] = this.checkNull(bean.getUnixHostAccess()); // APPLN_SEC_UNIX_HOST_ACCESS
                draftObj [0] [39] = this.checkNull(bean.getUnixGroup()); // APPLN_SEC_UNIX_GROUP
                draftObj [0] [40] = this.checkNull(bean.getNtHost1()); // APPLN_SEC_NT_HOST_1
                draftObj [0] [41] = this.checkNull(bean.getNtHost2()); // APPLN_SEC_NT_HOST_2
                draftObj [0] [42] = this.checkNull(bean.getNtHost3()); // APPLN_SEC_NT_HOST_3
                draftObj [0] [43] = this.checkNull(bean.getNtHost4()); // APPLN_SEC_NT_HOST_4
                draftObj [0] [44] = this.checkNull(bean.getNtHostAccess()); // APPLN_SEC_NT_HOST_ACCESS
                draftObj [0] [45] = this.checkNull(bean.getNtGroup()); // APPLN_SEC_NT_GROUP
                draftObj [0] [46] = this.checkNull(bean.getFrontDoorAccess()).equals(ApplicationSecurityRequestModel.FLAG_TRUE) ? 
                        ApplicationSecurityRequestModel.FLAG_Y : ApplicationSecurityRequestModel.FLAG_N; // APPLN_SEC_FD_ACCESS
                draftObj [0] [47] = this.checkNull(bean.getDataRoomAccess()).equals(ApplicationSecurityRequestModel.FLAG_TRUE) ? 
                        ApplicationSecurityRequestModel.FLAG_Y : ApplicationSecurityRequestModel.FLAG_N; // APPLN_SEC_DR_ACCESS
                draftObj [0] [48] = this.checkNull(bean.getPictureIdAccess()).equals(ApplicationSecurityRequestModel.FLAG_TRUE) ? 
                        ApplicationSecurityRequestModel.FLAG_Y : ApplicationSecurityRequestModel.FLAG_N; // APPLN_SEC_PI_ACCESS
                draftObj [0] [49] = this.checkNull(bean.getAgency()); // APPLN_SEC_AGENCY
                draftObj [0] [50] = this.checkNull(bean.getComments()); // APPLN_SEC_COMMENTS
                draftObj [0] [51] = this.checkNull(bean.getInitiatorId()); // APPLN_SEC_INITIATOR
                draftObj [0] [52] = this.checkNull(bean.getTrackingNo()); // APPLN_SEC_TRACKING_NO
System.out.println("   draftObj [0][52] ======================== " +    draftObj [0][52] );
                // added ganesh
                draftObj [0] [53] = this.checkNull(bean.getEmpToken()); // APPLN_SEC_EMP_TOKEN
                draftObj [0] [54] = this.checkNull(bean.getEmpName()); // APPLN_SEC_EMP_NAME
                draftObj [0] [55] = applicationId; // APPLN_SEC_ID

                return draftObj;
        }

        /**
         * Method to reject application.
         * 
         * @param bean - Used to get application status
         * @return - message
         */
        public String reject(final ApplicationSecurityRequest bean) {

                String message = "Application cannot be rejected!";

                final String applnStatus = bean.getApplnStatus();
                String statusToUpdate = ApplicationSecurityRequestModel.STATUS_REJECTED;

                if (applnStatus.equals(ApplicationSecurityRequestModel.STATUS_CANCELED)) {
                        statusToUpdate = ApplicationSecurityRequestModel.STATUS_CANCELED_REJECTED;
                }

                final String updateSql = "UPDATE HRMS_D1_APPLN_SECURITY SET APPLN_SEC_STATUS = '" + statusToUpdate + "' WHERE APPLN_SEC_ID = " + 
                        bean.getApplicationId();
                this.getSqlModel().singleExecute(updateSql);

                final boolean isSaved = this.insertApproverComments(bean, statusToUpdate);

                if (isSaved) {
                        try {
                                this.sendMailFromApproverToApplicant(bean);
                        } catch (final Exception e) {
                                ApplicationSecurityRequestModel.logger.error(e);
                        }

                        message = "Application rejected successfully!";
                }

                return message;
        }

        /**
         * Method to save application.
         * 
         * @param bean - Used in populateApplicationToSave(), used to applicationId
         * @param applicationId - Used to set applicationId
         * @return - message
         */
        private String save(final ApplicationSecurityRequest bean, final String applicationId) {

                String message = "Application cannot be saved!";

                final String saveSql = "INSERT INTO HRMS_D1_APPLN_SECURITY (APPLN_SEC_REQ_DATE, APPLN_SEC_REQ_TYPE, APPLN_SEC_MGR_ID, APPLN_SEC_MGR_DIV, " +
                        "APPLN_SEC_MGR_DESGN, APPLN_SEC_MGR_DEPT, APPLN_SEC_MGR_CITY, APPLN_SEC_MGR_STATE, APPLN_SEC_MGR_PIN, APPLN_SEC_MGR_WPHONE, " +
                        "APPLN_SEC_MGR_EXT, APPLN_SEC_MGR_FAX, APPLN_SEC_MGR_EMAIL, APPLN_SEC_IS_FILE_ATTACHED, APPLN_SEC_FILE_NAME, APPLN_SEC_EMP_ID, " +
                        "APPLN_SEC_EMP_DESGN, APPLN_SEC_EMP_TYPE, APPLN_SEC_EMP_EXP_DATE, APPLN_SEC_EMP_DIVISION, APPLN_SEC_EMP_DEPARTMENT, " +
                        "APPLN_SEC_EMP_CITY, APPLN_SEC_EMP_STATE, APPLN_SEC_EMP_PIN, APPLN_SEC_EMP_EMAIL, APPLN_SEC_EMP_WORKPHONE, " +
                        "APPLN_SEC_EMP_EXTENSION, APPLN_SEC_EMP_FAX, APPLN_SEC_APPLICATIONS, APPLN_SEC_ASTEA_WORKGRP, APPLN_SEC_ASTEA_FLD_ROLE, " +
                        "APPLN_SEC_ASTEA_FLD_WH, APPLN_SEC_ASTEA_FIN_ROLE, APPLN_SEC_ASTEA_LGS_ROLE, APPLN_SEC_UNIX_HOST_1, APPLN_SEC_UNIX_HOST_2, " +
                        "APPLN_SEC_UNIX_HOST_3, APPLN_SEC_UNIX_HOST_4, APPLN_SEC_UNIX_HOST_ACCESS, APPLN_SEC_UNIX_GROUP, APPLN_SEC_NT_HOST_1, " +
                        "APPLN_SEC_NT_HOST_2, APPLN_SEC_NT_HOST_3, APPLN_SEC_NT_HOST_4, APPLN_SEC_NT_HOST_ACCESS, APPLN_SEC_NT_GROUP, " +
                        "APPLN_SEC_FD_ACCESS, APPLN_SEC_DR_ACCESS, APPLN_SEC_PI_ACCESS, APPLN_SEC_AGENCY, APPLN_SEC_COMMENTS, APPLN_SEC_INITIATOR, " +
                        "APPLN_SEC_TRACKING_NO,APPLN_SEC_EMP_TOKEN, APPLN_SEC_EMP_NAME, APPLN_SEC_ID) " +
                        "VALUES (TO_DATE(?, 'DD-MM-YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'DD-MM-YYYY'), ?, ?, ?, ?, ?, " +
                        "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";

                final Object [][] draftObj = this.populateApplicationToSave(bean, applicationId);
                final boolean isSaved = this.getSqlModel().singleExecute(saveSql, draftObj);

                if (isSaved) {
                        message = "Application saved successfully!";
                        bean.setApplicationId(applicationId);
                }

                return message;
        }

        /**
         * Method to send back the application.
         * 
         * @param bean - Used in insertApproverComments()
         * @return - message
         */
        public String sendBack(final ApplicationSecurityRequest bean) {

                String message = "Application cannot be sent back!";
                final String statusToUpdate = ApplicationSecurityRequestModel.STATUS_SEND_BACK;

                final String updateSql = "UPDATE HRMS_D1_APPLN_SECURITY SET APPLN_SEC_STATUS = '" + statusToUpdate + "' WHERE APPLN_SEC_ID = " + 
                        bean.getApplicationId();
                this.getSqlModel().singleExecute(updateSql);

                final boolean isSaved = this.insertApproverComments(bean, statusToUpdate);

                if (isSaved) {
                        try {
                                this.sendMailFromApproverToApplicant(bean);
                        } catch (final Exception e) {
                                ApplicationSecurityRequestModel.logger.error(e);
                        }

                        message = "Application sent back successfully!";
                }

                return message;
        }

        /**
         * Method to send application for approval.
         * 
         * @param bean - Used in draft(), sendMailFromApplicantToApprover(), used to get applicationId, trackingNo
         * @return - message
         */
        public String sendForApproval(final ApplicationSecurityRequest bean) {

                String message = "Application cannot be send for approval!";

                this.draft(bean);
                final String applicationId = bean.getApplicationId();

                final String updateStatusSql = "UPDATE HRMS_D1_APPLN_SECURITY SET APPLN_SEC_STATUS = 'P' WHERE APPLN_SEC_ID = " + applicationId;
                final boolean isUpdated = this.getSqlModel().singleExecute(updateStatusSql);

                if (isUpdated) {
                        try {
                                this.sendMailFromApplicantToApprover(bean);
                        } catch (final Exception e) {
                                ApplicationSecurityRequestModel.logger.error(e);
                        }

                        // message = "Application successfully send for approval!";

                        final String trackCOde = bean.getTrackingNo();
                        message = "Application send for approval successfully \n Tracking No: " + trackCOde;
                }

                return message;
        }

        /**
         * Method to send mail from applicant to approver.
         * 
         * @param bean - Used to get userEmpId, applicationId, dataPath, addedFile
         */
        private void sendMailFromApplicantToApprover(final ApplicationSecurityRequest bean) {

                final String groupMailSql = "SELECT APP_EMAIL_ID FROM HRMS_D1_APPROVAL_SETTINGS " +
                        "WHERE APP_EMAIL_ID IS NOT NULL AND APP_SETTINGS_TYPE = 'K'";
                final Object [][] groupMail = this.getSqlModel().getSingleResult(groupMailSql);

                if (groupMail != null && groupMail.length > 0) {
                        final EmailTemplateBody template = new EmailTemplateBody();
                        template.initiate(context, session);
                        template.setEmailTemplate("D1-APPLICATION SECURITY REQUEST APPLICATION DETAILS TO APPROVER");
                        template.getTemplateQueries();

                        final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
                        templateQuery1.setParameter(1, bean.getUserEmpId());

                        final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
                        templateQuery2.setParameter(1, "0");

                        final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
                        templateQuery3.setParameter(1, bean.getApplicationId());

                        final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
                        final String strApplications = this.getSelectedApplications(bean.getHdnApplications());
                        templateQuery4.setParameter(1, strApplications);
                        templateQuery4.setParameter(2, bean.getApplicationId());
                        
                        final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
                        templateQuery5.setParameter(1, bean.getApplicationId());
                        
                        final EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
                        templateQuery6.setParameter(1, bean.getApplicationId());
                        
                        template.configMailAlert();
                        // template.sendApplMailWithAttachment(new
                        // String[]{bean.getDataPath() + bean.getAddedFile()});
                        // template.sendApplicationMailToGroups(groupMail);
                        final String path = bean.getDataPath();
                        final String fileanme = checkNull(bean.getAddedFile());
                        
                        if (groupMail != null && groupMail.length > 0) {
                                final String [] attachment = new String [1];
                                attachment [0] = path + fileanme;
                                
                                if (!"".equals(fileanme.trim())) {
                                        template.sendApplicationMailToGroups(groupMail, attachment);
                                } else {
                                        template.sendApplicationMailToGroups(groupMail);
                                }
                        }

                        template.clearParameters();
                        template.terminate();
                }
        }

        /**
         * Method to send mail from approver to applicant.
         * 
         * @param bean - Used to get userEmpId, mgrId, applicationId, dataPath, empFile, initiatorId
         */
        private void sendMailFromApproverToApplicant(final ApplicationSecurityRequest bean) {

                final String groupMailSql = "SELECT APP_EMAIL_ID FROM HRMS_D1_APPROVAL_SETTINGS " +
                        "WHERE APP_EMAIL_ID IS NOT NULL AND APP_SETTINGS_TYPE = 'K'";
                final Object [][] groupMail = this.getSqlModel().getSingleResult(groupMailSql);

                final EmailTemplateBody template = new EmailTemplateBody();
                template.initiate(context, session);
                template.setEmailTemplate("D1-APPLICATION SECURITY REQUEST APPLICATION DETAILS APPROVE/REJECT/SEND BACK FROM APPROVER");
                template.getTemplateQueries();

                final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
                templateQuery1.setParameter(1, bean.getUserEmpId());

                final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
                templateQuery2.setParameter(1, bean.getMgrId());

                final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
                templateQuery3.setParameter(1, bean.getApplicationId());

                final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4); // IT_APPROVER_NAME
                templateQuery4.setParameter(1, bean.getUserEmpId());

                final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
                final String strApplications = this.getSelectedApplications(bean.getHdnApplications());
                templateQuery5.setParameter(1, strApplications);
                templateQuery5.setParameter(2, bean.getApplicationId());

                final EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
                templateQuery6.setParameter(1, bean.getApplicationId());
                
                final EmailTemplateQuery templateQuery7= template.getTemplateQuery(7);
                templateQuery7.setParameter(1, bean.getApplicationId());
                
                final EmailTemplateQuery templateQuery8= template.getTemplateQuery(8);
                templateQuery8.setParameter(1, bean.getApplicationId());
                

                template.configMailAlert();
                // template.sendApplMailWithAttachment(new String[]{bean.getDataPath() +
                // bean.getAddedFile()});
                /*
                 * template.sendApplicationMail(); template.sendApplicationMailToGroups(groupMail);
                 * template.sendApplicationMailToKeepInfo(bean.getInitiatorId());
                 */
                final String path = bean.getDataPath();
                final String fileanme = checkNull(bean.getAddedFile());

                if (!"".equals(fileanme)) {
                        final String [] attachment = new String [1];
                        attachment [0] = path + fileanme;
                        template.sendApplMailWithAttachment(attachment);
                } else {
                        template.sendApplicationMail();
                }
                // template.sendApplicationMailToGroups(groupMail);

                /*final String [] attachment = new String [1];
                attachment [0] = path + fileanme;
                if (groupMail != null && groupMail.length > 0) {

                        template.sendApplicationMailToGroups(groupMail, attachment);
                }*/
                
                
                if (groupMail != null && groupMail.length > 0) {
                        if (!"".equals(fileanme.trim())) {
                        	final String [] attachment = new String [1];
                            attachment [0] = path + fileanme;
                                template.sendApplicationMailToGroups(groupMail, attachment);
                        } else {
                                template.sendApplicationMailToGroups(groupMail);
                        }
                }

                final String [] keepAttach = new String [1];
                keepAttach [0] = bean.getInitiatorId();
               // template.sendApplMailWithAttachmentToKeepInf(keepAttach, attachment);
                if (!"".equals(fileanme.trim())) {
                	final String [] attachment = new String [1];
                    attachment [0] = path + fileanme;
                    template.sendApplMailWithAttachmentToKeepInf(keepAttach, attachment);
            } else {
                    template.sendApplMailWithAttachmentToKeepInf(keepAttach,null);
            }
                template.clearParameters();
                template.terminate();
        }

        /**
         * Method to set application details.
         * 
         * @param applnSecReqId - Use to get details of application
         * @param bean - Used in populateApplicationDetails()
         */
        public void setApplicationDetails(final String applnSecReqId, final ApplicationSecurityRequest bean) {

                final String applnDetailsSql = "SELECT APPLN_SEC_ID, TO_CHAR(APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, APPLN_SEC_REQ_TYPE, " +
                        "APPLN_SEC_STATUS, OFFC_MGR.EMP_ID AS MGR_ID, OFFC_MGR.EMP_TOKEN AS MGR_TOKEN, OFFC_MGR.EMP_FNAME || ' ' || " +
                        "OFFC_MGR.EMP_MNAME || ' ' || OFFC_MGR.EMP_LNAME AS MGR_NAME, APPLN_SEC_MGR_DIV, APPLN_SEC_MGR_DESGN, APPLN_SEC_MGR_DEPT, " +
                        "APPLN_SEC_MGR_CITY, APPLN_SEC_MGR_STATE, APPLN_SEC_MGR_PIN, APPLN_SEC_MGR_WPHONE, APPLN_SEC_MGR_EXT, APPLN_SEC_MGR_FAX, " +
                        "APPLN_SEC_MGR_EMAIL, APPLN_SEC_IS_FILE_ATTACHED, APPLN_SEC_FILE_NAME, APPLN_SEC_EMP_TOKEN , APPLN_SEC_EMP_NAME, " +
                        "APPLN_SEC_EMP_DESGN, APPLN_SEC_EMP_TYPE, TO_CHAR(APPLN_SEC_EMP_EXP_DATE, 'DD-MM-YYYY') AS APPLN_SEC_EMP_EXP_DATE, " +
                        "APPLN_SEC_EMP_DIVISION, APPLN_SEC_EMP_DEPARTMENT, APPLN_SEC_EMP_CITY, APPLN_SEC_EMP_STATE, APPLN_SEC_EMP_PIN, " +
                        "APPLN_SEC_EMP_EMAIL, APPLN_SEC_EMP_WORKPHONE, APPLN_SEC_EMP_EXTENSION, APPLN_SEC_EMP_FAX, APPLN_SEC_APPLICATIONS, " +
                        "APPLN_SEC_ASTEA_WORKGRP, APPLN_SEC_ASTEA_FLD_ROLE, APPLN_SEC_ASTEA_FLD_WH, APPLN_SEC_ASTEA_FIN_ROLE, " +
                        "APPLN_SEC_ASTEA_LGS_ROLE, APPLN_SEC_UNIX_HOST_1, APPLN_SEC_UNIX_HOST_2, APPLN_SEC_UNIX_HOST_3, APPLN_SEC_UNIX_HOST_4, " +
                        "APPLN_SEC_UNIX_HOST_ACCESS, APPLN_SEC_UNIX_GROUP, APPLN_SEC_NT_HOST_1, APPLN_SEC_NT_HOST_2, APPLN_SEC_NT_HOST_3, " +
                        "APPLN_SEC_NT_HOST_4, APPLN_SEC_NT_HOST_ACCESS, APPLN_SEC_NT_GROUP, APPLN_SEC_FD_ACCESS, APPLN_SEC_DR_ACCESS, " +
                        "APPLN_SEC_PI_ACCESS, APPLN_SEC_AGENCY, APPLN_SEC_COMMENTS, APPLN_SEC_INITIATOR, INITIATOR.EMP_TOKEN || ' ' || " +
                        "INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, APPLN_SEC_TRACKING_NO " +
                        "FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC_MGR ON(OFFC_MGR.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) " +
                        //"LEFT JOIN HRMS_EMP_OFFC OFFC_EMP ON(OFFC_EMP.EMP_ID = APPSEC.APPLN_SEC_EMP_ID) "
                        "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = APPSEC.APPLN_SEC_INITIATOR) WHERE APPLN_SEC_ID = " + applnSecReqId;
                final Object [][] applnDetailsObj = this.getSqlModel().getSingleResult(applnDetailsSql);

                this.populateApplicationDetails(applnDetailsObj, bean);
        }

        /**
         * Method to set application details for approval.
         * 
         * @param applnSecReqId - Use to get details of application
         * @param bean - Used in populateApplicationDetails()
         */
        public void setApplicationDetailsForApproval(final String applnSecReqId, final ApplicationSecurityRequest bean) {

                final String applnDetailsSql = "SELECT APPLN_SEC_ID, TO_CHAR(APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE, " +
                        "DECODE(APPLN_SEC_REQ_TYPE, 'A', 'Add', 'C', 'Change', 'D', 'Delete') AS REQ_TYPE, DECODE(APPLN_SEC_STATUS, 'D', 'Draft', " +
                        "'P', 'Pending', 'B', 'Sent Back', 'A', 'Approved', 'R', 'Rejected', 'F', 'Forwarded', 'C', 'Applied For Cancellation', " +
                        "'X', 'Cancellation Approved', 'Z', 'Cancellation Rejected') AS STATUS, OFFC_MGR.EMP_ID AS MGR_ID, " +
                        "OFFC_MGR.EMP_TOKEN AS MGR_TOKEN, OFFC_MGR.EMP_FNAME || ' ' || OFFC_MGR.EMP_MNAME || ' ' || OFFC_MGR.EMP_LNAME AS MGR_NAME, " +
                        "APPLN_SEC_MGR_DIV, APPLN_SEC_MGR_DESGN, APPLN_SEC_MGR_DEPT, APPLN_SEC_MGR_CITY, APPLN_SEC_MGR_STATE, APPLN_SEC_MGR_PIN, " +
                        "APPLN_SEC_MGR_WPHONE, APPLN_SEC_MGR_EXT, APPLN_SEC_MGR_FAX, APPLN_SEC_MGR_EMAIL, DECODE(APPLN_SEC_IS_FILE_ATTACHED, " +
                        "'Y', 'Yes', 'N', 'No') AS IS_FILE_ATTACHED, APPLN_SEC_FILE_NAME, APPLN_SEC_EMP_TOKEN, APPLN_SEC_EMP_NAME, " +
                        "APPLN_SEC_EMP_DESGN, DECODE(APPLN_SEC_EMP_TYPE, 'R', 'Regular', 'T', 'Temp/Contractor', 'V', 'VWE') AS APPLN_SEC_EMP_TYPE, " +
                        "TO_CHAR(APPLN_SEC_EMP_EXP_DATE, 'DD-MM-YYYY') AS APPLN_SEC_EMP_EXP_DATE, APPLN_SEC_EMP_DIVISION, APPLN_SEC_EMP_DEPARTMENT, " +
                        "APPLN_SEC_EMP_CITY, APPLN_SEC_EMP_STATE, APPLN_SEC_EMP_PIN, APPLN_SEC_EMP_EMAIL, APPLN_SEC_EMP_WORKPHONE, " +
                        "APPLN_SEC_EMP_EXTENSION, APPLN_SEC_EMP_FAX, APPLN_SEC_APPLICATIONS, APPLN_SEC_ASTEA_WORKGRP, APPLN_SEC_ASTEA_FLD_ROLE, " +
                        "APPLN_SEC_ASTEA_FLD_WH, APPLN_SEC_ASTEA_FIN_ROLE, APPLN_SEC_ASTEA_LGS_ROLE, APPLN_SEC_UNIX_HOST_1, APPLN_SEC_UNIX_HOST_2, " +
                        "APPLN_SEC_UNIX_HOST_3, APPLN_SEC_UNIX_HOST_4, APPLN_SEC_UNIX_HOST_ACCESS, APPLN_SEC_UNIX_GROUP, APPLN_SEC_NT_HOST_1, " +
                        "APPLN_SEC_NT_HOST_2, APPLN_SEC_NT_HOST_3, APPLN_SEC_NT_HOST_4, APPLN_SEC_NT_HOST_ACCESS, APPLN_SEC_NT_GROUP, " +
                        "APPLN_SEC_FD_ACCESS, APPLN_SEC_DR_ACCESS, APPLN_SEC_PI_ACCESS, APPLN_SEC_AGENCY, APPLN_SEC_COMMENTS, APPLN_SEC_INITIATOR, " +
                        "INITIATOR.EMP_TOKEN || ' ' || INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS " +
                        "INITIATOR_NAME, APPLN_SEC_TRACKING_NO FROM HRMS_D1_APPLN_SECURITY APPSEC " +
                        "INNER JOIN HRMS_EMP_OFFC OFFC_MGR ON(OFFC_MGR.EMP_ID = APPSEC.APPLN_SEC_MGR_ID) " +
                        // + "LEFT JOIN HRMS_EMP_OFFC OFFC_EMP ON(OFFC_EMP.EMP_ID = APPSEC.APPLN_SEC_EMP_ID) "
                        "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = APPSEC.APPLN_SEC_INITIATOR) WHERE APPLN_SEC_ID = " + applnSecReqId;
                final Object [][] applnDetailsObj = this.getSqlModel().getSingleResult(applnDetailsSql);

                if (applnDetailsObj != null && applnDetailsObj.length > 0) {
                        this.populateApplicationDetails(applnDetailsObj, bean);
                }
        }

        /**
         * Method to set list of approver comments.
         * 
         * @param bean - Used to get applicationId, used to set approver comment list
         */
        public void setApproverCommentsList(final ApplicationSecurityRequest bean) {

                final String apprCommentsListSql = "SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS APPROVER_NAME, " +
                        "PATH_COMMENTS, TO_CHAR(PATH_APPROVAL_DATE, 'DD-MM-YYYY') AS APPROVAL_DATE, DECODE(PATH_STATUS, 'D', 'Draft', 'P', 'Pending', " +
                        "'B', 'Sent Back', 'A', 'Approved', 'R', 'Rejected', 'F', 'Forwarded', 'C', 'Applied For Cancellation', 'X', " +
                        "'Cancellation Approved', 'Z', 'Cancellation Rejected') AS STATUS FROM HRMS_D1_APPLN_SECURITY_PATH PATH " +
                        "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = PATH.PATH_APPROVER) WHERE PATH.APPLN_SEC_ID = " + bean.getApplicationId() +
                        " ORDER BY PATH.PATH_APPROVAL_DATE DESC , PATH.PATH_APPROVER DESC ";

                final Object [][] apprCommentsListObj = this.getSqlModel().getSingleResult(apprCommentsListSql);

                if (apprCommentsListObj != null && apprCommentsListObj.length > 0) {
                        final List<ApplicationSecurityRequest> approverCommentsList = 
                                new ArrayList<ApplicationSecurityRequest>(apprCommentsListObj.length);

                        for (int i = 0; i < apprCommentsListObj.length; i++) {
                                final ApplicationSecurityRequest applnSecReqBean = new ApplicationSecurityRequest();
                                applnSecReqBean.setApprName(this.checkNull(String.valueOf(apprCommentsListObj [i] [0])));
                                applnSecReqBean.setApprComments(this.checkNull(String.valueOf(apprCommentsListObj [i] [1])));
                                applnSecReqBean.setApprDate(this.checkNull(String.valueOf(apprCommentsListObj [i] [2])));
                                applnSecReqBean.setApprStatus(this.checkNull(String.valueOf(apprCommentsListObj [i] [3])));
                                approverCommentsList.add(applnSecReqBean);
                        }

                        bean.setApproverCommentsList(approverCommentsList);
                }
        }

        
        /**
         * Method to set list of approver comments.
         * 
         * @param bean - Used to get applicationId, used to set approver comment list
         */
        public void setSearchApproverCommentsList(final ApplicationSecurityRequest bean) {

                final String apprCommentsListSql = "SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS APPROVER_NAME, " +
                        "PATH_COMMENTS, TO_CHAR(PATH_APPROVAL_DATE, 'DD-MM-YYYY') AS APPROVAL_DATE, DECODE(PATH_STATUS, 'D', 'Draft', 'P', 'Pending', " +
                        "'B', 'Sent Back', 'A', 'Approved', 'R', 'Rejected', 'F', 'Forwarded', 'C', 'Applied For Cancellation', 'X', " +
                        "'Cancellation Approved', 'Z', 'Cancellation Rejected') AS STATUS FROM HRMS_D1_APPLN_SECURITY_PATH PATH " +
                        "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = PATH.PATH_APPROVER) WHERE PATH.APPLN_SEC_ID = " + bean.getApplicationId() +
                        " ORDER BY PATH.PATH_APPROVAL_DATE DESC, PATH.PATH_APPROVER DESC ";

                final Object [][] apprCommentsListObj = this.getSqlModel().getSingleResult(apprCommentsListSql);

                if (apprCommentsListObj != null && apprCommentsListObj.length > 0) {
                        final List<ApplicationSecurityRequest> approverCommentsList = 
                                new ArrayList<ApplicationSecurityRequest>(apprCommentsListObj.length);

                        for (int i = 0; i < apprCommentsListObj.length; i++) {
                                final ApplicationSecurityRequest applnSecReqBean = new ApplicationSecurityRequest();
                                applnSecReqBean.setApprName(this.checkNull(String.valueOf(apprCommentsListObj [i] [0])));
                                applnSecReqBean.setApprComments(this.checkNull(String.valueOf(apprCommentsListObj [i] [1])));
                                applnSecReqBean.setApprDate(this.checkNull(String.valueOf(apprCommentsListObj [i] [2])));
                                applnSecReqBean.setStatus(this.checkNull(String.valueOf(apprCommentsListObj [i] [3])));
                                approverCommentsList.add(applnSecReqBean);
                        }

                        bean.setApproverCommentsList(approverCommentsList);
                }
        }
        
        /**
         * Method to get manager details.
         * 
         * @param mgrId - Used to get manager details
         * @return - Object[][] containing manager data
         */
        public Object [][] setManagerDetails(final String mgrId) {

                final String mgrDetailsSql = "SELECT DIV.DIV_NAME AS DIVISON, RANK.RANK_NAME AS DESIGNATION, DEPT.DEPT_NAME AS DEPARTMENT, " +
                        "CITY.LOCATION_NAME AS CITY, STATE.LOCATION_NAME AS STATE, ADDR.ADD_PINCODE AS PINCODE, ADDR.ADD_PH1 AS WORKPHONE, " +
                        "ADDR.ADD_EXTENSION AS EXTENSION, ADDR.ADD_FAX AS FAX, ADDR.ADD_EMAIL AS EMAIL FROM HRMS_EMP_OFFC OFFC " +
                        "LEFT JOIN HRMS_DIVISION DIV ON(DIV.DIV_ID = OFFC.EMP_DIV) " + 
                        "LEFT JOIN HRMS_RANK RANK ON(RANK.RANK_ID = OFFC.EMP_RANK) " +
                        "LEFT JOIN HRMS_DEPT DEPT ON(DEPT.DEPT_ID = OFFC.EMP_DEPT) " +
                        "LEFT JOIN HRMS_EMP_ADDRESS ADDR ON (ADDR.EMP_ID = OFFC.EMP_ID) " +
                        "LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = ADDR.ADD_CITY AND ADDR.ADD_TYPE = 'P') " +
                        "LEFT JOIN HRMS_LOCATION STATE ON(STATE.LOCATION_CODE = CITY.LOCATION_PARENT_CODE) WHERE OFFC.EMP_ID = " + mgrId;

                return this.getSqlModel().getSingleResult(mgrDetailsSql);
        }

        /**
         * Method to set manager name.
         * 
         * @param bean - Used to get userEmpId, used to set mgeToken, mgrName
         */
        public void setManagerName(final ApplicationSecurityRequest bean) {

                final String managerSql = "SELECT EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME || ' ' || EMP_LNAME AS ENAME, EMP_ID FROM HRMS_EMP_OFFC " +
                        "WHERE EMP_ID = " + bean.getUserEmpId();
                final Object [][] managerObj = this.getSqlModel().getSingleResult(managerSql);

                bean.setMgrToken(this.checkNull(String.valueOf(managerObj [0] [0])));
                bean.setMgrName(this.checkNull(String.valueOf(managerObj [0] [1])));
        }

        /**
         * Method to update application.
         * 
         * @param bean - Used in populateApplicationToSave()
         * @param applicationId - Used in populateApplicationToSave()
         * @return - message
         */
        private String update(final ApplicationSecurityRequest bean, final String applicationId) {

                String message = "Application cannot be modified!";

                final String updateSql = "UPDATE HRMS_D1_APPLN_SECURITY SET APPLN_SEC_REQ_DATE = TO_DATE(?, 'DD-MM-YYYY'), APPLN_SEC_REQ_TYPE = ?, " +
                        "APPLN_SEC_MGR_ID = ?, APPLN_SEC_MGR_DIV = ?, APPLN_SEC_MGR_DESGN = ?, APPLN_SEC_MGR_DEPT = ?, APPLN_SEC_MGR_CITY = ?, " +
                        "APPLN_SEC_MGR_STATE = ?, APPLN_SEC_MGR_PIN = ?, APPLN_SEC_MGR_WPHONE = ?, APPLN_SEC_MGR_EXT = ?, APPLN_SEC_MGR_FAX = ?, " +
                        "APPLN_SEC_MGR_EMAIL = ?, APPLN_SEC_IS_FILE_ATTACHED = ?, APPLN_SEC_FILE_NAME = ?, APPLN_SEC_EMP_ID = ?, " +
                        "APPLN_SEC_EMP_DESGN = ?, APPLN_SEC_EMP_TYPE = ?, APPLN_SEC_EMP_EXP_DATE = TO_DATE(?, 'DD-MM-YYYY'), " +
                        "APPLN_SEC_EMP_DIVISION = ?, APPLN_SEC_EMP_DEPARTMENT = ?, APPLN_SEC_EMP_CITY = ?, APPLN_SEC_EMP_STATE = ?, " +
                        "APPLN_SEC_EMP_PIN = ?, APPLN_SEC_EMP_EMAIL = ?, APPLN_SEC_EMP_WORKPHONE = ?, APPLN_SEC_EMP_EXTENSION = ?, " +
                        "APPLN_SEC_EMP_FAX = ?, APPLN_SEC_APPLICATIONS = ?, APPLN_SEC_ASTEA_WORKGRP = ?, APPLN_SEC_ASTEA_FLD_ROLE = ?, " +
                        "APPLN_SEC_ASTEA_FLD_WH = ?, APPLN_SEC_ASTEA_FIN_ROLE = ?, APPLN_SEC_ASTEA_LGS_ROLE = ?, APPLN_SEC_UNIX_HOST_1 = ?, " +
                        "APPLN_SEC_UNIX_HOST_2 = ?, APPLN_SEC_UNIX_HOST_3 = ?, APPLN_SEC_UNIX_HOST_4 = ?, APPLN_SEC_UNIX_HOST_ACCESS = ?, " +
                        "APPLN_SEC_UNIX_GROUP = ?, APPLN_SEC_NT_HOST_1 = ?, APPLN_SEC_NT_HOST_2 = ?, APPLN_SEC_NT_HOST_3 = ?, APPLN_SEC_NT_HOST_4 = ?, " +
                        "APPLN_SEC_NT_HOST_ACCESS = ?, APPLN_SEC_NT_GROUP = ?, APPLN_SEC_FD_ACCESS = ?, APPLN_SEC_DR_ACCESS = ?, " +
                        "APPLN_SEC_PI_ACCESS = ?, APPLN_SEC_AGENCY = ?, APPLN_SEC_COMMENTS = ?, APPLN_SEC_INITIATOR = ?, APPLN_SEC_TRACKING_NO = ?, " +
                        "APPLN_SEC_EMP_TOKEN = ?, APPLN_SEC_EMP_NAME = ? WHERE APPLN_SEC_ID = ?";

                final Object [][] draftObj = this.populateApplicationToSave(bean, applicationId);
                final boolean isUpdated = this.getSqlModel().singleExecute(updateSql, draftObj);

                if (isUpdated) {
                        message = "Application modified successfully!";
                }

                return message;
        }
}
