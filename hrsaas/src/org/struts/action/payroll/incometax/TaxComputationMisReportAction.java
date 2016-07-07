/**
 * 
 */
package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.TaxComputationMisReport;
import org.paradyne.model.payroll.incometax.TaxComputationMisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0517
 *
 */
public class TaxComputationMisReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TaxComputationMisReportAction.class);
	TaxComputationMisReport	taxComputation;
	
	public TaxComputationMisReport getTaxComputation() {
		return taxComputation;
	}

	public void setTaxComputation(TaxComputationMisReport taxComputation) {
		this.taxComputation = taxComputation;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		taxComputation = new TaxComputationMisReport();
		taxComputation.setMenuCode(688);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return taxComputation;
	}

	public void prepare_withLoginProfileDetails()throws Exception {
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysdate = formater.format(date);
		String[]split = sysdate.split("/");
		int month =  Integer.parseInt((split[1]));
		int year = Integer.parseInt(String.valueOf(split[2]));
		if(month < 4){
			year = year - 1;
		} //end of if
		taxComputation.setFrmYear(year);
		taxComputation.setToYear(year + 1);
		if(taxComputation.isGeneralFlag()){
			logger.info(" ################ IS GENERAL #############");
			TaxComputationMisReportModel model = new TaxComputationMisReportModel();
			model.initiate(context, session);
			model.setDetailsForGeneralUser(taxComputation);
			model.terminate();
		} //end of if
	}
	
	public String generalReport() throws Exception{
		TaxComputationMisReportModel model = new TaxComputationMisReportModel();
		model.initiate(context, session);
		model.generateReport(request, response, taxComputation);
		model.terminate();
		return null;
	} //end of generalReport method
	
	public String report() throws Exception{
		TaxComputationMisReportModel model = new TaxComputationMisReportModel();
		model.initiate(context, session);
		if(taxComputation.getEmpId().equals("")){
			String str=model.generateUrlList(request, response,taxComputation);
			if(!str.equals("1"))
				addActionMessage(str);
			return SUCCESS;
		} //end of if
		else{
			model.generateReport(request, response, taxComputation);
			model.terminate();
			return null;
		}
		
	} //end of report method
	
	/*
	 * This method will call when the user Click on the DownLoad Report Link. 
	 */
	public String reportforLink() throws Exception {
		TaxComputationMisReportModel model = new TaxComputationMisReportModel();
		model.initiate(context, session);
		String range = (String) request.getParameter("rangeValue");
		if (range != null && range != "") {
			taxComputation.setRangeCode(range);
			System.out.println("RRRRRRRRR value-->" + taxComputation.getRangeCode());
			model.generateReport(request, response, taxComputation);
		}
		model.terminate();
		return null;
	}
	
	public String reset() throws Exception {
		taxComputation.setDivisionId("");
		taxComputation.setDivisionName("");
		taxComputation.setBranchId("");
		taxComputation.setBranchName("");
		taxComputation.setEmpTypeId("");
		taxComputation.setEmpTypeName("");
		taxComputation.setDeptId("");
		taxComputation.setDeptName("");
		taxComputation.setPaybillId("");
		taxComputation.setPaybillName("");
		taxComputation.setReportingToId("");
		taxComputation.setReportingToName("");
		taxComputation.setReportingToToken("");
		
		if(taxComputation.isGeneralFlag()){
			
		}
		else{
			taxComputation.setEmpId("");
			taxComputation.setEmpName("");
			taxComputation.setEmpToken("");
		}
		return SUCCESS;
	}
	
	public String f9Division() throws Exception {

		String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		

		if(taxComputation.getUserProfileDivision() !=null && taxComputation.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+taxComputation.getUserProfileDivision() +")"; 
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
	
	public String f9Branch() throws Exception {
		String query = " SELECT DISTINCT CENTER_ID,NVL(CENTER_NAME,' ') FROM HRMS_CENTER ORDER BY CENTER_ID";

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
		String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY DEPT_ID";
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
	
	public String f9employee() throws Exception {
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,HRMS_EMP_OFFC.EMP_ID,"
				+ "	HRMS_EMP_OFFC.EMP_DIV,NVL(DIV_NAME,' ') FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER"
				+ "	INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)";
			
				query += getprofileQuery(taxComputation);
			
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

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
	public String f9reportingTo(){
		try {
		String query = "SELECT EMP_TOKEN,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " WHERE EMP_STATUS	='S' ";
		
		
			if (!(taxComputation.getUserEmpId().equals("") || taxComputation.getUserEmpId().equals("null"))) {
				query += "  AND EMP_ID NOT IN (SELECT HRMS_EMP_OFFC.EMP_ID "
						+ " FROM HRMS_EMP_OFFC "
						+ " START WITH HRMS_EMP_OFFC.EMP_ID = "
						+taxComputation.getUserEmpId()
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
	
	public String f9paybill() {
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

} //end of class
