package org.struts.action.D1.reports;

import org.paradyne.bean.D1.reports.InformationSystemChangeMisReport;
import org.paradyne.model.D1.reports.InformationSystemChangeMisReportModel;
import org.struts.lib.ParaActionSupport;
/**
 * @author AA1380
 * Created on : 16th April 2012
 */
public class InformationSystemChangeMisReportAction extends ParaActionSupport {
	/** logger.	 *	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InformationSystemChangeMisReportAction.class);
	/** infoChngReportBean. */
	private InformationSystemChangeMisReport infoChngReportBean;
	/** RETURN_INPUT . * */
	private String RETURN_INPUT = "input";
	
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
		this.infoChngReportBean = new InformationSystemChangeMisReport();
		this.infoChngReportBean.setMenuCode(5015);
	}

	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean 
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.infoChngReportBean;
	}
	
	/** getInfoChngReportBean. 
	 * @return InformationSystemChangeMisReport
	 * * */
	public InformationSystemChangeMisReport getInfoChngReportBean() {
		return this.infoChngReportBean;
	}
	/** setInfoChngReportBean. 
	 * @param infoChngReportBean :InformationSystemChangeMisReport
	 * * */
	public void setInfoChngReportBean(final InformationSystemChangeMisReport infoChngReportBean) {
		this.infoChngReportBean = infoChngReportBean;
	}
	
	/**Method : input.
	 * Purpose : This method is used to display report page
	 * @return String
	 */
	public String input() {
		return this.RETURN_INPUT;
	}
	
	
	/**
	 * Method : getInfoChangeReport. 
	 * Purpose : This method is used to generate report
	 * @return String
	 */
	public String getInfoChangeReport() {
		try {
			final InformationSystemChangeMisReportModel model = new InformationSystemChangeMisReportModel();
			model.initiate(context, session);
			final String reportPath = "";
			model.getReport(this.infoChngReportBean, response,  request, reportPath, "MIS");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method : getInfoChangeActivityLogsReport. 
	 * Purpose : This method is used to generate activity logs report
	 * @return String
	 */
	public String getInfoChangeActivityLogsReport() {
		try {
			final InformationSystemChangeMisReportModel model = new InformationSystemChangeMisReportModel();
			model.initiate(context, session);
			final String reportPath = "";
			model.getReport(this.infoChngReportBean, response,  request, reportPath, "Acivity");
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/** Method : f9TicketNumber.
	 * Purpose : Used to set select ticket numbers.
	 * return String
	 */
	public String f9TicketNumber() {
		String query = "SELECT HRMS_D1_INF_CNG_REQ.TRACKING_NUMBER FROM HRMS_D1_INF_CNG_REQ ";
		if(!(infoChngReportBean.getFromDate().trim().equals("")) && !(infoChngReportBean.getToDate().trim().equals(""))) {
			query += " WHERE TO_CHAR(HRMS_D1_INF_CNG_REQ.CREATED_ON,'DD-MM-YYYY') BETWEEN '" + infoChngReportBean.getFromDate() + "' AND '" + infoChngReportBean.getToDate() + "' ";
		}
		String[] headers = {"Ticket Number"};
		String[] headerWidth = {"100"};
		String[] fieldNames = {"ticketNumber"};
		int[] columnIndex = {0};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	/** Method : reset.
	 * Purpose : Used to re-set form fields.
	 * return String
	 */
	public String reset() {
		infoChngReportBean.setFromDate("");
		infoChngReportBean.setToDate("");
		infoChngReportBean.setTicketNumber("");
		infoChngReportBean.setApplicationStatus("-1");
		return this.RETURN_INPUT;
	}
	 
}
