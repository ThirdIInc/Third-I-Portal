package org.struts.action.LMS;

import org.paradyne.bean.LMS.HouseMasterBean;
import org.paradyne.model.LMS.HouseMasterModel;

import org.struts.lib.ParaActionSupport;

public class HouseMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ColonyMasterAction.class);
	HouseMasterBean bean;

	public void prepare_local() throws Exception {
		bean = new HouseMasterBean();

		bean.setMenuCode(1147);
		getNavigationPanel(1);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		ColonyMasterAction.logger = logger;
	}

	public HouseMasterBean getBean() {
		return bean;
	}

	public void setBean(HouseMasterBean bean) {
		this.bean = bean;
	}

	public String input() throws Exception {

		try {
			HouseMasterModel model = new HouseMasterModel();
			model.initiate(context, session);
			model.getList(bean, request);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String addNew() {
		reset();
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String back() {

		HouseMasterModel model = new HouseMasterModel();
		model.initiate(context, session);
		model.getList(bean, request);
		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}

	public String deleteCheck() {
		String code[] = request.getParameterValues("hidCode");
		String houseId[] = request.getParameterValues("ittHouseId");
		
		HouseMasterModel model = new HouseMasterModel();
		model.initiate(context, session);
		boolean flag = model.deleteCheck(bean, code, houseId, request);
		if (flag) {
			addActionMessage("Record deleted successfully");
		} else {
			addActionMessage("Please Select record");
		}
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}

	public String save() {
		HouseMasterModel model = new HouseMasterModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getHouseId().equals("")) {
			result = model.save(bean);

			if (result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage("duplicate record found");
			}
		} else {
			result = model.update(bean);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				addActionMessage("duplicate record found");
			}// end of else
		}
		getDetails();
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}

	public String reset() {
		bean.setHouseNo("");
		bean.setColonyId("");
		bean.setHouseId("");
		bean.setHouseAddress("");
		bean.setNoFamily("");
		bean.setNoIndividual("");
		bean.setColonyName("");
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String searchHouse() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT  HOUSE_NO,COLONY_NAME, HOUSE_ADDRESS,LMS_HOUSE_ID FROM LMS_HOUSE INNER JOIN LMS_COLONY ON(LMS_HOUSE.COLONY_ID=LMS_COLONY.COLONY_ID)  ORDER BY  LMS_HOUSE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "House No", "Colony Name", "House Address" };

		String[] headerWidth = { "30", "30", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "houseNo", "colonyName", "houseAddress",
				"houseId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1, 2, 3 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "HouseMaster_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9Colony() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT  COLONY_ID,COLONY_NAME FROM LMS_COLONY  ORDER BY COLONY_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Colony Id", "Colony Name" };

		String[] headerWidth = { "30", "70" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "colonyId", "colonyName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1 };

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

	public String delete() {
		boolean result;
		HouseMasterModel model = new HouseMasterModel();
		model.initiate(context, session);
		result = model.delete(bean, request);
		if (result) {
			addActionMessage("Record Deleted successfully.");

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

	public String getDetails() {
		HouseMasterModel model = new HouseMasterModel();
		model.initiate(context, session);
		model.getDetails(bean);
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}

	public String dblClickItt() {
		HouseMasterModel model = new HouseMasterModel();
		model.initiate(context, session);
		model.dblClickItt(bean);
		model.terminate();

		getNavigationPanel(3);
		bean.setEnableAll("N");
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

}
