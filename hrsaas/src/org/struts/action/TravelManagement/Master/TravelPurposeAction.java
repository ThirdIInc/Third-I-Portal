package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.TravelPurpose;
import org.paradyne.model.TravelManagement.Master.TravelPurposeModel;

/**
 * Author Dilip 
 * modified by Dipti
 */
public class TravelPurposeAction extends org.struts.lib.ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	TravelPurpose bean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

		bean = new TravelPurpose();
		bean.setMenuCode(763);

	}

	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		// callPage();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public TravelPurpose getBean() {
		return bean;
	}

	public void setBean(TravelPurpose bean) {
		this.bean = bean;
	}

	/*
	 * public String input() throws Exception{ //Default Method with default
	 * modeCode(1) logger.info("Input-------->"); getNavigationPanel(1);
	 * TravelPurposeModel model=new TravelPurposeModel();
	 * model.initiate(context, session); bean.setOnLoadFlag(true); callPage();
	 * model.terminate(); return INPUT; }
	 */
	public String input() throws Exception {
		// Default Method with default modeCode(1)
		logger.info("Input-------->");
		getNavigationPanel(1);
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setOnLoadFlag(false);
		bean.setEditModeFlag(true);
		bean.setPanelFlag("1");
		bean.setRetrnFlag("view");
		model.terminate();
		return "view";
	}

	public String addNew() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(2);
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setOnLoadFlag(true);
		reset();
		model.terminate();

		bean.setPanelFlag("2");
		bean.setRetrnFlag("success");
		return "success";

	}

	public String edit() throws Exception {
		/* Default Method with save modeCode(2) */
		getNavigationPanel(2);
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		model.getTravelPurpose(bean);
		model.getRecords(bean, request);
		bean.setOnLoadFlag(true);
		bean.setCancelFlg("true");
		bean.setPageFlag("true");
		logger.info("Inside Edit----->" + bean.isOnLoadFlag());
		model.terminate();

		bean.setPanelFlag("2");
		bean.setRetrnFlag("success");
		return "success";

	}

	public String cancelFirst() throws Exception {
		logger.info("=========bean.getCancelFlg()========="
				+ bean.isOnLoadFlag());
		String str;
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		if (bean.getCancelFlg().equals("true")) {
			getNavigationPanel(3);
			model.getTravelPurposeEdt(bean);
			model.getRecords(bean, request);
			bean.setOnLoadFlag(true);
			bean.setPanelFlag("3");
			bean.setRetrnFlag("view");
			str = "view";
		} else {
			getNavigationPanel(1);
			model.getRecords(bean, request);
			reset();
			bean.setOnLoadFlag(false);
			bean.setPanelFlag("1");
			bean.setRetrnFlag("view");

			str = "view";
		}
		model.terminate();
		return str;
	}

	public String cancelSec() throws Exception {
		getNavigationPanel(1);
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		reset();
		bean.setOnLoadFlag(false);
		model.terminate();
		bean.setPanelFlag("1");
		bean.setRetrnFlag("view");
		return "view";
	}

	/**
	 * following function is called when add new record is clicked in the jsp
	 * page
	 * 
	 * @returnc
	 */
	public String save() throws Exception {
		// Default Method with Edit modeCode(3)
		getNavigationPanel(3);
		bean.setPanelFlag("3");
		boolean result;
		String str;
		bean.setOnLoadFlag(true);
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);

		if (bean.getPurposeCode().equals("") || bean.getPurposeCode() == null) {
			result = model.addTravelPurpose(bean);
			if (result) {
				addActionMessage(getMessage("save"));
				bean.setPageFlag("true");
				bean.setRetrnFlag("view");
				str = "view";
			} else {
				addActionMessage(getMessage("duplicate"));
				bean.setCancelFlg("false");
				getNavigationPanel(2);

				bean.setPanelFlag("2");
				bean.setRetrnFlag("success");
				str = "success";
			}
		} else {
			logger.info("==========in update========");
			result = model.modTravelPurpose(bean);
			if (result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				bean.setPageFlag("true");

				bean.setPanelFlag("3");
				bean.setRetrnFlag("view");
				str = "view";
			} else {
				addActionMessage(getMessage("duplicate"));
				bean.setCancelFlg("false");
				getNavigationPanel(2);

				bean.setPanelFlag("2");
				bean.setRetrnFlag("success");

				str = "success";
			}

		}

		model.getRecords(bean, request);
		bean.setEditModeFlag(false);
		bean.setOnLoadFlag(true);
		logger.info("dhth----hgghj---"+bean.isOnLoadFlag());
		model.terminate();
		return str;

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
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		boolean result = model.deleteTravelPurpose(bean);
		logger.info("result in Delete---->" + result);

		if (result) {
			addActionMessage(getMessage("delete"));
			bean.setOnLoadFlag(false);
			reset();
		} else {
			addActionMessage(getMessage("del.error"));
		}

		//model.getRecords(bean, request);
		input();
		model.terminate();

		bean.setPanelFlag("1");
		bean.setRetrnFlag("view");
		return "view";
	}

	/**
	 * following function is called to reset the fields.
	 * 
	 * @return
	 */
	public String reset() {

		bean.setPurposeCode("");
		bean.setPurposeName("");
		bean.setDescription("");
		bean.setStatus("");
		bean.setHdeleteCode("");

		return "success";
	}

	public String report() throws Exception {

		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		String[] label = { "Sr.No", getMessage("trv.name"), getMessage("desc"),
				getMessage("status") };
		model.getReport(bean, request, response, context, label);
		bean.setSaveFlag(true);
		model.getRecords(bean, request);
		model.terminate();
		return null;
	}

	public String callPage1() throws Exception {
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		bean.setPageFlag("true");
		getNavigationPanel(Integer.parseInt(bean.getPanelFlag()));
		model.getRecords(bean, request);
		model.terminate();
		return bean.getRetrnFlag();
	}

	/*public String callPage2() throws Exception {
		getNavigationPanel(1);
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setPageFlag(true);
		model.terminate();

		return "success";
	}*/

	/**
	 * following function is called to display all the records when the link is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		/*if(bean.getPageFlag().equals("true")){
			getNavigationPanel(2);
			model.getRecords(bean, request);
		}
		else{
			getNavigationPanel(1);
			model.getRecords(bean, request);
		}
		model.getRecords(bean, request);
		model.terminate();

		return "success";*/

		getNavigationPanel(Integer.parseInt(bean.getPanelFlag()));
		model.getRecords(bean, request);
		model.terminate();
		return bean.getRetrnFlag();
	}

	public String callPageView() throws Exception {

		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		/*if(bean.getPageFlag().equals("true")){			
			model.getRecords(bean, request);
			getNavigationPanel(3);
			logger.info("--------bean.getPageFlag()----------"+bean.getPageFlag());
		}
		else{
			getNavigationPanel(1);
			model.getRecords(bean, request);
		}
		//model.getRecords(bean, request);
		model.terminate();

		return "view";*/

		getNavigationPanel(Integer.parseInt(bean.getPanelFlag()));
		model.getRecords(bean, request);
		model.terminate();
		return bean.getRetrnFlag();

	}

	/**
	 * following function is called when
	 * 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		getNavigationPanel(2);
		logger.info("Calforedit inside Travel Purpose ");
		bean.setCancelFlg("false");
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		model.getTravelPurposeOnDoubleClick(bean);
		model.getTravelPurpose(bean);
		model.getRecords(bean, request);
		bean.setOnLoadFlag(true);
		bean.setDblFlag(true);
		bean.setModFlag(false);
		bean.setPageFlag("true");
		model.terminate();
		bean.setPanelFlag("2");
		bean.setRetrnFlag("success");

		return "success";

	}

	public String delete1() throws Exception {
		/* Default Method with save modeCode(2) */
		logger.info("In Delete---------->");
		getNavigationPanel(1);
		boolean result;
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		//callPage2();
		String[] code = request.getParameterValues("hdeleteCode");
		result = model.delChkdRec(bean, code);
		if (result) {
			prepare_withLoginProfileDetails();

			addActionMessage(getText("delMessage", ""));
			model.getRecords(bean, request);
			bean.setOnLoadFlag(false);
			bean.setPageFlag("true");
			reset();
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}

		model.getRecords(bean, request);

		bean.setPanelFlag("1");
		bean.setRetrnFlag("view");
		return "view";
	}

	/**
	 * 
	 * The Following Method is used to display Search Window to get Record to
	 * modify
	 */

	public String f9Action() throws Exception {

		String query = " SELECT  NVL(PURPOSE_NAME,' '),DECODE(PURPOSE_STATUS,'A','Active','D','Deactive') ,PURPOSE_ID FROM HRMS_TMS_PURPOSE ORDER BY PURPOSE_NAME";

		String[] headers = { getMessage("trv.name"), getMessage("status") };

		String[] headerwidth = { "20", "20" };

		String fieldNames[] = { "purposeName", "Status", "purposeCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlage = "true";

		String submitToMethod = "TravelPurpose_details.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}

	public String details() {
		getNavigationPanel(3);
		bean.setOnLoadFlag(true);
		TravelPurposeModel model = new TravelPurposeModel();
		model.initiate(context, session);
		model.getTravelPurposeRec(bean);
		model.getRecords(bean, request);
		model.terminate();
		bean.setPanelFlag("3");
		bean.setRetrnFlag("view");
		return "view";
	}
}
