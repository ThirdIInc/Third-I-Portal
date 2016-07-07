package org.struts.action.reimbursement;

import org.paradyne.bean.reimbursement.ReimbConfigurationBean;
import org.paradyne.model.TravelManagement.Master.TravelModeListMasterModel;
import org.paradyne.model.reimbursement.ReimbConfigurationModel;
import org.struts.action.TravelManagement.Master.TravelModeListMasterAction;
import org.struts.lib.ParaActionSupport;

public class ReimbConfigurationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(TravelModeListMasterAction.class);
	
	ReimbConfigurationBean bean;
	public void prepare_local() throws Exception {
		bean=new ReimbConfigurationBean();
		bean.setMenuCode(1128);
		getNavigationPanel(1);
	}

	public ReimbConfigurationBean getBean() {
		return bean;
	}

	public void setBean(ReimbConfigurationBean bean) {
		this.bean = bean;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	
	public String input(){
		try{
			ReimbConfigurationModel model=new ReimbConfigurationModel();
			model.initiate(context, session);
			model.getList(bean, request);
			model.terminate();
			getNavigationPanel(1);
		
		}catch(Exception e){
			
		}
		return INPUT;
	}
	
	public String reset() {
		bean.setIdHead("");
		bean.setTypeHead("");
		bean.setEmpIdAdmin("");
		bean.setEmpTokenAdmin("");
		bean.setEmpIdAccountant("");
		bean.setEmpTokenAccountant("");
		bean.setReimbHead("");
		bean.setReimbManagerApproval("");
		bean.setReimbAdminApproval("");
		bean.setReimbAdmin("");
		bean.setReimbAccountant("");
		bean.setPaymentMode("");
		bean.setPaymentModeCash("");
		bean.setPaymentModeCheque("");
		bean.setPaymentModeSalary("");
		bean.setTransferAccount("");
		bean.setReimbursementPeriod("");
		bean.setCarrierForward("");
		bean.setCarrierPercentage("");
		bean.setAdvanceAllowed("");
		bean.setStartmonth("");
		bean.setEndmonth("");
		bean.setYearFrom("");
		bean.setYearTo("");
		
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String addNew() {
		reset();
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String f9ReimbursementHead() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//String query = " SELECT  CREDIT_CODE,CREDIT_NAME,CREDIT_HEAD_TYPE FROM HRMS_CREDIT_HEAD WHERE CREDIT_HEAD_TYPE='R' OR CREDIT_HEAD_TYPE='V'";
		
		String query = "SELECT  CREDIT_NAME, CREDIT_CODE,CREDIT_HEAD_TYPE "
		+" FROM HRMS_CREDIT_HEAD WHERE (CREDIT_HEAD_TYPE='R' OR CREDIT_HEAD_TYPE='V') AND CREDIT_CODE"
		+" NOT IN(SELECT REIMB_CREDIT_HEAD	FROM HRMS_REIMB_CONFIG	)";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Credit Name" };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "reimbHead","IdHead","TypeHead"  };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	public String searchAdmin() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN,EMP_FNAME|| ' ' ||EMP_MNAME|| ' ' ||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Token", "Employee Name" };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "EmpTokenAdmin", "reimbAdmin", "EmpIdAdmin"  };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	public String searchAccountant() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT EMP_TOKEN,EMP_FNAME|| ' ' ||EMP_MNAME|| ' ' ||EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Token", "Employee Name" };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "EmpTokenAccountant", "reimbAccountant", "EmpIdAccountant"  };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String save() {
		ReimbConfigurationModel model=new ReimbConfigurationModel();
		model.initiate(context, session);
		boolean result;
		boolean value=model.check(bean);

		if (!value) {
			result = model.save(bean);
			if (result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage("Type can not be added");
			}
		} else {
			result = model.update(bean);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			
			}else {
				addActionMessage("Type can not be added");
			}
		}
		getDetails();
			model.terminate();
		getNavigationPanel(3);
		return SUCCESS;
	}
	public String edit() {

		try {
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
	}
	public String searchConfiguration() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT CREDIT_NAME, DECODE(IS_MANAGER_APPROVAL,'Y','YES','N','NO'), DECODE(IS_ADMIN_APPROVAL,'Y','YES','N','NO'),H1.EMP_FNAME|| ' ' ||H1.EMP_MNAME|| ' ' ||H1.EMP_LNAME, H2.EMP_FNAME|| ' ' ||H2.EMP_MNAME|| ' ' ||H2.EMP_LNAME,REIMB_CREDIT_HEAD FROM HRMS_REIMB_CONFIG"
			+ " LEFT JOIN  HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD)"
			+ "  LEFT JOIN HRMS_EMP_OFFC H1  ON(H1.EMP_ID=HRMS_REIMB_CONFIG.REIMB_ADMIN)"
			+ "  LEFT JOIN HRMS_EMP_OFFC  H2  ON(H2.EMP_ID=HRMS_REIMB_CONFIG.REIMB_ACCOUNTANT)"
			+ " ORDER BY REIMB_CREDIT_HEAD";
	

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Reimbursement Head", "Manager Approval", "Admin Approval" ,"Admin", "Accountant"};

		String[] headerWidth = { "20", "10", "10","30", "30"  };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "reimbHead", "reimbManagerApproval", "reimbAdminApproval","reimbAdmin", "reimbAccountant","IdHead" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2,3,4 ,5};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReimbConfiguration_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String getDetails() {
		ReimbConfigurationModel model=new ReimbConfigurationModel();
		model.initiate(context, session);
		model.getDetails(bean);
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}
	public String dblClickItt() {
		ReimbConfigurationModel model=new ReimbConfigurationModel();
		model.initiate(context, session);
		model.dblClickItt(bean);
		model.terminate();

		getNavigationPanel(3);
		bean.setEnableAll("N");
		return SUCCESS;
	}
	public String delete() {
		boolean result;

		ReimbConfigurationModel model=new ReimbConfigurationModel();
		model.initiate(context, session);
		result = model.delete(bean, request);
		if (result) {
			addActionMessage("Record Deleted successfully.");

		}

		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}
	public String back() {

		ReimbConfigurationModel model=new ReimbConfigurationModel();
		model.initiate(context, session);
		model.getList(bean, request);
		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}
	
	public String deleteCheck(){
		
		String code[] = request.getParameterValues("hidCode");
		String ReimbCode[] = request.getParameterValues("ittReimbCode");
		// for (int j = 0; j < code.length; j++) {
		// System.out.println("......"+code[j]);
		// }
		// for (int i = 0; i < code.length; i++) {
		// System.out.println("......"+empId[i]);
		// }
		ReimbConfigurationModel model=new ReimbConfigurationModel();
		model.initiate(context, session);
		boolean flag = model.deleteCheck(bean, code, ReimbCode, request);
		if (flag) {
			addActionMessage("Record deleted successfully");
		} else {
			addActionMessage("Please Select record");
		}
		model.terminate();
		getNavigationPanel(1);
		
		return INPUT;
	}
	public String callPage() throws Exception {
		try {
			input();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
}



