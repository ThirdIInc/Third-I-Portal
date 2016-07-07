package org.paradyne.model.admin.srd;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.lsmp.djep.xjep.XJep;
import org.nfunk.jep.Node;
import org.paradyne.bean.admin.srd.CTCDetail;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Prajakta.Bhandare
 * @date 11 Jan 2013
 */
public class CTCDetailModel extends ModelBase {// Start class

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CTCDetailModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	AuditTrail trial = null;
	/**
	 * Method to get general Employee Details
	 * 
	 * @param ctcDetail
	 * @param request
	 */
	public void getEmpDetails(CTCDetail ctcDetail, HttpServletRequest request) {
		Object obj[] = new Object[1];
		obj[0] = ctcDetail.getEmpId();
		System.out.println("............................*********" + obj[0]);
		Object ctcObj[][] = getSqlModel().getSingleResult(getQuery(1), obj);
		if (ctcObj != null && ctcObj.length > 0) {// if data
			ctcDetail.setEmpCenter(checkNull(String.valueOf(ctcObj[0][0])));
			ctcDetail.setEmpRank(checkNull(String.valueOf(ctcObj[0][1])));
			ctcDetail.setPfFlag(checkNull(String.valueOf(ctcObj[0][5])));
			ctcDetail.setEmpAccNo(checkNull(String.valueOf(ctcObj[0][6])));
			ctcDetail.setEmpPANNo(checkNull(String.valueOf(ctcObj[0][7])));
			ctcDetail.setEmpDeptName(checkNull(String.valueOf(ctcObj[0][8])));
			ctcDetail.setEmpDeptId(checkNull(String.valueOf(ctcObj[0][9])));
			ctcDetail.setEmpJoinDate(checkNull(String.valueOf(ctcObj[0][10])));
			ctcDetail.setEmpGradeName(checkNull(String.valueOf(ctcObj[0][11])));
			ctcDetail.setEmpGradeId(checkNull(String.valueOf(ctcObj[0][12])));
			ctcDetail.setEmpToken(checkNull(String.valueOf(ctcObj[0][13])));
			ctcDetail.setEmpName(checkNull(String.valueOf(ctcObj[0][14])));
		}// end of if
	}
	
	/**
	 * Method to get general Employee Details
	 * 
	 * @param ctcDetail
	 * @param request
	 */
	public void getEmpRecord(CTCDetail ctcDetail, HttpServletRequest request) {
		Object obj[] = new Object[1];
		obj[0] = ctcDetail.getEmpId();
		System.out.println("............................*********" + obj[0]);
		Object ctcObj[][] = getSqlModel().getSingleResult(getQuery(1), obj);
		if (ctcObj != null && ctcObj.length > 0) {// if data
			ctcDetail.setEmpCenter(checkNull(String.valueOf(ctcObj[0][0])));
			ctcDetail.setEmpRank(checkNull(String.valueOf(ctcObj[0][1])));
			ctcDetail.setGrsAmt(checkNull(String.valueOf(ctcObj[0][4])));
			ctcDetail.setPfFlag(checkNull(String.valueOf(ctcObj[0][5])));
			ctcDetail.setEmpAccNo(checkNull(String.valueOf(ctcObj[0][6])));
			ctcDetail.setEmpPANNo(checkNull(String.valueOf(ctcObj[0][7])));
			ctcDetail.setEmpDeptName(checkNull(String.valueOf(ctcObj[0][8])));
			ctcDetail.setEmpDeptId(checkNull(String.valueOf(ctcObj[0][9])));
			ctcDetail.setEmpJoinDate(checkNull(String.valueOf(ctcObj[0][10])));
			ctcDetail.setEmpGradeName(checkNull(String.valueOf(ctcObj[0][11])));
			ctcDetail.setEmpGradeId(checkNull(String.valueOf(ctcObj[0][12])));
			ctcDetail.setEmpToken(checkNull(String.valueOf(ctcObj[0][13])));
			ctcDetail.setEmpName(checkNull(String.valueOf(ctcObj[0][14])));
		}// end of if
		showIncrement(ctcDetail, request);
	}


	/**
	 * Method to set formula, salary grade and gross amount
	 * 
	 * @param ctcDetail
	 * @param request
	 */
	public void showIncrement(CTCDetail ctcDetail, HttpServletRequest request) {
		Object obj[] = new Object[1];
		String empId = ctcDetail.getEmpId();
		obj[0] = ctcDetail.getEmpId();
		Object empCtcObject[][] = getSqlModel().getSingleResult(getQuery(2),
				obj);
		if (empCtcObject != null && empCtcObject.length > 0) {// if data
			ctcDetail.setCtc(String.valueOf(empCtcObject[0][0]));
			ctcDetail.setFormulaId(Utility.checkNull(String
					.valueOf(empCtcObject[0][1])));
			ctcDetail.setFormula(Utility.checkNull(String
					.valueOf(empCtcObject[0][2])));
			ctcDetail.setSalGradeName(Utility.checkNull(String
					.valueOf(empCtcObject[0][3])));
			ctcDetail.setSalGradeId(Utility.checkNull(String
					.valueOf(empCtcObject[0][4])));
			ctcDetail.setGrsAmt(String.valueOf(empCtcObject[0][5]));
		}// end of if
		else {// start of else
			ctcDetail.setCtc("");
			ctcDetail.setFormulaId("");
			ctcDetail.setFormula("");
			ctcDetail.setSalGradeId("");
			ctcDetail.setSalGradeName("");
			ctcDetail.setGrsAmt("");
		}// end of else
		String query = "";
		if (ctcDetail.isEditDetail()) {// if in edit mode
			query = "SELECT CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),NVL(CREDIT_AMT,0) , "
					+ " DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_EMP_CREDIT "
					+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)"
					+ " WHERE EMP_ID="
					+ empId
					+ " UNION ALL "
					+ " SELECT CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),0 , "
					+ " DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
					+ " WHERE CREDIT_HEAD_TYPE='S' and CREDIT_CODE not in (select HRMS_EMP_CREDIT.CREDIT_CODE from HRMS_EMP_CREDIT where EMP_ID="
					+ empId + ")" + " ORDER BY 4,5";
		}// end of if in edit mode
		else {// start of else
			query = "SELECT  CREDIT_NAME,DECODE(CREDIT_PERIODICITY,'H','Half Yearly','A','Annually','Q','Quarterly','M','Monthly'),NVL(CREDIT_AMT,0)"
					+ " ,DECODE(CREDIT_PERIODICITY,'H','3','A','4','Q','2','M','1') AS ORD,HRMS_EMP_CREDIT.CREDIT_CODE FROM HRMS_EMP_CREDIT "
					+ " RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE)"
					+ " WHERE EMP_ID="
					+ empId
					+ " and HRMS_EMP_CREDIT.CREDIT_AMT>0"
					+ " ORDER BY ORD,HRMS_EMP_CREDIT.CREDIT_CODE";
		}// end of else
		showIncrementHistory(ctcDetail, request, query);
		ctcMethod(ctcDetail);
		getIncrementPeriod(ctcDetail);
	}

	/**
	 * Method to set salary header,Period, Amount, Annual and monthly earnings
	 * 
	 * @param ctcDetail
	 * @param request
	 * @param query
	 */
	public void showIncrementHistory(CTCDetail ctcDetail,
			HttpServletRequest request, String query) {
		try {
			ctcDetail.setNoData("false");
			Object salHeaderObj[][] = getSqlModel().getSingleResult(query);
			double monthAMT = 0.00;
			double annualSum = 0.00;
			if (salHeaderObj != null && salHeaderObj.length > 0) {// if data
				ArrayList innerList = new ArrayList();
				for (int i = 0; i < salHeaderObj.length; i++) {// for loop to
																// retrieve
																// record list
					CTCDetail bean = new CTCDetail();
					bean.setCtcNameItt(String.valueOf(salHeaderObj[i][0])
							.toUpperCase());
					bean.setCtcPeriodItt(String.valueOf(salHeaderObj[i][1]));
					bean.setCtcAmountItt(Utility.twoDecimals(String
							.valueOf(salHeaderObj[i][2])));
					bean.setCtcNameIdItt(String.valueOf(salHeaderObj[i][4]));

					if (String.valueOf(salHeaderObj[i][1]).equals("Monthly")) {// if
																				// period
																				// is
																				// monthly
						monthAMT += Double.parseDouble(formatter
								.format(Double.parseDouble(String
										.valueOf(salHeaderObj[i][2]))));
						annualSum += Double.parseDouble(formatter
								.format(Double.parseDouble(String
										.valueOf(salHeaderObj[i][2])) * 12));
					}// end of else if
					else if (String.valueOf(salHeaderObj[i][1]).equals(
							"Quarterly")) {// if period is Quarterly
						annualSum += Double.parseDouble(formatter
								.format(Double.parseDouble(String
										.valueOf(salHeaderObj[i][2])) * 4));
					}// end of else if
					else if (String.valueOf(salHeaderObj[i][1]).equals(
							"Half Yearly")) {// if period is Half Yearly
						annualSum += Double.parseDouble(formatter
								.format(Double.parseDouble(String
										.valueOf(salHeaderObj[i][2])) * 2));
					}// end of else if
					else if (String.valueOf(salHeaderObj[i][1]).equals(
							"Annually")) {// if period is Annually
						annualSum += Double.parseDouble(formatter
								.format(Double.parseDouble(String
										.valueOf(salHeaderObj[i][2]))));
					}// end of else if
					innerList.add(bean);
				}// end of for loop
				ctcDetail.setSalaryHdr(innerList);
				ctcDetail.setTotalAmt(formatter.format(Double
						.parseDouble(String.valueOf(monthAMT))));
				ctcDetail.setAnnualAmt(formatter.format(Double
						.parseDouble(String.valueOf(annualSum))));
			}// end of if data
			else {// start of else
				ctcDetail.setSalaryHdr(null);
				ctcDetail.setNoData("true");
				ctcDetail.setTotalAmt(formatter.format((monthAMT)));
				ctcDetail.setAnnualAmt(formatter.format((annualSum)));
				ctcMethod(ctcDetail);
			}// end of else
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to set date for increment period
	 * 
	 * @param ctcDetail
	 */
	public void getIncrementPeriod(CTCDetail ctcDetail) {
		HashMap periodMap = new HashMap();
		try {
			Object obj[] = new Object[1];
			obj[0] = ctcDetail.getEmpId();
			Object[][] incPeriodObj = getSqlModel().getSingleResult(
					getQuery(3), obj);
			if (incPeriodObj != null && incPeriodObj.length > 0) {// if data
				for (int i = 0; i < incPeriodObj.length; i++) {// for loop to
																// put values in
																// hash map
					periodMap.put(String.valueOf(incPeriodObj[i][0]), String
							.valueOf(incPeriodObj[i][1]));

				}// end of for loop
				periodMap = (HashMap<Object, Object>) org.paradyne.lib.Utility
						.sortMapByValue(periodMap, null, true);
			}// end of if
			else {// start of else
				periodMap.put("N/A", "N/A");
			}// end of else
			ctcDetail.setIncrementHistoryMap(periodMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to calculate Credits based on formula and gross amount
	 * 
	 * @param ctcDetail
	 * @param request
	 * @return
	 */
	public Object[][] showFormula(CTCDetail ctcDetail,
			HttpServletRequest request) {

		Object[][] tableData = null;
		Object obj[] = new Object[1];
		obj[0] = ctcDetail.getEmpId();
		try {

			Object[][] creditsObj = getSqlModel().getSingleResult(getQuery(4),
					obj);

			if (creditsObj != null && creditsObj.length > 0) {// if data
				tableData = new Object[creditsObj.length][4];
				for (int i = 0; i < creditsObj.length; i++) {// for loop
					tableData[i][0] = String.valueOf(creditsObj[i][0]);// credit
																		// code
					tableData[i][1] = String.valueOf(creditsObj[i][1]);// credit
																		// name
					tableData[i][2] = String.valueOf(creditsObj[i][2]);// credit
																		// periodicity
					tableData[i][3] = String.valueOf(creditsObj[i][3]);// credit
																		// amount
				}// end of for loop
			}// end of if
			Object objFrm[] = new Object[1];
			objFrm[0] = ctcDetail.getFormulaId();
			Object[][] formulaObj = getSqlModel().getSingleResult(getQuery(5),
					objFrm);
			if (ctcDetail.getGrsAmt().length() > 0) {// if gross amount
				double CTC = Double.parseDouble(ctcDetail.getGrsAmt());
				for (int j = 0; j < tableData.length; j++) {// start for loop to
															// set credit amount
					tableData[j][3] = "0.00";
				}// end for loop
				ArrayList<String> diffData = new ArrayList<String>();
				ArrayList<String> formData = new ArrayList<String>();
				for (int j = 0; j < tableData.length; j++) {// start j for loop
					/*
					 * Loop for checking length of salary codes This loop
					 * calculates new salary amount according to salary code .
					 */
					for (int i = 0; i < formulaObj.length; i++) {// start i
																	// for loop
						/*
						 * Loop for checking formula type according to formula
						 * code.
						 */
						String exec = "";
						if (String.valueOf(tableData[j][0]).equals(
								String.valueOf(formulaObj[i][0]))) {// start 1st
																	// if
							/*
							 * If loop for checking equal salary code It
							 * calculates new amount if salary code is equal to
							 * salary code of formula
							 */
							String sal_type = String.valueOf(formulaObj[i][1]);
							String sal_formula = String
									.valueOf(formulaObj[i][2]);
							if (sal_type.trim().equals("Fi")) {// start 2nd if

								/*
								 * if loop for calculating new amount when
								 * salary type is fixed
								 */

								tableData[j][3] = sal_formula;
							} // end of if
							else if (sal_type.trim().equals("Fr")) {// start
																	// else if
																	// of 2nd if

								/*
								 * if loop for calculating new amount when
								 * salary type is formula
								 */

								try {
									exec = executeFormula(sal_formula, CTC,
											tableData, formData);
									logger.info("exec===" + exec);
								} catch (Exception e) {
									e.printStackTrace();
								}
								tableData[j][3] = formatter.format(Double
										.parseDouble(String.valueOf(exec)));
								// logger.info("tableData[j][3]==="+tableData[j][3]);
							} // end of else if
							else if (sal_type.trim().equals("Df")) {// start
																	// else if

								/*
								 * if loop for calculating new amount when
								 * salary type is difference
								 */

								diffData.add(String.valueOf(tableData[j][0]));
								tableData[j][3] = "0.00";
							}// end of else if

						}// end of if
					}// end of i loop
				} // end of j loop

				double sum = 0.00;

				for (int k = 0; k < tableData.length; k++) {// start k for loop
					/*
					 * calculates difference according to periodicity
					 */
					try {

						if (String.valueOf(tableData[k][2]).equals("Annually")) {// start
																					// if
																					// Annually
							sum += (Double.parseDouble(formatter.format(Double
									.parseDouble(String
											.valueOf(tableData[k][3])))));
						} // end of if - Annually
						else if (String.valueOf(tableData[k][2]).equals(
								"Quarterly")) {// start if Quarterly
							sum += (Double.parseDouble(formatter.format(Double
									.parseDouble(String
											.valueOf(tableData[k][3])) * 4)));
						} // end of else if - Quarterly
						else if (String.valueOf(tableData[k][2]).equals(
								"Half Yearly")) {// start if Half Yearly
							sum += (Double.parseDouble(formatter.format(Double
									.parseDouble(String
											.valueOf(tableData[k][3])) * 2)));
						} // end of else if = Half yearly
						else if (String.valueOf(tableData[k][2]).equals(
								"Monthly")) {// start if Monthly
							// logger.info("String.valueOf(tableData[k][3]====)"+String.valueOf(tableData[k][3]));
							sum += (Double.parseDouble(formatter.format(Double
									.parseDouble(String
											.valueOf(tableData[k][3])) * 12)));
						} // end of else if - Monthly
					} catch (Exception e) {
						e.printStackTrace();
					}// end of try-catch block
				}// end of k loop

				double cal = 0.00;
				if (CTC >= sum) {// start if CTC greater than sum
					cal = ((CTC - sum) / 12);
				}// end if
				if (CTC <= sum) {// start if CTC less than sum
					cal = ((sum - CTC) / 12);
				}// end if
				for (int j = 0; j < tableData.length; j++) {// start j for loop

					for (int i = 0; i < diffData.size(); i++) {// start i for
																// loop
						if (String.valueOf(tableData[j][0]).equals(
								diffData.get(i))) {// start if
							if (String.valueOf(tableData[j][2]).equals(
									"Annually")) {// start 1st if
								tableData[j][3] = formatter.format(cal * 12);
							} // end of 1st if
							if (String.valueOf(tableData[j][2]).equals(
									"Half Yearly")) {// start 2nd if
								tableData[j][3] = formatter.format(cal * 6);
							} // end of 2nd if
							if (String.valueOf(tableData[j][2]).equals(
									"Quarterly")) {// start 3rd if
								tableData[j][3] = formatter.format(cal * 4);
							} // end of 3rd if
							if (String.valueOf(tableData[j][2]).equals(
									"Monthly")) {// start 4th if
								tableData[j][3] = formatter.format(cal);
							} // end of 4th if
						} // end of if
					} // end of i loop
				}// end of j loop
			}// end of if
			// Calculation for total Annually
			double temp = 0;
			for (int i = 0; i < tableData.length; i++) {// start i for loop

				if (String.valueOf(tableData[i][2]).equals("Annually")) {// start
																			// 1st
																			// if
					temp += (Double.parseDouble(formatter.format(Double
							.parseDouble(String.valueOf(tableData[i][3])))));
				}// end of 1st if
				if (String.valueOf(tableData[i][2]).equals("Half Yearly")) {// start
																			// 2nd
																			// if
					temp += Double.parseDouble(formatter.format(Double
							.parseDouble(String.valueOf(tableData[i][3])) * 2));
				}// end of 2nd if
				if (String.valueOf(tableData[i][2]).equals("Quarterly")) {// start
																			// 3rd
																			// if
					temp += Double.parseDouble(formatter.format(Double
							.parseDouble(String.valueOf(tableData[i][3])) * 4));
				}// end of 3rd if

				if (String.valueOf(tableData[i][2]).equals("Monthly")) {// start
																		// 4th
																		// if
					temp += Double
							.parseDouble(formatter.format(Double
									.parseDouble(String
											.valueOf(tableData[i][3])) * 12));
				}// end of 4th if

			}// end of i for loop
			ctcDetail.setAnnualAmt(formatter.format((temp)));
			// calculation for Monthly
			double monthTemp = 0;
			for (int i = 0; i < tableData.length; i++) {// start of i for loop
				if (String.valueOf(tableData[i][2]).equals("Monthly")) {// start
																		// 1st
																		// if
					monthTemp += Double.parseDouble(String
							.valueOf(tableData[i][3]));
				}// end 1st if
			}
			ctcDetail.setTotalAmt(formatter.format(monthTemp));
			// -----------------------------
			Object[][] periLength = getSqlModel().getSingleResult(getQuery(6));
			Object[][] mdata = null;
			Object[][] qdata = null;
			Object[][] hdata = null;
			Object[][] adata = null;
			if (periLength != null && periLength.length > 0) {// if data
				for (int i = 0; i < periLength.length; i++) {// start i for
																// loop
					if (String.valueOf(periLength[i][1]).equals("M")) {// start
																		// 1st
																		// if
						mdata = new Object[Integer.parseInt(String
								.valueOf(periLength[i][0]))][4];
					}// end 1st if
					if (String.valueOf(periLength[i][1]).equals("Q")) {// start
																		// 2nd
																		// if
						qdata = new Object[Integer.parseInt(String
								.valueOf(periLength[i][0]))][4];
					}// end 2nd if
					if (String.valueOf(periLength[i][1]).equals("H")) {// start
																		// 3rd
																		// if
						hdata = new Object[Integer.parseInt(String
								.valueOf(periLength[i][0]))][4];
					}// end 3rd if
					if (String.valueOf(periLength[i][1]).equals("A")) {// start
																		// 4th
																		// if
						adata = new Object[Integer.parseInt(String
								.valueOf(periLength[i][0]))][4];
					}// end 4th if
				}// end i for loop
			}// end if data
			double monthAMT = 0.0;
			double annualSum = 0.0;
			if (tableData != null && tableData.length > 0) {// if data
				ArrayList innerList = new ArrayList();
				for (int i = 0; i < tableData.length; i++) {// start i for loop
					CTCDetail bean = new CTCDetail();
					bean.setCtcNameItt(String.valueOf(tableData[i][1])
							.toUpperCase());
					bean.setCtcPeriodItt(String.valueOf(tableData[i][2]));
					bean.setCtcAmountItt(Utility.twoDecimals(String
							.valueOf(tableData[i][3])));
					bean.setCtcNameIdItt(String.valueOf(tableData[i][0]));

					if (String.valueOf(tableData[i][2]).equals("Monthly")) {// start
																			// if
						monthAMT += Double.parseDouble(formatter.format(Double
								.parseDouble(String.valueOf(tableData[i][3]))));
						annualSum += Double.parseDouble(formatter
								.format(Double.parseDouble(String
										.valueOf(tableData[i][3])) * 12));
					} else if (String.valueOf(tableData[i][2]).equals(
							"Quarterly")) {
						annualSum += Double.parseDouble(formatter
								.format(Double.parseDouble(String
										.valueOf(tableData[i][3])) * 4));
					} else if (String.valueOf(tableData[i][2]).equals(
							"Half Yearly")) {
						annualSum += Double.parseDouble(formatter
								.format(Double.parseDouble(String
										.valueOf(tableData[i][3])) * 2));
					} else if (String.valueOf(tableData[i][2]).equals(
							"Annually")) {
						annualSum += Double.parseDouble(formatter.format(Double
								.parseDouble(String.valueOf(tableData[i][3]))));
					}// end if

					innerList.add(bean);
				}// end i for loop
				// annualSum+=monthAMT;
				ctcDetail.setSalaryHdr(innerList);
				ctcDetail.setTotalAmt(formatter.format(Double
						.parseDouble(String.valueOf(monthAMT))));
				ctcDetail.setAnnualAmt(formatter.format(Double
						.parseDouble(String.valueOf(annualSum))));
			} // End of if
			// ----------------------
			for (int i = 0; i < tableData.length; i++) {// Start i for loop

				CTCDetail bean1 = new CTCDetail();
				bean1.setCtcNameId(String.valueOf(tableData[i][0]));
				bean1.setCtcName(String.valueOf(tableData[i][1]));
				bean1.setCtcPeriod(String.valueOf(tableData[i][2]));
				bean1.setCtcAmount(String.valueOf(tableData[i][3]));
				bean1.setGeneralFlag(ctcDetail.isGeneralFlag());
				// atlist.add(bean1);
			}// end i for loop
			ctcMethod(ctcDetail);
			getIncrementPeriod(ctcDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableData;
	}

	/**
	 * Method toexecute formula
	 * 
	 * @param sal_formula
	 * @param CTC
	 * @param tableData
	 * @param frmData
	 * @return String
	 */
	public String executeFormula(String sal_formula, double CTC,
			Object[][] tableData, ArrayList frmData) {

		try {

			String str[] = sal_formula.split("#");
			String frml = "";
			for (int i = 0; i < str.length; i++) {// start of 1st i for loop

				logger.info("Printing Str[i] " + str[i]);
				if (str[i].equals("CTC")) {// start if
					logger.info("Inside CTC");
					frml += "" + CTC;
				}// end if

				else {// start of 1st else
					String flag = "false", strValue = "";
					int cnt = 1;

					if (str[i].equals("") || str[i].equals("null")) {// if
																		// null
						strValue = str[i];
					}// end if null
					else if (str[i].length() == 1) {// if 1
						strValue = str[i];
					}// end if 1
					else if (str[i].contains("C"))
						strValue = str[i].substring(1, str[i].length());
					else
						strValue = str[i];

					for (int z = 0; z < tableData.length; z++) {// start z for
																// loop

						if (String.valueOf(strValue).trim().equals("" + cnt))// *12)/100
						// (1*12)/100
						{// start of 1st if
							logger.info("checking for count=======" + z);
							for (int j = 0; j < tableData.length; j++) {// start
																		// j for
																		// loop
								if (String.valueOf(tableData[j][0]).trim()
										.equals("" + cnt)) {// start if
									frml += String.valueOf(tableData[j][3]);
									flag = "true";
								}// end if
							}// end of j for loop
						}// end of 1st if
						cnt++;
					}// end z for loop
					if (flag.equals("false")) {// if flag is false
						frml += "" + strValue;// (1000*12)/100
						logger.info("frml======>>>" + frml);
					}// end if flag is false
				}// end of 1st else
			}// end of 1st i for loop
			return expressionEvaluate(frml);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Method toevaluate expression
	 * 
	 * @param formula
	 * @return String
	 */
	public String expressionEvaluate(String formula) {
		try {
			XJep j = new XJep();
			try {
				j.addStandardConstants();
				j.addStandardFunctions();
				j.addComplex();
				j.setAllowUndeclared(true);
				j.setImplicitMul(true);
				j.setAllowAssignment(true);
				// parse expression
				Node node = j.parse(formula); // print it
				Node processed = j.preprocess(node);
				Node simp = j.simplify(processed);
				Object value = j.evaluate(simp);
				logger.info("processed=========" + value.toString());
				double vv = Double.parseDouble(String.valueOf(value));
				logger.info("value=========" + vv);
				return "" + vv;
				// return
				// ""+Utility.twoDecimals(Double.parseDouble(String.valueOf((value))));
			} catch (Exception e) {
			}
		} catch (Exception e) {
			return "0";
		}
		return "0";
	}

	/**
	 * Method toset CTC
	 * 
	 * @param ctcDetail
	 */
	public void ctcMethod(CTCDetail ctcDetail) {
		try {
			double annualAmt = 0;
			// pfMethod(empCredit, empCredit.getEmpId());
			Object obj[] = new Object[1];
			obj[0] = ctcDetail.getEmpId();
			Object[][] creditAmtObj = getSqlModel().getSingleResult(
					getQuery(7), obj);
			annualAmt = Double.parseDouble(formatter.format(Double
					.parseDouble(String.valueOf(creditAmtObj[0][0]))));
			ctcDetail.setCtc(formatter.format(annualAmt));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method update Salary grade
	 * 
	 * @param bean
	 */
	public void updateSalGrade(CTCDetail bean) {
		Object[][] updateGrade = new Object[1][2];
		updateGrade[0][0] = bean.getSalGradeId();
		updateGrade[0][1] = bean.getEmpId();
		getSqlModel().singleExecute(getQuery(11), updateGrade);
	}

	/**
	 * Method to update Formula
	 * 
	 * @param bean
	 */
	public void updateFormula(CTCDetail bean) {
		try {
			Object[][] updateObj = new Object[1][7];
			updateObj[0][0] = checkNull(bean.getFormulaId()); // formula id
			updateObj[0][1] = checkNull(bean.getGrsAmt()); // gross amount
			updateObj[0][2] = checkNull(bean.getTotalAmt()); // total monthly
																// salary
			updateObj[0][3] = bean.getAnnualAmt(); // total annual salary
			updateObj[0][4] = bean.getAnnualAmtPer(); // total annual
														// perquisite
			ctcMethod(bean);
			updateObj[0][5] = bean.getCtc(); // ctc
			updateObj[0][6] = bean.getEmpId();// emp id
			getSqlModel().singleExecute(getQuery(12), updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to add Credits
	 * 
	 * @param tableData
	 * @param ctcDetail
	 * @param request
	 * @return String
	 */
	public String addCreditData(Object[] tableData, CTCDetail ctcDetail,
			HttpServletRequest request) {

		boolean result;
		String str = null;
		String qury = " SELECT HRMS_EMP_CREDIT.CREDIT_AMT FROM HRMS_EMP_CREDIT WHERE HRMS_EMP_CREDIT.CREDIT_CODE="
				+ tableData[0]
				+ "  AND "
				+ "  HRMS_EMP_CREDIT.EMP_ID ="
				+ tableData[3];
		Object amt[][] = getSqlModel().getSingleResult(qury);
		logger.info("FIRING QUERY");
		if (amt.length == 0) {// Start if amount length 0
			Object[][] bean = new Object[1][4];
			bean[0][0] = tableData[0];
			bean[0][1] = tableData[1];
			bean[0][2] = tableData[2];
			bean[0][3] = tableData[3];
			result = getSqlModel().singleExecute(getQuery(9), bean);
			if (result) {// if result is true
				trial = new AuditTrail(ctcDetail.getUserEmpId());
				/** AUDIT TRIAL INITIALIZE * */
				trial.initiate(context, session);

				trial.setParameters("HRMS_EMP_CREDIT", new String[] {
						"CREDIT_CODE", "EMP_ID" }, new String[] {
						String.valueOf(tableData[0]), ctcDetail.getEmpId() },
						ctcDetail.getEmpId());

				trial.setComments("CREDIT_CODE   :"
						+ String.valueOf(tableData[0]));

				/** AUDIT TRAIL EXECUTION * */
				trial.executeADDTrail(request);
			}// end if result is true
			str = "Record saved Successfully!";
		}// end if amount length 0
		else {// start else
			logger.info("===USER LOGIN CODE====" + ctcDetail.getUserEmpId());
			trial = new AuditTrail(ctcDetail.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE * */
			trial.initiate(context, session);
			trial.setParameters("HRMS_EMP_CREDIT", new String[] {
					"CREDIT_CODE", "EMP_ID" }, new String[] {
					String.valueOf(tableData[0]), ctcDetail.getEmpId() },
					ctcDetail.getEmpId());
			trial.beginTrail();
			trial.setComments("CREDIT_CODE   :" + String.valueOf(tableData[0]));
			//update HRMS_EMP_CREDIT
			String upd = "UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT="
					+ tableData[2] + ", CREDIT_APPLICABLE='" + tableData[1]
					+ "' WHERE CREDIT_CODE=" + tableData[0] + " AND EMP_ID="
					+ tableData[3];
			logger.info(upd);
			result = getSqlModel().singleExecute(upd);
			/** AUDIT TRAIL EXECUTION * */
			trial.executeMODTrail(request);
			str = "Record updated Successfully!";
		}// end else
		return str;

	}

	/**
	 * METHOD TO GET IMAGE OF EMPLOYEE
	 * 
	 * @param ctcDetail
	 */
	public void getImage(CTCDetail ctcDetail) {

		try {
			//Query to  Get Profile Name
			String query = "select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="
					+ ctcDetail.getEmpId();

			Object[][] myPics = getSqlModel().getSingleResult(query);
			if (myPics != null && myPics.length > 0) {// if data
				ctcDetail.setUploadFileName(String.valueOf(myPics[0][0]));
				ctcDetail.setProfileEmpName(String.valueOf(myPics[0][1]) + " "
						+ String.valueOf(myPics[0][2]) + " "
						+ String.valueOf(myPics[0][3]));
			}// end if data

		}// end of try
		catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to set employee details according to promotion code
	 * 
	 * @param ctcDetail
	 * @param promotionCode
	 */
	public void fetchEmployeeDetailsByPromotionCode(CTCDetail ctcDetail,
			String promotionCode) {
		try {
			String empDetailQuery = "SELECT   HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS NAME,"
					+ " HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,"
					+ " NVL(SALGRADE_TYPE,' ') ,SALGRADE_CODE, HRMS_EMP_OFFC.EMP_ID,"
					+ " HRMS_SALARY_MISC.GROSS_AMT,DECODE(HRMS_SALARY_MISC.SAL_EPF_FLAG,'Y','true','N','false'), NVL(SAL_ACCNO_REGULAR,' '), "
					+ " NVL(SAL_PANNO,' '), NVL(DEPT_NAME,' '), DEPT_ID , TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'), NVL(CADRE_NAME,' '), HRMS_PROMOTION.PRO_GRADE, NVL(CTC,0)"
					+ " FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.EMP_CODE = HRMS_EMP_OFFC.EMP_ID AND HRMS_PROMOTION.PROM_CODE ="
					+ promotionCode
					+ ")"
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_PROMOTION.PRO_DESG)"
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_PROMOTION.PRO_BRANCH)"
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)"
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_PROMOTION.PRO_DEPT )"
					+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_PROMOTION.PRO_GRADE)"
					+ " LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_SALGRADE_HDR.SALGRADE_CODE = HRMS_PROMOTION.PROM_SALGRADE)"
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " LEFT JOIN HRMS_FORMULABUILDER_HDR ON(HRMS_FORMULABUILDER_HDR.FORMULA_ID = HRMS_SALARY_MISC.FORMULA_ID)";
			Object empDetailObj[][] = getSqlModel().getSingleResult(
					empDetailQuery);

			if (empDetailObj != null && empDetailObj.length > 0) {// if data

				ctcDetail.setEmpToken(String.valueOf(empDetailObj[0][0]));
				ctcDetail.setEmpName(String.valueOf(empDetailObj[0][1]));
				ctcDetail.setEmpCenter(String.valueOf(empDetailObj[0][2]));
				ctcDetail.setEmpRank(String.valueOf(empDetailObj[0][3]));
				ctcDetail.setSalGradeName(String.valueOf(empDetailObj[0][4]));
				ctcDetail.setSalGradeId(String.valueOf(empDetailObj[0][5]));
				ctcDetail.setEmpId(String.valueOf(empDetailObj[0][6]));
				ctcDetail.setGrsAmt(String.valueOf(empDetailObj[0][7]));
				ctcDetail.setPfFlag(String.valueOf(empDetailObj[0][8]));
				ctcDetail.setEmpAccNo(String.valueOf(empDetailObj[0][9]));
				ctcDetail.setEmpPANNo(String.valueOf(empDetailObj[0][10]));
				ctcDetail.setEmpDeptName(String.valueOf(empDetailObj[0][11]));
				ctcDetail.setEmpDeptId(String.valueOf(empDetailObj[0][12]));
				ctcDetail.setEmpJoinDate(String.valueOf(empDetailObj[0][13]));
				ctcDetail.setEmpGradeName(String.valueOf(empDetailObj[0][14]));
				ctcDetail.setEmpGradeId(String.valueOf(empDetailObj[0][15]));
				ctcDetail.setCtc(formatter.format(String.valueOf(empDetailObj[0][16])));

			}// end if data
		} catch (Exception e) {
			e.printStackTrace();
		}
		getIncrementPeriod(ctcDetail);
	}

	/** Method to check null values
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {// if null
			return "";
		}// end if null
		else {// start else
			return result;
		}// end else
	}
}// end of class
