package org.struts.action.promotion;

import org.paradyne.bean.promotion.PromotionUpload;
import org.paradyne.model.Promotion.PromotionUploadModel;
import org.struts.action.DataMigration.MigratePayrollExcelData;
import org.struts.lib.ParaActionSupport;

public class PromotionUploadAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PromotionUploadAction.class);
	PromotionUpload proBean;

	/* 
	 * prepare_local
	 */
	public final void prepare_local() throws Exception {
		proBean = new PromotionUpload();
		proBean.setMenuCode(2248);
	}

	/* 
	 * getModel
	 */
	public final Object getModel() {
		return proBean;
	}

	/* The default page in displayed.
	 * input
	 */
	public final String input() {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/"+ poolName + "/";
		proBean.setDataPath(dataPath);
		return INPUT;
	}

	/** This function displays the searched data. 
	 * @return search page
	 */
	public final String f9division() {
		 try {
			 String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID, NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_DIVISION ";
				if (!(proBean.getUserProfileDivision().equals(""))) {
					query+= " WHERE HRMS_DIVISION.DIV_ID IN ("+proBean.getUserProfileDivision() +")"; 
				}
				query+= " ORDER BY  HRMS_DIVISION.DIV_ID ";
					
			String[] headers = { getMessage("division.code"), getMessage("division")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "divisionId", "divisionName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function displays the searched data. 
	 * @return search page
	 */
	public final String f9branch() {
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER ";
			query += " ORDER BY HRMS_CENTER.CENTER_ID";
			
			String[] headers = { "Branch Code", getMessage("branch")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "branchId", "branchName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function displays the searched data. 
	 * @return search page
	 */
	public final String f9grade() {
		try {
			String query = " SELECT DISTINCT HRMS_CADRE.CADRE_ID, NVL(HRMS_CADRE.CADRE_NAME,' ') FROM HRMS_CADRE ORDER BY HRMS_CADRE.CADRE_ID";
			
			String[] headers = { "Grade Code",getMessage("grade")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "gradeId", "gradeName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function displays the searched data. 
	 * @return search page
	 */
	public final String f9department() {
		try {
			String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY HRMS_DEPT.DEPT_ID";
			
			String[] headers = { "Department Code", getMessage("department")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "departmentId", "departmentName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function displays the searched data. 
	 * @return search page
	 */
	public final String f9Rank() {
		try {
			String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME) FROM  HRMS_RANK ORDER BY  RANK_ID ";
			String[] headers = { getMessage("designation.id"),
					getMessage("designation") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "desgCode", "desgName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function displays the searched data. 
	 * @return search page
	 */
	public final String f9paybill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "; 
			query +=" ORDER BY HRMS_PAYBILL.PAYBILL_ID";

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

	/** This function displays the searched data. 
	 * @return search page
	 */
	public final String f9EmpType() {
		try {
			String query = " SELECT DISTINCT TYPE_ID,TO_CHAR(TYPE_NAME) FROM  HRMS_EMP_TYPE   ORDER BY  TYPE_ID "; 
			String[] headers = { "Employee Type Code", getMessage("employee.type")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "empTypeId", "empTypeName" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/** This function displays the searched data. 
	 * @return search page
	 */
	public final String f9employee() {
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_ID, TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) "
				+ " FROM HRMS_EMP_OFFC ";
			query += getprofileQuery(proBean);
			if (!proBean.getDivisionId().equals("")) {
				query+= " AND HRMS_EMP_OFFC.EMP_DIV ="+proBean.getDivisionId();
			}
			if (!proBean.getBranchId().equals("")) {
				query+= " AND HRMS_EMP_OFFC.EMP_CENTER ="+proBean.getBranchId();
			}
			if (!proBean.getPaybillId().equals("")) {
				query+= " AND HRMS_EMP_OFFC.EMP_PAYBILL ="+proBean.getPaybillId();
			} 
			if (!proBean.getGradeId().equals("")) {
				query+= " AND HRMS_EMP_OFFC.EMP_CADRE ="+proBean.getGradeId();
			} 
			if (!proBean.getDepartmentId().equals("")) {
				query+= " AND HRMS_EMP_OFFC.EMP_DEPT =" +proBean.getDepartmentId();
			} 
			query += "  AND HRMS_EMP_OFFC.EMP_STATUS='S' ORDER BY HRMS_EMP_OFFC.EMP_ID  ";
			
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
	
	/**
	 * This function is used to download the template as per the filter
	 * 
	 */
	public final void downloadTemplate() {
		try {
			PromotionUploadModel model = new PromotionUploadModel();
			model.initiate(context, session);
			model.generateTemplate(proBean, response, "TEMPLATE");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This function is used to upload the promotion details of the employees as per the filter
	 *
	 * 
	 * @return
	 */
	public final String uploadPromotionDetails() {
		try {
			PromotionUploadModel model = new PromotionUploadModel();
			model.initiate(context, session);
			model.uploadEmployeePromotions(response, request, proBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * This function is used to view the uploaded file
	 * 
	 */
	public final void viewUploadedFile() {
		try {
			//String uploadFileName = request.getParameter("uploadFileName");
			String uploadFileName = proBean.getUploadedFile();
			String dataPath = request.getParameter("dataPath");
			
			System.out.println("#######uploadFileName#######"+uploadFileName);
			System.out.println("#######dataPath#######"+dataPath);
			MigratePayrollExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	
	public final String reset() {
		proBean.setDivisionId("");
		proBean.setDivisionName("");
		proBean.setBranchId("");
		proBean.setBranchName("");
		proBean.setGradeId("");
		proBean.setGradeName("");
		proBean.setDepartmentId("");
		proBean.setDepartmentName("");
		proBean.setDesgCode("");
		proBean.setDesgName("");
		proBean.setPaybillId("");
		proBean.setPaybillName("");
		proBean.setEmpTypeId("");
		proBean.setEmpTypeName("");
		proBean.setEmpId("");
		proBean.setEmpName("");
		proBean.setUploadName("");
		proBean.setUploadFileName("");
		proBean.setUploadedFile("");
		proBean.setNote("");
		proBean.setStatus("Fail");
		proBean.setDisplayNoteFlag(false);
		return input();
	}
}
