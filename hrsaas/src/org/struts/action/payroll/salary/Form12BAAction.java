package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.Form12BA;
import org.paradyne.model.payroll.MonthlyEDSummaryReportModel;
import org.paradyne.model.payroll.salary.Form12BAModel;
import org.struts.lib.ParaActionSupport;

public class Form12BAAction extends ParaActionSupport {

	
	Form12BA frm12 ;
	public void prepare_local() throws Exception {
		frm12 = new Form12BA();
		frm12.setMenuCode(731);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return frm12;
	}

	public Form12BA getFrm12() {
		return frm12;
	}

	public void setFrm12(Form12BA frm12) {
		this.frm12 = frm12;
	}
	
	public void report(){
		try {
			Form12BAModel model = new Form12BAModel();
			model.initiate(context, session);
			String[] data = new String[] { frm12.getEmpId(),frm12.getEmpName(),
					frm12.getFromYear(), frm12.getToYear(), frm12.getDesg(),
					frm12.getDivId(), frm12.getPanNo() };
			String reportPath = "";
			model.generateReport(frm12, response,request, reportPath,data);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String reset()
	{
		frm12.setEmpId("");
		frm12.setEmpName("");
		frm12.setEmpToken("");
		frm12.setFromYear("");
		frm12.setToYear("");
		frm12.setDesg("");
		frm12.setDivId("");
		frm12.setPanNo("");
		frm12.setRptType("Pdf"); 
		return "success";
	}
	
	
	/**
	 * @This method is used for show the Employee information.
	 */
	public String f9Employee()
	{
		try
		{
			
	String query = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,NVL(RANK_NAME,' ')," 
			    +"  NVL(SAL_PANNO,' '),EMP_DIV,HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC "
		        +"	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
		        +"  LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " ;
	
	query += getprofileQuery(frm12);
	
	query +=" ORDER BY EMP_ID "; 
			
			String[] headers = {getMessage("employee.id"),getMessage("employee")};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"empToken","empName","desg","panNo","divId","empId"};

			int[] columnIndex = {0, 1,2,3,4,5};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	} // end of f9Employee

	public final String f9reportType() {
		try {
			String[][] type = new String[][]{{"PDF"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"rptType"};
			int[] columnIndex = {0};
			String submitFlag = "true";
			String submitToMethod = "Form12BA_mailReport.action";
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public final String mailReport(){
		try {
			Form12BAModel model = new Form12BAModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
			String[] data = new String[] { frm12.getEmpId(),frm12.getEmpName(),
					frm12.getFromYear(), frm12.getToYear(), frm12.getDesg(),
					frm12.getDivId(), frm12.getPanNo() };
			model.generateReport(frm12, response,  request, reportPath, data);
		//	model.generateReport(bean, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String input(){
		//deleteFile();
		return INPUT;
	}
}
