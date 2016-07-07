package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.paradyne.bean.D1.ReportRequestChange;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * Bhushan Dasare Mar 17, 2011 Model for report request change application and approval.
 */

public class ReportRequestChangeModel extends ModelBase {

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
        private static Logger logger = Logger.getLogger(ReportRequestChangeModel.class);

        /**
         * Method to do authorized sign off.
         * 
         * @param bean - Used to get status, userEmpId, requestId, used in sendMailForForward(), sendMailForApproveReject()
         * @param isUserAITApprover - Check whether user is
         * @return - message
         */
        public String authorizedSignOff(final ReportRequestChange bean, final boolean isUserAITApprover) {

                String message = "Application cannot be approved!";
                final String status = bean.getStatus();
                String statusToUpdate = ReportRequestChangeModel.STATUS_APPROVED;

                if (!isUserAITApprover) {
                        statusToUpdate = ReportRequestChangeModel.STATUS_FORWARDED;
                } else {
                        if (status.equals(ReportRequestChangeModel.STATUS_PENDING)) {
                                statusToUpdate = ReportRequestChangeModel.STATUS_FORWARDED;
                        }
                }

                final String updateSql = "UPDATE HRMS_D1_REPORT_REQ_CHANGE SET REQ_STATUS = '" + statusToUpdate + "' WHERE REQ_ID = " + 
                        bean.getRequestId();
                this.getSqlModel().singleExecute(updateSql);

                final boolean isSaved = this.insertApproverComments(bean, statusToUpdate);

                if (isSaved) {
                        try {
                                final String prevApproversSql = "SELECT DISTINCT PATH_APPROVER FROM HRMS_D1_REPORT_REQ_PATH WHERE PATH_APPROVER != " + 
                                        bean.getUserEmpId() + " AND PATH_STATUS IN('P', 'F') AND PATH_APPROVER NOT IN (SELECT APP_SETTINGS_EMP_ID " + 
                                        "FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'M' AND APP_SETTINGS_EMP_ID IS NOT NULL) " + 
                                        "AND REQ_ID = " + bean.getRequestId();
                                final Object [][] prevApproversObj = this.getSqlModel().getSingleResult(prevApproversSql);

                                if (statusToUpdate.equals(ReportRequestChangeModel.STATUS_FORWARDED)) {
                                        final String toEmp = "0";
                                        this.sendMailForForward(bean, statusToUpdate, toEmp, prevApproversObj);
                                } else {
                                        this.sendMailForApproveReject(bean, statusToUpdate, prevApproversObj);
                                }
                        } catch (final Exception e) {
                                ReportRequestChangeModel.logger.error(e);
                        }

                        message = "Application approved successfully!";
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

        /**
         * Method to delete application.
         * 
         * @param requestId - Used in delete query
         * @return - message
         */
        public String delete(final String requestId) {

                String message = "Application cannot be deleted!";

                final String deleteSql = "DELETE FROM HRMS_D1_REPORT_REQ_CHANGE WHERE REQ_ID = " + requestId;
                final boolean isDeleted = this.getSqlModel().singleExecute(deleteSql);

                if (isDeleted) {
                        message = "Application deleted successfully!";
                }

                return message;
        }

        /**
         * Method to draft an application.
         * 
         * @param bean - Use to get requestId, userEmpId, used in save(), update(), used to set requestId, trackingNo
         * @return - message
         */
        public String draft(final ReportRequestChange bean) {

                String message = "Application cannot be saved!";
                String requestId = this.checkNull(bean.getRequestId());

                if (bean.isNewRecord()) {
                        requestId = this.getRequestId();
                        bean.setRequestId(requestId);
                        bean.setTrackingNo(this.getTrackingNo(bean.getUserEmpId()));
                        message = this.save(bean, requestId);
                } else {
                        message = this.update(bean, requestId);
                }

                return message;
        }

        /**
         * Method to forward an application.
         * 
         * @param bean - Used to get nextApproverId, userEmpId, used in sendMailForForward()
         * @return - message
         */
        public String forward(final ReportRequestChange bean) {

                String message = "Application cannot be forwarded!";
                final String statusToUpdate = ReportRequestChangeModel.STATUS_PENDING;

                final String updateSql = "UPDATE HRMS_D1_REPORT_REQ_CHANGE SET REQ_APPROVER = " + bean.getNextApproverId() + " WHERE REQ_ID = " + 
                        bean.getRequestId();
                this.getSqlModel().singleExecute(updateSql);

                final boolean isSaved = this.insertApproverComments(bean, statusToUpdate);

                if (isSaved) {
                        try {
                                final String status = ReportRequestChangeModel.STATUS_FORWARDED;
                                final String toEmp = bean.getNextApproverId();

                                final String prevApproversSql = "SELECT DISTINCT PATH_APPROVER FROM HRMS_D1_REPORT_REQ_PATH " + 
                                        "WHERE PATH_APPROVER != " + bean.getUserEmpId() + " AND PATH_STATUS = 'P' AND REQ_ID = " + 
                                        bean.getRequestId();
                                final Object [][] prevApproversObj = this.getSqlModel().getSingleResult(prevApproversSql);

                                this.sendMailForForward(bean, status, toEmp, prevApproversObj);
                        } catch (final Exception e) {
                                ReportRequestChangeModel.logger.error(e);
                        }

                        message = "Application forwarded successfully!";
                }

                return message;
        }

        /**
         * Method to get list of approved applications.
         * 
         * @param bean - Used to get isUserAManager, isUserAITApprover, userEmpId, pageForApproved, used to set approvedList
         * @param request - Used to set totalPageForApproved and pageNoForApproved in attribute
         */
        public void getApprovedList(final ReportRequestChange bean, final HttpServletRequest request) {

                String approvedListSql = "SELECT DISTINCT REQ_ID, REQ_EMP_ID, REQ_TRACKING_NO, OFFC.EMP_FNAME || ' ' || " + 
                        "OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS REQ_NAME, TO_CHAR(REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE " + 
                        "FROM HRMS_D1_REPORT_REQ_CHANGE REQ " + "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = REQ.REQ_EMP_ID) " + 
                        "INNER JOIN HRMS_D1_REPORT_REQ_PATH PATH ON(PATH.REQ_ID = REQ.REQ_ID) ";

                if (bean.isUserAManager()) {
                        approvedListSql += "WHERE REQ_STATUS IN('P', 'F', 'A') AND PATH.PATH_APPROVER = " + bean.getUserEmpId();
                } else if (bean.isUserAITApprover()) {
                        approvedListSql += "WHERE REQ_STATUS IN('A')";
                } else {
                        approvedListSql += "WHERE REQ_STATUS IN('A') AND REQ.REQ_INITIATOR = " + bean.getUserEmpId();
                }

                approvedListSql += " ORDER BY REQ.REQ_ID DESC";

                final Object [][] approvedListObj = this.getSqlModel().getSingleResult(approvedListSql);

                int totalPageForApproved = 0;
                int pageNoForApproved = 0;

                if (approvedListObj != null && approvedListObj.length > 0) {
                        final String [] pageIndex = Utility.doPaging(bean.getPageForApproved(), approvedListObj.length, 20);

                        if (pageIndex == null) {
                                pageIndex [0] = "0";
                                pageIndex [1] = "20";
                                pageIndex [2] = "1";
                                pageIndex [3] = "1";
                                pageIndex [4] = "";
                        }

                        totalPageForApproved = Integer.parseInt(String.valueOf(pageIndex [2]));
                        pageNoForApproved = Integer.parseInt(String.valueOf(pageIndex [3]));

                        final List<ReportRequestChange> approvedList = new ArrayList<ReportRequestChange>(approvedListObj.length);

                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ReportRequestChange reportRequestChange = new ReportRequestChange();
                                reportRequestChange.setReqId(this.checkNull(String.valueOf(approvedListObj [i] [0])));
                                reportRequestChange.setTrackingNo(this.checkNull(String.valueOf(approvedListObj [i] [1])));
                                reportRequestChange.setRequestorToken(this.checkNull(String.valueOf(approvedListObj [i] [2])));
                                reportRequestChange.setRequestorName(this.checkNull(String.valueOf(approvedListObj [i] [3])));
                                reportRequestChange.setRequestDate(this.checkNull(String.valueOf(approvedListObj [i] [4])));

                                approvedList.add(reportRequestChange);
                        }

                        bean.setApprovedList(approvedList);
                }

                request.setAttribute("totalPageForApproved", totalPageForApproved);
                request.setAttribute("pageNoForApproved", pageNoForApproved);
        }

        /**
         * Method to get list of draft applications.
         * 
         * @param bean - Used to get userEmpId, used to set draftList
         * @param request - Used to set totalPageForDraft and pageNoForDraft in attribute
         */
        public void getDraftList(final ReportRequestChange bean, final HttpServletRequest request) {

                final String draftListSql = "SELECT REQ_ID, REQ_EMP_ID, REQ_TRACKING_NO, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME AS REQ_NAME, TO_CHAR(REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE FROM HRMS_D1_REPORT_REQ_CHANGE REQ " + 
                        "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = REQ.REQ_EMP_ID) WHERE REQ_STATUS = 'D' AND REQ_INITIATOR = " + 
                        bean.getUserEmpId() + " ORDER BY REQ.REQ_ID DESC";
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

                        final List<ReportRequestChange> draftList = new ArrayList<ReportRequestChange>(draftListObj.length);

                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ReportRequestChange reportRequestChange = new ReportRequestChange();
                                reportRequestChange.setReqId(this.checkNull(String.valueOf(draftListObj [i] [0])));
                                reportRequestChange.setTrackingNo(this.checkNull(String.valueOf(draftListObj [i] [1])));
                                reportRequestChange.setRequestorToken(this.checkNull(String.valueOf(draftListObj [i] [2])));
                                reportRequestChange.setRequestorName(this.checkNull(String.valueOf(draftListObj [i] [3])));
                                reportRequestChange.setRequestDate(this.checkNull(String.valueOf(draftListObj [i] [4])));

                                draftList.add(reportRequestChange);
                        }

                        bean.setDraftList(draftList);
                }

                request.setAttribute("totalPageForDraft", totalPageForDraft);
                request.setAttribute("pageNoForDraft", pageNoForDraft);
        }

        /**
         * Method to get initiator details.
         * 
         * @param initiatorId - Used in query to get initiator details
         * @return - Object [][] containing initiator data
         */
        public Object [][] getInitiatorDetails(final String initiatorId) {

                final String initiatorSql = "SELECT INITIATOR.EMP_ID, INITIATOR.EMP_TOKEN || ' ' || INITIATOR.EMP_FNAME ||' ' || " +
                        "INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI') AS COMPLETED_ON " +
                        "FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID = " + initiatorId;
                return this.getSqlModel().getSingleResult(initiatorSql);
        }

        /**
         * Method to get manager details.
         * 
         * @param manager - Used in query to get manager details
         * @return - Object [][] containing manager data
         */
        public Object [][] getManager(final String manager) {

                final String managerSql = "SELECT MGR.EMP_ID, MGR.EMP_TOKEN, MGR.EMP_FNAME ||' ' || MGR.EMP_MNAME || ' ' || MGR.EMP_LNAME AS MNAME " + 
                        "FROM HRMS_EMP_OFFC MGR WHERE MGR.EMP_ID = " + manager;
                return this.getSqlModel().getSingleResult(managerSql);
        }

        /**
         * Method to get list of pending applications.
         * 
         * @param bean - Used to get userEmpId, used to set pendingList
         * @param isUserAManager - Whether user is a manager
         * @param isUserAITApprover - Whether user is a IT approver
         * @param request - Used to set totalPageForPending and pageNoForPending in attribute
         */
        public void getPendingList(final ReportRequestChange bean, final boolean isUserAManager, final boolean isUserAITApprover, 
                final HttpServletRequest request) {

                String pendingListSql = "SELECT REQ_ID, REQ_EMP_ID, REQ_TRACKING_NO, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME AS REQ_NAME, TO_CHAR(REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE FROM HRMS_D1_REPORT_REQ_CHANGE REQ " + 
                        "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = REQ.REQ_EMP_ID) ";

                if (isUserAManager) {
                        pendingListSql += "WHERE REQ_STATUS = 'P' AND REQ.REQ_APPROVER = " + bean.getUserEmpId();
                } else if (isUserAITApprover) {
                        pendingListSql += "WHERE REQ_STATUS = 'F' OR (REQ_STATUS = 'P' AND REQ.REQ_APPROVER = " + bean.getUserEmpId() + ")";
                } else {
                        pendingListSql += "WHERE REQ_STATUS = 'P' AND REQ.REQ_INITIATOR = " + bean.getUserEmpId();
                }

                pendingListSql += " ORDER BY REQ.REQ_ID DESC";

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

                        final List<ReportRequestChange> pendingList = new ArrayList<ReportRequestChange>(pendingListObj.length);

                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ReportRequestChange reportRequestChange = new ReportRequestChange();
                                reportRequestChange.setReqId(this.checkNull(String.valueOf(pendingListObj [i] [0])));
                                reportRequestChange.setTrackingNo(this.checkNull(String.valueOf(pendingListObj [i] [1])));
                                reportRequestChange.setRequestorToken(this.checkNull(String.valueOf(pendingListObj [i] [2])));
                                reportRequestChange.setRequestorName(this.checkNull(String.valueOf(pendingListObj [i] [3])));
                                reportRequestChange.setRequestDate(this.checkNull(String.valueOf(pendingListObj [i] [4])));

                                pendingList.add(reportRequestChange);
                        }

                        bean.setPendingList(pendingList);
                }

                request.setAttribute("totalPageForPending", totalPageForPending);
                request.setAttribute("pageNoForPending", pageNoForPending);
        }

        /**
         * Method to get list of rejected applications.
         * 
         * @param bean - Used to get userEmpId, pageForRejected, used to set rejectedList
         * @param isUserAManager - Whether user is a manager
         * @param isUserAITApprover - Whether user is a IT approver
         * @param request - Used to set totalPageForRejected and pageNoForRejected in attribute
         */
        public void getRejectedList(final ReportRequestChange bean, final boolean isUserAManager, final boolean isUserAITApprover, 
                final HttpServletRequest request) {

                String rejectedListSql = "SELECT REQ_ID, REQ_EMP_ID, REQ_TRACKING_NO, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME AS REQ_NAME, TO_CHAR(REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE FROM HRMS_D1_REPORT_REQ_CHANGE REQ " +
                        "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = REQ.REQ_EMP_ID) WHERE REQ_STATUS = 'R' ";

                if (isUserAManager) {
                        rejectedListSql += "AND REQ.REQ_APPROVER = " + bean.getUserEmpId();
                } else if (!isUserAITApprover) {
                        rejectedListSql += "AND REQ.REQ_INITIATOR = " + bean.getUserEmpId();
                }
                rejectedListSql += " ORDER BY REQ.REQ_ID DESC";
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

                        final List<ReportRequestChange> rejectedList = new ArrayList<ReportRequestChange>(rejectedListObj.length);

                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ReportRequestChange reportRequestChange = new ReportRequestChange();
                                reportRequestChange.setReqId(this.checkNull(String.valueOf(rejectedListObj [i] [0])));
                                reportRequestChange.setTrackingNo(this.checkNull(String.valueOf(rejectedListObj [i] [1])));
                                reportRequestChange.setRequestorToken(this.checkNull(String.valueOf(rejectedListObj [i] [2])));
                                reportRequestChange.setRequestorName(this.checkNull(String.valueOf(rejectedListObj [i] [3])));
                                reportRequestChange.setRequestDate(this.checkNull(String.valueOf(rejectedListObj [i] [4])));

                                rejectedList.add(reportRequestChange);
                        }

                        bean.setRejectedList(rejectedList);
                }

                request.setAttribute("totalPageForRejected", totalPageForRejected);
                request.setAttribute("pageNoForRejected", pageNoForRejected);
        }

        /**
         * Method to get incremented requestId.
         * 
         * @return - requestId
         */
        public String getRequestId() {

                final String requestIdSql = "SELECT NVL(MAX(REQ_ID), 0) + 1 AS REQ_ID FROM HRMS_D1_REPORT_REQ_CHANGE";
                return String.valueOf(this.getSqlModel().getSingleResult(requestIdSql) [0] [0]);
        }

        /**
         * Method to get list of send back applications.
         * 
         * @param bean - Used to get userEmpId, used to set sendBackList
         * @param isUserAITApprover - Whether user is a IT approver
         * @param request - Used to set totalPageForSendBack and pageNoForSendBack in attribute
         */
        public void getSendBackList(final ReportRequestChange bean, final boolean isUserAITApprover, final HttpServletRequest request) {

                String sendBackListSql = "SELECT REQ_ID, REQ_EMP_ID, REQ_TRACKING_NO, OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || " +
                        "OFFC.EMP_LNAME AS REQ_NAME, TO_CHAR(REQ_DATE, 'DD-MM-YYYY') AS REQ_DATE FROM HRMS_D1_REPORT_REQ_CHANGE REQ " +
                        "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = REQ.REQ_EMP_ID) WHERE REQ_STATUS = 'B' ";

                if (!isUserAITApprover) {
                        sendBackListSql += "AND REQ.REQ_EMP_ID = " + bean.getUserEmpId();
                }

                sendBackListSql += " ORDER BY REQ.REQ_ID DESC";

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

                        final List<ReportRequestChange> sendBackList = new ArrayList<ReportRequestChange>(sendBackListObj.length);

                        for (int i = Integer.parseInt(pageIndex [0]); i < Integer.parseInt(pageIndex [1]); i++) {
                                final ReportRequestChange reportRequestChange = new ReportRequestChange();
                                reportRequestChange.setReqId(this.checkNull(String.valueOf(sendBackListObj [i] [0])));
                                reportRequestChange.setTrackingNo(this.checkNull(String.valueOf(sendBackListObj [i] [1])));
                                reportRequestChange.setRequestorToken(this.checkNull(String.valueOf(sendBackListObj [i] [2])));
                                reportRequestChange.setRequestorName(this.checkNull(String.valueOf(sendBackListObj [i] [3])));
                                reportRequestChange.setRequestDate(this.checkNull(String.valueOf(sendBackListObj [i] [4])));

                                sendBackList.add(reportRequestChange);
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

                /*
                 * String trackingNo = ""; if(requestId.length() < 4) { int remChars = 4 - requestId.length(); for(int i = 0; i < remChars; i++) {
                 * requestId = "0" + requestId; } } Calendar cal = Calendar.getInstance(); cal.setTime(new Date()); int day =
                 * cal.get(Calendar.DAY_OF_MONTH); int month = cal.get(Calendar.MONTH) + 1; int year = cal.get(Calendar.YEAR); String strDay = day <
                 * 10 ? "0" + day : String.valueOf(day); String strMonth = month < 10 ? "0" + month : String.valueOf(month); String strYear =
                 * String.valueOf(year); trackingNo = "REPCHG-" + strYear + strMonth + strDay + "-" + requestId; return trackingNo;
                 */

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
                        trackingNo = model.generateApplicationCode("", "D1-REPCHG", empId, sysDate);

                        model.terminate();
                } catch (final Exception e) {
                        ReportRequestChangeModel.logger.error(e);
                }

                return trackingNo;
        }

        /**
         * Method to save approver comments.
         * 
         * @param bean - Used to get userEmpId, approverCommnets, requestId
         * @param statusToUpdate - Contains status to update
         * @return - Whether saved successfully or not
         */
        private boolean insertApproverComments(final ReportRequestChange bean, final String statusToUpdate) {

                final String insertSql = "INSERT INTO HRMS_D1_REPORT_REQ_PATH (PATH_ID, PATH_APPROVER, PATH_COMMENTS, PATH_STATUS, REQ_ID) " + 
                        "VALUES ((SELECT NVL(MAX(PATH_ID), 0) + 1 FROM HRMS_D1_REPORT_REQ_PATH), ?, ?, ?, ?)";
                final Object [][] insertObj = new Object [1] [4];
                insertObj [0] [0] = this.checkNull(bean.getUserEmpId());
                insertObj [0] [1] = this.checkNull(bean.getApproverComments());
                insertObj [0] [2] = statusToUpdate;
                insertObj [0] [3] = this.checkNull(bean.getRequestId());

                return this.getSqlModel().singleExecute(insertSql, insertObj);
        }

        /**
         * Method to get check whether IT approver exists or not.
         * 
         * @return - Whether IT approver exists or not
         */
        public boolean isITApproversExist() {

                final String itApproverSql = "SELECT * FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'M'";
                final Object [][] itApproverObj = this.getSqlModel().getSingleResult(itApproverSql);

                if (itApproverObj != null && itApproverObj.length > 0) { return true; }

                return false;
        }

        /**
         * Method to check whether user an IT approver or not.
         * 
         * @param userId - Used in query to get result
         * @return - Whether user an IT approver or not
         */
        public boolean isUserAITApprover(final String userId) {
                final String itApproverSql = "SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS " + 
                        "WHERE APP_SETTINGS_TYPE = 'M' AND APP_SETTINGS_EMP_ID = " + userId;
                final Object [][] itApproverObj = this.getSqlModel().getSingleResult(itApproverSql);

                if (itApproverObj != null && itApproverObj.length > 0) { return true; }

                return false;
        }

        /**
         * Method to populate application data for save.
         * 
         * @param bean - Used to get application data
         * @param requestId - Used while populating data
         * @return - Object [][] to save
         */
        private Object [][] populateApplicationToSave(final ReportRequestChange bean, final String requestId) {

                final Object [][] objToSave = new Object [1] [12];
                objToSave [0] [0] = this.checkNull(bean.getTrackingNo()); // REQ_TRACKING_NO
                objToSave [0] [1] = this.checkNull(bean.getRequestorId()); // REQ_EMP_ID
                objToSave [0] [2] = this.checkNull(bean.getRequestorDesgn()); // REQ_EMP_DESGN
                objToSave [0] [3] = this.checkNull(bean.getRequestorPhone()); // REQ_EMP_PHONE
                objToSave [0] [4] = this.checkNull(bean.getRequestType()); // REQ_TYPE
                objToSave [0] [5] = this.checkNull(bean.getCustomer()); // REQ_CUSTOMER
                objToSave [0] [6] = this.checkNull(bean.getReportTitle()); // REQ_REPORT_TITLE
                objToSave [0] [7] = this.checkNull(bean.getDetails()); // REQ_DETAILS
                objToSave [0] [8] = this.checkNull(bean.getAddedFile()); // REQ_FILE

                final String defApproverId = this.checkNull(bean.getDefApproverId());
                final String newApproverId = this.checkNull(bean.getNewApproverId());
                String approver = defApproverId;

                if (!"".equals(newApproverId)) {
                        approver = newApproverId;
                }

                objToSave [0] [9] = approver; // REQ_APPROVER
                objToSave [0] [10] = this.checkNull(bean.getUserEmpId()); // REQ_INITIATOR
                objToSave [0] [11] = this.checkNull(requestId); // REQ_ID

                return objToSave;
        }

        /**
         * Method to reject application.
         * 
         * @param bean - Used to get requestId, used in insertApproverComments()
         * @return - message
         */
        public String reject(final ReportRequestChange bean) {

                String message = "Application cannot be rejected!";
                final String statusToUpdate = ReportRequestChangeModel.STATUS_REJECTED;

                final String updateSql = "UPDATE HRMS_D1_REPORT_REQ_CHANGE SET REQ_STATUS = '" + statusToUpdate + 
                        "' WHERE REQ_ID = " + bean.getRequestId();
                this.getSqlModel().singleExecute(updateSql);

                final boolean isSaved = this.insertApproverComments(bean, statusToUpdate);

                if (isSaved) {
                        this.sendMailForRejectSendBack(bean, statusToUpdate);
                        message = "Application rejected successfully!";
                }

                return message;
        }

        /**
         * Method to save application.
         * 
         * @param bean - Used in populateApplicationToSave()
         * @param requestId - Used in populateApplicationToSave()
         * @return - message
         */
        private String save(final ReportRequestChange bean, final String requestId) {

                String message = "Application cannot be saved!";

                final String insertSql = "INSERT INTO HRMS_D1_REPORT_REQ_CHANGE (REQ_TRACKING_NO, REQ_EMP_ID, REQ_EMP_DESGN, REQ_EMP_PHONE, REQ_TYPE, " +
                        "REQ_CUSTOMER, REQ_REPORT_TITLE, REQ_DETAILS, REQ_FILE, REQ_APPROVER, REQ_INITIATOR, REQ_ID) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                final Object [][] insertObj = this.populateApplicationToSave(bean, requestId);
                final boolean isInserted = this.getSqlModel().singleExecute(insertSql, insertObj);

                if (isInserted) {
                        message = "Application saved successfully!";
                }

                return message;
        }

        /**
         * Method to send back an application.
         * 
         * @param bean - Used to get requestId, used in sendMailForRejectSendBack()
         * @return - message
         */
        public String sendBack(final ReportRequestChange bean) {

                String message = "Application cannot be sent back!";
                final String statusToUpdate = ReportRequestChangeModel.STATUS_SEND_BACK;

                final String updateSql = "UPDATE HRMS_D1_REPORT_REQ_CHANGE SET REQ_STATUS = '" + statusToUpdate + 
                        "' WHERE REQ_ID = " + bean.getRequestId();
                this.getSqlModel().singleExecute(updateSql);

                final boolean isSaved = this.insertApproverComments(bean, statusToUpdate);

                if (isSaved) {
                        try {
                                this.sendMailForRejectSendBack(bean, statusToUpdate);
                        } catch (final Exception e) {
                                ReportRequestChangeModel.logger.error(e);
                        }

                        message = "Application sent back successfully!";
                }

                return message;
        }

        /**
         * Method to send application for approval.
         * 
         * @param bean - Used to get requestId, used in sendMailFromApplicantToManager()
         * @return - message
         */
        public String sendForApproval(final ReportRequestChange bean) {

                String message = "Application cannot be send for approval!";

                this.draft(bean);
                final String requestId = bean.getRequestId();

                final String updateStatusSql = "UPDATE HRMS_D1_REPORT_REQ_CHANGE SET REQ_STATUS = 'P' WHERE REQ_ID = " + requestId;
                final boolean isUpdated = this.getSqlModel().singleExecute(updateStatusSql);

                if (isUpdated) {
                        try {
                                this.sendMailFromApplicantToManager(bean);
                        } catch (final Exception e) {
                                ReportRequestChangeModel.logger.error(e);
                        }

                        message = "Application successfully send for approval!";
                }

                return message;
        }

        /**
         * Method to send mail for approve/reject.
         * 
         * @param bean - Used to get requestorId, initiatorId, used in sendMailForForwardApproveReject()
         * @param status - Used in sendMailForForwardApproveReject()
         * @param prevApproversObj - Contains all previous approverIds
         */
        private void sendMailForApproveReject(final ReportRequestChange bean, final String status, final Object [][] prevApproversObj) {

                final String groupMailSql = "SELECT APP_EMAIL_ID FROM HRMS_D1_APPROVAL_SETTINGS " +
                        "WHERE APP_EMAIL_ID IS NOT NULL AND APP_SETTINGS_TYPE = 'M'";
                final Object [][] groupMail = this.getSqlModel().getSingleResult(groupMailSql);

                final String toEmp = this.checkNull(bean.getInitiatorId());
                int keepInfoSize = prevApproversObj.length;

                if (!bean.getRequestorId().equals(bean.getInitiatorId())) {
                        keepInfoSize++;
                }

                final String [] keepInfo = new String [keepInfoSize];
                int cnt = 0;

                if (prevApproversObj != null && prevApproversObj.length > 0) {
                        for (int i = 0; i < prevApproversObj.length; i++) {
                                keepInfo [cnt] = this.checkNull(String.valueOf(prevApproversObj [i] [0]));
                                cnt++;
                        }
                }

                if (!bean.getRequestorId().equals(bean.getInitiatorId())) {
                        keepInfo [cnt] = bean.getRequestorId();
                }

                try {
                        this.sendMailForForwardApproveReject(bean, status, toEmp, keepInfo, groupMail);
                } catch (final Exception e) {
                        ReportRequestChangeModel.logger.error(e);
                }
        }

        /**
         * Method to send mail for forward.
         * 
         * @param bean - Used to get requestorId, initiatorId, used in sendMailForForwardApproveReject()
         * @param status - Used in sendMailForForwardApproveReject()
         * @param toEmp - Used in sendMailForForwardApproveReject()
         * @param prevApproversObj - Contains all previous approverIds
         */
        private void sendMailForForward(final ReportRequestChange bean, final String status, final String toEmp, final Object [][] prevApproversObj) {

                final String groupMailSql = "SELECT APP_EMAIL_ID FROM HRMS_D1_APPROVAL_SETTINGS " +
                        "WHERE APP_EMAIL_ID IS NOT NULL AND APP_SETTINGS_TYPE = 'M'";
                final Object [][] groupMail = this.getSqlModel().getSingleResult(groupMailSql);

                if (groupMail != null && groupMail.length > 0) {
                        int keepInfoSize = prevApproversObj.length + 1;

                        if (!bean.getRequestorId().equals(bean.getInitiatorId())) {
                                keepInfoSize++;
                        }

                        final String [] keepInfo = new String [keepInfoSize];
                        int cnt = 0;

                        if (prevApproversObj != null && prevApproversObj.length > 0) {
                                for (int i = 0; i < prevApproversObj.length; i++) {
                                        keepInfo [cnt] = this.checkNull(String.valueOf(prevApproversObj [i] [0]));
                                        cnt++;
                                }
                        }

                        keepInfo [cnt] = bean.getInitiatorId();
                        cnt++;

                        if (!bean.getRequestorId().equals(bean.getInitiatorId())) {
                                keepInfo [cnt] = bean.getRequestorId();
                        }

                        try {
                                this.sendMailForForwardApproveReject(bean, status, toEmp, keepInfo, groupMail);
                        } catch (final Exception e) {
                                ReportRequestChangeModel.logger.error(e);
                        }
                }
        }

        /**
         * Method to send mail for forward/approve/reject.
         * 
         * @param bean - Used to get userEmpId, requestId, addedFile, dataPath
         * @param status - Contains either forward, approve or reject
         * @param toEmp - Used in "To"
         * @param keepInfo - template.sendApplicationMailToKeepInfo()
         * @param groupMail - Used to send group mail
         */
        private void sendMailForForwardApproveReject(final ReportRequestChange bean, final String status, final String toEmp, final String [] keepInfo, 
                final Object [][] groupMail) {

                final EmailTemplateBody template = new EmailTemplateBody();
                template.initiate(context, session);
                template.setEmailTemplate("D1-REPORT REQUEST CHANGE FORWARD/APPROVE/REJECT");
                template.getTemplateQueries();

                final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
                templateQuery1.setParameter(1, bean.getUserEmpId());

                final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
                templateQuery2.setParameter(1, toEmp);

                final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3); // Request Info
                templateQuery3.setParameter(1, status);
                templateQuery3.setParameter(2, bean.getRequestId());

                final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4); // Requestor Details
                templateQuery4.setParameter(1, bean.getRequestId());

                final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5); // FROM_EMP
                templateQuery5.setParameter(1, bean.getUserEmpId());

                /*
                 * final EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6); // TO_EMP templateQuery6.setParameter(1, toEmp);
                 */

                final EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7); // Approval Info
                templateQuery7.setParameter(1, bean.getRequestId());

                template.configMailAlert();
                final String addedFile = bean.getAddedFile();
                final String dataPath = bean.getDataPath();
                final String filePath = dataPath + addedFile;
                final String [] attachment = new String [1];
                attachment [0] = filePath;
                
                if (status.equals(ReportRequestChangeModel.STATUS_FORWARDED)) {
                        if (!"".equals(addedFile)) {
                                template.sendApplicationMailToGroups(groupMail, attachment);
                        } else {
                                template.sendApplicationMailToGroups(groupMail);
                        }
                } else {

                        if (!"".equals(addedFile)) {
                                template.sendApplMailWithAttachment(attachment);
                                template.sendApplicationMailToGroups(groupMail, attachment);
                        } else {
                                template.sendApplicationMail();
                                template.sendApplicationMailToGroups(groupMail);
                        }
                }

                if (keepInfo != null && keepInfo.length > 0) {
                        if (!"".equals(addedFile)) {
                                template.sendApplMailWithAttachmentToKeepInf(keepInfo, attachment);
                        } else {
                                template.sendApplicationMailToKeepInfo(keepInfo);
                        }
                }

                template.clearParameters();
                template.terminate();
        }

        /**
         * Method to send mail for reject/send back.
         * 
         * @param bean - Used to get initiatorId, requestId, userEmpId, used in sendMailForForwardApproveReject()
         * @param status - Used in sendMailForForwardApproveReject()
         */
        private void sendMailForRejectSendBack(final ReportRequestChange bean, final String status) {
                try {
                        final String toEmp = bean.getInitiatorId();
                        int itApproversSize = 0;
                        int prevApproverSize = 0;
                        Object [][] itApproversObj = null;

                        if (this.isUserAITApprover(bean.getUserEmpId())) {
                                final String itApproversSql = "SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS " +
                                        "WHERE APP_SETTINGS_TYPE = 'M' ORDER BY APP_SETTINGS_ID";
                                itApproversObj = this.getSqlModel().getSingleResult(itApproversSql);
                                itApproversSize = itApproversObj.length - 1;
                        }

                        final String prevApproversSql = "SELECT DISTINCT PATH_APPROVER FROM HRMS_D1_REPORT_REQ_PATH WHERE PATH_APPROVER != " + 
                                bean.getUserEmpId() + " AND PATH_STATUS IN('P', 'F') AND PATH_APPROVER NOT IN (SELECT APP_SETTINGS_EMP_ID " +
                                "FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'M') AND REQ_ID = " + bean.getRequestId();
                        final Object [][] prevApproversObj = this.getSqlModel().getSingleResult(prevApproversSql);

                        if (prevApproversObj != null && prevApproversObj.length > 0) {
                                prevApproverSize = prevApproversObj.length;
                        }

                        final int keepInfoSize = itApproversSize + prevApproverSize;
                        final String [] keepInfo = new String [keepInfoSize + 1];
                        int cnt = 0;

                        if (itApproversObj != null && itApproversObj.length > 0) {
                                for (int i = 0; i < itApproversObj.length; i++) {
                                        final String itApprover = this.checkNull(String.valueOf(itApproversObj [i] [0]));

                                        if (!itApprover.equals(bean.getUserEmpId())) {
                                                keepInfo [cnt] = itApprover;
                                                cnt++;
                                        }
                                }
                        }

                        if (prevApproversObj != null && prevApproversObj.length > 0) {
                                for (int i = 0; i < prevApproversObj.length; i++) {
                                        keepInfo [cnt] = this.checkNull(String.valueOf(prevApproversObj [i] [0]));
                                        cnt++;
                                }
                        }

                        this.sendMailForForwardApproveReject(bean, status, toEmp, keepInfo, null);
                } catch (final Exception e) {
                        ReportRequestChangeModel.logger.error(e);
                }
        }

        /**
         * Method to send mail from applicant to manager.
         * 
         * @param bean - Used to get userEmpId, defApproverId, newApproverId, requestId, addedFile, dataPath
         */
        private void sendMailFromApplicantToManager(final ReportRequestChange bean) {

                final EmailTemplateBody template = new EmailTemplateBody();
                template.initiate(context, session);
                template.setEmailTemplate("D1-REPORT REQUEST CHANGE APPLICANT TO MANAGER");
                template.getTemplateQueries();

                final EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
                templateQuery1.setParameter(1, bean.getUserEmpId());

                final String defApproverId = this.checkNull(bean.getDefApproverId());
                final String newApproverId = this.checkNull(bean.getNewApproverId());
                String toEmp = defApproverId;

                if (!"".equals(newApproverId)) {
                        toEmp = newApproverId;
                }

                final EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
                templateQuery2.setParameter(1, toEmp);

                final EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3); // Request Info
                templateQuery3.setParameter(1, bean.getRequestId());

                final EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4); // Requestor Details
                templateQuery4.setParameter(1, bean.getRequestId());

                final EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5); // FROM_EMP
                templateQuery5.setParameter(1, bean.getUserEmpId());

                template.configMailAlert();

                /*
                 * if(!this.checkNull(bean.getAddedFile()).equals("")) { template.sendApplMailWithAttachment(new String[]{bean.getDataPath() +
                 * bean.getAddedFile()}); } else { template.sendApplicationMail(); }
                 */

                final String addedFile = bean.getAddedFile();
                final String dataPath = bean.getDataPath();
                final String filePath = dataPath + addedFile;
                
                if (!"".equals(addedFile)) {
                        final String [] attachment = new String [1];
                        attachment [0] = filePath;
                        template.sendApplMailWithAttachment(attachment);
                } else {
                        template.sendApplicationMail();
                }
                template.clearParameters();
                template.terminate();
        }

        /**
         * Method to set application details.
         * 
         * @param bean - Used to set application data
         * @param reqId - Used to set requestId
         */
        public void setApplicationDetails(final ReportRequestChange bean, final String reqId) {

                final String applnDtlsSql = "SELECT REQ_TRACKING_NO, REQ_EMP_ID, OFFC.EMP_TOKEN AS REQ_TOKEN, OFFC.EMP_FNAME || ' ' || " +
                        "OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS REQ_NAME, REQ_EMP_DESGN, REQ_EMP_PHONE, REQ_TYPE, REQ_CUSTOMER, " +
                        "REQ_REPORT_TITLE, REQ_DETAILS, REQ_FILE, REQ_STATUS, APPROVER.EMP_ID AS APPROVER_ID, APPROVER.EMP_TOKEN AS APPROVER_TOKEN, " +
                        "APPROVER.EMP_FNAME ||' ' || APPROVER.EMP_MNAME || ' ' || APPROVER.EMP_LNAME AS APPROVER_NAME, " +
                        "INITIATOR.EMP_ID AS INITIATOR_ID, INITIATOR.EMP_TOKEN || ' ' || INITIATOR.EMP_FNAME ||' ' || INITIATOR.EMP_MNAME || ' ' || " +
                        "INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI') AS COMPLETED_ON " +
                        "FROM HRMS_D1_REPORT_REQ_CHANGE REQ " +
                        "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = REQ.REQ_EMP_ID) " + 
                        "LEFT JOIN HRMS_EMP_OFFC APPROVER ON(APPROVER.EMP_ID = REQ.REQ_APPROVER) " + 
                        "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = REQ.REQ_INITIATOR) WHERE REQ_ID = " + reqId;
                final Object [][] applnDtlsObj = this.getSqlModel().getSingleResult(applnDtlsSql);

                if (applnDtlsObj != null && applnDtlsObj.length > 0) {
                        bean.setRequestId(reqId);
                        bean.setTrackingNo(this.checkNull(String.valueOf(applnDtlsObj [0] [0]))); // REQ_TRACKING_NO
                        bean.setRequestorId(this.checkNull(String.valueOf(applnDtlsObj [0] [1]))); // REQ_EMP_ID
                        bean.setRequestorToken(this.checkNull(String.valueOf(applnDtlsObj [0] [2]))); // REQ_TOKEN
                        bean.setRequestorName(this.checkNull(String.valueOf(applnDtlsObj [0] [3]))); // REQ_NAME
                        bean.setRequestorDesgn(this.checkNull(String.valueOf(applnDtlsObj [0] [4]))); // RANK_NAME
                        bean.setRequestorPhone(this.checkNull(String.valueOf(applnDtlsObj [0] [5]))); // ADD_PH1
                        bean.setRequestType(this.checkNull(String.valueOf(applnDtlsObj [0] [6]))); // REQ_TYPE
                        bean.setCustomer(this.checkNull(String.valueOf(applnDtlsObj [0] [7]))); // REQ_CUSTOMER
                        bean.setReportTitle(this.checkNull(String.valueOf(applnDtlsObj [0] [8]))); // REQ_REPORT_TITLE
                        bean.setDetails(this.checkNull(String.valueOf(applnDtlsObj [0] [9]))); // REQ_DETAILS
                        bean.setAddedFile(this.checkNull(String.valueOf(applnDtlsObj [0] [10]))); // REQ_FILE
                        bean.setStatus(this.checkNull(String.valueOf(applnDtlsObj [0] [11]))); // REQ_STATUS
                        bean.setNewApproverId(this.checkNull(String.valueOf(applnDtlsObj [0] [12]))); // APPROVER_ID
                        bean.setNewApproverToken(this.checkNull(String.valueOf(applnDtlsObj [0] [13]))); // APPROVER_TOKEN
                        bean.setNewApproverName(this.checkNull(String.valueOf(applnDtlsObj [0] [14]))); // APPROVER_NAME
                        bean.setInitiatorId(this.checkNull(String.valueOf(applnDtlsObj [0] [15]))); // INITIATOR_ID
                        bean.setInitiatorName(this.checkNull(String.valueOf(applnDtlsObj [0] [16]))); // INITIATOR_NAME
                        bean.setCompletedOn(this.checkNull(String.valueOf(applnDtlsObj [0] [17]))); // COMPLETED_ON
                }

                bean.setNewRecord(false);
        }

        /**
         * Method to set application details for view.
         * 
         * @param bean - Used to set application details
         * @param reqId - Used to set requestId
         */
        public void setApplicationDetailsForView(final ReportRequestChange bean, final String reqId) {

                final String applnDtlsSql = "SELECT REQ_TRACKING_NO, REQ_EMP_ID, OFFC.EMP_TOKEN AS REQ_TOKEN, OFFC.EMP_FNAME || ' ' || " +
                        "OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS REQ_NAME, REQ_EMP_DESGN, REQ_EMP_PHONE, DECODE(REQ_TYPE, 'N', 'New', " +
                        "'C', 'Change', 'D', 'Delete') AS REQ_TYPE, REQ_CUSTOMER, REQ_REPORT_TITLE, REQ_DETAILS, REQ_FILE, REQ_STATUS, " +
                        "INITIATOR.EMP_ID AS INITIATOR_ID, INITIATOR.EMP_TOKEN || ' ' || INITIATOR.EMP_FNAME ||' ' || INITIATOR.EMP_MNAME || ' ' || " +
                        "INITIATOR.EMP_LNAME AS INITIATOR_NAME, TO_CHAR(SYSDATE, 'DD-MM-YYYY HH24:MI') AS COMPLETED_ON " +
                        "FROM HRMS_D1_REPORT_REQ_CHANGE REQ LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = REQ.REQ_EMP_ID) " +
                        "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = REQ.REQ_INITIATOR) WHERE REQ_ID = " + reqId;
                final Object [][] applnDtlsObj = this.getSqlModel().getSingleResult(applnDtlsSql);

                if (applnDtlsObj != null && applnDtlsObj.length > 0) {
                        bean.setRequestId(reqId);
                        bean.setTrackingNo(this.checkNull(String.valueOf(applnDtlsObj [0] [0]))); // REQ_TRACKING_NO
                        bean.setRequestorId(this.checkNull(String.valueOf(applnDtlsObj [0] [1]))); // REQ_EMP_ID
                        bean.setRequestorToken(this.checkNull(String.valueOf(applnDtlsObj [0] [2]))); // REQ_TOKEN
                        bean.setRequestorName(this.checkNull(String.valueOf(applnDtlsObj [0] [3]))); // REQ_NAME
                        bean.setRequestorDesgn(this.checkNull(String.valueOf(applnDtlsObj [0] [4]))); // RANK_NAME
                        bean.setRequestorPhone(this.checkNull(String.valueOf(applnDtlsObj [0] [5]))); // ADD_PH1
                        bean.setRequestType(this.checkNull(String.valueOf(applnDtlsObj [0] [6]))); // REQ_TYPE
                        bean.setCustomer(this.checkNull(String.valueOf(applnDtlsObj [0] [7]))); // REQ_CUSTOMER
                        bean.setReportTitle(this.checkNull(String.valueOf(applnDtlsObj [0] [8]))); // REQ_REPORT_TITLE
                        bean.setDetails(this.checkNull(String.valueOf(applnDtlsObj [0] [9]))); // REQ_DETAILS
                        bean.setAddedFile(this.checkNull(String.valueOf(applnDtlsObj [0] [10]))); // REQ_FILE
                        bean.setStatus(this.checkNull(String.valueOf(applnDtlsObj [0] [11]))); // REQ_STATUS
                        bean.setInitiatorId(this.checkNull(String.valueOf(applnDtlsObj [0] [12]))); // INITIATOR_ID
                        bean.setInitiatorName(this.checkNull(String.valueOf(applnDtlsObj [0] [13]))); // INITIATOR_NAME
                        bean.setCompletedOn(this.checkNull(String.valueOf(applnDtlsObj [0] [14]))); // COMPLETED_ON
                }

                bean.setNewRecord(false);
        }

        /**
         * Method to set list of approver comments.
         * 
         * @param bean - Used to get requestId, used to set approverCommentsList
         */
        public void setApproverCommentsList(final ReportRequestChange bean) {

                final String apprCommentsListSql = "SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS APPROVER_NAME, " +
                        "PATH_COMMENTS, TO_CHAR(PATH_APPROVAL_DATE, 'DD-MM-YYYY') AS APPROVAL_DATE, DECODE(PATH_STATUS, 'D', 'Draft', " +
                        "'P', 'Forwarded', 'B', 'Sent Back', 'A', 'Approved', 'R', 'Rejected', 'F', 'Authorised Sign Off', " +
                        "'C', 'Applied For Cancellation', 'X', 'Cancellation Approved', 'Z', 'Cancellation Rejected') AS STATUS " +
                        "FROM HRMS_D1_REPORT_REQ_PATH PATH " +
                        "LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = PATH.PATH_APPROVER) WHERE PATH.REQ_ID = " + bean.getRequestId() + 
                        " ORDER BY PATH_ID DESC ";

                final Object [][] apprCommentsListObj = this.getSqlModel().getSingleResult(apprCommentsListSql);

                if (apprCommentsListObj != null && apprCommentsListObj.length > 0) {
                        final List<ReportRequestChange> approverCommentsList = new ArrayList<ReportRequestChange>(apprCommentsListObj.length);

                        for (int i = 0; i < apprCommentsListObj.length; i++) {
                                final ReportRequestChange reportRequestChange = new ReportRequestChange();
                                reportRequestChange.setApprName(this.checkNull(String.valueOf(apprCommentsListObj [i] [0])));
                                reportRequestChange.setApprComments(this.checkNull(String.valueOf(apprCommentsListObj [i] [1])));
                                reportRequestChange.setApprDate(this.checkNull(String.valueOf(apprCommentsListObj [i] [2])));
                                reportRequestChange.setApprStatus(this.checkNull(String.valueOf(apprCommentsListObj [i] [3])));
                                approverCommentsList.add(reportRequestChange);
                        }

                        bean.setApproverCommentsList(approverCommentsList);
                }
        }

        /**
         * Method to get default requester details.
         * 
         * @param requestorId - Used in query to get requester details
         * @return - Object[][] containing requester details
         */
        public Object [][] setDefaultRequestor(final String requestorId) {

                final String requestorSql = "SELECT EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME || ' ' || EMP_LNAME AS ENAME, RANK.RANK_NAME, ADD_PH1, " +
                        "EMP_ID FROM HRMS_EMP_OFFC LEFT JOIN HRMS_RANK RANK ON(RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " + 
                        "LEFT JOIN HRMS_EMP_ADDRESS ADDRESS ON(ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE = 'P') WHERE EMP_ID = " + requestorId;
                return this.getSqlModel().getSingleResult(requestorSql);
        }

        /**
         * Method to update the application.
         * 
         * @param bean - Used in populateApplicationToSave()
         * @param requestId - Used in populateApplicationToSave()
         * @return - message
         */
        private String update(final ReportRequestChange bean, final String requestId) {

                String message = "Application cannot be modified!";

                final String updateSql = "UPDATE HRMS_D1_REPORT_REQ_CHANGE SET REQ_TRACKING_NO = ?, REQ_EMP_ID = ?, REQ_EMP_DESGN = ?, " +
                        "REQ_EMP_PHONE = ?, REQ_TYPE = ?, REQ_CUSTOMER = ?, REQ_REPORT_TITLE = ?, REQ_DETAILS = ?, REQ_FILE = ?, REQ_APPROVER = ?, " +
                        "REQ_INITIATOR = ? WHERE REQ_ID = ? ";

                final Object [][] updateObj = this.populateApplicationToSave(bean, requestId);
                final boolean isUpdateed = this.getSqlModel().singleExecute(updateSql, updateObj);

                if (isUpdateed) {
                        message = "Application modified successfully";
                }

                return message;
        }
}
