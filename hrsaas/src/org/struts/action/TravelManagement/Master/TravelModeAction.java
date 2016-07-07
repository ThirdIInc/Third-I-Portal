package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.TravelMode;
import org.paradyne.model.TravelManagement.Master.TravelModeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0651
 * 
 */
public class TravelModeAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelModeAction.class);
	TravelMode travel;

	public void prepare_local() throws Exception {

		travel = new TravelMode();
		travel.setMenuCode(783);

	}

	public Object getModel() {
		return travel;
	}

	public TravelMode getTravel() {
		return travel;
	}

	public void setTravel(TravelMode travel) {
		this.travel = travel;
	}

	public void prepare_withLoginProfileDetails() throws Exception {

		input();

	}

	public String input() throws Exception {
		getNavigationPanel(1);
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		model.reqData(travel, request);
		travel.setOnLoadFlag(false);
		travel.setPanelFlag("1");
		travel.setRetrnFlag("view");
		model.terminate();
		return "view";
	}

	public String addNew() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(2);
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		model.reqData(travel, request);
		// callPage();
		// reset();
		travel.setOnLoadFlag(true);
		travel.setLoadFlag(true);
		travel.setFlag(true);
		travel.setTrvId("");
		travel.setPanelFlag("2");
		travel.setRetrnFlag("success");
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
		travel.setPanelFlag("3");
		String str;
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		logger.info("***********==Inside Save========= " + travel.getTrvId());
		if (travel.getTrvId().equals("")) {

			boolean result = model.addTravelMode(travel);
			if (result) {
				addActionMessage(getMessage("save"));
				travel.setOnLoadFlag(true);
				travel.setPageFlag("true");
				travel.setRetrnFlag("view");
				str = "view";
			} else {
				addActionMessage(getMessage("duplicate"));
				travel.setCancelFlg("false");
				getNavigationPanel(2);
				travel.setPanelFlag("2");
				travel.setRetrnFlag("success");
				str = "success";
			}
		} else {

			boolean result = model.modTravelMode(travel);
			if (result) {
				addActionMessage(getMessage("update"));
				travel.setOnLoadFlag(true);
				getNavigationPanel(3);
				travel.setPageFlag("true");
				travel.setRetrnFlag("view");
				str = "view";
			} else {
				addActionMessage(getMessage("duplicate"));
				getNavigationPanel(2);
				travel.setLoadFlag(true);
				travel.setCancelFlg("false");
				travel.setFlag(true);
				travel.setPanelFlag("2");
				travel.setRetrnFlag("success");
				str = "success";
			}
		}
		model.reqData(travel, request);
		travel.setTableLength("");
		travel.setOnLoadFlag(true);
		model.terminate();
		return str;
	}

	public String report() {
		getNavigationPanel(5);
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		String[] label = { "Sr.No", getMessage("travel.mode"),
				getMessage("travel.class"), getMessage("travel.sts") };
		model.generateReport(travel, response, label);
		model.terminate();
		return null;
	}

	public String edit() throws Exception {
		getNavigationPanel(4);
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		travel.setCancelFlg("true");
		model.getTravelModeSearch(travel);
		model.reqData(travel, request);
		model.terminate();
		travel.setPanelFlag("4");
		travel.setRetrnFlag("success");
		return "success";
	}

	/**
	 * following function is called when
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		getNavigationPanel(2);
		travel.setCancelFlg("false");
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		model.getTravelModeOnDoubleClick(travel);
		model.reqData(travel, request);
		travel.setOnLoadFlag(true);
		model.terminate();
		travel.setPanelFlag("2");
		travel.setRetrnFlag("success");
		return "success";

	}

	public String reset() throws Exception {
		travel.setTrvMode("");
		travel.setTrvId("");
		travel.setStatus("");
		return SUCCESS;
	}

	public String delete1() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(1);
		boolean result;
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		// callPage2();
		String[] code = request.getParameterValues("hdeleteCode");
		result = model.delChkdRec(travel, code);		
		if (result) {
			try {
				input();
			} catch (Exception e) {
				logger.error("Error in delete1---");
			}
			travel.setOnLoadFlag(false);
			travel.setPageFlag("true");
			reset();
			addActionMessage(getMessage("delete"));
		} else
			addActionMessage(getMessage("multiple.del.error"));

		travel.setPanelFlag("1");
		travel.setRetrnFlag("view");
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
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);

		boolean result = model.deleteTravelMode(travel);

		if (result) {
			addActionMessage(getMessage("delete"));
			travel.setOnLoadFlag(false);
			// reset();
			// callPage();
		} else {
			addActionMessage(getMessage("del.error"));
			// callPage();
		}
		// travel.setOnLoadFlag(true);
		input();
		model.terminate();

		travel.setPanelFlag("1");
		travel.setRetrnFlag("view");
		return "view";
	}

	public String f9Action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		// String query = "SELECT * FROM HRMS_TMS_ROOM_TYPE ORDER BY
		// ROOM_TYPE_ID";
		String query = "SELECT  JOURNEY_NAME,"
				+ " CASE WHEN JOURNEY_STATUS='A' THEN 'Active' ELSE 'Deactive' END,JOURNEY_ID "
				+ " FROM HRMS_TMS_JOURNEY_MODE ORDER BY  JOURNEY_LEVEL";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("travel.mode"),
				getMessage("travel.sts") };

		String[] headerWidth = { "20", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "trvMode", "status", "trvId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

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
		String submitToMethod = "TravelMode_details.action";
		//String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String details() {
		getNavigationPanel(3);
		travel.setOnLoadFlag(true);
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		//model.getTravelMode(travel);
		travel.setSaveFlag(true);
		model.reqData(travel, request);
		model.terminate();
		travel.setPanelFlag("3");
		travel.setRetrnFlag("view");
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

	public String getRecord() throws Exception {

		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		model.getTravelMode(travel);
		travel.setSaveFlag(true);
		model.reqData(travel, request);
		model.terminate();
		return "success";
	}

	/*
	 * public String callPage2() throws Exception { getNavigationPanel(1);
	 * TravelModeModel model = new TravelModeModel(); model.initiate(context,
	 * session); model.reqData(travel,request); travel.setPageFlag("true");
	 * model.terminate();
	 * 
	 * 
	 * return "success"; }
	 * 
	 *//**
	 * following function is called to display all the records when the link
	 * is clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String callPage() throws Exception { TravelModeModel model = new
	 * TravelModeModel(); model.initiate(context, session);
	 * model.reqData(travel,request); model.terminate(); return "success"; }
	 */
	/*
	 * public String cancelThrd() throws Exception{ logger.info("Inside Cancel
	 * Third"); getNavigationPanel(3); TravelModeModel model = new
	 * TravelModeModel(); model.initiate(context, session);
	 * 
	 * callPage(); model.getTravelMode(travel); travel.setSaveFlag(true);
	 * travel.setModFlag(false); model.terminate(); return "success";
	 *  }
	 * 
	 * public String cancelSec() throws Exception{ getNavigationPanel(1);
	 * TravelModeModel model = new TravelModeModel(); model.initiate(context,
	 * session); callPage(); travel.setOnLoadFlag(true);
	 * travel.setSaveFlag(true); model.terminate(); return "success";
	 *  }
	 * 
	 * public String cancelFirst() throws Exception{ getNavigationPanel(1);
	 * callPage(); travel.setOnLoadFlag(true); reset(); return "success"; }
	 * 
	 * public String cancelFrth() throws Exception{ logger.info("Inside Cancel
	 * Fourth"); getNavigationPanel(1); TravelModeModel model = new
	 * TravelModeModel(); model.initiate(context, session); callPage(); reset();
	 * travel.setOnLoadFlag(true); model.terminate(); return "success";
	 *  }
	 */
	public String cancelFirst() throws Exception {
		logger.info("=========bean.getCancelFlg()========="
				+ travel.isOnLoadFlag());
		String str;
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		if (travel.getCancelFlg().equals("true")) {
			getNavigationPanel(3);
			model.getTravelMode(travel);
			model.reqData(travel, request);
			travel.setOnLoadFlag(true);
			travel.setPanelFlag("3");
			travel.setRetrnFlag("view");
			str = "view";
		} else {
			getNavigationPanel(1);
			model.reqData(travel, request);
			reset();
			travel.setOnLoadFlag(false);
			travel.setPanelFlag("1");
			travel.setRetrnFlag("view");
			str = "view";
		}
		model.terminate();
		return str;
	}

	public String cancelSec() throws Exception {
		getNavigationPanel(1);
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		model.reqData(travel, request);
		reset();
		travel.setOnLoadFlag(false);
		model.terminate();

		travel.setPanelFlag("1");
		travel.setRetrnFlag("view");
		return "view";
	}

	public String callPage() throws Exception {

		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		/*
		 * if (travel.getPageFlag().equals("true")) { getNavigationPanel(2); }
		 * else { getNavigationPanel(1); } model.reqData(travel, request);
		 * model.terminate();
		 */

		getNavigationPanel(Integer.parseInt(travel.getPanelFlag()));
		model.reqData(travel, request);
		model.terminate();
		return travel.getRetrnFlag();

	}

	public String callPageView() throws Exception {
		logger.info("--------bean.getPageFlag()----------"
				+ travel.getPageFlag());
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		/*
		 * if (travel.getPageFlag().equals("true")) { model.reqData(travel,
		 * request); getNavigationPanel(3);
		 * logger.info("--------bean.getPageFlag()----------" +
		 * travel.getPageFlag()); } else { getNavigationPanel(1);
		 * model.reqData(travel, request); } //model.getRecords(bean, request);
		 */getNavigationPanel(Integer.parseInt(travel.getPanelFlag()));
		model.reqData(travel, request);
		model.terminate();
		return travel.getRetrnFlag();

	}

	public String callPage1() throws Exception {
		getNavigationPanel(1);
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		travel.setPageFlag("true");
		// bean.setOnLoadFlag("true");
		getNavigationPanel(Integer.parseInt(travel.getPanelFlag()));
		model.reqData(travel, request);
		model.terminate();
		return travel.getRetrnFlag();

	}

	public String upData() throws Exception {
		/* Default Method with save modeCode(2) */
		logger.info("====UPDATA===");
		getNavigationPanel(1);
		boolean result;
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		// callPage2();
		model.upData(travel);
		prepare_withLoginProfileDetails();
		reset();

		travel.setPanelFlag("1");
		travel.setRetrnFlag("success");
		return "success";
	}

	public String upDataView() throws Exception {
		/* Default Method with save modeCode(2) */
		logger.info("====UPDATA----VIEW===");
		getNavigationPanel(1);
		boolean result;
		TravelModeModel model = new TravelModeModel();
		model.initiate(context, session);
		// callPage2();
		model.upData(travel);
		prepare_withLoginProfileDetails();
		reset();
		travel.setPanelFlag("1");
		travel.setRetrnFlag("view");
		return "view";
	}
}
