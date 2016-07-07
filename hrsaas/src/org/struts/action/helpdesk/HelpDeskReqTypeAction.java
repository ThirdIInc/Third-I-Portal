/**
 * 
 */
package org.struts.action.helpdesk;

import org.paradyne.bean.helpdesk.HelpDeskReqType;
import org.paradyne.model.helpdesk.HelpDeskReqTypeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0623
 * 
 */
public class HelpDeskReqTypeAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.helpdesk.HelpDeskReqTypeAction.class);

	HelpDeskReqType reqType;

	/**
	 * @return the reqType
	 */
	public HelpDeskReqType getReqType() {
		return reqType;
	}

	/**
	 * @param reqType
	 *            the reqType to set
	 */
	public void setReqType(HelpDeskReqType reqType) {
		this.reqType = reqType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		reqType = new HelpDeskReqType();
		reqType.setMenuCode(1040);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return reqType;
	}

	@Override
	public String input() throws Exception {
		getNavigationPanel(1);
		HelpDeskReqTypeModel model = new HelpDeskReqTypeModel();
		model.initiate(context, session);
		model.getRecords(reqType, request);
		reqType.setEnableAll("N");
		model.terminate();
		return INPUT;
	}

	public String addNew() {
		try {
			reset();
			getNavigationPanel(2);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String f9Action() throws Exception {

		String query = " SELECT NVL(REQUEST_TYPE_NAME,' '), NVL(HELPDESK_DEPT.DEPT_NAME,' '), REQUEST_TYPE_DEPT, REQUEST_TYPE_ID "
				+ " FROM HELPDESK_REQUEST_TYPE "
				+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_TYPE.REQUEST_TYPE_DEPT) "
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HELPDESK_DEPT.DEPT_CODE) "
				+ " ORDER BY HRMS_DEPT.DEPT_ID";

		String[] headers = { getMessage("req.type"), getMessage("dept.name") };
		String[] headerwidth = { "55", "45" };

		String fieldNames[] = { "reqType", "reqTypeDept", "reqTypeDeptId",
				"reqTypeCode" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlage = "true";

		String submitToMethod = "HelpDeskReqType_details.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}

	/**
	 * following function is called to set the field values when a record is
	 * selected from the search window
	 * 
	 * @throws Exception
	 */
	public String details() {
		try {
			getNavigationPanel(3);
			HelpDeskReqTypeModel model = new HelpDeskReqTypeModel();
			model.initiate(context, session);
			model.getRequestTypes(reqType);
			//model.getRecords(reqType, request);
			//model.getReqTypeOnDblClick(reqType);
			getNavigationPanel(3);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * Method to select the department. *
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9deptaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT NVL(HELPDESK_DEPT.DEPT_NAME,' '), HELPDESK_DEPT.DEPT_CODE FROM HELPDESK_DEPT "
			+ " WHERE  HELPDESK_DEPT.IS_ACTIVE='Y' "
			+ " ORDER BY  HELPDESK_DEPT.DEPT_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("deptname") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "reqTypeDept", "reqTypeDeptId" };

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
	 * following function is called to reset the fields.
	 * 
	 * @return
	 */
	public String reset() {
		reqType.setReqType("");
		reqType.setReqTypeCode("");
		reqType.setReqTypeDept("");
		reqType.setReqTypeDeptId("");
		reqType.setHdeleteCode("");
		reqType.setHiddencode("");
		reqType.setIsManagerApproved("false");
		getNavigationPanel(2);
		return SUCCESS;
	}

	/**
	 * following function is called to display all the records when the link is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		HelpDeskReqTypeModel model = new HelpDeskReqTypeModel();
		model.initiate(context, session);
		model.getRecords(reqType, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}

	/**
	 * following function is called when
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() {
		try {
			HelpDeskReqTypeModel model = new HelpDeskReqTypeModel();
			model.initiate(context, session);
			model.getReqTypeOnDblClick(reqType);
			//model.getRecords(reqType, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		reqType.setEnableAll("N");
		return SUCCESS;
	}

	public String deleteList() throws Exception {
		getNavigationPanel(1);
		String code[] = request.getParameterValues("hdeleteCode");
		HelpDeskReqTypeModel model = new HelpDeskReqTypeModel();
		model.initiate(context, session);
		boolean result = model.delChkdRec(reqType, code);
		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can not be deleted \n as they are associated with some other records.");
		}
		model.getRecords(reqType, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 * 
	 * @return
	 */
	public String delete() throws Exception {
		getNavigationPanel(1);
		HelpDeskReqTypeModel model = new HelpDeskReqTypeModel();
		model.initiate(context, session);
		boolean result = model.deleteReqType(reqType);
		if (result) {
			addActionMessage(getText("Record deleted successfully!"));
		} else {
			addActionMessage("This record is referenced in some other records \n so can not be deleted");
		}
		model.terminate();
		reset();
		model.getRecords(reqType, request);
		getNavigationPanel(1);
		return INPUT;
	}

	/**
	 * following function is called when add new record and to update a record
	 * in the jsp page
	 * 
	 * @return
	 */

	public String save() throws Exception {

		String result = "";
		String page;

		HelpDeskReqTypeModel model = new HelpDeskReqTypeModel();
		model.initiate(context, session);

		if (reqType.getReqTypeCode().equals("")) {
			result = model.addRequestTypes(reqType);
			if (result.equals("saved")) {
				addActionMessage(getText("addMessage", ""));
				getNavigationPanel(3);
				page = "success";
			} else if (result.equals("duplicate")) {
				addActionMessage(getText("Duplicate entry found!"));
				getNavigationPanel(1);
				input();
				page = "input";
			} else {
				addActionMessage(getText("Record can not be saved!"));
				getNavigationPanel(1);
				input();
				page = "input";
			}
		} else {
			result = model.modRequestTypes(reqType);
			if (result.equals("modified")) {
				addActionMessage(getText("Record updated successfully!"));
				getNavigationPanel(3);
				page = "success";

			} else if (result.equals("duplicate")) {
				addActionMessage(getText("Duplicate entry found!"));
				getNavigationPanel(1);
				input();
				page = "input";
			} else {
				addActionMessage(getText("Record cannot be updated!"));
				getNavigationPanel(1);
				input();
				page = "input";
			}
		}
		model.getRecords(reqType, request);
		model.terminate();
		return page;
	}

}
