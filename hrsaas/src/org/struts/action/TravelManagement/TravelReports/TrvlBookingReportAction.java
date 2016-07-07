package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlBookingReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlBookingReportModel;
import org.paradyne.model.TravelManagement.TravelReports.TrvlFrequentTravellerReportModel;
import org.struts.lib.ParaActionSupport;

public class TrvlBookingReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrvlBookingReportAction.class);
	
	TrvlBookingReport bookingBean = null;
	@Override
	public void prepare_local() throws Exception {
		bookingBean = new TrvlBookingReport();
		bookingBean.setMenuCode(1188);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bookingBean;
	}
	
	public String input() {
		TrvlBookingReportModel model = new TrvlBookingReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, bookingBean);
		model.terminate();
		return INPUT ;
	}
	
	public String generateReport(){
		try {
			TrvlBookingReportModel model = new TrvlBookingReportModel();
			model.initiate(context, session);
			model.generateReport(bookingBean, response);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;	
	}
	
	
	
}
