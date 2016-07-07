/**
 * Balaji
 * 08-08-2008
**/
package org.struts.action.TravelManagement;

import org.paradyne.bean.TravelManagement.TravelApplication;
import org.paradyne.model.TravelManagement.TravelApplicationModel;
import org.struts.lib.ParaActionSupport;

/**
 * This class is used for store the information about Travel Application.
**/

public class TravelApplicationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	
	TravelApplication trApp;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		trApp = new TravelApplication();
		trApp.setMenuCode(649);

	} //end of prepare_local
	
	public void prepare_withLoginProfileDetails() throws Exception {
		TravelApplicationModel  model = new TravelApplicationModel();
		model.initiate(context, session); 
		if (trApp.isGeneralFlag()) {  
		  	 model.getEmployeeDetails(trApp.getUserEmpId(), trApp); 
		 	 model.generateExpense(trApp);
		 	 
		}
		model.terminate();
	} //end of prepare_withLoginProfileDetails

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return trApp;
	}

	public TravelApplication getTrApp() {
		return trApp;
	}

	public void setTrApp(TravelApplication trApp) {
		this.trApp = trApp;
	}
	
	/**
	 * @This method is used for save and update the application information.
	 */
	
	/**
	 * @return success
	 */
	public String save()
	{
		TravelApplicationModel  model = new TravelApplicationModel();
        model.initiate(context, session);
        Object[][] empFlow = generateEmpFlow(trApp.getEmpId(), "TYD", 1);
		if(empFlow==null)
		{
			addActionMessage("Reporting structure not defined for the employee\n"
					+ trApp.getEmpName());
			addActionMessage("Please contact HR manager.");
		}else
		{
			if(trApp.getTravelAppId().equals(""))
			{
			 boolean result = model.save(trApp,request,empFlow);
			  if(result)
				{
					addActionMessage(" Record saved successfully.");
					reset();
				}else
				{
					addActionMessage(" Record can't saved.");
				}
			}else
			{ 
				boolean ckFlag =model.checkForward(trApp);
			 if(ckFlag)
			 {
				  boolean result = model.update(trApp,request,empFlow);
				  if(result)
					{
						addActionMessage(" Record updated successfully.");
						reset();
					}else
					{
						addActionMessage(" Record can't updated.");
					}
			  }else
			  {
				  addActionMessage(" Forwarded Application can't updated.");
				  
				   reset();
			  } 
			
			}
		} 
		model.terminate(); 
		return "success";
	}    //end of save
	
	/**
	 * @This method is used for delete application information.
	 */
	/**
	 * @return success
	 */
	public String delete()
	{
		TravelApplicationModel  model = new TravelApplicationModel();
        model.initiate(context, session);
        boolean ckFlag =model.checkForward(trApp);      
        if(ckFlag)
		 {
		    boolean result =   model.deleteApp(trApp);
		    if(result)
		    {
		    	addActionMessage("Record deleted successfully.");
		    	reset();
		    	
		    }else
		    {
		    	addActionMessage("Record can't deleted.");
		    }
		 }else
		 {
			 addActionMessage("Forwarded record can't deleted.");
		 }
        model.terminate(); 
		return "success";
	}   //end of delete

	/**
	 * @This method is used for add the element in Arraylist. 
	 */
	/**
	 * @return success
	 */
	public String addIterator()
	{   
		trApp.setNoDataFlag("true");
		TravelApplicationModel  model = new TravelApplicationModel();
        model.initiate(context, session);  
        model.generateExpense(trApp);
        if(trApp.getCheckEdit().equals(""))
        {
		model.addIterator(trApp,request);
        }else
        {
        	model.editIterator(trApp,request);
        }
		trApp.setFromPlace("");
		trApp.setFromPlaceId("");
		trApp.setToPlace("");
		trApp.setToPlaceId("");
		trApp.setJourneyName("");
		trApp.setJourneyId("");
		trApp.setJourneyClassName("");
		trApp.setJourneyClassId("");
		trApp.setJourneyDate("");
		trApp.setJourneyTime("");
		trApp.setCheckEdit("");
		trApp.setJourneyModeRefNumber("");		
		model.terminate(); 
		return "success";
	}  //end of reset
	/**
	 * @This method is used for delete the element in Arraylist. 
	 */
	/**
	 * @return success
	 */
	public String delItem()
	{ 
		TravelApplicationModel  model = new TravelApplicationModel();
        model.initiate(context, session);
        model.generateExpense(trApp);
        model.delItem(trApp,request);
        trApp.setCheckEdit("");
        model.terminate(); 
        trApp.setNoDataFlag("true");
        
		return "success";
	}  //end of delItem
	
 
	/**
	 * @This method is used for Generate the report for particular application
	 */
	public String report() { 

		TravelApplicationModel  model = new TravelApplicationModel();
		model.initiate(context, session);
	 	model.getReport(request, response, trApp);
		model.terminate();
		return null;
	} //end of report
	/**
	 * @This method is used for clear all the field.
	 */
	/**
	 * @return success
	 */
	public String reset()
	{
		
		if(!trApp.isGeneralFlag())
		{
		trApp.setEmpToken("");
		trApp.setEmpId("");
		trApp.setEmpName("");
		trApp.setBranchName("");
		trApp.setDesg("");
		trApp.setDept(""); 
		trApp.setGradeCode("");
		trApp.setGradeName("");
		trApp.setEmpAge("");
		trApp.setContactNo("");
		}
		trApp.setTravelAppId("");
		trApp.setAppDate("");
		trApp.setStatus("p");
		trApp.setFromPlace("");
		trApp.setFromPlaceId("");
		trApp.setToPlace("");
		trApp.setToPlaceId("");
		trApp.setJourneyName("");
		trApp.setJourneyId("");
		trApp.setJourneyClassName("");
		trApp.setJourneyClassId("");
		trApp.setJourneyDate("");
		trApp.setJourneyTime("");
		trApp.setApplicantComment(""); 
		trApp.setIdProof("");
		trApp.setIdNumber("");
		trApp.setModeName("");
		return "success";
	} // end of reset
	/**
	 * @This method is used collect detail data of application 
	 */
	/**
	 * @return success
	 */
	public String callDtl()
	{   trApp.setNoDataFlag("true");
		TravelApplicationModel  model = new TravelApplicationModel();
        model.initiate(context, session);
        model.callDtl(trApp);
        model.generateExpense(trApp);
        model.terminate(); 
		return "success";
	}
	public String callExpense()
	{    
		TravelApplicationModel  model = new TravelApplicationModel();
        model.initiate(context, session); 
        model.generateExpense(trApp);
        model.terminate(); 
		return "success";
	}
	
	
	/**
	 * @This method is used for prepare the schedule of journey.
	 */
	/**
	 * @return success
	 */
	public String travelScheduleApp()
	{  
		 trApp.setNoDataFlag("true");   
		 if(trApp.getStatusSch().equals("S"))
		 {  trApp.setSchViewFlag("true");
		   
		 } 
		TravelApplicationModel  model = new TravelApplicationModel();
        model.initiate(context, session);
        model.travelScheduleEmpInfo(trApp);
        model.generateExpense(trApp);
        model.callDtlSchedule(trApp,request); 
       // if(trApp.getStatusSch().equals("S"))
		// { 
        	model.getHoteldetail(trApp);
		// }  
        model.terminate(); 
		return "success";
	} // end of travelScheduleApp
	
	/**
	 * @This method is used for cancel the journey
	 */
	/**
	 * @return success
	 */
	public String travelCancel()
	{  
		 trApp.setNoDataFlag("true");   
		 if(trApp.getStatusSch().equals("S"))
		 {  trApp.setSchViewFlag("true");
		   
		 } 
		TravelApplicationModel  model = new TravelApplicationModel();
        model.initiate(context, session);
        model.travelScheduleEmpInfo(trApp); 
        model.callDtlForCancel(trApp,request); 
        model.generateExpense(trApp);
       // if(trApp.getStatusSch().equals("S"))
		// { 
        	model.getHoteldetailForCancel(trApp);
		// } 
        	try 
        	{
            trApp.setTotalCanceled(""+(Double.parseDouble(trApp.getTotalJCancel())+ Double.parseDouble(trApp.getHotelTotalCanceled())));
        	}catch (Exception e) {
				// TODO: handle exception
			}
        model.terminate(); 
		return "travelCancel";
	} // end of travelCancel
	 
	/**
	 * @This method is used for cancel journey saved.
	 */
	/**
	 * @return success
	 */
	public String travelCancelSave()
	{  
		trApp.setNoDataFlag("true");  
	
		TravelApplicationModel  model = new TravelApplicationModel(); 
        model.initiate(context, session);
        boolean result = model.travelCancelSave(trApp,request);
        model.travelScheduleEmpInfo(trApp);
        model.callDtlForCancel(trApp,request); 
        model.generateExpense(trApp);
     //   if(trApp.getStatusSch().equals("S"))
		// { 
       	model.getHoteldetailForCancel(trApp);
		// } 
        if(result)
	  {  		
			    	addActionMessage("Travel cancelled successfully."); 
			        	
	    }else
	    {
	    	addActionMessage("Travel can't cancelled.");
	    }
       // trApp.setSchSaveFlag("false");
        //trApp.setScheduleFinalFlag("");
        model.terminate(); 
		return "travelCancel";
	}  // end of travelCancelSave
	
	/**
	 * @This method is used for schedule of travel to saved.
	 */
	/**
	 * @return success
	 */
	public String travelScheduleSave()
	{  
		trApp.setNoDataFlag("true");  
	
		TravelApplicationModel  model = new TravelApplicationModel(); 
        model.initiate(context, session);
        boolean result = model.travelScheduleSave(trApp,request);
        model.travelScheduleEmpInfo(trApp);
        model.callDtlSchedule(trApp,request); 
        model.generateExpense(trApp);
     //   if(trApp.getStatusSch().equals("S"))
		// { 
       	model.getHoteldetail(trApp);
		// } 
        if(result)
	  {  		if(trApp.getScheduleFinalFlag().equals("final"))
			    {
		        addActionMessage("Travel scheduled successfully."); 
			    }else
			    {
			    	addActionMessage("Record saved successfully."); 
			    }
	    	
	    }else
	    {
	    	addActionMessage("Record can't Saved.");
	    }
        trApp.setSchSaveFlag("false");
        trApp.setScheduleFinalFlag("");
        model.terminate(); 
		return "success";
	} // end of travelScheduleSave
	
 
	/**
	 * @This method is used for add the details of hotel.
	 */
	/**
	 * @return success
	 */
	
	public String addHotel()
	{   
		trApp.setNoDataFlag("true");  
		TravelApplicationModel  model = new TravelApplicationModel(); 
        model.initiate(context, session);
        model.generateExpense(trApp);
        model.addHoltelList(trApp,request); 
        model.travelScheduleEmpInfo(trApp);
        model.callDtlForHotel(trApp,request);
        model.terminate(); 
		return "success";
	}  // end of addHotel
	
	/**
	 * @This method is used for show the existing saved application.
	 */
	public String f9action()
	{
		try
		{
			String query = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " 
					     +" TO_CHAR(TRAVEL_APPDATE,'DD-MM-YYYY'),CASE WHEN TRAVEL_SCHEDULE_STATUS ='S' THEN DECODE(TRAVEL_CONFIRM_STATUS,'D','Schedule','C','Confirm','N','Cancel In Process','K','Cancel') ELSE  DECODE(TRAVEL_STATUS,'P','Pending','A','Approved','R','Rejected') END, "	
						 +" CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID,TRAVEL_STATUS,TRAVEL_ID ,CASE WHEN TRAVEL_SCHEDULE_STATUS ='S' THEN TRAVEL_CONFIRM_STATUS ELSE TRAVEL_STATUS END ,TRAVEL_SCHEDULE_STATUS ,NVL(CADRE_NAME,' '),EMP_CADRE FROM HRMS_TRAVEL "
						 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TRAVEL.TRAVEL_EMPID ) " 
						 +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) " 
						 +" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) " 
						 +" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) " 
						 +" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) " 
						 +" LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) ";
			if (trApp.isGeneralFlag()) {
				query += " WHERE EMP_ID = " + trApp.getUserEmpId();
			}
			query += " ORDER BY EMP_ID ,EMP_FNAME , TRAVEL_APPDATE ";
			
			String[] headers = {"Employee ID", "Employee Name","Applicaton Date","Status"};

			String[] headerWidth = {"20", "40" ,"20","20"};

			String[] fieldNames = {"trApp.empToken","trApp.empName","trApp.appDate","status","trApp.branchName","trApp.dept","trApp.desg","trApp.empId","status","trApp.travelAppId" ,"hidStatus","trHidSchStatus","trApp.gradeName","trApp.gradeCode"};

			int[] columnIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};

			String submitFlag = "true";

			String submitToMethod = "TravelAppMngt_callDtl.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	

   }  // end of f9action
	
	/**
	 * @This method is used for show the Employee information.
	 */
	public String f9Employee()
	{
		try
		{
			
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
						 +"	CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID ,NVL(CADRE_NAME,' '),EMP_CADRE FROM HRMS_EMP_OFFC "
						 +"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
						 +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
						 +"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
						 +"	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
						 +" LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) " ;
			
			query += getprofileQuery(trApp);
			 query +=" AND EMP_STATUS='S'";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
			
						 
			
			String[] headers = {"Employee ID", "Employee Name"};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"trApp.empToken","trApp.empName","trApp.branchName","trApp.dept","trApp.desg","trApp.empId","trApp.gradeName","trApp.gradeCode"};

			int[] columnIndex = {0, 1,2,3,4,5,6,7};

			String submitFlag = "true";

			String submitToMethod = "TravelAppMngt_callExpense.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	} // end of f9Employee
	
	/**
	 * @This method is used for show the city Name
	 */
	
	public String f9FromPlace()
	{
		try
		{
			String query = " SELECT LOCATION_NAME,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE='Ci' ORDER BY LOCATION_NAME ";
			 
			
			String[] headers = {"Place Name","Place Id"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"trApp.fromPlace","trApp.fromPlaceId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	} // end of f9FromPlace
	/**
	 * @This method is used for show the city Name
	 */
	public String f9ToPlace()
	{
		try
		{
			String query = " SELECT LOCATION_NAME,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE='Ci' ORDER BY LOCATION_NAME ";
			 
			
			String[] headers = {"Place Name","Place Id"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"trApp.toPlace","trApp.toPlaceId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	} // end of f9ToPlace
	
	/**
	 * @This method is used for show the Journey type.
	 */
	public String f9Journey()
	{
		try
		{
			String query = " SELECT JOURNEY_NAME,JOURNEY_ID FROM HRMS_JOURNEY ";
			 
			
			String[] headers = {"Journey Type", "Journey TypeId"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"trApp.journeyName","trApp.journeyId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}  // end of f9Journey
	
	/**
	 * @This method is used for show journey class.
	 */
	public String f9JourneyClass()
	{
		try
		{
			String query = "  SELECT  JOURNEY_CLASS_NAME ,JOURNEY_CLASS_ID  FROM HRMS_JOURNEY_CLASS  "
                            +" WHERE  JOURNEY_CLASS_JOURNEY_ID = "+trApp.getJourneyId();
			 
			
			String[] headers = {"Journey Class", "Journey ClassId"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"trApp.journeyClassName","trApp.journeyClassId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	} //end of f9JourneyClass
	
	/**
	 * @This method is used for show journey name for schedule.
	 */
	public String f9SchJourney()
	{
		try
		{
			String query = " SELECT JOURNEY_NAME,JOURNEY_ID FROM HRMS_JOURNEY ";
			 
			
			String[] headers = {"Journey Type", "Journey TypeId"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"schJourneyMode"+trApp.getSchJournyId(),"schJourneyModeId"+trApp.getSchJournyId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}  //end of f9SchJourney
	
	/**
	 * @This method is used for show journey class for schedule.
	 */
	public String f9SchJourneyClass()
	{
		try
		{
			String query = "  SELECT  JOURNEY_CLASS_NAME ,JOURNEY_CLASS_ID  FROM HRMS_JOURNEY_CLASS  "; 
			 String jourId = request.getParameter("schJourneyModeId"+trApp.getSchJournyId());
			logger.info("jourId=========  "+jourId);
			              if(!(jourId.equals("")||jourId.equals("0")))
			              {
			            	  query += " WHERE  JOURNEY_CLASS_JOURNEY_ID = "+jourId;
			              }
			 
			
			String[] headers = {"Journey Class", "Journey ClassId"};

			String[] headerWidth = {"80", "20"};

			String[] fieldNames = {"schJourneyClass"+trApp.getSchJournyId(),"schJourneyClassId"+trApp.getSchJournyId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}   //end of f9SchJourneyClass
	
}
	
	
 
