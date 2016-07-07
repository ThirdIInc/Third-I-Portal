package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.TravelClass;
import org.paradyne.model.TravelManagement.Master.TravelClassModel;
import org.struts.lib.ParaActionSupport;

public class TravelClassAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelModeAction.class);
	TravelClass trvlCls;

	public void prepare_local() throws Exception {
		trvlCls = new TravelClass();
		trvlCls.setMenuCode(918);

	}

	public Object getModel() {
		return trvlCls;
	}

	public TravelClass getTrvCls() {
		return trvlCls;
	}

	public void setTrvCls(TravelClass trvCls) {
		this.trvlCls = trvCls;
	}

	public String input() throws Exception {
		getNavigationPanel(1);
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		model.reqData(trvlCls, request);
		trvlCls.setOnLoadFlag(false);
		trvlCls.setPanelFlag("1");
		trvlCls.setRetrnFlag("view");
		model.terminate();
		return "view";
	}

	public String addNew() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(2);
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		model.reqData(trvlCls, request);
		// callPage();
		// reset();
		trvlCls.setOnLoadFlag(true);
		trvlCls.setLoadFlag(true);
		trvlCls.setFlag(true);
		trvlCls.setTrvlClsId("");
		trvlCls.setPanelFlag("2");
		trvlCls.setRetrnFlag("success");
		reset();
		model.terminate();
		return "success";

	}

	/**
	 * following function is called when add new record is clicked in the jsp
	 * page
	 * 
	 * @return
	 */
	public String save() throws Exception {
		// Default Method with Edit modeCode(3)
		getNavigationPanel(3);
		trvlCls.setPanelFlag("3");
		String str;
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);

		if (trvlCls.getHiddencode().equals("")) {

			boolean result = model.addTravelClass(trvlCls);
			if (result) {
				addActionMessage(getMessage("save"));
				trvlCls.setOnLoadFlag(true);
				trvlCls.setPageFlag("true");
				trvlCls.setRetrnFlag("view");
				str = "view";
			} else {
				addActionMessage(getMessage("duplicate"));
				trvlCls.setCancelFlg("false");
				getNavigationPanel(2);
				trvlCls.setPanelFlag("2");
				trvlCls.setRetrnFlag("success");
				str = "success";
			}
		} else {

			boolean result = model.modTravelClass(trvlCls);
			if (result) {
				addActionMessage(getMessage("update"));
				trvlCls.setOnLoadFlag(true);
				getNavigationPanel(3);
				trvlCls.setPageFlag("true");
				trvlCls.setRetrnFlag("view");
				str = "view";
			} else {
				addActionMessage(getMessage("duplicate"));
				getNavigationPanel(2);
				trvlCls.setLoadFlag(true);
				trvlCls.setCancelFlg("false");
				trvlCls.setFlag(true);
				trvlCls.setPanelFlag("2");
				trvlCls.setRetrnFlag("success");
				str = "success";
			}
		}
		trvlCls.setTrvlClsId(trvlCls.getHiddencode());
		model.reqData(trvlCls, request);
		trvlCls.setTableLength("");
		trvlCls.setOnLoadFlag(true);
		model.terminate();

		logger.info("-----------------str--------------------" + str);

		return str;
	}

	public String report() {

		return null;
	}

	public String edit() throws Exception {
		getNavigationPanel(4);
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		trvlCls.setCancelFlg("true");
		model.getTravelClassSearch(trvlCls);
		model.reqData(trvlCls, request);
		model.terminate();
		trvlCls.setPanelFlag("4");
		trvlCls.setRetrnFlag("success");
		return "success";
	}

	/**
	 * following function is called when
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		
		trvlCls.setCancelFlg("false");
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		model.getTravelClassOnDoubleClick(trvlCls);
		//model.reqData(trvlCls, request);
		trvlCls.setOnLoadFlag(true);
		model.terminate();
		trvlCls.setPanelFlag("3");
		trvlCls.setRetrnFlag("success");
		getNavigationPanel(3);
		trvlCls.setEnableAll("N");
		return "success";

	}

	public String reset() throws Exception {
		trvlCls.setTrvlClass("");
		trvlCls.setTrvlClsId("");
		trvlCls.setTrvlMode("");
		trvlCls.setTrvlModeId("");
		trvlCls.setExpnseAmnt("");
		trvlCls.setHiddencode("");
		trvlCls.setStatus("");
		return SUCCESS;
	}

	public String delete1() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(1);
		boolean result;
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		// callPage2();
		String[] code = request.getParameterValues("hdeleteCode");
		result = model.delChkdRec(trvlCls, code);
		logger.info("Result-------delete1----" + result);
		if (result) {
			try {
				input();
			} catch (Exception e) {
				logger.error("Error in delete1---");
			}
			trvlCls.setOnLoadFlag(false);
			trvlCls.setPageFlag("true");
			reset();
			addActionMessage(getMessage("delete"));
		} else
			addActionMessage(getMessage("multiple.del.error"));

		trvlCls.setPanelFlag("1");
		trvlCls.setRetrnFlag("view");
		return "view";
	}

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(1);
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);

		boolean result = model.deleteTravelClass(trvlCls);

		if (result) {
			addActionMessage(getMessage("delete"));
			trvlCls.setOnLoadFlag(false);
			// reset();
			// callPage();
		} else {
			addActionMessage(getMessage("del.error"));
			// callPage();
		}
		// travel.setOnLoadFlag(true);
		input();
		model.terminate();

		trvlCls.setPanelFlag("1");
		trvlCls.setRetrnFlag("view");
		return "view";
	}

	public String f9Action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		// String query = "SELECT * FROM HRMS_TMS_ROOM_TYPE ORDER BY
		// ROOM_TYPE_ID";
		String query = "SELECT  NVL(JOURNEY_NAME,' '),NVL(CLASS_NAME,''),"
				+ " CASE WHEN CLASS_STATUS='A' THEN 'Active' ELSE 'Deactive' END as status,CLASS_MIN_EXPENSE,"
				+ " CLASS_ID ,CLASS_JOURNEY_ID"
				+ " FROM HRMS_TMS_JOURNEY_CLASS "
				+ " LEFT JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
				+ "ORDER BY  CLASS_ID,CLASS_LEVEL";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("travel.mode"),
				getMessage("travel.class"), getMessage("travel.sts"), };

		String[] headerWidth = { "20", "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "trvlMode", "trvlClass", "status","expnseAmnt","trvlClsId",
				"trvlModeId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 ,5};

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
		String submitToMethod = "TravelClass_calforedit.action";
		// String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String details() {
		getNavigationPanel(3);
		trvlCls.setOnLoadFlag(true);
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		 //model.getTravelMode(trvlCls);
		trvlCls.setSaveFlag(true);
		model.reqData(trvlCls, request);
		model.terminate();
		trvlCls.setPanelFlag("3");
		trvlCls.setRetrnFlag("view");
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

	/*
	 * public String getRecord() throws Exception {
	 * 
	 * TravelClassModel model = new TravelClassModel(); model.initiate(context,
	 * session); model.getTravelMode(travel); travel.setSaveFlag(true);
	 * model.reqData(travel, request); model.terminate(); return "success"; }
	 */

	public String cancelFirst() throws Exception {
		String str;
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		if (trvlCls.getCancelFlg().equals("true")) {
			getNavigationPanel(3);
			model.getTravelClass(trvlCls);
			model.reqData(trvlCls, request);
			trvlCls.setOnLoadFlag(true);
			trvlCls.setPanelFlag("3");
			trvlCls.setRetrnFlag("view");
			str = "view";
		} else {
			getNavigationPanel(1);
			model.reqData(trvlCls, request);
			reset();
			trvlCls.setOnLoadFlag(false);
			trvlCls.setPanelFlag("1");
			trvlCls.setRetrnFlag("view");
			str = "view";
		}
		model.terminate();
		return str;
	}

	public String cancelSec() throws Exception {
		getNavigationPanel(1);
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		model.reqData(trvlCls, request);
		reset();
		trvlCls.setOnLoadFlag(false);
		model.terminate();

		trvlCls.setPanelFlag("1");
		trvlCls.setRetrnFlag("view");
		return "view";
	}

	public String callPage() throws Exception {

		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		getNavigationPanel(Integer.parseInt(trvlCls.getPanelFlag()));
		model.reqData(trvlCls, request);
		model.terminate();
		return trvlCls.getRetrnFlag();

	}

	public String callPageView() throws Exception {

		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		getNavigationPanel(Integer.parseInt(trvlCls.getPanelFlag()));
		model.reqData(trvlCls, request);
		model.terminate();
		return trvlCls.getRetrnFlag();

	}

	public String callPage1() throws Exception {
		getNavigationPanel(1);
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		trvlCls.setPageFlag("true");
		// bean.setOnLoadFlag("true");
		getNavigationPanel(Integer.parseInt(trvlCls.getPanelFlag()));
		model.reqData(trvlCls, request);
		model.terminate();
		return trvlCls.getRetrnFlag();

	}

	/*
	 * public String deleteCls() throws Exception { getNavigationPanel(2);
	 * TravelClassModel model = new TravelClassModel(); model.initiate(context,
	 * session); model.delCls(travel, request); trvlCls.setLoadFlag(true);
	 * trvlCls.setFlag(true); model.terminate();
	 * 
	 * travel.setPanelFlag("2"); travel.setRetrnFlag("success");
	 * 
	 * return SUCCESS; }
	 */

	public String upData() throws Exception {
		/* Default Method with save modeCode(2) */
		logger.info("====UPDATA===");
		getNavigationPanel(1);
		boolean result;
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		// callPage2();
		String results = model.upData(trvlCls);
		if (!results.equals("")) {
			addActionMessage(results);
		}
		prepare_withLoginProfileDetails();
		reset();

		trvlCls.setPanelFlag("1");
		trvlCls.setRetrnFlag("success");
		return "success";
	}

	public String upDataView() throws Exception {
		/* Default Method with save modeCode(2) */
		logger.info("====UPDATA----VIEW===");
		getNavigationPanel(1);
		boolean result;
		TravelClassModel model = new TravelClassModel();
		model.initiate(context, session);
		// callPage2();
		String results = model.upData(trvlCls);
		if (!results.equals("")) {
			addActionMessage(results);
		}
		prepare_withLoginProfileDetails();
		reset();
		trvlCls.setPanelFlag("1");
		trvlCls.setRetrnFlag("view");
		return "view";
	}

	public void prepare_withLoginProfileDetails() throws Exception {

		input();

	}

	public String f9Mode() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT NVL(JOURNEY_NAME,''),JOURNEY_ID FROM HRMS_TMS_JOURNEY_MODE"
				+ "  WHERE  JOURNEY_STATUS='A'  ORDER BY   JOURNEY_LEVEL";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("travel.mode") };
		String[] headerWidth = { "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "trvlMode", "trvlModeId" };

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

}
