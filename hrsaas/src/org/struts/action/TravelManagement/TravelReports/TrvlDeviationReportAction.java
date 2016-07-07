/**
 * 
 */
package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlDeviationReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlDeviationReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 *
 */
public class TrvlDeviationReportAction extends ParaActionSupport {
	TrvlDeviationReport report = null;
	public TrvlDeviationReport getReport() {
		return report;
	}

	public void setReport(TrvlDeviationReport report) {
		this.report = report;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		report = new TrvlDeviationReport();
		report.setMenuCode(1194);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return report;
	}
	
	public String deviationReport() throws Exception {
		TrvlDeviationReportModel model = new TrvlDeviationReportModel();
		model.initiate(context, session);
		model.generateDeviationReport(response, report);
		model.terminate();
		return null;
	}

}
