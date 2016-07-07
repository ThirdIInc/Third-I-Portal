package org.struts.action.payroll;

import org.struts.lib.ParaActionSupport;

/*public class LTCConfigAction extends ParaActionSupport {

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}*/


import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.LTCConfigBean;

import org.paradyne.model.payroll.LTCConfigModel;
/*
 * Date: 30/11/2010
 * Ganesh
 */

public class LTCConfigAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AllowancePayAction.class);

	LTCConfigBean configBean;

	public void prepare_local() throws Exception {
		try {
			// TODO Auto-generated method stub
			configBean = new LTCConfigBean();
			configBean.setMenuCode(1116);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return configBean;
	}

	

	public LTCConfigBean getConfigBean() {
		return configBean;
	}

	public void setConfigBean(LTCConfigBean configBean) {
		this.configBean = configBean;
	}
	
	
	
	 public String input() {
		try {
			showSetting();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	} 
	public String showSetting() throws Exception{
		try {
			LTCConfigModel model = new LTCConfigModel();
			model.initiate(context, session);
			model.showSetting(configBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		  
	 }
	
	public String reset() {
		try {

			configBean.setCreditCode("");
			configBean.setCreditName("");
			configBean.setAttnDays("");
			configBean.setLeaveDays("");
			configBean.setTrvlDays("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String save() {
		try {
			LTCConfigModel model = new LTCConfigModel();
			model.initiate(context, session);
			boolean result = model.saveValues(configBean);
			if (result) {
				addActionMessage("Record saved successfully");
			} else {
				addActionMessage("Record could not be save properly");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * THIS METHOD IS USED FOR SELECTING LTC HEAD
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
				+ " WHERE CREDIT_PAYFLAG = 'Y' AND CREDIT_PERIODICITY = 'M' ORDER BY CREDIT_CODE ";
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

	

}
