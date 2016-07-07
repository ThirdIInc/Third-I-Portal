package org.struts.action.D1.reports;

import org.paradyne.bean.D1.reports.BRDMisReport;
import org.paradyne.model.D1.reports.BRDMisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * Created by Manish Sakpal.
 * 
 * */
public class BRDMisReportAction extends ParaActionSupport {
	/** logger.	 *	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BRDMisReportAction.class);
	/** brdMisBean. */
	private BRDMisReport brdMisBean; 
	/**  returnINPUT . * */
	private final String returnInput = "input";
	
	/** f9PageConst. * */
	private final String f9PageConst = "f9page";
	
	/** f9MultiSelectConst. * */
	private final String f9MultiSelectConst = "f9multiSelect";
	
	/** divisionConst. * */
	private final String divisionConst = "division";
	
	/** falseValueConst. * */
	private final String falseValueConst = "false";
	
	/** Method : prepare_local.
	 * Purpose : This method is used to set menuCode 
	 * @throws Exception : Exception
	 * */
	public void prepare_local() throws Exception {
		this.brdMisBean = new BRDMisReport();
		this.brdMisBean.setMenuCode(5016);
	}

	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean 
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.brdMisBean;
	}
	
	 
	/**Method : input.
	 * Purpose : This method is used to display report page
	 * @return String
	 */
	public String input() {
		return this.returnInput;
	}
	
	
	/**
	 * Method : getBRDReport. 
	 * Purpose : This method is used to generate report
	 * @return String
	 */
	public String getBRDReport() {
		try {
			final BRDMisReportModel model = new BRDMisReportModel();
			model.initiate(context, session);
			final String reportPath = "";
			model.getReport(this.brdMisBean, response,  request, reportPath, "MIS");
			model.terminate();
		} catch (final Exception e) {
			BRDMisReportAction.logger.error("Exception occured in getBRDReport method >>" + e);
		}
		return null;
	}
	
	/**
	 * Method : getBRDActivityLogsReport. 
	 * Purpose : This method is used to generate activity logs report
	 * @return String
	 */
	public String getBRDActivityLogsReport() {
		try {
			final BRDMisReportModel model = new BRDMisReportModel();
			model.initiate(context, session);
			final String reportPath = "";
			model.getReport(this.brdMisBean, response,  request, reportPath, "Acivity");
			model.terminate();
		} catch (final Exception e) {
			BRDMisReportAction.logger.error("Exception occured in getBRDActivityLogsReport method >>" + e);
		}
		return null;
	}
	
	/**
	 * Method : getBRDSnapshotReport. 
	 * Purpose : This method is used to generate snapshot report
	 * @return String
	 */
	public String getBRDSnapshotReport() {
		try {
			final BRDMisReportModel model = new BRDMisReportModel();
			model.initiate(context, session);
			final String reportPath = "";
			model.getReport(this.brdMisBean, response,  request, reportPath, "SnapShot");
			model.terminate();
		} catch (final Exception e) {
			BRDMisReportAction.logger.error("Exception occured in getBRDSnapshotReport method >>" + e);
		}
		return null;
	}
	
	
	/** Method : f9TicketNumber.
	 * Purpose : Used to set select ticket numbers.
	 * @return String
	 */
	public String f9TicketNumber() {
		String query = "SELECT HRMS_D1_BUSINESS_REQUIREMENT.BRD_TICKET_NO FROM HRMS_D1_BUSINESS_REQUIREMENT ";
		if (!("".equals(this.brdMisBean.getFromDate().trim())) && !("".equals(this.brdMisBean.getToDate().trim()))) {
			query += " WHERE HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_DATE"
						+ " BETWEEN TO_DATE('" + this.brdMisBean.getFromDate() + "','DD-MM-YYYY') "
						+ " AND TO_DATE('" + this.brdMisBean.getToDate() + "','DD-MM-YYYY') ";
		}
		final String[] headers = {"Ticket Number"};
		final String[] headerWidth = {"100"};
		final String[] fieldNames = {"ticketNumber"};
		final int[] columnIndex = {0};
		final String submitFlag = this.falseValueConst;
		final String submitToMethod = "";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return this.f9PageConst;
	}
	
	/** Method : reset.
	 * Purpose : Used to re-set form fields.
	 * @return String
	 */
	public String reset() {
		this.brdMisBean.setFromDate("");
		this.brdMisBean.setToDate("");
		this.brdMisBean.setTicketNumber("");
		this.brdMisBean.setApplicationStatus("-1");
		return this.returnInput;
	}
	 

}
