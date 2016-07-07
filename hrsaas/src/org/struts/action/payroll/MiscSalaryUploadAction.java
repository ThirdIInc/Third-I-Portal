package org.struts.action.payroll;

import org.paradyne.bean.payroll.MiscSalaryUpload;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.MiscSalaryUploadModel;
import org.struts.action.DataMigration.MigratePayrollExcelData;
import org.struts.lib.ParaActionSupport;

public class MiscSalaryUploadAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MiscSalaryUploadAction.class);
	MiscSalaryUpload miscBean;

	public void prepare_local() throws Exception {
		miscBean = new MiscSalaryUpload();
		miscBean.setMenuCode(2232);
	}

	public Object getModel() {
		return miscBean;
	}
	
	public String input(){
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			String dataPath = getText("data_path") + "/DataMigration/"+ poolName + "/";
			miscBean.setDataPath(dataPath);
			boolean isAuthorized = checkAuthorization();
			
			if(!isAuthorized){
				String msg = "You are not authorized to access this page." 
					+ "<br>To set authorization please proceed to" 
					+ "<br>Configuration -> Application Setting -> Configure Authorization";
				request.setAttribute("unauthorizedMessage", msg);
				return "unauthorizedPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	/**
	 * This function is used to download the template as per the filter
	 * 
	 */
	public void downloadTemplate(){
		try {
			MiscSalaryUploadModel model = new MiscSalaryUploadModel();
			model.initiate(context, session);
			model.generateTemplate(miscBean, response, "TEMPLATE");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This function is used to upload the salary details of the employees as per the filter
	 *
	 * 
	 * @return
	 */
	public String uploadMiscSalaryDetails() {
		try {
			MiscSalaryUploadModel model = new MiscSalaryUploadModel();
			model.initiate(context, session);
			model.uploadMiscSalaryData(response, request, miscBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String f9earnings() {
		try {
			String query = " SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ') FROM HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y'";
			
			if(!(miscBean.getManagerCode().equals("") && miscBean.getManagerCreditCode().equals("0"))){
				query += " AND HRMS_CREDIT_HEAD.CREDIT_CODE IN ("+miscBean.getManagerCreditCode()+")";
			}
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
	
	public String f9deductions() {
		try {
			String query = " SELECT DISTINCT HRMS_DEBIT_HEAD.DEBIT_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' ') FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y'";
			
			if(!(miscBean.getManagerCode().equals("") && miscBean.getManagerDebitCode().equals("0"))){
				query += " AND HRMS_DEBIT_HEAD.DEBIT_CODE IN ("+miscBean.getManagerDebitCode()+")";
			}
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
	public String f9employee() {
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_ID, TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) "
				+ " FROM HRMS_EMP_OFFC ";
			query += getprofileQuery(miscBean);
			query+= " AND HRMS_EMP_OFFC.EMP_STATUS='S'";
			
			if(!miscBean.getDivisionId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_DIV ="+miscBean.getDivisionId();
			}
			if(!miscBean.getBranchId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_CENTER ="+miscBean.getBranchId();
			}
			if(!miscBean.getPaybillId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_PAYBILL ="+miscBean.getPaybillId();
			} 
			if(!miscBean.getGradeId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_CADRE ="+miscBean.getGradeId();
			} 
			if(!miscBean.getDepartmentId().equals("")){
				query+= " AND HRMS_EMP_OFFC.EMP_DEPT =" +miscBean.getDepartmentId();
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
	public String f9paybill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL WHERE 1=1"; 
			if(!(miscBean.getUserProfilePaybill().equals(""))){
				query += " AND HRMS_PAYBILL.PAYBILL_ID IN ("+miscBean.getUserProfilePaybill()+")";
			}
			if(!(miscBean.getManagerPaybillCode().equals("0"))){
				query += " AND HRMS_PAYBILL.PAYBILL_ID IN ("+miscBean.getManagerPaybillCode()+")";
			}
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
	
	public String f9division() {
		 try {
			 String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID, NVL(HRMS_DIVISION.DIV_NAME,' '), NVL(DIV_ABBR,NVL(HRMS_DIVISION.DIV_NAME,' ')) FROM HRMS_DIVISION WHERE 1=1";
				if(!(miscBean.getUserProfileDivision().equals(""))){
					query+= " AND HRMS_DIVISION.DIV_ID IN ("+miscBean.getUserProfileDivision() +")"; 
				}
				if(!(miscBean.getManagerCode().equals(""))){
					query+= " AND HRMS_DIVISION.DIV_ID IN ("+miscBean.getManagerDivCode() +")"; 
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
	
	public String f9branch() {
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER WHERE 1=1";
			if(!(miscBean.getUserProfileCenters().equals(""))){
				query += " AND HRMS_CENTER.CENTER_ID IN ("+miscBean.getUserProfileCenters()+")";
			}
			if(!(miscBean.getManagerBranchCode().equals("0"))){
				query += " AND HRMS_CENTER.CENTER_ID IN ("+miscBean.getManagerBranchCode()+")";
			}
			
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
	
	public String f9grade() {
		try {
			String query = " SELECT DISTINCT HRMS_CADRE.CADRE_ID, NVL(HRMS_CADRE.CADRE_NAME,' ') FROM HRMS_CADRE ORDER BY HRMS_CADRE.CADRE_ID";
			
			String[] headers = { "Grade Code", getMessage("grade")};
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
	
	public String f9department() {
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
	
	public String reset() {
		
		miscBean.setEarningCompId("");
		miscBean.setEarningCompName("");
		miscBean.setDeductionCompId("");
		miscBean.setDeductionCompName("");
		miscBean.setDivisionId("");
		miscBean.setDivisionName("");
		miscBean.setBranchId("");
		miscBean.setBranchName("");
		miscBean.setPaybillId("");
		miscBean.setPaybillName("");
		miscBean.setGradeId("");
		miscBean.setGradeName("");
		miscBean.setDepartmentId("");
		miscBean.setDepartmentName("");
		miscBean.setEmpId("");
		miscBean.setEmpName("");
		miscBean.setUploadFileName("");
		miscBean.setOverwriteChk("");
		miscBean.setAppendChk("");
		miscBean.setYear("");
		miscBean.setMonth("");
		miscBean.setDisplayNoteFlag(false);
		miscBean.setShowStatisticsFlag(false);
		return input();
	}
	
	/**
	 * This function is used to view the uploaded file
	 * 
	 */
	public void viewUploadedFile() {
		try {
			//String uploadFileName = request.getParameter("uploadFileName");
			String uploadFileName = miscBean.getUploadedFile();
			String dataPath = request.getParameter("dataPath");
			
			System.out.println("#######uploadFileName#######"+uploadFileName);
			System.out.println("#######dataPath#######"+dataPath);
			MigratePayrollExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	/** DO NOT DELETE
	 * This function is used to generate report when report button is clicked.
	 * 
	 */
	
	public void Old_report(){
		try {
			MiscSalaryUploadModel model = new MiscSalaryUploadModel();
			model.initiate(context, session);
			model.generateTemplate(miscBean, response, "REPORT");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function is used to generate report when report button is clicked.
	 *
	 */
	public final void report() {
		try {
			MiscSalaryUploadModel model = new MiscSalaryUploadModel();
			model.initiate(context, session);
			String reportPath = "";
			model.generateReport(miscBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final String mailReport(){
		try {
			MiscSalaryUploadModel model = new MiscSalaryUploadModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/PayrollReport" + poolName + "/";
			model.generateReport(miscBean, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	/** This funciton is used to get the statistics of the data uploaded.
	 * 
	 */
	public String showStatistics(){
		try {
			MiscSalaryUploadModel model = new MiscSalaryUploadModel();
			model.initiate(context, session);
			model.fetchStatisticsByMonthYear(miscBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * This function is used to check if the user is a manager 
	 * 
	 */
	
	public boolean checkAuthorization(){
		boolean isAuthorized = false;
		try {
			MiscSalaryUploadModel model = new MiscSalaryUploadModel();
			model.initiate(context, session);
			String loginId = miscBean.getUserEmpId();
			
			String query = "SELECT HRMS_SAL_UPLOAD_AUTH.DIV_CODE, HRMS_SAL_UPLOAD_AUTH.PAYBILL_CODE, HRMS_SAL_UPLOAD_AUTH.CENTER_CODE, HRMS_SAL_UPLOAD_AUTH.DEBIT_CODE, HRMS_SAL_UPLOAD_AUTH.CREDIT_CODE FROM HRMS_SAL_UPLOAD_AUTH "
				+" WHERE HRMS_SAL_UPLOAD_AUTH.MANAGER_CODE ="+loginId;
			logger.info("miscBean.getDivisionId()=="+miscBean.getDivisionId());
			if(!miscBean.getDivisionId().equals("")){
				query+=" AND DIV_CODE="+miscBean.getDivisionId();
			}
			Object[][] dataObj = model.getSqlModel().getSingleResult(query);
			
			String divCodes="0";
			String paybillCodes="0";
			String branchCodes="0";
			String debitCodes="0";
			String creditCodes="0";
			
			if(dataObj!=null && dataObj.length >0){
				for (int i = 0; i < dataObj.length; i++) {
					if(!( Utility.checkNull(String.valueOf(dataObj[i][0])).equals(""))){
						divCodes += ","+String.valueOf(dataObj[i][0]);
					}
					if(!(Utility.checkNull(String.valueOf(dataObj[i][1])).equals(""))){
						paybillCodes += ","+String.valueOf(dataObj[i][1]);
					}
					if(!(Utility.checkNull(String.valueOf(dataObj[i][2])).equals(""))){
						branchCodes += ","+String.valueOf(dataObj[i][2]);
					}
					if(!(Utility.checkNull(String.valueOf(dataObj[i][3])).equals(""))){
						debitCodes += ","+String.valueOf(dataObj[i][3]);
					}
					if(!(Utility.checkNull(String.valueOf(dataObj[i][4])).equals(""))){
						creditCodes += ","+String.valueOf(dataObj[i][4]);
					}
				}
				miscBean.setManagerDivCode(divCodes);
				miscBean.setManagerPaybillCode(paybillCodes);
				miscBean.setManagerBranchCode(branchCodes);
				miscBean.setManagerCode(loginId);
				miscBean.setManagerDebitCode(debitCodes);
				miscBean.setManagerCreditCode(creditCodes);
				
				isAuthorized= true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAuthorized;
	}
}
