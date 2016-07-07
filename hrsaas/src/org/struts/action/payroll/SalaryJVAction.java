/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.SalaryJV;
import org.paradyne.model.payroll.SalaryJVModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class SalaryJVAction extends ParaActionSupport {

	SalaryJV salaryJV = null;
	
	public void prepare_local() throws Exception {
		salaryJV = new SalaryJV();
		salaryJV.setMenuCode(887);
	}

	public Object getModel() {
		return salaryJV;
	}
	
	public void generateReport()
	{
		SalaryJVModel model = new SalaryJVModel();
		model.initiate(context, session);
		model.generateReport(salaryJV,request,response);
		model.terminate();
	}
	
	public String f9Div()
	{
		try
		{
			String query = " SELECT  DISTINCT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
			
			if(salaryJV.getUserProfileDivision() !=null && salaryJV.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+salaryJV.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";

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
			return "";
		} //end of try-catch block
	} //end of f9Div
	
	
	public String f9Debit()
	{
		try
		{
			String query = " SELECT  DISTINCT DEBIT_ABBR,DEBIT_NAME,DEBIT_CODE FROM HRMS_DEBIT_HEAD "
				+" WHERE HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y' AND HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M'";	

			String[] headers = {getMessage("debitAbbr"),getMessage("debithead")};

			String[] headerWidth = {"50","50"};

			String[] fieldNames = {"debitAbbr", "debitHead","debitCode"};

			int[] columnIndex = {0, 1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Div
	
	public String f9Credit()
	{
		try
		{
			String query = " SELECT  DISTINCT CREDIT_ABBR,CREDIT_NAME,CREDIT_CODE FROM HRMS_CREDIT_HEAD "
						+" WHERE HRMS_CREDIT_HEAD.CREDIT_PAYFLAG='Y' AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M'";	

			String[] headers = {getMessage("creditAbbr"),getMessage("creditHead")};

			String[] headerWidth = {"50","50"};

			String[] fieldNames = {"creditAbbr", "creditHead","creditCode"};

			int[] columnIndex = {0, 1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Div
	
}
