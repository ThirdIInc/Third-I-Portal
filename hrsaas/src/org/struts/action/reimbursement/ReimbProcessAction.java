/**
 * 
 */
package org.struts.action.reimbursement;

import org.paradyne.bean.reimbursement.ReimbProcess;
import org.paradyne.model.reimbursement.ReimbProcessModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class ReimbProcessAction extends ParaActionSupport {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReimbProcessAction.class);
	ReimbProcess reimbProcess;
	@Override
	public void prepare_local() throws Exception {
		reimbProcess = new ReimbProcess();
		reimbProcess.setMenuCode(1148);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return reimbProcess;
	}
	
	public String input(){
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		model.getProcessList(reimbProcess, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String getLockedList(){
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		model.getLockedList(reimbProcess, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String f9division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(reimbProcess.getUserProfileDivision() != null && reimbProcess.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + reimbProcess.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divisionName", "divisionCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			return "";
		}
	} //end of f9Div
	
	public String f9Search() {
		try {
			String query = " SELECT DIV_NAME,CREDIT_NAME, " 
						+" TO_CHAR(REIMB_PROCESS_DATE,'DD-MM-YYYY'),  "
						+" TO_CHAR(TO_DATE(REIMB_FROM_MONTH,'mm'),'Month') FROMMO, TO_CHAR(TO_DATE(REIMB_TO_MONTH,'mm') ,'Month'),"
						+" REIMB_YEAR, REIMB_STATUS,REIMB_CODE FROM HRMS_REIMB_PROCESS_HDR"
						+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=REIMB_CREDIT_CODE)"
						+" INNER JOIN HRMS_DIVISION ON(DIV_ID=REIMB_DIVISION) WHERE REIMB_STATUS='"+reimbProcess.getStatus()+"'"
						+" ORDER BY REIMB_PROCESS_DATE DESC";

			String[] headers = {getMessage("division"),getMessage("reimbHead"),getMessage("processDate"),getMessage("processFrom"),getMessage("processTo")};

			String[] headerWidth = {"25","25","20","15","15"};

			String[] fieldNames = {"divisionName", "reimbHeadName","processDate","fromMonthView","toMonthView","reimbYear","status","processCode"};

			int[] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7};

			String submitFlag = "true";

			String submitToMethod = "ReimbProcess_getProcessDetails.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			return "";
		}
	} //end of f9Div
	public String addNew(){
		getNavigationPanel(2);
		reimbProcess.setStatus("UNLOCK");
		logger.info("addNew");
		return "ProcData";
	}
	
	public String f9reimbHead() throws Exception {

		String query = " SELECT CREDIT_NAME,DECODE(REIMB_PERIOD,'A','ANNUAL','H','HALF YEAR','Q','QUARTER','M','MONTH','F','FREQUENT'),REIMB_PERIOD,CREDIT_CODE FROM HRMS_REIMB_CONFIG "
						+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=REIMB_CREDIT_HEAD)"
						+" WHERE  CREDIT_HEAD_TYPE IN('R') ORDER BY CREDIT_NAME ";
		String[] headers = { getMessage("reimbHead"),"Reimbursement Period" };

		String[] headerWidth = { "80","20" };

		String[] fieldNames = { "reimbHeadName","reimbPeriod","reimbPeriod",
				"reimbHeadCode" };
		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "true";

		String submitToMethod = "ReimbProcess_addNew.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String reset(){
		getNavigationPanel(2);
		reimbProcess.setAnnYear("");
		reimbProcess.setDivisionCode("");
		reimbProcess.setDivisionName("");
		reimbProcess.setDivisionNameList("");
		reimbProcess.setEmpProcessLength("false");
		reimbProcess.setFromDate("");
		reimbProcess.setHalfMonth("");
		reimbProcess.setHalfYear("");
		reimbProcess.setMonth("");
		reimbProcess.setProcessCode("");
		reimbProcess.setReimbHeadCode("");
		reimbProcess.setReimbHeadName("");
		reimbProcess.setReimbPeriod("");
		reimbProcess.setStatus("");
		reimbProcess.setToDate("");
		reimbProcess.setYear("");
		return "ProcData";
	}
	
	public String processReimb(){
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		String resultMsg=model.processReimb(reimbProcess);
		addActionMessage(resultMsg);
		if(resultMsg.contains("success")){
			getNavigationPanel(3);
			showProcessEmployee();
			reimbProcess.setEnableAll("N");
		}else{
			getNavigationPanel(2);
		}
		model.terminate();
		
		return "ProcData";
	}
	public String lockProcess(){
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		boolean  result=model.lockProcess(reimbProcess);
		if(result){
			addActionMessage("Process locked successfully");
			reimbProcess.setStatus("LOCK");
		}else{
		}
		getProcessDetails();
		model.terminate();
		
		return "ProcData";
	}
	public String showProcessEmployee() {
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		
		reimbProcess.setEnableAll("N");
		model.showProcessEmployee(reimbProcess,request);
		if(reimbProcess.getStatus().equals("LOCK")){
			getNavigationPanel(4);
		}else {
			getNavigationPanel(3);
		}
		model.terminate();
		return "ProcData";
	}
	public String viewDetails(){
		String status=request.getParameter("status");
		String processCode=request.getParameter("processCode");
		reimbProcess.setStatus(status);
		reimbProcess.setProcessCode(processCode);
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		model.getProcessDetails(reimbProcess);
		showProcessEmployee();
		reimbProcess.setEnableAll("N");
		return "ProcData";
	}
	public String getProcessDetails(){
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		model.getProcessDetails(reimbProcess);
		showProcessEmployee();
		reimbProcess.setEnableAll("N");
		return "ProcData";
	}
	public String save(){
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		model.saveReimbAmount(reimbProcess,request);
		logger.info("###### save function###########");
		showProcessEmployee();
		reimbProcess.setEnableAll("N");
		return "ProcData";
	}
	public String delete(){
		ReimbProcessModel model= new ReimbProcessModel();
		model.initiate(context, session);
		boolean result=model.deleteReimbProcess(reimbProcess,request);
		if(result){
			addActionMessage("Record deleted successfully");
		}
		logger.info("###### delete function###########");
		getNavigationPanel(1);
		return input();
	}

}
