/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.srd.AccMISReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Reeba_Joseph
 * @date 30-11-2009
 */
public class AccMISReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	public String getReport(AccMISReport bean, HttpServletResponse response,
			String[] labelNames, HttpServletRequest request) {
		try {
			String reportType = "";
			if (bean.getReportType().equals("P")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (bean.getReportType().equals("T")) {
				reportType = "Txt";
			}

			String reportName = "";

			if (!bean.getReportTitle().equals("")) {
				System.out.println("REPORTNAME--1==" + bean.getReportTitle());
				reportName = bean.getReportTitle();
			} else {
				System.out.println("REPORTNAME==" + reportName);
				reportName = "Accountant MIS Report";
				System.out.println("REPORTNAME=2=" + reportName);
			}
			System.out.println("reportName-------IN VIEW METHOD-------"
					+ reportName);
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName, "landscape");
			rg.addText("\n", 0, 0, 0);

			logger.info("multi select values  : " + bean.getHiddenColumns());
			String mutliSelectValues = bean.getHiddenColumns();
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0,
						mutliSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}
			int count = 0;
			logger.info("counter initial value==========" + count);
			Object[][] creditsObj = null;
			Object[][] debitsObj = null;
			// SELECT QUERY WITH COUNT (COUNT APPENDED AFTER #)
			String queryWithCount = selectQuery(bean, labelNames, count,
					splitComma, request);
			// SPLIT TO GET COUNT VALUE
			String[] splitquery = queryWithCount.split("#");

			String query = splitquery[0];
			count = Integer.parseInt(splitquery[1]);
			logger.info("counter after select query==========" + count);

			if (bean.getCtcFlag().equals("true")) {
				count++;
			}

			if (bean.getSalaryFlag().equals("true")) {

				String creditQuery = "SELECT CREDIT_NAME||' ('||DECODE(CREDIT_PERIODICITY,'M','Monthly','A','Annually','H','Half Yearly','Q','Quarterly' )||')' AS NAME FROM HRMS_CREDIT_HEAD "
						+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
				creditsObj = getSqlModel().getSingleResult(creditQuery);
				if (creditsObj != null) {
					count = count + creditsObj.length;
				}

			}
			if (bean.getDebitFlag().equals("true")) {
				String debitQuery = "SELECT DEBIT_NAME||' ('||DECODE(DEBIT_PERIODICITY,'M','Monthly','A','Annually','H','Half Yearly','Q','Quarterly' )||')' AS NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";
				debitsObj = getSqlModel().getSingleResult(debitQuery);
				if (debitsObj != null) {
					count = count + debitsObj.length;
				}
			}
			logger.info("counter after ctc & salary==========" + count);

			String[] str_colNames = new String[count + 1];
			str_colNames[0] = "Employee Code";
			int str_colNames_array = 0;
			int[] cellWidth = new int[count + 1];
			cellWidth[0] = 10;
			int cellWidth_array = 0;
			int[] cellAlign = new int[count + 1];
			cellAlign[0] = 0;
			int cellAlign_array = 0;

			// QUERY APPENDED WITH CONDITIONS
			query += conditionQuery(bean, labelNames);

			Object[][] finalObj = null;
			Object[][] reportObj = getSqlModel().getSingleResult(query);
			Object[][] ctcObj = null;
			if (bean.getCtcFlag().equals("true")) {

				if (reportObj != null && reportObj.length > 0) {
					ctcObj = new Object[reportObj.length][reportObj[0].length];
					String pfIncludeFlag = " SELECT EMP_ID,SAL_EPF_FLAG FROM HRMS_SALARY_MISC "
							+ " WHERE ";
					String strAppend = getValue(reportObj,
							reportObj[0].length - 1, "EMP_ID", 0);
					pfIncludeFlag += strAppend;

					HashMap map = (HashMap) getSqlModel().getSingleResultMap(
							pfIncludeFlag, 0, 0);

					for (int i = 0; i < reportObj.length; i++) {
						for (int j = 0; j < reportObj[0].length - 1; j++) {
							ctcObj[i][j] = reportObj[i][j];
						}
						String checkMap = "N";

						try {
							Object[] obj_pf = (Object[]) map
									.get(String
											.valueOf(reportObj[i][reportObj[0].length - 1]));	
							if(obj_pf!=null &&obj_pf.length >0 && !String.valueOf(obj_pf[1]).equals("null")){
								checkMap = String.valueOf(obj_pf[1]);
							}
							System.out.println("checkMap : " + checkMap);
						} catch (Exception e) {
							e.printStackTrace();
						}
						ctcObj[i][ctcObj[0].length - 1] = getCTC(
								String
										.valueOf(reportObj[i][reportObj[0].length - 1]),
								checkMap);
						System.out.println("Total sum is       :"
								+ ctcObj[i][ctcObj[0].length - 1]);
					}
				}
			}
			if (bean.getSalaryFlag().equals("true")
					&& bean.getDebitFlag().equals("true")) {
				if (bean.getCtcFlag().equals("true")) {
					if (ctcObj != null && ctcObj.length > 0) {
						finalObj = new Object[ctcObj.length][ctcObj[0].length
								+ creditsObj.length + debitsObj.length + 1];
						for (int i = 0; i < ctcObj.length; i++) {
							for (int j = 0; j < ctcObj[0].length; j++) {
								finalObj[i][j] = ctcObj[i][j];
							}
						}
					}
				} else {
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length
								+ creditsObj.length + debitsObj.length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length - 1; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
				}
				if (reportObj != null && reportObj.length > 0) {

					for (int i = 0; i < reportObj.length; i++) {
						String credAmtQuery = "SELECT CREDIT_NAME,NVL(CREDIT_AMT,0) FROM HRMS_CREDIT_HEAD "
								+ " LEFT JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "
								+ " AND HRMS_EMP_CREDIT.EMP_ID ="
								+ reportObj[i][reportObj[0].length - 1]
								+ " )"
								+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
						Object[][] credAmtObj = getSqlModel().getSingleResult(
								credAmtQuery);
						int lastCount = 0;
						if (bean.getCtcFlag().equals("true")) {
							lastCount = ctcObj[0].length;
						} else
							lastCount = reportObj[0].length - 1;
						for (int j = 0; j < credAmtObj.length; j++) {
							finalObj[i][lastCount] = credAmtObj[j][1];
							lastCount++;
						}

						String debitAmtQuery = "SELECT DEBIT_NAME,NVL(DEBIT_AMT,0) FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON(HRMS_EMP_DEBIT.DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE AND HRMS_EMP_DEBIT.EMP_ID = "
								+ reportObj[i][reportObj[0].length - 1]
								+ " )"
								+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
						Object[][] debitAmtObj = getSqlModel().getSingleResult(
								debitAmtQuery);
						for (int j = 0; j < debitAmtObj.length; j++) {
							finalObj[i][lastCount] = debitAmtObj[j][1];
							lastCount++;
						}
					}
				}
			}
			else if (bean.getSalaryFlag().equals("true")) {
				if (bean.getCtcFlag().equals("true")) {
					if (ctcObj != null && ctcObj.length > 0) {
						finalObj = new Object[ctcObj.length][ctcObj[0].length
								+ creditsObj.length + 1];
						for (int i = 0; i < ctcObj.length; i++) {
							for (int j = 0; j < ctcObj[0].length; j++) {
								finalObj[i][j] = ctcObj[i][j];
							}
						}
					}
				} else {
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length
								+ creditsObj.length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length - 1; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
				}

				if (reportObj != null && reportObj.length > 0) {
					for (int i = 0; i < reportObj.length; i++) {
						String credAmtQuery = "SELECT CREDIT_NAME,NVL(CREDIT_AMT,0) FROM HRMS_CREDIT_HEAD "
								+ " LEFT JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "
								+ " AND HRMS_EMP_CREDIT.EMP_ID ="
								+ reportObj[i][reportObj[0].length - 1]
								+ " )"
								+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
						Object[][] credAmtObj = getSqlModel().getSingleResult(
								credAmtQuery);
						int lastCount = 0;
						if (bean.getCtcFlag().equals("true")) {
							lastCount = ctcObj[0].length;
						} else
							lastCount = reportObj[0].length - 1;
						for (int j = 0; j < credAmtObj.length; j++) {
							finalObj[i][lastCount] = credAmtObj[j][1];
							lastCount++;
						}
					}
				}
			}
			else if (bean.getDebitFlag().equals("true")) {

				if (bean.getCtcFlag().equals("true")) {
					if (ctcObj != null && ctcObj.length > 0) {
						finalObj = new Object[ctcObj.length][ctcObj[0].length
								+ debitsObj.length + 1];
						for (int i = 0; i < ctcObj.length; i++) {
							for (int j = 0; j < ctcObj[0].length; j++) {
								finalObj[i][j] = ctcObj[i][j];
							}
						}
					}
				} else {
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length
								+ debitsObj.length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length - 1; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
				}

				if (reportObj != null && reportObj.length > 0) {
					for (int i = 0; i < reportObj.length; i++) {
						String debitAmtQuery = "SELECT DEBIT_NAME,NVL(DEBIT_AMT,0) FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON(HRMS_EMP_DEBIT.DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE AND HRMS_EMP_DEBIT.EMP_ID = "
								+ reportObj[i][reportObj[0].length - 1]
								+ " )"
								+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
						Object[][] debitAmtObj = getSqlModel().getSingleResult(
								debitAmtQuery);
						int lastCount = 0;
						if (bean.getCtcFlag().equals("true")) {
							lastCount = ctcObj[0].length;
						} else
							lastCount = reportObj[0].length - 1;

						for (int j = 0; j < debitAmtObj.length; j++) {
							finalObj[i][lastCount] = debitAmtObj[j][1];
							lastCount++;
						}
					}
				}

			} else {
				// if (reportObj != null && reportObj.length > 0) {
				if (bean.getCtcFlag().equals("false")) {
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
				} else {
					if (ctcObj != null && ctcObj.length > 0) {
						finalObj = new Object[ctcObj.length][ctcObj[0].length + 1];
						for (int i = 0; i < ctcObj.length; i++) {
							for (int j = 0; j < ctcObj[0].length; j++) {
								finalObj[i][j] = ctcObj[i][j];
							}
						}
					}

				}
			}
			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			/*Sequence of Label is same as report() method in Action*/
			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);

					//NAME
					if (splitDash[1].equals(labelNames[0])) {
						str_colNames[++str_colNames_array] = labelNames[0];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					//DIVISION
					else if (splitDash[1].equals(labelNames[1])) {
						str_colNames[++str_colNames_array] = labelNames[1];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					//DEPARTMENT
					else if (splitDash[1].equals(labelNames[2])) {
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}
					//BRANCH
					else if (splitDash[1].equals(labelNames[3])) {
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					//DESIGNATION
					else if (splitDash[1].equals(labelNames[4])) {
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}
					//EMPLOYEE TYPE
					else if (splitDash[1].equals(labelNames[5])) {
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					// EMPLOYEE GRADE
					else if (splitDash[1].equals(labelNames[6])) {
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// FOR SHIFT - ADDED BY REEBA
					else if (splitDash[1].equals(labelNames[7])) {
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// DATE OF BIRTH
					else if (splitDash[1].equals(labelNames[8])) {
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// DATE OF JOINING
					else if (splitDash[1].equals(labelNames[9])) {
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					// FOR DATE OF CONFIRMATION
					else if (splitDash[1].equals(labelNames[10])) {
						str_colNames[++str_colNames_array] = labelNames[10];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// DATE OF LEAVING
					else if (splitDash[1].equals(labelNames[11])) {
						str_colNames[++str_colNames_array] = labelNames[11];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// SERVICE TENURE
					else if (splitDash[1].equals(labelNames[12])) {
						str_colNames[++str_colNames_array] = labelNames[12];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// EMPLOYEE STATUS
					else if (splitDash[1].equals(labelNames[13])) {
						str_colNames[++str_colNames_array] = labelNames[13];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// REPORTING TO
					else if (splitDash[1].equals(labelNames[14])) {
						str_colNames[++str_colNames_array] = labelNames[14];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// SALARY GRADE
					else if (splitDash[1].equals(labelNames[15])) {
						str_colNames[++str_colNames_array] = labelNames[15];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					//GROUP JOINING DATE
					else if (splitDash[1].equals(labelNames[16])) {
						str_colNames[++str_colNames_array] = labelNames[16];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					// TRADE
					else if (splitDash[1].equals(labelNames[17])) {
						str_colNames[++str_colNames_array] = labelNames[17];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}
					
					// GENDER
					else if (splitDash[1].equals(labelNames[18])) {
						str_colNames[++str_colNames_array] = labelNames[18];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					// MOBILE NO
					else if (splitDash[1].equals(labelNames[19])) {
						str_colNames[++str_colNames_array] = labelNames[19];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					// EMAIL ID
					else if (splitDash[1].equals(labelNames[20])) {
						str_colNames[++str_colNames_array] = labelNames[20];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					// AGE
					else if (splitDash[1].equals(labelNames[21])) {
						str_colNames[++str_colNames_array] = labelNames[21];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					// PF NUMBER
					else if (splitDash[1].equals(labelNames[22])) {
						str_colNames[++str_colNames_array] = labelNames[22];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// PAN  NUMBER
					else if (splitDash[1].equals(labelNames[23])) {
						str_colNames[++str_colNames_array] = labelNames[23];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// ESIC NUMBER
					else if (splitDash[1].equals(labelNames[24])) {
						str_colNames[++str_colNames_array] = labelNames[24];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// PAY MODE
					else if (splitDash[1].equals(labelNames[25])) {
						str_colNames[++str_colNames_array] = labelNames[25];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					// SALARY ACCOUNT
					else if (splitDash[1].equals(labelNames[26])) {
						str_colNames[++str_colNames_array] = labelNames[26];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// SALARY BANK NAME
					else if (splitDash[1].equals(labelNames[27])) {
						str_colNames[++str_colNames_array] = labelNames[27];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// REIMBURSEMENT ACCOUNR NO
					else if (splitDash[1].equals(labelNames[28])) {
						str_colNames[++str_colNames_array] = labelNames[28];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// REIMBURSEMENT BANK NAME
					else if (splitDash[1].equals(labelNames[29])) {
						str_colNames[++str_colNames_array] = labelNames[29];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					// SALARY
					/*else if (splitDash[1].equals(labelNames[30])) {
						str_colNames[++str_colNames_array] = labelNames[30];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}*/
					// CTC
					/*else if (splitDash[1].equals(labelNames[31])) {
						str_colNames[++str_colNames_array] = labelNames[31];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}*/
					// ACCOUNTING CATEGORY
					else if (splitDash[1].equals(labelNames[32])) {
						str_colNames[++str_colNames_array] = labelNames[32];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}
					// COST CENTER
					else if (splitDash[1].equals(labelNames[33])) {
						str_colNames[++str_colNames_array] = labelNames[33];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}
					// SUBCOST CENTER
					else if (splitDash[1].equals(labelNames[34])) {
						str_colNames[++str_colNames_array] = labelNames[34];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}
					// CUSTOMER REFERENCE NUMBER
					else if (splitDash[1].equals(labelNames[35])) {
						str_colNames[++str_colNames_array] = labelNames[35];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					// PAYBILL NAME
					else if (splitDash[1].equals(labelNames[36])) {
						str_colNames[++str_colNames_array] = labelNames[36];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					// DEBIT
				/*	else if (splitDash[1].equals(labelNames[37])) {
						str_colNames[++str_colNames_array] = labelNames[37];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}*/
					// GRATUITY ID

					else if (splitDash[1].equals(labelNames[38])) {
						str_colNames[++str_colNames_array] = labelNames[38];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}
					// PENSION ACCOUNT NUMBER
					else if (splitDash[1].equals(labelNames[39])) {
						str_colNames[++str_colNames_array] = labelNames[39];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}

					//PENSION BANK NAME
					else if (splitDash[1].equals(labelNames[40])) {
						str_colNames[++str_colNames_array] = labelNames[40];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
						
					} 
					//ROLE
					else if (splitDash[1].equals(labelNames[41])) {
						str_colNames[++str_colNames_array] = labelNames[41];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
						
					} /*else if (splitDash[1].equals(labelNames[41])) {
						str_colNames[++str_colNames_array] = labelNames[41];
						cellWidth[++cellWidth_array] = 10;
						cellAlign[++cellAlign_array] = 0;
					}*/
				}
			}

			if (bean.getCtcFlag().equals("true")) {
				str_colNames[++str_colNames_array] = "CTC";
				cellWidth[++cellWidth_array] = 15;
				cellAlign[++cellAlign_array] = 0;
			}

			if (bean.getSalaryFlag().equals("true")) {
				if (creditsObj != null) {
					for (int i = 0; i < creditsObj.length; i++) {
						str_colNames[++str_colNames_array] = String
								.valueOf(creditsObj[i][0]);
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

				}
			}
			if (bean.getDebitFlag().equals("true")) {
				if (debitsObj != null) {
					for (int i = 0; i < debitsObj.length; i++) {
						str_colNames[++str_colNames_array] = String
								.valueOf(debitsObj[i][0]);
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

				}
			}

			Object[][] objData1 = null;
			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];
				if (bean.getCasteFlag().equals("true")) {
					objData1 = new Object[finalObj.length][count + 2];
				}
				logger.info("objData1 length" + objData1.length);
				for (int i = 0; i < finalObj.length; ++i) {
					int int_count = 0;
					objData1[i][0] = checkNull(String.valueOf(finalObj[i][0]));// Employee Token
					if (bean.getEmpNameFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));// EMPLOYEE NAME
					}
					//DIVISION
					if (bean.getDivFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//DEPARTMENT
					if (bean.getDeptFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//BRANCH
					if (bean.getCenterFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//DESIGNATION
					if (bean.getRankFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));// Employee Name
					}
					//EMPLOYEE TYPE
					if (bean.getTypeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					
					// EMPLOYEE GRADE
					if (bean.getEmpGradeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}						
					//SHIFT
					if (bean.getShiftFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//DATE OF BIRTH					
					if (bean.getDateBirthFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//DATE OF JOINING
					if (bean.getDateRegFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//DATE OFCONFIRMATION
					if (bean.getDateOfConf().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//DATE OF LEAVING
					if (bean.getDateOfSepFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// SERVICE TENURE
					if (bean.getYearexperienceFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// EMPLOYEE STATUS
					if (bean.getEmpStatusFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// REPORTING TO
					if (bean.getReportingToFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					
					//SALARY GRADE
					if (bean.getGradeFlage().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// FOR GROUP JOIN DATE
					if (bean.getGroupjoindateFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					
					//TRADE
					if (bean.getTradeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					
					// GENDER
					if (bean.getGenderFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// MOBILE NO
					if (bean.getMobFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// EMAIL ID
					if (bean.getEmailFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// AGE
					if (bean.getAgeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					
					//PF NUMBER
					if (bean.getPfNoFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//PAN NUMBER
					if (bean.getPanNoFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//ESIC NUMBER
					if (bean.getEsicNoFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//PAY MODE
					if (bean.getPayModeFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//SALARY ACCOUNT NUMBER
					if (bean.getSalAccFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//SALARY BANK NAME
					if (bean.getSalBankFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					/*
					 * if (bean.getSalBankFlag().equals("true")) {
					 * objData1[i][++int_count] = checkNull(String
					 * .valueOf(finalObj[i][int_count])); }
					 */
					//REIMBURSEMENT	ACCOUNT NUMBER				
					if (bean.getReimbAccFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//REIMBURSEMENT BANK NAME
					if (bean.getReimbBankFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					/*
					 * if (bean.getReimbBankFlag().equals("true")) {
					 * objData1[i][++int_count] = checkNull(String
					 * .valueOf(finalObj[i][int_count])); }
					 */
					// SALARY
					if (bean.getSalaryFlag().equals("true")) {

						for (int j = 0; j < creditsObj.length; j++) {
							objData1[i][++int_count] = checkNull(String
									.valueOf(finalObj[i][int_count]));
						}
					}
					// CTC
					if (bean.getCtcFlag().equals("true")) {

						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// ACCOUNTING CATEGORY
					if (bean.getAccCategoryFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// COST CENTER
					if (bean.getCostCenterFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// SUB COST CENTER
					if (bean.getSubCostCenterFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
													
					//CUSTOMER REFERENCE NO
					if (bean.getCustomerRefNoFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//PAY BILL
					if (bean.getPaybillFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					
					// DEBIT
					if (bean.getDebitFlag().equals("true")) {
						for (int j = 0; j < debitsObj.length; j++) {
							objData1[i][++int_count] = checkNull(String
									.valueOf(finalObj[i][int_count]));
						}
					}
					//GRATUITY ID
					if (bean.getGratuityIDFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//PENSION ACCOUNT NUMBER
					if (bean.getPensionAccNoFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					//PENSION BANK NAME
					if (bean.getPensionBankFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}
					// ROLE
					if (bean.getRoleFlag().equals("true")) {
						objData1[i][++int_count] = checkNull(String
								.valueOf(finalObj[i][int_count]));
					}

				}

				logger.info("Export all values   : " + bean.getExportAll());
				logger.info("counter for exporting=========="
						+ bean.getReqStatus());
				if (bean.getReqStatus().trim().equals("R"))
					bean.setExportAll("on");
				if (bean.getExportAll().equals("on")) {
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						// rg.setFName(reportName + ".xls");
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					}
				} else {
					String[] pageIndex = Utility.doPaging(bean.getMyPage(),
							objData1.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					int columnLength = count + 1;
					logger.info("columnLength   : " + columnLength);
					Object[][] pageObj = new Object[numOfRec][columnLength];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < columnLength; j++) {
							// logger.info("objData1["+i+"]["+j+"] :
							// "+objData1[i][j]);
							pageObj[z][j] = objData1[i][j];
							// pageObj[z][0] = String.valueOf(srNo);
						}
						z++;
						srNo++;
					}
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					}
				}

			} else {
				rg.addFormatedText("There is no data to display.", 0, 1, 1, 0);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public double getCTC(String empid, String pfFlag) {
		double annualSum = 0.0;
		try {
			String query = " SELECT  HRMS_CREDIT_HEAD.CREDIT_CODE ,HRMS_CREDIT_HEAD.CREDIT_NAME,case when CREDIT_PERIODICITY ='M' then  NVL(CREDIT_AMT,0) * 12 "
					+ " when CREDIT_PERIODICITY ='Q' then  NVL(CREDIT_AMT,0) * 4 when CREDIT_PERIODICITY ='H' then  NVL(CREDIT_AMT,0) * 2  "
					+ " when CREDIT_PERIODICITY ='A' then  NVL(CREDIT_AMT,0) * 1 else  0 end as amt, EMP_ID FROM HRMS_CREDIT_HEAD "
					+ " LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE)  where EMP_ID="
					+ empid
					+ " AND CREDIT_IS_CTC_COMPONENT='Y'  ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
			String query1 = "SELECT  HRMS_PERQUISITE_HEAD.PERQ_CODE ,HRMS_PERQUISITE_HEAD.PERQ_NAME,case when PERQ_PERIOD ='M' then  NVL(PERQ_AMT,0) * 12 "
					+ " when PERQ_PERIOD ='Q' then  NVL(PERQ_AMT,0) * 4 when PERQ_PERIOD ='H' then  NVL(PERQ_AMT,0) * 2  "
					+ " when PERQ_PERIOD ='A' then  NVL(PERQ_AMT,0) * 1 else  0 end as amt, EMP_ID FROM HRMS_PERQUISITE_HEAD "
					+ " LEFT JOIN HRMS_EMP_PERQUISITE ON (HRMS_PERQUISITE_HEAD.PERQ_CODE=HRMS_EMP_PERQUISITE.PERQ_CODE)   where EMP_ID="
					+ empid + "   ORDER BY HRMS_EMP_PERQUISITE.PERQ_CODE ";
			Object salaryquery[][] = getSqlModel().getSingleResult(query);
			for (int i = 0; i < salaryquery.length; i++) {
				annualSum += Double.parseDouble(String
						.valueOf(salaryquery[i][2]));
				/*
				 * if (pfFlag.equals("Y")) { if
				 * (String.valueOf(salaryquery[i][0]).equals("1")) {
				 * 
				 * annualSum += (Double.parseDouble(String
				 * .valueOf(salaryquery[i][2]))) * .12; } //end of if } //end of
				 * if
				 */} // end of loop
			Object salaryquery1[][] = getSqlModel().getSingleResult(query1);
			for (int j = 0; j < salaryquery1.length; j++) {
				annualSum += Double.parseDouble(String
						.valueOf(salaryquery1[j][2]));
			}
		} catch (Exception e) {
			logger.error("exception in getCtc", e);
		} // end of catch
		return Math.round(annualSum);

	}

	public String selectQuery(AccMISReport bean, String[] labelNames,
			int count, String[] splitComma, HttpServletRequest request) {
		String labels = "Employee Code,";
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ";

		if (splitComma != null) {
			LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			/*
			 * for(int i=0;i < labelNames.length;i++) { System.out.println("The
			 * lable labelNames["+i+"] Name : "+labelNames[i]); }
			 */

			/*Sequence of Label is keep as viewOnScreen and report method in action*/
			for (int i = 0; i < splitComma.length; i++) {
				String splitDash[] = splitComma[i].split("-");
				if (splitDash[1].trim().equals(labelNames[0].trim())) {
					columnMap
							.put(
									Integer.parseInt(splitDash[0]),
									"NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||"
											+ "NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')");
					labelMap.put(1, labelNames[0]);
					count++;
				}
				// DIVISION
				else if (splitDash[1].equals(labelNames[1])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(DIV_NAME,' ')");
					labelMap.put(2, labelNames[1]);
					count++;
				}
				// DEPARTMENT
				else if (splitDash[1].equals(labelNames[2])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(DEPT_NAME,' ')");
					labelMap.put(3, labelNames[2]);
					count++;
				}
				// BRANCH
				else if (splitDash[1].equals(labelNames[3])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(CENTER_NAME,' ')");
					labelMap.put(4, labelNames[3]);
					count++;
				}
				// DESIGNATION
				else if (splitDash[1].equals(labelNames[4])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(RANK_NAME,' ')");
					labelMap.put(5, labelNames[4]);
					count++;
				}
				// EMPLOYEE TYPE
				else if (splitDash[1].equals(labelNames[5])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_EMP_TYPE.TYPE_NAME,' ')");
					count++;
					labelMap.put(6, labelNames[5]);
				}
				// EMPLOYEE GRADE
				else if (splitDash[1].equals(labelNames[6])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_CADRE.CADRE_NAME,' ')");
					labelMap.put(7, labelNames[6]);
					count++;
				}
				// FOR SHIFT - ADDED BY REEBA
				else if (splitDash[1].equals(labelNames[7])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SHIFT.SHIFT_NAME,' ')");
					labelMap.put(8, labelNames[7]);
					count++;
				}
				// DATE OF BIRTH
				else if (splitDash[1].equals(labelNames[8])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'"
									+ getSelectedDateFormat(bean) + "')");
					labelMap.put(9, labelNames[8]);
					count++;
				}

				// DATE OF JOINING
				else if (splitDash[1].equals(labelNames[9])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'"
									+ getSelectedDateFormat(bean) + "')");
					labelMap.put(10, labelNames[9]);
					count++;
				}
				// DATE OF CONFIRMATION
				else if (splitDash[1].equals(labelNames[10])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" TO_CHAR(HRMS_EMP_OFFC.EMP_CONFIRM_DATE,'"
									+ getSelectedDateFormat(bean) + "')");
					labelMap.put(11, labelNames[10]);
					count++;
				}
				// DATE OF LEAVING
				else if (splitDash[1].equals(labelNames[11])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'"
									+ getSelectedDateFormat(bean) + "')");
					labelMap.put(12, labelNames[11]);
					count++;
				}
				// SERVICE TENURE
				else if (splitDash[1].equals(labelNames[12])) {
					columnMap
							.put(
									Integer.parseInt(splitDash[0]),
									" CASE WHEN FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)<0 THEN '0 YEARS 0 MONTHS' "
											+ " ELSE FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)||' '||'YEARS'||' '|| "
											+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)- "
											+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12) "
											+ " ||' '||'MONTHS' END AS EXPERIENCE");
					labelMap.put(13, labelNames[12]);
					count++;
				}

				// EMPLOYEE STATUS
				else if (splitDash[1].equals(labelNames[13])) {
					columnMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retired','N','Resigned','E','Terminated'),' ')");
					labelMap.put(14, labelNames[13]);
					count++;
				}

				// REPORTING TO
				else if (splitDash[1].equals(labelNames[14])) {
					columnMap
							.put(
									Integer.parseInt(splitDash[0]),
									" NVL(OFF1.EMP_FNAME,' ')||' '||NVL(OFF1.EMP_MNAME,' ')||' '||NVL(OFF1.EMP_LNAME,' ')");
					labelMap.put(15, labelNames[14]);
					count++;
				}

				// SALARY GRADE
				else if (splitDash[1].equals(labelNames[15])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALGRADE_HDR.SALGRADE_TYPE,' ')");
					labelMap.put(16, labelNames[15]);
					count++;
				}

				// GROUP JOINING DATE
				else if (splitDash[1].equals(labelNames[16])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							"  NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE,'"
									+ getSelectedDateFormat(bean) + "'),' ') ");
					labelMap.put(17, labelNames[16]);
					count++;
				}

				// TRADE
				else if (splitDash[1].equals(labelNames[17])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_TRADE.TRADE_NAME,' ')");
					labelMap.put(18, labelNames[17]);
					count++;
				}

				// GENDER
				else if (splitDash[1].equals(labelNames[18])) {
					columnMap
							.put(Integer.parseInt(splitDash[0]),
									" DECODE(HRMS_EMP_OFFC.EMP_GENDER,'M','Male','F','Female','O','Other')");

					labelMap.put(19, labelNames[18]);
					count++;
				}

				// MOBILE NUMBER
				else if (splitDash[1].equals(labelNames[19])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_EMP_ADDRESS.ADD_MOBILE, ' ')");
					labelMap.put(20, labelNames[19]);
					count++;
				}

				// EMAIL ID
				else if (splitDash[1].equals(labelNames[20])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							"NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ' )");
					labelMap.put(21, labelNames[20]);
					count++;
				}
				// AGE
				else if (splitDash[1].equals(labelNames[21])) {
					columnMap
							.put(Integer.parseInt(splitDash[0]),
									"ROUND( (SYSDATE - HRMS_EMP_OFFC.EMP_DOB)/365.24,0)");
					labelMap.put(22, labelNames[21]);
					count++;
				}

				// PF NUMBER
				else if (splitDash[1].equals(labelNames[22])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALARY_MISC.SAL_GPFNO,' ')");
					labelMap.put(23, labelNames[22]);
					count++;
				}

				// PAN NUMBER
				else if (splitDash[1].equals(labelNames[23])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALARY_MISC.SAL_PANNO,' ')");
					labelMap.put(24, labelNames[23]);
					count++;
				}

				// ESIC NUMBER
				else if (splitDash[1].equals(labelNames[24])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALARY_MISC.SAL_ESCINUMBER,' ')");
					labelMap.put(25, labelNames[24]);
					count++;
				}

				// PAY MODE
				else if (splitDash[1].equals(labelNames[25])) {
					columnMap
							.put(
									Integer.parseInt(splitDash[0]),
									" DECODE(HRMS_SALARY_MISC.SAL_PAY_MODE,'T','Transfer','C','Cash','H','Cheque',' ')");
					labelMap.put(26, labelNames[25]);
					count++;
				}

				// SALARY ACCOUNT NUMBER
				else if (splitDash[1].equals(labelNames[26])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' ')");
					labelMap.put(27, labelNames[26]);
					count++;
				}
				// SALARY BANK NAME
				else if (splitDash[1].equals(labelNames[27])) {
					columnMap
							.put(
									Integer.parseInt(splitDash[0]),
									" CASE WHEN (HRMS_SALARY_MISC.SAL_MICR_REGULAR IS NULL AND B1.BANK_NAME IS NULL) "
											+ " THEN '' "
											+ " WHEN B1.BANK_NAME IS NULL THEN TO_CHAR(HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
											+ " WHEN HRMS_SALARY_MISC.SAL_MICR_REGULAR IS NULL THEN TO_CHAR(B1.BANK_NAME) "
											+ " ELSE TO_CHAR(HRMS_SALARY_MISC.SAL_MICR_REGULAR||'-'||B1.BANK_NAME) "
											+ " END AS NAME");

					labelMap.put(28, labelNames[27]);
					count++;
				}
				/*
				 * else if (splitDash[1].equals(labelNames[20])) { // query += "
				 * ,HRMS_BANK.BANK_NAME ";
				 * columnMap.put(Integer.parseInt(splitDash[0]), "
				 * HRMS_BANK.BANK_NAME"); labelMap.put(22, labelNames[20]);
				 * count++; }
				 */

				// REIMBURSEMENT ACCOUNT NUMBER
				else if (splitDash[1].equals(labelNames[28])) {					
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALARY_MISC.SAL_REIMBMENT ,' ')");
					labelMap.put(29, labelNames[28]);
					count++;
				}
				// REIMBURSEMENT BANK NAME
				else if (splitDash[1].equals(labelNames[29])) {
					columnMap
							.put(
									Integer.parseInt(splitDash[0]),
									" CASE WHEN (HRMS_SALARY_MISC.SAL_REIMBANK IS NULL AND B2.BANK_NAME IS NULL) "
											+ " THEN '' "
											+ " WHEN B2.BANK_NAME IS NULL THEN TO_CHAR(HRMS_SALARY_MISC.SAL_REIMBANK) "
											+ " WHEN HRMS_SALARY_MISC.SAL_REIMBANK IS NULL THEN TO_CHAR(B2.BANK_NAME) "
											+ " ELSE TO_CHAR(HRMS_SALARY_MISC.SAL_REIMBANK||'-'||B2.BANK_NAME) "
											+ " END AS NAME");
					labelMap.put(30, "Reimbursement Bank MICR");
					count++;
				}
				/*
				 * else if (splitDash[1].equals(labelNames[22])) { // query += "
				 * ,HRMS_BANK.BANK_NAME ";
				 * columnMap.put(Integer.parseInt(splitDash[0]), "
				 * HRMS_BANK.BANK_NAME"); labelMap.put(25, labelNames[22]);
				 * count++; }
				 */

				// ACCOUNTING CATEGORY
				else if (splitDash[1].equals(labelNames[32])) {
					columnMap
							.put(Integer.parseInt(splitDash[0]),
									" NVL(HRMS_ACCOUNTING_CATEGORY.ACCOUNT_CATEGORY_NAME,' ')");
					labelMap.put(33, labelNames[32]);
					count++;
				}
				// COST CENTER
				else if (splitDash[1].equals(labelNames[33])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_COST_CENTER.COST_CENTER_NAME,' ')");
					labelMap.put(34, labelNames[33]);
					count++;
				}
				// SUB COST CENTER
				else if (splitDash[1].equals(labelNames[34])) {
					columnMap
							.put(Integer.parseInt(splitDash[0]),
									" NVL(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_NAME,' ')");
					labelMap.put(35, labelNames[34]);
					count++;
				}
				// CUSTOMER REFERENCE NUMBER
				else if (splitDash[1].equals(labelNames[35])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALARY_MISC.CUSTOMER_REF_NO,' ')");
					labelMap.put(36, labelNames[35]);
					count++;
				}
				// PAY BILL NAME
				else if (splitDash[1].equals(labelNames[36])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ')");
					labelMap.put(37, labelNames[36]);
					count++;
				}

				// GRATUITY ID
				else if (splitDash[1].equals(labelNames[38])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALARY_MISC.SAL_GRATUITY_ACCNO,' ')");
					labelMap.put(39, labelNames[38]);
					count++;
				}

				// PENSION ACCOUNT NUMBER
				else if (splitDash[1].equals(labelNames[39])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_SALARY_MISC.SAL_PENSION_ACCNO,' ')");
					labelMap.put(40, labelNames[39]);
					count++;
				}
				// PENSION BANK NAME
				else if (splitDash[1].equals(labelNames[40])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(B3.BANK_NAME,' ')");
					labelMap.put(41, labelNames[40]);
					count++;
				}
				// ROLE
				else if (splitDash[1].equals(labelNames[41])) {
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(HRMS_EMP_OFFC.EMP_ROLE,' ')");
					labelMap.put(42, labelNames[41]);
					count++;
				}
			}

			Iterator<Integer> iterator = columnMap.keySet().iterator();
			while (iterator.hasNext()) {
				String mapValues = (String) columnMap.get(iterator.next());
				logger.info("mapValues : " + mapValues);
				query += "," + mapValues;
			}

			Iterator<Integer> labelIter = labelMap.keySet().iterator();
			String labelValues = "";
			while (labelIter.hasNext()) {
				labelValues = (String) labelMap.get(labelIter.next());
				logger.info("labelValues : " + labelValues);
				labels += labelValues + ",";
			}

		}
		query += " ,HRMS_EMP_OFFC.EMP_ID " + "#" + count + "#" + labels;

		return query;
	}

	public String conditionQuery(AccMISReport bean, String[] labelNames) {
		String query = " FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_EMP_OFFC OFF1 ON(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER=OFF1.EMP_ID)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+ " LEFT JOIN HRMS_TRADE ON(HRMS_TRADE.TRADE_CODE=HRMS_EMP_OFFC.EMP_TRADE)"
				+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
				+ " LEFT JOIN HRMS_DISCIPLINE ON(HRMS_DISCIPLINE.DISCP_ID=HRMS_EMP_OFFC.EMP_DISCIPLINE)"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ " LEFT JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_EMP_OFFC.EMP_SHIFT)"
				+ " LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)"
				+ " LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " LEFT JOIN HRMS_TITLE T2 ON(T2.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " LEFT JOIN HRMS_EMP_PERS ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_ACCOUNTING_CATEGORY ON(HRMS_ACCOUNTING_CATEGORY.ACCOUNT_CATEGORY_ID=HRMS_SALARY_MISC.ACCOUNT_CATEGORY_ID)"
				+ " LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
				+ " LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID "
				+ " AND HRMS_SUB_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID ) "
				+ " LEFT JOIN HRMS_BANK B1 ON(B1.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_MICR_REGULAR)"
				+ " LEFT JOIN HRMS_BANK B2 ON(B2.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_REIMBANK)"
				+ " LEFT JOIN HRMS_BANK B3 ON(B3.BANK_MICR_CODE=HRMS_SALARY_MISC.SAL_PENSION_BANK)"
				// + " LEFT JOIN HRMS_CATG
				// ON(HRMS_EMP_PERS.EMP_CASTE_CATG=HRMS_CATG.CATG_ID) "
				// + " LEFT JOIN HRMS_CAST
				// ON(HRMS_EMP_PERS.EMP_CASTE=HRMS_CAST.CAST_ID) "
				+ " LEFT JOIN HRMS_RELIGION ON(HRMS_RELIGION.RELIGION_ID=HRMS_EMP_PERS.EMP_RELIGION)"
				+ " LEFT JOIN HRMS_INITIAL_APPTMT ON(HRMS_EMP_OFFC.EMP_ID=HRMS_INITIAL_APPTMT.EMP_ID)"
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P')"
				+ " LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_SALGRADE_HDR.SALGRADE_CODE=HRMS_EMP_OFFC.EMP_SAL_GRADE) ";

		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += "  " + " WHERE 1=1 ";
		}

		if (!(bean.getFirstName().equals("")) && !(bean.getFirstName() == null)
				&& !bean.getFirstName().equals("null")) {

			query += " AND UPPER(HRMS_EMP_OFFC.EMP_FNAME) LIKE '%"
					+ bean.getFirstName().trim().toUpperCase() + "%'";
		}
		if (!(bean.getLastName().equals("")) && !(bean.getLastName() == null)
				&& !bean.getLastName().trim().equals("null")) {

			query += " AND UPPER(HRMS_EMP_OFFC.EMP_LNAME) LIKE '%"
					+ bean.getLastName().toUpperCase() + "%'";
		}
		if (!(bean.getGender().equals("1")) && !(bean.getGender() == null)
				&& !bean.getGender().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_GENDER= '"
					+ bean.getGender().trim() + "'";
		}

		if (!(bean.getRankCode().equals("")) && !(bean.getRankCode() == null)
				&& !bean.getRankCode().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_RANK	IN(" + bean.getRankCode()
					+ ")";
		}
		if (!(bean.getDesgCode().equals("")) && !(bean.getDesgCode() == null)
				&& !bean.getDesgCode().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_DESG	IN(" + bean.getDesgCode()
					+ ")";
		}
		if (!(bean.getGradeCode().equals("")) && !(bean.getGradeCode() == null)
				&& !bean.getGradeCode().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_GRADE IN(" + bean.getGradeCode()
					+ ")";
		}
		if (!(bean.getCenterCode().equals(""))
				&& !(bean.getCenterCode() == null)
				&& !bean.getCenterCode().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_CENTER IN(" + bean.getCenterCode()
					+ ")";
		}
		if (!(bean.getDeptCode().equals("")) && !(bean.getDeptCode() == null)
				&& !bean.getDeptCode().equals("null")) {

			query += " AND HRMS_DEPT.DEPT_ID IN(" + bean.getDeptCode() + ")";
		}
		if (!(bean.getDivCode().equals("")) && !(bean.getDivCode() == null)
				&& !bean.getDivCode().equals("null")) {

			query += " AND HRMS_DIVISION.DIV_ID IN(" + bean.getDivCode() + ")";
		}
		if (!(bean.getTypeCode().equals("")) && !(bean.getTypeCode() == null)
				&& !bean.getTypeCode().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_TYPE	 IN(" + bean.getTypeCode()
					+ ")";
		}
		if (!(bean.getStatus().equals("1")) && !(bean.getStatus() == null)
				&& !bean.getStatus().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_STATUS ='"
					+ bean.getStatus().trim() + "'";
		}
		if (!(bean.getReportingID().equals(""))
				&& !(bean.getReportingID() == null)
				&& !bean.getReportingID().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER IN("
					+ bean.getReportingID() + ")";
		}
		if (!(bean.getBornFlag().equals("")) && !(bean.getBornFlag() == null)
				&& !bean.getBornFlag().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_BORN	 ='"
					+ bean.getBornFlag().trim() + "'";
		}
		if (!(bean.getPayBillId().equals("")) && !(bean.getPayBillId() == null)
				&& !bean.getPayBillId().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN(" + bean.getPayBillId()
					+ ")";
		}
		if (!(bean.getShiftCode().equals("")) && !(bean.getShiftCode() == null)
				&& !bean.getShiftCode().equals("null")) {

			query += " AND HRMS_EMP_OFFC.EMP_SHIFT IN(" + bean.getShiftCode()
					+ ")";
		}
		if (!(bean.getMaritalStatus().equals("1"))
				&& !(bean.getMaritalStatus() == null)
				&& !bean.getMaritalStatus().equals("null")) {

			query += " AND HRMS_EMP_PERS.EMP_MARITAL_STATUS	 ='"
					+ bean.getMaritalStatus().trim() + "'";
		}

		if (!(bean.getPayScaleCode().equals(""))
				&& !(bean.getPayScaleCode() == null)
				&& !bean.getPayScaleCode().equals("null")) {

			query += "  AND HRMS_SALARY_MISC.SAL_PAYSCALE IN("
					+ bean.getPayScaleCode() + ")";
		}

		/*
		 * if (!(bean.getDateBirthFlag().equals("0")) &&
		 * !(bean.getDateBirthFlag() == null) &&
		 * !bean.getDateBirthFlag().equals("null")) {
		 * 
		 * query += " AND TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'MM') ='" +
		 * bean.getDateBirthFlag().trim() + "'"; }
		 */

		// CODING FOR AGE FILTER
		/*
		 * if (!bean.getAgeSelect().equals("") &&
		 * !bean.getAgeSelect().trim().equals("BN")) { query += " AND ROUND(
		 * (SYSDATE - HRMS_EMP_OFFC.EMP_DOB)/365.24,0) " +
		 * getAdvanceFilter(bean.getAgeSelect()) + " " + bean.getAgeFrom(); }
		 * 
		 * if (bean.getAgeSelect().trim().equals("BN")) { query += " AND ROUND(
		 * (SYSDATE - HRMS_EMP_OFFC.EMP_DOB)/365.24,0) >= " + bean.getAgeFrom();
		 * 
		 * if (!bean.getAgeTo().equals("")) { query += "AND ROUND( (SYSDATE -
		 * HRMS_EMP_OFFC.EMP_DOB)/365.24,0) <= " + bean.getAgeTo(); } }
		 */

		/*
		 * //CODING FOR GROSS SALARY FILTER if
		 * (!bean.getGrossSalSelect().equals("") &&
		 * !bean.getGrossSalSelect().trim().equals("BN")) { query += " AND
		 * NVL(TO_CHAR(sysdate,'YYYY')-TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'YYYY'),0) " +
		 * getAdvanceFilter(bean.getGrossSalSelect()) + " " +
		 * bean.getGrossSalFrom(); }
		 * 
		 * if (bean.getGrossSalSelect().trim().equals("BN")) { query += " AND
		 * NVL(TO_CHAR(sysdate,'YYYY')-TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'YYYY'),0) >= " +
		 * bean.getGrossSalFrom();
		 * 
		 * if (!bean.getGrossSalTo().equals("")) { query += "AND
		 * NVL(TO_CHAR(sysdate,'YYYY')-TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'YYYY'),0) <= " +
		 * bean.getGrossSalTo(); } }
		 * 
		 * //CODING FOR CTC FILTER if (!bean.getCtcSelect().equals("") &&
		 * !bean.getCtcSelect().trim().equals("BN")) { query += " AND
		 * NVL(TO_CHAR(sysdate,'YYYY')-TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'YYYY'),0) " +
		 * getAdvanceFilter(bean.getCtcSelect()) + " " + bean.getCtcFrom(); }
		 * 
		 * if (bean.getCtcSelect().trim().equals("BN")) { query += " AND
		 * NVL(TO_CHAR(sysdate,'YYYY')-TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'YYYY'),0) >= " +
		 * bean.getCtcFrom();
		 * 
		 * if (!bean.getCtcTo().equals("")) { query += "AND
		 * NVL(TO_CHAR(sysdate,'YYYY')-TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'YYYY'),0) <= " +
		 * bean.getCtcTo(); } }
		 */

		// CODING FOR DATE OF BIRTH...
		// CODING FOR DATE OF BIRTH...
		if (!bean.getDobSelect().trim().equals("")) {
			if (bean.getDobSelect().trim().equals("ON")) {
				query += " AND HRMS_EMP_OFFC.EMP_DOB = TO_DATE('"
						+ bean.getDOBfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDobSelect().trim().equals("OB")) {
				query += " AND HRMS_EMP_OFFC.EMP_DOB <= TO_DATE('"
						+ bean.getDOBfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDobSelect().trim().equals("OA")) {
				query += " AND HRMS_EMP_OFFC.EMP_DOB >= TO_DATE('"
						+ bean.getDOBfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDobSelect().trim().equals("BF")) {
				query += " AND HRMS_EMP_OFFC.EMP_DOB < TO_DATE('"
						+ bean.getDOBfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDobSelect().trim().equals("AF")) {
				query += " AND HRMS_EMP_OFFC.EMP_DOB > TO_DATE('"
						+ bean.getDOBfromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDobSelect().trim().equals("BN")) {
				query += " AND HRMS_EMP_OFFC.EMP_DOB >= TO_DATE('"
						+ bean.getDOBfromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getDOBToDate().equals("") || bean.getDOBToDate()
						.equals("dd-mm-yyyy"))) {
					query += "AND HRMS_EMP_OFFC.EMP_DOB <= TO_DATE('"
							+ bean.getDOBToDate() + "','DD-MM-YYYY') ";
				}
			}
		}
		// CODING FOR DATE OF LEAVING...

		if (!bean.getDolSelect().trim().equals("")) {
			if (bean.getDolSelect().trim().equals("ON")) {
				query += " AND HRMS_EMP_OFFC.EMP_LEAVE_DATE = TO_DATE('"
						+ bean.getLeaveFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDolSelect().trim().equals("OB")) {
				query += " AND HRMS_EMP_OFFC.EMP_LEAVE_DATE <= TO_DATE('"
						+ bean.getLeaveFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDolSelect().trim().equals("OA")) {
				query += " AND HRMS_EMP_OFFC.EMP_LEAVE_DATE >= TO_DATE('"
						+ bean.getLeaveFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDolSelect().trim().equals("BF")) {
				query += " AND HRMS_EMP_OFFC.EMP_LEAVE_DATE < TO_DATE('"
						+ bean.getLeaveFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDolSelect().trim().equals("AF")) {
				query += " AND HRMS_EMP_OFFC.EMP_LEAVE_DATE > TO_DATE('"
						+ bean.getLeaveFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDolSelect().trim().equals("BN")) {
				query += " AND HRMS_EMP_OFFC.EMP_LEAVE_DATE >= TO_DATE('"
						+ bean.getLeaveFromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getLeaveToDate().equals("") || bean.getLeaveToDate()
						.equals("dd-mm-yyyy"))) {
					query += "AND HRMS_EMP_OFFC.EMP_LEAVE_DATE <= TO_DATE('"
							+ bean.getLeaveToDate() + "','DD-MM-YYYY') ";
				}
			}
		}

		// CODING FOR DATE OF CONFIRMATION...

		if (!bean.getDocSelect().trim().equals("")) {
			if (bean.getDocSelect().trim().equals("ON")) {
				query += " AND HRMS_EMP_OFFC.EMP_CONFIRM_DATE = TO_DATE('"
						+ bean.getConfFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDocSelect().trim().equals("OB")) {
				query += " AND HRMS_EMP_OFFC.EMP_CONFIRM_DATE <= TO_DATE('"
						+ bean.getConfFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDocSelect().trim().equals("OA")) {
				query += " AND HRMS_EMP_OFFC.EMP_CONFIRM_DATE >= TO_DATE('"
						+ bean.getConfFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDocSelect().trim().equals("BF")) {
				query += " AND HRMS_EMP_OFFC.EMP_CONFIRM_DATE < TO_DATE('"
						+ bean.getConfFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDocSelect().trim().equals("AF")) {
				query += " AND HRMS_EMP_OFFC.EMP_CONFIRM_DATE > TO_DATE('"
						+ bean.getConfFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDocSelect().trim().equals("BN")) {
				query += " AND HRMS_EMP_OFFC.EMP_CONFIRM_DATE >= TO_DATE('"
						+ bean.getConfFromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getConfToDate().equals("") || bean.getConfToDate()
						.equals("dd-mm-yyyy"))) {
					query += "AND HRMS_EMP_OFFC.EMP_CONFIRM_DATE <= TO_DATE('"
							+ bean.getConfToDate() + "','DD-MM-YYYY') ";
				}
			}
		}

		// CODING FOR DATE OF JOINING...

		if (!bean.getDojSelect().trim().equals("")) {
			if (bean.getDojSelect().trim().equals("ON")) {
				query += " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE = TO_DATE('"
						+ bean.getRegularFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDojSelect().trim().equals("OB")) {
				query += " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE <= TO_DATE('"
						+ bean.getRegularFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDojSelect().trim().equals("OA")) {
				query += " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE >= TO_DATE('"
						+ bean.getRegularFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDojSelect().trim().equals("BF")) {
				query += " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE < TO_DATE('"
						+ bean.getRegularFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDojSelect().trim().equals("AF")) {
				query += " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE > TO_DATE('"
						+ bean.getRegularFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getDojSelect().trim().equals("BN")) {
				query += " AND HRMS_EMP_OFFC.EMP_REGULAR_DATE >= TO_DATE('"
						+ bean.getRegularFromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getRegularToDate().equals("") || bean
						.getRegularToDate().equals("dd-mm-yyyy"))) {
					query += "AND HRMS_EMP_OFFC.EMP_REGULAR_DATE <= TO_DATE('"
							+ bean.getRegularToDate() + "','DD-MM-YYYY') ";
				}
			}
		}

		// CODING FOR GROUP JOINING DATE

		if (!bean.getGroupjoindateSelect().trim().equals("")) {
			if (bean.getGroupjoindateSelect().trim().equals("ON")) {
				query += " AND HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE = TO_DATE('"
						+ bean.getGroupjoinFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getGroupjoindateSelect().trim().equals("OB")) {
				query += " AND HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE <= TO_DATE('"
						+ bean.getGroupjoinFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getGroupjoindateSelect().trim().equals("OA")) {
				query += " AND HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE >= TO_DATE('"
						+ bean.getGroupjoinFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getGroupjoindateSelect().trim().equals("BF")) {
				query += " AND HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE < TO_DATE('"
						+ bean.getGroupjoinFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getGroupjoindateSelect().trim().equals("AF")) {
				query += " AND HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE > TO_DATE('"
						+ bean.getGroupjoinFromDate() + "','DD-MM-YYYY')";
			}

			if (bean.getGroupjoindateSelect().trim().equals("BN")) {
				query += " AND HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE >= TO_DATE('"
						+ bean.getGroupjoinFromDate() + "','DD-MM-YYYY') ";

				if (!(bean.getGroupjoinToDate().equals("") || bean
						.getGroupjoinToDate().equals("dd-mm-yyyy"))) {
					query += "AND HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE <= TO_DATE('"
							+ bean.getGroupjoinToDate() + "','DD-MM-YYYY') ";
				}
			}
		}
		// ============ start of sorting ===========
		if (!bean.getSortBy().equals("1") || !bean.getThenBy1().equals("1")
				|| !bean.getThenBy2().equals("1")) {
			query += " ORDER BY ";
		}
		if (!bean.getSortBy().equals("1")) {
			query += getSortVal(bean.getSortBy(), labelNames) + " "
					+ getSortOrder(bean.getSortByOrder());
			if (!bean.getThenBy1().equals("1")
					|| !bean.getThenBy2().equals("1")) {
				query += " , ";
			}
		}

		if (!bean.getThenBy1().equals("1")) {
			query += getSortVal(bean.getThenBy1(), labelNames) + " "
					+ getSortOrder(bean.getThenByOrder1());
			if (!bean.getThenBy2().equals("1")) {
				query += " , ";
			}
		}

		if (!bean.getThenBy2().equals("1")) {
			query += getSortVal(bean.getThenBy2(), labelNames) + " "
					+ getSortOrder(bean.getThenByOrder2());
		}
		// ============ end of sorting ===========

		return query;
	}

	public String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);
		String sql = "";
		if (Status.equals(labelNames[0])) {
			sql = "NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||"
					+ "NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')";
		}
		//DIVISION
		else if (Status.equals(labelNames[1])) {
			sql = "NVL(DIV_NAME,' ')";
		}
		//DEPARTMENT
		else if (Status.equals(labelNames[2])) {
			sql = " NVL(DEPT_NAME, ' ') ";
		}
		//BRANCH
		else if (Status.equals(labelNames[3])) {
			sql = " NVL(CENTER_NAME, ' ')";
		}
		//DESIGNATION
		else if (Status.equals(labelNames[4])) {
			sql = " NVL(RANK_NAME, ' ') ";
		} 
		//EMPLOYEE TYPE
		else if (Status.equals(labelNames[5])) {
			sql = " NVL(HRMS_EMP_TYPE.TYPE_NAME, ' ') ";
		}
		//EMPLOYEE GRADE
		else if (Status.equals(labelNames[6])) {
			sql = "  NVL(HRMS_CADRE.CADRE_NAME,' ')";
		}
		// FOR SHIFT - ADDED BY REEBA
		else if (Status.equals(labelNames[7])) {
			sql = " NVL(HRMS_SHIFT.SHIFT_NAME, ' ') ";
		}
		// DATE OF BIRTH
		else if (Status.equals(labelNames[8])) {
			sql = " HRMS_EMP_OFFC.EMP_DOB";
		}
		//DATE OF JOINING
		else if (Status.equals(labelNames[9])) {
			sql = "HRMS_EMP_OFFC.EMP_REGULAR_DATE ";
		}
		//DATE OF CONFIRMATION
		else if (Status.equals(labelNames[10])) {
			sql = "HRMS_EMP_OFFC.EMP_CONFIRM_DATE ";
		}
		//DATE OFLEAVING
		else if (Status.equals(labelNames[11])) {
			sql = "HRMS_EMP_OFFC.EMP_LEAVE_DATE ";
		}
		//SERVICE TENURE
		else if (Status.equals(labelNames[12])) {

			sql = "FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)||' "
					+ " '||'YEARS'||' '|| FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)- "
					+ " FLOOR(MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) "
					+ " *12) ||' '||'MONTHS' AS EXPERRIENCE ";
		}
		// EMPLOYEE STATUS
		else if (Status.equals(labelNames[13])) {
			sql = " NVL(HRMS_EMP_OFFC.EMP_STATUS, ' ') ";
		}
		// REPORTING TO
		else if (Status.equals(labelNames[14])) {
			sql = " NVL(OFF1.EMP_FNAME,' ')||' '||NVL(OFF1.EMP_MNAME,' ')||' '||NVL(OFF1.EMP_LNAME,' ')";
		}
		// SALARY GRADE
		else if (Status.equals(labelNames[15])) {
			sql = " NVL(HRMS_SALGRADE_HDR.SALGRADE_TYPE, ' ') ";
		}
		// GROUP JOINING DATE
		else if (Status.equals(labelNames[16])) {
			sql = " HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE";
		}
		//TRADE
		else if (Status.equals(labelNames[17])) {
			sql = " NVL(HRMS_TRADE.TRADE_NAME,' ')";
		}
		//GENDER
		else if (Status.equals(labelNames[18])) {
			sql = " DECODE(HRMS_EMP_OFFC.EMP_GENDER,'M','Male','F','Female','O','Other')";
		}
		//MOBILE NUMBER
		else if (Status.equals(labelNames[19])) {
			sql = " NVL(HRMS_EMP_ADDRESS.ADD_MOBILE, ' ')";
		}
		//EMAIL ID
		else if (Status.equals(labelNames[20])) {
			sql = " NVL(HRMS_EMP_ADDRESS.ADD_EMAIL,' ' )";
		}
		//AGE
		else if (Status.equals(labelNames[21])) {
			sql = " ROUND( (SYSDATE - HRMS_EMP_OFFC.EMP_DOB)/365.24,0)";
		}
		// PF NUMBER
		else if (Status.equals(labelNames[22])) {
			sql = " HRMS_SALARY_MISC.SAL_GPFNO ";
		}
		// PAN NUMBER
		else if (Status.equals(labelNames[23])) {
			sql = " HRMS_SALARY_MISC.SAL_PANNO ";
		}
		// ESIC NUMBER
		else if (Status.equals(labelNames[24])) {
			sql = " HRMS_SALARY_MISC.SAL_ESCINUMBER ";
		}
		// PAY MODE
		else if (Status.equals(labelNames[25])) {
			sql = "HRMS_SALARY_MISC.SAL_PAY_MODE";
		}
		// SALARY ACCOUNT
		else if (Status.equals(labelNames[26])) {
			sql = " HRMS_SALARY_MISC.SAL_ACCNO_REGULAR ";
		}
		// SALARY ACCOUNT BANK
		else if (Status.equals(labelNames[27])) {
			sql = " HRMS_SALARY_MISC.SAL_MICR_REGULAR ";
		} 
		else if (Status.equals(labelNames[27])) {
			sql = " HRMS_BANK.BANK_NAME ";
		}
		// REIMBURSEMENT ACCOUNT
		else if (Status.equals(labelNames[28])) {
			sql = " HRMS_SALARY_MISC.SAL_REIMBMENT ";
		}
		// REIMBURSEMENT BANK
		else if (Status.equals(labelNames[29])) {
			sql = " HRMS_SALARY_MISC.SAL_REIMBANK ";
		} 
		else if (Status.equals(labelNames[29])) {
			sql = " HRMS_BANK.BANK_NAME ";
		}
		// SALARY-30
		// CTC-31
		// ACCOUNTING CATEGORY
		else if (Status.equals(labelNames[32])) {
			sql = "HRMS_ACCOUNTING_CATEGORY.ACCOUNT_CATEGORY_NAME";
		}
		// COST CENTER
		else if (Status.equals(labelNames[33])) {
			sql = "HRMS_COST_CENTER.COST_CENTER_NAME";
		}
		// SUBCOST CENTER
		else if (Status.equals(labelNames[34])) {
			sql = "HRMS_SUB_COST_CENTER.SUB_COST_CENTER_NAME ";
		}	
		//CUTOMER REFERENCE NUMBER
		else if (Status.equals(labelNames[35])) {
			sql = " NVL(HRMS_SALARY_MISC.CUSTOMER_REF_NO,' ')";
		}
		//PAYBILL NAME
		else if (Status.equals(labelNames[36])) {
			sql = " NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ')";
		}	
		//DEBIT-37
		//GRATUITY ID
		else if (Status.equals(labelNames[38])) {
			sql = " NVL(HRMS_SALARY_MISC.SAL_GRATUITY_ACCNO,' ')";
		}
		//PENSION ACCOUNT NUMBER
		else if (Status.equals(labelNames[39])) {
			sql = " NVL(HRMS_SALARY_MISC.SAL_PENSION_ACCNO,' ') ";
		}
		//PENSION BANK NAME
		else if (Status.equals(labelNames[40])) {
			sql = " HRMS_SALARY_MISC.SAL_PENSION_BANK ";
		}
		else if (Status.equals(labelNames[40])) {
			sql = " NVL(B3.BANK_NAME,' ') ";
		}
		//ROLE
		else if (Status.equals(labelNames[41])) {
			sql = " NVL(HRMS_EMP_OFFC.EMP_ROLE,' ')";
		}
		//EMPLOYEE CODE
		else if (Status.equals("Employee Code")) {
			sql = " HRMS_EMP_OFFC.EMP_TOKEN ";
		}

		return sql;
	}

	public String getSortOrder(String Status) {
		String sql = "";
		if (Status.equals("A")) {
			sql = "ASC";
		}
		if (Status.equals("D")) {
			sql = "DESC";
		}
		return sql;
	}

	public String getAdvanceFilter(String Status) {
		String sql = "";
		if (Status.equals("IE")) {
			sql = "=";
		}
		if (Status.equals("LE")) {
			sql = "<=";
		}

		if (Status.equals("GE")) {
			sql = ">=";
		}
		if (Status.equals("LT")) {
			sql = "<";
		}
		if (Status.equals("GT")) {
			sql = ">";
		}
		if (Status.equals("GT")) {
			sql = ">";
		}
		return sql;
	}

	/**
	 * Method to sort HashMap values by key
	 * 
	 * @param passedMap
	 * @return
	 */
	public LinkedHashMap<Integer, String> sortHashMapByValuesD(
			HashMap<Integer, String> passedMap) {
		List<Integer> mapKeys = new ArrayList<Integer>(passedMap.keySet());
		List<String> mapValues = new ArrayList<String>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<Integer, String> sortedMap = new LinkedHashMap<Integer, String>();

		Iterator<String> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Object val = valueIt.next();
			Iterator<Integer> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Object key = keyIt.next();
				String comp1 = passedMap.get(key).toString();
				String comp2 = val.toString();

				if (comp1.equals(comp2)) {
					passedMap.remove(key);
					mapKeys.remove(key);
					sortedMap.put((Integer) key, (String) val);
					break;
				}

			}

		}
		return sortedMap;
	}

	public void callViewScreen(AccMISReport misreport,
			HttpServletRequest request, String[] labelNames) {
		try {
			String mutliSelectValues = misreport.getHiddenColumns();
			// System.out.println("mutliSelectValues : "+mutliSelectValues);
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0,
						mutliSelectValues.length() - 1);
				splitComma = lastComma.split(",");
			}
			int count = 0;
			Object[][] creditsObj = null;
			Object[][] debitsObj = null;
			// SELECT QUERY WITH COUNT (COUNT APPENDED AFTER #)
			String queryWithCount = selectQuery(misreport, labelNames, count,
					splitComma, request);
			// SPLIT TO GET COUNT VALUE
			String[] splitquery = queryWithCount.split("#");
			String query = splitquery[0];
			count = Integer.parseInt(splitquery[1]);
			String labels = splitquery[2];
			if (misreport.getCtcFlag().equals("true")) {
				count++;
			}
			if (misreport.getSalaryFlag().equals("true")) {
				String creditQuery = "SELECT CREDIT_NAME||' ('||DECODE(CREDIT_PERIODICITY,'M','Monthly','A','Annually','H','Half Yearly','Q','Quarterly' )||')' AS NAME FROM HRMS_CREDIT_HEAD ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
				creditsObj = getSqlModel().getSingleResult(creditQuery);
				if (creditsObj != null) {
					count = count + creditsObj.length;
				}
			}
			if (misreport.getDebitFlag().equals("true")) {
				String debitQuery = "SELECT DEBIT_NAME||' ('||DECODE(DEBIT_PERIODICITY,'M','Monthly','A','Annually','H','Half Yearly','Q','Quarterly' )||')' AS NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";
				debitsObj = getSqlModel().getSingleResult(debitQuery);
				if (debitsObj != null) {
					count = count + debitsObj.length;
				}
			}
			// QUERY APPENDED WITH CONDITIONS
			query += conditionQuery(misreport, labelNames);
			System.out.println("QUERY=" + query);
			Object[][] finalObj = null;
			Object[][] reportObj = getSqlModel().getSingleResult(query);
			// CALCULATION FOR CTC AND SALARY BEGINS-----------
			Object[][] ctcObj = null;
			if (misreport.getCtcFlag().equals("true")) {
				if (reportObj != null && reportObj.length > 0) {
					ctcObj = new Object[reportObj.length][reportObj[0].length];
					String pfIncludeFlag = " SELECT HRMS_EMP_OFFC.EMP_ID,SAL_EPF_FLAG FROM HRMS_EMP_OFFC "
							+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID) "
							+ " WHERE ";
					String strAppend = getValue(reportObj,
							reportObj[0].length - 1, "HRMS_EMP_OFFC.EMP_ID", 0);
					pfIncludeFlag += strAppend;
					HashMap map = (HashMap) getSqlModel().getSingleResultMap(
							pfIncludeFlag, 0, 0);
					for (int i = 0; i < reportObj.length; i++) {
						for (int j = 0; j < reportObj[0].length - 1; j++) {
							ctcObj[i][j] = reportObj[i][j];
						}
						String checkMap = "N";
						try {
							Object[] obj_pf = (Object[]) map
									.get(String
											.valueOf(reportObj[i][reportObj[0].length - 1]));
								checkMap = String.valueOf(obj_pf[1]);

						} catch (Exception e) {
							e.printStackTrace();
						}
						ctcObj[i][ctcObj[0].length - 1] = getCTC(
								String
										.valueOf(reportObj[i][reportObj[0].length - 1]),
								checkMap);
					}
				}
			}
			if (misreport.getSalaryFlag().equals("true")
					&& misreport.getDebitFlag().equals("true")) {
				if (misreport.getCtcFlag().equals("true")) {
					if (ctcObj != null && ctcObj.length > 0) {
						finalObj = new Object[ctcObj.length][ctcObj[0].length
								+ creditsObj.length + debitsObj.length + 1];
						for (int i = 0; i < ctcObj.length; i++) {
							for (int j = 0; j < ctcObj[0].length; j++) {
								finalObj[i][j] = ctcObj[i][j];
							}
						}
					}
				} else {
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length
								+ creditsObj.length + debitsObj.length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length - 1; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
				}

				if (reportObj != null && reportObj.length > 0) {

					for (int i = 0; i < reportObj.length; i++) {
						String credAmtQuery = "SELECT CREDIT_NAME||' ('||DECODE(CREDIT_PERIODICITY,'M','Monthly','A','Annually','H','Half Yearly' )||')',NVL(CREDIT_AMT,0) FROM HRMS_CREDIT_HEAD "
								+ " LEFT JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "
								+ " AND HRMS_EMP_CREDIT.EMP_ID ="
								+ reportObj[i][reportObj[0].length - 1]
								+ " )"
								+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
						Object[][] credAmtObj = getSqlModel().getSingleResult(
								credAmtQuery);
						int lastCount = 0;
						if (misreport.getCtcFlag().equals("true")) {
							lastCount = ctcObj[0].length;
						} else
							lastCount = reportObj[0].length - 1;
						for (int j = 0; j < credAmtObj.length; j++) {
							finalObj[i][lastCount] = credAmtObj[j][1];
							lastCount++;
						}

						String debitAmtQuery = "SELECT DEBIT_NAME,NVL(DEBIT_AMT,0) FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON(HRMS_EMP_DEBIT.DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE AND HRMS_EMP_DEBIT.EMP_ID = "
								+ reportObj[i][reportObj[0].length - 1]
								+ " )"
								+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
						Object[][] debitAmtObj = getSqlModel().getSingleResult(
								debitAmtQuery);
						for (int j = 0; j < debitAmtObj.length; j++) {
							finalObj[i][lastCount] = debitAmtObj[j][1];
							lastCount++;
						}

					}
				}

			}

			else if (misreport.getSalaryFlag().equals("true")) {
				if (misreport.getCtcFlag().equals("true")) {
					if (ctcObj != null && ctcObj.length > 0) {
						finalObj = new Object[ctcObj.length][ctcObj[0].length
								+ creditsObj.length + 1];
						for (int i = 0; i < ctcObj.length; i++) {
							for (int j = 0; j < ctcObj[0].length; j++) {
								finalObj[i][j] = ctcObj[i][j];
							}
						}
					}
				} else {
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length
								+ creditsObj.length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length - 1; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
				}

				if (reportObj != null && reportObj.length > 0) {

					for (int i = 0; i < reportObj.length; i++) {
						String credAmtQuery = "SELECT CREDIT_NAME||' ('||DECODE(CREDIT_PERIODICITY,'M','Monthly','A','Annually','H','Half Yearly' )||')',NVL(CREDIT_AMT,0) FROM HRMS_CREDIT_HEAD "
								+ " LEFT JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.CREDIT_CODE = HRMS_CREDIT_HEAD.CREDIT_CODE "
								+ " AND HRMS_EMP_CREDIT.EMP_ID ="
								+ reportObj[i][reportObj[0].length - 1]
								+ " )"
								+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
						Object[][] credAmtObj = getSqlModel().getSingleResult(
								credAmtQuery);
						int lastCount = 0;
						if (misreport.getCtcFlag().equals("true")) {
							lastCount = ctcObj[0].length;
						} else
							lastCount = reportObj[0].length - 1;
						for (int j = 0; j < credAmtObj.length; j++) {
							finalObj[i][lastCount] = credAmtObj[j][1];
							lastCount++;
						}

					}
				}

			}

			else if (misreport.getDebitFlag().equals("true")) {

				if (misreport.getCtcFlag().equals("true")) {
					if (ctcObj != null && ctcObj.length > 0) {
						finalObj = new Object[ctcObj.length][ctcObj[0].length
								+ debitsObj.length + 1];
						for (int i = 0; i < ctcObj.length; i++) {
							for (int j = 0; j < ctcObj[0].length; j++) {
								finalObj[i][j] = ctcObj[i][j];
							}
						}
					}
				} else {
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length
								+ debitsObj.length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length - 1; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
				}

				if (reportObj != null && reportObj.length > 0) {

					for (int i = 0; i < reportObj.length; i++) {
						String debitAmtQuery = "SELECT DEBIT_NAME,NVL(DEBIT_AMT,0) FROM HRMS_DEBIT_HEAD LEFT JOIN HRMS_EMP_DEBIT ON(HRMS_EMP_DEBIT.DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE AND HRMS_EMP_DEBIT.EMP_ID = "
								+ reportObj[i][reportObj[0].length - 1]
								+ " )"
								+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";
						Object[][] debitAmtObj = getSqlModel().getSingleResult(
								debitAmtQuery);
						int lastCount = 0;
						if (misreport.getCtcFlag().equals("true")) {
							lastCount = ctcObj[0].length;
						} else
							lastCount = reportObj[0].length - 1;

						for (int j = 0; j < debitAmtObj.length; j++) {
							finalObj[i][lastCount] = debitAmtObj[j][1];
							lastCount++;
						}

					}
				}

			} else {
				// if (reportObj != null && reportObj.length > 0) {
				if (misreport.getCtcFlag().equals("false")) {
					if (reportObj != null && reportObj.length > 0) {
						finalObj = new Object[reportObj.length][reportObj[0].length + 1];
						for (int i = 0; i < reportObj.length; i++) {
							for (int j = 0; j < reportObj[0].length; j++) {
								finalObj[i][j] = reportObj[i][j];
							}
						}
					}
				} else {
					if (ctcObj != null && ctcObj.length > 0) {
						finalObj = new Object[ctcObj.length][ctcObj[0].length + 1];
						for (int i = 0; i < ctcObj.length; i++) {
							for (int j = 0; j < ctcObj[0].length; j++) {
								finalObj[i][j] = ctcObj[i][j];
							}
						}
					}

				}
			}

			// CALCULATION FOR CTC AND SALARY ENDS------------
			if (misreport.getCtcFlag().equals("true")) {
				labels += "CTC,";
			}

			if (misreport.getSalaryFlag().equals("true")) {
				if (creditsObj != null) {
					for (int i = 0; i < creditsObj.length; i++) {
						labels += String.valueOf(creditsObj[i][0]) + ",";
					}

				}
			}
			if (misreport.getDebitFlag().equals("true")) {
				if (debitsObj != null) {
					for (int i = 0; i < debitsObj.length; i++) {

						labels += String.valueOf(debitsObj[i][0]) + ",";
					}
				}
			}
			request.setAttribute("labelValues", labels);
			Object[][] objData1 = null;
			String[] labelObject = null;

			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 1];
				labelObject = new String[count + 1];
				if (misreport.getCasteFlag().equals("true")) {
					objData1 = new Object[finalObj.length][count + 2];
					labelObject = new String[count + 2];
				}
				misreport.setDataLength(String.valueOf(objData1.length));
				if (finalObj != null && finalObj.length > 0) {
					for (int j = 0; j < finalObj.length; ++j) {
						int int_count = 0;
						int label_count = 0;
						objData1[j][0] = checkNull(String
								.valueOf(finalObj[j][0]));// Employee Token
						labelObject[0] = "Employee Id";
						if (misreport.getEmpNameFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));// Employee
																		// Name
							labelObject[++label_count] = labelNames[0];
						}
						// DIVISION
						if (misreport.getDivFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[1];
						}
						// DEPARTMENT
						if (misreport.getDeptFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[2];
						}
						// BRANCH
						if (misreport.getCenterFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[3];
						}
						// DESIGNATION
						if (misreport.getRankFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[4];
						}
						// EMPLOYEE TYPE
						if (misreport.getTypeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[5];
						}

						// EMPLOYEE GRADE
						if (misreport.getEmpGradeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[6];
						}

						// FOR SHIFT - ADDED BY REEBA
						if (misreport.getShiftFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[7];

						}
						// DATE OF BIRTH
						if (misreport.getDateBirthFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[8];
						}

						// DATE OF JOINING
						if (misreport.getDateRegFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[9];
						}

						// DATE OF CONFIRMATION
						if (misreport.getDateOfConf().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[10];
						}

						// DATE OF LEAVING
						if (misreport.getDateOfSepFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[11];
						}

						// SERVICE TENURE
						if (misreport.getYearexperienceFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[12];
						}

						// EMPLOYEE STATUS
						if (misreport.getEmpStatusFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[13];
						}

						// REPORTING TO
						if (misreport.getReportingToFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[14];
						}

						// SALARY GRADE
						if (misreport.getGradeFlage().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[15];
						}

						// GROUP JOIN DATE
						if (misreport.getGroupjoindateFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[16];
						}

						// TRADE
						if (misreport.getTradeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[17];
						}

						// GENDER
						if (misreport.getGenderFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[18];

						}

						// MOBILE NO
						if (misreport.getMobFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[19];
						}

						// EMAIL ID
						if (misreport.getEmailFlag().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[20];
						}

						// AGE
						if (misreport.getAgeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[21];
						}

						// PF NUMBER
						if (misreport.getPfNoFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[22];
						}

						// PAN NUMBER
						if (misreport.getPanNoFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[23];
						}

						// ESIC NUMBER
						if (misreport.getEsicNoFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[24];
						}

						// PAY MODE
						if (misreport.getPayModeFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[25];
						}
						// SALARY ACCOUNT NUMBER
						if (misreport.getSalAccFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[26];
						}
						/*
						 * if (misreport.getSalBankFlag().equals("true")) {
						 * objData1[j][++int_count] = checkNull(String
						 * .valueOf(finalObj[j][int_count]));
						 * labelObject[++label_count] = "Salary Bank MICR"; }
						 */
						// SALARY BANK NAME
						if (misreport.getSalBankFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[27];
						}
						// REIMBURSEMENT ACCOUNT NUMBER
						if (misreport.getReimbAccFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[28];
						}
						// REIMBURSEMENT BANK NAME
						if (misreport.getReimbBankFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[29];
						}

						// SALARY
						if (misreport.getSalaryFlag().equals("true")) {
							for (int k = 0; k < creditsObj.length; k++) {
								objData1[j][++int_count] = checkNull(String
										.valueOf(finalObj[j][int_count]));
								labels += String.valueOf(creditsObj[k][0])
										+ ",";
								++label_count;
							}
						}
						// CTC
						if (misreport.getCtcFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[31];
						}

						// ACCOUNTING CATEGORY
						if (misreport.getAccCategoryFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[32];
						}
						// COST CENTER
						if (misreport.getCostCenterFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[33];
						}
						// SUB COST CENTER
						if (misreport.getSubCostCenterFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[34];
						}

						// CUSTOMER REF NO
						if (misreport.getCustomerRefNoFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[35];
						}

						// PAY BILL
						if (misreport.getPaybillFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[36];
						}
						// DEBIT
						if (misreport.getDebitFlag().equals("true")) {
							for (int k = 0; k < debitsObj.length; k++) {
								objData1[j][++int_count] = checkNull(String
										.valueOf(finalObj[j][int_count]));
								labels += String.valueOf(debitsObj[k][0]) + ",";
								++label_count;
							}
						}

						// GRATUITY ID
						if (misreport.getGratuityIDFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[38];
						}

						// PENSION ACCOUNT NUMBER
						if (misreport.getPensionAccNoFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[39];
						}
						// PENSION BANK NAME
						if (misreport.getPensionBankFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[40];
						}

						// ROLE
						if (misreport.getRoleFlag().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[41];
						}

					}
				}
				String[] pageIndex = Utility.doPaging(misreport.getMyPage(),
						objData1.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					misreport.setMyPage("1");

				int numOfRec = Integer.parseInt(pageIndex[1])
						- Integer.parseInt(pageIndex[0]);
				int columnLength = count + 1;
				Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				int srNo = 1;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						pageObj[z][j] = objData1[i][j];
					}
					z++;
					srNo++;
					request.setAttribute("finalObj", pageObj);
				}

			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				misreport.setNoData("true");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public boolean deleteSavedReport(AccMISReport misReport) {
		boolean result = false;
		String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
				+ misReport.getReportId();
		String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
				+ misReport.getReportId();
		result = getSqlModel().singleExecute(deleteDetail);
		if (result)
			result = getSqlModel().singleExecute(deleteHeader);
		return result;
	}

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(AccMISReport misReport) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE UPPER(REPORT_CRITERIA) LIKE '"
				+ misReport.getSettingName().trim().toUpperCase()
				+ "' AND REPORT_TYPE='ACCMISReport'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	public boolean saveReportCriteria(AccMISReport misReport) {
		boolean result = false;
		if (!checkDuplicate(misReport)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = checkNull(misReport.getSettingName().trim());
			saveObj[0][1] = checkNull(misReport.getReportTitle().trim());
			saveObj[0][2] = "ACCMISReport";
			String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE,REPORT_TYPE)"
					+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?,?)";
			result = getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] codeObj = getSqlModel().getSingleResult(codeQuery);
				misReport.setReportId(String.valueOf(codeObj[0][0]));
				misReport.setReportTypeStr("ACCMISReport");
				if (saveFilters(misReport) && saveColumns(misReport)
						&& saveSortOptions(misReport)
						&& saveAdvancedFilters(misReport)) {
					result = true;
				} else
					result = false;
			}
		} else
			result = false;
		return result;
	}

	public boolean saveFilters(AccMISReport misReport) {
		boolean result = false;
		int count = 0;
		if (!misReport.getFirstName().equals("")) {
			count++;
		}
		if (!misReport.getLastName().equals("")) {
			count++;
		}
		if (!misReport.getGender().equals("1")) {
			count++;
		}
		if (!misReport.getMaritalStatus().equals("1")) {
			count++;
		}
		if (!misReport.getRankCode().equals("")) {
			count++;// code
		}
		if (!misReport.getRankCode().equals("")) {
			count++;// name
		}
		if (!misReport.getBldGroup().equals("0")) {
			count++;
		}
		if (!misReport.getPayBillId().equals("")) {
			count++;// code
		}
		if (!misReport.getPayBillId().equals("")) {
			count++;// name
		}
		if (!misReport.getCenterCode().equals("")) {
			count++;// code
		}
		if (!misReport.getCenterCode().equals("")) {
			count++;// name
		}
		if (!misReport.getDeptCode().equals("")) {
			count++;// code
		}
		if (!misReport.getDeptCode().equals("")) {
			count++;// name
		}
		if (!misReport.getDivCode().equals("")) {
			count++;// code
		}
		if (!misReport.getDivCode().equals("")) {
			count++;// name
		}
		if (!misReport.getTypeCode().equals("")) {
			count++;// code
		}
		if (!misReport.getTypeCode().equals("")) {
			count++;// name
		}
		if (!misReport.getShiftCode().equals("")) {
			count++;// code
		}
		if (!misReport.getShiftCode().equals("")) {
			count++;// name
		}
		if (!misReport.getReportingID().equals("")) {
			count++;// code
		}
		if (!misReport.getReportingID().equals("")) {
			count++;// name
		}
		if (!misReport.getReportingID().equals("")) {
			count++;// token
		}
		if (!misReport.getStatus().equals("1")) {
			count++;
		}
		if (!misReport.getReligionCode().equals("")) {
			count++;// code
		}
		if (!misReport.getReligionCode().equals("")) {
			count++;// name
		}
		if (!misReport.getCastCode().equals("")) {
			count++;// code
		}
		if (!misReport.getCastCode().equals("")) {
			count++;// name
		}
		if (!misReport.getCastCategoryCode().equals("")) {
			count++;// code
		}
		if (!misReport.getCastCategoryCode().equals("")) {
			count++;// name
		}
		if (!misReport.getBDayMonth().equals("0")) {
			count++;
		}
		logger.info("Count for Save filters : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!misReport.getFirstName().equals("")) {
			addObj[int_count][0] = "misreport.firstName";
			addObj[int_count][1] = checkNull(misReport.getFirstName().trim());
			int_count++;
		}
		if (!misReport.getLastName().equals("")) {
			addObj[int_count][0] = "misreport.lastName";
			addObj[int_count][1] = checkNull(misReport.getLastName().trim());
			int_count++;
		}
		if (!misReport.getGender().equals("1")) {
			addObj[int_count][0] = "misreport.gender";
			addObj[int_count][1] = checkNull(misReport.getGender().trim());
			int_count++;
		}
		if (!misReport.getMaritalStatus().equals("1")) {
			addObj[int_count][0] = "misreport.maritalStatus";
			addObj[int_count][1] = checkNull(misReport.getMaritalStatus()
					.trim());
			int_count++;
		}
		if (!misReport.getRankCode().equals("")) {// code
			addObj[int_count][0] = "misreport.rankCode";
			addObj[int_count][1] = checkNull(misReport.getRankCode().trim());
			int_count++;
		}
		if (!misReport.getRankName().equals("")) {// name
			addObj[int_count][0] = "misreport.rankName";
			addObj[int_count][1] = checkNull(misReport.getRankName().trim());
			int_count++;
		}
		if (!misReport.getBldGroup().equals("0")) {
			addObj[int_count][0] = "misreport.bldGroup";
			addObj[int_count][1] = checkNull(misReport.getBldGroup().trim());
			int_count++;
		}
		if (!misReport.getPayBillId().equals("")) {
			addObj[int_count][0] = "misreport.payBillId";
			addObj[int_count][1] = checkNull(misReport.getPayBillId().trim());
			int_count++;
		}
		if (!misReport.getPayBillName().equals("")) {
			addObj[int_count][0] = "misreport.payBillName";
			addObj[int_count][1] = checkNull(misReport.getPayBillName().trim());
			int_count++;
		}
		if (!misReport.getCenterCode().equals("")) {
			addObj[int_count][0] = "misreport.centerCode";
			addObj[int_count][1] = checkNull(misReport.getCenterCode().trim());
			int_count++;
		}
		if (!misReport.getCenterName().equals("")) {
			addObj[int_count][0] = "misreport.centerName";
			addObj[int_count][1] = checkNull(misReport.getCenterName().trim());
			int_count++;
		}
		if (!misReport.getDeptCode().equals("")) {
			addObj[int_count][0] = "misreport.deptCode";
			addObj[int_count][1] = checkNull(misReport.getDeptCode().trim());
			int_count++;
		}
		if (!misReport.getDeptName().equals("")) {
			addObj[int_count][0] = "misreport.deptName";
			addObj[int_count][1] = checkNull(misReport.getDeptName().trim());
			int_count++;
		}
		if (!misReport.getDivCode().equals("")) {
			addObj[int_count][0] = "misreport.divCode";
			addObj[int_count][1] = checkNull(misReport.getDivCode().trim());
			int_count++;
		}
		if (!misReport.getDivName().equals("")) {
			addObj[int_count][0] = "misreport.divName";
			addObj[int_count][1] = checkNull(misReport.getDivName().trim());
			int_count++;
		}
		if (!misReport.getTypeCode().equals("")) {
			addObj[int_count][0] = "misreport.typeCode";
			addObj[int_count][1] = checkNull(misReport.getTypeCode().trim());
			int_count++;
		}
		if (!misReport.getTypeName().equals("")) {
			addObj[int_count][0] = "misreport.typeName";
			addObj[int_count][1] = checkNull(misReport.getTypeName().trim());
			int_count++;
		}
		if (!misReport.getShiftCode().equals("")) {
			addObj[int_count][0] = "misreport.shiftCode";
			addObj[int_count][1] = checkNull(misReport.getShiftCode().trim());
			int_count++;
		}
		if (!misReport.getShiftType().equals("")) {
			addObj[int_count][0] = "misreport.shiftType";
			addObj[int_count][1] = checkNull(misReport.getShiftType().trim());
			int_count++;
		}
		if (!misReport.getReportingID().equals("")) {
			addObj[int_count][0] = "misreport.reportingID";
			addObj[int_count][1] = checkNull(misReport.getReportingID().trim());
			int_count++;
		}
		if (!misReport.getReportingTo().equals("")) {
			addObj[int_count][0] = "misreport.reportingTo";
			addObj[int_count][1] = checkNull(misReport.getReportingTo().trim());
			int_count++;
		}
		if (!misReport.getReportingToken().equals("")) {
			addObj[int_count][0] = "misreport.reportingToken";
			addObj[int_count][1] = checkNull(misReport.getReportingToken()
					.trim());
			int_count++;
		}
		if (!misReport.getStatus().equals("1")) {
			addObj[int_count][0] = "misreport.status";
			addObj[int_count][1] = checkNull(misReport.getStatus().trim());
			int_count++;
		}
		if (!misReport.getReligionCode().equals("")) {
			addObj[int_count][0] = "misreport.religionCode";
			addObj[int_count][1] = checkNull(misReport.getReligionCode().trim());
			int_count++;
		}
		if (!misReport.getReligion().equals("")) {
			addObj[int_count][0] = "misreport.religion";
			addObj[int_count][1] = checkNull(misReport.getReligion().trim());
			int_count++;
		}
		if (!misReport.getCastCode().equals("")) {
			addObj[int_count][0] = "misreport.castCode";
			addObj[int_count][1] = checkNull(misReport.getCastCode().trim());
			int_count++;
		}
		if (!misReport.getCastName().equals("")) {
			addObj[int_count][0] = "misreport.castName";
			addObj[int_count][1] = checkNull(misReport.getCastName().trim());
			int_count++;
		}
		if (!misReport.getCastCategoryCode().equals("")) {
			addObj[int_count][0] = "misreport.castCategoryCode";
			addObj[int_count][1] = checkNull(misReport.getCastCategoryCode()
					.trim());
			int_count++;
		}
		if (!misReport.getCastCategory().equals("")) {
			addObj[int_count][0] = "misreport.castCategory";
			addObj[int_count][1] = checkNull(misReport.getCastCategory().trim());
			int_count++;
		}
		if (!misReport.getBDayMonth().equals("0")) {
			addObj[int_count][0] = "misreport.bDayMonth";
			addObj[int_count][1] = checkNull(misReport.getBDayMonth().trim());
			int_count++;
		}
		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {

			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addFilters = new Object[int_count][3];
			for (int i = 0; i < addFilters.length; i++) {
				addFilters[i][0] = maxCode[0][0];
				addFilters[i][1] = addObj[i][0];
				logger.info("addFilters[" + i + "][1]  : " + addFilters[i][1]);
				addFilters[i][2] = addObj[i][1];
				logger.info("addFilters[" + i + "][2]  : " + addFilters[i][2]);
			}
			String insertFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertFilters, addFilters);
			return result;
		}
	}

	public boolean saveColumns(AccMISReport misReport) {
		boolean result = false;
		int count = 0;
		if (misReport.getEmpNameFlag().equals("true")) {
			count++;
		}
		if (misReport.getDivFlag().equals("true")) {
			count++;
		}
		if (misReport.getDeptFlag().equals("true")) {
			count++;
		}
		if (misReport.getCenterFlag().equals("true")) {
			count++;
		}
		if (misReport.getRankFlag().equals("true")) {
			count++;
		}
		if (misReport.getTypeFlag().equals("true")) {
			count++;
		}
		if (misReport.getGradeFlage().equals("true")) {
			count++;
		}
		if (misReport.getShiftFlag().equals("true")) {
			count++;
		}
		if (misReport.getDateBirthFlag().equals("true")) {
			count++;
		}
		if (misReport.getDateRegFlag().equals("true")) {
			count++;
		}
		if (misReport.getDateOfConf().equals("true")) {
			count++;
		}
		if (misReport.getDateOfSepFlag().equals("true")) {
			count++;
		}
		if (misReport.getYearexperienceFlag().equals("true")) {
			count++;
		}
		if (misReport.getEmpStatusFlag().equals("true")) {
			count++;
		}
		if (misReport.getReportingToFlag().equals("true")) {
			count++;
		}
		if (misReport.getPfNoFlag().equals("true")) {
			count++;
		}
		if (misReport.getPanNoFlag().equals("true")) {
			count++;
		}
		if (misReport.getEsicNoFlag().equals("true")) {
			count++;
		}
		if (misReport.getPayModeFlag().equals("true")) {
			count++;
		}
		if (misReport.getSalAccFlag().equals("true")) {
			count++;
		}
		if (misReport.getSalBankFlag().equals("true")) {
			count++;
		}
		if (misReport.getReimbAccFlag().equals("true")) {
			count++;
		}
		if (misReport.getReimbBankFlag().equals("true")) {
			count++;
		}
		if (misReport.getCtcFlag().equals("true")) {
			count++;
		}
		if (misReport.getSalaryFlag().equals("true")) {
			count++;
		}
		if (misReport.getAccCategoryFlag().equals("true")) {
			count++;
		}
		if (misReport.getCostCenterFlag().equals("true")) {
			count++;
		}
		if (misReport.getSubCostCenterFlag().equals("true")) {
			count++;
		}

		// FOR GROUP JOIN DATE
		if (misReport.getGroupjoindateFlag().equals("true")) {
			count++;
		}

		// Gender
		if (misReport.getGenderFlag().equals("true")) {
			count++;
		}
		// Mobile No
		if (misReport.getMobFlag().equals("true")) {
			count++;
		}
		// Email Id
		if (misReport.getEmailFlag().equals("true")) {
			count++;
		}
		// Age
		if (misReport.getAgeFlag().equals("true")) {
			count++;
		}
		// Customer Ref NO
		if (misReport.getCustomerRefNoFlag().equals("true")) {
			count++;
		}
		// Pay Bill
		if (misReport.getPaybillFlag().equals("true")) {
			count++;
		}
		// Debit
		if (misReport.getDebitFlag().equals("true")) {
			count++;
		}
		// Pay Scale
		if (misReport.getRoleFlag().equals("true")) {
			count++;
		}
		// Grade
		if (misReport.getEmpGradeFlag().equals("true")) {
			count++;
		}
		// Trade
		if (misReport.getTradeFlag().equals("true")) {
			count++;
		}
		if(misReport.getGratuityIDFlag().equals("true")){
			count++;
		}
		if(misReport.getPensionAccNoFlag().equals("true")){
			count++;
		}
		if(misReport.getPensionBankFlag().equals("true")){
			count++;
		}

		logger.info("Count for Save columns : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (misReport.getEmpNameFlag().equals("true")) {
			addObj[int_count][0] = "misreport.empNameFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getDivFlag().equals("true")) {
			addObj[int_count][0] = "misreport.divFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getDeptFlag().equals("true")) {
			addObj[int_count][0] = "misreport.deptFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getCenterFlag().equals("true")) {
			addObj[int_count][0] = "misreport.centerFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getRankFlag().equals("true")) {
			addObj[int_count][0] = "misreport.rankFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getTypeFlag().equals("true")) {
			addObj[int_count][0] = "misreport.typeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getGradeFlage().equals("true")) {
			addObj[int_count][0] = "misreport.gradeFlage";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getShiftFlag().equals("true")) {
			addObj[int_count][0] = "misreport.shiftFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getDateBirthFlag().equals("true")) {
			addObj[int_count][0] = "misreport.dateBirthFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getDateRegFlag().equals("true")) {
			addObj[int_count][0] = "misreport.dateRegFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getDateOfConf().equals("true")) {
			addObj[int_count][0] = "misreport.dateOfConf";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getDateOfSepFlag().equals("true")) {
			addObj[int_count][0] = "misreport.dateOfSepFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getYearexperienceFlag().equals("true")) {
			addObj[int_count][0] = "misreport.yearexperienceFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getEmpStatusFlag().equals("true")) {
			addObj[int_count][0] = "misreport.empStatusFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getReportingToFlag().equals("true")) {
			addObj[int_count][0] = "misreport.reportingToFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getPfNoFlag().equals("true")) {
			addObj[int_count][0] = "misreport.pfNoFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getPanNoFlag().equals("true")) {
			addObj[int_count][0] = "misreport.panNoFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getEsicNoFlag().equals("true")) {
			addObj[int_count][0] = "misreport.esicNoFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getPayModeFlag().equals("true")) {
			addObj[int_count][0] = "misreport.payModeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getSalAccFlag().equals("true")) {
			addObj[int_count][0] = "misreport.salAccFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getSalBankFlag().equals("true")) {
			addObj[int_count][0] = "misreport.salBankFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getReimbAccFlag().equals("true")) {
			addObj[int_count][0] = "misreport.reimbAccFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getReimbBankFlag().equals("true")) {
			addObj[int_count][0] = "misreport.reimbBankFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getCtcFlag().equals("true")) {
			addObj[int_count][0] = "misreport.ctcFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getSalaryFlag().equals("true")) {
			addObj[int_count][0] = "misreport.salaryFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getAccCategoryFlag().equals("true")) {
			addObj[int_count][0] = "misreport.accCategoryFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getCostCenterFlag().equals("true")) {
			addObj[int_count][0] = "misreport.costCenterFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getSubCostCenterFlag().equals("true")) {
			addObj[int_count][0] = "misreport.subCostCenterFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		// FOR GROUP JOIN DATE
		if (misReport.getGroupjoindateFlag().equals("true")) {
			addObj[int_count][0] = "misreport.groupjoindateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		// Gender
		if (misReport.getGenderFlag().equals("true")) {
			addObj[int_count][0] = "misreport.genderFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		// Mobile No
		if (misReport.getMobFlag().equals("true")) {
			addObj[int_count][0] = "misreport.mobFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		// Email Id
		if (misReport.getEmailFlag().equals("true")) {
			addObj[int_count][0] = "misreport.emailFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		// Age
		if (misReport.getAgeFlag().equals("true")) {
			addObj[int_count][0] = "misreport.ageFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		// Customer Ref No
		if (misReport.getCustomerRefNoFlag().equals("true")) {
			addObj[int_count][0] = "misreport.customerRefNoFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		// Pay Bill
		if (misReport.getPaybillFlag().equals("true")) {
			addObj[int_count][0] = "misreport.paybillFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		// Debit
		if (misReport.getDebitFlag().equals("true")) {
			addObj[int_count][0] = "misreport.debitFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		// Pay Scale
		if (misReport.getRoleFlag().equals("true")) {
			addObj[int_count][0] = "misreport.roleFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}

		if (misReport.getEmpGradeFlag().equals("true")) {
			addObj[int_count][0] = "misreport.empGradeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if (misReport.getTradeFlag().equals("true")) {
			addObj[int_count][0] = "misreport.tradeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		if(misReport.getGratuityIDFlag().equals("true")){
			addObj[int_count][0] = "";
			addObj[int_count][1]="Y";
			int_count++;
		}
		if(misReport.getPensionAccNoFlag().equals("true")){
			addObj[int_count][0]="";
			addObj[int_count][1]="Y";
			int_count++;
		}
		if(misReport.getPensionBankFlag().equals("true")){
			addObj[int_count][0]="";
			addObj[int_count][1]="Y";
			int_count++;
		}
		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addColumns = new Object[int_count][3];
			for (int i = 0; i < addColumns.length; i++) {
				addColumns[i][0] = maxCode[0][0];
				addColumns[i][1] = addObj[i][0];
				logger.info("addColumns[" + i + "][1]  : " + addColumns[i][1]);
				addColumns[i][2] = addObj[i][1];
				logger.info("addColumns[" + i + "][2]  : " + addColumns[i][2]);
			}
			String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertColumns, addColumns);
			return result;
		}
	}

	public boolean saveSortOptions(AccMISReport misReport) {
		boolean result = false;
		int count = 0;
		if (!misReport.getSortBy().equals("1")) {
			count++;
		}
		if (!misReport.getHiddenSortBy().equals("")) {
			count++;
		}
		if (!misReport.getSortByAsc().equals("")) {
			count++;
		}
		if (!misReport.getSortByDsc().equals("")) {
			count++;
		}
		if (!misReport.getSortByOrder().equals("")) {
			count++;
		}
		if (!misReport.getThenBy1().equals("1")) {
			count++;
		}
		if (!misReport.getHiddenThenBy1().equals("")) {
			count++;
		}
		if (!misReport.getThenByOrder1Asc().equals("")) {
			count++;
		}
		if (!misReport.getThenByOrder1Dsc().equals("")) {
			count++;
		}
		if (!misReport.getThenByOrder1().equals("")) {
			count++;
		}
		if (!misReport.getThenBy2().equals("1")) {
			count++;
		}
		if (!misReport.getHiddenThenBy2().equals("")) {
			count++;
		}
		if (!misReport.getThenByOrder2Asc().equals("")) {
			count++;
		}
		if (!misReport.getThenByOrder2Dsc().equals("")) {
			count++;
		}
		if (!misReport.getThenByOrder2().equals("")) {
			count++;
		}
		if (!misReport.getHiddenColumns().equals("")) {
			count++;
		}

		logger.info("Count for Save sort options : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!misReport.getSortBy().equals("1")) {
			addObj[int_count][0] = "sortBy";
			addObj[int_count][1] = checkNull(misReport.getSortBy().trim());
			int_count++;
		}
		if (!misReport.getHiddenSortBy().equals("")) {
			addObj[int_count][0] = "hiddenSortBy";
			addObj[int_count][1] = checkNull(misReport.getHiddenSortBy().trim());
			int_count++;
		}
		logger.info("getSortByAsc...." + misReport.getSortByAsc());
		if (!misReport.getSortByAsc().equals("")) {
			addObj[int_count][0] = "sortByAsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByAsc().trim());
			int_count++;
		}
		logger.info("getSortByDsc...." + misReport.getSortByDsc());
		if (!misReport.getSortByDsc().equals("")) {
			addObj[int_count][0] = "sortByDsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByDsc().trim());
			int_count++;
		}
		if (!misReport.getSortByOrder().equals("")) {
			addObj[int_count][0] = "sortByOrder";
			addObj[int_count][1] = checkNull(misReport.getSortByOrder().trim());
			int_count++;
		}
		if (!misReport.getThenBy1().equals("1")) {
			addObj[int_count][0] = "thenBy1";
			addObj[int_count][1] = checkNull(misReport.getThenBy1().trim());
			int_count++;
		}
		if (!misReport.getHiddenThenBy1().equals("")) {
			addObj[int_count][0] = "hiddenThenBy1";
			addObj[int_count][1] = checkNull(misReport.getHiddenThenBy1()
					.trim());
			int_count++;
		}
		if (!misReport.getThenByOrder1Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Asc().trim());
			int_count++;
		}
		if (!misReport.getThenByOrder1Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Dsc().trim());
			int_count++;
		}
		if (!misReport.getThenByOrder1().equals("")) {
			addObj[int_count][0] = "thenByOrder1";
			addObj[int_count][1] = checkNull(misReport.getThenByOrder1().trim());
			int_count++;
		}
		if (!misReport.getThenBy2().equals("1")) {
			addObj[int_count][0] = "thenBy2";
			addObj[int_count][1] = checkNull(misReport.getThenBy2().trim());
			int_count++;
		}
		if (!misReport.getHiddenThenBy2().equals("")) {
			addObj[int_count][0] = "hiddenThenBy2";
			addObj[int_count][1] = checkNull(misReport.getHiddenThenBy2()
					.trim());
			int_count++;
		}
		if (!misReport.getThenByOrder2Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Asc().trim());
			int_count++;
		}
		if (!misReport.getThenByOrder2Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Dsc().trim());
			int_count++;
		}
		if (!misReport.getThenByOrder2().equals("")) {
			addObj[int_count][0] = "thenByOrder2";
			addObj[int_count][1] = checkNull(misReport.getThenByOrder2().trim());
			int_count++;
		}
		if (!misReport.getHiddenColumns().equals("")) {
			addObj[int_count][0] = "hiddenColumns";
			addObj[int_count][1] = checkNull(misReport.getHiddenColumns()
					.trim());
			int_count++;
		}

		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addSortOptions = new Object[int_count][3];
			for (int i = 0; i < addSortOptions.length; i++) {
				addSortOptions[i][0] = maxCode[0][0];
				addSortOptions[i][1] = addObj[i][0];
				logger.info("addSortOptions[" + i + "][1]  : "
						+ addSortOptions[i][1]);
				addSortOptions[i][2] = addObj[i][1];
				logger.info("addSortOptions[" + i + "][2]  : "
						+ addSortOptions[i][2]);
			}
			String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertSortOptions,
					addSortOptions);

			return result;
		}
	}

	public boolean saveAdvancedFilters(AccMISReport misReport) {
		boolean result = false;
		int count = 0;
		if (!misReport.getAgeSelect().equals("")) {
			count++;
		}
		if (!misReport.getAgeFrom().equals("")) {
			count++;
		}
		if (!misReport.getAgeTo().equals("")) {
			count++;
		}
		if (!misReport.getGrossSalSelect().equals("")) {
			count++;
		}
		if (!misReport.getGrossSalFrom().equals("")) {
			count++;
		}
		if (!misReport.getGrossSalTo().equals("")) {
			count++;
		}
		if (!misReport.getCtcSelect().equals("")) {
			count++;
		}
		if (!misReport.getCtcFrom().equals("")) {
			count++;
		}
		if (!misReport.getCtcTo().equals("")) {
			count++;
		}
		if (!misReport.getDobSelect().equals("")) {
			count++;
		}
		if (!misReport.getDOBfromDate().equals("")) {
			count++;
		}
		if (!misReport.getDOBToDate().equals("")) {
			count++;
		}
		if (!misReport.getDojSelect().equals("")) {
			count++;
		}
		if (!misReport.getRegularFromDate().equals("")) {
			count++;
		}
		if (!misReport.getRegularToDate().equals("")) {
			count++;
		}
		if (!misReport.getDocSelect().equals("")) {
			count++;
		}
		if (!misReport.getConfFromDate().equals("")) {
			count++;
		}
		if (!misReport.getConfToDate().equals("")) {
			count++;
		}
		if (!misReport.getDolSelect().equals("")) {
			count++;
		}
		if (!misReport.getSepFromDate().equals("")) {
			count++;
		}
		if (!misReport.getSepToDate().equals("")) {
			count++;
		}

		// GROUP JOINING DATE

		if (!misReport.getGroupjoindateSelect().equals("")) {
			count++;
		}
		if (!misReport.getGroupjoinFromDate().equals("")) {
			count++;
		}
		if (!misReport.getGroupjoinToDate().equals("")) {
			count++;
		}

		if (!misReport.getDateFormatSelect().equals("")) {
			count++;
		}

		logger.info("Count for Save advance filters : " + count);

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!misReport.getAgeSelect().equals("")) {
			addObj[int_count][0] = "ageSelect";
			addObj[int_count][1] = checkNull(misReport.getAgeSelect().trim());
			int_count++;
		}
		if (!misReport.getAgeFrom().equals("")) {
			addObj[int_count][0] = "ageFrom";
			addObj[int_count][1] = checkNull(misReport.getAgeFrom().trim());
			int_count++;
		}
		if (!misReport.getAgeTo().equals("")) {
			addObj[int_count][0] = "ageTo";
			addObj[int_count][1] = checkNull(misReport.getAgeTo().trim());
			int_count++;
		}
		if (!misReport.getGrossSalSelect().equals("")) {
			addObj[int_count][0] = "grossSalSelect";
			addObj[int_count][1] = checkNull(misReport.getGrossSalSelect()
					.trim());
			int_count++;
		}
		if (!misReport.getGrossSalFrom().equals("")) {
			addObj[int_count][0] = "grossSalFrom";
			addObj[int_count][1] = checkNull(misReport.getGrossSalFrom().trim());
			int_count++;
		}
		if (!misReport.getGrossSalTo().equals("")) {
			addObj[int_count][0] = "grossSalTo";
			addObj[int_count][1] = checkNull(misReport.getGrossSalTo().trim());
			int_count++;
		}
		if (!misReport.getCtcSelect().equals("")) {
			addObj[int_count][0] = "ctcSelect";
			addObj[int_count][1] = checkNull(misReport.getCtcSelect().trim());
			int_count++;
		}
		if (!misReport.getCtcFrom().equals("")) {
			addObj[int_count][0] = "ctcFrom";
			addObj[int_count][1] = checkNull(misReport.getCtcFrom().trim());
			int_count++;
		}
		if (!misReport.getCtcTo().equals("")) {
			addObj[int_count][0] = "ctcTo";
			addObj[int_count][1] = checkNull(misReport.getCtcTo().trim());
			int_count++;
		}
		if (!misReport.getDobSelect().equals("")) {
			addObj[int_count][0] = "dobSelect";
			addObj[int_count][1] = checkNull(misReport.getDobSelect().trim());
			int_count++;
		}
		if (!misReport.getDOBfromDate().equals("")) {
			addObj[int_count][0] = "misreport.DOBfromDate";
			addObj[int_count][1] = checkNull(misReport.getDOBfromDate().trim());
			int_count++;
		}
		if (!misReport.getDOBToDate().equals("")) {
			addObj[int_count][0] = "misreport.DOBToDate";
			addObj[int_count][1] = checkNull(misReport.getDOBToDate().trim());
			int_count++;
		}
		if (!misReport.getDojSelect().equals("")) {
			addObj[int_count][0] = "dojSelect";
			addObj[int_count][1] = checkNull(misReport.getDojSelect().trim());
			int_count++;
		}
		if (!misReport.getRegularFromDate().equals("")) {
			addObj[int_count][0] = "misreport.regularFromDate";
			addObj[int_count][1] = checkNull(misReport.getRegularFromDate()
					.trim());
			int_count++;
		}
		if (!misReport.getRegularToDate().equals("")) {
			addObj[int_count][0] = "misreport.regularToDate";
			addObj[int_count][1] = checkNull(misReport.getRegularToDate()
					.trim());
			int_count++;
		}
		if (!misReport.getDocSelect().equals("")) {
			addObj[int_count][0] = "docSelect";
			addObj[int_count][1] = checkNull(misReport.getDocSelect().trim());
			int_count++;
		}
		if (!misReport.getConfFromDate().equals("")) {
			addObj[int_count][0] = "misreport.confFromDate";
			addObj[int_count][1] = checkNull(misReport.getConfFromDate().trim());
			int_count++;
		}
		if (!misReport.getConfToDate().equals("")) {
			addObj[int_count][0] = "misreport.confToDate";
			addObj[int_count][1] = checkNull(misReport.getConfToDate().trim());
			int_count++;
		}
		if (!misReport.getDolSelect().equals("")) {
			addObj[int_count][0] = "dolSelect";
			addObj[int_count][1] = checkNull(misReport.getDolSelect().trim());
			int_count++;
		}
		if (!misReport.getSepFromDate().equals("")) {
			addObj[int_count][0] = "misreport.leaveFromDate";
			addObj[int_count][1] = checkNull(misReport.getSepFromDate().trim());
			int_count++;
		}
		if (!misReport.getSepToDate().equals("")) {
			addObj[int_count][0] = "misreport.leaveToDate";
			addObj[int_count][1] = checkNull(misReport.getSepToDate().trim());
			int_count++;
		}

		// Group JOINING DATE

		if (!misReport.getGroupjoindateSelect().equals("")) {
			addObj[int_count][0] = "groupjoindateSelect";
			addObj[int_count][1] = checkNull(misReport.getGroupjoindateSelect()
					.trim());
			int_count++;
		}
		if (!misReport.getGroupjoinFromDate().equals("")) {
			addObj[int_count][0] = "misreport.groupjoinFromDate";
			addObj[int_count][1] = checkNull(misReport.getGroupjoinFromDate()
					.trim());
			int_count++;
		}
		if (!misReport.getGroupjoinToDate().equals("")) {
			addObj[int_count][0] = "misreport.groupjoinToDate";
			addObj[int_count][1] = checkNull(misReport.getGroupjoinToDate()
					.trim());
			int_count++;
		}

		if (!misReport.getDateFormatSelect().equals("")) {
			addObj[int_count][0] = "misreport.dateFormatSelect";
			addObj[int_count][1] = checkNull(misReport.getDateFormatSelect()
					.trim());
			int_count++;
		}

		logger.info("int_count     : " + int_count);
		if (int_count == 0) {
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addAdvanceFilters = new Object[int_count][3];
			for (int i = 0; i < addAdvanceFilters.length; i++) {
				addAdvanceFilters[i][0] = maxCode[0][0];
				addAdvanceFilters[i][1] = addObj[i][0];
				logger.info("addAdvanceFilters[" + i + "][1]  : "
						+ addAdvanceFilters[i][1]);
				addAdvanceFilters[i][2] = addObj[i][1];
				logger.info("addAdvanceFilters[" + i + "][2]  : "
						+ addAdvanceFilters[i][2]);
			}
			String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertAdvFilters,
					addAdvanceFilters);
			return result;
		}
	}

	public void setDetailOnScreen(AccMISReport misreport) {

		String dtlQuery = "SELECT HRMS_MISREPORT_DTL.REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL "
				+ "  INNER JOIN HRMS_MISREPORT_HDR  ON (HRMS_MISREPORT_HDR.REPORT_ID= HRMS_MISREPORT_DTL.REPORT_ID )"
				+ " WHERE HRMS_MISREPORT_DTL.REPORT_ID ="
				+ misreport.getReportId();

		Object[][] dtlObj = getSqlModel().getSingleResult(dtlQuery);
		if (dtlObj != null && dtlObj.length > 0) {
			try {
				// Class modelClass = Class.forName(aClass);
				for (int i = 0; i < dtlObj.length; i++) {

					String methodStr = "";
					logger.info("dtlObj[" + i + "][1] "
							+ String.valueOf(dtlObj[i][1]));
					String fieldName = String.valueOf(dtlObj[i][1]).replace(
							".", "-");
					logger.info("fieldName " + fieldName);
					String[] fieldNames = fieldName.split("-");
					if (fieldNames.length > 1)
						methodStr = fieldNames[1];
					else
						methodStr = fieldNames[0];
					logger.info("methodStr  : " + methodStr);
					if (String.valueOf(dtlObj[i][2]).trim().equals("Y")) {
						dtlObj[i][2] = "true";
					}
					if (String.valueOf(dtlObj[i][2]).trim().equals("true")
							&& (String.valueOf(dtlObj[i][1]).trim().equals(
									"sortByAsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("sortByDsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder1Asc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder1Dsc")
									|| String.valueOf(dtlObj[i][1]).trim()
											.equals("thenByOrder2Asc") || String
									.valueOf(dtlObj[i][1]).trim().equals(
											"thenByOrder2Dsc"))) {
						dtlObj[i][2] = "checked";
					}
					Method modelMethod = AccMISReport.class.getDeclaredMethod(
							"set" + initCap(methodStr), String.class);
					modelMethod.invoke(misreport, String.valueOf(dtlObj[i][2]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static String initCap(String name) {
		String properName = "";

		try {
			properName = name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
		} catch (Exception e) {
			return properName;
		}
		return properName;
	}

	public String getValue(Object[][] obj, int columnNo, String fieldName,
			int inORnotin) {
		String str = "";
		String in_out_str = "";
		if (inORnotin == 0)
			in_out_str = " IN ";
		else
			in_out_str = " NOT IN ";
		try {
			if (obj != null && obj.length > 0) {
				logger.info("obj=====" + obj.length);
				logger.info("obj[0]=====" + obj[0].length);
				logger.info("columnNo=====" + columnNo);
				logger.info("1=====");
				str = fieldName + in_out_str + "( ";
				for (int i = 0; i < obj.length; i++) {
					str += String.valueOf(obj[i][columnNo]) + ",";
					if (i != 0 && i % 100 == 0 && obj.length % 100 != 0) {
						str = str.substring(0, str.length() - 1);
						str += ") ";
						logger.info("2=====");
						if (obj.length > i) {
							logger.info("3=====");
							str += " OR " + fieldName + in_out_str + "( ";
						}
					}
				}// end of loop
				str = str.substring(0, str.length() - 1);
				str += " )";
				logger.info("5=====");
			}

			logger.info("6=====");
		} catch (RuntimeException e) {
			logger.error("Exception in getValue metjod : " + e);
		}

		return str;

	}

	private String getSelectedDateFormat(AccMISReport bean) {
		String dtaeFormat = "DD-MM-YYYY";
		try {
			dtaeFormat = bean.getDateFormatSelect().trim();
			if (dtaeFormat.equals("")) {
				dtaeFormat = "DD-MM-YYYY";
			}
		} catch (Exception e) {
			dtaeFormat = "DD-MM-YYYY";
		}
		return dtaeFormat;
	}

}
