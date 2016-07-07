/**
 * 
 */
package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlOutstationMedReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAccomodationReportModel;
import org.paradyne.model.TravelManagement.TravelReports.TrvlOutstationMedReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 *
 */
public class TrvlOutstationMedReportAction extends ParaActionSupport {
	TrvlOutstationMedReport report = null;
	public TrvlOutstationMedReport getReport() {
		return report;
	}

	public void setReport(TrvlOutstationMedReport report) {
		this.report = report;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		report = new TrvlOutstationMedReport();
		report.setMenuCode(1195);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return report;
	}
	
	public String outstationMediumReport() throws Exception {
		TrvlOutstationMedReportModel model = new TrvlOutstationMedReportModel();
		model.initiate(context, session);
		model.generateOutstationCarReport(response, report);
		model.terminate();
		return null;
	}

}
