/**
 * 
 */
package org.paradyne.model.payroll;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.CashArrears;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Pankaj_Jain
 * 
 */
public class CashArrearsModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * This method is used to calculate the cash arrears for the employees 
	 * whose promotion has been done but the increment amount is not paid.
	 * @param bean
	 * @return Object[][] contains calculated arrears records
	 */
	public Object[][] calArrears(CashArrears bean) {
		try {
			 // TO SET CREDIT AND DEBIT IN TO BEAN OBJECT
			setHeaders(bean);
			Object [][] cashAmtObj = null;
			Object [][] promAmtObj = null;
			Object [][] arrearsObj = null;
			String effDate = "";
			double totAmt = 0.0;

			// QUERY TO FIND EMPLOYEES
			String empSql = " SELECT PROM_CODE, EMP_CODE, EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME) EMP_NAME, "
					+ " TO_CHAR(EFFECT_DATE, 'DD-MM-YYYY'),DECODE(RESIGN_WITHDRAWN ,'Y','true', 'false') "
					+ " FROM HRMS_PROMOTION "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_PROMOTION.EMP_CODE) "
					+ " LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE CASH_ARR_STATUS = 'N'";
			empSql = setFilters(bean, empSql);
			empSql += " ORDER BY UPPER(EMP_FNAME || EMP_MNAME || EMP_LNAME) ";
			Object[][] empObj = getSqlModel().getSingleResult(empSql);
			arrearsObj = new Object[empObj.length][];
			double arrDays[] = new double[empObj.length];
			if(empObj != null && empObj.length > 0)
			{
				for (int i = 0; i < empObj.length; i++) {
					effDate = String.valueOf(empObj[i][4]);
					int empId =  Integer.parseInt(String.valueOf(empObj[i][1]));
					int promCode = Integer.parseInt(String.valueOf(empObj[i][0]));
					String cashCodeQuery = "SELECT CASH_CODE,CASH_TODATE-TO_DATE('"+effDate+"', 'DD-MM-YYYY') FROM HRMS_CASH_HDR WHERE CASH_TODATE >= To_Date('"
							+ effDate + "','DD-MM-YYYY')";
					Object[][] cashCodeObj = getSqlModel().getSingleResult(cashCodeQuery);
					if(cashCodeObj != null && cashCodeObj.length > 0)
					{
						for (int j = 0; j < cashCodeObj.length; j++) {
							int cashCode = Integer.parseInt(String.valueOf(cashCodeObj[j][0]));
							String cashAmtQuery = " SELECT CREDIT_CODE, NVL(CASH_AMOUNT,0) FROM HRMS_CREDIT_HEAD "
							+" LEFT JOIN HRMS_CASH_DTL ON (HRMS_CASH_DTL.CASH_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE " 
							+" AND EMP_ID = "+empId+" AND HRMS_CASH_DTL.CASH_CODE = "+cashCode+") "	  
							+" LEFT JOIN HRMS_CASH_HDR ON(HRMS_CASH_DTL.CASH_CODE = HRMS_CASH_HDR.CASH_CODE AND HRMS_CASH_HDR.CASH_CODE = "+cashCode+") " 	
							+" WHERE CREDIT_PAYFLAG = 'N' AND CREDIT_PERIODICITY = 'M'  "
							+" ORDER BY CREDIT_CODE ";
							cashAmtObj = getSqlModel().getSingleResult(cashAmtQuery);
							
							String promAmt= " SELECT NVL(NEW_AMT,0),NVL(NEW_AMT-OLD_AMT,'0'),CREDIT_CODE FROM HRMS_PROMOTION_SALARY " 
										   +" RIGHT JOIN HRMS_CREDIT_HEAD on(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE AND PROM_CODE = "+promCode+") "
										   +" WHERE CREDIT_PAYFLAG = 'N' AND CREDIT_PERIODICITY = 'M' " 
										   +" ORDER BY CREDIT_CODE "; 
							promAmtObj = getSqlModel().getSingleResult(promAmt);
							logger.info("cashAmtObj[0][0] = "+cashAmtObj[0][0]+" promAmtObj[0][2] = "+promAmtObj[0][2]);
							
							if(String.valueOf(cashAmtObj[0][0]).equals(String.valueOf(promAmtObj[0][2])))
							{
								if(!String.valueOf(cashAmtObj[0][1]).equals(String.valueOf(promAmtObj[0][2])))
								{
									arrDays[i]  = Double.parseDouble(String.valueOf(cashCodeObj[j][1]));
								}
							} // end if
							
						} // END J LOOP CASHCODEOBJ
					
						int j = 4;
						arrearsObj[i] = new Object[cashAmtObj.length + 6];
						arrearsObj[i][0] = promCode;
						arrearsObj[i][1] = empId;
						arrearsObj[i][2] = empObj[i][2];
						arrearsObj[i][3] = empObj[i][3];
						if(cashAmtObj != null)
						for (int k = 0; k < cashAmtObj.length; k++) {
							double value = Double.parseDouble(""+promAmtObj[k][1]) / 30.0 * arrDays[i];
							arrearsObj[i][j++] = Math.round(value);
							totAmt+=value;
						} // END K LOOP CASHAMTOBJ
						arrearsObj[i][j++] = Math.round(totAmt);
						arrearsObj[i][j++] = arrDays[i];
					}   // END IF CASHCODEOBJ != NULL
					
				} 	//END I LOOP EMPOBJ LOOP
				
			} 	// END IF EMPOBJ != NULL
			return arrearsObj;
		} // TRY BLOCK END
		catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
			return null;
		}
	} // METHOD END

	/**
	 * this method is used to set the query according to the selected filter
	 * @param bean
	 * @param sqlQuery
	 * @return String containing query with filters
	 */
	public String setFilters(CashArrears bean, String sqlQuery) 
		{
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
			logger.error("Exception in cash arrears "+e);
			return "";
		}
	} // end of method

	/**
	 * the method is used to select the credit from credit master 
	 * @param bean
	 * @return Object[][] contains credit codes and their abbreviation
	 */
	public Object[][] setSalaryHeads(CashArrears bean) {
		try {
			String crHDSql = " SELECT CREDIT_CODE, TRIM(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD "
					+ " WHERE CREDIT_PAYFLAG = 'N' "
					+ " AND CREDIT_PERIODICITY = 'M' ORDER BY CREDIT_CODE ";
			Object[][] crHDObj = getSqlModel().getSingleResult(crHDSql);
			bean.setCreditCodeObj(crHDObj);
			return crHDObj;
		} catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
			return null;
		}
	} // end of method

	/**
	 * the method is used to select the credit from credit master 
	 * @param bean
	 * @return Object[][] contains credit codes and their abbreviation
	 */
	public Object[][] getCreditHeader(CashArrears bean)
	{
		try {
			String crHDSql = " SELECT CREDIT_CODE, TRIM(CREDIT_ABBR) FROM HRMS_CREDIT_HEAD "
					+ " WHERE CREDIT_PAYFLAG = 'N'"
					+ " AND CREDIT_PERIODICITY = 'M' ORDER BY CREDIT_CODE ";
			Object[][] crHDObj = getSqlModel().getSingleResult(crHDSql);
			return crHDObj;
		} catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
			return null;
		}
	}

	/**
	 * this method is used to save the processed arrears 
	 * @param rows
	 * @param c
	 * @param emp_id
	 * @param arrDays
	 * @param bean
	 * @param promCode
	 * @param onHold
	 * @return integer value indicating save status
	 */
	public int save(Object[][] rows, Object[][] c,
			String[] emp_id, String[] arrDays, CashArrears bean,
			String[] promCode, String[] onHold) {
		try {
			// IF IT IS 2 RECORD IS GETTING MODIFIED IF 1 NEW INSERT
			int saveType = 2;
			String delArrearCode = bean.getArrCode();
			String insertArrCode = bean.getArrCode();
			if (delArrearCode.equals(""))
				delArrearCode = "0";

			// IF TRUE NEW RECORD IS INSERTED IN THE TABLES
			if (insertArrCode.equals("")) {
				String arrcodeQuery = "SELECT NVL(MAX(ARREARS_CODE),0)+1 from HRMS_CASH_ARREARS_HDR";
				Object[][] code = getSqlModel().getSingleResult(arrcodeQuery);
				insertArrCode = String.valueOf(code[0][0]);
				saveType = 1; // CHANGE SAVETYPE = 1 AS IT IS NEW INSERT
			}

			String deleteHdrQuery = " DELETE FROM HRMS_CASH_ARREARS_HDR WHERE ARREARS_CODE =  "+ delArrearCode;
			String deleteDtlQuery = " DELETE FROM HRMS_CASH_ARREARS_DTL WHERE ARREARS_CODE =  "+ delArrearCode;
			String deleteCreditQuery = " DELETE FROM HRMS_CASH_ARREARS_CREDITS WHERE ARREARS_CODE =  "+ delArrearCode;

			getSqlModel().singleExecute(deleteDtlQuery);
			getSqlModel().singleExecute(deleteHdrQuery);
			getSqlModel().singleExecute(deleteCreditQuery);

			// (SELECT NVL(MAX(ARREARS_CODE),0)+1 from
			// HRMS_ARREARS_"+bean.getArrYear()+")
			// QUERY FOR ARREARS HDR TABLE
			String insertHdrQuery = " INSERT INTO HRMS_CASH_ARREARS_HDR(ARREARS_CODE,ARREARS_DIVISION,ARREARS_BRANCH,ARREARS_STATUS) "
								   +" VALUES(?,?,?,?) ";
			
			// QUERY FOR ARREARS DTL TABLE
			String insertCreditQuery = " INSERT INTO HRMS_CASH_ARREARS_DTL(ARREARS_CODE,ARREARS_EMPID,ARREARS_DAYS,ARREARS_PROMCODE,ARREARS_TOTAL_CREDIT) "
									  +" VALUES(?,?,?,?,?)";

			// QUERY FOR CREDITS TABLE
			String insertDtlQuery = " INSERT INTO HRMS_CASH_ARREARS_CREDITS(ARREARS_CODE,ARREARS_EMPID,ARREARS_CREDITCODE,ARREARS_AMT) "
								   +" VALUES(?,?,?,?)";

			// QUERY FOR UPDATING PROMOTION TABLE PROCESS_STATUS AS ARREARS ARE
			// PROCCESSED
			String updatePromCode = "";
			for (int i = 0; i < promCode.length; i++) {
				updatePromCode+=promCode[i]+",";
			}
			updatePromCode = updatePromCode.substring(0,updatePromCode.length() - 1);
			
			String updatePromoFlagQuery = " UPDATE HRMS_PROMOTION SET CASH_ARR_STATUS = 'Y'"
				+ " WHERE PROM_CODE IN("+updatePromCode+")";
			Object[][] creditData = new Object[emp_id.length * c.length][4];
			Object[][] saveHdrObj = new Object[1][4];
			Object[][] saveDtlObj = new Object[emp_id.length][5];
			
			
			int creditCount = 0;
			saveHdrObj[0][0] = insertArrCode;
			saveHdrObj[0][1] = bean.getDivCode();;
			saveHdrObj[0][2] = bean.getBrnCode();
			saveHdrObj[0][3] = "P";

			// OBJECT FOR INSERTING INTO HRMS_ARREARS_HDR TABLE
			for (int i = 0; i < saveDtlObj.length; i++) {

				double totAmt = 0.0;
				saveDtlObj[i][0] = insertArrCode;
				saveDtlObj[i][1] = emp_id[i];
				saveDtlObj[i][2] = arrDays[i];
				saveDtlObj[i][3] = promCode[i];
				
				// LOOP FOR INDIVIDUAL EMPLOYEE CREDIT 
				for (int j = 0; j < c.length; j++) {
						// OBJECT FOR INSERTING INTO DTL TABLE
						creditData[creditCount][0] = insertArrCode; // 
						creditData[creditCount][1] = emp_id[i]; // EMP_ID
						creditData[creditCount][2] = c[j][0];// 
						creditData[creditCount][3] = rows[i][j]; // CREDIT
						totAmt+=Double.parseDouble(String.valueOf(rows[i][j]));
						creditCount++;
				}
				saveDtlObj[i][4] = totAmt;
			}
			// UPDATE PROMOTION TABLE CASH_ARR_STATUS FLAG
			getSqlModel().singleExecute(updatePromoFlagQuery);
			
			// QUERY FOR HDR TABLE
			getSqlModel().singleExecute(insertHdrQuery, saveHdrObj);
			
			// QUERY FOR DTL TABLE
			getSqlModel().singleExecute(insertCreditQuery, saveDtlObj);

			// QUERY FOR CREDIT TABLE
			getSqlModel().singleExecute(insertDtlQuery,creditData); 
			
			return saveType;

		} catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
			return 0;
		}
	} // end of method

	/**
	 * This method is used for applying pagination on user screen so that
	 * records are displayed page wise as specified in configuration.
	 * @param bean
	 * @param empLength
	 * @param empObj
	 * @param request
	 * @param empSearch
	 */
	public void doPaging(CashArrears bean, int empLength,
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
			logger.error("Exception in cash arrears "+e);
		}
	} // end of method

	/**
	 * this method is used to show the saved arrears record after selecting
	 * it from search window 
	 * @param bean
	 * @return Object[][] containing records fetched from database
	 */
	public Object[][] showArrearsRecords(CashArrears bean) {
		try {
			String dtlQuery = " SELECT ARREARS_PROMCODE,ARREARS_EMPID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME, ARREARS_DAYS " 
						     +" FROM HRMS_CASH_ARREARS_DTL "
						     +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_ARREARS_DTL.ARREARS_EMPID) "
						     +" WHERE ARREARS_CODE = "+bean.getArrCode();
			Object empObj[][] = getSqlModel().getSingleResult(dtlQuery);
			Object rows[][] = new Object[empObj.length][];
			if(empObj!=null && empObj.length > 0)
			{
				setHeaders(bean);
				for (int i = 0; i < empObj.length; i++) {
					String creditQuery = " SELECT NVL(HRMS_CASH_ARREARS_CREDITS.ARREARS_AMT,'0') FROM  HRMS_CREDIT_HEAD "
									+" LEFT JOIN HRMS_CASH_ARREARS_CREDITS ON (HRMS_CASH_ARREARS_CREDITS.ARREARS_CREDITCODE = HRMS_CREDIT_HEAD.CREDIT_CODE) "
								    +" WHERE HRMS_CASH_ARREARS_CREDITS.ARREARS_CODE = "+bean.getArrCode()+" AND HRMS_CASH_ARREARS_CREDITS.ARREARS_EMPID = "+String.valueOf(empObj[i][1])				
								    +" AND HRMS_CREDIT_HEAD.CREDIT_PERIODICITY = 'M' AND HRMS_CREDIT_HEAD.CREDIT_PAYFLAG = 'N'"
								    +" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
					Object[][] creditObj = getSqlModel().getSingleResult(creditQuery);
					rows[i] = new Object[creditObj.length+6];
					rows[i][0] = empObj[i][0];
					rows[i][1] = empObj[i][1];
					rows[i][2] = empObj[i][2];
					rows[i][3] = empObj[i][3];
					int k = 4;
					double totalAmt = 0.0;
				   for (int j = 0; j < creditObj.length; j++) {
					   rows[i][k++] = creditObj[j][0];
					   totalAmt += Double.parseDouble(String.valueOf(creditObj[j][0]));
				   }
				   rows[i][k++] = Utility.twoDecimals(totalAmt);
				   rows[i][k++] = empObj[i][4];
			   } // End for loop
			} // End if
			return rows;
		} // Try Block End
		catch (Exception e) {
			logger.error("Exception in showing record for Cash Arrears "+e);
			return null;
		}
	} // End of method

	
	/**
	 * this method sets all the credit header in iterator
	 * @param bean
	 */
	public void setHeaders(CashArrears bean) {
		try {
			String creditQuery = " SELECT CREDIT_CODE,TRIM(CREDIT_ABBR)  FROM HRMS_CREDIT_HEAD "
					+ " WHERE CREDIT_PAYFLAG = 'N'"
					+ " AND CREDIT_PERIODICITY = 'M' "
					+ " ORDER BY CREDIT_CODE ";
			Object[][] salCrCodes = getSqlModel().getSingleResult(creditQuery);
			if (salCrCodes != null && salCrCodes.length > 0) {
				ArrayList<Object> crHDList = new ArrayList<Object>();
				for (int i = 0; i < salCrCodes.length; i++) {
					CashArrears crBean = new CashArrears();
					crBean.setCreditCode(String.valueOf(salCrCodes[i][0]));
					crBean.setCreditName(String.valueOf(salCrCodes[i][1]));
					crHDList.add(crBean);
				} // end for loop
				bean.setCrHDList(crHDList);
			} // end if
		} catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
		}
	} // end of method

	/**
	 * this method is used to update the arrears status in database
	 * @param bean
	 * @param type
	 * @param empId
	 * @param creditValue
	 * @param creditCode
	 * @return true on success else false
	 */
	public boolean lockArrears(CashArrears bean, String type, String[] empId, 
								String[][] creditValue, Object[][] creditCode) {
		try {
			String status = type;
			Object[][] updateObj = new Object[creditValue[0].length * empId.length][3];
			int count = 0;
			for (int i = 0; i < empId.length; i++) {
				for (int j = 0; j < creditCode.length; j++) {
					updateObj[count][0] = creditValue[i][j];
					updateObj[count][1] = empId[i];
					updateObj[count][2] = creditCode[j][0];
					count++;
				}
			}
			String updateBalance = "";
			updateBalance = " UPDATE HRMS_CASH_BALANCE ";
			if (status.equals("lock"))
			{
				updateBalance+= " SET CASH_BALANCE_AMT = CASH_BALANCE_AMT + ? "
							   +" WHERE EMP_ID = ? AND CASH_CREDIT_CODE = ? ";
				status = "L";
			}
			else
			{
				updateBalance+= " SET CASH_BALANCE_AMT = CASH_BALANCE_AMT - ? "
							   +" WHERE EMP_ID = ? AND CASH_CREDIT_CODE = ? ";
				status = "P";
			}
			String updateQuery = " UPDATE HRMS_CASH_ARREARS_HDR SET ARREARS_STATUS = '"+ status+ "'"
					+ " WHERE ARREARS_CODE = "+ bean.getArrCode();
			
			getSqlModel().singleExecute(updateBalance,updateObj);
			return getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
			return false;
		}
	} // end of method

	/**
	 * to delete the saved records from database
	 * @param bean
	 * @return true on success and false otherwise
	 */
	public boolean delete(CashArrears bean) {
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
			logger.error("Exception in cash arrears "+e);
			return false;
		}
	} // end of method

	/**
	 * method to remove the selected records from user screen
	 * the method is not used for now.
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
	public void removeEmpRecord(CashArrears arrBean, String[] empId,
			String[] empToken, String[] empName, String[] totCredit,
			String[] totDebit, String[] salDays, String[] arr_Days,
			String[] net_pay, String[] for_month, String[] for_year,
			String[] prom_code, Object[][] rowsAmt, Object[][] credit,
			Object[][] debit, HttpServletRequest request) {}

	
	/**
	 * method use to change the employee status to On Hold
	 * @param empId
	 * @param month
	 * @param year
	 * @param arrCode
	 */
	public void onHoldSave(String[] empId, String[] month, String[] year,
			String arrCode) {
		try {
			Object[][] updateObj = new Object[empId.length][4];
			String updateQuery = " UPDATE HRMS_ARREARS_2008 SET ARREARS_ONHOLD = 'Y' "
					+ " WHERE EMP_ID = ? AND ARREARS_MONTH = ? AND ARREARS_YEAR = ? AND ARREARS_CODE = ?";
			for (int i = 0; i < updateObj.length; i++) {
				updateObj[i][0] = empId[i];
				updateObj[i][1] = month[i];
				updateObj[i][2] = year[i];
				updateObj[i][3] = arrCode;
			}
			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
		}
	} // end of method

	
	/**
	 * this method is used to remove the selected records from On Hold
	 * @param empId
	 * @param month
	 * @param year
	 * @param arrCode
	 */
	public void removeOnHoldSave(String[] empId, String[] month, String[] year,
			String arrCode) {
		try {
			Object[][] updateObj = new Object[empId.length][4];
			String updateQuery = " UPDATE HRMS_ARREARS_2008 SET ARREARS_ONHOLD = 'N' "
					+ " WHERE EMP_ID = ? AND ARREARS_MONTH = ? AND ARREARS_YEAR = ? AND ARREARS_CODE = ?";
			for (int i = 0; i < updateObj.length; i++) {
				updateObj[i][0] = empId[i];
				updateObj[i][1] = month[i];
				updateObj[i][2] = year[i];
				updateObj[i][3] = arrCode;
			}
			getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
		}
	} // end of method

	
	/**
	 * this method is use to recalculate the se record
	 * @param empId
	 * @param promCode
	 * @param bean
	 * @return Object[][] containing recalculated amount and other employee information
	 */
	public Object[][] recalculate(String[] empId,String[] promCode, CashArrears bean) {

		try {
			Object[][] recalObj = new Object[empId.length][];
			Object[][] cashAmtObj = null;
			Object[][] promAmtObj = null;
			double[] arrDays = new double[empId.length];
			Object empObj[][] = null;
			for (int i = 0; i < empId.length; i++) {
				double totAmt = 0.0;
				String empQuery = " SELECT EMP_TOKEN, (EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME) EMP_NAME,  TO_CHAR(EFFECT_DATE, 'DD-MM-YYYY'),   EMP_CENTER, EMP_TYPE, DECODE(RESIGN_WITHDRAWN ,'Y','true', 'false'),LOCATION_PARENT_CODE "
						+ " FROM HRMS_PROMOTION  "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_PROMOTION.EMP_CODE) "
						+ " LEFT JOIN HRMS_RESIGN ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
						+ " INNER JOIN HRMS_CENTER	 ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+ " INNER JOIN HRMS_LOCATION	 ON(hrms_center.CENTER_LOCATION = HRMS_LOCATION.LOCATION_CODE)"
						+ " WHERE PROM_CODE = " + promCode[i];
				empObj = getSqlModel().getSingleResult(empQuery);
				String cashCodeQuery = "SELECT CASH_CODE,CASH_TODATE-TO_DATE('"+String.valueOf(empObj[0][2])+"', 'DD-MM-YYYY') FROM HRMS_CASH_HDR WHERE CASH_TODATE >= To_Date('"
				+ String.valueOf(empObj[0][2]) + "','DD-MM-YYYY')";
				Object[][] cashCodeObj = getSqlModel().getSingleResult(cashCodeQuery);
				if(cashCodeObj != null && cashCodeObj.length > 0)
				{
					for (int j = 0; j < cashCodeObj.length; j++) {
						int cashCode = Integer.parseInt(String.valueOf(cashCodeObj[j][0]));
						String cashAmtQuery = " SELECT CREDIT_CODE, NVL(CASH_AMOUNT,0) FROM HRMS_CREDIT_HEAD "
						+" LEFT JOIN HRMS_CASH_DTL ON (HRMS_CASH_DTL.CASH_CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE " 
						+" AND EMP_ID = "+empId[i]+" AND HRMS_CASH_DTL.CASH_CODE = "+cashCode+") "	  
						+" LEFT JOIN HRMS_CASH_HDR ON(HRMS_CASH_DTL.CASH_CODE = HRMS_CASH_HDR.CASH_CODE AND HRMS_CASH_HDR.CASH_CODE = "+cashCode+") " 	
						+" WHERE CREDIT_PAYFLAG = 'N' AND CREDIT_PERIODICITY = 'M'  "
						+" ORDER BY CREDIT_CODE";
						cashAmtObj = getSqlModel().getSingleResult(cashAmtQuery);
						
						String promAmt= " SELECT NVL(NEW_AMT,0),NVL(NEW_AMT-OLD_AMT,'0'),CREDIT_CODE FROM HRMS_PROMOTION_SALARY " 
									   +" RIGHT JOIN HRMS_CREDIT_HEAD on(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE AND PROM_CODE = "+promCode[i]+") "
									   +" WHERE CREDIT_PAYFLAG = 'N' AND CREDIT_PERIODICITY = 'M' " 
									   +" ORDER BY CREDIT_CODE "; 
						promAmtObj = getSqlModel().getSingleResult(promAmt);
						logger.info("cashAmtObj[0][0] = "+cashAmtObj[0][0]+" promAmtObj[0][2] = "+promAmtObj[0][2]);
						
						if(String.valueOf(cashAmtObj[0][0]).equals(String.valueOf(promAmtObj[0][2])))
						{
							if(!String.valueOf(cashAmtObj[0][1]).equals(String.valueOf(promAmtObj[0][2])))
							{
								arrDays[i]  = Double.parseDouble(String.valueOf(cashCodeObj[j][1]));
							}
						}
						
					} // END J LOOP CASHCODEOBJ
				
					int j = 4;
					recalObj[i] = new Object[cashAmtObj.length + 6];
					recalObj[i][0] = promCode[i];
					recalObj[i][1] = empId[i];
					recalObj[i][2] = empObj[0][0];
					recalObj[i][3] = empObj[0][1];
					if(cashAmtObj != null)
					for (int k = 0; k < cashAmtObj.length; k++) {
						double value = Double.parseDouble(""+promAmtObj[k][1]) / 30.0 * arrDays[i];
						recalObj[i][j++] = Math.round(value);
						totAmt+=value;
					} // END K LOOP CASHAMTOBJ
					recalObj[i][j++] = Math.round(totAmt);
					recalObj[i][j++] = arrDays[i];
				}   // END IF CASHCODEOBJ != NULL
			}
			return recalObj;
		} catch (Exception e) {
			logger.error("Exception in cash arrears "+e);
			return null;
		}
	} // end of method
	
	public void reportCashArrears(CashArrears bean, HttpServletResponse response)
	{
		String repHead="\nCASH ARREARS REPORT\n\n";
		
	}

}
