package org.struts.action.payroll;

import org.paradyne.bean.payroll.EmployeeAnnualEarningReport;
import org.paradyne.model.payroll.EmployeeAnnualEarningReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1383
 *
 */
public class EmployeeAnnualEarningReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeeAnnualEarningReportAction.class);
	
	EmployeeAnnualEarningReport annualBean;
	
	public final void prepare_local() throws Exception {
		annualBean = new EmployeeAnnualEarningReport();
		annualBean.setMenuCode(2261);
	}
	
	public final Object getModel() {
		return annualBean;
	}
	
	/* This method displays the default mapped page.
	 * input
	 */
	public final String input() {
		return INPUT;
	}
	
	/** This method displays the available paybills to select.
	 * @return paybill
	 */
	public final String f9paybill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL WHERE 1=1"; 
			if(!(annualBean.getUserProfilePaybill().equals(""))){
				query += " AND HRMS_PAYBILL.PAYBILL_ID IN ("+annualBean.getUserProfilePaybill()+")";
			}
			
			query += " ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String header = getMessage("pay.bill");
			
			String textAreaId ="paraFrm_paybillName";
			String hiddenFieldId ="paraFrm_paybillId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/** This method displays the available division to select.
	 * @return division
	 */
	public final String f9Division() {
		try {
			String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID, NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_DIVISION ";
			if(!(annualBean.getUserProfileDivision().equals(""))){
				query+= " WHERE HRMS_DIVISION.DIV_ID IN ("+annualBean.getUserProfileDivision() +")"; 
			}
			query+= " ORDER BY  HRMS_DIVISION.DIV_ID ";
			
			String header = getMessage("division");
			
			String textAreaId ="paraFrm_divisionName";
			String hiddenFieldId ="paraFrm_divisionId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/**This method displays the available branch to select.
	 * @return branch
	 */
	
	public final String f9branch() {
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER WHERE 1=1";
			if(!(annualBean.getUserProfileCenters().equals(""))){
				query += " AND HRMS_CENTER.CENTER_ID IN ("+annualBean.getUserProfileCenters()+")";
			}
			query += " ORDER BY HRMS_CENTER.CENTER_ID";
			
			String header = getMessage("branch");
			
			String textAreaId ="paraFrm_branchName";
			String hiddenFieldId ="paraFrm_branchId";
			
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/**This method displays the available department to select.
	 * @return department
	 */
	public final String f9department() {
		try {
			String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY HRMS_DEPT.DEPT_ID";
			
			String header = getMessage("department");
			String textAreaId ="paraFrm_departmentName";
			String hiddenFieldId ="paraFrm_departmentId";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/**This method displays the available employee type to select.
	 * @return employee type
	 */
	public final String f9employeeType() {
		try {
			String query = "SELECT HRMS_EMP_TYPE.TYPE_ID, HRMS_EMP_TYPE.TYPE_NAME FROM HRMS_EMP_TYPE " 
				+ " WHERE HRMS_EMP_TYPE.IS_ACTIVE='Y'"
				+ " ORDER BY HRMS_EMP_TYPE.TYPE_ID";
			
			String header = getMessage("employee.type");
			String textAreaId ="paraFrm_empTypeName";
			String hiddenFieldId ="paraFrm_empTypeId";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/** This method displays the available employee to select.
	 * @return employee
	 */
	public final String f9employee() {
		try {
			String query = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_ID, TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME)"
				+ " FROM HRMS_EMP_OFFC ";
			
			query += getprofileQuery(annualBean);
			
			query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";
			
			String header = getMessage("employee");
			String textAreaId ="paraFrm_empName";
			String hiddenFieldId ="paraFrm_empId";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/**This method displays the available credits to select.
	 * @return credits
	 */
	public final String f9earnings() {
		try {
			String query = " SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ') FROM HRMS_CREDIT_HEAD ";
			query += " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			String[] headers ={getMessage("credit.code"), getMessage("credit.name")};
			String[] headerWidth = {"20", "80"};
			String[] fieldNames = {"earningCompId","earningCompName"};
			int[] columnIndex = {0, 1};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	/**This method displays the available debits to select.
	 * @return debits
	 */
	public final String f9deductions() {
		try {
			String query = " SELECT DISTINCT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' ') FROM HRMS_DEBIT_HEAD ";
			query +=" ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
			String[] headers ={getMessage("debit.code"), getMessage("debit.name")};
			String[] headerWidth = {"20", "80"};
			String[] fieldNames = {"deductionCompId","deductionCompName"};
			int[] columnIndex = {0, 1};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	/** This method resets the fields on the form
	 * @return INPUT
	 */
	public final String reset() {
		annualBean.setFromYear("");
		annualBean.setToYear("");
		annualBean.setEarningCompId("");
		annualBean.setEarningCompName("");
		annualBean.setDeductionCompId("");
		annualBean.setDeductionCompName("");
		annualBean.setDivisionId("");
		annualBean.setDivisionName("");
		annualBean.setBranchId("");
		annualBean.setBranchName("");
		annualBean.setPaybillId("");
		annualBean.setPaybillName("");
		annualBean.setDepartmentId("");
		annualBean.setDepartmentName("");
		annualBean.setEmpTypeId("");
		annualBean.setEmpTypeName("");
		annualBean.setEmpId("");
		annualBean.setEmpToken("");
		annualBean.setEmpName("");
		annualBean.setReportType("");
		return input();
	}
	
	/**This function is used to generate report when report button is clicked.
	 *
	 */
	public final void report() {
		try {
			EmployeeAnnualEarningReportModel model = new EmployeeAnnualEarningReportModel();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(annualBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This method is used to send the report as attachment.
	 * @return view page
	 */
	public final String mailReport(){
		try {
			EmployeeAnnualEarningReportModel model = new EmployeeAnnualEarningReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.generateReport(annualBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
}
