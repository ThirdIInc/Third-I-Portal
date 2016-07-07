package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.FinancialStatementBean;
import org.paradyne.model.admin.master.FinancialStatementModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class FinancialStatementAction extends ParaActionSupport {

	FinancialStatementBean financeBean;
	
	public void prepare_local() throws Exception {
		financeBean = new FinancialStatementBean();
		financeBean.setMenuCode(2252);

	}

	public Object getModel() {
		
		return financeBean;
	}

	/**
	 * @return the financeBean
	 */
	public FinancialStatementBean getFinanceBean() {
		return financeBean;
	}

	/**
	 * @param financeBean the financeBean to set
	 */
	public void setFinanceBean(FinancialStatementBean financeBean) {
		this.financeBean = financeBean;
	}

	public String input(){
		final FinancialStatementModel model = new FinancialStatementModel();
		model.initiate(context, session);
		model.setDetails(financeBean);
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String save(){
		
			try {
				final FinancialStatementModel model = new FinancialStatementModel();
				model.initiate(context, session);
				model.addStatementData(this.financeBean);
				addActionMessage("Record Save Successfully");
				//reset();
			} catch (Exception e) {
				e.printStackTrace();
			}
		input();
			return INPUT;
	}
	
	
	
	public String reset ()
	{
			
		financeBean.setAppCode("");
		financeBean.setBasicCode("");
		financeBean.setBasicName("");
		financeBean.setDaCode("");
		financeBean.setDaName("");
		financeBean.setHraCode("");
		financeBean.setHraName("");
		financeBean.setMatCode("");
		financeBean.setMaternityName("");
		financeBean.setOtCode("");
		financeBean.setOtName("");
		financeBean.setLeaveCode("");
		financeBean.setLeaveName("");
		financeBean.setBasicCode("");
		financeBean.setBonusName("");
		financeBean.setGratuityCode("");
		financeBean.setGratuityName("");
		financeBean.setCompensationCode("");
		financeBean.setCompensationName("");

		financeBean.setProvidentCode("");
		financeBean.setProvidentName("");
		financeBean.setEsiCode("");
		financeBean.setEsiName("");
		financeBean.setTaxCode("");
		financeBean.setTaxName("");
		financeBean.setOtherCode("");
		financeBean.setOtherName("");
		financeBean.setRecCode("");
		financeBean.setRecName("");
		financeBean.setEpfCode("");
		financeBean.setEpfName("");
		financeBean.setEsicCode("");
		financeBean.setEsicName("");
		getNavigationPanel(1);
		return INPUT;
	}
	
	
	
	/**
	 * Method f9basic().
	 * Purpose : Select Basic. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9basic() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"Basic Code",	 this.getMessage("basic_name")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"basicCode", "basicName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * Method f9DA().
	 * Purpose : Select DA. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9DA() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"DA Code",	 this.getMessage("da_name")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"daCode", "daName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * Method f9HRA().
	 * Purpose : Select HRA. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9HRA() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"HRA Code",	 this.getMessage("hra_name")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"hraCode", "hraName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	/**
	 * Method f9maternity().
	 * Purpose : Select Maternity. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9maternity() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("maternity_name")};
		final String[] headerWidth = {"30", "30"};
		final String[] fieldNames = {"matCode", "maternityName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * Method f9overTime().
	 * Purpose : Select Over Time. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9overTime() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("over_time")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"otCode", "otName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * Method f9leaveEncash().
	 * Purpose : Select Leave Encashment. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9leaveEncash() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("leave_encashment")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"leaveCode", "leaveName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * Method f9bonus().
	 * Purpose : Select Bonus. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9bonus() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("bonus")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"bonusCode", "bonusName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	/**
	 * Method f9gratuity().
	 * Purpose : Select Gratuity. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9gratuity() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("gratuity")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"gratuityCode", "gratuityName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * Method f9workCompensation().
	 * Purpose : Select Compensation. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9workCompensation() throws Exception {
		String query = " SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("workmen_compensation")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"compensationCode", "compensationName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	/**
	 * Method f9providentFund().
	 * Purpose : Select Provident Fund. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9providentFund() throws Exception {
		String query = " SELECT DEBIT_CODE, DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("provident_fund")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"providentCode", "providentName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	/**
	 * Method f9esi().
	 * Purpose : Select ESI. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9esi() throws Exception {
		String query = " SELECT DEBIT_CODE, DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("esi_name")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"esiCode", "esiName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	/**
	 * Method f9tax().
	 * Purpose : Select TAX. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9tax() throws Exception {
		String query = " SELECT DEBIT_CODE, DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("tax_per")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"taxCode", "taxName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	/**
	 * Method f9other().
	 * Purpose : Select Other. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9other() throws Exception {
		String query = " SELECT DEBIT_CODE, DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("other")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"otherCode", "otherName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	/**
	 * Method f9recovery().
	 * Purpose : Select Recovery. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9recovery() throws Exception {
		String query = " SELECT DEBIT_CODE, DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("recovery")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"recCode", "recName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * Method f9epf().
	 * Purpose : Select EPF. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9epf() throws Exception {
		String query = " SELECT DEBIT_CODE, DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("emp_contribution_epf")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"epfCode", "epfName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	/**
	 * Method f9esic().
	 * Purpose : Select ESIC. 
	 * @return String.
	 * @throws Exception - Exception.
	 */
	public String f9esic() throws Exception {
		String query = " SELECT DEBIT_CODE, DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE ";
		
		final String[] headers = {"Code",	 this.getMessage("emp_contribution_esic")};
		final String[] headerWidth = {"10", "30"};
		final String[] fieldNames = {"esicCode", "esicName"};
		final int[] columnIndex = {0 , 1};
		final String submitFlag = "false";
		final String submitToMethod = "";
		this.setF9Window(query , headers , headerWidth , fieldNames , columnIndex , submitFlag , submitToMethod);
		return "f9page";
	}
	
	
}
