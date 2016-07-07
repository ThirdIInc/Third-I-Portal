package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.ReimbursementConfiguration;
import org.paradyne.model.payroll.TaxInvModel;
import org.paradyne.model.payroll.salary.ReimbursementConfigurationModel;
import org.struts.lib.ParaActionSupport;

public class ReimbursementConfigurationAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ReimbursementConfigurationAction.class);
	
	
	ReimbursementConfiguration reimburseBean = null;

	public ReimbursementConfiguration getReimburseBean() {
		return reimburseBean;
	}

	public void setReimburseBean(ReimbursementConfiguration reimburseBean) {
		this.reimburseBean = reimburseBean;
	}

	@Override
	public void prepare_local() throws Exception {
		reimburseBean = new ReimbursementConfiguration();
		reimburseBean.setMenuCode(1112);
	}

	public Object getModel() {
		
		return reimburseBean;
	}

	
	
	public String input() {
		
		logger.info("######## IN INPUT METHOD ##############");
		
		try {
			ReimbursementConfigurationModel model = new ReimbursementConfigurationModel();
			model.initiate(context, session);
			model.getAccountantsList(reimburseBean, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(1);
		return SUCCESS;
	 }
	
	public String addNew() {
		try {
			reset();
			getNavigationPanel(2);
			return "showData";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String save()throws Exception {
		
		ReimbursementConfigurationModel model = new ReimbursementConfigurationModel();
		model.initiate(context,session);
		boolean result =false;
			if (String.valueOf(reimburseBean.getHiddenEmpId()).equals("")) {
			
				result = model.addAccountDivision(reimburseBean);
				if (result) {
					addActionMessage("Record saved successfully");
				} else {
					addActionMessage("Record could not be saved");
				}
			
		} else {
			update();
		}
		model.getAccountantDtl(reimburseBean, request);
		model.terminate();
		input();
		return SUCCESS;
	}
	
	public String update(){
		boolean result=false;
		try {
			ReimbursementConfigurationModel model = new ReimbursementConfigurationModel();
			model.initiate(context, session);
			
				result = model.modAccountantDetails(reimburseBean);
				if (result) {
					addActionMessage(getMessage("update"));
				} else {
					addActionMessage(getMessage("update.error"));
				}
			model.getAccountantDtl(reimburseBean, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(3);
		reimburseBean.setEnableAll("N");
		return "showData";
	}
	
	public String reset() {
		try {
			reimburseBean.setAccountantEmpToken("");
			reimburseBean.setAccountantEmpId("");
			reimburseBean.setAccountantDivId("");
			reimburseBean.setAccountantEmpName("");
			reimburseBean.setAccountantDivName("");
			reimburseBean.setHiddenDivId("");
			reimburseBean.setHiddenEmpId("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(2);
		return "showData";
	}
	
	public String back() throws Exception {
		getNavigationPanel(1);
		input();
		reimburseBean.setHiddenDivId("");
		reimburseBean.setHiddenEmpId("");
		return SUCCESS;
	}
	
	public String f9Division() {
		try {
			String query = "SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION";
			if (reimburseBean.getUserProfileDivision() != null
					&& reimburseBean.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN ("
						+ reimburseBean.getUserProfileDivision() + ")";
			query += " ORDER BY  DIV_ID ";
			String[] headers = { "Division Id", "Division Name" };
			String[] headerWidth = { "50", "50" };
			String[] fieldNames = {"accountantDivName", "accountantDivId" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
		
	}
	public String f9Accountant() {
		
		try {
			String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID "
					+ " FROM HRMS_EMP_OFFC ";
			query += getprofileQuery(reimburseBean);
			query += " AND EMP_STATUS='S'";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };
			String[] headerWidth = { "30", "70" };
			String[] fieldNames = { "accountantEmpToken", "accountantEmpName", "accountantEmpId" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
		
	}
	public String f9action() {
		
		try {
			
			String query = " SELECT EMP_FNAME||' '||EMP_LNAME, DIV_NAME, ACCOUNTANT_EMP_ID, ACCOUNTANT_DIV_ID, EMP_TOKEN  "
				+ " FROM HRMS_CASH_CONFIG "
				+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID = ACCOUNTANT_EMP_ID) "
				+ " INNER JOIN HRMS_DIVISION ON (DIV_ID = EMP_DIV) ";
			
			String[] headers = {"Accountant Name", "Accountant Division"};
			String[] headerWidth = { "50", "50" };
			String[] fieldNames = {"accountantEmpName","accountantDivName" ,"accountantEmpId", "accountantDivId","accountantEmpToken"}; 
			
			int[] columnIndex = { 0,1,2,3,4};
			String submitFlag = "true";
			String submitToMethod = "ReimbursementConfiguaration_getAccountantRecord.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "f9page";
	}
	
	public String callForEdit() {
		
		try {
			
			ReimbursementConfigurationModel model = new ReimbursementConfigurationModel();
			String empCode = request.getParameter("autoCode");
			String divId = request.getParameter("divCode");
			
			model.initiate(context, session);
			model.callForEdit(reimburseBean, empCode, divId);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(3);
		reimburseBean.setEnableAll("N");
		return "showData";
	}
	
	public String edit() {
		
		try {
			ReimbursementConfigurationModel model = new ReimbursementConfigurationModel();
			model.initiate(context, session);
			model.getAccountantDtl(reimburseBean, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(2);
		return "showData";
	}
	
	public String delete(){
		
		try {
			boolean result ;
			ReimbursementConfigurationModel model = new ReimbursementConfigurationModel();
			model.initiate(context,session);
			result = model.deleteAccountantDivision(reimburseBean);
		} catch (Exception e) {
			// TODO: handle exception
		}
		input();
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String getAccountantRecord()throws Exception
	{			
		try {
			ReimbursementConfigurationModel model = new ReimbursementConfigurationModel();
			model.initiate(context, session);
			model.getAccountantDtl(reimburseBean, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(3);
		reimburseBean.setEnableAll("N");
		return "showData";
	}
	
	public String deleteMultipleRecordsFromList(){
		try {
			boolean result = false;
			ReimbursementConfigurationModel model = new ReimbursementConfigurationModel();
			model.initiate(context, session);
			String[] accntId = request.getParameterValues("hdeleteCode");
			String[] divisionId = request.getParameterValues("hdeleteDivCode");
			
			result = model.deleteCheckedRecords(reimburseBean, accntId, divisionId);
			if (result){
				addActionMessage(getText("delMessage", ""));
			}else{
				addActionMessage(getMessage("del.error"));
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		input();
		return SUCCESS;
	}
	
	
}
