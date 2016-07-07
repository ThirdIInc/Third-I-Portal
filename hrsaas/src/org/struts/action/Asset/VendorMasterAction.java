package org.struts.action.Asset;

import org.paradyne.bean.Asset.VendorMaster;
import org.paradyne.model.Asset.VendorMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author krishna
 * 
 */

public class VendorMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VendorMasterAction.class);

	/**
	 * Declare VendorMaster reference variable
	 */
	VendorMaster vendorMaster;

	/**
	 * Over ridden prepare_local() method
	 */
	public void prepare_local() throws Exception {
		vendorMaster = new VendorMaster();
		vendorMaster.setMenuCode(639);

	}

	public Object getModel() {
		return vendorMaster;
	}
	public String input() throws Exception {
		getNavigationPanel(1);
		return "success";
	}
	
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return "success";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "Data";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	/*
	 * method name : save 
	 * purpose : to add new record or to modify the existing record 
	 * return type : String 
	 * parameter : none
	 */

	public String save() {
		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		boolean result;
		if (vendorMaster.getVendorCode().equals("")) {
			result = model.add(vendorMaster);
			if (result) {
				addActionMessage("Record saved successfully.");
				//reset();
				getNavigationPanel(3);
			}// end of if
			else {
				addActionMessage("Duplicate entry found.");
				model.Data(vendorMaster, request);
				getNavigationPanel(1);
				reset();
				return "success";
				
			}// end of else
		}// end of if
		else {
			result = model.mod(vendorMaster);
			if (result) {
				addActionMessage("Record updated Successfully.");
				//reset();
				getNavigationPanel(3);
			}// end of if
			else {
				getNavigationPanel(1);
				model.Data(vendorMaster, request);
				addActionMessage("Duplicate entry found.");
				reset();
				return "success";
				
			}// end of else
		}// end of else
		model.calforedit(vendorMaster,vendorMaster.getVendorCode());	
		model.terminate();
		return "Data";
	}

	/*
	 * method name : setData 
	 * purpose : for setting the bean properties 
	 * return type : String 
	 * parameter : none
	 */
	public String setData() {

		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		model.setData(vendorMaster);
		model.terminate();
		getNavigationPanel(3);
		return "Data";
	}

	/*
	 * method name : setState 
	 * purpose : for setting the state property of bean
	 * return type : String 
	 * parameter : none
	 */

	public String setState() {
		try {
			VendorMasterModel model = new VendorMasterModel();
			model.initiate(context, session);
			model.setState(vendorMaster);
			model.terminate();
			getNavigationPanel(2);
			vendorMaster.setEnableAll("Y");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Data";
	}

	/*
	 * method name : delete 
	 * purpose : to delete the selected record return type :
	 * String 
	 * parameter : none
	 */
	public String delete() {
		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		boolean result = model.delete(vendorMaster);
		if (result) {
			addActionMessage("Record Deleted Successfully.");
			reset();
		}// end of if
		else {
			addActionMessage("This record is referenced in other resources.So can't delete.");
			reset();
		}// end of else
		model.Data(vendorMaster, request);
		model.terminate();
		getNavigationPanel(1); 
		return "success";
	}

	/*
	 * method name : reset
	 * purpose : to reset all the form fields and set all values to empty strings
	 * return type : String 
	 * parameter : none
	 */

	public String reset() {
		vendorMaster.setVendorCode("");
		vendorMaster.setVendorName("");
		vendorMaster.setVendorEmail("");
		vendorMaster.setVendorCon("");
		vendorMaster.setVendorCty("");
		vendorMaster.setVendorState("");
		vendorMaster.setVendorAdd("");
		vendorMaster.setCtyId("");
		vendorMaster.setStateId("");
		vendorMaster.setShow("");
		vendorMaster.setHdeleteCode("");
		vendorMaster.setMyPage("");
		vendorMaster.setHiddencode("");
		vendorMaster.setLocParentCode("");
		vendorMaster.setPinCode("");
		vendorMaster.setVendortype("");
		getNavigationPanel(2);
		return "Data";
	}

	/*
	 * method name : f9action 
	 * purpose : to show all the details for the selected application 
	 * return type : String 
	 * parameter : none
	 */

	public String f9action() throws Exception {

		String query = "SELECT VENDOR_CODE,NVL(VENDOR_NAME,''),NVL(VENDOR_ADDRESS,''),NVL(VENDOR_CONTACT_NO,''),"
				+ " NVL(VENDOR_CITY,''),NVL(VENDOR_STATE,''), NVL(L1.LOCATION_NAME,'') AS CITY,NVL(L2.LOCATION_NAME,'') AS CITY FROM HRMS_VENDOR"
				+ " LEFT JOIN HRMS_LOCATION L1 ON(HRMS_VENDOR.VENDOR_CITY=L1.LOCATION_CODE)"
				+ " LEFT JOIN HRMS_LOCATION L2 ON(HRMS_VENDOR.VENDOR_STATE=L2.LOCATION_CODE) ORDER BY VENDOR_CODE ";

		String[] headers = { "Vendor Code", getMessage("vendor.name")};

		String[] headerWidth = { "30", "50" };

		String[] fieldNames = { "vendorCode", "vendorName", "vendorAdd",
				"vendorCon", "ctyId", "stateId", "vendorCty", "vendorState" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };

		String submitFlag = "true";

		String submitToMethod = "VendorMaster_setData.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * method name : f9city 
	 * purpose : to show all the details for the selected
	 * application return type : String 
	 * parameter : none
	 */
	public String f9city() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT  LOCATION_CODE,LOCATION_NAME,LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE='Ci' ORDER BY LOCATION_NAME";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "City Code", getMessage("vendor.city") };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "ctyId", "vendorCty", "locParentCode" };

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
		String submitToMethod = "VendorMaster_setState.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	/*
	 * method name : f9Stateaction 
	 * purpose : to show all the details for the
	 * selected application 
	 * return type : String parameter : none
	 */
	public String f9Stateaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT LOCATION_CODE, LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 1 AND LOCATION_CODE="
				+ vendorMaster.getLocParentCode();

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "State Code", "State Name" };

		String[] headerWidth = { "20", "80" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "stateId", "vendorState" };

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

	/*
	 * method name : prepare_withLoginProfileDetails 
	 * purpose : to retrieve the details at the time page loading 
	 * return type : void 
	 * parameter : none
	 */

	public void prepare_withLoginProfileDetails() throws Exception {
		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		model.Data(vendorMaster, request);
		model.terminate();
	}

	/*
	 * method name : callPage
	 * purpose : to displays the records in the form
	 * return type : String 
	 * parameter : none
	 */
	public String callPage() throws Exception {
		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		model.Data(vendorMaster, request);
		model.terminate();
		return SUCCESS;

	}

	/*
	 * method name : calforedit
	 * purpose : to edit the records 
	 * return type : String 
	 * parameter : none
	 * throws : Exception
	 */
	public String calforedit() throws Exception {
		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		model.calforedit(vendorMaster,vendorMaster.getHiddencode());		
		model.terminate();
		getNavigationPanel(3);
		vendorMaster.setEnableAll("N");
		return "Data";
	}

	/*
	 * method name : calfordelete 
	 * purpose : to delete the selected Record 
	 * returntype : String 
	 * parameter : none
	 */
	public String calfordelete() {
		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		boolean result;
		result = model.calfordelete(vendorMaster);
		if (result) {
			addActionMessage(getText("Record  Deleted Successfully."));
			vendorMaster.setVendorCode("");
			vendorMaster.setVendorName("");
			vendorMaster.setVendorAdd("");
			vendorMaster.setVendorCon("");
			vendorMaster.setVendorEmail("");
			vendorMaster.setCtyId("");
			vendorMaster.setStateId("");
			vendorMaster.setVendorCty("");
			vendorMaster.setVendorState("");
			vendorMaster.setMyPage("");
			vendorMaster.setHiddencode("");
			vendorMaster.setShow("");

		}// end of if
		else {
			addActionMessage("Record can not be deleted.");
		}// end of else

		model.Data(vendorMaster, request);
		model.terminate();

		return "success";
	}

	/*
	 * method name : delete1 
	 * purpose : to delete the record selected by check on the Check Box 
	 * return type : String 
	 * parameter : none
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		boolean result = model.deletecheckedRecords(vendorMaster, code);
		if (result) {
			addActionMessage(getText("delMessage", ""));
		}// end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}// end of else

		model.Data(vendorMaster, request);
		model.terminate();
		reset();
		getNavigationPanel(1);
		return "success";

	}

	/*
	 * method name : report 
	 * purpose : to generate the report for the selected application
	 *  return type : String 
	 *  parameter : none
	 */
	public String report() {
		VendorMasterModel model = new VendorMasterModel();
		model.initiate(context, session);
		 String[] headers = { "Sr no", "Code ", "Name", "Address",
					"Contact No", "E-mail", "City", "State", "Pin Code" };
		model.report(vendorMaster, request,response,headers);
		model.terminate();
		return null;

	}
}
