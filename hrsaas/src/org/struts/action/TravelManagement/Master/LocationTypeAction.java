package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.LocationType;
import org.paradyne.model.TravelManagement.Master.LocationTypeModel;

/**
 * Author Dilip 
 * modified by Dipti
 */
public class LocationTypeAction extends org.struts.lib.ParaActionSupport {

	// private static final long serialVersionUID = 7047317819789938757L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LocationTypeAction.class);

	LocationType bean;

	public void prepare_local() throws Exception {

		bean = new LocationType();
		bean.setMenuCode(774);

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		//callPage();
	}

	public Object getModel() {

		return bean;
	}

	public LocationType getBean() {
		return bean;
	}

	public void setBean(LocationType bean) {
		this.bean = bean;
	}

	public String input() throws Exception {
		//Default Method with default modeCode(1)		
		getNavigationPanel(1);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setOnLoadFlag(false);

		bean.setPanelFlag("1");
		bean.setRetrnFlag("view");

		//callPage();

		model.terminate();
		return "view";
	}

	public String addNew() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setPageFlag("true");
		//callPage();
		reset();
		bean.setPanelFlag("2");
		bean.setRetrnFlag("success");

		//bean.setLoadFlag(true);
		//bean.setFlag(true);
		//System.out.println("val of flag"+bean.getFlag());

		model.terminate();
		return "success";

	}

	public String edit() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		bean.setCancelFlg("true");
		model.getLocationType(bean);
		model.getRecords(bean, request);
		/*//model.getRecords(bean, request);
		//callPage();
		logger.info("Inside Edit----->");
		//logger.info("bean.getEditFlag()---->"+bean.getEditFlag());				
		//bean.setEditFlag(true);				
		//logger.info("bean.getEditFlag() After Setting---->"+bean.getEditFlag());
		bean.setModFlag(true);
		bean.setAddFlag(false);
		bean.setOnLoadFlag(false);*/
		bean.setPanelFlag("2");
		bean.setRetrnFlag("success");

		model.terminate();
		return "success";

	}

	/*public String cancelThrd() throws Exception{
		logger.info("Inside Cancel Third");
			getNavigationPanel(3);
			LocationTypeModel model=new LocationTypeModel();
			model.initiate(context, session);
			
			callPage();
			model.getLocationTypeEdt(bean);
			bean.setSaveFlag(true);
			bean.setModFlag(false);
			model.terminate();
			return "success";
			
		}
		
		public String cancelSec() throws Exception{
			getNavigationPanel(1);
			LocationTypeModel model=new LocationTypeModel();
			model.initiate(context, session);
			callPage();
			bean.setOnLoadFlag(true);
			bean.setSaveFlag(true);
			model.terminate();
			reset();
			return "success";
			
			
		}
		
		public String cancelFirst() throws Exception{
			getNavigationPanel(1);
			callPage();
			bean.setOnLoadFlag(true);
			logger.info("-----Reset in Cancel First------");
			reset();
			return "success";
		}
		
		public String cancelFrth() throws Exception{
			logger.info("Inside Cancel Fourth");
			getNavigationPanel(1);
			LocationTypeModel model=new LocationTypeModel();
			model.initiate(context, session);
			callPage();
			reset();
			bean.setOnLoadFlag(true);
			model.terminate();
			return "success";
			
		}*/

	public String cancelFirst() throws Exception {
		String str;
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		if (bean.getCancelFlg().equals("true")) {
			getNavigationPanel(3);
			model.getLocationTypeEdt(bean);
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

	/**
	 * following function is called when the Cancel button in the second page is clicked.
	 * @return
	 * @throws Exception
	 */
	public String cancelSec() throws Exception {
		getNavigationPanel(1);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setOnLoadFlag(false);
		reset();

		bean.setPanelFlag("1");
		bean.setRetrnFlag("view");
		model.terminate();
		return "view";
	}

	/**
	 * following function is called when add new record is clicked in the jsp page
	 * @return
	 */
	public String save() throws Exception {
		//Default Method with Edit modeCode(3)
		getNavigationPanel(3);

		bean.setPanelFlag("3");

		boolean result;
		String str;
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);

		{
			if (bean.getLocationCode().equals("")) {
				result = model.addLocationType(bean);
				if (result) {
					addActionMessage(getMessage("save"));
					getNavigationPanel(3);
					bean.setOnLoadFlag(true);
					bean.setRetrnFlag("view");
					str = "view";
					//callPage();
				} else {
					addActionMessage(getMessage("duplicate"));
					bean.setCancelFlg("false");
					getNavigationPanel(2);
					bean.setPanelFlag("2");
					bean.setRetrnFlag("success");
					str = "success";
					//callPage();
				}
			} else {
				result = model.modLocationType(bean);
				if (result) {
					addActionMessage(getMessage("update"));
					getNavigationPanel(3);
					bean.setOnLoadFlag(true);

					bean.setPanelFlag("3");
					bean.setRetrnFlag("view");
					str = "view";
				} else {
					addActionMessage(getMessage("duplicate"));
					bean.setCancelFlg("false");
					getNavigationPanel(2);
					str = "success";

					bean.setPanelFlag("2");
					bean.setRetrnFlag("success");

					//getNavigationPanel(2);
					//bean.setLoadFlag(true);
					//bean.setFlag(true);
					//callPage();
				}

			}

			//logger.info("value of save flag---->"+bean.getSaveFlag());
			//bean.setLoadFlag(false);
			//bean.setAddFlag(true);
			//bean.setSaveFlag(true);				
			//logger.info("value of Save Flag After Setting--->"+bean.getSaveFlag());

		}
		model.getRecords(bean, request);
		bean.setOnLoadFlag(true);
		model.terminate();
		return str;

	}

	/**
	 * following function is called when update button is clicked in the jsp page  
	 * @return
	 */
	public String update() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		boolean result = model.modLocationType(bean);
		if (result) {
			addActionMessage(getMessage("update"));
			//callPage();
		} else {
			addActionMessage(getMessage("duplicate"));
			//callPage();
		}
		bean.setModFlag(true);
		bean.setSaveFlag(true);
		model.terminate();
		bean.setPanelFlag("2");
		bean.setRetrnFlag("success");

		return "success";
	}

	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		boolean result = model.deleteLocationType(bean);
		logger.info("result in Delete---->" + result);
		if (result) {
			addActionMessage(getMessage("delete"));
			reset();
			bean.setOnLoadFlag(false);
		} else {
			addActionMessage(getMessage("del.error"));
		}
		model.getRecords(bean, request);
		model.terminate();
		return input();
	}

	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset() {

		bean.setLocationCode("");
		bean.setLocationName("");
		bean.setDescription("");
		bean.setStatus("");

		return "success";
	}

	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String getRecord() throws Exception {

		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		model.getLocationType(bean);
		bean.setSaveFlag(true);
		model.getRecords(bean, request);
		model.terminate();
		return "success";
	}

	public String report() throws Exception {

		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		String[] label = { "Sr.No", getMessage("location.name"),
				getMessage("desc"), getMessage("status") };
		model.getReport(bean, request, response, context, label);
		bean.setSaveFlag(true);
		model.getRecords(bean, request);
		model.terminate();
		return null;
	}

	public String callPage() throws Exception {
		logger
				.info("--------bean.getPageFlag()----------"
						+ bean.getPageFlag());
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		/*if(bean.getPageFlag().equals("true")){
			getNavigationPanel(2);
		}
		else{
			getNavigationPanel(1);
		}*/

		getNavigationPanel(Integer.parseInt(bean.getPanelFlag()));
		model.getRecords(bean, request);
		model.terminate();
		return bean.getRetrnFlag();

	}

	public String callPageView() throws Exception {

		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		/*if(bean.getPageFlag().equals("true")){			
			getNavigationPanel(3);
			
		}
		else{
			getNavigationPanel(1);
		}*/

		getNavigationPanel(Integer.parseInt(bean.getPanelFlag()));
		model.getRecords(bean, request);
		model.terminate();
		return bean.getRetrnFlag();

	}

	public String callPage1View() throws Exception {
		getNavigationPanel(1);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		bean.setPageFlag("true");

		getNavigationPanel(Integer.parseInt(bean.getPanelFlag()));
		model.getRecords(bean, request);
		model.terminate();
		return bean.getRetrnFlag();

	}

	public String callPage1() throws Exception {
		getNavigationPanel(1);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		bean.setPageFlag("true");
		getNavigationPanel(Integer.parseInt(bean.getPanelFlag()));
		model.getRecords(bean, request);
		model.terminate();
		return bean.getRetrnFlag();
	}

	/*public String callPage2() throws Exception {
		getNavigationPanel(1);
		LocationTypeModel model=new LocationTypeModel();
		model.initiate(context, session);
		model.getRecords(bean,request);
		bean.setPageFlag(true);
		model.terminate();
		
		
		return "success";
	}
	
	
	 *//**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	/*
				public String callPage() throws Exception {
					LocationTypeModel model=new LocationTypeModel();
					model.initiate(context, session);
					model.getRecords(bean,request);
					model.terminate();		
					
					return "view";
				}*/

	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		getNavigationPanel(2);
		logger.info("Calforedit inside indus ");
		bean.setCancelFlg("false");
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		model.getLocationTypeOnDoubleClick(bean);
		model.getLocationType(bean);
		model.getRecords(bean, request);
		bean.setDblFlag(true);
		bean.setModFlag(false);
		//bean.setFlag("true");
		model.terminate();
		bean.setPanelFlag("2");
		bean.setRetrnFlag("success");

		return "success";

	}

	public String delete1() throws Exception {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		boolean result;
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		String[] code = request.getParameterValues("hdeleteCode");
		result = model.delChkdRec(bean, code);
		if (result) {
			prepare_withLoginProfileDetails();
			addActionMessage(getText("delMessage", ""));
			model.getRecords(bean, request);
			bean.setOnLoadFlag(false);
			reset();
		} else
			addActionMessage(getMessage("multiple.del.error"));
		model.getRecords(bean, request);
		reset();

		bean.setPanelFlag("1");
		bean.setRetrnFlag("view");
		return "view";
	}

	/**
	 * 	 
	 *  The Following Method is used to display Search Window to get Record to modify 
	 */

	public String f9Action() throws Exception {

		String query = " SELECT NVL(LOCATION_TYPE_NAME,' '),DECODE(LOCATION_TYPE_STATUS,'A','Active','D','Deactive'),LOCATION_TYPE_ID  FROM HRMS_TMS_LOCATION_TYPE ORDER BY LOCATION_TYPE_NAME";

		String[] headers = { getMessage("location.name"), getMessage("status") };
		String[] headerwidth = { "20", "20" };

		String fieldNames[] = { "locationName", "Status", "locationCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlage = "true";

		String submitToMethod = "LocationType_details.action";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}

	public String details() {
		getNavigationPanel(3);
		bean.setOnLoadFlag(true);
		LocationTypeModel model = new LocationTypeModel();
		model.initiate(context, session);
		model.getLocationTypeRec(bean);
		bean.setSaveFlag(true);
		model.getRecords(bean, request);
		model.terminate();

		bean.setPanelFlag("3");
		bean.setRetrnFlag("view");

		return "view";
	}
}
