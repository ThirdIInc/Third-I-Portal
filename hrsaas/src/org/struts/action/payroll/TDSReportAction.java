package org.struts.action.payroll;

import org.paradyne.bean.payroll.TDSReport;
import org.paradyne.model.payroll.TdsReportModel;
import org.struts.lib.ParaActionSupport;

public class TDSReportAction extends ParaActionSupport {

	TDSReport tds;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void prepare_local() throws Exception {
		tds = new TDSReport();	 
		tds.setMenuCode(1094);	
	}
	
	public Object getModel() {
		return this.tds;
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
		tds.setBrnCode("");
		tds.setTypeCode("");
		tds.setTypeName("");
		tds.setMonth("");
		tds.setYear("");
		tds.setPayBillNo("");
		tds.setPayBillName("");
		tds.setDivisionAbbrevation("");
		tds.setDivCode("");
		tds.setDivName("");
		tds.setBrnCode("");
		tds.setBrnName("");
		tds.setDeptCode("");
		tds.setDeptName("");
		tds.setOnHold("");
		tds.setReportType("");
		tds.setReportOption("");
		tds.setCadreCode("");
		tds.setCadreName("");
		return SUCCESS;

	}
	
	public String report() {
		TdsReportModel model = new TdsReportModel();
		model.initiate(context, session);
		String result = "";

		try {
			model.getTdsReport(tds, request, response, "");
		} catch (Exception e) {
			return "tdsReport";
		}
		if (result == "F") {
			addActionMessage("Please Process Salary First");
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
	
	
	public String f9div() {
		try {
			String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION";

			if (tds.getUserProfileDivision() != null && tds.getUserProfileDivision().length() > 0) {
				query+= " WHERE DIV_ID IN ("+tds.getUserProfileDivision() +")";
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

	
	
	public final String f9reportType() {
		try {
			//String query = " SELECT 'Pdf', 'Excel', 'Html', 'Word' FROM DUAL";
			String query = " SELECT 'Pdf' FROM DUAL";
			String[][] type = new String[][]{{"PDF"},{"Xls"},{"Html"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"reportType"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "TDSReport_tdsMailReport.action";
			//setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	
	public final String tdsMailReport(){
		try {
			TdsReportModel model = new TdsReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			model.getTdsReport(tds, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}


}
