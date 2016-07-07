/**
 * 
 */
package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.Form16MisReport;
import org.paradyne.model.payroll.incometax.Form16MisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0517
 *
 */
public class Form16MisReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.action.payroll.incometax.Form16MisReportAction.class);

	Form16MisReport bulkForm16;
	
	public Form16MisReport getBulkForm16() {
		return bulkForm16;
	}

	public void setBulkForm16(Form16MisReport bulkForm16) {
		this.bulkForm16 = bulkForm16;
	}

	public void prepare_local() throws Exception {
		bulkForm16 = new Form16MisReport();
		bulkForm16.setMenuCode(288);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return bulkForm16;
	}
	/**
	 * This method is called onload and used to set the employee information if logged employee is general employee profile
	 */
	public void prepare_withLoginProfileDetails()throws Exception {
		try {
			
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			bulkForm16.setSource(source);
			
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String sysdate = formater.format(date);
			String[] split = sysdate.split("/");
			int month = Integer.parseInt((split[1]));
			int year = Integer.parseInt(String.valueOf(split[2]));
			if (month < 4) {
				year = year - 1;
			} //end of if
			bulkForm16.setFrmYear(year);
			bulkForm16.setToYear(year + 1);
			bulkForm16.setForm12Flag("true");// to check whether to include form 12BA
			if (bulkForm16.isGeneralFlag()) {
				Form16MisReportModel model = new Form16MisReportModel();
				model.initiate(context, session);
				model.setDetailsForGeneralUser(bulkForm16);
				model.terminate();
			} //end of if
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * This method is used to generate the report for general employees
	 * @return
	 * @throws Exception
	 */
	public String generalReport() throws Exception{
		Form16MisReportModel model = new Form16MisReportModel();
		model.initiate(context, session);
		model.generateReport(request, response, bulkForm16);
		model.terminate();
		return null;
	} //end of generalReport method
	
	/**
	 * This method is used to generate the report in bulk
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception{
		Form16MisReportModel model = new Form16MisReportModel();
		model.initiate(context, session);
		if(bulkForm16.getEmpId().equals("")){
			String str=model.generateUrlList(request, response,bulkForm16);
			if(!str.equals("1"))
				addActionMessage(str);
			return SUCCESS;
		} //end of if
		else{
			model.generateReport(request, response, bulkForm16);
			model.terminate();
			return null;
		}
	} //end of report method
	
	/*
	 * This method will call when the user Click on the DownLoad Report Link. 
	 */
	public String reportforLink() throws Exception {
		Form16MisReportModel model = new Form16MisReportModel();
		model.initiate(context, session);
		String range = (String) request.getParameter("rangeValue");
		if (range != null && range != "") {
			bulkForm16.setRangeCode(range);
			System.out.println("RRRRRRRRR value-->" + bulkForm16.getRangeCode());
			model.generateReport(request, response, bulkForm16);
		}
		model.terminate();
		return null;
	}
	/**
	 * This methods will reset all the fields
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		bulkForm16.setDivisionId("");
		bulkForm16.setDivisionName("");
		bulkForm16.setBranchId("");
		bulkForm16.setBranchName("");
		bulkForm16.setEmpTypeId("");
		bulkForm16.setEmpTypeName("");
		bulkForm16.setDeptId("");
		bulkForm16.setDeptName("");
		bulkForm16.setRepType("Pdf");
		bulkForm16.setForm12Flag("true");
		bulkForm16.setSignDate("");
		if(bulkForm16.isGeneralFlag()){
			
		}
		else{
			bulkForm16.setEmpId("");
			bulkForm16.setEmpName("");
			bulkForm16.setEmpToken("");
		}
		return SUCCESS;
	}
	/**
	 * Method to select the division to filter out employee
	 * @return
	 * @throws Exception
	 */
	public String f9Division() throws Exception {
		
		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') "
						+ " FROM HRMS_DIVISION"
						+ " WHERE 1=1 AND IS_ACTIVE='Y'";
		
		if(bulkForm16.getUserProfileDivision() !=null && bulkForm16.getUserProfileDivision().length()>0)
			query+= " AND DIV_ID IN ("+bulkForm16.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

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
	/**
	 * Method to select the branch to filter out employee
	 * @return
	 * @throws Exception
	 */
	public String f9Branch() throws Exception {
		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') "
			+ " FROM HRMS_CENTER "
			+ " WHERE IS_ACTIVE='Y'" 
			+ " ORDER BY CENTER_ID";

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
	/**
	 * Method to select the department to filter out employee
	 * @return
	 * @throws Exception
	 */
	public String f9Dept() throws Exception {
		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') "
			+ " FROM HRMS_DEPT "
			+ " ORDER BY DEPT_ID";
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
	/**
	 * Method to select the emp Type to filter out employee
	 * @return
	 * @throws Exception
	 */
	public String f9Emptype() throws Exception {
		String query = "SELECT   TYPE_NAME , TYPE_ID FROM HRMS_EMP_TYPE	";

		String[] headers = { getMessage("employee.type") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "empTypeName","empTypeId"};

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/**
	 * Method to select the employee 
	 * @return
	 * @throws Exception
	 */
	public String f9employee() throws Exception {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,HRMS_EMP_OFFC.EMP_ID,"
				+ "	HRMS_EMP_OFFC.EMP_DIV,NVL(DIV_NAME,' ') FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER"
				+ "	INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)";
				query += getprofileQuery(bulkForm16);
				query +=  "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "30", "70" };

		String[] fieldNames = { "empToken", "empName", "empId",
				"divisionId", "divisionName" };

		int[] columnIndex = { 0, 1, 2, 3, 4 };

		String submitFlag = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
}
