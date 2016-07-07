package org.paradyne.model.extraWorkBenefits;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.extraWorkBenefits.ExtraWorkBenifitCalculation;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;

public class ExtraWorkBenifitCalculationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ExtraWorkBenifitCalculationModel.class);

	public boolean processData(ExtraWorkBenifitCalculation extraWorkBenifitCal,
			String path, HttpServletRequest request) {
		boolean result = false;
		try {

			// CHECK FOR EXTRA WORK BENIFIT APPLICATION
			String extraWorkAppDateQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,  "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS emp_name,TO_CHAR(EXTRAWORK_DATE,'DD-MM-YYYY'),HRMS_EXTRAWORK_APPL_DTL.EMP_ID,EXTRAWORK_DAY,EXTRAWORK_DAY_TYPE,EXTRAWORK_STARTTIME,EXTRAWORK_ENDTIME "
					+ "   FROM HRMS_EXTRAWORK_APPL_DTL "
					+ " INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EXTRAWORK_APPL_DTL.EMP_ID)  "
					+ " WHERE  EMP_DIV ="
					+ extraWorkBenifitCal.getDivisionCode()
					+ " AND EXTRAWORK_APPL_STATUS='A'  AND TO_CHAR(EXTRAWORK_DATE,'MM')="
					+ extraWorkBenifitCal.getMonth()
					+ " AND TO_CHAR(EXTRAWORK_DATE,'YYYY')="
					+ extraWorkBenifitCal.getYear();

			System.out.println("extraWorkAppDateQuery  "
					+ extraWorkAppDateQuery);
			Object[][] extraWorkDateObj = getSqlModel().getSingleResult(
					extraWorkAppDateQuery);

			if (extraWorkDateObj != null && extraWorkDateObj.length > 0) {
				Object[][] credit_obj = null;
				credit_obj = getCreditHeader(path);
				Vector isAvailable = new Vector();
				Vector creditAmt = new Vector();

				for (int j = 0; j < extraWorkDateObj.length; j++) {

					//getBenifit(empid,benefit for)
					Object[][] tempObj = getBenifit(String
							.valueOf(extraWorkDateObj[j][3]), String
							.valueOf(extraWorkDateObj[j][4]), String
							.valueOf(extraWorkDateObj[j][5]));

					if (tempObj != null && tempObj.length > 0) {
						for (int i = 0; i < tempObj.length; i++) {

							//Benefit Type(EP,EL,FB,VB)
							if (String.valueOf(tempObj[i][1]).equals("EP")) {
								Object cred_amt[][] = new Object[1][credit_obj.length];
								isAvailable.add(extraWorkDateObj[j][0]);//employee token
								isAvailable.add(extraWorkDateObj[j][1]);//employee name
								isAvailable.add(extraWorkDateObj[j][2]);// extra work date
								isAvailable.add(tempObj[i][0]);//EXTRAWORK_BENEFIT_FOR
								isAvailable.add("Extra-Day Pay");//EXTRAWORK_BENEFIT_TYPE
								isAvailable.add(extraWorkDateObj[j][3]);//employee ID
								isAvailable.add(tempObj[i][8]);//CREDITED TO
								/**
								 * Calculation salary 1 day salary amount for all credit
								 * heads
								 */

								isAvailable.add(extraWorkDateObj[j][6]);//CREDITED TO

								isAvailable.add(extraWorkDateObj[j][7]);//CREDITED TO

								Calendar cal = Calendar.getInstance();
								cal.setTime(Utility
										.getDate(01
												+ "-"
												+ String.valueOf(
														extraWorkDateObj[j][2])
														.substring(3, 5)
												+ "-"
												+ String.valueOf(
														extraWorkDateObj[j][2])
														.substring(6, 10)));

								int daysOfMonth = cal
										.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

								String creditCOde = "0";
								if (credit_obj != null && credit_obj.length > 0) {
									for (int m = 0; m < credit_obj.length; m++) {
										creditCOde += ","
												+ String
														.valueOf(credit_obj[m][0]);
									}
								}
								Object[][] credit_amount = null;
								/*String selectCredits = "     SELECT  NVL(CREDIT_AMT,0) "
										+ " FROM HRMS_EMP_CREDIT WHERE  EMP_ID="
										+ String.valueOf(extraWorkDateObj[j][3])
										+ " AND CREDIT_CODE IN("
										+ creditCOde
										+ ")ORDER BY CREDIT_CODE ";*/

								String selectCredits = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0)  FROM HRMS_CREDIT_HEAD LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND  EMP_ID='"
										+ String
												.valueOf(extraWorkDateObj[j][3])
										+ "'  ) where HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' order BY HRMS_CREDIT_HEAD.CREDIT_CODE";
								credit_amount = getSqlModel().getSingleResult(
										selectCredits, new Object[0][0]);

								credit_amount = getSqlModel().getSingleResult(
										selectCredits, new Object[0][0]);
								// for loop for credit_header
								double total_cred_amt = 0.0d;
								for (int k = 0; k < credit_obj.length; k++) {

									try {
										if (String.valueOf(extraWorkDateObj[j][5]).equals("F"))
											total_cred_amt += Utility
													.twoDecimal(
															Double
																	.parseDouble(String
																			.valueOf(credit_amount[k][1]))
																	/ daysOfMonth,
															2);
										else
											total_cred_amt += Utility
													.twoDecimal(
															(Double
																	.parseDouble(String
																			.valueOf(credit_amount[k][1])) / daysOfMonth) / 2,
															2);

									} catch (Exception e) {

									}

								}
								for (int k = 0; k < credit_obj.length; k++) {
									cred_amt[0][k] = "0";
									if (String
											.valueOf(credit_obj[k][0])
											.equals(
													String
															.valueOf(tempObj[i][8])))// credit
									{
										cred_amt[0][k] = Math
												.round(total_cred_amt);
									}

								}
								creditAmt.add(cred_amt);

							}
							//Benefit Type(EP,EL,FB,VB)
							if (String.valueOf(tempObj[i][1]).equals("FB")) {

								/**
								 * Calculation FD==Application F/H/O against credit code
								 * assign amount(All credit code will be zero) HOUR
								 * CALCULATION 2:30 setting Partial Day /hrs
								 */
								Object cred_amt[][] = new Object[1][credit_obj.length];
								isAvailable.add(extraWorkDateObj[j][0]);//employee token
								isAvailable.add(extraWorkDateObj[j][1]);//employee name
								isAvailable.add(extraWorkDateObj[j][2]);// extra work date
								isAvailable.add(tempObj[i][0]);//EXTRAWORK_BENEFIT_FOR
								isAvailable.add("Fixed Benefits");//EXTRAWORK_BENEFIT_TYPE
								isAvailable.add(extraWorkDateObj[j][3]);//employee ID
								isAvailable.add(tempObj[i][8]);//CREDITED TO

								isAvailable.add(extraWorkDateObj[j][6]);//CREDITED TO

								isAvailable.add(extraWorkDateObj[j][7]);//CREDITED TO

								// for loop for credit_header
								for (int k = 0; k < credit_obj.length; k++) {

									logger.info("credit_obj  in fb    "
											+ credit_obj[k][0]);
									logger.info("tempObj  in fb      "
											+ tempObj[i][8]);

									cred_amt[0][k] = "0";

									//checking credit codes creditheadcode=temp credit code
									if (String
											.valueOf(credit_obj[k][0])
											.equals(
													String
															.valueOf(tempObj[i][8])))// credit
									// code
									// check
									{
										//checking employee working full day
										if (String.valueOf(
												extraWorkDateObj[j][5]).equals(
												"F")) {

											cred_amt[0][k] = Math
													.round(Double
															.parseDouble(String
																	.valueOf(tempObj[i][4])));// full day
											// amt
											logger.info("full day amt        "
													+ cred_amt[0][k]);
										}
										//checking employee working half day
										else if (String.valueOf(
												extraWorkDateObj[j][5]).equals(
												"H")) {

											cred_amt[0][k] = Math
													.round(Double
															.parseDouble(String
																	.valueOf(tempObj[i][5])));// half day
											// amt
											logger.info("half day amt        "
													+ cred_amt[0][k]);
										}
										//checking employee working partial
										else {

											cred_amt[0][k] = Math
													.round(Double
															.parseDouble(String
																	.valueOf(tempObj[i][6])));//variable amt
											logger
													.info("hourly day amt        "
															+ cred_amt[0][k]); // hour
										}

									}

								}
								creditAmt.add(cred_amt);

							}// end of

							//checking for variable benefits
							if (String.valueOf(tempObj[i][1]).equals("VB")) {

								/**
								 * Calculation : formula calling get amount pass empid
								 * and get the amount against credit code assign
								 * amount(All credit code will be zero)
								 */

								//System.out.println("String.valueOf(extraWorkDateObj[j][6]  "+String.valueOf(extraWorkDateObj[j][6]));
								//System.out.println("String.valueOf(extraWorkDateObj[j][7]  "+String.valueOf(extraWorkDateObj[j][7]));
								//System.out.println("String.valueOf(tempObj[i][10])           "+String.valueOf(tempObj[i][10]));
								//System.out.println("String.valueOf(extraWorkDateObj[j][5])                 "+String.valueOf(extraWorkDateObj[j][5]));

								//System.out.println("String.valueOf(extraWorkDateObj[j][2])                 "+String.valueOf(extraWorkDateObj[j][2]));
								long actualWorkingHrsinmin = calculateWorkingHrs(
										String.valueOf(extraWorkDateObj[j][6]),
										String.valueOf(extraWorkDateObj[j][7]),
										String.valueOf(tempObj[i][10]),
										String.valueOf(extraWorkDateObj[j][5]),
										String.valueOf(extraWorkDateObj[j][2]));

								//	System.out.println("value of actualWorkingHrsinmin:::::::::"+actualWorkingHrsinmin);

								double actualWorkingHrs = Double
										.parseDouble(String
												.valueOf(actualWorkingHrsinmin)) / 60;

								//	System.out.println("value of actualWorkingHrs::::$$$$$$$$$$$$$$$$$$:::::"+actualWorkingHrs);

								Object cred_amt[][] = new Object[1][credit_obj.length];
								isAvailable.add(extraWorkDateObj[j][0]);//employee token
								isAvailable.add(extraWorkDateObj[j][1]);//employee name
								isAvailable.add(extraWorkDateObj[j][2]);// extra work date
								isAvailable.add(tempObj[i][0]);//EXTRAWORK_BENEFIT_FOR
								isAvailable.add("Variable Benefits");//EXTRAWORK_BENEFIT_TYPE
								isAvailable.add(extraWorkDateObj[j][3]);//employee ID
								isAvailable.add(tempObj[i][8]);//CREDITED TO

								isAvailable.add(extraWorkDateObj[j][6]);//CREDITED TO

								isAvailable.add(extraWorkDateObj[j][7]);//CREDITED TO

								// for loop for credit_header
								for (int k = 0; k < credit_obj.length; k++) {

									cred_amt[0][k] = "0";
									//checking for credit code
									if (String
											.valueOf(credit_obj[k][0])
											.equals(
													String
															.valueOf(tempObj[i][8])))// credit
									// code
									// check
									{
										double amount = 0;
										/*try {
											if (String.valueOf(extraWorkDateObj[j][5]).equals("F")||String.valueOf(extraWorkDateObj[j][5]).equals("O"))
											amount = Utility.expressionEvaluate(new Utility().generateFormula(String.valueOf(extraWorkDateObj[j][3]),String.valueOf(tempObj[i][7]),context,session)); 
											else
											{	amount = Utility.expressionEvaluate(new Utility().generateFormula(String.valueOf(extraWorkDateObj[j][3]),String.valueOf(tempObj[i][7]),context,session));		
											//passing empid and formula to calculate amount
											amount=amount/2; 
											}
											cred_amt[0][k] = Math.round(amount);
											logger.info("formula amount       "+ cred_amt[0][k]);
										}// end of try
										catch (Exception e) {
											// TODO Auto-generated catch block
											logger.error("Exception in  first---------------"+ e);
											e.printStackTrace();
										}// end of catch
										 */
										amount = Utility
												.expressionEvaluate(new Utility()
														.generateFormula(
																String
																		.valueOf(extraWorkDateObj[j][3]),
																String
																		.valueOf(tempObj[i][7]),
																context,
																session));
										//passing empid and formula to calculate amount
										amount = amount * actualWorkingHrs;

										cred_amt[0][k] = Math.round(amount);
										logger.info("formula amount       "
												+ cred_amt[0][k]);
									}

								}

								creditAmt.add(cred_amt);

							}

						}
					}

				}

				Object[][] totalObj = new Object[creditAmt.size()][credit_obj.length + 1];

				int iter_countr = 0;

				if (creditAmt != null && creditAmt.size() > 0) {
					for (int i = 0; i < creditAmt.size(); i++) {
						Object[][] crd = (Object[][]) creditAmt.get(i);
						double totAmt = 0.0d;
						for (int j = 0; j < crd[0].length; j++) {
							totalObj[iter_countr][j] = crd[0][j];
							totAmt += Utility.twoDecimal(Double
									.parseDouble(String.valueOf(crd[0][j])), 2);

						}

						totalObj[iter_countr][credit_obj.length] = Math
								.round(totAmt);
						iter_countr++;

					}
					request.setAttribute("credit_amt", totalObj);
				}

				ArrayList<ExtraWorkBenifitCalculation> List = new ArrayList<ExtraWorkBenifitCalculation>();

				int counter = 0;
				for (int i = 0; i < isAvailable.size() / 9; i++) {
					result = true;
					ExtraWorkBenifitCalculation bean = new ExtraWorkBenifitCalculation();
					bean.setEmpToken(checkNull(String.valueOf(isAvailable
							.get(counter++))));
					bean.setEmpName(checkNull(String.valueOf(isAvailable
							.get(counter++))));
					bean.setExtraWorkDate(checkNull(String.valueOf(isAvailable
							.get(counter++))));
					bean.setBenifitFor(checkNull(String.valueOf(isAvailable
							.get(counter++))));
					bean.setBenifitType(checkNull(String.valueOf(isAvailable
							.get(counter++))));
					bean.setEmployeeId(checkNull(String.valueOf(isAvailable
							.get(counter++))));
					bean.setCreditedTo(checkNull(String.valueOf(isAvailable
							.get(counter++))));
					bean.setExtraworkFromTime(checkNull(String
							.valueOf(isAvailable.get(counter++))));
					bean.setExtraworkToTime(checkNull(String
							.valueOf(isAvailable.get(counter++))));
					List.add(bean);
				}
				extraWorkBenifitCal.setList(List);

				ArrayList<ExtraWorkBenifitCalculation> creditList = new ArrayList<ExtraWorkBenifitCalculation>();

				if (credit_obj != null && credit_obj.length > 0) {
					for (int i = 0; i < credit_obj.length; i++) {

						ExtraWorkBenifitCalculation bean = new ExtraWorkBenifitCalculation();
						bean
								.setCreditHeadCode(String
										.valueOf(credit_obj[i][0]));
						bean.setCreditHead(checkNull(String
								.valueOf(credit_obj[i][1])));
						creditList.add(bean);
					}
					extraWorkBenifitCal.setCreditList(creditList);
				}
			}

			String delTempDataQuery = " DELETE FROM HRMS_EXTRAWORK_BENEFIT_TEMP  ";
			getSqlModel().singleExecute(delTempDataQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getDayOfWeek(String date) {
		Calendar c = Calendar.getInstance();
		c.setTime(Utility.getDate(date));
		int day = c.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case 1:
			return "SUN";
		case 2:
			return "MON";
		case 3:
			return "TUE";
		case 4:
			return "WED";
		case 5:
			return "THU";
		case 6:
			return "FRI";
		case 7:
			return "SAT";

		}
		return "";
	}

	/**
	 * GET CREDIT CODE AND CREDIT ABBR
	 * 
	 * @param String
	 *            'path', where Credit Head XML file has been stored
	 * @return CREDITS Object 'credit_header'
	 */
	public Object[][] getCreditHeader(String path) {
		Object credit_header[][] = null;
		try {
			credit_header = null;
			Document document = null;
			try {
				path = path + "/creditHead.xml";
				/**
				 * the below statements are user for reading the data from XML file
				 * using DOM parser
				 */
				document = new XMLReaderWriter().parse(new File(path));
				List fonts = document
						.selectNodes("//PAYROLL/CREDIT[@name='CREDIT HEAD']/CREDIT");
				credit_header = new String[fonts.size()][5];
				int count = 0;
				// this loop is used to set values from List 'fonts' to Object
				// 'credit_header'
				for (Iterator iter = fonts.iterator(); iter.hasNext();) {
					Element font = (Element) iter.next();
					credit_header[count][0] = font.attributeValue("Code")
							.trim();
					credit_header[count][1] = font.attributeValue(
							"Abbreviation").trim();
					count++;

				} // end of for loop
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} catch (Exception e) {
			logger.error("Exception in getCreditHeader----------" + e);
		}
		return credit_header;
	} // end of method getCreditHeader()

	public boolean saveExtraBenifitRecord(
			ExtraWorkBenifitCalculation extraWorkBenifitCal, String[] empCode,
			String[] extraWorkDate, String[] extraWorkBenifitFor,
			String[] extraWorkBenifitType, Object[][] rows,
			String[] creditHeadCode, String[] totAmount, String[] creditedTo,
			String[] extraworkFromTime, String[] extraworkToTime) {

		boolean result = false;
		try {

			String inserQuery = " INSERT INTO HRMS_EXTRAWORK_PROCESS_HDR(EXTRAWORK_PROCESS_CODE, EXTRAWORK_PROCESS_MONTH, EXTRAWORK_PROCESS_YEAR, EXTRAWORK_PROCESS_DIVISION, EXTRAWORK_INCLUDE_SAL_FLAG, EXTRAWORK_INCLUDE_SAL_MONTH, EXTRAWORK_INCLUDE_SAL_YEAR) "
					+ " VALUES((SELECT NVL(MAX(EXTRAWORK_PROCESS_CODE),0)+1 FROM HRMS_EXTRAWORK_PROCESS_HDR),?,?,?,?,?,?)";
			Object insertObj[][] = new Object[1][6];
			insertObj[0][0] = extraWorkBenifitCal.getMonth();
			insertObj[0][1] = extraWorkBenifitCal.getYear();
			insertObj[0][2] = extraWorkBenifitCal.getDivisionCode();
			if (extraWorkBenifitCal.getSalaryCheck().equals("true")) {
				insertObj[0][3] = "Y";
			} else {
				insertObj[0][3] = "N";
			}
			insertObj[0][4] = extraWorkBenifitCal.getSalarymonth();
			insertObj[0][5] = extraWorkBenifitCal.getSalaryyear();
			result = getSqlModel().singleExecute(inserQuery, insertObj);
			String selectQuery = " SELECT NVL(MAX(EXTRAWORK_PROCESS_CODE),0) FROM HRMS_EXTRAWORK_PROCESS_HDR ";
			Object processcodeObj[][] = getSqlModel().getSingleResult(
					selectQuery);

			String selectDtlIdQuery = " SELECT NVL(MAX(EXTRAWORK_DTL_CODE),0)+1 FROM HRMS_EXTRAWORK_PROCESS_DTL";

			Object dtlIdQueryObj[][] = getSqlModel().getSingleResult(
					selectDtlIdQuery);
			int dtlCode = Integer.parseInt(String.valueOf(dtlIdQueryObj[0][0]));

			extraWorkBenifitCal.setProcessCode(String
					.valueOf(processcodeObj[0][0]));
			Object[][] saveDtlRecordObj = new Object[empCode.length][10];

			Object[][] saveCreditRecordObj = new Object[empCode.length
					* creditHeadCode.length][7];
			int creditCount = 0;
			if (result) {
				String insertDtlQuery = " INSERT INTO HRMS_EXTRAWORK_PROCESS_DTL(EXTRAWORK_PROCESS_CODE, EXTRAWORK_DTL_CODE, EMP_ID, EXTRAWORK_BENEFIT_FOR, EXTRAWORK_BENEFIT_TYPE, EXTRAWORK_BENEFIT_DATE, EXTRAWORK_BENEFIT_TOTAL_AMT,EXTRAWORK_BENEFIT_CREDITED_TO,EXTRAWORK_BENEFIT_STARTTIME, EXTRAWORK_BENEFIT_ENDTIME) "
						+ " VALUES(?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?) ";

				String creditAmtQuery = " INSERT INTO HRMS_EXTRAWORK_PROCESS_CREDITS(EXTRAWORK_PROCESS_CODE, EXTRAWORK_DTL_CODE, CREDIT_CODE, CREDIT_AMOUNT, EMP_ID, EXTRAWORK_PROCESS_MONTH, EXTRAWORK_PROCESS_YEAR) "
						+ " VALUES(?,?,?,?,?,?,?)";

				if (empCode != null && empCode.length > 0) {
					for (int i = 0; i < empCode.length; i++) {

						saveDtlRecordObj[i][0] = processcodeObj[0][0];// process
						// code
						saveDtlRecordObj[i][1] = dtlCode;//  
						saveDtlRecordObj[i][2] = empCode[i];// employee code
						saveDtlRecordObj[i][3] = extraWorkBenifitFor[i];// benifit
						// for

						if (extraWorkBenifitType[i].equals("Extra-Day Pay")) {
							saveDtlRecordObj[i][4] = "EP";
						}
						if (extraWorkBenifitType[i].equals("Extra-Day Leave")) {
							saveDtlRecordObj[i][4] = "EL";
						}
						if (extraWorkBenifitType[i].equals("Fixed Benefits")) {
							saveDtlRecordObj[i][4] = "FB";
						}
						if (extraWorkBenifitType[i].equals("Variable Benefits")) {
							saveDtlRecordObj[i][4] = "VB";
						}

						saveDtlRecordObj[i][5] = extraWorkDate[i];// extra working
						// date
						saveDtlRecordObj[i][6] = totAmount[i];
						saveDtlRecordObj[i][7] = creditedTo[i];
						saveDtlRecordObj[i][8] = extraworkFromTime[i];
						saveDtlRecordObj[i][9] = extraworkToTime[i];

						System.out.println("insertDtlQuery     "
								+ insertDtlQuery);
						//String dtlCode1 = " SELECT NVL(MAX(EXTRAWORK_DTL_CODE),0) FROM HRMS_EXTRAWORK_PROCESS_DTL ";
						//Object dtlCodeObj[][] = getSqlModel().getSingleResult(dtlCode1);
						/*Object[][] saveCreditRecordObj = new Object[empCode.length
						 * creditHeadCode.length][7];*/
						for (int j = 0; j < creditHeadCode.length; j++) { // LOOP FOR INDIVIDUAL EMPLOYEE CREDIT AND DEBIT
							saveCreditRecordObj[creditCount][0] = processcodeObj[0][0]; //  
							saveCreditRecordObj[creditCount][1] = dtlCode; //  
							saveCreditRecordObj[creditCount][2] = creditHeadCode[j];
							saveCreditRecordObj[creditCount][3] = rows[i][j];
							saveCreditRecordObj[creditCount][4] = empCode[i];
							saveCreditRecordObj[creditCount][5] = extraWorkBenifitCal
									.getMonth();
							saveCreditRecordObj[creditCount][6] = extraWorkBenifitCal
									.getYear();
							// COUNTER FOR CREDIT DATA OBJECT REQUIRED FOR BATCH UPDATE
							creditCount++;
						}

						dtlCode++;

					}
					result = getSqlModel().singleExecute(insertDtlQuery,
							saveDtlRecordObj);
					result = getSqlModel().singleExecute(creditAmtQuery,
							saveCreditRecordObj);

				}
			}

		} catch (Exception e) {
			logger.error("Exception in saveExtraBenifitRecord----------" + e);
		}
		return result;
	}

	public void setProcessedRecord(
			ExtraWorkBenifitCalculation extraWorkBenifitCal,
			HttpServletRequest request) {

		try {
			String query = " SELECT HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS emp_name "
					+ " ,TO_CHAR(EXTRAWORK_BENEFIT_DATE,'DD-MM-YYYY'),EXTRAWORK_BENEFIT_FOR,DECODE(EXTRAWORK_BENEFIT_TYPE,'EP','Extra-Day Pay','EL','Extra-Day Leave','FB','Fixed Benefits','VB','Variable Benefits'),NVL(EXTRAWORK_BENEFIT_TOTAL_AMT,0),EXTRAWORK_BENEFIT_CREDITED_TO,EXTRAWORK_BENEFIT_STARTTIME, EXTRAWORK_BENEFIT_ENDTIME "
					+ " FROM HRMS_EXTRAWORK_PROCESS_DTL "
					+ " INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID)"
					+ "  WHERE EXTRAWORK_PROCESS_CODE="
					+ extraWorkBenifitCal.getProcessCode()
					+ "  ORDER BY EXTRAWORK_DTL_CODE ";
			Object data[][] = getSqlModel().getSingleResult(query);
			ArrayList<ExtraWorkBenifitCalculation> List = new ArrayList<ExtraWorkBenifitCalculation>();
			String creditHeadQuery = "  SELECT DISTINCT HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_CODE,CREDIT_ABBR FROM HRMS_EXTRAWORK_PROCESS_CREDITS "
					+ "  INNER JOIN  hrms_CREDIT_HEAD ON(hrms_CREDIT_HEAD.CREDIT_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_CODE)"
					+ "  WHERE EXTRAWORK_PROCESS_CODE="
					+ extraWorkBenifitCal.getProcessCode()
					+ " ORDER BY HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_CODE ";

			Object creditHeadQueryObj[][] = getSqlModel().getSingleResult(
					creditHeadQuery);
			Object finalObj[][] = new Object[data.length][creditHeadQueryObj.length + 1];
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					ExtraWorkBenifitCalculation bean = new ExtraWorkBenifitCalculation();
					bean.setEmployeeId(checkNull(String.valueOf(data[i][0])));
					bean.setEmpToken(checkNull(String.valueOf(data[i][1])));
					bean.setEmpName(checkNull(String.valueOf(data[i][2])));
					bean
							.setExtraWorkDate(checkNull(String
									.valueOf(data[i][3])));
					bean.setBenifitFor(checkNull(String.valueOf(data[i][4])));
					bean.setBenifitType(checkNull(String.valueOf(data[i][5])));
					bean.setCreditedTo(checkNull(String.valueOf(data[i][7])));
					bean.setExtraworkFromTime(checkNull(String
							.valueOf(data[i][8])));
					bean.setExtraworkToTime(checkNull(String
							.valueOf(data[i][9])));
					List.add(bean);
					finalObj[i][creditHeadQueryObj.length] = data[i][6];
				}
				extraWorkBenifitCal.setList(List);
			}
			if (creditHeadQueryObj != null && creditHeadQueryObj.length > 0) {
				ArrayList<ExtraWorkBenifitCalculation> creditList = new ArrayList<ExtraWorkBenifitCalculation>();

				for (int i = 0; i < creditHeadQueryObj.length; i++) {

					ExtraWorkBenifitCalculation bean = new ExtraWorkBenifitCalculation();
					bean.setCreditHeadCode(String
							.valueOf(creditHeadQueryObj[i][0]));
					bean
							.setCreditHead(String
									.valueOf(creditHeadQueryObj[i][1]));
					creditList.add(bean);
				}
				extraWorkBenifitCal.setCreditList(creditList);
			}
			String crediAmtQuery = " SELECT CREDIT_AMOUNT FROM HRMS_EXTRAWORK_PROCESS_CREDITS  WHERE EXTRAWORK_PROCESS_CODE="
					+ extraWorkBenifitCal.getProcessCode()
					+ " ORDER BY  EXTRAWORK_DTL_CODE ,HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_CODE ";
			Object crediAmtQueryObj[][] = getSqlModel().getSingleResult(
					crediAmtQuery);
			if (crediAmtQueryObj != null && crediAmtQueryObj.length > 0) {

				int cnt = 0;
				int k = 0;
				for (int i = 0; i < crediAmtQueryObj.length
						/ creditHeadQueryObj.length; i++) {
					for (int j = 0; j < creditHeadQueryObj.length; j++) {
						finalObj[cnt][j] = crediAmtQueryObj[k++][0];
					}
					cnt++;
				}
				request.setAttribute("credit_amt", finalObj);
			}
		} catch (Exception e) {
			logger.error("Exception in setProcessedRecord----------" + e);
			e.printStackTrace();
		}

	}

	public boolean updateExtraBenifitRecord(
			ExtraWorkBenifitCalculation extraWorkBenifitCal, String[] empCode,
			String[] extraWorkDate, String[] extraWorkBenifitFor,
			String[] extraWorkBenifitType, Object[][] rows,
			String[] creditHeadCode, String[] totAmount,
			String[] extraworkFromTime, String[] extraworkToTime) {

		boolean result = false;

		try {

			String updateQue = " UPDATE HRMS_EXTRAWORK_PROCESS_HDR "
					+ "  SET  EXTRAWORK_PROCESS_MONTH=?, EXTRAWORK_PROCESS_YEAR=?, EXTRAWORK_PROCESS_DIVISION=?, EXTRAWORK_INCLUDE_SAL_FLAG=?, EXTRAWORK_INCLUDE_SAL_MONTH=?, EXTRAWORK_INCLUDE_SAL_YEAR=? "
					+ "  WHERE EXTRAWORK_PROCESS_CODE=? ";
			Object updateObj[][] = new Object[1][7];
			updateObj[0][0] = extraWorkBenifitCal.getMonth();
			updateObj[0][1] = extraWorkBenifitCal.getYear();
			updateObj[0][2] = extraWorkBenifitCal.getDivisionCode();
			if (extraWorkBenifitCal.getSalaryCheck().equals("true")) {
				updateObj[0][3] = "Y";
			} else {
				updateObj[0][3] = "N";
			}
			updateObj[0][4] = extraWorkBenifitCal.getSalarymonth();
			updateObj[0][5] = extraWorkBenifitCal.getSalaryyear();
			updateObj[0][6] = extraWorkBenifitCal.getProcessCode();
			result = getSqlModel().singleExecute(updateQue, updateObj);

			String selectQuery = " SELECT NVL(MAX(EXTRAWORK_PROCESS_CODE),0) FROM HRMS_EXTRAWORK_PROCESS_HDR ";
			Object processcodeObj[][] = getSqlModel().getSingleResult(
					selectQuery);

			String selectDtlIdQuery = " SELECT NVL(MAX(EXTRAWORK_DTL_CODE),0)+1 FROM HRMS_EXTRAWORK_PROCESS_DTL";

			Object dtlIdQueryObj[][] = getSqlModel().getSingleResult(
					selectDtlIdQuery);
			int dtlCode = Integer.parseInt(String.valueOf(dtlIdQueryObj[0][0]));

			extraWorkBenifitCal.setProcessCode(String
					.valueOf(processcodeObj[0][0]));
			Object[][] saveDtlRecordObj = new Object[empCode.length][9];
			Object[][] saveCreditRecordObj = new Object[empCode.length
					* creditHeadCode.length][7];

			Object[][] updateDtlRecordObj = new Object[empCode.length][5];

			int creditCount = 0;
			
			String delCreditQue = " DELETE FROM HRMS_EXTRAWORK_PROCESS_CREDITS WHERE EXTRAWORK_PROCESS_CODE="
				+ extraWorkBenifitCal.getProcessCode();

			getSqlModel().singleExecute(delCreditQue);

			String delQuery = " DELETE FROM  HRMS_EXTRAWORK_PROCESS_DTL WHERE EXTRAWORK_PROCESS_CODE="
					+ extraWorkBenifitCal.getProcessCode();

			getSqlModel().singleExecute(delQuery);

			

			if (result) {

				String insertDtlQuery = " INSERT INTO HRMS_EXTRAWORK_PROCESS_DTL(EXTRAWORK_PROCESS_CODE, EXTRAWORK_DTL_CODE, EMP_ID, EXTRAWORK_BENEFIT_FOR, EXTRAWORK_BENEFIT_TYPE, EXTRAWORK_BENEFIT_DATE, EXTRAWORK_BENEFIT_TOTAL_AMT,EXTRAWORK_BENEFIT_STARTTIME, EXTRAWORK_BENEFIT_ENDTIME) "
						+ " VALUES(?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?) ";

				String creditAmtQuery = " INSERT INTO HRMS_EXTRAWORK_PROCESS_CREDITS(EXTRAWORK_PROCESS_CODE, EXTRAWORK_DTL_CODE, CREDIT_CODE, CREDIT_AMOUNT, EMP_ID, EXTRAWORK_PROCESS_MONTH, EXTRAWORK_PROCESS_YEAR) "
						+ " VALUES(?,?,?,?,?,?,?)";

				String updateAmtQuery = "UPDATE HRMS_EXTRAWORK_APPL_DTL  SET EXTRAWORK_AMOUNT =? "
						+ " WHERE EXTRAWORK_DATE=TO_DATE(?,'DD-MM-YYYY') AND EMP_ID=? "
						+ "  AND EXTRAWORK_STARTTIME=? AND EXTRAWORK_ENDTIME=? ";

				if (empCode != null && empCode.length > 0) {
					for (int i = 0; i < empCode.length; i++) {

						saveDtlRecordObj[i][0] = processcodeObj[0][0];// process
						// code
						saveDtlRecordObj[i][1] = dtlCode;//  
						saveDtlRecordObj[i][2] = empCode[i];// employee code
						saveDtlRecordObj[i][3] = extraWorkBenifitFor[i];// benifit
						// for

						if (extraWorkBenifitType[i].equals("Extra-Day Pay")) {
							saveDtlRecordObj[i][4] = "EP";
						}
						if (extraWorkBenifitType[i].equals("Extra-Day Leave")) {
							saveDtlRecordObj[i][4] = "EL";
						}
						if (extraWorkBenifitType[i].equals("Fixed Benefits")) {
							saveDtlRecordObj[i][4] = "FB";
						}
						if (extraWorkBenifitType[i].equals("Variable Benefits")) {
							saveDtlRecordObj[i][4] = "VB";
						}

						saveDtlRecordObj[i][5] = extraWorkDate[i];// extra working
						// date
						saveDtlRecordObj[i][6] = totAmount[i];// total amount

						saveDtlRecordObj[i][7] = extraworkFromTime[i];// total amount

						saveDtlRecordObj[i][8] = extraworkToTime[i];// total amount

						System.out.println("insertDtlQuery     "
								+ insertDtlQuery);

						updateDtlRecordObj[i][0] = totAmount[i];

						updateDtlRecordObj[i][1] = extraWorkDate[i];

						updateDtlRecordObj[i][2] = empCode[i];

						updateDtlRecordObj[i][3] = extraworkFromTime[i];

						updateDtlRecordObj[i][4] = extraworkToTime[i];

						//String dtlCode1 = " SELECT NVL(MAX(EXTRAWORK_DTL_CODE),0) FROM HRMS_EXTRAWORK_PROCESS_DTL ";
						//Object dtlCodeObj[][] = getSqlModel().getSingleResult(dtlCode1);
						/*Object[][] saveCreditRecordObj = new Object[empCode.length
						 * creditHeadCode.length][7];*/

						for (int j = 0; j < creditHeadCode.length; j++) { // LOOP FOR INDIVIDUAL EMPLOYEE CREDIT AND DEBIT
							saveCreditRecordObj[creditCount][0] = processcodeObj[0][0]; //  
							saveCreditRecordObj[creditCount][1] = dtlCode; //  
							saveCreditRecordObj[creditCount][2] = creditHeadCode[j];
							saveCreditRecordObj[creditCount][3] = rows[i][j];
							saveCreditRecordObj[creditCount][4] = empCode[i];
							saveCreditRecordObj[creditCount][5] = extraWorkBenifitCal
									.getMonth();
							saveCreditRecordObj[creditCount][6] = extraWorkBenifitCal
									.getYear();
							// COUNTER FOR CREDIT DATA OBJECT REQUIRED FOR BATCH UPDATE
							creditCount++;
						}

						dtlCode++;

					}
					result = getSqlModel().singleExecute(insertDtlQuery,
							saveDtlRecordObj);
					result = getSqlModel().singleExecute(creditAmtQuery,
							saveCreditRecordObj);

					result = getSqlModel().singleExecute(updateAmtQuery,
							updateDtlRecordObj);

				}

			}

		} catch (Exception e) {
			logger.error("Exception in updateExtraBenifitRecord----------" + e);
		}
		return result;
	}

	public boolean lockRecord(ExtraWorkBenifitCalculation extraWorkBenifitCal,
			String[] empCode, String[] extraWorkDate,
			String[] extraworkFromTime, String[] extraworkToTime,
			String[] totAmount) {

		boolean result = false;
		try {

			String updateQue = " UPDATE HRMS_EXTRAWORK_PROCESS_HDR "
					+ " SET EXTRAWORK_PROCESS_FLAG='Y' WHERE EXTRAWORK_PROCESS_CODE="
					+ extraWorkBenifitCal.getProcessCode();
			result = getSqlModel().singleExecute(updateQue);

			String updateAmtQuery = "UPDATE HRMS_EXTRAWORK_APPL_DTL  SET EXTRAWORK_AMOUNT =? "
					+ " WHERE EXTRAWORK_DATE=TO_DATE(?,'DD-MM-YYYY') AND EMP_ID=? "
					+ "  AND EXTRAWORK_STARTTIME=? AND EXTRAWORK_ENDTIME=? ";
			if (empCode != null && empCode.length > 0) {

				Object[][] updateDtlRecordObj = new Object[empCode.length][5];

				for (int i = 0; i < empCode.length; i++) {

					updateDtlRecordObj[i][0] = totAmount[i];

					updateDtlRecordObj[i][1] = extraWorkDate[i];

					updateDtlRecordObj[i][2] = empCode[i];

					updateDtlRecordObj[i][3] = extraworkFromTime[i];

					updateDtlRecordObj[i][4] = extraworkToTime[i];

				}

				result = getSqlModel().singleExecute(updateAmtQuery,
						updateDtlRecordObj);
			}

		} catch (Exception e) {
			logger.error("Exception in lockRecord----------" + e);
		}
		return result;
	}

	public String setSalFlag(ExtraWorkBenifitCalculation extraWorkBenifitCal) {

		String str = "N";

		try {

			String selQuery = " SELECT EXTRAWORK_INCLUDE_SAL_FLAG FROM HRMS_EXTRAWORK_PROCESS_HDR "
					+ " WHERE EXTRAWORK_PROCESS_CODE="
					+ extraWorkBenifitCal.getProcessCode();
			Object[][] salFlagObj = getSqlModel().getSingleResult(selQuery);
			if (salFlagObj != null && salFlagObj.length > 0) {
				if (String.valueOf(salFlagObj[0][0]).equals("Y")) {
					str = String.valueOf(salFlagObj[0][0]);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in setSalFlag----------" + e);
		}
		return str;
	}

	public boolean unlockRecord(
			ExtraWorkBenifitCalculation extraWorkBenifitCal, String[] empCode,
			String[] extraWorkDate, String[] extraworkFromTime,
			String[] extraworkToTime) {

		boolean result = false;
		try {

			String updateQue = " UPDATE HRMS_EXTRAWORK_PROCESS_HDR "
					+ " SET EXTRAWORK_PROCESS_FLAG='N' WHERE EXTRAWORK_PROCESS_CODE="
					+ extraWorkBenifitCal.getProcessCode();
			result = getSqlModel().singleExecute(updateQue);

			if (empCode != null && empCode.length > 0) {
				Object[][] updateDtlRecordObj = new Object[empCode.length][4];

				String updateDtlQuery = " UPDATE HRMS_EXTRAWORK_APPL_DTL SET EXTRAWORK_AMOUNT='' "
						+ " WHERE EXTRAWORK_DATE=TO_DATE(?,'DD-MM-YYYY') AND EMP_ID=? AND EXTRAWORK_STARTTIME=? AND EXTRAWORK_ENDTIME=?";

				if (empCode != null && empCode.length > 0) {
					for (int i = 0; i < empCode.length; i++) {

						updateDtlRecordObj[i][0] = extraWorkDate[i];

						updateDtlRecordObj[i][1] = empCode[i];

						updateDtlRecordObj[i][2] = extraworkFromTime[i];

						updateDtlRecordObj[i][3] = extraworkToTime[i];

					}

					result = getSqlModel().singleExecute(updateDtlQuery,
							updateDtlRecordObj);
				}
			}

		} catch (Exception e) {
			logger.error("Exception in unlockRecord----------" + e);
		}

		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	//calculating employee benefit
	public Object[][] getBenifit(String empid, String benifit_type,
			String extrawork_day_type) {

		Object dataObj[][] = null;
		String benefit ="";
		try {
			String empMatchQuery = " SELECT DISTINCT EMP_DIVISION, EMP_DEPT, EMP_CENTER, EMP_RANK, EMP_TYPE,HRMS_EXTRAWORK_SETTING.EXTRAWORK_BENEFIT_CODE "
					+ "  ,EXTRAWORK_BENEFIT_FOR,EXTRAWORK_BENEFIT_TYPE,EXTRAWORK_LEAVE_CREDIT_TYPE, EXTRAWORK_LEAVE_CREDIT_AMT "
					+ "  ,EXTRAWORK_FIXBENEFIT_FULLDAY,EXTRAWORK_FIXBENEFIT_HALFDAY,EXTRAWORK_FIXBENEFIT_HOUR,EXTRAWORK_VARBENEFIT_FORMULA,EXTRAWORK_BENEFIT_CREDITCODE,EXTRAWORK_CHKDAILY_ATTENDANCE   "
					+ "  ,DECODE(EXTRAWORK_ROUNDOFF,'0','No Round Off','1','Round off 15','2','Round off 30','3','Round off 45','4','Round off 60') FROM HRMS_EXTRAWORK_SETTING "
					+ " INNER JOIN HRMS_EXTRAWORK_BENEFIT ON(HRMS_EXTRAWORK_BENEFIT.EXTRAWORK_BENEFIT_CODE=HRMS_EXTRAWORK_SETTING.EXTRAWORK_BENEFIT_CODE)      "
					+ "  WHERE EXTRAWORK_BENEFIT_TYPE!='EL' AND HRMS_EXTRAWORK_SETTING.EMP_DIVISION =(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empid
					+ ")"
					+ " AND EXTRAWORK_BENEFIT_FOR='"
					+ benifit_type
					+ "'"
					+ "  ORDER BY  (CASE WHEN EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+  "
					+ "(CASE WHEN EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
					+ "(CASE WHEN EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+   "
					+ "(CASE WHEN EMP_RANK IS NULL THEN 1 ELSE 0 END )+   "
					+ " (CASE WHEN EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC  ";

			Object empMatchObj[][] = getSqlModel().getSingleResult(
					empMatchQuery);
			Vector vect = new Vector();
			HashMap map = new HashMap();
			Object result[][] = null;
			String query = "";

			if (empMatchObj != null && empMatchObj.length > 0) {
				for (int i = 0; i < empMatchObj.length; i++) {
					query = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 AND ";
					if (!(String.valueOf(empMatchObj[i][4]).equals(""))
							&& !(String.valueOf(empMatchObj[i][4]) == null)
							&& !String.valueOf(empMatchObj[i][4])
									.equals("null")) {
						// if emp type not null
						query += String.valueOf(empMatchObj[i][4])
								+ " =(SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE emp_id="
								+ empid + ")" + " AND ";
					}// end if

					if (!(String.valueOf(empMatchObj[i][1]).equals(""))
							&& !(String.valueOf(empMatchObj[i][1]) == null)
							&& !String.valueOf(empMatchObj[i][1])
									.equals("null")) {
						// if dept not null
						query += String.valueOf(empMatchObj[i][1])
								+ " =(SELECT EMP_DEPT FROM HRMS_EMP_OFFC WHERE emp_id="
								+ empid + ")" + " AND ";
					}// end if

					if (!(String.valueOf(empMatchObj[i][2]).equals(""))
							&& !(String.valueOf(empMatchObj[i][2]) == null)
							&& !String.valueOf(empMatchObj[i][2])
									.equals("null")) {
						// if branch not null
						query += String.valueOf(empMatchObj[i][2])
								+ "  =(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE emp_id="
								+ empid + ")" + " AND ";
					}// end if
					if (!(String.valueOf(empMatchObj[i][3]).equals(""))
							&& !(String.valueOf(empMatchObj[i][3]) == null)
							&& !String.valueOf(empMatchObj[i][3])
									.equals("null")) {
						// if desg not null
						query += String.valueOf(empMatchObj[i][3])
								+ "=(SELECT EMP_RANK FROM HRMS_EMP_OFFC WHERE emp_id="
								+ empid + ")" + " AND ";
					}// end if

					query += " EMP_DIV =(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE emp_id="
							+ empid + ")";

					System.out.println("query       " + query);

					Object res[][] = getSqlModel().getSingleResult(query);

					if (res != null && res.length > 0) {
						result = new Object[1][11];
						result[0][0] = empMatchObj[i][6];//Benefit_for
						
						System.out.println("extrawork_day_type      "+extrawork_day_type);
						
						System.out.println("String.valueOf(empMatchObj[i][7])                "+String.valueOf(empMatchObj[i][7]));
						if (extrawork_day_type.equals("F")
								|| extrawork_day_type.equals("H")) {
							if (String.valueOf(empMatchObj[i][7]).equals("EP")) {
								benefit = "EP";
								//break; 
							}
						} if(extrawork_day_type.equals("O")) {
							if (String.valueOf(empMatchObj[i][7]).equals("VB")) {
								benefit = "VB";
								//break; 
							}
						}
						
						System.out.println("benefit   "+benefit);
						result[0][1] = benefit;//Benefit Type
						result[0][2] = empMatchObj[i][8];//Extra Work Leave Credit Type
						result[0][3] = empMatchObj[i][9];//Extra Work Leave Credit Amount
						result[0][4] = empMatchObj[i][10];//Fixed Full Day
						result[0][5] = empMatchObj[i][11];//Half Day
						result[0][6] = empMatchObj[i][12];//Partial
						result[0][7] = empMatchObj[i][13];//Variable Benefit Formula
						result[0][8] = empMatchObj[i][14];//Credit Code
						result[0][9] = empMatchObj[i][15];//daily attendance flag value
						result[0][10] = empMatchObj[i][16];//round off up to
						vect.add(result);
						map.put(empid + empMatchObj[i][6] + empMatchObj[i][7],
								result);
						
						if (extrawork_day_type.equals("F")
								|| extrawork_day_type.equals("H")) {
							if (String.valueOf(empMatchObj[i][7]).equals("EP")) {								
								break; 
							}
						} if(extrawork_day_type.equals("O")) {
							if (String.valueOf(empMatchObj[i][7]).equals("VB")) {								
								break; 
							}
						}

					}

				}
				dataObj = new Object[vect.size()][11];
				logger.info(" dataObj           " + dataObj.length);
				logger.info(" vect.size()           " + vect.size());
				int count = 0;

				Iterator iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					Object[][] name = (Object[][]) map.get(iterator.next());

					if (name != null && name.length > 0) {

						for (int j = 0; j < name.length; j++) {
							for (int j2 = 0; j2 < name[0].length; j2++) {

								logger.info(" final value of object          "
										+ name[j][j2]);
								dataObj[count][j2] = name[j][j2];
							}
						}
						count++;
					}

				}
			}

		} catch (Exception e) {
			logger.error("Exception in getBenifit----------------------" + e);
		}
		return dataObj;
	}

	public boolean deleteRecord(
			ExtraWorkBenifitCalculation extraWorkBenifitCal,
			String[] extraWorkDate, String[] empCode,
			String[] extraworkFromTime, String[] extraworkToTime) {

		boolean result = false;
		try {

			String CreditsDelQuery = " DELETE FROM HRMS_EXTRAWORK_PROCESS_CREDITS WHERE EXTRAWORK_PROCESS_CODE="
				+ extraWorkBenifitCal.getProcessCode();
			result = getSqlModel().singleExecute(CreditsDelQuery);

			String dtlDelQuery = " DELETE FROM HRMS_EXTRAWORK_PROCESS_DTL WHERE EXTRAWORK_PROCESS_CODE="
					+ extraWorkBenifitCal.getProcessCode();
			result = getSqlModel().singleExecute(dtlDelQuery);
			
			String hdrDelQuery = " DELETE FROM HRMS_EXTRAWORK_PROCESS_HDR WHERE EXTRAWORK_PROCESS_CODE="
				+ extraWorkBenifitCal.getProcessCode();
			result = getSqlModel().singleExecute(hdrDelQuery);
					
			Object[][] updateDtlRecordObj = new Object[empCode.length][4];
			if (empCode != null && empCode.length > 0) {
				String updateDtlQuery = " UPDATE HRMS_EXTRAWORK_APPL_DTL SET EXTRAWORK_AMOUNT='' "
						+ " WHERE EXTRAWORK_DATE=TO_DATE(?,'DD-MM-YYYY') AND EMP_ID=? AND EXTRAWORK_STARTTIME=? AND EXTRAWORK_ENDTIME=?";

				if (empCode != null && empCode.length > 0) {
					for (int i = 0; i < empCode.length; i++) {

						updateDtlRecordObj[i][0] = extraWorkDate[i];

						updateDtlRecordObj[i][1] = empCode[i];

						updateDtlRecordObj[i][2] = extraworkFromTime[i];

						updateDtlRecordObj[i][3] = extraworkToTime[i];

					}

					result = getSqlModel().singleExecute(updateDtlQuery,
							updateDtlRecordObj);
				}
			}

		} catch (Exception e) {
			logger.error("Exception in deleteRecord----------------------" + e);
			e.printStackTrace();
		}
		return result;
	}

	private long calculateWorkingHrs(String start_Time, String end_Time,
			String roundOffUpto, String extraworkdaytype, String extraWorkDate) {

		int check = 0;

		String[] startTime = start_Time.split(":");
		String[] endTime = end_Time.split(":");

		String[] diffHours = new String[2];
		diffHours[0] = String.valueOf(Integer.parseInt(endTime[0])
				- Integer.parseInt(startTime[0]));
		if (Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]) >= 0) {
			diffHours[1] = String.valueOf(Integer.parseInt(endTime[1])
					- Integer.parseInt(startTime[1]));
		} else {
			diffHours[1] = String.valueOf(Integer.parseInt(endTime[1])
					+ Integer.parseInt(startTime[1]));
			diffHours[0] = String.valueOf(Integer.parseInt(diffHours[0]) - 1);
		}
		String timeDifference = diffHours[0] + ":" + diffHours[1];

		long endTimeCheck = getMinute(timeDifference);

		if (extraworkdaytype.equals("F") && endTimeCheck < 0) {
			check = 1;
		} else if (extraworkdaytype.equals("H") && (endTimeCheck / 2) < 0) {
			check = 1;
		}

		String calculateTimeDiffQuery = "  SELECT TRIM(FLOOR(((TO_DATE('"
				+ extraWorkDate + "-" + end_Time + "','dd-mm-yyyy-HH24:MI')+"
				+ check + " -TO_DATE('" + extraWorkDate + "-" + start_Time
				+ "','dd-mm-yyyy-HH24:MI'))*24*60*60)/3600)" + "  || ':' || "
				+ "  FLOOR((((TO_DATE('" + extraWorkDate + "-" + end_Time
				+ "','dd-mm-yyyy-HH24:MI')-TO_DATE('" + extraWorkDate + "-"
				+ start_Time + "','dd-mm-yyyy-HH24:MI'))*24*60*60) - "
				+ "   FLOOR(((TO_DATE('" + extraWorkDate + "-" + end_Time
				+ "','dd-mm-yyyy-HH24:MI')-TO_DATE('" + extraWorkDate + "-"
				+ start_Time
				+ "','dd-mm-yyyy-HH24:MI'))*24*60*60)/3600)*3600)/60)"
				+ " )TIME_DIFFERENCE FROM DUAL ";

		//System.out.println("calculateTimeDiffQuery-----------"
		//+ calculateTimeDiffQuery);

		Object timeDiffObj[][] = getSqlModel().getSingleResult(
				calculateTimeDiffQuery);

		//System.out.println("String.valueOf(timeDiffObj[0][0])           "+String.valueOf(timeDiffObj[0][0]));

		long totalWorkingHrsinmin = 0;

		if (timeDiffObj != null && timeDiffObj.length > 0) {
			totalWorkingHrsinmin = getMinute(String.valueOf(timeDiffObj[0][0]));
		}
		//	System.out.println("totalWorkingHrsinmin           "+totalWorkingHrsinmin);

		long finalWorkinghrsinmin = 0;

		//System.out.println("roundOffUpto    "+roundOffUpto);
		if (roundOffUpto.equals("No Round Off")) {
			finalWorkinghrsinmin = totalWorkingHrsinmin;
		}
		long multipicationFactor = 0;
		long reminder = 0;

		long addValue = 0;

		if (roundOffUpto.equals("Round off 15")) {
			//System.out.println("in first if-----------");

			multipicationFactor = totalWorkingHrsinmin / 15;

			reminder = totalWorkingHrsinmin % 15;

			//	System.out.println("in first if----reminder-------"+reminder);

			//	System.out.println("in first if----multipicationFactor-------"+multipicationFactor);

			if (reminder > 7) {
				addValue = 15;
			}

			//	System.out.println("in first if----addValue-------"+addValue);

			finalWorkinghrsinmin = 15 * multipicationFactor + addValue;

			//	System.out.println("in first if----finalWorkinghrsinmin-------"+finalWorkinghrsinmin);

		}

		if (roundOffUpto.equals("Round off 30")) {

			//System.out.println("in second if 11");

			multipicationFactor = totalWorkingHrsinmin / 30;

			reminder = totalWorkingHrsinmin % 30;

			//System.out.println("in first if--11--reminder-------"+reminder);

			//System.out.println("in first if--11--multipicationFactor-------"+multipicationFactor);

			if (reminder > 15) {
				addValue = 30;
			}

			//System.out.println("in first if--11--addValue-------"+addValue);

			finalWorkinghrsinmin = 30 * multipicationFactor + addValue;

			//System.out.println("in first if--11--finalWorkinghrsinmin-------"+finalWorkinghrsinmin);

		}

		if (roundOffUpto.equals("Round off 45")) {

			//System.out.println("in third if222222222222");

			multipicationFactor = totalWorkingHrsinmin / 45;

			reminder = totalWorkingHrsinmin % 45;

			//System.out.println("in first if--11-2222222-reminder-------"+reminder);

			//System.out.println("in first if--11-22222222222-multipicationFactor-------"+multipicationFactor);

			if (reminder > 22) {
				addValue = 45;
			}

			//System.out.println("in first if--11-222222222222222-addValue-------"+addValue);

			finalWorkinghrsinmin = 45 * multipicationFactor + addValue;

			//System.out.println("in first if--11-222222222222222222222-finalWorkinghrsinmin-------"+finalWorkinghrsinmin);
		}

		if (roundOffUpto.equals("Round off 60")) {
			//System.out.println("in fourth if 333333333333333");

			multipicationFactor = totalWorkingHrsinmin / 60;

			reminder = totalWorkingHrsinmin % 60;

			//System.out.println("in first if--11-2222222-333333333333reminder-------"+reminder);

			//System.out.println("in first if--11-22222222222-3333333333333333333multipicationFactor-------"+multipicationFactor);

			if (reminder > 30) {
				addValue = 60;
			}

			//System.out.println("in first if--11-2222222222222224444444444444444444addValue-------"+addValue);

			finalWorkinghrsinmin = 60 * multipicationFactor + addValue;

			//System.out.println("in first if--11-2222222222244444444444444442222222222-finalWorkinghrsinmin-------"+finalWorkinghrsinmin);
		}

		//System.out.println("fianal hrs     $$$$$$$$        "+finalWorkinghrsinmin);

		return finalWorkinghrsinmin;

	}

	public int getMinute(String result) {
		int min = 0;
		try {

			if (result == null || result.equals("null") || result.equals("")) {
				return min;
			}// end of if
			else {
				if (result.contains(":")) {
					String[] chk = result.split(":");
					try {
						min = Integer.parseInt(chk[0]) * 60;
						min = min + Integer.parseInt(chk[1]);
					} catch (Exception e) {
					}
				}
			}// end of else
		} catch (Exception e) {
			e.printStackTrace();

		}

		return min;
	}

	public String getHours(int minutes) {
		String hours = "";
		try {
			int hrs = (int) Math.floor(minutes / 60);
			int mins = (int) Math.floor(minutes % 60);

			hours = Integer.parseInt(String.valueOf(hrs)) < 10 ? "0"
					+ String.valueOf(hrs) : String.valueOf(hrs);
			hours += ":"
					+ (Integer.parseInt(String.valueOf(mins)) < 10 ? "0"
							+ String.valueOf(minutes % 60) : String
							.valueOf(minutes % 60));
		} catch (Exception e) {
			logger.error("Exception in getHours:" + e);
		}
		return hours;
	}
	

	//calculating employee benefit if partial
	public Object[][] getBenifitIfPartial(String empid, String benifit_type,
			String extrawork_day_type) {

		Object dataObj[][] = null;
		String benefit ="";
		try {
			String empMatchQuery = " SELECT DISTINCT EMP_DIVISION, EMP_DEPT, EMP_CENTER, EMP_RANK, EMP_TYPE,HRMS_EXTRAWORK_SETTING.EXTRAWORK_BENEFIT_CODE "
					+ "  ,EXTRAWORK_BENEFIT_FOR,EXTRAWORK_BENEFIT_TYPE,EXTRAWORK_LEAVE_CREDIT_TYPE, EXTRAWORK_LEAVE_CREDIT_AMT "
					+ "  ,EXTRAWORK_FIXBENEFIT_FULLDAY,EXTRAWORK_FIXBENEFIT_HALFDAY,EXTRAWORK_FIXBENEFIT_HOUR,EXTRAWORK_VARBENEFIT_FORMULA,EXTRAWORK_BENEFIT_CREDITCODE,EXTRAWORK_CHKDAILY_ATTENDANCE   "
					+ "  ,DECODE(EXTRAWORK_ROUNDOFF,'0','No Round Off','1','Round off 15','2','Round off 30','3','Round off 45','4','Round off 60') FROM HRMS_EXTRAWORK_SETTING "
					+ " INNER JOIN HRMS_EXTRAWORK_BENEFIT ON(HRMS_EXTRAWORK_BENEFIT.EXTRAWORK_BENEFIT_CODE=HRMS_EXTRAWORK_SETTING.EXTRAWORK_BENEFIT_CODE)      "
					+ "  WHERE EXTRAWORK_BENEFIT_TYPE='EL' AND HRMS_EXTRAWORK_SETTING.EMP_DIVISION =(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empid
					+ ")"
					+ " AND EXTRAWORK_BENEFIT_FOR='"
					+ benifit_type
					+ "'"
					+ "  ORDER BY  (CASE WHEN EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+  "
					+ "(CASE WHEN EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
					+ "(CASE WHEN EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+   "
					+ "(CASE WHEN EMP_RANK IS NULL THEN 1 ELSE 0 END )+   "
					+ " (CASE WHEN EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC  ";

			Object empMatchObj[][] = getSqlModel().getSingleResult(
					empMatchQuery);
			Vector vect = new Vector();
			HashMap map = new HashMap();
			Object result[][] = null;
			String query = "";

			if (empMatchObj != null && empMatchObj.length > 0) {
				for (int i = 0; i < empMatchObj.length; i++) {
					query = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 AND ";
					if (!(String.valueOf(empMatchObj[i][4]).equals(""))
							&& !(String.valueOf(empMatchObj[i][4]) == null)
							&& !String.valueOf(empMatchObj[i][4])
									.equals("null")) {
						// if emp type not null
						query += String.valueOf(empMatchObj[i][4])
								+ " =(SELECT EMP_TYPE FROM HRMS_EMP_OFFC WHERE emp_id="
								+ empid + ")" + " AND ";
					}// end if

					if (!(String.valueOf(empMatchObj[i][1]).equals(""))
							&& !(String.valueOf(empMatchObj[i][1]) == null)
							&& !String.valueOf(empMatchObj[i][1])
									.equals("null")) {
						// if dept not null
						query += String.valueOf(empMatchObj[i][1])
								+ " =(SELECT EMP_DEPT FROM HRMS_EMP_OFFC WHERE emp_id="
								+ empid + ")" + " AND ";
					}// end if

					if (!(String.valueOf(empMatchObj[i][2]).equals(""))
							&& !(String.valueOf(empMatchObj[i][2]) == null)
							&& !String.valueOf(empMatchObj[i][2])
									.equals("null")) {
						// if branch not null
						query += String.valueOf(empMatchObj[i][2])
								+ "  =(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE emp_id="
								+ empid + ")" + " AND ";
					}// end if
					if (!(String.valueOf(empMatchObj[i][3]).equals(""))
							&& !(String.valueOf(empMatchObj[i][3]) == null)
							&& !String.valueOf(empMatchObj[i][3])
									.equals("null")) {
						// if desg not null
						query += String.valueOf(empMatchObj[i][3])
								+ "=(SELECT EMP_RANK FROM HRMS_EMP_OFFC WHERE emp_id="
								+ empid + ")" + " AND ";
					}// end if

					query += " EMP_DIV =(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE emp_id="
							+ empid + ")";

					System.out.println("query       " + query);

					Object res[][] = getSqlModel().getSingleResult(query);

					if (res != null && res.length > 0) {
						result = new Object[1][11];
						result[0][0] = empMatchObj[i][6];//Benefit_for
						
						System.out.println("extrawork_day_type      "+extrawork_day_type);
						
						System.out.println("String.valueOf(empMatchObj[i][7])                "+String.valueOf(empMatchObj[i][7]));
						if (extrawork_day_type.equals("F")
								|| extrawork_day_type.equals("H")) {
							if (String.valueOf(empMatchObj[i][7]).equals("EP")) {
								benefit = "EP";
								//break; 
							}
						} if(extrawork_day_type.equals("O")) {
							if (String.valueOf(empMatchObj[i][7]).equals("VB")) {
								benefit = "VB";
								//break; 
							}
						}
						
						System.out.println("benefit   "+benefit);
						result[0][1] = benefit;//Benefit Type
						result[0][2] = empMatchObj[i][8];//Extra Work Leave Credit Type
						result[0][3] = empMatchObj[i][9];//Extra Work Leave Credit Amount
						result[0][4] = empMatchObj[i][10];//Fixed Full Day
						result[0][5] = empMatchObj[i][11];//Half Day
						result[0][6] = empMatchObj[i][12];//Partial
						result[0][7] = empMatchObj[i][13];//Variable Benefit Formula
						result[0][8] = empMatchObj[i][14];//Credit Code
						result[0][9] = empMatchObj[i][15];//daily attendance flag value
						result[0][10] = empMatchObj[i][16];//round off up to
						vect.add(result);
						map.put(empid + empMatchObj[i][6] + empMatchObj[i][7],
								result);
						
						if (extrawork_day_type.equals("F")
								|| extrawork_day_type.equals("H")) {
							if (String.valueOf(empMatchObj[i][7]).equals("EP")) {								
								break; 
							}
						} if(extrawork_day_type.equals("O")) {
							if (String.valueOf(empMatchObj[i][7]).equals("VB")) {								
								break; 
							}
						}

					}

				}
				dataObj = new Object[vect.size()][11];
				logger.info(" dataObj           " + dataObj.length);
				logger.info(" vect.size()           " + vect.size());
				int count = 0;

				Iterator iterator = map.keySet().iterator();
				while (iterator.hasNext()) {
					Object[][] name = (Object[][]) map.get(iterator.next());

					if (name != null && name.length > 0) {

						for (int j = 0; j < name.length; j++) {
							for (int j2 = 0; j2 < name[0].length; j2++) {

								logger.info(" final value of object          "
										+ name[j][j2]);
								dataObj[count][j2] = name[j][j2];
							}
						}
						count++;
					}

				}
			}

		} catch (Exception e) {
			logger.error("Exception in getBenifit----------------------" + e);
		}
		return dataObj;
	}

}
