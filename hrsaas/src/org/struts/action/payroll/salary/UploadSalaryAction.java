
package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.UploadSalary;
import org.paradyne.model.payroll.salary.UploadSalaryModel;
import org.struts.action.DataMigration.MigratePayrollExcelData;
import org.struts.lib.ParaActionSupport;

public class UploadSalaryAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UploadSalaryAction.class);
	UploadSalary uploadBean;

	public final void prepare_local() throws Exception {
		uploadBean = new UploadSalary();
		uploadBean.setMenuCode(939);
	}

	public final Object getModel() {
		return uploadBean;
	}

	/**This method is called when the link is clicked. 
	 * @return view page
	 */
	public final String input() {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		uploadBean.setDataPath(dataPath);
		return INPUT;
	}

	/** This function is used to select the credit heads. 
	 * @return search page
	 */
	public final String f9earnings() {
		try {
			String query = " SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ') FROM HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y'";

			query += " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";

			String header = getMessage("credit.name");

			String textAreaId ="paraFrm_earningCompName";
			String hiddenFieldId ="paraFrm_earningCompId";

			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/** This function is used to select the debit heads 
	 * @return search page
	 */
	public final String f9deductions() {
		try {
			String query = " SELECT DISTINCT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' ') FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y'";

			query += " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
			String header = getMessage("debit.name");
			String textAreaId = "paraFrm_deductionCompName";
			String hiddenFieldId = "paraFrm_deductionCompId";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/** This function is used to select the employees.
	 * @return search page
	 */
	public final String f9employee() {
		try {
			UploadSalaryModel model = new UploadSalaryModel();
			String query = "SELECT HRMS_EMP_OFFC.EMP_ID, TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) "
				+ " FROM HRMS_EMP_OFFC ";
			query += getprofileQuery(uploadBean);
			query += " AND TO_DATE(TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY'),'MM-YYYY') <= TO_DATE('"+model.fetchUploadPeriod(uploadBean)+"','MM-YYYY') AND (TO_DATE(TO_CHAR(EMP_LEAVE_DATE,'MM-YYYY'),'MM-YYYY') >= " 
					+ " TO_DATE('"+model.fetchUploadPeriod(uploadBean)+"','MM-YYYY') OR EMP_LEAVE_DATE IS NULL)";

			if(!uploadBean.getDivisionId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV ="+uploadBean.getDivisionId();
			}
			/*if(!uploadBean.getBranchId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_CENTER ="+uploadBean.getBranchId();
			}*/
			if(!uploadBean.getPaybillId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_PAYBILL ="+uploadBean.getPaybillId();
			} 
			if(!uploadBean.getGradeId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_CADRE ="+uploadBean.getGradeId();
			}
			/*if(!uploadBean.getDepartmentId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_DEPT =" +uploadBean.getDepartmentId();
			} */
			query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

			String header = getMessage("employee");
			String textAreaId = "paraFrm_empName";
			String hiddenFieldId = "paraFrm_empId";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/** This function is used to select the paybill.
	 * @return search page
	 */
	public final String f9paybill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL WHERE 1=1"; 
			if(!(uploadBean.getUserProfilePaybill().equals(""))) {
				query += " AND HRMS_PAYBILL.PAYBILL_ID IN ("+uploadBean.getUserProfilePaybill()+")";
			}
			query += " ORDER BY HRMS_PAYBILL.PAYBILL_ID";

			String[] headers = { "Paybill Code", getMessage("pay.bill")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "paybillId", "paybillName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function is used to select the division.
	 * @return search page
	 */
	public final String f9division() {
		 try {
			 String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID, NVL(HRMS_DIVISION.DIV_NAME,' '), NVL(DIV_ABBR,NVL(HRMS_DIVISION.DIV_NAME,' ')) FROM HRMS_DIVISION WHERE 1=1";
				if(!(uploadBean.getUserProfileDivision().equals(""))){
					query+= " AND HRMS_DIVISION.DIV_ID IN ("+uploadBean.getUserProfileDivision() +")"; 
				}
				query+= " ORDER BY  HRMS_DIVISION.DIV_ID ";

			String[] headers = { getMessage("division.code"), getMessage("division")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "divisionId", "divisionName", "divisionAbbrevation" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function is used to select the branch.
	 * @return search page
	 */
	public final String f9branch() {
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER WHERE 1=1";
			if (!(uploadBean.getUserProfileCenters().equals(""))) {
				query += " AND HRMS_CENTER.CENTER_ID IN (" + uploadBean.getUserProfileCenters() + ")";
			}
			query += " ORDER BY HRMS_CENTER.CENTER_ID";

			String[] headers = {"Branch Code", getMessage("branch")};
			String[] headerWidth = {"20", "80"};
			String[] fieldNames = {"branchId", "branchName"};
			int[] columnIndex = {0, 1};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function is used to select the grade.
	 * @return search page
	 */
	public final String f9grade() {
		try {
			String query = " SELECT DISTINCT HRMS_CADRE.CADRE_ID, NVL(HRMS_CADRE.CADRE_NAME,' ') FROM HRMS_CADRE ORDER BY HRMS_CADRE.CADRE_ID";
			String[] headers = {"Grade Code", getMessage("grade")};
			String[] headerWidth = {"20", "80"};
			String[] fieldNames = {"gradeId", "gradeName"};
			int[] columnIndex = {0, 1};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function is used to select the department.
	 * @return search page
	 */
	public final String f9department() {
		try {
			String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY HRMS_DEPT.DEPT_ID";
			String[] headers = {"Department Code", getMessage("department")};
			String[] headerWidth = {"20", "80"};
			String[] fieldNames = {"departmentId", "departmentName"};
			int[] columnIndex = {0, 1};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function is used to reset the form fields.
	 *
	 */
	public final String reset() {
		uploadBean.setEarningCompId("");
		uploadBean.setEarningCompName("");
		uploadBean.setDeductionCompId("");
		uploadBean.setDeductionCompName("");
		uploadBean.setDivisionId("");
		uploadBean.setDivisionName("");
		uploadBean.setBranchId("");
		uploadBean.setBranchName("");
		uploadBean.setPaybillId("");
		uploadBean.setPaybillName("");
		uploadBean.setGradeId("");
		uploadBean.setGradeName("");
		uploadBean.setDepartmentId("");
		uploadBean.setDepartmentName("");
		uploadBean.setEmpId("");
		uploadBean.setEmpName("");
		uploadBean.setUploadFileName("");
		uploadBean.setYear("");
		uploadBean.setMonth("");
		uploadBean.setDisplayNoteFlag(false);
		return input();
	}

	/**This function is used to download the template as per the filter.
	 *
	 */
	public final void downloadTemplate() {
		try {
			UploadSalaryModel model = new UploadSalaryModel();
			model.initiate(context, session);
			model.generateTemplate(uploadBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This function is used to upload the salary details of the employees as per the filter.
	 *
	 * @return view
	 */
	public final String uploadSalaryDetails() {
		try {
			UploadSalaryModel model = new UploadSalaryModel();
			model.initiate(context, session);
			model.uploadSalaryData(response, request, uploadBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**This function is used to view the uploaded file.
	 *
	 */
	public final void viewUploadedFile() {
		try {
			String uploadFileName = uploadBean.getUploadedFile();
			String dataPath = request.getParameter("dataPath");
			MigratePayrollExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch (Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
}