/**
 * 
 */
package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlHotelwiseCostReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAccomodationReportModel;
import org.paradyne.model.TravelManagement.TravelReports.TrvlHotelwiseCostReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 *
 */
public class TrvlHotelwiseCostReportAction extends ParaActionSupport {
	TrvlHotelwiseCostReport report = null;
	public TrvlHotelwiseCostReport getReport() {
		return report;
	}

	public void setReport(TrvlHotelwiseCostReport report) {
		this.report = report;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		report = new TrvlHotelwiseCostReport();
		report.setMenuCode(1193);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return report;
	}
	
	public String hotelwiseCostReport() throws Exception {
		TrvlHotelwiseCostReportModel model = new TrvlHotelwiseCostReportModel();
		model.initiate(context, session);
		model.generateHotelwiseCostReport(response, report);
		model.terminate();
		return null;
	}
	/**
	 * @method prepare_withLoginProfileDetails
	 * @purpose to set the currency type from currency master
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlHotelwiseCostReportModel model = new TrvlHotelwiseCostReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, report);
		model.terminate();
	
	}
}
