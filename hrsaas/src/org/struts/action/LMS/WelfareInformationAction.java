package org.struts.action.LMS;

import org.paradyne.bean.LMS.WelfareInformationBean;
import org.paradyne.model.LMS.WelfareInformationModel;

import org.struts.lib.ParaActionSupport;

public class WelfareInformationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(WelfareInformationAction.class);

	WelfareInformationBean bean;

	public WelfareInformationBean getBean() {
		return bean;
	}

	public void setBean(WelfareInformationBean bean) {
		this.bean = bean;
	}

	public void prepare_local() throws Exception {

		bean = new WelfareInformationBean();
		bean.setMenuCode(1157);
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

	public String input() throws Exception {

		try {
			showSetting();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String showSetting() throws Exception{
		try {
			WelfareInformationModel model = new WelfareInformationModel();
			model.initiate(context, session);
			model.showSetting(bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return SUCCESS;
		  
	 }
	
	public String save() {
		WelfareInformationModel model = new WelfareInformationModel();
		model.initiate(context, session);
		boolean result;
	
			result = model.save(bean, request);

			if (result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage("duplicate record found");
			}
		
		getNavigationPanel(1);
		bean.setEnableAll("Y");

		return SUCCESS;
	}

	public String reset() throws Exception {
		getNavigationPanel(1);
		bean.setDrinkWaterFacilityFlag("");
		bean.setChildMindingFacilityFlag("");
		bean.setLockerFacilityFlag("");
		bean.setMealFacilityFlag("");
		bean.setMedicalFacilityFlag("");
		bean.setOnsiteAccomodationFlag("");
		bean.setFullTimeFlag("");
		bean.setOffsiteAccomodationFlag("");
		bean.setPartTimeFlag("");
		bean.setDocFullTimeFlag("");
		bean.setNurseFullTimeFlag("");
		bean.setDocPartTimeFlag("");
		bean.setNursePartTimeFlag("");
		bean.setNumberOfChangingRooms("");
		bean.setNumberOfRestRooms("");
		bean.setNumberOfMensToilet("");
		bean.setNumberOfWomensToilet("");
		bean.setNumberOfMensUrinals("");
		bean.setNumberOfClinics("");
		bean.setNumberOfEmergencyRooms("");
		bean.setNumberOfAmbulance("");
		bean.setNumberOfUniforms("");
		bean.setNumberOfRainCoats("");

		return SUCCESS;
	}

}
