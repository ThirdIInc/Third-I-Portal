package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.MonthlyFinancialStatement;
import org.paradyne.model.payroll.salary.MonthlyFinancialStatementModel;
import org.struts.lib.ParaActionSupport;

public class MonthlyFinancialStatementAction extends ParaActionSupport {

	MonthlyFinancialStatement bean;
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean=new MonthlyFinancialStatement();
		bean.setMenuCode(2251);
	}

	public MonthlyFinancialStatement getBean() {
		return bean;
	}

	public void setBean(MonthlyFinancialStatement bean) {
		this.bean = bean;
	}

	/**
	 * Generates a report
	 */
	public void report() {
		try {
			MonthlyFinancialStatementModel model = new MonthlyFinancialStatementModel();
			model.initiate(context, session);
			model.report(bean, response);
			model.terminate();			
		} catch(Exception e) {
			e.printStackTrace();
		} // end of try-catch block
	}
	
	public String reset(){
		bean.setMonth("");
		bean.setYear("");
		bean.setDivisionId("");
		bean.setDivisionName("");
		return SUCCESS;
	}
	
	
	
	/**
	 * Popup window contains list of all division
	 * 
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
		} catch(Exception e) {
			return "";
		}
	} // end of f9Div
	
}
