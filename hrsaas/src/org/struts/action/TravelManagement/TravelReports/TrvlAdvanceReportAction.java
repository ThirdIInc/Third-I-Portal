package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlAdvanceReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAdvanceReportModel;
import org.struts.lib.ParaActionSupport;

public class TrvlAdvanceReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrvlAdvanceReportAction.class);

	TrvlAdvanceReport advanceBean = null;
	public void prepare_local() throws Exception {
		advanceBean = new TrvlAdvanceReport();
		advanceBean.setMenuCode(1191);

	}

	public Object getModel() {
		return advanceBean;
	}
	
	public String input() throws Exception {
		return INPUT ;
	}
	
	public String generateReport(){
		try {
			TrvlAdvanceReportModel model = new TrvlAdvanceReportModel();
			model.initiate(context, session);
			model.generateReport(advanceBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;	
	}
	
	/**
	 * @method prepare_withLoginProfileDetails
	 * @purpose to set the currency type from currency master
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlAdvanceReportModel model = new TrvlAdvanceReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, advanceBean);
		model.terminate();
	
	}
	
}
