/**
 * 
 */
package org.struts.action.attendance;

import org.paradyne.bean.attendance.DailyMusterReport;
import org.paradyne.model.attendance.DailyMusterReportModel;
import org.struts.lib.ParaActionSupport;
/**
 * @author Reeba_Joseph
 * @modified By AA1711 : To Generate Attendance Report For Selected Month
 * 
 */
public class DailyMusterReportAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DailyMusterReportAction.class);
	DailyMusterReport musterReport = null;

	/**
	 * @return the musterReport
	 */
	public DailyMusterReport getMusterReport() {
		return musterReport;
	}
	/**
	 * @param musterReport
	 *            the musterReport to set
	 */
	public void setMusterReport(DailyMusterReport musterReport) {
		this.musterReport = musterReport;
	}
	/*
	 * (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		musterReport = new DailyMusterReport();
		musterReport.setMenuCode(1055);

	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return musterReport;
	}

	/**Method Name: f9Branch()
	 * @purpose : Popup window contains list of all branches 
	 * @return String f9page
	 */
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER "
					+ " WHERE IS_ACTIVE='Y'" + " ORDER BY UPPER(CENTER_NAME) ";
			String[] headers = { getMessage("branch") };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "brnName", "brnCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Branch in action: " + e);
			return "";
		}
	}

	/**Method Name: f9Div()
	 * @purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String f9Div() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION  ";

			if (musterReport.getUserProfileDivision() != null
					&& musterReport.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN ("
						+ musterReport.getUserProfileDivision() + ")";
			else {
				query += "WHERE 1=1";
			}
			query += " AND IS_ACTIVE='Y' ORDER BY UPPER(DIV_NAME) ";
			String[] headers = { getMessage("division") };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "divName", "divCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error("Exception in f9Div in action: " + e);
			return "";
		}
	}

	/**Method Name: getReport()
	 * @purpose: To Show Report
	 * @return String
	 */
	public String getReport() {
		final DailyMusterReportModel model = new DailyMusterReportModel();
		model.initiate(context, session);
		String reportPath = "";
		model.getReport(musterReport, response, request, reportPath);
		model.terminate();
		return null;
	}

	/**Method Name: mailReport()
	 * @purpose: To send Mail Report
	 * @return String
	 */
	public final String mailReport() {
		try {
			final DailyMusterReportModel model = new DailyMusterReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath = getText("data_path") + "/Report/Payroll"
					+ poolName + "/";			
			model.getReport(musterReport, response, request, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	public String input() {
		// deleteFile();
		return INPUT;
	}

	/**Method Name: reset()
	 * @purpose : Resets the fields
	 * @return String SUCCESS
	 */
	public String reset() {
		try {
			musterReport.setMonth("");
			musterReport.setYear("");
			musterReport.setBrnCode("");
			musterReport.setBrnName("");
			musterReport.setDivCode("");
			musterReport.setDivName("");
			musterReport.setReportType("");
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in reset in action: " + e);
			return "";
		}
	}

}
