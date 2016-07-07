package org.struts.action.ApplicationStudio;

import org.paradyne.model.ApplicationStudio.EventModel;
import org.paradyne.bean.ApplicationStudio.EventMaster;


/**
 * @author Anantha lakshmi
 * 
 */
public class EventAction extends org.struts.lib.ParaActionSupport {
	private static final long serialVersionUID = 1L;
	EventMaster eventMaster = null;

	/**
	 * @return the eventMaster
	 */

	public void prepare_local() throws Exception {
		eventMaster = new EventMaster();
		eventMaster.setMenuCode(2038);
	}

	public Object getModel() {

		return eventMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * @param eventMaster
	 *            the eventMaster to set
	 */
	/**
	 * @return the eventMaster
	 */
	public EventMaster getEventMaster() {
		return eventMaster;
	}

	/**
	 * @param EventMaster the eventMaster to set
	 */
	public void setEventMaster(EventMaster eventMaster) {
		this.eventMaster = eventMaster;
	}

	/** called on load to set the list* */
	public String input() {
		//System.out.println("==ON LOADING=======");
		EventModel model = new EventModel();
		model.initiate(context, session);
		model.eventData(eventMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */

	// to add new event data
	public String addNewEvent() {
		try {
			getNavigationPanel(2);
			reset(); 
			return "eventData";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callForEdit() {
		//System.out.println("-calforedit-------");
		EventModel model = new EventModel();
		model.initiate(context, session);
		String hiddenCode = request.getParameter("hiddenCode");

		model.editEventData(eventMaster, hiddenCode);
		getNavigationPanel(3);
		eventMaster.setEnableAll("N");
		model.terminate();
		return "eventData";
	}

	// this is for delete EventData 
	public String deleteEventData() {
		boolean result;
		EventModel model = new EventModel();
		model.initiate(context, session);
		String code[] = request.getParameterValues("hdeleteCode");
		result = model.deleteEvent(eventMaster, code);
		model.eventData(eventMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	public String back() {
		eventMaster.setMailId("");
		eventMaster.setEvent("");
		eventMaster.setEventCode("");
		return input();
	}
	
	
	public String reset() {
		eventMaster.setMailId("");
		eventMaster.setEvent("");
		eventMaster.setEventCode("");
		getNavigationPanel(2);
		return "eventData";
	}

	public String delete() {
		try {
			EventModel model = new EventModel();
			model.initiate(context, session);
			boolean flag = model.delete(eventMaster);
			model.eventData(eventMaster, request);
			if (flag) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record not deleted ");
			}

			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * To Add Event record or update records
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		boolean result;

		EventModel model = new EventModel();
		model.initiate(context, session);
		if (eventMaster.getEventCode().equals("")) {
			result = model.addEventData(eventMaster);
			if (result) {
				addActionMessage(getText("addMessage", ""));
			}
			else{
				addActionMessage("Record already present");
			}
		} else {
			result = model.modifyEvent(eventMaster);
			if (result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "eventData";
			}// end of if
			else {
				addActionMessage("Record already present");
				reset();
				getNavigationPanel(1);
				return input();
			}// end of else
		}
		model.terminate();
		//return SUCCESS;
		return input();
		//return "eventData";
	}

	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String callPage() throws Exception {

		EventModel model = new EventModel();
		model.initiate(context, session);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	public String data() {
		try {
			getNavigationPanel(3);
			return "eventData";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String f9action() throws Exception { 
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT EVENT_NAME, EVENT_EMAIL, EVENT_CODE FROM HRMS_PORTAL_EVENT";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		
		String[] headers ={  getMessage("event"), getMessage("mailId") }; 

		String[] headerWidth = { "50", "50" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */

		String[] fieldNames = {"eventName","mailId","eventCode"};

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2};

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "Event_data.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
}