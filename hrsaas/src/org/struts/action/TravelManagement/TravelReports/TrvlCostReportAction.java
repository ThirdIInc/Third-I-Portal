/**
 * 
 */
package org.struts.action.TravelManagement.TravelReports;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlCostReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlCostReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author REEBA_JOSEPH
 *
 */
public class TrvlCostReportAction extends ParaActionSupport {
	TrvlCostReport report = null;
	public TrvlCostReport getReport() {
		return report;
	}

	public void setReport(TrvlCostReport report) {
		this.report = report;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		report = new TrvlCostReport();
		report.setMenuCode(1187);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return report;
	}
	
	public String employeewiseCostReport() throws Exception {
		TrvlCostReportModel model = new TrvlCostReportModel();
		model.initiate(context, session);
		model.generateEmployeewiseCostReport(response, report);
		model.terminate();
		return null;
	}
	
	public String deptwiseCostReport() throws Exception {
		TrvlCostReportModel model = new TrvlCostReportModel();
		model.initiate(context, session);
		model.generateDeptwiseCostReport(response, report);
		model.terminate();
		return null;
	}
	
	public String zonewiseCostReport() throws Exception {
		TrvlCostReportModel model = new TrvlCostReportModel();
		model.initiate(context, session);
		model.generateZonewiseCostReport(response, report);
		model.terminate();
		return null;
	}
	
	public String projectwiseCostReport() throws Exception {
		TrvlCostReportModel model = new TrvlCostReportModel();
		model.initiate(context, session);
		model.generateProjectwiseCostReport(response, report);
		model.terminate();
		return null;
	}
	
	public String cutomerwiseCostReport() throws Exception {
		TrvlCostReportModel model = new TrvlCostReportModel();
		model.initiate(context, session);
		model.generateCustomerwiseCostReport(response, report);
		model.terminate();
		return null;
	}
	/**
	 * @method prepare_withLoginProfileDetails
	 * @purpose to set the currency type from currency master
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlCostReportModel model = new TrvlCostReportModel();
		model.initiate(context, session);
		model.setCurrencyList(response, report);
		model.terminate();
	
	}

}
