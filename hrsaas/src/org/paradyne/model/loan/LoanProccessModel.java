/**
 * 
 */
package org.paradyne.model.loan;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Loan.LoanApproval;
import org.paradyne.bean.Loan.LoanProcessing;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;


/**
 * @author MangeshJ
 * @date 13-07-2008 LoanApplicationModel class to write business logic to save,
 *       update, delete and view loan processing details for the selected
 *       application
 */
public class LoanProccessModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanProccessModel.class);

	// NumberFormat teas = NumberFormat.getNumberInstance();
	NumberFormat formatter = new DecimalFormat("#0.00");

	/**
	 * Method name-- showApplDetails parameters--- LoanProcessing bean return
	 * type-- void Purpose --- To retrieve all the details of a selected
	 * application and set them in respective form fields
	 */
	public void showApplDetails(LoanProcessing bean) {
		try {
			Object[] loanCode = new Object[1];
			loanCode[0] = bean.getLoanAppCode();
			Object data[][] = getSqlModel().getSingleResult(getQuery(1),
					loanCode);
			bean.setLoanAppCode(String.valueOf(data[0][0]));
			bean.setEmpCode(String.valueOf(data[0][1]));
			bean.setEmpToken(checkNull(String.valueOf(data[0][2])));
			bean.setEmpName(checkNull(String.valueOf(data[0][3])));
			bean.setBranchName(checkNull(String.valueOf(data[0][4])));
			bean.setDeptName(checkNull(String.valueOf(data[0][5])));
			bean.setAppdate(checkNull(String.valueOf(data[0][6])));
			bean.setLoanType(checkNull(String.valueOf(data[0][7])));
			bean.setLoanAmount(checkNull(String.valueOf(data[0][8])));
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			logger.error(e);
		}
	}

	public void grossSalary(LoanProcessing bean) {
		/*
		 * execute select query to retrieve gross salary of the employee from
		 * HRMS_EMP_CREDIT
		 */
		Object[][] getGrossSalary = getSqlModel().getSingleResult(getQuery(12),
				new Object[] { bean.getEmpCode() });

		/*
		 * if data retrieved successfully then set the data to the related form
		 * fields
		 */
		if (getGrossSalary != null && getGrossSalary.length != 0) {
			bean.setGrossSalary(String.valueOf(getGrossSalary[0][0]));
		} // end of if
	}

	/**
	 * Method name-- generateFirstInterest parameters--- String
	 * paymentDate,String startingDate,double principalAmt,String interestRate
	 * return type-- Double Purpose --- To calculate the first interest amount
	 * for the selected application
	 */
	public Double generateFirstInterest(String paymentDate,
			String startingDate, double principalAmt, String interestRate) {
		int daysBetween = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			Date payDate = sdf.parse(paymentDate);
			Date startDate = sdf.parse(startingDate);

			Calendar calFrom = Calendar.getInstance();
			calFrom.setTime(payDate);

			Calendar calTo = Calendar.getInstance();
			calTo.setTime(startDate);

			while (calFrom.before(calTo)) {
				calFrom.add(calFrom.DAY_OF_MONTH, 1);
				daysBetween++;
			}
			//logger.info("days -----  " + daysBetween);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (interestRate.equals(""))
			interestRate = "0";
		Double intAmt = ((principalAmt * Double.parseDouble(interestRate) * daysBetween))
				/ (100.0 * 365);
		////logger.info("intAmt inside generateFirstInterest= GGGG==" + intAmt);
		return intAmt;
	}

	/*
	 * public Double generateFirstInterest(LoanProcessing bean, String
	 * paymentDate,String startingDate,double principalAmt,String interestRate){
	 * 
	 * String query="SELECT (TO_DATE('"+startingDate+"','DD-MM-YYYY')-
	 * TO_DATE('"+paymentDate+"','DD-MM-YYYY'))FROM DUAL"; Object daysDiff
	 * [][]=getSqlModel().getSingleResult(query); if(interestRate.equals(""))
	 * interestRate="0"; Double intAmt = ((principalAmt *
	 * Double.parseDouble(interestRate)*Double.parseDouble(String.valueOf(daysDiff[0][0])))/
	 * (100.0*365)); double emiForMonth = 0.0; double emiForDays = 0.0; double
	 * principalForDays =0.0; double intRate=Double.parseDouble(interestRate);
	 * double noOfInst=Double.parseDouble(bean.getInstallmentNumber());
	 * emiForMonth = ((principalAmt * intRate) * (Math .pow((1.0 + intRate),
	 * noOfInst))) / ((Math.pow((1.0 + intRate), noOfInst)) - 1.0);
	 * 
	 * emiForDays = emiForMonth *
	 * Double.parseDouble(String.valueOf(daysDiff[0][0]))/ 30.0;
	 * principalForDays = (principalAmt * intRate *
	 * Double.parseDouble(String.valueOf(daysDiff[0][0])))/ (noOfInst * 100.0 *
	 * 30.0 );
	 * 
	 * logger.info("intAmt inside generateFirstInterest==="+intAmt); return
	 * intAmt; }
	 */
	/**
	 * Method name-- generateInstallmentSchedule parameters--- LoanProcessing
	 * bean,HttpServletRequest request return type-- void Purpose --- To
	 * generate the installment schedule for the selected application
	 */
	public boolean generateInstallmentSchedule(LoanProcessing bean,
			HttpServletRequest request) {

		boolean result = false;
		Map<String, String> checkMap = new HashMap<String, String>();
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			int noOfInstallment = 0;
			if (!(bean.getInterestType().equals("I"))) {
				noOfInstallment = Integer.parseInt(bean
						.getInstallmentNumberFlat());
			} else {
				noOfInstallment = Integer.parseInt(bean.getInstallmentNumberFlat());
			}
			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			String[] date = bean.getPaymentDate().split("-");
			String[] date1 = bean.getStartingDate().split("-");
			String strDay1 = date1[0];
			String strYear = date[2];
			String strMon1 = date1[1];
			String startingDate = bean.getStartingDate();
			double firstInt = generateFirstInterest(bean.getPaymentDate(),
					startingDate, Double.parseDouble(bean.getSanctionAmount()),
					bean.getInterestRate());

			String[][] installmentData = null;
			/*
			 * if (! bean.getInterestType().equals("I")){ installmentData =
			 * calculateInstallment(Double.parseDouble(bean.getSanctionAmount()),
			 * noOfInstallment, bean.getInterestRate(), bean.getInterestType(),
			 * bean.getStartingDate()); } else if (
			 * bean.getInterestType().equals("I")){
			 * 
			 * installmentData =
			 * calcReduceInterestInstallmentSch(balanceAmt,noOfInstallment,
			 * Double.parseDouble(bean.getInterestRate()),
			 * bean.getStartingDate(),Double.parseDouble(bean.getSanctionAmount())/noOfInstallment);
			 *  }
			 */

			double principalAmt = Double.parseDouble(bean.getSanctionAmount());
			Object[] loanApplCode = { bean.getHiddenCode() };
			Object[][] paidInstallData = getSqlModel().getSingleResult(
					getQuery(14), loanApplCode);

			Object[][] prepaymentSum = getSqlModel().getSingleResult(
					getQuery(15), loanApplCode);

			/*
			 * set paid installment if any
			 * 
			 */
			if (paidInstallData != null) {

				String monthYear = "";

				for (int i = 0; i < paidInstallData.length; i++) {
					LoanProcessing bean1 = new LoanProcessing();
					monthYear = String.valueOf(paidInstallData[i][7]) + "-"
							+ String.valueOf(paidInstallData[i][0]) + "-"
							+ String.valueOf(paidInstallData[i][1]);
					bean1.setMonthYear(monthYear);
					bean1.setMonthNo(String.valueOf(paidInstallData[i][9]));
					bean1.setPrincipalAmt(String.valueOf(paidInstallData[i][2]));
					bean1.setInterestAmt(String.valueOf(Double.parseDouble(String.valueOf(paidInstallData[i][3]))));
					bean1.setInstallmentAmt(String.valueOf(Double.parseDouble(String.valueOf(paidInstallData[i][4]))));
					bean1.setBalancePrincipalAmt(Utility.twoDecimals(String.valueOf(paidInstallData[i][5])));
					totalEmi += Double.parseDouble(String.valueOf(paidInstallData[i][4]));
					totalIntPaid += Double.parseDouble(String.valueOf(paidInstallData[i][3]));
					totalPrincipal += Double.parseDouble(String.valueOf(paidInstallData[i][2]));
					bean1.setPaidFlag("true");

					tableList.add(bean1);
					try {
						logger.info("Paid flag  in if   "
								+ String.valueOf(paidInstallData[i][6]));
						checkMap.put("" + i, "Y");

					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e);
					}
					principalAmt = principalAmt
							- Double.parseDouble("" + paidInstallData[i][2]); // subtract
																				// the
																				// paid
																				// principal
																				// amount
																				// from
																				// the
																				// total
																				// principal
																				// amount
					noOfInstallment -= 1;
					startingDate = String.valueOf(paidInstallData[i][8]);

				} // end of for loop

				bean.setNoOfPaidInstallments(String
						.valueOf(paidInstallData.length));

			}
			principalAmt -= Double.parseDouble(String
					.valueOf(prepaymentSum[0][0])); // subtract the prepaid
													// amount.

			startingDate = getDate(startingDate);

			logger.info("balance principal after prepayment==" + principalAmt);
			boolean isInstallmentPaid = Boolean.parseBoolean(bean
					.getInstallmentPaidFlag());
			installmentData = calculateInstallment(principalAmt,
					noOfInstallment, bean.getInterestRate(), bean
							.getInterestType(), startingDate, firstInt,
					isInstallmentPaid);

			if (bean.getInterestType().equals("I")
					&& bean.getInstallmentPaidFlag().equals("false")) {
				LoanProcessing bean1 = new LoanProcessing();

				bean1.setMonthYear(strDay1
								+ "-"
								+ (getMonthString(Integer.parseInt(strMon1))
										+ "-" + strYear));
				bean1.setMonthNo(strMon1);
				bean1.setPrincipalAmt(formatter.format((Double.parseDouble(bean
						.getSanctionAmount()) / noOfInstallment)));
				bean1.setInterestAmt(formatter.format(firstInt));
				bean1
						.setInstallmentAmt(formatter
								.format(firstInt
										+ ((Double.parseDouble(bean
												.getSanctionAmount()) / noOfInstallment))));
				bean1.setBalancePrincipalAmt(formatter.format(Double
						.parseDouble(bean.getSanctionAmount())
						- (Double.parseDouble(bean.getSanctionAmount()) / 12)));
				totalIntPaid += firstInt;
				tableList.add(bean1);
				try {

					bean.setPaidFlag("false");
					checkMap.put("" + (tableList.size() - 1), "N");

					logger.info("checkmap====" + checkMap.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // end of if

			if (installmentData != null) {
				if (!bean.getInterestType().equals("I")) {

					for (int i = 0; i < installmentData.length; i++) {
						LoanProcessing bean1 = new LoanProcessing();

						bean1.setMonthYear(String
								.valueOf(installmentData[i][0]));
						String[] date3 = String.valueOf(installmentData[i][0]).split("-");
						String strMon3 = getMonthNumber(date3[1]);
						
						bean1.setMonthNo(strMon3);
						bean1.setPrincipalAmt(formatter.format(Double
								.parseDouble(installmentData[i][1])));
						bean1.setInterestAmt(formatter.format(Double
								.parseDouble(installmentData[i][2])));
						bean1.setInstallmentAmt(formatter.format(Double
								.parseDouble(installmentData[i][3])));
						bean1.setBalancePrincipalAmt(formatter.format(Double
								.parseDouble(installmentData[i][4])));

						totalEmi += Double.parseDouble(installmentData[i][3]);
						totalIntPaid += Double
								.parseDouble(installmentData[i][2]);
						totalPrincipal += Double
								.parseDouble(installmentData[i][1]);
						tableList.add(bean1);
						try {

							bean.setPaidFlag("false");
							checkMap.put("" + (tableList.size() - 1), "N");

							logger.info("checkmap====" + checkMap.size());
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(e);
						}

					} // end of for loop

				} else {

					for (int i = 0; i < installmentData.length; i++) {
						LoanProcessing bean1 = new LoanProcessing();

						bean1.setMonthYear(String
								.valueOf(installmentData[i][0]));
						
						String[] date3 = String.valueOf(installmentData[i][0]).split("-");
						String strMon3 = getMonthNumber(date3[1]);
						
						bean1.setMonthNo(strMon3);
						
						bean1.setPrincipalAmt(formatter.format(Double
								.parseDouble(installmentData[i][1])));
						bean1.setInterestAmt(formatter.format(Double
								.parseDouble(installmentData[i][2])));
						bean1.setInstallmentAmt(formatter.format(Double
								.parseDouble(installmentData[i][3])));
						bean1.setBalancePrincipalAmt(formatter.format(Double
								.parseDouble(installmentData[i][4])));

						totalEmi += Double.parseDouble(installmentData[i][3]);
						totalIntPaid += Double
								.parseDouble(installmentData[i][2]);
						totalPrincipal += Double
								.parseDouble(installmentData[i][1]);
						tableList.add(bean1);
						try {

							bean.setPaidFlag("false");
							checkMap.put("" + (tableList.size() - 1), "N");

							logger.info("checkmap====" + checkMap.size());
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(e);
						}
					}

				}
				if (!bean.getInterestType().equals("R")) {
					bean.setTotalPrincipalAmt(formatter.format(Double
							.parseDouble(bean.getSanctionAmount())));
					logger.info("bean.setTotalPrincipalAmt=="
							+ bean.getTotalPrincipalAmt());
					bean.setTotalInstallmenteAmt(formatter
							.format((Double.parseDouble(bean
									.getSanctionAmount()) + totalIntPaid)));
				} // end of if
				else
					bean
							.setTotalInstallmenteAmt((formatter
									.format((totalEmi))));
				bean.setTotalInterestAmt(formatter.format(totalIntPaid));

				bean.setInstallmentList(tableList);
				request.setAttribute("data", checkMap);
				// end of if
				result = true;
				/*if(bean.getApplicationStatus().equals("F")){
					bean.setInstallmentFlag("false");
					System.out.println("FALSE=="+bean.getApplicationStatus());
				}else{
					bean.setInstallmentFlag("true");
					System.out.println("TRUE==="+bean.getApplicationStatus());
				}
				*/
				bean.setInstallmentFlag("true");
			} else {
				result = false;
				bean.setInstallmentFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			bean.setInstallmentFlag("false");
			return false;
		}
		showPrePayemntDetails(bean);
		return result;
	}

	public boolean installmentScheduleForPrincipal(LoanProcessing bean,
			HttpServletRequest request) {

		boolean result = false;
		Map<String, String> checkMap = new HashMap<String, String>();
		try {

			logger.info("inside installmentScheduleForPrincipal");

			ArrayList<Object> tableList = new ArrayList<Object>();

			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			double intRate = Double.parseDouble(bean.getInterestRate());
			String[] date = bean.getPaymentDate().split("-");
			String[] date1 = bean.getStartingDate().split("-");
			String strDay1 = date1[0];
			String strYear = date[2];
			String strMon1 = date1[1];
			String startingDate = bean.getStartingDate();
			double firstInt = generateFirstInterest(bean.getPaymentDate(),
					startingDate, Double.parseDouble(bean.getSanctionAmount()),
					bean.getInterestRate());

			logger.info("intAmt inside generateFirstInterest===" + firstInt);
			String[][] installmentData = null;

			double principalAmt = Double.parseDouble(bean.getSanctionAmount());
			Object[] loanApplCode = { bean.getHiddenCode() };
			Object[][] paidInstallData = getSqlModel().getSingleResult(
					getQuery(14), loanApplCode);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Object[][] prepaymentSum = getSqlModel().getSingleResult(
					getQuery(15), loanApplCode);

			/*
			 * set paid installment if any
			 * 
			 */
			if (paidInstallData != null) {

				String monthYear = "";

				for (int i = 0; i < paidInstallData.length; i++) {
					LoanProcessing bean1 = new LoanProcessing();
					monthYear = String.valueOf(paidInstallData[i][7]) + "-"
							+ String.valueOf(paidInstallData[i][0]) + "-"
							+ String.valueOf(paidInstallData[i][1]);
					bean1.setMonthYear(monthYear);
					bean1.setMonthNo(String.valueOf(paidInstallData[i][9]));
					bean1
							.setPrincipalAmt(String
									.valueOf(paidInstallData[i][2]));
					bean1.setInterestAmt(String
							.valueOf(Double.parseDouble(String
									.valueOf(paidInstallData[i][3]))));
					bean1.setInstallmentAmt(String
							.valueOf(Double.parseDouble(String
									.valueOf(paidInstallData[i][4]))));
					bean1.setBalancePrincipalAmt(Utility.twoDecimals(String
							.valueOf(paidInstallData[i][5])));
					totalEmi += Double.parseDouble(String
							.valueOf(paidInstallData[i][4]));
					totalIntPaid += Double.parseDouble(String
							.valueOf(paidInstallData[i][3]));
					totalPrincipal += Double.parseDouble(String
							.valueOf(paidInstallData[i][2]));
					bean1.setPaidFlag("true");

					tableList.add(bean1);
					try {
						logger.info("Paid flag  in if   "
								+ String.valueOf(paidInstallData[i][6]));
						checkMap.put("" + i, "Y");

					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e);
					}
					principalAmt = principalAmt
							- Double.parseDouble("" + paidInstallData[i][2]); // subtract
																				// the
																				// paid
																				// principal
																				// amount
																				// from
																				// the
																				// total
																				// principal
																				// amount
					startingDate = String.valueOf(paidInstallData[i][8]);
				} // end of for loop

				bean.setNoOfPaidInstallments(String
						.valueOf(paidInstallData.length));

			}
			principalAmt -= Double.parseDouble(String
					.valueOf(prepaymentSum[0][0])); // subtract the prepaid
													// amount.
			startingDate = getDate(startingDate);
			int noOfInstallment = 0;
			double monthlyPrincAmt = Double.parseDouble(bean
					.getMonthlyPrincAmount());
			String noOfInstallmentString = String
					.valueOf(Math.ceil(principalAmt
							/ Double.parseDouble(bean.getMonthlyPrincAmount())));
			noOfInstallment = Integer.parseInt(noOfInstallmentString.substring(
					0, noOfInstallmentString.indexOf(".")));

			logger.info("balance principal after prepayment==" + principalAmt);

			if ((bean.getInstallmentPaidFlag().equals("false"))) {

				installmentData = calcReduceInterestInstallmentSch(principalAmt
						- Double.parseDouble(bean.getMonthlyPrincAmount()),
						noOfInstallment - 1, intRate, startingDate, Double
								.parseDouble(bean.getMonthlyPrincAmount()));

				LoanProcessing bean1 = new LoanProcessing();

				bean1
						.setMonthYear(strDay1
								+ "-"
								+ (getMonthString(Integer.parseInt(strMon1))
										+ "-" + strYear));
				
				bean1.setMonthNo(strMon1);
				bean1.setPrincipalAmt(formatter.format(monthlyPrincAmt));
				bean1.setInterestAmt(formatter.format(firstInt));
				bean1.setInstallmentAmt(formatter.format(firstInt
						+ monthlyPrincAmt));
				bean1.setBalancePrincipalAmt(formatter.format(Double
						.parseDouble(bean.getSanctionAmount())
						- (Double.parseDouble(bean.getSanctionAmount()) / 12)));
				totalIntPaid += firstInt;
				tableList.add(bean1);
				try {

					bean.setPaidFlag("false");
					checkMap.put("" + (tableList.size() - 1), "N");

					logger.info("checkmap====" + checkMap.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // end of if
			else {
				Calendar calInst = Calendar.getInstance();
				Date instDate = sdf.parse(startingDate);
				calInst.setTime(instDate);

				calInst.add(calInst.MONTH, -1);
				installmentData = calcReduceInterestInstallmentSch(
						principalAmt, noOfInstallment, intRate, sdf
								.format(calInst.getTime()), Double
								.parseDouble(bean.getMonthlyPrincAmount()));
			}
			if (installmentData != null) {

				for (int i = 0; i < installmentData.length; i++) {
					LoanProcessing bean1 = new LoanProcessing();

					bean1.setMonthYear(String.valueOf(installmentData[i][0]));
					String[] date3 = String.valueOf(installmentData[i][0]).split("-");
					String strMon3 = getMonthNumber(date3[1]);
					
					bean1.setMonthNo(strMon3);
					bean1.setPrincipalAmt(formatter.format(Double
							.parseDouble(installmentData[i][1])));
					bean1.setInterestAmt(formatter.format(Double
							.parseDouble(installmentData[i][2])));
					bean1.setInstallmentAmt(formatter.format(Double
							.parseDouble(installmentData[i][3])));
					bean1.setBalancePrincipalAmt(formatter.format(Double
							.parseDouble(installmentData[i][4])));

					totalEmi += Double.parseDouble(installmentData[i][3]);
					totalIntPaid += Double.parseDouble(installmentData[i][2]);
					totalPrincipal += Double.parseDouble(installmentData[i][1]);
					tableList.add(bean1);
					try {

						bean.setPaidFlag("false");
						checkMap.put("" + (tableList.size() - 1), "N");

						logger.info("checkmap====" + checkMap.size());
					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e);
					}
				}

				bean.setTotalPrincipalAmt(formatter.format(Double
						.parseDouble(bean.getSanctionAmount())));
				logger.info("bean.setTotalPrincipalAmt=="
						+ bean.getTotalPrincipalAmt());
				bean
						.setTotalInstallmenteAmt(formatter
								.format((Double.parseDouble(bean
										.getSanctionAmount()) + totalIntPaid)));
				bean.setTotalInterestAmt(formatter.format(totalIntPaid));

				bean.setInstallmentList(tableList);
				request.setAttribute("data", checkMap);
				// end of if
				result = true;
				bean.setInstallmentFlag("true");
			} else {
				result = false;
				bean.setInstallmentFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			bean.setInstallmentFlag("false");
			return false;
		}
		showPrePayemntDetails(bean);
		return result;
	}

	/*
	 * /** Method name-- generateInstSchForReducePrincipal parameters---
	 * LoanProcessing bean,HttpServletRequest request return type-- void Purpose
	 * --- To generate the installment schedule for the selected application
	 * 
	 * public boolean generateInstSchForReducePrincipal(LoanProcessing
	 * bean,HttpServletRequest request){
	 * 
	 * boolean result = false; Map checkMap=new HashMap(); try{
	 * 
	 * ArrayList<Object> tableList = new ArrayList<Object>(); int
	 * noOfInstallment = 0; double totalEmi = 0.0; double totalIntPaid = 0.0;
	 * double totalPrincipal = 0.0; String [] date
	 * =bean.getPaymentDate().split("-"); String [] date1
	 * =bean.getStartingDate().split("-"); String strDay1= date1[0]; String
	 * strYear= date[2]; String strMon1= date1[1];
	 * 
	 * double firstInt = generateFirstInterest(bean.getPaymentDate(),
	 * bean.getStartingDate(),
	 * Double.parseDouble(bean.getSanctionAmount()),bean.getInterestRate());
	 * 
	 * logger.info("intAmt inside generateFirstInterest==="+firstInt); String
	 * [][] installmentData = null; installmentData =
	 * calculateNoOfInstallmentForReduce(Double.parseDouble(bean.getSanctionAmount()),
	 * Double.parseDouble(bean.getEmiAmount()),
	 * Double.parseDouble(bean.getInterestRate()), bean.getStartingDate());
	 * 
	 * 
	 * if (installmentData != null) {
	 * 
	 * for (int i = 0; i < installmentData.length; i++) { LoanProcessing bean1 =
	 * new LoanProcessing();
	 * 
	 * bean1.setMonthYear(String.valueOf(installmentData[i][0]));
	 * bean1.setPrincipalAmt(Utility.twoDecimals(installmentData[i][1]));
	 * bean1.setInterestAmt(Utility.twoDecimals(installmentData[i][2]));
	 * bean1.setInstallmentAmt(Utility.twoDecimals(installmentData[i][3]));
	 * bean1.setBalancePrincipalAmt(Utility.twoDecimals(installmentData[i][4]));
	 * 
	 * 
	 * totalEmi += Double.parseDouble(installmentData[i][3]); totalIntPaid +=
	 * Double.parseDouble(installmentData[i][2]); totalPrincipal +=
	 * Double.parseDouble(installmentData[i][1]); tableList.add(bean1); try {
	 * 
	 * bean.setPaidFlag("false"); checkMap.put("" + (tableList.size() - 1),
	 * "N");
	 * 
	 * logger.info("checkmap===="+checkMap.size()); } catch (Exception e) { //
	 * TODO: handle exception logger.error(e); }
	 *  } //end of for loop
	 * 
	 * bean.setTotalInstallmenteAmt(Utility.twoDecimals(totalEmi));
	 * bean.setTotalInterestAmt(Utility.twoDecimals(totalIntPaid));
	 * 
	 * bean.setInstallmentList(tableList);
	 * request.setAttribute("data",checkMap); //end of if result = true;
	 * bean.setInstallmentFlag("true"); }else{ result = false;
	 * bean.setInstallmentFlag("false"); } } catch (Exception e) {
	 * e.printStackTrace(); logger.error(e); bean.setInstallmentFlag("false");
	 * return false; } return result; }
	 * 
	 */
	/**
	 * Method name-- generateInstallmentSchedule parameters--- LoanProcessing
	 * bean,HttpServletRequest request return type-- void Purpose --- To
	 * generate the installment schedule for the selected application
	 */
	public boolean installmentScheduleForEMI(LoanProcessing bean,
			HttpServletRequest request) {

		boolean result = false;
		String startingDate = bean.getStartingDate();
		try {

			logger.info("inside generateInstallmentSchedule");

			Map checkMap = new HashMap();
			bean.setInstallmentFlag("true");
			ArrayList<Object> tableList = new ArrayList<Object>();

			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			double firstInt = 0.0;

			double principalAmt = Double.parseDouble(bean.getSanctionAmount());
			Object[] loanApplCode = { bean.getHiddenCode() };
			Object[][] paidInstallData = getSqlModel().getSingleResult(
					getQuery(14), loanApplCode);

			Object[][] prepaymentSum = getSqlModel().getSingleResult(
					getQuery(15), loanApplCode);

			/*
			 * set paid installment if any
			 * 
			 */
			if (paidInstallData != null) {

				String monthYear = "";

				for (int i = 0; i < paidInstallData.length; i++) {
					LoanProcessing bean1 = new LoanProcessing();
					monthYear = String.valueOf(paidInstallData[i][7]) + "-"
							+ String.valueOf(paidInstallData[i][0]) + "-"
							+ String.valueOf(paidInstallData[i][1]);
					bean1.setMonthYear(monthYear);
					bean1.setMonthNo(String.valueOf(paidInstallData[i][9]));
					bean1
							.setPrincipalAmt(String
									.valueOf(paidInstallData[i][2]));
					bean1.setInterestAmt(String
							.valueOf(Double.parseDouble(String
									.valueOf(paidInstallData[i][3]))));
					bean1.setInstallmentAmt(String
							.valueOf(Double.parseDouble(String
									.valueOf(paidInstallData[i][4]))));
					bean1.setBalancePrincipalAmt(Utility.twoDecimals(String
							.valueOf(paidInstallData[i][5])));
					totalEmi += Double.parseDouble(String
							.valueOf(paidInstallData[i][4]));
					totalIntPaid += Double.parseDouble(String
							.valueOf(paidInstallData[i][3]));
					totalPrincipal += Double.parseDouble(String
							.valueOf(paidInstallData[i][2]));
					bean1.setPaidFlag("true");

					tableList.add(bean1);
					try {
						logger.info("Paid flag  in if   "
								+ String.valueOf(paidInstallData[i][6]));
						checkMap.put("" + i, "Y");

					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e);
					}
					principalAmt = principalAmt
							- Double.parseDouble("" + paidInstallData[i][2]);
					startingDate = String.valueOf(paidInstallData[i][8]);
				} // end of for loop

				bean.setNoOfPaidInstallments(String
						.valueOf(paidInstallData.length));
			}

			principalAmt -= Double.parseDouble(String
					.valueOf(prepaymentSum[0][0]));
			logger.info("intAmt inside generateFirstInterest===" + firstInt);
			String[][] installmentData = null;
			if (bean.getInterestType().equals("F")) {
				firstInt = generateFirstInterest(bean.getPaymentDate(), bean
						.getStartingDate(), Double.parseDouble(bean
						.getSanctionAmount()), bean.getInterestRate());
				installmentData = calculateNoOfInstallment(principalAmt, Double
						.parseDouble(bean.getEmiAmount()), bean
						.getInterestRate(), startingDate, firstInt);
			} else if (bean.getInterestType().equals("N")) {
				installmentData = calculateNoOfInstallment(principalAmt, Double
						.parseDouble(bean.getEmiAmount()), startingDate);
			} else {
				installmentData = calculateNoOfInstallmentForReduce(
						principalAmt, Double.parseDouble(bean.getEmiAmount()),
						Double.parseDouble(bean.getInterestRate()),
						startingDate);
			}

			if (installmentData != null) {
				for (int i = 0; i < installmentData.length; i++) {
					LoanProcessing bean1 = new LoanProcessing();

					bean1.setMonthYear(String.valueOf(installmentData[i][0]));
					
					String[] date3 = String.valueOf(installmentData[i][0]).split("-");
					String strMon3 = getMonthNumber(date3[1]);
					
					bean1.setMonthNo(strMon3);
					
					bean1.setPrincipalAmt(formatter.format(Double
							.parseDouble(installmentData[i][1])));
					bean1.setInterestAmt(formatter.format(Double
							.parseDouble(installmentData[i][2])));
					bean1.setInstallmentAmt(formatter.format(Double
							.parseDouble(installmentData[i][3])));
					bean1.setBalancePrincipalAmt(formatter.format(Double
							.parseDouble(installmentData[i][4])));

					totalEmi += Double.parseDouble(installmentData[i][3]);
					totalIntPaid += Double.parseDouble(installmentData[i][2]);
					totalPrincipal += Double.parseDouble(installmentData[i][1]);
					tableList.add(bean1);
					try {

						bean.setPaidFlag("false");
						checkMap.put("" + (tableList.size() - 1), "N");

						// logger.info("checkmap===="+checkMap.size());
					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e);
					}

				} // end of for loop

				if (!bean.getInterestType().equals("R")) {
					bean.setTotalPrincipalAmt(formatter.format(Double
							.parseDouble(bean.getSanctionAmount())));
					bean.setTotalInstallmenteAmt(formatter.format((Double
							.parseDouble(bean.getSanctionAmount()))
							+ totalIntPaid));
				} // end of if
				else
					bean.setTotalInstallmenteAmt(formatter.format(totalEmi));
				bean.setTotalInterestAmt(formatter.format(totalIntPaid));

				bean.setInstallmentList(tableList);
				request.setAttribute("data", checkMap);

				result = true;
				bean.setInstallmentFlag("true");
			} else {
				result = false;
				bean.setInstallmentFlag("false");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			bean.setInstallmentFlag("false");
			return false;
		}
		showPrePayemntDetails(bean);
		return result;
	}

	/**
	 * Method name-- showInstallmentSchedule parameters--- LoanProcessing
	 * bean,HttpServletRequest request return type-- void Purpose --- To
	 * retrieve all the installment details for the selected application and to
	 * display those details on the form
	 */
	public void showInstallmentSchedule(LoanProcessing bean,
			HttpServletRequest request) {
		try {
			logger.info("inside showInstallmentSchedule");
			String monthYear = "";
			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			bean.setInstallmentPaidFlag("false");
			Map checkMap = new HashMap();
			int noOfPaidInstallments = 0;
			ArrayList<Object> tableList = new ArrayList<Object>();
			Object[] loanApplCode = { bean.getHiddenCode() };
			Object[][] installData = getSqlModel().getSingleResult(getQuery(9),
					loanApplCode);
			for (int i = 0; i < installData.length; i++) {
				LoanProcessing bean1 = new LoanProcessing();
				monthYear = String.valueOf(installData[i][7]) + "-"
						+ String.valueOf(installData[i][0]) + "-"
						+ String.valueOf(installData[i][1]);
				bean1.setMonthYear(monthYear);
				bean1.setMonthNo(String.valueOf(installData[i][8]));
				bean1.setPrincipalAmt(String.valueOf(installData[i][2]));
				bean1.setInterestAmt(String.valueOf(Double.parseDouble(String
						.valueOf(installData[i][3]))));
				bean1.setInstallmentAmt(String.valueOf(Double
						.parseDouble(String.valueOf(installData[i][4]))));
				bean1.setBalancePrincipalAmt(Utility.twoDecimals(String
						.valueOf(installData[i][5])));
				totalEmi += Double.parseDouble(String
						.valueOf(installData[i][4]));
				totalIntPaid += Double.parseDouble(String
						.valueOf(installData[i][3]));
				totalPrincipal += Double.parseDouble(String
						.valueOf(installData[i][2]));
				// bean1.setCheckFlag(String.valueOf(installData[i][6]));

				if (String.valueOf(installData[i][6]).equals("Y"))
					bean1.setPaidFlag("true");
				else
					bean1.setPaidFlag("false");
				tableList.add(bean1);
				try {
					if (String.valueOf(installData[i][6]).equals("Y")) {
						logger.info("Paid flag  in if   "
								+ String.valueOf(installData[i][6]));
						checkMap.put("" + i, "Y");
						bean.setInstallmentPaidFlag("true");
						noOfPaidInstallments++;
					} // end of if
					else {
						logger.info("Paid flag  in else  "
								+ String.valueOf(installData[i][6]));
						checkMap.put("" + i, "N");
					} // end of else

					/*
					 * if(bean1.getCheckFlag().equals("Y")){
					 * bean1.setPaidFlag("true"); checkMap.put(""+i,"Y"); }else{
					 * bean1.setPaidFlag("false"); checkMap.put(""+i,"N"); }
					 */
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e);
				}
				// checkMap.put(""+i, "Y");
			} // end of for loop

			bean.setInstallmentFlag("true");
			if (!bean.getInterestType().equals("R")) {
				bean.setTotalPrincipalAmt(formatter.format(Double
						.parseDouble(bean.getSanctionAmount())));
				bean.setTotalInstallmenteAmt(formatter.format(Double
						.parseDouble(bean.getSanctionAmount())
						+ totalIntPaid));
			} // end of if
			else

				bean.setTotalInstallmenteAmt(formatter.format(totalEmi));
			bean.setTotalInterestAmt(formatter.format(totalIntPaid));

			bean.setInstallmentList(tableList);
			request.setAttribute("data", checkMap);
			bean.setNoOfPaidInstallments(String.valueOf(noOfPaidInstallments));
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
		}

	}

	/**
	 * Method name-- showPrePayemntDetails parameters--- LoanProcessing bean
	 * return type-- void Purpose --- To retrieve all the pre payment details
	 * for the selected application and to display those details on the form
	 */
	public void showPrePayemntDetails(LoanProcessing bean) {

		double totalPrepayment = 0.0;

		Object[][] prepaymentData = getSqlModel().getSingleResult(getQuery(13),
				new Object[] { bean.getLoanAppCode() });
		ArrayList<Object> tableList = new ArrayList<Object>();

		try {
			if (prepaymentData != null && prepaymentData.length != 0) {
				for (int i = 0; i < prepaymentData.length; i++) {
					LoanProcessing bean1 = new LoanProcessing();
					bean1.setPrePaymentDate(String
							.valueOf(prepaymentData[i][1]));
					bean1
							.setPrePaymentAmt(String
									.valueOf(prepaymentData[i][2]));
					// bean1.setPendingInstallments(String.valueOf(prepaymentData[i][3]));
					totalPrepayment += Double.parseDouble(String
							.valueOf(prepaymentData[i][2]));

					tableList.add(bean1);
				} // end of for loop
				bean.setPrePaymentFlag("true");
				bean.setPrePaymentList(tableList);
				bean.setTotalPrePaymenAmt(String.valueOf(totalPrepayment));
			} // end of if
			else
				bean.setPrePaymentFlag("false");
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
		}
	}

	/*
	 * function:calculateInstallment() parameter LoanProcessing bean object
	 * return double array containing 1) Principal amount 2) Interest Amount 3)
	 * Installment amount
	 */
	/*
	 * public double[][] calculateInstallment(LoanProcessing bean){ try {
	 * double[][] data = new double[Integer.parseInt(bean
	 * .getInstallmentNumber())][4];
	 * 
	 * double intRate = 0.0; if (!(bean.getInterestType().equals("R"))) { for
	 * (int i = 0; i < Integer.parseInt(bean .getInstallmentNumber()); i++) {
	 * data[i][0] = Double.parseDouble(bean.getSanctionAmount()) /
	 * Double.parseDouble(bean.getInstallmentNumber()); // / principal amount
	 * 
	 * if (!(bean.getInterestRate().equals("")) &&
	 * bean.getInterestType().equals("F")) intRate =
	 * Double.parseDouble(bean.getInterestRate());
	 * 
	 * data[i][1] = (data[i][0] * intRate) / 100; //Interest amount =(principal
	 * amount* intRate)/100 data[i][2] = data[i][0] + data[i][1]; // Installment
	 * amount= principal amount + Interest amount
	 * 
	 * data[i][3] = 0.0; } } else data = calcReduceInstallmentSch(bean); return
	 * data; } catch (Exception e) { e.printStackTrace(); return null; } }
	 */

	/**
	 * Method name-- calculateInstallment parameters--- double principalAmt, int
	 * noOfInstallment, String interestRate, String interestType, String
	 * startingDate return type-- String[][] Purpose --- To calculate the
	 * installment amount based on the given information
	 */
	public String[][] calculateInstallment(double principalAmt,
			int noOfInstallment, String interestRate, String interestType,
			String startingDate, double firstInt, boolean isInstallmentPaid) {
		try {
			String[][] data = new String[noOfInstallment][5];
			String[] date = startingDate.split("-");
			String strDay = date[0];
			String strMon = date[1];
			String strYear = date[2];
			double rate = 0.0;
			if (interestRate.equals(""))
				rate = 0.0;
			else
				rate = Double.parseDouble(interestRate);
			int year = Integer.parseInt(strYear);
			int month = Integer.parseInt(strMon);
			double intRate = 0.0;
			startingDate = getDate(startingDate);
			firstInt = firstInt / noOfInstallment;

			logger.info("firstInt ==" + firstInt);
			if (!(interestType.equals("R")) && !(interestType.equals("I"))) {
				for (int i = 0; i < noOfInstallment; i++) {
					if ((month + i) % 12 == 1 && !(i == 0)) {
						year += 1;
					} // end of if
					String tempDay = getDay(strDay, month + i, year);
					// strDay = getDay(strDay, month+i, year);
					data[i][0] = tempDay + "-" + getMonthString(month + i)
							+ "-" + year;
					data[i][1] = String.valueOf(principalAmt
							/ (noOfInstallment)); // / principal amount

					if (interestType.equals("F")) {
						intRate = rate;

						data[i][2] = String.valueOf(((Double
								.parseDouble(data[i][1]) * intRate)
								* noOfInstallment / 1200)
								+ firstInt); // Interest amount =(principal
												// amount* intRate)/100

						data[i][3] = String.valueOf(Double
								.parseDouble(data[i][1])
								+ Double.parseDouble(data[i][2])); // Installment
																	// amount=
																	// principal
																	// amount +
																	// Interest
																	// amount

					} else {
						data[i][2] = String.valueOf((Double
								.parseDouble(data[i][1]) * intRate) / 100); // Interest
																			// amount
																			// =(principal
																			// amount*
																			// intRate)/100
						data[i][3] = String.valueOf(Double
								.parseDouble(data[i][1])
								+ Double.parseDouble(data[i][2])); // Installment
																	// amount=
																	// principal
																	// amount +
																	// Interest
																	// amount
					}
					data[i][4] = "0.0";
				} // end of for loop
			} // end of if
			else if ((interestType.equals("R")))
				data = calcReduceInstallmentSch(principalAmt, noOfInstallment,
						rate, startingDate);
			else {
				if (isInstallmentPaid) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Calendar calInst = Calendar.getInstance();
					Date instDate = sdf.parse(startingDate);
					calInst.setTime(instDate);

					calInst.add(calInst.MONTH, -1);
					data = calcReduceInterestInstallmentSch(principalAmt,
							noOfInstallment, rate, sdf
									.format(calInst.getTime()), principalAmt
									/ noOfInstallment);
				} else {

					data = calcReduceInterestInstallmentSch(principalAmt
							- (principalAmt / noOfInstallment),
							noOfInstallment - 1, rate, startingDate,
							principalAmt / noOfInstallment);
				}
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public String[][] calculateInstallment(double principalAmt,int
	 * noOfInstallment,String interestRate,String interestType,String
	 * startingDate, double firstInt,double monthlyPrincAmount){ try {
	 * 
	 * logger.info("inside functionForcalculateInstallment");
	 * 
	 * String [][] data = new String[noOfInstallment][5];
	 * 
	 * double rate=0.0; if(interestRate.equals("")) rate=0.0; else
	 * rate=Double.parseDouble(interestRate);
	 * 
	 * data =
	 * calcReduceInterestInstallmentSch(principalAmt-monthlyPrincAmount,noOfInstallment,rate,startingDate,monthlyPrincAmount);
	 * return data; } catch (Exception e) { //e.printStackTrace();
	 * logger.error(e); e.printStackTrace(); return null; } }
	 */
	/**
	 * Method name-- calculateNoOfInstallment parameters--- double principalAmt,
	 * int emiAmount, String interestRate, String interestType, String
	 * startingDate return type-- String[][] Purpose --- To calculate the
	 * installment amount based on the given information
	 */
	public String[][] calculateNoOfInstallment(double principalAmt,
			double emiAmount, String interestRate, String startingDate,
			double firstInt) {
		try {
			logger.info("inside functionForcalculateInstallment=="
					+ principalAmt);

			String[] date = startingDate.split("-");
			String strDay = date[0];
			String strMon = date[1];
			String strYear = date[2];

			double monthlyInterestAmt = principalAmt
					* Double.parseDouble(interestRate) / 1200; // interest =
																// principalAmt
																// *
																// intRate/1200
			double monthlyPrincipalAmt = emiAmount - monthlyInterestAmt;
			double lastEmi = 0.0;
			double noOfInstallmentTemp = Math.floor(principalAmt
					/ monthlyPrincipalAmt);
			double noOfInstallment = 0;
			noOfInstallment = noOfInstallmentTemp;
			if (noOfInstallmentTemp * monthlyPrincipalAmt < principalAmt) {

				lastEmi = (principalAmt
						- (noOfInstallmentTemp * monthlyPrincipalAmt)
						+ firstInt + monthlyInterestAmt);
				// noOfInstallment += 1;

			} else {
				lastEmi = firstInt;
			}
			// noOfInstallment = noOfInstallment +1;
			if (lastEmi > emiAmount) {
				double noOfInstInner = Math.floor(lastEmi / emiAmount);
				noOfInstallment = noOfInstallment + noOfInstInner;
				if (noOfInstInner * emiAmount < lastEmi) {
					noOfInstallment = noOfInstallment + 1;

				}
				lastEmi = lastEmi - (noOfInstInner * emiAmount);
			} else {
				if (lastEmi != 0)
					noOfInstallment = noOfInstallment + 1;
			}
			logger.info("noOfInstallment===" + noOfInstallment);
			logger.info("lastEmi===" + lastEmi);

			int noOfInstallmentFinal = Integer.parseInt(String.valueOf(
					noOfInstallment).substring(0,
					String.valueOf(noOfInstallment).indexOf(".")));

			String[][] data = new String[noOfInstallmentFinal][5];
			int year = Integer.parseInt(strYear);
			int month = Integer.parseInt(strMon);
			for (int i = 0; i < data.length; i++) {
				if ((month + i) % 12 == 1 && !(i == 0)) {
					year += 1;
				} // end of if

				String tempDay = getDay(strDay, month + i, year);
				// strDay = getDay(strDay, month+i, year);
				data[i][0] = tempDay + "-" + getMonthString(month + i) + "-"
						+ year;
				if (i < noOfInstallmentTemp) {

					data[i][1] = String.valueOf(monthlyPrincipalAmt); // /
																		// principal
																		// amount
																		// =
																		// interest
																		// Amount
																		// -
																		// emiAmount

					data[i][2] = String.valueOf(monthlyInterestAmt); // Interest
																		// amount

					data[i][3] = String.valueOf(emiAmount); // Installment
															// amount= principal
															// amount + Interest
															// amount

				} else {
					if (i == noOfInstallmentTemp) {
						if (i == data.length - 1) { // last EMI
							data[i][1] = ""
									+ (principalAmt - (noOfInstallmentTemp * monthlyPrincipalAmt)); // /
																									// remaining
																									// principal
																									// amount

							data[i][2] = String.valueOf(lastEmi
									- Double.parseDouble(data[i][1])); // Interest
																		// amount

							data[i][3] = "" + lastEmi; // Installment amount=
														// principal amount +
														// Interest amount
						} else { // 3-1(600-183.27)
							data[i][1] = ""
									+ (principalAmt - (noOfInstallmentTemp * monthlyPrincipalAmt)); // /
																									// remaining
																									// principal
																									// amount

							data[i][2] = String.valueOf(emiAmount
									- Double.parseDouble(data[i][1])); // Interest
																		// amount

							data[i][3] = String.valueOf(emiAmount);

						}
					} else {
						if (i == data.length - 1) { // last emi principal zero

							data[i][1] = "0.0"; // / remaining principal amount

							data[i][2] = String.valueOf(lastEmi); // Interest
																	// amount

							data[i][3] = String.valueOf(lastEmi);
						} else { // interest == emiamount principal zero

							data[i][1] = "0.0"; // / remaining principal amount

							data[i][2] = String.valueOf(emiAmount); // Interest
																	// amount

							data[i][3] = String.valueOf(emiAmount);
						}
					}
				}

				data[i][4] = "0.0";
			}
			logger.info("datalength==" + data.length);
			/*
			 * for (int i = 0; i < data.length; i++) { for (int j = 0; j < 5;
			 * j++) { logger.info("data ["+i+"]["+j+"]===="+data[i][j]); }
			 *  }
			 */

			return data;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method name-- calculateNoOfInstallment parameters--- double principalAmt,
	 * int emiAmount, String interestRate, String interestType, String
	 * startingDate return type-- String[][] Purpose --- To calculate the
	 * installment amount based on the given information
	 */
	public String[][] calculateNoOfInstallment(double principalAmt,
			double emiAmount, String startingDate) {
		try {
			logger.info("inside functionForcalculateInstallment=="
					+ principalAmt);

			String[] date = startingDate.split("-");
			String strDay = date[0];
			String strMon = date[1];
			String strYear = date[2];

			double lastEmi = 0.0;
			double noOfInstallmentTemp = Math.floor(principalAmt / emiAmount);
			double noOfInstallment = 0;
			noOfInstallment = noOfInstallmentTemp;
			if (noOfInstallmentTemp * emiAmount < principalAmt) {

				lastEmi = (principalAmt - (noOfInstallmentTemp * emiAmount));
				noOfInstallment += 1;

			}
			// noOfInstallment = noOfInstallment +1;

			logger.info("noOfInstallment===" + noOfInstallment);
			logger.info("lastEmi===" + lastEmi);

			int noOfInstallmentFinal = Integer.parseInt(String.valueOf(
					noOfInstallment).substring(0,
					String.valueOf(noOfInstallment).indexOf(".")));

			String[][] data = new String[noOfInstallmentFinal][5];
			int year = Integer.parseInt(strYear);
			int month = Integer.parseInt(strMon);
			for (int i = 0; i < data.length; i++) {
				if ((month + i) % 12 == 1 && !(i == 0)) {
					year += 1;
				} // end of if
				String tempDay = getDay(strDay, month, year);
				// strDay = getDay(strDay, month+i, year);
				data[i][0] = tempDay + "-" + getMonthString(month + i) + "-"
						+ year;
				data[i][2] = "0.0";
				data[i][4] = "0.0";
				if (i < noOfInstallmentTemp) {
					data[i][1] = String.valueOf(emiAmount);
					data[i][3] = String.valueOf(emiAmount);
				} else {
					data[i][1] = String.valueOf(lastEmi);
					data[i][3] = String.valueOf(lastEmi);
				}
			}
			logger.info("datalength for No interest==" + data.length);

			return data;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
			e.printStackTrace();
			return null;
		}
	}

	public String[][] calcReduceInterestInstallmentSch(
			double principalDeduction, int noOfInstallment,
			double interestRate, String startingDate, double installRecovered) {
		logger.info("inside functionForcalcReduceInstallmentSch");
		try {
			String[][] data = new String[noOfInstallment][5];

			double balanceAmt = Double.parseDouble(formatter
					.format(principalDeduction));
			double noOfDaysInYear = 365.0;

			String[] date = startingDate.split("-");
			String strDay = date[0];
			String strMon = String.valueOf(Integer.parseInt(date[1]) + 1);
			String strYear = date[2];

			int year = Integer.parseInt(strYear);
			if (strMon.equals("13")) {
				year += 1;
			}

			for (int i = 0; i < noOfInstallment; i++) {

				if ((Integer.parseInt(strMon) + i) % 12 == 1 && !(i == 0)) {
					year += 1;
				} // end of if
				int month = (Integer.parseInt(strMon) + i) % 12;
				if (month == 0)
					month = 12;
				String tempDay = getDay(strDay, month, year);
				// strDay = getDay(strDay, month, year);
				data[i][0] = tempDay + "-"
						+ (getMonthString(month) + "-" + year);
				if (i == noOfInstallment - 1) {
					data[i][1] = String
							.valueOf((principalDeduction + installRecovered)
									- ((i + 1) * installRecovered));
				} else {
					data[i][1] = String.valueOf(installRecovered);
				}

				logger.info("month before query " + month);

				String queryDays = "SELECT TO_CHAR(LAST_DAY (TO_DATE ('"
						+ (month) + "-" + year
						+ "','MM-YYYY')),'DD') FROM DUAL";
				logger.info("queryDays===" + queryDays);
				Object daysInMonth[][] = getSqlModel().getSingleResult(
						queryDays);
				logger.info("year" + year);
				if (year % 400 == 0)
					noOfDaysInYear = 366.0;
				logger.info("no of days in year==" + noOfDaysInYear);
				logger.info("balanceAmt ==" + balanceAmt);
				logger.info("days in month =="
						+ String.valueOf(daysInMonth[0][0]));
				data[i][2] = String.valueOf(formatter
						.format(balanceAmt
								* (Double.parseDouble(String
										.valueOf(daysInMonth[0][0])))
								* interestRate / (noOfDaysInYear * 100))); // interest
																			// amt
				logger.info("interest amrt== ==" + data[i][2]);
				data[i][3] = String.valueOf(formatter.format(Double
						.parseDouble(data[i][2])
						+ Double.parseDouble(String.valueOf(data[i][1]))));
				logger.info("total pay== ==" + data[i][3]);
				data[i][4] = String.valueOf(formatter.format(balanceAmt));
				balanceAmt = balanceAmt
						- Double.parseDouble(String.valueOf(data[i][1]));
				logger.info("principal after deduction== ==" + balanceAmt);
			}

			return data;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
			return null;
		}
	}

	/**
	 * Method name-- calcReduceInstallmentSch parameters--- double sanctionAmt,
	 * int noOfInstallment, double interestRate, String interestType, String
	 * startingDate return type-- String[][] Purpose --- To calculate the
	 * installment amount for reduced interest type based on the given
	 * information
	 */
	public String[][] calcReduceInstallmentSch(double sanctionAmt,
			int noOfInstallment, double interestRate, String startingDate) {
		try {
			String[][] data = new String[noOfInstallment][5];
			double emi;
			double balanceAmt = Double.parseDouble(formatter
					.format(sanctionAmt));

			double intRate = interestRate / 1200; // divided by 12*100 to
													// convert into monthly rate
			double noOfInst = (noOfInstallment);
			String[] date = startingDate.split("-");
			String strDay = date[0];
			String strMon = date[1];
			String strYear = date[2];
			int year = Integer.parseInt(strYear);
			//logger.info("balanceAmt==" + balanceAmt);
			//logger.info("intRate==" + intRate);
			//logger.info("noOfInst==" + noOfInst);
			emi = Double.parseDouble(formatter
					.format(((balanceAmt * intRate) * (Math.pow(
							(1.0 + intRate), noOfInst)))
							/ ((Math.pow((1.0 + intRate), noOfInst)) - 1.0)));

			double intAmt;
			double princReduction;
			for (int i = 0; i < noOfInstallment; i++) {
				intAmt = Double.parseDouble(formatter.format(balanceAmt
						* intRate));
				princReduction = emi - intAmt;

				balanceAmt = balanceAmt - princReduction;
				if ((Integer.parseInt(strMon) + i) % 12 == 1 && !(i == 0)) {
					year += 1;
				} // end of if
				int month = (Integer.parseInt(strMon) + i) % 12;
				if (month == 0)
					month = 12;
				String tempDay = getDay(strDay, month, year);
				// strDay = getDay(strDay, month, year);
				data[i][0] = tempDay + "-"
						+ (getMonthString(month) + "-" + year);
				data[i][1] = String.valueOf(formatter.format(princReduction)); // principal
																				// to
																				// be
																				// reduced
																				// from
																				// loan
																				// amount
				data[i][2] = String.valueOf(formatter.format(intAmt)); // Interest
																		// on
																		// balance
																		// principal
																		// amount
				data[i][3] = String.valueOf(formatter.format(emi)); // EMI for
																	// every
																	// month
				data[i][4] = String.valueOf(formatter.format(balanceAmt)); // balance
																			// principal
																			// amount

				if (i == (noOfInstallment) - 1) {
					logger.info("converted into number format for last param=="
							+ Math.abs(Double.parseDouble(formatter
									.format(balanceAmt))));
					data[i][4] = String.valueOf(Math.abs(Double
							.parseDouble(formatter.format(balanceAmt))));
				} // end of if
				//logger.info("princReduction==" + data[i][1]);
				//logger.info("interest on balance principal amount=="
				//		+ data[i][2]);
				//logger.info("EMI for every month==" + data[i][3]);
				//logger.info("balance principal amount==" + data[i][4]);

			} // end of for loop

			// logger.info("emi in the reduce princ====" + emi);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
	}

	/**
	 * Method name-- calcReduceInstallmentSch parameters--- double sanctionAmt,
	 * int noOfInstallment, double interestRate, String interestType, String
	 * startingDate return type-- String[][] Purpose --- To calculate the
	 * installment amount for reduced interest type based on the given
	 * information
	 */
	public String[][] calculateNoOfInstallmentForReduce(double sanctionAmt,
			double emiAmount, double interestRate, String startingDate) {
		logger.info("inside functionForcalcReduceInstallmentSch");
		try {

			double emi = emiAmount;
			double balanceAmt = sanctionAmt;

			double intRate = interestRate / 1200; // divided by 12*100 to
													// convert into monthly rate
			double noOfInstallmentTemp = Math.ceil((Math.log(emi) - Math
					.log(emi - (balanceAmt * intRate)))
					/ (Math.log(1 + intRate)));

			int noOfInstallment = Integer.parseInt(String.valueOf(
					noOfInstallmentTemp).substring(0,
					String.valueOf(noOfInstallmentTemp).indexOf(".")));
			String[] date = startingDate.split("-");
			String strDay = date[0];
			String strMon = date[1];
			String strYear = date[2];

			double noOfInst = (noOfInstallment);

			String[][] data = new String[noOfInstallment][5];
			int year = Integer.parseInt(strYear);
			logger.info("balanceAmt==" + balanceAmt);
			logger.info("intRate==" + intRate);
			logger.info("noOfInst==" + noOfInst);
			/*
			 * emi = ((balanceAmt * intRate) * (Math .pow((1.0 + intRate),
			 * noOfInst))) / ((Math.pow((1.0 + intRate), noOfInst)) - 1.0);
			 */

			double intAmt;
			double princReduction;
			for (int i = 0; i < noOfInstallment; i++) {
				intAmt = (balanceAmt * intRate);
				if (i == noOfInstallment - 1) {
					princReduction = balanceAmt;
				} else {
					princReduction = emi - intAmt;
				}
				balanceAmt = balanceAmt - princReduction;
				if ((Integer.parseInt(strMon) + i) % 12 == 1 && !(i == 0)) {
					year += 1;
				} // end of if
				int month = (Integer.parseInt(strMon) + i) % 12;
				if (month == 0)
					month = 12;
				String tempDay = getDay(strDay, month, year);
				// strDay = getDay(strDay, month, year);
				data[i][0] = tempDay + "-"
						+ (getMonthString(month) + "-" + year);

				if (i == (noOfInstallment) - 1) {
					data[i][1] = String.valueOf(princReduction); // principal
																	// to be
																	// reduced
																	// from loan
																	// amount
					data[i][2] = String.valueOf(Math.abs(Double
							.parseDouble(formatter.format(intAmt)))); // Interest
																		// on
																		// balance
																		// principal
																		// amount
					data[i][3] = String.valueOf(Double.parseDouble(String
							.valueOf(data[i][1]))
							+ Double.parseDouble(String.valueOf(data[i][2]))); // EMI
																				// for
																				// every
																				// month
					logger.info("converted into number format for last param=="
							+ Math.abs(Double.parseDouble(formatter
									.format(balanceAmt))));
					data[i][4] = String.valueOf(Math.abs(Double
							.parseDouble(formatter.format(balanceAmt))));
				} // end of if
				else {
					data[i][1] = formatter.format(princReduction); // principal
																	// to be
																	// reduced
																	// from loan
																	// amount
					data[i][2] = String.valueOf(formatter.format(intAmt)); // Interest
																			// on
																			// balance
																			// principal
																			// amount
					data[i][3] = String.valueOf(formatter.format(emi)); // EMI
																		// for
																		// every
																		// month
					data[i][4] = String.valueOf(formatter.format(balanceAmt)); // balance
																				// principal
																				// amount
				}
				logger.info("princReduction==" + data[i][1]);
				logger.info("interest on balance principal amount=="
						+ data[i][2]);
				logger.info("EMI for every month==" + data[i][3]);
				logger.info("balance principal amount==" + data[i][4]);

			} // end of for loop

			// logger.info("emi in the reduce princ====" + emi);
			return data;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
			return null;
		}
	}

	/*
	 * public double[][] calcReduceInstallmentSch(LoanProcessing bean){
	 * logger.info("inside reduce princi"); try { double[][] data = new
	 * double[Integer.parseInt(bean .getInstallmentNumber())][4]; double emi;
	 * double balanceAmt = Double.parseDouble(bean.getSanctionAmount()); double
	 * intRate = Double.parseDouble(bean.getInterestRate()) / 1200; // divided
	 * by 12*100 to convert into monthly rate double noOfInst =
	 * Double.parseDouble(bean.getInstallmentNumber()); emi = ((balanceAmt *
	 * intRate) * (Math .pow((1.0 + intRate), noOfInst))) / ((Math.pow((1.0 +
	 * intRate), noOfInst)) - 1.0);
	 * 
	 * double intAmt; double princReduction; for (int i = 0; i <
	 * Integer.parseInt(bean.getInstallmentNumber()); i++) { intAmt =
	 * (balanceAmt * intRate); princReduction = emi - intAmt;
	 * 
	 * balanceAmt = balanceAmt - princReduction;
	 * 
	 * data[i][0] = princReduction; // principal to be reduced from loan amount
	 * data[i][1] = intAmt; // Interest on balance principal amount data[i][2] =
	 * emi; // EMI for every month data[i][3] = balanceAmt; // balance principal
	 * amount
	 * 
	 * if(i==Integer.parseInt(bean.getInstallmentNumber())-1){
	 * logger.info("converted into number format for last
	 * param=="+Math.abs(Double.parseDouble(formatter.format(balanceAmt))));
	 * data[i][3] = Math.abs(Double.parseDouble(formatter.format(balanceAmt))); }
	 * logger.info("princReduction==" + data[i][0]); logger.info("nterest on
	 * balance principal amount==" + data[i][1]); logger.info("EMI for every
	 * month==" + data[i][2]); logger.info("balance principal amount==" +
	 * data[i][3]);
	 * 
	 *  }
	 * 
	 * //logger.info("emi in the reduce princ====" + emi); return data; } catch
	 * (Exception e) { //e.printStackTrace(); logger.error(e); return null; } }
	 */

	/**
	 * Method name-- showProccessDetails parameters--- LoanProcessing bean
	 * return type-- void Purpose --- To retrieve the processing details for the
	 * selected application and set these details in the respective form fields
	 */
	public void showProccessDetails(LoanProcessing bean) {
		try {
			Object[] loanCode = new Object[1];
			loanCode[0] = bean.getHiddenCode();
			Object data[][] = getSqlModel().getSingleResult(getQuery(4),
					loanCode);
			bean.setHiddenCode(String.valueOf(data[0][0]));
			bean.setEmpToken(checkNull(String.valueOf(data[0][1])));
			bean.setEmpName(checkNull(String.valueOf(data[0][2])));
			bean.setBranchName(checkNull(String.valueOf(data[0][3])));
			bean.setDeptName(checkNull(String.valueOf(data[0][4])));
			bean.setLoanType(checkNull(String.valueOf(data[0][5])));
			bean.setAppdate(checkNull(String.valueOf(data[0][6])));
			bean.setEmpCode(checkNull(String.valueOf(data[0][7])));

			bean.setSanctionDate(checkNull(String.valueOf(data[0][8])));
			bean.setSanctionAmount(checkNull(String.valueOf(data[0][9])));
			bean.setPaymentDate(checkNull(String.valueOf(data[0][10])));
			bean.setPaymentMode(checkNull(String.valueOf(data[0][11])));
			bean.setBankName(checkNull(String.valueOf(data[0][12])));
			bean.setChequeNumber(checkNull(String.valueOf(data[0][13])));
			bean.setInterestType(checkNull(String.valueOf(data[0][14])));
			bean.setInterestRate(checkNull(String.valueOf(data[0][15])));

			// bean.setStartingMonth(checkNull(String.valueOf(data[0][16])));
			// bean.setStartingYear(checkNull(String.valueOf(data[0][17])));
			bean.setStartingDate(checkNull(String.valueOf(data[0][16])));
			if (!bean.getInterestType().equals("I")) {
				bean.setInstallmentNumberFlat(checkNull(String
						.valueOf(data[0][17])));
				bean.setEmiAmount(checkNull(String.valueOf(data[0][24])));
			} else {
				bean
						.setInstallmentNumber(checkNull(String
								.valueOf(data[0][17])));
				bean.setMonthlyPrincAmount(checkNull(String
						.valueOf(data[0][25])));
			}
			/*if (!bean.getEmiAmount().equals("")) {
				bean.setHiddenCalType("E");
			} else if (!bean.getMonthlyPrincAmount().equals("")) {
				bean.setHiddenCalType("P");
			} else {
				bean.setHiddenCalType("I");
			}*/
			// bean.setStartingDate(checkNull(String.valueOf(data[0][17])));
			bean.setSecurityDetails(checkNull(String.valueOf(data[0][18])));
			bean.setSecurityValue(checkNull(String.valueOf(data[0][19])));
			bean.setSubmissionDate(checkNull(String.valueOf(data[0][20])));
			bean.setSancPersonCode(checkNull(String.valueOf(data[0][21])));
			bean.setSancPersonToken(checkNull(String.valueOf(data[0][22])));
			bean.setSancPersonName(checkNull(String.valueOf(data[0][23])));

			if (String.valueOf(data[0][26]).equals("E")) {
				bean.setExpectedEmi("true");
				bean.setHiddenChechfrmId("E");
				
				bean.setEmiAmt("true");
				bean.setHiddenCalfrmId("E");
				
				
			}

			if (String.valueOf(data[0][26]).equals("T")) {
				bean.setTenure("true");
				bean.setHiddenChechfrmId("T");
				
				bean.setNoOfInstallment("true");
				bean.setHiddenCalfrmId("T");
				
			}
			
			if (String.valueOf(data[0][26]).equals("P")) {
				
				bean.setPrnAmt("true");
				bean.setHiddenCalfrmId("P");
				
			}
			
			
			grossSalary(bean);
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			
		}
	}

	/**
	 * Method name-- save parameters--- LoanProcessing bean, String[] monthYear,
	 * String[] princAmt, String[] interAmt, String[] installAmt, String []
	 * balancePrinc, String [] isPaid return type-- boolean Purpose --- To save
	 * the processing details for the selected application
	 * @param apprAmount 
	 * @param comments 
	 * @param empCode 
	 * @param loanCode2 
	 * @param status 
	 * @param monthNo 
	 */
	public boolean save(LoanProcessing bean, String[] monthYear,
			String[] princAmt, String[] interAmt, String[] installAmt,
			String[] balancePrinc, String[] isPaid, String status, String applicationId, String empCode, String comments, String apprAmount, String[] monthNo) {

		boolean result = false;
		String month = "";
		String year = "";
		String day = "";
		String[] split;
		Object[][] loanCode = new Object[1][1];
		loanCode[0][0] = applicationId;
		try {

			Object addObj[][] = new Object[1][18];
			addObj[0][0] = applicationId;
			// addObj [0][1] = bean.getEmpCode();
			addObj[0][1] = bean.getSanctionAmount();
			addObj[0][2] = bean.getSanctionDate();
			addObj[0][3] = bean.getPaymentDate();
			addObj[0][4] = bean.getPaymentMode();
			addObj[0][5] = bean.getBankName();
			addObj[0][6] = bean.getChequeNumber();

			// addObj[0][9] = bean.getStartingMonth();
			// addObj[0][10] = bean.getStartingYear();
			if (bean.getLoanRefundableFlag().equals("true")) {
				addObj[0][7] = bean.getInterestRate();
				addObj[0][8] = bean.getInterestType();
				addObj[0][9] = bean.getStartingDate();

				addObj[0][10] = "";
				addObj[0][15] = "";
				addObj[0][16] = "";

				if (!bean.getInterestType().equals("I")) {
					if (bean.getHiddenCalfrmId().equals("E")) {
						addObj[0][15] = checkNull(bean.getEmiAmount());
					} else {
						addObj[0][10] = bean.getInstallmentNumberFlat();
					}

				} else {
					if (bean.getHiddenCalfrmId().equals("P")) {
						addObj[0][16] = checkNull(bean.getMonthlyPrincAmount());
					} else {
						addObj[0][10] = bean.getInstallmentNumberFlat();
					}

				}
			} else {
				addObj[0][7] = "";
				addObj[0][8] = "";
				addObj[0][9] = "";
				addObj[0][10] = "";
				addObj[0][15] = "";

			}
			
			System.out.println("TENURE::::::::::"+addObj[0][10]);
System.out.println("EMI AMT::::::::::"+addObj[0][15]);
System.out.println("PRINCIPAL::::::::::"+addObj[0][16]);

			addObj[0][11] = bean.getSecurityDetails();
			addObj[0][12] = bean.getSecurityValue();
			addObj[0][13] = bean.getSubmissionDate();
			addObj[0][14] = bean.getSancPersonCode();
			addObj[0][17] = checkNull(bean.getAccountNumber());
			
			result = getSqlModel().singleExecute(getQuery(2), addObj);

			
			
			try {
				if (result && !(monthNo.equals(null))) {
					if (getSqlModel().singleExecute(getQuery(11), loanCode)) {
						for (int i = 0; i < monthNo.length; i++) {
							split = monthYear[i].split("-");
							day = split[0];
							month = getMonthNumber(split[1]);
							year = split[2];
							Object[][] installData = new Object[1][10];
							installData[0][0] = applicationId;
							installData[0][1] = monthNo[i];
							installData[0][2] = year;
							installData[0][3] = princAmt[i];
							installData[0][4] = interAmt[i];
							installData[0][5] = installAmt[i];
							installData[0][6] = bean.getEmpCode();
							installData[0][7] = balancePrinc[i];
							installData[0][8] = day;
							installData[0][9] = isPaid[i];
							result = getSqlModel().singleExecute(getQuery(10),
									installData);
							
							
						} // end of for loop
					} // end of if
				} // end of if
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				String updateApproverCommentsSql = " UPDATE HRMS_LOAN_APPLICATION SET LOAN_APPL_STATUS = 'A', "
				+ " LOAN_HR_AMT ='"+bean.getSanctionAmount()+"' , LOAN_EMI_TYPE='"+bean.getHiddenCalfrmId()+"' WHERE LOAN_APPL_CODE = " + bean.getHiddenCode();
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(bean,applicationId, empCode, comments, status,apprAmount);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			 e.printStackTrace();
			logger.error(e);
			return result;
		}
		return result;
	}

	private void insertApproverComments(LoanProcessing bean, String applicationId, String empCode,
			String comments, String status, String apprAmount) {
		String insertSql = " INSERT INTO HRMS_LOAN_PATH (LOAN_PATH_ID, LOAN_APPL_CODE, LOAN_APPROVER_CODE, LOAN_COMMENTS, LOAN_APPL_STATUS, LOAN_APPR_AMT,LOAN_APPLIED_BY,LOAN_APPROVED_DATE) "
			+ " VALUES ((SELECT NVL(MAX(LOAN_PATH_ID), 0) + 1 FROM HRMS_LOAN_PATH), ?, ?,?,?,?,?, SYSDATE) ";
String amt = "";
		Object[][] insertObj = new Object[1][6];
		insertObj[0][0] = applicationId;
		insertObj[0][1] = bean.getUserEmpId();
		insertObj[0][2] = comments;
		insertObj[0][3] = status;
		
		if(amt.equals("")){
			insertObj[0][4] = apprAmount;
		}else{
			insertObj[0][4] = apprAmount;
		}
		insertObj[0][5] = empCode;
		
		getSqlModel().singleExecute(insertSql, insertObj);
		
		/*for (int k = 0; k < insertObj.length; k++) {
			for (int j = 0; j < insertObj[k].length; j++) {
				logger.info("installData[" + k + "][" + j + "]  "
						+ insertObj[k][j]);
			}
		}*/
	}

	/**
	 * Method name-- update parameters--- LoanProcessing bean, String[]
	 * monthYear, String[] princAmt, String[] interAmt, String[] installAmt,
	 * String [] balancePrinc, String [] isPaid return type-- boolean Purpose
	 * --- To update the processing details for the selected application
	 * @param apprAmount 
	 * @param comments 
	 * @param empCode 
	 * @param applicationId 
	 * @param status 
	 * @param monthNo 
	 */
	public boolean update(LoanProcessing bean, String[] monthYear,
			String[] princAmt, String[] interAmt, String[] installAmt,
			String[] balancePrinc, String[] isPaid, String status, String applicationId, String empCode, String comments, String apprAmount, String[] monthNo) {
		
		boolean result = false;
		String month = "";
		String year = "";
		String day = "";
		String[] split;
		Object[][] loanCode = new Object[1][1];
		loanCode[0][0] = applicationId;
		
		try {
			Object addObj[][] = new Object[1][18];

			// addObj [0][1] = bean.getEmpCode();
			addObj[0][0] = bean.getSanctionAmount();
			addObj[0][1] = bean.getSanctionDate();
			addObj[0][2] = bean.getPaymentDate();
			addObj[0][3] = bean.getPaymentMode();
			addObj[0][4] = bean.getBankName();
			addObj[0][5] = bean.getChequeNumber();
			addObj[0][6] = bean.getInterestRate();
			addObj[0][7] = bean.getInterestType();
			addObj[0][8] = bean.getStartingDate();
			// addObj [0][8] = bean.getStartingMonth();
			// addObj [0][9] = bean.getStartingYear();
			if (bean.getLoanRefundableFlag().equals("true")) {
				addObj[0][6] = bean.getInterestRate();
				addObj[0][7] = bean.getInterestType();
				addObj[0][8] = bean.getStartingDate();

				addObj[0][9] = "";
				addObj[0][14] = "";
				addObj[0][15] = "";

				if (!bean.getInterestType().equals("I")) {
					if (bean.getHiddenCalfrmId().equals("E")) {
						addObj[0][14] = checkNull(bean.getEmiAmount());
					} else {
						addObj[0][9] = bean.getInstallmentNumberFlat();
					}

				} else {
					if (bean.getHiddenCalfrmId().equals("P")) {
						addObj[0][15] = checkNull(bean.getMonthlyPrincAmount());
					} else {
						addObj[0][9] = bean.getInstallmentNumberFlat();
					}

				}

			} else {
				addObj[0][6] = "";
				addObj[0][7] = "";
				addObj[0][8] = "";
				addObj[0][9] = "";
				addObj[0][14] = "";
				}

			
System.out.println("addObj[0][9]===="+addObj[0][9]);
			addObj[0][10] = bean.getSecurityDetails();
			addObj[0][11] = bean.getSecurityValue();
			addObj[0][12] = bean.getSubmissionDate();
			addObj[0][13] = bean.getSancPersonCode();
			addObj[0][16] = checkNull(bean.getAccountNumber());
			
			addObj[0][17] = bean.getHiddenCode();
			System.out.println("addObj[0][17]===="+addObj[0][17]);
			result = getSqlModel().singleExecute(getQuery(3), addObj);
			
			try {
				if (result && !(monthNo.equals(null))) {
					if (getSqlModel().singleExecute(getQuery(11), loanCode)) {

						for (int i = 0; i < monthNo.length; i++) {
							split = monthYear[i].split("-");
							
							day = split[0];
							month = getMonthNumber(split[1]);
							
							year = split[2];
							Object[][] installData = new Object[1][10];
							installData[0][0] = applicationId;
							installData[0][1] = monthNo[i];
							installData[0][2] = year;
							installData[0][3] = princAmt[i];
							installData[0][4] = interAmt[i];
							installData[0][5] = installAmt[i];
							installData[0][6] = bean.getEmpCode();
							installData[0][7] = balancePrinc[i];
							installData[0][8] = day;
							installData[0][9] = isPaid[i];
							result = getSqlModel().singleExecute(getQuery(10),
									installData);
							
							/*for (int k = 0; k < installData.length; k++) {
								for (int j = 0; j < installData[k].length; j++) {
									logger.info("installData[" + i + "][" + j + "]  "
											+ installData[k][j]);
								}
							}*/
							
							
						} // end of for loop
					} // end of if
				} // end of if
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String updateApproverCommentsSql = " UPDATE HRMS_LOAN_APPLICATION SET LOAN_APPL_STATUS = 'A', "
				+ " LOAN_HR_AMT ='"+bean.getSanctionAmount()+"' , LOAN_EMI_TYPE='"+bean.getHiddenCalfrmId()+"'  WHERE LOAN_APPL_CODE = " + bean.getHiddenCode();
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(bean,applicationId, empCode, comments, status,apprAmount);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
			return result;
		}
		return result;
	}

	/**
	 * Method name-- showGuarantor parameters--- LoanProcessing bean return
	 * type-- void Purpose --- To retrieve the guarantor details and set these
	 * details in respective form fields
	 */
	public void showGuarantor(LoanProcessing bean) {
		Object data[][] = null;
		Object loanAppCode[] = new Object[1];
		loanAppCode[0] = bean.getLoanAppCode();
		data = getSqlModel().getSingleResult(getQuery(6), loanAppCode);
		ArrayList<Object> tableList = new ArrayList<Object>();
		LoanProcessing bean1 = new LoanProcessing();
		LoanProcessing bean2 = new LoanProcessing();
		bean1.setGuarId(String.valueOf(data[0][0]));
		bean1.setGuarName(String.valueOf(data[0][1]));
		bean1.setGuarDept(String.valueOf(data[0][2]));
		bean1.setGuarBranch(String.valueOf(data[0][3]));
		bean1.setGuarDesg(String.valueOf(data[0][4]));
		tableList.add(bean1);
		data = getSqlModel().getSingleResult(getQuery(7), loanAppCode);
		bean2.setGuarId(String.valueOf(data[0][0]));
		bean2.setGuarName(String.valueOf(data[0][1]));
		bean2.setGuarDept(String.valueOf(data[0][2]));
		bean2.setGuarBranch(String.valueOf(data[0][3]));
		bean2.setGuarDesg(String.valueOf(data[0][4]));
		tableList.add(bean2);

		bean.setGurantorList(tableList);
	}

	/**
	 * Method name-- delete parameters--- LoanProcessing bean return type--
	 * boolean Purpose --- To delete all the details of the selected record
	 */
	public boolean delete(LoanProcessing bean) {
		boolean result = false;
		Object loanAppCode[][] = new Object[1][1];
		loanAppCode[0][0] = bean.getLoanAppCode();
		result = getSqlModel().singleExecute(getQuery(5), loanAppCode);
		if (result) {
			result = getSqlModel().singleExecute(getQuery(11), loanAppCode);
		} // end of if
		return result;

	}

	public void setPfDetails(LoanProcessing bean) {

		String query = "SELECT PFT_LOAN_CODE,PFT_CODE,PFT_LOAN_MINLIMIT, PFT_LOAN_MAXLIMIT FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
				+ " FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE)";

		Object[][] pfObj = getSqlModel().getSingleResult(query);
		try {
			bean.setPfLoanCode(String.valueOf(pfObj[0][0]));
			bean.setMinSanctionLimit(String.valueOf(pfObj[0][2]));
			bean.setMaxSanctionLimit(String.valueOf(pfObj[0][3]));
		} catch (Exception e) {

		}
	}

	public String calPfBalance(LoanProcessing bean) {
		String pfBalance = "";
		String frmYear = "";
		double openingBalance = 0.0;
		double pfSubAmt = 0.0;
		double pfRefundAmt = 0.0;
		double pfLoanAmt = 0.0;
		int noOfMonth = 0;
		int year = 0;

		String date[] = bean.getAppdate().split("-");
		logger.info("bean.getAppdate()===" + bean.getAppdate());
		logger.info("date[0]===" + date[0]);
		logger.info("date[1]===" + date[1]);
		logger.info("date[2]===" + date[2]);

		if (Integer.parseInt(date[1]) > 3) {
			frmYear = String.valueOf(Integer.parseInt(date[2]) - 1);
			noOfMonth = Integer.parseInt(date[1]) - 3;
			year = Integer.parseInt(date[2]);
		} else {
			frmYear = String.valueOf(Integer.parseInt(date[2]) - 2);
			noOfMonth = Integer.parseInt(date[1]) + 9;
			year = Integer.parseInt(date[2]) - 1;
		}
		String opnBalanceQuery = "SELECT NVL(PF_CLOSING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="
				+ bean.getEmpCode() + " AND " + " PF_FROM_YEAR =" + frmYear;

		Object[][] opnBalanceObj = getSqlModel().getSingleResult(
				opnBalanceQuery);
		logger.info("frmYear===" + frmYear);
		logger.info("year===" + year);
		try {
			openingBalance = Double.parseDouble(String
					.valueOf(opnBalanceObj[0][0]));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String pfSubQuery = "";
		String pfRefundQuery = "";
		String pfLoanQuery = "";
		int month = 0;
		for (int i = 0; i < noOfMonth; i++) {
			month = (4 + i) % 12;
			if (month == 0)
				month = 12;
			if (month == 1) {
				year += 1;
			}
			logger.info("year in for loop===" + year);
			pfSubQuery = "SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_DEBITS_"
					+ year
					+ " INNER JOIN HRMS_PFTRUST_CONF ON(PFT_DEBIT_CODE=SAL_DEBIT_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ month + " AND LEDGER_YEAR=" + year + " )"
					+ " WHERE EMP_ID =" + bean.getEmpCode();

			Object[][] pfSubObj = getSqlModel().getSingleResult(pfSubQuery);

			try {
				pfSubAmt += Double.parseDouble(String.valueOf(pfSubObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
			pfRefundQuery = "SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_DEBITS_"
					+ year
					+ " INNER JOIN HRMS_LOAN_MASTER ON (LOAN_DEBIT_CODE=SAL_DEBIT_CODE)"
					+ " INNER JOIN HRMS_PFTRUST_CONF ON(PFT_LOAN_CODE=LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ month
					+ " AND LEDGER_YEAR="
					+ year
					+ " )"
					+ " WHERE EMP_ID =" + bean.getEmpCode();

			Object[][] pfRefundObj = getSqlModel().getSingleResult(
					pfRefundQuery);

			try {
				pfRefundAmt += Double.parseDouble(String
						.valueOf(pfRefundObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
			String monthString = "" + month;
			if (month < 10) {
				monthString = "0" + month;
			}
			pfLoanQuery = "SELECT SUM(LOAN_SANCTION_AMOUNT) FROM HRMS_LOAN_PROCESS "
					+ " INNER JOIN HRMS_LOAN_APPLICATION ON(HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_PROCESS.LOAN_APPL_CODE)"
					+ " INNER JOIN HRMS_PFTRUST_CONF ON(PFT_LOAN_CODE=LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
					+ " WHERE TO_CHAR(LOAN_PAYMENT_DATE,'MM-YYYY') ='"
					+ monthString
					+ "-"
					+ year
					+ "' AND LOAN_EMP_ID ="
					+ bean.getEmpCode()
					+ " AND HRMS_LOAN_PROCESS.LOAN_APPL_CODE!="
					+ bean.getLoanAppCode();

			Object[][] pfLoanObj = getSqlModel().getSingleResult(pfLoanQuery);

			try {
				pfLoanAmt += Double
						.parseDouble(String.valueOf(pfLoanObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		pfBalance = formatter.format(openingBalance + pfSubAmt + pfRefundAmt
				- pfLoanAmt);

		double minSanctAmt = 0.0;
		double maxSanctAmt = 0.0;
		minSanctAmt = Double.parseDouble(bean.getMinSanctionLimit())
				* Double.parseDouble(pfBalance) / 100;
		maxSanctAmt = Double.parseDouble(bean.getMaxSanctionLimit())
				* Double.parseDouble(pfBalance) / 100;

		bean.setMinLoanSanctAmt(formatter.format(minSanctAmt));
		bean.setMaxLoanSanctAmt(formatter.format(maxSanctAmt));

		return pfBalance;
	}

	public void checkPFTFlag(LoanProcessing bean) {
		if (bean.getPfLoanCode().equals(bean.getLoanTypeCode())) {
			bean.setPfTrustFlag("true");
		} else {
			bean.setPfTrustFlag("false");
		}
	}

	/**
	 * Method name-- getReport parameters--- LoanProcessing bean,
	 * HttpServletRequest response return type-- void Purpose --- To generate
	 * report for the selected application
	 */
	public void getReport(LoanProcessing bean, HttpServletRequest request,
			HttpServletResponse response) {
		String s = "\nLOAN PROCESSING REPORT\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);

		Object[][] heading = new Object[1][1];
		int[] cells = { 25 };
		int[] align = { 0 };

		Object[][] loanData = new Object[11][4];
		Object[][] employee = new Object[1][2];
		Object[][] securityDetails = new Object[1][2];

		employee[0][0] = "Employee Name : ";
		employee[0][1] = ": " + checkNull(bean.getEmpName());

		Object[][] date = getSqlModel().getSingleResult(
				"SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
		Object[][] result = getSqlModel().getSingleResult(getQuery(8),
				new Object[] { bean.getLoanAppCode() });
		Object[][] prepaymentResult = getSqlModel().getSingleResult(
				getQuery(13), new Object[] { bean.getLoanAppCode() });

		loanData[0][0] = "Department ";
		loanData[0][1] = ": " + checkNull(bean.getDeptName());
		loanData[0][2] = "Branch ";
		loanData[0][3] = ": " + checkNull(bean.getBranchName());

		loanData[1][0] = "Gross Salary ";
		loanData[1][1] = ": " + checkNull(bean.getGrossSalary());
		loanData[1][2] = "Application Date ";
		loanData[1][3] = ": " + checkNull(bean.getAppdate());

		loanData[2][0] = "Loan Amount ";
		loanData[2][1] = ": " + checkNull(bean.getLoanAmount());
		loanData[2][2] = "Loan Type ";
		loanData[2][3] = ": " + checkNull(bean.getLoanType());

		loanData[3][0] = "Sanction Amount ";
		loanData[3][1] = ": " + checkNull(String.valueOf(result[0][1]));
		loanData[3][2] = "Sanction Date ";
		loanData[3][3] = ": " + checkNull(String.valueOf(result[0][0]));

		loanData[4][0] = "Payment Date ";
		loanData[4][1] = ": " + checkNull(String.valueOf(result[0][2]));
		loanData[4][2] = "Payment Mode :";
		loanData[4][3] = ": " + checkNull(String.valueOf(result[0][3]));

		loanData[5][0] = "Bank Name ";
		loanData[5][1] = ": " + checkNull(String.valueOf(result[0][4]));
		loanData[5][2] = "Cheque Number ";
		loanData[5][3] = ": " + checkNull(String.valueOf(result[0][5]));

		if (bean.getLoanRefundableFlag().equals("true")) {

			loanData[6][2] = "Interest Rate ";
			loanData[6][3] = ": " + checkNull(String.valueOf(result[0][7]));
			loanData[6][0] = "Interest Type ";
			loanData[6][1] = ": " + checkNull(String.valueOf(result[0][6]));

			if (checkNull(String.valueOf(result[0][8])).equals("")) {
				loanData[7][0] = "EMI Amount ";
				loanData[7][1] = ": "
						+ checkNull(String.valueOf(result[0][13]));
			} else {
				loanData[7][0] = "Number Of Installments ";
				loanData[7][1] = ": " + checkNull(String.valueOf(result[0][8]));
			}

			loanData[7][2] = "";// "Starting Date ";
			loanData[7][3] = "";// +checkNull(String.valueOf(result[0][9]));

			loanData[8][0] = "Security Value ";
			loanData[8][1] = ": " + checkNull(String.valueOf(result[0][10]));
			loanData[8][2] = "Submission Date ";
			loanData[8][3] = ": " + checkNull(String.valueOf(result[0][11]));

			securityDetails[0][0] = "Security Details ";
			securityDetails[0][1] = ": "
					+ checkNull(String.valueOf(result[0][9]));
		}

		loanData[9][0] = "Sanctioned By ";
		loanData[9][1] = ": " + checkNull(String.valueOf(result[0][12]));

		if (bean.getPfTrustFlag().equals("true")) {
			loanData[9][2] = "Loan Refundable ";
			loanData[9][3] = ": " + bean.getLoanRefundable();
		}

		int[] cellwidth2 = { 25, 75 };
		int[] alignmnet2 = { 0, 0 };
		int[] cellwidth = { 25, 25, 25, 25 };
		int[] alignmnet = { 0, 0, 0, 0 };
		rg.addFormatedText(s, 6, 0, 1, 0);
		rg.addText("Date :" + checkNull(String.valueOf(date[0][0])), 0, 2, 0);
		rg.addText("\n", 0, 0, 0);

		rg.tableBodyNoBorder(employee, cellwidth2, alignmnet2);
		rg.tableBodyNoBorder(loanData, cellwidth, alignmnet);
		rg.tableBodyNoBorder(securityDetails, cellwidth2, alignmnet2);

		if (bean.getLoanRefundableFlag().equals("true")) {
			Object[][] guarantor = null;
			Object loanAppCode[] = new Object[1];
			loanAppCode[0] = bean.getLoanAppCode();
			Object[][] guarantorOne = getSqlModel().getSingleResult(
					getQuery(6), loanAppCode);
			Object[][] guarantorTwo = getSqlModel().getSingleResult(
					getQuery(7), loanAppCode);

			if (guarantorOne != null && guarantorOne.length > 0
					&& guarantorTwo == null && guarantorTwo.length == 0) {
				guarantor = new Object[1][6];
				for (int i = 1; i < 6; i++) {
					guarantor[0][0] = "1";
					guarantor[0][i] = guarantorOne[0][i - 1];
				} // end of for loop
			} else if (guarantorOne == null && guarantorOne.length == 0
					&& guarantorTwo != null && guarantorTwo.length > 0) {
				guarantor = new Object[1][6];
				for (int j = 1; j < 6; j++) {
					guarantor[1][0] = "2";
					guarantor[1][j] = guarantorTwo[0][j - 1];
				} // end of for loop
			} else if (guarantorOne != null && guarantorOne.length > 0
					&& guarantorTwo != null && guarantorTwo.length > 0) {
				guarantor = new Object[2][6];
				for (int j = 1; j < 6; j++) {
					guarantor[0][0] = "1";
					guarantor[0][j] = guarantorOne[0][j - 1];
					guarantor[1][0] = "2";
					guarantor[1][j] = guarantorTwo[0][j - 1];
				} // end of for loop
			}

			String guarantorcolumn[] = { "Sr.No", "Employee ID",
					"Employee Name", "Department", "Branch", "Designation" };
			String prePaymentCol[] = { "Sr.No", "Payemnt Date",
					"Payment Amount" };
			int cellwidthPrePay[] = { 15, 30, 30 };
			int alignmentPrePay[] = { 1, 1, 2 };

			int cellwidthGuar[] = { 5, 15, 30, 25, 22, 22 };
			int alignmentGuar[] = { 1, 1, 0, 0, 0, 0 };
			rg.addFormatedText("\n", 0, 0, 0, 0);
			heading[0][0] = "Guarantor Details :";
			rg.tableBodyBold(heading, cells, align);

			if (guarantor != null && guarantor.length > 0) {
				rg.tableBody(guarantorcolumn, guarantor, cellwidthGuar,
						alignmentGuar);
			}
			rg.addFormatedText("\n", 0, 0, 0, 0);

			/*
			 * 
			 * show Installment schedule
			 * 
			 */

			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			Object loanApplCode[] = new Object[1];
			loanApplCode[0] = bean.getLoanAppCode();
			Object[][] installData = getSqlModel().getSingleResult(getQuery(9),
					loanApplCode);
			Object installDataTable[][] = new Object[installData.length][6];
			Object totalObject[][] = new Object[1][6];
			String monthYear = "";
			for (int i = 0; i < installDataTable.length; i++) {
				installDataTable[i][0] = String.valueOf(i + 1);
				monthYear = String.valueOf(installData[i][7]) + "-"
						+ String.valueOf(installData[i][0]) + "-"
						+ String.valueOf(installData[i][1]);
				installDataTable[i][1] = monthYear;
				installDataTable[i][2] = String.valueOf(installData[i][2]);
				installDataTable[i][3] = String.valueOf(installData[i][3]);
				installDataTable[i][4] = String.valueOf(installData[i][4]);

				totalEmi += Double.parseDouble(String
						.valueOf(installData[i][4]));
				totalIntPaid += Double.parseDouble(String
						.valueOf(installData[i][3]));
				totalPrincipal += Double.parseDouble(String
						.valueOf(installData[i][2]));
				if (String.valueOf(installData[i][6]).equals("Y"))
					installDataTable[i][5] = "Yes";
				else
					installDataTable[i][5] = "No";
			} // end of for loop
			String installmentcolumn[] = { "Sr.No", "Installment Date",
					"Principal Amt", "Interest Amt", "EMI Amt", "Is Paid" };
			int cellwidthinstall[] = { 8, 20, 15, 15, 15, 15 };
			int alignmentinstall[] = { 1, 1, 2, 2, 2, 1 };

			int alignmentTotal[] = { 1, 2, 2, 2, 2, 1 };
			heading[0][0] = "Installment Schedule:";
			rg.tableBodyBold(heading, cells, align);
			rg.tableBody(installmentcolumn, installDataTable, cellwidthinstall,
					alignmentinstall);

			totalObject[0][0] = "";
			totalObject[0][1] = "Total :";
			totalObject[0][2] = Math.round(totalPrincipal);
			totalObject[0][3] = Math.round(totalIntPaid);
			totalObject[0][4] = Math.round(totalEmi);
			totalObject[0][5] = "";
			rg.tableBody(totalObject, cellwidthinstall, alignmentTotal);

			/*
			 * end Installment schedule
			 * 
			 */

			/*
			 * show prepayment schedule
			 * 
			 */
			rg.addFormatedText("\n", 0, 0, 0, 0);
			heading[0][0] = "Pre-Payment Details :";
			rg.tableBodyBold(heading, cells, align);
			if (prepaymentResult == null || prepaymentResult.length == 0) {
				rg.addFormatedText("No Pre-Payment done", 0, 0, 1, 0);
			} else
				rg.tableBody(prePaymentCol, prepaymentResult, cellwidthPrePay,
						alignmentPrePay);
		}

		rg.createReport(response);

	}

	/**
	 * Method name-- checkNull parameters--- String result return type-- String
	 * Purpose --- To check whether the value is null or not
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	/*
	 * public String getMonthString(int id){ int month=(id)%12 ;
	 * logger.info("inside getMonthString month==="+month); switch (month) {
	 * case 1:return "Jan"; case 2:return "Feb"; case 3:return "Mar"; case
	 * 4:return "Apr"; case 5:return "May"; case 6:return "June"; case 7:return
	 * "July"; case 8:return "Aug"; case 9:return "Sep"; case 10:return "Oct";
	 * case 11:return "Nov"; case 0:return "Dec"; } return ""; } public String
	 * getMonthNumber(String month){ if(month.equals("Jan")) return "1"; else
	 * if(month.equals("Feb")) return "2"; else if(month.equals("Mar")) return
	 * "3"; else if(month.equals("Apr")) return "4"; else
	 * if(month.equals("May")) return "5"; else if(month.equals("June")) return
	 * "6"; else if(month.equals("July")) return "7"; else
	 * if(month.equals("Aug")) return "8"; else if(month.equals("Sep")) return
	 * "9"; else if(month.equals("Oct")) return "10"; else
	 * if(month.equals("Nov")) return "11"; else if(month.equals("Dec")) return
	 * "12";
	 * 
	 * else return ""; }
	 */

	/**
	 * Method name-- getMonthString parameters--- int id return type-- String
	 * Purpose --- To convert the int representation of the month in to the
	 * String format
	 */
	/*public String getMonthString(int id) {
		if(id > 12 && id <= 24){
			id = id - 12;
		}
		else if(id > 24 && id <= 36){
			id = id - 24;
		}else if(id > 36 && id <= 48){
			id = id - 36;
		}
		else if(id > 48){
			id = id - 48;
		}
		System.out.println("id----"+id);
		DateFormatSymbols obj=new DateFormatSymbols();
		System.out.println("id-22---"+obj.getShortMonths()[id-1]);
		return obj.getShortMonths()[id-1];
	}*/
	
	public String getMonthString(int id) {
		if(id>12){
			if(id%12==0){
				id=12;
			}else{
				id=id%12;
			}
		}
		DateFormatSymbols obj=new DateFormatSymbols();
		return obj.getShortMonths()[id-1];
	}

	/**
	 * Method name-- getMonthNumber parameters--- String month return type--
	 * String Purpose --- To convert the string representation of the month in
	 * to the number format
	 */
	public String getMonthNumber(String month) {
		DateFormatSymbols obj=new DateFormatSymbols();
		HashMap monthMap=new HashMap();
		for (int i = 0; i < 12; i++) {
			monthMap.put(obj.getShortMonths()[i], i+1);
		}
		String returnMonth="";
		try{
			returnMonth=String.valueOf(monthMap.get(month));
		}catch (Exception e) {
			// TODO: handle exception
		}
		if(returnMonth.length()==1){
			returnMonth="0"+returnMonth;
		}
		return returnMonth;
	}

	/**
	 * Method name-- getDay parameters--- int day, int month, int year return
	 * type-- int Purpose --- To get the day of the month
	 */

	public String getDay(String day, int month, int year) {
		int dayOfMonth = Integer.parseInt(day);
		month = month % 12;
		if (month == 0)
			month = 12;
		if (dayOfMonth > 28 && month == 2) {
			if ((year % 4 == 0) || (year % 100 == 0 && year % 400 == 0)) {
				dayOfMonth = 29;
			} else {
				dayOfMonth = 28;
			}
		}
		if (dayOfMonth > 30
				&& (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
			dayOfMonth = 30;
		}

		return String.valueOf(dayOfMonth);
	}

	public String getDate(String date) {
		String dateArray[] = date.split("-");

		int dayOfMonth = Integer.parseInt(dateArray[0]);
		int month = Integer.parseInt(dateArray[1]);
		int year = Integer.parseInt(dateArray[2]);
		month = month % 12;
		if (month == 0)
			month = 12;
		if (dayOfMonth > 28 && month == 2) {
			if ((year % 4 == 0) || (year % 100 == 0 && year % 400 == 0)) {
				dayOfMonth = 29;
			} else {
				dayOfMonth = 28;
			}
		}
		if (dayOfMonth > 30
				&& (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
			dayOfMonth = 30;
		}
		String returnDate = dayOfMonth + "-" + month + "-" + year;
		if (month < 10)
			returnDate = dayOfMonth + "-0" + month + "-" + year;
		return returnDate;
	}

	public void loanApproverCommentList(LoanProcessing bean) {
		ArrayList<Object> list = new ArrayList<Object>();

		/*
		 * select query to select approver comment data from hrms_loan_path
		 */
		String commentQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
				+ "TO_CHAR(LOAN_APPROVED_DATE, 'DD-MM-YYYY'), DECODE(LOAN_APPL_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected') , "
				+ "NVL(LOAN_COMMENTS, ' '), HRMS_LOAN_PATH.LOAN_APPR_AMT  "
				+ "FROM HRMS_LOAN_PATH "
				+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LOAN_PATH.LOAN_APPROVER_CODE) "
				+ "WHERE LOAN_APPL_CODE = "
				+ bean.getHiddenCode()
				+ " ORDER BY LOAN_PATH_ID DESC";

		/*
		 * execute select query and set the comment data to the related form
		 * fields
		 */
		Object[][] comment = getSqlModel().getSingleResult(commentQuery);
		logger.info("comment.length  " + comment.length);
		if (comment != null && comment.length != 0) {
			

			for (int i = 0; i < comment.length; i++) {
				LoanProcessing bean1 = new LoanProcessing();
				/*bean1.setApproverName(String.valueOf(comment[i][0]));
				bean1.setApprovedDate(String.valueOf(comment[i][1]));
				bean1.setApprovedStatus(String.valueOf(comment[i][2]));
				bean1.setApproverComment(String.valueOf(comment[i][3]));*/
				
				bean1.setIttApproverName(checkNull(String.valueOf(comment[i][0])));
				bean1.setIttComments(checkNull(String.valueOf(comment[i][3])));
				bean1.setIttApplicationDate(checkNull(String
						.valueOf(comment[i][1])));
				bean1.setIttStatus(checkNull(String.valueOf(comment[i][2])));
				bean1.setIttApprAmount(checkNull(String.valueOf(comment[i][4])));

				list.add(bean1);
			} // end of for loop

			bean.setListComment(list);
			// bean.setAppCommentFlag("true");
		} // end of if
	}

	public void input(LoanProcessing bean, HttpServletRequest request) {
		String query = "SELECT LOAN_TRACKING_NUMBER, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') "
				+ "	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,LOAN_APPL_CODE, LOAN_CODE,LOAN_NAME, LOAN_AMOUNT  FROM HRMS_LOAN_APPLICATION "
				+ "	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "+
				"INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)";
		/*
		 * if (bean.isGeneralFlag()) { query += " WHERE
		 * HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID=" +
		 * bean.getUserEmpId(); } else { }
		 */

		query += " WHERE 1=1 ";

		String pending = query;
		String logistic = query;
		if (bean.getFlag().equals("")) {
			pending = pending + " AND LOAN_APPL_STATUS IN('F') ";

		} else if (bean.getFlag().equals("AA")) {
			pending = pending + " AND LOAN_APPL_STATUS='A'  ";

		} else if (bean.getFlag().equals("RR")) {
			pending = query + " AND LOAN_APPL_STATUS='R'  ";

		}

		String finalQuery = pending + " AND (LOAN_APPL_APPROVER="
				+ bean.getUserEmpId() + " OR LOAN_APPL_APPROVER="
				+ bean.getUserEmpId() + ") ";
		bean.setListDraft(null);
		Object[][] objApprove = getSqlModel().getSingleResult(
				finalQuery + "   ORDER BY LOAN_APPL_CODE DESC");
		boolean check = false;
		if (objApprove != null && objApprove.length > 0) {
			check = true;
		}

		/*
		 * if(checkReporting(bean)){ logistic = logistic + " ORDER BY
		 * HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC "; objApprove =
		 * getSqlModel().getSingleResult(logistic); check=true; }
		 */

		String apprRejectQuery = "  SELECT distinct LOAN_TRACKING_NUMBER, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') 	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,HRMS_LOAN_APPLICATION.LOAN_APPL_CODE , LOAN_CODE,LOAN_NAME, LOAN_AMOUNT FROM HRMS_LOAN_APPLICATION "
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID)"
				+ "	 INNER JOIN HRMS_LOAN_PATH  ON(HRMS_LOAN_PATH.LOAN_APPL_CODE=HRMS_LOAN_APPLICATION.LOAN_APPL_CODE AND LOAN_APPROVER_CODE="
				+ bean.getUserEmpId() + ") " + 
				" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)"+
				"	WHERE 1=1  ";

		if (bean.getFlag().equals("AA")) {
			apprRejectQuery += "  AND LOAN_APPL_STATUS IN('A') ORDER BY HRMS_LOAN_APPLICATION.LOAN_APPL_CODE DESC ";
			objApprove = getSqlModel().getSingleResult(apprRejectQuery);
			check = true;
		} else if (bean.getFlag().equals("RR")) {
			apprRejectQuery += "  AND LOAN_APPL_STATUS IN('R') ORDER BY HRMS_LOAN_APPLICATION.LOAN_APPL_CODE DESC ";
			objApprove = getSqlModel().getSingleResult(apprRejectQuery);
			check = true;
		}

		String[] pageIndexAppr = setData(bean, request, objApprove,
				"totalPage", "pageNo", bean.getMyPage(), "");

		if (objApprove != null && objApprove.length > 0 && check) {
			ArrayList list = new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
					.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
				LoanProcessing bean1 = new LoanProcessing();
				bean1.setIttEmployeeToken(checkNull(String
						.valueOf(objApprove[i][0])));
				bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objApprove[i][2]));
				// bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
				bean1.setIttloanApplCode(String.valueOf(objApprove[i][4]));
				bean1.setLoanCode(String.valueOf(objApprove[i][5]));
				bean1.setLoanAmount(String.valueOf(objApprove[i][7]));
				list.add(bean1);
				if (bean.getFlag().equals("")) {
					bean1.setButtonName("View/Approve Application");
				} else {
					bean1.setButtonName("View Application");
				}
			}
			bean.setListDraft(list);
		}

	}

	/**
	 * 
	 */

	public String[] setData(LoanProcessing bean, HttpServletRequest request,
			Object[][] data, String totalPage, String pageNo, String page,
			String type) {
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(page,
				data.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute(totalPage, Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute(pageNo, Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1")) {
			bean.setMyPage("1");
			if (type.equals("1")) {
				bean.setMyPage1("1");
			}
			if (type.equals("2")) {
				bean.setMyPage2("1");
			}
		}
		return pageIndex;
	}

	public void setLoanList(LoanProcessing bean, String type) {
		logger.info("type==" + type);
		String quer = "SELECT LOAN_CODE,LOAN_NAME FROM HRMS_LOAN_MASTER ";
		if (type.equals("N")) {
			quer += " WHERE LOAN_CODE NOT IN(SELECT PFT_LOAN_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
					+ " FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE )) AND LOAN_DIV_CODE = '"
					+ bean.getDivCode() + "' ";
		}
		quer += "  ORDER BY UPPER(LOAN_NAME)";
		Object[][] iterator = getSqlModel().getSingleResult(quer);
		HashMap mp = new HashMap();
		try {
			
			for (int i = 0; i < iterator.length; i++) {
				mp.put(String.valueOf(iterator[i][0]), String
						.valueOf(iterator[i][1]));

			}
			mp = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(mp, null, true);
			bean.setLoanTypeHashmap(mp);
		} catch (Exception e) {
			logger.error("exception in setLoanList" + e);
			e.printStackTrace();
		}
	}

	public void getAmountLimit(LoanProcessing loanApp) {
		String loanAmountQuery = " SELECT NVL(LOAN_LIMIT, 0),INT_TYPE, INT_RATE   FROM HRMS_LOAN_MASTER WHERE LOAN_CODE= "
				+ loanApp.getLoanCode();

		Object[][] loanLimitAmt = getSqlModel()
				.getSingleResult(loanAmountQuery);

		if (loanLimitAmt != null && loanLimitAmt.length > 0) {
			loanApp.setLoanAllowedLimit(checkNull(String
					.valueOf(loanLimitAmt[0][0])));

			loanApp.setInterestType(checkNull(String
					.valueOf(loanLimitAmt[0][1])));
			loanApp.setInterestRate(checkNull(String
					.valueOf(loanLimitAmt[0][2])));
		}

	}

	public boolean editApplication(LoanProcessing loanProcess, String requestID) {
		try {

			String editQuery = "SELECT LOAN_APPL_CODE, LOAN_EMP_ID,OFFC.EMP_TOKEN ,"
					+ "OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME,"
					+ " HRMS_LOAN_APPLICATION.LOAN_CODE,LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_AMOUNT,"
					+ " LOAN_APPL_STATUS ,LOAN_APPLICANT_COMMENT ,OFFC.EMP_RANK,RANK_NAME AS DESIGNATION ,"
					+ " OFFC.EMP_DEPT,DEPT_NAME AS DEPARTMENT , OFFC.EMP_CENTER,CENTER_NAME AS BRANCH,"
					+ "TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') ,OFFC.EMP_CADRE,CADRE_NAME ,LOAN_EMI_TYPE, LOAN_INITIATOR_CODE,"
					+ "INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, "
					+ "TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY HH24:MI'),LOAN_TRACKING_NUMBER ,OFFC.EMP_DIV,DIV_NAME ,LOAN_APPL_LEVEL, LOAN_HR_AMT,SAL_ACCNO_REGULAR "
					+ "	FROM HRMS_LOAN_APPLICATION "
					+ "		INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)"
					+ "		LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
					+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = OFFC.EMP_RANK)	"
					+ "	 LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = OFFC.EMP_DEPT)"
					+ "	  LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = OFFC.EMP_CENTER)"
					+ "	   LEFT JOIN HRMS_CADRE  ON (HRMS_CADRE.CADRE_ID = OFFC.EMP_CADRE)"
					+ "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_INITIATOR_CODE)"
					+"LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = OFFC.EMP_DIV)"
					+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID)"
					+ "		WHERE  LOAN_APPL_CODE = " + requestID;

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				loanProcess.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				loanProcess.setEmpCode(checkNull(String.valueOf(editObj[0][1])));
				loanProcess.setEmpToken(checkNull(String.valueOf(editObj[0][2])));
				loanProcess.setEmpName(checkNull(String.valueOf(editObj[0][3])));
				loanProcess.setLoanCode(checkNull(String.valueOf(editObj[0][4])));
				loanProcess.setLoanType(checkNull(String.valueOf(editObj[0][5])));
				loanProcess.setApplicationdate(checkNull(String
						.valueOf(editObj[0][6])));
				loanProcess.setLoanAmount(checkNull(String.valueOf(editObj[0][7])));
				loanProcess.setApplicationStatus(checkNull(String
						.valueOf(editObj[0][8])));
				loanProcess.setApplicantComment(checkNull(String
						.valueOf(editObj[0][9])));

				loanProcess.setDesgCode(checkNull(String.valueOf(editObj[0][10])));
				loanProcess.setDesgName(checkNull(String.valueOf(editObj[0][11])));

				loanProcess.setDeptCode(checkNull(String.valueOf(editObj[0][12])));
				loanProcess.setDeptName(checkNull(String.valueOf(editObj[0][13])));

				loanProcess
						.setBranchCode(checkNull(String.valueOf(editObj[0][14])));
				loanProcess
						.setBranchName(checkNull(String.valueOf(editObj[0][15])));

				loanProcess.setConfirmationDate(checkNull(String
						.valueOf(editObj[0][16])));

				loanProcess.setGradeCode(checkNull(String.valueOf(editObj[0][17])));
				loanProcess.setGrade(checkNull(String.valueOf(editObj[0][18])));

				if (String.valueOf(editObj[0][19]).equals("E")) {
					loanProcess.setExpectedEmi("true");
					loanProcess.setHiddenChechfrmId("E");
					loanProcess.setEmiAmt("true");
					loanProcess.setHiddenCalfrmId("E");
				}

				if (String.valueOf(editObj[0][19]).equals("T")) {
					loanProcess.setTenure("true");
					loanProcess.setHiddenChechfrmId("T");
					loanProcess.setNoOfInstallment("true");
					loanProcess.setHiddenCalfrmId("T");
				}

				loanProcess.setInitiatorCode(checkNull(String
						.valueOf(editObj[0][20])));
				loanProcess.setInitiatorName(checkNull(String
						.valueOf(editObj[0][21])));
				loanProcess.setInitiatorDate(checkNull(String
						.valueOf(editObj[0][22])));
				loanProcess.setTrackingNo(checkNull(String
						.valueOf(editObj[0][23])));
				
				loanProcess.setDivCode(checkNull(String
						.valueOf(editObj[0][24])));
				loanProcess.setDivName(checkNull(String
						.valueOf(editObj[0][25])));
				loanProcess.setLevel(checkNull(String
						.valueOf(editObj[0][26])));
				loanProcess.setSanctionAmount(checkNull(String
						.valueOf(editObj[0][27])));

				
				loanProcess.setAccountNumber(checkNull(String
						.valueOf(editObj[0][28])));
				
				/*for (int i = 0; i < editObj.length; i++) {
					for (int j = 0; j < editObj[i].length; j++) {
						logger.info("addObj[" + i + "][" + j + "]  "
								+ editObj[i][j]);
					}
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getApproverComments(LoanProcessing bean, String requestID) {
		String qq = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_LOAN_PATH.LOAN_COMMENTS,TO_CHAR(HRMS_LOAN_PATH.LOAN_APPROVED_DATE,'DD-MM-YYYY'),"
				+ "		DECODE(HRMS_LOAN_PATH.LOAN_APPL_STATUS,'A','Approved','R','Reject','B','Send Back','P','Pending','Z','Pending for Resubmit','L','Forwarded ') "
				+ "		, HRMS_LOAN_PATH.LOAN_APPR_AMT FROM HRMS_LOAN_PATH "
				+ "	INNER JOIN HRMS_EMP_OFFC ON(HRMS_LOAN_PATH.LOAN_APPROVER_CODE=HRMS_EMP_OFFC.EMP_ID)  "
				+ "	WHERE HRMS_LOAN_PATH .LOAN_APPL_CODE="
				+ requestID
				+ " ORDER BY LOAN_PATH_ID DESC";
		Object[][] data = getSqlModel().getSingleResult(qq);
		ArrayList list = new ArrayList();
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				LoanApproval bean1 = new LoanApproval();
				bean1.setIttApproverName(checkNull(String.valueOf(data[i][0])));
				bean1.setIttComments(checkNull(String.valueOf(data[i][1])));
				bean1.setIttApplicationDate(checkNull(String
						.valueOf(data[i][2])));
				bean1.setIttStatus(checkNull(String.valueOf(data[i][3])));
				bean1.setIttApprAmount(checkNull(String.valueOf(data[i][4])));
				list.add(bean1);
			}
			bean.setListComment(list);
		}
		return "";

	}

	public String changeApplStatus(LoanProcessing bean, String loanCode,
			String empCode) {
		boolean result = false;
		Object[][] to_mailIds = new Object[1][1];
		Object[][] from_mailIds = new Object[1][1];

		String applStatus = "pending";
		
		Object[][] statusChanged = new Object[1][2];
		statusChanged[0][0] = "F";
		statusChanged[0][1] = loanCode;

		result = getSqlModel().singleExecute(getQuery(16), statusChanged);

		String hrQuery = "SELECT LOAN_CODE, ADMIN_CODE, ACCOUNT_CODE FROM HRMS_LOAN_MASTER WHERE  LOAN_CODE="
				+ bean.getLoanCode();

		Object[][] data = getSqlModel().getSingleResult(hrQuery);
		// bean.setBankMicrCode(checkNull((String.valueOf(data[0][0]))));
		bean.setAdminCode(String.valueOf(data[0][2]));

		String updateQuery = "UPDATE HRMS_LOAN_APPLICATION SET "
				+ " LOAN_APPL_APPROVER = " + bean.getAdminCode()
				+ ", LOAN_HR_AMT = "+bean.getApprLoanAmount()+"  WHERE LOAN_APPL_CODE=" +  loanCode;
		result = getSqlModel().singleExecute(updateQuery);

		if (result) {
			applStatus = "approved";
		}
		//showApplications(bean);
		return applStatus;
	}

	public String forward(LoanProcessing bean, String status,
			String loanCode, String empCode, String comments, String apprAmount) {
		Object[][] changeStatus = new Object[1][7];

		String applStatus = "pending";
		boolean result = false;

		/*
		 * Object[][] to_mailIds = new Object[1][1]; Object[][] from_mailIds =
		 * new Object[1][1];
		 */

		changeStatus[0][0] = loanCode;
		changeStatus[0][1] = loanCode;
		changeStatus[0][2] = bean.getUserEmpId();
		changeStatus[0][3] = comments;
		changeStatus[0][4] = status;
		changeStatus[0][5] = empCode;
		changeStatus[0][6] = apprAmount;
		if (status.equals("A")) {
			result = getSqlModel().singleExecute(getQuery(17), changeStatus);
		} // end of if
		else if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status);
			reject[0][1] = String.valueOf(loanCode);

			result = getSqlModel().singleExecute(getQuery(16), reject);
			result = getSqlModel().singleExecute(getQuery(17), changeStatus);

			if (result) {
				applStatus = "rejected";

			} // end of else if

			/*
			 * if (result) {
			 * 
			 * send the mail to the next approver
			 * 
			 * 
			 * try { to_mailIds[0][0] = empCode; from_mailIds[0][0] =
			 * bean.getUserEmpId();
			 * 
			 * MailUtility mail = new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds, "Loan", "", "R");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); logger .info("exception in send
			 * mail to next approver()" + e); } }
			 */
		} // end of else
		//showApplications(bean);
		return applStatus;
		
	}

	public void setApproverData(LoanProcessing bean, Object[][] empFlow) {
		try {
			if(empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][3];
				for(int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' " + ", HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC "
						+ " left JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)" + " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
					approverDataObj[i][1] = String.valueOf(temObj[0][1]);
					approverDataObj[i][2] = String.valueOf(temObj[0][2]);
				}

				if(approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for(int i = 0; i < approverDataObj.length; i++) {
						LoanProcessing bean1 = new LoanProcessing();
						bean1.setApproverName(String.valueOf(approverDataObj[i][0]));
						bean1.setApproverCode(String.valueOf(approverDataObj[i][2]));
						// String srNo = i + 1 + getOrdinalFor(i + 1)
						// + "-Approver";
						// bean1.setSrNoIterator(srNo);
						arrList.add(bean1);
					}
					bean.setApproverList(arrList);
				}else {
					
				}

			}
		} catch(Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}

	}

	public String sendBack(LoanProcessing loanProcess, String applicationId,
			 String empCode, String comments, String apprAmount, String apprStatus) {
		String updateApproverCommentsSql = " UPDATE HRMS_LOAN_APPLICATION SET LOAN_APPL_STATUS = '"+apprStatus+"', LOAN_APPL_LEVEL = 0 "
			+ " WHERE LOAN_APPL_CODE = " + applicationId;
			getSqlModel().singleExecute(updateApproverCommentsSql);

			insertApproverComments(loanProcess,applicationId, empCode, comments, apprStatus,apprAmount);
			
			return "B";
	}

	public boolean viewApplication(LoanProcessing loanProcess, String requestID) {
		try {

			String editQuery = "SELECT LOAN_APPL_CODE, LOAN_EMP_ID,OFFC.EMP_TOKEN ,"
					+ "OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME,"
					+ " HRMS_LOAN_APPLICATION.LOAN_CODE,LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_AMOUNT,"
					+ " LOAN_APPL_STATUS ,LOAN_APPLICANT_COMMENT ,OFFC.EMP_RANK,RANK_NAME AS DESIGNATION ,"
					+ " OFFC.EMP_DEPT,DEPT_NAME AS DEPARTMENT , OFFC.EMP_CENTER,CENTER_NAME AS BRANCH,"
					+ "TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') ,OFFC.EMP_CADRE,CADRE_NAME ,LOAN_EMI_TYPE, LOAN_INITIATOR_CODE,"
					+ "INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, "
					+ "TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY HH24:MI'),LOAN_TRACKING_NUMBER ,OFFC.EMP_DIV,DIV_NAME ,LOAN_APPL_LEVEL "
					+ "	FROM HRMS_LOAN_APPLICATION "
					+ "		INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)"
					+ "		LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
					+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = OFFC.EMP_RANK)	"
					+ "	 LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = OFFC.EMP_DEPT)"
					+ "	  LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = OFFC.EMP_CENTER)"
					+ "	   LEFT JOIN HRMS_CADRE  ON (HRMS_CADRE.CADRE_ID = OFFC.EMP_CADRE)"
					+ "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_INITIATOR_CODE)"
					+"LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = OFFC.EMP_DIV)"
							+ "		WHERE  LOAN_APPL_CODE = " + requestID;

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				loanProcess.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				loanProcess.setEmpCode(checkNull(String.valueOf(editObj[0][1])));
				loanProcess.setEmpToken(checkNull(String.valueOf(editObj[0][2])));
				loanProcess.setEmpName(checkNull(String.valueOf(editObj[0][3])));
				loanProcess.setLoanCode(checkNull(String.valueOf(editObj[0][4])));
				loanProcess.setLoanType(checkNull(String.valueOf(editObj[0][5])));
				loanProcess.setApplicationdate(checkNull(String
						.valueOf(editObj[0][6])));
				loanProcess.setLoanAmount(checkNull(String.valueOf(editObj[0][7])));
				loanProcess.setApplicationStatus(checkNull(String
						.valueOf(editObj[0][8])));
				loanProcess.setApplicantComment(checkNull(String
						.valueOf(editObj[0][9])));

				loanProcess.setDesgCode(checkNull(String.valueOf(editObj[0][10])));
				loanProcess.setDesgName(checkNull(String.valueOf(editObj[0][11])));

				loanProcess.setDeptCode(checkNull(String.valueOf(editObj[0][12])));
				loanProcess.setDeptName(checkNull(String.valueOf(editObj[0][13])));

				loanProcess
						.setBranchCode(checkNull(String.valueOf(editObj[0][14])));
				loanProcess
						.setBranchName(checkNull(String.valueOf(editObj[0][15])));

				loanProcess.setConfirmationDate(checkNull(String
						.valueOf(editObj[0][16])));

				loanProcess.setGradeCode(checkNull(String.valueOf(editObj[0][17])));
				loanProcess.setGrade(checkNull(String.valueOf(editObj[0][18])));

				if (String.valueOf(editObj[0][19]).equals("E")) {
					loanProcess.setExpectedEmi("true");
					loanProcess.setHiddenChechfrmId("E");
					
					loanProcess.setEmiAmt("true");
					loanProcess.setHiddenCalfrmId("E");
					
					
				}

				if (String.valueOf(editObj[0][19]).equals("T")) {
					loanProcess.setTenure("true");
					loanProcess.setHiddenChechfrmId("T");
					
					loanProcess.setNoOfInstallment("true");
					loanProcess.setHiddenCalfrmId("T");
					
				}
				

				loanProcess.setInitiatorCode(checkNull(String
						.valueOf(editObj[0][20])));
				loanProcess.setInitiatorName(checkNull(String
						.valueOf(editObj[0][21])));
				loanProcess.setInitiatorDate(checkNull(String
						.valueOf(editObj[0][22])));
				loanProcess.setTrackingNo(checkNull(String
						.valueOf(editObj[0][23])));
				
				loanProcess.setDivCode(checkNull(String
						.valueOf(editObj[0][24])));
				loanProcess.setDivName(checkNull(String
						.valueOf(editObj[0][25])));
				loanProcess.setLevel(checkNull(String
						.valueOf(editObj[0][26])));
				
				
				if (String.valueOf(editObj[0][19]).equals("T")) {
					loanProcess.setNoOfInstallment("true");
					loanProcess.setHiddenCalfrmId("T");
				}
				if (String.valueOf(editObj[0][19]).equals("E")) {
					loanProcess.setEmiAmt("true");
					loanProcess.setHiddenCalfrmId("E");
				}
				
				/*for (int i = 0; i < editObj.length; i++) {
					for (int j = 0; j < editObj[i].length; j++) {
						logger.info("addObj[" + i + "][" + j + "]  "
								+ editObj[i][j]);
					}
				}*/
			}
			
			
			String processQuery = "SELECT LOAN_APPL_CODE, LOAN_SANCTION_AMOUNT, LOAN_SANCTION_DATE, " +
					"LOAN_PAYMENT_DATE, LOAN_PAYMENT_MODE, LOAN_BANK_NAME, LOAN_CHEQUE_NUMBER, " +
					"LOAN_INTEREST_RATE, LOAN_INTEREST_TYPE, LOAN_INTALMENT_NUMBERS, " +
					"LOAN_INSTAL_START_MONTH, LOAN_SECURITY_DETAILS, LOAN_SECURITY_VALUE, " +
					"LOAN_SUBMISSION_DATE, LOAN_INSTAL_START_YEAR, LOAN_INSTALLMENT_START_DATE," +
					" LOAN_REQ_EMI_AMOUNT, LOAN_MONHLY_PRINCIPAL, LOAN_EMP_ACCOUNT "+
					"FROM HRMS_LOAN_PROCESS	WHERE  LOAN_APPL_CODE = " + requestID;

		Object[][] processObj = getSqlModel().getSingleResult(processQuery);
		if (processObj != null && processObj.length > 0) {
			loanProcess.setSanctionAmount(checkNull(String.valueOf(processObj[0][1])));
			loanProcess.setSanctionDate(checkNull(String.valueOf(processObj[0][2])));
			loanProcess.setPaymentDate(checkNull(String.valueOf(processObj[0][3])));
			loanProcess.setPaymentMode(checkNull(String.valueOf(processObj[0][4])));
			loanProcess.setBankName(checkNull(String.valueOf(processObj[0][5])));
			loanProcess.setChequeNumber(checkNull(String.valueOf(processObj[0][6])));
			loanProcess.setInterestType(checkNull(String.valueOf(processObj[0][8])));
			loanProcess.setInstallmentNumberFlat(checkNull(String.valueOf(processObj[0][9])));
			loanProcess.setStartingDate(checkNull(String.valueOf(processObj[0][15])));
			loanProcess.setEmiAmt(checkNull(String.valueOf(processObj[0][16])));
			loanProcess.setPrnAmt(checkNull(String.valueOf(processObj[0][17])));
			loanProcess.setAccountNumber(checkNull(String.valueOf(processObj[0][18])));
			
			
		}
		
		String installShcQuery = "SELECT TO_CHAR(TO_DATE(LOAN_INSTAL_MONTH,'MM'),'MON'),LOAN_INSTAL_YEAR,LOAN_PRINCIPLE_AMT,"
		+"	LOAN_INSTAL_INTEREST,LOAN_INSTAL_AMT,LOAN_BALANCE_PRINCIPAL,  "
		+"	LOAN_INSTALLMENT_IS_PAID,LOAN_INSTAL_DAY,LOAN_INSTAL_DAY||'-'||(LOAN_INSTAL_MONTH+1)||'-'||LOAN_INSTAL_YEAR "
		+"	 	FROM HRMS_LOAN_INSTALMENT "
		+"		WHERE LOAN_APPL_CODE="+ requestID + " AND LOAN_INSTALLMENT_IS_PAID ='N' "
		+"		ORDER BY LOAN_INSTAL_YEAR, LOAN_INSTAL_MONTH, LOAN_PRINCIPLE_AMT";

		Object[][] installShcObj = getSqlModel().getSingleResult(installShcQuery);
		if (installShcObj != null && installShcObj.length > 0) {
			loanProcess.setMonthYear(checkNull(String.valueOf(processObj[0][0])));
			loanProcess.setPrincipalAmt(checkNull(String.valueOf(processObj[0][2])));
			loanProcess.setInterestAmt(checkNull(String.valueOf(processObj[0][3])));
			loanProcess.setInstallmentAmt(checkNull(String.valueOf(processObj[0][4])));
			
			
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This function is used for showing Applicant Comment And EMI Type.
	 * @param loanProcess
	 * @param requestID
	 * @return
	 */
	public boolean viewApplicantDetails(LoanProcessing loanProcess,
			String requestID) {

		try {

			String editQuery = " SELECT LOAN_APPL_CODE,LOAN_APPLICANT_COMMENT,LOAN_EMI_TYPE " +
					"FROM HRMS_LOAN_APPLICATION WHERE  LOAN_APPL_CODE =  " + loanProcess.getHiddenCode();

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				loanProcess.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				loanProcess.setApplicantComment(checkNull(String
						.valueOf(editObj[0][1])));
				
				if (String.valueOf(editObj[0][2]).equals("E")) {
					loanProcess.setExpectedEmi("true");
					//loanProcess.setHiddenChechfrmId("E");
					//loanProcess.setEmiAmt("true");
					//loanProcess.setHiddenCalfrmId("E");
				}
				if (String.valueOf(editObj[0][2]).equals("T")) {
					loanProcess.setTenure("true");
					//loanProcess.setHiddenChechfrmId("T");
					//loanProcess.setNoOfInstallment("true");
					//loanProcess.setHiddenCalfrmId("T");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	
	}

	public boolean isCurrentUser(String userId, LoanProcessing loanProcess) {
		String currentUserSql = " SELECT * FROM HRMS_LOAN_MASTER " +
				"INNER JOIN HRMS_LOAN_APPLICATION ON (HRMS_LOAN_APPLICATION.LOAN_CODE = HRMS_LOAN_MASTER.LOAN_CODE) " +
				"WHERE  HRMS_LOAN_MASTER.ACCOUNT_CODE=" + userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(currentUserSql);
		if(currentUserObj != null && currentUserObj.length > 0) { return true; }
		return false;
	}

	
	public void getPendingList(LoanProcessing bean, HttpServletRequest request, String userId) {
		try {
			Object[][] pendingListData = null;
			ArrayList pendingList = new ArrayList();


			// For drafted application Begins

			String selQuery = "SELECT LOAN_TRACKING_NUMBER,EMP_TOKEN  || ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME," +
					"TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') 	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,LOAN_APPL_CODE, " +
					"LOAN_CODE,LOAN_NAME, LOAN_AMOUNT ,LOAN_APPL_STATUS  FROM HRMS_LOAN_APPLICATION 	" 
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID)" 
					+" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "
					+" WHERE 1=1  AND LOAN_APPL_STATUS IN('F')  "
					+" AND LOAN_APPL_APPROVER="+userId+"  AND LOAN_HR_AMT  IS NOT NULL "
					+" ORDER BY LOAN_APPL_CODE DESC ";

			pendingListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(), pendingListData.length, 20);
			if(pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalPendingPage", Integer.parseInt(String.valueOf(pageIndexDrafted[2])));
			request.setAttribute("pendingPageNo", Integer.parseInt(String.valueOf(pageIndexDrafted[3])));
			if(pageIndexDrafted[4].equals("1")) bean.setMyPage("1");
			bean.setPendingIteratorList(null);
			if(pendingListData != null && pendingListData.length > 0) {
				bean.setPendingListLength(true);
				for(int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer.parseInt(pageIndexDrafted[1]); i++) {
					LoanProcessing beanItt = new LoanProcessing();
					beanItt.setTrackingNo(checkNull(String.valueOf(pendingListData[i][0])));
					beanItt.setEmpName(checkNull(String.valueOf(pendingListData[i][1])));
					beanItt.setApplicationdate(checkNull(String.valueOf(pendingListData[i][2])));
					beanItt.setApplicationStatus(checkNull(String.valueOf(pendingListData[i][8])));
					beanItt.setLoanAppCode(checkNull(String.valueOf(pendingListData[i][4])));
					beanItt.setLoanType(checkNull(String.valueOf(pendingListData[i][6])));
					pendingList.add(beanItt);
				}
				bean.setPendingIteratorList(pendingList);
			}
			// For drafted application Ends

			

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void getApprovedList(LoanProcessing bean, HttpServletRequest request, String userId) {
		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();


			// Approved application Begins
			String selQuery = "SELECT DISTINCT  LOAN_TRACKING_NUMBER,EMP_TOKEN  || ' - ' || EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME," +
			"TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY') 	,HRMS_LOAN_APPLICATION.LOAN_EMP_ID,LOAN_APPL_CODE, " +
			"LOAN_CODE,LOAN_NAME, LOAN_AMOUNT ,LOAN_APPL_STATUS  FROM HRMS_LOAN_APPLICATION 	" 
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOAN_APPLICATION.LOAN_EMP_ID)" 
			+" INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE) "
			+" INNER JOIN HRMS_LOAN_PATH  ON(HRMS_LOAN_PATH.LOAN_APPL_CODE=HRMS_LOAN_APPLICATION.LOAN_APPL_CODE AND LOAN_APPROVER_CODE="+userId+")  "
			+" WHERE 1=1  AND HRMS_LOAN_APPLICATION.LOAN_APPL_STATUS IN('A')  "
			///+" AND (LOAN_APPL_APPROVER="+userId+" OR LOAN_APPL_APPROVER="+userId+")    "
			+" ORDER BY LOAN_APPL_CODE DESC ";

			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(bean.getMyPageApproved(), approvedListData.length, 20);
			if(pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndexApproved[3])));
			if(pageIndexApproved[4].equals("1")) bean.setMyPageApproved("1");
			bean.setApprovedIteratorList(null);
			if(approvedListData != null && approvedListData.length > 0) {
				bean.setApprovedListLength(true);
				for(int i = Integer.parseInt(pageIndexApproved[0]); i < Integer.parseInt(pageIndexApproved[1]); i++) {
					LoanProcessing beanItt = new LoanProcessing();
								
					beanItt.setTrackingNo(checkNull(String.valueOf(approvedListData[i][0])));
					beanItt.setEmpName(checkNull(String.valueOf(approvedListData[i][1])));
					beanItt.setApplicationdate(checkNull(String.valueOf(approvedListData[i][2])));
					beanItt.setApplicationStatus(checkNull(String.valueOf(approvedListData[i][8])));
					beanItt.setLoanAppCode(checkNull(String.valueOf(approvedListData[i][4])));
					beanItt.setLoanType(checkNull(String.valueOf(approvedListData[i][6])));	
					approvedList.add(beanItt);
				}
				bean.setApprovedIteratorList(approvedList);
			}
			// Approved application Ends
	

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
