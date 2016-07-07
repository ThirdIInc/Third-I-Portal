package org.struts.action.TravelManagement.Master;

import java.util.TreeMap;

import org.paradyne.bean.TravelManagement.Master.HotelMaster;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.TravelManagement.Master.HotelMasterModel;
import org.struts.lib.ParaActionSupport;

public class HotelMasterAction extends ParaActionSupport {

	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HotelMasterAction.class);
	HotelMaster hotelMaster;
	String poolDir = "";
	String fileName = "";


	public void prepare_local() throws Exception {
		hotelMaster = new HotelMaster();
		hotelMaster.setMenuCode(1081);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return hotelMaster;
	}

	public HotelMaster getHotelMaster() {
		return hotelMaster;
	}
	public void setHotelMaster(HotelMaster hotelMaster) {
		this.hotelMaster = hotelMaster;
	}

/*This input function is get called for displaying onload list*/
	public String input() throws Exception {

		HotelMasterModel model = new HotelMasterModel();
		model.initiate(context, session);
		model.intData(hotelMaster, request);
		model.terminate();
		getNavigationPanel(1);
		return "input";
	}


	public String addNew() {
		try {
			
			getNavigationPanel(3);
			return "addnew";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		HotelMasterModel  model = new HotelMasterModel ();
		model.initiate(context, session);
		model.intData(hotelMaster, request);
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("zone");
		hotelMaster.setZonemap(map);
		dmu.terminate();
		model.terminate();
		
	
	}
	
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		HotelMasterModel model = new HotelMasterModel();
		model.initiate(context, session);
		model.intData(hotelMaster, request);
		getNavigationPanel(1);
		model.terminate();
		
		return "input";
	}

	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		HotelMasterModel model = new HotelMasterModel();
		model.initiate(context, session);
		boolean result = model.delRecord(hotelMaster);
		model.intData(hotelMaster, request);
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		hotelMaster.setHotelName("");
		hotelMaster.setHotelType("");
		hotelMaster.setContactPerson("");
		hotelMaster.setAddress("");
		hotelMaster.setCity("");
		hotelMaster.setPhone1("");
		hotelMaster.setPhone2("");
		hotelMaster.setEmailId("");
	   	
		return "input";
	}
	

	/** This method is called on save button for saving records */
	public String save() throws Exception {
		try {
				
			
	   String[] rmtype = request.getParameterValues("roomtypeId");
	   String[] actrate = request.getParameterValues("actualRate");
	   String[] corprate = request.getParameterValues("corporateRate");
	   String[] isbreakFast = request.getParameterValues("isbreakFast");
	   
						
			HotelMasterModel model = new HotelMasterModel();
			model.initiate(context, session);
			boolean result;

			if (hotelMaster.getHiddencode().equals("")) {
				
				System.out.println("m in save hotel master>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+hotelMaster.getHiddencode());
				result = model.addData(hotelMaster,rmtype,actrate,corprate,isbreakFast);
				if (result) {
					addActionMessage(getMessage("save"));
					model.setRoomtypeRecord(hotelMaster);
					

				} else {
					addActionMessage(getMessage("duplicate"));
					
					reset();

					 
				}
			} else {

				result = model.update(hotelMaster,rmtype,actrate,corprate,isbreakFast);

				if (result) {
					addActionMessage(getMessage("update"));
					model.setRoomtypeRecord(hotelMaster);
					 

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();

					 
				}

			}
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		
		getNavigationPanel(2);
		hotelMaster.setEnableAll("N");
		return "addnew";

	}

	
	/**following function is called when delete button is clicked in the jsp page*/
	
	public String deleteChkRecords() throws Exception {
		
		
		
		String code[] = request.getParameterValues("hdeleteCode");
		HotelMasterModel model = new HotelMasterModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(hotelMaster, code);

		System.out.println("result>>>>>>>>>>>>>>>>>>>>>>>>>>>"+result);
		
		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.intData(hotelMaster, request);
		getNavigationPanel(1);
		model.terminate();

		return "input";

	}
	
	/*This function is called for setting values from search window into respective fields*/
	public String setRecord() throws Exception {
		try{
			HotelMasterModel model = new HotelMasterModel();
			model.initiate(context, session);
			model.getRecord(hotelMaster);
			model.setRoomtypeRecord(hotelMaster);
			model.terminate();
			
		}catch(Exception e){
			logger.error("Exception in HotelMaster - setRecord " +e);
		}
		
		getNavigationPanel(2);
		hotelMaster.setEnableAll("N");
		return "addnew";
	}
	
	
	/*This function is called for setting room afrter hotel type choose*/	
	
	
	public String setRoom() throws Exception {
		try{
			HotelMasterModel model = new HotelMasterModel();
			model.initiate(context, session);
			model.getRoom(hotelMaster);
			//model.setRoomtypeRecord(hotelMaster);
			model.terminate();
			
		}catch(Exception e){
			logger.error("Exception in HotelMaster - setRoom " +e);
		}
		
		getNavigationPanel(3);
		hotelMaster.setEnableAll("Y");
		return "addnew";
	}
	
	
	
	/**This f9action is for Search pop up window */
	public String f9action() throws Exception {
		String query = " select HOTEL_NAME,HOTEL_ADDRESS,HOTEL_ID FROM HRMS_TRAVEL_HOTEL_MASTER ORDER BY HOTEL_NAME";
		String[] headers = { getMessage("hotelname"),getMessage("hoteladdress")};
		String[] headerWidth = { "40", "40" };
		String[] fieldNames = { "hotelName", "address","hiddencode" };
		int[] columnIndex = { 0, 1, 2};
		String submitFlag = "true";
		String submitToMethod = "HotelMaster_setRecord.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	/*This f9city for selecting city from pop up window*/
	public String f9city() throws Exception {	
	
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION where location_type='Ci' ORDER BY LOCATION_NAME"; 

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("city")};

		String[] headerWidth = { "50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "city","cityId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

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
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	
	}
	
	/*This f9type for selecting hotel type from pop up window*/
	public String f9type() throws Exception {	
	
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT NVL(HOTEL_TYPE_NAME,' '),HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE "
+" ORDER BY HOTEL_TYPE_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("hoteltype")};

		String[] headerWidth = {"100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "hotelType","typeId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "HotelMaster_setRoom.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
	
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	
		}
		
	
/**This method is called on reset button*/
	
	public String reset() throws Exception {
		try {
			hotelMaster.setHotelName("");
			hotelMaster.setHotelType("");
			hotelMaster.setHotelId("");
			hotelMaster.setContactPerson("");
			hotelMaster.setAddress("");
			hotelMaster.setCity("");
			hotelMaster.setCityId("");
			hotelMaster.setPhone1("");
			hotelMaster.setPhone2("");
			hotelMaster.setEmailId("");
			hotelMaster.setHiddencode("");
			hotelMaster.setHotelMasterRemark("");
			hotelMaster.setIsbreakFast("");
			hotelMaster.setDistanceFromAirport("");
			hotelMaster.setZoneName("");
			hotelMaster.setDinnerPackage("");
			hotelMaster.setDinnerPackageCost("");			
			getNavigationPanel(3);
		} catch (Exception e) {
			logger.error("Error in reset" + e);
		}
		return "addnew";
	}
	/*This function called when edit button clicked on jsp after record get saved*/ 
	public String edit() throws Exception {
		try {
			HotelMasterModel model = new HotelMasterModel();
			model.initiate(context, session);
			model.setRoomtypeRecord(hotelMaster);
			model.terminate();
			getNavigationPanel(3);
			hotelMaster.setEnableAll("Y");
		
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return "addnew";
	}

	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		try {
			HotelMasterModel model = new HotelMasterModel();
			model.initiate(context, session);
			model.calforedit(hotelMaster);
			model.setRoomtypeRecord(hotelMaster);
			
			//interviewRecord();
			getNavigationPanel(2);
			hotelMaster.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "addnew";
	}


	/**This method is called on back button*/
	public String cancel() {
		try {
			
			getNavigationPanel(1);
			
			reset();
			
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			
			return "input";
		}
		catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
}
