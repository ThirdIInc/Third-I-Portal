package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.Effect3D;
import org.paradyne.bean.payroll.PromotionArrears;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import org.paradyne.model.payroll.incometax.EmployeeTaxCalculation;
/**
 * Created on 17th Jan 2012.
 * @author aa0476
 */
public class PromotionArrearsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	NumberFormat formatter = new DecimalFormat("#0.00");


	/**..
	 * Purpose : this method is used to calculate the employee's ESI amount depending on
	 * the location of employee's branch
	 * @param bean
	 * @param typeCode
	 * @param branchCode
	 * @param totalCr
	 * @param empCode
	 * @return double value for calculated ESI amount
	 */
	public double calESIAmount(PromotionArrears bean, String typeCode,
			String branchCode, double totalCr, String empCode) {
		double esiAmount = 0.0, esiResult = 0.0;
		try {
			// BRANCH WISE CHECK FOR ESI
			if (checkTypeBranch(bean, typeCode, branchCode, 1)) 
			{
				Object[][] esiData = bean.getEsiData();
				if (totalCr <= Double
						.parseDouble(String.valueOf(esiData[0][4]))) {
					esiAmount = Math.round((totalCr * Double
							.parseDouble(esiData[0][2].toString())) / 100);
				}
			}
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
		}
		return esiAmount;
	} // end method


	/**
	 * Purpose : this method is used to calculate the employee's PT amount depending on
	 * the location of employee's branch and gross amount
	 * @param bean
	 * @param typeCode
	 * @param branchCode
	 * @param totalCr
	 * @param month
	 * @param locationCode
	 * @return double value for calculated Professional tax
	 */
	public double calPTAmt(PromotionArrears bean, String typeCode,
			String branchCode, double totalCr, String month, String locationCode) {
		double ptAmt = 0.0;
		try {
			//CHECK BRANCH WISE
			if (checkTypeBranch(bean, typeCode, branchCode, 2)) 
			{
				Object[][] ptData = bean.getPtData();
				for (int i = 0; i < ptData.length; i++) {
					//CHECK LOCATION CODE
					if (locationCode.equals(String.valueOf(ptData[i][1]))) {
						if (totalCr >= Double.parseDouble(String
								.valueOf(ptData[i][2]))
								&& totalCr <= Double.parseDouble(String
										.valueOf(ptData[i][3]))) {
							if (month.equals(ptData[i][4])) {
								//GET PT AMOUNT	
								ptAmt = Double.parseDouble(String
										.valueOf(ptData[i][6]));
							} else {
								//GET PT AMOUNT	
								ptAmt = Double.parseDouble(String
										.valueOf(ptData[i][5]));
							}
							break;
						}
					}
				}
			}//END IF
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
		}
		return ptAmt;
	}

	/**
	 * Purpose : this method is used to identify whether the branch is applicable for
	 * debit/deduction from salary
	 * 
	 * @param bean
	 * @param typeCode
	 * @param branchCode
	 * @param index
	 * @return true if branch is eligible for tax else false if exempted
	 */
	public boolean checkTypeBranch(PromotionArrears bean, String typeCode,
			String branchCode, int index) {
		try {
			//SELECT CONFIGURATION FROM HRMS_EMP_TYPE
			String typeSql = " SELECT TYPE_ID, TYPE_ESI, TYPE_PT, TYPE_PF FROM HRMS_EMP_TYPE "
					+ " WHERE TYPE_ID = " + typeCode + " ORDER BY TYPE_ID ";
			Object[][] typeData = getSqlModel().getSingleResult(typeSql);
			//SSELECT CONFIGURATION FROM HRMS_CENTER
			String branchSql = " SELECT CENTER_ID, CENTER_ESI, CENTER_PT, CENTER_PF FROM HRMS_CENTER "
					+ " WHERE CENTER_ID = "
					+ branchCode
					+ " ORDER BY CENTER_ID ";
			Object[][] branchData = getSqlModel().getSingleResult(branchSql);
			//CHECK TYPE IS Y
			if (!typeData[0][index].equals("Y")) {
				return false;
			}
			//CHECK BRANCH Y
			if (!branchData[0][index].equals("Y")) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	} // end of method

	/**
	 * Purpose : this method is used to find the code for debit ESI from sal_debit_head
	 * 
	 * @param bean
	 * @param year
	 * @return String, return the code for ESIC.
	 */
	public String getESICode(PromotionArrears bean, String debitEffectDate) {
		try {
			String esiSql = "SELECT ESI_DEBIT_CODE, ESI_FORMULA, ESI_EMP_PERCENTAGE, ESI_DATE, ESI_GROSS_UPTO "
					+ " FROM HRMS_ESI "
					+ " WHERE ESI_DATE = (SELECT MAX(ESI_DATE) FROM HRMS_ESI WHERE ESI_DATE <= TO_DATE('"
					+ debitEffectDate + "','DD-MM-YYYY')) ";

			Object[][] esiData = getSqlModel().getSingleResult(esiSql);
			bean.setEsiData(esiData);
			return String.valueOf(esiData[0][0]);
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	}

	/**
	 * Purpose : this method is used to find the code for debit PF from sal_debit_head
	 * 
	 * @param bean
	 * @param year
	 * @return boolean, if eligible then returns true if not returns false.
	 */
	public String getPFCode(PromotionArrears bean, String debitEffectDate) {
		try {
			String pfSql = " SELECT PF_DEBIT_CODE, PF_FORMULA, PF_PERCENTAGE "
					+ " FROM HRMS_PF_CONF "
					+ " WHERE PF_DATE = (SELECT MAX(PF_DATE) FROM HRMS_PF_CONF WHERE PF_DATE <= TO_DATE('"
					+ debitEffectDate + "','DD-MM-YYYY')) ";
			Object[][] pfData = getSqlModel().getSingleResult(pfSql);
			bean.setPfData(pfData);
			return String.valueOf(pfData[0][0]);
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	}

	/**
	 * purpose : - this method is used to find the code for debit PT from
	 * sal_debit_head
	 * 
	 * @param bean
	 * @return boolean, if eligible then returns true if not returns false.
	 */
	public String getPTCode(PromotionArrears bean, String debitEffectDate) {
		try {
			String ptSql = " SELECT HRMS_PTAX_HDR.PTAX_CODE, PTAX_LOCATION, PTAX_FROMAMT, PTAX_UPTOAMT, PTAX_VARMTH, "
					+ " PTAX_FIXEDAMT, PTAX_VARAMT, PTAX_DEBIT_CODE FROM HRMS_PTAX_HDR "
					+ " INNER JOIN HRMS_PTAX_DTL ON (HRMS_PTAX_DTL.PTAX_CODE = HRMS_PTAX_HDR.PTAX_CODE) "
					+ " WHERE PTAX_DATE IN (SELECT PTAX_DATE FROM HRMS_PTAX_HDR "
					+ " WHERE PTAX_DATE <= TO_DATE('"
					+ debitEffectDate
					+ "','DD-MM-YYYY')) "
					+ " ORDER BY HRMS_PTAX_HDR.PTAX_DATE DESC";
			Object[][] ptData = getSqlModel().getSingleResult(ptSql);
			bean.setPtData(ptData);
			return String.valueOf(ptData[0][7]);
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	}

	/**
	 * purpose : - this method is used to apply the filters selected from
	 * application
	 * 
	 * @param bean
	 * @param sqlQuery
	 * @return String, returns the query with selected filters
	 */
	public String setFilters(PromotionArrears bean, String sqlQuery) {
		try {
			String typeCode = bean.getTypeCode();
			String payBillNo = bean.getPayBillNo();
			String brnCode = bean.getBrnCode();
			String deptCode = bean.getDeptCode();
			String divCode = bean.getDivCode();
			if (!divCode.equals("")) {
				sqlQuery += " AND EMP_DIV = " + divCode;
				bean.setEmpChkFlag("true");
			}
			if (!typeCode.equals("")) {
				sqlQuery += " AND EMP_TYPE = " + typeCode;
				bean.setEmpChkFlag("true");
			}
			if (!payBillNo.equals("")) {
				sqlQuery += " AND EMP_PAYBILL = " + payBillNo;
				bean.setEmpChkFlag("true");
			}
			if (!brnCode.equals("")) {
				sqlQuery += " AND EMP_CENTER = " + brnCode;
				bean.setEmpChkFlag("true");
			}
			if (!deptCode.equals("")) {
				sqlQuery += " AND EMP_DEPT = " + deptCode;
				bean.setEmpChkFlag("true");
			}

			return sqlQuery;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return "";
		}
	} // end of method

	/**
	 * Purpose : this method is used to set the credits and debits head required to
	 * process the arrears.
	 * 
	 * @param bean
	 */
	public void setSalaryHeads(PromotionArrears bean) {
		try {
			String crHDSql = " SELECT CREDIT_CODE, TRIM(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD "
					+ " WHERE CREDIT_PAYFLAG = 'Y' AND CREDIT_APPLICABLE_ARREARS = 'Y' "
					+ " AND CREDIT_PERIODICITY IN('M','A') ORDER BY CREDIT_CODE ";
			Object[][] crHDObj = getSqlModel().getSingleResult(crHDSql);
			//SET CREDIT HEADE OBJECT
			bean.setCreditCodeObj(crHDObj);
			String drHDSql = " SELECT DEBIT_CODE, TRIM(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD "
					+ " WHERE DEBIT_PAYFLAG = 'Y' AND  DEBIT_APPLICABLE_ARREARS = 'Y' "
					+ " AND DEBIT_PERIODICITY = 'M' ORDER BY DEBIT_CODE ";
			Object[][] dbHDObj = getSqlModel().getSingleResult(drHDSql);
			bean.setDebitCodeObj(dbHDObj);
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
		}
	} // end of method

	/**
	 * Purpose : this method is used to find all the credits that are applicable for
	 * processing the arrears from sal_credit_head
	 * 
	 * @param bean
	 * @return Object[][], containing credit codes and their abbreviation
	 */
	public Object[][] getCreditHeader(PromotionArrears bean) {
		try {
			String crHDSql = " SELECT CREDIT_CODE, TRIM(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD "
					+ " WHERE CREDIT_PAYFLAG = 'Y' AND CREDIT_APPLICABLE_ARREARS = 'Y' "
					+ " AND CREDIT_PERIODICITY IN ('M','A') ORDER BY CREDIT_CODE ";
			Object[][] crHDObj = getSqlModel().getSingleResult(crHDSql);
			return crHDObj;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return null;
		}
	} // end of method

	/**
	 * Purpose : this method is used to find all the debits that are applicable for
	 * processing the arrears from sal_debit_head
	 * 
	 * @param bean
	 * @return Object[][], containing debit codes and their abbreviation
	 */
	public Object[][] getDebitHeader(PromotionArrears bean) {
		try {
			String drHDSql = " SELECT DEBIT_CODE, TRIM(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD "
					+ " WHERE DEBIT_PAYFLAG = 'Y' AND DEBIT_APPLICABLE_ARREARS = 'Y' "
					+ " AND DEBIT_PERIODICITY = 'M' ORDER BY DEBIT_CODE ";
			Object[][] dbHDObj = getSqlModel().getSingleResult(drHDSql);
			return dbHDObj;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return null;
		}
	} // end of method

	/**
	 * Purpose : this method is used to find all the credits that are applicable for
	 * processing the arrears from sal_credit_head
	 * 
	 * @param rows
	 * @param d
	 * @param c
	 * @param emp_id
	 * @param month
	 * @param year
	 * @param total_credit
	 * @param total_debit
	 * @param arrDays
	 * @param bean
	 * @param netPay
	 * @param forMonth
	 * @param forYear
	 * @param promCode
	 * @param onHold
	 * @return Object[][], containing credit codes and their abbreviation
	 */
	public int save(Object[][] rows, Object[][] d, Object[][] c,
			String[] emp_id, String month, String year, String[] total_credit,
			String[] total_debit, String[] arrDays, PromotionArrears bean,
			String[] netPay, String[] forMonth, String[] forYear,
			String[] promCode, String[] onHold) {
		try {
			// if it is 2 record is getting modified if 1 new insert
			int saveType = 2;
			String delArrearCode = bean.getArrCode();
			String insertArrCode = bean.getArrCode();
			if (delArrearCode.equals(""))
				delArrearCode = "0";

			// if true new record is inserted in the tables
			if (insertArrCode.equals("")) {
				String arrcodeQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 from HRMS_ARREARS_LEDGER";
				Object[][] code = getSqlModel().getSingleResult(arrcodeQuery);
				insertArrCode = String.valueOf(code[0][0]);
				saveType = 1; // change savetype = 1 as it is new insert
			}
			//DELETE FROM HRMS_ARREAR_YEAR
			String deleteQuery = " DELETE FROM HRMS_ARREARS_"
					+ bean.getEArrYear() + " WHERE ARREARS_CODE =  "
					+ delArrearCode;
			//DELETE FROM CRDIT TABLE
			String deleteCreditQuery = " DELETE FROM HRMS_ARREARS_CREDIT_"
					+ bean.getEArrYear() + " WHERE ARREARS_CODE =  "
					+ delArrearCode;
			//DELETE FROM DEBIT TABLE
			String deleteDebitQuery = " DELETE FROM HRMS_ARREARS_DEBIT_"
					+ bean.getEArrYear() + " WHERE ARREARS_CODE = "
					+ delArrearCode;
			//DELETE FROM LEDGER
			String deleteLedgerQuery = " DELETE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_CODE = "
					+ delArrearCode;

			getSqlModel().singleExecute(deleteCreditQuery);
			getSqlModel().singleExecute(deleteDebitQuery);
			getSqlModel().singleExecute(deleteQuery);
			getSqlModel().singleExecute(deleteLedgerQuery);

			// (select nvl(max(arrears_code),0)+1 from
			// hrms_arrears_"+bean.getarryear()+")
			// query for arrears hdr table
			String insertQuery = " INSERT INTO HRMS_ARREARS_"
					+ bean.getEArrYear()
					+ "(ARREARS_CODE,EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,"
					+ " ARREARS_NET_AMT,ARREARS_CREDITS_AMT,"
					+ " ARREARS_DEBITS_AMT,ARREARS_ONHOLD,ARREARS_PROMCODE) VALUES("
					+ insertArrCode + ",?,?,?,?,?,?,?,?,?)";

			// query for arrears_credit table
			String insertCreditQuery = " INSERT INTO HRMS_ARREARS_CREDIT_"
					+ bean.getEArrYear()
					+ " (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_CREDIT_CODE, "
					+ " ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT,PROM_CODE) VALUES("
					+ insertArrCode + ",?,?,?,?,?,?)";

			// query for arrears_debit table
			String insertDebitQuery = " INSERT INTO HRMS_ARREARS_DEBIT_"
					+ bean.getEArrYear()
					+ " (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_DEBIT_CODE, "
					+ " ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT,PROM_CODE) VALUES("
					+ insertArrCode + ",?,?,?,?,?,?)";

			// query for updating promotion table process_status as arrears are
			// PROCCESSED
			String updatePromoFlagQuery = " UPDATE HRMS_PROMOTION SET ARREARS_PROCESS_STATUS = 'Y'"
					+ " WHERE PROM_CODE = ?";
			// query for insert record in ledger table
			// UPDATED BY REEBA
			String ledgerInsertQuery = " INSERT INTO HRMS_ARREARS_LEDGER(ARREARS_CODE,ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_STATUS,"
					+ " ARREARS_PROCESS_DATE,ARREARS_DIVISION,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL,ARREARS_TYPE,ARREARS_PAY_IN_SAL,ARREARS_PAID_MONTH, ARREARS_PAID_YEAR)"
					+ " VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?)";
			int debitCount = 0;
			int creditCount = 0;

			Object[][] creditData = new Object[emp_id.length * c.length][6];
			Object[][] debitData = new Object[emp_id.length * d.length][6];
			Object[][] saveObj = new Object[emp_id.length][9];
			Object[][] ledgerObj = new Object[1][13];
			// object for inserting into hrms_arrears_ledger table
			ledgerObj[0][0] = insertArrCode;
			ledgerObj[0][1] = bean.getEArrMonth();
			ledgerObj[0][2] = bean.getEArrYear();
			ledgerObj[0][3] = "P";

			ledgerObj[0][4] = bean.getDivCode();
			if (bean.getDivCode() == null)
				ledgerObj[0][4] = "";

			ledgerObj[0][5] = bean.getBrnCode();
			if (bean.getBrnCode() == null)
				ledgerObj[0][5] = "";

			ledgerObj[0][6] = bean.getDeptCode();
			if (bean.getDeptCode() == null)
				ledgerObj[0][6] = "";

			ledgerObj[0][7] = bean.getTypeCode();
			if (bean.getTypeCode() == null)
				ledgerObj[0][7] = "";

			ledgerObj[0][8] = bean.getPayBillNo();
			if (bean.getPayBillNo() == null)
				ledgerObj[0][8] = "";
			ledgerObj[0][9] = "P";
			ledgerObj[0][10] = "N";
			if (bean.getPayinSalFlag() != null)
				if (bean.getPayinSalFlag().equals("true"))
					ledgerObj[0][10] = "Y";
			// ADDED BY REEBA BEGINS
			ledgerObj[0][11] = bean.getPaidArrMonth();
			ledgerObj[0][12] = bean.getPaidYear();
			// ADDED BY REEBA ENDS

			getSqlModel().singleExecute(ledgerInsertQuery, ledgerObj);

			// object to pass to update query for promotion table
			Object[][] pCode = new Object[1][1];

			// loop for total employees
			for (int i = 0; i < emp_id.length; i++) {

				// if it is already updated it wont update it again
				if (!String.valueOf(pCode[0][0]).equals(promCode[i])) {
					pCode[0][0] = promCode[i];
					getSqlModel().singleExecute(updatePromoFlagQuery, pCode);
				}

				// save object for hrms_arrears_2008(year)
				saveObj[i][0] = emp_id[i];
				saveObj[i][1] = forMonth[i];
				saveObj[i][2] = forYear[i];
				saveObj[i][3] = arrDays[i];
				saveObj[i][4] = netPay[i];
				saveObj[i][5] = total_credit[i];
				saveObj[i][6] = total_debit[i];
				saveObj[i][7] = onHold[i];
				saveObj[i][8] = promCode[i];

				/*
				 * logger.info("saveObj[i][0]"+saveObj[i][0]);
				 * logger.info("saveObj[i][1]"+saveObj[i][1]);
				 * logger.info("saveObj[i][2]"+saveObj[i][2]);
				 * logger.info("saveObj[i][3]"+saveObj[i][3]);
				 * logger.info("saveObj[i][4]"+saveObj[i][4]);
				 * logger.info("saveObj[i][5]"+saveObj[i][5]);
				 * logger.info("saveObj[i][6]"+saveObj[i][6]);
				 * logger.info("saveObj[i][7]"+saveObj[i][7]);
				 * logger.info("saveObj[i][8]"+saveObj[i][8]);
				 * logger.info("total_credit[i];"+total_credit[i]);
				 */

				// loop for individual employee credit and debit
				for (int j = 0, k = 0; j < c.length + d.length; j++) {
					if (j < c.length) // for all the credits
					{
						creditData[creditCount][0] = emp_id[i]; // emp_id
						creditData[creditCount][1] = c[j][0]; // credit code
						creditData[creditCount][2] = forMonth[i];// bean.getarrrefmonth();
						// month
						creditData[creditCount][3] = forYear[i]; // year
						creditData[creditCount][4] = rows[i][j]; // credit
						// amount
						creditData[creditCount][5] = promCode[i];

						// counter for credit data object required for batch
						// update
						creditCount++;

					} else // for all the debits
					{
						debitData[debitCount][0] = emp_id[i]; // emp_id
						debitData[debitCount][1] = d[k][0]; // debit_code
						debitData[debitCount][2] = forMonth[i];// bean.getarrrefmonth();
						// month
						debitData[debitCount][3] = forYear[i]; // year
						debitData[debitCount][4] = rows[i][j]; // debit_amount
						debitData[debitCount][5] = promCode[i];
						k++; // should be diffrent then j counter

						// counter for debit data object required for batch
						// update
						debitCount++;
					}
				} // end for loop
			} // end if
			// query for hdr table
			boolean flag = getSqlModel().singleExecute(insertQuery, saveObj);
			if (flag) {
				// query for dtl credit table
				if (flag)
					flag = getSqlModel().singleExecute(insertCreditQuery,
							creditData);

				// query for dtl debit table
				if (flag)
					flag = getSqlModel().singleExecute(insertDebitQuery,
							debitData);
				if (flag) {
					/**
					 * Following code calculates the tax and updates tax process
					 */
					try {
						CommonTaxCalculationModel model = new CommonTaxCalculationModel();
						logger.info("I m calling tax calculation method");
						model.initiate(context, session);
						Object[][] empList = new Object[emp_id.length][1];
						for (int i = 0; i < empList.length; i++)
							empList[i][0] = emp_id[i];
						int fromYear = Integer.parseInt(bean.getEArrYear());
						int mnth = Integer.parseInt(bean.getEArrMonth());
						logger.info("Month : " + month + " Year : " + fromYear);
						if (mnth <= 3)
							fromYear--;
						if (empList != null && empList.length > 0)
							model.calculateTax(empList, String
									.valueOf(fromYear), String
									.valueOf(fromYear + 1));
						model.terminate();
					} catch (Exception e) {
						logger
								.error("Exception in save() in PromotionArrearsModel while calling calculateTax : "
										+ e);

					}
				}
			}
			return saveType;

		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			e.printStackTrace();
			return 0;
		}
	} // end of method

	/**
	 * Purpose : this method is used to apply pagination on user screen to divide the
	 * records in to no of pages.
	 * 
	 * @param bean
	 * @param empLength
	 * @param empObj
	 * @param request
	 * @param empSearch
	 */
	public void doPaging(PromotionArrears bean, int empLength,
			Object[][] empObj, HttpServletRequest request, String empSearch) {
		try {
			// totalRec -: No. of records per page
			// fromTotRec -: Starting No. of record to show on a current page
			// toTotRec -: Last No. of record to show on a current page
			// pageNo -: Current page to show
			// totalPage -: Total No. of Pages

			String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));

			double row1 = 0.0;
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int totalPage = 0;

			row1 = (double) empLength / totalRec;
			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(row1);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			if (String.valueOf(bean.getHdPage()).equals("null")
					|| String.valueOf(bean.getHdPage()).equals(null)
					|| String.valueOf(bean.getHdPage()).equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;
				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			} else {
				pageNo = Integer.parseInt(bean.getHdPage());

				if (pageNo == 1) {
					fromTotRec = 0;
					toTotRec = totalRec;
				} else {
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}
				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
			}

			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("PageNo", pageNo);
			request.setAttribute("To_TOT", toTotRec);
			request.setAttribute("From_TOT", fromTotRec);
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
		}
	} // end of method

	/**
	 * Purpose : this method is used to show the records saved after processing
	 * 
	 * @param request
	 * @param bean
	 */
	public void showArrearsRecords(HttpServletRequest request,
			PromotionArrears bean) {
		try {
			// employee query to retrieve the records for that selected proccess
			String empObjQuery = " SELECT HRMS_ARREARS_"
					+ bean.getEArrYear()
					+ ".EMP_ID,ARREARS_CODE, EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME) EMP_NAME, "
					+ " ARREARS_MONTH, ARREARS_YEAR,ARREARS_DAYS,DECODE(ARREARS_ONHOLD,'Y','true','false'),ARREARS_PROMCODE FROM HRMS_ARREARS_"
					+ bean.getEArrYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_"
					+ bean.getEArrYear()
					+ ".EMP_ID) "
					+ " INNER JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_"
					+ bean.getEArrYear() + ".ARREARS_CODE) "
					+ " WHERE ARREARS_CODE = " + bean.getArrCode()
					+ " ORDER BY UPPER(EMP_FNAME || EMP_MNAME || EMP_LNAME) ";

			Object empObj[][] = getSqlModel().getSingleResult(empObjQuery);
			ArrayList<Object> arrList = new ArrayList<Object>();
			if (empObj != null && empObj.length > 0) {
				int arrCode = Integer.parseInt(bean.getArrCode());
				bean.setArrCode(String.valueOf(arrCode));
				setHeaders(bean, arrCode);
				for (int i = 0; i < empObj.length; i++) {
					int empCode = Integer
							.parseInt(String.valueOf(empObj[i][0]));
					int month = Integer.parseInt(String.valueOf(empObj[i][4]));
					int year = Integer.parseInt(String.valueOf(empObj[i][5]));
					int promCode = Integer.parseInt(String
							.valueOf(empObj[i][8]));

					PromotionArrears arrBean = new PromotionArrears();
					arrBean.setEId(String.valueOf(empCode));
					arrBean.setEToken(String.valueOf(empObj[i][2]));
					arrBean.setEName(String.valueOf(empObj[i][3]));
					arrBean.setMonth(Utility.month(month));
					arrBean.setHMonth(String.valueOf(month));
					arrBean.setYear(String.valueOf(year));
					arrBean.setArrDays(String.valueOf(empObj[i][6]));
					arrBean.setEmpOnHoldFlag(String.valueOf(empObj[i][7]));
					arrBean.setPromotionCode(String.valueOf(empObj[i][8]));

					/*
					 * String salDaysQuery = " SELECT SAL_DAYS FROM
					 * HRMS_SALARY_" + year + " INNER JOIN HRMS_SALARY_LEDGER
					 * ON(HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_" + year +
					 * ".SAL_LEDGER_CODE) " + " WHERE EMP_ID = " + year + " AND
					 * LEDGER_MONTH = " + month + " AND LEDGER_YEAR = " + year;
					 * Object[][] salDays = getSqlModel().getSingleResult(
					 * salDaysQuery); if (salDays != null && salDays.length > 0)
					 * arrBean.setSalDays(String.valueOf(salDays[0][0]));
					 */

					arrBean.setSalDays("");

					// query to retreive the arrears credit amount already saved
					String creditAmtQuery = " SELECT  CREDIT_CODE, NVL(ARREARS_AMT,'0') FROM HRMS_ARREARS_CREDIT_"
							+ bean.getEArrYear()
							+ " RIGHT JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_"
							+ bean.getEArrYear()
							+ ".ARREARS_CREDIT_CODE  "
							+ " AND ARREARS_EMP_ID = "
							+ empCode
							+ " AND ARREARS_MONTH = "
							+ month
							+ " AND PROM_CODE = "
							+ promCode
							+ " AND HRMS_ARREARS_CREDIT_"
							+ bean.getEArrYear()
							+ ".ARREARS_CODE ="
							+ arrCode
							+ ")"
							+ " LEFT JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_CREDIT_"
							+ bean.getEArrYear()
							+ ".ARREARS_CODE "
							+ " AND HRMS_ARREARS_LEDGER.ARREARS_CODE = "
							+ arrCode
							+ ")"
							+ " WHERE CREDIT_PAYFLAG = 'Y' AND CREDIT_APPLICABLE_ARREARS = 'Y'  AND CREDIT_PERIODICITY IN ('M','A') "
							+ " ORDER BY CREDIT_CODE ";
					Object[][] salCrObj = getSqlModel().getSingleResult(
							creditAmtQuery);

					double creditVal = 0.0;
					double debitVal = 0.0;
					double sumCr = 0.0;
					double sumDr = 0.0;
					ArrayList<Object> crValList = new ArrayList<Object>();
					ArrayList<Object> drValList = new ArrayList<Object>();

					// loop setting the credit values in iterator
					if (salCrObj != null && salCrObj.length > 0) {
						for (int j = 0; j < salCrObj.length; j++) {
							PromotionArrears crValBean = new PromotionArrears();
							if (salCrObj[j][1] != null
									&& !String.valueOf(salCrObj[j][1]).equals(
											""))
								creditVal = Double.parseDouble(String
										.valueOf(salCrObj[j][1]));
							crValBean.setCreditVal(String.valueOf(creditVal));
							crValList.add(crValBean);
							sumCr += creditVal;
						} // end for loop
					} // end if

					// query to retreive the arrears debit amount already saved
					String debitAmtQuery = " SELECT DEBIT_CODE, NVL(ARREARS_AMT,'0')  FROM HRMS_ARREARS_DEBIT_"
							+ bean.getEArrYear()
							+ " RIGHT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"
							+ bean.getEArrYear()
							+ ".ARREARS_DEBIT_CODE AND "
							+ " ARREARS_MONTH = "
							+ month
							+ " AND ARREARS_EMP_ID = "
							+ empCode
							+ " AND PROM_CODE = "
							+ promCode
							+ " AND HRMS_ARREARS_DEBIT_"
							+ bean.getEArrYear()
							+ ".ARREARS_CODE ="
							+ arrCode
							+ ")"
							+ " LEFT JOIN HRMS_ARREARS_LEDGER ON(HRMS_ARREARS_LEDGER.ARREARS_CODE = HRMS_ARREARS_DEBIT_"
							+ bean.getEArrYear()
							+ ".ARREARS_CODE "
							+ " AND HRMS_ARREARS_LEDGER.ARREARS_CODE = "
							+ arrCode
							+ ")"
							+ " WHERE DEBIT_PAYFLAG = 'Y' AND DEBIT_APPLICABLE_ARREARS = 'Y'  AND DEBIT_PERIODICITY = 'M'"
							+ " ORDER BY DEBIT_CODE";
					Object[][] salDrObj = getSqlModel().getSingleResult(
							debitAmtQuery);

					// loop setting the debit values in iterator
					if (salDrObj != null && salDrObj.length > 0) {
						for (int j = 0; j < salDrObj.length; j++) {
							PromotionArrears drValBean = new PromotionArrears();
							if (salDrObj[j][1] != null
									&& !String.valueOf(salDrObj[j][1]).equals(
											""))
								debitVal = Double.parseDouble(String
										.valueOf(salDrObj[j][1]));
							drValBean.setDebitVal(String.valueOf(debitVal));
							drValList.add(drValBean);
							sumDr += debitVal;
						} // end for loop
					} // end if

					arrBean.setCrValList(crValList);
					arrBean.setTotalCr(String.valueOf(sumCr));
					arrBean.setDrValList(drValList);
					arrBean.setTotalDr(String.valueOf(sumDr));
					double amtPay = sumCr - sumDr;
					arrBean.setAmtPay(String.valueOf(amtPay));
					arrList.add(arrBean);
				}
				bean.setFlag(true);
				bean.setArrList(arrList);
				bean.setEmpChkFlag("true");
				bean.setMonthName(bean.getEArrMonth());
				bean.setSavedFlag("true");
			} // end if for empobj
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			e.printStackTrace();
		}
	} // end of method

	/**
	 * Purpose : this method is used to set the credits and debits header that are
	 * applicable for arrears
	 * 
	 * @param bean
	 * @param arrCode
	 */
	public void setHeaders(PromotionArrears bean, int arrCode) {
		try {
			String creditQuery = " SELECT CREDIT_CODE,TRIM(CREDIT_ABBR)  FROM HRMS_CREDIT_HEAD "
					+ " WHERE CREDIT_PAYFLAG = 'Y' AND CREDIT_APPLICABLE_ARREARS = 'Y' "
					+ " AND CREDIT_PERIODICITY IN ('M','A') "
					+ " ORDER BY CREDIT_CODE ";
			String debitQuery = " SELECT DEBIT_CODE,TRIM(DEBIT_ABBR) FROM HRMS_DEBIT_HEAD "
					+ " WHERE DEBIT_PAYFLAG = 'Y' AND DEBIT_APPLICABLE_ARREARS = 'Y' "
					+ " AND DEBIT_PERIODICITY = 'M' " + " ORDER BY DEBIT_CODE ";
			Object[][] salCrCodes = getSqlModel().getSingleResult(creditQuery);
			Object[][] salDrCodes = getSqlModel().getSingleResult(debitQuery);
			if (salCrCodes != null || salCrCodes.length > 0) {
				ArrayList<Object> crHDList = new ArrayList<Object>();
				for (int i = 0; i < salCrCodes.length; i++) {
					PromotionArrears crBean = new PromotionArrears();
					crBean.setCreditCode(String.valueOf(salCrCodes[i][0]));
					crBean.setCreditName(String.valueOf(salCrCodes[i][1]));
					crHDList.add(crBean);
				}
				bean.setCrHDList(crHDList);
			}
			ArrayList<Object> drHDList = new ArrayList<Object>();
			if (salDrCodes != null || salDrCodes.length > 0) {
				for (int i = 0; i < salDrCodes.length; i++) {
					PromotionArrears drBean = new PromotionArrears();
					drBean.setDebitCode(String.valueOf(salDrCodes[i][0]));
					drBean.setDebitName(String.valueOf(salDrCodes[i][1]));
					drHDList.add(drBean);
				}
				bean.setDrHDList(drHDList);
			}
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
		}
	} // end of method

	/**
	 * Purpose : this method is used to finalize and to unlock the arrears. Changes the
	 * ledger table status in database.
	 * 
	 * @param bean
	 * @param type
	 * @return boolean, return true if successful else false.
	 */
	public boolean lockArrears(PromotionArrears bean, String type) {
		try {
			String status = type;

			// if arrears have to be locked
			if (status.equals("lock"))
				status = "L";

			// if arrears have to unlocked which has been locked previously
			else
				status = "P";

			// update query to change the status in arrears_ledger table
			String updateQuery = " UPDATE HRMS_ARREARS_LEDGER SET ARREARS_STATUS = '"
					+ status + "'WHERE ARREARS_CODE = " + bean.getArrCode();

			return getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return false;
		}
	} // end of method

	/**
	 * Purpose : this method is used to delete the saved records from database. it deletes
	 * the record from ledger, arrears_2008, credit and debits table.
	 * 
	 * @param bean
	 * @return boolean, return true if successful else false.
	 */
	public boolean delete(PromotionArrears bean) {
		try {
			String delArrearCode = bean.getArrCode();
			String deleteQuery = " DELETE FROM HRMS_ARREARS_"
					+ bean.getEArrYear() + " WHERE ARREARS_CODE =  "
					+ delArrearCode;
			String deleteCreditQuery = " DELETE FROM HRMS_ARREARS_CREDIT_"
					+ bean.getEArrYear() + " WHERE ARREARS_CODE =  "
					+ delArrearCode;
			String deleteDebitQuery = " DELETE FROM HRMS_ARREARS_DEBIT_"
					+ bean.getEArrYear() + " WHERE ARREARS_CODE = "
					+ delArrearCode;
			String deleteLedgerQuery = "DELETE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_CODE = "
					+ delArrearCode;
			getSqlModel().singleExecute(deleteCreditQuery);
			getSqlModel().singleExecute(deleteDebitQuery);
			getSqlModel().singleExecute(deleteQuery);
			getSqlModel().singleExecute(deleteLedgerQuery);
			return true;
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			return false;
		}
	} // end of method

	public void updatePromotionStatus(String[] promCode) {
		try {
			String updatePromoFlagQuery = " UPDATE HRMS_PROMOTION SET ARREARS_PROCESS_STATUS = 'N' "
					+ " WHERE PROM_CODE = ? ";
			Object[][] promCodeObj = new Object[promCode.length][1];
			for (int i = 0; i < promCodeObj.length; i++) {
				promCodeObj[i][0] = promCode[i];
				logger.info("promCodeObj==" + promCodeObj[i][0]);
			}
			getSqlModel().singleExecute(updatePromoFlagQuery, promCodeObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}// end of method

	/**
	 * Purpose : this method is used to remove the selected records after processing this
	 * remove the selected row from jsp and reflects the changes after saving.
	 * 
	 * @param arrBean
	 * @param empId
	 * @param empToken
	 * @param empName
	 * @param totCredit
	 * @param totDebit
	 * @param salDays
	 * @param arr_Days
	 * @param net_pay
	 * @param for_month
	 * @param for_year
	 * @param prom_code
	 * @param rowsAmt
	 * @param credit
	 * @param debit
	 * @param request
	 */
	public void removeEmpRecord(PromotionArrears arrBean, String[] empId,
			String[] empToken, String[] empName, String[] totCredit,
			String[] totDebit, String[] salDays, String[] arr_Days,
			String[] net_pay, String[] for_month, String[] for_year,
			String[] prom_code, Object[][] rowsAmt, Object[][] credit,
			Object[][] debit, HttpServletRequest request) {
		if (arrBean.getDivCode() != null && !arrBean.getDivCode().equals("")) {
			arrBean.setDivCode(arrBean.getDivCode());
			arrBean.setDivName(arrBean.getDivName());
			arrBean.setEmpChkFlag("true");
		}
		if (arrBean.getTypeCode() != null && !arrBean.getTypeCode().equals("")) {
			arrBean.setTypeCode(arrBean.getTypeCode());
			arrBean.setTypeName(arrBean.getTypeName());
			arrBean.setEmpChkFlag("true");
		}
		if (arrBean.getPayBillNo() != null
				&& !arrBean.getPayBillNo().equals("")) {
			arrBean.setPayBillNo(arrBean.getPayBillNo());
			arrBean.setPayBillName(arrBean.getPayBillName());
			arrBean.setEmpChkFlag("true");
		}
		if (arrBean.getBrnCode() != null && !arrBean.getBrnCode().equals("")) {
			arrBean.setBrnCode(arrBean.getBrnCode());
			arrBean.setBrnName(arrBean.getBrnName());
			arrBean.setEmpChkFlag("true");
		}
		if (arrBean.getDeptCode() != null && !arrBean.getDeptCode().equals("")) {
			arrBean.setDeptCode(arrBean.getDeptCode());
			arrBean.setDeptName(arrBean.getDeptName());
			arrBean.setEmpChkFlag("true");
		}
		try {
			if (empId != null && empId.length > 0) {
				Object[][] salCrCodes = getCreditHeader(arrBean);
				Object[][] salDrCodes = getDebitHeader(arrBean);

				// loop setting the credits head
				if (salCrCodes != null || salCrCodes.length > 0) {
					ArrayList<Object> crHDList = new ArrayList<Object>();
					for (int i = 0; i < salCrCodes.length; i++) {
						PromotionArrears crBean = new PromotionArrears();
						crBean.setCreditCode(String.valueOf(salCrCodes[i][0]));
						crBean.setCreditName(String.valueOf(salCrCodes[i][1]));
						crHDList.add(crBean);
					} // end for loop
					arrBean.setCrHDList(crHDList);
				} // END IF
				ArrayList<Object> drHDList = new ArrayList<Object>();

				// loop setting the debits head
				if (salDrCodes != null || salDrCodes.length > 0) {
					for (int i = 0; i < salDrCodes.length; i++) {
						PromotionArrears drBean = new PromotionArrears();
						drBean.setDebitCode(String.valueOf(salDrCodes[i][0]));
						drBean.setDebitName(String.valueOf(salDrCodes[i][1]));
						drHDList.add(drBean);
					} // end for loop
					arrBean.setDrHDList(drHDList);
				} // end if

				ArrayList<Object> arrList = new ArrayList<Object>();
				Object[] empObj = new Object[empId.length];
				doPaging(arrBean, empObj.length, new Object[0][0], request, "");

				// loop for setting the employee records in iterator
				for (int i = 0; i < empId.length; i++) {
					PromotionArrears bean = new PromotionArrears();
					ArrayList<Object> crValList = new ArrayList<Object>();
					ArrayList<Object> drValList = new ArrayList<Object>();
					bean.setPromotionCode(prom_code[i]);
					bean.setEId(empId[i]);
					bean.setEToken(empToken[i]);
					bean.setEName(empName[i]);
					bean
							.setMonth(Utility.month(Integer
									.parseInt(for_month[i])));
					bean.setHMonth(for_month[i]);
					bean.setYear(for_year[i]);
					bean.setSalDays(salDays[i]);
					bean.setArrDays(arr_Days[i]);

					// loop for setting the credits and debits valuie in
					// iterator
					for (int j = 0; j < rowsAmt[i].length; j++) {
						PromotionArrears crValBean = new PromotionArrears();
						PromotionArrears drValBean = new PromotionArrears();
						if (j < credit.length) {
							crValBean.setCreditVal(String
									.valueOf(rowsAmt[i][j]));
							crValList.add(crValBean);
						} else {
							drValBean
									.setDebitVal(String.valueOf(rowsAmt[i][j]));
							drValList.add(drValBean);
						}
					}
					bean.setCrValList(crValList);
					bean.setDrValList(drValList);
					bean.setTotalCr(totCredit[i]);
					bean.setTotalDr(totDebit[i]);
					bean.setAmtPay(net_pay[i]);
					arrList.add(bean);
					arrBean.setMonthName(arrBean.getEArrMonth());
				}
				arrBean.setFlag(true);
				arrBean.setArrList(arrList);

			} // end if empobj

		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
		}
	} // end of method

	/**
	 * Purpose : this method is used to change the status in arrears_2008 table the
	 * selected record will be kept on "On Hold".
	 * 
	 * @param empId
	 * @param month
	 * @param year
	 * @param arrCode
	 */
	public void onHoldSave(String[] empId, String[] month, String[] year,
			String arrCode) {
		try {
			Object[][] updateObj = new Object[empId.length][4];

			// query to update the status in arrears_2008 (year defined)
			String updateQuery = " UPDATE HRMS_ARREARS_"
					+ year
					+ " SET ARREARS_ONHOLD = 'Y' "
					+ " WHERE EMP_ID = ? AND ARREARS_MONTH = ? AND ARREARS_YEAR = ? AND ARREARS_CODE = ?";
			for (int i = 0; i < updateObj.length; i++) {
				updateObj[i][0] = empId[i];
				updateObj[i][1] = month[i];
				updateObj[i][2] = year[i];
				updateObj[i][3] = arrCode;
			}
			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
			;
		}
	} // end of method

	/**
	 * Purpose : this method is used to remove the "On Hold" status from selected records
	 * and changes the status in arrears_2008 table
	 * 
	 * @param empId
	 * @param month
	 * @param year
	 * @param arrCode
	 */
	public void removeOnHoldSave(String[] empId, String[] month, String[] year,
			String arrCode) {
		try {
			Object[][] updateObj = new Object[empId.length][4];
			String updateQuery = " UPDATE HRMS_ARREARS_"
					+ year
					+ " SET ARREARS_ONHOLD = 'N' "
					+ " WHERE EMP_ID = ? AND ARREARS_MONTH = ? AND ARREARS_YEAR = ? AND ARREARS_CODE = ?";
			for (int i = 0; i < updateObj.length; i++) {
				updateObj[i][0] = empId[i];
				updateObj[i][1] = month[i];
				updateObj[i][2] = year[i];
				updateObj[i][3] = arrCode;
			}
			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			logger.error("Error in promotion arrears " + e);
		}
	}

	/**
	 * Purpose : this method is used to recalculate the amounts and arrears days It works
	 * same as processing picks the records and amount from promotion but
	 * difference is here selected records are recalculated.
	 * 
	 * @param empId
	 * @param month
	 * @param year
	 * @param promCode
	 * @param bean
	 * @return Object[][] containing recalculated values for selected records
	 */
	public Object[][] recalculate(String[] empId, String[] month,
			String[] year, String[] promCode, PromotionArrears bean, String path) {
		return null;
	} // end of method

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean deleteEmployee(String[] empId, String[] month,
			String[] year, String[] promCode, PromotionArrears bean) {
		boolean result = false;
		// loop for selected no. of records
		if (empId != null && empId.length > 0) {
			for (int i = 0; i < empId.length; i++) {
				// DELETE FROM DEBITS
				String deleteQuery = " DELETE FROM HRMS_ARREARS_"
						+ bean.getEArrYear() + " WHERE ARREARS_CODE ="
						+ bean.getArrCode() + "  AND ARREARS_PROMCODE ="
						+ promCode[i] + " AND EMP_ID=" + empId[i]
						+ " AND ARREARS_MONTH = " + month[i]
						+ " AND ARREARS_YEAR=" + year[i];
				String deleteCreditQuery = " DELETE FROM HRMS_ARREARS_CREDIT_"
						+ bean.getEArrYear() + " WHERE ARREARS_CODE ="
						+ bean.getArrCode() + "  AND PROM_CODE = "
						+ promCode[i] + " AND ARREARS_EMP_ID=" + empId[i]
						+ " AND ARREARS_MONTH = " + month[i]
						+ " AND ARREARS_YEAR=" + year[i];
				String deleteDebitQuery = " DELETE FROM HRMS_ARREARS_DEBIT_"
						+ bean.getEArrYear() + " WHERE ARREARS_CODE ="
						+ bean.getArrCode() + "  AND PROM_CODE =" + promCode[i]
						+ " AND ARREARS_EMP_ID=" + empId[i]
						+ " AND ARREARS_MONTH = " + month[i]
						+ " AND ARREARS_YEAR=" + year[i];
				result = getSqlModel().singleExecute(deleteCreditQuery);
				result = getSqlModel().singleExecute(deleteDebitQuery);
				result = getSqlModel().singleExecute(deleteQuery);

				String updatePromotionStatus = " UPDATE HRMS_PROMOTION SET ARREARS_PROCESS_STATUS = 'N' WHERE PROM_CODE = "
						+ promCode[i];
				result = getSqlModel().singleExecute(updatePromotionStatus);
			}
		}
		return result;
	}

	public String getEmpIdString(PromotionArrears bean, String eArrDate) {
		String empSql = "SELECT EMP_CODE FROM HRMS_PROMOTION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=EMP_CODE)"
				+ " WHERE (ARREARS_PROCESS_STATUS = 'N') AND EFFECT_DATE <= TO_DATE('"
				+ eArrDate + "', 'DD-MM-YYYY') ";
		empSql = setFilters(bean, empSql);
		Object[][] empObj = getSqlModel().getSingleResult(empSql);
		String empIdString = "0";
		for (int i = 0; i < empObj.length; i++) {
			empIdString += "," + String.valueOf(empObj[i][0]);
		}
		return empIdString;
	}

	public HashMap<String, String> getEmpRatingData(String empIds) {
		String query = "";
		HashMap<String, String> performanceDataMap = null;
		Object[][] performanceDataObj = null;
		empIds = Utility.getConcatenatedIds("RATING_EMP_ID", empIds);
		try {
			query = " SELECT RATING_EMP_ID,CASE WHEN RATING_MONTH=12 THEN 1 ELSE RATING_MONTH+1 END, CASE WHEN RATING_MONTH=12 THEN RATING_YEAR+1 ELSE RATING_YEAR END, RATING FROM HRMS_EMP_MONTHLY_RATING"
					// +" INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=RATING_EMP_ID)"
					+ " WHERE 1=1 " + empIds; // RATING_MONTH="+month+" AND
												// RATING_YEAR="+year;
			// if branch is selected
			performanceDataObj = getSqlModel().getSingleResult(query);
			if (performanceDataObj != null && performanceDataObj.length > 0) {
				performanceDataMap = new HashMap<String, String>();
				for (int i = 0; i < performanceDataObj.length; i++) {
					/*
					 * create combine key of emp_id,rating month & rating year
					 */
					String mapKey = String.valueOf(performanceDataObj[i][0])
							+ "#" + String.valueOf(performanceDataObj[i][1])
							+ "#" + String.valueOf(performanceDataObj[i][2]);
					performanceDataMap.put(mapKey, String
							.valueOf(performanceDataObj[i][3]));

				}
			}
			// performanceDataMap=getSqlModel().getSingleResultMap(query,0,2);
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
		}
		return performanceDataMap;
	}

	public HashMap<String, String> getEmpCTCData(String empIds) {
		String query = "";
		HashMap<String, String> ctcDataMap = null;
		Object[][] ctcDataObj = null;
		empIds = Utility.getConcatenatedIds("HRMS_EMP_OFFC.EMP_ID", empIds);
		try {
			query = " SELECT HRMS_SALARY_MISC.EMP_ID, NVL(CTC,0) FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					// +" INNER JOIN HRMS_PROMOTION ON(HRMS_EMP_OFFC.EMP_ID =
					// HRMS_PROMOTION.EMP_CODE) "
					+ " WHERE 1=1 " + empIds;
			// if branch is selected

			ctcDataObj = getSqlModel().getSingleResult(query);
			if (ctcDataObj != null && ctcDataObj.length > 0) {
				ctcDataMap = new HashMap<String, String>();
				for (int i = 0; i < ctcDataObj.length; i++) {
					ctcDataMap.put(String.valueOf(ctcDataObj[i][0]), String
							.valueOf(ctcDataObj[i][1]));

				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpCTCData:" + e);
		}
		return ctcDataMap;
	}

	public HashMap<String, Object[]> getEmpFlagData(String empIds) {
		String query = "";
		HashMap<String, Object[]> empFlagData = null;
		Object[][] ctcDataObj = null;
		empIds = Utility.getConcatenatedIds("EMP_ID", empIds);
		try {
			query = "SELECT NVL(SAL_EPF_FLAG,'N'), NVL(SAL_PTAX_FLAG,'Y'),EMP_ID FROM HRMS_SALARY_MISC WHERE 1=1 "
					+ empIds;
			// if branch is selected

			ctcDataObj = getSqlModel().getSingleResult(query);
			if (ctcDataObj != null && ctcDataObj.length > 0) {
				empFlagData = new HashMap<String, Object[]>();
				for (int i = 0; i < ctcDataObj.length; i++) {
					empFlagData.put(String.valueOf(ctcDataObj[i][2]),
							(ctcDataObj[i]));

				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpCTCData:" + e);
		}
		return empFlagData;
	}

	public double getEmpESIAmt(Object[][] ESI_ConfigObj, String[] dataString,
			double grossESICreditTotal, double monthESICreditTotal,
			String comLedgerCode, SalaryProcessModel salModel) {
		double esi_amt = 0;
		try {
			String typeId = dataString[0];
			String branchId = dataString[1];
			String path = dataString[2];
			String month = dataString[3];
			String year = dataString[4];
			String emp_id = dataString[5];
			/*
			 * for (int i = 0; i < ESI_ConfigObj.length; i++) { for (int j = 0;
			 * j < ESI_ConfigObj[0].length; j++) {
			 * logger.info("ESI_ConfigObj["+i+"]["+j+"]=="+ESI_ConfigObj[i][j]); } }
			 */

			String previousEsic = "";
			if (ESI_ConfigObj != null && ESI_ConfigObj.length > 0) {
				boolean empTypeBranchCheck = salModel
						.getEmpTypeBranchApplicabilityChk(typeId, branchId, 1,
								path);
				if (empTypeBranchCheck) {
					if (grossESICreditTotal <= Integer.parseInt(String
							.valueOf(ESI_ConfigObj[0][4]))) {
						esi_amt = (monthESICreditTotal
								* Double.parseDouble(String
										.valueOf(ESI_ConfigObj[0][2])) / 100);
					} else if (month
							.equals(String.valueOf(ESI_ConfigObj[0][5]))) {
						// If ESI start month and end month is equal then
						// straight away esi will be calculated.
						if (grossESICreditTotal <= Integer.parseInt(String
								.valueOf(ESI_ConfigObj[0][4]))) {
							esi_amt = (monthESICreditTotal
									* Double.parseDouble(String
											.valueOf(ESI_ConfigObj[0][2])) / 100);
						}
					} else if (grossESICreditTotal >= Integer.parseInt(String
							.valueOf(ESI_ConfigObj[0][4]))) {
						/**
						 * if not, system will check whether esi deducted on the
						 * specified month or not this method returns status of
						 * esi whether to deduct or not. It it returns "NP"
						 * means salary has not been processed for specified esi
						 * cutoff months, then condition will remain same depend
						 * on gross esi will be deducted. If it returns "CE"
						 * means esi deducted on cut off months hence esi will
						 * be deducted irrespective of gorss
						 */
						if (comLedgerCode.equals("spilt")) {
							previousEsic = salModel.getPreESICSpilt(month,
									year, emp_id, ESI_ConfigObj, comLedgerCode);
						} else {
							previousEsic = salModel.getPreESIC(month, year,
									emp_id, ESI_ConfigObj, comLedgerCode);
						}
						if (previousEsic.equals("true")) {
							esi_amt = (monthESICreditTotal
									* Double.parseDouble(String
											.valueOf(ESI_ConfigObj[0][2])) / 100);
						}
					}
				} else {
					esi_amt = 0;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getEmpESIAmt:" + e);
			esi_amt = 0;
		}
		return esi_amt;
	}

	public Object[][] getRoundOffSetting() {
		String query = "SELECT NVL(CONF_CREDIT_ROUND,0),NVL(CONF_TOTCREDIT_OPTION,0),NVL(CONF_TOTDEBIT_ROUND,0),NVL(CONF_NETPAY_ROUND,0)"
				+ " FROM HRMS_SALARY_CONF ";
		Object[][] roundOffSettingObj = getSqlModel().getSingleResult(query);
		return roundOffSettingObj;
	}

	public double roundCheck(int ch, double amount) {
		try {

			switch (ch) {
			case 0:
				return Double.parseDouble(Utility.twoDecimals(amount));

			case 1:
				return Math.round(Double.parseDouble(Utility
						.twoDecimals(amount)));

			case 2:
				return Math.floor(Double.parseDouble(Utility
						.twoDecimals(amount)));

			case 3:
				return Math.ceil(Double
						.parseDouble(Utility.twoDecimals(amount)));

			case 4:
				if (!(Math.round((amount)) % 10 == 0))
					return Double
							.parseDouble(Utility
									.twoDecimals((Math.round(amount) + (10 - (Math
											.round(amount) % 10)))));
				else
					return amount;

			default:
				return amount;
			}

		} catch (Exception e) {
			logger.error("Exception in getting roundCheck  ---------" + e);
			try {
				return Double.parseDouble(Utility.twoDecimals(amount));
			} catch (Exception e1) {
				return 0;
			}
		}
	}
	/**..
	 * Purpose : this method is used to calculate the employee's promotion
	 * @param bean
	 * @param empCode
	 * @return string
	 */
	public String processPromotionArrears(PromotionArrears bean, String empCode) {
		/**
		 * LOAD THE SALARY CONFIGURATION
		 */
		SalaryProcessModel salModel = new SalaryProcessModel();
		salModel.initiate(context, session);
		salModel.loadPayrollSetting();

		NonIndustrialSalaryModel nonModel = new NonIndustrialSalaryModel();
		nonModel.initiate(context, session);
		// loadPayrollSetting()

		String message = "";
		String fromMonth = bean.getMonthName();
		if (Integer.parseInt(fromMonth) < 10) {
			fromMonth = "0" + fromMonth;
		}
		String fromYear = bean.getEArrYear();
		String toMonth = bean.getArrToMonth();
		if (Integer.parseInt(toMonth) < 10) {
			toMonth = "0" + toMonth;
		}
		String toYear = bean.getArrToYear();
		String calculateFromYear = fromYear;
		String calculateToYear = fromYear.equals(toYear) ? "" : toYear;
		String calculateFromMonth = "0";
		String calculateToMonth = "0";
		String promYear = "";
		String promMonth = "";
		String promToYearStatus = "";
		int startMonth = Integer.parseInt(fromMonth);
		int endMonth = Integer.parseInt(toMonth);
		if (fromYear.equals(toYear)) {
			for (int i = 0; i < (Integer.parseInt(toMonth) - Integer
					.parseInt(fromMonth)) + 1; i++) {
				calculateFromMonth += "," + startMonth;
				promMonth += startMonth + ",";
				promYear += fromYear + ",";
				promToYearStatus += "false" + ",";
				startMonth++;
			}
		} else {
			// CALCULATE FROM MONTHS
			for (int i = 0; i < (13 - Integer.parseInt(fromMonth)); i++) {
				calculateFromMonth += "," + startMonth;
				promMonth += startMonth + ",";
				promYear += fromYear + ",";
				promToYearStatus += "false" + ",";
				startMonth++;
			}
			// CALCULATE TO MONTHS
			for (int i = 0; i < (endMonth); i++) {
				calculateToMonth += "," + endMonth;
				promMonth += endMonth + ",";
				promYear += toYear + ",";
				promToYearStatus += "true" + ",";
				endMonth--;
			}
		}
		if (promYear.length() > 0) {
			promYear = promYear.substring(0, promYear.length() - 1);
		}
		if (promMonth.length() > 0) {
			promMonth = promMonth.substring(0, promMonth.length() - 1);
		}
		if (promToYearStatus.length() > 0) {
			promToYearStatus = promToYearStatus.substring(0, promToYearStatus
					.length() - 1);
		}

		/**
		 * CHECK SALARY LOCK NOR NOT
		 */
		String paidYear = bean.getArrPaidYear();
		String paidMonth = bean.getArrPaidMonth();

		String salQUery = "SELECT LEDGER_STATUS FROM HRMS_SALARY_LEDGER WHERE LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH="
				+ paidMonth
				+ " AND LEDGER_YEAR="
				+ paidYear
				+ " AND "
				+ "	LEDGER_DIVISION=" + bean.getDivCode() + " ";
		if (!bean.getBrnCode().equals("")) {
			salQUery += " AND LEDGER_BRN=" + bean.getBrnCode();
		}
		Object[][] salObj = getSqlModel().getSingleResult(salQUery);
		if (bean.getPayinSalCheckBox().equals("true") && salObj != null
				&& salObj.length > 0) {
			message = "Salary already locked ?\n Please select another pay month and year";
			return message;
		}
		/**
		 * GET PRESET CREDIT CONFIGURATION
		 */
		/*
		 * String credit="SELECT EMP_ID,CREDIT_CODE,CREDIT_AMT FROM
		 * HRMS_EMP_CREDIT ORDER BY EMP_ID "; HashMap<String,
		 * Object[][]>creditMap=getSqlModel().getSingleResultMap(credit, 0, 2);
		 */

		/**
		 * GET PROMOTION SALARY
		 */
		String PROMOTION_EMP = "0";
		String PROMOTION_CODE = "0";
		Object[][] updateProm = null;
		String promotionQuery = "SELECT PROM_CODE,EMP_CODE,TO_CHAR(EFFECT_DATE,'DD-MM-YYYY') FROM HRMS_PROMOTION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PROMOTION.EMP_CODE)"
				+ " WHERE LOCK_FLAG='Y' AND ARREARS_PROCESS_STATUS='N'"
				+ "  AND EMP_DIV="
				+ bean.getDivCode()
				+ " AND  TO_DATE(TO_CHAR(EFFECT_DATE,'MM-YYYY'),'MM-YYYY')>=TO_DATE('"
				+ fromMonth
				+ "-"
				+ fromYear
				+ "','MM-YYYY') AND  TO_DATE(TO_CHAR(EFFECT_DATE,'MM-YYYY'),'MM-YYYY')<=TO_DATE('"
				+ toMonth + "-" + toYear + "','MM-YYYY') ";
		if (!bean.getBrnCode().equals("")) {
			promotionQuery += " AND EMP_CENTER=" + bean.getBrnCode();
		}
		if (!empCode.equals("")) {
			promotionQuery += " AND EMP_CODE=" + empCode;
		}
		promotionQuery += "   ORDER BY EMP_CODE,EFFECT_DATE ASC ";
		Object[][] promotionObj = getSqlModel().getSingleResult(promotionQuery);
		if (promotionObj != null && promotionObj.length > 0) {
			updateProm = new Object[promotionObj.length][2];
			for (int i = 0; i < promotionObj.length; i++) {
				PROMOTION_EMP += "," + String.valueOf(promotionObj[i][1]);
				PROMOTION_CODE += "," + String.valueOf(promotionObj[i][0]);

				updateProm[i][0] = promotionObj[i][0];
				updateProm[i][1] = promotionObj[i][1];
			}
			String promotionDistQuery = "SELECT DISTINCT EMP_CODE,NVL(EMP_CENTER,0), NVL(EMP_TYPE,0),NVL(CENTER_PTAX_STATE,15),NVL(EMP_GENDER,'A'),TO_CHAR(EFFECT_DATE,'DD-MM-YYYY') FROM HRMS_PROMOTION "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PROMOTION.EMP_CODE)"
					+ " INNER JOIN HRMS_CENTER	 ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_LOCATION	 ON(HRMS_CENTER.CENTER_PTAX_STATE = HRMS_LOCATION.LOCATION_CODE)"
					+ " WHERE LOCK_FLAG='Y' AND ARREARS_PROCESS_STATUS='N'"
					+ "  AND EMP_DIV="
					+ bean.getDivCode()
					+ " AND  TO_DATE(TO_CHAR(EFFECT_DATE,'MM-YYYY'),'MM-YYYY')>=TO_DATE('"
					+ fromMonth
					+ "-"
					+ fromYear
					+ "','MM-YYYY') AND  TO_DATE(TO_CHAR(EFFECT_DATE,'MM-YYYY'),'MM-YYYY')<=TO_DATE('"
					+ toMonth + "-" + toYear + "','MM-YYYY') ";
			if (!bean.getBrnCode().equals("")) {
				promotionDistQuery += " AND EMP_CENTER=" + bean.getBrnCode();
			}
			if (!empCode.equals("")) {
				promotionDistQuery += " AND EMP_CODE=" + empCode;
			}
			// +" ORDER BY EMP_CODE,EFFECT_DATE ASC ";
			Object[][] promotionDistObj = getSqlModel().getSingleResult(
					promotionDistQuery);

			/**
			 * GET EMPLOYEE PF APPLICABILITY
			 */

			HashMap<String, Object[]> empFlagMap = getEmpFlagData(PROMOTION_EMP);

			HashMap<String, Object[][]> promotionMap = getSqlModel()
					.getSingleResultMap(promotionQuery, 0, 2);

			String promotionDTLQuery = "SELECT PROM_CODE||'#'||SAL_CODE,NVL(OLD_AMT,0),NVL(NEW_AMT,0) FROM HRMS_PROMOTION_SALARY WHERE PROM_CODE IN("
					+ PROMOTION_CODE + ")";
			HashMap<String, Object[][]> promotionDTLMap = getSqlModel()
					.getSingleResultMap(promotionDTLQuery, 0, 2);

			/**
			 * GET EMPLOYEE SALARY DAYS
			 */
			String salaryQUery = "SELECT EMP_ID||'#'||SAL_MONTH||'#'||SAL_YEAR,NVL(SAL_DAYS,0)"
					+ " FROM HRMS_SALARY_"
					+ calculateFromYear
					+ " WHERE SAL_MONTH IN("
					+ calculateFromMonth
					+ ") AND EMP_ID IN(" + PROMOTION_EMP + ") ";
			HashMap<String, Object[][]> salaryMap = getSqlModel()
					.getSingleResultMap(salaryQUery, 0, 2);

			String salaryQUery2 = "SELECT EMP_ID||'#'||SAL_MONTH||'#'||SAL_YEAR,NVL(SAL_DAYS,0)"
					+ " FROM HRMS_SALARY_"
					+ calculateToYear
					+ " WHERE SAL_MONTH IN("
					+ calculateToMonth
					+ ") AND EMP_ID IN(" + PROMOTION_EMP + ") ";
			HashMap<String, Object[][]> salaryMap2 = getSqlModel()
					.getSingleResultMap(salaryQUery2, 0, 2);
			/**
			 * GET EMPLOYEE CREDIT SALARY
			 */
			String creditAmtQuery = "SELECT EMP_ID||'#'||SAL_MONTH||'#'||SAL_YEAR||'#'||SAL_CREDIT_CODE,NVL(SAL_AMOUNT,0),NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_TAXABLE_FLAG,'N') FROM HRMS_SAL_CREDITS_"
					+ calculateFromYear
					+ " "
					+ "  INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"
					+ calculateFromYear
					+ ".SAL_CREDIT_CODE) "
					+ " WHERE SAL_MONTH IN("
					+ calculateFromMonth
					+ ")  AND EMP_ID IN(" + PROMOTION_EMP + ") AND CREDIT_APPLICABLE_ARREARS = 'Y' ";
			HashMap<String, Object[][]> salaryCreditMap = getSqlModel()
					.getSingleResultMap(creditAmtQuery, 0, 2);

			String creditAmtQuery2 = "SELECT EMP_ID||'#'||SAL_MONTH||'#'||SAL_YEAR||'#'||SAL_CREDIT_CODE,NVL(SAL_AMOUNT,0),NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_TAXABLE_FLAG,'N')  FROM HRMS_SAL_CREDITS_"
					+ calculateToYear
					+ " "
					+ "  INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"
					+ calculateToYear
					+ ".SAL_CREDIT_CODE) "
					+ " WHERE SAL_MONTH IN("
					+ calculateToMonth
					+ ")  AND EMP_ID IN(" + PROMOTION_EMP + ") AND CREDIT_APPLICABLE_ARREARS = 'Y' ";
			HashMap<String, Object[][]> salaryCreditMap2 = null;

			// IF FROM YEAR AND TO YEAR DIFFERENT
			if (!calculateToYear.equals("")) {
				salaryCreditMap2 = getSqlModel().getSingleResultMap(
						creditAmtQuery2, 0, 2);
			}

			/**
			 * GET EMPLOYEE DEBIT SALARY
			 */
			String debitAmtQuery = "SELECT EMP_ID||'#'||SAL_MONTH||'#'||SAL_YEAR||'#'||SAL_DEBIT_CODE,NVL(SAL_AMOUNT,0) FROM HRMS_SAL_DEBITS_"
					+ calculateFromYear
					+ " "
					+ " WHERE SAL_MONTH IN("
					+ calculateFromMonth
					+ ")  AND EMP_ID IN("
					+ PROMOTION_EMP
					+ ")";
			HashMap<String, Object[][]> salaryDebittMap = getSqlModel()
					.getSingleResultMap(debitAmtQuery, 0, 2);

			String debitAmtQuery2 = "SELECT EMP_ID||'#'||SAL_MONTH||'#'||SAL_YEAR||'#'||SAL_DEBIT_CODE,NVL(SAL_AMOUNT,0) FROM HRMS_SAL_DEBITS_"
					+ calculateToYear
					+ " "
					+ " WHERE SAL_MONTH IN("
					+ calculateToMonth
					+ ")  AND EMP_ID IN("
					+ PROMOTION_EMP
					+ ")";
			HashMap<String, Object[][]> salaryDebitMap2 = null;

			// IF FROM YEAR AND TO YEAR DIFFERENT
			if (!calculateToYear.equals("")) {
				salaryDebitMap2 = getSqlModel().getSingleResultMap(
						debitAmtQuery2, 0, 2);
			}

			/**
			 * GET EMPLOYEE ARREARS INFORMATION
			 */
			String arrearsQuery = "SELECT HRMS_ARREARS_" + calculateFromYear
					+ ".EMP_ID||'#'||HRMS_ARREARS_" + calculateFromYear
					+ ".ARREARS_MONTH||'#'||HRMS_ARREARS_" + calculateFromYear
					+ ".ARREARS_YEAR,NVL(HRMS_ARREARS_" + calculateFromYear
					+ ".ARREARS_DAYS,0)"
					+ " ,NVL(ARREARS_PROMCODE,'0') FROM HRMS_ARREARS_"
					+ calculateFromYear + " " + " WHERE HRMS_ARREARS_"
					+ calculateFromYear + ".ARREARS_MONTH IN("
					+ calculateFromMonth + ")  AND HRMS_ARREARS_"
					+ calculateFromYear + ".EMP_ID IN(" + PROMOTION_EMP + ") ";
			if (!bean.getArrCode().equals("")) {
				arrearsQuery += " AND ARREARS_CODE NOT IN ("
						+ bean.getArrCode() + ")";
			}
			arrearsQuery += "  ORDER BY HRMS_ARREARS_" + calculateFromYear
					+ ".EMP_ID||'#'||HRMS_ARREARS_" + calculateFromYear
					+ ".ARREARS_MONTH||'#'||HRMS_ARREARS_" + calculateFromYear
					+ ".ARREARS_YEAR ";

			HashMap<String, Object[][]> arrearsMap = getSqlModel()
					.getSingleResultMap(arrearsQuery, 0, 2);

			String arrearsQuery2 = "SELECT EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR,NVL(ARREARS_DAYS,0) "
					+ " ,NVL(ARREARS_PROMCODE,'0') FROM HRMS_ARREARS_"
					+ calculateToYear
					+ " WHERE ARREARS_MONTH IN("
					+ calculateToMonth
					+ ")  AND EMP_ID IN("
					+ PROMOTION_EMP
					+ ")";
			if (!bean.getArrCode().equals("")) {
				arrearsQuery2 += " AND  ARREARS_CODE NOT IN ("
						+ bean.getArrCode() + ")";
			}
			arrearsQuery2 += "  ORDER BY EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR ";

			HashMap<String, Object[][]> arrearsMap2 = getSqlModel()
					.getSingleResultMap(arrearsQuery2, 0, 2);
			/*
			 * GET EMPLOYEE CREDIT ARREARS
			 */
			String creditArrAmtQuery = "SELECT ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||ARREARS_CREDIT_CODE,NVL(ARREARS_AMT,0),NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_APPLICABLE_ESI,'N') FROM HRMS_ARREARS_CREDIT_"
					+ calculateFromYear
					+ " "
					+ "  INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"
					+ calculateFromYear
					+ ".ARREARS_CREDIT_CODE) "
					+ " WHERE CREDIT_APPLICABLE_ARREARS = 'Y' AND ARREARS_MONTH IN("
					+ calculateFromMonth
					+ ") AND ARREARS_PAY_TYPE='A' AND ARREARS_EMP_ID IN("
					+ PROMOTION_EMP + ") ";
			if (!bean.getArrCode().equals("")) {
				creditArrAmtQuery += " AND ARREARS_CODE NOT IN ("
						+ bean.getArrCode() + ")";
			}
			creditArrAmtQuery += "  ORDER BY ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||ARREARS_CREDIT_CODE ";
			// ORDER BY
			HashMap<String, Object[][]> arrCreditMap = getSqlModel()
					.getSingleResultMap(creditArrAmtQuery, 0, 2);

			String creditArrAmtQuery2 = "SELECT ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||ARREARS_CREDIT_CODE,NVL(ARREARS_AMT,0),NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_APPLICABLE_ESI,'N') FROM HRMS_ARREARS_CREDIT_"
					+ calculateToYear
					+ " "
					+ "  INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"
					+ calculateToYear
					+ ".ARREARS_CREDIT_CODE) "
					+ " WHERE  CREDIT_APPLICABLE_ARREARS = 'Y' AND ARREARS_MONTH IN("
					+ calculateToMonth
					+ ") AND ARREARS_PAY_TYPE='A' AND ARREARS_EMP_ID IN("
					+ PROMOTION_EMP + ")";
			if (!bean.getArrCode().equals("")) {
				creditArrAmtQuery2 += " AND ARREARS_CODE NOT IN ("
						+ bean.getArrCode() + ")";
			}
			creditArrAmtQuery2 += "  ORDER BY ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||ARREARS_CREDIT_CODE ";

			HashMap<String, Object[][]> arrCreditMap2 = getSqlModel()
					.getSingleResultMap(creditArrAmtQuery2, 0, 2);
			/*
			 * GET EMPLOYEE DEBIT ARREARS
			 */
			String debitArrAmtQuery = "SELECT ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||ARREARS_DEBIT_CODE,NVL(ARREARS_AMT,0) FROM HRMS_ARREARS_DEBIT_"
					+ calculateFromYear
					+ " "
					+ " WHERE ARREARS_MONTH IN("
					+ calculateFromMonth
					+ ") AND ARREARS_EMP_ID IN("
					+ PROMOTION_EMP + ")";
			if (!bean.getArrCode().equals("")) {
				debitArrAmtQuery += " AND ARREARS_CODE NOT IN ("
						+ bean.getArrCode() + ")";
			}
			debitArrAmtQuery += "  ORDER BY ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||ARREARS_DEBIT_CODE ";

			HashMap<String, Object[][]> arrDebitMap = getSqlModel()
					.getSingleResultMap(debitArrAmtQuery, 0, 2);

			String debitArrAmtQuery2 = "SELECT ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||ARREARS_DEBIT_CODE,NVL(ARREARS_AMT,0) FROM HRMS_ARREARS_DEBIT_"
					+ calculateToYear
					+ " "
					+ " WHERE ARREARS_MONTH IN("
					+ calculateToMonth
					+ ") AND ARREARS_EMP_ID IN("
					+ PROMOTION_EMP + ")";
			if (!bean.getArrCode().equals("")) {
				debitArrAmtQuery2 += " AND ARREARS_CODE NOT IN ("
						+ bean.getArrCode() + ")";
			}
			debitArrAmtQuery2 += "  ORDER BY ARREARS_EMP_ID||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR||'#'||ARREARS_DEBIT_CODE ";

			HashMap<String, Object[][]> arrDebitMap2 = getSqlModel()
					.getSingleResultMap(debitArrAmtQuery2, 0, 2);

			/*
			 * GET CREDIT HEADS DETAILS
			 */
			Object[][] creditHeadObj = getCreditHead();
			/*
			 * GET DEBIT HEADS DETAILS
			 */
			Object[][] debitHeadObj = getDebitHead();

			String delArrearCode = bean.getArrCode();
			String insertArrCode = bean.getArrCode();
			if (delArrearCode.equals(""))
				delArrearCode = "0";
			// if true new record is inserted in the tables
			if (insertArrCode.equals("")) {
				String arrcodeQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 from HRMS_ARREARS_LEDGER";
				Object[][] code = getSqlModel().getSingleResult(arrcodeQuery);
				insertArrCode = String.valueOf(code[0][0]);
				bean.setArrCode(insertArrCode);
			}

			Object[][] salaryFilters = getPayrollConfig();
			int creditRound = 0;
			int creditTotalRound = 0;
			int debitTotalRound = 0;
			int netPayRound = 0;
			String companyEPFFlag = "N";
			try {
				creditRound = Integer.parseInt(String
						.valueOf(salaryFilters[0][0]));
				creditTotalRound = Integer.parseInt(String
						.valueOf(salaryFilters[0][1]));
				debitTotalRound = Integer.parseInt(String
						.valueOf(salaryFilters[0][2]));
				netPayRound = Integer.parseInt(String
						.valueOf(salaryFilters[0][3]));

				companyEPFFlag = (String.valueOf(salaryFilters[0][5]));
			} catch (Exception e) {
				// TODO: handle exception
			}

			/**
			 * PROMOTION CALCULATION
			 */
			// String calculateFromYear=fromYear;
			// String calculateToYear=fromYear.equals(toYear)?"":toYear;
			// String calculateFromMonth="0";
			// String calculateToMonth="0";
			String[] ARR_MONTH = promMonth.split(",");
			String[] ARR_YEAR = promYear.split(",");
			String[] ARR_TOYEAR_STATUS = promToYearStatus.split(",");
			EmployeeTaxCalculation taxModel = new EmployeeTaxCalculation();
			taxModel.initiate(context, session);
			HashMap<String, String> empPromMap = new HashMap<String, String>();
			if (promotionObj != null && promotionObj.length > 0) {
				for (int i = 0; i < promotionObj.length; i++) {
					String effectiveDate = String.valueOf(promotionObj[i][2]);
					String[] str = effectiveDate.split("-");
					String effectiveMonth = String.valueOf(Integer
							.parseInt(str[1]));
					String effectiveYear = str[2];
					if (ARR_MONTH != null && ARR_MONTH.length > 0) {
						for (int j = 0; j < ARR_MONTH.length; j++) {
							String key = String.valueOf(promotionObj[i][1])
									+ "#" + ARR_MONTH[j];
							String values = "0";
							if (Integer.parseInt(ARR_YEAR[j] + ARR_MONTH[j]) >= Integer
									.parseInt(effectiveYear + effectiveMonth)) {
								values = String.valueOf(promotionObj[i][0]);
								empPromMap.put(key, values);
							}
						}
					}
				}
			}

			int DAY_OF_MONTH = 30;
			double DAY_OF_SALARY = DAY_OF_MONTH;
			double DAY_OF_ARREAR = 0;
			double DAY_OF_PROMOTION = DAY_OF_MONTH;
			String SUPPRESSION_DEBIT = bean.getSuppressDebitCode();
			String[] suppress_debit = SUPPRESSION_DEBIT.split(",");
			int COUNT = 0;
			int ARR_COUNT = 0;
			int DEBIT_COUNT = 0;
			int deleteCount = 0;
			String TDS_DEBIT_CODE = "";
			int debitRound = 0;
			String query = "  SELECT NVL(TDS_DEBIT_CODE,0) from HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM = "
					+ String.valueOf(Integer.parseInt(paidYear))
					+ " AND TDS_FINANCIALYEAR_TO = "
					+ String.valueOf(Integer.parseInt(paidYear) + 1);
			Object[][] tdsDebitCodeObj = getSqlModel().getSingleResult(query);
			if (tdsDebitCodeObj != null && tdsDebitCodeObj.length > 0) {
				TDS_DEBIT_CODE = String.valueOf(tdsDebitCodeObj[0][0]);
			}
			Object[][] finalObj = new Object[promotionDistObj.length
					* (ARR_MONTH.length) * creditHeadObj.length][7];
			Object[][] insertDebitObj = new Object[promotionDistObj.length
					* (ARR_MONTH.length) * debitHeadObj.length][7];
			Object[][] insertArrObj = new Object[promotionDistObj.length
					* (ARR_MONTH.length)][9];
			Object[][] deleteObj = new Object[promotionDistObj.length
					* (ARR_MONTH.length)][3];
			HashMap<String, Object[][]> promCreditAmtMap = new HashMap<String, Object[][]>();
			HashMap<String, Object[][]> promCreditAmtMap_PF = new HashMap<String, Object[][]>();
			for (int j = 0; j < promotionDistObj.length; j++) {
				 String effectiveDate=String.valueOf(promotionDistObj[j][5]);
				 String[]str=effectiveDate.split("-");
				 String effectMonth=str[1];
				 String effectYear=str[2];
				 String effectDay=str[0];
				for (int i = 0; i < ARR_MONTH.length; i++) {

					DAY_OF_MONTH = 30;
					DAY_OF_SALARY = 0;
					DAY_OF_ARREAR = 0;
					DAY_OF_PROMOTION = DAY_OF_MONTH;

					deleteObj[deleteCount][0] = promotionDistObj[j][0];
					deleteObj[deleteCount][1] = ARR_MONTH[i];
					deleteObj[deleteCount][2] = ARR_YEAR[i];
					deleteCount++;
					double totalCreditAmt = 0.0;
					double totalDebitAmt = 0.0;
					double totalNetAmt = 0.0;
					double ptaxTotalCredit = 0;
					double grossESICreditTotal = 0.0;
					double esicTotalCredit = 0.0;
					double totalTaxCreditAmt = 0.0;
					/**
					 * KEY FOR PROMOTION CODE
					 */
					String promKey = String.valueOf(promotionDistObj[j][0])
							+ "#" + ARR_MONTH[i];
					String PROM_CODE = empPromMap.get(promKey);
					// if(!ARR_MONTH[i].equals("0") &&
					// Integer.parseInt(ARR_MONTH[i])<Integer.parseInt(effectiveMonth)){
					if (!ARR_MONTH[i].equals("0") && PROM_CODE == null) {
						insertArrObj[ARR_COUNT][0] = String
								.valueOf(promotionDistObj[j][0]);// EMP_ID
						insertArrObj[ARR_COUNT][1] = "0";// ARREARS_MONTH
						insertArrObj[ARR_COUNT][2] = fromYear;// ARREARS_YEAR
						insertArrObj[ARR_COUNT][3] = DAY_OF_PROMOTION;// ARREARS_DAYS
						insertArrObj[ARR_COUNT][4] = totalNetAmt;// ARREARS_NET_AMT
						insertArrObj[ARR_COUNT][5] = totalCreditAmt;// ARREARS_CREDITS_AMT
						insertArrObj[ARR_COUNT][6] = totalDebitAmt;// ARREARS_DEBITS_AMT
						insertArrObj[ARR_COUNT][7] = "N";// ARREARS_ONHOLD
						insertArrObj[ARR_COUNT][8] = "0";
						;// PROM_CODE
						ARR_COUNT++;

						for (int k = 0; k < creditHeadObj.length; k++) {
							finalObj[COUNT][0] = insertArrCode;// ARREARS_CODE
							finalObj[COUNT][1] = String
									.valueOf(promotionDistObj[j][0]);// ARREARS_EMP_ID
							finalObj[COUNT][2] = String
									.valueOf(creditHeadObj[k][0]);// ARREARS_CREDIT_CODE
							finalObj[COUNT][3] = ARR_MONTH[i];// ARREARS_MONTH
							finalObj[COUNT][4] = ARR_YEAR[i];// ARREARS_YEAR
							finalObj[COUNT][5] = "0";// ARREARS_AMT
							finalObj[COUNT][6] = "0";// PROM_CODE
							COUNT++;
						}
						for (int k = 0; k < debitHeadObj.length; k++) {
							insertDebitObj[DEBIT_COUNT][0] = insertArrCode;// ARREARS_CODE
							insertDebitObj[DEBIT_COUNT][1] = String
									.valueOf(promotionDistObj[j][0]);// ARREARS_EMP_ID
							insertDebitObj[DEBIT_COUNT][2] = String
									.valueOf(debitHeadObj[k][0]);// ARREARS_CREDIT_CODE
							insertDebitObj[DEBIT_COUNT][3] = ARR_MONTH[i];// ARREARS_MONTH
							insertDebitObj[DEBIT_COUNT][4] = ARR_YEAR[i];// ARREARS_YEAR
							insertDebitObj[DEBIT_COUNT][5] = 0;// ARREARS_AMT
							insertDebitObj[DEBIT_COUNT][6] = "0";// PROM_CODE
							DEBIT_COUNT++;
						}

					} else if (!ARR_MONTH[i].equals("0")) {
						try {
							// KEY=EMPCODE+MONTH+YEAR+CRCODE
							String date = "01-" + ARR_MONTH[i] + "-"
									+ ARR_YEAR[i];
							java.text.DateFormat df = new java.text.SimpleDateFormat(
									"dd-MM-yyyy");
							java.util.Date dt = df.parse(date);
							java.util.Calendar cal = java.util.Calendar
									.getInstance();
							cal.setTime(dt);
							DAY_OF_MONTH = cal
									.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
						} catch (Exception e) {
							// TODO: handle exception
						}
						String KEY = String.valueOf(promotionDistObj[j][0])
								+ "#" + ARR_MONTH[i] + "#" + ARR_YEAR[i];
						// GET EMPLOYEE SALARY DAYS
						if (salaryMap != null && salaryMap.size() > 0) {
							Object[][] obj = salaryMap.get(KEY);
							if (String.valueOf(ARR_TOYEAR_STATUS[i]).equals(
									"true")) {
								obj = salaryMap2.get(KEY);
							}
							if (obj != null && obj.length > 0) {
								DAY_OF_SALARY = Double.parseDouble(String
										.valueOf(String.valueOf(obj[0][1])));
							}
						}
						// GET EMPLOYEE ARREAR DAYS
						if (arrearsMap != null && arrearsMap.size() > 0) {
							Object[][] obj = arrearsMap.get(KEY);
							if (String.valueOf(ARR_TOYEAR_STATUS[i]).equals(
									"true")) {
								obj = arrearsMap2.get(KEY);
							}
							if (obj != null && obj.length > 0) {
								for (int k = 0; k < obj.length; k++) {
									String arrDay = String.valueOf(obj[k][2]);
									System.out.println("arrDay::" + arrDay);
									if (arrDay.equals("0")) {
										DAY_OF_ARREAR = Double
												.parseDouble(String
														.valueOf(String
																.valueOf(obj[k][1])));
									}
								}

							}
						}

						if (creditHeadObj != null && creditHeadObj.length > 0) {
							Object[][] promObj = new Object[creditHeadObj.length][2];
							Object[][] promObj_PF = new Object[creditHeadObj.length][2];
							for (int k = 0; k < creditHeadObj.length; k++) {
								double proCreditAmt = 0.0;
								double proCredit = 0.0;
								double salaryCreditAmt = 0.0;
								double arrCreditAmt = 0.0;
								double newCreditAmt = 0.0;
								KEY = String.valueOf(promotionDistObj[j][0])
										+ "#" + ARR_MONTH[i] + "#"
										+ ARR_YEAR[i] + "#"
										+ String.valueOf(creditHeadObj[k][0]);
								// PROMOTION KEY=PROMOTIOE+CRECODE
								// GET CREDIT AMT FROM PROMOTION TABLE
								double TOTAL_SAL_ARR = DAY_OF_SALARY
										+ DAY_OF_ARREAR;
								if (TOTAL_SAL_ARR > 0) {
									if (promotionDTLMap != null
											&& promotionDTLMap.size() > 0) {
										Object[][] proObj = promotionDTLMap
												.get(String.valueOf(PROM_CODE)
														+ "#"
														+ String
																.valueOf(creditHeadObj[k][0]));
										if (proObj != null && proObj.length > 0) {
											proCreditAmt = Double
													.parseDouble(String
															.valueOf(proObj[0][2]));
											proCredit = Double
													.parseDouble(String
															.valueOf(proObj[0][2]));
										}
									}
									// GET CREDIT AMT FROM SALARY
									if (salaryCreditMap != null
											&& salaryCreditMap.size() > 0) {
										Object[][] proObj = salaryCreditMap
												.get(KEY);
										if (String
												.valueOf(ARR_TOYEAR_STATUS[i])
												.equals("true")) {
											proObj = salaryCreditMap2.get(KEY);
										}
										if (proObj != null && proObj.length > 0) {
											salaryCreditAmt = Double
													.parseDouble(String
															.valueOf(proObj[0][1]));
											if (String.valueOf(proObj[0][2])
													.equals("Y")) {
												ptaxTotalCredit += salaryCreditAmt;
											}
											if (String.valueOf(proObj[0][3])
													.equals("Y")
													&& DAY_OF_SALARY > 0) {
												grossESICreditTotal += salaryCreditAmt;
												// esicTotalCredit+=((salaryCreditAmt/DAY_OF_SALARY)*DAY_OF_MONTH);
											}
										}
									}
									// GET CREDIT AMT FROM ARREARS
									if (arrCreditMap != null
											&& arrCreditMap.size() > 0) {
										Object[][] proObj = arrCreditMap
												.get(KEY);
										if (String
												.valueOf(ARR_TOYEAR_STATUS[i])
												.equals("true")) {
											proObj = arrCreditMap2.get(KEY);
										}
										if (proObj != null && proObj.length > 0) {

											for (int l = 0; l < proObj.length; l++) {
												arrCreditAmt += Double
														.parseDouble(String
																.valueOf(proObj[l][1]));
											}
											if (String.valueOf(proObj[0][2])
													.equals("Y")) {
												ptaxTotalCredit += arrCreditAmt;
											}
											if (String.valueOf(proObj[0][3])
													.equals("Y")
													&& DAY_OF_ARREAR > 0) {
												grossESICreditTotal += arrCreditAmt;
												// esicTotalCredit+=((arrCreditAmt/DAY_OF_ARREAR)*DAY_OF_MONTH);
											}
										}
									}

								}

								/**
								 * FOR FIXED CREDIT TYPE
								 */
								if (String.valueOf(creditHeadObj[k][3]).equals(
										"2")) {
									
									newCreditAmt = (proCreditAmt - (salaryCreditAmt + arrCreditAmt));
									newCreditAmt = roundCheck(creditRound,
											newCreditAmt);
									
									
								} else {
									if(Integer.parseInt(ARR_MONTH[i])==(Integer.parseInt(effectMonth))&& Integer.parseInt(ARR_YEAR[i])==(Integer.parseInt(effectYear))&& !effectDay.equals("01")){
										double promDays=DAY_OF_MONTH-Integer.parseInt(effectDay)+1;
										double oldDays=DAY_OF_SALARY-promDays;
										proCreditAmt=	(proCreditAmt*promDays/DAY_OF_MONTH)+(oldDays*(salaryCreditAmt+arrCreditAmt)/DAY_OF_SALARY);
									
										salaryCreditAmt = Double
										.parseDouble(formatter
												.format(salaryCreditAmt
														+ arrCreditAmt));
									newCreditAmt = (proCreditAmt - (salaryCreditAmt));
									try {
										newCreditAmt = roundCheck(creditRound,
												newCreditAmt);
									} catch (Exception e) {
										// TODO: handle exception
									}
										
									}else{
									proCreditAmt = ((proCreditAmt * (DAY_OF_SALARY + DAY_OF_ARREAR)) / DAY_OF_MONTH);// PROMOTION
																														// AMOUNT
									// proCreditAmt=roundCheck(creditRound,
									// proCreditAmt);
									proCreditAmt = Double.parseDouble(formatter
											.format(proCreditAmt));// SALARY
																	// AMOUNT
									salaryCreditAmt = Double
											.parseDouble(formatter
													.format(salaryCreditAmt
															+ arrCreditAmt));
									newCreditAmt = (proCreditAmt - (salaryCreditAmt));
									newCreditAmt = roundCheck(creditRound,
											newCreditAmt);
									}
									
								}
								/**
								 * FOR SUPPRESION CALCULATION
								 */
								if (bean.getSuppressCreditFlag().equals(
										"true")) {
									if (newCreditAmt < 0) {
										newCreditAmt = 0;
									}
								}
								totalCreditAmt = totalCreditAmt
										+ newCreditAmt;
								if (String.valueOf(creditHeadObj[k][2]).equals(
										"Y")) {
									totalTaxCreditAmt += newCreditAmt;
								}
								if (String.valueOf(creditHeadObj[k][4]).equals(
										"Y")) {
									esicTotalCredit += newCreditAmt;
								}
								if (String.valueOf(creditHeadObj[k][5]).equals(
										"Y")) {
									ptaxTotalCredit += newCreditAmt;
								}
								// creditRound
								finalObj[COUNT][0] = insertArrCode;// ARREARS_CODE
								finalObj[COUNT][1] = String
										.valueOf(promotionDistObj[j][0]);// ARREARS_EMP_ID
								finalObj[COUNT][2] = String
										.valueOf(creditHeadObj[k][0]);// ARREARS_CREDIT_CODE
								finalObj[COUNT][3] = ARR_MONTH[i];// ARREARS_MONTH
								finalObj[COUNT][4] = ARR_YEAR[i];// ARREARS_YEAR
								finalObj[COUNT][5] = formatter
										.format(newCreditAmt);// ARREARS_AMT
								finalObj[COUNT][6] = PROM_CODE;// PROM_CODE
								COUNT++;

								promObj[k][0] = String
										.valueOf(creditHeadObj[k][0]);

								promObj[k][1] = proCredit;

								promObj_PF[k][0] = String
										.valueOf(creditHeadObj[k][0]);
								proCredit = (proCredit / DAY_OF_MONTH)
										* TOTAL_SAL_ARR;
								promObj_PF[k][1] = proCredit;

							}// END FOR LOOP CREDIT

							// SET PROMOTION CREDIT AMOUNT INTO MAP FOR EPF AND
							// PTAX CALCULATION
							// KEY=EMPCODE_MONTH
							promCreditAmtMap.put(String
									.valueOf(promotionDistObj[j][0])
									+ "#" + String.valueOf(ARR_MONTH[i]),
									promObj);

							promCreditAmtMap_PF.put(String
									.valueOf(promotionDistObj[j][0])
									+ "#" + String.valueOf(ARR_MONTH[i]),
									promObj_PF);

						}// END IF CREDIT LOOP

						/*
						 * DEBIT CALCULATION
						 */
						String month = ARR_MONTH[i];
						String year = ARR_YEAR[i];
						String debitEffectDate = DAY_OF_MONTH + "-" + month
								+ "-" + year;
						String ptCode = getPTCode(bean, debitEffectDate);
						String pfCode = getPFCode(bean, debitEffectDate);
						String esiCode = getESICode(bean, debitEffectDate);

						Object[][] pf_data = salModel.getPFData("", String
								.valueOf(ARR_MONTH[i]), String
								.valueOf(ARR_YEAR[i]));

						if (debitHeadObj != null && debitHeadObj.length > 0) {
							for (int k = 0; k < debitHeadObj.length; k++) {
								double proDebitAmt = 0.0;
								double salaryDebitAmt = 0.0;
								double salaryPromDebitAmt = 0.0;
								double arrDebitAmt = 0.0;
								double newDebitAmt = 0.0;
								double pfAmt = 0.0;
								double pfAmt_PF = 0.0;
								double ptAmt = 0.0;
								double esiAmt = 0.0;
								debitRound = 0;
								debitRound = Integer.parseInt(String
										.valueOf(debitHeadObj[k][2]));
								String debitCode = String
										.valueOf(debitHeadObj[k][0]);
								String typeCode = String
										.valueOf(promotionDistObj[j][2]);
								String branchCode = String
										.valueOf(promotionDistObj[j][1]);
								String locationCode = String
										.valueOf(promotionDistObj[j][3]);

								KEY = String.valueOf(promotionDistObj[j][0])
										+ "#" + ARR_MONTH[i] + "#"
										+ ARR_YEAR[i] + "#"
										+ String.valueOf(debitHeadObj[k][0]);
								// PROMOTION KEY=PROMOTIOE+CRECODE
								// GET CREDIT AMT FROM PROMOTION TABLE
								/*
								 * if(promotionDTLMap!=null &&
								 * promotionDTLMap.size()>0){
								 * Object[][]proObj=promotionDTLMap.get(String.valueOf(PROM_CODE)+"#"+String.valueOf(debitHeadObj[k][0]));
								 * if(proObj!=null && proObj.length>0){
								 * proDebitAmt=Double.parseDouble(String.valueOf(proObj[0][2])); } }
								 */

								// GET CREDIT AMT FROM SALARY
								if (salaryDebittMap != null
										&& salaryDebittMap.size() > 0) {
									Object[][] proObj = salaryDebittMap
											.get(KEY);
									if (String.valueOf(ARR_TOYEAR_STATUS[i])
											.equals("true")) {
										proObj = salaryDebitMap2.get(KEY);
									}
									if (proObj != null && proObj.length > 0) {
										salaryDebitAmt = Double
												.parseDouble(String
														.valueOf(proObj[0][1]));
									}
								}
								// GET CREDIT AMT FROM ARREARS
								if (arrDebitMap != null
										&& arrDebitMap.size() > 0) {
									Object[][] proObj = arrDebitMap.get(KEY);
									if (String.valueOf(ARR_TOYEAR_STATUS[i])
											.equals("true")) {
										proObj = arrDebitMap2.get(KEY);
									}
									if (proObj != null && proObj.length > 0) {
										arrDebitAmt = Double.parseDouble(String
												.valueOf(proObj[0][1]));
									}
								}
								salaryDebitAmt = salaryDebitAmt + arrDebitAmt;
								Object[] flagObj = null;
								if (empFlagMap != null && empFlagMap.size() > 0) {
									flagObj = empFlagMap.get(String
											.valueOf(promotionDistObj[j][0]));
								}
								/**
								 * IF SALARY DAYS GREATER THAN ZERO
								 */
								double TOTAL_SAL_ARR = DAY_OF_SALARY
										+ DAY_OF_ARREAR;
								if (TOTAL_SAL_ARR > 0) {

									/**
									 * TDS CALCULATION
									 */
									if (TDS_DEBIT_CODE.equals(debitCode)
											&& bean.getDeductTaxCheckBox()
													.equals("true")) {
										String gender = String
												.valueOf(promotionDistObj[j][4]);
										Object[][] taxCalculateObj = new Object[1][3];
										taxCalculateObj[0][0] = String
												.valueOf(promotionDistObj[j][0]);// EMP_ID
										taxCalculateObj[0][1] = totalTaxCreditAmt;// AMOUNT
										taxCalculateObj[0][2] = gender;// GENDER
										final Object[][] insertTaxObj = taxModel
												.getEmpSlabTaxAmount(
														taxCalculateObj,
														paidYear,
														String
																.valueOf(Integer
																		.parseInt(paidYear) + 1));
										if (insertTaxObj != null
												&& insertTaxObj.length > 0) {
											salaryDebitAmt = Double
													.parseDouble(String
															.valueOf(insertTaxObj[0][2]));

											if (suppress_debit != null
													&& suppress_debit.length > 0) {
												for (int d = 0; d < suppress_debit.length; d++) {
													if (String.valueOf(
															suppress_debit[d])
															.equals(debitCode)) {
														if (salaryDebitAmt < 0) {
															salaryDebitAmt = 0;
															break;
														}
													}
												}
											}
											salaryPromDebitAmt = salaryDebitAmt;
										}
									}

									/**
									 * EPF DEBIT CALCULATION
									 */
									else if (debitCode.equals(pfCode)) {

										if (flagObj != null
												&& flagObj.length > 0
												&& String.valueOf(flagObj[0])
														.equals("Y")) { // check
																		// EPF
																		// applicability
											try {
												if (companyEPFFlag.equals("Y")
														&& pf_data != null
														&& pf_data.length > 0) {
													String[] dataString = new String[3];
													dataString[0] = typeCode;
													dataString[1] = branchCode;
													dataString[2] = "";
													/*
													 * GET EMPLOYEE PROM CREDIT
													 * VALUES FOR EMP CALCLATION
													 * KEY=EMPCODE+MONTH
													 */
													Object[][] creditObj = promCreditAmtMap
															.get(String
																	.valueOf(promotionDistObj[j][0])
																	+ "#"
																	+ String
																			.valueOf(ARR_MONTH[i]));

													Object[][] creditObj_PF = promCreditAmtMap_PF
															.get(String
																	.valueOf(promotionDistObj[j][0])
																	+ "#"
																	+ String
																			.valueOf(ARR_MONTH[i]));

													if (creditObj != null
															&& creditObj.length > 0) {
														pfAmt = salModel
																.getEmpPFAmt(
																		creditObj,
																		creditObj_PF,
																		pf_data,
																		dataString);
													}
													// pfAmt=(pfAmt/DAY_OF_MONTH)*TOTAL_SAL_ARR;
													// boolean
													// minPFEmolumentCheck =
													// salModel.getEmpTypeMinAmtChkCondition(typeCode,
													// 4, "");
													double maxPf_amount = ((Double
															.parseDouble(String
																	.valueOf(pf_data[0][7])) * Double
															.parseDouble(String
																	.valueOf(pf_data[0][2]))) / 100);
													if (String.valueOf(
															pf_data[0][6])
															.equals("Y")) {
														if (maxPf_amount <= (pfAmt)) {
															salaryDebitAmt = maxPf_amount
																	- salaryDebitAmt;

														} else {
															salaryDebitAmt = pfAmt
																	- salaryDebitAmt;
														}
													} else {
														salaryDebitAmt = pfAmt
																- salaryDebitAmt;
													}

													/**
													 * SUPPRESION DEBIT
													 * CALCULATION
													 */
													if (suppress_debit != null
															&& suppress_debit.length > 0) {
														for (int d = 0; d < suppress_debit.length; d++) {
															if (String
																	.valueOf(
																			suppress_debit[d])
																	.equals(
																			pfCode)) {
																if (salaryDebitAmt < 0) {
																	salaryDebitAmt = 0;
																	break;
																}
															}
														}
													}

													salaryPromDebitAmt = salaryDebitAmt;

												}
											} catch (Exception e) {
												logger
														.error("Exception in getMonthlyEmpDebit -- epfCalculation :"
																+ e);
												pfAmt = 0;
											}
										}

									} else if (debitCode.equals(ptCode)) {
										/**
										 * PTAX CALCULATION
										 */
										if (flagObj != null
												&& flagObj.length > 0
												&& String.valueOf(flagObj[1])
														.equals("Y")) { // check
																		// PTAX
																		// applicability
											ptAmt = calPTAmt(bean, typeCode,
													branchCode,
													ptaxTotalCredit,
													"" + month, locationCode);

										} else {
											ptAmt = 0;
										}
										if (ptAmt > 0) {
											salaryDebitAmt = ptAmt
													- salaryDebitAmt;
										} else {
											salaryDebitAmt = ptAmt;
										}
										/**
										 * SUPPRESION DEBIT CALCULATION
										 */
										if (suppress_debit != null
												&& suppress_debit.length > 0) {
											for (int d = 0; d < suppress_debit.length; d++) {
												if (String.valueOf(
														suppress_debit[d])
														.equals(ptCode)) {
													if (salaryDebitAmt < 0) {
														salaryDebitAmt = 0;
														break;
													}
												}
											}
										}
										salaryPromDebitAmt = salaryDebitAmt;
									} else if (debitCode.equals(esiCode)) {
										if (salaryDebitAmt > 0.0) {
											Object[][] esi_data = salModel
													.getESIData(
															"",
															String
																	.valueOf(month),
															String
																	.valueOf(year));
											// if(divEsicZone.equals("Y")){

											if (esi_data != null
													&& esi_data.length > 0) {
												try {
													String[] dataString = new String[6];
													dataString[0] = typeCode;
													dataString[1] = branchCode;
													dataString[2] = "";
													dataString[3] = String
															.valueOf(month);
													dataString[4] = String
															.valueOf(year);
													dataString[5] = String
															.valueOf(promotionDistObj[j][0]);
													String comLedgerCode = nonModel
															.prevLedger(
																	dataString[3],
																	dataString[4],
																	esi_data);
													esiAmt = getEmpESIAmt(
															esi_data,
															dataString,
															grossESICreditTotal,
															esicTotalCredit,
															comLedgerCode,
															salModel);
												} catch (Exception e) {
													logger
															.error("Exception in getMonthlyEmpDebit -- esiAmt :"
																	+ e);
												}
											}

											salaryDebitAmt = esiAmt;
											/**
											 * SUPPRESION DEBIT CALCULATION
											 */
											if (suppress_debit != null
													&& suppress_debit.length > 0) {
												for (int d = 0; d < suppress_debit.length; d++) {
													if (String.valueOf(
															suppress_debit[d])
															.equals(esiCode)) {
														if (salaryDebitAmt < 0) {
															salaryDebitAmt = 0;
															break;
														}
													}
												}
											}
											salaryPromDebitAmt = salaryDebitAmt;
										}
									}

								} else {
									salaryPromDebitAmt = 0.0;
								}

								salaryPromDebitAmt = roundCheck(debitRound,
										salaryPromDebitAmt);
								totalDebitAmt = totalDebitAmt
										+ salaryPromDebitAmt;

								insertDebitObj[DEBIT_COUNT][0] = insertArrCode;// ARREARS_CODE
								insertDebitObj[DEBIT_COUNT][1] = String
										.valueOf(promotionDistObj[j][0]);// ARREARS_EMP_ID
								insertDebitObj[DEBIT_COUNT][2] = String
										.valueOf(debitHeadObj[k][0]);// ARREARS_CREDIT_CODE
								insertDebitObj[DEBIT_COUNT][3] = ARR_MONTH[i];// ARREARS_MONTH
								insertDebitObj[DEBIT_COUNT][4] = ARR_YEAR[i];// ARREARS_YEAR
								insertDebitObj[DEBIT_COUNT][5] = formatter
										.format(salaryPromDebitAmt);// ARREARS_AMT
								insertDebitObj[DEBIT_COUNT][6] = PROM_CODE;// PROM_CODE
								DEBIT_COUNT++;
							}// END FOR LOOP CREDIT
						}// END IF CREDIT LOOP

						DAY_OF_PROMOTION = DAY_OF_SALARY + DAY_OF_ARREAR;
						if (DAY_OF_PROMOTION > DAY_OF_MONTH) {
							DAY_OF_PROMOTION = DAY_OF_MONTH;
						}
						totalCreditAmt = roundCheck(creditTotalRound,
								totalCreditAmt);
						totalDebitAmt = roundCheck(debitTotalRound,
								totalDebitAmt);
						totalNetAmt = totalCreditAmt - totalDebitAmt;
						totalNetAmt = roundCheck(netPayRound, totalNetAmt);

						insertArrObj[ARR_COUNT][0] = String
								.valueOf(promotionDistObj[j][0]);// EMP_ID
						insertArrObj[ARR_COUNT][1] = ARR_MONTH[i];// ARREARS_MONTH
						insertArrObj[ARR_COUNT][2] = ARR_YEAR[i];// ARREARS_YEAR
						insertArrObj[ARR_COUNT][3] = DAY_OF_PROMOTION;// ARREARS_DAYS
						insertArrObj[ARR_COUNT][4] = formatter
								.format(totalNetAmt);// ARREARS_NET_AMT
						insertArrObj[ARR_COUNT][5] = formatter
								.format(totalCreditAmt);// ARREARS_CREDITS_AMT
						insertArrObj[ARR_COUNT][6] = formatter
								.format(totalDebitAmt);// ARREARS_DEBITS_AMT
						insertArrObj[ARR_COUNT][7] = "N";// ARREARS_ONHOLD
						insertArrObj[ARR_COUNT][8] = PROM_CODE;// PROM_CODE
						ARR_COUNT++;

					}// END IF MONTH

				}// END MONTH FOR LOOP
			}// END PROMOTION FOR LOOP

			/**
			 * INSERT DATA
			 */
			insertPromotionArrears(bean, finalObj, deleteObj, insertArrObj,
					insertDebitObj);

			/**
			 * UPDATE PROMOTION
			 */
			if (updateProm != null && updateProm.length > 0) {
				String updateQuery = "UPDATE HRMS_PROMOTION SET ARREARS_PROCESS_STATUS='Y' WHERE PROM_CODE=? AND EMP_CODE=?";
				getSqlModel().singleExecute(updateQuery, updateProm);

			}
		} else {
			message = "Record not found";
		}
		String arrCode = bean.getArrCode();
		String selQuery = "SELECT DISTINCT EMP_ID FROM HRMS_ARREARS_"
				+ bean.getArrPaidYear() + " WHERE ARREARS_CODE=" + arrCode;
		Object[][] sumObj = getSqlModel().getSingleResult(selQuery);
		if (sumObj != null && sumObj.length > 0) {
			bean.setTotalEmployee(String.valueOf(sumObj.length));
		}
		String selQuery1 = "SELECT   NVL(SUM(ARREARS_NET_AMT),0)  FROM HRMS_ARREARS_"
				+ bean.getArrPaidYear() + " WHERE ARREARS_CODE=" + arrCode;
		Object[][] sumObj1 = getSqlModel().getSingleResult(selQuery1);
		if (sumObj1 != null && sumObj1.length > 0) {
			bean.setTotalNetAmount(formatter.format(Double.parseDouble(String
					.valueOf(sumObj1[0][0]))));
		}
		// String promotionQuery="";
		return message;
	}

	/**
	 * METHOD TO CALCULATE PROMOTION ARREARS
	 * 
	 * @param bean
	 * @return
	 */
	public boolean insertPromotionArrears(PromotionArrears bean,
			Object[][] finalObj, Object[][] deleteObj, Object[][] insertArrObj,
			Object[][] insertDebitObj) {
		boolean result = false;
		/**
		 * INSERT INTO TABLES
		 */
		String delArrearCode = bean.getArrCode();
		String insertArrCode = bean.getArrCode();
		if (delArrearCode.equals(""))
			delArrearCode = "0";

		String ledgerInsertQuery = " INSERT INTO HRMS_ARREARS_LEDGER(ARREARS_CODE,ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_STATUS,"
				+ " ARREARS_PROCESS_DATE,ARREARS_DIVISION,ARREARS_BRANCH,ARREARS_DEPARTMENT,ARREARS_EMPTYPE,ARREARS_PAYBILL,ARREARS_TYPE,ARREARS_PAY_IN_SAL,ARREARS_PAID_MONTH, ARREARS_PAID_YEAR,ARREARS_REF_TO_MONTH, ARREARS_REF_TO_YEAR,ARREARS_DEDUCT_TAX,SUPPRESSED_CREDIT_FLAG,SUPPRESSED_DEBIT_CODE)"
				+ " VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[][] ledgerObj = new Object[1][18];
		// object for inserting into hrms_arrears_ledger table
		ledgerObj[0][0] = insertArrCode;
		ledgerObj[0][1] = bean.getMonthName();
		ledgerObj[0][2] = bean.getEArrYear();
		ledgerObj[0][3] = "P";
		ledgerObj[0][4] = bean.getDivCode();
		ledgerObj[0][5] = bean.getBrnCode();
		ledgerObj[0][6] = bean.getDeptCode();
		ledgerObj[0][7] = bean.getTypeCode();
		ledgerObj[0][8] = bean.getPayBillNo();
		ledgerObj[0][9] = "P";
		ledgerObj[0][10] = "N";
		if (bean.getPayinSalCheckBox().equals("true")) {
			ledgerObj[0][10] = "Y";
		}
		ledgerObj[0][11] = bean.getArrPaidMonth();
		ledgerObj[0][12] = bean.getArrPaidYear();
		ledgerObj[0][13] = bean.getArrToMonth();
		ledgerObj[0][14] = bean.getArrToYear();
		ledgerObj[0][15] = "N";
		if (bean.getDeductTaxCheckBox().equals("true")) {
			ledgerObj[0][15] = "Y";
		}
		ledgerObj[0][16] = "N";
		if (bean.getSuppressCreditFlag().equals("true")) {
			ledgerObj[0][16] = "Y";
		}
		ledgerObj[0][17] = bean.getSuppressDebitCode();

		boolean result1 = false;
		result1 = getSqlModel().singleExecute(
				"DELETE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_CODE="
						+ insertArrCode);
		result1 = getSqlModel().singleExecute(ledgerInsertQuery, ledgerObj);

		// query for arrears_credit table
		String deleteCreditQuery = "DELETE FROM HRMS_ARREARS_CREDIT_"
				+ bean.getArrPaidYear()
				+ " WHERE ARREARS_EMP_ID=?"
				+ "	AND ARREARS_MONTH=? AND ARREARS_YEAR=? AND ARREARS_PAY_TYPE='A' AND ARREARS_CODE="
				+ insertArrCode + " ";

		String deleteDebitQuery = "DELETE FROM HRMS_ARREARS_DEBIT_"
				+ bean.getArrPaidYear()
				+ " WHERE ARREARS_EMP_ID=?"
				+ "	AND ARREARS_MONTH=? AND ARREARS_YEAR=? AND ARREARS_PAY_TYPE='A' AND ARREARS_CODE="
				+ insertArrCode + " ";

		String deleteArrearQuery = "DELETE FROM HRMS_ARREARS_"
				+ bean.getArrPaidYear()
				+ " WHERE EMP_ID=?"
				+ "	AND ARREARS_MONTH=? AND ARREARS_YEAR=? AND ARREARS_PAY_TYPE='A' AND ARREARS_CODE="
				+ insertArrCode + " ";

		String insertQuery = " INSERT INTO HRMS_ARREARS_"
				+ bean.getArrPaidYear()
				+ "(ARREARS_CODE,EMP_ID,ARREARS_MONTH,ARREARS_YEAR,ARREARS_DAYS,"
				+ " ARREARS_NET_AMT,ARREARS_CREDITS_AMT,"
				+ " ARREARS_DEBITS_AMT,ARREARS_ONHOLD,ARREARS_PROMCODE,ARREARS_PAY_TYPE) VALUES("
				+ insertArrCode + ",?,?,?,?,?,?,?,?,?,'A')";

		String insertCreditQuery = " INSERT INTO HRMS_ARREARS_CREDIT_"
				+ bean.getArrPaidYear()
				+ " (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_CREDIT_CODE, "
				+ " ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT,PROM_CODE,ARREARS_PAY_TYPE)"
				+ " VALUES(?,?,?,?,?,?,?,'A')";

		String insertDebitQuery = " INSERT INTO HRMS_ARREARS_DEBIT_"
				+ bean.getArrPaidYear()
				+ " (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_DEBIT_CODE, "
				+ " ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT,PROM_CODE,ARREARS_PAY_TYPE)"
				+ " VALUES(?,?,?,?,?,?,?,'A')";
		if (result1 && finalObj != null) {
			// DELETE AND INSERT INTO ARREAR_SALARY
			getSqlModel().singleExecute(deleteArrearQuery, deleteObj);
			getSqlModel().singleExecute(insertQuery, insertArrObj);

			// DELETE AND INSERT INTO ARREAR_CREDIT
			getSqlModel().singleExecute(deleteCreditQuery, deleteObj);
			getSqlModel().singleExecute(insertCreditQuery, finalObj);

			// DELETE AND INSERT INTO ARREAR_DEBIT
			getSqlModel().singleExecute(deleteDebitQuery, deleteObj);
			getSqlModel().singleExecute(insertDebitQuery, insertDebitObj);

			getSqlModel().singleExecute(
					"DELETE FROM HRMS_ARREARS_" + bean.getArrPaidYear()
							+ " WHERE ARREARS_MONTH =0 AND ARREARS_PROMCODE=0");
			getSqlModel().singleExecute(
					"DELETE FROM HRMS_ARREARS_DEBIT_" + bean.getArrPaidYear()
							+ " WHERE ARREARS_CODE =" + insertArrCode
							+ " AND PROM_CODE=0 ");
			getSqlModel().singleExecute(
					"DELETE FROM HRMS_ARREARS_CREDIT_" + bean.getArrPaidYear()
							+ " WHERE ARREARS_CODE =" + insertArrCode
							+ " AND PROM_CODE=0 ");
		}
		return result;
	}

	public Object[][] getPayrollConfig() {
		Object[][] salaryFiltersObj = null;
		try {
			String attendanceFiltersSql = " SELECT  "
					+ " NVL(CONF_CREDIT_ROUND,0),NVL(CONF_TOTCREDIT_OPTION,0),NVL(CONF_TOTDEBIT_ROUND,0),NVL(CONF_NETPAY_ROUND,0),0"
					+ " ,NVL(CONF_EPF,'Y') FROM HRMS_SALARY_CONF ";
			salaryFiltersObj = getSqlModel().getSingleResult(
					attendanceFiltersSql);
		} catch (Exception e) {
			logger.error("Exception in getFilters:" + e);
		}
		return salaryFiltersObj;
	}

	/**
	 * METHOD TO DISPALY LIST
	 * 
	 * @param bean
	 * @param request
	 */
	public void displayList(PromotionArrears bean, HttpServletRequest request) {
		String query = "SELECT ARREARS_CODE, DECODE(ARREARS_REF_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December')  ||'- '||ARREARS_REF_YEAR,"
				+ "  DECODE(ARREARS_REF_TO_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') ||'- '||ARREARS_REF_TO_YEAR "
				+ "	,ARREARS_DIVISION,DIV_NAME,ARREARS_BRANCH,NVL(CENTER_NAME,'ALL') ,DECODE(ARREARS_STATUS,'P','Process','L','Lock','U','UnLock')  FROM HRMS_ARREARS_LEDGER  "
				+ "	INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ARREARS_LEDGER.ARREARS_DIVISION) "
				+ "	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_ARREARS_LEDGER.ARREARS_BRANCH) "
				+ "	WHERE ARREARS_TYPE='P'	ORDER BY ARREARS_CODE DESC";
		Object[][] obj = getSqlModel().getSingleResult(query);

		bean.setIteratorlist(null);
		if (obj != null && obj.length > 0) {
			bean.setToTotRec(String.valueOf(obj.length));
			String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length,
					10);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				PromotionArrears subBean = new PromotionArrears();
				subBean.setListArrearCode(String.valueOf(obj[i][0]));
				subBean.setListFromMonth(String.valueOf(obj[i][1]));
				subBean.setListToMonth(String.valueOf(obj[i][2]));
				subBean.setListDivId(String.valueOf(obj[i][3]));
				subBean.setListDivName(String.valueOf(obj[i][4]));
				subBean.setListBranchId(String.valueOf(obj[i][5]));
				subBean.setListBranch(String.valueOf(obj[i][6]));
				subBean.setListStatus(String.valueOf(obj[i][7]));
				list.add(subBean);
			}
			bean.setIteratorlist(list);
		}

	}

	/**
	 * METHOD TO DISPALY PROMOTION DETAILS
	 * 
	 * @param request
	 * @param bean
	 */

	public void callForEdit(HttpServletRequest request, PromotionArrears bean) {
		String arrCode = bean.getArrCode();

		Object[][] debitObj = getDebitHead();
		String query = " SELECT ARREARS_CODE, ARREARS_REF_MONTH,ARREARS_REF_YEAR,ARREARS_REF_TO_MONTH,ARREARS_REF_TO_YEAR , "
				+ "	  ARREARS_PAID_MONTH, ARREARS_PAID_YEAR,ARREARS_PAY_IN_SAL, NVL(ARREARS_DEDUCT_TAX,'N'),"
				+ "	  ARREARS_DIVISION,DIV_NAME, ARREARS_BRANCH,CENTER_NAME,DECODE(ARREARS_STATUS,'P','Process','L','Lock','U','UnLock') 	"
				+ "	  , NVL(SUPPRESSED_CREDIT_FLAG,'N'),NVL(SUPPRESSED_DEBIT_CODE,'0') FROM HRMS_ARREARS_LEDGER  	"
				+ "	  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_ARREARS_LEDGER.ARREARS_DIVISION) "
				+ "	 	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_ARREARS_LEDGER.ARREARS_BRANCH) 	WHERE ARREARS_CODE="
				+ arrCode + "	";
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {

			bean.setMonthName(String.valueOf(obj[0][1]));
			bean.setEArrYear(String.valueOf(obj[0][2]));
			bean.setArrToMonth(String.valueOf(obj[0][3]));
			bean.setArrToYear(String.valueOf(obj[0][4]));
			bean.setArrPaidMonth(String.valueOf(obj[0][5]));
			bean.setArrPaidYear(String.valueOf(obj[0][6]));
			bean.setPayinSalCheckBox("false");
			if (String.valueOf(obj[0][7]).equals("Y")) {
				bean.setPayinSalCheckBox("true");
			}
			bean.setDeductTaxCheckBox("false");
			if (String.valueOf(obj[0][8]).equals("Y")) {
				bean.setDeductTaxCheckBox("true");
			}
			bean.setDivCode(checkNull(String.valueOf(obj[0][9])));
			bean.setDivName(checkNull(String.valueOf(obj[0][10])));
			bean.setBrnCode(checkNull(String.valueOf(obj[0][11])));
			bean.setBrnName(checkNull(String.valueOf(obj[0][12])));
			bean.setStatus(String.valueOf(obj[0][13]));
			bean.setSuppressCreditFlag("false");
			if (String.valueOf(obj[0][14]).equals("Y")) {
				bean.setSuppressCreditFlag("true");
			}
			try {
				bean
						.setSuppressDebitCode(checkNull(String
								.valueOf(obj[0][15])));
				bean.setSuppressDebitName(Utility.getNameByKey(debitObj, String
						.valueOf(obj[0][15])));
			} catch (Exception e) {
				// TODO: handle exception
			}
			String year = bean.getArrPaidYear();
			String selQuery = "SELECT DISTINCT EMP_ID FROM HRMS_ARREARS_"
					+ year + " WHERE ARREARS_CODE=" + arrCode;
			Object[][] sumObj = getSqlModel().getSingleResult(selQuery);
			if (sumObj != null && sumObj.length > 0) {
				bean.setTotalEmployee(String.valueOf(sumObj.length));
			}
			String selQuery1 = "SELECT  NVL(SUM(ARREARS_NET_AMT),0)   FROM HRMS_ARREARS_"
					+ year + " WHERE ARREARS_CODE=" + arrCode;
			Object[][] sumObj1 = getSqlModel().getSingleResult(selQuery1);
			if (sumObj1 != null && sumObj1.length > 0) {
				bean.setTotalNetAmount(formatter.format(Double
						.parseDouble(String.valueOf(sumObj1[0][0]))));
			}

		}

	}

	/**
	 * METHOD TO DELETE PROMOTION
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deletePromotionArrears(PromotionArrears bean) {
		boolean result = false;
		String insertArrCode = bean.getArrCode();
		String year = bean.getArrPaidYear();
		String deletePromotion = "DELETE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_CODE="
				+ insertArrCode;
		result = getSqlModel().singleExecute(deletePromotion);
		if (result) {
			deletePromotion = "DELETE FROM HRMS_ARREARS_CREDIT_" + year
					+ " WHERE ARREARS_CODE=" + insertArrCode;
			result = getSqlModel().singleExecute(deletePromotion);
		}
		if (result) {
			deletePromotion = "DELETE FROM HRMS_ARREARS_DEBIT_" + year
					+ " WHERE ARREARS_CODE=" + insertArrCode;
			result = getSqlModel().singleExecute(deletePromotion);
		}
		if (result) {
			deletePromotion = "DELETE FROM HRMS_ARREARS_" + year
					+ " WHERE ARREARS_CODE=" + insertArrCode;
			result = getSqlModel().singleExecute(deletePromotion);
		}
		return result;
	}

	/**
	 * METHOD TO DELETE PROMOTION
	 * 
	 * @param bean
	 * @return
	 */
	public String deleteAllPromotionArrears(PromotionArrears bean) {
		String message = "";
		boolean result = false;
		String insertArrCode = bean.getArrCode();
		// String year=bean.getArrPaidYear();

		/**
		 * CHECK SALARY LOCK NOR NOT
		 */
		String paidYear = bean.getArrPaidYear();
		String paidMonth = bean.getArrPaidMonth();

		String salQUery = "SELECT LEDGER_STATUS FROM HRMS_SALARY_LEDGER WHERE LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH="
				+ paidMonth
				+ " AND LEDGER_YEAR="
				+ paidYear
				+ " AND "
				+ "	LEDGER_DIVISION=" + bean.getDivCode() + " ";
		if (!bean.getBrnCode().equals("")) {
			salQUery += " AND LEDGER_BRN=" + bean.getBrnCode();
		}
		Object[][] salObj = getSqlModel().getSingleResult(salQUery);
		if (bean.getPayinSalCheckBox().equals("true") && salObj != null
				&& salObj.length > 0) {
			message = "Salary already locked ?\n Can not delete the promotion";
			return message;
		}

		String query = "SELECT ARREARS_PROMCODE,EMP_ID FROM HRMS_ARREARS_"
				+ paidYear + " WHERE ARREARS_CODE=" + insertArrCode;
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			String updateQuery = "UPDATE HRMS_PROMOTION SET ARREARS_PROCESS_STATUS='N' WHERE PROM_CODE=? AND EMP_CODE=?";
			result = getSqlModel().singleExecute(updateQuery, obj);
		}

		String deletePromotion = "DELETE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_CODE="
				+ insertArrCode;
		if (result) {
			result = getSqlModel().singleExecute(deletePromotion);
		}
		if (result) {
			deletePromotion = "DELETE FROM HRMS_ARREARS_CREDIT_" + paidYear
					+ " WHERE ARREARS_CODE=" + insertArrCode;
			result = getSqlModel().singleExecute(deletePromotion);
		}
		if (result) {
			deletePromotion = "DELETE FROM HRMS_ARREARS_DEBIT_" + paidYear
					+ " WHERE ARREARS_CODE=" + insertArrCode;
			result = getSqlModel().singleExecute(deletePromotion);
		}
		if (result) {
			deletePromotion = "DELETE FROM HRMS_ARREARS_" + paidYear
					+ " WHERE ARREARS_CODE=" + insertArrCode;
			result = getSqlModel().singleExecute(deletePromotion);
		}
		return message;
	}

	/**
	 * METHOD TO GET CREDIT HEADS
	 * 
	 * @return OBJECT
	 */
	public Object[][] getCreditHead() {
		String creditQuery = " SELECT CREDIT_CODE, CREDIT_ABBR,NVL(CREDIT_TAXABLE_FLAG,'N'),NVL(CREDIT_TYPE,0),NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_APPLICABLE_PTAX,'N') FROM HRMS_CREDIT_HEAD	"
				+ "	WHERE CREDIT_APPLICABLE_ARREARS = 'Y' AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
		Object[][] creditHeadObj = getSqlModel().getSingleResult(creditQuery);
		return creditHeadObj;
	}

	/**
	 * METHOD TO GET DEBIT HEADS
	 * 
	 * @return OBJECT
	 */
	public Object[][] getDebitHead() {
		String debitQuery = " SELECT DEBIT_CODE, DEBIT_ABBR,NVL(DEBIT_ROUND,'0') FROM HRMS_DEBIT_HEAD	"
				+ "	WHERE DEBIT_APPLICABLE_ARREARS = 'Y' AND HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_CODE";
		Object[][] debitHeadObj = getSqlModel().getSingleResult(debitQuery);
		return debitHeadObj;
	}

	public void viewEmpPromotionArrears(PromotionArrears bean) {
		// TODO Auto-generated method stub
		String arrCode = bean.getArrCode();
		String empCode = bean.getEditEmpCode();
		String arrPaidYear = bean.getArrPaidYear();
		String creditHearQuery = "SELECT DISTINCT HRMS_ARREARS_CREDIT_"
				+ arrPaidYear
				+ ".ARREARS_CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_ABBR "
				+ "	 FROM HRMS_ARREARS_CREDIT_"
				+ arrPaidYear
				+ " "
				+ "	 INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_ARREARS_CREDIT_"
				+ arrPaidYear + ".ARREARS_CREDIT_CODE ) "
				+ "	 WHERE CREDIT_APPLICABLE_ARREARS = 'Y' AND HRMS_ARREARS_CREDIT_" + arrPaidYear
				+ ".ARREARS_CODE=" + arrCode + " AND HRMS_ARREARS_CREDIT_"
				+ arrPaidYear + ".ARREARS_EMP_ID=" + empCode + " ";
		Object[][] creditHeadObj = getSqlModel().getSingleResult(
				creditHearQuery);

		String debitHeadQuery = "SELECT DISTINCT HRMS_ARREARS_DEBIT_"
				+ arrPaidYear
				+ ".ARREARS_DEBIT_CODE,HRMS_DEBIT_HEAD.DEBIT_ABBR "
				+ "	 FROM HRMS_ARREARS_DEBIT_"
				+ arrPaidYear
				+ " "
				+ "	 INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_ARREARS_DEBIT_"
				+ arrPaidYear + ".ARREARS_DEBIT_CODE ) "
				+ "	 WHERE DEBIT_APPLICABLE_ARREARS = 'Y' AND HRMS_ARREARS_DEBIT_" + arrPaidYear
				+ ".ARREARS_CODE=" + arrCode + " AND HRMS_ARREARS_DEBIT_"
				+ arrPaidYear + ".ARREARS_EMP_ID=" + empCode + " ";
		Object[][] debitHeadObj = getSqlModel().getSingleResult(debitHeadQuery);

		String arrCreditQuery = " SELECT   ARREARS_CREDIT_CODE||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR,NVL(ARREARS_AMT,0) "
				+ " FROM HRMS_ARREARS_CREDIT_"
				+ arrPaidYear
				+ " "
				+ " WHERE ARREARS_CODE="
				+ arrCode
				+ " AND ARREARS_EMP_ID="
				+ empCode + " ";
		HashMap<String, Object[][]> arrCreditObj = getSqlModel()
				.getSingleResultMap(arrCreditQuery, 0, 2);

		String arrDebitQuery = " SELECT   ARREARS_DEBIT_CODE||'#'||ARREARS_MONTH||'#'||ARREARS_YEAR,NVL(ARREARS_AMT,0) "
				+ " FROM HRMS_ARREARS_DEBIT_"
				+ arrPaidYear
				+ " "
				+ " WHERE ARREARS_CODE="
				+ arrCode
				+ " AND ARREARS_EMP_ID="
				+ empCode + " ";
		HashMap<String, Object[][]> arrDebitObj = getSqlModel()
				.getSingleResultMap(arrDebitQuery, 0, 2);

		String arrEmpQuery = " SELECT ARREARS_MONTH,ARREARS_YEAR,NVL(ARREARS_DAYS,0),NVL(ARREARS_CREDITS_AMT,0),NVL(ARREARS_DEBITS_AMT,0), "
				+ "	NVL(ARREARS_NET_AMT,0),DECODE(ARREARS_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December'),EMP_ID,NVL(ARREARS_ONHOLD,'N'),ARREARS_PROMCODE    FROM HRMS_ARREARS_"
				+ arrPaidYear
				+ "  WHERE ARREARS_CODE="
				+ arrCode
				+ " AND EMP_ID=" + empCode + " ";
		Object[][] arrEmpObj = getSqlModel().getSingleResult(arrEmpQuery);

		if (arrEmpObj != null && arrEmpObj.length > 0) {
			ArrayList crHDList = new ArrayList();
			ArrayList drHDList = new ArrayList();

			ArrayList list = new ArrayList();
			bean.setFlag(true);
			// SET CREDIT HEAD
			if (creditHeadObj != null && creditHeadObj.length > 0) {
				bean.setCrHDList(null);
				for (int i = 0; i < creditHeadObj.length; i++) {
					PromotionArrears crBean = new PromotionArrears();
					crBean.setCreditCode(String.valueOf(creditHeadObj[i][0]));
					crBean.setCreditName(String.valueOf(creditHeadObj[i][1]));
					crHDList.add(crBean);
				}
				bean.setCrHDList(crHDList);
			}
			// SET DEBIT HEAD
			if (debitHeadObj != null && debitHeadObj.length > 0) {
				bean.setDrHDList(null);
				for (int i = 0; i < debitHeadObj.length; i++) {
					PromotionArrears drBean = new PromotionArrears();
					drBean.setDebitCode(String.valueOf(debitHeadObj[i][0]));
					drBean.setDebitName(String.valueOf(debitHeadObj[i][1]));
					drHDList.add(drBean);
				}
				bean.setDrHDList(drHDList);
			}

			/**
			 * SET EMPLOYEE ARR AMOUNT
			 */
			for (int i = 0; i < arrEmpObj.length; i++) {
				PromotionArrears bean1 = new PromotionArrears();
				bean1.setMonth(String.valueOf(arrEmpObj[i][6]));
				bean1.setListEmpCode(String.valueOf(arrEmpObj[i][7]));
				bean1.setHMonth(String.valueOf(arrEmpObj[i][0]));
				bean1.setYear(String.valueOf(arrEmpObj[i][1]));
				bean1.setArrDays(String.valueOf(arrEmpObj[i][2]));

				ArrayList crValList = new ArrayList();
				ArrayList drValList = new ArrayList();
				// SET EMPLOYEE CREDIT AMOUNT
				if (arrCreditObj != null && arrCreditObj.size() > 0) {
					for (int j = 0; j < creditHeadObj.length; j++) {
						PromotionArrears crValueBean = new PromotionArrears();
						String KEY = String.valueOf(creditHeadObj[j][0]) + "#"
								+ String.valueOf(arrEmpObj[i][0]) + "#"
								+ String.valueOf(arrEmpObj[i][1]);
						Object[][] obj = arrCreditObj.get(KEY);
						crValueBean.setCreditVal("0");
						if (obj != null && obj.length > 0) {
							crValueBean.setCreditVal(String.valueOf(obj[0][1]));
						}
						crValList.add(crValueBean);
					}
					bean1.setCrValList(crValList);
				}

				bean1.setTotalCr(String.valueOf(arrEmpObj[i][3]));

				// SET EMPLOYEE DEBIT AMOUNT
				if (arrDebitObj != null && arrDebitObj.size() > 0) {
					for (int j = 0; j < debitHeadObj.length; j++) {
						PromotionArrears crValueBean = new PromotionArrears();
						String KEY = String.valueOf(debitHeadObj[j][0]) + "#"
								+ String.valueOf(arrEmpObj[i][0]) + "#"
								+ String.valueOf(arrEmpObj[i][1]);
						Object[][] obj = arrDebitObj.get(KEY);
						crValueBean.setDebitVal("0");
						if (obj != null && obj.length > 0) {
							crValueBean.setDebitVal(String.valueOf(obj[0][1]));
						}
						drValList.add(crValueBean);
					}
					bean1.setDrValList(drValList);
				}
				bean1.setTotalDr(String.valueOf(arrEmpObj[i][4]));
				bean1.setAmtPay(String.valueOf(arrEmpObj[i][5]));
				bean1.setHPromCode(String.valueOf(arrEmpObj[i][9]));
				list.add(bean1);
			}
			bean.setArrList(list);

		}

	}

	public String savePromotionArrears(PromotionArrears bean,
			HttpServletRequest request) {
		String mess = "";
		String empCode = bean.getEditEmpCode();
		String arrCode = bean.getArrCode();
		String year = bean.getArrPaidYear();
		String[] listMonth = request.getParameterValues("hMonth");
		String[] listYear = request.getParameterValues("year");
		String[] listArrDays = request.getParameterValues("arrDays");
		String[] listCreditCode = request.getParameterValues("creditCode");
		String[] listDebitCode = request.getParameterValues("debitCode");
		String[] listTotCredit = request.getParameterValues("totCredit");
		String[] listTotDebit = request.getParameterValues("totDebit");
		String[] listTotAmtPay = request.getParameterValues("totAmtPay");
		String[] hPromCode = request.getParameterValues("hPromCode");
		String[] hcheck = request.getParameterValues("hcheck");
		int COUNT = 0;
		if (hcheck != null && hcheck.length > 0) {
			for (int i = 0; i < hcheck.length; i++) {
				if (String.valueOf(hcheck[i]).equals("Y")) {
					COUNT++;
				}
			}

			int cnt = 0;
			int credit_cnt = 0;
			int debit_cnt = 0;
			int CREDIT_COUNT = 0;
			int DEBIT_COUNT = 0;
			if (listCreditCode != null && listCreditCode.length > 0) {
				CREDIT_COUNT = listCreditCode.length;
			}
			if (listDebitCode != null && listDebitCode.length > 0) {
				DEBIT_COUNT = listDebitCode.length;
			}
			Object[][] updateArrObj = new Object[COUNT][7];
			Object[][] insertCreditObj = new Object[COUNT * CREDIT_COUNT][7];
			Object[][] insertDebitObj = new Object[COUNT * DEBIT_COUNT][7];
			Object[][] deleteObj = new Object[COUNT][4];
			for (int i = 0; i < hcheck.length; i++) {
				if (String.valueOf(hcheck[i]).equals("Y")) {
					String[] listCreditValue = request.getParameterValues("CR"
							+ (i + 1));
					String[] listDebitValue = request.getParameterValues("DR"
							+ (i + 1));
					updateArrObj[cnt][0] = listTotCredit[i];// TOTALCREDIT
					updateArrObj[cnt][1] = listTotDebit[i];// TOTALDEBIT
					updateArrObj[cnt][2] = listTotAmtPay[i];// NETPAY
					updateArrObj[cnt][3] = arrCode;// ARREARS_CODE
					updateArrObj[cnt][4] = empCode;// EMP_ID
					updateArrObj[cnt][5] = listMonth[i];// ARREARS_MONTH
					updateArrObj[cnt][6] = listYear[i];// ARREARS_YEAR

					// DELETE OBJECT
					deleteObj[cnt][0] = arrCode;
					deleteObj[cnt][1] = empCode;
					deleteObj[cnt][2] = listMonth[i];// ARREARS_MONTH;
					deleteObj[cnt][3] = listYear[i];// ARREARS_YEAR;

					// INSERT ARR CREDIT
					if (CREDIT_COUNT > 0) {
						for (int j = 0; j < listCreditCode.length; j++) {
							insertCreditObj[credit_cnt][0] = arrCode;// ARREARS_CODE
							insertCreditObj[credit_cnt][1] = empCode;// ARREARS_EMP_ID
							insertCreditObj[credit_cnt][2] = listCreditCode[j];// ARREARS_CREDIT_CODE
							insertCreditObj[credit_cnt][3] = listMonth[i];// ARREARS_MONTH;
							insertCreditObj[credit_cnt][4] = listYear[i];// ARREARS_YEAR;
							insertCreditObj[credit_cnt][5] = listCreditValue[j];// ARREARS_AMT
							insertCreditObj[credit_cnt][6] = hPromCode[i];// PROM_CODE
							credit_cnt++;
						}
					}

					// INSERT ARR DEBIT
					if (DEBIT_COUNT > 0) {
						for (int j = 0; j < listDebitCode.length; j++) {
							insertDebitObj[debit_cnt][0] = arrCode;// ARREARS_CODE
							insertDebitObj[debit_cnt][1] = empCode;// ARREARS_EMP_ID
							insertDebitObj[debit_cnt][2] = listDebitCode[j];// ARREARS_CREDIT_CODE
							insertDebitObj[debit_cnt][3] = listMonth[i];// ARREARS_MONTH;
							insertDebitObj[debit_cnt][4] = listYear[i];// ARREARS_YEAR;
							insertDebitObj[debit_cnt][5] = listDebitValue[j];// ARREARS_AMT
							insertDebitObj[debit_cnt][6] = hPromCode[i];// PROM_CODE
							debit_cnt++;
						}
					}

					cnt++;
				}
			}

			String deleteCreditQuery = "DELETE FROM HRMS_ARREARS_CREDIT_"
					+ year
					+ " WHERE ARREARS_CODE=? AND ARREARS_EMP_ID=? AND ARREARS_MONTH=? AND ARREARS_YEAR=?";
			String deleteDebitQuery = "DELETE FROM HRMS_ARREARS_DEBIT_"
					+ year
					+ " WHERE ARREARS_CODE=? AND ARREARS_EMP_ID=? AND ARREARS_MONTH=? AND ARREARS_YEAR=?";

			String insertCreditQuery = " INSERT INTO HRMS_ARREARS_CREDIT_"
					+ year
					+ " (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_CREDIT_CODE, "
					+ " ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT,PROM_CODE,ARREARS_PAY_TYPE)"
					+ " VALUES(?,?,?,?,?,?,?,'A')";

			String updateArr = "UPDATE HRMS_ARREARS_"
					+ year
					+ " SET ARREARS_CREDITS_AMT=?,ARREARS_DEBITS_AMT=?,ARREARS_NET_AMT=?   "
					+ " WHERE ARREARS_CODE=? AND EMP_ID=? AND ARREARS_MONTH=? AND ARREARS_YEAR=?  ";

			String insertDebitQuery = " INSERT INTO HRMS_ARREARS_DEBIT_"
					+ bean.getArrPaidYear()
					+ " (ARREARS_CODE,ARREARS_EMP_ID,ARREARS_DEBIT_CODE, "
					+ " ARREARS_MONTH,ARREARS_YEAR,ARREARS_AMT,PROM_CODE,ARREARS_PAY_TYPE)"
					+ " VALUES(?,?,?,?,?,?,?,'A')";

			boolean flag = getSqlModel().singleExecute(deleteCreditQuery,
					deleteObj);
			flag = getSqlModel().singleExecute(deleteDebitQuery, deleteObj);

			if (flag) {
				flag = getSqlModel().singleExecute(updateArr, updateArrObj);
				flag = getSqlModel().singleExecute(insertCreditQuery,
						insertCreditObj);
				flag = getSqlModel().singleExecute(insertDebitQuery,
						insertDebitObj);
			}


		} else {
			mess = "Please select atleast one record";
		}
		return null;
	}

	/**
	 * METHOD TO REMOVE THE SELECTED RECORDS
	 * 
	 * @param bean
	 * @param request
	 * @return GOOLEAN
	 */
	public boolean removePromotionArrears(PromotionArrears bean,
			HttpServletRequest request) {
		boolean flag = false;
		String empCode = bean.getEditEmpCode();
		String arrCode = bean.getArrCode();
		String year = bean.getArrPaidYear();
		String[] listMonth = request.getParameterValues("hMonth");
		String[] listYear = request.getParameterValues("year");
		String[] hcheck = request.getParameterValues("hcheck");
		String[] hPromCode = request.getParameterValues("hPromCode");
		int COUNT = 0;
		int cnt = 0;
		if (hcheck != null && hcheck.length > 0) {
			for (int i = 0; i < hcheck.length; i++) {
				if (String.valueOf(hcheck[i]).equals("Y")) {
					COUNT++;
				}
			}
			Object[][] deleteObj = new Object[COUNT][4];
			Object[][] updateObj = new Object[COUNT][2];
			for (int i = 0; i < hcheck.length; i++) {
				if (String.valueOf(hcheck[i]).equals("Y")) {
					// DELETE OBJECT
					deleteObj[cnt][0] = arrCode;
					deleteObj[cnt][1] = empCode;
					deleteObj[cnt][2] = listMonth[i];// ARREARS_MONTH;
					deleteObj[cnt][3] = listYear[i];// ARREARS_YEAR;
					updateObj[cnt][0] = hPromCode[i];
					updateObj[cnt][1] = empCode;
					cnt++;
				}
			}

			String deleteCreditQuery = "DELETE FROM HRMS_ARREARS_CREDIT_"
					+ year
					+ " WHERE ARREARS_CODE=? AND ARREARS_EMP_ID=? AND ARREARS_MONTH=? AND ARREARS_YEAR=?";
			String deleteDebitQuery = "DELETE FROM HRMS_ARREARS_DEBIT_"
					+ year
					+ " WHERE ARREARS_CODE=? AND ARREARS_EMP_ID=? AND ARREARS_MONTH=? AND ARREARS_YEAR=?";
			String deleteQuery = "DELETE FROM HRMS_ARREARS_"
					+ year
					+ " WHERE ARREARS_CODE=? AND EMP_ID=? AND ARREARS_MONTH=? AND ARREARS_YEAR=?";
			flag = getSqlModel().singleExecute(deleteQuery, deleteObj);
			if (flag) {
				flag = getSqlModel()
						.singleExecute(deleteCreditQuery, deleteObj);
				flag = getSqlModel().singleExecute(deleteDebitQuery, deleteObj);
				/*
				 * UPDATE PROMOTION STATUS
				 */
				if (updateObj != null && updateObj.length > 0) {
					for (int i = 0; i < updateObj.length; i++) {
						String selectQuery = "SELECT ARREARS_PROMCODE,EMP_ID FROM HRMS_ARREARS_"
								+ year
								+ " "
								+ " WHERE ARREARS_CODE="
								+ arrCode
								+ " AND EMP_ID="
								+ empCode
								+ " AND ARREARS_PROMCODE= " + updateObj[i][0];
						Object[][] obj = getSqlModel().getSingleResult(
								selectQuery);
						if (obj != null && obj.length > 0) {

						} else {
							Object[][] update_Obj = new Object[1][2];
							update_Obj[0][0] = updateObj[i][0];
							update_Obj[0][1] = empCode;
							String updateQuery1 = "UPDATE HRMS_PROMOTION SET ARREARS_PROCESS_STATUS='N' WHERE PROM_CODE=? AND EMP_CODE=?";
							getSqlModel().singleExecute(updateQuery1,
									update_Obj);
						}
					}
				}

			}
		}
		return flag;
	}

	/**
	 * METHOD TO REMOVE THE SELECTED RECORDS
	 * 
	 * @param bean
	 * @param request
	 * @return GOOLEAN
	 */
	public boolean onholdPromEmployee(PromotionArrears bean,
			HttpServletRequest request, String status) {
		boolean flag = false;
		String empCode = bean.getEditEmpCode();
		String arrCode = bean.getArrCode();
		String year = bean.getArrPaidYear();
		String[] listMonth = request.getParameterValues("hMonth");
		String[] listYear = request.getParameterValues("year");
		String[] hcheck = request.getParameterValues("hcheck");
		int COUNT = 0;
		int cnt = 0;
		if (hcheck != null && hcheck.length > 0) {
			for (int i = 0; i < hcheck.length; i++) {
				if (String.valueOf(hcheck[i]).equals("Y")) {
					COUNT++;
				}
			}
			Object[][] updateObj = new Object[COUNT][4];
			for (int i = 0; i < hcheck.length; i++) {
				if (String.valueOf(hcheck[i]).equals("Y")) {
					// DELETE OBJECT
					updateObj[cnt][0] = arrCode;
					updateObj[cnt][1] = empCode;
					updateObj[cnt][2] = listMonth[i];// ARREARS_MONTH;
					updateObj[cnt][3] = listYear[i];// ARREARS_YEAR;
				}
			}

			String deleteQuery = "UPDATE HRMS_ARREARS_"
					+ year
					+ " SET ARREARS_ONHOLD='"
					+ status
					+ "' WHERE ARREARS_CODE=? AND EMP_ID=? AND ARREARS_MONTH=? AND ARREARS_YEAR=?";
			flag = getSqlModel().singleExecute(deleteQuery, updateObj);
		}
		return flag;
	}

	public void updatePromotionArrearsStatus(PromotionArrears bean,
			String empCode, String hPromCode) {
		String year = bean.getArrPaidYear();
		String fromMonth = bean.getMonthName();
		String fromYear = bean.getEArrYear();
		String toMonth = bean.getArrToMonth();
		String toYear = bean.getArrToYear();
		if (Integer.parseInt(fromMonth) < 10) {
			fromMonth = "0" + fromMonth;
		}
		if (Integer.parseInt(toMonth) < 10) {
			toMonth = "0" + toMonth;
		}

		String promotionQuery = "SELECT HRMS_PROMOTION.PROM_CODE,HRMS_PROMOTION.EMP_CODE FROM HRMS_PROMOTION "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_PROMOTION.EMP_CODE)"
				+ " WHERE HRMS_PROMOTION.LOCK_FLAG='Y' "
				+ "  AND HRMS_EMP_OFFC.EMP_DIV="
				+ bean.getDivCode()
				+ " AND TO_DATE(TO_CHAR(EFFECT_DATE,'MM-YYYY'),'MM-YYYY')>=TO_DATE('"
				+ fromMonth
				+ "-"
				+ fromYear
				+ "','MM-YYYY')  AND TO_DATE(TO_CHAR(EFFECT_DATE,'MM-YYYY'),'MM-YYYY')<=TO_DATE('"
				+ toMonth + "-" + toYear + "','MM-YYYY')  ";
		if (!bean.getBrnCode().equals("")) {
			promotionQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="
					+ bean.getBrnCode();
		}
		if (!empCode.equals("")) {
			promotionQuery += " AND HRMS_PROMOTION.EMP_CODE=" + empCode;
		}

		String query = "SELECT ARREARS_PROMCODE,EMP_ID FROM HRMS_ARREARS_"
				+ year + " WHERE ARREARS_CODE=" + bean.getArrCode();
		if (!empCode.equals("")) {
			query += " AND EMP_ID=" + empCode;
		}
		if (!hPromCode.equals("")) {
			query += " AND ARREARS_PROMCODE IN(" + hPromCode + ")";
		}
		// Object[][]obj=getSqlModel().getSingleResult(promotionQuery+" UNION
		// "+query);
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			String updateQuery = "UPDATE HRMS_PROMOTION SET ARREARS_PROCESS_STATUS='N' WHERE PROM_CODE=? AND EMP_CODE=?";
			getSqlModel().singleExecute(updateQuery, obj);
		}
	}

	public boolean lockPromotion(PromotionArrears bean, String status) {
		String updateQuery = "UPDATE HRMS_ARREARS_LEDGER SET ARREARS_STATUS='"
				+ status + "' WHERE ARREARS_CODE=" + bean.getArrCode();
		boolean result = getSqlModel().singleExecute(updateQuery);
		return result;
	}
	/**
	 * 
	 * @param bean
	 * @return string
	 */
	public String checkSalaryLock(PromotionArrears bean) {
		/**
		 * CHECK SALARY LOCK NOR NOT
		 */
		String message = "";
		String paidYear = bean.getArrPaidYear();
		String paidMonth = bean.getArrPaidMonth();

		String salQUery = "SELECT LEDGER_STATUS FROM HRMS_SALARY_LEDGER WHERE LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH="
				+ paidMonth
				+ " AND LEDGER_YEAR="
				+ paidYear
				+ " AND "
				+ "	LEDGER_DIVISION=" + bean.getDivCode() + " ";
		if (!bean.getBrnCode().equals("")) {
			salQUery += " AND LEDGER_BRN=" + bean.getBrnCode();
		}
		Object[][] salObj = getSqlModel().getSingleResult(salQUery);
		if (bean.getPayinSalCheckBox().equals("true") && salObj != null
				&& salObj.length > 0) {
			message = "Salary already locked for select paid in month and year";
			return message;
		}

		salQUery = "SELECT LEDGER_STATUS FROM HRMS_SALARY_LEDGER WHERE LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH="
				+ paidMonth
				+ " AND LEDGER_YEAR="
				+ paidYear
				+ " AND "
				+ "	LEDGER_DIVISION="
				+ bean.getDivCode()
				+ " AND LEDGER_BRN IS NULL ";

		salObj = getSqlModel().getSingleResult(salQUery);
		if (bean.getPayinSalCheckBox().equals("true") && salObj != null
				&& salObj.length > 0) {
			message = "Salary already locked for select paid in month and year";
			return message;
		}

		return message;
	}// end of method

} // end of class

