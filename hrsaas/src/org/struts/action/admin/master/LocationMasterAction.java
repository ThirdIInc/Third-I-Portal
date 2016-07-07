/**
 * 
 */
package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.LocationMaster;

import org.paradyne.model.admin.master.LocationMasterModel;

/**
 * @author Lakkichand
 * @date 20 APR 2007
 */

public class LocationMasterAction extends org.struts.lib.ParaActionSupport {

	LocationMaster locationMaster;
	static int  count=0;
	
	/**
	 * @return the locationMaster
	 */
	public LocationMaster getLocationMaster() {
		return locationMaster;
	}
	
	public Object getModel(){
		
		return locationMaster;
	}
	/**
	 * @param locationMaster the locationMaster to set
	 */

	public void setLocationMaster(LocationMaster locationMaster) {
		this.locationMaster = locationMaster;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		locationMaster = new LocationMaster();
		locationMaster.setMenuCode(20);
	}
	
	
public void prepare_withLoginProfileDetails() throws Exception {
	
}

public String callList()throws Exception{
	LocationMasterModel model= new LocationMasterModel();
	locationMaster.setEnableAll("N");
	model.initiate(context,session);
	
	
		model.employeeSearch(locationMaster,request);
	
		getNavigationPanel(1);
	   model.terminate();
	  return SUCCESS;
}

public String input() throws Exception {
	
	return SUCCESS;
}
public String addNew() {
	try {
		getNavigationPanel(2);
		return "locationData";
	} catch(Exception e) {
		logger.error("Exception in addNew in action:" + e);
		return null;
	}
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
public String callPage() throws Exception {
	
	LocationMasterModel model= new LocationMasterModel();
		
		model.initiate(context,session);
		model.employeeSearch(locationMaster,request);
		getNavigationPanel(1);
		model.terminate();
	 return SUCCESS;
	
	}
	
	public String save(){
		
		String result;
		String locationType = "";
		
		LocationMasterModel model = new LocationMasterModel();
		model.initiate(context,session);
		
		if(locationMaster.getLocationType().equals("Country")){
			locationType = "C";
			
		}if(locationMaster.getLocationType().equals("State")){
			locationType = "S";
			
		}if(locationMaster.getLocationType().equals("City")){
			locationType = "Ci";
			
		}
		
		if(locationMaster.getLocationCode().equals("")){
			result = model.save(locationMaster, locationType);
			
			if(result.equals("saved")){
				addActionMessage(getText("addMessage", ""));
				//reset();
				getNavigationPanel(3);
				return "locationData";
			}
			else if(result.equals("duplicate")){
				addActionMessage("Duplicate entry found.");
				reset();
				getNavigationPanel(1);
				return "success";
			}
			else{
				addActionMessage("Record can not be saved.");
			
				return "success";
			}
		}else{
			result = model.modify(locationMaster, locationType);
			
			if(result.equals("updated")){
				addActionMessage(getText("Record updated successfully."));
				//reset();
				getNavigationPanel(3);
				return "locationData";
			}
			else if(result.equals("duplicate")){
				addActionMessage("Duplicate entry found.");
				reset();
				getNavigationPanel(1);
				return "success";
			}
			else{
				addActionMessage("Record can not be updated.");
				return "success";
			}
		}
		
		//model.employeeSearch(locationMaster,request);
		
		//return "success";
	}
	
	public String reset(){
		locationMaster.setLocationCode("");
		locationMaster.setLocationName("");
		locationMaster.setLocationType("");
		locationMaster.setLocationType1("");
		locationMaster.setCountryCode("");
		locationMaster.setCountryName("");
		locationMaster.setStateCode("");
		locationMaster.setStateName("");
		locationMaster.setLocclass("");
		LocationMasterModel model = new LocationMasterModel();
		model.initiate(context,session);
		model.employeeSearch(locationMaster,request);
		model.terminate();
		getNavigationPanel(2);
		return "locationData";
	}
	
	public String delete(){
		LocationMasterModel model = new LocationMasterModel();
		model.initiate(context,session);
		
		String result = model.delete(locationMaster);
		model.employeeSearch(locationMaster,request);
		model.terminate();
		
		if(result.equals("deleted")){
			addActionMessage(getText("delMessage", ""));
			locationMaster.setLocationCode("");
			locationMaster.setLocationName("");
			locationMaster.setCountryCode("");
			locationMaster.setCountryName("");
			locationMaster.setStateCode("");
			locationMaster.setStateName("");
			locationMaster.setLocationType("");
			locationMaster.setLocationType1("");
			locationMaster.setLocclass("");
			locationMaster.setIsActive("");
		}
		else if(result.equals("referenced")){
			addActionMessage("This record is referenced in other resources. So cannot delete.");
		}
		else{
			addActionMessage("Record can not be deleted");
		}
		getNavigationPanel(1);
		return "success";
	}
	
	public String showRecord(){
		LocationMasterModel model = new LocationMasterModel();
		model.initiate(context,session);
		model.showRecord(locationMaster);
		
		locationMaster.setLocationType1(locationMaster.getLocationType());
		model.employeeSearch(locationMaster,request);
		getNavigationPanel(3);
		model.terminate();
		return "locationData";
	}
	
	public String report(){
		LocationMasterModel model = new LocationMasterModel();
		model.initiate(context,session);
		/*model.report(locationMaster);
		model.terminate();
		return "report";*/
		
		String [] label={"Sr.No",getMessage("locationname"),getMessage("locationtype"),getMessage("is.active")};
		model.getReport(locationMaster,request,response,context,label);
		model.terminate();
		return null;
	}
	
	public String setStateNull(){
		locationMaster.setStateCode("");
		locationMaster.setStateName("");
		LocationMasterModel model = new LocationMasterModel();
		model.initiate(context,session);
		model.employeeSearch(locationMaster,request);
		model.terminate();
		return "locationData";
	}
	
	public String calforedit(){
		LocationMasterModel model = new LocationMasterModel();
		model.initiate(context,session);
		model.calforedit(locationMaster);
	
		showRecord();
		model.employeeSearch(locationMaster,request);
		getNavigationPanel(3);
		locationMaster.setEnableAll("N");
		model.terminate();
		return "locationData";
	}
	
	public String calfordelete()
	{
		LocationMasterModel model = new LocationMasterModel();
		model.initiate(context,session);
		boolean result;
		result = model.calfordelete(locationMaster);
		if(result){
			addActionMessage("Record deleted successfully");
			locationMaster.setLocationCode("");
			locationMaster.setLocationName("");
			locationMaster.setCountryCode("");
			locationMaster.setCountryName("");
			locationMaster.setStateCode("");
			locationMaster.setStateName("");
			locationMaster.setLocationType("");
			locationMaster.setLocationType1("");
		}else{
			addActionMessage("Record can not be deleted");
		}
		
		model.employeeSearch(locationMaster,request);
		model.terminate();
		
	return "success";
	}
	public String delete1()throws Exception {
		String code [] = request.getParameterValues("hdeleteCode");
		
		LocationMasterModel model = new LocationMasterModel();
		
		model.initiate(context,session);
		boolean result = model.deleteLocation(locationMaster,code);
		
		if(result)
		{addActionMessage("Record deleted successfully");
		locationMaster.setLocationCode("");
		locationMaster.setLocationName("");
		locationMaster.setCountryCode("");
		locationMaster.setCountryName("");
		locationMaster.setStateCode("");
		locationMaster.setStateName("");
		locationMaster.setLocationType("");
		locationMaster.setLocationType1("");
			
		}
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}
		
		model.employeeSearch(locationMaster,request);
		getNavigationPanel(1);
		model.terminate();
		
		return "success";

	}
	
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT   LOCATION_NAME, DECODE(LOCATION_TYPE,'C','Country','S','State','Ci','City'), DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'), LOCATION_PARENT_CODE "
						+" ,LOCATION_CLASS ,LOCATION_CODE FROM HRMS_LOCATION ORDER BY upper(LOCATION_NAME) ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={ getMessage("locationname"), getMessage("locationtype"), getMessage("is.active")};
		
		String[] headerWidth={ "40", "40", "20" };
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"locationMaster.locationName", 
								"locationMaster.locationType","locationMaster.isActive", "locationMaster.parentCode","locationMaster.locclass","locationMaster.locationCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4,5};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="LocationMaster_showRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9Countryaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT  LOCATION_NAME,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 0 "
						+"ORDER BY LOCATION_NAME";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("countryname")};
		
		String[] headerWidth={"100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"locationMaster.countryName","locationMaster.countryCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9Stateaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT  LOCATION_NAME,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_LEVEL_CODE = 1 "
						+"AND LOCATION_PARENT_CODE = "+locationMaster.getCountryCode() 
						+" ORDER BY LOCATION_NAME";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={getMessage("statename")};
		
		String[] headerWidth={ "100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"locationMaster.stateName","locationMaster.stateCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
}

