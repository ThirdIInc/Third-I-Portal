package org.struts.action.payroll.bonus;

import org.paradyne.bean.payroll.bonus.BonusAllowanceReport;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.bonus.BonusAllowanceReportModel;
import org.struts.lib.ParaActionSupport;

/**Created on 30th Jan. 2012
 * @author AA1380
 *
 */
public class BonusAllowanceReportAction extends ParaActionSupport {
	/** logger.	 *	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BonusAllowanceReportAction.class);
	/** bonusReportBean. */
	private BonusAllowanceReport bonusReportBean;
	/** final . * */
	private String returnInput = "input";
	
	/** Method : prepare_local.
	 * Purpose : This method is used to set menuCode 
	 * @throws Exception : Exception
	 * */
	public void prepare_local() throws Exception {
		this.bonusReportBean = new BonusAllowanceReport();
		this.bonusReportBean.setMenuCode(2283);
	}

	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean 
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.bonusReportBean;
	}
	
	/** Method : getBonusReportBean.
	 * Purpose : This method is used to get bonusReportBean 
	 * @return BonusAllowanceReport
	 * */
	public BonusAllowanceReport getBonusReportBean() {
		return this.bonusReportBean;
	}

	/** Method : setBonusReportBean.
	 * Purpose : This method is used to set bonusReportBean 
	 * @param bonusReportBean : bonusReportBean
	 * */
	public void setBonusReportBean(final BonusAllowanceReport bonusReportBean) {
		this.bonusReportBean = bonusReportBean;
	}
	
	/**Method : input.
	 * Purpose : This method is used to display report page
	 * @return String
	 */
	public String input() {
		return this.returnInput;
	}
	
	/**Method : reset.
	 * Purpose : This method is used to reset all the fields
	 * @return String
	 */
	public String reset() {
		try {
			this.bonusReportBean.setBonusAllowancePeriod("");
			this.bonusReportBean.setBonusAllowanceID("");
			this.bonusReportBean.setFromMonth("");
			this.bonusReportBean.setFromYear("");
			this.bonusReportBean.setToMonth("");
			this.bonusReportBean.setToYear("");
			this.bonusReportBean.setDivisionReportCheckBox("");
			this.bonusReportBean.setBranchReportCheckBox("");
			this.bonusReportBean.setDepartmentReportCheckBox("");
			this.bonusReportBean.setApplicableCreditReportCheckBox("");
			this.bonusReportBean.setGradeReportCheckBox("");
			this.bonusReportBean.setAccountNumberReportCheckBox("");
			this.bonusReportBean.setBankReportCheckBox("");
			this.bonusReportBean.setIndividualRatingReportCheckBox("");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnInput;
	}
	
	
	/**
	 * Method : getBonusReport. 
	 * Purpose : This method is used to generate report
	 * @return String
	 */
	public String getBonusReport() {
		try {
			final BonusAllowanceReportModel model = new BonusAllowanceReportModel();
			model.initiate(context, session);
			final String reportPath = "";
			model.getReport(this.bonusReportBean, response,  request, reportPath);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**Method : mailReport.
	 * Purpose : This method is used to send mail.
	 * @return String
	 */
	public final String mailReport() {
		try {
			final BonusAllowanceReportModel model = new BonusAllowanceReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!("".equals(poolName) || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String reportPath =  this.getText("data_path") + "/Report/Payroll" + poolName + "/";
			model.getReport(this.bonusReportBean, response,  request, reportPath);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	
	/**Method : searchBonusRecords.
	 *Purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String searchBonusRecords() {
		try {
			final String query = "SELECT DECODE(HRMS_BONUS_HDR.BONUS_FROM_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December')||''||HRMS_BONUS_HDR.BONUS_FROM_YEAR||'-'|| DECODE(HRMS_BONUS_HDR.BONUS_TO_MONTH,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','October','11','November','12','December')||''||HRMS_BONUS_HDR.BONUS_TO_YEAR, " +
								 " HRMS_BONUS_HDR.BONUS_CODE, HRMS_BONUS_HDR.BONUS_FROM_MONTH, HRMS_BONUS_HDR.BONUS_FROM_YEAR, HRMS_BONUS_HDR.BONUS_TO_MONTH, HRMS_BONUS_HDR.BONUS_TO_YEAR" + 
								 " FROM HRMS_BONUS_HDR ";

			final String[] headers = {"Bonus Period"};
			final String[] headerWidth = {"100"};
			final String[] fieldNames = {"bonusAllowancePeriod", "bonusAllowanceID", "fromMonth", "fromYear", "toMonth", "toYear"};
			final int[] columnIndex = {0, 1, 2, 3, 4, 5};
			final String submitFlag = "false";
			final String submitToMethod = "";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	/**Method  : fromBonusAllowance.
	 * Purpose : This method is called when we clicked on "Report" button from Bonus Allowance form
	 * @return String
	 */
	public String fromBonusAllowance() {
		try {
			this.bonusReportBean.setFromBonusAllowanceFlag(true);
			final String fromMonth = request.getParameter("fromMonthBonus");
			final String fromYear = request.getParameter("fromYearBonus");
			final String toMonth = request.getParameter("toMonthBonus");
			final String toYear = request.getParameter("toYearBonus");
			final String bonusAllowanceId = request.getParameter("bonusAllowanceId");
			final String bonusAllowanceStatus = request.getParameter("bonusAllowanceStatus");
			
			final String fromPeriod = Utility.month(Integer.parseInt(fromMonth)) + " " + fromYear;
			final String toPeriod = Utility.month(Integer.parseInt(toMonth)) + " " + toYear;
			final String finalPeriodToDispaly = fromPeriod + " - " + toPeriod;
			this.bonusReportBean.setBonusAllowancePeriod(finalPeriodToDispaly);
			
			this.bonusReportBean.setFromMonth(fromMonth);
			this.bonusReportBean.setFromYear(fromYear);
			this.bonusReportBean.setToMonth(toMonth);
			this.bonusReportBean.setToYear(toYear);
			this.bonusReportBean.setBonusAllowanceID(bonusAllowanceId);
			this.bonusReportBean.setBonusAllowanceStatus(bonusAllowanceStatus);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnInput;
	}
}
