package org.struts.action.CR;


import javax.servlet.http.HttpSession;

import org.paradyne.bean.CR.PerformanceMetrics;
import org.paradyne.model.CR.PerformanceMetricsModel;
/*
 * GTL-AA1385
 * Date:11.06.2012
 */
public class PerformanceMetricsAction extends org.struts.lib.ParaActionSupport {
	PerformanceMetrics bean = null;

	public PerformanceMetrics getBean() {
		return bean;
	}

	public void setbean(PerformanceMetrics bean) {
		this.bean = bean;
	} 
 
	public Object getModel() {
		return bean;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public String input() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		
		try {
			String dashBoardID = request.getParameter("dashBoardID");
			String dashBoardName= request.getParameter("dashBoardName");
			if(!dashBoardName.equals("null")||!dashBoardName.equals("")){
			bean.setDashBoardName(dashBoardName);}
			else{
				bean.setDashBoardName("CRM Dashboard");	
			}
		String searchMessage = request.getParameter("searchMessage");
		bean.getHiddenSearchMessage();
		 HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
		    String userType=(String) session.getAttribute("userType");

		 String  accountNumber = model.performanceMetricsDetails(bean, request,bean.getHiddenSearchMessage(),dashBoardID,userEmpID,userType,"false");
		 request.setAttribute("accountNumber", accountNumber);
		 request.setAttribute("userType", userType);
		 request.setAttribute("userEmpID", userEmpID);
		 request.setAttribute("searchMessage", searchMessage);
		bean.setHiddenSearchMessage(bean.getHiddenSearchMessage());
		bean.setDashBoardID(dashBoardID);
		bean.setAutoId("");
		String queryParam="?param=1";
		queryParam+="&dashBoardID="+dashBoardID;
		queryParam+="&accountNumber="+accountNumber;
		queryParam+="&userType="+userType;
		queryParam+="&userEmpID="+userEmpID;
		//queryParam+="&searchMessage="+searchMessage;
		//getNavigationPanel(1);
		/*String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/PerformanceMetrics_input.action"+queryParam;
		System.out.println(D1_URL);
		response.sendRedirect(D1_URL);*/
		model.terminate();
		///bean.setEnableAll("N");
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return INPUT;
		
	}
	
	public String cancel() {
		try {
			PerformanceMetricsModel model = new PerformanceMetricsModel();
			model.initiate(context, session);
			String dashBoardID = request.getParameter("dashBoardID");
			String searchMessage = request.getParameter("searchMessage");
			bean.setHiddenSearchMessage(searchMessage);
			 HttpSession session = request.getSession();
			    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
			    String userType=(String) session.getAttribute("userType");
			model.performanceMetricsDetails(bean, request,searchMessage,dashBoardID,userEmpID,userType,"false");
		///	reset();
		//	getNavigationPanel(1);
			model.terminate();
			return input();
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public void prepare_local() throws Exception {
		bean = new PerformanceMetrics();
		bean.setMenuCode(5038);
	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String searchMessage = request.getParameter("searchMessage");
		bean.setHiddenSearchMessage(searchMessage);
		String dashBoardID = request.getParameter("dashBoardID");
		 HttpSession session = request.getSession();
		 String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
		 String userType=(String) session.getAttribute("userType");
		model.performanceMetricsDetails(bean, request,searchMessage,dashBoardID,userEmpID,userType,"false");
		model.terminate();
	}

	public String callForParentAccouts() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String searchMessage = request.getParameter("searchMessage");
		bean.setHiddenSearchMessage(searchMessage);
		String dashBoardID = bean.getDashBoardID();
		 HttpSession session = request.getSession();
		 String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
		 String userType=(String) session.getAttribute("userType");
		 String parentFlag=(String)request.getParameter("parentFlag");
		 
		model.performanceMetricsDetails(bean, request,searchMessage,dashBoardID,userEmpID,userType,parentFlag);
		model.terminate();
		return INPUT;
	}
	
	
	
	
	
	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {
			bean.setHiddenColName("");
			bean.setGreenValStart("");
			bean.setGreenValEnd("");
			bean.setYellowValueStart("");
			bean.setYellowValueEnd("");
			bean.setRedValueStart("");
			bean.setRedValueEnd("");
		} catch (Exception e) {
			logger.error("Error in reset" + e);
		}
		return SUCCESS;
	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String savePerformanceMetrics() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
		 HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
		if (bean.getPerformanceMetricsId().equals("")) {
			result = model.savePerformanceMetrics(bean,request,userEmpID);
			if (result) {
				addActionMessage(getMessage("save"));
				reset();
				String requestID = bean.getAccountCode();
				//model.setperformanceMetricsDtl(bean,requestID);
				model.callForPerformanceMetrics(bean,requestID);
				model.callForSla(bean,request,requestID);
				//return SUCCESS;
			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				//reset();
			///	return SUCCESS;
			}//end of else
		}//end of nested if
	
		bean.setEnableAll("Y");
		return SUCCESS;
	}
	
	public String applyChildPerformanceMetrics() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
		 HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
			result = model.applyChildPerformanceMetrics(bean,request,userEmpID);
			if (result) {
				addActionMessage(getMessage("save"));
				reset();
				String requestID = bean.getAccountCode();
				//model.setperformanceMetricsDtl(bean,requestID);
				model.callForPerformanceMetrics(bean,requestID);
				//return SUCCESS;
			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				//reset();
			///	return SUCCESS;
			}//end of else
	
		bean.setEnableAll("Y");
		return SUCCESS;
	}
	
	public String data() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		///model.data(tm, request);
	//	model.gerSelectRecord(bean,request);
		getNavigationPanel(2);
		model.terminate();
		bean.setEnableAll("Y");
		return SUCCESS;
	}

	public String f9action() throws Exception { 
		String query = " SELECT ACCOUNT_ID, ACCOUNT_NAME,  " +
			" TO_CHAR(RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM'),TO_CHAR(RECORD_MODIFIEDON,'DD-MM-YYYY HH:MM AM') " +
			" , DECODE(IS_ACTIVE,'Y','Yes','N','No'), ACCOUNT_CODE FROM CR_CLIENT_MASTER ORDER BY  ACCOUNT_CODE ASC ";
		String[] headers ={  getMessage("acc.id"), getMessage("business.name"),"Created On","Last Modified",getMessage("is.active") }; 
		String[] headerWidth = { "20", "20","20", "20", "20" };
		String[] fieldNames = {  "accountId", "businessName", "recordCreatedOn" ,"recordModifiedOn","isActive","accountCode"};
		int[] columnIndex = { 0, 1, 2,3,4,5};
		String submitFlag = "true";
		String submitToMethod = "PerformanceMetrics_data.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	
	/**
	 * For Selecting relation to employee
	 * 
	 * @return String
	 */
	public String f9parent() {
		String query = "SELECT ACCOUNT_NAME,ACCOUNT_CODE FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='Y'" ;
		query +=" ORDER BY  ACCOUNT_CODE ASC ";
		String[] headers = {getMessage("business.name")};
		String[] headerWidth = {"100"};
		String[] fieldNames = {"parentName", "parentCode"};
		int[] columnIndex = {0, 1};
		String submitFlag = "true";
		String submitToMethod = "PerformanceMetrics_setData.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String f9misc() {
		String query = "SELECT MISC_NAME , MISC_ID FROM CR_MISC_MASTER " ;
		query +=" ORDER BY  MISC_ID ASC ";
		String[] headers = {getMessage("misc.name")};
		String[] headerWidth = {"100"};
		String[] fieldNames = {"miscName", "miscCode"};
		int[] columnIndex = {0, 1};
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	public String setData() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		///model.data(tm, request);
		model.getSelectRecord(bean,request);
		model.terminate();
		bean.setEnableAll("Y");
		return SUCCESS;
	}
	/**
	 * To callForPerformanceMetrics in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callForPerformanceMetrics() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String requestID = request.getParameter("accountId");
		/*model.callForPerformanceMetrics(bean,requestID);
		resetSLA();
		model.callForSla(bean,request,requestID);*/
		
		
		request.setAttribute("requestID", requestID);
		HttpSession session = request.getSession();
		
		  String id = session.getId();
		  System.out.println("");
		String queryParam="?param=1";
		queryParam+="&requestID="+requestID+"&jsessionid="+id;
		
		//queryParam+="&searchMessage="+searchMessage;
		//getNavigationPanel(1);
	
		  
		
		String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/PerformanceMetrics_callForPerformanceMetrics.action"+queryParam;
		System.out.println(D1_URL);
		response.sendRedirect(D1_URL);
		
		
		
		
		
		bean.setEnableAll("Y");
		bean.setAutoId("");
		model.terminate();
		return SUCCESS;
	}
	/**
	 * To callForEscalations in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callForEscalations() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String requestID = request.getParameter("accountId");
	//	model.callForEscalations(bean,requestID);
		
		
		request.setAttribute("requestID", requestID);
		
		String queryParam="?param=1";
		queryParam+="&requestID="+requestID;
		
		//queryParam+="&searchMessage="+searchMessage;
		//getNavigationPanel(1);
		String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/PerformanceMetrics_callForEscalations.action"+queryParam;
		System.out.println(D1_URL);
		response.sendRedirect(D1_URL);
		
		
		
		bean.setEnableAll("Y");
		model.terminate();
		return "escalationDtl";
	}
	/**
	 * Method to view Ot Register Dtl. 
	 * @return String
	 * @throws Exception
	 */
	public String generateListDtl() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		bean.setViewListDtlFlag(true);
		//model.generateDtlList(bean, request, bean.getFromDate(), bean.getToDate());	
		
		
		
		String queryParam="?param=1";
		queryParam+="&fromDate="+bean.getFromDate();
		queryParam+="&fromDate="+bean.getToDate();
		
		//queryParam+="&searchMessage="+searchMessage;
		//getNavigationPanel(1);
		/*String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/PerformanceMetrics_generateListDtl.action"+queryParam;
		System.out.println(D1_URL);
		response.sendRedirect(D1_URL);*/
		
		
		
		
		
		model.terminate();
		return "escalationDtl";
	}
	
	/**This is used when planned detail is added.
	 * @return : result
	 * @throws Exception
	 */
	public String generatePlannedListDtl() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
		bean.setViewPlannedDtlFlag(true);
		model.setCallTypeList(bean);
		model.setCallSelList(response, bean);
		model.setRepairTypeList(bean);
		model.setClosureTypeList(bean);
		if (bean.getParacode().equals("")) {	
		result = model.generatePlannedListDtl(bean, request, bean.getFromDate());
		if (result) {
			addActionMessage(getMessage("save"));
			bean.setMiscCode("");
			bean.setMiscName("");
			bean.setFromDate("");
			bean.setToDate("");
			bean.setMiscValue("");
			bean.setMiscWeekendValue("");
			String requestID = bean.getAccountCode();
			model.callForPlanned(bean,requestID,request);
		}else{
			addActionMessage(getMessage("duplicate"));
		}
		}// end of if
		else {
			result = model.modPlannedListDtl(bean,request);
			if (result) {
				addActionMessage(getMessage("update"));
				bean.setMiscCode("");
				bean.setMiscName("");
				bean.setFromDate("");
				bean.setToDate("");
				bean.setMiscValue("");
				bean.setMiscWeekendValue("");
				String requestID = bean.getAccountCode();
				model.callForPlanned(bean,requestID,request);
			}else{
				addActionMessage(getMessage("error"));
			}
		}// end of else
		model.terminate();
		return "plannedDtl";
	}
	/**
	 * To save the saveEscalationDtl
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String saveEscalationDtl() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
			result = model.saveEscalationDtl(bean,request);
			if (result) {
				addActionMessage(getMessage("save"));
			}//end of if
		bean.setEnableAll("Y");
		return "escalationDtl";
	}

	public String callForPlan() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String requestID = request.getParameter("accountId");
	//	model.callForEscalations(bean,requestID);
		
		
		request.setAttribute("requestID", requestID);
		
		String queryParam="?param=1";
		queryParam+="&requestID="+requestID;
		
		//queryParam+="&searchMessage="+searchMessage;
		//getNavigationPanel(1);
		String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/PerformanceMetrics_callForPlanned.action"+queryParam;
		System.out.println(D1_URL);
		response.sendRedirect(D1_URL);
		
		
		
		bean.setEnableAll("Y");
		model.terminate();
		return "escalationDtl";
	}
	
	
	
	/**
	 * callForPlanned () when page is load.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callForPlanned() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String requestID = request.getParameter("accountId");
		//model.callForPlanned(bean,requestID,request);
		
		request.setAttribute("requestID", requestID);
		
		String queryParam="?param=1";
		queryParam+="&requestID="+requestID;
		
		//queryParam+="&searchMessage="+searchMessage;
		//getNavigationPanel(1);
		String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/PerformanceMetrics_callForPlanned.action"+queryParam;
		System.out.println(D1_URL);
		response.sendRedirect(D1_URL);
		
		//bean.setEdit(true);
		model.setCallTypeList(bean);
		model.setRepairTypeList(bean); 
		model.setClosureTypeList(bean);
		model.setCallSelList(response, bean);
		bean.setEnableAll("Y");
		model.terminate();
		return "plannedDtl";
	}
	/**
	 * callForShowPlanned is used for paging.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callForShowPlanned() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String requestID = bean.getAccountCode();
		model.callForPlanned(bean,requestID,request);
		bean.setEnableAll("Y");
		model.terminate();
		return "plannedDtl";
	}
	/**
	 * To set value of the fields for edit.
	 * @return String
	 * @throws Exception
	 */
	public String editPlannedLstDtl() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String plannedID = bean.getParacode();
		///model.getRecord(bean);
		model.editPlannedLstDtl(bean,request,plannedID);
		String requestID = bean.getAccountCode();
		model.callForPlanned(bean,requestID,request);
		model.setCallTypeList(bean);
		model.setCallSelList(response, bean);
		model.setRepairTypeList(bean);
		model.setClosureTypeList(bean);
		model.terminate();
		bean.setEnableAll("Y");
		return "plannedDtl";
	}
	/**
	 * Method to delete the family details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callDelete() throws Exception {
		try {
			PerformanceMetricsModel model = new PerformanceMetricsModel();
			model.initiate(context, session);
			boolean result = model.delPlannedDtl(bean,request);
			String requestID = bean.getAccountCode();
			model.callForPlanned(bean,requestID,request);
			bean.setViewPlannedDtlFlag(true);
			model.setCallTypeList(bean);
			model.setRepairTypeList(bean);
			model.setClosureTypeList(bean);
			model.setCallSelList(response, bean);
			model.terminate();
			if (result) {
				addActionMessage(getMessage("delete"));
				bean.setParacode("");
				reset();
			} else {
				addActionMessage(getMessage("no result"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "plannedDtl";
	}
	public String f9ChildAccount() {
		try {
			String code = request.getParameter("accountParentCode");
			////String query = " SELECT ACCOUNT_CODE,ACCOUNT_NAME FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='Y' AND ACCOUNT_CODE NOT IN ("+code+") ORDER BY  ACCOUNT_CODE ASC ";
			String query = " SELECT ACCOUNT_CODE,ACCOUNT_NAME||' '||DECODE(PARENT_FLAG,'Y','(P)','N',' ') FROM CR_CLIENT_MASTER WHERE  1=1 "; 
			if(!(code.equals(""))){
				query += " AND (PARENT_CODE IN ("+code+"))";
			}
			query += " ORDER BY  ACCOUNT_CODE ASC";
			String header = getMessage("parent");
			String textAreaId = "paraFrm_selectChildName";
			String hiddenFieldId = "paraFrm_selectChildCode";
			String submitFlag = "";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in f9report method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**This function is used to save new add call type.
	 * @return result
	 * @throws Exception
	 */
	public String addNewCallType() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
		String requestID = bean.getAccountCode();
		model.callForPlanned(bean,requestID,request);
		 HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
			result = model.addNewCallType(bean,userEmpID);
			if (result) {
				this.addActionMessage("Call Type Created successfully.");
				bean.setAddNewCall("");
				bean.setAddNewCallDesc("");
				//getNavigationPanel(3);
			}else {
				this.addActionMessage("Call Type Already present.");
			}//end of else
			model.setCallTypeList(bean);
			model.setRepairTypeList(bean);
			model.setClosureTypeList(bean);
			bean.setEnableAll("Y");
			model.terminate();
			return "plannedDtl";
	}
	/**This Function is used to save selected client call type.
	 * @return result
	 * @throws Exception
	 */
	public String saveClientCallType() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
		String requestID = bean.getAccountCode();
		model.callForPlanned(bean,requestID,request);
		 HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
			result = model.saveClientCallType(bean,userEmpID);
			if (result) {
				this.addActionMessage("Record Saved successfully.");
				//getNavigationPanel(3);
			}else {
				this.addActionMessage("Error occured.");
			}//end of else
			model.setCallTypeList(bean);
			model.setRepairTypeList(bean);
			model.setClosureTypeList(bean);
			bean.setEnableAll("Y");
			model.terminate();
			return "plannedDtl";
	}
	
	/**This is used when search is clicled.
	 * @return string
	 */
	public String applyFilter() {
		try {
			PerformanceMetricsModel model = new PerformanceMetricsModel();
			model.initiate(context, session);
			
			String searchMessage = request.getParameter("searchMessage");
			String dashBoardID = request.getParameter("dashBoardID");
			 HttpSession session = request.getSession();
			    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
			model.applyFilters(bean, request,searchMessage,dashBoardID,userEmpID);
			bean.setHiddenSearchMessage(searchMessage);
			
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	/**This function is used to save new add repair code.
	 * @return result
	 * @throws Exception
	 */
	public String addNewRepairCode() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
		String requestID = bean.getAccountCode();
		model.callForPlanned(bean,requestID,request);
		 HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
			result = model.addNewRepairCode(bean,userEmpID);
			if (result) {
				this.addActionMessage("Repair Code Added successfully.");
				bean.setAddRepairCode("");
				bean.setAddRepairCodeDesc("");
				//getNavigationPanel(3);
			}else {
				this.addActionMessage("Repair Code Already present.");
			}//end of else
			model.setRepairTypeList(bean);
			model.setClosureTypeList(bean);
			model.setCallTypeList(bean);
			bean.setEnableAll("Y");
			model.terminate();
			return "plannedDtl";
	}
	
	
	/**
	 * callForDataReconcilation () when page is load.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callForDataReconcilation() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String requestID = request.getParameter("accountId");
		///model.callAccountName(bean,requestID,request);
		
		request.setAttribute("requestID", requestID);
		
		String queryParam="?param=1";
		queryParam+="&requestID="+requestID;
		
		//queryParam+="&searchMessage="+searchMessage;
		//getNavigationPanel(1);
		String D1_URL=getText("D1_PeoplepowerCRM_URL")+"cr/PerformanceMetrics_callForDataReconcilation.action"+queryParam;
		System.out.println(D1_URL);
		response.sendRedirect(D1_URL);
		
		
		///model.callForDataReconcilation(bean,requestID,request);
		//bean.setEdit(true);
		//model.setCallTypeList(bean);
		//model.setRepairTypeList(bean);
		//model.setCallSelList(response, bean);
		bean.setEnableAll("Y");
		model.terminate();
		return "dataReconcilation";
	}
	/**
	 * Method to get Data reconciliation details list. 
	 * @return String
	 * @throws Exception
	 */
	public String generateDataReconListDtl() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		bean.setViewDataReconListDtlFlag(true);
		String requestID = bean.getAccountCode();
		System.out.println("requestID----------------::"+requestID);
		
		String callType = bean.getSlaTypeHiddenRadio();
		System.out.println("callType----------------::"+callType);
		
		model.callAccountName(bean,requestID,request);
		model.callForDataReconcilation(bean, requestID,request,callType);					
		model.terminate();
		return "dataReconcilation";
	}
	
	/**
	 * Method to get show call details. 
	 * @return String
	 * @throws Exception
	 */
	public String getCallDetails() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String callId = request.getParameter("callId");
		
		Object[][] callData = model.getCallDetail(callId);
		
		request.setAttribute("callData", callData);
		model.terminate();
		return "callViewPage";
	}
	
	/**
	 * To save the saveDataReconciliation
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String saveDataReconciliation() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
			result = model.saveDataReconciliation(bean,request);
			if (result) {
				
				addActionMessage(getMessage("save"));
				bean.setViewDataReconListDtlFlag(true);
				String requestID = bean.getAccountCode();
				String callType = bean.getSlaTypeHiddenRadio();
				System.out.println("callType----------------::"+callType);
				model.callForDataReconcilation(bean,requestID,request,callType);
			}//end of if
			model.terminate();
		bean.setEnableAll("Y");
		
		return "dataReconcilation";
	}
	
	
	/**This function is used to save new add closure code.
	 * @return result
	 * @throws Exception
	 */
	public String addNewClosureCode() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
		String requestID = bean.getAccountCode();
		model.callForPlanned(bean,requestID,request);
		 HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
			result = model.addNewClosureCode(bean,userEmpID);
			if (result) {
				this.addActionMessage("Cause Code Added successfully.");
				bean.setAddClosureCode("");
				bean.setAddClosureCodeDesc("");
				//getNavigationPanel(3);
			}else {
				this.addActionMessage("Cause Code Already present.");
			}//end of else
			model.setClosureTypeList(bean);
			model.setRepairTypeList(bean);
			model.setCallTypeList(bean);
			bean.setEnableAll("Y");
			model.terminate();
			return "plannedDtl";
	}
	
	
	
	/**
	 * Method to publish data. 
	 * @return String
	 * @throws Exception
	 */
	public String publishData() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		bean.setViewDataReconListDtlFlag(true);
		String requestID = bean.getAccountCode();
		System.out.println("requestID----------------::"+requestID);
		model.callAccountName(bean,requestID,request);
		String callType = bean.getSlaTypeHiddenRadio();
		System.out.println("callType----------------::"+callType);
		model.callForDataReconcilation(bean, requestID,request,callType);
		boolean result;
		
		result = model.publishReconData(bean);
		if (result) {
			this.addActionMessage("Data Publish successfully.");
			
			//getNavigationPanel(3);
		}else {
			///this.addActionMessage("Cause Code Already present.");
		}//end of else
		model.terminate();
		return "dataReconcilation";
	}
	
	/** method name : report.
	 * purpose     : to generate the report
	 * return type : String
	 * parameter   : none
	 */
	public String getReport() {
		final PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		String reportPath = "";
		 HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
		model.getReport(bean, response,  request, reportPath,userEmpID);
		model.terminate();
		return null;
	}
	
	/**
	 * Method to get show call details. 
	 * @return String
	 * @throws Exception
	 */
	public String defineSLAFun() throws Exception {
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		/*String callId = request.getParameter("callId");
		
		Object[][] callData = model.getCallDetail(callId);
		
		request.setAttribute("callData", callData);*/
		model.terminate();
		return "defineSLA";
	}
	
	public String saveSLA() throws Exception{
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result;
		HttpSession session = request.getSession();
		String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
		String accountCode=(String)request.getParameter("accountCode");
		result =model.saveSLA(bean,request,userEmpID,accountCode);
		if(result)
		{
			addActionMessage(getMessage("save"));
			resetSLA();
			model.callForSla(bean,request,accountCode);
			reset();
			String requestID = bean.getAccountCode();
			//model.setperformanceMetricsDtl(bean,requestID);
			model.callForPerformanceMetrics(bean,requestID);
			
		}
		else{
			addActionMessage("Duplicate SLA type & Hrs can't be added");
			resetSLA();
			model.callForSla(bean,request,accountCode);
			reset();
			String requestID = bean.getAccountCode();
			//model.setperformanceMetricsDtl(bean,requestID);
			model.callForPerformanceMetrics(bean,requestID);
			
		}
		model.terminate();
		
		return SUCCESS;
	}
	
	public String editSla() throws Exception{
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result=false;
		String accountCode=(String)request.getParameter("accountCode");
		String autoId=(String)request.getParameter("autoId");
		result= model.editSla(bean,request,autoId,accountCode);
		if(result){
			model.callForSla(bean,request,accountCode);
			reset();
			String requestID = bean.getAccountCode();
			//model.setperformanceMetricsDtl(bean,requestID);
			model.callForPerformanceMetrics(bean,requestID);
			model.terminate();
			
		}else{
			
			model.callForSla(bean,request,accountCode);
			reset();
			String requestID = bean.getAccountCode();
			//model.setperformanceMetricsDtl(bean,requestID);
			model.callForPerformanceMetrics(bean,requestID);
			model.terminate();
		}
		
		return SUCCESS;
	}
	
	public String deleteSla() throws Exception{
		PerformanceMetricsModel model = new PerformanceMetricsModel();
		model.initiate(context, session);
		boolean result=false;
		String accountCode=(String)request.getParameter("accountCode");
		String autoId=(String)request.getParameter("autoId");
		result= model.deleteSla(bean,request,autoId);
		if(result){
			addActionMessage(getMessage("delete"));
			
		}else{
			
			addActionMessage(getMessage("del.error"));
		}
		
		
		resetSLA();
		model.callForSla(bean,request,accountCode);
		reset();
		String requestID = bean.getAccountCode();
		//model.setperformanceMetricsDtl(bean,requestID);
		model.callForPerformanceMetrics(bean,requestID);
		model.terminate();
		return SUCCESS;
	}
	
	
	public String resetSLA()  {
		try {
			bean.setCaptionName("");
			bean.setSlaType("");
			bean.setHrs("");
			bean.setSlaGreenValStart("0.0");
			bean.setSlaGreenValEnd("0.0");
			bean.setSlaRedValStart("0.0");
			bean.setSlaRedValEnd("0.0");
			bean.setSlaYellowValStart("0.0");
			bean.setSlaYellowValEnd("0.0");
			
		} catch (Exception e) {
			logger.error("Error in reset" + e);
		}
		return SUCCESS;
	}
	
}