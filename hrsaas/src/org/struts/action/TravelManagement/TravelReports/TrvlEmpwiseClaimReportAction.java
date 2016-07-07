/**
 * 
 */
package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlEmpwiseClaimReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlEmpwiseClaimReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 *
 */
public class TrvlEmpwiseClaimReportAction extends ParaActionSupport {
	TrvlEmpwiseClaimReport report = null; 
	public TrvlEmpwiseClaimReport getReport() {
		return report;
	}

	public void setReport(TrvlEmpwiseClaimReport report) {
		this.report = report;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		report = new TrvlEmpwiseClaimReport();
		report.setMenuCode(1192);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return report;
	}
	
	public String empwiseClaimReport() throws Exception {
		TrvlEmpwiseClaimReportModel model = new TrvlEmpwiseClaimReportModel();
		model.initiate(context, session);
		model.generateEmpwiseClaimReport(response, report);
		model.terminate();
		return null;
	}
	/**
	 * @method prepare_withLoginProfileDetails
	 * @purpose to set the currency type from currency master
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlEmpwiseClaimReportModel model = new TrvlEmpwiseClaimReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, report);
		model.terminate();
	
	}

}
