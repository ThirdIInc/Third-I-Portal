/*
 * Added by manish sakpal
 * 
 */

package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.TravelAgencyListBean;
import org.paradyne.model.TravelManagement.Master.TravelAgencyListModel;
import org.struts.lib.ParaActionSupport;

public class TravelAgencyListAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelAgencyListAction.class);
	TravelAgencyListBean bean;

	public void prepare_local() throws Exception {
		bean = new TravelAgencyListBean();
		bean.setMenuCode(1082);
		getNavigationPanel(1);
	}

	public TravelAgencyListBean getBean() {
		return bean;
	}

	public void setBean(TravelAgencyListBean bean) {
		this.bean = bean;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public String input() throws Exception {

		try {
			TravelAgencyListModel model = new TravelAgencyListModel();
			model.initiate(context, session);
			model.getAgencyList(bean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}

	/** This method is called on save button for saving records */
	public String save() throws Exception {
		try {
			TravelAgencyListModel model = new TravelAgencyListModel();
			model.initiate(context, session);
			boolean result;

			if (bean.getAgencycode().equals("")) 
			{
				result = model.addData(bean);
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
				result = model.update(bean);
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

		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		
		
		bean.setEnableAll("N");
		return SUCCESS;

	}// End of save method

	public String addNew() 
	{
		
		try
		{
			reset();
		}
		catch(Exception e)
		{
			System.out.println("Exception occurred =============>"+e);
		}
		getNavigationPanel(2);
		
		return SUCCESS;
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			TravelAgencyListModel model = new TravelAgencyListModel();
			model.initiate(context, session);
			boolean result = model.delRecord(bean);
			model.getAgencyList(bean, request);
			model.terminate();

			if (result) {
				addActionMessage(getMessage("delete"));

			} else {
				addActionMessage(getMessage("no result"));
			}// end of else

			bean.setAgencyName("");
			bean.setContactPerson("");
			bean.setAddress("");
			bean.setCity("");
			bean.setPhone1("");
			bean.setPhone2("");
			bean.setEmailId1("");
			bean.setEmailId2("");

		} catch (Exception e) {
			System.out.println("Error ========> " + e);
		}
		getNavigationPanel(1);
		bean.setEnableAll("Y");
		return INPUT;
	}

	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 */

	public String deleteChkRecords() throws Exception {

		try {
			String code[] = request.getParameterValues("agencydeleteCode");
			TravelAgencyListModel model = new TravelAgencyListModel();
			model.initiate(context, session);
			boolean result = model.deleteCheckedRecords(bean, code);
			if (result) {
				addActionMessage(getText("delMessage", ""));
				reset();
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
			model.getAgencyList(bean, request);
			getNavigationPanel(1);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
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
		bean.setAgencyName("");
		bean.setContactPerson("");
		bean.setAddress("");
		bean.setCityId("");
		bean.setCity("");		
		bean.setPhone1("");
		bean.setPhone2("");
		bean.setEmailId1("");
		bean.setEmailId2("");
		bean.setTravelmode("");
		bean.setTravelmodeCode("");
		bean.setAgencycode("");

		return SUCCESS;
	}

	/*
	 * This function is called for setting values from search window into
	 * respective fields
	 */
	public String setRecord() throws Exception {
		try {
			TravelAgencyListModel model = new TravelAgencyListModel();
			model.initiate(context, session);
			model.getRecord(bean);
			model.terminate();

		} catch (Exception e) {
			logger.error("Exception in HotelMaster - setRecord " + e);
		}

		getNavigationPanel(3);
		bean.setEnableAll("N");
		return SUCCESS;
	}

	/* This f9city for selecting Agency from pop up window */
	public String searchAgency() throws Exception {
		String searchquery = "SELECT AGENCY_NAME,AGENCY_CONTACT_PERSON, AGENCY_CODE  FROM TMS_TRAVEL_AGENCY ORDER BY AGENCY_CODE";
		String[] headers = { getMessage("agency.nameList"),
				getMessage("contact.personList") };
		String[] headerWidth = { "50", "50" };

		String[] fieldNames = { "agencyName", "contactPerson", "agencycode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "TravelAgencyList_setRecord.action";

		setF9Window(searchquery, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/* This f9city for selecting city from pop up window */
	public String f9city() throws Exception {

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION where location_type='Ci' ORDER BY LOCATION_NAME";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("city") };

		String[] headerWidth = { "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "city", "cityId" };

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

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		input();
		return INPUT;
	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		try {
			TravelAgencyListModel model = new TravelAgencyListModel();
			String agencyID = request.getParameter("id");
			System.out.println("agencyID ===========> "+agencyID);
			model.initiate(context, session);
			model.calforedit(bean, agencyID);		
			model.terminate();
			getNavigationPanel(3);
		} catch (Exception e) {
			System.out.println("Error Occured : " + e);
		}
		bean.setEnableAll("N");
		
		return SUCCESS;
	}

	public String f9selectTravelMode() {

		String query = " SELECT JOURNEY_ID,NVL(JOURNEY_NAME,' ') "
				+ " FROM HRMS_TMS_JOURNEY_MODE "
				+ " WHERE JOURNEY_STATUS='A' "
				+ " ORDER BY JOURNEY_LEVEL ";
		
		String header ="Journey Name";
		String textAreaId = "paraFrm_travelmode";

		String hiddenFieldId ="paraFrm_travelmodeCode";

		String submitFlag = "";
		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}
}
