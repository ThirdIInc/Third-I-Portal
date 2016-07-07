package org.struts.action.LMS;

import org.paradyne.bean.LMS.AccommodationMasterBean;
import org.paradyne.bean.LMS.HouseMasterBean;
import org.paradyne.model.LMS.AccommodationMasterModel;
import org.paradyne.model.LMS.HouseMasterModel;
import org.struts.lib.ParaActionSupport;

public class AccommodationMasterAction extends ParaActionSupport{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ColonyMasterAction.class);
	
	AccommodationMasterBean bean;
	public void prepare_local() throws Exception {
		bean = new AccommodationMasterBean();

		bean.setMenuCode(1151);
		getNavigationPanel(1);
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}
	/**
	 * @return the bean
	 */
	public AccommodationMasterBean getBean() {
		return bean;
	}
	/**
	 * @param bean the bean to set
	 */
	public void setBean(AccommodationMasterBean bean) {
		this.bean = bean;
	}
	/**
	 * @param logger the logger to set
	 */
	public static void setLogger(org.apache.log4j.Logger logger) {
		AccommodationMasterAction.logger = logger;
	}
	
	public String input() throws Exception {

		try {
			AccommodationMasterModel model = new AccommodationMasterModel();
			model.initiate(context, session);
			model.getList(bean, request);
			model.terminate();
			getNavigationPanel(1);
			//getNavigationPanel(7);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
	
	public String selectCheckOut(){
		//reset();
		getNavigationPanel(7);
		return SUCCESS;
	}
	
	public String addNew() {
		//reset();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	
	public String f9Employee() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "select EMP_ID,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,EMP_TOKEN,EMP_RANK,EMP_CENTER "
			+" from hrms_emp_offc "
			+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) " 
			+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) order by EMP_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Employee Id", "Employee Name","Designation","Branch","Token" };

		String[] headerWidth = { "10", "30","20","20" ,"20"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "empID", "empName" ,"designationName","branchName","empToken"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1 ,2, 3, 4};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String f9Colony() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//String query = "SELECT  COLONY_ID,COLONY_NAME FROM LMS_COLONY  ORDER BY COLONY_ID ";
		
		String query="SELECT  LMS_COLONY.COLONY_ID,COLONY_NAME,LMS_HOUSE.HOUSE_NO ,LMS_HOUSE.HOUSE_ADDRESS,LMS_HOUSE.LMS_HOUSE_ID FROM LMS_COLONY "  
			 + " INNER JOIN LMS_HOUSE on(LMS_COLONY.COLONY_ID=LMS_HOUSE.COLONY_ID) "
			 + " ORDER BY COLONY_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Colony Id", "Colony Name","House Number","House Address","House ID" };

		String[] headerWidth = { "10", "30","20","30","10" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "colonyID", "colonyName" ,"houseNo" ,"houseAddr" ,"houseID"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1,2,3,4 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	
	public String f9House() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "select LMS_HOUSE_ID, HOUSE_NO, HOUSE_ADDRESS from lms_house order by LMS_HOUSE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "House Id", "House Number" ,"House Address"};

		String[] headerWidth = { "30", "30" ,"40"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "houseID", "houseNo","houseAddr" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1,2  };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String save() {
		AccommodationMasterModel model = new AccommodationMasterModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getAccommodationId().equals("")) {
			result = model.save(bean);

			if (result) {
				addActionMessage(getMessage("save"));
			} else {
				addActionMessage("House already allocatted Please select another house");
			}
		} else {
			result = model.update(bean);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				//addActionMessage("duplicate record found");
				addActionMessage("House already allocatted Please select another house");
			}// end of else
		}
		//getDetails();
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}
	
	
	public String checkOutDatasave() {
		AccommodationMasterModel model = new AccommodationMasterModel();
		model.initiate(context, session);
		boolean result;
		if (bean.getAccommodationId()!=null) {
			result = model.saveCheckOutData(bean);

			if (result) {
				addActionMessage(getMessage("save"));
			} else {
				addActionMessage("duplicate record found");
			}
		} 
		//getDetails();
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String edit() {

		try {
			request.setAttribute("valueRadioStatus", bean.getRadioValue());
			request.setAttribute("indFamRadioStatus", bean.getRadio1Value());
			request.setAttribute("accId", bean.getAccommodationId());
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
	}
	
	public String callPage() throws Exception {
		try {
			input();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}
	

	public String dblClickItt() {
		AccommodationMasterModel model = new AccommodationMasterModel();
		model.initiate(context, session);
		model.dblClickItt(bean,request);
		model.terminate();

		getNavigationPanel(3);
		bean.setEnableAll("N");
		return SUCCESS;
	}
	
	public String checkOutData() {
		AccommodationMasterModel model = new AccommodationMasterModel();
		model.initiate(context, session);
		model.dblClickItt(bean,request);
		model.terminate();

		getNavigationPanel(2);
		//bean.setEnableAll("N");
		return "successCheck";
	}
	
	
	public String search() {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "select ACCOM_ID,LMS_ACCOMMODATION.EMP_ID,TO_CHAR(EMP_TOKEN ||'-' ||EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME), "
			+" TO_CHAR(LMS_COLONY.COLONY_NAME),h1.HOUSE_NO from LMS_ACCOMMODATION "
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=LMS_ACCOMMODATION.EMP_ID) "
			+" LEFT JOIN LMS_COLONY ON(LMS_COLONY.COLONY_ID=LMS_ACCOMMODATION.ACCOM_COLONY_ID) "
			+" LEFT JOIN LMS_HOUSE h1 ON(h1.LMS_HOUSE_ID=LMS_ACCOMMODATION.ACCOM_HOUSE_ID) "
			+" order by ACCOM_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Accommodation Id", "empID","Employee " ,"Colony","House Number"};

		String[] headerWidth = { "10", "30" ,"20","20","20"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "AccommodationId", "empID" ,"empName","colonyID" ,"houseNo"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE:
		 * COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = { 0, 1,2 ,3 ,4};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AccommodationMaster_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String back() {

		AccommodationMasterModel model = new AccommodationMasterModel();
		model.initiate(context, session);
		model.getList(bean, request);
		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}
	
	public String reset() {
		bean.setAccommodationId("");
		bean.setEmpID("");
		bean.setEmpName("");
		bean.setBranchName("");
		bean.setDesignationName("");
		bean.setAmount("");
		bean.setRentAmount("");
		bean.setColonyName("");
		bean.setColonyID("");
		bean.setHouseID("");
		bean.setHouseNo("");
		bean.setHouseAddr("");
		bean.setValidityDate("");
		bean.setFreeAccommodations("");
		bean.setIndividual("");
		
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	
	
	public String resetCheckOutData() {
		AccommodationMasterModel model = new AccommodationMasterModel();
		bean.setCheckOutDate("");
		bean.setReasonCheckOut("");
		bean.setRemarkCheckOut("");
		model.getDetails(bean,request);
		getNavigationPanel(2);
		return "successCheck";
	}
	
	public String getDetails() {
		AccommodationMasterModel model = new AccommodationMasterModel();
		model.initiate(context, session);
		model.getDetails(bean,request);
		model.terminate();

		getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String delete() {
		boolean result;
		AccommodationMasterModel model = new AccommodationMasterModel();
		model.initiate(context, session);
		result = model.delete(bean, request);
		if (result) {
			addActionMessage("Record Deleted successfully.");

		}

		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}
	
}
