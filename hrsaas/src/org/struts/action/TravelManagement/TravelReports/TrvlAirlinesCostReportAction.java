/**
 * 
 */
package org.struts.action.TravelManagement.TravelReports;

import java.util.LinkedHashMap;

import org.paradyne.bean.TravelManagement.TravelReports.TrvlAirlinesCostReport;
import org.paradyne.model.TravelManagement.TravelReports.TrvlAirlinesCostReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 *
 */
public class TrvlAirlinesCostReportAction extends ParaActionSupport {
	TrvlAirlinesCostReport report = null;
	public TrvlAirlinesCostReport getReport() {
		return report;
	}

	public void setReport(TrvlAirlinesCostReport report) {
		this.report = report;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		report = new TrvlAirlinesCostReport();
		report.setMenuCode(1196);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return report;
	}
	
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		TrvlAirlinesCostReportModel model = new TrvlAirlinesCostReportModel();
		model.initiate(context, session);
		LinkedHashMap<String, String> travelModeMap = model.setTravelModes();
		report.setTravelModeMap(travelModeMap);
		model.setCurrencyList(response, report);
		model.terminate();
	}
	
	public String modewiseReport() throws Exception {
		TrvlAirlinesCostReportModel model = new TrvlAirlinesCostReportModel();
		model.initiate(context, session);
		model.generateModewiseCostReport(response, report);
		model.terminate();
		return null;
	}
	
	public String routewiseModewiseReport() throws Exception {
		TrvlAirlinesCostReportModel model = new TrvlAirlinesCostReportModel();
		model.initiate(context, session);
		model.generateRoutewiseModewiseCostReport(response, report);
		model.terminate();
		return null;
	}

}
