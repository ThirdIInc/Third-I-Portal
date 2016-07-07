package org.struts.action.TravelManagement.TravelClaim;

import org.paradyne.bean.TravelManagement.TravelClaim.TrvlSettmntDefaltr;
import org.paradyne.model.TravelManagement.TravelClaim.TrvlSettmntDefaltrModel;
import org.struts.lib.ParaActionSupport;

/*
 * author:Pradeep Sahoo
 * Date:22-12-2009
 */

public class TrvlSettmntDefaltrAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	TrvlSettmntDefaltr bean;

	public TrvlSettmntDefaltr getBean() {
		return bean;
	}

	public void setBean(TrvlSettmntDefaltr bean) {
		this.bean = bean;
	}

	public void prepare_local() {
		bean = new TrvlSettmntDefaltr();
		bean.setMenuCode(976);
	}

	public Object getModel() {
		return bean;
	}

	/**
	 * following function is called when Travel Advance Closed Defaulter List is
	 * clicked.
	 * 
	 * @return
	 */
	public String closedDefaltr() {
		try {
			bean.setDecPage("closed");
			TrvlSettmntDefaltrModel model = new TrvlSettmntDefaltrModel();
			model.initiate(context, session);
			//	bean.setMyPage("");
			if (bean.getMyPage().equals("")) {
				bean.setMyPage("1");
			}
			bean.setDeductFlag("true");
			request
					.setAttribute("flag",
							"Travel Advance Closed Defaulter List");
			model.getSettlementDefaltrClosed(bean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String input() {
		request
				.setAttribute("flag",
						"Travel Advance Settlement Defaulter List");
		TrvlSettmntDefaltrModel model = new TrvlSettmntDefaltrModel();
		bean.setDecPage("open");
		if (bean.getMyPage().equals("")) {
			bean.setMyPage("1");
		}
		model.initiate(context, session);
		model.getSettlementDefaltr(bean, request);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * following function is called when the Process button is clicked to
	 * process the data.
	 * 
	 * @return
	 */
	public String processData() {
		String[] trvlAppId = request.getParameterValues("trvlAppId");
		String[] trvlAppCode = request.getParameterValues("trvlAppCode");
		String[] empId = request.getParameterValues("empId");
		String[] advance = request.getParameterValues("advAmt");
		String[] deduction = request.getParameterValues("dedAmt");
		String[] hiddenChkBox = request.getParameterValues("hiddenChkBox");
		String[] disburseId = request.getParameterValues("disburseId");
		TrvlSettmntDefaltrModel model = new TrvlSettmntDefaltrModel();
		model.initiate(context, session);
		boolean result = model.saveData(bean, trvlAppId, trvlAppCode, empId,
				advance, deduction, hiddenChkBox, disburseId);
		if (result)
			addActionMessage("Record saved successfully.");
		input();
		bean.setMonth("");
		bean.setYear("");
		model.terminate();
		return "success";
	}

	/**
	 * following function is called when any row is double clicked in the
	 * defaulter list to display the employee details
	 * 
	 * @return
	 */
	public String getEmployeeDet() {

		TrvlSettmntDefaltrModel model = new TrvlSettmntDefaltrModel();
		model.initiate(context, session);
		String trvlAppId = request.getParameter("trvAppId");
		String trvAppCode = request.getParameter("trvAppCode");
		String empId = request.getParameter("empId");
		String advAmt = request.getParameter("advAmt");
		String type = request.getParameter("status");
		String defaultId = request.getParameter("defaultId");
		model.getEmployeeDetails(bean, trvlAppId, trvAppCode, empId, advAmt,
				type, defaultId);
		model.terminate();
		return "employeeDet";
	}

}
