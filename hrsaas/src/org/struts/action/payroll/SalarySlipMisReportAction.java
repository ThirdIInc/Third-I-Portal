package org.struts.action.payroll;

import org.paradyne.bean.payroll.SalarySlipMisReport;
import org.paradyne.model.payroll.SalarySlipMisReportModel;
import org.struts.lib.ParaActionSupport;

public class SalarySlipMisReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SalarySlipMisReportAction.class);

	SalarySlipMisReport salMisbean;
	
	
	                           
	
	public SalarySlipMisReport getSalMisbean() {
		return salMisbean;
	}

	public void setSalMisbean(SalarySlipMisReport salMisbean) {
		this.salMisbean = salMisbean;
	}

	public void prepare_local() throws Exception {
		salMisbean = new SalarySlipMisReport();
		salMisbean.setMenuCode(968);

	}

	public Object getModel() {
		return salMisbean;
	}
	/*
	 * when the user is General user Setting the User Details when clicking the Link.
	 */
	public String input() throws Exception {
		SalarySlipMisReportModel model = new SalarySlipMisReportModel();
		model.initiate(context, session);
		model.terminate();
		return SUCCESS;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		SalarySlipMisReportModel model = new SalarySlipMisReportModel();
		model.initiate(context,session);	
		if (salMisbean.isGeneralFlag()) {
			model.getEmployeeDetails(salMisbean.getUserEmpId(),salMisbean);
		}
		model.terminate();
	
    }

	/*
	 * report method will call for admin user 
	 * Rule 1. if employee select generating direct pdf report.
	 * Rule 2. if employee not select salary is not process for particular month showing one alert message.
	 * Rule 3. if salary process showing the DownloadURL list in the jsp page.reporting.to
	 */

	public String report() throws Exception {
		System.out.println("-----------REPORT---------");
		SalarySlipMisReportModel model = new SalarySlipMisReportModel();
		model.initiate(context, session);
		String [] headers={getMessage("role"),getMessage("department"),getMessage("employee.id"),getMessage("bank.name"),
				getMessage("employee.type"),getMessage("pay.mode"),getMessage("doj"),getMessage("sal.name"),getMessage("PF.number"),getMessage("PAN.number"),
				getMessage("acc.no"),getMessage("grade"),getMessage("sal.grad"),getMessage("branch"),getMessage("designation"),getMessage("pay.bill"),getMessage("trade"),getMessage("reporting.to")};
		if (salMisbean.getEmpCode().equals("")) {
			System.out.println("CHandini......");			
			String str=model.generateUrlList(request, response, salMisbean);
			if(!str.equals("1"))
			addActionMessage(str);
			model.terminate();
			return SUCCESS;
			
		} else {
			
			logger.info(" In If EmpCode------" + salMisbean.getEmpCode());
			model.generateReport(request, response, salMisbean,0,headers,"");
			model.terminate();
			return null;

		}

	}
	/*
	 * This method will call when the general user generate the report.
	 * 
	 */
	public String genreport() throws Exception {
		System.out.println("-----------GENREPORT---------");
		SalarySlipMisReportModel model = new SalarySlipMisReportModel();
		model.initiate(context, session);
		String [] headers={getMessage("role"),getMessage("department"),getMessage("employee.id"),getMessage("bank.name"),
				getMessage("employee.type"),getMessage("pay.mode"),getMessage("doj"),getMessage("sal.name"),getMessage("PF.number"),getMessage("PAN.number"),
				getMessage("acc.no"),getMessage("grade"),getMessage("sal.grad"),getMessage("branch"),getMessage("designation"),getMessage("payBill"),getMessage("trade"),getMessage("reporting.to")};
			logger.info(" In If EmpCode------" + salMisbean.getEmpCode());
			model.generateReport(request, response, salMisbean,0,headers,"");
			model.terminate();
			return null;
	}
	
	/*
	 * This method will call when the user Click on the DownLoad Report Link. 
	 */

	public String reportforLink() throws Exception {
		SalarySlipMisReportModel model = new SalarySlipMisReportModel();
		model.initiate(context, session);
		String range = (String) request.getParameter("rangeValue");
		String [] headers={getMessage("role"),getMessage("department"),getMessage("employee.id"),getMessage("bank.name"),
				getMessage("employee.type"),getMessage("pay.mode"),getMessage("doj"),getMessage("sal.name"),getMessage("PF.number"),getMessage("PAN.number"),
				getMessage("acc.no"),getMessage("grade"),getMessage("sal.grad"),getMessage("branch"),getMessage("designation"),getMessage("payBill"),getMessage("trade"),getMessage("reporting.to")};
		if (range != null && range != "") {
			salMisbean.setRangeCode(range);
			System.out.println("RRRRRRRRR value--@@@@@@@@@>" + salMisbean.getRangeCode());
			model.generateReport(request, response, salMisbean,0,headers,"");
		}

		model.terminate();
		return null;
	}
	
		
	/*
	 * when the user is General user no Need to Clear the Employee Details.
	 * Other wise Reset all the Details. 
	 */
	public String reset() throws Exception {

		if (!salMisbean.isGeneralFlag()) {
		salMisbean.setSalBranchId("");
		salMisbean.setEmpCode("");
		salMisbean.setEmpName("");
		salMisbean.setEmpToken("");
		salMisbean.setSalDivisionName("");
		salMisbean.setSalDivisionId("");
		
		}
		
		salMisbean.setSalYear("");
		salMisbean.setSalMonth("");
		salMisbean.setSalBranchName("");
		salMisbean.setSalBranchId("");
		
		salMisbean.setSalDeptName("");
		salMisbean.setSalDeptId("");
		salMisbean.setSalEmpTypeName("");
		salMisbean.setSalEmpTypeId("");
		salMisbean.setEmpRankId("");
		salMisbean.setEmpRank("");
		salMisbean.setPaybillId("");
		salMisbean.setPaybillName("");
		salMisbean.setReportingToId("");
		salMisbean.setReportingToToken("");
		salMisbean.setReportingToName("");
		salMisbean.setSalarySlipFor("S");
		return SUCCESS;
	}

	public String f9employee() throws Exception {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,HRMS_EMP_OFFC.EMP_ID,"
				+ "	HRMS_EMP_OFFC.EMP_DIV,NVL(DIV_NAME,' ') FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER"
				+ "	INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)";
				
				query += getprofileQuery(salMisbean);
		
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "EmpToken", "EmpName", "EmpCode",
				"salDivisionId", "salDivisionName" };

		int[] columnIndex = { 0, 1, 2, 3, 4 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Branch() throws Exception {
		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "salBranchId", "salBranchName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Dept() throws Exception {
		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY DEPT_ID";
		String[] headers = { getMessage("department.code"),
				getMessage("department") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "salDeptId", "salDeptName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Emptype() throws Exception {
		String query = "SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE	";

		String[] headers = { getMessage("employee.type") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "salEmpTypeName", "salEmpTypeId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Division() throws Exception {

		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		
		if(salMisbean.getUserProfileDivision() !=null && salMisbean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+salMisbean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "salDivisionId", "salDivisionName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9designation(){
		try {
			String query = " SELECT HRMS_RANK.RANK_ID, NVL(HRMS_RANK.RANK_NAME,' ') FROM HRMS_RANK WHERE HRMS_RANK.IS_ACTIVE = 'Y' ORDER BY HRMS_RANK.RANK_ID ";
			
			String[] headers = { "Designation Code", getMessage("designation")};
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "empRankId", "empRank" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String f9paybill(){
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "
				+ " ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
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
	
	/**
	 * Method to select the Reporting Person of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9reportingTo(){
		try {
		String query = "SELECT EMP_TOKEN,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " WHERE EMP_STATUS	='S' ";
		
		
			if (!(salMisbean.getUserEmpId().equals("") || salMisbean.getUserEmpId().equals("null"))) {
				query += "  AND EMP_ID NOT IN (SELECT HRMS_EMP_OFFC.EMP_ID "
						+ " FROM HRMS_EMP_OFFC "
						+ " START WITH HRMS_EMP_OFFC.EMP_ID = "
						+salMisbean.getUserEmpId()
						+ " CONNECT BY PRIOR EMP_ID = EMP_REPORTING_OFFICER )";
			}
		
		query += "  ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "reportingToToken",	"reportingToName", "reportingToId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = " ";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

}
