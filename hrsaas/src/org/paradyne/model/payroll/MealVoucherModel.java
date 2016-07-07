package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

import org.paradyne.bean.D1.BusinessRequirementDocument;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.bean.payroll.MealVoucherBean;
import org.paradyne.bean.payroll.incometax.EmpInvestmentMaster;
import org.paradyne.lib.ModelBase;

public class MealVoucherModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MealVoucherModel.class);

	public void setEmployeeDetails(MealVoucherBean mealVoucher) {
		// TODO Auto-generated method stub
		try {
			String employeeDtlQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID "
					+ "	FROM HRMS_EMP_OFFC "
					+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID="
					+ mealVoucher.getUserEmpId();

			Object dataObj[][] = getSqlModel()
					.getSingleResult(employeeDtlQuery);

			if (dataObj != null && dataObj.length > 0) {
				mealVoucher
						.setEmpToken(checkNull(String.valueOf(dataObj[0][0])));
				mealVoucher
						.setEmpName(checkNull(String.valueOf(dataObj[0][1])));
				mealVoucher.setBranchName(checkNull(String
						.valueOf(dataObj[0][2])));
				mealVoucher.setDesignationName(checkNull(String
						.valueOf(dataObj[0][3])));
				mealVoucher
						.setEmpCode(checkNull(String.valueOf(dataObj[0][4])));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public boolean save(MealVoucherBean mealVoucher) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			String deleteQuery = " DELETE FROM  HRMS_MEAL_VOUCHER WHERE MEAL_VOUCHER_EMPID="
					+ mealVoucher.getEmpCode().trim();

			result = getSqlModel().singleExecute(deleteQuery);

			if (result) {
				Object insertObj[][] = new Object[1][6];

				insertObj[0][0] = mealVoucher.getEmpCode().trim();
				insertObj[0][1] = mealVoucher.getApplicationDate().trim();
				insertObj[0][2] = mealVoucher.getMealVoucherAmount().trim();
				insertObj[0][3] = mealVoucher.getMealVoucherCoupenAmt().trim();
				insertObj[0][4] = mealVoucher
						.getMealVoucherSplAllowanceAmount().trim();
				
				insertObj[0][5] = mealVoucher.getFromYear().trim()+ "-" + mealVoucher.getToYear().trim();
				
System.out.println("insertObj[0][5]==="+insertObj[0][5]);
				String insertQuery = " INSERT INTO HRMS_MEAL_VOUCHER(MEAL_VOUCHER_EMPID, MEAL_VOUCHER_APPDATE, MEAL_VOUCHER_AMOUNT, MEAL_VOUCHER_COUPEN, MEAL_VOUCHER_SPLALLOWANCE,MEAL_VOUCHER_FINC_YEAR) "
						+ " VALUES(?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?) ";

				result = getSqlModel().singleExecute(insertQuery, insertObj);

				if (result) {
					mealVoucher.setCheckValidation("false");
					mealVoucher.setSaveFlag("true");
					result = updateEmployeeCredit(mealVoucher);
					if (result) {
						result = updateEmployeeDebit(mealVoucher);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Object[][] getMealVoucherConfiguration() {
		Object data[][] = null;
		try {
			String query = " select MEAL_CONFIG_CASH,MEAL_CONFIG_FREQUENCY,MEAL_CONFIG_COUPEN, MEAL_CONFIG_DEBIT "
					+ " from HRMS_MEAL_VOUCHER_CONFIG ";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}

	public boolean updateEmployeeCredit(MealVoucherBean mealVoucher) {
		boolean result = false;

		try {

			Object creditObj[][] = getMealVoucherConfiguration();
			Object data[][] = null;
			if (creditObj != null && creditObj.length > 0) {
				String updateMealAllowanceAmtQuery = " UPDATE HRMS_EMP_CREDIT SET   CREDIT_AMT="
						+ mealVoucher.getMealVoucherSplAllowanceAmount().trim()
						+ " WHERE CREDIT_CODE="
						+ String.valueOf(creditObj[0][0])
						+ "   AND EMP_ID="
						+ mealVoucher.getEmpCode().trim();
				result = getSqlModel().singleExecute(
						updateMealAllowanceAmtQuery);

				String selectQuery = " SELECT * FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE="
						+ String.valueOf(creditObj[0][2])
						+ " AND EMP_ID="
						+ mealVoucher.getEmpCode().trim();

				data = getSqlModel().getSingleResult(selectQuery);

			}

			if (data != null && data.length == 0) {
					
				//credit code previous 47 hard coded
				String insertMealAllowanceAmtQuery = " INSERT INTO HRMS_EMP_CREDIT ( EMP_ID, CREDIT_CODE, CREDIT_AMT, CREDIT_APPLICABLE, "
						+ " CREDIT_AMT_PRECOMMISSION ) VALUES ( "
						+ mealVoucher.getEmpCode().trim()
						+ ", "+String.valueOf(creditObj[0][2])+",  "
						+ mealVoucher.getMealVoucherCoupenAmt().trim()
						+ ", 'Y', 0) ";

				result = getSqlModel().singleExecute(
						insertMealAllowanceAmtQuery);

			} else {
				String updateMealVoucherAmtQuery = " UPDATE HRMS_EMP_CREDIT SET   CREDIT_AMT="
						+ mealVoucher.getMealVoucherCoupenAmt().trim()
						+ " WHERE CREDIT_CODE="
						+ String.valueOf(creditObj[0][2])
						+ "   AND EMP_ID="
						+ mealVoucher.getEmpCode().trim();
				result = getSqlModel().singleExecute(updateMealVoucherAmtQuery);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updateEmployeeDebit(MealVoucherBean mealVoucher) {
		boolean result = false;

		try {
			Object debitObj[][] = getMealVoucherConfiguration();
			Object data[][] = null;

			if (debitObj != null && debitObj.length > 0) {
				String selectQuery = " SELECT * FROM HRMS_EMP_DEBIT WHERE DEBIT_CODE="
						+ String.valueOf(debitObj[0][3])
						+ " AND EMP_ID="
						+ mealVoucher.getEmpCode().trim();

				data = getSqlModel().getSingleResult(selectQuery);

			}

			if (data != null && data.length == 0) {

				String insertMealAllowanceAmtQuery = " INSERT INTO HRMS_EMP_DEBIT( EMP_ID,DEBIT_CODE,DEBIT_AMT,DEBIT_APPLICABLE ) VALUES ( "
						+ mealVoucher.getEmpCode().trim()
						+ ", "
						+ String.valueOf(debitObj[0][3])
						+ ",  "
						+ mealVoucher.getMealVoucherCoupenAmt().trim()
						+ ", 'Y') ";

				result = getSqlModel().singleExecute(
						insertMealAllowanceAmtQuery);

			} else {
				String updateMealVoucherAmtQuery = " UPDATE HRMS_EMP_DEBIT SET   DEBIT_AMT="
						+ mealVoucher.getMealVoucherCoupenAmt().trim()
						+ " WHERE DEBIT_CODE="
						+ String.valueOf(debitObj[0][3])
						+ "   AND EMP_ID=" + mealVoucher.getEmpCode().trim();
				result = getSqlModel().singleExecute(updateMealVoucherAmtQuery);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean setMealVoucherDtl(MealVoucherBean mealVoucher) {
		// TODO Auto-generated method stub

		boolean result = false;
		try {
			String selectQuery = " SELECT TO_CHAR(MEAL_VOUCHER_APPDATE,'DD-MM-YYYY'), MEAL_VOUCHER_AMOUNT, MEAL_VOUCHER_COUPEN, MEAL_VOUCHER_SPLALLOWANCE FROM HRMS_MEAL_VOUCHER WHERE MEAL_VOUCHER_EMPID ="
					+ mealVoucher.getUserEmpId().trim();

			Object data[][] = getSqlModel().getSingleResult(selectQuery);

			if (data != null && data.length > 0) {
				mealVoucher.setPreviousDate(checkNull(String
						.valueOf(data[0][0])));
				mealVoucher.setMealVoucherAmount(checkNull(String
						.valueOf(data[0][1])));
				mealVoucher.setMealVoucherCoupenAmt(checkNull(String
						.valueOf(data[0][2])));
				mealVoucher.setMealVoucherSplAllowanceAmount(checkNull(String
						.valueOf(data[0][3])));
				mealVoucher.setSaveFlag("true");

				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void setMealVoucherDeclaredAmount(MealVoucherBean mealVoucher) {
		// TODO Auto-generated method stub
		try {

			// 32 meal vouchers
			// 47 meal allowance
			Object creditObj[][] = getMealVoucherConfiguration();
			Object data[][] = null;
			if (creditObj != null && creditObj.length > 0) {
				String query = "  SELECT NVL(SUM(HRMS_EMP_CREDIT.CREDIT_AMT),0) FROM  HRMS_EMP_CREDIT "
						+ " WHERE CREDIT_CODE IN("
						+ String.valueOf(creditObj[0][0])
						+ ","
						+ String.valueOf(creditObj[0][2])
						+ ") "
						+ " AND EMP_ID=" + mealVoucher.getUserEmpId().trim();

				data = getSqlModel().getSingleResult(query);
			}

			if (data != null && data.length > 0) {
				mealVoucher.setMealVoucherDeclaredAmt(String
						.valueOf(data[0][0]));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Method : setDeclareAmt().
	 * Pupose : Used to fetch all data from HRMS_MEAL_VOUCHER_CONFIG table to display in dropdown list.
	 * @param bean :Used to set map for Declaration Amount.
	 */
	public void setDeclareAmt(MealVoucherBean mealVoucher) {
		ArrayList amtList = new ArrayList();
		
		final String amtQuery = "SELECT HRMS_MEAL_VOUCHER_CONFIG.MEAL_CONFIG_AMT_DECLARE , " +
				"HRMS_MEAL_VOUCHER_CONFIG.MEAL_FROM_PERIOD, " +
				"HRMS_MEAL_VOUCHER_CONFIG.MEAL_TO_PERIOD ," +
				"HRMS_MEAL_VOUCHER_CONFIG.MEAL_CONFIG_AMT_LIMIT " +
				"FROM  HRMS_MEAL_VOUCHER_CONFIG ";
		final Object[][] amttypeObj = this.getSqlModel().getSingleResult(amtQuery);

		mealVoucher.setAmountValue(String.valueOf(amttypeObj[0][0]));
		mealVoucher.setFromPeriod(String.valueOf(amttypeObj[0][1]));
		mealVoucher.setToPeriod(String.valueOf(amttypeObj[0][2]));
		mealVoucher.setMealVoucherDeclaredAmt(String.valueOf(amttypeObj[0][3]));
		String[] amt = String.valueOf(amttypeObj[0][0]).split(",");
		
		LinkedList  list= new LinkedList ();
		for (int j = 0; j < amt.length; j++) {
		//	 logger.info("in for loopo ----------" + amt[j]);
			//MealVoucherBean bean = new MealVoucherBean();
			list.add(amt[j]);
		//bean.setAmountValue(amt[j]);
			//list.add(bean);
		}
		mealVoucher.setAmtValue(list);

		
	}
	

}
