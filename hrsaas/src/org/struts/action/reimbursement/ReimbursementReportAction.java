package org.struts.action.reimbursement;

import org.paradyne.bean.reimbursement.ReimbursementReport;
import org.paradyne.model.reimbursement.ReimbursementReportModel;
import org.struts.lib.ParaActionSupport;

public class ReimbursementReportAction extends ParaActionSupport
{
	ReimbursementReport reimbursebean;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		reimbursebean =new ReimbursementReport();
		reimbursebean.setMenuCode(1129);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return reimbursebean;
	}

	public ReimbursementReport getReimbursebean() {
		return reimbursebean;
	}

	public void setReimbursebean(ReimbursementReport reimbursebean) {
		this.reimbursebean = reimbursebean;
	}
	
	public String input()
	{
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String reset()
	{
		reimbursebean.setDivId("");
		reimbursebean.setDivision("");
		reimbursebean.setBranch("");
		reimbursebean.setBranchId("");
		reimbursebean.setDept("");
		reimbursebean.setDeptId("");
		reimbursebean.setDesg("");
		reimbursebean.setDesgId("");
		reimbursebean.setFromDate("");
		reimbursebean.setToDate("");
		reimbursebean.setCreditCode("");
		reimbursebean.setCreditName("");
		reimbursebean.setCreditCode("");
		reimbursebean.setCreditType("");
		reimbursebean.setReportType("");
		reimbursebean.setGradeName("");
		reimbursebean.setGradeId("");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	
public String f9Division()throws Exception{
		
		try {
			String query = " SELECT HRMS_DIVISION.DIV_ID,nvl(HRMS_DIVISION.DIV_NAME,' ')	 FROM HRMS_DIVISION";
			String header = getMessage("reimbursement.div");
			String textAreaId = "paraFrm_division";
			String hiddenFieldId = "paraFrm_divId";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}


public String f9Branch()throws Exception{
	
	try {
		String query = " SELECT HRMS_CENTER.CENTER_ID,nvl(HRMS_CENTER.CENTER_NAME,' ')	FROM HRMS_CENTER";		
		String header = getMessage("reimbursement.branch");
		String textAreaId = "paraFrm_branch";
		String hiddenFieldId = "paraFrm_branchId";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9multiSelect";
}

public String f9Paybill()throws Exception{
	
	try {
		String query = " SELECT HRMS_PAYBILL.PAYBILL_ID,nvl(HRMS_PAYBILL.PAYBILL_GROUP,'') FROM HRMS_PAYBILL "
			+ " ORDER BY  HRMS_PAYBILL.PAYBILL_ID ";		
		String header = "Pay Bill Name";
		String textAreaId = "paraFrm_paybillname";
		String hiddenFieldId = "paraFrm_paybillid";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9multiSelect";
}


public String f9Dept()throws Exception{
	
	try {
		String query = " SELECT HRMS_DEPT.DEPT_ID,nvl(HRMS_DEPT.DEPT_NAME,'')	FROM HRMS_DEPT";
		
		String header = getMessage("reimbursement.dept");
		String textAreaId = "paraFrm_dept";
		String hiddenFieldId = "paraFrm_deptId";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9multiSelect";
}

public String f9Grade()throws Exception{
	
	try {
		String query = " SELECT DISTINCT HRMS_CADRE.CADRE_ID,TO_CHAR(HRMS_CADRE.CADRE_NAME) FROM  HRMS_CADRE   ORDER BY HRMS_CADRE.CADRE_ID";
		
		String header = getMessage("reimbursement.grade");
		String textAreaId = "paraFrm_gradeName";
		String hiddenFieldId = "paraFrm_gradeId";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
				submitFlag, submitToMethod);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9multiSelect";
}	


public String f9Desg()throws Exception{
	
	String query = " SELECT HRMS_RANK.RANK_NAME,HRMS_RANK.RANK_ID	FROM HRMS_RANK";	
	String[] headers = {getMessage("reimbursement.desg")};	
	String[] headerWidth = { "50" };	
	String[] fieldNames = { "desg","desgId"};	
	int[] columnIndex = { 0,1};	
	String submitFlag = "false";	
	String submitToMethod = "";	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);	
	return "f9page";
}	

public String f9CreditHead() throws Exception
{
try {
	String query = " SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_HEAD_TYPE IN ('R','V') ";

	String header = "Credit Name";
	String textAreaId = "paraFrm_creditName";
	String hiddenFieldId = "paraFrm_creditCode";
	String submitFlag = "false";
	String submitToMethod = "";
	setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
			submitFlag, submitToMethod);
} catch (Exception e) {
	e.printStackTrace();
}
	return "f9multiSelect";
}


public String getReport()throws Exception{
	
	try {
		ReimbursementReportModel model = new ReimbursementReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getReport(reimbursebean,request, response, reportPath);
		model.terminate();		
		} 
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	getNavigationPanel(1);
	return null;
}
public String getDisbReport()throws Exception{
	
	try {
		ReimbursementReportModel model = new ReimbursementReportModel();
		model.initiate(context, session);
		reimbursebean.setAccountantID(reimbursebean.getUserEmpId());
		String reportPath = "";
		model.getReport(reimbursebean,request, response, reportPath);
		model.terminate();		
		} 
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	return null;
}

public final String f9reportType() {
	try {
		String[][] type = new String[][]{{"PDF"},{"xls"},{"Doc"},{"Html"}};
		String[] headers = {getMessage("report.type")};
		String[] headerWidth = {"100"};
		String[] fieldNames = {"reportType"};
		int[] columnIndex = {0};
		String submitFlag = "true";
		String submitToMethod = "ReimbursementReport_mailReport.action";
		setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "f9page";
}

public final String mailReport(){
	try {
		ReimbursementReportModel model = new ReimbursementReportModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
		model.getReport(reimbursebean, request, response, reportPath);
		
		model.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "mailReport";
}


}