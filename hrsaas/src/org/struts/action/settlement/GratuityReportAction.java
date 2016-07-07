/**
 * 
 */
package org.struts.action.settlement;

import org.paradyne.bean.settlement.GratuityReport;
import org.paradyne.model.admin.srd.ManpowerSnapshotModel;
import org.paradyne.model.settlement.GratuityReportModel;
import org.struts.action.admin.srd.ManpowerSnapshotAction;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class GratuityReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(GratuityReportAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	GratuityReport gratReport ;
	@Override
	public void prepare_local() throws Exception {
		gratReport = new GratuityReport();
		gratReport.setMenuCode(1035);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return gratReport;
	}
	
	public String f9divaction() throws Exception {
		String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
		 
		if(gratReport.getUserProfileDivision() !=null && gratReport.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+gratReport.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		String[] headers = {getMessage("division") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "divName","divCode"};

		int[] columnIndex = { 0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String getGratReport() {
		try {
			GratuityReportModel model = new GratuityReportModel();
			model.initiate(context, session);
			model.getGratReport(gratReport, response);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in report------------------" + e);
		}
		return null;
	}

}
