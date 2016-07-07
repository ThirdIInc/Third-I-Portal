package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalSummaryReport;
import org.paradyne.bean.PAS.MonthlyRatingReport;
import org.paradyne.model.PAS.AppraisalSummaryReportModel;
import org.paradyne.model.PAS.MonthlyRatingReportModel;
import org.struts.lib.ParaActionSupport;

public class MonthlyRatingReportAction extends ParaActionSupport
{
	MonthlyRatingReport monthlyratingbean;

	public MonthlyRatingReport getMonthlyrating() {
		return monthlyratingbean;
	}

	public void setMonthlyrating(MonthlyRatingReport monthlyrating) {
		this.monthlyratingbean = monthlyrating;
	}
	
	public void prepare_local() throws Exception {
		monthlyratingbean = new MonthlyRatingReport();
		monthlyratingbean.setMenuCode(1120);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return monthlyratingbean;
	}
	
	public String input() {
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String reset()
	{
		
		monthlyratingbean.setDivision("");
		monthlyratingbean.setDivId("");
		monthlyratingbean.setBranch("");
		monthlyratingbean.setBranchId("");
		monthlyratingbean.setDept("");
		monthlyratingbean.setDeptId("");
		monthlyratingbean.setDesg("");
		monthlyratingbean.setDesgId("");
		monthlyratingbean.setMonth("");
		monthlyratingbean.setYear("");
		monthlyratingbean.setManager("");
		monthlyratingbean.setManagerID("");
		monthlyratingbean.setReportType("");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	
public String f9Division()throws Exception{
		
		String query = " SELECT DIV_NAME,DIV_ID	 FROM HRMS_DIVISION";
		
		String[] headers = {getMessage("appraisal.div")};
		
		String[] headerWidth = { "50" };
		
		String[] fieldNames = { "division","divId"};
		
		int[] columnIndex = { 0,1};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		
		return "f9page";
	}

public String f9Branch()throws Exception{
	
	String query = " SELECT CENTER_NAME,CENTER_ID	FROM HRMS_CENTER";
	
	String[] headers = {getMessage("appraisal.branch")};
	
	String[] headerWidth = { "50" };
	
	String[] fieldNames = { "branch","branchId"};
	
	int[] columnIndex = { 0,1};
	
	String submitFlag = "false";
	
	String submitToMethod = "";
	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	
	return "f9page";
}	


public String f9Dept()throws Exception{
	
	String query = " SELECT DEPT_NAME,DEPT_ID	FROM HRMS_DEPT";
	
	String[] headers = {getMessage("appraisal.dept")};
	
	String[] headerWidth = { "50" };
	
	String[] fieldNames = { "dept","deptId"};
	
	int[] columnIndex = { 0,1};
	
	String submitFlag = "false";
	
	String submitToMethod = "";
	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	
	return "f9page";
}	


public String f9Desg()throws Exception{
	
	String query = " SELECT RANK_NAME,RANK_ID	FROM HRMS_RANK";
	
	String[] headers = {getMessage("appraisal.desg")};
	
	String[] headerWidth = { "50" };
	
	String[] fieldNames = { "desg","desgId"};
	
	int[] columnIndex = { 0,1};
	
	String submitFlag = "false";
	
	String submitToMethod = "";
	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	
	return "f9page";
}	

public String f9Manager() throws Exception
{
String query = " SELECT EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME  AS Manager, EMP_ID FROM HRMS_EMP_OFFC ";
	
	String[] headers = {"Manager Token", "Manager Name"};
	
	String[] headerWidth = { "20","50" };
	
	String[] fieldNames = { "managerToken","manager","managerID"};
	
	int[] columnIndex = { 0,1,2};
	
	String submitFlag = "false";
	
	String submitToMethod = "";
	
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
	
	
	return "f9page";
}


public String getReport()throws Exception{
	
	try {
		MonthlyRatingReportModel model = new MonthlyRatingReportModel();
		model.initiate(context, session);
		model.getReport(monthlyratingbean, request, response);
		model.terminate();		
		} 
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	getNavigationPanel(1);
	return null;
}
}