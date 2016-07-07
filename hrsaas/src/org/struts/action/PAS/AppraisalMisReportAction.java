package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalMisReport;
import org.paradyne.model.PAS.AppraisalMisReportModel;
import org.paradyne.model.payroll.SalarySlipMisReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalMisReportAction extends ParaActionSupport {

	AppraisalMisReport appraisalMisReport = null;
	public void prepare_local() throws Exception {
		appraisalMisReport = new AppraisalMisReport();
		appraisalMisReport.setMenuCode(823);
	}
	
	public Object getModel() {
		return appraisalMisReport;
	}
	
	public AppraisalMisReport getAppraisalMisReport() {
		return appraisalMisReport;
	}

	public void setAppraisalMisReport(AppraisalMisReport appraisalMisReport) {
		this.appraisalMisReport = appraisalMisReport;
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		AppraisalMisReportModel model = new AppraisalMisReportModel();
		model.initiate(context,session);	
		if (appraisalMisReport.isGeneralFlag()) {
			model.getEmployeeDetails(appraisalMisReport.getUserEmpId(),appraisalMisReport);
		}
		model.terminate();
	
    }
	public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID,TO_CHAR(APPR_CAL_START_DATE,'Mon YYYY')||'-'||TO_CHAR(APPR_CAL_END_DATE,'Mon YYYY') FROM PAS_APPR_CALENDAR "
			+" ORDER BY APPR_ID";

		
		String[] headers = { getMessage("appraisal.code"),getMessage("appraisal.from"),getMessage("appraisal.to")};

		
		String[] headerWidth = { "50","25","25" };

		String[] fieldNames = { "apprCode","frmDate","toDate","apprId","apprPeriod"};

		int[] columnIndex = { 0,1,2,3,4 };

		String submitFlag = "false";

		String submitToMethod = "AppraisalReport_input.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9Branch() throws Exception {
		
		String query = " SELECT  DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER " 
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CENTER.CENTER_ID = hrms_emp_offc.EMP_CENTER) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraisalMisReport.getApprId() 
				+" ORDER BY CENTER_ID ";
		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "branchId", "branchName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9Dept() throws Exception {
		
		String query = " SELECT DISTINCT DEPT_ID,DEPT_NAME FROM  HRMS_DEPT "
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = hrms_emp_offc.EMP_DEPT) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (hrms_emp_offc.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraisalMisReport.getApprId() 
				+" ORDER BY DEPT_ID ";
		String[] headers = { getMessage("department.code"),
				getMessage("department") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "deptId", "deptName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Emptype() throws Exception {
		
		String query = " SELECT  DISTINCT TYPE_ID, TYPE_NAME  FROM HRMS_EMP_TYPE "
			+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE ) "
			+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
			+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraisalMisReport.getApprId() 
			+" ORDER BY TYPE_ID ";

		String[] headers = {"Type Code", getMessage("employee.type") };

		String[] headerWidth = { "30","70" };

		String[] fieldNames = { "empTypeId", "empTypeName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Division() throws Exception {

		String query = " SELECT  DISTINCT DIV_ID, DIV_NAME FROM HRMS_DIVISION "
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+appraisalMisReport.getApprId() 
				+" ORDER BY DIV_ID ";

		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "divisionId", "divisionName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String getURL(){
		AppraisalMisReportModel model = new AppraisalMisReportModel();
		model.initiate(context, session);
		if (appraisalMisReport.isGeneralFlag()) {
			try{
				getReport();
			}catch(Exception e){}
			
		}else{
			model.generateUrlList(request, response, appraisalMisReport);
		}
		model.terminate();
		return SUCCESS;
	}
	
	
	public String f9Employee()throws Exception{
		
		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
						+" HRMS_EMP_OFFC.EMP_ID FROM PAS_APPR_ELIGIBLE_EMP "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)"
						+" WHERE APPR_ID="+appraisalMisReport.getApprId();

		
		String[] headers = { "Employee Token",getMessage("employee.name")};

		
		String[] headerWidth = { "45","50" };

		String[] fieldNames = { "empToken","empName","empId"};

		int[] columnIndex = { 0,1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}

	/*
	 * This method will call when the user Click on the DownLoad Report Link. 
	 */

	public String getReport() throws Exception {
		AppraisalMisReportModel model = new AppraisalMisReportModel();
		String range="";
		model.initiate(context, session);
		System.out.println("################");
		try {
			String[] careerLabels = { getMessage("career.progression"),
					getMessage("career.general.comments") };
			if (appraisalMisReport.isGeneralFlag()) {
				range = String.valueOf(1);
				appraisalMisReport.setRangeCode(range);
				model.generateReport(request, response, appraisalMisReport,
						careerLabels);
			} else {
				range = (String) request.getParameter("rangeValue");
				if (range != null && range != "") {
					System.out.println("range :"+range);
					appraisalMisReport.setRangeCode(range);
					model.generateReport(request, response, appraisalMisReport,
							careerLabels);
				}
				else{
					System.out.println("range1 :"+range);
					appraisalMisReport.setRangeCode("0");
					model.generateReport(request, response, appraisalMisReport,
							careerLabels);
				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return null;
	}
	
	public String reset(){
		
		appraisalMisReport.setApprCode("");
		appraisalMisReport.setFrmDate("");
		appraisalMisReport.setToDate("");
		appraisalMisReport.setApprId("");
		appraisalMisReport.setApprPeriod("");
		appraisalMisReport.setDivisionId("");
		appraisalMisReport.setDivisionName("");
		appraisalMisReport.setBranchId("");
		appraisalMisReport.setBranchName("");
		appraisalMisReport.setDeptId("");
		appraisalMisReport.setDeptName("");
		appraisalMisReport.setEmpTypeId("");
		appraisalMisReport.setEmpTypeName("");
		appraisalMisReport.setEmpId("");
		appraisalMisReport.setEmpToken("");
		appraisalMisReport.setEmpName("");
		appraisalMisReport.setRecordFlag("false");
		appraisalMisReport.setRecordsPerPage("20");
		appraisalMisReport.setRangeCode("");
		
		return SUCCESS;
	}
	
}
