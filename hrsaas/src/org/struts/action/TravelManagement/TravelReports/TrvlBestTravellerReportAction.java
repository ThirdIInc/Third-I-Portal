package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlAdvanceReport;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlBestTravellerReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAdvanceReportModel;
import org.paradyne.model.TravelManagement.TravelReports.TrvlBestTravellerReportModel;
import org.struts.lib.ParaActionSupport;

public class TrvlBestTravellerReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrvlBestTravellerReportAction.class);

	TrvlBestTravellerReport bestTravellerBean = null;
	public void prepare_local() throws Exception {
		bestTravellerBean = new TrvlBestTravellerReport();
		bestTravellerBean.setMenuCode(1189);

	}

	public Object getModel() {
		return bestTravellerBean;
	}
	
	public String input() throws Exception {
		return INPUT ;
	}
	
	public String generateReport(){
		try {
			TrvlBestTravellerReportModel model = new TrvlBestTravellerReportModel();
			model.initiate(context, session);
			model.generateReport(bestTravellerBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;	
	}

}
