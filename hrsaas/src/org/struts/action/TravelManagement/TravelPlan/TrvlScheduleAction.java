package org.struts.action.TravelManagement.TravelPlan;

import org.paradyne.bean.TravelManagement.TravelPlan.TrvlSchedule;
import org.paradyne.model.TravelManagement.TravelPlan.TrvlScheduleModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author krishna
 * 
 */

public class TrvlScheduleAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TrvlScheduleAction.class);
	TrvlSchedule trvlSchl;

	public void prepare_local() throws Exception {
		trvlSchl = new TrvlSchedule();
		trvlSchl.setMenuCode(810);
	}

	public Object getModel() {

		return trvlSchl;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		trvlSchl.setMenuCode(806);
		TrvlScheduleModel model = new TrvlScheduleModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		model.callStatus(trvlSchl, "P", request);
		model.terminate();
	}

	public String input() {
		TrvlScheduleModel model = new TrvlScheduleModel();
		model.initiate(context, session);
		boolean reqStaus = model.callRequistionStatus(trvlSchl);
		if (reqStaus == false) {
			addActionMessage("Insufficient priviliges to prepare schedule.");
			return null;
		}
		model.terminate();
		return "success";

	}

	public String callStatus() {
		TrvlScheduleModel model = new TrvlScheduleModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		trvlSchl.setStat(status);
		model.callStatus(trvlSchl, status, request);
		
		if (status.equals("P")) {
			trvlSchl.setPen("true");
		} else if (status.equals("ST")) {
			trvlSchl.setSchTr("true");
		} else if (status.equals("SA")) {
			trvlSchl.setSchAppr("true");
		} else if (status.equals("SR")) {
			trvlSchl.setSchReg("true");
		} else if (status.equals("SC")) {
			trvlSchl.setSchCan("true");
		} else if (status.equals("SCD")) {
			trvlSchl.setSchCanceled("true");
		}
		model.terminate();
		return "success";
	} // end of callStatus method

	public String callSchedule() {
		String trvlApplId = request.getParameter("trvAppId");
		// get status and AppId
		String statS = request.getParameter("stat");
		trvlSchl.setAppID(trvlApplId);
		trvlSchl.setTravelAppId(trvlApplId);
		trvlSchl.setStat(statS);
		TrvlScheduleModel model = new TrvlScheduleModel();
		model.initiate(context, session);
		model.callDtl(trvlSchl, trvlApplId, request);

		model.terminate();

		if (trvlSchl.getStat().equals("P")) {
			return "scheDule";
		} else if (trvlSchl.getStat().equals("ST")) {
			return "schTrvlList";
		} else if (trvlSchl.getStat().equals("SA")) {
			return "schTrvlList";
		} else if (trvlSchl.getStat().equals("SR")) {
			return "schRegList";
		} else if (trvlSchl.getStat().equals("SC")) {
			return "schCancel";
		}

		else if (trvlSchl.getStat().equals("SCD")) {
			return "schCanceled";
		}
		return "scheDule";

	}

	public String f9SchJourney() {
		try {
			String query = " SELECT NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),''),JOURNEY_ID FROM HRMS_TMS_JOURNEY ";

			String[] headers = { "Journey Type", "Journey TypeId" };

			String[] headerWidth = { "80", "20" };

			String[] fieldNames = { "travelMdAndCls" + trvlSchl.getRowId(),
					"travelMdAndClsId" + trvlSchl.getRowId() };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	} // end of f9SchJourney

	public String f9LocJourney() {
		try {
			String query = " SELECT NVL((JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME),''),JOURNEY_ID FROM HRMS_TMS_JOURNEY ";

			String[] headers = { "Journey Type", "Journey TypeId" };

			String[] headerWidth = { "80", "20" };

			String[] fieldNames = { "locConTrvlMod" + trvlSchl.getRowId(),
					"locConTrvlModId" + trvlSchl.getRowId() };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	} // end of f9SchJourney

	public String f9LodgCity() {
		try {
			String query = "  SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE='Ci' ORDER BY LOCATION_NAME  ";

			String[] headers = { "City Name" };

			String[] headerWidth = { "100" };

			String[] fieldNames = { "lodgCity" + trvlSchl.getRowId(),
					"lodgCityId" + trvlSchl.getRowId() };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return null;
		}
	}

	public String f9HotelType() {
		try {
			String query = " SELECT HOTEL_TYPE_NAME ,HOTEL_TYPE_ID  FROM HRMS_TMS_HOTEL_TYPE "
					+ " WHERE HOTEL_TYPE_STATUS ='A'ORDER BY HOTEL_TYPE_NAME ";

			String[] headers = { "Hotel Type Name" };

			String[] headerWidth = { "100" };

			String[] fieldNames = { "lodgHotelName" + trvlSchl.getRowId(),
					"lodgHotelType" + trvlSchl.getRowId() };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return null;
		}
	}

	public String f9RoomType() {
		try {
			String query = "  SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID  FROM HRMS_TMS_ROOM_TYPE "
					+ " WHERE ROOM_TYPE_STATUS='A'ORDER BY ROOM_TYPE_NAME ";

			String[] headers = { "Room Type Name" };

			String[] headerWidth = { "100" };

			String[] fieldNames = { "lodgRoomName" + trvlSchl.getRowId(),
					"lodgRoomType" + trvlSchl.getRowId() };

			int[] columnIndex = { 0, 1 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return null;
		}
	}

	// method save ie update the records in data base

	public String save() throws Exception {

		boolean result = false;
		// set the saveStatusFlag as 'U'
		trvlSchl.setSaveStatusFlag("U");

		result = add(trvlSchl);
		TrvlScheduleModel model = new TrvlScheduleModel();

		model.initiate(context, session);
		if (result) {
			addActionMessage("Application Saved successfully");
			model.callStatus(trvlSchl, "P", request);

		} else {
			addActionMessage(getText("Record cann't saved"));

		}
		return "success";
	}

	public String finalizeSchedule() {
		boolean result = false;
		// set the saveStatusFlag as 'U'
		trvlSchl.setSaveStatusFlag("S");
		result = add(trvlSchl);
		TrvlScheduleModel model = new TrvlScheduleModel();

		model.initiate(context, session);

		model.callStatus(trvlSchl, "P", request);
		if (result) {
			addActionMessage(getText("Schedule finalized successfully"));

		} else {
			addActionMessage(getText("Schedule can't finalized"));

		}
		return "success";
	}

	// common method for save and finalize Schedule
	public boolean add(TrvlSchedule bean) {
		boolean result = false;
		TrvlScheduleModel model = new TrvlScheduleModel();
		model.initiate(context, session);
		// save data in HRMS_TMS_SCH_DTL then update remaining tables
		result = model.saveSchlerData(trvlSchl, request);

		if (result) {
			model.upDateBookDtls(trvlSchl, request);
			model.upDateLocConDtls(trvlSchl, request);
			model.upDateLodgDtls(trvlSchl, request);
			model.terminate();
			return result;
		} else {
			return result;
		}

	}

	public String saveCanceled() {
		boolean result = false;
		TrvlScheduleModel model = new TrvlScheduleModel();
		model.initiate(context, session);
		result = model.saveCanceled(trvlSchl, request);
		if (result) {
			addActionMessage(getText("Record saved successfully"));
		} else {
			addActionMessage(getText("Record can't saved"));
		}
		model.terminate();
		return "success";
	}
 
	public String callAlterschUpdate() {
		trvlSchl.setSaveStatusFlag("U"); 
		TrvlScheduleModel model = new TrvlScheduleModel();
		model.initiate(context, session); 
		 callSchedule(); 
		 add(trvlSchl); 
		model.callAlterschUpdate(trvlSchl,request);
		model.callStatus(trvlSchl, "P", request);
		trvlSchl.setPen("true");
		model.terminate();
		 
		return "success";
	}
	
	public String f9AlterScheduler()
	{
		try
		{ 	 
			String query ="   SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,ALTER_SCH_SEC_SCH FROM HRMS_TMS_MANG_ALTER_SCH  "
						+"	   LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= ALTER_SCH_SEC_SCH) WHERE ALTER_SCH_PRI_SCH ="+trvlSchl.getUserEmpId();
						 
			
			String[] headers = {"Employee Id","Employee Token"};

			String[] headerWidth = {"30","70"};

			String[] fieldNames = {"altSchEmpToken","altSchEmpName","altSchEmpId"};

			int[] columnIndex = {0,1,2};

			String submitFlag = "true";

			String submitToMethod = "TrvlSchl_callAlterschUpdate.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}
} // end of class

