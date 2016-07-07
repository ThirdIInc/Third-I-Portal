/**
 * 
 */
package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlAccomodationReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAccomodationReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 *
 */
public class TrvlAccomodationReportAction extends ParaActionSupport {
	TrvlAccomodationReport report = null;
	public TrvlAccomodationReport getReport() {
		return report;
	}

	public void setReport(TrvlAccomodationReport report) {
		this.report = report;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		report = new TrvlAccomodationReport();
		report.setMenuCode(1190);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return report;
	}
	
	public String empwiseAccomodationReport() throws Exception {
		TrvlAccomodationReportModel model = new TrvlAccomodationReportModel();
		model.initiate(context, session);
		model.generateEmpwiseAccommodationReport(response, report);
		model.terminate();
		return null;
	}
	
	public String deptwiseAccomodationReport() throws Exception {
		TrvlAccomodationReportModel model = new TrvlAccomodationReportModel();
		model.initiate(context, session);
		model.generateDeptwiseAccommodationReport(response, report);
		model.terminate();
		return null;
	}
	
	public String zonewiseAccomodationReport() throws Exception {
		TrvlAccomodationReportModel model = new TrvlAccomodationReportModel();
		model.initiate(context, session);
		model.generateZonewiseAccommodationReport(response, report);
		model.terminate();
		return null;
	}
	
	/**
	 * @method prepare_withLoginProfileDetails
	 * @purpose to set the currency type from currency master
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlAccomodationReportModel model = new TrvlAccomodationReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, report);
		model.terminate();
	
	}

}
