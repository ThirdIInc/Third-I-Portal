package org.struts.action.payroll;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.paradyne.bean.payroll.AllowancePay;
import org.paradyne.model.payroll.AllowancePayModel;
import org.struts.lib.ParaActionSupport;

public class AllowancePayAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AllowancePayAction.class);

	AllowancePay allowancePay;

	public void prepare_local() throws Exception {
		try {
			// TODO Auto-generated method stub
			allowancePay = new AllowancePay();
			allowancePay.setMenuCode(1110);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return allowancePay;
	}

	public AllowancePay getAllowancePay() {
		return allowancePay;
	}

	public void setAllowancePay(AllowancePay allowancePay) {
		this.allowancePay = allowancePay;
	}

	public String input() {
		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			allowancePay.setProcessingDate(sysDate);
			AllowancePayModel model = new AllowancePayModel();
			model.initiate(context, session);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String deleteData() {
		try {
			AllowancePayModel model = new AllowancePayModel();
			model.initiate(context, session);
			boolean result = model.delete(allowancePay);
			if (result) {
				addActionMessage("Record deleted successfully");
				reset();
			} else {
				addActionMessage("Record can't be deleted ");

			}
			model.displayIttValues(allowancePay,allowancePay.getHiddenMonthEdit(),allowancePay.getHiddenYearEdit());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String process() {
		try {
			AllowancePayModel model = new AllowancePayModel();
			model.initiate(context, session);
			boolean result = model.displayIttValues(allowancePay,allowancePay.getMonth(),allowancePay.getYear());
			if (result) {
				addActionMessage("Record processed successfully");
				reset();
			} else {
				addActionMessage("There is no data to display");

			}
		//	model.displayIttValues(allowancePay,allowancePay.getHiddenMonthEdit(),allowancePay.getHiddenYearEdit());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addToList() {

		try {

			AllowancePayModel model = new AllowancePayModel();
			model.initiate(context, session);
			boolean result = model.addToList(allowancePay);
			if (result) {
				addActionMessage("Record added successfully");
				reset();
			} else {
				addActionMessage("Record can't be added ");

			}
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String reset() {
		try {

			allowancePay.setCreditCode("");
			allowancePay.setCreditName("");
			allowancePay.setEmployeeToken("");
			allowancePay.setEmployeeName("");
			allowancePay.setEmployeeCode("");
			allowancePay.setCreditAmount("");
			allowancePay.setMonth("");
			allowancePay.setYear("");
			allowancePay.setRemarks("");
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

		String[] fieldNames = { "creditCode", "creditName" };

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

	public String f9employee() throws Exception {
		// to remove an already selected employee from the search list
		/*String[] empCode = request.getParameterValues("employeeId");
		String str = "0";
		if (empCode != null) {
			for (int i = 0; i < empCode.length; i++) {// loop x
				str += "," + empCode[i];
			}// end loop x
		}// end if
*/		
		
		String query = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID  "
				+ " FROM HRMS_EMP_OFFC ";
		query += " WHERE EMP_STATUS='S' ";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "employeeToken", "employeeName", "employeeCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "LeaveEncashmentProcess_chk.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9employee method

}
