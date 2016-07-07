package org.struts.action.D1;

import org.apache.log4j.Logger;
import org.paradyne.bean.D1.ReportRequestChange;
import org.paradyne.model.D1.ReportRequestChangeModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Mar 22, 2011
 */

public class ReportRequestChangeApprovalAction extends ParaActionSupport {

	static Logger logger = Logger.getLogger(ReportRequestChangeApprovalAction.class);
	ReportRequestChange bean;

	public String authorizedSignOff() throws Exception {

		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);

		boolean isUserAITApprover = model.isUserAITApprover(bean.getUserEmpId());
		String message = model.authorizedSignOff(bean, isUserAITApprover);
		addActionMessage(message);

		model.terminate();
		bean.setListType("pending");
		return input();
	}

	public String callPage() throws Exception {

		String listType = bean.getListType();

		if(listType.equals("pending")) {
			getPendingList();
		} else if(listType.equals("approved")) {
			getApprovedList();
		} else if(listType.equals("rejected")) {
			getRejectedList();
		}

		getNavigationPanel(1);
		return INPUT;
	}

	public String f9NextApprover() {

		String query = "SELECT EMP_TOKEN, EMP_FNAME ||' ' || EMP_MNAME || ' ' || EMP_LNAME AS ENAME, EMP_ID FROM HRMS_EMP_OFFC ";
		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS = 'S' AND EMP_ID NOT IN (SELECT DISTINCT PATH_APPROVER FROM HRMS_D1_REPORT_REQ_PATH WHERE REQ_ID = "+bean.getReqId()+") "
			+ "AND EMP_ID NOT IN(" + bean.getRequestorId() + ", " + bean.getUserEmpId() + ") ORDER BY UPPER(ENAME) ";

		String[] headers = {getMessage("approver.token"), getMessage("approver.name")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"nextApproverToken", "nextApproverName", "nextApproverId"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String forward() throws Exception {

		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);

		String message = model.forward(bean);
		addActionMessage(message);

		model.terminate();
		bean.setListType("pending");
		return input();
	}

	public String getApprovedList() {

		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);
		
		model.getApprovedList(bean, request);

		model.terminate();
		bean.setListType("approved");
		return INPUT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}

	public String getPendingList() {

		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);

		boolean isUserAITApprover = model.isUserAITApprover(bean.getUserEmpId());
		boolean isUserAManager = true;

		if(isUserAITApprover) {
			isUserAManager = false;
		}

		model.getPendingList(bean, isUserAManager, isUserAITApprover, request);

		model.terminate();
		bean.setListType("pending");
		return INPUT;
	}

	public String getRejectedList() {
		
		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);

		boolean isUserAITApprover = model.isUserAITApprover(bean.getUserEmpId());
		boolean isUserAManager = true;

		if(isUserAITApprover) {
			isUserAManager = false;
		}
		
		model.getRejectedList(bean, isUserAManager, isUserAITApprover, request);

		model.terminate();
		bean.setListType("rejected");
		return INPUT;
	}

	@Override
	public String input() throws Exception {

		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);
		String listType = model.checkNull(bean.getListType());

		if(listType.equals("")) {
			getPendingList();
		} else {
			if(listType.equals("approved")) {
				getApprovedList();
			} else if(listType.equals("rejected")) {
				getRejectedList();
			} else {
				getPendingList();
			}
		}
		
		boolean userIsAITApprover = model.isUserAITApprover(bean.getUserEmpId());
		bean.setUserAITApprover(userIsAITApprover);
		
		if(!userIsAITApprover) {
			bean.setUserAManager(true);
		}
		
		model.terminate();
		return INPUT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new ReportRequestChange();
		bean.setMenuCode(2034);
	}

	@Override
	public void prepare_withLoginProfileDetails() throws Exception {

		String poolName = String.valueOf(session.getAttribute("session_pool"));

		if(!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}

		String dataPath = getText("data_path") + "/upload" + poolName + "/REPORT_REQUEST/";
		bean.setDataPath(dataPath);
	}

	public String reject() throws Exception {

		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);

		String message = model.reject(bean);
		addActionMessage(message);

		model.terminate();
		bean.setListType("reject");
		return input();
	}

	public String sendBack() throws Exception {

		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);

		String message = model.sendBack(bean);
		addActionMessage(message);

		model.terminate();
		bean.setListType("pending");
		return input();
	}

	public String viewDetails() {

		ReportRequestChangeModel model = new ReportRequestChangeModel();
		model.initiate(context, session);
		model.terminate();

		String reqId = request.getParameter("reqId");
		model.setApplicationDetailsForView(bean, reqId);
		model.setApproverCommentsList(bean);
		bean.setItApproversExist(model.isITApproversExist());
		String status = bean.getStatus();

		if(status.equals("P") || status.equals("C")) {
			getNavigationPanel(1);
			bean.setShowNextApprover(true);
			bean.setShowApproverComments(true);
		} else if(status.equals("F") && bean.isUserAITApprover()) {
			getNavigationPanel(2);
			bean.setShowNextApprover(false);
			bean.setShowApproverComments(true);
		} else {
			getNavigationPanel(3);
			bean.setShowNextApprover(false);
			bean.setShowApproverComments(false);
		}

		model.terminate();
		return SUCCESS;
	}
}