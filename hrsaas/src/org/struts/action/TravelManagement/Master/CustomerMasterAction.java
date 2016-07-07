/*
 * Added by manish sakpal
 */

package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.CustomerMasterBean;
import org.paradyne.model.TravelManagement.Master.CustomerMasterModel;
import org.struts.lib.ParaActionSupport;

public class CustomerMasterAction extends ParaActionSupport
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerMasterAction.class);
	
	CustomerMasterBean cbean;
	public CustomerMasterBean getCbean() {
		return cbean;
	}

	public void setCbean(CustomerMasterBean cbean) {
		this.cbean = cbean;
	}

	public void prepare_local() throws Exception {
		cbean = new CustomerMasterBean();
		cbean.setMenuCode(1083);
		getNavigationPanel(1);
	}

	public Object getModel() {
		return cbean;
	}
	
	public String input() throws Exception {
		try {
			CustomerMasterModel model = new CustomerMasterModel();
			model.initiate(context, session);
			model.getAgencyList(cbean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		cbean.setEnableAll("Y");
		return INPUT;
	}
	
	
	/** This method is called on save button for saving records */
	public String save() throws Exception 
	{
		try 
		{
			CustomerMasterModel model = new CustomerMasterModel();
			model.initiate(context, session);
			boolean result;

			if (cbean.getCustomercode().equals("")) 
			{
				result = model.addData(cbean);
				if (result) 
				{
					addActionMessage(getMessage("save"));
				} 
				else 
				{
					addActionMessage(getMessage("duplicate"));
					reset();
				}				
			}
			else 
			{
				result = model.update(cbean);
				if (result) 
				{
					addActionMessage(getMessage("update"));
				} 
				else 
				{
					addActionMessage(getMessage("duplicate"));
					reset();
				}
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		getNavigationPanel(1);
		
		input();
		cbean.setEnableAll("Y");
		return INPUT;

	} // End of save method

	public String addNew() 
	{
		try 
		{		
			reset();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method."+e);
		}
		getNavigationPanel(2);
		return SUCCESS;
	}

	/**
	 * To delete any record
	 */
	public String delete() throws Exception {
		try {
			CustomerMasterModel model = new CustomerMasterModel();
			model.initiate(context, session);
			boolean result = model.delRecord(cbean);
			model.terminate();

			if (result) {
				addActionMessage(getMessage("delete"));

			} else {
				addActionMessage(getMessage("no result"));
			}

		} catch (Exception e) {
			System.out.println("Error ========> " + e);
		}
		return input();
		//return INPUT;
	}

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 */

	public String deleteChkRecords() throws Exception {

		try {
			String code[] = request.getParameterValues("customerdeleteCode");
			CustomerMasterModel model = new CustomerMasterModel();
			model.initiate(context, session);
			boolean result = model.deleteCheckedRecords(cbean, code);
			if (result) {
				addActionMessage(getText("delMessage", ""));
				reset();
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
			model.getAgencyList(cbean, request);
			getNavigationPanel(1);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured : "+e);
		}
		return "input";

	}

	public String edit() throws Exception {
		try {
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
	}

	public String back() throws Exception {
		input();
		getNavigationPanel(1);
		return INPUT;
	}

	public String reset() throws Exception {
		getNavigationPanel(2);
		cbean.setCustomercode("");
		cbean.setCustomerName("");
		cbean.setContactPerson("");
		cbean.setAddress("");
		cbean.setCity("");		
		cbean.setPhone("");		
		cbean.setEmailId("");
		cbean.setProjectName("");
		return SUCCESS;
	}

	/*
	 * This function is called for setting values from search window into
	 * respective fields
	 */
	public String setRecord() throws Exception {
		try {
			CustomerMasterModel model = new CustomerMasterModel();
			model.initiate(context, session);
			model.getRecord(cbean);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in HotelMaster - setRecord " + e);
		}
		getNavigationPanel(3);
		cbean.setEnableAll("N");
		return SUCCESS;
	}

	
	/* This f9city for selecting Agency from pop up window */
	public String f9searchCustomer() throws Exception {
		String searchquery = "SELECT TRAVEL_CUST_NAME, TRAVEL_CUST_CONTACT_PERSON,TRAVEL_CUST_ID  FROM TMS_TRAVEL_CUSTOMER ORDER BY TRAVEL_CUST_ID";
		String[] headers = { getMessage("customer.name"),getMessage("contact.person") };
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = { "customerName", "contactPerson", "customercode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "true";
		String submitToMethod = "CustomerMaster_setRecord.action";
		setF9Window(searchquery, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	
	/* This f9city for selecting city from pop up window */
	public String f9city() throws Exception {
		 
		String query = " SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION where location_type='Ci' ORDER BY LOCATION_NAME";
		
		String[] headers = { getMessage("city") };
		String[] headerWidth = { "50" };
		String[] fieldNames = { "city", "cityId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}


	/* This f9city for selecting city from pop up window */
	public String f9Project() throws Exception { 
		 
		String query = " SELECT PROJECT_NAME, PROJECT_ID FROM TMS_TRAVEL_PROJECT ORDER BY PROJECT_ID";

		String[] headers = { getMessage("customer.project") };
		String[] headerWidth = { "50" };		
		String[] fieldNames = { "projectName", "projectId" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	/**
	 * To set the page according to the page numbers
	 */
	public String callPage() throws Exception 
	{
		input();
		return INPUT;
	}

	
	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 */
	public String calforedit() throws Exception 
	{
		try {
			CustomerMasterModel model = new CustomerMasterModel();
			String custID = request.getParameter("id");
			model.initiate(context, session);
			model.calforedit(cbean, custID);		
			model.terminate();
			getNavigationPanel(3);
			} 
		catch (Exception e) 
		{
			System.out.println("Error Occured : " + e);
		}
		cbean.setEnableAll("N");		
		return SUCCESS;
	}//End of calforedit()

} // End of the class
