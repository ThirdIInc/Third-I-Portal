package org.struts.action.TravelManagement.TravelPlan;

import org.paradyne.bean.TravelManagement.TravelPlan.TravelScheduleApprover;
import org.paradyne.model.TravelManagement.TravelPlan.TravelScheduleApproverModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author Krishna
 *
 */

public class TravelScheduleApproverAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelScheduleApproverAction.class);
	TravelScheduleApprover trvlSchlApr;

	public void prepare_local() throws Exception {
		trvlSchlApr = new TravelScheduleApprover();
		trvlSchlApr.setMenuCode(806);

	}

	public Object getModel() {
		return trvlSchlApr;
	}

	/**
	 * @return the trvlSchlApr
	 */
	public TravelScheduleApprover getTrvlSchlApr() {
		return trvlSchlApr;
	}

	/**
	 * @param trvlSchlApr the trvlSchlApr to set
	 */
	public void setTrvlSchlApr(TravelScheduleApprover trvlSchlApr) {
		this.trvlSchlApr = trvlSchlApr;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		// trvlSchl.setMenuCode(806);
		TravelScheduleApproverModel model = new TravelScheduleApproverModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		model.callStatus(trvlSchlApr, "P", request);

		model.terminate();
	}

	public String callStatus() {
		logger.info("call status of Action");
		TravelScheduleApproverModel model = new TravelScheduleApproverModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		trvlSchlApr.setStat(status);
		model.callStatus(trvlSchlApr, status, request);
		if (status.equals("P")) {
			trvlSchlApr.setPen("true");
		} else if (status.equals("A")) {
			trvlSchlApr.setApprved("true");
			trvlSchlApr.setApprflag("true");
		} else if (status.equals("R")) {
			trvlSchlApr.setRegcted("true");
			trvlSchlApr.setApprflag("true");
		}
		model.terminate();

		logger.info("returning in call status of action");

		return "success";
	} // end of callStatus method

	public String callView() {

		String trvlApplId = request.getParameter("trvAppId");
		// get status and AppId
		String statS = request.getParameter("stat");
		//trvlSchlApr.setAppID(trvlApplId);
		trvlSchlApr.setTravelAppId(trvlApplId);
		trvlSchlApr.setAppID(trvlApplId);
		trvlSchlApr.setStat(statS);

		TravelScheduleApproverModel model = new TravelScheduleApproverModel();

		model.initiate(context, session);

		model.callDtl(trvlSchlApr, trvlApplId, request);

		model.terminate();

		if (trvlSchlApr.getStat().equals("P")) {
			return "SchApprPending";
		} else if (trvlSchlApr.getStat().equals("A")
				|| trvlSchlApr.getStat().equals("R")) {
			return "SchApprView";
		}

		return "success";
	}

	public String save() {
		boolean result = false;
		String[] travelAppId = request.getParameterValues("travelAppId");
		String[] travelEmpId = request.getParameterValues("travelEmpId");
		String[] chkStatus = request.getParameterValues("checkStatus");

		String[] comments = request.getParameterValues("comment");
		TravelScheduleApproverModel model = new TravelScheduleApproverModel();
		model.initiate(context, session);

		for (int i = 0; i < chkStatus.length; i++) {
			result = model.changeApplStatus(trvlSchlApr, travelAppId[i],
					chkStatus[i], comments[i]);

		} // end of for loop

		if (result) {
			addActionMessage("Record saved Successfully");
		} else {
			addActionMessage("Record can't be saved");
		}
		model.callStatus(trvlSchlApr, trvlSchlApr.getStat(), request);
		model.terminate();
		return "success";
	} // end of save method

	/**
	 * Method to approve the Scheduled travel application.
	 * @param bean
	 * @return boolean.
	 */

	public String approve() {

		//trvlSchlApr.setDecFlag("approve");
		trvlSchlApr.setSavingStatus("A");
		boolean result = send(trvlSchlApr);
		TravelScheduleApproverModel model = new TravelScheduleApproverModel();
		model.initiate(context, session);

		if (result) {
			addActionMessage("Application approved successfully");
			model.callStatus(trvlSchlApr, "A", request);
			trvlSchlApr.setApprved("true");
		} else {
			addActionMessage("Application can't be approved");
		}

		return "success";

	}

	public String reject() {

		//trvlSchlApr.setDecFlag("reject");
		trvlSchlApr.setSavingStatus("R");
		boolean result = send(trvlSchlApr);
		TravelScheduleApproverModel model = new TravelScheduleApproverModel();

		model.initiate(context, session);
		if (result) {
			addActionMessage("Application rejected successfully");
			model.callStatus(trvlSchlApr, "R", request);
			trvlSchlApr.setRegcted("true");
		} else {
			addActionMessage("Application can't be rejected");
		}
		model.terminate();
		return "success";

	}

	public boolean send(TravelScheduleApprover bean) {
		logger.info("I am in send method");
		boolean result = false;
		TravelScheduleApproverModel model = new TravelScheduleApproverModel();

		model.initiate(context, session);
		// save data in HRMS_TMS_SCH_DTL then update remaining tables
		result = model.saveSchlerData(trvlSchlApr, request);

		//model.upDateBookDtls(trvlSchlApr, request);
		//model.upDateLocConDtls(trvlSchlApr, request);
		//model.upDateLodgDtls(trvlSchlApr, request);
		

		model.terminate();
		if (result) {
			model.upDateBookDtls(trvlSchlApr, request);
			model.upDateLocConDtls(trvlSchlApr, request);
			model.upDateLodgDtls(trvlSchlApr, request);
			model.saveAdvancceDtls(trvlSchlApr);

			model.terminate();
		}
		return result;
	}

	public String callForward(){
		TravelScheduleApproverModel model = new TravelScheduleApproverModel(); 
		model.initiate(context, session);
		model.callForward(trvlSchlApr);
		model.callStatus(trvlSchlApr, "P", request); 
		trvlSchlApr.setPen("true");
		model.terminate();
		return "success";
	}
	public String f9AlterSchApproval()
	{
		try
		{ 	 
			String query ="   SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,ALTER_SCH_APPR_SEC_SCH_APPR FROM  HRMS_TMS_MANG_ALTER_SCH_APPR " 
						 +" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= ALTER_SCH_APPR_SEC_SCH_APPR)  "
						 +" WHERE ALTER_SCH_APPR_PRI_SCH_APPR  ="+trvlSchlApr.getUserEmpId();
						 
			
			String[] headers = {"Employee Id","Employee Token"};

			String[] headerWidth = {"30","70"};

			String[] fieldNames = {"altSchEmpToken","altSchEmpName","altSchEmpId"};

			int[] columnIndex = {0,1,2};

			String submitFlag = "true";

			String submitToMethod = "TravelSchAppr_callForward.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}
} // end of class

