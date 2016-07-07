package org.struts.action.payroll;

import org.paradyne.bean.payroll.ESICReport;
import org.paradyne.model.payroll.EsiReportModel;
import org.struts.lib.ParaActionSupport;

public class ESICReportAction extends ParaActionSupport {

	ESICReport esic;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void prepare_local() throws Exception {
		esic = new ESICReport();	 
		esic.setMenuCode(1093);	
	}
	
	public Object getModel() {
		return this.esic;
	}
	
	public String input() {
		return INPUT;
	}
	
	/**
	 * Reset the fields of the form
	 * 
	 * @return String
	 */
	public String reset() {
		esic.setBrnCode("");
		esic.setTypeCode("");
		esic.setTypeName("");
		esic.setMonth("");
		esic.setYear("");
		esic.setPayBillNo("");
		esic.setPayBillName("");
		esic.setDivisionAbbrevation("");
		esic.setDivCode("");
		esic.setDivName("");
		esic.setBrnCode("");
		esic.setBrnName("");
		esic.setDeptCode("");
		esic.setDeptName("");
		esic.setOnHold("");
		esic.setReportType("");
		esic.setBranchFlag("");
		esic.setCadreCode("");
		esic.setCadreName("");
		
		return SUCCESS;

	}
	/**
	 * This method is called when clicked on anyone report icon (i.e. Pdf or Xls)
	 * @return
	 */
	public String report() {
		EsiReportModel model = new EsiReportModel();
		model.initiate(context, session);

		try {
			model.getEsiReport(esic, request, response, "");
		} catch (Exception e) {
			return "esicReport";
		}
		model.terminate();
		return null;
	}
	
	
	
	/*
	 * Following function is called in the jsp page to show the employee type and id
	 */
	
	public String f9type() throws Exception {
		String query = " SELECT   TYPE_ID, TYPE_NAME  FROM HRMS_EMP_TYPE";
		String header = getMessage("type.name");
		String textAreaId ="paraFrm_typeName";
		String hiddenFieldId ="paraFrm_typeCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		
		logger.info("4");
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
	/*
	 * Following function is called in the jsp page to show the department id and name
	 */
	public String f9dept() throws Exception {
		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY DEPT_ID";
		String header = getMessage("department");
		String textAreaId ="paraFrm_deptName";
		String hiddenFieldId ="paraFrm_deptCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		
		logger.info("4");
		return "f9multiSelect";
	}
	/*
	 * Following function is called to generate the branch id and branch name in the jsp page
	 */
	public String f9brn() throws Exception {
		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";
		String header = getMessage("branch");
		String textAreaId ="paraFrm_brnName";
		String hiddenFieldId ="paraFrm_brnCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		
		logger.info("4");
		return "f9multiSelect";
	}
	
	/*
	 * Following function is called to generate the division id and name in the jsp page
	 */
	public String f9div() {
		try {
			String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION";

			if (esic.getUserProfileDivision() != null && esic.getUserProfileDivision().length() > 0) {
				query+= " WHERE DIV_ID IN ("+esic.getUserProfileDivision() +")";
			}
				query+= " ORDER BY  DIV_ID ";
			
			String header = getMessage("division");
			
			String textAreaId ="paraFrm_divName";
			String hiddenFieldId ="paraFrm_divCode";
			
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
	
	/**
	 * This method is called when clicked on mail report icon
	 * @return
	 */
	public final String esicMailReport(){
		try {
			EsiReportModel model = new EsiReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.getEsiReport(esic, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}


}
