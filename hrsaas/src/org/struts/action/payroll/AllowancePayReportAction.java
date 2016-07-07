/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.AllowancePayReport;
import org.paradyne.model.payroll.AllowancePayReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0431 Lakkichand Golegaonkar
 * @date 17 Nov 2010
 *
 */
public class AllowancePayReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AllowancePayReportAction.class);
	
	AllowancePayReport allowancePayReport=null;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		allowancePayReport=new AllowancePayReport();
		allowancePayReport.setMenuCode(1111);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return allowancePayReport;
	}
	public String report()
	{try {
		AllowancePayReportModel model = new AllowancePayReportModel();
		model.initiate(context, session);
		logger.info("allowancePayReport   "+allowancePayReport.getMonth());
		model.generateReport(response, allowancePayReport);
		model.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
	
	public String reset()
	{
		allowancePayReport.setEmpId("");
		allowancePayReport.setEmpName("");
		allowancePayReport.setEmpToken("");
		
		allowancePayReport.setDivCode("");
		allowancePayReport.setDivName("");
		
		allowancePayReport.setDeptCode("");
		allowancePayReport.setDeptName("");
		
		allowancePayReport.setBrnCode("");
		allowancePayReport.setBrnName("");
		
		allowancePayReport.setCreditCode("");
		allowancePayReport.setCreditName("");
		
		allowancePayReport.setMonth("");
		allowancePayReport.setYear("");
		
		allowancePayReport.setReportType("");
	
		return "success";
	}
	
	public String f9Branch()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"brnName", "brnCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Branch
	
	/**
	 * Popup window contains list of all departments
	**/
	/**
	 * @return String f9page
	**/
	public String f9Dept()
	{
		try
		{
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptName", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Dept
	
	/**
	 * Popup window contains list of all division
	**/
	/**
	 * @return String f9page
	**/
	public String f9Div()
	{
		try
		{
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			
			if(allowancePayReport.getUserProfileDivision() !=null && allowancePayReport.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+allowancePayReport.getUserProfileDivision() +")"; 
				query+= " ORDER BY UPPER(DIV_NAME) ";
			 
			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Div
	
	// action on select employee
	public String f9Employee() 
	{
		try {
			String query = " SELECT EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS NAME, "
					+ " EMP_ID FROM HRMS_EMP_OFFC ";
			query += getprofileQuery(allowancePayReport);
			query += " AND  EMP_STATUS = 'S'";
			String headers[] = { getMessage("employee.id"),
					getMessage("employee") };
			String headerWidth[] = { "10", "40" };
			String fieldNames[] = { "empToken", "empName", "empId" };
			int columnIndex[] = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public AllowancePayReport getAllowancePayReport() {
		return allowancePayReport;
	}

	public void setAllowancePayReport(AllowancePayReport allowancePayReport) {
		this.allowancePayReport = allowancePayReport;
	}
	
	public String f9Credit() throws Exception 
	{
		
		logger.info("in f9 action");
		
		String query = "SELECT CREDIT_NAME,CREDIT_CODE FROM HRMS_CREDIT_HEAD" +
					   "  ORDER BY CREDIT_CODE";		
		
		
		String[] headers={getMessage("creditHead")};
		
		String[] headerWidth={"100"};
		
		
		String[] fieldNames={"creditName","creditCode"};
		
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
}
