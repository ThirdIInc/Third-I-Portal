package org.struts.action.payroll;

import org.paradyne.bean.payroll.PFReport;
import org.paradyne.model.payroll.PFReportModel;
import org.struts.lib.ParaActionSupport;

public class PFReportAction extends ParaActionSupport {

	PFReport pf;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void prepare_local() throws Exception {
		pf = new PFReport();	 
		pf.setMenuCode(1090);	
	}
	
	public Object getModel() {
		return this.pf;
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
		pf.setBrnCode("");
		pf.setTypeCode("");
		pf.setTypeName("");
		pf.setMonth("");
		pf.setYear("");
		pf.setPayBillNo("");
		pf.setPayBillName("");
		pf.setDivisionAbbrevation("");
		pf.setDivCode("");
		pf.setDivName("");
		pf.setBrnCode("");
		pf.setBrnName("");
		pf.setDeptCode("");
		pf.setDeptName("");
		pf.setOnHold("");
		pf.setReportType("");
		return SUCCESS;

	}


	/*
	 * Following function is called in the jsp page to show the employee type and id
	 */
	
	/*public String f9type() throws Exception {
		String query = " SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE";
		String[] headers = { getMessage("type.name")  };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "typeName",	"typeCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}*/
	public final String f9type() {
		try {
			String query = "SELECT HRMS_EMP_TYPE.TYPE_ID, HRMS_EMP_TYPE.TYPE_NAME FROM HRMS_EMP_TYPE " 
				+ " WHERE HRMS_EMP_TYPE.IS_ACTIVE='Y'"
				+ " ORDER BY HRMS_EMP_TYPE.TYPE_ID";
			
			String header = getMessage("employee.type");
			String textAreaId ="paraFrm_typeName";
			String hiddenFieldId ="paraFrm_typeCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public final String f9grade() {
		try {
			String query = "SELECT HRMS_CADRE.CADRE_ID, HRMS_CADRE.CADRE_NAME FROM HRMS_CADRE " 
				+ " WHERE HRMS_CADRE.CADRE_IS_ACTIVE='Y'"
				+ " ORDER BY HRMS_CADRE.CADRE_ID";
			
			String header = getMessage("grade");
			String textAreaId ="paraFrm_gradeName";
			String hiddenFieldId ="paraFrm_gradeCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	/*
	 * Following function is called to show the pay bill id and name in the jsp
	 */
	
	/*public String f9payBill() throws Exception {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "; 
			query +=" ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String[] headers = { "Paybill Code", getMessage("pay.bill")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "payBillNo", "payBillName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}*/
	
	public final String f9payBill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "; 
			query += " ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
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
	/*public String f9dept() throws Exception {
		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY DEPT_ID";
		String[] headers = {getMessage("department.code"),getMessage("department") };
		String[] headerWidth = { "20","80" };
		String[] fieldNames = { "deptCode","deptName"};
		int[] columnIndex = { 0 ,1};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		logger.info("4");
		return "f9page";
	}*/
	
	public final String f9dept() {
		try {
			String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY HRMS_DEPT.DEPT_ID";
			
			String header = getMessage("department");
			String textAreaId ="paraFrm_deptName";
			String hiddenFieldId ="paraFrm_deptCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/*
	 * Following function is called to generate the branch id and branch name in the jsp page
	 */
	/*public String f9brn() throws Exception {
		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";
		String[] headers = {getMessage("branch.code"),getMessage("branch") };
		String[] headerWidth = { "20","80" };
		String[] fieldNames = { "brnCode","brnName"};
		int[] columnIndex = { 0 ,1};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}*/
	
	public final String f9brn() {
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER ";
			query += " ORDER BY HRMS_CENTER.CENTER_ID";
			String header = getMessage("branch");
			String textAreaId ="paraFrm_brnName";
			String hiddenFieldId ="paraFrm_brnCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/*
	 * Following function is called to show the division name and division id in the jsp 
	 */
	/*public String f9div() throws Exception {
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' '),DIV_ADDRESS1 ||' '||DIV_ADDRESS2 ||' '||DIV_ADDRESS3 AS Address,DIV_ESI_CODE, NVL(DIV_ABBR,' ') FROM HRMS_DIVISION";
		if (pf.getUserProfileDivision() != null && pf.getUserProfileDivision().length() > 0) {
			query+= " WHERE DIV_ID IN ("+pf.getUserProfileDivision() +")";
		}
			query+= " ORDER BY  DIV_ID ";
		
		String[] headers = { getMessage("division.code"),getMessage("division") };
		String[] headerWidth = { "20","80" };

		String[] fieldNames = { "divCode","divName","divAdd","divEsiCode","divisionAbbrevation"};
		int[] columnIndex = { 0 ,1,2,3,4};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}*/
	
	public String f9div() {
		try {
			String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID, NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_DIVISION ";
			if(!(pf.getUserProfileDivision().equals(""))){
				query+= " WHERE HRMS_DIVISION.DIV_ID IN ("+pf.getUserProfileDivision() +")"; 
			}
			query+= " ORDER BY  HRMS_DIVISION.DIV_ID ";
			String header = getMessage("division");
			String textAreaId ="paraFrm_divName";
			String hiddenFieldId ="paraFrm_divCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/**
	 * Following function is called to generate the pf report
	 * @return
	 */
	public String report() {
		PFReportModel model = new PFReportModel();
		model.initiate(context, session);
		try{
			String reportPath = "";	
			model.generateReport(pf, request, response, reportPath);
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Following function is called to mail the pf report
	 * @return
	 */
	public final String mailReport(){
		try {
			PFReportModel model = new PFReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.generateReport(pf, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
}
