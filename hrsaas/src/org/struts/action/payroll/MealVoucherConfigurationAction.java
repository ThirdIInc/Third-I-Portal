package org.struts.action.payroll;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.master.QualificationMaster;
import org.paradyne.bean.payroll.MealVoucherConfiguration;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.admin.master.LeaveMasterModel;
import org.paradyne.model.admin.master.TitleMasterModel;
import org.paradyne.model.payroll.MealVoucherConfigurationModel;

public class MealVoucherConfigurationAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MealVoucherConfigurationAction.class);

	MealVoucherConfiguration mealVoucher;

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		mealVoucher = new MealVoucherConfiguration();
		mealVoucher.setMenuCode(2052);
	 
	}

	public String input() throws Exception {
		try {
			MealVoucherConfigurationModel model = new MealVoucherConfigurationModel();
			model.initiate(context, session);
			model.getSavedRecord(mealVoucher);
			model.terminate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	}

	public String addNew() {
		try {
			
			mealVoucher.setMealvoucherconfID("");
			return "success";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String cancel() {
		try {
			reset();
			return input();
		} catch (Exception e) {
			logger.error("Exception in cancel in action:" + e);
			return null;
		}
	}

	public String calforedit() throws Exception {
		
		MealVoucherConfigurationModel model = new MealVoucherConfigurationModel();
		model.initiate(context, session);
		model.getMealvoucherConfDoubleclick(mealVoucher);
		//model.getRecords(mealVoucher,request);
		
		mealVoucher.setEnableAll("N");
		model.terminate();
		return "success";
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return mealVoucher;
	}

	public MealVoucherConfiguration getMealVoucher() {
		return mealVoucher;
	}

	public void setMealVoucher(MealVoucherConfiguration mealVoucher) {
		this.mealVoucher = mealVoucher;
	}

	public String reset() {
		try {

			mealVoucher.setCreditHead("");
			mealVoucher.setCoupenCode("");
			mealVoucher.setCoupenHead("");
			mealVoucher.setDebitCode("");
			mealVoucher.setDebitHead("");
			mealVoucher.setFreqencyOfChange("-1");
			mealVoucher.setCreditCode("");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String delete() throws Exception {
		MealVoucherConfigurationModel model = new MealVoucherConfigurationModel();
		model.initiate(context, session);

		boolean result = model.deleteMealvoucherConf(mealVoucher);
		if (result) {
			addActionMessage(getText("Record deleted successfully!"));
			reset();
			callPage();
		} else {
			addActionMessage(getText("This record is referenced in some other resources.\nso can not be deleted!"));
			callPage();
		}
		
		model.terminate();

		return "success";
	}

	public String callPage() throws Exception {
		try {
			input();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String save() {
		try {

			MealVoucherConfigurationModel model = new MealVoucherConfigurationModel();
			model.initiate(context, session);
			boolean result;
			if (mealVoucher.getMealvoucherconfID().equals("")) {
				result = model.save(mealVoucher);

				if (result) {
					addActionMessage(getMessage("save"));
				} else {
					addActionMessage(getMessage("save.error"));
				}
			} else if (!mealVoucher.getMealvoucherconfID().equals("")) {
				result = model.update(mealVoucher);
				if (result) {
					addActionMessage(getText("modMessage", ""));
				}// end of if

			}
			//model.setMealVoucherDtl(mealVoucher);
			mealVoucher.setEnableAll("Y");
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING LEAVE TYPE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9credit() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD "
				+ " ORDER BY CREDIT_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("credit.code"),
				getMessage("credit.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "creditCode", "creditHead" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9ltypeaction

	/**
	 * 
	 * @return
	 * @throws Exception
	 */

	public String f9debit() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD "
				+ " ORDER BY DEBIT_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("debit.code"),
				getMessage("debit.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "debitCode", "debitHead" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING LEAVE TYPE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9creditcoupen() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD "
				+ " ORDER BY CREDIT_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("credit.code"),
				getMessage("credit.name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "coupenCode", "coupenHead" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9ltypeaction

}
