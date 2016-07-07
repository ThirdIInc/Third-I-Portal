package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.AdvanceClaimDisbursementReportBean;
import org.paradyne.model.TravelManagement.TravelReports.AdvanceClaimDisbursementReportModel;

import org.struts.lib.ParaActionSupport;

public class AdvanceClaimDisbursementReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AdvanceClaimDisbursementReportAction.class);
	AdvanceClaimDisbursementReportBean bean;

	public void prepare_local() throws Exception {
		bean = new AdvanceClaimDisbursementReportBean();
		bean.setMenuCode(1131);
		getNavigationPanel(1);
	}

	public Object getModel() {
		return bean;
	}

	public String input() {
		AdvanceClaimDisbursementReportModel model = new AdvanceClaimDisbursementReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, bean);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}

	public String generateReport() {
		try {
			AdvanceClaimDisbursementReportModel model = new AdvanceClaimDisbursementReportModel();
			model.initiate(context, session);
			model.generateReport(bean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String reset() {
		try {
			bean.setFromDate("");
			bean.setToDate("");
			bean.setPaymentMode("");
			bean.setReportType("X");
			AdvanceClaimDisbursementReportModel model = new AdvanceClaimDisbursementReportModel();
			model.initiate(context, session);
			model.setCurrencyList(response, bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		
		return INPUT;
	}
}
