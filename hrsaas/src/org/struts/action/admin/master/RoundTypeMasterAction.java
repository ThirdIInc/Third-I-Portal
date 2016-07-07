package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.RoundTypeMasterBean;
import org.paradyne.model.TravelManagement.Master.ProjectMasterModel;
import org.paradyne.model.admin.master.EmpTypeModel;
import org.paradyne.model.admin.master.RoundTypeMasterModel;
import org.paradyne.model.payroll.PayrollZoneMasterModel;
import org.struts.action.TravelManagement.Master.ProjectMasterAction;
import org.struts.lib.ParaActionSupport;

public class RoundTypeMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RoundTypeMasterAction.class);
	RoundTypeMasterBean bean;

	public void prepare_local() throws Exception {
		bean = new RoundTypeMasterBean();
		bean.setMenuCode(1104);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public String input() throws Exception {

		try {
			RoundTypeMasterModel model = new RoundTypeMasterModel();
			model.initiate(context, session);
			model.getList(bean, request);
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public RoundTypeMasterBean getBean() {
		return bean;
	}

	public void setBean(RoundTypeMasterBean bean) {
		this.bean = bean;
	}

	public String addNew() {
		reset();
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String back() {

		RoundTypeMasterModel model = new RoundTypeMasterModel();
		model.initiate(context, session);
		model.getList(bean, request);
		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}

	public String deleteCheck() {
		String code[] = request.getParameterValues("hidCode");
		RoundTypeMasterModel model = new RoundTypeMasterModel();
		model.initiate(context, session);
		boolean result = model.deleteCheck(bean, code);
		
		if(result) {
			reset();
			addActionMessage(getMessage("delete"));
		}else {
			addActionMessage(getMessage("multiple.del.error"));
		}

		model.getList(bean, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}

	public String save() {
		RoundTypeMasterModel model = new RoundTypeMasterModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getRoundCode().equals("")) {
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

	public String reset() {
		bean.setRoundType("");
		bean.setRoundCode("");

		getNavigationPanel(2);
		return SUCCESS;
	}

	public String search() {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "  SELECT  ROUND_TYPE,ROUND_CODE FROM HRMS_REC_ROUND_TYPE  ORDER BY ROUND_CODE  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Round Type" };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "roundType", "roundCode" };

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
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "RoundTypeMaster_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String getDetails() {
		RoundTypeMasterModel model = new RoundTypeMasterModel();
		model.initiate(context, session);
		model.getDetails(bean);
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}


	public String dblClickItt() {
		RoundTypeMasterModel model = new RoundTypeMasterModel();
		model.initiate(context, session);
		model.dblClickItt(bean);
		model.terminate();

		getNavigationPanel(3);
		bean.setEnableAll("N");
		return SUCCESS;
	}

	public String delete() {
		boolean result;
		RoundTypeMasterModel model = new RoundTypeMasterModel();
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

}
