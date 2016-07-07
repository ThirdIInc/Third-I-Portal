/**
 * 
 */
package org.struts.action.TravelManagement.TravelProcess;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelMonitoring;
import org.paradyne.model.TravelManagement.TravelProcess.TravelMonitoringModel;
import org.struts.lib.ParaActionSupport;

import java.io.File;

/**
 * @author Pankaj_Jain
 *
 */
public class TravelMonitoringAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(TravelMonitoringAction.class);
	TravelMonitoring monitorBean = null;
	
	// abstract  
	public void prepare_local() throws Exception {
		monitorBean = new TravelMonitoring();
		monitorBean.setMenuCode(912);
	}
	
	public Object getModel() {
		return monitorBean;
	}
	
	public String input() throws Exception {
		getMonitoringRecords();
		return SUCCESS;
	}
	
	/** 
	 * Action that displays the travel details
	 * related to particular application
	 * @return : the JSP in which in all
	 * travel details will be shown
	 */
	public String showTravelDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.getTravelDetails(request,monitorBean);
		model.terminate();
		return "travelDtl";
	}
	
	/** 
	 * Action that displays the Accommodation details
	 * related to particular application
	 * @return : the JSP in which in all
	 * accommodation details will be shown
	 */
	public String showAccommodationDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.getAccomDetails(request,monitorBean);
		model.terminate();
		return "accomDtl";
	}
	
	/** 
	 * Action that displays the local conveyance details
	 * related to particular application
	 * @return : the JSP in which in all
	 * local conveyance details will be shown
	 */
	public String showLocalConveyanceDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.getLocConvDetails(request,monitorBean);
		model.terminate();
		return "locConvDtl";
	}
	
	/**
	 * Action that is called when the application
	 * is forwarded to approver if there is some 
	 * change in schedule or process.
	 * This action is called when user clicks Forward 
	 * To Approver button on monitoring screen.
	 * @return : displays the monitoring screen 
	 * again.
	 */
	public String forwardToApprover()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.updateStatus(request,"CO");
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	/**
	 * Action that is called when the application
	 * is forwarded to scheduler if the process is
	 * .
	 * This action is called when user clicks Forward 
	 * To Approver button on monitoring screen.
	 * @return : displays the monitoring screen 
	 * again.
	 */
	public String forwardToScheduler()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.updateStatus(request,"CE");
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	/**
	 * Action that is called when the scheduler
	 * clicks Start Booking on user screen.
	 * @return : displays the monitoring screen 
	 * again.
	 */
	public String startBooking()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.callDtl(monitorBean,request);
		model.terminate();
		return "book";
	}
	
	/**
	 * Action that is called when the Approver
	 * clicks Approve on details screen.
	 * @return : displays the monitoring screen 
	 * again.
	 */
	public String approve()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.approve(request.getParameter("type"),request.getParameter("monitorId"),monitorBean,request);
		addActionMessage(getMessage("save"));
		getMonitoringRecords();
		model.terminate();
		return SUCCESS;
	}
	
	
	/**
	 * Action that is called when the scheduler
	 * clicks on Save button on Travel Booking Screen.
	 */
	public String book() throws Exception{ 
		
		String[] jourNo = request.getParameterValues("jourNo");
		String[] ticketNo = request.getParameterValues("ticketNo");
		String[] jourCost = request.getParameterValues("jourCost");		
		String[] jourClsId = request.getParameterValues("jourClsId");
		String[] jourDate = request.getParameterValues("jourDate");
		String[] jourTime = request.getParameterValues("jourTime");
		String[] JournDtlId = request.getParameterValues("JournDtlId");
		String[] jourDetails = request.getParameterValues("jourDetails");
		String[] jourCancelCharge = request.getParameterValues("cancelChargeTvl");
		String[] jourRefundAmt = request.getParameterValues("refundAmtTvl");
		String[] jourComments = request.getParameterValues("commentsTvl");
		String[] jourFileName = request.getParameterValues("uploadFileTvl");
		
		String[] locConCost = request.getParameterValues("locConCost");
		String[] locConId = request.getParameterValues("locConId");
		String[] locConDtlId = request.getParameterValues("locConDtlId");
		String[] locDetails = request.getParameterValues("locDetails");
		String[] locCancelCharge = request.getParameterValues("cancelChargeLoc");
		String[] locRefundAmt = request.getParameterValues("refundAmtLoc");
		String[] locComments = request.getParameterValues("commentsLoc");
		String[] locFileName = request.getParameterValues("uploadFileLoc");
		
		String[] lodgBookAmt = request.getParameterValues("lodgBookAmt");
		String[] lodgId = request.getParameterValues("lodgId");
		String[] lodgDtlId = request.getParameterValues("lodgDtlId");
		String[] lodgDetails = request.getParameterValues("accDetails");
		String[] lodgCancelCharge = request.getParameterValues("cancelChargeAcc");
		String[] lodgRefundAmt = request.getParameterValues("refundAmtAcc");
		String[] lodgComments = request.getParameterValues("commentsAcc");
		String[] lodgFileName = request.getParameterValues("uploadFileAcc");
		
		boolean flag=false;
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		flag=model.book(monitorBean,request,jourNo,ticketNo,jourCost,
				locConCost,lodgBookAmt,locConId,lodgId,jourClsId,jourDate,
				jourTime,JournDtlId,lodgDtlId,locConDtlId,jourDetails,
				lodgDetails,locDetails,
				lodgCancelCharge,lodgRefundAmt,lodgComments
				,jourCancelCharge,jourRefundAmt,jourComments
				,locCancelCharge,locRefundAmt,locComments
				,jourFileName, lodgFileName, locFileName);
			addActionMessage("Record saved succesfully.");
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	/**
	 * Method that calls model method showMonitoringRecords()
	 * to fetch the records that are under monitoring 
	 * control.
	 */
	public String getMonitoringRecords()
	{
		try {
			TravelMonitoringModel model = new TravelMonitoringModel();
			model.initiate(context, session);
			model.showMonitoringRecords(monitorBean,request);
			model.terminate();
		}catch(Exception e)
		{
			logger.error("Exception in TravelMonitoring in getMonitoringRecords method : "+e);
		}
		return SUCCESS;
	}
	
	/**
	 * Method to add a row to option in journey details
	 * @return journey details jsp
	 */
	public String addRowToOptionForTravel()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.addRowToOption_Travel(monitorBean,request);
		model.terminate();
		return "travelDtl";
	}
	
	/**
	 * Method to add an option for journey
	 * @return journey details jsp
	 */
	public String addOptionForTravel()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.addOption_Travel(monitorBean,request);
		model.terminate();
		return "travelDtl";
	}
	
	/**
	 * Method to remove an option for journey
	 * @return journey details jsp
	 */
	public String removeOptionForTravel()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.removeOption_Travel(monitorBean,request);
		model.terminate();
		return "travelDtl";
	}
	
	/**
	 * Method to remove a row to option in journey details
	 * @return journey details jsp
	 */
	public String removeRowOptionForTravel()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.removeOptionFromRow_Travel(monitorBean,request);
		model.terminate();
		return "travelDtl";
	}
	
	/**
	 * Action is called when scheduler/ sub scheduler
	 * clicks on save to provide the options
	 * @return browses the scheduler/ sub scheduler to monitor jsp
	 */
	public String saveTravelDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.save_Travel(monitorBean,request);
			addActionMessage(getMessage("save"));
		}catch(Exception e)
		{
			logger.error("Error while saving travel details : "+e);
			addActionMessage(getMessage("save.error"));
		}
		getMonitoringRecords();
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * Action is called when applicant/initiator
	 * clicks on save to provide the options
	 * @return browses the scheduler/ sub scheduler to monitor jsp
	 */
	public String acceptTravelDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.accept_Travel(monitorBean,request);
			addActionMessage(getMessage("save"));
		}catch(Exception e)
		{
			logger.error("Error while saving travel details : "+e);
			addActionMessage(getMessage("save.error"));
		}
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	/**
	 * Action is called when 
	 * 1 :- user says self managed or none of these
	 * 2 :- approver rejects the policy violated option
	 * @return browses the applicant/approver to monitor jsp
	 */
	public String declineTravelDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.decline_Travel(monitorBean,request);
			addActionMessage("Record saved successfully");
		}catch(Exception e)
		{
			logger.error("Error while saving travel details : "+e);
			addActionMessage("Error while saving record");
		}
		
		
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	/**
	 * Action to check whether the user is violating the policy
	 * at the tie of accepting the options
	 * @return string indicating selected option and allowed option in 
	 * policy.
	 */
	public void isTravelPolicyViolate()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		String status = model.isPolicyViolate(request.getParameter("empId"), 
				request.getParameter("classId"), request.getParameter("cost"));
		model.terminate();
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(status);
			logger.info("status : "+status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String addRowToOptionForAccom()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.addRowToOption_Accom(monitorBean,request);
		model.terminate();
		return "accomDtl";
	}
	
	public String addOptionForAccom()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.addOption_Accom(monitorBean,request);
		model.terminate();
		return "accomDtl";
	}
	
	public String removeOptionForAccom()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.removeOption_Accom(monitorBean,request);
		model.terminate();
		return "accomDtl";
	}
	public String removeRowOptionForAccom()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.removeOptionFromRow_Accom(monitorBean,request);
		model.terminate();
		return "accomDtl";
	}
	
	public String saveAccomDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.save_Accom(monitorBean,request);
			addActionMessage(getMessage("save"));
		}catch(Exception e)
		{
			logger.error("Error while saving travel details : "+e);
			addActionMessage(getMessage("save.error"));
		}
		getMonitoringRecords();
		model.terminate();
		return SUCCESS;
	}
	
	public String acceptAccomDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.accept_Accom(monitorBean,request);
			addActionMessage(getMessage("save"));
		}catch(Exception e)
		{
			logger.error("Error while saving travel details : "+e);
			addActionMessage(getMessage("save.error"));
		}
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	public String declineAccomDetails()
	{
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.decline_Accom(monitorBean,request);
			addActionMessage("Record saved successfully");
		}catch(Exception e)
		{
			logger.error("Error while saving travel details : "+e);
			addActionMessage("Error while saving record");
		}
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	public void isAccomPolicyViolate()
	{
		String classId = request.getParameter("classId");
		String empId = request.getParameter("empId");
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		String status = model.isPolicyViolateAccom(empId, classId);
		model.terminate();
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(status);
			logger.info("status : "+status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String f9HotelType()
	{
		String query = " SELECT HOTEL_TYPE_NAME,HOTEL_TYPE_ID,'','' FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_STATUS='A' ORDER BY HOTEL_LEVEL";
		
		String[] headers = {getMessage("hotel.type")};

		String[] headerWidth = {"100"};

		String[] fieldNames = {"hotelTypeName_"+monitorBean.getRowId(), "hotelTypeCode_"+monitorBean.getRowId(),"roomTypeName_"+monitorBean.getRowId(), "roomTypeCode_"+monitorBean.getRowId()};

		int[] columnIndex = {0, 1,2,3};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9RoomType()
	{
		String query = " SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE "
					 + " INNER JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE) "
					 + " WHERE ROOM_TYPE_STATUS='A'"
					 + " AND ROOM_HOTEL_TYPE = "+monitorBean.getDeskFlag()
					 + " ORDER BY HOTEL_LEVEL,ROOM_LEVEL ";
		
		String[] headers = {getMessage("room.type")};

		String[] headerWidth = {"100"};

		String[] fieldNames = {"roomTypeName_"+monitorBean.getRowId(), "roomTypeCode_"+monitorBean.getRowId()};

		int[] columnIndex = {0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String addRowToOptionForLocConDtls() {
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.addRowToOption_LocCon(monitorBean, request);
		model.terminate();
		return "locConvDtl";
	}

	public String removeRowOptionForLocConDtls() {
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.removeOptionFromRow_LocCon(monitorBean, request);
		model.terminate();
		return "locConvDtl";
	}

	public String addOptionForLocConDtls() {
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.addOption_LocCon(monitorBean, request);
		model.terminate();
		return "locConvDtl";
	}

	public String removeOptionForLocConDtls() {
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.removeOption_LocCon(monitorBean, request);
		model.terminate();
		return "locConvDtl";
	}

	public String saveLocConDetails() {
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.save_LocCon(monitorBean, request);
			addActionMessage(getMessage("save"));
		} catch (Exception e) {
			logger.error("Error while saving travel details : " + e);
			addActionMessage(getMessage("save.error"));
		}
		getMonitoringRecords();
		model.terminate();
		return SUCCESS;
	}

	public String acceptLocConDetails() {
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.accept_LocCon(monitorBean, request);
			addActionMessage(getMessage("save"));
		} catch (Exception e) {
			logger.error("Error while saving travel details : " + e);
			addActionMessage(getMessage("save.error"));
		}
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}

	public String declineLocConDetails() {
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		try {
			model.decline_LocCon(monitorBean, request);
			addActionMessage("Record saved successfully");
		} catch (Exception e) {
			logger.error("Error while saving travel details : " + e);
			addActionMessage("Error while saving record");
		}
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	public String cancel()
	{
		logger.info("I am in cancel-----");
		TravelMonitoringModel model = new TravelMonitoringModel();
		model.initiate(context, session);
		model.cancel(request,monitorBean);
		addActionMessage(getMessage("save"));
		model.terminate();
		getMonitoringRecords();
		return SUCCESS;
	}
	
	public String uploadTicket()
	{
		File f  = null;
		try {
			
			f  = new File("c:/myfile.txt");
			FileUtils.copyFile(monitorBean.getMyFile(),f);
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "uploadFile";
	}
	
	/**
	 * following function is called when View button nis clicked in the candDataBankView.jsp page
	 * This function displays the candidate's cv.
	 * @throws Exception
	 */
	public void viewCV()throws Exception{
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream =null;
		String fileName="";
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			fileName= request.getParameter("fileName");
			if(fileName==null){
				if(fileName.length()<=0){
					fileName = "blank.doc";
				}
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/TMS/" + poolName+ "/Tickets/"+fileName;
			oStream = response.getOutputStream();
			
			response.setHeader("Content-type", "application/msword"); 
			response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
		
			int iChar;
			fsStream = new FileInputStream(path);
			
			while ((iChar = fsStream.read()) != -1)
			{
				oStream.write(iChar);
			}
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch",e);
			addActionMessage("Resume not found");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			if(fsStream!=null){
			fsStream.close();  
			}
			if(oStream!=null){
			oStream.flush();
			oStream.close();
			}
		}
		
		
		//return null;
	}
}
