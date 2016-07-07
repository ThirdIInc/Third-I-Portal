package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.BankingSummaryReport;
import org.paradyne.model.payroll.salary.BankingSummaryReportModel;
import org.struts.lib.ParaActionSupport;

public class BankingSummaryReportAction extends ParaActionSupport {

	BankingSummaryReport bean;
	public void prepare_local() throws Exception {
		bean=new BankingSummaryReport();
		bean.setMenuCode(2263);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public BankingSummaryReport getBean() {
		return bean;
	}

	public void setBean(BankingSummaryReport bean) {
		this.bean = bean;
	}
	public String report() throws Exception {
		BankingSummaryReportModel model=new BankingSummaryReportModel();
		model.initiate(context, session);
		model.report(request,response,bean);
		model.terminate();
		return null;
	}
	
	public String reset() throws Exception {
		bean.setMonth("0");
		bean.setYear("");
		bean.setDivisionId("");
		bean.setDivisionName("");
		return SUCCESS;
	}
	
	/**
	 * Opens a popup window containing a list of all division
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY DIV_ID ";
			String[] headers = {getMessage("division")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"divisionName", "divisionId"};
			int[] columnIndex = {0, 1};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);			
			return "f9page";
		} catch (Exception e) {			
			return "";
		}
	} //end of f9Div
}
