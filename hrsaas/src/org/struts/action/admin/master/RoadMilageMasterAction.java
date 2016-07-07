package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.RoadMilageMaster;

import org.paradyne.model.admin.master.RoadMilageMasterModel;
import org.struts.lib.ParaActionSupport;

public class RoadMilageMasterAction extends ParaActionSupport {

	RoadMilageMaster roadMilage;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		roadMilage =new RoadMilageMaster();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return roadMilage;
	}

	public RoadMilageMaster getRoadMilage() {
		return roadMilage;
	}

	public void setRoadMilage(RoadMilageMaster roadMilage) {
		this.roadMilage = roadMilage;
	}

	public String reset()
	{
		roadMilage.setClassCity("");
		roadMilage.setCityId("");
		roadMilage.setKiloMeter("");
		roadMilage.setRoadMilRate("");
		roadMilage.setId("");
		
		return "success";
	}
	
 	public String save()throws Exception {
	
		RoadMilageMasterModel model=new RoadMilageMasterModel();
		model.initiate(context,session);
		int km =Integer.parseInt(roadMilage.getKiloMeter());
		int rate =Integer.parseInt(roadMilage.getRoadMilRate());
		boolean flag =false;
		boolean flag1 =false;
		
		if(roadMilage.getId().equals(""))
		{
			if(km!=rate)
			{
			 flag=model.addCity(roadMilage);
			 if(flag)
			 {
				 addActionMessage("Record Saved Successfully");
				 
				 reset();
			 }
			}else
			{
				addActionMessage("Duplicate Record Found");
			}
					 
		}
		 else
		{
			 int km1 =Integer.parseInt(roadMilage.getKiloMeter());
				int rate1 =Integer.parseInt(roadMilage.getRoadMilRate());
				if(km1!=rate1)
				{
				  flag1 =model.modCity(roadMilage);
				}
			if(flag1)
			{
			addActionMessage("Record Updated Successfully");
			 reset();
			}
			else
			{
				addActionMessage("Duplicate Record Found");
			}
		} 
		model.terminate();
	 
		return "success";
	}
 
	
	public String delete()throws Exception {
		
		RoadMilageMasterModel model=new RoadMilageMasterModel();
		model.initiate(context,session);
		boolean result=model.deleteCity(roadMilage);
		if(result)
		{
			addActionMessage("Record Deleted Successfully");
		}
		model.terminate();
		reset();
		return "success";
	}
	
	public String report()throws Exception {	
		
		RoadMilageMasterModel model=new RoadMilageMasterModel();
		model.initiate(context, session);
			model.getCityReport(roadMilage);
			model.terminate();	
			return "report"; 
		} 
		
	
	
	public String f9selectaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT ROAD_CITYCLASS,ROAD_KILOMETER,ROAD_RATE,ROAD_ID FROM HRMS_ROADMILEAGE ORDER BY ROAD_ID ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Class Of City","Kilometer","Road Milage Rate"};
		
		String[] headerWidth={"40", "30","30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"roadMilage.classCity","roadMilage.kiloMeter","roadMilage.roadMilRate","roadMilage.id"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3};
		
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

	

	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		String query = "SELECT HRMS_LOCATION.LOCATION_CODE,  HRMS_LOCATION.LOCATION_CLASS "
			+ "FROM HRMS_LOCATION "
			+ "LEFT JOIN HRMS_LOCATION STAT ON (HRMS_LOCATION.LOCATION_PARENT_CODE = STAT.LOCATION_CODE) "
			+ "WHERE LEVEL = 3 "
			+ "START WITH HRMS_LOCATION.LOCATION_CODE = (SELECT HRMS_LOCATION.LOCATION_CODE FROM HRMS_LOCATION WHERE UPPER(LOCATION_NAME) LIKE UPPER('INDIA')) "
			+ "CONNECT BY PRIOR HRMS_LOCATION.LOCATION_CODE = HRMS_LOCATION.LOCATION_PARENT_CODE "
			+ "ORDER BY STAT.LOCATION_CODE";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"City Code", "City Name"};
		
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"roadMilage.cityId","roadMilage.classCity"};
		
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
