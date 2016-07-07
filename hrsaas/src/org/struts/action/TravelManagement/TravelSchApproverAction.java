package org.struts.action.TravelManagement;

import org.paradyne.bean.TravelManagement.TravelSchApprover;
import org.paradyne.model.TravelManagement.TravelExpensesDisbursementModel;
import org.paradyne.model.TravelManagement.TravelSchApproverModel;
import org.struts.lib.ParaActionSupport;

public class TravelSchApproverAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelSchApproverAction.class);
	TravelSchApprover trvlSchAppr;

	public void prepare_local() throws Exception {
		trvlSchAppr = new TravelSchApprover();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return trvlSchAppr;
	}

	/**
	 * @return the trvlSchAppr
	 */
	public TravelSchApprover getTrvlSchAppr() {
		return trvlSchAppr;
	}

	/**
	 * @param trvlSchAppr the trvlSchAppr to set
	 */
	public void setTrvlSchAppr(TravelSchApprover trvlSchAppr) {
		this.trvlSchAppr = trvlSchAppr;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		trvlSchAppr.setMenuCode(806);
		TravelSchApproverModel model = new TravelSchApproverModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		model.callStatus(trvlSchAppr, "P", request);
		model.terminate();
	}

	public String callStatus() {
		TravelSchApproverModel model = new TravelSchApproverModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		trvlSchAppr.setStat(status);

		model.callStatus(trvlSchAppr, status, request);
		if (status.equals("P")) {
			trvlSchAppr.setPen("true");
		} else if (status.equals("A")) {
			trvlSchAppr.setApprvd("true");
		} else if (status.equals("R")) {
			trvlSchAppr.setRegctd("true");
		}
		model.terminate();
		return "success";
	} // end of callStatus method

	
	
	public String callForward() {
		TravelSchApproverModel model = new TravelSchApproverModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		trvlSchAppr.setStat(status);

		model.callStatus(trvlSchAppr, status, request);
		if (status.equals("P")) {
			trvlSchAppr.setPen("true");
		} else if (status.equals("A")) {
			trvlSchAppr.setApprvd("true");
		} else if (status.equals("R")) {
			trvlSchAppr.setRegctd("true");
		}
		model.terminate();
		return "success";
	} // end of callStatus method

	
	
	
	
	
	
} // end of class

