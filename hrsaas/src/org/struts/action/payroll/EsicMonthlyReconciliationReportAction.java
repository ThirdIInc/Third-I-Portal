package org.struts.action.payroll;

import org.paradyne.bean.payroll.EsicMonthlyReconciliationReport;
import org.paradyne.model.payroll.EsicMonthlyReconciliationReportModel;
import org.struts.lib.ParaActionSupport;

public class EsicMonthlyReconciliationReportAction extends ParaActionSupport {
	EsicMonthlyReconciliationReport esicBean;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		esicBean = new EsicMonthlyReconciliationReport();
		esicBean.setMenuCode(2109);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return esicBean;
	}
	
	public String input(){
		return INPUT;
	}
	
	public String f9division() throws Exception {
		try {
			String query = " SELECT DISTINCT  DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION  ";
			if (esicBean.getUserProfileDivision() != null
					&& esicBean.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN ("
						+ esicBean.getUserProfileDivision() + ")";
			query += " ORDER BY  DIV_ID ";

			String header = getMessage("division");

			String textAreaId = "paraFrm_divName";
			String hiddenFieldId = "paraFrm_divId";
			String submitFlag = "false";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return "f9multiSelect";
	}// end of f9div
	
	
	/*
	 * Following function is called to generate the branch id and branch name in the jsp page
	 */
	public String f9brn() throws Exception {
		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";
		String header = getMessage("branch");
		String textAreaId ="paraFrm_brnName";
		String hiddenFieldId ="paraFrm_brnId";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		
		return "f9multiSelect";
	}
	
	/*
	 * Following function is called in the jsp page to show the department id and name
	 */
	public String f9dept() throws Exception {
		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY DEPT_ID";
		String header = getMessage("department");
		String textAreaId ="paraFrm_deptName";
		String hiddenFieldId ="paraFrm_deptId";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		
		return "f9multiSelect";
	}
	
	/*
	 * Following function is called to show the pay bill id and name in the jsp
	 */	
	public String f9payBill() throws Exception {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "; 
			query +=" ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String header = getMessage("pay.bill");
			String textAreaId ="paraFrm_payBillName";
			String hiddenFieldId ="paraFrm_payBillNo";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/**
	 * Method to select the Grade of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9grade() throws Exception {

		String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE  "
				+ " ORDER BY CADRE_ID ";

		String header = getMessage("grade");
		String textAreaId ="paraFrm_cadreName";
		String hiddenFieldId ="paraFrm_cadreCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	
	public void report(){
		try {
			EsicMonthlyReconciliationReportModel model = new EsicMonthlyReconciliationReportModel();
			model.initiate(context, session);
			model.getReport(esicBean, request, response, "");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String reset(){
		esicBean.setDivId("");
		esicBean.setDivName("");
		esicBean.setYear("");
		esicBean.setMonth("");
		esicBean.setReportType("");
		esicBean.setBrnId("");
		esicBean.setBrnName("");
		esicBean.setDeptId("");
		esicBean.setDeptName("");
		esicBean.setPayBillNo("");
		esicBean.setPayBillName("");
		esicBean.setCadreCode("");
		esicBean.setCadreName("");
		return INPUT;
	}
	
	public final String reportMail(){
		try {
			EsicMonthlyReconciliationReportModel model = new EsicMonthlyReconciliationReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.getReport(esicBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}
