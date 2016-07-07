/**
 * 
 */
package org.paradyne.model.payroll;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.BonusAcqReport;
import org.paradyne.bean.payroll.EmpBonus;
 import org.paradyne.lib.ModelBase;

/**
 * @author IBRAHIM
 *
 */
public class BonusAcqReportModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	/**
	 * 
	 */
	public BonusAcqReportModel() {
		// TODO Auto-generated constructor stub
	}
	
	public String generateReport(BonusAcqReport bonusAcq, HttpServletResponse response) {
		
		String reportName = " Bonus Acquaintance Roll ";
		String comnname = "Navy";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				"Xls", reportName);

		String quer = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ')   ,"
				+ " HRMS_EMP_BONUS.BONUS_DAYS_ELIGIBLE,HRMS_EMP_BONUS.BONUS_AMOUNT, HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_BONUS.BONUS_CODE,HRMS_EMP_BONUS.EMP_PAYBILL "
				+ " FROM HRMS_EMP_BONUS "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_BONUS.EMP_ID)"
				+"  LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " WHERE HRMS_EMP_BONUS.BONUS_CODE = "
				+ bonusAcq.getBonusCode()
				+ " AND HRMS_EMP_BONUS.EMP_PAYBILL = "
				+ bonusAcq.getPaybillId();
	
		Object[][] result = getSqlModel().getSingleResult(quer);
	

		

			String[] colNames = { "Employee Token", "Employee Name",
					"Bonus Days", "Bonus Amount" };

			int[] cellWidth = { 20, 30, 25, 25 };

			if (null != result && result.length != 0) {
				try {

					/*String text[] = { "Bonus Report" };
					int style[] = {7 };
					//rg.addFormatedText(text, style, 0, 1, 40);
					int fontSty[]={12};
					rg.addText("      									 Bonus Acquaintance Roll  ", 100, 100, 50);
					rg.addFormatedText("bonusssssssssssss acq", 10, 11, 5, 5, 12);*/
					rg.addText("                                              BONUS ACQUAINTANCE ROLL ", 0, 1, 2);
					String header = " Bonus Acquaintance Roll--------------- ";
					rg.addFormatedText(header, 2, 0, 1, 0);
				//	rg.addFormatedText(text, style, 20, 11, 5, fontSty);
					rg.tableBody(colNames, result, cellWidth);

					// rg.genHeader(111);
					rg.genHeader(comnname);
					logger.info("Inside-->generateReport2");
					rg.createReport(response);

				} catch (Exception e) {

					logger.info("Exception in Report" + e);
				}

			}
		return null;
	}

}
