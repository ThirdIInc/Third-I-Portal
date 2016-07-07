package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.Logger;
import org.paradyne.bean.D1.ReportRequestChange;
import org.paradyne.model.D1.ReportRequestChangeModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Mar 17, 2011 Action for add/delete/update/view/submit for report request change.
 */

public class ReportRequestChangeAction extends ParaActionSupport {

        /**
         * Log4j logger.
         */
        private static Logger logger = Logger.getLogger(ReportRequestChangeAction.class);
        
        /**
         * List type for pending applications.
         */
        private static final String LIST_TYPE_PENDING = "pending";

        /**
         * List type for approved applications.
         */
        private static final String LIST_TYPE_APPROVED = "approved";

        /**
         * List type for rejected applications.
         */
        private static final String LIST_TYPE_REJECTED = "rejected";
        
        /**
         * Request id get from request.
         */
        private static final String REQ_ID = "reqId";
        
        /**
         * Used for f9 windows.
         */
        private static final String F9_PAGE = "f9page";

        /**
         * Object of ReportRequestChange.
         */
        private ReportRequestChange bean;

        /**
         * Method to add new application.
         * 
         * @return SUCCESS
         */
        public String addApplication() {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                this.bean.setNewRecord(true);
                this.bean.setStatus(ReportRequestChangeModel.STATUS_DRAFT);

                this.setRequestorInfo(model);

                model.terminate();
                this.getNavigationPanel(2);
                return SUCCESS;
        }

        /**
         * Method to get list of applications as per the list type, when clicked on paging buttons.
         * 
         * @return INPUT
         */
        public String callPage() {

                final String listType = this.bean.getListType();

                if (listType.equals(ReportRequestChangeAction.LIST_TYPE_PENDING)) {
                        this.getPendingList();
                } else if (listType.equals(ReportRequestChangeAction.LIST_TYPE_APPROVED)) {
                        this.getApprovedList();
                } else if (listType.equals(ReportRequestChangeAction.LIST_TYPE_REJECTED)) {
                        this.getRejectedList();
                } else {
                        this.getPendingList();
                }

                this.getNavigationPanel(1);
                return INPUT;
        }

        /**
         * Method to delete the application.
         * 
         * @return this.input()
         * @throws Exception - this.input() throws Exception
         */
        public String delete() throws Exception {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                final String requestId = this.bean.getRequestId();
                final String message = model.delete(requestId);
                this.addActionMessage(message);

                model.terminate();
                return this.input();
        }

        /**
         * Method to draft the application.
         * 
         * @return this.input()
         * @throws Exception - this.input() throws Exception
         */
        public String draft() throws Exception {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                final String message = model.draft(this.bean);
                this.addActionMessage(message);

                model.terminate();
                return this.input();
        }

        /**
         * Method to edit the application.
         * 
         * @return SUCCESS
         */
        public String editDetails() {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                final String reqId = request.getParameter(ReportRequestChangeAction.REQ_ID);
                model.setApplicationDetails(this.bean, reqId);
                model.setApproverCommentsList(this.bean);
                this.getManager();
                final String status = this.bean.getStatus();

                if (status.equals(ReportRequestChangeModel.STATUS_SEND_BACK)) {
                        this.getNavigationPanel(2);
                } else {
                        this.getNavigationPanel(6);
                }

                this.bean.setListType(ReportRequestChangeAction.LIST_TYPE_PENDING);
                this.bean.setEnableAll("Y");
                model.terminate();
                return SUCCESS;
        }

        /**
         * Method to open f9 window for approver.
         * 
         * @return - f9page
         */
        public String f9Approver() {

                String query = "SELECT EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME || ' ' || EMP_LNAME AS ENAME, EMP_ID FROM HRMS_EMP_OFFC ";

                if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
                        query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
                }

                query += " AND EMP_STATUS = 'S' AND EMP_ID NOT IN(" + this.bean.getRequestorId();

                if (this.bean.getDefApproverId() != null && this.bean.getDefApproverId().length() > 0) {
                        query += ", " + this.bean.getDefApproverId();
                }

                query += ") ORDER BY UPPER(ENAME) ";

                final String [] headers = {this.getMessage("approver.token"), this.getMessage("approver.name") };

                final String [] headerWidth = {"20", "80"};

                final String [] fieldNames = {"newApproverToken", "newApproverName", "newApproverId"};

                final int [] columnIndex = {0, 1, 2};

                final String submitFlag = "false";

                final String submitToMethod = "";

                this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

                return ReportRequestChangeAction.F9_PAGE;
        }

        /**
         * Method to open f9 window for requestor.
         * 
         * @return - f9page
         */
        public String f9Requestor() {

                String query = "SELECT EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME || ' ' || EMP_LNAME AS ENAME, RANK.RANK_NAME, ADD_PH1, " +
                        "HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC LEFT JOIN HRMS_RANK RANK ON(RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " +
                        "LEFT JOIN HRMS_EMP_ADDRESS ADDRESS ON(ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE = 'P') ";
                query += this.getprofileQuery(this.bean);
                query += " AND EMP_STATUS = 'S' ORDER BY UPPER(ENAME) ";

                final String [] headers = {this.getMessage("requestor.token"), this.getMessage("requestor.name"), 
                        this.getMessage("requestor.designation")};

                final String [] headerWidth = {"20", "60", "20"};

                final String [] fieldNames = {"requestorToken", "requestorName", "requestorDesgn", "requestorPhone", "requestorId"};

                final int [] columnIndex = {0, 1, 2, 3, 4};

                final String submitFlag = "true";

                final String submitToMethod = "ReportRequestChange_getManager.action";

                this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

                return ReportRequestChangeAction.F9_PAGE;
        }

        /**
         * Method to get list of approved applications.
         * 
         * @return INPUT
         */
        public String getApprovedList() {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                model.getApprovedList(this.bean, request);

                model.terminate();
                this.getNavigationPanel(1);
                this.bean.setListType(ReportRequestChangeAction.LIST_TYPE_APPROVED);
                return INPUT;
        }

        /**
         * Method to get initiator details.
         */
        private void getInitiator() {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                final Object [][] initiatorObj = model.getInitiatorDetails(this.bean.getUserEmpId());

                if (initiatorObj != null && initiatorObj.length > 0) {
                        this.bean.setInitiatorId(model.checkNull(String.valueOf(initiatorObj [0] [0])));
                        this.bean.setInitiatorName(model.checkNull(String.valueOf(initiatorObj [0] [1])));
                        this.bean.setCompletedOn(model.checkNull(String.valueOf(initiatorObj [0] [2])));
                }

                model.terminate();
        }

        /**
         * Method to get manager details.
         * 
         * @return SUCCESS
         */
        public String getManager() {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                this.bean.setDefApproverId("");
                this.bean.setDefApproverToken("");
                this.bean.setDefApproverName("");

                final Object [][] empFlow = this.generateEmpFlow(this.bean.getRequestorId(), "D1", 1);

                if (empFlow != null && empFlow.length > 0) {
                        final String manager = model.checkNull(String.valueOf(empFlow [0] [0]));

                        if (!"".equals(manager)) {
                                final Object [][] managerObj = model.getManager(manager);

                                if (managerObj != null && managerObj.length > 0) {
                                        this.bean.setDefApproverId(model.checkNull(String.valueOf(managerObj [0] [0])));
                                        this.bean.setDefApproverToken(model.checkNull(String.valueOf(managerObj [0] [1])));
                                        this.bean.setDefApproverName(model.checkNull(String.valueOf(managerObj [0] [2])));

                                        if (this.bean.getDefApproverId().equals(this.bean.getNewApproverId())) {
                                                this.bean.setNewApproverId("");
                                                this.bean.setNewApproverToken("");
                                                this.bean.setNewApproverName("");
                                        }
                                }
                        }
                }

                model.terminate();
                this.getNavigationPanel(1);
                return SUCCESS;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.opensymphony.xwork2.ModelDriven#getModel()
         */
        /**
         * Method to get bean.
         * 
         * @return - object of bean
         */
        public Object getModel() {
                return this.bean;
        }

        /**
         * Method to get list of pending applications.
         * 
         * @return INPUT
         */
        public String getPendingList() {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                model.getDraftList(this.bean, request);
                model.getPendingList(this.bean, false, false, request);
                model.getSendBackList(this.bean, false, request);

                this.getNavigationPanel(1);
                model.terminate();
                this.bean.setListType(ReportRequestChangeAction.LIST_TYPE_PENDING);
                return INPUT;
        }

        /**
         * Method to get list of rejected applications.
         * 
         * @return INPUT
         */
        public String getRejectedList() {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                model.getRejectedList(this.bean, false, false, request);

                model.terminate();
                this.getNavigationPanel(1);
                this.bean.setListType(ReportRequestChangeAction.LIST_TYPE_REJECTED);
                return INPUT;
        }

        @Override
        public String input() throws Exception {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                final String listType = model.checkNull(this.bean.getListType());

                if ("".equals(listType)) {
                        this.getPendingList();
                } else {
                        if (listType.equals(ReportRequestChangeAction.LIST_TYPE_APPROVED)) {
                                this.getApprovedList();
                        } else if (listType.equals(ReportRequestChangeAction.LIST_TYPE_REJECTED)) {
                                this.getRejectedList();
                        } else {
                                this.getPendingList();
                        }
                }
                this.getNavigationPanel(1);
                return INPUT;
        }

        /**
         * Method to open attached file.
         * 
         * @throws IOException - For writing data into stream.
         */
        public void openAttachedFile() throws IOException {

                final String addedFile = this.bean.getAddedFile();
                final String hash = "#";
                final String [] extension = addedFile.replace(".", hash).split(hash);
                final String ext = extension [extension.length - 1];
                String mimeType = "";
                final String dataPath = this.bean.getDataPath();
                final String filePath = dataPath + addedFile;

                OutputStream oStream = null;
                FileInputStream fsStream = null;

                try {
                        final String applnPdf = "pdf";
                        final String applnDoc = "doc";
                        final String applnXls = "xls";
                        final String applnXlsx = "xlsx";
                        final String applnJpg = "jpg";
                        final String applnTxt = "txt";
                        final String applnGif = "gif";
                        
                        final String mimeTypeAcrobat = "acrobat";
                        final String mimeTypeMSWord = "msword";
                        final String mimeTypeMSExcel = "msexcel";
                        final String mimeTypeJpg = "jpg";
                        final String mimeTypeTxt = "txt";
                        final String mimeTypeGif = "gif";
                        
                        if (applnPdf.equals(ext)) {
                                mimeType = mimeTypeAcrobat;
                        } else if (applnDoc.equals(ext)) {
                                mimeType = mimeTypeMSWord;
                        } else if (applnXls.equals(ext)) {
                                mimeType = mimeTypeMSExcel;
                        } else if (applnXlsx.equals(ext)) {
                                mimeType = mimeTypeMSExcel;
                        } else if (applnJpg.equals(ext)) {
                                mimeType = mimeTypeJpg;
                        } else if (applnTxt.equals(ext)) {
                                mimeType = mimeTypeTxt;
                        } else if (applnGif.equals(ext)) {
                                mimeType = mimeTypeGif;
                        }

                        response.setHeader("Content-type", "application/" + mimeType);
                        response.setHeader("Content-disposition", "attachment;filename=\"" + addedFile + "\"");

                        int iChar;
                        fsStream = new FileInputStream(filePath);
                        oStream = response.getOutputStream();

                        while ((iChar = fsStream.read()) != -1) {
                                oStream.write(iChar);
                        }
                } catch (final Exception e) {
                        ReportRequestChangeAction.logger.error("Exception in openTemplate:" + e);
                } finally {
                        if (fsStream != null) {
                                fsStream.close();
                        }

                        if (oStream != null) {
                                oStream.flush();
                                oStream.close();
                        }
                }
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.struts.lib.ParaActionSupport#prepare_local()
         */
        @Override
        public void prepare_local() throws Exception {

                this.bean = new ReportRequestChange();
                this.bean.setMenuCode(2015);
        }

        @Override
        public void prepare_withLoginProfileDetails() throws Exception {

                String poolName = String.valueOf(session.getAttribute("session_pool"));

                if (!("".equals(poolName) || poolName == null)) {
                        poolName = "/" + poolName;
                }

                final String dataPath = this.getText("data_path") + "/upload" + poolName + "/REPORT_REQUEST/";
                this.bean.setDataPath(dataPath);

                this.getInitiator();
        }

        /**
         * Method to reset form fields.
         * 
         * @return SUCCESS
         */
        public String reset() {

                this.bean.setRequestId("");
                this.bean.setStatus("D");
                this.bean.setRequestorId("");
                this.bean.setRequestorToken("");
                this.bean.setRequestorName("");
                this.bean.setRequestorDesgn("");
                this.bean.setRequestorPhone("");
                this.bean.setRequestType("");
                this.bean.setCustomer("");
                this.bean.setReportTitle("");
                this.bean.setDetails("");
                this.bean.setReportFile("");
                this.bean.setAddedFile("");
                this.bean.setNewApproverId("");
                this.bean.setNewApproverToken("");
                this.bean.setNewApproverName("");

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                this.bean.setNewRecord(true);
                final String requestId = model.getRequestId();
                this.bean.setRequestId(requestId);

                this.setRequestorInfo(model);

                model.terminate();
                this.getNavigationPanel(2);
                return SUCCESS;
        }

        /**
         * Method to send application for approval.
         * 
         * @return this.input()
         * @throws Exception - this.input() throws Exception
         */
        public String sendForApproval() throws Exception {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                final String message = model.sendForApproval(this.bean);
                this.addActionMessage(message);

                model.terminate();
                return this.input();
        }

        /**
         * Method to set requester info.
         * 
         * @param model - Used to set info
         */
        private void setRequestorInfo(final ReportRequestChangeModel model) {

                final Object [][] requestorObj = model.setDefaultRequestor(this.bean.getUserEmpId());

                if (requestorObj != null && requestorObj.length > 0) {
                        this.bean.setRequestorToken(model.checkNull(String.valueOf(requestorObj [0] [0])));
                        this.bean.setRequestorName(model.checkNull(String.valueOf(requestorObj [0] [1])));
                        this.bean.setRequestorDesgn(model.checkNull(String.valueOf(requestorObj [0] [2])));
                        this.bean.setRequestorPhone(model.checkNull(String.valueOf(requestorObj [0] [3])));
                        this.bean.setRequestorId(model.checkNull(String.valueOf(requestorObj [0] [4])));
                }

                this.getManager();
        }

        /**
         * Method to view application details.
         * 
         * @return - SUCCESS
         */
        public String viewDetails() {

                final ReportRequestChangeModel model = new ReportRequestChangeModel();
                model.initiate(context, session);

                String reqId = request.getParameter(ReportRequestChangeAction.REQ_ID);

                /**
                 * FOR SUPER USER
                 */
                final String applCode = request.getParameter("applCode");
                if (applCode != null && applCode.length() > 0) {
                        reqId = applCode;
                }

                model.setApplicationDetails(this.bean, reqId);
                this.getManager();
                model.setApproverCommentsList(this.bean);
                final String status = this.bean.getStatus();

                if (status.equals(ReportRequestChangeModel.STATUS_APPROVED)) {
                        this.bean.setListType(ReportRequestChangeAction.LIST_TYPE_APPROVED);
                } else if (status.equals(ReportRequestChangeModel.STATUS_REJECTED)) {
                        this.bean.setListType(ReportRequestChangeAction.LIST_TYPE_REJECTED);
                } else {
                        this.bean.setListType(ReportRequestChangeAction.LIST_TYPE_PENDING);
                }

                this.getNavigationPanel(5);
                final String flagN = "N";
                this.bean.setEnableAll(flagN);
                model.terminate();
                if (applCode != null && applCode.length() > 0) {
                        this.getNavigationPanel(0);
                        this.bean.setEnableAll(flagN);
                }
                return SUCCESS;
        }
}
