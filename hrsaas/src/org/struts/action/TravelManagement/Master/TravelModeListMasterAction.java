package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.TravelModeListMasterBean;
import org.paradyne.model.TravelManagement.Master.TravelModeListMasterModel;
import org.struts.lib.ParaActionSupport;

public class TravelModeListMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelModeListMasterAction.class);

	TravelModeListMasterBean bean;

	public void prepare_local() throws Exception {
		bean = new TravelModeListMasterBean();
		bean.setMenuCode(1088);
		getNavigationPanel(1);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public String input() throws Exception {

		try {
			TravelModeListMasterModel model = new TravelModeListMasterModel();
			model.initiate(context, session);
			model.getList(bean, request);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public TravelModeListMasterBean getBean() {
		return bean;
	}

	public void setBean(TravelModeListMasterBean bean) {
		this.bean = bean;
	}

	public String reset() {
		bean.setJourneyId("");
		bean.setTravelModeName("");
		bean.setAirlineBusTrain("");
		bean.setModeId("");
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String addNew() {
		reset();
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String searchMode() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT JOURNEY_ID,NVL(JOURNEY_NAME,' ')  FROM HRMS_TMS_JOURNEY_MODE WHERE JOURNEY_STATUS='A' ORDER BY JOURNEY_LEVEL  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "JOURNEY ID", "JOURNEY_NAME" };

		String[] headerWidth = { "30", "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "journeyId", "travelModeName" };

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

	public String back() {

		TravelModeListMasterModel model = new TravelModeListMasterModel();
		model.initiate(context, session);
		model.getList(bean, request);
		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}

	public String searchModeName() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT TRAVEL_CARRIER_ID,JOURNEY_NAME,TRAVEL_CARRIER_NAME FROM TMS_CARRIER LEFT JOIN  HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=TMS_CARRIER.TRAVEL_MODE_ID)  ORDER BY TRAVEL_CARRIER_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "TRAVEL_ID", "MODE NAME", "AIRLINES/BUS/TRAIN" };

		String[] headerWidth = { "30", "40", "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "ModeId", "travelModeName", "airlineBusTrain" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ListOfTravelMode_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String getDetails() {
		TravelModeListMasterModel model = new TravelModeListMasterModel();
		model.initiate(context, session);
		model.getDetails(bean);
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}

	public String save() {
		TravelModeListMasterModel model = new TravelModeListMasterModel();
		model.initiate(context, session);
		boolean result;

		if (bean.getModeId().equals("")) {
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
			}// end of if
			else {
				addActionMessage("Type can not be added");
			}// end of else
		}
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}

	public String dblClickItt() {
		TravelModeListMasterModel model = new TravelModeListMasterModel();
		model.initiate(context, session);
		model.dblClickItt(bean);
		model.terminate();

		getNavigationPanel(3);
		bean.setEnableAll("N");
		return SUCCESS;
	}

	public String deleteCheck() {
		String code[] = request.getParameterValues("hidCode");
		String ModeId[] = request.getParameterValues("ittModeCode");
		// for (int j = 0; j < code.length; j++) {
		// System.out.println("......"+code[j]);
		// }
		// for (int i = 0; i < code.length; i++) {
		// System.out.println("......"+empId[i]);
		// }
		TravelModeListMasterModel model = new TravelModeListMasterModel();
		model.initiate(context, session);
		boolean flag = model.deleteCheck(bean, code, ModeId, request);
		if (flag) {
			addActionMessage("Record deleted successfully");
		} else {
			addActionMessage("One or more records cannot be deleted as they are associated with some other records");
		}
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}

	public String edit() {

		try {
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
	}

	public String callPage() throws Exception {
		try {
			input();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String delete() {
		boolean result;

		TravelModeListMasterModel model = new TravelModeListMasterModel();
		model.initiate(context, session);
		result = model.delete(bean, request);
		if (result) {
			addActionMessage("Record Deleted successfully");

		}else{
			addActionMessage("Record cannot be deleted \n as it is associated with some other records");
		}

		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}

}
