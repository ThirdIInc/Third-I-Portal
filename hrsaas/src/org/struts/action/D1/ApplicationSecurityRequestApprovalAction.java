package org.struts.action.D1;

import org.paradyne.bean.D1.ApplicationSecurityRequest;
import org.paradyne.model.D1.ApplicationSecurityRequestModel;
import org.paradyne.model.D1.HardwareSoftwareRequestApproverModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Mar 9, 2011
 */

public class ApplicationSecurityRequestApprovalAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ApplicationSecurityRequestApprovalAction.class);

	ApplicationSecurityRequest bean;

	public String approve() throws Exception {

		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
		model.initiate(context, session);

		String message = model.approve(bean);
		addActionMessage(message);
		bean.setListType("pending");

		model.terminate();
		return input();
	}

	public String callPage() {

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

	public String getApprovedList() {
		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
		model.initiate(context, session);

		boolean isUserAITApprover = model.isUserAITApprover(bean.getUserEmpId());

		if(isUserAITApprover) {
			model.getApprovedList(bean, isUserAITApprover, request);
			model.getApproveCacelledList(bean, isUserAITApprover, request);
		}

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

		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
		model.initiate(context, session);

		boolean isUserAITApprover = model.isUserAITApprover(bean.getUserEmpId());

		if(isUserAITApprover) {
			model.getPendingList(bean, isUserAITApprover, request);
			model.getPendingCancelList(bean, isUserAITApprover, request);
		}

		model.terminate();
		bean.setListType("pending");
		return INPUT;
	}

	public String getRejectedList() {

		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
		model.initiate(context, session);

		boolean isUserAITApprover = model.isUserAITApprover(bean.getUserEmpId());

		if(isUserAITApprover) {
			model.getRejectedList(bean, isUserAITApprover, request);
		}

		model.terminate();
		bean.setListType("rejected");
		return INPUT;
	}

	@Override
	public String input() throws Exception {

		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
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

		return INPUT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new ApplicationSecurityRequest();
		bean.setMenuCode(2020);
	}

	@Override
	public void prepare_withLoginProfileDetails() throws Exception {

		String poolName = String.valueOf(session.getAttribute("session_pool"));

		if(!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}

		String dataPath = getText("data_path") + "/upload" + poolName + "/APPLICATION_SECURITY/";
		bean.setDataPath(dataPath);
	}

	public String reject() throws Exception {

		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
		model.initiate(context, session);

		String message = model.reject(bean);
		addActionMessage(message);
		bean.setListType("reject");

		model.terminate();
		return input();
	}

	public String sendBack() throws Exception {

		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
		model.initiate(context, session);

		String message = model.sendBack(bean);
		addActionMessage(message);
		bean.setListType("pending");

		model.terminate();
		return input();
	}

	public String applicationSearch()
	{
		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
		model.initiate(context, session);
		bean.setTrackingFlag("true");
		String applnSecReqId = bean.getHiddenSearchId();
		System.out.println("applnSecReqId   ======  " + applnSecReqId);
		
		String status = request.getParameter("status");
		
		bean.setApplnStatus(status);
		
		/*
         * FOR SUPER USER
         */
        final String applCode = request.getParameter("applCode");
        if (applCode != null && applCode.length() > 0) {
                applnSecReqId = applCode;
        }
        
		model.setApplicationDetailsForApproval(applnSecReqId, bean);
		model.getApplicationList(bean, request);
		model.setSearchApproverCommentsList(bean);
		String statusNew =bean.getStatus().trim();
		System.out.println("statusNew ======  " + statusNew);

		
		if(bean.getStatus().equals("Pending") || bean.getStatus().equals("Applied For Cancellation") ) {
			getNavigationPanel(1);
			System.out.println("in if part===222===");
			
		} else {
			System.out.println("in else part==111====");
			getNavigationPanel(2);
		}
	
		if (applCode != null && applCode.length() > 0) {
			if(bean.getStatus().equals("Draft") || bean.getStatus().equals("Approved") ||bean.getStatus().equals("Cancellation Approved")||bean.getStatus().equals("Cancellation Rejected")||bean.getStatus().equals("Sent Back"))
			{
				getNavigationPanel(4);
				bean.setEnableAll("N");
			}else if(bean.getStatus().equals("Pending")|| bean.getStatus().equals("Applied For Cancellation"))
				{
					bean.setApplnStatus("P");
					getNavigationPanel(3);
					//bean.setEnableAll("N");
				}
    }
		model.terminate();
		return SUCCESS;
		
	}
	
	public String viewDetails() {

		ApplicationSecurityRequestModel model = new ApplicationSecurityRequestModel();
		model.initiate(context, session);
		bean.setTrackingFlag("true");
		String applnSecReqId = request.getParameter("applnSecReqId");
		System.out.println("applnSecReqId   ======  " + applnSecReqId);
		
		String status = request.getParameter("status");
		bean.setApplnStatus(status);
		
		/*
         * FOR SUPER USER
         */
        final String applCode = request.getParameter("applCode");
        if (applCode != null && applCode.length() > 0) {
                applnSecReqId = applCode;
        }
        
		model.setApplicationDetailsForApproval(applnSecReqId, bean);
		model.getApplicationList(bean, request);
		model.setApproverCommentsList(bean);
		String status1 = bean.getStatus();
		System.out.println("status1 ----------" + status1);
		
		if(bean.getStatus().equals("Pending") || bean.getStatus().equals("Applied For Cancellation") ) {
			//bean.setApplnStatus("P");
			getNavigationPanel(1);
			System.out.println("in if part======");
			
		} else {
			System.out.println("in else part======");
			getNavigationPanel(2);
		}
	
		if (applCode != null && applCode.length() > 0) {
			if(bean.getStatus().equals("Draft") || bean.getStatus().equals("Approved") ||bean.getStatus().equals("Cancellation Approved")||bean.getStatus().equals("Cancellation Rejected")||bean.getStatus().equals("Sent Back"))
			{
				getNavigationPanel(4);
				bean.setEnableAll("N");
			}else if(bean.getStatus().equals("Pending")|| bean.getStatus().equals("Applied For Cancellation"))
				{
					bean.setStatus("P");
					getNavigationPanel(3);
					//bean.setEnableAll("N");
				}
    }
		model.terminate();
		return SUCCESS;
	}
	
	public String f9action() throws Exception {
		
		HardwareSoftwareRequestApproverModel model = new HardwareSoftwareRequestApproverModel();
		String query = "";
	
		String userId = bean.getUserEmpId();
        System.out.println("getApplicationStatus -------++++++--------- " + bean.getAppSecStatus());



		if (bean.getAppSecStatus().equals("P")) {
			query = " SELECT APPLN_SEC_TRACKING_NO,(OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME ||' '|| OFFC.EMP_LNAME) AS MANAGER_NAME , "
			+ " APPLN_SEC_EMP_TOKEN AS EMPLOYEE_ID , APPLN_SEC_EMP_NAME AS  EMPLOYEE_NAME , TO_CHAR(APPPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') ,APPPSEC.APPLN_SEC_ID ,APPPSEC.APPLN_SEC_STATUS"
			+ " FROM HRMS_D1_APPLN_SECURITY APPPSEC "
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID =  APPPSEC.APPLN_SEC_MGR_ID) " 
			+ " WHERE APPPSEC.APPLN_SEC_STATUS = 'P' " 
			+ " ORDER BY APPPSEC.APPLN_SEC_REQ_DATE, APPPSEC.APPLN_SEC_MGR_ID , APPPSEC.APPLN_SEC_EMP_ID DESC ";
			
		}
		
		if (bean.getAppSecStatus().equals("A")) {
			query = " SELECT APPLN_SEC_TRACKING_NO,(OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME ||' '|| OFFC.EMP_LNAME) AS MANAGER_NAME , "
			+ " APPLN_SEC_EMP_TOKEN AS EMPLOYEE_ID , APPLN_SEC_EMP_NAME AS  EMPLOYEE_NAME, TO_CHAR(APPPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY') , APPPSEC.APPLN_SEC_ID ,APPPSEC.APPLN_SEC_STATUS"
			+ " FROM HRMS_D1_APPLN_SECURITY APPPSEC "
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID =  APPPSEC.APPLN_SEC_MGR_ID) " 
			+ " WHERE APPPSEC.APPLN_SEC_STATUS = 'A' " 
			+ " ORDER BY APPPSEC.APPLN_SEC_REQ_DATE, APPPSEC.APPLN_SEC_MGR_ID , APPPSEC.APPLN_SEC_EMP_ID DESC ";
		
		}
		
		if (bean.getAppSecStatus().equals("R")) {
			query = " SELECT APPLN_SEC_TRACKING_NO,(OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME ||' '|| OFFC.EMP_LNAME) AS MANAGER_NAME , "
			+ " APPLN_SEC_EMP_TOKEN AS EMPLOYEE_ID , APPLN_SEC_EMP_NAME AS  EMPLOYEE_NAME , TO_CHAR(APPPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY'), APPPSEC.APPLN_SEC_ID ,APPPSEC.APPLN_SEC_STATUS "
			+ " FROM HRMS_D1_APPLN_SECURITY APPPSEC "
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID =  APPPSEC.APPLN_SEC_MGR_ID) " 
			+ " WHERE APPPSEC.APPLN_SEC_STATUS = 'R' " 
			+ " ORDER BY APPPSEC.APPLN_SEC_REQ_DATE, APPPSEC.APPLN_SEC_MGR_ID , APPPSEC.APPLN_SEC_EMP_ID DESC ";
		
		}
		
		
		if (bean.getAppSecStatus().equals("C")) {
			query = " SELECT APPLN_SEC_TRACKING_NO,(OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME ||' '|| OFFC.EMP_LNAME) AS MANAGER_NAME , "
			+ " APPLN_SEC_EMP_TOKEN AS EMPLOYEE_ID , APPLN_SEC_EMP_NAME AS  EMPLOYEE_NAME , TO_CHAR(APPPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY'),APPPSEC.APPLN_SEC_ID ,APPPSEC.APPLN_SEC_STATUS" 
			+ " FROM HRMS_D1_APPLN_SECURITY APPPSEC "
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID =  APPPSEC.APPLN_SEC_MGR_ID) " 
			+ " WHERE APPPSEC.APPLN_SEC_STATUS = 'C' " 
			+ " ORDER BY APPPSEC.APPLN_SEC_REQ_DATE, APPPSEC.APPLN_SEC_MGR_ID , APPPSEC.APPLN_SEC_EMP_ID DESC";
		
		}
		
	
		if (bean.getAppSecStatus().equals("X")) {
			query = " SELECT APPLN_SEC_TRACKING_NO,(OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME ||' '|| OFFC.EMP_LNAME) AS MANAGER_NAME , "
			+ " APPLN_SEC_EMP_TOKEN AS EMPLOYEE_ID , APPLN_SEC_EMP_NAME AS  EMPLOYEE_NAME , TO_CHAR(APPPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY'),APPPSEC.APPLN_SEC_ID ,APPPSEC.APPLN_SEC_STATUS "
			+ " FROM HRMS_D1_APPLN_SECURITY APPPSEC "
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID =  APPPSEC.APPLN_SEC_MGR_ID) " 
			+ " WHERE APPPSEC.APPLN_SEC_STATUS = 'X' " 
			+ " ORDER BY APPPSEC.APPLN_SEC_REQ_DATE, APPPSEC.APPLN_SEC_MGR_ID , APPPSEC.APPLN_SEC_EMP_ID DESC";
		
		}
		
		if (bean.getAppSecStatus().equals("Z")) {
			query = " SELECT APPLN_SEC_TRACKING_NO,(OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME ||' '|| OFFC.EMP_LNAME) AS MANAGER_NAME , "
			+ " APPLN_SEC_EMP_TOKEN AS EMPLOYEE_ID , APPLN_SEC_EMP_NAME AS  EMPLOYEE_NAME , TO_CHAR(APPPSEC.APPLN_SEC_REQ_DATE, 'DD-MM-YYYY'),APPPSEC.APPLN_SEC_ID ,APPPSEC.APPLN_SEC_STATUS "
			+ " FROM HRMS_D1_APPLN_SECURITY APPPSEC "
			+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID =  APPPSEC.APPLN_SEC_MGR_ID) " 
			+ " WHERE APPPSEC.APPLN_SEC_STATUS = 'Z' " 
			+ " ORDER BY APPPSEC.APPLN_SEC_REQ_DATE, APPPSEC.APPLN_SEC_MGR_ID , APPPSEC.APPLN_SEC_EMP_ID DESC";
		
		}
		
		String[] headers = { "Tracking Number", "Manager Name", "Token Number","Employee Name"," Requested Date"};

		String[] headerWidth = { "15", "30", "10","25","20"};

		String[] fieldNames = { "trackingNumIterator","managerName","requesterTokenIterator", "requesterNameIterator","applicationDateIterator","hiddenSearchId","appSecStatus"};

		int[] columnIndex = { 0, 1, 2, 3 ,4, 5, 6};
		String submitFlag = "true";

		String submitToMethod = "ApplicationSecurityRequestApproval_applicationSearch.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	
}