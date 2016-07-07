/**
 * 
 */
package org.struts.action.openCloseOffice;

import org.paradyne.bean.openCloseOffice.OpenCloseOffice;
import org.paradyne.bean.openCloseOffice.OpenCloseReport;
import org.paradyne.model.openCloseOffice.OpenCloseReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 *
 */
public class OpenCloseReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(OpenCloseOfficeAction.class);
	
	OpenCloseReport openCloseReport;
	
	public void prepare_local() throws Exception {
		openCloseReport = new OpenCloseReport();
		openCloseReport.setMenuCode(1058);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return openCloseReport;
	}

	public OpenCloseReport getOpenCloseReport() {
		return openCloseReport;
	}

	public void setOpenCloseReport(OpenCloseReport openCloseReport) {
		this.openCloseReport = openCloseReport;
	}
	
	public String report()throws Exception{
		OpenCloseReportModel model = new OpenCloseReportModel();
		model.initiate(context, session);
		model.report(request, response,openCloseReport);
		model.terminate();
		return null;
	}
	
	public String f9branch() throws Exception {

		/*
		 * String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)" +" FROM
		 * HRMS_EMP_OFFC ORDER BY EMP_ID ";
		 */
		String query = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER "
						+"ORDER BY CENTER_ID ASC ";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "branchId", "branchName"};

		int[] columnIndex = { 0, 1};

		/*
		 * String submitFlag = "true";
		 * 
		 * 
		 * String submitToMethod="AssetEmployee_showDetails.action";
		 */
		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	

}
