package org.struts.action.TravelManagement;

import org.paradyne.bean.TravelManagement.TravelExpensesDisbursement;
import org.paradyne.model.TravelManagement.TravelExpensesDisbursementModel;
import org.struts.lib.ParaActionSupport;

public class TravelExpensesDisbursementAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelExpensesDisbursementAction.class);
	TravelExpensesDisbursement trvldisb;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		trvldisb = new TravelExpensesDisbursement();
		trvldisb.setMenuCode(803);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return trvldisb;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		trvldisb.setMenuCode(803);
		TravelExpensesDisbursementModel model = new TravelExpensesDisbursementModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		model.callStatus(trvldisb, "P", request);
		model.terminate();
	}

	public String callStatus() {
		TravelExpensesDisbursementModel model = new TravelExpensesDisbursementModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		trvldisb.setStat(status);
		model.callStatus(trvldisb, status, request);
		if (status.equals("P")) {
			trvldisb.setPen("true");
		} else if (status.equals("B")) {
			trvldisb.setBal("true");
		} else if (status.equals("F")) {
			trvldisb.setFull("true");
		}
		model.terminate();
		return "success";
	} // end of callStatus method

	public String callExpense() {
		logger.info("Eshrath...........");
		String travelExpId = request.getParameter("travelExpense");
		String statS = request.getParameter("stat");
		String trvlAppId = request.getParameter("travelAppId");
		logger.info("STATUS++++++++++++++" + statS);
		trvldisb.setNavStatus(statS);
		trvldisb.setExpDisbId(travelExpId);
		trvldisb.setExpAppId(trvlAppId);

		TravelExpensesDisbursementModel model = new TravelExpensesDisbursementModel();
		model.initiate(context, session);

		model.callDtl(trvldisb, travelExpId);

		model.terminate();

		if (statS.equals("P")) {
			return "trvlExpense";
		} else if (statS.equals("B")) {
			return "trvlBalance";
		} else {
			return "trvlFull";
		}

	}

	public String save() {

		TravelExpensesDisbursementModel model = new TravelExpensesDisbursementModel();

		model.initiate(context, session);

		boolean result = false;

		//check status

		if (trvldisb.getNavStatus().equals("P")) {

			result = model.saveExpnsDtls(trvldisb);
			model.callStatus(trvldisb, "P", request);

		} else if (trvldisb.getNavStatus().equals("B")) {
			result = model.saveBalDtls(trvldisb);
			model.callStatus(trvldisb, "B", request);
		}

		if (result) {
			addActionMessage(getText("addMessage", ""));
			//model.callStatus(trvldisb, "B", request);
		}// end of if
		else {
			addActionMessage("Duplicate entry found");
		}// end of else

		model.terminate();
		return reset();
	}

	public String reset() {

		return "success";
	}

} // end of class

