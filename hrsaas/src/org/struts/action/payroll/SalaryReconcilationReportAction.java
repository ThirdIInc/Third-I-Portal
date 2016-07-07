/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.SalaryReconcilation;
import org.paradyne.model.payroll.SalaryReconcilationReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Pankaj_Jain
 *
 */
public class SalaryReconcilationReportAction extends ParaActionSupport {

	
	SalaryReconcilation salRecon = null;
	public void prepare_local() throws Exception {
		salRecon  = new SalaryReconcilation();
		salRecon.setMenuCode(893);
		}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return salRecon;
	}
	
	public String f9Div()
	{
		try
		{
			String query = " SELECT  DISTINCT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
			
			if(salRecon.getUserProfileDivision() !=null && salRecon.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+salRecon.getUserProfileDivision() +")"; 
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

	
	public void generateReport()
	{
		SalaryReconcilationReportModel model = new SalaryReconcilationReportModel();
		model.initiate(context, session);
		model.genReport(salRecon, response, "", "");
		model.terminate();
	}

}
