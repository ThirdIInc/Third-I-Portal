package org.struts.action.TravelManagement.TravelPlan;

/**
 * Dilip
 * 15-08-2008
 **/

import org.paradyne.bean.TravelManagement.TravelPlan.TravelAdvanceDisbursement;
import org.paradyne.model.TravelManagement.TravelPlan.TravelAdvanceDisbursementModel;
import org.struts.lib.ParaActionSupport;

/**
 * This class is used for prepare the schedule for travel.
 */
public class TravelAdvanceDisbursementAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	TravelAdvanceDisbursement travelDisburs;

	public void prepare_local() throws Exception {
		travelDisburs = new TravelAdvanceDisbursement();
		travelDisburs.setMenuCode(795);
		logger.info("===111111=============" + travelDisburs.getUserEmpId());
	}

	public Object getModel() {
		// logger.info("=======22222========="+travelDisburs.getUserEmpId());
		return travelDisburs;
	}

	@Override
	/*
	 * public String input() throws Exception { TravelAdvanceDisbursementModel
	 * model = new TravelAdvanceDisbursementModel(); model.initiate(context,
	 * session); model.callStatus(); model.terminate(); return INPUT; }
	 */
	public void prepare_withLoginProfileDetails() throws Exception {

		TravelAdvanceDisbursementModel model = new TravelAdvanceDisbursementModel();
		model.initiate(context, session);
		model.callStatus(travelDisburs, request);
		
		model.terminate();

	}

	/**
	 * @This method is used for show view TravelSchedule details.
	 */

	/**
	 * @return success
	 */
	public String callStatus() {
		TravelAdvanceDisbursementModel model = new TravelAdvanceDisbursementModel();
		model.initiate(context, session);
		
		String status = "";
		System.out.println("========"+travelDisburs.getStatus());
		logger.info("========"+travelDisburs.getStatus());
		String stat = "";
		try {
			status = request.getParameter("payStatus");
			
		
			//status = String.valueOf(status.charAt(0));
		}// end of try
		catch (Exception e) {
			// TODO: handle exception
		
		}// end of catch
		if (status == null || status.equals("")) {
			status = "pend";
		}// end of if
		
		if (status.equals("pend")) {
		 
			stat = "Pending Advance List";
		}// end of if
		 if (status.equals("paid")) {
				
				 travelDisburs.setFlag("false");
		 	 stat = "Paid Advance List";
		
		} 
		 request.setAttribute("stat", stat);
		model.callStatus(travelDisburs, request);
		model.terminate();
		return "success";
	} // end of callStatus

	public TravelAdvanceDisbursement getTravelDisburs() {
		return travelDisburs;
	}

	public void setTravelDisburs(TravelAdvanceDisbursement travelDisburs) {
		this.travelDisburs = travelDisburs;
	}

	
	/*public String callBack()
	{
		TravelAdvanceDisbursementModel model = new TravelAdvanceDisbursementModel();
		model.initiate(context, session);
		String stat = "Paid Advance List";
		 request.setAttribute("stat", stat);
		model.terminate();
		return "success";
	}*/
	public String callMod() {
		TravelAdvanceDisbursementModel model = new TravelAdvanceDisbursementModel();
		model.initiate(context, session);
		logger.info("=====callMod=========" + travelDisburs.getUserEmpId());
		String empCode = request.getParameter("empCode");// leave
		
		travelDisburs.setEmpId(empCode);
		model.callStatus(travelDisburs, request);
		model.terminate();
		return "success";
	} // end of callStatus

	public String callView() {
		// String travelAppId = request.getParameter("traSchViewNo");

		TravelAdvanceDisbursementModel model = new TravelAdvanceDisbursementModel();
		model.initiate(context, session);
		// model.setApplication(travelDisburs ,travelAppId);
		String empCode = request.getParameter("empCode");// leave
		String appId = request.getParameter("appCode");// leave
		
		travelDisburs.setEmpId(empCode);
		travelDisburs.setAppId(appId);
		
		model.callDtl(travelDisburs, request);
		model.callMod(travelDisburs, request);
		
		// model.setApprover(travelDisburs);
		model.terminate();

		return "advancePaymentDetails";
	}

	public String save() throws Exception {
		
		boolean result;
		TravelAdvanceDisbursementModel model = new TravelAdvanceDisbursementModel();
		model.initiate(context, session);
		String empCode = request.getParameter("empCode");// leave
		String appId = request.getParameter("appCode");// leave
		travelDisburs.setEmpId(travelDisburs.getEmpId());
		travelDisburs.setAppId(travelDisburs.getAppId());
		result = model.getAddPayDetails(travelDisburs);
		
		if (result) {

			addActionMessage(getText("Record saved successfully"));
			model.callStatus(travelDisburs, request);
		} else {
			addActionMessage(getText("Record cann't saved successfully"));

		}

		model.terminate();
		return "success";
	}
	
	 public String paymentDtl()throws Exception{
		 
		  TravelAdvanceDisbursementModel model = new TravelAdvanceDisbursementModel();
			model.initiate(context, session);
			// model.setApplication(travelDisburs ,travelAppId);
			String empCode = request.getParameter("empCode");// leave
			String appId = request.getParameter("appCode");// leave
			travelDisburs.setEmpId(empCode);
			 travelDisburs.setAppId(appId);
			 model.callDtl(travelDisburs, request);
			 model.callMod(travelDisburs, request);
			 model.paymentDtl(travelDisburs, request);
			
		  model.terminate();
		  return "paymentDetails";
 	 }
	 
}
