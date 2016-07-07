package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.ExpensesCategory;
import org.paradyne.model.TravelManagement.Master.ExpensesCategoryModel;
import org.struts.lib.ParaActionSupport;

public class ExpensesCategoryAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ExpensesCategoryAction.class);
	ExpensesCategory expenses;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		expenses = new ExpensesCategory();
		expenses.setMenuCode(773);

	}

	public Object getModel() {

		return expenses;
	}

	public ExpensesCategory getExpenses() {
		return expenses;
	}

	public void setExpenses(ExpensesCategory expenses) {
		this.expenses = expenses;
	}

	/*public void prepare_withLoginProfileDetails() throws Exception {
		ExpensesCategoryModel model = new ExpensesCategoryModel();		
		model.initiate(context, session);
		model.getData(expenses, request);
		model.terminate();
	}*/

	public String input() throws Exception {
		//Default Method with default modeCode(1)		
		getNavigationPanel(1);
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);		
		model.getData(expenses, request);
		expenses.setOnLoadFlag(false);
		expenses.setEditModeFlag(true);

		expenses.setPanelFlag("1");
		expenses.setRetrnFlag("view");

		model.terminate();
		return "view";
	}

	public String addNew() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		model.getData(expenses, request);
		expenses.setLoadFlag(true);
		reset();
		//	expenses.setFlag(true);

		expenses.setPanelFlag("2");
		expenses.setRetrnFlag("success");

		model.terminate();
		return "success";

	}

	public String edit() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		model.getExpenseSearch(expenses);
		model.getData(expenses, request);
		expenses.setOnLoadFlag(true);
		expenses.setCancelFlg("true");
		expenses.setPageFlag("true");

		expenses.setPanelFlag("2");
		expenses.setRetrnFlag("success");

		model.terminate();
		return "success";

	}

	public String reset() throws Exception {
		expenses.setExpenseName("");
		expenses.setExpenseUnit("");
		expenses.setExpenseId("");
		expenses.setStatus("");
		expenses.setDescription("");

		return SUCCESS;
	}

	public String cancelFirst() throws Exception {
		logger.info("=========bean.getCancelFlg()========="
				+ expenses.isOnLoadFlag());
		String str;
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		if (expenses.getCancelFlg().equals("true")) {
			getNavigationPanel(3);
			model.getExpense(expenses);
			model.getData(expenses, request);
			expenses.setOnLoadFlag(true);
			expenses.setPanelFlag("3");
			expenses.setRetrnFlag("view");
			str = "view";
		} else {
			getNavigationPanel(1);
			model.getData(expenses, request);
			reset();
			expenses.setOnLoadFlag(false);
			expenses.setPanelFlag("1");
			expenses.setRetrnFlag("view");

			str = "view";
		}
		model.terminate();
		return str;
	}
	
	
	

	/*	
	 public String cancelSec() throws Exception{
	 getNavigationPanel(1);
	 ExpensesCategoryModel model = new ExpensesCategoryModel();	
	 model.initiate(context, session);
	 callPage();
	 expenses.setOnLoadFlag(true);
	 expenses.setSaveFlag(true);
	 model.terminate();
	 return "success";
	
	 }
	 */

	public String cancelSec() throws Exception {
		getNavigationPanel(1);
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		model.getData(expenses, request);
		reset();
		expenses.setOnLoadFlag(false);
		expenses.setPanelFlag("1");
		expenses.setRetrnFlag("view");

		model.terminate();
		return "view";
	}

	/*
	
	
	
	
	public String cancelThrd() throws Exception{
		logger.info("Inside Cancel Third");
			getNavigationPanel(3);
			ExpensesCategoryModel model = new ExpensesCategoryModel();	
			model.initiate(context, session);			
			callPage();
			model.getExpense(expenses);
			expenses.setSaveFlag(true);
			expenses.setModFlag(false);
			model.terminate();
			return "success";
			
		}
		
	 */

	/*public String cancelFrth() throws Exception{
		logger.info("Inside Cancel Fourth");
		getNavigationPanel(1);
		ExpensesCategoryModel model = new ExpensesCategoryModel();		
		model.initiate(context, session);
		callPage();
		reset();
		expenses.setOnLoadFlag(true);
		model.terminate();
		return "success";
		
	}*/

	/**
	 * following function is called when add new record is clicked in the jsp page
	 * @return
	 */
	public String save() throws Exception {
		//Default Method with Edit modeCode(3)
		getNavigationPanel(3);
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		boolean result;
		String str = "";
		expenses.setOnLoadFlag(true);
		model.initiate(context, session);

		expenses.setPanelFlag("3");

		if (expenses.getExpenseId().equals("")) {
			result = model.addExpenseType(expenses);
			if (result) {
				addActionMessage(getMessage("save"));

				expenses.setPageFlag("true");
				model.getData(expenses, request);
				expenses.setRetrnFlag("view");
				str = "view";

			} else {
				addActionMessage(getMessage("duplicate"));
				expenses.setCancelFlg("false");
				getNavigationPanel(2);
				expenses.setPanelFlag("2");
				expenses.setRetrnFlag("success");
				str = "success";
			}

		} else {
			logger.info("***********==Inside Update=========");
			result = model.modExpenseType(expenses);
			if (result) {
				addActionMessage(getMessage("update"));
				expenses.setPageFlag("true");
				expenses.setRetrnFlag("view");
				str = "view";
				model.getData(expenses, request);
			} else {

				addActionMessage(getMessage("duplicate"));
				expenses.setCancelFlg("false");
				getNavigationPanel(2);
				expenses.setPanelFlag("2");
				expenses.setRetrnFlag("success");
				str = "success";

			}
		}
		model.getData(expenses, request);
		expenses.setEditModeFlag(false);
		expenses.setOnLoadFlag(true);
		model.terminate();
		return str;

	}

	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		boolean result = model.deleteExpenseType(expenses);
		if (result) {
			logger.info("DELETING........");
			addActionMessage(getMessage("delete"));
			expenses.setOnLoadFlag(false);
			expenses.setPanelFlag("1");
			expenses.setRetrnFlag("view");
			reset();
		} else {
			addActionMessage(getMessage("del.error"));
		}
		input();
		model.terminate();
		expenses.setPanelFlag("1");
		expenses.setRetrnFlag("view");
		
		return "view";
	}

	
	
	
	
	
	
	
	
	
	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String getRecord() throws Exception {

		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		model.getExpense(expenses);
		expenses.setSaveFlag(true);
		model.getData(expenses, request);
		model.terminate();
		expenses.setRetrnFlag("success");
		return "success";
	}

	public String callPage1() throws Exception {
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		expenses.setPageFlag("true");
		getNavigationPanel(Integer.parseInt(expenses.getPanelFlag()));
		model.getData(expenses, request);
		model.terminate();
		return expenses.getRetrnFlag();
	}

	/*
	public String callPage2() throws Exception {
		getNavigationPanel(1);
		ExpensesCategoryModel model = new ExpensesCategoryModel();	
		model.initiate(context, session);
		model.getData(expenses,request);
		expenses.setPageFlag(true);
		model.terminate();
		
		
		return "success";
	}
	
	 */

	public String callPage() throws Exception {
		logger.info("INSIDE callPage");
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		/*	
			if (expenses.getPageFlag().equals("true")) {
				getNavigationPanel(2);
				model.getData(expenses, request);
			} else {
				getNavigationPanel(1);
				model.getData(expenses, request);
			}
			
		 */

		getNavigationPanel(Integer.parseInt(expenses.getPanelFlag()));
		model.getData(expenses, request);
		model.terminate();
		return expenses.getRetrnFlag();
	}

	public String callPageView() throws Exception {

		logger.info("i am in callPageView--------");

		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		/*if (expenses.getPageFlag().equals("true")) {		
			getNavigationPanel(3);
			model.getData(expenses, request);

		} else {
			getNavigationPanel(1);
			model.getData(expenses, request);
		}*/
		getNavigationPanel(Integer.parseInt(expenses.getPanelFlag()));
		model.getData(expenses, request);
		model.terminate();
		return expenses.getRetrnFlag();
	}

	public String calforedit() throws Exception {
		logger.info("----------INside calforedit()---------");
		getNavigationPanel(2);
		expenses.setCancelFlg("false");
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		model.getExpenseTypeOnDoubleClick(expenses);
		//model.getExpense(expenses);
		model.getData(expenses, request);
		expenses.setOnLoadFlag(true);
		expenses.setDblFlag(true);
		expenses.setModFlag(false);
		expenses.setPageFlag("true");

		expenses.setPanelFlag("2");
		expenses.setRetrnFlag("success");

		model.terminate();
		return "success";

	}

	public String delete1() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		boolean result;
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		//callPage2();
		String[] code = request.getParameterValues("hdeleteCode");
		result = model.delChkdRec(expenses, code);

		if (result) {

			addActionMessage(getMessage("delete"));
			model.getData(expenses, request);
			expenses.setOnLoadFlag(false);
			expenses.setPageFlag("true");
			reset();
		}

		else
			addActionMessage(getMessage("multiple.del.error"));
		model.getData(expenses, request);

		expenses.setPanelFlag("1");
		expenses.setRetrnFlag("view");

		return "view";
	}

	public String report() {
		getNavigationPanel(5);
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		String[] label = { "Sr.No", getMessage("expense.type"),
				getMessage("expense.unit"), getMessage("expense.desc"),
				getMessage("expense.sts") };
		model.generateReport(expenses, response, label);
		model.terminate();

		return null;
	}

	public String f9Action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String query = "SELECT  NVL(EXP_CATEGORY_NAME,' '),"
				+ " DECODE(EXP_CATEGORY_UNIT,'D','Per Day','T','Per Travel') ,"
				+ " DECODE(EXP_CATEGORY_STATUS,'A','Active','D','Deactive') , "
				+ " EXP_CATEGORY_ID  FROM HRMS_TMS_EXP_CATEGORY	 ORDER BY  EXP_CATEGORY_ID";

		String[] headers = { getMessage("expense.type"),
				getMessage("expense.unit"), getMessage("expense.sts") };

		String[] headerWidth = { "15", "15", "10" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "expenseName", "expenseUnit", "status",
				"expenseId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ExpensesCategory_details.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String details() {
		logger.info("I am in details method-----");
		getNavigationPanel(3);
		expenses.setOnLoadFlag(true);
		ExpensesCategoryModel model = new ExpensesCategoryModel();
		model.initiate(context, session);
		model.getDesc(expenses);
		//model.getExpense(expenses);
		model.getData(expenses, request);
		model.terminate();

		expenses.setPanelFlag("3");
		expenses.setRetrnFlag("view");
		return "view";
	}

	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String search() {
		getNavigationPanel(3);
		return SUCCESS;
	}

}
