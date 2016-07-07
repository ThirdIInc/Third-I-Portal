package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlFrequentTravellerReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAdvanceReportModel;
import org.paradyne.model.TravelManagement.TravelReports.TrvlFrequentTravellerReportModel;
import org.struts.lib.ParaActionSupport;

public class TrvlFrequentTravellerReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrvlFrequentTravellerReportAction.class);
	TrvlFrequentTravellerReport frequentBean = null;
	
	public void prepare_local() throws Exception {
		frequentBean = new TrvlFrequentTravellerReport();
		frequentBean.setMenuCode(1188);
	}

	public Object getModel() {
		return frequentBean;
	}
	
	public String input() throws Exception {
		return INPUT ;
	}
	
	public String generateReport(){
		try {
			TrvlFrequentTravellerReportModel model = new TrvlFrequentTravellerReportModel();
			model.initiate(context, session);
			model.generateReport(frequentBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;	
	}

}
