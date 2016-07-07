package org.struts.action.payroll;

import java.io.File;

import org.paradyne.bean.payroll.EmployeeMonthlyEarningReport;
import org.paradyne.model.payroll.EmployeeMonthlyEarningReportModel;
import org.struts.lib.ParaActionSupport;

public class EmployeeMonthlyEarningReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmployeeMonthlyEarningReportAction.class);
	
	EmployeeMonthlyEarningReport earningBean;
	
	public void prepare_local() throws Exception {
		earningBean = new EmployeeMonthlyEarningReport();
		earningBean.setMenuCode(2256);
	}

	public Object getModel() {
		return earningBean;
	}
	
	public String input(){
		try {
			//logger.info("message"+earningBean.getActionMessage());
			//request.setAttribute("buttonList1", "Report#reportCall()@Test#testCall()");
			/*String returnMessage = String.valueOf(request
					.getParameter("returnMessage"));
			if (returnMessage!= null && !returnMessage.equals("")&& !returnMessage.equals("null")) {
				addActionMessage(returnMessage);
			}*/
		} catch (Exception e) {
			// TODO: handle exception
		}
		//deleteFile();
		return INPUT;
	}
	public void deleteFile(){
		String path=String.valueOf(request.getParameter("reportPath"));
		logger.info("path in delete=="+path);
			File file = new File(path);
		if(file.exists()){
			new File(path).delete();
		}
	}
	
	public String f9paybill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "; 
			query +=" ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String textAreaId ="paraFrm_paybillName";
			String hiddenFieldId ="paraFrm_paybillId";
			//String[] headers = { getMessage("division")};
			//String[] headerWidth = { "20", "80" };
			//String[] fieldNames = { "divisionId", "divisionName", "divisionAbbrevation" };
			//int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, getMessage("paybill"), textAreaId,hiddenFieldId,submitFlag,submitToMethod);
			//setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String f9division() {
		 try {
			 String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_DIVISION ";
				if(!(earningBean.getUserProfileDivision().equals(""))){
					query+= " WHERE HRMS_DIVISION.DIV_ID IN ("+earningBean.getUserProfileDivision() +")"; 
				}
				query+= " ORDER BY  HRMS_DIVISION.DIV_ID ";
					
			String textAreaId ="paraFrm_divisionName";
			String hiddenFieldId ="paraFrm_divisionId";
			//String[] headers = { getMessage("division")};
			//String[] headerWidth = { "20", "80" };
			//String[] fieldNames = { "divisionId", "divisionName", "divisionAbbrevation" };
			//int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, getMessage("division"), textAreaId,hiddenFieldId,submitFlag,submitToMethod);
			//setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	public String f9earnings() {
		try {
			String query = " SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ') FROM HRMS_CREDIT_HEAD ";
			query += " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			
			String header = getMessage("credit.name");
			
			String textAreaId ="paraFrm_earningCompName";
			String hiddenFieldId ="paraFrm_earningCompId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public final String f9reportType() {
		try {
			String[][] type = new String[][]{{"PDF"},{"Xls"},{"Doc"},{"Html"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"reportType"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "EmployeeMonthlyEarningReport_mailReport.action";
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String f9branch() {
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER ";
			query += " ORDER BY HRMS_CENTER.CENTER_ID";
			
			String header = getMessage("branch.name");
			
			String textAreaId ="paraFrm_branchName";
			String hiddenFieldId ="paraFrm_branchId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String f9department() {
		try {
			String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY HRMS_DEPT.DEPT_ID";
			
			String header = getMessage("dept.name");
			
			String textAreaId ="paraFrm_departmentName";
			String hiddenFieldId ="paraFrm_departmentId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String f9employeeType(){
		try {
			String query = "SELECT HRMS_EMP_TYPE.TYPE_ID, HRMS_EMP_TYPE.TYPE_NAME FROM HRMS_EMP_TYPE " 
			+ " WHERE HRMS_EMP_TYPE.IS_ACTIVE='Y'"
			+ " ORDER BY HRMS_EMP_TYPE.TYPE_ID";
			
			String header = getMessage("credit.name");
			
			String textAreaId ="paraFrm_empTypeName";
			String hiddenFieldId ="paraFrm_empTypeId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String f9employee() {
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_ID, TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) "
				+ " FROM HRMS_EMP_OFFC ";
			query += getprofileQuery(earningBean);
			
			if(!earningBean.getDivisionId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_DIV ="+earningBean.getDivisionId();
			}
			if(!earningBean.getBranchId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_CENTER ="+earningBean.getBranchId();
			}
			if(!earningBean.getPaybillId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_PAYBILL ="+earningBean.getPaybillId();
			} 
			if(!earningBean.getDepartmentId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_DEPT =" +earningBean.getDepartmentId();
			} 
			if(!earningBean.getEmpTypeId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_TYPE =" +earningBean.getEmpTypeId();
			} 
			query += " ORDER BY HRMS_EMP_OFFC.EMP_ID  ";
			
			String header = getMessage("employee");
			String textAreaId ="paraFrm_empName";
			String hiddenFieldId ="paraFrm_empId";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	
	
	public String f9deductions() {
		try {
			String query = " SELECT DISTINCT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' ') FROM HRMS_DEBIT_HEAD ";
			query +=" ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
			
			String header = getMessage("debit.name");
			String textAreaId ="paraFrm_deductionCompName";
			String hiddenFieldId ="paraFrm_deductionCompId";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String reset() {
		earningBean.setEarningMonth("");
		earningBean.setEarningYear("");
		earningBean.setEarningCompId("");
		earningBean.setEarningCompName("");
		earningBean.setDeductionCompId("");
		earningBean.setDeductionCompName("");
		earningBean.setDivisionId("");
		earningBean.setDivisionName("");
		earningBean.setBranchId("");
		earningBean.setBranchName("");
		earningBean.setPaybillId("");
		earningBean.setPaybillName("");
		earningBean.setDepartmentId("");
		earningBean.setDepartmentName("");
		earningBean.setEmpTypeId("");
		earningBean.setEmpTypeName("");
		earningBean.setEmpId("");
		earningBean.setEmpName("");
		
		return input();
	}
	
	/**
	 * This function is used to generate report when report button is clicked
	 * 
	 */
	
	public String report(){
		try {
			EmployeeMonthlyEarningReportModel model = new EmployeeMonthlyEarningReportModel();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(earningBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public final String mailReport(){
		try {
			EmployeeMonthlyEarningReportModel model = new EmployeeMonthlyEarningReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			model.generateReport(earningBean, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
}
