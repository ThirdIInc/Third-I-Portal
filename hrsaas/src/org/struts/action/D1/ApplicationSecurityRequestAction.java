package org.struts.action.D1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import org.paradyne.bean.D1.ApplicationSecurityRequest;
import org.paradyne.model.D1.ApplicationSecurityRequestModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Feb 28, 2011 Action for add/delete/update/view/submit for approve IT security requests.
 */

public class ApplicationSecurityRequestAction extends ParaActionSupport {

        /**
         * Log4j logger.
         */
        private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ApplicationSecurityRequestAction.class);
        /**
         * List type for pending applications.
         */
        private static final String LIST_TYPE_PENDING = "pending";

        /**
         * List type for approved applications.
         */
        private static final String LIST_TYPE_APPROVED = "approved";

        /**
         * List type for canceled applications.
         */
        private static final String LIST_TYPE_CANCELLED = "cancelled";

        /**
         * List type for rejected applications.
         */
        private static final String LIST_TYPE_REJECTED = "rejected";

        /**
         * Application is get from request.
         */
        private static final String APPLN_SEC_REQ_ID = "applnSecReqId";

        /**
         * Used for f9 windows.
         */
        private static final String F9_PAGE = "f9page";

        /**
         * Used to set false value.
         */
        private static final String FLAG_FALSE = "false";

        /**
         * Object of ApplicationSecurityRequest.
         */
        private ApplicationSecurityRequest bean;

        /**
         * Method to add new application.
         * 
         * @return SUCCESS
         */
        public String addApplication() {

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);
                bean.setTrackingFlag("false");
                this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_PENDING);

                this.bean.setStatus(ApplicationSecurityRequestModel.STATUS_DRAFT);

                if (this.bean.isGeneralFlag()) {
                        this.bean.setMgrId(this.bean.getUserEmpId());
                        model.setManagerName(this.bean);
                        this.setManagerDetails();
                }

                model.getApplicationList(this.bean, request);

                this.getNavigationPanel(2);
                model.terminate();
                return SUCCESS;
        }

        /**
         * Method to get list of applications as per the list type, when clicked on paging buttons.
         * 
         * @return INPUT
         */
        public String callPage() {

                final String listType = this.bean.getListType();

                if (listType.equals(ApplicationSecurityRequestAction.LIST_TYPE_PENDING)) {
                        this.getPendingList();
                } else if (listType.equals(ApplicationSecurityRequestAction.LIST_TYPE_APPROVED)) {
                        this.getApprovedList();
                } else if (listType.equals(ApplicationSecurityRequestAction.LIST_TYPE_CANCELLED)) {
                        this.getCancelledList();
                } else if (listType.equals(ApplicationSecurityRequestAction.LIST_TYPE_REJECTED)) {
                        this.getRejectedList();
                }

                this.getNavigationPanel(1);
                return INPUT;
        }

        /**
         * Method to cancel the application.
         * 
         * @return this.input()
         * @throws Exception - this.input() throws Exception
         */
        public String cancel() throws Exception {

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                final String message = model.cancel(this.bean);
                this.addActionMessage(message);
                this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_APPROVED);

                model.terminate();
                return this.input();
        }

        /**
         * Method to check whether result passed is null or not.
         * 
         * @param result Contains string data to be checked
         * @return String after checking null
         */
        public String checkNull(final String result) {

                if (result == null || "null".equals(result)) {
                        return "";
                } else {
                        return result;
                }
        }

        /**
         * Method to delete the application.
         * 
         * @return this.input()
         * @throws Exception - this.input() throws Exception
         */
        public String delete() throws Exception {

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                final String applicationId = this.bean.getApplicationId();
                final String message = model.delete(applicationId);
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

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
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

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);
                bean.setTrackingFlag("true");
                final String applnSecReqId = request.getParameter(ApplicationSecurityRequestAction.APPLN_SEC_REQ_ID);
                model.setApplicationDetails(applnSecReqId, this.bean);
                model.setApproverCommentsList(this.bean);
                model.getApplicationList(this.bean, request);
                this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_PENDING);
                this.getNavigationPanel(3);
                this.bean.setEnableAll("Y");

                model.terminate();
                return SUCCESS;
        }

        /**
         * Method to open f9 window to copy department.
         * 
         * @return f9page
         */
        public String f9copyDeptNumber() {

                final String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY  DEPT_ID DESC ";

                // query += getprofileQuery(bean);

                // query += " ORDER BY HRMS_D1_DEPARTMENT.DEPT_ID";

                final String [] headers = {"Department Code", "Department Name"};

                final String [] headerWidth = {"20", "80"};

                final String [] fieldNames = {"deptCode", "copyMgrDept"};

                final int [] columnIndex = {0, 1};

                final String submitFlag = ApplicationSecurityRequestAction.FLAG_FALSE;

                final String submitToMethod = "";

                this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

                return ApplicationSecurityRequestAction.F9_PAGE;

        }

        // added by ganesh 11 May 2011 11:00 am
        /**
         * Method to open f9 window for selecting approver.
         * 
         * @return f9page
         */
        public String f9deptNumber() {

                 String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT  ";

                if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
    				query += " WHERE DEPT_DIV_CODE IN ("
    						+ bean.getUserProfileDivision() + ")";
    			}
                query += " ORDER BY DEPT_ID DESC";
                // query += " ORDER BY HRMS_D1_DEPARTMENT.DEPT_ID";

                final String [] headers = {"Department Code", "Department Name"};

                final String [] headerWidth = {"20", "80"};

                final String [] fieldNames = {"deptCode", "mgrDepartment"};

                final int [] columnIndex = {0, 1};

                final String submitFlag = ApplicationSecurityRequestAction.FLAG_FALSE;

                final String submitToMethod = "";

                this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

                return ApplicationSecurityRequestAction.F9_PAGE;
        }

        /**
         * Method to open f9 window for employee.
         * 
         * @return f9page
         */
        public String f9Employee() {

                String query = "SELECT EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME || ' ' || EMP_LNAME AS ENAME, HRMS_EMP_OFFC.EMP_ID, RANK_NAME, " +
                        "ADD_EMAIL, ADD_PH1, ADD_EXTENSION, ADD_FAX FROM HRMS_EMP_OFFC LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " +
                        "LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE = 'P') ";

                if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
                        query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
                } else {
                        query += " WHERE 1 = 1 ";
                }

                query += " AND EMP_STATUS = 'S' ORDER BY UPPER(ENAME) ";

                final String [] headers = {this.getMessage("employee.f9.token"), this.getMessage("employee.f9.name") };

                final String [] headerWidth = {"20", "80"};

                final String [] fieldNames = {"empToken", "empName", "empId", "empDesignation", "copyMgrEmail", "copyMgrWorkPhone", "copyMgrExt", 
                        "copyMgrFax", };

                final int [] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7};

                final String submitFlag = ApplicationSecurityRequestAction.FLAG_FALSE;

                final String submitToMethod = "";

                this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

                return ApplicationSecurityRequestAction.F9_PAGE;
        }

        /**
         * Method to open f9 window for manager.
         * 
         * @return f9page
         */
        public String f9Manager() {

                String query = "SELECT EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME, HRMS_EMP_OFFC.EMP_ID, DIV_NAME, " +
                		"RANK_NAME, DEPT_NAME, CITY.LOCATION_NAME AS CITY, STATE.LOCATION_NAME AS STATE, ADD_PINCODE, ADD_PH1, ADD_EXTENSION, " +
                		"ADD_FAX, ADD_EMAIL FROM HRMS_EMP_OFFC " +
                		"LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) " +
                		"LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " +
                		"LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " +
                		"LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE = 'P') " +
                		"LEFT JOIN HRMS_LOCATION CITY ON(CITY.LOCATION_CODE = HRMS_EMP_ADDRESS.ADD_CITY) " +
                		"LEFT JOIN HRMS_LOCATION STATE ON(STATE.LOCATION_CODE = CITY.LOCATION_PARENT_CODE) ";
              //  query += this.getprofileQuery(this.bean);
                if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
                    query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
            } else {
                    query += " WHERE 1 = 1 ";
            }
                query += " AND EMP_STATUS = 'S' ORDER BY UPPER(EMP_NAME) ";

                final String [] headers = {this.getMessage("manager.f9.token"), this.getMessage("manager.f9.name")};

                final String [] headerWidth = {"20", "80"};

                final String [] fieldNames = {"mgrToken", "mgrName", "mgrId", "mgrDivision", "mgrDesignation", "mgrDepartment", "mgrCity", "mgrState",
                        "mgrPincode", "mgrWorkPhone", "mgrExt", "mgrFax", "mgrEmail", };

                final int [] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

                final String submitFlag = ApplicationSecurityRequestAction.FLAG_FALSE;

                final String submitToMethod = "";

                this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

                return ApplicationSecurityRequestAction.F9_PAGE;
        }

        /**
         * Method to get list of approved applications.
         * 
         * @return INPUT
         */
        public String getApprovedList() {

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                this.setTotalPageAndPageNoToZero();
                model.getApprovedList(this.bean, false, request);
                model.getApproveCacelledList(this.bean, false, request);

                model.terminate();
                this.getNavigationPanel(1);
                this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_APPROVED);
                return INPUT;
        }

        /**
         * Method to get list of canceled applications.
         * 
         * @return INPUT
         */
        public String getCancelledList() {

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                this.setTotalPageAndPageNoToZero();
                model.getPendingCancelList(this.bean, false, request);

                model.terminate();
                this.getNavigationPanel(1);
                this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_CANCELLED);
                return INPUT;
        }

        /**
         * Method to get initiator.
         */
        private void getInitiator() {

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                final Object [][] initiatorObj = model.getInitiatorDetails(this.bean.getUserEmpId());

                if (initiatorObj != null && initiatorObj.length > 0) {
                        this.bean.setInitiatorId(model.checkNull(String.valueOf(initiatorObj [0] [0])));
                        this.bean.setInitiatorName(model.checkNull(String.valueOf(initiatorObj [0] [1])));
                        this.bean.setCompletedOn(model.checkNull(String.valueOf(initiatorObj [0] [2])));
                }

                model.terminate();
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.opensymphony.xwork2.ModelDriven#getModel()
         */
        /**
         * Method to get bean.
         * 
         * @return bean
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

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                this.setTotalPageAndPageNoToZero();
                model.getDraftList(this.bean, request);
                model.getPendingList(this.bean, false, request);
                model.getSendBackList(this.bean, request);

                this.getNavigationPanel(1);
                model.terminate();
                this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_PENDING);
                return INPUT;
        }

        /**
         * Method to get list of rejected applications.
         * 
         * @return INPUT
         */
        public String getRejectedList() {

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                this.setTotalPageAndPageNoToZero();
                model.getRejectedList(this.bean, false, request);
                model.getRejectedCancelList(this.bean, false, request);

                model.terminate();
                this.getNavigationPanel(1);
                this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_REJECTED);
                return INPUT;
        }

        @Override
        public String input() throws Exception {

                this.setTotalPageAndPageNoToZero();

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                final String listType = model.checkNull(this.bean.getListType());

                if ("".equals(listType)) {
                        this.getPendingList();
                } else {
                        if (listType.equals(ApplicationSecurityRequestAction.LIST_TYPE_APPROVED)) {
                                this.getApprovedList();
                        } else if (listType.equals(ApplicationSecurityRequestAction.LIST_TYPE_CANCELLED)) {
                                this.getCancelledList();
                        } else if (listType.equals(ApplicationSecurityRequestAction.LIST_TYPE_REJECTED)) {
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
         * @throws IOException for writing data into stream.
         */
        public void openAttachedFile() throws IOException {

                final String addedFile = this.bean.getAddedFile();
                final String hash = "#";
                final String dot = ".";
                final String [] extension = addedFile.replace(dot, hash).split(hash);
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
                        response.setHeader("Content-disposition", "attachment;filename= \"" + addedFile + "\"");

                        int iChar;
                        fsStream = new FileInputStream(filePath);
                        oStream = response.getOutputStream();

                        while ((iChar = fsStream.read()) != -1) {
                                oStream.write(iChar);
                        }
                } catch (final Exception e) {
                        ApplicationSecurityRequestAction.logger.error("Exception in openTemplate:" + e);
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

        /**
         * Method to populate manager details on page.
         * 
         * @param mgrDetails - Contains manager details
         */
        private void populateManagerDetails(final Object [][] mgrDetails) {

                this.bean.setMgrDivision(this.checkNull(String.valueOf(mgrDetails [0] [0])));
                this.bean.setMgrDesignation(this.checkNull(String.valueOf(mgrDetails [0] [1])));
                this.bean.setMgrDepartment(this.checkNull(String.valueOf(mgrDetails [0] [2])));
                this.bean.setMgrCity(this.checkNull(String.valueOf(mgrDetails [0] [3])));
                this.bean.setMgrState(this.checkNull(String.valueOf(mgrDetails [0] [4])));
                this.bean.setMgrPincode(this.checkNull(String.valueOf(mgrDetails [0] [5])));
                this.bean.setMgrWorkPhone(this.checkNull(String.valueOf(mgrDetails [0] [6])));
                this.bean.setMgrExt(this.checkNull(String.valueOf(mgrDetails [0] [7])));
                this.bean.setMgrFax(this.checkNull(String.valueOf(mgrDetails [0] [8])));
                this.bean.setMgrEmail(this.checkNull(String.valueOf(mgrDetails [0] [9])));
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.struts.lib.ParaActionSupport#prepare_local()
         */
        @Override
        public void prepare_local() throws Exception {

                this.bean = new ApplicationSecurityRequest();
                this.bean.setMenuCode(2002);
        }

        @Override
        public void prepare_withLoginProfileDetails() throws Exception {

                this.setRequestDate();

                String poolName = String.valueOf(session.getAttribute("session_pool"));

                if (!("".equals(poolName) || poolName == null)) {
                        poolName = "/" + poolName;
                }

                final String dataPath = this.getText("data_path") + "/upload" + poolName + "/APPLICATION_SECURITY/";
                this.bean.setDataPath(dataPath);

                this.getInitiator();
        }

        /**
         * Method to reset form fields.
         * 
         * @return SUCCESS
         */
        public String reset() {

                this.bean.setApplicationId("");
                this.setRequestDate();
                this.bean.setRequestType("");
                this.bean.setStatus(ApplicationSecurityRequestModel.STATUS_DRAFT);
                this.bean.setMgrId("");
                this.bean.setMgrToken("");
                this.bean.setMgrName("");
                this.bean.setMgrDivision("");
                this.bean.setMgrDesignation("");
                this.bean.setMgrDepartment("");
                this.bean.setMgrCity("");
                this.bean.setMgrState("");
                this.bean.setMgrPincode("");
                this.bean.setMgrWorkPhone("");
                this.bean.setMgrExt("");
                this.bean.setMgrFax("");
                this.bean.setMgrEmail("");
                this.bean.setEmpFileRequest("");
                this.bean.setEmpFile("");
                this.bean.setAddedFile("");
                this.bean.setEmpId("");
                this.bean.setEmpToken("");
                this.bean.setEmpName("");
                this.bean.setEmpDesignation("");
                this.bean.setEmpType("");
                this.bean.setEmpExpDate("");
                this.bean.setCopyMgrDiv("");
                this.bean.setCopyMgrDept("");
                this.bean.setCopyMgrCity("");
                this.bean.setCopyMgrState("");
                this.bean.setCopyMgrPincode("");
                this.bean.setCopyMgrEmail("");
                this.bean.setCopyMgrWorkPhone("");
                this.bean.setCopyMgrExt("");
                this.bean.setCopyMgrFax("");
                this.bean.setHdnApplications("");
                this.bean.setHdnSections("");
                this.bean.setAsteaWorkgroup("");
                this.bean.setAsteaFieldRole("");
                this.bean.setAsteaFieldDefaultWarehouse("");
                this.bean.setAsteaFinanceRole("");
                this.bean.setAsteaLogisticsRole("");
                this.bean.setUnixHost1("");
                this.bean.setUnixHost2("");
                this.bean.setUnixHost3("");
                this.bean.setUnixHost4("");
                this.bean.setUnixHostAccess("");
                this.bean.setUnixGroup("");
                this.bean.setNtHost1("");
                this.bean.setNtHost2("");
                this.bean.setNtHost3("");
                this.bean.setNtHost4("");
                this.bean.setNtHostAccess("");
                this.bean.setNtGroup("");
                this.bean.setFrontDoorAccess(ApplicationSecurityRequestAction.FLAG_FALSE);
                this.bean.setDataRoomAccess(ApplicationSecurityRequestAction.FLAG_FALSE);
                this.bean.setPictureIdAccess(ApplicationSecurityRequestAction.FLAG_FALSE);
                this.bean.setAgency("");
                this.bean.setComments("");

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);
                model.getApplicationList(this.bean, request);
                model.terminate();

                this.getNavigationPanel(1);

                return SUCCESS;
        }

        /**
         * Method to send application for approval.
         * 
         * @return this.input()
         * @throws Exception - this.input() throws Exception
         */
        public String sendForApproval() throws Exception {
                
                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                final String message = model.sendForApproval(this.bean);
                this.addActionMessage(message);

                model.terminate();
                return this.input();
        }

        /**
         * Method to set manager details.
         * 
         * @return SUCCESS
         */
        public String setManagerDetails() {

                final String mgrId = this.bean.getMgrId();

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);

                final Object [][] mgrDetails = model.setManagerDetails(mgrId);
                this.populateManagerDetails(mgrDetails);
                this.getNavigationPanel(1);

                model.terminate();
                return SUCCESS;
        }

        /**
         * Method to set request date.
         */
        private void setRequestDate() {

                final Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                
                final String zero = "0";
                final String date = cal.get(Calendar.DATE) < 10 ? zero + String.valueOf(cal.get(Calendar.DATE)) : String.valueOf(cal.get(Calendar.DATE));
                final int intMonth = cal.get(Calendar.MONTH) + 1;
                final String month = intMonth < 10 ? zero + String.valueOf(intMonth) : String.valueOf(intMonth);
                
                final String dash = "-";
                final String currentDate = date + dash + month + dash + cal.get(Calendar.YEAR);
                
                this.bean.setRequestDate(currentDate);
        }

        /**
         * Method to set total page and page no. to zero
         */
        private void setTotalPageAndPageNoToZero() {
                request.setAttribute("totalPageForDraft", 0);
                request.setAttribute("pageNoForDraft", 0);
                request.setAttribute("totalPageForApplnInProc", 0);
                request.setAttribute("pageNoForApplnInProc", 0);
                request.setAttribute("totalPageForSendBack", 0);
                request.setAttribute("pageNoForSendBack", 0);
                request.setAttribute("totalPageForApprove", 0);
                request.setAttribute("pageNoForApprove", 0);
                request.setAttribute("totalPageForApproveCancel", 0);
                request.setAttribute("pageNoForApproveCancel", 0);
        }

        /**
         * Method to view details.
         * 
         * @return SUCCESS
         */
        public String viewDetails() {

                final ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
                model.initiate(context, session);
                bean.setTrackingFlag("true");
                String applnSecReqId = request.getParameter(ApplicationSecurityRequestAction.APPLN_SEC_REQ_ID);
                /*
                 * FOR SUPER USER
                 */
                final String applCode = request.getParameter("applCode");
                if (applCode != null && applCode.length() > 0) {
                        applnSecReqId = applCode;
                }
                model.setApplicationDetails(applnSecReqId, this.bean);
                model.getApplicationList(this.bean, request);

                final String status = this.bean.getStatus();
                final String statusPending = "P";
                final String statusApproved = "A";
                final String statusCancelApproved = "X";
                final String statusCancel = "C";
                
                if (!status.equals(statusPending)) {
                        model.setApproverCommentsList(this.bean);
                }

                if (status.equals(statusApproved)) {
                        this.getNavigationPanel(5);
                } else {
                        this.getNavigationPanel(4);
                }

                if (status.equals(statusPending)) {
                        this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_PENDING);
                } else if (status.equals(statusApproved) || status.equals(statusCancelApproved)) {
                        this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_APPROVED);
                } else if (status.equals(statusCancel)) {
                        this.bean.setListType(ApplicationSecurityRequestAction.LIST_TYPE_CANCELLED);
                }

                final String enableAllToN = "N";
                this.bean.setEnableAll(enableAllToN);
                model.terminate();
                
                if (applCode != null && applCode.length() > 0) {
                        this.getNavigationPanel(0);
                        this.bean.setEnableAll(enableAllToN);
                }
                return SUCCESS;
        }
        
        
}
