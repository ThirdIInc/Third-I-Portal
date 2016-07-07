package org.paradyne.model.payroll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.paradyne.bean.admin.master.QualificationMaster;
import org.paradyne.bean.admin.master.TitleMaster;
import org.paradyne.bean.payroll.MealVoucherConfiguration;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MealVoucherConfigurationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MealVoucherModel.class);

	public void setEmployeeDetails(MealVoucherConfiguration mealVoucher) {
		// TODO Auto-generated method stub
	}

	public boolean save(MealVoucherConfiguration mealVoucher) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			String deleteQuery = " DELETE FROM HRMS_MEAL_VOUCHER_CONFIG  ";
			boolean flag = getSqlModel().singleExecute(deleteQuery);

			if (flag) {
				Object[][] add = new Object[1][10];
				add[0][0] = mealVoucher.getCreditCode();
				add[0][1] = mealVoucher.getFreqencyOfChange();
				add[0][2] = mealVoucher.getCoupenCode();
				add[0][3] = mealVoucher.getDebitCode();
				
				add[0][4] = mealVoucher.getFromYear();
				add[0][5] = mealVoucher.getToYear();
				add[0][6] = mealVoucher.getMealVoucherAmtLimit();
				add[0][7] = mealVoucher.getMealVoucherAmtDeclare();
				add[0][8] = mealVoucher.getFromPeriod();
				add[0][9] = mealVoucher.getToPeriod();
				
				String insertQuery = " INSERT INTO HRMS_MEAL_VOUCHER_CONFIG(MEAL_CONFIG_CODE, MEAL_CONFIG_CASH, MEAL_CONFIG_FREQUENCY,MEAL_CONFIG_COUPEN,MEAL_CONFIG_DEBIT,MEAL_CONFIG_FROM, MEAL_CONFIG_TO, MEAL_CONFIG_AMT_LIMIT, MEAL_CONFIG_AMT_DECLARE, MEAL_FROM_PERIOD, MEAL_TO_PERIOD) "
						+ "VALUES((SELECT NVL(MAX(MEAL_CONFIG_CODE),0)+1 FROM HRMS_MEAL_VOUCHER_CONFIG ),? ,?,?,?,? ,?,?,?,?,?)";

				result = getSqlModel().singleExecute(insertQuery, add);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * for deleting the record after selecting
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean deleteMealvoucherConf(MealVoucherConfiguration bean) {
		boolean result = false;
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getMealvoucherconfID();

		String query = "DELETE FROM HRMS_MEAL_VOUCHER_CONFIG WHERE MEAL_CONFIG_CODE=?";

		result = getSqlModel().singleExecute(query, addObj);
		return result;
	}

	/**
	 * for modifying the data
	 * 
	 * @param bean
	 * @return
	 */
	public boolean update(MealVoucherConfiguration bean) {
		boolean result = false;
		Object Obj[][] = new Object[1][3];
		Obj[0][0] = bean.getCreditCode();
		Obj[0][1] = bean.getFreqencyOfChange();
		Obj[0][2] = bean.getMealvoucherconfID();
		String updateQery = "UPDATE HRMS_MEAL_VOUCHER_CONFIG SET MEAL_CONFIG_CASH = ? ,MEAL_CONFIG_FREQUENCY = ? WHERE MEAL_CONFIG_CODE = ?";
		result = getSqlModel().singleExecute(updateQery, Obj);
		return result;
	}

	/**
	 * to show list of records belonging to meal voucher configuration
	 * 
	 * @param bean
	 * @param request
	 */

	public void getMealvoucherConfDoubleclick(MealVoucherConfiguration bean) {
		try {
			String query = "SELECT A.MEAL_CONFIG_CODE,A.MEAL_CONFIG_CASH,B.CREDIT_NAME,A.MEAL_CONFIG_FREQUENCY FROM HRMS_MEAL_VOUCHER_CONFIG A,HRMS_CREDIT_HEAD B WHERE A.MEAL_CONFIG_CASH=B.CREDIT_CODE AND A.MEAL_CONFIG_CODE = "
					+ bean.getMealvoucherconfID();
			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setMealvoucherconfID(String.valueOf(data[0][0]).trim());
			bean.setCreditCode(String.valueOf(data[0][1]).trim());
			bean.setCreditHead(String.valueOf(data[0][2]).trim());
			bean.setFreqencyOfChange(String.valueOf(data[0][3]));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void generateReport(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			String reportName = "\n\nMeal Voucher Configuration Report\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", reportName);
			rg.setFName("Voucher Configuration Report.Pdf");

			String query = " SELECT B.CREDIT_NAME,DECODE(A.MEAL_CONFIG_FREQUENCY,'M','Monthly','Q','Quartrly','H','HalfYearly','A','Annaully') "
					+ " FROM HRMS_MEAL_VOUCHER_CONFIG A ,HRMS_CREDIT_HEAD B "
					+ " WHERE A.MEAL_CONFIG_CASH=B.CREDIT_CODE ORDER BY A.MEAL_CONFIG_CODE ";

			Object dataobj[][] = getSqlModel().getSingleResult(query);

			String headerNames[] = { "Sr.No.", "Credit Head", "Frequency" };

			if (dataobj != null && dataobj.length > 0) {

				Object[][] Data = new Object[dataobj.length][3];

				int j = 1;
				for (int i = 0; i < dataobj.length; i++) {
					Data[i][0] = j;
					Data[i][1] = dataobj[i][0];
					Data[i][2] = dataobj[i][1];

					j++;
				}
				int cellwidth[] = { 10, 45, 45 };
				int alignment[] = { 1, 0, 0 };
				rg.addFormatedText(reportName, 6, 0, 1, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addTextBold("Date :" + toDay, 0, 2, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(headerNames, Data, cellwidth, alignment);
			} else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}
			rg.createReport(response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/* for deleting the multiple record from list by double clicking */
	public boolean deleteMealvochConf(MealVoucherConfiguration tm, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		String Query = "DELETE FROM HRMS_MEAL_VOUCHER_CONFIG WHERE MEAL_CONFIG_CODE = ?";
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					// logger.info(" into
					// delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"
					// + code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(Query, delete);
					if (!flag) {
						cnt++;
					}// end of if
					// result=true;
				}// end of if
			}// end of loop
		}// end of nested if
		if (cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}

	public void getSavedRecord(MealVoucherConfiguration mealVoucher) {
		// TODO Auto-generated method stub

		String query = " SELECT MEAL_CONFIG_CASH, MEAL_CONFIG_FREQUENCY, MEAL_CONFIG_COUPEN, MEAL_CONFIG_DEBIT  "
				+ " ,CASH.CREDIT_NAME,COUPEN.CREDIT_NAME,DEBIT_NAME , " +
				" MEAL_CONFIG_FROM, MEAL_CONFIG_TO, MEAL_CONFIG_AMT_LIMIT, MEAL_CONFIG_AMT_DECLARE, MEAL_FROM_PERIOD, MEAL_TO_PERIOD "
				+ " FROM HRMS_MEAL_VOUCHER_CONFIG "
				+ " INNER JOIN HRMS_CREDIT_HEAD CASH ON(CASH.CREDIT_CODE=HRMS_MEAL_VOUCHER_CONFIG.MEAL_CONFIG_CASH) "
				+ " INNER JOIN HRMS_CREDIT_HEAD COUPEN ON(COUPEN.CREDIT_CODE=HRMS_MEAL_VOUCHER_CONFIG.MEAL_CONFIG_COUPEN) "
				+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_MEAL_VOUCHER_CONFIG.MEAL_CONFIG_DEBIT) ";

		Object dataObj[][] = getSqlModel().getSingleResult(query);

		if (dataObj != null && dataObj.length > 0) {
			
			mealVoucher.setCreditCode(String.valueOf(dataObj[0][0]));
			mealVoucher.setFreqencyOfChange(String.valueOf(dataObj[0][1]));
			mealVoucher.setCoupenCode(String.valueOf(dataObj[0][2]));
			mealVoucher.setDebitCode(String.valueOf(dataObj[0][3]));
			mealVoucher.setCreditHead(String.valueOf(dataObj[0][4]));
			mealVoucher.setCoupenHead(String.valueOf(dataObj[0][5]));
			mealVoucher.setDebitHead(String.valueOf(dataObj[0][6]));
			
			mealVoucher.setFromYear(String.valueOf(dataObj[0][7]));
			mealVoucher.setToYear(String.valueOf(dataObj[0][8]));
			mealVoucher.setMealVoucherAmtLimit(String.valueOf(dataObj[0][9]));
			mealVoucher.setMealVoucherAmtDeclare(String.valueOf(dataObj[0][10]));
			mealVoucher.setFromPeriod(checkNull(String.valueOf(dataObj[0][11])));
			mealVoucher.setToPeriod(checkNull(String.valueOf(dataObj[0][12])));
			
			
			
		}

	}
	
	/**
	 * to check null value
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}


}
