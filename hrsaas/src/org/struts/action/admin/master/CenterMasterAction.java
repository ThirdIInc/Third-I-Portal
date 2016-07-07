package org.struts.action.admin.master;

import java.util.TreeMap;

import org.paradyne.bean.admin.master.CenterMaster;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.master.CenterModel;
import org.paradyne.model.payroll.PayrollZoneMasterModel;

public class CenterMasterAction extends org.struts.lib.ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	CenterMaster cntrMaster;
	String poolDir = "";
	String fileName = "";

	
	public String input() throws Exception {
		cntrMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "centerData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		CenterModel model = new CenterModel();
		model.initiate(context, session);
		model.calforedit(cntrMaster);
		centerRecord();

		model.hasData(cntrMaster, request);
		getNavigationPanel(3);
		cntrMaster.setEnableAll("N");
		model.terminate();
		return "centerData";
	}

	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		CenterModel model = new CenterModel();
		model.initiate(context, session);
		model.hasData(cntrMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * To set the fields after search
	 * @return String
	 * @throws Exception
	 */
	public String centerRecord() throws Exception {
		CenterModel model = new CenterModel();
		model.initiate(context, session);
		model.getCenterRecord(cntrMaster);
		getNavigationPanel(3);
		model.terminate();
		return "centerData";
	}

	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		CenterModel model = new CenterModel();
		model.initiate(context, session);
		
		boolean result = false;
		
		fileName = getText("data_path") + "/datafiles/" + poolDir + "/xml/Payroll/salaryZone_branch.xml";
		
		if(!cntrMaster.getCenterID().equals("")) {
			result = model.deleteCenter(cntrMaster);
		}
		model.hasData(cntrMaster, request);
		
		model.terminate();
		
		if(result) {
			PayrollZoneMasterModel model1 = new PayrollZoneMasterModel();
			model1.initiate(context, session);
			model1.xml_salaryZoneBranch(fileName);
			addActionMessage(getMessage("delete"));
			cntrMaster.setCenterID("");
			cntrMaster.setCenterName("");
			cntrMaster.setLocId("");
			cntrMaster.setLocName("");
			cntrMaster.setAdd1("");
			cntrMaster.setAdd2("");
			cntrMaster.setAdd3("");
			cntrMaster.setCity("");
			cntrMaster.setPin("");
			cntrMaster.setTel("");
			cntrMaster.setFax("");
			cntrMaster.setEsiZone("");
			cntrMaster.setPtZone("");
			cntrMaster.setPfZone("");
			cntrMaster.setCenterAbbr("");
			cntrMaster.setIsActive("");
			cntrMaster.setCenterType("B");
			
		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		return "success";
	}

	/**
	 * To delete one or more records from the list
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		
		CenterModel model = new CenterModel();
		model.initiate(context, session);
		
		fileName = getText("data_path") + "/datafiles/" + poolDir + "/xml/Payroll/salaryZone_branch.xml";
		
		boolean result = model.deleteCenter(cntrMaster, code);
		
		if(result) {
			PayrollZoneMasterModel model1 = new PayrollZoneMasterModel();
			model1.initiate(context, session);
			
			model1.xml_salaryZoneBranch(fileName);
			
			addActionMessage(getMessage("delete"));
			
			cntrMaster.setCenterID("");
			cntrMaster.setCenterName("");
			cntrMaster.setLocId("");
			cntrMaster.setLocName("");
			cntrMaster.setAdd1("");
			cntrMaster.setAdd2("");
			cntrMaster.setAdd3("");
			cntrMaster.setCity("");
			cntrMaster.setPin("");
			cntrMaster.setFax("");
			cntrMaster.setTel("");
			cntrMaster.setEsiZone("");
			cntrMaster.setPtZone("");
			cntrMaster.setIsActive("");
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.hasData(cntrMaster, request);
		getNavigationPanel(1);
		model.terminate();

		return "success";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT CENTER_NAME,HRMS_LOCATION.LOCATION_NAME " + ", CENTER_ID FROM HRMS_CENTER "
				+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_CENTER.CENTER_LOCATION) "
				+ " WHERE HRMS_LOCATION.LOCATION_TYPE='Ci' ORDER BY upper(CENTER_NAME)";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("branch"), getMessage("city")};

		String[] headerWidth = { "50", "50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"cntrMaster_centerName", "cntrMaster_city","cntrMaster_centerID"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1,2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "CenterMaster_centerRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9city() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION where location_type='Ci' ORDER BY LOCATION_NAME";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("city")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "cntrMaster.city","cntrMaster.locId"};

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
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		cntrMaster.setMasterMenuCode(20);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public CenterMaster getCntrMaster() {
		return cntrMaster;
	}

	public Object getModel() {
		return cntrMaster;
	}

	public void prepare_local() throws Exception {
		cntrMaster = new CenterMaster();
		cntrMaster.setMenuCode(31);
		poolDir = String.valueOf(session.getAttribute("session_pool"));
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		CenterModel model = new CenterModel();
		model.initiate(context, session);

		model.hasData(cntrMaster, request);
		
		model.terminate();
		
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("zone");
		cntrMaster.setMap(map);
		dmu.terminate();
	}

	/**
	 * To generate report
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		CenterModel model = new CenterModel();
		model.initiate(context, session);
		String[]label={"Sr.No",getMessage("branch"),getMessage("city"),getMessage("address"),getMessage("telephone"),getMessage("fax"),getMessage("is.active"),getMessage("zone"),getMessage("centerType")};
		model.getReport(cntrMaster, request, response, context, session,label);
		
		model.terminate();
		return null;
	}

	/**
	 * To reset the fields
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			cntrMaster.setCenterID("");
			cntrMaster.setCenterName("");
			cntrMaster.setLocId("");
			cntrMaster.setLocName("");
			cntrMaster.setAdd1("");
			cntrMaster.setAdd2("");
			cntrMaster.setAdd3("");
			cntrMaster.setCity("");
			cntrMaster.setPin("");
			cntrMaster.setTel("");
			cntrMaster.setFax("");
			cntrMaster.setEsiZone("");
			cntrMaster.setPtZone("");
			cntrMaster.setPfZone("");
			cntrMaster.setCenterAbbr("");
			cntrMaster.setIsActive("");
			cntrMaster.setCenterType("B");
			
			getNavigationPanel(2);
		} catch(Exception e) {
			logger.error("Error in reset" + e);
		}
		return "centerData";
	}

	
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		CenterModel model = new CenterModel();
		model.initiate(context, session);
		
		boolean result;
		
		fileName = getText("data_path") + "/datafiles/" + poolDir + "/xml/Payroll/salaryZone_branch.xml";
		System.out.println("filename in center master action" +fileName);
		if(cntrMaster.getCenterID().equals("")) {
			result = model.addCenter(cntrMaster);

			if(result) {
				PayrollZoneMasterModel model1 = new PayrollZoneMasterModel();
				model1.initiate(context, session);
				model1.xml_salaryZoneBranch(fileName);
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "centerData";
				
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
				
			}// end of else
		} else {
			
			System.out.println("m in update method now &&&&>>>>>>>>>>>>>>>.");
			result = model.modCenter(cntrMaster);
			model.terminate();
			
			if(result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "centerData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(1);
				return "success";
			}// end of else
		}// end of else
		
	//	model.hasData(cntrMaster, request);
		
		//model.terminate();

		//return reset();
	}

	public void setCntrMaster(CenterMaster cntrMaster) {
		this.cntrMaster = cntrMaster;
	}
}