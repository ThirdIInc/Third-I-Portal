package org.struts.action.D1;

import org.paradyne.bean.D1.OfficialDetailSettingsBean;
import org.paradyne.bean.settings.SuperSettings;
import org.paradyne.model.D1.DepartmentNoMasterModel;
import org.paradyne.model.D1.OfficialDetailSettingsModel;
import org.paradyne.model.LMS.AccommodationMasterModel;
import org.paradyne.model.settings.SuperSettingsModel;
import org.struts.lib.ParaActionSupport;

public class OfficialDetailSettingsAction extends ParaActionSupport {
	OfficialDetailSettingsBean bean;

	
	
	/**
	 * @return the bean
	 */
	public OfficialDetailSettingsBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(OfficialDetailSettingsBean bean) {
		this.bean = bean;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	/**
	 * Called on load Set Payroll Settings fields on load
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		model.hasData(bean, request);
		model.terminate();
	}
	
	public String showAppSetting() {
		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		model.showOfficialDetailSetting(bean);
		model.terminate();
		return SUCCESS;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new OfficialDetailSettingsBean();
		bean.setMenuCode(1161);
		
	}
	
	public String input() throws Exception {

		try {
			getNavigationPanel(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "success";
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Saving Records under Official Details settings
	 * 
	 * @return String
	 */
	/*public String saveApplicationSetting() {
		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		boolean flag=false;
		// save
		System.out.println("here "+bean.getHiddenCode());
		if(bean.getHiddenCode().equals("")){
			flag = model.saveOfficialDetailSetting(bean);
			if (flag){
				addActionMessage("Settings Saved Successfully");
			}else{
				addActionMessage("Settings Cant be Saved.");
			}
		}else{
			flag = model.updateOfficialDetailSetting(bean);
			if (flag){
				addActionMessage("Settings updated Successfully");
			}else{
				addActionMessage("Settings can not be updated.");
			}
		}
		// setting after save
		model.showOfficialDetailSetting(bean);
		model.terminate();
		return SUCCESS;
	}*/
	
	
	public String saveApplicationSetting() throws Exception {
		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		boolean result;
		System.out.println("Im in save>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>."+bean.getConfigId());
		if(bean.getConfigId().equals("")) {
			result = model.saveOfficialDetailSetting(bean);
			System.out.println("Im in save>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			if(result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "success";
			} else {
				addActionMessage(getMessage("duplicate"));
				//reset();// new added
				getNavigationPanel(3);
				return "success";
			}// end of else
		} else {
			result = model.updateOfficialDetailSetting(bean);
			model.terminate();
			if(result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "success";
			} else {
				addActionMessage(getMessage("duplicate"));
				//reset();// new added
				getNavigationPanel(3);
				return "success";
			}// end of else
		}// end of else
		
		
		//model.hasData(dptMaster, request);
		//model.terminate();
		//return reset();
	}
	
	
	public String f9Divaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		System.out.println("m in 3rd f9 block>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		
		
		String query = " SELECT  DIV_NAME,DIV_ID FROM HRMS_DIVISION WHERE IS_ACTIVE='Y'";
		
		if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
			query+= " and DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "bean.divName","bean.deptDivCode"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0,1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = " ";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		//bean.setMasterMenuCode(42);

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return "input";
		} catch(Exception e) {
			return null;
		}
	}
	public String callPage() throws Exception {

		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		model.hasData(bean, request);
		getNavigationPanel(1);
		model.terminate();
		bean.setEnableAll("N");
		return SUCCESS;
	}
	public String reset(){
		try{
			bean.setDivD1Flag("");
			bean.setDivName("");
			bean.setEmergencyFlag("");
			bean.setExeFlag("");
			bean.setSsnFlag("");
			bean.setDeptDivCode("");
			bean.setSinFlag("");
			bean.setRegionFlag("");
			bean.setDeptNoFlag("");
			bean.setConfigId("");
		}catch(Exception e){
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	public String delete() throws Exception {
		System.out.println("in delete");
		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		boolean result = model.deleteDept(bean);
		model.hasData(bean, request);
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		getNavigationPanel(1);
		bean.setDivD1Flag("");
		bean.setDivName("");
		bean.setEmergencyFlag("");
		bean.setExeFlag("");
		bean.setSsnFlag("");
		bean.setDeptDivCode("");
		bean.setSinFlag("");
		bean.setRegionFlag("");
		bean.setDeptNoFlag("");
		input();
		return "input";
	}

	/**
	 * To delete one or more records from the list
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		System.out.println("in delete");
		String code[] = request.getParameterValues("hdeleteCode");

		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		
		boolean result = model.deleteDept(bean, code);

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.hasData(bean, request);
		model.terminate();
		getNavigationPanel(1);
		bean.setConfigList(null);
		prepare_withLoginProfileDetails();
		
		//input();
		return "input";
	}
	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		model.calforedit(bean);
		deptRecord();

		model.hasData(bean, request);
		getNavigationPanel(3);
		bean.setEnableAll("N");
		model.terminate();
		return SUCCESS;
	}
	
	
	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deptRecord() throws Exception {
		OfficialDetailSettingsModel model = new OfficialDetailSettingsModel();
		model.initiate(context, session);
		model.getDeptRecord(bean);
		getNavigationPanel(3);
		model.terminate();
		return SUCCESS;
	}
	
	public String searchAction() throws Exception {
		System.out.println("In search query...>> xdfdg ddsfdfgdfhbdtra ha>>>>>>>>>>>>");
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		

		String query = "select CONF_DIV_CODE,HRMS_DIVISION.DIV_NAME,CONFIG_ID" 
		    +" from HRMS_D1_OFFICE_CONF"
			+" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_D1_OFFICE_CONF.CONF_DIV_CODE) "
			+" ORDER BY upper( CONFIG_ID) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {" Division Code", "Division"};

		String[] headerWidth = {"10", "60",};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */

		String[] fieldNames = { "bean.divName","bean.deptDivCode","bean.configId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1 ,2};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "OfficialDetailsSettings_deptRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	


}
